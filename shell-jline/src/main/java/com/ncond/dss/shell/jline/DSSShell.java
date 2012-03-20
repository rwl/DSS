package com.ncond.dss.shell.jline;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import jline.ANSIBuffer;
import jline.ConsoleReader;
import jline.WindowsTerminal;
import jline.ANSIBuffer.ANSICodes;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.roo.shell.AbstractShell;
import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.ExitShellRequest;
import org.springframework.roo.shell.Parser;
import org.springframework.roo.shell.event.ShellStatus;
import org.springframework.roo.shell.event.ShellStatusListener;
import org.springframework.roo.shell.event.ShellStatus.Status;
import org.springframework.roo.shell.jline.JLineCompletorAdapter;
import org.springframework.roo.shell.jline.JLineShell;
import org.springframework.roo.support.util.AnsiEscapeCode;

public class DSSShell extends JLineShell {

	private static class FlashInfo {
		Level flashLevel;
		String flashMessage;
		long flashMessageUntil;
		int rowNumber;
	}

	protected static final String DSS_PROMPT = "dss> ";

	private static final String ANSI_CONSOLE_CLASSNAME = "org.fusesource.jansi.AnsiConsole";
	private static final boolean APPLE_TERMINAL = Boolean
			.getBoolean("is.apple.terminal");

	private static final String BEL = "\007";
	private static final char ESCAPE = 27;
	private static final boolean JANSI_AVAILABLE = isPresent(
			ANSI_CONSOLE_CLASSNAME,
			JLineShell.class.getClassLoader());

	private static boolean isPresent(final String className,
			final ClassLoader classLoader) {
		try {
			return classLoader.loadClass(className) != null;
		} catch (final Throwable t) {
			// Class or one of its dependencies is not present...
			return false;
		}
	}

	private final DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final Map<Integer, Integer> rowErasureMap = new HashMap<Integer, Integer>();

	private FileWriter fileLog;
	private final Map<String, FlashInfo> flashInfoMap = new HashMap<String, FlashInfo>();

	private ConsoleReader reader;

	private boolean shutdownHookFired = false;

	private ConsoleReader createAnsiWindowsReader() throws Exception {
		// Get decorated OutputStream that parses ANSI-codes
		final PrintStream ansiOut = (PrintStream) ClassUtils
				.getClass(JLineShell.class.getClassLoader(),
						ANSI_CONSOLE_CLASSNAME)
				.getMethod("out").invoke(null);
		final WindowsTerminal ansiTerminal = new WindowsTerminal() {
			@Override
			public boolean isANSISupported() {
				return true;
			}
		};
		ansiTerminal.initializeTerminal();
		// Make sure to reset the original shell's colors on shutdown by
		// closing the stream
		statusListener = new ShellStatusListener() {
			public void onShellStatusChange(
					final ShellStatus oldStatus,
					final ShellStatus newStatus) {
				if (newStatus.getStatus().equals(
						Status.SHUTTING_DOWN)) {
					ansiOut.close();
				}
			}
		};
		addShellStatusListener(statusListener);

		return new ConsoleReader(
				new FileInputStream(FileDescriptor.in),
				new PrintWriter(new OutputStreamWriter(ansiOut,
					System.getProperty("jline.WindowsTerminal.output.encoding", "Cp850"))),
				null, ansiTerminal);
	}

	// Externally synchronized via the two calling methods having a mutex on
	// flashInfoMap
	private void doAnsiFlash(final int row, final Level level,
			final String message) {
		final ANSIBuffer buff = DSSLogHandler.getANSIBuffer();
		if (APPLE_TERMINAL) {
			buff.append(ESCAPE + "7");
		} else {
			buff.append(ANSICodes.save());
		}

		// Figure out the longest line we're presently displaying (or
		// were) and
		// erase the line from that position
		int mostFurtherLeftColNumber = Integer.MAX_VALUE;
		for (final Integer candidate : rowErasureMap.values()) {
			if (candidate < mostFurtherLeftColNumber) {
				mostFurtherLeftColNumber = candidate;
			}
		}

		if (mostFurtherLeftColNumber == Integer.MAX_VALUE) {
			// There is nothing to erase
		} else {
			buff.append(ANSICodes.gotoxy(row,
					mostFurtherLeftColNumber));
			// Clear what was present on the line
			buff.append(ANSICodes.clreol());
		}

		if ("".equals(message)) {
			// They want the line blank; we've already achieved this
			// if needed via the erasing above
			// Just need to record we no longer care about this line
			// the next time doAnsiFlash is invoked
			rowErasureMap.remove(row);
		} else {
			if (shutdownHookFired) {
				return; // ROO-1599
			}
			// They want some message displayed
			int startFrom = reader.getTermwidth() - message.length() + 1;
			if (startFrom < 1) {
				startFrom = 1;
			}
			buff.append(ANSICodes.gotoxy(row, startFrom));
			buff.reverse(message);
			// Record we want to erase from this positioning next
			// time (so we
			// clean up after ourselves)
			rowErasureMap.put(row, startFrom);
		}
		if (APPLE_TERMINAL) {
			buff.append(ESCAPE + "8");
		} else {
			buff.append(ANSICodes.restore());
		}

		final String stg = buff.toString();
		try {
			reader.printString(stg);
			reader.flushConsole();
		} catch (final IOException ignored) {
		}
	}

	@Override
	public void flash(final Level level, final String message,
			final String slot) {
		Validate.notNull(level, "Level is required for a flash message");
		Validate.notNull(message, "Message is required for a flash message");
		Validate.notBlank(slot, "Slot name must be specified for a flash message");

		if (Shell.WINDOW_TITLE_SLOT.equals(slot)) {
			if (reader != null && reader.getTerminal().isANSISupported()) {
				// We can probably update the window title, as requested
				if (StringUtils.isBlank(message)) {
					System.out.println("No text");
				}

				final ANSIBuffer buff = DSSLogHandler.getANSIBuffer();
				buff.append(ESCAPE + "]0;").append(message).append(BEL);
				final String stg = buff.toString();
				try {
					reader.printString(stg);
					reader.flushConsole();
				} catch (final IOException ignored) {
				}
			}

			return;
		}
		if (reader != null && !reader.getTerminal().isANSISupported()) {
			super.flash(level, message, slot);
			return;
		}
		synchronized (flashInfoMap) {
			FlashInfo flashInfo = flashInfoMap.get(slot);

			if ("".equals(message)) {
				// Request to clear the message, but give the
				// user some time to read it first
				if (flashInfo == null) {
					// We didn't have a record of displaying
					// it in the first place, so just quit
					return;
				}
				flashInfo.flashMessageUntil = System.currentTimeMillis() + 1500;
			} else {
				// Display this message displayed until further notice
				if (flashInfo == null) {
					// Find a row for this new slot; we
					// basically take the first
					// line number we discover
					flashInfo = new FlashInfo();
					flashInfo.rowNumber = Integer.MAX_VALUE;
					outer: for (int i = 1; i < Integer.MAX_VALUE; i++) {
						for (final FlashInfo existingFlashInfo : flashInfoMap.values()) {
							if (existingFlashInfo.rowNumber == i) {
								// Veto, let's
								// try the new
								// candidate row
								// number
								continue outer;
							}
						}
						// If we got to here, nobody
						// owns this row number, so
						// use it
						flashInfo.rowNumber = i;
						break outer;
					}

					// Store it
					flashInfoMap.put(slot, flashInfo);
				}
				// Populate the instance with the latest data
				flashInfo.flashMessageUntil = Long.MAX_VALUE;
				flashInfo.flashLevel = level;
				flashInfo.flashMessage = message;

				// Display right now
				doAnsiFlash(flashInfo.rowNumber,
						flashInfo.flashLevel,
						flashInfo.flashMessage);
			}
		}
	}

	private void flashMessageRenderer() {
		if (!reader.getTerminal().isANSISupported()) {
			return;
		}
		// Setup a thread to ensure flash messages are displayed and
		// cleared
		// correctly
		final Thread t = new Thread(new Runnable() {
			public void run() {
				while (!shellStatus.getStatus().equals(
						Status.SHUTTING_DOWN)
						&& !shutdownHookFired) {
					synchronized (flashInfoMap) {
						final long now = System.currentTimeMillis();

						final Set<String> toRemove = new HashSet<String>();
						for (final String slot : flashInfoMap
								.keySet()) {
							final FlashInfo flashInfo = flashInfoMap
									.get(slot);

							if (flashInfo.flashMessageUntil < now) {
								// Message has
								// expired, so
								// clear it
								toRemove.add(slot);
								doAnsiFlash(flashInfo.rowNumber,
										Level.ALL,
										"");
							} else {
								// The
								// expiration
								// time for this
								// message has
								// not
								// been reached,
								// so preserve
								// it
								doAnsiFlash(flashInfo.rowNumber,
										flashInfo.flashLevel,
										flashInfo.flashMessage);
							}
						}
						for (final String slot : toRemove) {
							flashInfoMap.remove(slot);
						}
					}
					try {
						Thread.sleep(200);
					} catch (final InterruptedException ignore) {
					}
				}
			}
		}, "Spring Roo JLine Flash Message Manager");
		t.start();
	}

	private void openFileLogIfPossible() {
		try {
			fileLog = new FileWriter("log.dss", true);
			// First write, so let's record the date and time of the
			// first user
			// command
			fileLog.write("// DSS " + versionInfo() + " log opened at "
					+ df.format(new Date()) + "\n");
			fileLog.flush();
		} catch (final IOException ignoreIt) {
		}
	}

	private void removeHandlers(final Logger l) {
		final Handler[] handlers = l.getHandlers();
		if (handlers != null && handlers.length > 0) {
			for (final Handler h : handlers) {
				l.removeHandler(h);
			}
		}
	}

	public void run() {
		try {
			if (JANSI_AVAILABLE && SystemUtils.IS_OS_WINDOWS) {
				try {
					reader = createAnsiWindowsReader();
				} catch (final Exception e) {
					// Try again using default ConsoleReader
					// constructor
					logger.warning("Can't initialize jansi AnsiConsole, falling back to default: " + e);
				}
			}
			if (reader == null) {
				reader = new ConsoleReader();
			}
		} catch (final IOException ioe) {
			throw new IllegalStateException(
					"Cannot start console class", ioe);
		}

		setPromptPath(null);

		final DSSLogHandler handler = new DSSLogHandler(reader, this);
		DSSLogHandler.prohibitRedraw(); // Affects this thread only
		final Logger mainLogger = Logger.getLogger("");
		removeHandlers(mainLogger);
		mainLogger.addHandler(handler);

		reader.addCompletor(new JLineDSSCompletor(getParser()));

		reader.setBellEnabled(true);
		if (Boolean.getBoolean("jline.nobell")) {
			reader.setBellEnabled(false);
		}

		// reader.setDebug(new PrintWriter(new FileWriter("writer.debug", true)));

		openFileLogIfPossible();

		// Try to build previous command history from the project's log
		try {
			final String logFileContents = FileUtils
					.readFileToString(new File("log.dss"));
			final String[] logEntries = logFileContents
					.split(IOUtils.LINE_SEPARATOR);
			// LIFO
			for (final String logEntry : logEntries) {
				if (!logEntry.startsWith("//")) {
					reader.getHistory().addToHistory(
							logEntry);
				}
			}
		} catch (final IOException ignored) {
		}

		flashMessageRenderer();

//		logger.info(version(null));

		flash(Level.FINE, "DSS " + versionInfo(),
				Shell.WINDOW_TITLE_SLOT);

		logger.info("Welcome to DSS. For assistance press "
				+ completionKeys
				+ " or type \"help\" then hit ENTER.");

		final String startupNotifications = getStartupNotifications();
		if (StringUtils.isNotBlank(startupNotifications)) {
			logger.info(startupNotifications);
		}

		setShellStatus(Status.STARTED);

		// Monitor CTRL+C initiated shutdowns (ROO-1599)
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				shutdownHookFired = true;
				// We don't need to closeShell(), as the
				// shutdown hook in
				// o.s.r.bootstrap.Main calls stop() which calls
				// JLineShellComponent.deactivate() and that
				// calls closeShell()
			}
		}, "DSS JLine Shutdown Hook"));

		// Handle any "execute-then-quit" operation
		final String dssArgs = System.getProperty("dss.args");
		if (dssArgs != null && !"".equals(dssArgs)) {
			setShellStatus(Status.USER_INPUT);
			final boolean success = executeCommand(dssArgs);
			if (exitShellRequest == null) {
				// The command itself did not specify an exit
				// shell code, so
				// we'll fall back to something sensible here
				executeCommand("quit"); // ROO-839
				exitShellRequest = success ? ExitShellRequest.NORMAL_EXIT
						: ExitShellRequest.FATAL_EXIT;
			}
			setShellStatus(Status.SHUTTING_DOWN);
		} else {
			// Normal RPEL processing
			promptLoop();
		}
	}

	@Override
	protected Collection<URL> findResources(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ExecutionStrategy getExecutionStrategy() {
		return new DSSExecutionStrategy();
	}

	@Override
	protected Parser getParser() {
		return new BasicParser();
	}

	@Override
	public void setPromptPath(final String path) {
		setPromptPath(path, false);
	}

	@Override
	public void setPromptPath(final String path, final boolean overrideStyle) {
		if (reader.getTerminal().isANSISupported()) {
			if (StringUtils.isBlank(path)) {
				shellPrompt = AnsiEscapeCode.decorate(
						DSS_PROMPT,
						AnsiEscapeCode.FG_YELLOW);
			} else {
				final String decoratedPath = overrideStyle ? AnsiEscapeCode
						.decorate(path)
						: AnsiEscapeCode.decorate(
								path,
								AnsiEscapeCode.FG_CYAN);
				shellPrompt = decoratedPath
						+ AnsiEscapeCode.decorate(
								" "
										+ DSS_PROMPT,
								AnsiEscapeCode.FG_YELLOW);
			}
		} else {
			// The superclass will do for this non-ANSI terminal
			super.setPromptPath(path);
		}

		// The shellPrompt is now correct; let's ensure it now gets used
		reader.setDefaultPrompt(AbstractShell.shellPrompt);
	}

	public void promptLoop() {
		setShellStatus(Status.USER_INPUT);
		String line;

		try {
			while (exitShellRequest == null && ((line = reader.readLine()) != null)) {
				DSSLogHandler.resetMessageTracking();
				setShellStatus(Status.USER_INPUT);

				if ("".equals(line)) {
					continue;
				}

				executeCommand(line);
			}
		} catch (IOException ioe) {
			throw new IllegalStateException(
					"Shell line reading failure", ioe);
		}

		setShellStatus(Status.SHUTTING_DOWN);
	}


	    public static String versionInfo() {
	        // Try to determine the bundle version
	        String bundleVersion = null;
	        String gitCommitHash = null;
	        JarFile jarFile = null;
	        try {
	            final URL classContainer = DSSShell.class
	                    .getProtectionDomain().getCodeSource().getLocation();
	            if (classContainer.toString().endsWith(".jar")) {
	                // Attempt to obtain the "Bundle-Version" version from the
	                // manifest
	                jarFile = new JarFile(new File(classContainer.toURI()), false);
	                final ZipEntry manifestEntry = jarFile
	                        .getEntry("META-INF/MANIFEST.MF");
	                final Manifest manifest = new Manifest(
	                        jarFile.getInputStream(manifestEntry));
	                bundleVersion = manifest.getMainAttributes().getValue(
	                        "Bundle-Version");
	                gitCommitHash = manifest.getMainAttributes().getValue(
	                        "Git-Commit-Hash");
	            }
	        }
	        catch (final IOException ignoreAndMoveOn) {
	        }
	        catch (final URISyntaxException ignoreAndMoveOn) {
	        }
	        finally {
	            if (jarFile != null) {
	                try {
	                    jarFile.close();
	                }
	                catch (final IOException ignored) {
	                }
	            }
	        }

	        final StringBuilder sb = new StringBuilder();

	        if (bundleVersion != null) {
	            sb.append(bundleVersion);
	        }

	        if (gitCommitHash != null && gitCommitHash.length() > 7) {
	            if (sb.length() > 0) {
	                sb.append(" ");
	            }
	            sb.append("[rev ");
	            sb.append(gitCommitHash.substring(0, 7));
	            sb.append("]");
	        }

	        if (sb.length() == 0) {
	            sb.append("UNKNOWN VERSION");
	        }

	        return sb.toString();
	    }

}

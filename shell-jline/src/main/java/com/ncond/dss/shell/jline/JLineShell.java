/**
 * Copyright (C) 2008-2012 SpringSource Inc. All Rights Reserved.
 * Copyright (C) 2011-2012 Richard Lincoln
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Originally part of Spring Roo (http://www.springsource.org/spring-roo)
 */

package com.ncond.dss.shell.jline;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SystemUtils;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.DSSObject;

import jline.ANSIBuffer;
import jline.ConsoleReader;
import jline.WindowsTerminal;

public class JLineShell implements Runnable {

	private static final String ANSI_CONSOLE_CLASSNAME = "org.fusesource.jansi.AnsiConsole";

	private static final boolean JANSI_AVAILABLE = isPresent(
			ANSI_CONSOLE_CLASSNAME, JLineShell.class.getClassLoader());

	private PrintStream ansiOut;

	protected ConsoleReader reader;

	public static String promptString = "dss>";

	protected String shellPrompt = promptString + " ";

	public static String completionKeys = "TAB";

	private Executive executive;

	public void run() {
		try {
			if (JANSI_AVAILABLE && SystemUtils.IS_OS_WINDOWS) {
				try {
					reader = createAnsiWindowsReader();
				} catch (final Exception e) {
					// try again using default ConsoleReader constructor
					System.err.println("Can't initialize jansi AnsiConsole, falling back to default: " + e);
				}
			}
			if (reader == null) {
				reader = new ConsoleReader();
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to start console reader", e);
		}

		reader.addCompletor(new JLineDSSCompletor());

		setPromptBase(null);

		executive = Executive.getInstance();
		executive.createDefaultDSSItems();

	        System.out.println("Welcome to DSS. For assistance press "
	                + completionKeys + " or type \"help\" then hit ENTER.");

		promptLoop();

	        // make sure to reset the original shell's colors on shutdown
                ansiOut.close();
	}

	public void promptLoop() {
		String line;
		try {
			while ((line = reader.readLine(shellPrompt)) != null)
				if (!line.equals(""))
					executeCommand(line);
		} catch (IOException e) {
			throw new IllegalStateException("JLine error", e);
		}
	}

	public void setPromptBase(String base) {
		if (reader.getTerminal().isANSISupported()) {
			ANSIBuffer ansi = new ANSIBuffer();
			if (base == null || base.equals("")) {
				shellPrompt = ansi.yellow(promptString + " ").toString();
			} else {
				shellPrompt = ansi.cyan(base).yellow(" " + promptString + " ").toString();
			}
		} else {
			if (base == null || base.equals("")) {
				shellPrompt = promptString + " ";
			} else {
				shellPrompt = base + " " + promptString + " ";
			}
		}
	}

	public void executeCommand(String line) {
		String clsName, objName, base;

		executive.setCommand(line);

		if (DSS.activeDSSObject == null) {
			base = "";
		} else {
			DSSObject obj = DSS.activeDSSObject;
			clsName = obj.getDSSClassName();
			objName = obj.getName();
			base = String.format("%s.%s", clsName, objName);
		}
		setPromptBase(base);
	}

	public String getShellPrompt() {
		return shellPrompt;
	}

	public static void setPromptString(String promptString) {
		JLineShell.promptString = promptString;
	}


	private static boolean isPresent(final String className, final ClassLoader classLoader) {
		try {
			return classLoader.loadClass(className) != null;
		}
		catch (final Throwable t) {
			// Class or one of its dependencies is not present...
			return false;
		}
	}

	private ConsoleReader createAnsiWindowsReader() throws Exception {
		// Get decorated OutputStream that parses ANSI-codes
	        ansiOut = (PrintStream) ClassUtils.getClass(JLineShell.class.getClassLoader(),
	                        ANSI_CONSOLE_CLASSNAME).getMethod("out").invoke(null);

	        final WindowsTerminal ansiTerminal = new WindowsTerminal() {
	            @Override
	            public boolean isANSISupported() {
	                return true;
	            }
	        };
	        ansiTerminal.initializeTerminal();

	        return new ConsoleReader(new FileInputStream(FileDescriptor.in),
	                new PrintWriter(new OutputStreamWriter(ansiOut,
	                // default to Cp850 encoding for Windows console output
	                		System.getProperty(
	                                "jline.WindowsTerminal.output.encoding",
	                                "Cp850"))), null, ansiTerminal);
	}

}

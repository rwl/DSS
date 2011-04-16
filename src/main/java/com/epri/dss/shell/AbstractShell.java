package com.epri.dss.shell;

import java.io.IOException;

import jline.ANSIBuffer;
import jline.ConsoleReader;

public abstract class AbstractShell implements Shell {
	
	public static String promptString = ">";

	protected String shellPrompt = promptString + " ";
	
	protected ConsoleReader reader;
	
	public void run() {
		
		try {
			reader = new ConsoleReader();
		} catch (IOException e) {
			throw new IllegalStateException("Failure starting console reader", e);
		}
		
		setPromptBase(null);
		
		promptLoop();
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
	
	public abstract void executeCommand(String line);
	
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

	public String getShellPrompt() {
		return shellPrompt;
	}

	public static void setPromptString(String promptString) {
		AbstractShell.promptString = promptString;
	}
}

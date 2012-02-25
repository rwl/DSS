package com.ncond.dss.shell.jline;

public interface Shell {
	
	void run();

	void promptLoop();
	
	void executeCommand(String line);
	
	void setPromptBase(String base);
	
	String getShellPrompt();

}

package com.ncond.dss.shell;

public interface Shell {

	void promptLoop();

	void executeCommand(String line);

	void setPromptBase(String base);

	String getShellPrompt();

}

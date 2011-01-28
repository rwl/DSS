package com.epri.dss.shared;

public interface CommandList {

	int getNumCommands();

	boolean isAbbrevAllowed();

	void setAbbrevAllowed(boolean abbrevAllowed);

	void addCommand(String cmd);

	int getCommand(String Cmd);

	String get(int i);

}

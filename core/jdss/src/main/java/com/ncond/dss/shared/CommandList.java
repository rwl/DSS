package com.ncond.dss.shared;

public interface CommandList {

	int getNumCommands();

	boolean isAbbrevAllowed();

	void setAbbrevAllowed(boolean abbrevAllowed);

	void addCommand(String cmd);

	int getCommand(String cmd);

	String get(int i);

}

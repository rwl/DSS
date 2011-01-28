package com.epri.dss.shared;

public interface CommandList {

	public int getNumCommands();

	public boolean isAbbrevAllowed();

	public void setAbbrevAllowed(boolean abbrevAllowed);

	public void addCommand(String cmd);

	public int getCommand(String Cmd);

	public String get(int i);

}

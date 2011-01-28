package com.epri.dss.executive;

public interface Executive {

	public String getLastError();

	public int getErrorResult();

	public String getCommand();

	public void setCommand(String Value);

	public void setRecorderOn(boolean Value);

	public boolean isRecorderOn();

	public void createDefaultDSSItems();

	public void writeToRecorderFile(String S);

	public void clear();

}

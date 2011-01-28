package com.epri.dss.executive;

public interface Executive {

	String getLastError();

	int getErrorResult();

	String getCommand();

	void setCommand(String Value);

	void setRecorderOn(boolean Value);

	boolean isRecorderOn();

	void createDefaultDSSItems();

	void writeToRecorderFile(String S);

	void clear();

}

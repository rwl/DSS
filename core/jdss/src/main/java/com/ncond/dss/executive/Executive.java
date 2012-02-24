package com.ncond.dss.executive;

public interface Executive {

	String getLastError();

	int getErrorResult();

	String getCommand();

	void setCommand(String value);

	void setRecorderOn(boolean value);

	boolean isRecorderOn();

	void createDefaultDSSItems();

	void writeToRecorderFile(String s);

	void clear();

}

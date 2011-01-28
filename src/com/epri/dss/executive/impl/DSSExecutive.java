package com.epri.dss.executive.impl;

import java.io.PrintStream;

import com.epri.dss.executive.Executive;

public class DSSExecutive implements Executive {

	private String RecorderOn;
	private PrintStream RecorderFile;

	public DSSExecutive() {
		// TODO Auto-generated constructor stub
	}

	public String getLastError() {
		return null;
	}

	public int getErrorResult() {
		return 0;
	}

	public String getCommand() {
		return null;
	}

	public void setCommand(String Value) {

	}

	public void setRecorderOn(boolean Value) {

	}

	public boolean isRecorderOn() {
		return false;
	}

	public void createDefaultDSSItems() {

	}

	public void writeToRecorderFile(String S) {

	}

	public void clear() {

	}

}

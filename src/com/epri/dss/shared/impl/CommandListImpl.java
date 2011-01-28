package com.epri.dss.shared.impl;

import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.HashList;

public class CommandListImpl implements CommandList {

	private HashList CommandList;
	private boolean AbbrevAllowed;

	public CommandListImpl(String[] Commands) {
		// TODO Auto-generated constructor stub
	}

	public int getNumCommands() {
		return 0;
	}

	public boolean isAbbrevAllowed() {
		return AbbrevAllowed;
	}

	public void setAbbrevAllowed(boolean abbrevAllowed) {
		AbbrevAllowed = abbrevAllowed;
	}

	public void addCommand(String cmd) {

	}

	public int getCommand(String Cmd) {
		return 0;
	}

	public String get(int i) {
		return null;
	}

}

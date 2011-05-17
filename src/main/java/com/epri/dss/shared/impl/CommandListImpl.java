package com.epri.dss.shared.impl;

import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.HashList;

public class CommandListImpl implements CommandList {

	private HashList CommandList;
	private boolean AbbrevAllowed;

	public CommandListImpl(String[] Commands) {
		super();
		this.CommandList = new HashListImpl(Commands.length);

		for (int i = 0; i < Commands.length; i++)
			this.CommandList.add(Commands[i]);

		AbbrevAllowed = true;
	}

	public void addCommand(String cmd) {
		CommandList.add(cmd);
	}

	public int getCommand(String Cmd) {
		int Result = CommandList.find(Cmd);
		/* If no match found on whole command, check for abbrev */
		/* This routine will generally be faster if one enters the whole command */
		if (Result == -1)
			if (AbbrevAllowed)
				Result = CommandList.findAbbrev(Cmd);

		return Result;
	}

	public String get(int i) {
		return CommandList.get(i);
	}

	public int getNumCommands() {
		return CommandList.listSize();
	}

	public boolean isAbbrevAllowed() {
		return AbbrevAllowed;
	}

	public void setAbbrevAllowed(boolean abbrevAllowed) {
		AbbrevAllowed = abbrevAllowed;
	}

}

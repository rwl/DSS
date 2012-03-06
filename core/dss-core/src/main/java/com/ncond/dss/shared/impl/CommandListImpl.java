package com.ncond.dss.shared.impl;

import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.HashList;

public class CommandListImpl implements CommandList {

	private HashList commandList;
	private boolean abbrevAllowed;

	public CommandListImpl(String[] commands) {
		this(commands, true);
	}

	public CommandListImpl(String[] commands, boolean abbrevAllowed) {
		super();
		commandList = new HashListImpl(commands.length);

		for (int i = 0; i < commands.length; i++)
			commandList.add(commands[i]);

		this.abbrevAllowed = abbrevAllowed;
	}

	@Override
	public void addCommand(String cmd) {
		commandList.add(cmd);
	}

	@Override
	public int getCommand(String cmd) {
		int result = commandList.find(cmd);
		/* If no match found on whole command, check for abbrev */
		/* This routine will generally be faster if one enters the whole command */
		if (result == -1)
			if (abbrevAllowed)
				result = commandList.findAbbrev(cmd);

		return result;
	}

	@Override
	public String get(int i) {
		return commandList.get(i);
	}

	@Override
	public int getNumCommands() {
		return commandList.listSize();
	}

	@Override
	public boolean isAbbrevAllowed() {
		return abbrevAllowed;
	}

	@Override
	public void setAbbrevAllowed(boolean allowed) {
		abbrevAllowed = allowed;
	}

}

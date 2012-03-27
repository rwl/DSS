package com.ncond.dss.shared.test;

import com.ncond.dss.shared.CommandList;

import junit.framework.TestCase;

public class CommandListTest extends TestCase {

	String[] months = new String[] {"January", "Febuary", "March",
			"April", "May", "June", "July", "August",
			"September", "October", "November", "December"};

	CommandList commands;

	protected void setUp() throws Exception {
		commands = new CommandList(months, true);
		super.setUp();
	}

	public void testGetCommand() {
		int idx;

		idx = commands.getCommand("may");
		assertEquals(4, idx);

		idx = commands.getCommand("dec");
		assertEquals(11, idx);

		idx = commands.getCommand("foo");
		assertEquals(-1, idx);

		idx = commands.getCommand("");
		assertEquals(-1, idx);
	}

	public void testGet() {
		String cmd;

		cmd = commands.get(1);
		assertEquals("febuary", cmd);

		cmd = commands.get(16);
		assertEquals("", cmd);
	}

	public void testGetNumCommands() {
		assertEquals(12, commands.getNumCommands());
	}

}

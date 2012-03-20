package com.ncond.dss.shell.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.shell.DSSCompletor;

import junit.framework.TestCase;

public class CompletorTest extends TestCase {

	private DSSCompletor completor;

	private static final Set<String> commands = getCommandSet();
	private static final Set<String> classNames = getClassNameSet();

	private String buffer;
	private int idx, cursor;
	private List<String> candidates;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		completor = new DSSCompletor();
		candidates = new ArrayList<String>();
	}

	public void testCompleteBlank() {
		buffer = "";
		cursor = 0;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(commands.size() +
				//classNames.size() +  // too many
				1, candidates.size());
		assertEquals(0, idx);
	}

	public void testCompleteCommand() {
		buffer = "comm";
		cursor = 4;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(1, candidates.size());
		assertEquals("command", candidates.get(0));
		assertEquals(0, idx);
	}

	private static Set<String> getClassNameSet() {
		Executive.getInstance();
		Set<String> names = new HashSet<String>();
		for (String name : DSS.classNames.toArray())
			if (name != null) names.add(name);
		return names;
	}

	private static Set<String> getCommandSet() {
		Set<String> cmds = new HashSet<String>();
		Collections.addAll(cmds, ExecCommands.execCommand);
		return cmds;
	}

}

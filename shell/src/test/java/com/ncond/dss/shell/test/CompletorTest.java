package com.ncond.dss.shell.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.GrowthShape;
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

	private static Set<String> getClassNameSet() {
		Executive.getInstance().createDefaultDSSItems();
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

	public void testCompleteBlank() {
		buffer = "";
		cursor = 0;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(commands.size() -
				7 +  // step control commands (named with leading "_")
				//classNames.size() +  // too many
				1, candidates.size());
		assertEquals(0, idx);
	}

	public void testCompleteCommand() {
		buffer = "comm";
		cursor = 4;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(0, idx);
		assertEquals(1, candidates.size());
		assertEquals("command.", candidates.get(0));
	}

	public void testCompleteClassName() {
		buffer = "Trans";
		cursor = 4;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(0, idx);
		assertEquals(1, candidates.size());
		assertEquals("transformer.", candidates.get(0));
	}

	public void testCompleteClassNameDot() {
		buffer = "growthshape";
		cursor = 11;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(11, idx);
		assertEquals(1, candidates.size());
		assertEquals(".", candidates.get(0));
	}

	public void testCompleteObjName() {
		buffer = "growthshape.";
		cursor = 12;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(12, idx);
		assertEquals(1, candidates.size());
		assertEquals("default.", candidates.get(0));
	}

	public void testCompleteObjName2() {
		buffer = "spectrum.default";
		cursor = 16;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(9, idx);
		assertEquals(3, candidates.size());
		assertEquals("defaultgen", candidates.get(0));
		assertEquals("defaultload", candidates.get(1));
		assertEquals("defaultvsource", candidates.get(2));
	}

	public void testCompleteObjNameDot() {
		buffer = "spectrum.defaultload";
		cursor = 20;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(20, idx);
		assertEquals(1, candidates.size());
		assertEquals(".", candidates.get(0));
	}

	public void testCompleteParamName() {
		buffer = "growthshape.default.";
		cursor = 20;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(20, idx);
		assertEquals(DSS.growthShapeClass.getPropertyName().length,
				candidates.size());
	}

	public void testCompleteParamNameHalf() {
		buffer = "spectrum.defaultload.Nu";
		cursor = 23;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(21, idx);
		assertEquals(1, candidates.size());
		assertEquals("numHarm=", candidates.get(0));
	}


	public void testCompleteParamNameDot() {
		buffer = "growthshape.default.year";
		cursor = 24;
		idx = completor.complete(buffer, cursor, candidates);

		assertEquals(24, idx);
		assertEquals(1, candidates.size());
		assertEquals("=", candidates.get(0));
	}

}

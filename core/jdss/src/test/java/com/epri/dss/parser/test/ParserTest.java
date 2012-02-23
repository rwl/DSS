package com.epri.dss.parser.test;

import com.epri.dss.parser.impl.Parser;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	private String cmdString;
	private Parser parser;
	private String param;

	protected void setUp() throws Exception {
		super.setUp();
		parser = Parser.getAuxInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetNextParam() {

	}

	public void testParseAsBusName() {
		cmdString = "BusName.1.2.3";
		parser.setCmdString(cmdString);
		param = parser.getNextParam();

		int[] nodes = new int[3];
//		String name = parser.parseAsBusName(nodes.length, nodes);

//		System.out.println("BusName: " + param + name + nodes.toString());
	}

	public void testParseAsVector() {
//		fail("Not yet implemented");
	}

	public void testParseAsMatrix() {
//		fail("Not yet implemented");
	}

	public void testParseAsSymMatrix() {
//		fail("Not yet implemented");
	}

	public void testMakeString() {
//		fail("Not yet implemented");
	}

	public void testMakeInteger() {
//		fail("Not yet implemented");
	}

	public void testMakeDouble() {
//		fail("Not yet implemented");
	}

	public void testGetRemainder() {
//		fail("Not yet implemented");
	}

}

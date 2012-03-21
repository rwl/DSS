package com.ncond.dss.parser.test;

import junit.framework.TestCase;

import com.ncond.dss.parser.Parser;

public class ParserTest extends TestCase {

	private static final double delta = 1e-6;
	private String cmdString;
	private Parser parser;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		parser = Parser.getAuxInstance();
	}

	public void testGetNextParam() {
		String param;

		cmdString = "like=lin2 length=1.7";
		parser.setCmdBuffer(cmdString);

		param = parser.getNextParam();
		assertEquals("like", param);

		param = parser.getNextParam();
		assertEquals("length", param);

		param = parser.getNextParam();
		assertEquals("", param);
	}

	public void testParseAsBusName() {
		int[] n, nodes;
		String name;

		cmdString = "BusName ";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		nodes = new int[3];
		n = new int[1];
		name = parser.parseAsBusName(n, nodes);

		assertEquals("BusName", name);
		assertEquals(0, n[0]);
		assertEquals(0, nodes[0]);
		assertEquals(0, nodes[1]);
		assertEquals(0, nodes[2]);
	}

	public void testParseAsBusName3() {
		int[] n, nodes;
		String name;

		cmdString = "BusName.1.2.3 ";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		nodes = new int[4];
		n = new int[1];
		name = parser.parseAsBusName(n, nodes);

		assertEquals("BusName", name);
		assertEquals(3, n[0]);
		assertEquals(1, nodes[0]);
		assertEquals(2, nodes[1]);
		assertEquals(3, nodes[2]);
		assertEquals(0, nodes[3]);
	}

	public void testParseAsVector() {
		int n;
		double[] vector;

		cmdString = "[115, 6.6, 22]";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		vector = new double[4];
		n = parser.parseAsVector(3, vector);

		assertEquals(3, n);
		assertEquals(115, vector[0], delta);
		assertEquals(6.6, vector[1], delta);
		assertEquals(22, vector[2], delta);
		assertEquals(0, vector[3], delta);
	}

	public void testParseAsVector2() {
		int n;
		double[] vector;

		cmdString = "{1.2 .3 .3 2}";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		vector = new double[4];
		n = parser.parseAsVector(4, vector);

		assertEquals(4, n);
		assertEquals(1.2, vector[0], delta);
		assertEquals(0.3, vector[1], delta);
		assertEquals(0.3, vector[2], delta);
		assertEquals(2.0, vector[3], delta);
	}

	public void testParseAsMatrix() {
		int order;
		double[] matrix;

		cmdString = "{1.2, .3 | 1.2, 3}";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		matrix = new double[5];
		order = parser.parseAsMatrix(2, matrix);  // returns matrix in column order

		assertEquals(2, order);
		assertEquals(1.2, matrix[0], delta);
		assertEquals(0.3, matrix[2], delta);
		assertEquals(0.0, matrix[4], delta);
	}

	public void testParseAsMatrix2() {
		int order;
		double[] matrix;

		cmdString = "[1.2 .4 .3 | .3 1.2 3 | .3 .3 1.2]";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		matrix = new double[10];
		order = parser.parseAsMatrix(3, matrix);  // returns matrix in column order

		assertEquals(3, order);
		assertEquals(1.2, matrix[0], delta);
		assertEquals(0.4, matrix[3], delta);
		assertEquals(1.2, matrix[8], delta);
		assertEquals(0.0, matrix[9], delta);
	}

	public void testParseAsSymMatrix() {
		int order;
		double[] matrix;

		cmdString = "[1.2 | .4 1.2 | .3 .3 1.2]";
		parser.setCmdBuffer(cmdString);
		parser.getNextParam();

		matrix = new double[10];
		order = parser.parseAsSymMatrix(3, matrix);  // returns matrix in column order
		/*
		 * 1.2 0.4 0.3
		 * 0.4 1.2 0.3
		 * 0.3 0.3 1.2
		 */
		assertEquals(3, order);
		assertEquals(1.2, matrix[0], delta);
		assertEquals(0.4, matrix[3], delta);
		assertEquals(1.2, matrix[8], delta);
		assertEquals(0.0, matrix[9], delta);
	}

	public void testMakeString() {
		String value;

		cmdString = "bus1=b142, like=lin2";
		parser.setCmdBuffer(cmdString);

		parser.getNextParam();
		value = parser.makeString();
		assertEquals("b142", value);

		parser.getNextParam();
		value = parser.makeString();
		assertEquals("lin2", value);
	}

	public void testMakeInteger() {
		int value;

		cmdString = "6  year=5, 6.0";
		parser.setCmdBuffer(cmdString);

		parser.getNextParam();
		value = parser.makeInteger();
		assertEquals(6, value);

		parser.getNextParam();
		value = parser.makeInteger();
		assertEquals(5, value);

		parser.getNextParam();
		value = parser.makeInteger();
		assertEquals(6, value);

	}

	public void testMakeDouble() {
		double value;

		cmdString = "6,  mult=10.0 -5e06";
		parser.setCmdBuffer(cmdString);

		parser.getNextParam();
		value = parser.makeDouble();
		assertEquals(6.0, value, delta);

		parser.getNextParam();
		value = parser.makeDouble();
		assertEquals(10.0, value, delta);

		parser.getNextParam();
		value = parser.makeInteger();
		assertEquals(-5e6, value, delta);
	}

	public void testGetRemainder() {
		String remainder;

		cmdString = "new line.Lin2 Bs1 Bs2";
		parser.setCmdBuffer(cmdString);

		parser.getNextParam();
		parser.getNextParam();

		remainder = parser.getRemainder();

		assertEquals("Bs1 Bs2 ", remainder);
	}

}

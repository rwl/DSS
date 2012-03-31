package com.ncond.dss.executive.test;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.Executive;

import junit.framework.TestCase;

public class ExecOptionsTest extends TestCase {

	private static final double delta = 1e-3;

	private Executive exec;

	protected void setUp() throws Exception {
		super.setUp();
		exec = Executive.getInstance();
		exec.createDefaultDSSItems();
	}

	protected void process(String cmd) {
		exec.setCommand(cmd);
	}

	protected void createCircuit() {
		exec.setCommand("new circuit.ckt1");
	}

	public void testSetVoltageBases() {
		createCircuit();
		Circuit ckt = DSS.activeCircuit;

		process("set voltagebases=[12.47, 4.16]");

		assertEquals(3, ckt.getLegalVoltageBases().length);
		assertEquals(12.47, ckt.getLegalVoltageBase(0));
		assertEquals( 4.16, ckt.getLegalVoltageBase(1));
		assertEquals( 0.00, ckt.getLegalVoltageBase(2));

		process("set voltagebases=\".208, .480, 12.47, 24.9, 34.5, 115.0, 230.0\"");

		assertEquals(8, ckt.getLegalVoltageBases().length);
		assertEquals(0.208, ckt.getLegalVoltageBase(0), delta);
		assertEquals(230.0, ckt.getLegalVoltageBase(6), delta);
		assertEquals( 0.00, ckt.getLegalVoltageBase(7), delta);
	}

}

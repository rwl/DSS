package com.ncond.dss.executive.test;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.conversion.VSourceObj;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.ExecOptions;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.LoadShape;
import com.ncond.dss.general.LoadShapeObj;

import junit.framework.TestCase;

public class ExecutiveTest extends TestCase {

	private static final double delta = 1e-6;

	public void testGetInstance() {
		Executive exec = Executive.getInstance();

		assertNotNull(ExecCommands.commandList);
		assertNotNull(ExecOptions.optionList);

		assertTrue(DSS.DSSClassList.size() > 0);
		assertEquals(DSS.DSSClassList.size(), DSS.classNames.listSize());

		assertNotNull(DSS.circuits);
		assertEquals(0, DSS.numCircuits);
		assertNull(DSS.activeCircuit);

		assertEquals("", ExecCommands.lastCmdLine);
		assertEquals("", ExecCommands.redirFile);

		assertFalse(exec.isRecorderOn());
	}

	public void testCreateDefaultDSSItems() {
		int n;
		LoadShapeObj loadShape;

		Executive exec = Executive.getInstance();

		exec.createDefaultDSSItems();

		n = DSS.loadShapeClass.getElementCount();
		assertTrue(n > 0);

		loadShape = (LoadShapeObj) DSS.loadShapeClass.find("default");
		assertNotNull(loadShape);
		assertEquals(24, loadShape.getNumPoints());

		n = DSS.growthShapeClass.getElementCount();
		assertTrue(n > 0);

		n = DSS.spectrumClass.getElementCount();
		assertTrue(n > 0);

		n = DSS.TCC_CurveClass.getElementCount();
		assertTrue(n > 0);
	}

	public void testSetCommandNewCircuit() {
		String cmd;

		Executive exec = Executive.getInstance();

		cmd = "new circuit.ckt6";
		exec.setCommand(cmd);

		assertEquals(1, DSS.numCircuits);
		assertNotNull(DSS.activeCircuit);
		assertEquals(1, DSS.numCircuits);
		assertEquals("ckt6", DSS.activeCircuit.getName());

		cmd = "clear";
		exec.setCommand(cmd);

		assertEquals(0, DSS.numCircuits);
		assertNull(DSS.activeCircuit);
		assertEquals(0, DSS.numCircuits);
	}

	public void testSetCommandEdit() {
		String cmd;
		VSourceObj vsource;

		Executive exec = Executive.getInstance();

		cmd = "new circuit.ckt13";
		exec.setCommand(cmd);

		vsource = (VSourceObj) DSSClassDefs.getDSSClass("VSource").find("source");

		cmd = "~ basekv=115 pu=1.00 phases=3 bus1=Bus1";
		exec.setCommand(cmd);

		assertEquals(115, vsource.getKVBase(), delta);
		assertEquals(1.0, vsource.getPerUnit(), delta);
		assertEquals(3, vsource.getNPhases());
		assertEquals("bus1", vsource.getBus(0));

		cmd = "more angle=30";
		exec.setCommand(cmd);

		assertEquals(30.0, vsource.getAngle(), delta);

		cmd = "m MVAsc3=20000 MVAsc1=21000";
		exec.setCommand(cmd);

		assertEquals(2e4, vsource.getMVAsc3(), delta);
		assertEquals(2.1e4, vsource.getMVAsc1(), delta);
	}

}

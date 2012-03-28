package com.ncond.dss.executive.test;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.LoadShapeObj;

import junit.framework.TestCase;

public class ExecutiveTest extends TestCase {

	/*public void testGetInstance() {
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
	}*/

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

	public void testSetCommand() {
		Executive exec = Executive.getInstance();
		exec.setCommand("");

		boolean formsSave = DSS.noFormsAllowed;
		DSS.noFormsAllowed = true;  // suppress messages

		exec.setCommand("foo");

		DSS.noFormsAllowed = formsSave;
	}

	public void testGetCommand() {
		Executive exec = Executive.getInstance();
		String cmd = "clear";
		exec.setCommand(cmd);
		assertEquals(cmd, exec.getCommand());
	}

	public void testClear() {
		Executive exec = Executive.getInstance();

		exec.setCommand("new circuit.ckt1");
		exec.clear();

		assertEquals(0, DSS.numCircuits);
		assertNull(DSS.activeCircuit);

		assertTrue(DSS.loadShapeClass.getElementCount() > 0);
	}

	/*public void testSetRecorderOn() {
		Executive exec = Executive.getInstance();

		exec.setRecorderOn(true);

		assertTrue(DSS.globalResult.endsWith("DSSRecorder.dss"));
		assertTrue(exec.isRecorderOn());

		exec.setRecorderOn(false);
		assertFalse(exec.isRecorderOn());
	}*/

}

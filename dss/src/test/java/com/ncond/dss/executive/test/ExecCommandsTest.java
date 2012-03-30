package com.ncond.dss.executive.test;

import java.io.File;

import com.google.common.io.Files;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.conversion.VSourceObj;
import com.ncond.dss.delivery.TransformerObj;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.DSSObject;

import junit.framework.TestCase;

public class ExecCommandsTest extends TestCase {

	private static final double delta = 1e-6;

	private static final String COMPILE_SCRIPT = "4Bus-GrdYD-Bal.dss";

	private Executive exec;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		exec = Executive.getInstance();
		exec.createDefaultDSSItems();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		exec.clear();
	}

	protected void createCircuit() {
		exec.setCommand("new circuit.ckt1");
	}

	protected void process(String cmd) {
		ExecCommands.processCommand(cmd);
	}

	public void testNewCircuit() {
		process("new circuit.ckt6");

		assertEquals(1, DSS.numCircuits);
		assertNotNull(DSS.activeCircuit);
		assertEquals(1, DSS.numCircuits);
		assertEquals("ckt6", DSS.activeCircuit.getName());


		process("clear");

		assertEquals(0, DSS.numCircuits);
		assertNull(DSS.activeCircuit);
	}

	public void testComment() {
		process("!new circuit.ckt6");
		assertEquals(0, DSS.numCircuits);

		process("//new circuit.ckt6");
		assertEquals(0, DSS.numCircuits);
	}

	public void testEdit() {
		VSourceObj vsource;

		createCircuit();
		vsource = (VSourceObj) DSSClassDefs.getDSSClass("VSource").find("source");

		process("edit object=VSource.source basekv=220 bus1=b9");
		assertEquals(220.0, vsource.getKVBase(), delta);
		assertEquals("b9", vsource.getBus(0));

		process("VSource.source.bus1=b6");
		assertEquals("b6", vsource.getBus(0));

		DSS.setObject("VSource.source");
		process("pu=1.1");
		assertEquals(1.1, vsource.getPerUnit(), delta);
	}

	public void testMore() {
		VSourceObj vsource;

		createCircuit();

		vsource = (VSourceObj) DSSClassDefs.getDSSClass("VSource").find("source");


		process("~ basekv=115 pu=1.00 phases=3 bus1=Bus1");

		assertEquals(115, vsource.getKVBase(), delta);
		assertEquals(1.0, vsource.getPerUnit(), delta);
		assertEquals(3, vsource.getNPhases());
		assertEquals("bus1", vsource.getBus(0));


		process("more angle=30");

		assertEquals(30.0, vsource.getAngle(), delta);


		process("m MVAsc3=20000 MVAsc1=21000");

		assertEquals(2e4, vsource.getMVAsc3(), delta);
		assertEquals(2.1e4, vsource.getMVAsc1(), delta);
	}

	public void testSelect() {
		DSSObject obj;
		CktElement elem;

		createCircuit();

		process("select growthshape.default");

		obj = DSS.activeDSSObject;
		assertEquals("default", obj.getName());

		process("new Line.line1 phases=3 bus1=b1 bus2=b2");

		elem = (CktElement) DSS.activeDSSObject;
		assertEquals("line1", elem.getName());
		assertEquals(0, elem.getActiveTerminalIdx());


		process("select object=Line.line1 terminal=2");

		assertEquals(1, elem.getActiveTerminalIdx());
	}

	/*public void testSave() {
		String path;
		File dir;

		dir = Files.createTempDir();
		path = dir.getAbsolutePath();

		createCircuit();

		process("save class=meters dir=" + path);
		assertTrue(new File(dir, "meters.dss").exists());

		process("save class=circuit");

		process("save class=voltages");

		process("save class=VSource");
	}*/

	public void testSolve() {
		createCircuit();
		process("solve");
		assertTrue(DSS.activeCircuit.isSolved());
	}

	public void testCompile() {
		File file;
		TransformerObj xfmr;

		file = new File(ExecutiveTest.class.getResource(COMPILE_SCRIPT).getPath());

		process("compile filename=" + file.getAbsolutePath());

		assertEquals(file.getParent(), DSS.currentDirectory);
		assertEquals(file.getParent() + DSS.SEPARATOR, DSS.dataDirectory);

		//assertTrue(DSS.activeCircuit.isSolved());

		xfmr = (TransformerObj) DSSClassDefs.getDSSClass("transformer").find("t1");
		assertNotNull(xfmr);
	}

	public void testChangeDirectory() {
		String dirName;
		boolean formsSave;
		File tempDir, tempDir2;

		tempDir = Files.createTempDir();

		/* change to valid directory */
		process("cd " + tempDir.getAbsolutePath());

		assertEquals(tempDir.getAbsolutePath(), DSS.currentDirectory);
		assertEquals(tempDir.getAbsolutePath() + DSS.SEPARATOR, DSS.dataDirectory);


		/* change to invalid directory */
		formsSave = DSS.noFormsAllowed;
		DSS.noFormsAllowed = true;  // suppress messages


		process("cd /zyxjkl");

		DSS.noFormsAllowed = formsSave;

		assertEquals(tempDir.getAbsolutePath(), DSS.currentDirectory);
		assertEquals(tempDir.getAbsolutePath() + DSS.SEPARATOR, DSS.dataDirectory);


		/* change to relative directory */
		dirName = "dssdata";
		tempDir2 = new File(tempDir, dirName);
		tempDir2.mkdir();

		process("cd " + dirName);

		assertEquals(tempDir2.getAbsolutePath(), DSS.currentDirectory);
		assertEquals(tempDir2.getAbsolutePath() + DSS.SEPARATOR, DSS.dataDirectory);
	}

}

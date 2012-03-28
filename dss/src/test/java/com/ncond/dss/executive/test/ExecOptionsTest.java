package com.ncond.dss.executive.test;

import com.ncond.dss.executive.Executive;

import junit.framework.TestCase;

public class ExecOptionsTest extends TestCase {

	private Executive exec;

	protected void setUp() throws Exception {
		super.setUp();
		exec = Executive.getInstance();
		exec.createDefaultDSSItems();
	}

	public void testDoSetCmd_NoCircuit() {

	}

	public void testDoSetCmd() {
	}


	public void testDoGetCmd() {

	}

}

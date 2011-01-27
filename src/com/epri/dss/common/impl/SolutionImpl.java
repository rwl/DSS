package com.epri.dss.common.impl;

import com.epri.dss.common.Solution;

public class SolutionImpl extends DSSClassImpl implements Solution {

	private class ControlProblem extends Exception {

	}

	/* Raised when solution aborted */
	private class SolveError extends Exception {

	}

	public SolutionImpl() {
		// TODO Auto-generated constructor stub
	}

	protected void defineProperties() {

	}

	public int edit() {
		return 0;
	}

	public int init(int handle) {
		return 0;
	}

	public int newObject(String objName) {
		return 0;
	}

}

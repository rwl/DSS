package com.ncond.dss.common;

public enum ProfilePlot {

	THREEPH (9999),  // some big number > likely no. of phases
	ALL (9998),
	ALLPRI (9997),
	LLALL (9996),
	LLPRI (9995),
	LL (9994);

	private final int phs;

	ProfilePlot(int phs) {
		this.phs = phs;
	}

	public int phs() {
		return this.phs;
	}
}

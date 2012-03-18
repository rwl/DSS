/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Terminal {

	private int numCond;
	private int activeConductor;

	protected int busRef;

	protected int[] termNodeRef;
	protected Conductor[] conductors;
	protected boolean checked;

	public Terminal(int nCond) {
		super();
		numCond = nCond;
		busRef = -1;  // signify not set
		termNodeRef = new int[numCond];
		conductors = new Conductor[numCond];
		for (int i = 0; i < numCond; i++)
			conductors[i] = new Conductor();
		activeConductor = 0;
	}

	public void setActiveConductor(int value) {
		if (value >= 0 & value < numCond) activeConductor = value;
	}

	public Conductor getConductor(int idx) {
		return conductors[idx];
	}

	public int getTermNodeRef(int idx) {
		return termNodeRef[idx];
	}

	public int[] getTermNodeRef() {
		return termNodeRef;
	}

}

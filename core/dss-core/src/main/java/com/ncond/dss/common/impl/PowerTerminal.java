package com.ncond.dss.common.impl;

import com.ncond.dss.common.Conductor;
import com.ncond.dss.common.Terminal;

public class PowerTerminal implements Terminal {

	private int numCond;
	private int activeConductor;

	protected int busRef;

	protected int[] termNodeRef;
	protected Conductor[] conductors;
	protected boolean checked;

	public PowerTerminal(int nCond) {
		super();
		numCond = nCond;
		busRef = -1;  // signify not set
		termNodeRef = new int[numCond];
		conductors = new Conductor[numCond];
		for (int i = 0; i < numCond; i++)
			conductors[i] = new ConductorImpl();
		activeConductor = 0;
	}

	public int getTermNodeRef(int idx) {
		return termNodeRef[idx];
	}

	public int getBusRef() {
		return busRef;
	}

	public void setBusRef(int value) {
		busRef = value;
	}

	public int[] getTermNodeRef() {
		return termNodeRef;
	}

	public void setTermNodeRef(int[] value) {
		termNodeRef = value;
	}

	public Conductor[] getConductors() {
		return conductors;
	}

	public void setConductors(Conductor[] value) {
		conductors = value;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean value) {
		checked = value;
	}

	public void setActiveConductor(int value) {
		if (value >= 0 & value < numCond)
			activeConductor = value;
	}

	public int getActiveConductor() {
		return activeConductor;
	}

}

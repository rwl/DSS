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

	@Override
	public Conductor getConductor(int idx) {
		return conductors[idx];
	}

	@Override
	public int getTermNodeRef(int idx) {
		return termNodeRef[idx];
	}

	@Override
	public int getBusRef() {
		return busRef;
	}

	@Override
	public void setBusRef(int value) {
		busRef = value;
	}

	@Override
	public int[] getTermNodeRef() {
		return termNodeRef;
	}

	@Override
	public void setTermNodeRef(int[] value) {
		termNodeRef = value;
	}

	@Override
	public Conductor[] getConductors() {
		return conductors;
	}

	@Override
	public void setConductors(Conductor[] value) {
		conductors = value;
	}

	@Override
	public boolean isChecked() {
		return checked;
	}

	@Override
	public void setChecked(boolean value) {
		checked = value;
	}

	@Override
	public void setActiveConductor(int value) {
		if (value >= 0 & value < numCond)
			activeConductor = value;
	}

	@Override
	public int getActiveConductor() {
		return activeConductor;
	}

}

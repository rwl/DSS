package com.epri.dss.common.impl;

import com.epri.dss.common.Conductor;
import com.epri.dss.common.Terminal;

public class PowerTerminal implements Terminal {

	private int numCond;
	private int activeConductor;

	protected int busRef;

	protected int[] termNodeRef;
	protected Conductor[] conductors;
	protected boolean checked;

	public PowerTerminal(int nCond) {
		super();
		this.numCond = nCond;
		this.busRef = -1;  // signify not set
		this.termNodeRef = new int[this.numCond];
		this.conductors = new Conductor[this.numCond];
		for (int i = 0; i < this.numCond; i++)
			conductors[i] = new ConductorImpl();
		activeConductor = 0;  // TODO Check zero based indexing
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
		if ((value >= 0) & (value < numCond))  // TODO Check zero based indexing
			activeConductor = value;
	}

	public int getActiveConductor() {
		return activeConductor;
	}
}

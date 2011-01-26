package com.epri.dss.common.impl;

import com.epri.dss.common.Terminal;

public class PowerTerminal implements Terminal {

	private int NumCond;
	private int ActiveConductor;

	public int BusRef;
	public int[] TermNodeRef;
	public ConductorImpl[] Conductors;
	public boolean Checked;

	public PowerTerminal(int Ncond) {

	}

	public void Set_ActiveConductor(int Value) {

	}

	public int Get_ActiveConductor() {
		return 0;
	}
}

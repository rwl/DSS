package com.epri.dss.common;

public class PowerTerminal {

	private int NumCond;
	private int ActiveConductor;

	public int BusRef;
	public int[] TermNodeRef;
	public Conductor[] Conductors;
	public boolean Checked;

	public PowerTerminal(int Ncond) {

	}

	public void Set_ActiveConductor(int Value) {

	}

	public int Get_ActiveConductor() {
		return 0;
	}
}

package com.epri.dss.common;

import com.epri.dss.common.impl.ConductorImpl;

public interface Terminal {

	public int BusRef = 0;
	public int[] TermNodeRef = null;
	public ConductorImpl[] Conductors = null;
	public boolean Checked = false;

	public void Set_ActiveConductor(int Value);

	public int Get_ActiveConductor();

}

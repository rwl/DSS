package com.epri.dss.common;

import java.io.PrintStream;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.general.NamedObject;
import com.epri.dss.shared.impl.CktTreeImpl;
import com.epri.dss.shared.impl.HashListImpl;
import com.epri.dss.shared.impl.PointerListImpl;

public interface Circuit extends NamedObject {

	public void Set_ActiveCktElement(DSSCktElement Value);

	public DSSCktElement Get_ActiveCktElement();

	public void Set_BusNameRedefined(boolean Value);

	public boolean Is_BusNameRedefined();

	/* Total Circuit PD Element losses */
	public double[] Get_Losses();

	public void Set_LoadMultiplier(double Value);

	public double Get_LoadMultiplier();

	public void Set_CaseName(String Value);

	public String Get_CaseName();

	public String Get_Name();

	/* Adds last DSS object created to circuit */
	public void AddCktElement(int Handle);

	/* Totalize all energymeters in the problem */
	public void TotalizeMeters();

	public boolean ComputeCapacity();

	public boolean Save(String Dir);

	public void ProcessBusDefs();

	/* Redo all Buslists, nodelists */
	public void ReProcessBusDefs();

	public void DoResetMeterZones();

	public int SetElementActive(String FullObjectName);

	public void InvalidateAllPCElements();

	public void DebugDump(PrintStream F);

	/* Access to topology from the first source */
	public CktTreeImpl GetTopology();

	public void FreeTopology();

	public AdjArray GetBusAdjacentPDLists();

	public AdjArray GetBusAdjacentPCLists();

}

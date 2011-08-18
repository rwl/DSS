package com.epri.dss.common;

import com.epri.dss.conversion.PCElement;
import com.epri.dss.shared.CktTree;

/**
 * Feeders get created from energy meters if Radial is set to yes and meter
 * zones are already computed.  If Radial=Yes and the MeterZones are reset,
 * then the feeders are redefined.  If Radial is subsequently set to NO or a
 * solution mode is used that doesn't utilize feeders, the get currents
 * routines will not do anything.
 *
 * Feeders cannot be re-enabled unless the EnergyMeter object allows them
 * to be.
 *
 * Feeders are not saved. This is implicit with the EnergyMeter saving.
 *
 */
public interface FeederObj extends PCElement {

	boolean isSynched();

	void setSynched(boolean isSynched);

	void initializeFeeder(CktTree<CktElement> branchList);

	void setCktElementFeederFlags(boolean value);

}

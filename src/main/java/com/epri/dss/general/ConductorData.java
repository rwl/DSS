package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface ConductorData extends DSSClass {

	static final String LineUnitsHelp = "{mi|kft|km|m|Ft|in|cm|mm} Default=none.";

	void setNumConductorClassProps(int numConductorClassProps);

	int getNumConductorClassProps();

}

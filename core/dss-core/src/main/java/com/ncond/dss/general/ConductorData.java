package com.ncond.dss.general;

import com.ncond.dss.common.DSSClass;

public interface ConductorData extends DSSClass {

	static final String LineUnitsHelp = "{mi|kft|km|m|Ft|in|cm|mm} Default=none.";

	void setNumConductorClassProps(int numConductorClassProps);

	int getNumConductorClassProps();

}

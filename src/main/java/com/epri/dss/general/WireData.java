package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface WireData extends ConductorData {

	static final int NumPropsThisClass = 0;  // because they were all moved to ConductorData

	String getCode();

	void setCode(String Value);

}

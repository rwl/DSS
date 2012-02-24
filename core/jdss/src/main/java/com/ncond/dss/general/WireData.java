package com.ncond.dss.general;

public interface WireData extends ConductorData {

	static final int NumPropsThisClass = 0;  // because they were all moved to ConductorData

	String getCode();

	void setCode(String value);

}

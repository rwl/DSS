package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface WireData extends DSSClass {
	
	static final int NumPropsThisClass = 10;
	
	String getCode();
	
	void setCode(String Value);

}

package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface TCC_Curve extends DSSClass {

	static final int NumPropsThisClass = 3;

	String getCode();

	void setCode(String value);

}

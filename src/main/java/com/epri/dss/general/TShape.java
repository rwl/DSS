package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface TShape extends DSSClass {

	static final int NumPropsThisClass = 12;

	String getCode();

	void setCode(String Value);

}

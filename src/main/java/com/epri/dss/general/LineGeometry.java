package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface LineGeometry extends DSSClass {

	static final int NumPropsThisClass = 16;

	String getCode();

	void setCode(String value);

}

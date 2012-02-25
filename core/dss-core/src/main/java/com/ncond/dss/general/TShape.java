package com.ncond.dss.general;

import com.ncond.dss.common.DSSClass;

public interface TShape extends DSSClass {

	static final int NumPropsThisClass = 12;

	String getCode();

	void setCode(String value);

	void TOPExport(String objName);

}

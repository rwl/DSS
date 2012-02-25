package com.ncond.dss.general;

import com.ncond.dss.common.DSSClass;

public interface LineCode extends DSSClass {

	static final int NumPropsThisClass = 22;

	/**
	 * Returns active line code string.
	 */
	String getCode();

	/**
	 * Sets the active line code.
	 */
	void setCode(String value);

}

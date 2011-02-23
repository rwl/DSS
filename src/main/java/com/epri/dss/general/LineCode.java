package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface LineCode extends DSSClass {
	
	static final int NumPropsThisClass = 22;

	/**
	 * Returns active line code string.
	 */
	String getCode();
	
	/**
	 * Sets the active linecode.
	 */
	void setCode(String Value);

}

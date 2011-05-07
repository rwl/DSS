package com.epri.dss.general;

public interface CNData extends CableData {

	static final int NumPropsThisClass = 4;

	/**
	 * Returns active line code string.
	 */
	String getCode();

	/**
	 * Sets the  active CNData.
	 */
	void setCode(String Value);

}

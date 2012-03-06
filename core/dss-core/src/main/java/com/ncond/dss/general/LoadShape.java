package com.ncond.dss.general;

import com.ncond.dss.common.DSSClass;

public interface LoadShape extends DSSClass {

	static final int NumPropsThisClass = 18;

	/**
	 * Returns active LoadShape string.
	 */
	String getCode();

	/**
	 * Sets the active LoadShape.
	 */
	void setCode(String value);

	@Override
	int edit();

	@Override
	int init(int handle);

	@Override
	int newObject(String objName);

	/**
	 * Find an obj of this class by name.
	 */
	@Override
	DSSObject find(String objName);

	void TOPExport(String objName);

}

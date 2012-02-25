package com.ncond.dss.common;

/**
 * Collection of all solution objects.
 */
public interface Solution extends DSSClass {

	static final int NORMALSOLVE = 0;
	static final int NEWTONSOLVE = 1;

	int edit();

	int init(int handle);

	int newObject(String objName);

}

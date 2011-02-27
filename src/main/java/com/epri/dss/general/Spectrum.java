package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

/* Superstructure for all Spectrum objects */
public interface Spectrum extends DSSClass {
	
	static final int NumPropsThisClass = 5;

	/* Returns active spectrum code string */
	String getCode();

	/* Sets the active Spectrum */
	void setCode(String Value);

	int edit();

	int newObject(String ObjName);

}

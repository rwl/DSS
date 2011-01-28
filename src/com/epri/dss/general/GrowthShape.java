package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface GrowthShape extends DSSClass {

	/* Returns active GrowthShape string */
	String getCode();

	/* Sets the active GrowthShape */
	void setCode(String Value);

	int edit();

	int init(int Handle);

	int newObject(String ObjName);

}

package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface GrowthShape extends DSSClass {

	/* Returns active GrowthShape string */
	public String getCode();

	/* Sets the active GrowthShape */
	public void setCode(String Value);

	public int edit();

	public int init(int Handle);

	public int newObject(String ObjName);

}

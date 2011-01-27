package com.epri.dss.general;

import com.epri.dss.common.DSSClass;

public interface LoadShape extends DSSClass {

	/* Returns active LoadShape string */
	public String getCode();

	/* Sets the active LoadShape */
	public void setCode(String Value);

	public int edit();

	public int init(int Handle);

	public int newObject(String ObjName);

	/* Find an obj of this class by name */
	public Object find(String ObjName);

	public void tOPExport(String ObjName);

}

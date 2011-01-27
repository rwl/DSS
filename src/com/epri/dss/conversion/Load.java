package com.epri.dss.conversion;

/* Superstructure for all Line objects */
public interface Load extends PCClass {

	public int edit();

	public int init(int Handle);

	public int newObject(String ObjName);

}

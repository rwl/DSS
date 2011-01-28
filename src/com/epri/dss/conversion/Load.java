package com.epri.dss.conversion;

/* Superstructure for all Line objects */
public interface Load extends PCClass {

	int edit();

	int init(int Handle);

	int newObject(String ObjName);

}

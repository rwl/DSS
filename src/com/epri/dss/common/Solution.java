package com.epri.dss.common;

public interface Solution extends DSSClass {

	public static final int NORMALSOLVE = 0;
	public static final int NEWTONSOLVE = 1;

	public int edit();

	public int init(int handle);

	public int newObject(String objName);

}

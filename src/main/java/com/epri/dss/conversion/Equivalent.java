package com.epri.dss.conversion;

public interface Equivalent extends PCClass {
	
	static final int NumPropsThisClass = 16;

	void interpretAllBuses(String S);
	
}

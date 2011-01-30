package com.epri.dss.conversion;

public interface Storage extends PCClass {
	
	String[] getRegisterNames();

	void setRegisterNames(String[] registerNames);
	
	void resetRegistersAll();
	
	void sampleAll();
	
	void updateAll();

}

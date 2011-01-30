package com.epri.dss.conversion;

public interface Generator extends PCClass {
	
	String[] getRegisterNames();

	void setRegisterNames(String[] registerNames);
	
	String getCode();
	
	void setCode(String Value);
	
	void resetRegistersAll();
	
	void sampleAll();

}

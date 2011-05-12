package com.epri.dss.conversion;

public interface PVSystem extends PCClass {

	static final int NumPVSystemRegisters = 5;  // Number of energy meter registers
	static final int NumPVSystemVariables = 4;  // No state variables that need integrating.

	void setRegisterNames(String[] registerNames);

	String[] getRegisterNames();

	void resetRegistersAll();

	void sampleAll();

	void updateAll();

}

package com.epri.dss.general;

public interface LineSpacingObj extends DSSObject {
	
	void setNWires(int Value);
	
	int getNWires();
	
	double getXcoord(int i);
	
	double getYcoord(int i);
	
	int getNPhases();

	int getUnits();

}

package com.epri.dss.delivery;

public interface CapacitorObj extends PDElement {
	
	int getStates(int Idx);
	
	void setStates(int Idx, int Value);
	
	/* 1=kvar, 2=Cuf, 3=Cmatrix */
	void setNumSteps(int Value);
	
	int getNumSteps();

	int getConnection();

	void setConnection(int connection);
	
	boolean addStep();
	
	boolean subtractStep();
	
	int availableSteps();

	double getTotalkvar();

	double getKvrating();

}

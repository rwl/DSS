package com.epri.dss.general;

public interface LineSpacingObj extends DSSObject {

	void setNWires(int value);

	int getNWires();

	double getXCoord(int i);

	double getYCoord(int i);

	int getNPhases();

	int getUnits();

	// FIXME Private members in OpenDSS.

	int getNConds();

	void setNConds(int nConds);

	double[] getX();

	void setX(double[] x);

	double[] getY();

	void setY(double[] y);

	boolean isDataChanged();

	void setDataChanged(boolean dataChanged);

	void setNPhases(int nPhases);

	void setUnits(int units);

}

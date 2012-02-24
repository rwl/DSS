package com.ncond.dss.general;

import com.ncond.dss.general.impl.ConductorChoice;
import com.ncond.dss.general.impl.LineGeometryObjImpl.LineGeometryProblem;
import com.ncond.dss.shared.CMatrix;

/**
 * The LineGeometry object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the code property in the LineGeometry
 * class. This sets the active LineGeometry object to be the one referenced by
 * the code property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public interface LineGeometryObj extends DSSObject {

	void changeLineConstantsType(ConductorChoice newPhaseChoice);

	void setNConds(int value);

	void setNPhases(int value);

	int getNPhases();

	void setActiveCond(int value);

	int getActiveCond();

	CMatrix getYcMatrix(double f, double length, int units);

	CMatrix getZMatrix(double f, double length, int units);

	double getRhoEarth();

	void setRhoEarth(double value);

	int getNConds();

	double getXCoord(int i);

	double getYCoord(int i);

	String getConductorName(int i);

	ConductorDataObj getConductorData(int i);

	int getNWires();

	/**
	 * Called from a Line object that has its own Spacing and Wires input
	 * automatically sets reduce=y if the spacing has more wires than phases
	 */
	void loadSpacingAndWires(LineSpacingObj spc, ConductorDataObj[] wires);

	double getNormAmps();

	void setNormAmps(double normAmps);

	double getEmergAmps();

	void setEmergAmps(double emergAmps);

	ConductorChoice getPhaseChoice();

	// FIXME Private method in OpenDSS
	void updateLineGeometryData(double f) throws LineGeometryProblem;


	// FIXME Private members in OpenDSS.

	String[] getCondName();

	void setCondName(String[] condName);

	ConductorDataObj[] getConductorData();

	void setConductorData(ConductorDataObj[] wireData);

	double[] getX();

	void setX(double[] x);

	double[] getY();

	void setY(double[] y);

	int[] getUnits();

	void setUnits(int[] units);

	int getLastUnit();

	void setLastUnit(int lastUnit);

	boolean isDataChanged();

	void setDataChanged(boolean dataChanged);

	boolean isReduce();

	void setReduce(boolean reduce);

	String getSpacingType();

	void setSpacingType(String spacingType);

	LineConstants getLineData();

	void setLineData(LineConstants lineData);

	void setPhaseChoice(ConductorChoice phaseChoice);

}

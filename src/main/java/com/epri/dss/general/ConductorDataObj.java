package com.epri.dss.general;

/**
 * The ConductorData object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 *
 * The values are set by the normal New and Edit procedures for any DSS object.
 *
 * The values are retrieved by setting the Code Property in the ConductorData Class.
 * This sets the active ConductorData object to be the one referenced by the Code Property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public interface ConductorDataObj extends DSSObject {

	double getNormAmps();

	void setNormAmps(double normAmps);

	double getEmergAmps();

	void setEmergAmps(double emergAmps);

	double getRDC();

	double getR60();

	double getGMR60();

	double getRadius();

	int getGMRUnits();

	int getResistanceUnits();

	int getRadiusUnits();

	// FIXME Private members in OpenDSS.

	void setRDC(double rDC);

	void setR60(double r60);

	void setGMR60(double gMR60);

	void setRadius(double radius);

	void setGMRUnits(int gMRUnits);

	void setResistanceUnits(int resistanceUnits);

	void setRadiusUnits(int radiusUnits);

}

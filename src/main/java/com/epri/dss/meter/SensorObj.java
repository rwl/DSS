package com.epri.dss.meter;

/**
 * Sensor compares voltages and currents. Power quantities are converted to
 * current quantities based on rated kVBase, or actual voltage if voltage
 * measurement specified.
 *
 */
public interface SensorObj extends MeterElement {

	/* Connection code */
	void setConn(int Value);
	
	int getConn();
	
	void setAction(String Value);
	
	double getWLSCurrentError();
	
	double getWLSVoltageError();

	double[] getSensorKW();

	double[] getSensorKVar();

	double getkVBase();

	int getDeltaDirection();

	double getPctError();

	void setPctError(double pctError);

	double getWeight();

	void setWeight(double weight);
	
	void resetIt();
	
	/* Saves present buffer to file */
	void save();

}

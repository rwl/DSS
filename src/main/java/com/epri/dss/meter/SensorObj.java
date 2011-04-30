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

	// FIXME Private method in OpenDSS
	int limitToPlusMinusOne(int i);

	// FIXME Private method in OpenDSS
	void clearSensor();


	// FIXME Private members in OpenDSS

	boolean isValidSensor();

	void setValidSensor(boolean validSensor);

	double getVbase();

	void setVbase(double vbase);

	boolean isVspecified();

	void setVspecified(boolean vspecified);

	boolean isIspecified();

	void setIspecified(boolean ispecified);

	boolean isPspecified();

	void setPspecified(boolean pspecified);

	boolean isQspecified();

	void setQspecified(boolean qspecified);

	boolean isClearSpecified();

	void setClearSpecified(boolean clearSpecified);

	void setSensorKW(double[] sensorKW);

	void setSensorKVar(double[] sensorKVar);

	void setkVBase(double kVBase);

	void setDeltaDirection(int deltaDirection);

}

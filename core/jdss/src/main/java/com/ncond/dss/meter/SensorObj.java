package com.ncond.dss.meter;

/**
 * Sensor compares voltages and currents. Power quantities are converted to
 * current quantities based on rated kVBase, or actual voltage if voltage
 * measurement specified.
 *
 */
public interface SensorObj extends MeterElement {

	/** Connection code */
	void setConn(int value);

	int getConn();

	void setAction(String value);

	double getWLSCurrentError();

	double getWLSVoltageError();

	double[] getSensorKW();

	double[] getSensorKVAr();

	double getKVBase();

	int getDeltaDirection();

	double getPctError();

	void setPctError(double pctError);

	double getWeight();

	void setWeight(double weight);

	void resetIt();

	/** Saves present buffer to file. */
	void save();

	// FIXME Private method in OpenDSS
	int limitToPlusMinusOne(int i);

	// FIXME Private method in OpenDSS
	void clearSensor();


	// FIXME Private members in OpenDSS

	boolean isValidSensor();

	void setValidSensor(boolean validSensor);

	double getVBase();

	void setVBase(double vbase);

	boolean isVSpecified();

	void setVSpecified(boolean vspecified);

	boolean isISpecified();

	void setISpecified(boolean ispecified);

	boolean isPSpecified();

	void setPSpecified(boolean pspecified);

	boolean isQSpecified();

	void setQSpecified(boolean qspecified);

	boolean isClearSpecified();

	void setClearSpecified(boolean clearSpecified);

	void setSensorKW(double[] sensorKW);

	void setSensorKVAr(double[] sensorKVar);

	void setKVBase(double kVBase);

	void setDeltaDirection(int deltaDirection);

}

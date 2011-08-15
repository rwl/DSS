package com.epri.dss.meter.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.Sensor;
import com.epri.dss.meter.SensorObj;

public class SensorObjImpl extends MeterElementImpl implements SensorObj {

	private boolean validSensor;
	private double[] sensorKW;
	private double[] sensorKVAr;
	private double KVBase;  // value specified
	private double VBase;   // in volts

	private int conn;

	private boolean VSpecified, ISpecified, PSpecified, QSpecified;

	private boolean clearSpecified;
	private int deltaDirection;

	protected double pctError, weight;

	public SensorObjImpl(DSSClassImpl parClass, String sensorName) {
		super(parClass);
		setName(sensorName.toLowerCase());

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		sensorKW   = null;
		sensorKVAr = null;

		KVBase = 12.47;  // default 3-phase voltage
		weight = 1.0;
		pctError = 1.0;

		setConn(0);  // wye

		clearSensor();

		objType = parClass.getDSSClassType();  // SENSOR_ELEMENT;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals globals = DSSGlobals.getInstance();

		validSensor = false;
		int devIndex = Utilities.getCktElementIndex(elementName);
		if (devIndex >= 0) {  // sensored element must already exist
			meteredElement = globals.getActiveCircuit().getCktElements().get(devIndex);

			if (meteredTerminal > meteredElement.getNTerms()) {  // TODO Check zero based indexing
				globals.doErrorMsg("Sensor: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Respecify terminal no.", 665);
			} else {
				setNPhases( meteredElement.getNPhases() );
				setNConds( meteredElement.getNConds() );

				// sets name of i-th terminal's connected bus in Sensor's bus list
				// this value will be used to set the nodeRef array (see takeSample)
				setBus(1, meteredElement.getBus(meteredTerminal));

				clearSensor();

				validSensor = true;

				allocateSensorObjArrays();
				zeroSensorArrays();
				recalcVbase();
			}
		} else {
			meteredElement = null;   // element not found
			globals.doErrorMsg("Sensor: \"" + getName() + "\"", "Circuit Element \""+ elementName + "\" not found.",
					" Element must be defined previously.", 666);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (meteredElement != null) {
			setBus(1, meteredElement.getBus(meteredTerminal));
			setNPhases( meteredElement.getNPhases() );
			setNConds( meteredElement.getNConds() );
			clearSensor();
			validSensor = true;
			allocateSensorObjArrays();
			zeroSensorArrays();
			recalcVbase();
		}
		super.makePosSequence();
	}

	private void recalcVbase() {
		switch (conn) {
		case 0:
			if (nPhases == 1) {
				VBase = KVBase * 1000.0;
			} else {
				VBase = KVBase * 1000.0 / DSSGlobals.SQRT3;
			}
			break;
		case 1:
			VBase = KVBase * 1000.0;
			break;
		}
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as nil and they will be ignored
	}

	public void resetIt() {
		clearSensor();
	}

	/**
	 * For delta connections or line-line voltages.
	 */
	private int rotatePhases(int j) {
		int result = j + deltaDirection;

		// make sure result is within limits
		if (nPhases > 2) {
			// assumes 2 phase delta is open delta
			if (result > nPhases) result = 1;
			if (result < 1) result = nPhases;
		} else {
			if (result < 1) result = 3;  // for 2-phase delta, next phase will be 3rd phase
		}

		return result;
	}

	@Override
	public void takeSample() {
		if (!(validSensor && isEnabled()))
			return;

		meteredElement.getCurrents(calculatedCurrent);
		computeVTerminal();
		switch (conn) {
		case 1:
			for (int i = 0; i < nPhases; i++)
				calculatedVoltage[i] = VTerminal[i].subtract( VTerminal[rotatePhases(i)] );
			break;
		default:
			for (int i = 0; i < nPhases; i++)
				calculatedVoltage[i] = VTerminal[i];
			break;
		}
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Return the WLS Error for currents.
	 * Get square error and weight it.
	 */
	public double getWLSCurrentError() {
		double kVA;
		int i;

		double result = 0.0;

		/* Convert P and Q specification to currents */
		if (PSpecified) {  // compute currents assuming vbase
			if (QSpecified) {
				for (i = 0; i < nPhases; i++) {
					kVA = new Complex(sensorKW[i], sensorKVAr[i]).abs();
					sensorCurrent[i] = kVA * 1000.0 / VBase;
				}
			} else {  // no Q just use P
				for (i = 0; i < nPhases; i++)
					sensorCurrent[i] = sensorKW[i] * 1000.0 / VBase;
			}
			ISpecified = true;  // overrides current specification
		}

		if (ISpecified)
			for (i = 0; i < nPhases; i++)
				result = result + Math.pow(calculatedCurrent[i].getReal(), 2) + Math.pow(calculatedCurrent[i].getImaginary(), 2) - Math.pow(sensorCurrent[i], 2);

		result = result * weight;

		return result;
	}

	/**
	 * Get square error and weight it.
	 */
	public double getWLSVoltageError() {
		int i;
		double result = 0.0;

		if (VSpecified)
			for (i = 0; i < nPhases; i++)
				result = result + Math.pow(calculatedVoltage[i].getReal(), 2) + Math.pow(calculatedVoltage[i].getImaginary(), 2) - Math.pow(sensorVoltage[i], 2);

		result = result * weight;

		return result;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete)
			f.println();
	}

	// FIXME Private method in OpenDSS
	public void clearSensor() {
		VSpecified = false;
		ISpecified = false;
		PSpecified = false;
		QSpecified = false;
		clearSpecified = false;
	}

	private void allocateSensorObjArrays() {
		sensorKW   = (double[]) Utilities.resizeArray(sensorKW, nPhases);
		sensorKVAr = (double[]) Utilities.resizeArray(sensorKVAr, nPhases);
		allocateSensorArrays();
	}

	private void zeroSensorArrays() {
		for (int i = 0; i < nPhases; i++) {
			sensorCurrent[i] = 0.0;
			sensorVoltage[i] = 0.0;
			sensorKW[i]      = 0.0;
			sensorKVAr[i]    = 0.0;
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");   // 'element';
		setPropertyValue(1, "1");  // 'terminal';
		setPropertyValue(2, "12.47");  // 'kVBase';
		setPropertyValue(3, "No");  // must be set to yes to clear before setting quantities
		setPropertyValue(4, "[7.2, 7.2, 7.2]");
		setPropertyValue(5, "[0.0, 0.0, 0.0]");  // currents
		setPropertyValue(6, "[0.0, 0.0, 0.0]");  // P kW
		setPropertyValue(7, "[0.0, 0.0, 0.0]");  // Q kvar
		setPropertyValue(8, "wye");
		setPropertyValue(9, "1");
		setPropertyValue(10, "1");  // %Error
		setPropertyValue(11, "1");  // %Error
		setPropertyValue(12, "");   // Action

		super.initPropertyValues(Sensor.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public int limitToPlusMinusOne(int i) {
		if (i >= 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Saves present buffer to file.
	 */
	public void save() {

	}

	/**
	 * Connection code.
	 */
	public void setConn(int value) {
		conn = value;
		recalcVbase();
	}

	public void setAction(String value) {

	}

	public int getConn() {
		return conn;
	}

	public double[] getSensorKW() {
		return sensorKW;
	}

	public double[] getSensorKVAr() {
		return sensorKVAr;
	}

	public double getKVBase() {
		return KVBase;
	}

	public int getDeltaDirection() {
		return deltaDirection;
	}

	public double getPctError() {
		return pctError;
	}

	public void setPctError(double pct) {
		this.pctError = pct;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double w) {
		weight = w;
	}


	// FIXME Private members in OpenDSS

	public boolean isValidSensor() {
		return validSensor;
	}

	public void setValidSensor(boolean valid) {
		validSensor = valid;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vbase) {
		VBase = vbase;
	}

	public boolean isVSpecified() {
		return VSpecified;
	}

	public void setVSpecified(boolean specified) {
		VSpecified = specified;
	}

	public boolean isISpecified() {
		return ISpecified;
	}

	public void setISpecified(boolean specified) {
		ISpecified = specified;
	}

	public boolean isPSpecified() {
		return PSpecified;
	}

	public void setPSpecified(boolean specified) {
		PSpecified = specified;
	}

	public boolean isQSpecified() {
		return QSpecified;
	}

	public void setQSpecified(boolean specified) {
		QSpecified = specified;
	}

	public boolean isClearSpecified() {
		return clearSpecified;
	}

	public void setClearSpecified(boolean clear) {
		clearSpecified = clear;
	}

	public void setSensorKW(double[] kw) {
		sensorKW = kw;
	}

	public void setSensorKVAr(double[] kvar) {
		sensorKVAr = kvar;
	}

	public void setKVBase(double base) {
		this.KVBase = base;
	}

	public void setDeltaDirection(int direction) {
		deltaDirection = direction;
	}

}

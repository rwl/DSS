package com.epri.dss.meter.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.Sensor;
import com.epri.dss.meter.SensorObj;

public class SensorObjImpl extends MeterElementImpl implements SensorObj {

	private boolean ValidSensor;
	private double[] SensorKW;
	private double[] SensorKVar;
	private double kVBase; // value specified
	private double Vbase; // in volts

	private int Conn;

	private boolean Vspecified, Ispecified, Pspecified, Qspecified;

	private boolean ClearSpecified;
	private int DeltaDirection;

	protected double pctError, Weight;

	public SensorObjImpl(DSSClassImpl ParClass, String SensorName) {
		super(ParClass);
		setName(SensorName.toLowerCase());

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // this forces allocation of terminals and conductors in base class

		this.SensorKW   = null;
		this.SensorKVar = null;

		this.kVBase = 12.47;  // default 3-phase voltage
		this.Weight = 1.0;
		this.pctError = 1.0;

		this.Conn = 0;  // Wye

		clearSensor();

		this.DSSObjType = ParClass.getDSSClassType();  // SENSOR_ELEMENT;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		ValidSensor = false;
		int DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {  // Sensored element must already exist
			MeteredElement = Globals.getActiveCircuit().getCktElements().get(DevIndex);

			if (MeteredTerminal > MeteredElement.getNTerms()) {  // TODO Check zero based indexing
				Globals.doErrorMsg("Sensor: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Respecify terminal no.", 665);
			} else {
				nPhases = MeteredElement.getNPhases();
				nConds  = MeteredElement.getNConds();

				// Sets name of i-th terminal's connected bus in Sensor's buslist
				// This value will be used to set the NodeRef array (see TakeSample)
				setBus(1, MeteredElement.getBus(MeteredTerminal));

				clearSensor();

				ValidSensor = true;

				allocateSensorObjArrays();
				zeroSensorArrays();
				recalcVbase();
			}
		} else {
			MeteredElement = null;   // element not found
			Globals.doErrorMsg("Sensor: \"" + getName() + "\"", "Circuit Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 666);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (MeteredElement != null) {
			setBus(1, MeteredElement.getBus(MeteredTerminal));
			nPhases = MeteredElement.getNPhases();
			nConds  = MeteredElement.getNConds();
			clearSensor();
			ValidSensor = true;
			allocateSensorObjArrays();
			zeroSensorArrays();
			recalcVbase();
		}
		super.makePosSequence();
	}

	private void recalcVbase() {
		switch (Conn) {
		case 0:
			if (nPhases == 1) {
				Vbase = kVBase * 1000.0;
			} else {
				Vbase = kVBase * 1000.0 / DSSGlobals.SQRT3;
			}
		case 1:
			Vbase = kVBase * 1000.0;
		}
	}

	@Override
	public void calcYPrim() {
		// Leave YPrims as nil and they will be ignored.
		// Yprim is zeroed when created. Leave it as is.
	}

	public void resetIt() {
		clearSensor();
	}

	/**
	 * For delta connections or line-line voltages.
	 */
	private int rotatePhases(int j) {
		int Result = j + DeltaDirection;

		// Make sure result is within limits
		if (nPhases > 2) {
			// Assumes 2 phase delta is open delta
			if (Result > nPhases) Result = 1;
			if (Result < 1) Result = nPhases;
		} else {
			if (Result < 1) Result = 3;  // For 2-phase delta, next phase will be 3rd phase
		}

		return Result;
	}

	@Override
	public void takeSample() {
		if (!(ValidSensor && isEnabled()))
			return;

		MeteredElement.getCurrents(CalculatedCurrent);
		computeVterminal();
		switch (Conn) {
		case 1:
			for (int i = 0; i < nPhases; i++)
				CalculatedVoltage[i] = Vterminal[i].subtract( Vterminal[rotatePhases(i)] );
		default:
			for (int i = 0; i < nPhases; i++)
				CalculatedVoltage[i] = Vterminal[i];
		}
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	/**
	 * Return the WLS Error for currents.
	 * Get square error and weight it.
	 */
	public double getWLSCurrentError() {
		double kVA;
		int i;

		double Result = 0.0;

		/* Convert P and Q specification to currents */
		if (Pspecified) {  // compute currents assuming vbase
			if (Qspecified) {
				for (i = 0; i < nPhases; i++) {
					kVA = new Complex(SensorKW[i], SensorKVar[i]).abs();
					SensorCurrent[i] = kVA * 1000.0 / Vbase;
				}
			} else {  // No Q just use P
				for (i = 0; i < nPhases; i++)
					SensorCurrent[i] = SensorKW[i] * 1000.0 / Vbase;
			}
			Ispecified = true;  // Overrides current specification
		}

		if (Ispecified)
			for (i = 0; i < nPhases; i++)
				Result = Result + Math.pow(CalculatedCurrent[i].getReal(), 2) + Math.pow(CalculatedCurrent[i].getImaginary(), 2) - Math.pow(SensorCurrent[i], 2);

		Result = Result * Weight;

		return Result;
	}

	/**
	 * Get square error and weight it.
	 */
	public double getWLSVoltageError() {
		int i;
		double Result = 0.0;

		if (Vspecified)
			for (i = 0; i < nPhases; i++)
				Result = Result + Math.pow(CalculatedVoltage[i].getReal(), 2) + Math.pow(CalculatedVoltage[i].getImaginary(), 2) - Math.pow(SensorVoltage[i], 2);

		Result = Result * Weight;

		return Result;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete)
			F.println();
	}

	private void clearSensor() {
		Vspecified = false;
		Ispecified = false;
		Pspecified = false;
		Qspecified = false;
		ClearSpecified = false;
	}

	private void allocateSensorObjArrays() {
		SensorKW   = (double[]) Utilities.resizeArray(SensorKW, nPhases);
		SensorKVar = (double[]) Utilities.resizeArray(SensorKVar, nPhases);
		allocateSensorArrays();
	}

	private void zeroSensorArrays() {
		for (int i = 0; i < nPhases; i++) {
			SensorCurrent[i] = 0.0;
			SensorVoltage[i] = 0.0;
			SensorKW[i]      = 0.0;
			SensorKVar[i]    = 0.0;
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(0, ""); // 'element';
		setPropertyValue(1, "1"); // 'terminal';
		setPropertyValue(2, "12.47"); // 'kVBase';
		setPropertyValue(3, "No"); // Must be set to yes to clear before setting quantities
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

	private int limitToPlusMinusOne(int i) {
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
	public void setConn(int Value) {
		Conn = Value;
		recalcVbase();
	}

	public void setAction(String Value) {

	}

	public int getConn() {
		return Conn;
	}

	public double[] getSensorKW() {
		return SensorKW;
	}

	public double[] getSensorKVar() {
		return SensorKVar;
	}

	public double getkVBase() {
		return kVBase;
	}

	public int getDeltaDirection() {
		return DeltaDirection;
	}

	public double getPctError() {
		return pctError;
	}

	public void setPctError(double pctError) {
		this.pctError = pctError;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}


	// FIXME Private members in OpenDSS

	public boolean isValidSensor() {
		return ValidSensor;
	}

	public void setValidSensor(boolean validSensor) {
		ValidSensor = validSensor;
	}

	public double getVbase() {
		return Vbase;
	}

	public void setVbase(double vbase) {
		Vbase = vbase;
	}

	public boolean isVspecified() {
		return Vspecified;
	}

	public void setVspecified(boolean vspecified) {
		Vspecified = vspecified;
	}

	public boolean isIspecified() {
		return Ispecified;
	}

	public void setIspecified(boolean ispecified) {
		Ispecified = ispecified;
	}

	public boolean isPspecified() {
		return Pspecified;
	}

	public void setPspecified(boolean pspecified) {
		Pspecified = pspecified;
	}

	public boolean isQspecified() {
		return Qspecified;
	}

	public void setQspecified(boolean qspecified) {
		Qspecified = qspecified;
	}

	public boolean isClearSpecified() {
		return ClearSpecified;
	}

	public void setClearSpecified(boolean clearSpecified) {
		ClearSpecified = clearSpecified;
	}

	public void setSensorKW(double[] sensorKW) {
		SensorKW = sensorKW;
	}

	public void setSensorKVar(double[] sensorKVar) {
		SensorKVar = sensorKVar;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public void setDeltaDirection(int deltaDirection) {
		DeltaDirection = deltaDirection;
	}

}

package com.ncond.dss.conversion.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.CktElementImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.meter.MeterElement;

public abstract class PCElementImpl extends CktElementImpl implements PCElement {

	private boolean ITerminalUpdated;

	protected String spectrum;
	protected SpectrumObj spectrumObj;
	/** Upline energy meter */
	private MeterElement meterObj;
	/** Upline sensor for this element */
	private MeterElement sensorObj;

	private Complex[] injCurrent;

	public PCElementImpl(DSSClass ParClass) {
		super(ParClass);
		spectrum = "default";
		spectrumObj = null;  // have to allocate later because not guaranteed there will be one now
		sensorObj   = null;
		meterObj    = null;
		injCurrent  = null;
		ITerminalUpdated = false;

		objType = DSSClassDefs.PC_ELEMENT;
	}

	/**
	 * Add injection currents into system currents array.
	 */
	@Override
	public int injCurrents() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		for (int i = 0; i < YOrder; i++)
			sol.setCurrent(nodeRef[i], sol.getCurrent(nodeRef[i]).add( injCurrent[i] ));

		return 0;
	}

	/**
	 * Get present values of terminal.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		DSS.doErrorMsg("PCElement.InjCurrents", ("Improper call to getInjCurrents for element: " + getName() + "."),
			"Called PCElement class virtual function instead of actual.", 640);
	}

	/**
	 * This is called only if we need to compute the terminal currents from the inj currents.
	 *
	 * Such as for harmonic model.
	 */
	protected void getTerminalCurrents(Complex[] curr) {
		if (getITerminalUpdated()) {  // just copy ITerminal unless ITerminal=curr
			if (curr != getITerminal())
				for (int i = 0; i < YOrder; i++)
					curr[i] = getITerminal()[i];
		} else {
			YPrim.vMult(curr, getVTerminal());
			for (int i = 0; i < YOrder; i++)
				curr[i] = curr[i].add( getInjCurrent()[i].negate() );
			setITerminalUpdated(true);
		}
		ITerminalSolutionCount = DSS.activeCircuit.getSolution().getSolutionCount();
	}

	/**
	 * Get present values of terminal.
	 *
	 * Gets total currents going into a devices terminals.
	 */
	@Override
	public void getCurrents(Complex[] curr) {
		try {
			SolutionObj sol = DSS.activeCircuit.getSolution();

			if (isEnabled()) {

				if ( sol.lastSolutionWasDirect() && (! (sol.isDynamicModel() || sol.isHarmonicModel()) ) ) {

					// take a short cut and get currents from YPrim only
					// for case where model is entirely in Y matrix

					calcYPrimContribution(curr);

				} else {

					getTerminalCurrents(curr);
				}

			} else {  // not enabled
				for (int i = 0; i < YOrder; i++)
					curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			DSS.doErrorMsg(("getCurrents for element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element.", 641);
		}
	}

	@Override
	public void calcYPrimContribution(Complex[] curr) {
		computeVTerminal();
		// apply these voltages to Yprim
		YPrim.vMult(curr, VTerminal);
	}

	/**
	 * For harmonics mode
	 */
	@Override
	public void initHarmonics() {
		// by default do nothing in the base class
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		propertyValue[arrayOffset + 1] = spectrum;

		super.initPropertyValues(arrayOffset + 1);
	}

	/**
	 * For dynamics mode and control devices.
	 */
	@Override
	public void initStateVars() {
		// by default do nothing
	}

	@Override
	public void integrateStates() {
		// by default do nothing
	}

	@Override
	public void getAllVariables(double[] states) {
		/* Do nothing */
	}

	@Override
	public int numVariables() {
		return 0;
	}

	@Override
	public String variableName(int i) {
		/* Do nothing */
		return "";
	}

	/**
	 * Search through variable name list and return index if found.
	 * Compare up to length of S.
	 */
	@Override
	public int lookupVariable(String s) {
		int result = -1;   // returns -1 for error not found
		int testLength = s.length();
		for (int i = 0; i < numVariables(); i++) {
			if (variableName(i).substring(0, testLength).equalsIgnoreCase(s)) {
				result = i;
				break;
			}
		}
		return result;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		if (complete) {
			f.println("! VARIABLES");
			for (int i = 0; i < numVariables(); i++)
				f.println("! " + i + ": " + variableName(i) + " = " + String.format("%-.5g", getVariable(i)));
		}
	}

	@Override
	public double getVariable(int i) {
		/* Do nothing here -- up to override function */
		return -9999.99;
	}

	@Override
	public void setVariable(int i, double value) {
		/* Do nothing */
	}

	@Override
	public void computeITerminal() {
		Circuit ckt = DSS.activeCircuit;

		if (ITerminalSolutionCount != ckt.getSolution().getSolutionCount()) {
			getCurrents(ITerminal);
			ITerminalSolutionCount = ckt.getSolution().getSolutionCount();
		}
	}

	@Override
	public void zeroInjCurrent() {
		for (int i = 0; i < YOrder; i++)
			injCurrent[i] = Complex.ZERO;
	}

	@Override
	public void setITerminalUpdated(boolean value) {
		ITerminalUpdated = value;
		if (value)
			ITerminalSolutionCount = DSS.activeCircuit.getSolution().getSolutionCount();
	}

	@Override
	public boolean getITerminalUpdated() {
		return ITerminalUpdated;
	}

	@Override
	public Complex[] getInjCurrent() {
		return injCurrent;
	}

	@Override
	public String getSpectrum() {
		return spectrum;
	}

	@Override
	public void setSpectrum(String value) {
		spectrum = value;
	}

	/** Upline sensor for this element */
	@Override
	public SpectrumObj getSpectrumObj() {
		return spectrumObj;
	}

	@Override
	public void setSpectrumObj(SpectrumObj spectrum) {
		spectrumObj = spectrum;
	}

	/** Upline energy meter */
	@Override
	public MeterElement getMeterObj() {
		return meterObj;
	}

	@Override
	public void setMeterObj(MeterElement meter) {
		meterObj = meter;
	}

	@Override
	public MeterElement getSensorObj() {
		return sensorObj;
	}

	@Override
	public void setSensorObj(MeterElement sensor) {
		sensorObj = sensor;
	}

	@Override
	public void setInjCurrent(Complex[] current) {
		injCurrent = current;
	}

}

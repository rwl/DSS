package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.meter.MeterElement;
import com.epri.dss.general.SpectrumObj;

public abstract class PCElementImpl extends DSSCktElement implements PCElement {

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
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		for (int i = 0; i < YOrder; i++)
			sol.getCurrents()[nodeRef[i]] = sol.getCurrents()[nodeRef[i]].add( injCurrent[i] );

		return 0;
	}

	/**
	 * Get present values of terminal.
	 */
	public void getInjCurrents(Complex[] curr) {
		DSSGlobals.doErrorMsg("PCElement.InjCurrents", ("Improper call to getInjCurrents for element: " + getName() + "."),
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
			YPrim.MVMult(curr, getVTerminal());
			for (int i = 0; i < YOrder; i++)
				curr[i] = curr[i].add( getInjCurrent()[i].negate() );
			setITerminalUpdated(true);
		}
		ITerminalSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount();
	}

	/**
	 * Get present values of terminal.
	 *
	 * Gets total currents going into a devices terminals.
	 */
	public void getCurrents(Complex[] curr) {
		try {
			SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

			if (isEnabled()) {

				if ( sol.lastSolutionWasDirect() && (! (sol.isDynamicModel() || sol.isHarmonicModel())) ) {

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
			DSSGlobals.doErrorMsg(("getCurrents for element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element.", 641);
		}
	}

	public void calcYPrimContribution(Complex[] curr) {
		computeVTerminal();
		// apply these voltages to Yprim
		YPrim.MVMult(curr, VTerminal);
	}

	/**
	 * For harmonics mode
	 */
	public void initHarmonics() {
		// by default do nothing in the base class
	}

	public void initPropertyValues(int arrayOffset) {
		propertyValue[arrayOffset + 1] = spectrum;

		super.initPropertyValues(arrayOffset + 1);
	}

	/**
	 * For dynamics mode and control devices.
	 */
	public void initStateVars() {
		// by default do nothing
	}

	public void integrateStates() {
		// by default do nothing
	}

	public void getAllVariables(double[] states) {
		/* Do nothing */
	}

	public int numVariables() {
		return 0;
	}

	public String variableName(int i) {
		/* Do nothing */
		return "";
	}

	/**
	 * Search through variable name list and return index if found.
	 * Compare up to length of S.
	 */
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

	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		if (complete) {
			f.println("! VARIABLES");
			for (int i = 0; i < numVariables(); i++)
				f.println("! " + i + ": " + variableName(i) + " = " + String.format("%-.5g", getVariable(i)));
		}
	}

	public double getVariable(int i) {
		/* Do nothing here -- up to override function */
		return -9999.99;
	}

	public void setVariable(int i, double value) {
		/* Do nothing */
	}

	public void computeITerminal() {
		Circuit ckt = DSSGlobals.activeCircuit;

		if (ITerminalSolutionCount != ckt.getSolution().getSolutionCount()) {
			getCurrents(ITerminal);
			ITerminalSolutionCount = ckt.getSolution().getSolutionCount();
		}
	}

	public void zeroInjCurrent() {
		for (int i = 0; i < YOrder; i++)
			injCurrent[i] = Complex.ZERO;
	}

	public void setITerminalUpdated(boolean value) {
		ITerminalUpdated = value;
		if (value)
			ITerminalSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount();
	}

	public boolean getITerminalUpdated() {
		return ITerminalUpdated;
	}

	public Complex[] getInjCurrent() {
		return injCurrent;
	}

	public String getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(String value) {
		spectrum = value;
	}

	/** Upline sensor for this element */
	public SpectrumObj getSpectrumObj() {
		return spectrumObj;
	}

	public void setSpectrumObj(SpectrumObj spectrum) {
		spectrumObj = spectrum;
	}

	/** Upline energy meter */
	public MeterElement getMeterObj() {
		return meterObj;
	}

	public void setMeterObj(MeterElement meter) {
		meterObj = meter;
	}

	public MeterElement getSensorObj() {
		return sensorObj;
	}

	public void setSensorObj(MeterElement sensor) {
		sensorObj = sensor;
	}

	public void setInjCurrent(Complex[] current) {
		injCurrent = current;
	}

}

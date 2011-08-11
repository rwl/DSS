package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

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

	private boolean IterminalUpdated;

	protected String Spectrum;
	protected SpectrumObj SpectrumObj;
	/** Upline energy meter */
	private MeterElement MeterObj;
	/** Upline sensor for this element */
	private MeterElement SensorObj;

	private Complex[] InjCurrent;

	public PCElementImpl(DSSClass ParClass) {
		super(ParClass);
		this.Spectrum = "default";
		this.SpectrumObj = null;  // have to allocate later because not guaranteed there will be one now
		this.SensorObj   = null;
		this.MeterObj    = null;
		this.InjCurrent  = null;
		this.IterminalUpdated = false;

		this.DSSObjType = DSSClassDefs.PC_ELEMENT;
	}

	/**
	 * Add injection currents into system currents array.
	 */
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		for (int i = 0; i < Yorder; i++)
			sol.getCurrents()[NodeRef[i]] = sol.getCurrents()[NodeRef[i]].add( InjCurrent[i] );

		return 0;
	}

	/**
	 * Get present values of terminal.
	 */
	public void getInjCurrents(Complex[] Curr) {
		DSSGlobals.getInstance().doErrorMsg("PCElement.InjCurrents", ("Improper call to getInjCurrents for element: " + getName() + "."),
			"Called PCElement class virtual function instead of actual.", 640);
	}

	/**
	 * This is called only if we need to compute the terminal currents from the inj currents.
	 *
	 * Such as for harmonic model.
	 */
	protected void getTerminalCurrents(Complex[] Curr) {
		if (getITerminalUpdated()) {  // just copy ITerminal unless ITerminal=curr
			if (Curr != getIterminal())
				for (int i = 0; i < Yorder; i++)
					Curr[i] = getIterminal()[i];
		} else {
			YPrim.MVMult(Curr, getVterminal());
			for (int i = 0; i < Yorder; i++)
				Curr[i] = Curr[i].add( getInjCurrent()[i].negate() );
			setITerminalUpdated(true);
		}
		IterminalSolutionCount = DSSGlobals.getInstance().getActiveCircuit().getSolution().getSolutionCount();
	}

	/**
	 * Get present values of terminal.
	 *
	 * Gets total currents going into a devices terminals.
	 */
	public void getCurrents(Complex[] Curr) {
		try {
			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

			if (isEnabled()) {

				if ( sol.isLastSolutionWasDirect() && (! (sol.isIsDynamicModel() || sol.isIsHarmonicModel())) ) {

					// take a short cut and get currents from YPrim only
					// for case where model is entirely in Y matrix

					calcYPrimContribution(Curr);

				} else {

					getTerminalCurrents(Curr);
				}

			} else {  // not enabled
				for (int i = 0; i < Yorder; i++)
					Curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg(("getCurrents for element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element.", 641);
		}
	}

	public void calcYPrimContribution(Complex[] Curr) {
		computeVterminal();
		// apply these voltages to Yprim
		YPrim.MVMult(Curr, Vterminal);
	}

	/**
	 * For harmonics mode
	 */
	public void initHarmonics() {
		// by default do nothing in the base class
	}

	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[ArrayOffset + 1] = Spectrum;

		super.initPropertyValues(ArrayOffset + 1);
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

	public void getAllVariables(double[] States) {
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
	public int lookupVariable(String S) {
		int Result = -1;   // returns -1 for error not found
		int TestLength = S.length();
		for (int i = 0; i < numVariables(); i++) {
			if (variableName(i).substring(0, TestLength).equalsIgnoreCase(S)) {
				Result = i;
				break;
			}
		}
		return Result;
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		if (Complete) {
			F.println("! VARIABLES");
			for (int i = 0; i < numVariables(); i++)
				F.println("! " + i + ": " + variableName(i) + " = " + String.format("%-.5g", getVariable(i)));
		}
	}

	public double getVariable(int i) {
		/* Do nothing here -- up to override function */
		return -9999.99;
	}

	public void setVariable(int i, double Value) {
		/* Do nothing */
	}

	public void computeIterminal() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (IterminalSolutionCount != ckt.getSolution().getSolutionCount()) {
			getCurrents(Iterminal);
			IterminalSolutionCount = ckt.getSolution().getSolutionCount();
		}
	}

	public void zeroInjCurrent() {
		for (int i = 0; i < Yorder; i++)
			InjCurrent[i] = Complex.ZERO;
	}

	public void setITerminalUpdated(boolean Value) {
		IterminalUpdated = Value;
		if (Value)
			IterminalSolutionCount = DSSGlobals.getInstance().getActiveCircuit().getSolution().getSolutionCount();
	}

	public boolean getITerminalUpdated() {
		return IterminalUpdated;
	}

	public Complex[] getInjCurrent() {
		return InjCurrent;
	}

	public String getSpectrum() {
		return Spectrum;
	}

	public void setSpectrum(String spectrum) {
		Spectrum = spectrum;
	}

	/** Upline sensor for this element */
	public SpectrumObj getSpectrumObj() {
		return SpectrumObj;
	}

	public void setSpectrumObj(SpectrumObj spectrumObj) {
		SpectrumObj = spectrumObj;
	}

	/** Upline energy meter */
	public MeterElement getMeterObj() {
		return MeterObj;
	}

	public void setMeterObj(MeterElement meterObj) {
		MeterObj = meterObj;
	}

	public MeterElement getSensorObj() {
		return SensorObj;
	}

	public void setSensorObj(MeterElement sensorObj) {
		SensorObj = sensorObj;
	}

	public void setInjCurrent(Complex[] injCurrent) {
		InjCurrent = injCurrent;
	}

}

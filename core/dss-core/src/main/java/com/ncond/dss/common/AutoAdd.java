package com.ncond.dss.common;

import com.ncond.dss.common.impl.ControlProblem;
import com.ncond.dss.common.impl.Esolv32Problem;
import com.ncond.dss.common.impl.SolverError;

/*
 * Unit for processing the AutoAdd solution functions
 *
 * Note: Make sure this class in instantiated after EnergyMeter class
 *
 * There is one of these per circuit.
 */
public interface AutoAdd {

	/**
	 * Makes a list of unique bus names.
	 *
	 * If autoAddBusList in activeCircuit is not null, use this list.
	 * Otherwise, use the element lists in energy meters.
	 * If no energy meters, use all the buses in the active circuit.
	 */
	void makeBusList();

	/**
	 * Returns losses in metered part of circuit + weighted EEN values.
	 *
	 * If no meters, returns just total losses in circuit.
	 * Base everything on gen kW.
	 */
	double getWeightedLosses();

	/**
	 * Automatically add caps or generators.
	 *
	 * Automatically add a specified size of generator or capacitor at the location
	 * that results in the lowest losses in either metered part of circuit or
	 * total circuit, if no meters.
	 *
	 * If metered, EEN is also added in with a selected weighting factor (see
	 * set ueweight= ... command).
	 *
	 * Thus, this algorithm placed generators and capacitors to minimize losses and
	 * potential unserved energy.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	int solve() throws SolverError, ControlProblem, Esolv32Problem;

	/**
	 * Compute injection currents for generator or capacitor and add into
	 * system currents array.
	 */
	void addCurrents(int solveType);

	double getGenKW();

	void setGenKW(double genkW);

	double getGenPF();

	void setGenPF(double genPF);

	double getGenKVAr();

	void setGenKVAr(double genkvar);

	double getCapKVAr();

	void setCapKVAr(double capkvar);

	int getAddType();

	void setAddType(int addType);

	boolean isModeChanged();

	void setModeChanged(boolean modeChanged);

	void appendToFile(String whichFile, String s);

}

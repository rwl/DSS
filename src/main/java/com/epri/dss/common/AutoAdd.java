package com.epri.dss.common;

import com.epri.dss.common.impl.ControlProblem;
import com.epri.dss.common.impl.Esolv32Problem;
import com.epri.dss.common.impl.SolverError;

/*
 * Unit for processing the AutoAdd Solution FUNCTIONs
 *
 * Note: Make sure this class in instantiated after EnergyMeter class
 *
 * There is one of these per circuit
 */
public interface AutoAdd {

	double getGenkW();

	void setGenkW(double genkW);

	double getGenPF();

	void setGenPF(double genPF);

	double getGenkvar();

	void setGenkvar(double genkvar);

	double getCapkvar();

	void setCapkvar(double capkvar);

	int getAddType();

	void setAddType(int addType);

	boolean isModeChanged();

	void setModeChanged(boolean modeChanged);

	void makeBusList();

	void appendToFile(final String WhichFile, final String S);

	void addCurrents(int SolveType);

	/**
	 * Automatically add caps or generators.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	int solve() throws SolverError, ControlProblem, Esolv32Problem;

	double getWeightedLosses();

}

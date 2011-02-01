package com.epri.dss.common.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.AutoAdd;
import com.epri.dss.conversion.Generator;
import com.epri.dss.delivery.Capacitor;

public class AutoAddImpl implements AutoAdd {

	private Generator GeneratorClass;
	private Capacitor CapacitorClass;

	private int[] BusIdxList;
	private int BusIdxListSize;
	private boolean BusIdxListCreated;
	private int LastAddedGenerator;
	private int LastAddedCapacitor;

	private int BusIndex;
	private int Phases;

	private double Ycap;
	private Complex GenVA;

	private double kWLosses, BaseLosses, puLossImprovement;
	private double kWEEN , BaseEEN, puEENImprovement;

	private PrintStream Log;  // Log File

	private int ProgressCount;


	/* Autoadd mode Variables */
	protected double GenkW, GenPF, Genkvar, Capkvar;
	protected int AddType;

	protected boolean ModeChanged;

	public AutoAddImpl() {
		// TODO Auto-generated constructor stub
	}

	private double getWeightedLosses() {
		return 0.0;
	}

	private void ComputekWLosses_EEN() {

	}

	private void setBaseLosses() {

	}

	private String getUniqueGenName() {
		return null;
	}

	private String getUniqueCapName() {
		return null;
	}

	public double getGenkW() {
		return GenkW;
	}

	public void setGenkW(double genkW) {
		GenkW = genkW;
	}

	public double getGenPF() {
		return GenPF;
	}

	public void setGenPF(double genPF) {
		GenPF = genPF;
	}

	public double getGenkvar() {
		return Genkvar;
	}

	public void setGenkvar(double genkvar) {
		Genkvar = genkvar;
	}

	public double getCapkvar() {
		return Capkvar;
	}

	public void setCapkvar(double capkvar) {
		Capkvar = capkvar;
	}

	public int getAddType() {
		return AddType;
	}

	public void setAddType(int addType) {
		AddType = addType;
	}

	public boolean isModeChanged() {
		return ModeChanged;
	}

	public void setModeChanged(boolean modeChanged) {
		ModeChanged = modeChanged;
	}

	public void makeBusList() {

	}

	public void appendToFile(String WhichFile, String S) {

	}

	public void addCurrents(int SolveType) {

	}

}

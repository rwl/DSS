package com.ncond.dss.conversion.impl;

import java.io.File;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.impl.DSSCallBacks;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.impl.DynamicsRec;
import com.ncond.dss.shared.impl.GeneratorVars;

public class IndMach012Model extends GenUserModelImpl {

	private static IndMach012Model activeModel;
	private static Parser modelParser;
	private static CommandList commandList;

	private static boolean debugTrace;


	private double puRs, puXs, puRr, puXr, puXm;
	private double S1;  // pos seq slip
	private double S2;
	private double MaxSlip;  // limit for slip to prevent solution blowing up
	private double dSdP;  // for power flow

	/* Dynamics variables */
	private double Xopen, Xp;
	private double T0p;  // rotor time constant

	private boolean InDynamics;

	private Complex Zs, Zm, Zr, Zrsc;
	private Complex Is1, Ir1, V1;  // keep the last computed voltages and currents
	private Complex Is2, Ir2, V2;

	/* Complex variables for dynamics */
	private Complex E1, E1n, dE1dt, dE1dtn,
		E2, E2n, dE2dt, dE2dtn, Zsp;

	private boolean FirstIteration, FixedSlip;

	private File TraceFile;

	protected DynamicsRec DynaData;
	protected GeneratorVars GenData;
	protected DSSCallBacks CallBack;

	public IndMach012Model(GeneratorVars genVars, DynamicsRec dynaVars, DSSCallBacks callBacks) {
		super(genVars);

		/* Vestas wind generator */
		puRs = 0.0053;
		puXs = 0.106;
		puRr = 0.007;
		puXr = 0.12;
		puXm = 4.0;

		GenData = genVars;
		DynaData = dynaVars;
		CallBack = callBacks;

		// set slip local and make generator model agree
		MaxSlip = 0.1;  // 10% slip limit - set this before setting slip
		setSlip(-0.007);  // generating about 1 pu power
		FixedSlip = false;  // allow slip to float to match specified power

		InDynamics = false;

		reCalcElementData();
	}

	public void setLocalSlip(final double Value) {

	}

	public double getLocalSlip() {
		return S1;
	}

	private void getModelCurrent(final Complex V, final double s, Complex[] Istator, Complex[] Irotor) {
		double RL;
		Complex ZRotor, Numerator, Zmotor;

		if (s != 0.0) {
			RL = Zr.getReal() * (1.0 - s) / s;
		} else {
			RL = Zr.getReal() * 1.0e6;
		}

		ZRotor = new Complex(RL, 0.0).add(Zr);
		Numerator = Zm.multiply(ZRotor);
		Zmotor = Zs.add(Numerator.divide(ZRotor.add(Zm)));
		Istator[0] = V.divide(Zmotor);
		/* Ir = Is -(V-ZsIs)/Zm */
		Irotor[0] = Istator[0].subtract( V.subtract(Zs.multiply(Istator[0])).divide(Zm) );
	}

	private void getDynamicModelCurrent(final Complex V1, final Complex V2) {

	}

	private void doHelpCmd() {

	}

	public void setSlip(final double Value) {

	}

	private static double getRotorLosses() {
		return 0;
	}

	private static double getStatorLosses() {
		return 0;
	}

	private static double compute_dSdP() {
		return 0;
	}

	private void initTraceFile() {

	}

	private void writeTraceRecord() {

	}

	public void calcDynamic(Complex[] V012, Complex[] I012) {

	}

	public void CalcPFlow(Complex[] V012, Complex[] I012) {

	}

	public void reCalcElementData() {
		double Rs, Xs, Rr, Xr, Xm, ZBase;

		ZBase = Math.pow(GenData.kVGeneratorBase, 2) / GenData.kVARating * 1000.0;

		Rs = puRs * ZBase;
		Xs = puXs * ZBase;
		Rr = puRr * ZBase;
		Xr = puXr * ZBase;
		Xm = puXm * ZBase;
		Zs = new Complex(Rs, Xs);
		Zm = new Complex(0.0, Xm);
		Zr = new Complex(Rr, Xr);

		Xopen = Xs + Xm;
		Xp  = Xs + (Xr*Xm)/(Xr+Xm);
		Zsp = new Complex(Rs, Xp);
		T0p = (Xr + Xm) / (GenData.w0 * Rr);

		Zrsc = Zr.add(Zs.multiply(Zm).divide(Zs.add(Zm)));
		dSdP = compute_dSdP();

		Is1 = Complex.ZERO;
		V1  = Complex.ZERO;
		Is2 = Complex.ZERO;
		V2  = Complex.ZERO;

		FirstIteration = true;

		if (debugTrace)	initTraceFile();
	}

	public void interpretOption(String s) {

	}


	public int select(int x) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String value) {
		// TODO Auto-generated method stub

	}

	public void edit(String value) {
		// one should really use the callbacks

		int ParamPointer = -1;
		String ParamName = modelParser.getNextParam();
		String Param = modelParser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				if (Utilities.compareTextShortest(Param, "help") == 0) {
					ParamPointer = 8;
				} else {
					ParamPointer += 1;
				}
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			switch (ParamPointer) {
			case 0:
				puRs = modelParser.makeDouble();
				break;
			case 1:
				puXs = modelParser.makeDouble();
				break;
			case 2:
				puRr = modelParser.makeDouble();
				break;
			case 3:
				puXr = modelParser.makeDouble();
				break;
			case 4:
				puXm = modelParser.makeDouble();
				break;
			case 5:
				setSlip(modelParser.makeDouble());
				break;
			case 6:
				MaxSlip = modelParser.makeDouble();
				break;
			case 7:
				interpretOption(modelParser.makeString());
				break;
			case 8:
				doHelpCmd();     // whatever the option, do help
				break;
			}

			ParamName = modelParser.getNextParam();
			Param = modelParser.makeString();
		}

		reCalcElementData();
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public void edit(int s, int maxlen) {
		// TODO Auto-generated method stub

	}

	public void init(Complex[] V012, Complex[] I012) {
		// initialize rotor speed
		GenData.speed = -getLocalSlip() * GenData.w0;

		//reCalcElementData(); ???

		// compute voltage behind transient reactance and set derivatives to zero
		E1 = V012[0].subtract(I012[0].multiply(Zsp));
		dE1dt = Complex.ZERO;
		E1n = E1;
		dE1dtn = dE1dt;
		E2 = V012[1].subtract(I012[1].multiply(Zsp));
		dE2dt = Complex.ZERO;
		E2n = E2;
		dE2dtn = dE2dt;
	}

	public void calc(Complex[] V, Complex[] I) {
		// TODO Auto-generated method stub

	}

	public void integrate() {
		// TODO Auto-generated method stub

	}

	public void updateModel() {
		// TODO Auto-generated method stub

	}

	public GeneratorVars getActiveGeneratorVars() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setActiveGeneratorVars(GeneratorVars activeGeneratorVars) {
		// TODO Auto-generated method stub

	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public void restore() {
		// TODO Auto-generated method stub

	}

	public int numVars() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void getAllVars(double[] vars) {
		// TODO Auto-generated method stub

	}

	public void getAllVars(double d) {
		// TODO Auto-generated method stub

	}

	public double getVariable(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVariable(int i, double value) {
		// TODO Auto-generated method stub

	}

	public void getVarName(int varNum, int varName, int maxlen) {
		// TODO Auto-generated method stub

	}

	public DynamicsRec getDynaData() {
		return DynaData;
	}

	public void setDynaData(DynamicsRec dynaData) {
		DynaData = dynaData;
	}

	public GeneratorVars getGenData() {
		return GenData;
	}

	public void setGenData(GeneratorVars genData) {
		GenData = genData;
	}

	public DSSCallBacks getCallBack() {
		return CallBack;
	}

	public void setCallBack(DSSCallBacks callBack) {
		CallBack = callBack;
	}

}

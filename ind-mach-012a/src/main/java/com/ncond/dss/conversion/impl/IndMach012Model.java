package com.ncond.dss.conversion.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.impl.DSSCallBacks;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.impl.ComplexUtil;
import com.ncond.dss.shared.impl.DynamicsRec;
import com.ncond.dss.shared.impl.GeneratorVars;

public class IndMach012Model extends GenUserModelImpl {

	private static IndMach012Model activeModel;
	private static Parser modelParser;
	private static CommandList commandList;

	private static boolean debugTrace = false;


	private double puRs, puXs, puRr, puXr, puXm;
	private double S1;  // pos seq slip
	private double S2;
	private double maxSlip;  // limit for slip to prevent solution blowing up
	private double dSdP;  // for power flow

	/* Dynamics variables */
	private double Xopen, Xp;
	private double T0p;  // rotor time constant

	private boolean inDynamics;

	private Complex Zs, Zm, Zr, Zrsc;
	private Complex Is1, Ir1, V1;  // keep the last computed voltages and currents
	private Complex Is2, Ir2, V2;

	/* Complex variables for dynamics */
	private Complex E1, E1n, dE1dt, dE1dtn,
		E2, E2n, dE2dt, dE2dtn, Zsp;

	private boolean firstIteration, fixedSlip;

	private File traceFile;

	protected DynamicsRec dynaData;
	protected GeneratorVars genData;
	protected DSSCallBacks callBack;

	public IndMach012Model(GeneratorVars genVars, DynamicsRec dynaVars, DSSCallBacks callBacks) {
		super(genVars);

		/* Vestas wind generator */
		puRs = 0.0053;
		puXs = 0.106;
		puRr = 0.007;
		puXr = 0.12;
		puXm = 4.0;

		genData = genVars;
		dynaData = dynaVars;
		callBack = callBacks;

		// set slip local and make generator model agree
		maxSlip = 0.1;  // 10% slip limit - set this before setting slip
		setSlip(-0.007);  // generating about 1 pu power
		fixedSlip = false;  // allow slip to float to match specified power

		inDynamics = false;

		reCalcElementData();
	}

	private static double sign(final double x) {
		return x < 0.0 ? -1.0 : 1.0;
	}

	public void setLocalSlip(final double value) {
		S1 = value;
		if (!inDynamics)
			if (Math.abs(S1) > maxSlip)
				S1 = sign(S1) * maxSlip;  // put limits on the slip  unless dynamics
		S2 = 2.0 - S1;
	}

	public double getLocalSlip() {
		return S1;
	}

	private void getModelCurrent(final Complex V, final double S, Complex[] IStator, Complex[] IRotor) {
		double RL;
		Complex ZRotor, numerator, ZMotor;

		if (S != 0.0) {
			RL = Zr.getReal() * (1.0 - S) / S;
		} else {
			RL = Zr.getReal() * 1.0e6;
		}

		ZRotor = new Complex(RL, 0.0).add(Zr);
		numerator = Zm.multiply(ZRotor);
		ZMotor = Zs.add(numerator.divide(ZRotor.add(Zm)));
		IStator[0] = V.divide(ZMotor);
		/* Ir = Is -(V-ZsIs)/Zm */
		IRotor[0] = IStator[0].subtract( V.subtract(Zs.multiply(IStator[0])).divide(Zm) );
	}

	private void getDynamicModelCurrent(final Complex V1, final Complex V2) {
		Is1 = V1.subtract(E1).divide(Zsp);  // I = (V-E')/Z'
		Is2 = V2.subtract(E2).divide(Zsp);  // I = (V-E')/Z'

		// rotor current  Ir1= Is1-Vm/jXm
		Ir1 = Is1.subtract(V1.subtract(Is1.multiply(Zsp)).divide(Zm));
		Ir2 = Is2.subtract(V2.subtract(Is2.multiply(Zsp)).divide(Zm));
	}

	private void doHelpCmd() {
		String helpStr;
		String CRLF;

		CRLF = "\n";
		helpStr = "Rs= per unit stator resistance." + CRLF;
		helpStr = helpStr + "Xs= per unit stator leakage reactance." + CRLF;
		helpStr = helpStr + "Rr= per unit rotor  resistance." + CRLF;
		helpStr = helpStr + "Xr= per unit rotor leakage reactance." + CRLF;
		helpStr = helpStr + "Xm= per unit magnetizing reactance." + CRLF;
		helpStr = helpStr + "slip= initial slip value." + CRLF;
		helpStr = helpStr + "maxslip= max slip value to allow." + CRLF;
		helpStr = helpStr + "option={fixedslip | variableslip | Debug | NoDebug }" + CRLF;
		helpStr = helpStr + "Help: this help message.";

		callBack.msgCallBack(helpStr, helpStr.length());
	}

	public void setSlip(final double value) {
	        setLocalSlip(value);
	        genData.speed = genData.w0 * (-S1);  // make generator speed agree
	}

	private double getRotorLosses() {
	        return 3.0 * (Math.pow(Ir1.getReal(), 2) + Math.pow(Ir1.getImaginary(), 2) + Math.pow(Ir2.getReal(), 2) + Math.pow(Ir2.getImaginary(), 2)) * Zr.getReal();
	}

	private double getStatorLosses() {
		return 3.0 * (Math.pow(Is1.getReal(), 2) + Math.pow(Is1.getImaginary(), 2) + Math.pow(Is2.getReal(), 2) + Math.pow(Is2.getImaginary(), 2)) * Zs.getReal();
	}

	private double compute_dSdP() {
		Complex[] IIs1 = new Complex[1], IIr1 = new Complex[1];
		// dSdP based on rated slip and rated voltage
		V1 = new Complex(genData.kVGeneratorBase * 1000.0 / 1.732, 0.0);
		if (S1 != 0.0) getModelCurrent(V1, S1, IIs1, IIr1);
		Is1 = IIs1[0]; Ir1 = IIr1[0];
		return S1 / (V1.multiply(Is1.conjugate())).getReal();
	}

	private void initTraceFile() {
		traceFile = new File("IndMach012_Trace.csv");
		FileWriter f;
		PrintWriter pw;

		try {
			f = new FileWriter(traceFile, false);
			pw = new PrintWriter(traceFile);
			pw.println("Time, Iteration, S1, |IS1|, |IS2|, |E1|, |dE1dt|, |E2|, |dE2dt|, |V1|, |V2|");
			f.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeTraceRecord() {
		traceFile = new File("IndMach012_Trace.csv");
		FileWriter f;
		PrintWriter pw;

		try {
			f = new FileWriter(traceFile, false);
			pw = new PrintWriter(traceFile);

			pw.printf("%-.6g, %d, %-.6g, ", dynaData.t, dynaData.iterationFlag, S1);
			pw.printf("%-.6g, %-.6g, ", Is1.abs(), Is2.abs());
			pw.printf("%-.6g, %-.6g, %-.6g, %-.6g, ", E1.abs(), dE1dt.abs(), E2.abs(), dE2dt.abs());
			pw.printf("%-.6g, %-.6g", V1.abs(), V2.abs());

			pw.println();

			f.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void calcDynamic(Complex[] V012, Complex[] I012) {
		/* In dynamics mode, slip is allowed to vary */
		inDynamics = true;
		V1 = V012[0];  // save for variable calcs
		V2 = V012[1];
		/* Gets slip from shaft speed */
		setLocalSlip((-genData.speed) / genData.w0);
		getDynamicModelCurrent(V1, V2);
		// getModelCurrent(V2, S2, Is2, Ir2);
		I012[0] = Is1;    // Save for variable calcs
		I012[1] = Is2;
		I012[-1] = new Complex(0.0, 0.0);  // FIXME: zero based indexing

		if (debugTrace) writeTraceRecord();
	}

	public void CalcPFlow(Complex[] V012, Complex[] I012) {
		Complex[] IIs1 = new Complex[1], IIr1 = new Complex[1];
		Complex[] IIs2 = new Complex[1], IIr2 = new Complex[1];
		double PError;

		V1 = V012[0];  // save for variable calcs
		V2 = V012[1];

		inDynamics = false;

		if (firstIteration) {
			getModelCurrent(V1, S1, IIs1, IIr1);  // initialize Is1
			Is1 = IIs1[0]; Ir1 = IIr1[0];
			firstIteration = false;
		}

		//P_Error = -GenData.WnominalperPhase - TerminalPowerIn(V, I, 3).getReal() / 3.0;
		/* If Fixed slip option set, then use the value set by the user */
		if (!fixedSlip) {
			PError = -genData.PNominalPerPhase - V1.multiply(Is1.conjugate()).getReal();
			setLocalSlip(S1 + dSdP * PError);  // make new guess at slip
		}

		//setLocalSlip(computeSlip(V1, Psh);
		getModelCurrent(V1, S1, IIs1, IIr1);
		Is1 = IIs1[0]; Ir1 = IIr1[0];
		getModelCurrent(V2, S2, IIs2, IIr2);
		Is2 = IIs2[0]; Ir2 = IIr2[0];

		I012[0] = Is1;  // save for variable calcs
		I012[1] = Is2;
		I012[-1] = new Complex(0.0, 0.0);  // FIXME: zero based indexing
	}

	public void reCalcElementData() {
		double Rs, Xs, Rr, Xr, Xm, ZBase;

		ZBase = Math.pow(genData.kVGeneratorBase, 2) / genData.kVARating * 1000.0;

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
		T0p = (Xr + Xm) / (genData.w0 * Rr);

		Zrsc = Zr.add(Zs.multiply(Zm).divide(Zs.add(Zm)));
		dSdP = compute_dSdP();

		Is1 = Complex.ZERO;
		V1  = Complex.ZERO;
		Is2 = Complex.ZERO;
		V2  = Complex.ZERO;

		firstIteration = true;

		if (debugTrace)	initTraceFile();
	}

	public void interpretOption(String s) {
		switch (s.toUpperCase().charAt(0)) {
		case 'F':
			fixedSlip = true;
			break;
		case 'V':
			fixedSlip = false;
			break;
		case 'D':
			debugTrace = true;   // DEBUG
			break;
		case 'N':
			debugTrace = false;  // NODEBUG
			break;
		default:
			break;
		}
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

		int paramPointer = -1;
		String paramName = modelParser.getNextParam();
		String param = modelParser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				if (Utilities.compareTextShortest(param, "help") == 0) {
					paramPointer = 8;
				} else {
					paramPointer += 1;
				}
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			switch (paramPointer) {
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
				maxSlip = modelParser.makeDouble();
				break;
			case 7:
				interpretOption(modelParser.makeString());
				break;
			case 8:
				doHelpCmd();     // whatever the option, do help
				break;
			}

			paramName = modelParser.getNextParam();
			param = modelParser.makeString();
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
		genData.speed = -getLocalSlip() * genData.w0;

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
		double h2;

		if (dynaData.iterationFlag == 0) {  // on predictor step
			E1n = E1;            // update old values
			dE1dtn = dE1dt;
			E2n = E2;
			dE2dtn = dE2dt;
		}

		// derivative of E
		// dEdt = -jw0SE' - (E' - j(X-X')I')/T0'
		dE1dt = new Complex(0.0, -genData.w0 * S1).multiply(E1).subtract(ComplexUtil.divide(E1.subtract(new Complex(0.0, Xopen-Xp).multiply(Is1)), T0p));
		dE2dt = new Complex(0.0, -genData.w0 * S2).multiply(E2).subtract(ComplexUtil.divide(E2.subtract(new Complex(0.0, Xopen-Xp).multiply(Is2)), T0p));

		// trapezoidal integration
		h2 = dynaData.h * 0.5;
		E1 = E1n.add(dE1dt.add(dE1dtn).multiply(h2));
		E2 = E2n.add(dE2dt.add(dE2dtn).multiply(h2));

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
		double result = -1.0;

		switch (i) {
		case 0:
			result = getLocalSlip();
			break;
		case 1:
			result = puRs;
			break;
		case 2:
			result = puXs;
			break;
		case 3:
			result = puRr;
			break;
		case 4:
			result = puXr;
			break;
		case 5:
			result = puXm;
			break;
		case 6:
			result = maxSlip;
			break;
		case 7:
			result = Is1.abs();
			break;
		case 8:
			result = Is2.abs();
			break;
		case 9:
			result = Ir1.abs();
			break;
		case 10:
			result = Ir2.abs();
			break;
		case 11:
			result = getStatorLosses();
			break;
		case 12:
			result = getRotorLosses();
			break;
		case 13:  // shaft power (hp)
			result = 3.0 / 746.0 * (Math.pow(Ir1.abs(), 2) * (1.0 - S1) / S1 + Math.pow(Ir2.abs(), 2) * (1.0 - S2) / S2 )* Zr.getReal();
			break;
		}
		return result;
	}

	public void setVariable(int i, double value) {
		switch (i) {
		case 0:
			setSlip(value);
			break;
		case 1:
			puRs = value;
			break;
		case 2:
			puXs = value;
			break;
		case 3:
			puRr = value;
			break;
		case 4:
			puXr = value;
			break;
		case 5:
			puXm = value;
			break;
		}
	}

	public void getVarName(int varNum, int varName, int maxlen) {
		// TODO Auto-generated method stub

	}

	public DynamicsRec getDynaData() {
		return dynaData;
	}

	public void setDynaData(DynamicsRec dynaVars) {
		dynaData = dynaVars;
	}

	public GeneratorVars getGenData() {
		return genData;
	}

	public void setGenData(GeneratorVars data) {
		genData = data;
	}

	public DSSCallBacks getCallBack() {
		return callBack;
	}

	public void setCallBack(DSSCallBacks c) {
		callBack = c;
	}

}

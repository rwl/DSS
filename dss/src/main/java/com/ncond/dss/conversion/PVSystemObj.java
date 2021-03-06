/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.general.TempShapeObj;
import com.ncond.dss.general.XYCurveObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.MathUtil;

/**
 * The PVsystem element is essentially a generator that consists of a PV panel and an inverter.
 *
 * The PVsystem element can also produce or absorb vars within the kVA rating of the inverter.
 *
 * The PVsystem element is assumed balanced over the no. of phases defined.
 *
 * TODO:
 *   Make connection to user model
 *   Yprim for various modes
 *   Define state vars and dynamics mode behavior
 *   Complete harmonics mode algorithm (generator mode is implemented)
 */
@SuppressWarnings("unused")
public class PVSystemObj extends PCElement {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private Complex[] cBuffer = new Complex[24];

	private Complex Yeq;     // at nominal
	private Complex Yeq95;   // at VMin
	private Complex Yeq105;  // at VMax

	private boolean debugTrace;
	private int PVSystemSolutionCount;
	private double PVSystemFundamental;  /* Thevinen equivalent voltage mag and angle reference for harmonic model */
	private boolean PVSystemObjSwitchOpen;
	private boolean firstSampleAfterReset;
	private boolean PFSpecified;
	private boolean kVArSpecified;

	private double kVARating;
	private double kVPVSystemBase;
	private double kVArOut;
	private double kWOut;
	private double panelKW;  // computed
	private double irradiance;
	private double kVArRequested;
	private double temperature;
	private double Pmpp;

	private double effFactor;
	private double tempFactor;

	private boolean inverterON;
	private double pctCutIn;
	private double pctCutOut;
	private double cutInKW;
	private double cutOutKW;

	private double pctR;
	private double pctX;

	private int openPVSystemSolutionCount;
	private double PNominalPerPhase;
	private double QNominalPerPhase;
	private double randomMult;

	private int regHours;
	private int regKVArh;
	private int regKWh;
	private int regMaxKVA;
	private int regMaxKW;
	private int regPrice;
	private Complex shapeFactor;
	private double TShapeValue;
	private double thetaHarm;  /* Thevinen equivalent voltage mag and angle reference for harmonic model */
	private File traceFile;
	private PVSystemUserModel userModel;  /* User-written models */

	private double varBase;  // base vars per phase
	private double VBase;    // base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double VMaxPU;
	private double VMinPU;
	private double VThevHarm;  /* Thevinen equivalent voltage mag and angle reference for harmonic model */
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;

	// public members
	private Connection connection;
	private String dailyShape;           // daily (24 HR) PVSystem element irradiance shape
	private LoadShapeObj dailyShapeObj;  // daily PVSystem element irradiance shape for this load
	private String dutyShape;            // duty cycle irradiance shape for changes typically less than one hour
	private LoadShapeObj dutyShapeObj;   // irradiance shape for this PVSystem element
	private String yearlyShape;
	private LoadShapeObj yearlyShapeObj;  // yearly irradiance shape for this PVSystem element

	private String dailyTShape;
	private TempShapeObj dailyTShapeObj;
	private String dutyTShape;
	private TempShapeObj dutyTShapeObj;
	private String yearlyTShape;
	private TempShapeObj yearlyTShapeObj;

	private String inverterCurve;
	private XYCurveObj inverterCurveObj;
	private String powerTempCurve;
	private XYCurveObj powerTempCurveObj;

	private int FClass;
	private PVSystemModel voltageModel;  // variation with voltage
	protected double PFNominal;

	private double[] registers = new double[PVSystem.NumPVSystemRegisters];
	private double[] derivatives = new double[PVSystem.NumPVSystemRegisters];

	public PVSystemObj(DSSClass parClass, String PVSystemName) {
		super(parClass);

		setName(PVSystemName.toLowerCase());
		objType = parClass.getClassType();  // + PVSystem_ELEMENT;  // in both PCElement and PVSystemElement list

		setNumPhases(3);
		nConds = 4;    // defaults to wye
		YOrder = 0;    // to trigger an initial allocation
		setNumTerms(1);  // forces allocations

		yearlyShape       = "";
		yearlyShapeObj    = null;  // if yearlyShapeObj = null then the irradiance always stays nominal
		dailyShape        = "";
		dailyShapeObj     = null;  // if dailyShapeObj = null then the irradiance always stays nominal
		dutyShape         = "";
		dutyShapeObj      = null;  // if dutyShapeObj = null then the irradiance always stays nominal

		yearlyTShape      = "";
		yearlyTShapeObj   = null;  // if yearlyShapeObj = null then the temperature always stays nominal
		dailyTShape       = "";
		dailyTShapeObj    = null;  // if dailyShapeObj = null then the temperature always stays nominal
		dutyTShape        = "";
		dutyTShapeObj     = null;  // if dutyShapeObj = null then the temperature always stays nominal

		inverterCurveObj  = null;
		powerTempCurveObj = null;
		inverterCurve     = "";
		powerTempCurve    = "";

		connection        = Connection.WYE;
		voltageModel      = PVSystemModel.PQ;  // typical fixed kW negative load
		FClass            = 1;

		PVSystemSolutionCount     = -1;  // for keep track of the present solution in injCurrent calcs
		openPVSystemSolutionCount = -1;
		YPrimOpenCond             = null;

		kVPVSystemBase = 12.47;
		VBase          = 7200.0;
		VMinPU         = 0.90;
		VMaxPU         = 1.10;
		VBase95        = VMinPU  * VBase;
		VBase105       = VMaxPU  * VBase;
		YOrder         = nTerms * nConds;
		randomMult     = 1.0 ;

		PFSpecified    = true;
		kVArSpecified  = false;
		inverterON     = true; // start with inverterOn

		temperature    = 25.0;
		irradiance     = 1.0;  // kW / sq-m
		pctCutIn       = 20.0;
		pctCutOut      = 20.0;

		/* Output rating stuff */
		kWOut     = 500.0;
		kVArOut   = 0.0;
		PFNominal = 1.0;
		kVARating = 500.0;
		Pmpp      = 500.0;

		pctR = 0.0;;
		pctX = 50.0;

		userModel = new PVSystemUserModelImpl();

		regKWh    = 0;
		regKVArh  = 1;
		regMaxKW  = 2;
		regMaxKVA = 3;
		regHours  = 4;
		regPrice  = 5;

		debugTrace            = false;
		PVSystemObjSwitchOpen = false;
		spectrum              = "";  // override base class
		spectrumObj           = null;

		initPropertyValues(0);
		recalcElementData();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "3");         // "phases";
		setPropertyValue(1, getBus(0));   // "bus1";

		setPropertyValue(PVSystem.KV, String.format("%g", kVPVSystemBase));
		setPropertyValue(PVSystem.IRRADIANCE, String.format("%g", irradiance));
		setPropertyValue(PVSystem.PF, String.format("%g", PFNominal));
		setPropertyValue(PVSystem.MODEL, "1");
		setPropertyValue(PVSystem.YEARLY, "");
		setPropertyValue(PVSystem.DAILY, "");
		setPropertyValue(PVSystem.DUTY, "");
		setPropertyValue(PVSystem.T_YEARLY, "");
		setPropertyValue(PVSystem.T_DAILY, "");
		setPropertyValue(PVSystem.T_DUTY, "");
		setPropertyValue(PVSystem.CONNECTION, "wye");
		setPropertyValue(PVSystem.KVAR, String.format("%g", getPresentKVAr()));

		setPropertyValue(PVSystem.PCTR, String.format("%g", pctR));
		setPropertyValue(PVSystem.PCTX, String.format("%g", pctX));

		setPropertyValue(PVSystem.CLASS, "1"); //"class"

		setPropertyValue(PVSystem.INV_EFF_CURVE, "");
		setPropertyValue(PVSystem.TEMP, String.format("%g", temperature));
		setPropertyValue(PVSystem.PMPP, String.format("%g", Pmpp));
		setPropertyValue(PVSystem.P_T_CURVE, "");
		setPropertyValue(PVSystem.CUT_IN, "20");
		setPropertyValue(PVSystem.CUT_OUT, "20");

		setPropertyValue(PVSystem.VMIN_PU, "0.90");
		setPropertyValue(PVSystem.VMAX_PU, "1.10");
		setPropertyValue(PVSystem.KVA, String.format("%g", kVARating));

		setPropertyValue(PVSystem.USER_MODEL, "");  // UserModel
		setPropertyValue(PVSystem.USER_DATA, "");   // UserData
		setPropertyValue(PVSystem.DEBUG_TRACE, "NO");

		super.initPropertyValues(PVSystem.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case PVSystem.KV         : return String.format("%.6g", kVPVSystemBase);
		case PVSystem.IRRADIANCE : return String.format("%.6g", irradiance);
		case PVSystem.PF         : return String.format("%.6g", PFNominal);
		case PVSystem.MODEL      : return String.format("%d",   voltageModel);
		case PVSystem.YEARLY     : return yearlyShape;
		case PVSystem.DAILY      : return dailyShape;
		case PVSystem.DUTY       : return dutyShape;

		case PVSystem.T_YEARLY    : return yearlyTShape;
		case PVSystem.T_DAILY     : return dailyTShape;
		case PVSystem.T_DUTY      : return dutyTShape;

		/*case PVSystem.propCONNECTION :;*/
		case PVSystem.KVAR       : return String.format("%.6g", kVArOut);
		case PVSystem.PCTR       : return String.format("%.6g", pctR);
		case PVSystem.PCTX       : return String.format("%.6g", pctX);
		/*case PVSystem.propCLASS      = 17;*/
		case PVSystem.INV_EFF_CURVE: return inverterCurve;
		case PVSystem.TEMP       : return String.format("%.6g", temperature);
		case PVSystem.PMPP       : return String.format("%.6g", Pmpp);
		case PVSystem.P_T_CURVE  : return powerTempCurve;
		case PVSystem.CUT_IN      : return String.format("%.6g", pctCutIn);
		case PVSystem.CUT_OUT     : return String.format("%.6g", pctCutOut);
		case PVSystem.VMIN_PU     : return String.format("%.6g", VMinPU);
		case PVSystem.VMAX_PU     : return String.format("%.6g", VMaxPU);
		case PVSystem.KVA        : return String.format("%.6g", kVARating);

		case PVSystem.USER_MODEL  : return userModel.getName();
		case PVSystem.USER_DATA   : return "(" + super.getPropertyValue(index) + ")";
		/*case PVSystem.propDEBUGTRACE = 33;*/

		default:  // take the generic handler
			return super.getPropertyValue(index);
		}
	}

	/**
	 * 0 = reset to 1.0; 1 = Gaussian around mean and std dev; 2 = uniform
	 */
	public void randomize(Randomization opt) {
		switch (opt) {
		case NONE:
			randomMult = 1.0;
			break;
		case GAUSSIAN:
			randomMult = MathUtil.gauss(yearlyShapeObj.getMean(), yearlyShapeObj.getStdDev());
			break;
		case UNIFORM:
			randomMult = Math.random();  // number between 0 and 1.0
			break;
		case LOGNORMAL:
			randomMult = MathUtil.quasiLognormal(yearlyShapeObj.getMean());
			break;
		}
	}

	private void calcDailyMult(double hr) {
		if (dailyShapeObj != null) {
			shapeFactor = dailyShapeObj.getMult(hr);
		} else {
			shapeFactor = CDOUBLEONE;  // default to no variation
		}
	}

	private void calcDailyTemperature(double hr) {
		if (dailyTShapeObj != null) {
			TShapeValue = dailyTShapeObj.getTemperature(hr);
		} else {
			TShapeValue = 1.0;  // default to no variation
		}
	}

	private void calcDutyMult(double hr) {
		if (dutyShapeObj != null) {
			shapeFactor = dutyShapeObj.getMult(hr);
		} else {
			calcDailyMult(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcDutyTemperature(double hr) {
		if (dutyTShapeObj != null) {
			TShapeValue = dutyTShapeObj.getTemperature(hr);
		} else {
			calcDailyTemperature(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double hr) {
		if (yearlyShapeObj != null) {
			shapeFactor = yearlyShapeObj.getMult(hr) ;
		} else {
			calcDailyMult(hr);  // defaults to daily curve
		}
	}

	private void calcYearlyTemperature(double hr) {
		if (yearlyTShapeObj != null) {
			TShapeValue = yearlyTShapeObj.getTemperature(hr) ;
		} else {
			calcDailyTemperature(hr);  // defaults to daily curve
		}
	}

	public void setNominalPVSystemOuput() {
		shapeFactor = CDOUBLEONE;  // init here; changed by curve routine
		TShapeValue = 25.0;  // init here; changed by curve routine

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		// check to make sure the PVSystem element is on
		if (!(sol.isDynamicModel() || sol.isHarmonicModel())) {
			// leave PVSystem element in whatever state it was prior to entering dynamic mode

			// check dispatch to see what state the PVSystem element should be in
			switch (sol.getMode()) {
			case SNAPSHOT:
				/* Just solve for the present kW, kVAr */  // don't check for state change
				break;
			case DAILYMODE:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
				break;
			case YEARLYMODE:
				calcYearlyMult(sol.getDblHour());
				calcYearlyTemperature(sol.getDblHour());
				break;
			case MONTECARLO1:
			case MONTEFAULT:
			case FAULTSTUDY:
			case DYNAMICMODE:
				// do nothing yet
				break;

				// assume daily curve, if any, for the following
			case MONTECARLO2:
			case MONTECARLO3:
			case LOADDURATION1:
			case LOADDURATION2:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
				break;
			case PEAKDAY:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
				break;
			case DUTYCYCLE:
				calcDutyMult(sol.getDblHour());
				calcDutyTemperature(sol.getDblHour());
				break;
			case AUTOADDFLAG:
				// do nothing
				break;
			}

			computeKwKVAr();
			PNominalPerPhase = 1000.0 * kWOut    / getNumPhases();
			QNominalPerPhase = 1000.0 * kVArOut  / getNumPhases();

			switch (voltageModel) {
			case USER:
				//****  Fix this when user model gets connected in
				//Yeq = new Complex(0.0, -storeVARs.Xd).invert();  // gets negated in calcYPrim
				break;

			default:
				Yeq = new Complex(
					PNominalPerPhase,
					-QNominalPerPhase
				).div(MathUtil.sqr(VBase));  // VBase must be L-N for 3-phase

				if (VMinPU != 0.0) {
					Yeq95 = Yeq.div(MathUtil.sqr(VMinPU));  // at 95% voltage
				} else {
					Yeq95 = Yeq; // Always a constant Z model
				}

				if (VMaxPU != 0.0) {
					Yeq105 = Yeq.div(MathUtil.sqr(VMaxPU));  // at 105% voltage
				} else {
					Yeq105 = Yeq;
				}
				break;
			}
			/* When we leave here, all the Yeq's are in L-N values */
		}
	}

	@Override
	public void recalcElementData() {
		VBase95 = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		varBase = 1000.0 * kVArOut / nPhases;

		// values in ohms for Thevenin equivalents
		RThev = pctR * 0.01 * MathUtil.sqr(getPresentKV()) / kVARating * 1000.0;
		XThev = pctX * 0.01 * MathUtil.sqr(getPresentKV()) / kVARating * 1000.0;

		cutInKW  = pctCutIn  * kVARating / 100.0;
		cutOutKW = pctCutOut * kVARating / 100.0;

		setNominalPVSystemOuput();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning. */
		if (yearlyShapeObj == null)
			if (yearlyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Yearly load shape: \""+ yearlyShape +"\" not found.", 563);
		if (dailyShapeObj == null)
			if (dailyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Daily load shape: \""+ dailyShape +"\" not found.", 564);
		if (dutyShapeObj == null)
			if (dutyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Duty load shape: \""+ dutyShape +"\" not found.", 565);
		if (yearlyTShapeObj == null)
			if (yearlyTShape.length() > 0)
				DSS.doSimpleMsg("Warning: Yearly temperature shape: \""+ yearlyTShape +"\" not found.", 5631);
		if (dailyTShapeObj == null)
			if (dailyTShape.length() > 0)
				DSS.doSimpleMsg("Warning: Daily temperature shape: \""+ dailyTShape +"\" not found.", 5641);
		if (dutyTShapeObj == null)
			if (dutyTShape.length() > 0)
				DSS.doSimpleMsg("Warning: Duty temperature shape: \""+ dutyTShape +"\" not found.", 5651);

		if (getSpectrum().length() > 0) {
			setSpectrumObj( (SpectrumObj) DSS.spectrumClass.find(getSpectrum()) );
			if (getSpectrumObj() == null)
				DSS.doSimpleMsg("Error: Spectrum \"" + getSpectrum() + "\" not found.", 566);
		} else {
			setSpectrumObj(null);
		}

		// initialize to zero - defaults to PQ PVSystem element
		// solution object will reset after circuit modifications

		setInjCurrent(Util.resizeArray(getInjCurrent(), YOrder));

		/* Update any user-written models */
		if (userModel.exists()) userModel.updateModel();
	}

	private void calcYPrimMatrix(CMatrix YMatrix) {
		Complex Y, Yij;
		int i, j;
		double freqMultiplier;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		YPrimFreq = sol.getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		if (sol.isHarmonicModel()) {  /*|| sol.isDynamicModel()*/
			/* Yeq is computed from %R and %X -- inverse of Rthev + j Xthev */
			Y = Yeq;  // L-N value computed in initialization routines

			if (connection == Connection.DELTA)
				Y = Y.div(3.0);  // convert to delta impedance
			Y = new Complex(Y.real(), Y.imag() / freqMultiplier);
			Yij = Y.neg();
			for (i = 0; i < nPhases; i++) {
				switch (connection) {
				case WYE:
					YMatrix.set(i, i, Y);
					YMatrix.add(nConds-1, nConds-1, Y);
					YMatrix.setSym(i, nConds-1, Yij);
					break;
				case DELTA:
					YMatrix.set(i, i, Y);
					YMatrix.add(i, i, Y);  // put it in again
					for (j = 0; j < i; j++)
						YMatrix.setSym(i, j, Yij);
					break;
				}
			}
		} else {
			// regular power flow PVSystem element model

			/* Yeq is always expected as the equivalent line-neutral admittance */

			Y = Yeq.neg();  // negate for generation; YEQ is L-N quantity

			// ****** need to modify the base admittance for real harmonics calcs
			Y = new Complex(Y.real(), Y.imag() / freqMultiplier);

			switch (connection) {
			case WYE:
				Yij = Y.neg();
				for (i = 0; i < nPhases; i++) {
					YMatrix.set(i, i, Y);
					YMatrix.add(nConds-1, nConds-1, Y);
					YMatrix.setSym(i, nConds, Yij);
				}
				break;
			case DELTA:
				Y = Y.div(3.0);  // convert to delta impedance
				Yij = Y.neg();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j >= nConds) j = 0;  // wrap around for closed connections
					YMatrix.add(i, i, Y);
					YMatrix.add(j, j, Y);
					YMatrix.addSym(i, j, Yij);
				}
				break;
			}
		}
	}

	private void computeInverterPower() {
		double kVA_Gen;
		double effFactor = 1.0;
		double kW_Out = 0.0;

		// determine state of the inverter
		if (inverterON) {
			if (panelKW < cutOutKW) inverterON = false;
		} else {
			if (panelKW >= cutInKW) inverterON = true;
		}

		// set inverter output; defaults to 100% of the panelkW if no efficiency curve spec'd
		if (inverterON) {
			if (inverterCurveObj != null)
				effFactor = inverterCurveObj.getYValue(panelKW / kVARating);  // pu eff vs pu power
			kW_Out = panelKW * effFactor;
		} else {
			kW_Out = 0.0;
		}

		// kVAr value
		if (PFSpecified) {
			if (PFNominal == 1.0) {
				kVArOut = 0.0;
			} else {
				kVArOut = kWOut * Math.sqrt(1.0 / MathUtil.sqr(PFNominal) - 1.0) * Math.signum(PFNominal);
				// if PF is negative, make sure kVAr has opposite sign of kW
				// kW will always be positive
			}
		} else {  // kVAr is specified
			kVArOut = kVArRequested;
		}

		// limit kVAr so that kVA of inverter is not exceeded
		kVA_Gen = Math.sqrt(MathUtil.sqr(kWOut) + MathUtil.sqr(kVArOut));
		if (kVA_Gen > kVARating) {
			if (kWOut > kVARating) {
				kWOut   = kVARating;
				kVArOut = 0.0;
			} else {
				kVArOut = Math.sqrt(MathUtil.sqr(kVARating) - MathUtil.sqr(kW_Out)) * Math.signum(kVArOut);
			}
		}
	}

	private void computeKwKVAr() {
		computePanelPower();  // apply irradiance
		computeInverterPower();  // apply inverter eff after checking for cutin/cutout
	}

	private void computePanelPower() {
		tempFactor = 1.0;
		if (powerTempCurveObj != null)
			tempFactor = powerTempCurveObj.getYValue(TShapeValue);  // pu Pmpp vs T (actual)

		panelKW = irradiance * shapeFactor.real() * Pmpp * tempFactor;
	}

	@Override
	public void calcYPrim() {
		// build only shunt Yprim
		// build a dummy Yprim series so that calcV does not fail
		if (isYprimInvalid()) {
			YPrimShunt = new CMatrix(YOrder);
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		setNominalPVSystemOuput();
		calcYPrimMatrix(YPrimShunt);

		// set YPrim_Series based on diagonals of YPrim_shunt so that calcVoltages doesn't fail
		for (int i = 0; i < YOrder; i++)
			YPrimSeries.set(i, i, YPrimShunt.get(i, i).mult(1.0e-10));

		YPrim.copyFrom(YPrimShunt);

		// account for open conductors
		super.calcYPrim();
	}

	/**
	 * Add the current into the proper location according to connection.
	 *
	 * Reverse of similar routine in load (complex negates are switched).
	 */
	private void putCurrInTerminalArray(Complex[] termArray, Complex curr, int i) {
		switch (connection) {
		case WYE:
			termArray[i] = termArray[i].add(curr);
			termArray[nConds-1] = termArray[nConds-1].add(curr.neg());  // neutral
			break;
		case DELTA:
			termArray[i] = termArray[i].add(curr);
			int j = i + 1;
			if (j >= nConds) j = 0;  // wrap
			termArray[j] = termArray[j].add(curr.neg());
			break;
		}
	}

	private void writeTraceRecord(String s) {
		int i;
		try {
			if (!DSS.inShowResults) {
				FileWriter fw = new FileWriter(traceFile, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.printf("%-.g, %d, %-.g, ",
						DSS.activeCircuit.getSolution().getDynaVars().t,
						DSS.activeCircuit.getSolution().getIteration(),
						DSS.activeCircuit.getLoadMultiplier());
				pw.print(Util.getSolutionModeID() + ", " +
						Util.getLoadModel() + ", " +
						voltageModel + ", " +
						(QNominalPerPhase * 3.0 / 1.0e6) + ", " +
						(PNominalPerPhase * 3.0 / 1.0e6) + ", " +
						s + ", ");
				for (i = 0; i < nPhases; i++)
					pw.write(getInjCurrent(i).abs() + ", ");
				for (i = 0; i < nPhases; i++)
					pw.write(getITerminal(i).abs() + ", ");
				for (i = 0; i < nPhases; i++)
					pw.write(getVTerminal(i).abs() + ", ");

				pw.println();
				pw.close();
				fw.close();
			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing trace record: " + e.getMessage(), -1);
		}
	}

	/**
	 * Compute total terminal current for constant PQ.
	 */
	private void doConstantPQPVsystemObj() {
		int i;
		Complex curr = null, V;
		double VMag;

		// treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		zeroITerminal();

		calcVTerminalPhase();  // get actual voltage across each phase of the load
		for (i = 0; i < getNumPhases(); i++) {
			V = VTerminal[i];
			VMag = V.abs();

			switch (connection) {
			case WYE:
				if (VMag <= VBase95) {
					curr = Yeq95.mult(V);   // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = Yeq105.mult(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(
						PNominalPerPhase,
						QNominalPerPhase
					).div(V).conj();  // between 95% -105%, constant PQ
				}
				break;

			case DELTA:
				VMag = VMag / DSS.SQRT3;  // L-N magnitude
				if (VMag <= VBase95) {
					curr = Yeq95.div(3.0).mult(V);   // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = Yeq105.div(3.0).mult(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(
						PNominalPerPhase,
						QNominalPerPhase
					).div(V).conj();  // between 95% -105%, constant PQ
				}
				break;
			}

			putCurrInTerminalArray(getITerminal(), curr.neg(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Constant Z model
	 */
	private void doConstantZPVsystemObj() {
		int i;
		Complex curr, Yeq2;

		// assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();
		if (connection == Connection.WYE) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = Yeq.div(3.0);
		}

		for (i = 0; i < getNumPhases(); i++) {
			curr = Yeq2.mult(VTerminal[i]);   // Yeq is always line to neutral
			putCurrInTerminalArray(getITerminal(), curr.neg(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from User-written model
	 */
	private void doUserModel() {
		calcYPrimContribution(getInjCurrent());  // init injCurrent array

		if (userModel.exists()) {  // check automatically selects the user model if true
			userModel.calc(VTerminal, ITerminal);
			setITerminalUpdated(true);
			// negate currents from user model for power flow PVSystem element model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent(i).add(ITerminal[i].neg());
		} else {
			DSS.doSimpleMsg("PVSystem." + getName() +
				" model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute total current and add into injTemp
	 *
	 * For now, just assume the PVSystem element is constant power
	 * for the duration of the dynamic simulation.
	 */
	private void doDynamicMode() {
		doConstantPQPVsystemObj();
	}

	/**
	 * Compute injection current only when in harmonics mode.
	 *
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
	 * Vd is the fundamental frequency voltage behind Xd' for phase 1
	 */
	private void doHarmonicMode() {
		int i;
		Complex e;
		double PVSystemHarmonic;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		computeVTerminal();

		PVSystemHarmonic = sol.getFrequency() / PVSystemFundamental;
		if (getSpectrumObj() != null) {
			e = getSpectrumObj().getMult(PVSystemHarmonic).mult(VThevHarm);  // get base harmonic magnitude
		} else {
			e = Complex.ZERO;
		}

		e = Util.rotatePhasorRad(e, PVSystemHarmonic, thetaHarm);  // time shift by fundamental frequency phase shift
		for (i = 0; i < nPhases; i++) {
			cBuffer[i] = e;
			if (i < nPhases - 1)
				e = Util.rotatePhasorDeg(e, PVSystemHarmonic, -120.0);  // assume 3-phase PVSystem element
		}

		/* Handle wye connection */
		if (connection == Connection.WYE)
			cBuffer[nConds] = VTerminal[nConds];  // assume no neutral injection voltage

		/* Inj currents = Yprim (E) */
		YPrim.vMult(getInjCurrent(), cBuffer);
	}

	private void calcVTerminalPhase() {
		int i, j;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		/* Establish phase voltages and stick in VTerminal */
		switch (connection) {
		case WYE:
			for (i = 0; i < nPhases; i++)
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[nConds - 1]);
			break;

		case DELTA:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j >= nConds) j = 0;
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[j]);
			}
			break;
		}

		PVSystemSolutionCount = sol.getSolutionCount();
	}

	/**
	 * This is where the power gets computed.
	 *
	 * Calculates PVSystem element current and adds it properly into the injcurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag)
	 */
	private void calcPVSystemModelContribution() {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isDynamicModel()) {
			doDynamicMode();
		} else if (sol.isHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute currents and put into injTemp array
			switch (voltageModel) {
			case PQ:
				doConstantPQPVsystemObj();
				break;
			case Z:
				doConstantZPVsystemObj();
				break;
			case USER:
				doUserModel();
				break;
			default:
				doConstantPQPVsystemObj();  // for now, until we implement the other models.
				break;
			}
		}

		/* ITerminal is now up to date */
	}

	/**
	 * Difference between currents in YPrim and total current.
	 */
	private void calcInjCurrentArray() {
		// now get injection currents
		if (PVSystemObjSwitchOpen) {
			zeroInjCurrent();
		} else {
			calcPVSystemModelContribution();
		}
	}

	/**
	 * Compute total currents.
	 */
	@Override
	public void getTerminalCurrents(Complex[] curr) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!PVSystemObjSwitchOpen)
				calcPVSystemModelContribution();  // adds totals in ITerminal as a side effect
		}
		super.getTerminalCurrents(curr);

		if (debugTrace) writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (sol.loadsNeedUpdating())
			setNominalPVSystemOuput();  // set the nominal kW, etc for the type of solution being done

		calcInjCurrentArray();          // difference between currents in YPrim and total terminal current

		if (debugTrace) writeTraceRecord("Injection");

		// add into system injection current array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominal, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		calcInjCurrentArray();  // difference between currents in YPrim and total current

		try {
			// copy into buffer array
			for (int i = 0; i < YOrder; i++) curr[i] = getInjCurrent(i);
		} catch (Exception e) {
			DSS.doErrorMsg("PVSystem object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(), "Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		int i;
		for (i = 0; i < PVSystem.NumPVSystemRegisters; i++)
			registers[i] = 0.0;
		for (i = 0; i < PVSystem.NumPVSystemRegisters; i++)
			derivatives[i] = 0.0;
		firstSampleAfterReset = true;  // initialize for trapezoidal integration
	}

	private void integrate(int reg, double deriv, double interval) {
		Circuit ckt = DSS.activeCircuit;

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal rule integration */
			if (!firstSampleAfterReset)
				registers[reg] = registers[reg] + 0.5 * interval * (deriv + derivatives[reg]);
		} else {  /* Plain Euler integration */
			registers[reg] = registers[reg] + interval * deriv;
		}

		derivatives[reg] = deriv;
	}

	/**
	 * Update energy from metered zone
	 */
	public void takeSample() {
		Complex S;
		double Smag;
		double hourValue;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		// compute energy in PVSystem element branch
		if (isEnabled()) {
			S = new Complex(getPresentKW(), getPresentKVAr());
			Smag = S.abs();
			hourValue = 1.0;

			if (ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case
				 * Don't need to for gen off and normal integration.
				 */
				if (ckt.isPositiveSequence()) {
					S = S.mult(3.0);
					Smag = 3.0 * Smag;
				}
				integrate            (regKWh,    S.real(), sol.getIntervalHrs());   // accumulate the power
				integrate            (regKVArh,  S.imag(), sol.getIntervalHrs());
				setDragHandRegister  (regMaxKW,  Math.abs(S.real()));
				setDragHandRegister  (regMaxKVA, Smag);
				integrate            (regHours,  hourValue, sol.getIntervalHrs());  // accumulate hours in operation
				integrate            (regPrice,  S.real() * ckt.getPriceSignal(), sol.getIntervalHrs());  // accumulate hours in operation
				firstSampleAfterReset = false;
			}
		}
	}

	/**
	 * Update PVSystem elements based on present kW and IntervalHrs variable.
	 */
	protected void updatePVSystem() {
		// do nothing
	}

	public double getPresentKW() {
		return PNominalPerPhase * 0.001 * nPhases;
	}

	public double getPresentIrradiance() {
		return irradiance * shapeFactor.real();
	}

	public double getPresentKV() {
		return kVPVSystemBase;
	}

	public double getPresentKVAr() {
		return QNominalPerPhase * 0.001 * nPhases;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, idx;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			idx = getParentClass().getPropertyIdxMap(i);
			switch (idx) {
			case PVSystem.USER_DATA:
				pw.println("~ " + getParentClass().getPropertyName(i) +
					"=(" + getPropertyValue(idx) + ")");
				break;
			default:
				pw.println("~ " + getParentClass().getPropertyName(i) +
					"=" + getPropertyValue(idx));
				break;
			}
		}
		pw.println();
		pw.close();
	}

	/**
	 * This routine makes a Thevenin equivalent behis the reactance spec'd in %R and %X.
	 */
	@Override
	public void initHarmonics() {
		Complex e, Va = null;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		setYPrimInvalid(true);  // force rebuild of YPrims
		PVSystemFundamental = sol.getFrequency();  // whatever the frequency is when we enter here

		Yeq = new Complex(RThev, XThev).inv();  // used for current calcs; always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		computeITerminal();  // get present value of current

		switch (connection) {
		case WYE:
			Va = sol.getNodeV(nodeRef[0]).sub(sol.getNodeV(nodeRef[nConds - 1]));
			break;
		case DELTA:
			Va = sol.getNodeV(nodeRef[0]);
			break;
		}

		e = Va.sub(ITerminal[0].mult(new Complex(RThev, XThev)));
		VThevHarm = e.abs();  // establish base mag and angle
		thetaHarm = e.arg();
	}

	/**
	 * For going into dynamics mode
	 */
	@Override
	public void initStateVars() {
		setYPrimInvalid(true);  // force rebuild of YPrims
	}

	/**
	 * Dynamics mode integration routine
	 */
	@Override
	public void integrateStates() {
		// do nothing
	}

	/**
	 * Return variables one at a time
	 */
	@Override
	public double getVariable(int i) {
		int n, k;

		if (i < 0) return -9999.0;  // error return value; no state vars  FIXME throw exception

		// for now, report kWh stored and mode
		switch (i) {
		case 0:
			return getPresentIrradiance();
		case 1:
			return getPanelKW();
		case 2:
			return getTempFactor();
		case 3:
			return getEffFactor();
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = i - PVSystem.NumPVSystemVariables;
				if (k < n) return userModel.getVariable(k);
			}
			break;
		}
		return -9999.0;
	}

	@Override
	public void setVariable(int i, double value) {
		int n, k;

		if (i < 0) return;  // no variables to set

		switch (i) {
		case 0:
			setIrradiance(value);
			break;
		case 1:
			// setting this has no effect; read only
			break;
		case 2:
			// setting this has no effect; read only
			break;
		case 3:
			// setting this has no effect; read only
			break;
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = i - PVSystem.NumPVSystemVariables;
				if (k < n) {
					userModel.setVariable(k, value);
					return;
				}
			}
			break;
		}
	}

	@Override
	public void getAllVariables(double[] states) {
		for (int i = 0; i < PVSystem.NumPVSystemVariables; i++)
			states[i] = getVariable(i);

		if (userModel.exists())
			userModel.getAllVars(states[PVSystem.NumPVSystemVariables]);
	}

	@Override
	public int numVariables() {
		int num = PVSystem.NumPVSystemVariables;
		if (userModel.exists())
			num = num + userModel.numVars();
		return num;
	}

	@Override
	public String variableName(int i) {
		int n, i2;
		String[] pName;
		String result = "";

		if (i < 0) return result;

		switch (i) {
		case 0:
			return "Irradiance";
		case 1:
			return "PanelkW";
		case 2:
			return "P_TFactor";
		case 3:
			return "Efficiency";
		default:
			if (userModel.exists()) {
				pName = new String[1];
				n = userModel.numVars();
				i2 = i - PVSystem.NumPVSystemVariables;
				if (i2 < n) {
					userModel.getVarName(i2, pName);
					return pName[0];
				}
			}
			break;
		}
		return result;
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		String s;
		double V;

		s = "phases=1 conn=wye";

		// make sure voltage is line-neutral
		if (nPhases > 1 || connection != Connection.WYE) {
			V = kVPVSystemBase / DSS.SQRT3;
		} else {
			V = kVPVSystemBase;
		}

		s = s + String.format(" kV=%-.5g", V);

		if (nPhases > 1)
			s = s + String.format(" kva=%-.5g  PF=%-.5g", kVARating / nPhases, PFNominal);

		Parser.getInstance().setCommand(s);
		edit();

		super.makePosSequence();  // write out other properties
	}

	@Override
	public void setConductorClosed(int index, boolean value) {
		// just turn PVSystem element on or off
		PVSystemObjSwitchOpen = !value;
	}

	public void setPowerFactor(double value) {
		PFNominal = value;
		PFSpecified = true;
	}

	public void setPresentIrradiance(double value) {
		irradiance = value;
	}

	public void setPresentKV(double value) {
		kVPVSystemBase = value;
		switch (nPhases) {
		case 2:
			VBase = kVPVSystemBase * DSS.InvSQRT3x1000;
			break;
		case 3:
			VBase = kVPVSystemBase * DSS.InvSQRT3x1000;
			break;
		default:
			VBase = kVPVSystemBase * 1000.0;
			break;
		}
	}

	public void setPresentKVAr(double value) {
		kVArRequested = value;
	}

	private void setDragHandRegister(int reg, double value) {
		if (value > registers[reg]) registers[reg] = value;
	}

	public double getPowerFactor() {
		return PFNominal;
	}

	public double getKVARating() {
		return kVARating;
	}

	public double getKVPVSystemBase() {
		return kVPVSystemBase;
	}

	public double getKVArOut() {
		return kVArOut;
	}

	public double getKWOut() {
		return kWOut;
	}

	public double getPanelKW() {
		return panelKW;
	}

	public double getIrradiance() {
		return irradiance;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getEffFactor() {
		return effFactor;
	}

	public double getTempFactor() {
		return tempFactor;
	}

	public double getPctCutIn() {
		return pctCutIn;
	}

	public double getPctCutOut() {
		return pctCutOut;
	}

	public double getPctR() {
		return pctR;
	}

	public double getPctX() {
		return pctX;
	}

	public double getPNominalPerPhase() {
		return PNominalPerPhase;
	}

	public double getQNominalPerPhase() {
		return QNominalPerPhase;
	}

	public double getRandomMult() {
		return randomMult;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getDailyShape() {
		return dailyShape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return dailyShapeObj;
	}

	public String getDutyShape() {
		return dutyShape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return dutyShapeObj;
	}

	public String getDailyTShape() {
		return dailyTShape;
	}

	public TempShapeObj getDailyTShapeObj() {
		return dailyTShapeObj;
	}

	public String getDutyTShape() {
		return dutyTShape;
	}

	public TempShapeObj getDutyTShapeObj() {
		return dutyTShapeObj;
	}

	public String getInverterCurve() {
		return inverterCurve;
	}

	public XYCurveObj getInverterCurveObj() {
		return inverterCurveObj;
	}

	public String getPowerTempCurve() {
		return powerTempCurve;
	}

	public XYCurveObj getPowerTempCurveObj() {
		return powerTempCurveObj;
	}

	public int getFClass() {
		return FClass;
	}

	public boolean isDebugTrace() {
		return debugTrace;
	}

	public PVSystemUserModel getUserModel() {
		return userModel;
	}

	public double getVBase() {
		return VBase;
	}

	public double getVBase105() {
		return VBase105;
	}

	public double getVBase95() {
		return VBase95;
	}

	public double getVMaxPU() {
		return VMaxPU;
	}

	public double getVMinPU() {
		return VMinPU;
	}

	public String getYearlyShape() {
		return yearlyShape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return yearlyShapeObj;
	}

	public String getYearlyTShape() {
		return yearlyTShape;
	}

	public TempShapeObj getYearlyTShapeObj() {
		return yearlyTShapeObj;
	}

	public PVSystemModel getVoltageModel() {
		return voltageModel;
	}

	public double getPmpp() {
		return Pmpp;
	}

	public void setDebugTrace(boolean debugTrace) {
		this.debugTrace = debugTrace;
	}

	public void setKVArSpecified(boolean kVArSpecified) {
		this.kVArSpecified = kVArSpecified;
	}

	public void setKVARating(double kVARating) {
		this.kVARating = kVARating;
	}

	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}

	public void setPctCutIn(double pctCutIn) {
		this.pctCutIn = pctCutIn;
	}

	public void setPctCutOut(double pctCutOut) {
		this.pctCutOut = pctCutOut;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setDailyShape(String dailyShape) {
		this.dailyShape = dailyShape;
	}

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		this.dailyShapeObj = dailyShapeObj;
	}

	public void setDutyShape(String dutyShape) {
		this.dutyShape = dutyShape;
	}

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		this.dutyShapeObj = dutyShapeObj;
	}

	public void setDailyTShape(String dailyTShape) {
		this.dailyTShape = dailyTShape;
	}

	public void setDailyTShapeObj(TempShapeObj dailyTShapeObj) {
		this.dailyTShapeObj = dailyTShapeObj;
	}

	public void setDutyTShape(String dutyTShape) {
		this.dutyTShape = dutyTShape;
	}

	public void setDutyTShapeObj(TempShapeObj dutyTShapeObj) {
		this.dutyTShapeObj = dutyTShapeObj;
	}

	public void setInverterCurve(String inverterCurve) {
		this.inverterCurve = inverterCurve;
	}

	public void setInverterCurveObj(XYCurveObj inverterCurveObj) {
		this.inverterCurveObj = inverterCurveObj;
	}

	public void setFClass(int fClass) {
		FClass = fClass;
	}

	public void setPFSpecified(boolean pFSpecified) {
		PFSpecified = pFSpecified;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public void setPmpp(double pmpp) {
		Pmpp = pmpp;
	}

	public void setPctR(double pctR) {
		this.pctR = pctR;
	}

	public void setPctX(double pctX) {
		this.pctX = pctX;
	}

	public void setPNominalPerPhase(double pNominalPerPhase) {
		PNominalPerPhase = pNominalPerPhase;
	}

	public void setQNominalPerPhase(double qNominalPerPhase) {
		QNominalPerPhase = qNominalPerPhase;
	}

	public void setRandomMult(double randomMult) {
		this.randomMult = randomMult;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public void setVBase105(double vBase105) {
		VBase105 = vBase105;
	}

	public void setVBase95(double vBase95) {
		VBase95 = vBase95;
	}

	public void setVMaxPU(double vMaxPU) {
		VMaxPU = vMaxPU;
	}

	public void setVMinPU(double vMinPU) {
		VMinPU = vMinPU;
	}

	public void setYearlyShape(String yearlyShape) {
		this.yearlyShape = yearlyShape;
	}

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		this.yearlyShapeObj = yearlyShapeObj;
	}

	public void setYearlyTShape(String yearlyTShape) {
		this.yearlyTShape = yearlyTShape;
	}

	public void setYearlyTShapeObj(TempShapeObj yearlyTShapeObj) {
		this.yearlyTShapeObj = yearlyTShapeObj;
	}

	public void setPowerTempCurve(String powerTempCurve) {
		this.powerTempCurve = powerTempCurve;
	}

	public void setPowerTempCurveObj(XYCurveObj powerTempCurveObj) {
		this.powerTempCurveObj = powerTempCurveObj;
	}

	public void setVoltageModel(PVSystemModel voltageModel) {
		this.voltageModel = voltageModel;
	}

	public void setKVPVSystemBase(double kVPVSystemBase) {
		this.kVPVSystemBase = kVPVSystemBase;
	}

	public void setKVArOut(double kVArOut) {
		this.kVArOut = kVArOut;
	}

	public void setKWOut(double kWOut) {
		this.kWOut = kWOut;
	}

}

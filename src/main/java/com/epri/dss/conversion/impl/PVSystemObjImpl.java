package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.common.impl.YMatrix;
import com.epri.dss.conversion.PVSystem;
import com.epri.dss.conversion.PVSystemObj;
import com.epri.dss.conversion.PVSystemUserModel;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

public class PVSystemObjImpl extends PCElementImpl implements PVSystemObj {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private Complex[] cBuffer = new Complex[24];

	private Complex YEQ;     // at nominal
	private Complex YEQ95;   // at Vmin
	private Complex YEQ105;  // at VMax

	private boolean DebugTrace;
	private int PVSystemSolutionCount;
	private double PVSystemFundamental;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private boolean PVsystemObjSwitchOpen;
	private boolean FirstSampleAfterReset;
	private boolean PFSpecified;
	private boolean kvarSpecified;

	private double kVArating;
	private double kVPVSystemBase;
	private double kvar_out;
	private double kW_out;
	private double PanelkW;  // computed
	private double Irradiance;
	private double kvarRequested;
	private double Temperature;
	private double Pmpp;

	private double EffFactor;
	private double TempFactor;

	private boolean InverterON;
	private double pctCutIn;
	private double pctCutOut;
	private double CutInkW;
	private double CutOutkW;

	private double pctR;
	private double pctX;

	private int OpenPVSystemSolutionCount;
	private double Pnominalperphase;
	private double Qnominalperphase;
	private double RandomMult;

	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	private double TShapeValue;
	private double Thetaharm;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private File Tracefile;
	private PVSystemUserModel UserModel;  /* User-Written Models */

	private double varBase;  // Base vars per phase
	private double VBase;    // Base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double Vmaxpu;
	private double Vminpu;
	private double Vthevharm;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;

	// public members
	private int Connection;  /* 0 = line-neutral; 1 = Delta */
	private String DailyShape;  // Daily (24 HR) PVSystem element irradiance shape
	private LoadShapeObj DailyShapeObj;  // Daily PVSystem element irradianceShape for this load
	private String DutyShape;  // Duty cycle irradiance shape for changes typically less than one hour
	private LoadShapeObj DutyShapeObj;  // irradiance Shape for this PVSystem element
	private String YearlyShape;
	private LoadShapeObj YearlyShapeObj;  // Yearly irradiance Shape for this PVSystem element

	private String DailyTShape;
	private TShapeObj DailyTShapeObj;
	private String DutyTShape;
	private TShapeObj DutyTShapeObj;
	private String YearlyTShape;
	private TShapeObj YearlyTShapeObj;

	private String InverterCurve;
	private XYCurveObj InverterCurveObj;
	private String Power_TempCurve;
	private XYCurveObj Power_TempCurveObj;

	private int FClass;
	private int VoltageModel;  // Variation with voltage
	private double PFnominal;

	private double[] Registers = new double[PVSystem.NumPVSystemRegisters];
	private double[] Derivatives = new double[PVSystem.NumPVSystemRegisters];

	public PVSystemObjImpl(DSSClass ParClass, String PVSystemName) {
		super(ParClass);

		setName(PVSystemName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();  // + PVSystem_ELEMENT;  // In both PCelement and PVSystemelement list

		this.nPhases = 3;
		this.nConds  = 4;  // defaults to wye
		this.Yorder  = 0;  // To trigger an initial allocation
		this.nTerms  = 1;  // forces allocations

		this.YearlyShape        = "";
		this.YearlyShapeObj     = null;  // if YearlyShapeobj = null Then the Irradiance alway stays nominal
		this.DailyShape         = "";
		this.DailyShapeObj      = null;  // if DaillyShapeobj = null Then the Irradiance alway stays nominal
		this.DutyShape          = "";
		this.DutyShapeObj       = null;  // if DutyShapeobj = null Then the Irradiance alway stays nominal

		this.YearlyTShape       = "";
		this.YearlyTShapeObj    = null;  // if YearlyShapeobj = null Then the Temperature always stays nominal
		this.DailyTShape        = "";
		this.DailyTShapeObj     = null;  // if DaillyShapeobj = null Then the Temperature always stays nominal
		this.DutyTShape         = "";
		this.DutyTShapeObj      = null;  // if DutyShapeobj = null Then the Temperature always stays nominal

		this.InverterCurveObj   = null;
		this.Power_TempCurveObj = null;
		this.InverterCurve      = "";
		this.Power_TempCurve    = "";

		this.Connection         = 0;  // Wye (star, L-N)
		this.VoltageModel       = 1;  // Typical fixed kW negative load
		this.FClass             = 1;

		this.PVSystemSolutionCount     = -1;  // For keep track of the present solution in Injcurrent calcs
		this.OpenPVSystemSolutionCount = -1;
		this.YPrimOpenCond             = null;

		this.kVPVSystemBase   = 12.47;
		this.VBase            = 7200.0;
		this.Vminpu           = 0.90;
		this.Vmaxpu           = 1.10;
		this.VBase95          = this.Vminpu  * this.VBase;
		this.VBase105         = this.Vmaxpu  * this.VBase;
		this.Yorder           = this.nTerms * this.nConds;
		this.RandomMult       = 1.0 ;

		this.PFSpecified      = true;
		this.kvarSpecified    = false;
		this.InverterON       = true; // start with inverterON

		this.Temperature     = 25.0;
		this.Irradiance      = 1.0;  // kW/sq-m
		this.pctCutIn        = 20.0;
		this.pctCutOut       = 20.0;

		/* Output rating stuff */
		this.kW_out       = 500.0;
		this.kvar_out     = 0.0;
		this.PFnominal    = 1.0;
		this.kVArating    = 500.0;
		this.Pmpp        = 500.0;

		this.pctR         = 0.0;;
		this.pctX         = 50.0;

		this.UserModel  = new PVSystemUserModelImpl();

		this.Reg_kWh    = 0;
		this.Reg_kvarh  = 1;
		this.Reg_MaxkW  = 2;
		this.Reg_MaxkVA = 3;
		this.Reg_Hours  = 4;
		this.Reg_Price  = 5;

		this.DebugTrace = false;
		this.PVsystemObjSwitchOpen = false;
		setSpectrum("");  // override base class
		setSpectrumObj(null);

		initPropertyValues(0);
		recalcElementData();
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(0, "3");         // "phases";
		setPropertyValue(1, getBus(1));   // "bus1";

		setPropertyValue(PVSystem.propKV, String.format("%-g", kVPVSystemBase));
		setPropertyValue(PVSystem.propIrradiance, String.format("%-g", Irradiance));
		setPropertyValue(PVSystem.propPF, String.format("%-g", PFnominal));
		setPropertyValue(PVSystem.propMODEL, "1");
		setPropertyValue(PVSystem.propYEARLY, "");
		setPropertyValue(PVSystem.propDAILY, "");
		setPropertyValue(PVSystem.propDUTY, "");
		setPropertyValue(PVSystem.propTYEARLY, "");
		setPropertyValue(PVSystem.propTDAILY, "");
		setPropertyValue(PVSystem.propTDUTY, "");
		setPropertyValue(PVSystem.propCONNECTION, "wye");
		setPropertyValue(PVSystem.propKVAR, String.format("%-g", getPresentkvar()));

		setPropertyValue(PVSystem.propPCTR, String.format("%-g", pctR));
		setPropertyValue(PVSystem.propPCTX, String.format("%-g", pctX));

		setPropertyValue(PVSystem.propCLASS, "1"); //"class"

		setPropertyValue(PVSystem.propInvEffCurve, "");
		setPropertyValue(PVSystem.propTemp, String.format("%-g", Temperature));
		setPropertyValue(PVSystem.propPmpp, String.format("%-g", Pmpp));
		setPropertyValue(PVSystem.propP_T_Curve, "");
		setPropertyValue(PVSystem.propCutin, "20");
		setPropertyValue(PVSystem.propCutout, "20");

		setPropertyValue(PVSystem.propVMINPU, "0.90");
		setPropertyValue(PVSystem.propVMAXPU, "1.10");
		setPropertyValue(PVSystem.propKVA, String.format("%-g", kVArating));

		setPropertyValue(PVSystem.propUSERMODEL, "");  // Usermodel
		setPropertyValue(PVSystem.propUSERDATA, "");  // Userdata
		setPropertyValue(PVSystem.propDEBUGTRACE, "NO");

		super.initPropertyValues(PVSystem.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case PVSystem.propKV         : return String.format("%.6g", kVPVSystemBase);
		case PVSystem.propIrradiance : return String.format("%.6g", Irradiance);
		case PVSystem.propPF         : return String.format("%.6g", PFnominal);
		case PVSystem.propMODEL      : return String.format("%d",   VoltageModel);
		case PVSystem.propYEARLY     : return YearlyShape;
		case PVSystem.propDAILY      : return DailyShape;
		case PVSystem.propDUTY       : return DutyShape;

		case PVSystem.propTYEARLY    : return YearlyTShape;
		case PVSystem.propTDAILY     : return DailyTShape;
		case PVSystem.propTDUTY      : return DutyTShape;

		/*case PVSystem.propCONNECTION :;*/
		case PVSystem.propKVAR       : return String.format("%.6g", kvar_out);
		case PVSystem.propPCTR       : return String.format("%.6g", pctR);
		case PVSystem.propPCTX       : return String.format("%.6g", pctX);
		/*case PVSystem.propCLASS      = 17;*/
		case PVSystem.propInvEffCurve: return InverterCurve;
		case PVSystem.propTemp       : return String.format("%.6g", Temperature);
		case PVSystem.propPmpp       : return String.format("%.6g", Pmpp);
		case PVSystem.propP_T_Curve  : return Power_TempCurve;
		case PVSystem.propCutin      : return String.format("%.6g", pctCutIn);
		case PVSystem.propCutout     : return String.format("%.6g", pctCutOut);
		case PVSystem.propVMINPU     : return String.format("%.6g", Vminpu);
		case PVSystem.propVMAXPU     : return String.format("%.6g", Vmaxpu);
		case PVSystem.propKVA        : return String.format("%.6g", kVArating);

		case PVSystem.propUSERMODEL  : return UserModel.getName();
		case PVSystem.propUSERDATA   : return "(" + super.getPropertyValue(Index) + ")";
		/*case PVSystem.propDEBUGTRACE = 33;*/

		default:  // take the generic handler
			return super.getPropertyValue(Index);
		}
	}

	/**
	 * 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform
	 */
	public void randomize(int Opt) {
		switch (Opt) {
		case 0:
			RandomMult = 1.0;
		case DSSGlobals.GAUSSIAN:
			RandomMult = MathUtil.gauss(YearlyShapeObj.getMean(), YearlyShapeObj.getStdDev());
		case DSSGlobals.UNIFORM:
			RandomMult = Math.random();  // number between 0 and 1.0
		case DSSGlobals.LOGNORMAL:
			RandomMult = MathUtil.quasiLognormal(YearlyShapeObj.getMean());
		}
	}

	private void calcDailyMult(double Hr) {
		if (DailyShapeObj != null) {
			ShapeFactor = DailyShapeObj.getMult(Hr);
		} else {
			ShapeFactor = CDOUBLEONE;  // Default to no variation
		}
	}

	private void calcDailyTemperature(double Hr) {
		if (DailyTShapeObj != null) {
			TShapeValue = DailyTShapeObj.getTemperature(Hr);
		} else {
			TShapeValue = 1.0;  // Default to no variation
		}
	}

	private void calcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			ShapeFactor = DutyShapeObj.getMult(Hr);
		} else {
			calcDailyMult(Hr);  // Default to daily mult if no duty curve specified
		}
	}

	private void calcDutyTemperature(double Hr) {
		if (DutyTShapeObj != null) {
			TShapeValue = DutyTShapeObj.getTemperature(Hr);
		} else {
			calcDailyTemperature(Hr);  // Default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double Hr) {
		if (YearlyShapeObj != null) {
			ShapeFactor = YearlyShapeObj.getMult(Hr) ;
		} else {
			calcDailyMult(Hr);  // Defaults to daily curve
		}
	}

	private void calcYearlyTemperature(double Hr) {
		if (YearlyTShapeObj != null) {
			TShapeValue = YearlyTShapeObj.getTemperature(Hr) ;
		} else {
			calcDailyTemperature(Hr);  // Defaults to daily curve
		}
	}

	public void setNominalPVSystemOuput() {
		ShapeFactor  = CDOUBLEONE;  // init here; changed by curve routine
		TShapeValue  = 25.0;  // init here; changed by curve routine

		// Check to make sure the PVSystem element is ON
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (!(sol.isIsDynamicModel() || sol.isIsHarmonicModel())) {
			// Leave PVSystem element in whatever state it was prior to entering Dynamic mode

			// Check dispatch to see what state the PVSystem element should be in
			switch (sol.getMode()) {
			case Dynamics.SNAPSHOT:
				/* Just solve for the present kW, kvar */  // Don"t check for state change
			case Dynamics.DAILYMODE:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
			case Dynamics.YEARLYMODE:
				calcYearlyMult(sol.getDblHour());
				calcYearlyTemperature(sol.getDblHour());
			case Dynamics.MONTECARLO1:
				// do nothing yet
			case Dynamics.MONTEFAULT:
				// do nothing yet
			case Dynamics.FAULTSTUDY:
				// do nothing yet
			case Dynamics.DYNAMICMODE:
				// do nothing yet

				// Assume daily curve, if any, for the following
			case Dynamics.MONTECARLO2:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
			case Dynamics.MONTECARLO3:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
			case Dynamics.LOADDURATION1:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
			case Dynamics.LOADDURATION2:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());
			case Dynamics.PEAKDAY:
				calcDailyMult(sol.getDblHour());
				calcDailyTemperature(sol.getDblHour());

			case Dynamics.DUTYCYCLE:
				calcDutyMult(sol.getDblHour());
				calcDutyTemperature(sol.getDblHour());
			case Dynamics.AUTOADDFLAG:
				// do nothing
			}

			computekWkvar();
			Pnominalperphase   = 1000.0 * kW_out    / getNPhases();
			Qnominalperphase   = 1000.0 * kvar_out  / getNPhases();

			switch (VoltageModel) {
			case 3:
				//****  Fix this when user model gets connected in
				//YEQ = new Complex(0.0, -StoreVARs.Xd).invert();  // Gets negated in CalcYPrim

			default:
				YEQ = new Complex(Pnominalperphase, -Qnominalperphase).divide( MathUtil.sqr(VBase) );  // Vbase must be L-N for 3-phase

				if (Vminpu != 0.0) {
					YEQ95 = YEQ.divide( MathUtil.sqr(Vminpu));  // at 95% voltage
				} else {
					YEQ95 = YEQ; // Always a constant Z model
				}

				if (Vmaxpu != 0.0) {
					YEQ105 = YEQ.divide(MathUtil.sqr(Vmaxpu));  // at 105% voltage
				} else {
					YEQ105 = YEQ;
				}
			}
			/* When we leave here, all the YEQ"s are in L-N values */
		}
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		VBase95 = Vminpu * VBase;
		VBase105 = Vmaxpu * VBase;

		varBase = 1000.0 * kvar_out / nPhases;

		// values in ohms for thevenin equivalents
		RThev = pctR * 0.01 * MathUtil.sqr(getPresentkV()) / kVArating * 1000.0;
		XThev = pctX * 0.01 * MathUtil.sqr(getPresentkV()) / kVArating * 1000.0;

		CutInkW  = pctCutIn  * kVArating / 100.0;
		CutOutkW = pctCutOut * kVArating / 100.0;

		setNominalPVSystemOuput();

		/* Now check for errors.  if any of these came out nil and the string was not nil, give warning */
		if (YearlyShapeObj == null)
			if (YearlyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Yearly load shape: \""+ YearlyShape +"\" Not Found.", 563);
		if (DailyShapeObj == null)
			if (DailyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Daily load shape: \""+ DailyShape +"\" Not Found.", 564);
		if (DutyShapeObj == null)
			if (DutyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Duty load shape: \""+ DutyShape +"\" Not Found.", 565);
		if (YearlyTShapeObj == null)
			if (YearlyTShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Yearly temperature shape: \""+ YearlyTShape +"\" Not Found.", 5631);
		if (DailyTShapeObj == null)
			if (DailyTShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Daily temperature shape: \""+ DailyTShape +"\" Not Found.", 5641);
		if (DutyTShapeObj == null)
			if (DutyTShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Duty temperature shape: \""+ DutyTShape +"\" Not Found.", 5651);

		if (getSpectrum().length() > 0) {
			setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );
			if (getSpectrumObj() == null)
				Globals.doSimpleMsg("ERROR! Spectrum \""+getSpectrum()+"\" Not Found.", 566);
		} else {
			setSpectrumObj(null);
		}

		// Initialize to zero - defaults to PQ PVSystem element
		// Solution object will reset after circuit modifications

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );

		/* Update any user-written models */
		if (UserModel.exists())
			UserModel.updateModel();
	}

	private void calcYPrimMatrix(CMatrix Ymatrix) {
		Complex Y, Yij;
		int i, j;
		double FreqMultiplier;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		YprimFreq = sol.getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;

		if (/*sol.isDynamicModel() ||*/ sol.isIsHarmonicModel()) {
			/* YEQ is computed from %R and %X -- inverse of Rthev + j Xthev */
			Y = YEQ;  // L-N value computed in initialization routines

			if (Connection == 1)
				Y = Y.divide(3.0);  // Convert to delta impedance
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);
			Yij = Y.negate();
			for (i = 0; i < nPhases; i++) {
				switch (Connection) {
				case 0:
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
				case 1:  /* Delta connection */
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(i, i, Y);  // put it in again
					for (j = 0; j < i; j++)  // TODO Check zero based indexing
						Ymatrix.setElemSym(i, j, Yij);
				}
			}
		} else {
			//  Regular power flow PVSystem element model

			/* YEQ is always expected as the equivalent line-neutral admittance */

			Y = YEQ.negate();  // negate for generation    YEQ is L-N quantity

			// ****** Need to modify the base admittance for real harmonics calcs
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);

			switch (Connection) {
			case 0:  // Wye
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
				}
			case 1:  // Delta  or L-L
				Y   = Y.divide(3.0); // Convert to delta impedance
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;  // TODO Check zero based indexing
					if (j >= nConds)
						j = 0;  // wrap around for closed connections
					Ymatrix.addElement(i, i, Y);
					Ymatrix.addElement(j, j, Y);
					Ymatrix.addElemSym(i, j, Yij);
				}
			}
		}
	}

	private void computeInverterPower() {
		double kVA_Gen;
		double EffFactor = 1.0;
		double kW_Out = 0.0;

		// Determine state of the inverter
		if (InverterON) {
			if (PanelkW < CutOutkW)
				InverterON = false;
		} else {
			if (PanelkW >= CutInkW)
				InverterON = true;
		}

		// Set inverter output. Defaults to 100% of the panelkW if no efficiency curve spec'd
		if (InverterON) {
			if (InverterCurveObj != null)
				EffFactor = InverterCurveObj.getYValue(PanelkW / kVArating);  // pu eff vs pu power
			kW_Out = PanelkW * EffFactor;
		} else {
			kW_Out = 0.0;
		}

		// kvar value
		if (PFSpecified) {
			if (PFnominal == 1.0) {
				kvar_out = 0.0;
			} else {
				kvar_out = kW_out * Math.sqrt(1.0 / MathUtil.sqr(PFnominal) - 1.0) * Math.signum(PFnominal);
				// if pf is negative, make sure kvar has opposite sign of kW
				// kW will always be positive
			}
		} else {  // kvar is specified
			kvar_out = kvarRequested;
		}

		// Limit kvar so that kVA of inverter is not exceeded
		kVA_Gen = Math.sqrt(MathUtil.sqr(kW_out) + MathUtil.sqr(kvar_out));
		if (kVA_Gen > kVArating) {
			if (kW_out > kVArating) {
				kW_out   = kVArating;
				kvar_out = 0.0;
			} else {
				kvar_out = Math.sqrt(MathUtil.sqr(kVArating) - MathUtil.sqr(kW_Out)) * Math.signum(kvar_out);
			}
		}
	}

	private void computekWkvar() {
		computePanelPower();  // apply irradiance
		computeInverterPower();  // apply inverter eff after checking for cutin/cutout
	}

	private void computePanelPower() {
		TempFactor = 1.0;
		if (Power_TempCurveObj != null)
			TempFactor = Power_TempCurveObj.getYValue(TShapeValue);  // pu Pmpp vs T (actual)

		PanelkW = Irradiance * ShapeFactor.getReal() * Pmpp * TempFactor;
	}

	@Override
	public void calcYPrim() {
		// Build only shunt Yprim
		// Build a dummy Yprim Series so that CalcV Does not fail
		if (isYprimInvalid()) {
			if (YPrim_Shunt != null) YPrim_Shunt = null;
			YPrim_Shunt = new CMatrixImpl(Yorder);
			if (YPrim_Series != null) YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Shunt.clear();
			YPrim_Series.clear();
			YPrim.clear();
		}

		setNominalPVSystemOuput();
		calcYPrimMatrix(YPrim_Shunt);

		// Set YPrim_Series based on diagonals of YPrim_shunt so that calcVoltages doesn't fail
		for (int i = 0; i < Yorder; i++)
			YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrim_Shunt);

		// Account for open conductors
		super.calcYPrim();
	}

	/**
	 * Add the current into the proper location according to connection.
	 *
	 * Reverse of similar routine in load (complex negates are switched).
	 */
	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {
		switch (Connection) {
		case 0:  // Wye
			TermArray[i] = TermArray[i].add(Curr);
			TermArray[nConds] = TermArray[nConds].add(Curr.negate());  // neutral
		case 1:  // delta
			TermArray[i] = TermArray[i].add(Curr);
			int j = i + 1;
			if (j >= nConds) j = 0;  // wrap
			TermArray[j] = TermArray[j].add(Curr.negate());
		}
	}

	private void writeTraceRecord(String s) {
		int i;
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (!Globals.isInShowResults()) {
				FileWriter TraceStream = new FileWriter(Tracefile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(String.format("%-.g, %d, %-.g, ",
						Globals.getActiveCircuit().getSolution().getDynaVars().t,
						Globals.getActiveCircuit().getSolution().getIteration(),
						Globals.getActiveCircuit().getLoadMultiplier()) +
						Utilities.getSolutionModeID() + ", " +
						Utilities.getLoadModel() + ", " +
						VoltageModel + ", " +
						(Qnominalperphase * 3.0 / 1.0e6) + ", " +
						(Pnominalperphase * 3.0 / 1.0e6) + ", " +
						s + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getInjCurrent()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getIterminal()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getVterminal()[i].abs() + ", ");

				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			}
		} catch (Exception e) {
			// FIXME handle exception
		}
	}

	/**
	 * Compute total terminal current for constant PQ.
	 */
	private void doConstantPQPVsystemObj() {
		int i;
		Complex Curr = null, V;
		double VMag;

		// Treat this just like the Load model

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		zeroITerminal();

		calcVTerminalPhase();  // get actual voltage across each phase of the load
		for (i = 0; i < getNPhases(); i++) {
			V    = Vterminal[i];
			VMag = V.abs();

			switch (Connection) {
			case 0:  /* Wye */
				if (VMag <= VBase95) {
					Curr = YEQ95.multiply(V);  // Below 95% use an impedance model
				} else if (VMag > VBase105) {
					Curr = YEQ105.multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(Pnominalperphase, Qnominalperphase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}

			case 1:  /* Delta */
				VMag = VMag / DSSGlobals.SQRT3;  // L-N magnitude
				if (VMag <= VBase95) {
					Curr = YEQ95.divide(3.0).multiply(V);  // Below 95% use an impedance model
				} else if (VMag > VBase105) {
					Curr = YEQ105.divide(3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(Pnominalperphase, Qnominalperphase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Constant Z model
	 */
	private void doConstantZPVsystemObj() {
		int i;
		Complex Curr, YEQ2;

		// Assume YEQ is kept up to date
		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();
		if (Connection == 0) {
			YEQ2 = YEQ;
		} else {
			YEQ2 = YEQ.divide(3.0);
		}

		for (i = 0; i < getNPhases(); i++) {
			Curr = YEQ2.multiply(Vterminal[i]);   // YEQ is always line to neutral
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from User-written model
	 */
	private void doUserModel() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array

		if (UserModel.exists()) {  // Check automatically selects the usermodel If true
			UserModel.calc(Vterminal, Iterminal);
			setITerminalUpdated(true);
			// Negate currents from user model for power flow PVSystem element model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent()[i].add( Iterminal[i].negate() );
		} else {
			Globals.doSimpleMsg("PVSystem." + getName() + " model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute Total Current and add into InjTemp
	 *
	 * For now, just assume the PVSystem element is constant power
	 * for the duration of the dynamic simulation.
	 */
	private void doDynamicMode() {
		doConstantPQPVsystemObj();
	}

	/**
	 * Compute Injection Current Only when in harmonics mode
	 *
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
	 * Vd is the fundamental frequency voltage behind Xd' for phase 1
	 */
	private void doHarmonicMode() {
		int i;
		Complex E;
		double PVSystemHarmonic;

		computeVterminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		PVSystemHarmonic = sol.getFrequency() / PVSystemFundamental;
		if (getSpectrumObj() != null) {
			E = getSpectrumObj().getMult(PVSystemHarmonic).multiply(Vthevharm);  // Get base harmonic magnitude
		} else {
			E = Complex.ZERO;
		}

		E = Utilities.rotatePhasorRad(E, PVSystemHarmonic, Thetaharm);  // Time shift by fundamental frequency phase shift
		for (i = 0; i < nPhases; i++) {
			cBuffer[i] = E;
			if (i < nPhases)  // TODO Check zero based indexing
				E = Utilities.rotatePhasorDeg(E, PVSystemHarmonic, -120.0);  // Assume 3-phase PVSystem element
		}

		/* Handle Wye Connection */
		if (Connection == 0)
			cBuffer[nConds] = Vterminal[nConds];  // assume no neutral injection voltage

		/* Inj currents = Yprim (E) */
		YPrim.MVMult(getInjCurrent(), cBuffer);
	}

	private void calcVTerminalPhase() {
		int i, j;

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		/* Establish phase voltages and stick in Vterminal */
		switch (Connection) {
		case 0:
			for (i = 0; i < nPhases; i++)
				Vterminal[i] = sol.vDiff(NodeRef[i], NodeRef[nConds]);

		case 1:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;  // TODO Check zero based indexing
				if (j >= nConds) j = 0;
				Vterminal[i] = sol.vDiff(NodeRef[i], NodeRef[j]);
			}
		}

		PVSystemSolutionCount = sol.getSolutionCount();
	}

	/*private void calcVterminal()*/

	/**
	 * This is where the power gets computed.
	 *
	 * Calculates PVSystem element current and adds it properly into the injcurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag)
	 */
	private void calcPVSystemModelContribution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isIsDynamicModel()) {
			doDynamicMode();
		} else if (sol.isIsHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute currents and put into InjTemp array;
			switch (VoltageModel) {
			case 1:
				doConstantPQPVsystemObj();
			case 2:
				doConstantZPVsystemObj();
			case 3:
				doUserModel();
			default:
				doConstantPQPVsystemObj();  // for now, until we implement the other models.
			}
		}

		/* When this is done, ITerminal is up to date */
	}

	/**
	 * Difference between currents in YPrim and total current.
	 */
	private void calcInjCurrentArray() {
		// now get injection currents
		if (PVsystemObjSwitchOpen) {
			zeroInjCurrent();
		} else {
			calcPVSystemModelContribution();
		}
	}

	/**
	 * Compute total currents.
	 */
	@Override
	public void getTerminalCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (IterminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!PVsystemObjSwitchOpen)
				calcPVSystemModelContribution();  // Adds totals in Iterminal as a side effect
		}
		super.getTerminalCurrents(Curr);

		if (DebugTrace)
			writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (sol.isLoadsNeedUpdating())
			setNominalPVSystemOuput();  // Set the nominal kW, etc for the type of solution being Done

		calcInjCurrentArray();          // Difference between currents in YPrim and total terminal current

		if (DebugTrace)
			writeTraceRecord("Injection");

		// Add into System Injection Current Array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominal, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {

		calcInjCurrentArray();  // Difference between currents in YPrim and total current

		try {
			// Copy into buffer array
			for (int i = 0; i < Yorder; i++)
				Curr[i] = getInjCurrent()[i];
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("PVSystem Object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(), "Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		int i;
		for (i = 0; i < PVSystem.NumPVSystemRegisters; i++)
			Registers[i] = 0.0;
		for (i = 0; i < PVSystem.NumPVSystemRegisters; i++)
			Derivatives[i] = 0.0;
		FirstSampleAfterReset = true;  // initialize for trapezoidal integration
	}

	private void integrate(int Reg, double Deriv, double Interval) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal Rule Integration */
			if (!FirstSampleAfterReset)
				Registers[Reg] = Registers[Reg] + 0.5 * Interval * (Deriv + Derivatives[Reg]);
		} else {  /* Plain Euler integration */
			Registers[Reg] = Registers[Reg] + Interval * Deriv;
		}

		Derivatives[Reg] = Deriv;
	}

	/**
	 * Update energy from metered zone
	 */
	public void takeSample() {
		Complex S;
		double Smag;
		double HourValue;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		// Compute energy in PVSystem element branch
		if (isEnabled()) {
			S = new Complex(getPresentkW(), getPresentkvar());
			Smag = S.abs();
			HourValue = 1.0;

			if (ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case
				 * Don't need to for Gen Off and normal integration.
				 */
				if (ckt.isPositiveSequence()) {
					S    = S.multiply(3.0);
					Smag = 3.0 * Smag;
				}
				integrate            (Reg_kWh,   S.getReal(), sol.getIntervalHrs());   // Accumulate the power
				integrate            (Reg_kvarh, S.getImaginary(), sol.getIntervalHrs());
				setDragHandRegister  (Reg_MaxkW, Math.abs(S.getReal()));
				setDragHandRegister  (Reg_MaxkVA, Smag);
				integrate            (Reg_Hours, HourValue, sol.getIntervalHrs());  // Accumulate Hours in operation
				integrate            (Reg_Price, S.getReal() * ckt.getPriceSignal(), sol.getIntervalHrs());  // Accumulate Hours in operation
				FirstSampleAfterReset = false;
			}
		}
	}

	// private void setKWandKvarOut()

	/**
	 * Update PVSystem elements based on present kW and IntervalHrs variable.
	 */
	// FIXME Private method in OpenDSS
	public void updatePVSystem() {
		// do nothing
	}

	public double getPresentkW() {
		return Pnominalperphase * 0.001 * nPhases;
	}

	public double getPresentIrradiance() {
		return Irradiance * ShapeFactor.getReal();
	}

	public double getPresentkV() {
		return kVPVSystemBase;
	}

	public double getPresentkvar() {
		return Qnominalperphase * 0.001 * nPhases;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, idx;

		super.dumpProperties(F, Complete);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			idx = getParentClass().getPropertyIdxMap()[i];
			switch (idx) {
			case PVSystem.propUSERDATA:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(idx) + ")");
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(idx));
			}
		}
		F.println();
	}

	/**
	 * This routine makes a thevenin equivalent behis the reactance spec'd in %R and %X.
	 */
	@Override
	public void initHarmonics() {
		Complex E, Va = null;  // FIXME Implement connection enum

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		setYprimInvalid(true);  // Force rebuild of YPrims
		PVSystemFundamental = sol.getFrequency();  // Whatever the frequency is when we enter here.

		YEQ = new Complex(RThev, XThev).invert();      // used for current calcs  Always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		computeIterminal();  // Get present value of current

		switch (Connection) {
		case 0:  /* wye - neutral is explicit */
			Va = sol.getNodeV()[ NodeRef[0] ].subtract(sol.getNodeV()[ NodeRef[nConds] ]);
		case 1:  /*delta -- assume neutral is at zero */
			Va = sol.getNodeV()[ NodeRef[0] ];
		}

		E = Va.subtract( Iterminal[0].multiply(new Complex(RThev, XThev)) );
		Vthevharm = E.abs();   // establish base mag and angle
		Thetaharm = E.getArgument();
	}

	/**
	 * For going into dynamics mode
	 */
	@Override
	public void initStateVars() {
		setYprimInvalid(true);  // Force rebuild of YPrims
	}

	/**
	 * Dynamics mode integration routine
	 */
	@Override
	public void integrateStates() {

	}

	/**
	 * Return variables one at a time
	 */
	@Override
	public double getVariable(int i) {
		int N, k;

		if (i < 0) return -9999.0;  // error return value; no state fars  FIXME throw exception

		// for now, report kWhstored and mode
		switch (i) {
		case 0:
			return getPresentIrradiance();
		case 1:
			return getPanelkW();
		case 2:
			return getTempFactor();
		case 3:
			return getEffFactor();
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = i - PVSystem.NumPVSystemVariables;  // TODO Check zero based indexing
				if (k < N)
					return UserModel.getVariable(k);
			}
		}
		return -9999.0;
	}

	@Override
	public void setVariable(int i, double Value) {
		int N, k;

		if (i < 0) return;  // No variables to set

		switch (i) {
		case 0:
			setIrradiance(Value);
		case 1:
			// Setting this has no effect Read only
		case 2:
			// Setting this has no effect Read only
		case 3:
			// Setting this has no effect Read only
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = (i - PVSystem.NumPVSystemVariables) ;
				if (k < N) {  // TODO Check zero based indexing
					UserModel.setVariable(k, Value);
					return;
				}
			}
		}
	}

	@Override
	public void getAllVariables(double[] States) {
		for (int i = 0; i < PVSystem.NumPVSystemVariables; i++)
			States[i] = getVariable(i);

		if (UserModel.exists())
			UserModel.getAllVars(States[PVSystem.NumPVSystemVariables + 1]);
	}

	@Override
	public int numVariables() {
		int Result = PVSystem.NumPVSystemVariables;
		if (UserModel.exists())
			Result = Result + UserModel.numVars();
		return Result;
	}

	@Override
	public String variableName(int i) {
		final int BuffSize = 255;

		int n, i2;
		//char[] Buff = new char[BuffSize];
		int pName;
		String Result = "";

		if (i < 0) return Result;

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
			if (UserModel.exists()) {
				pName = 0;
				n = UserModel.numVars();
				i2 = i - PVSystem.NumPVSystemVariables;
				if (i2 < n) {
					UserModel.getVarName(i2, pName, BuffSize);
					return String.valueOf(pName);
				}
			}
		}
		return Result;
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		String S;
		double V;

		S = "Phases=1 conn=wye";

		// Make sure voltage is line-neutral
		if ((nPhases > 1) || (Connection != 0)) {
			V = kVPVSystemBase / DSSGlobals.SQRT3;
		} else {
			V = kVPVSystemBase;
		}

		S = S + String.format(" kV=%-.5g", V);

		if (nPhases > 1)
			S = S + String.format(" kva=%-.5g  PF=%-.5g", kVArating / nPhases, PFnominal);

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();  // write out other properties
	}

	@Override
	public void setConductorClosed(int Index, boolean Value) {
		// Just turn PVSystem element on or off
		if (Value) {
			PVsystemObjSwitchOpen = false;
		} else {
			PVsystemObjSwitchOpen = true;
		}
	}

	public void setPowerFactor(double Value) {
		PFnominal = Value;
		PFSpecified = true;
	}

	public void setPresentIrradiance(double Value) {
		Irradiance = Value;
	}

	public void setPresentkV(double Value) {
		kVPVSystemBase = Value;
		switch (nPhases) {
		case 2:
			VBase = kVPVSystemBase * DSSGlobals.InvSQRT3x1000;
		case 3:
			VBase = kVPVSystemBase * DSSGlobals.InvSQRT3x1000;
		default:
			VBase = kVPVSystemBase * 1000.0 ;
		}
	}

	public void setPresentkvar(double Value) {
		kvarRequested = Value;
	}

	private void setDragHandRegister(int Reg, double Value) {
		if (Value > Registers[Reg])
			Registers[Reg] = Value;
	}

	public double getPowerFactor() {
		return PFnominal;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public String getDailyShape() {
		return DailyShape;
	}

	public void setDailyShape(String dailyShape) {
		DailyShape = dailyShape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return DailyShapeObj;
	}

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		DailyShapeObj = dailyShapeObj;
	}

	public String getDutyShape() {
		return DutyShape;
	}

	public void setDutyShape(String dutyShape) {
		DutyShape = dutyShape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return DutyShapeObj;
	}

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		DutyShapeObj = dutyShapeObj;
	}

	public String getYearlyShape() {
		return YearlyShape;
	}

	public void setYearlyShape(String yearlyShape) {
		YearlyShape = yearlyShape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return YearlyShapeObj;
	}

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		YearlyShapeObj = yearlyShapeObj;
	}

	public String getDailyTShape() {
		return DailyTShape;
	}

	public void setDailyTShape(String dailyTShape) {
		DailyTShape = dailyTShape;
	}

	public TShapeObj getDailyTShapeObj() {
		return DailyTShapeObj;
	}

	public void setDailyTShapeObj(TShapeObj dailyTShapeObj) {
		DailyTShapeObj = dailyTShapeObj;
	}

	public String getDutyTShape() {
		return DutyTShape;
	}

	public void setDutyTShape(String dutyTShape) {
		DutyTShape = dutyTShape;
	}

	public TShapeObj getDutyTShapeObj() {
		return DutyTShapeObj;
	}

	public void setDutyTShapeObj(TShapeObj dutyTShapeObj) {
		DutyTShapeObj = dutyTShapeObj;
	}

	public String getYearlyTShape() {
		return YearlyTShape;
	}

	public void setYearlyTShape(String yearlyTShape) {
		YearlyTShape = yearlyTShape;
	}

	public TShapeObj getYearlyTShapeObj() {
		return YearlyTShapeObj;
	}

	public void setYearlyTShapeObj(TShapeObj yearlyTShapeObj) {
		YearlyTShapeObj = yearlyTShapeObj;
	}

	public String getInverterCurve() {
		return InverterCurve;
	}

	public void setInverterCurve(String inverterCurve) {
		InverterCurve = inverterCurve;
	}

	public XYCurveObj getInverterCurveObj() {
		return InverterCurveObj;
	}

	public void setInverterCurveObj(XYCurveObj inverterCurveObj) {
		InverterCurveObj = inverterCurveObj;
	}

	public String getPower_TempCurve() {
		return Power_TempCurve;
	}

	public void setPower_TempCurve(String power_TempCurve) {
		Power_TempCurve = power_TempCurve;
	}

	public XYCurveObj getPower_TempCurveObj() {
		return Power_TempCurveObj;
	}

	public void setPower_TempCurveObj(XYCurveObj power_TempCurveObj) {
		Power_TempCurveObj = power_TempCurveObj;
	}

	public int getFClass() {
		return FClass;
	}

	public void setFClass(int fClass) {
		FClass = fClass;
	}

	public int getVoltageModel() {
		return VoltageModel;
	}

	public void setVoltageModel(int voltageModel) {
		VoltageModel = voltageModel;
	}

	public double getPFnominal() {
		return PFnominal;
	}

	public void setPFnominal(double pFnominal) {
		PFnominal = pFnominal;
	}

	public double[] getRegisters() {
		return Registers;
	}

	public void setRegisters(double[] registers) {
		Registers = registers;
	}

	public double[] getDerivatives() {
		return Derivatives;
	}

	public void setDerivatives(double[] derivatives) {
		Derivatives = derivatives;
	}


	// FIXME Private members in OpenDSS.

	public Complex getYEQ() {
		return YEQ;
	}

	public void setYEQ(Complex yEQ) {
		YEQ = yEQ;
	}

	public Complex getYEQ95() {
		return YEQ95;
	}

	public void setYEQ95(Complex yEQ95) {
		YEQ95 = yEQ95;
	}

	public Complex getYEQ105() {
		return YEQ105;
	}

	public void setYEQ105(Complex yEQ105) {
		YEQ105 = yEQ105;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public int getPVSystemSolutionCount() {
		return PVSystemSolutionCount;
	}

	public void setPVSystemSolutionCount(int pVSystemSolutionCount) {
		PVSystemSolutionCount = pVSystemSolutionCount;
	}

	public double getPVSystemFundamental() {
		return PVSystemFundamental;
	}

	public void setPVSystemFundamental(double pVSystemFundamental) {
		PVSystemFundamental = pVSystemFundamental;
	}

	public boolean isPVsystemObjSwitchOpen() {
		return PVsystemObjSwitchOpen;
	}

	public void setPVsystemObjSwitchOpen(boolean pVsystemObjSwitchOpen) {
		PVsystemObjSwitchOpen = pVsystemObjSwitchOpen;
	}

	public boolean isFirstSampleAfterReset() {
		return FirstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSampleAfterReset) {
		FirstSampleAfterReset = firstSampleAfterReset;
	}

	public boolean isPFSpecified() {
		return PFSpecified;
	}

	public void setPFSpecified(boolean pFSpecified) {
		PFSpecified = pFSpecified;
	}

	public boolean isKvarSpecified() {
		return kvarSpecified;
	}

	public void setKvarSpecified(boolean kvarSpecified) {
		this.kvarSpecified = kvarSpecified;
	}

	public double getkVArating() {
		return kVArating;
	}

	public void setkVArating(double kVArating) {
		this.kVArating = kVArating;
	}

	public double getkVPVSystemBase() {
		return kVPVSystemBase;
	}

	public void setkVPVSystemBase(double kVPVSystemBase) {
		this.kVPVSystemBase = kVPVSystemBase;
	}

	public double getKvar_out() {
		return kvar_out;
	}

	public void setKvar_out(double kvar_out) {
		this.kvar_out = kvar_out;
	}

	public double getkW_out() {
		return kW_out;
	}

	public void setkW_out(double kW_out) {
		this.kW_out = kW_out;
	}

	public double getPanelkW() {
		return PanelkW;
	}

	public void setPanelkW(double panelkW) {
		PanelkW = panelkW;
	}

	public double getIrradiance() {
		return Irradiance;
	}

	public void setIrradiance(double irradiance) {
		Irradiance = irradiance;
	}

	public double getKvarRequested() {
		return kvarRequested;
	}

	public void setKvarRequested(double kvarRequested) {
		this.kvarRequested = kvarRequested;
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	public double getPmpp() {
		return Pmpp;
	}

	public void setPmpp(double pmpp) {
		Pmpp = pmpp;
	}

	public double getEffFactor() {
		return EffFactor;
	}

	public void setEffFactor(double effFactor) {
		EffFactor = effFactor;
	}

	public double getTempFactor() {
		return TempFactor;
	}

	public void setTempFactor(double tempFactor) {
		TempFactor = tempFactor;
	}

	public boolean isInverterON() {
		return InverterON;
	}

	public void setInverterON(boolean inverterON) {
		InverterON = inverterON;
	}

	public double getPctCutIn() {
		return pctCutIn;
	}

	public void setPctCutIn(double pctCutIn) {
		this.pctCutIn = pctCutIn;
	}

	public double getPctCutOut() {
		return pctCutOut;
	}

	public void setPctCutOut(double pctCutOut) {
		this.pctCutOut = pctCutOut;
	}

	public double getCutInkW() {
		return CutInkW;
	}

	public void setCutInkW(double cutInkW) {
		CutInkW = cutInkW;
	}

	public double getCutOutkW() {
		return CutOutkW;
	}

	public void setCutOutkW(double cutOutkW) {
		CutOutkW = cutOutkW;
	}

	public double getPctR() {
		return pctR;
	}

	public void setPctR(double pctR) {
		this.pctR = pctR;
	}

	public double getPctX() {
		return pctX;
	}

	public void setPctX(double pctX) {
		this.pctX = pctX;
	}

	public int getOpenPVSystemSolutionCount() {
		return OpenPVSystemSolutionCount;
	}

	public void setOpenPVSystemSolutionCount(int openPVSystemSolutionCount) {
		OpenPVSystemSolutionCount = openPVSystemSolutionCount;
	}

	public double getPnominalperphase() {
		return Pnominalperphase;
	}

	public void setPnominalperphase(double pnominalperphase) {
		Pnominalperphase = pnominalperphase;
	}

	public double getQnominalperphase() {
		return Qnominalperphase;
	}

	public void setQnominalperphase(double qnominalperphase) {
		Qnominalperphase = qnominalperphase;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public int getReg_Hours() {
		return Reg_Hours;
	}

	public void setReg_Hours(int reg_Hours) {
		Reg_Hours = reg_Hours;
	}

	public int getReg_kvarh() {
		return Reg_kvarh;
	}

	public void setReg_kvarh(int reg_kvarh) {
		Reg_kvarh = reg_kvarh;
	}

	public int getReg_kWh() {
		return Reg_kWh;
	}

	public void setReg_kWh(int reg_kWh) {
		Reg_kWh = reg_kWh;
	}

	public int getReg_MaxkVA() {
		return Reg_MaxkVA;
	}

	public void setReg_MaxkVA(int reg_MaxkVA) {
		Reg_MaxkVA = reg_MaxkVA;
	}

	public int getReg_MaxkW() {
		return Reg_MaxkW;
	}

	public void setReg_MaxkW(int reg_MaxkW) {
		Reg_MaxkW = reg_MaxkW;
	}

	public int getReg_Price() {
		return Reg_Price;
	}

	public void setReg_Price(int reg_Price) {
		Reg_Price = reg_Price;
	}

	public Complex getShapeFactor() {
		return ShapeFactor;
	}

	public void setShapeFactor(Complex shapeFactor) {
		ShapeFactor = shapeFactor;
	}

	public double getTShapeValue() {
		return TShapeValue;
	}

	public void setTShapeValue(double tShapeValue) {
		TShapeValue = tShapeValue;
	}

	public double getThetaharm() {
		return Thetaharm;
	}

	public void setThetaharm(double thetaharm) {
		Thetaharm = thetaharm;
	}

	public File getTracefile() {
		return Tracefile;
	}

	public void setTracefile(File tracefile) {
		Tracefile = tracefile;
	}

	public PVSystemUserModel getUserModel() {
		return UserModel;
	}

	public void setUserModel(PVSystemUserModel userModel) {
		UserModel = userModel;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public double getVBase105() {
		return VBase105;
	}

	public void setVBase105(double vBase105) {
		VBase105 = vBase105;
	}

	public double getVBase95() {
		return VBase95;
	}

	public void setVBase95(double vBase95) {
		VBase95 = vBase95;
	}

	public double getVmaxpu() {
		return Vmaxpu;
	}

	public void setVmaxpu(double vmaxpu) {
		Vmaxpu = vmaxpu;
	}

	public double getVminpu() {
		return Vminpu;
	}

	public void setVminpu(double vminpu) {
		Vminpu = vminpu;
	}

	public double getVthevharm() {
		return Vthevharm;
	}

	public void setVthevharm(double vthevharm) {
		Vthevharm = vthevharm;
	}

	public CMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(CMatrix yPrimOpenCond) {
		YPrimOpenCond = yPrimOpenCond;
	}

	public double getRThev() {
		return RThev;
	}

	public void setRThev(double rThev) {
		RThev = rThev;
	}

	public double getXThev() {
		return XThev;
	}

	public void setXThev(double xThev) {
		XThev = xThev;
	}

}

package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Storage;
import com.epri.dss.conversion.StorageObj;
import com.epri.dss.conversion.StoreUserModel;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;

public class StorageObjImpl extends PCElementImpl implements StorageObj {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private static Complex[] cBuffer = new Complex[24];

	private Complex Yeq;         // at nominal
	private Complex Yeq95;       // at 95%
	private Complex Yeq105;      // at 105%
	private Complex YeqIdling;   // in shunt representing idle impedance

	private boolean DebugTrace;
	private int State;
	private boolean StateChanged;
	private boolean FirstSampleAfterReset;
	private int StorageSolutionCount;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double StorageFundamental;
	private boolean StorageObjSwitchOpen;

	private boolean kVANotSet;
	private double kVArating;
	private double kVStorageBase;
	private double kvar_out;
	private double kW_out;
	private double kvarRequested;
	private double pctIdlekW;
	private double pctIdlekvar;
	private double pctChargeEff;
	private double pctDischargeEff;
	private double ChargeEff;
	private double DischargeEff;
	private double DischargeTrigger;
	private double ChargeTrigger;
	private double ChargeTime;

	private double pctR;
	private double pctX;

	private int OpenStorageSolutionCount;
	private double PNominalPerPhase;
	private double QNominalPerPhase ;
	private double RandomMult;

	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double ThetaHarm;
	private File TraceFile;
	/* User-Written Models */
	private StoreUserModel UserModel;

	private double varBase; // Base vars per phase
	private double VBase;  // Base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double Vmaxpu;
	private double Vminpu;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double VThevhH;
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;

	/* 0 = line-neutral; 1 = Delta */
	protected int Connection;
	/* Daily (24 HR) Storage element shape */
	protected String DailyShape;
	/* Daily Storage element Shape for this load */
	protected LoadShapeObj DailyShapeObj;
	/* Duty cycle load shape for changes typically less than one hour */
	protected String DutyShape;
	/* Shape for this Storage element */
	protected LoadShapeObj DutyShapeObj;
	protected int StorageClass;
	/* Variation with voltage */
	protected int VoltageModel;
	protected double PFNominal;
	/* ='fixed' means no variation  on all the time */
	protected String YearlyShape;
	/* Shape for this Storage element */
	protected LoadShapeObj YearlyShapeObj;

	protected double kWrating;
	protected double kWhRating;
	protected double kWhStored;
	protected double kWhReserve;
	/* percent of kW rated output currently dispatched */
	protected double pctKWout;
	protected double pctKVarout;
	protected double pctKWin;
	protected double pctReserve;
	protected int DispatchMode;

	protected double[] Registers = new double[Storage.NumStorageRegisters];
	protected double[] Derivatives = new double[Storage.NumStorageRegisters];

	public StorageObjImpl(DSSClassImpl ParClass, String StorageName) {
		super(ParClass);

		setName(StorageName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); // + STORAGE_ELEMENT;  // In both PCelement and Storageelement list

		this.nPhases    = 3;
		this.nConds     = 4;  // defaults to wye
		this.Yorder     = 0;  // To trigger an initial allocation
		this.nTerms     = 1;  // forces allocations

		this.YearlyShape       = "";
		this.YearlyShapeObj    = null;  // If YearlyShapeobj = null Then the load alway stays nominal * global multipliers
		this.DailyShape        = "";
		this.DailyShapeObj     = null;  // If DaillyShapeobj = null Then the load alway stays nominal * global multipliers
		this.DutyShape         = "";
		this.DutyShapeObj      = null;  // If DutyShapeobj = null Then the load alway stays nominal * global multipliers
		this.Connection        = 0;     // Wye (star)
		this.VoltageModel      = 1;  /* Typical fixed kW negative load */
		this.StorageClass      = 1;

		this.StorageSolutionCount     = -1;  // For keep track of the present solution in Injcurrent calcs
		this.OpenStorageSolutionCount = -1;
		this.YPrimOpenCond            = null;

		this.kVStorageBase    = 12.47;
		this.VBase            = 7200.0;
		this.Vminpu           = 0.90;
		this.Vmaxpu           = 1.10;
		this.VBase95          = this.Vminpu * this.VBase;
		this.VBase105         = this.Vmaxpu * this.VBase;
		this.Yorder           = this.nTerms * this.nConds;
		this.RandomMult       = 1.0 ;

		/* Output rating stuff */
		this.kW_out       = 25.0;
		this.kvar_out     = 0.0;
		this.PFNominal    = 1.0;
		this.kWrating     = 25.0;
		this.kVArating    = this.kWrating *1.0;

		this.State           = Storage.STORE_IDLING;  // Idling and fully charged
		this.StateChanged    = true;  // Force building of YPrim
		this.kWhRating       = 50;
		this.kWhStored       = kWhRating;
		this.pctReserve      = 20.0;  // per cent of kWhRating
		this.kWhReserve      = kWhRating * pctReserve /100.0;
		this.pctR            = 0.0;;
		this.pctX            = 50.0;
		this.pctIdlekW       = 1.0;
		this.pctIdlekvar     = 0.0;

		this.DischargeTrigger = 0.0;
		this.ChargeTrigger    = 0.0;
		this.pctChargeEff     = 90.0;
		this.pctDischargeEff  = 90.0;
		this.pctKWout         = 100.0;
		this.pctKVarout       = 100.0;
		this.pctKWin          = 100.0;

		this.ChargeTime       = 2.0;   // 2 AM

		this.kVANotSet  = true;  // Flag to set the default value for kVA

		this.UserModel  = new StoreUserModelImpl();

		this.Reg_kWh    = 1;
		this.Reg_kvarh  = 2;
		this.Reg_MaxkW  = 3;
		this.Reg_MaxkVA = 4;
		this.Reg_Hours  = 5;
		this.Reg_Price  = 6;

		this.DebugTrace = false;
		this.StorageObjSwitchOpen = false;
		setSpectrum("");  // override base class
		setSpectrumObj(null);

		initPropertyValues(0);
		recalcElementData();
	}

	private String decodeState() {
		switch (State) {
		case Storage.STORE_CHARGING:
			return "CHARGING";
		case Storage.STORE_DISCHARGING:
			return "DISCHARGING";
		default:
			return "IDLING";
		}
	}

	/**
	 * Define default values for the properties.
	 */
	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0]      = "3";         // "phases";
		PropertyValue[1]      = getBus(1);   // "bus1";

		PropertyValue[Storage.propKV]      = String.format("%-g", kVStorageBase);
		PropertyValue[Storage.propKW]      = String.format("%-g", kW_out);
		PropertyValue[Storage.propPF]      = String.format("%-g", PFNominal);
		PropertyValue[Storage.propMODEL]     = "1";
		PropertyValue[Storage.propYEARLY]    = "";
		PropertyValue[Storage.propDAILY]     = "";
		PropertyValue[Storage.propDUTY]      = "";
		PropertyValue[Storage.propDISPMODE]  = "Default";
		PropertyValue[Storage.propIDLEKVAR]  = "0";
		PropertyValue[Storage.propCONNECTION]= "wye";
		PropertyValue[Storage.propKVAR]      = String.format("%-g", getPresentKVar());

		PropertyValue[Storage.propPCTR]      = String.format("%-g", pctR);
		PropertyValue[Storage.propPCTX]      = String.format("%-g", pctX);

		PropertyValue[Storage.propIDLEKW]    = "1";       // PERCENT
		PropertyValue[Storage.propCLASS]     = "1"; //"class"
		PropertyValue[Storage.propDISPOUTTRIG] = "0";   // 0 MEANS NO TRIGGER LEVEL
		PropertyValue[Storage.propDISPINTRIG] = "0";
		PropertyValue[Storage.propCHARGEEFF] = "90";
		PropertyValue[Storage.propDISCHARGEEFF] = "90";
		PropertyValue[Storage.propPCTKWOUT]  = "100";
		PropertyValue[Storage.propPCTKWIN]   = "100";

		PropertyValue[Storage.propVMINPU]    = "0.90";
		PropertyValue[Storage.propVMAXPU]    = "1.10";
		PropertyValue[Storage.propSTATE]     = "IDLING";
		PropertyValue[Storage.propKVA]       = String.format("%-g", kVArating);
		PropertyValue[Storage.propKWRATED]   = String.format("%-g", kWrating);
		PropertyValue[Storage.propKWHRATED]  = String.format("%-g", kWhRating);
		PropertyValue[Storage.propKWHSTORED] = String.format("%-g", kWhStored);
		PropertyValue[Storage.propPCTSTORED] = String.format("%-g", kWhStored / kWhRating * 100.0);
		PropertyValue[Storage.propPCTRESERVE]= String.format("%-g", pctReserve);
		PropertyValue[Storage.propCHARGETIME]= String.format("%-g", ChargeTime);

		PropertyValue[Storage.propUSERMODEL] = "";  // Usermodel
		PropertyValue[Storage.propUSERDATA]  = "";  // Userdata
		PropertyValue[Storage.propDEBUGTRACE]= "NO";

		super.initPropertyValues(Storage.NumPropsThisClass);
	}

	private String returnDispMode(int imode) {
		switch (imode) {
		case Storage.STORE_EXTERNALMODE:
			return "External";
		case Storage.STORE_LOADMODE:
			return "Loadshape";
		case Storage.STORE_PRICEMODE:
			return "Price";
		default:
			return "default";
		}
	}

	@Override
	public String getPropertyValue(int Index) {

		switch (Index) {
		case Storage.propKV:
			return String.format("%.6g", kVStorageBase);
		case Storage.propKW:
			return String.format("%.6g", kW_out);
		case Storage.propPF:
			return String.format("%.6g", PFNominal);
		case Storage.propMODEL:
			return String.format("%d", VoltageModel);
		case Storage.propYEARLY:
			return YearlyShape;
		case Storage.propDAILY:
			return DailyShape;
		case Storage.propDUTY:
			return DutyShape;
		case Storage.propDISPMODE:
			return returnDispMode(DispatchMode);
		case Storage.propIDLEKVAR:
			return String.format("%.6g", pctIdlekvar);
		//case Storage.propCONNECTION:;
		case Storage.propKVAR:
			return String.format("%.6g", kvar_out);
		case Storage.propPCTR:
			return String.format("%.6g", pctR);
		case Storage.propPCTX:
			return String.format("%.6g", pctX);
		case Storage.propIDLEKW:
			return String.format("%.6g", pctIdlekW);
		//case Storage.propCLASS      = 17;
		case Storage.propDISPOUTTRIG:
			return String.format("%.6g", DischargeTrigger);
		case Storage.propDISPINTRIG:
			return String.format("%.6g", ChargeTrigger);
		case Storage.propCHARGEEFF:
			return String.format("%.6g", pctChargeEff);
		case Storage.propDISCHARGEEFF:
			return String.format("%.6g", pctDischargeEff);
		case Storage.propPCTKWOUT:
			return String.format("%.6g", pctKWout);
		case Storage.propVMINPU:
			return String.format("%.6g", Vminpu);
		case Storage.propVMAXPU:
			return String.format("%.6g", Vmaxpu);
		case Storage.propSTATE:
			return decodeState();
		case Storage.propKVA:
			return String.format("%.6g", kVArating);
		case Storage.propKWRATED:
			return String.format("%.6g", kWrating);
		case Storage.propKWHRATED:
			return String.format("%.6g", kWhRating);
		case Storage.propKWHSTORED:
			return String.format("%.6g", kWhStored);
		case Storage.propPCTRESERVE:
			return String.format("%.6g", pctReserve);
		case Storage.propUSERMODEL:
			return UserModel.getName();
		case Storage.propUSERDATA:
			return "(" + super.getPropertyValue(Index) + ")";
		//case Storage.propDEBUGTRACE = 33;
		case Storage.propPCTKWIN:
			return String.format("%.6g", pctKWin);
		case Storage.propPCTSTORED:
			return String.format("%.6g", kWhStored / kWhRating * 100.0);
		case Storage.propCHARGETIME:
			return String.format("%.6g", ChargeTime);
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
			ShapeFactor = CDOUBLEONE;  // Default to no  variation
		}

		checkStateTriggerLevel(ShapeFactor.getReal());   // last recourse
	}

	private void CalcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			ShapeFactor = DutyShapeObj.getMult(Hr);
			checkStateTriggerLevel(ShapeFactor.getReal());
		} else {
			calcDailyMult(Hr);  // Default to Daily Mult If no duty curve specified
		}
	}

	private void calcYearlyMult(double Hr) {
		if (YearlyShapeObj != null) {
			ShapeFactor = YearlyShapeObj.getMult(Hr) ;
			checkStateTriggerLevel(ShapeFactor.getReal());
		} else {
			calcDailyMult(Hr);  // Defaults to Daily curve
		}
	}

	private void setKWandKvarOut() {
		switch (State) {
		case Storage.STORE_CHARGING:
			if (kWhStored < kWhRating) {
				kW_out = -kWrating * pctKWin / 100.0;
				if (PFNominal == 1.0) {
					kvar_out = 0.0;
				} else {
					kvar_out = kW_out * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				}
			} else {
				State = Storage.STORE_IDLING;   // all charged up
			}

		case Storage.STORE_DISCHARGING:
			if (kWhStored > kWhReserve) {
				kW_out = kWrating * pctKWout / 100.0;
				if (PFNominal == 1.0) {
					kvar_out = 0.0;
				} else {
					kvar_out = kW_out * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				}
			} else {
				State = Storage.STORE_IDLING;  // not enough storage to discharge
			}
		}
	}

	public void setNominalStorageOuput() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		ShapeFactor = CDOUBLEONE;  // init here; changed by curve routine

		// Check to make sure the Storage element is ON
		if (! (sol.isIsDynamicModel() || sol.isIsHarmonicModel()) ) {  // Leave Storage element in whatever state it was prior to entering Dynamic mode

			// Check dispatch to see what state the storage element should be in
			switch (DispatchMode) {
			case Storage.STORE_EXTERNALMODE:
				// Do nothing
			case Storage.STORE_LOADMODE:
				checkStateTriggerLevel(ckt.getGeneratorDispatchReference());
			case Storage.STORE_PRICEMODE:
				checkStateTriggerLevel(ckt.getPriceSignal());

			default:  // dispatch off element's loadshapes, If any

				switch (sol.getMode()) {
				case Dynamics.SNAPSHOT:
					/* Just solve for the present kW, kvar */  // Don't check for state change
				case Dynamics.DAILYMODE:
					calcDailyMult(sol.getDblHour()); // Daily dispatch curve
				case Dynamics.YEARLYMODE:
					calcYearlyMult(sol.getDblHour());
				case Dynamics.MONTECARLO1:
				case Dynamics.MONTEFAULT:
				case Dynamics.FAULTSTUDY:
				case Dynamics.DYNAMICMODE:
					// do nothing
					// Assume daily curve, if any, for the following
				case Dynamics.MONTECARLO2:
					calcDailyMult(sol.getDblHour());
				case Dynamics.MONTECARLO3:
					calcDailyMult(sol.getDblHour());
				case Dynamics.LOADDURATION1:
					calcDailyMult(sol.getDblHour());
				case Dynamics.LOADDURATION2:
					calcDailyMult(sol.getDblHour());
				case Dynamics.PEAKDAY:
					calcDailyMult(sol.getDblHour());
				case Dynamics.DUTYCYCLE:
					CalcDutyMult(sol.getDblHour());
				case Dynamics.AUTOADDFLAG:
				}

			}

			setKWandKvarOut();  // Based on State and amount of energy left in storage

			if (State == Storage.STORE_IDLING) {
				// YeqIdle will be in the Yprim matrix so set this to zero
				PNominalPerPhase = 0.0;  // -0.1 * kWRating / Fnphases;     // watts
				if (DispatchMode == Storage.STORE_EXTERNALMODE) {  // Check for requested kvar
					QNominalPerPhase = kvarRequested / nPhases * 1000.0;
				} else {
					QNominalPerPhase = 0.0;
				}
				Yeq = new Complex(PNominalPerPhase, -QNominalPerPhase).divide(Math.pow(VBase, 2));   // Vbase must be L-N for 3-phase
				Yeq95  = Yeq;
				Yeq105 = Yeq;
			} else {
				PNominalPerPhase = 1000.0 * kW_out   / nPhases;
				QNominalPerPhase = 1000.0 * kvar_out / nPhases;

				switch (VoltageModel) {
				/*  Fix this when user model gets connected in */
				case 3:
					// Yeq = new Complex(0.0, -StoreVARs.Xd)).invert();  // Gets negated in calcYPrim
				default:
					Yeq = new Complex(PNominalPerPhase, -QNominalPerPhase).divide(Math.pow(VBase, 2));   // Vbase must be L-N for 3-phase
					if (Vminpu != 0.0) {
						Yeq95 = Yeq.divide(Math.pow(Vminpu, 2));  // at 95% voltage
					} else {
						Yeq95 = Yeq;  // Always a constant Z model
					}

					if (Vmaxpu != 0.0) {
						Yeq105 = Yeq.divide(Math.pow(Vmaxpu, 2));  // at 105% voltage
					} else {
						Yeq105 = Yeq;
					}
				}
			}
			/* When we leave here, all the Yeq's are in L-N values */

		}

		// If Storage element state changes, force re-calc of Y matrix
		if (StateChanged) {
			setYprimInvalid(true);
			StateChanged = false;  // reset the flag
		}
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		VBase95  = Vminpu * VBase;
		VBase105 = Vmaxpu * VBase;

		varBase = 1000.0 * kvar_out / nPhases;

		// values in ohms for thevenin equivalents
		RThev = pctR * 0.01 * Math.pow(getPresentKV(), 2) / kVArating * 1000.0;
		XThev = pctX * 0.01 * Math.pow(getPresentKV(), 2) / kVArating * 1000.0;

		// efficiencies
		ChargeEff    = pctChargeEff    * 0.01;
		DischargeEff = pctDischargeEff * 0.01;

		YeqIdling = new Complex(pctIdlekW, pctIdlekvar).multiply( (kWrating * 10.0 / Math.pow(VBase, 2) / nPhases) );  // 10.0 = 1000/100 = kW->W/pct

		setNominalStorageOuput();

		/* Now check for errors.  If any of these came out nil and the string was not nil, give warning */
		if (YearlyShapeObj == null)
			if (YearlyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Yearly load shape: \""+ YearlyShape +"\" Not Found.", 563);
		if (DailyShapeObj == null)
			if (DailyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Daily load shape: \""+ DailyShape +"\" Not Found.", 564);
		if (DutyShapeObj == null)
			if (DutyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Duty load shape: \""+ DutyShape +"\" Not Found.", 565);

		if (getSpectrum().length() > 0) {
			setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );
			if (getSpectrumObj() == null)
				Globals.doSimpleMsg("ERROR! Spectrum \""+getSpectrum()+"\" Not Found.", 566);
		} else {
			setSpectrumObj(null);
		}

		// Initialize to Zero - defaults to PQ Storage element
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

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		YprimFreq = sol.getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;

		if (/*sol.isIsDynamicModel() ||*/ sol.isIsHarmonicModel()) {
			/* Yeq is computed from %R and %X -- inverse of Rthev + j Xthev */
			switch (State) {
			case Storage.STORE_IDLING:
				Y = YeqIdling;
			case Storage.STORE_DISCHARGING:
				Y = Yeq.negate().add(YeqIdling);
			default:
				Y = Yeq;  // L-N value computed in initialization routines
			}

			if (Connection == 1)
				Y = Y.divide(3.0); // Convert to delta impedance
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
					for (j = 0; j < i - 1; j++)  // TODO Check zero based indexing
						Ymatrix.setElemSym(i, j, Yij);
				}
			}
		} else {
			//  Regular power flow Storage element model

			/* Yeq is always expected as the equivalent line-neutral admittance */

			switch (State) {
			case Storage.STORE_IDLING:
				Y = YeqIdling;
			default:
				Y = Yeq.negate().add(YeqIdling);   // negate for generation    Yeq is L-N quantity
			}

			// ****** Need to modify the base admittance for real harmonics calcs
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);

			switch (Connection) {
			case 0:
				// WYE
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
				}

			case 1:
				// Delta  or L-L
				Y = Y.divide(3.0); // Convert to delta impedance
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j >= nConds)
						j = 0;  // wrap around for closed connections
					Ymatrix.addElement(i, i, Y);
					Ymatrix.addElement(j, j, Y);
					Ymatrix.addElemSym(i, j, Yij);
				}
			}
		}
	}

	/**
	 * Normalize time to a floating point number representing time of day if Hour > 24
	 * time should be 0 to 24.
	 */
	private double normalizeToTOD(int h, double sec) {
		int HourOfDay;

		if (h > 23) {
			HourOfDay = (h - (h / 24) * 24);
		} else {
			HourOfDay = h;
		}

		double Result = HourOfDay + sec / 3600.0;

		if (Result > 24.0)
			Result = Result - 24.0;  // wrap around

		return Result;
	}

	/**
	 * This is where we set the state of the storage element.
	 */
	private void checkStateTriggerLevel(double Level) {

		StateChanged = false;
		if ((ChargeTrigger == 0.0) && (DischargeTrigger == 0.0))
			return;

		int OldState = State;
		// First see If we want to turn off Charging or Discharging State
		switch (State) {
		case Storage.STORE_CHARGING:
			if (ChargeTrigger != 0.0)
				if ((ChargeTrigger < Level) || (kWhStored >= kWhRating))
					State = Storage.STORE_IDLING;
		case Storage.STORE_DISCHARGING:
			if (DischargeTrigger != 0.0)
				if ((DischargeTrigger > Level) || (kWhStored <= kWhReserve))
					State = Storage.STORE_IDLING;
		}

		// Now check to see If we want to turn on the opposite state
		switch (State) {
		case Storage.STORE_IDLING:
			if ((DischargeTrigger != 0.0) && (DischargeTrigger < Level) && (kWhStored > kWhReserve)) {
				State = Storage.STORE_DISCHARGING;
			} else if ((ChargeTrigger != 0.0) && (ChargeTrigger > Level) && (kWhStored < kWhRating)) {
				State = Storage.STORE_CHARGING;
			}

			// Check to see If it is time to turn the charge cycle on If it is not already on.
			if (! (State == Storage.STORE_CHARGING)) {
				if (ChargeTime > 0.0) {
					SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
					if (Math.abs( normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - ChargeTime) < sol.getDynaVars().h / 3600.0)
						State = Storage.STORE_CHARGING;
				}
			}
		}

		if (OldState != State)
			StateChanged = true;
	}

	@Override
	public void calcYPrim() {
		// Build only shunt Yprim
		// Build a dummy Yprim Series so that CalcV does not fail
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

		setNominalStorageOuput();
		calcYPrimMatrix(YPrim_Shunt);

		// Set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		for (int i = 0; i < Yorder; i++)
			YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrim_Shunt);

		// Account for open conductors
		super.calcYPrim();
	}

	/**
	 * Add the current into the proper location according to connection.
	 *
	 * Reverse of similar routine in load  (complex negates are switched).
	 */
	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {
		switch (Connection) {
		case 0:  // Wye
			TermArray[i] = TermArray[i].add(Curr);
			TermArray[nConds] = TermArray[nConds].add(Curr.negate());  // Neutral
		case 1:  // DELTA
			TermArray[i] = TermArray[i].add(Curr);
			int j = i + 1;
			if (j >= nConds)
				j = 0;
			TermArray[j] = TermArray[j].add(Curr.negate());
		}
	}

	private void writeTraceRecord(String S) {
		int i;
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			if (!Globals.isInShowResults()) {
				FileWriter TraceStream = new FileWriter(TraceFile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

				TraceBuffer.write(String.format("%-.g, %d, %-.g, ",
						ckt.getSolution().getDynaVars().t,
						ckt.getSolution().getIteration(),
						ckt.getLoadMultiplier()) +
						Utilities.getSolutionModeID() + ", " +
						Utilities.getLoadModel() + ", " +
						VoltageModel + ", " +
						(QNominalPerPhase * 3.0 / 1.0e6) + ", " +
						(PNominalPerPhase * 3.0 / 1.0e6) + ", " +
						S + ", ");

				for (i = 0; i < nPhases; i++)
					TraceBuffer.write( getInjCurrent()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write( getIterminal()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write( getVterminal()[i].abs() + ", " );
				//TraceBuffer.write(VThevMag + ", " + StoreVARs.Theta * 180.0 / Math.PI);
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Compute total terminal current for Constant PQ.
	 */
	private void doConstantPQStorageObj() {
		Complex Curr = null, V;
		double Vmag;

		// Treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		zeroITerminal();

		calcVTerminalPhase(); // Get actual voltage across each phase of the load

		for (int i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:  /* Wye */
				if (Vmag <= VBase95) {
					Curr = Yeq95.multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(PNominalPerPhase, QNominalPerPhase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}

			case 1:  /* Delta */
				Vmag = Vmag / DSSGlobals.SQRT3;  // L-N magnitude
				if (Vmag <= VBase95) {
					Curr = Yeq95.divide(3.0).multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.divide(3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(PNominalPerPhase, QNominalPerPhase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Constant Z model.
	 */
	private void doConstantZStorageObj() {
		Complex Curr, Yeq2;

		// Assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		if (Connection == 0) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = Yeq.divide(3.0);
		}

		for (int i = 0; i < nPhases; i++) {
			Curr = Yeq2.multiply(Vterminal[i]);   // Yeq is always line to neutral
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from user-written model.
	 */
	private void doUserModel() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array

		if (UserModel.exists()) {  // Check automatically selects the usermodel If true
			UserModel.calc(Vterminal, Iterminal);
			setITerminalUpdated(true);
//			SolutionObj sol = Globals.getActiveCircuit().getSolution();
			// Negate currents from user model for power flow Storage element model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent()[i].add( Iterminal[i].negate() );
		} else {
			Globals.doSimpleMsg("Storage." + getName() + " model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute Total Current and add into InjTemp.
	 *
	 * For now, just assume the storage element is constant power
	 * for the duration of the dynamic simulation.
	 */
	private void doDynamicMode() {

		doConstantPQStorageObj();

	}

	/**
	 * Compute Injection Current Only when in harmonics mode.
	 *
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
	 * Vd is the fundamental frequency voltage behind Xd" for phase 1.
	 */
	private void doHarmonicMode() {
		Complex E;
		double StorageHarmonic;

		computeVterminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		StorageHarmonic = sol.getFrequency() / StorageFundamental;
		if (getSpectrumObj() != null) {
			E = getSpectrumObj().getMult(StorageHarmonic).multiply(VThevhH); // Get base harmonic magnitude
		} else {
			E = Complex.ZERO;
		}

		Utilities.rotatePhasorRad(E, StorageHarmonic, ThetaHarm);  // Time shift by fundamental frequency phase shift
		for (int i = 0; i < nPhases; i++) {
			cBuffer[i] = E;
			if (i < nPhases)
				Utilities.rotatePhasorDeg(E, StorageHarmonic, -120.0);  // Assume 3-phase Storage element
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
				j = i + 1;
				if (j >= nConds)
					j = 0;
				Vterminal[i] = sol.vDiff(NodeRef[i], NodeRef[j]);
			}
		}

		StorageSolutionCount = sol.getSolutionCount();
	}

//	/**
//	 * Put terminal voltages in an array.
//	 */
//	private void calcVterminal() {
//		computeVterminal();
//		StorageSolutionCount = DSSGlobals.getInstance().getActiveCircuit().getSolution().getSolutionCount();
//	}

	/**
	 * Calculates Storage element current and adds it properly into the injcurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag).
	 */
	private void calcStorageModelContribution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isIsDynamicModel()) {
			doDynamicMode();
		} else if (sol.isIsHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// Compute currents and put into InjTemp array;
			switch (VoltageModel) {
			case 1:
				doConstantPQStorageObj();
			case 2:
				doConstantZStorageObj();
			case 3:
				doUserModel();
			default:
				doConstantPQStorageObj();  // for now, until we implement the other models.
			}
		}

		/* When this is done, ITerminal is up to date */
	}

	/**
	 * Difference between currents in YPrim and total current.
	 */
	private void calcInjCurrentArray() {
		// Now get injection currents
		if (StorageObjSwitchOpen) {
			zeroInjCurrent();
		} else {
			calcStorageModelContribution();
		}
	}

	/**
	 * Compute total currents.
	 */
	@Override
	protected void getTerminalCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (IterminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!StorageObjSwitchOpen)
				calcStorageModelContribution();  // Adds totals in Iterminal as a side effect
			super.getTerminalCurrents(Curr);
		}

		if (DebugTrace)
			writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (sol.isLoadsNeedUpdating())
			setNominalStorageOuput();  // Set the nominal kW, etc for the type of solution being Done

		calcInjCurrentArray();  // Difference between currents in YPrim and total terminal current

		if (DebugTrace)
			writeTraceRecord("Injection");

		// Add into System Injection Current Array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {

		calcInjCurrentArray();  // Difference between currents in YPrim and total current

		try {
			// Copy into buffer array
			for (int i = 0; i < Yorder; i++)
				Curr[i] = getInjCurrent()[i];
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("Storage Object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(), "Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		int i;
		for (i = 0; i < Storage.NumStorageRegisters; i++)
			Registers[i] = 0.0;
		for (i = 0; i < Storage.NumStorageRegisters; i++)
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
	 * Update energy from metered zone.
	 */
	public void takeSample() {
		Complex S;
		double Smag;
		double HourValue;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// Compute energy in Storage element branch
		if (isEnabled()) {

			// Only tabulate discharge hours
			if (State == Storage.STORE_DISCHARGING) {
				S = new Complex(getPresentkW(), getPresentKVar());
				Smag = S.abs();
				HourValue = 1.0;
			} else {
				S = Complex.ZERO;
				Smag = 0.0;
				HourValue = 0.0;
			}

			if ((State == Storage.STORE_DISCHARGING) || ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case
				 * Don't need to for Gen Off and normal integration
				 */
				SolutionObj sol = ckt.getSolution();

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

	/**
	 * Update Storage elements based on present kW and IntervalHrs variable.
	 */
	// FIXME Private method in OpenDSS
	public void updateStorage() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		switch (State) {
		case Storage.STORE_DISCHARGING:
			kWhStored = kWhStored - getPresentkW() * sol.getIntervalHrs() / DischargeEff;
			if (kWhStored < kWhReserve) {
				kWhStored = kWhReserve;
				State = Storage.STORE_IDLING;  // It's empty Turn it off
				StateChanged = true;
			}

		case Storage.STORE_CHARGING:
			kWhStored = kWhStored - getPresentkW() * sol.getIntervalHrs() * ChargeEff;
			if (kWhStored > kWhRating) {
				kWhStored = kWhRating;
				State = Storage.STORE_IDLING;  // It's full turn it off
				StateChanged = true;
			}
		}
	}

	public double getPresentkW() {
		return PNominalPerPhase * 0.001 * nPhases;
	}

	public double getPresentKV() {
		return kVStorageBase;
	}

	public double getPresentKVar() {
		return QNominalPerPhase * 0.001 * nPhases;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, idx;

		super.dumpProperties(F, Complete);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			idx = getParentClass().getPropertyIdxMap()[i] ;
			switch (idx) {
			case Storage.propUSERDATA:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + PropertyValue[idx] + ")");
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + PropertyValue[idx]);
			}
		}
		F.println();
	}

	/**
	 * This routine makes a Thevenin equivalent behis the reactance spec'd in %R and %X
	 */
	@Override
	public void initHarmonics() {
		Complex E, Va = null;

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		setYprimInvalid(true);  // Force rebuild of YPrims
		StorageFundamental = sol.getFrequency();  // Whatever the frequency is when we enter here.

		Yeq = new Complex(RThev, XThev).invert();      // used for current calcs  Always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		if (State == Storage.STORE_DISCHARGING) {
			computeIterminal();  // Get present value of current

			switch (Connection) {
			case 0:  /* wye - neutral is explicit */
				Va = sol.getNodeV()[NodeRef[0]].subtract( sol.getNodeV()[NodeRef[nConds]] );
			case 1:  /* delta -- assume neutral is at zero */
				Va = sol.getNodeV()[NodeRef[0]];
			}

			E = Va.subtract( Iterminal[0].multiply(new Complex(RThev, XThev)) );
			VThevhH = E.abs();   // establish base mag and angle
			ThetaHarm = E.getArgument();
		} else {
			VThevhH = 0.0;
			ThetaHarm = 0.0;
		}
	}

	/**
	 * For going into dynamics mode.
	 */
	@Override
	public void initStateVars() {
		setYprimInvalid(true);  // Force rebuild of YPrims
	}

	/**
	 * Dynamics mode integration routine.
	 */
	@Override
	public void integrateStates() {

	}

	// FIXME Private method in OpenDSS
	public int interpretState(String S) {

		switch (S.toLowerCase().charAt(0)) {
		case 'c':
			return Storage.STORE_CHARGING;
		case 'd':
			return Storage.STORE_DISCHARGING;
		default:
			return Storage.STORE_IDLING;
		}
	}

	/**
	 * Return variables one at a time.
	 */
	@Override
	public double getVariable(int i) {
		int N, k;

		if (i < 0)
			return -9999.99;

		// for now, report kWhstored and mode
		switch (i) {
		case 0:
			return kWhStored;
		case 1:
			return State;
		case 2:
			return pctKWout;
		case 3:
			return pctKWin;
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = (i - Storage.NumStorageVariables);
				if (k <= N)
					return UserModel.getVariable(k);
			}
		}
		return -9999.99;
	}

	@Override
	public void setVariable(int i, double Value) {
		int N, k;

		if (i < 0)
			return;  // No variables to set

		switch (i) {
		case 0:
			kWhStored = Value;
		case 1:
			State = (int) Value;
		case 2:
			pctKWout = Value;
		case 3:
			pctKWin = Value;
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = (i - Storage.NumStorageVariables) ;
				if (k <= N) {
					UserModel.setVariable(k, Value);
					return;
				}
			}
		}
	}

	@Override
	public void getAllVariables(double[] States) {
		for (int i = 0; i < Storage.NumStorageVariables; i++)
			States[i] = getVariable(i);

		if (UserModel.exists())
			UserModel.getAllVars(States[Storage.NumStorageVariables]);
	}

	@Override
	public int numVariables() {
		int Result = Storage.NumStorageVariables;
		if (UserModel.exists())
			Result = Result + UserModel.numVars();
		return Result;
	}

	@Override
	public String variableName(int i) {
		int BuffSize = 255;

		int n, i2;
//		char[] Buff = new char[BuffSize];
		char pName;

		if (i < 0)
			return null;

		switch (i) {
		case 0:
			return "kWh Stored";
		case 1:
			return "Storage State Flag";
		case 2:
			return "% discharge level";
		case 3:
			return "% charge level";
		default:
			if (UserModel.exists()) {
				pName = 0;
				n = UserModel.numVars();
				i2 = i - Storage.NumStorageVariables;
				if (i2 <= n) {
					UserModel.getVarName(i2, pName, BuffSize);
					return String.valueOf(pName);
				}
			}
		}
		return null;
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		String S;
		double V;

		S = "phases=1 conn=wye";

		// Make sure voltage is line-neutral
		if ((nPhases > 1) || (Connection != 0)) {
			V = kVStorageBase / DSSGlobals.SQRT3;
		} else {
			V = kVStorageBase;
		}

		S = S + String.format(" kV=%-.5g", V);

		if (nPhases > 1)
			S = S + String.format(" kWrating=%-.5g  PF=%-.5g", kWrating / nPhases, PFNominal);

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();  // write out other properties
	}

	@Override
	public void setConductorClosed(int Index, boolean Value) {
		super.setConductorClosed(Index, Value);

		// Just turn storage element on or off;

		if (Value) {
			StorageObjSwitchOpen = false;
		} else {
			StorageObjSwitchOpen = true;
		}
	}

	public void setPctKVarOut(double Value) {
		pctKVarout = Value;
		// Force recompute of target PF and requested kVAr
		setPresentKVar( kWrating * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0) * pctKVarout / 100.0 );
	}

	public void setPctKWOut(double Value) {
		pctKWout = Value;
		kW_out = pctKWout * kWrating / 100.0;
	}

	public void setPowerFactor(double Value) {
		PFNominal = Value;
		syncUpPowerQuantities();
	}

	public void setPresentKV(double Value) {
		kVStorageBase = Value;
		switch (nPhases) {
		case 2:
			VBase = kVStorageBase * DSSGlobals.InvSQRT3x1000;
		case 3:
			VBase = kVStorageBase * DSSGlobals.InvSQRT3x1000;
		default:
			VBase = kVStorageBase * 1000.0 ;
		}
	}

	public void setPresentKVar(double Value) {
		double kVA_Gen;

		kvar_out = Value;
		kvarRequested = Value;
		/* Requested kVA output */
		kVA_Gen = Math.sqrt(Math.pow(kW_out, 2) + Math.pow(kvar_out, 2)) ;
		if (kVA_Gen > kVArating)
			kVA_Gen = kVArating;  // Limit kVA to rated value
		if (kVA_Gen != 0.0) {
			setPFNominal(kW_out / kVA_Gen);
		} else {
			setPFNominal(1.0);
		}
		if ((kW_out * kvar_out) < 0.0)
			setPFNominal(-getPFNominal());
	}

	public void setPresentKW(double Value) {
		pctKWout = Value / kWhRating * 100.0;
		kW_out   = Value;
		//syncUpPowerQuantities();
	}

	public void setState(int Value) {
		State = Value;
	}

	// FIXME Private method in OpenDSS
	public void syncUpPowerQuantities() {
		// keep kvar nominal up to date with kW and PF
		if (PFNominal != 0.0) {
			kvar_out = kW_out * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			QNominalPerPhase = 1000.0 * kvar_out / nPhases;
			if (PFNominal < 0.0)
				kvar_out = -kvar_out;
			if (kVANotSet)
				kVArating = kWrating;
		}
	}

	private void setDragHandRegister(int Reg, double Value) {
		if (Value > Registers[Reg])
			Registers[Reg] = Value;
	}

	public double getPowerFactor() {
		return PFNominal;
	}

	public int getState() {
		return State;
	}

	public double getPctKVarOut() {
		return pctKVarout;
	}

	public double getPctKWOut() {
		return pctKWout;
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

	public int getStorageClass() {
		return StorageClass;
	}

	public void setStorageClass(int storageClass) {
		StorageClass = storageClass;
	}

	public int getVoltageModel() {
		return VoltageModel;
	}

	public void setVoltageModel(int voltageModel) {
		VoltageModel = voltageModel;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
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

	public double getkWrating() {
		return kWrating;
	}

	public void setkWrating(double kWrating) {
		this.kWrating = kWrating;
	}

	public double getkWhRating() {
		return kWhRating;
	}

	public void setkWhRating(double kWhRating) {
		this.kWhRating = kWhRating;
	}

	public double getkWhStored() {
		return kWhStored;
	}

	public void setkWhStored(double kWhStored) {
		this.kWhStored = kWhStored;
	}

	public double getkWhReserve() {
		return kWhReserve;
	}

	public void setkWhReserve(double kWhReserve) {
		this.kWhReserve = kWhReserve;
	}

	public double getPctKWout() {
		return pctKWout;
	}

	public void setPctKWout(double pctKWout) {
		this.pctKWout = pctKWout;
	}

	public double getPctKVarout() {
		return pctKVarout;
	}

	public void setPctKVarout(double pctKVarout) {
		this.pctKVarout = pctKVarout;
	}

	public double getPctKWin() {
		return pctKWin;
	}

	public void setPctKWin(double pctKWin) {
		this.pctKWin = pctKWin;
	}

	public double getPctReserve() {
		return pctReserve;
	}

	public void setPctReserve(double pctReserve) {
		this.pctReserve = pctReserve;
	}

	public int getDispatchMode() {
		return DispatchMode;
	}

	public void setDispatchMode(int dispatchMode) {
		DispatchMode = dispatchMode;
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

	// FIXME Private members in OpenDSS

	public Complex getYeq() {
		return Yeq;
	}

	public void setYeq(Complex yeq) {
		Yeq = yeq;
	}

	public Complex getYeq95() {
		return Yeq95;
	}

	public void setYeq95(Complex yeq95) {
		Yeq95 = yeq95;
	}

	public Complex getYeq105() {
		return Yeq105;
	}

	public void setYeq105(Complex yeq105) {
		Yeq105 = yeq105;
	}

	public Complex getYeqIdling() {
		return YeqIdling;
	}

	public void setYeqIdling(Complex yeqIdling) {
		YeqIdling = yeqIdling;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public boolean isStateChanged() {
		return StateChanged;
	}

	public void setStateChanged(boolean stateChanged) {
		StateChanged = stateChanged;
	}

	public boolean isFirstSampleAfterReset() {
		return FirstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSampleAfterReset) {
		FirstSampleAfterReset = firstSampleAfterReset;
	}

	public int getStorageSolutionCount() {
		return StorageSolutionCount;
	}

	public void setStorageSolutionCount(int storageSolutionCount) {
		StorageSolutionCount = storageSolutionCount;
	}

	public double getStorageFundamental() {
		return StorageFundamental;
	}

	public void setStorageFundamental(double storageFundamental) {
		StorageFundamental = storageFundamental;
	}

	public boolean isStorageObjSwitchOpen() {
		return StorageObjSwitchOpen;
	}

	public void setStorageObjSwitchOpen(boolean storageObjSwitchOpen) {
		StorageObjSwitchOpen = storageObjSwitchOpen;
	}

	public boolean iskVANotSet() {
		return kVANotSet;
	}

	public void setkVANotSet(boolean kVANotSet) {
		this.kVANotSet = kVANotSet;
	}

	public double getkVArating() {
		return kVArating;
	}

	public void setkVArating(double kVArating) {
		this.kVArating = kVArating;
	}

	public double getkVStorageBase() {
		return kVStorageBase;
	}

	public void setkVStorageBase(double kVStorageBase) {
		this.kVStorageBase = kVStorageBase;
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

	public double getKvarRequested() {
		return kvarRequested;
	}

	public void setKvarRequested(double kvarRequested) {
		this.kvarRequested = kvarRequested;
	}

	public double getPctIdlekW() {
		return pctIdlekW;
	}

	public void setPctIdlekW(double pctIdlekW) {
		this.pctIdlekW = pctIdlekW;
	}

	public double getPctIdlekvar() {
		return pctIdlekvar;
	}

	public void setPctIdlekvar(double pctIdlekvar) {
		this.pctIdlekvar = pctIdlekvar;
	}

	public double getPctChargeEff() {
		return pctChargeEff;
	}

	public void setPctChargeEff(double pctChargeEff) {
		this.pctChargeEff = pctChargeEff;
	}

	public double getPctDischargeEff() {
		return pctDischargeEff;
	}

	public void setPctDischargeEff(double pctDischargeEff) {
		this.pctDischargeEff = pctDischargeEff;
	}

	public double getChargeEff() {
		return ChargeEff;
	}

	public void setChargeEff(double chargeEff) {
		ChargeEff = chargeEff;
	}

	public double getDischargeEff() {
		return DischargeEff;
	}

	public void setDischargeEff(double dischargeEff) {
		DischargeEff = dischargeEff;
	}

	public double getDischargeTrigger() {
		return DischargeTrigger;
	}

	public void setDischargeTrigger(double dischargeTrigger) {
		DischargeTrigger = dischargeTrigger;
	}

	public double getChargeTrigger() {
		return ChargeTrigger;
	}

	public void setChargeTrigger(double chargeTrigger) {
		ChargeTrigger = chargeTrigger;
	}

	public double getChargeTime() {
		return ChargeTime;
	}

	public void setChargeTime(double chargeTime) {
		ChargeTime = chargeTime;
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

	public int getOpenStorageSolutionCount() {
		return OpenStorageSolutionCount;
	}

	public void setOpenStorageSolutionCount(int openStorageSolutionCount) {
		OpenStorageSolutionCount = openStorageSolutionCount;
	}

	public double getPNominalPerPhase() {
		return PNominalPerPhase;
	}

	public void setPNominalPerPhase(double pNominalPerPhase) {
		PNominalPerPhase = pNominalPerPhase;
	}

	public double getQNominalPerPhase() {
		return QNominalPerPhase;
	}

	public void setQNominalPerPhase(double qNominalPerPhase) {
		QNominalPerPhase = qNominalPerPhase;
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

	public double getThetaHarm() {
		return ThetaHarm;
	}

	public void setThetaHarm(double thetaHarm) {
		ThetaHarm = thetaHarm;
	}

	public File getTraceFile() {
		return TraceFile;
	}

	public void setTraceFile(File traceFile) {
		TraceFile = traceFile;
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

	public double getVThevhH() {
		return VThevhH;
	}

	public void setVThevhH(double vThevhH) {
		VThevhH = vThevhH;
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

	public StoreUserModel getUserModel() {
		return UserModel;
	}

	public void setUserModel(StoreUserModel userModel) {
		UserModel = userModel;
	}

}

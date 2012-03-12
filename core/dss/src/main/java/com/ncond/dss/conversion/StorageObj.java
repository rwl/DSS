package com.ncond.dss.conversion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.common.types.SolutionMode;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.MathUtil;

/**
 * The storage element is essentially a generator that can be dispatched
 * to either produce power or consume power commensurate with rating and
 * amount of stored energy.
 *
 * The storage element can also produce or absorb VArs within the kVA rating
 * of the inverter.
 * That is, a StorageController object requests kvar and the storage element
 * provides them if it has any capacity left. The storage element can
 * produce/absorb kvar while idling.
 *
 * The Storage element is assumed balanced over the no. of phases defined.
 *
 * TODO: Make connection to user model
 * TODO: Yprim for various modes
 * TODO: Define state vars and dynamics mode behaviour
 * TODO: Complete harmonics mode algorithm (generator mode is implemented)
 *
 */
@Getter @Setter
public class StorageObj extends PCElement {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private static Complex[] cBuffer = new Complex[24];

	private Complex Yeq;           // at nominal
	private Complex Yeq95;         // at 95%
	private Complex Yeq105;        // at 105%
	private Complex YeqIdling;     // in shunt representing idle impedance
        private Complex YeqDischarge;  // equiv at rated power of storage element only

	private boolean debugTrace;
	private int state;
	private boolean stateChanged;
	private boolean firstSampleAfterReset;
	private int storageSolutionCount;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model */
	private double storageFundamental;
	private boolean storageObjSwitchOpen;

	private boolean kVANotSet;
	private double kVARating;
	private double kVStorageBase;
	private double kVArOut;
	private double kWOut;
	private double kVArRequested;
	private double pctIdleKW;
	private double pctIdleKVAr;
	private double pctChargeEff;
	private double pctDischargeEff;
	private double chargeEff;
	private double dischargeEff;
	private double dischargeTrigger;
	private double chargeTrigger;
	private double chargeTime;
        private double kWhBeforeUpdate;

	private double pctR;
	private double pctX;

	private int openStorageSolutionCount;
	private double PNominalPerPhase;
	private double QNominalPerPhase ;
	private double randomMult;

	private int regHours;
	private int regKVArh;
	private int regKWh;
	private int regMaxKVA;
	private int regMaxKW;
	private int regPrice;
	private Complex shapeFactor;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model */
	private double thetaHarm;
	private File traceFile;
	/** User-written models */
	private StoreUserModel userModel;

	private double kVArBase;  // base vars per phase
	private double VBase;  // base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double VMaxPU;
	private double VMinPU;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model */
	private double VThevhH;
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;

	/** 0 = line-neutral; 1 = Delta */
	protected int connection;
	/** Daily (24 HR) storage element shape */
	protected String dailyShape;
	/** Daily storage element shape for this load */
	protected LoadShapeObj dailyShapeObj;
	/** Duty cycle load shape for changes typically less than one hour */
	protected String dutyShape;
	/** Shape for this storage element */
	protected LoadShapeObj dutyShapeObj;
	protected int storageClass;
	/** Variation with voltage */
	protected int voltageModel;
	protected double PFNominal;
	/** ='fixed' means no variation on all the time */
	protected String yearlyShape;
	/** Shape for this storage element */
	protected LoadShapeObj yearlyShapeObj;

	protected double kWRating;
	protected double kWhRating;
	protected double kWhStored;
	protected double kWhReserve;
	/** Percent of kW rated output currently dispatched */
	private double pctKWout;
	private double pctKVArOut;
	private double pctKWIn;
	private double pctReserve;
	private int dispatchMode;

	protected double[] registers = new double[Storage.NumStorageRegisters];
	protected double[] derivatives = new double[Storage.NumStorageRegisters];

	public StorageObj(DSSClass parClass, String storageName) {
		super(parClass);

		setName(storageName.toLowerCase());
		objType = parClass.getClassType(); // + STORAGE_ELEMENT;  // in both PCElement and StorageElement list

		setNumPhases(3);
		nConds = 4;  // defaults to wye
		YOrder = 0;  // to trigger an initial allocation
		setNumTerms(1);     // forces allocations

		yearlyShape    = "";
		yearlyShapeObj = null;  // if yearlyShapeObj = null then the load always stays nominal * global multipliers
		dailyShape     = "";
		dailyShapeObj  = null;  // if dailyShapeObj = null then the load always stays nominal * global multipliers
		dutyShape      = "";
		dutyShapeObj   = null;  // if dutyShapeObj = null then the load always stays nominal * global multipliers
		connection     = 0;     // Wye (star)
		voltageModel   = 1;  /* Typical fixed kW negative load */
		storageClass   = 1;

		storageSolutionCount     = -1;  // for keep track of the present solution in injCurrent calcs
		openStorageSolutionCount = -1;
		YPrimOpenCond            = null;

		kVStorageBase = 12.47;
		VBase         = 7200.0;
		VMinPU        = 0.90;
		VMaxPU        = 1.10;
		VBase95       = VMinPU * VBase;
		VBase105      = VMaxPU * VBase;
		YOrder        = nTerms * nConds;
		randomMult    = 1.0 ;

		/* Output rating stuff */
		kWOut     = 25.0;
		kVArOut   = 0.0;
		PFNominal = 1.0;
		kWRating  = 25.0;
		kVARating = kWRating *1.0;

		state        = Storage.IDLING;  // idling and fully charged
		stateChanged = true;  // force building of YPrim
		kWhRating    = 50;
		kWhStored    = kWhRating;
		kWhBeforeUpdate = kWhRating;
		pctReserve   = 20.0;  // per cent of kWhRating
		kWhReserve   = kWhRating * pctReserve /100.0;
		pctR         = 0.0;;
		pctX         = 50.0;
		pctIdleKW    = 1.0;
		pctIdleKVAr  = 0.0;

		dischargeTrigger = 0.0;
		chargeTrigger    = 0.0;
		pctChargeEff     = 90.0;
		pctDischargeEff  = 90.0;
		pctKWout         = 100.0;
		pctKVArOut       = 100.0;
		pctKWIn          = 100.0;

		chargeTime = 2.0;  // 2 AM

		kVANotSet = true;  // flag to set the default value for kVA

		userModel = new StoreUserModelImpl();

		regKWh    = 1;
		regKVArh  = 2;
		regMaxKW  = 3;
		regMaxKVA = 4;
		regHours  = 5;
		regPrice  = 6;

		debugTrace = false;
		storageObjSwitchOpen = false;
		spectrum = "";  // override base class
		spectrumObj = null;

		initPropertyValues(0);
		recalcElementData();
	}

	private String decodeState() {
		switch (state) {
		case Storage.CHARGING:
			return "CHARGING";
		case Storage.DISCHARGING:
			return "DISCHARGING";
		default:
			return "IDLING";
		}
	}

	/**
	 * Define default values for the properties.
	 */
	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "3");         // "phases";
		setPropertyValue(1, getBus(0));   // "bus1";

		setPropertyValue(Storage.KV, String.format("%-g", kVStorageBase));
		setPropertyValue(Storage.KW, String.format("%-g", kWOut));
		setPropertyValue(Storage.PF, String.format("%-g", PFNominal));
		setPropertyValue(Storage.MODEL, "1");
		setPropertyValue(Storage.YEARLY, "");
		setPropertyValue(Storage.DAILY, "");
		setPropertyValue(Storage.DUTY, "");
		setPropertyValue(Storage.DISP_MODE, "Default");
		setPropertyValue(Storage.IDLE_KVAR, "0");
		setPropertyValue(Storage.CONNECTION, "wye");
		setPropertyValue(Storage.KVAR, String.format("%-g", getPresentKVAr()));

		setPropertyValue(Storage.PCTR, String.format("%-g", pctR));
		setPropertyValue(Storage.PCTX, String.format("%-g", pctX));

		setPropertyValue(Storage.IDLE_KW, "1");  // percent
		setPropertyValue(Storage.CLASS, "1");    // "class"
		setPropertyValue(Storage.DISP_OUT_TRIG, "0");  // 0 - no trigger level
		setPropertyValue(Storage.DISP_IN_TRIG, "0");
		setPropertyValue(Storage.CHARGE_EFF, "90");
		setPropertyValue(Storage.DISCHARGE_EFF, "90");
		setPropertyValue(Storage.PCT_KW_OUT, "100");
		setPropertyValue(Storage.PCT_KW_IN, "100");

		setPropertyValue(Storage.VMIN_PU, "0.90");
		setPropertyValue(Storage.VMAX_PU, "1.10");
		setPropertyValue(Storage.STATE, "IDLING");
		setPropertyValue(Storage.KVA, String.format("%-g", kVARating));
		setPropertyValue(Storage.KW_RATED, String.format("%-g", kWRating));
		setPropertyValue(Storage.KWH_RATED, String.format("%-g", kWhRating));
		setPropertyValue(Storage.KWH_STORED, String.format("%-g", kWhStored));
		setPropertyValue(Storage.PCT_STORED, String.format("%-g", kWhStored / kWhRating * 100.0));
		setPropertyValue(Storage.PCT_RESERVE, String.format("%-g", pctReserve));
		setPropertyValue(Storage.CHARGE_TIME, String.format("%-g", chargeTime));

		setPropertyValue(Storage.USER_MODEL, "");  // UserModel
		setPropertyValue(Storage.USER_DATA, "");  // UserData
		setPropertyValue(Storage.DEBUG_TRACE, "NO");

		super.initPropertyValues(Storage.NumPropsThisClass - 1);
	}

	private String returnDispMode(int imode) {
		switch (imode) {
		case Storage.EXTERNAL_MODE:
			return "External";
		case Storage.FOLLOW:
			return "Follow";
		case Storage.LOAD_MODE:
			return "Loadshape";
		case Storage.PRICE_MODE:
			return "Price";
		default:
			return "default";
		}
	}

	@Override
	public String getPropertyValue(int index) {

		switch (index) {
		case Storage.KV:
			return String.format("%.6g", kVStorageBase);
		case Storage.KW:
			return String.format("%.6g", kWOut);
		case Storage.PF:
			return String.format("%.6g", PFNominal);
		case Storage.MODEL:
			return String.format("%d", voltageModel);
		case Storage.YEARLY:
			return yearlyShape;
		case Storage.DAILY:
			return dailyShape;
		case Storage.DUTY:
			return dutyShape;
		case Storage.DISP_MODE:
			return returnDispMode(dispatchMode);
		case Storage.IDLE_KVAR:
			return String.format("%.6g", pctIdleKVAr);
		//case Storage.propCONNECTION:;
		case Storage.KVAR:
			return String.format("%.6g", kVArOut);
		case Storage.PCTR:
			return String.format("%.6g", pctR);
		case Storage.PCTX:
			return String.format("%.6g", pctX);
		case Storage.IDLE_KW:
			return String.format("%.6g", pctIdleKW);
		//case Storage.propCLASS      = 17;
		case Storage.DISP_OUT_TRIG:
			return String.format("%.6g", dischargeTrigger);
		case Storage.DISP_IN_TRIG:
			return String.format("%.6g", chargeTrigger);
		case Storage.CHARGE_EFF:
			return String.format("%.6g", pctChargeEff);
		case Storage.DISCHARGE_EFF:
			return String.format("%.6g", pctDischargeEff);
		case Storage.PCT_KW_OUT:
			return String.format("%.6g", pctKWout);
		case Storage.VMIN_PU:
			return String.format("%.6g", VMinPU);
		case Storage.VMAX_PU:
			return String.format("%.6g", VMaxPU);
		case Storage.STATE:
			return decodeState();
		case Storage.KVA:
			return String.format("%.6g", kVARating);
		case Storage.KW_RATED:
			return String.format("%.6g", kWRating);
		case Storage.KWH_RATED:
			return String.format("%.6g", kWhRating);
		case Storage.KWH_STORED:
			return String.format("%.6g", kWhStored);
		case Storage.PCT_RESERVE:
			return String.format("%.6g", pctReserve);
		case Storage.USER_MODEL:
			return userModel.getName();
		case Storage.USER_DATA:
			return "(" + super.getPropertyValue(index) + ")";
		//case Storage.propDEBUGTRACE = 33;
		case Storage.PCT_KW_IN:
			return String.format("%.6g", pctKWIn);
		case Storage.PCT_STORED:
			return String.format("%.6g", kWhStored / kWhRating * 100.0);
		case Storage.CHARGE_TIME:
			return String.format("%.6g", chargeTime);
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

		checkStateTriggerLevel(shapeFactor.getReal());  // last recourse
	}

	private void CalcDutyMult(double hr) {
		if (dutyShapeObj != null) {
			shapeFactor = dutyShapeObj.getMult(hr);
			checkStateTriggerLevel(shapeFactor.getReal());
		} else {
			calcDailyMult(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double hr) {
		if (yearlyShapeObj != null) {
			shapeFactor = yearlyShapeObj.getMult(hr) ;
			checkStateTriggerLevel(shapeFactor.getReal());
		} else {
			calcDailyMult(hr);  // defaults to daily curve
		}
	}

	private void setKWAndKVArOut() {
		int oldState = state;

		switch (state) {
		case Storage.CHARGING:
			if (kWhStored < kWhRating) {
				switch (dispatchMode) {
				case Storage.FOLLOW:
					kWOut   = kWRating * shapeFactor.getReal();
					kVArOut = kVArBase * shapeFactor.getImaginary();  // ???
				default:
					kWOut = -kWRating * pctKWIn / 100.0;
					if (PFNominal == 1.0) {
						kVArOut = 0.0;
					} else {
						syncUpPowerQuantities();  // computes kvar_out from PF
					}
				}
			} else {
				setStorageState(Storage.IDLING);   // all charged up
			}
			break;

		case Storage.DISCHARGING:
			if (kWhStored > kWhReserve) {
				switch (dispatchMode) {
				case Storage.FOLLOW:
					kWOut   = kWRating * shapeFactor.getReal();
					kVArOut = kVArBase * shapeFactor.getImaginary();
				default:
					kWOut = kWRating * pctKWout / 100.0;
					if (PFNominal == 1.0) {
						kVArOut = 0.0;
					} else {
						syncUpPowerQuantities();  // computes kvar_out from PF
					}
				}
			} else {
				setStorageState(Storage.IDLING);   // not enough storage to discharge
			}
			break;
		}

		/* If idling output is only losses */
		if (state == Storage.IDLING) {
			kWOut = 0.0;   // -kWIdlingLosses;     Just use YeqIdling
		        kVArOut = 0.0;
		}

		if (oldState != state) stateChanged = true;
	}

	public void setNominalStorageOuput() {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		shapeFactor = CDOUBLEONE;  // init here; changed by curve routine

		// check to make sure the storage element is on
		if (! (sol.isDynamicModel() || sol.isHarmonicModel()) ) {  // leave storage element in whatever state it was prior to entering dynamic mode

			// check dispatch to see what state the storage element should be in
			switch (dispatchMode) {
			case Storage.EXTERNAL_MODE:
				// Do nothing
				break;
			case Storage.LOAD_MODE:
				checkStateTriggerLevel(ckt.getGeneratorDispatchReference());
				break;
			case Storage.PRICE_MODE:
				checkStateTriggerLevel(ckt.getPriceSignal());
				break;

			default:  // dispatch off element's load shapes, if any

				switch (sol.getMode()) {
				case SNAPSHOT:
					/* Just solve for the present kW, kvar */  // don't check for state change
					break;
				case DAILYMODE:
					calcDailyMult(sol.getDblHour());  // daily dispatch curve
					break;
				case YEARLYMODE:
					calcYearlyMult(sol.getDblHour());
					break;
				/*case MONTECARLO1:  // do nothing for these modes
					break;
				case MONTEFAULT:
					break;
				case FAULTSTUDY:
					break;
				case DYNAMICMODE:
					break;*/
				// assume daily curve, if any, for the following
				case MONTECARLO2:
					calcDailyMult(sol.getDblHour());
					break;
				case MONTECARLO3:
					calcDailyMult(sol.getDblHour());
					break;
				case LOADDURATION1:
					calcDailyMult(sol.getDblHour());
					break;
				case LOADDURATION2:
					calcDailyMult(sol.getDblHour());
					break;
				case PEAKDAY:
					calcDailyMult(sol.getDblHour());
					break;
				case DUTYCYCLE:
					CalcDutyMult(sol.getDblHour());
					break;
				case AUTOADDFLAG:
					break;
				}
				break;

			}

			setKWAndKVArOut();  // based on state and amount of energy left in storage

			/* Pnominalperphase is net at the terminal.  When discharging, the storage supplies the idling losses.
			 * When charging, the idling losses are subtracting from the amount entering the storage element. */
			PNominalPerPhase = 1000.0 * kWOut / nPhases;

			if (state == Storage.IDLING) {
				if (dispatchMode == Storage.EXTERNAL_MODE) {  // check for requested kVAr
					QNominalPerPhase = kVArRequested / nPhases * 1000.0;
				} else {
					QNominalPerPhase = 0.0;
				}
				Yeq = ComplexUtil.divide(new Complex(PNominalPerPhase, -QNominalPerPhase), Math.pow(VBase, 2));  // VBase must be L-N for 3-phase
				Yeq95  = Yeq;
				Yeq105 = Yeq;
			} else {
				QNominalPerPhase = 1000.0 * kVArOut / nPhases;

				switch (voltageModel) {
				/* Fix this when user model gets connected in */
				case 3:
					// Yeq = new Complex(0.0, -StoreVARs.Xd)).invert();  // gets negated in calcYPrim
					break;
				default:
					/* Yeq no longer used for anything other than this calculation of Yeq95, Yeq105 */
					Yeq = ComplexUtil.divide(new Complex(PNominalPerPhase, -QNominalPerPhase), Math.pow(VBase, 2));  // VBase must be L-N for 3-phase
					if (VMinPU != 0.0) {
						Yeq95 = ComplexUtil.divide(Yeq, Math.pow(VMinPU, 2));   // at 95% voltage
					} else {
						Yeq95 = Yeq;  // always a constant Z model
					}

					if (VMaxPU != 0.0) {
						Yeq105 = ComplexUtil.divide(Yeq, Math.pow(VMaxPU, 2));  // at 105% voltage
					} else {
						Yeq105 = Yeq;
					}
					break;
				}
			}
			/* When we leave here, all the Yeq's are in L-N values */
		}

		// if storage element state changes, force re-calc of Y matrix
		if (stateChanged) {
			setYPrimInvalid(true);
			stateChanged = false;  // reset the flag
		}
	}

	@Override
	public void recalcElementData() {

		VBase95  = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		kVArBase = 1000.0 * kVArOut / nPhases;  // remember this for follow mode

		// values in ohms for Thevenin equivalents
		RThev = pctR * 0.01 * Math.pow(getPresentKV(), 2) / kVARating * 1000.0;
		XThev = pctX * 0.01 * Math.pow(getPresentKV(), 2) / kVARating * 1000.0;

		// efficiencies
		chargeEff    = pctChargeEff    * 0.01;
		dischargeEff = pctDischargeEff * 0.01;

		YeqIdling = new Complex(pctIdleKW, pctIdleKVAr).multiply( (kWRating * 10.0 / Math.pow(VBase, 2) / nPhases) );  // 10.0 = 1000/100 = kW->W/pct
		YeqDischarge = new Complex((kWRating * 1000.0 / Math.pow(VBase, 2) / nPhases), 0.0);

		setNominalStorageOuput();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning */
		if (yearlyShapeObj == null)
			if (yearlyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Yearly load shape: \""+ yearlyShape +"\" not found.", 563);
		if (dailyShapeObj == null)
			if (dailyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Daily load shape: \""+ dailyShape +"\" not found.", 564);
		if (dutyShapeObj == null)
			if (dutyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Duty load shape: \""+ dutyShape +"\" not found.", 565);

		if (getSpectrum().length() > 0) {
			setSpectrumObj( (com.ncond.dss.general.SpectrumObj) DSS.spectrumClass.find(getSpectrum()) );
			if (getSpectrumObj() == null)
				DSS.doSimpleMsg("Error: Spectrum \""+getSpectrum()+"\" not found.", 566);
		} else {
			setSpectrumObj(null);
		}

		// initialize to zero - defaults to PQ storage element
		// solution object will reset after circuit modifications

		setInjCurrent( Util.resizeArray(getInjCurrent(), YOrder) );

		/* Update any user-written models */
		if (userModel.exists())
			userModel.updateModel();
	}

	private void calcYPrimMatrix(CMatrix YMatrix) {
		Complex Y, Yij;
		int i, j;
		double freqMultiplier;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		YPrimFreq = sol.getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		if (/*sol.isIsDynamicModel() ||*/ sol.isHarmonicModel()) {
			/* Yeq is computed from %R and %X -- inverse of Rthev + j Xthev */
			switch (state) {
			case Storage.CHARGING:
				Y = YeqDischarge.add(YeqIdling);
				break;
			case Storage.IDLING:
				Y = YeqIdling;
				break;
			case Storage.DISCHARGING:
				Y = YeqDischarge.negate().add(YeqIdling);
				break;
			default:
				Y = Yeq;  // L-N value computed in initialization routines
				break;
			}

			if (connection == 1)
				Y = ComplexUtil.divide(Y, 3.0);  // convert to delta impedance
			Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);
			Yij = Y.negate();
			for (i = 0; i < nPhases; i++) {
				switch (connection) {
				case 0:
					YMatrix.set(i, i, Y);
					YMatrix.add(nConds - 1, nConds - 1, Y);
					YMatrix.setSym(i, nConds - 1, Yij);
					break;
				case 1:  /* Delta connection */
					YMatrix.set(i, i, Y);
					YMatrix.add(i, i, Y);  // put it in again
					for (j = 0; j < i; j++)
						YMatrix.setSym(i, j, Yij);
					break;
				}
			}
		} else {
			// regular power flow storage element model

			/* Yeq is always expected as the equivalent line-neutral admittance */

			switch (state) {
			case Storage.CHARGING:
				Y = YeqDischarge.add(YeqIdling);
				break;
			case Storage.IDLING:
				Y = YeqIdling;
				break;
			case Storage.DISCHARGING:
				Y = YeqDischarge.negate().add(YeqIdling);
			default:
				Y = Yeq.negate().add(YeqIdling);  // negate for generation; Yeq is L-N quantity
				break;
			}

			// ****** need to modify the base admittance for real harmonics calcs
			Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);

			switch (connection) {
			case 0:
				// wye
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					YMatrix.set(i, i, Y);
					YMatrix.add(nConds - 1, nConds - 1, Y);
					YMatrix.setSym(i, nConds - 1, Yij);
				}
				break;

			case 1:
				// delta or L-L
				Y = ComplexUtil.divide(Y, 3.0);  // convert to delta impedance
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j >= nConds)
						j = 0;  // wrap around for closed connections
					YMatrix.add(i, i, Y);
					YMatrix.add(j, j, Y);
					YMatrix.addSym(i, j, Yij);
				}
				break;
			}
		}
	}

	/**
	 * Normalize time to a floating point number representing time of day if Hour > 24
	 * time should be 0 to 24.
	 */
	private double normalizeToTOD(int h, double sec) {
		int hourOfDay;
		double result;

		if (h > 23) {
			hourOfDay = (h - (h / 24) * 24);
		} else {
			hourOfDay = h;
		}

		result = hourOfDay + sec / 3600.0;

		if (result > 24.0)
			result = result - 24.0;  // wrap around

		return result;
	}

	/**
	 * This is where we set the state of the storage element.
	 */
	private void checkStateTriggerLevel(double level) {
		stateChanged = false;

		int oldState = state;

		if (dispatchMode == Storage.FOLLOW) {

			// set charge and discharge modes based on sign of load shape
			if (level > 0.0 && kWhStored > kWhReserve) {
				setStorageState(Storage.DISCHARGING);
			} else if (level < 0.0 && kWhStored < kWhRating) {
				setStorageState(Storage.CHARGING);
			} else {
				setStorageState(Storage.IDLING);
			}
		} else {
			// all other dispatch modes, just compare to trigger value

			if (chargeTrigger == 0.0 && dischargeTrigger == 0.0)
				return;

			// first see If we want to turn off charging or discharging state
			switch (getState()) {
			case Storage.CHARGING:
				if (chargeTrigger != 0.0)
					if (chargeTrigger < level || kWhStored >= kWhRating)
						setStorageState(Storage.IDLING);
				break;
			case Storage.DISCHARGING:
				if (dischargeTrigger != 0.0)
					if (dischargeTrigger > level || kWhStored <= kWhReserve)
						setStorageState(Storage.IDLING);
				break;
			}

			// now check to see if we want to turn on the opposite state
			switch (getState()) {
			case Storage.IDLING:
				if (dischargeTrigger != 0.0 && dischargeTrigger < level && kWhStored > kWhReserve) {
					setStorageState(Storage.DISCHARGING);
				} else if (chargeTrigger != 0.0 && chargeTrigger > level && kWhStored < kWhRating) {
					setStorageState(Storage.CHARGING);
				}

				// check to see if it is time to turn the charge cycle on If it is not already on
				if (!(getState() == Storage.CHARGING))
					if (chargeTime > 0.0) {
						SolutionObj sol = DSS.activeCircuit.getSolution();
						if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - chargeTime) < sol.getDynaVars().h / 3600.0)
							setStorageState(Storage.CHARGING);
					}
				break;
			}
		}

		if (oldState != state) {
			stateChanged = true;
			setYPrimInvalid(true);
		}
	}

	@Override
	public void calcYPrim() {
		// build only shunt Yprim
		// build a dummy Yprim_Series so that calcV does not fail
		if (isYprimInvalid()) {
			YPrimShunt = new CMatrix(YOrder);
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		setNominalStorageOuput();
		calcYPrimMatrix(YPrimShunt);

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		for (int i = 0; i < YOrder; i++)
			YPrimSeries.set(i, i, YPrimShunt.get(i, i).multiply(1.0e-10));

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
		case 0:  // wye
			termArray[i] = termArray[i].add(curr);
			termArray[nConds - 1] = termArray[nConds - 1].add(curr.negate());  // neutral
			break;
		case 1:  // delta
			termArray[i] = termArray[i].add(curr);
			int j = i + 1;
			if (j >= nConds)
				j = 0;
			termArray[j] = termArray[j].add(curr.negate());
			break;
		}
	}

	private void writeTraceRecord(String s) {
		int i;
		Circuit ckt = DSS.activeCircuit;

		try {
			if (!DSS.inShowResults) {
				FileWriter fw = new FileWriter(traceFile, true);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(String.format("%-.g, %d, %-.g, ",
						ckt.getSolution().getDblHour(),
						ckt.getSolution().getIteration(),
						ckt.getLoadMultiplier()) +
						Util.getSolutionModeID() + ", " +
						Util.getLoadModel() + ", " +
						voltageModel + ", " +
						(QNominalPerPhase * 3.0 / 1.0e6) + ", " +
						(PNominalPerPhase * 3.0 / 1.0e6) + ", " +
						s + ", ");

				for (i = 0; i < nPhases; i++)
					bw.write( getInjCurrent(i).abs() + ", ");
				for (i = 0; i < nPhases; i++)
					bw.write( getITerminal(i).abs() + ", ");
				for (i = 0; i < nPhases; i++)
					bw.write( getVTerminal(i).abs() + ", " );
				for (i = 0; i < numVariables(); i++)
					bw.write( String.format("%-.g, ", getVariable(i)) );
				//TraceBuffer.write(VThevMag + ", " + StoreVARs.Theta * 180.0 / Math.PI);
				bw.newLine();
				bw.close();
				fw.close();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Compute total terminal current for Constant PQ.
	 */
	private void doConstantPQStorageObj() {
		int i;
		Complex curr = null, V;
		double VMag;

		// treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		zeroITerminal();

		switch (state) {
		case Storage.IDLING:  // YPrim current is only current
			for (i = 0; i < nPhases; i++) {
				curr = getInjCurrent(i);
				putCurrInTerminalArray(ITerminal, curr.negate(), i);  // put YPrim contribution into Terminal array taking into account connection
				setITerminalUpdated(true);
				putCurrInTerminalArray(getInjCurrent(), curr.negate(), i);    // Compensation current is zero since terminal current is same as Yprim contribution
			}
		default:  // For Charging and Discharging

			calcVTerminalPhase();  // get actual voltage across each phase of the load

			for (i = 0; i < nPhases; i++) {
				V = VTerminal[i];
				VMag = V.abs();

				switch (connection) {
				case 0:  /* Wye */
					if (VMag <= VBase95) {
						curr = Yeq95.multiply(V);   // below 95% use an impedance model
					} else if (VMag > VBase105) {
						curr = Yeq105.multiply(V);  // above 105% use an impedance model
					} else {
						curr = new Complex(PNominalPerPhase, QNominalPerPhase).divide(V).conjugate();  // between 95% -105%, constant PQ
					}
					break;

				case 1:  /* Delta */
					VMag = VMag / DSS.SQRT3;  // L-N magnitude
					if (VMag <= VBase95) {
						curr = ComplexUtil.divide(Yeq95, 3.0).multiply(V);   // below 95% use an impedance model
					} else if (VMag > VBase105) {
						curr = ComplexUtil.divide(Yeq105, 3.0).multiply(V);  // above 105% use an impedance model
					} else {
						curr = new Complex(PNominalPerPhase, QNominalPerPhase).divide(V).conjugate();  // between 95% -105%, constant PQ
					}
					break;
				}

				putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
				setITerminalUpdated(true);
				putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
			}
		}
	}

	/**
	 * Constant Z model.
	 */
	private void doConstantZStorageObj() {
		Complex curr, Yeq2;

		// assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		if (connection == 0) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = ComplexUtil.divide(Yeq, 3.0);
		}

		for (int i = 0; i < nPhases; i++) {
			curr = Yeq2.multiply(VTerminal[i]);  // Yeq is always line to neutral
			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from user-written model.
	 */
	private void doUserModel() {

		calcYPrimContribution(getInjCurrent());  // init injCurrent array

		if (userModel.exists()) {  // check automatically selects the user model if true
			userModel.calc(VTerminal, ITerminal);
			setITerminalUpdated(true);
			//SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
			// negate currents from user model for power flow storage element model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent(i).add( ITerminal[i].negate() );
		} else {
			DSS.doSimpleMsg("Storage." + getName() + " model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute total current and add into injTemp.
	 *
	 * For now, just assume the storage element is constant power
	 * for the duration of the dynamic simulation.
	 */
	private void doDynamicMode() {
		doConstantPQStorageObj();
	}

	/**
	 * Compute injection current only when in harmonics mode.
	 *
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
	 * Vd is the fundamental frequency voltage behind Xd" for phase 1.
	 */
	private void doHarmonicMode() {
		Complex e;
		double storageHarmonic;

		computeVTerminal();

		SolutionObj sol = DSS.activeCircuit.getSolution();

		storageHarmonic = sol.getFrequency() / storageFundamental;
		if (getSpectrumObj() != null) {
			e = getSpectrumObj().getMult(storageHarmonic).multiply(VThevhH);  // get base harmonic magnitude
		} else {
			e = Complex.ZERO;
		}

		e = Util.rotatePhasorRad(e, storageHarmonic, thetaHarm);  // time shift by fundamental frequency phase shift
		for (int i = 0; i < nPhases; i++) {
			cBuffer[i] = e;
			if (i < nPhases)
				e = Util.rotatePhasorDeg(e, storageHarmonic, -120.0);  // assume 3-phase Storage element
		}

		/* Handle wye connection */
		if (connection == 0)
			cBuffer[nConds - 1] = VTerminal[nConds - 1];  // assume no neutral injection voltage

		/* Inj currents = Yprim (E) */
		YPrim.vMult(getInjCurrent(), cBuffer);
	}

	private void calcVTerminalPhase() {
		int i, j;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		/* Establish phase voltages and stick in VTerminal */
		switch (connection) {
		case 0:
			for (i = 0; i < nPhases; i++)
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[nConds - 1]);
			break;

		case 1:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j >= nConds)
					j = 0;
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[j]);
			}
			break;
		}

		storageSolutionCount = sol.getSolutionCount();
	}

//	/**
//	 * Put terminal voltages in an array.
//	 */
//	private void calcVterminal() {
//		computeVterminal();
//		StorageSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount();
//	}

	/**
	 * Calculates storage element current and adds it properly into the injCurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag).
	 */
	private void calcStorageModelContribution() {
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
			case 1:
				doConstantPQStorageObj();
				break;
			case 2:
				doConstantZStorageObj();
				break;
			case 3:
				doUserModel();
				break;
			default:
				doConstantPQStorageObj();  // for now, until we implement the other models.
				break;
			}
		}

		/* When this is done, ITerminal is up to date */
	}

	/**
	 * Difference between currents in YPrim and total current.
	 */
	private void calcInjCurrentArray() {
		// now get injection currents
		if (storageObjSwitchOpen) {
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
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!storageObjSwitchOpen)
				calcStorageModelContribution();  // adds totals in ITerminal as a side effect
			super.getTerminalCurrents(Curr);
		}

		if (debugTrace)
			writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (sol.loadsNeedUpdating())
			setNominalStorageOuput();  // set the nominal kW, etc for the type of solution being done

		calcInjCurrentArray();  // difference between currents in YPrim and total terminal current

		if (debugTrace)
			writeTraceRecord("Injection");

		// add into system injection current array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {

		calcInjCurrentArray();  // difference between currents in YPrim and total current

		try {
			// copy into buffer array
			for (int i = 0; i < YOrder; i++)
				curr[i] = getInjCurrent(i);
		} catch (Exception e) {
			DSS.doErrorMsg("Storage object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(), "Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		int i;
		for (i = 0; i < Storage.NumStorageRegisters; i++)
			registers[i] = 0.0;
		for (i = 0; i < Storage.NumStorageRegisters; i++)
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
	 * Update energy from metered zone.
	 */
	public void takeSample() {
		Complex S;
		double SMag;
		double hourValue;

		Circuit ckt = DSS.activeCircuit;

		// compute energy in storage element branch
		if (isEnabled()) {

			// only tabulate discharge hours
			if (state == Storage.DISCHARGING) {
				S = new Complex(getPresentKW(), getPresentKVAr());
				SMag = S.abs();
				hourValue = 1.0;
			} else {
				S = Complex.ZERO;
				SMag = 0.0;
				hourValue = 0.0;
			}

			if (state == Storage.DISCHARGING || ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case.
				 * Don't need to for gen off and normal integration.
				 */
				SolutionObj sol = ckt.getSolution();

				if (ckt.isPositiveSequence()) {
					S = S.multiply(3.0);
					SMag = 3.0 * SMag;
				}
				integrate            (regKWh,   S.getReal(), sol.getIntervalHrs());   // accumulate the power
				integrate            (regKVArh, S.getImaginary(), sol.getIntervalHrs());
				setDragHandRegister  (regMaxKW, Math.abs(S.getReal()));
				setDragHandRegister  (regMaxKVA, SMag);
				integrate            (regHours, hourValue, sol.getIntervalHrs());  // accumulate hours in operation
				integrate            (regPrice, S.getReal() * ckt.getPriceSignal(), sol.getIntervalHrs());  // accumulate hours in operation
				firstSampleAfterReset = false;
			}
		}
	}

	/**
	 * Update storage elements based on present kW and intervalHrs variable.
	 */
	// FIXME Private method in OpenDSS
	public void updateStorage() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		kWhBeforeUpdate = kWhStored;  // keep this for reporting change in storage as a variable

		switch (state) {
		case Storage.DISCHARGING:
                	/* Deplete storage by amount of idling power to achieve present kW output */
			kWhStored = kWhStored - (getPresentKW() + getKWIdlingLosses()) * sol.getIntervalHrs() / dischargeEff;
			if (kWhStored < kWhReserve) {
				kWhStored = kWhReserve;
				setStorageState(Storage.IDLING);  // it's empty turn it off
				stateChanged = true;
			}
			break;

		case Storage.CHARGING:
			/* kWIdlingLosses is always positive while presentKW is negative for charging */
			kWhStored = kWhStored - (getPresentKW() + getKWIdlingLosses()) * sol.getIntervalHrs() * chargeEff;
			if (kWhStored > kWhRating) {
				kWhStored = kWhRating;
				setStorageState(Storage.IDLING);  // it's full turn it off
				stateChanged = true;
			}
			break;
		}

		// the update is done at the end of a time step so have to force
		// a recalc of the Yprim for the next time step. Otherwise, it will stay the same.
		if (stateChanged) setYPrimInvalid(true);
	}

	public double getPresentKW() {
		return kWOut; //PNominalPerPhase * 0.001 * nPhases;
	}

	public double getKWTotalLosses() {
		switch (getState()) {
		case Storage.CHARGING:
			return Math.abs(getPower(0).getReal() * (100.0 - pctChargeEff) / 100000.0) + pctChargeEff * getKWIdlingLosses() / 100.0;  // kW
		case Storage.IDLING:
			return getKWIdlingLosses();
		case Storage.DISCHARGING:
			return Math.abs(getPower(0).getReal() * (100.0 - pctDischargeEff) / 100000.0) + (2.0 - pctChargeEff / 100.0) * getKWIdlingLosses();  // kW
		}
		return 0;
	}

	public double getKWIdlingLosses() {
		int i;

		computeVTerminal();

		double result = 0.0;
		// compute sum of sqr(V) at this device -- sum of VV*
		for (i = 0; i < nPhases; i++)
			result = result + getVTerminal(i).multiply( getVTerminal(i).conjugate() ).getReal();

		result = result * YeqIdling.getReal() * 0.001;  // to kW

		return result;
	}

	public double getPresentKV() {
		return kVStorageBase;
	}

	public double getPresentKVAr() {
		return kVArOut;  // QNominalPerPhase * 0.001 * nPhases;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, idx;

		super.dumpProperties(f, complete);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			idx = getParentClass().getPropertyIdxMap(i);
			switch (idx) {
			case Storage.USER_DATA:
				f.println("~ " + getParentClass().getPropertyName(i) +
					"=(" + getPropertyValue(idx) + ")");
				break;
			default:
				f.println("~ " + getParentClass().getPropertyName(i) +
					"=" + getPropertyValue(idx));
				break;
			}
		}
		f.println();
	}

	/**
	 * This method makes a Thevenin equivalent behis the reactance spec'd in %R and %X
	 */
	@Override
	public void initHarmonics() {
		Complex e, Va = null;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		setYPrimInvalid(true);  // force rebuild of YPrims
		storageFundamental = sol.getFrequency();  // whatever the frequency is when we enter here

		Yeq = ComplexUtil.invert(new Complex(RThev, XThev));  // used for current calcs; always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		if (state == Storage.DISCHARGING) {
			computeITerminal();  // get present value of current

			switch (connection) {
			case 0:  /* wye - neutral is explicit */
				Va = sol.getNodeV(nodeRef[0]).subtract( sol.getNodeV(nodeRef[nConds - 1]) );
				break;
			case 1:  /* delta -- assume neutral is at zero */
				Va = sol.getNodeV(nodeRef[0]);
				break;
			}

			e = Va.subtract( ITerminal[0].multiply(new Complex(RThev, XThev)) );
			VThevhH = e.abs();  // establish base mag and angle
			thetaHarm = e.getArgument();
		} else {
			VThevhH = 0.0;
			thetaHarm = 0.0;
		}
	}

	/**
	 * For going into dynamics mode.
	 */
	@Override
	public void initStateVars() {
		setYPrimInvalid(true);  // force rebuild of YPrims
	}

	/**
	 * Dynamics mode integration routine.
	 */
	@Override
	public void integrateStates() {

	}

	// FIXME Private method in OpenDSS
	public int interpretState(String s) {

		switch (s.toLowerCase().charAt(0)) {
		case 'c':
			return Storage.CHARGING;
		case 'd':
			return Storage.DISCHARGING;
		default:
			return Storage.IDLING;
		}
	}

	private String stateToStr() {
		switch (state) {
		case Storage.CHARGING: return "Charging";
		case Storage.IDLING: return "Idling";
		case Storage.DISCHARGING: return "Discharging";
		default: return "";
		}
	}

	/**
	 * Return variables one at a time.
	 */
	@Override
	public double getVariable(int i) {
		int n, k;

		if (i < 0) return -9999.99;

		// for now, report kWh stored and mode
		switch (i) {
		case 0:
			return kWhStored;
		case 1:
			return state;
		case 2:
			if (state != Storage.DISCHARGING) {
				return 0.0;
			} else {
				return getPower(0).getReal() * 0.001;  // kW_Out; // pctkWout;  TODO check zero based indexing
			}
		case 3:
			if (state == Storage.CHARGING) {
				return 0.0;
			} else {
				return getPower(0).getReal() * 0.001;  // kW_out; // pctkWin;  TODO check zero based indexing
			}
		case 4:
			return getKWTotalLosses();  /* Present kW charge or discharge loss incl idle losses */
		case 5:
			return getKWIdlingLosses();  /* Present idling loss */
		case 6:
			return kWhStored - kWhBeforeUpdate;
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = (i - Storage.NumStorageVariables);  // TODO Check zero based indexing
				if (k <= n)
					return userModel.getVariable(k);
			}
			break;
		}
		return -9999.99;
	}

	@Override
	public void setVariable(int i, double value) {
		int n, k;

		if (i < 0)
			return;  // no variables to set

		switch (i) {
		case 0:
			kWhStored = value;
			break;
		case 1:
			setStorageState((int) value);
			break;
		case 2:
			setPctKWOut(value);
			break;
		case 3:
			pctKWIn = value;
			break;
		case 4:
			/* Do nothing; read only */
			break;
		case 5:
			/* Do nothing; read only */
			break;
		case 6:
			/* Do nothing; read only */
			break;
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = (i - Storage.NumStorageVariables);  // TODO Check zero based indexing
				if (k <= n) {
					userModel.setVariable(k, value);
					return;
				}
			}
			break;
		}
	}

	@Override
	public void getAllVariables(double[] states) {
		for (int i = 0; i < Storage.NumStorageVariables; i++)
			states[i] = getVariable(i);

		if (userModel.exists())
			userModel.getAllVars(states[Storage.NumStorageVariables - 1]);
	}

	@Override
	public int numVariables() {
		int result = Storage.NumStorageVariables;
		if (userModel.exists())
			result = result + userModel.numVars();
		return result;
	}

	@Override
	public String variableName(int i) {
		final int buffSize = 255;

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
			return "kW Discharging";
		case 3:
			return "kW Charging";
		case 4:
			return "kW Losses";
		case 5:
			return "kW Idling Losses";
		case 6:
			return "kWh Change";
		default:
			if (userModel.exists()) {
				pName = 0;
				n = userModel.numVars();
				i2 = i - Storage.NumStorageVariables;  // TODO Check zero based indexing
				if (i2 <= n) {
					userModel.getVarName(i2, pName, buffSize);
					return String.valueOf(pName);
				}
			}
			break;
		}
		return null;
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		String s;
		double V;

		s = "phases=1 conn=wye";

		// make sure voltage is line-neutral
		if (nPhases > 1 || connection != 0) {
			V = kVStorageBase / DSS.SQRT3;
		} else {
			V = kVStorageBase;
		}

		s = s + String.format(" kV=%-.5g", V);

		if (nPhases > 1)
			s = s + String.format(" kWrating=%-.5g  PF=%-.5g", kWRating / nPhases, PFNominal);

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();  // write out other properties
	}

	@Override
	public void setConductorClosed(int index, boolean value) {
		super.setConductorClosed(index, value);

		// just turn storage element on or off

		if (value) {
			storageObjSwitchOpen = false;
		} else {
			storageObjSwitchOpen = true;
		}
	}

	public void setPctKVArOut(double value) {
		pctKVArOut = value;
		// force recompute of target PF and requested kVAr
		setPresentKVAr( kWRating * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0) * pctKVArOut / 100.0 );
	}

	public void setPctKWOut(double value) {
		pctKWout = value;
		kWOut = pctKWout * kWRating / 100.0;
	}

	public void setPowerFactor(double value) {
		PFNominal = value;
		syncUpPowerQuantities();
	}

	public void setPresentKV(double value) {
		kVStorageBase = value;
		switch (nPhases) {
		case 2:
			VBase = kVStorageBase * DSS.InvSQRT3x1000;
			break;
		case 3:
			VBase = kVStorageBase * DSS.InvSQRT3x1000;
			break;
		default:
			VBase = kVStorageBase * 1000.0;
			break;
		}
	}

	/**
	 * Set the kvar to requested value within rating of inverter
	 */
	public void setPresentKVAr(double value) {
		double kVA_Gen;

		kVArOut = value;
		kVArRequested = value;
		/* Requested kVA output */
		kVA_Gen = Math.sqrt(Math.pow(kWOut, 2) + Math.pow(kVArOut, 2)) ;
		if (kVA_Gen > kVARating)
			kVA_Gen = kVARating;  // limit kVA to rated value
		if (kVA_Gen != 0.0) {
			setPowerFactor(kWOut / kVA_Gen);
		} else {
			setPowerFactor(1.0);
		}
		if ((kWOut * kVArOut) < 0.0)
			setPowerFactor(-PFNominal);
	}

	public void setPresentKW(double value) {
		setPctKWOut( value / kWhRating * 100.0 );
		kWOut = value;
		//syncUpPowerQuantities();
	}

	public void setStorageState(int value) {
		if (value != state) stateChanged = true;
		state = value;
	}

	// FIXME Private method in OpenDSS
	public void syncUpPowerQuantities() {
		if (kVANotSet)
			kVARating = kWRating;
		kVArOut = 0.0;
		// keep kVAr nominal up to date with kW and PF
		if (PFNominal != 0.0) {
			kVArOut = kWOut * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArOut = -kVArOut;
		}
	}

	private void setDragHandRegister(int reg, double value) {
		if (value > registers[reg])
			registers[reg] = value;
	}

	public double getPowerFactor() {
		return PFNominal;
	}

}

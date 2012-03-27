/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Storage extends PCClass {

	public static final int NumStorageRegisters = 6;
	public static final int NumStorageVariables = 7;

	public static final int KV         =  2;
	public static final int KW         =  3;
	public static final int PF         =  4;
	public static final int MODEL      =  5;
	public static final int YEARLY     =  6;
	public static final int DAILY      =  7;
	public static final int DUTY       =  8;
	public static final int DISP_MODE   =  9;
	public static final int IDLE_KVAR   = 10;
	public static final int CONNECTION = 11;
	public static final int KVAR       = 12;
	public static final int PCTR       = 13;
	public static final int PCTX       = 14;
	public static final int IDLE_KW     = 15;
	public static final int CLASS      = 16;
	public static final int DISP_OUT_TRIG= 17;
	public static final int DISP_IN_TRIG = 18;
	public static final int CHARGE_EFF  = 19;
	public static final int DISCHARGE_EFF = 20;
	public static final int PCT_KW_OUT   = 21;
	public static final int VMIN_PU     = 22;
	public static final int VMAX_PU     = 23;
	public static final int STATE      = 24;
	public static final int KVA        = 25;
	public static final int KW_RATED    = 26;
	public static final int KWH_RATED   = 27;
	public static final int KWH_STORED  = 28;
	public static final int PCT_RESERVE = 29;
	public static final int USER_MODEL  = 30;
	public static final int USER_DATA   = 31;
	public static final int DEBUG_TRACE = 32;
	public static final int PCT_KW_IN    = 33;
	public static final int PCT_STORED  = 34;
	public static final int CHARGE_TIME = 35;

	public static final int NumPropsThisClass = 36;  // make this agree with the last property constant

	public static StorageObj activeStorageObj;

	public static Complex[] cBuffer = new Complex[24];

	private String[] registerNames = new String[NumStorageRegisters];

	public Storage() {
		super();
		className = "Storage";
		classType = classType + DSSClassDefs.STORAGE_ELEMENT;  // in both PCElement and Storage element list

		activeElement = -1;

		// set register names
		registerNames[0] = "kWh";
		registerNames[1] = "kvarh";
		registerNames[2] = "Max kW";
		registerNames[3] = "Max kVA";
		registerNames[4] = "Hours";
		registerNames[5] = "$";

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {

		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();  /* see DSSClass */

		// define property names
		/*
		 * Using the addProperty function, you can list the properties here in the order you want
		 * them to appear when properties are accessed sequentially without tags. Syntax:
		 *
		 *   addProperty(<name of property>, <index in the edit case statement>, <help text>);
		 */
		addProperty("phases", 0,
				"Number of Phases, this Storage element.  Power is evenly divided among phases.");
		addProperty("bus1", 1,
				"Bus to which the Storage element is connected.  May include specific node specification.");
		addProperty("kv", KV,
				"Nominal rated (1.0 per unit) voltage, kV, for Storage element. For 2- and 3-phase Storage elements, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the Storage element. "+
				"If wye (star), specify phase-neutral kV. "+DSS.CRLF+DSS.CRLF+
				"If delta or phase-phase connected, specify phase-phase kV.");  // line-neutral voltage//  base voltage
		addProperty("kW", KW,
				"Get/set the present kW value.  A positive value denotes power coming OUT of the element, "+DSS.CRLF+
				"which is the opposite of a Load element. A negative value indicates the Storage element is in Charging state. " +
				"This value is modified internally depending on the dispatch mode. " );
		addProperty("pf", PF,
				"Nominally, the power factor for discharging (acting as a generator). Default is 1.0. " +
				"Setting this property will also set the kvar property." +
				"Enter negative for leading powerfactor "+
				"(when kW and kvar have opposite signs.)"+DSS.CRLF+DSS.CRLF+
				"A positive power factor for a generator signifies that the Storage element produces vars " +
				"as is typical for a generator.  ");
		addProperty("conn", CONNECTION,
				"={wye|LN|delta|LL}.  Default is wye.");
		addProperty("kvar", KVAR,
				"Get/set the present kW value.  Alternative to specifying the power factor.  Side effect: "+
				" the power factor value is altered to agree based on present value of kW.");
		addProperty("kVA", KVA,
				"kVA rating of power output. Defaults to rated kW. Used as the base for Dynamics mode and Harmonics mode values.");
		addProperty("kWrated", KW_RATED,
				"kW rating of power output. Base for Loadshapes when DispMode=Follow. Side effect: Set KVA property.");

		addProperty("kWhrated", KWH_RATED,
				"Rated storage capacity in kWh. Default is 50.");
		addProperty("kWhstored", KWH_STORED,
				"Present amount of energy stored, kWh. Default is same as kWh rated.");
		addProperty("%stored", PCT_STORED,
				"Present amount of energy stored, % of rated kWh. Default is 100%.");
		addProperty("%reserve", PCT_RESERVE,
				"Percent of rated kWh storage capacity to be held in reserve for normal operation. Default = 20. " + DSS.CRLF +
				"This is treated as the minimum energy discharge level unless there is an emergency. For emergency operation " +
				"set this property lower. Cannot be less than zero.");
		addProperty("State", STATE,
				"{IDLING | CHARGING | DISCHARGING}  Get/Set present operational state. In DISCHARGING mode, the Storage element " +
				"acts as a generator and the kW property is positive. The element continues discharging at the scheduled output power level " +
				"until the storage reaches the reserve value. Then the state reverts to IDLING. " +
				"In the CHARGING state, the Storage element behaves like a Load and the kW property is negative. " +
				"The element continues to charge until the max storage kWh is reached and Then switches to IDLING state. " +
				"In IDLING state, the kW property shows zero. However, the resistive and reactive loss elements remain in the circuit " +
				"and the power flow report will show power being consumed.");
		addProperty("%Discharge", PCT_KW_OUT,
				"Discharge rate (output power) in Percent of rated kW. Default = 100.");
		addProperty("%Charge", PCT_KW_IN,
				"Charging rate (input power) in Percent of rated kW. Default = 100.");
		addProperty("%EffCharge", CHARGE_EFF,
				"Percent efficiency for CHARGING the storage element. Default = 90.");
		addProperty("%EffDischarge", DISCHARGE_EFF,
				"Percent efficiency for DISCHARGING the storage element. Default = 90." +
				"Idling losses are handled by %IdlingkW property and are in addition to the charging and discharging efficiency losses " +
				"in the power conversion process inside the unit.");
		addProperty("%IdlingkW", IDLE_KW,
				"Percent of rated kW consumed while idling. Default = 1.");
		addProperty("%Idlingkvar", IDLE_KVAR,
				"Percent of rated kW consumed as reactive power (kvar) while idling. Default = 0.");
		addProperty("%R", PCTR,
				"Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for " +
				"losses in power flow modes.");
		addProperty("%X", PCTX,
				"Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. (Limits fault current to 2 pu.) " +
				"Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.");
		addProperty("model", MODEL,
				"Integer code (default=1) for the model to use for powet output variation with voltage. "+
				"Valid values are:" +DSS.CRLF+DSS.CRLF+
				"1:Storage element injects a CONSTANT kW at specified power factor."+DSS.CRLF+
				"2:Storage element is modeled as a CONSTANT ADMITTANCE."  +DSS.CRLF+
				"3:Compute load injection from User-written Model.");

		addProperty("Vminpu", VMIN_PU,
				"Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.");
		addProperty("Vmaxpu", VMAX_PU,
				"Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.");
		addProperty("yearly", YEARLY,
				"Dispatch shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. If this is not specified, the Daily dispatch shape, if any, is repeated "+
				"during Yearly solution modes. In the default dispatch mode, " +
				"the Storage element uses this loadshape to trigger State changes.");
		addProperty("daily", DAILY,
				"Dispatch shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, "+
				"the Storage element uses this loadshape to trigger State changes."); // daily dispatch (hourly)
		addProperty("duty", DUTY,
				"Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a Loadshape object. "+DSS.CRLF+DSS.CRLF+
				"Typically would have time intervals of 1-5 seconds. "+DSS.CRLF+DSS.CRLF+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat.");  // as for wind generation
		addProperty("DispMode", DISP_MODE,
				"{DEFAULT | FOLLOW | EXTERNAL | LOADLEVEL | PRICE } Default = \"DEFAULT\". Dispatch mode. "+DSS.CRLF+DSS.CRLF+
				"In DEFAULT mode, Storage element state is triggered to discharge or charge at the specified rate by the " +
				"loadshape curve corresponding to the solution mode. "+ DSS.CRLF + DSS.CRLF +
				"In FOLLOW mode the kW and kvar output of the STORAGE element follows the active loadshape multipliers " +
				"until storage is either exhausted or full. " +
				"The element discharges for positive values and charges for negative values.  The loadshapes are based on the kW and kvar " +
				"values in the most recent definition of kW and PF or kW and kvar properties. " + DSS.CRLF + DSS.CRLF);
		addProperty("DischargeTrigger", DISP_OUT_TRIG,
				"Dispatch trigger value for discharging the storage. "+DSS.CRLF+
				"If = 0.0 the Storage element state is changed by the State command or by a StorageController object. " +DSS.CRLF+
				"If <> 0  the Storage element state is set to DISCHARGING when this trigger level is EXCEEDED by either the specified " +
				"Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.");
		addProperty("Chargetrigger", DISP_IN_TRIG,
				"Dispatch trigger value for charging the storage. "+DSS.CRLF+DSS.CRLF+
				"If = 0.0 the Storage element state is changed by the State command or StorageController object.  " +DSS.CRLF+DSS.CRLF+
				"If <> 0  the Storage element state is set to CHARGING when this trigger level is GREATER than either the specified " +
				"Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.");
		addProperty("TimeChargeTrig", CHARGE_TIME,
				"Time of day in fractional hours (0230 = 2.5) at which storage element will automatically go into charge state. " +
				"Default is 2.0.  Enter a negative time value to disable this feature.");

		addProperty("class", CLASS,
				"An arbitrary integer number representing the class of Storage element so that Storage values may "+
				"be segregated by class."); // integer

		addProperty("UserModel", USER_MODEL,
				"Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, " +
				"overriding the default model.  Set to \"none\" to negate previous setting.");
		addProperty("UserData", USER_DATA,
				"String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.");
		addProperty("debugtrace", DEBUG_TRACE,
				"{Yes | No }  Default is no.  Turn this on to capture the progress of the Storage model " +
				"for each iteration.  Creates a separate file for each Storage element named \"STORAGE_name.CSV\"." );

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override default help string
		propertyHelp[NumPropsThisClass] = "Name of harmonic voltage or current spectrum for this Storage element. " +
							"Current injection is assumed for inverter. " +
							"Default value is \"default\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {

		DSS.activeCircuit.setActiveCktElement(new StorageObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void setNcondsForConnection() {
		StorageObj as = activeStorageObj;

		switch (as.getConnection()) {
		case WYE:
			as.setNumConds(as.getNumPhases() + 1);
			break;
		case DELTA:
			switch (as.getNumPhases()) {
			case 1:
				as.setNumConds(as.getNumPhases() + 1);  // L-L
				break;
			case 2:
				as.setNumConds(as.getNumPhases() + 1);  // open-delta
				break;
			default:
				as.setNumConds(as.getNumPhases());
				break;
			}
			break;
		}
	}

	public void updateAll() {
		StorageObj pElem;
		for (int i = 0; i < elementList.size(); i++) {
			pElem = (StorageObj) elementList.get(i);
			if (pElem.isEnabled())
				pElem.updateStorage();
		}
	}

	/**
	 * Accepts (case insensitive):
	 *   delta or LL
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		StorageObj as = activeStorageObj;
		String testS = s.toLowerCase();
		switch (testS.charAt(0)) {
		case 'y':
			as.setConnection(Connection.WYE);
			break;
		case 'w':
			as.setConnection(Connection.WYE);
			break;
		case 'd':
			as.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (testS.charAt(1)) {
			case 'n':
				as.setConnection(Connection.WYE);
				break;
			case 'l':
				as.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		setNcondsForConnection();

		/* VBase is always L-N voltage unless 1-phase device or more than 3 phases */

		switch (as.getNumPhases()) {
		case 2:
			as.setVBase(as.getKVStorageBase() * DSS.InvSQRT3x1000);  // L-N Volts
			break;
		case 3:
			as.setVBase(as.getKVStorageBase() * DSS.InvSQRT3x1000);
			break;
		default:
			as.setVBase(as.getKVStorageBase() * 1000.0);  // just use what is supplied
			break;
		}

		as.setVBase95(as.getVMinPU() * as.getVBase());
		as.setVBase105(as.getVMaxPU() * as.getVBase());

		as.setYOrder(as.getNumConds() * as.getNumTerms());
		as.setYPrimInvalid(true);
	}

	private DispatchMode interpretDispMode(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'e':
			return DispatchMode.EXTERNAL;
		case 'f':
			return DispatchMode.FOLLOW;
		case 'l':
			return DispatchMode.LOAD;
		case 'p':
			return DispatchMode.PRICE;
		default:
			return DispatchMode.DEFAULT;
		}
	}

	@Override
	public int edit() {
		int i, iCase;

		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeStorageObj = (StorageObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeStorageObj);

		int result = 0;

		StorageObj as = activeStorageObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();  // parse next property off the command line
		String param = parser.stringValue();        // put the string value of the property value in local memory for faster access
		while (param.length() > 0) {

			if (paramName.length() == 0) {
				paramPointer += 1;  // if it is not a named property, assume the next property
			} else {
				paramPointer = commandList.getCommand(paramName);  // look up the name in the list for this class
			}

			if (paramPointer >= 0 && paramPointer < numProperties) {
				as.setPropertyValue(propertyIdxMap[paramPointer], param);  // update the string value of the property
			} else {
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for Storage \""+as.getName()+"\"", 560);
			}

			if (paramPointer > 0) {
				iCase = propertyIdxMap[paramPointer];
				switch (iCase) {
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getClassName() +"."+ as.getName() + "\"", 561);
					break;
				case 0:
					as.setNumPhases(parser.integerValue());  // num phases
					break;
				case 1:
					as.setBus(0, param);
					break;
				case KV:
					as.setPresentKV(parser.doubleValue());
					break;
				case KW:
					as.setKWOut(parser.doubleValue());
					break;
				case PF:
					as.PFNominal = parser.doubleValue();
					break;
				case MODEL:
					as.setVoltageModel(parser.integerValue());
					break;
				case YEARLY:
					as.setYearlyShape(param);
					break;
				case DAILY:
					as.setDailyShape(param);
					break;
				case DUTY:
					as.setDutyShape(param);
					break;
				case DISP_MODE:
					as.setDispatchMode(interpretDispMode(param));
					break;
				case IDLE_KVAR:
					as.setPctIdleKVAr(parser.doubleValue());
					break;
				case CONNECTION:
					interpretConnection(param);
					break;
				case KVAR:
					as.setPresentKVAr(parser.doubleValue());
					break;
				case PCTR:
					as.setPctR(parser.doubleValue());
					break;
				case PCTX:
					as.setPctX(parser.doubleValue());
					break;
				case IDLE_KW:
					as.setPctIdleKW(parser.doubleValue());
					break;
				case CLASS:
					as.setStorageClass(parser.integerValue());
					break;
				case DISP_OUT_TRIG:
					as.setDischargeTrigger(parser.doubleValue());
					break;
				case DISP_IN_TRIG:
					as.setChargeTrigger(parser.doubleValue());
					break;
				case CHARGE_EFF:
					as.setPctChargeEff(parser.doubleValue());
					break;
				case DISCHARGE_EFF:
					as.setPctDischargeEff(parser.doubleValue());
					break;
				case PCT_KW_OUT:
					as.setPctKWOut(parser.doubleValue());
					break;
				case VMIN_PU:
					as.setVMinPU(parser.doubleValue());
					break;
				case VMAX_PU:
					as.setVMaxPU(parser.doubleValue());
					break;
				case STATE:
					as.setStorageState(as.interpretState(param));
					break;
				case KVA:
					as.setKVARating(parser.doubleValue());
					break;
				case KW_RATED:
					as.setKWRating(parser.doubleValue());
					break;
				case KWH_RATED:
					as.setKWhRating(parser.doubleValue());
					break;
				case KWH_STORED:
					as.setKWhStored(parser.doubleValue());
					break;
				case PCT_RESERVE:
					as.setPctReserve(parser.doubleValue());
					break;
				case USER_MODEL:
					as.getUserModel().setName(parser.stringValue());  // connect to user written models
					break;
				case USER_DATA:
					as.getUserModel().edit(parser.stringValue());  // send edit string to user model
					break;
				case DEBUG_TRACE:
					as.setDebugTrace(Util.interpretYesNo(param));
					break;
				case PCT_KW_IN:
					as.setPctKWIn(parser.doubleValue());
					break;
				case PCT_STORED:
					as.setKWhStored(parser.doubleValue() * 0.01 * as.getKWhRating());
					break;
				case CHARGE_TIME:
					as.setChargeTime(parser.doubleValue());
					break;
				default:
					// inherited parameters
					classEdit(activeStorageObj, paramPointer - NumPropsThisClass);
					break;
				}

				switch (iCase) {
				case 0:
					setNcondsForConnection();  // force reallocation of terminal info
					break;
				case KW:
					as.syncUpPowerQuantities();   // keep kVAr nominal up to date with kW and PF
					break;
				case PF:
					as.syncUpPowerQuantities();
					break;

					/* Set load shape objects; returns nil if not valid */
				case YEARLY:
					as.setYearlyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(as.getYearlyShape()));
					break;
				case DAILY:
					as.setDailyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(as.getDailyShape()));
					break;
				case DUTY:
					as.setDutyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(as.getDutyShape()));
					break;
				case KW_RATED:
					as.setKVARating(as.getKWRating());
					break;
				case KWH_RATED:
					as.setKWhStored(as.getKWhRating());  // Assume fully charged
					as.setKWhBeforeUpdate(as.getKWhStored());
					as.setKWhReserve(as.getKWhRating() * as.getPctReserve() * 0.01);
					break;

				case PCT_RESERVE:
					as.setKWhReserve(as.getKWhRating() * as.getPctReserve() * 0.01);
					break;

				case DEBUG_TRACE:
					if (as.isDebugTrace()) {
						try {
							// init trace file
							File TraceFile = new File(DSS.dataDirectory + "STOR_"+as.getName()+".csv");
							FileWriter TraceStream = new FileWriter(TraceFile, false);
							BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

							TraceBuffer.write("t, Iteration, LoadMultiplier, Mode, LoadModel, StorageModel,  Qnominalperphase, Pnominalperphase, CurrentType");
							for (i = 0; i < as.getNumPhases(); i++)
								TraceBuffer.write(", |Iinj" + String.valueOf(i) + "|");
							for (i = 0; i < as.getNumPhases(); i++)
								TraceBuffer.write(", |Iterm"+ String.valueOf(i) + "|");
							for (i = 0; i < as.getNumPhases(); i++)
								TraceBuffer.write(", |Vterm" + String.valueOf(i) + "|");
							for (i = 0; i < as.numVariables(); i++)
								TraceBuffer.write(", " + as.variableName(i));
							TraceBuffer.write(",Vthev, Theta");
							TraceBuffer.newLine();

							TraceBuffer.close();
							TraceStream.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					break;

				case KVA:
					as.setKVANotSet(false);
					break;
				}
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		as.recalcElementData();
		as.setYPrimInvalid(true);

		return result;
	}

	/**
	 * Copy over essential properties from other object.
	 */
	@Override
	protected int makeLike(String otherStorageObjName) {
		int result = 0;
		/* See if we can find this line name in the present collection */
		StorageObj otherStorageObj = (StorageObj) find(otherStorageObjName);
		if (otherStorageObj != null) {
			StorageObj as = activeStorageObj;

			if (as.getNumPhases() != otherStorageObj.getNumPhases()) {
				as.setNumPhases(otherStorageObj.getNumPhases());
				as.setNumConds(as.getNumPhases());  // forces reallocation of terminal stuff
				as.setYOrder(as.getNumConds() * as.getNumTerms());
				as.setYPrimInvalid(true);
			}

			as.setKVStorageBase(otherStorageObj.getKVStorageBase());
			as.setVBase(otherStorageObj.getVBase());
			as.setVMinPU(otherStorageObj.getVMinPU());
			as.setVMaxPU(otherStorageObj.getVMaxPU());
			as.setVBase95(otherStorageObj.getVBase95());
			as.setVBase105(otherStorageObj.getVBase105());
			as.setKWOut(otherStorageObj.getKWOut());
			as.setKVArOut(otherStorageObj.getKVArOut());
			as.setPNominalPerPhase(otherStorageObj.getPNominalPerPhase());
			as.PFNominal = otherStorageObj.PFNominal;
			as.setQNominalPerPhase(otherStorageObj.getQNominalPerPhase());
			as.setConnection(otherStorageObj.getConnection());
			as.setYearlyShape(otherStorageObj.getYearlyShape());
			as.setYearlyShapeObj(otherStorageObj.getYearlyShapeObj());
			as.setDailyShape(otherStorageObj.getDailyShape());
			as.setDailyShapeObj(otherStorageObj.getDailyShapeObj());
			as.setDutyShape(otherStorageObj.getDutyShape());
			as.setDutyShapeObj(otherStorageObj.getDutyShapeObj());
			as.setDispatchMode(otherStorageObj.getDispatchMode());
			as.setStorageClass(otherStorageObj.getStorageClass());
			as.setVoltageModel(otherStorageObj.getVoltageModel());

			as.setStorageState(otherStorageObj.getState());
			as.setStateChanged(otherStorageObj.isStateChanged());
			as.setKVANotSet(otherStorageObj.isKVANotSet());

			as.setKVARating(otherStorageObj.getKVARating());

			as.setKWRating(otherStorageObj.getKWRating());
			as.setKWhRating(otherStorageObj.getKWhRating());
			as.setKWhStored(otherStorageObj.getKWhStored());
			as.setKWhBeforeUpdate(otherStorageObj.getKWhBeforeUpdate());
			as.setKWhReserve(otherStorageObj.getKWhReserve());
			as.setPctReserve(otherStorageObj.getPctReserve());
			as.setDischargeTrigger(otherStorageObj.getDischargeTrigger());
			as.setChargeTrigger(otherStorageObj.getChargeTrigger());
			as.setPctChargeEff(otherStorageObj.getPctChargeEff());
			as.setPctDischargeEff(otherStorageObj.getPctDischargeEff());
			as.setPctKWOut(otherStorageObj.getPctKWout());
			as.setPctKWIn(otherStorageObj.getPctKWIn());
			as.setPctIdleKW(otherStorageObj.getPctIdleKW());
			as.setPctIdleKVAr(otherStorageObj.getPctIdleKVAr());
			as.setChargeTime(otherStorageObj.getChargeTime());

			as.setPctR(otherStorageObj.getPctR());
			as.setPctX(otherStorageObj.getPctX());

			as.setRandomMult(otherStorageObj.getRandomMult());

			as.getUserModel().setName(otherStorageObj.getUserModel().getName());  // connect to user written models

			classMakeLike(otherStorageObj);

			for (int i = 0; i < as.getParentClass().getNumProperties(); i++)
				as.setPropertyValue(i, otherStorageObj.getPropertyValue(i));

			result = 1;
		} else {
			DSS.doSimpleMsg("Error in Storage makeLike: \"" + otherStorageObjName + "\" not found.", 562);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		StorageObj pElem;

		if (handle == -1) {  // init all
			for (int i = 0; i < elementList.size(); i++) {
				pElem = (StorageObj) elementList.get(i);
				pElem.randomize(Randomization.NONE);
			}
		} else {
			setActiveElement(handle);
			pElem = (StorageObj) getActiveObj();
			pElem.randomize(Randomization.NONE);
		}

		DSS.doSimpleMsg("Need to implement Storage.init", -1);
		return 0;
	}

	/**
	 * Force all EnergyMeters in the circuit to reset.
	 */
	public void resetRegistersAll() {
		int idx = getFirst();
		StorageObj pElem;
		while (idx >= 0) {
			pElem = (StorageObj) getActiveObj();
			pElem.resetRegisters();
			idx = getNext();
		}
	}

	/**
	 * Force all Storage elements in the circuit to take a sample.
	 */
	public void sampleAll() {
		StorageObj pElem;
		for (int i = 0; i < elementList.size(); i++) {
			pElem = (StorageObj) elementList.get(i);
			if (pElem.isEnabled())
				pElem.takeSample();
		}
	}

}

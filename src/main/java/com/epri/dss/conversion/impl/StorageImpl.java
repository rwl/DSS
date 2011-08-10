package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Storage;
import com.epri.dss.conversion.StorageObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.Complex;

public class StorageImpl extends PCClassImpl implements Storage {

	public static StorageObj ActiveStorageObj;

	public static Complex[] cBuffer = new Complex[24];

	private String[] RegisterNames = new String[NumStorageRegisters];

	public StorageImpl() {
		super();
		Class_Name = "Storage";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.STORAGE_ELEMENT;  // In both PCelement and Storage element list

		setActiveElement(-1);

		// Set register names
		this.RegisterNames[0]  = "kWh";
		this.RegisterNames[1]  = "kvarh";
		this.RegisterNames[2]  = "Max kW";
		this.RegisterNames[3]  = "Max kVA";
		this.RegisterNames[4]  = "Hours";
		this.RegisterNames[5]  = "$";

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();  /* see DSSClass */

		// Define Property names
		/* Using the addProperty FUNCTION, you can list the properties here in the order you want
		 * them to appear when properties are accessed sequentially without tags.   Syntax:
		 *   addProperty( <name of property>, <index in the EDIT Case statement>, <help text>);
		 */
		addProperty("phases", 0,
				"Number of Phases, this Storage element.  Power is evenly divided among phases.");
		addProperty("bus1", 1,
				"Bus to which the Storage element is connected.  May include specific node specification.");
		addProperty("kv", propKV,
				"Nominal rated (1.0 per unit) voltage, kV, for Storage element. For 2- and 3-phase Storage elements, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the Storage element. "+
				"If wye (star), specify phase-neutral kV. "+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"If delta or phase-phase connected, specify phase-phase kV.");  // line-neutral voltage//  base voltage
		addProperty("kW", propKW,
				"Get/set the present kW value.  A positive value denotes power coming OUT of the element, "+DSSGlobals.CRLF+
				"which is the opposite of a Load element. A negative value indicates the Storage element is in Charging state. " +
				"This value is modified internally depending on the dispatch mode. " );
		addProperty("pf", propPF,
				"Nominally, the power factor for discharging (acting as a generator). Default is 1.0. " +
				"Setting this property will also set the kvar property." +
				"Enter negative for leading powerfactor "+
				"(when kW and kvar have opposite signs.)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"A positive power factor for a generator signifies that the Storage element produces vars " +
				"as is typical for a generator.  ");
		addProperty("conn", propCONNECTION,
				"={wye|LN|delta|LL}.  Default is wye.");
		addProperty("kvar", propKVAR,
				"Get/set the present kW value.  Alternative to specifying the power factor.  Side effect: "+
				" the power factor value is altered to agree based on present value of kW.");
		addProperty("kVA", propKVA,
				"kVA rating of power output. Defaults to rated kW. Used as the base for Dynamics mode and Harmonics mode values.");
		addProperty("kWrated", propKWRATED,
				"kW rating of power output. Base for Loadshapes when DispMode=Follow. Side effect: Set KVA property.");

		addProperty("kWhrated", propKWHRATED,
				"Rated storage capacity in kWh. Default is 50.");
		addProperty("kWhstored", propKWHSTORED,
				"Present amount of energy stored, kWh. Default is same as kWh rated.");
		addProperty("%stored", propPCTSTORED,
				"Present amount of energy stored, % of rated kWh. Default is 100%.");
		addProperty("%reserve", propPCTRESERVE,
				"Percent of rated kWh storage capacity to be held in reserve for normal operation. Default = 20. " + DSSGlobals.CRLF +
				"This is treated as the minimum energy discharge level unless there is an emergency. For emergency operation " +
				"set this property lower. Cannot be less than zero.");
		addProperty("State", propSTATE,
				"{IDLING | CHARGING | DISCHARGING}  Get/Set present operational state. In DISCHARGING mode, the Storage element " +
				"acts as a generator and the kW property is positive. The element continues discharging at the scheduled output power level " +
				"until the storage reaches the reserve value. Then the state reverts to IDLING. " +
				"In the CHARGING state, the Storage element behaves like a Load and the kW property is negative. " +
				"The element continues to charge until the max storage kWh is reached and Then switches to IDLING state. " +
				"In IDLING state, the kW property shows zero. However, the resistive and reactive loss elements remain in the circuit " +
				"and the power flow report will show power being consumed.");
		addProperty("%Discharge", propPCTKWOUT,
				"Discharge rate (output power) in Percent of rated kW. Default = 100.");
		addProperty("%Charge", propPCTKWIN,
				"Charging rate (input power) in Percent of rated kW. Default = 100.");
		addProperty("%EffCharge", propCHARGEEFF,
				"Percent efficiency for CHARGING the storage element. Default = 90.");
		addProperty("%EffDischarge", propDISCHARGEEFF,
				"Percent efficiency for DISCHARGING the storage element. Default = 90." +
				"Idling losses are handled by %IdlingkW property and are in addition to the charging and discharging efficiency losses " +
				"in the power conversion process inside the unit.");
		addProperty("%IdlingkW", propIDLEKW,
				"Percent of rated kW consumed while idling. Default = 1.");
		addProperty("%Idlingkvar", propIDLEKVAR,
				"Percent of rated kW consumed as reactive power (kvar) while idling. Default = 0.");
		addProperty("%R", propPCTR,
				"Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for " +
				"losses in power flow modes.");
		addProperty("%X", propPCTX,
				"Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. (Limits fault current to 2 pu.) " +
				"Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.");
		addProperty("model", propMODEL,
				"Integer code (default=1) for the model to use for powet output variation with voltage. "+
				"Valid values are:" +DSSGlobals.CRLF+DSSGlobals.CRLF+
				"1:Storage element injects a CONSTANT kW at specified power factor."+DSSGlobals.CRLF+
				"2:Storage element is modeled as a CONSTANT ADMITTANCE."  +DSSGlobals.CRLF+
				"3:Compute load injection from User-written Model.");

		addProperty("Vminpu", propVMINPU,
				"Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.");
		addProperty("Vmaxpu", propVMAXPU,
				"Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.");
		addProperty("yearly", propYEARLY,
				"Dispatch shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. If this is not specified, the Daily dispatch shape, if any, is repeated "+
				"during Yearly solution modes. In the default dispatch mode, " +
				"the Storage element uses this loadshape to trigger State changes.");
		addProperty("daily", propDAILY,
				"Dispatch shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, "+
				"the Storage element uses this loadshape to trigger State changes."); // daily dispatch (hourly)
		addProperty("duty", propDUTY,
				"Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a Loadshape object. "+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Typically would have time intervals of 1-5 seconds. "+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat.");  // as for wind generation
		addProperty("DispMode", propDISPMODE,
				"{DEFAULT | FOLLOW | EXTERNAL | LOADLEVEL | PRICE } Default = \"DEFAULT\". Dispatch mode. "+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"In DEFAULT mode, Storage element state is triggered to discharge or charge at the specified rate by the " +
				"loadshape curve corresponding to the solution mode. "+ DSSGlobals.CRLF + DSSGlobals.CRLF +
				"In FOLLOW mode the kW and kvar output of the STORAGE element follows the active loadshape multipliers " +
				"until storage is either exhausted or full. " +
				"The element discharges for positive values and charges for negative values.  The loadshapes are based on the kW and kvar " +
				"values in the most recent definition of kW and PF or kW and kvar properties. " + DSSGlobals.CRLF + DSSGlobals.CRLF);
		addProperty("DischargeTrigger", propDISPOUTTRIG,
				"Dispatch trigger value for discharging the storage. "+DSSGlobals.CRLF+
				"If = 0.0 the Storage element state is changed by the State command or by a StorageController object. " +DSSGlobals.CRLF+
				"If <> 0  the Storage element state is set to DISCHARGING when this trigger level is EXCEEDED by either the specified " +
				"Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.");
		addProperty("Chargetrigger", propDISPINTRIG,
				"Dispatch trigger value for charging the storage. "+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"If = 0.0 the Storage element state is changed by the State command or StorageController object.  " +DSSGlobals.CRLF+DSSGlobals.CRLF+
				"If <> 0  the Storage element state is set to CHARGING when this trigger level is GREATER than either the specified " +
				"Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.");
		addProperty("TimeChargeTrig", propCHARGETIME,
				"Time of day in fractional hours (0230 = 2.5) at which storage element will automatically go into charge state. " +
				"Default is 2.0.  Enter a negative time value to disable this feature.");

		addProperty("class", propCLASS,
				"An arbitrary integer number representing the class of Storage element so that Storage values may "+
				"be segregated by class."); // integer

		addProperty("UserModel", propUSERMODEL,
				"Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, " +
				"overriding the default model.  Set to \"none\" to negate previous setting.");
		addProperty("UserData", propUSERDATA,
				"String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.");
		addProperty("debugtrace", propDEBUGTRACE,
				"{Yes | No }  Default is no.  Turn this on to capture the progress of the Storage model " +
				"for each iteration.  Creates a separate file for each Storage element named \"STORAGE_name.CSV\"." );

		ActiveProperty = NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list

		// Override default help string
		PropertyHelp[NumPropsThisClass] = "Name of harmonic voltage or current spectrum for this Storage element. " +
							"Current injection is assumed for inverter. " +
							"Default value is \"default\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new StorageObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void setNcondsForConnection() {
		StorageObj as = getActiveStorageObj();

		switch (as.getConnection()) {
		case 0:
			as.setNConds(as.getNPhases() + 1);
			break;
		case 1:
			switch (as.getNPhases()) {
			case 1:
				as.setNConds(as.getNPhases() + 1);  // L-L
				break;
			case 2:
				as.setNConds(as.getNPhases() + 1);  // Open-delta
				break;
			default:
				as.setNConds(as.getNPhases());
				break;
			}
			break;
		}
	}

	public void updateAll() {
		StorageObj pElem;
		for (int i = 0; i < ElementList.size(); i++) {
			pElem = (StorageObj) ElementList.get(i);
			if (pElem.isEnabled())
				pElem.updateStorage();
		}
	}

	/**
	 * Accepts
	 *   delta or LL           (Case insensitive)
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		StorageObj as = getActiveStorageObj();
		String TestS = S.toLowerCase();
		switch (TestS.charAt(0)) {
		case 'y':
			as.setConnection(0);  /* Wye */
			break;
		case 'w':
			as.setConnection(0);  /* Wye */
			break;
		case 'd':
			as.setConnection(1);  /* Delta or line-Line */
			break;
		case 'l':
			switch (TestS.charAt(1)) {
			case 'n':
				as.setConnection(0);
				break;
			case 'l':
				as.setConnection(1);
				break;
			}
			break;
		}

		setNcondsForConnection();

		/* VBase is always L-N voltage unless 1-phase device or more than 3 phases */

		switch (as.getNPhases()) {
		case 2:
			as.setVBase(as.getkVStorageBase() * DSSGlobals.InvSQRT3x1000);    // L-N Volts
			break;
		case 3:
			as.setVBase(as.getkVStorageBase() * DSSGlobals.InvSQRT3x1000);
			break;
		default:
			as.setVBase(as.getkVStorageBase() * 1000.0);   // Just use what is supplied
			break;
		}

		as.setVBase95(as.getVminpu() * as.getVBase());
		as.setVBase105(as.getVmaxpu() * as.getVBase());

		as.setYorder(as.getNConds() * as.getNTerms());
		as.setYprimInvalid(true);
	}

	private int interpretDispMode(String S) {
		switch (S.toLowerCase().charAt(0)) {
		case 'e':
			return STORE_EXTERNALMODE;
		case 'f':
			return STORE_FOLLOW;
		case 'l':
			return STORE_LOADMODE;
		case 'p':
			return STORE_PRICEMODE;
		default:
			return STORE_DEFAULT;
		}
	}

	@Override
	public int edit() {
		int i, iCase;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// Continue parsing with contents of parser
		setActiveStorageObj((StorageObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveStorageObj());

		int Result = 0;

		StorageObj as = getActiveStorageObj();

		int ParamPointer = 0;
		String ParamName    = parser.getNextParam();  // Parse next property off the command line
		String Param        = parser.makeString();   // Put the string value of the property value in local memory for faster access
		while (Param.length() > 0) {

			if (ParamName.length() == 0) {
				ParamPointer += 1;  // If it is not a named property, assume the next property
			} else {
				ParamPointer = CommandList.getCommand(ParamName);  // Look up the name in the list for this class
			}

			if ((ParamPointer >= 0) && (ParamPointer <= NumProperties)) {
				as.setPropertyValue(PropertyIdxMap[ParamPointer], Param);  // Update the string value of the property
			} else {
				Globals.doSimpleMsg("Unknown parameter \""+ParamName+"\" for Storage \""+as.getName()+"\"", 560);
			}

			if (ParamPointer > 0) {
				iCase = PropertyIdxMap[ParamPointer];
				switch (iCase) {
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ as.getName() + "\"", 561);
					break;
				case 0:
					as.setNPhases(parser.makeInteger());  // num phases
					break;
				case 1:
					as.setBus(1, Param);  // TODO Check zero based indexing
					break;
				case propKV:
					as.setPresentKV(parser.makeDouble());
					break;
				case propKW:
					as.setkW_out(parser.makeDouble());
					break;
				case propPF:
					as.setPowerFactor(parser.makeDouble());
					break;
				case propMODEL:
					as.setVoltageModel(parser.makeInteger());
					break;
				case propYEARLY:
					as.setYearlyShape(Param);
					break;
				case propDAILY:
					as.setDailyShape(Param);
					break;
				case propDUTY:
					as.setDutyShape(Param);
					break;
				case propDISPMODE:
					as.setDispatchMode(interpretDispMode(Param));
					break;
				case propIDLEKVAR:
					as.setPctIdlekvar(parser.makeDouble());
					break;
				case propCONNECTION:
					interpretConnection(Param);
					break;
				case propKVAR:
					as.setPresentKVar(parser.makeDouble());
					break;
				case propPCTR:
					as.setPctR(parser.makeDouble());
					break;
				case propPCTX:
					as.setPctX(parser.makeDouble());
					break;
				case propIDLEKW:
					as.setPctIdlekW(parser.makeDouble());
					break;
				case propCLASS:
					as.setStorageClass(parser.makeInteger());
					break;
				case propDISPOUTTRIG:
					as.setDischargeTrigger(parser.makeDouble());
					break;
				case propDISPINTRIG:
					as.setChargeTrigger(parser.makeDouble());
					break;
				case propCHARGEEFF:
					as.setPctChargeEff(parser.makeDouble());
					break;
				case propDISCHARGEEFF:
					as.setPctDischargeEff(parser.makeDouble());
					break;
				case propPCTKWOUT:
					as.setPctKWOut(parser.makeDouble());
					break;
				case propVMINPU:
					as.setVminpu(parser.makeDouble());
					break;
				case propVMAXPU:
					as.setVmaxpu(parser.makeDouble());
					break;
				case propSTATE:
					as.setState(as.interpretState(Param));
					break;
				case propKVA:
					as.setkVArating(parser.makeDouble());
					break;
				case propKWRATED:
					as.setkWrating(parser.makeDouble());
					break;
				case propKWHRATED:
					as.setkWhRating(parser.makeDouble());
					break;
				case propKWHSTORED:
					as.setkWhStored(parser.makeDouble());
					break;
				case propPCTRESERVE:
					as.setPctReserve(parser.makeDouble());
					break;
				case propUSERMODEL:
					as.getUserModel().setName(parser.makeString());  // Connect to user written models
					break;
				case propUSERDATA:
					as.getUserModel().edit(parser.makeString());  // Send edit string to user model
					break;
				case propDEBUGTRACE:
					as.setDebugTrace(Utilities.interpretYesNo(Param));
					break;
				case propPCTKWIN:
					as.setPctKWin(parser.makeDouble());
					break;
				case propPCTSTORED:
					as.setkWhStored(parser.makeDouble() * 0.01 * as.getkWhRating());
					break;
				case propCHARGETIME:
					as.setChargeTime(parser.makeDouble());
					break;
				default:
					// Inherited parameters
					classEdit(getActiveStorageObj(), ParamPointer - NumPropsThisClass);
					break;
				}

				switch (iCase) {
				case 0:
					setNcondsForConnection();  // Force Reallocation of terminal info
					break;
				case propKW:
					as.syncUpPowerQuantities();   // keep kvar nominal up to date with kW and PF
					break;
				case propPF:
					as.syncUpPowerQuantities();
					break;

					/* Set loadshape objects;  returns nil If not valid */
				case propYEARLY:
					as.setYearlyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(as.getYearlyShape()));
					break;
				case propDAILY:
					as.setDailyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(as.getDailyShape()));
					break;
				case propDUTY:
					as.setDutyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(as.getDutyShape()));
					break;
				case propKWRATED:
					as.setkVArating(as.getkWrating());
					break;
				case propKWHRATED:
					as.setkWhStored(as.getkWhRating());  // Assume fully charged
					as.setkWhReserve(as.getkWhRating() * as.getPctReserve() * 0.01);
					break;

				case propPCTRESERVE:
					as.setkWhReserve(as.getkWhRating() * as.getPctReserve() * 0.01);
					break;

				case propDEBUGTRACE:
					if (as.isDebugTrace()) {
						try {
							// Init trace file
							File TraceFile = new File(Globals.getDSSDataDirectory() + "STOR_"+as.getName()+".csv");
							FileWriter TraceStream = new FileWriter(TraceFile, false);
							BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

							TraceBuffer.write("t, Iteration, LoadMultiplier, Mode, LoadModel, StorageModel,  Qnominalperphase, Pnominalperphase, CurrentType");
							for (i = 0; i < as.getNPhases(); i++)
								TraceBuffer.write(", |Iinj" + String.valueOf(i) + "|");
							for (i = 0; i < as.getNPhases(); i++)
								TraceBuffer.write(", |Iterm"+ String.valueOf(i) + "|");
							for (i = 0; i < as.getNPhases(); i++)
								TraceBuffer.write(", |Vterm" + String.valueOf(i) + "|");
							TraceBuffer.write(",Vthev, Theta");
							TraceBuffer.newLine();

							TraceBuffer.close();
							TraceStream.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					break;

				case propKVA:
					as.setkVANotSet(false);
					break;
				}
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		as.recalcElementData();
		as.setYprimInvalid(true);

		return Result;
	}

	/**
	 * Copy over essential properties from other object.
	 */
	@Override
	protected int makeLike(String OtherStorageObjName) {
		int Result = 0;
		/* See If we can find this line name in the present collection */
		StorageObj OtherStorageObj = (StorageObj) find(OtherStorageObjName);
		if (OtherStorageObj != null) {
			StorageObj as = getActiveStorageObj();

			if (as.getNPhases() != OtherStorageObj.getNPhases()) {
				as.setNPhases(OtherStorageObj.getNPhases());
				as.setNConds(as.getNPhases());  // Forces reallocation of terminal stuff
				as.setYorder(as.getNConds() * as.getNTerms());
				as.setYprimInvalid(true);
			}

			as.setkVStorageBase(OtherStorageObj.getkVStorageBase());
			as.setVBase(OtherStorageObj.getVBase());
			as.setVminpu(OtherStorageObj.getVminpu());
			as.setVmaxpu(OtherStorageObj.getVmaxpu());
			as.setVBase95(OtherStorageObj.getVBase95());
			as.setVBase105(OtherStorageObj.getVBase105());
			as.setkW_out(OtherStorageObj.getkW_out());
			as.setKvar_out(OtherStorageObj.getKvar_out());
			as.setPNominalPerPhase(OtherStorageObj.getPNominalPerPhase());
			as.setPowerFactor(OtherStorageObj.getPowerFactor());
			as.setQNominalPerPhase(OtherStorageObj.getQNominalPerPhase());
			as.setConnection(OtherStorageObj.getConnection());
			as.setYearlyShape(OtherStorageObj.getYearlyShape());
			as.setYearlyShapeObj(OtherStorageObj.getYearlyShapeObj());
			as.setDailyShape(OtherStorageObj.getDailyShape());
			as.setDailyShapeObj(OtherStorageObj.getDailyShapeObj());
			as.setDutyShape(OtherStorageObj.getDutyShape());
			as.setDutyShapeObj(OtherStorageObj.getDutyShapeObj());
			as.setDispatchMode(OtherStorageObj.getDispatchMode());
			as.setStorageClass(OtherStorageObj.getStorageClass());
			as.setVoltageModel(OtherStorageObj.getVoltageModel());

			as.setState(OtherStorageObj.getState());
			as.setStateChanged(OtherStorageObj.isStateChanged());
			as.setkVANotSet(OtherStorageObj.iskVANotSet());

			as.setkVArating(OtherStorageObj.getkVArating());

			as.setkWrating(OtherStorageObj.getkWrating());
			as.setkWhRating(OtherStorageObj.getkWhRating());
			as.setkWhStored(OtherStorageObj.getkWhStored());
			as.setkWhReserve(OtherStorageObj.getkWhReserve());
			as.setPctReserve(OtherStorageObj.getPctReserve());
			as.setDischargeTrigger(OtherStorageObj.getDischargeTrigger());
			as.setChargeTrigger(OtherStorageObj.getChargeTrigger());
			as.setPctChargeEff(OtherStorageObj.getPctChargeEff());
			as.setPctDischargeEff(OtherStorageObj.getPctDischargeEff());
			as.setPctKWOut(OtherStorageObj.getPctKWOut());
			as.setPctKWin(OtherStorageObj.getPctKWin());
			as.setPctIdlekW(OtherStorageObj.getPctIdlekW());
			as.setPctIdlekvar(OtherStorageObj.getPctIdlekvar());
			as.setChargeTime(OtherStorageObj.getChargeTime());

			as.setPctR(OtherStorageObj.getPctR());
			as.setPctX(OtherStorageObj.getPctX());

			as.setRandomMult(OtherStorageObj.getRandomMult());

			as.getUserModel().setName(OtherStorageObj.getUserModel().getName());  // Connect to user written models

			classMakeLike(OtherStorageObj);

			for (int i = 0; i < as.getParentClass().getNumProperties(); i++)
				as.setPropertyValue(i, OtherStorageObj.getPropertyValue(i));

			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Storage makeLike: \"" + OtherStorageObjName + "\" Not Found.", 562);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {

		StorageObj pElem;

		if (Handle == 0) {  // init all
			for (int i = 0; i < ElementList.size(); i++) {
				pElem = (StorageObj) ElementList.get(i);
				pElem.randomize(0);
			}
		} else {
			setActiveElement(Handle);
			pElem = (StorageObj) getActiveObj();
			pElem.randomize(0);
		}

		DSSGlobals.getInstance().doSimpleMsg("Need to implement Storage.init", -1);
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
		for (int i = 0; i < ElementList.size(); i++) {
			pElem = (StorageObj) ElementList.get(i);
			if (pElem.isEnabled())
				pElem.takeSample();
		}
	}

	public String[] getRegisterNames() {
		return RegisterNames;
	}

	public void setRegisterNames(String[] registerNames) {
		RegisterNames = registerNames;
	}

	public static StorageObj getActiveStorageObj() {
		return ActiveStorageObj;
	}

	public static void setActiveStorageObj(StorageObj activeStorageObj) {
		ActiveStorageObj = activeStorageObj;
	}

}

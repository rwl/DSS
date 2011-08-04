package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PVSystem;
import com.epri.dss.conversion.PVSystemObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class PVSystemImpl extends PCClassImpl implements PVSystem {

	private static PVSystemObj ActivePVsystemObj;

	private String[] RegisterNames = new String[NumPVSystemRegisters];

	public PVSystemImpl() {
		super();
		this.Class_Name = "PVSystem";
		this.DSSClassType = DSSClassType + DSSClassDefs.PVSYSTEM_ELEMENT;  // In both PCelement and PVSystem element list

		this.ActiveElement = -1;

		// Set register names
		this.RegisterNames[0]  = "kWh";
		this.RegisterNames[1]  = "kvarh";
		this.RegisterNames[2]  = "Max kW";
		this.RegisterNames[3]  = "Max kVA";
		this.RegisterNames[4]  = "Hours";

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		NumProperties = PVSystem.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();  /* see DSSClass */

		// Define property names
		addProperty("phases", 0,
				"Number of Phases, this PVSystem element.  Power is evenly divided among phases.");
		addProperty("bus1", 1,
				"Bus to which the PVSystem element is connected.  May include specific node specification.");
		addProperty("kv", PVSystem.propKV,
				"Nominal rated (1.0 per unit) voltage, kV, for PVSystem element. For 2- and 3-phase PVSystem elements, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the PVSystem element. "+
				"If 1-phase wye (star or LN), specify phase-neutral kV. "+
				"If 1-phase delta or phase-phase connected, specify phase-phase kV.");  // line-neutral voltage//  base voltage
		addProperty("irradiance", PVSystem.propIrradiance,
				"Get/set the present irradiance value in kW/sq-m. Used as base value for shape multipliers. "+
				"Generally entered as peak value for the time period of interest and the yearly, daily, and duty load shape " +
				"objects are defined as per unit multipliers (just like Loads/Generators)." );
		addProperty("Pmpp", PVSystem.propPmpp,
				"Get/set the rated max power of the PV array for 1.0 kW/sq-m irradiance and a user-selected array temperature. " +
				"The P-TCurve should be defined relative to the selected array temperature." );
		addProperty("Temperature", PVSystem.propTemp,
				"Get/set the present Temperature. Used as fixed value corresponding to PTCurve property. "+
				"A multiplier is obtained from the Pmpp-Temp curve and applied to the nominal Pmpp from the irradiance " +
				"to determine the net array output." );
		addProperty("pf", PVSystem.propPF,
				"Nominally, the power factor for the output power. Default is 1.0. " +
				"Setting this property will cause the inverter to operate in constant power factor mode." +
				"Enter negative when kW and kvar have opposite signs."+CRLF+
				"A positive power factor signifies that the PVSystem element produces vars " + CRLF +
				"as is typical for a generator.  ");
		addProperty("conn", PVSystem.propCONNECTION,
				"={wye|LN|delta|LL}.  Default is wye.");
		addProperty("kvar", PVSystem.propKVAR,
				"Get/set the present kvar value.  Setting this property forces the inverter to operate in constant kvar mode.");
		addProperty("kVA", propKVA,
				"kVA rating of inverter. Used as the base for Dynamics mode and Harmonics mode values.");
		addProperty("%Cutin", PVSystem.propCutin,
				"% cut-in power -- % of kVA rating of inverter. " +
				"When the inverter is OFF, the power from the array must be greater than this for the inverter to turn on.");
		addProperty("%Cutout", PVSystem.propCutout,
				"% cut-out power -- % of kVA rating of inverter. "+
				"When the inverter is ON, the inverter turns OFF when the power from the array drops below this valye.");

		addProperty("EffCurve", PVSystem.propInvEffCurve,
				"An XYCurve object, previously defined, that describes the PER UNIT efficiency vs PER UNIT of rated kVA for the inverter. " +
				"Inverter output power is discounted by the multiplier obtained from this curve.");

		addProperty("P-TCurve", PVSystem.propP_T_Curve,
				"An XYCurve object, previously defined, that describes the PV array PER UNIT Pmpp vs Temperature curve. " +
				"Temperature units must agree with the Temperature property and the Temperature shapes used for simulations. " +
				"The Pmpp values are specified in per unit of the Pmpp value for 1 kW/sq-m irradiance. " +
				"The value for the temperature at which Pmpp is defined should be 1.0. " +
				"The net array power is determined by the irradiance * Pmpp * f(Temperature)");
		addProperty("%R", propPCTR,
				"Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for " +
				"losses in power flow modes.");
		addProperty("%X", PVSystem.propPCTX,
				"Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. (Limits fault current to 2 pu.) " +
				"Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.");
		addProperty("model", PVSystem.propMODEL,
				"Integer code (default=1) for the model to use for power output variation with voltage. "+
				"Valid values are:" +CRLF+CRLF+
				"1:PVSystem element injects a CONSTANT kW at specified power factor."+CRLF+
				"2:PVSystem element is modeled as a CONSTANT ADMITTANCE."  +CRLF+
				"3:Compute load injection from User-written Model.");

		addProperty("Vminpu", PVSystem.propVMINPU,
				"Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.");
		addProperty("Vmaxpu", PVSystem.propVMAXPU,
				"Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.");
		addProperty("yearly", PVSystem.propYEARLY,
				"Dispatch shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. If this is not specified, the Daily dispatch shape, If any, is repeated "+
				"during Yearly solution modes. In the default dispatch mode, " +
				"the PVSystem element uses this loadshape to trigger State changes.");
		addProperty("daily", propDAILY,
				"Dispatch shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, "+
				"the PVSystem element uses this loadshape to trigger State changes."); // daily dispatch (hourly)
		addProperty("duty", PVSystem.propDUTY,
				"Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a Loadshape object. "+
				"Typically would have time intervals of 1-5 seconds. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat.");  // as for wind generation

		addProperty("Tyearly", PVSystem.propTYEARLY,
				"Temperature shape to use for yearly simulations.  Must be previously defined "+
				"as a TShape object. If this is not specified, the Daily dispatch shape, If any, is repeated "+
				"during Yearly solution modes. " +
				"The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve.");
		addProperty("Tdaily", PVSystem.propTDAILY,
				"Temperature shape to use for daily simulations.  Must be previously defined "+
				"as a TShape object of 24 hrs, typically.  "+
				"The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve."); // daily dispatch (hourly)
		addProperty("Tduty", PVSystem.propTDUTY,
				"Temperature shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a TShape object. "+
				"Typically would have time intervals of 1-5 seconds. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat. " +
				"The PVSystem model uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve.");  // Cloud transient simulation
		addProperty("class", propCLASS,
				"An arbitrary integer number representing the class of PVSystem element so that PVSystem values may "+
				"be segregated by class."); // integer

		addProperty("UserModel", propUSERMODEL,
				"Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, " +
				"overriding the default model.  Set to \"none\" to negate previous setting.");
		addProperty("UserData", PVSystem.propUSERDATA,
				"String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.");
		addProperty("debugtrace", PVSystem.propDEBUGTRACE,
				"{Yes | No }  Default is no.  Turn this on to capture the progress of the PVSystem model " +
				"for each iteration.  Creates a separate file for each PVSystem element named \"PVSystem_name.csv\"." );

		ActiveProperty = PVSystem.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list

		// Override default help string
		PropertyHelp[PVSystem.NumPropsThisClass] = "Name of harmonic voltage or current spectrum for this PVSystem element. " +
				"Current injection is assumed for inverter. " +
				"Default value is \"default\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new PVSystemObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void setNcondsForConnection() {
		PVSystemObj apv = getActivePVsystemObj();

		switch (apv.getConnection()) {
		case 0:
			apv.setNConds(apv.getNPhases() + 1);
		case 1:
			switch (apv.getNPhases()) {
			case 1:
				apv.setNConds(apv.getNPhases() + 1);  // L-L
			case 2:
				apv.setNConds(apv.getNPhases() + 1);  // Open-delta
			default:
				apv.setNConds(apv.getNPhases());
			}
		}
	}

	public void updateAll() {
		for (int i = 0; i < ElementList.size(); i++) {
			PVSystemObj pElem = (PVSystemObj) ElementList.get(i);
			if (pElem.isEnabled())
				pElem.updatePVSystem();
		}
	}

	/**
	 * Accepts:
	 *   delta or LL           (Case insensitive)
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		PVSystemObj apv = getActivePVsystemObj();

		switch (S.toLowerCase().charAt(0)) {
		case 'y':
			apv.setConnection(0);  /* Wye */
		case 'w':
			apv.setConnection(0);  /* Wye */
		case 'd':
			apv.setConnection(1);  /* Delta or line-Line */
		case 'l':
			switch (S.toLowerCase().charAt(1)) {
			case 'n':
				apv.setConnection(0);
			case 'l':
				apv.setConnection(1);
			}
		}

		setNcondsForConnection();

		/* VBase is always L-N voltage unless 1-phase device or more than 3 phases */

		switch (apv.getNPhases()) {
		case 2:
			apv.setVBase(apv.getkVPVSystemBase() * DSSGlobals.InvSQRT3x1000);  // L-N Volts
		case 3:
			apv.setVBase(apv.getkVPVSystemBase() * DSSGlobals.InvSQRT3x1000);  // L-N Volts
		default:
			apv.setVBase(apv.getkVPVSystemBase() * 1000.0);  // Just use what is supplied
		}

		apv.setVBase95(apv.getVminpu() * apv.getVBase());
		apv.setVBase105(apv.getVmaxpu() * apv.getVBase());

		apv.setYorder(apv.getNConds() * apv.getNTerms());
		apv.setYprimInvalid(true);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActivePVsystemObj((PVSystemObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActivePVsystemObj());

		int iCase, Result = 0;

		PVSystemObj apv = getActivePVsystemObj();

		int ParamPointer = 0;
		String ParamName    = parser.getNextParam();  // Parse next property off the command line
		String Param        = parser.makeString();    // Put the string value of the property value in local memory for faster access
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;  // If it is not a named property, assume the next property
			} else {
				ParamPointer = CommandList.getCommand(ParamName);  // Look up the name in the list for this class
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties)) {
				apv.setPropertyValue(PropertyIdxMap[ParamPointer], Param);  // Update the string value of the property
			} else {
				Globals.doSimpleMsg("Unknown parameter \""+ParamName+"\" for PVSystem \""+apv.getName()+"\"", 560);
			}

			if (ParamPointer >= 0) {
				iCase = PropertyIdxMap[ParamPointer];
				switch (iCase) {
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ apv.getName() + "\"", 561);
				case 0:
					apv.setNPhases(parser.makeInteger());  // num phases
				case 1:
					apv.setBus(1, Param);  // TODO Check zero based indexing
				case propKV:
					apv.setPresentkV(parser.makeDouble());
				case propIrradiance:
					apv.setIrradiance(parser.makeDouble());
				case propPF:
					apv.setPFSpecified(true);
					apv.setKvarSpecified(false);
					apv.setPFnominal(parser.makeDouble());
				case propMODEL:
					apv.setVoltageModel(parser.makeInteger());
				case propYEARLY:
					apv.setYearlyShape(Param);
				case propDAILY:
					apv.setDailyShape(Param);
				case propDUTY:
					apv.setDutyShape(Param);
				case propTYEARLY:
					apv.setYearlyTShape(Param);
				case propTDAILY:
					apv.setDailyTShape(Param);
				case propTDUTY:
					apv.setDutyTShape(Param);
				case propCONNECTION:
					interpretConnection(Param);
				case propKVAR:
					apv.setKvarSpecified(true);
					apv.setPFSpecified(false);
					apv.setPresentkvar(parser.makeDouble());
				case propPCTR:
					apv.setPctR(parser.makeDouble());
				case propPCTX:
					apv.setPctX(parser.makeDouble());
				case propCLASS:
					apv.setFClass(parser.makeInteger());
				case propInvEffCurve:
					apv.setInverterCurve(Param);
				case propTemp:
					apv.setTemperature(parser.makeDouble());
				case propPmpp:
					apv.setPmpp(parser.makeDouble());
				case propP_T_Curve:
					apv.setPower_TempCurve(Param);
				case propCutin:
					apv.setPctCutIn(parser.makeDouble());
				case propCutout:
					apv.setPctCutOut(parser.makeDouble());
				case propVMINPU:
					apv.setVminpu(parser.makeDouble());
				case propVMAXPU:
					apv.setVmaxpu(parser.makeDouble());
				case propKVA:
					apv.setkVArating(parser.makeDouble());
				case propUSERMODEL:
					apv.getUserModel().setName(parser.makeString());  // Connect to user written models
				case propUSERDATA:
					apv.getUserModel().edit(parser.makeString());  // Send edit string to user model
				case propDEBUGTRACE:
					apv.setDebugTrace(Utilities.interpretYesNo(Param));
				default:
					// Inherited parameters
					classEdit(getActivePVsystemObj(), ParamPointer - NumPropsThisClass);
				}

				switch (iCase) {
				case 0:
					setNcondsForConnection();  // Force Reallocation of terminal info

				/* Set loadshape objects;  returns nil If not valid */
				case propYEARLY:
					apv.setYearlyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(apv.getYearlyShape()) );
				case propDAILY:
					apv.setDailyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(apv.getDailyShape()) );
				case propDUTY:
					apv.setDutyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(apv.getDutyShape()) );

				case propTYEARLY:
					apv.setYearlyTShapeObj( (TShapeObj) Globals.getTShapeClass().find(apv.getYearlyTShape()) );
				case propTDAILY:
					apv.setDailyTShapeObj( (TShapeObj) Globals.getTShapeClass().find(apv.getDailyTShape()) );
				case propTDUTY:
					apv.setDutyTShapeObj( (TShapeObj) Globals.getTShapeClass().find(apv.getDutyTShape()) );

				case propInvEffCurve:
					apv.setInverterCurveObj( (XYCurveObj) Globals.getXYCurveClass().find(apv.getInverterCurve()) );
				case propP_T_Curve:
					apv.setPower_TempCurveObj( (XYCurveObj) Globals.getXYCurveClass().find(apv.getPower_TempCurve()) );

				case propDEBUGTRACE:
					if (apv.isDebugTrace()) {  // Init trace file
						try {
							FileWriter TraceStream = new FileWriter(Globals.getDSSDataDirectory() + "STOR_"+apv.getName()+".csv", false);
							BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

							TraceBuffer.write("t, Iteration, LoadMultiplier, Mode, LoadModel, PVSystemModel,  Qnominalperphase, Pnominalperphase, CurrentType");
							for (int i = 0; i < apv.getNPhases(); i++)
								TraceBuffer.write(", |Iinj"+String.valueOf(i)+"|");
							for (int i = 0; i < apv.getNPhases(); i++)
								TraceBuffer.write(", |Iterm"+String.valueOf(i)+"|");
							for (int i = 0; i < apv.getNPhases(); i++)
								TraceBuffer.write(", |Vterm"+String.valueOf(i)+"|");
							TraceBuffer.write(",Vthev, Theta");
							TraceBuffer.newLine();

							TraceBuffer.close();
							TraceStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		apv.recalcElementData();
		apv.setYprimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherPVSystemObjName) {
		int Result = 0;
		/* See If we can find this line name in the present collection */
		PVSystemObj OtherPVsystemObj = (PVSystemObj) find(OtherPVSystemObjName);
		if (OtherPVsystemObj != null) {
			PVSystemObj apv = getActivePVsystemObj();

			if (apv.getNPhases() != OtherPVsystemObj.getNPhases()) {
				apv.setNPhases(OtherPVsystemObj.getNPhases());
				apv.setNConds(apv.getNPhases());  // Forces reallocation of terminal stuff
				apv.setYorder(apv.getNConds() * apv.getNTerms());
				apv.setYprimInvalid(true);
			}

			apv.setkVPVSystemBase(OtherPVsystemObj.getkVPVSystemBase());
			apv.setVBase(OtherPVsystemObj.getVBase());
			apv.setVminpu(OtherPVsystemObj.getVminpu());
			apv.setVmaxpu(OtherPVsystemObj.getVmaxpu());
			apv.setVBase95(OtherPVsystemObj.getVBase95());
			apv.setVBase105(OtherPVsystemObj.getVBase105());
			apv.setkW_out(OtherPVsystemObj.getkW_out());
			apv.setKvar_out(OtherPVsystemObj.getKvar_out());
			apv.setPnominalperphase(OtherPVsystemObj.getPnominalperphase());
			apv.setPFnominal(OtherPVsystemObj.getPFnominal());
			apv.setQnominalperphase(OtherPVsystemObj.getQnominalperphase());
			apv.setConnection(OtherPVsystemObj.getConnection());
			apv.setYearlyShape(OtherPVsystemObj.getYearlyShape());
			apv.setYearlyShapeObj(OtherPVsystemObj.getYearlyShapeObj());
			apv.setDailyShape(OtherPVsystemObj.getDailyShape());
			apv.setDailyShapeObj(OtherPVsystemObj.getDailyShapeObj());
			apv.setDutyShape(OtherPVsystemObj.getDutyShape());
			apv.setDutyShapeObj(OtherPVsystemObj.getDutyShapeObj());
			apv.setYearlyTShape(OtherPVsystemObj.getYearlyTShape());
			apv.setYearlyTShapeObj(OtherPVsystemObj.getYearlyTShapeObj());
			apv.setDailyTShape(OtherPVsystemObj.getDailyTShape());
			apv.setDailyTShapeObj(OtherPVsystemObj.getDailyTShapeObj());
			apv.setDutyTShape(OtherPVsystemObj.getDutyTShape());
			apv.setDutyTShapeObj(OtherPVsystemObj.getDutyTShapeObj());
			apv.setInverterCurve(OtherPVsystemObj.getInverterCurve());
			apv.setInverterCurveObj(OtherPVsystemObj.getInverterCurveObj());
			apv.setPower_TempCurve(OtherPVsystemObj.getPower_TempCurve());
			apv.setPower_TempCurveObj(OtherPVsystemObj.getPower_TempCurveObj());
			apv.setFClass(OtherPVsystemObj.getFClass());
			apv.setVoltageModel(OtherPVsystemObj.getVoltageModel());

			apv.setTemperature(OtherPVsystemObj.getTemperature());
			apv.setPmpp(OtherPVsystemObj.getPmpp());
			apv.setPctCutIn(OtherPVsystemObj.getPctCutIn());
			apv.setPctCutOut(OtherPVsystemObj.getPctCutOut());
			apv.setIrradiance(OtherPVsystemObj.getIrradiance());

			apv.setkVArating(OtherPVsystemObj.getkVArating());

			apv.setPctR(OtherPVsystemObj.getPctR());
			apv.setPctX(OtherPVsystemObj.getPctX());

			apv.setRandomMult(OtherPVsystemObj.getRandomMult());

			apv.getUserModel().setName(OtherPVsystemObj.getUserModel().getName());  // Connect to user written models

			classMakeLike(OtherPVsystemObj);

			for (int i = 0; i < apv.getParentClass().getNumProperties(); i++)
				apv.setPropertyValue(i, OtherPVsystemObj.getPropertyValue(i));

			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in PVSystem makeLike: \"" + OtherPVSystemObjName + "\" Not Found.", 562);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		PVSystemObj p;

		if (Handle == 0) {  // init all
			p = (PVSystemObj) ElementList.getFirst();
			while (p != null) {
				p.randomize(0);
				p = (PVSystemObj) ElementList.getNext();
			}
		} else {
			setActive(Handle);
			p = (PVSystemObj) getActiveObj();
			p.randomize(0);
		}

		DSSGlobals.getInstance().doSimpleMsg("Need to implement PVSystem.init", -1);
		return 0;
	}

	public void resetRegistersAll() {
		int idx = getFirst();
		while (idx > 0) {
			((PVSystemObj) getActiveObj()).resetRegisters();
			idx = getNext();
		}
	}

	public void sampleAll() {
		PVSystemObj pElem;
		for (int i = 0; i < ElementList.size(); i++) {
			pElem = (PVSystemObj) ElementList.get(i);
			if (pElem.isEnabled())
				pElem.takeSample();
		}
	}

	public void setRegisterNames(String[] registerNames) {
		RegisterNames = registerNames;
	}

	public String[] getRegisterNames() {
		return RegisterNames;
	}

	public static void setActivePVsystemObj(PVSystemObj activePVsystemObj) {
		ActivePVsystemObj = activePVsystemObj;
	}

	public static PVSystemObj getActivePVsystemObj() {
		return ActivePVsystemObj;
	}

}

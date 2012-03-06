package com.ncond.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.conversion.PVSystem;
import com.ncond.dss.conversion.PVSystemObj;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.TShapeObj;
import com.ncond.dss.general.XYCurveObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CommandListImpl;

public class PVSystemImpl extends PCClassImpl implements PVSystem {

	public static PVSystemObj activePVSystemObj;

	private String[] registerNames = new String[NumPVSystemRegisters];

	public PVSystemImpl() {
		super();
		className = "PVSystem";
		classType = classType + DSSClassDefs.PVSYSTEM_ELEMENT;  // in both PCElement and PVSystem element list

		activeElement = -1;

		// set register names
		registerNames[0] = "kWh";
		registerNames[1] = "kvarh";
		registerNames[2] = "Max kW";
		registerNames[3] = "Max kVA";
		registerNames[4] = "Hours";

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = PVSystem.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();  /* see DSSClass */

		// define property names
		addProperty("phases", 0,
				"Number of Phases, this PVSystem element.  Power is evenly divided among phases.");
		addProperty("bus1", 1,
				"Bus to which the PVSystem element is connected.  May include specific node specification.");
		addProperty("kv", PVSystem.KV,
				"Nominal rated (1.0 per unit) voltage, kV, for PVSystem element. For 2- and 3-phase PVSystem elements, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the PVSystem element. "+
				"If 1-phase wye (star or LN), specify phase-neutral kV. "+
				"If 1-phase delta or phase-phase connected, specify phase-phase kV.");  // line-neutral voltage//  base voltage
		addProperty("irradiance", PVSystem.IRRADIANCE,
				"Get/set the present irradiance value in kW/sq-m. Used as base value for shape multipliers. "+
				"Generally entered as peak value for the time period of interest and the yearly, daily, and duty load shape " +
				"objects are defined as per unit multipliers (just like Loads/Generators)." );
		addProperty("Pmpp", PVSystem.PMPP,
				"Get/set the rated max power of the PV array for 1.0 kW/sq-m irradiance and a user-selected array temperature. " +
				"The P-TCurve should be defined relative to the selected array temperature." );
		addProperty("Temperature", PVSystem.TEMP,
				"Get/set the present Temperature. Used as fixed value corresponding to PTCurve property. "+
				"A multiplier is obtained from the Pmpp-Temp curve and applied to the nominal Pmpp from the irradiance " +
				"to determine the net array output." );
		addProperty("pf", PVSystem.PF,
				"Nominally, the power factor for the output power. Default is 1.0. " +
				"Setting this property will cause the inverter to operate in constant power factor mode." +
				"Enter negative when kW and kvar have opposite signs."+CRLF+
				"A positive power factor signifies that the PVSystem element produces vars " + CRLF +
				"as is typical for a generator.  ");
		addProperty("conn", PVSystem.CONNECTION,
				"={wye|LN|delta|LL}.  Default is wye.");
		addProperty("kvar", PVSystem.KVAR,
				"Get/set the present kvar value.  Setting this property forces the inverter to operate in constant kvar mode.");
		addProperty("kVA", KVA,
				"kVA rating of inverter. Used as the base for Dynamics mode and Harmonics mode values.");
		addProperty("%Cutin", PVSystem.CUT_IN,
				"% cut-in power -- % of kVA rating of inverter. " +
				"When the inverter is OFF, the power from the array must be greater than this for the inverter to turn on.");
		addProperty("%Cutout", PVSystem.CUT_OUT,
				"% cut-out power -- % of kVA rating of inverter. "+
				"When the inverter is ON, the inverter turns OFF when the power from the array drops below this valye.");

		addProperty("EffCurve", PVSystem.INV_EFF_CURVE,
				"An XYCurve object, previously defined, that describes the PER UNIT efficiency vs PER UNIT of rated kVA for the inverter. " +
				"Inverter output power is discounted by the multiplier obtained from this curve.");

		addProperty("P-TCurve", PVSystem.P_T_CURVE,
				"An XYCurve object, previously defined, that describes the PV array PER UNIT Pmpp vs Temperature curve. " +
				"Temperature units must agree with the Temperature property and the Temperature shapes used for simulations. " +
				"The Pmpp values are specified in per unit of the Pmpp value for 1 kW/sq-m irradiance. " +
				"The value for the temperature at which Pmpp is defined should be 1.0. " +
				"The net array power is determined by the irradiance * Pmpp * f(Temperature)");
		addProperty("%R", PCTR,
				"Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for " +
				"losses in power flow modes.");
		addProperty("%X", PVSystem.PCTX,
				"Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source" +
				" for harmonics and dynamics modes. (Limits fault current to 2 pu.) " +
				"Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.");
		addProperty("model", PVSystem.MODEL,
				"Integer code (default=1) for the model to use for power output variation with voltage. "+
				"Valid values are:" +CRLF+CRLF+
				"1:PVSystem element injects a CONSTANT kW at specified power factor."+CRLF+
				"2:PVSystem element is modeled as a CONSTANT ADMITTANCE."  +CRLF+
				"3:Compute load injection from User-written Model.");

		addProperty("Vminpu", PVSystem.VMIN_PU,
				"Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.");
		addProperty("Vmaxpu", PVSystem.VMAX_PU,
				"Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.");
		addProperty("yearly", PVSystem.YEARLY,
				"Dispatch shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. If this is not specified, the Daily dispatch shape, If any, is repeated "+
				"during Yearly solution modes. In the default dispatch mode, " +
				"the PVSystem element uses this loadshape to trigger State changes.");
		addProperty("daily", DAILY,
				"Dispatch shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, "+
				"the PVSystem element uses this loadshape to trigger State changes."); // daily dispatch (hourly)
		addProperty("duty", PVSystem.DUTY,
				"Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a Loadshape object. "+
				"Typically would have time intervals of 1-5 seconds. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat.");  // as for wind generation

		addProperty("Tyearly", PVSystem.T_YEARLY,
				"Temperature shape to use for yearly simulations.  Must be previously defined "+
				"as a TShape object. If this is not specified, the Daily dispatch shape, If any, is repeated "+
				"during Yearly solution modes. " +
				"The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve.");
		addProperty("Tdaily", PVSystem.T_DAILY,
				"Temperature shape to use for daily simulations.  Must be previously defined "+
				"as a TShape object of 24 hrs, typically.  "+
				"The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve."); // daily dispatch (hourly)
		addProperty("Tduty", PVSystem.T_DUTY,
				"Temperature shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. " +
				"Must be previously defined as a TShape object. "+
				"Typically would have time intervals of 1-5 seconds. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat. " +
				"The PVSystem model uses this TShape to determine the Pmpp from the Pmpp vs T curve. " +
				"Units must agree with the Pmpp vs T curve.");  // Cloud transient simulation
		addProperty("class", CLASS,
				"An arbitrary integer number representing the class of PVSystem element so that PVSystem values may "+
				"be segregated by class."); // integer

		addProperty("UserModel", USER_MODEL,
				"Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, " +
				"overriding the default model.  Set to \"none\" to negate previous setting.");
		addProperty("UserData", PVSystem.USER_DATA,
				"String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.");
		addProperty("debugtrace", PVSystem.DEBUG_TRACE,
				"{Yes | No }  Default is no.  Turn this on to capture the progress of the PVSystem model " +
				"for each iteration.  Creates a separate file for each PVSystem element named \"PVSystem_name.csv\"." );

		activeProperty = PVSystem.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override default help string
		propertyHelp[PVSystem.NumPropsThisClass] = "Name of harmonic voltage or current spectrum for this PVSystem element. " +
				"Current injection is assumed for inverter. " +
				"Default value is \"default\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {

		DSS.activeCircuit.setActiveCktElement(new PVSystemObjImpl(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void setNCondsForConnection() {
		PVSystemObj apv = activePVSystemObj;

		switch (apv.getConnection()) {
		case 0:
			apv.setNumConds(apv.getNumPhases() + 1);
			break;
		case 1:
			switch (apv.getNumPhases()) {
			case 1:
				apv.setNumConds(apv.getNumPhases() + 1);  // L-L
				break;
			case 2:
				apv.setNumConds(apv.getNumPhases() + 1);  // open-delta
				break;
			default:
				apv.setNumConds(apv.getNumPhases());
				break;
			}
			break;
		}
	}

	public void updateAll() {
		for (int i = 0; i < elementList.size(); i++) {
			PVSystemObj pElem = (PVSystemObj) elementList.get(i);
			if (pElem.isEnabled())
				pElem.updatePVSystem();
		}
	}

	/**
	 * Accepts (case insensitive):
	 *   delta or LL
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		PVSystemObj apv = activePVSystemObj;

		switch (s.toLowerCase().charAt(0)) {
		case 'y':
			apv.setConnection(0);  /* Wye */
			break;
		case 'w':
			apv.setConnection(0);  /* Wye */
			break;
		case 'd':
			apv.setConnection(1);  /* Delta or Line-Line */
			break;
		case 'l':
			switch (s.toLowerCase().charAt(1)) {
			case 'n':
				apv.setConnection(0);
				break;
			case 'l':
				apv.setConnection(1);
				break;
			}
			break;
		}

		setNCondsForConnection();

		/* VBase is always L-N voltage unless 1-phase device or more than 3 phases */

		switch (apv.getNumPhases()) {
		case 2:
			apv.setVBase(apv.getKVPVSystemBase() * DSS.InvSQRT3x1000);  // L-N volts
			break;
		case 3:
			apv.setVBase(apv.getKVPVSystemBase() * DSS.InvSQRT3x1000);  // L-N volts
			break;
		default:
			apv.setVBase(apv.getKVPVSystemBase() * 1000.0);  // just use what is supplied
			break;
		}

		apv.setVBase95(apv.getVMinPU() * apv.getVBase());
		apv.setVBase105(apv.getVMaxPU() * apv.getVBase());

		apv.setYOrder(apv.getNumConds() * apv.getNumTerms());
		apv.setYPrimInvalid(true);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activePVSystemObj = (PVSystemObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activePVSystemObj);

		int iCase, result = 0;

		PVSystemObj apv = activePVSystemObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();  // parse next property off the command line
		String param = parser.makeString();    // put the string value of the property value in local memory for faster access
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;  // if it is not a named property, assume the next property
			} else {
				paramPointer = commandList.getCommand(paramName);  // look up the name in the list for this class
			}

			if (paramPointer >= 0 && paramPointer < numProperties) {
				apv.setPropertyValue(propertyIdxMap[paramPointer], param);  // update the string value of the property
			} else {
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for PVSystem \""+apv.getName()+"\"", 560);
			}

			if (paramPointer >= 0) {
				iCase = propertyIdxMap[paramPointer];
				switch (iCase) {
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ apv.getName() + "\"", 561);
					break;
				case 0:
					apv.setNumPhases(parser.makeInteger());  // num phases
					break;
				case 1:
					apv.setBus(0, param);
					break;
				case KV:
					apv.setPresentKV(parser.makeDouble());
					break;
				case IRRADIANCE:
					apv.setIrradiance(parser.makeDouble());
					break;
				case PF:
					apv.setPFSpecified(true);
					apv.setKVArSpecified(false);
					apv.setPowerFactor(parser.makeDouble());
					break;
				case MODEL:
					apv.setVoltageModel(parser.makeInteger());
					break;
				case YEARLY:
					apv.setYearlyShape(param);
					break;
				case DAILY:
					apv.setDailyShape(param);
					break;
				case DUTY:
					apv.setDutyShape(param);
					break;
				case T_YEARLY:
					apv.setYearlyTShape(param);
					break;
				case T_DAILY:
					apv.setDailyTShape(param);
					break;
				case T_DUTY:
					apv.setDutyTShape(param);
					break;
				case CONNECTION:
					interpretConnection(param);
					break;
				case KVAR:
					apv.setKVArSpecified(true);
					apv.setPFSpecified(false);
					apv.setPresentKVAr(parser.makeDouble());
					break;
				case PCTR:
					apv.setPctR(parser.makeDouble());
					break;
				case PCTX:
					apv.setPctX(parser.makeDouble());
					break;
				case CLASS:
					apv.setFClass(parser.makeInteger());
					break;
				case INV_EFF_CURVE:
					apv.setInverterCurve(param);
					break;
				case TEMP:
					apv.setTemperature(parser.makeDouble());
					break;
				case PMPP:
					apv.setPmpp(parser.makeDouble());
					break;
				case P_T_CURVE:
					apv.setPowerTempCurve(param);
					break;
				case CUT_IN:
					apv.setPctCutIn(parser.makeDouble());
					break;
				case CUT_OUT:
					apv.setPctCutOut(parser.makeDouble());
					break;
				case VMIN_PU:
					apv.setVMinPU(parser.makeDouble());
					break;
				case VMAX_PU:
					apv.setVMaxPU(parser.makeDouble());
					break;
				case KVA:
					apv.setKVArating(parser.makeDouble());
					break;
				case USER_MODEL:
					apv.getUserModel().setName(parser.makeString());  // connect to user written models
					break;
				case USER_DATA:
					apv.getUserModel().edit(parser.makeString());  // send edit string to user model
					break;
				case DEBUG_TRACE:
					apv.setDebugTrace(Util.interpretYesNo(param));
					break;
				default:
					// inherited parameters
					classEdit(activePVSystemObj, paramPointer - NumPropsThisClass);
					break;
				}

				switch (iCase) {
				case 0:
					setNCondsForConnection();  // force reallocation of terminal info
					break;
				/* set loadshape objects; returns nil if not valid */
				case YEARLY:
					apv.setYearlyShapeObj( (LoadShapeObj) DSS.loadShapeClass.find(apv.getYearlyShape()) );
					break;
				case DAILY:
					apv.setDailyShapeObj( (LoadShapeObj) DSS.loadShapeClass.find(apv.getDailyShape()) );
					break;
				case DUTY:
					apv.setDutyShapeObj( (LoadShapeObj) DSS.loadShapeClass.find(apv.getDutyShape()) );
					break;

				case T_YEARLY:
					apv.setYearlyTShapeObj( (TShapeObj) DSS.TShapeClass.find(apv.getYearlyTShape()) );
					break;
				case T_DAILY:
					apv.setDailyTShapeObj( (TShapeObj) DSS.TShapeClass.find(apv.getDailyTShape()) );
					break;
				case T_DUTY:
					apv.setDutyTShapeObj( (TShapeObj) DSS.TShapeClass.find(apv.getDutyTShape()) );
					break;

				case INV_EFF_CURVE:
					apv.setInverterCurveObj( (XYCurveObj) DSS.XYCurveClass.find(apv.getInverterCurve()) );
					break;
				case P_T_CURVE:
					apv.setPowerTempCurveObj( (XYCurveObj) DSS.XYCurveClass.find(apv.getPowerTempCurve()) );
					break;

				case DEBUG_TRACE:
					if (apv.isDebugTrace()) {  // init trace file
						try {
							FileWriter fw = new FileWriter(DSS.dataDirectory + "STOR_"+apv.getName()+".csv", false);
							BufferedWriter bw = new BufferedWriter(fw);

							bw.write("t, Iteration, LoadMultiplier, Mode, LoadModel, PVSystemModel,  Qnominalperphase, Pnominalperphase, CurrentType");
							for (int i = 0; i < apv.getNumPhases(); i++)
								bw.write(", |Iinj"+String.valueOf(i)+"|");
							for (int i = 0; i < apv.getNumPhases(); i++)
								bw.write(", |Iterm"+String.valueOf(i)+"|");
							for (int i = 0; i < apv.getNumPhases(); i++)
								bw.write(", |Vterm"+String.valueOf(i)+"|");
							bw.write(",Vthev, Theta");
							bw.newLine();

							bw.close();
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				}
			}
			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		apv.recalcElementData();
		apv.setYPrimInvalid(true);

		return result;
	}

	@Override
	protected int makeLike(String otherPVSystemObjName) {
		int result = 0;
		/* See if we can find this line name in the present collection */
		PVSystemObj otherPVsystemObj = (PVSystemObj) find(otherPVSystemObjName);
		if (otherPVsystemObj != null) {
			PVSystemObj apv = activePVSystemObj;

			if (apv.getNumPhases() != otherPVsystemObj.getNumPhases()) {
				apv.setNumPhases(otherPVsystemObj.getNumPhases());
				apv.setNumConds(apv.getNumPhases());  // forces reallocation of terminal stuff
				apv.setYOrder(apv.getNumConds() * apv.getNumTerms());
				apv.setYPrimInvalid(true);
			}

			apv.setKVPVSystemBase(otherPVsystemObj.getKVPVSystemBase());
			apv.setVBase(otherPVsystemObj.getVBase());
			apv.setVMinPU(otherPVsystemObj.getVMinPU());
			apv.setVMaxPU(otherPVsystemObj.getVMaxPU());
			apv.setVBase95(otherPVsystemObj.getVBase95());
			apv.setVBase105(otherPVsystemObj.getVBase105());
			apv.setKWOut(otherPVsystemObj.getKWOut());
			apv.setKVArOut(otherPVsystemObj.getKVArOut());
			apv.setPNominalPerPhase(otherPVsystemObj.getPNominalPerPhase());
			apv.setPowerFactor(otherPVsystemObj.getPowerFactor());
			apv.setQNominalPerPhase(otherPVsystemObj.getQNominalPerPhase());
			apv.setConnection(otherPVsystemObj.getConnection());
			apv.setYearlyShape(otherPVsystemObj.getYearlyShape());
			apv.setYearlyShapeObj(otherPVsystemObj.getYearlyShapeObj());
			apv.setDailyShape(otherPVsystemObj.getDailyShape());
			apv.setDailyShapeObj(otherPVsystemObj.getDailyShapeObj());
			apv.setDutyShape(otherPVsystemObj.getDutyShape());
			apv.setDutyShapeObj(otherPVsystemObj.getDutyShapeObj());
			apv.setYearlyTShape(otherPVsystemObj.getYearlyTShape());
			apv.setYearlyTShapeObj(otherPVsystemObj.getYearlyTShapeObj());
			apv.setDailyTShape(otherPVsystemObj.getDailyTShape());
			apv.setDailyTShapeObj(otherPVsystemObj.getDailyTShapeObj());
			apv.setDutyTShape(otherPVsystemObj.getDutyTShape());
			apv.setDutyTShapeObj(otherPVsystemObj.getDutyTShapeObj());
			apv.setInverterCurve(otherPVsystemObj.getInverterCurve());
			apv.setInverterCurveObj(otherPVsystemObj.getInverterCurveObj());
			apv.setPowerTempCurve(otherPVsystemObj.getPowerTempCurve());
			apv.setPowerTempCurveObj(otherPVsystemObj.getPowerTempCurveObj());
			apv.setFClass(otherPVsystemObj.getFClass());
			apv.setVoltageModel(otherPVsystemObj.getVoltageModel());

			apv.setTemperature(otherPVsystemObj.getTemperature());
			apv.setPmpp(otherPVsystemObj.getPmpp());
			apv.setPctCutIn(otherPVsystemObj.getPctCutIn());
			apv.setPctCutOut(otherPVsystemObj.getPctCutOut());
			apv.setIrradiance(otherPVsystemObj.getIrradiance());

			apv.setKVArating(otherPVsystemObj.getKVARating());

			apv.setPctR(otherPVsystemObj.getPctR());
			apv.setPctX(otherPVsystemObj.getPctX());

			apv.setRandomMult(otherPVsystemObj.getRandomMult());

			apv.getUserModel().setName(otherPVsystemObj.getUserModel().getName());  // connect to user written models

			classMakeLike(otherPVsystemObj);

			for (int i = 0; i < apv.getParentClass().getNumProperties(); i++)
				apv.setPropertyValue(i, otherPVsystemObj.getPropertyValue(i));

			result = 1;
		} else {
			DSS.doSimpleMsg("Error in PVSystem makeLike: \"" + otherPVSystemObjName + "\" not found.", 562);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		PVSystemObj p;

		if (handle == 0) {  // init all
			p = (PVSystemObj) elementList.getFirst();
			while (p != null) {
				p.randomize(0);
				p = (PVSystemObj) elementList.getNext();
			}
		} else {
			setActiveElement(handle);
			p = (PVSystemObj) getActiveObj();
			p.randomize(0);
		}

		DSS.doSimpleMsg("Need to implement PVSystem.init", -1);
		return 0;
	}

	public void resetRegistersAll() {
		int idx = getFirst();
		while (idx >= 0) {
			((PVSystemObj) getActiveObj()).resetRegisters();
			idx = getNext();
		}
	}

	public void sampleAll() {
		PVSystemObj pElem;
		for (int i = 0; i < elementList.size(); i++) {
			pElem = (PVSystemObj) elementList.get(i);
			if (pElem.isEnabled())
				pElem.takeSample();
		}
	}

	public void setRegisterNames(String[] names) {
		registerNames = names;
	}

	public String[] getRegisterNames() {
		return registerNames;
	}

}

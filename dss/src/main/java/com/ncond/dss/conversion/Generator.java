/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Generator extends PCClass {

	/** Number of energy meter registers */
	public static final int NumGenRegisters = 6;
	public static final int NumGenVariables = 6;

	public static final int NumPropsThisClass = 36;

	public static GeneratorObj activeGeneratorObj;

	private String[] registerNames = new String[Generator.NumGenRegisters];

	public Generator() {
		super();

		className = "Generator";
		classType = classType + DSSClassDefs.GEN_ELEMENT;  // in both PCElement and GenElement list

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
		numProperties = Generator.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		addProperty("phases", 0, "Number of Phases, this Generator.  Power is evenly divided among phases.");
		addProperty("bus1", 1, "Bus to which the Generator is connected.  May include specific node specification.");
		addProperty("kv",  2,  "Nominal rated (1.0 per unit) voltage, kV, for Generator. For 2- and 3-phase Generators, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the Generator. "+
				"If wye (star), specify phase-neutral kV. "+
				"If delta or phase-phase connected, specify phase-phase kV.");  // line-neutral voltage//  base voltage
		addProperty("kW", 3, "Total base kW for the Generator.  A positive value denotes power coming OUT of the element, "+DSS.CRLF+
				"which is the opposite of a load. This value is modified depending on the dispatch mode. " +
				"Unaffected by the global load multiplier and growth curves. " +
				"If you want there to be more generation, you must add more generators or change this value.");
		addProperty("pf", 4, "Generator power factor. Default is 0.80. Enter negative for leading powerfactor "+
				"(when kW and kvar have opposite signs.)"+DSS.CRLF+
				"A positive power factor for a generator signifies that the generator produces vars " + DSS.CRLF +
				"as is typical for a synchronous generator.  Induction machines would be " +DSS.CRLF+
				"specified with a negative power factor.");
		addProperty("kvar", 12,   "Specify the base kvar.  Alternative to specifying the power factor.  Side effect: "+
				" the power factor value is altered to agree based on present value of kW.");
		addProperty("model", 5, "Integer code for the model to use for generation variation with voltage. "+
				"Valid values are:" +DSS.CRLF+DSS.CRLF+
				"1:Generator injects a constant kW at specified power factor."+DSS.CRLF+
				"2:Generator is modeled as a constant admittance."  +DSS.CRLF+
				"3:Const kW, constant kV.  Somewhat like a conventional transmission power flow P-V generator."+DSS.CRLF+
				"4:Const kW, Fixed Q (Q never varies)"+DSS.CRLF+
				"5:Const kW, Fixed Q(as a constant reactance)"+DSS.CRLF+
				"6:Compute load injection from User-written Model.(see usage of Xd, Xdp)"+DSS.CRLF+
				"7:Constant kW, kvar, but current limited below Vminpu. Approximates a simple inverter.");
		addProperty("Vminpu", 22,   "Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.");
		addProperty("Vmaxpu", 23, "Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.");
		addProperty("yearly", 6,  "Dispatch shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. If this is not specified, a constant value is assumed (no variation). "+
				"If the generator is assumed to be ON continuously, specify Status=FIXED, or "+
				"designate a curve that is 1.0 per unit at all times. "+
				"Set to NONE to reset to no loadahape. " +
				"Nominally for 8760 simulations.  If there are fewer points in the designated shape than "+
				"the number of points in the solution, the curve is repeated.");
		addProperty("daily", 7,  "Dispatch shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically.  If generator is assumed to be "+
				"ON continuously, specify Status=FIXED, or designate a Loadshape object"+
				"that is 1.0 perunit for all hours. " +
				"Set to NONE to reset to no loadahape. "       ); // daily dispatch (hourly)
		addProperty("duty", 8,  "Load shape to use for duty cycle dispatch simulations such as for wind generation. " +
				"Must be previously defined as a Loadshape object. "+
				"Typically would have time intervals less than 1 hr -- perhaps, in seconds. "+
				"Set Status=Fixed to ignore Loadshape designation. " +
				"Set to NONE to reset to no loadahape. " +
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat.");  // as for wind generation
		addProperty("dispmode", 9,   "{Default* | Loadlevel | Price } Default = Default. Dispatch mode. "+
				"In default mode, gen is either always on or follows dispatch curve as specified. "+
				"Otherwise, the gen comes on when either the global default load level (Loadshape \"default\") or the price level "+
				"exceeds the dispatch value."); // = 0 | >0
		addProperty("dispvalue", 10,  "Dispatch value. "+DSS.CRLF+
				"If = 0.0 (default) then Generator follow dispatch curves, if any. " +DSS.CRLF+
				"If > 0  then Generator is ON only when either the price signal (in Price dispatch mode) "+
				"exceeds this value or the active circuit load multiplier * \"default\" loadshape value * the default yearly growth factor " +
				"exceeds this value.  Then the generator follows dispatch curves (duty, daily, or yearly), if any (see also Status).");  // = 0 | >0
		addProperty("conn",  11,  "={wye|LN|delta|LL}.  Default is wye.");
		addProperty("Rneut", 13, "Removed due to causing confusion - Add neutral impedance externally.");
		addProperty("Xneut", 14, "Removed due to causing confusion - Add neutral impedance externally.");
		addProperty("status", 15,  "={Fixed | Variable*}.  If Fixed, then dispatch multipliers do not apply. "+
				"The generator is alway at full power when it is ON. "+
				" Default is Variable  (follows curves).");  // fixed or variable
		addProperty("class", 16,   "An arbitrary integer number representing the class of Generator so that Generator values may "+
				"be segregated by class."); // integer
		addProperty("Vpu", 17,  "Per Unit voltage set point for Model = 3  (typical power flow model).  Default is 1.0. "); // per unit set point voltage for power flow model
		addProperty("maxkvar", 18,  "Maximum kvar limit for Model = 3.  Defaults to twice the specified load kvar.  "+
				"Always reset this if you change PF or kvar properties.");
		addProperty("minkvar", 19,  "Minimum kvar limit for Model = 3. Enter a negative number if generator can absorb vars."+
				" Defaults to negative of Maxkvar.  Always reset this if you change PF or kvar properties.");
		addProperty("pvfactor", 20,  "Deceleration factor for P-V generator model (Model=3).  Default is 0.1. " +
				"If the circuit converges easily, you may want to use a higher number such as 1.0. " +
				"Use a lower number if solution diverges. Use Debugtrace=yes to create a file that will " +
				"trace the convergence of a generator model.");
		addProperty("forceon",  24, "{Yes | No}  Forces generator ON despite requirements of other dispatch modes. " +
				"Stays ON until this property is set to NO, or an internal algorithm cancels the forced ON state.");
		addProperty("kVA", 25, "kVA rating of electrical machine. Defaults to 1.2* kW if not specified. Applied to machine or inverter definition for Dynamics mode solutions. ");
		addProperty("MVA", 26, "MVA rating of electrical machine.  Alternative to using kVA=.");
		addProperty("Xd", 27,  "Per unit synchronous reactance of machine. Presently used only for Thevinen impedance for power flow calcs of user models (model=6). " +
				"Typically use a value 0.4 to 1.0. Default is 1.0");
		addProperty("Xdp",  28, "Per unit transient reactance of the machine.  Used for Dynamics mode and Fault studies.  Default is 0.27." +
				"For user models, this value is used for the Thevinen/Norton impedance for Dynamics Mode.");
		addProperty("Xdpp",  29, "Per unit subtransient reactance of the machine.  Used for Harmonics. Default is 0.20.");
		addProperty("H", 30,  "Per unit mass constant of the machine.  MW-sec/MVA.  Default is 1.0.");
		addProperty("D",  31, "Damping constant.  Usual range is 0 to 4. Default is 1.0.  Adjust to get damping");
		addProperty("UserModel", 32, "Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, " +
				"overriding the default model.  Set to \"none\" to negate previous setting.");
		addProperty("UserData", 33, "String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.");
		addProperty("ShaftModel",  34, "Name of user-written DLL containing a Shaft model, which models the prime mover and determines the power on the shaft for Dynamics studies. "+
				"Models additional mass elements other than the single-mass model in the DSS default model. Set to \"none\" to negate previous setting.");
		addProperty("ShaftData", 35,  "String (in quotes or parentheses) that gets passed to user-written shaft dynamic model for defining the data for that model.");
		addProperty("debugtrace", 21,  "{Yes | No }  Default is no.  Turn this on to capture the progress of the generator model " +
				"for each iteration.  Creates a separate file for each generator named \"GEN_name.CSV\"." );

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override default help string
		propertyHelp[Generator.NumPropsThisClass] = "Name of harmonic voltage or current spectrum for this generator. " +
				"Voltage behind Xd' for machine - default. Current injection for inverter. " +
				"Default value is \"default\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new GeneratorObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void setNcondsForConnection() {
		GeneratorObj elem = activeGeneratorObj;

		switch (elem.getConnection()) {
		case WYE:
			elem.setNumConds(elem.getNumPhases() + 1);
			break;
		case DELTA:
			switch (elem.getNumPhases()) {
			case 1:
				elem.setNumConds(elem.getNumPhases() + 1);  // L-L
				break;
			case 2:
				elem.setNumConds(elem.getNumPhases() + 1);  // open-delta
				break;
			default:
				elem.setNumConds(elem.getNumPhases());
				break;
			}
			break;
		}
	}

	/**
	 * Accepts (case insensitive):
	 * 		delta or LL
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		String ss;
		GeneratorObj elem = activeGeneratorObj;

		ss = s.toLowerCase();
		switch (ss.charAt(0)) {
		case 'y':
			elem.setConnection(Connection.WYE);
			break;
		case 'w':
			elem.setConnection(Connection.WYE);
			break;
		case 'd':
			elem.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (ss.charAt(1)) {
			case 'n':
				elem.setConnection(Connection.WYE);
				break;
			case 'l':
				elem.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		setNcondsForConnection();

		/* VBase is always L-N voltage unless 1-phase device or more than 3 phases */
		switch (elem.getNumPhases()) {
		case 2:
			elem.setVBase(elem.getGenVars().kVGeneratorBase * DSS.InvSQRT3x1000);  // L-N Volts
			break;
		case 3:
			elem.setVBase(elem.getGenVars().kVGeneratorBase * DSS.InvSQRT3x1000);  // L-N Volts
			break;
		default:
			elem.setVBase(elem.getGenVars().kVGeneratorBase * 1000.0);   // just use what is supplied
			break;
		}
		elem.setVBase95(elem.getVMinPU() * elem.getVBase());
		elem.setVBase105(elem.getVMaxPU() * elem.getVBase());

		elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
		elem.setYPrimInvalid(true);
	}

	private static DispatchMode interpretDispMode(String s) {
		switch (s.toLowerCase().charAt(0)) {
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
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeGeneratorObj = (GeneratorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeGeneratorObj);

		GeneratorObj elem = activeGeneratorObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties) {
				elem.setPropertyValue(getPropertyIdxMap(paramPointer), param);
			} else {
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for generator \"" +
						elem.getName() + "\"", 560);
			}

			if (paramPointer >= 0) {
				switch (getPropertyIdxMap(paramPointer)) {
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
							getClassName() + "." + elem.getName() + "\"", 561);
					break;
				case 0:
					elem.setNumPhases(parser.integerValue());  // num phases
					break;
				case 1:
					elem.setBus(0, param);
					break;
				case 2:
					elem.setPresentKV(parser.doubleValue());
					break;
				case 3:
					elem.setKWBase(parser.doubleValue());
					break;
				case 4:
					elem.setPowerFactor(parser.doubleValue());
					break;
				case 5:
					elem.setGenModel(GeneratorModel.values()[parser.integerValue() - 1]);
					break;
				case 6:
					elem.setYearlyShape(param);
					break;
				case 7:
					elem.setDailyDispShape(param);
					break;
				case 8:
					elem.setDutyShape(param);
					break;
				case 9:
					elem.setDispatchMode(interpretDispMode(param));
					break;
				case 10:
					elem.setDispatchValue(parser.doubleValue());
					break;
				case 11:
					interpretConnection(param);
					break;
				case 12:
					elem.setPresentKVAr(parser.doubleValue());
					break;
				case 13:
					DSS.doSimpleMsg("Rneut property has been deleted. Use external impedance.", 5611);
					break;
				case 14:
					DSS.doSimpleMsg("Xneut property has been deleted. Use external impedance.", 5612);
					break;
				case 15:
					if (param.toLowerCase().charAt(0) == 'f') {
						elem.setFixed(true);
					} else {
						elem.setFixed(false);
					}
					break;
				case 16:
					elem.setGenClass(parser.integerValue());
					break;
				case 17:
					elem.setVpu(parser.doubleValue());
					break;
				case 18:
					elem.setKVArMax(parser.doubleValue());
					break;
				case 19:
					elem.setKVArMin(parser.doubleValue());
					break;
				case 20:
					elem.setPVFactor(parser.doubleValue());  // declaration factor
					break;
				case 21:
					elem.setDebugTrace(Util.interpretYesNo(param));
					break;
				case 22:
					elem.setVMinPU(parser.doubleValue());
					break;
				case 23:
					elem.setVMaxPU(parser.doubleValue());
					break;
				case 24:
					elem.setForcedOn(Util.interpretYesNo(param));
					break;
				case 25:
					elem.getGenVars().kVARating = parser.doubleValue();
					break;
				case 26:
					elem.getGenVars().kVARating = parser.doubleValue() * 1000.0;  // "MVA";
					break;
				case 27:
					elem.getGenVars().puXd = parser.doubleValue();
					break;
				case 28:
					elem.getGenVars().puXdp = parser.doubleValue();
					break;
				case 29:
					elem.getGenVars().puXdpp = parser.doubleValue();
					break;
				case 30:
					elem.getGenVars().HMass = parser.doubleValue();
					break;
				case 31:
					elem.getGenVars().Dpu = parser.doubleValue();
					break;
				case 32:
					elem.getUserModel().setName(parser.stringValue());  // connect to user written models
					break;
				case 33:
					elem.getUserModel().edit(parser.stringValue());  // send edit string to user model
					break;
				case 34:
					elem.getShaftModel().setName(parser.stringValue());
					break;
				case 35:
					elem.getShaftModel().edit(parser.stringValue());
					break;
				default:
					// inherited parameters
					classEdit(activeGeneratorObj, paramPointer - Generator.NumPropsThisClass);
					break;
				}
			}

			if (paramPointer >= 0) {
				switch (getPropertyIdxMap(paramPointer)) {
				case 0:
					setNcondsForConnection();  // force reallocation of terminal info
					break;
				case 3:
					// keep kvar nominal up to date with kW and PF
					elem.syncUpPowerQuantities();
					break;
				case 4:
					// keep kvar nominal up to date with kW and PF
					elem.syncUpPowerQuantities();
					break;
				case 6:
					/* Set shape objects; returns nil if not valid */
					/* Sets the kW and kvar properties to match the peak kW demand from the LoadShape */
					elem.setYearlyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getYearlyShape()));
					if (elem.getYearlyShape() != null) {
						if (elem.getYearlyShapeObj().isUseActual())
							elem.setKwKVAr(elem.getYearlyShapeObj().getMaxP(), elem.getYearlyShapeObj().getMaxQ());
					}
					break;
				case 7:
					elem.setDailyDispShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDailyDispShape()));
					if (elem.getDailyDispShapeObj() != null) {
						if (elem.getDailyDispShapeObj().isUseActual())
							elem.setKwKVAr(elem.getDailyDispShapeObj().getMaxP(), elem.getDailyDispShapeObj().getMaxQ());
					}
					break;
				case 8:
					elem.setDutyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDutyShape()));
					if (elem.getDutyShapeObj() != null) {
						if (elem.getDutyShapeObj().isUseActual())
							elem.setKwKVAr(elem.getDutyShapeObj().getMaxP(), elem.getDutyShapeObj().getMaxQ());
					}
					break;
				case 21:
					if (elem.isDebugTrace()) {
						try {
							File f = new File(DSS.dataDirectory + "GEN_"+elem.getName()+".csv");
							FileWriter fw = new FileWriter(f, false);
							PrintWriter pw = new PrintWriter(fw);

							pw.write("t, Iteration, LoadMultiplier, Mode, LoadModel, GenModel, dQdV, Avg_Vpu, Vdiff, MQnominalperphase, MPnominalperphase, CurrentType");

							for (int i = 0; i < elem.getNumPhases(); i++)
								pw.print(", |Iinj" + String.valueOf(i) + "|");
							for (int i = 0; i < elem.getNumPhases(); i++)
								pw.print(", |Iterm" + String.valueOf(i) + "|");
							for (int i = 0; i < elem.getNumPhases(); i++)
								pw.print(", |Vterm" + String.valueOf(i) + "|");

							pw.println(", Vthev, Theta");

							pw.close();
							fw.close();
						} catch (Exception e) {
							DSS.doSimpleMsg("Error writing debug trace: " + e.getMessage(), -1);
						}
					}
					break;
				case 25:
					elem.setKVANotSet(false);
					break;
				case 26:
					elem.setKVANotSet(false);
					break;
				}
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		elem.recalcElementData();
		elem.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String otherGeneratorName) {
		int i, success = 0;

		/* See if we can find this line name in the present collection */
		GeneratorObj other = (GeneratorObj) find(otherGeneratorName);

		if (other != null) {
			GeneratorObj elem = activeGeneratorObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // forces reallocation of terminal stuff

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.getGenVars().kVGeneratorBase = other.getGenVars().kVGeneratorBase;
			elem.setVBase(other.getVBase());
			elem.setVMinPU(other.getVMinPU());
			elem.setVMaxPU(other.getVMaxPU());
			elem.setVBase95(other.getVBase95());
			elem.setVBase105(other.getVBase105());
			elem.setKWBase(other.getKWBase());
			elem.setKVArBase(other.getKVArBase());
			elem.getGenVars().PNominalPerPhase = other.getGenVars().PNominalPerPhase;
			elem.setPowerFactor(other.getPowerFactor());
			elem.getGenVars().QNominalPerPhase = other.getGenVars().QNominalPerPhase;
			elem.setVarMin(other.getVarMin());
			elem.setVarMax(other.getVarMax());
			elem.setConnection(other.getConnection());
			//elem.setRneut(other.getRneut());
			//elem.setXneut(other.getXneut());
			elem.setYearlyShape(other.getYearlyShape());
			elem.setYearlyShapeObj(other.getYearlyShapeObj());
			elem.setDailyDispShape(other.getDailyDispShape());
			elem.setDailyDispShapeObj(other.getDailyDispShapeObj());
			elem.setDutyShape(other.getDutyShape());
			elem.setDutyShapeObj(other.getDutyShapeObj());
			elem.setDispatchMode(other.getDispatchMode());
			elem.setDispatchValue(other.getDispatchValue());
			elem.setGenClass(other.getGenClass());
			elem.setGenModel(other.getGenModel());
			elem.setFixed(other.isFixed());
			elem.setVTarget(other.getVTarget());
			elem.setVpu(other.getVpu());
			elem.setKVArMax(other.getKVArMax());
			elem.setKVArMin(other.getKVArMin());
			elem.setForcedOn(other.isForcedOn());
			elem.setKVANotSet(other.isKVANotSet());

			elem.getGenVars().kVARating = other.getGenVars().kVARating;
			elem.getGenVars().puXd      = other.getGenVars().puXd;
			elem.getGenVars().puXdp     = other.getGenVars().puXdp;
			elem.getGenVars().puXdpp    = other.getGenVars().puXdpp;
			elem.getGenVars().HMass     = other.getGenVars().HMass;
			elem.getGenVars().theta     = other.getGenVars().theta;
			elem.getGenVars().speed     = other.getGenVars().speed;
			elem.getGenVars().w0        = other.getGenVars().w0;
			elem.getGenVars().dSpeed    = other.getGenVars().dSpeed;
			elem.getGenVars().D         = other.getGenVars().D;
			elem.getGenVars().Dpu       = other.getGenVars().Dpu;

			elem.getUserModel().setName(other.getUserModel().getName());  // connect to user written models
			elem.getShaftModel().setName(other.getShaftModel().getName());

			classMakeLike(other);

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Load makeLike: \"" + otherGeneratorName + "\" not found.", 562);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		GeneratorObj gen;

		if (handle == 0) {  // init all
			for (int i = 0; i < elementList.size(); i++) {
				gen = (GeneratorObj) elementList.get(i);
				gen.randomize(Randomization.NONE);
			}
		} else {
			setActiveElement(handle);
			gen = (GeneratorObj) getActiveObj();
			gen.randomize(Randomization.NONE);
		}

		DSS.doSimpleMsg("Need to implement Generator.init()", -1);
		return 0;
	}

	/**
	 * Force all EnergyMeters in the circuit to reset.
	 */
	public void resetRegistersAll() {
		for (GeneratorObj gen : DSS.activeCircuit.getGenerators())
			gen.resetRegisters();
	}

	/**
	 * Force all energy meters in the circuit to take a sample.
	 */
	public void sampleAll() {
		for (GeneratorObj pGen : DSS.activeCircuit.getGenerators())
			pGen.takeSample();
	}

	public String getRegisterName(int idx) {
		return registerNames[idx];
	}

	public String[] getRegisterNames() {
		return registerNames;
	}

	public void setRegisterNames(String[] names) {
		registerNames = names;
	}

}

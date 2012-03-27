/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.general.GrowthShapeObj;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Load extends PCClass {

	public static final int NumPropsThisClass = 33;

	public static LoadObj activeLoadObj;

	public Load() {
		super();
		className = "Load";
		classType = classType + DSSClassDefs.LOAD_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	/**
	 * Add properties of this class to propName.
	 */
	@Override
	protected void defineProperties() {
		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "phases";
		propertyName[1] = "bus1";
		propertyName[2] = "kV";
		propertyName[3] = "kW";
		propertyName[4] = "pf";
		propertyName[5] = "model";
		propertyName[6] = "yearly";
		propertyName[7] = "daily";
		propertyName[8] = "duty";
		propertyName[9] = "growth";
		propertyName[10] = "conn";
		propertyName[11] = "kvar";
		propertyName[12] = "Rneut";     // if entered -, assume open
		propertyName[13] = "Xneut";
		propertyName[14] = "status";    // fixed or variable
		propertyName[15] = "class";     // integer
		propertyName[16] = "Vminpu";    // min pu voltage for which model applies
		propertyName[17] = "Vmaxpu";    // max pu voltage for which model applies
		propertyName[18] = "Vminnorm";  // min pu voltage normal load
		propertyName[19] = "Vminemerg"; // min pu voltage emergency rating
		propertyName[20] = "xfkVA";     // service transformer rated kVA
		propertyName[21] = "allocationfactor";  // allocation factor for xfkVA
		propertyName[22] = "kVA";       // specify load in kVA and PF
		propertyName[23] = "%mean";     // per cent default mean
		propertyName[24] = "%stddev";   // per cent default standard deviation
		propertyName[25] = "CVRwatts";  // percent watts reduction per 1% reduction in voltage from nominal
		propertyName[26] = "CVRvars";   // percent vars reduction per 1% reduction in voltage from nominal
		propertyName[27] = "kwh";       // kwh billing
		propertyName[28] = "kwhdays";   // kwh billing period (24-hr days)
		propertyName[29] = "Cfactor";   // multiplier from kWh avg to peak kW
		propertyName[30] = "CVRcurve";  // name of curve to use for yearly CVR simulations
		propertyName[31] = "NumCust";   // number of customers, this load
		propertyName[32] = "ZIPV";      // array of 7 coefficients


		// define property help values
		propertyHelp[0] = "Number of Phases, this load.  Load is evenly divided among phases.";
		propertyHelp[1] = "Bus to which the load is connected.  May include specific node specification.";
		propertyHelp[2] = "Nominal rated (1.0 per unit) voltage, kV, for load. For 2- and 3-phase loads, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the load. "+
				"If wye (star), specify phase-neutral kV. "+
				"If delta or phase-phase connected, specify phase-phase kV.";  // line-neutral voltage
		propertyHelp[3] = "Total base kW for the load.  Normally, you would enter the maximum kW for the load for the first year "+
				"and allow it to be adjusted by the load shapes, growth shapes, and global load multiplier."+DSS.CRLF+DSS.CRLF+
				"Legal ways to define base load:"+DSS.CRLF+
				"kW, PF"+DSS.CRLF+
				"kW, kvar"+DSS.CRLF+
				"kVA, PF" +DSS.CRLF+
				"XFKVA * Allocationfactor, PF" +DSS.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		propertyHelp[4] = "Load power factor.  Enter negative for leading powerfactor (when kW and kvar have opposite signs.)";
		propertyHelp[5] = "Integer code for the model to use for load variation with voltage. "+
				"Valid values are:" +DSS.CRLF+DSS.CRLF+
				"1:Standard constant P+jQ load. (Default)"+DSS.CRLF+
				"2:Constant impedance load. "+DSS.CRLF+
				"3:Const P, Quadratic Q (like a motor)."+DSS.CRLF+
				"4:Nominal Linear P, Quadratic Q (feeder mix). Use this with CVRfactor."+DSS.CRLF+
				"5:Constant Current Magnitude"+DSS.CRLF+
				"6:Const P, Fixed Q"+DSS.CRLF+
				"7:Const P, Fixed Impedance Q"+DSS.CRLF+
				"8:ZIPV (7 values)"+DSS.CRLF+DSS.CRLF+
				"For Types 6 and 7, only the P is modified by load multipliers.";
		propertyHelp[6] = "Load shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. Defaults to Daily load shape " +
				" when Daily is defined.  The daily load shape is repeated in this case. "+
				"Set Status=Fixed to ignore Loadshape designation. " +
				"Set to NONE to reset to no loadahape. " +
				"The default is no variation.";
		propertyHelp[7] = "Load shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically. " +
				"Set Status=Fixed to ignore Loadshape designation. " +
				"Set to NONE to reset to no loadahape. " +
				"Default is no variation (constant) if not defined. " +
				"Side effect: Sets Yearly load shape if not already defined.";
		propertyHelp[8] = "Load shape to use for duty cycle simulations.  Must be previously defined "+
				"as a Loadshape object.  Typically would have time intervals less than 1 hr. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat."+
				"Set to NONE to reset to no loadahape. " +
				"Set Status=Fixed to ignore Loadshape designation. " +
				" Defaults to Daily curve If not specified.";
		propertyHelp[9] = "Characteristic  to use for growth factors by years.  Must be previously defined "+
				"as a Growthshape object. Defaults to circuit default growth factor (see Set Growth command).";
		propertyHelp[10] = "={wye or LN | delta or LL}.  Default is wye.";
		propertyHelp[11] = "Specify the base kvar for specifying load as kW & kvar.  Assumes kW has been already defined.  Alternative to specifying the power factor.  Side effect: "+
				" the power factor and kVA is altered to agree.";
		propertyHelp[12] = "Default is -1. Neutral resistance of wye (star)-connected load in actual ohms. " +
				"If entered as a negative value, the neutral can be open, or floating, or it can be connected to "+
				"node 0 (ground), which is the usual default. " +
				"If >=0 be sure to explicitly specify the node connection for the neutral, or last, conductor. " +
				"Otherwise, the neutral impedance will be shorted to ground.";
		propertyHelp[13] = "Neutral reactance of wye(star)-connected load in actual ohms.  May be + or -.";
		propertyHelp[14] = "={Variable | Fixed | Exempt}.  Default is variable. If Fixed, no load multipliers apply;  however, growth "+
				"multipliers do apply.  All multipliers apply to Variable loads.  Exempt loads are not "+
				"modified by the global load multiplier, such as in load duration curves, etc.  Daily multipliers "+
				"do apply, so setting this property to Exempt is a good way to represent industrial load that stays the same" +
				" day-after-day for the period study.";  // fixed or variable
		propertyHelp[15] = "An arbitrary integer number representing the class of load so that load values may "+
				"be segregated by load value. Default is 1; not used internally.";
		propertyHelp[16] = "Default = 0.95.  Minimum per unit voltage for which the MODEL is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.";
		propertyHelp[17] = "Default = 1.05.  Maximum per unit voltage for which the MODEL is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.";
		propertyHelp[18] = "Minimum per unit voltage for load EEN evaluations, Normal limit.  Default = 0, which defaults to system \"vminnorm\" " +
				"property (see Set Command under Executive).  If this property is specified, it ALWAYS " +
				"overrides the system specification. This allows you to have different criteria for different loads. "+
				"Set to zero to revert to the default system value.";
		propertyHelp[19] = "Minimum per unit voltage for load UE evaluations, Emergency limit.  Default = 0, which defaults to system \"vminemerg\" " +
				"property (see Set Command under Executive).  If this property is specified, it ALWAYS " +
				"overrides the system specification. This allows you to have different criteria for different loads. "+
				"Set to zero to revert to the default system value.";
		propertyHelp[20] = "Default = 0.0.  Rated kVA of service transformer for allocating loads based on connected kVA " +
				"at a bus. Side effect:  kW, PF, and kvar are modified. See help on kVA.";
		propertyHelp[21] = "Default = 0.5.  Allocation factor for allocating loads based on connected kVA " +
				"at a bus. Side effect:  kW, PF, and kvar are modified by multiplying this factor times the XFKVA (if > 0).";
		propertyHelp[22] = "Specify base Load in kVA (and power factor)"+DSS.CRLF+DSS.CRLF+
				"Legal ways to define base load:"+DSS.CRLF+
				"kW, PF"+DSS.CRLF+
				"kW, kvar"+DSS.CRLF+
				"kVA, PF" +DSS.CRLF+
				"XFKVA * Allocationfactor, PF" +DSS.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		propertyHelp[23] = "Percent mean value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 50.";
		propertyHelp[24] = "Percent Std deviation value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 10.";
		propertyHelp[25] = "Percent reduction in active power (watts) per 1% reduction in voltage from 100% rated. Default=1. " +DSS.CRLF +
				" Typical values range from 0.4 to 0.8. Applies to Model=4 only." + DSS.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		propertyHelp[26] = "Percent reduction in reactive power (vars) per 1% reduction in voltage from 100% rated. Default=2. " +DSS.CRLF +
				" Typical values range from 2 to 3. Applies to Model=4 only." + DSS.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		propertyHelp[27] = "kWh billed for this period. Default is 0. See help on kVA and Cfactor and kWhDays.";
		propertyHelp[28] = "Length of kWh billing period in days (24 hr days). Default is 30. Average demand is computed using this value.";   // kwh billing period (24-hr days)
		propertyHelp[29] = "Factor relating average kW to peak kW. Default is 4.0. See kWh and kWhdays. See kVA.";   // multiplier from kWh avg to peak kW
		propertyHelp[30] = "Default is NONE. Curve describing both watt and var factors as a function of time. " +
				"Refers to a LoadShape object with both Mult and Qmult defined. " +
				"Define a Loadshape to agree with yearly or daily curve according to the type of analysis being done. " +
				"If NONE, the CVRwatts and CVRvars factors are used and assumed constant.";
		propertyHelp[31] = "Number of customers, this load. Default is 1.";
		propertyHelp[32] = "Array of 7 coefficients:" + DSS.CRLF +
				" First 3 are ZIP weighting factors for real power (should sum to 1)" + DSS.CRLF +
				" Next 3 are ZIP weighting factors for reactive power (should sum to 1)" + DSS.CRLF +
				" Last 1 is cut-off voltage in p.u. of base kV; load is 0 below this cut-off" + DSS.CRLF +
				" No defaults; all coefficients must be specified if using model=8.";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		propertyHelp[NumPropsThisClass] = "Name of harmonic current spectrum for this load.  Default is \"defaultload\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new LoadObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void setNcondsForConnection() {
		LoadObj elem = activeLoadObj;

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
	 * Accepts (checks only min number of chars required):
	 * 		delta or LL           (Case insensitive)
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		LoadObj al = activeLoadObj;

		String testS = s.toLowerCase();
		switch (testS.charAt(0)) {
		case 'y':
			al.setConnection(Connection.WYE);
			break;
		case 'w':
			al.setConnection(Connection.WYE);
			break;
		case 'd':
			al.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (testS.charAt(1)) {
			case 'n':
				al.setConnection(Connection.WYE);
				break;
			case 'l':
				al.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		setNcondsForConnection();

		switch (al.getConnection()) {
		case DELTA:
			al.setVBase(al.getKVLoadBase() * 1000.0);
		default:
			switch (al.getNumPhases()) {
			case 2:
				al.setVBase(al.getKVLoadBase() * DSS.InvSQRT3x1000);
				break;
			case 3:
				al.setVBase(al.getKVLoadBase() * DSS.InvSQRT3x1000);
				break;
			default:
				al.setVBase(al.getKVLoadBase() * 1000.0);
				break;
			}
			break;
		}
		al.setVBase95(al.getVMinPU() * al.getVBase());
		al.setVBase105(al.getVMaxPU() * al.getVBase());

		al.setYOrder(al.getNumConds() * al.getNumTerms());
		al.setYPrimInvalid(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLoadObj = (LoadObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeLoadObj);

		LoadObj elem = activeLoadObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 580);
				break;
			case 0:
				elem.setNumPhases(parser.makeInteger()); // num phases
				break;
			case 1:
				elem.setBus(0, param);
				break;
			case 2:
				elem.setKVLoadBase(parser.makeDouble());
				break;
			case 3:
				elem.setKWBase(parser.makeDouble());
				break;
			case 4:
				elem.setPFNominal(parser.makeDouble());
				break;
			case 5:
				elem.setLoadModel(LoadModel.values()[parser.makeInteger() - 1]);
				break;
			case 6:
				elem.setYearlyShape(param);
				break;
			case 7:
				elem.setDailyShape(param);
				break;
			case 8:
				elem.setDutyShape(param);
				break;
			case 9:
				elem.setGrowthShape(param);
				break;
			case 10:
				interpretConnection(param);
				break;
			case 11:
				elem.setKVArBase(parser.makeDouble());
				break;
			case 12:
				elem.setRNeut(parser.makeDouble());
				break;
			case 13:
				elem.setXNeut(parser.makeDouble());
				break;
			case 14:
				switch (param.toLowerCase().charAt(0)) {
				case 'f':
					elem.setFixed(true);
					elem.setExemptFromLDCurve(false);
					break;
				case 'e':
					elem.setFixed(false);
					elem.setExemptFromLDCurve(true);
					break;
				default:
					elem.setFixed(false);
					elem.setExemptFromLDCurve(false);
					break;
				}
			case 15:
				elem.setLoadClass(parser.makeInteger());
				break;
			case 16:
				elem.setVMinPU(parser.makeDouble());
				break;
			case 17:
				elem.setVMaxPU(parser.makeDouble());
				break;
			case 18:
				elem.setVMinNormal(parser.makeDouble());
				break;
			case 19:
				elem.setVMinEmerg(parser.makeDouble());
				break;
			case 20:
				elem.setConnectedKVA(parser.makeDouble());
				break;
			case 21:
				elem.setKVAAllocationFactor(parser.makeDouble());
				break;
			case 22:
				elem.setKVABase(parser.makeDouble());
				break;
			case 23:
				elem.setPuMean(parser.makeDouble() / 100.0);
				break;
			case 24:
				elem.setPuStdDev(parser.makeDouble() / 100.0);
				break;
			case 25:
				elem.setCVRwattFactor(parser.makeDouble());
				break;
			case 26:
				elem.setCVRvarFactor(parser.makeDouble());
				break;
			case 27:
				elem.setKWh(parser.makeDouble());
				break;
			case 28:
				elem.setKWhDays(parser.makeDouble());
				break;
			case 29:
				elem.setCFactor(parser.makeDouble());
				break;
			case 30:
				elem.setCVRShape(param);
				break;
			case 31:
				elem.setNumCustomers(parser.makeInteger());
				break;
			case 32:
				elem.setZIPVSize(7);
				parser.parseAsVector(7, elem.getZIPV());
				break;
			default:
				// Inherited edits
				classEdit(activeLoadObj, paramPointer - Load.NumPropsThisClass);
				break;
			}

			// side effects
			// keep kvar nominal up to date with kW and PF
			switch (paramPointer) {
			case 0:
				setNcondsForConnection();  // force reallocation of terminal info
				elem.updateVoltageBases();
				break;
			case 2:
				elem.updateVoltageBases();
				break;
			case 3:
				elem.setLoadSpecType(LoadSpecType.KW_PF);
				break;
			case 4:
				elem.setPFChanged(true);
				break;
			case 6:
				/* Set shape objects; returns nil if not valid */
				/* Sets the kW and kvar properties to match the peak kW demand from the LoadShape */
				elem.setYearlyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getYearlyShape()));
				if (elem.getYearlyShapeObj() != null)
					if (elem.getYearlyShapeObj().isUseActual())
						elem.setKW_KVAr(elem.getYearlyShapeObj().getMaxP(), elem.getYearlyShapeObj().getMaxQ());
				break;
			case 7:
				elem.setDailyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDailyShape()));
				if (elem.getDailyShapeObj() != null)
					if (elem.getDailyShapeObj().isUseActual())
						elem.setKW_KVAr(elem.getDailyShapeObj().getMaxP(), elem.getDailyShapeObj().getMaxQ());
				/* If yearly load shape is not yet defined, make it the same as daily */
				if (elem.getYearlyShapeObj() == null)
					elem.setYearlyShapeObj(elem.getDailyShapeObj());
				break;
			case 8:
				elem.setDutyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDutyShape()));
				if (elem.getDutyShapeObj() != null)
					if (elem.getDutyShapeObj().isUseActual())
						elem.setKW_KVAr(elem.getDutyShapeObj().getMaxP(), elem.getDutyShapeObj().getMaxQ());
				break;
			case 9:
				elem.setGrowthShapeObj((GrowthShapeObj) DSS.growthShapeClass.find(elem.getGrowthShape()));
				break;
			case 11:
				elem.setLoadSpecType(LoadSpecType.KW_KVAR);
				break;
			/*** see set_xfkva, etc           21, 22: LoadSpectype = 3;  // XFKVA*AllocationFactor, PF  */
			case 22:
				elem.setLoadSpecType(LoadSpecType.KVA_PF);
				break;
			/*** see set_kwh, etc           28..30: LoadSpecType = 4;  // kWh, days, cfactor, PF */
			case 30:
				elem.setCVRShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getCVRShape()));
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();
		elem.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String otherLoadName) {
		int success = 0;

		/* See if we can find this line name in the present collection */
		LoadObj other = (LoadObj) find(otherLoadName);

		if (other != null) {
			LoadObj elem = activeLoadObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // forces reallocation of terminal stuff
				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.setKVLoadBase(other.getKVLoadBase());
			elem.setVBase(other.getVBase());
			elem.setVMinPU(other.getVMinPU());
			elem.setVMaxPU(other.getVMaxPU());
			elem.setVBase95(other.getVBase95());
			elem.setVBase105(other.getVBase105());
			elem.setKWBase(other.getKWBase());
			elem.setKVABase(other.getKVABase());
			elem.setKVArBase(other.getKVArBase());
			elem.setLoadSpecType(other.getLoadSpecType());
			elem.setWNominal(other.getWNominal());
			elem.setPFNominal(other.getPFNominal());
			elem.setVarNominal(other.getVarNominal());
			elem.setConnection(other.getConnection());
			elem.setRNeut(other.getRNeut());
			elem.setXNeut(other.getXNeut());
			elem.setYearlyShape(other.getYearlyShape());
			elem.setYearlyShapeObj(other.getYearlyShapeObj());
			elem.setCVRShape(other.getCVRShape());
			elem.setCVRShapeObj(other.getCVRShapeObj());
			elem.setDailyShape(other.getDailyShape());
			elem.setDailyShapeObj(other.getDailyShapeObj());
			elem.setDutyShape(other.getDutyShape());
			elem.setDutyShapeObj(other.getDutyShapeObj());
			elem.setGrowthShape(other.getGrowthShape());
			elem.setGrowthShapeObj(other.getGrowthShapeObj());
			//elem.setSpectrum(other.getSpectrum();  in base class now
			//elem.setSpectrumObj(other.getSpectrumObj());
			elem.setLoadClass(other.getLoadClass());
			elem.setNumCustomers(other.getNumCustomers());
			elem.loadModel = other.loadModel;
			elem.setFixed(other.isFixed());
			elem.setExemptFromLDCurve(other.isExemptFromLDCurve());
			elem.kVAAllocationFactor = other.kVAAllocationFactor;
			elem.connectedkVA = other.connectedkVA;
			elem.CVRwattFactor = other.CVRwattFactor;
			elem.CVRvarFactor = other.CVRvarFactor;
			elem.setShapeIsActual(other.shapeIsActual());

			elem.setZIPVSize(other.getNZIPV());
			for (int i = 0; i < elem.getNZIPV(); i++)
				elem.getZIPV()[i] = other.getZIPV()[i];

			classMakeLike(other);  // take care of inherited class properties

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Load makeLike: \"" + otherLoadName + "\" not found.", 581);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		LoadObj load;
		if (handle == 0) {
			for (int i = 0; i < elementList.size(); i++) {
				load = (LoadObj) elementList.get(i);
				load.randomize(Randomization.NONE);
			}
		} else {
			setActiveElement(handle);
			load = (LoadObj) getActiveObj();
			load.randomize(Randomization.NONE);
		}

		DSS.doSimpleMsg("Need to finish implementation Load.init", -1);
		return 0;
	}

}

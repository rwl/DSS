package com.ncond.dss.conversion.impl;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.conversion.Load;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.general.GrowthShapeObj;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CommandListImpl;

public class LoadImpl extends PCClassImpl implements Load {

	public static LoadObj activeLoadObj;

	public LoadImpl() {
		super();
		className = "Load";
		classType = classType + DSSClassDefs.LOAD_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	/**
	 * Add properties of this class to propName.
	 */
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
				"and allow it to be adjusted by the load shapes, growth shapes, and global load multiplier."+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Legal ways to define base load:"+DSSGlobals.CRLF+
				"kW, PF"+DSSGlobals.CRLF+
				"kW, kvar"+DSSGlobals.CRLF+
				"kVA, PF" +DSSGlobals.CRLF+
				"XFKVA * Allocationfactor, PF" +DSSGlobals.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		propertyHelp[4] = "Load power factor.  Enter negative for leading powerfactor (when kW and kvar have opposite signs.)";
		propertyHelp[5] = "Integer code for the model to use for load variation with voltage. "+
				"Valid values are:" +DSSGlobals.CRLF+DSSGlobals.CRLF+
				"1:Standard constant P+jQ load. (Default)"+DSSGlobals.CRLF+
				"2:Constant impedance load. "+DSSGlobals.CRLF+
				"3:Const P, Quadratic Q (like a motor)."+DSSGlobals.CRLF+
				"4:Nominal Linear P, Quadratic Q (feeder mix). Use this with CVRfactor."+DSSGlobals.CRLF+
				"5:Constant Current Magnitude"+DSSGlobals.CRLF+
				"6:Const P, Fixed Q"+DSSGlobals.CRLF+
				"7:Const P, Fixed Impedance Q"+DSSGlobals.CRLF+
				"8:ZIPV (7 values)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
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
		propertyHelp[22] = "Specify base Load in kVA (and power factor)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Legal ways to define base load:"+DSSGlobals.CRLF+
				"kW, PF"+DSSGlobals.CRLF+
				"kW, kvar"+DSSGlobals.CRLF+
				"kVA, PF" +DSSGlobals.CRLF+
				"XFKVA * Allocationfactor, PF" +DSSGlobals.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		propertyHelp[23] = "Percent mean value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 50.";
		propertyHelp[24] = "Percent Std deviation value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 10.";
		propertyHelp[25] = "Percent reduction in active power (watts) per 1% reduction in voltage from 100% rated. Default=1. " +DSSGlobals.CRLF +
				" Typical values range from 0.4 to 0.8. Applies to Model=4 only." + DSSGlobals.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		propertyHelp[26] = "Percent reduction in reactive power (vars) per 1% reduction in voltage from 100% rated. Default=2. " +DSSGlobals.CRLF +
				" Typical values range from 2 to 3. Applies to Model=4 only." + DSSGlobals.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		propertyHelp[27] = "kWh billed for this period. Default is 0. See help on kVA and Cfactor and kWhDays.";
		propertyHelp[28] = "Length of kWh billing period in days (24 hr days). Default is 30. Average demand is computed using this value.";   // kwh billing period (24-hr days)
		propertyHelp[29] = "Factor relating average kW to peak kW. Default is 4.0. See kWh and kWhdays. See kVA.";   // multiplier from kWh avg to peak kW
		propertyHelp[30] = "Default is NONE. Curve describing both watt and var factors as a function of time. " +
				"Refers to a LoadShape object with both Mult and Qmult defined. " +
				"Define a Loadshape to agree with yearly or daily curve according to the type of analysis being done. " +
				"If NONE, the CVRwatts and CVRvars factors are used and assumed constant.";
		propertyHelp[31] = "Number of customers, this load. Default is 1.";
		propertyHelp[32] = "Array of 7 coefficients:" + DSSGlobals.CRLF +
				" First 3 are ZIP weighting factors for real power (should sum to 1)" + DSSGlobals.CRLF +
				" Next 3 are ZIP weighting factors for reactive power (should sum to 1)" + DSSGlobals.CRLF +
				" Last 1 is cut-off voltage in p.u. of base kV; load is 0 below this cut-off" + DSSGlobals.CRLF +
				" No defaults; all coefficients must be specified if using model=8.";


		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		propertyHelp[NumPropsThisClass] = "Name of harmonic current spectrum for this load.  Default is \"defaultload\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {

		DSSGlobals.activeCircuit.setActiveCktElement(new LoadObjImpl(this, objName));
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	private void setNcondsForConnection() {
		LoadObj al = activeLoadObj;

		switch (al.getConnection()) {
		case 0:
			al.setNConds(al.getNPhases() + 1);
			break;
		case 1:
			switch (al.getNPhases()) {
			case 1:
				al.setNConds(al.getNPhases() + 1);  // L-L
				break;
			case 2:
				al.setNConds(al.getNPhases() + 1);  // open-delta
				break;
			default:
				al.setNConds(al.getNPhases());
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
			al.setConnection(0);  /* Wye */
			break;
		case 'w':
			al.setConnection(0);  /* Wye */
			break;
		case 'd':
			al.setConnection(1);  /* Delta or Line-Line */
			break;
		case 'l':
			switch (testS.charAt(1)) {
			case 'n':
				al.setConnection(0);
				break;
			case 'l':
				al.setConnection(1);
				break;
			}
			break;
		}

		setNcondsForConnection();

		switch (al.getConnection()) {
		case 1:
			al.setVBase(al.getKVLoadBase() * 1000.0);
		default:
			switch (al.getNPhases()) {
			case 2:
				al.setVBase(al.getKVLoadBase() * DSSGlobals.InvSQRT3x1000);
				break;
			case 3:
				al.setVBase(al.getKVLoadBase() * DSSGlobals.InvSQRT3x1000);
				break;
			default:
				al.setVBase(al.getKVLoadBase() * 1000.0);
				break;
			}
			break;
		}
		al.setVBase95(al.getVMinPU() * al.getVBase());
		al.setVBase105(al.getVMaxPU() * al.getVBase());

		al.setYOrder(al.getNConds() * al.getNTerms());
		al.setYPrimInvalid(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLoadObj = (LoadObj) elementList.getActive();
		DSSGlobals.activeCircuit.setActiveCktElement(activeLoadObj);

		int result = 0;

		LoadObj al = activeLoadObj;

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
				al.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ al.getName() + "\"", 580);
				break;
			case 0:
				al.setNPhases(parser.makeInteger()); // num phases
				break;
			case 1:
				al.setBus(1, param);  // TODO: Check zero based indexing
				break;
			case 2:
				al.setKVLoadBase(parser.makeDouble());
				break;
			case 3:
				al.setKWBase(parser.makeDouble());
				break;
			case 4:
				al.setPFNominal(parser.makeDouble());
				break;
			case 5:
				al.setLoadModel(parser.makeInteger());
				break;
			case 6:
				al.setYearlyShape(param);
				break;
			case 7:
				al.setDailyShape(param);
				break;
			case 8:
				al.setDutyShape(param);
				break;
			case 9:
				al.setGrowthShape(param);
				break;
			case 10:
				interpretConnection(param);
				break;
			case 11:
				al.setKVArBase(parser.makeDouble());
				break;
			case 12:
				al.setRNeut(parser.makeDouble());
				break;
			case 13:
				al.setXNeut(parser.makeDouble());
				break;
			case 14:
				switch (param.toLowerCase().charAt(0)) {
				case 'f':
					al.setFixed(true);
					al.setExemptFromLDCurve(false);
					break;
				case 'e':
					al.setFixed(false);
					al.setExemptFromLDCurve(true);
					break;
				default:
					al.setFixed(false);
					al.setExemptFromLDCurve(false);
					break;
				}
			case 15:
				al.setLoadClass(parser.makeInteger());
				break;
			case 16:
				al.setVMinPU(parser.makeDouble());
				break;
			case 17:
				al.setVMaxPU(parser.makeDouble());
				break;
			case 18:
				al.setVMinNormal(parser.makeDouble());
				break;
			case 19:
				al.setVMinEmerg(parser.makeDouble());
				break;
			case 20:
				al.setConnectedKVA(parser.makeDouble());
				break;
			case 21:
				al.setKVAAllocationFactor(parser.makeDouble());
				break;
			case 22:
				al.setKVABase(parser.makeDouble());
				break;
			case 23:
				al.setPUMean(parser.makeDouble() / 100.0);
				break;
			case 24:
				al.setPUStdDev(parser.makeDouble() / 100.0);
				break;
			case 25:
				al.setCVRWattFactor(parser.makeDouble());
				break;
			case 26:
				al.setCVRVArFactor(parser.makeDouble());
				break;
			case 27:
				al.setKWh(parser.makeDouble());
				break;
			case 28:
				al.setKWhDays(parser.makeDouble());
				break;
			case 29:
				al.setCFactor(parser.makeDouble());
				break;
			case 30:
				al.setCVRShape(param);
				break;
			case 31:
				al.setNumCustomers(parser.makeInteger());
				break;
			case 32:
				al.setZIPVSize(7);
				parser.parseAsVector(7, al.getZIPV());
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
				al.updateVoltageBases();
				break;
			case 2:
				al.updateVoltageBases();
				break;
			case 3:
				al.setLoadSpecType(0);
				break;
			case 4:
				al.setPFChanged(true);
				break;
			case 6:
				/* Set shape objects; returns nil if not valid */
				/* Sets the kW and kvar properties to match the peak kW demand from the LoadShape */
				al.setYearlyShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find(al.getYearlyShape()));
				if (al.getYearlyShapeObj() != null)
					if (al.getYearlyShapeObj().isUseActual())
						al.setKW_KVAr(al.getYearlyShapeObj().getMaxP(), al.getYearlyShapeObj().getMaxQ());
				break;
			case 7:
				al.setDailyShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find(al.getDailyShape()));
				if (al.getDailyShapeObj() != null)
					if (al.getDailyShapeObj().isUseActual())
						al.setKW_KVAr(al.getDailyShapeObj().getMaxP(), al.getDailyShapeObj().getMaxQ());
				/* If yearly load shape is not yet defined, make it the same as daily */
				if (al.getYearlyShapeObj() == null)
					al.setYearlyShapeObj(al.getDailyShapeObj());
				break;
			case 8:
				al.setDutyShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find(al.getDutyShape()));
				if (al.getDutyShapeObj() != null)
					if (al.getDutyShapeObj().isUseActual())
						al.setKW_KVAr(al.getDutyShapeObj().getMaxP(), al.getDutyShapeObj().getMaxQ());
				break;
			case 9:
				al.setGrowthShapeObj((GrowthShapeObj) DSSGlobals.growthShapeClass.find(al.getGrowthShape()));
				break;
			case 11:
				al.setLoadSpecType(1);  // kW, kvar
				break;
			/*** see set_xfkva, etc           21, 22: LoadSpectype = 3;  // XFKVA*AllocationFactor, PF  */
			case 22:
				al.setLoadSpecType(2);  // kVA, PF
				break;
			/*** see set_kwh, etc           28..30: LoadSpecType = 4;  // kWh, days, cfactor, PF */
			case 30:
				al.setCVRShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find(al.getCVRShape()));
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		al.recalcElementData();
		al.setYPrimInvalid(true);

		return result;
	}

	@Override
	protected int makeLike(String otherLoadName) {
		int result = 0;

		/* See if we can find this line name in the present collection */
		LoadObj otherLoad = (LoadObj) find(otherLoadName);
		if (otherLoad != null) {
			LoadObj al = activeLoadObj;

			if (al.getNPhases() != otherLoad.getNPhases()) {
				al.setNPhases(otherLoad.getNPhases());
				al.setNConds(al.getNPhases());  // forces reallocation of terminal stuff
				al.setYOrder(al.getNConds() * al.getNTerms());
				al.setYPrimInvalid(true);
			}

			al.setKVLoadBase(otherLoad.getKVLoadBase());
			al.setVBase(otherLoad.getVBase());
			al.setVMinPU(otherLoad.getVMinPU());
			al.setVMaxPU(otherLoad.getVMaxPU());
			al.setVBase95(otherLoad.getVBase95());
			al.setVBase105(otherLoad.getVBase105());
			al.setKWBase(otherLoad.getKWBase());
			al.setKVABase(otherLoad.getKVABase());
			al.setKVArBase(otherLoad.getKVArBase());
			al.setLoadSpecType(otherLoad.getLoadSpecType());
			al.setWNominal(otherLoad.getWNominal());
			al.setPFNominal(otherLoad.getPFNominal());
			al.setVArNominal(otherLoad.getVArNominal());
			al.setConnection(otherLoad.getConnection());
			al.setRNeut(otherLoad.getRNeut());
			al.setXNeut(otherLoad.getXNeut());
			al.setYearlyShape(otherLoad.getYearlyShape());
			al.setYearlyShapeObj(otherLoad.getYearlyShapeObj());
			al.setCVRShape(otherLoad.getCVRShape());
			al.setCVRShapeObj(otherLoad.getCVRShapeObj());
			al.setDailyShape(otherLoad.getDailyShape());
			al.setDailyShapeObj(otherLoad.getDailyShapeObj());
			al.setDutyShape(otherLoad.getDutyShape());
			al.setDutyShapeObj(otherLoad.getDutyShapeObj());
			al.setGrowthShape(otherLoad.getGrowthShape());
			al.setGrowthShapeObj(otherLoad.getGrowthShapeObj());
			//al.setSpectrum(OtherLoad.getSpectrum();  in base class now
			//al.setSpectrumObj(OtherLoad.getSpectrumObj());
			al.setLoadClass(otherLoad.getLoadClass());
			al.setNumCustomers(otherLoad.getNumCustomers());
			al.setLoadModel(otherLoad.getLoadModel());
			al.setFixed(otherLoad.isFixed());
			al.setExemptFromLDCurve(otherLoad.isExemptFromLDCurve());
			al.setKVAAllocationFactor(otherLoad.getKVAAllocationFactor());
			al.setConnectedKVA(otherLoad.getConnectedKVA());
			al.setCVRWattFactor(otherLoad.getCVRWattFactor());
			al.setCVRVArFactor(otherLoad.getCVRVArFactor());
			al.setShapeIsActual(otherLoad.shapeIsActual());

			al.setZIPVSize(otherLoad.getNZIPV());
			for (int i = 0; i < al.getNZIPV(); i++)
				al.getZIPV()[i] = otherLoad.getZIPV()[i];

			classMakeLike(otherLoad);  // take care of inherited class properties


			for (int i = 0; i < al.getParentClass().getNumProperties(); i++)
				al.setPropertyValue(i, otherLoad.getPropertyValue(i));

			result = 1;
		} else {
			DSSGlobals.doSimpleMsg("Error in Load makeLike: \"" + otherLoadName + "\" not found.", 581);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		LoadObj pLoad;
		if (handle == 0) {
			for (int i = 0; i < elementList.size(); i++) {
				pLoad = (LoadObj) elementList.get(i);
				pLoad.randomize(0);
			}
		} else {
			setActiveElement(handle);
			pLoad = (LoadObj) getActiveObj();
			pLoad.randomize(0);
		}

		DSSGlobals.doSimpleMsg("Need to finish implementation Load.init", -1);
		return 0;
	}

}

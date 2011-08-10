package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.Load;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class LoadImpl extends PCClassImpl implements Load {

	private static LoadObj ActiveLoadObj;

	public LoadImpl() {
		super();
		this.Class_Name = "Load";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.LOAD_ELEMENT;

		setActiveElement(-1);

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	/**
	 * Add properties of this class to propName.
	 */
	protected void defineProperties() {

		NumProperties = NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		// Define property names
		PropertyName[0] = "phases";
		PropertyName[1] = "bus1";
		PropertyName[2] = "kV";  //
		PropertyName[3] = "kW";
		PropertyName[4] = "pf";
		PropertyName[5] = "model";
		PropertyName[6] = "yearly";
		PropertyName[7] = "daily";
		PropertyName[8] = "duty";
		PropertyName[9] = "growth";
		PropertyName[10] = "conn";
		PropertyName[11] = "kvar";
		PropertyName[12] = "Rneut";  // if entered -, assume open
		PropertyName[13] = "Xneut";
		PropertyName[14] = "status";    // fixed or variable
		PropertyName[15] = "class";     // integer
		PropertyName[16] = "Vminpu";    // Min pu voltage FOR which model applies
		PropertyName[17] = "Vmaxpu";    // Max pu voltage FOR which model applies
		PropertyName[18] = "Vminnorm";  // Min pu voltage normal load
		PropertyName[19] = "Vminemerg"; // Min pu voltage emergency rating
		PropertyName[20] = "xfkVA";     // Service transformer rated kVA
		PropertyName[21] = "allocationfactor";  // allocation factor  for xfkVA
		PropertyName[22] = "kVA";       // specify load in kVA and PF
		PropertyName[23] = "%mean";     // per cent default mean
		PropertyName[24] = "%stddev";   // per cent default standard deviation
		PropertyName[25] = "CVRwatts";  // Percent watts reduction per 1% reduction in voltage from nominal
		PropertyName[26] = "CVRvars";   // Percent vars reduction per 1% reduction in voltage from nominal
		PropertyName[27] = "kwh";       // kwh billing
		PropertyName[28] = "kwhdays";   // kwh billing period (24-hr days)
		PropertyName[29] = "Cfactor";   // multiplier from kWh avg to peak kW
		PropertyName[30] = "CVRcurve";  // name of curve to use for yearly CVR simulations
		PropertyName[31] = "NumCust";   // Number of customers, this load
		PropertyName[32] = "ZIPV";      // array of 7 coefficients


		// Define property help values
		PropertyHelp[0] = "Number of Phases, this load.  Load is evenly divided among phases.";
		PropertyHelp[1] = "Bus to which the load is connected.  May include specific node specification.";
		PropertyHelp[2] = "Nominal rated (1.0 per unit) voltage, kV, for load. For 2- and 3-phase loads, specify phase-phase kV. "+
				"Otherwise, specify actual kV across each branch of the load. "+
				"If wye (star), specify phase-neutral kV. "+
				"If delta or phase-phase connected, specify phase-phase kV.";  // line-neutral voltage
		PropertyHelp[3] = "Total base kW for the load.  Normally, you would enter the maximum kW for the load for the first year "+
				"and allow it to be adjusted by the load shapes, growth shapes, and global load multiplier."+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Legal ways to define base load:"+DSSGlobals.CRLF+
				"kW, PF"+DSSGlobals.CRLF+
				"kW, kvar"+DSSGlobals.CRLF+
				"kVA, PF" +DSSGlobals.CRLF+
				"XFKVA * Allocationfactor, PF" +DSSGlobals.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		PropertyHelp[4] = "Load power factor.  Enter negative for leading powerfactor (when kW and kvar have opposite signs.)";
		PropertyHelp[5] = "Integer code for the model to use for load variation with voltage. "+
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
		PropertyHelp[6] = "Load shape to use for yearly simulations.  Must be previously defined "+
				"as a Loadshape object. Defaults to Daily load shape " +
				" when Daily is defined.  The daily load shape is repeated in this case. "+
				"Set Status=Fixed to ignore Loadshape designation. " +
				"Set to NONE to reset to no loadahape. " +
				"The default is no variation.";
		PropertyHelp[7] = "Load shape to use for daily simulations.  Must be previously defined "+
				"as a Loadshape object of 24 hrs, typically. " +
				"Set Status=Fixed to ignore Loadshape designation. " +
				"Set to NONE to reset to no loadahape. " +
				"Default is no variation (constant) if not defined. " +
				"Side effect: Sets Yearly load shape if not already defined.";
		PropertyHelp[8] = "Load shape to use for duty cycle simulations.  Must be previously defined "+
				"as a Loadshape object.  Typically would have time intervals less than 1 hr. "+
				"Designate the number of points to solve using the Set Number=xxxx command. "+
				"If there are fewer points in the actual shape, the shape is assumed to repeat."+
				"Set to NONE to reset to no loadahape. " +
				"Set Status=Fixed to ignore Loadshape designation. " +
				" Defaults to Daily curve If not specified.";
		PropertyHelp[9] = "Characteristic  to use for growth factors by years.  Must be previously defined "+
				"as a Growthshape object. Defaults to circuit default growth factor (see Set Growth command).";
		PropertyHelp[10] = "={wye or LN | delta or LL}.  Default is wye.";
		PropertyHelp[11] = "Specify the base kvar for specifying load as kW & kvar.  Assumes kW has been already defined.  Alternative to specifying the power factor.  Side effect: "+
				" the power factor and kVA is altered to agree.";
		PropertyHelp[12] = "Default is -1. Neutral resistance of wye (star)-connected load in actual ohms. " +
				"If entered as a negative value, the neutral can be open, or floating, or it can be connected to "+
				"node 0 (ground), which is the usual default. " +
				"If >=0 be sure to explicitly specify the node connection for the neutral, or last, conductor. " +
				"Otherwise, the neutral impedance will be shorted to ground.";
		PropertyHelp[13] = "Neutral reactance of wye(star)-connected load in actual ohms.  May be + or -.";
		PropertyHelp[14] = "={Variable | Fixed | Exempt}.  Default is variable. If Fixed, no load multipliers apply;  however, growth "+
				"multipliers do apply.  All multipliers apply to Variable loads.  Exempt loads are not "+
				"modified by the global load multiplier, such as in load duration curves, etc.  Daily multipliers "+
				"do apply, so setting this property to Exempt is a good way to represent industrial load that stays the same" +
				" day-after-day for the period study.";  // fixed or variable
		PropertyHelp[15] = "An arbitrary integer number representing the class of load so that load values may "+
				"be segregated by load value. Default is 1; not used internally.";
		PropertyHelp[16] = "Default = 0.95.  Minimum per unit voltage for which the MODEL is assumed to apply. " +
				"Below this value, the load model reverts to a constant impedance model.";
		PropertyHelp[17] = "Default = 1.05.  Maximum per unit voltage for which the MODEL is assumed to apply. " +
				"Above this value, the load model reverts to a constant impedance model.";
		PropertyHelp[18] = "Minimum per unit voltage for load EEN evaluations, Normal limit.  Default = 0, which defaults to system \"vminnorm\" " +
				"property (see Set Command under Executive).  If this property is specified, it ALWAYS " +
				"overrides the system specification. This allows you to have different criteria for different loads. "+
				"Set to zero to revert to the default system value.";
		PropertyHelp[19] = "Minimum per unit voltage for load UE evaluations, Emergency limit.  Default = 0, which defaults to system \"vminemerg\" " +
				"property (see Set Command under Executive).  If this property is specified, it ALWAYS " +
				"overrides the system specification. This allows you to have different criteria for different loads. "+
				"Set to zero to revert to the default system value.";
		PropertyHelp[20] = "Default = 0.0.  Rated kVA of service transformer for allocating loads based on connected kVA " +
				"at a bus. Side effect:  kW, PF, and kvar are modified. See help on kVA.";
		PropertyHelp[21] = "Default = 0.5.  Allocation factor for allocating loads based on connected kVA " +
				"at a bus. Side effect:  kW, PF, and kvar are modified by multiplying this factor times the XFKVA (if > 0).";
		PropertyHelp[22] = "Specify base Load in kVA (and power factor)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Legal ways to define base load:"+DSSGlobals.CRLF+
				"kW, PF"+DSSGlobals.CRLF+
				"kW, kvar"+DSSGlobals.CRLF+
				"kVA, PF" +DSSGlobals.CRLF+
				"XFKVA * Allocationfactor, PF" +DSSGlobals.CRLF+
				"kWh/(kWhdays*24) * Cfactor, PF";
		PropertyHelp[23] = "Percent mean value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 50.";
		PropertyHelp[24] = "Percent Std deviation value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 10.";
		PropertyHelp[25] = "Percent reduction in active power (watts) per 1% reduction in voltage from 100% rated. Default=1. " +DSSGlobals.CRLF +
				" Typical values range from 0.4 to 0.8. Applies to Model=4 only." + DSSGlobals.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		PropertyHelp[26] = "Percent reduction in reactive power (vars) per 1% reduction in voltage from 100% rated. Default=2. " +DSSGlobals.CRLF +
				" Typical values range from 2 to 3. Applies to Model=4 only." + DSSGlobals.CRLF +
				" Intended to represent conservation voltage reduction or voltage optimization measures.";
		PropertyHelp[27] = "kWh billed for this period. Default is 0. See help on kVA and Cfactor and kWhDays.";
		PropertyHelp[28] = "Length of kWh billing period in days (24 hr days). Default is 30. Average demand is computed using this value.";   // kwh billing period (24-hr days)
		PropertyHelp[29] = "Factor relating average kW to peak kW. Default is 4.0. See kWh and kWhdays. See kVA.";   // multiplier from kWh avg to peak kW
		PropertyHelp[30] = "Default is NONE. Curve describing both watt and var factors as a function of time. " +
				"Refers to a LoadShape object with both Mult and Qmult defined. " +
				"Define a Loadshape to agree with yearly or daily curve according to the type of analysis being done. " +
				"If NONE, the CVRwatts and CVRvars factors are used and assumed constant.";
		PropertyHelp[31] = "Number of customers, this load. Default is 1.";
		PropertyHelp[32] = "Array of 7 coefficients:" + DSSGlobals.CRLF +
				" First 3 are ZIP weighting factors for real power (should sum to 1)" + DSSGlobals.CRLF +
				" Next 3 are ZIP weighting factors for reactive power (should sum to 1)" + DSSGlobals.CRLF +
				" Last 1 is cut-off voltage in p.u. of base kV; load is 0 below this cut-off" + DSSGlobals.CRLF +
				" No defaults; all coefficients must be specified if using model=8.";


		ActiveProperty = NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list

		PropertyHelp[NumPropsThisClass - 1] = "Name of harmonic current spectrum for this load.  Default is \"defaultload\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new LoadObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void setNcondsForConnection() {
		LoadObj al = getActiveLoadObj();

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
				al.setNConds(al.getNPhases() + 1);  // Open-delta
				break;
			default:
				al.setNConds(al.getNPhases());
				break;
			}
			break;
		}
	}

	/**
	 * Accepts     (checks only min number of chars required}
	 * 		delta or LL           (Case insensitive)
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		LoadObj al = getActiveLoadObj();

		String TestS = S.toLowerCase();
		switch (TestS.charAt(0)) {
		case 'y':
			al.setConnection(0);  /* Wye */
			break;
		case 'w':
			al.setConnection(0);  /* Wye */
			break;
		case 'd':
			al.setConnection(1);  /* Delta or line-Line */
			break;
		case 'l':
			switch (TestS.charAt(1)) {
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
			al.setVBase(al.getkVLoadBase() * 1000.0);
		default:
			switch (al.getNPhases()) {
			case 2:
				al.setVBase(al.getkVLoadBase() * DSSGlobals.InvSQRT3x1000);
				break;
			case 3:
				al.setVBase(al.getkVLoadBase() * DSSGlobals.InvSQRT3x1000);
				break;
			default:
				al.setVBase(al.getkVLoadBase() * 1000.0);
				break;
			}
			break;
		}
		al.setVBase95(al.getVminpu() * al.getVBase());
		al.setVBase105(al.getVmaxpu() * al.getVBase());

		al.setYorder(al.getNConds() * al.getNTerms());
		al.setYprimInvalid(true);
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// Continue parsing with contents of parser
		setActiveLoadObj((LoadObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveLoadObj());

		int Result = 0;

		LoadObj al = getActiveLoadObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				al.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ al.getName() + "\"", 580);
				break;
			case 0:
				al.setNPhases(parser.makeInteger()); // num phases
				break;
			case 1:
				al.setBus(1, Param);  // TODO: Check zero based indexing
				break;
			case 2:
				al.setkVLoadBase(parser.makeDouble());
				break;
			case 3:
				al.setkWBase(parser.makeDouble());
				break;
			case 4:
				al.setPFNominal(parser.makeDouble());
				break;
			case 5:
				al.setLoadModel(parser.makeInteger());
				break;
			case 6:
				al.setYearlyShape(Param);
				break;
			case 7:
				al.setDailyShape(Param);
				break;
			case 8:
				al.setDutyShape(Param);
				break;
			case 9:
				al.setGrowthShape(Param);
				break;
			case 10:
				interpretConnection(Param);
				break;
			case 11:
				al.setKvarBase(parser.makeDouble());
				break;
			case 12:
				al.setRneut(parser.makeDouble());
				break;
			case 13:
				al.setXneut(parser.makeDouble());
				break;
			case 14:
				switch (Param.toLowerCase().charAt(0)) {
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
				al.setVminpu(parser.makeDouble());
				break;
			case 17:
				al.setVmaxpu(parser.makeDouble());
				break;
			case 18:
				al.setVminNormal(parser.makeDouble());
				break;
			case 19:
				al.setVminEmerg(parser.makeDouble());
				break;
			case 20:
				al.setConnectedkVA(parser.makeDouble());
				break;
			case 21:
				al.setkVAAllocationFactor(parser.makeDouble());
				break;
			case 22:
				al.setkVABase(parser.makeDouble());
				break;
			case 23:
				al.setPuMean(parser.makeDouble() / 100.0);
				break;
			case 24:
				al.setPuStdDev(parser.makeDouble() / 100.0);
				break;
			case 25:
				al.setCVRwattFactor(parser.makeDouble());
				break;
			case 26:
				al.setCVRvarFactor(parser.makeDouble());
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
				al.setCVRshape(Param);
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
				classEdit(getActiveLoadObj(), ParamPointer - Load.NumPropsThisClass);
				break;
			}

			// SIDE EFFECTS
			// keep kvar nominal up to date WITH kW and PF
			switch (ParamPointer) {
			case 0:
				setNcondsForConnection();  // Force Reallocation of terminal info
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
				/* Set shape objects;  returns nil if not valid */
				/* Sets the kW and kvar properties to match the peak kW demand from the Loadshape */
				al.setYearlyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(al.getYearlyShape()));
				if (al.getYearlyShapeObj() != null)
					if (al.getYearlyShapeObj().isUseActual())
						al.setkWkvar(al.getYearlyShapeObj().getMaxP(), al.getYearlyShapeObj().getMaxQ());
				break;
			case 7:
				al.setDailyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(al.getDailyShape()));
				if (al.getDailyShapeObj() != null)
					if (al.getDailyShapeObj().isUseActual())
						al.setkWkvar(al.getDailyShapeObj().getMaxP(), al.getDailyShapeObj().getMaxQ());
				/* If Yearly load shape is not yet defined, make it the same as Daily */
				if (al.getYearlyShapeObj() == null)
					al.setYearlyShapeObj(al.getDailyShapeObj());
				break;
			case 8:
				al.setDutyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(al.getDutyShape()));
				if (al.getDutyShapeObj() != null)
					if (al.getDutyShapeObj().isUseActual())
						al.setkWkvar(al.getDutyShapeObj().getMaxP(), al.getDutyShapeObj().getMaxQ());
				break;
			case 9:
				al.setGrowthShapeObj((GrowthShapeObj) Globals.getGrowthShapeClass().find(al.getGrowthShape()));
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
				al.setCVRShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find(al.getCVRshape()));
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		al.recalcElementData();
		al.setYprimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherLoadName) {
		int Result = 0;

		/* See if we can find this line name in the present collection */
		LoadObj OtherLoad = (LoadObj) find(OtherLoadName);
		if (OtherLoad != null) {
			LoadObj al = getActiveLoadObj();

			if (al.getNPhases() != OtherLoad.getNPhases()) {
				al.setNPhases(OtherLoad.getNPhases());
				al.setNConds(al.getNPhases());  // Forces reallocation of terminal stuff
				al.setYorder(al.getNConds() * al.getNTerms());
				al.setYprimInvalid(true);
			}

			al.setkVLoadBase(OtherLoad.getkVLoadBase());
			al.setVBase(OtherLoad.getVBase());
			al.setVminpu(OtherLoad.getVminpu());
			al.setVmaxpu(OtherLoad.getVmaxpu());
			al.setVBase95(OtherLoad.getVBase95());
			al.setVBase105(OtherLoad.getVBase105());
			al.setkWBase(OtherLoad.getkWBase());
			al.setkVABase(OtherLoad.getkVABase());
			al.setKvarBase(OtherLoad.getKvarBase());
			al.setLoadSpecType(OtherLoad.getLoadSpecType());
			al.setWNominal(OtherLoad.getWNominal());
			al.setPFNominal(OtherLoad.getPFNominal());
			al.setVarNominal(OtherLoad.getVarNominal());
			al.setConnection(OtherLoad.getConnection());
			al.setRneut(OtherLoad.getRneut());
			al.setXneut(OtherLoad.getXneut());
			al.setYearlyShape(OtherLoad.getYearlyShape());
			al.setYearlyShapeObj(OtherLoad.getYearlyShapeObj());
			al.setCVRshape(OtherLoad.getCVRshape());
			al.setCVRShapeObj(OtherLoad.getCVRShapeObj());
			al.setDailyShape(OtherLoad.getDailyShape());
			al.setDailyShapeObj(OtherLoad.getDailyShapeObj());
			al.setDutyShape(OtherLoad.getDutyShape());
			al.setDutyShapeObj(OtherLoad.getDutyShapeObj());
			al.setGrowthShape(OtherLoad.getGrowthShape());
			al.setGrowthShapeObj(OtherLoad.getGrowthShapeObj());
			//al.setSpectrum(OtherLoad.getSpectrum();       in base class now
			//al.setSpectrumObj(OtherLoad.getSpectrumObj());
			al.setLoadClass(OtherLoad.getLoadClass());
			al.setNumCustomers(OtherLoad.getNumCustomers());
			al.setLoadModel(OtherLoad.getLoadModel());
			al.setFixed(OtherLoad.isFixed());
			al.setExemptFromLDCurve(OtherLoad.isExemptFromLDCurve());
			al.setkVAAllocationFactor(OtherLoad.getkVAAllocationFactor());
			al.setConnectedkVA(OtherLoad.getConnectedkVA());
			al.setCVRwattFactor(OtherLoad.getCVRwattFactor());
			al.setCVRvarFactor(OtherLoad.getCVRvarFactor());
			al.setShapeIsActual(OtherLoad.isShapeIsActual());

			al.setZIPVSize(OtherLoad.getnZIPV());
			for (int i = 0; i < al.getnZIPV(); i++)
				al.getZIPV()[i] = OtherLoad.getZIPV()[i];

			classMakeLike(OtherLoad);  // Take care of inherited class properties


			for (int i = 0; i < al.getParentClass().getNumProperties(); i++)
				al.setPropertyValue(i, OtherLoad.getPropertyValue(i));

			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Load makeLike: \"" + OtherLoadName + "\" Not Found.", 581);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		LoadObj pLoad;
		if (Handle == 0) {
			for (int i = 0; i < ElementList.size(); i++) {
				pLoad = (LoadObj) ElementList.get(i);
				pLoad.randomize(0);
			}
		} else {
			setActiveElement(Handle);
			pLoad = (LoadObj) getActiveObj();
			pLoad.randomize(0);
		}

		DSSGlobals.getInstance().doSimpleMsg("Need to finish implementation Load.init", -1);
		return 0;
	}

	public static LoadObj getActiveLoadObj() {
		return ActiveLoadObj;
	}

	public static void setActiveLoadObj(LoadObj activeLoadObj) {
		ActiveLoadObj = activeLoadObj;
	}

}

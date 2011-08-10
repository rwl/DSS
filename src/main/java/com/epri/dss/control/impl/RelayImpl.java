package com.epri.dss.control.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.Relay;
import com.epri.dss.control.RelayObj;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class RelayImpl extends ControlClassImpl implements Relay {

	private static RelayObj ActiveRelayObj;

	private DSSClass TCC_CurveClass;

	public RelayImpl() {
		super();

		this.Class_Name = "Relay";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.RELAY_CONTROL;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);

		this.TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");
	}

	protected void defineProperties() {

		NumProperties = Relay.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();  /* see DSSClass */


		// define property names

		addProperty("MonitoredObj", 0,
				"Full object name of the circuit element, typically a line, transformer, load, or generator, "+
				"to which the relay's PT and/or CT are connected." +
				" This is the \"monitored\" element. " +
				"There is no default; must be specified.");
		addProperty("MonitoredTerm", 1,
				"Number of the terminal of the circuit element to which the Relay is connected. "+
				"1 or 2, typically.  Default is 1.");
		addProperty("SwitchedObj", 2,
				"Name of circuit element switch that the Relay controls. "+
				"Specify the full object name." +
				"Defaults to the same as the Monitored element. "+
				"This is the \"controlled\" element.");
		addProperty( "SwitchedTerm", 3,
				"Number of the terminal of the controlled element in which the switch is controlled by the Relay. "+
				"1 or 2, typically.  Default is 1.");
		addProperty("type", 4, "One of a legal relay type:" +DSSGlobals.CRLF+
				"Current"+DSSGlobals.CRLF+"Voltage"+DSSGlobals.CRLF+"Reversepower"+DSSGlobals.CRLF+"46 (neg seq current)"+DSSGlobals.CRLF+
				"47 (neg seq voltage)"+DSSGlobals.CRLF+
				"Generic (generic over/under relay)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Default is overcurrent relay (Current) " +
				"Specify the curve and pickup settings appropriate for each type. "+
				"Generic relays monitor PC Element Control variables and trip on out of over/under range in definite time.");
		addProperty("Phasecurve", 5, "Name of the TCC Curve object that determines the phase trip.  "+
				"Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored). "+
				"For overcurrent relay, multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.");
		addProperty("Groundcurve", 6, "Name of the TCC Curve object that determines the ground trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored)."+
				"For overcurrent relay, multiplying the current values in the curve by the \"groundtrip\" valuw gives the actual current.");
		addProperty("PhaseTrip", 7, "Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.");
		addProperty("GroundTrip",8, "Multiplier or actual ground amps (3I0) for the ground TCC curve.  Defaults to 1.0.");
		addProperty("TDPhase", 27, "Time dial for Phase trip curve. Multiplier on time axis of specified curve. Default=1.0.");
		addProperty("TDGround", 28, "Time dial for Ground trip curve. Multiplier on time axis of specified curve. Default=1.0.");
		addProperty("PhaseInst", 9, "Actual  amps (Current relay) or kW (reverse power relay) for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. "+
				"Use this value for specifying the Reverse Power threshold (kW) for reverse power relays.");
		addProperty("GroundInst", 10, "Actual  amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.");
		addProperty("Reset", 11, "Reset time in sec for relay.  Default is 15. If ");
		addProperty("Shots", 12, "Number of shots to lockout.  Default is 4. This is one more than the number of reclose intervals.");
		addProperty("RecloseIntervals", 13, "Array of reclose intervals. If none, specify \"NONE\". Default for overcurrent relay is (0.5, 2.0, 2.0) seconds. " +
				"Default for a voltage relay is (5.0). In a voltage relay, this is  seconds after restoration of " +
				"voltage that the reclose occurs. " +
				"Reverse power relay is one shot to lockout, "+
				"so this is ignored.  A locked out relay must be closed manually (set action=close).");
		addProperty("Delay", 23, "Trip time delay (sec) for definite time relays. Default is 0.0 for current and voltage relays.  If >0 then this value is used instead of curves. "+
				" Used exclusively by RevPower, 46 and 47 relays at this release. Defaults to 0.1 s for these relays.");
		addProperty("Overvoltcurve", 14, "TCC Curve object to use for overvoltage relay.  Curve is assumed to be defined with per unit voltage values. "+
				"Voltage base should be defined for the relay. Default is none (ignored).");
		addProperty("Undervoltcurve", 15, "TCC Curve object to use for undervoltage relay.  Curve is assumed to be defined with per unit voltage values. "+
				"Voltage base should be defined for the relay. Default is none (ignored).");
		addProperty("kvbase", 16, "Voltage base (kV) for the relay. Specify line-line for 3 phase devices); line-neutral for 1-phase devices.  Relay assumes " +
				"the number of phases of the monitored element.  Default is 0.0, which results in assuming the voltage " +
				"values in the \"TCC\" curve are specified in actual line-to-neutral volts.");
		addProperty("47%Pickup", 24, "Percent voltage pickup for 47 relay (Neg seq voltage). Default is 2. Specify also base voltage (kvbase) and delay time value.   ");
		addProperty("46BaseAmps", 22, "Base current, Amps, for 46 relay (neg seq current)." +
				"  Used for establishing pickup and per unit I-squared-t." );
		addProperty("46%Pickup", 20, "Percent pickup current for 46 relay (neg seq current).  Default is 20.0. " +
				"  When current exceeds this value * BaseAmps, I-squared-t calc starts." );
		addProperty("46isqt", 21, "Negative Sequence I-squared-t trip value for 46 relay (neg seq current)." +
				"  Default is 1 (trips in 1 sec for 1 per unit neg seq current).  Should be 1 to 99.");
		addProperty("Variable", 19, "Name of variable in PC Elements being monitored.  Only applies to Generic relay.");
		addProperty("overtrip", 25, "Trip setting (high value) for Generic relay variable.  Relay trips in definite time if value of variable exceeds this value.");
		addProperty("undertrip", 26,"Trip setting (low value) for Generic relay variable.  Relay trips in definite time if value of variable is less than this value.");
		addProperty("Breakertime",17, "Fixed delay time (sec) added to relay time. Default is 0.0. Designed to represent breaker time or some other delay after a trip decision is made." +
				"Use Delay_Time property for setting a fixed trip time delay." +
				"Added to trip time of current and voltage relays. Could use in combination with inst trip value to obtain a definite time overcurrent relay.");
		addProperty("action", 18, "{Trip/Open | Close}  Action that overrides the relay control. Simulates manual control on breaker. " +
				"\"Trip\" or \"Open\" causes the controlled element to open and lock out. " +
				"\"Close\" causes the controlled element to close and the relay to reset to its first operation.");

		ActiveProperty = Relay.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new RelayObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	public TCC_CurveObj getTccCurve(String CurveName) {

		TCC_CurveObj Result = (TCC_CurveObj) TCC_CurveClass.find(CurveName);

		if (Result == null)
			DSSGlobals.getInstance().doSimpleMsg("TCC Curve object: \""+CurveName+"\" not found.", 380);

		return Result;
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveRelayObj((RelayObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveRelayObj());

		int Result = 0;

		RelayObj ar = getActiveRelayObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer <= NumProperties)) {
				ar.setPropertyValue(PropertyIdxMap[ParamPointer], Param);
			} else {
				Globals.doSimpleMsg("Unknown parameter \""+ParamName+"\" for Relay \""+ar.getName()+"\"", 381);
			}

			if (ParamPointer >= 0) {
				switch (PropertyIdxMap[ParamPointer]) {
				/* internal relay property commands */
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ ar.getName() + "\"", 382);
					break;
				case 0:
					ar.setMonitoredElementName(Param.toLowerCase());
					break;
				case 1:
					ar.setMonitoredElementTerminal(parser.makeInteger());
					break;
				case 2:
					ar.setElementName(Param.toLowerCase());
					break;
				case 3:
					ar.setElementTerminal(parser.makeInteger());
					break;
				case 4:
					ar.interpretRelayType(Param);
					break;
				case 5:
					ar.setPhaseCurve(getTccCurve(Param));
					break;
				case 6:
					ar.setGroundCurve(getTccCurve(Param));
					break;
				case 7:
					ar.setPhaseTrip(parser.makeDouble());
					break;
				case 8:
					ar.setGroundTrip(parser.makeDouble());
					break;
				case 9:
					ar.setPhaseInst(parser.makeDouble());
					break;
				case 10:
					ar.setGroundInst(parser.makeDouble());
					break;
				case 11:
					ar.setResetTime(parser.makeDouble());
					break;
				case 12:
					ar.setNumReclose(parser.makeInteger() - 1);  // one less than number of shots
					break;
				case 13:
					if (Param.equalsIgnoreCase("NONE")) {
						ar.setNumReclose(1);
					} else {
						ar.setNumReclose(parser.parseAsVector(4, ar.getRecloseIntervals()));  // max of 4 allowed
					}
					break;
				case 14:
					ar.setOVcurve(getTccCurve(Param));
					break;
				case 15:
					ar.setUVCurve(getTccCurve(Param));
					break;
				case 16:
					ar.setkVBase(parser.makeDouble());
					break;
				case 17:
					ar.setBreaker_time(parser.makeDouble());
					break;
				case 18:
					ar.interpretRelayAction(Param);
					break;
				case 19:
					ar.setMonitorVariable(Param.toLowerCase());  // for pc elements
					break;
				case 20:
					ar.setPctPickup46(parser.makeDouble());
					break;
				case 21:
					ar.setIsqt46(parser.makeDouble());
					break;
				case 22:
					ar.setBaseAmps46(parser.makeDouble());
					break;
				case 23:
					ar.setDelay_Time(parser.makeDouble());
					break;
				case 24:
					ar.setPctPickup47(parser.makeDouble());
					break;
				case 25:
					ar.setOverTrip(parser.makeDouble());
					break;
				case 26:
					ar.setUnderTrip(parser.makeDouble());
					break;
				case 27:
					ar.setTDPhase(parser.makeDouble());
					break;
				case 28:
					ar.setTDGround(parser.makeDouble());
					break;
				default:
					// Inherited parameters
					classEdit(ActiveRelayObj, ParamPointer - Relay.NumPropsThisClass);
					break;
				}
			}

			if (ParamPointer >= 0) {
				switch (PropertyIdxMap[ParamPointer]) {
				/* Default the controlled element to the monitored element */
				case 0:
					ar.setElementName(ar.getMonitoredElementName());
					break;
				case 1:
					ar.setElementTerminal(ar.getMonitoredElementTerminal());
					break;
				case 4:  /* Set Default Reclose Intervals */
					switch (Param.toLowerCase().charAt(0)) {
					case 'c':
						ar.setPropertyValue(13, "(0.5, 2.0, 2.0)");
						break;
					case 'v':
						ar.setPropertyValue(13, "(5.0)");
						break;
					}
					Globals.getAuxParser().setCmdString(ar.getPropertyValue(13));
					ParamName = Globals.getAuxParser().getNextParam();
					ar.setNumReclose(Globals.getAuxParser().parseAsVector(4, ar.getRecloseIntervals()));
					break;
				}
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		ar.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String RelayName) {
		int Result = 0;
		/* See if we can find this Relay name in the present collection */
		RelayObj OtherRelay = (RelayObj) find(RelayName);
		if (OtherRelay != null) {

			RelayObj ar = getActiveRelayObj();

			ar.setNPhases(OtherRelay.getNPhases());
			ar.setNConds(OtherRelay.getNConds()); // Force Reallocation of terminal stuff

			ar.setElementName(OtherRelay.getElementName());
			ar.setElementTerminal(OtherRelay.getElementTerminal());
			ar.setControlledElement(OtherRelay.getControlledElement());  // Pointer to target circuit element

			ar.setMonitoredElement(OtherRelay.getMonitoredElement());  // Pointer to target circuit element
			ar.setMonitoredElementName(OtherRelay.getMonitoredElementName());  // Pointer to target circuit element
			ar.setMonitoredElementTerminal(OtherRelay.getMonitoredElementTerminal());  // Pointer to target circuit element

			ar.setPhaseCurve(OtherRelay.getPhaseCurve());
			ar.setGroundCurve(OtherRelay.getGroundCurve());
			ar.setOVcurve(OtherRelay.getOVcurve());
			ar.setUVCurve(OtherRelay.getUVCurve());
			ar.setPhaseTrip(OtherRelay.getPhaseTrip());
			ar.setGroundTrip(OtherRelay.getGroundTrip());
			ar.setTDPhase(OtherRelay.getTDPhase());
			ar.setTDGround(OtherRelay.getTDGround());
			ar.setPhaseInst(OtherRelay.getPhaseInst());
			ar.setGroundInst(OtherRelay.getGroundInst());
			ar.setResetTime(OtherRelay.getResetTime());
			ar.setNumReclose(OtherRelay.getNumReclose());
			ar.setDelay_Time(OtherRelay.getDelay_Time());
			ar.setBreaker_time(OtherRelay.getBreaker_time());

			ar.setRecloseIntervals( (double[]) Utilities.resizeArray(ar.getRecloseIntervals(), 4) );  // Always make a max of 4
			for (int i = 0; i < ar.getNumReclose(); i++)
				ar.getRecloseIntervals()[i] = OtherRelay.getRecloseIntervals()[i];

			ar.setkVBase(OtherRelay.getkVBase());
			ar.setLockedOut(OtherRelay.isLockedOut());

			ar.setControlType(OtherRelay.getControlType());
			ar.setPresentState(OtherRelay.getPresentState());
			ar.setCondOffset(OtherRelay.getCondOffset());

			/* 46 Relay Neg Seq Current */
			ar.setPickupAmps46(OtherRelay.getPickupAmps46());
			ar.setPctPickup46(OtherRelay.getPctPickup46());
			ar.setBaseAmps46(OtherRelay.getBaseAmps46());
			ar.setIsqt46(OtherRelay.getIsqt46());

			/* 47 Relay */
			ar.setPickupVolts47(OtherRelay.getPickupVolts47());
			ar.setPctPickup47(OtherRelay.getPctPickup47());

			/* Generic Relay */
			ar.setMonitorVariable(OtherRelay.getMonitorVariable());
			ar.setOverTrip(OtherRelay.getOverTrip());
			ar.setUnderTrip(OtherRelay.getUnderTrip());

			for (int i = 0; i < ar.getParentClass().getNumProperties(); i++)
				ar.setPropertyValue(i, OtherRelay.getPropertyValue(i));

		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Relay makeLike: \"" + RelayName + "\" Not Found.", 383);
		}

		return Result;
	}

	public static void setActiveRelayObj(RelayObj activeRelayObj) {
		ActiveRelayObj = activeRelayObj;
	}

	public static RelayObj getActiveRelayObj() {
		return ActiveRelayObj;
	}

}

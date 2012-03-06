package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.TCC_CurveObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Relay extends ControlClass {

	public static final int NumPropsThisClass = 29;

	public static final int CURRENT = 0;  /* Default */
	public static final int VOLTAGE = 1;
	public static final int REVPOWER = 3;
	public static final int NEGCURRENT = 4;
	public static final int NEGVOLTAGE = 5;
	public static final int GENERIC = 6;  /* Use this for frequency, etc. Generic over/under relay */

	public static RelayObj activeRelayObj;

	private DSSClass TCC_CurveClass;

	public Relay() {
		super();

		className = "Relay";
		classType = classType + DSSClassDefs.RELAY_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);

		TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");
	}

	protected void defineProperties() {

		numProperties = Relay.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

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
		addProperty("type", 4, "One of a legal relay type:" +DSS.CRLF+
				"Current"+DSS.CRLF+"Voltage"+DSS.CRLF+"Reversepower"+DSS.CRLF+"46 (neg seq current)"+DSS.CRLF+
				"47 (neg seq voltage)"+DSS.CRLF+
				"Generic (generic over/under relay)"+DSS.CRLF+DSS.CRLF+
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

		activeProperty = Relay.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	public int newObject(String objName) {

		DSS.activeCircuit.setActiveCktElement(new RelayObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	public TCC_CurveObj getTccCurve(String curveName) {

		TCC_CurveObj result = (TCC_CurveObj) TCC_CurveClass.find(curveName);

		if (result == null)
			DSS.doSimpleMsg("TCC curve object: \""+curveName+"\" not found.", 380);

		return result;
	}

	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeRelayObj = (RelayObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeRelayObj);

		int result = 0;

		RelayObj ar = activeRelayObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties) {
				ar.setPropertyValue(propertyIdxMap[paramPointer], param);
			} else {
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for relay \""+ar.getName()+"\"", 381);
			}

			if (paramPointer >= 0) {
				switch (propertyIdxMap[paramPointer]) {
				/* internal relay property commands */
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ ar.getName() + "\"", 382);
					break;
				case 0:
					ar.setMonitoredElementName(param.toLowerCase());
					break;
				case 1:
					ar.setMonitoredElementTerminal(parser.makeInteger());
					break;
				case 2:
					ar.setElementName(param.toLowerCase());
					break;
				case 3:
					ar.setElementTerminal(parser.makeInteger());
					break;
				case 4:
					ar.interpretRelayType(param);
					break;
				case 5:
					ar.setPhaseCurve(getTccCurve(param));
					break;
				case 6:
					ar.setGroundCurve(getTccCurve(param));
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
					if (param.equalsIgnoreCase("none")) {
						ar.setNumReclose(1);
					} else {
						ar.setNumReclose(parser.parseAsVector(4, ar.getRecloseIntervals()));  // max of 4 allowed
					}
					break;
				case 14:
					ar.setOVCurve(getTccCurve(param));
					break;
				case 15:
					ar.setUVCurve(getTccCurve(param));
					break;
				case 16:
					ar.setKVBase(parser.makeDouble());
					break;
				case 17:
					ar.setBreakerTime(parser.makeDouble());
					break;
				case 18:
					ar.interpretRelayAction(param);
					break;
				case 19:
					ar.setMonitorVariable(param.toLowerCase());  // for pc elements
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
					ar.setDelayTime(parser.makeDouble());
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
					// inherited parameters
					classEdit(activeRelayObj, paramPointer - Relay.NumPropsThisClass);
					break;
				}
			}

			if (paramPointer >= 0) {
				switch (propertyIdxMap[paramPointer]) {
				/* Default the controlled element to the monitored element */
				case 0:
					ar.setElementName(ar.getMonitoredElementName());
					break;
				case 1:
					ar.setElementTerminal(ar.getMonitoredElementTerminal());
					break;
				case 4:  /* Set default reclose intervals */
					switch (param.toLowerCase().charAt(0)) {
					case 'c':
						ar.setPropertyValue(13, "(0.5, 2.0, 2.0)");
						break;
					case 'v':
						ar.setPropertyValue(13, "(5.0)");
						break;
					}
					DSS.auxParser.setCmdString(ar.getPropertyValue(13));
					paramName = DSS.auxParser.getNextParam();
					ar.setNumReclose(DSS.auxParser.parseAsVector(4, ar.getRecloseIntervals()));
					break;
				}
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		ar.recalcElementData();

		return result;
	}

	protected int makeLike(String relayName) {
		int result = 0;
		/* See if we can find this relay name in the present collection */
		RelayObj otherRelay = (RelayObj) find(relayName);
		if (otherRelay != null) {

			RelayObj ar = activeRelayObj;

			ar.setNumPhases(otherRelay.getNumPhases());
			ar.setNumConds(otherRelay.getNumConds());  // force reallocation of terminal stuff

			ar.setElementName(otherRelay.getElementName());
			ar.setElementTerminal(otherRelay.getElementTerminal());
			ar.setControlledElement(otherRelay.getControlledElement());  // target circuit element

			ar.setMonitoredElement(otherRelay.getMonitoredElement());  // target circuit element
			ar.setMonitoredElementName(otherRelay.getMonitoredElementName());  // target circuit element
			ar.setMonitoredElementTerminal(otherRelay.getMonitoredElementTerminal());  // target circuit element

			ar.setPhaseCurve(otherRelay.getPhaseCurve());
			ar.setGroundCurve(otherRelay.getGroundCurve());
			ar.setOVCurve(otherRelay.getOVCurve());
			ar.setUVCurve(otherRelay.getUVCurve());
			ar.setPhaseTrip(otherRelay.getPhaseTrip());
			ar.setGroundTrip(otherRelay.getGroundTrip());
			ar.setTDPhase(otherRelay.getTDPhase());
			ar.setTDGround(otherRelay.getTDGround());
			ar.setPhaseInst(otherRelay.getPhaseInst());
			ar.setGroundInst(otherRelay.getGroundInst());
			ar.setResetTime(otherRelay.getResetTime());
			ar.setNumReclose(otherRelay.getNumReclose());
			ar.setDelayTime(otherRelay.getDelayTime());
			ar.setBreakerTime(otherRelay.getBreakerTime());

			ar.setRecloseIntervals( Util.resizeArray(ar.getRecloseIntervals(), 4) );  // always make a max of 4
			for (int i = 0; i < ar.getNumReclose(); i++)
				ar.getRecloseIntervals()[i] = otherRelay.getRecloseIntervals()[i];

			ar.setKVBase(otherRelay.getKVBase());
			ar.setLockedOut(otherRelay.isLockedOut());

			ar.setControlType(otherRelay.getControlType());
			ar.setPresentState(otherRelay.getPresentState());
			ar.setCondOffset(otherRelay.getCondOffset());

			/* 46 relay neg seq current */
			ar.setPickupAmps46(otherRelay.getPickupAmps46());
			ar.setPctPickup46(otherRelay.getPctPickup46());
			ar.setBaseAmps46(otherRelay.getBaseAmps46());
			ar.setIsqt46(otherRelay.getIsqt46());

			/* 47 relay */
			ar.setPickupVolts47(otherRelay.getPickupVolts47());
			ar.setPctPickup47(otherRelay.getPctPickup47());

			/* Generic relay */
			ar.setMonitorVariable(otherRelay.getMonitorVariable());
			ar.setOverTrip(otherRelay.getOverTrip());
			ar.setUnderTrip(otherRelay.getUnderTrip());

			for (int i = 0; i < ar.getParentClass().getNumProperties(); i++)
				ar.setPropertyValue(i, otherRelay.getPropertyValue(i));

		} else {
			DSS.doSimpleMsg("Error in Relay makeLike: \"" + relayName + "\" not found.", 383);
		}

		return result;
	}

	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Relay.init", -1);
		return 0;
	}

}

package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.XYCurveObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

/**
 * A VVCControl is a control element that is connected to a terminal of another
 * circuit element and sends dispatch kW signals and kvar to a set of generators it controls
 *
 * A VVCControl is defined by a New command:
 *
 * New VVCControl.Name=myname Element=devclass.name terminal=[ 1|2|...] GenList = (gen1  gen2 ...)
 *
 */
public class VVControl extends ControlClass {

	public static final int NumPropsThisClass = 19;

	public static VVControlObj activeVVCControlObj;

	private DSSClass XY_CurveClass;

	public VVControl() {

		className = "VVControl";
		classType = classType + DSSClassDefs.VV_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);

		XY_CurveClass = DSSClassDefs.getDSSClass("XYCurve");
	}

	protected void defineProperties() {

		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "Element";
		propertyName[1] = "Terminal";
		propertyName[2] = "vvc_Vmaxpu";
		propertyName[3] = "vvc_Vminpu";
		propertyName[4] = "kva_rating";
		propertyName[5] = "kW_rating";
		propertyName[6] = "kvar_full_output";
		propertyName[7] = "pf";
		propertyName[8] = "delay";
		propertyName[9] = "delayoff";
		propertyName[10] = "kW_ramp_rate";
		propertyName[11] = "kvar_ramp_rate";
		propertyName[12] = "kW_limit";
		propertyName[13] = "kvar_limit";
		propertyName[14] = "GenList";
		propertyName[15] = "Weights";
		propertyName[16] = "NumPts";
		propertyName[17] = "VVC_curve";
		propertyName[18] = "deltaQ_factor";

		propertyHelp[0] =
		"Full object name of the circuit element, typically a line or transformer, " + "which the control is monitoring. There is no default; must be specified.";
		propertyHelp[1] =
		"Number of the terminal of the circuit element to which the VVCControl control is connected. " + "1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		propertyHelp[2] =
		"Default = 0.90.  Minimum per unit voltage for which the vvccurve volts property is assumed to apply. "
		+ "Below this value, the var output is zero (i.e., the unit will not operate).";
		propertyHelp[3] =
		"Default = 1.10.  Maximum per unit voltage for which the vvccurve volts property is assumed to apply. "
		+ "Above this value, the var output is zero (i.e., the unit will not operate).";
		propertyHelp[4] =
		"Default = 1.2 times the kW_rating of the unit.  Maximum steady-state apparent power output.";
		propertyHelp[5] =
		"Default = 4.0.  Maximum steady-state active power output of the unit under control.";
		propertyHelp[6] =
		"Max kvar to be delivered through the element.  Corresponds to the +/- 1.0 per-unit var value in the volt/var curve.";
		propertyHelp[7] =
		"Displacement power factor set-point of the inverter (modeled as a generator).  PF set-point will not cause delivered kvar to exceed the maximum kvar limit.";
		propertyHelp[8] =
		"Delay in seconds for switching ON the inverter (modeled as a generator). Default is 0.0 s";
		propertyHelp[9] =
		"Delay in seconds for switching OFF the inverter (modeled as a generator). Default is 0.0 s";
		propertyHelp[10] =
		"Ramp rate in kW per second for turning ON and OFF the inverter.  Ramps the kW from 0 or other full to kW_rating over x seconds. Default is -1 denoting immediate switch ON/OFF, after optional delay";
		propertyHelp[11] =
		"Ramp rate in kvar per second for turning ON and OFF the inverter.  Ramps the kW from 0 or other full to kvar_limit over x seconds. Default is -1 denoting immediate switch ON/OFF, after optional delay";

		propertyHelp[12] =
		"kW Limit for the monitored element. The generators are dispatched to hold the active power to attempt to achieve this value.";
		propertyHelp[13] =
		"kvar Limit for the monitored element. The generators are dispatched to hold the reactive power to attempt to achieve this value.";
		propertyHelp[14] =
		"Array list of generators to be dispatched.  If not specified, all generators in the circuit are assumed dispatchable.";
		propertyHelp[15] =
		"Array of proportional weights corresponding to each generator in the GenList."
		+ " The needed kW to get back to center band is dispatched to each generator according to these weights. " + "Default is to set all weights to 1.0.";
		propertyHelp[16] =
		"Number of points expected to be in the volt curve or the var curve (XYCurve object).";
		propertyHelp[17] =
		"Name of the volt-var curve that has been previously defined using the XYCurve object.";
		propertyHelp[18] =
		"deltaQ_factor.  The maximum change in per-unit from the prior var output to the var output indicated by the volt-var curve (XYCurve object).";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	private XYCurveObj getVVCCurve(final String curveName) {

		XYCurveObj result = (XYCurveObj) DSS.XYCurveClass.find(curveName);

		if (result == null)
			DSS.doSimpleMsg("XY curve object: \"" + curveName + "\" not found.", 380);

		return result;
	}

	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new VVControlObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeVVCControlObj = (VVControlObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeVVCControlObj);

		int result = 0;

		VVControlObj avc = activeVVCControlObj;

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
				avc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" +
						className + "." + avc.getName() + "\"", 364);
				break;
			case 0:
				avc.setElementName(param.toLowerCase());
				break;
			case 1:
				avc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				avc.setVvc_Vmaxpu(parser.makeDouble());
				break;
			case 3:
				avc.setVvc_Vminpu(parser.makeDouble());
				break;
			case 4:
				avc.setKva_rating(parser.makeDouble());
				break;
			case 5:
				avc.setkW_rating(parser.makeDouble());
				break;
			case 6:
				avc.setKvar_fulloutput(parser.makeDouble());
				break;
			case 7:
				avc.setPf(parser.makeDouble());
				break;
			case 8:
				avc.setDelay(parser.makeDouble());
				break;
			case 9:
				avc.setDelayoff(parser.makeDouble());
				break;
			case 10:
				avc.setkW_ramp_rate(parser.makeDouble());
				break;
			case 11:
				avc.setKvar_ramp_rate(parser.makeDouble());
				break;
			case 12:
				avc.setkW_limit(parser.makeDouble());
				break;
			case 13:
				avc.setKvar_limit(parser.makeDouble());
				break;

			case 14:
				Util.interpretStringListArray(param, avc.getGeneratorNameList());
				break;
			case 15:
				avc.setListSize(avc.getGeneratorNameList().size());
				if (avc.getListSize() > 0) {
					Util.resizeArray(avc.getWeights(), avc.getListSize());
					Util.interpretDblArray(param, avc.getListSize(), avc.getWeights());
				}
				break;
			case 16:
				avc.setVvc_curve_size(parser.makeInteger());
				break;
			case 17:
				avc.setVvc_curve(getVVCCurve(param));
				break;
			case 18:
				avc.setDeltaQ_factor(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeVVCControlObj, paramPointer - NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 14:
				// re-alloc based on
				avc.getGenPointerList().clear();  // clear this for resetting on first sample
				avc.setListSize(avc.getGeneratorNameList().size());
				Util.resizeArray(avc.getWeights(), avc.getListSize());
				for (int i = 0; i < avc.getListSize(); i++)
					avc.getWeights()[i] = 1.0;
			case 17:
				// re-set the number vvc_curve_size property to the number
				// of points in the curve
				if (avc.getVvc_curve().getNumPoints() != avc.getVvc_curve_size())
					avc.setVvc_curve_size(avc.getVvc_curve().getNumPoints());
			}


			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		avc.recalcElementData();
		return result;
	}

	protected int makeLike(String VVCControlName) {
		int result = 0;

		/* See if we can find this VVCControl name in the present collection */
		VVControlObj otherVVCControl = (VVControlObj) find(VVCControlName);
		if (otherVVCControl != null) {
			VVControlObj avc = activeVVCControlObj;

			avc.setNumPhases(otherVVCControl.getNumPhases());
			avc.setNumConds(otherVVCControl.getNumConds());  // force reallocation of terminal data

			avc.setElementName(otherVVCControl.getElementName());
			avc.setControlledElement(otherVVCControl.getControlElement());
			// target circuit element
			avc.setMonitoredElement(otherVVCControl.getMonitoredElement());
			// monitored circuit element
			avc.setElementTerminal(otherVVCControl.getElementTerminal());
			avc.setCondOffset(otherVVCControl.getCondOffset());
			avc.setDeltaVTolerance(otherVVCControl.getDeltaVTolerance());

			for (int i = 0; i < avc.getParentClass().getNumProperties(); i++)
				avc.setPropertyValue(i, otherVVCControl.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in VVCControl makeLike: \"" + VVCControlName +
				  "\" Not Found.", 370);
		}
		return result;
	}

	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement VVControl.init", -1);
		return 0;
	}

}

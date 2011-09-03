package com.epri.dss.control.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.control.VVControl;
import com.epri.dss.control.VVControlObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.impl.CommandListImpl;

public class VVControlImpl extends ControlClassImpl implements VVControl {

	public static VVControlObj activeVVCControlObj;

	private DSSClass XY_CurveClass;

	public VVControlImpl() {

		className = "VVControl";
		classType = classType + DSSClassDefs.VV_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
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
		    "Number of points expected to be in the volt curve or the var curve.";
		  propertyHelp[17] =
		    "Name of the volt-var curve that has been previously defined using the VVC_Curve object.";
		  propertyName[18] =
		    "deltaQ_factor.  The maximum change in per-unit from the prior var output to the var output indicated by the volt-var curve.";

		  activeProperty = NumPropsThisClass;
		  super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	private XYCurveObj getVVCCurve(final String CurveName) {

		XYCurveObj Result = (XYCurveObj) DSSGlobals.XYCurveClass.find(CurveName);

		if (Result == null)
			DSSGlobals.doSimpleMsg("XY Curve object: \"" + CurveName + "\" not found.", 380);

		return Result;
	}

	@Override
	public int newObject(String ObjName) {

	}

	@Override
	public int edit() {

	}

	@Override
	protected int makeLike(String genDispatcherName) {

	}

}

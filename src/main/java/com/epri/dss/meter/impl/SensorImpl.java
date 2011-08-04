package com.epri.dss.meter.impl;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.meter.Sensor;
import com.epri.dss.meter.SensorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class SensorImpl extends MeterClassImpl implements Sensor {

	private static SensorObj ActiveSensorObj;

	public SensorImpl() {
		super();

		this.Class_Name = "Sensor";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.SENSOR_ELEMENT;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();


		// Define property names

		PropertyName[0] = "element";
		PropertyName[1] = "terminal";
		PropertyName[2] = "kvbase";
		PropertyName[3] = "clear";
		PropertyName[4] = "kVs";
		PropertyName[5] = "currents";
		PropertyName[6] = "kWs";
		PropertyName[7] = "kvars";
		PropertyName[8] = "conn";  //  Sensor connection
		PropertyName[9] = "Deltadirection";  //  +/- 1
		PropertyName[10] = "%Error";  //  %Error of sensor
		PropertyName[11] = "Weight";  // for WLS calc
		PropertyName[12] = "action";

		PropertyHelp[0] = "Name (Full Object name) of element to which the Sensor is connected.";
		PropertyHelp[1] = "Number of the terminal of the circuit element to which the Sensor is connected. "+
				"1 or 2, typically. Default is 1.";
		PropertyHelp[2] = "Voltage base for the sensor, in kV. If connected to a 2- or 3-phase terminal, " + DSSGlobals.CRLF +
				"specify L-L voltage. For 1-phase devices specify L-N or actual 1-phase voltage. "+
				"Like many other DSS devices, default is 12.47kV.";
		PropertyHelp[3] = "{ Yes | No }. Clear=Yes clears sensor values. Should be issued before putting in a new set of measurements.";
		PropertyHelp[4] = "Array of Voltages (kV) measured by the voltage sensor. For Delta-connected " +
				"sensors, Line-Line voltages are expected. For Wye, Line-Neutral are expected.";
		PropertyHelp[5] = "Array of Currents (amps) measured by the current sensor. Specify this or power quantities; not both.";
		PropertyHelp[6] = "Array of Active power (kW) measurements at the sensor. Is converted into Currents along with q=[...]"+DSSGlobals.CRLF+
				"Will override any i=[...] specification.";
		PropertyHelp[7] = "Array of Reactive power (kvar) measurements at the sensor. Is converted into Currents along with p=[...]";
		PropertyHelp[8] = "Voltage sensor Connection: { wye | delta | LN | LL }.  Default is wye. Applies to voltage measurement only. "+DSSGlobals.CRLF+
				"Currents are always assumed to be line currents." + DSSGlobals.CRLF +
				"If wye or LN, voltage is assumed measured line-neutral; otherwise, line-line.";
		PropertyHelp[9] ="{1 or -1}  Default is 1:  1-2, 2-3, 3-1.  For reverse rotation, enter -1. Any positive or negative entry will suffice.";
		PropertyHelp[10] ="Assumed percent error in the measurement. Default is 1.";
		PropertyHelp[11] ="Weighting factor: Default is 1.";
		PropertyHelp[12] ="NOT IMPLEMENTED.Action options: "+DSSGlobals.CRLF+"SQERROR: Show square error of the present value of the monitored terminal  "+DSSGlobals.CRLF+
				"quantity vs the sensor value. Actual values - convert to per unit in calling program.  "+DSSGlobals.CRLF+
				"Value reported in result window/result variable.";

		ActiveProperty = Sensor.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new SensorObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveSensorObj((SensorObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveSensorObj());

		int Result = 0;
		boolean DoRecalcElementData = false;

		SensorObj as = getActiveSensorObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				as.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ as.getName() + "\"", 661);
			case 0:
				as.setElementName(Param.toLowerCase());
			case 1:
				as.setMeteredTerminal(parser.makeInteger());
			case 2:
				as.setkVBase(parser.makeDouble());
			case 3:
				as.setClearSpecified(Utilities.interpretYesNo(Param));
			case 4:
				parser.parseAsVector(as.getNPhases(), as.getSensorVoltage());  // Inits to zero
			case 5:
				parser.parseAsVector(as.getNPhases(), as.getSensorCurrent());  // Inits to zero
			case 6:
				parser.parseAsVector(as.getNPhases(), as.getSensorKW());
			case 7:
				parser.parseAsVector(as.getNPhases(), as.getSensorKVar());
			case 8:
				as.setConn(Utilities.interpretConnection(Param));
			case 9:
				as.setDeltaDirection( as.limitToPlusMinusOne(parser.makeInteger()) );
			case 10:
				as.setPctError(parser.makeDouble());
			case 11:
				as.setWeight(parser.makeDouble());
			case 12:
				as.setAction(Param);  // Put sq error in Global Result
			default:
				// Inherited parameters
				classEdit(ActiveSensorObj, ParamPointer - Sensor.NumPropsThisClass);
			}

			switch (ParamPointer) {
			case 0:
				DoRecalcElementData = true;
				as.setMeteredElementChanged(true);
			case 1:
				DoRecalcElementData = true;
				as.setMeteredElementChanged(true);
			case 2:
				DoRecalcElementData = true;

			/* Do not recalc element data for setting of sensor quantities */
			case 3:
				if (as.isClearSpecified()) as.clearSensor();
			case 4:
				as.setVspecified(true);
			case 5:
				as.setIspecified(true);
			case 6:
				as.setPspecified(true);
			case 7:
				as.setQspecified(true);
			case 8:
				DoRecalcElementData = true;
			case 9:
				DoRecalcElementData = true;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		if (DoRecalcElementData)
			as.recalcElementData();

		return Result;
	}

	/**
	 * Force all sensors in the circuit to reset.
	 */
	@Override
	public void resetAll() {
		for (SensorObj pSensor : DSSGlobals.getInstance().getActiveCircuit().getSensors())
			pSensor.resetIt();
	}

	/**
	 * Force all sensors to take a sample.
	 */
	@Override
	public void sampleAll() {
		for (SensorObj pSensor : DSSGlobals.getInstance().getActiveCircuit().getSensors())
			pSensor.takeSample();
	}

	/**
	 * Force all sensors to save their buffers to disk.
	 */
	@Override
	public void saveAll() {
//		for (SensorObj pSensor : DSSGlobals.getInstance().getActiveCircuit().getSensors())
//			pSensor.save();
	}

	/**
	 * Set the hasSensorObj flag for all cktElement.
	 */
	public void setHasSensorFlag() {
		int i;
		SensorObj ThisSensor;
		CktElement CktElem;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		/* Initialize all to false */
		for (i = 0; i < ckt.getPDElements().size(); i++) {
			CktElem = ckt.getPDElements().get(i);
			CktElem.setHasSensorObj(false);
		}
		for (i = 0; i < ckt.getPCElements().size(); i++) {
			CktElem = ckt.getPCElements().get(i);
			CktElem.setHasSensorObj(false);
		}

		for (i = 0; i < ckt.getSensors().size(); i++) {
			ThisSensor = ckt.getSensors().get(i);
			if (ThisSensor.getMeteredElement() != null) {
				ThisSensor.getMeteredElement().setHasSensorObj(true);
				if (ThisSensor.getMeteredElement() instanceof PCElement) {
					((PCElement) ThisSensor.getMeteredElement()).setSensorObj(ThisSensor);
				} else {
					((PDElement) ThisSensor.getMeteredElement()).setSensorObj(ThisSensor);
				}
			}
		}
	}

	@Override
	protected int makeLike(String SensorName) {
		SensorObj OtherSensor;
		int i, Result = 0;

		/* See if we can find this Sensor name in the present collection */
		OtherSensor = (SensorObj) find(SensorName);
		if (OtherSensor != null) {
			SensorObj as = getActiveSensorObj();

			as.setNPhases(OtherSensor.getNPhases());
			as.setNConds(OtherSensor.getNConds());  // Force reallocation of terminal stuff

			as.setElementName(OtherSensor.getElementName());
			as.setMeteredElement(OtherSensor.getMeteredElement());  // Pointer to target circuit element
			as.setMeteredTerminal(OtherSensor.getMeteredTerminal());

			for (i = 0; i < as.getParentClass().getNumProperties(); i++)
				as.setPropertyValue(i, OtherSensor.getPropertyValue(i));

			as.setBaseFrequency(OtherSensor.getBaseFrequency());
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Sensor makeLike: \"" + SensorName + "\" Not Found.", 662);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		SensorObj pSensor;
		int Result = 0;

		if (Handle >= 0) {
			pSensor = (SensorObj) ElementList.get(Handle);
			pSensor.resetIt();
		} else {  // Do 'em all
			for (int i = 0; i < ElementList.size(); i++) {
				pSensor = (SensorObj) ElementList.get(i);
				pSensor.resetIt();
			}
		}

		return Result;
	}

	public static void setActiveSensorObj(SensorObj activeSensorObj) {
		ActiveSensorObj = activeSensorObj;
	}

	public static SensorObj getActiveSensorObj() {
		return ActiveSensorObj;
	}

}

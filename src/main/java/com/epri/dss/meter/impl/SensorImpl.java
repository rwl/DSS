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

		this.className = "Sensor";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.SENSOR_ELEMENT;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "element";
		propertyName[1] = "terminal";
		propertyName[2] = "kvbase";
		propertyName[3] = "clear";
		propertyName[4] = "kVs";
		propertyName[5] = "currents";
		propertyName[6] = "kWs";
		propertyName[7] = "kvars";
		propertyName[8] = "conn";  // sensor connection
		propertyName[9] = "Deltadirection";  // +/- 1
		propertyName[10] = "%Error";  // %Error of sensor
		propertyName[11] = "Weight";  // for WLS calc
		propertyName[12] = "action";

		propertyHelp[0] = "Name (Full Object name) of element to which the Sensor is connected.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the Sensor is connected. "+
				"1 or 2, typically. Default is 1.";
		propertyHelp[2] = "Voltage base for the sensor, in kV. If connected to a 2- or 3-phase terminal, " + DSSGlobals.CRLF +
				"specify L-L voltage. For 1-phase devices specify L-N or actual 1-phase voltage. "+
				"Like many other DSS devices, default is 12.47kV.";
		propertyHelp[3] = "{ Yes | No }. Clear=Yes clears sensor values. Should be issued before putting in a new set of measurements.";
		propertyHelp[4] = "Array of Voltages (kV) measured by the voltage sensor. For Delta-connected " +
				"sensors, Line-Line voltages are expected. For Wye, Line-Neutral are expected.";
		propertyHelp[5] = "Array of Currents (amps) measured by the current sensor. Specify this or power quantities; not both.";
		propertyHelp[6] = "Array of Active power (kW) measurements at the sensor. Is converted into Currents along with q=[...]"+DSSGlobals.CRLF+
				"Will override any i=[...] specification.";
		propertyHelp[7] = "Array of Reactive power (kvar) measurements at the sensor. Is converted into Currents along with p=[...]";
		propertyHelp[8] = "Voltage sensor Connection: { wye | delta | LN | LL }.  Default is wye. Applies to voltage measurement only. "+DSSGlobals.CRLF+
				"Currents are always assumed to be line currents." + DSSGlobals.CRLF +
				"If wye or LN, voltage is assumed measured line-neutral; otherwise, line-line.";
		propertyHelp[9] ="{1 or -1}  Default is 1:  1-2, 2-3, 3-1.  For reverse rotation, enter -1. Any positive or negative entry will suffice.";
		propertyHelp[10] ="Assumed percent error in the measurement. Default is 1.";
		propertyHelp[11] ="Weighting factor: Default is 1.";
		propertyHelp[12] ="NOT IMPLEMENTED.Action options: "+DSSGlobals.CRLF+"SQERROR: Show square error of the present value of the monitored terminal  "+DSSGlobals.CRLF+
				"quantity vs the sensor value. Actual values - convert to per unit in calling program.  "+DSSGlobals.CRLF+
				"Value reported in result window/result variable.";

		activeProperty = Sensor.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
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
		setActiveSensorObj((SensorObj) elementList.getActive());
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
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				as.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ as.getName() + "\"", 661);
				break;
			case 0:
				as.setElementName(Param.toLowerCase());
				break;
			case 1:
				as.setMeteredTerminal(parser.makeInteger());
				break;
			case 2:
				as.setkVBase(parser.makeDouble());
				break;
			case 3:
				as.setClearSpecified(Utilities.interpretYesNo(Param));
				break;
			case 4:
				parser.parseAsVector(as.getNPhases(), as.getSensorVoltage());  // inits to zero
				break;
			case 5:
				parser.parseAsVector(as.getNPhases(), as.getSensorCurrent());  // inits to zero
				break;
			case 6:
				parser.parseAsVector(as.getNPhases(), as.getSensorKW());
				break;
			case 7:
				parser.parseAsVector(as.getNPhases(), as.getSensorKVar());
				break;
			case 8:
				as.setConn(Utilities.interpretConnection(Param));
				break;
			case 9:
				as.setDeltaDirection( as.limitToPlusMinusOne(parser.makeInteger()) );
				break;
			case 10:
				as.setPctError(parser.makeDouble());
				break;
			case 11:
				as.setWeight(parser.makeDouble());
				break;
			case 12:
				as.setAction(Param);  // put sq error in global result
				break;
			default:
				// inherited parameters
				classEdit(ActiveSensorObj, ParamPointer - Sensor.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			case 0:
				DoRecalcElementData = true;
				as.setMeteredElementChanged(true);
				break;
			case 1:
				DoRecalcElementData = true;
				as.setMeteredElementChanged(true);
				break;
			case 2:
				DoRecalcElementData = true;
				break;

			/* Do not recalc element data for setting of sensor quantities */
			case 3:
				if (as.isClearSpecified()) as.clearSensor();
				break;
			case 4:
				as.setVspecified(true);
				break;
			case 5:
				as.setIspecified(true);
				break;
			case 6:
				as.setPspecified(true);
				break;
			case 7:
				as.setQspecified(true);
				break;
			case 8:
				DoRecalcElementData = true;
				break;
			case 9:
				DoRecalcElementData = true;
				break;
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
	 * Set the hasSensorObj flag for all ckt element.
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

		/* See if we can find this sensor name in the present collection */
		OtherSensor = (SensorObj) find(SensorName);
		if (OtherSensor != null) {
			SensorObj as = getActiveSensorObj();

			as.setNPhases(OtherSensor.getNPhases());
			as.setNConds(OtherSensor.getNConds());  // force reallocation of terminal stuff

			as.setElementName(OtherSensor.getElementName());
			as.setMeteredElement(OtherSensor.getMeteredElement());  // target circuit element
			as.setMeteredTerminal(OtherSensor.getMeteredTerminal());

			for (i = 0; i < as.getParentClass().getNumProperties(); i++)
				as.setPropertyValue(i, OtherSensor.getPropertyValue(i));

			as.setBaseFrequency(OtherSensor.getBaseFrequency());
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Sensor makeLike: \"" + SensorName + "\" not found.", 662);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		SensorObj pSensor;
		int Result = 0;

		if (Handle >= 0) {
			pSensor = (SensorObj) elementList.get(Handle);
			pSensor.resetIt();
		} else {  // Do 'em all
			for (int i = 0; i < elementList.size(); i++) {
				pSensor = (SensorObj) elementList.get(i);
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

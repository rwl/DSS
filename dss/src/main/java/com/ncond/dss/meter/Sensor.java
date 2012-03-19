/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Sensor extends MeterClass {

	public static final int NumPropsThisClass = 13;

	public static SensorObj activeSensorObj;

	public Sensor() {
		super();

		className = "Sensor";
		classType = classType + DSSClassDefs.SENSOR_ELEMENT;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		propertyHelp[2] = "Voltage base for the sensor, in kV. If connected to a 2- or 3-phase terminal, " + DSS.CRLF +
				"specify L-L voltage. For 1-phase devices specify L-N or actual 1-phase voltage. "+
				"Like many other DSS devices, default is 12.47kV.";
		propertyHelp[3] = "{ Yes | No }. Clear=Yes clears sensor values. Should be issued before putting in a new set of measurements.";
		propertyHelp[4] = "Array of Voltages (kV) measured by the voltage sensor. For Delta-connected " +
				"sensors, Line-Line voltages are expected. For Wye, Line-Neutral are expected.";
		propertyHelp[5] = "Array of Currents (amps) measured by the current sensor. Specify this or power quantities; not both.";
		propertyHelp[6] = "Array of Active power (kW) measurements at the sensor. Is converted into Currents along with q=[...]"+DSS.CRLF+
				"Will override any i=[...] specification.";
		propertyHelp[7] = "Array of Reactive power (kvar) measurements at the sensor. Is converted into Currents along with p=[...]";
		propertyHelp[8] = "Voltage sensor Connection: { wye | delta | LN | LL }.  Default is wye. Applies to voltage measurement only. "+DSS.CRLF+
				"Currents are always assumed to be line currents." + DSS.CRLF +
				"If wye or LN, voltage is assumed measured line-neutral; otherwise, line-line.";
		propertyHelp[9] ="{1 or -1}  Default is 1:  1-2, 2-3, 3-1.  For reverse rotation, enter -1. Any positive or negative entry will suffice.";
		propertyHelp[10] ="Assumed percent error in the measurement. Default is 1.";
		propertyHelp[11] ="Weighting factor: Default is 1.";
		propertyHelp[12] ="NOT IMPLEMENTED.Action options: "+DSS.CRLF+"SQERROR: Show square error of the present value of the monitored terminal  "+DSS.CRLF+
				"quantity vs the sensor value. Actual values - convert to per unit in calling program.  "+DSS.CRLF+
				"Value reported in result window/result variable.";

		activeProperty = Sensor.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new SensorObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeSensorObj = (SensorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeSensorObj);

		boolean doRecalcElementData = false;

		SensorObj elem = activeSensorObj;

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
						getClassName() + "." + elem.getName() + "\"", 661);
				break;
			case 0:
				elem.setElementName(param.toLowerCase());
				break;
			case 1:
				elem.setMeteredTerminalIdx(parser.makeInteger() - 1);
				break;
			case 2:
				elem.setKVBase(parser.makeDouble());
				break;
			case 3:
				elem.setClearSpecified(Util.interpretYesNo(param));
				break;
			case 4:
				parser.parseAsVector(elem.getNumPhases(), elem.getSensorVoltage());  // inits to zero
				break;
			case 5:
				parser.parseAsVector(elem.getNumPhases(), elem.getSensorCurrent());  // inits to zero
				break;
			case 6:
				parser.parseAsVector(elem.getNumPhases(), elem.getSensorKW());
				break;
			case 7:
				parser.parseAsVector(elem.getNumPhases(), elem.getSensorKVAr());
				break;
			case 8:
				elem.setConn(Util.interpretConnection(param));
				break;
			case 9:
				elem.setDeltaDirection( elem.limitToPlusMinusOne(parser.makeInteger()) );
				break;
			case 10:
				elem.setPctError(parser.makeDouble());
				break;
			case 11:
				elem.setWeight(parser.makeDouble());
				break;
			case 12:
				elem.setAction(param);  // put sq error in global result
				break;
			default:
				// inherited parameters
				classEdit(activeSensorObj, paramPointer - Sensor.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
			case 1:
				doRecalcElementData = true;
				elem.setMeteredElementChanged(true);
				break;
			case 2:
				doRecalcElementData = true;
				break;

			/* Do not recalc element data for setting of sensor quantities */
			case 3:
				if (elem.isClearSpecified()) elem.clearSensor();
				break;
			case 4:
				elem.setVSpecified(true);
				break;
			case 5:
				elem.setISpecified(true);
				break;
			case 6:
				elem.setPSpecified(true);
				break;
			case 7:
				elem.setQSpecified(true);
				break;
			case 8:
			case 9:
				doRecalcElementData = true;
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (doRecalcElementData) elem.recalcElementData();

		return 0;
	}

	/**
	 * Force all sensors in the circuit to reset.
	 */
	@Override
	public void resetAll() {
		for (SensorObj sensor : DSS.activeCircuit.getSensors())
			sensor.resetIt();
	}

	/**
	 * Force all sensors to take a sample.
	 */
	@Override
	public void sampleAll() {
		for (SensorObj sensor : DSS.activeCircuit.getSensors())
			sensor.takeSample();
	}

	/**
	 * Force all sensors to save their buffers to disk.
	 */
	@Override
	public void saveAll() {
		/*for (SensorObj sensor : DSS.activeCircuit.getSensors())
			sensor.save();*/
	}

	/**
	 * Set the hasSensorObj flag for all ckt element.
	 */
	public void setHasSensorFlag() {

		Circuit ckt = DSS.activeCircuit;

		/* Initialize all to false */
		for (PDElement elem : ckt.getPDElements())
			elem.setHasSensorObj(false);

		for (PCElement elem : ckt.getPCElements())
			elem.setHasSensorObj(false);

		for (SensorObj sensor : ckt.getSensors()) {
			if (sensor.getMeteredElement() != null) {
				sensor.getMeteredElement().setHasSensorObj(true);
				if (sensor.getMeteredElement() instanceof PCElement) {
					((PCElement) sensor.getMeteredElement()).setSensorObj(sensor);
				} else {
					((PDElement) sensor.getMeteredElement()).setSensorObj(sensor);
				}
			}
		}
	}

	@Override
	protected int makeLike(String sensorName) {
		SensorObj other;
		int i, success = 0;

		/* See if we can find this sensor name in the present collection */
		other = (SensorObj) find(sensorName);
		if (other != null) {
			SensorObj elem = activeSensorObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setMeteredElement(other.getMeteredElement());  // target circuit element
			elem.setMeteredTerminalIdx(other.getMeteredTerminalIdx());

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			elem.setBaseFrequency(other.getBaseFrequency());

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Sensor makeLike: \"" + sensorName + "\" not found.", 662);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		SensorObj sensor;

		if (handle >= 0) {
			sensor = (SensorObj) elementList.get(handle);
			sensor.resetIt();
		} else {  // do them all
			for (int i = 0; i < elementList.size(); i++) {
				sensor = (SensorObj) elementList.get(i);
				sensor.resetIt();
			}
		}

		return 0;
	}

}

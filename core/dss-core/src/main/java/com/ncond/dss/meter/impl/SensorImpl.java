package com.ncond.dss.meter.impl;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.meter.Sensor;
import com.ncond.dss.meter.SensorObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CommandListImpl;

public class SensorImpl extends MeterClassImpl implements Sensor {

	public static SensorObj activeSensorObj;

	public SensorImpl() {
		super();

		className = "Sensor";
		classType = classType + DSSClassDefs.SENSOR_ELEMENT;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
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
		DSS.activeCircuit.setActiveCktElement(new SensorObjImpl(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeSensorObj = (SensorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeSensorObj);

		int result = 0;
		boolean doRecalcElementData = false;

		SensorObj as = activeSensorObj;

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
				as.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ as.getName() + "\"", 661);
				break;
			case 0:
				as.setElementName(param.toLowerCase());
				break;
			case 1:
				as.setMeteredTerminal(parser.makeInteger());
				break;
			case 2:
				as.setKVBase(parser.makeDouble());
				break;
			case 3:
				as.setClearSpecified(Util.interpretYesNo(param));
				break;
			case 4:
				parser.parseAsVector(as.getNumPhases(), as.getSensorVoltage());  // inits to zero
				break;
			case 5:
				parser.parseAsVector(as.getNumPhases(), as.getSensorCurrent());  // inits to zero
				break;
			case 6:
				parser.parseAsVector(as.getNumPhases(), as.getSensorKW());
				break;
			case 7:
				parser.parseAsVector(as.getNumPhases(), as.getSensorKVAr());
				break;
			case 8:
				as.setConn(Util.interpretConnection(param));
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
				as.setAction(param);  // put sq error in global result
				break;
			default:
				// inherited parameters
				classEdit(activeSensorObj, paramPointer - Sensor.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
				doRecalcElementData = true;
				as.setMeteredElementChanged(true);
				break;
			case 1:
				doRecalcElementData = true;
				as.setMeteredElementChanged(true);
				break;
			case 2:
				doRecalcElementData = true;
				break;

			/* Do not recalc element data for setting of sensor quantities */
			case 3:
				if (as.isClearSpecified()) as.clearSensor();
				break;
			case 4:
				as.setVSpecified(true);
				break;
			case 5:
				as.setISpecified(true);
				break;
			case 6:
				as.setPSpecified(true);
				break;
			case 7:
				as.setQSpecified(true);
				break;
			case 8:
				doRecalcElementData = true;
				break;
			case 9:
				doRecalcElementData = true;
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (doRecalcElementData)
			as.recalcElementData();

		return result;
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
//		for (SensorObj pSensor : DSSGlobals.activeCircuit.getSensors())
//			pSensor.save();
	}

	/**
	 * Set the hasSensorObj flag for all ckt element.
	 */
	@Override
	public void setHasSensorFlag() {
		int i;
		SensorObj thisSensor;
		CktElement cktElem;

		Circuit ckt = DSS.activeCircuit;

		/* Initialize all to false */
		for (i = 0; i < ckt.getPDElements().size(); i++) {
			cktElem = ckt.getPDElements().get(i);
			cktElem.setHasSensorObj(false);
		}
		for (i = 0; i < ckt.getPCElements().size(); i++) {
			cktElem = ckt.getPCElements().get(i);
			cktElem.setHasSensorObj(false);
		}

		for (i = 0; i < ckt.getSensors().size(); i++) {
			thisSensor = ckt.getSensors().get(i);
			if (thisSensor.getMeteredElement() != null) {
				thisSensor.getMeteredElement().setHasSensorObj(true);
				if (thisSensor.getMeteredElement() instanceof PCElement) {
					((PCElement) thisSensor.getMeteredElement()).setSensorObj(thisSensor);
				} else {
					((PDElement) thisSensor.getMeteredElement()).setSensorObj(thisSensor);
				}
			}
		}
	}

	@Override
	protected int makeLike(String sensorName) {
		SensorObj otherSensor;
		int i, result = 0;

		/* See if we can find this sensor name in the present collection */
		otherSensor = (SensorObj) find(sensorName);
		if (otherSensor != null) {
			SensorObj as = activeSensorObj;

			as.setNumPhases(otherSensor.getNumPhases());
			as.setNumConds(otherSensor.getNumConds());  // force reallocation of terminal stuff

			as.setElementName(otherSensor.getElementName());
			as.setMeteredElement(otherSensor.getMeteredElement());  // target circuit element
			as.setMeteredTerminal(otherSensor.getMeteredTerminal());

			for (i = 0; i < as.getParentClass().getNumProperties(); i++)
				as.setPropertyValue(i, otherSensor.getPropertyValue(i));

			as.setBaseFrequency(otherSensor.getBaseFrequency());
		} else {
			DSS.doSimpleMsg("Error in Sensor makeLike: \"" + sensorName + "\" not found.", 662);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		SensorObj sensor;
		int Result = 0;

		if (handle >= 0) {
			sensor = (SensorObj) elementList.get(handle);
			sensor.resetIt();
		} else {  // do them all
			for (int i = 0; i < elementList.size(); i++) {
				sensor = (SensorObj) elementList.get(i);
				sensor.resetIt();
			}
		}

		return Result;
	}

}

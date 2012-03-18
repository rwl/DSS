/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class GenDispatcher extends ControlClass {

	public static final int NumPropsThisClass = 6;

	public static GenDispatcherObj activeGenDispatcherObj;

	public GenDispatcher() {
		super();

		className = "GenDispatcher";
		classType = classType + DSSClassDefs.GEN_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = GenDispatcher.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "Element";
		propertyName[1] = "Terminal";
		propertyName[2] = "kWLimit";
		propertyName[3] = "kWBand";
		propertyName[4] = "kvarlimit";
		propertyName[5] = "GenList";
		propertyName[6] = "Weights";

		propertyHelp[0] = "Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the GenDispatcher control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		propertyHelp[2] = "kW Limit for the monitored element. The generators are dispatched to hold the power in band.";
		propertyHelp[3] = "Bandwidth (kW) of the dead band around the target limit." +
				"No dispatch changes are attempted if the power in the monitored terminal stays within this band.";
		propertyHelp[4] = "Max kvar to be delivered through the element.  Uses same dead band as kW.";
		propertyHelp[5] = "Array list of generators to be dispatched.  If not specified, all generators in the circuit are assumed dispatchable.";
		propertyHelp[6] = "Array of proportional weights corresponding to each generator in the GenList." +
				" The needed kW to get back to center band is dispatched to each generator according to these weights. " +
				"Default is to set all weights to 1.0.";

		activeProperty = GenDispatcher.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new GenDispatcherObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeGenDispatcherObj = (GenDispatcherObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeGenDispatcherObj);

		GenDispatcherObj elem = activeGenDispatcherObj;

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
						getClassName() + "." + elem.getName() + "\"", 364);
				break;
			case 0:
				elem.setElementName(param.toLowerCase());
				break;
			case 1:
				elem.setElementTerminalIdx(parser.makeInteger() - 1);
				break;
			case 2:
				elem.setKWLimit(parser.makeDouble());
				break;
			case 3:
				elem.setKWBand(parser.makeDouble());
				break;
			case 4:
				elem.setKVArLimit(parser.makeDouble());
				break;
			case 5:
				Util.interpretStringListArray(param, elem.getGeneratorNames());
				break;
			case 6:
				elem.setListSize(elem.getGeneratorNames().size());
				if (elem.getListSize() > 0) {
					elem.setWeights(Util.resizeArray(elem.getWeights(), elem.getListSize()));

					Util.interpretDblArray(param, elem.getListSize(), elem.getWeights());
				}
				break;
			default:
				// inherited parameters
				classEdit(activeGenDispatcherObj, paramPointer - NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 3:
				elem.setHalfKWBand(elem.getKWBand() / 2.0);
				break;
			case 5:  // levelize the list
				elem.getGenerators().clear();  // clear this for resetting on first sample
				elem.setListSize(elem.getGeneratorNames().size());
				elem.setWeights( Util.resizeArray(elem.getWeights(), elem.getListSize()) );
				for (int i = 0; i < elem.getListSize(); i++)
					elem.getWeights()[i] = 1.0;
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		elem.recalcElementData();

		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement GenDispatcher.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String genDispatcherName) {
		/* See if we can find this GenDispatcher name in the present collection */
		GenDispatcherObj otherGenDispatcher = (GenDispatcherObj) find(genDispatcherName);

		if (otherGenDispatcher != null) {
			GenDispatcherObj elem = activeGenDispatcherObj;

			elem.setNumPhases(otherGenDispatcher.getNumPhases());
			elem.setNumConds(otherGenDispatcher.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(otherGenDispatcher.getElementName());
			elem.setControlledElement(otherGenDispatcher.getControlledElement());  // pointer to target circuit element
			elem.setMonitoredElement(otherGenDispatcher.getMonitoredElement());  // pointer to target circuit element

			elem.setElementTerminalIdx(otherGenDispatcher.getElementTerminalIdx());

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, otherGenDispatcher.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in GenDispatcher makeLike: \"" + genDispatcherName + "\" not found.", 370);
		}

		return 0;
	}

}

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
	public int newObject(String ObjName) {

		DSS.activeCircuit.setActiveCktElement(new GenDispatcherObj(this, ObjName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeGenDispatcherObj = (GenDispatcherObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeGenDispatcherObj);

		int result = 0;

		GenDispatcherObj agd = activeGenDispatcherObj;

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
				agd.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getClassName() +"."+ agd.getName() + "\"", 364);
				break;
			case 0:
				agd.setElementName(param.toLowerCase());
				break;
			case 1:
				agd.setElementTerminalIdx(parser.makeInteger() - 1);
				break;
			case 2:
				agd.setKWLimit(parser.makeDouble());
				break;
			case 3:
				agd.setKWBand(parser.makeDouble());
				break;
			case 4:
				agd.setKVArLimit(parser.makeDouble());
				break;
			case 5:
				Util.interpretStringListArray(param, agd.getGeneratorNameList());
				break;
			case 6:
				agd.setListSize(agd.getGeneratorNameList().size());
				if (agd.getListSize() > 0) {
					agd.setWeights( Util.resizeArray(agd.getWeights(), agd.getListSize()) );

					Util.interpretDblArray(param, agd.getListSize(), agd.getWeights());
				}
				break;
			default:
				// inherited parameters
				classEdit(activeGenDispatcherObj, paramPointer - GenDispatcher.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 3:
				agd.setHalfKWBand(agd.getKWBand() / 2.0);
				break;
			case 5:  // levelize the list
				agd.getGenPointerList().clear();  // clear this for resetting on first sample
				agd.setListSize(agd.getGeneratorNameList().size());
				agd.setWeights( Util.resizeArray(agd.getWeights(), agd.getListSize()) );
				for (int i = 0; i < agd.getListSize(); i++)
					agd.getWeights()[i] = 1.0;
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		agd.recalcElementData();

		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement GenDispatcher.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String genDispatcherName) {
		int result = 0;

		/* See if we can find this GenDispatcher name in the present collection */
		GenDispatcherObj otherGenDispatcher = (GenDispatcherObj) find(genDispatcherName);
		if (otherGenDispatcher != null) {
			GenDispatcherObj agd = activeGenDispatcherObj;

			agd.setNumPhases(otherGenDispatcher.getNumPhases());
			agd.setNumConds(otherGenDispatcher.getNumConds());  // force reallocation of terminal stuff

			agd.setElementName(otherGenDispatcher.getElementName());
			agd.setControlledElement(otherGenDispatcher.getControlledElement());  // pointer to target circuit element
			agd.setMonitoredElement(otherGenDispatcher.getMonitoredElement());  // pointer to target circuit element

			agd.setElementTerminalIdx(otherGenDispatcher.getElementTerminalIdx());

			for (int i = 0; i < agd.getParentClass().getNumProperties(); i++)
				agd.setPropertyValue(i, otherGenDispatcher.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in GenDispatcher makeLike: \"" + genDispatcherName + "\" not found.", 370);
		}

		return result;
	}

}

package com.epri.dss.control.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.GenDispatcher;
import com.epri.dss.control.GenDispatcherObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class GenDispatcherImpl extends ControlClassImpl implements GenDispatcher {

	private static GenDispatcherObj ActiveGenDispatcherObj;

	public GenDispatcherImpl() {
		super();

		this.Class_Name = "GenDispatcher";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.GEN_CONTROL;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		this.NumProperties = GenDispatcher.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		// Define property names

		PropertyName[0] = "Element";
		PropertyName[1] = "Terminal";
		PropertyName[2] = "kWLimit";
		PropertyName[3] = "kWBand";
		PropertyName[4] = "kvarlimit";
		PropertyName[5] = "GenList";
		PropertyName[6] = "Weights";

		PropertyHelp[0] = "Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		PropertyHelp[1] = "Number of the terminal of the circuit element to which the GenDispatcher control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		PropertyHelp[2] = "kW Limit for the monitored element. The generators are dispatched to hold the power in band.";
		PropertyHelp[3] = "Bandwidth (kW) of the dead band around the target limit." +
				"No dispatch changes are attempted if the power in the monitored terminal stays within this band.";
		PropertyHelp[4] = "Max kvar to be delivered through the element.  Uses same dead band as kW.";
		PropertyHelp[5] = "Array list of generators to be dispatched.  If not specified, all generators in the circuit are assumed dispatchable.";
		PropertyHelp[6] = "Array of proportional weights corresponding to each generator in the GenList." +
				" The needed kW to get back to center band is dispatched to each generator according to these weights. " +
				"Default is to set all weights to 1.0.";

		ActiveProperty = GenDispatcher.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new GenDispatcherObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveGenDispatcherObj((GenDispatcherObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveGenDispatcherObj());

		int Result = 0;

		GenDispatcherObj agd = getActiveGenDispatcherObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer <= NumProperties))
				agd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ agd.getName() + "\"", 364);
				break;
			case 0:
				agd.setElementName(Param.toLowerCase());
				break;
			case 1:
				agd.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				agd.setkWLimit(parser.makeDouble());
				break;
			case 3:
				agd.setkWBand(parser.makeDouble());
				break;
			case 4:
				agd.setKvarLimit(parser.makeDouble());
				break;
			case 5:
				Utilities.interpretStringListArray(Param, agd.getGeneratorNameList());
				break;
			case 6:
				agd.setListSize(agd.getGeneratorNameList().size());
				if (agd.getListSize() > 0) {
					agd.setWeights( (double[]) Utilities.resizeArray(agd.getWeights(), agd.getListSize()) );

					Utilities.interpretDblArray(Param, agd.getListSize(), agd.getWeights());
				}
				break;
			default:
				// Inherited parameters
				classEdit(ActiveGenDispatcherObj, ParamPointer - GenDispatcher.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			case 3:
				agd.setHalfkWBand(agd.getkWBand() / 2.0);
				break;
			case 5:  // levelize the list
				agd.getGenPointerList().clear();  // clear this for resetting on first sample
				agd.setListSize(agd.getGeneratorNameList().size());
				agd.setWeights( (double[]) Utilities.resizeArray(agd.getWeights(), agd.getListSize()) );
				for (int i = 0; i < agd.getListSize(); i++)
					agd.getWeights()[i] = 1.0;
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		agd.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String GenDispatcherName) {
		int Result = 0;

		/* See if we can find this GenDispatcher name in the present collection */
		GenDispatcherObj OtherGenDispatcher = (GenDispatcherObj) find(GenDispatcherName);
		if (OtherGenDispatcher != null) {
			GenDispatcherObj agd = getActiveGenDispatcherObj();

			agd.setNPhases(OtherGenDispatcher.getNPhases());
			agd.setNConds(OtherGenDispatcher.getNConds());  // Force reallocation of terminal stuff

			agd.setElementName(OtherGenDispatcher.getElementName());
			agd.setControlledElement(OtherGenDispatcher.getControlledElement());  // Pointer to target circuit element
			agd.setMonitoredElement(OtherGenDispatcher.getMonitoredElement());  // Pointer to target circuit element

			agd.setElementTerminal(OtherGenDispatcher.getElementTerminal());

			for (int i = 0; i < agd.getParentClass().getNumProperties(); i++)
				agd.setPropertyValue(i, OtherGenDispatcher.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GenDispatcher makeLike: \"" + GenDispatcherName + "\" Not Found.", 370);
		}

		return Result;
	}

	public static void setActiveGenDispatcherObj(GenDispatcherObj activeGenDispatcherObj) {
		ActiveGenDispatcherObj = activeGenDispatcherObj;
	}

	public static GenDispatcherObj getActiveGenDispatcherObj() {
		return ActiveGenDispatcherObj;
	}

}

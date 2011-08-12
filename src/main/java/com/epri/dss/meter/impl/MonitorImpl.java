package com.epri.dss.meter.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.Monitor;
import com.epri.dss.meter.MonitorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class MonitorImpl extends MeterClassImpl implements Monitor {

	private static MonitorObj ActiveMonitorObj;

	public MonitorImpl() {
		super();

		this.className   = "Monitor";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.MON_ELEMENT;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = Monitor.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "element";
		propertyName[1] = "terminal";
		propertyName[2] = "mode";
		propertyName[3] = "action";    // buffer=clear|save
		propertyName[4] = "residual";  // buffer=clear|save
		propertyName[5] = "VIPolar";   // V I in mag and angle rather then re and im
		propertyName[6] = "PPolar";    // power in power PF rather then power and vars

		propertyHelp[0] = "Name (Full Object name) of element to which the monitor is connected.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the monitor is connected. "+
				"1 or 2, typically. For monitoring states, attach monitor to terminal 1.";
		propertyHelp[2] = "Bitmask integer designating the values the monitor is to capture: "+DSSGlobals.CRLF+
				"0 = Voltages and currents" + DSSGlobals.CRLF+
				"1 = Powers"+DSSGlobals.CRLF+
				"2 = Tap Position (Transformers only)"+DSSGlobals.CRLF+
				"3 = State Variables (PCElements only)" +DSSGlobals.CRLF +DSSGlobals.CRLF+
				"Normally, these would be actual phasor quantities from solution." + DSSGlobals.CRLF+
				"Combine with adders below to achieve other results for terminal quantities:" + DSSGlobals.CRLF+
				"+16 = Sequence quantities" + DSSGlobals.CRLF+
				"+32 = Magnitude only" + DSSGlobals.CRLF+
				"+64 = Positive sequence only or avg of all phases" + DSSGlobals.CRLF+ DSSGlobals.CRLF +
				"Mix adder to obtain desired results. For example:" + DSSGlobals.CRLF+
				"Mode=112 will save positive sequence voltage and current magnitudes only" + DSSGlobals.CRLF+
				"Mode=48 will save all sequence voltages and currents, but magnitude only.";
		propertyHelp[3] = "{Clear | Save | Take}" + DSSGlobals.CRLF +
				"(C)lears or (S)aves current buffer." + DSSGlobals.CRLF +
				"(T)ake action takes a sample."+ DSSGlobals.CRLF + DSSGlobals.CRLF +
				"Note that monitors are automatically reset (cleared) when the Set Mode= command is issued. "+
				"Otherwise, the user must explicitly reset all monitors (reset monitors command) or individual " +
				"monitors with the Clear action.";
		propertyHelp[4] = "{Yes/True | No/False} Default = No.  Include Residual cbannel (sum of all phases) for voltage and current. " +
				"Does not apply to sequence quantity modes or power modes.";
		propertyHelp[5] = "{Yes/True | No/False} Default = YES. Report voltage and current in polar form (Mag/Angle). (default)  Otherwise, it will be real and imaginary.";
		propertyHelp[6] = "{Yes/True | No/False} Default = YES. Report power in Apparent power, S, in polar form (Mag/Angle).(default)  Otherwise, is P and Q";

		activeProperty = Monitor.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new MonitorObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveMonitorObj((MonitorObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveMonitorObj());

		int Result = 0;

		MonitorObj am = getActiveMonitorObj();

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
				am.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ am.getName() + "\"", 661);
				break;
			case 0:
				am.setElementName(Param.toLowerCase());
				break;
			case 1:
				am.setMeteredTerminal(parser.makeInteger());
				break;
			case 2:
				am.setMode(parser.makeInteger());
				break;
			case 3:
				switch (Param.toLowerCase().charAt(0)) {
				case 's':
					am.save();
					break;
				case 'c':
					am.resetIt();
					break;
				case 'r':
					am.resetIt();
					break;
				case 't':
					am.takeSample();
					break;
				}
				break;
			case 4:
				am.setIncludeResidual( Utilities.interpretYesNo(Param) );
				break;
			case 5:
				am.setVIpolar( Utilities.interpretYesNo(Param) );
				break;
			case 6:
				am.setPpolar( Utilities.interpretYesNo(Param) );
				break;
			default:
				// Inherited parameters
				classEdit(getActiveMonitorObj(), ParamPointer - Monitor.NumPropsThisClass);
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		am.recalcElementData();

		return Result;
	}

	/**
	 * Force all monitors in the circuit to reset.
	 */
	@Override
	public void resetAll() {
		for (MonitorObj pMon : DSSGlobals.getInstance().getActiveCircuit().getMonitors())
			if (pMon.isEnabled())
				pMon.resetIt();
	}

	/**
	 * Force all monitors to take a sample.
	 */
	@Override
	public void sampleAll() {
		for (MonitorObj pMon : DSSGlobals.getInstance().getActiveCircuit().getMonitors())
			if (pMon.isEnabled())
				pMon.takeSample();
	}

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	@Override
	public void saveAll() {
		for (MonitorObj pMon : DSSGlobals.getInstance().getActiveCircuit().getMonitors())
			if (pMon.isEnabled())
				pMon.save();
	}

	@Override
	protected int makeLike(String MonitorName) {
		int i, Result = 0;
		/* See if we can find this monitor name in the present collection */
		MonitorObj OtherMonitor = (MonitorObj) find(MonitorName);
		if (OtherMonitor != null) {
			MonitorObj am = getActiveMonitorObj();

			am.setNPhases(OtherMonitor.getNPhases());
			am.setNConds(OtherMonitor.getNConds());  // force reallocation of terminal stuff

			am.setBufferSize(OtherMonitor.getBufferSize());
			am.setElementName(OtherMonitor.getElementName());
			am.setMeteredElement(OtherMonitor.getMeteredElement());  // target circuit element
			am.setMeteredTerminal(OtherMonitor.getMeteredTerminal());
			am.setMode(OtherMonitor.getMode());
			am.setIncludeResidual(OtherMonitor.isIncludeResidual());

			for (i = 0; i < am.getParentClass().getNumProperties(); i++)
				am.setPropertyValue(i, OtherMonitor.getPropertyValue(i));

			am.setBaseFrequency(OtherMonitor.getBaseFrequency());

		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Monitor makeLike: \"" + MonitorName + "\" not found.", 662);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		MonitorObj Mon;
		int Result = 0;

		if (Handle >= 0) {
			Mon = (MonitorObj) elementList.get(Handle);
			Mon.resetIt();
		} else {
			// Do 'em all
			for (int i = 0; i < elementList.size(); i++) {
				Mon = (MonitorObj) elementList.get(i);
				Mon.resetIt();
			}
		}

		return Result;
	}

	public void TOPExport(String ObjName) {
		// FIXME Implement or remove this
		throw new UnsupportedOperationException();
	}

	public static void setActiveMonitorObj(MonitorObj activeMonitorObj) {
		ActiveMonitorObj = activeMonitorObj;
	}

	public static MonitorObj getActiveMonitorObj() {
		return ActiveMonitorObj;
	}

}

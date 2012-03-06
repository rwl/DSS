package com.ncond.dss.meter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Monitor extends MeterClass {

	public static final int SEQUENCEMASK = 16;
	public static final int MAGNITUDEMASK = 32;
	public static final int POSSEQONLYMASK = 64;
	public static final int MODEMASK = 15;

	public static final int NumPropsThisClass = 7;

	public static MonitorObj activeMonitorObj;

	public Monitor() {
		super();

		className   = "Monitor";
		classType = classType + DSSClassDefs.MON_ELEMENT;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		propertyHelp[2] = "Bitmask integer designating the values the monitor is to capture: "+DSS.CRLF+
				"0 = Voltages and currents" + DSS.CRLF+
				"1 = Powers"+DSS.CRLF+
				"2 = Tap Position (Transformers only)"+DSS.CRLF+
				"3 = State Variables (PCElements only)" +DSS.CRLF +DSS.CRLF+
				"Normally, these would be actual phasor quantities from solution." + DSS.CRLF+
				"Combine with adders below to achieve other results for terminal quantities:" + DSS.CRLF+
				"+16 = Sequence quantities" + DSS.CRLF+
				"+32 = Magnitude only" + DSS.CRLF+
				"+64 = Positive sequence only or avg of all phases" + DSS.CRLF+ DSS.CRLF +
				"Mix adder to obtain desired results. For example:" + DSS.CRLF+
				"Mode=112 will save positive sequence voltage and current magnitudes only" + DSS.CRLF+
				"Mode=48 will save all sequence voltages and currents, but magnitude only.";
		propertyHelp[3] = "{Clear | Save | Take}" + DSS.CRLF +
				"(C)lears or (S)aves current buffer." + DSS.CRLF +
				"(T)ake action takes a sample."+ DSS.CRLF + DSS.CRLF +
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
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new MonitorObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeMonitorObj = (MonitorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeMonitorObj);

		int result = 0;

		MonitorObj am = activeMonitorObj;

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
				am.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ am.getName() + "\"", 661);
				break;
			case 0:
				am.setElementName(param.toLowerCase());
				break;
			case 1:
				am.setMeteredTerminal(parser.makeInteger());
				break;
			case 2:
				am.setMode(parser.makeInteger());
				break;
			case 3:
				switch (param.toLowerCase().charAt(0)) {
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
				am.setIncludeResidual( Util.interpretYesNo(param) );
				break;
			case 5:
				am.setVIPolar( Util.interpretYesNo(param) );
				break;
			case 6:
				am.setPPolar( Util.interpretYesNo(param) );
				break;
			default:
				// Inherited parameters
				classEdit(activeMonitorObj, paramPointer - Monitor.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		am.recalcElementData();

		return result;
	}

	/**
	 * Force all monitors in the circuit to reset.
	 */
	@Override
	public void resetAll() {
		for (MonitorObj pMon : DSS.activeCircuit.getMonitors())
			if (pMon.isEnabled())
				pMon.resetIt();
	}

	/**
	 * Force all monitors to take a sample.
	 */
	@Override
	public void sampleAll() {
		for (MonitorObj pMon : DSS.activeCircuit.getMonitors())
			if (pMon.isEnabled())
				pMon.takeSample();
	}

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	@Override
	public void saveAll() {
		for (MonitorObj pMon : DSS.activeCircuit.getMonitors())
			if (pMon.isEnabled())
				pMon.save();
	}

	@Override
	protected int makeLike(String MonitorName) {
		int i, result = 0;
		/* See if we can find this monitor name in the present collection */
		MonitorObj otherMonitor = (MonitorObj) find(MonitorName);
		if (otherMonitor != null) {
			MonitorObj am = activeMonitorObj;

			am.setNumPhases(otherMonitor.getNumPhases());
			am.setNumConds(otherMonitor.getNumConds());  // force reallocation of terminal stuff

			am.setBufferSize(otherMonitor.getBufferSize());
			am.setElementName(otherMonitor.getElementName());
			am.setMeteredElement(otherMonitor.getMeteredElement());  // target circuit element
			am.setMeteredTerminal(otherMonitor.getMeteredTerminal());
			am.setMode(otherMonitor.getMode());
			am.setIncludeResidual(otherMonitor.isIncludeResidual());

			for (i = 0; i < am.getParentClass().getNumProperties(); i++)
				am.setPropertyValue(i, otherMonitor.getPropertyValue(i));

			am.setBaseFrequency(otherMonitor.getBaseFrequency());

		} else {
			DSS.doSimpleMsg("Error in Monitor makeLike: \"" + MonitorName + "\" not found.", 662);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		MonitorObj mon;
		int result = 0;

		if (handle >= 0) {
			mon = (MonitorObj) elementList.get(handle);
			mon.resetIt();
		} else {
			// do them all
			for (int i = 0; i < elementList.size(); i++) {
				mon = (MonitorObj) elementList.get(i);
				mon.resetIt();
			}
		}

		return result;
	}

	public void TOPExport(String objName) {
		// FIXME Implement or remove this
		throw new UnsupportedOperationException();
	}

}

package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;

public class SwtControlObj extends ControlElem {

	private ControlAction presentState;
	private boolean locked;

	public SwtControlObj(DSSClass parClass, String swtControlName) {
		super(parClass);

		setName(swtControlName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		elementName   = "";
		setControlledElement(null);
		elementTerminal = 0;
		presentState  = ControlAction.CLOSE;
		locked        = false;
		timeDelay     = 120.0;

		initPropertyValues(0);
	}

	public void recalcElementData() {

		int devIndex = Util.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			setControlledElement(DSS.activeCircuit.getCktElements().get(devIndex));
			setNumPhases( getControlledElement().getNumPhases() );
			setNumConds(nPhases);
			getControlledElement().setActiveTerminalIdx(elementTerminal);
			if (!locked) {
				switch (presentState) {
				case OPEN:
					getControlledElement().setConductorClosed(0, false);
					break;
				case CLOSE:
					getControlledElement().setConductorClosed(0, true);
					break;
				}
			}
			// attach controller bus to the switch bus - no space allocated for monitored variables
			setBus(0, getControlledElement().getBus(elementTerminal));
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("SwtControl: \"" + getName() + "\"", "CktElement Element \""+ elementName + "\" not found.",
					" Element must be defined previously.", 387);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setNumPhases( getControlledElement().getNumPhases() );
			setNumConds(nPhases);
			setBus(0, getControlledElement().getBus(elementTerminal));
		}
		super.makePosSequence();
	}

	public void calcYPrim() {
		// leave YPrims as nil
	}

	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	public void doPendingAction(int code, int proxyHdl) {
		if (!locked) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);
			if (code == ControlAction.OPEN.code() && presentState == ControlAction.CLOSE) {
				getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
				Util.appendToEventLog("SwtControl."+getName(), "Opened");
			}
			if (code == ControlAction.CLOSE.code() && presentState == ControlAction.OPEN) {
				getControlledElement().setConductorClosed(-1, true);  // close all phases of active terminal
				Util.appendToEventLog("SwtControl."+getName(), "Closed");
			}
		}
	}

	// FIXME Private method in OpenDSS
	public void interpretSwitchAction(String action) {
		if (!locked) {
			switch (action.toLowerCase().charAt(0)) {
			case 'o':
				presentState = ControlAction.OPEN;
				break;
			case 'c':
				presentState = ControlAction.CLOSE;
				break;
			}

			if (getControlledElement() != null) {
				getControlledElement().setActiveTerminalIdx(elementTerminal);
				switch (presentState) {
				case OPEN:
					getControlledElement().setConductorClosed(-1, false);
					break;
				case CLOSE:
					getControlledElement().setConductorClosed(-1, true);
					break;
				}
			}
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void sample() {
		getControlledElement().setActiveTerminalIdx(elementTerminal);
		if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
			presentState = ControlAction.CLOSE;
		} else {
			presentState = ControlAction.OPEN;
		}
	}

	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) +
				"=" + getPropertyValue(getParentClass().getPropertyIdxMap(i)));

		if (complete) pw.println();

		pw.close();
	}

	public String getPropertyValue(int index) {
		return super.getPropertyValue(index);
	}

	/**
	 * Reset to initial defined state.
	 */
	public void reset() {
		presentState = ControlAction.CLOSE;
		locked       = false;
		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			getControlledElement().setConductorClosed(-1, true);  // close all phases of active terminal
		}
	}

	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");   // 'element';
		setPropertyValue(1, "1");  // 'terminal';
		setPropertyValue(2, "c");
		setPropertyValue(3, "n");
		setPropertyValue(4, "120.0");
		super.initPropertyValues(SwtControl.NumPropsThisClass - 1);
	}

	public ControlAction getPresentState() {
		return presentState;
	}

	public boolean isLocked() {
		return locked;
	}

	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private members in OpenDSS

	public void setPresentState(ControlAction state) {
		presentState = state;
	}

	public void setLocked(boolean value) {
		locked = value;
	}

}

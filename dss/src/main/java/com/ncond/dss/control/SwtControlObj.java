/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;

import static com.ncond.dss.common.Util.appendToEventLog;
import static com.ncond.dss.common.Util.getCktElementIndex;


public class SwtControlObj extends ControlElem {

	private ControlAction presentState;
	private boolean locked;

	public SwtControlObj(DSSClass parClass, String swtControlName) {
		super(parClass);

		setName(swtControlName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminalIdx = 0;
		presentState = ControlAction.CLOSE;
		locked = false;
		timeDelay = 120.0;

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		int devIndex = getCktElementIndex(elementName);

		if (devIndex >= 0) {
			setControlledElement(DSS.activeCircuit.getCktElements().get(devIndex));
			setNumPhases(getControlledElement().getNumPhases());
			setNumConds(nPhases);
			getControlledElement().setActiveTerminalIdx(elementTerminalIdx);
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
			setBus(0, getControlledElement().getBus(elementTerminalIdx));
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("SwtControl: \"" + getName() + "\"",
					"CktElement Element \"" + elementName + "\" not found.",
					"Element must be defined previously.", 387);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setNumPhases(getControlledElement().getNumPhases());
			setNumConds(nPhases);
			setBus(0, getControlledElement().getBus(elementTerminalIdx));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as nil
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int code, int proxyHdl) {
		if (!locked) {
			getControlledElement().setActiveTerminalIdx(elementTerminalIdx);

			if (code == ControlAction.OPEN.code() && presentState == ControlAction.CLOSE) {
				getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
				appendToEventLog("SwtControl."+getName(), "Opened");
			}
			if (code == ControlAction.CLOSE.code() && presentState == ControlAction.OPEN) {
				getControlledElement().setConductorClosed(-1, true);  // close all phases of active terminal
				appendToEventLog("SwtControl."+getName(), "Closed");
			}
		}
	}

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
				getControlledElement().setActiveTerminalIdx(elementTerminalIdx);
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
	@Override
	public void sample() {
		getControlledElement().setActiveTerminalIdx(elementTerminalIdx);
		if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
			presentState = ControlAction.CLOSE;
		} else {
			presentState = ControlAction.OPEN;
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			pw.println("~ " + getParentClass().getPropertyName(i) +
				"=" + getPropertyValue(getParentClass().getPropertyIdxMap(i)));
		}

		if (complete) pw.println();

		pw.close();
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		presentState = ControlAction.CLOSE;
		locked = false;
		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminalIdx);  // set active terminal
			getControlledElement().setConductorClosed(-1, true);  // close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");   // 'element';
		setPropertyValue(1, "1");  // 'terminal';
		setPropertyValue(2, "c");
		setPropertyValue(3, "n");
		setPropertyValue(4, "120.0");
		super.initPropertyValues(SwtControl.NumPropsThisClass - 1);
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public ControlAction getPresentState() {
		return presentState;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setPresentState(ControlAction presentState) {
		this.presentState = presentState;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}

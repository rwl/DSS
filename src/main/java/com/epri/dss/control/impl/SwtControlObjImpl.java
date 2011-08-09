package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.SwtControl;
import com.epri.dss.control.SwtControlObj;

public class SwtControlObjImpl extends ControlElemImpl implements SwtControlObj {

	private ControlAction PresentState;
	private boolean Locked;

	public SwtControlObjImpl(DSSClassImpl ParClass, String SwtControlName) {
		super(ParClass);

		setName(SwtControlName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		setNConds(3);
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		this.ElementName   = "";
		setControlledElement(null);
		this.ElementTerminal = 1;
		this.PresentState  = ControlAction.CLOSE;
		this.Locked        = false;
		this.TimeDelay     = 120.0;

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int DevIndex = Utilities.getCktElementIndex(ElementName);
		if (DevIndex >= 0) {
			setControlledElement(Globals.getActiveCircuit().getCktElements().get(DevIndex));
			setNPhases( getControlledElement().getNPhases() );
			setNConds(nPhases);
			getControlledElement().setActiveTerminalIdx(ElementTerminal);
			if (!Locked) {
				switch (PresentState) {
				case OPEN:
					getControlledElement().setConductorClosed(0, false);
					break;
				case CLOSE:
					getControlledElement().setConductorClosed(0, true);
					break;
				}
			}
			// Attach controller bus to the switch bus - no space allocated for monitored variables
			setBus (1, getControlledElement().getBus(ElementTerminal));
		} else {
			setControlledElement(null);  // element not found
			Globals.doErrorMsg("SwtControl: \"" + getName() + "\"", "CktElement Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 387);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setNPhases( getControlledElement().getNPhases() );
			setNConds(nPhases);
			setBus(1, getControlledElement().getBus(ElementTerminal));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// Leave YPrims as nil.
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {
		if (!Locked) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);
			if ((Code == ControlAction.OPEN.code()) && (PresentState == ControlAction.CLOSE)) {
				getControlledElement().setConductorClosed(0, false);  // Open all phases of active terminal
				Utilities.appendToEventLog("SwtControl."+getName(), "Opened");
			}
			if ((Code == ControlAction.CLOSE.code()) && (PresentState == ControlAction.OPEN)) {
				getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
				Utilities.appendToEventLog("SwtControl."+getName(), "Closed");
			}
		}
	}

	// FIXME Private method in OpenDSS
	public void interpretSwitchAction(String Action) {
		if (!Locked) {
			switch (Action.toLowerCase().charAt(0)) {
			case 'o':
				PresentState = ControlAction.OPEN;
				break;
			case 'c':
				PresentState = ControlAction.CLOSE;
				break;
			}

			if (getControlledElement() != null) {
				getControlledElement().setActiveTerminalIdx(ElementTerminal);
				switch (PresentState) {
				case OPEN:
					getControlledElement().setConductorClosed(0, false);
					break;
				case CLOSE:
					getControlledElement().setConductorClosed(0, true);
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
		getControlledElement().setActiveTerminalIdx(ElementTerminal);
		if (getControlledElement().getConductorClosed(0)) {  // Check state of phases of active terminal
			PresentState = ControlAction.CLOSE;
		} else {
			PresentState = ControlAction.OPEN;
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);
		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(getParentClass().getPropertyIdxMap()[i]));
		if (Complete)
			F.println();
	}

	@Override
	public String getPropertyValue(int Index) {
		return super.getPropertyValue(Index);
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		PresentState = ControlAction.CLOSE;
		Locked       = false;
		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal
			getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(0, "");   // 'element';
		setPropertyValue(1, "1");  // 'terminal';
		setPropertyValue(2, "c");
		setPropertyValue(3, "n");
		setPropertyValue(4, "120.0");
		super.initPropertyValues(SwtControl.NumPropsThisClass);
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public boolean isLocked() {
		return Locked;
	}

	// FIXME Private members in OpenDSS

	public void setPresentState(ControlAction presentState) {
		PresentState = presentState;
	}

	public void setLocked(boolean locked) {
		Locked = locked;
	}

}

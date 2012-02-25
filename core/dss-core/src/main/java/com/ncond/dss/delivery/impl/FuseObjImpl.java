package com.ncond.dss.delivery.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.impl.DSSCktElement;
import com.ncond.dss.common.impl.DSSClassImpl;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.control.impl.ControlAction;
import com.ncond.dss.control.impl.ControlElemImpl;
import com.ncond.dss.delivery.Fuse;
import com.ncond.dss.delivery.FuseObj;
import com.ncond.dss.general.TCC_CurveObj;

public class FuseObjImpl extends ControlElemImpl implements FuseObj {

	private TCC_CurveObj fuseCurve;

	private double ratedCurrent;

	private double delayTime;

	private String monitoredElementName;
	private int monitoredElementTerminal;
	private DSSCktElement monitoredElement;

	/* Handle to control queue actions */
	private int[] hAction = new int[Fuse.FUSEMAXDIM];
	/* 0 = open 1 = close */
	private ControlAction[] presentState = new ControlAction[Fuse.FUSEMAXDIM];
	private boolean[] readyToBlow = new boolean[Fuse.FUSEMAXDIM];

	/* Offset for monitored terminal */
	private int condOffset;
	private Complex[] cBuffer;

	public FuseObjImpl(DSSClassImpl parClass, String fuseName) {
		super(parClass);

		int i;

		setName(fuseName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);  // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminal = 0;

		monitoredElementName = "";
		monitoredElementTerminal = 0;
		monitoredElement = null;

		fuseCurve = FuseImpl.getTccCurve("tlink");

		ratedCurrent = 1.0;

		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			presentState[i] = ControlAction.CLOSE;
		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			readyToBlow[i] = false;
		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			hAction[i] = 0;

		cBuffer = null;  // complex buffer

		objType = parClass.getDSSClassType(); //CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		int i;

		int devIndex = Utilities.getCktElementIndex(monitoredElementName);
		if (devIndex >= 0) {
			monitoredElement = (DSSCktElement) DSSGlobals.activeCircuit.getCktElements().get(devIndex);
			setNPhases( monitoredElement.getNPhases() );  // force number of phases to be same
			if (getNPhases() > Fuse.FUSEMAXDIM)
				DSSGlobals.doSimpleMsg("Warning: Fuse "+getName()+": Number of phases > max fuse dimension.", 404);
			if (monitoredElementTerminal >= monitoredElement.getNTerms()) {
				DSSGlobals.doErrorMsg("Fuse: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 404);
			} else {
				// sets name of i-th terminal's connected bus in fuse's bus list
				setBus(0, monitoredElement.getBus(monitoredElementTerminal));
				// allocate a buffer big enough to hold everything from the monitored element
				cBuffer = Utilities.resizeArray(cBuffer, monitoredElement.getYorder());
				condOffset = monitoredElementTerminal * monitoredElement.getNConds();  // for speedy sampling
			}
		}

		/* Check for existence of controlled element */

		devIndex = Utilities.getCktElementIndex(elementName);
		if (devIndex >= 0) {  // both CktElement and monitored element must already exist
			setControlledBus( (Bus) DSSGlobals.activeCircuit.getCktElements().get(devIndex) );
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // make the 1st terminal active

			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++) {
				if (getControlledElement().getConductorClosed(i)) {  // check state of i-th phase of active terminal
					presentState[i] = ControlAction.CLOSE;
				} else {
					presentState[i] = ControlAction.OPEN;
				}
			}
			for (i = 0; i < getControlledElement().getNPhases(); i++)
				hAction[i] = 0;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				readyToBlow[i] = false;
		} else {
			setControlledElement(null);  // element not found
			DSSGlobals.doErrorMsg("Fuse: \"" + getName() + "\"", "CktElement Element \""+ elementName + "\" not found.",
					" Element must be defined previously.", 405);
		}
	}

	/**
	 * Always zero for a fuse.
	 */
	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	/**
	 * Get present value of terminal current.
	 */
	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < getNConds(); i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Returns injection currents.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < getNConds(); i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Do the action that is pending from last sample.
	 *
	 * Theoretically, there shouldn't be anything on the queue unless we have to do something.
	 *
	 * Only legal action is to open one phase.
	 */
	@Override
	public void doPendingAction(int phs, int proxyHdl) {
		if (phs <= Fuse.FUSEMAXDIM) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal of CktElement to terminal 1
			switch (presentState[phs]) {
			case CLOSE:
				if (readyToBlow[phs]) {  // ignore if we became disarmed in meantime
					getControlledElement().setConductorClosed(phs, false);  // open all phases of active terminal
					Utilities.appendToEventLog("Fuse." + getName(), "Phase "+String.valueOf(phs)+" Blown");
					hAction[phs] = 0;
				}
				break;
			default:
				// do nothing
				break;
			}
		}
	}

	public void interpretFuseAction(String action) {
		if (getControlElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			switch (action.toLowerCase().charAt(0)) {
			case 'o':
				getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal   TODO Check zero based indexing
				break;
			case 't':
				getControlledElement().setConductorClosed(0, false);
				break;
			case 'c':
				getControlledElement().setConductorClosed(0, true);  // close all phases of active terminal
				break;
			}
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		double CMag;
		double tripTime;

		getControlledElement().setActiveTerminalIdx(elementTerminal);
		monitoredElement.getCurrents(cBuffer);

		for (int i = 0; i < Math.min(Fuse.FUSEMAXDIM, monitoredElement.getNPhases()); i++) {
			if (getControlledElement().getConductorClosed(i)) {  // check state of phases of active terminal
				presentState[i] = ControlAction.CLOSE;
			} else {
				presentState[i] = ControlAction.OPEN;
			}

			if (presentState[i] == ControlAction.CLOSE) {
				tripTime = -1.0;

				/* Check phase trip, if any */

				if (fuseCurve != null) {
					CMag = cBuffer[i].abs();
					tripTime = fuseCurve.getTCCTime(CMag / ratedCurrent);
				}

				if (tripTime > 0.0) {
					if (!readyToBlow[i]) {
						Circuit ckt = DSSGlobals.activeCircuit;
						// then arm for an open operation
						hAction[i] = ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + tripTime + delayTime, i, 0, this);
						readyToBlow[i] = true;
					}
				} else {
					if (readyToBlow[i]) {
						// current has dropped below pickup and it hasn't blown yet
						DSSGlobals.activeCircuit.getControlQueue().delete(hAction[i]);  // delete the fuse blow action
						readyToBlow[i] = false;
					}
				}
			}
		}
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete)
			f.println();
	}

	@Override
	public String getPropertyValue(int index) {
		return super.getPropertyValue(index);
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		int i;

		if (getControlledElement() != null) {
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				presentState[i] = ControlAction.CLOSE;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				readyToBlow[i] = false;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				hAction[i] = 0;
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			getControlledElement().setConductorClosed(0, true);            // close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");   // "element";
		setPropertyValue(1, "1");  // "terminal";
		setPropertyValue(2, "");
		setPropertyValue(3, "1");  // "terminal";
		setPropertyValue(4, "Tlink");
		setPropertyValue(5, "1.0");
		setPropertyValue(6, "0");
		setPropertyValue(7, "");

		super.initPropertyValues(Fuse.NumPropsThisClass - 1);
	}

	// FIXME: Private members in OpenDSS

	public TCC_CurveObj getFuseCurve() {
		return fuseCurve;
	}

	public void setFuseCurve(TCC_CurveObj curve) {
		fuseCurve = curve;
	}

	public double getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(double current) {
		ratedCurrent = current;
	}

	public double getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(double time) {
		delayTime = time;
	}

	public String getMonitoredElementName() {
		return monitoredElementName;
	}

	public void setMonitoredElementName(String name) {
		monitoredElementName = name;
	}

	public int getMonitoredElementTerminal() {
		return monitoredElementTerminal;
	}

	public void setMonitoredElementTerminal(int terminal) {
		monitoredElementTerminal = terminal;
	}

	public DSSCktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setMonitoredElement(DSSCktElement element) {
		monitoredElement = element;
	}

	public int[] getHAction() {
		return hAction;
	}

	public void setHAction(int[] action) {
		this.hAction = action;
	}

	public ControlAction[] getPresentState() {
		return presentState;
	}

	public void setPresentState(ControlAction[] state) {
		presentState = state;
	}

	public boolean[] getReadyToBlow() {
		return readyToBlow;
	}

	public void setReadyToBlow(boolean[] ready) {
		readyToBlow = ready;
	}

	public int getCondOffset() {
		return condOffset;
	}

	public void setCondOffset(int offset) {
		condOffset = offset;
	}

	public Complex[] getCBuffer() {
		return cBuffer;
	}

	public void setCBuffer(Complex[] buffer) {
		cBuffer = buffer;
	}

}

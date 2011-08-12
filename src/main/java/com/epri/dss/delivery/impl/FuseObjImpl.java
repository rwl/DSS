package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.impl.ControlAction;
import com.epri.dss.control.impl.ControlElemImpl;
import com.epri.dss.delivery.Fuse;
import com.epri.dss.delivery.FuseObj;
import com.epri.dss.general.TCC_CurveObj;

public class FuseObjImpl extends ControlElemImpl implements FuseObj {

	private TCC_CurveObj FuseCurve;

	private double RatedCurrent;

	private double DelayTime;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private DSSCktElement MonitoredElement;

	/* Handle to control queue actions */
	private int[] hAction = new int[Fuse.FUSEMAXDIM];
	/* 0 = open 1 = close */
	private ControlAction[] PresentState = new ControlAction[Fuse.FUSEMAXDIM];
	private boolean[] ReadyToBlow = new boolean[Fuse.FUSEMAXDIM];

	/* Offset for monitored terminal */
	private int CondOffset;
	private Complex[] cBuffer;

	public FuseObjImpl(DSSClassImpl ParClass, String FuseName) {
		super(ParClass);

		int i;

		setName(FuseName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(1);  // this forces allocation of terminals and conductors in base class

		this.elementName = "";
		setControlledElement(null);
		this.elementTerminal = 1;

		this.MonitoredElementName = "";
		this.MonitoredElementTerminal = 1;
		this.MonitoredElement = null;

		this.FuseCurve = FuseImpl.getTccCurve("tlink");

		this.RatedCurrent = 1.0;

		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			this.PresentState[i] = ControlAction.CLOSE;
		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			this.ReadyToBlow[i] = false;
		for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getNPhases()); i++)
			this.hAction[i] = 0;

		this.cBuffer = null;  // complex buffer

		this.DSSObjType = ParClass.getDSSClassType(); //CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		int i;

		int DevIndex = Utilities.getCktElementIndex(MonitoredElementName);
		if (DevIndex >= 0) {
			MonitoredElement = (DSSCktElement) Globals.getActiveCircuit().getCktElements().get(DevIndex);
			setNPhases( MonitoredElement.getNPhases() );  // force number of phases to be same
			if (getNPhases() > Fuse.FUSEMAXDIM)
				Globals.doSimpleMsg("Warning: Fuse "+getName()+": Number of phases > max fuse dimension.", 404);
			if (MonitoredElementTerminal > MonitoredElement.getNTerms()) {
				Globals.doErrorMsg("Fuse: \"" + getName() + "\"",
										"Terminal no. \"" +"\" does not exist.",
										"Re-specify terminal no.", 404);
			} else {
				// sets name of i-th terminal's connected bus in fuse's bus list
				setBus(1, MonitoredElement.getBus(MonitoredElementTerminal));  // TODO Check zero based indexing
				// allocate a buffer big enough to hold everything from the monitored element
				cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
				CondOffset = (MonitoredElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
			}
		}

		/* Check for existence of controlled element */

		DevIndex = Utilities.getCktElementIndex(elementName);
		if (DevIndex >= 0) {  // both CktElement and monitored element must already exist
			setControlledBus( (Bus) Globals.getActiveCircuit().getCktElements().get(DevIndex) );
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // make the 1st terminal active

			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++) {
				if (getControlledElement().getConductorClosed(i)) {  // check state of i-th phase of active terminal
					PresentState[i] = ControlAction.CLOSE;
				} else {
					PresentState[i] = ControlAction.OPEN;
				}
			}
			for (i = 0; i < getControlledElement().getNPhases(); i++)
				hAction[i] = 0;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				ReadyToBlow[i] = false;
		} else {
			setControlledElement(null);  // element not found
			Globals.doErrorMsg("Fuse: \"" + getName() + "\"", "CktElement Element \""+ elementName + "\" not found.",
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
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < getNConds(); i++)
			Curr[i] = Complex.ZERO;
	}

	/**
	 * Returns injection currents.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {
		for (int i = 0; i < getNConds(); i++)
			Curr[i] = Complex.ZERO;
	}

	/**
	 * Do the action that is pending from last sample.
	 *
	 * Theoretically, there shouldn't be anything on the queue unless we have to do something.
	 *
	 * Only legal action is to open one phase.
	 */
	@Override
	public void doPendingAction(int Phs, int ProxyHdl) {
		if (Phs <= Fuse.FUSEMAXDIM) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal of CktElement to terminal 1
			switch (PresentState[Phs]) {
			case CLOSE:
				if (ReadyToBlow[Phs]) {  // ignore if we became disarmed in meantime
					getControlledElement().setConductorClosed(Phs, false);  // open all phases of active terminal
					Utilities.appendToEventLog("Fuse." + getName(), "Phase "+String.valueOf(Phs)+" Blown");
					hAction[Phs] = 0;
				}
				break;
			default:
				// do nothing
				break;
			}
		}
	}

	public void interpretFuseAction(String Action) {
		if (getControlElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			switch (Action.toLowerCase().charAt(0)) {
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
		double Cmag;
		double TripTime;

		getControlledElement().setActiveTerminalIdx(elementTerminal);
		MonitoredElement.getCurrents(cBuffer);

		for (int i = 0; i < Math.min(Fuse.FUSEMAXDIM, MonitoredElement.getNPhases()); i++) {
			if (getControlledElement().getConductorClosed(i)) {  // check state of phases of active terminal
				PresentState[i] = ControlAction.CLOSE;
			} else {
				PresentState[i] = ControlAction.OPEN;
			}

			if (PresentState[i] == ControlAction.CLOSE) {
				TripTime = -1.0;

				/* Check phase trip, if any */

				if (FuseCurve != null) {
					Cmag     = cBuffer[i].abs();
					TripTime = FuseCurve.getTCCTime(Cmag / RatedCurrent);
				}

				if (TripTime > 0.0) {
					if (!ReadyToBlow[i]) {
						Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
						// then arm for an open operation
						hAction[i] = ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + TripTime + DelayTime, i, 0, this);
						ReadyToBlow[i] = true;
					}
				} else {
					if (ReadyToBlow[i]) {
						// current has dropped below pickup and it hasn't blown yet
						DSSGlobals.getInstance().getActiveCircuit().getControlQueue().delete(hAction[i]);  // delete the fuse blow action
						ReadyToBlow[i] = false;
					}
				}
			}
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

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
		int i;

		if (getControlledElement() != null) {
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				PresentState[i] = ControlAction.CLOSE;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				ReadyToBlow[i] = false;
			for (i = 0; i < Math.min(Fuse.FUSEMAXDIM, getControlledElement().getNPhases()); i++)
				hAction[i] = 0;
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			getControlledElement().setConductorClosed(0, true);            // close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[1]  = "";  // "element";
		PropertyValue[2]  = "1"; // "terminal";
		PropertyValue[3]  = "";
		PropertyValue[4]  = "1"; // "terminal";
		PropertyValue[5]  = "Tlink";
		PropertyValue[6]  = "1.0";
		PropertyValue[7]  = "0";
		PropertyValue[8]  = "";

		super.initPropertyValues(Fuse.NumPropsThisClass);
	}

	// FIXME: Private members in OpenDSS

	public TCC_CurveObj getFuseCurve() {
		return FuseCurve;
	}

	public void setFuseCurve(TCC_CurveObj fuseCurve) {
		FuseCurve = fuseCurve;
	}

	public double getRatedCurrent() {
		return RatedCurrent;
	}

	public void setRatedCurrent(double ratedCurrent) {
		RatedCurrent = ratedCurrent;
	}

	public double getDelayTime() {
		return DelayTime;
	}

	public void setDelayTime(double delayTime) {
		DelayTime = delayTime;
	}

	public String getMonitoredElementName() {
		return MonitoredElementName;
	}

	public void setMonitoredElementName(String monitoredElementName) {
		MonitoredElementName = monitoredElementName;
	}

	public int getMonitoredElementTerminal() {
		return MonitoredElementTerminal;
	}

	public void setMonitoredElementTerminal(int monitoredElementTerminal) {
		MonitoredElementTerminal = monitoredElementTerminal;
	}

	public DSSCktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(DSSCktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public int[] gethAction() {
		return hAction;
	}

	public void sethAction(int[] hAction) {
		this.hAction = hAction;
	}

	public ControlAction[] getPresentState() {
		return PresentState;
	}

	public void setPresentState(ControlAction[] presentState) {
		PresentState = presentState;
	}

	public boolean[] getReadyToBlow() {
		return ReadyToBlow;
	}

	public void setReadyToBlow(boolean[] readyToBlow) {
		ReadyToBlow = readyToBlow;
	}

	public int getCondOffset() {
		return CondOffset;
	}

	public void setCondOffset(int condOffset) {
		CondOffset = condOffset;
	}

	public Complex[] getcBuffer() {
		return cBuffer;
	}

	public void setcBuffer(Complex[] cBuffer) {
		this.cBuffer = cBuffer;
	}

}

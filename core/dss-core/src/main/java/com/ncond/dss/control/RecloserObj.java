package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.TCC_CurveObj;

/**
 * A control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 *
 * The control is usually placed in the terminal of a line or transformer, but
 * it could be any element.
 *
 * CktElement to be controlled must already exist.
 */
public class RecloserObj extends ControlElem {

	private TCC_CurveObj phaseDelayed,
		groundDelayed,
		phaseFast,
		groundFast;

	private double phaseTrip,
		groundTrip,
		phaseInst,
		groundInst;

	private double[] recloseIntervals;
	private int numFast, numReclose;
	private double resetTime,
		delayTime,
		TDGrDelayed,
		TDPhDelayed,
		TDGrFast,
		TDPhFast;

	private String monitoredElementName;
	private int monitoredElementTerminal;
	private CktElement monitoredElement;

	private ControlAction presentState;

	private int operationCount;

	private boolean lockedOut,
		armedForClose, armedForOpen, groundTarget, phaseTarget;

	private int condOffset;  // offset for monitored terminal

	private Complex[] cBuffer;

	public RecloserObj(DSSClass parClass, String recloserName) {
		super(parClass);

		setName(recloserName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminal = 0;

		monitoredElementName = "";
		monitoredElementTerminal = 1;
		monitoredElement = null;

		phaseFast     = Recloser.getTCC_Curve("a");
		phaseDelayed  = Recloser.getTCC_Curve("d");
		groundFast    = null;
		groundDelayed = null;

		phaseTrip     = 1.0;
		groundTrip    = 1.0;
		phaseInst     = 0.0;
		groundInst    = 0.0;

		TDGrDelayed   = 1.0;
		TDPhDelayed   = 1.0;
		TDGrFast      = 1.0;
		TDPhFast      = 1.0;

		resetTime     = 15.0;
		numReclose    = 3;
		numFast	      = 1;

		recloseIntervals = new double[4];  // fixed allocation of 4
		recloseIntervals[0] = 0.5;
		recloseIntervals[1] = 2.0;
		recloseIntervals[2] = 2.0;

		presentState  = ControlAction.CLOSE;

		operationCount = 1;
		lockedOut      = false;
		armedForOpen   = false;
		armedForClose  = false;
		groundTarget   = false;
		phaseTarget    = false;

		cBuffer = null;

		objType = parClass.getDSSClassType();  // CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	public void recalcElementData() {
		Circuit ckt = DSS.activeCircuit;

		int devIndex = Util.getCktElementIndex(monitoredElementName);

		if (devIndex >= 0) {
			monitoredElement = ckt.getCktElements().get(devIndex);
			setNumPhases(monitoredElement.getNumPhases());  // force number of phases to be same
			if (monitoredElementTerminal >= monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("Recloser: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 392);
			} else {
				// sets name of i-th terminal's connected bus in Recloser's bus list
				setBus(0, monitoredElement.getBus(monitoredElementTerminal));
				// allocate a buffer big enough to hold everything from the monitored element
				cBuffer = Util.resizeArray(cBuffer, monitoredElement.getYorder());
				condOffset = monitoredElementTerminal * monitoredElement.getNumConds();  // for speedy sampling
			}
		}

		/* Check for existence of controlled element */

		devIndex = Util.getCktElementIndex(elementName);
		if (devIndex >= 0) {  // both CktElement and monitored element must already exist

			setControlledElement( ckt.getCktElements().get(devIndex) );

			getControlledElement().setActiveTerminalIdx(elementTerminal);  // make the 1st terminal active
			if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
				presentState = ControlAction.CLOSE;
				lockedOut = false;
				operationCount = 1;
				armedForOpen = false;
			} else {
				presentState = ControlAction.OPEN;
				lockedOut = true;
				operationCount = numReclose + 1;
				armedForClose = false;
			}
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("Recloser: \"" + getName() + "\"", "CktElement \""+ elementName + "\" not found.",
					" Element must be defined previously.", 393);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		if (monitoredElement != null) {
			setNumPhases( monitoredElement.getNumPhases() );
			setNumConds(nPhases);
			setBus(0, monitoredElement.getBus(elementTerminal));
			// allocate a buffer big enough to hold everything from the monitored element
			cBuffer = Util.resizeArray(cBuffer, monitoredElement.getYorder());
			condOffset = elementTerminal * monitoredElement.getNumConds();  // for speedy sampling
		}
		super.makePosSequence();
	}

	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
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

		getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal of CktElement to terminal 1

		if (code == ControlAction.OPEN.code()) {
			switch (presentState) {
			case CLOSE:
				if (armedForOpen) {  // ignore if we became disarmed in meantime
					getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
					if (operationCount > numReclose) {
						lockedOut = true;
						Util.appendToEventLog("Recloser."+getName(), "Opened, Locked Out");
					} else {
						if (operationCount > numFast) {
							Util.appendToEventLog("Recloser."+getName(), "Opened, Delayed");
						} else {
							Util.appendToEventLog("Recloser."+getName(), "Opened, Fast");
						}
					}
					if (phaseTarget)
						Util.appendToEventLog(" ", "Phase Target");
					if (groundTarget)
						Util.appendToEventLog(" ", "Ground Target");
					armedForOpen = false;
				}
				break;
			}

		} else if (code == ControlAction.CLOSE.code()) {
			switch (presentState) {
			case OPEN:
				if (armedForClose && !lockedOut) {
					getControlledElement().setConductorClosed(-1, true);  // close all phases of active terminal
					operationCount += 1;
					Util.appendToEventLog("Recloser." + getName(), "Closed");
					armedForClose = false;
				}
				break;
			}

		} else if (code == ControlAction.CTRL_RESET.code()) {
			switch (presentState) {
			case CLOSE:
				if (!armedForOpen)
					operationCount = 1;  // don't reset if we just rearmed
				break;
			}
		}
	}

	// FIXME Private method in OpenDSS
	public void interpretRecloserAction(String action) {

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			switch (action.toLowerCase().charAt(0)) {
			case 'o':
				getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
				lockedOut = true;
				operationCount = numReclose + 1;
				break;
			case 't':
				getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
				lockedOut = true;
				operationCount = numReclose + 1;
				break;
			case 'c':
				getControlledElement().setConductorClosed(-1, true);   // close all phases of active terminal
				lockedOut = false;
				operationCount = 1;
				break;
			}
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void sample() {
		int i;
		double CMag;
		Complex CSum;
		Circuit ckt;

		TCC_CurveObj groundCurve, phaseCurve;
		double groundTime, phaseTime, tripTime, timeTest;
		double TDPhase, TDGround;

		getControlledElement().setActiveTerminalIdx(elementTerminal);

		if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
			presentState = ControlAction.CLOSE;
		} else {
			presentState = ControlAction.OPEN;
		}

		if (operationCount > numFast) {
			groundCurve = groundDelayed;
			phaseCurve = phaseDelayed;
			TDGround = TDGrDelayed;
			TDPhase =  TDPhDelayed;
		} else {
			groundCurve = groundFast;
			phaseCurve = phaseFast;
			TDGround = TDGrFast;
			TDPhase =  TDPhFast;
		}

		if (presentState == ControlAction.CLOSE) {
			tripTime = -1.0;
			groundTime = -1.0;
			phaseTime = -1.0;  /* No trip */

			// check largest current of all phases of monitored element
			monitoredElement.getCurrents(cBuffer);

			/* Check ground trip, if any */
			if (groundCurve != null) {
				CSum = Complex.ZERO;
				for (i = condOffset; i < nPhases + condOffset; i++)
					CSum = CSum.add(cBuffer[i]);
				CMag = CSum.abs();
				if (groundInst > 0.0 && CMag >= groundInst && operationCount == 1) {
					groundTime = 0.01 + delayTime;  // inst trip on first operation
				} else {
					groundTime = TDGround * groundCurve.getTCCTime(CMag / groundTrip);
				}
			}

			if (groundTime > 0.0) {
				tripTime = groundTime;
				groundTarget = true;
			}

			// if groundTime > 0 then we have a ground trip

			/* Check phase trip, if any */

			if (phaseCurve != null) {
				for (i = condOffset; i < nPhases + condOffset; i++) {
					CMag =  cBuffer[i].abs();

					if (phaseInst > 0.0 && CMag >= phaseInst && operationCount == 1) {
						phaseTime = 0.01 + delayTime;  // inst trip on first operation
						break;  /* no sense checking other phases */
					} else {
						timeTest = TDPhase * phaseCurve.getTCCTime(CMag / phaseTrip);
						if (timeTest > 0.0) {
							if (phaseTime < 0.0) {
								phaseTime = timeTest;
							} else {
								phaseTime = Math.min(phaseTime, timeTest);
							}
						}
					}
				}
			}

			// if PhaseTime > 0 then we have a phase trip
			if (phaseTime > 0.0) {
				phaseTarget = true;
				if (tripTime > 0.0) {
					tripTime = Math.min(tripTime, phaseTime);
				} else {
					tripTime = phaseTime;
				}
			}

			if (tripTime > 0.0) {
				if (!armedForOpen) {
					ckt = DSS.activeCircuit;
					// then arm for an open operation
					ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + delayTime, ControlAction.OPEN, 0, this);
					if (operationCount <= numReclose)
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + delayTime + recloseIntervals[operationCount], ControlAction.CLOSE, 0, this);
					armedForOpen = true;
					armedForClose = true;
				}
			} else {
				if (armedForOpen) {
					ckt = DSS.activeCircuit;
					// if current dropped below pickup, disarm trip and set for reset.
					ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + resetTime, ControlAction.CTRL_RESET, 0, this);
					armedForOpen = false;
					armedForClose = false;
					groundTarget = false;
					phaseTarget = false;
				}
			}
		}
	}

	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) pw.println();

		pw.close();
	}

	public String getPropertyValue(int index) {
		String result = "";
		switch (index) {
		case 15:
			result = "(";
			for (int i = 0; i < numReclose; i++)
				result = result + String.format("%-g, ", recloseIntervals[i]);
			result = result + ")";
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}
		return result;
	}

	/**
	 * Reset to initial defined state.
	 */
	public void reset() {

		presentState   = ControlAction.CLOSE;
		operationCount = 1;
		lockedOut      = false;
		armedForOpen   = false;
		armedForClose  = false;
		groundTarget   = false;
		phaseTarget    = false;

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(elementTerminal);  // set active terminal
			getControlledElement().setConductorClosed(-1, true);            // close all phases of active terminal
		}
	}

	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "");   // "element";
		setPropertyValue(1, "1");  // "terminal";
		setPropertyValue(2, "");
		setPropertyValue(3, "1");  // "terminal";
		setPropertyValue(4, String.valueOf(numFast));
		setPropertyValue(5, "");
		setPropertyValue(6, "");
		setPropertyValue(7, "");
		setPropertyValue(8, "");
		setPropertyValue(9, "1.0");
		setPropertyValue(10, "1.0");
		setPropertyValue(11, "0");
		setPropertyValue(12, "0");
		setPropertyValue(13, "15");
		setPropertyValue(14, "4");
		setPropertyValue(15, "(0.5, 2.0, 2.0)");
		setPropertyValue(16, "0.0");
		setPropertyValue(17, "");
		setPropertyValue(18, "1.0");
		setPropertyValue(19, "1.0");
		setPropertyValue(20, "1.0");
		setPropertyValue(21, "1.0");

		super.initPropertyValues(Recloser.NumPropsThisClass - 1);
	}

	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private members in Open DSS

	public TCC_CurveObj getPhaseDelayed() {
		return phaseDelayed;
	}

	public void setPhaseDelayed(TCC_CurveObj value) {
		phaseDelayed = value;
	}

	public TCC_CurveObj getGroundDelayed() {
		return groundDelayed;
	}

	public void setGroundDelayed(TCC_CurveObj value) {
		groundDelayed = value;
	}

	public TCC_CurveObj getPhaseFast() {
		return phaseFast;
	}

	public void setPhaseFast(TCC_CurveObj value) {
		phaseFast = value;
	}

	public TCC_CurveObj getGroundFast() {
		return groundFast;
	}

	public void setGroundFast(TCC_CurveObj value) {
		groundFast = value;
	}

	public double getPhaseTrip() {
		return phaseTrip;
	}

	public void setPhaseTrip(double value) {
		phaseTrip = value;
	}

	public double getGroundTrip() {
		return groundTrip;
	}

	public void setGroundTrip(double value) {
		groundTrip = value;
	}

	public double getPhaseInst() {
		return phaseInst;
	}

	public void setPhaseInst(double value) {
		phaseInst = value;
	}

	public double getGroundInst() {
		return groundInst;
	}

	public void setGroundInst(double value) {
		groundInst = value;
	}

	public double[] getRecloseIntervals() {
		return recloseIntervals;
	}

	public void setRecloseIntervals(double[] intervals) {
		recloseIntervals = intervals;
	}

	public int getNumFast() {
		return numFast;
	}

	public void setNumFast(int num) {
		numFast = num;
	}

	public int getNumReclose() {
		return numReclose;
	}

	public void setNumReclose(int num) {
		numReclose = num;
	}

	public double getResetTime() {
		return resetTime;
	}

	public void setResetTime(double time) {
		resetTime = time;
	}

	public double getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(double time) {
		delayTime = time;
	}

	public double getTDGrDelayed() {
		return TDGrDelayed;
	}

	public void setTDGrDelayed(double value) {
		TDGrDelayed = value;
	}

	public double getTDPhDelayed() {
		return TDPhDelayed;
	}

	public void setTDPhDelayed(double value) {
		TDPhDelayed = value;
	}

	public double getTDGrFast() {
		return TDGrFast;
	}

	public void setTDGrFast(double value) {
		TDGrFast = value;
	}

	public double getTDPhFast() {
		return TDPhFast;
	}

	public void setTDPhFast(double value) {
		TDPhFast = value;
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

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setMonitoredElement(CktElement element) {
		monitoredElement = element;
	}

	public ControlAction getPresentState() {
		return presentState;
	}

	public void setPresentState(ControlAction state) {
		presentState = state;
	}

	public int getOperationCount() {
		return operationCount;
	}

	public void setOperationCount(int count) {
		operationCount = count;
	}

	public boolean isLockedOut() {
		return lockedOut;
	}

	public void setLockedOut(boolean locked) {
		lockedOut = locked;
	}

	public boolean isArmedForClose() {
		return armedForClose;
	}

	public void setArmedForClose(boolean armed) {
		armedForClose = armed;
	}

	public boolean isArmedForOpen() {
		return armedForOpen;
	}

	public void setArmedForOpen(boolean armed) {
		armedForOpen = armed;
	}

	public boolean isGroundTarget() {
		return groundTarget;
	}

	public void setGroundTarget(boolean target) {
		groundTarget = target;
	}

	public boolean isPhaseTarget() {
		return phaseTarget;
	}

	public void setPhaseTarget(boolean target) {
		phaseTarget = target;
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
		this.cBuffer = buffer;
	}

}

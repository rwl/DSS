/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.delivery.CapacitorObj;

import static com.ncond.dss.common.Util.appendToEventLog;
import static com.ncond.dss.common.Util.getCktElementIndex;
import static com.ncond.dss.common.Util.resizeArray;

import static java.lang.Math.max;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import static java.lang.String.format;


/**
 * A control element that is connected to a terminal of another
 * circuit element and controls a capacitor.  The control is usually placed in
 * the terminal of a line or transformer, although a voltage control device
 * could be placed in the terminal of the capacitor it controls.
 *
 *   new capControl.name=myName element=devClass.name terminal=[ 1|2|...] capacitor=name
 *
 * Capacitor to be controlled must already exist.
 */
public class CapControlObj extends ControlElem {

	private static boolean SRPInhibit = false;
	@SuppressWarnings("unused")
	private static int SRPControlActionHandle = 0;

	private CapControlType controlType;

	private int CTPhaseIdx, PTPhaseIdx;  // zero based phase index, "ALL" is -1

	private double onValue, offValue, PFOnValue, PFOffValue,
		CTRatio, PTRatio, onDelay, OffDelay, deadTime, lastOpenTime;

	private boolean VOverride;
        private boolean VOverrideEvent;
	private boolean VOverrideBusSpecified;
	private String VOverrideBusName;
	private int VOverrideBusIndex;
	private double VMax, VMin;

	private String capacitorName;
	private CktElement monitoredElement;
	private CapacitorObj controlledCapacitor;
	private ControlAction pendingChange;
	private boolean shouldSwitch;  // true: action is pending
	private boolean armed;  // control is armed for switching unless reset
	private ControlAction presentState;
	private ControlAction initialState;
	private int controlActionHandle;
	private int condOffset;  // offset for monitored terminal

	private Complex[] cBuffer;

	public CapControlObj(DSSClass parClass, String capControlName) {
		super(parClass);

		setName(capControlName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		CTPhaseIdx = 0;
		PTPhaseIdx = 0;

		PTRatio = 60.0;
		CTRatio = 60.0;
		controlType = CapControlType.CURRENT;
		onDelay = 15.0;
		OffDelay = 15.0;
		deadTime = 300.0;
		lastOpenTime = -deadTime;

		onValue = 300.0;
		offValue = 200.0;

		PFOnValue = 0.95;
		PFOffValue = 1.05;

		VOverride = false;
		VOverrideEvent = false;
		VOverrideBusSpecified = false;
		VOverrideBusName = "";

		VMax = 126;
		VMin = 115;

		elementName = "";
		setControlledElement(null);
		elementTerminalIdx = 0;
		capacitorName = "";
		monitoredElement = null;

		presentState = ControlAction.CLOSE;

		shouldSwitch = false;
		armed = false;
		setPendingChange(ControlAction.NONE);
		controlActionHandle = 0;

		cBuffer = null;

		objType = parClass.getClassType();  // CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		Circuit ckt = DSS.activeCircuit;

		/* Check for existence of capacitor */

		int devIndex = getCktElementIndex(capacitorName);
		if (devIndex >= 0) {
			// both capacitor and monitored element must already exist
			setControlledElement(ckt.getCktElements().get(devIndex));
			controlledCapacitor = getCapacitor();
			setNumPhases(getControlledElement().getNumPhases());  // force number of phases to be same
			setNumConds(nPhases);
			getControlledElement().setActiveTerminalIdx(0);  // make the 1st terminal active
			// get control synched up with capacitor

			if (controlledCapacitor.availableSteps() == controlledCapacitor.getNumSteps()) {
				getControlledElement().setConductorClosed(-1, false);  // -1 sets all conductors
			} else {
				getControlledElement().setConductorClosed(-1, true);  // -1 sets all conductors
			}

			if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
				presentState = ControlAction.CLOSE;
			} else {
				presentState = ControlAction.OPEN;
			}
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("CapControl." + getName() + ":", "Capacitor element \""+ capacitorName + "\" not found.",
					"Element must be defined previously.", 361);
		}

		initialState = presentState;

		/* Check for existence of monitored element */

		devIndex = getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = ckt.getCktElements().get(devIndex);
			if (elementTerminalIdx >= monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("CapControl: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.", "Re-specify terminal no.", 362);
			} else {
				// sets name of i-th terminal's connected bus in CapControl's bus list
				setBus(0, monitoredElement.getBus(elementTerminalIdx));

				// allocate a buffer big enough to hold everything from the monitored element
				cBuffer = resizeArray(cBuffer, monitoredElement.getYOrder());

				condOffset = (elementTerminalIdx + 1) * monitoredElement.getNumConds();  // for speedy sampling
			}
		} else {
			DSS.doSimpleMsg("Monitored element in CapControl." + getName() +
					" does not exist:\"" + elementName + "\"", 363);
		}

		/* alternative override bus */
		if (VOverrideBusSpecified) {
			VOverrideBusIndex = ckt.getBusList().find(VOverrideBusName);
			if (VOverrideBusIndex == -1) {
				DSS.doSimpleMsg(format("CapControl.%s: Voltage override Bus \"%s\" not found. " +
						"Did you wait until buses were defined? Reverting to default.",
						getName(), VOverrideBusName), 10361);
				VOverrideBusSpecified = false;
			}
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled(getControlledElement().isEnabled());
			setNumPhases(getControlledElement().getNumPhases());
			setNumConds(nPhases);
		}

		if (monitoredElement != null) {
			setBus(0, monitoredElement.getBus(elementTerminalIdx));

			// allocate a buffer big enough to hold everything from the monitored element
			cBuffer = resizeArray(cBuffer, monitoredElement.getYOrder());

			condOffset = (elementTerminalIdx + 1) * monitoredElement.getNumConds();  // for speedy sampling
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	private void getBusVoltages(Bus bus, Complex[] buff) {
		if (bus.getVBus() != null) {  // uses nPhases from CapControlObj
			for (int j = 0; j < nPhases; j++)
				buff[j] = DSS.activeCircuit.getSolution().getNodeV(bus.getRef(j));
		}
	}

	/**
	 * Get current to control on based on type of control specified.
	 */
	private double getControlCurrent() {
		int i;
		double controlCurrent;

		switch (CTPhaseIdx) {
		case CapControl.AVGPHASES:
			controlCurrent = 0.0;  // get avg of all phases
			for (i = condOffset; i < nPhases + condOffset; i++)
				controlCurrent += cBuffer[i].abs();
			controlCurrent = controlCurrent / nPhases / CTRatio;
			break;
		case CapControl.MAXPHASE:
			controlCurrent = 0.0;  // get max of all phases
			for (i = condOffset; i < nPhases + condOffset; i++)
				controlCurrent = max(controlCurrent, cBuffer[i].abs());
			controlCurrent = controlCurrent / CTRatio;
			break;
		case CapControl.MINPHASE:
			controlCurrent = 1.0e50;  // get min of all phases
			for (i = condOffset; i < nPhases + condOffset; i++)
				controlCurrent = min(controlCurrent, cBuffer[i].abs());
			controlCurrent = controlCurrent / CTRatio;
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			controlCurrent = cBuffer[CTPhaseIdx].abs() / CTRatio;  // monitored phase only
			break;
		}

		return controlCurrent;
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) pw.println();

		pw.close();
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int code, int proxyHdl) {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		getControlledElement().setActiveTerminalIdx(0);  // set active terminal of capacitor to terminal 1

		switch (controlType) {
		case SRP:  // allows one capacitor to switch every 15 min
			/* SRPInhibit is a module variable and if any SRP capcontrol sets it true all other SRP CapControls will simply exit */
			if (SRPInhibit) {
				if (code == CapControl.SRPINHIBITRELEASE) {
					SRPInhibit = false;
					return;  // without doing anything; just process the inhibit release if sent
				} else {
					// if it is a VOverride event just process the pending change
					// but leave the inhibit on
					// if not, need to remove the armed switch so cap control can sample and
					// send the message again.
					if (!VOverrideEvent) {
						shouldSwitch = false;
						armed = false;  // reset control
						return;  // don't do anything; just send it back
					}
				}
			} else {
				if ((code == ControlAction.OPEN.code()) || (code == ControlAction.CLOSE.code())) {  // skip NONE
					/* We'll switch capacitor this time, but then not again until inhibit released */
					SRPInhibit = true;  // prevent further switching until inhibit released in 15 min
					SRPControlActionHandle = ckt.getControlQueue().push(sol.getIntHour(),
							sol.getDynaVars().t + 900.0 , CapControl.SRPINHIBITRELEASE, 0, this);
				}
			}
		}

		switch (pendingChange) {
		case OPEN:
			switch (controlledCapacitor.getNumSteps()) {
			case 1:
				if (presentState == ControlAction.CLOSE) {
					getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal

					if (showEventLog)
						appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");

					presentState = ControlAction.OPEN;

					lastOpenTime = sol.getDynaVars().t + 3600.0 * sol.getIntHour();
				}
				break;
			default:
				if (presentState == ControlAction.CLOSE)  // do this only if at least one step is closed
					if (!controlledCapacitor.subtractStep()) {
						presentState = ControlAction.OPEN;
						getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal
						if (showEventLog)
							appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");
					} else {
						if (showEventLog)
							appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Down**");
					}
				break;
			}
			break;
		case CLOSE:
			if (presentState == ControlAction.OPEN) {
				getControlledElement().setConductorClosed(0, true);  // close all phases of active terminal
				if (showEventLog)
					appendToEventLog("Capacitor." + getControlledElement().getName(), "**Closed**");
				presentState = ControlAction.CLOSE;
				controlledCapacitor.addStep();
			} else {
				if (controlledCapacitor.addStep()) {
					if (showEventLog)
						appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Up**");
				}
			}
			break;
		default:
			/* Do nothing for none if the control has reset */
			break;
		}

		VOverrideEvent = false;
		shouldSwitch = false;
		armed = false;  // reset control
	}

	/**
	 * Get Voltage used for voltage control based on specified options.
	 */
	private double getControlVoltage() {
		int i;
		double controlVoltage;

		switch (PTPhaseIdx) {
		case CapControl.AVGPHASES:
			controlVoltage = 0.0;
			for (i = 0; i < monitoredElement.getNumPhases(); i++)
				controlVoltage += cBuffer[i].abs();
			controlVoltage = controlVoltage / monitoredElement.getNumPhases() / PTRatio;
			break;
		case CapControl.MAXPHASE:
			controlVoltage = 0.0;
			for (i = 0; i < monitoredElement.getNumPhases(); i++)
				controlVoltage = max(controlVoltage, cBuffer[i].abs());
			controlVoltage = controlVoltage / PTRatio;
			break;
		case CapControl.MINPHASE:
			controlVoltage = 1.0e50;
			for (i = 0; i < monitoredElement.getNumPhases(); i++)
				controlVoltage = min(controlVoltage, cBuffer[i].abs());
			controlVoltage = controlVoltage / PTRatio;
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			// use L-L aB if capacitor is delta connected
			switch (((CapacitorObj) getControlledElement()).getConnection()) {
			case DELTA:
				controlVoltage = cBuffer[PTPhaseIdx].subtract(cBuffer[nextDeltaPhase(PTPhaseIdx)]).abs() / PTRatio;
				break;
			default:
				controlVoltage = cBuffer[PTPhaseIdx].abs() / PTRatio;
				break;
			}
			break;
		}

		return controlVoltage;
	}

	private int nextDeltaPhase(int iphs) {
		int next = iphs + 1;
		if (next >= nPhases) next = 0;
		return next;
	}


	/**
	 * @return PF in range of 1 to 2
	 */
	private static double pf1to2(Complex S) {
		double pf;
		double Sabs = S.abs();

		pf = (Sabs != 0.0) ? abs(S.getReal()) / Sabs : 1.0;  // default to unity

		if (S.getImaginary() < 0.0) pf = 2.0 - pf;

		return pf;
	}
	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		Complex S;
		double normalizedTime, Q, pf, currTest, Vtest;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		getControlledElement().setActiveTerminalIdx(0);

		if (getControlledElement().isConductorClosed(-1)) {  // check state of phases of active terminal
			presentState = ControlAction.CLOSE;
		} else {
			presentState = ControlAction.OPEN;
		}

		shouldSwitch = false;

		// first check voltage override
		if (VOverride) {
			if (controlType != CapControlType.VOLTAGE) {  // don't bother for voltage control

				if (VOverrideBusSpecified) {
					getBusVoltages(DSS.activeCircuit.getBus(VOverrideBusIndex), cBuffer);
				} else {
					monitoredElement.getTermVoltages(elementTerminalIdx, cBuffer);
				}

				Vtest = getControlVoltage();

				switch (presentState) {
				case OPEN:
					if (Vtest < VMin) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
						VOverrideEvent = true;
						if (showEventLog)
							appendToEventLog("Capacitor." + getControlledElement().getName(),
									format("Low voltage override: %.8g V", Vtest));
					}
					break;
				case CLOSE:
					if (Vtest > VMax) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
						VOverrideEvent = true;
						if (showEventLog)
							appendToEventLog("Capacitor." + getControlledElement().getName(),
									format("High voltage override: %.8g V", Vtest));
					}
					break;
				}
			}
		}

		if (!shouldSwitch) {  // else skip other control evaluations
			switch (controlType) {
			case CURRENT:
				// check largest current of all phases of monitored element
				monitoredElement.getCurrents(cBuffer);

				currTest = getControlCurrent();

				switch (presentState) {
				case OPEN:
					if (currTest > onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (currTest < offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (currTest > onValue) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch = true;
						}
					} else {  // reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			case VOLTAGE:
				monitoredElement.getTermVoltages(elementTerminalIdx, cBuffer);

				Vtest = getControlVoltage();

				switch (presentState) {
				case OPEN:
					if (Vtest < onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (Vtest > offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (Vtest < onValue) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch = true;
						}
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			case KVAR:
				//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
				S = monitoredElement.getPower(elementTerminalIdx);
				Q = S.getImaginary() * 0.001;  // kvar

				switch (presentState) {
				case OPEN:
					if (Q > onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (Q < offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (Q > onValue) {
							setPendingChange(ControlAction.CLOSE);  // we can go some more
							shouldSwitch = true;
						}
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			case SRP:  /* kvar modified to keep PF around .98 lead */
				//monitoredElement.activeTerminalIdx = elementTerminal;
				S = monitoredElement.getPower(elementTerminalIdx);
				Q = S.getImaginary() * 0.001 + 0.20306 * S.getReal() * 0.001;  // kvar for -.98 PF
				//Q = S.getImaginary() * 0.001 + 0.063341 * S.getReal() * 0.001;  // kvar for -.998 PF

				switch (presentState) {
				case OPEN:
					if (Q > onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (Q < offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (Q > onValue) {
							setPendingChange(ControlAction.CLOSE);  // we can go some more
							shouldSwitch = true;
						}
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			case TIME:
				normalizedTime = normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t);

				switch (presentState) {
				case OPEN:
					if (offValue > onValue) {
						if (normalizedTime >= onValue && normalizedTime < offValue) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch  = true;
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					} else {  // OFF time is next day
						if (normalizedTime >= onValue && normalizedTime < 24.0) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch  = true;
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					}
					break;
				case CLOSE:
					if (offValue > onValue) {
						if (normalizedTime >= offValue) {
							setPendingChange(ControlAction.OPEN);
							shouldSwitch = true;
						} else if (controlledCapacitor.availableSteps() > 0) {
							if (normalizedTime >= onValue && normalizedTime < offValue) {
								setPendingChange(ControlAction.CLOSE);  // we can go some more
								shouldSwitch = true;
							}
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					} else {  // off time is next day
						if (normalizedTime >= offValue && normalizedTime < onValue) {
							setPendingChange(ControlAction.OPEN);
							shouldSwitch = true;
						} else if (controlledCapacitor.availableSteps() > 0) {
							if (normalizedTime >= onValue && normalizedTime < 24.0) {
								setPendingChange(ControlAction.CLOSE);  // we can go some more
								shouldSwitch = true;
							}
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					}
					break;
				}

			case PF:
				//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
				S = monitoredElement.getPower(elementTerminalIdx);
				pf = pf1to2(S);

				/* PF is in range of 0..2; leading is 1..2 */
				/* When turning on make sure there is at least half the kvar of the bank */

				switch (presentState) {
				case OPEN:
					// make sure we don't go too far leading
					if (pf < PFOnValue && (S.getImaginary() * 0.001 > controlledCapacitor.getTotalKVAr() * 0.5)) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (pf > PFOffValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (pf < PFOnValue && (S.getImaginary() * 0.001 > controlledCapacitor.getTotalKVAr() / controlledCapacitor.getNumSteps() * 0.5)) {
							setPendingChange(ControlAction.CLOSE);  // we can go some more
							shouldSwitch = true;
						}
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			}
		}

		if (shouldSwitch && !armed) {
			if (pendingChange == ControlAction.CLOSE) {
				if ((sol.getDynaVars().t + sol.getIntHour() * 3600.0 - lastOpenTime) < deadTime) {  // delay the close operation
					/* Added ONDelay to deadTime so that all caps do not close back in at same time */
					timeDelay = max(onDelay,
						(deadTime + onDelay) - (sol.getDynaVars().t + sol.getIntHour() * 3600.0 - lastOpenTime));
				} else {
					timeDelay = onDelay;
				}
			} else {
				timeDelay = OffDelay;
			}
			controlActionHandle = ckt.getControlQueue().push(sol.getIntHour(),
				sol.getDynaVars().t + timeDelay , pendingChange, 0, this);
			armed = true;
			if (showEventLog)
				appendToEventLog("Capacitor." + getControlledElement().getName(),
					format("**Armed**, Delay= %.5g sec", timeDelay));
		}

		if (armed && pendingChange == ControlAction.NONE) {
			ckt.getControlQueue().delete(controlActionHandle);
			armed = false;
			if (showEventLog)
				appendToEventLog("Capacitor." + getControlledElement().getName(), "**Reset**");
		}
	}

	public CapacitorObj getCapacitor() {
		return (CapacitorObj) getControlledElement();
	}

	/**
	 * Normalize time to a floating point number representing time of day if Hour > 24
	 * Resulting time should be 0:00+ to 24:00 inclusive.
	 */
	private double normalizeToTOD(int h, double sec) {
		int hourOfDay;
		double result;

		if (h > 24) {
			hourOfDay = (h - ((h - 1) / 24) * 24);  // creates numbers 1-24
		} else {
			hourOfDay = h;
		}

		result = hourOfDay + sec / 3600.0;

		// if the TOD is at least slightly greater than 24:00 wrap around to 0:00
		if (result - 24.0 > DSS.EPSILON)
			result = result - 24.0;  // wrap around

		return result;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		setPendingChange(ControlAction.NONE);
		getControlledElement().setActiveTerminalIdx(0);
		switch (initialState) {
		case OPEN:
			getControlledElement().setConductorClosed(-1, false);  // open all phases of active terminal
			break;
		case CLOSE:
			getControlledElement().setConductorClosed(-1, true);   // close all phases of active terminal
			break;
		}
		shouldSwitch = false;
		lastOpenTime = -deadTime;
		presentState = initialState;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");   // "element";
		setPropertyValue(1, "1");  // "terminal";
		setPropertyValue(2, "");
		setPropertyValue(3, "current");
		setPropertyValue(4, "60");
		setPropertyValue(5, "60");
		setPropertyValue(6, "300");
		setPropertyValue(7, "200");
		setPropertyValue(8, "15");
		setPropertyValue(9, "no");
		setPropertyValue(10, "126");
		setPropertyValue(11, "115");
		setPropertyValue(12, "15");
		setPropertyValue(13, "300");
		setPropertyValue(14, "1");
		setPropertyValue(15, "1");
		setPropertyValue(16, "");
		setPropertyValue(17, "yes");

		super.initPropertyValues(CapControl.NumPropsThisClass - 1);
	}

	public void setPendingChange(ControlAction value) {
		pendingChange = value;
		traceParameter = value.code();
	}

	public CapControlType getControlType() {
		return controlType;
	}

	public void setControlType(CapControlType controlType) {
		this.controlType = controlType;
	}

	public int getCTPhaseIdx() {
		return CTPhaseIdx;
	}

	public void setCTPhaseIdx(int cTPhaseIdx) {
		CTPhaseIdx = cTPhaseIdx;
	}

	public String getCapacitorName() {
		return capacitorName;
	}

	public void setCapacitorName(String capacitorName) {
		this.capacitorName = capacitorName;
	}

	public int getPTPhaseIdx() {
		return PTPhaseIdx;
	}

	public double getOnValue() {
		return onValue;
	}

	public double getOffValue() {
		return offValue;
	}

	public double getPFOnValue() {
		return PFOnValue;
	}

	public double getPFOffValue() {
		return PFOffValue;
	}

	public double getCTRatio() {
		return CTRatio;
	}

	public double getPTRatio() {
		return PTRatio;
	}

	public boolean isVOverride() {
		return VOverride;
	}

	public boolean isVOverrideBusSpecified() {
		return VOverrideBusSpecified;
	}

	public String getVOverrideBusName() {
		return VOverrideBusName;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public boolean isShouldSwitch() {
		return shouldSwitch;
	}

	public ControlAction getPresentState() {
		return presentState;
	}

	public int getCondOffset() {
		return condOffset;
	}

	public void setPTPhaseIdx(int pTPhaseIdx) {
		PTPhaseIdx = pTPhaseIdx;
	}

	public void setOnValue(double onValue) {
		this.onValue = onValue;
	}

	public void setOffValue(double offValue) {
		this.offValue = offValue;
	}

	public void setPFOnValue(double pFOnValue) {
		PFOnValue = pFOnValue;
	}

	public void setPFOffValue(double pFOffValue) {
		PFOffValue = pFOffValue;
	}

	public void setCTRatio(double cTRatio) {
		CTRatio = cTRatio;
	}

	public void setPTRatio(double pTRatio) {
		PTRatio = pTRatio;
	}

	public void setOnDelay(double onDelay) {
		this.onDelay = onDelay;
	}

	public void setOffDelay(double offDelay) {
		OffDelay = offDelay;
	}

	public void setDeadTime(double deadTime) {
		this.deadTime = deadTime;
	}

	public void setVOverride(boolean vOverride) {
		VOverride = vOverride;
	}

	public void setVOverrideBusSpecified(boolean vOverrideBusSpecified) {
		VOverrideBusSpecified = vOverrideBusSpecified;
	}

	public void setVOverrideBusName(String vOverrideBusName) {
		VOverrideBusName = vOverrideBusName;
	}

	public void setVMax(double vMax) {
		VMax = vMax;
	}

	public void setVMin(double vMin) {
		VMin = vMin;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		this.monitoredElement = monitoredElement;
	}

	public void setShouldSwitch(boolean shouldSwitch) {
		this.shouldSwitch = shouldSwitch;
	}

	public void setPresentState(ControlAction presentState) {
		this.presentState = presentState;
	}

	public void setCondOffset(int condOffset) {
		this.condOffset = condOffset;
	}

}

package com.epri.dss.control.impl;

import java.io.PrintStream;

import org.apache.commons.lang.mutable.MutableDouble;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.CapControl;
import com.epri.dss.control.CapControlObj;
import com.epri.dss.delivery.CapacitorObj;

public class CapControlObjImpl extends ControlElemImpl implements CapControlObj {

	public enum CapControlType {
		CURRENT, VOLTAGE, KVAR, TIME, PF, SRP
	}

	private CapControlType controlType;

	private int CTPhase, PTPhase;  // "ALL" is -1

	private double onValue,
		offValue,
		PFOnValue,
		PFOffValue,
		CTRatio,
		PTRatio,
		OnDelay,
		OffDelay,
		DeadTime,
		LastOpenTime;

	private boolean VOverride;
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

	public CapControlObjImpl(DSSClassImpl parClass, String capControlName) {
		super(parClass);
		setName(capControlName.toLowerCase());
		this.DSSObjType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		this.CTPhase = 1;
		this.PTPhase = 1;

		this.PTRatio     = 60.0;
		this.CTRatio     = 60.0;
		this.controlType = CapControlType.CURRENT;
		this.OnDelay     = 15.0;
		this.OffDelay    = 15.0;
		this.DeadTime    = 300.0;
		this.LastOpenTime = -DeadTime;

		this.onValue    = 300.0;
		this.offValue   = 200.0;

		this.PFOnValue  = 0.95;
		this.PFOffValue = 1.05;

		this.VOverride = false;
		this.VMax      = 126;
		this.VMin      = 115;

		this.elementName = "";
		setControlledElement(null);
		this.elementTerminal = 1;
		this.capacitorName = "";
		this.monitoredElement = null;

		this.presentState = ControlAction.CLOSE;

		this.shouldSwitch = false;
		this.armed        = false;
		setPendingChange(ControlAction.NONE);
		this.controlActionHandle = 0;

		this.cBuffer = null;

		this.DSSObjType = parClass.getDSSClassType();  // CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		/* Check for existence of capacitor */

		int devIndex = Utilities.getCktElementIndex(capacitorName);
		if (devIndex >= 0) {
			// both capacitor and monitored element must already exist
			setControlledElement(ckt.getCktElements().get(devIndex));
			controlledCapacitor = getCapacitor();
			setNPhases( getControlledElement().getNPhases() );  // force number of phases to be same
			setNConds(nPhases);
			getControlledElement().setActiveTerminalIdx(0);  // make the 1st terminal active   TODO Check zero based indexing
			// Get control synched up with capacitor

			if (controlledCapacitor.availableSteps() == controlledCapacitor.getNumSteps()) {
				getControlledElement().setConductorClosed(0, false);  // TODO Check zero based indexing
			} else {
				getControlledElement().setConductorClosed(0, true);  // TODO Check zero based indexing
			}
			if (getControlledElement().getConductorClosed(0)) {  // check state of phases of active terminal
				presentState = ControlAction.CLOSE;
			} else {
				presentState = ControlAction.OPEN;
			}
		} else {
			setControlledElement(null);  // element not found
			globals.doErrorMsg("CapControl: \"" + getName() + "\"", "Capacitor Element \""+ capacitorName + "\" Not Found.",
					"Element must be defined previously.", 361);
		}

		initialState = presentState;

		/* Check for existence of monitored element */

		devIndex = Utilities.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = ckt.getCktElements().get(devIndex);
			if (elementTerminal > monitoredElement.getNTerms() - 1) {  // TODO Check zero based indexing
				globals.doErrorMsg("CapControl: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.", "Re-specify terminal no.", 362);
			} else {
				// sets name of i-th terminal's connected bus in CapControl's buslist
				setBus(1, monitoredElement.getBus(elementTerminal));  // TODO Check zero based indexing

				// allocate a buffer big enough to hold everything from the monitored element
				cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, monitoredElement.getYorder());

				condOffset = (elementTerminal - 1) * monitoredElement.getNConds();  // for speedy sampling
			}
		} else {
			globals.doSimpleMsg("Monitored Element in CapControl."+getName()+ " does not exist:\""+elementName+"\"", 363);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled(getControlledElement().isEnabled());
			setNPhases( getControlledElement().getNPhases() );
			setNConds(nPhases);
		}

		if (monitoredElement != null) {
			setBus(1, monitoredElement.getBus(elementTerminal));

			// allocate a buffer big enough to hold everything from the monitored element
			cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, monitoredElement.getYorder());

			condOffset = (elementTerminal - 1) * monitoredElement.getNConds();  // for speedy sampling
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	/**
	 * Get current to control on based on type of control specified.
	 */
	private void getControlCurrent(MutableDouble controlCurrent) {
		// FIXME: return double
		int i;

		switch (CTPhase) {
		case CapControl.AVGPHASES:
			controlCurrent.setValue(0.0);  // get avg of all phases
			for (i = (1 + condOffset); i < (nPhases + condOffset); i++)  // TODO Check zero based indexing
				controlCurrent.add(cBuffer[i].abs());
			controlCurrent.setValue(controlCurrent.doubleValue() / nPhases / CTRatio);
			break;
		case CapControl.MAXPHASE:
			controlCurrent.setValue(0.0);  // get max of all phases
			for (i = (1 + condOffset); i < (nPhases + condOffset); i++)
				controlCurrent.setValue(Math.max(controlCurrent.doubleValue(), cBuffer[i].abs()));
			controlCurrent.setValue(controlCurrent.doubleValue() / CTRatio);
			break;
		case CapControl.MINPHASE:
			controlCurrent.setValue(1.0e50);  // get min of all phases
			for (i = (1 + condOffset); i < (nPhases + condOffset); i++)
				controlCurrent.setValue(Math.min(controlCurrent.doubleValue(), cBuffer[i].abs()));
			controlCurrent.setValue(controlCurrent.doubleValue() / CTRatio);
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			controlCurrent.setValue(cBuffer[CTPhase].abs() / CTRatio);  // monitored phase only
			break;
		}
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete)
			f.println();
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int code, int proxyHdl) {

		getControlledElement().setActiveTerminalIdx(0);  // set active terminal of capacitor to terminal 1  TODO Check zero based indexing

		switch (pendingChange) {
		case OPEN:
			switch (controlledCapacitor.getNumSteps()) {
			case 1:
				if (presentState == ControlAction.CLOSE) {
					getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal

					Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");
					presentState = ControlAction.OPEN;

					SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

					LastOpenTime = sol.getDynaVars().t + 3600.0 * sol.getIntHour();
				}
				break;
			default:
				if (presentState == ControlAction.CLOSE)  // do this only if at least one step is closed
					if (!controlledCapacitor.subtractStep()) {
						presentState = ControlAction.OPEN;
						getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal
						Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");
					} else {
						Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Down**");
					}
				break;
			}
			break;
		case CLOSE:
			if (presentState == ControlAction.OPEN) {
				getControlledElement().setConductorClosed(0, true);  // close all phases of active terminal
				Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Closed**");
				presentState = ControlAction.CLOSE;
				controlledCapacitor.addStep();
			} else {
				if (controlledCapacitor.addStep())
					Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Up**");
			}
			break;
		default:
			/* Do nothing for none if the control has reset */
			break;
		}

		shouldSwitch = false;
		armed        = false;  // reset control
	}


	private int nextDeltaPhase(int iphs) {
		int result = iphs + 1;
		if (result >= nPhases)
			result = 0;
		return result;
	}
	/**
	 * Get Voltage used for voltage control based on specified options.
	 */
	private void getControlVoltage(MutableDouble controlVoltage) {
		// FIXME: return double
		int i;

		switch (PTPhase) {
		case CapControl.AVGPHASES:
			controlVoltage.setValue(0.0);
			for (i = 0; i < monitoredElement.getNPhases(); i++)
				controlVoltage.add(cBuffer[i].abs());
			controlVoltage.setValue(controlVoltage.doubleValue() / monitoredElement.getNPhases() / PTRatio);
			break;
		case CapControl.MAXPHASE:
			controlVoltage.setValue(0.0);
			for (i = 0; i < monitoredElement.getNPhases(); i++)
				controlVoltage.setValue(Math.max(controlVoltage.doubleValue(), cBuffer[i].abs()));
			controlVoltage.setValue(controlVoltage.doubleValue() / PTRatio);
			break;
		case CapControl.MINPHASE:
			controlVoltage.setValue(1.0e50);
			for (i = 0; i < monitoredElement.getNPhases(); i++)
				controlVoltage.setValue(Math.min(controlVoltage.doubleValue(), cBuffer[i].abs()));
			controlVoltage.setValue(controlVoltage.doubleValue() / PTRatio);
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			// use L-L aB if capacitor is delta connected
			CapacitorObj pElem = (CapacitorObj) getControlledElement();
			switch (pElem.getConnection()) {
			case 1:
				controlVoltage.setValue( cBuffer[PTPhase].subtract( cBuffer[nextDeltaPhase(PTPhase)] ).abs() / PTRatio );  // delta
				break;
			default:
				controlVoltage.setValue( cBuffer[PTPhase].abs() / PTRatio );  // wye - default
				break;
			}
			break;
		}
	}


	private static double PF1to2(Complex S) {  // return PF in range of 1 to 2
		double result;
		double Sabs = S.abs();

		if (Sabs != 0.0) {
			result = Math.abs(S.getReal()) / Sabs;
		} else {
			result = 1.0;  // default to unity
		}

		if (S.getImaginary() < 0.0)
			result = 2.0 - result;

		return result;
	}
	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		double normalizedTime, Q;
		MutableDouble VTest = new MutableDouble();
		MutableDouble currTest = new MutableDouble();
		Complex S;
		double PF;

		getControlledElement().setActiveTerminalIdx(0);
		if (getControlledElement().getConductorClosed(0)) {  // check state of phases of active terminal
			presentState = ControlAction.CLOSE;
		} else {
			presentState = ControlAction.OPEN;
		}
		shouldSwitch = false;

		// first check voltage override
		if (VOverride) {
			if (controlType != CapControlType.VOLTAGE) {  // don't bother for voltage control

				monitoredElement.getTermVoltages(elementTerminal, cBuffer);

				getControlVoltage(VTest);

				switch (presentState) {
				case OPEN:
					if (VTest.doubleValue() < VMin) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					}
					break;
				case CLOSE:
					if (VTest.doubleValue() > VMax) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					}
					break;
				}
			}
		}


		if (!shouldSwitch) {  // else skip other control evaluations
			switch (controlType) {
			case CURRENT:  /* Current */

				// check largest current of all phases of monitored element
				monitoredElement.getCurrents(cBuffer);

				getControlCurrent(currTest);

				switch (presentState) {
				case OPEN:
					if (currTest.doubleValue() > onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (currTest.doubleValue() < offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (currTest.doubleValue() > onValue) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch = true;
						}
					} else {  // reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				}
				break;
			case VOLTAGE:  /* Voltage */
				monitoredElement.getTermVoltages(elementTerminal, cBuffer);

				getControlVoltage(VTest);

				switch (presentState) {
				case OPEN:
					if (VTest.doubleValue() < onValue) {
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {
						// reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (VTest.doubleValue() > offValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if (VTest.doubleValue() < onValue) {
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
			case KVAR:  /* kvar */
				//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
				S = monitoredElement.getPower(elementTerminal);
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
				//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
				S = monitoredElement.getPower(elementTerminal);
				Q = S.getImaginary() * 0.001 + 0.20306 * S.getReal() * 0.001;  // kvar for -.98 PF

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
			case TIME:  /* time */
				SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
				normalizedTime = normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t);

				switch (presentState) {
				case OPEN:
					if (offValue > onValue) {
						if ((normalizedTime >= onValue) && (normalizedTime < offValue)) {
							setPendingChange(ControlAction.CLOSE);
							shouldSwitch  = true;
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					} else {  // OFF time is next day
						if ((normalizedTime >= onValue) && (normalizedTime < 24.0)) {
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
						if (normalizedTime  >= offValue) {
							setPendingChange(ControlAction.OPEN);
							shouldSwitch = true;
						} else if (controlledCapacitor.availableSteps() > 0) {
							if ((normalizedTime >= onValue) && (normalizedTime < offValue)) {
								setPendingChange(ControlAction.CLOSE);  // we can go some more
								shouldSwitch = true;
							}
						} else {
							// reset
							setPendingChange(ControlAction.NONE);
						}
					} else {  // off time is next day
						if ((normalizedTime >= offValue) && (normalizedTime < onValue)) {
							setPendingChange(ControlAction.OPEN);
							shouldSwitch = true;
						} else if (controlledCapacitor.availableSteps() > 0) {
							if ((normalizedTime >= onValue) && (normalizedTime < 24.0)) {
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

			case PF:  /* PF */
				//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
				S = monitoredElement.getPower(elementTerminal);
				PF = PF1to2(S);  // TODO Check zero based indexing

				/* PF is in range of 0 .. 2; leading is 1..2 */
				/* When turning on make sure there is at least half the kvar of the bank */

				switch (presentState) {
				case OPEN:
					if ((PF < PFOnValue) && (S.getImaginary() * 0.001 > controlledCapacitor.getTotalkvar() * 0.5)) {  // make sure we don't go too far leading
						setPendingChange(ControlAction.CLOSE);
						shouldSwitch = true;
					} else {  // reset
						setPendingChange(ControlAction.NONE);
					}
					break;
				case CLOSE:
					if (PF > PFOffValue) {
						setPendingChange(ControlAction.OPEN);
						shouldSwitch = true;
					} else if (controlledCapacitor.availableSteps() > 0) {
						if ((PF < PFOnValue) && (S.getImaginary() * 0.001 > controlledCapacitor.getTotalkvar() / controlledCapacitor.getNumSteps() * 0.5)) {
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

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (shouldSwitch && !armed) {
			if (pendingChange == ControlAction.CLOSE) {
				if ((sol.getDynaVars().t + sol.getIntHour() * 3600.0 - LastOpenTime) < DeadTime) {  // delay the close operation
					/* 2-6-09 Added ONDelay to DeadTime so that all caps do not close back in at same time */
					timeDelay = Math.max(OnDelay, (DeadTime + OnDelay) - (sol.getDynaVars().t + sol.getIntHour() * 3600.0 - LastOpenTime));
				} else {
					timeDelay = OnDelay;
				}
			} else {
				timeDelay = OffDelay;
			}
			controlActionHandle = ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + timeDelay , pendingChange, 0, this);
			armed = true;
			Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), String.format("**Armed**, Delay= %.5g sec", timeDelay));
		}

		if (armed && (pendingChange == ControlAction.NONE)) {
			ckt.getControlQueue().delete(controlActionHandle);
			armed = false;
			Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Reset**");
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

		if (h > 24) {
			hourOfDay = (h - ((h - 1) / 24) * 24);  // creates numbers 1-24
		} else {
			hourOfDay = h;
		}

		double result = hourOfDay + sec / 3600.0;

		// if the TOD is at least slightly greater than 24:00 wrap around to 0:00
		if (result - 24.0 > DSSGlobals.EPSILON)
			result = result - 24.0;  // Wrap around

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
			getControlledElement().setConductorClosed(0, false);  // open all phases of active terminal
			break;
		case CLOSE:
			getControlledElement().setConductorClosed(0, true);   // close all phases of active terminal
			break;
		}
		shouldSwitch = false;
		LastOpenTime = -DeadTime;
		presentState = initialState;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		PropertyValue[0]  = "";   // "element";
		PropertyValue[1]  = "1";  // "terminal";
		PropertyValue[2]  = "";
		PropertyValue[3]  = "current";
		PropertyValue[4]  = "60";
		PropertyValue[5]  = "60";
		PropertyValue[6]  = "300";
		PropertyValue[7]  = "200";
		PropertyValue[8]  = "15";
		PropertyValue[9]  = "NO";
		PropertyValue[10] = "126";
		PropertyValue[11] = "115";
		PropertyValue[12] = "15";
		PropertyValue[13] = "300";
		PropertyValue[14] = "1";
		PropertyValue[15] = "1";

		super.initPropertyValues(CapControl.NumPropsThisClass);
	}

	public void setPendingChange(ControlAction value) {
		pendingChange = value;
		dblTraceParameter = value.code();
	}

	public ControlAction getPendingChange() {
		return pendingChange;
	}

	public CapControlType getControlType() {
		return controlType;
	}

	public void setControlType(CapControlType type) {
		controlType = type;
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

	public double getOnDelay() {
		return OnDelay;
	}

	public double getOffDelay() {
		return OffDelay;
	}

	public double getDeadTime() {
		return DeadTime;
	}

	public boolean isVOverride() {
		return VOverride;
	}

	public double getVMax() {
		return VMax;
	}

	public double getVMin() {
		return VMin;
	}

	// FIXME Private properties in OpenDSS

	public int getCTPhase() {
		return CTPhase;
	}

	public void setCTPhase(int phase) {
		CTPhase = phase;
	}

	public int getPTPhase() {
		return PTPhase;
	}

	public void setPTPhase(int phase) {
		PTPhase = phase;
	}

	public double getLastOpenTime() {
		return LastOpenTime;
	}

	public void setLastOpenTime(double time) {
		LastOpenTime = time;
	}

	public String getCapacitorName() {
		return capacitorName;
	}

	public void setCapacitorName(String name) {
		capacitorName = name;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setMonitoredElement(CktElement element) {
		monitoredElement = element;
	}

	public CapacitorObj getControlledCapacitor() {
		return controlledCapacitor;
	}

	public void setControlledCapacitor(CapacitorObj capacitor) {
		controlledCapacitor = capacitor;
	}

	public boolean isShouldSwitch() {
		return shouldSwitch;
	}

	public void setShouldSwitch(boolean value) {
		shouldSwitch = value;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean value) {
		armed = value;
	}

	public ControlAction getPresentState() {
		return presentState;
	}

	public void setPresentState(ControlAction state) {
		presentState = state;
	}

	public ControlAction getInitialState() {
		return initialState;
	}

	public void setInitialState(ControlAction state) {
		initialState = state;
	}

	public int getControlActionHandle() {
		return controlActionHandle;
	}

	public void setControlActionHandle(int handle) {
		controlActionHandle = handle;
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

	public void setOnValue(double value) {
		onValue = value;
	}

	public void setOffValue(double value) {
		offValue = value;
	}

	public void setPFOnValue(double value) {
		PFOnValue = value;
	}

	public void setPFOffValue(double value) {
		PFOffValue = value;
	}

	public void setCTRatio(double ratio) {
		CTRatio = ratio;
	}

	public void setPTRatio(double ratio) {
		PTRatio = ratio;
	}

	public void setOnDelay(double delay) {
		OnDelay = delay;
	}

	public void setOffDelay(double delay) {
		OffDelay = delay;
	}

	public void setDeadTime(double time) {
		DeadTime = time;
	}

	public void setVOverride(boolean vOverride) {
		VOverride = vOverride;
	}

	public void setVMax(double vmax) {
		VMax = vmax;
	}

	public void setVMin(double vmin) {
		VMin = vmin;
	}

}

package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.Relay;
import com.epri.dss.control.RelayObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.general.TCC_CurveObj;

public class RelayObjImpl extends ControlElemImpl implements RelayObj {

	private int ControlType;

	/* OverCurrent Relay */
	private TCC_CurveObj PhaseCurve, GroundCurve;

	private double PhaseTrip, GroundTrip, PhaseInst, GroundInst;

	private double[] RecloseIntervals;
	private int NumReclose;

	private double ResetTime,
		Delay_Time,
		Breaker_time,
		TDPhase, TDGround;

	private String RelayTarget;

	/* Over/Under Voltage Relay */
	// Curves assumed in per unit of base voltage
	private TCC_CurveObj OVcurve, UVCurve;

	private double Vbase,   // line-neut volts base
		kVBase;

	/* 46 Relay  Neg Seq Current */
	private double PickupAmps46,
		PctPickup46,
		BaseAmps46,
		Isqt46;

	/* 47 Relay */
	private double PickupVolts47,
		PctPickup47;

	/* Generic Relay */
	private double OverTrip,
		UnderTrip;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private CktElement MonitoredElement;

	private ControlAction PresentState;

	private int OperationCount;

	private boolean LockedOut,
		ArmedForClose,
		ArmedForOpen,
		PhaseTarget, GroundTarget;

	private double NextTriptime;
	private int LastEventHandle;

	private int CondOffset;  // Offset for monitored terminal

	private Complex[] cBuffer;  // Complexarray buffer

	public RelayObjImpl(DSSClassImpl ParClass, String RelayName) {
		super(ParClass);
		setName(RelayName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // this forces allocation of terminals and conductors in base class


		this.ElementName   = "";
		setControlledElement(null);
		this.ElementTerminal = 0;  // TODO Check zero based indexing

		this.MonitoredElementName = "";
		this.MonitoredElementTerminal = 0;
		this.MonitoredElement = null;

		this.RelayTarget = "";

		this.PhaseCurve     = null;
		this.GroundCurve    = null;
		this.OVcurve        = null;
		this.UVCurve        = null;
		this.PhaseTrip      = 1.0;
		this.GroundTrip     = 1.0;
		this.TDPhase        = 1.0;
		this.TDGround       = 1.0;
		this.PhaseInst      = 0.0;
		this.GroundInst     = 0.0;
		this.ResetTime      = 15.0;
		this.NumReclose     = 3;
		this.RecloseIntervals = null;

		this.RecloseIntervals = (double[]) Utilities.resizeArray(this.RecloseIntervals, 4);  // fixed allocation of 4
		this.RecloseIntervals[0] = 0.5;
		this.RecloseIntervals[1] = 2.0;
		this.RecloseIntervals[2] = 2.0;

		this.PresentState = ControlAction.CLOSE;

		this.Isqt46 = 1.0;
		this.BaseAmps46 = 100.0;
		this.PctPickup46 = 20.0;
		this.PickupAmps46 = this.BaseAmps46 * this.PctPickup46 * 0.01;

		this.PctPickup47 = 2.0;

		this.OverTrip  = 1.2;
		this.UnderTrip = 0.8;

		this.OperationCount   = 1;
		this.LockedOut        = false;
		this.ArmedForOpen     = false;
		this.ArmedForClose    = false;
		this.PhaseTarget      = false;
		this.GroundTarget     = false;

		this.NextTriptime     = -1.0;  // not set to trip

		this.cBuffer = null; // Complex buffer

		this.DSSObjType = ParClass.getDSSClassType();  //CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int DevIndex = Utilities.getCktElementIndex(MonitoredElementName); // Global function
		if (DevIndex >= 0) {
			MonitoredElement = Globals.getActiveCircuit().getCktElements().get(DevIndex);
			nPhases = MonitoredElement.getNPhases();  // Force number of phases to be same
			if (MonitoredElementTerminal > MonitoredElement.getNTerms()) {
				Globals.doErrorMsg("Relay: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 384);
			} else {
				// Sets name of i-th terminal's connected bus in Relay's buslist
				setBus(1, MonitoredElement.getBus(MonitoredElementTerminal));
				// Allocate a buffer big enough to hold everything from the monitored element
				cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
				CondOffset = (MonitoredElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling

				switch (ControlType) {
				case Relay.GENERIC:
					if ((MonitoredElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PC_ELEMENT) {
						Globals.doSimpleMsg("Relay "+getName()+": Monitored element for generic relay is not a PC Element.", 385);
					} else {
						PCElement pElem = (PCElement) MonitoredElement;
						MonitorVarIndex = pElem.lookupVariable(MonitorVariable);
						if (MonitorVarIndex < 0)
							Globals.doSimpleMsg("Relay "+getName()+": Monitor variable \""+MonitorVariable+"\" does not exist.", 386);
					}
					break;
				}
			}
		}

		/* Check for existence of controlled element */
		DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {
			// Both CktElement and monitored element must already exist
			setControlledElement(Globals.getActiveCircuit().getCktElements().get(DevIndex));
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Make the 1 st terminal active
			if (getControlledElement().getConductorClosed(0)) {  // Check state of phases of active terminal
				PresentState = ControlAction.CLOSE;
				LockedOut = false;
				OperationCount = 1;
				ArmedForOpen = false;
			} else {
				PresentState = ControlAction.OPEN;
				LockedOut = true;
				OperationCount = NumReclose + 1;
				ArmedForClose = false;
			}
		} else {
			setControlledElement(null);  // element not found
			Globals.doErrorMsg("Relay: \"" + getName() + "\"", "CktElement Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 387);
		}

		/* Misc stuff */

		PickupAmps46 = BaseAmps46 * PctPickup46 * 0.01;

		switch (nPhases) {
		case 1:
			Vbase = kVBase * 1000.0;
			break;
		default:
			Vbase = kVBase / DSSGlobals.SQRT3 * 1000.0;
			break;
		}

		PickupVolts47 = Vbase * PctPickup47 * 0.01;
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (MonitoredElement != null) {
			nPhases = MonitoredElement.getNPhases();
			nConds = nPhases;
			setBus(1, MonitoredElement.getBus(ElementTerminal));
			// Allocate a buffer big enough to hold everything from the monitored element
			cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
			CondOffset = (ElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
		}

		switch (nPhases) {
		case 1:
			Vbase = kVBase * 1000.0;
			break;
		default:
			Vbase = kVBase / DSSGlobals.SQRT3 * 1000.0;
			break;
		}

		PickupVolts47 = Vbase * PctPickup47 * 0.01;

		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// Leave YPrims as null and they will be ignored.
		// Yprim is zeroed when created.  Leave it as is.
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

		getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal of CktElement to terminal 1

		if (Code == ControlAction.OPEN.code()) {
			switch (PresentState) {
			case CLOSE:
				if (ArmedForOpen) {
					// ignore if we became disarmed in meantime
					getControlledElement().setConductorClosed(0, false);   // Open all phases of active terminal
					if (OperationCount > NumReclose) {
						LockedOut = true;
						Utilities.appendToEventLog("Relay."+getName(), "Opened on "+RelayTarget+" & Locked Out ");
					}
				} else {
					Utilities.appendToEventLog("Relay."+getName(), "Opened");
				}
				if (PhaseTarget) Utilities.appendToEventLog(" ", "Phase Target");
				if (GroundTarget) Utilities.appendToEventLog(" ", "Ground Target");
				ArmedForOpen = false;
				break;
			}
		} else if (Code == ControlAction.CLOSE.code()) {
			switch (PresentState) {
			case OPEN:
				if (ArmedForClose && !LockedOut) {
					getControlledElement().setConductorClosed(0, true);    // Close all phases of active terminal
					OperationCount += 1;
					Utilities.appendToEventLog("Relay."+getName(), "Closed");
					ArmedForClose = false;
				}
				break;
			}
		} else if (Code == ControlAction.CTRL_RESET.code()) {
			switch (PresentState) {
			case CLOSE:
				if (!ArmedForOpen)
					OperationCount = 1;  // Don't reset if we just rearmed
				break;
			}
		}
	}

	// FIXME Private method in OpenDSS
	public void interpretRelayAction(String Action) {

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal
			switch (Action.toLowerCase().charAt(0)) {
			case 'o':
				getControlledElement().setConductorClosed(0, false);  // Open all phases of active terminal
				LockedOut = true;
				OperationCount = NumReclose + 1;
				break;
			case 't':
				getControlledElement().setConductorClosed(0, false);  // Open all phases of active terminal
				LockedOut = true;
				OperationCount = NumReclose + 1;
				break;
			case 'c':
				getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
				LockedOut = false;
				OperationCount = 1;
				break;
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

			switch (ControlType) {
			case Relay.CURRENT:
				overcurrentLogic();  /* Current */
				break;
			case Relay.VOLTAGE:
				voltageLogic();   /* Reclosing Voltage Relay - definite time */
				break;
			case Relay.REVPOWER:
				revPowerLogic();  /* one shot to lockout */
				break;
			case Relay.NEGCURRENT:
				negSeq46Logic();  /* one shot to lockout */
				break;
			case Relay.NEGVOLTAGE:
				negSeq47Logic();  /* one shot to lockout */
				break;
			case Relay.GENERIC:
				genericLogic();   /* one shot to lockout */
				break;
			}
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue( getParentClass().getPropertyIdxMap()[i] ));

		if (Complete)
		F.println();
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result = "";

		switch (getParentClass().getPropertyIdxMap()[Index]) {
		case 13:
			Result = "(";
			if (NumReclose == 0) {
				Result = Result + "NONE";
			} else {
				for (int i = 0; i < NumReclose; i++)
					Result = Result + String.format("%-g, " , RecloseIntervals[i]);
				Result = Result + ")";
			}
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}
		return Result;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {

		PresentState   = ControlAction.CLOSE;
		OperationCount = 1;
		LockedOut      = false;
		ArmedForOpen   = false;
		ArmedForClose  = false;
		PhaseTarget    = false;
		GroundTarget   = false;

		NextTriptime   = -1.0;  // not set to trip

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal
			getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "";   //"element";
		PropertyValue[1] = "1";  //"terminal";
		PropertyValue[2] = "";
		PropertyValue[3] = "1";  //"terminal";
		PropertyValue[4] = "current";
		PropertyValue[5] = "";
		PropertyValue[6] = "";
		PropertyValue[7] = "1.0";
		PropertyValue[8] = "1.0";
		PropertyValue[9] = "0.0";
		PropertyValue[10] = "0.0";
		PropertyValue[11] = "15";
		PropertyValue[12] = "4";
		PropertyValue[13] = "(0.5, 2.0, 2.0)";
		PropertyValue[14] = "";
		PropertyValue[15] = "";
		PropertyValue[16] = "0.0";
		PropertyValue[17] = "0.0";
		PropertyValue[18] = "";
		PropertyValue[19] = "";
		PropertyValue[20] = "20";
		PropertyValue[21] = "1";
		PropertyValue[22] = "100";
		PropertyValue[23] = "0";
		PropertyValue[24] = "2";
		PropertyValue[25] = "1.2";
		PropertyValue[26] = "0.8";
		PropertyValue[27] = "1.0";
		PropertyValue[28] = "1.0";

		super.initPropertyValues(Relay.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public void interpretRelayType(String S) {

		switch (S.toLowerCase().charAt(0)) {
		case 'c':
			ControlType = Relay.CURRENT;
			break;
		case'v':
			ControlType = Relay.VOLTAGE;
			break;
		case'r':
			ControlType = Relay.REVPOWER;
			break;
		case'4':
			switch (S.charAt(1)) {
			case '6':
				ControlType = Relay.NEGCURRENT;
				break;
			case '7':
				ControlType = Relay.NEGVOLTAGE;
				break;
			}
		case '8':
			ControlType = Relay.GENERIC;
			break;
		default:
			ControlType = Relay.CURRENT;
			break;
		}

		/* Set Definite Time Defaults */
		switch (S.toLowerCase().charAt(0)) {
		case 'c':
			Delay_Time = 0.0;
			break;
		case 'v':
			Delay_Time = 0.0;
			break;
		case 'r':
			Delay_Time = 0.1;
			break;
		case '4':
			Delay_Time = 0.1;
			break;
		case '8':
			Delay_Time = 0.1;
			break;
		default:
			Delay_Time = 0.0;
			break;
		}

		PropertyValue[23] = String.format("%-.g", Delay_Time);
	}

	/**
	 * Generic relays only work on PC Elements With control terminals.
	 */
	private void genericLogic() {
		PCElement pElem = (PCElement) MonitoredElement;
		double VarValue = pElem.getVariable(MonitorVarIndex);

		/* Check for trip */
		if ((VarValue >  OverTrip) || (VarValue < UnderTrip)) {
			if (!ArmedForOpen) {  // push the trip operation and arm to trip
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				RelayTarget = pElem.variableName(MonitorVarIndex);
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + Delay_Time + Breaker_time, ControlAction.OPEN, 0, this);
				OperationCount = NumReclose + 1;  // force a lockout
				ArmedForOpen = true;
			}
		} else {
			/* Within bounds */
			/* Less than pickup value: reset if armed */
			if (ArmedForOpen) {  // We became unarmed, so reset and disarm
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
				ArmedForOpen = false;
			}
		}
	}

	/**
	 * Negative Sequence Current Relay.
	 * Patterned after Basler relay.
	 */
	private void negSeq46Logic() {
		double NegSeqCurrentMag, TripTime;
		int iOffset;
		Complex[] I012 = new Complex[3];

		MonitoredElement.setActiveTerminalIdx(MonitoredElementTerminal);
		MonitoredElement.getCurrents(cBuffer);
		iOffset = (MonitoredElementTerminal - 1) * MonitoredElement.getNConds();  // offset for active terminal
		MathUtil.phase2SymComp(cBuffer[iOffset + 1], I012);
		NegSeqCurrentMag = I012[2].abs();
		if (NegSeqCurrentMag >= PickupAmps46) {
			if (!ArmedForOpen) {  // push the trip operation and arm to trip
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				RelayTarget = "-Seq Curr";
				/* simple estimate of trip time assuming current will be constant */
				if (Delay_Time > 0.0) {
					TripTime = Delay_Time;
				} else {
					TripTime = Isqt46 / Math.pow(NegSeqCurrentMag / BaseAmps46, 2);  // Sec
				}
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + TripTime + Breaker_time, ControlAction.OPEN, 0, this);
				OperationCount = NumReclose + 1;  // force a lockout
				ArmedForOpen = true;
			}
		} else {
			/* Less Than pickup value: reset if armed */
			if (ArmedForOpen) {  // We became unarmed, so reset and disarm
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
				ArmedForOpen = false;
			}
		}
	}

	private void overcurrentLogic() {
		int i;
		double Cmag;
		Complex Csum;

		double GroundTime, PhaseTime, TripTime, TimeTest;

		if (PresentState == ControlAction.CLOSE) {
			TripTime = -1.0;
			GroundTime = -1.0;
			PhaseTime = -1.0;  /* No trip */

			// Check largest current of all phases of monitored element
			MonitoredElement.getCurrents(cBuffer);

			/* Check Ground Trip, if any */
			if (((GroundCurve != null) || (Delay_Time > 0.0)) && (GroundTrip > 0.0)) {
				Csum = Complex.ZERO;
				for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++)  // TODO Check zero based indexing
					Csum = Csum.add(cBuffer[i]);
				Cmag  = Csum.abs();
				if ((GroundInst > 0.0) && (Cmag >= GroundInst) && (OperationCount == 1)) {
					GroundTime = 0.01 + Breaker_time;  // Inst trip on first operation
				} else {
					if (Delay_Time > 0.0) {  // Definite Time Ground Relay
						if (Cmag >= GroundTrip) {
							GroundTime = Delay_Time;
						} else {
							GroundTime = -1.0;
						}
					} else {
						GroundTime = TDGround * GroundCurve.getTCCTime(Cmag / GroundTrip);
					}
				}
			}

			if (GroundTime > 0.0) {
				TripTime = GroundTime;
				GroundTarget = true;
			}

			// If GroundTime > 0 then we have a ground trip

			/* Check Phase Trip, if any */

			if (((PhaseCurve != null) || (Delay_Time > 0.0)) && (PhaseTrip > 0.0)) {
				for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++) {  // TODO Check zero based indexing
					Cmag = cBuffer[i].abs();
					if ((PhaseInst > 0.0) && (Cmag >= PhaseInst) && (OperationCount == 1)) {
						PhaseTime = 0.01 + Breaker_time;  // Inst trip on first operation
						break;  /* if Inst, no sense checking other phases */
					} else {
						if (Delay_Time > 0.0) {  // Definite Time Phase Relay
							if (Cmag >= PhaseTrip) {
								TimeTest = Delay_Time;
							} else {
								TimeTest = -1.0;
							}
						} else {
							TimeTest = TDPhase * PhaseCurve.getTCCTime(Cmag / PhaseTrip);
						}
						if (TimeTest > 0.0) {
							if (PhaseTime < 0.0) {
								PhaseTime = TimeTest;
							} else {
								PhaseTime = Math.min(PhaseTime, TimeTest);
							}
						}
					}
				}
			}

			// If PhaseTime > 0 then we have a phase trip
			if (PhaseTime > 0.0) {
				PhaseTarget = true;
				if (TripTime > 0.0) {
					TripTime = Math.min(TripTime, PhaseTime);
				} else {
					TripTime = PhaseTime;
				}
			}

			if (TripTime > 0.0) {
				if (!ArmedForOpen) {
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
					// Then arm for an open operation
					RelayTarget = "";
					if (PhaseTime > 0.0)
						RelayTarget = RelayTarget + "Ph";
					if (GroundTime > 0.0)
						RelayTarget = RelayTarget + " Gnd";
					LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + TripTime + Breaker_time, ControlAction.OPEN, 0, this);
					if (OperationCount <= NumReclose)
						LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + TripTime + Breaker_time + RecloseIntervals[OperationCount], ControlAction.CLOSE, 0, this);
					ArmedForOpen = true;
					ArmedForClose = true;
				}
			} else {
				if (ArmedForOpen) {
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
					// If current dropped below pickup, disarm trip and set for reset
					LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
					ArmedForOpen  = false;
					ArmedForClose = false;
					PhaseTarget   = false;
					GroundTarget  = false;
				}
			}
		}
	}

	private void revPowerLogic() {

		//----MonitoredElement.ActiveTerminalIdx = MonitoredElementTerminal;
		Complex S = MonitoredElement.getPower(MonitoredElementTerminal);
		if (S.getReal() < 0.0) {
			if (Math.abs(S.getReal()) > PhaseInst * 1000.0) {
				if (!ArmedForOpen) {  // push the trip operation and arm to trip
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
					RelayTarget = "Rev P";
					LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + Delay_Time +  Breaker_time, ControlAction.OPEN, 0, this);
					OperationCount = NumReclose + 1;  // force a lockout
					ArmedForOpen = true;
				}
			} else {
				if (ArmedForOpen) {  // We became unarmed, so reset and disarm
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
					LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
					ArmedForOpen = false;
				}
			}
		}
	}

	private void voltageLogic() {
		int i;
		double Vmax, Vmin, Vmag = 0;
		double OVTime, UVTime, TripTime;

		if (!LockedOut) {
			/* *** Fix so that fastest trip time applies *** */
			MonitoredElement.getTermVoltages(MonitoredElementTerminal, cBuffer);

			Vmin = 1.e50;
			Vmax = 0.0;
			for (i = 0; i < MonitoredElement.getNPhases(); i++)
				Vmag = cBuffer[i].abs();
			if (Vmag > Vmax) Vmax = Vmag;
			if (Vmag < Vmin) Vmin = Vmag;

			/* Convert to Per Unit */
			Vmax = Vmax / Vbase;
			Vmin = Vmin / Vbase;

			if (PresentState == ControlAction.CLOSE) {
				TripTime = -1.0;
				OVTime = -1.0;
				UVTime = -1.0;

				/* Check OverVoltage Trip, if any */
				if (OVcurve != null) OVTime = OVcurve.getOVTime(Vmax);

				if (OVTime > 0.0)
					TripTime = OVTime;
				// If OVTime > 0 then we have a OV trip

				/* Check UV Trip, if any */
				if (UVCurve != null)
					UVTime = UVCurve.getUVTime(Vmin);

				// If UVTime > 0 then we have a UV trip
				if (UVTime > 0.0) {
					if (TripTime > 0.0) {
						TripTime = Math.min(TripTime, UVTime);  // Min of UV or OV time
					} else {
						TripTime = UVTime;
					}
				}

				if (TripTime > 0.0) {
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

					if (ArmedForOpen && ((ckt.getSolution().getDynaVars().t + TripTime + Breaker_time) < NextTriptime)) {
						ckt.getControlQueue().delete(LastEventHandle);  // Delete last event from Queue
						ArmedForOpen = false;  // force it to go through next IF
					}

					if (!ArmedForOpen) {
						// Then arm for an open operation
						if (TripTime == UVTime) {
							if (TripTime == OVTime) {
								RelayTarget = "UV + OV";
							} else {
								RelayTarget = "UV";
							}
						} else {
							RelayTarget = "OV";
						}

						NextTriptime = ckt.getSolution().getDynaVars().t + TripTime + Breaker_time;
						LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), NextTriptime, ControlAction.OPEN, 0, this);
						ArmedForOpen = true;
					}
				} else {
					if (ArmedForOpen) {
						Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
						// If voltage dropped below pickup, disarm trip and set for reset
						ckt.getControlQueue().delete(LastEventHandle);  // Delete last event from Queue
						NextTriptime = -1.0;
						LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
						ArmedForOpen = false;
					}
				}
			} else {
				/* Present state is Open, Check for Voltage and then set reclose interval */
				if (OperationCount <= NumReclose) {
					if (!ArmedForClose) {
						if (Vmax > 0.9) {
							Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
							// OK if voltage > 90%
							LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + RecloseIntervals[OperationCount], ControlAction.CLOSE, 0, this);
							ArmedForClose = true;
						}
					} else {
						/* Armed, but check to see if voltage dropped before it reclosed and cancel action */
						if (Vmax < 0.9)
							ArmedForClose = false;
					}
				}
			}
		}
	}

	/**
	 * Neg Seq voltage Relay
	 */
	private void negSeq47Logic() {
		double NegSeqVoltageMag;
		Complex[] V012 = new Complex[3];

		MonitoredElement.getTermVoltages(MonitoredElementTerminal, cBuffer);
		MathUtil.phase2SymComp(cBuffer, V012);  // Phase to symmetrical components
		NegSeqVoltageMag = V012[2].abs();
		if (NegSeqVoltageMag >= PickupVolts47) {
			if (!ArmedForOpen) {  // push the trip operation and arm to trip
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				RelayTarget = "-Seq V";
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + Delay_Time + Breaker_time, ControlAction.OPEN, 0, this);
				OperationCount = NumReclose + 1;  // force a lockout
				ArmedForOpen = true;
			}
		} else {
			/* Less than pickup value: reset if armed */
			if (ArmedForOpen) {  // We became unarmed, so reset and disarm
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				LastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
				ArmedForOpen = false;
			}
		}
	}

	// FIXME Private members in OpenDSS

	public int getControlType() {
		return ControlType;
	}

	public void setControlType(int controlType) {
		ControlType = controlType;
	}

	public TCC_CurveObj getPhaseCurve() {
		return PhaseCurve;
	}

	public void setPhaseCurve(TCC_CurveObj phaseCurve) {
		PhaseCurve = phaseCurve;
	}

	public TCC_CurveObj getGroundCurve() {
		return GroundCurve;
	}

	public void setGroundCurve(TCC_CurveObj groundCurve) {
		GroundCurve = groundCurve;
	}

	public double getPhaseTrip() {
		return PhaseTrip;
	}

	public void setPhaseTrip(double phaseTrip) {
		PhaseTrip = phaseTrip;
	}

	public double getGroundTrip() {
		return GroundTrip;
	}

	public void setGroundTrip(double groundTrip) {
		GroundTrip = groundTrip;
	}

	public double getPhaseInst() {
		return PhaseInst;
	}

	public void setPhaseInst(double phaseInst) {
		PhaseInst = phaseInst;
	}

	public double getGroundInst() {
		return GroundInst;
	}

	public void setGroundInst(double groundInst) {
		GroundInst = groundInst;
	}

	public double[] getRecloseIntervals() {
		return RecloseIntervals;
	}

	public void setRecloseIntervals(double[] recloseIntervals) {
		RecloseIntervals = recloseIntervals;
	}

	public int getNumReclose() {
		return NumReclose;
	}

	public void setNumReclose(int numReclose) {
		NumReclose = numReclose;
	}

	public double getResetTime() {
		return ResetTime;
	}

	public void setResetTime(double resetTime) {
		ResetTime = resetTime;
	}

	public double getDelay_Time() {
		return Delay_Time;
	}

	public void setDelay_Time(double delay_Time) {
		Delay_Time = delay_Time;
	}

	public double getBreaker_time() {
		return Breaker_time;
	}

	public void setBreaker_time(double breaker_time) {
		Breaker_time = breaker_time;
	}

	public double getTDPhase() {
		return TDPhase;
	}

	public void setTDPhase(double tDPhase) {
		TDPhase = tDPhase;
	}

	public double getTDGround() {
		return TDGround;
	}

	public void setTDGround(double tDGround) {
		TDGround = tDGround;
	}

	public String getRelayTarget() {
		return RelayTarget;
	}

	public void setRelayTarget(String relayTarget) {
		RelayTarget = relayTarget;
	}

	public TCC_CurveObj getOVcurve() {
		return OVcurve;
	}

	public void setOVcurve(TCC_CurveObj oVcurve) {
		OVcurve = oVcurve;
	}

	public TCC_CurveObj getUVCurve() {
		return UVCurve;
	}

	public void setUVCurve(TCC_CurveObj uVCurve) {
		UVCurve = uVCurve;
	}

	public double getVbase() {
		return Vbase;
	}

	public void setVbase(double vbase) {
		Vbase = vbase;
	}

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getPickupAmps46() {
		return PickupAmps46;
	}

	public void setPickupAmps46(double pickupAmps46) {
		PickupAmps46 = pickupAmps46;
	}

	public double getPctPickup46() {
		return PctPickup46;
	}

	public void setPctPickup46(double pctPickup46) {
		PctPickup46 = pctPickup46;
	}

	public double getBaseAmps46() {
		return BaseAmps46;
	}

	public void setBaseAmps46(double baseAmps46) {
		BaseAmps46 = baseAmps46;
	}

	public double getIsqt46() {
		return Isqt46;
	}

	public void setIsqt46(double isqt46) {
		Isqt46 = isqt46;
	}

	public double getPickupVolts47() {
		return PickupVolts47;
	}

	public void setPickupVolts47(double pickupVolts47) {
		PickupVolts47 = pickupVolts47;
	}

	public double getPctPickup47() {
		return PctPickup47;
	}

	public void setPctPickup47(double pctPickup47) {
		PctPickup47 = pctPickup47;
	}

	public double getOverTrip() {
		return OverTrip;
	}

	public void setOverTrip(double overTrip) {
		OverTrip = overTrip;
	}

	public double getUnderTrip() {
		return UnderTrip;
	}

	public void setUnderTrip(double underTrip) {
		UnderTrip = underTrip;
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

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public void setPresentState(ControlAction presentState) {
		PresentState = presentState;
	}

	public int getOperationCount() {
		return OperationCount;
	}

	public void setOperationCount(int operationCount) {
		OperationCount = operationCount;
	}

	public boolean isLockedOut() {
		return LockedOut;
	}

	public void setLockedOut(boolean lockedOut) {
		LockedOut = lockedOut;
	}

	public boolean isArmedForClose() {
		return ArmedForClose;
	}

	public void setArmedForClose(boolean armedForClose) {
		ArmedForClose = armedForClose;
	}

	public boolean isArmedForOpen() {
		return ArmedForOpen;
	}

	public void setArmedForOpen(boolean armedForOpen) {
		ArmedForOpen = armedForOpen;
	}

	public boolean isPhaseTarget() {
		return PhaseTarget;
	}

	public void setPhaseTarget(boolean phaseTarget) {
		PhaseTarget = phaseTarget;
	}

	public boolean isGroundTarget() {
		return GroundTarget;
	}

	public void setGroundTarget(boolean groundTarget) {
		GroundTarget = groundTarget;
	}

	public double getNextTriptime() {
		return NextTriptime;
	}

	public void setNextTriptime(double nextTriptime) {
		NextTriptime = nextTriptime;
	}

	public int getLastEventHandle() {
		return LastEventHandle;
	}

	public void setLastEventHandle(int lastEventHandle) {
		LastEventHandle = lastEventHandle;
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

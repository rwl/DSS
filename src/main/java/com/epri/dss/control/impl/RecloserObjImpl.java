package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.Recloser;
import com.epri.dss.control.RecloserObj;
import com.epri.dss.general.TCC_CurveObj;

public class RecloserObjImpl extends ControlElemImpl implements RecloserObj {

	private TCC_CurveObj PhaseDelayed,
		GroundDelayed,
		PhaseFast,
		GroundFast;

	private double PhaseTrip,
		GroundTrip,
		PhaseInst,
		GroundInst;

	private double[] RecloseIntervals;
	private int NumFast, NumReclose;
	private double ResetTime,
		DelayTime,
		TDGrDelayed,
		TDPhDelayed,
		TDGrFast,
		TDPhFast;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private CktElement MonitoredElement;

	private ControlAction PresentState;

	private int OperationCount;

	private boolean LockedOut,
		ArmedForClose, ArmedForOpen, GroundTarget, PhaseTarget;

	private int CondOffset; // Offset for monitored terminal

	private Complex[] cBuffer;

	public RecloserObjImpl(DSSClassImpl ParClass, String RecloserName) {
		super(ParClass);

		setName(RecloserName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		setNConds(3);
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		this.ElementName       = "";
		setControlledElement(null);
		this.ElementTerminal   = 1;

		this.MonitoredElementName = "";
		this.MonitoredElementTerminal = 1;
		this.MonitoredElement = null;

		this.PhaseFast      = RecloserImpl.getTCC_Curve("a");
		this.PhaseDelayed   = RecloserImpl.getTCC_Curve("d");
		this.GroundFast     = null;
		this.GroundDelayed  = null;

		this.PhaseTrip      = 1.0;
		this.GroundTrip     = 1.0;
		this.PhaseInst      = 0.0;
		this.GroundInst     = 0.0;

		this.TDGrDelayed    = 1.0;
		this.TDPhDelayed    = 1.0;
		this.TDGrFast       = 1.0;
		this.TDPhFast       = 1.0;

		this.ResetTime      = 15.0;
		this.NumReclose     = 3;
		this.NumFast	    = 1;

		this.RecloseIntervals = new double[4];  // fixed allocation of 4
		this.RecloseIntervals[0] = 0.5;
		this.RecloseIntervals[1] = 2.0;
		this.RecloseIntervals[2] = 2.0;


		this.PresentState  = ControlAction.CLOSE;


		this.OperationCount = 1;
		this.LockedOut      = false;
		this.ArmedForOpen   = false;
		this.ArmedForClose  = false;
		this.GroundTarget   = false;
		this.PhaseTarget    = false;


		this.cBuffer = null;  // Complex buffer

		this.DSSObjType = ParClass.getDSSClassType();  // CAP_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		int DevIndex = Utilities.getCktElementIndex(MonitoredElementName);

		if (DevIndex >= 0) {
			MonitoredElement = ckt.getCktElements().get(DevIndex);
			setNPhases(MonitoredElement.getNPhases());  // force number of phases to be same
			if (MonitoredElementTerminal > MonitoredElement.getNTerms()) {
				Globals.doErrorMsg("Recloser: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 392);
			} else {
				// Sets name of i-th terminal's connected bus in Recloser's buslist
				setBus(1, MonitoredElement.getBus(MonitoredElementTerminal));
				// Allocate a buffer bigenough to hold everything from the monitored element
				cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
				CondOffset = (MonitoredElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
			}
		}

		/* Check for existence of Controlled Element */

		DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {  // Both CktElement and monitored element must already exist

			setControlledElement( ckt.getCktElements().get(DevIndex) );

			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Make the 1st terminal active
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
			Globals.doErrorMsg("Recloser: \"" + getName() + "\"", "CktElement Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 393);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (MonitoredElement != null) {
			setNPhases( MonitoredElement.getNPhases() );
			setNConds(nPhases);
			setBus(1, MonitoredElement.getBus(ElementTerminal));
			// Allocate a buffer big enough to hold everything from the monitored element
			cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
			CondOffset = (ElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
		}
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
				if (ArmedForOpen) {  // ignore if we became disarmed in meantime
					getControlledElement().setConductorClosed(0, false);   // Open all phases of active terminal
					if (OperationCount > NumReclose) {
						LockedOut = true;
						Utilities.appendToEventLog("Recloser."+getName(), "Opened, Locked Out");
					} else {
						if (OperationCount > NumFast) {
							Utilities.appendToEventLog("Recloser."+getName(), "Opened, Delayed");
						} else {
							Utilities.appendToEventLog("Recloser."+getName(), "Opened, Fast");
						}
					}
					if (PhaseTarget)
						Utilities.appendToEventLog(" ", "Phase Target");
					if (GroundTarget)
						Utilities.appendToEventLog(" ", "Ground Target");
					ArmedForOpen = false;
				}
				break;
			}

		} else if (Code == ControlAction.CLOSE.code()) {
			switch (PresentState) {
			case OPEN:
				if (ArmedForClose && !LockedOut) {
					getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
					OperationCount += 1;
					Utilities.appendToEventLog("Recloser."+getName(), "Closed");
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
	public void interpretRecloserAction(String Action) {

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal
			switch (Action.toLowerCase().charAt(0)) {
			case 'o':
				getControlledElement().setConductorClosed(0, false);   // Open all phases of active terminal
				LockedOut = true;
				OperationCount = NumReclose + 1;
				break;
			case 't':
				getControlledElement().setConductorClosed(0, false);   // Open all phases of active terminal
				LockedOut = true;
				OperationCount = NumReclose + 1;
				break;
			case 'c':
				getControlledElement().setConductorClosed(0, true);    // Close all phases of active terminal
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
		int i;
		double Cmag;
		Complex Csum;

		TCC_CurveObj GroundCurve, PhaseCurve;
		double Groundtime, PhaseTime, TripTime, TimeTest;
		double TDPhase, TDGround;

		getControlledElement().setActiveTerminalIdx(ElementTerminal);

		if (getControlledElement().getConductorClosed(0)) {      // Check state of phases of active terminal
			PresentState = ControlAction.CLOSE;
		} else {
			PresentState = ControlAction.OPEN;
		}

		if (OperationCount > NumFast) {
			GroundCurve = GroundDelayed;
			PhaseCurve = PhaseDelayed;
			TDGround = TDGrDelayed;
			TDPhase =  TDPhDelayed;
		} else {
			GroundCurve = GroundFast;
			PhaseCurve = PhaseFast;
			TDGround = TDGrFast;
			TDPhase =  TDPhFast;
		}

		if (PresentState == ControlAction.CLOSE) {
			TripTime = -1.0;
			Groundtime = -1.0;
			PhaseTime = -1.0;  /* No trip */

			// Check largest Current of all phases of monitored element
			MonitoredElement.getCurrents(cBuffer);

			/* Check ground trip, if any */
			if (GroundCurve != null) {
				Csum = Complex.ZERO;
				for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++)
					Csum = Csum.add(cBuffer[i]);
				Cmag = Csum.abs();
				if ((GroundInst > 0.0) && (Cmag >= GroundInst) && (OperationCount == 1)) {
					Groundtime = 0.01 + DelayTime;  // Inst trip on first operation
				} else {
					Groundtime = TDGround * GroundCurve.getTCCTime(Cmag / GroundTrip);
				}
			}

			if (Groundtime > 0.0) {
				TripTime = Groundtime;
				GroundTarget = true;
			}

			// If GroundTime > 0 then we have a ground trip

			/* Check phase trip, if any */

			if (PhaseCurve != null) {
				for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++) {
					Cmag =  cBuffer[i].abs();

					if ((PhaseInst > 0.0) && (Cmag >= PhaseInst) && (OperationCount == 1)) {
						PhaseTime = 0.01 + DelayTime;  // Inst trip on first operation
						break;  /* no sense checking other phases */
					} else {
						TimeTest = TDPhase * PhaseCurve.getTCCTime(Cmag / PhaseTrip);
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
					ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + TripTime + DelayTime, ControlAction.OPEN, 0, this);
					if (OperationCount <= NumReclose)
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + TripTime + DelayTime + RecloseIntervals[OperationCount], ControlAction.CLOSE, 0, this);
					ArmedForOpen = true;
					ArmedForClose = true;
				}
			} else {
				if (ArmedForOpen) {
					Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
					// If current dropped below pickup, disarm trip and set for reset.
					ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + ResetTime, ControlAction.CTRL_RESET, 0, this);
					ArmedForOpen = false;
					ArmedForClose = false;
					GroundTarget = false;
					PhaseTarget = false;
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
		String Result = "";
		switch (Index) {
		case 15:
			Result = "(";
			for (int i = 0; i < NumReclose; i++)
				Result = Result + String.format("%-g, ", RecloseIntervals[i]);
			Result = Result + ")";
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
		GroundTarget   = false;
		PhaseTarget    = false;

		if (getControlledElement() != null) {
			getControlledElement().setActiveTerminalIdx(ElementTerminal);  // Set active terminal
			getControlledElement().setConductorClosed(0, true);             // Close all phases of active terminal
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0]  = "";  // "element";
		PropertyValue[1]  = "1"; // "terminal";
		PropertyValue[2]  = "";
		PropertyValue[3]  = "1";  // "terminal";
		PropertyValue[4]  = String.valueOf(NumFast);
		PropertyValue[5]  = "";
		PropertyValue[6]  = "";
		PropertyValue[7]  = "";
		PropertyValue[8]  = "";
		PropertyValue[9]  = "1.0";
		PropertyValue[10] = "1.0";
		PropertyValue[11] = "0";
		PropertyValue[12] = "0";
		PropertyValue[13] = "15";
		PropertyValue[14] = "4";
		PropertyValue[15] = "(0.5, 2.0, 2.0)";
		PropertyValue[16] = "0.0";
		PropertyValue[17] = "";
		PropertyValue[18] = "1.0";
		PropertyValue[19] = "1.0";
		PropertyValue[20] = "1.0";
		PropertyValue[21] = "1.0";

		super.initPropertyValues(Recloser.NumPropsThisClass);
	}

	// FIXME Private members in Open DSS

	public TCC_CurveObj getPhaseDelayed() {
		return PhaseDelayed;
	}

	public void setPhaseDelayed(TCC_CurveObj phaseDelayed) {
		PhaseDelayed = phaseDelayed;
	}

	public TCC_CurveObj getGroundDelayed() {
		return GroundDelayed;
	}

	public void setGroundDelayed(TCC_CurveObj groundDelayed) {
		GroundDelayed = groundDelayed;
	}

	public TCC_CurveObj getPhaseFast() {
		return PhaseFast;
	}

	public void setPhaseFast(TCC_CurveObj phaseFast) {
		PhaseFast = phaseFast;
	}

	public TCC_CurveObj getGroundFast() {
		return GroundFast;
	}

	public void setGroundFast(TCC_CurveObj groundFast) {
		GroundFast = groundFast;
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

	public int getNumFast() {
		return NumFast;
	}

	public void setNumFast(int numFast) {
		NumFast = numFast;
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

	public double getDelayTime() {
		return DelayTime;
	}

	public void setDelayTime(double delayTime) {
		DelayTime = delayTime;
	}

	public double getTDGrDelayed() {
		return TDGrDelayed;
	}

	public void setTDGrDelayed(double tDGrDelayed) {
		TDGrDelayed = tDGrDelayed;
	}

	public double getTDPhDelayed() {
		return TDPhDelayed;
	}

	public void setTDPhDelayed(double tDPhDelayed) {
		TDPhDelayed = tDPhDelayed;
	}

	public double getTDGrFast() {
		return TDGrFast;
	}

	public void setTDGrFast(double tDGrFast) {
		TDGrFast = tDGrFast;
	}

	public double getTDPhFast() {
		return TDPhFast;
	}

	public void setTDPhFast(double tDPhFast) {
		TDPhFast = tDPhFast;
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

	public boolean isGroundTarget() {
		return GroundTarget;
	}

	public void setGroundTarget(boolean groundTarget) {
		GroundTarget = groundTarget;
	}

	public boolean isPhaseTarget() {
		return PhaseTarget;
	}

	public void setPhaseTarget(boolean phaseTarget) {
		PhaseTarget = phaseTarget;
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

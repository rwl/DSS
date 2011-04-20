package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
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

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // This forces allocation of terminals and conductors in base class

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

		int DevIndex = Utilities.getCktElementIndex(MonitoredElementName);  // Global function

		if (DevIndex >= 0) {
			MonitoredElement = ckt.getCktElements().get(DevIndex);
			nPhases = MonitoredElement.getNPhases();       // Force number of phases to be same
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
			nPhases = MonitoredElement.getNPhases();
			nConds = nPhases;
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

	}

	private void interpretRecloserAction(String Action) {

	}

	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {

	}

	/* Reset to initial defined state */
	@Override
	public void reset() {

	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

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

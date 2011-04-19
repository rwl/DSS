package com.epri.dss.control.impl;

import java.io.PrintStream;

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
		CURRENTCONTROL,
		VOLTAGECONTROL,
		KVARCONTROL,
		TIMECONTROL,
		PFCONTROL,
		SRPCONTROL
	}
	
	private CapControlType ControlType;

	private int CTPhase, PTPhase;  // "ALL" is -1

	private double ON_Value,
		OFF_Value,
		PFON_Value,
		PFOFF_Value,
		CTRatio,
		PTRatio,
		ONDelay,
		OFFDelay,
		DeadTime,
		LastOpenTime;

	private boolean VOverride;
	private double Vmax, Vmin;

	private String CapacitorName;
	private CktElement MonitoredElement;
	private CapacitorObj ControlledCapacitor;
	private ControlAction PendingChange;
	private boolean ShouldSwitch;  // True: action is pending
	private boolean Armed;  // Control is armed for switching unless reset
	private ControlAction PresentState;
	private ControlAction InitialState;
	private int ControlActionHandle;
	private int CondOffset; // Offset for monitored terminal

	private Complex[] cBuffer;    // Complexarray buffer

	public CapControlObjImpl(DSSClassImpl ParClass, String CapControlName) {
		super(ParClass);
		setName(CapControlName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // this forces allocation of terminals and conductors
							// in base class

		this.CTPhase = 1;
		this.PTPhase = 1;

		this.PTRatio     = 60.0;
		this.CTRatio     = 60.0;
		this.ControlType = CapControlType.CURRENTCONTROL;
		this.ONDelay     = 15.0;
		this.OFFDelay    = 15.0;
		this.DeadTime    = 300.0;
		this.LastOpenTime = -DeadTime;

		this.ON_Value    = 300.0;
		this.OFF_Value   = 200.0;

		this.PFON_Value    = 0.95;
		this.PFOFF_Value   = 1.05;

		this.VOverride = false;
		this.Vmax      = 126;
		this.Vmin      = 115;

		this.ElementName   = "";
		setControlledElement(null);
		this.ElementTerminal = 1;
		this.CapacitorName = "";
		this.MonitoredElement = null;

		this.PresentState  = ControlAction.CLOSE;

		this.ShouldSwitch = false;
		this.Armed        = false;
		this.PendingChange = ControlAction.NONE;
		this.ControlActionHandle = 0;

		this.cBuffer = null; // Complex buffer

		this.DSSObjType = ParClass.getDSSClassType(); //cap_CONTROL;

		initPropertyValues(0);

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		/* Check for existence of capacitor */

		int DevIndex = Utilities.getCktElementIndex(CapacitorName); // Global function
		if (DevIndex >= 0) {
			// Both capacitor and monitored element must already exist
			setControlledElement(ckt.getCktElements().get(DevIndex));
			ControlledCapacitor = getCapacitor();
			nPhases = getControlledElement().getNPhases();  // Force number of phases to be same   Added 5/21/01  RCD
			nConds  = nPhases;
			getControlledElement().setActiveTerminalIdx(0);  // Make the 1 st terminal active   TODO Check zero based indexing
			// Get control synched up with capacitor

			if (ControlledCapacitor.availableSteps() == ControlledCapacitor.getNumSteps()) {
				getControlledElement().setConductorClosed(0, false);  // TODO Check zero based indexing
			} else {
				getControlledElement().setConductorClosed(0, true);  // TODO Check zero based indexing
			}
			if (getControlledElement().getConductorClosed(0)) {  // Check state of phases of active terminal
				PresentState = ControlAction.CLOSE;
			} else {
				PresentState = ControlAction.OPEN;
			}
		} else {
			setControlledElement(null);  // element not found
			Globals.doErrorMsg("CapControl: \"" + getName() + "\"", "Capacitor Element \""+ CapacitorName + "\" Not Found.",
					"Element must be defined previously.", 361);
		}

		InitialState = PresentState;

		/* Check for existence of monitored element */

		DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {
			MonitoredElement = ckt.getCktElements().get(DevIndex);
			if (ElementTerminal > MonitoredElement.getNTerms() - 1) {  // TODO Check zero based indexing
				Globals.doErrorMsg("CapControl: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.", "Re-specify terminal no.", 362);
			} else {
				// Sets name of i-th terminal's connected bus in CapControl's buslist
				setBus(1, MonitoredElement.getBus(ElementTerminal));  // TODO Check zero based indexing
				
				// Allocate a buffer big enough to hold everything from the monitored element
				cBuffer = (Complex[]) Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
				
				CondOffset = (ElementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
			}
		} else {
			Globals.doSimpleMsg("Monitored Element in CapControl."+getName()+ " does not exist:\""+ElementName+"\"", 363);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled(getControlledElement().isEnabled());
			nPhases = getControlledElement().getNPhases();
			nConds  = nPhases;
		}
		
		if (MonitoredElement != null) {
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
		// Yprim is zeroed when created. Leave it as is.
	}
	
	/**
	 * Get current to control on based on type of control specified.
	 */
	private void getControlCurrent(double ControlCurrent) {
		int i;

		switch (CTPhase) {
		case CapControl.AVGPHASES:
			ControlCurrent = 0.0;  // Get avg of all phases
			for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++)  // TODO Check zero based indexing
				ControlCurrent = ControlCurrent + cBuffer[i].abs();
			ControlCurrent = ControlCurrent / nPhases / CTRatio;
					
		case CapControl.MAXPHASE:
			ControlCurrent = 0.0;  // Get max of all phases
			for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++)
				ControlCurrent = Math.max(ControlCurrent, cBuffer[i].abs());
			ControlCurrent = ControlCurrent / CTRatio;

		case CapControl.MINPHASE:
			ControlCurrent = 1.0e50;  // Get min of all phases
			for (i = (1 + CondOffset); i < (nPhases + CondOffset); i++)
				ControlCurrent = Math.min(ControlCurrent, cBuffer[i].abs());
			ControlCurrent = ControlCurrent / CTRatio;

		default:
			/* Just use one phase because that's what most controls do. */
			ControlCurrent = cBuffer[CTPhase].abs() / CTRatio;  // monitored phase only
		}
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
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete)
			F.println();
	}
	
	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {

		getControlledElement().setActiveTerminalIdx(0);  // Set active terminal of capacitor to terminal 1  TODO Check zero based indexing

		switch (PendingChange) {
		case OPEN:
			switch (ControlledCapacitor.getNumSteps()) {
			case 1:
				if (PresentState == ControlAction.CLOSE) {
					getControlledElement().setConductorClosed(0, false);  // Open all phases of active terminal
				
					Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");
					PresentState = ControlAction.OPEN;
					
					SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
					
					LastOpenTime = sol.getDynaVars().t + 3600.0 * sol.getIntHour();
				}
					
			default:
				if (PresentState == ControlAction.CLOSE)  // Do this only if at least one step is closed
					if (!ControlledCapacitor.subtractStep()) {
						PresentState = ControlAction.OPEN;
						getControlledElement().setConductorClosed(0, false);  // Open all phases of active terminal
						Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Opened**");
					} else {
						Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Down**");
					}
			}
			
		case CLOSE:
			if (PresentState == ControlAction.OPEN) {
				getControlledElement().setConductorClosed(0, true);  // Close all phases of active terminal
				Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Closed**");
				PresentState = ControlAction.CLOSE;
				ControlledCapacitor.addStep();
			} else {
				if (ControlledCapacitor.addStep()) 
					Utilities.appendToEventLog("Capacitor." + getControlledElement().getName(), "**Step Up**");
			}
			
		default:
			/* Do Nothing for NONE if the control has reset */
		}

		ShouldSwitch = false;
		Armed        = false;  // reset control	
	}
	

	private int nextDeltaPhase(int iphs) {
		int Result = iphs + 1;
		if (Result >= nPhases)
			Result = 0;
		return Result;
	}
	/**
	 * Get Voltage used for voltage control based on specified options.
	 */
	private void getControlVoltage(double ControlVoltage) {
		int i;
		
		switch (PTPhase) {
		case CapControl.AVGPHASES:
			ControlVoltage = 0.0;
			for (i = 0; i < MonitoredElement.getNPhases(); i++)
				ControlVoltage = ControlVoltage + cBuffer[i].abs();
			ControlVoltage = ControlVoltage / MonitoredElement.getNPhases() / PTRatio;
					
		case CapControl.MAXPHASE:
			ControlVoltage = 0.0;
			for (i = 0; i < MonitoredElement.getNPhases(); i++)
				ControlVoltage = Math.max(ControlVoltage, cBuffer[i].abs());
			ControlVoltage = ControlVoltage / PTRatio;
			
		case CapControl.MINPHASE:
			ControlVoltage = 1.0E50;
			for (i = 0; i < MonitoredElement.getNPhases(); i++)
				ControlVoltage = Math.min(ControlVoltage, cBuffer[i].abs());
			ControlVoltage = ControlVoltage / PTRatio;

		default:
			/* Just use one phase because that's what most controls do. */
			// Use L-L aB if capacitor is delta connected!!
			CapacitorObj pElem = (CapacitorObj) getControlledElement();
			switch (pElem.getConnection()) {
			case 1:
				ControlVoltage = cBuffer[PTPhase].subtract( cBuffer[nextDeltaPhase(PTPhase)] ).abs() / PTRatio;   // Delta
			default:
				ControlVoltage = cBuffer[PTPhase].abs() / PTRatio;     // Wye - Default
			}
		}	
	}
	

	private static double PF1to2(Complex Spower) {  // return PF in range of 1 to 2
		double Result, Sabs = Spower.abs();
		if (Sabs != 0.0) {
			Result = Math.abs(Spower.getReal()) / Sabs;
		} else {
			Result = 1.0;  // default to unity
		}
		if (Spower.getImaginary() < 0.0)
			Result = 2.0 - Result;
		return Result;
	}
	/**
	 * Sample control quantities and set action times in control queue.
	 */
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
	
	public CapacitorObj getCapacitor() {
		return null;
	}
	
	private double normalizeToTOD(int h, double sec) {
		return 0.0;
	}
	
	public void setPendingChange(ControlAction Value) {
		
	}
	
	public ControlAction getPendingChange() {
		return PendingChange;
	}
	
	public CapControlType getControlType() {
		return ControlType;
	}

	public void setControlType(CapControlType controlType) {
		ControlType = controlType;
	}

	public double getON_Value() {
		return ON_Value;
	}

	public double getOFF_Value() {
		return OFF_Value;
	}

	public double getPFON_Value() {
		return PFON_Value;
	}

	public double getPFOFF_Value() {
		return PFOFF_Value;
	}

	public double getCTRatio() {
		return CTRatio;
	}

	public double getPTRatio() {
		return PTRatio;
	}

	public double getONDelay() {
		return ONDelay;
	}

	public double getOFFDelay() {
		return OFFDelay;
	}

	public double getDeadTime() {
		return DeadTime;
	}

	public boolean isVOverride() {
		return VOverride;
	}

	public double getVmax() {
		return Vmax;
	}

	public double getVmin() {
		return Vmin;
	}
	
	// FIXME Private properties in OpenDSS

	public int getCTPhase() {
		return CTPhase;
	}

	public void setCTPhase(int cTPhase) {
		CTPhase = cTPhase;
	}

	public int getPTPhase() {
		return PTPhase;
	}

	public void setPTPhase(int pTPhase) {
		PTPhase = pTPhase;
	}

	public double getLastOpenTime() {
		return LastOpenTime;
	}

	public void setLastOpenTime(double lastOpenTime) {
		LastOpenTime = lastOpenTime;
	}

	public String getCapacitorName() {
		return CapacitorName;
	}

	public void setCapacitorName(String capacitorName) {
		CapacitorName = capacitorName;
	}

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public CapacitorObj getControlledCapacitor() {
		return ControlledCapacitor;
	}

	public void setControlledCapacitor(CapacitorObj controlledCapacitor) {
		ControlledCapacitor = controlledCapacitor;
	}

	public boolean isShouldSwitch() {
		return ShouldSwitch;
	}

	public void setShouldSwitch(boolean shouldSwitch) {
		ShouldSwitch = shouldSwitch;
	}

	public boolean isArmed() {
		return Armed;
	}

	public void setArmed(boolean armed) {
		Armed = armed;
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public void setPresentState(ControlAction presentState) {
		PresentState = presentState;
	}

	public ControlAction getInitialState() {
		return InitialState;
	}

	public void setInitialState(ControlAction initialState) {
		InitialState = initialState;
	}

	public int getControlActionHandle() {
		return ControlActionHandle;
	}

	public void setControlActionHandle(int controlActionHandle) {
		ControlActionHandle = controlActionHandle;
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

	public void setON_Value(double oN_Value) {
		ON_Value = oN_Value;
	}

	public void setOFF_Value(double oFF_Value) {
		OFF_Value = oFF_Value;
	}

	public void setPFON_Value(double pFON_Value) {
		PFON_Value = pFON_Value;
	}

	public void setPFOFF_Value(double pFOFF_Value) {
		PFOFF_Value = pFOFF_Value;
	}

	public void setCTRatio(double cTRatio) {
		CTRatio = cTRatio;
	}

	public void setPTRatio(double pTRatio) {
		PTRatio = pTRatio;
	}

	public void setONDelay(double oNDelay) {
		ONDelay = oNDelay;
	}

	public void setOFFDelay(double oFFDelay) {
		OFFDelay = oFFDelay;
	}

	public void setDeadTime(double deadTime) {
		DeadTime = deadTime;
	}

	public void setVOverride(boolean vOverride) {
		VOverride = vOverride;
	}

	public void setVmax(double vmax) {
		Vmax = vmax;
	}

	public void setVmin(double vmin) {
		Vmin = vmin;
	}

}

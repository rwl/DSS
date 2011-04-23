package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.StorageController;
import com.epri.dss.control.StorageControllerObj;
import com.epri.dss.conversion.Storage;
import com.epri.dss.conversion.StorageObj;
import com.epri.dss.general.LoadShapeObj;

public class StorageControllerObjImpl extends ControlElemImpl implements StorageControllerObj {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private double kWTarget,
		pctkWBand,
		HalfkWBand,
		PFTarget,    // Range on this is 0..2 where 1..2 is leading
		TotalWeight;
	private double HalfPFBand;
	private double PFBand;
	private double kWNeeded;
	private int FleetSize;
	private int FleetState;

	private List<String> StorageNameList;
	private List<Object> FleetPointerList;
	private double[] Weights;

	private boolean ElementListSpecified;

	private int DischargeMode;
	private int ChargeMode;
	private double DischargeTriggerTime;
	private double ChargeTriggerTime;
	private double pctKWRate;
	private double pctkvarRate;
	private double pctChargeRate;
	private double pctFleetReserve;
	private boolean FleetListChanged;
	private boolean ChargingAllowed;
	private boolean ShowEventLog;
	private boolean DispatchVars;
	private boolean DischargeTriggeredByTime;
	private boolean DischargeInhibited;
	private boolean OutOfOomph;
	private int InhibitHrs;

	private double TotalKWCapacity;
	private double TotalKWhCapacity;

	private String YearlyShape;  // ="fixed" means no variation  on all the time
	private LoadShapeObj YearlyShapeObj;  // Shape for this Storage element
	private String DailyShape;  // Daily (24 HR) Storage element shape
	private LoadShapeObj DailyShapeObj;  // Daily Storage element Shape for this load
	private String DutyShape;  // Duty cycle load shape for changes typically less than one hour
	private LoadShapeObj DutyShapeObj;  // Shape for this Storage element

	private Complex LoadShapeMult;

	private CktElement MonitoredElement;

	public StorageControllerObjImpl(DSSClassImpl ParClass, String StorageControllerName) {
		super(ParClass);
		setName(StorageControllerName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // this forces allocation of terminals and conductors

		this.ElementName       = "";
		setControlledElement(null);  // not used in this control
		this.ElementTerminal   = 1;
		this.MonitoredElement  = null;

		this.StorageNameList  = new ArrayList<String>();
		this.Weights          = null;
		this.FleetPointerList = new ArrayList<Object>(20);  // Default size and increment
		this.FleetSize        = 0;
		this.FleetState       = Storage.STORE_IDLING;
		this.kWTarget         = 8000.0;
		this.pctkWBand        = 2.0;
		this.TotalWeight      = 1.0;
		this.HalfkWBand       = this.pctkWBand / 200.0 * this.kWTarget;
		this.PFTarget         = 0.96;
		this.PFBand           = 0.04;
		this.HalfPFBand       = this.PFBand / 2.0;
		this.kWNeeded         = 0.0;

		this.DischargeMode = StorageController.MODEPEAKSHAVE;
		this.ChargeMode    = StorageController.MODETIME;

		this.DischargeTriggerTime = -1.0;  // disabled
		this.ChargeTriggerTime    = 2.0;   // 2 AM
		this.ElementListSpecified = false;
		this.FleetListChanged     = true;  // force building of list
		this.pctKWRate            = 20.0;
		this.pctkvarRate          = 20.0;
		this.pctChargeRate        = 20.0;
		this.pctFleetReserve      = 25.0;

		this.ShowEventLog         = false;
		this.DispatchVars         = false;
		this.DischargeTriggeredByTime = false;
		this.DischargeInhibited   = false;
		this.OutOfOomph           = false;
		this.InhibitHrs           = 5;   // No. Hours to inhibit discharging after going into charge mode

		initPropertyValues(0);
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(StorageController.propELEMENT, "");
		setPropertyValue(StorageController.propTERMINAL, "1");
		setPropertyValue(StorageController.propKWTARGET, "8000");
		setPropertyValue(StorageController.propKWBAND, "2");
		setPropertyValue(StorageController.propPFTARGET, ".96");
		setPropertyValue(StorageController.propPFBAND, ".04");
		setPropertyValue(StorageController.propELEMENTLIST, "");
		setPropertyValue(StorageController.propWEIGHTS, "");
		setPropertyValue(StorageController.propMODEDISCHARGE, "Follow");
		setPropertyValue(StorageController.propMODECHARGE, "Time");
		setPropertyValue(StorageController.propTIMEDISCHARGETRIGGER, "-1");
		setPropertyValue(StorageController.propTIMECHARGETRIGGER, "2");
		setPropertyValue(StorageController.propRATEKW, "20");
		setPropertyValue(StorageController.propRATEKVAR, "20");
		setPropertyValue(StorageController.propRATECHARGE, "20");
		setPropertyValue(StorageController.propRESERVE, "25");
		setPropertyValue(StorageController.propKWHTOTAL, "");
		setPropertyValue(StorageController.propKWTOTAL, "");
		setPropertyValue(StorageController.propKWACTUAL, "");
		setPropertyValue(StorageController.propKWNEED, "");
		setPropertyValue(StorageController.propPARTICIPATION, "");
		setPropertyValue(StorageController.propYEARLY, "");
		setPropertyValue(StorageController.propDAILY, "");
		setPropertyValue(StorageController.propDUTY, "");
		setPropertyValue(StorageController.propEVENTLOG, "No");
		setPropertyValue(StorageController.propINHIBITTIME, "5");

		super.initPropertyValues(StorageController.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case StorageController.propKWTARGET:
			return String.format("%-.6g", kWTarget);
		case StorageController.propKWBAND:
			return String.format("%-.6g", pctkWBand);
		case StorageController.propPFTARGET:
			return String.format("%-.6g", Utilities.convertPFRange2ToPF(PFTarget));
		case StorageController.propPFBAND:
			return String.format("%-.6g", PFBand);
		case StorageController.propELEMENTLIST:
			return returnElementsList();
		case StorageController.propWEIGHTS:
			return returnWeightsList();
		case StorageController.propMODEDISCHARGE:
			return getModeString(StorageController.propMODEDISCHARGE, DischargeMode);
		case StorageController.propMODECHARGE:
			return getModeString(StorageController.propMODECHARGE, ChargeMode);
		case StorageController.propTIMEDISCHARGETRIGGER:
			return String.format("%.6g", DischargeTriggerTime);
		case StorageController.propTIMECHARGETRIGGER:
			return String.format("%.6g", ChargeTriggerTime);
		case StorageController.propRATEKW:
			return String.format("%-.8g", pctKWRate);
		case StorageController.propRATEKVAR:
			return String.format("%-.8g", pctkvarRate);
		case StorageController.propRATECHARGE:
			return String.format("%-.8g", pctChargeRate);
		case StorageController.propRESERVE:
			return String.format("%-.8g", pctFleetReserve);
		case StorageController.propKWHTOTAL:
			return getkWhTotal(TotalKWhCapacity);
		case StorageController.propKWTOTAL:
			return getkWTotal(TotalKWCapacity);
		case StorageController.propKWHACTUAL:
			return getkWhActual();
		case StorageController.propKWACTUAL:
			return getkWActual();
		case StorageController.propKWNEED:
			return String.format("%-.6g", kWNeeded);
		/*case StorageController.propPARTICIPATION:
			return getPropertyValue(Index);*/
		case StorageController.propYEARLY:
			return YearlyShape;
		case StorageController.propDAILY:
			return DailyShape;
		case StorageController.propDUTY:
			return DutyShape;
		case StorageController.propEVENTLOG:
			if (ShowEventLog) {
				return "Yes";
			} else {
				return "No";
			}
		case StorageController.propVARDISPATCH:
			if (DispatchVars) {
				return "Yes";
			} else {
				return "No";
			}
		case StorageController.propINHIBITTIME:
			return String.format("%d", InhibitHrs);
		default:  // take the generic handler
			return super.getPropertyValue(Index);
		}
	}

	public double getFleetkW() {
		StorageObj pStorage;
		double Result = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Result = Result + pStorage.getPresentkW();
		}
		return Result;
	}

	public double getFleetkWh() {
		StorageObj pStorage;
		double Result = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Result = Result + pStorage.getkWhStored();
		}
		return Result;
	}

	public double getFleetReserveKWh() {
		StorageObj pStorage;
		double Result = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Result = Result + pStorage.getkWhReserve();
		}
		return Result;
	}

	/**
	 * Recalculate critical element values after changes have been made.
	 */
	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Check for existence of monitored element */
		int DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {
			MonitoredElement = Globals.getActiveCircuit().getCktElements().get(DevIndex);
			if (ElementTerminal > MonitoredElement.getNTerms()) {
				Globals.doErrorMsg("StorageController: \"" + getName() + "\"",
						"Terminal no. \"" +"\" Does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				nPhases = MonitoredElement.getNPhases();
				nConds  = nPhases;
				// Sets name of i-th terminal's connected bus in StorageController's buslist
				setBus(1, MonitoredElement.getBus(ElementTerminal));
			}
		} else {
			Globals.doSimpleMsg("Monitored Element in StorageController."+getName()+ " Does not exist:\""+ElementName+"\"", 372);
		}

		if (FleetListChanged)
			if (!makeFleetList())
				Globals.doSimpleMsg("No unassigned storage elements found to assign to StorageController."+getName(), 37201);

		getkWTotal(TotalKWCapacity);
		getkWhTotal(TotalKWhCapacity);

		if (FleetSize > 0) {
			setFleetToExternal();
			setAllFleetValues();
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
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// Leave YPrims as null and they will be ignored/
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

	private String getkWActual() {
		StorageObj pStorage;
		double Sum = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Sum = Sum + pStorage.getPresentkW();
		}
		return String.format("%-.8g", Sum);
	}

	private String getkWhActual() {
		StorageObj pStorage;
		double Sum = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Sum = Sum + pStorage.getkWhStored();
		}
		return String.format("%-.8g", Sum);
	}

	private String getkWhTotal(double Sum) {
		StorageObj pStorage;
		Sum = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Sum = Sum + pStorage.getkWhRating();
		}
		return String.format("%-.8g", Sum);
	}

	private String getkWTotal(double Sum) {
		StorageObj pStorage;
		Sum = 0;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			Sum = Sum + pStorage.getkWrating();
		}
		return String.format("%-.8g", Sum);
	}

	private String getModeString(int Opt, int Mode) {
		switch (Opt) {
		case StorageController.propMODEDISCHARGE:
			switch (Mode) {
			case StorageController.MODEFOLLOW:
				return "Follow";
			case StorageController.MODELOADSHAPE:
				return "Loadshape";
			case StorageController.MODESUPPORT:
				return "Support";
			case StorageController.MODETIME:
				return "Time";
			case StorageController.MODEPEAKSHAVE:
				return "Peakshave";
			default:
				return "UNKNOWN";
			}
		case StorageController.propMODECHARGE:
			switch (Mode) {
			/*case 1:
				return "Follow";*/
			case StorageController.MODELOADSHAPE:
				return "Loadshape";
			/*case 3:
				return "Support";*/
			case StorageController.MODETIME:
				return "Time";
			default:
				return "UNKNOWN";
			}
		default:
			DSSGlobals.getInstance().doSimpleMsg("Unknown Charge/Discharge designation", 14401);
			return "";
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

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {
		/* Release  the discharge inhibit.
		 * Do nothing for other codes.
		 */
		if ((Code == StorageController.RELEASE_INHIBIT) && (DischargeMode != StorageController.MODEFOLLOW))
			DischargeInhibited = false;
	}

	/**
	 * In Time mode we need to only turn the storage elements on. They will turn themselves
	 * off when they are either fully discharged, fully charged, or receive another command
	 * from the controller.
	 */
	private void doTimeMode(int Opt) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		switch (Opt) {
		case 1:
			if (DischargeTriggerTime > 0.0) {
				// turn on if time within 1/2 time step
				if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - DischargeTriggerTime) < sol.getDynaVars().h / 7200.0) {
					if (!(FleetState == Storage.STORE_DISCHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (ShowEventLog)
							Utilities.appendToEventLog("StorageController." + getName(), "Fleet Set to Discharging by Time Trigger");
						setFleetToDisCharge();
						setFleetkWRate();
						DischargeInhibited = false;
						if (DischargeMode == StorageController.MODEFOLLOW) {
							DischargeTriggeredByTime = true;
						} else {
							sol.setLoadsNeedUpdating(true);  // Force recalc of power parms
							// Push present time onto control queue to force re solve at new dispatch value
							ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.STORE_DISCHARGING, 0, this);
						}
					}
				} else {
					ChargingAllowed = true;
				}
			}
		case 2:
			if (ChargeTriggerTime > 0.0) {
				if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - ChargeTriggerTime) < sol.getDynaVars().h / 7200.0) {
					if (!(FleetState == Storage.STORE_CHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (ShowEventLog)
							Utilities.appendToEventLog("StorageController." + getName(), "Fleet Set to Charging by Time Trigger");
						setFleetToCharge();
						DischargeInhibited = true;
						OutOfOomph         = false;

						sol.setLoadsNeedUpdating(true); // Force recalc of power parms
						// Push present time onto control queue to force re solve at new dispatch value
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.STORE_CHARGING, 0, this);
						ckt.getControlQueue().push(sol.getIntHour() + InhibitHrs, sol.getDynaVars().t, StorageController.RELEASE_INHIBIT, 0, this);
					}
				}
			}
		}
	}

	/**
	 * Normalise time to a floating point number representing time of day if Hour > 24.
	 * time should be 0 to 23.999999.
	 */
	private double normalizeToTOD(int h, double sec) {
		int HourOfDay;

		if (h > 23) {
			HourOfDay = (h - (h / 24) * 24);
		} else {
			HourOfDay = h;
		}

		double Result = HourOfDay + sec / 3600.0;

		if (Result >= 24.0)
			Result = Result - 24.0;   // Wrap around

		return Result;
	}

	private void doLoadFollowMode() {
		int i;
		double PDiff, PFDiff;
		Complex S;
		StorageObj pStorage;
		boolean StorekWChanged, StorekvarChanged;
		double DispatchkW, Dispatchkvar;
		boolean SkipkWDispatch;
		double RemainingkWh, ReservekWh;

		// If list is not defined, go make one from all storage elements in circuit
		if (FleetPointerList.size() == 0)
			makeFleetList();

		if (FleetSize > 0) {

			StorekWChanged   = false;
			StorekvarChanged = false;
			SkipkWDispatch   = false;

			//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
			S = MonitoredElement.getPower(ElementTerminal);  // Power in active terminal
			switch (DischargeMode) {
			// Following Load; try to keep load below kW Target
			case StorageController.MODEFOLLOW:
				if (DischargeTriggeredByTime) {
					if (ShowEventLog)
						Utilities.appendToEventLog("StorageController." + getName(),
								String.format("Fleet Set to Discharging by Time Trigger; Old kWTarget = %-.6g; New = 5-.6g", kWTarget, S.getReal() * 0.001));
					kWTarget = S.getReal() * 0.001;  // Capture present kW and reset target
					DischargeTriggeredByTime = false;  // so we don't come back in here right away
					setFleetToIdle();
				}
				PDiff  = S.getReal() * 0.001 - kWTarget;  // Assume S.re is normally positive
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for peak shaving
			// supporting DG; Try to keep load above kW target
			case StorageController.MODESUPPORT:
				PDiff  = S.getReal() * 0.001 + kWTarget;  // assume S.re is normally negative
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for generator
			case StorageController.MODEPEAKSHAVE:
				PDiff  = S.getReal() * 0.001 - kWTarget;  // Assume S.re is normally positive
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for peak shaving
			default:
				PDiff = 0.0;
				PFDiff = 0.0;
			}

			kWNeeded = PDiff;

			/* kW dispatch */

			if (DischargeInhibited) {
				SkipkWDispatch   = true;
			} else {
				if (FleetState == Storage.STORE_CHARGING)
					PDiff = PDiff + getFleetkW();  // ignore overload due to charging

				switch (FleetState) {
				case Storage.STORE_CHARGING:
					if ((PDiff < 0.0) || OutOfOomph) {
						// Don't bother trying to dispatch
						ChargingAllowed = true;
						SkipkWDispatch  = true;
					}
				case Storage.STORE_IDLING:
					if ((PDiff < 0.0) || OutOfOomph) {
						// Don't bother trying to dispatch
						ChargingAllowed = true;
						SkipkWDispatch  = true;
					}
				case Storage.STORE_DISCHARGING:
					if (((PDiff + getFleetkW()) < 0.0) || OutOfOomph) {
						// desired decrease is greater then present output; just cancel
						setFleetToIdle();  // also sets presentkW = 0
						ChargingAllowed = true;
						SkipkWDispatch  = true;
					}
				}
			}

			if (!SkipkWDispatch) {
				RemainingkWh = getFleetkWh();
				ReservekWh   = getFleetReserveKWh();
				if (RemainingkWh > ReservekWh) {
					// Don't dispatch kW  if not enough storage left or an endless control loop will occur
					if (Math.abs(PDiff) > HalfkWBand) {
						// Attempt to change storage dispatch
						if (!(FleetState == Storage.STORE_DISCHARGING))
							setFleetToDisCharge();
						if (ShowEventLog)
							Utilities.appendToEventLog("StorageController." + getName(),
									String.format("Attempting to dispatch %-.6g kW with %-.6g kWh remaining and %-.6g reserve.", kWNeeded, RemainingkWh, ReservekWh));
						for (i = 0; i < FleetSize; i++) {
							pStorage = (StorageObj) FleetPointerList.get(i);
							// compute new dispatch value for this storage element ...
							DispatchkW = Math.min(pStorage.getkWrating(), (pStorage.getPresentkW() + PDiff *(Weights[i] / TotalWeight)));
							if (DispatchkW != pStorage.getPresentkW()) {
								// Attempt to set discharge kW;  Storage element will revert to idling if out of capacity
								pStorage.setPresentKW(DispatchkW);
								StorekWChanged = true;
							}
						}
					}
				} else {
					if (!(getFleetState() == Storage.STORE_IDLING))
						setFleetToIdle();
					ChargingAllowed = true;
					OutOfOomph = true;
					if (ShowEventLog)
						Utilities.appendToEventLog("StorageController." + getName(),
								String.format("Ran out of OOMPH: %-.6g kWh remaining and %-.6g reserve.", RemainingkWh, ReservekWh));
				}
			}

			// kVAr dispatch  NOTE: PFDiff computed from PF in range of 0..2
			// Redispatch the vars only if the PF is outside the band
			if (DispatchVars && (Math.abs(PFDiff) > HalfPFBand)) {
				if (ShowEventLog)
					Utilities.appendToEventLog("StorageController." + getName(),
							String.format("Changed kvar Dispatch. PF Diff needed = %.6g", PFDiff));
				// Redispatch Storage elements
				for (i = 0; i < FleetSize; i++) {
					pStorage = (StorageObj) FleetPointerList.get(i);
					// compute new var dispatch value for this storage element ...
					if (PFTarget == 1.0) {
						Dispatchkvar = 0.0;
					} else {
						Dispatchkvar = S.getReal() * Math.sqrt(1.0 / Math.pow(Utilities.convertPFRange2ToPF(PFTarget), 2) - 1.0) * (Weights[i] / TotalWeight);
						if (PFTarget > 1.0) Dispatchkvar = -Dispatchkvar;  // for watts and vars in opposite direction
					}

					if (Dispatchkvar != pStorage.getPresentKVar()) {
						pStorage.setPresentKVar(Dispatchkvar);  // Ask for this much kvar  but may be limited by element
						StorekvarChanged = true;
					}
				}
			}

			if (StorekWChanged || StorekvarChanged) {  // Only push onto controlqueue If there has been a change
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				SolutionObj sol = ckt.getSolution();

				sol.setLoadsNeedUpdating(true);  // Force recalc of power parms
				// Push present time onto control queue to force re solve at new dispatch value
				ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.STORE_DISCHARGING, 0, this);
			}
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		ChargingAllowed = false;

		/* Check discharge mode first. Then if not discharging, we can check for charging */

		switch (DischargeMode) {
		case StorageController.MODEFOLLOW:
			doTimeMode(1);
			doLoadFollowMode();
		case StorageController.MODELOADSHAPE:
			doLoadShapeMode();
		case StorageController.MODESUPPORT:
			doLoadFollowMode();
		case StorageController.MODETIME:
			doTimeMode(1);
		case StorageController.MODEPEAKSHAVE:
			doLoadFollowMode();
		default:
			DSSGlobals.getInstance().doSimpleMsg(String.format("Invalid DisCharging Mode: %d", DischargeMode), 14408);
		}

		if (ChargingAllowed)
			switch (ChargeMode) {
			case StorageController.MODELOADSHAPE:
				//doLoadShapeMode();  already executed above
			case StorageController.MODETIME:
				doTimeMode(2);
			default:
				DSSGlobals.getInstance().doSimpleMsg(String.format("Invalid Charging Mode: %d", ChargeMode), 14409);
			}
	}

	private void calcDailyMult(double Hr) {
		if (DailyShapeObj != null) {
			LoadShapeMult = getDailyShapeObj().getMult(Hr);
		} else {
			LoadShapeMult = CDOUBLEONE;  // Default to no variation
		}
	}

	private void calcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			LoadShapeMult = DutyShapeObj.getMult(Hr);
		} else {
			calcDailyMult(Hr);  // Default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double Hr) {
		if (YearlyShapeObj != null) {
			LoadShapeMult = YearlyShapeObj.getMult(Hr) ;
		} else {
			calcDailyMult(Hr);  // Defaults to daily curve
		}
	}

	private void doLoadShapeMode() {
		int FleetStateSaved;
		boolean RateChanged;
		double NewChargeRate;
		double NewkWRate, NewkvarRate;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		FleetStateSaved = FleetState;
		RateChanged     = false;

		// Get multiplier
		switch (sol.getMode()) {
		case Dynamics.DAILYMODE:
			calcDailyMult(sol.getDblHour());  // Daily dispatch curve
		case Dynamics.YEARLYMODE:
			calcYearlyMult(sol.getDblHour());
		case Dynamics.LOADDURATION2:
			calcDailyMult(sol.getDblHour());
		case Dynamics.PEAKDAY:
			calcDailyMult(sol.getDblHour());
		case Dynamics.DUTYCYCLE:
			calcDutyMult(sol.getDblHour()) ;
		}

		if (LoadShapeMult.getReal() < 0.0) {
			ChargingAllowed = true;
			NewChargeRate = Math.abs(LoadShapeMult.getReal()) * 100.0;
			if (NewChargeRate != pctChargeRate)
				RateChanged = true;
			pctChargeRate = NewChargeRate;
			setFleetChargeRate();
			setFleetToCharge();
		} else if (LoadShapeMult.getReal() == 0.0) {
			setFleetToIdle();
		} else {
			// Set fleet to discharging at a rate
			NewkWRate   = LoadShapeMult.getReal() * 100.0;
			NewkvarRate = LoadShapeMult.getImaginary() * 100.0;
			if ((NewkWRate != pctKWRate) || (NewkvarRate != pctkvarRate))
				RateChanged = true;
			pctKWRate   = NewkWRate;
			pctkvarRate = NewkvarRate;
			setFleetkWRate();
			setFleetkvarRate();
			setFleetToDisCharge();
			sol.setLoadsNeedUpdating(true);  // Force recalc of power parms
		}

		if ((FleetState != FleetStateSaved) || RateChanged) {
			sol.setLoadsNeedUpdating(true);  // Force recalc of power parms
			// Push present time onto control queue to force re solve at new dispatch value
			ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, this);
		}
	}
	private void setAllFleetValues() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setPctKWin(pctChargeRate);
			pStorage.setPctKVarout(pctkvarRate);
			pStorage.setPctKWout(pctKWRate);
			pStorage.setPctReserve(pctFleetReserve);
		}
	}

	private void setFleetChargeRate() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setPctKWin(pctChargeRate);
		}
	}

	private void setFleetkvarRate() {
		StorageObj pStorage;
		/* For side effects see pctKVArOut property of storage element */
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setPctKVarout(pctkvarRate);
		}
	}

	private void setFleetkWRate() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setPctKWout(pctKWRate);
		}
	}

	private void setFleetToCharge() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setState(Storage.STORE_CHARGING);
		}
		FleetState = Storage.STORE_CHARGING;
	}

	private void setFleetToDisCharge() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setState(Storage.STORE_DISCHARGING);
		}
		FleetState = Storage.STORE_DISCHARGING;
	}

	private void setFleetToIdle() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setState(Storage.STORE_IDLING);
			pStorage.setPresentKW(0.0);
		}
		FleetState = Storage.STORE_IDLING;
	}

	public void setPFBand(double Value) {
		PFBand = Value;
		HalfPFBand = PFBand / 2.0;
	}

	public double getPFBand() {
		return PFBand;
	}

	private void setFleetToExternal() {
		StorageObj pStorage;
		for (int i = 0; i < FleetPointerList.size(); i++) {
			pStorage = (StorageObj) FleetPointerList.get(i);
			pStorage.setState(Storage.STORE_EXTERNALMODE);
		}
	}

//	private void setPctReserve() {
//		StorageObj pStorage;
//		for (int i = 0; i < FleetPointerList.size(); i++) {
//			pStorage = (StorageObj) FleetPointerList.get(i);
//			pStorage.setPctReserve(pctFleetReserve);
//		}
//	}

	private int interpretMode(int Opt, String S) {
		switch (Opt) {
		case StorageController.propMODEDISCHARGE:
			switch (S.toLowerCase().charAt(0)) {
			case 'f':
				return StorageController.MODEFOLLOW;
			case 'l':
				return StorageController.MODELOADSHAPE;
			case 'p':
				return StorageController.MODEPEAKSHAVE;
			case 's':
				return StorageController.MODESUPPORT;
			case 't':
				return StorageController.MODETIME;
			default:
				DSSGlobals.getInstance().doSimpleMsg("Discharge Mode \"" + S + "\" not recognized.", 14402);
			}
		case StorageController.propMODECHARGE:
			switch (S.toLowerCase().charAt(0)) {
			/*case 'f':
				return StorageController.MODEFOLLOW;*/
			case 'l':
				return StorageController.MODELOADSHAPE;
			/*case 's':
				return StorageController.MODESUPPORT;*/
			case 't':
				return StorageController.MODETIME;
			default:
				DSSGlobals.getInstance().doSimpleMsg("Charge Mode \"" + S + "\" not recognized.", 14402);
			}
		default:
			return 0;
		}
	}

	private boolean makeFleetList() {
		StorageObj pStorage;
		int i;

		DSSGlobals Globals = DSSGlobals.getInstance();

		boolean Result = false;

		if (ElementListSpecified) {  // Name list is defined - Use it

			FleetPointerList.clear();
			for (i = 0; i < FleetSize; i++) {
				pStorage = (StorageObj) Globals.getStorageClass().find(StorageNameList.get(i - 1));
				if (pStorage != null) {
					if (pStorage.isEnabled())
						FleetPointerList.add(pStorage);
				} else {
					Globals.doSimpleMsg("Error: Storage Element \"" + StorageNameList.get(i - 1) + "\" not found.", 14403);
					return Result;
				}
			}

		} else {

			/* Search through the entire circuit for enabled storage elements and add them to the list */
			StorageNameList.clear();
			FleetPointerList.clear();
			for (i = 0; i < Globals.getStorageClass().getElementCount(); i++) {
				pStorage = (StorageObj) Globals.getStorageClass().getElementList().get(i);
				// Look for a storage element not already assigned
				if (pStorage.isEnabled() && (pStorage.getDispatchMode() != Storage.STORE_EXTERNALMODE)) {
					StorageNameList.add(pStorage.getName());  // Add to list of names
					FleetPointerList.add(pStorage);
				}
			}

			/* Allocate uniform weights */
			FleetSize = FleetPointerList.size();
			Weights = (double[]) Utilities.resizeArray(Weights, FleetSize);
			for (i = 0; i < FleetSize; i++)
				Weights[i] = 1.0;
		}

		// Add up total weights
		TotalWeight = 0.0;
		for (i = 0; i < FleetSize; i++)
			TotalWeight = TotalWeight + Weights[i];

		if (FleetPointerList.size() > 0)
			Result = true;

		FleetListChanged = false;

		return Result;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		//super.reset();
		setFleetToIdle();

		// Do we want to set fleet to 100% charged storage?
	}

	private String returnElementsList() {
		if (FleetSize == 0)
			return "";

		String Result = "[" + StorageNameList.get(0);
		for (int i = 0; i < FleetSize - 1; i++)
			Result = Result + ", " + StorageNameList.get(i);
		Result = Result + "]";  // terminate the array

		return Result;
	}

	private String returnWeightsList() {
		if (FleetSize == 0)
			return "";

		String Result = "["+ String.format("%-.6g", Weights[0]);
		for (int i = 1; i < FleetSize; i++)
			Result = Result + String.format(", %-.6g", Weights[i]);
		Result = Result + "]";  // terminate the array

		return Result;
	}

	// FIXME Private members in OpenDSS

	public double getkWTarget() {
		return kWTarget;
	}


	public void setkWTarget(double kWTarget) {
		this.kWTarget = kWTarget;
	}


	public double getPctkWBand() {
		return pctkWBand;
	}


	public void setPctkWBand(double pctkWBand) {
		this.pctkWBand = pctkWBand;
	}


	public double getHalfkWBand() {
		return HalfkWBand;
	}


	public void setHalfkWBand(double halfkWBand) {
		HalfkWBand = halfkWBand;
	}


	public double getPFTarget() {
		return PFTarget;
	}


	public void setPFTarget(double pFTarget) {
		PFTarget = pFTarget;
	}


	public double getTotalWeight() {
		return TotalWeight;
	}


	public void setTotalWeight(double totalWeight) {
		TotalWeight = totalWeight;
	}


	public double getHalfPFBand() {
		return HalfPFBand;
	}


	public void setHalfPFBand(double halfPFBand) {
		HalfPFBand = halfPFBand;
	}


	public double getkWNeeded() {
		return kWNeeded;
	}


	public void setkWNeeded(double kWNeeded) {
		this.kWNeeded = kWNeeded;
	}


	public int getFleetSize() {
		return FleetSize;
	}


	public void setFleetSize(int fleetSize) {
		FleetSize = fleetSize;
	}


	public int getFleetState() {
		return FleetState;
	}


	public void setFleetState(int fleetState) {
		FleetState = fleetState;
	}


	public List<String> getStorageNameList() {
		return StorageNameList;
	}


	public void setStorageNameList(List<String> storageNameList) {
		StorageNameList = storageNameList;
	}


	public List<Object> getFleetPointerList() {
		return FleetPointerList;
	}


	public void setFleetPointerList(List<Object> fleetPointerList) {
		FleetPointerList = fleetPointerList;
	}


	public double[] getWeights() {
		return Weights;
	}


	public void setWeights(double[] weights) {
		Weights = weights;
	}


	public boolean isElementListSpecified() {
		return ElementListSpecified;
	}


	public void setElementListSpecified(boolean elementListSpecified) {
		ElementListSpecified = elementListSpecified;
	}


	public int getDischargeMode() {
		return DischargeMode;
	}


	public void setDischargeMode(int dischargeMode) {
		DischargeMode = dischargeMode;
	}


	public int getChargeMode() {
		return ChargeMode;
	}


	public void setChargeMode(int chargeMode) {
		ChargeMode = chargeMode;
	}


	public double getDischargeTriggerTime() {
		return DischargeTriggerTime;
	}


	public void setDischargeTriggerTime(double dischargeTriggerTime) {
		DischargeTriggerTime = dischargeTriggerTime;
	}


	public double getChargeTriggerTime() {
		return ChargeTriggerTime;
	}


	public void setChargeTriggerTime(double chargeTriggerTime) {
		ChargeTriggerTime = chargeTriggerTime;
	}


	public double getPctKWRate() {
		return pctKWRate;
	}


	public void setPctKWRate(double pctKWRate) {
		this.pctKWRate = pctKWRate;
	}


	public double getPctkvarRate() {
		return pctkvarRate;
	}


	public void setPctkvarRate(double pctkvarRate) {
		this.pctkvarRate = pctkvarRate;
	}


	public double getPctChargeRate() {
		return pctChargeRate;
	}


	public void setPctChargeRate(double pctChargeRate) {
		this.pctChargeRate = pctChargeRate;
	}


	public double getPctFleetReserve() {
		return pctFleetReserve;
	}


	public void setPctFleetReserve(double pctFleetReserve) {
		this.pctFleetReserve = pctFleetReserve;
	}


	public boolean isFleetListChanged() {
		return FleetListChanged;
	}


	public void setFleetListChanged(boolean fleetListChanged) {
		FleetListChanged = fleetListChanged;
	}


	public boolean isChargingAllowed() {
		return ChargingAllowed;
	}


	public void setChargingAllowed(boolean chargingAllowed) {
		ChargingAllowed = chargingAllowed;
	}


	public boolean isShowEventLog() {
		return ShowEventLog;
	}


	public void setShowEventLog(boolean showEventLog) {
		ShowEventLog = showEventLog;
	}


	public boolean isDispatchVars() {
		return DispatchVars;
	}


	public void setDispatchVars(boolean dispatchVars) {
		DispatchVars = dispatchVars;
	}


	public boolean isDischargeTriggeredByTime() {
		return DischargeTriggeredByTime;
	}


	public void setDischargeTriggeredByTime(boolean dischargeTriggeredByTime) {
		DischargeTriggeredByTime = dischargeTriggeredByTime;
	}


	public boolean isDischargeInhibited() {
		return DischargeInhibited;
	}


	public void setDischargeInhibited(boolean dischargeInhibited) {
		DischargeInhibited = dischargeInhibited;
	}


	public boolean isOutOfOomph() {
		return OutOfOomph;
	}


	public void setOutOfOomph(boolean outOfOomph) {
		OutOfOomph = outOfOomph;
	}


	public int getInhibitHrs() {
		return InhibitHrs;
	}


	public void setInhibitHrs(int inhibitHrs) {
		InhibitHrs = inhibitHrs;
	}


	public double getTotalKWCapacity() {
		return TotalKWCapacity;
	}


	public void setTotalKWCapacity(double totalKWCapacity) {
		TotalKWCapacity = totalKWCapacity;
	}


	public double getTotalKWhCapacity() {
		return TotalKWhCapacity;
	}


	public void setTotalKWhCapacity(double totalKWhCapacity) {
		TotalKWhCapacity = totalKWhCapacity;
	}


	public String getYearlyShape() {
		return YearlyShape;
	}


	public void setYearlyShape(String yearlyShape) {
		YearlyShape = yearlyShape;
	}


	public LoadShapeObj getYearlyShapeObj() {
		return YearlyShapeObj;
	}


	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		YearlyShapeObj = yearlyShapeObj;
	}


	public String getDailyShape() {
		return DailyShape;
	}


	public void setDailyShape(String dailyShape) {
		DailyShape = dailyShape;
	}


	public LoadShapeObj getDailyShapeObj() {
		return DailyShapeObj;
	}


	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		DailyShapeObj = dailyShapeObj;
	}


	public String getDutyShape() {
		return DutyShape;
	}


	public void setDutyShape(String dutyShape) {
		DutyShape = dutyShape;
	}


	public LoadShapeObj getDutyShapeObj() {
		return DutyShapeObj;
	}


	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		DutyShapeObj = dutyShapeObj;
	}


	public Complex getLoadShapeMult() {
		return LoadShapeMult;
	}


	public void setLoadShapeMult(Complex loadShapeMult) {
		LoadShapeMult = loadShapeMult;
	}


	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}


	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

}

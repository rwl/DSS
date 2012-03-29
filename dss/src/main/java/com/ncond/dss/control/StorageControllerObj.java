/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.conversion.DispatchMode;
import com.ncond.dss.conversion.StorageObj;
import com.ncond.dss.conversion.StorageState;
import com.ncond.dss.general.LoadShapeObj;

import static com.ncond.dss.common.Util.appendToEventLog;
import static com.ncond.dss.common.Util.powerFactor;
import static com.ncond.dss.common.Util.getCktElementIndex;
import static com.ncond.dss.common.Util.convertPFToPFRange2;
import static com.ncond.dss.common.Util.convertPFRange2ToPF;
import static com.ncond.dss.common.Util.resizeArray;

/**
 * A control element that is connected to a terminal
 * of another circuit element and sends dispatch  signals to a fleet of energy
 * storage elements it controls.
 *
 *   new storageController.name=myName element=devClass.name terminal=[ 1|2|...] elementList=(elem1  elem2 ...)
 *
 * or ... ElementList=[File=filename] where storage class elements are listed
 * one to a line. If omitted, all storage elements found in the active circuit
 * are included by default and controlled as a fleet.
 *
 */
public class StorageControllerObj extends ControlElem {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private double kWTarget, kWThreshold, pctkWBand, halfKWBand, totalWeight;
	private double PFTarget;  // range on this is 0..2 where 1..2 is leading
	private double halfPFBand;
	protected double PFBand;
	private double kWNeeded;
	private int fleetSize;
	private StorageState fleetState;

	private List<String> storageNameList;
	private List<Object> fleetPointerList;
	private double[] weights;

	private boolean elementListSpecified;

	private StorageControlMode dischargeMode;
	private StorageControlMode chargeMode;
	private double dischargeTriggerTime;
	private double chargeTriggerTime;
	private double pctKWRate;
	private double pctKVArRate;
	private double pctChargeRate;
	private double pctFleetReserve;
	private boolean fleetListChanged;
	private boolean chargingAllowed;
	private boolean dispatchVars;
	private boolean dischargeTriggeredByTime;
	private boolean dischargeInhibited;
	private boolean outOfEnergy;
	private int inhibitHrs;
	private double upRampTime;
	private double flatTime;
	private double dnRampTime;
	private double upPlusFlat;
	private double upPlusFlatPlusDn;
	private double lastPctDischargeRate;

	private double[] totalKWCapacity = new double[1];
	private double[] totalKWhCapacity = new double[1];

	private String yearlyShape;          // ="fixed" means no variation  on all the time
	private LoadShapeObj yearlyShapeObj; // shape for this Storage element
	private String dailyShape;           // daily (24 HR) storage element shape
	private LoadShapeObj dailyShapeObj;  // daily storage element Shape for this load
	private String dutyShape;            // duty cycle load shape for changes typically less than one hour
	private LoadShapeObj dutyShapeObj;   // shape for this storage element

	private Complex loadShapeMult;

	private CktElement monitoredElement;

	public StorageControllerObj(DSSClass parClass, String storageControllerName) {
		super(parClass);

		setName(storageControllerName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors

		elementName = "";
		setControlledElement(null);  // not used in this control
		elementTerminalIdx = 0;
		monitoredElement = null;

		storageNameList = new ArrayList<String>();
		weights = null;
		fleetPointerList = new ArrayList<Object>(20);  // default size and increment
		fleetSize = 0;
		fleetState = StorageState.IDLING;
		kWTarget = 8000.0;
		kWThreshold = 6000.0;
		pctkWBand = 2.0;
		totalWeight = 1.0;
		halfKWBand = pctkWBand / 200.0 * kWTarget;
		PFTarget = 0.96;
		PFBand = 0.04;
		halfPFBand = PFBand / 2.0;
		kWNeeded = 0.0;

		dischargeMode = StorageControlMode.PEAKSHAVE;
		chargeMode = StorageControlMode.TIME;

		dischargeTriggerTime = -1.0;  // disabled
		chargeTriggerTime = 2.0;   // 2 AM
		elementListSpecified = false;
		fleetListChanged = true;  // force building of list
		pctKWRate = 20.0;
		pctKVArRate = 20.0;
		pctChargeRate = 20.0;
		pctFleetReserve = 25.0;

		showEventLog = false;
		dispatchVars = false;
		dischargeTriggeredByTime = false;
		dischargeInhibited = false;
		outOfEnergy = false;
		inhibitHrs = 5;  // no. hours to inhibit discharging after going into charge mode

		upRampTime = 0.25;  // hr
		flatTime = 2.0;
		dnRampTime = 0.25;
		lastPctDischargeRate = 0.0;

		initPropertyValues(0);
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(StorageController.ELEMENT, "");
		setPropertyValue(StorageController.TERMINAL, "1");
		setPropertyValue(StorageController.KW_TARGET, "8000");
		setPropertyValue(StorageController.KW_BAND, "2");
		setPropertyValue(StorageController.PF_TARGET, ".96");
		setPropertyValue(StorageController.PF_BAND, ".04");
		setPropertyValue(StorageController.ELEMENT_LIST, "");
		setPropertyValue(StorageController.WEIGHTS, "");
		setPropertyValue(StorageController.MODE_DISCHARGE, "Follow");
		setPropertyValue(StorageController.MODE_CHARGE, "Time");
		setPropertyValue(StorageController.TIME_DISCHARGE_TRIGGER, "-1");
		setPropertyValue(StorageController.TIME_CHARGE_TRIGGER, "2");
		setPropertyValue(StorageController.RATE_KW, "20");
		setPropertyValue(StorageController.RATE_KVAR, "20");
		setPropertyValue(StorageController.RATE_CHARGE, "20");
		setPropertyValue(StorageController.RESERVE, "25");
		setPropertyValue(StorageController.KWH_TOTAL, "");
		setPropertyValue(StorageController.KW_TOTAL, "");
		setPropertyValue(StorageController.KW_ACTUAL, "");
		setPropertyValue(StorageController.KW_NEED, "");
		setPropertyValue(StorageController.PARTICIPATION, "");
		setPropertyValue(StorageController.YEARLY, "");
		setPropertyValue(StorageController.DAILY, "");
		setPropertyValue(StorageController.DUTY, "");
		setPropertyValue(StorageController.EVENTLOG, "No");
		setPropertyValue(StorageController.INHIBIT_TIME, "5");
		setPropertyValue(StorageController.T_UP_RAMP, "0.25");
		setPropertyValue(StorageController.T_FLAT, "2.0");
		setPropertyValue(StorageController.T_DN_RAMP, "0.25");
		setPropertyValue(StorageController.KW_THRESHOLD, "4000");

		super.initPropertyValues(StorageController.NumPropsThisClass - 1);
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case StorageController.KW_TARGET:
			return String.format("%-.6g", kWTarget);
		case StorageController.KW_BAND:
			return String.format("%-.6g", pctkWBand);
		case StorageController.PF_TARGET:
			return String.format("%-.6g", convertPFRange2ToPF(PFTarget));
		case StorageController.PF_BAND:
			return String.format("%-.6g", PFBand);
		case StorageController.ELEMENT_LIST:
			return returnElementsList();
		case StorageController.WEIGHTS:
			return returnWeightsList();
		case StorageController.MODE_DISCHARGE:
			return getModeString(StorageController.MODE_DISCHARGE, dischargeMode);
		case StorageController.MODE_CHARGE:
			return getModeString(StorageController.MODE_CHARGE, chargeMode);
		case StorageController.TIME_DISCHARGE_TRIGGER:
			return String.format("%.6g", dischargeTriggerTime);
		case StorageController.TIME_CHARGE_TRIGGER:
			return String.format("%.6g", chargeTriggerTime);
		case StorageController.RATE_KW:
			return String.format("%-.8g", pctKWRate);
		case StorageController.RATE_KVAR:
			return String.format("%-.8g", pctKVArRate);
		case StorageController.RATE_CHARGE:
			return String.format("%-.8g", pctChargeRate);
		case StorageController.RESERVE:
			return String.format("%-.8g", pctFleetReserve);
		case StorageController.KWH_TOTAL:
			return getkWhTotal(totalKWhCapacity);
		case StorageController.KW_TOTAL:
			return getkWTotal(totalKWCapacity);
		case StorageController.KWH_ACTUAL:
			return getkWhActual();
		case StorageController.KW_ACTUAL:
			return getkWActual();
		case StorageController.KW_NEED:
			return String.format("%-.6g", kWNeeded);
		/*case StorageController.propPARTICIPATION:
			return getPropertyValue(Index);*/
		case StorageController.YEARLY:
			return yearlyShape;
		case StorageController.DAILY:
			return dailyShape;
		case StorageController.DUTY:
			return dutyShape;
		case StorageController.EVENTLOG:
			if (showEventLog) {
				return "Yes";
			} else {
				return "No";
			}
		case StorageController.VAR_DISPATCH:
			if (dispatchVars) {
				return "Yes";
			} else {
				return "No";
			}
		case StorageController.INHIBIT_TIME:
			return String.format("%d", inhibitHrs);
		case StorageController.T_UP_RAMP:
			return String.format("%.6g", upRampTime);
		case StorageController.T_FLAT:
			return String.format("%.6g", flatTime);
		case StorageController.T_DN_RAMP:
			return String.format("%.6g", dnRampTime);
		case StorageController.KW_THRESHOLD:
			return String.format("%.6g", kWThreshold);
		default:  // take the generic handler
			return super.getPropertyValue(index);
		}
	}

	public double getFleetKW() {
		StorageObj storage;
		double kw = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			kw = kw + storage.getPresentKW();
		}
		return kw;
	}

	public double getFleetkWh() {
		StorageObj storage;
		double kwh = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			kwh = kwh + storage.getKWhStored();
		}
		return kwh;
	}

	public double getFleetReserveKWh() {
		StorageObj storage;
		double kwh = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			kwh = kwh + storage.getKWhReserve();
		}
		return kwh;
	}

	/**
	 * Recalculate critical element values after changes have been made.
	 */
	@Override
	public void recalcElementData() {
		/* Check for existence of monitored element */
		int devIndex = getCktElementIndex(elementName);

		if (devIndex >= 0) {
			monitoredElement = DSS.activeCircuit.getCktElements().get(devIndex);
			if (elementTerminalIdx >= monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("StorageController: \"" + getName() + "\"",
						"Terminal no. \"" + (elementTerminalIdx+1) + "\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				setNumPhases(monitoredElement.getNumPhases());
				setNumConds(nPhases);
				// sets name of i-th terminal's connected bus in StorageController's bus list
				setBus(0, monitoredElement.getBus(elementTerminalIdx));
			}
		} else {
			DSS.doSimpleMsg("Monitored element in StorageController." + getName() +
					" does not exist:\"" + elementName + "\"", 372);
		}

		if (fleetListChanged) {
			if (!makeFleetList())
				DSS.doSimpleMsg("No unassigned storage elements found to assign to StorageController." + getName(), 37201);
		}

		getkWTotal(totalKWCapacity);
		getkWhTotal(totalKWhCapacity);

		if (fleetSize > 0) {
			setFleetToExternal();
			setAllFleetValues();
		}

		upPlusFlat = upRampTime + flatTime;
		upPlusFlatPlusDn = upPlusFlat + dnRampTime;
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (monitoredElement != null) {
			setNumPhases(monitoredElement.getNumPhases());
			setNumConds(nPhases);
			setBus(0, monitoredElement.getBus(elementTerminalIdx));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	private String getkWActual() {
		return String.format("%-.8g", getFleetKW());
	}

	private String getkWhActual() {
		return String.format("%-.8g", getFleetkWh());
	}

	private String getkWhTotal(double[] sum) {
		StorageObj storage;
		sum[0] = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			sum[0] += storage.getKWhRating();
		}
		return String.format("%-.8g", sum);
	}

	private String getkWTotal(double[] sum) {
		StorageObj storage;
		sum[0] = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			sum[0] += storage.getKWRating();
		}
		return String.format("%-.8g", sum);
	}

	private String getModeString(int opt, StorageControlMode mode) {
		switch (opt) {
		case StorageController.MODE_DISCHARGE:
			switch (mode) {
			case FOLLOW:
				return "Follow";
			case LOADSHAPE:
				return "Loadshape";
			case SUPPORT:
				return "Support";
			case TIME:
				return "Time";
			case PEAKSHAVE:
				return "Peakshave";
			default:
				return "Unknown";
			}
		case StorageController.MODE_CHARGE:
			switch (mode) {
			/*case FOLLOW:
				return "Follow";*/
			case LOADSHAPE:
				return "Loadshape";
			/*case SUPPORT:
				return "Support";*/
			case TIME:
				return "Time";
			default:
				return "Unknown";
			}
		default:
			DSS.doSimpleMsg("Unknown charge/discharge designation", 14401);
			return "";
		}
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
		/* Release the discharge inhibit.
		 * Do nothing for other codes.
		 */
		if (code == StorageController.RELEASE_INHIBIT && dischargeMode != StorageControlMode.FOLLOW)
			dischargeInhibited = false;
	}

	/**
	 * In "schedule" mode we ramp up the storage from zero to the specified pctkWRate.
	 * This value is held for the flat time or until they turn themselves
	 * off when they are either fully discharged, or ramped down
	 *
	 * The discharge trigger time must be greater than 0
	 */
	private void doScheduleMode() {
		double tDiff;
		double pctDischargeRate = 0.0;  // init for test

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (dischargeTriggerTime > 0.0) {
			// turn on if time within 1/2 time step
			if (!(fleetState == StorageState.DISCHARGING)) {
				chargingAllowed = true;
				tDiff = normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - dischargeTriggerTime;
				if (Math.abs(tDiff) < sol.getDynaVars().h / 7200.0) {
					/* Time is within 1 time step of the trigger time */
					if (showEventLog) {
						appendToEventLog("StorageController." + getName(),
							"Fleet set to discharging (up ramp) by schedule");
					}
					setFleetToDisCharge();
					chargingAllowed = false;
					pctDischargeRate = Math.min(pctKWRate, Math.max(pctKWRate * tDiff / upRampTime, 0.0));
					setFleetkWRate(pctDischargeRate);
					dischargeInhibited = false;
					pushTimeOntoControlQueue(StorageState.DISCHARGING);
				}
			} else {  // fleet is already discharging
				tDiff = normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - dischargeTriggerTime;
				if (tDiff < upRampTime) {
					pctDischargeRate = Math.min(pctKWRate, Math.max(pctKWRate * tDiff / upRampTime, 0.0));
					setFleetkWRate(pctDischargeRate);
				} else {
					if (tDiff < upPlusFlat) {
						pctDischargeRate = pctKWRate;
						if (pctDischargeRate != lastPctDischargeRate)
							setFleetkWRate(pctKWRate);  // on the flat part

					} else if (tDiff > upPlusFlatPlusDn) {
						setFleetToIdle();
						chargingAllowed = true;
						pctDischargeRate = 0.0;
						if (showEventLog) {
							appendToEventLog("StorageController." + getName(),
									"Fleet set to idling by schedule");
						}
					} else {  // we're on the down ramp
						tDiff = upPlusFlatPlusDn - tDiff;
						pctDischargeRate = Math.max(0.0, Math.min(pctKWRate * tDiff / dnRampTime, pctKWRate));
						setFleetkWRate(pctDischargeRate);
					}
				}

				if (pctDischargeRate != lastPctDischargeRate)
					pushTimeOntoControlQueue(StorageState.DISCHARGING);
			}
		}
		lastPctDischargeRate = pctDischargeRate;  // remember this value
	}

	/**
	 * In "time" mode we need to only turn the storage elements on. They will turn themselves
	 * off when they are either fully discharged, fully charged, or receive another command
	 * from the controller.
	 */
	private void doTimeMode(int opt) {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		switch (opt) {
		case 1:
			if (dischargeTriggerTime > 0.0) {
				// turn on if time within 1/2 time step
				if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - dischargeTriggerTime) < sol.getDynaVars().h / 7200.0) {
					if (!(fleetState == StorageState.DISCHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (showEventLog)
							appendToEventLog("StorageController." + getName(), "Fleet set to discharging by time trigger");
						setFleetToDisCharge();
						setFleetkWRate(pctKWRate);
						dischargeInhibited = false;
						if (dischargeMode == StorageControlMode.FOLLOW) {
							dischargeTriggeredByTime = true;
						} else {
							pushTimeOntoControlQueue(StorageState.DISCHARGING);
						}
					}
				} else {
					chargingAllowed = true;
				}
			}
			break;
		case 2:
			if (chargeTriggerTime > 0.0) {
				if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - chargeTriggerTime) < sol.getDynaVars().h / 7200.0) {
					if (!(fleetState == StorageState.CHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (showEventLog)
							appendToEventLog("StorageController." + getName(), "Fleet set to charging by time trigger");
						setFleetToCharge();
						dischargeInhibited = true;
						outOfEnergy        = false;
						pushTimeOntoControlQueue(StorageState.DISCHARGING);

						sol.setLoadsNeedUpdating(true);  // force recalc of power parms
						// push present time onto control queue to force re solve at new dispatch value
						ckt.getControlQueue().push(sol.getIntHour() + inhibitHrs, sol.getDynaVars().t,
								StorageController.RELEASE_INHIBIT, 0, this);
					}
				}
			}
			break;
		}
	}

	/**
	 * Normalise time to a floating point number representing time of day if hour > 24
	 * time should be 0 to 23.999999.
	 */
	private double normalizeToTOD(int h, double sec) {
		int hourOfDay;
		double t;

		if (h > 23) {
			hourOfDay = (h - (h / 24) * 24);
		} else {
			hourOfDay = h;
		}

		t = hourOfDay + sec / 3600.0;

		if (t >= 24.0)
			t = t - 24.0;  // wrap around

		return t;
	}

	/**
	 * Push present time onto control queue to force re solve at new dispatch value
	 */
	private void pushTimeOntoControlQueue(StorageState code) {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		sol.setLoadsNeedUpdating(true);  // force recalc of power parms
		ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, code.code(), 0, this);
	}

	private void doLoadFollowMode() {
		int i;
		double Pdiff, PFdiff;
		Complex S;
		StorageObj storage;
		boolean storeKWChanged, storeKVArChanged;
		double dispatchKW, dispatchKVAr;
		boolean skipKWDispatch;
		double remainingKWh, reserveKWh;

		// if list is not defined, go make one from all storage elements in circuit
		if (fleetPointerList.size() == 0)
			makeFleetList();

		if (fleetSize > 0) {
			storeKWChanged = false;
			storeKVArChanged = false;
			skipKWDispatch = false;

			//monitoredElement.activeTerminalIdx = elementTerminalIdx;
			S = monitoredElement.getPower(elementTerminalIdx);  // power in active terminal
			switch (dischargeMode) {
			// following load; try to keep load below kW Target
			case FOLLOW:
				if (dischargeTriggeredByTime) {
					if (showEventLog) {
						appendToEventLog("StorageController." + getName(),
							String.format("Fleet set to discharging by time trigger; Old kWTarget = %-.6g; New = 5-.6g", kWTarget, S.getReal() * 0.001));
					}
					kWTarget = Math.max(kWThreshold, S.getReal() * 0.001);  // capture present kW and reset target
					dischargeTriggeredByTime = false;  // so we don't come back in here right away
					setFleetToIdle();
				}
				Pdiff  = S.getReal() * 0.001 - kWTarget;  // assume S.re is normally positive
				PFdiff = convertPFToPFRange2(powerFactor(S)) - PFTarget;  // for peak shaving
				break;
			// supporting DG; try to keep load above kW target
			case SUPPORT:
				Pdiff  = S.getReal() * 0.001 + kWTarget;  // assume S.re is normally negative
				PFdiff = convertPFToPFRange2(powerFactor(S)) - PFTarget;  // for generator
				break;
			case PEAKSHAVE:
				Pdiff  = S.getReal() * 0.001 - kWTarget;  // assume S.re is normally positive
				PFdiff = convertPFToPFRange2(powerFactor(S)) - PFTarget;  // for peak shaving
				break;
			default:
				Pdiff = 0.0;
				PFdiff = 0.0;
				break;
			}

			kWNeeded = Pdiff;

			/* kW dispatch */
			if (dischargeInhibited) {
				skipKWDispatch = true;
			} else {
				if (fleetState == StorageState.CHARGING)
					Pdiff = Pdiff + getFleetKW();  // ignore overload due to charging

				switch (fleetState) {
				case CHARGING:
					if (Pdiff < 0.0 || outOfEnergy) {
						// don't bother trying to dispatch
						chargingAllowed = true;
						skipKWDispatch = true;
					}
					break;
				case IDLING:
					if (Pdiff < 0.0 || outOfEnergy) {
						// don't bother trying to dispatch
						chargingAllowed = true;
						skipKWDispatch = true;
					}
					break;
				case DISCHARGING:
					if (Pdiff + getFleetKW() < 0.0 || outOfEnergy) {
						// desired decrease is greater then present output; just cancel
						setFleetToIdle();  // also sets presentkW = 0
			                        pushTimeOntoControlQueue(StorageState.IDLING);  // force a new power flow solution
						chargingAllowed = true;
						skipKWDispatch = true;
					}
					break;
				}
			}

			if (!skipKWDispatch) {
				remainingKWh = getFleetkWh();
				reserveKWh = getFleetReserveKWh();
				if (remainingKWh > reserveKWh) {
					// don't dispatch kW if not enough storage left or an endless control loop will occur
					if (Math.abs(Pdiff) > halfKWBand) {
						// attempt to change storage dispatch
						if (!(fleetState == StorageState.DISCHARGING))
							setFleetToDisCharge();

						if (showEventLog) {
							appendToEventLog("StorageController." + getName(),
								String.format("Attempting to dispatch %-.6g kW with %-.6g kWh remaining and %-.6g reserve.", kWNeeded, remainingKWh, reserveKWh));
						}

						for (i = 0; i < fleetSize; i++) {
							storage = (StorageObj) fleetPointerList.get(i);
							// compute new dispatch value for this storage element ...
							dispatchKW = Math.min(storage.getKWRating(), (storage.getPresentKW() + Pdiff *(weights[i] / totalWeight)));
							if (dispatchKW != storage.getPresentKW()) {  // redispatch only if change requested
								if (storage.getKWhStored() > storage.getKWhReserve()) {
									// attempt to set discharge kW; storage element will revert to idling if out of capacity
									storage.setPresentKW(dispatchKW);
									storeKWChanged = true;  // this is what keeps the control iterations going
								}
							}
						}
					}
				} else {
					if (!(getFleetState() == StorageState.IDLING)) {
						setFleetToIdle();
			                        pushTimeOntoControlQueue(StorageState.IDLING);  // force a new power flow solution
					}
					chargingAllowed = true;
					outOfEnergy = true;
					if (showEventLog) {
						appendToEventLog("StorageController." + getName(),
							String.format("Ran out of energy: %-.6g kWh remaining and %-.6g reserve.", remainingKWh, reserveKWh));
					}
				}
			}

			// kVAr dispatch Note: PFdiff computed from PF in range of 0..2
			// redispatch the vars only if the PF is outside the band
			if (dispatchVars && Math.abs(PFdiff) > halfPFBand) {
				if (showEventLog) {
					appendToEventLog("StorageController." + getName(),
						String.format("Changed kvar dispatch. PF diff needed = %.6g", PFdiff));
				}
				// redispatch storage elements
				for (i = 0; i < fleetSize; i++) {
					storage = (StorageObj) fleetPointerList.get(i);
					// compute new var dispatch value for this storage element ...
					if (PFTarget == 1.0) {
						dispatchKVAr = 0.0;
					} else {
						dispatchKVAr = S.getReal() * Math.sqrt(1.0 / Math.pow(convertPFRange2ToPF(PFTarget), 2) - 1.0) * (weights[i] / totalWeight);
						if (PFTarget > 1.0)
							dispatchKVAr = -dispatchKVAr;  // for watts and vars in opposite direction
					}

					if (dispatchKVAr != storage.getPresentKVAr()) {
						storage.setPresentKVAr(dispatchKVAr);  // ask for this much kvar but may be limited by element
						storeKVArChanged = true;
					}
				}
			}

			if (storeKWChanged || storeKVArChanged) {  // only push onto control queue if there has been a change
			           pushTimeOntoControlQueue(StorageState.DISCHARGING);
			}
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		chargingAllowed = false;

		/* Check discharge mode first. Then if not discharging, we can check for charging. */

		switch (dischargeMode) {
		case FOLLOW:
			doTimeMode(1);
			doLoadFollowMode();
			break;
		case LOADSHAPE:
			doLoadShapeMode();
			break;
		case SUPPORT:
			doLoadFollowMode();
			break;
		case TIME:
			doTimeMode(1);
			break;
		case PEAKSHAVE:
			doLoadFollowMode();
			break;
		case SCHEDULE:
			doScheduleMode();
			break;
		default:
			DSS.doSimpleMsg(String.format("Invalid discharging mode: %d", dischargeMode), 14408);
			break;
		}

		if (chargingAllowed) {
			switch (chargeMode) {
			case LOADSHAPE:
				//doLoadShapeMode();  already executed above
				break;
			case TIME:
				doTimeMode(2);
				break;
			default:
				DSS.doSimpleMsg(String.format("Invalid charging mode: %d", chargeMode), 14409);
				break;
			}
		}
	}

	private void calcDailyMult(double hr) {
		if (dailyShapeObj != null) {
			loadShapeMult = getDailyShapeObj().getMult(hr);
		} else {
			loadShapeMult = CDOUBLEONE;  // default to no variation
		}
	}

	private void calcDutyMult(double hr) {
		if (dutyShapeObj != null) {
			loadShapeMult = dutyShapeObj.getMult(hr);
		} else {
			calcDailyMult(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double hr) {
		if (yearlyShapeObj != null) {
			loadShapeMult = yearlyShapeObj.getMult(hr) ;
		} else {
			calcDailyMult(hr);  // defaults to daily curve
		}
	}

	private void doLoadShapeMode() {
		StorageState fleetStateSaved;
		boolean rateChanged;
		double newChargeRate;
		double newKWRate, newKVArRate;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		fleetStateSaved = fleetState;
		rateChanged = false;

		// get multiplier
		switch (sol.getMode()) {
		case DAILYMODE:
			calcDailyMult(sol.getDblHour());  // daily dispatch curve
			break;
		case YEARLYMODE:
			calcYearlyMult(sol.getDblHour());
			break;
		case LOADDURATION2:
			calcDailyMult(sol.getDblHour());
			break;
		case PEAKDAY:
			calcDailyMult(sol.getDblHour());
			break;
		case DUTYCYCLE:
			calcDutyMult(sol.getDblHour()) ;
			break;
		}

		if (loadShapeMult.getReal() < 0.0) {
			chargingAllowed = true;
			newChargeRate = Math.abs(loadShapeMult.getReal()) * 100.0;
			if (newChargeRate != pctChargeRate) rateChanged = true;
			pctChargeRate = newChargeRate;
			setFleetChargeRate();
			setFleetToCharge();
		} else if (loadShapeMult.getReal() == 0.0) {
			setFleetToIdle();
		} else {
			// set fleet to discharging at a rate
			newKWRate = loadShapeMult.getReal() * 100.0;
			newKVArRate = loadShapeMult.getImaginary() * 100.0;
			if (newKWRate != pctKWRate || newKVArRate != pctKVArRate) {
				rateChanged = true;
			}
			pctKWRate = newKWRate;
			pctKVArRate = newKVArRate;
			setFleetkWRate(pctKWRate);
			setFleetkvarRate(pctKVArRate);
			setFleetToDisCharge();
			sol.setLoadsNeedUpdating(true);  // force recalc of power parms
		}

		/* Force a new power flow solution if fleet state has changed */
		if (fleetState != fleetStateSaved || rateChanged) {
			pushTimeOntoControlQueue(StorageState.IDLING);
		}
	}
	private void setAllFleetValues() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setPctKWIn(pctChargeRate);
			storage.setPctKVArOut(pctKVArRate);
			storage.setPctKWOut(pctKWRate);
			storage.setPctReserve(pctFleetReserve);
		}
	}

	private void setFleetChargeRate() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setPctKWIn(pctChargeRate);
		}
	}

	private void setFleetkvarRate(double pctkvar) {
		StorageObj storage;
		/* For side effects see pctKVArOut property of storage element */
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setPctKVArOut(pctKVArRate);
		}
	}

	private void setFleetkWRate(double pctkw) {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setPctKWOut(pctkw);
		}
	}

	private void setFleetToCharge() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setStorageState(StorageState.CHARGING);
		}
		fleetState = StorageState.CHARGING;
	}

	private void setFleetToDisCharge() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setStorageState(StorageState.DISCHARGING);
		}
		fleetState = StorageState.DISCHARGING;
	}

	private void setFleetToIdle() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setStorageState(StorageState.IDLING);
			storage.setPresentKW(0.0);
		}
		fleetState = StorageState.IDLING;
	}

	public void setPFBand(double value) {
		PFBand = value;
		halfPFBand = PFBand / 2.0;
	}

	public double getPFBand() {
		return PFBand;
	}

	private void setFleetToExternal() {
		StorageObj storage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			storage = (StorageObj) fleetPointerList.get(i);
			storage.setStorageState(StorageState.EXTERNAL);
		}
	}

//	private void setPctReserve() {
//		StorageObj storage;
//		for (int i = 0; i < fleetPointerList.size(); i++) {
//			storage = (StorageObj) fleetPointerList.get(i);
//			storage.setPctReserve(pctFleetReserve);
//		}
//	}

	public StorageControlMode interpretMode(int opt, String s) {
		switch (opt) {
		case StorageController.MODE_DISCHARGE:
			switch (s.toLowerCase().charAt(0)) {
			case 'f':
				return StorageControlMode.FOLLOW;
			case 'l':
				return StorageControlMode.LOADSHAPE;
			case 'p':
				return StorageControlMode.PEAKSHAVE;
			case 's':
				if (s.toLowerCase().charAt(1) == 'c') {
					return StorageControlMode.SCHEDULE;
				} else {
					return StorageControlMode.SUPPORT;
				}
			case 't':
				return StorageControlMode.TIME;
			default:
				DSS.doSimpleMsg("Discharge mode \"" + s + "\" not recognized.", 14402);
			}
			break;
		case StorageController.MODE_CHARGE:
			switch (s.toLowerCase().charAt(0)) {
			/*case 'f':
				return StorageControlMode.MODEFOLLOW;*/
			case 'l':
				return StorageControlMode.LOADSHAPE;
			/*case 's':
				return StorageControlMode.MODESUPPORT;*/
			case 't':
				return StorageControlMode.TIME;
			default:
				DSS.doSimpleMsg("Charge mode \"" + s + "\" not recognized.", 14402);
			}
			break;
		default:
			break;
		}
		return null;
	}

	private boolean makeFleetList() {
		StorageObj storage;
		int i;
		boolean made = false;

		if (elementListSpecified) {  // name list is defined - use it
			fleetPointerList.clear();
			for (i = 0; i < fleetSize; i++) {
				storage = (StorageObj) DSS.storageClass.find(storageNameList.get(i));
				if (storage != null) {
					if (storage.isEnabled())
						fleetPointerList.add(storage);
				} else {
					DSS.doSimpleMsg("Error: Storage element \"" + storageNameList.get(i) + "\" not found.", 14403);
					return made;
				}
			}
		} else {
			/* Search through the entire circuit for enabled storage elements and add them to the list */
			storageNameList.clear();
			fleetPointerList.clear();
			for (i = 0; i < DSS.storageClass.getElementCount(); i++) {
				storage = (StorageObj) DSS.storageClass.getElementList().get(i);
				// Look for a storage element not already assigned
				if (storage.isEnabled() && (storage.getDispatchMode() != DispatchMode.EXTERNAL)) {
					storageNameList.add(storage.getName());  // add to list of names
					fleetPointerList.add(storage);
				}
			}

			/* Allocate uniform weights */
			fleetSize = fleetPointerList.size();
			weights = resizeArray(weights, fleetSize);
			for (i = 0; i < fleetSize; i++) weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < fleetSize; i++)
			totalWeight = totalWeight + weights[i];

		if (fleetPointerList.size() > 0)
			made = true;

		fleetListChanged = false;

		return made;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		//super.reset();
		setFleetToIdle();

		// do we want to set fleet to 100% charged storage?
	}

	private String returnElementsList() {
		if (fleetSize == 0) return "";

		StringBuilder sb = new StringBuilder("[");
		sb.append(storageNameList.get(0));
		for (int i = 1; i < fleetSize; i++) {
			sb.append(", ");
			sb.append(storageNameList.get(i));
		}
		sb.append("]");  // terminate the array

		return sb.toString();
	}

	private String returnWeightsList() {
		if (fleetSize == 0) return "";

		StringBuilder sb = new StringBuilder("[");
		sb.append(String.format("%-.6g", weights[0]));
		for (int i = 1; i < fleetSize; i++)
			sb.append(String.format(", %-.6g", weights[i]));
		sb.append("]");  // terminate the array

		return sb.toString();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public double getKWTarget() {
		return kWTarget;
	}

	public double getKWThreshold() {
		return kWThreshold;
	}

	public double getPctkWBand() {
		return pctkWBand;
	}

	public double getPFTarget() {
		return PFTarget;
	}

	public double getHalfPFBand() {
		return halfPFBand;
	}

	public int getFleetSize() {
		return fleetSize;
	}

	public StorageState getFleetState() {
		return fleetState;
	}

	public List<String> getStorageNameList() {
		return storageNameList;
	}

	public List<Object> getFleetPointerList() {
		return fleetPointerList;
	}

	public StorageControlMode getDischargeMode() {
		return dischargeMode;
	}

	public StorageControlMode getChargeMode() {
		return chargeMode;
	}

	public double getDischargeTriggerTime() {
		return dischargeTriggerTime;
	}

	public double getChargeTriggerTime() {
		return chargeTriggerTime;
	}

	public double getPctKVArRate() {
		return pctKVArRate;
	}

	public double getPctChargeRate() {
		return pctChargeRate;
	}

	public double getPctFleetReserve() {
		return pctFleetReserve;
	}

	public int getInhibitHrs() {
		return inhibitHrs;
	}

	public double getFlatTime() {
		return flatTime;
	}

	public double getDnRampTime() {
		return dnRampTime;
	}

	public String getDailyShape() {
		return dailyShape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return dailyShapeObj;
	}

	public String getDutyShape() {
		return dutyShape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return dutyShapeObj;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public double[] getWeights() {
		return weights;
	}

	public double getPctKWRate() {
		return pctKWRate;
	}

	public boolean isDispatchVars() {
		return dispatchVars;
	}

	public double getUpRampTime() {
		return upRampTime;
	}

	public String getYearlyShape() {
		return yearlyShape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return yearlyShapeObj;
	}

	public void setFleetSize(int fleetSize) {
		this.fleetSize = fleetSize;
	}

	public void setElementListSpecified(boolean elementListSpecified) {
		this.elementListSpecified = elementListSpecified;
	}

	public void setChargeMode(StorageControlMode chargeMode) {
		this.chargeMode = chargeMode;
	}

	public void setDischargeTriggerTime(double dischargeTriggerTime) {
		this.dischargeTriggerTime = dischargeTriggerTime;
	}

	public void setFleetListChanged(boolean fleetListChanged) {
		this.fleetListChanged = fleetListChanged;
	}

	public void setFlatTime(double flatTime) {
		this.flatTime = flatTime;
	}

	public void setDnRampTime(double dnRampTime) {
		this.dnRampTime = dnRampTime;
	}

	public void setDutyShape(String dutyShape) {
		this.dutyShape = dutyShape;
	}

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		this.dutyShapeObj = dutyShapeObj;
	}

	public void setKWTarget(double kWTarget) {
		this.kWTarget = kWTarget;
	}

	public void setKWThreshold(double kWThreshold) {
		this.kWThreshold = kWThreshold;
	}

	public void setPctkWBand(double pctkWBand) {
		this.pctkWBand = pctkWBand;
	}

	public void setPFTarget(double pFTarget) {
		PFTarget = pFTarget;
	}

	public void setHalfPFBand(double halfPFBand) {
		this.halfPFBand = halfPFBand;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public void setDischargeMode(StorageControlMode dischargeMode) {
		this.dischargeMode = dischargeMode;
	}

	public void setChargeTriggerTime(double chargeTriggerTime) {
		this.chargeTriggerTime = chargeTriggerTime;
	}

	public void setPctKWRate(double pctKWRate) {
		this.pctKWRate = pctKWRate;
	}

	public void setPctKVArRate(double pctKVArRate) {
		this.pctKVArRate = pctKVArRate;
	}

	public void setPctChargeRate(double pctChargeRate) {
		this.pctChargeRate = pctChargeRate;
	}

	public void setPctFleetReserve(double pctFleetReserve) {
		this.pctFleetReserve = pctFleetReserve;
	}

	public void setDispatchVars(boolean dispatchVars) {
		this.dispatchVars = dispatchVars;
	}

	public void setInhibitHrs(int inhibitHrs) {
		this.inhibitHrs = inhibitHrs;
	}

	public void setUpRampTime(double upRampTime) {
		this.upRampTime = upRampTime;
	}

	public void setYearlyShape(String yearlyShape) {
		this.yearlyShape = yearlyShape;
	}

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		this.yearlyShapeObj = yearlyShapeObj;
	}

	public void setDailyShape(String dailyShape) {
		this.dailyShape = dailyShape;
	}

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		this.dailyShapeObj = dailyShapeObj;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		this.monitoredElement = monitoredElement;
	}

	public void setHalfKWBand(double halfKWBand) {
		this.halfKWBand = halfKWBand;
	}

}

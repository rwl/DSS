package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;

import com.epri.dss.shared.Dynamics;
import org.apache.commons.math.complex.Complex;

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
		kWThreshold,
		pctkWBand,
		halfKWBand,
		PFTarget,    // range on this is 0..2 where 1..2 is leading
		totalWeight;
	private double halfPFBand;
	private double PFBand;
	private double kWNeeded;
	private int fleetSize;
	private int fleetState;

	private List<String> storageNameList;
	private List<Object> fleetPointerList;
	private double[] weights;

	private boolean elementListSpecified;

	private int dischargeMode;
	private int chargeMode;
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

	private MutableDouble totalKWCapacity = new MutableDouble();
	private MutableDouble totalKWhCapacity = new MutableDouble();

	private String yearlyShape;          // ="fixed" means no variation  on all the time
	private LoadShapeObj yearlyShapeObj; // shape for this Storage element
	private String dailyShape;           // daily (24 HR) storage element shape
	private LoadShapeObj dailyShapeObj;  // daily storage element Shape for this load
	private String dutyShape;            // duty cycle load shape for changes typically less than one hour
	private LoadShapeObj dutyShapeObj;   // shape for this storage element

	private Complex loadShapeMult;

	private CktElement monitoredElement;

	public StorageControllerObjImpl(DSSClassImpl parClass, String storageControllerName) {
		super(parClass);
		setName(storageControllerName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors

		elementName       = "";
		setControlledElement(null);  // not used in this control
		elementTerminal   = 0;
		monitoredElement  = null;

		storageNameList  = new ArrayList<String>();
		weights          = null;
		fleetPointerList = new ArrayList<Object>(20);  // default size and increment
		fleetSize        = 0;
		fleetState       = Storage.IDLING;
		kWTarget         = 8000.0;
		kWThreshold      = 6000.0;
		pctkWBand        = 2.0;
		totalWeight      = 1.0;
		halfKWBand       = pctkWBand / 200.0 * kWTarget;
		PFTarget         = 0.96;
		setPFBand(0.04);
		halfPFBand       = PFBand / 2.0;
		kWNeeded         = 0.0;

		dischargeMode = StorageController.PEAKSHAVE;
		chargeMode    = StorageController.TIME;

		dischargeTriggerTime = -1.0;  // disabled
		chargeTriggerTime    = 2.0;   // 2 AM
		elementListSpecified = false;
		fleetListChanged     = true;  // force building of list
		pctKWRate            = 20.0;
		pctKVArRate          = 20.0;
		pctChargeRate        = 20.0;
		pctFleetReserve      = 25.0;

		showEventLog         = false;
		dispatchVars         = false;
		dischargeTriggeredByTime = false;
		dischargeInhibited   = false;
		outOfEnergy           = false;
		inhibitHrs           = 5;   // no. hours to inhibit discharging after going into charge mode

		upRampTime = 0.25;  // hr
		flatTime   = 2.0;
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
			return String.format("%-.6g", Utilities.convertPFRange2ToPF(PFTarget));
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
		StorageObj pStorage;
		double result = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			result = result + pStorage.getPresentKW();
		}
		return result;
	}

	public double getFleetkWh() {
		StorageObj pStorage;
		double result = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			result = result + pStorage.getKWhStored();
		}
		return result;
	}

	public double getFleetReserveKWh() {
		StorageObj pStorage;
		double result = 0;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			result = result + pStorage.getKWhReserve();
		}
		return result;
	}

	/**
	 * Recalculate critical element values after changes have been made.
	 */
	@Override
	public void recalcElementData() {

		/* Check for existence of monitored element */
		int devIndex = Utilities.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex);
			if (elementTerminal > monitoredElement.getNTerms()) {
				DSSGlobals.doErrorMsg("StorageController: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				setNPhases( monitoredElement.getNPhases() );
				setNConds(nPhases);
				// sets name of i-th terminal's connected bus in StorageController's bus list
				setBus(0, monitoredElement.getBus(elementTerminal));
			}
		} else {
			DSSGlobals.doSimpleMsg("Monitored element in StorageController."+getName()+ " does not exist:\""+elementName+"\"", 372);
		}

		if (fleetListChanged)
			if (!makeFleetList())
				DSSGlobals.doSimpleMsg("No unassigned storage elements found to assign to StorageController."+getName(), 37201);

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
			setNPhases( monitoredElement.getNPhases() );
			setNConds(nPhases);
			setBus(0, monitoredElement.getBus(elementTerminal));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
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

	private String getkWActual() {
		return String.format("%-.8g", getFleetKW());
	}

	private String getkWhActual() {
		return String.format("%-.8g", getFleetkWh());
	}

	private String getkWhTotal(MutableDouble sum) {
		StorageObj pStorage;
		sum.setValue(0);
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			sum.add(pStorage.getKWhRating());
		}
		return String.format("%-.8g", sum);
	}

	private String getkWTotal(MutableDouble sum) {
		StorageObj pStorage;
		sum.setValue(0);
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			sum.add(pStorage.getKWRating());
		}
		return String.format("%-.8g", sum);
	}

	private String getModeString(int opt, int mode) {
		switch (opt) {
		case StorageController.MODE_DISCHARGE:
			switch (mode) {
			case StorageController.FOLLOW:
				return "Follow";
			case StorageController.LOADSHAPE:
				return "Loadshape";
			case StorageController.SUPPORT:
				return "Support";
			case StorageController.TIME:
				return "Time";
			case StorageController.PEAKSHAVE:
				return "Peakshave";
			default:
				return "UNKNOWN";
			}
		case StorageController.MODE_CHARGE:
			switch (mode) {
			/*case 1:
				return "Follow";*/
			case StorageController.LOADSHAPE:
				return "Loadshape";
			/*case 3:
				return "Support";*/
			case StorageController.TIME:
				return "Time";
			default:
				return "UNKNOWN";
			}
		default:
			DSSGlobals.doSimpleMsg("Unknown charge/discharge designation", 14401);
			return "";
		}
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
		/* Release the discharge inhibit.
		 * Do nothing for other codes.
		 */
		if (code == StorageController.RELEASE_INHIBIT && dischargeMode != StorageController.FOLLOW)
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

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (dischargeTriggerTime > 0.0) {
			// turn on if time within 1/2 time step
			if (!(fleetState == Storage.DISCHARGING)) {
				chargingAllowed = true;
				tDiff = normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - dischargeTriggerTime;
				if (Math.abs(tDiff) < sol.getDynaVars().h / 7200.0) {
					/* Time is within 1 time step of the trigger time */
					if (showEventLog)
						Utilities.appendToEventLog("StorageController." + getName(), "Fleet set to discharging (up ramp) by schedule");
					setFleetToDisCharge();
					chargingAllowed = false;
					pctDischargeRate = Math.min(pctKWRate, Math.max(pctKWRate * tDiff / upRampTime, 0.0));
					setFleetkWRate(pctDischargeRate);
					dischargeInhibited = false;

					sol.setLoadsNeedUpdating(true);  // force recalc of power parms
					// push present time onto control queue to force re solve at new dispatch value
					ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, this);
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
						if (showEventLog)
							Utilities.appendToEventLog("StorageController." + getName(), "Fleet set to idling by schedule");

					} else {  // we're on the down ramp

						tDiff = upPlusFlatPlusDn - tDiff;
						pctDischargeRate = Math.max(0.0, Math.min(pctKWRate * tDiff / dnRampTime, pctKWRate));
						setFleetkWRate(pctDischargeRate);
					}
				}

				if (pctDischargeRate != lastPctDischargeRate) {
					sol.setLoadsNeedUpdating(true);  // force recalc of power parms
					// push present time onto control queue to force re solve at new dispatch value
					ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, this);
				}
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		switch (opt) {
		case 1:
			if (dischargeTriggerTime > 0.0) {
				// turn on if time within 1/2 time step
				if (Math.abs(normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - dischargeTriggerTime) < sol.getDynaVars().h / 7200.0) {
					if (!(fleetState == Storage.DISCHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (showEventLog)
							Utilities.appendToEventLog("StorageController." + getName(), "Fleet set to discharging by time trigger");
						setFleetToDisCharge();
						setFleetkWRate(pctKWRate);
						dischargeInhibited = false;
						if (dischargeMode == StorageController.FOLLOW) {
							dischargeTriggeredByTime = true;
						} else {
							sol.setLoadsNeedUpdating(true);  // force recalc of power parms
							// push present time onto control queue to force re solve at new dispatch value
							ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, this);
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
					if (!(fleetState == Storage.CHARGING)) {
						/* Time is within 1 time step of the trigger time */
						if (showEventLog)
							Utilities.appendToEventLog("StorageController." + getName(), "Fleet set to charging by time trigger");
						setFleetToCharge();
						dischargeInhibited = true;
						outOfEnergy        = false;

						sol.setLoadsNeedUpdating(true);  // force recalc of power parms
						// push present time onto control queue to force re solve at new dispatch value
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.CHARGING, 0, this);
						ckt.getControlQueue().push(sol.getIntHour() + inhibitHrs, sol.getDynaVars().t, StorageController.RELEASE_INHIBIT, 0, this);
					}
				}
			}
			break;
		}
	}

	/**
	 * Normalise time to a floating point number representing time of day if Hour > 24.
	 * time should be 0 to 23.999999.
	 */
	private double normalizeToTOD(int h, double sec) {
		int hourOfDay;
		double result;

		if (h > 23) {
			hourOfDay = (h - (h / 24) * 24);
		} else {
			hourOfDay = h;
		}

		result = hourOfDay + sec / 3600.0;

		if (result >= 24.0)
			result = result - 24.0;  // wrap around

		return result;
	}

	private void doLoadFollowMode() {
		int i;
		double PDiff, PFDiff;
		Complex S;
		StorageObj pStorage;
		boolean storeKWChanged, storeKVArChanged;
		double dispatchKW, dispatchKVAr;
		boolean skipKWDispatch;
		double remainingKWh, reserveKWh;

		// if list is not defined, go make one from all storage elements in circuit
		if (fleetPointerList.size() == 0)
			makeFleetList();

		if (fleetSize > 0) {

			storeKWChanged   = false;
			storeKVArChanged = false;
			skipKWDispatch   = false;

			//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
			S = monitoredElement.getPower(elementTerminal);  // power in active terminal
			switch (dischargeMode) {
			// following load; try to keep load below kW Target
			case StorageController.FOLLOW:
				if (dischargeTriggeredByTime) {
					if (showEventLog)
						Utilities.appendToEventLog("StorageController." + getName(),
								String.format("Fleet set to discharging by time trigger; Old kWTarget = %-.6g; New = 5-.6g", kWTarget, S.getReal() * 0.001));
					kWTarget = Math.max(kWThreshold, S.getReal() * 0.001);  // capture present kW and reset target
					dischargeTriggeredByTime = false;  // so we don't come back in here right away
					setFleetToIdle();
				}
				PDiff  = S.getReal() * 0.001 - kWTarget;  // assume S.re is normally positive
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for peak shaving
				break;
			// supporting DG; try to keep load above kW target
			case StorageController.SUPPORT:
				PDiff  = S.getReal() * 0.001 + kWTarget;  // assume S.re is normally negative
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for generator
				break;
			case StorageController.PEAKSHAVE:
				PDiff  = S.getReal() * 0.001 - kWTarget;  // assume S.re is normally positive
				PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - PFTarget;  // for peak shaving
				break;
			default:
				PDiff = 0.0;
				PFDiff = 0.0;
				break;
			}

			kWNeeded = PDiff;

			/* kW dispatch */

			if (dischargeInhibited) {
				skipKWDispatch = true;
			} else {
				if (fleetState == Storage.CHARGING)
					PDiff = PDiff + getFleetKW();  // ignore overload due to charging

				switch (fleetState) {
				case Storage.CHARGING:
					if (PDiff < 0.0 || outOfEnergy) {
						// don't bother trying to dispatch
						chargingAllowed = true;
						skipKWDispatch  = true;
					}
					break;
				case Storage.IDLING:
					if (PDiff < 0.0 || outOfEnergy) {
						// don't bother trying to dispatch
						chargingAllowed = true;
						skipKWDispatch  = true;
					}
					break;
				case Storage.DISCHARGING:
					if (PDiff + getFleetKW() < 0.0 || outOfEnergy) {
						// desired decrease is greater then present output; just cancel
						setFleetToIdle();  // also sets presentkW = 0
						chargingAllowed = true;
						skipKWDispatch  = true;
					}
					break;
				}
			}

			if (!skipKWDispatch) {
				remainingKWh = getFleetkWh();
				reserveKWh   = getFleetReserveKWh();
				if (remainingKWh > reserveKWh) {
					// don't dispatch kW  if not enough storage left or an endless control loop will occur
					if (Math.abs(PDiff) > halfKWBand) {
						// attempt to change storage dispatch
						if (!(fleetState == Storage.DISCHARGING))
							setFleetToDisCharge();
						if (showEventLog)
							Utilities.appendToEventLog("StorageController." + getName(),
									String.format("Attempting to dispatch %-.6g kW with %-.6g kWh remaining and %-.6g reserve.", kWNeeded, remainingKWh, reserveKWh));
						for (i = 0; i < fleetSize; i++) {
							pStorage = (StorageObj) fleetPointerList.get(i);
							// compute new dispatch value for this storage element ...
							dispatchKW = Math.min(pStorage.getKWRating(), (pStorage.getPresentKW() + PDiff *(weights[i] / totalWeight)));
							if (dispatchKW != pStorage.getPresentKW())  // redispatch only if change requested
								if (pStorage.getKWhStored() > pStorage.getKWhReserve()) {
									// attempt to set discharge kW; storage element will revert to idling if out of capacity
									pStorage.setPresentKW(dispatchKW);
									storeKWChanged = true;  // this is what keeps the control iterations going
								}
						}
					}
				} else {
					if (!(getFleetState() == Storage.IDLING))
						setFleetToIdle();
					chargingAllowed = true;
					outOfEnergy = true;
					if (showEventLog)
						Utilities.appendToEventLog("StorageController." + getName(),
								String.format("Ran out of energy: %-.6g kWh remaining and %-.6g reserve.", remainingKWh, reserveKWh));
				}
			}

			// kVAr dispatch Note: PFDiff computed from PF in range of 0..2
			// redispatch the vars only if the PF is outside the band
			if (dispatchVars && Math.abs(PFDiff) > halfPFBand) {
				if (showEventLog)
					Utilities.appendToEventLog("StorageController." + getName(),
							String.format("Changed kvar dispatch. PF diff needed = %.6g", PFDiff));
				// redispatch storage elements
				for (i = 0; i < fleetSize; i++) {
					pStorage = (StorageObj) fleetPointerList.get(i);
					// compute new var dispatch value for this storage element ...
					if (PFTarget == 1.0) {
						dispatchKVAr = 0.0;
					} else {
						dispatchKVAr = S.getReal() * Math.sqrt(1.0 / Math.pow(Utilities.convertPFRange2ToPF(PFTarget), 2) - 1.0) * (weights[i] / totalWeight);
						if (PFTarget > 1.0)
							dispatchKVAr = -dispatchKVAr;  // for watts and vars in opposite direction
					}

					if (dispatchKVAr != pStorage.getPresentKVAr()) {
						pStorage.setPresentKVAr(dispatchKVAr);  // ask for this much kvar but may be limited by element
						storeKVArChanged = true;
					}
				}
			}

			if (storeKWChanged || storeKVArChanged) {  // only push onto control queue if there has been a change
				Circuit ckt = DSSGlobals.activeCircuit;
				SolutionObj sol = ckt.getSolution();

				sol.setLoadsNeedUpdating(true);  // force recalc of power parms
				// push present time onto control queue to force re solve at new dispatch value
				ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, this);
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
		case StorageController.FOLLOW:
			doTimeMode(1);
			doLoadFollowMode();
			break;
		case StorageController.LOADSHAPE:
			doLoadShapeMode();
			break;
		case StorageController.SUPPORT:
			doLoadFollowMode();
			break;
		case StorageController.TIME:
			doTimeMode(1);
			break;
		case StorageController.PEAKSHAVE:
			doLoadFollowMode();
			break;
		case StorageController.SCHEDULE:
			doScheduleMode();
			break;
		default:
			DSSGlobals.doSimpleMsg(String.format("Invalid discharging mode: %d", dischargeMode), 14408);
			break;
		}

		if (chargingAllowed)
			switch (chargeMode) {
			case StorageController.LOADSHAPE:
				//doLoadShapeMode();  already executed above
				break;
			case StorageController.TIME:
				doTimeMode(2);
				break;
			default:
				DSSGlobals.doSimpleMsg(String.format("Invalid charging mode: %d", chargeMode), 14409);
				break;
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
		int fleetStateSaved;
		boolean rateChanged;
		double newChargeRate;
		double newKWRate, newKVArRate;

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		fleetStateSaved = fleetState;
		rateChanged     = false;

		// get multiplier
		switch (sol.getMode()) {
		case Dynamics.DAILYMODE:
			calcDailyMult(sol.getDblHour());  // daily dispatch curve
			break;
		case Dynamics.YEARLYMODE:
			calcYearlyMult(sol.getDblHour());
			break;
		case Dynamics.LOADDURATION2:
			calcDailyMult(sol.getDblHour());
			break;
		case Dynamics.PEAKDAY:
			calcDailyMult(sol.getDblHour());
			break;
		case Dynamics.DUTYCYCLE:
			calcDutyMult(sol.getDblHour()) ;
			break;
		}

		if (loadShapeMult.getReal() < 0.0) {
			chargingAllowed = true;
			newChargeRate = Math.abs(loadShapeMult.getReal()) * 100.0;
			if (newChargeRate != pctChargeRate)
				rateChanged = true;
			pctChargeRate = newChargeRate;
			setFleetChargeRate();
			setFleetToCharge();
		} else if (loadShapeMult.getReal() == 0.0) {
			setFleetToIdle();
		} else {
			// set fleet to discharging at a rate
			newKWRate   = loadShapeMult.getReal() * 100.0;
			newKVArRate = loadShapeMult.getImaginary() * 100.0;
			if (newKWRate != pctKWRate || newKVArRate != pctKVArRate)
				rateChanged = true;
			pctKWRate   = newKWRate;
			pctKVArRate = newKVArRate;
			setFleetkWRate(pctKWRate);
			setFleetkvarRate(pctKVArRate);
			setFleetToDisCharge();
			sol.setLoadsNeedUpdating(true);  // force recalc of power parms
		}

		if (fleetState != fleetStateSaved || rateChanged) {
			sol.setLoadsNeedUpdating(true);  // force recalc of power parms
			// push present time onto control queue to force re solve at new dispatch value
			ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, this);
		}
	}
	private void setAllFleetValues() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setPctKWin(pctChargeRate);
			pStorage.setPctKVArOut(pctKVArRate);
			pStorage.setPctKWOut(pctKWRate);
			pStorage.setPctReserve(pctFleetReserve);
		}
	}

	private void setFleetChargeRate() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setPctKWin(pctChargeRate);
		}
	}

	private void setFleetkvarRate(double pctkvar) {
		StorageObj pStorage;
		/* For side effects see pctKVArOut property of storage element */
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setPctKVArOut(pctKVArRate);
		}
	}

	private void setFleetkWRate(double pctkw) {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setPctKWOut(pctkw);
		}
	}

	private void setFleetToCharge() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setState(Storage.CHARGING);
		}
		fleetState = Storage.CHARGING;
	}

	private void setFleetToDisCharge() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setState(Storage.DISCHARGING);
		}
		fleetState = Storage.DISCHARGING;
	}

	private void setFleetToIdle() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setState(Storage.IDLING);
			pStorage.setPresentKW(0.0);
		}
		fleetState = Storage.IDLING;
	}

	public void setPFBand(double value) {
		PFBand = value;
		halfPFBand = PFBand / 2.0;
	}

	public double getPFBand() {
		return PFBand;
	}

	private void setFleetToExternal() {
		StorageObj pStorage;
		for (int i = 0; i < fleetPointerList.size(); i++) {
			pStorage = (StorageObj) fleetPointerList.get(i);
			pStorage.setState(Storage.EXTERNAL_MODE);
		}
	}

//	private void setPctReserve() {
//		StorageObj pStorage;
//		for (int i = 0; i < FleetPointerList.size(); i++) {
//			pStorage = (StorageObj) FleetPointerList.get(i);
//			pStorage.setPctReserve(pctFleetReserve);
//		}
//	}

	// FIXME Private method in OpenDSS
	public int interpretMode(int opt, String s) {
		switch (opt) {
		case StorageController.MODE_DISCHARGE:
			switch (s.toLowerCase().charAt(0)) {
			case 'f':
				return StorageController.FOLLOW;
			case 'l':
				return StorageController.LOADSHAPE;
			case 'p':
				return StorageController.PEAKSHAVE;
			case 's':
				if (s.toLowerCase().charAt(1) == 'c') {
					return StorageController.SCHEDULE;
				} else {
					return StorageController.SUPPORT;
				}
			case 't':
				return StorageController.TIME;
			default:
				DSSGlobals.doSimpleMsg("Discharge Mode \"" + s + "\" not recognized.", 14402);
			}
			break;
		case StorageController.MODE_CHARGE:
			switch (s.toLowerCase().charAt(0)) {
			/*case 'f':
				return StorageController.MODEFOLLOW;*/
			case 'l':
				return StorageController.LOADSHAPE;
			/*case 's':
				return StorageController.MODESUPPORT;*/
			case 't':
				return StorageController.TIME;
			default:
				DSSGlobals.doSimpleMsg("Charge Mode \"" + s + "\" not recognized.", 14402);
			}
			break;
		default:
			break;
		}
		return 0;
	}

	private boolean makeFleetList() {
		StorageObj pStorage;
		int i;
		boolean result = false;

		if (elementListSpecified) {  // name list is defined - use it

			fleetPointerList.clear();
			for (i = 0; i < fleetSize; i++) {
				pStorage = (StorageObj) DSSGlobals.storageClass.find(storageNameList.get(i));
				if (pStorage != null) {
					if (pStorage.isEnabled())
						fleetPointerList.add(pStorage);
				} else {
					DSSGlobals.doSimpleMsg("Error: Storage element \"" + storageNameList.get(i) + "\" not found.", 14403);
					return result;
				}
			}

		} else {

			/* Search through the entire circuit for enabled storage elements and add them to the list */
			storageNameList.clear();
			fleetPointerList.clear();
			for (i = 0; i < DSSGlobals.storageClass.getElementCount(); i++) {
				pStorage = (StorageObj) DSSGlobals.storageClass.getElementList().get(i);
				// Look for a storage element not already assigned
				if (pStorage.isEnabled() && (pStorage.getDispatchMode() != Storage.EXTERNAL_MODE)) {
					storageNameList.add(pStorage.getName());  // add to list of names
					fleetPointerList.add(pStorage);
				}
			}

			/* Allocate uniform weights */
			fleetSize = fleetPointerList.size();
			weights = Utilities.resizeArray(weights, fleetSize);
			for (i = 0; i < fleetSize; i++)
				weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < fleetSize; i++)
			totalWeight = totalWeight + weights[i];

		if (fleetPointerList.size() > 0)
			result = true;

		fleetListChanged = false;

		return result;
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
		if (fleetSize == 0)
			return "";

		String result = "[" + storageNameList.get(0);
		for (int i = 0; i < fleetSize - 1; i++)
			result = result + ", " + storageNameList.get(i);
		result = result + "]";  // terminate the array

		return result;
	}

	private String returnWeightsList() {
		if (fleetSize == 0)
			return "";

		String result = "["+ String.format("%-.6g", weights[0]);
		for (int i = 1; i < fleetSize; i++)
			result = result + String.format(", %-.6g", weights[i]);
		result = result + "]";  // terminate the array

		return result;
	}

	// FIXME Private members in OpenDSS

	public double getKWTarget() {
		return kWTarget;
	}

	public void setKWTarget(double target) {
		this.kWTarget = target;
	}


	public void setKWThreshold(double threshold) {
		this.kWThreshold = threshold;
	}

	public double getKWThreshold() {
		return kWThreshold;
	}

	public double getPctKWBand() {
		return pctkWBand;
	}


	public void setPctKWBand(double band) {
		this.pctkWBand = band;
	}


	public double getHalfKWBand() {
		return halfKWBand;
	}


	public void setHalfKWBand(double band) {
		halfKWBand = band;
	}


	public double getPFTarget() {
		return PFTarget;
	}


	public void setPFTarget(double target) {
		PFTarget = target;
	}


	public double getTotalWeight() {
		return totalWeight;
	}


	public void setTotalWeight(double weight) {
		totalWeight = weight;
	}


	public double getHalfPFBand() {
		return halfPFBand;
	}


	public void setHalfPFBand(double band) {
		halfPFBand = band;
	}


	public double getKWNeeded() {
		return kWNeeded;
	}


	public void setKWNeeded(double needed) {
		this.kWNeeded = needed;
	}


	public int getFleetSize() {
		return fleetSize;
	}


	public void setFleetSize(int size) {
		fleetSize = size;
	}


	public int getFleetState() {
		return fleetState;
	}


	public void setFleetState(int state) {
		fleetState = state;
	}


	public List<String> getStorageNameList() {
		return storageNameList;
	}


	public void setStorageNameList(List<String> list) {
		storageNameList = list;
	}


	public List<Object> getFleetPointerList() {
		return fleetPointerList;
	}


	public void setFleetPointerList(List<Object> list) {
		fleetPointerList = list;
	}


	public double[] getWeights() {
		return weights;
	}


	public void setWeights(double[] values) {
		weights = values;
	}


	public boolean isElementListSpecified() {
		return elementListSpecified;
	}


	public void setElementListSpecified(boolean value) {
		elementListSpecified = value;
	}


	public int getDischargeMode() {
		return dischargeMode;
	}


	public void setDischargeMode(int mode) {
		dischargeMode = mode;
	}


	public int getChargeMode() {
		return chargeMode;
	}


	public void setChargeMode(int mode) {
		chargeMode = mode;
	}


	public double getDischargeTriggerTime() {
		return dischargeTriggerTime;
	}


	public void setDischargeTriggerTime(double time) {
		dischargeTriggerTime = time;
	}


	public double getChargeTriggerTime() {
		return chargeTriggerTime;
	}


	public void setChargeTriggerTime(double time) {
		chargeTriggerTime = time;
	}


	public double getPctKWRate() {
		return pctKWRate;
	}


	public void setPctKWRate(double pct) {
		this.pctKWRate = pct;
	}


	public double getPctKVArRate() {
		return pctKVArRate;
	}


	public void setPctKVArRate(double pct) {
		this.pctKVArRate = pct;
	}


	public double getPctChargeRate() {
		return pctChargeRate;
	}


	public void setPctChargeRate(double pct) {
		this.pctChargeRate = pct;
	}


	public double getPctFleetReserve() {
		return pctFleetReserve;
	}


	public void setPctFleetReserve(double pct) {
		this.pctFleetReserve = pct;
	}


	public boolean isFleetListChanged() {
		return fleetListChanged;
	}


	public void setFleetListChanged(boolean changed) {
		fleetListChanged = changed;
	}


	public boolean isChargingAllowed() {
		return chargingAllowed;
	}


	public void setChargingAllowed(boolean allowed) {
		chargingAllowed = allowed;
	}


	public boolean isShowEventLog() {
		return showEventLog;
	}


	public void setShowEventLog(boolean show) {
		showEventLog = show;
	}


	public boolean isDispatchVars() {
		return dispatchVars;
	}


	public void setDispatchVars(boolean vars) {
		dispatchVars = vars;
	}


	public boolean isDischargeTriggeredByTime() {
		return dischargeTriggeredByTime;
	}


	public void setDischargeTriggeredByTime(boolean value) {
		dischargeTriggeredByTime = value;
	}


	public boolean isDischargeInhibited() {
		return dischargeInhibited;
	}


	public void setDischargeInhibited(boolean inhibited) {
		dischargeInhibited = inhibited;
	}


	public boolean isOutOfEnergy() {
		return outOfEnergy;
	}


	public void setOutOfEnergy(boolean value) {
		outOfEnergy = value;
	}


	public int getInhibitHrs() {
		return inhibitHrs;
	}


	public void setInhibitHrs(int hrs) {
		inhibitHrs = hrs;
	}

	public double getUpRampTime() {
		return upRampTime;
	}

	public void setUpRampTime(double time) {
		upRampTime = time;
	}

	public double getFlatTime() {
		return flatTime;
	}

	public void setFlatTime(double time) {
		flatTime = time;
	}

	public double getDnRampTime() {
		return dnRampTime;
	}

	public void setDnRampTime(double time) {
		dnRampTime = time;
	}

	public double getUpPlusFlat() {
		return upPlusFlat;
	}

	public void setUpPlusFlat(double value) {
		upPlusFlat = value;
	}

	public double getUpPlusFlatPlusDn() {
		return upPlusFlatPlusDn;
	}

	public void setUpPlusFlatPlusDn(double value) {
		upPlusFlatPlusDn = value;
	}

	public double getLastpctDischargeRate() {
		return lastPctDischargeRate;
	}

	public void setLastpctDischargeRate(double rate) {
		lastPctDischargeRate = rate;
	}

	public double getTotalKWCapacity() {
		return totalKWCapacity.doubleValue();
	}


	public void setTotalKWCapacity(double capacity) {
		totalKWCapacity.setValue(capacity);
	}


	public double getTotalKWhCapacity() {
		return totalKWhCapacity.doubleValue();
	}


	public void setTotalKWhCapacity(double capacity) {
		totalKWhCapacity.setValue(capacity);
	}


	public String getYearlyShape() {
		return yearlyShape;
	}


	public void setYearlyShape(String shape) {
		yearlyShape = shape;
	}


	public LoadShapeObj getYearlyShapeObj() {
		return yearlyShapeObj;
	}


	public void setYearlyShapeObj(LoadShapeObj yearlyShape) {
		yearlyShapeObj = yearlyShape;
	}


	public String getDailyShape() {
		return dailyShape;
	}


	public void setDailyShape(String shape) {
		dailyShape = shape;
	}


	public LoadShapeObj getDailyShapeObj() {
		return dailyShapeObj;
	}


	public void setDailyShapeObj(LoadShapeObj dailyShape) {
		dailyShapeObj = dailyShape;
	}


	public String getDutyShape() {
		return dutyShape;
	}


	public void setDutyShape(String shape) {
		dutyShape = shape;
	}


	public LoadShapeObj getDutyShapeObj() {
		return dutyShapeObj;
	}


	public void setDutyShapeObj(LoadShapeObj dutyShape) {
		dutyShapeObj = dutyShape;
	}


	public Complex getLoadShapeMult() {
		return loadShapeMult;
	}


	public void setLoadShapeMult(Complex mult) {
		loadShapeMult = mult;
	}


	public CktElement getMonitoredElement() {
		return monitoredElement;
	}


	public void setMonitoredElement(CktElement element) {
		monitoredElement = element;
	}

}

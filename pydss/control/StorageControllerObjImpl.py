from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.shared.Dynamics import (Dynamics,)
from dss.control.StorageController import (StorageController,)
from dss.conversion.Storage import (Storage,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.StorageControllerObj import (StorageControllerObj,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class StorageControllerObjImpl(ControlElemImpl, StorageControllerObj):
    CDOUBLEONE = Complex(1.0, 1.0)
    kWTarget = None
    kWThreshold = None
    pctkWBand = None
    halfKWBand = None
    PFTarget = None
    totalWeight = None
    # range on this is 0..2 where 1..2 is leading
    halfPFBand = None
    PFBand = None
    kWNeeded = None
    fleetSize = None
    fleetState = None
    storageNameList = None
    fleetPointerList = None
    weights = None
    elementListSpecified = None
    dischargeMode = None
    chargeMode = None
    dischargeTriggerTime = None
    chargeTriggerTime = None
    pctKWRate = None
    pctKVArRate = None
    pctChargeRate = None
    pctFleetReserve = None
    fleetListChanged = None
    chargingAllowed = None
    showEventLog = None
    dispatchVars = None
    dischargeTriggeredByTime = None
    dischargeInhibited = None
    outOfEnergy = None
    inhibitHrs = None
    upRampTime = None
    flatTime = None
    dnRampTime = None
    upPlusFlat = None
    upPlusFlatPlusDn = None
    lastPctDischargeRate = None
    totalKWCapacity = MutableDouble()
    totalKWhCapacity = MutableDouble()
    yearlyShape = None
    # ="fixed" means no variation  on all the time
    yearlyShapeObj = None
    # shape for this Storage element
    dailyShape = None
    # daily (24 HR) storage element shape
    dailyShapeObj = None
    # daily storage element Shape for this load
    dutyShape = None
    # duty cycle load shape for changes typically less than one hour
    dutyShapeObj = None
    # shape for this storage element
    loadShapeMult = None
    monitoredElement = None

    def __init__(self, parClass, storageControllerName):
        super(StorageControllerObjImpl, self)(parClass)
        self.setName(storageControllerName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors
        self.elementName = ''
        self.setControlledElement(None)
        # not used in this control
        self.elementTerminal = 1
        self.monitoredElement = None
        self.storageNameList = list()
        self.weights = None
        self.fleetPointerList = list(20)
        # default size and increment
        self.fleetSize = 0
        self.fleetState = Storage.IDLING
        self.kWTarget = 8000.0
        self.kWThreshold = 6000.0
        self.pctkWBand = 2.0
        self.totalWeight = 1.0
        self.halfKWBand = (self.pctkWBand / 200.0) * self.kWTarget
        self.PFTarget = 0.96
        self.setPFBand(0.04)
        self.halfPFBand = self.PFBand / 2.0
        self.kWNeeded = 0.0
        self.dischargeMode = StorageController.PEAKSHAVE
        self.chargeMode = StorageController.TIME
        self.dischargeTriggerTime = -1.0
        # disabled
        self.chargeTriggerTime = 2.0
        # 2 AM
        self.elementListSpecified = False
        self.fleetListChanged = True
        # force building of list
        self.pctKWRate = 20.0
        self.pctKVArRate = 20.0
        self.pctChargeRate = 20.0
        self.pctFleetReserve = 25.0
        self.showEventLog = False
        self.dispatchVars = False
        self.dischargeTriggeredByTime = False
        self.dischargeInhibited = False
        self.outOfEnergy = False
        self.inhibitHrs = 5
        # no. hours to inhibit discharging after going into charge mode
        self.upRampTime = 0.25
        # hr
        self.flatTime = 2.0
        self.dnRampTime = 0.25
        self.lastPctDischargeRate = 0.0
        self.initPropertyValues(0)

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(StorageController.ELEMENT, '')
        self.setPropertyValue(StorageController.TERMINAL, '1')
        self.setPropertyValue(StorageController.KW_TARGET, '8000')
        self.setPropertyValue(StorageController.KW_BAND, '2')
        self.setPropertyValue(StorageController.PF_TARGET, '.96')
        self.setPropertyValue(StorageController.PF_BAND, '.04')
        self.setPropertyValue(StorageController.ELEMENT_LIST, '')
        self.setPropertyValue(StorageController.WEIGHTS, '')
        self.setPropertyValue(StorageController.MODE_DISCHARGE, 'Follow')
        self.setPropertyValue(StorageController.MODE_CHARGE, 'Time')
        self.setPropertyValue(StorageController.TIME_DISCHARGE_TRIGGER, '-1')
        self.setPropertyValue(StorageController.TIME_CHARGE_TRIGGER, '2')
        self.setPropertyValue(StorageController.RATE_KW, '20')
        self.setPropertyValue(StorageController.RATE_KVAR, '20')
        self.setPropertyValue(StorageController.RATE_CHARGE, '20')
        self.setPropertyValue(StorageController.RESERVE, '25')
        self.setPropertyValue(StorageController.KWH_TOTAL, '')
        self.setPropertyValue(StorageController.KW_TOTAL, '')
        self.setPropertyValue(StorageController.KW_ACTUAL, '')
        self.setPropertyValue(StorageController.KW_NEED, '')
        self.setPropertyValue(StorageController.PARTICIPATION, '')
        self.setPropertyValue(StorageController.YEARLY, '')
        self.setPropertyValue(StorageController.DAILY, '')
        self.setPropertyValue(StorageController.DUTY, '')
        self.setPropertyValue(StorageController.EVENTLOG, 'No')
        self.setPropertyValue(StorageController.INHIBIT_TIME, '5')
        self.setPropertyValue(StorageController.T_UP_RAMP, '0.25')
        self.setPropertyValue(StorageController.T_FLAT, '2.0')
        self.setPropertyValue(StorageController.T_DN_RAMP, '0.25')
        self.setPropertyValue(StorageController.KW_THRESHOLD, '4000')
        super(StorageControllerObjImpl, self).initPropertyValues(StorageController.NumPropsThisClass)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == StorageController.KW_TARGET:
                _1 = True
                return String.format.format('%-.6g', self.kWTarget)
            if (_1 is True) or (_0 == StorageController.KW_BAND):
                _1 = True
                return String.format.format('%-.6g', self.pctkWBand)
            if (_1 is True) or (_0 == StorageController.PF_TARGET):
                _1 = True
                return String.format.format('%-.6g', Utilities.convertPFRange2ToPF(self.PFTarget))
            if (_1 is True) or (_0 == StorageController.PF_BAND):
                _1 = True
                return String.format.format('%-.6g', self.PFBand)
            if (_1 is True) or (_0 == StorageController.ELEMENT_LIST):
                _1 = True
                return self.returnElementsList()
            if (_1 is True) or (_0 == StorageController.WEIGHTS):
                _1 = True
                return self.returnWeightsList()
            if (_1 is True) or (_0 == StorageController.MODE_DISCHARGE):
                _1 = True
                return self.getModeString(StorageController.MODE_DISCHARGE, self.dischargeMode)
            if (_1 is True) or (_0 == StorageController.MODE_CHARGE):
                _1 = True
                return self.getModeString(StorageController.MODE_CHARGE, self.chargeMode)
            if (_1 is True) or (_0 == StorageController.TIME_DISCHARGE_TRIGGER):
                _1 = True
                return String.format.format('%.6g', self.dischargeTriggerTime)
            if (_1 is True) or (_0 == StorageController.TIME_CHARGE_TRIGGER):
                _1 = True
                return String.format.format('%.6g', self.chargeTriggerTime)
            if (_1 is True) or (_0 == StorageController.RATE_KW):
                _1 = True
                return String.format.format('%-.8g', self.pctKWRate)
            if (_1 is True) or (_0 == StorageController.RATE_KVAR):
                _1 = True
                return String.format.format('%-.8g', self.pctKVArRate)
            if (_1 is True) or (_0 == StorageController.RATE_CHARGE):
                _1 = True
                return String.format.format('%-.8g', self.pctChargeRate)
            if (_1 is True) or (_0 == StorageController.RESERVE):
                _1 = True
                return String.format.format('%-.8g', self.pctFleetReserve)
            if (_1 is True) or (_0 == StorageController.KWH_TOTAL):
                _1 = True
                return self.getkWhTotal(self.totalKWhCapacity)
            if (_1 is True) or (_0 == StorageController.KW_TOTAL):
                _1 = True
                return self.getkWTotal(self.totalKWCapacity)
            if (_1 is True) or (_0 == StorageController.KWH_ACTUAL):
                _1 = True
                return self.getkWhActual()
            if (_1 is True) or (_0 == StorageController.KW_ACTUAL):
                _1 = True
                return self.getkWActual()
            if (_1 is True) or (_0 == StorageController.KW_NEED):
                _1 = True
                return String.format.format('%-.6g', self.kWNeeded)
                # case StorageController.propPARTICIPATION:
                # 			return getPropertyValue(Index);

            if (_1 is True) or (_0 == StorageController.YEARLY):
                _1 = True
                return self.yearlyShape
            if (_1 is True) or (_0 == StorageController.DAILY):
                _1 = True
                return self.dailyShape
            if (_1 is True) or (_0 == StorageController.DUTY):
                _1 = True
                return self.dutyShape
            if (_1 is True) or (_0 == StorageController.EVENTLOG):
                _1 = True
                if self.showEventLog:
                    return 'Yes'
                else:
                    return 'No'
            if (_1 is True) or (_0 == StorageController.VAR_DISPATCH):
                _1 = True
                if self.dispatchVars:
                    return 'Yes'
                else:
                    return 'No'
            if (_1 is True) or (_0 == StorageController.INHIBIT_TIME):
                _1 = True
                return String.format.format('%d', self.inhibitHrs)
            if (_1 is True) or (_0 == StorageController.T_UP_RAMP):
                _1 = True
                return String.format.format('%.6g', self.upRampTime)
            if (_1 is True) or (_0 == StorageController.T_FLAT):
                _1 = True
                return String.format.format('%.6g', self.flatTime)
            if (_1 is True) or (_0 == StorageController.T_DN_RAMP):
                _1 = True
                return String.format.format('%.6g', self.dnRampTime)
            if (_1 is True) or (_0 == StorageController.KW_THRESHOLD):
                _1 = True
                return String.format.format('%.6g', self.kWThreshold)
            if True:
                _1 = True
                return super(StorageControllerObjImpl, self).getPropertyValue(index)
            break

    def getFleetKW(self):
        result = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            result = result + pStorage.getPresentKW()
        return result

    def getFleetkWh(self):
        result = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            result = result + pStorage.getKWhStored()
        return result

    def getFleetReserveKWh(self):
        result = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            result = result + pStorage.getKWhReserve()
        return result

    def recalcElementData(self):
        """Recalculate critical element values after changes have been made."""
        # Check for existence of monitored element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            self.monitoredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            if self.elementTerminal > self.monitoredElement.getNTerms():
                DSSGlobals.doErrorMsg('StorageController: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 371)
            else:
                self.setNPhases(self.monitoredElement.getNPhases())
                self.setNConds(self.nPhases)
                # sets name of i-th terminal's connected bus in StorageController's bus list
                self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
        else:
            DSSGlobals.doSimpleMsg('Monitored element in StorageController.' + self.getName() + ' does not exist:\"' + self.elementName + '\"', 372)
        if self.fleetListChanged:
            if not self.makeFleetList():
                DSSGlobals.doSimpleMsg('No unassigned storage elements found to assign to StorageController.' + self.getName(), 37201)
        self.getkWTotal(self.totalKWCapacity)
        self.getkWhTotal(self.totalKWhCapacity)
        if self.fleetSize > 0:
            self.setFleetToExternal()
            self.setAllFleetValues()
        self.upPlusFlat = self.upRampTime + self.flatTime
        self.upPlusFlatPlusDn = self.upPlusFlat + self.dnRampTime

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.monitoredElement is not None:
            self.setNPhases(self.monitoredElement.getNPhases())
            self.setNConds(self.nPhases)
            self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
        super(StorageControllerObjImpl, self).makePosSequence()

    def calcYPrim(self):
        # leave YPrims as null and they will be ignored
        pass

    def getCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def getInjCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def getkWActual(self):
        return String.format.format('%-.8g', self.getFleetKW())

    def getkWhActual(self):
        return String.format.format('%-.8g', self.getFleetkWh())

    def getkWhTotal(self, sum):
        # FIXME return total as double
        sum.setValue(0)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            sum.add(pStorage.getKWhRating())
        return String.format.format('%-.8g', sum)

    def getkWTotal(self, sum):
        # FIXME return total as double
        sum.setValue(0)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            sum.add(pStorage.getKWRating())
        return String.format.format('%-.8g', sum)

    def getModeString(self, opt, mode):
        _0 = opt
        _1 = False
        while True:
            if _0 == StorageController.MODE_DISCHARGE:
                _1 = True
                _2 = mode
                _3 = False
                while True:
                    if _2 == StorageController.FOLLOW:
                        _3 = True
                        return 'Follow'
                    if (_3 is True) or (_2 == StorageController.LOADSHAPE):
                        _3 = True
                        return 'Loadshape'
                    if (_3 is True) or (_2 == StorageController.SUPPORT):
                        _3 = True
                        return 'Support'
                    if (_3 is True) or (_2 == StorageController.TIME):
                        _3 = True
                        return 'Time'
                    if (_3 is True) or (_2 == StorageController.PEAKSHAVE):
                        _3 = True
                        return 'Peakshave'
                    if True:
                        _3 = True
                        return 'UNKNOWN'
                    break
            if (_1 is True) or (_0 == StorageController.MODE_CHARGE):
                _1 = True
                # case 1:
                # 				return "Follow";

                _4 = mode
                _5 = False
                while True:
                    if _4 == StorageController.LOADSHAPE:
                        _5 = True
                        return 'Loadshape'
                        # case 3:
                        # 				return "Support";

                    if (_5 is True) or (_4 == StorageController.TIME):
                        _5 = True
                        return 'Time'
                    if True:
                        _5 = True
                        return 'UNKNOWN'
                    break
            if True:
                _1 = True
                DSSGlobals.doSimpleMsg('Unknown charge/discharge designation', 14401)
                return ''
            break

    def dumpProperties(self, f, complete):
        super(StorageControllerObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 

    def doPendingAction(self, code, proxyHdl):
        """Do the action that is pending from last sample."""
        # Release the discharge inhibit.
        # Do nothing for other codes.

        if (
            code == StorageController.RELEASE_INHIBIT and self.dischargeMode != StorageController.FOLLOW
        ):
            self.dischargeInhibited = False

    def doScheduleMode(self):
        """In "schedule" mode we ramp up the storage from zero to the specified pctkWRate.
        This value is held for the flat time or until they turn themselves
        off when they are either fully discharged, or ramped down

        The discharge trigger time must be greater than 0
        """
        pctDischargeRate = 0.0
        # init for test
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if self.dischargeTriggerTime > 0.0:
            # turn on if time within 1/2 time step
            if not (self.fleetState == Storage.DISCHARGING):
                self.chargingAllowed = True
                TDiff = self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - self.dischargeTriggerTime
                if self.Math.abs(TDiff) < sol.getDynaVars().h / 7200.0:
                    # Time is within 1 time step of the trigger time
                    if self.showEventLog:
                        Utilities.appendToEventLog('StorageController.' + self.getName(), 'Fleet set to discharging (up ramp) by schedule')
                    self.setFleetToDisCharge()
                    self.chargingAllowed = False
                    pctDischargeRate = self.Math.min(self.pctKWRate, self.Math.max((self.pctKWRate * TDiff) / self.upRampTime, 0.0))
                    self.setFleetkWRate(pctDischargeRate)
                    self.dischargeInhibited = False
                    sol.setLoadsNeedUpdating(True)
                    # force recalc of power parms
                    # push present time onto control queue to force re solve at new dispatch value
                    ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, self)
            else:
                # fleet is already discharging
                TDiff = self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - self.dischargeTriggerTime
                if TDiff < self.upRampTime:
                    pctDischargeRate = self.Math.min(self.pctKWRate, self.Math.max((self.pctKWRate * TDiff) / self.upRampTime, 0.0))
                    self.setFleetkWRate(pctDischargeRate)
                elif TDiff < self.upPlusFlat:
                    pctDischargeRate = self.pctKWRate
                    if pctDischargeRate != self.lastPctDischargeRate:
                        self.setFleetkWRate(self.pctKWRate)
                        # on the flat part
                elif TDiff > self.upPlusFlatPlusDn:
                    self.setFleetToIdle()
                    self.chargingAllowed = True
                    pctDischargeRate = 0.0
                    if self.showEventLog:
                        Utilities.appendToEventLog('StorageController.' + self.getName(), 'Fleet set to idling by schedule')
                else:
                    # we're on the down ramp
                    TDiff = self.upPlusFlatPlusDn - TDiff
                    pctDischargeRate = self.Math.max(0.0, self.Math.min((self.pctKWRate * TDiff) / self.dnRampTime, self.pctKWRate))
                    self.setFleetkWRate(pctDischargeRate)
                if pctDischargeRate != self.lastPctDischargeRate:
                    sol.setLoadsNeedUpdating(True)
                    # force recalc of power parms
                    # push present time onto control queue to force re solve at new dispatch value
                    ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, self)
        self.lastPctDischargeRate = pctDischargeRate
        # remember this value

    def doTimeMode(self, opt):
        """In "time" mode we need to only turn the storage elements on. They will turn themselves
        off when they are either fully discharged, fully charged, or receive another command
        from the controller.
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        _0 = opt
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                if self.dischargeTriggerTime > 0.0:
                    # turn on if time within 1/2 time step
                    if (
                        self.Math.abs(self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - self.dischargeTriggerTime) < sol.getDynaVars().h / 7200.0
                    ):
                        if not (self.fleetState == Storage.DISCHARGING):
                            # Time is within 1 time step of the trigger time
                            if self.showEventLog:
                                Utilities.appendToEventLog('StorageController.' + self.getName(), 'Fleet set to discharging by time trigger')
                            self.setFleetToDisCharge()
                            self.setFleetkWRate(self.pctKWRate)
                            self.dischargeInhibited = False
                            if self.dischargeMode == StorageController.FOLLOW:
                                self.dischargeTriggeredByTime = True
                            else:
                                sol.setLoadsNeedUpdating(True)
                                # force recalc of power parms
                                # push present time onto control queue to force re solve at new dispatch value
                                ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, self)
                    else:
                        self.chargingAllowed = True
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                if self.chargeTriggerTime > 0.0:
                    if (
                        self.Math.abs(self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - self.chargeTriggerTime) < sol.getDynaVars().h / 7200.0
                    ):
                        if not (self.fleetState == Storage.CHARGING):
                            # Time is within 1 time step of the trigger time
                            if self.showEventLog:
                                Utilities.appendToEventLog('StorageController.' + self.getName(), 'Fleet set to charging by time trigger')
                            self.setFleetToCharge()
                            self.dischargeInhibited = True
                            self.outOfEnergy = False
                            sol.setLoadsNeedUpdating(True)
                            # force recalc of power parms
                            # push present time onto control queue to force re solve at new dispatch value
                            ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.CHARGING, 0, self)
                            ckt.getControlQueue().push(sol.getIntHour() + self.inhibitHrs, sol.getDynaVars().t, StorageController.RELEASE_INHIBIT, 0, self)
                break
            break

    def normalizeToTOD(self, h, sec):
        """Normalise time to a floating point number representing time of day if Hour > 24.
        time should be 0 to 23.999999.
        """
        if h > 23:
            hourOfDay = h - ((h / 24) * 24)
        else:
            hourOfDay = h
        result = hourOfDay + (sec / 3600.0)
        if result >= 24.0:
            result = result - 24.0
            # wrap around
        return result

    def doLoadFollowMode(self):
        # if list is not defined, go make one from all storage elements in circuit
        if len(self.fleetPointerList) == 0:
            self.makeFleetList()
        if self.fleetSize > 0:
            storeKWChanged = False
            storeKVArChanged = False
            skipKWDispatch = False
            # MonitoredElement.ActiveTerminalIdx = ElementTerminal;
            S = self.monitoredElement.getPower(self.elementTerminal)
            # power in active terminal
            _0 = self.dischargeMode
            _1 = False
            while True:
                if _0 == StorageController.FOLLOW:
                    _1 = True
                    if self.dischargeTriggeredByTime:
                        if self.showEventLog:
                            Utilities.appendToEventLog('StorageController.' + self.getName(), String.format.format('Fleet set to discharging by time trigger; Old kWTarget = %-.6g; New = 5-.6g', self.kWTarget, S.getReal() * 0.001))
                        self.kWTarget = self.Math.max(self.kWThreshold, S.getReal() * 0.001)
                        # capture present kW and reset target
                        self.dischargeTriggeredByTime = False
                        # so we don't come back in here right away
                        self.setFleetToIdle()
                    PDiff = (S.getReal() * 0.001) - self.kWTarget
                    # assume S.re is normally positive
                    PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - self.PFTarget
                    # for peak shaving
                    break
                    # supporting DG; try to keep load above kW target
                if (_1 is True) or (_0 == StorageController.SUPPORT):
                    _1 = True
                    PDiff = (S.getReal() * 0.001) + self.kWTarget
                    # assume S.re is normally negative
                    PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - self.PFTarget
                    # for generator
                    break
                if (_1 is True) or (_0 == StorageController.PEAKSHAVE):
                    _1 = True
                    PDiff = (S.getReal() * 0.001) - self.kWTarget
                    # assume S.re is normally positive
                    PFDiff = Utilities.convertPFToPFRange2(Utilities.powerFactor(S)) - self.PFTarget
                    # for peak shaving
                    break
                if True:
                    _1 = True
                    PDiff = 0.0
                    PFDiff = 0.0
                    break
                break
            # following load; try to keep load below kW Target
            self.kWNeeded = PDiff
            # kW dispatch
            if self.dischargeInhibited:
                skipKWDispatch = True
            else:
                if self.fleetState == Storage.CHARGING:
                    PDiff = PDiff + self.getFleetKW()
                    # ignore overload due to charging
                _2 = self.fleetState
                _3 = False
                while True:
                    if _2 == Storage.CHARGING:
                        _3 = True
                        if (PDiff < 0.0) or self.outOfEnergy:
                            # don't bother trying to dispatch
                            self.chargingAllowed = True
                            skipKWDispatch = True
                        break
                    if (_3 is True) or (_2 == Storage.IDLING):
                        _3 = True
                        if (PDiff < 0.0) or self.outOfEnergy:
                            # don't bother trying to dispatch
                            self.chargingAllowed = True
                            skipKWDispatch = True
                        break
                    if (_3 is True) or (_2 == Storage.DISCHARGING):
                        _3 = True
                        if (PDiff + self.getFleetKW() < 0.0) or self.outOfEnergy:
                            # desired decrease is greater then present output; just cancel
                            self.setFleetToIdle()
                            # also sets presentkW = 0
                            self.chargingAllowed = True
                            skipKWDispatch = True
                        break
                    break
            if not skipKWDispatch:
                remainingKWh = self.getFleetkWh()
                reserveKWh = self.getFleetReserveKWh()
                if remainingKWh > reserveKWh:
                    # don't dispatch kW  if not enough storage left or an endless control loop will occur
                    if self.Math.abs(PDiff) > self.halfKWBand:
                        # attempt to change storage dispatch
                        if not (self.fleetState == Storage.DISCHARGING):
                            self.setFleetToDisCharge()
                        if self.showEventLog:
                            Utilities.appendToEventLog('StorageController.' + self.getName(), String.format.format('Attempting to dispatch %-.6g kW with %-.6g kWh remaining and %-.6g reserve.', self.kWNeeded, remainingKWh, reserveKWh))
                        _4 = True
                        i = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < self.fleetSize):
                                break
                            pStorage = self.fleetPointerList[i]
                            # compute new dispatch value for this storage element ...
                            dispatchKW = self.Math.min(pStorage.getKWRating(), pStorage.getPresentKW() + (PDiff * (self.weights[i] / self.totalWeight)))
                            if dispatchKW != pStorage.getPresentKW():
                                # redispatch only if change requested
                                if pStorage.getKWhStored() > pStorage.getKWhReserve():
                                    # attempt to set discharge kW; storage element will revert to idling if out of capacity
                                    pStorage.setPresentKW(dispatchKW)
                                    storeKWChanged = True
                                    # this is what keeps the control iterations going
                else:
                    if not (self.getFleetState() == Storage.IDLING):
                        self.setFleetToIdle()
                    self.chargingAllowed = True
                    self.outOfEnergy = True
                    if self.showEventLog:
                        Utilities.appendToEventLog('StorageController.' + self.getName(), String.format.format('Ran out of energy: %-.6g kWh remaining and %-.6g reserve.', remainingKWh, reserveKWh))
            # kVAr dispatch Note: PFDiff computed from PF in range of 0..2
            # redispatch the vars only if the PF is outside the band
            if self.dispatchVars and self.Math.abs(PFDiff) > self.halfPFBand:
                if self.showEventLog:
                    Utilities.appendToEventLog('StorageController.' + self.getName(), String.format.format('Changed kvar dispatch. PF diff needed = %.6g', PFDiff))
                    # redispatch storage elements
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.fleetSize):
                        break
                    pStorage = self.fleetPointerList[i]
                    # compute new var dispatch value for this storage element ...
                    if self.PFTarget == 1.0:
                        dispatchKVAr = 0.0
                    else:
                        dispatchKVAr = S.getReal() * self.Math.sqrt((1.0 / self.Math.pow(Utilities.convertPFRange2ToPF(self.PFTarget), 2)) - 1.0) * (self.weights[i] / self.totalWeight)
                        if self.PFTarget > 1.0:
                            dispatchKVAr = -dispatchKVAr
                            # for watts and vars in opposite direction
                    if dispatchKVAr != pStorage.getPresentKVAr():
                        pStorage.setPresentKVAr(dispatchKVAr)
                        # ask for this much kvar but may be limited by element
                        storeKVArChanged = True
            if storeKWChanged or storeKVArChanged:
                # only push onto control queue if there has been a change
                ckt = DSSGlobals.activeCircuit
                sol = ckt.getSolution()
                sol.setLoadsNeedUpdating(True)
                # force recalc of power parms
                # push present time onto control queue to force re solve at new dispatch value
                ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, Storage.DISCHARGING, 0, self)

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        self.chargingAllowed = False
        # Check discharge mode first. Then if not discharging, we can check for charging.
        _0 = self.dischargeMode
        _1 = False
        while True:
            if _0 == StorageController.FOLLOW:
                _1 = True
                self.doTimeMode(1)
                self.doLoadFollowMode()
                break
            if (_1 is True) or (_0 == StorageController.LOADSHAPE):
                _1 = True
                self.doLoadShapeMode()
                break
            if (_1 is True) or (_0 == StorageController.SUPPORT):
                _1 = True
                self.doLoadFollowMode()
                break
            if (_1 is True) or (_0 == StorageController.TIME):
                _1 = True
                self.doTimeMode(1)
                break
            if (_1 is True) or (_0 == StorageController.PEAKSHAVE):
                _1 = True
                self.doLoadFollowMode()
                break
            if (_1 is True) or (_0 == StorageController.SCHEDULE):
                _1 = True
                self.doScheduleMode()
                break
            if True:
                _1 = True
                DSSGlobals.doSimpleMsg(String.format.format('Invalid discharging mode: %d', self.dischargeMode), 14408)
                break
            break
        if self.chargingAllowed:
            _2 = self.chargeMode
            _3 = False
            while True:
                if _2 == StorageController.LOADSHAPE:
                    _3 = True
                    break
                if (_3 is True) or (_2 == StorageController.TIME):
                    _3 = True
                    self.doTimeMode(2)
                    break
                if True:
                    _3 = True
                    DSSGlobals.doSimpleMsg(String.format.format('Invalid charging mode: %d', self.chargeMode), 14409)
                    break
                break

    def calcDailyMult(self, hr):
        if self.dailyShapeObj is not None:
            self.loadShapeMult = self.getDailyShapeObj().getMult(hr)
        else:
            self.loadShapeMult = self.CDOUBLEONE
            # default to no variation

    def calcDutyMult(self, hr):
        if self.dutyShapeObj is not None:
            self.loadShapeMult = self.dutyShapeObj.getMult(hr)
        else:
            self.calcDailyMult(hr)
            # default to daily mult if no duty curve specified

    def calcYearlyMult(self, hr):
        if self.yearlyShapeObj is not None:
            self.loadShapeMult = self.yearlyShapeObj.getMult(hr)
        else:
            self.calcDailyMult(hr)
            # defaults to daily curve

    def doLoadShapeMode(self):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        fleetStateSaved = self.fleetState
        rateChanged = False
        # get multiplier
        _0 = sol.getMode()
        _1 = False
        while True:
            if _0 == Dynamics.DAILYMODE:
                _1 = True
                self.calcDailyMult(sol.getDblHour())
                # daily dispatch curve
                break
            if (_1 is True) or (_0 == Dynamics.YEARLYMODE):
                _1 = True
                self.calcYearlyMult(sol.getDblHour())
                break
            if (_1 is True) or (_0 == Dynamics.LOADDURATION2):
                _1 = True
                self.calcDailyMult(sol.getDblHour())
                break
            if (_1 is True) or (_0 == Dynamics.PEAKDAY):
                _1 = True
                self.calcDailyMult(sol.getDblHour())
                break
            if (_1 is True) or (_0 == Dynamics.DUTYCYCLE):
                _1 = True
                self.calcDutyMult(sol.getDblHour())
                break
            break
        if self.loadShapeMult.getReal() < 0.0:
            self.chargingAllowed = True
            newChargeRate = self.Math.abs(self.loadShapeMult.getReal()) * 100.0
            if newChargeRate != self.pctChargeRate:
                rateChanged = True
            self.pctChargeRate = newChargeRate
            self.setFleetChargeRate()
            self.setFleetToCharge()
        elif self.loadShapeMult.getReal() == 0.0:
            self.setFleetToIdle()
        else:
            # set fleet to discharging at a rate
            newKWRate = self.loadShapeMult.getReal() * 100.0
            newKVArRate = self.loadShapeMult.getImaginary() * 100.0
            if (newKWRate != self.pctKWRate) or (newKVArRate != self.pctKVArRate):
                rateChanged = True
            self.pctKWRate = newKWRate
            self.pctKVArRate = newKVArRate
            self.setFleetkWRate(self.pctKWRate)
            self.setFleetkvarRate(self.pctKVArRate)
            self.setFleetToDisCharge()
            sol.setLoadsNeedUpdating(True)
            # force recalc of power parms
        if (self.fleetState != fleetStateSaved) or rateChanged:
            sol.setLoadsNeedUpdating(True)
            # force recalc of power parms
            # push present time onto control queue to force re solve at new dispatch value
            ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, self)

    def setAllFleetValues(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setPctKWin(self.pctChargeRate)
            pStorage.setPctKVArOut(self.pctKVArRate)
            pStorage.setPctKWOut(self.pctKWRate)
            pStorage.setPctReserve(self.pctFleetReserve)

    def setFleetChargeRate(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setPctKWin(self.pctChargeRate)

    def setFleetkvarRate(self, pctkvar):
        # For side effects see pctKVArOut property of storage element
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setPctKVArOut(self.pctKVArRate)

    def setFleetkWRate(self, pctkw):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setPctKWOut(pctkw)

    def setFleetToCharge(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setState(Storage.CHARGING)
        self.fleetState = Storage.CHARGING

    def setFleetToDisCharge(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setState(Storage.DISCHARGING)
        self.fleetState = Storage.DISCHARGING

    def setFleetToIdle(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setState(Storage.IDLING)
            pStorage.setPresentKW(0.0)
        self.fleetState = Storage.IDLING

    def setPFBand(self, value):
        self.PFBand = value
        self.halfPFBand = self.PFBand / 2.0

    def getPFBand(self):
        return self.PFBand

    def setFleetToExternal(self):
        # private void setPctReserve() {
        # StorageObj pStorage;
        # for (int i = 0; i < FleetPointerList.size(); i++) {
        # pStorage = (StorageObj) FleetPointerList.get(i);
        # pStorage.setPctReserve(pctFleetReserve);
        # }
        # }
        # FIXME Private method in OpenDSS
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.fleetPointerList)):
                break
            pStorage = self.fleetPointerList[i]
            pStorage.setState(Storage.EXTERNAL_MODE)

    def interpretMode(self, opt, s):
        _0 = opt
        _1 = False
        while True:
            if _0 == StorageController.MODE_DISCHARGE:
                _1 = True
                _2 = s.toLowerCase()[0]
                _3 = False
                while True:
                    if _2 == 'f':
                        _3 = True
                        return StorageController.FOLLOW
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        return StorageController.LOADSHAPE
                    if (_3 is True) or (_2 == 'p'):
                        _3 = True
                        return StorageController.PEAKSHAVE
                    if (_3 is True) or (_2 == 's'):
                        _3 = True
                        if s.toLowerCase()[1] == 'c':
                            return StorageController.SCHEDULE
                        else:
                            return StorageController.SUPPORT
                    if (_3 is True) or (_2 == 't'):
                        _3 = True
                        return StorageController.TIME
                    if True:
                        _3 = True
                        DSSGlobals.doSimpleMsg('Discharge Mode \"' + s + '\" not recognized.', 14402)
                    break
                break
            if (_1 is True) or (_0 == StorageController.MODE_CHARGE):
                _1 = True
                # case 'f':
                # 				return StorageController.MODEFOLLOW;

                _4 = s.toLowerCase()[0]
                _5 = False
                while True:
                    if _4 == 'l':
                        _5 = True
                        return StorageController.LOADSHAPE
                        # case 's':
                        # 				return StorageController.MODESUPPORT;

                    if (_5 is True) or (_4 == 't'):
                        _5 = True
                        return StorageController.TIME
                    if True:
                        _5 = True
                        DSSGlobals.doSimpleMsg('Charge Mode \"' + s + '\" not recognized.', 14402)
                    break
                break
            if True:
                _1 = True
                break
            break
        return 0

    def makeFleetList(self):
        result = False
        if self.elementListSpecified:
            # name list is defined - use it
            self.fleetPointerList.clear()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.fleetSize):
                    break
                pStorage = DSSGlobals.storageClass.find(self.storageNameList[i - 1])
                if pStorage is not None:
                    if pStorage.isEnabled():
                        self.fleetPointerList.add(pStorage)
                else:
                    DSSGlobals.doSimpleMsg('Error: Storage element \"' + self.storageNameList[i - 1] + '\" not found.', 14403)
                    return result
        else:
            # Search through the entire circuit for enabled storage elements and add them to the list
            self.storageNameList.clear()
            self.fleetPointerList.clear()
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < DSSGlobals.storageClass.getElementCount()):
                    break
                pStorage = DSSGlobals.storageClass.getElementList().get(i)
                # Look for a storage element not already assigned
                if (
                    pStorage.isEnabled() and pStorage.getDispatchMode() != Storage.EXTERNAL_MODE
                ):
                    self.storageNameList.add(pStorage.getName())
                    # add to list of names
                    self.fleetPointerList.add(pStorage)
            # Allocate uniform weights
            self.fleetSize = len(self.fleetPointerList)
            self.weights = Utilities.resizeArray(self.weights, self.fleetSize)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.fleetSize):
                    break
                self.weights[i] = 1.0
        # add up total weights
        self.totalWeight = 0.0
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < self.fleetSize):
                break
            self.totalWeight = self.totalWeight + self.weights[i]
        if len(self.fleetPointerList) > 0:
            result = True
        self.fleetListChanged = False
        return result

    def reset(self):
        """Reset to initial defined state."""
        # super.reset();
        self.setFleetToIdle()
        # do we want to set fleet to 100% charged storage?

    def returnElementsList(self):
        if self.fleetSize == 0:
            return ''
        result = '[' + self.storageNameList[0]
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.fleetSize - 1):
                break
            result = result + ', ' + self.storageNameList[i]
        result = result + ']'
        # terminate the array
        return result

    def returnWeightsList(self):
        # FIXME Private members in OpenDSS
        if self.fleetSize == 0:
            return ''
        result = '[' + String.format.format('%-.6g', self.weights[0])
        _0 = True
        i = 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.fleetSize):
                break
            result = result + String.format.format(', %-.6g', self.weights[i])
        result = result + ']'
        # terminate the array
        return result

    def getKWTarget(self):
        return self.kWTarget

    def setKWTarget(self, target):
        self.kWTarget = target

    def setKWThreshold(self, threshold):
        self.kWThreshold = threshold

    def getKWThreshold(self):
        return self.kWThreshold

    def getPctKWBand(self):
        return self.pctkWBand

    def setPctKWBand(self, band):
        self.pctkWBand = band

    def getHalfKWBand(self):
        return self.halfKWBand

    def setHalfKWBand(self, band):
        self.halfKWBand = band

    def getPFTarget(self):
        return self.PFTarget

    def setPFTarget(self, target):
        self.PFTarget = target

    def getTotalWeight(self):
        return self.totalWeight

    def setTotalWeight(self, weight):
        self.totalWeight = weight

    def getHalfPFBand(self):
        return self.halfPFBand

    def setHalfPFBand(self, band):
        self.halfPFBand = band

    def getKWNeeded(self):
        return self.kWNeeded

    def setKWNeeded(self, needed):
        self.kWNeeded = needed

    def getFleetSize(self):
        return self.fleetSize

    def setFleetSize(self, size):
        self.fleetSize = size

    def getFleetState(self):
        return self.fleetState

    def setFleetState(self, state):
        self.fleetState = state

    def getStorageNameList(self):
        return self.storageNameList

    def setStorageNameList(self, list):
        self.storageNameList = list

    def getFleetPointerList(self):
        return self.fleetPointerList

    def setFleetPointerList(self, list):
        self.fleetPointerList = list

    def getWeights(self):
        return self.weights

    def setWeights(self, values):
        self.weights = values

    def isElementListSpecified(self):
        return self.elementListSpecified

    def setElementListSpecified(self, value):
        self.elementListSpecified = value

    def getDischargeMode(self):
        return self.dischargeMode

    def setDischargeMode(self, mode):
        self.dischargeMode = mode

    def getChargeMode(self):
        return self.chargeMode

    def setChargeMode(self, mode):
        self.chargeMode = mode

    def getDischargeTriggerTime(self):
        return self.dischargeTriggerTime

    def setDischargeTriggerTime(self, time):
        self.dischargeTriggerTime = time

    def getChargeTriggerTime(self):
        return self.chargeTriggerTime

    def setChargeTriggerTime(self, time):
        self.chargeTriggerTime = time

    def getPctKWRate(self):
        return self.pctKWRate

    def setPctKWRate(self, pct):
        self.pctKWRate = pct

    def getPctKVArRate(self):
        return self.pctKVArRate

    def setPctKVArRate(self, pct):
        self.pctKVArRate = pct

    def getPctChargeRate(self):
        return self.pctChargeRate

    def setPctChargeRate(self, pct):
        self.pctChargeRate = pct

    def getPctFleetReserve(self):
        return self.pctFleetReserve

    def setPctFleetReserve(self, pct):
        self.pctFleetReserve = pct

    def isFleetListChanged(self):
        return self.fleetListChanged

    def setFleetListChanged(self, changed):
        self.fleetListChanged = changed

    def isChargingAllowed(self):
        return self.chargingAllowed

    def setChargingAllowed(self, allowed):
        self.chargingAllowed = allowed

    def isShowEventLog(self):
        return self.showEventLog

    def setShowEventLog(self, show):
        self.showEventLog = show

    def isDispatchVars(self):
        return self.dispatchVars

    def setDispatchVars(self, vars):
        self.dispatchVars = vars

    def isDischargeTriggeredByTime(self):
        return self.dischargeTriggeredByTime

    def setDischargeTriggeredByTime(self, value):
        self.dischargeTriggeredByTime = value

    def isDischargeInhibited(self):
        return self.dischargeInhibited

    def setDischargeInhibited(self, inhibited):
        self.dischargeInhibited = inhibited

    def isOutOfEnergy(self):
        return self.outOfEnergy

    def setOutOfEnergy(self, value):
        self.outOfEnergy = value

    def getInhibitHrs(self):
        return self.inhibitHrs

    def setInhibitHrs(self, hrs):
        self.inhibitHrs = hrs

    def getUpRampTime(self):
        return self.upRampTime

    def setUpRampTime(self, time):
        self.upRampTime = time

    def getFlatTime(self):
        return self.flatTime

    def setFlatTime(self, time):
        self.flatTime = time

    def getDnRampTime(self):
        return self.dnRampTime

    def setDnRampTime(self, time):
        self.dnRampTime = time

    def getUpPlusFlat(self):
        return self.upPlusFlat

    def setUpPlusFlat(self, value):
        self.upPlusFlat = value

    def getUpPlusFlatPlusDn(self):
        return self.upPlusFlatPlusDn

    def setUpPlusFlatPlusDn(self, value):
        self.upPlusFlatPlusDn = value

    def getLastpctDischargeRate(self):
        return self.lastPctDischargeRate

    def setLastpctDischargeRate(self, rate):
        self.lastPctDischargeRate = rate

    def getTotalKWCapacity(self):
        return self.totalKWCapacity.doubleValue()

    def setTotalKWCapacity(self, capacity):
        self.totalKWCapacity.setValue(capacity)

    def getTotalKWhCapacity(self):
        return self.totalKWhCapacity.doubleValue()

    def setTotalKWhCapacity(self, capacity):
        self.totalKWhCapacity.setValue(capacity)

    def getYearlyShape(self):
        return self.yearlyShape

    def setYearlyShape(self, shape):
        self.yearlyShape = shape

    def getYearlyShapeObj(self):
        return self.yearlyShapeObj

    def setYearlyShapeObj(self, yearlyShape):
        self.yearlyShapeObj = yearlyShape

    def getDailyShape(self):
        return self.dailyShape

    def setDailyShape(self, shape):
        self.dailyShape = shape

    def getDailyShapeObj(self):
        return self.dailyShapeObj

    def setDailyShapeObj(self, dailyShape):
        self.dailyShapeObj = dailyShape

    def getDutyShape(self):
        return self.dutyShape

    def setDutyShape(self, shape):
        self.dutyShape = shape

    def getDutyShapeObj(self):
        return self.dutyShapeObj

    def setDutyShapeObj(self, dutyShape):
        self.dutyShapeObj = dutyShape

    def getLoadShapeMult(self):
        return self.loadShapeMult

    def setLoadShapeMult(self, mult):
        self.loadShapeMult = mult

    def getMonitoredElement(self):
        return self.monitoredElement

    def setMonitoredElement(self, element):
        self.monitoredElement = element

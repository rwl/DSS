from dss.control.impl.StorageControllerObjImpl import (StorageControllerObjImpl,)
from dss.control.StorageController import (StorageController,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class StorageControllerImpl(ControlClassImpl, StorageController):
    activeStorageControllerObj = None

    def __init__(self):
        super(StorageControllerImpl, self)()
        self.className = 'StorageController'
        self.classType = self.classType + DSSClassDefs.STORAGE_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = StorageController.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[StorageController.ELEMENT] = 'Element'
        self.propertyName[StorageController.TERMINAL] = 'Terminal'
        self.propertyName[StorageController.KW_TARGET] = 'kWTarget'
        self.propertyName[StorageController.KW_BAND] = '%kWBand'
        self.propertyName[StorageController.PF_TARGET] = 'PFTarget'
        self.propertyName[StorageController.PF_BAND] = 'PFBand'
        self.propertyName[StorageController.ELEMENT_LIST] = 'ElementList'
        self.propertyName[StorageController.WEIGHTS] = 'Weights'
        self.propertyName[StorageController.MODE_DISCHARGE] = 'ModeDischarge'
        self.propertyName[StorageController.MODE_CHARGE] = 'ModeCharge'
        self.propertyName[StorageController.TIME_DISCHARGE_TRIGGER] = 'TimeDischargeTrigger'
        self.propertyName[StorageController.TIME_CHARGE_TRIGGER] = 'TimeChargeTrigger'
        self.propertyName[StorageController.RATE_KW] = '%RatekW'
        self.propertyName[StorageController.RATE_KVAR] = '%Ratekvar'
        self.propertyName[StorageController.RATE_CHARGE] = '%RateCharge'
        self.propertyName[StorageController.RESERVE] = '%Reserve'
        self.propertyName[StorageController.KWH_TOTAL] = 'kWhTotal'
        self.propertyName[StorageController.KW_TOTAL] = 'kWTotal'
        self.propertyName[StorageController.KWH_ACTUAL] = 'kWhActual'
        self.propertyName[StorageController.KW_ACTUAL] = 'kWActual'
        self.propertyName[StorageController.KW_NEED] = 'kWneed'
        self.propertyName[StorageController.PARTICIPATION] = '%Participation'
        self.propertyName[StorageController.YEARLY] = 'Yearly'
        self.propertyName[StorageController.DAILY] = 'Daily'
        self.propertyName[StorageController.DUTY] = 'Duty'
        self.propertyName[StorageController.EVENTLOG] = 'EventLog'
        self.propertyName[StorageController.VAR_DISPATCH] = 'VarDispatch'
        self.propertyName[StorageController.INHIBIT_TIME] = 'InhibitTime'
        self.propertyName[StorageController.T_UP_RAMP] = 'Tup'
        self.propertyName[StorageController.T_FLAT] = 'TFlat'
        self.propertyName[StorageController.T_DN_RAMP] = 'Tdn'
        self.propertyName[StorageController.KW_THRESHOLD] = 'kWThreshold'
        self.propertyHelp[StorageController.ELEMENT] = 'Full object name of the circuit element, typically a line or transformer, ' + 'which the control is monitoring. There is no default; must be specified.'
        self.propertyHelp[StorageController.TERMINAL] = 'Number of the terminal of the circuit element to which the StorageController control is connected. ' + '1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.'
        self.propertyHelp[StorageController.KW_TARGET] = 'kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band ' + 'at least until the storage is depleted.'
        self.propertyHelp[StorageController.KW_BAND] = 'Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%).' + 'No dispatch changes are attempted If the power in the monitored terminal stays within this band.'
        self.propertyHelp[StorageController.PF_TARGET] = 'Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. ' + 'It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.'
        self.propertyHelp[StorageController.PF_BAND] = 'Bandwidth of the Target power factor of the monitored element. of the dead band around the kvar target value. Default is 0.04 (+/- 0.02).' + 'No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.'
        self.propertyHelp[StorageController.ELEMENT_LIST] = 'Array list of Storage elements to be controlled.  If not specified, all storage elements in the circuit not presently dispatched by another controller ' + 'are assumed dispatched by this controller.'
        self.propertyHelp[StorageController.WEIGHTS] = 'Array of proportional weights corresponding to each storage element in the ElementList. ' + 'The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. ' + 'Default is to set all weights to 1.0.'
        self.propertyHelp[StorageController.MODE_DISCHARGE] = '{PeakShave* | Follow | Support | Loadshape | Time | Schedule} Mode of operation for the DISCHARGE FUNCTION of this controller. ' + 'In PeakShave mode (Default), the control attempts to discharge storage to keep power in the monitored element below the kWTarget. ' + 'In Follow mode, the control is triggered by time and resets the kWTarget value to the present monitored element power. ' + 'It then attempts to discharge storage to keep power in the monitored element below the new kWTarget. See TimeDischargeTrigger.' + 'In Support mode, the control operates oppositely of PeakShave mode: storage is discharged to keep kW power output up near the target. ' + 'In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. ' + 'Storage is discharged when the loadshape value is positive. ' + 'In Time mode, the storage discharge is turned on at the specified %RatekW and %Ratekvar at the specified discharge trigger time in fractional hours.' + 'In Schedule mode, the Tup, TFlat, and Tdn properties specify the up ramp duration, flat duration, and down ramp duration for the schedule. ' + 'The schedule start time is set by TimeDischargeTrigger and the rate of discharge for the flat part is determined by RatekW.'
        self.propertyHelp[StorageController.MODE_CHARGE] = '{Loadshape | Time*} Mode of operation for the CHARGE FUNCTION of this controller. ' + 'In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. ' + 'Storage is charged when the loadshape value is negative. ' + 'In Time mode, the storage charging FUNCTION is triggered at the specified %RateCharge at the specified sharge trigger time in fractional hours.'
        self.propertyHelp[StorageController.TIME_DISCHARGE_TRIGGER] = 'Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time ' + 'each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. ' + 'In Time mode, the discharge is based on the %RatekW property value. ' + 'Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored). '
        self.propertyHelp[StorageController.TIME_CHARGE_TRIGGER] = 'Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200).' + 'When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is ' + 'topped off for the next discharge cycle.'
        self.propertyHelp[StorageController.RATE_KW] = 'Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered ' + 'by time.'
        self.propertyHelp[StorageController.RATE_KVAR] = 'Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered ' + 'by time.'
        self.propertyHelp[StorageController.RATE_CHARGE] = 'Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is ' + 'entered due to a time trigger.'
        self.propertyHelp[StorageController.RESERVE] = 'Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to ' + 'allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.'
        self.propertyHelp[StorageController.KWH_TOTAL] = '(Read only). Total rated kWh energy storage capacity of storage elements controlled by this controller.'
        self.propertyHelp[StorageController.KW_TOTAL] = '(Read only). Total rated kW power capacity of storage elements controlled by this controller.'
        self.propertyHelp[StorageController.KWH_ACTUAL] = '(Read only). Actual kWh output of all controlled storage elements. '
        self.propertyHelp[StorageController.KW_ACTUAL] = '(Read only). Actual kW output of all controlled storage elements. '
        self.propertyHelp[StorageController.KW_NEED] = '(Read only). KW needed to meet target.'
        self.propertyHelp[StorageController.PARTICIPATION] = 'Participation factor, %. Default = 100.'
        self.propertyHelp[StorageController.YEARLY] = 'Dispatch loadshape object, If any, for Yearly solution Mode.'
        self.propertyHelp[StorageController.DAILY] = 'Dispatch loadshape object, If any, for Daily solution mode.'
        self.propertyHelp[StorageController.DUTY] = 'Dispatch loadshape object, If any, for Dutycycle solution mode.'
        self.propertyHelp[StorageController.EVENTLOG] = '{Yes/True | No/False} Default is No. Log control actions to Eventlog.'
        self.propertyHelp[StorageController.VAR_DISPATCH] = '{Yes/True | No/False} Default is No. Flag to indicate whether or not to disatch vars as well as watts.'
        self.propertyHelp[StorageController.INHIBIT_TIME] = 'Hours (integer) to inhibit Discharging after going into Charge mode. Default is 5'
        self.propertyHelp[StorageController.T_UP_RAMP] = 'Duration, hrs, of upramp part for SCHEDULE mode. Default is 0.25.'
        self.propertyHelp[StorageController.T_FLAT] = 'Duration, hrs, of flat part for SCHEDULE mode. Default is 2.0.'
        self.propertyHelp[StorageController.T_DN_RAMP] = 'Duration, hrs, of downramp part for SCHEDULE mode. Default is 0.25.'
        self.propertyHelp[StorageController.KW_THRESHOLD] = 'Threshold, kW, for Follow mode. kW has to be above this value for the Storage element ' + 'to be dispatched on. Defaults to 75% of the kWTarget value. Must reset this property after ' + 'setting kWTarget if you want a different value.'
        self.activeProperty = StorageController.NumPropsThisClass - 1
        super(StorageControllerImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(StorageControllerObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeStorageControllerObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeStorageControllerObj)
        result = 0
        asc = self.activeStorageControllerObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                asc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + asc.getName() + '\"', 14407)
                    break
                if (_1 is True) or (_0 == StorageController.ELEMENT):
                    _1 = True
                    asc.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == StorageController.TERMINAL):
                    _1 = True
                    asc.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == StorageController.KW_TARGET):
                    _1 = True
                    asc.setKWTarget(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.KW_BAND):
                    _1 = True
                    asc.setPctKWBand(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.PF_TARGET):
                    _1 = True
                    asc.setPFTarget(Utilities.convertPFToPFRange2(parser.makeDouble()))
                    break
                if (_1 is True) or (_0 == StorageController.PF_BAND):
                    _1 = True
                    asc.setPFBand(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.ELEMENT_LIST):
                    _1 = True
                    Utilities.interpretStringListArray(param, asc.getStorageNameList())
                    break
                if (_1 is True) or (_0 == StorageController.WEIGHTS):
                    _1 = True
                    asc.setFleetSize(len(asc.getStorageNameList()))
                    if asc.getFleetSize() > 0:
                        asc.setWeights(Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()))
                        Utilities.interpretDblArray(param, asc.getFleetSize(), asc.getWeights())
                    break
                if (_1 is True) or (_0 == StorageController.MODE_DISCHARGE):
                    _1 = True
                    asc.setDischargeMode(asc.interpretMode(self.MODE_DISCHARGE, param))
                    break
                if (_1 is True) or (_0 == StorageController.MODE_CHARGE):
                    _1 = True
                    asc.setChargeMode(asc.interpretMode(self.MODE_CHARGE, param))
                    break
                if (_1 is True) or (_0 == StorageController.TIME_DISCHARGE_TRIGGER):
                    _1 = True
                    asc.setDischargeTriggerTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.TIME_CHARGE_TRIGGER):
                    _1 = True
                    asc.setChargeTriggerTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.RATE_KW):
                    _1 = True
                    asc.setPctKWRate(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.RATE_KVAR):
                    _1 = True
                    asc.setPctKVArRate(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.RATE_CHARGE):
                    _1 = True
                    asc.setPctChargeRate(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.RESERVE):
                    _1 = True
                    asc.setPctFleetReserve(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.KWH_TOTAL):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.KW_TOTAL):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.KWH_ACTUAL):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.KW_ACTUAL):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.KW_NEED):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.PARTICIPATION):
                    _1 = True
                    break
                if (_1 is True) or (_0 == StorageController.YEARLY):
                    _1 = True
                    asc.setYearlyShape(param)
                    break
                if (_1 is True) or (_0 == StorageController.DAILY):
                    _1 = True
                    asc.setDailyShape(param)
                    break
                if (_1 is True) or (_0 == StorageController.DUTY):
                    _1 = True
                    asc.setDutyShape(param)
                    break
                if (_1 is True) or (_0 == StorageController.EVENTLOG):
                    _1 = True
                    asc.setShowEventLog(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == StorageController.VAR_DISPATCH):
                    _1 = True
                    asc.setDispatchVars(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == StorageController.INHIBIT_TIME):
                    _1 = True
                    asc.setInhibitHrs(self.Math.max(1, parser.makeInteger()))
                    # >= 1
                    break
                if (_1 is True) or (_0 == StorageController.T_UP_RAMP):
                    _1 = True
                    asc.setUpRampTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.T_FLAT):
                    _1 = True
                    asc.setFlatTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.T_DN_RAMP):
                    _1 = True
                    asc.setDnRampTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == StorageController.KW_THRESHOLD):
                    _1 = True
                    asc.setKWThreshold(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeStorageControllerObj, paramPointer - StorageController.NumPropsThisClass)
                    break
                break
            # side effects of setting properties above
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == self.KW_TARGET:
                    _3 = True
                    asc.setHalfKWBand((asc.getPctKWBand() / 200.0) * asc.getKWTarget())
                    break
                if (_3 is True) or (_2 == self.KW_BAND):
                    _3 = True
                    asc.setHalfKWBand((asc.getPctKWBand() / 200.0) * asc.getKWTarget())
                    asc.setKWThreshold(asc.getKWTarget() * 0.75)
                    break
                if (_3 is True) or (_2 == self.PF_BAND):
                    _3 = True
                    asc.setHalfPFBand(asc.getPFBand() / 2.0)
                    break
                if (_3 is True) or (_2 == self.MODE_DISCHARGE):
                    _3 = True
                    if asc.getDischargeMode() == StorageController.FOLLOW:
                        asc.setDischargeTriggerTime(12.0)
                        # noon
                    break
                if (_3 is True) or (_2 == self.ELEMENT_LIST):
                    _3 = True
                    asc.getFleetPointerList().clear()
                    # clear this for resetting on first sample
                    asc.setFleetListChanged(True)
                    asc.setElementListSpecified(True)
                    asc.setFleetSize(len(asc.getStorageNameList()))
                    # realloc weights to be same size as possible number of storage elements
                    asc.setWeights(Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()))
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < asc.getFleetSize()):
                            break
                        asc.getWeights()[i] = 1.0
                    break
                if (_3 is True) or (_2 == self.YEARLY):
                    _3 = True
                    asc.setYearlyShapeObj(DSSGlobals.loadShapeClass.find(asc.getYearlyShape()))
                    if asc.getYearlyShapeObj() is None:
                        DSSGlobals.doSimpleMsg('Yearly loadshape \"' + asc.getYearlyShape() + '\" not found.', 14404)
                    break
                if (_3 is True) or (_2 == self.DAILY):
                    _3 = True
                    asc.setDailyShapeObj(DSSGlobals.loadShapeClass.find(asc.getDailyShape()))
                    if asc.getDailyShapeObj() is None:
                        DSSGlobals.doSimpleMsg('Daily loadshape \"' + asc.getDailyShape() + '\" not found.', 14405)
                    break
                if (_3 is True) or (_2 == self.DUTY):
                    _3 = True
                    asc.setDutyShapeObj(DSSGlobals.loadShapeClass.find(asc.getDutyShape()))
                    if asc.getDutyShapeObj() is None:
                        DSSGlobals.doSimpleMsg('Dutycycle loadshape \"' + asc.getDutyShape() + '\" not found.', 14406)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        asc.recalcElementData()
        return result

    def makeLike(self, storageControllerName):
        result = 0
        # See if we can find this StorageController name in the present collection
        otherStorageController = self.find(storageControllerName)
        if otherStorageController is not None:
            asc = self.activeStorageControllerObj
            asc.setNPhases(otherStorageController.getNPhases())
            asc.setNConds(otherStorageController.getNConds())
            # force reallocation of terminal stuff
            asc.setElementName(otherStorageController.getElementName())
            asc.setControlledElement(otherStorageController.getControlledElement())
            # pointer to target circuit element
            asc.setMonitoredElement(otherStorageController.getMonitoredElement())
            # pointer to target circuit element
            asc.setElementTerminal(otherStorageController.getElementTerminal())
            asc.setKWTarget(otherStorageController.getKWTarget())
            asc.setKWThreshold(otherStorageController.getKWThreshold())
            asc.setPctKWBand(otherStorageController.getPctKWBand())
            asc.setPFTarget(otherStorageController.getPFTarget())
            asc.setPFBand(otherStorageController.getPFBand())
            asc.setHalfPFBand(otherStorageController.getHalfPFBand())
            asc.getStorageNameList().clear()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(otherStorageController.getStorageNameList())):
                    break
                asc.getStorageNameList().add(otherStorageController.getStorageNameList().get(i - 1))
            asc.setFleetSize(len(asc.getStorageNameList()))
            if asc.getFleetSize() > 0:
                asc.setWeights(Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()))
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < asc.getFleetSize()):
                        break
                    asc.getWeights()[i] = otherStorageController.getWeights()[i]
            asc.setDischargeMode(otherStorageController.getDischargeMode())
            asc.setChargeMode(otherStorageController.getChargeMode())
            asc.setDischargeTriggerTime(otherStorageController.getDischargeTriggerTime())
            asc.setChargeTriggerTime(otherStorageController.getChargeTriggerTime())
            asc.setPctKWRate(otherStorageController.getPctKWRate())
            asc.setPctKVArRate(otherStorageController.getPctKVArRate())
            asc.setPctChargeRate(otherStorageController.getPctChargeRate())
            asc.setPctFleetReserve(otherStorageController.getPctFleetReserve())
            asc.setYearlyShape(otherStorageController.getYearlyShape())
            asc.setDailyShape(otherStorageController.getDailyShape())
            asc.setDutyShape(otherStorageController.getDutyShape())
            asc.setDispatchVars(otherStorageController.isDispatchVars())
            asc.setShowEventLog(otherStorageController.isShowEventLog())
            asc.setInhibitHrs(otherStorageController.getInhibitHrs())
            asc.setUpRampTime(otherStorageController.getUpRampTime())
            asc.setFlatTime(otherStorageController.getFlatTime())
            asc.setDnRampTime(otherStorageController.getDnRampTime())
            # fill in private properties
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < asc.getParentClass().getNumProperties()):
                    break
                asc.setPropertyValue(i, otherStorageController.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in StorageController makeLike: \"' + storageControllerName + '\" not found.', 370)
        return result

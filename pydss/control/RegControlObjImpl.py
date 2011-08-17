from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.RegControl import (RegControl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.RegControlObj import (RegControlObj,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
# from java.io.BufferedWriter import (BufferedWriter,)
# from java.io.File import (File,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.PrintStream import (PrintStream,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class RegControlObjImpl(ControlElemImpl, RegControlObj):
    lastChange = None
    Vreg = None
    bandwidth = None
    PTRatio = None
    CTRating = None
    R = None
    X = None
    # Reverse power variables
    revVreg = None
    revBandwidth = None
    revPowerThreshold = None
    kWRevPowerThreshold = None
    revDelay = None
    revR = None
    revX = None
    # W
    isReversible = None
    inReverseMode = None
    reversePending = None
    reverseNeutral = None
    LDCActive = None
    usingRegulatedBus = None
    regulatedBus = None
    pendingTapChange = None
    # amount of tap change pending
    tapDelay = None
    # delay between taps
    debugTrace = None
    armed = None
    traceFile = None
    tapLimitPerChange = None
    tapWinding = None
    inverseTime = None
    VLimit = None
    VLimitActive = None
    PTPhase = None
    controlledPhase = None
    VBuffer = None
    CBuffer = None

    def __init__(self, parClass, regControlName):
        super(RegControlObjImpl, self)(parClass)
        self.setName(regControlName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.Vreg = 120.0
        self.bandwidth = 3.0
        self.PTRatio = 60.0
        self.CTRating = 300.0
        self.R = 0.0
        self.X = 0.0
        self.timeDelay = 15.0
        self.PTPhase = 1
        self.LDCActive = False
        self.tapDelay = 2.0
        self.tapLimitPerChange = 16
        self.debugTrace = False
        self.armed = False
        # Reverse mode variables
        self.revVreg = 120.0
        self.revBandwidth = 3.0
        self.revR = 0.0
        self.revX = 0.0
        self.revDelay = 60.0
        # power must be reversed this long before it will reverse
        self.revPowerThreshold = 100000.0
        # 100 kW
        self.kWRevPowerThreshold = 100.0
        self.isReversible = False
        self.reversePending = False
        self.inReverseMode = False
        self.reverseNeutral = False
        self.elementName = ''
        self.setControlledElement(None)
        self.elementTerminal = 1
        self.tapWinding = self.elementTerminal
        self.VBuffer = None
        self.CBuffer = None
        self.objType = parClass.getDSSClassType()
        # REG_CONTROL;
        self.initPropertyValues(0)
        self.inverseTime = False
        self.regulatedBus = ''
        self.VLimit = 0.0
        # recalcElementData();

    def recalcElementData(self):
        if (self.R != 0.0) or (self.X != 0.0):
            self.LDCActive = True
        else:
            self.LDCActive = False
        if len(self.regulatedBus) == 0:
            self.usingRegulatedBus = False
        else:
            self.usingRegulatedBus = True
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # TODO Check zero based indexing
            # RegControled element must already exist
            self.setControlledElement(DSSGlobals.activeCircuit.getCktElements().get(devIndex))
            if self.usingRegulatedBus:
                self.setNPhases(1)
                # only need one phase
                self.setNConds(2)
            else:
                self.setNPhases(self.getControlledElement().getNPhases())
                self.setNConds(self.nPhases)
                if self.PTPhase > self.nPhases:
                    self.PTPhase = 1
                    self.setPropertyValue(21, '1')
            if (
                self.getControlledElement().getDSSClassName().equalsIgnoreCase('transformer')
            ):
                if self.elementTerminal > self.getControlledElement().getNTerms():
                    DSSGlobals.doErrorMsg('RegControl: \"' + self.getName() + '\"', 'Winding no. \"' + '\" does not exist.', 'Respecify monitored winding no.', 122)
                else:
                    # sets name of i-th terminal's connected bus in RegControl's bus list
                    # this value will be used to set the nodeRef array (see sample function)
                    if self.usingRegulatedBus:
                        self.setBus(1, self.regulatedBus)
                        # hopefully this will actually exist
                    else:
                        self.setBus(1, self.getControlledElement().getBus(self.elementTerminal))
                    # buffer to hold regulator voltages
                    self.VBuffer = Utilities.resizeArray(self.VBuffer, self.getControlledElement().getNPhases())
                    self.CBuffer = Utilities.resizeArray(self.CBuffer, self.getControlledElement().getYorder())
            else:
                self.setControlledElement(None)
                # we get here if element not found
                DSSGlobals.doErrorMsg('RegControl: \"' + self.getName() + '\"', 'Controlled regulator element \"' + self.elementName + '\" is not a transformer.', ' Element must be defined previously.', 123)
        else:
            self.setControlledElement(None)
            # element not found
            DSSGlobals.doErrorMsg('RegControl: \"' + self.getName() + '\"', 'Transformer element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 124)

    def calcYPrim(self):
        # leave YPrim as null and it will be ignored ... zero current source
        pass

    def getControlVoltage(self, VBuffer, nphs, PTRatio):
        _0 = self.PTPhase
        _1 = False
        while True:
            if _0 == RegControl.MAXPHASE:
                _1 = True
                self.controlledPhase = 1
                V = VBuffer[self.controlledPhase].abs()
                _2 = True
                i = 1
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < nphs):
                        break
                    if VBuffer[i].abs() > V:
                        V = VBuffer[i].abs()
                        self.controlledPhase = i
                result = ComplexUtil.divide(VBuffer[self.controlledPhase], PTRatio)
                break
            if (_1 is True) or (_0 == RegControl.MINPHASE):
                _1 = True
                self.controlledPhase = 1
                V = VBuffer[self.controlledPhase].abs()
                _3 = True
                i = 1
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < nphs):
                        break
                    if VBuffer[i].abs() < V:
                        V = VBuffer[i].abs()
                        self.controlledPhase = i
                result = ComplexUtil.divide(VBuffer[self.controlledPhase], PTRatio)
                break
            if True:
                _1 = True
                result = ComplexUtil.divide(VBuffer[self.PTPhase], PTRatio)
                self.controlledPhase = self.PTPhase
                break
            break
        # case AVGPHASES:
        # Result = Complex.ZERO;
        # for (i = 0; i < Nphs; i++)
        # Result = Result + VBuffer[i].abs();
        # Result = Result.divide(Nphs * PTRatio);
        # break;
        return result

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

    def dumpProperties(self, f, complete):
        super(RegControlObjImpl, self).dumpProperties(f, complete)
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
            print '! Bus =' + self.getBus(0)
            print 

    def atLeastOneTap(self, proposedChange, increment):
        """Called in static mode.
        Changes 70% of the way but at least one tap, subject to maximum allowable tap change.
        """
        numTaps = (0.7 * self.Math.abs(proposedChange)) / increment
        if numTaps == 0:
            numTaps = 1
        if numTaps > self.tapLimitPerChange:
            numTaps = self.tapLimitPerChange
        self.lastChange = numTaps
        if proposedChange > 0.0:
            # check sign on change
            result = numTaps * increment
        else:
            result = -numTaps * increment
            self.lastChange = -numTaps
        return result

    def oneInDirectionOf(self, proposedChange, increment):
        """Computes the amount of one tap change in the direction of the pending tapchange.
        Automatically decrements the proposed change by that amount.
        """
        self.lastChange = 0
        if proposedChange > 0.0:
            result = increment
            self.lastChange = 1
            proposedChange = proposedChange - increment
        else:
            result = -increment
            self.lastChange = -1
            proposedChange = proposedChange + increment
        if self.Math.abs(proposedChange) < 0.9 * increment:
            proposedChange = 0.0
        return result

    def doPendingAction(self, code, proxyHdl):
        """Do the action that is pending from last sample."""
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        _0 = code
        _1 = False
        while True:
            if _0 == RegControl.ACTION_TAPCHANGE:
                _1 = True
                if self.debugTrace:
                    self.regWriteDebugRecord(String.format.format('+++ %.6g s: Handling TapChange = %.8g', ckt.getSolution().getDynaVars().t, self.pendingTapChange))
                if self.pendingTapChange == 0.0:
                    # Check to make sure control has not reset
                    self.armed = False
                else:
                    pElem = self.getControlledElement()
                    _2 = sol.getControlMode()
                    _3 = False
                    while True:
                        if _2 == DSSGlobals.CTRLSTATIC:
                            _3 = True
                            tapChangeToMake = self.atLeastOneTap(self.pendingTapChange, pElem.getTapIncrement(self.tapWinding))
                            if self.debugTrace:
                                self.regWriteTraceRecord(tapChangeToMake)
                            pElem.setPresentTap(self.tapWinding, pElem.getPresentTap(self.tapWinding) + tapChangeToMake)
                            Utilities.appendToEventLog('Regulator.' + self.getControlledElement().getName(), String.format.format(' Changed %d taps to %-.6g.', self.lastChange, pElem.getPresentTap(self.tapWinding)))
                            self.setPendingTapChange(0.0)
                            # reset to no change; program will determine if another needed
                            self.armed = False
                        if (_3 is True) or (_2 == DSSGlobals.EVENTDRIVEN):
                            _3 = True
                            tapChangeToMake = self.oneInDirectionOf(self.pendingTapChange, pElem.getTapIncrement(self.tapWinding))
                            if self.debugTrace:
                                self.regWriteTraceRecord(tapChangeToMake)
                            pElem.setPresentTap(self.tapWinding, pElem.getPresentTap(self.tapWinding) + tapChangeToMake)
                            if self.pendingTapChange != 0.0:
                                ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + self.tapDelay, 0, 0, self)
                            else:
                                self.armed = False
                        if (_3 is True) or (_2 == DSSGlobals.TIMEDRIVEN):
                            _3 = True
                            tapChangeToMake = self.oneInDirectionOf(self.pendingTapChange, pElem.getTapIncrement(self.tapWinding))
                            if self.debugTrace:
                                self.regWriteTraceRecord(tapChangeToMake)
                            pElem.setPresentTap(self.tapWinding, pElem.getPresentTap(self.tapWinding) + tapChangeToMake)
                            Utilities.appendToEventLog('Regulator.' + self.getControlledElement().getName(), String.format.format(' Changed %d tap to %-.6g.', self.lastChange, pElem.getPresentTap(self.tapWinding)))
                            if self.debugTrace:
                                self.regWriteDebugRecord(String.format.format('--- Regulator.%s Changed %d tap to %-.6g.', pElem.getControlElement().getName(), self.lastChange, pElem.getPresentTap(self.tapWinding)))
                            if self.pendingTapChange != 0.0:
                                ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + self.tapDelay, 0, 0, self)
                            else:
                                self.armed = False
                        break
                break
            if (_1 is True) or (_0 == RegControl.ACTION_REVERSE):
                _1 = True
                if self.debugTrace:
                    self.regWriteDebugRecord(String.format.format('Handling Reverse Action, ReversePending=%s, InReverseMode=%s', String.valueOf.valueOf(self.reversePending), String.valueOf.valueOf(self.inReverseMode)))
                if self.reversePending:
                    # check to see if action has reset
                    if self.inReverseMode:
                        self.inReverseMode = False
                    else:
                        self.inReverseMode = True
                    self.reversePending = False
                break
            break

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        ckt = DSSGlobals.activeCircuit
        controlledTransformer = self.getControlledElement()
        # First, check the direction of power flow to see if we need to
        # 		 * reverse direction

        # Don't do this if using regulated bus logic
        if not self.usingRegulatedBus:
            if self.isReversible:
                if not self.inReverseMode:
                    # if looking forward, check to see if we should reverse
                    if not self.reversePending:
                        # if reverse is already pending, don't send any more messages
                        fwdPower = -controlledTransformer.getPower(self.elementTerminal).getReal()
                        # Watts
                        if fwdPower < -self.revPowerThreshold:
                            if self.debugTrace:
                                self.regWriteDebugRecord(String.format.format('Pushing Reverse Action, FwdPower=%.8g', fwdPower))
                            self.reversePending = True
                            ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.revDelay, RegControl.ACTION_REVERSE, 0, self)
                        else:
                            self.reversePending = False
                            # reset it if power goes back
                else:
                    # if reversed look to see if power is back in forward direction
                    if not self.reversePending:
                        fwdPower = -controlledTransformer.getPower(self.elementTerminal).getReal()
                        # Watts
                        if fwdPower > self.revPowerThreshold:
                            if self.debugTrace:
                                self.regWriteDebugRecord(String.format.format('Pushing Reverse Action to switch back, FwdPower=%.8g', fwdPower))
                            self.reversePending = True
                            ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.revDelay, RegControl.ACTION_REVERSE, 0, self)
                        else:
                            self.reversePending = False
                            # reset it if power went back to reverse
                    # Check for special case of reverse neutral where
                    # 					 * regulator is to move to neutral position

                    if self.reverseNeutral:
                        if not self.armed:
                            self.setPendingTapChange(0.0)
                            if (
                                self.Math.abs(controlledTransformer.getPresentTap(self.tapWinding) - 1.0) > DSSGlobals.EPSILON
                            ):
                                increment = controlledTransformer.getTapIncrement(self.tapWinding)
                                self.setPendingTapChange(self.Math.round((1.0 - controlledTransformer.getPresentTap(self.tapWinding)) / increment) * increment)
                                if self.pendingTapChange != 0.0 and not self.armed:
                                    if self.debugTrace:
                                        self.regWriteDebugRecord(String.format.format('*** %.6g s: Pushing TapChange = %.8g, delay= %.8g', ckt.getSolution().getDynaVars().t, self.pendingTapChange, self.tapDelay))
                                    ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.tapDelay, RegControl.ACTION_TAPCHANGE, 0, self)
                                    self.armed = True
                        return
                        # we're done here in any case if reverse neutral specified
                # else
        if self.usingRegulatedBus:
            transformerConnection = controlledTransformer.getWinding()[self.elementTerminal].getConnection()
            self.computeVTerminal()
            # computes the voltage at the bus being regulated
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.getNPhases()):
                    break
                _1 = transformerConnection
                _2 = False
                while True:
                    if _1 == 0:
                        _2 = True
                        self.VBuffer[i] = self.VTerminal[i]
                        break
                    if (_2 is True) or (_1 == 1):
                        _2 = True
                        ii = controlledTransformer.rotatePhases(i)
                        self.VBuffer[i] = self.VTerminal[i].subtract(self.VTerminal[ii])
                        break
                    break
        else:
            controlledTransformer.getWindingVoltages(self.elementTerminal, self.VBuffer)
        Vcontrol = self.getControlVoltage(self.VBuffer, self.getNPhases(), self.PTRatio)
        # check VLimit
        if self.VLimitActive:
            if self.usingRegulatedBus:
                controlledTransformer.getWindingVoltages(self.elementTerminal, self.VBuffer)
                VLocalBus = ComplexUtil.divide(self.VBuffer[1], self.PTRatio).abs()
            else:
                VLocalBus = Vcontrol.abs()
        else:
            VLocalBus = 0.0
            # to get rid of warning message
        # check for LDC
        if not self.usingRegulatedBus and self.LDCActive:
            self.getControlledElement().getCurrents(self.CBuffer)
            ILDC = ComplexUtil.divide(self.CBuffer[(self.getControlledElement().getNConds() * (self.elementTerminal - 1)) + self.controlledPhase], self.CTRating)
            if self.inReverseMode:
                VLDC = Complex(self.revR, self.revX).multiply(ILDC)
            else:
                VLDC = Complex(self.R, self.X).multiply(ILDC)
            Vcontrol = Vcontrol.add(VLDC)
            # direction on ILDC is into terminal, so this is equivalent to Vterm - (R+jX)*ILDC
        Vactual = Vcontrol.abs()
        # check for out of band voltage
        if self.Math.abs(self.Vreg - Vactual) > self.bandwidth / 2.0:
            tapChangeIsNeeded = True
        else:
            tapChangeIsNeeded = False
        if self.VLimitActive:
            if VLocalBus > self.VLimit:
                tapChangeIsNeeded = True
        if tapChangeIsNeeded:
            # compute tapchange
            # if tapChangeIsNeeded
            Vboost = self.Vreg - Vactual
            if self.VLimitActive:
                if VLocalBus > self.VLimit:
                    Vboost = self.VLimit - VLocalBus
            boostNeeded = (Vboost * self.PTRatio) / controlledTransformer.getBaseVoltage(self.elementTerminal)
            # per unit winding boost needed
            increment = controlledTransformer.getTapIncrement(self.tapWinding)
            self.setPendingTapChange(self.Math.round(boostNeeded / increment) * increment)
            # make sure it is an even increment
            # If tap is another winding or in reverse mode, it has to move
            # 			 * the other way to accomplish the change

            if (self.tapWinding != self.elementTerminal) or self.inReverseMode:
                self.setPendingTapChange(-self.pendingTapChange)
                # send initial tap change message to control queue
                # add delay time to solution control queue
            if self.pendingTapChange != 0.0 and not self.armed:
                # now see if any tap change is possible in desired direction, else ignore
                if self.pendingTapChange > 0.0:
                    if (
                        controlledTransformer.getPresentTap(self.tapWinding) < controlledTransformer.getMaxTap(self.tapWinding)
                    ):
                        ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.computeTimeDelay(Vactual), RegControl.ACTION_TAPCHANGE, 0, self)
                        self.armed = True
                        # armed to change taps
                elif (
                    controlledTransformer.getPresentTap(self.tapWinding) > controlledTransformer.getMinTap(self.tapWinding)
                ):
                    ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.computeTimeDelay(Vactual), RegControl.ACTION_TAPCHANGE, 0, self)
                    self.armed = True
                    # armed to change taps
        else:
            self.setPendingTapChange(0.0)

    def getTransformer(self):
        """Controlled transformer."""
        return self.getControlledElement()

    def getWinding(self):
        """Report tapped winding."""
        return self.tapWinding

    def getMinTap(self):
        return self.getTransformer().getMinTap(self.tapWinding)

    def getMaxTap(self):
        return self.getTransformer().getMaxTap(self.tapWinding)

    def getTapIncrement(self):
        return self.getTransformer().getTapIncrement(self.tapWinding)

    def getNumTaps(self):
        return self.getTransformer().getNumTaps(self.tapWinding)

    def regWriteDebugRecord(self, s):
        # write a general debug string
        try:
            if not DSSGlobals.inShowResults:
                fw = FileWriter(self.traceFile, True)
                bw = BufferedWriter(fw)
                bw.write(s)
                bw.newLine()
                bw.close()
                fw.close()
        except Exception, e:
            pass # astStmt: [Stmt([]), None]

    def regWriteTraceRecord(self, tapChangeMade):
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        try:
            if not DSSGlobals.inShowResults:
                fw = FileWriter(self.traceFile, True)
                bw = BufferedWriter(fw)
                pElem = self.getControlledElement()
                bw.write(ckt.getSolution().getIntHour() + sep + ckt.getSolution().getDynaVars().t + sep + ckt.getSolution().getControlIteration() + sep + ckt.getSolution().getIteration() + sep + ckt.getLoadMultiplier() + sep + pElem.getPresentTap(self.elementTerminal) + sep + self.pendingTapChange + sep + tapChangeMade + sep + pElem.getTapIncrement(self.elementTerminal) + sep + pElem.getMinTap(self.elementTerminal) + sep + pElem.getMaxTap(self.elementTerminal))
                bw.newLine()
                bw.close()
                fw.close()
        except Exception, e:
            pass # astStmt: [Stmt([]), None]

    def reset(self):
        """Reset to initial defined state."""
        self.setPendingTapChange(0.0)

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = ''
        # "element";
        self.propertyValue[1] = '1'
        # "terminal";
        self.propertyValue[2] = '120'
        self.propertyValue[3] = '3'
        self.propertyValue[4] = '60'
        self.propertyValue[5] = '300'
        self.propertyValue[6] = '0'
        self.propertyValue[7] = '0'
        self.propertyValue[8] = ''
        self.propertyValue[9] = '15'
        self.propertyValue[10] = 'no'
        self.propertyValue[11] = '120'
        self.propertyValue[12] = '3'
        self.propertyValue[13] = '0'
        self.propertyValue[14] = '0'
        self.propertyValue[15] = '2'
        self.propertyValue[16] = 'no'
        self.propertyValue[17] = '16'
        self.propertyValue[18] = 'no'
        self.propertyValue[19] = '1'
        self.propertyValue[20] = '0.0'
        self.propertyValue[21] = '1'
        self.propertyValue[22] = '100'
        self.propertyValue[23] = '60'
        self.propertyValue[24] = 'No'
        super(RegControlObjImpl, self).initPropertyValues(RegControl.NumPropsThisClass)

    def setPendingTapChange(self, value):
        self.pendingTapChange = value
        self.setDblTraceParameter(value)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.getControlledElement() is not None:
            self.setEnabled(self.getControlledElement().isEnabled())
            if self.usingRegulatedBus:
                self.setNPhases(1)
            else:
                self.setNPhases(self.getControlledElement().getNPhases())
            self.setNConds(self.nPhases)
            if (
                self.getControlledElement().getDSSClassName().equalsIgnoreCase('transformer')
            ):
                # sets name of i-th terminal's connected bus in RegControl's bus list
                # this value will be used to set the nodeRef array (see sample function)
                if self.usingRegulatedBus:
                    self.setBus(1, self.regulatedBus)
                    # hopefully this will actually exist
                else:
                    self.setBus(1, self.getControlledElement().getBus(self.elementTerminal))
                    # buffer to hold regulator voltages
                    self.VBuffer = Utilities.resizeArray(self.VBuffer, self.getControlledElement().getNPhases())
                    self.CBuffer = Utilities.resizeArray(self.CBuffer, self.getControlledElement().getYorder())
        super(RegControlObjImpl, self).makePosSequence()

    def computeTimeDelay(self, Vavg):
        if self.inverseTime:
            return self.timeDelay / self.Math.min(10.0, (2.0 * self.Math.abs(self.Vreg - Vavg)) / self.bandwidth)
        else:
            return self.timeDelay

    def getPendingTapChange(self):
        return self.pendingTapChange

    def getTargetVoltage(self):
        return self.Vreg

    def getBandwidth(self):
        return self.bandwidth

    def getPTRatio(self):
        return self.PTRatio

    def getCTRating(self):
        return self.CTRating

    def getLineDropR(self):
        return self.R

    def getLineDropX(self):
        return self.X

    def getRevTargetVoltage(self):
        return self.revVreg

    def getRevBandwidth(self):
        return self.revBandwidth

    def getRevR(self):
        return self.revR

    def getRevX(self):
        return self.revX

    def useReverseDrop(self):
        return self.isReversible

    def useLineDrop(self):
        return self.LDCActive

    def getTapDelay(self):
        return self.tapDelay

    def getMaxTapChange(self):
        return self.tapLimitPerChange

    def isInverseTime(self):
        return self.inverseTime

    def getVoltageLimit(self):
        return self.VLimit

    def useLimit(self):
        # FIXME Private members in OpenDSS
        return self.VLimitActive

    def getVReg(self):
        return self.Vreg

    def setVReg(self, value):
        self.Vreg = value

    def getR(self):
        return self.R

    def setR(self, value):
        self.R = value

    def getX(self):
        return self.X

    def setX(self, value):
        self.X = value

    def getRevVReg(self):
        return self.revVreg

    def setRevVReg(self, value):
        self.revVreg = value

    def getRegulatedBus(self):
        return self.regulatedBus

    def setRegulatedBus(self, bus):
        self.regulatedBus = bus

    def isReversible(self):
        return self.isReversible

    def setReversible(self, value):
        self.isReversible = value

    def isLDCActive(self):
        return self.LDCActive

    def setLDCActive(self, active):
        self.LDCActive = active

    def isUsingRegulatedBus(self):
        return self.usingRegulatedBus

    def setUsingRegulatedBus(self, value):
        self.usingRegulatedBus = value

    def isDebugTrace(self):
        return self.debugTrace

    def setDebugTrace(self, value):
        self.debugTrace = value

    def isArmed(self):
        return self.armed

    def setArmed(self, value):
        self.armed = value

    def getTraceFile(self):
        return self.traceFile

    def setTraceFile(self, tracefile):
        self.traceFile = tracefile

    def getTapLimitPerChange(self):
        return self.tapLimitPerChange

    def setTapLimitPerChange(self, tapLimit):
        self.tapLimitPerChange = tapLimit

    def getTapWinding(self):
        return self.tapWinding

    def setTapWinding(self, winding):
        self.tapWinding = winding

    def isInversetime(self):
        return self.inverseTime

    def setInverseTime(self, time):
        self.inverseTime = time

    def getVLimit(self):
        return self.VLimit

    def setVLimit(self, limit):
        self.VLimit = limit

    def isVLimitActive(self):
        return self.VLimitActive

    def setVLimitActive(self, limitActive):
        self.VLimitActive = limitActive

    def getPTPhase(self):
        return self.PTPhase

    def setPTPhase(self, phase):
        self.PTPhase = phase

    def getControlledPhase(self):
        return self.controlledPhase

    def setControlledPhase(self, phase):
        self.controlledPhase = phase

    def getVBuffer(self):
        return self.VBuffer

    def setVBuffer(self, buffer):
        self.VBuffer = buffer

    def getCBuffer(self):
        return self.CBuffer

    def setCBuffer(self, buffer):
        self.CBuffer = buffer

    def setBandwidth(self, value):
        self.bandwidth = value

    def setPTRatio(self, ratio):
        self.PTRatio = ratio

    def setCTRating(self, rating):
        self.CTRating = rating

    def setRevBandwidth(self, bandwidth):
        self.revBandwidth = bandwidth

    def setRevR(self, value):
        self.revR = value

    def setRevX(self, value):
        self.revX = value

    def setTapDelay(self, delay):
        self.tapDelay = delay

    def getKWRevPowerThreshold(self):
        return self.kWRevPowerThreshold

    def setKWRevPowerThreshold(self, threshold):
        self.kWRevPowerThreshold = threshold

    def setRevDelay(self, delay):
        self.revDelay = delay

    def setReverseNeutral(self, value):
        self.reverseNeutral = value

    def setRevPowerThreshold(self, threshold):
        self.revPowerThreshold = threshold

    def getRevPowerThreshold(self):
        return self.revPowerThreshold

    def getRevDelay(self):
        return self.revDelay

    def isReverseNeutral(self):
        return self.reverseNeutral

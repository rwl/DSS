from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.delivery.PDElement import (PDElement,)
from dss.common.impl.DSSCktElement import (DSSCktElement,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class PDElementImpl(DSSCktElement, PDElement):
    normAmps = None
    emergAmps = None
    faultRate = None
    pctPerm = None
    hrsToRepair = None
    fromTerminal = None
    toTerminal = None
    # set by meter zone for radial feeder
    isShunt = None
    numCustomers = None
    totalCustomers = None
    parentPDElement = None
    meterObj = None
    sensorObj = None
    # upline energymeter
    # upline sensor for this element for allocation and estimation
    overloadUE = None
    overloadEEN = None
    # indicate amount of branch overload

    def __init__(self, parClass):
        super(PDElementImpl, self)(parClass)
        self.isShunt = False
        self.fromTerminal = 1
        self.numCustomers = 0
        self.totalCustomers = 0
        self.sensorObj = None
        self.meterObj = None
        self.parentPDElement = None
        self.objType = DSSClassDefs.PD_ELEMENT

    def getCurrents(self, curr):
        try:
            if self.isEnabled():
                sol = DSSGlobals.activeCircuit.getSolution()
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    self.VTerminal[i] = sol.getNodeV()[self.nodeRef[i]]
                self.YPrim.vMult(curr, self.VTerminal)
            else:
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    curr[i] = Complex.ZERO
        except Exception, e:
            DSSGlobals.doErrorMsg('Trying to Get Currents for Element: ' + self.getName() + '.', e.getMessage(), 'Has circuit been solved?', 660)

    def getExcessKVANorm(self, idxTerm):
        if (self.normAmps == 0.0) or (not self.isEnabled()):
            self.overloadEEN = 0.0
            return Complex.ZERO
        kVA = self.getPower(idxTerm).multiply(0.001)
        # also forces computation of current into iTemp
        Factor = (self.maxTerminalOneIMag() / self.normAmps) - 1.0
        if Factor > 0.0:
            self.overloadEEN = Factor
            Factor = 1.0 - (1.0 / (Factor + 1.0))
            # to get factor
            result = kVA.multiply(Factor)
        else:
            self.overloadEEN = 0.0
            result = Complex.ZERO
        # **********DEBUG CODE: Use DLL Debug file  ***
        # ****    WriteDLLDebugFile(String.format("%s.%s: Terminal=%u Factor=%.7g kW=%.7g kvar=%.7g Normamps=%.7g Overload_EEN=%.7g Result=%.7g +j %.7g ",
        # 			ParentClass.getName(), getName(), ActiveTerminalIdx, Factor, kVA.re, kVA.im, NormAmps, Overload_EEN, Result.re, Result.im));

        return result

    def getExcessKVAEmerg(self, idxTerm):
        if (self.getEmergAmps() == 0.0) or (not self.isEnabled()):
            self.overloadUE = 0.0
            return Complex.ZERO
        kVA = self.getPower(idxTerm).multiply(0.001)
        # also forces computation of current into iTemp
        Factor = (self.maxTerminalOneIMag() / self.getEmergAmps()) - 1.0
        if Factor > 0.0:
            self.overloadUE = Factor
            Factor = 1.0 - (1.0 / (Factor + 1.0))
            # to get excess
            result = kVA.multiply(Factor)
        else:
            self.overloadUE = 0.0
            result = Complex.ZERO
        return result

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[arrayOffset + 1] = '400'
        # normAmps   TODO Check zero based indexing
        self.propertyValue[arrayOffset + 2] = '600'
        # emerAamps
        self.propertyValue[arrayOffset + 3] = '0.1'
        # faultRate
        self.propertyValue[arrayOffset + 4] = '20'
        # pctPerm
        self.propertyValue[arrayOffset + 5] = '3'
        # hrsToRepair
        super(PDElementImpl, self).initPropertyValues(arrayOffset + 5)

    def getNormAmps(self):
        return self.normAmps

    def setNormAmps(self, amps):
        self.normAmps = amps

    def getEmergAmps(self):
        return self.emergAmps

    def setEmergAmps(self, amps):
        self.emergAmps = amps

    def getFaultRate(self):
        return self.faultRate

    def setFaultRate(self, rate):
        self.faultRate = rate

    def getPctPerm(self):
        return self.pctPerm

    def setPctPerm(self, pct):
        self.pctPerm = pct

    def getHrsToRepair(self):
        return self.hrsToRepair

    def setHrsToRepair(self, hrs):
        self.hrsToRepair = hrs

    def getFromTerminal(self):
        return self.fromTerminal

    def setFromTerminal(self, terminal):
        self.fromTerminal = terminal

    def getToTerminal(self):
        return self.toTerminal

    def setToTerminal(self, terminal):
        self.toTerminal = terminal

    def isShunt(self):
        return self.isShunt

    def setShunt(self, value):
        self.isShunt = value

    def getNumCustomers(self):
        return self.numCustomers

    def setNumCustomers(self, num):
        self.numCustomers = num

    def getTotalCustomers(self):
        return self.totalCustomers

    def setTotalCustomers(self, total):
        self.totalCustomers = total

    def getParentPDElement(self):
        return self.parentPDElement

    def setParentPDElement(self, parent):
        self.parentPDElement = parent

    def getMeterObj(self):
        return self.meterObj

    def setMeterObj(self, meter):
        self.meterObj = meter

    def getSensorObj(self):
        return self.sensorObj

    def setSensorObj(self, sensor):
        self.sensorObj = sensor

    def getOverloadUE(self):
        return self.overloadUE

    def setOverload_UE(self, overload):
        self.overloadUE = overload

    def getOverloadEEN(self):
        return self.overloadEEN

    def setOverloadEEN(self, overload):
        self.overloadEEN = overload

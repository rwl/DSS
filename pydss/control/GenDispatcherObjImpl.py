from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.GenDispatcher import (GenDispatcher,)
from dss.control.GenDispatcherObj import (GenDispatcherObj,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class GenDispatcherObjImpl(ControlElemImpl, GenDispatcherObj):
    kWLimit = None
    kWBand = None
    halfKWBand = None
    kVArLimit = None
    totalWeight = None
    listSize = None
    generatorNameList = None
    genPointerList = None
    weights = None
    monitoredElement = None

    def __init__(self, parClass, genDispatcherName):
        super(GenDispatcherObjImpl, self)(parClass)
        self.setName(genDispatcherName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.elementName = ''
        self.setControlledElement(None)
        # not used in this control
        self.elementTerminal = 1
        self.monitoredElement = None
        self.generatorNameList = list()
        self.weights = None
        self.genPointerList = list(20)
        # default size and increment
        self.listSize = 0
        self.kWLimit = 8000.0
        self.kWBand = 100.0
        self.totalWeight = 1.0
        self.halfKWBand = self.kWBand / 2.0
        self.initPropertyValues(0)
        self.kVArLimit = self.kWLimit / 2.0
        # recalcElementData();

    def recalcElementData(self):
        # Check for existence of monitored element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            self.monitoredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            if self.elementTerminal > self.monitoredElement.getNTerms():
                DSSGlobals.doErrorMsg('GenDispatcher: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 371)
            else:
                # sets name of i-th terminal's connected bus in GenDispatcher's buslist
                self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
        else:
            DSSGlobals.doSimpleMsg('Monitored Element in GenDispatcher.' + self.getName() + ' does not exist:\"' + self.elementName + '\"', 372)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.monitoredElement is not None:
            self.setNPhases(self.getControlledElement().getNPhases())
            self.setNConds(self.nPhases)
            self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
        super(GenDispatcherObjImpl, self).makePosSequence()

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

    def dumpProperties(self, f, complete):
        super(GenDispatcherObjImpl, self).dumpProperties(f, complete)
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
        # Do nothing
        pass

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        # if list is not define, go make one from all generators in circuit
        if len(self.genPointerList) == 0:
            self.makeGenList()
        if self.listSize > 0:
            # MonitoredElement.ActiveTerminalIdx = ElementTerminal;
            S = self.monitoredElement.getPower(self.elementTerminal)
            # power in active terminal
            PDiff = (S.getReal() * 0.001) - self.kWLimit
            QDiff = (S.getImaginary() * 0.001) - self.kVArLimit
            # redispatch the vars
            genKWChanged = False
            genKVArChanged = False
            if self.Math.abs(PDiff) > self.halfKWBand:
                # redispatch generators
                # PDiff is kW needed to get back into band
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.listSize):
                        break
                    gen = self.genPointerList[i]
                    # compute new dispatch value for this generator ...
                    genKW = self.Math.max(1.0, gen.getKWBase() + (PDiff * (self.weights[i] / self.totalWeight)))
                    if genKW != gen.getKWBase():
                        gen.setKWBase(genKW)
                        genKWChanged = True
            if self.Math.abs(QDiff) > self.halfKWBand:
                # redispatch generators
                # QDiff is kvar needed to get back into band
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.listSize):
                        break
                    gen = self.genPointerList[i]
                    # compute new dispatch value for this generator ...
                    genKVAr = self.Math.max(0.0, gen.getKVArBase() + (QDiff * (self.weights[i] / self.totalWeight)))
                    if genKVAr != gen.getKVArBase():
                        gen.setKVArBase(genKVAr)
                        genKVArChanged = True
            if genKWChanged or genKVArChanged:
                # only push onto control queue if there has been a change
                ckt = DSSGlobals.activeCircuit
                sol = ckt.getSolution()
                sol.setLoadsNeedUpdating(True)
                # force recalc of power parms
                # push present time onto control queue to force re solve at new dispatch value
                ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, self)

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = ''
        # "element";
        self.propertyValue[1] = '1'
        # "terminal";
        self.propertyValue[2] = '8000'
        self.propertyValue[3] = '100'
        self.propertyValue[4] = '0'
        self.propertyValue[5] = ''
        self.propertyValue[6] = ''
        super(GenDispatcherObjImpl, self).initPropertyValues(GenDispatcher.NumPropsThisClass)
        # TODO Check zero based indexing

    def makeGenList(self):
        result = False
        genClass = DSSClassDefs.getDSSClass('generator')
        if self.listSize > 0:
            # name list is defined - use it
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.listSize):
                    break
                gen = genClass.find(self.generatorNameList[i - 1])
                if gen is not None and gen.isEnabled():
                    self.genPointerList.add(gen)
        else:
            # Search through the entire circuit for enabled generators and add them to the list
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < genClass.getElementCount()):
                    break
                gen = genClass.getElementList().get(i)
                if gen.isEnabled():
                    self.genPointerList.add(gen)
            # Allocate uniform weights
            self.listSize = len(self.genPointerList)
            self.weights = Utilities.resizeArray(self.weights, self.listSize)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.listSize):
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
            if not (i < self.listSize):
                break
            self.totalWeight = self.totalWeight + self.weights[i]
        if len(self.genPointerList) > 0:
            result = True
        return result

    def reset(self):
        """Reset to initial defined state."""
        # super.reset();
        # FIXME Private members in OpenDSS
        pass

    def getKWLimit(self):
        return self.kWLimit

    def setKWLimit(self, limit):
        self.kWLimit = limit

    def getKWBand(self):
        return self.kWBand

    def setKWBand(self, band):
        self.kWBand = band

    def getHalfKWBand(self):
        return self.halfKWBand

    def setHalfKWBand(self, band):
        self.halfKWBand = band

    def getKVArLimit(self):
        return self.kVArLimit

    def setKVArLimit(self, limit):
        self.kVArLimit = limit

    def getTotalWeight(self):
        return self.totalWeight

    def setTotalWeight(self, total):
        self.totalWeight = total

    def getListSize(self):
        return self.listSize

    def setListSize(self, size):
        self.listSize = size

    def getGeneratorNameList(self):
        return self.generatorNameList

    def setGeneratorNameList(self, list):
        self.generatorNameList = list

    def getGenPointerList(self):
        return self.genPointerList

    def setGenPointerList(self, list):
        self.genPointerList = list

    def getWeights(self):
        return self.weights

    def setWeights(self, values):
        self.weights = values

    def getMonitoredElement(self):
        return self.monitoredElement

    def setMonitoredElement(self, element):
        self.monitoredElement = element

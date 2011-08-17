from __pyjamas__ import (ARGERROR,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.general.impl.OHLineConstantsImpl import (OHLineConstantsImpl,)
from dss.general.impl.TSLineConstantsImpl import (TSLineConstantsImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.general.impl.CNLineConstantsImpl import (CNLineConstantsImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.impl.ConductorChoice import (ConductorChoice,)
from dss.general.LineGeometryObj import (LineGeometryObj,)
from dss.general.LineGeometry import (LineGeometry,)


class LineGeometryObjImpl(DSSObjectImpl, LineGeometryObj):

    class LineGeometryProblem(Exception):

        def __init__(self, string):
            # TODO Auto-generated constructor stub
            pass

        serialVersionUID = -181990921259563478L

    phaseChoice = None
    nConds = None
    nPhases = None
    condName = None
    wireData = None
    X = None
    Y = None
    units = None
    lastUnit = None
    dataChanged = None
    reduce = None
    activeCond = None
    spacingType = None
    lineData = None
    normAmps = None
    emergAmps = None

    def __init__(self, parClass, lineGeometryName):
        super(LineGeometryObjImpl, self)(parClass)
        self.setName(lineGeometryName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.dataChanged = True
        self.phaseChoice = ConductorChoice.UNKNOWN
        self.condName = None
        self.wireData = None
        self.X = None
        self.Y = None
        self.units = None
        self.lineData = None
        self.spacingType = ''
        self.setNConds(3)
        # allocates terminals
        self.nPhases = 3
        self.setActiveCond(1)
        # TODO Check zero based indexing
        self.lastUnit = LineUnits.UNITS_FT
        self.normAmps = 0.0
        self.emergAmps = 0.0
        self.reduce = False
        self.initPropertyValues(0)

    def dumpProperties(self, f, complete):
        super(LineGeometryObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < 2):
                break
            # TODO Check zero based indexing
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        _1 = True
        j = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                j += 1
            if not (j < self.nConds):
                break
            self.setActiveCond(j)
            print '~ ' + self.parentClass.getPropertyName()[2] + '=' + self.getPropertyValue(2)
            print '~ ' + self.parentClass.getPropertyName()[3] + '=' + self.getPropertyValue(3)
            print '~ ' + self.parentClass.getPropertyName()[4] + '=' + self.getPropertyValue(4)
            print '~ ' + self.parentClass.getPropertyName()[5] + '=' + self.getPropertyValue(5)
            print '~ ' + self.parentClass.getPropertyName()[6] + '=' + self.getPropertyValue(6)
        _2 = True
        i = 7
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                result = String.format.format('%d', self.activeCond)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = self.condName[self.activeCond]
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                result = self.condName[self.activeCond]
                break
            if (_1 is True) or (_0 == 13):
                _1 = True
                result = self.condName[self.activeCond]
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                result = String.format.format('%-g', self.X[self.activeCond])
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                result = String.format.format('%-g', self.Y[self.activeCond])
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                result = LineUnits.lineUnitsStr(self.units[self.activeCond])
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                result = '['
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nConds):
                        break
                    result = result + self.condName[i] + ' '
                result = result + ']'
                break
            if (_1 is True) or (_0 == 14):
                _1 = True
                result = '['
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nConds):
                        break
                    result = result + self.condName[i] + ' '
                result = result + ']'
                break
            if (_1 is True) or (_0 == 15):
                _1 = True
                result = '['
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.nConds):
                        break
                    result = result + self.condName[i] + ' '
                result = result + ']'
                break
            if True:
                _1 = True
                result = super(LineGeometryObjImpl, self).getPropertyValue(index)
                break
            break
        return result

    def getXCoord(self, i):
        return self.X[i] if i < self.nConds else 0.0
        # TODO Check zero based indexing

    def getYCoord(self, i):
        return self.Y[i] if i < self.nConds else 0.0

    def getConductorName(self, i):
        return self.condName[i] if i < self.nConds else ''

    def getConductorData(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.wireData
        elif _1 == 1:
            i, = _0
            return self.wireData[i] if i < self.nConds else None
        else:
            raise ARGERROR(0, 1)

    def getNConds(self):
        return self.nPhases if self.reduce else self.nConds

    def getRhoEarth(self):
        return self.lineData.getRhoEarth()

    def getYcMatrix(self, f, length, units):
        result = None
        if self.dataChanged:
            # TODO Auto-generated catch block
            try:
                self.updateLineGeometryData(f)
            except LineGeometryProblem, e:
                e.printStackTrace()
        if not DSSGlobals.solutionAbort:
            result = self.lineData.getYcMatrix(f, length, units)
        return result

    def getZMatrix(self, f, length, units):
        result = None
        if self.dataChanged:
            # TODO Auto-generated catch block
            try:
                self.updateLineGeometryData(f)
            except LineGeometryProblem, e:
                e.printStackTrace()
        if not DSSGlobals.solutionAbort:
            result = self.lineData.getZMatrix(f, length, units)
        return result

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '3'
        self.propertyValue[1] = '3'
        self.propertyValue[2] = '1'
        self.propertyValue[3] = ''
        self.propertyValue[4] = '0'
        self.propertyValue[5] = '32'
        self.propertyValue[6] = 'ft'
        self.propertyValue[7] = '0'
        self.propertyValue[8] = '0'
        super(LineGeometryObjImpl, self).initPropertyValues(LineGeometry.NumPropsThisClass)

    def saveWrite(self, f):
        """Overrides standard saveWrite.
        LineGeometry structure not conducive to standard means of saving.
        """
        # Write only properties that were explicitly set in the
        # final order they were actually set.

        iProp = self.getNextPropertySet(0)
        # works on activeDSSObject
        if iProp >= 0:
            # TODO Check zero based indexing
            print 
        while iProp >= 0:
            _0 = self.parentClass.getRevPropertyIdxMap()[iProp]
            _1 = False
            while True:
                if _0 == 2:
                    _1 = True
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.nConds):
                            break
                        print String.format.format('~ Cond=%d wire=%s X=%.7g h=%.7g units=%s', i, self.condName[i], self.X[i], self.Y[i], LineUnits.lineUnitsStr(self.units[i]))
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.nConds):
                            break
                        print String.format.format('~ Cond=%d wire=%s X=%.7g h=%.7g units=%s', i, self.condName[i], self.X[i], self.Y[i], LineUnits.lineUnitsStr(self.units[i]))
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < self.nConds):
                            break
                        print String.format.format('~ Cond=%d wire=%s X=%.7g h=%.7g units=%s', i, self.condName[i], self.X[i], self.Y[i], LineUnits.lineUnitsStr(self.units[i]))
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    print String.format.format('~ normamps=%.4g', self.normAmps)
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    print String.format.format('~ emergamps=%.4g', self.emergAmps)
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    if self.reduce:
                        print '~ Reduce=Yes'
                    break
                if True:
                    _1 = True
                    print String.format.format('~ %s=%s', self.parentClass.getPropertyName()[self.parentClass.getRevPropertyIdxMap()[iProp]], Utilities.checkForBlanks(self.propertyValue[iProp]))
                    break
                break
            iProp = self.getNextPropertySet(iProp)

    def setActiveCond(self, value):
        # FIXME Private method in OpenDSS
        if value > 0:
            if value <= self.nConds:
                self.setActiveCond(value)
                if self.units[self.activeCond] == -1:
                    self.units[self.activeCond] = self.lastUnit
                    # makes this a sticky value so you don't have to repeat it

    def changeLineConstantsType(self, newPhaseChoice):
        newLineData = None
        needNew = False
        if newPhaseChoice != self.phaseChoice:
            needNew = True
        if self.lineData is None:
            needNew = True
        elif self.nConds != self.lineData.getNumConds():
            needNew = True
        if needNew:
            _0 = newPhaseChoice
            _1 = False
            while True:
                if _0 == self.OVERHEAD:
                    _1 = True
                    newLineData = OHLineConstantsImpl(self.getNConds())
                    break
                if (_1 is True) or (_0 == self.CONCENTRIC_NEUTRAL):
                    _1 = True
                    newLineData = CNLineConstantsImpl(self.getNConds())
                    break
                if (_1 is True) or (_0 == self.TAPE_SHIELD):
                    _1 = True
                    newLineData = TSLineConstantsImpl(self.getNConds())
                    break
                break
        if newLineData is not None:
            if self.lineData is not None:
                newLineData.setNPhases(self.lineData.getNPhases())
                newLineData.setRhoEarth(self.lineData.getRhoEarth())
            else:
                self.lineData = None
                self.lineData = newLineData
        self.phaseChoice = newPhaseChoice

    def setNConds(self, value):
        self.nConds = value
        if self.lineData is not None:
            self.lineData = None
        self.changeLineConstantsType(self.phaseChoice)
        self.condName = [None] * self.nConds
        # Allocations
        self.wireData = [None] * self.nConds
        self.X = [None] * self.nConds
        self.Y = [None] * self.nConds
        self.units = [None] * self.nConds
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.units[i] = -1
            # default to ft
        self.lastUnit = LineUnits.UNITS_FT

    def getPhaseChoice(self):
        return self.phaseChoice

    def setNPhases(self, value):
        self.nPhases = value
        self.lineData.setNPhases(value)

    def setRhoEarth(self, value):
        # FIXME Private method in OpenDSS
        self.lineData.setRhoEarth(value)

    def updateLineGeometryData(self, f):
        """Call this before using the line data.
        @throws LineGeometryProblem
        """
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.lineData.setX(i, self.units[i], self.X[i])
            self.lineData.setY(i, self.units[i], self.Y[i])
            self.lineData.setRadius(i, self.wireData[i].getRadiusUnits(), self.wireData[i].getRadius())
            self.lineData.setGMR(i, self.wireData[i].getGMRUnits(), self.wireData[i].getGMR60())
            self.lineData.setRdc(i, self.wireData[i].getResistanceUnits(), self.wireData[i].getRDC())
            self.lineData.setRac(i, self.wireData[i].getResistanceUnits(), self.wireData[i].getR60())
            # Rac
            if isinstance(self.wireData[i], CNDataObj):
                cnlc = self.lineData
                cnd = self.wireData[i]
                cnlc.setEpsR(i, cnd.getEpsR())
                cnlc.setInsLayer(i, cnd.getRadiusUnits(), cnd.getInsLayer())
                cnlc.setDiaIns(i, cnd.getRadiusUnits(), cnd.getDiaIns())
                cnlc.setDiaCable(i, cnd.getRadiusUnits(), cnd.getDiaCable())
                cnlc.setKStrand(i, cnd.getkStrand())
                cnlc.setDiaStrand(i, cnd.getRadiusUnits(), cnd.getDiaStrand())
                cnlc.setGmrStrand(i, cnd.getGMRUnits(), cnd.getGmrStrand())
                cnlc.setRStrand(i, cnd.getResistanceUnits(), cnd.getRStrand())
            elif isinstance(self.wireData[i], TSDataObj):
                tslc = self.lineData
                tsd = self.wireData[i]
                tslc.setEpsR(i, tsd.getEpsR())
                tslc.setInsLayer(i, tsd.getRadiusUnits(), tsd.getInsLayer())
                tslc.setDiaIns(i, tsd.getRadiusUnits(), tsd.getDiaIns())
                tslc.setDiaCable(i, tsd.getRadiusUnits(), tsd.getDiaCable())
                tslc.setDiaShield(i, tsd.getRadiusUnits(), tsd.getDiaShield())
                tslc.setTapeLayer(i, tsd.getRadiusUnits(), tsd.getTapeLayer())
                tslc.setTapeLap(i, tsd.getTapeLap())
        self.lineData.setNPhases(self.nPhases)
        self.dataChanged = False
        # Before we calc, check for bad conductor definitions
        lineGeomErrMsg = str()
        if self.lineData.conductorsInSameSpace(lineGeomErrMsg):
            DSSGlobals.solutionAbort = True
            raise self.LineGeometryProblem('Error in LineGeometry.' + self.getName() + ': ' + str(lineGeomErrMsg))
        else:
            self.lineData.calc(f)
            if self.reduce:
                self.lineData.reduce()
                # reduce out neutrals

    def loadSpacingAndWires(self, spc, wires):
        """Called from a line object that has its own spacing and wires input
        automatically sets reduce=y if the spacing has more wires than phases.
        """
        self.nConds = spc.getNWires()
        # allocates
        self.setNPhases(spc.getNPhases())
        self.spacingType = spc.getName()
        if self.nConds > self.nPhases:
            self.reduce = True
        newPhaseChoice = ConductorChoice.OVERHEAD
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNConds()):
                break
            if isinstance(wires[i], CNDataObj):
                newPhaseChoice = ConductorChoice.CONCENTRIC_NEUTRAL
            if isinstance(wires[i], TSDataObj):
                newPhaseChoice = ConductorChoice.TAPE_SHIELD
        self.changeLineConstantsType(newPhaseChoice)
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.condName[i] = wires[i].getName()
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.wireData[i] = wires[i]
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.X[i] = spc.getXCoord(i)
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.Y[i] = spc.getYCoord(i)
        _5 = True
        i = 0
        while True:
            if _5 is True:
                _5 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.units[i] = spc.getUnits()
        self.dataChanged = True
        self.normAmps = wires[0].getNormAmps()
        # TODO Check zero based indexing
        self.emergAmps = wires[0].getEmergAmps()
        # TODO Auto-generated catch block
        try:
            self.updateLineGeometryData(DSSGlobals.activeCircuit.getSolution().getFrequency())
        except LineGeometryProblem, e:
            e.printStackTrace()

    def getUnits(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.units
        elif _1 == 1:
            i, = _0
            return self.units[i]
        else:
            raise ARGERROR(0, 1)

    def getNPhases(self):
        return self.nPhases

    def getActiveCond(self):
        return self.activeCond

    def getNWires(self):
        return self.nConds

    def getNormAmps(self):
        return self.normAmps

    def setNormAmps(self, amps):
        self.normAmps = amps

    def getEmergAmps(self):
        return self.emergAmps

    def setEmergAmps(self, amps):
        # FIXME Private members in OpenDSS.
        self.emergAmps = amps

    def getCondName(self):
        return self.condName

    def setCondName(self, name):
        self.condName = name

    def setConductorData(self, data):
        self.wireData = data

    def getX(self):
        return self.X

    def setX(self, x):
        self.X = x

    def getY(self):
        return self.Y

    def setY(self, y):
        self.Y = y

    def setUnits(self, value):
        self.units = value

    def getLastUnit(self):
        return self.lastUnit

    def setLastUnit(self, unit):
        self.lastUnit = unit

    def isDataChanged(self):
        return self.dataChanged

    def setDataChanged(self, changed):
        self.dataChanged = changed

    def isReduce(self):
        return self.reduce

    def setReduce(self, value):
        self.reduce = value

    def getSpacingType(self):
        return self.spacingType

    def setSpacingType(self, type):
        self.spacingType = type

    def getLineData(self):
        return self.lineData

    def setLineData(self, data):
        self.lineData = data

    def setPhaseChoice(self, choice):
        self.phaseChoice = choice

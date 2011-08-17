from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.ISourceObj import (ISourceObj,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.conversion.ISource import (ISource,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class ISourceObjImpl(PCElementImpl, ISourceObj):
    amps = None
    angle = None
    phaseShift = None
    scanType = None
    sequenceType = None
    srcFrequency = None

    def __init__(self, parClass, sourceName):
        super(ISourceObjImpl, self)(parClass)
        self.setName(sourceName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # SOURCE + NON_PCPD_ELEM;  // don't want this in PC Element List
        self.setNPhases(3)
        self.nConds = 3
        self.setNTerms(1)
        self.amps = 0.0
        self.angle = 0.0
        self.srcFrequency = self.baseFrequency
        self.phaseShift = 120.0
        self.scanType = 1
        # pos sequence
        self.sequenceType = 1
        self.initPropertyValues(0)
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()

    def recalcElementData(self):
        self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
        if self.getSpectrumObj() is None:
            DSSGlobals.doSimpleMsg('Spectrum object \"' + self.getSpectrum() + '\" for device ISource.' + self.getName() + ' not found.', 333)
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))

    def calcYPrim(self):
        # build only YPrim_Series
        if self.isYprimInvalid():
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            self.YPrimSeries.clear()
            self.YPrim.clear()
        # Yprim = 0 for ideal current source; just leave it zeroed
        # Now account for open conductors.
        # For any conductor that is open, zero out row and column.

        super(ISourceObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def getBaseCurr(self):
        result = None
        try:
            sol = DSSGlobals.activeCircuit.getSolution()
            # Get first phase current
            if sol.isHarmonicModel():
                srcHarmonic = sol.getFrequency() / self.srcFrequency
                result = self.getSpectrumObj().getMult(srcHarmonic).multiply(self.amps)
                # base current for this harmonic
                result = Utilities.rotatePhasorDeg(result, srcHarmonic, self.angle)
            elif (
                self.Math.abs(sol.getFrequency() - self.srcFrequency) < DSSGlobals.EPSILON2
            ):
                result = ComplexUtil.polarDeg2Complex(self.amps, self.angle)
            else:
                result = Complex.ZERO
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error computing current for ISource.' + self.getName() + '. Check specification. Aborting.', 334)
            if DSSGlobals.inRedirect:
                DSSGlobals.redirectAbort = True
        return result

    def injCurrents(self):
        """Sum currents directly into solution array."""
        self.getInjCurrents(self.getInjCurrent())
        return super(ISourceObjImpl, self).injCurrents()
        # adds into system array

    def getCurrents(self, curr):
        """Total currents into a device."""
        try:
            self.getInjCurrents(self.complexBuffer)
            # get present value of inj currents
            # add together with YPrim currents
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                curr[i] = self.complexBuffer[i].negate()
        except Exception, e:
            DSSGlobals.doErrorMsg('GetCurrents for ISource element: ' + self.getName() + '.', e.getMessage(), 'Inadequate storage allotted for circuit element.', 335)

    def getInjCurrents(self, curr):
        """Fill up an array of injection currents."""
        sol = DSSGlobals.activeCircuit.getSolution()
        baseCurr = self.getBaseCurr()
        # this func applies spectrum if needed
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            curr[i] = baseCurr
            if i < self.getNPhases():
                if sol.isHarmonicModel():
                    _1 = self.scanType
                    _2 = False
                    while True:
                        if _1 == 1:
                            _2 = True
                            baseCurr = Utilities.rotatePhasorDeg(baseCurr, 1.0, -self.getPhaseShift())
                            # maintain positive sequence for ISource
                            break
                        if (_2 is True) or (_1 == 0):
                            _2 = True
                            break
                        if True:
                            _2 = True
                            baseCurr = Utilities.rotatePhasorDeg(baseCurr, sol.getHarmonic(), -self.getPhaseShift())
                            # rotate by frequency
                            # Harmonic 1 will be pos; 2 is neg; 3 is zero, and so on.
                            break
                        break
                else:
                    _3 = self.sequenceType
                    _4 = False
                    while True:
                        if _3 == -1:
                            _4 = True
                            baseCurr = Utilities.rotatePhasorDeg(baseCurr, 1.0, self.phaseShift)
                            # neg seq
                            break
                        if (_4 is True) or (_3 == 0):
                            _4 = True
                            break
                        if True:
                            _4 = True
                            baseCurr = Utilities.rotatePhasorDeg(baseCurr, 1.0, -self.phaseShift)
                            # maintain pos seq
                            break
                        break

    def dumpProperties(self, f, complete):
        super(ISourceObjImpl, self).dumpProperties(f, complete)
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
            print 

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, self.getBus(0))
        self.setPropertyValue(1, '0')
        self.setPropertyValue(2, '0')
        self.setPropertyValue(3, String.format.format('%-.6g', self.srcFrequency))
        self.setPropertyValue(4, '3')
        self.setPropertyValue(5, 'pos')
        self.setPropertyValue(6, 'pos')
        super(ISourceObjImpl, self).initPropertyValues(ISource.NumPropsThisClass)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.getNPhases() > 1:
            Parser.getInstance().setCmdString('phases=1')
            self.edit()
        super(ISourceObjImpl, self).makePosSequence()

    def getSrcFrequency(self):
        return self.srcFrequency

    def setSrcFrequency(self, frequency):
        # FIXME Private members in OpenDSS
        self.srcFrequency = frequency

    def getAmps(self):
        return self.amps

    def setAmps(self, value):
        self.amps = value

    def getAngle(self):
        return self.angle

    def setAngle(self, ang):
        self.angle = ang

    def getPhaseShift(self):
        return self.phaseShift

    def setPhaseShift(self, shift):
        self.phaseShift = shift

    def getScanType(self):
        return self.scanType

    def setScanType(self, type):
        self.scanType = type

    def getSequenceType(self):
        return self.sequenceType

    def setSequenceType(self, type):
        self.sequenceType = type

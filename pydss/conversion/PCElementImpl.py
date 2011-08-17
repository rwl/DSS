from dss.conversion.PCElement import (PCElement,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.DSSCktElement import (DSSCktElement,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class PCElementImpl(DSSCktElement, PCElement):
    ITerminalUpdated = None
    spectrum = None
    spectrumObj = None
    meterObj = None
    sensorObj = None
    injCurrent = None

    def __init__(self, ParClass):
        super(PCElementImpl, self)(ParClass)
        self.spectrum = 'default'
        self.spectrumObj = None
        # have to allocate later because not guaranteed there will be one now
        self.sensorObj = None
        self.meterObj = None
        self.injCurrent = None
        self.ITerminalUpdated = False
        self.objType = DSSClassDefs.PC_ELEMENT

    def injCurrents(self):
        """Add injection currents into system currents array."""
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
            sol.getCurrents()[self.nodeRef[i]] = sol.getCurrents()[self.nodeRef[i]].add(self.injCurrent[i])
        return 0

    def getInjCurrents(self, curr):
        """Get present values of terminal."""
        DSSGlobals.doErrorMsg('PCElement.InjCurrents', 'Improper call to getInjCurrents for element: ' + self.getName() + '.', 'Called PCElement class virtual function instead of actual.', 640)

    def getTerminalCurrents(self, curr):
        """This is called only if we need to compute the terminal currents from the inj currents.

        Such as for harmonic model.
        """
        if self.getITerminalUpdated():
            # just copy ITerminal unless ITerminal=curr
            if curr != self.getITerminal():
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    curr[i] = self.getITerminal()[i]
        else:
            self.YPrim.vMult(curr, self.getVTerminal())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                curr[i] = curr[i].add(self.getInjCurrent()[i].negate())
            self.setITerminalUpdated(True)
        self.ITerminalSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount()

    def getCurrents(self, curr):
        """Get present values of terminal.

        Gets total currents going into a devices terminals.
        """
        try:
            sol = DSSGlobals.activeCircuit.getSolution()
            if self.isEnabled():
                if (
                    sol.lastSolutionWasDirect() and not (sol.isDynamicModel() or sol.isHarmonicModel())
                ):
                    # take a short cut and get currents from YPrim only
                    # for case where model is entirely in Y matrix
                    self.calcYPrimContribution(curr)
                else:
                    self.getTerminalCurrents(curr)
            else:
                # not enabled
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    curr[i] = Complex.ZERO
        except Exception, e:
            DSSGlobals.doErrorMsg('getCurrents for element: ' + self.getName() + '.', e.getMessage(), 'Inadequate storage allotted for circuit element.', 641)

    def calcYPrimContribution(self, curr):
        self.computeVTerminal()
        # apply these voltages to Yprim
        self.YPrim.vMult(curr, self.VTerminal)

    def initHarmonics(self):
        """For harmonics mode"""
        # by default do nothing in the base class
        pass

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[arrayOffset + 1] = self.spectrum
        super(PCElementImpl, self).initPropertyValues(arrayOffset + 1)

    def initStateVars(self):
        """For dynamics mode and control devices."""
        # by default do nothing
        pass

    def integrateStates(self):
        # by default do nothing
        pass

    def getAllVariables(self, states):
        # Do nothing
        pass

    def numVariables(self):
        return 0

    def variableName(self, i):
        # Do nothing
        return ''

    def lookupVariable(self, s):
        """Search through variable name list and return index if found.
        Compare up to length of S.
        """
        result = -1
        # returns -1 for error not found
        testLength = len(s)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numVariables()):
                break
            if self.variableName(i)[:testLength].equalsIgnoreCase(s):
                result = i
                break
        return result

    def dumpProperties(self, f, complete):
        super(PCElementImpl, self).dumpProperties(f, complete)
        if complete:
            print '! VARIABLES'
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numVariables()):
                    break
                print '! ' + i + ': ' + self.variableName(i) + ' = ' + String.format.format('%-.5g', self.getVariable(i))

    def getVariable(self, i):
        # Do nothing here -- up to override function
        return -9999.99

    def setVariable(self, i, value):
        # Do nothing
        pass

    def computeITerminal(self):
        ckt = DSSGlobals.activeCircuit
        if self.ITerminalSolutionCount != ckt.getSolution().getSolutionCount():
            self.getCurrents(self.ITerminal)
            self.ITerminalSolutionCount = ckt.getSolution().getSolutionCount()

    def zeroInjCurrent(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            self.injCurrent[i] = Complex.ZERO

    def setITerminalUpdated(self, value):
        self.ITerminalUpdated = value
        if value:
            self.ITerminalSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount()

    def getITerminalUpdated(self):
        return self.ITerminalUpdated

    def getInjCurrent(self):
        return self.injCurrent

    def getSpectrum(self):
        return self.spectrum

    def setSpectrum(self, value):
        self.spectrum = value

    def getSpectrumObj(self):
        """Upline sensor for this element"""
        return self.spectrumObj

    def setSpectrumObj(self, spectrum):
        self.spectrumObj = spectrum

    def getMeterObj(self):
        """Upline energy meter"""
        return self.meterObj

    def setMeterObj(self, meter):
        self.meterObj = meter

    def getSensorObj(self):
        return self.sensorObj

    def setSensorObj(self, sensor):
        self.sensorObj = sensor

    def setInjCurrent(self, current):
        self.injCurrent = current

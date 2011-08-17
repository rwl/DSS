from dss.general.LineCode import (LineCode,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.general.impl.LineCodeObjImpl import (LineCodeObjImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LineCodeImpl(DSSClassImpl, LineCode):
    activeLineCodeObj = None
    symComponentsChanged = None
    matrixChanged = None

    def __init__(self):
        super(LineCodeImpl, self)()
        self.className = 'LineCode'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = LineCode.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'nphases'
        self.propertyName[1] = 'r1'
        self.propertyName[2] = 'x1'
        self.propertyName[3] = 'r0'
        self.propertyName[4] = 'x0'
        self.propertyName[5] = 'c1'
        self.propertyName[6] = 'c0'
        self.propertyName[7] = 'units'
        self.propertyName[8] = 'rmatrix'
        self.propertyName[9] = 'xmatrix'
        self.propertyName[10] = 'cmatrix'
        self.propertyName[11] = 'baseFreq'
        self.propertyName[12] = 'normamps'
        self.propertyName[13] = 'emergamps'
        self.propertyName[14] = 'faultrate'
        self.propertyName[15] = 'pctperm'
        self.propertyName[16] = 'repair'
        self.propertyName[17] = 'Kron'
        self.propertyName[18] = 'Rg'
        self.propertyName[19] = 'Xg'
        self.propertyName[20] = 'rho'
        self.propertyName[21] = 'neutral'
        self.propertyHelp[0] = 'Number of phases in the line this line code data represents.  Setting this property reinitializes the line code.  Impedance matrix is reset for default symmetrical component.'
        self.propertyHelp[1] = 'Positive-sequence Resistance, ohms per unit length.  See also Rmatrix.'
        self.propertyHelp[2] = 'Positive-sequence Reactance, ohms per unit length.  See also Xmatrix'
        self.propertyHelp[3] = 'Zero-sequence Resistance, ohms per unit length.'
        self.propertyHelp[4] = 'Zero-sequence Reactance, ohms per unit length.'
        self.propertyHelp[5] = 'Positive-sequence capacitance, nf per unit length. See also Cmatrix.'
        self.propertyHelp[6] = 'Zero-sequence capacitance, nf per unit length.'
        self.propertyHelp[7] = 'One of (ohms per ...) {none|mi|km|kft|m|me|ft|in|cm}.  Default is none; assumes units agree with length units' + 'given in Line object'
        self.propertyHelp[8] = 'Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. ' + 'May be used to specify the impedance of any line configuration.  For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[9] = 'Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. ' + 'May be used to specify the impedance of any line configuration.  For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[10] = 'Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. ' + 'May be used to specify the shunt capacitance of any line configuration.  For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[11] = 'Frequency at which impedances are specified.'
        self.propertyHelp[12] = 'Normal ampere limit on line.  This is the so-called Planning Limit. It may also be ' + 'the value above which load will have to be dropped in a contingency.  Usually about ' + '75% - 80% of the emergency (one-hour) rating.'
        self.propertyHelp[13] = 'Emergency ampere limit on line (usually one-hour rating).'
        self.propertyHelp[14] = 'Number of faults per unit length per year.'
        self.propertyHelp[15] = 'Percentage of the faults that become permanent.'
        self.propertyHelp[16] = 'Hours to repair.'
        self.propertyHelp[17] = 'Kron = Y/N. Default=N.  Perform Kron reduction on the impedance matrix after it is formed, reducing order by 1. ' + 'Eliminates the conductor designated by the \"Neutral=\" property. ' + 'Do this after the R, X, and C matrices are defined. Ignored for symmetrical components. ' + 'May be issued more than once to eliminate more than one conductor by resetting the Neutral property after the previous ' + 'invoking of this property. Generally, you do not want to do a Kron reduction on the matrix if you intend to solve at a ' + 'frequency other than the base frequency and exploit the Rg and Xg values.'
        self.propertyHelp[18] = 'Carson earth return resistance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. ' + 'Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). ' + 'This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. ' + 'If not, set both Rg and Xg = 0.'
        self.propertyHelp[19] = 'Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. ' + 'Default value is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). ' + 'This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. ' + 'If not, set both Rg and Xg = 0.'
        self.propertyHelp[20] = 'Default=100 meter ohms.  Earth resitivity used to compute earth correction factor.'
        self.propertyHelp[21] = 'Designates which conductor is the \"neutral\" conductor that will be eliminated by Kron reduction. ' + 'Default is the last conductor (nphases value). After Kron reduction is set to 0. Subsequent issuing of Kron=Yes ' + 'will not do anything until this property is set to a legal value. Applies only to LineCodes defined by R, X, and C matrix.'
        self.activeProperty = LineCode.NumPropsThisClass - 1
        super(LineCodeImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        """Create a new object of this class and add to list."""
        DSSGlobals.activeDSSObject = LineCodeObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setUnits(self, s):
        """Decodes the units string and sets the units variable."""
        self.activeLineCodeObj.setUnits(LineUnits.getUnitsCode(s))

    def setZ1Z0(self, i, value):
        """Set symmetrical component impedances and a flag to indicate they were changed."""
        self.symComponentsChanged = True
        self.activeLineCodeObj.setSymComponentsModel(True)
        _0 = i
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                self.activeLineCodeObj.setR1(value)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                self.activeLineCodeObj.setX1(value)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.activeLineCodeObj.setR0(value)
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                self.activeLineCodeObj.setX0(value)
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                self.activeLineCodeObj.setC1(value)
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                self.activeLineCodeObj.setC0(value)
                break
            break

    def doMatrix(self, i):
        """Set impedances as matrices."""
        nOrder = MutableInt()
        np2 = self.activeLineCodeObj.getNPhases() * self.activeLineCodeObj.getNPhases()
        self.matrixChanged = True
        matBuffer = [None] * np2
        orderFound = Parser.getInstance().parseAsSymMatrix(self.activeLineCodeObj.getNPhases(), matBuffer)
        if orderFound > 0:
            # parse was successful
            _0 = i
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    ZValues = self.activeLineCodeObj.getZ().asArray(nOrder)
                    # TODO Check nOrder is set
                    if nOrder.intValue() == self.activeLineCodeObj.getNPhases():
                        _2 = True
                        j = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                j += 1
                            if not (j < np2):
                                break
                            ZValues[j] = Complex(matBuffer[j], ZValues[j].getImaginary())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    ZValues = self.activeLineCodeObj.getZ().asArray(nOrder)
                    if nOrder.intValue() == self.activeLineCodeObj.getNPhases():
                        _3 = True
                        j = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                j += 1
                            if not (j < np2):
                                break
                            ZValues[j] = Complex(ZValues[j].getReal(), matBuffer[j])
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    factor = DSSGlobals.TWO_PI * self.activeLineCodeObj.getBaseFrequency() * 1e-09
                    ZValues = self.activeLineCodeObj.getYC().asArray(nOrder)
                    if nOrder.intValue() == self.activeLineCodeObj.getNPhases():
                        _4 = True
                        j = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                j += 1
                            if not (j < np2):
                                break
                            ZValues[j] = Complex(ZValues[j].getReal(), factor * matBuffer[j])
                    break
                break
            # TODO Check zero based indexing
        matBuffer = None

    def edit(self):
        """Uses global parser."""
        result = 0
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeLineCodeObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeLineCodeObj
        self.symComponentsChanged = False
        self.matrixChanged = False
        self.activeLineCodeObj.setReduceByKron(False)
        # allow all matrices to be computed it raw form
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
                if paramPointer > 0 and paramPointer <= self.numProperties:
                    self.activeLineCodeObj.setPropertyValue(paramPointer, param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == 0:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Object \"' + self.getName() + '.' + self.getName() + '\"', 101)
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        self.activeLineCodeObj.setNPhases(parser.makeInteger())
                        # use property value to force reallocations
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        self.setZ1Z0(1, parser.makeDouble())
                        # R1
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        self.setZ1Z0(2, parser.makeDouble())
                        # X0
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        self.setZ1Z0(3, parser.makeDouble())
                        # R1
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        self.setZ1Z0(4, parser.makeDouble())
                        # X0
                        break
                    if (_1 is True) or (_0 == 6):
                        _1 = True
                        self.setZ1Z0(5, parser.makeDouble() * 1e-09)
                        # C1   // convert from nano to farads
                        break
                    if (_1 is True) or (_0 == 7):
                        _1 = True
                        self.setZ1Z0(6, parser.makeDouble() * 1e-09)
                        # C0
                        break
                    if (_1 is True) or (_0 == 8):
                        _1 = True
                        self.setUnits(param)
                        break
                    if (_1 is True) or (_0 == 9):
                        _1 = True
                        self.doMatrix(1)
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        self.doMatrix(2)
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        self.doMatrix(3)
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        self.activeLineCodeObj.setBaseFrequency(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        self.activeLineCodeObj.setNormAmps(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        self.activeLineCodeObj.setEmergAmps(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        self.activeLineCodeObj.setFaultRate(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 16):
                        _1 = True
                        self.activeLineCodeObj.setPctPerm(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 17):
                        _1 = True
                        self.activeLineCodeObj.setHrsToRepair(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 18):
                        _1 = True
                        self.activeLineCodeObj.setReduceByKron(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == 19):
                        _1 = True
                        self.activeLineCodeObj.setRg(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 20):
                        _1 = True
                        self.activeLineCodeObj.setXg(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 21):
                        _1 = True
                        self.activeLineCodeObj.setRho(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 22):
                        _1 = True
                        self.activeLineCodeObj.setNeutralConductor(parser.makeInteger())
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeLineCodeObj, paramPointer - LineCode.NumPropsThisClass)
                        break
                    break
                _2 = paramPointer
                _3 = False
                while True:
                    if _2 == 9:
                        _3 = True
                        self.activeLineCodeObj.setSymComponentsModel(False)
                        break
                    if (_3 is True) or (_2 == 10):
                        _3 = True
                        self.activeLineCodeObj.setSymComponentsModel(False)
                        break
                    if (_3 is True) or (_2 == 11):
                        _3 = True
                        self.activeLineCodeObj.setSymComponentsModel(False)
                        break
                    if (_3 is True) or (_2 == 18):
                        _3 = True
                        if (
                            self.activeLineCodeObj.isReduceByKron() and not self.activeLineCodeObj.isSymComponentsModel()
                        ):
                            self.activeLineCodeObj.doKronReduction()
                        break
                    break
                paramName = parser.getNextParam()
                param = parser.makeString()
            if self.activeLineCodeObj.isSymComponentsModel():
                self.activeLineCodeObj.calcMatricesFromZ1Z0()
            if self.matrixChanged:
                self.activeLineCodeObj.getZinv().copyFrom(self.activeLineCodeObj.getZ())
                self.activeLineCodeObj.getZinv().invert()
        return result

    def makeLike(self, lineName):
        result = 0
        # See if we can find this line code in the present collection
        otherLineCode = self.find(lineName)
        if otherLineCode is not None:
            if self.activeLineCodeObj.getNPhases() != otherLineCode.getNPhases():
                self.activeLineCodeObj.setNPhases(otherLineCode.getNPhases())
                if self.activeLineCodeObj.getZ() is not None:
                    self.activeLineCodeObj.setZ(None)
                if self.activeLineCodeObj.getZinv() is not None:
                    self.activeLineCodeObj.setZinv(None)
                if self.activeLineCodeObj.getYC() is not None:
                    self.activeLineCodeObj.setYc(None)
                self.activeLineCodeObj.setZ(CMatrixImpl(self.activeLineCodeObj.getNPhases()))
                self.activeLineCodeObj.setZinv(CMatrixImpl(self.activeLineCodeObj.getNPhases()))
                self.activeLineCodeObj.setYc(CMatrixImpl(self.activeLineCodeObj.getNPhases()))
            self.activeLineCodeObj.getZ().copyFrom(otherLineCode.getZ())
            self.activeLineCodeObj.getZinv().copyFrom(otherLineCode.getZinv())
            self.activeLineCodeObj.getYC().copyFrom(otherLineCode.getYC())
            self.activeLineCodeObj.setBaseFrequency(otherLineCode.getBaseFrequency())
            self.activeLineCodeObj.setR1(otherLineCode.getR1())
            self.activeLineCodeObj.setX1(otherLineCode.getX1())
            self.activeLineCodeObj.setR0(otherLineCode.getR0())
            self.activeLineCodeObj.setX0(otherLineCode.getX0())
            self.activeLineCodeObj.setC1(otherLineCode.getC1())
            self.activeLineCodeObj.setC0(otherLineCode.getC0())
            self.activeLineCodeObj.setRg(otherLineCode.getRg())
            self.activeLineCodeObj.setXg(otherLineCode.getXg())
            self.activeLineCodeObj.setRho(otherLineCode.getRho())
            self.activeLineCodeObj.setNeutralConductor(otherLineCode.getNeutralConductor())
            self.activeLineCodeObj.setNormAmps(otherLineCode.getNormAmps())
            self.activeLineCodeObj.setEmergAmps(otherLineCode.getEmergAmps())
            self.activeLineCodeObj.setFaultRate(otherLineCode.getFaultRate())
            self.activeLineCodeObj.setPctPerm(otherLineCode.getPctPerm())
            self.activeLineCodeObj.setHrsToRepair(otherLineCode.getHrsToRepair())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.activeLineCodeObj.getParentClass().getNumProperties()):
                    break
                self.activeLineCodeObj.setPropertyValue(i, otherLineCode.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in line makeLike: \"' + lineName + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement LineCode.init()', -1)
        return 0

    def getCode(self):
        """Returns active line code string."""
        active = self.elementList.getActive()
        return active.getName()

    def setCode(self, value):
        """Sets the active line code."""
        self.activeLineCodeObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pCode = self.elementList.get(i)
            if pCode.getName().equalsIgnoreCase(value):
                self.activeLineCodeObj = pCode
                return
        DSSGlobals.doSimpleMsg('LineCode: \"' + value + '\" not found.', 103)

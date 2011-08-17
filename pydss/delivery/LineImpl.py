from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.delivery.impl.LineObjImpl import (LineObjImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.delivery.Line import (Line,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LineImpl(PDClassImpl, Line):
    activeLineObj = None
    lineGeometryClass = None
    lineCodeClass = None

    def __init__(self):
        super(LineImpl, self)()
        self.className = 'Line'
        self.classType = self.classType + DSSClassDefs.LINE_ELEMENT
        # in both PD element list and line section lists
        self.activeElement = -1
        self.lineCodeClass = None
        self.lineGeometryClass = None
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = Line.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'bus2'
        self.propertyName[2] = 'linecode'
        self.propertyName[3] = 'length'
        self.propertyName[4] = 'phases'
        self.propertyName[5] = 'r1'
        self.propertyName[6] = 'x1'
        self.propertyName[7] = 'r0'
        self.propertyName[8] = 'x0'
        self.propertyName[9] = 'c1'
        self.propertyName[10] = 'c0'
        self.propertyName[11] = 'rmatrix'
        self.propertyName[12] = 'xmatrix'
        self.propertyName[13] = 'cmatrix'
        self.propertyName[14] = 'Switch'
        self.propertyName[15] = 'Rg'
        self.propertyName[16] = 'Xg'
        self.propertyName[17] = 'rho'
        self.propertyName[18] = 'geometry'
        self.propertyName[19] = 'units'
        self.propertyName[20] = 'spacing'
        self.propertyName[21] = 'wires'
        self.propertyName[22] = 'EarthModel'
        self.propertyName[23] = 'cncables'
        self.propertyName[24] = 'tscables'
        # define property help values
        self.propertyHelp[0] = 'Name of bus to which first terminal is connected.' + DSSGlobals.CRLF + 'Example:' + DSSGlobals.CRLF + 'bus1=busname   (assumes all terminals connected in normal phase order)' + DSSGlobals.CRLF + 'bus1=busname.3.1.2.0 (specify terminal to node connections explicitly)'
        self.propertyHelp[1] = 'Name of bus to which 2nd terminal is connected.'
        self.propertyHelp[2] = 'Name of linecode object describing line impedances.' + DSSGlobals.CRLF + 'If you use a line code, you do not need to specify the impedances here. ' + 'The line code must have been PREVIOUSLY defined. ' + 'The values specified last will prevail over those specified earlier (left-to-right ' + 'sequence of properties).  You can subsequently change the number of phases if symmetrical component quantities are specified.' + 'If no line code or impedance data are specified, the line object ' + 'defaults to 336 MCM ACSR on 4 ft spacing.'
        self.propertyHelp[3] = 'Length of line. Default is 1.0. If units do not match the impedance data, specify \"units\" property. '
        self.propertyHelp[4] = 'Number of phases, this line.'
        self.propertyHelp[5] = 'Positive-sequence Resistance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces ' + 'the program to use the symmetrical component line definition. See also Rmatrix.'
        self.propertyHelp[6] = 'Positive-sequence Reactance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces ' + 'the program to use the symmetrical component line definition.  See also Xmatrix'
        self.propertyHelp[7] = 'Zero-sequence Resistance, ohms per unit length.'
        self.propertyHelp[8] = 'Zero-sequence Reactance, ohms per unit length.'
        self.propertyHelp[9] = 'Positive-sequence capacitance, nf per unit length.  Setting any of R1, R0, X1, X0, C1, C0 forces ' + 'the program to use the symmetrical component line definition. See also Cmatrix.'
        self.propertyHelp[10] = 'Zero-sequence capacitance, nf per unit length.'
        self.propertyHelp[11] = 'Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. ' + 'May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix ' + 'forces program to use the matrix values for line impedance definition. For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[12] = 'Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. ' + 'May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix ' + 'forces program to use the matrix values for line impedance definition.  For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[13] = 'Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. ' + 'May be used to specify the shunt capacitance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix ' + 'forces program to use the matrix values for line impedance definition.  For balanced line models, you may ' + 'use the standard symmetrical component data definition instead.'
        self.propertyHelp[14] = '{y/n | T/F}  Default= no/false.  Designates this line as a switch for graphics and algorithmic purposes. ' + DSSGlobals.CRLF + 'SIDE EFFECT: Sets r1 = 1.0; x1 = 1.0; r0 = 1.0; x0 = 1.0; c1 = 1.1 ; c0 = 1.0;  length = 0.001; You must reset if you want something different.'
        self.propertyHelp[15] = 'Carson earth return resistance per unit length used to compute impedance values at base frequency. ' + 'Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). ' + 'This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. ' + 'If not, set both Rg and Xg = 0.'
        self.propertyHelp[16] = 'Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. ' + 'Default is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). ' + 'This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. ' + 'If not, set both Rg and Xg = 0.'
        self.propertyHelp[17] = 'Default=100 meter ohms.  Earth resitivity used to compute earth correction factor. Overrides Line geometry definition if specified.'
        self.propertyHelp[18] = 'Geometry code for LineGeometry Object. Supercedes any previous definition of line impedance. ' + 'Line constants are computed for each frequency change or rho change. CAUTION: may alter number of phases. ' + 'You cannot subsequently change the number of phases unless you change how the line impedance is defined.'
        self.propertyHelp[19] = 'Length Units = {none | mi|kft|km|m|Ft|in|cm } Default is None - assumes length units match impedance units.'
        self.propertyHelp[20] = 'Reference to a LineSpacing for use in a line constants calculation.' + DSSGlobals.CRLF + 'Must be used in conjunction with the Wires property.' + DSSGlobals.CRLF + 'Specify this before the wires property.'
        self.propertyHelp[21] = 'Array of WireData names for use in a line constants calculation.' + DSSGlobals.CRLF + 'Must be used in conjunction with the Spacing property.' + DSSGlobals.CRLF + 'Specify the Spacing first, and \"ncond\" wires.' + DSSGlobals.CRLF + 'May also be used to specify bare neutrals with cables, using \"ncond-nphase\" wires.'
        self.propertyHelp[22] = 'One of {Carson | FullCarson | Deri}. Default is the global value established with the Set EarthModel command. ' + 'See the Options Help on EarthModel option. This is used to override the global value for this line. This ' + 'option applies only when the \"geometry\" property is used.'
        self.propertyHelp[23] = 'Array of CNData names for use in a cable constants calculation.' + DSSGlobals.CRLF + 'Must be used in conjunction with the Spacing property.' + DSSGlobals.CRLF + 'Specify the Spacing first, using \"nphases\" cncables.' + DSSGlobals.CRLF + 'You may later specify \"nconds-nphases\" wires for separate neutrals'
        self.propertyHelp[24] = 'Array of TSData names for use in a cable constants calculation.' + DSSGlobals.CRLF + 'Must be used in conjunction with the Spacing property.' + DSSGlobals.CRLF + 'Specify the Spacing first, using \"nphases\" tscables.' + DSSGlobals.CRLF + 'You may later specify \"nconds-nphases\" wires for separate neutrals'
        self.activeProperty = Line.NumPropsThisClass - 1
        super(LineImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(LineObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def doRmatrix(self):
        nOrder = MutableInt()
        al = self.activeLineObj
        MatBuffer = [None] * al.getNPhases() * al.getNPhases()
        OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer)
        if OrderFound > 0:
            # parse was successful
            # R
            ZValues = al.getZ().asArray(nOrder)
            if nOrder.intValue() == al.getNPhases():
                _0 = True
                j = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        j += 1
                    if not (j < al.getNPhases() * al.getNPhases()):
                        break
                    ZValues[j] = Complex(MatBuffer[j], ZValues[j].getImaginary())
        MatBuffer = None

    def doXmatrix(self):
        nOrder = MutableInt()
        al = self.activeLineObj
        MatBuffer = [None] * al.getNPhases() * al.getNPhases()
        OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer)
        if OrderFound > 0:
            # parse was successful
            # X
            ZValues = al.getZ().asArray(nOrder)
            if nOrder.intValue() == al.getNPhases():
                _0 = True
                j = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        j += 1
                    if not (j < al.getNPhases() * al.getNPhases()):
                        break
                    ZValues[j] = Complex(ZValues[j].getReal(), MatBuffer[j])
        MatBuffer = None

    def doCmatrix(self):
        nOrder = MutableInt()
        al = self.activeLineObj
        matBuffer = [None] * al.getNPhases() * al.getNPhases()
        orderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), matBuffer)
        if orderFound > 0:
            # parse was successful
            # X
            factor = DSSGlobals.TWO_PI * al.getBaseFrequency() * 1e-09
            YValues = al.getYc().asArray(nOrder)
            if nOrder.intValue() == al.getNPhases():
                _0 = True
                j = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        j += 1
                    if not (j < al.getNPhases() * al.getNPhases()):
                        break
                    YValues[j] = Complex(YValues[j].getReal(), factor * matBuffer[j])
        matBuffer = None

    def edit(self):
        """A line defaults to 3-phases and some typical symmetrical component data.

        Line impedances are specified in per unit length and are multiplied by the length
        when the primitive Y matrix is computed.

        You may specify the impedances of the line either by symmetrical components or
        by R, X, and nodal C matrices  (also per unit length).

        All C's is entered in nano farads.

        The ultimate values are in the matrices. If you specify matrices, then the symmetrical
        component values are ignored. However, if you change any of the symmetrical component values
        the matrices will be recomputed. It is assumed you want to use symmetrical component values.
        Don't mix data entry by matrix and by symmetrical components.

        Note that if you change the number of phases, the matrices are reallocated and reinitialized
        with whatever is currently in the symmetrical component data.
        """
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeLineObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeLineObj)
        # use property to set this value
        al = self.activeLineObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.getNumProperties():
                al.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"Line.' + al.getName() + '\"', 181)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    al.setBus(1, param)
                    # TODO Check zero based indexing
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    al.setBus(2, param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    al.fetchLineCode(param)
                    # define line by conductor code
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    al.setLen(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    al.setR1(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    al.setX1(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    al.setR0(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    al.setX0(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    al.setC1(parser.makeDouble() * 1e-09)
                    # convert from nano to farads
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    al.setC0(parser.makeDouble() * 1e-09)
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    self.doRmatrix()
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    self.doXmatrix()
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    self.doCmatrix()
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    al.setSwitch(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    al.setRg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    al.setXg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    al.setRho(parser.makeDouble())
                    al.setRhoSpecified(True)
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    al.fetchGeometryCode(param)
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    newLengthUnits = LineUnits.getUnitsCode(param)
                    if al.isLineCodeSpecified():
                        al.setUnitsConvert(LineUnits.convertLineUnits(al.getLineCodeUnits(), newLengthUnits))
                    else:
                        al.setUnitsConvert(al.getUnitsConvert() * LineUnits.convertLineUnits(al.getLengthUnits(), newLengthUnits))
                    al.setLengthUnits(newLengthUnits)
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    al.fetchLineSpacing(param)
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    al.fetchWireList(param)
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    al.setEarthModel(Utilities.interpretEarthModel(param))
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    al.fetchCNCableList(param)
                    break
                if (_1 is True) or (_0 == 25):
                    _1 = True
                    al.fetchTSCableList(param)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeLineObj, paramPointer - Line.NumPropsThisClass)
                    break
                break
            # side effects ...
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 4:
                    _3 = True
                    if al.getNPhases() != parser.makeInteger():
                        if not al.isGeometrySpecified() and al.isSymComponentsModel():
                            # ignore change of nPhases if geometry used
                            al.setNPhases(parser.makeInteger())
                            al.setNConds(al.getNPhases())
                            # force reallocation of terminal info
                            al.setYorder(al.getNTerms() * al.getNConds())
                            # al.setYprimInvalid(true);
                            # now set below
                            al.recalcElementData()
                            # reallocate Z, etc.
                        else:
                            DSSGlobals.doSimpleMsg('Illegal change of number of phases for Line.' + al.getName(), 181)
                    break
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 9):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 10):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    al.resetLengthUnits()
                    al.setSymComponentsChanged(True)
                    al.setSymComponentsModel(True)
                    break
                if (_3 is True) or (_2 == 11):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.setSymComponentsModel(False)
                    al.resetLengthUnits()
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    break
                if (_3 is True) or (_2 == 12):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.setSymComponentsModel(False)
                    al.resetLengthUnits()
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    break
                if (_3 is True) or (_2 == 13):
                    _3 = True
                    al.setLineCodeSpecified(False)
                    al.setSymComponentsModel(False)
                    al.resetLengthUnits()
                    al.killGeometrySpecified()
                    al.killSpacingSpecified()
                    break
                if (_3 is True) or (_2 == 14):
                    _3 = True
                    if al.isSwitch():
                        al.setSymComponentsChanged(True)
                        al.setYPrimInvalid(True)
                        al.setGeometrySpecified(False)
                        al.setSpacingSpecified(False)
                        al.setR1(1.0)
                        al.setX1(1.0)
                        al.setR0(1.0)
                        al.setX0(1.0)
                        al.setC1(1.1 * 1e-09)
                        al.setC0(1.0 * 1e-09)
                        al.setLen(0.001)
                        al.resetLengthUnits()
                    break
                if (_3 is True) or (_2 == 16):
                    _3 = True
                    al.setKXg(al.getXg() / self.Math.log(658.5 * self.Math.sqrt(al.getRho() / al.getBaseFrequency())))
                    break
                if (_3 is True) or (_2 == 17):
                    _3 = True
                    al.setKXg(al.getXg() / self.Math.log(658.5 * self.Math.sqrt(al.getRho() / al.getBaseFrequency())))
                    break
                if (_3 is True) or (_2 == 18):
                    _3 = True
                    al.setGeometrySpecified(True)
                    al.setSymComponentsModel(False)
                    al.setSymComponentsChanged(False)
                    break
                if (_3 is True) or (_2 == 20):
                    _3 = True
                    if al.getLineSpacingObj() is not None and al.getWireData() is not None:
                        al.setSpacingSpecified(True)
                        al.setSymComponentsModel(False)
                        al.setSymComponentsChanged(False)
                        al.killGeometrySpecified()
                    al.setYPrimInvalid(True)
                    break
                if (_3 is True) or (_2 == 21):
                    _3 = True
                    if al.getLineSpacingObj() is not None and al.getWireData() is not None:
                        al.setSpacingSpecified(True)
                        al.setSymComponentsModel(False)
                        al.setSymComponentsChanged(False)
                        al.killGeometrySpecified()
                    al.setYPrimInvalid(True)
                    break
                break
            # YPrim invalidation on anything that changes impedance values
            if paramPointer >= 2 and paramPointer <= 13:
                al.setYPrimInvalid(True)
            elif paramPointer == 17:
                if al.isGeometrySpecified() and al.getLineGeometryObj() is not None:
                    al.getLineGeometryObj().setRhoEarth(al.getRho())
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 9:
                    _5 = True
                    al.setCapSpecified(True)
                    break
                if (_5 is True) or (_4 == 10):
                    _5 = True
                    al.setCapSpecified(True)
                    break
                if (_5 is True) or (_4 == 13):
                    _5 = True
                    al.setCapSpecified(True)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        # if (al.isSymComponentsChanged()) al.recalcElementData();
        return result

    def makeLike(self, lineName):
        result = 0
        # See if we can find this line name in the present collection
        otherLine = self.find(lineName)
        if otherLine is not None:
            al = self.activeLineObj
            if al.getNPhases() != otherLine.getNPhases():
                al.setNPhases(otherLine.getNPhases())
                al.setNConds(al.getNPhases())
                # force reallocation of terminals and conductors
                al.setYorder(al.getNConds() * al.getNTerms())
                al.setYPrimInvalid(True)
                if al.getZ() is not None:
                    al.setZ(None)
                if al.getZInv() is not None:
                    al.setZInv(None)
                if al.getYc() is not None:
                    al.setYc(None)
                    # for a line, nPhases = nCond, for now
                al.setZ(CMatrixImpl(al.getNPhases()))
                al.setZInv(CMatrixImpl(al.getNPhases()))
                al.setYc(CMatrixImpl(al.getNPhases()))
            al.getZ().copyFrom(otherLine.getZ())
            # al.getZinv().copyFrom(OtherLine.getZinv());
            al.getYc().copyFrom(otherLine.getYc())
            al.setR1(otherLine.getR1())
            al.setX1(otherLine.getX1())
            al.setR0(otherLine.getR0())
            al.setX0(otherLine.getX0())
            al.setC1(otherLine.getC1())
            al.setC0(otherLine.getC0())
            al.setLen(otherLine.getLen())
            al.setSymComponentsModel(otherLine.isSymComponentsModel())
            al.setCapSpecified(otherLine.isCapSpecified())
            self.classMakeLike(otherLine)
            # take care of inherited class properties
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < al.getParentClass().getNumProperties()):
                    break
                al.setPropertyValue(i, otherLine.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Line makeLike: \"' + lineName + '\" not found.', 182)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Line.init()', -1)
        return 0

from dss.general.impl.LineSpacingImpl import (LineSpacingImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.general.impl.LineGeometryObjImpl import (LineGeometryObjImpl,)
from dss.general.impl.ConductorChoice import (ConductorChoice,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.LineGeometry import (LineGeometry,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
# from com.epri.dss.general.impl.LineGeometryObjImpl.LineGeometryProblem import (LineGeometryProblem,)


class LineGeometryImpl(DSSClassImpl, LineGeometry):
    activeLineGeometryObj = None

    def __init__(self):
        super(LineGeometryImpl, self)()
        self.className = 'LineGeometry'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = LineGeometry.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'nconds'
        self.propertyName[1] = 'nphases'
        self.propertyName[2] = 'cond'
        self.propertyName[3] = 'wire'
        self.propertyName[4] = 'x'
        self.propertyName[5] = 'h'
        self.propertyName[6] = 'units'
        self.propertyName[7] = 'normamps'
        self.propertyName[8] = 'emergamps'
        self.propertyName[9] = 'reduce'
        self.propertyName[10] = 'spacing'
        self.propertyName[11] = 'wires'
        self.propertyName[12] = 'cncable'
        self.propertyName[13] = 'tscable'
        self.propertyName[14] = 'cncables'
        self.propertyName[15] = 'tscables'
        self.propertyHelp[0] = 'Number of conductors in this geometry. Default is 3. Triggers memory allocations. Define first!'
        self.propertyHelp[1] = 'Number of phases. Default =3; All other conductors are considered neutrals and might be reduced out.'
        self.propertyHelp[2] = 'Set this = number of the conductor you wish to define. Default is 1.'
        self.propertyHelp[3] = 'Code from WireData. MUST BE PREVIOUSLY DEFINED. no default.' + CRLF + 'Specifies use of Overhead Line parameter calculation,' + CRLF + 'Unless Tape Shield cable previously assigned to phases, and this wire is a neutral.'
        self.propertyHelp[4] = 'x coordinate.'
        self.propertyHelp[5] = 'Height of conductor.'
        self.propertyHelp[6] = 'Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined'
        self.propertyHelp[7] = 'Normal ampacity, amperes for the line. Defaults to first conductor if not specified.'
        self.propertyHelp[8] = 'Emergency ampacity, amperes. Defaults to first conductor if not specified.'
        self.propertyHelp[9] = '{Yes | No} Default = no. Reduce to Nphases (Kron Reduction). Reduce out neutrals.'
        self.propertyHelp[10] = 'Reference to a LineSpacing for use in a line constants calculation.' + CRLF + 'Alternative to x, h, and units. MUST BE PREVIOUSLY DEFINED.' + CRLF + 'Must match \"nconds\" as previously defined for this geometry.' + CRLF + 'Must be used in conjunction with the Wires property.'
        self.propertyHelp[11] = 'Array of WireData names for use in a line constants calculation.' + CRLF + 'Alternative to individual wire inputs. ALL MUST BE PREVIOUSLY DEFINED.' + CRLF + 'Must match \"nconds\" as previously defined for this geometry,' + CRLF + 'unless TSData or CNData were previously assigned to phases, and these wires are neutrals.' + CRLF + 'Must be used in conjunction with the Spacing property.'
        self.propertyHelp[12] = 'Code from CNData. MUST BE PREVIOUSLY DEFINED. no default.' + CRLF + 'Specifies use of Concentric Neutral cable parameter calculation.'
        self.propertyHelp[13] = 'Code from TSData. MUST BE PREVIOUSLY DEFINED. no default.' + CRLF + 'Specifies use of Tape Shield cable parameter calculation.'
        self.propertyHelp[14] = 'Array of CNData names for cable parameter calculation.' + CRLF + 'All must be previously defined, and match \"nphases\" for this geometry.' + CRLF + 'You can later define \"nconds-nphases\" wires for bare neutral conductors.'
        self.propertyHelp[15] = 'Array of TSData names for cable parameter calculation.' + CRLF + 'All must be previously defined, and match \"nphases\" for this geometry.' + CRLF + 'You can later define \"nconds-nphases\" wires for bare neutral conductors.'
        self.activeProperty = LineGeometry.NumPropsThisClass - 1
        super(LineGeometryImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        """Create a new object of this class and add to list."""
        DSSGlobals.activeDSSObject = LineGeometryObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        alg = self.activeLineGeometryObj
        result = 0
        # continue parsing with contents of Parser
        self.activeLineGeometryObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = alg
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
                if paramPointer > 0 and paramPointer <= self.numProperties:
                    alg.setPropertyValue(paramPointer, param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Object \"' + self.getName() + '.' + alg.getName() + '\"', 10101)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        alg.setNConds(parser.makeInteger())
                        # use property value to force reallocations
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        alg.setNPhases(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        alg.setActiveCond(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        alg.getCondName()[alg.getActiveCond()] = param
                        if alg.getPhaseChoice() == ConductorChoice.UNKNOWN:
                            alg.changeLineConstantsType(ConductorChoice.OVERHEAD)
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        alg.getX()[alg.getActiveCond()] = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        alg.getY()[alg.getActiveCond()] = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 6):
                        _1 = True
                        alg.getUnits()[alg.getActiveCond()] = LineUnits.getUnitsCode(param)
                        alg.setLastUnit(alg.getUnits()[alg.getActiveCond()])
                        break
                    if (_1 is True) or (_0 == 7):
                        _1 = True
                        alg.setNormAmps(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 8):
                        _1 = True
                        alg.setEmergAmps(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 9):
                        _1 = True
                        alg.setReduce(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        alg.setSpacingType(parser.makeString())
                        if DSSGlobals.lineSpacingClass.setActive(alg.getSpacingType()):
                            LineSpacingImpl.activeLineSpacingObj = DSSGlobals.lineSpacingClass.getActiveObj()
                            if alg.getNConds() == LineSpacingImpl.activeLineSpacingObj.getNWires():
                                alg.setLastUnit(LineSpacingImpl.activeLineSpacingObj.getUnits())
                                _2 = True
                                i = 0
                                while True:
                                    if _2 is True:
                                        _2 = False
                                    else:
                                        i += 1
                                    if not (i < alg.getNConds()):
                                        break
                                    alg.getX()[i] = LineSpacingImpl.activeLineSpacingObj.getXCoord(i)
                                    alg.getY()[i] = LineSpacingImpl.activeLineSpacingObj.getYCoord(i)
                                    alg.getUnits()[i] = self.activeLineGeometryObj.getLastUnit()
                            else:
                                DSSGlobals.doSimpleMsg('LineSpacing object ' + alg.getSpacingType() + ' has the wrong number of wires.', 10103)
                        else:
                            DSSGlobals.doSimpleMsg('LineSpacing object ' + alg.getSpacingType() + ' has not been defined.', 10103)
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        alg.getCondName()[alg.getActiveCond()] = param
                        alg.changeLineConstantsType(ConductorChoice.CONCENTRIC_NEUTRAL)
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        alg.getCondName()[alg.getActiveCond()] = param
                        alg.changeLineConstantsType(ConductorChoice.TAPE_SHIELD)
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        istart = 0
                        istop = alg.getNConds() - 1
                        # TODO Check zero based indexing
                        if alg.getPhaseChoice() == ConductorChoice.UNKNOWN:
                            alg.changeLineConstantsType(ConductorChoice.OVERHEAD)
                        else:
                            # these are buried neutral wires
                            istart = alg.getNPhases()
                            # TODO Check zero based indexing
                        DSSGlobals.auxParser.setCmdString(parser.makeString())
                        _3 = True
                        i = istart
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < istop + 1):
                                break
                            DSSGlobals.auxParser.getNextParam()
                            # ignore any parameter name  not expecting any
                            alg.getCondName()[i] = DSSGlobals.auxParser.makeString()
                            DSSGlobals.wireDataClass.setCode(alg.getCondName()[i])
                            if ConductorDataImpl.activeConductorDataObj is not None:
                                alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj
                                if i == 0:
                                    if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                        alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                    if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                        alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                            else:
                                DSSGlobals.doSimpleMsg('WireData object \"' + alg.getCondName()[i] + '\" not defined. Must be previously defined.', 10103)
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        istart = 0
                        istop = alg.getNConds() - 1
                        # TODO Check zero based indexing
                        alg.changeLineConstantsType(ConductorChoice.CONCENTRIC_NEUTRAL)
                        istop = alg.getNPhases() - 1
                        # TODO Check zero based indexing
                        DSSGlobals.auxParser.setCmdString(parser.makeString())
                        _4 = True
                        i = istart
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < istop + 1):
                                break
                            DSSGlobals.auxParser.getNextParam()
                            # ignore any parameter name not expecting any
                            alg.getCondName()[i] = DSSGlobals.auxParser.makeString()
                            DSSGlobals.CNDataClass.setCode(alg.getCondName()[i])
                            if ConductorDataImpl.activeConductorDataObj is not None:
                                alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj
                                if i == 0:
                                    if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                        alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                    if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                        alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                            else:
                                DSSGlobals.doSimpleMsg('CNData object \"' + alg.getCondName()[i] + '\" not defined. Must be previously defined.', 10103)
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        istart = 0
                        istop = alg.getNConds() - 1
                        # TODO Check zero based indexing
                        alg.changeLineConstantsType(ConductorChoice.TAPE_SHIELD)
                        istop = alg.getNPhases()
                        # TODO Check zero based indexing
                        DSSGlobals.auxParser.setCmdString(parser.makeString())
                        _5 = True
                        i = istart
                        while True:
                            if _5 is True:
                                _5 = False
                            else:
                                i += 1
                            if not (i < istop + 1):
                                break
                            DSSGlobals.auxParser.getNextParam()
                            # ignore any parameter name  not expecting any
                            alg.getCondName()[i] = DSSGlobals.auxParser.makeString()
                            DSSGlobals.TSDataClass.setCode(alg.getCondName()[i])
                            if ConductorDataImpl.activeConductorDataObj is not None:
                                alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj
                                if i == 0:
                                    if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                        alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                    if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                        alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                            else:
                                DSSGlobals.doSimpleMsg('TSData object \"' + alg.getCondName()[i] + '\" not defined. Must be previously defined.', 10103)
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeLineGeometryObj, paramPointer - LineGeometry.NumPropsThisClass)
                        break
                    break
                # Set defaults
                _6 = paramPointer
                _7 = False
                while True:
                    if _6 == 1:
                        _7 = True
                        if alg.getNPhases() > alg.getNConds():
                            alg.setNPhases(alg.getNConds())
                        break
                    if (_7 is True) or (_6 == 2):
                        _7 = True
                        if (alg.getActiveCond() < 1) or (alg.getActiveCond() > alg.getNConds()):
                            DSSGlobals.doSimpleMsg('Illegal cond= specification in line geometry:' + DSSGlobals.CRLF + parser.getCmdString(), 10102)
                        break
                    if (_7 is True) or (_6 == 3):
                        _7 = True
                        DSSGlobals.wireDataClass.setCode(param)
                        if ConductorDataImpl.activeConductorDataObj is not None:
                            alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj
                            # Default the current ratings for this geometry to the rating of the first conductor
                            if alg.getActiveCond() == 1:
                                # TODO Check zero based indexing
                                if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                    alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                    alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                        else:
                            DSSGlobals.doSimpleMsg('WireData object \"' + param + '\" not defined. Must be previously defined.', 10103)
                        break
                    if (_7 is True) or (_6 == 12):
                        _7 = True
                        DSSGlobals.CNDataClass.setCode(param)
                        if ConductorDataImpl.activeConductorDataObj is not None:
                            alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj
                            # Default the current ratings for this geometry to the rating of the first conductor
                            if alg.getActiveCond() == 1:
                                # TODO Check zero based indexing
                                if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                    alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                    alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                        else:
                            DSSGlobals.doSimpleMsg('CNData object \"' + param + '\" not defined. Must be previously defined.', 10103)
                        break
                    if (_7 is True) or (_6 == 13):
                        _7 = True
                        DSSGlobals.TSDataClass.setCode(param)
                        if ConductorDataImpl.activeConductorDataObj is not None:
                            alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj
                            # Default the current ratings for this geometry to the rating of the first conductor
                            if alg.getActiveCond() == 1:
                                # TODO Check zero based indexing
                                if ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0:
                                    alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps())
                                if ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0:
                                    alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps())
                        else:
                            DSSGlobals.doSimpleMsg('TSData object \"' + param + '\" not defined. Must be previously defined.', 10103)
                        break
                    break
                _8 = paramPointer
                _9 = False
                while True:
                    if _8 == 0:
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 3):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 4):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 5):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 6):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 10):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 11):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 12):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 13):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 14):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    if (_9 is True) or (_8 == 15):
                        _9 = True
                        alg.setDataChanged(True)
                        break
                    break
                paramName = parser.getNextParam()
                param = parser.makeString()
        return result

    def makeLike(self, lineName):
        result = 0
        # See if we can find this line code in the present collection
        otherLineGeometry = self.find(lineName)
        if otherLineGeometry is not None:
            alg = self.activeLineGeometryObj
            alg.setPhaseChoice(otherLineGeometry.getPhaseChoice())
            alg.setNConds(otherLineGeometry.getNWires())
            # allocates
            alg.setNPhases(otherLineGeometry.getNPhases())
            alg.setSpacingType(otherLineGeometry.getSpacingType())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < alg.getNConds()):
                    break
                alg.getCondName()[i] = otherLineGeometry.getCondName()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < alg.getNConds()):
                    break
                alg.getConductorData()[i] = otherLineGeometry.getConductorData()[i]
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < alg.getNConds()):
                    break
                alg.getX()[i] = otherLineGeometry.getX()[i]
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < alg.getNConds()):
                    break
                alg.getY()[i] = otherLineGeometry.getY()[i]
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < alg.getNConds()):
                    break
                alg.getUnits()[i] = otherLineGeometry.getUnits()[i]
            alg.setDataChanged(True)
            alg.setNormAmps(otherLineGeometry.getNormAmps())
            alg.setEmergAmps(otherLineGeometry.getEmergAmps())
            # TODO Auto-generated catch block
            try:
                alg.updateLineGeometryData(DSSGlobals.activeCircuit.getSolution().getFrequency())
            except LineGeometryProblem, e:
                e.printStackTrace()
            _5 = True
            i = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    i += 1
                if not (i < alg.getParentClass().getNumProperties()):
                    break
                alg.setPropertyValue(i, otherLineGeometry.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in LineGeometry makeLike: \"' + lineName + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement LineGeometry.init()', -1)
        return 0

    def getCode(self):
        active = self.elementList.getActive()
        return active.getName()

    def setCode(self, value):
        self.activeLineGeometryObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pLineGeo = self.elementList.get(i)
            if pLineGeo.getName().equalsIgnoreCase(value):
                self.activeLineGeometryObj = pLineGeo
                return
        DSSGlobals.doSimpleMsg('LineGeometry: \"' + value + '\" not found.', 103)

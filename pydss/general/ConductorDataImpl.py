from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.ConductorData import (ConductorData,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)


class ConductorDataImpl(DSSClassImpl, ConductorData):
    activeConductorDataObj = None
    conductorChoiceArray = [None] * 100
    numConductorClassProps = None

    def __init__(self):
        super(ConductorDataImpl, self)()
        self.numConductorClassProps = 10
        self.classType = DSSClassDefs.DSS_OBJECT

    def countProperties(self):
        self.numProperties = self.numProperties + self.getNumConductorClassProps()
        super(ConductorDataImpl, self).countProperties()

    def defineProperties(self):
        self.propertyName[self.activeProperty + 1] = 'Rdc'
        self.propertyName[self.activeProperty + 2] = 'Rac'
        self.propertyName[self.activeProperty + 3] = 'Runits'
        self.propertyName[self.activeProperty + 4] = 'GMRac'
        self.propertyName[self.activeProperty + 5] = 'GMRunits'
        self.propertyName[self.activeProperty + 6] = 'radius'
        self.propertyName[self.activeProperty + 7] = 'radunits'
        self.propertyName[self.activeProperty + 8] = 'normamps'
        self.propertyName[self.activeProperty + 9] = 'emergamps'
        self.propertyName[self.activeProperty + 10] = 'diam'
        self.propertyHelp[self.activeProperty + 1] = 'dc Resistance, ohms per unit length (see Runits). Defaults to Rac/1.02 if not specified.'
        self.propertyHelp[self.activeProperty + 2] = 'Resistance at 60 Hz per unit length. Defaults to 1.02*Rdc if not specified.'
        self.propertyHelp[self.activeProperty + 3] = 'Length units for resistance: ohms per ' + self.LineUnitsHelp
        self.propertyHelp[self.activeProperty + 4] = 'GMR at 60 Hz. Defaults to .7788*radius if not specified.'
        self.propertyHelp[self.activeProperty + 5] = 'Units for GMR: ' + self.LineUnitsHelp
        self.propertyHelp[self.activeProperty + 6] = 'Outside radius of conductor. Defaults to GMR/0.7788 if not specified.'
        self.propertyHelp[self.activeProperty + 7] = 'Units for outside radius: ' + self.LineUnitsHelp
        self.propertyHelp[self.activeProperty + 8] = 'Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not specified.'
        self.propertyHelp[self.activeProperty + 9] = 'Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not specified.'
        self.propertyHelp[self.activeProperty + 10] = 'Diameter; Alternative method for entering radius.'
        self.activeProperty = self.activeProperty + self.numConductorClassProps
        super(ConductorDataImpl, self).defineProperties()

    def classEdit(self, activeObj, paramPointer):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        if paramPointer >= 0:
            cd = activeObj
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    cd.setRDC(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    cd.setR60(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    cd.setResistanceUnits(LineUnits.getUnitsCode(parser.makeString()))
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    cd.setGMR60(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    cd.setGMRUnits(LineUnits.getUnitsCode(parser.makeString()))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    cd.setRadius(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    cd.setRadiusUnits(LineUnits.getUnitsCode(parser.makeString()))
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    cd.setNormAmps(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    cd.setEmergAmps(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    cd.setRadius(parser.makeDouble() / 2.0)
                    break
                if True:
                    _1 = True
                    super(ConductorDataImpl, self).classEdit(activeObj, paramPointer - self.numConductorClassProps)
                    break
                break
            # Set defaults
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    if cd.getR60() < 0.0:
                        cd.setR60(1.02 * cd.getRDC())
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if cd.getRDC() < 0.0:
                        cd.setRDC(cd.getR60() / 1.02)
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    if cd.getRadius() < 0.0:
                        cd.setRadius(cd.getGMR60() / 0.7788)
                    break
                if (_3 is True) or (_2 == 4):
                    _3 = True
                    if cd.getRadiusUnits() == 0:
                        cd.setRadiusUnits(cd.getGMRUnits())
                    break
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    if cd.getGMR60() < 0.0:
                        cd.setGMR60(0.7788 * cd.getRadius())
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    if cd.getGMRUnits() == 0:
                        cd.setGMRUnits(cd.getRadiusUnits())
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    if cd.getEmergAmps() < 0.0:
                        cd.setEmergAmps(1.5 * cd.getNormAmps())
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    if cd.getNormAmps() < 0.0:
                        cd.setNormAmps(cd.getEmergAmps() / 1.5)
                    break
                if (_3 is True) or (_2 == 9):
                    _3 = True
                    if cd.getGMR60() < 0.0:
                        cd.setGMR60(0.7788 * cd.getRadius())
                    break
                break
            # Check for critical errors
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 3:
                    _5 = True
                    if cd.getRadius() == 0.0:
                        DSSGlobals.doSimpleMsg('Error: Radius is specified as zero for ConductorData.' + cd.getName(), 999)
                    break
                if (_5 is True) or (_4 == 5):
                    _5 = True
                    if cd.getGMR60() == 0.0:
                        DSSGlobals.doSimpleMsg('Error: GMR is specified as zero for ConductorData.' + cd.getName(), 999)
                    break
                break
        return result

    def classMakeLike(self, otherObj):
        otherConductorData = otherObj
        cd = DSSGlobals.activeDSSObject
        cd.setRDC(otherConductorData.getRDC())
        cd.setR60(otherConductorData.getR60())
        cd.setResistanceUnits(otherConductorData.getResistanceUnits())
        cd.setGMR60(otherConductorData.getGMR60())
        cd.setGMRUnits(otherConductorData.getGMRUnits())
        cd.setRadius(otherConductorData.getRadius())
        cd.setRadiusUnits(otherConductorData.getRadiusUnits())
        cd.setNormAmps(otherConductorData.getNormAmps())
        cd.setEmergAmps(otherConductorData.getEmergAmps())
        # super.classMakeLike(OtherObj);

    def setNumConductorClassProps(self, num):
        self.numConductorClassProps = num

    def getNumConductorClassProps(self):
        return self.numConductorClassProps

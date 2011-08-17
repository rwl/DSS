from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.CableData import (CableData,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class CableDataImpl(ConductorDataImpl, CableData):
    numCableClassProps = None

    def __init__(self):
        self.numCableClassProps = 4
        self.classType = DSSClassDefs.DSS_OBJECT

    def countProperties(self):
        self.numProperties = self.numProperties + self.numCableClassProps
        super(CableDataImpl, self).countProperties()

    def defineProperties(self):
        self.propertyName[self.activeProperty + 1] = 'EpsR'
        self.propertyName[self.activeProperty + 2] = 'InsLayer'
        self.propertyName[self.activeProperty + 3] = 'DiaIns'
        self.propertyName[self.activeProperty + 4] = 'DiaCable'
        self.propertyHelp[self.activeProperty + 1] = 'Insulation layer relative permittivity; default is 2.3.'
        self.propertyHelp[self.activeProperty + 2] = 'Insulation layer thickness; same units as radius; no default. ' + 'With DiaIns, establishes inner radius for capacitance calculation.'
        self.propertyHelp[self.activeProperty + 3] = 'Diameter over insulation layer; same units as radius; no default.' + 'Establishes outer radius for capacitance calculation.'
        self.propertyHelp[self.activeProperty + 4] = 'Diameter over cable; same units as radius; no default.'
        self.activeProperty = self.activeProperty + self.numCableClassProps
        super(CableDataImpl, self).defineProperties()

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
                    cd.setEpsR(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    cd.setInsLayer(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    cd.setDiaIns(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    cd.setDiaCable(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    super(CableDataImpl, self).classEdit(activeObj, paramPointer - self.numCableClassProps)
                    break
                break
            # Check for critical errors
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    if cd.getEpsR() < 1.0:
                        DSSGlobals.doSimpleMsg('Error: Insulation permittivity must be greater than one for CableData ' + cd.getName(), 999)
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if cd.getInsLayer() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Insulation layer thickness must be positive for CableData ' + cd.getName(), 999)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    if cd.getDiaIns() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Diameter over insulation layer must be positive for CableData ' + cd.getName(), 999)
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    if cd.getDiaCable() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Diameter over cable must be positive for CableData ' + cd.getName(), 999)
                    break
                break
        return result

    def classMakeLike(self, otherObj):
        otherCableData = otherObj
        cd = DSSGlobals.activeDSSObject
        cd.setEpsR(otherCableData.getEpsR())
        cd.setInsLayer(otherCableData.getInsLayer())
        cd.setDiaIns(otherCableData.getDiaIns())
        cd.setDiaCable(otherCableData.getDiaCable())
        super(CableDataImpl, self).classMakeLike(otherObj)

    def setNumCableClassProps(self, num):
        self.numCableClassProps = num

    def getNumCableClassProps(self):
        return self.numCableClassProps

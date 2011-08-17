from dss.general.impl.ConductorDataObjImpl import (ConductorDataObjImpl,)
from dss.general.CableDataObj import (CableDataObj,)


class CableDataObjImpl(ConductorDataObjImpl, CableDataObj):
    epsR = None
    # next 3 use parent radiusUnits
    insLayer = None
    diaIns = None
    diaCable = None

    def __init__(self, parClass, cableDataName):
        super(CableDataObjImpl, self)(parClass, cableDataName)
        self.setName(cableDataName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.epsR = 2.3
        self.insLayer = -1.0
        self.diaIns = -1.0
        self.diaCable = -1.0

    def dumpProperties(self, f, complete):
        super(CableDataObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            f.print_('~ ' + self.getParentClass().getPropertyName()[i] + '=')
            _1 = i
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    print String.format.format('%.3g', self.epsR)
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print String.format.format('%.6g', self.insLayer)
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    print String.format.format('%.6g', self.diaIns)
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print String.format.format('%.6g', self.diaCable)
                    break
                break

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(arrayOffset + 0, '2.3')
        # TODO Check zero based indexing
        self.setPropertyValue(arrayOffset + 1, '-1')
        self.setPropertyValue(arrayOffset + 2, '-1')
        self.setPropertyValue(arrayOffset + 3, '-1')
        super(CableDataObjImpl, self).initPropertyValues(arrayOffset + 4)

    def getEpsR(self):
        return self.epsR

    def getInsLayer(self):
        return self.insLayer

    def getDiaIns(self):
        return self.diaIns

    def getDiaCable(self):
        # FIXME Private members in OpenDSS.
        return self.diaCable

    def setEpsR(self, epsr):
        self.epsR = epsr

    def setInsLayer(self, inslayer):
        self.insLayer = inslayer

    def setDiaIns(self, diains):
        self.diaIns = diains

    def setDiaCable(self, diacable):
        self.diaCable = diacable

from dss.general.CNDataObj import (CNDataObj,)
from dss.general.impl.CableDataObjImpl import (CableDataObjImpl,)
from dss.general.CNData import (CNData,)


class CNDataObjImpl(CableDataObjImpl, CNDataObj):
    kStrand = None
    diaStrand = None
    gmrStrand = None
    rStrand = None

    def __init__(self, parClass, CNDataName):
        super(CNDataObjImpl, self)(parClass, CNDataName)
        self.setName(CNDataName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.kStrand = 2
        self.diaStrand = -1.0
        self.gmrStrand = -1.0
        self.rStrand = -1.0
        self.initPropertyValues(0)

    def dumpProperties(self, f, complete):
        super(CNDataObjImpl, self).dumpProperties(f, complete)
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
                    print String.format.format('%d', self.kStrand)
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print String.format.format('%.6g', self.diaStrand)
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    print String.format.format('%.6g', self.gmrStrand)
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print String.format.format('%.6g', self.rStrand)
                    break
                break

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '2')
        self.setPropertyValue(1, '-1')
        self.setPropertyValue(2, '-1')
        self.setPropertyValue(3, '-1')
        super(CNDataObjImpl, self).initPropertyValues(arrayOffset + CNData.NumPropsThisClass)

    def getkStrand(self):
        return self.kStrand

    def getDiaStrand(self):
        return self.diaStrand

    def getGmrStrand(self):
        return self.gmrStrand

    def getRStrand(self):
        # FIXME: Private members in OpenDSS
        return self.rStrand

    def setkStrand(self, kstrand):
        self.kStrand = kstrand

    def setDiaStrand(self, diastrand):
        self.diaStrand = diastrand

    def setGmrStrand(self, gmrstrand):
        self.gmrStrand = gmrstrand

    def setRStrand(self, rstrand):
        self.rStrand = rstrand

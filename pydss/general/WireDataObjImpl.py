from dss.general.WireData import (WireData,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.WireDataObj import (WireDataObj,)
from dss.general.impl.ConductorDataObjImpl import (ConductorDataObjImpl,)


class WireDataObjImpl(ConductorDataObjImpl, WireDataObj):

    def __init__(self, parClass, wireDataName):
        super(WireDataObjImpl, self)(parClass, wireDataName)

    def dumpProperties(self, f, complete):
        super(WireDataObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            f.print_('~ ' + self.parentClass.getPropertyName()[i] + '=')
            _1 = i
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    print String.format.format('%.6g', self.getRDC())
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print String.format.format('%.6g', self.getR60())
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    print String.format.format('%s', LineUnits.lineUnitsStr(self.getResistanceUnits()))
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print String.format.format('%.6g', self.getGMR60())
                    break
                if (_2 is True) or (_1 == 4):
                    _2 = True
                    print String.format.format('%s', LineUnits.lineUnitsStr(self.getGMRUnits()))
                    break
                if (_2 is True) or (_1 == 5):
                    _2 = True
                    print String.format.format('%.6g', self.getRadius())
                    break
                if (_2 is True) or (_1 == 6):
                    _2 = True
                    print String.format.format('%s', LineUnits.lineUnitsStr(self.getRadiusUnits()))
                    break
                if (_2 is True) or (_1 == 7):
                    _2 = True
                    print String.format.format('%.6g', self.getNormAmps())
                    break
                if (_2 is True) or (_1 == 8):
                    _2 = True
                    print String.format.format('%.6g', self.getEmergAmps())
                    break
                if (_2 is True) or (_1 == 9):
                    _2 = True
                    print String.format.format('%.6g', self.getRadius() * 2.0)
                    break
                break

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[1] = '-1'
        self.propertyValue[2] = '-1'
        self.propertyValue[3] = 'none'
        self.propertyValue[4] = '-1'
        self.propertyValue[5] = 'none'
        self.propertyValue[6] = '-1'
        self.propertyValue[7] = 'none'
        self.propertyValue[8] = '-1'
        self.propertyValue[9] = '-1'
        self.propertyValue[10] = '-1'
        super(WireDataObjImpl, self).initPropertyValues(WireData.NumPropsThisClass)

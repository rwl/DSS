from dss.general.TSDataObj import (TSDataObj,)
from dss.general.impl.CableDataObjImpl import (CableDataObjImpl,)
from dss.general.TSData import (TSData,)


class TSDataObjImpl(CableDataObjImpl, TSDataObj):
    diaShield = None
    tapeLayer = None
    tapeLap = None

    def __init__(self, parClass, TSDataName):
        super(TSDataObjImpl, self)(parClass, TSDataName)
        self.setName(TSDataName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.diaShield = -1.0
        self.tapeLayer = -1.0
        self.tapeLap = 20.0
        self.initPropertyValues(0)

    def dumpProperties(self, f, complete):
        super(TSDataObjImpl, self).dumpProperties(f, complete)
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
                    print String.format.format('%.6g', self.diaShield)
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print String.format.format('%.6g', self.tapeLayer)
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    print String.format.format('%.2g', self.tapeLap)
                    break
                break

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '-1')
        self.setPropertyValue(1, '-1')
        self.setPropertyValue(2, '20.0')
        super(TSDataObjImpl, self).initPropertyValues(arrayOffset + TSData.NumPropsThisClass)

    def getDiaShield(self):
        return self.diaShield

    def getTapeLayer(self):
        return self.tapeLayer

    def getTapeLap(self):
        # FIXME Private members in OpenDSS
        return self.tapeLap

    def setDiaShield(self, shield):
        self.diaShield = shield

    def setTapeLayer(self, layer):
        self.tapeLayer = layer

    def setTapeLap(self, lap):
        self.tapeLap = lap

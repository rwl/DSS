from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.ConductorDataObj import (ConductorDataObj,)


class ConductorDataObjImpl(DSSObjectImpl, ConductorDataObj):
    Rdc = None
    R60 = None
    gmr60 = None
    radius = None
    gmrUnits = None
    resistanceUnits = None
    radiusUnits = None
    normAmps = None
    emergAmps = None

    def __init__(self, parClass, conductorDataName):
        super(ConductorDataObjImpl, self)(parClass)
        self.setName(conductorDataName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.Rdc = -1.0
        self.R60 = -1.0
        self.gmr60 = -1.0
        self.radius = -1.0
        self.gmrUnits = 0
        self.resistanceUnits = 0
        self.radiusUnits = 0
        self.normAmps = -1.0
        self.emergAmps = -1.0

    def dumpProperties(self, f, complete):
        super(ConductorDataObjImpl, self).dumpProperties(f, complete)
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
        self.setPropertyValue(arrayOffset + 0, '-1')
        self.setPropertyValue(arrayOffset + 1, '-1')
        self.setPropertyValue(arrayOffset + 2, 'none')
        self.setPropertyValue(arrayOffset + 3, '-1')
        self.setPropertyValue(arrayOffset + 4, 'none')
        self.setPropertyValue(arrayOffset + 5, '-1')
        self.setPropertyValue(arrayOffset + 6, 'none')
        self.setPropertyValue(arrayOffset + 7, '-1')
        self.setPropertyValue(arrayOffset + 8, '-1')
        self.setPropertyValue(arrayOffset + 9, '-1')
        super(ConductorDataObjImpl, self).initPropertyValues(arrayOffset + 10)

    def getNormAmps(self):
        return self.normAmps

    def setNormAmps(self, amps):
        self.normAmps = amps

    def getEmergAmps(self):
        return self.emergAmps

    def setEmergAmps(self, amps):
        self.emergAmps = amps

    def getRDC(self):
        return self.Rdc

    def getR60(self):
        return self.R60

    def getGMR60(self):
        return self.gmr60

    def getRadius(self):
        return self.radius

    def getGMRUnits(self):
        return self.gmrUnits

    def getResistanceUnits(self):
        return self.resistanceUnits

    def getRadiusUnits(self):
        # FIXME Private members in OpenDSS.
        return self.radiusUnits

    def setRDC(self, rdc):
        self.Rdc = rdc

    def setR60(self, r60):
        self.R60 = r60

    def setGMR60(self, gmr):
        self.gmr60 = gmr

    def setRadius(self, value):
        self.radius = value

    def setGMRUnits(self, units):
        self.gmrUnits = units

    def setResistanceUnits(self, units):
        self.resistanceUnits = units

    def setRadiusUnits(self, units):
        self.radiusUnits = units

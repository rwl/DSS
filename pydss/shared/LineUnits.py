

class LineUnits(object):
    UNITS_MAXNUM = 9
    UNITS_NONE = 0
    UNITS_MILES = 1
    UNITS_KFT = 2
    UNITS_KM = 3
    UNITS_M = 4
    UNITS_FT = 5
    UNITS_IN = 6
    UNITS_CM = 7
    UNITS_MM = 8

    @classmethod
    def getUnitsCode(cls, s):
        sTest = s[:2]
        # copy first 2 chars for most of the test  TODO Check indexing
        if sTest.equalsIgnoreCase('no'):
            return cls.UNITS_NONE
            # no units specified
        elif sTest.equalsIgnoreCase('mi'):
            return cls.UNITS_MILES
            # per mile
        elif sTest.equalsIgnoreCase('kf'):
            return cls.UNITS_KFT
            # per 1000 ft (kft)
        elif sTest.equalsIgnoreCase('km'):
            return cls.UNITS_KM
            # per km
        elif sTest.equalsIgnoreCase('m'):
            return cls.UNITS_M
            # per meter
        elif sTest.equalsIgnoreCase('me'):
            return cls.UNITS_M
            # per meter
        elif sTest.equalsIgnoreCase('ft'):
            return cls.UNITS_FT
        elif sTest.equalsIgnoreCase('in'):
            return cls.UNITS_IN
        elif sTest.equalsIgnoreCase('cm'):
            return cls.UNITS_CM
        elif sTest.equalsIgnoreCase('mm'):
            return cls.UNITS_MM
        return 0

    @classmethod
    def lineUnitsStr(cls, units):
        # Conversion to and from meters and per meter
        _0 = units
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return 'none'
            if (_1 is True) or (_0 == cls.UNITS_MILES):
                _1 = True
                return 'mi'
            if (_1 is True) or (_0 == cls.UNITS_KFT):
                _1 = True
                return 'kft'
            if (_1 is True) or (_0 == cls.UNITS_KM):
                _1 = True
                return 'km'
            if (_1 is True) or (_0 == cls.UNITS_M):
                _1 = True
                return 'm'
            if (_1 is True) or (_0 == cls.UNITS_FT):
                _1 = True
                return 'ft'
            if (_1 is True) or (_0 == cls.UNITS_IN):
                _1 = True
                return 'in'
            if (_1 is True) or (_0 == cls.UNITS_CM):
                _1 = True
                return 'cm'
            if (_1 is True) or (_0 == cls.UNITS_MM):
                _1 = True
                return 'mm'
            if True:
                _1 = True
                return 'none'
            break

    @classmethod
    def toMeters(cls, units):
        _0 = units
        _1 = False
        while True:
            if _0 == cls.UNITS_MILES:
                _1 = True
                return 1609.3
            if (_1 is True) or (_0 == cls.UNITS_KFT):
                _1 = True
                return 304.8
            if (_1 is True) or (_0 == cls.UNITS_KM):
                _1 = True
                return 1000.0
            if (_1 is True) or (_0 == cls.UNITS_M):
                _1 = True
                return 1.0
            if (_1 is True) or (_0 == cls.UNITS_FT):
                _1 = True
                return 0.3048
            if (_1 is True) or (_0 == cls.UNITS_IN):
                _1 = True
                return 0.0254
            if (_1 is True) or (_0 == cls.UNITS_CM):
                _1 = True
                return 0.01
            if (_1 is True) or (_0 == cls.UNITS_MM):
                _1 = True
                return 0.001
            if True:
                _1 = True
                return 1.0
            break

    @classmethod
    def toPerMeter(cls, units):
        return 1.0 / cls.toMeters(units)

    @classmethod
    def fromPerMeter(cls, units):
        return cls.toMeters(units)

    @classmethod
    def fromMeters(cls, units):
        return 1.0 / cls.toMeters(units)

    @classmethod
    def convertLineUnits(cls, fromUnits, toUnits):
        if (fromUnits == cls.UNITS_NONE) or (toUnits == cls.UNITS_NONE):
            return 1.0
            # Don't know what to convert
        else:
            return cls.fromMeters(toUnits) * cls.toMeters(fromUnits)

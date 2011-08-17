

class ConductorChoice(object):
    OVERHEAD = 'OVERHEAD'
    CONCENTRIC_NEUTRAL = 'CONCENTRIC_NEUTRAL'
    TAPE_SHIELD = 'TAPE_SHIELD'
    UNKNOWN = 'UNKNOWN'
    _values = [OVERHEAD, CONCENTRIC_NEUTRAL, TAPE_SHIELD, UNKNOWN]

    @classmethod
    def values(cls):
        return cls._enum_values[:]



class ControlAction(object):
    NONE = [1]
    OPEN = [2]
    CLOSE = [3]
    CTRL_RESET = [4]
    LOCK = [5]
    UNLOCK = [6]
    TAPUP = [7]
    TAPDOWN = [8]
    code = None

    def __init__(self, code):
        self.code = code

    def code(self):
        return self.code

    _values = [NONE, OPEN, CLOSE, CTRL_RESET, LOCK, UNLOCK, TAPUP, TAPDOWN]

    @classmethod
    def values(cls):
        return cls._enum_values[:]


ControlAction._enum_values = [ControlAction(*v) for v in ControlAction._enum_values]

from __pyjamas__ import (ARGERROR,)
from dss.conversion.StoreUserModel import (StoreUserModel,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class StoreUserModelImpl(object, StoreUserModel):
    handle = None
    # handle to DLL containing user model
    id = None
    # id of this instance of the user model
    name = None
    # name of the DLL file containing user model
    funcError = None

    def __init__(self):
        # TODO Auto-generated constructor stub
        pass

    def makeNew(self, genVars, callBacks):
        """Make a new instance."""
        return 0

    def delete(self, x):
        """Deletes specified instance."""
        pass

    def select(self, x):
        """Select active instance."""
        return 0

    def getName(self):
        return self.name

    def setName(self, Value):
        pass

    def checkFuncError(self, addr, funcName):
        return None

    def edit(self, *args):
        """None
        ---
        Send string to user model to handle.
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            value, = _0
        elif _1 == 2:
            s, maxlen = _0
        else:
            raise ARGERROR(1, 2)

    def exists(self):
        return False

    def init(self, V, I):
        """For dynamics."""
        pass

    def calc(self, V, I):
        """Returns currents or sets pShaft."""
        pass

    def integrate(self):
        """Integrates any state vars."""
        pass

    def updateModel(self):
        """Called when props of generator updated."""
        pass

    def save(self):
        pass

    def restore(self):
        # Monitoring functions
        pass

    def numVars(self):
        return 0

    def getAllVars(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 1:
            vars, = _0
            var, = _0
        else:
            raise ARGERROR(1, 1)

    def getVariable(self, i):
        return 0.0

    def setVariable(self, i, value):
        pass

    def getVarName(self, varNum, varName, maxlen):
        pass

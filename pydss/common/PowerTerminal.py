from dss.common.Terminal import (Terminal,)
from dss.common.impl.ConductorImpl import (ConductorImpl,)


class PowerTerminal(object, Terminal):
    numCond = None
    activeConductor = None
    busRef = None
    termNodeRef = None
    conductors = None
    checked = None

    def __init__(self, nCond):
        super(PowerTerminal, self)()
        self.numCond = nCond
        self.busRef = -1
        # signify not set
        self.termNodeRef = [None] * self.numCond
        self.conductors = [None] * self.numCond
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numCond):
                break
            self.conductors[i] = ConductorImpl()
        self.activeConductor = 0
        # TODO Check zero based indexing

    def getBusRef(self):
        return self.busRef

    def setBusRef(self, value):
        self.busRef = value

    def getTermNodeRef(self):
        return self.termNodeRef

    def setTermNodeRef(self, value):
        self.termNodeRef = value

    def getConductors(self):
        return self.conductors

    def setConductors(self, value):
        self.conductors = value

    def isChecked(self):
        return self.checked

    def setChecked(self, value):
        self.checked = value

    def setActiveConductor(self, value):
        if (value >= 0) & (value < self.numCond):
            # TODO Check zero based indexing
            self.activeConductor = value

    def getActiveConductor(self):
        return self.activeConductor

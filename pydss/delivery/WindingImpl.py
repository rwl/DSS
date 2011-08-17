from dss.delivery.Winding import (Winding,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)


class WindingImpl(object, Winding):
    connection = None
    kVLL = None
    VBase = None
    kVA = None
    puTap = None
    Rpu = None
    RNeut = None
    XNeut = None
    # on transformer MVABase (1st winding)
    Y_PPM = None
    # Anti float reactance adder
    # Tap changer data
    tapIncrement = None
    minTap = None
    maxTap = None
    numTaps = None

    def __init__(self):
        super(WindingImpl, self)()
        self.connection = 0
        self.kVLL = 12.47
        self.VBase = (self.kVLL / DSSGlobals.SQRT3) * 1000.0
        self.kVA = 1000.0
        self.puTap = 1.0
        self.Rpu = 0.002
        self.RNeut = -1.0
        # default to open - make user specify connection
        self.XNeut = 0.0
        self.computeAntiFloatAdder(1e-06, self.kVA / 3.0 / 1000.0)
        # 1 PPM
        self.tapIncrement = 0.00625
        self.numTaps = 32
        self.maxTap = 1.1
        self.minTap = 0.9

    def computeAntiFloatAdder(self, PPM_Factor, VABase1ph):
        self.Y_PPM = -PPM_Factor / self.Math.pow(self.VBase, 2) / VABase1ph

    def getConnection(self):
        return self.connection

    def setConnection(self, conn):
        self.connection = conn

    def getKVLL(self):
        return self.kVLL

    def setKVLL(self, kvll):
        self.kVLL = kvll

    def getVBase(self):
        return self.VBase

    def setVBase(self, base):
        self.VBase = base

    def getKVA(self):
        return self.kVA

    def setKVA(self, kva):
        self.kVA = kva

    def getPUTap(self):
        return self.puTap

    def setPUTap(self, tap):
        self.puTap = tap

    def getRpu(self):
        return self.Rpu

    def setRpu(self, rpu):
        self.Rpu = rpu

    def getRNeut(self):
        return self.RNeut

    def setRNeut(self, rneut):
        self.RNeut = rneut

    def getXNeut(self):
        return self.XNeut

    def setXNeut(self, xneut):
        self.XNeut = xneut

    def getY_PPM(self):
        return self.Y_PPM

    def setY_PPM(self, y):
        self.Y_PPM = y

    def getTapIncrement(self):
        return self.tapIncrement

    def setTapIncrement(self, increment):
        self.tapIncrement = increment

    def getMinTap(self):
        return self.minTap

    def setMinTap(self, min):
        self.minTap = min

    def getMaxTap(self):
        return self.maxTap

    def setMaxTap(self, max):
        self.maxTap = max

    def getNumTaps(self):
        return self.numTaps

    def setNumTaps(self, num):
        self.numTaps = num

from dss.common.Bus import (Bus,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.impl.NamedObjectImpl import (NamedObjectImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class DSSBus(NamedObjectImpl, Bus):

    class NodeBus(object):
        # Ref to bus in circuit's bus list
        busRef = None
        nodeNum = None

    numNodesThisBus = None
    nodes = None
    allocation = None
    refNo = None
    VBus = None
    busCurrent = None
    Zsc = None
    Ysc = None
    # Coordinates
    x = None
    y = None
    kVBase = None
    # Base kV for each node to ground (0)
    distFromMeter = None
    coordDefined = None
    busChecked = None
    keep = None
    isRadialBus = None

    def __init__(self):
        super(DSSBus, self)('Bus')
        self.allocation = 3
        self.nodes = [None] * self.allocation
        self.refNo = [None] * self.allocation
        self.numNodesThisBus = 0
        self.Ysc = None
        self.Zsc = None
        self.VBus = None
        self.busCurrent = None
        self.kVBase = 0.0
        # signify that it has not been set
        self.x = 0.0
        self.y = 0.0
        self.distFromMeter = 0.0
        self.coordDefined = False
        self.keep = False
        self.isRadialBus = False

    def addANode(self):
        self.numNodesThisBus += 1
        if self.numNodesThisBus > self.allocation:
            self.allocation = self.allocation + 1
            self.nodes = Utilities.resizeArray(self.nodes, self.allocation)
            self.refNo = Utilities.resizeArray(self.refNo, self.allocation)

    def add(self, nodeNum):
        if nodeNum == 0:
            result = 0
        else:
            result = self.find(nodeNum)
            if result == 0:
                # add a node to the bus
                self.addANode()
                self.nodes[self.numNodesThisBus] = nodeNum
                ckt = DSSGlobals.activeCircuit
                ckt.setNumNodes(ckt.getNumNodes() + 1)
                # global node number for circuit
                self.refNo[self.numNodesThisBus] = ckt.getNumNodes()
                result = ckt.getNumNodes()
                # return global node number
        return result

    def find(self, nodeNum):
        """Returns reference num for node by node number."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numNodesThisBus):
                break
            if self.nodes[i] == nodeNum:
                return self.refNo[i]
        return 0

    def getRef(self, nodeIndex):
        """Returns reference num for node by node index."""
        # FIXME Check zero based indexing
        if nodeIndex > 0 and nodeIndex <= self.numNodesThisBus:
            return self.refNo[nodeIndex]
        else:
            return 0

    def getNum(self, nodeIndex):
        """Returns ith node number designation."""
        if nodeIndex > 0 and nodeIndex <= self.numNodesThisBus:
            return self.nodes[nodeIndex]
        else:
            return 0

    def allocateBusQuantities(self):
        # have to perform a short circuit study to get this allocated
        self.Ysc = CMatrixImpl(self.numNodesThisBus)
        self.Zsc = CMatrixImpl(self.numNodesThisBus)
        self.allocateBusVoltages()
        self.allocateBusCurrents()

    def getZsc0(self):
        """= Zs + 2 Zm"""
        if self.Zsc is not None:
            return self.Zsc.avgDiagonal().add(self.Zsc.avgOffDiagonal().multiply(2.0))
        else:
            return Complex.ZERO

    def getZsc1(self):
        """= Zs - Zm"""
        if self.Zsc is not None:
            return self.Zsc.avgDiagonal().subtract(self.Zsc.avgOffDiagonal())
        else:
            return Complex.ZERO

    def findIdx(self, nodeNum):
        """Returns index of node by node number."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numNodesThisBus):
                break
            if self.nodes[i] == nodeNum:
                return i
        return 0
        # TODO Check zero based indexing

    def allocateBusVoltages(self):
        self.VBus = Utilities.resizeArray(self.VBus, self.numNodesThisBus)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numNodesThisBus):
                break
            self.VBus[i] = Complex.ZERO

    def allocateBusCurrents(self):
        self.busCurrent = Utilities.resizeArray(self.busCurrent, self.numNodesThisBus)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numNodesThisBus):
                break
            self.busCurrent[i] = Complex.ZERO

    def getVBus(self):
        return self.VBus

    def setVBus(self, vBus):
        self.VBus = vBus

    def getBusCurrent(self):
        return self.busCurrent

    def setBusCurrent(self, buscurrent):
        self.busCurrent = buscurrent

    def getZsc(self):
        return self.Zsc

    def setZsc(self, zsc):
        self.Zsc = zsc

    def getYsc(self):
        return self.Ysc

    def setYsc(self, ysc):
        self.Ysc = ysc

    def getX(self):
        return self.x

    def setX(self, x):
        self.x = x

    def getY(self):
        return self.y

    def setY(self, y):
        self.y = y

    def getKVBase(self):
        return self.kVBase

    def setKVBase(self, kVBase):
        self.kVBase = kVBase

    def getDistFromMeter(self):
        return self.distFromMeter

    def setDistFromMeter(self, distFromMeter):
        self.distFromMeter = distFromMeter

    def isCoordDefined(self):
        return self.coordDefined

    def setCoordDefined(self, defined):
        self.coordDefined = defined

    def isBusChecked(self):
        return self.busChecked

    def setBusChecked(self, checked):
        self.busChecked = checked

    def isKeep(self):
        return self.keep

    def setKeep(self, keep):
        self.keep = keep

    def isRadialBus(self):
        return self.isRadialBus

    def setRadialBus(self, isRadial):
        self.isRadialBus = isRadial

    def getNumNodesThisBus(self):
        return self.numNodesThisBus

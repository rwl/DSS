# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

from numpy import zeros

from dss.common.Named import Named

global ActiveCircuit

class Bus(Named):
    """A bus is a circuit element having [1..N] nodes. Buses are the connection
    point for all other circuit elements. The main electrical property of a Bus
    is voltage.  Each node has a voltage with respect to the zero voltage
    reference (remote ground).  There is a nodal admittance equation written
    for every node (i.e., the current is summed at each node).  A bus may have
    any number of nodes (places to connect device terminal conductors).  The
    nodes may be arbitrarily numbered, except that the first N are reserved for
    the N phases.  Thus, if a bus has 3-phase devices connected to it,
    connections would be expected to nodes 1, 2, and 3.  So the DSS would use
    these voltages to compute the sequence voltages, for example.   Phase 1
    would nominally represent the same phase throughout the circuit, although
    that would not be mandatory.  It is up to the user to maintain a consistent
    definition.  If only the default connections are used, the consistency is
    maintained automatically. Any other nodes would simply be points of
    connection with no special meaning.  Each Bus object keeps track of the
    allocation and designation of its nodes.  Node 0 of a bus is always the
    voltage reference (a.k.a, ground, or earth). That is, it always has a
    voltage of exactly zero volts.
    """

    def __init__(self, VBus=None, BusCurrent=None, Zsc=None, Ysc=None, x=0.0,
            y=0.0, kVBase=0.0, CoordDefined=False, BusChecked=False,
            Keep=False, IsRadialBus=False, Circuit=None, *args, **kw_args):
        """Initialises a new 'Bus' instance.
        """
        self.VBus = VBus

        self.BusCurrent = BusCurrent

        self.Zsc = Zsc

        self.Ysc = Ysc

        #: X coordinate.
        self.x = x

        #: Y coordinate.
        self.y = y

        #: Base kV for each node to ground.
        #  Default: 0.0 (Signify that it has not been set)
        self.kVBase = kVBase

        #: Are the coordinates defined?
        self.CoordDefined = CoordDefined

        self.BusChecked = BusChecked

        self.Keep = Keep

        #: Flag for general use in bus searches.
        self.IsRadialBus = IsRadialBus

        ## Bus Collection
        self.BusArray = []

        NodeBus = {'BusRef': 0,  # Ref to Bus in circuit's BusList
                   'NodeNum': 0}
        self.NodeBusArray = []

#        self._circuit = None
#        self.Circuit = Circuit

        self._NumNodesThisBus = 0
        self._Nodes = []
        self._Allocation = 3
        self._RefNo = []

        super(Bus, self).__init__('Bus', *args, **kw_args)


    def _AddANode(self):
        self.NumNodesThisBus += 1
        if self.NumNodesThisBus > self._Allocation:
            self._Allocation += 1
#            ReallocMem(self._Nodes, Sizeof(Nodes^[1])*Allocation)
#            ReallocMem(self._RefNo, Sizeof(RefNo^[1])*Allocation)


    def Add(self, NodeNum=0):
        if NodeNum == 0:
            Result = 0
        else:
            Result = self.Find(NodeNum)
            if Result == 0:
                # Add a node to the bus
                self.AddANode()
                self._Nodes[self._NumNodesThisBus] = NodeNum

                ac = ActiveCircuit
                ac.NumNodes += 1  # Global node number for circuit
                self.RefNo[self._NumNodesThisBus] = ac.NumNodes
                Result = ac.NumNodes  # Return global node number
        return 0


    def Find(self, NodeNum=0):
        """Returns reference num for node by node number."""
        for i in range(self._NumNodesThisBus):
            if self._Nodes[i] == NodeNum:
                return self._RefNo[i]
        return 0


    def GetRef(self, NodeIndex=0):
        """Returns reference Num for node by node index."""
        Result = 0
        if (NodeIndex > 0) and (NodeIndex <= self._NumNodesThisBus):
            Result = self._RefNo[NodeIndex]
        return Result


    def GetNum(self, NodeIndex=0):
        """Returns ith node number designation."""
        Result = 0
        if (NodeIndex > 0) and (NodeIndex <= self._NumNodesThisBus):
            Result = self._Nodes[NodeIndex]
        return Result


    def AllocateBusQuantities(self):
        # Have to perform a short circuit study to get this allocated
        if self.Ysc is not None:
            self.Ysc = None
        if self.Zsc is not None:
            self.Zsc = None
        self.Ysc = zeros(self._NumNodesThisBus)
        self.Zsc = zeros(self._NumNodesThisBus)
        self.AllocateBusVoltages()
        self.AllocateBusCurrents()


    def _Get_Zsc0(self):
        """= Zs + 2 Zm"""
        if self.Zsc is not None:
            Result = self.Zsc.AvgDiagonal# + CmulReal(self.Zsc.AvgOffDiagonal, 2.0)
        else:
            Result = complex(0, 0)
        return Result

    Zsc0 = property(_Get_Zsc0)


    def _Get_Zsc1(self):
        """= Zs-Zm"""
        if self.Zsc is not None:
            Result = self.Zsc.AvgDiagonal - self.Zsc.AvgOffDiagonal
        else:
            Result = complex(0, 0)
        return Result

    Zsc1 = property(_Get_Zsc1)


    def FindIdx(self, NodeNum=0):
        """Returns index of node by node number."""
        for i in range(self._NumNodesThisBus):
            if self._Nodes[i] == NodeNum:
                return i
        return 0


    def AllocateBusVoltages(self):
        self.VBus = [complex(0.0, 0.0)] * self._NumNodesThisBus


    def AllocateBusCurrents(self):
        self.BusCurrent = [complex(0.0, 0.0)] * self._NumNodesThisBus


    @property
    def NumNodesThisBus(self):
        return self._NumNodesThisBus

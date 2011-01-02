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

from pydss.common.Named import Named

class Bus(Named):
    """A bus is a circuit element having [1..N] nodes. Buses are the connection point for all other circuit elements. The main electrical property of a Bus is voltage.  Each node has a voltage with respect to the zero voltage reference (remote ground).  There is a nodal admittance equation written for every node (i.e., the current is summed at each node).  A bus may have any number of nodes (places to connect device terminal conductors).  The nodes may be arbitrarily numbered, except that the first N are reserved for the N phases.  Thus, if a bus has 3-phase devices connected to it, connections would be expected to nodes 1, 2, and 3.  So the DSS would use these voltages to compute the sequence voltages, for example.   Phase 1 would nominally represent the same phase throughout the circuit, although that would not be mandatory.  It is up to the user to maintain a consistent definition.  If only the default connections are used, the consistency is maintained automatically. Any other nodes would simply be points of connection with no special meaning.  Each Bus object keeps track of the allocation and designation of its nodes.  Node 0 of a bus is always the voltage reference (a.k.a, ground, or earth). That is, it always has a voltage of exactly zero volts.
    """

    def __init__(self, vBus=0.0, busCurrent=0.0, zSC=0.0, ySC=0.0, x=0.0, y=0.0, kVBase=0.0, coordsDefined=False, busChecked=False, keep=False, radialBus=False, circuit=None, *args, **kw_args):
        """Initialises a new 'Bus' instance.
        """

        self.vBus = vBus


        self.busCurrent = busCurrent


        self.zSC = zSC


        self.ySC = ySC

        #: X coordinate.
        self.x = x

        #: Y coordinate.
        self.y = y

        #: Base kV for each node to ground.
        self.kVBase = kVBase

        #: Are the coordinates defined?
        self.coordsDefined = coordsDefined


        self.busChecked = busChecked


        self.keep = keep

        #: Flag for general use in bus searches.
        self.radialBus = radialBus

        self._circuit = None
        self.circuit = circuit

        super(Bus, self).__init__(*args, **kw_args)

    def getcircuit(self):
        
        return self._circuit

    def setcircuit(self, value):
        if self._circuit is not None:
            filtered = [x for x in self.circuit.busList if x != self]
            self._circuit._busList = filtered

        self._circuit = value
        if self._circuit is not None:
            if self not in self._circuit._busList:
                self._circuit._busList.append(self)

    circuit = property(getcircuit, setcircuit)

    def find(self, nodeNum):
        pass
    def getRef(self, nodeIndex):
        pass

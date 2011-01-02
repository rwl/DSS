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

from pydss.delivery.PowerDeliveryElement import PowerDeliveryElement

class Reactor(PowerDeliveryElement):
    """Uses same rules as Capacitor and Fault for connections  Implemented as a
    two-terminal constant impedance (Power Delivery Element) Defaults to a
    Shunt Reactor but can be connected as a two-terminal series reactor  If
    Parallel=Yes, then the R and X components are treated as being in parallel.
    Bus2 connection defaults to 0 node of Bus1 (if Bus2 has the default bus
    connection at the time Bus1 is defined.  Therefore, if only Bus1 is
    specified, a shunt Reactor results. If delta connected, Bus2 is set to node
    zero of Bus1 and nothing is returned in the lower half of YPrim - all
    zeroes.  If an ungrounded wye is desired, explicitly set Bus2= and set all
    nodes the same, e.g.
        Bus1.4.4.4   (uses 4th node of Bus1 as neutral point)  or
        BusNew.1.1.1  (makes a new bus for the neutral point)
    You must specify the nodes or you will get a series Reactor!  A series
    Reactor is specified simply by setting bus2 and declaring the connection to
    be Wye.  If the connection is specified as delta, nothing will be connected
    to Bus2. In fact the number of terminals is set to 1.  Reactance may be
    specified as:
        1.  kvar and kv ratings at base frequency.  impedance.
        Specify kvar as total for all phases. For 1-phase, kV = Reactor coil kV
        rating.  For 2 or 3-phase, kV is line-line three phase. For more than
        3 phases, specify kV as actual coil voltage.

        2.  Series Resistance and Reactance in ohns at base frequency to be
        used in each phase.  If specified in this manner, the given value is
        always used whether wye or delta.

        3.  A R and X  matrices. If conn=wye then 2-terminal through device If
        conn=delta then 1-terminal. Ohms at base frequency

    Note that Rmatix may be in parallel with Xmatric (set parallel = Yes)
    """

    def __init__(self, bus1='', bus2='', kVAr=0.0, kV=0.0, conn="Wye",
            parallel=False, r=0.0, x=0.0, rp=0.0, rMatrix=None, xMatrix=None,
            *args, **kw_args):
        """Initialises a new 'Reactor' instance.
        """
        #: Name of first bus.
        self.bus1 = bus1

        #: Name of 2nd bus. Defaults to all phases connected to first bus, node
        #  0. (Shunt Wye Connection) Not necessary to specify for delta (LL)
        #  connection
        self.bus2 = bus2

        #: Total kvar, all phases.  Evenly divided among phases. Only
        #  determines X.  Specify R separately.
        self.kVAr = kVAr

        #: For 2, 3-phase, kV phase-phase. Otherwise specify actual coil
        #  rating.
        self.kV = kV

        #: Default is wye, which is equivalent to LN. If Delta, then only one
        #  terminal. Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: Signifies R and X are to be interpreted as being in parallel.
        self.parallel = parallel

        #: Resistance, each phase, ohms.
        self.r = r

        #: Reactance, each phase, ohms at base frequency.
        self.x = x

        #: Resistance in parallel with R and X (the entire branch). Assumed
        #  infinite if not specified.
        self.rp = rp

        #: Resistance matrix, lower triangle, ohms at base frequency. Order of
        #  the matrix is the number of phases.
        self.rMatrix = rMatrix

        #: Reactance matrix, lower triangle, ohms at base frequency. Order of
        #  the matrix is the number of phases.
        self.xMatrix = xMatrix

        super(Reactor, self).__init__(*args, **kw_args)

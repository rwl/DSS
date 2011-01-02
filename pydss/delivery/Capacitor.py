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

class Capacitor(PowerDeliveryElement):
    """Basic capacitor  Implemented as a two-terminal constant impedance (Power
    Delivery Element)  Bus2 connection defaults to 0 node of Bus1 (if Bus2 has
    the default bus connection at the time Bus1 is defined.  Therefore, if only
    Bus1 is specified, a shunt capacitor results. If delta connected, Bus2 is
    set to node zero of Bus1 and nothing is returned in the lower half of
    YPrim - all zeroes.  If an ungrounded wye is desired, explicitly set
    Bus2= and set all nodes the same, e.g. Bus1.4.4.4   (uses 4th node of
    Bus1 as neutral point) or BusNew.1.1.1  (makes a new bus for the neutral
    point) You must specify the nodes or you will get a series capacitor!  A
    series capacitor is specified simply by setting bus2 and declaring the
    connection to be Wye.  If the connection is specified as delta, nothing
    will be connected to Bus2. In fact the number of terminals is set to 1.
    Capacitance may be specified as:
        1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
        total for all phases (all can assumed equal). For 1-phase,
        kV = capacitor can      kV rating. For 2 or 3-phase, kV is line-line
        three phase. For more than 3 phases, specify kV as actual can voltage.

        2.  Capacitance in uF to be used in each phase.  If specified in this
        manner, the given value is always used whether wye or delta.

        3.  A nodal C matrix (like a nodal admittance matrix). If conn=wye then
        2-terminal through device If conn=delta then 1-terminal. Microfarads.
    """

    def __init__(self, bus1='', bus2='', kVAr=0.0, kV=0.0, conn="Wye", cuf=0.0,
            r=0.0, xl=0.0, harm=0.0, nSteps=0, states=False, cMatrix=None,
            *args, **kw_args):
        """Initialises a new 'Capacitor' instance.
        """
        #: Name of first bus.
        self.bus1 = bus1

        #: Name of 2nd bus. Defaults to all phases connected to first bus,
        #  node 0.  (Shunt Wye Connection) Not necessary to specify for delta
        #  (LL) connection
        self.bus2 = bus2

        #: Total kvar, if one step, or ARRAY of kvar ratings for each step.
        #  Evenly divided among phases. See rules for NUMSTEPS.
        self.kVAr = kVAr

        #: For 2, 3-phase, kV phase-phase. Otherwise specify actual can rating.
        self.kV = kV

        #: Default is wye, which is equivalent to LN. Values are: "Wye", "LN",
        #  "Delta", "LL"
        self.conn = conn

        #: Array of Capacitance, each phase, for each step, microfarads.  See
        #  Rules for NumSteps.
        self.cuf = cuf

        #: Array of series resistance in each phase (line), ohms.
        self.r = r

        #: Array of series inductive reactance(s) in each phase (line) for
        #  filter, # ohms at base frequency. Use this OR 'h' property to define
        #  filter.
        self.xl = xl

        #: Array of harmonics to which each step is tuned. Zero is interpreted
        #  as meaning zero reactance (no filter).
        self.harm = harm

        #: Number of steps in this capacitor bank. Default = 1. Forces
        #  reallocation of the capacitance, reactor, and states array.  Rules:
        #  If this property was previously =1, the value in the kvar property
        #  is divided equally among the steps. The kvar property does not need
        #  to be reset if that is accurate.  If the Cuf or Cmatrix property was
        #  used previously, all steps are set to the value of the first step.
        #  The states property is set to all steps on. All filter steps are set
        #  to the same harmonic.  If this property was previously >1, the
        #  arrays are reallocated, but no values are altered. You must
        #  SUBSEQUENTLY assign all array properties.
        self.nSteps = nSteps

        #: Array of integers {1|0} states representing the state of each step
        #  (on|off). Defaults to 1 when reallocated (on).  Capcontrol will
        #  modify this array as it turns steps on or off.
        self.states = states

        #: Nodal cap. matrix, lower triangle, microfarads, of the following
        #  form:    cmatrix='c11 | -c21 c22 | -c31 -c32 c33' All steps are
        #  assumed the same if this property is used.
        self.cMatrix = cMatrix

        super(Capacitor, self).__init__(*args, **kw_args)

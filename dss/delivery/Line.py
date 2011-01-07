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

from dss.delivery.PowerDeliveryElement import PowerDeliveryElement

class Line(PowerDeliveryElement):
    """Line impedances are specified in per unit length and are multiplied by
    the length when the primitive Y matrix is computed.  You may specify the
    impedances of the line either by symmetrical components or by R, X, and
    nodal C matrices  (also per unit length).  All C's is entered in nano
    farads.  The ultimate values are in the matrices.  If you specify matrices,
    then the symmetrical component values are ignored.  However, if you change
    any of the symmetrical component values the matrices will be recomputed.
    It is assumed you want to use symmetrical component values.  Don't mix data
    entry by matrix and by symmetrical components.  Note that if you change the
    number of phases, the matrices are reallocated and reinitialized with
    whatever is currently in the symmetrical component data.   Multi-phase,
    two-port line or cable.  Pi model.  Power delivery element described by its
    impedance.  Impedances may be specified by symmetrical component values or
    by matrix values.  Alternatively, you may simply refer to an existing
    LineCode object from which the impedance values will be copied.  Then you
    need only specify the length.  You can define the line impedance at a base
    frequency directly in a Line object definition or you can import the
    impedance definition from a LineCode object. Both of these definitions of
    impedance are quite similar except that the LineCode object can perform
    Kron reduction.  If the geometry property is specified all previous
    definitions are ignored. The DSS will compute the impedance matrices from
    the specified geometry each time the frequency changes.  Whichever
    definition is the most recent applies, as with nearly all DSS functions.
    Note the units property; you can declare any length measurement in whatever
    units you please.  Internally, everything is converted to meters. Just be
    sure to declare the units. Otherwise, they are assumed to be compatible
    with other data or irrelevant.
    """

    def __init__(self, bus1='', bus2='', lineCode='', length=0.0, r1=0.0,
            x1=0.0, r0=0.0, x0=0.0, c1=0.0, c0=0.0, switch=False, rg=0.0,
            xg=0.0, rho=0.0, geometry='', units="none", rMatrix=None,
            xMatrix=None, cMatrix=None, spacing=None, wires=None,
            *args, **kw_args):
        """Initialises a new 'Line' instance.
        """
        #: Name of bus for terminal 1. Node order definitions optional.
        self.bus1 = bus1

        #: Name of bus for terminal 2.
        self.bus2 = bus2

        #: Name of linecode object describing line impedances.  If you use a
        #  line code, you do not need to specify the impedances here.  The line
        #  code must have been PREVIOUSLY defined.  The values specified last
        #  will prevail over those specified earlier (left-to-right sequence of
        #  properties).  If no line code or impedance data are specified, line
        #  object defaults to 336 MCM ACSR on 4 ft spacing.
        self.lineCode = lineCode

        #: Length of line. If units do not match the impedance data, specify
        #  'units' property.
        self.length = length

        #: Positive-sequence Resistance, ohms per unit length.
        self.r1 = r1

        #: Positive-sequence Reactance, ohms per unit length.
        self.x1 = x1

        #: Zero-sequence resistance, ohms per unit length.
        self.r0 = r0

        #: Zero-sequence Reactance, ohms per unit length.
        self.x0 = x0

        #: Positive-sequence capacitance, nF per unit length.
        self.c1 = c1

        #: Zero-sequence capacitance, nF per unit length.
        self.c0 = c0

        #: Designates this line as a switch for graphics and algorithmic
        #  purposes.  SIDE EFFECT: Sets R1=0.001 X1=0.0. You must reset if you
        #  want something different.
        self.switch = switch

        #: Carson earth return resistance per unit length used to compute
        #  impedance values at base frequency.  For making better frequency
        #  adjustments.
        self.rg = rg

        #: Carson earth return reactance per unit length used to compute
        #  impedance values at base frequency.  For making better frequency
        #  adjustments.
        self.xg = xg

        #: Earth resistivity used to compute earth correction factor. Overrides
        #  Line geometry definition if specified.
        self.rho = rho

        #: Geometry code for LineGeometry Object. Supercedes any previous
        #  definition of line impedance. Line constants are computed for each
        #  frequency change or rho change. CAUTION: may alter number of phases.
        self.geometry = geometry

        #: Default is None - assumes length units match impedance units. Values
        #  are: "none", "mi", "km", "kft", "m", "me", "ft", "in", "cm"
        self.units = units

        #: Resistance matrix, lower triangle, ohms per unit length. Order of
        #  the matrix is the number of phases. May be used to specify the
        #  impedance of any line configuration.  For balanced line models, you
        #  may use the standard symmetrical component data definition instead.
        self.rMatrix = rMatrix

        #: Reactance matrix, lower triangle, ohms per unit length. Order of the
        #  matrix is the number of phases. May be used to specify the impedance
        #  of any line configuration.  For balanced line models, you may use
        #  the standard symmetrical component data definition instead.
        self.xMatrix = xMatrix

        #: Nodal Capacitance matrix, lower triangle, nf per unit length.  Order
        #  of the matrix is the number of phases.  May be used to specify the
        #  shunt capacitance of any line configuration.  For balanced line
        #  models, you may use the standard symmetrical component data
        #  definition instead.
        self.cMatrix = cMatrix

        self.spacing = spacing

        self.wires = [] if wires is None else wires

        super(Line, self).__init__(*args, **kw_args)

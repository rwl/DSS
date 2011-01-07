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

from dss.conversion.PowerConversionElement import PowerConversionElement

class Equivalent(PowerConversionElement):
    """Multi terminal, multi-phase Short Circuit (Thevinen) Equivalent  Enter
    positive and zero short circuit impedance matrices and Voltage behind the
    equivalent.
    """

    def __init__(self, buses='', baseKV=0.0, pu=0.0, angle=0.0, frequency=0.0,
            phases=0, r1=0.0, x1=0.0, r0=0.0, x0=0.0, *args, **kw_args):
        """Initialises a new 'Equivalent' instance.
        """
        #: Array of Bus Names to which equivalent source is connected.
        self.buses = buses

        #: Base Source kV, usually L-L unless you are making a
        #  positive-sequence model in which case, it will be L-N.
        self.baseKV = baseKV

        #: Per unit of the base voltage that the source is actually operating
        #  at.
        self.pu = pu

        #: Phase angle in degrees of first phase.
        self.angle = angle

        #: Source frequency.
        self.frequency = frequency

        #: Number of phases.
        self.phases = phases

        #: Positive-sequence resistance matrix, lower triangle.
        self.r1 = r1

        #: Positive-sequence reactance matrix, lower triangle.
        self.x1 = x1

        #: Zero-sequence resistance matrix, lower triangle.
        self.r0 = r0

        #: Zero-sequence reactance matrix, lower triangle.
        self.x0 = x0

        super(Equivalent, self).__init__(*args, **kw_args)

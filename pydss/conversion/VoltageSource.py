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

from pydss.conversion.PowerConversionElement import PowerConversionElement

class VoltageSource(PowerConversionElement):
    """This is a special power conversion element.  It is special because
    voltage sources must be identified to initialize the solution with all
    other injection sources set to zero.  A Vsource object is simply a
    multi-phase Thevenin equivalent with data specified as it would commonly
    be for a power system source equivalent: Line-line voltage (kV) and short
    circuit MVA.
    """

    def __init__(self, bus1='', baseKV=0.0, perUnit=0.0, angle=0.0,
            frequency=0.0, mvaSC3=0.0, mvaSC1=0.0, x1R1=0.0, x0R0=0.0,
            iSC3=0.0, iSC1=0.0, r1=0.0, x1=0.0, r0=0.0, x0=0.0,
            scanType="Positive", zSpecType="MVASc", vMag=0.0, z=None,
            zInv=None, *args, **kw_args):
        """Initialises a new 'VoltageSource' instance.
        """
        #: Name of bus to which the source's one terminal is connected.
        #  Remember to specify the node order if the terminals are connected
        #  in some unusual manner.
        self.bus1 = bus1

        #: Base Source kV, usually L-L unless you are making a
        #  positive-sequence model in which case, it will be L-N.
        self.baseKV = baseKV

        #: Per unit of the base voltage that the source is actually
        #  operating at.  Assumed balanced for all phases.
        self.perUnit = perUnit

        #: Phase angle in degrees of first phase.
        self.angle = angle

        #: Source frequency.
        self.frequency = frequency

        #: MVA Short circuit, 3-phase fault.  Z1 is determined by squaring
        #  the base kv and dividing by this value.  For single-phase source,
        #  this value is not used.
        self.mvaSC3 = mvaSC3

        #: MVA Short Circuit, 1-phase fault.  The 'single-phase impedance',
        #  Zs, is determined by squaring the base kV and dividing by this
        #  value.  Then Z0 is determined by Z0 = 3Zs - 2Z1.  For 1-phase
        #  sources, Zs is used directly. Use x0_r0 to define X/R ratio for
        #  1-phase source.
        self.mvaSC1 = mvaSC1

        #: Positive-sequence X/R ratio.
        self.x1R1 = x1R1

        #: Zero-sequence X/R ratio.
        self.x0R0 = x0R0

        #: Alternate method of defining the source impedance. 3-phase short
        #  circuit current, amps.
        self.iSC3 = iSC3

        #: Alternate method of defining the source impedance. Single-phase
        #  short circuit current, amps.
        self.iSC1 = iSC1

        #: Alternate method of defining the source impedance. Positive-sequence
        #  resistance, ohms.
        self.r1 = r1

        #: Alternate method of defining the source impedance. Positive-sequence
        #  reactance, ohms.
        self.x1 = x1

        #: Alternate method of defining the source impedance. Zero-sequence
        #  resistance, ohms.
        self.r0 = r0

        #: Alternate method of defining the source impedance. Zero-sequence
        #  reactance, ohms.
        self.x0 = x0

        #: Maintain specified sequence for harmonic solution.  Default is
        #  positive sequence. Otherwise, angle between phases rotates with
        #  harmonic. Values are: "Positive", "Zero", "None"
        self.scanType = scanType

        #: Values are: "MVASc", "ISc", "Z1Z0"
        self.zSpecType = zSpecType

        self.vMag = vMag

        #: Base frequency series Z matrix.
        self.z = z

        self.zInv = zInv

        super(VoltageSource, self).__init__(*args, **kw_args)

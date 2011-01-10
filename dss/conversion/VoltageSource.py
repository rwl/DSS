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

from numpy import array, Inf, sqrt, sin, pi

from dss.conversion.PowerConversionElement import PowerConversionElement

SQRT3 = sqrt(3.0)

class VoltageSource(PowerConversionElement):
    """This is a special power conversion element.  It is special because
    voltage sources must be identified to initialize the solution with all
    other injection sources set to zero.  A Vsource object is simply a
    multi-phase Thevenin equivalent with data specified as it would commonly
    be for a power system source equivalent: Line-line voltage (kV) and short
    circuit MVA.
    """

    def __init__(self, bus1='', baseKV=0.0, perUnit=1.0, angle=0.0,
            frequency=60.0, mvaSC3=2000.0, mvaSC1=2100.0, x1R1=0.0, x0R0=0.0,
            iSC3=1e4, iSC1=10.5e3, r1=1.65, x1=6.6, r0=1.9, x0=5.7,
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
#        self.x1R1 = x1R1

        #: Zero-sequence X/R ratio.
#        self.x0R0 = x0R0

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


    @property
    def nPhases(self):
        """Number of phases. For 3 or less, phase shift is 120 degrees.
        Valid 'phases' values are: "BCN", "ACN", "AB", "A", "B", "ABCN",
        "AC", "N", "AN", "C", "ABN", "BN", "ABC", "BC", "CN"
        """
        phases = self.phases
        if phases in ["ABCN", "ABC"]:
            n_phases = 3
        elif phases in ["BCN", "ACN", "ABN", "AB", "BC"]:
            n_phases = 2
        elif phases in ["AN", "BN", "CN", "A", "B", "C"]:
            n_phases = 1
        else:
            n_phases = 0

        return n_phases


    @property
    def x1R1(self):
        """Positive-sequence X/R ratio.
        """
        if self.r1 != 0.0:
            return self.x1 / self.r1
        else:
            return Inf


    @property
    def x0R0(self):
        """Zero-sequence X/R ratio.
        """
        if self.r0 != 0.0:
            return self.x0 / self.r0
        else:
            return Inf


    def recalcElementData(self):
        """Computes the attributes of the source.
        """
        r1 = self.r1
        x1 = self.x1
        r0 = self.r0
        x0 = self.x0
        np = self.nPhases

        # For a Source, n_phases = n_cond
        self.z = z = array(0.0 + 0.0j, (np, np))
        self.z_inv = z_inv = array(0.0 + 0.0j, (np, np))

        if np == 1:
            factor = 1.0
        else:
            factor = SQRT3

        rs = 0.0
        rm = 0.0
        xs = 0.1
        xm = 0.0

        if self.spec_type == "MVAsc":
            kv_base = self.base_voltage.nominal_voltage

            self.x1 = kv_base**2 / self.mva_sc3/sqrt(1.0 + 1.0/self.x1r1**2)
            Xs      = kv_base**2 / self.mva_sc1/sqrt(1.0 + 1.0/self.x0r0**2)

            r1 = self.x1 / self.x1r1
            xm = xs - x1
            x0 = xs + 2.0 * xm
            r0 = self.x0 / self.x0r0

            self.i_sc3 = self.mva_sc3 * 1000.0 / (SQRT3 * kv_base)
            self.i_sc1 = self.mva_sc1 * 1000.0 / (factor * kv_base)

            if np == 1:
                rs = xs / self.x0r0
            else:
                rs = (2.0 * r1 + r0) / 3.0

            rm = (r0 - r1) / 3.0

        elif self.ZSpecType == "Isc":
            raise NotImplementedError
        elif self.ZSpecType == "Specified":
            raise NotImplementedError
        else:
            raise

        zs = complex(rs, xs)
        zm = complex(rm, xm)

        for i in range(np):
            z[i, i] = zs
            for j in range(i - 1):
                z[i, j] = zm
                z[j, i] = zm

        if np == 1:
            self.v_mag = kv_base * self.pu * 1000.0
        else:
            self.v_mag = kv_base * self.pu * 1000.0 \
                / (2.0 / (sin( (180.0 / np) * pi / 180.0 )))


    def calcYPrim(self):
        """Returns the primitive Y (admittance) matrix for this element alone.
        """
        z_inv = self.z_inv
        n_phases = self.n_phases

        if self.y_prim_invalid: # Build only YPrim Series
            y_order = n_phases * 2#len(self.terminals)

            self.y_prim_series = array(0.0+0.0j, (y_order, y_order))
            self.y_prim        = array(0.0+0.0j, (y_order, y_order))
        else:
            self.y_prim_series[:] = 0.0+0.0j
            self.y_prim[:]        = 0.0+0.0j

        y_prim_freq = self.model.frequency #TODO: Implement circuit frequency.
        if self.frequency != 0.0:
            freq_multiplier = y_prim_freq / self.frequency
        else:
            freq_multiplier = 1.0

        for i in range(n_phases):
            for j in range(n_phases):
                value = self.z[i, j]
                # Modify from base frequency.
                value = complex( value.real, value.imag * freq_multiplier )
                z_inv[i, j] = value

#        numpy.invert(z_inv) #TODO: Inverse using CVXOPT.

        for i in range(n_phases):
            for j in range(n_phases):
                value = z_inv[i, j]

                self.y_prim_series[i, j] = value
                self.y_prim_series[i + n_phases, j + n_phases] = value
                self.y_prim_series[i + n_phases, j] = -value

        y_prim = array( self.y_prim_series )

        #TODO: Zero out rows and columns for open conductors.

        self.y_prim_invalid = False

        return y_prim

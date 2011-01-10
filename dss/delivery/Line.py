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

from numpy import pi, sqrt
from scipy.sparse import csr_matrix as sparse

from dss.delivery.PowerDeliveryElement import PowerDeliveryElement

# 5 kVAr of capacitive reactance at 345 kV to avoid open line problem.
CAP_EPSILON = complex(0.0, 4.2e-8)

class Line(PowerDeliveryElement):
    """A Line defaults to 3-phases and some typical symmetrical component
    data.

    Line impedances are specified in per unit length and are multiplied
    by the length when the primitive Y matrix is computed.  You may specify the
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


    def recalcElementData(self):

        n_phases = self.n_phases
        r1 = self.r1
        x1 = self.x1
        c1 = self.c1
        positive_sequence = self.parent.positive_sequence
        length = self.length

        z = sparse((n_phases, n_phases))
        z_inv = sparse((n_phases, n_phases))
        y_c = sparse((n_phases, n_phases))

        ONETHIRD = 1.0 / 3.0

        # Only time this is called is if symmetrical components are specified.
        z_tmp = complex(r1, x1) * 2.0

        # Handle special case for 1-phase line and/or positive sequence model.
        if n_phases == 1 or positive_sequence:
            # Long-line equivalent PI, but only for ckt_model='positive'.
            if positive_sequence and (c1 > 0.0):
                # Nominal PI parameters per unit length.
                z_s = complex(r1, x1)
                y_s = complex(0.0, 2*pi * base_frequency * c1)

                # Apply the long-line correction to obtain Zm and Ym.
                gamma_l = sqrt(mul(z_s, y_s))
                gamma_l = mul(gamma_l, length)

                exp_p = mul( complex(cos(gamma_l.imag()), sin(gamma_l.imag())),
                             exp(gamma_l.real()) )

                exp_2p = mul( complex(cos(0.5 * gamma_l.imag()),
                                      sin(0.5 * gamma_l.imag())),
                              exp(0.5 * gamma_l.real()) )

                exp_m  = numpy.inverse(exp_p)
                exp_2m = numpy.inverse(exp_2p)

                sinh_gl  = mul(exp_p - exp_m, 0.5)
                tanh2_gl = div(exp_2p - exp_2m, exp_2p + exp_2m)

                z_m = div( mul( mul(z_s, length), sinh_gl), gamma_l)
                y_m = div( mul( mul(y_s, length), tanh2_gl), mul(gamma_l, 0.5))

                # Rely on this function being called only once, unless R1, X1,
                # or C1 changes.
                r1 = z_m.real() / length
                x1 = z_m.imag() / length
                c1 = y_m.imag() / length / 2*pi / base_frequency

            # Zero sequence the same as positive sequence.
            r0 = r1
            x0 = x1
            c0 = c1

        z_s = mul( z_tmp + complex(r0, x0), ONETHIRD )
        z_m = mul( complex(r0, x0) - complex(r1, x1), ONETHIRD )

        y_c1 = 2*pi * base_frequency * c1
        y_c0 = 2*pi * base_frequency * c0

        y_s = mul(mul(complex(0.0, y_c1), 2.0) + complex(0.0, y_c0), ONETHIRD)
        y_m = mul( complex(0.0, y_c0) - complex(0.0, y_c1), ONETHIRD )

        for i in range(n_phases):
            z[i, i] = z_s
            y_c[i, i] = y_s

            for j in range(i):
                z_m[i, j] = z_m
                z_m[j, i] = z_m
                y_c[i, j] = y_m
                y_c[j, i] = y_m

        self.sym_components_changed = False


    def calcYPrim(self):
        """Returns the primitive Y (admittance) matrix for this element alone.
        """
        freq_mult = 1.0
        len_mult = 1.0

        if self.sym_components_changed:
            self.recalc_element_data()

        self._clear_y_prim()

        # Build series y_prim and Z matrix.
        if self.geometry_specified:
            raise NotImplementedError
        else:
            # Z is from line code or specified in line data.
            # Convert to per unit length.
            len_mult = length / conversion_factor # FUnitsConvert
            y_prim_freq = self.parent.solution.frequency
            freq_mult = y_prim_freq / base_frequency

            # Put in series RL.
            z_values = z.get_values_array_ptr()
            z_inv_values = z.get_values_array_ptr()

            # Correct the impedances for length and frequency.
            # Rg increases with frequency.
            # Xg modified by ln of sqrt(1/f).
            if xg != 0.0:
                xg_mod = 0.5 * k_xg * log(freq_mult)
            else:
                xg_mod = 0.0

            for i in range(n_order * n_order):
                z_inv_values[i] = complex(
                    z_values[i].real() + rg * (freq_mult - 1.0) * len_mult,
                    (z_values[i].imag() - xg_mod) * len_mult * freq_mult
                )

            numpy.invert(z_inv)

            for i in range(n_phases):
                for j in range(n_phases):
                    value = z_inv[i, j]
                    y_prim_series[i, j] = value
                    y_prim_series[i + n_phases, j + n_phases] = value
                    y_prim_series[i, j + n_phases] = -value
                    y_prim_series[j + n_phases, i] = -value

            y_prim = matrix(y_prim_series)

            for i in range(y_order):
                y_prim_series[i, i] = CAP_EPSILON

        # Now build the shunt admittances and add into y_prim.
        # Put half the Shunt Capacitive Admittance at each end.
        y_values = yc.get_values_array_ptr(n_order)

        if self.geometry_specified:
            # Values are already compensated for length and frequency.
            raise NotImplementedError

        else:
            # Regular line model - values computed per unit length at base
            # frequency.
            k = 0
            for i in range(n_phases):
                for j in range(n_phases):
                    k += 1 # Assume matrix in col order (1,1  2,1  3,1 ...).
                    value = complex(
                        0.0,
                        y_values[k].imag() * len_mult * freq_mult / 2.0
                    )
                    y_prim_shunt[i, j] = value
                    y_prim_shunt[i + n_phases, j + n_phases] = value

        # TODO: Account for open conductors.

        y_prim += y_prim_shunt

        self.y_prim_invalid = False


    def _clearYPrim(self):
        """Initialises the primitive Y matrix.
        """
        if self.y_prim_invalid:
            y_prim_series = sparse((y_order, y_order))
            y_prim_shunt  = sparse((y_order, y_order))
            y_prim        = sparse((y_order, y_order))

        else:
            y_prim_series[:] = 0.0
            y_prim_shunt[:]  = 0.0
            y_prim[:]        = 0.0

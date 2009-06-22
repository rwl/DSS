# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Power delivery elements usually consist of two or more multiphase
    terminals.  Their basic function is to transport energy from one point to
    another.  On the power system, the most common power delivery elements are
    lines and transformers

    Power delivery elements are standard electrical elements generally
    completely defined in the rms steady state by their impedances.
"""

from cim.wires import ACLineSegment, PowerTransformer

# 5 kVAr of capacitive reactance at 345 kV to avoid open line problem.
CAP_EPSILON = complex(0.0, 4.2e-8)

class Transformer(PowerTransformer):
    """ The Transfomer model is implemented as a multi-terminal (two or more)
        power delivery element.

        A transfomer consists of two or more Windings, connected in somewhat
        arbitray fashion (with the standard Wye-Delta defaults, of course).

        Transformers have one or more phases.  The number of conductors per
        terminal is always one more than the number of phases.  For wye- or
        star-connected windings, the extra conductor is the neutral point.  For
        delta-connected windings, the extra terminal is open internally (you
        normally leave this connected to node 0).
    """

    def recalc_element_data(self):

        # Determine delta direction.
        # If high voltage is delta, delta leads y.
        # If high voltage is wye, delta lags wye.
        primary   = self.contains_transformer_windings[0]
        secondary = self.contains_transformer_windings[1]

        if primary.connection_type == secondary.connection_type:
            delta_direction = 1
        else:
            if primary.base_voltage.nominal_voltage > \
                secondary.base_voltage.nominal_voltage:
                idx_high_volt = 1
            else:
                idx_high_volt = 2

            if self.contains_transformer_windings[idx_high_volt] == "d":
                delta_direction = 1
            elif self.contains_transformer_windings[idx_high_volt] == "y":
                delta_direction = -1
            else:
                delta_direction = 0

        # Re-establish term_ref if num windings or connection changed.
        self._set_term_ref()

        for i in range(n_windings):
            winding = self.contains_transformer_windings[i]
            if len(winding.tap_changers) > 0:
#                tap_increment = winding.max_tap - winding.min_tap / n_taps
                tap_increment = winding.tap_changers[0].normal_step
            else:
                tap_increment = 0.0

        # Set winding voltage bases (in volts)
        for i in range(n_windings):
            winding = self.contains_transformer_windings[i]
            # Get the actual turns voltage base for each winding.
            if winding.connection_type == "y":
                if n_phases == 2 or n_phases == 3:
                    # Assume 3-phase for 2-phase designation.
                    v_base = kv_ll * 577.35
                else:
                    v_base = kv_ll * 1000.0
            else:
                v_base = kv_ll * 1000.0 # delta

        # Base rating for winding 1.
        va_base = self.contains_transformer_windings[0].base_voltage * 1000.0

        # Normal and Emergency terminal current rating for UE check.
        v_factor = 1.0
        if primary.connection_type == "y":
            v_factor = primary.base_voltage * 0.001
        elif primary.connection_type == "d":
            if n_phases == 1:
                v_factor = primary.base_voltage * 0.001
            elif n_phases == 2 or n_phases == 3:
                v_factor = primary.base_voltage * 0.001 / SQRT3
            else:
                v_factor = primary.base_voltage * 0.001 * 0.5/sin(pi/n_phases)
        else:
            raise NotImplementedError

        # Divide per phase kva by voltage to neutral.
        norm_amps  = norm_max_high_kva / n_phases / v_factor
        emerg_amps = emerg_max_high_kva / n_phases / v_factor

        # Calc y_terminal at base frequency.
        self.calc_y_terminal(1.0)


    def calc_y_terminal(self, freq_mult):
        # Construct zb_matrix.
        zb[:] = 0.0
        z_base = 1.0 / (va_base / n_phases) # Base ohms on 1.0 volt basis.

        for i in range(n_windings - 1):
            # Convert pu to ohms on one volt base as we go...
            zb[i, i] = mul(complex((primary.r_pu + winding[i + 1].r_pu),
                                   freq_mult * xsc[i]), z_base)

            # Off diagonals.
            k = n_windings
            for i in range(n_windings - 1):
                for j in range(n_windings - 1):
                    value = mul( (zb[i, i] + zb[j, j]) - mul(
                        complex((windings[i + 1].r_pu + winding[j + 1].r_pu),
                        freq_mult * xsc[k]), z_base), 0.5)

                    zb[i, j] = value
                    k += 1


    def _set_term_ref(self):
        """ Sets an array which maps the two conductors of each winding to the
            phase and neutral conductors of the transformer according to the
            winding connection.
        """
        n_windings = len(self.contains_transformer_windings)
        n_phases = self.n_phases
        n_conds  = self.n_conds

        k = 0

        if n_phases == 1:
            for j in range(n_windings):
                k += 1
                term_ref[k] = (j - 1) * n_conds + 1
                k += 1
                term_ref[k] = j * n_conds
        else:
            for i in range(n_phases):
                for j in range(n_windings):
                    k += 1
                    winding = self.contains_transformer_windings[j]

                    if winding.connection_type == "y": # Wye
                        term_ref[k] = (j - 1) * n_conds + i
                        k += 1
                        term_ref[k] = j * n_conds

                    elif winding.connection_type == "d": # Delta
                        # TODO: Check with 2-phase open delta.
                        term_ref[k] = (j - 1) * n_conds + i
                        k += 1
                        # Connect to next phase in sequence.
                        term_ref[k] = (j - 1) * n_conds + self.rotate_phases(i)


    def rotate_phases(self, idx_phase):
        """ For Delta connections or Line-Line voltages begin.
        """
        result = idx_phase + self.delta_direction;

        # Make sure result is within limits.
        if n_phases > 2:
            # Assumes 2 phase delta is open delta.
            if result > n_phases:
                result = 1
            elif result < 1:
                result = n_phases
        else:
            if result < 1:
                # For 2-phase delta, next phase will be 3rd phase.
                result = 3


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
        """
        if self.y_prim_invalid:
            y_prim_series = matrix(0.0, (y_order, y_order))
            y_prim_shunt  = matrix(0.0, (y_order, y_order))
            y_prim        = matrix(0.0, (y_order, y_order))

        else: # Same size as last time; just zero out to start over.
            y_prim_series[:] = 0.0 # Zero out y_prim.
            y_prim_shunt[:]  = 0.0 # Zero out y_prim.
            y_prim[:]        = 0.0

        # Set frequency multipliers for this calculation.
        y_prim_freq = self.parent.frequency #TODO: Implement circuit frequency.
        if self.frequency != 0.0:
            freq_multiplier = y_prim_freq / self.frequency
        else:
            freq_multiplier = 1.0

        # Check for rebuilding Y_Terminal; Only rebuild if freq is different
        # than last time.
        if freq_multiplier != y_term_freqmult:
            self.calc_y_terminal(freq_multiplier)

        self.build_y_prim_component(y_prim_series, y_term)
        self.build_y_prim_component(y_prim_shunt, y_term_nl)

        self.add_neutral_to_y(freq_multiplier)

        # Combine the two y_prim components into y_prim.
        y_prim += y_prim_series
        y_prim += y_prim_shunt

        #TODO: Zero out rows and columns for open conductors.

        self.y_prim_invalid = False


    def build_y_prim_component(self, y_prim_component, y_terminal):

        # Now, Put in y_prim matrix.
        # Have to add every element of y_terminal into y_prim somewhere.
        n_windings2 = 2 * self.n_windings

        for i in range(n_windings2):
            for j in range(i):
                 value = y_terminal[i, j]
                 # This value goes in y_prim n_phases times.
                 for k in range(n_phases):
                     y_prim_component[term_ref[i + k * n_windings2],
                                      term_ref[j + k * n_windings2]] = value


class Line(ACLineSegment):
    """ A Line defaults to 3-phases and some typical symmetrical component
        data. Line impedances are specified in per unit length and are
        multiplied by the length when the primitive Y matrix is computed.
    """

    def recalc_element_data(self):

        n_phases = self.n_phases
        r1 = self.r1
        x1 = self.x1
        c1 = self.c1
        positive_sequence = self.parent.positive_sequence
        length = self.length

        z = matrix(0.0, (n_phases, n_phases))
        z_inv = matrix(0.0, (n_phases, n_phases))
        y_c = matrix(0.0, (n_phases, n_phases))

        ONETHIRD = 1.0 / 3.0

        # Only time this is called is if symmetrical components are specified.
        z_tmp = mul(complex(r1, x1), 2.0)

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


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
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


    def _clear_y_prim(self):
        """ Initialises the primitive Y matrix.
        """
        if self.y_prim_invalid:
            y_prim_series = matrix(0.0, (y_order, y_order))
            y_prim_shunt  = matrix(0.0, (y_order, y_order))
            y_prim        = matrix(0.0, (y_order, y_order))

        else:
            y_prim_series[:] = 0.0
            y_prim_shunt[:]  = 0.0
            y_prim[:]        = 0.0

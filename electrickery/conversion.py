# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Power conversion elements convert power from electrical form to some other
    form, or vice-versa.  Most will have only one connection to the power
    system and, therefore, only one multiphase terminal.
"""

from math import sqrt, pi, sin
import numpy
from cvxopt import matrix, spmatrix

from cim.core import ConductingEquipment
from cim.load_model import ConformLoad
from cim.wires import SynchronousMachine

INF = 1e08
SQRT3 = sqrt(3.0)

class Generator(SynchronousMachine):
    """ The generator is essentially a negative load that can be dispatched.

        If the dispatch value (disp_value) is 0, the generator always follows
        the appropriate dispatch curve, which are simply load curves.  If
        disp_value > 0 then the generator only comes on when the global circuit
        load multiplier exceeds disp_value.  When the generator is on, it
        always follows the dispatch curve appropriate for the type of solution
        being performed.

        If you want to model a generator that is fully on whenever it is
        dispatched on, simply designate status='fixed'.  The default is
        status='variable' (i.e., it follows a dispatch curve.  You could also
        define a dispatch curve that is always 1.0.

        The default is for the generator to be a current injection source.
        Thus, its primitive Y matrix contains only the impedance that might
        exist from the neutral of a wye-connected generator to ground.
        However, if the generator model is switched to 'admittance' from
        'power_flow', the generator is converted to an admittance and included
        in the system Y matrix.

        Generators are assumed balanced for the number of phases specified.
        If you would like unbalanced generators, enter separate single-phase
        generators.
    """

    def set_n_conds_for_connection(self):
        """ Sets the number of conductors according to the specified
            connection.
        """
        n_phases = self.n_phases

        if self.connection in ["wye"]:
            self.n_conds = n_phases + 1

        elif self.connection in ["delta"]:
            if n_phases == 1 or n_phases == 2:
                self.n_conds = n_phases + 1
            else:
                self.n_conds = n_phases

        else:
            raise


    def interpret_connection(self, s):
        """ Interprets the specified connection string.
        """
        kv_generator_base = self.base_voltage.nominal_voltage

        if s.lower() in ["wye"]:
            self.connection = "wye"

            if self.n_phases == 2 or self.n_phases == 3:
                self.v_base = v_base = kv_generator_base * 577.35 # L-N Volts

            else:
                self.v_base = v_base = kv_load_base * 1000.0

        elif s.lower() in ['delta']:
            self.connection = "delta"
            self.v_base = v_base = kv_load_base * 1000.0

        else:
            raise ValueError, "Unrecognised connection string."

        self.set_n_conds_for_connection()

        # v_base is always L-N voltage unless 1-phase device or more than
        # 3 phases.

        v_min_pu = self.base_voltage.voltage_level.low_voltage_limit
        v_max_pu = self.base_voltage.voltage_level.high_voltage_limit

        self.v_base_95  = v_min_pu * v_base
        self.v_base_105 = v_max_pu * v_base

        self.y_order = self.n_conds * len(self.terminals)
        self.y_prim_invalid = True


    def set_nominal_generation(self):
        gen_on_saved = self.gen_on #normally_in_service
        shape_factor = 1 + 1j

        self.gen_on == True # Initialise on then check if it should be off.
        dispatch_value = self.dispatch_value

        if self.dispatch_mode == 'load':
            reference = self.generator_dispatch_reference

            if (dispatch_value > 0.0) and (reference < dispatch_value):
                self.gen_on = False

        elif self.dispatch_mode == "price":
            price_signal = self.price_signal

            if (dispatch_value > 0.0) and (price_signal < dispatch_value):
                self.gen_on = False

        else:
            raise

        if not self.gen_on:
            # If Generator is OFF enter as tiny resistive load (.0001 pu) so we
            # don't get divide by zero in matrix.
            p_nominal_per_phase = -0.1 * kw_base / n_phases
            q_nominal_per_phase = 0.0
        else:
            # Compute generator nominal Watts and VArs.
            if self.fixed:
                # For fixed generators, set constant.
                factor = 1.0

            else:
                if mode in ["snapshot"]:
                    # TODO: Implement global generator multiplier.
                    factor = self.parent.gen_multiplier * 1.0

                elif mode in ["daily"]:
                    factor = self.parent.gen_multiplier
                    self._calc_daily_mult(dbl_hour)

                elif mode in ["yearly"]:
                    factor = self.parent.gen_multiplier
                    self._calc_yearly_mult(dbl_hour)

                else:
                    raise

        # If generator state changes, force re-calc of Y matrix.
        if self.gen_on != gen_on_saved:
            self.y_prim_invalid = True


    def recalc_element_data(self):
        """ Computes attributes for the generator.
        """
        n_phases = self.n_phases

        v_min_pu = self.base_voltage.voltage_level.low_voltage_limit
        v_max_pu = self.base_voltage.voltage_level.high_voltage_limit

        self.v_base_95  = v_min_pu * v_base
        self.v_base_105 = v_max_pu * v_base

        var_base = 1000.0 * kvar_base / n_phases;
        var_min  = 1000.0 * kvar_min  / n_phases;
        var_max  = 1000.0 * kvar_max  / n_phases;

        self.set_nominal_generation()

        y_qfixed = -var_base / v_base**2
        v_target = v_pu * 1000.0 * kv_generator_base / SQRT3


    def calc_y_prim_matrix(self, y_matrix):

        y_order = self.y_order

        y_prim_freq = self.parent.frequency
        freq_multiplier = y_prim_freq / self.frequency

        # Regular power flow generator model.
        # y_eq is always expected as the equivalent line-neutral admittance.
        y = -y_eq # Negate for generation.  y_eq is L-N quantity.
        y = complex(real(y), imag(y) / freq_multiplier)

        if self.connection in ["wye"]:
            y_ij = -y

            for i in range(n_phases):
                y_matrix[i, i] = y
                y_matrix[n_conds, n_conds] = y
                y_matrix[i, j] = y_ij
                y_matrix[j, i] = y_ij

        elif self.connection in ["delta", "L-L"]:
            y = div(y, 3.0) # Convert to delta impedance.
            y_ij = -y

            for i in range(n_phases):
                j = i + 1
                if j > n_conds:
                    j = 1 # Wrap around for closed connections.

                y_matrix[i, i] = y
                y_matrix[j, j] = y
                y_matrix[i, j] = y_ij
                y_matrix[j, i] = y_ij


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
        """
        y_order = self.y_order

        # Build only y_prim shunt for a Load then copy to y_prim.
        # Build a dummy y_prim series so that calc_v does not fail.
        if self.y_prim_invalid:
            y_prim_series = matrix(0.0, (y_order, y_order))
            y_prim_shunt  = matrix(0.0, (y_order, y_order))
            y_prim        = matrix(0.0, (y_order, y_order))

        else:
            y_prim_series[:] = 0.0
            y_prim_shunt[:]  = 0.0
            y_prim[:]        = 0.0

        if self.parent.solution.load_model == "power flow":
            self.set_nominal_load() # Same as admittance model.
            self.calc_y_prim_matrix(y_prim_shunt)
        else:
            # Admittance model wanted.
            self.set_nominal_load()
            self.calc_y_prim_matrix(y_prim_shunt)

        # Set y_prim_series based on diagonals of y_prim_shunt so that
        # calc_voltages() doesn't fail.
        for i in range(y_order):
#            value = mul(y_prim_shunt[i, i], 1.0e-10)
            y_prim_series[i, i] *= 1.0e-10

        self.y_prim = matrix(y_prim_shunt)

        # TODO: Account for open conductors.


class Load(ConformLoad):
    """ The load is assumed balanced over the no. of phases defined. To model
        unbalanced loads, define separate single-phase loads.

        ConformLoad represent loads that follow a daily load change pattern
        where the pattern can be used to scale the load with a system load.
    """

    @property
    def n_phases(self):
        """ Number of phases. For 3 or less, phase shift is 120 degrees.
            Valid 'phase' values are: "BCN", "ACN", "AB", "A", "B", "ABCN",
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

    # Nominal rated (1.0 per unit) voltage, kV, for load. For 2- and 3-phase
    # loads, specify phase-phase kV.  Otherwise, specify actual kV across each
    # branch of the load.  If wye (star), specify phase-neutral kV.  If delta
    # or phase-phase connected, specify phase-phase kV.
#    nominal_voltage = 12.47

    # Total base kW for the load.  Normally, you would enter the maximum kW for
    # the load for the first year and allow it to be adjusted by the load
    # shapes, growth shapes, and global load multiplier.
    # Legal ways to define base load:
    #    kW, PF
    #    kW, kvar
    #    kVA, PF
    pfixed = 10.0

    def interpret_connection(self, s):
        kv_load_base = self.base_voltage.nominal_voltage

        if s.lower() in ["wye"]:
            if self.n_phases == 2 or self.n_phases == 3:
                self.v_base = v_base = kv_load_base * 577.35
            else:
                self.v_base = v_base = kv_load_base * 1000.0

        elif s.lower() in ['delta']:
            self.v_base = v_base = kv_load_base * 1000.0

        else:
            raise ValueError, "Unrecognised connection string."

        v_min_pu = self.base_voltage.voltage_level.low_voltage_limit
        v_max_pu = self.base_voltage.voltage_level.high_voltage_limit

        self.v_base_95  = v_min_pu * v_base
        self.v_base_105 = v_max_pu * v_base

        self.y_order = self.n_conds * len(self.terminals)
        self.y_prim_invalid = True


    def set_nominal_load(self):
        """ Sets the nominal rating for the load.
        """
        if self.fixed:
            # For fixed loads, consider only growth factor.
            factor = self._growth_factor(year)
        else:
            if self.mode in ["Snapshot", "Harmonic", "Dynamic"]:
                if self.exempt_from_ld_curve:
                    factor = self._growth_factor(year)

                else:
                    load_multiplier = self.parent.load_multiplier
                    factor = load_multiplier * self._growth_factor(year)

            elif self.mode in ["Daily"]:
                if self.exempt_from_ld_curve:
                    factor = self._growth_factor(year)
                    self.calc_daily_mult(dbl_hour)

                else:
                    load_multiplier = self.parent.load_multiplier
                    factor = load_multiplier * self._growth_factor(year)

                    self._calc_daily_mult(dbl_hour)

            elif self.mode in ["Yearly"]:
                load_multiplier = self.parent.load_multiplier
                factor = load_multiplier * self._growth_factor(year)

                self._calc_yearly_mult(hour)

            else:
                # Defaults to Base kW * growth.
                factor = self._growth_factor(year)

        w_nominal   = 1e3 * kw_base * factor * real(shape_factor) / n_phases
        var_nominal = 1e3 * kvar_base * factor * imag(shape_factor) / n_phases

        self.y_eq = y_eq = div(complex(w_nominal, -var_nominal), v_base**2)

        # At 95% voltage.
        if v_min_pu != 0.0:
            y_eq_95 = div(y_eq, v_min_pu**2)
        else:
            y_eq_95 = 0.0 + 0.0j # CZERO

        # At 105% voltage.
        if v_max_pu != 0.0:
            y_eq_105 = div(y_eq, v_max_pu**2)
        else:
            y_eq_105 = y_eq


    def _calc_daily_mult(self, hour):
        """ Returns the daily multiplier for the specified hour.
        """
        if self.daily_shape is not None:
            shape_factor = self.daily_shape.get_mult(hour)
        else:
            # Default to no daily variation.
            shape_factor = 1.0 + 1.0j

        return shape_factor


    def _calc_yearly_mult(self, hour):
        """ Yearly curve is assumed to be hourly only.
        """
        if self.yearly_shape is not None:
            shape_factor = self.yearly_shape.get_mult(hour)
        else:
            # Default to no yearly variation.
            shape_factor = 1.0 + 1.0j

        return shape_factor


    def _growth_factor(self, year):
        """ Returns the growth factor for the specified year.
        """
        if year == 0:
            # Default all to 1 in year 0; use base values.
            last_growth_factor = 1.0
        else:
            if self.load_response is None:
                # TODO: Implement circuit 'default_growth_factor'.
                last_growth_factor = self.parent.default_growth_factor
            elif year != self.last_year:
                load_growth_factor = self.load_response.get_mult(year)

        return load_growth_factor


    def recalc_element_data(self):
        """ Computes the attributes of the load.
        """
        n_phases = self.n_phases

        v_min_pu = self.base_voltage.voltage_level.low_voltage_limit
        v_max_pu = self.base_voltage.voltage_level.high_voltage_limit

        self.v_base_95  = v_min_pu * v_base
        self.v_base_105 = v_max_pu * v_base

        # Set kW and kvar from root values of kVA and PF.

        if self.spec_type == "kW, PF":
            kvar_base = kw_base * sqrt(1.0 / self.pf_nominal**2 - 1.0)
            if self.pf_nominal < 0.0:
                kvar_base = -kvar_base

        elif self.spec_type == "kW, kVAr": # Need to set 'pf_nominal'.
            kva_base = sqrt(kw_base**2 + kvar_base**2)
            if kva_base > 0.0:
                pf_nominal = kw_base / kva_base

                if kvar_base != 0.0:
                    # cmp(x, 0) returns -1 if x < 0 ...
                    pf_nominal = pf_nominal * float(cmp(kw_base * kvar_base, 0))

        elif self.spec_type == "kVA, PF":
            kw_base = kva_base * abs(pf_nominal)
            kvar_base = kw_base * sqrt(1.0 / pf_nominal**2 - 1.0)
            if pf_nominal < 0.0:
                kvar_base = -kvar_base
        elif self.spec_type == "Compute allocated load":
            # TODO: Do automatically in property set.
            pass
        else:
            raise

        var_base = 1000.0 * kvar_base / n_phases;
        y_qfixed = -var_base / v_base**2


    def calc_y_prim_matrix(self, y_matrix):

        y_prim_freq = self.parent.frequency #TODO: Implement circuit frequency.
        freq_multiplier = y_prim_freq / self.frequency

        y = self.y_eq
        y = complex(real(y), imag(y) / freq_multiplier)
        y_ij = -y

        if s.lower() in ["wye"]:
            for i in range(n_phases):
                y_matrix[i, i] = y
                y_matrix[n_conds, n_conds] = y
                y_matrix[i, n_conds] = y_ij
                y_matrix[n_conds, i] = y_ij

            y_matrix[n_conds, n_conds, self.y_neut] # neutral

            # If neutral is floating, make sure there is some small connection
            # to ground  by increasing the last diagonal slightly.
            if self.r_neut < 0.0:
#                neut = mul(y_matrix[n_conds, n_conds], 1.000001)
                y_matrix[n_conds, n_conds] *= 1.000001

        elif s.lower() in ['delta']:
            for i in range(n_phases):
                j = i + 1
                if j > n_conds:
                    # Wrap around for closed connections.
                    j = 1

                y_matrix[i, i] = y
                y_matrix[j, j] = y
                y_matrix[i, j] = y_ij
                y_matrix[j, i] = y_ij # Set both off-diagonal elements.

        else:
            raise


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
        """
        y_order = self.y_order

        # Build only y_prim shunt for a Load then copy to y_prim.
        # Build a dummy y_prim series so that calc_v does not fail.
        if self.y_prim_invalid:
            y_prim_series = matrix(0.0, (y_order, y_order))
            y_prim_shunt  = matrix(0.0, (y_order, y_order))
            y_prim        = matrix(0.0, (y_order, y_order))

        else:
            y_prim_series[:] = 0.0
            y_prim_shunt[:]  = 0.0
            y_prim[:]        = 0.0

        if self.parent.solution.load_model == "power flow":
            self.set_nominal_load() # Same as admittance model.
            self.calc_y_prim_matrix(y_prim_shunt)
        else:
            # Admittance model wanted.
            self.set_nominal_load()
            self.calc_y_prim_matrix(y_prim_shunt)

        # Set y_prim_series based on diagonals of y_prim_shunt so that
        # calc_voltages() doesn't fail.
        for i in range(y_order):
#            value = mul(y_prim_shunt[i, i], 1.0e-10)
            y_prim_series[i, i] *= 1.0e-10

        self.y_prim = matrix(y_prim_shunt)

        # TODO: Account for open conductors.


class ISource(ConductingEquipment):
    """ Ideal current source.
    """

    # Magnitude of current source, each phase, in Amps.
    amps = 0.0

    # Phase angle in degrees of first phase. Phase shift between phases is
    # assumed 120 degrees when number of phases <= 3
    angle = 0.0

    # Source frequency.  Defaults to  circuit fundamental frequency.
    frequency = 60.0

    @property
    def n_phases(self):
        """ Number of phases. For 3 or less, phase shift is 120 degrees.
            Valid 'phase' values are: "BCN", "ACN", "AB", "A", "B", "ABCN",
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

    # Injection current.
    inj_current = 0.0

    def __init__(self, phases="ABCN", normally_in_service=True, **kw_args):
        self.phases = phases
        self.normally_in_service = normally_in_service # enabled
        super(ISource, self).__init__(phases=phases,
            normally_in_service=normally_in_service, **kw_args)


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
        """
        n_phases = self.n_phases
        y_order = n_phases * len(self.terminals)

        self.y_prim_series = matrix(0.0, (y_order, y_order))
        self.y_prim        = matrix(0.0, (y_order, y_order))

        # Yprim = 0 for Ideal Current Source; just leave it zeroed
        # TODO: Zero out rows and columns for open conductors.

        self.y_prim_invalid = False


class VSource(ConductingEquipment):
    """ This is a special power conversion element.  It is special because
        voltage sources must be identified to initialize the solution with all
        other injection sources set to zero.

        A Vsource object is simply a multi-phase Thevenin equivalent with data
        specified as it would commonly be for a power system source equivalent:
        Line-line voltage (kV) and short circuit MVA.
    """

    # Base Frequency Series Z matrix
    z = matrix

    z_inv = matrix

    @property
    def n_phases(self):
        """ Number of phases. For 3 or less, phase shift is 120 degrees.
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

    # Short circuit impedance type. Possible values are: MVAsc, Isc, Specified
    spec_type = "MVAsc"

    # 3-phase short circuit MVA = kVBase^^2 / Zsc
    mva_sc3 = 2000.0

    # 1-phase short circuit MVA. Unless a neutral reactor is used, it should be
    # a number on the same order of magnitude as MVAsc3.
    mva_sc1 = 2100.0

    # Alternate method of defining the source impedance. Positive-sequence
    # resistance, ohms.
    r1 = 1.65

    # Alternate method of defining the source impedance. Positive-sequence
    # reactance, ohms.
    x1 = 6.6

    @property
    def x1r1(self):
        """ Positive-sequence X/R ratio.
        """
        if self.r1 != 0.0:
            return self.x1 / self.r1
        else:
            return INF

    # Alternate method of defining the source impedance. Zero-sequence
    # resistance, ohms.
    r0 = 1.9

    # Alternate method of defining the source impedance. Zero-sequence
    # reactance, ohms.
    x0 = 5.7

    # Alternate method of defining the source impedance. 3-phase short circuit
    # current, amps.
    i_sc3 = 10000.0

    # Alternate method of defining the source impedance. Single-phase short
    # circuit current, amps.
    i_sc1 = 10500.0

    @property
    def x0r0(self):
        """ Zero-sequence X/R ratio.
        """
        if self.r0 != 0.0:
            return self.x0 / self.r0
        else:
            return INF

    # Per unit of the base voltage that the source is actually operating at.
    # Assumed balanced for all phases.
    pu = 1.0

    # Source frequency.
    frequency = 60.0

    y_prim_invalid = True


    def __init__(self, phases="ABCN", base_voltage=None, **kw_args):
        self.phases = phases
        if base_voltage is None:
            self.base_voltage = BaseVoltage(nominal_voltage=115.0)
        else:
            self.base_voltage = base_voltage

        super(VSource, self).__init__(phases=phases, base_voltage=base_voltage,
            **kw_args)


    def recalc_element_data(self):
        """ Computes the attributes of the source.
        """
        r1 = self.r1
        x1 = self.x1
        r0 = self.r0
        x0 = self.x0
        n_phases = self.n_phases

        # For a Source, n_phases = n_cond
        self.z = z = matrix(0.0+0.0j, (n_phases, n_phases))
        self.z_inv = z_inv = matrix(0.0+0.0j, (n_phases, n_phases))

        if n_phases == 1:
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

            if n_phases == 1:
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

        for i in range(n_phases):
            z[i, i] = zs
            for j in range(i - 1):
                z[i, j] = zm
                z[j, i] = zm

        if n_phases == 1:
            self.v_mag = kv_base * self.pu * 1000.0
        else:
            self.v_mag = kv_base * self.pu * 1000.0 \
                / (2.0 / (sin( (180.0 / n_phases) * pi / 180.0 )))


    def calc_y_prim(self):
        """ Returns the primitive Y (admittance) matrix for this element alone.
        """
        z_inv = self.z_inv
        n_phases = self.n_phases

        if self.y_prim_invalid: # Build only YPrim Series
            y_order = n_phases * 2#len(self.terminals)

            self.y_prim_series = matrix(0.0+0.0j, (y_order, y_order))
            self.y_prim        = matrix(0.0+0.0j, (y_order, y_order))
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

        y_prim = matrix( self.y_prim_series )

        #TODO: Zero out rows and columns for open conductors.

        self.y_prim_invalid = False

        return y_prim

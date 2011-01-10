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

class Transformer(PowerDeliveryElement):
    """The Transfomer model is implemented as a multi-terminal (two or more)
    power delivery element.

    A transfomer consists of two or more Windings, connected in somewhat
    arbitray fashion (with the standard Wye-Delta defaults, of course).

    You can specify the parameters of a winding one
    winding at a time or use arrays to set all the values.  Use the 'wdg=...'
    parameter to select a winding.  Transformers have one or more phases.  The
    number of conductors per terminal is always one more than the number of
    phases.  For wye- or star-connected windings, the extra conductor is the
    neutral point.  For delta-connected windings, the extra terminal is open
    internally (you normally leave this connected to node 0).
    """

    def __init__(self, windings=0, wdg=0, bus='', conn="Wye", kV=0.0, kVA=0.0,
            tap=0.0, rPct=0.0, rNeut=0.0, xNeut=0.0, buses='', conns="Wye",
            kVs=0.0, kVAs=0.0, taps=0.0, xHL=0.0, xHT=0.0, xLT=0.0,
            xSCArray=0.0, thermal=0.0, n=0.0, m=0.0, fLRise=0.0, hSRise=0.0,
            pctLoadLoss=0.0, pctNoLoadLoss=0.0, normHKVa=0.0, emergHKVa=0.0,
            substation=False, maxTap=0.0, minTap=0.0, numTaps=0, subName='',
            pctImage=0.0, ppmAntiFloat=0.0, *args, **kw_args):
        """Initialises a new 'Transformer' instance.
        """
        #: Number of windings, this transformers. (Also is the number of
        #  terminals)
        self.windings = windings

        #: Set this = to the number of the winding you wish to define.  Then
        #  set the values for this winding.  Repeat for each winding.
        #  Alternatively, use the array collections (buses, kvas, etc.) to
        #  define the windings. Note: impedances are BETWEEN pairs of windings;
        #  they are not the property of a single winding.
        self.wdg = wdg

        #: Bus to which this winding is connected.
        self.bus = bus

        #: Connection of this winding. Default is 'wye' with the neutral
        #  solidly grounded. Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating
        #  of the actual winding.
        self.kV = kV

        #: Base kVA rating of the winding. Side effect: forces change of max
        #  normal and emerg kva ratings.
        self.kVA = kVA

        #: Per unit tap that this winding is on.
        self.tap = tap

        #: Percent resistance this winding.  (half of total for a 2-winding).
        self.rPct = rPct

        #: Neutral resistance of wye (star)-connected winding in actual ohms.
        #  If entered as a negative value, the neutral is assumed to be open,
        #  or floating.
        self.rNeut = rNeut

        #: Neutral reactance of wye(star)-connected winding in actual ohms.
        #  May be + or -.
        self.xNeut = xNeut

        #: Use this to specify all the bus connections at once using an array.
        self.buses = buses

        #: Use this to specify all the Winding connections at once using an
        #  array. Values are: "Wye", "LN", "Delta", "LL"
        self.conns = conns

        #: Use this to specify the kV ratings of all windings at once using an
        #  array.
        self.kVs = kVs

        #: Use this to specify the kVA ratings of all windings at once using an
        #  array.
        self.kVAs = kVAs

        #: Use this to specify the p.u. tap of all windings at once using an
        #  array.
        self.taps = taps

        #: Use this to specify the percent reactance, H-L (winding 1 to
        #  winding 2).  Use for 2- or 3-winding transformers. On the kva base
        #  of winding 1.
        self.xHL = xHL

        #: Use this to specify the percent reactance, H-T (winding 1 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xHT = xHT

        #: Use this to specify the percent reactance, L-T (winding 2 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xLT = xLT

        #: Use this to specify the percent reactance between all pairs of
        #  windings as an array.  All values are on the kVA base of winding 1.
        #  The order of the values is as follows:  (x12 13 14... 23 24.. 34 ..)
        #  There will be n(n-1)/2 values, where n=number of windings.
        self.xSCArray = xSCArray

        #: Thermal time constant of the transformer in hours.  Typically
        #  about 2.
        self.thermal = thermal

        #: n Exponent for thermal properties in IEEE C57.  Typically 0.8.
        self.n = n

        #: m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0
        self.m = m

        #: Temperature rise, deg C, for full load.
        self.fLRise = fLRise

        #: Hot spot temperature rise, deg C.
        self.hSRise = hSRise

        #: Percent load loss at full load. The %R of the High and Low windings
        #  (1 and 2) are adjusted to agree at rated kVA loading.
        self.pctLoadLoss = pctLoadLoss

        #: Percent no load losses at rated excitation voltage. Converts to a
        #  resistance in parallel with the magnetising impedance in each
        #  winding.
        self.pctNoLoadLoss = pctNoLoadLoss

        #: Normal maximum kVA rating of H winding (winding 1).  Usually
        #  100% - 110% of maximum nameplate rating, depending on load shape.
        #  Defaults to 110% of kVA rating of Winding 1.
        self.normHKVa = normHKVa

        #: Emergency (contingency)  kVA rating of H winding (winding 1).
        #  Usually 140% - 150% of maximum nameplate rating, depending on load
        #  shape. Defaults to 150% of kVA rating of Winding 1.
        self.emergHKVa = emergHKVa

        #: Designates whether this transformer is to be considered a
        #  substation.
        self.substation = substation

        #: Max per unit tap for the active winding.
        self.maxTap = maxTap

        #: Min per unit tap for the active winding.
        self.minTap = minTap

        #: Total number of taps between min and max tap.
        self.numTaps = numTaps

        #: Substation Name. Optional. If specified, printed on plots.
        self.subName = subName

        #: Percent magnetizing current. Default=0.0. Magnetizing branch is in
        #  parallel with windings in each phase. Also, see 'ppm_antifloat'.
        self.pctImage = pctImage

        #: Parts per million by which the reactive term is increased to protect
        #  against accidentally floating a winding.  If positive then the
        #  effect is adding a small reactor to ground. If negative, then a
        #  capacitor.
        self.ppmAntiFloat = ppmAntiFloat

        super(Transformer, self).__init__(*args, **kw_args)


    def recalcElementData(self):

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


    def calcYTerminal(self, freq_mult):
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


    def _setTermRef(self):
        """Sets an array which maps the two conductors of each winding to the
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


    def rotatePhases(self, idx_phase):
        """For Delta connections or Line-Line voltages begin.
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


    def calcYPrim(self):
        """Returns the primitive Y (admittance) matrix for this element alone.
        """
        if self.y_prim_invalid:
            y_prim_series = sparse((y_order, y_order))
            y_prim_shunt  = sparse((y_order, y_order))
            y_prim        = sparse((y_order, y_order))

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


    def buildYPrimComponent(self, y_prim_component, y_terminal):

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

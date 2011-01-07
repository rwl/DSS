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

from dss.control.ControlElement import ControlElement

class Relay(ControlElement):
    """A Relay is a control element that is connected to a terminal of a
    circuit element and controls the switches in the same or another terminal.
    The control is usually placed in the terminal of a line or transformer,
    but it could be any element.  Voltage relay is a definite time relay that
    operates after the voltage stays out of bounds for a fixed time interval.
    It will then reclose a set time after the voltage comes back in the normal
    range.
    """

    def __init__(self, monitoredObj, monitoredTerm=0, switchedObj='',
            switchedTerm=0, type="Current", phaseCurve='', groundCurve='',
            phaseTrip=0.0, groundTrip=0.0, tDPhase=0.0, tDGround=0.0,
            phaseInst=0.0, groundInst=0.0, reset=0.0, shots=0,
            recloseIntervals=0.0, delay=0.0, kVBase=0.0, pctPickup47=0.0,
            pctAmps46=0.0, pctPickup46=0.0, iSQT46=0.0, variable='',
            overtrip=0.0, undertrip=0.0, breakerTime=0.0, action="Trip_Open",
            overvoltCurve=None, undervoltCurve=None, *args, **kw_args):
        """Initialises a new 'Relay' instance.
        """
        #: Full object name of the circuit element, typically a line,
        #  transformer, load, or generator, to which the relay's PT and/or
        #  CT are connected. This is the 'monitored' element. There is no
        #  default; must be specified.
        self.monitoredObj = monitoredObj

        #: Number of the terminal of the circuit element to which the Relay is
        #  connected. 1 or 2, typically.
        self.monitoredTerm = monitoredTerm

        #: Name of circuit element switch that the Relay controls. Specify the
        #  full object name. Defaults to the same as the Monitored element.
        #  This is the 'controlled' element.
        self.switchedObj = switchedObj

        #: Number of the terminal of the controlled element in which the switch
        #  is controlled by the Relay. 1 or 2, typically.
        self.switchedTerm = switchedTerm

        #: One of a legal relay type:
        #      Current Voltage Reversepower 46 (neg seq current)
        #      47 (neg seq voltage)
        #      Generic (generic over/under relay)
        #  Default is overcurrent relay (Current) Specify the curve and pickup
        #  settings appropriate for each type. Generic relays monitor PC
        #  Element Control variables and trip on     out of over/under range in
        #  definite time. Values are: "Current", "Fortyseven", "Generic"
        self.type = type

        #: Name of the TCC Curve object that determines the phase trip.  Must
        #  have been previously defined as a TCC_Curve object. Default is none
        #  (ignored).  For overcurrent relay, multiplying the current values in
        #  the curve by the 'phasetrip' value gives the actual current.
        self.phaseCurve = phaseCurve

        #: Name of the TCC Curve object that determines the ground trip. Must
        #  have been previously defined as a TCC_Curve object. For overcurrent
        #  relay, multiplying the current values in the curve by the
        #  'groundtrip' value gives the actual current.
        self.groundCurve = groundCurve

        #: Multiplier or actual phase amps for the phase TCC curve.
        self.phaseTrip = phaseTrip

        #: Multiplier or actual ground amps (3I0) for the ground TCC curve.
        self.groundTrip = groundTrip

        #: Time dial for Phase trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDPhase = tDPhase

        #: Time dial for Ground trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDGround = tDGround

        #: Actual amps (Current relay) or kW (reverse power relay) for
        #  instantaneous phase trip which is assumed to happen in 0.01 sec +
        #  Delay Time. Default is 0.0, which signifies no inst trip. Use this
        #  value for specifying the Reverse Power threshold (kW) for reverse
        #  power relays.
        self.phaseInst = phaseInst

        #: Actual  amps for instantaneous ground trip which is assumed to
        #  happen in 0.01 sec + Delay Time.Default is 0.0, which signifies
        #  no inst trip.
        self.groundInst = groundInst

        #: Reset time in sec for relay.
        self.reset = reset

        #: Number of shots to lockout. This is one more than the number of
        #  reclose intervals.
        self.shots = shots

        #: Array of reclose intervals. If none, specify 'NONE'. Default for
        #  overcurrent relay is (0.5, 2.0, 2.0) seconds. Default for a voltage
        #  relay is (5.0). In a voltage relay, this is  seconds after
        #  restoration of voltage that the reclose occurs. Reverse power relay
        #  is one shot to lockout, so this is ignored.  A locked out relay must
        #  be closed manually (set action=close).
        self.recloseIntervals = recloseIntervals

        #: Trip time delay (sec) for definite time relays. Default is 0.0 for
        #  current and voltage relays.  If >0 then this value is used instead
        #  of curves.  Used exclusively by RevPower, 46 and 47 relays at this
        #  release.  Defaults to 0.1 s for these relays.
        self.delay = delay

        #: Voltage base (kV) for the relay. Specify line-line for 3 phase
        #  devices); line-neutral for 1-phase devices.  Relay assumes the
        #  number of phases of the monitored element.  Default is 0.0, which
        #  results in assuming the voltage values in the 'TCC' curve are
        #  specified in actual line-to-neutral volts.
        self.kVBase = kVBase

        #: Percent voltage pickup for 47 relay (Neg seq voltage). Specify also
        #  base voltage (kvbase) and delay time value.
        self.pctPickup47 = pctPickup47

        #: Base current, Amps, for 46 relay (neg seq current). Used for
        #  establishing pickup and per unit I-squared-t.
        self.pctAmps46 = pctAmps46

        #: Percent pickup current for 46 relay (neg seq current). When current
        #  exceeds this value * BaseAmps, I-squared-t calc starts.
        self.pctPickup46 = pctPickup46

        #: Negative Sequence I-squared-t trip value for 46 relay (neg seq
        #  current).  Default is 1 (trips in 1 sec for 1 per unit neg seq
        #  current).  Should be 1 to 99.
        self.iSQT46 = iSQT46

        #: Name of variable in PC Elements being monitored.  Only applies to
        #  Generic relay.
        self.variable = variable

        #: Trip setting (high value) for Generic relay variable. Relay trips in
        #  definite time if value of variable exceeds this value.
        self.overtrip = overtrip

        #: Trip setting (low value) for Generic relay variable.  Relay trips in
        #  definite time if value of variable is less than this value.
        self.undertrip = undertrip

        #: Fixed delay time (sec) added to relay time. Designed to represent
        #  breaker time or some other delay after a trip decision is made.Use
        #  Delay_Time property for setting a fixed trip time delay.Added to
        #  trip time of current and voltage relays. Could use in combination
        #  with inst trip value to obtain a definite time overcurrent relay.
        self.breakerTime = breakerTime

        #: Action that overrides the relay control. Simulates manual control on
        #  breaker. 'Trip' or 'Open' causes the controlled element to open and
        #  lock out. 'Close' causes the controlled element to close and the
        #  relay to reset to its first operation. Values are: "Trip_Open",
        #  "Close"
        self.action = action

        #: TCC Curve object to use for overvoltage relay.  Curve is assumed to
        #  be defined with per unit voltage values. Voltage base should be
        #  defined for the relay.
        self.overvoltCurve = overvoltCurve

        #: TCC Curve object to use for undervoltage relay.  Curve is assumed to
        #  be defined with per unit voltage values. Voltage base should be
        #  defined for the relay.
        self.undervoltCurve = undervoltCurve

        super(Relay, self).__init__(*args, **kw_args)

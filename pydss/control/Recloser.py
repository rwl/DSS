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

from pydss.control.ControlElement import ControlElement

class Recloser(ControlElement):
    """A Recloser is a control element that is connected to a terminal of a
    circuit element and controls the switches in the same or another terminal.
    The control is usually placed in the terminal of a line or transformer, but
    it could be any element  CktElement to be controlled must already exist.
    """

    def __init__(self, monitoredObj, monitoredTerm=0, switchedObj='',
            switchedTerm=0, nFast=0, phaseFast='', phaseDelayed='',
            groundFast='', groundDelayed='', phaseTrip=0.0, groundTrip=0.0,
            phaseInst=0.0, groundInst=0.0, reset=0.0, shots=0,
            recloseIntervals=0.0, delay=0.0, action="Trip_Open",
            tDPhFast=0.0, tDGrFast=0.0, tDPhDelayed=0.0, tDGrDelayed=0.0,
            *args, **kw_args):
        """Initialises a new 'Recloser' instance.
        """
        #: Full object name of the circuit element, typically a line,
        #  transformer, load, or generator, to which the Recloser's PT and/or
        #  CT are connected.  This is the 'monitored' element.
        self.monitoredObj = monitoredObj

        #: Number of the terminal of the circuit element to which the Recloser
        #  is connected. 1 or 2, typically.
        self.monitoredTerm = monitoredTerm

        #: Name of circuit element switch that the Recloser controls. Specify
        #  the full object name.  Defaults to the same as the Monitored
        #  element. This is the 'controlled' element.
        self.switchedObj = switchedObj

        #: Number of the terminal of the controlled element in which the switch
        #  is controlled by the Recloser. 1 or 2, typically.
        self.switchedTerm = switchedTerm

        #: Number of Fast (fuse saving) operations. (See 'Shots')
        self.nFast = nFast

        #: Name of the TCC Curve object that determines the Phase Fast trip.
        #  Must have been previously defined as a TCC_Curve object. Default
        #  is 'A'.  Multiplying the current values in the curve by the
        #  'phasetrip' value gives the actual current.
        self.phaseFast = phaseFast

        #: Name of the TCC Curve object that determines the Phase Delayed trip.
        #  Must have been previously defined as a TCC_Curve object. Default is
        #  'D'.  Multiplying the current values in the curve by the 'phasetrip'
        #  value gives the actual current.
        self.phaseDelayed = phaseDelayed

        #: Name of the TCC Curve object that determines the Ground Fast trip.
        #  Must have been previously defined as a TCC_Curve object. Multiplying
        #  the current values in the curve by the 'groundtrip' value gives the
        #  actual current.
        self.groundFast = groundFast

        #: Name of the TCC Curve object that determines the Ground Delayed
        #  trip.  Must have been previously defined as a TCC_Curve object.
        #  Multiplying the current values in the curve by the 'groundtrip'
        #  value gives the actual current.
        self.groundDelayed = groundDelayed

        self.phaseTrip = phaseTrip

        #: Multiplier or actual ground amps (3I0) for the ground TCC curve.
        self.groundTrip = groundTrip

        #: Multiplier or actual phase amps for the phase TCC curve.
        self.phaseInst = phaseInst

        #: Actual amps for instantaneous ground trip which is assumed to happen
        #  in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst
        #  trip.
        self.groundInst = groundInst

        #: Reset time in sec for Recloser.
        self.reset = reset

        #: Total Number of fast and delayed shots to lockout. This is one more
        #  than the number of reclose intervals.
        self.shots = shots

        #: Array of reclose intervals.  Default for Recloser is (0.5, 2.0, 2.0)
        #  seconds. A locked out Recloser must be closed manually
        #  (action=close).
        self.recloseIntervals = recloseIntervals

        #: Fixed delay time (sec) added to Recloser trip time. Used to
        #  represent breaker time or any other delay.
        self.delay = delay

        #: Action that overrides the Recloser control.  Simulates manual
        #  control on recloser 'Trip' or 'Open' causes the controlled element
        #  to open and lock out. 'Close' causes the controlled element to close
        #  and the Recloser to reset to its first operation. Values are:
        #  "Trip_Open", "Close"
        self.action = action

        #: Time dial for Phase Fast trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDPhFast = tDPhFast

        #: Time dial for Ground Fast trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDGrFast = tDGrFast

        #: Time dial for Phase Delayed trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDPhDelayed = tDPhDelayed

        #: Time dial for Ground Delayed trip curve. Multiplier on time axis of
        #  specified curve.
        self.tDGrDelayed = tDGrDelayed

        super(Recloser, self).__init__(*args, **kw_args)

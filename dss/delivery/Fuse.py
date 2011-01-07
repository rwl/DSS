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

class Fuse(PowerDeliveryElement):
    """A Fuse is a control element that is connected to a terminal of a circuit
    element and controls the switches in the same or another terminal.  The
    control is usually placed in the terminal of a line or transformer, but it
    could be any element.
    """

    def __init__(self, monitoredObj='', monitorTerm=0, switchedObj='',
            switchedTerm=0, fuseCurve='', ratedCurrent=0.0, delay=0.0,
            action="Trip_Open", *args, **kw_args):
        """Initialises a new 'Fuse' instance.
        """
        #: Full object name of the circuit element, typically a line,
        #  transformer, load, or generator, to which the Fuse is connected.
        #  This is the 'monitored' element. There is no default; must be
        #  specified.
        self.monitoredObj = monitoredObj

        #: Number of the terminal of the circuit element to which the Fuse is
        #  connected.  1 or 2, typically.
        self.monitorTerm = monitorTerm

        #: Name of circuit element switch that the Fuse controls. Specify the
        #  full object name. Defaults to the same as the Monitored element.
        #  This is the 'controlled' element.
        self.switchedObj = switchedObj

        #: Number of the terminal of the controlled element in which the switch
        #  is controlled by the Fuse. 1 or 2, typically.  Assumes all phases of
        #  the element have a fuse of this type.
        self.switchedTerm = switchedTerm

        #: Name of the TCC Curve object that determines the fuse blowing.  Must
        #  have been previously defined as a TCC_Curve object. Default is
        #  'Tlink'.  Multiplying the current values in the curve by the
        #  'RatedCurrent' value gives the actual current.
        self.fuseCurve = fuseCurve

        #: Multiplier or actual phase amps for the phase TCC curve.
        self.ratedCurrent = ratedCurrent

        #: Fixed delay time (sec) added to Fuse blowing time determined from
        #  the TCC curve. Used to represent fuse clearing time or any other
        #  delay.
        self.delay = delay

        #: Action that overrides the Fuse control. Simulates manual control on
        #  Fuse 'Trip' or 'Open' causes the controlled element to open and lock
        #  out. 'Close' causes the controlled element to close and the Fuse to
        #  reset.
        self.action = action

        super(Fuse, self).__init__(*args, **kw_args)

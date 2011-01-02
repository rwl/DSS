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

from pydss.meter.MeterElement import MeterElement

class Sensor(MeterElement):
    """A sensor.
    """

    def __init__(self, element='', terminal=0, modes=["Voltage"], v=0.0, i=0.0,
            p=0.0, q=0.0, phases=0, conn="Wye", pctError=0.0,
            action="SquareError", *args, **kw_args):
        """Initialises a new 'Sensor' instance.
        """
        #: Name (Full Object name) of element to which the Sensor is connected.
        self.element = element

        #: Number of the terminal of the circuit element to which the Sensor is
        #  connected. 1 or 2, typically. For Sensoring states, attach Sensor to
        #  terminal 1.
        self.terminal = terminal

        #: Array of any of { Voltage | Current | kW | kvar } in any order.
        #  Quantities being sensed. Scalar magnitudes only.
        self.modes = modes

        #: Array of Voltages (kV) measured by the voltage sensor.
        self.v = v

        #: Array of Currents (amps) measured by the current sensor.
        self.i = i

        #: Array of Active power (kW) measurements at the sensor.
        self.p = p

        #: Array of Reactive power (kvar) measurements at the sensor.
        self.q = q

        #: Array of phases being monitored by this sensor. [1, 2, 3] or
        #  [2 3 1] or [1], etc.  Corresponds to the order that the measurement
        #  arrays will be supplied. Defaults to same number of quantities as
        #  phases in the monitored element.
        self.phases = phases

        #: Applies to voltage measurement. If wye or LN, voltage is assumed
        #  measured line-neutral; otherwise, line-line. Values are: "Wye",
        #  "LN", "Delta", "LL"
        self.conn = conn

        #: Assumed percent error in the measurement.
        self.pctError = pctError

        #: Action options: SQERROR: Show square error of the present value of
        #  the monitored terminal quantity vs the sensor value. Actual values -
        #  convert to per unit in calling program.  Value reported in result
        #  window/result variable. Values are: "SquareError", "ActualValues"
        self.action = action

        super(Sensor, self).__init__(*args, **kw_args)

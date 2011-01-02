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

class RegulatorControl(ControlElement):
    """A RegulatorControl is a control element that is connected to a terminal
    of another circuit element that must be a transformer.
    """

    def __init__(self, transformer='', winding=0, vReg=0.0, band=0.0,
            pTRatio=0.0, cTPrim=0.0, r=0.0, x=0.0, bus='', delay=0.0,
            reversible=False, revVReg=0.0, revBand=0.0, revR=0.0, revX=0.0,
            tapDelay=0.0, debugTrace=False, maxTapChange=0, inverseTime=False,
            tapWinding=0, vLimit=0.0, *args, **kw_args):
        """Initialises a new 'RegulatorControl' instance.
        """
        #: Name of Transformer element to which the RegControl is connected.
        #  Do not specify the full object name; 'Transformer' is assumed for
        #  the object class.
        self.transformer = transformer

        #: Number of the winding of the transformer element that the RegControl
        #  is monitoring. 1 or 2, typically.  Side Effect: Sets TAPWINDING
        #  property to the same winding.
        self.winding = winding

        #: Voltage regulator setting, in VOLTS, for the winding being
        #  controlled.  Multiplying this value times the ptratio should yield
        #  the voltage across the WINDING of the controlled transformer.
        self.vReg = vReg

        #: Bandwidth in VOLTS for the controlled bus (see help for ptratio
        #  property)
        self.band = band

        #: Ratio of the PT that converts the controlled winding voltage to the
        #  regulator voltage. If the winding is Wye, the line-to-neutral
        #  voltage is used.  Else, the line-to-line voltage is used.
        self.pTRatio = pTRatio

        #: Rating, in Amperes, of the primary CT rating for converting the line
        #  amps to control amps.The typical default secondary ampere rating is
        #  5 Amps.
        self.cTPrim = cTPrim

        #: R setting on the line drop compensator in the regulator, expressed
        #  in VOLTS.
        self.r = r

        #: X setting on the line drop compensator in the regulator, expressed
        #  in VOLTS.
        self.x = x

        #: Name of a bus in the system to use as the controlled bus instead of
        #  the bus to which the winding is connected or the R and X line drop
        #  compensator settings.  Do not specify this value if you wish to use
        #  the line drop compensator settings.  Default is null string. Assumes
        #  the base voltage for this bus is the same as the transformer winding
        #  base specified above. Note: This bus WILL BE CREATED by the
        #  regulator control upon SOLVE if not defined by some other device.
        self.bus = bus

        #: Time delay, in seconds, from when the voltage goes out of band to
        #  when the tap changing begins. This is used to determine which
        #  regulator control will act first. You may specify any floating point
        #  number to achieve a model of whatever condition is necessary.
        self.delay = delay

        #: Indicates whether or not the regulator can be switched to regulate
        #  in the reverse direction. Default is No.Typically applies only to
        #  line regulators and not to LTC on a substation transformer.
        self.reversible = reversible

        #: Voltage setting in volts for operation in the reverse direction.
        self.revVReg = revVReg

        #: Bandwidth for operating in the reverse direction.
        self.revBand = revBand

        #: R line drop compensator setting for reverse direction.
        self.revR = revR

        #: X line drop compensator setting for reverse direction.
        self.revX = revX

        #: Delay in sec between tap changes. This is how long it takes between
        #  changes after the first change.
        self.tapDelay = tapDelay

        #: Turn this on to capture the progress of the regulator model for each
        #  control iteration.  Creates a separate file for each RegControl
        #  named 'REG_name.CSV'.
        self.debugTrace = debugTrace

        #: Maximum allowable tap change per control iteration in STATIC control
        #  mode. Set this to 1 to better approximate actual control action. Set
        #  this to 0 to fix the tap in the current position.
        self.maxTapChange = maxTapChange

        #: The time delay is adjusted inversely proportional to the amount the
        #  voltage is outside the band down to 10%.
        self.inverseTime = inverseTime

        #: Winding containing the actual taps, if different than the WINDING
        #  property. Defaults to the same winding as specified by the WINDING
        #  property.
        self.tapWinding = tapWinding

        #: Voltage Limit for bus to which regulated winding is connected
        #  (e.g. first customer). Default is 0.0. Set to a value greater
        #  then zero to activate this function.
        self.vLimit = vLimit

        super(RegulatorControl, self).__init__(*args, **kw_args)

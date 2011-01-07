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

class CapacitorControl(ControlElement):
    """A CapacitorControl is a control element that is connected to a terminal
    of another circuit element and controls a capacitor.  The control is
    usually placed in the terminal of a line or transformer, although a voltage
    control device could be placed in the terminal of the capacitor it
    controls.  Capacitor to be controlled must already exist.
    """

    def __init__(self, element='', terminal=0, capacitor='', type="Current",
            pTRatio=0.0, cTRatio=0.0, onSetting=0.0, offSetting=0.0, delay=0.0,
            voltOverride=False, vMax=0.0, vMin=0.0, delayOff=0.0, deadTime=0.0,
            *args, **kw_args):
        """Initialises a new 'CapacitorControl' instance.
        """
        #: Full object name of the circuit element, typically a line or
        #  transformer, to which the capacitor control's PT and/or CT are
        #  connected. There is no default; must be specified.
        self.element = element

        #: Number of the terminal of the circuit element to which the
        #  CapControl is connected. 1 or 2, typically.  Default is 1.
        self.terminal = terminal

        #: Name of Capacitor element which the CapControl controls. No Default;
        #  Must be specified.  Do not specify the full object name; 'Capacitor'
        #  is assumed for the object class.
        self.capacitor = capacitor

        #: Control type.  Specify the ONsetting and OFFsetting appropriately
        #  with the type of control. (See help for ONsetting) Values are:
        #  "Current", "Voltage", "kVAr", "Time"
        self.type = type

        #: Ratio of the PT that converts the monitored voltage to the control
        #  voltage. Default is 60.  If the capacitor is Wye, the 1st phase
        #  line-to-neutral voltage is monitored.  Else, the line-to-line
        #  voltage (1st - 2nd phase) is monitored.
        self.pTRatio = pTRatio

        #: Ratio of the CT from line amps to control ampere setting for current
        #  and kvar control types.
        self.cTRatio = cTRatio

        #: Value at which the control arms to switch the capacitor ON (or
        #  ratchet up a step).  Type of Control:
        #      Current: Line Amps / CTratio
        #      Voltage: Line-Neutral (or Line-Line for delta) Volts / PTratio
        #      kvar:    Total kvar, all phases (3-phase for pos seq model).
        #               This is directional.
        #      Time:    Hrs from Midnight as a floating point number (decimal).
        #               7:30am would be entered as 7.5.
        self.onSetting = onSetting

        #: Value at which the control arms to switch the capacitor OFF. (See
        #  help for ONsetting)
        self.offSetting = offSetting

        #: Time delay, in seconds, from when the control is armed before it
        #  sends out the switching command to turn ON.  The control may reset
        #  before the action actually occurs. This is used to determine which
        #  capacity control will act first. Default is 15.  You may specify
        #  any floating point number to achieve a model of whatever condition
        #  is necessary.
        self.delay = delay

        #: Switch to indicate whether VOLTAGE OVERRIDE is to be considered.
        #  Vmax and Vmin must be set to reasonable values if this property
        #  is Yes.
        self.voltOverride = voltOverride

        #: Maximum voltage, in volts.  If the voltage across the capacitor
        #  divided by the PTRATIO is greater than this voltage, the capacitor
        #  will switch OFF regardless of other control settings. Default is
        #  126 (goes with a PT ratio of 60 for 12.47 kV system).
        self.vMax = vMax

        #: Minimum voltage, in volts.  If the voltage across the capacitor
        #  divided by the PTRATIO is less than this voltage, the capacitor will
        #  switch ON regardless of other control settings. Default is 115 (goes
        #  with a PT ratio of 60 for 12.47 kV system).
        self.vMin = vMin

        #: Time delay, in seconds, for control to turn OFF when present state
        #  is ON.
        self.delayOff = delayOff

        #: Dead time after capacitor is turned OFF before it can be turned
        #  back ON.
        self.deadTime = deadTime

        super(CapacitorControl, self).__init__(*args, **kw_args)

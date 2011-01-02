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
from pydss.control.CapacitorControl import CapacitorControl
from pydss.control.GeneratorDispatcher import GeneratorDispatcher
from pydss.control.Recloser import Recloser
from pydss.control.RegulatorControl import RegulatorControl
from pydss.control.Relay import Relay
from pydss.control.StorageController import StorageController
from pydss.control.SwitchControl import SwitchControl

class controlType(str):
    """Values are: Current, Voltage, kVAr, Time
    """
    pass

class relayType(str):
    """Values are: Current, Fortyseven, Generic
    """
    pass

class dischargeMode(str):
    """Values are: peakShave, follow, support, loadShape, time
    """
    pass

class chargeMode(str):
    """Values are: loadShape, time
    """
    pass

class switchAction(str):
    """Values are: open, close
    """
    pass

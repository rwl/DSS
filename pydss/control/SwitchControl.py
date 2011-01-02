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

class SwitchControl(ControlElement):

    def __init__(self, action="open", lock=False, delay=0.0, *args, **kw_args):
        """Initialises a new 'SwitchControl' instance.
        """
        #: Simulates manual operation of the controlled switch to open or close, after a time delay. Values are: "open", "close"
        self.action = action

        #: Controlled switch is locked in its present open / close state. Switch will not respond to either manual or automatic control until this Lock is removed.
        self.lock = lock

        #: Operating time delay (sec) of the switch.
        self.delay = delay

        super(SwitchControl, self).__init__(*args, **kw_args)


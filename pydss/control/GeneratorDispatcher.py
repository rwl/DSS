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

class GeneratorDispatcher(ControlElement):
    """A GenDispatcher is a control element that is connected to a terminal of another circuit element and sends dispatch kW signals to a set of generators it controls.
    """

    def __init__(self, element='', terminal=0, kWLimit=0.0, kWBand=0.0, kVarLimit=0.0, weights=0.0, genList=None, *args, **kw_args):
        """Initialises a new 'GeneratorDispatcher' instance.
        """
        #: Full object name of the circuit element, typically a line or transformer, which the control is monitoring. There is no default; must be specified.
        self.element = element

        #: Number of the terminal of the circuit element to which the GenDispatcher control is connected. 1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.
        self.terminal = terminal

        #: kW Limit for the monitored element. The generators are dispatched to hold the power in band.the object class.
        self.kWLimit = kWLimit

        #: Bandwidth (kW) of the dead band around the target limit. No dispatch changes are attempted if the power in the monitored terminal stays within this band.
        self.kWBand = kWBand

        #: Max kvar to be delivered through the element.  Uses same dead band as kW.
        self.kVarLimit = kVarLimit

        #: Array of proportional weights corresponding to each generator in the gen_list. The needed kW to get back to center band is dispatched to each generator according to these weights. Default is to set all weights to 1.0.
        self.weights = weights

        self.genList = [] if genList is None else genList

        super(GeneratorDispatcher, self).__init__(*args, **kw_args)

    def add_genList(self, *genList):
        for obj in genList:
            self.genList.append(obj)

    def remove_genList(self, *genList):
        for obj in genList:
            self.genList.remove(obj)


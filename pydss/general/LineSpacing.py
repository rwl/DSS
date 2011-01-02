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

class LineSpacing(object):

    def __init__(self, nConds=0, nPhases=0, x=0.0, h=0.0, units="none"):
        """Initialises a new 'LineSpacing' instance.
        """
        #: Number of wires in this geometry. Default is 3.
        self.nConds = nConds

        #: Number of retained phase conductors. If less than the number of
        #  wires, list the retained phase coordinates first.
        self.nPhases = nPhases

        #: Array of wire X coordinates.
        self.x = x

        #: Array of wire Heights.
        self.h = h

        #: Units for x and h. Values are: "none", "mi", "km", "kft", "m", "me",
        #  "ft", "in", "cm"
        self.units = units

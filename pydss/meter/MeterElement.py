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

from pydss.common.CircuitElement import CircuitElement

class MeterElement(CircuitElement):
    """Base for all meters.
    """

    def __init__(self, elementName='', meteredTerminal=0, meteredElement=None,
            *args, **kw_args):
        self.elementName = elementName

        self.meteredTerminal = meteredTerminal

        self.meteredElement = meteredElement

        super(MeterElement, self).__init__(*args, **kw_args)

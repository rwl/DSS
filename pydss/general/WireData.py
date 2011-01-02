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

from pydss.common.Named import Named

class WireData(Named):
    """The WireData object is a general DSS object used by all circuits as a
    reference for obtaining line impedances.  This class of data defines the
    raw conductor data that is used to compute the impedance for a line
    geometry.  Note that you can use whatever units you want for any of the
    dimensional data - be sure to declare the units. Otherwise, the units are
    all assumed to match, which would be very rare for conductor data.
    Conductor data is usually supplied in a hodge-podge of units. Everything
    is converted to meters internally to the DSS.
    """

    def __init__(self, rDC=0.0, rAC=0.0, rUnits="none", gmrAC=0.0,
            gmrUnits="none", radius=0.0, radUnits="none", normAmps=0.0,
            emergAmps=0.0, diameter=0.0, *args, **kw_args):
        """Initialises a new 'WireData' instance.
        """
        #: DC resistance, ohms per unit length (see rUnits). Defaults to rAC if
        #  not specified.
        self.rDC = rDC

        #: Resistance at 60 Hz per unit length. Defaults to rDC if not
        #  specified.
        self.rAC = rAC

        #: Length units for resistance: ohms per {mi|kft|km|m|Ft|in|cm}
        self.rUnits = rUnits

        #: GMR at 60 Hz. Defaults to .7788*radius if not specified.
        self.gmrAC = gmrAC

        #: Units for GMR: {mi|kft|km|m|Ft|in|cm}
        self.gmrUnits = gmrUnits

        #: Outside radius of conductor. Defaults to GMR/0.7788 if not
        #  specified.
        self.radius = radius

        #: Units for outside radius: {mi|kft|km|m|Ft|in|cm}
        self.radUnits = radUnits

        #: Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not
        #  specified.
        self.normAmps = normAmps

        #: Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not
        #  specified.
        self.emergAmps = emergAmps

        #: Diameter; Alternative method for entering radius.
        self.diameter = diameter

        super(WireData, self).__init__(*args, **kw_args)

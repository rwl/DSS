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

class Feeder(object):
    """User cannot instantiate this object.  Feeders are created on the fly
    when a radial system is specified.  Feeders are created from Energymeters
    and are given the same name.  Feeders get created from energy meters if
    Radial is set to yes and meter zones are already computed.  If Radial=Yes
    and the meterzones are reset, then the feeders are redefined.  If Radial
    is subsequently set to NO or a solution mode is used that doesn't utilize
    feeders, the get currents routines will not do anything.  Feeders cannot
    be re-enabled unless the energymeter object allows them to be.  Feeders
    are not saved.  This is implicit with the Energymeter saving.
    """

    def __init__(self, baseFreq=0.0, enabled=False, spectrum=None, like=None):
        """Initialises a new 'Feeder' instance.
        """
        #: Base Frequency for ratings.
        self.baseFreq = baseFreq

        #: Indicates whether this element is enabled.
        self.enabled = enabled

        #: Harmonic spectrum for this device.
        self.spectrum = spectrum

        self.like = like

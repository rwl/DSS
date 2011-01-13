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

class Conductor(object):
    """A power conductor.
    """

    def __init__(self, Closed=True, FuseBlown=False):
        super(Conductor, self).__init__()

        self._TCCName = ""

        self._AmbientTemp = 0.0

        #: Accumulated I2t
        self._Accum_Isqt = 0.0

        self.Closed = Closed

        self.FuseBlown = FuseBlown

    def CalcIsqt(self, CurrentMag):
        """Computes whether conductor has burned down."""
        raise NotImplementedError


    def ResetIsqt(self):
        """Restore the conductor and reset the i2t calcs."""
        raise NotImplementedError


    def _Get_Ambient(self):
        return self._AmbientTemp
    def _Set_Ambient(self, Value):
        self._AmbientTemp = Value
    Ambient = property(_Get_Ambient, _Set_Ambient)


    def _Get_TCCName(self):
        return self._TCCName
    def _Set_TCCName(self, Value):
        self._TCCName = Value.lower()
    TccCurve = property(_Get_TCCName, _Set_TCCName)
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

class TCC_Curve(object):
    """Nominally, a time-current curve, but also used for volt-time curves.
    Collections of time points.  Return values can be interpolated either
    Log-Log as traditional TCC or as over- or under-voltage definite time.
    A TCC_Curve object is defined similarly to Loadshape and Growthshape
    objects in that they all are defined by curves consisting of arrays of
    points.  Intended to model time-current characteristics for overcurrent
    relays, TCC_Curve objects are also used for other relay types requiring
    time curves.  Both the time array and the C array must be entered.
    """

    def __init__(self, nPts=0, cArray=0.0, tArray=0.0):
        self.nPts = nPts

        self.cArray = cArray

        self.tArray = tArray

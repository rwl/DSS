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

from dss.common.Named import Named

class LineGeometry(Named):
    """The LineGeometry object is a general DSS object used by all circuits as
    a reference for obtaining line impedances.  Defines the positions of the
    conductors.
    """

    def __init__(self, nConds=0, nPhases=0, cond=0, x=0.0, h=0.0, units="none",
            normAmps=0.0, emergAmps=0.0, reduce=False, wire=None, spacing=None,
            wires=None, like=None, *args, **kw_args):
        """Initialises a new 'LineGeometry' instance.
        """
        #: Number of conductors in this geometry.
        self.nConds = nConds

        #: Number of phases.  All other conductors are considered neutrals and
        #  might be reduced out.
        self.nPhases = nPhases

        #: Set this = number of the conductor you wish to define.
        self.cond = cond

        #: x coordinate.
        self.x = x

        #: Height of conductor.
        self.h = h

        #: Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is 'ft',
        #  but defaults to last unit defined.
        self.units = units

        #: Normal ampacity, amperes for the line. Defaults to first conductor
        #  if not specified.
        self.normAmps = normAmps

        #: Emergency ampacity, amperes. Defaults to first conductor if not
        #  specified.
        self.emergAmps = emergAmps

        #: Reduce to n_phases (Kron Reduction). Reduce out neutrals.
        self.reduce = reduce

        self.wire = wire

        self.spacing = spacing

        self.wires = [] if wires is None else wires

        self.like = like

        super(LineGeometry, self).__init__(*args, **kw_args)

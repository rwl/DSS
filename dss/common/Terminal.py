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

class Terminal(object):
    """Each electrical element in the power system has one or more terminals.
    Each terminal has one or more conductors.  Each conductor contains a
    disconnect switch and a TCC (fuse) curve[Fuse has been disabled and is
    being redesigned; a Relay object can be used if needed to control the
    switches].  The conductors are numbered [1,2,3,...].  If the terminal is
    connected to an N-phase device, the first N conductors are assumed to
    correspond to the phases, in order.  The remaining conductors may be
    neutrals or whatever.
    """

    def __init__(self, busRef=0, termNodeRef=0, checked=False, nCond=0,
            activeConductor=0, conductors=None):
        """Initialises a new 'Terminal' instance.
        """
        self.busRef = busRef

        self.termNodeRef = termNodeRef

        self.checked = checked

        self.nCond = nCond

        self.activeConductor = activeConductor

        self.conductors = [] if conductors is None else conductors

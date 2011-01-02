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


class CircuitElement(object):
    """Base class for all elements of a circuit.
    """

    def __init__(self, name='', enabled=False, baseFreq=0.0, nodeRef=0, yOrder=0, yPrimInvalid=False, lastTerminalChecked=0, checked=False, hasMeter=False, isolated=False, hasControl=False, partOfFeeder=False, nTerms=0, nConds=0, nPhases=0, busIndex=0, yPrimFreq=0.0, controlElement=None, terminals=None, activeTerminal=None, yPrimSeries=None, yPrimShunt=None, yPrim=None, like=None):
        """Initialises a new 'CircuitElement' instance.
        """

        self.name = name

        #: Indicates whether this element is enabled.
        self.enabled = enabled

        #: Base frequency for ratings.
        self.baseFreq = baseFreq


        self.nodeRef = nodeRef


        self.yOrder = yOrder


        self.yPrimInvalid = yPrimInvalid

        #: Flag used in tree searches.
        self.lastTerminalChecked = lastTerminalChecked

        #: Flag used in tree searches etc.
        self.checked = checked


        self.hasMeter = hasMeter


        self.isolated = isolated


        self.hasControl = hasControl


        self.partOfFeeder = partOfFeeder


        self.nTerms = nTerms

        #: No. conductors per terminal.
        self.nConds = nConds


        self.nPhases = nPhases


        self.busIndex = busIndex

        #: Frequency at which YPrim has been computed.
        self.yPrimFreq = yPrimFreq

        self.controlElement = controlElement

        self.terminals = [] if terminals is None else terminals

        self.activeTerminal = activeTerminal

        self.yPrimSeries = yPrimSeries

        self.yPrimShunt = yPrimShunt

        self.yPrim = yPrim

        self.like = like


    controlElement = None

    def add_terminals(self, *terminals):
        for obj in terminals:
            self.terminals.append(obj)

    def remove_terminals(self, *terminals):
        for obj in terminals:
            self.terminals.remove(obj)

    activeTerminal = None

    yPrimSeries = None

    yPrimShunt = None

    yPrim = None

    like = None

    def doYPrimCalcs(self, yMatrix):
        pass
    def makePosSequence(self):
        pass

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

"""Defines objects common to all circuits in the DSS.
"""
from pydss.common.Circuit import Circuit
from pydss.common.Bus import Bus
from pydss.common.CircuitElement import CircuitElement
from pydss.common.Collection import Collection
from pydss.common.Conductor import Conductor
from pydss.common.Feeder import Feeder
from pydss.common.Solution import Solution
from pydss.common.Terminal import Terminal
from pydss.common.ControlQueue import ControlQueue
from pydss.common.Named import Named
from pydss.common.Electrickery import Electrickery

class connectionType(str):
    """Values are: Wye, LN, Delta, LL
    """
    pass

class lengthUnit(str):
    """Values are: none, mi, km, kft, m, me, ft, in, cm
    """
    pass

class tripAction(str):
    """Values are: Trip_Open, Close
    """
    pass

class yBuildOption(str):
    """Values are: SeriesOnly, WholeMatrix
    """
    pass

class controlModeType(str):
    """Values are: EventDriven, TimeDriven, Static
    """
    pass

class algorithmType(str):
    """Values are: NormalSolve, NewtonSolve
    """
    pass

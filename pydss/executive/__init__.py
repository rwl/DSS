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

from pydss.executive.ExecOptions import ExecOptions
from pydss.executive.ExecCommands import ExecCommands
from pydss.executive.Executive import Executive

class solutionMode(str):
    """Values are: Snapshot, Daily, Direct, Dutycycle, Dynamic, Harmonic, MonteCarlo1, MonteCarlo2, MonteCarlo3, FaultStudy, Yearly, MonteFault, Peakday, LoadDuration1, LoadDuration2, AutoAdd
    """
    pass

class randomType(str):
    """Values are: Uniform, Gaussian, Lognormal, None
    """
    pass

class loadModelType(str):
    """Values are: Powerflow, Admittance
    """
    pass

class autoAddType(str):
    """Values are: Generator, Capacitor
    """
    pass

class algorithmType(str):
    """Values are: Normal, Newton
    """
    pass

class controlModeType(str):
    """Values are: Static, Event, Time
    """
    pass

class circuitModelType(str):
    """Values are: Multiphase, Positive
    """
    pass

class reductionStrategy(str):
    """Values are: Stubs, MergeParallel, Breakloops, Tapends, Ends, Switches
    """
    pass

class resetType(str):
    """Values are: MO, ME, Faults, Controls, EventLog, KeepList, All
    """
    pass

class nextType(str):
    """Values are: Year, Hour, Time
    """
    pass

class exportType(str):
    """Values are: Voltages, SeqVoltages, Currents, Overloads, Unserved, SeqCurrents, Powers, FaultStudy, Generators, Loads, Meters, Monitors, Yprims, Y
    """
    pass

class reduceType(str):
    """Values are: All, MeterName
    """
    pass

class distributionType(str):
    """Values are: Proportional, Uniform, Random, Skip
    """
    pass

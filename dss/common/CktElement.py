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

import logging

from dss.general.DSSObject import DSSObject

global ActiveCircuit

logger = logging.getLogger(__name__)

class CktElement(DSSObject):
    """Base class for all elements of a circuit.
    """

    def __init__(self, ParClass):
        """Initialises a new 'CircuitElement' instance.
        """
        super(CktElement, self).__init__(ParClass)

        #: Total Noderef array for element
        self.NodeRef = None  # Need fast access to this
        self.Yorder = 0
        self.LastTerminalChecked = 0  # Flag used in tree searches
        self.Checked = False
        self.HasEnergyMeter = False
        self.HasSensorObj = False
        self.IsIsolated = False
        self.HasControl = False
        self.IsPartofFeeder = False  # Flag used in tree searches etc

        #: Pointer to control for this device
        self.ControlElement = None

        self.Iterminal = None
        self.Vterminal = None

        #: Base frequency for ratings.
        self.BaseFrequency = ActiveCircuit.Fundamental

        self.Terminals = []
        self.ActiveTerminal = 1

        ## Protected
        self.NTerms = 0
        self.Nconds = 0
        self.NPhases = 0
        self.ComplexBuffer = []
        #: Indicates which solution Itemp is computed for
        self.IterminalSolutionCount = -1
        self.BusIndex = 0
        self.YPrim_Series = None  # Order will be NTerms * Ncond
        self.YPrim_Shunt = None
        self.YPrim = None
        #: # Frequency at which YPrim has been computed
        self.YprimFreq

        ## Private
        self._BusNames = []
        self._Enabled = True
        self._EnabledProperty = 0
        self._ActiveTerminal = 0
        self._YPrimInvalid = True
        self._Handle = -1


    def _Get_YprimInvalid(self):
        return self._YPrimInvalid

    def _Set_YprimInvalid(self, Value):
        self._YPrimInvalid = Value
        if Value:
            # If this device is in the circuit, then we have to rebuild Y
            # on a change in Yprim
            if self.Enabled:
                ActiveCircuit.Solution.SystemYChanged = True

    YPrimInvalid = property(_Get_YprimInvalid, _Set_YprimInvalid)


    def _Get_ActiveTerminal(self):
        return self._ActiveTerminal

    def _Set_ActiveTerminal(self, Value):
        if (Value > 0) and (Value <= self.Nterms):
            self._ActiveTerminal = Value;
            self.ActiveTerminal = self.Terminals[Value]

    ActiveTerminalIdx = property(_Get_ActiveTerminal, _Set_ActiveTerminal)


    def _Get_Handle(self):
        return self._Handle

    def Set_Handle(self, Value):
        self._Handle = Value

    Handle = property(_Get_Handle, Set_Handle)


    def _Get_ConductorClosed(self, Index):
        """return state of selected conductor
        if index=0 return true if all phases closed, else false"""
        if (Index == 0):
            Result = True
            for i in range(self.Nphases):
                if not self.Terminals[self.ActiveTerminal].Conductors[i].Closed:
                    Result = False
                    break
        else:
            if (Index > 0) and (Index <= self.Nconds):
                Result = \
                self.Terminals[self.ActiveTerminal].Conductors[Index].Closed
            else:
                Result = False
        return Result

    def Set_ConductorClosed(self, Index, Value):
        if (Index == 0):  # Do all conductors
            for i in range(self.Nphases):
                self.Terminals[self.ActiveTerminal].Conductors[i].Closed = Value
            # force Y matrix rebuild
            ActiveCircuit.Solution.SystemYChanged = True
            self.YPrimInvalid = True
        else:
            if (Index > 0) and (Index <= self.Nconds):
                self.Terminals[self.ActiveTerminal].Conductors[Index].Closed \
                = Value
            ActiveCircuit.Solution.SystemYChanged = True
            self.YPrimInvalid = True


    def _Get_NConds(self):
        """No. conductors per terminal."""
        return self.NConds

    def _Set_NConds(self, Value):
        # Check for an almost certain programming error
        if Value <= 0:
            logger.error('Invalid number of terminals (%d) for "%s.%s"' %
                         (Value, self.ParentClass.Name, self.Name))
            return

        if Value != self.Nconds:
            ActiveCircuit.BusNameRedefined = True
        self.Nconds = Value
        # ReallocTerminals    NEED MORE EFFICIENT WAY TO DO THIS
        self.Set_Nterms(self.Nterms)

    NConds = property(_Get_NConds, _Set_NConds)


    def Closed(self, Index):
        pass


    def GetYPrim(self, Ymatrix, Opt=0):
        """returns values of array"""
        return 0
    def GetYPrimValues(self, Opt):
        pass
    def MaxTerminalOneIMag(self):
        """Max of Iterminal 1 phase currents"""
        return 0.0
    def ComputeIterminal(self):
        """Computes Iterminal for this device"""
        return
    def ComputeVterminal(self):
        pass
    def ZeroITerminal(self):
        pass
    def GetCurrents(self, Curr):
        """Get present value of terminal Curr for reports"""
        pass
    def GetInjCurrents(self, Curr):
        """Returns Injection currents"""
        pass
    def InjCurrents(self):
        """Applies to PC Elements Puts straight into Solution Array"""
        pass
    def GetBus(self, i):
        """Get bus name by index"""
        return ""
    def SetBus(self, i, s):
        """Set bus name by index"""
        pass
    def SetNodeRef(self, iTerm, NodeRefArray):
        """Set NodeRef Array for fast solution with intrinsics"""
        pass
    def RecalcElementData(self):
        pass
    def CalcYPrim(self):
        pass
    def MakePosSequence(self):
        """Make a positive Sequence Model"""
        pass
    def GetTermVoltages(self, iTerm, VBuffer):
        pass
    def GetPhasePower(self, PowerBuffer):
        pass
    def GetPhaseLosses(self, Num_Phases, LossBuffer):
        pass
    def GetLosses(self, TotalLosses, LoadLosses, NoLoadLosses):
        pass
    def GetSeqLosses(self, PosSeqLosses, NegSeqLosses, ZeroModeLosses):
        pass
    def SumCurrents(self):
        pass

    def GetPropertyValue(self, Index):
        pass
    def InitPropertyValues(self, ArrayOffset):
        pass
    def DumpProperties(self, F, Complete):
        pass

    def _Get_NTerms(self):
        pass
    def Set_NTerms(self, Value):
        pass
    NTerms = property(_Get_NTerms, Set_NTerms)

    ## Private
    def _Get_Freq(self):
        pass
    def _Set_Freq(self, Value=0.0):
        """Set freq and recompute YPrim."""
        pass
    YPrimFreq = property(_Get_Freq, _Set_Freq)

    def _Get_NPhases(self):
        pass
    def _Set_NPhases(self, value):
        pass
    NPhases = property(_Get_NPhases, _Set_NPhases)

    def _Get_Enabled(self):
        pass
    def _Set_Enabled(self, value):
        pass
    Enabled = property(_Get_Enabled, _Set_Enabled)

    def _Get_FirstBus(self):
        return ""
    FirstBus = property(_Get_FirstBus)

    def _Get_NextBus(self):
        return ""
    NextBus = property(_Get_NextBus)

    def _Get_Losses(self):
        return complex
    Losses = property(_Get_Losses)

    def _Get_Power(self):
        """Get total complex power in active terminal."""
        return complex

    def Power(self, idxTerm):
        """Total power in active terminal"""
        return self._Get_Power()

    def _DoYprimCalcs(self, Ymatrix):
        pass

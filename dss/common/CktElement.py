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

import sys
import logging

from numpy import sqrt

from dss.general.DSSObject import DSSObject
from dss.common.Terminal import PowerTerminal

global ActiveCircuit

CZERO = complex(0.0, 0.0)

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
        self._NTerms = 0
        self._NConds = 0
        self._NPhases = 0
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

        if Value != self._NConds:
            ActiveCircuit.BusNameRedefined = True
        self._NConds = Value
        # ReallocTerminals    NEED MORE EFFICIENT WAY TO DO THIS
        self.Set_Nterms(self._Nterms)

    NConds = property(_Get_NConds, _Set_NConds)


    def _Get_NPhases(self):
        return self._NPhases

    def _Set_NPhases(self, Value):
        if Value > 0:
            self._NPhases = Value

    NPhases = property(_Get_NPhases, _Set_NPhases)


    def _Get_NTerms(self):
        return self._NTerms

    def Set_NTerms(self, Value):
        i = 0
        NewBusNames = []

        # Check for an almost certain programming error
        if Value <= 0:
            logger.error('Invalid number of terminals (%d) for "%s.%s"' %
                            (Value, self.Parentclass.Name, self.Name))
            sys.exit()

        # If value is same as present value, no reallocation necessary;
        # If either Nterms or Nconds has changed then reallocate
        if (Value != self.NTerms) or (Value * self.NConds != self.YOrder):

            # Sanity Check
            if self.NConds > 101:
                logger.error('Warning: Number of conductors is very large ' +
                             '(%d) for Circuit Element: "%s.%s.' +
                             'Possible error in specifying the Number of ' +
                             'Phases for element.' %
                             (self.NConds, self.Parentclass.Name, self.Name))

            ## ReAllocate BusNames
            #  because they are Strings, we have to do it differently
            if Value < self.NTerms:
                # Keeps old values; truncates storage
#                ReallocMem(self.BusNames, Sizeof(self.BusNames[1]) * Value)
                pass
            else:
                if self.BusNames is None:
                    # First allocation
                    ## Always allocate arrays of strings with AllocMem so
                    ## that the pointers are all nil else Delphi thinks
                    ## non-zero values are pointing to an existing string.}
                    # fill with zeros or strings will crash
#                    FBusNames = AllocMem( Sizeof(FBusNames[1]) * Value)
                    for i in range(Value):
                        # Make up a bus name to stick in.
                        self.BusNames[i] = self.Name + '_' + str(i)
                        # This is so devices like transformers which may be
                        # defined on multiple commands
                        # will have something in the BusNames array.
                else:
                    # make some new space
#                    NewBusNames = AllocMem( Sizeof(FBusNames[1]) * Value)
                    for i in range(self.NTerms):
                        NewBusNames[i] = self.BusNames[i]  # copy old into new
                    for i in range(self.NTerms):
                        # decrement usage counts by setting to nil string
                        self.BusNames[i] = ''
                    for i in range(self.NTerms+1, Value):
                        # Make up a bus name to stick in.
                        NewBusNames[i] = self.Name + '_' + str(i)
#                    ReAllocMem(self.BusNames, 0)  # dispose of old array storage
                    self.BusNames = NewBusNames

        ## Reallocate Terminals if Nconds or NTerms changed}
        if self.Terminals is not None:
            for i in range(self.NTerms):
                self.Terminals[i].Free  # clean up old storage

#        ReallocMem(Terminals, Sizeof(Terminals[1]) * Value)

        self.NTerms = Value   # Set new number of terminals
        self.YOrder = self.NTerms * self.NConds
#        ReallocMem(Vterminal,     Sizeof(Vterminal[1])    *Yorder)
#        ReallocMem(Iterminal,     Sizeof(Iterminal[1])    *Yorder)
#        # used by both PD and PC elements
#        ReallocMem(ComplexBuffer, Sizeof(ComplexBuffer[1])*Yorder)

        for i in range(Value):
            self.Terminals[i] = PowerTerminal(self.NConds)

    NTerms = property(_Get_NTerms, Set_NTerms)


    def _Get_Enabled(self):
        return self._Enabled

    def _Set_Enabled(self, Value):
        # If disabled, but defined, just have to processBusDefs. Adding
        # a bus OK if being removed from circuit, could remove a node or
        # bus so have to rebuild
        ac = ActiveCircuit
        if Value != self._Enabled:
            # don't change unless this represents a change

            # This code was too cute and prevented rebuilding of meter zones
            # Removed 7/24/01
            if Value:
                ac.NumNodesSaved = ac.NumNodes
                # If we create new nodes, force rebuild of bus lists
                ac.ProcessBusDefs()
                if ac.NumNodes > ac.NumNodesSaved:
                    ac.BusNameRedefined = True
            else:
                ac.Solution.SystemYChanged = True  # just rebuild of yPrim
        else:
            ac.BusNameRedefined = True #  Force Rebuilding of BusLists anyway

        self._Enabled = Value
        # forces rebuilding of Y matrix and bus lists
        ac.BusNameRedefined = True

    Enabled = property(_Get_Enabled, _Set_Enabled)


    def GetYPrim(self, YMatrix, Opt=0):
        """returns values of array"""
        if Opt == "ALL_YPRIM":
            YMatrix = self.YPrim
        elif Opt == "SERIES":
            YMatrix = self.YPrim_Series
        elif Opt == "SHUNT":
            YMatrix = self.YPrim_Shunt

        return 0


    def GetYPrimValues(self, Opt):
        Norder = 0
        if Opt == "ALL_YPRIM":
            return self.YPrim.GetValuesArrayPtr(Norder)
        elif Opt == "SERIES":
            return self.YPrim_Series.GetValuesArrayPtr(Norder)
        elif Opt == "SHUNT":
            return self.YPrim_Shunt.GetValuesArrayPtr(Norder)
        else:
            return None


    def GetCurrents(self, Curr):
        """Get present value of terminal Curr for reports"""
        raise NotImplementedError


    def GetInjCurrents(self, Curr):
        """Returns Injection currents"""
        raise NotImplementedError


    def GetLosses(self, TotalLosses, LoadLosses, NoLoadLosses):
        # For no override, Default behavior is: Just return total losses
        # and set LoadLosses=total losses and noload losses =0
        TotalLosses = self.Losses  # Watts, vars
        LoadLosses = TotalLosses
        NoLoadLosses = complex(0, 0)


    def InjCurrents(self):
        """Applies to PC Elements Puts straight into Solution Array"""
        raise NotImplementedError


    def SetNodeRef(self, iTerm, NodeRefArray):
        """Set NodeRef Array for fast solution with intrinsics"""
        # Also allocates VTemp  & Itemp
        Size, Size2 = 0

        # Allocate NodeRef and move new values into it.
        Size = self.YOrder * len(self.NodeRef[0])
        Size2 = len(self.NodeRef[0]) * self.NConds  # Size for one terminal
        # doesn't do anything if already properly allocated
#        ReallocMem(NodeRef, Size)
#        Move(NodeRefArray^[1], NodeRef^[(iTerm-1)*Fnconds+1], Size2)  # Zap
#        # Copy in Terminal as well
#        Move(NodeRefArray^[1], Terminals^[iTerm].TermNodeRef^[1], Size2)

        # Allocate temp array used to hold voltages and currents for calcs
#        ReallocMem(Vterminal, Yorder * SizeOf(Vterminal^[1]));
#        ReallocMem(Iterminal, Yorder * SizeOf(Iterminal^[1]));
#        ReallocMem(ComplexBuffer, Yorder * SizeOf(ComplexBuffer^[1]))


    def _Get_FirstBus(self):
        if self.NTerms > 0:
            self.BusIndex = 0
            Result = self.BusNames[self.BusIndex]
        else:
            Result = ''
        return Result

    FirstBus = property(_Get_FirstBus)


    def _Get_NextBus(self):
        Result = ''
        if self.NTerms > 0:
            self.BusIndex += 1
            if self.BusIndex <= self.NTerms:
                Result = self.BusNames[self.BusIndex]
        else:
            self.BusIndex = self.NTerms
        return Result

    NextBus = property(_Get_NextBus)


    def GetBus(self, i):
        """Get bus name by index"""
        if i <= self.NTerms:
            Result = self.BusNames[i]
        else:
            Result = ''
        return Result


    def SetBus(self, i, S):
        """Set bus name by index"""
        if i <= self.NTerms:
            self.BusNames[i] = S.lower()
            # Set Global Flag to signal circuit to rebuild busdefs
            ActiveCircuit.BusNameRedefined = True
        else:
            logger.error('Attempt to set bus name for non-existent circuit '
                         'element terminal(%d): "%s"' % (i, S))


    def _Get_Freq(self):
        return self._YprimFreq

    def _Set_Freq(self, Value):
        """Set freq and recompute YPrim."""
        if Value > 0.0:
            self._YprimFreq = Value

    YPrimFreq = property(_Get_Freq, _Set_Freq)


    def RecalcElementData(self):
        raise NotImplementedError


    def CalcYPrim(self):
        if self.YPrim_Series is not None:
            self._DoYPrimCalcs(self.Yprim_Series)
        if self.YPrim_Shunt is not None:
            self._DoYPrimCalcs(self.YPrim_Shunt)
        if self.YPrim is not None:
            self._DoYPrimCalcs(self.YPrim)


    def ComputeIterminal(self):
        """Computes Iterminal for this device"""
        # to save time, only recompute if a different solution than
        # last time it was computed.
        if self.IterminalSolutionCount != ActiveCircuit.Solution.SolutionCount:
            self.GetCurrents(self.Iterminal)
            self.IterminalSolutionCount = ActiveCircuit.Solution.SolutionCount


    def MaxTerminalOneIMag(self):
        """Max of Iterminal 1 phase currents"""
        # Get max of phase currents on the first terminal.
        # Requires computing Iterminal
        Result = 0.0
        if self._Enabled:
            for i in range(self.NPhases):
                It = self.Iterminal[i]
                Result = max(Result, It.real**2 + It.imag**2)
        Result = sqrt(Result)  ## just do the sqrt once and save a little time

        return Result


    def _Get_Power(self, idxTerm):
        """Get total complex power in active terminal."""
        cPower = CZERO
        i, k, n = 0

        self.ActiveTerminalIdx = idxTerm

        if self._Enabled:
            self.ComputeIterminal()

            # Method: Sum complex power going into phase conductors
            # of active terminal
            sol = ActiveCircuit.Solution
            k = (idxTerm - 1) * self.NConds;
            for i in range(self.NConds):
                # 11-7-08 Changed from Fnphases - was not accounting
                # for all conductors

                # don't bother for grounded node
                n = self.ActiveTerminal.TermNodeRef[i]

#                if n > 0:
#                    Caccum(cPower, Cmul(NodeV^[n], conjg(Iterminal[k+i]) ))

            # If this is a positive sequence circuit, then we need to multiply
            # by 3 to get the 3-phase power}
            if ActiveCircuit.PositiveSequence:
                cPower = cPower * 3.0

        return cPower


    def _Get_Losses(self):
        """get total losses in circuit element, all phases, all terminals.
        Returns complex losses (watts, vars)"""
        cLoss = CZERO
        k, n = 0

        if self.Enabled:
            self.ComputeIterminal()

            # Method: Sum complex power going into all conductors
            # of all terminals
            sol = ActiveCircuit.Solution
            for k in range(self.YOrder):
                n = self.NodeRef[k]
#                if n > 0:
#                    if ActiveCircuit.PositiveSequence:
#                        Caccum(cLoss, CmulReal(Cmul(NodeV[n],
#                                                    conjg(Iterminal[k])), 3.0))
#                    else:
#                        Caccum(cLoss, Cmul(NodeV^[n], conjg(Iterminal^[k])))

        return cLoss

    Losses = property(_Get_Losses)


    def GetPhasePower(self, PowerBuffer):
        """Get the power in each phase (complex losses) of active terminal
        neutral conductors are ignored by this routine."""
        i, n = 0

        if self.Enabled:
            self.ComputeIterminal()

            sol = ActiveCircuit.Solution
            for i in range(self.YOrder):
                n = self.NodeRef[i]  # increment through terminals
#                if n > 0:
#                    if ActiveCircuit.PositiveSequence:
#                        PowerBuffer[i] = \
#                            CmulReal(Cmul(NodeV^[n], conjg(Iterminal[i])), 3.0)
#                    else:
#                        PowerBuffer[i] = \
#                            Cmul(NodeV^[n], conjg(Iterminal[i]))
        else:
            for i in range(self.YOrder):
                PowerBuffer[i] = CZERO


    def GetPhaseLosses(self, Num_Phases, LossBuffer):
        # Get the losses in each phase (complex losses);  Power difference
        # coming out each phase. Note: This can be misleading if the nodev
        # voltage is greatly unbalanced. neutral conductors are ignored by
        # this routine
        i, j, k, n = 0
        cLoss = CZERO

        Num_Phases = self.NPhases;
        if self.Enabled:
            self.ComputeIterminal()

            sol = ActiveCircuit.Solution
            for i in range(Num_Phases):
                cLoss = complex(0.0, 0.0)
                for j in range(self.NTerms):
                    k = (j - 1) * self.NConds + i
                    n = self.NodeRef[k]  # increment through terminals
#                    if n > 0:
#                        if ActiveCircuit.PositiveSequence:
#                            Caccum(cLoss,
#                                CmulReal(Cmul(NodeV[n],
#                                              conjg(Iterminal[k])), 3.0))
#                        else:
#                            Caccum(cLoss,Cmul(NodeV[n], conjg(Iterminal[k])))
                LossBuffer[i] = cLoss
        else:
            for i in range(Num_Phases):
                LossBuffer[i] = CZERO


    def DumpProperties(self, F, Complete):
        i, j = 0

        super(CktElement, self).DumpProperties(F, Complete)

        if self.Enabled:
            F.write('! ENABLED' + '\n')
        else:
            F.write('! DISABLED' + '\n')
        if Complete:
            F.write('! NPhases = %s\n' % self.NPhases)
            F.write('! Nconds = %s\n' % self.NConds)
            F.write('! Nterms = %s\n' % self.NTerms)
            F.write('! Yorder = %s\n'% self.YOrder)
            F.write('! NodeRef = "')
            if self.NodeRef is None:
                F.write('nil')
            else:
                for i in range(self.YOrder):
                    F.write(self.NodeRef[i] +' ')
            F.write('"\n')
            F.write('! Terminal Status: [')
            for i in range(self.NTerms):
                for j in range(self.NConds):
                    if self.Terminals[i].Conductors[j].Closed:
                        F.write('C ')
                    else:
                        F.write('O ')
            F.write(']\n')
            F.write('! Terminal Bus Ref: [')
            for i in range(self.NTerms):
                for j in range(self.NConds):
                    F.write(self.Terminals[i].BusRef + ' ')
            F.write(']\n')
            F.write('\n')

            if self.YPrim is not None:
                F.write( '! YPrim (G matrix)\n')
                for i in range(self.YOrder):
                    F.write('! ')
                    for j in range(i):
                        F.write(' %13.10g |' % self.YPrim[i, j].real)
                    F.write("\n")
                F.write( '! YPrim (B Matrix) = \n')
                for i in range(self.YOrder):
                    F.write('! ')
                for j in range(i):
                    F.write(' %13.10g |' % self.YPrim[i, j].imag)
                F.write("\n")


    def Closed(self, Index):
        pass
    def ComputeVterminal(self):
        pass
    def ZeroITerminal(self):
        pass
    def MakePosSequence(self):
        """Make a positive Sequence Model"""
        pass
    def GetTermVoltages(self, iTerm, VBuffer):
        pass
    def GetSeqLosses(self, PosSeqLosses, NegSeqLosses, ZeroModeLosses):
        pass
    def SumCurrents(self):
        pass

    def GetPropertyValue(self, Index):
        pass
    def InitPropertyValues(self, ArrayOffset):
        pass

    def Power(self, idxTerm):
        """Total power in active terminal"""
        return self._Get_Power()

    def _DoYprimCalcs(self, Ymatrix):
        pass

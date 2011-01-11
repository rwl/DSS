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

import os
import sys

import logging

from numpy import array, zeros

from dss.common.Solution import Solution
from dss.common.Named import Named
from dss.common.ControlQueue import ControlQueue
from dss.common.Bus import Bus
from dss.common.Utilities import \
    ParseObjectClassandName, LogThisEvent, WriteVsourceClassFile, \
    WriteClassFile, CheckForBlanks
from dss.common.CircuitElement import CircuitElement
from dss.general.LoadShape import LoadShape
from dss.delivery.PowerDeliveryElement import PowerDeliveryElement
from dss.delivery.PowerConversionElement import PowerConversionElement
from dss.control.ControlElement import ControlElement
from dss.meter.MeterElement import MeterElement
from dss.meter.Monitor import Monitor
from dss.meter.EnergyMeter import EnergyMeter
from dss.meter.Sensor import Sensor
from dss.conversion.Generator import Generator
from dss.conversion.CurrentSource import CurrentSource
from dss.conversion.VoltageSource import VoltageSource
from dss.control.CapacitorControl import CapacitorControl
#from dss.control.SWTControl import SWTControl
from dss.control.RegulatorControl import RegulatorControl
from dss.conversion.Load import Load
from dss.delivery.Capacitor import Capacitor
from dss.delivery.Transformer import Transformer
from dss.delivery.Line import Line
from dss.delivery.Fault import Fault
from dss.common.Feeder import Feeder
from dss.conversion.Storage import Storage

global DefaultBaseFreq, USENONE, AppendGlobalresult, LastClassReferenced, \
    ClassNames, DSSClassList, ActiveDSSClass, CmdResult, ActiveDSSObject, \
    EnergyMeterClass, SavedFileList, FeederClass, SolutionClass, \
    CircuitName_

logger = logging.getLogger(__name__)

class Circuit(Named):
    """Defines a container of circuit elements.
    """

    def __init__(self, aName):
        """Initialises a new 'Circuit' instance.
        """
        super(Circuit, self).__init__("Circuit")

        self.LocalName = aName.lower()

        # Default case name to circuitname.
        # Sets CircuitName_
        self.CaseName = aName

        self.ActiveCktElement = None

        self.ActiveBusIndex = 1  # Always a bus

        #: Fundamental and default base frequency.
        self.Fundamental = DefaultBaseFreq

        #: Flag for use by control elements to detect redefinition of buses.
        self.Control_BusNameRedefined = False

        self.BusList = {}
        self.AutoAddBusList = {}
        self.DeviceList = {}

        #: Type and handle of device
        self.DeviceRef = []

        # lists of pointers to different elements by class
        self.faults = []
        self.CktElements = []
        self.PDElements = []
        self.PCElements = []
        self.DSSControls = []
        self.Sources = []
        self.MeterElements = []
        self.voltageSources = []
        self.currentSources = []
        self.sensors = []
        self.monitors = []
        self.energyMeters = []
        self._generators = []
        self.generators = []
        self.StorageElements = []
        self.Substations = []
        self.transformers = []
        self.capControls = []
        self.regControls = []
        self.lines = []
        self.loads = []
        self.shuntCapacitors = []
        self.feeder = []
        self.SwtControls = []

        self.controlQueue = ControlQueue

        self._solution = None
        self.Solution = Solution(self.Name)
#        self.AutoAddObj = AutoAdd()

        # For AutoAdd stuff
        self.UEWeight = 1.0  # Default to weighting UE same as losses
        self.LossWeight = 1.0

        self.NumUEregs = 1
        self.NumLossRegs = 1
        self.Ueregs = [10] # Overload UE
        self.LossRegs = [13] # Zone Losses

        self.CapacityStart = 0.9  # for Capacity search
        self.CapacityIncrement = 0.005

        #: Flag for trapezoidal integration.
        self.TrapezoidalIntegration = False  # Default to Euler method
        self.LogEvents = False

        self.LoadDurCurve = ""
        self.LoadDurCurveObj = None
        self.PriceCurve = ""
        self.PriceCurveObj = None

        self.MaxDevices = 1000 # good sized allocation to start
        self.MaxBuses = 1000
        self.MaxNodes = 3 * self.MaxBuses
        self.IncDevices = 1000
        self.IncBuses = 1000
        self.IncNodes = 3 * 1000
        # Eventually allocate a single source
        self.NumDevices, self.NumBuses, self.NumNodes = 0

        # Bus and Node stuff
        self.Buses = []
        self.MapNodeToBus = []

        # Flags
        self.Issolved = False
        self.DuplicatesAllowed = False
        self.ZonesLocked = False  # Meter zones recomputed after each change
        self.MeterZonesComputed = False
        #: Model is to be interpreted as Pos seq?
        self.PositiveSequence = False

        # Voltage limits
        # per unit voltage restraints for this circuit
        self.NormalMinVolts = 0.95
        self.NormalMaxVolts = 1.05
        self.EmergMaxVolts = 1.08
        self.EmergMinVolts = 0.90
        # Default Voltage Bases
        self.LegalVoltageBases = [0.208, 0.480, 12.47, 24.9, 34.5, 115.0,230.0]

        # Global circuit multipliers
        self.GeneratorDispatchReference = 0.0

        self.defaultGrowthFactor = 1.0
        self.DefaultGrowthRate = 1.025
        #: Global multiplier for every generator.
        self.genMultiplier = 1.0
        # Init global circuit harmonic source multiplier.
        self.HarmMult = 1.0
        self.defaultHourMult = complex(0, 0)

        #: price signal for entire circuit
        self.priceSignal = 25.0  # $25/MWH

        # EnergyMeter Totals
        self.RegisterTotals = []

        self.DefaultDailyShapeObj = LoadShape.Find('default')
        self.DefaultYearlyShapeObj = LoadShape.Find('default')

        self.CurrentDirectory = ""

        self.ReductionStrategy = 0
        self.ReductionMaxAngle = 15.0
        self.ReductionZmag = 0.02
        self.ReductionStrategyString = ""

        self.PctNormalFactor = 100.0

        ## Plot Markers
        self.NodeMarkerCode = 16
        self.NodeMarkerWidth = 1
        self.SwitchMarkerCode = 5
        self.TransMarkerSize = 1
        self.TransMarkerCode = 35

        self.MarkSwitches = False
        self.MarkTransformers = False

        self.ActiveLoadShapeClass = USENONE

        #: Lets control devices know the bus list has changed.
#        self.control_busNameRedefined = control_busNameRedefined

        ## Private
        self._NodeBufferMax = 50
        # A place to hold the nodes.
        self._NodeBuffer = zeros(self._NodeBufferMax, dtype=int)
        self._BusNameRedefined = False
        # set to force rebuild of buslists, nodelists
        self.BusNameRedefined = True
        self._ActiveCktElement = None

        # Temp arrays for when the bus swap takes place
        self._SavedBuses = None #[]
        self._SavedBusNames = None #[]
        self._SavedNumBuses = 0

        self._LoadMultiplier = 1.0
        # Init global circuit load multiplier.
        self.LoadMultiplier = 1.0

        self._AbortBusProcess = False

        self._Branch_List = None
        self._BusAdjPC = None #array([])
        self._BusAdjPD = None #array([])


    def ProcessBusDefs(self):
        BusName = ""
        NNodes, Ncond, i, j, iTerm, RetVal = 0
        NodesOK = False

        ace = self.ActiveCktElement
        np = ace.NPhases
        NCond = ace.NConds

        # use parser functions to decode
#        Parser.Token = ace.FirstBus

        for iTerm in range(ace.Nterms):
            NodesOK = True
            # Assume normal phase rotation  for default
            for i in range(np):
                self._NodeBuffer[i] = i # set up buffer with defaults

            # Default all other conductors to a ground connection
            # if user wants them ungrounded, must be specified explicitly!
            for i in range(np + 1, NCond):
                self._NodeBuffer[i] = 0

            # Parser will override bus connection if any specified
#            BusName = Parser.ParseAsBusName(NNodes, self.NodeBuffer)

            # Check for error in node specification
            for j in range(NNodes):
                if self._NodeBuffer[j] < 0:
#                    logger.error('Error in Node specification for Element: "',
#                     self.__class__.__name__ + '.' + self.Name + '"\n' +
#                     'Bus Spec: "' + Parser.Token + '"')
                    RetVal = -1
                    NodesOK = False
                    if RetVal == -1:
                        self._AbortBusProcess = True
                        AppendGlobalresult('Aborted bus process.')
                    sys.exit(RetVal)
                break

            # Node-Terminal Connections
            # Caution: Magic -- AddBus replaces values in nodeBuffer to
            # correspond with global node reference number.
            if NodesOK:
                ace.ActiveTerminalIdx = iTerm
                ace.ActiveTerminal.BusRef = self.AddBus(BusName, Ncond)
                ace.SetNodeRef(iTerm, self._NodeBuffer) # for active circuit
#            Parser.Token = NextBus

        return


    def _AddABus(self):
        """Allocates more memory if necessary."""
        if self.NumBuses > self.MaxBuses:
            self.MaxBuses += self.IncBuses


    def _AddANodeBus(self):
        if self.NumNodes > self.MaxNodes:
            self.MaxNodes += self.IncNodes


    def _AddBus(self, BusName="", NNodes=0):
        NodeRef, i = 0

        # Trap error in bus name
        if not BusName:
            # Error in busname
            logger.error('Circuit.AddBus: BusName for Object "' +
                         self.ActiveCktElement.Name + '" is null.' +
                         'Error in definition of object.')
            for i in range(self.ActiveCktElement.NConds):
                self._NodeBuffer[i] = 0

            sys.exit()
            return 0

        Result = self.BusList.Find(BusName)
        if Result == 0:
            self.NumBuses += 1
            self._AddABus() # Allocates more memory if necessary
            self.Buses[self.NumBuses] = Bus()

        # Define nodes belonging to the bus}
        # Replace Nodebuffer values with global reference number
        Bus = self.Buses[Result]
        for i in range(NNodes):
            NodeRef = Bus.add(self._NodeBuffer[i])
            if NodeRef == self.NumNodes:
                # This was a new node so Add a NodeToBus element ????
                self.AddANodeBus() # Allocates more memory if necessary
                self.MapNodeToBus[self.NumNodes].BusRef = Result
                self.MapNodeToBus[self.NumNodes].NodeNum = self._NodeBuffer[i]
            # Swap out in preparation to setnoderef call
            self._NodeBuffer[i] = NodeRef

        return Result


    def _AddDeviceHandle(self, Handle=0):
        if self.NumDevices > self.MaxDevices:
            self.MaxDevices = self.MaxDevices + self.IncDevices

        # Index into CktElements
        self.DeviceRef[self.NumDevices].devHandle = Handle
        self.DeviceRef[self.NumDevices].CktElementClass = LastClassReferenced


    def SetElementActive(self, FullObjectName=""):
        """Fast way to set a cktelement active."""
        DevType = ""
        DevName = ""

        Result = 0

        ParseObjectClassandName(FullObjectName, DevType, DevName)
        DevClassIndex = ClassNames.index(DevType)
        if DevClassIndex == 0:
            DevClassIndex = LastClassReferenced
        DevIndex = self.DeviceList.index(DevName)
        while DevIndex > 0:
            if self.DeviceRef[DevIndex].CktElementClass == DevClassIndex:
                # we got a match
                ActiveDSSClass = DSSClassList[DevClassIndex]
                LastClassReferenced = DevClassIndex
                Result = self.DeviceRef[DevIndex].devHandle
#                ActiveDSSClass.Active = Result;
#                ActiveCktElement = ActiveDSSClass.GetActiveObj;
                self.ActiveCktElement = self.CktElements[Result]
                break
            DevIndex = self.DeviceList.next()   # Could be duplicates

        CmdResult = Result

        return Result


    def AddCktElement(self, Handle=0):
        """Adds last DSS object created to circuit.
        """
        # Update lists that keep track of individual circuit elements
        self.NumDevices += 1

        # Resize DeviceList if no. of devices greatly exceeds allocation
#        if Cardinal(self.NumDevices) > 2 * self.DeviceList.InitialAllocation:
        if self.NumDevices > 2 * self.DeviceList.InitialAllocation:
            self.ReAllocDeviceList()
        self.DeviceList.append(self.ActiveCktElement.Name)
        self.CktElements.append(self.ActiveCktElement)

        # Build Lists of PC and PD elements
        ace = self.ActiveCktElement
        if isinstance(ace, PowerDeliveryElement):
            self.PDElements.append(ace)
        elif isinstance(ace, PowerConversionElement):
            self.PCElements.append(ace)
        elif isinstance(ace, ControlElement):
            self.DSSControls.append(ace)
        elif isinstance(ace, MeterElement):
            self.MeterElements.append(ace)
        else:
            pass

        # Build lists of Special elements and generic types
        if isinstance(ace, Monitor):
            self.Monitors.append(ace)
        elif isinstance(ace, EnergyMeter):
            self.EnergyMeters.append(ace)
        elif isinstance(ace, Sensor):
            self.Sensors.append(ace)
        elif isinstance(ace, Generator):
            self.Generators.append(ace)
        elif isinstance(ace, CurrentSource) or isinstance(ace, VoltageSource):
            self.Sources.append(ace)
        elif isinstance(ace, CapacitorControl):
            self.CapControls.append(ace)
#        elif isinstance(ace, SWTControl):
#            self.SwtControls.append(ace)
        elif isinstance(ace, RegulatorControl):
            self.RegControls.append(ace)
        elif isinstance(ace, Load):
            self.Loads.append(ace)
        elif isinstance(ace, Capacitor):
            self.ShuntCapacitors.append(ace)
        # Keep Lines, Transformer, and Lines and Faults in PDElements and
        # separate lists so we can find them quickly.
        elif isinstance(ace, Transformer):
            self.Transformers.append(ace)
        elif isinstance(ace, Line):
            self.Lines.append(ace)
        elif isinstance(ace, Fault):
            self.Faults.append(ace)
        elif isinstance(ace, Feeder):
            self.Feeders.append(ace)
        elif isinstance(ace, Storage):
            self.StorageElements.append(ace)
        else:
            pass

        # AddDeviceHandle(Handle) # Keep Track of this device result is handle
        # Handle is global index into CktElements
        self.AddDeviceHandle(self.CktElements.ListSize)
        self.ActiveCktElement.Handle = self.CktElements.ListSize


    def DoResetMeterZones(self):
        # Do this only if meterzones unlocked .  Normally, Zones will remain
        # unlocked so that all changes to the circuit will result in rebuilding
        # the lists.
        if not self.MeterZonesComputed or not self.ZonesLocked:
            if self.LogEvents:
                LogThisEvent('Resetting Meter Zones')
            EnergyMeter.ResetMeterZonesAll()
            self.MeterZonesComputed = True
            if self.LogEvents:
                LogThisEvent('Done Resetting Meter Zones')

        self.FreeTopology()


    def _SaveBusInfo(self):
        i = 0
        # Save existing bus definitions and names for info that needs
        # to be restored.
        self.SavedBuses = []
        self.SavedBusNames = []

        for i in range(self.NumBuses):
            self.SavedBuses[i] = self.Buses[i]
            self.SavedBusNames[i] = self.BusList[i]
        self.SavedNumBuses = self.NumBuses


    def _RestoreBusInfo(self):
        i, j, idx, jdx = 0
        pBus = None
        # Restore  kV bases, other values to buses still in the list
        for i in range(self.SavedNumBuses):
            idx = self.BusList.index(self.SavedBusNames[i])
            if idx != 0:
                bus = self.Buses[idx]
                pBus = self.SavedBuses[i]
                kvBase = pBus.kVBase
                x = pBus.x
                Y = pBus.y
                CoordDefined = pBus.CoordDefined
                Keep = pBus.Keep
                # Restore Voltages in new bus def that existed
                # in old bus def
                if pBus.VBus != None:
                    for j in range(pBus.NumNodesThisBus):
                        # Find index in new bus for j-th node  in old bus
                        jdx = bus.FindIdx(pBus.GetNum(j))
                        if jdx > 0:
                            bus.Vbus[jdx] = pBus.VBus[j]
            self.SavedBusNames[i] = ''  # De-allocate string

        if self.SavedBuses != None:
            for i in range(self.SavedNumBuses):
                self.SavedBuses[i].Free  # gets rid of old bus voltages, too

#        ReallocMem(SavedBuses, 0);
#        ReallocMem(SavedBusNames, 0)


    def ReProcessBusDefs(self):
        """Redo all Buslists, nodelists."""
        CktElementSave = None
        i = 0

        if self.LogEvents:
            LogThisEvent('Reprocessing Bus Definitions')

        self.AbortBusProcess = False
        self.SaveBusInfo()  # So we don't have to keep re-doing this
        # Keeps present definitions of bus objects until new ones created

        # get rid of old bus lists
        del self.BusList[:]  # Clears hash list of Bus names for adding more
        self.BusList = {}  # won't have many more buses than this

        NumBuses = 0  # Leave allocations same, but start count over
        NumNodes = 0

        # Now redo all enabled circuit elements
        self.CktElementSave = self.ActiveCktElement
        ActiveCktElement = self.CktElements.next()
        while ActiveCktElement != None:
            if ActiveCktElement.Enabled:
                self.ProcessBusDefs
            if self.AbortBusProcess:
                sys.exit()
            ActiveCktElement = self.CktElements.next()

        # restore active circuit element
        self.ActiveCktElement = self.CktElementSave

        for i in range(NumBuses):
            self.Buses[i].AllocateBusVoltages
        for i in range(NumBuses):
            self.Buses[i].AllocateBusCurrents

        self.RestoreBusInfo()     # frees old bus info, too
        self.DoResetMeterZones()  # Fix up meter zones to correspond

        self.BusNameRedefined = False  # Get ready for next time


    def DebugDump(self, F):
        F.write('NumBuses= ', self.NumBuses)
        F.write('NumNodes= ', self.NumNodes)
        F.write('NumDevices= ', self.NumDevices)
        F.write('BusList:')
        for i in range(self.NumBuses):
            F.write('  %12s', self.BusList[i])
            F.write(' (', self.Buses[i].NumNodesThisBus, ' Nodes)')
            for j in range(self.Buses[i].NumNodesThisBus):
                F.write(' ', self.Buses[i].Getnum(j))
            F.write("\n")

        F.write('DeviceList:')
        for i in range(self.NumDevices):
            F.write('  %12s', self.DeviceList[i])
            self.ActiveCktElement = self.CktElements[i]
            if not self.ActiveCktElement.Enabled:
                F.write('  DISABLED')
            F.write("\n")
        F.write('NodeToBus Array:')
        for i in range(self.NumNodes):
            j =  self.MapNodeToBus[i].BusRef
#            F.write('  ', i:2, ' ', j:2, ' (=', self.BusList[j], '.',
#                    self.MapNodeToBus[i].NodeNum:0,')')
            F.write("\n")


    def InvalidateAllPCElements(self):
        for p in self.CktElements:
            p.YprimInvalid = True

        # Force rebuild of matrix on next solution
        self.Solution.SystemYChanged = True


    def TotalizeMeters(self):
        """Totalize all energymeters in the problem."""
        i = 0
        for i in range(EnergyMeter.NumEMRegisters):
            self.RegisterTotals[i] = 0.0

        for pem in self.EnergyMeters:
            for i in range(EnergyMeter.NumEMRegisters):
                self.RegisterTotals[i] += pem.Registers[i] * pem.TotalsMask[i]


    def ComputeCapacity(self):
        CapacityFound = False

        def SumSelectedRegisters(mtrRegisters=[], Regs=[1], count=0):
            Result = 0.0
            for i in range(count):
                Result += mtrRegisters[Regs[i]]
            return Result

        Result = False
        if len(self.EnergyMeters) == 0:
            logger.error('Cannot compute system capacity with EnergyMeter '
                         'objects!')
            sys.exit()

        if self.NumUeRegs == 0:
            logger.error('Cannot compute system capacity with no UE resisters '
                         'defined.  Use SET UEREGS=(...) command.')
            sys.exit()

        self.Solution.Mode = 'SNAPSHOT'
        self.LoadMultiplier = self.CapacityStart
        self.CapacityFound = False

        while (self.LoadMultiplier < 1.0) or not CapacityFound:
            EnergyMeterClass.ResetAll()
            self.Solution.Solve()
            EnergyMeterClass.SampleAll()
            self.TotalizeMeters()

            # Check for non-zero in UEregs
            if SumSelectedRegisters(self.RegisterTotals, self.UEregs,
                                    self.NumUEregs) != 0.0:
                CapacityFound = True
            # LoadMultiplier is a property ...
            if not CapacityFound:
                self.LoadMultiplier = \
                    self.LoadMultiplier + self.CapacityIncrement
        if self.LoadMultiplier > 1.0:
            self.LoadMultiplier = 1.0

        Result = True

        return Result


    def Save(self, Dir=""):
        """Save the present circuit - Enabled devices only"""
        i = 0
        Success = False
        CurrDir = ""
        SaveDir = ""

        Result = False
        # Make a new subfolder in the present folder based on the circuit
        # name and a unique sequence number
#        SaveDir = os.path.pwd()  # remember where to come back to
        Success = False
        if len(Dir) == 0:
            dir = self.Name

        CurrDir = Dir
        for i in range(999):  # Find a unique dir name
            if not os.path.isdir(CurrDir):
                if os.mkdir(CurrDir):
                    os.chdir(CurrDir)
                    Success = True
                    break
            CurrDir = Dir + '%.3d' % [i]
        else:
            if not os.path.isdir(Dir):
                CurrDir = Dir
                if os.mkdir(CurrDir):
                    os.chdir(CurrDir);
                    Success = True

            else:  # Exists - overwrite
                CurrDir = Dir
                os.chdir(CurrDir)
                Success = True

        if not Success:
            logger.error('Could not create a folder "' +
                         Dir + '" for saving the circuit.')
            sys.exit()

        del SavedFileList[:]  # This list keeps track of all files saved

        # Initialize so we will know when we have saved the circuit elements
        for cktElem in self.CktElements:
            cktElem[i].HasBeenSaved = False

        # Initialize so we don't save a class twice
        for cls in DSSClassList:
            cls.Saved = False

        # Ignore Feeder Class -- gets saved with Energymeters
        FeederClass.Saved = True  # will think this class is already saved

        # Define voltage sources first
        Success =  WriteVsourceClassFile('vsource', True)
        # Write library files so that they will be available to lines, loads, etc
        # Use default filename=classname
        if Success: Success =  WriteClassFile('wiredata','', False)
        if Success: Success =  WriteClassFile('linegeometry', '', False)
        if Success: Success =  WriteClassFile('linecode', '', False)
        if Success: Success =  WriteClassFile('linespacing', '', False)
        if Success: Success =  WriteClassFile('linecode', '', False)
        if Success: Success =  WriteClassFile('xfmrcode', '', False)
        if Success: Success =  WriteClassFile('growthshape', '', False)
        if Success: Success =  WriteClassFile('TCC_Curve', '', False)
        if Success: Success =  WriteClassFile('Spectrum', '', False)
        if Success: Success = self.SaveFeeders()  # Save feeders first
        if Success: Success = self.SaveDSSObjects()  # Save rest ot the objects
        if Success: Success = self.SaveBusCoords()
        if Success: Success = self.SaveMasterFile()

        if Success:
            logger.info('Circuit saved in directory: %s' % os.getcwd())
        else:
            logger.error('Error attempting to save circuit in %s' %
                         os.getcwd())

        # Return to Original directory
        os.chdir(SaveDir)

        return True


    def _SaveDSSObjects(self):
        DSS_Class = None
        i = 0

        # Write Files for all populated DSS Classes  Except Solution Class
        for i in range(DSSClassList.ListSize):
            DSS_Class = DSSClassList[i]
            if (DSS_Class == SolutionClass) or DSS_Class.Saved:
                continue  # Cycle to next
            # use default filename=classname
            if not WriteClassFile(DSS_Class, '',
                                  isinstance(DSS_Class, CircuitElement)):
                sys.exit()  # bail on error
        DSS_Class.Saved = True

        return True


    def _SaveMasterFile(self):
        F = None
        i = 0

        Result = False
        try:
            F = open('Master.DSS', "wb")
            F.write('Clear' + '\n')
            F.write('New Circuit.' + self.Name + '\n')
            F.write('\n')
            if self.PositiveSequence:
                F.write('Set Cktmodel=Positive' + '\n')
            if self.DuplicatesAllowed:
                F.write('set allowdup=yes' + '\n')
            F.write('\n')

            # Write Redirect for all populated DSS Classes
            # Except Solution Class
            for i in range(len(SavedFileList.Count)):
                F.write('Redirect ', SavedFileList.Strings[i - 1])

            if os.path.exists('buscoords.dss'):
                F.write('MakeBusList')
                F.write('Buscoords buscoords.dss')

            F.close()
            Result = True
        except Exception, E:
            logger.error('Error Saving Master File: ' + E.message)

        return Result


    def _SaveFeeders(self):
        Result = True
        # Write out all energy meter  zones to separate subdirectories
        SaveDir = os.getcwd()
        for Meter in self.EnergyMeters:
            CurrDir = Meter.Name
            if os.mkdir(CurrDir):
                os.chdir(CurrDir)
                Meter.SaveZone(CurrDir)
                os.chdir(SaveDir)
            else:
                logger.error('Cannot create directory: ' + CurrDir)
                Result = False
                os.chdir(SaveDir)  # back to whence we came
                break

        return Result


    def _SaveBusCoords(self):
        Result = False

        try:
            F = open('BusCoords.dss', "wb")

            for i in range(self.NumBuses):
                if self.Buses[i].CoordDefined:
                    F.write(CheckForBlanks(self.BusList[i]), ', %-g, %-g' %
                            (self.Buses[i].X, self.Buses[i].Y))

            F.close()

            Result = True

        except Exception, E:
            logger.error('Error creating Buscoords.dss. ' + E.msg)

        return Result


    def _ReallocDeviceList(self):
        """Reallocates the device list to improve the performance of searches.
        """
        TempList = []
        i = 0
        if self.LogEvents:
            LogThisEvent('Reallocating Device List')

        for i in range(len(self.DeviceList)):
            TempList.Append(self.DeviceList[i])

        del self.DeviceList[:] # Throw away the old one.
        self.DeviceList = TempList


    def GetBusAdjacentPDLists(self):
#        if self._BusAdjPD is None:
#            BuildActiveBusAdjacencyLists(self._BusAdjPD, self._BusAdjPC)
        return self._BusAdjPD


    def GetBusAdjacentPCLists(self):
#        if self._BusAdjPC is None:
#            BuildActiveBusAdjacencyLists(self._BusAdjPD, self._BusAdjPC)
        return self._BusAdjPC


    def GetTopology(self):
        """Access to topology from the first source."""
        i = 0
        elem = None

        if self.Branch_List is None:
            # Initialize all Circuit Elements and Buses to not checked,
            # then build a new tree}
            elem = self.CktElements.next()
            while elem is not None:
                elem.Checked = False
                for i in range(elem.Nterms):
                    elem.Terminals[i].Checked = False
                elem.IsIsolated = True  # till proven otherwise
                elem = self.CktElements.next()

            for i in range(self.NumBuses):
                self.Buses[i].BusChecked = False
            # calls back to build adjacency lists
#            self.Branch_List = GetIsolatedSubArea(self.Sources.next(), True)

        return self.Branch_List


    def FreeTopology(self):
        if self.Branch_List is not None:
            del self.Branch_List[:]
        self.Branch_List = None
#        if self._BusAdjPC is not None:
#            FreeAndNilBusAdjacencyLists(self._BusAdjPD, self._BusAdjPC)


    def _Get_Name(self):
        return self.LocalName

    Name = property(_Get_Name)


    def Get_CaseName(self):
        return self._CaseName

    def _Set_CaseName(self, value):
        self._CaseName = value
        CircuitName_ = value + "_"

    CaseName = property(Get_CaseName, _Set_CaseName)


    def Get_ActiveCktElement(self):
        return self._ActiveCktElement

    def _Set_ActiveCktElement(self, value):
        self._ActiveCktElement = value
        ActiveDSSObject = value

    ActiveCktElement = property(Get_ActiveCktElement, _Set_ActiveCktElement)


    def _Get_Losses(self):
        """Total Circuit PD Element losses."""
        pdElem = self.PDElements.next()
        Result = complex(0.0, 0.0)
        while pdElem != None:
            if pdElem.enabled:
                # Ignore Shunt Elements
                if not pdElem.IsShunt:
                    Result += pdElem.losses
            pdElem = self.PDElements.next()
        return Result

    Losses = property(_Get_Losses)


    def Get_BusNameRedefined(self):
        return self._BusNameRedefined

    def _Set_BusNameRedefined(self, value):
        self._BusNameRedefined = value
        if value:
            # Force Rebuilding of SystemY if bus def has changed
            Solution.SystemYChanged = True
            # So controls will know buses redefined
            self.Control_BusNameRedefined = True

    BusNameRedefined = property(Get_BusNameRedefined, _Set_BusNameRedefined)


    def Get_LoadMultiplier(self):
        return self._LoadMultiplier

    def _Set_LoadMultiplier(self, Value):
        if (Value != self._LoadMultiplier):
            # We may have to change the Y matrix if the load multiplier
            # has changed
            if self.Solution.LoadModel == "ADMITTANCE":
                self.InvalidateAllPCElements()

        self._LoadMultiplier = Value

    LoadMultiplier = property(Get_LoadMultiplier, _Set_LoadMultiplier)

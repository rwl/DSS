# Copyright (C) 2010 Richard Lincoln
#
# This library is free software you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

import os

from math import sqrt

from dss.common.Circuit import Circuit

# Some units which have global vars defined here
from dss.general.LoadShape import LoadShape
from dss.general.GrowthShape import GrowthShape
from dss.general.Spectrum import Spectrum
from dss.common.Solution import Solution
from dss.meter.EnergyMeter import EnergyMeter
from dss.common.Feeder import Feeder
from dss.meter.Monitor import Monitor
from dss.meter.Sensor import Sensor
from dss.general.TCC_Curve import TCC_Curve
from dss.general.WireData import WireData
from dss.general.LineSpacing import LineSpacing
from dss.conversion.Storage import Storage

from dss.common import DSSMessageDlg, DSSInfoMessageDlg, GetDSSExeFile

## Constants and Switches
CZERO = complex(0.0, 0.0)

CRLF = "\n"

PI =  3.14159265359

TwoPi = 2.0 * PI

RadiansToDegrees = 57.29577951

EPSILON = 1.0e-12   # Default tiny floating point
EPSILON2 = 1.0e-3   # Default for Real number mismatch testing

POWERFLOW  = 1  # Load model types for solution
ADMITTANCE = 2

# For YPrim matrices
ALL_YPRIM = 0
SERIES = 1
SHUNT  = 2

## Control Modes
CONTROLSOFF = -1
EVENTDRIVEN =  1
TIMEDRIVEN  =  2
CTRLSTATIC  =  0

## Randomization Constants
GAUSSIAN  = 1
UNIFORM   = 2
LOGNORMAL = 3

## Autoadd Constants
GENADD = 1
CAPADD = 2

## ERRORS
SOLUTION_ABORT = 99

## For General Sequential Time Simulations
USEDAILY  = 0
USEYEARLY = 1
USEDUTY   = 2
USENONE   =-1

## Earth Model
SIMPLECARSON  = 1
FULLCARSON    = 2
DERI          = 3

## Variables
DLLFirstTime    = True
DLLDebugFile    = None
DSS_IniFileName = 'OpenDSSPanel.ini'
#DSS_Registry    = IniRegSave('\Software\OpenDSS') # Registry   (See Executive)

IsDLL = False
NoFormsAllowed  = False

ActiveCircuit   = None
ActiveDSSClass  = None
LastClassReferenced = 0  # index of class of last thing edited
ActiveDSSObject = None
NumCircuits     = 0
MaxCircuits     = 1
MaxBusLimit     = 0 # Set in Validation
MaxAllocationIterations = 2
Circuits        = []
DSSObjs         = []

# Auxiliary parser for use by anybody for reparsing values
AuxParser       = None #Parser()

ErrorPending       = False
CmdResult          = 0
global ErrorNumber
ErrorNumber        = 0
global LastErrorMessage
LastErrorMessage   = ""

DefaultEarthModel  = DERI
ActiveEarthModel   = DefaultEarthModel

LastFileCompiled   = ""
LastCommandWasCompile = False

CALPHA             = complex(-0.5, -0.866025)  # 120-degree shift constant
SQRT2              = sqrt(2)
SQRT3              = sqrt(3)
InvSQRT3           = 1.0 / SQRT3
InvSQRT3x1000      = SQRT3 * 1000
SolutionAbort      = False
InShowResults      = False
Redirect_Abort     = False
In_Redirect        = False
DIFilesAreOpen     = False
AutoShowExport     = False
SolutionWasAttempted = False

## Initialize filenames and directories
GlobalHelpString     = ""
GlobalPropertyValue  = ""
GlobalResult         = ""
VersionString        = ''  # see GetDSSVersion below

DefaultEditor    = 'NotePad'     # normally, Notepad
DefaultFontSize  = 8
DSSFileName      = GetDSSExeFile()  # Name of current exe or DLL
DSSDirectory     = os.path.dirname(DSSFileName) # where the current exe resides
StartupDirectory = os.getcwd() + '\\'  # where we started
DSSDataDirectory = StartupDirectory
CircuitName_     = ""     # Name of Circuit with a "_" appended

DefaultBaseFreq  = 60.0
DaisySize        = 1.0

# Some commonly used classes   so we can find them easily
LoadShapeClass     = LoadShape
GrowthShapeClass   = GrowthShape
SpectrumClass      = Spectrum
SolutionClass      = Solution
EnergyMeterClass   = EnergyMeter
FeederClass        = Feeder
MonitorClass       = Monitor
SensorClass        = Sensor
TCC_CurveClass     = TCC_Curve
WireDataClass      = WireData
LineSpacingClass   = LineSpacing
StorageClass       = Storage

EventStrings  = []
SavedFileList = []

DSSClassList = [] # pointers to the base class types
ClassNames   = {}


_LastUserDLLHandle = None
_DSSRegisterProc = None  # of last library loaded

#def IsDSSDLL(Fname=""):
#    Result = False
#
#    # Ignore if "DSSLIB.DLL"
#    if ExtractFileName(Fname).lower() != 'dsslib.dll':
#        return Result
#
#    _LastUserDLLHandle = LoadLibrary(Fname)
#    if _LastUserDLLHandle != 0:
#        # Assign the address of the DSSRegister proc to DSSRegisterProc variable
#        @DSSRegisterProc = GetProcAddress(LastUserDLLHandle, 'DSSRegister')
#        if @DSSRegisterProc is not Nne:
#            Result = True
#        else:
#            FreeLibrary(LastUserDLLHandle)
#
#    return result


def DoErrorMsg(S="", Emsg="", ProbCause="", ErrNum=0):
    Msg = 'Error %d Reported From DSS Intrinsic Function: ' % ErrNum + CRLF + \
        S + CRLF + CRLF + 'Error Description: ' + CRLF + Emsg + CRLF + CRLF + \
        'Probable Cause: ' + CRLF + ProbCause

    if not NoFormsAllowed:
        if In_Redirect:
            RetVal = DSSMessageDlg(Msg, False)
            if RetVal == -1:
                Redirect_Abort = True
        else:
            DSSMessageDlg(Msg, True)

    LastErrorMessage = Msg
    ErrorNumber = ErrNum
    AppendGlobalResultCRLF(Msg)


def AppendGlobalResultCRLF(S=""):
    # Separate by CRLF
    if len(GlobalResult) > 0:
        GlobalResult = GlobalResult + CRLF + S
    else:
        GlobalResult = S


def DoSimpleMsg(S="", ErrNum=0):
    if not NoFormsAllowed:
        if In_Redirect:
            RetVal = DSSMessageDlg('(%d) %s%s', [ErrNum, CRLF, S], False)
            if RetVal == -1:
                Redirect_Abort = True
        else:
            DSSInfoMessageDlg('(%d) %s%s' % (ErrNum, CRLF, S))

    LastErrorMessage = S
    ErrorNumber = ErrNum
    AppendGlobalResultCRLF(S)


def SetObject(Param=""):
    """Set object active by name"""
    # Split off Obj class and name
    dotpos = Param.index('.')
    if dotpos:
        # assume it is all name; class defaults
        ObjName = Param[:len(Param)]
    else:
        ObjClass = Param[:dotpos - 1]
        ObjName  = Param[dotpos + 1, len(Param)]

#    if len(ObjClass) > 0:
#        SetObjectClass(ObjClass)

    ActiveDSSClass = DSSClassList.Get(LastClassReferenced)
    if ActiveDSSClass is not None:
        if not ActiveDSSClass.SetActive(ObjName):
            # scroll through list of objects untill a match
            DoSimpleMsg('Error! Object "' + ObjName + '" not found.'+ CRLF +
                        parser.CmdString, 904)
        else:
            ac = ActiveCircuit
            if ac.DSSObjType == DSS_OBJECT: # do nothing for general DSS object
                pass
            else:  # for circuit types, set ActiveCircuit Element, too
                ac.ActiveCktElement = ActiveDSSClass.GetActiveObj()
    else:
        DoSimpleMsg('Error! Active object type/class is not set.', 905)


def SetActiveBus(BusName=""):
    # find the bus and set active
    Result = 0
    ac = ActiveCircuit
    if ac.BusList.ListSize == 0:
        return Result  # Buslist not yet built
        ac.ActiveBusIndex = ac.BusList.index(BusName)
        if ac.ActiveBusIndex == 0:
            Result = 1
            AppendGlobalResult('SetActiveBus: Bus ' + BusName + ' Not Found.')
    return Result


def ClearAllCircuits():
    ActiveCircuit = Circuits.next()
    while ActiveCircuit is not None:
        ActiveCircuit.Free()
        ActiveCircuit = Circuits.next()
    Circuits.Free()
    Circuits = [None] * 2  # Make a new list of circuits
    NumCircuits = 0
    DefaultEarthModel = DERI  # original state


def MakeNewCircuit(Name=""):
    if NumCircuits <= MaxCircuits - 1:
        ActiveCircuit = Circuit(Name)
        ActiveDSSObject = ActiveSolutionObj
        #*Handle := *
        Circuits.append(ActiveCircuit)
        NumCircuits += 1
        S = Parser.Remainder  # Pass remainder of string on to vsource.
        # Create a default Circuit
        SolutionABort = False
        # Voltage source named "source" connected to SourceBus

        # Load up the parser as if it were read in
        DSSExecutive.Command = 'New object=vsource.source Bus1=SourceBus ' + S
    else:
        DoErrorMsg('MakeNewCircuit',
                   'Cannot create new circuit.',
                   'Max. Circuits Exceeded.'+CRLF+
                   '(Max no. of circuits=' + str(MaxCircuits) + ')', 906)


def AppendGlobalResult(S=""):
    """Append a string to Global result, separated by commas"""
    if len(GlobalResult) == 0:
        GlobalResult = S
    else:
        GlobalResult = GlobalResult + ', ' + S


def GetDSSVersion():
    Result = 'Unknown.'

#    InfoSize = GetFileVersionInfoSize(DSSFileName, Wnd)
#    if InfoSize != 0:
#        GetMem(VerBuf, InfoSize)
#        try:
#            if GetFileVersionInfo(DSSFileName, Wnd, InfoSize, VerBuf):
#                if VerQueryValue(VerBuf, '\\', Pointer(FI), VerSize):
#                    MinorVer = FI.dwFileVersionMS and $FFFF
#                    MajorVer = (FI.dwFileVersionMS and $FFFF0000) shr 16
#                    BuildNo =  FI.dwFileVersionLS and $FFFF
#                    RelNo = (FI.dwFileVersionLS and $FFFF0000) shr 16
#                    Result = str(MajorVer) + '.' + str(MinorVer) + '.' + \
#                        str(RelNo) + ' Build ' + str(BuildNo)
#        finally:
#            FreeMem(VerBuf)

    return Result

VersionString = 'Version ' + GetDSSVersion()


def WriteDLLDebugFile(S=""):
    if DLLFirstTime:
        DLLDebugFile = open(DSSDataDirectory + 'DSSDLLDebug.TXT', "wb")
        DLLFirstTime = False
    else:
        DLLDebugFile = open(DSSDataDirectory + 'DSSDLLDebug.TXT', "ab")
        DLLDebugFile.write(S + "\n")
        DLLDebugFile.close()


def SetDataPath(PathName=""):
    """Pathname may be null"""
    if (len(PathName) > 0) and not os.path.exists(PathName):
        # Try to create the directory
        if not os.mkdir(PathName):
            DoSimpleMsg('Cannot create ' + PathName + ' directory.', 907)
            return

    DSSDataDirectory = PathName

    # Put a \ on the end if not supplied. Allow a null specification.
    if len(DSSDataDirectory) > 0:
        os.chdir(DSSDataDirectory)  # Change to specified directory
        if DSSDataDirectory[len(DSSDataDirectory)] != '\\':
            DSSDataDirectory = DSSDataDirectory + '\\'


def ReadDSS_Registry():
    DefaultEditor    = DSS_Registry.ReadString('Editor', 'Notepad.exe' )
    DefaultFontSize  = int(DSS_Registry.ReadString('ScriptFontSize', '8' ))
    DefaultBaseFreq  = int(DSS_Registry.ReadString('BaseFrequency', '60' ))
    LastFileCompiled = DSS_Registry.ReadString('LastFile', '' )


def WriteDSS_Registry():
    DSS_Registry.WriteString('Editor', DefaultEditor)
    DSS_Registry.WriteString('ScriptFontSize', '%d' % DefaultFontSize)
    DSS_Registry.WriteString('BaseFrequency', '%d' % round(DefaultBaseFreq))
    DSS_Registry.WriteString('LastFile', LastFileCompiled)

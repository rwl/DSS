from dss.common.impl.DSSCircuit import (DSSCircuit,)
from dss.common.impl.CommandLineDSSForms import (CommandLineDSSForms,)
from dss.executive.impl.DSSExecutive import (DSSExecutive,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.SolutionImpl import (SolutionImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.DSSForms import (DSSForms,)
# from java.io.BufferedWriter import (BufferedWriter,)
# from java.io.File import (File,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.IOException import (IOException,)
# from java.io.PrintStream import (PrintStream,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class DSSGlobals(object):
    CRLF = System.getProperty('line.separator')
    PI = 3.14159265359
    TWO_PI = 2.0 * PI
    RADIANS_TO_DEGREES = 57.29577951
    EPSILON = 1e-12
    # default tiny floating point
    EPSILON2 = 0.001
    # default for real number mismatch testing
    # Load model types for solution
    POWERFLOW = 1
    ADMITTANCE = 2
    # For YPrim matrices
    ALL_YPRIM = 0
    SERIES = 1
    SHUNT = 2
    # Control modes
    CONTROLSOFF = -1
    EVENTDRIVEN = 1
    TIMEDRIVEN = 2
    CTRLSTATIC = 0
    # Randomization constants
    GAUSSIAN = 1
    UNIFORM = 2
    LOGNORMAL = 3
    # AutoAdd constants
    GENADD = 1
    CAPADD = 2
    # Errors
    SOLUTION_ABORT = 99
    # For general sequential time simulations
    USEDAILY = 0
    USEYEARLY = 1
    USEDUTY = 2
    USENONE = -1
    # Earth model
    SIMPLECARSON = 1
    FULLCARSON = 2
    DERI = 3
    # Profile plot constants
    PROFILE3PH = 9999
    # some big number > likely no. of phases
    PROFILEALL = 9998
    PROFILEALLPRI = 9997
    PROFILELLALL = 9996
    PROFILELLPRI = 9995
    PROFILELL = 9994
    # 120-degree shift constant
    CALPHA = Complex(-0.5, -0.866025)
    SQRT2 = super(DSSGlobals).Math.sqrt(2.0)
    SQRT3 = super(DSSGlobals).Math.sqrt(3.0)
    InvSQRT3 = 1.0 / SQRT3
    InvSQRT3x1000 = InvSQRT3 * 1000.0
    # DSS Forms
    DSSForms = CommandLineDSSForms.getInstance()
    # Variables
    DLLFirstTime = True
    DLLDebugFile = None
    DSSIniFileName = 'OpenDSSPanel.ini'
    # Registry   (See Executive)
    # public static IniRegSave DSS_Registry = IniRegSave("\\Software\\OpenDSS");
    isDLL = False
    noFormsAllowed = False
    activeCircuit = None
    activeDSSClass = None
    lastClassReferenced = None
    # index of class of last thing edited
    activeDSSObject = None
    numCircuits = 0
    maxCircuits = 1
    maxBusLimit = 0
    # set in validation
    maxAllocationIterations = 2
    circuits = None
    DSSObjs = None
    # auxiliary parser for use by anybody for reparsing values
    auxParser = Parser.getInstance()
    errorPending = False
    cmdResult = 0
    errorNumber = 0
    lastErrorMessage = ''
    defaultEarthModel = DERI
    activeEarthModel = defaultEarthModel
    lastFileCompiled = ''
    lastCommandWasCompile = False
    solutionAbort = False
    inShowResults = False
    redirectAbort = False
    inRedirect = False
    DIFilesAreOpen = False
    autoShowExport = False
    solutionWasAttempted = False
    globalHelpString = ''
    globalPropertyValue = ''
    globalResult = ''
    versionString = 'Version ' + getDSSVersion()
    defaultEditor = 'NotePad'
    DSSFileName = None
    # = GetDSSExeFile();  // name of current exe or DLL
    DSSDirectory = None
    # = new File(DSSFileName).getParent();  // where the current exe resides
    startupDirectory = System.getProperty('user.dir') + '/'
    # starting point
    DSSDataDirectory = startupDirectory
    circuitName_ = None
    # name of circuit with a "_" appended
    currentDirectory = startupDirectory
    # current working directory
    defaultBaseFreq = 60.0
    daisySize = 1.0
    # commonly used classes
    loadShapeClass = None
    TShapeClass = None
    priceShapeClass = None
    XYCurveClass = None
    growthShapeClass = None
    spectrumClass = None
    solutionClass = None
    energyMeterClass = None
    # public static Feeder feederClass;
    monitorClass = None
    sensorClass = None
    TCC_CurveClass = None
    wireDataClass = None
    CNDataClass = None
    TSDataClass = None
    lineSpacingClass = None
    storageClass = None
    PVSystemClass = None
    eventStrings = None
    savedFileList = None
    DSSClassList = None
    # base class types
    classNames = None

    @classmethod
    def doErrorMsg(cls, s, emsg, probCause, errNum):
        msg = String.format.format('Error %d reported from DSS function: ', errNum) + s + cls.CRLF + 'Error description: ' + cls.CRLF + emsg + cls.CRLF + 'Probable cause: ' + cls.CRLF + probCause
        if not cls.noFormsAllowed:
            if cls.inRedirect:
                retVal = DSSForms.messageDlg(msg, False)
                if retVal == -1:
                    cls.redirectAbort = True
            else:
                DSSForms.messageDlg(msg, True)
        cls.lastErrorMessage = msg
        cls.errorNumber = errNum
        cls.appendGlobalResult(msg)

    @classmethod
    def doSimpleMsg(cls, s, errNum):
        if not cls.noFormsAllowed:
            if cls.inRedirect:
                RetVal = DSSForms.messageDlg(String.format.format('(%d) %s%s', errNum, cls.CRLF, s), False)
                if RetVal == -1:
                    cls.redirectAbort = True
            else:
                DSSForms.infoMessageDlg(String.format.format('(%d) %s%s', errNum, cls.CRLF, s))
        cls.lastErrorMessage = s
        cls.errorNumber = errNum
        cls.appendGlobalResult(s)

    @classmethod
    def clearAllCircuits(cls):
        cls.activeCircuit = None
        cls.circuits = list(2)
        # make a new list of circuits
        cls.numCircuits = 0

    @classmethod
    def setObject(cls, param):
        """Set object active by name."""
        objClass = None
        # Split off obj class and name
        dotpos = param.find('.')
        _0 = dotpos
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                objName = param
                break
            if True:
                _1 = True
                objClass = param[:dotpos - 1]
                objName = param[dotpos + 1:len(param)]
                break
            break
        if len(objClass) > 0:
            DSSClassDefs.setObjectClass(objClass)
        cls.activeDSSClass = cls.DSSClassList[cls.lastClassReferenced]
        if cls.activeDSSClass is not None:
            if not cls.activeDSSClass.setActive(objName):
                # scroll through list of objects until a match
                cls.doSimpleMsg('Error: Object \"' + objName + '\" not found.' + cls.CRLF + Parser.getInstance().getCmdString(), 904)
            else:
                _2 = cls.activeDSSObject.getDSSObjType()
                _3 = False
                while True:
                    if _2 == DSSClassDefs.DSS_OBJECT:
                        _3 = True
                        break
                    if True:
                        _3 = True
                        cls.activeCircuit.setActiveCktElement(cls.activeDSSClass.getActiveObj())
                        break
                    break
        else:
            cls.doSimpleMsg('Error: Active object type/class is not set.', 905)

    @classmethod
    def setActiveBus(cls, busName):
        """Finds the bus and sets it active."""
        result = 0
        if cls.activeCircuit.getBusList().listSize() == 0:
            return result
            # bus list not yet built
        cls.activeCircuit.setActiveBusIndex(cls.activeCircuit.getBusList().find(busName))
        if cls.activeCircuit.getActiveBusIndex() == 0:
            result = 1
            cls.appendGlobalResult('SetActiveBus: Bus ' + busName + ' not found.')
        return result

    @classmethod
    def setDataPath(cls, pathName):
        """Pathname may be null"""
        f = File(pathName)
        if len(pathName) > 0 and not f.exists():
            # try to create the directory
            if f.mkdir():
                cls.doSimpleMsg('Cannot create ' + pathName + ' directory.', 907)
                System.exit(0)
        cls.DSSDataDirectory = pathName
        # Put a \ on the end if not supplied. Allow a null specification.
        if len(cls.DSSDataDirectory) > 0:
            cls.currentDirectory = cls.DSSDataDirectory
            # change to specified directory
            # if (DSSDataDirectory.charAt(DSSDataDirectory.length()) != '\\') {
            # DSSDataDirectory = DSSDataDirectory + "\\";
            # }

    @classmethod
    def makeNewCircuit(cls, name):
        if cls.numCircuits < cls.maxCircuits:
            cls.activeCircuit = DSSCircuit(name)
            cls.activeDSSObject = SolutionImpl.activeSolutionObj
            # Handle =
            cls.circuits.add(cls.activeCircuit)
            cls.numCircuits += 1
            # pass remainder of string on to VSource
            s = Parser.getInstance().getRemainder()
            # create a default circuit
            cls.solutionAbort = False
            # Voltage source named "source" connected to "SourceBus"
            # load up the parser as if it were read in
            DSSExecutive.getInstance().setCommand('new object=vsource.source bus1=SourceBus ' + s)
        else:
            cls.doErrorMsg('makeNewCircuit', 'Cannot create new circuit.', 'Max. circuits exceeded.' + cls.CRLF + '(Max no. of circuits=' + String.valueOf.valueOf(cls.maxCircuits) + ')', 906)

    @classmethod
    def appendGlobalResult(cls, s):
        """Append a string to global result, separated by commas."""
        if len(cls.globalResult) == 0:
            cls.globalResult = s
        else:
            cls.globalResult = cls.globalResult + ', ' + s

    @classmethod
    def appendGlobalResultCRLF(cls, s):
        """Separate by CRLF."""
        if len(cls.globalResult) > 0:
            cls.globalResult += cls.CRLF + s
        else:
            cls.globalResult = s

    @classmethod
    def WriteDLLDebugFile(cls, s):
        if cls.DLLFirstTime:
            append = False
            cls.DLLFirstTime = False
        else:
            append = True
        # TODO Auto-generated catch block
        try:
            writer = FileWriter(cls.DSSDataDirectory + 'DSSDLLDebug.txt', append)
            debugFile = BufferedWriter(writer)
            debugFile.write(s + cls.CRLF)
            debugFile.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def readDSS_Registry(cls):
        raise cls.UnsupportedOperationException()

    @classmethod
    def writeDSS_Registry(cls):
        raise cls.UnsupportedOperationException()

    @classmethod
    def isDSSDLL(cls, fname):
        raise cls.UnsupportedOperationException()

    @classmethod
    def getDSSVersion(cls):
        # TODO: Implement GetDSSVersion()
        return 'Unknown.'

from dss.plot.impl.DSSPlotImpl import (DSSPlotImpl,)
from dss.plot.impl.DSSGraphDeclarations import (DSSGraphDeclarations,)
from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.SolutionAlgs import (SolutionAlgs,)
from dss.executive.impl.ExecCommands import (ExecCommands,)
from dss.conversion.impl.LoadImpl import (LoadImpl,)
from dss.plot.DSSPlot import (DSSPlot,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.executive.impl.DSSExecutive import (DSSExecutive,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.SolutionImpl import (SolutionImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.meter.EnergyMeterObj import (EnergyMeterObj,)
# from com.epri.dss.common.impl.DSSCircuit.ReductionStrategyType import (ReductionStrategyType,)
# from java.io.BufferedReader import (BufferedReader,)
# from java.io.DataInputStream import (DataInputStream,)
# from java.io.File import (File,)
# from java.io.FileInputStream import (FileInputStream,)
# from java.io.FileNotFoundException import (FileNotFoundException,)
# from java.io.FileReader import (FileReader,)
# from java.io.IOException import (IOException,)
# from java.io.InputStreamReader import (InputStreamReader,)
# from java.io.PrintWriter import (PrintWriter,)
# from java.util.ArrayList import (ArrayList,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class ExecHelper(object):
    saveCommands = None
    distributeCommands = None
    DI_PlotCommands = None
    reconductorCommands = None
    rephaseCommands = None
    addMarkerCommands = None
    setBusXYCommands = None

    def __init__(self):
        pass

    @classmethod
    def initialize(cls):
        cls.saveCommands = CommandListImpl(['class', 'file', 'dir', 'keepdisabled'])
        cls.saveCommands.setAbbrevAllowed(True)
        cls.DI_PlotCommands = CommandListImpl(['case', 'year', 'registers', 'peak', 'meter'])
        cls.distributeCommands = CommandListImpl(['kW', 'how', 'skip', 'pf', 'file', 'MW'])
        cls.distributeCommands.setAbbrevAllowed(True)
        cls.reconductorCommands = CommandListImpl(['Line1', 'Line2', 'LineCode', 'Geometry', 'EditString'])
        cls.reconductorCommands.setAbbrevAllowed(True)
        cls.rephaseCommands = CommandListImpl(['StartLine', 'PhaseDesignation', 'EditString', 'ScriptFileName', 'StopAtTransformers'])
        cls.rephaseCommands.setAbbrevAllowed(True)
        cls.addMarkerCommands = CommandListImpl(['Bus', 'code', 'color', 'size'])
        cls.addMarkerCommands.setAbbrevAllowed(True)
        cls.setBusXYCommands = CommandListImpl(['Bus', 'x', 'y'])
        cls.setBusXYCommands.setAbbrevAllowed(True)

    @classmethod
    def getObjClassAndName(cls, objClass, objName):
        """Looks for object definition:

          paramName = 'object' if given
          and the name of the object

          object=capacitor.C1
          or just capacitor.C1

        If no dot, last class is assumed.
        """
        parser = Parser.getInstance()
        objClass.delete(0, len(objClass))
        objName.delete(0, len(objName))
        paramName = parser.getNextParam().toLowerCase()
        param = parser.makeString()
        if len(paramName) > 0:
            # if specified, must be object or an abbreviation.
            if Utilities.compareTextShortest(paramName, 'object') != 0:
                DSSGlobals.doSimpleMsg('object=class.name expected as first parameter in command.' + DSSGlobals.CRLF + parser.getCmdString(), 240)
                return
        Utilities.parseObjectClassandName(param, objClass, objName)

    @classmethod
    def doNewCmd(cls):
        """Process the "new" command

            new type=xxxx name=xxxx editstring

        If the device being added already exists, the default behaviour is to
        treat the "new" command as an "edit" command.  This may be overridden
        by setting the duplicatesAllowed variable to true, in which case,
        the "new" command always results in a new device being added.
        """
        objClass = str()
        objName = str()
        handle = 0
        result = 0
        cls.getObjClassAndName(objClass, objName)
        if str(objClass).equalsIgnoreCase('solution'):
            DSSGlobals.doSimpleMsg('You cannot create new Solution objects through the command interface.', 241)
            return result
        if str(objClass).equalsIgnoreCase('circuit'):
            DSSGlobals.makeNewCircuit(str(objName))
            Utilities.clearEventLog()
            # start the event log in the current directory
        else:
            # everything else must be a circuit element or DSS object
            handle = cls.addObject(str(objClass), str(objName))
        if handle == 0:
            result = 1
        return result

    @classmethod
    def doEditCmd(cls):
        """edit type=xxxx name=xxxx editstring"""
        objType = str()
        objName = str()
        result = 0
        cls.getObjClassAndName(objType, objName)
        if str(objType).equalsIgnoreCase('circuit'):
            # do nothing
            pass
        else:
            # everything else must be a circuit element
            result = cls.editObject(str(objType), str(objName))
        return result

    @classmethod
    def doRedirect(cls, isCompile):
        """This routine should be recursive.
        So you can redirect input an arbitrary number of times.

        If isCompile, makes directory of the file the new home directory.
        If not isCompile (is simple redirect), return to where we started.
        """
        currDir = ''
        result = 0
        # get next parm and try to interpret as a file name
        paramName = Parser.getInstance().getNextParam()
        ExecCommands.getInstance().setRedirFile(Utilities.expandFileName(Parser.getInstance().makeString()))
        if not (ExecCommands.getInstance().getRedirFile() == ''):
            saveDir = DSSGlobals.currentDirectory
            # couldn't find file; try appending '.dss' to the file name
            # if it doesn't already have an extension
            # OK, we finally got one open, so we're going to continue
            try:
                fr = FileReader(ExecCommands.getInstance().getRedirFile())
                if isCompile:
                    DSSGlobals.lastFileCompiled = ExecCommands.getInstance().getRedirFile()
            except FileNotFoundException, e:
                if ExecCommands.getInstance().getRedirFile().index('.') == -1:
                    ExecCommands.getInstance().setRedirFile(ExecCommands.getInstance().getRedirFile() + '.dss')
                    try:
                        fr = FileReader(ExecCommands.getInstance().getRedirFile())
                    except FileNotFoundException, ex:
                        DSSGlobals.doSimpleMsg('Redirect file: \"' + ExecCommands.getInstance().getRedirFile() + '\" not found.', 242)
                        DSSGlobals.solutionAbort = True
                        return result
                else:
                    DSSGlobals.doSimpleMsg('Redirect file: \"' + ExecCommands.getInstance().getRedirFile() + '\" not found.', 243)
                    DSSGlobals.solutionAbort = True
                    return result
                    # already had an extension
            try:
                currDir = Utilities.extractFileDir(ExecCommands.getInstance().getRedirFile())
                DSSGlobals.currentDirectory = currDir
                if isCompile:
                    DSSGlobals.setDataPath(currDir)
                DSSGlobals.redirectAbort = False
                DSSGlobals.inRedirect = True
                br = StringIO(fr)
                while (inputLine = br.readline() is not None) or DSSGlobals.redirectAbort:
                    if not DSSGlobals.solutionAbort:
                        ExecCommands.getInstance().processCommand(inputLine)
                    else:
                        DSSGlobals.redirectAbort = True
                        # abort file if solution was aborted
                if DSSGlobals.activeCircuit is not None:
                    DSSGlobals.activeCircuit.setCurrentDirectory(currDir + '\"')
                br.close()
                fr.close()
            except IOException, e:
                DSSGlobals.doErrorMsg('DoRedirect' + DSSGlobals.CRLF + 'Error processing input stream in Compile/Redirect.', e.getMessage(), 'Error in file: \"' + ExecCommands.getInstance().getRedirFile() + '\" or filename.', 244)
            finally:
                DSSGlobals.inRedirect = False
                if isCompile:
                    DSSGlobals.setDataPath(currDir)
                    # change DSSDataDirectory
                    DSSGlobals.lastCommandWasCompile = True
                else:
                    DSSGlobals.currentDirectory = saveDir
                    # set back to where we were for redirect, but not compile
            # change directory to path specified by file in case that loads in more files
        # else ignore altogether if null filename
        return result

    @classmethod
    def doSelectCmd(cls):
        """Select active object.

          select element=elementname terminal=terminalnumber
        """
        objClass = str()
        objName = str()
        result = 1
        cls.getObjClassAndName(objClass, objName)
        # parse object class and name
        if len(str(objClass)) == 0 and len(str(objName)) == 0:
            return result
            # select active obj if any
        if str(objClass).equalsIgnoreCase('circuit'):
            cls.setActiveCircuit(str(objName))
        else:
            # everything else must be a circuit element
            if len(str(objClass)) > 0:
                DSSClassDefs.setObjectClass(str(objClass))
            DSSGlobals.activeDSSClass = DSSGlobals.DSSClassList.get(DSSGlobals.lastClassReferenced)
            if DSSGlobals.activeDSSClass is not None:
                if not DSSGlobals.activeDSSClass.setActive(str(objName)):
                    # scroll through list of objects until a match
                    DSSGlobals.doSimpleMsg('Error: Object \"' + str(objName) + '\" not found.' + DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 245)
                    result = 0
                else:
                    _0 = DSSGlobals.activeDSSObject.getDSSObjType()
                    _1 = False
                    while True:
                        if _0 == DSSClassDefs.DSS_OBJECT:
                            _1 = True
                            break
                        if True:
                            _1 = True
                            DSSGlobals.activeCircuit.setActiveCktElement(DSSGlobals.activeDSSClass.getActiveObj())
                            # now check for active terminal designation
                            paramName = Parser.getInstance().getNextParam().toLowerCase()
                            param = Parser.getInstance().makeString()
                            if len(param) > 0:
                                DSSGlobals.activeCircuit.getActiveCktElement().setActiveTerminalIdx(Parser.getInstance().makeInteger())
                            else:
                                DSSGlobals.activeCircuit.getActiveCktElement().setActiveTerminalIdx(0)
                            DSSGlobals.setActiveBus(DSSGlobals.activeCircuit.getActiveCktElement().getBus(DSSGlobals.activeCircuit.getActiveCktElement().getActiveTerminalIdx()))
                            break
                        break
            else:
                DSSGlobals.doSimpleMsg('Error: Active object type/class is not set.', 246)
                result = 0
        return result

    @classmethod
    def doMoreCmd(cls):
        """More edit string (assumes active circuit element)."""
        if DSSGlobals.activeDSSClass is not None:
            return DSSGlobals.activeDSSClass.edit()
        else:
            return 0

    @classmethod
    def doSaveCmd(cls):
        # TODO: Implement this method
        raise cls.UnsupportedOperationException()

    @classmethod
    def doClearCmd(cls):
        DSSExecutive.getInstance().clear()
        return 0

    @classmethod
    def doHelpCmd(cls):
        DSSGlobals.DSSForms.showHelpForm()
        return 0

    @classmethod
    def doSampleCmd(cls):
        """Force all monitors and meters in active circuit to take a sample."""
        DSSGlobals.monitorClass.sampleAll()
        DSSGlobals.energyMeterClass.sampleAll()
        # gets generators too
        return 0

    @classmethod
    def doSolveCmd(cls):
        # just invoke solution obj's editor to pick up parsing
        # and execute rest of command
        SolutionImpl.activeSolutionObj = DSSGlobals.activeCircuit.getSolution()
        return DSSGlobals.solutionClass.edit()

    @classmethod
    def setActiveCktElement(cls):
        """Parses the object off the line and sets it active as a CktElement."""
        objType = str()
        objName = str()
        result = 0
        cls.getObjClassAndName(objType, objName)
        if str(objType).equalsIgnoreCase('circuit'):
            # do nothing
            pass
        elif not str(objType).equalsIgnoreCase(DSSGlobals.activeDSSClass.getName()):
            DSSGlobals.lastClassReferenced = DSSGlobals.classNames.find(str(objType))
            _0 = DSSGlobals.lastClassReferenced
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Object type \"' + str(objType) + '\" not found.' + DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 253)
                    result = 0
                    return result
                if True:
                    _1 = True
                    DSSGlobals.activeDSSClass = DSSGlobals.DSSClassList.get(DSSGlobals.lastClassReferenced)
                    if DSSGlobals.activeDSSClass.setActive(str(objName)):
                        # scroll through list of objects until a match
                        _2 = DSSGlobals.activeDSSObject.getDSSObjType()
                        _3 = False
                        while True:
                            if _2 == DSSClassDefs.DSS_OBJECT:
                                _3 = True
                                DSSGlobals.doSimpleMsg('Error in setActiveCktElement: Object not a circuit element.' + DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 254)
                                break
                            if True:
                                _3 = True
                                DSSGlobals.activeCircuit.setActiveCktElement(DSSGlobals.activeDSSClass.getActiveObj())
                                result = 1
                            break
                    break
                break
        return result

    @classmethod
    def doEnableCmd(cls):
        objType = str()
        objName = str()
        # Result = setActiveCktElement();
        # if (Result > 0) DSSGlobals.activeCircuit.getActiveCktElement().setEnabled(true);
        result = 0
        cls.getObjClassAndName(objType, objName)
        if str(objType).equalsIgnoreCase('circuit'):
            # do nothing
            pass
        elif len(str(objType)) > 0:
            # only applies to CktElementClass objects
            classPtr = DSSClassDefs.getDSSClass(str(objType))
            if classPtr is not None:
                if classPtr.getDSSClassType() & DSSClassDefs.BASECLASSMASK > 0:
                    # everything else must be a circuit element
                    if str(objName) == '*':
                        # enable all elements of this class
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < classPtr.getElementCount()):
                                break
                            cktElem = classPtr.getElementList().get(i)
                            cktElem.setEnabled(True)
                    else:
                        # just load up the parser and call the edit routine for the object in question
                        Parser.getInstance().setCmdString('enabled=true')
                        # will only work for CktElements
                        result = cls.editObject(str(objType), str(objName))
        return result

    @classmethod
    def doDisableCmd(cls):
        objType = str()
        objName = str()
        result = 0
        cls.getObjClassAndName(objType, objName)
        if str(objType).equalsIgnoreCase('circuit'):
            # do nothing
            pass
        elif len(str(objType)) > 0:
            # only applies to CktElementClass objects
            classPtr = DSSClassDefs.getDSSClass(str(objType))
            if classPtr is not None:
                if classPtr.getDSSClassType() & DSSClassDefs.BASECLASSMASK > 0:
                    # everything else must be a circuit element
                    if str(objName) == '*':
                        # disable all elements of this class
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < classPtr.getElementCount()):
                                break
                            cktElem = classPtr.getElementList().get(i)
                            cktElem.setEnabled(False)
                else:
                    # just load up the parser and call the edit routine for the object in question
                    Parser.getInstance().setCmdString('Enabled=false')
                    # will only work for CktElements
                    result = cls.editObject(str(objType), str(objName))
        # Result = setActiveCktElement();
        # if (Result > 0) getActiveCircuit().getActiveCktElement().setEnabled(false);
        return result

    @classmethod
    def doPropertyDump(cls):
        # TODO Implement this method.
        raise cls.UnsupportedOperationException()

    @classmethod
    def setTime(cls):
        """For interpreting time specified as an array "hour, sec"."""
        timeArray = [None] * 2
        Parser.getInstance().parseAsVector(2, timeArray)
        solution = DSSGlobals.activeCircuit.getSolution()
        solution.setIntHour(timeArray[0])
        solution.getDynaVars().t = timeArray[1]
        solution.updateDblHour()

    @classmethod
    def setActiveCircuit(cls, cktName):
        for ckt in DSSGlobals.circuits:
            if ckt.getName().equalsIgnoreCase(cktName):
                DSSGlobals.activeCircuit = ckt
                return
        DSSGlobals.doSimpleMsg('Error: No circuit named \"' + cktName + '\" found.' + DSSGlobals.CRLF + 'Active circuit not changed.', 258)

    @classmethod
    def doLegalVoltageBases(cls):
        dummy = [None] * 100
        # big buffer
        num = Parser.getInstance().parseAsVector(100, dummy)
        # Parsing zero-fills the array
        # LegalVoltageBases is a zero-terminated array, so we have to allocate
        # one more than the number of actual values}

        ckt = DSSGlobals.activeCircuit
        ckt.setLegalVoltageBases([None] * (num + 1))
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < num + 1):
                break
            ckt.getLegalVoltageBases()[i] = dummy[i]

    @classmethod
    def doOpenCmd(cls):
        """Opens a terminal and conductor of a ckt element.

        Syntax: "open class.name term=xx cond=xx"
        If cond is omitted, all conductors are opened.
        """
        parser = Parser.getInstance()
        retval = cls.setActiveCktElement()
        if retval > 0:
            paramName = parser.getNextParam()
            terminal = parser.makeInteger()
            paramName = parser.getNextParam()
            conductor = parser.makeInteger()
            ckt = DSSGlobals.activeCircuit
            ckt.getActiveCktElement().setActiveTerminalIdx(terminal)
            ckt.getActiveCktElement().setConductorClosed(conductor, False)
            DSSGlobals.setActiveBus(Utilities.stripExtension(ckt.getActiveCktElement().getBus(ckt.getActiveCktElement().getActiveTerminalIdx())))
        else:
            DSSGlobals.doSimpleMsg('Error in Open command: Circuit element not found.' + DSSGlobals.CRLF + parser.getCmdString(), 259)
        return 0

    @classmethod
    def doCloseCmd(cls):
        """Closes a terminal and conductor of a ckt element.

        Syntax: "close class.name term=xx cond=xx"
        If cond is omitted, all conductors are opened.
        """
        parser = Parser.getInstance()
        retval = cls.setActiveCktElement()
        if retval > 0:
            paramName = parser.getNextParam()
            terminal = parser.makeInteger()
            paramName = parser.getNextParam()
            conductor = parser.makeInteger()
            ckt = DSSGlobals.activeCircuit
            ckt.getActiveCktElement().setActiveTerminalIdx(terminal)
            ckt.getActiveCktElement().setConductorClosed(conductor, True)
            DSSGlobals.setActiveBus(Utilities.stripExtension(ckt.getActiveCktElement().getBus(ckt.getActiveCktElement().getActiveTerminalIdx())))
        else:
            DSSGlobals.doSimpleMsg('Error in Close command circuit element not found.' + DSSGlobals.CRLF + parser.getCmdString(), 260)
        return 0

    @classmethod
    def doResetCmd(cls):
        parser = Parser.getInstance()
        result = 0
        # get next parm and try to interpret as a file name
        paramName = parser.getNextParam()
        param = parser.makeString().toUpperCase()
        if len(param) == 0:
            cls.doResetMonitors()
            cls.doResetMeters()
            Utilities.doResetFaults()
            Utilities.doResetControls()
            Utilities.clearEventLog()
            Utilities.doResetKeepList()
        else:
            _0 = param[0]
            _1 = False
            while True:
                if _0 == 'M':
                    _1 = True
                    _2 = param[1]
                    _3 = False
                    while True:
                        if _2 == 'O':
                            _3 = True
                            cls.doResetMonitors()
                            break
                        if (_3 is True) or (_2 == 'E'):
                            _3 = True
                            cls.doResetMeters()
                            break
                        break
                    break
                if (_1 is True) or (_0 == 'F'):
                    _1 = True
                    Utilities.doResetFaults()
                    break
                if (_1 is True) or (_0 == 'C'):
                    _1 = True
                    Utilities.doResetControls()
                    break
                if (_1 is True) or (_0 == 'E'):
                    _1 = True
                    Utilities.clearEventLog()
                    break
                if (_1 is True) or (_0 == 'K'):
                    _1 = True
                    Utilities.doResetKeepList()
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown argument to Reset command: \"' + param + '\"', 261)
                    break
                break
        return 0

    @classmethod
    def markCapAndReactorBuses(cls):
        # Mark all buses as keepers if there are capacitors or reactors on them
        cls = DSSClassDefs.getDSSClass('capacitor')
        if cls is not None:
            objRef = cls.getFirst()
            while objRef > 0:
                capElement = DSSGlobals.activeDSSObject
                if capElement.isShunt():
                    if capElement.isEnabled():
                        DSSGlobals.activeCircuit.getBuses()[capElement.getTerminals()[0].getBusRef()].setKeep(True)
                objRef = cls.getNext()
        # Now get the reactors
        cls = DSSClassDefs.getDSSClass('reactor')
        if cls is not None:
            objRef = cls.getFirst()
            while objRef > 0:
                reacElement = DSSGlobals.activeDSSObject
                if reacElement.isShunt():
                    try:
                        if reacElement.isEnabled():
                            DSSGlobals.activeCircuit.getBuses()[reacElement.getTerminals()[0].getBusRef()].setKeep(True)
                    except Exception, e:
                        DSSGlobals.doSimpleMsg(String.format.format('%s %s reactor=%s Bus No.=%d ', e.getMessage(), DSSGlobals.CRLF, reacElement.getName(), reacElement.getNodeRef()[0]), 9999)
                objRef = cls.getNext()

    @classmethod
    def doReduceCmd(cls):
        result = 0
        # get next parm and try to interpret as a file name
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString().toUpperCase()
        # Mark capacitor and reactor buses as keep so we don't lose them
        cls.markCapAndReactorBuses()
        if len(param) == 0:
            param = 'A'
        _0 = param[0]
        _1 = False
        while True:
            if _0 == 'A':
                _1 = True
                for MeterObj in DSSGlobals.activeCircuit.getEnergyMeters():
                    MeterObj.reduceZone()
                break
            if True:
                _1 = True
                devClassIndex = DSSGlobals.classNames.find('energymeter')
                if devClassIndex > 0:
                    # TODO Check zero indexing
                    meterClass = DSSGlobals.DSSClassList.get(devClassIndex)
                    if meterClass.setActive(param):
                        # try to set it active
                        MeterObj = meterClass.getActiveObj()
                        MeterObj.reduceZone()
                    else:
                        DSSGlobals.doSimpleMsg('EnergyMeter \"' + param + '\" not found.', 262)
                break
            break
        return 0

    @classmethod
    def doResetMonitors(cls):
        for mon in DSSGlobals.activeCircuit.getMonitors():
            mon.resetIt()
        return 0

    @classmethod
    def doFileEditCmd(cls):
        # get next parm and try to interpret as a file name
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        if File(param).exists():
            Utilities.fireOffEditor(param)
        else:
            DSSGlobals.globalResult = 'File \"' + param + '\" does not exist.'
        return 1

    @classmethod
    def parseObjName(cls, fullName, objName, propName):
        """Parse strings such as
            1. Classname.Objectname,Property  (full name)
            2. Objectname.Property            (classname omitted)
            3. Property                       (classname and objectname omitted
        """
        dotpos1 = fullName.find('.')
        _0 = dotpos1
        _1 = False
        while True:
            if _0 == -1:
                _1 = True
                objName.delete(0, len(objName))
                propName.setLength(len(fullName))
                propName.replace(0, len(fullName), fullName)
                break
            if True:
                _1 = True
                tmpName = fullName[dotpos1 + 1:-dotpos1]
                # TODO check indexing
                propName.setLength(len(tmpName))
                propName.replace(0, len(tmpName), tmpName)
                dotpos2 = propName.index('.')
                _2 = dotpos2
                _3 = False
                while True:
                    if _2 == -1:
                        _3 = True
                        tmpName = fullName[:dotpos1 - 1]
                        objName.setLength(len(tmpName))
                        objName.replace(0, len(tmpName), tmpName)
                        break
                    if True:
                        _3 = True
                        tmpName = fullName[:(dotpos1 + dotpos2) - 1]
                        objName.setLength(len(tmpName))
                        objName.replace(0, len(tmpName), tmpName)
                        tmpName = propName[dotpos2 + 1:-dotpos2]
                        propName.setLength(len(tmpName))
                        propName.replace(0, len(tmpName), tmpName)
                        break
                    break
                break
            break

    @classmethod
    def doQueryCmd(cls):
        """? Command
        Syntax: "? Line.Line1.R1"
        """
        objName = str()
        propName = str()
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        cls.parseObjName(param, objName, propName)
        if str(objName).equalsIgnoreCase('solution'):
            # special for solution
            DSSGlobals.activeDSSClass = DSSGlobals.solutionClass
            DSSGlobals.activeDSSObject = DSSGlobals.activeCircuit.getSolution()
        else:
            # set object active
            Parser.getInstance().setCmdString('\"' + str(objName) + '\"')
            cls.doSelectCmd()
        # put property value in global variable
        propIndex = DSSGlobals.activeDSSClass.propertyIndex(str(propName))
        if propIndex > 0:
            DSSGlobals.globalPropertyValue = DSSGlobals.activeDSSObject.getPropertyValue(propIndex)
        else:
            DSSGlobals.globalPropertyValue = 'Property unknown'
        DSSGlobals.globalResult = DSSGlobals.globalPropertyValue
        # MessageDlg(Param + ' = ' + GlobalPropertyValue,  mtCustom, [mbOK], 0);
        return result

    @classmethod
    def doResetMeters(cls):
        DSSGlobals.energyMeterClass.resetAll()
        return 0

    @classmethod
    def doNextCmd(cls):
        # get next parm and try to interpret as a file name
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        solution = DSSGlobals.activeCircuit.getSolution()
        _0 = param.toUpperCase()[0]
        _1 = False
        while True:
            if _0 == 'Y':
                _1 = True
                solution.setYear(solution.getYear() + 1)
                # year
                break
            if (_1 is True) or (_0 == 'H'):
                _1 = True
                solution.setIntHour(solution.getIntHour() + 1)
                # hour
                break
            if (_1 is True) or (_0 == 'T'):
                _1 = True
                solution.incrementTime()
                # time
                break
            break
        return 0

    @classmethod
    def doSetVoltageBases(cls):
        DSSGlobals.activeCircuit.getSolution().setVoltageBases()
        return 0

    @classmethod
    def doAboutBox(cls):
        if DSSGlobals.noFormsAllowed:
            return
        DSSGlobals.DSSForms.showAboutBox()

    @classmethod
    def addObject(cls, objType, name):
        parser = Parser.getInstance()
        handle = -1
        # search for class if not already active
        # if nothing specified, lastClassReferenced remains
        if not objType.equalsIgnoreCase(DSSGlobals.activeDSSClass.getName()):
            DSSGlobals.lastClassReferenced = DSSGlobals.classNames.find(objType)
        _0 = DSSGlobals.lastClassReferenced
        _1 = False
        while True:
            if _0 == -1:
                _1 = True
                DSSGlobals.doSimpleMsg('New command: Object type \"' + objType + '\" not found.' + DSSGlobals.CRLF + parser.getCmdString(), 263)
                handle = 0
                return handle
            if True:
                _1 = True
                DSSGlobals.activeDSSClass = DSSGlobals.DSSClassList.get(DSSGlobals.lastClassReferenced)
                # name must be supplied
                if len(name) == 0:
                    DSSGlobals.doSimpleMsg('Object name missing' + DSSGlobals.CRLF + parser.getCmdString(), 264)
                    return handle
                # now let's make a new object or set an existing one active, whatever the case
                # activeDSSObject now points to the object just added
                # if a circuit element, activeCktElement in activeCircuit is also set
                _2 = DSSGlobals.activeDSSClass.getDSSClassType()
                _3 = False
                while True:
                    if _2 == DSSClassDefs.DSS_OBJECT:
                        _3 = True
                        if not DSSGlobals.activeDSSClass.setActive(name):
                            handle = DSSGlobals.activeDSSClass.newObject(name)
                            # stick in object list to keep track of it
                            DSSGlobals.DSSObjs.add(DSSGlobals.activeDSSObject)
                        break
                    if True:
                        _3 = True
                        if DSSGlobals.activeCircuit is None:
                            DSSGlobals.doSimpleMsg('You must create a circuit first: \"new circuit.cktname\"', 265)
                            return handle
                        # if object already exists, treat as an "edit" if duplicates not allowed
                        if DSSGlobals.activeCircuit.isDuplicatesAllowed():
                            handle = DSSGlobals.activeDSSClass.newObject(name)
                            # returns index into this class
                            DSSGlobals.activeCircuit.addCktElement(handle)
                            # adds active object to active circuit
                        elif not DSSGlobals.activeDSSClass.setActive(name):
                            handle = DSSGlobals.activeDSSClass.newObject(name)
                            # returns index into this class
                            DSSGlobals.activeCircuit.addCktElement(handle)
                            # adds active object to active circuit
                        else:
                            DSSGlobals.doSimpleMsg('Warning: Duplicate new element definition: \"' + DSSGlobals.activeDSSClass.getName() + '.' + name + '\"' + DSSGlobals.CRLF + 'Element being redefined.', 266)
                        # check to see if we can set it active first
                        break
                    break
                if handle > 0:
                    DSSGlobals.activeDSSObject.setClassIndex(handle)
                    # process remaining instructions on the command line
                DSSGlobals.activeDSSClass.edit()
                break
            break
        return handle

    @classmethod
    def editObject(cls, objType, name):
        result = 0
        DSSGlobals.lastClassReferenced = DSSGlobals.classNames.find(objType)
        _0 = DSSGlobals.lastClassReferenced
        _1 = False
        while True:
            if _0 == -1:
                _1 = True
                DSSGlobals.doSimpleMsg('Edit command: Object type \"' + objType + '\" not found.' + DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 267)
                result = 0
                return result
            if True:
                _1 = True
                DSSGlobals.activeDSSClass = DSSGlobals.DSSClassList.get(DSSGlobals.lastClassReferenced)
                if DSSGlobals.activeDSSClass.setActive(name):
                    result = DSSGlobals.activeDSSClass.edit()
                    # edit the active object
                break
            break
        return result

    @classmethod
    def doSetkVBase(cls):
        result = 0
        # parse off next two items on line
        paramName = Parser.getInstance().getNextParam()
        busName = Parser.getInstance().makeString()
        paramName = Parser.getInstance().getNextParam()
        kVValue = Parser.getInstance().makeDouble()
        # now find the bus and set the value
        ckt = DSSGlobals.activeCircuit
        ckt.setActiveBusIndex(ckt.getBusList().find(busName))
        if ckt.getActiveBusIndex() > 0:
            if paramName.equalsIgnoreCase('kvln'):
                ckt.getBuses()[ckt.getActiveBusIndex()].setKVBase(kVValue)
            else:
                ckt.getBuses()[ckt.getActiveBusIndex()].setKVBase(kVValue / DSSGlobals.SQRT3)
            result = 0
            ckt.getSolution().setVoltageBaseChanged(True)
            # Solution.setSolutionInitialized(false);  // force reinitialization
        else:
            result = 1
            DSSGlobals.appendGlobalResult('Bus ' + busName + ' not found.')
        return result

    @classmethod
    def doAutoAddBusList(cls, s):
        """Syntax can be either a list of bus names or a file specification:
            file= ...
        """
        DSSGlobals.activeCircuit.getAutoAddBusList().clear()
        # Load up auxiliary parser to reparse the array list or file name
        DSSGlobals.auxParser.setCmdString(s)
        parmName = DSSGlobals.auxParser.getNextParam()
        param = DSSGlobals.auxParser.makeString()
        # Syntax can be either a list of bus names or a file specification: file= ...
        if parmName.equalsIgnoreCase('file'):
            # load the list from a file
            try:
                F = File(param)
                fis = FileInputStream(F)
                dis = DataInputStream(fis)
                br = StringIO(InputStreamReader(dis))
                while s2 = br.readline() is not None:
                    DSSGlobals.auxParser.setCmdString(s2)
                    parmName = DSSGlobals.auxParser.getNextParam()
                    param = DSSGlobals.auxParser.makeString()
                    if len(param) > 0:
                        DSSGlobals.activeCircuit.getAutoAddBusList().add(param)
                br.close()
                dis.close()
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error trying to read bus list file. Error is: ' + e.getMessage(), 268)
        else:
            # parse bus names off of array list
            while # TODO Check zero indexinglen(param) > 0:
                DSSGlobals.activeCircuit.getAutoAddBusList().add(param)
                DSSGlobals.auxParser.getNextParam()
                param = DSSGlobals.auxParser.makeString()

    @classmethod
    def doKeeperBusList(cls, s):
        """Set keep flag on buses found in list so they aren't eliminated by
        some reduction algorithm.  This command is cumulative. To clear flag,
        use Reset keepList.

        Syntax can be either a list of bus names or a file specification: file= ...
        """
        # load up auxiliary parser to reparse the array list or file name
        DSSGlobals.auxParser.setCmdString(s)
        parmName = DSSGlobals.auxParser.getNextParam()
        param = DSSGlobals.auxParser.makeString()
        if parmName.equalsIgnoreCase('file'):
            # load the list from a file
            try:
                f = File(param)
                fis = FileInputStream(f)
                dis = DataInputStream(fis)
                br = StringIO(InputStreamReader(dis))
                while s2 = br.readline() is not None:
                    DSSGlobals.auxParser.setCmdString(s2)
                    parmName = DSSGlobals.auxParser.getNextParam()
                    param = DSSGlobals.auxParser.makeString()
                    if len(param) > 0:
                        ckt = DSSGlobals.activeCircuit
                        iBus = ckt.getBusList().find(param)
                        if iBus > 0:
                            ckt.getBuses()[iBus].setKeep(True)
                br.close()
                dis.close()
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error trying to read bus list file ' + param + '. Error is: ' + e.getMessage(), 269)
        else:
            # parse bus names off of array list
            while len(param) > 0:
                ckt = DSSGlobals.activeCircuit
                iBus = ckt.getBusList().find(param)
                if iBus > 0:
                    ckt.getBuses()[iBus].setKeep(True)
                DSSGlobals.auxParser.getNextParam()
                param = DSSGlobals.auxParser.makeString()

    @classmethod
    def doCktLossesCmd(cls):
        result = 0
        if DSSGlobals.activeCircuit is not None:
            DSSGlobals.globalResult = ''
            lossValue = DSSGlobals.activeCircuit.getLosses()
            DSSGlobals.globalResult = String.format.format('%10.5g, %10.5g', lossValue.getReal() * 0.001, lossValue.getImaginary() * 0.001)
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doCurrentsCmd(cls):
        result = 0
        if DSSGlobals.activeCircuit is not None:
            cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
            nValues = cktElem.getNConds() * cktElem.getNTerms()
            DSSGlobals.globalResult = ''
            cBuffer = [None] * nValues
            cktElem.getCurrents(cBuffer)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nValues):
                    break
                DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %6.1f,', cBuffer[i].abs(), ComplexUtil.degArg(cBuffer[i]))
            cBuffer = None
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doLossesCmd(cls):
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveCktElement() is not None:
                DSSGlobals.globalResult = ''
                lossValue = ckt.getActiveCktElement().getLosses()
                DSSGlobals.globalResult = String.format.format('%10.5g, %10.5g', lossValue.getReal() * 0.001, lossValue.getImaginary() * 0.001)
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doPhaseLossesCmd(cls):
        """Returns phase losses in kW, kVAr."""
        result = 0
        if DSSGlobals.activeCircuit is not None:
            cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
            nValues = cktElem.getNPhases()
            cBuffer = [None] * nValues
            DSSGlobals.globalResult = ''
            cktElem.getPhaseLosses(nValues, cBuffer)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nValues):
                    break
                DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %10.5g,', cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001)
            cBuffer = None
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doPowersCmd(cls):
        result = 0
        if DSSGlobals.activeCircuit is not None:
            cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
            nValues = cktElem.getNConds() * cktElem.getNTerms()
            DSSGlobals.globalResult = ''
            cBuffer = [None] * nValues
            cktElem.getPhasePower(cBuffer)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nValues):
                    break
                DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %10.5g,', cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001)
            cBuffer = None
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doSeqCurrentsCmd(cls):
        """All sequence currents of active circuit element.
        Returns magnitude only.
        """
        Iph = [None] * 3
        I012 = [None] * 3
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveCktElement() is not None:
                cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
                DSSGlobals.globalResult = ''
                if cktElem.getNPhases() < 3:
                    _0 = True
                    i = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            i += 1
                        if not (i < 3 * cktElem.getNTerms()):
                            break
                        DSSGlobals.globalResult = DSSGlobals.globalResult + ' -1.0,'
                        # signify n/a
                else:
                    nValues = cktElem.getNConds() * cktElem.getNTerms()
                    cBuffer = [None] * nValues
                    cktElem.getCurrents(cBuffer)
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < cktElem.getNTerms()):
                            break
                        k = (j - 1) * cktElem.getNConds()
                        _2 = True
                        i = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                i += 1
                            if not (i < 3):
                                break
                            Iph[i] = cBuffer[k + i]
                        MathUtil.phase2SymComp(Iph, I012)
                        _3 = True
                        i = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < 3):
                                break
                            DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, ', I012[i].abs())
                    cBuffer = None
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doSeqPowersCmd(cls):
        """All seq powers of active 3-phase ciruit element.
        Returns kW + j kVAr
        """
        S = None
        Vph = [None] * 3
        V012 = [None] * 3
        Iph = [None] * 3
        I012 = [None] * 3
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveCktElement() is not None:
                cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
                DSSGlobals.globalResult = ''
                if cktElem.getNPhases() < 3:
                    _0 = True
                    i = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            i += 1
                        if not (i < (2 * 3 * cktElem.getNTerms()) - 1):
                            break
                        DSSGlobals.globalResult = DSSGlobals.globalResult + '-1.0, '
                        # signify n/a
                else:
                    nValues = cktElem.getNConds() * cktElem.getNTerms()
                    cBuffer = [None] * nValues
                    cktElem.getCurrents(cBuffer)
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < cktElem.getNTerms()):
                            break
                        k = (j - 1) * cktElem.getNConds()
                        _2 = True
                        i = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                i += 1
                            if not (i < 3):
                                break
                            Vph[i] = ckt.getSolution().getNodeV()[cktElem.getTerminals()[j].getTermNodeRef()[i]]
                        _3 = True
                        i = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < 3):
                                break
                            Iph[i] = cBuffer[k + i]
                        MathUtil.phase2SymComp(Iph, I012)
                        MathUtil.phase2SymComp(Vph, V012)
                        _4 = True
                        i = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < 3):
                                break
                            S = V012[i].multiply(I012[i].conjugate())
                        DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %10.5g,', S.getReal() * 0.003, S.getImaginary() * 0.003)
                        # 3-phase kW conversion
                cBuffer = None
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doSeqVoltagesCmd(cls):
        """All voltages of active ciruit element.
        Magnitude only.
        @return a set of seq voltages (3) for each terminal.
        """
        Vph = [None] * 3
        V012 = [None] * 3
        result = 0
        nValues = -1
        # unassigned, for exception message
        n = -1
        # unassigned, for exception message
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveCktElement() is not None:
                cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
                if cktElem.isEnabled():
                    try:
                        nValues = cktElem.getNPhases()
                        DSSGlobals.globalResult = ''
                        if nValues < 3:
                            _0 = True
                            i = 0
                            while True:
                                if _0 is True:
                                    _0 = False
                                else:
                                    i += 1
                                if not (i < 3 * cktElem.getNTerms()):
                                    break
                                DSSGlobals.globalResult = DSSGlobals.globalResult + '-1.0, '
                                # signify n/a
                        else:
                            _1 = True
                            j = 0
                            while True:
                                if _1 is True:
                                    _1 = False
                                else:
                                    j += 1
                                if not (j < cktElem.getNTerms()):
                                    break
                                k = (j - 1) * cktElem.getNConds()
                                _2 = True
                                i = 0
                                while True:
                                    if _2 is True:
                                        _2 = False
                                    else:
                                        i += 1
                                    if not (i < 3):
                                        break
                                    Vph[i] = ckt.getSolution().getNodeV()[cktElem.getNodeRef()[i + k]]
                                MathUtil.phase2SymComp(Vph, V012)
                                # compute symmetrical components
                                _3 = True
                                i = 0
                                while True:
                                    if _3 is True:
                                        _3 = False
                                    else:
                                        i += 1
                                    if not (i < 3):
                                        break
                                    DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, ', V012[i].abs())
                    except Exception, e:
                        S = e.getMessage() + DSSGlobals.CRLF + 'element=' + cktElem.getName() + DSSGlobals.CRLF + 'nValues=' + String.valueOf.valueOf(nValues) + DSSGlobals.CRLF + 'nTerms=' + String.valueOf.valueOf(cktElem.getNTerms()) + DSSGlobals.CRLF + 'nConds =' + String.valueOf.valueOf(cktElem.getNConds()) + DSSGlobals.CRLF + 'nodeRef=' + String.valueOf.valueOf(n)
                        DSSGlobals.doSimpleMsg(S, 270)
            else:
                DSSGlobals.globalResult = 'Element disabled'
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doVoltagesCmd(cls, perUnit):
        """Bus Voltages at active terminal."""
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveBusIndex() != 0:
                # TODO Check indexing.
                activeBus = ckt.getBuses()[ckt.getActiveBusIndex()]
                DSSGlobals.globalResult = ''
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < activeBus.getNumNodesThisBus()):
                        break
                    volts = ckt.getSolution().getNodeV()[activeBus.getRef(i)]
                    VMag = volts.abs()
                    if perUnit and activeBus.getKVBase() > 0.0:
                        VMag = (VMag * 0.001) / activeBus.getKVBase()
                        DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %6.1f, ', VMag, ComplexUtil.degArg(volts))
                    else:
                        DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%10.5g, %6.1f, ', VMag, ComplexUtil.degArg(volts))
            else:
                DSSGlobals.globalResult = 'No active bus'
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doZscCmd(cls, ZMatrix):
        """Bus short circuit matrix."""
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveBusIndex() != 0:
                # FIXME: Bus indexing.
                activeBus = ckt.getBuses()[ckt.getActiveBusIndex()]
                DSSGlobals.globalResult = ''
                if activeBus.getZsc() is None:
                    return result
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < activeBus.getNumNodesThisBus()):
                        break
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < activeBus.getNumNodesThisBus()):
                            break
                        if ZMatrix:
                            Z = activeBus.getZsc().getElement(i, j)
                        else:
                            Z = activeBus.getYsc().getElement(i, j)
                        DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%-.5g, %-.5g,   ', Z.getReal(), Z.getImaginary())
            else:
                DSSGlobals.globalResult = 'No active bus'
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doZsc10Cmd(cls):
        """Bus Short Circuit matrix."""
        result = 0
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            if ckt.getActiveBusIndex() != 0:
                # FIXME: Bus indexing.
                activeBus = ckt.getBuses()[ckt.getActiveBusIndex()]
                DSSGlobals.globalResult = ''
                if activeBus.getZsc() is None:
                    Z = activeBus.getZsc1()
                    DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('Z1, %-.5g, %-.5g, ', Z.getReal(), Z.getImaginary()) + DSSGlobals.CRLF
                    Z = activeBus.getZsc0()
                    DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('Z0, %-.5g, %-.5g, ', Z.getReal(), Z.getImaginary())
            else:
                DSSGlobals.globalResult = 'No active bus'
        else:
            DSSGlobals.globalResult = 'No active circuit'
        return result

    @classmethod
    def doAllocateLoadsCmd(cls):
        """Requires an EnergyMeter object at the head of the feeder.
        Adjusts loads defined by connected kVA or kWh billing.
        """
        result = 0
        ckt = DSSGlobals.activeCircuit
        ckt.setLoadMultiplier(1.0)
        # setter has side effects
        ckt.getSolution().setMode(Dynamics.SNAPSHOT)
        ckt.getSolution().solve()
        # make guess based on present allocation factors
        # Allocation loop -- make maxAllocationIterations iterations
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < DSSGlobals.maxAllocationIterations):
                break
            # Do energy meters
            for meter in ckt.getEnergyMeters():
                meter.calcAllocationFactors()
            # Now do other sensors
            for sensor in ckt.getSensors():
                sensor.calcAllocationFactors()
            # Now let the energy meters run down the circuit setting the loads
            for meter in ckt.getEnergyMeters():
                meter.allocateLoad()
            ckt.getSolution().solve()
            # update the solution
        return result

    @classmethod
    def doSetAllocationFactors(cls, x):
        if x < 0.0:
            DSSGlobals.doSimpleMsg('Allocation factor must be greater than zero.', 271)
        else:
            for load in DSSGlobals.activeCircuit.getLoads():
                load.setKVAAllocationFactor(x)

    @classmethod
    def doSetCFactors(cls, x):
        if x <= 0.0:
            DSSGlobals.doSimpleMsg('CFactor must be greater than zero.', 271)
        else:
            for load in DSSGlobals.activeCircuit.getLoads():
                load.setCFactor(x)

    @classmethod
    def doHarmonicsList(cls, s):
        result = 0
        solution = DSSGlobals.activeCircuit.getSolution()
        if s.equalsIgnoreCase('ALL'):
            solution.setDoAllHarmonics(True)
        else:
            solution.setDoAllHarmonics(False)
            Dummy = [None] * 100
            # big buffer
            Num = Parser.getInstance().parseAsVector(100, Dummy)
            # Parsing zero-fills the array
            solution.setHarmonicListSize(Num)
            Utilities.resizeArray(solution.getHarmonicList(), solution.getHarmonicListSize())
            # FIXME set list
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < solution.getHarmonicListSize()):
                    break
                solution.getHarmonicList()[i] = Dummy[i]
            Dummy = None
        return result

    @classmethod
    def doFormEditCmd(cls):
        if DSSGlobals.noFormsAllowed:
            return 0
        cls.doSelectCmd()
        # select active object
        if DSSGlobals.activeDSSObject is not None:
            DSSGlobals.DSSForms.showPropEditForm()
        else:
            DSSGlobals.doSimpleMsg('Element not found.', 272)
        return 1

    @classmethod
    def doMeterTotals(cls):
        if DSSGlobals.activeCircuit is not None:
            DSSGlobals.activeCircuit.totalizeMeters()
            # Now export to global result
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < EnergyMeterObj.NumEMRegisters):
                    break
                DSSGlobals.appendGlobalResult(String.format.format('%-.6g', DSSGlobals.activeCircuit.getRegisterTotals()[i]))
        return 0

    @classmethod
    def doCapacityCmd(cls):
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                _0 = paramName[0]
                _1 = False
                while True:
                    if _0 == 's':
                        _1 = True
                        paramPointer = 1
                        break
                    if (_1 is True) or (_0 == 'i'):
                        _1 = True
                        paramPointer = 2
                        break
                    if True:
                        _1 = True
                        paramPointer = 0
                        break
                    break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for capacity command', 273)
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    DSSGlobals.activeCircuit.setCapacityStart(Parser.getInstance().makeDouble())
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    DSSGlobals.activeCircuit.setCapacityIncrement(Parser.getInstance().makeDouble())
                    break
                break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        ckt = DSSGlobals.activeCircuit
        if ckt.computeCapacity():
            # totalizes energy meters at end
            DSSGlobals.globalResult = String.format.format('%-.6g', ckt.getRegisterTotals()[3] + ckt.getRegisterTotals()[19])
            # peak kW in meters
            DSSGlobals.appendGlobalResult(String.format.format('%-.6g', ckt.getLoadMultiplier()))
        return 0

    @classmethod
    def doClassesCmd(cls):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < DSSClassDefs.getNumIntrinsicClasses()):
                break
            DSSGlobals.appendGlobalResult(DSSGlobals.DSSClassList.get(i).getName())
        return 0

    @classmethod
    def doUserClassesCmd(cls):
        if DSSClassDefs.getNumUserClasses() == 0:
            DSSGlobals.appendGlobalResult('No user classes defined.')
        else:
            _0 = True
            i = DSSClassDefs.getNumIntrinsicClasses() + 1
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(DSSGlobals.DSSClassList)):
                    break
                DSSGlobals.appendGlobalResult(DSSGlobals.DSSClassList.get(i).getName())
        return 0

    @classmethod
    def doZscRefresh(cls):
        result = 1
        try:
            ckt = DSSGlobals.activeCircuit
            solution = ckt.getSolution()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                solution.getCurrents()[i] = Complex.ZERO
                # clear currents array
            if (
                ckt.getActiveBusIndex() > 0 and ckt.getActiveBusIndex() <= ckt.getNumBuses()
            ):
                # TODO Check zero indexing
                if ckt.getBuses()[ckt.getActiveBusIndex()].getZsc() is None:
                    ckt.getBuses()[ckt.getActiveBusIndex()].allocateBusQuantities()
                SolutionAlgs.computeYsc(ckt.getActiveBusIndex())
                # compute Ysc for active bus
                result = 0
        except Exception, e:
            DSSGlobals.doSimpleMsg('ZscRefresh error: ' + e.getMessage() + DSSGlobals.CRLF, 274)
        return result

    @classmethod
    def doVarValuesCmd(cls):
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            # Check if PCElement
            _0 = ckt.getActiveCktElement().getDSSObjType()
            _1 = False
            while True:
                if _0 == DSSClassDefs.PC_ELEMENT:
                    _1 = True
                    cktElem = ckt.getActiveCktElement()
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < cktElem.numVariables()):
                            break
                        DSSGlobals.appendGlobalResult(String.format.format('%-.6g', cktElem.getVariable(i)))
                    break
                if True:
                    _1 = True
                    DSSGlobals.appendGlobalResult('Null')
                    break
                break
        return 0

    @classmethod
    def doVarNamesCmd(cls):
        if DSSGlobals.activeCircuit is not None:
            ckt = DSSGlobals.activeCircuit
            # Check if PCElement
            _0 = ckt.getActiveCktElement().getDSSObjType()
            _1 = False
            while True:
                if _0 == DSSClassDefs.PC_ELEMENT:
                    _1 = True
                    cktElem = ckt.getActiveCktElement()
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < cktElem.numVariables()):
                            break
                        DSSGlobals.appendGlobalResult(cktElem.variableName(i))
                    break
                if True:
                    _1 = True
                    DSSGlobals.appendGlobalResult('Null')
                    break
                break
        return 0

    @classmethod
    def doBusCoordsCmd(cls):
        """Format of file should be

          BusName, x, y

        (x, y are real values)
        """
        result = 0
        # Get next parameter on command line
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        try:
            f = File(param)
            fis = FileInputStream(f)
            dis = DataInputStream(fis)
            br = StringIO(InputStreamReader(dis))
            while s = br.readline() is not None:
                parser = DSSGlobals.auxParser
                # user aux parser to parse line
                parser.setCmdString(s)
                parser.getNextParam()
                busName = parser.makeString()
                ib = DSSGlobals.activeCircuit.getBusList().find(busName)
                if ib > 0:
                    bus = DSSGlobals.activeCircuit.getBuses()[ib]
                    parser.getNextParam()
                    bus.setX(parser.makeDouble())
                    parser.getNextParam()
                    bus.setY(parser.makeDouble())
                    bus.setCoordDefined(True)
            # ignore a bus that's not in the circuit
            br.close()
            dis.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Bus coordinate file: \"' + param + '\" not found.', 275)
        return result

    @classmethod
    def doMakePosSeq(cls):
        DSSGlobals.activeCircuit.setPositiveSequence(True)
        for cktElem in DSSGlobals.activeCircuit.getCktElements():
            cktElem.makePosSequence()
        return 0

    @classmethod
    def atLeast(cls, i, j):
        if j < i:
            return i
        else:
            return j

    @classmethod
    def doSetReduceStrategy(cls, s):
        DSSGlobals.activeCircuit.setReductionStrategyString(s)
        DSSGlobals.auxParser.setCmdString(s)
        paramName = DSSGlobals.auxParser.getNextParam()
        param = DSSGlobals.auxParser.makeString().toUpperCase()
        paramName = DSSGlobals.auxParser.getNextParam()
        param2 = DSSGlobals.auxParser.makeString()
        DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.DEFAULT)
        if len(param) == 0:
            return
            # No option given
        _0 = param[0]
        _1 = False
        while True:
            if _0 == 'B':
                _1 = True
                DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.BREAK_LOOP)
                break
            if (_1 is True) or (_0 == 'D'):
                _1 = True
                DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.DEFAULT)
                break
            if (_1 is True) or (_0 == 'E'):
                _1 = True
                DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.DANGLING)
                break
            if (_1 is True) or (_0 == 'M'):
                _1 = True
                DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.MERGE_PARALLEL)
                break
            if (_1 is True) or (_0 == 'T'):
                _1 = True
                DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.TAP_ENDS)
                DSSGlobals.activeCircuit.setReductionMaxAngle(15.0)
                # default
                if len(param2) > 0:
                    DSSGlobals.activeCircuit.setReductionMaxAngle(DSSGlobals.auxParser.makeDouble())
                break
            if (_1 is True) or (_0 == 'S'):
                _1 = True
                if Utilities.compareTextShortest(param, 'SWITCH') == 0:
                    DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.SWITCHES)
                else:
                    DSSGlobals.activeCircuit.setReductionZmag(0.02)
                    DSSGlobals.activeCircuit.setReductionStrategy(ReductionStrategyType.STUBS)
                    if len(param2) > 0:
                        DSSGlobals.activeCircuit.setReductionZmag(DSSGlobals.auxParser.makeDouble())
                break
            if True:
                _1 = True
                DSSGlobals.doSimpleMsg('Unknown reduction strategy: \"' + s + '\".', 276)
                break
            break

    @classmethod
    def doInterpolateCmd(cls):
        """Interpolate bus coordinates in meter zones."""
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString().toUpperCase()
        # initialize the checked flag for all circuit elements
        ckt = DSSGlobals.activeCircuit
        for cktElem in ckt.getCktElements():
            cktElem.setChecked(False)
        if len(param) == 0:
            param = 'A'
        _0 = param[0]
        _1 = False
        while True:
            if _0 == 'A':
                _1 = True
                for MetObj in ckt.getEnergyMeters():
                    MetObj.interpolateCoordinates()
                break
            if True:
                _1 = True
                devClassIndex = DSSGlobals.classNames.find('energymeter')
                if devClassIndex > 0:
                    meterClass = DSSGlobals.DSSClassList.get(devClassIndex)
                    if meterClass.setActive(param):
                        # Try to set it active
                        MetObj = meterClass.getActiveObj()
                        MetObj.interpolateCoordinates()
                    else:
                        DSSGlobals.doSimpleMsg('EnergyMeter \"' + param + '\" not found.', 277)
                break
            break
        return result

    @classmethod
    def doAlignFileCmd(cls):
        """Rewrites designated file, aligning the fields into columns."""
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        if File(param).exists():
            if not Utilities.rewriteAlignedFile(param):
                result = 1
        else:
            DSSGlobals.doSimpleMsg('File \"' + param + '\" does not exist.', 278)
            result = 1
        if result == 0:
            Utilities.fireOffEditor(DSSGlobals.globalResult)
        return result

    @classmethod
    def doTOPCmd(cls):
        """Sends Monitors, Loadshapes, GrowthShapes, or TCC Curves to TOP
        as an STO file.
        """
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString().toUpperCase()
        paramName = Parser.getInstance().getNextParam()
        objName = Parser.getInstance().makeString().toUpperCase()
        if len(objName) == 0:
            objName = 'ALL'
        _0 = param[0]
        _1 = False
        while True:
            if _0 == 'L':
                _1 = True
                DSSGlobals.loadShapeClass.TOPExport(objName)
                break
            if (_1 is True) or (_0 == 'T'):
                _1 = True
                DSSGlobals.TShapeClass.TOPExport(objName)
                break
                # case 'G':
                # DSSGlobals.getInstance().getGrowthShapeClass().tOPExportAll();
                # break;
                # case 'T':
                # DSSGlobals.getInstance().getTCC_CurveClass().tOPExportAll();
                # break;
            if True:
                _1 = True
                DSSGlobals.monitorClass.TOPExport(objName)
                break
            break
        return result

    @classmethod
    def doSetNormal(cls, pctNormal):
        ckt = DSSGlobals.activeCircuit
        if ckt is not None:
            pctNormal = pctNormal * 0.01
            # FIXME local copy only
            for line in ckt.getLines():
                line.setNormAmps(pctNormal * line.getEmergAmps())

    @classmethod
    def doRotateCmd(cls):
        """Rotate about the center of the coordinates."""
        result = 0
        ckt = DSSGlobals.activeCircuit
        if ckt is not None:
            paramName = Parser.getInstance().getNextParam()
            angle = (Parser.getInstance().makeDouble() * DSSGlobals.PI) / 180.0
            # deg to rad
            a = Complex(cls.Math.cos(angle), cls.Math.sin(angle))
            xmin = 1e+50
            xmax = -1e+50
            ymin = 1e+50
            ymax = -1e+50
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                if ckt.getBuses()[i].isCoordDefined():
                    bus = ckt.getBuses()[i]
                    xmax = cls.Math.max(xmax, bus.getX())
                    xmin = cls.Math.min(xmin, bus.getX())
                    ymax = cls.Math.max(ymax, bus.getY())
                    ymin = cls.Math.min(ymin, bus.getY())
            xc = (xmax + xmin) / 2.0
            yc = (ymax + ymin) / 2.0
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                if ckt.getBuses()[i].isCoordDefined():
                    bus = ckt.getBuses()[i]
                    vector = Complex(bus.getX() - xc, bus.getY() - yc)
                    vector = vector.multiply(a)
                    bus.setX(xc + vector.getReal())
                    bus.setY(yc + vector.getImaginary())
        return result

    @classmethod
    def doVDiffCmd(cls):
        # TODO Implement this method.
        raise cls.UnsupportedOperationException()

    @classmethod
    def doSummaryCmd(cls):
        """Returns summary in global result string."""
        CRLF = DSSGlobals.CRLF
        ckt = DSSGlobals.activeCircuit
        result = 0
        s = ''
        if ckt.isSolved():
            s = s + 'Status = SOLVED' + DSSGlobals.CRLF
        else:
            s = s + 'Status = NOT Solved' + CRLF
        s = s + 'Solution Mode = ' + Utilities.getSolutionModeID() + CRLF
        s = s + 'Number = ' + String.valueOf.valueOf(ckt.getSolution().getNumberOfTimes()) + CRLF
        s = s + 'Load Mult = ' + String.format.format('%5.3f', ckt.getLoadMultiplier()) + CRLF
        s = s + 'Devices = ' + String.format.format('%d', ckt.getNumDevices()) + CRLF
        s = s + 'Buses = ' + String.format.format('%d', ckt.getNumBuses()) + CRLF
        s = s + 'Nodes = ' + String.format.format('%d', ckt.getNumNodes()) + CRLF
        s = s + 'Control Mode =' + Utilities.getControlModeID() + CRLF
        s = s + 'Total Iterations = ' + String.valueOf.valueOf(ckt.getSolution().getIteration()) + CRLF
        s = s + 'Control Iterations = ' + String.valueOf.valueOf(ckt.getSolution().getControlIteration()) + CRLF
        s = s + 'Max Sol Iter = ' + String.valueOf.valueOf(ckt.getSolution().getMostIterationsDone()) + CRLF
        s = s + ' ' + CRLF
        s = s + ' - Circuit Summary -' + CRLF
        s = s + ' ' + CRLF
        if ckt is not None:
            s = s + String.format.format('Year = %d ', ckt.getSolution().getYear()) + CRLF
            s = s + String.format.format('Hour = %d ', ckt.getSolution().getIntHour()) + CRLF
            s = s + 'Max pu. voltage = ' + String.format.format('%-.5g ', Utilities.getMaxPUVoltage()) + CRLF
            s = s + 'Min pu. voltage = ' + String.format.format('%-.5g ', Utilities.getMinPUVoltage(True)) + CRLF
            cPower = Utilities.getTotalPowerFromSources().multiply(1e-06)
            # MVA
            s = s + String.format.format('Total Active Power:   %-.6g MW', cPower.getReal()) + CRLF
            s = s + String.format.format('Total Reactive Power: %-.6g Mvar', cPower.getImaginary()) + CRLF
            cLosses = ckt.getLosses().multiply(1e-06)
            if cPower.getReal() != 0.0:
                s = s + String.format.format('Total Active Losses:   %-.6g MW, (%-.4g %%)', cLosses.getReal(), (cLosses.getReal() / cPower.getReal()) * 100.0) + CRLF
            else:
                s = s + 'Total Active Losses:   ****** MW, (**** %%)' + CRLF
            s = s + String.format.format('Total Reactive Losses: %-.6g Mvar', cLosses.getImaginary()) + CRLF
            s = s + String.format.format('Frequency = %-g Hz', ckt.getSolution().getFrequency()) + CRLF
            s = s + 'Mode = ' + Utilities.getSolutionModeID() + CRLF
            s = s + 'Control Mode = ' + Utilities.getControlModeID() + CRLF
            s = s + 'Load Model = ' + Utilities.getLoadModel() + CRLF
        DSSGlobals.globalResult = s
        return result

    @classmethod
    def doDistributeCmd(cls):
        parser = Parser.getInstance()
        result = 0
        paramPointer = 0
        # Defaults
        kW = 1000.0
        how = 'Proportional'
        skip = 1
        PF = 1.0
        fileName = 'DistGenerators.dss'
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.distributeCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    kW = parser.makeDouble()
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    how = parser.makeString()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    skip = parser.makeInteger()
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    PF = parser.makeDouble()
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    fileName = parser.makeString()
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    kW = parser.makeDouble() * 1000.0
                    break
                if True:
                    _1 = True
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        Utilities.makeDistributedGenerators(kW, PF, how, skip, fileName)
        return result

    @classmethod
    def doDI_PlotCmd(cls):
        if DSSGlobals.DIFilesAreOpen:
            DSSGlobals.energyMeterClass.closeAllDIFiles()
        if DSSPlotImpl.getDSSPlotObj() is None:
            DSSPlotImpl.setDSSPlotObj(DSSPlotImpl())
            # Defaults
        numRegs = 1
        dRegisters = [None] * EnergyMeterObj.NumEMRegisters
        iRegisters = [None] * numRegs
        iRegisters[0] = 9
        peakDay = False
        caseYear = 1
        caseName = ''
        meterName = 'DI_Totals'
        parser = Parser.getInstance()
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.DI_PlotCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    caseName = param
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    caseYear = parser.makeInteger()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    numRegs = parser.parseAsVector(EnergyMeterObj.NumEMRegisters, dRegisters)
                    iRegisters = [None] * numRegs
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < numRegs):
                            break
                        iRegisters[i - 1] = dRegisters[i]
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    peakDay = Utilities.interpretYesNo(param)
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    meterName = parser.makeString()
                    break
                if True:
                    _1 = True
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        DSSPlotImpl.getDSSPlotObj().doDI_Plot(caseName, caseYear, iRegisters, peakDay, meterName)
        iRegisters = None
        return 0

    @classmethod
    def doCompareCasesCmd(cls):
        if DSSGlobals.DIFilesAreOpen:
            DSSGlobals.energyMeterClass.closeAllDIFiles()
        if DSSPlotImpl.getDSSPlotObj() is None:
            DSSPlotImpl.setDSSPlotObj(DSSPlotImpl())
        caseName1 = 'base'
        caseName2 = ''
        reg = 9
        # overload EEN
        whichFile = 'Totals'
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam().toUpperCase()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            unknown = False
            if len(paramName) == 0:
                paramPointer += 1
            elif Utilities.compareTextShortest(paramName, 'CASE1') == 0:
                paramPointer = 1
            elif Utilities.compareTextShortest(paramName, 'CASE2') == 0:
                paramPointer = 2
            elif Utilities.compareTextShortest(paramName, 'REGISTER') == 0:
                paramPointer = 3
            elif Utilities.compareTextShortest(paramName, 'METER') == 0:
                paramPointer = 4
            else:
                unknown = True
            if not unknown:
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == 1:
                        _1 = True
                        caseName1 = param
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        caseName2 = param
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        reg = Parser.getInstance().makeInteger()
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        whichFile = param
                        break
                    if True:
                        _1 = True
                        break
                    break
            paramName = Parser.getInstance().getNextParam().toUpperCase()
            param = Parser.getInstance().makeString()
        DSSPlotImpl.getDSSPlotObj().doCompareCases(caseName1, caseName2, whichFile, reg)
        return 0

    @classmethod
    def doYearlyCurvesCmd(cls):
        dRegisters = [None] * EnergyMeterObj.NumEMRegisters
        if DSSGlobals.DIFilesAreOpen:
            DSSGlobals.energyMeterClass.closeAllDIFiles()
        if DSSPlotImpl.getDSSPlotObj() is None:
            DSSPlotImpl.setDSSPlotObj(DSSPlotImpl())
        nRegs = 1
        iRegisters = [None] * nRegs
        caseNames = list()
        caseNames.clear()
        whichFile = 'Totals'
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            unknown = False
            if len(paramName) == 0:
                paramPointer += 1
            else:
                _0 = paramName.toUpperCase()[0]
                _1 = False
                while True:
                    if _0 == 'C':
                        _1 = True
                        paramPointer = 1
                        break
                    if (_1 is True) or (_0 == 'R'):
                        _1 = True
                        paramPointer = 2
                        break
                    if (_1 is True) or (_0 == 'M'):
                        _1 = True
                        paramPointer = 3
                        # meter=
                        break
                    if True:
                        _1 = True
                        unknown = True
                        break
                    break
            if not unknown:
                _2 = paramPointer
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        DSSGlobals.auxParser.setCmdString(param)
                        DSSGlobals.auxParser.getNextParam()
                        param = DSSGlobals.auxParser.makeString()
                        while len(param) > 0:
                            caseNames.add(param)
                            DSSGlobals.auxParser.getNextParam()
                            param = DSSGlobals.auxParser.makeString()
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        nRegs = Parser.getInstance().parseAsVector(EnergyMeterObj.NumEMRegisters, dRegisters)
                        iRegisters = [None] * nRegs
                        _4 = True
                        i = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < nRegs):
                                break
                            iRegisters[i - 1] = dRegisters[i]
                            # TODO: Check zero indexing
                        break
                    if (_3 is True) or (_2 == 3):
                        _3 = True
                        whichFile = param
                        break
                    if True:
                        _3 = True
                        break
                    break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        DSSPlotImpl.getDSSPlotObj().doYearlyCurvePlot(caseNames, whichFile, iRegisters)
        iRegisters = None
        caseNames.clear()
        return 0

    @classmethod
    def doVisualizeCmd(cls):
        result = 0
        quantity = DSSPlot.vizCURRENT
        elemName = ''
        # Parse rest of command line
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam().toUpperCase()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            unknown = False
            if len(paramName) == 0:
                paramPointer += 1
            elif Utilities.compareTextShortest(paramName, 'WHAT') == 0:
                paramPointer = 1
            elif Utilities.compareTextShortest(paramName, 'ELEMENT') == 0:
                paramPointer = 2
            else:
                unknown = True
            if not unknown:
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == 1:
                        _1 = True
                        _2 = param.toLowerCase()[0]
                        _3 = False
                        while True:
                            if _2 == 'c':
                                _3 = True
                                quantity = DSSPlot.vizCURRENT
                                break
                            if (_3 is True) or (_2 == 'v'):
                                _3 = True
                                quantity = DSSPlot.vizVOLTAGE
                                break
                            if (_3 is True) or (_2 == 'p'):
                                _3 = True
                                quantity = DSSPlot.vizPOWER
                                break
                            break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        elemName = param
                        break
                    if True:
                        _1 = True
                        break
                    break
            paramName = Parser.getInstance().getNextParam().toUpperCase()
            param = Parser.getInstance().makeString()
        # --------------------------------------------------------------
        devIndex = Utilities.getCktElementIndex(elemName)
        # Global function
        if devIndex > 0:
            # element must already exist
            elem = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            if isinstance(elem, DSSCktElement):
                DSSPlotImpl.getDSSPlotObj().doVisualizationPlot(elem, quantity)
            else:
                DSSGlobals.doSimpleMsg(elem.getName() + ' must be a circuit element type!', 282)
                # wrong type
        else:
            DSSGlobals.doSimpleMsg('Requested Circuit Element: \"' + elemName + '\" Not Found.', 282)
            # did not find it ..
        return result

    @classmethod
    def doCloseDICmd(cls):
        DSSGlobals.energyMeterClass.closeAllDIFiles()
        return 0

    @classmethod
    def doADOScmd(cls):
        Utilities.doShellCmd(Parser.getInstance().getRemainder())
        return 0

    @classmethod
    def doEstimateCmd(cls):
        """Load current estimation is driven by energy meters at head of feeders."""
        cls.doAllocateLoadsCmd()
        # Let's look to see how well we did
        if not DSSGlobals.autoShowExport:
            DSSExecutive.getInstance().setCommand('Set showexport=yes')
        DSSExecutive.getInstance().setCommand('Export Estimation')
        return 0

    @classmethod
    def doReconductorCmd(cls):
        lineCode = ''
        geometry = ''
        result = 0
        paramPointer = 0
        lineCodeSpecified = False
        geometrySpecified = False
        line1 = ''
        line2 = ''
        myEditString = ''
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.reconductorCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    line1 = param
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    line2 = param
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    lineCode = param
                    lineCodeSpecified = True
                    geometrySpecified = False
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    geometry = param
                    lineCodeSpecified = False
                    geometrySpecified = True
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    myEditString = param
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Error: Unknown parameter on command line: ' + param, 28701)
                    break
                break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        # Check for errors
        # If user specified full line name, get rid of "line."
        line1 = Utilities.stripClassName(line1)
        line2 = Utilities.stripClassName(line2)
        if (len(line1) == 0) or (len(line2) == 0):
            DSSGlobals.doSimpleMsg('Both Line1 and Line2 must be specified!', 28702)
            return result
        if not lineCodeSpecified and not geometrySpecified:
            DSSGlobals.doSimpleMsg('Either a new LineCode or a Geometry must be specified!', 28703)
            return result
        lineClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('Line'))
        pLine1 = lineClass.find(line1)
        pLine2 = lineClass.find(line2)
        if (pLine1 is None) or (pLine2 is None):
            if pLine1 is None:
                DSSGlobals.doSimpleMsg('Line.' + line1 + ' not found.', 28704)
            elif pLine2 is None:
                DSSGlobals.doSimpleMsg('Line.' + line2 + ' not found.', 28704)
            return result
        # Now check to make sure they are in the same meter's zone
        if (pLine1.getMeterObj() is None) or (pLine2.getMeterObj() is None):
            DSSGlobals.doSimpleMsg('Error: Both Lines must be in the same EnergyMeter zone. One or both are not in any meter zone.', 28705)
            return result
        if pLine1.getMeterObj() != pLine2.getMeterObj():
            DSSGlobals.doSimpleMsg('Error: Line1 is in EnergyMeter.' + pLine1.getMeterObj().getName() + ' zone while Line2 is in EnergyMeter.' + pLine2.getMeterObj().getName() + ' zone. Both must be in the same Zone.', 28706)
            return result
        # Since the lines can be given in either order, Have to check to see
        # which direction they are specified and find the path between them.

        traceDirection = 0
        if Utilities.isPathBetween(pLine1, pLine2):
            traceDirection = 1
        if Utilities.isPathBetween(pLine2, pLine1):
            traceDirection = 2
        if lineCodeSpecified:
            editString = 'LineCode=' + lineCode
        else:
            editString = 'Geometry=' + geometry
        # append myEditString onto the end of the edit string to change the linecode or geometry
        editString = String.format.format('%s  %s', editString, myEditString)
        _2 = traceDirection
        _3 = False
        while True:
            if _2 == 1:
                _3 = True
                Utilities.traceAndEdit(pLine1, pLine2, editString)
                break
            if (_3 is True) or (_2 == 2):
                _3 = True
                Utilities.traceAndEdit(pLine2, pLine1, editString)
                break
            if True:
                _3 = True
                DSSGlobals.doSimpleMsg('Traceback path not found between Line1 and Line2.', 28707)
                return result
            break
        return result

    @classmethod
    def doAddMarkerCmd(cls):
        busName = ''
        parser = Parser.getInstance()
        result = 0
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.addMarkerCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    busName = param
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    DSSPlotImpl.setAddMarkerCode(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    DSSPlotImpl.setAddMarkerColor(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    DSSPlotImpl.setAddMarkerSize(parser.makeInteger())
                    break
                if True:
                    _1 = True
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        ckt = DSSGlobals.activeCircuit
        busIdx = ckt.getBusList().find(busName)
        if busIdx > 0:
            # TODO Check zero indexing.
            bus = ckt.getBuses()[busIdx]
            if bus.isCoordDefined():
                DSSGraphDeclarations.addNewMarker(bus.getX(), bus.getY(), DSSPlotImpl.getAddMarkerColor(), DSSPlotImpl.getAddMarkerCode(), DSSPlotImpl.getAddMarkerSize())
                DSSGraphDeclarations.showGraph()
            else:
                DSSGlobals.doSimpleMsg('Bus coordinates not defined for bus ' + busName, 28709)
        else:
            DSSGlobals.doSimpleMsg('Bus not found.', 28708)
        return result

    @classmethod
    def doSetLoadAndGenKVCmd(cls):
        ckt = DSSGlobals.activeCircuit
        result = 0
        for pLoad in ckt.getLoads():
            LoadImpl.activeLoadObj = pLoad
            # for updateVoltageBases to work
            sBus = Utilities.stripExtension(pLoad.getBus(0))
            iBus = ckt.getBusList().find(sBus)
            pBus = ckt.getBuses()[iBus]
            kvln = pBus.getKVBase()
            if (pLoad.getConnection() == 1) or (pLoad.getNPhases() == 3):
                pLoad.setKVLoadBase(kvln * DSSGlobals.SQRT3)
            else:
                pLoad.setKVLoadBase(kvln)
            pLoad.updateVoltageBases()
            pLoad.recalcElementData()
        for pGen in ckt.getGenerators():
            sBus = Utilities.stripExtension(pGen.getBus(0))
            iBus = ckt.getBusList().find(sBus)
            pBus = ckt.getBuses()[iBus]
            kvln = pBus.getKVBase()
            if (pGen.getConnection() == 1) or (pGen.getNPhases() > 1):
                pGen.setPresentKV(kvln * DSSGlobals.SQRT3)
            else:
                pGen.setPresentKV(kvln)
            pGen.recalcElementData()
        return result

    @classmethod
    def doUUIDsCmd(cls):
        # TODO Implement this method.
        raise cls.UnsupportedOperationException()

    @classmethod
    def doCvrtLoadshapesCmd(cls):
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        if len(param) == 0:
            param = 's'
            # Double file or Single file?
        _0 = param.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'd':
                _1 = True
                action = 'action=dblsave'
                break
            if True:
                _1 = True
                action = 'action=sngsave'
                # default
                break
            break
        loadShapeClass = DSSClassDefs.getDSSClass('loadshape')
        fileName = 'ReloadLoadShapes.dss'
        f = File(fileName)
        # TODO Auto-generated catch block
        try:
            pw = PrintWriter(f)
            iLoadshape = loadShapeClass.getFirst()
            # TODO Make class iterable.
            while # FIXME Zero based indexing iLoadshape > 0:
                pLoadShape = loadShapeClass.getActiveObj()
                Parser.getInstance().setCmdString(action)
                pLoadShape.edit()
                print String.format.format('New loadShape.%s npts=%d interval=%.8g %s', pLoadShape.getName(), pLoadShape.getNumPoints(), pLoadShape.getInterval(), DSSGlobals.globalResult)
                iLoadshape = loadShapeClass.getNext()
            pw.close()
        except FileNotFoundException, e:
            e.printStackTrace()
        Utilities.fireOffEditor(fileName)
        return 0

    @classmethod
    def doNodeDiffCmd(cls):
        numNodes = MutableInt()
        nodeBuffer = [None] * 50
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        sNode1 = param
        if paramName.find('2') >= 0:
            # TODO Check zero indexing
            sNode2 = param
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        sNode2 = param
        if paramName.find('1') > 0:
            sNode1 = param
            # get first node voltage
        DSSGlobals.auxParser.setToken(sNode1)
        nodeBuffer[0] = 1
        # TODO Check zero indexing
        sBusName = DSSGlobals.auxParser.parseAsBusName(numNodes, nodeBuffer)
        iBusIdx = DSSGlobals.activeCircuit.getBusList().find(sBusName)
        if iBusIdx > 0:
            b1ref = DSSGlobals.activeCircuit.getBuses()[iBusIdx].find(nodeBuffer[0])
            # TODO Check zero indexing
        else:
            DSSGlobals.doSimpleMsg(String.format.format('Bus %s not found.', sBusName), 28709)
            return result
        V1 = DSSGlobals.activeCircuit.getSolution().getNodeV()[b1ref]
        # get 2nd node voltage
        DSSGlobals.auxParser.setToken(sNode2)
        nodeBuffer[0] = 1
        # TODO Check zero indexing
        sBusName = DSSGlobals.auxParser.parseAsBusName(numNodes, nodeBuffer)
        iBusIdx = DSSGlobals.activeCircuit.getBusList().find(sBusName)
        if iBusIdx > 0:
            b2ref = DSSGlobals.activeCircuit.getBuses()[iBusIdx].find(nodeBuffer[0])
            # TODO Check zero indexing
        else:
            DSSGlobals.doSimpleMsg(String.format.format('Bus %s not found.', sBusName), 28710)
            return result
        V2 = DSSGlobals.activeCircuit.getSolution().getNodeV()[b2ref]
        VNodeDiff = V1.subtract(V2)
        DSSGlobals.globalResult = String.format.format('%.7g, V,    %.7g, deg  ', VNodeDiff.abs(), ComplexUtil.degArg(VNodeDiff))
        return result

    @classmethod
    def doRephaseCmd(cls):
        startLine = ''
        newPhases = ''
        result = 0
        paramPointer = 0
        myEditString = ''
        scriptFileName = 'RephaseEditScript.dss'
        transfStop = True
        # stop at transformers
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.rephaseCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    startLine = param
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    newPhases = param
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    myEditString = param
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    scriptFileName = param
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    transfStop = Utilities.interpretYesNo(param)
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Error: Unknown parameter on command line: ' + param, 28711)
                    break
                break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        lineClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('Line'))
        pStartLine = lineClass.find(Utilities.stripClassName(startLine))
        if pStartLine is None:
            DSSGlobals.doSimpleMsg('Starting line (' + startLine + ') not found.', 28712)
            return result
        # Check for some error conditions and abort if necessary
        if pStartLine.getMeterObj() is None:
            DSSGlobals.doSimpleMsg('Starting line must be in an EnergyMeter zone.', 28713)
            return result
        if not isinstance(pStartLine.getMeterObj(), EnergyMeterObj):
            DSSGlobals.doSimpleMsg('Starting line must be in an EnergyMeter zone.', 28713)
            return result
        Utilities.goForwardAndRephase(pStartLine, newPhases, myEditString, scriptFileName, transfStop)
        return result

    @classmethod
    def doSetBusXYCmd(cls):
        busName = ''
        XVal = 0.0
        YVal = 0.0
        result = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        paramPointer = 0
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = cls.setBusXYCommands.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    busName = param
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    XVal = Parser.getInstance().makeDouble()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    YVal = Parser.getInstance().makeDouble()
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Error: Unknown parameter on command line: ' + param, 28721)
                    break
                break
            iB = DSSGlobals.activeCircuit.getBusList().find(busName)
            if iB > 0:
                DSSGlobals.activeCircuit.getBuses()[iB].setX(XVal)
                DSSGlobals.activeCircuit.getBuses()[iB].setY(YVal)
                DSSGlobals.activeCircuit.getBuses()[iB].setCoordDefined(True)
            else:
                DSSGlobals.doSimpleMsg('Error: Bus \"' + busName + '\" not found.', 28722)
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        return result

    @classmethod
    def doUpdateStorageCmd(cls):
        DSSGlobals.storageClass.updateAll()
        return 0

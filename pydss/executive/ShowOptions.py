from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.ShowResults import (ShowResults,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.parser.impl.Parser import (Parser,)


class ShowOptions(object):
    CRLF = DSSGlobals.CRLF
    NumShowOptions = 30
    showOption = None
    showHelp = None
    showCommands = None
    # private constructor prevents instantiation from other classes

    def __init__(self):
        self.defineOptions()
        self.showCommands = CommandListImpl(self.showOption)
        self.showCommands.setAbbrevAllowed(True)

    class ShowOptionsHolder(object):
        """ShowOptionsHolder is loaded on the first execution of
        ShowOptions.getInstance() or the first access to
        ShowOptionsHolder.INSTANCE, not before.
        """
        INSTANCE = ShowOptions()

    @classmethod
    def getInstance(cls):
        return cls.ShowOptionsHolder.INSTANCE

    def defineOptions(self):
        self.showOption = [None] * self.NumShowOptions
        self.showOption[0] = 'autoadded'
        self.showOption[1] = 'buses'
        self.showOption[2] = 'currents'
        self.showOption[3] = 'convergence'
        self.showOption[4] = 'elements'
        self.showOption[5] = 'faults'
        self.showOption[6] = 'isolated'
        self.showOption[7] = 'generators'
        self.showOption[8] = 'meters'
        self.showOption[9] = 'monitor'
        self.showOption[10] = 'panel'
        self.showOption[11] = 'powers'
        self.showOption[12] = 'voltages'
        self.showOption[13] = 'zone'
        self.showOption[14] = 'taps'
        self.showOption[15] = 'overloads'
        self.showOption[16] = 'unserved'
        self.showOption[17] = 'eventlog'
        self.showOption[18] = 'variables'
        self.showOption[19] = 'ratings'
        self.showOption[20] = 'loops'
        self.showOption[21] = 'losses'
        self.showOption[22] = 'busflow'
        self.showOption[23] = 'lineconstants'
        self.showOption[24] = 'yprim'
        self.showOption[25] = 'y'
        self.showOption[26] = 'controlqueue'
        self.showOption[27] = 'topology'
        self.showOption[28] = 'mismatch'
        self.showOption[29] = 'kvbasemismatch'
        self.showHelp = [None] * self.NumShowOptions
        self.showHelp[0] = 'Shows auto added capacitors or generators. See AutoAdd solution mode.'
        self.showHelp[1] = 'Report showing all buses and nodes currently defined.'
        self.showHelp[2] = 'Report showing currents from most recent solution. syntax: ' + self.CRLF + self.CRLF + 'Show Currents  [[residual=]yes|no*] [Seq* | Elements]' + self.CRLF + self.CRLF + 'If \"residual\" flag is yes, the sum of currents in all conductors is reported. ' + 'Default is to report Sequence currents; otherwise currents in all conductors are reported.'
        self.showHelp[3] = 'Report on the convergence of each node voltage.'
        self.showHelp[4] = 'Shows names of all elements in circuit or all elements of a specified class. Syntax: ' + self.CRLF + self.CRLF + 'Show ELements [Classname] ' + self.CRLF + self.CRLF + 'Useful for creating scripts that act on selected classes of elements. '
        self.showHelp[5] = 'After fault study solution, shows fault currents.'
        self.showHelp[6] = 'Report showing buses and elements that are isolated from the main source.'
        self.showHelp[7] = 'Report showing generator elements currently defined and the values of the energy meters ' + self.CRLF + 'associated with each generator.'
        self.showHelp[8] = 'Shows the present values of the registers in the EnergyMeter elements.'
        self.showHelp[9] = 'Shows the contents of a selected monitor. Syntax: ' + self.CRLF + self.CRLF + ' Show Monitor monitorname'
        self.showHelp[10] = 'Shows control panel. (not necessary for standalone version)'
        self.showHelp[11] = 'Report on powers flowing in circuit from most recent solution. ' + self.CRLF + 'Powers may be reported in kVA or MVA and in sequence quantities or in every ' + 'conductor of each element. Syntax:' + self.CRLF + self.CRLF + 'Show Powers [MVA|kVA*] [Seq* | Elements]' + self.CRLF + self.CRLF + 'Sequence powers in kVA is the default. Examples:' + self.CRLF + self.CRLF + 'Show powers' + self.CRLF + 'Show power kva element' + self.CRLF + 'Show power mva elem'
        self.showHelp[12] = 'Reports voltages from most recent solution. Voltages are reported with respect to ' + self.CRLF + 'system reference (Node 0) by default (LN option), but may also be reported Line-Line (LL option).' + self.CRLF + 'The voltages are normally reported by bus/node, but may also be reported by circuit element. Syntax:' + self.CRLF + self.CRLF + 'Show Voltages [LL |LN*]  [Seq* | Nodes | Elements]' + self.CRLF + self.CRLF + 'Show Voltages' + self.CRLF + 'Show Voltage LN Nodes' + self.CRLF + 'Show Voltages LL Nodes' + self.CRLF + 'Show Voltage LN Elem'
        self.showHelp[13] = 'Shows the zone for a selected EnergyMeter element. Shows zone either in ' + 'a text file or in a graphical tree view.' + self.CRLF + self.CRLF + 'Show Zone  energymetername [Treeview]'
        self.showHelp[14] = 'Shows the regulator/LTC taps from the most recent solution.'
        self.showHelp[15] = 'Shows overloaded power delivery elements.'
        self.showHelp[16] = 'Shows loads that are \"unserved\". That is, loads for which the voltage is too low, ' + 'or a branch on the source side is overloaded. If UEonly is specified, shows only those loads ' + 'in which the emergency rating has been exceeded. Syntax:' + self.CRLF + self.CRLF + 'Show Unserved [UEonly] (unserved loads)'
        self.showHelp[17] = 'Shows the present event log. (Regulator tap changes, capacitor switching, etc.)'
        self.showHelp[18] = 'Shows internal state variables of devices (Power conversion elements) that report them.'
        self.showHelp[19] = 'Shows ratings of power delivery elements.'
        self.showHelp[20] = 'Shows closed loops detected by EnergyMeter elements that are possibly unwanted. Otherwise, loops are OK.'
        self.showHelp[21] = 'Reports losses in each element and in the entire circuit.'
        self.showHelp[22] = 'Creates a report showing power and current flows as well as voltages around a selected bus. Syntax:' + self.CRLF + self.CRLF + 'Show BUSFlow busname [MVA|kVA*] [Seq* | Elements]' + self.CRLF + self.CRLF + 'Show busflow busxxx kVA elem' + self.CRLF + 'Show busflow busxxx MVA seq' + self.CRLF + self.CRLF + 'NOTE: The Show menu will prompt you for these values.'
        self.showHelp[23] = 'Creates two report files for the line constants (impedances) of every LINEGEOMETRY element currently defined. ' + 'One file shows the main report with the matrices. The other file contains corresponding LINECODE ' + 'definitions that you may use in subsequent simulations.  Syntax:' + self.CRLF + self.CRLF + 'Show LIneConstants [frequency] [none|mi|km|kft|m|me|ft|in|cm] [rho]' + self.CRLF + self.CRLF + 'Specify the frequency, length units and earth resistivity (meter-ohms). Examples:' + self.CRLF + self.CRLF + 'Show Lineconstants 60 kft 100' + self.CRLF + 'Show Linecon 50 km 1000'
        self.showHelp[24] = 'Show the primitive admittance (y) matrix for the active element.'
        self.showHelp[25] = 'Show the system Y matrix. Could be a large file!'
        self.showHelp[26] = 'Shows the present contents of the control queue.'
        self.showHelp[27] = 'Shows the topology as seen by the SwtControl elements.'
        self.showHelp[28] = 'Shows the current mismatches at each node in amperes and percent of max currents at node.'
        self.showHelp[29] = 'Creates a report of Load and Generator elements for which the base voltage does not match the Bus base voltage. ' + 'Scripts for correcting the voltage base are suggested.'

    def doShowCmd(self):
        fileName = ''
        parser = Parser.getInstance()
        parser.getNextParam()
        param = parser.makeString().toLowerCase()
        paramPointer = self.showCommands.getCommand(param)
        if paramPointer == 0:
            paramPointer = 13
            # voltages
        DSSGlobals.inShowResults = True
        _0 = paramPointer
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                Utilities.fireOffEditor(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'AutoAddedGenerators.txt')
                Utilities.fireOffEditor(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'AutoAddedCapacitors.txt')
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                ShowResults.showBuses(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Buses.txt')
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                showOptionCode = 0
                showResid = False
                parser.getNextParam()
                # look for residual
                param = parser.makeString().toUpperCase()
                # logic handles show curr y|n|t elements or show curr elements
                if len(param) > 0:
                    _2 = param[0]
                    _3 = False
                    while True:
                        if _2 == 'Y':
                            _3 = True
                            showResid = True
                            break
                        if (_3 is True) or (_2 == 'T'):
                            _3 = True
                            showResid = True
                            break
                        if (_3 is True) or (_2 == 'N'):
                            _3 = True
                            showResid = False
                            break
                        if (_3 is True) or (_2 == 'E'):
                            _3 = True
                            showOptionCode = 1
                            break
                        break
                    parser.getNextParam()
                    # look for another param
                    param = parser.makeString().toUpperCase()
                    if len(param) > 0:
                        _4 = param[0]
                        _5 = False
                        while True:
                            if _4 == 'E':
                                _5 = True
                                showOptionCode = 1
                                break
                            break
                    _6 = showOptionCode
                    _7 = False
                    while True:
                        if _6 == 0:
                            _7 = True
                            fileName = 'Curr_Seq'
                            break
                        if (_7 is True) or (_6 == 1):
                            _7 = True
                            fileName = 'Curr_Elem'
                            break
                        break
                    ShowResults.showCurrents(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + fileName + '.txt', showResid, showOptionCode)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                DSSGlobals.activeCircuit.getSolution().writeConvergenceReport(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Convergence.txt')
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                parser.getNextParam()
                # Look for another param
                param = parser.makeString().toLowerCase()
                ShowResults.showElements(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Elements.txt', param)
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                ShowResults.showFaultStudy(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'FaultStudy.txt')
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                ShowResults.showIsolated(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Isolated.txt')
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                ShowResults.showGenMeters(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'GenMeterOut.txt')
                break
            if (_1 is True) or (_0 == 8):
                _1 = True
                ShowResults.showMeters(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'EMout.txt')
                break
            if (_1 is True) or (_0 == 9):
                _1 = True
                parser.getNextParam()
                param = parser.makeString()
                if len(param) > 0:
                    pMon = DSSGlobals.monitorClass.find(param)
                    if pMon is not None:
                        pMon.translateToCSV(True)
                    else:
                        DSSGlobals.doSimpleMsg('Monitor \"' + param + '\" not found.' + DSSGlobals.CRLF + parser.getCmdString(), 248)
                else:
                    DSSGlobals.doSimpleMsg('Monitor name not specified.' + DSSGlobals.CRLF + parser.getCmdString(), 249)
                break
            if (_1 is True) or (_0 == 10):
                _1 = True
                DSSGlobals.DSSForms.showControlPanel()
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                showOptionCode = 0
                MVAOpt = 0
                fileName = 'Power'
                parser.getNextParam()
                param = parser.makeString().toLowerCase()
                if len(param) > 0:
                    _8 = param[0]
                    _9 = False
                    while True:
                        if _8 == 'm':
                            _9 = True
                            MVAOpt = 1
                            break
                        if (_9 is True) or (_8 == 'e'):
                            _9 = True
                            showOptionCode = 1
                            break
                        break
                parser.getNextParam()
                param = parser.makeString().toLowerCase()
                if len(param) > 0:
                    if param[0] == 'e':
                        showOptionCode = 1
                if showOptionCode == 1:
                    fileName = fileName + '_elem'
                else:
                    fileName = fileName + '_seq'
                if MVAOpt == 1:
                    fileName = fileName + '_MVA'
                else:
                    fileName = fileName + '_kVA'
                ShowResults.showPowers(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + fileName + '.txt', MVAOpt, showOptionCode)
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                LLOpt = False
                # line-line voltage option
                showOptionCode = 0
                # Check for LL or LN option
                parser.getNextParam()
                param = parser.makeString()
                fileName = 'VLN'
                if len(param) > 0:
                    if param.equalsIgnoreCase('LL'):
                        LLOpt = True
                        fileName = 'VLL'
                # Check for seq | nodes | elements
                parser.getNextParam()
                param = parser.makeString().toUpperCase()
                if len(param) > 0:
                    _10 = param[0]
                    _11 = False
                    while True:
                        if _10 == 'N':
                            _11 = True
                            showOptionCode = 1
                            fileName = fileName + '_node'
                            break
                        if (_11 is True) or (_10 == 'E'):
                            _11 = True
                            showOptionCode = 2
                            fileName = fileName + '_elem'
                            break
                        if True:
                            _11 = True
                            fileName = fileName + '_seq'
                            break
                        break
                ShowResults.showVoltages(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + fileName + '.txt', LLOpt, showOptionCode)
                break
            if (_1 is True) or (_0 == 13):
                _1 = True
                ShowResults.showMeterZone(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'ZoneOut.txt')
                break
            if (_1 is True) or (_0 == 14):
                _1 = True
                ShowResults.showRegulatorTaps(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'RegTaps.txt')
                break
            if (_1 is True) or (_0 == 15):
                _1 = True
                ShowResults.showOverloads(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Overload.txt')
                break
            if (_1 is True) or (_0 == 16):
                _1 = True
                parser.getNextParam()
                param = parser.makeString()
                if len(param) > 0:
                    ShowResults.showUnserved(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Unserved.txt', True)
                else:
                    ShowResults.showUnserved(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Unserved.txt', False)
                break
            if (_1 is True) or (_0 == 17):
                _1 = True
                DSSGlobals.DSSForms.showMessageForm(DSSGlobals.eventStrings)
                break
            if (_1 is True) or (_0 == 18):
                _1 = True
                ShowResults.showVariables(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Variables.txt')
                break
            if (_1 is True) or (_0 == 19):
                _1 = True
                ShowResults.showRatings(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'RatingsOut.txt')
                break
            if (_1 is True) or (_0 == 20):
                _1 = True
                ShowResults.showLoops(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Loops.txt')
                break
            if (_1 is True) or (_0 == 21):
                _1 = True
                ShowResults.showLosses(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Losses.txt')
                break
            if (_1 is True) or (_0 == 22):
                _1 = True
                showOptionCode = 0
                MVAOpt = 0
                parser.getNextParam()
                # get bus name
                busName = parser.makeString()
                if len(busName) > 0:
                    fileName = busName
                else:
                    fileName = 'BusPower'
                parser.getNextParam()
                param = parser.makeString().toLowerCase()
                if len(param) > 0:
                    _12 = param[0]
                    _13 = False
                    while True:
                        if _12 == 'm':
                            _13 = True
                            MVAOpt = 1
                            break
                        if (_13 is True) or (_12 == 'e'):
                            _13 = True
                            showOptionCode = 1
                            break
                        break
                parser.getNextParam()
                param = parser.makeString().toLowerCase()
                if len(param) > 0:
                    if param[0] == 'e':
                        showOptionCode = 1
                if showOptionCode == 1:
                    fileName = fileName + '_elem'
                else:
                    fileName = fileName + '_seq'
                if MVAOpt == 1:
                    fileName = fileName + '_MVA'
                else:
                    fileName = fileName + '_kVA'
                ShowResults.showBusPowers(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + fileName + '.txt', busName, MVAOpt, showOptionCode)
                break
            if (_1 is True) or (_0 == 23):
                _1 = True
                freq = DSSGlobals.defaultBaseFreq
                # default
                units = LineUnits.UNITS_KFT
                # 'kft'; // default
                rhoLine = 100.0
                parser.getNextParam()
                if len(parser.makeString()) > 0:
                    freq = parser.makeDouble()
                parser.getNextParam()
                if len(parser.makeString()) > 0:
                    units = LineUnits.getUnitsCode(parser.makeString())
                parser.getNextParam()
                if len(parser.makeString()) > 0:
                    rhoLine = parser.makeDouble()
                ShowResults.showLineConstants(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'LineConstants.txt', freq, units, rhoLine)
                break
            if (_1 is True) or (_0 == 24):
                _1 = True
                if DSSGlobals.activeCircuit is not None:
                    # Yprim
                    cktElem = DSSGlobals.activeCircuit.getActiveCktElement()
                    ShowResults.showYPrim(DSSGlobals.DSSDataDirectory + cktElem.getParentClass().getName() + '_' + cktElem.getName() + '_Yprim.txt')
                break
            if (_1 is True) or (_0 == 25):
                _1 = True
                ShowResults.showY(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'SystemY.txt')
                break
            if (_1 is True) or (_0 == 26):
                _1 = True
                if DSSGlobals.activeCircuit is not None:
                    DSSGlobals.activeCircuit.getControlQueue().showQueue(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'ControlQueue.csv')
                break
            if (_1 is True) or (_0 == 27):
                _1 = True
                ShowResults.showTopology(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_)
                break
            if (_1 is True) or (_0 == 28):
                _1 = True
                ShowResults.showNodeCurrentSum(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'NodeMismatch.txt')
                break
            if (_1 is True) or (_0 == 29):
                _1 = True
                ShowResults.showkVBaseMismatch(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'kVBaseMismatch.txt')
                break
            if (_1 is True) or (_0 == 30):
                _1 = True
                ShowResults.showDeltaV(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'DeltaV.txt')
                break
            break
        DSSGlobals.inShowResults = False
        return 0

from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.CIMProfileChoice import (CIMProfileChoice,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.ExportResults import (ExportResults,)


class ExportOptions(object):
    NumExportOptions = 32
    exportOption = None
    exportHelp = None
    exportCommands = None
    # private constructor prevents instantiation from other classes

    def __init__(self):
        self.defineOptions()
        self.exportCommands = CommandListImpl(self.exportOption)
        self.exportCommands.setAbbrevAllowed(True)

    class ExportOptionsHolder(object):
        """ExportOptionsHolder is loaded on the first execution of
        ExportOptions.getInstance() or the first access to
        ExportOptionsHolder.INSTANCE, not before.
        """
        INSTANCE = ExportOptions()

    @classmethod
    def getInstance(cls):
        return cls.ExportOptionsHolder.INSTANCE

    def defineOptions(self):
        self.exportOption = [None] * self.NumExportOptions
        self.exportOption[0] = 'Voltages'
        self.exportOption[1] = 'SeqVoltages'
        self.exportOption[2] = 'Currents'
        self.exportOption[3] = 'SeqCurrents'
        self.exportOption[4] = 'Estimation'
        self.exportOption[5] = 'Capacity'
        self.exportOption[6] = 'Overloads'
        self.exportOption[7] = 'Unserved'
        self.exportOption[8] = 'Powers'
        self.exportOption[9] = 'SeqPowers'
        self.exportOption[10] = 'Faultstudy'
        self.exportOption[11] = 'Generators'
        self.exportOption[12] = 'Loads'
        self.exportOption[13] = 'Meters'
        self.exportOption[14] = 'Monitors'
        self.exportOption[15] = 'Yprims'
        self.exportOption[16] = 'Y'
        self.exportOption[17] = 'seqz'
        self.exportOption[18] = 'P_byphase'
        self.exportOption[19] = 'CDPSMCombined'
        self.exportOption[20] = 'CDPSMFunc'
        self.exportOption[21] = 'CDPSMAsset'
        self.exportOption[22] = 'Buscoords'
        self.exportOption[23] = 'Losses'
        self.exportOption[24] = 'Guids'
        self.exportOption[25] = 'Counts'
        self.exportOption[26] = 'Summary'
        self.exportOption[27] = 'CDPSMElec'
        self.exportOption[28] = 'CDPSMGeo'
        self.exportOption[29] = 'CDPSMTopo'
        self.exportOption[30] = 'CDPSMStateVar'
        self.exportOption[31] = 'Profile'
        self.exportHelp[0] = '(Default file = EXP_VOLTAGES.CSV) Voltages to ground by bus/node.'
        self.exportHelp[1] = '(Default file = EXP_SEQVOLTAGES.CSV) Sequence voltages.'
        self.exportHelp[2] = '(Default file = EXP_CURRENTS.CSV) Currents in each conductor of each element.'
        self.exportHelp[3] = '(Default file = EXP_SEQCURRENTS.CSV) Sequence currents in each terminal of 3-phase elements.'
        self.exportHelp[4] = '(Default file = EXP_ESTIMATION.CSV) Results of last estimation.'
        self.exportHelp[5] = '(Default file = EXP_CAPACITY.CSV) Capacity report.'
        self.exportHelp[6] = '(Default file = EXP_OVERLOADS.CSV) Overloaded elements report.'
        self.exportHelp[7] = '(Default file = EXP_UNSERVED.CSV) Report on elements that are served in violation of ratings.'
        self.exportHelp[8] = '(Default file = EXP_POWERS.CSV) Powers into each terminal of each element.'
        self.exportHelp[9] = '(Default file = EXP_SEQPOWERS.CSV) Sequence powers into each terminal of 3-phase elements.'
        self.exportHelp[10] = '(Default file = EXP_FAULTS.CSV) results of a fault study.'
        self.exportHelp[11] = '(Default file = EXP_GENMETERS.CSV) Present values of generator meters. Adding the switch \"/multiple\" or \"/m\" will ' + ' cause a separate file to be written for each generator.'
        self.exportHelp[12] = '(Default file = EXP_LOADS.CSV) Report on loads from most recent solution.'
        self.exportHelp[13] = '(Default file = EXP_METERS.CSV) Energy meter exports. Adding the switch \"/multiple\" or \"/m\" will ' + ' cause a separate file to be written for each meter.'
        self.exportHelp[14] = '(file name is assigned by Monitor export) Monitor values.'
        self.exportHelp[15] = '(Default file = EXP_YPRIMS.CSV) All primitive Y matrices.'
        self.exportHelp[16] = '(Default file = EXP_Y.CSV) System Y matrix.'
        self.exportHelp[17] = '(Default file = EXP_SEQZ.CSV) Equivalent sequence Z1, Z0 to each bus.'
        self.exportHelp[18] = '(Default file = EXP_P_BYPHASE.CSV) Power by phase.'
        self.exportHelp[19] = '(Default file = CDPSM.XML) (IEC 61968-13, CDPSM Unbalanced load flow profile)'
        self.exportHelp[20] = '(Default file = CDPSM_Connect.XML) (IEC 61968-13, CDPSM Unbalanced connectivity profile)'
        self.exportHelp[21] = '(Default file = CDPSM_Balanced.XML) (IEC 61968-13, CDPSM Balanced profile)'
        self.exportHelp[22] = '[Default file = EXP_BUSCOORDS.CSV] Bus coordinates in csv form.'
        self.exportHelp[23] = '[Default file = EXP_LOSSES.CSV] Losses for each element.'
        self.exportHelp[24] = '[Default file = EXP_GUIDS.CSV] Guids for each element.'
        self.exportHelp[25] = '[Default file = EXP_Counts.CSV] (instance counts for each class)'
        self.exportHelp[26] = '[Default file = EXP_Summary.CSV] Solution summary.'
        self.exportHelp[27] = '(Default file = CDPSM_ElectricalProperties.XML) (IEC 61968-13, CDPSM Electrical Properties profile)'
        self.exportHelp[28] = '(Default file = CDPSM_Geographical.XML) (IEC 61968-13, CDPSM Geographical profile)'
        self.exportHelp[29] = '(Default file = CDPSM_Topology.XML) (IEC 61968-13, CDPSM Topology profile)'
        self.exportHelp[30] = '(Default file = CDPSM_StateVariables.XML) (IEC 61968-13, CDPSM State Variables profile)'
        self.exportHelp[31] = '[Default file = EXP_Profile.CSV] Coordinates, color of each line section in Profile plot. Same options as Plot Profile Phases property.' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Example:  Export Profile Phases=All [optional file name]'

    def doExportCmd(self):
        parm2 = ''
        parser = Parser.getInstance()
        Parser.getInstance().getNextParam()
        parm1 = Parser.getInstance().makeString().toLowerCase()
        paramPointer = self.exportCommands.getCommand(parm1)
        MVAOpt = 0
        UEOnlyOpt = False
        phasesToPlot = DSSGlobals.PROFILE3PH
        # init this to get rid of compiler warning
        _0 = paramPointer
        _1 = False
        while True:
            if _0 == 8:
                _1 = True
                parser.getNextParam()
                parm2 = Parser.getInstance().makeString().toLowerCase()
                MVAOpt = 0
                if len(parm2) > 0:
                    if parm2[0] == 'm':
                        MVAOpt = 1
                break
            if (_1 is True) or (_0 == 18):
                _1 = True
                parser.getNextParam()
                parm2 = Parser.getInstance().makeString().toLowerCase()
                MVAOpt = 0
                if len(parm2) > 0:
                    if parm2[0] == 'm':
                        MVAOpt = 1
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                parser.getNextParam()
                parm2 = parser.makeString().toLowerCase()
                UEOnlyOpt = False
                if len(parm2) > 0:
                    if parm2[0] == 'u':
                        UEOnlyOpt = True
                break
            if (_1 is True) or (_0 == 14):
                _1 = True
                parser.getNextParam()
                parm2 = parser.makeString()
                break
            if (_1 is True) or (_0 == 31):
                _1 = True
                parser.getNextParam()
                parm2 = parser.makeString()
                phasesToPlot = DSSGlobals.PROFILE3PH
                # the default
                if Utilities.compareTextShortest(parm2, 'default') == 0:
                    phasesToPlot = DSSGlobals.PROFILE3PH
                elif Utilities.compareTextShortest(parm2, 'all') == 0:
                    phasesToPlot = DSSGlobals.PROFILEALL
                elif Utilities.compareTextShortest(parm2, 'primary') == 0:
                    phasesToPlot = DSSGlobals.PROFILEALLPRI
                elif Utilities.compareTextShortest(parm2, 'll3ph') == 0:
                    phasesToPlot = DSSGlobals.PROFILELL
                elif Utilities.compareTextShortest(parm2, 'llall') == 0:
                    phasesToPlot = DSSGlobals.PROFILELLALL
                elif Utilities.compareTextShortest(parm2, 'llprimary') == 0:
                    phasesToPlot = DSSGlobals.PROFILELLPRI
                elif len(parm2) == 1:
                    phasesToPlot = parser.makeInteger()
                break
            break
        # Pick up last parameter on line, alternate file name, if any
        parser.getNextParam()
        fileName = parser.makeString().toLowerCase()
        # should be full path name to work universally
        DSSGlobals.inShowResults = True
        # Assign default file name if alternate not specified
        if len(fileName) == 0:
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    fileName = 'EXP_VOLTAGES.csv'
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    fileName = 'EXP_SEQVOLTAGES.csv'
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    fileName = 'EXP_CURRENTS.csv'
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    fileName = 'EXP_SEQCURRENTS.csv'
                    break
                if (_3 is True) or (_2 == 4):
                    _3 = True
                    fileName = 'EXP_ESTIMATION.csv'
                    break
                    # estimation error
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    fileName = 'EXP_CAPACITY.csv'
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    fileName = 'EXP_OVERLOADS.csv'
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    fileName = 'EXP_UNSERVED.csv'
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    fileName = 'EXP_POWERS.csv'
                    break
                if (_3 is True) or (_2 == 9):
                    _3 = True
                    fileName = 'EXP_SEQPOWERS.csv'
                    break
                if (_3 is True) or (_2 == 10):
                    _3 = True
                    fileName = 'EXP_FAULTS.csv'
                    break
                if (_3 is True) or (_2 == 11):
                    _3 = True
                    fileName = 'EXP_GENMETERS.csv'
                    break
                if (_3 is True) or (_2 == 12):
                    _3 = True
                    fileName = 'EXP_LOADS.csv'
                    break
                if (_3 is True) or (_2 == 13):
                    _3 = True
                    fileName = 'EXP_METERS.csv'
                    break
                    # case 14: FileName is assigned
                if (_3 is True) or (_2 == 15):
                    _3 = True
                    fileName = 'EXP_YPRIM.csv'
                    break
                if (_3 is True) or (_2 == 16):
                    _3 = True
                    fileName = 'EXP_Y.csv'
                    break
                if (_3 is True) or (_2 == 17):
                    _3 = True
                    fileName = 'EXP_SEQZ.csv'
                    break
                if (_3 is True) or (_2 == 18):
                    _3 = True
                    fileName = 'EXP_P_BYPHASE.csv'
                    break
                if (_3 is True) or (_2 == 19):
                    _3 = True
                    fileName = 'CDPSM_Combined.xml'
                    break
                if (_3 is True) or (_2 == 20):
                    _3 = True
                    fileName = 'CDPSM_Functional.xml'
                    break
                if (_3 is True) or (_2 == 21):
                    _3 = True
                    fileName = 'CDPSM_Asset.xml'
                    break
                if (_3 is True) or (_2 == 22):
                    _3 = True
                    fileName = 'EXP_BUSCOORDS.csv'
                    break
                if (_3 is True) or (_2 == 23):
                    _3 = True
                    fileName = 'EXP_LOSSES.csv'
                    break
                if (_3 is True) or (_2 == 24):
                    _3 = True
                    fileName = 'EXP_GUIDS.csv'
                    break
                if (_3 is True) or (_2 == 25):
                    _3 = True
                    fileName = 'EXP_Counts.csv'
                    break
                if (_3 is True) or (_2 == 26):
                    _3 = True
                    fileName = 'EXP_Summary.csv'
                    break
                if (_3 is True) or (_2 == 27):
                    _3 = True
                    fileName = 'CDPSM_ElectricalProperties.xml'
                    break
                if (_3 is True) or (_2 == 28):
                    _3 = True
                    fileName = 'CDPSM_Geographical.xml'
                    break
                if (_3 is True) or (_2 == 29):
                    _3 = True
                    fileName = 'CDPSM_Topology.xml'
                    break
                if (_3 is True) or (_2 == 30):
                    _3 = True
                    fileName = 'CDPSM_StateVariables.xml'
                    break
                if (_3 is True) or (_2 == 31):
                    _3 = True
                    fileName = 'EXP_Profile.csv'
                    break
                if True:
                    _3 = True
                    fileName = 'EXP_VOLTAGES.csv'
                    break
                break
            fileName = DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + fileName
            # explicitly define directory
        _4 = paramPointer
        _5 = False
        while True:
            if _4 == 0:
                _5 = True
                ExportResults.exportVoltages(fileName)
                break
            if (_5 is True) or (_4 == 1):
                _5 = True
                ExportResults.exportSeqVoltages(fileName)
                break
            if (_5 is True) or (_4 == 2):
                _5 = True
                ExportResults.exportCurrents(fileName)
                break
            if (_5 is True) or (_4 == 3):
                _5 = True
                ExportResults.exportSeqCurrents(fileName)
                break
            if (_5 is True) or (_4 == 4):
                _5 = True
                ExportResults.exportEstimation(fileName)
                break
                # estimation error
            if (_5 is True) or (_4 == 5):
                _5 = True
                ExportResults.exportCapacity(fileName)
                break
            if (_5 is True) or (_4 == 6):
                _5 = True
                ExportResults.exportOverloads(fileName)
                break
            if (_5 is True) or (_4 == 7):
                _5 = True
                ExportResults.exportUnserved(fileName, UEOnlyOpt)
                break
            if (_5 is True) or (_4 == 8):
                _5 = True
                ExportResults.exportPowers(fileName, MVAOpt)
                break
            if (_5 is True) or (_4 == 9):
                _5 = True
                ExportResults.exportSeqPowers(fileName, MVAOpt)
                break
            if (_5 is True) or (_4 == 10):
                _5 = True
                ExportResults.exportFaultStudy(fileName)
                break
            if (_5 is True) or (_4 == 11):
                _5 = True
                ExportResults.exportGenMeters(fileName)
                break
            if (_5 is True) or (_4 == 12):
                _5 = True
                ExportResults.exportLoads(fileName)
                break
            if (_5 is True) or (_4 == 13):
                _5 = True
                ExportResults.exportMeters(fileName)
                break
            if (_5 is True) or (_4 == 14):
                _5 = True
                if len(parm2) > 0:
                    pMon = DSSGlobals.monitorClass.find(parm2)
                    if pMon is not None:
                        pMon.translateToCSV(False)
                        fileName = DSSGlobals.globalResult
                    else:
                        DSSGlobals.doSimpleMsg('Monitor \"' + parm2 + '\" not found.' + DSSGlobals.CRLF + parser.getCmdString(), 250)
                else:
                    DSSGlobals.doSimpleMsg('Monitor name not specified.' + DSSGlobals.CRLF + parser.getCmdString(), 251)
                break
            if (_5 is True) or (_4 == 15):
                _5 = True
                ExportResults.exportYprim(fileName)
                break
            if (_5 is True) or (_4 == 16):
                _5 = True
                ExportResults.exportY(fileName)
                break
            if (_5 is True) or (_4 == 17):
                _5 = True
                ExportResults.exportSeqZ(fileName)
                break
            if (_5 is True) or (_4 == 18):
                _5 = True
                ExportResults.exportPbyphase(fileName, MVAOpt)
                break
            if (_5 is True) or (_4 == 19):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.COMBINED)
                break
                # defaults to a load-flow model
            if (_5 is True) or (_4 == 20):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.FUNCTIONAL)
                break
            if (_5 is True) or (_4 == 21):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.ASSET)
                break
            if (_5 is True) or (_4 == 22):
                _5 = True
                ExportResults.exportBusCoords(fileName)
                break
            if (_5 is True) or (_4 == 23):
                _5 = True
                ExportResults.exportLosses(fileName)
                break
            if (_5 is True) or (_4 == 24):
                _5 = True
                ExportResults.exportUUIDs(fileName)
                break
            if (_5 is True) or (_4 == 25):
                _5 = True
                ExportResults.exportCounts(fileName)
                break
            if (_5 is True) or (_4 == 26):
                _5 = True
                ExportResults.exportSummary(fileName)
                break
            if (_5 is True) or (_4 == 27):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.ELECTRICAL_PROPERTIES)
                break
            if (_5 is True) or (_4 == 28):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.GEOGRAPHICAL)
                break
            if (_5 is True) or (_4 == 29):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.TOPOLOGY)
                break
            if (_5 is True) or (_4 == 30):
                _5 = True
                ExportResults.exportCDPSM(fileName, CIMProfileChoice.STATE_VARIABLES)
                break
            if (_5 is True) or (_4 == 31):
                _5 = True
                ExportResults.exportProfile(fileName, phasesToPlot)
                break
            if True:
                _5 = True
                ExportResults.exportVoltages(fileName)
                break
            break
        result = 0
        DSSGlobals.inShowResults = False
        if DSSGlobals.autoShowExport:
            Utilities.fireOffEditor(fileName)
        return result

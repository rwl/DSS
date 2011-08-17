from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.Transformer import (Transformer,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.TransformerObjImpl import (TransformerObjImpl,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class TransformerImpl(PDClassImpl, Transformer):
    activeTransfObj = None
    XfmrCodeClass = None

    def __init__(self):
        super(TransformerImpl, self)()
        self.className = 'Transformer'
        self.classType = self.classType + DSSClassDefs.XFMR_ELEMENT
        # override PDElement (kept in both actually)
        self.activeElement = -1
        self.defineProperties()
        # Make space for transformer property list
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)
        # allow property list abbreviations

    def defineProperties(self):
        self.numProperties = Transformer.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # Define property names
        self.propertyName[0] = 'phases'
        self.propertyName[1] = 'windings'
        # winding definition
        self.propertyName[2] = 'wdg'
        self.propertyName[3] = 'bus'
        self.propertyName[4] = 'conn'
        self.propertyName[5] = 'kV'
        # for 2-and 3- always kVLL else actual winding kV
        self.propertyName[6] = 'kVA'
        self.propertyName[7] = 'tap'
        self.propertyName[8] = '%R'
        self.propertyName[9] = 'Rneut'
        self.propertyName[10] = 'Xneut'
        # general data
        self.propertyName[11] = 'buses'
        self.propertyName[12] = 'conns'
        self.propertyName[13] = 'kVs'
        self.propertyName[14] = 'kVAs'
        self.propertyName[15] = 'taps'
        self.propertyName[16] = 'Xhl'
        self.propertyName[17] = 'Xht'
        self.propertyName[18] = 'Xlt'
        self.propertyName[19] = 'Xscarray'
        # x12 13 14... 23 24.. 34 ..
        self.propertyName[20] = 'thermal'
        self.propertyName[21] = 'n'
        self.propertyName[22] = 'm'
        self.propertyName[23] = 'flrise'
        self.propertyName[24] = 'hsrise'
        self.propertyName[25] = '%loadloss'
        self.propertyName[26] = '%noloadloss'
        self.propertyName[27] = 'normhkVA'
        self.propertyName[28] = 'emerghkVA'
        self.propertyName[29] = 'sub'
        # =y/n
        self.propertyName[30] = 'MaxTap'
        self.propertyName[31] = 'MinTap'
        self.propertyName[32] = 'NumTaps'
        self.propertyName[33] = 'subname'
        self.propertyName[34] = '%imag'
        self.propertyName[35] = 'ppm_antifloat'
        self.propertyName[36] = '%Rs'
        self.propertyName[37] = 'bank'
        self.propertyName[38] = 'XfmrCode'
        # define property help values
        self.propertyHelp[0] = 'Number of phases this transformer. Default is 3.'
        self.propertyHelp[1] = 'Number of windings, this transformers. (Also is the number of terminals) ' + 'Default is 2.'
        # winding definition
        self.propertyHelp[2] = 'Set this = to the number of the winding you wish to define.  Then set ' + 'the values for this winding.  Repeat for each winding.  Alternatively, use ' + 'the array collections (buses, kvas, etc.) to define the windings.  Note: ' + 'reactances are BETWEEN pairs of windings; they are not the property of a single winding.'
        self.propertyHelp[3] = 'Bus connection spec for this winding.'
        self.propertyHelp[4] = 'Connection of this winding. Default is \"wye\" with the neutral solidly grounded.'
        self.propertyHelp[5] = 'For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding'
        self.propertyHelp[6] = 'Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings.' + 'If 2-winding transformer, forces other winding to same value. ' + 'When winding 1 is defined, all other windings are defaulted to the same rating ' + 'and the first two winding resistances are defaulted to the %loadloss value.'
        self.propertyHelp[7] = 'Per unit tap that this winding is on.'
        self.propertyHelp[8] = 'Percent resistance this winding.  (half of total for a 2-winding).'
        self.propertyHelp[9] = 'Default = -1. Neutral resistance of wye (star)-connected winding in actual ohms.' + 'If entered as a negative value, the neutral is assumed to be open, or floating.'
        self.propertyHelp[10] = 'Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.'
        # general data
        self.propertyHelp[11] = 'Use this to specify all the bus connections at once using an array. Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\"'
        self.propertyHelp[12] = 'Use this to specify all the Winding connections at once using an array. Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + '~ conns=(delta, wye)'
        self.propertyHelp[13] = 'Use this to specify the kV ratings of all windings at once using an array. Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + DSSGlobals.CRLF + '~ conns=(delta, wye)' + DSSGlobals.CRLF + '~ kvs=(115, 12.47)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'See kV= property for voltage rules.'
        self.propertyHelp[14] = 'Use this to specify the kVA ratings of all windings at once using an array.'
        self.propertyHelp[15] = 'Use this to specify the p.u. tap of all windings at once using an array.'
        self.propertyHelp[16] = 'Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use ' + 'for 2- or 3-winding transformers. On the kva base of winding 1.'
        self.propertyHelp[17] = 'Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use ' + 'for 3-winding transformers only. On the kVA base of winding 1.'
        self.propertyHelp[18] = 'Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use ' + 'for 3-winding transformers only. On the kVA base of winding 1.'
        self.propertyHelp[19] = 'Use this to specify the percent reactance between all pairs of windings as an array. ' + 'All values are on the kVA base of winding 1.  The order of the values is as follows:' + DSSGlobals.CRLF + DSSGlobals.CRLF + '(x12 13 14... 23 24.. 34 ..)  ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'There will be n(n-1)/2 values, where n=number of windings.'
        self.propertyHelp[20] = 'Thermal time constant of the transformer in hours.  Typically about 2.'
        self.propertyHelp[21] = 'n Exponent for thermal properties in IEEE C57.  Typically 0.8.'
        self.propertyHelp[22] = 'm Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0'
        self.propertyHelp[23] = 'Temperature rise, deg C, for full load.  Default is 65.'
        self.propertyHelp[24] = 'Hot spot temperature rise, deg C.  Default is 15.'
        self.propertyHelp[25] = 'Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.'
        self.propertyHelp[26] = 'Percent no load losses at rated excitatation voltage. Default is 0. Converts to a resistance in parallel with the magnetizing impedance in each winding.'
        self.propertyHelp[27] = 'Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of' + 'maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.'
        self.propertyHelp[28] = 'Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of' + 'maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.'
        self.propertyHelp[29] = '={Yes|No}  Designates whether this transformer is to be considered a substation.' + 'Default is No.'
        # =y/n
        self.propertyHelp[30] = 'Max per unit tap for the active winding.  Default is 1.10'
        self.propertyHelp[31] = 'Min per unit tap for the active winding.  Default is 0.90'
        self.propertyHelp[32] = 'Total number of taps between min and max tap.  Default is 32.'
        self.propertyHelp[33] = 'Substation Name. Optional. Default is null. If specified, printed on plots'
        self.propertyHelp[34] = 'Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see \"ppm_antifloat\".'
        self.propertyHelp[35] = 'Default=1 ppm.  Parts per million of transformer winding VA rating connected to ground to protect against accidentally floating a winding without a reference. ' + 'If positive then the effect is adding a very large reactance to ground.  If negative, then a capacitor.'
        self.propertyHelp[36] = 'Use this property to specify all the winding %resistances using an array. Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + '~ %Rs=(0.2  0.3)'
        self.propertyHelp[37] = 'Name of the bank this transformer is part of, for CIM, MultiSpeak, and other interfaces.'
        self.propertyHelp[38] = 'Name of a library entry for transformer properties. The named XfmrCode must already be defined.'
        self.activeProperty = Transformer.NumPropsThisClass - 1
        super(TransformerImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(TransformerObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        """A Transf defaults to 3-phases, 2-windings (both wye)."""
        # continue parsing cmdline presently in parser
        parser = Parser.getInstance()
        # Make this object the active circuit element
        self.activeTransfObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeTransfObj)
        # use property to set this value
        result = 0
        at = self.activeTransfObj
        at.setXHLChanged(False)
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                at.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"Transformer.' + at.getName() + '\"', 110)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    at.setNPhases(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    at.setNumWindings(parser.makeInteger())
                    # reallocate stuff if bigger
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    at.setActiveWinding(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    at.setBus(at.getActiveWinding(), param)
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    self.interpretConnection(param)
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setKVLL(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setPUTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setRpu(parser.makeDouble() * 0.01)
                    # %R
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setRNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setXNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    self.interpretAllBuses(param)
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    self.interpretAllConns(param)
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    self.interpretAllkVRatings(param)
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    self.interpretAllkVARatings(param)
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    self.interpretAllTaps(param)
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    at.setXHL(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    at.setXHT(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    at.setXLT(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    parser.parseAsVector(((at.getNumWindings() - 1) * at.getNumWindings()) / 2, at.getXSC())
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    at.setThermalTimeConst(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    at.setNThermal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    at.setMThermal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 23):
                    _1 = True
                    at.setFLRise(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    at.setHSRise(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 25):
                    _1 = True
                    at.setPctLoadLoss(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 26):
                    _1 = True
                    at.setPctNoLoadLoss(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 27):
                    _1 = True
                    at.setNormMaxHKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 28):
                    _1 = True
                    at.setEmergMaxHKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 29):
                    _1 = True
                    at.setSubstation(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 30):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setMaxTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 31):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setMinTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 32):
                    _1 = True
                    at.getWinding()[at.getActiveWinding()].setNumTaps(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 33):
                    _1 = True
                    at.setSubstationName(param)
                    break
                if (_1 is True) or (_0 == 34):
                    _1 = True
                    at.setPctImag(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 35):
                    _1 = True
                    at.setPPM_FloatFactor(parser.makeDouble() * 1e-06)
                    break
                if (_1 is True) or (_0 == 36):
                    _1 = True
                    self.interpretAllRs(param)
                    break
                if (_1 is True) or (_0 == 37):
                    _1 = True
                    at.setXfmrBank(param)
                    break
                if (_1 is True) or (_0 == 38):
                    _1 = True
                    at.fetchXfmrCode(param)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeTransfObj, paramPointer - Transformer.NumPropsThisClass)
                    break
                break
            # Take care of properties that require some additional work
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    at.setNConds(at.getNPhases() + 1)
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    if at.getActiveWinding() == 0:
                        # TODO Check zero based indexing
                        _4 = True
                        i = 1
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < at.getNumWindings()):
                                break
                            at.getWinding()[i].setKVA(at.getWinding()[0].getKVA())
                        at.setNormMaxHKVA(1.1 * at.getWinding()[0].getKVA())
                        # defaults for new winding rating
                        at.setEmergMaxHKVA(1.5 * at.getWinding()[0].getKVA())
                        at.getWinding()[0].setRpu(at.getPctLoadLoss() / 2.0 / 100.0)
                        at.getWinding()[1].setRpu(at.getWinding()[0].getRpu())
                    elif at.getNumWindings() == 2:
                        at.getWinding()[0].setKVA(at.getWinding()[1].getKVA())
                        # for 2-winding, force both kVAs to be same
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    at.setPctLoadLoss((at.getWinding()[0].getRpu() + at.getWinding()[1].getRpu()) * 100.0)
                    break
                if (_3 is True) or (_2 == 14):
                    _3 = True
                    at.setNormMaxHKVA(1.1 * at.getWinding()[0].getKVA())
                    # defaults for new winding rating
                    at.setEmergMaxHKVA(1.5 * at.getWinding()[0].getKVA())
                    break
                if (_3 is True) or (_2 == 16):
                    _3 = True
                    at.setXHLChanged(True)
                    break
                if (_3 is True) or (_2 == 17):
                    _3 = True
                    at.setXHLChanged(True)
                    break
                if (_3 is True) or (_2 == 18):
                    _3 = True
                    at.setXHLChanged(True)
                    break
                if (_3 is True) or (_2 == 19):
                    _3 = True
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < ((at.getNumWindings() - 1) * at.getNumWindings()) / 2):
                            break
                        at.getXSC()[i] = at.getXsc(i) * 0.01
                        # convert to per unit   TODO Check translation
                    break
                if (_3 is True) or (_2 == 25):
                    _3 = True
                    at.getWinding()[0].setRpu(at.getPctLoadLoss() / 2.0 / 100.0)
                    at.getWinding()[1].setRpu(at.getWinding()[0].getRpu())
                    break
                break
            # YPrim invalidation on anything that changes impedance values
            if paramPointer >= 4 and paramPointer <= 18:
                at.setYPrimInvalid(True)
            else:
                _6 = paramPointer
                _7 = False
                while True:
                    if _6 == 25:
                        _7 = True
                        at.setYPrimInvalid(True)
                        break
                    if (_7 is True) or (_6 == 26):
                        _7 = True
                        at.setYPrimInvalid(True)
                        break
                    if (_7 is True) or (_6 == 34):
                        _7 = True
                        at.setYPrimInvalid(True)
                        break
                    if (_7 is True) or (_6 == 35):
                        _7 = True
                        at.setYPrimInvalid(True)
                        break
                    break
            # Advance to next property on input line
            paramName = parser.getNextParam()
            param = parser.makeString()
        at.recalcElementData()
        return result

    def setActiveWinding(self, w):
        at = self.activeTransfObj
        if w >= 0 and w < at.getNumWindings():
            at.setActiveWinding(w)
        else:
            DSSGlobals.doSimpleMsg('Wdg parameter invalid for \"' + at.getName() + '\"', 112)

    def interpretConnection(self, s):
        """Accepts (case insensitive):
        		delta or LL
        		Y, wye, or LN
        """
        at = self.activeTransfObj
        aw = at.getWinding()[at.getActiveWinding()]
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                aw.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                aw.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                aw.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = s.toLowerCase()[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        aw.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        aw.setConnection(1)
                        break
                    break
                break
            break
        at.setYorder(at.getNConds() * at.getNTerms())
        at.setYPrimInvalid(True)

    def interpretAllConns(self, s):
        """Routine expecting all winding connections expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            s1 = DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            s2 = DSSGlobals.auxParser.makeString()
            if len(s2) > 0:
                self.interpretConnection(s2)

    def interpretAllBuses(self, s):
        """Routine expecting all winding bus connections expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; Ignore omitted values
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            busNam = DSSGlobals.auxParser.makeString()
            if len(busNam) > 0:
                at.setBus(at.getActiveWinding(), busNam)

    def interpretAllkVRatings(self, s):
        """Routine expecting all winding kV ratings expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; ignore omitted values
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            dataStr = DSSGlobals.auxParser.makeString()
            if len(dataStr) > 0:
                at.getWinding()[at.getActiveWinding()].setKVLL(DSSGlobals.auxParser.makeDouble())

    def interpretAllkVARatings(self, s):
        """Routine expecting all winding ratings expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; ignore omitted values
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            dataStr = DSSGlobals.auxParser.makeString()
            if len(dataStr) > 0:
                at.getWinding()[at.getActiveWinding()].setKVA(DSSGlobals.auxParser.makeDouble())

    def interpretAllRs(self, s):
        """Routine expecting all winding ratings expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; ignore omitted values
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            dataStr = DSSGlobals.auxParser.makeString()
            if len(dataStr) > 0:
                at.getWinding()[at.getActiveWinding()].setRpu(DSSGlobals.auxParser.makeDouble() * 0.01)

    def interpretAllTaps(self, s):
        """Routine expecting all winding taps expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; ignore omitted values
        at = self.activeTransfObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < at.getNumWindings()):
                break
            at.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name, not expecting any
            dataStr = DSSGlobals.auxParser.makeString()
            if len(dataStr) > 0:
                at.getWinding()[at.getActiveWinding()].setPUTap(DSSGlobals.auxParser.makeDouble())

    def makeLike(self, transfName):
        result = 0
        # See if we can find this Transf name in the present collection
        otherTransf = self.find(transfName)
        if otherTransf is not None:
            at = self.activeTransfObj
            at.setNPhases(otherTransf.getNPhases())
            at.setNumWindings(otherTransf.getNumWindings())
            at.setNConds(at.getNPhases() + 1)
            # forces reallocation of terminals and conductors
            at.setYorder(at.getNConds() * at.getNTerms())
            at.setYPrimInvalid(True)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < at.getNumWindings()):
                    break
                w = at.getWinding()[i]
                w.setConnection(otherTransf.getWinding()[i].getConnection())
                w.setKVLL(otherTransf.getWinding()[i].getKVLL())
                w.setVBase(otherTransf.getWinding()[i].getVBase())
                w.setKVA(otherTransf.getWinding()[i].getKVA())
                w.setPUTap(otherTransf.getWinding()[i].getPUTap())
                w.setRpu(otherTransf.getWinding()[i].getRpu())
                w.setRNeut(otherTransf.getWinding()[i].getRNeut())
                w.setXNeut(otherTransf.getWinding()[i].getXNeut())
                # copy the taps
                w.setTapIncrement(otherTransf.getWinding()[i].getTapIncrement())
                w.setMinTap(otherTransf.getWinding()[i].getMinTap())
                w.setMaxTap(otherTransf.getWinding()[i].getMaxTap())
                w.setNumTaps(otherTransf.getWinding()[i].getNumTaps())
            at.setTermRef()
            at.setXHL(otherTransf.getXHL())
            at.setXHT(otherTransf.getXHT())
            at.setXLT(otherTransf.getXLT())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < (at.getNumWindings() * (at.getNumWindings() - 1)) / 2):
                    break
                at.getXSC()[i] = otherTransf.getXsc(i)
            at.getZB().copyFrom(otherTransf.getZB())
            at.getY_1Volt().copyFrom(otherTransf.getY_1Volt())
            at.getY_Term().copyFrom(otherTransf.getY_Term())
            at.getY_1Volt_NL().copyFrom(otherTransf.getY_1Volt_NL())
            at.getYTermNL().copyFrom(otherTransf.getYTermNL())
            at.setThermalTimeConst(otherTransf.getThermalTimeConst())
            at.setNThermal(otherTransf.getNThermal())
            at.setMThermal(otherTransf.getMThermal())
            at.setFLRise(otherTransf.getFLRise())
            at.setHSRise(otherTransf.getHSRise())
            at.setPctLoadLoss(otherTransf.getPctLoadLoss())
            at.setPctNoLoadLoss(otherTransf.getPctNoLoadLoss())
            at.setNormMaxHKVA(otherTransf.getNormMaxHKVA())
            at.setEmergMaxHKVA(otherTransf.getEmergMaxHKVA())
            at.setXfmrBank(otherTransf.getXfmrBank())
            at.setXfmrCode(otherTransf.getXfmrCode())
            self.classMakeLike(otherTransf)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < at.getParentClass().getNumProperties()):
                    break
                at.setPropertyValue(i, otherTransf.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Transf makeLike: \"' + transfName + '\" not found.', 113)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Transformer.init()', -1)
        return 0

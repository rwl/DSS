from dss.general.XfmrCode import (XfmrCode,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.impl.XfmrCodeObjImpl import (XfmrCodeObjImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)


class XfmrCodeImpl(DSSClassImpl, XfmrCode):

    class WdgParmChoice(object):
        CONN = 'CONN'
        KV = 'KV'
        KVA = 'KVA'
        R = 'R'
        TAP = 'TAP'
        _values = [CONN, KV, KVA, R, TAP]

        @classmethod
        def values(cls):
            return cls._enum_values[:]

    activeXfmrCodeObj = None

    def __init__(self):
        super(XfmrCodeImpl, self)()
        self.className = 'XfmrCode'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = XfmrCode.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'phases'
        self.propertyName[1] = 'windings'
        # winding definition
        self.propertyName[2] = 'wdg'
        self.propertyName[3] = 'conn'
        self.propertyName[4] = 'kV'
        # for 2-and 3- always kVLL else actual winding KV
        self.propertyName[5] = 'kVA'
        self.propertyName[6] = 'tap'
        self.propertyName[7] = '%R'
        self.propertyName[8] = 'Rneut'
        self.propertyName[9] = 'Xneut'
        # general data
        self.propertyName[10] = 'conns'
        self.propertyName[11] = 'kVs'
        self.propertyName[12] = 'kVAs'
        self.propertyName[13] = 'taps'
        self.propertyName[14] = 'Xhl'
        self.propertyName[15] = 'Xht'
        self.propertyName[16] = 'Xlt'
        self.propertyName[17] = 'Xscarray'
        # x12 13 14... 23 24.. 34 ..
        self.propertyName[18] = 'thermal'
        self.propertyName[19] = 'n'
        self.propertyName[20] = 'm'
        self.propertyName[21] = 'flrise'
        self.propertyName[22] = 'hsrise'
        self.propertyName[23] = '%loadloss'
        self.propertyName[24] = '%noloadloss'
        self.propertyName[25] = 'normhkVA'
        self.propertyName[26] = 'emerghkVA'
        self.propertyName[27] = 'MaxTap'
        self.propertyName[28] = 'MinTap'
        self.propertyName[29] = 'NumTaps'
        self.propertyName[30] = '%imag'
        self.propertyName[31] = 'ppm_antifloat'
        self.propertyName[32] = '%Rs'
        # define property help values
        self.propertyHelp[0] = 'Number of phases this transformer. Default is 3.'
        self.propertyHelp[1] = 'Number of windings, this transformers. (Also is the number of terminals) ' + 'Default is 2.'
        # winding definition
        self.propertyHelp[2] = 'Set this = to the number of the winding you wish to define.  Then set ' + 'the values for this winding.  Repeat for each winding.  Alternatively, use ' + 'the array collections (buses, kvas, etc.) to define the windings.  Note: ' + 'reactances are BETWEEN pairs of windings; they are not the property of a single winding.'
        self.propertyHelp[3] = 'Connection of this winding. Default is \"wye\" with the neutral solidly grounded.'
        self.propertyHelp[4] = 'For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding'
        self.propertyHelp[5] = 'Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings.' + 'If 2-winding transformer, forces other winding to same value. ' + 'When winding 1 is defined, all other windings are defaulted to the same rating ' + 'and the first two winding resistances are defaulted to the %loadloss value.'
        self.propertyHelp[6] = 'Per unit tap that this winding is normally on.'
        self.propertyHelp[7] = 'Percent resistance this winding.  (half of total for a 2-winding).'
        self.propertyHelp[8] = 'Default = -1. Neutral resistance of wye (star)-connected winding in actual ohms.' + 'If entered as a negative value, the neutral is assumed to be open, or floating.'
        self.propertyHelp[9] = 'Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.'
        # general data
        self.propertyHelp[10] = 'Use this to specify all the Winding connections at once using an array. Example:' + CRLF + CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + '~ conns=(delta, wye)'
        self.propertyHelp[11] = 'Use this to specify the kV ratings of all windings at once using an array. Example:' + CRLF + CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + CRLF + '~ conns=(delta, wye)' + CRLF + '~ kvs=(115, 12.47)' + CRLF + CRLF + 'See kV= property for voltage rules.'
        self.propertyHelp[12] = 'Use this to specify the kVA ratings of all windings at once using an array.'
        self.propertyHelp[13] = 'Use this to specify the normal p.u. tap of all windings at once using an array.'
        self.propertyHelp[14] = 'Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use ' + 'for 2- or 3-winding transformers. On the kva base of winding 1.'
        self.propertyHelp[15] = 'Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use ' + 'for 3-winding transformers only. On the kVA base of winding 1.'
        self.propertyHelp[16] = 'Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use ' + 'for 3-winding transformers only. On the kVA base of winding 1.'
        self.propertyHelp[17] = 'Use this to specify the percent reactance between all pairs of windings as an array. ' + 'All values are on the kVA base of winding 1.  The order of the values is as follows:' + CRLF + CRLF + '(x12 13 14... 23 24.. 34 ..)  ' + CRLF + CRLF + 'There will be n(n-1)/2 values, where n=number of windings.'
        self.propertyHelp[18] = 'Thermal time constant of the transformer in hours.  Typically about 2.'
        self.propertyHelp[19] = 'n Exponent for thermal properties in IEEE C57.  Typically 0.8.'
        self.propertyHelp[20] = 'm Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0'
        self.propertyHelp[21] = 'Temperature rise, deg C, for full load.  Default is 65.'
        self.propertyHelp[22] = 'Hot spot temperature rise, deg C.  Default is 15.'
        self.propertyHelp[23] = 'Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.'
        self.propertyHelp[24] = 'Percent no load losses at rated excitatation voltage. Default is 0. Converts to a resistance in parallel with the magnetizing impedance in each winding.'
        self.propertyHelp[25] = 'Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of' + 'maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.'
        self.propertyHelp[26] = 'Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of' + 'maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.'
        self.propertyHelp[27] = 'Max per unit tap for the active winding.  Default is 1.10'
        self.propertyHelp[28] = 'Min per unit tap for the active winding.  Default is 0.90'
        self.propertyHelp[29] = 'Total number of taps between min and max tap.  Default is 32.'
        self.propertyHelp[30] = 'Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see \"ppm_antifloat\".'
        self.propertyHelp[31] = 'Default=1 ppm.  Parts per million of transformer winding VA rating connected to ground to protect against accidentally floating a winding without a reference. ' + 'If positive then the effect is adding a very large reactance to ground.  If negative, then a capacitor.'
        self.propertyHelp[32] = 'Use this property to specify all the winding %resistances using an array. Example:' + CRLF + CRLF + 'New Transformer.T1 buses=\"Hibus, lowbus\" ' + '~ %Rs=(0.2  0.3)'
        self.activeProperty = self.NumPropsThisClass - 1
        super(XfmrCodeImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = XfmrCodeObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setActiveWinding(self, w):
        axc = self.activeXfmrCodeObj
        if w > 0 and w <= axc.getNumWindings():
            axc.setActiveWinding(w)
        else:
            DSSGlobals.doSimpleMsg('Wdg parameter invalid for \"' + self.activeXfmrCodeObj.getName() + '\"', 112)

    def interpretWindings(self, s, which):
        DSSGlobals.auxParser.setCmdString(s)
        axc = self.activeXfmrCodeObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < axc.getNumWindings()):
                break
            axc.setActiveWinding(i)
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            str = DSSGlobals.auxParser.makeString()
            if len(str) > 0:
                _1 = which
                _2 = False
                while True:
                    if _1 == self.CONN:
                        _2 = True
                        axc.getWinding()[axc.getActiveWinding()].setConnection(Utilities.interpretConnection(str))
                        break
                    if (_2 is True) or (_1 == self.KV):
                        _2 = True
                        axc.getWinding()[axc.getActiveWinding()].setKVLL(DSSGlobals.auxParser.makeDouble())
                        break
                    if (_2 is True) or (_1 == self.KVA):
                        _2 = True
                        axc.getWinding()[axc.getActiveWinding()].setKVA(DSSGlobals.auxParser.makeDouble())
                        break
                    if (_2 is True) or (_1 == self.R):
                        _2 = True
                        axc.getWinding()[axc.getActiveWinding()].setRpu(0.01 * DSSGlobals.auxParser.makeDouble())
                        break
                    if (_2 is True) or (_1 == self.TAP):
                        _2 = True
                        axc.getWinding()[axc.getActiveWinding()].setPUTap(DSSGlobals.auxParser.makeDouble())
                        break
                    break

    def edit(self):
        self.activeXfmrCodeObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeXfmrCodeObj
        updateXsc = False
        parser = Parser.getInstance()
        axc = self.activeXfmrCodeObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                axc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"XfmrCode.' + axc.getName() + '\"', 110)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    axc.setNPhases(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    axc.setNumWindings(parser.makeInteger())
                    # Reallocate stuff if bigger
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    axc.setActiveWinding(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setConnection(Utilities.interpretConnection(param))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setKVLL(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setPUTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setRpu(parser.makeDouble() * 0.01)
                    # %R
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setRNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setXNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    self.interpretWindings(param, self.WdgParmChoice.CONN)
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    self.interpretWindings(param, self.WdgParmChoice.KV)
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    self.interpretWindings(param, self.WdgParmChoice.KVA)
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    self.interpretWindings(param, self.WdgParmChoice.TAP)
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    axc.setXHL(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    axc.setXHT(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    axc.setXLT(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    parser.parseAsVector(((axc.getNumWindings() - 1) * axc.getNumWindings()) / 2, axc.getXSC())
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    axc.setThermalTimeConst(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    axc.setNThermal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    axc.setMThermal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    axc.setLRise(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 23):
                    _1 = True
                    axc.setHSRise(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    axc.setPctLoadLoss(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 25):
                    _1 = True
                    axc.setPctNoLoadLoss(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 26):
                    _1 = True
                    axc.setNormMaxHKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 27):
                    _1 = True
                    axc.setEmergMaxHKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 28):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setMaxTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 29):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setMinTap(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 30):
                    _1 = True
                    axc.getWinding()[axc.getActiveWinding()].setNumTaps(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 31):
                    _1 = True
                    axc.setPctImag(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 32):
                    _1 = True
                    axc.setPpmFloatFactor(parser.makeDouble() * 1e-06)
                    break
                if (_1 is True) or (_0 == 33):
                    _1 = True
                    self.interpretWindings(param, self.WdgParmChoice.R)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeXfmrCodeObj, paramPointer - XfmrCode.NumPropsThisClass)
                    break
                break
            # Take care of properties that require some additional work,
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 6:
                    _3 = True
                    if axc.getActiveWinding() == 1:
                        # TODO Check zero based indexing
                        _4 = True
                        i = 1
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < axc.getNumWindings()):
                                break
                            axc.getWinding()[i].setKVA(axc.getWinding()[0].getKVA())
                            # TODO Check zero based indexing
                        axc.setNormMaxHKVA(1.1 * axc.getWinding()[0].getKVA())
                        # defaults for new winding rating
                        axc.setEmergMaxHKVA(1.5 * axc.getWinding()[0].getKVA())
                        axc.getWinding()[0].setRpu(axc.getPctLoadLoss() / 2.0 / 100.0)
                        axc.getWinding()[1].setRpu(axc.getWinding()[0].getRpu())
                    elif axc.getNumWindings() == 2:
                        axc.getWinding()[0].setKVA(axc.getWinding()[1].getKVA())
                        # for 2-winding, force both kVAs to be same
                    # update LoadLosskW if winding %r changed; using only windings 1 and 2
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    axc.setPctLoadLoss(axc.getWinding()[0].getRpu() + (axc.getWinding()[1].getRpu() * 100.0))
                    break
                if (_3 is True) or (_2 == 13):
                    _3 = True
                    axc.setNormMaxHKVA(1.1 * axc.getWinding()[0].getKVA())
                    # defaults for new winding rating
                    axc.setEmergMaxHKVA(1.5 * axc.getWinding()[0].getKVA())
                    break
                if (_3 is True) or (_2 == 15):
                    _3 = True
                    updateXsc = True
                    break
                if (_3 is True) or (_2 == 16):
                    _3 = True
                    updateXsc = True
                    break
                if (_3 is True) or (_2 == 17):
                    _3 = True
                    updateXsc = True
                    break
                if (_3 is True) or (_2 == 18):
                    _3 = True
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < ((axc.getNumWindings() - 1) * axc.getNumWindings()) / 2):
                            break
                        axc.getXSC()[i] = axc.getXSC()[i] * 0.01
                        # Convert to per unit
                    break
                if (_3 is True) or (_2 == 24):
                    _3 = True
                    axc.getWinding()[0].setRpu(axc.getPctLoadLoss() / 2.0 / 100.0)
                    axc.getWinding()[1].setRpu(axc.getWinding()[0].getRpu())
                    break
                break
            # default all winding kVAs to first winding so latter do not have to be specified
            # advance to next property on input line
            paramName = parser.getNextParam()
            param = parser.makeString()
        if updateXsc:
            if axc.getNumWindings() <= 3:
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < (axc.getNumWindings() * (axc.getNumWindings() - 1)) / 2):
                        break
                    _7 = i
                    _8 = False
                    while True:
                        if _7 == 0:
                            _8 = True
                            axc.getXSC()[0] = axc.getXHL()
                            break
                        if (_8 is True) or (_7 == 1):
                            _8 = True
                            axc.getXSC()[1] = axc.getXHT()
                            break
                        if (_8 is True) or (_7 == 2):
                            _8 = True
                            axc.getXSC()[2] = axc.getXLT()
                            break
                        break
        return 0

    def makeLike(self, name):
        result = 0
        # See if we can find this ode in the present collection
        other = self.find(name)
        if other is not None:
            axc = self.activeXfmrCodeObj
            axc.setNPhases(other.getNPhases())
            axc.setNumWindings(other.getNumWindings())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < axc.getNumWindings()):
                    break
                wdg = axc.getWinding()[i]
                wdg.setConnection(other.getWinding()[i].getConnection())
                wdg.setKVLL(other.getWinding()[i].getKVLL())
                wdg.setVBase(other.getWinding()[i].getVBase())
                wdg.setKVA(other.getWinding()[i].getKVA())
                wdg.setPUTap(other.getWinding()[i].getPUTap())
                wdg.setRpu(other.getWinding()[i].getRpu())
                wdg.setRNeut(other.getWinding()[i].getRNeut())
                wdg.setXNeut(other.getWinding()[i].getXNeut())
                wdg.setTapIncrement(other.getWinding()[i].getTapIncrement())
                wdg.setMinTap(other.getWinding()[i].getMinTap())
                wdg.setMaxTap(other.getWinding()[i].getMaxTap())
                wdg.setNumTaps(other.getWinding()[i].getNumTaps())
            axc.setXHL(other.getXHL())
            axc.setXHT(other.getXHT())
            axc.setXLT(other.getXLT())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < (axc.getNumWindings() * (axc.getNumWindings() - 1)) / 2):
                    break
                axc.getXSC()[i] = other.getXSC()[i]
            axc.setThermalTimeConst(other.getThermalTimeConst())
            axc.setNThermal(other.getNThermal())
            axc.setMThermal(other.getMThermal())
            axc.setLRise(other.getLRise())
            axc.setHSRise(other.getHSRise())
            axc.setPctLoadLoss(other.getPctLoadLoss())
            axc.setPctNoLoadLoss(other.getPctNoLoadLoss())
            axc.setNormMaxHKVA(other.getNormMaxHKVA())
            axc.setEmergMaxHKVA(other.getEmergMaxHKVA())
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < axc.getParentClass().getNumProperties()):
                    break
                axc.setPropertyValue(i, other.getPropertyValue(i))
                result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in XfmrCode.makeLike: \"' + name + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement XfmrCode.init', -1)
        return 0

    def getCode(self):
        pXfmrCode = self.elementList.getActive()
        return pXfmrCode.getName()

    def setCode(self, value):
        self.activeXfmrCodeObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            xfmrCode = self.elementList.get(i)
            if xfmrCode.getName().equalsIgnoreCase(value):
                self.activeXfmrCodeObj = xfmrCode
                return
        DSSGlobals.doSimpleMsg('XfmrCode: \"' + value + '\" not found.', 103)

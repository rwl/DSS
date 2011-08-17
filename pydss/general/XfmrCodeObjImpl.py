from dss.general.XfmrCodeObj import (XfmrCodeObj,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.delivery.impl.TransformerImpl import (TransformerImpl,)
from dss.general.XfmrCode import (XfmrCode,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.WindingImpl import (WindingImpl,)


class XfmrCodeObjImpl(DSSObjectImpl, XfmrCodeObj):
    nPhases = None
    activeWinding = None
    numWindings = None
    maxWindings = None
    XHL = None
    XHT = None
    XLT = None
    # per unit
    XSC = None
    # per unit SC measurements
    VABase = None
    # for impedances
    normMaxHKVA = None
    emergMaxHKVA = None
    thermalTimeConst = None
    # hr
    nThermal = None
    mThermal = None
    # exponents
    LRise = None
    HSRise = None
    pctLoadLoss = None
    pctNoLoadLoss = None
    ppmFloatFactor = None
    # parts per million winding float factor
    pctImag = None
    winding = None

    def __init__(self, parClass, xfmrCodeName):
        super(XfmrCodeObjImpl, self)(parClass)
        self.setName(xfmrCodeName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # default values and sizes
        self.nPhases = 3
        self.numWindings = 2
        self.maxWindings = 2
        self.activeWinding = 1
        # TODO Check zero based indexing
        self.winding = [None] * self.maxWindings
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.maxWindings):
                break
            self.winding[i] = WindingImpl()
        self.XHL = 0.07
        self.XHT = 0.35
        self.XLT = 0.3
        self.XSC = [None] * (((self.numWindings - 1) * self.numWindings) / 2)
        self.VABase = self.winding[0].getKVA() * 1000.0
        self.thermalTimeConst = 2.0
        self.nThermal = 0.8
        self.mThermal = 0.8
        self.LRise = 65.0
        self.HSRise = 15.0
        # hot spot rise
        self.normMaxHKVA = 1.1 * self.winding[0].getKVA()
        self.emergMaxHKVA = 1.5 * self.winding[0].getKVA()
        self.pctLoadLoss = 2.0 * self.winding[0].getRpu() * 100.0
        # assume two windings
        self.ppmFloatFactor = 1e-06
        # Compute antifloat added for each winding
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            self.winding[i].computeAntiFloatAdder(self.ppmFloatFactor, self.VABase / self.nPhases)
        self.pctNoLoadLoss = 0.0
        self.pctImag = 0.0
        self.initPropertyValues(0)

    def setNumWindings(self, n):
        if n > 1:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                self.winding[i] = None
                # free old winding objects
            OldWdgSize = ((self.numWindings - 1) * self.numWindings) / 2
            self.numWindings = n
            self.maxWindings = n
            self.winding = Utilities.resizeArray(self.winding, self.maxWindings)
            # reallocate collector array
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.maxWindings):
                    break
                self.winding[i] = WindingImpl()
            self.XSC = Utilities.resizeArray(self.XSC, ((self.numWindings - 1) * self.numWindings) / 2)
            _2 = True
            i = OldWdgSize + 1
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                    break
                self.XSC[i] = 0.3
                # default to something
        else:
            DSSGlobals.doSimpleMsg('Invalid number of windings: ' + String.valueOf.valueOf(n) + ' for Transformer ' + TransformerImpl.activeTransfObj.getName(), 111)

    def pullFromTransformer(self, obj):
        self.setNumWindings(obj.getNumWindings())
        self.nPhases = obj.getNPhases()
        self.XHL = obj.getXHL()
        self.XHT = obj.getXHT()
        self.XLT = obj.getXLT()
        self.VABase = obj.getBaseVA()
        self.normMaxHKVA = obj.getNormMaxHKVA()
        self.emergMaxHKVA = obj.getEmergMaxHKVA()
        self.thermalTimeConst = obj.getThTau()
        self.nThermal = obj.getThN()
        self.mThermal = obj.getThM()
        self.LRise = obj.getFLRise()
        self.HSRise = obj.getHSRise()
        self.pctLoadLoss = obj.getPctLoadLoss()
        self.pctNoLoadLoss = obj.getPctNoLoadLoss()
        self.ppmFloatFactor = obj.getPPM_FloatFactor()
        self.pctImag = obj.getPctImag()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                break
            self.XSC[i] = obj.getXsc(i)
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            self.winding[i].setConnection(obj.getWdgConnection(i))
            self.winding[i].setKVLL(obj.getBasekVLL(i))
            self.winding[i].setVBase(obj.getBaseVoltage(i))
            self.winding[i].setKVA(obj.getWdgKVA(i))
            self.winding[i].setPUTap(obj.getPresentTap(i))
            self.winding[i].setRpu(obj.getWdgResistance(i))
            self.winding[i].setRNeut(obj.getWdgRNeutral(i))
            self.winding[i].setXNeut(obj.getWdgXNeutral(i))
            self.winding[i].setY_PPM(obj.getWdgYPPM(i))
            self.winding[i].setTapIncrement(obj.getTapIncrement(i))
            self.winding[i].setMinTap(obj.getMinTap(i))
            self.winding[i].setMaxTap(obj.getMaxTap(i))
            self.winding[i].setNumTaps(obj.getNumTaps(i))

    def dumpProperties(self, f, complete):
        super(XfmrCodeObjImpl, self).dumpProperties(f, complete)
        # Basic property dump
        print '~ ' + 'NumWindings=' + self.numWindings
        print '~ ' + 'phases=' + self.nPhases
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            wdg = self.winding[i]
            if i == 0:
                # TODO Check zero based indexing
                print '~ ' + 'Wdg=' + i
            else:
                print '~ ' + 'Wdg=' + i
            _1 = wdg.getConnection()
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    print '~ conn=wye'
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print '~ conn=delta'
                    break
                break
            print '~ kv=' + wdg.getKVLL()
            print '~ kva=' + wdg.getKVA()
            print '~ tap=' + wdg.getPUTap()
            print '~ %r=' + (wdg.getRpu() * 100.0)
            print '~ rneut=' + wdg.getRNeut()
            print '~ xneut=' + wdg.getXNeut()
        print '~ ' + 'xhl=' + (self.XHL * 100.0)
        print '~ ' + 'xht=' + (self.XHT * 100.0)
        print '~ ' + 'xlt=' + (self.XLT * 100.0)
        f.print_('~ Xscmatrix= \"')
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                break
            f.print_((self.XSC[i] * 100.0) + ' ')
        print '\"'
        print '~ ' + 'NormMAxHkVA=' + self.normMaxHKVA
        print '~ ' + 'EmergMAxHkVA=' + self.emergMaxHKVA
        print '~ ' + 'thermal=' + self.thermalTimeConst
        print '~ ' + 'n=' + self.nThermal
        print '~ ' + 'm=' + self.mThermal
        print '~ ' + 'flrise=' + self.LRise
        print '~ ' + 'hsrise=' + self.HSRise
        print '~ ' + '%loadloss=' + self.pctLoadLoss
        print '~ ' + '%noloadloss=' + self.pctNoLoadLoss
        _4 = True
        i = 27
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < XfmrCode.NumPropsThisClass):
                break
            # TODO Check zero based indexing
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        _5 = True
        i = XfmrCode.NumPropsThisClass + 1
        while True:
            if _5 is True:
                _5 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            # TODO Check zero based indexing
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)

    def getPropertyValue(self, index):
        """Gets the property for the active winding.
        Set the active winding before calling.
        """
        _0 = index
        _1 = False
        while True:
            if _0 == 10:
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 13):
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 17):
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 32):
                _1 = True
                result = '['
                break
            if True:
                _1 = True
                result = ''
                break
            break
        _2 = index
        _3 = False
        while True:
            if _2 == 3:
                _3 = True
                result = String.valueOf.valueOf(self.activeWinding)
                # return active winding
                break
            if (_3 is True) or (_2 == 4):
                _3 = True
                _4 = self.winding[self.activeWinding].getConnection()
                _5 = False
                while True:
                    if _4 == 0:
                        _5 = True
                        result = 'wye '
                        break
                    if (_5 is True) or (_4 == 1):
                        _5 = True
                        result = 'delta '
                        break
                    break
                break
            if (_3 is True) or (_2 == 5):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getKVLL())
                break
            if (_3 is True) or (_2 == 6):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getKVA())
                break
            if (_3 is True) or (_2 == 7):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getPUTap())
                break
            if (_3 is True) or (_2 == 8):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getRpu() * 100.0)
                # %R
                break
            if (_3 is True) or (_2 == 9):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getRNeut())
                break
            if (_3 is True) or (_2 == 10):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getXNeut())
                break
            if (_3 is True) or (_2 == 11):
                _3 = True
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    _7 = self.winding[i].getConnection()
                    _8 = False
                    while True:
                        if _7 == 0:
                            _8 = True
                            result = result + 'wye, '
                            break
                        if (_8 is True) or (_7 == 1):
                            _8 = True
                            result = result + 'delta, '
                            break
                        break
                break
            if (_3 is True) or (_2 == 12):
                _3 = True
                _9 = True
                i = 0
                while True:
                    if _9 is True:
                        _9 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getKVLL())
                break
            if (_3 is True) or (_2 == 13):
                _3 = True
                _10 = True
                i = 0
                while True:
                    if _10 is True:
                        _10 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getKVA())
                break
            if (_3 is True) or (_2 == 14):
                _3 = True
                _11 = True
                i = 0
                while True:
                    if _11 is True:
                        _11 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getPUTap())
                break
            if (_3 is True) or (_2 == 18):
                _3 = True
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                        break
                    result = result + String.format.format('%-g, ', self.XSC[i] * 100.0)
            if (_3 is True) or (_2 == 24):
                _3 = True
                result = String.format.format('%.7g', self.pctLoadLoss)
                break
            if (_3 is True) or (_2 == 25):
                _3 = True
                result = String.format.format('%.7g', self.pctNoLoadLoss)
                break
            if (_3 is True) or (_2 == 28):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getMaxTap())
                break
            if (_3 is True) or (_2 == 29):
                _3 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getMinTap())
                break
            if (_3 is True) or (_2 == 30):
                _3 = True
                result = String.format.format('%-d', self.winding[self.activeWinding].getNumTaps())
                break
            if (_3 is True) or (_2 == 33):
                _3 = True
                _13 = True
                i = 0
                while True:
                    if _13 is True:
                        _13 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getRpu() * 100.0)
                break
            if True:
                _3 = True
                result = super(XfmrCodeObjImpl, self).getPropertyValue(index)
                break
            break
        _14 = index
        _15 = False
        while True:
            if _14 == 10:
                _15 = True
                result = ']'
                break
            if (_15 is True) or (_14 == 11):
                _15 = True
                result = ']'
                break
            if (_15 is True) or (_14 == 12):
                _15 = True
                result = ']'
                break
            if (_15 is True) or (_14 == 13):
                _15 = True
                result = ']'
                break
            if (_15 is True) or (_14 == 17):
                _15 = True
                result = ']'
                break
            if (_15 is True) or (_14 == 32):
                _15 = True
                result = ']'
                break
            if True:
                _15 = True
                result = ''
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        # FIXME Private members in OpenDSS
        self.propertyValue[0] = '3'
        # "phases";
        self.propertyValue[1] = '2'
        # "windings";
        self.propertyValue[2] = '1'
        # "wdg";
        self.propertyValue[3] = 'wye'
        # "conn";
        self.propertyValue[4] = '12.47'
        # if 2or 3-phase: phase-phase else actual winding
        self.propertyValue[5] = '1000'
        self.propertyValue[6] = '1.0'
        self.propertyValue[7] = '0.2'
        self.propertyValue[8] = '-1'
        self.propertyValue[9] = '0'
        self.propertyValue[10] = ''
        self.propertyValue[11] = ''
        # if 1-phase: actual winding rating; else phase-phase
        self.propertyValue[12] = ''
        # if 1-phase: actual winding rating; else phase-phase
        self.propertyValue[13] = ''
        self.propertyValue[14] = '7'
        self.propertyValue[15] = '35'
        self.propertyValue[16] = '30'
        self.propertyValue[17] = ''
        # x12 13 14... 23 24.. 34 ..
        self.propertyValue[18] = '2'
        self.propertyValue[19] = '.8'
        self.propertyValue[20] = '.8'
        self.propertyValue[21] = '65'
        self.propertyValue[22] = '15'
        self.propertyValue[23] = '0'
        self.propertyValue[24] = '0'
        self.propertyValue[25] = ''
        self.propertyValue[26] = ''
        self.propertyValue[27] = '1.10'
        self.propertyValue[28] = '0.90'
        self.propertyValue[29] = '32'
        self.propertyValue[30] = '0'
        self.propertyValue[31] = '1'
        self.propertyValue[32] = ''
        super(XfmrCodeObjImpl, self).initPropertyValues(XfmrCode.NumPropsThisClass)

    def getNPhases(self):
        return self.nPhases

    def setNPhases(self, num):
        self.nPhases = num

    def getActiveWinding(self):
        return self.activeWinding

    def setActiveWinding(self, winding):
        self.activeWinding = winding

    def getMaxWindings(self):
        return self.maxWindings

    def setMaxWindings(self, max):
        self.maxWindings = max

    def getXHL(self):
        return self.XHL

    def setXHL(self, value):
        self.XHL = value

    def getXHT(self):
        return self.XHT

    def setXHT(self, value):
        self.XHT = value

    def getXLT(self):
        return self.XLT

    def setXLT(self, value):
        self.XLT = value

    def getXSC(self):
        return self.XSC

    def setXSC(self, value):
        self.XSC = value

    def getVABase(self):
        return self.VABase

    def setVABase(self, base):
        self.VABase = base

    def getNormMaxHKVA(self):
        return self.normMaxHKVA

    def setNormMaxHKVA(self, max):
        self.normMaxHKVA = max

    def getEmergMaxHKVA(self):
        return self.emergMaxHKVA

    def setEmergMaxHKVA(self, max):
        self.emergMaxHKVA = max

    def getThermalTimeConst(self):
        return self.thermalTimeConst

    def setThermalTimeConst(self, timeConst):
        self.thermalTimeConst = timeConst

    def getNThermal(self):
        return self.nThermal

    def setNThermal(self, n):
        self.nThermal = n

    def getMThermal(self):
        return self.mThermal

    def setMThermal(self, m):
        self.mThermal = m

    def getLRise(self):
        return self.LRise

    def setLRise(self, rise):
        self.LRise = rise

    def getHSRise(self):
        return self.HSRise

    def setHSRise(self, rise):
        self.HSRise = rise

    def getPctLoadLoss(self):
        return self.pctLoadLoss

    def setPctLoadLoss(self, pct):
        self.pctLoadLoss = pct

    def getPctNoLoadLoss(self):
        return self.pctNoLoadLoss

    def setPctNoLoadLoss(self, pct):
        self.pctNoLoadLoss = pct

    def getPpmFloatFactor(self):
        return self.ppmFloatFactor

    def setPpmFloatFactor(self, factor):
        self.ppmFloatFactor = factor

    def getPctImag(self):
        return self.pctImag

    def setPctImag(self, pct):
        self.pctImag = pct

    def getWinding(self):
        return self.winding

    def setWinding(self, values):
        self.winding = values

    def getNumWindings(self):
        return self.numWindings

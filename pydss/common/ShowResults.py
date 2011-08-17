from dss.conversion.Generator import (Generator,)
from dss.delivery.impl.LineImpl import (LineImpl,)
from dss.shared.impl.CktTreeImpl import (CktTreeImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.EnergyMeter import (EnergyMeter,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.IOException import (IOException,)
# from java.io.PrintWriter import (PrintWriter,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class ShowResults(object):
    TABCHAR = '\u0009'
    maxBusNameLength = 12
    maxDeviceNameLength = 30

    @classmethod
    def setMaxBusNameLength(cls):
        cls.maxBusNameLength = 4
        ckt = DSSGlobals.activeCircuit
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            cls.maxBusNameLength = cls.Math.max(cls.maxBusNameLength, len(ckt.getBusList().get(i)))

    @classmethod
    def setMaxDeviceNameLength(cls):
        ckt = DSSGlobals.activeCircuit
        cls.maxDeviceNameLength = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumDevices()):
                break
            devName = ckt.getDeviceList().get(i)
            devClassName = DSSGlobals.DSSClassList.get(ckt.getDeviceRef()[i].cktElementClass).getName()
            cls.maxDeviceNameLength = cls.Math.max(cls.maxDeviceNameLength, len(devName) + len(devClassName) + 1)

    @classmethod
    def writeSeqVoltages(cls, pw, i, LL):
        Vph = [None] * 3
        VLL = [None] * 3
        V012 = [None] * 3
        ckt = DSSGlobals.activeCircuit
        if ckt.getBuses()[i].getNumNodesThisBus() >= 3:
            _0 = True
            j = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    j += 1
                if not (j < 3):
                    break
                Vph[j] = ckt.getSolution().getNodeV()[ckt.getBuses()[i].getRef(j)]
            if LL:
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < 3):
                        break
                    k = j + 1
                    if k > 3:
                        k = 1
                        # TODO Check zero based indexing
                    VLL[j] = Vph[j].subtract(Vph[k])
                MathUtil.phase2SymComp(VLL, V012)
            else:
                MathUtil.phase2SymComp(Vph, V012)
            V0 = V012[0].abs()
            V1 = V012[1].abs()
            V2 = V012[2].abs()
        else:
            Vph[0] = ckt.getSolution().getNodeV()[ckt.getBuses()[i].getRef(0)]
            V0 = 0.0
            V1 = Vph[0].abs()
            # use first phase value for non-three phase buses
            V2 = 0.0
        V1 = V1 / 1000.0
        # Convert to kV
        V2 = V2 / 1000.0
        V0 = V0 / 1000.0
        # calc per unit value
        if ckt.getBuses()[i].getKVBase() != 0.0:
            Vpu = V1 / ckt.getBuses()[i].getKVBase()
        else:
            Vpu = 0.0
        if LL:
            Vpu = Vpu / DSSGlobals.SQRT3
        if V1 > 0.0:
            V2V1 = (100.0 * V2) / V1
            V0V1 = (100.0 * V0) / V1
        else:
            V2V1 = 0.0
            V0V1 = 0.0
        pw.printf('%s %9.4g  %9.4g  %9.4g  %9.4g %9.4g %9.4g', Utilities.pad(ckt.getBusList().get(i), cls.maxBusNameLength), V1, Vpu, V2, V2V1, V0, V0V1)
        print 

    @classmethod
    def writeBusVoltages(cls, pw, i, LL):
        ckt = DSSGlobals.activeCircuit
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < ckt.getBuses()[i].getNumNodesThisBus()):
                break
            nref = ckt.getBuses()[i].getRef(j)
            volts = ckt.getSolution().getNodeV()[nref]
            if LL and j < 4:
                # TODO Check zero based indexing
                # convert to line-line assuming no more than 3 phases
                k = j + 1
                if k > 3:
                    k = 1
                    # TODO Check zero based indexing
                if k <= ckt.getBuses()[i].getNumNodesThisBus():
                    nref = ckt.getBuses()[i].getRef(k)
                    volts = volts.subtract(ckt.getSolution().getNodeV()[nref])
            Vmag = volts.abs() * 0.001
            if ckt.getBuses()[i].getKVBase() != 0.0:
                Vpu = Vmag / ckt.getBuses()[i].getKVBase()
            else:
                Vpu = 0.0
            if LL:
                Vpu = Vpu / DSSGlobals.SQRT3
            if j == 0:
                # TODO Check zero based indexing
                BName = Utilities.padDots(ckt.getBusList().get(i), cls.maxBusNameLength)
            else:
                BName = Utilities.pad('   -', cls.maxBusNameLength)
            pw.printf('%s %2d %10.5g /_ %6.1f %9.5g %9.3f', BName, ckt.getBuses()[i].getNum(j), Vmag, ComplexUtil.degArg(volts), Vpu, ckt.getBuses()[i].getKVBase() * DSSGlobals.SQRT3)
            print 

    @classmethod
    def writeElementVoltages(cls, pw, pElem, LL):
        ckt = DSSGlobals.activeCircuit
        nCond = pElem.getNConds()
        nTerm = pElem.getNTerms()
        k = -1
        busName = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
        print 'ELEMENT = \"' + pElem.getDSSClassName() + '.' + pElem.getName() + '\"'
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < nTerm):
                break
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < nCond):
                    break
                k += 1
                nref = pElem.getNodeRef()[k]
                volts = ckt.getSolution().getNodeV()[nref]
                Vmag = volts.abs() * 0.001
                if nref == 0:
                    Vpu = 0.0
                else:
                    bref = ckt.getMapNodeToBus()[nref].busRef
                    if ckt.getBuses()[bref].getKVBase() != 0.0:
                        Vpu = Vmag / ckt.getBuses()[bref].getKVBase()
                    else:
                        Vpu = 0.0
                if LL:
                    Vpu = Vpu / DSSGlobals.SQRT3
                pw.printf('%s  (%3d) %4d    %13.5g (%8.4g) /_ %6.1f', busName, nref, i, Vmag, Vpu, ComplexUtil.degArg(volts))
                print 
            if j < nTerm:
                print '------------'
            busName = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)

    @classmethod
    def writeElementDeltaVoltages(cls, pw, pElem):
        ckt = DSSGlobals.activeCircuit
        nCond = pElem.getNConds()
        elemName = Utilities.pad(pElem.getDSSClassName() + '.' + pElem.getName(), cls.maxDeviceNameLength)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < nCond):
                break
            node1 = pElem.getNodeRef()[i]
            node2 = pElem.getNodeRef()[i + nCond]
            if node1 > 0:
                bus1 = ckt.getMapNodeToBus()[node1].busRef
            else:
                bus1 = 0
            if node2 > 0:
                bus2 = ckt.getMapNodeToBus()[node2].busRef
            else:
                bus2 = 0
            if bus1 > 0 and bus2 > 0:
                # TODO Check zero based indexing
                volts1 = ckt.getSolution().getNodeV()[node1]
                # OK if Node1 or Node2 = 0
                volts2 = ckt.getSolution().getNodeV()[node2]
                volts1 = volts1.subtract(volts2)
                # diff voltage
                if ckt.getBuses()[bus1].getKVBase() != ckt.getBuses()[bus2].getKVBase():
                    Vmag = 0.0
                elif ckt.getBuses()[bus1].getKVBase() > 0.0:
                    Vmag = (volts1.abs() / (1000.0 * ckt.getBuses()[bus1].getKVBase())) * 100.0
                else:
                    Vmag = 0.0
                pw.printf('%s,  %4d,    %12.5g, %12.5g, %12.5g, %6.1f', elemName, i, volts1.abs(), Vmag, ckt.getBuses()[bus1].getKVBase(), ComplexUtil.degArg(volts1))
                print 

    @classmethod
    def showVoltages(cls, fileName, LL, showOptionCode):
        """Show bus voltages by circuit element terminal."""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            cls.setMaxBusNameLength()
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            _0 = showOptionCode
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    print 
                    if LL:
                        print 'SYMMETRICAL COMPONENT PHASE-PHASE VOLTAGES BY BUS (for 3-phase buses)'
                    else:
                        print 'SYMMETRICAL COMPONENT VOLTAGES BY BUS (for 3-phase buses)'
                    print 
                    print Utilities.pad('Bus', cls.maxBusNameLength) + '  Mag:   V1 (kV)    p.u.     V2 (kV)   %V2/V1    V0 (kV)    %V0/V1'
                    print 
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < ckt.getNumBuses()):
                            break
                        cls.writeSeqVoltages(pw, i, LL)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    print 
                    if LL:
                        print 'PHASE-PHASE VOLTAGES BY BUS & NODE'
                    else:
                        print 'NODE-GROUND VOLTAGES BY BUS & NODE'
                    print 
                    print Utilities.pad('Bus', cls.maxBusNameLength) + ' Node    V (kV)    Angle      p.u.   Base kV'
                    print 
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < ckt.getNumBuses()):
                            break
                        cls.writeBusVoltages(pw, i, LL)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    print 
                    print 'NODE-GROUND VOLTAGES BY CIRCUIT ELEMENT'
                    print 
                    print 'Power Delivery Elements'
                    print 
                    print Utilities.pad('Bus', cls.maxBusNameLength) + ' (node ref)  Phase    Magnitude, kV (pu)    Angle'
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            cls.writeElementVoltages(pw, pElem, LL)
                        print 
                    # PD elements first
                    for pElem in ckt.getPDElements():
                        if pElem.isEnabled():
                            cls.writeElementVoltages(pw, pElem, LL)
                        print 
                    print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
                    print 
                    print 'Power Conversion Elements'
                    print 
                    print Utilities.pad('Bus', cls.maxBusNameLength) + ' (node ref)  Phase    Magnitude, kV (pu)    Angle'
                    print 
                    # PC elements next
                    for pElem in ckt.getPCElements():
                        if pElem.isEnabled():
                            cls.writeElementVoltages(pw, pElem, LL)
                        print 
                    break
                break
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def getI0I1I2(cls, I0, I1, I2, CMax, nPhases, kOffset, cBuffer):
        Iph = [None] * 3
        I012 = [None] * 3
        if nPhases >= 3:
            CMax = 0.0
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < 3):
                    break
                Iph[i] = cBuffer[kOffset + i]
                CMag = Iph[i].abs()
                if CMag > CMax:
                    CMax = CMag
            MathUtil.phase2SymComp(Iph, I012)
            I0 = I012[0].abs()
            I1 = I012[1].abs()
            I2 = I012[2].abs()
        else:
            I0 = 0.0
            I1 = cBuffer[1 + kOffset].abs()
            I2 = 0.0
            CMax = I1

    @classmethod
    def writeSeqCurrents(cls, pw, paddedBrName, I0, I1, I2, CMax, normAmps, emergAmps, j, DSSObjType):
        INormal = 0.0
        IEmerg = 0.0
        if j == 0:
            # TODO Check zero based indexing
            name = paddedBrName
        else:
            name = Utilities.pad('   -', len(paddedBrName))
        if I1 > 0.0:
            I2I1 = (100.0 * I2) / I1
        else:
            I2I1 = 0.0
        if I1 > 0.0:
            I0I1 = (100.0 * I0) / I1
        else:
            I0I1 = 0.0
        if DSSClassDefs.CLASSMASK & DSSObjType != DSSClassDefs.CAP_ELEMENT and j == 0:
            # TODO Check zero based indexing
            # only write overloads for non-capacitors and terminal 1
            if normAmps > 0.0:
                INormal = (CMax / normAmps) * 100.0
            if emergAmps > 0.0:
                IEmerg = (CMax / emergAmps) * 100.0
        pw.printf('%s %3d  %10.5g   %10.5g %8.2f  %10.5g %8.2f  %8.2f %8.2f', name, j, I1, I2, I2I1, I0, I0I1, INormal, IEmerg)
        print 

    @classmethod
    def writeTerminalCurrents(cls, pw, pElem, showResidual):
        nCond = pElem.getNConds()
        nTerm = pElem.getNTerms()
        cBuffer = [None] * nCond * nTerm
        pElem.getCurrents(cBuffer)
        k = -1
        fromBus = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
        print 'ELEMENT = ' + Utilities.fullName(pElem)
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < nTerm):
                break
            CTotal = Complex.ZERO
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < nCond):
                    break
                k += 1
                if showResidual:
                    CTotal = CTotal.add(cBuffer[k])
                pw.printf('%s  %4d    %13.5g /_ %6.1f', fromBus, Utilities.getNodeNum(pElem.getNodeRef()[k]), cBuffer[k].abs(), ComplexUtil.degArg(cBuffer[k]))
                print 
            if showResidual and pElem.getNPhases() > 1:
                residPolar = ComplexUtil.complexToPolarDeg(CTotal.negate())
                pw.printf('%s Resid    %13.5g /_ %6.1f', fromBus, residPolar.getMag(), residPolar.getAng())
                print 
            if j < nTerm:
                print '------------'
                # TODO Check zero based indexing
            fromBus = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)
        print 

    @classmethod
    def showCurrents(cls, fileName, showResidual, showOptionCode):
        I0 = 0
        I1 = 0
        I2 = 0
        CMax = 0
        ckt = DSSGlobals.activeCircuit
        cls.setMaxDeviceNameLength()
        cls.setMaxBusNameLength()
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            _0 = showOptionCode
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    print 
                    print 'SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT (first 3 phases)'
                    print 
                    print Utilities.pad('Element', cls.maxDeviceNameLength + 2) + ' Term      I1         I2         %I2/I1    I0         %I0/I1   %Normal %Emergency'
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            cBuffer = [None] * nCond * nTerm
                            pElem.getCurrents(cBuffer)
                            _2 = True
                            j = 0
                            while True:
                                if _2 is True:
                                    _2 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                cls.getI0I1I2(I0, I1, I2, CMax, pElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                # TODO Check pass be value
                                cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(pElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, pElem.getDSSObjType())
                            cBuffer = None
                    # PD elements next
                    for PDElem in ckt.getPDElements():
                        if PDElem.isEnabled():
                            nCond = PDElem.getNConds()
                            nTerm = PDElem.getNTerms()
                            cBuffer = [None] * nCond * nTerm
                            PDElem.getCurrents(cBuffer)
                            _3 = True
                            j = 0
                            while True:
                                if _3 is True:
                                    _3 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                cls.getI0I1I2(I0, I1, I2, CMax, PDElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(PDElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, PDElem.getNormAmps(), PDElem.getEmergAmps(), j, PDElem.getDSSObjType())
                            cBuffer = None
                    # PC elements next
                    for PCElem in ckt.getPCElements():
                        if PCElem.isEnabled():
                            nCond = PCElem.getNConds()
                            nTerm = PCElem.getNTerms()
                            cBuffer = [None] * nCond * nTerm
                            PCElem.getCurrents(cBuffer)
                            _4 = True
                            j = 0
                            while True:
                                if _4 is True:
                                    _4 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                cls.getI0I1I2(I0, I1, I2, CMax, PCElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(PCElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, PCElem.getDSSObjType())
                            cBuffer = None
                    # faults next
                    for pElem in ckt.getFaults():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            cBuffer = [None] * nCond * nTerm
                            pElem.getCurrents(cBuffer)
                            _5 = True
                            j = 0
                            while True:
                                if _5 is True:
                                    _5 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                cls.getI0I1I2(I0, I1, I2, CMax, pElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(pElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, pElem.getDSSObjType())
                            cBuffer = None
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    print 
                    print 'CIRCUIT ELEMENT CURRENTS'
                    print 
                    print '(Currents into element from indicated bus)'
                    print 
                    print 'Power Delivery Elements'
                    print 
                    print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase    Magnitude, A     Angle'
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            cls.writeTerminalCurrents(pw, pElem, False)
                    # PD elements next
                    for pElem in ckt.getPDElements():
                        if pElem.isEnabled():
                            cls.writeTerminalCurrents(pw, pElem, showResidual)
                    # faults
                    for pElem in ckt.getFaults():
                        if pElem.isEnabled():
                            cls.writeTerminalCurrents(pw, pElem, False)
                    print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
                    print 
                    print 'Power Conversion Elements'
                    print 
                    print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase    Magnitude, A     Angle'
                    print 
                    # PC elements next
                    for pElem in ckt.getPCElements():
                        if pElem.isEnabled():
                            cls.writeTerminalCurrents(pw, pElem, False)
                    break
                break
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            DSSGlobals.doSimpleMsg('Exception raised in ShowCurrents: ' + e.getMessage(), 2190)

    @classmethod
    def showPowers(cls, fileName, opt, showOptionCode):
        """opt = 0: kVA
        opt = 1: MVA
        """
        Vph = [None] * 3
        V012 = [None] * 3
        Iph = [None] * 3
        I012 = [None] * 3
        ckt = DSSGlobals.activeCircuit
        cls.setMaxDeviceNameLength()
        cls.setMaxBusNameLength()
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # Allocate cBuffer big enough for largest circuit element
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            _0 = showOptionCode
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    print 
                    print 'SYMMETRICAL COMPONENT POWERS BY CIRCUIT ELEMENT (first 3 phases)                                     Excess Power'
                    print 
                    _2 = opt
                    _3 = False
                    while True:
                        if _2 == 1:
                            _3 = True
                            print Utilities.pad('Element', cls.maxDeviceNameLength + 2) + ' Term    P1(MW)   Q1(Mvar)       P2         Q2      P0      Q0       P_Norm      Q_Norm     P_Emerg    Q_Emerg'
                            break
                        if True:
                            _3 = True
                            print Utilities.pad('Element', cls.maxDeviceNameLength + 2) + ' Term    P1(kW)   Q1(kvar)       P2         Q2      P0      Q0       P_Norm      Q_Norm     P_Emerg    Q_Emerg'
                            break
                        break
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            pElem.getCurrents(cBuffer)
                            _4 = True
                            j = 0
                            while True:
                                if _4 is True:
                                    _4 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                pw.print_(Utilities.pad(Utilities.fullName(pElem), cls.maxDeviceNameLength + 2) + j)
                                _5 = True
                                i = 0
                                while True:
                                    if _5 is True:
                                        _5 = False
                                    else:
                                        i += 1
                                    if not (i < cls.Math.min(3, pElem.getNPhases())):
                                        break
                                    k = ((j - 1) * nCond) + i
                                    nref = pElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    Iph[i] = cBuffer[k]
                                    Vph[i] = volts
                                if pElem.getNPhases() >= 3:
                                    MathUtil.phase2SymComp(Iph, I012)
                                    MathUtil.phase2SymComp(Vph, V012)
                                else:
                                    # Handle single phase and pos seq models
                                    V012[0] = Complex.ZERO
                                    I012[0] = Complex.ZERO
                                    V012[2] = Complex.ZERO
                                    I012[2] = Complex.ZERO
                                    if ckt.isPositiveSequence():
                                        V012[1] = Vph[0]
                                        I012[1] = Iph[0]
                                    else:
                                        V012[1] = Complex.ZERO
                                        I012[1] = Complex.ZERO
                                S = V012[1].multiply(I012[1].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[2].multiply(I012[2].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[0].multiply(I012[0].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                print 
                    # PD elements next
                    for PDElem in ckt.getPDElements():
                        if PDElem.isEnabled():
                            nCond = PDElem.getNConds()
                            nTerm = PDElem.getNTerms()
                            PDElem.getCurrents(cBuffer)
                            _6 = True
                            j = 0
                            while True:
                                if _6 is True:
                                    _6 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                pw.print_(Utilities.pad(Utilities.fullName(PDElem), cls.maxDeviceNameLength + 2) + j)
                                _7 = True
                                i = 0
                                while True:
                                    if _7 is True:
                                        _7 = False
                                    else:
                                        i += 1
                                    if not (i < cls.Math.min(3, PDElem.getNPhases())):
                                        break
                                    k = ((j - 1) * nCond) + i
                                    nref = PDElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    Iph[i] = cBuffer[k]
                                    Vph[i] = volts
                                if PDElem.getNPhases() >= 3:
                                    MathUtil.phase2SymComp(Iph, I012)
                                    MathUtil.phase2SymComp(Vph, V012)
                                else:
                                    # Handle single phase and pos seq models
                                    V012[0] = Complex.ZERO
                                    I012[0] = Complex.ZERO
                                    V012[1] = Complex.ZERO
                                    I012[1] = Complex.ZERO
                                    if ckt.isPositiveSequence():
                                        V012[1] = Vph[0]
                                        I012[1] = Iph[0]
                                    else:
                                        V012[1] = Complex.ZERO
                                        I012[1] = Complex.ZERO
                                S = V012[1].multiply(I012[1].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[2].multiply(I012[2].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[0].multiply(I012[0].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                if j == 0:
                                    # TODO Check zero based indexing
                                    S = PDElem.getExcessKVANorm(0)
                                    if opt == 1:
                                        S = S.multiply(0.001)
                                    pw.print_(S.getReal())
                                    pw.print_(S.getImaginary())
                                    S = PDElem.getExcessKVAEmerg(1)
                                    # TODO Check zero based indexing
                                    if opt == 1:
                                        S = S.multiply(0.001)
                                    pw.print_(S.getReal())
                                    pw.print_(S.getImaginary())
                                print 
                    # PC elements next
                    for PCElem in ckt.getPCElements():
                        if PCElem.isEnabled():
                            nCond = PCElem.getNConds()
                            nTerm = PCElem.getNTerms()
                            PCElem.getCurrents(cBuffer)
                            _8 = True
                            j = 0
                            while True:
                                if _8 is True:
                                    _8 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                pw.print_(Utilities.pad(Utilities.fullName(PCElem), cls.maxDeviceNameLength + 2) + j)
                                _9 = True
                                i = 0
                                while True:
                                    if _9 is True:
                                        _9 = False
                                    else:
                                        i += 1
                                    if not (i < cls.Math.min(3, PCElem.getNPhases())):
                                        break
                                    k = ((j - 1) * nCond) + i
                                    # TODO Check zero based indexing
                                    nref = PCElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    Iph[i] = cBuffer[k]
                                    Vph[i] = volts
                                if PCElem.getNPhases() >= 3:
                                    MathUtil.phase2SymComp(Iph, I012)
                                    MathUtil.phase2SymComp(Vph, V012)
                                else:
                                    # Handle single phase and pos seq models
                                    V012[0] = Complex.ZERO
                                    I012[0] = Complex.ZERO
                                    V012[2] = Complex.ZERO
                                    I012[3] = Complex.ZERO
                                    if ckt.isPositiveSequence():
                                        V012[1] = Vph[0]
                                        I012[1] = Iph[0]
                                    else:
                                        V012[1] = Complex.ZERO
                                        I012[2] = Complex.ZERO
                                S = V012[1].multiply(I012[1].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[2].multiply(I012[2].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                S = V012[0].multiply(I012[0].conjugate())
                                if opt == 1:
                                    S = S.multiply(0.001)
                                pw.print_(S.getReal() * 0.003)
                                pw.print_(S.getImaginary() * 0.003)
                                print 
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    print 
                    print 'CIRCUIT ELEMENT POWER FLOW'
                    print 
                    print '(Power Flow into element from indicated Bus)'
                    print 
                    print 'Power Delivery Elements'
                    print 
                    _10 = opt
                    _11 = False
                    while True:
                        if _10 == 1:
                            _11 = True
                            print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase     MW     +j   Mvar         MVA         PF'
                            break
                        if True:
                            _11 = True
                            print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase     kW     +j   kvar         kVA         PF'
                            break
                        break
                    print 
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            pElem.getCurrents(cBuffer)
                            k = -1
                            fromBus = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
                            print 'ELEMENT = ' + Utilities.fullName(pElem)
                            _12 = True
                            j = 0
                            while True:
                                if _12 is True:
                                    _12 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                Saccum = Complex.ZERO
                                _13 = True
                                i = 0
                                while True:
                                    if _13 is True:
                                        _13 = False
                                    else:
                                        i += 1
                                    if not (i < nCond):
                                        break
                                    k += 1
                                    nref = pElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    S = volts.multiply(cBuffer[k].conjugate())
                                    if ckt.isPositiveSequence():
                                        # (pElem.getNPhases() == 1) and
                                        S = S.multiply(3.0)
                                    if opt == 1:
                                        S = S.multiply(0.001)
                                    Saccum = Saccum.add(S)
                                    pw.print_(fromBus + '  ' + Utilities.getNodeNum(pElem.getNodeRef()[k]) + '    ' + (S.getReal() / 1000.0) + ' +j ' + (S.getImaginary() / 1000.0))
                                    print '   ' + (S.abs() / 1000.0) + '     ' + Utilities.powerFactor(S)
                                pw.print_(Utilities.padDots('   TERMINAL TOTAL', cls.maxBusNameLength + 10) + (Saccum.getReal() / 1000.0) + ' +j ' + (Saccum.getImaginary() / 1000.0))
                                print '   ' + (Saccum.abs() / 1000.0) + '     ' + Utilities.powerFactor(Saccum)
                                fromBus = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)
                        print 
                    # PD elements next
                    for pElem in ckt.getPDElements():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            pElem.getCurrents(cBuffer)
                            k = 0
                            fromBus = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
                            print 'ELEMENT = ' + Utilities.fullName(pElem)
                            _14 = True
                            j = 0
                            while True:
                                if _14 is True:
                                    _14 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                Saccum = Complex.ZERO
                                _15 = True
                                i = 0
                                while True:
                                    if _15 is True:
                                        _15 = False
                                    else:
                                        i += 1
                                    if not (i < nCond):
                                        break
                                    k += 1
                                    nref = pElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    S = volts.multiply(cBuffer[k].conjugate())
                                    if ckt.isPositiveSequence():
                                        # (pElem.getNPhases() == 1) and
                                        S = S.multiply(3.0)
                                    if opt == 1:
                                        S = S.multiply(0.001)
                                    Saccum = Saccum.add(S)
                                    pw.print_(fromBus + '  ' + Utilities.getNodeNum(pElem.getNodeRef()[k]) + '    ' + (S.getReal() / 1000.0) + ' +j ' + (S.getImaginary() / 1000.0))
                                    print '   ' + (S.abs() / 1000.0) + '     ' + Utilities.powerFactor(S)
                                pw.print_(Utilities.padDots('   TERMINAL TOTAL', cls.maxBusNameLength + 10) + (Saccum.getReal() / 1000.0) + ' +j ' + (Saccum.getImaginary() / 1000.0))
                                print '   ' + (Saccum.abs() / 1000.0) + '     ' + Utilities.powerFactor(Saccum)
                                fromBus = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)
                        print 
                    print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
                    print 
                    print 'Power Conversion Elements'
                    print 
                    _16 = opt
                    _17 = False
                    while True:
                        if _16 == 1:
                            _17 = True
                            print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase     MW   +j  Mvar         MVA         PF'
                            break
                        if True:
                            _17 = True
                            print Utilities.pad('  Bus', cls.maxBusNameLength) + ' Phase     kW   +j  kvar         kVA         PF'
                            break
                        break
                    print 
                    # PC elements next
                    for pElem in ckt.getPCElements():
                        if pElem.isEnabled():
                            nCond = pElem.getNConds()
                            nTerm = pElem.getNTerms()
                            pElem.getCurrents(cBuffer)
                            k = -1
                            fromBus = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
                            print 'ELEMENT = ' + Utilities.fullName(pElem)
                            _18 = True
                            j = 0
                            while True:
                                if _18 is True:
                                    _18 = False
                                else:
                                    j += 1
                                if not (j < nTerm):
                                    break
                                Saccum = Complex.ZERO
                                _19 = True
                                i = 0
                                while True:
                                    if _19 is True:
                                        _19 = False
                                    else:
                                        i += 1
                                    if not (i < nCond):
                                        break
                                    k += 1
                                    nref = pElem.getNodeRef()[k]
                                    volts = ckt.getSolution().getNodeV()[nref]
                                    S = volts.multiply(cBuffer[k].conjugate())
                                    if ckt.isPositiveSequence():
                                        # (pElem.getNPhases() == 1) and
                                        S = S.multiply(3.0)
                                    if opt == 1:
                                        S = S.multiply(0.001)
                                    Saccum = Saccum.add(S)
                                    pw.print_(fromBus + '  ' + Utilities.getNodeNum(pElem.getNodeRef()[k]) + '    ' + (S.getReal() / 1000.0) + ' +j ' + (S.getImaginary() / 1000.0))
                                    print '   ' + (S.abs() / 1000.0) + '     ' + Utilities.powerFactor(S)
                                pw.print_(Utilities.padDots('   TERMINAL TOTAL', cls.maxBusNameLength + 10) + (Saccum.getReal() / 1000.0) + ' +j ' + (Saccum.getImaginary() / 1000.0))
                                print '   ' + (Saccum.abs() / 1000.0) + '     ' + Utilities.powerFactor(Saccum)
                                fromBus = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)
                        print 
                    break
                break
            print 
            S = ckt.getLosses().multiply(0.001)
            if opt == 1:
                S = S.multiply(0.001)
            print 'Total Circuit Losses = ' + S.getReal() + ' +j ' + S.getImaginary()
            cBuffer = None
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def checkBusReference(cls, cktElem, busReference, terminalIndex):
        """Check all terminals of cktelement to see if bus connected to busreference."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < cktElem.getNTerms()):
                break
            if cktElem.getTerminals()[i].busRef == busReference:
                terminalIndex = i
                return True
        return False

    @classmethod
    def writeTerminalPowerSeq(cls, pw, cktElem, j, opt):
        Vph = [None] * 3
        V012 = [None] * 3
        Iph = [None] * 3
        I012 = [None] * 3
        # Allocate to max total conductors
        ckt = DSSGlobals.activeCircuit
        try:
            cBuffer = [None] * cktElem.getYorder()
            nCond = cktElem.getNConds()
            cktElem.getCurrents(cBuffer)
            pw.print_(Utilities.pad(Utilities.fullName(cktElem), cls.maxDeviceNameLength + 2) + j)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < cls.Math.min(cktElem.getNPhases(), 3)):
                    break
                k = ((j - 1) * nCond) + i
                # TODO Check zero based indexing
                nref = cktElem.getNodeRef()[k]
                volts = ckt.getSolution().getNodeV()[nref]
                Iph[i] = cBuffer[k]
                Vph[i] = volts
            if cktElem.getNPhases() >= 3:
                MathUtil.phase2SymComp(Iph, I012)
                MathUtil.phase2SymComp(Vph, V012)
            else:
                # handle single phase and pos seq models
                V012[0] = Complex.ZERO
                I012[0] = Complex.ZERO
                V012[2] = Complex.ZERO
                I012[2] = Complex.ZERO
                if ckt.isPositiveSequence():
                    V012[1] = Vph[0]
                    I012[1] = Iph[0]
                else:
                    V012[1] = Complex.ZERO
                    I012[1] = Complex.ZERO
            # pos seq or single phase
            _1 = cktElem.getNPhases()
            _2 = False
            while True:
                if _1 == 1:
                    _2 = True
                    S = Vph[0].multiply(Iph[0].conjugate())
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    S = Vph[0].multiply(Iph[0].conjugate()).add(Vph[1].multiply(Iph[2].conjugate()))
                    break
                if True:
                    _2 = True
                    S = V012[1].multiply(I012[1].conjugate())
                    break
                break
            if opt == 1:
                S = S.multiply(0.001)
            pw.print_(S.getReal() * 0.003)
            pw.print_(S.getImaginary() * 0.003)
            S = V012[2].multiply(I012[2].conjugate())
            if opt == 1:
                S = S.multiply(0.001)
            pw.print_(S.getReal() * 0.003)
            pw.print_(S.getImaginary() * 0.003)
            S = V012[0].multiply(I012[0].conjugate())
            if opt == 1:
                S = S.multiply(0.001)
            pw.print_(S.getReal() * 0.003)
            pw.print_(S.getImaginary() * 0.003)
            print 
        finally:
            cBuffer = None
        # Allocate cBuffer big enough for this circuit element

    @classmethod
    def writeTerminalPower(cls, pw, cktElem, jTerm, opt):
        # allocate to max total conductors
        ckt = DSSGlobals.activeCircuit
        try:
            cBuffer = [None] * cktElem.getYorder()
            nCond = cktElem.getNConds()
            cktElem.getCurrents(cBuffer)
            fromBus = Utilities.pad(Utilities.stripExtension(cktElem.getBus(jTerm)), 12)
            print 'ELEMENT = ' + Utilities.pad(Utilities.fullName(cktElem), cls.maxDeviceNameLength + 2)
            Saccum = Complex.ZERO
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nCond):
                    break
                k = ((jTerm - 1) * nCond) + i
                # TODO Check zero based indexing
                nref = cktElem.getNodeRef()[k]
                volts = ckt.getSolution().getNodeV()[nref]
                S = volts.multiply(cBuffer[k].conjugate())
                if ckt.isPositiveSequence():
                    # (CktElem.getNPhases() == 1) and
                    S = S.multiply(3.0)
                if opt == 1:
                    S = S.multiply(0.001)
                Saccum = Saccum.add(S)
                pw.printf('%s %4d %10.5g +j %10.5g    %10.5g    %8.4f', fromBus, Utilities.getNodeNum(cktElem.getNodeRef()[k]), S.getReal() / 1000.0, S.getImaginary() / 1000.0, S.abs() / 1000.0, Utilities.powerFactor(S))
                print 
            pw.printf(' TERMINAL TOTAL   %10.5g +j %10.5g    %10.5g    %8.4f', Saccum.getReal() / 1000.0, Saccum.getImaginary() / 1000.0, Saccum.abs() / 1000.0, Utilities.powerFactor(Saccum))
            print 
        finally:
            cBuffer = None

    @classmethod
    def showBusPowers(cls, fileName, busName, opt, showOptionCode):
        """Report power flow around a specified bus.

        opt = 0: kVA
        opt = 1: MVA
        """
        j = 0
        I0 = 0
        I1 = 0
        I2 = 0
        CMax = 0
        # allocate to max total conductors
        jTerm = 0
        ckt = DSSGlobals.activeCircuit
        cls.setMaxDeviceNameLength()
        # Get bus reference
        busReference = ckt.getBusList().find(busName)
        if busReference == 0:
            DSSGlobals.doSimpleMsg('Bus \"' + busName + '\" not found.', 219)
            return
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            _0 = showOptionCode
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    print 
                    print '  Bus   Mag:    V1 (kV)  p.u.    V2 (kV)  %V2/V1  V0 (kV)  %V0/V1'
                    print 
                    cls.writeSeqVoltages(pw, busReference, False)
                    # Sequence currents
                    print 
                    print 'SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT (first 3 phases)'
                    print 
                    print 'Element                      Term      I1         I2         %I2/I1    I0         %I0/I1   %Normal %Emergency'
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                # Use j set by checkBusReference
                                nCond = pElem.getNConds()
                                nTerm = pElem.getNTerms()
                                pElem.getCurrents(cBuffer)
                                _2 = True
                                j = 0
                                while True:
                                    if _2 is True:
                                        _2 = False
                                    else:
                                        j += 1
                                    if not (j < nTerm):
                                        break
                                    cls.getI0I1I2(I0, I1, I2, CMax, pElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                    cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(pElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, pElem.getDSSObjType())
                    # PD elements next
                    for PDElem in ckt.getPDElements():
                        if PDElem.isEnabled():
                            if cls.checkBusReference(PDElem, busReference, j):
                                # Is this connected to the bus
                                # Use j set by CheckBusReference
                                nCond = PDElem.getNConds()
                                nTerm = PDElem.getNTerms()
                                PDElem.getCurrents(cBuffer)
                                _3 = True
                                j = 0
                                while True:
                                    if _3 is True:
                                        _3 = False
                                    else:
                                        j += 1
                                    if not (j < nTerm):
                                        break
                                    cls.getI0I1I2(I0, I1, I2, CMax, PDElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                    cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(PDElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, PDElem.getDSSObjType())
                    # PC elements next
                    for PCElem in ckt.getPCElements():
                        if PCElem.isEnabled():
                            if cls.checkBusReference(PCElem, busReference, j):
                                nCond = PCElem.getNConds()
                                nTerm = PCElem.getNTerms()
                                PCElem.getCurrents(cBuffer)
                                _4 = True
                                j = 0
                                while True:
                                    if _4 is True:
                                        _4 = False
                                    else:
                                        j += 1
                                    if not (j < nTerm):
                                        break
                                    cls.getI0I1I2(I0, I1, I2, CMax, PCElem.getNPhases(), (j - 1) * nCond, cBuffer)
                                    cls.writeSeqCurrents(pw, Utilities.padDots(Utilities.fullName(PCElem), cls.maxDeviceNameLength + 2), I0, I1, I2, CMax, 0.0, 0.0, j, PCElem.getDSSObjType())
                    # Sequence powers
                    print 
                    print 'SYMMETRICAL COMPONENT POWERS BY CIRCUIT ELEMENT (first 3 phases)'
                    print 
                    _5 = opt
                    _6 = False
                    while True:
                        if _5 == 1:
                            _6 = True
                            print 'Element                      Term    P1(MW)   Q1(Mvar)       P2         Q2      P0      Q0   '
                            break
                        if True:
                            _6 = True
                            print 'Element                      Term    P1(kW)   Q1(kvar)         P2         Q2      P0      Q0  '
                            break
                        break
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                # Use j set by checkBusReference
                                cls.writeTerminalPowerSeq(pw, pElem, j, opt)
                    # PD elements next
                    for PDElem in ckt.getPDElements():
                        if PDElem.isEnabled():
                            if cls.checkBusReference(PDElem, busReference, j):
                                # Is this connected to the bus
                                cls.writeTerminalPowerSeq(pw, PDElem, j, opt)
                    # PC elements next
                    for PCElem in ckt.getPCElements():
                        if PCElem.isEnabled():
                            if cls.checkBusReference(PCElem, busReference, j):
                                cls.writeTerminalPowerSeq(pw, PCElem, j, opt)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    print 
                    print '  Bus   (node ref)  Node       V (kV)    Angle    p.u.   Base kV'
                    print 
                    cls.writeBusVoltages(pw, busReference, False)
                    # Element currents
                    print 
                    print 'CIRCUIT ELEMENT CURRENTS'
                    print 
                    print '(Currents into element from indicated bus)'
                    print 
                    print 'Power Delivery Elements'
                    print 
                    print '  Bus         Phase    Magnitude, A     Angle'
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                cls.writeTerminalCurrents(pw, pElem, False)
                                print 
                    # PD elements first
                    for pElem in ckt.getPDElements():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                cls.writeTerminalCurrents(pw, pElem, True)
                                print 
                    print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
                    print 
                    print 'Power Conversion Elements'
                    print 
                    print '  Bus         Phase    Magnitude, A     Angle'
                    print 
                    # PC elements next
                    for pElem in ckt.getPCElements():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                cls.writeTerminalCurrents(pw, pElem, False)
                                print 
                    # Branch powers
                    print 
                    print 'CIRCUIT ELEMENT POWER FLOW'
                    print 
                    print '(Power Flow into element from indicated Bus)'
                    print 
                    _7 = opt
                    _8 = False
                    while True:
                        if _7 == 1:
                            _8 = True
                            print '  Bus       Phase     MW     +j   Mvar           MVA           PF'
                            break
                        if True:
                            _8 = True
                            print '  Bus       Phase     kW     +j   kvar           kVA           PF'
                            break
                        break
                    print 
                    # sources first
                    for pElem in ckt.getSources():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, j):
                                cls.writeTerminalPower(pw, pElem, j, opt)
                                print 
                    # PD elements first
                    for pElem in ckt.getPDElements():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, jTerm):
                                cls.writeTerminalPower(pw, pElem, jTerm, opt)
                                # Get the other bus for the report
                                if jTerm == 1:
                                    jTerm = 2
                                else:
                                    jTerm = 1
                                    # may sometimes give wrong terminal if more than 2 terminals
                                cls.writeTerminalPower(pw, pElem, jTerm, opt)
                                print 
                    print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
                    print 
                    print 'Power Conversion Elements'
                    print 
                    _9 = opt
                    _10 = False
                    while True:
                        if _9 == 1:
                            _10 = True
                            print '  Bus         Phase     MW   +j  Mvar         MVA         PF'
                        if True:
                            _10 = True
                            print '  Bus         Phase     kW   +j  kvar         kVA         PF'
                        break
                    print 
                    # PC elements next
                    for pElem in ckt.getPCElements():
                        if pElem.isEnabled():
                            if cls.checkBusReference(pElem, busReference, jTerm):
                                cls.writeTerminalPower(pw, pElem, jTerm, opt)
                                print 
                    break
                break
            cBuffer = None
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showFaultStudy(cls, fileName):
        # Big temp array
        cls.setMaxBusNameLength()
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            ckt = DSSGlobals.activeCircuit
            sol = ckt.getSolution()
            # Set source voltage injection currents
            # All phase faults
            print 'FAULT STUDY REPORT'
            print 
            print 'ALL-Node Fault Currents'
            print 
            print Utilities.pad('Bus', cls.maxBusNameLength) + '       Node 1  X/R        Node 2  X/R        Node 3  X/R   ...  (Amps)'
            print 
            _0 = True
            iBus = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    iBus += 1
                if not (iBus < ckt.getNumBuses()):
                    break
                # Bus Norton equivalent current, Isc has been previously computed
                bus = ckt.getBuses()[iBus]
                pw.print_(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(iBus)) + ',', cls.maxBusNameLength + 2))
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < bus.getNumNodesThisBus()):
                        break
                    currMag = bus.getBusCurrent()[i].abs()
                    if i > 1:
                        pw.print_(', ')
                    pw.print_(currMag)
                    if currMag > 0.0:
                        pw.print_(', ' + MathUtil.getXR(bus.getVBus()[i].divide(bus.getBusCurrent()[i])))
                    else:
                        pw.print_(',   N/A')
                print 
            print 
            # One phase faults
            print 
            print 'ONE-Node to ground Faults'
            print 
            print '                                      pu Node Voltages (L-N Volts if no base)'
            print Utilities.pad('Bus', cls.maxBusNameLength) + '   Node      Amps         Node 1     Node 2     Node 3    ...'
            print 
            # Solve for fault injection currents
            _2 = True
            iBus = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    iBus += 1
                if not (iBus < ckt.getNumBuses()):
                    break
                # Bus Norton equivalent current, Isc has been previously computed
                bus = ckt.getBuses()[iBus]
                ZFault = CMatrixImpl(bus.getNumNodesThisBus())
                ZFault.copyFrom(bus.getZsc())
                _3 = True
                iphs = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        iphs += 1
                    if not (iphs < bus.getNumNodesThisBus()):
                        break
                    IFault = bus.getVBus()[iphs].divide(bus.getZsc().getElement(iphs, iphs))
                    pw.print_(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(iBus)), cls.maxBusNameLength + 2) + iphs + IFault.abs() + '   ')
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < bus.getNumNodesThisBus()):
                            break
                        Vphs = bus.getVBus()[i].subtract(bus.getZsc().getElement(i, iphs).multiply(IFault)).abs()
                        if bus.getKVBase() > 0.0:
                            Vphs = (0.001 * Vphs) / bus.getKVBase()
                            pw.print_(' ' + Vphs)
                        else:
                            pw.print_(' ' + Vphs)
                    print 
                # Now, put it in the Css array where it belongs
                ZFault = None
            # Node-node faults
            print 
            print 'Adjacent Node-Node Faults'
            print 
            print '                                        pu Node Voltages (L-N Volts if no base)'
            print 'Bus          Node-Node      Amps        Node 1     Node 2     Node 3    ...'
            print 
            # Solve for fault injection currents
            _5 = True
            iBus = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    iBus += 1
                if not (iBus < ckt.getNumBuses()):
                    break
                # Bus Norton equivalent current, Isc has been previously computed
                bus = ckt.getBuses()[iBus]
                YFault = CMatrixImpl(bus.getNumNodesThisBus())
                VFault = [None] * bus.getNumNodesThisBus()
                GFault = Complex(10000.0, 0.0)
                _6 = True
                iphs = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        iphs += 1
                    if not (iphs < bus.getNumNodesThisBus()):
                        break
                    YFault.copyFrom(bus.getYsc())
                    YFault.addElement(iphs, iphs, GFault)
                    YFault.addElement(iphs + 1, iphs + 1, GFault)
                    YFault.addElemSym(iphs, iphs + 1, GFault.negate())
                    # Solve for injection currents
                    YFault.invert()
                    YFault.vMult(VFault, bus.getBusCurrent())
                    # Gets voltage appearing at fault
                    pw.print_(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(iBus)), cls.maxBusNameLength + 2) + iphs + iphs + 1 + VFault[iphs].subtract(VFault[iphs + 1]).multiply(GFault).abs() + '   ')
                    _7 = True
                    i = 0
                    while True:
                        if _7 is True:
                            _7 = False
                        else:
                            i += 1
                        if not (i < bus.getNumNodesThisBus()):
                            break
                        Vphs = VFault[i].abs()
                        if bus.getKVBase() > 0.0:
                            Vphs = (0.001 * Vphs) / bus.getKVBase()
                            pw.print_(' ' + Vphs)
                        else:
                            pw.print_(' ' + Vphs)
                    print 
            # Now, put it in the Css array where it belongs
            VFault = None
            YFault = None
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def writeElementRecord(cls, pw, pElem):
        nTerm = pElem.getNTerms()
        busName = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), cls.maxBusNameLength)
        pw.print_(Utilities.pad(Utilities.fullName(pElem), cls.maxDeviceNameLength + 2) + ' ')
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < nTerm):
                break
            pw.print_(busName + ' ')
            busName = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), cls.maxBusNameLength)
        print 

    @classmethod
    def showElements(cls, fileName, className):
        """Show elements and bus connections."""
        ckt = DSSGlobals.activeCircuit
        cls.setMaxBusNameLength()
        cls.setMaxDeviceNameLength()
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            disabledFileName = Utilities.stripExtension(fileName) + '_Disabled.txt'
            fwDisabled = FileWriter(disabledFileName)
            pwDisabled = PrintWriter(fw)
            if len(className) > 0:
                # Just give a list of active elements of a particular class
                if DSSClassDefs.setObjectClass(className):
                    print 'All Elements in Class \"' + className + '\"'
                    print 
                    print 'All DISABLED Elements in Class \"' + className + '\"'
                    print 
                    DSSGlobals.activeDSSClass = DSSGlobals.DSSClassList.get(DSSGlobals.lastClassReferenced)
                    _0 = True
                    i = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            i += 1
                        if not (i < DSSGlobals.activeDSSClass.getElementCount()):
                            break
                        DSSGlobals.activeDSSClass.setActiveElement(i)
                        if (
                            DSSGlobals.activeDSSClass.getDSSClassType() & DSSClassDefs.BASECLASSMASK > 0
                        ):
                            if DSSGlobals.activeDSSObject.isEnabled():
                                print DSSGlobals.activeDSSObject.getName()
                            else:
                                print DSSGlobals.activeDSSObject.getName()
                        else:
                            print DSSGlobals.activeDSSObject.getName()
                            # non cktelements
            else:
                # Default - just do PD and PC Element in active circuit
                print 
                print 'Elements in Active Circuit: ' + ckt.getName()
                print 
                print 'Power Delivery Elements'
                print 
                print Utilities.pad('Element', cls.maxDeviceNameLength + 2) + Utilities.pad(' Bus1', cls.maxBusNameLength) + Utilities.pad(' Bus2', cls.maxBusNameLength) + Utilities.pad(' Bus3', cls.maxBusNameLength) + ' ...'
                print 
                print 
                print 'DISABLED Elements in Active Circuit: ' + ckt.getName()
                print 
                print 'DISABLED Power Delivery Elements'
                print 
                print Utilities.pad('DISABLED Element', cls.maxDeviceNameLength + 2) + Utilities.pad(' Bus1', cls.maxBusNameLength) + Utilities.pad(' Bus2', cls.maxBusNameLength) + Utilities.pad(' Bus3', cls.maxBusNameLength) + ' ...'
                print 
                # PD elements first
                for pElem in ckt.getPDElements():
                    if pElem.isEnabled():
                        cls.writeElementRecord(pw, pElem)
                    else:
                        cls.writeElementRecord(pwDisabled, pElem)
                print 
                print 'Power Conversion Elements'
                print 
                print Utilities.pad('Element', cls.maxDeviceNameLength + 2) + Utilities.pad(' Bus1', cls.maxBusNameLength) + Utilities.pad(' Bus2', cls.maxBusNameLength) + Utilities.pad(' Bus3', cls.maxBusNameLength) + ' ...'
                print 
                print 
                print 'DISABLED Power Conversion Elements'
                print 
                print Utilities.pad('DISABLED Element', cls.maxDeviceNameLength + 2) + Utilities.pad(' Bus1', cls.maxBusNameLength) + Utilities.pad(' Bus2', cls.maxBusNameLength) + Utilities.pad(' Bus3', cls.maxBusNameLength) + ' ...'
                print 
                # PC elements next
                for pElem in ckt.getPCElements():
                    if pElem.isEnabled():
                        cls.writeElementRecord(pw, pElem)
                    else:
                        cls.writeElementRecord(pwDisabled, pElem)
            pw.close()
            fw.close()
            pwDisabled.close()
            fwDisabled.close()
            Utilities.fireOffEditor(disabledFileName)
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showBuses(cls, fileName):
        """Show bus names and nodes in uses."""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            cls.setMaxBusNameLength()
            cls.maxBusNameLength += 2
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'BUSES AND NODES IN ACTIVE CIRCUIT: ' + ckt.getName()
            print 
            print Utilities.pad('     ', cls.maxBusNameLength) + '                         Coord                        Number of     Nodes '
            print Utilities.pad('  Bus', cls.maxBusNameLength) + '    Base kV             (x, y)            Keep?       Nodes      connected ...'
            print 
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                pw.print_(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(i)), cls.maxBusNameLength) + ' ')
                pBus = ckt.getBuses()[i]
                if pBus.getKVBase() > 0.0:
                    pw.print_(pBus.getKVBase() * DSSGlobals.SQRT3)
                else:
                    pw.print_('   NA ')
                pw.print_('          (')
                if pBus.isCoordDefined():
                    pw.printf(' %-13.11g, %-13.11g)', pBus.getX(), pBus.getY())
                else:
                    pw.print_('           NA,            NA )')
                if pBus.isKeep():
                    pw.print_('     Yes  ')
                else:
                    pw.print_('     No  ')
                pw.print_('     ')
                pw.print_(pBus.getNumNodesThisBus())
                pw.print_('       ')
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < pBus.getNumNodesThisBus()):
                        break
                    pw.print_(pBus.getNum(j) + ' ')
                print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showMeters(cls, fileName):
        """Show values of meter elements."""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'ENERGY METER VALUES'
            print 
            print 'Registers:'
            meterClass = DSSClassDefs.getDSSClass('Energymeter')
            if meterClass is None:
                return
            if meterClass.getElementCount() == 0:
                print 'No Energymeter Elements Defined.'
            else:
                pMeter = ckt.getEnergyMeters().get(0)
                # write registernames for first meter only
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    print 'Reg ' + String.valueOf.valueOf(i) + ' = ' + pMeter.getRegisterNames()[i]
                print 
                if pMeter is not None:
                    pw.print_('Meter        ')
                    _1 = True
                    i = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            i += 1
                        if not (i < EnergyMeter.NUM_EM_REGISTERS):
                            break
                        pw.print_(Utilities.pad('   Reg ' + String.valueOf.valueOf(i), 11))
                    print 
                    print 
                for pElem in ckt.getEnergyMeters():
                    if pElem is not None:
                        if pElem.isEnabled():
                            pw.print_(Utilities.pad(pElem.getName(), 12))
                            _2 = True
                            j = 0
                            while True:
                                if _2 is True:
                                    _2 = False
                                else:
                                    j += 1
                                if not (j < EnergyMeter.NUM_EM_REGISTERS):
                                    break
                                pw.print_(pElem.getRegisters()[j] + ' ')
                    print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showGenMeters(cls, fileName):
        """Show values of generator meter elements"""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'GENERATOR ENERGY METER VALUES'
            print 
            pGen = ckt.getGenerators().get(0)
            if pGen is not None:
                generatorClass = pGen.getParentClass()
                pw.print_('Generator          ')
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < Generator.NumGenRegisters):
                        break
                    pw.print_(Utilities.pad(generatorClass.getRegisterNames()[i], 11))
                print 
                print 
            for pElem in ckt.getGenerators():
                if pElem is not None:
                    if pElem.isEnabled():
                        pw.print_(Utilities.pad(pElem.getName(), 12))
                        _1 = True
                        j = 0
                        while True:
                            if _1 is True:
                                _1 = False
                            else:
                                j += 1
                            if not (j < Generator.NumGenRegisters):
                                break
                            pw.print_(pElem.getRegisters()[j] + ' ')
                print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def tapPosition(cls, xfmr, iWind):
        """Assumes 0 is 1.0 per unit tap."""
        return cls.Math.round((xfmr.getPresentTap(iWind) - 1.0) / xfmr.getTapIncrement(iWind))

    @classmethod
    def showRegulatorTaps(cls, fileName):
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'CONTROLLED TRANSFORMER TAP SETTINGS'
            print 
            print 'Name            Tap      Min       Max     Step  Position'
            print 
            ckt = DSSGlobals.activeCircuit
            for pReg in ckt.getRegControls():
                t = pReg.getTransformer()
                iWind = pReg.getWinding()
                pw.print_(Utilities.pad(t.getName(), 12) + ' ')
                pw.printf('%8.5f %8.5f %8.5f %8.5f     %d', t.getPresentTap(iWind), t.getMinTap(iWind), t.getMaxTap(iWind), t.getTapIncrement(iWind), cls.tapPosition(pReg.getTransformer(), iWind))
                print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showMeterZone(cls, fileName):
        parser = Parser.getInstance()
        try:
            fileName = Utilities.stripExtension(fileName)
            paramName = parser.getNextParam()
            param = parser.makeString()
            fileName = fileName + '_' + param + '.txt'
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            DSSGlobals.globalResult = fileName
            pMtrClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('energymeter'))
            if len(param) > 0:
                pMtr = pMtrClass.find(param)
                # FIXME make generic
                if pMtr is None:
                    DSSGlobals.doSimpleMsg('EnergyMeter \"' + param + '\" not found.', 220)
                elif pMtr.getBranchList() is not None:
                    print 'Branches and Load in Zone for EnergyMeter ' + param
                    print 
                    PDElem = pMtr.getBranchList().getFirst()
                    while PDElem is not None:
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < pMtr.getBranchList().getLevel()):
                                break
                            pw.print_(cls.TABCHAR)
                        # F.print(pMtr.getBranchList().getLevel() +" ");
                        pw.print_(PDElem.getParentClass().getName() + '.' + PDElem.getName())
                        pb = pMtr.getBranchList().getPresentBranch()
                        if pb.isParallel():
                            pw.print_('(PARALLEL:' + pb.getLoopLineObj().getName() + ')')
                        if pb.isLoopedHere():
                            pw.print_('(LOOP:' + pb.getLoopLineObj().getParentClass().getName() + '.' + pb.getLoopLineObj().getName() + ')')
                        if PDElem.getSensorObj() is not None:
                            pw.printf(' (Sensor: %s.%s) ', PDElem.getSensorObj().getParentClass().getName(), PDElem.getSensorObj().getName())
                        else:
                            pw.print_(' (Sensor: NIL)')
                        print 
                        loadElem = pMtr.getBranchList().getFirstObject()
                        while loadElem is not None:
                            _1 = True
                            i = 0
                            while True:
                                if _1 is True:
                                    _1 = False
                                else:
                                    i += 1
                                if not (i < pMtr.getBranchList().getLevel() + 1):
                                    break
                                pw.print_(cls.TABCHAR)
                            pw.print_(loadElem.getParentClass().getName() + '.' + loadElem.getName())
                            if loadElem.getSensorObj() is not None:
                                pw.printf(' (Sensor: %s.%s) ', loadElem.getSensorObj().getParentClass().getName(), loadElem.getSensorObj().getName())
                            else:
                                pw.print_(' (Sensor: NIL)')
                            print 
                            loadElem = pMtr.getBranchList().getNextObject()
                        PDElem = pMtr.getBranchList().goForward()
            else:
                DSSGlobals.doSimpleMsg('Meter name not specified.' + DSSGlobals.CRLF + parser.getCmdString(), 221)
            pw.close()
            fw.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]
        finally:
            paramName = parser.getNextParam()
            param = parser.makeString()
            _2 = len(param)
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    Utilities.fireOffEditor(fileName)
                    break
                if True:
                    _3 = True
                    DSSGlobals.DSSForms.showTreeView(fileName)
                    break
                break
        # TODO: handle exception

    @classmethod
    def showOverloads(cls, fileName):
        # allocate to max total conductors
        Iph = [None] * 3
        I012 = [None] * 3
        ckt = DSSGlobals.activeCircuit
        cls.setMaxDeviceNameLength()
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # Allocate cBuffer big enough for largest circuit element
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            # Sequence currents
            print 
            print 'Power Delivery Element Overload Report'
            print 
            print 'SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT '
            print 
            print 'Element                      Term    I1      I2    %I2/I1    I0    %I0/I1 %Normal   %Emergency'
            print 
            # PD elements
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    if DSSClassDefs.CLASSMASK & PDElem.getDSSObjType() != DSSClassDefs.CAP_ELEMENT:
                        # ignore capacitors
                        NCond = PDElem.getNConds()
                        PDElem.getCurrents(cBuffer)
                        _0 = True
                        j = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                j += 1
                            if not (j < 1):
                                break
                            # check only terminal 1 for overloads
                            if PDElem.getNPhases() >= 3:
                                Cmax = 0.0
                                _1 = True
                                i = 0
                                while True:
                                    if _1 is True:
                                        _1 = False
                                    else:
                                        i += 1
                                    if not (i < 3):
                                        break
                                    k = ((j - 1) * NCond) + i
                                    Iph[i] = cBuffer[k]
                                    Cmag = Iph[i].abs()
                                    if Cmag > Cmax:
                                        Cmax = Cmag
                                MathUtil.phase2SymComp(Iph, I012)
                                I0 = I012[0].abs()
                                I1 = I012[1].abs()
                                I2 = I012[2].abs()
                            else:
                                I0 = 0.0
                                I1 = cBuffer[1 + ((j - 1) * NCond)].abs()
                                I2 = 0.0
                                Cmax = I1
                            if (PDElem.getNormAmps() > 0.0) or (PDElem.getEmergAmps() > 0.0):
                                if (Cmax > PDElem.getNormAmps()) or (Cmax > PDElem.getEmergAmps()):
                                    pw.print_(Utilities.pad(Utilities.fullName(PDElem), cls.maxDeviceNameLength + 2) + j)
                                    pw.print_(I1)
                                    pw.print_(I2)
                                    if I1 > 0.0:
                                        pw.print_((100.0 * I2) / I1)
                                    else:
                                        pw.print_('     0.0')
                                    pw.print_(I0)
                                    if I1 > 0.0:
                                        pw.print_((100.0 * I0) / I1)
                                    else:
                                        pw.print_('     0.0')
                                    if PDElem.getNormAmps() > 0.0:
                                        pw.print_((Cmax / PDElem.getNormAmps()) * 100.0)
                                    else:
                                        pw.print_('     0.0')
                                    if PDElem.getEmergAmps() > 0.0:
                                        pw.print_((Cmax / PDElem.getEmergAmps()) * 100.0)
                                    else:
                                        pw.print_('     0.0')
                                    print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showUnserved(cls, fileName, UE_Only):
        # LoadObj PLoad;
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'UNSERVED  LOAD  REPORT'
            print 
            print 'Load Element        Bus        Load kW  EEN Factor  UE Factor'
            print 
            # load
            for pLoad in ckt.getLoads():
                if pLoad.isEnabled():
                    doIt = False
                    if UE_Only:
                        if pLoad.getUnserved():
                            doIt = True
                    elif pLoad.getExceedsNormal():
                        doIt = True
                    if doIt:
                        pw.print_(Utilities.pad(pLoad.getName(), 20))
                        pw.print_(Utilities.pad(pLoad.getBus(0), 10))
                        pw.print_(pLoad.getKWBase())
                        pw.print_(pLoad.getEEN_Factor())
                        pw.print_(pLoad.getUE_Factor())
                        print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showLosses(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        cls.setMaxDeviceNameLength()
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # Sequence currents
            print 
            print 'LOSSES REPORT'
            print 
            print 'Power Delivery Element Loss Report'
            print 
            print 'Element                  kW Losses    % of Power   kvar Losses'
            print 
            totalLosses = Complex.ZERO
            lineLosses = Complex.ZERO
            transLosses = Complex.ZERO
            # PD elements
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    # if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) != DSSClassDefs.CAP_ELEMENT) {
                    # Ignore capacitors
                    kLosses = PDElem.getLosses().multiply(0.001)
                    # kW Losses in element
                    totalLosses = totalLosses.add(kLosses)
                    termPower = PDElem.getPower(1).multiply(0.001)
                    # terminal 1 power  TODO Check zero based indexing
                    if (
                        DSSClassDefs.CLASSMASK & PDElem.getDSSObjType() == DSSClassDefs.XFMR_ELEMENT
                    ):
                        transLosses = transLosses.add(kLosses)
                    if (
                        DSSClassDefs.CLASSMASK & PDElem.getDSSObjType() == DSSClassDefs.LINE_ELEMENT
                    ):
                        lineLosses = lineLosses.add(kLosses)
                    pw.print_(Utilities.pad(Utilities.fullName(PDElem), cls.maxDeviceNameLength + 2))
                    pw.printf('%10.5f, ', kLosses.getReal())
                    if termPower.getReal() > 0.0 and kLosses.getReal() > 0.0009:
                        pw.print_((kLosses.getReal() / cls.Math.abs(termPower.getReal())) * 100.0)
                    else:
                        pw.print_(Complex.ZERO.getReal())
                    pw.printf('     %.6g', kLosses.getImaginary())
                    print 
            print 
            print Utilities.pad('LINE LOSSES=', 30) + lineLosses.getReal() + ' kW'
            print Utilities.pad('TRANSFORMER LOSSES=', 30) + transLosses.getReal() + ' kW'
            print 
            print Utilities.pad('TOTAL LOSSES=', 30) + totalLosses.getReal() + ' kW'
            loadPower = Complex.ZERO
            # sum the total load kW being served in the circuit model
            for PCElem in ckt.getLoads():
                if PCElem.isEnabled():
                    loadPower = loadPower.add(PCElem.getPower(1))
            loadPower = loadPower.multiply(0.001)
            print 
            print Utilities.pad('TOTAL LOAD POWER = ', 30) + cls.Math.abs(loadPower.getReal()) + ' kW'
            pw.print_(Utilities.pad('Percent Losses for Circuit = ', 30))
            if loadPower.getReal() != 0.0:
                print (cls.Math.abs(totalLosses.getReal() / loadPower.getReal()) * 100.0) + ' %'
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showVariables(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # Sequence currents
            print 
            print 'VARIABLES REPORT'
            print 
            print 'Present values of all variables in PC Elements in the circuit.'
            print 
            for PCElem in ckt.getPCElements():
                if PCElem.isEnabled() and PCElem.numVariables() > 0:
                    print 'ELEMENT: ' + PCElem.getParentClass().getName() + '.' + PCElem.getName()
                    print 'No. of variables: ' + PCElem.numVariables()
                    _0 = True
                    i = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            i += 1
                        if not (i < PCElem.numVariables()):
                            break
                        print '  ' + PCElem.variableName(i) + ' = ' + String.format.format('%-.6g', PCElem.getVariable(i))
                    print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showIsolated(cls, fileName):
        """Show isolated buses/branches in present circuit."""
        # all circuit elements
        ckt = DSSGlobals.activeCircuit
        # make sure bus list is built
        if ckt.isBusNameRedefined():
            ckt.reProcessBusDefs()
            # Initialize all circuit elements to not checked
        for TestElement in ckt.getCktElements():
            TestElement.setChecked(False)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < TestElement.getNTerms()):
                    break
                TestElement.getTerminals()[i].setChecked(False)
        # initialize the checked flag for all buses
        _1 = True
        j = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                j += 1
            if not (j < ckt.getNumBuses()):
                break
            ckt.getBuses()[j].setBusChecked(False)
        # get started at main voltage source
        testElem = ckt.getSources().get(0)
        branchList = CktTreeImpl.getIsolatedSubArea(testElem)
        # Show report of elements connected and not connected
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 
            print 'ISOLATED CIRCUIT ELEMENT REPORT'
            print 
            print 
            print '***  THE FOLLOWING BUSES HAVE NO CONNECTION TO THE SOURCE ***'
            print 
            _2 = True
            j = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    j += 1
                if not (j < ckt.getNumBuses()):
                    break
                if not ckt.getBuses()[j].isBusChecked():
                    print Utilities.encloseQuotes(ckt.getBusList().get(j))
            print 
            print '***********  THE FOLLOWING SUB NETWORKS ARE ISOLATED ************'
            print 
            for TestElement in ckt.getCktElements():
                if TestElement.isEnabled():
                    if not TestElement.isChecked():
                        if (
                            TestElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK == DSSClassDefs.PD_ELEMENT
                        ):
                            subArea = CktTreeImpl.getIsolatedSubArea(TestElement)
                            print '*** START SUBAREA ***'
                            testBranch = subArea.getFirst()
                            # TODO Implement
                            while testBranch is not None:
                                print '(' + subArea.getLevel() + ') ' + testBranch.getParentClass().getName() + '.' + testBranch.getName()
                                pElem = subArea.getFirstObject()
                                while pElem is not None:
                                    print '[SHUNT], ' + pElem.getParentClass().getName() + '.' + pElem.getName()
                                    pElem = subArea.getNextObject()
                                testBranch = subArea.goForward()
                            subArea = None
                            print 
            print 
            print '***********  THE FOLLOWING ENABLED ELEMENTS ARE ISOLATED ************'
            print 
            # Mark all controls, energy meters and monitors as checked so they don"t show up
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < len(ckt.getDSSControls())):
                    break
                ckt.getDSSControls().get(i).setChecked(True)
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < len(ckt.getMeterElements())):
                    break
                ckt.getMeterElements().get(i).setChecked(True)
            for TestElement in ckt.getCktElements():
                if TestElement.isEnabled():
                    if not TestElement.isChecked():
                        pw.print_('\"' + TestElement.getParentClass().getName() + '.' + TestElement.getName() + '\"')
                        pw.print_('  Buses:')
                        _5 = True
                        j = 0
                        while True:
                            if _5 is True:
                                _5 = False
                            else:
                                j += 1
                            if not (j < TestElement.getNTerms()):
                                break
                            pw.print_('  \"' + TestElement.getBus(j) + '\"')
                        print 
            print 
            print '***  THE FOLLOWING BUSES ARE NOT CONNECTED TO ANY POWER DELIVERY ELEMENT ***'
            print 
            _6 = True
            j = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    j += 1
                if not (j < ckt.getNumBuses()):
                    break
                if not ckt.getBuses()[j].isBusChecked():
                    print Utilities.encloseQuotes(ckt.getBusList().get(j))
            print 
            print '***********  CONNECTED CIRCUIT ELEMENT TREE ************'
            print 
            print '(Lexical Level) Element name'
            print 
            testBranch = branchList.getFirst()
            # FIXME Make generic
            while testBranch is not None:
                print '(' + branchList.getLevel() + ') ' + testBranch.getParentClass().getName() + '.' + testBranch.getName()
                testElem = branchList.getFirstObject()
                while testElem is not None:
                    print '[SHUNT], ' + testElem.getParentClass().getName() + '.' + testElem.getName()
                    testElem = branchList.getNextObject()
                testBranch = branchList.goForward()
            branchList = None
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showRatings(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 'Power Delivery Elements Normal and Emergency (max) Ratings'
            print 
            for PDElem in ckt.getPDElements():
                pw.print_('\"' + PDElem.getParentClass().getName() + '.' + PDElem.getName() + '\" normamps=')
                pw.printf('%-.4g,  %-.4g  !Amps', PDElem.getNormAmps(), PDElem.getEmergAmps())
                print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showLoops(cls, fileName):
        """Show loops and paralleled branches in meter zones."""
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 'Loops and Paralleled Lines in all EnergyMeter Zones'
            print 
            hMeter = DSSGlobals.energyMeterClass.getFirst()
            while hMeter > 0:
                pMtr = DSSGlobals.activeDSSObject
                if pMtr.getBranchList() is not None:
                    PDElem = pMtr.getBranchList().getFirst()
                    while PDElem is not None:
                        pb = pMtr.getBranchList().getPresentBranch()
                        if pb.isParallel():
                            print '(' + pMtr.getName() + ') ' + PDElem.getParentClass().getName() + '.' + PDElem.getName() + ': PARALLEL WITH ' + pb.getLoopLineObj().getParentClass().getName() + '.' + pb.getLoopLineObj().getName()
                        if pb.isLoopedHere():
                            print '(' + pMtr.getName() + ') ' + PDElem.getParentClass().getName() + '.' + PDElem.getName() + ': LOOPED TO     ' + pb.getLoopLineObj().getParentClass().getName() + '.' + pb.getLoopLineObj().getName()
                        PDElem = pMtr.getBranchList().goForward()
                hMeter = DSSGlobals.energyMeterClass.getNext()
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def topoLevelTabs(cls, pw, nLevel):
        nTabs = 30
        if nLevel < nTabs:
            nTabs = nLevel
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < nTabs):
                break
            pw.print_(cls.TABCHAR)
        if nLevel > nTabs:
            pw.printf('(* %d *)', nLevel)

    @classmethod
    def showTopology(cls, fileRoot):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fileName = fileRoot + 'TopoSumm.txt'
            treeName = fileRoot + 'TopoTree.txt'
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 'Topology analysis for switch control algorithms'
            print 
            fwTree = FileWriter(treeName)
            pwTree = PrintWriter(fwTree)
            print 'Branches and Loads in Circuit ' + ckt.getName()
            print 
            topo = ckt.getTopology()
            nLoops = 0
            nParallel = 0
            nLevels = 0
            nIsolated = 0
            nSwitches = 0
            if topo is not None:
                PDElem = topo.getFirst()
                if topo.getLevel() > nLevels:
                    nLevels = topo.getLevel()
                cls.topoLevelTabs(pwTree, topo.getLevel())
                pwTree.print_(PDElem.getParentClass().getName() + '.' + PDElem.getName())
                pb = topo.getPresentBranch()
                if pb.isParallel():
                    nParallel += 1
                    pwTree.print_('(PARALLEL:' + pb.getLoopLineObj().getName() + ')')
                if pb.isLoopedHere():
                    nLoops += 1
                    pwTree.print_('(LOOP:' + pb.getLoopLineObj().getParentClass().getName() + '.' + pb.getLoopLineObj().getName() + ')')
                if PDElem.hasSensorObj():
                    pwTree.printf(' (Sensor: %s.%s) ', PDElem.getSensorObj().getParentClass().getName(), PDElem.getSensorObj().getName())
                if PDElem.hasControl():
                    pwTree.printf(' (Control: %s.%s) ', PDElem.getControlElement().getParentClass().getName(), PDElem.getControlElement().getName())
                    if (
                        PDElem.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.SWT_CONTROL
                    ):
                        nSwitches += 1
                if PDElem.hasEnergyMeter():
                    pwTree.printf(' (Meter: %s) ', PDElem.getMeterObj().getName())
                print 
                loadElem = topo.getFirstObject()
                while loadElem is not None:
                    cls.topoLevelTabs(pwTree, topo.getLevel() + 1)
                    pwTree.print_(loadElem.getParentClass().getName() + '.' + loadElem.getName())
                    if loadElem.hasSensorObj():
                        pwTree.printf(' (Sensor: %s.%s) ', loadElem.getSensorObj().getParentClass().getName(), loadElem.getSensorObj().getName())
                    if loadElem.hasControl():
                        pwTree.printf(' (Control: %s.%s) ', loadElem.getControlElement().getParentClass().getName(), loadElem.getControlElement().getName())
                        if (
                            loadElem.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.SWT_CONTROL
                        ):
                            nSwitches += 1
                    if loadElem.hasEnergyMeter():
                        pwTree.printf(' (Meter: %s) ', loadElem.getMeterObj().getName())
                        print 
                        loadElem = topo.getNextObject()
                    PDElem = topo.goForward()
            for PDElemt in ckt.getPDElements():
                if PDElemt.isIsolated():
                    pwTree.printf('Isolated: %s.%s', PDElemt.getParentClass().getName(), PDElemt.getName())
                    if PDElemt.hasSensorObj():
                        pwTree.printf(' (Sensor: %s.%s) ', PDElemt.getSensorObj().getParentClass().getName(), PDElemt.getSensorObj().getName())
                    if PDElemt.hasControl():
                        pwTree.printf(' (Control: %s.%s) ', PDElemt.getControlElement().getParentClass().getName(), PDElemt.getControlElement().getName())
                        if (
                            PDElemt.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.SWT_CONTROL
                        ):
                            nSwitches += 1
                    if PDElemt.hasEnergyMeter():
                        pwTree.printf(' (Meter: %s) ', PDElemt.getMeterObj().getName())
                        print 
                        nIsolated += 1
            nLoops = nLoops / 2
            # TODO, see if parallel lines also counted twice
            print String.format.format('%d Levels Deep', nLevels)
            print String.format.format('%d Loops', nLoops)
            print String.format.format('%d Parallel PD elements', nParallel)
            print String.format.format('%d Isolated PD components', nIsolated)
            print String.format.format('%d Controlled Switches', nSwitches)
            pw.close()
            fw.close()
            pwTree.close()
            fwTree.close()
            Utilities.fireOffEditor(fileName)
            DSSGlobals.DSSForms.showTreeView(treeName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showLineConstants(cls, fileNm, freq, units, rho):
        # common mode capacitance
        # TODO: handle exception
        try:
            fw = FileWriter(fileNm)
            pw = PrintWriter(fw)
            print 'LINE CONSTANTS'
            print String.format.format('Frequency = %.6g Hz, Earth resistivity = %.6g ohm-m', freq, rho)
            print 'Earth Model = ' + Utilities.getEarthModel(DSSGlobals.defaultEarthModel)
            print 
            lineCodesFileNm = 'LineConstantsCode.dss'
            fw2 = FileWriter(lineCodesFileNm)
            pw2 = PrintWriter(fw2)
            print '!--- OpenDSS Linecodes file generated from Show LINECONSTANTS command'
            print String.format.format('!--- Frequency = %.6g Hz, Earth resistivity = %.6g ohm-m', freq, rho)
            print '!--- Earth Model = ' + Utilities.getEarthModel(DSSGlobals.defaultEarthModel)
            LineImpl.lineGeometryClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('LineGeometry'))
            Z = None
            Yc = None
            DSSGlobals.activeEarthModel = DSSGlobals.defaultEarthModel
            p = LineImpl.lineGeometryClass.getFirst()
            while p > 0:
                pElem = LineImpl.lineGeometryClass.getActiveObj()
                Z = None
                Yc = None
                # get impedances per unit length
                try:
                    pElem.setRhoEarth(rho)
                    Z = pElem.getZMatrix(freq, 1.0, units)
                    Yc = pElem.getYcMatrix(freq, 1.0, units)
                except Exception, e:
                    DSSGlobals.doSimpleMsg('Error computing line constants for LineGeometry.' + pElem.getName() + '; Error message: ' + e.getMessage(), 9934)
                print 
                print '--------------------------------------------------'
                print 'Geometry Code = ' + pElem.getName()
                print 
                print 'R MATRIX, ohms per ' + LineUnits.lineUnitsStr(units)
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < Z.order()):
                        break
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw.printf('%.6g, ', Z.getElement(i, j).getReal())
                    print 
                print 
                print 'jX MATRIX, ohms per ' + LineUnits.lineUnitsStr(units)
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < Z.order()):
                        break
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw.printf('%.6g, ', Z.getElement(i, j).getImaginary())
                    print 
                print 
                print 'Susceptance (jB) MATRIX, S per ' + LineUnits.lineUnitsStr(units)
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < Yc.order()):
                        break
                    _5 = True
                    j = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw.printf('%.6g, ', Yc.getElement(i, j).getImaginary())
                    print 
                w = (freq * DSSGlobals.TWO_PI) / 1000.0
                print 
                print 'L MATRIX, mH per ' + LineUnits.lineUnitsStr(units)
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < Z.order()):
                        break
                    _7 = True
                    j = 0
                    while True:
                        if _7 is True:
                            _7 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw.printf('%.6g, ', Z.getElement(i, j).getImaginary() / w)
                    print 
                w = (freq * DSSGlobals.TWO_PI) / 1000000000.0
                print 
                print 'C MATRIX, nF per ' + LineUnits.lineUnitsStr(units)
                _8 = True
                i = 0
                while True:
                    if _8 is True:
                        _8 = False
                    else:
                        i += 1
                    if not (i < Yc.order()):
                        break
                    _9 = True
                    j = 0
                    while True:
                        if _9 is True:
                            _9 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw.printf('%.6g, ', Yc.getElement(i, j).getImaginary() / w)
                    print 
                # Write DSS line code record
                # F.println();
                # F.println(,"-------------------------------------------------------------------");
                # F.println(,"-------------------DSS LineCode Definition-------------------------");
                # F.println(,"-------------------------------------------------------------------");
                print 
                print String.format.format('new lineCode.%s nphases=%d  Units=%s', pElem.getName(), Z.order(), LineUnits.lineUnitsStr(units))
                pw2.print_('~ Rmatrix=[')
                _10 = True
                i = 0
                while True:
                    if _10 is True:
                        _10 = False
                    else:
                        i += 1
                    if not (i < Z.order()):
                        break
                    _11 = True
                    j = 0
                    while True:
                        if _11 is True:
                            _11 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw2.printf('%.6g  ', Z.getElement(i, j).getReal())
                    if i < Z.order():
                        pw2.print_('|')
                print ']'
                pw2.print_('~ Xmatrix=[')
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < Z.order()):
                        break
                    _13 = True
                    j = 0
                    while True:
                        if _13 is True:
                            _13 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw2.printf('%.6g  ', Z.getElement(i, j).getImaginary())
                    if i < Z.order():
                        pw2.print_('|')
                print ']'
                w = (freq * DSSGlobals.TWO_PI) / 1000000000.0
                pw2.print_('~ Cmatrix=[')
                _14 = True
                i = 0
                while True:
                    if _14 is True:
                        _14 = False
                    else:
                        i += 1
                    if not (i < Yc.order()):
                        break
                    _15 = True
                    j = 0
                    while True:
                        if _15 is True:
                            _15 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        pw2.printf('%.6g  ', Yc.getElement(i, j).getImaginary() / w)
                    if i < Yc.order():
                        pw2.print_('|')
                print ']'
                # Add pos- and zero-sequence approximation here
                # Kron reduce to 3 phases first
                # Average diagonals and off-diagonals

                Zs = Complex.ZERO
                Zm = Complex.ZERO
                Cs = 0.0
                Cm = 0.0
                if Z.order() == 3:
                    print 
                    print '-------------------------------------------------------------------'
                    print '-------------------Equiv Symmetrical Component --------------------'
                    print '-------------------------------------------------------------------'
                    print 
                    _16 = True
                    i = 0
                    while True:
                        if _16 is True:
                            _16 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        Zs = Zs.add(Z.getElement(i, i))
                    _17 = True
                    i = 0
                    while True:
                        if _17 is True:
                            _17 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        _18 = True
                        j = 0
                        while True:
                            if _18 is True:
                                _18 = False
                            else:
                                j += 1
                            if not (j < i - 1):
                                break
                            # TODO Check zero based indexing
                            Zm = Zm.add(Z.getElement(i, j))
                    Z1 = ComplexUtil.divide(Zs.subtract(Zm), 3.0)
                    Z0 = ComplexUtil.divide(Zm.multiply(2.0).add(Zs), 3.0)
                    w = (freq * DSSGlobals.TWO_PI) / 1000.0
                    print 
                    print 'Z1, ohms per ' + LineUnits.lineUnitsStr(units) + String.format.format(' = %.6g + j %.6g (L1 = %.6g mH) ', Z1.getReal(), Z1.getImaginary(), Z1.getImaginary() / w)
                    print 'Z0, ohms per ' + LineUnits.lineUnitsStr(units) + String.format.format(' = %.6g + j %.6g (L0 = %.6g mH) ', Z0.getReal(), Z0.getImaginary(), Z0.getImaginary() / w)
                    print 
                    # Compute common mode series impedance
                    Z.invert()
                    Ycm = Complex.ZERO
                    _19 = True
                    i = 0
                    while True:
                        if _19 is True:
                            _19 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        _20 = True
                        j = 0
                        while True:
                            if _20 is True:
                                _20 = False
                            else:
                                j += 1
                            if not (j < 3):
                                break
                            Ycm = Ycm.add(Z.getElement(i, j))
                    Xcm = ComplexUtil.invert(Ycm).getImaginary()
                    w = (freq * DSSGlobals.TWO_PI) / 1000000000.0
                    # Capacitance
                    _21 = True
                    i = 0
                    while True:
                        if _21 is True:
                            _21 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        Cs = Cs + Yc.getElement(i, i).getImaginary()
                    _22 = True
                    i = 0
                    while True:
                        if _22 is True:
                            _22 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        _23 = True
                        j = 0
                        while True:
                            if _23 is True:
                                _23 = False
                            else:
                                j += 1
                            if not (j < i - 1):
                                break
                            Cm = Cm + Yc.getElement(i, j).getImaginary()
                    C1 = (Cs - Cm) / 3.0 / w
                    # nF
                    C0 = (Cs + (2.0 * Cm)) / 3.0 / w
                    # Compute common mode shunt capacitance
                    Ycm = Complex.ZERO
                    _24 = True
                    i = 0
                    while True:
                        if _24 is True:
                            _24 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        # add up all elements of Z inverse
                        _25 = True
                        j = 0
                        while True:
                            if _25 is True:
                                _25 = False
                            else:
                                j += 1
                            if not (j < 3):
                                break
                            Ycm = Ycm.add(Yc.getElement(i, j))
                    Ccm = Ycm.getImaginary() / w
                    print 'C1, nF per ' + LineUnits.lineUnitsStr(units) + String.format.format(' = %.6g', C1)
                    print 'C0, nF per ' + LineUnits.lineUnitsStr(units) + String.format.format(' = %.6g', C0)
                    print 
                    w = freq * DSSGlobals.TWO_PI
                    print 'Surge Impedance:'
                    print String.format.format('  Positive sequence = %.6g ohms', cls.Math.sqrt(Z1.getImaginary() / w / (C1 * 1e-09)))
                    print String.format.format('  Zero sequence     = %.6g ohms', cls.Math.sqrt(Z0.getImaginary() / w / (C0 * 1e-09)))
                    print String.format.format('  Common Mode       = %.6g ohms', cls.Math.sqrt(Xcm / w / (Ccm * 1e-09)))
                    print 
                    print 'Propagation Velocity (Percent of speed of light):'
                    print String.format.format('  Positive sequence = %.6g ', (1.0 / cls.Math.sqrt((Z1.getImaginary() / w) * C1 * 1e-09) / 299792458.0 / LineUnits.toPerMeter(units)) * 100.0)
                    print String.format.format('  Zero sequence     = %.6g ', (1.0 / cls.Math.sqrt((Z0.getImaginary() / w) * C0 * 1e-09) / 299792458.0 / LineUnits.toPerMeter(units)) * 100.0)
                    print 
                p = LineImpl.lineGeometryClass.getNext()
            pw.close()
            fw.close()
            pw2.close()
            fw2.close()
            Utilities.fireOffEditor(fileNm)
            Utilities.fireOffEditor(lineCodesFileNm)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showYPrim(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        if ckt is not None:
            if ckt.getActiveCktElement() is not None:
                # TODO: handle exception
                try:
                    fw = FileWriter(fileName)
                    pw = PrintWriter(fw)
                    ace = ckt.getActiveCktElement()
                    print 'Yprim of active circuit element: ' + ace.getParentClass().getName() + '.' + ace.getName()
                    print 
                    cValues = ace.getYPrimValues(DSSGlobals.ALL_YPRIM)
                    if cValues is not None:
                        print 
                        print 'G matrix (conductance), S'
                        print 
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < ace.getYorder()):
                                break
                            _1 = True
                            j = 0
                            while True:
                                if _1 is True:
                                    _1 = False
                                else:
                                    j += 1
                                if not (j < i):
                                    break
                                pw.printf('%13.10g ', cValues[i + ((j - 1) * ace.getYorder())].getReal())
                            print 
                        print 
                        print 'jB matrix (Susceptance), S'
                        print 
                        _2 = True
                        i = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                i += 1
                            if not (i < ace.getYorder()):
                                break
                            _3 = True
                            j = 0
                            while True:
                                if _3 is True:
                                    _3 = False
                                else:
                                    j += 1
                                if not (j < i):
                                    break
                                pw.printf('%13.10g ', cValues[i + ((j - 1) * ace.getYorder())].getImaginary())
                            print 
                    else:
                        print 'Yprim matrix is nil'
                    pw.close()
                    fw.close()
                    Utilities.fireOffEditor(fileName)
                except IOException, e:
                    pass # astStmt: [Stmt([]), None]

    @classmethod
    def showY(cls, fileName):
        """Shows how to retrieve the system Y in triplet form."""
        ckt = DSSGlobals.activeCircuit
        if ckt is None:
            return
        hY = ckt.getSolution().getY()
        if hY is None:
            DSSGlobals.doSimpleMsg('Y Matrix not Built.', 222)
            return
        # print lower triangle of G and B using new functions
        # this compresses the entries if necessary - no extra work if already solved
        # KLU.factorSparseMatrix(hY);
        # KLU.getNNZ(hY, nNZ);
        # KLU.getSize(hY, nBus);  // we should already know this
        # ColIdx = new long[nNZ];
        # RowIdx = new long[nNZ];
        # cVals = new Complex[nNZ];
        # KLU.getTripletMatrix(hY, nNZ, RowIdx[0], ColIdx[0], cVals[0]);
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            print 'System Y Matrix (Lower Triangle by Columns)'
            print 
            print '  Row  Col               G               B'
            print 
            # shows how to easily traverse the triplet format
            # for (i = 0; i < nNZ - 1; i++) {  // TODO Check zero based indexing
            # col = ColIdx[i] + 1;
            # row = RowIdx[i] + 1;
            # if (row >= col) {
            # re = cVals[i].getReal();
            # im = cVals[i].getImaginary();
            # F.println(String.format("[%4d,%4d] = %13.10g + j%13.10g", row, col, re, im));
            # }
            # }
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showNodeCurrentSum(cls, fileName):
        """Summary and tree-view to separate files."""
        # CktElement pCktElement;
        maxNodeCurrent = [None] * 100
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        maxNodeCurrent = None
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # zero out the nodal current array
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                sol.getCurrents()[i] = Complex.ZERO
                # make temp storage for max current at node
            maxNodeCurrent = [None] * (ckt.getNumNodes() + 1)
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                maxNodeCurrent[i] = 0.0
                # now sum in each device current, keep track of the largest current at a node.
            for pCktElement in ckt.getCktElements():
                if pCktElement.isEnabled():
                    pCktElement.computeITerminal()
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < pCktElement.getYorder()):
                            break
                        CTemp = pCktElement.getITerminal()[i]
                        nRef = pCktElement.getNodeRef()[i]
                        sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(CTemp)
                        # nodeRef = 0 is OK  TODO Check
                        if CTemp.abs() > maxNodeCurrent[nRef]:
                            maxNodeCurrent[nRef] = CTemp.abs()
            # now write report
            cls.setMaxBusNameLength()
            cls.maxBusNameLength = cls.maxBusNameLength + 2
            print 
            print 'Node Current Mismatch Report'
            print 
            print 
            print Utilities.pad('Bus,', cls.maxBusNameLength) + ' Node, \"Current Sum (A)' + '%error' + 'Max Current (A)\"'
            # ground bus
            nRef = 0
            dTemp = sol.getCurrents()[nRef].abs()
            if (maxNodeCurrent[nRef] == 0.0) or (maxNodeCurrent[nRef] == dTemp):
                pctError = String.format.format('%10.1f', 0.0)
            else:
                pctError = String.format.format('%10.6f', (dTemp / maxNodeCurrent[nRef]) * 100.0)
            bName = Utilities.pad('\"System Ground\"', cls.maxBusNameLength)
            print String.format.format('%s, %2d, %10.5f,       %s, %10.5f', bName, nRef, dTemp, pctError, maxNodeCurrent[nRef])
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                _4 = True
                j = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        j += 1
                    if not (j < ckt.getBuses()[i].getNumNodesThisBus()):
                        break
                    nRef = ckt.getBuses()[i].getRef(j)
                    dTemp = sol.getCurrents()[nRef].abs()
                    if (maxNodeCurrent[nRef] == 0.0) or (maxNodeCurrent[nRef] == dTemp):
                        pctError = String.format.format('%10.1f', 0.0)
                    else:
                        pctError = String.format.format('%10.6f', (dTemp / maxNodeCurrent[nRef]) * 100.0)
                    if j == 0:
                        bName = Utilities.padDots(Utilities.encloseQuotes(ckt.getBusList().get(i)), cls.maxBusNameLength)
                    else:
                        bName = Utilities.pad('\"   -\"', cls.maxBusNameLength)
                    print String.format.format('%s, %2d, %10.5f,       %s, %10.5f', bName, ckt.getBuses()[i].getNum(j), dTemp, pctError, maxNodeCurrent[nRef])
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
            maxNodeCurrent = None
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showkVBaseMismatch(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            # Check loads
            if len(ckt.getLoads()) > 0:
                print 
                print '!!!  LOAD VOLTAGE BASE MISMATCHES'
                print 
            for pLoad in ckt.getLoads():
                # Find bus to which load connected
                pBus = ckt.getBuses()[pLoad.getTerminals()[0].busRef]
                busName = ckt.getBusList().get(pLoad.getTerminals()[0].busRef)
                if pBus.getKVBase() != 0.0:
                    if pLoad.getNPhases() == 1 and pLoad.getConnection() == 0:
                        if (
                            cls.Math.abs(pLoad.getKVLoadBase() - pBus.getKVBase()) > 0.1 * pBus.getKVBase()
                        ):
                            print String.format.format('!!!!! Voltage Base Mismatch, Load.%s.kV=%.6g, Bus %s LN kvBase = %.6g', pLoad.getName(), pLoad.getKVLoadBase(), pLoad.getBus(0), pBus.getKVBase())
                            print String.format.format('!setkvbase %s kVLN=%.6g', busName, pLoad.getKVLoadBase())
                            print String.format.format('!Load.%s.kV=%.6g', pLoad.getName(), pBus.getKVBase())
                    else:
                        busKV = pBus.getKVBase() * DSSGlobals.SQRT3
                        if cls.Math.abs(pLoad.getKVLoadBase() - busKV) > 0.1 * busKV:
                            print String.format.format('!!!!! Voltage Base Mismatch, Load.%s.kV=%.6g, Bus %s kvBase = %.6g', pLoad.getName(), pLoad.getKVLoadBase(), pLoad.getBus(0), busKV)
                            print String.format.format('!setkvbase %s kVLL=%.6g', busName, pLoad.getKVLoadBase())
                            print String.format.format('!Load.%s.kV=%.6g', pLoad.getName(), busKV)
            # Check generators
            if len(ckt.getGenerators()) > 0:
                print 
                print '!!!  GENERATOR VOLTAGE BASE MISMATCHES'
                print 
            for pGen in ckt.getGenerators():
                # Find bus to which generator connected
                pBus = ckt.getBuses()[pGen.getTerminals()[0].busRef]
                busName = ckt.getBusList().get(pGen.getTerminals()[0].busRef)
                if pBus.getKVBase() != 0.0:
                    if pGen.getNPhases() == 1 and pGen.getConnection() == 0:
                        if (
                            cls.Math.abs(pGen.getGenVars().kVGeneratorBase - pBus.getKVBase()) > 0.1 * pBus.getKVBase()
                        ):
                            print String.format.format('!!! Voltage Base Mismatch, Generator.%s.kV=%.6g, Bus %s LN kvBase = %.6g', pGen.getName(), pGen.getGenVars().kVGeneratorBase, pGen.getBus(1), pBus.getKVBase())
                            print String.format.format('!setkvbase %s kVLN=%.6g', busName, pGen.getGenVars().kVGeneratorBase)
                            print String.format.format('!Generator.%s.kV=%.6g', pGen.getName(), pBus.getKVBase())
                    else:
                        busKV = pBus.getKVBase() * DSSGlobals.SQRT3
                        if cls.Math.abs(pGen.getGenVars().kVGeneratorBase - busKV) > 0.1 * busKV:
                            print String.format.format('!!! Voltage Base Mismatch, Generator.%s.kV=%.6g, Bus %s kvBase = %.6g', pGen.getName(), pGen.getGenVars().kVGeneratorBase, pGen.getBus(1), busKV)
                            print String.format.format('!setkvbase %s kVLL=%.6g', busName, pGen.getGenVars().kVGeneratorBase)
                            print String.format.format('!Generator.%s.kV=%.6g', pGen.getName(), busKV)
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def showDeltaV(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            fw = FileWriter(fileName)
            pw = PrintWriter(fw)
            cls.setMaxDeviceNameLength()
            print 
            print 'VOLTAGES ACROSS CIRCUIT ELEMENTS WITH 2 TERMINALS'
            print 
            print 'Source Elements'
            print 
            print Utilities.pad('Element,', cls.maxDeviceNameLength) + ' Conductor,     Volts,   Percent,           kVBase,  Angle'
            print 
            # sources first
            for pElem in ckt.getSources():
                if pElem.isEnabled() and pElem.getNTerms() == 2:
                    cls.writeElementDeltaVoltages(pw, pElem)
                    print 
            print 
            print 'Power Delivery Elements'
            print 
            print Utilities.pad('Element,', cls.maxDeviceNameLength) + ' Conductor,     Volts,   Percent,           kVBase,  Angle'
            print 
            # PD elements next
            for pElem in ckt.getPDElements():
                if pElem.isEnabled() and pElem.getNTerms() == 2:
                    cls.writeElementDeltaVoltages(pw, pElem)
                    print 
            print '= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = ='
            print 
            print 'Power Conversion Elements'
            print 
            print Utilities.pad('Element,', cls.maxDeviceNameLength) + ' Conductor,     Volts,   Percent,           kVBase,  Angle'
            print 
            # PC elements next
            for pElem in ckt.getPCElements():
                if pElem.isEnabled() and pElem.getNTerms() == 2:
                    cls.writeElementDeltaVoltages(pw, pElem)
                    print 
            pw.close()
            fw.close()
            Utilities.fireOffEditor(fileName)
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

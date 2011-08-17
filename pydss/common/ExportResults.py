from dss.conversion.Generator import (Generator,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.EnergyMeter import (EnergyMeter,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.File import (File,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.IOException import (IOException,)
# from java.io.PrintWriter import (PrintWriter,)
# from java.text.SimpleDateFormat import (SimpleDateFormat,)
# from java.util.Calendar import (Calendar,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class ExportResults(object):

    def __init__(self):
        pass

    @classmethod
    def exportSeqVoltages(cls, fileName):
        """Export symmetrical component bus voltages."""
        Vph = [None] * 3
        V012 = [None] * 3
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        # FIXME: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            print 'Bus,  V1,  p.u.,Base kV, V2, %V2/V1, V0, %V0/V1, Vresidual, %NEMA'
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                if ckt.getBuses()[i].getNumNodesThisBus() < 3:
                    V0 = 0.0
                    V2 = 0.0
                    V_NEMA = 0.0
                    if ckt.getBuses()[i].getNumNodesThisBus() == 1 and ckt.isPositiveSequence():
                        # first node
                        nref = ckt.getBuses()[i].getRef(0)
                        # TODO Check zero based indexing
                        Vph[0] = ckt.getSolution().getNodeV()[nref]
                        V1 = Vph[0].abs()
                    else:
                        V1 = 0.0
                else:
                    bus = ckt.getBuses()[i]
                    _1 = True
                    j = 1
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < 4):
                            break
                        # first nodes named  1, 2, 3  TODO Check zero based indexing
                        Vph[j] = sol.getNodeV()[bus.getRef(bus.findIdx(j))]
                    MathUtil.phase2SymComp(Vph, V012)
                    V0 = V012[0].abs()
                    V1 = V012[1].abs()
                    V2 = V012[2].abs()
                    V_NEMA = MathUtil.pctNemaUnbalance(Vph)
                if ckt.getBuses()[i].getKVBase() != 0.0:
                    Vpu = (0.001 * V1) / ckt.getBuses()[i].getKVBase()
                else:
                    Vpu = 0.0
                if V1 > 0.0:
                    V2V1 = (100.0 * V2) / V1
                    V0V1 = (100.0 * V0) / V1
                else:
                    V2V1 = 0.0
                    V0V1 = 0.0
                Vresidual = Complex.ZERO
                _2 = True
                j = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j += 1
                    if not (j < ckt.getBuses()[i].getNumNodesThisBus()):
                        break
                    Vresidual = Vresidual.add(sol.getNodeV()[ckt.getBuses()[i].getRef(j)])
                writer.printf('\"%s\", %10.6g, %9.5g, %8.2f, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g', ckt.getBusList().get(i), V1, Vpu, ckt.getBuses()[i].getKVBase() * DSSGlobals.SQRT3, V2, V2V1, V0, V0V1, Vresidual.abs(), V_NEMA)
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportVoltages(cls, fileName):
        """Export symmetrical component bus voltages."""
        nodeIdx = 0
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        # Find max nodes at a bus
        maxNumNodes = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            maxNumNodes = cls.Math.max(maxNumNodes, ckt.getBuses()[i].getNumNodesThisBus())
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            writer.print_('Bus, BasekV')
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < maxNumNodes):
                    break
                writer.printf(', Node%d, Magnitude%d, Angle%d, pu%d', i, i, i, i)
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
                busName = ckt.getBusList().get(i)
                writer.printf('\"%s\", %.5g', busName, ckt.getBuses()[i].getKVBase() * DSSGlobals.SQRT3)
                jj = 0
                bus = ckt.getBuses()[i]
                _3 = True
                j = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        j += 1
                    if not (j < bus.getNumNodesThisBus()):
                        break
                    while # TODO Check zero based indexing nodeIdx <= 0:
                        nodeIdx = bus.findIdx(jj)
                        # try to find nodes in order
                        jj += 1
                    nref = bus.getRef(nodeIdx)
                    volts = sol.getNodeV()[nref]
                    Vmag = volts.abs()
                    if bus.getKVBase() != 0.0:
                        Vpu = (0.001 * Vmag) / bus.getKVBase()
                    else:
                        Vpu = 0.0
                    writer.printf(', %d, %10.6g, %6.1f, %9.5g', bus.getNum(nodeIdx), Vmag, ComplexUtil.degArg(volts), Vpu)
                # Zero fill row
                _4 = True
                j = ckt.getBuses()[i].getNumNodesThisBus()
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        j += 1
                    if not (j < maxNumNodes + 1):
                        break
                    # TODO Check zero based indexing
                    writer.print_(', 0, 0, 0, 0')
                print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def calcAndWriteSeqCurrents(cls, writer, j, pElem, cBuffer, doRatings):
        Iph = [None] * 3
        I012 = [None] * 3
        nCond = pElem.getNConds()
        if pElem.getNPhases() >= 3:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < 3):
                    break
                # TODO Check one based indexing
                k = ((j - 1) * nCond) + i
                Iph[i] = cBuffer[k]
            MathUtil.phase2SymComp(Iph, I012)
            I0 = I012[0].abs()
            I1 = I012[1].abs()
            I2 = I012[2].abs()
            I_NEMA = MathUtil.pctNemaUnbalance(Iph)
        else:
            I0 = 0.0
            I1 = 0.0
            I2 = 0.0
            I_NEMA = 0.0
            if DSSGlobals.activeCircuit.isPositiveSequence():
                I1 = Iph[0].abs()
                # use phase 1 only
        if I1 > 0.0:
            I2I1 = (100.0 * I2) / I1
            I0I1 = (100.0 * I0) / I1
        else:
            I2I1 = 0.0
            I0I1 = 0.0
        if doRatings and j == 0:
            # Only for 1st Terminal   TODO Check zero based indexing
            INormal = pElem.getNormAmps()
            if INormal > 0.0:
                INormal = (I1 / INormal) * 100.0
            IEmerg = pElem.getEmergAmps()
            if IEmerg > 0.0:
                IEmerg = (I1 / IEmerg) * 100.0
        else:
            INormal = 0.0
            IEmerg = 0.0
        IResidual = Complex.ZERO
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < nCond):
                break
            IResidual = IResidual.add(cBuffer[i])
        writer.printf('\"%s\", %3d, %10.6g, %8.4g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g', pElem.getDSSClassName() + '.' + pElem.getName(), j, I1, INormal, IEmerg, I2, I2I1, I0, I0I1, IResidual.abs(), I_NEMA)

    @classmethod
    def exportSeqCurrents(cls, fileName):
        # allocate to max total conductors
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            # Sequence currents
            print 'Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1, Iresidual, %NEMA'
            # Allocate cBuffer big enough for largest circuit element
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            # sources first
            for pElem in ckt.getSources():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    _0 = True
                    j = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            j += 1
                        if not (j < pElem.getNTerms()):
                            break
                        cls.calcAndWriteSeqCurrents(writer, j, pElem, cBuffer, False)
            # PD elements next
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    PDElem.getCurrents(cBuffer)
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < PDElem.getNTerms()):
                        break
                    cls.calcAndWriteSeqCurrents(writer, j, PDElem, cBuffer, True)
            # PC elements next
            for PCElem in ckt.getPCElements():
                if PCElem.isEnabled():
                    PCElem.getCurrents(cBuffer)
                    _2 = True
                    j = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            j += 1
                        if not (j < PCElem.getNTerms()):
                            break
                        cls.calcAndWriteSeqCurrents(writer, j, PCElem, cBuffer, False)
            # faults next
            for pElem in ckt.getFaults():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                _3 = True
                j = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        j += 1
                    if not (j < pElem.getNTerms()):
                        break
                    cls.calcAndWriteSeqCurrents(writer, j, pElem, cBuffer, False)
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def calcAndWriteCurrents(cls, writer, pElem, cBuffer, condWidth, termWidth):
        k = 0
        writer.printf('%s', pElem.getDSSClassName() + '.' + pElem.getName())
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < pElem.getNTerms()):
                break
            IResid = Complex.ZERO
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < pElem.getNConds()):
                    break
                k += 1
                writer.printf(', %10.6g, %8.2f', cBuffer[k].abs(), ComplexUtil.degArg(cBuffer[k]))
                IResid = IResid.add(cBuffer[k])
            _2 = True
            i = pElem.getNConds()
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < condWidth):
                    break
                # TODO Check zero based indexing
                writer.printf(', %10.6g, %8.2f', 0.0, 0.0)
            writer.printf(', %10.6g, %8.2f', IResid.abs(), ComplexUtil.degArg(IResid))
        # Filler if no. terms less than termwidth
        _3 = True
        j = pElem.getNTerms()
        while True:
            if _3 is True:
                _3 = False
            else:
                j += 1
            if not (j < termWidth):
                break
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < condWidth + 1):
                    break
                writer.printf(', %10.6g, %8.2f', 0.0, 0.0)
        print 

    @classmethod
    def calcAndWriteMaxCurrents(cls, writer, pElem, cBuffer):
        writer.printf('%s.%s', pElem.getDSSClassName(), pElem.getName())
        maxCurrent = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < pElem.getNPhases()):
                break
            currMag = cBuffer[i].abs()
            if currMag > maxCurrent:
                maxCurrent = currMag
        localPower = pElem.getPower(0).multiply(0.001)
        # TODO Check zero based indexing
        if (pElem.getNormAmps() == 0.0) or (pElem.getEmergAmps() == 0.0):
            writer.printf(', %10.6g, %8.2f, %8.2f', maxCurrent, 0.0, 0.0)
        else:
            writer.printf(', %10.6g, %8.2f, %8.2f', maxCurrent, (maxCurrent / pElem.getNormAmps()) * 100.0, (maxCurrent / pElem.getEmergAmps()) * 100.0)
        writer.printf(', %10.6g, %10.6g, %d, %d, %d', localPower.getReal(), localPower.getImaginary(), pElem.getNumCustomers(), pElem.getTotalCustomers(), pElem.getNPhases())
        ckt = DSSGlobals.activeCircuit
        writer.printf(', %-.3g ', ckt.getBuses()[ckt.getMapNodeToBus()[pElem.getNodeRef()[0]].busRef].getKVBase())
        print 

    @classmethod
    def exportCurrents(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            # Calculate the width of the file
            maxCond = 1
            maxTerm = 2
            for pElem in ckt.getCktElements():
                if pElem.getNTerms() > maxTerm:
                    maxTerm = pElem.getNTerms()
                if pElem.getNConds() > maxCond:
                    maxCond = pElem.getNConds()
            # Branch currents
            writer.print_('Element')
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < maxTerm):
                    break
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < maxCond):
                        break
                    writer.printf(', I%d_%d, Ang%d_%d', i, j, i, j)
                writer.printf(', Iresid%d, AngResid%d', i, i)
            print 
            # sources first
            for pElem in ckt.getSources():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    cls.calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm)
            # PD elements next
            for pElem in ckt.getPDElements():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    cls.calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm)
            # faults
            for pElem in ckt.getFaults():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    cls.calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm)
            # PC elements next
            for pElem in ckt.getPCElements():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    cls.calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm)
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            cBuffer = None
            # TODO: handle exception

    @classmethod
    def exportPowers(cls, fileName, opt):
        """Opt = 0: kVA
        Opt = 1: MVA
        """
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            _0 = opt
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    print 'Element, Terminal, P(MW), Q(Mvar), P_Normal, Q_Normal, P_Emergency, Q_Emergency'
                    break
                if True:
                    _1 = True
                    print 'Element, Terminal, P(kW), Q(kvar),  P_Normal, Q_Normal, P_Emergency, Q_Emergency'
                    break
                break
            # PD elements first
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    Nterm = PDElem.getNTerms()
                    _2 = True
                    j = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            j += 1
                        if not (j < Nterm):
                            break
                        writer.print_(Utilities.pad('\"' + PDElem.getDSSClassName() + '.' + PDElem.getName() + '\"', 24) + sep + j)
                        S = PDElem.getPower(j)
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.001))
                        writer.print_(sep + (S.getImaginary() * 0.001))
                        if j == 0:
                            S = PDElem.getExcessKVANorm(0)
                            # TODO Check zero based indexing
                            if opt == 1:
                                S = S.multiply(0.001)
                            writer.print_(sep + cls.Math.abs(S.getReal()))
                            writer.print_(sep + cls.Math.abs(S.getImaginary()))
                            S = PDElem.getExcessKVAEmerg(0)
                            # TODO Check zero based indexing
                            if opt == 1:
                                S = S.multiply(0.001)
                            writer.print_(sep + cls.Math.abs(S.getReal()))
                            writer.print_(sep + cls.Math.abs(S.getImaginary()))
                        print 
            # PC elements next
            for PCElem in ckt.getPCElements():
                if PCElem.isEnabled():
                    Nterm = PCElem.getNTerms()
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < Nterm):
                            break
                        writer.print_(Utilities.pad('\"' + PCElem.getDSSClassName() + '.' + PCElem.getName() + '\"', 24) + sep + j)
                        S = PCElem.getPower(j)
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.001))
                        writer.print_(sep + (S.getImaginary() * 0.001))
                        print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportLosses(cls, fileName):
        S_total = [None] * 2
        S_Load = [None] * 2
        S_NoLoad = [None] * 2
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            print 'Element,  Total(W), Total(var),  I2R(W), I2X(var), No-load(W), No-load(var)'
            for PDElem in DSSGlobals.activeCircuit.getPDElements():
                if PDElem.isEnabled():
                    PDElem.getLosses(S_total, S_Load, S_NoLoad)
                    writer.printf('%s.%s, %.7g, %.7g, %.7g, %.7g, %.7g, %.7g', PDElem.getParentClass().getName(), PDElem.getName(), S_total[0], S_total[1], S_Load[0], S_Load[1], S_NoLoad[0], S_NoLoad[1])
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportPbyphase(cls, fileName, opt):
        """Export powers by phase.

        opt = 0: kVA
        opt = 1: MVA
        """
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            _0 = opt
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    print 'Element, NumTerminals, NumConductors, NumPhases, MW1, Mvar1, MW2, Mvar2, MW3, Mvar3, ... '
                    break
                if True:
                    _1 = True
                    print 'Element, NumTerminals, NumConductors, NumPhases, kW1, kvar1, kW2, kvar2, kW3, kvar3, ... '
                    break
                break
            # PD elements first
            for PDElem in DSSGlobals.activeCircuit.getPDElements():
                if PDElem.isEnabled():
                    PDElem.computeITerminal()
                    PDElem.computeVTerminal()
                    writer.printf('\"%s.%s\", %d, %d, %d', PDElem.getDSSClassName(), PDElem.getName(), PDElem.getNTerms(), PDElem.getNConds(), PDElem.getNPhases())
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < PDElem.getYorder()):
                            break
                        S = PDElem.getVTerminal()[i].multiply(PDElem.getITerminal()[i].conjugate()).multiply(0.001)
                        if opt == 1:
                            S = S.multiply(0.001)
                            # convert to MVA
                        writer.printf(', %10.3f, %10.3f', S.getReal(), S.getImaginary())
                    print 
            # PC elements next
            for PCElem in DSSGlobals.activeCircuit.getPCElements():
                if PCElem.isEnabled():
                    PCElem.computeITerminal()
                    PCElem.computeVTerminal()
                    writer.printf('\"%s.%s\", %d, %d, %d', PCElem.getDSSClassName(), PCElem.getName(), PCElem.getNTerms(), PCElem.getNConds(), PCElem.getNPhases())
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < PCElem.getYorder()):
                            break
                        S = PCElem.getVTerminal()[i].multiply(PCElem.getITerminal()[i].conjugate()).multiply(0.001)
                        if opt == 1:
                            S = S.multiply(0.001)
                            # convert to MVA
                        writer.printf(', %10.3f, %10.3f', S.getReal(), S.getImaginary())
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportSeqPowers(cls, fileName, opt):
        """opt = 0: kVA
        opt = 1: MVA
        """
        Vph = [None] * 3
        V012 = [None] * 3
        Iph = [None] * 3
        I012 = [None] * 3
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            _0 = opt
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    print 'Element, Terminal, P1(MW), Q1(Mvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency'
                    break
                if True:
                    _1 = True
                    print 'Element, Terminal, P1(kW), Q1(kvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency'
                    break
                break
            # PD elements first
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    NCond = PDElem.getNConds()
                    nTerm = PDElem.getNTerms()
                    PDElem.getCurrents(cBuffer)
                    _2 = True
                    j = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            j += 1
                        if not (j < nTerm):
                            break
                        writer.print_(Utilities.pad('\"' + PDElem.getDSSClassName() + '.' + PDElem.getName() + '\"', 24) + sep + j)
                        _3 = True
                        i = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < PDElem.getNPhases()):
                                break
                            k = ((j - 1) * NCond) + i
                            nref = PDElem.getNodeRef()[k]
                            volts = ckt.getSolution().getNodeV()[nref]
                            Iph[i] = cBuffer[k]
                            Vph[i] = volts
                        if PDElem.getNPhases() >= 3:
                            MathUtil.phase2SymComp(Iph, I012)
                            MathUtil.phase2SymComp(Vph, V012)
                        else:
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
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        S = V012[2].multiply(I012[2].conjugate())
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        S = V012[0].multiply(I012[0].conjugate())
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        if j == 0:
                            S = PDElem.getExcessKVANorm(0)
                            if opt == 1:
                                S = S.multiply(0.001)
                            writer.print_(sep + cls.Math.abs(S.getReal()))
                            writer.print_(sep + cls.Math.abs(S.getImaginary()))
                            S = PDElem.getExcessKVAEmerg(0)
                            if opt == 1:
                                S = S.multiply(0.001)
                            writer.print_(sep + cls.Math.abs(S.getReal()))
                            writer.print_(sep + cls.Math.abs(S.getImaginary()))
                        print 
            # PC elements next
            for PCElem in ckt.getPCElements():
                if PCElem.isEnabled():
                    NCond = PCElem.getNConds()
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
                        writer.print_(Utilities.pad('\"' + PCElem.getDSSClassName() + '.' + PCElem.getName() + '\"', 24) + sep + j)
                        _5 = True
                        i = 0
                        while True:
                            if _5 is True:
                                _5 = False
                            else:
                                i += 1
                            if not (i < PCElem.getNPhases()):
                                break
                            k = ((j - 1) * NCond) + i
                            nref = PCElem.getNodeRef()[k]
                            volts = ckt.getSolution().getNodeV()[nref]
                            Iph[i] = cBuffer[k]
                            Vph[i] = volts
                        if PCElem.getNPhases() >= 3:
                            MathUtil.phase2SymComp(Iph, I012)
                            MathUtil.phase2SymComp(Vph, V012)
                        else:
                            V012[0] = Complex.ZERO
                            I012[0] = Complex.ZERO
                            V012[2] = Complex.ZERO
                            I012[2] = Complex.ZERO
                            if ckt.isPositiveSequence():
                                V012[1] = Vph[0]
                                I012[2] = Iph[0]
                            else:
                                V012[1] = Complex.ZERO
                                I012[1] = Complex.ZERO
                        S = V012[1].multiply(I012[1].conjugate())
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        S = V012[2].multiply(I012[2].conjugate())
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        S = V012[0].multiply(I012[0].conjugate())
                        if opt == 1:
                            S = S.multiply(0.001)
                        writer.print_(sep + (S.getReal() * 0.003))
                        writer.print_(sep + (S.getImaginary() * 0.003))
                        print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportFaultStudy(cls, fileName):
        # big temp array
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            # Set source voltage injection currents
            # All phase faults
            print 'Bus,  3-Phase,  1-Phase,  L-L'
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
                writer.print_(Utilities.pad(ckt.getBusList().get(iBus), 12))
                maxCurr = 0.0
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < bus.getNumNodesThisBus()):
                        break
                    if maxCurr < bus.getBusCurrent()[i].abs():
                        maxCurr = bus.getBusCurrent()[i].abs()
                writer.print_(sep + maxCurr)
                # One phase faults
                # Solve for fault injection currents
                YFault = CMatrixImpl(bus.getNumNodesThisBus())
                VFault = [None] * bus.getNumNodesThisBus()
                # Build YscTemp
                GFault = Complex(10000.0, 0.0)
                maxCurr = 0.0
                _2 = True
                iphs = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        iphs += 1
                    if not (iphs < bus.getNumNodesThisBus()):
                        break
                    YFault.copyFrom(bus.getYsc())
                    YFault.addElement(iphs, iphs, GFault)
                    # Solve for injection currents
                    YFault.invert()
                    YFault.vMult(VFault, bus.getBusCurrent())
                    # Gets voltage appearing at fault
                    currMag = VFault[iphs].multiply(GFault).abs()
                    if currMag > maxCurr:
                        maxCurr = currMag
                # Now, put it in the Css array where it belongs
                writer.print_(sep + maxCurr)
                VFault = None
                YFault = None
                # Node-node faults
                # Bus Norton equivalent current, Isc has been previously computed
                YFault = CMatrixImpl(bus.getNumNodesThisBus())
                VFault = [None] * bus.getNumNodesThisBus()
                GFault = Complex(10000.0, 0.0)
                maxCurr = 0.0
                _3 = True
                iphs = 0
                while True:
                    if _3 is True:
                        _3 = False
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
                    currMag = VFault[iphs].subtract(VFault[iphs + 1]).multiply(GFault).abs()
                    if currMag > maxCurr:
                        maxCurr = currMag
                # Now, put it in the Css array where it belongs
                writer.print_(sep + maxCurr)
                VFault = None
                YFault = None
                print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def zeroTempXArray(cls, tempX):
        _0 = True
        ii = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                ii += 1
            if not (ii < 3):
                break
            tempX[ii] = 0

    @classmethod
    def exportEstimation(cls, fileName):
        tempX = [None] * 3
        # temp number buffer
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            # Do the energy meters first
            print '\"Energy Meters\" '
            print '\"energyMeter\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\"'
            for pEnergyMeterObj in ckt.getEnergyMeters():
                if pEnergyMeterObj.isEnabled():
                    writer.printf('\"Energymeter.%s\"', pEnergyMeterObj.getName())
                    # Sensor currents (target)
                    cls.zeroTempXArray(tempX)
                    _0 = True
                    i = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            i += 1
                        if not (i < pEnergyMeterObj.getNPhases()):
                            break
                        tempX[i] = pEnergyMeterObj.getSensorCurrent()[i]
                    _1 = True
                    i = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Calculated currents
                    cls.zeroTempXArray(tempX)
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < pEnergyMeterObj.getNPhases()):
                            break
                        tempX[i] = pEnergyMeterObj.getCalculatedCurrent()[i].abs()
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Percent error
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < pEnergyMeterObj.getNPhases()):
                            break
                        tempX[i] = (1.0 - (tempX[i] / cls.Math.max(0.001, pEnergyMeterObj.getSensorCurrent()[i]))) * 100.0
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                    print 
            # Do the sensors next
            print 
            print '\"Sensors\" '
            writer.print_('\"Sensor\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\",')
            print ' \"V1 Target\", \"V2 Target\", \"V3 Target\", \"V1 Calc\", \"V2 Calc\", \"V3 Calc\", \"V1 %Err\", \"V2 %Err\", \"V3 %Err\", \"WLS Voltage Err\", \"WLS Current Err\"'
            for pSensorObj in ckt.getSensors():
                if pSensorObj.isEnabled():
                    writer.printf('\"Sensor.%s\"', pSensorObj.getName())
                    # Sensor currents (target)
                    cls.zeroTempXArray(tempX)
                    _6 = True
                    i = 0
                    while True:
                        if _6 is True:
                            _6 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = pSensorObj.getSensorCurrent()[i]
                    _7 = True
                    i = 0
                    while True:
                        if _7 is True:
                            _7 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Calculated currents
                    cls.zeroTempXArray(tempX)
                    _8 = True
                    i = 0
                    while True:
                        if _8 is True:
                            _8 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = pSensorObj.getCalculatedCurrent()[i].abs()
                    _9 = True
                    i = 0
                    while True:
                        if _9 is True:
                            _9 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Percent error
                    _10 = True
                    i = 0
                    while True:
                        if _10 is True:
                            _10 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = (1.0 - (tempX[i] / cls.Math.max(0.001, pSensorObj.getSensorCurrent()[i]))) * 100.0
                    _11 = True
                    i = 0
                    while True:
                        if _11 is True:
                            _11 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Sensor voltage (target)
                    cls.zeroTempXArray(tempX)
                    _12 = True
                    i = 0
                    while True:
                        if _12 is True:
                            _12 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = pSensorObj.getSensorVoltage()[i]
                    _13 = True
                    i = 0
                    while True:
                        if _13 is True:
                            _13 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Calculated voltage
                    cls.zeroTempXArray(tempX)
                    _14 = True
                    i = 0
                    while True:
                        if _14 is True:
                            _14 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = pSensorObj.getCalculatedVoltage()[i].abs()
                    _15 = True
                    i = 0
                    while True:
                        if _15 is True:
                            _15 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # Percent error
                    _16 = True
                    i = 0
                    while True:
                        if _16 is True:
                            _16 = False
                        else:
                            i += 1
                        if not (i < pSensorObj.getNPhases()):
                            break
                        tempX[i] = (1.0 - (tempX[i] / cls.Math.max(0.001, pSensorObj.getSensorVoltage()[i]))) * 100.0
                    _17 = True
                    i = 0
                    while True:
                        if _17 is True:
                            _17 = False
                        else:
                            i += 1
                        if not (i < 3):
                            break
                        writer.printf(', %.6g', tempX[i])
                        # WLS errors
                    cls.zeroTempXArray(tempX)
                    writer.printf(', %.6g, %.6g', pSensorObj.getWLSVoltageError(), pSensorObj.getWLSCurrentError())
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def writeMultipleMeterFiles(cls):
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        meterClass = DSSClassDefs.getDSSClass('EnergyMeter')
        if meterClass is None:
            return
        for pElem in ckt.getEnergyMeters():
            if pElem.isEnabled():
                # TODO: handle exception
                try:
                    fileName = DSSGlobals.DSSDataDirectory + 'EXP_MTR_' + pElem.getName() + '.csv'
                    if not File(fileName).exists():
                        f = FileWriter(fileName)
                        writer = PrintWriter(f)
                        # Write new header
                        writer.print_('Year, LDCurve, Hour, Meter')
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                                break
                            writer.write(sep + '\"' + pElem.getRegisterNames()[i] + '\"')
                        print 
                        writer.close()
                        f.close()
                    f = FileWriter(fileName, True)
                    # append
                    writer = PrintWriter(f)
                    writer.print_(ckt.getSolution().getYear() + sep)
                    writer.print_(ckt.getLoadDurCurve() + sep)
                    writer.print_(ckt.getSolution().getIntHour() + sep)
                    writer.print_(Utilities.pad('\"' + pElem.getName() + '\"', 14))
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < EnergyMeter.NUM_EM_REGISTERS):
                            break
                        writer.print_(sep + pElem.getRegisters()[j])
                    print 
                    DSSGlobals.globalResult = fileName
                    writer.close()
                    f.close()
                except IOException, e:
                    pass # astStmt: [Stmt([]), None]

    @classmethod
    def writeSingleMeterFile(cls, fileName):
        # String TestStr;
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            if File(fileName).exists():
                # See if it has already been written on
                # F = new FileWriter(FileNm);
                # FPrinter = new PrintWriter(F);
                # IF  Not EOF(F)
                # THEN Begin
                # Read(F, TestStr);
                # {See if it likely that the file is OK}
                # IF  CompareText(Copy(TestStr,1,4), 'Year')=0
                # THEN RewriteFile = FALSE       // Assume the file is OK
                # ELSE RewriteFile = TRUE;
                # End
                # ELSE RewriteFile = TRUE;
                # CloseFile(F);
                rewriteFile = False
                # FIXME See if it likely that the file is OK
            else:
                rewriteFile = True
            # Either open or append the file
            if rewriteFile:
                f = FileWriter(fileName)
                writer = PrintWriter(f)
                # Write New Header
                pElem = ckt.getEnergyMeters().get(0)
                writer.print_('Year, LDCurve, Hour, Meter')
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    writer.print_(sep + '\"' + pElem.getRegisterNames()[i] + '\"')
                print 
            else:
                f = FileWriter(fileName, True)
                writer = PrintWriter(f)
            for pElem in ckt.getEnergyMeters():
                if pElem.isEnabled():
                    writer.print_(ckt.getSolution().getYear() + sep)
                    writer.print_(ckt.getLoadDurCurve() + sep)
                    writer.print_(ckt.getSolution().getIntHour() + sep)
                    writer.print_(Utilities.pad('\"' + pElem.getName() + '\"', 14))
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < EnergyMeter.NUM_EM_REGISTERS):
                            break
                        writer.printf(sep + pElem.getRegisters()[j])
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportMeters(cls, fileName):
        """Export values of meter elements.
        These records are appended to an existing file so a running account is
        kept for some kinds of simulations.
        If switch /m is specified, a separate file is created for each meter
        using the meter's name.
        """
        if fileName[:2].toLowerCase() == '/m':
            cls.writeMultipleMeterFiles()
        else:
            cls.writeSingleMeterFile(fileName)

    @classmethod
    def writeMultipleGenMeterFiles(cls):
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        generatorClass = DSSClassDefs.getDSSClass('generator')
        if generatorClass is None:
            return
        for pElem in ckt.getGenerators():
            if pElem.isEnabled():
                # TODO: handle exception
                try:
                    fileName = DSSGlobals.DSSDataDirectory + 'EXP_GEN_' + pElem.getName() + '.csv'
                    if not File(fileName).exists():
                        f = FileWriter(fileName)
                        writer = PrintWriter(f)
                        # Write new header
                        writer.print_('Year, LDCurve, Hour, Generator')
                        _0 = True
                        i = 0
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < Generator.NumGenRegisters):
                                break
                            writer.print_(sep + '\"' + generatorClass.getRegisterNames()[i] + '\"')
                        print 
                        writer.close()
                        f.close()
                    f = FileWriter(fileName, True)
                    # append
                    writer = PrintWriter(f)
                    writer.print_(ckt.getSolution().getYear() + sep)
                    writer.print_(ckt.getLoadDurCurve() + sep)
                    writer.print_(ckt.getSolution().getIntHour() + sep)
                    writer.print_(Utilities.pad('\"' + pElem.getName() + '\"', 14))
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < Generator.NumGenRegisters):
                            break
                        writer.print_(sep + pElem.getRegisters()[j])
                    print 
                    DSSGlobals.globalResult = fileName
                    writer.close()
                    f.close()
                except IOException, e:
                    pass # astStmt: [Stmt([]), None]

    @classmethod
    def writeSingleGenMeterFile(cls, fileName):
        # String TestStr;
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        generatorClass = DSSClassDefs.getDSSClass('generator')
        if generatorClass is None:
            return
        # TODO: handle exception
        try:
            if File(fileName).exists():
                # See if it has already been written on
                # F = new FileWriter(FileNm);
                # FPrinter = new PrintWriter(F);
                # IF  Not EOF(F)
                # THEN Begin
                # Read(F, TestStr);
                # {See if it likely that the file is OK}
                # IF  CompareText(Copy(TestStr,1,4), 'Year')=0
                # THEN RewriteFile = FALSE       // Assume the file is OK
                # ELSE RewriteFile = TRUE;
                # End
                # ELSE RewriteFile = TRUE;
                # CloseFile(F);
                rewriteFile = False
                # FIXME See if it likely that the file is OK
            else:
                rewriteFile = True
            # Either open or append the file
            if rewriteFile:
                f = FileWriter(fileName)
                writer = PrintWriter(f)
                # Write new header
                writer.print_('Year, LDCurve, Hour, Generator')
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < Generator.NumGenRegisters):
                        break
                    writer.print_(sep + '\"' + generatorClass.getRegisterNames()[i] + '\"')
                print 
            else:
                f = FileWriter(fileName, True)
                # append
                writer = PrintWriter(f)
            for pElem in ckt.getGenerators():
                if pElem.isEnabled():
                    writer.print_(ckt.getSolution().getYear() + sep)
                    writer.print_(ckt.getLoadDurCurve() + sep)
                    writer.print_(ckt.getSolution().getIntHour() + sep)
                    writer.print_(Utilities.pad('\"' + pElem.getName() + '\"', 14))
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < Generator.NumGenRegisters):
                            break
                        writer.print_(sep + pElem.getRegisters()[j])
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportGenMeters(cls, fileNm):
        """Export values of generator meter elements.
        If switch /m is specified, a separate file is created for each
        generator using the generator's name.
        """
        if fileNm[:2].toLowerCase() == '/m':
            cls.writeMultipleGenMeterFiles()
        else:
            cls.writeSingleGenMeterFile(fileNm)

    @classmethod
    def exportLoads(cls, fileName):
        """Export loads to view present allocation."""
        sep = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            # Write header
            print 'Load, Connected KVA, Allocation Factor, Phases, kW, kvar, PF, Model'
            for pElem in ckt.getLoads():
                if pElem.isEnabled():
                    writer.print_(pElem.getName())
                    writer.print_(sep + pElem.getConnectedKVA())
                    writer.print_(sep + pElem.getKVAAllocationFactor())
                    writer.print_(sep + pElem.getNPhases())
                    writer.print_(sep + pElem.getKWBase())
                    writer.print_(sep + pElem.getKVArBase())
                    writer.print_(sep + pElem.getPFNominal())
                    writer.print_(sep + pElem.getLoadModel())
                print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportCapacity(cls, fileNm):
        """Similar to export currents except does only max of the phases and
        compares that to the NormAmps and EmergAmps rating.
        """
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileNm)
            writer = PrintWriter(f)
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            print 'Name, Imax, %normal, %emergency, kW, kvar, NumCustomers, TotalCustomers, NumPhases, kVBase'
            # PD elements only
            for pElem in ckt.getPDElements():
                if pElem.isEnabled():
                    pElem.getCurrents(cBuffer)
                    cls.calcAndWriteMaxCurrents(writer, pElem, cBuffer)
            DSSGlobals.globalResult = fileNm
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportOverloads(cls, fileName):
        # allocate to max total conductors
        Iph = [None] * 3
        I012 = [None] * 3
        Separator = ', '
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            # Allocate cBuffer big enough for largest circuit element
            cBuffer = [None] * Utilities.getMaxCktElementSize()
            # Sequence currents
            print 'Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1'
            # PD elements only
            for PDElem in ckt.getPDElements():
                if PDElem.isEnabled():
                    if DSSClassDefs.CLASSMASK & PDElem.getDSSObjType() != DSSClassDefs.CAP_ELEMENT:
                        # ignore caps
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
                            # only for terminal 1
                            CMax = 0.0
                            _1 = True
                            i = 0
                            while True:
                                if _1 is True:
                                    _1 = False
                                else:
                                    i += 1
                                if not (i < cls.Math.min(PDElem.getNPhases(), 3)):
                                    break
                                # check only first 3 phases
                                Iph[i] = cBuffer[((j - 1) * NCond) + i]
                                CMax = cls.Math.max(CMax, Iph[i].abs())
                            if PDElem.getNPhases() >= 3:
                                # Report symmetrical component currents for
                                MathUtil.phase2SymComp(Iph, I012)
                                I0 = I012[0].abs()
                                # Get abs values to report
                                I1 = I012[1].abs()
                                I2 = I012[2].abs()
                            else:
                                # Other than 3-phase
                                I0 = 0.0
                                I1 = Iph[0].abs()
                                # Ambiguous: Report only first phase
                                I2 = 0.0
                                CMax = I1
                            if (PDElem.getNormAmps() > 0.0) or (PDElem.getEmergAmps() > 0.0):
                                if (CMax > PDElem.getNormAmps()) or (CMax > PDElem.getEmergAmps()):
                                    writer.print_(Utilities.pad('\"' + PDElem.getDSSClassName() + '.' + PDElem.getName() + '\"', 22) + Separator + j)
                                    writer.print_(Separator + I1)
                                    if j == 0:
                                        # Only for 1st Terminal
                                        INormal = PDElem.getNormAmps()
                                        if INormal > 0.0:
                                            writer.print_(Separator + ((CMax / INormal) * 100.0))
                                        else:
                                            writer.print_(Separator + '     0.0')
                                        IEmerg = PDElem.getEmergAmps()
                                        if IEmerg > 0.0:
                                            writer.print_(Separator + ((CMax / IEmerg) * 100.0))
                                        else:
                                            writer.print_(Separator + '     0.0')
                                    else:
                                        writer.print_(Separator + '       0' + Separator + '       0')
                                    writer.print_(Separator + I2)
                                    if I1 > 0.0:
                                        writer.print_(Separator + ((100.0 * I2) / I1))
                                    else:
                                        writer.print_(Separator + '0.0')
                                    writer.print_(Separator + I0)
                                    if I1 > 0.0:
                                        writer.print_(Separator + ((100.0 * I0) / I1))
                                    else:
                                        writer.print_(Separator + '0.0')
                                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportUnserved(cls, fileName, UE_Only):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            print 'Load, Bus, kW, EEN_Factor,  UE_Factor'
            # Load
            for pLoad in ckt.getLoads():
                if pLoad.isEnabled():
                    doIt = False
                    if UE_Only:
                        if pLoad.getUnserved():
                            doIt = True
                    elif pLoad.getExceedsNormal():
                        doIt = True
                    if doIt:
                        writer.print_(pLoad.getName() + ', ')
                        writer.print_(pLoad.getBus(0) + ', ')
                        writer.print_(pLoad.getKWBase() + ', ')
                        writer.print_(pLoad.getEEN_Factor() + ', ')
                        writer.print_(pLoad.getUE_Factor())
                        print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportYprim(cls, fileName):
        """Exports YPrim matrices for all circuit elements."""
        ckt = DSSGlobals.activeCircuit
        if ckt is None:
            return
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            _0 = True
            k = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    k += 1
                if not (k < ckt.getNumDevices()):
                    break
                ckt.setActiveCktElement(ckt.getCktElements().get(k))
                if ckt.getActiveCktElement().isEnabled():
                    if (
                        isinstance(ckt.getActiveCktElement(), PDElement) or isinstance(ckt.getActiveCktElement(), PCElement)
                    ):
                        cktElem = ckt.getActiveCktElement()
                        print cktElem.getParentClass().getName() + '.' + cktElem.getName()
                        cValues = cktElem.getYPrimValues(DSSGlobals.ALL_YPRIM)
                        _1 = True
                        i = 0
                        while True:
                            if _1 is True:
                                _1 = False
                            else:
                                i += 1
                            if not (i < cktElem.getYorder()):
                                break
                            _2 = True
                            j = 0
                            while True:
                                if _2 is True:
                                    _2 = False
                                else:
                                    j += 1
                                if not (j < cktElem.getYorder()):
                                    break
                                writer.printf('%-13.10g, %-13.10g, ', cValues[i + ((j - 1) * cktElem.getYorder())].getReal(), cValues[i + ((j - 1) * cktElem.getYorder())].getImaginary())
                            print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportY(cls, fileName):
        """Exports system Y matrix in node order."""
        nBus = 0
        nnz = 0
        ckt = DSSGlobals.activeCircuit
        if ckt is None:
            return
        Y = ckt.getSolution().getY()
        if Y is None:
            DSSGlobals.doSimpleMsg('Y Matrix not built.', 222)
            return
        # this compresses the entries if necessary - no extra work if already solved
        # KLU.factorSparseMatrix(Y);
        # KLU.getNNZ(Y, nNZ);
        # KLU.getSize(Y, nBus);  // we should already know this
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            colPtr = [None] * (nBus + 1)
            rowIdx = [None] * nnz
            cVals = [None] * nnz
            # KLU.getCompressedMatrix(Y, nBus + 1, nNZ, ColPtr[0], RowIdx[0], cVals[0]);
            # Write out fully qualified bus names
            writer.printf('%d, ', ckt.getNumNodes())
            print 
            # for (i = 0; i < ckt.getNumNodes(); i++) {
            # 				j = ckt.getMapNodeToBus()[i].BusRef;
            # 				FPrinter.printf("%s.%-d, +j,", ckt.getBusList().get(j), ckt.getMapNodeToBus()[i].NodeNum);
            # 			}
            # 			FPrinter.println();

            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                j = ckt.getMapNodeToBus()[i].busRef
                writer.printf('%s.%-d, ', ckt.getBusList().get(j), ckt.getMapNodeToBus()[i].nodeNum)
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < ckt.getNumNodes()):
                        break
                    re = 0.0
                    im = 0.0
                    # search for a non-zero element [i, j]
                    _2 = True
                    p = colPtr[j - 1]
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            p += 1
                        if not (p < colPtr[j] - 1):
                            break
                        if rowIdx[p] + 1 == i:
                            re = cVals[p].getReal()
                            im = cVals[p].getImaginary()
                    writer.printf('%-13.10g, +j %-13.10g,', re, im)
                print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportSeqZ(cls, fileName):
        """Export symmetrical component impedances at each bus."""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            print 'Bus,  NumNodes, R1, X1, R0, X0, Z1, Z0, \"X1/R1\", \"X0/R0\"'
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                Z1 = ckt.getBuses()[i].getZsc1()
                Z0 = ckt.getBuses()[i].getZsc0()
                if Z1.getReal() != 0.0:
                    X1R1 = Z1.getImaginary() / Z1.getReal()
                else:
                    X1R1 = 1000.0
                if Z0.getReal() != 0.0:
                    X0R0 = Z0.getImaginary() / Z0.getReal()
                else:
                    X0R0 = 1000.0
                writer.printf('\"%s\", %d, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %8.4g, %8.4g', ckt.getBusList().get(i), ckt.getBuses()[i].getNumNodesThisBus(), Z1.getReal(), Z1.getImaginary(), Z0.getReal(), Z0.getImaginary(), Z1.abs(), Z0.abs(), X1R1, X0R0)
                print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportUUIDs(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            clsCode = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('linecode'))
            clsWire = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('wiredata'))
            clsGeom = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('linegeometry'))
            clsXfmr = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('xfmrcode'))
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            writer.printf('%s.%s %s', ckt.getDSSClassName(), ckt.getLocalName(), ckt.getID())
            print 
            for pNamed in ckt.getCktElements():
                writer.printf('%s.%s %s', pNamed.getDSSClassName(), pNamed.getLocalName(), pNamed.getID())
                print 
            pName = clsCode.getElementList().getFirst()
            # FIXME Make generic
            while pName is not None:
                writer.printf('%s.%s %s', pName.getDSSClassName(), pName.getLocalName(), pName.getID())
                print 
                pName = clsCode.getElementList().getNext()
            pName = clsWire.getElementList().getFirst()
            # FIXME Make generic
            while pName is not None:
                writer.printf('%s.%s %s', pName.getDSSClassName(), pName.getLocalName(), pName.getID())
                print 
                pName = clsWire.getElementList().getNext()
            pName = clsGeom.getElementList().getFirst()
            # FIXME Make generic
            while pName is not None:
                writer.printf('%s.%s %s', pName.getDSSClassName(), pName.getLocalName(), pName.getID())
                print 
                pName = clsGeom.getElementList().getNext()
            pName = clsXfmr.getElementList().getFirst()
            # FIXME Make generic
            while pName is not None:
                writer.printf('%s.%s %s', pName.getDSSClassName(), pName.getLocalName(), pName.getID())
                print 
                pName = clsXfmr.getElementList().getNext()
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportCounts(cls, fileName):
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            print 'Format: DSS Class Name = Instance Count'
            print 
            for cls in DSSGlobals.DSSClassList:
                writer.printf('%s = %d', cls.getName(), cls.getElementCount())
                print 
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportSummary(cls, fileName):
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            if File(fileName).exists():
                f = FileWriter(fileName, True)
                # append
                writer = PrintWriter(f)
            else:
                f = FileWriter(fileName)
                writer = PrintWriter(f)
                # Create and write the header
                writer.print_('DateTime, CaseName, ')
                writer.print_('Status, Mode, Number, LoadMult, NumDevices, NumBuses, NumNodes')
                writer.print_(', Iterations, ControlMode, ControlIterations')
                writer.print_(', MostIterationsDone')
                if ckt is not None:
                    if ckt.isSolved() and not ckt.isBusNameRedefined():
                        writer.print_(', Year, Hour, MaxPuVoltage, MinPuVoltage, TotalMW, TotalMvar')
                        writer.print_(', kWLosses, pctLosses, kvarLosses, Frequency')
                print 
            cal = Calendar.getInstance()
            sdf = SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
            writer.printf('\"%s\", ', sdf.format(cal.getTime()))
            if ckt is not None:
                writer.printf('%s, ', ckt.getCaseName())
            else:
                writer.print_('NONE, ')
            if ckt.isSolved():
                writer.print_('SOLVED')
            else:
                writer.print_('UnSolved')
            writer.printf(', %s', Utilities.getSolutionModeID())
            writer.printf(', %d', ckt.getSolution().getNumberOfTimes())
            writer.printf(', %8.3f', ckt.getLoadMultiplier())
            writer.printf(', %d', ckt.getNumDevices())
            writer.printf(', %d', ckt.getNumBuses())
            writer.printf(', %d', ckt.getNumNodes())
            writer.printf(', %d', ckt.getSolution().getIteration())
            writer.printf(', %s', Utilities.getControlModeID())
            writer.printf(', %d', ckt.getSolution().getControlIteration())
            writer.printf(', %d', ckt.getSolution().getMostIterationsDone())
            if ckt is not None:
                if ckt.isSolved() and not ckt.isBusNameRedefined():
                    writer.printf(', %d', ckt.getSolution().getYear())
                    writer.printf(', %d', ckt.getSolution().getIntHour())
                    writer.printf(', %-.5g', Utilities.getMaxPUVoltage())
                    writer.printf(', %-.5g', Utilities.getMinPUVoltage(True))
                    cPower = Utilities.getTotalPowerFromSources().multiply(1e-06)
                    # MVA
                    writer.printf(', %-.6g', cPower.getReal())
                    writer.printf(', %-.6g', cPower.getImaginary())
                    cLosses = ckt.getLosses().multiply(1e-06)
                    if cPower.getReal() != 0.0:
                        writer.printf(', %-.6g, %-.4g', cLosses.getReal(), (cLosses.getReal() / cPower.getReal()) * 100.0)
                    else:
                        writer.printf('Total Active Losses:   ****** MW, (**** %%)')
                    writer.printf(', %-.6g', cLosses.getImaginary())
                    writer.printf(', %-g', ckt.getSolution().getFrequency())
            print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportBusCoords(cls, fileName):
        """Export bus x, y coordinates."""
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
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
                    writer.printf('%s, %-13.11g, %-13.11g', Utilities.checkForBlanks(ckt.getBusList().get(i)), ckt.getBuses()[i].getX(), ckt.getBuses()[i].getY())
                    print 
            DSSGlobals.globalResult = fileName
            writer.close()
            f.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

    @classmethod
    def exportCDPSM(cls, fileName, Profile):
        # TODO Auto-generated method stub
        pass

    @classmethod
    def writeNewLine(cls, f, cktElementName, distFromMeter1, puV1, distFromMeter2, puV2, colorCode, thickness, lineType, markCenter, centerMarkerCode, nodeMarkerCode, nodeMarkerWidth):
        f.printf('%s, %.6g, %.6g, %.6g, %.6g,', cktElementName, distFromMeter1, puV1, distFromMeter2, puV2)
        f.printf('%d, %d, %d, ', colorCode, thickness, lineType)
        f.printf('%d, ', markCenter)
        f.printf('%d, %d, %d', centerMarkerCode, nodeMarkerCode, nodeMarkerWidth)
        print 

    @classmethod
    def exportProfile(cls, fileName, phasesToPlot):
        puV1 = 0
        puV2 = 0
        Linetype = 0
        ckt = DSSGlobals.activeCircuit
        # TODO: handle exception
        try:
            f = FileWriter(fileName)
            writer = PrintWriter(f)
            writer.print_('Name, Distance1, puV1, Distance2, puV2, Color, Thickness, Linetype, Markcenter, Centercode, NodeCode, NodeWidth,')
            # New graph created before this routine is entered
            _0 = phasesToPlot
            _1 = False
            while True:
                if _0 == DSSGlobals.PROFILELL:
                    _1 = True
                    s = 'L-L Voltage Profile'
                    break
                if (_1 is True) or (_0 == DSSGlobals.PROFILELLALL):
                    _1 = True
                    s = 'L-L Voltage Profile'
                    break
                if (_1 is True) or (_0 == DSSGlobals.PROFILELLPRI):
                    _1 = True
                    s = 'L-L Voltage Profile'
                    break
                if True:
                    _1 = True
                    s = 'L-N Voltage Profile'
                    break
                break
            print 'Title=' + s + ', Distance in km'
            iEnergyMeter = DSSGlobals.energyMeterClass.getFirst()
            while iEnergyMeter >= 0:
                activeEnergyMeter = DSSGlobals.energyMeterClass.getActiveObj()
                # Go down each branch list and draw a line
                presentCktElement = activeEnergyMeter.getBranchList().getFirst()
                while presentCktElement is not None:
                    if Utilities.isLineElement(presentCktElement):
                        bus1 = ckt.getBuses()[presentCktElement.getTerminals()[0].busRef]
                        bus2 = ckt.getBuses()[presentCktElement.getTerminals()[1].busRef]
                        # Now determin which phase to plot
                        if bus1.getKVBase() > 0.0 and bus2.getKVBase() > 0.0:
                            _2 = phasesToPlot
                            _3 = False
                            while True:
                                if _2 == DSSGlobals.PROFILE3PH:
                                    _3 = True
                                    if presentCktElement.getNPhases() >= 3 and bus1.getKVBase() > 1.0:
                                        _4 = True
                                        iphs = 0
                                        while True:
                                            if _4 is True:
                                                _4 = False
                                            else:
                                                iphs += 1
                                            if not (iphs < 3):
                                                break
                                            puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0
                                            puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0
                                            cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, 0, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                    # Plot all phases present (between 1 and 3)
                                if (_3 is True) or (_2 == DSSGlobals.PROFILEALL):
                                    _3 = True
                                    _5 = True
                                    iphs = 0
                                    while True:
                                        if _5 is True:
                                            _5 = False
                                        else:
                                            iphs += 1
                                        if not (iphs < 3):
                                            break
                                        if bus1.findIdx(iphs) >= 0 and bus2.findIdx(iphs) >= 0:
                                            if bus1.getKVBase() < 1.0:
                                                Linetype = 2
                                            else:
                                                Linetype = 0
                                            puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0
                                            puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0
                                            cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                    # Plot all phases present (between 1 and 3) for Primary only
                                if (_3 is True) or (_2 == DSSGlobals.PROFILEALLPRI):
                                    _3 = True
                                    if bus1.getKVBase() > 1.0:
                                        _6 = True
                                        iphs = 0
                                        while True:
                                            if _6 is True:
                                                _6 = False
                                            else:
                                                iphs += 1
                                            if not (iphs < 3):
                                                break
                                            if bus1.findIdx(iphs) >= 0 and bus2.findIdx(iphs) >= 0:
                                                if bus1.getKVBase() < 1.0:
                                                    Linetype = 2
                                                else:
                                                    Linetype = 0
                                                puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0
                                                puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0
                                                cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                if (_3 is True) or (_2 == DSSGlobals.PROFILELL):
                                    _3 = True
                                    if presentCktElement.getNPhases() >= 3:
                                        _7 = True
                                        iphs = 0
                                        while True:
                                            if _7 is True:
                                                _7 = False
                                            else:
                                                iphs += 1
                                            if not (iphs < 3):
                                                break
                                            iphs2 = iphs + 1
                                            if iphs2 >= 3:
                                                iphs2 = 1
                                                # TODO Check zero based indexing
                                            if (
                                                bus1.findIdx(iphs) >= 0 and bus2.findIdx(iphs) >= 0 and bus1.findIdx(iphs2) >= 0 and bus2.findIdx(iphs2) >= 0
                                            ):
                                                if bus1.getKVBase() < 1.0:
                                                    Linetype = 2
                                                else:
                                                    Linetype = 0
                                                sol = ckt.getSolution()
                                                puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract(sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))]).abs() / bus1.getKVBase() / 1732.0
                                                puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract(sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))]).abs() / bus2.getKVBase() / 1732.0
                                            cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                if (_3 is True) or (_2 == DSSGlobals.PROFILELLALL):
                                    _3 = True
                                    _8 = True
                                    iphs = 0
                                    while True:
                                        if _8 is True:
                                            _8 = False
                                        else:
                                            iphs += 1
                                        if not (iphs < 3):
                                            break
                                        iphs2 = iphs + 1
                                        if iphs2 >= 3:
                                            iphs2 = 0
                                            # TODO Check zero based indexing
                                        if (
                                            bus1.findIdx(iphs) >= 0 and bus2.findIdx(iphs) >= 0 and bus1.findIdx(iphs2) >= 0 and bus2.findIdx(iphs2) >= 0
                                        ):
                                            if bus1.getKVBase() < 1.0:
                                                Linetype = 2
                                            else:
                                                Linetype = 0
                                            sol = ckt.getSolution()
                                            puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract(sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))]).abs() / bus1.getKVBase() / 1732.0
                                            puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract(sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))]).abs() / bus2.getKVBase() / 1732.0
                                        cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                if (_3 is True) or (_2 == DSSGlobals.PROFILELLPRI):
                                    _3 = True
                                    if bus1.getKVBase() > 1.0:
                                        _9 = True
                                        iphs = 0
                                        while True:
                                            if _9 is True:
                                                _9 = False
                                            else:
                                                iphs += 1
                                            if not (iphs < 3):
                                                break
                                            iphs2 = iphs + 1
                                            if iphs2 >= 3:
                                                iphs2 = 0
                                                # TODO Check zero based indexing
                                            if (
                                                bus1.findIdx(iphs) >= 0 and bus2.findIdx(iphs) >= 0 and bus1.findIdx(iphs2) >= 0 and bus2.findIdx(iphs2) >= 0
                                            ):
                                                if bus1.getKVBase() < 1.0:
                                                    Linetype = 2
                                                else:
                                                    Linetype = 0
                                                sol = ckt.getSolution()
                                                puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract(sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))]).abs() / bus1.getKVBase() / 1732.0
                                                puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract(sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))]).abs() / bus2.getKVBase() / 1732.0
                                            cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                if True:
                                    _3 = True
                                    iphs = phasesToPlot
                                    if bus1.findIdx(iphs) > 0 and bus2.findIdx(iphs) > 0:
                                        if bus1.getKVBase() < 1.0:
                                            Linetype = 2
                                        else:
                                            Linetype = 0
                                        puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0
                                        puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0
                                        cls.writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2, iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth())
                                    break
                                break
                            # 3ph only
                    presentCktElement = activeEnergyMeter.getBranchList().goForward()
                iEnergyMeter = DSSGlobals.energyMeterClass.getNext()
            DSSGlobals.globalResult = fileName
            f.close()
            writer.close()
        except IOException, e:
            pass # astStmt: [Stmt([]), None]

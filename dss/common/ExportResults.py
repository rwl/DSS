# Copyright (C) 2010 Richard Lincoln
#
# This library is free software: you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation: either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY: without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library: if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

from numpy import array, angle

from dss.common.DSSGlobals import \
    ActiveCircuit, CZERO, SQRT3, GlobalResult, CRLF

from dss.MathUtil import PctNemaUnbalance, Phase2SymComp
from dss.delivery.PDElement import PDElement


def ExportVoltages(FileNm):
    """Export Symmetrical Component bus voltages"""
    F = file
    i, j = 0
    nref = 0
    Vph = array(range(3), dtype=complex)
    V012 = array(range(3), dtype=complex)

    V0, V1, V2, Vpu, V2V1, V0V1 = 0.0
    Vresidual = complex
    V_NEMA = 0.0

    try:
        F = open(FileNm, "wb")

        F.write('Bus,  V1,  p.u.,Base kV, V2, %V2/V1, V0, %V0/V1, '
                'Vresidual, %NEMA\n')
        ac = ActiveCircuit
        for i in range(ac.NumBuses):
            if ac.Buses[i].NumNodesThisBus < 3:
                V0 = 0.0
                V2 = 0.0
                V_NEMA = 0.0
                if (ac.Buses[i].NumNodesThisBus == 1) and ac.PositiveSequence:
                    # first node
                    nref = ac.Buses[i].GetRef(0)
                    Vph[0] = ActiveCircuit.Solution.NodeV[nref]
                    V1 = abs(Vph[0])
                else:
                    V1 = 0.0
            else:
                sol = ActiveCircuit.Solution
                bus = ac.Buses[i]
                for j in range(3):
                    # first nodes named  1, 2, 3
                    Vph[j] = sol.NodeV[bus.GetRef(bus.FindIdx(j))]

                Phase2SymComp(Vph, V012)

                V0 = abs(V012[0])
                V1 = abs(V012[1])
                V2 = abs(V012[2])

                V_NEMA = PctNemaUnbalance(Vph)

            if ac.Buses[i].kvbase != 0.0:
                Vpu = 0.001 * V1 / ac.Buses[i].kVBase
            else:
                Vpu = 0.0

            if V1 > 0.0:
                V2V1 = 100.0 * V2 / V1
                V0V1 = 100.0 * V0 / V1
            else:
                V2V1 = 0.0
                V0V1 = 0.0

            Vresidual = CZERO
            sol = ActiveCircuit.Solution
            for j in range(ac.Buses[i].NumNodesThisBus):
                Vresidual += sol.NodeV[ ac.Buses[i].GetRef(j) ]

            F.write('"%s", %10.6g, %9.5g, %8.2f, %10.6g, %8.4g, %10.6g, %8.4g, '
                    '%10.6g, %8.4g' % (ac.BusList[i]), V1, Vpu,
                    (ac.Buses[i].kvbase * SQRT3), V2, V2V1, V0, V0V1,
                    abs(Vresidual), V_NEMA)

        GlobalResult = FileNm
    finally:
        F.close()


def ExportSeqVoltages(FileNm):
    """Export Symmetrical Component bus voltages"""
    MaxNumNodes = 0
    F = file
    i, j, jj = 0
    BusName = ""
    Volts = CZERO
    nref = 0
    NodeIdx = 0
    Vmag, Vpu = 0.0

    # Find max nodes at a bus
    MaxNumNodes = 0
    ac = ActiveCircuit
    for i in range(ac.NumBuses):
        MaxNumNodes = max(MaxNumNodes, ac.Buses[i].NumNodesThisBus)

    try:
        F = open(FileNm, "wb")

        F.write('Bus, BasekV')
        for i in range(MaxNumNodes):
            F.write(', Node%d, Magnitude%d, Angle%d, pu%d' % (i, i, i, i))
        F.write(CRLF)

        ac = ActiveCircuit
        for i in range(ac.NumBuses):
            BusName = ac.BusList[i]
            F.write('"%s", %.5g' % (BusName, ac.Buses[i].kVBase * SQRT3))

            jj = 1
            bus = ac.Buses[i]
            for _ in range(bus.NumNodesThisBus):
                while NodeIdx <= 0:
                    NodeIdx = bus.FindIdx(jj) # Try to find nodes in order
                    jj += 1
                nref = bus.GetRef(NodeIdx)
                Volts = ActiveCircuit.Solution.NodeV[nref]
                Vmag = abs(Volts)
                if bus.kVBase != 0.0:
                    Vpu = 0.001 * Vmag / bus.kVBase
                else:
                    Vpu = 0.0

            F.write(', %d, %10.6g, %6.1f, %9.5g' %
                    (bus.GetNum(NodeIdx), Vmag, angle(Volts), Vpu))
            ## Zero Fill row
            for _ in range(ac.Buses[i].NumNodesThisBus + 1, MaxNumNodes):
                F.write(', 0, 0, 0, 0')
            F.write(CRLF)

        GlobalResult = FileNm

    finally:
        F.close()


def CalcAndWriteSeqCurrents(F, j=0, pelem=None, cBuffer=None, DoRatings=False):
    I0,I1,I2, I2I1, I0I1, iNormal,iEmerg = 0.0
    i,k,NCond = 0
    Iph, I012 = array(range(3), dtype=complex)
    Iresidual = CZERO
    I_NEMA = 0.0

    NCond = pelem.NConds
    if (pelem.Nphases >= 3):

        for i in range(3):
            k = (j - 1) * NCond + i
            Iph[i] = cBuffer[k]

        Phase2SymComp(Iph, I012)
        I0 = abs(I012[0])
        I1 = abs(I012[1])
        I2 = abs(I012[2])

        I_NEMA = PctNemaUnbalance(Iph)

    else:
        I0 = 0.0
        I1 = 0.0
        I2 = 0.0
        I_NEMA = 0.0
        if ActiveCircuit.PositiveSequence:    # Use phase 1 only
            I1 = abs(Iph[0])

    if I1 > 0.0:
        I2I1 = 100.0 * I2 / I1
        I0I1 = 100.0 * I0 / I1
    else:
        I2I1 = 0.0
        I0I1 = 0.0

    if DoRatings and (j == 1):  # Only for 1st Terminal
        iNormal = PDElement.NormAmps
        if iNormal > 0.0:
            iNormal = I1 / iNormal * 100.0
        iEmerg = PDElement.EmergAmps
        if iEmerg > 0.0:
            iEmerg = I1 / iEmerg * 100.0
    else:
        iNormal = 0.0
        iEmerg = 0.0

    Iresidual = CZERO
    for i in range(NCond):
        Iresidual += cBuffer[i]


    F.write('"%s", %3d, %10.6g, %8.4g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g, ' \
            '%10.6g, %8.4g\n' % ((pelem.DSSClassName + '.' + pelem.Name),
                               j, I1, iNormal, iEmerg, I2, I2I1, I0,
                               I0I1, abs(Iresidual), I_NEMA))


def ExportSeqCurrents(FileNm):
    F = None
    j = 0
    PElem = None
    PDElem = None
    PCElem = None
    cBuffer = []  # Allocate to max total conductors

    cBuffer = None

    try:
        F = open(FileNm, "wb")

        ## Sequence Currents
        F.write('Element, Terminal,  I1, %Normal, %Emergency, I2, ' \
                '%I2/I1, I0, %I0/I1, Iresidual, %NEMA\n')

        # Allocate cBuffer big enough for largest circuit element
#        Getmem(cBuffer, sizeof(cBuffer[1])* GetMaxCktElementSize)

        # Sources First
        PElem = ActiveCircuit.Sources.next()
        while PElem != None:
            if (PElem.Enabled):
                PElem.GetCurrents(cBuffer)
                for j in range(PElem.Nterms):
                    CalcAndWriteSeqCurrents(F, j, PElem, cBuffer, False)
            pelem = ActiveCircuit.Sources.next()

        # PDELEMENTS Next
        PDElem = ActiveCircuit.PDElements.next()

        while PDElem is not None:
            if (PDElem.Enabled):
                PDElem.GetCurrents(cBuffer)
                for j in range(PDElem.Nterms):
                    CalcAndWriteSeqCurrents(F, j, PDElem, cBuffer, True)
                PDElem = ActiveCircuit.PDElements.next()

        # PCelemENTS next
        PCElem = ActiveCircuit.PCelements.next()

        while PCElem is not None:
            if (PCElem.Enabled):
                PCElem.GetCurrents(cBuffer)
                for j in range(PCElem.Nterms):
                    CalcAndWriteSeqCurrents(F, j, PCElem, cBuffer, False)
            PCElem = ActiveCircuit.PCelements.next()

        # Faults Next
        PElem = ActiveCircuit.Faults.next()
        while pelem is not None:
            if pelem.Enabled:
                pelem.GetCurrents(cBuffer)
                for j in range(pelem.Nterms):
                    CalcAndWriteSeqCurrents(F, j, pelem, cBuffer, False)
            pelem = ActiveCircuit.Faults.next()

        GlobalResult = FileNm

    finally:
#        if Assigned(cBuffer):
#            Freemem(cBuffer)
        F.close()


def CalcAndWriteCurrents(F, PElem, cBuffer, CondWidth, TermWidth=0):
    i,j,k = 0
    Iresid = CZERO

    k = 0
    F.write('%s', (PElem.DSSClassName + '.' + PElem.Name))
    for j in range(PElem.Nterms):
        Iresid = CZERO
        for i in range(PElem.NConds):
            k += 1
            F.write(', %10.6g, %8.2f' % (abs(cBuffer[k]), angle(cBuffer[k])))
            Iresid += cBuffer[k]
        for i in range(PElem.Nconds + 1, CondWidth):
            F.write(', %10.6g, %8.2f' % (0.0, 0.0))
        F.write(', %10.6g, %8.2f' % (abs(Iresid), angle(Iresid)))

    ## Filler if no. terms less than termwidth
    for j in range(PElem.Nterms + 1, TermWidth):
        for i in range(CondWidth + 1):
            F.write(', %10.6g, %8.2f' % (0.0, 0.0))
    F.write(CRLF)


def CalcAndWriteMaxCurrents(F, pElem, cBuffer):
    i = 0
    Currmag, MaxCurrent = 0.0
    LocalPower = CZERO

    F.write('%s.%s' % (pElem.DSSClassName, pElem.Name))
    MaxCurrent = 0.0
    for i in range(pElem.Nphases):
        Currmag = abs(cBuffer[i])
        if Currmag  > MaxCurrent:
            MaxCurrent =  Currmag
    #----pElem.ActiveTerminalIdx = 1
    LocalPower = pElem.Power[0] * 0.001
    if (pElem.NormAmps == 0.0) or (pElem.EmergAmps == 0.0):
        F.write(', %10.6g, %8.2f, %8.2f' % (MaxCurrent, 0.0 , 0.0))
    else:
        F.write(', %10.6g, %8.2f, %8.2f' %
                (MaxCurrent,
                 MaxCurrent / pElem.NormAmps * 100.0 ,
                 MaxCurrent / pElem.Emergamps * 100.0))
    F.write(', %10.6g, %10.6g, %d, %d, %d' %
            (LocalPower.re, LocalPower.im,
             pElem.NumCustomers, pElem.TotalCustomers, pElem.NPhases))
    ac = ActiveCircuit
    F.write(', %-.3g ' %
        (ac.Buses[ac.MapNodeToBus[pElem.NodeRef[0]].BusRef].kVBase))
    F.write(CRLF)


def ExportCurrents(FileNm):
    F = None
    cBuffer = array([], dtype=complex)
    pElem = None
    MaxCond, MaxTerm = 0
    i, j = 0

    cBuffer = None

    try:
        F=open(FileNm, "Wb")

#        Getmem(cBuffer, sizeof(cBuffer[0]) * GetMaxCktElementSize)

        ## Calculate the width of the file
        MaxCond = 1
        MaxTerm = 2
        pElem = ActiveCircuit.CktElements.next()
        while pElem is not None:
            if pElem.NTerms > MaxTerm: MaxTerm = pElem.NTerms
            if pElem.NConds > MaxCond: MaxCond = pElem.NConds
            pElem = ActiveCircuit.CktElements.next()

        ## Branch Currents
        F.write('Element')
        for i in range(MaxTerm):
            for j in range(MaxCond):
                F.write(', I%d_%d, Ang%d_%d' % (i, j, i, j))
            F.write(', Iresid%d, AngResid%d' % (i ,i))
        F.write(CRLF)

        # Sources first
        pElem = ActiveCircuit.Sources.next()
        while pElem is not None:
            if pElem.Enabled:
                pElem.GetCurrents(cBuffer)
                CalcAndWriteCurrents(F, pElem, cBuffer, MaxCond, MaxTerm)
            pElem = ActiveCircuit.Sources.next()

        # PDELEMENTS first
        pElem = ActiveCircuit.PDElements.next()
        while pElem is not None:
            if pElem.Enabled:
                pElem.GetCurrents(cBuffer)
                CalcAndWriteCurrents(F, pElem, cBuffer, MaxCond, MaxTerm)
            pElem = ActiveCircuit.PDElements.next()

        # Faults
        pElem = ActiveCircuit.Faults.next()
        while pElem is not None:
            if pElem.Enabled:
                pElem.GetCurrents(cBuffer)
                CalcAndWriteCurrents(F, pElem, cBuffer, MaxCond, MaxTerm)
            pElem = ActiveCircuit.Faults.next()

        # PCELEMENTS next
        pElem = ActiveCircuit.PCElements.next()
        while pElem is not None:
            if pElem.Enabled:
                pElem.GetCurrents(cBuffer)
                CalcAndWriteCurrents(F, pElem, cBuffer, MaxCond, MaxTerm)
            pElem = ActiveCircuit.PCElements.next()

        GlobalResult = FileNm

    finally:
#        if Assigned(cBuffer):
#            Freemem(cBuffer)
        F.close()


def ExportPowers(FileNm, opt=0):
    """Opt = 0: kVA, opt = 1: MVA"""
    F = None
    Nterm, j = 0
    PDElem = None
    PCElem = None
    S = CZERO
    Separator = ""

    try:
        F = open(FileNm, "")
        Separator = ', '

        if opt == 1:
            F.write('Element, Terminal, P(MW), Q(Mvar), P_Normal, '
                    'Q_Normal, P_Emergency, Q_Emergency\n')
        else:
            F.write('Element, Terminal, P(kW), Q(kvar),  P_Normal, '
                    'Q_Normal, P_Emergency, Q_Emergency')

        # PDELEMENTS first
        PDElem = ActiveCircuit.PDElements.next()

        while PDElem is not None:
            if PDElem.Enabled:
                Nterm = PDElem.Nterms

#                for j in range(NTerm):
#                    F.write( Pad('"'+ PDelem.DSSClassName + '.' + PDElem.Name + '"', 24), Separator, j:3)
#                    #----PDElem.ActiveTerminalIdx = j
#                    S = PDElem.Power[j]
#                    if opt == 1: S = S * 0.001
#                    F.write(Separator, S.real * 0.001:11:1)
#                    F.write(Separator, S.imag * 0.001:11:1)
#                    if j == 1:
#                        #----PDelem.ActiveTerminalIdx = 1
#                        S = PDElem.ExcesskVANorm[0]
#                        if opt == 1: S = S * 0.001
#                        F.write(Separator, abs(S.real):11:1)
#                        F.write(Separator, abs(S.imag):11:1)
#                        S = PDElem.ExcesskVAEmerg[0]
#                        if opt == 1: S = S * 0.001)
#                        F.write(Separator, abs(S.real):11:1)
#                        F.write(Separator, abs(S.imag):11:1)
#                        F.write(CRLF)
            PDElem = ActiveCircuit.PDElements.next()

        # PCELEMENTS Next
        PCElem = ActiveCircuit.PCElements.next()

        while PCElem != None:
            if PCElem.Enabled:
                Nterm = PCElem.Nterms

#                for j in range(NTerm):
#                    F.write( Pad('"'+PCElem.DSSClassName + '.' + PCElem.Name+'"', 24), Separator, j:3)
#                    #----pcElem.ActiveTerminalIdx := j;
#                    S = PCElem.Power[j]
#                    if opt == 1: S = S * 0.001
#                    F.write(Separator, S.real * 0.001:11:1)
#                    F.write(Separator, S.imag * 0.001:11:1)
#                    F.write(CRLF)
            PCElem = ActiveCircuit.PCElements.next()

        GlobalResult = FileNm

    finally:
        F.close()


def ExportEstimation(Filenm):
    pass
def ExportPbyphase(FileNm, opt=0):
    pass
def ExportSeqPowers(FileNm, opt):
    pass
def ExportFaultStudy(FileNm):
    pass
def ExportMeters(FileNm):
    pass
def ExportGenMeters(FileNm):
    pass
def ExportLoads(FileNm ):
    pass
def ExpopassrtCapacity(FileNm):
    pass
def ExportOverloads(FileNm):
    pass
def ExportUnserved(FileNm, UE_Only):
    pass
def ExportYprim(FileNm):
    pass
def ExportY(FileNm):
    pass
def ExportSeqZ(FileNm):
    pass
def ExportBusCoords(FileNm):
    pass
def ExportLosses(FileNm):
    pass
def ExportGuids(FileNm):
    pass
def ExportCounts(FileNm):
    pass
def ExportSummary(FileNm):
    pass

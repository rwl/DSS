from dss.meter.impl.ReduceAlgs import (ReduceAlgs,)
from dss.shared.impl.CktTreeImpl import (CktTreeImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.meter.impl.EnergyMeterImpl import (EnergyMeterImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.impl.MeterElementImpl import (MeterElementImpl,)
from dss.meter.EnergyMeterObj import (EnergyMeterObj,)
from dss.parser.impl.Parser import (Parser,)
from dss.meter.EnergyMeter import (EnergyMeter,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class EnergyMeterObjImpl(MeterElementImpl, EnergyMeterObj):
    deltaHrs = None
    firstSampleAfterReset = None
    excessFlag = None
    zoneIsRadial = None
    voltageUEOnly = None
    localOnly = None
    hasFeeder = None
    losses = None
    lineLosses = None
    xfmrLosses = None
    seqLosses = None
    threePhaseLosses = None
    VBaseLosses = None
    phaseVoltageReport = None
    feederObj = None
    # not used at present
    definedZoneList = None
    definedZoneListSize = None
    # Limits on the entire load in the zone for networks where UE cannot be
    # determined by the individual branches.

    maxZoneKVANorm = None
    maxZoneKVAEmerg = None
    # Voltage bases in the meter zone
    VBaseTotalLosses = None
    # allocated array
    VBaseLineLosses = None
    VBaseLoadLosses = None
    VBaseNoLoadLosses = None
    VBaseLoad = None
    VBaseList = None
    VBaseCount = None
    maxVBaseCount = None
    # Arrays for phase voltage report
    VPhaseMax = None
    VPhaseMin = None
    VPhaseAccum = None
    VPhaseAccumCount = None
    VPhaseFile = None
    VPhaseReportFileIsOpen = None
    # Demand interval file variables
    DI_File = None
    thisMeterDI_FileIsOpen = None
    registerNames = [None] * super(EnergyMeterObjImpl).NumEMRegisters
    branchList = None
    # all circuit elements in meter"s zone
    registers = [None] * super(EnergyMeterObjImpl).NumEMRegisters
    derivatives = [None] * super(EnergyMeterObjImpl).NumEMRegisters
    totalsMask = [None] * super(EnergyMeterObjImpl).NumEMRegisters

    def __init__(self, parClass, energyMeterName):
        super(EnergyMeterObjImpl, self)(parClass)
        ckt = DSSGlobals.activeCircuit
        self.setName(energyMeterName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # ENERGY_METER;
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.excessFlag = True
        # default to excess energy for UE
        self.elementName = 'Vsource.' + ckt.getCktElements().get(0).getName()
        # default to first circuit element (source)
        self.meteredElement = None
        self.branchList = None
        # initialize to null, set later when inited
        self.thisMeterDI_FileIsOpen = False
        self.VPhaseReportFileIsOpen = False
        self.initPropertyValues(0)
        # max zone kW limits ignored unless the user provides a rating
        self.maxZoneKVANorm = 0.0
        self.maxZoneKVAEmerg = 0.0
        self.zoneIsRadial = True
        self.hasFeeder = False
        self.feederObj = None
        # initialise to not assigned
        self.definedZoneList = None
        self.definedZoneListSize = 0
        self.losses = True
        # Loss reporting switches
        self.lineLosses = True
        self.xfmrLosses = True
        self.seqLosses = True
        self.threePhaseLosses = True
        self.VBaseLosses = True
        self.phaseVoltageReport = False
        self.VBaseList = None
        self.VBaseTotalLosses = None
        self.VBaseLineLosses = None
        self.VBaseLoadLosses = None
        self.VBaseNoLoadLosses = None
        self.VBaseLoad = None
        self.VBaseCount = 0
        self.maxVBaseCount = (EnergyMeter.NUM_EM_REGISTERS - EnergyMeter.REG_VBASE_START) / 5
        self.VBaseList = [None] * self.maxVBaseCount
        self.VBaseTotalLosses = [None] * self.maxVBaseCount
        self.VBaseLineLosses = [None] * self.maxVBaseCount
        self.VBaseLoadLosses = [None] * self.maxVBaseCount
        self.VBaseNoLoadLosses = [None] * self.maxVBaseCount
        self.VBaseLoad = [None] * self.maxVBaseCount
        # arrays for phase voltage report
        self.VPhaseMax = [None] * self.maxVBaseCount * 3
        self.VPhaseMin = [None] * self.maxVBaseCount * 3
        self.VPhaseAccum = [None] * self.maxVBaseCount * 3
        self.VPhaseAccumCount = [None] * self.maxVBaseCount * 3
        self.localOnly = False
        self.voltageUEOnly = False
        # set register names  that correspond to the register quantities
        self.registerNames[0] = 'kWh'
        self.registerNames[1] = 'kvarh'
        self.registerNames[2] = 'Max kW'
        self.registerNames[3] = 'Max kVA'
        self.registerNames[4] = 'Zone kWh'
        self.registerNames[5] = 'Zone kvarh'
        self.registerNames[6] = 'Zone Max kW'
        self.registerNames[7] = 'Zone Max kVA'
        self.registerNames[8] = 'Overload kWh Normal'
        self.registerNames[9] = 'Overload kWh Emerg'
        self.registerNames[10] = 'Load EEN'
        self.registerNames[11] = 'Load UE'
        self.registerNames[12] = 'Zone Losses kWh'
        self.registerNames[13] = 'Zone Losses kvarh'
        self.registerNames[14] = 'Zone Max kW Losses'
        self.registerNames[15] = 'Zone Max kvar Losses'
        self.registerNames[16] = 'Load Losses kWh'
        self.registerNames[17] = 'Load Losses kvarh'
        self.registerNames[18] = 'No Load Losses kWh'
        self.registerNames[19] = 'No Load Losses kvarh'
        self.registerNames[20] = 'Max kW Load Losses'
        self.registerNames[21] = 'Max kW No Load Losses'
        self.registerNames[22] = 'Line Losses'
        self.registerNames[23] = 'Transformer Losses'
        self.registerNames[24] = 'Line Mode Line Losses'
        self.registerNames[25] = 'Zero Mode Line Losses'
        self.registerNames[26] = '3-phase Line Losses'
        self.registerNames[27] = '1- and 2-phase Line Losses'
        self.registerNames[28] = 'Gen kWh'
        self.registerNames[29] = 'Gen kvarh'
        self.registerNames[30] = 'Gen Max kW'
        self.registerNames[31] = 'Gen Max kVA'
        # Registers for capturing losses by base voltage, names assigned later
        _0 = True
        i = EnergyMeter.REG_VBASE_START + 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            self.registerNames[i] = ''
        self.resetRegisters()
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            self.totalsMask[i] = 1.0
        self.allocateSensorArrays()
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.sensorCurrent[i] = 400.0
            # recalcElementData();

    @classmethod
    def jiIndex(cls, i, j):
        return ((j - 1) * 3) + i

    def recalcElementData(self):
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # monitored element must already exist
            self.meteredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            # MeteredElement must be a PDElement
            if not isinstance(self.meteredElement, PDElement):
                self.meteredElement = None
                # element not found
                DSSGlobals.doErrorMsg('EnergyMeter: \"' + self.getName() + '\"', 'Circuit element \"' + self.elementName + '\" is not a Power Delivery (PD) element.', ' Element must be a PD element.', 525)
                return
            if self.meteredTerminal >= self.meteredElement.getNTerms():
                # TODO Check zero based indexing
                DSSGlobals.doErrorMsg('EnergyMeter: \"' + self.getName() + '\"', 'Terminal no. \"' + String.valueOf.valueOf(self.meteredTerminal) + '\" does not exist.', 'Respecify terminal no.', 524)
            else:
                if self.meteredElementChanged:
                    # Sets name of i-th terminal's connected bus in monitor's bus list
                    # This value will be used to set the nodeRef array (see takeSample)
                    self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
                    self.setNPhases(self.meteredElement.getNPhases())
                    self.nConds = self.meteredElement.getNConds()
                    self.allocateSensorArrays()
                    # if we come through here, throw branch list away
                    self.branchList = None
                if self.hasFeeder:
                    self.makeFeederObj()
                    # OK to call multiple times
        else:
            self.meteredElement = None
            # element not found
            DSSGlobals.doErrorMsg('EnergyMeter: \"' + self.getName() + '\"', 'Circuit element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 525)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.meteredElement is not None:
            self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
            self.setNPhases(self.meteredElement.getNPhases())
            self.setNConds(self.meteredElement.getNConds())
            self.allocateSensorArrays()
            self.branchList = None
        if self.hasFeeder:
            self.makeFeederObj()
        super(EnergyMeterObjImpl, self).makePosSequence()

    def makeVPhaseReportFileName(self):
        return DSSGlobals.energyMeterClass.getDI_Dir() + '/' + self.getName() + '_PhaseVoltageReport.csv'

    def resetRegisters(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            self.registers[i] = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            self.derivatives[i] = 0.0
            # Initialise drag hand registers to some big negative number
        self.registers[EnergyMeter.REG_MAX_KW] = -1e+50
        self.registers[EnergyMeter.REG_MAX_KVA] = -1e+50
        self.registers[EnergyMeter.REG_ZONE_MAX_KW] = -1e+50
        self.registers[EnergyMeter.REG_ZONE_MAX_KVA] = -1e+50
        self.registers[EnergyMeter.REG_MAX_LOAD_LOSSES] = -1e+50
        self.registers[EnergyMeter.REG_MAX_NO_LOAD_LOSSES] = -1e+50
        self.registers[EnergyMeter.REG_LOSSES_MAX_KW] = -1e+50
        self.registers[EnergyMeter.REG_LOSSES_MAX_KVAR] = -1e+50
        self.registers[EnergyMeter.REG_GEN_MAX_KW] = -1e+50
        self.registers[EnergyMeter.REG_GEN_MAX_KVA] = -1e+50
        self.firstSampleAfterReset = True
        # initialise for trapezoidal integration
        # Removed .. open in solution loop See Solve Yearly if (EnergyMeterClass.SaveDemandInterval) openDemandIntervalFile();

    def calcYPrim(self):
        # YPrim is all zeros; just leave as nil so it is ignored
        pass

    def saveRegisters(self):
        csvName = 'MTR_' + self.getName() + '.csv'
        try:
            f = self.File(DSSGlobals.DSSDataDirectory + csvName)
            fw = self.FileWriter(f, False)
            bw = self.BufferedWriter(fw)
            DSSGlobals.globalResult = csvName
            bw.write('Year, ' + DSSGlobals.activeCircuit.getSolution().getYear() + ',')
            bw.newLine()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < EnergyMeter.NUM_EM_REGISTERS):
                    break
                bw.write('\"' + self.registerNames[i] + '\",' + self.registers[i])
                bw.newLine()
            bw.close()
            fw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening Meter File \"' + DSSGlobals.CRLF + csvName + '\": ' + e.getMessage(), 526)
            return

    def integrate(self, reg, deriv, interval):
        ckt = DSSGlobals.activeCircuit
        if ckt.isTrapezoidalIntegration():
            # Trapezoidal rule integration
            if not self.firstSampleAfterReset:
                self.registers[reg] = self.registers[reg] + (0.5 * interval * (deriv + self.derivatives[reg]))
        else:
            # Plain Euler integration
            self.registers[reg] = self.registers[reg] + (interval * deriv)
        # Set the derivatives so that the proper value shows up in demand interval files
        # and prepare for next time step in Trapezoidal integration.

        self.derivatives[reg] = deriv

    def takeSample(self):
        """Update registers from metered zone.
        Assumes one time period has taken place since last sample.
        """
        # pass by reference
        mS_TotalLosses = [None] * 2
        mS_LoadLosses = [None] * 2
        mS_NoLoadLosses = [None] * 2
        mS_PosSeqLosses = [None] * 2
        mS_ZeroSeqLosses = [None] * 2
        mS_NegSeqLosses = [None] * 2
        # lines only for now
        EEN = 0
        UE = 0
        # compute energy in branch to which meter is connected
        # MeteredElement.setActiveTerminalIdx(MeteredTerminal);  // needed for excess kVA calcs
        S_Local = self.meteredElement.getPower(self.meteredTerminal).multiply(0.001)
        S_LocalKVA = S_Local.abs()
        self.deltaHrs = DSSGlobals.activeCircuit.getSolution().getIntervalHrs()
        self.integrate(EnergyMeter.REG_KWH, S_Local.getReal(), self.deltaHrs)
        # accumulate the power
        self.integrate(EnergyMeter.REG_KVARH, S_Local.getImaginary(), self.deltaHrs)
        self.setDragHandRegister(EnergyMeter.REG_MAX_KW, S_Local.getReal())
        # 3-10-04 removed abs()
        self.setDragHandRegister(EnergyMeter.REG_MAX_KVA, S_LocalKVA)
        # compute maximum overload energy in all branches in zone
        # and mark all load downline from an overloaded branch as unserved
        # if localonly, check only metered element
        totalLosses = Complex.ZERO
        # initialize loss accumulators
        totalLoadLosses = Complex.ZERO
        totalNoLoadLosses = Complex.ZERO
        totalLineLosses = Complex.ZERO
        totalLineModeLosses = Complex.ZERO
        totalZeroModeLosses = Complex.ZERO
        total3PhaseLosses = Complex.ZERO
        total1PhaseLosses = Complex.ZERO
        totalTransformerLosses = Complex.ZERO
        # init all voltage base loss accumulators
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.maxVBaseCount):
                break
            self.VBaseTotalLosses[i] = 0.0
            self.VBaseLineLosses[i] = 0.0
            self.VBaseLoadLosses[i] = 0.0
            self.VBaseNoLoadLosses[i] = 0.0
            self.VBaseLoad[i] = 0.0
        # phase voltage arrays
        if self.phaseVoltageReport:
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.maxVBaseCount):
                    break
                if self.VBaseList[i] > 0.0:
                    _2 = True
                    j = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            j += 1
                        if not (j < 3):
                            break
                        self.VPhaseMax[self.jiIndex(j, i)] = 0.0
                        self.VPhaseMin[self.jiIndex(j, i)] = 9999.0
                        self.VPhaseAccum[self.jiIndex(j, i)] = 0.0
                        self.VPhaseAccumCount[self.jiIndex(j, i)] = 0
                        # keep track of counts for average
        cktElem = self.branchList.getFirst()
        maxExcessKWNorm = 0.0
        maxExcessKWEmerg = 0.0
        # ------------------------------------------------------------------
        # ------------------------ Local Zone Only -------------------------
        # ------------------------------------------------------------------
        if self.localOnly:
            cktElem = self.meteredElement
            maxExcessKWNorm = self.Math.abs(cktElem.getExcessKVANorm(self.meteredTerminal).getReal())
            maxExcessKWEmerg = self.Math.abs(cktElem.getExcessKVAEmerg(self.meteredTerminal).getReal())
        else:
            # --------------------------------------------------------------
            # --------Cyle Through Entire Zone Setting EEN/UE --------------
            # --------------------------------------------------------------
            while # loop thru all ckt elements on zone cktElem is not None:
                cktElem.setActiveTerminalIdx(self.branchList.getPresentBranch().getFromTerminal())
                # invoking this property sets the Overload_UE flag in the PD element
                EEN = self.Math.abs(cktElem.getExcessKVANorm(cktElem.getActiveTerminalIdx()).getReal())
                UE = self.Math.abs(cktElem.getExcessKVAEmerg(cktElem.getActiveTerminalIdx()).getReal())
            # For radial circuits just keep the maximum overload; for mesh, add them up
            if self.zoneIsRadial:
                if UE > maxExcessKWEmerg:
                    maxExcessKWEmerg = UE
                if EEN > maxExcessKWNorm:
                    maxExcessKWNorm = EEN
            else:
                maxExcessKWEmerg = maxExcessKWEmerg + UE
                maxExcessKWNorm = maxExcessKWNorm + EEN
            # even if this branch is not overloaded, if the parent element is overloaded
            # mark load on this branch as unserved also
            # use the larger of the two factors
            parenElem = self.branchList.getParent()
            if parenElem is not None:
                cktElem.setOverloadEEN(self.Math.max(cktElem.getOverloadEEN(), parenElem.getOverloadEEN()))
                cktElem.setOverload_UE(self.Math.max(cktElem.getOverloadUE(), parenElem.getOverloadUE()))
            # mark loads (not generators) by the degree of overload if the meter's zone is to be considered radial
            # this overrides and supercedes the load's own determination of unserved based on voltage
            # if voltage only is to be used for Load UE/EEN, don't mark (set to 0.0 and load will calc UE based on voltage)
            PCElem = self.branchList.getFirstObject()
            while PCElem is not None:
                if (
                    PCElem.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.LOAD_ELEMENT
                ):
                    load = PCElem
                    if (
                        cktElem.getOverloadEEN() > 0.0 and self.zoneIsRadial and not self.voltageUEOnly
                    ):
                        load.setEEN_Factor(cktElem.getOverloadEEN())
                    else:
                        load.setEEN_Factor(0.0)
                    if (
                        cktElem.getOverloadUE() > 0.0 and self.zoneIsRadial and not self.voltageUEOnly
                    ):
                        load.setUE_Factor(cktElem.getOverloadUE())
                    else:
                        load.setUE_Factor(0.0)
                PCElem = self.branchList.getNextObject()
            cktElem = self.branchList.goForward()
        # get the losses, and unserved bus energies
        totalZoneKW = 0.0
        totalZoneKVAr = 0.0
        totalLoad_EEN = 0.0
        totalLoad_UE = 0.0
        totalGenKW = 0.0
        totalGenKVAr = 0.0
        # ------------------------------------------------------------------
        # ----       Cycle Through Zone Accumulating Load and Losses    ----
        # ------------------------------------------------------------------
        cktElem = self.branchList.getFirst()
        while cktElem is not None:
            PCElem = self.branchList.getFirstObject()
            while PCElem is not None:
                _3 = PCElem.getDSSObjType() & DSSClassDefs.CLASSMASK
                _4 = False
                while True:
                    if _3 == DSSClassDefs.LOAD_ELEMENT:
                        _4 = True
                        if not self.localOnly:
                            # don't check for load EEN/UE if Local only
                            load = PCElem
                            LoadKW = self.accumulateLoad(load, totalZoneKW, totalZoneKVAr, totalLoad_EEN, totalLoad_UE)
                            if self.VBaseLosses:
                                vbi = self.branchList.getPresentBranch().getVoltBaseIndex()
                                if vbi > 0:
                                    self.VBaseLoad[vbi] = self.VBaseLoad[vbi] + LoadKW
                        break
                    if (_4 is True) or (_3 == DSSClassDefs.GEN_ELEMENT):
                        _4 = True
                        gen = PCElem
                        self.accumulateGen(gen, totalGenKW, totalGenKVAr)
                        break
                    break
                PCElem = self.branchList.getNextObject()
            if self.losses:
                # compute and report losses
                # Get losses from the present circuit element
                cktElem.getLosses(mS_TotalLosses, mS_LoadLosses, mS_NoLoadLosses)
                # returns watts, vars
                S_TotalLosses = Complex(mS_TotalLosses[0], mS_TotalLosses[1])
                S_LoadLosses = Complex(mS_LoadLosses[0], mS_LoadLosses[1])
                S_NoLoadLosses = Complex(mS_NoLoadLosses[0], mS_NoLoadLosses[1])
                # Convert to kW
                S_TotalLosses = S_TotalLosses.multiply(0.001)
                S_LoadLosses = S_LoadLosses.multiply(0.001)
                S_NoLoadLosses = S_NoLoadLosses.multiply(0.001)
                # Update accumulators
                totalLosses = totalLosses.add(S_TotalLosses)
                # accumulate total losses in meter zone
                totalLoadLosses = totalLoadLosses.add(S_LoadLosses)
                # accumulate total load losses in meter zone
                totalNoLoadLosses = totalNoLoadLosses.add(S_NoLoadLosses)
                # accumulate total no load losses in meter zone
                # Line and transformer elements
                if Utilities.isLineElement(cktElem) and self.lineLosses:
                    totalLineLosses = totalLineLosses.add(S_TotalLosses)
                    # accumulate total losses in meter zone
                    if self.seqLosses:
                        cktElem.getSeqLosses(mS_PosSeqLosses, mS_NegSeqLosses, mS_ZeroSeqLosses)
                        S_PosSeqLosses = Complex(mS_PosSeqLosses[0], mS_PosSeqLosses[1])
                        S_NegSeqLosses = Complex(mS_NegSeqLosses[0], mS_NegSeqLosses[1])
                        S_ZeroSeqLosses = Complex(mS_ZeroSeqLosses[0], mS_ZeroSeqLosses[1])
                        S_PosSeqLosses = S_PosSeqLosses.add(S_NegSeqLosses)
                        # add line modes together
                        S_PosSeqLosses = S_PosSeqLosses.multiply(0.001)
                        # convert to kW
                        S_ZeroSeqLosses = S_ZeroSeqLosses.multiply(0.001)
                        totalLineModeLosses = totalLineModeLosses.add(S_PosSeqLosses)
                        totalZeroModeLosses = totalZeroModeLosses.add(S_ZeroSeqLosses)
                    # Separate line losses into 3- and "1-phase" losses
                    if self.threePhaseLosses:
                        if cktElem.getNPhases() == 3:
                            total3PhaseLosses = total3PhaseLosses.add(S_TotalLosses)
                        else:
                            total1PhaseLosses = total1PhaseLosses.add(S_TotalLosses)
                elif Utilities.isTransformerElement(cktElem) and self.xfmrLosses:
                    totalTransformerLosses = totalTransformerLosses.add(S_TotalLosses)
                    # accumulate total losses in meter zone
                if self.VBaseLosses:
                    vbi = self.branchList.getPresentBranch().getVoltBaseIndex()
                    if vbi >= 0:
                        self.VBaseTotalLosses[vbi] = self.VBaseTotalLosses[vbi] + S_TotalLosses.getReal()
                        if Utilities.isLineElement(cktElem):
                            self.VBaseLineLosses[vbi] = self.VBaseLineLosses[vbi] + S_TotalLosses.getReal()
                        elif Utilities.isTransformerElement(cktElem):
                            self.VBaseLoadLosses[vbi] = self.VBaseLoadLosses[vbi] + S_LoadLosses.getReal()
                            self.VBaseNoLoadLosses[vbi] = self.VBaseNoLoadLosses[vbi] + S_NoLoadLosses.getReal()
                # compute min, max, and average pu voltages for 1st 3 phases (nodes designated 1, 2, or 3)
                if self.phaseVoltageReport:
                    vbi = self.branchList.getPresentBranch().getVoltBaseIndex()
                    fbr = self.branchList.getPresentBranch().getFromBusReference()
                    if vbi >= 0:
                        ckt = DSSGlobals.activeCircuit
                        if ckt.getBuses()[fbr].getKVBase() > 0.0:
                            _5 = True
                            i = 0
                            while True:
                                if _5 is True:
                                    _5 = False
                                else:
                                    i += 1
                                if not (i < ckt.getBuses()[fbr].getNumNodesThisBus()):
                                    break
                                j = ckt.getBuses()[fbr].getNum(i)
                                if j >= 0 and j < 3:
                                    # TODO Check zero based indexing
                                    puV = ckt.getSolution().getNodeV()[ckt.getBuses()[fbr].getRef(i)].abs() / ckt.getBuses()[fbr].getKVBase()
                                    self.VPhaseMax[self.jiIndex(j, vbi)] = self.Math.max(self.VPhaseMax[self.jiIndex(j, vbi)], puV)
                                    self.VPhaseMin[self.jiIndex(j, vbi)] = self.Math.min(self.VPhaseMin[self.jiIndex(j, vbi)], puV)
                                    self.VPhaseAccum[self.jiIndex(j, vbi)] = self.VPhaseAccum[self.jiIndex(j, vbi)] + puV
                                    self.VPhaseAccumCount[self.jiIndex(j, vbi)] += 1
                                    # keep track of counts for average
            # if (losses)
            cktElem = self.branchList.goForward()
        # NOTE: integrate proc automatically sets derivatives array
        self.integrate(EnergyMeter.REG_LOAD_EEN, totalLoad_EEN, self.deltaHrs)
        self.integrate(EnergyMeter.REG_LOAD_UE, totalLoad_UE, self.deltaHrs)
        # Accumulate losses in appropriate registers
        self.integrate(EnergyMeter.REG_ZONE_LOSSES_KWH, totalLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_ZONE_LOSSES_KVARH, totalLosses.getImaginary(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_LOAD_LOSSES_KWH, totalLoadLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_LOAD_LOSSES_KVARH, totalLoadLosses.getImaginary(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KWH, totalNoLoadLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KVARH, totalNoLoadLosses.getImaginary(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_LINE_LOSSES_KWH, totalLineLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_LINE_MODE_LINE_LOSS, totalLineModeLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_ZERO_MODE_LINE_LOSS, totalZeroModeLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_3_PHASE_LINE_LOSS, total3PhaseLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_1_PHASE_LINE_LOSS, total1PhaseLosses.getReal(), self.deltaHrs)
        self.integrate(EnergyMeter.REG_TRANSFORMER_LOSSES_KWH, totalTransformerLosses.getReal(), self.deltaHrs)
        _6 = True
        i = 0
        while True:
            if _6 is True:
                _6 = False
            else:
                i += 1
            if not (i < self.maxVBaseCount):
                break
            self.integrate(EnergyMeter.REG_VBASE_START + i, self.VBaseTotalLosses[i], self.deltaHrs)
            self.integrate(EnergyMeter.REG_VBASE_START + (1 * self.maxVBaseCount) + i, self.VBaseLineLosses[i], self.deltaHrs)
            self.integrate(EnergyMeter.REG_VBASE_START + (2 * self.maxVBaseCount) + i, self.VBaseLoadLosses[i], self.deltaHrs)
            self.integrate(EnergyMeter.REG_VBASE_START + (3 * self.maxVBaseCount) + i, self.VBaseNoLoadLosses[i], self.deltaHrs)
            self.integrate(EnergyMeter.REG_VBASE_START + (4 * self.maxVBaseCount) + i, self.VBaseLoad[i], self.deltaHrs)
        # ------------------------------------------------------------------
        # ---------------   Total Zone Load and Generation -----------------
        # ------------------------------------------------------------------
        self.integrate(EnergyMeter.REG_ZONE_KWH, totalZoneKW, self.deltaHrs)
        self.integrate(EnergyMeter.REG_ZONE_KVARH, totalZoneKVAr, self.deltaHrs)
        self.integrate(EnergyMeter.REG_GEN_KWH, totalGenKW, self.deltaHrs)
        self.integrate(EnergyMeter.REG_GEN_KVARH, totalGenKVAr, self.deltaHrs)
        genKVA = self.Math.sqrt(self.Math.pow(totalGenKVAr, 2) + self.Math.pow(totalGenKW, 2))
        loadKVA = self.Math.sqrt(self.Math.pow(totalZoneKVAr, 2) + self.Math.pow(totalZoneKW, 2))
        # ------------------------------------------------------------------
        # ---------------   Set Drag Hand Registers  -----------------------
        # ------------------------------------------------------------------
        self.setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KW, self.Math.abs(totalLosses.getReal()))
        self.setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KVAR, self.Math.abs(totalLosses.getImaginary()))
        self.setDragHandRegister(EnergyMeter.REG_MAX_LOAD_LOSSES, self.Math.abs(totalLoadLosses.getReal()))
        self.setDragHandRegister(EnergyMeter.REG_MAX_NO_LOAD_LOSSES, self.Math.abs(totalNoLoadLosses.getReal()))
        self.setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KW, totalZoneKW)
        # Removed abs()  3-10-04
        self.setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KVA, loadKVA)
        # Max total generator registers
        self.setDragHandRegister(EnergyMeter.REG_GEN_MAX_KW, totalGenKW)
        # Removed abs()  3-10-04
        self.setDragHandRegister(EnergyMeter.REG_GEN_MAX_KVA, genKVA)
        # ------------------------------------------------------------------
        # ---------------------   Overload Energy  -------------------------
        # ------------------------------------------------------------------
        # Overload energy for the entire zone
        if self.localOnly:
            zoneKW = S_Local.getReal()
        else:
            zoneKW = totalZoneKW
        # Either the max excess kW of any PD element or the excess over zone limits
        # regs 9 and 10
        # Fixed these formulas 2-7-07 per discussions with Daniel Brooks
        if self.maxZoneKVANorm > 0.0:
            if S_LocalKVA == 0.0:
                S_LocalKVA = self.maxZoneKVANorm
            self.integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM, self.Math.max(0.0, zoneKW * (1.0 - (self.maxZoneKVANorm / S_LocalKVA))), self.deltaHrs)
        else:
            self.integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM, maxExcessKWNorm, self.deltaHrs)
        if self.maxZoneKVAEmerg > 0.0:
            if S_LocalKVA == 0.0:
                S_LocalKVA = self.maxZoneKVAEmerg
            self.integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG, self.Math.max(0.0, zoneKW * (1.0 - (self.maxZoneKVAEmerg / S_LocalKVA))), self.deltaHrs)
        else:
            self.integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG, maxExcessKWEmerg, self.deltaHrs)
        self.firstSampleAfterReset = False
        if DSSGlobals.energyMeterClass.isSaveDemandInterval():
            self.writeDemandIntervalData()

    def totalUpDownstreamCustomers(self):
        presentNode = None
        if self.branchList is None:
            DSSGlobals.doSimpleMsg('Meter zone lists need to be built. Solve or makeBusList first.', 529)
            return
        # Init totals and checked flag
        cktElem = self.branchList.getFirst()
        while cktElem is not None:
            cktElem.setChecked(False)
            cktElem.setTotalCustomers(0)
            cktElem = self.branchList.goForward()
        # This algorithm could be made more efficient with a sequence list
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.branchList.getZoneEndsList().getNumEnds()):
                break
            # Busref =
            self.branchList.getZoneEndsList().get(i, presentNode)
            if presentNode is not None:
                cktElem = presentNode.getCktObject()
                if not cktElem.isChecked():
                    # don't do a zone end element more than once
                    cktElem.setChecked(True)
                    accumulator = cktElem.getNumCustomers()
                    while (
                        # Trace back to the source
                        True
                    ):
                        cktElem.setTotalCustomers(cktElem.getTotalCustomers() + accumulator)
                        presentNode = presentNode.getParent()
                        if presentNode is None:
                            break
                        cktElem = presentNode.getCktObject()
                        if not cktElem.isChecked():
                            # avoid double counting
                            accumulator = accumulator + cktElem.getNumCustomers()
                            cktElem.setChecked(True)

    def makeMeterZoneLists(self):
        """This gets fired off whenever the buslists are rebuilt.
        Must be updated whenever there is a change in the circuit.
        """
        ckt = DSSGlobals.activeCircuit
        zoneListCounter = 0
        self.VBaseCount = 0
        # Build the voltage base list over in case a base added or deleted
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < self.maxVBaseCount):
                break
            self.VBaseList[j] = 0.0
        self.branchList = CktTreeImpl()
        # Instantiates ZoneEndsList, too
        # Get Started
        if self.meteredElement is not None:
            self.branchList.setNew(self.meteredElement)
        else:
            DSSGlobals.doSimpleMsg('Metered Element for EnergyMeter ' + self.getName() + ' not defined.', 527)
            return
        # Initialize sensorObj property of the first branch to this MeterElement object.
        # Before starting, all sensorObj definitions are cleared in PC elements and PD elements. The
        # sensorObj property is passed down to the Load objects for loadAllocation and state estimation.

        if isinstance(self.meteredElement, PDElement):
            self.meteredElement.setSensorObj(self)
        elif isinstance(self.meteredElement, PCElement):
            self.meteredElement.setSensorObj(self)
        if isinstance(self.meteredElement, PDElement):
            self.meteredElement.setMeterObj(self)
        elif isinstance(self.meteredElement, PCElement):
            self.meteredElement.setMeterObj(self)
        self.meteredElement.getTerminals()[self.meteredTerminal].setChecked(True)
        pb = self.branchList.getPresentBranch()
        # this bus is the head of the feeder; do not mark as radial bus
        pb.setFromBusReference(self.meteredElement.getTerminals()[self.meteredTerminal].getBusRef())
        DSSGlobals.activeCircuit.getBuses()[pb.getFromBusReference()].setDistFromMeter(0.0)
        pb.setVoltBaseIndex(self.addToVoltBaseList(pb.getFromBusReference()))
        pb.setFromTerminal(self.meteredTerminal)
        if isinstance(self.meteredElement, PDElement):
            self.meteredElement.setFromTerminal(self.meteredTerminal)
            # check off this element so we don't use it again
        self.meteredElement.setChecked(True)
        self.meteredElement.setIsolated(False)
        # now start looking for other branches
        # finds any branch connected to the testBranch and adds it to the list
        # goes until end of circuit, another energy meter, an open terminal, or disabled device
        activeBranch = self.meteredElement
        while activeBranch is not None:
            pb = self.branchList.getPresentBranch()
            pb.setLoopedHere(False)
            pb.setParallel(False)
            pb.setDangling(True)
            # unless we find something connected to it
            pb.setVoltBaseIndex(self.addToVoltBaseList(pb.getFromBusReference()))
            activeBranch.setNumCustomers(0)
            # init counter
            _1 = True
            iTerm = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    iTerm += 1
                if not (iTerm < activeBranch.getNTerms()):
                    break
                if not activeBranch.getTerminals()[iTerm].isChecked():
                    # now find all loads and generators connected to the bus on this end of branch
                    # attach them as generic objects to cktTree node
                    testBusNum = activeBranch.getTerminals()[iTerm].getBusRef()
                    self.branchList.getPresentBranch().setToBusReference(testBusNum)
                    # add this as a "to" bus reference
                    if Utilities.isLineElement(activeBranch):
                        # convert to consistent units (km)
                        ckt.getBuses()[testBusNum].setDistFromMeter(ckt.getBuses()[self.branchList.getPresentBranch().getFromBusReference()].getDistFromMeter() + (activeBranch.getLen() * LineUnits.convertLineUnits(activeBranch.getLengthUnits(), LineUnits.UNITS_KM)))
                    else:
                        ckt.getBuses()[testBusNum].setDistFromMeter(ckt.getBuses()[self.branchList.getPresentBranch().getFromBusReference()].getDistFromMeter())
                    adjLstPC = EnergyMeterImpl.busAdjPC[testBusNum]
                    _2 = True
                    iPC = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            iPC += 1
                        if not (iPC < len(adjLstPC)):
                            break
                        pc = adjLstPC[iPC]
                        if pc.isChecked():
                            continue
                            # skip ones we already checked
                        self.branchList.getPresentBranch().setDangling(False)
                        # something is connected here
                        # is this a load or a generator or a capacitor or reactor?
                        if (
                            (((pc.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.LOAD_ELEMENT) or (pc.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.GEN_ELEMENT)) or (pc.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.CAP_ELEMENT)) or (pc.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.REACTOR_ELEMENT)
                        ):
                            self.branchList.setNewObject(pc)
                            pc.setChecked(True)
                            # so we don't pick this element up again
                            pc.setIsolated(False)
                            pc.setActiveTerminalIdx(1)
                            # TODO Check zero based indexing
                            # Totalize number of customers if load type
                            if isinstance(pc, LoadObj):
                                load = pc
                                activeBranch.setNumCustomers(activeBranch.getNumCustomers() + load.getNumCustomers())
                            # If object does not have a sensor attached, it acquires the sensor of its parent branch
                            if not pc.hasSensorObj():
                                pc.setSensorObj(activeBranch.getSensorObj())
                            pc.setMeterObj(self)
                    # now find all branches connected to this bus that we havent found already
                    # do not include in this zone if branch has open terminals or has another meter
                    if self.definedZoneListSize == 0:
                        # search tree for connected branches (default)
                        isFeederEnd = True
                        adjLstPD = EnergyMeterImpl.busAdjPD[testBusNum]
                        _3 = True
                        iPD = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                iPD += 1
                            if not (iPD < len(adjLstPD)):
                                break
                            testElement = adjLstPD[iPD]
                            # only enabled objects are in this list
                            # see resetMeterZonesAll()
                            if not (testElement == activeBranch):
                                # skip self
                                if not testElement.hasEnergyMeter():
                                    # stop at other meters so zones don't interfere
                                    _4 = True
                                    j = 0
                                    while True:
                                        if _4 is True:
                                            _4 = False
                                        else:
                                            j += 1
                                        if not (j < testElement.getNTerms()):
                                            break
                                        # check each terminal
                                        if testBusNum == testElement.getTerminals()[j].getBusRef():
                                            self.branchList.getPresentBranch().setDangling(False)
                                            # we found something it was connected to
                                            # Check for loops and parallel branches and mark them
                                            if testElement.isChecked():
                                                # This branch is on some meter's list already
                                                self.branchList.getPresentBranch().setLoopedHere(True)
                                                # It's a loop
                                                self.branchList.getPresentBranch().setLoopLineObj(testElement)
                                                if (
                                                    Utilities.isLineElement(activeBranch) and Utilities.isLineElement(testElement)
                                                ):
                                                    if Utilities.checkParallel(activeBranch, testElement):
                                                        self.branchList.getPresentBranch().setParallel(True)
                                                        # It's paralleled with another line
                                            else:
                                                # push testElement onto stack and set properties
                                                isFeederEnd = False
                                                # for interpolation
                                                self.branchList.addNewChild(testElement, testBusNum, j)
                                                # add new child to the branch list
                                                testElement.getTerminals()[j].setChecked(True)
                                                testElement.setFromTerminal(j)
                                                testElement.setChecked(True)
                                                testElement.setIsolated(False)
                                                # Branch inherits sensor of upline branch if it doesn't have its own
                                                if not self.hasSensorObj:
                                                    testElement.setSensorObj(activeBranch.getSensorObj())
                                                testElement.setMeterObj(self)
                                                # set meterobj to this meter
                                                testElement.setParentPDElement(activeBranch)
                                                # record the parent so we can easily back up for reconductoring, etc.
                                                break
                                        # if testBusNum
                                    # for terminals
                        # for iPD
                        if isFeederEnd:
                            self.branchList.getZoneEndsList().add(self.branchList.getPresentBranch(), testBusNum)
                            # This is an end of the feeder and testBusNum is the end bus
                    else:
                        # zone is manually specified; just add next element in list as a child
                        zoneListCounter += 1
                        while zoneListCounter <= self.definedZoneListSize:
                            if ckt.setElementActive(self.definedZoneList[zoneListCounter]) == 0:
                                zoneListCounter += 1
                                # not found, let's search for another
                            else:
                                testElement = ckt.getActiveCktElement()
                                if not testElement.isEnabled():
                                    zoneListCounter += 1
                                    # lets ignore disabled devices
                                else:
                                    if (
                                        testElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK != DSSClassDefs.PD_ELEMENT
                                    ):
                                        zoneListCounter += 1
                                        # lets ignore non-PD elements
                                    else:
                                        self.branchList.addNewChild(testElement, 0, 0)
                                        # add it as a child to the previous element
                                    break
                                    # can't do reductions if manually spec'd
                        # while
            # for iTerm
            activeBranch = self.branchList.goForward()
            # sets present node
        self.totalUpDownstreamCustomers()
        if self.hasFeeder:
            self.feederObj.initializeFeeder(self.branchList)
            # synchronise the feeder definition
        self.assignVoltBaseRegisterNames()

    def getCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def getInjCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def zoneDump(self):
        ckt = DSSGlobals.activeCircuit
        csvName = 'Zone_' + self.getName() + '.csv'
        try:
            f = self.File(DSSGlobals.DSSDataDirectory, csvName)
            fw = self.FileWriter(f, False)
            bw = self.BufferedWriter(fw)
            DSSGlobals.globalResult = csvName
            bw.write('Level, Branch, Bus1, Bus2, Distance')
            bw.newLine()
            if self.branchList is not None:
                pd = self.branchList.getFirst()
                while pd is not None:
                    bw.write(String.format.format('%d, %s.%s, %s, %s, %10.4f', self.branchList.getLevel(), pd.getParentClass().getName(), pd.getName(), pd.getFirstBus(), pd.getNextBus(), ckt.getBuses()[self.branchList.getPresentBranch().getToBusReference()].getDistFromMeter()))
                    bw.newLine()
                    loadElem = self.branchList.getFirstObject()
                    while loadElem is not None:
                        bw.write('-1, ' + String.format.format('%s.%s, %s', loadElem.getParentClass().getName(), loadElem.getName(), loadElem.getFirstBus()))
                        bw.newLine()
                        loadElem = self.branchList.getNextObject()
                    pd = self.branchList.goForward()
            bw.close()
            fw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening file \"' + csvName + '\": ' + e.getMessage(), 528)

    def dumpProperties(self, f, complete):
        super(EnergyMeterObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            _1 = i
            _2 = False
            while True:
                if _1 == 3:
                    _2 = True
                    f.print_('~ ' + self.getParentClass().getPropertyName()[i] + '=(')
                    if self.excessFlag:
                        f.print_('E,')
                    else:
                        f.print_('T,')
                    if self.zoneIsRadial:
                        f.print_(' R,')
                    else:
                        f.print_(' M,')
                    if self.voltageUEOnly:
                        f.print_(' V')
                    else:
                        f.print_(' C')
                    print ')'
                    break
                if (_2 is True) or (_1 == 6):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.getPropertyValue(i) + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
                    break
                break
        if complete:
            print 'Registers'
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < EnergyMeter.NUM_EM_REGISTERS):
                    break
                print '\"' + self.registerNames[i] + '\" = ' + self.registers[i]
            print 
            print 'Branch List:'
            if self.branchList is not None:
                pd = self.branchList.getFirst()
                while pd is not None:
                    print 'Circuit Element = ' + pd.getName()
                    load = self.branchList.getFirstObject()
                    while load is not None:
                        print '   Shunt Element = ' + load.getParentClass().getName() + '.' + load.getName()
                        load = self.branchList.getNextObject()
                    pd = self.branchList.goForward()

    def addToVoltBaseList(self, busRef):
        """Add to VoltBase list if not already there and return index."""
        bus = DSSGlobals.activeCircuit.getBuses()[busRef]
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.VBaseCount):
                break
            if self.Math.abs(1.0 - (bus.getKVBase() / self.VBaseList[i])) < 0.01:
                # < 1% difference
                return i
        if bus.getKVBase() > 0.0 and self.VBaseCount < self.maxVBaseCount:
            self.VBaseCount += 1
            self.VBaseList[self.VBaseCount] = bus.getKVBase()
            return self.VBaseCount
        else:
            return 0

    def allocateLoad(self):
        # Now go through the meter's zone and adjust the loads.
        # 
        # While the AllocationFactor property is adjusted for all loads, it will only
        # have an effect on loads defined with either the XFKVA property or the
        # kWh property.
        # 
        # Loads have a sensorObj property that points to its upstream sensor that has the adjustments for
        # the allocation factors.  This is established in the makeMeterZoneLists proc in this unit.
        # 
        # Sensors consist of EnergyMeters, which drive the load allocation process and Sensor objects that
        # are simply voltage and current measuring points.  A Sensor may be attached to a line or transformer
        # or it may be connected directly to a load.

        cktElem = self.branchList.getFirst()
        while cktElem is not None:
            loadElem = self.branchList.getFirstObject()
            while loadElem is not None:
                if (
                    loadElem.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.LOAD_ELEMENT
                ):
                    # only for loads not other shunts
                    _0 = loadElem.getNPhases()
                    _1 = False
                    while True:
                        if _0 == 1:
                            _1 = True
                            connectedPhase = DSSGlobals.activeCircuit.getMapNodeToBus()[self.nodeRef[0]].nodeNum
                            if connectedPhase > 0 and connectedPhase < 4:
                                # restrict to phases 1..3
                                loadElem.setAllocationFactor(loadElem.getAllocationFactor() * loadElem.getSensorObj().getPhsAllocationFactor()[connectedPhase])
                            break
                        if True:
                            _1 = True
                            loadElem.setAllocationFactor(loadElem.getAllocationFactor() * self.avgAllocFactor)
                            break
                        break
                    # For single phase loads, allocate based on phase factor, else average factor
                loadElem = self.branchList.getNextObject()
                # Next load at this bus
            cktElem = self.branchList.goForward()
            # Go on down the tree

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '')
        # "element";
        self.setPropertyValue(1, '1')
        # "terminal";
        self.setPropertyValue(2, 'clear')
        # "action";
        self.setPropertyValue(3, '(E, R, C)')
        # "option";
        self.setPropertyValue(4, '0.0')
        # "kWnormal";
        self.setPropertyValue(5, '0.0')
        # "kwEmerg";
        self.setPropertyValue(6, '(400, 400, 400)')
        # "peakCurrent";
        self.setPropertyValue(7, '')
        # zoneList
        self.setPropertyValue(8, 'No')
        # Define mask as 1 for all registers
        s = '['
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            s = s + '1 '
        self.setPropertyValue(9, s + ']')
        self.setPropertyValue(10, 'Yes')
        self.setPropertyValue(11, 'Yes')
        self.setPropertyValue(12, 'Yes')
        self.setPropertyValue(13, 'Yes')
        self.setPropertyValue(14, 'Yes')
        # segregate losses by voltage base
        self.setPropertyValue(15, 'Yes')
        self.setPropertyValue(16, 'No')
        super(EnergyMeterObjImpl, self).initPropertyValues(EnergyMeter.NumPropsThisClass)

    def accumulateGen(self, gen, totalZonekW, totalZoneKVAr):
        # FIXME Pass by reference
        # pGen.setActiveTerminalIdx(1);
        S = gen.getPower(1).multiply(0.001).negate()
        # TODO Check zero based indexing
        totalZonekW = totalZonekW + S.getReal()
        totalZoneKVAr = totalZoneKVAr + S.getImaginary()

    def accumulateLoad(self, load, totalZoneKW, totalZoneKVAr, totalLoad_EEN, totalLoad_UE):
        # FIXME Pass be reference
        # pLoad.setActiveTerminalIdx(1);
        SLoad = load.getPower(1).multiply(0.001)
        # get power in terminal 1   TODO Check zero based indexing
        kWLoad = SLoad.getReal()
        result = kWLoad
        # Accumulate load in zone
        totalZoneKW = totalZoneKW + kWLoad
        totalZoneKVAr = totalZoneKVAr + SLoad.getImaginary()
        # Always integrate even if the value is 0.0
        # otherwise the integrate function is not correct.

        # Invoking the exceedsNormal and unserved properties causes the factors to be computed
        if self.excessFlag:
            # return excess load as EEN/UE
            if load.getExceedsNormal():
                loadEEN = kWLoad * load.getEEN_Factor()
            else:
                loadEEN = 0.0
            if load.getUnserved():
                loadUE = kWLoad * load.getUE_Factor()
            else:
                loadUE = 0.0
        else:
            # return total load as EEN/UE
            if load.getExceedsNormal():
                loadEEN = kWLoad
            else:
                loadEEN = 0.0
            if load.getUnserved():
                loadUE = kWLoad
            else:
                loadUE = 0.0
        totalLoad_EEN = totalLoad_EEN + loadEEN
        totalLoad_UE = totalLoad_UE + loadUE
        return result

    def reduceZone(self):
        """Reduce zone by eliminating buses and merging lines."""
        # make sure zone list is built
        if self.branchList is None:
            self.makeMeterZoneLists()
        _0 = DSSGlobals.activeCircuit.getReductionStrategy()
        _1 = False
        while True:
            if _0 == self.STUBS:
                _1 = True
                ReduceAlgs.doReduceStubs(self.branchList)
                break
            if (_1 is True) or (_0 == self.TAP_ENDS):
                _1 = True
                ReduceAlgs.doReduceTapEnds(self.branchList)
                break
            if (_1 is True) or (_0 == self.MERGE_PARALLEL):
                _1 = True
                ReduceAlgs.doMergeParallelLines(self.branchList)
                break
            if (_1 is True) or (_0 == self.DANGLING):
                _1 = True
                ReduceAlgs.doReduceDangling(self.branchList)
                break
            if (_1 is True) or (_0 == self.BREAK_LOOP):
                _1 = True
                ReduceAlgs.doBreakLoops(self.branchList)
                break
            if (_1 is True) or (_0 == self.SWITCHES):
                _1 = True
                ReduceAlgs.doReduceSwitches(self.branchList)
                break
            if True:
                _1 = True
                ReduceAlgs.doReduceDefault(self.branchList)
                break
            break
        # resynchronize with feeders
        if self.hasFeeder:
            self.feederObj.initializeFeeder(self.branchList)

    def interpolateCoordinates(self):
        """Start at the ends of the zone and work toward the start
        interpolating between known coordinates.
        """
        presentNode = None
        ckt = DSSGlobals.activeCircuit
        if self.branchList is None:
            DSSGlobals.doSimpleMsg('Meter zone lists need to be built. Solve or makeBusList first.', 529)
            return
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.branchList.getZoneEndsList().getNumEnds()):
                break
            busRef = self.branchList.getZoneEndsList().get(i, presentNode)
            firstCoordRef = busRef
            secondCoordRef = firstCoordRef
            # so compiler won't issue warning
            # Find a bus with a coordinate
            if not ckt.getBuses()[busRef].isCoordDefined():
                while not ckt.getBuses()[presentNode.getFromBusReference()].isCoordDefined():
                    presentNode = presentNode.getParent()
                    if presentNode is None:
                        break
                if presentNode is not None:
                    firstCoordRef = presentNode.getFromBusReference()
            while # Back up until we find another coord defined presentNode is not None:
                lineCount = 0
                # number of line segments in this segment
                startNode = presentNode
                cktElem = presentNode.getCktObject()
                if firstCoordRef != presentNode.getFromBusReference():
                    # Handle special case for end branch
                    if ckt.getBuses()[presentNode.getFromBusReference()].isCoordDefined():
                        firstCoordRef = presentNode.getFromBusReference()
                    else:
                        lineCount += 1
                while (
                    not ckt.getBuses()[secondCoordRef].isCoordDefined() and not cktElem.isChecked()
                ):
                    cktElem.setChecked(True)
                    presentNode = presentNode.getParent()
                    if presentNode is None:
                        break
                    cktElem = presentNode.getCktObject()
                    secondCoordRef = presentNode.getFromBusReference()
                    lineCount += 1
                if presentNode is not None and lineCount > 1:
                    if ckt.getBuses()[secondCoordRef].isCoordDefined():
                        self.calcBusCoordinates(startNode, firstCoordRef, secondCoordRef, lineCount)
                    else:
                        break
                        # While - went as far as we could go this way
                firstCoordRef = secondCoordRef
        # for

    def calcBusCoordinates(self, startBranch, firstCoordRef, secondCoordRef, lineCount):
        if lineCount == 1:
            return
            # Nothing to do!
        ckt = DSSGlobals.activeCircuit
        XInc = (ckt.getBuses()[firstCoordRef].getX() - ckt.getBuses()[secondCoordRef].getX()) / lineCount
        YInc = (ckt.getBuses()[firstCoordRef].getY() - ckt.getBuses()[secondCoordRef].getY()) / lineCount
        X = ckt.getBuses()[firstCoordRef].getX()
        Y = ckt.getBuses()[firstCoordRef].getY()
        # if (((X < 10.0) && (y < 10.0)) || ((ckt.getBuses()[SecondCoordRef].getX() < 10.0) && (ckt.getBuses()[SecondCoordRef].getY() < 10.0)))
        # 			X = Y;

        # stopping point
        # Either start with the "to" end of startNode or the "from" end;
        if firstCoordRef != startBranch.getFromBusReference():
            # start with "to" end
            X = X - XInc
            Y = Y - YInc
            ckt.getBuses()[startBranch.getFromBusReference()].setX(X)
            ckt.getBuses()[startBranch.getFromBusReference()].setY(Y)
            ckt.getBuses()[startBranch.getFromBusReference()].setCoordDefined(True)
            lineCount -= 1
        while lineCount > 1:
            X = X - XInc
            Y = Y - YInc
            startBranch = startBranch.getParent()
            # back up the tree
            ckt.getBuses()[startBranch.getFromBusReference()].setX(X)
            ckt.getBuses()[startBranch.getFromBusReference()].setY(Y)
            ckt.getBuses()[startBranch.getFromBusReference()].setCoordDefined(True)
            lineCount -= 1

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 3:
                _1 = True
                result = '('
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                result = '('
                break
            if True:
                _1 = True
                result = ''
                break
            break
        _2 = index
        _3 = False
        while True:
            if _2 == 4:
                _3 = True
                if self.excessFlag:
                    result = result + 'E,'
                else:
                    result = result + 'T,'
                if self.zoneIsRadial:
                    result = result + ' R,'
                else:
                    result = result + ' M,'
                if self.voltageUEOnly:
                    result = result + ' V'
                else:
                    result = result + ' C'
                break
            if True:
                _3 = True
                result = result + super(EnergyMeterObjImpl, self).getPropertyValue(index)
                break
            break
        _4 = index
        _5 = False
        while True:
            if _4 == 3:
                _5 = True
                result = result + ')'
                break
            if (_5 is True) or (_4 == 6):
                _5 = True
                result = result + ')'
                break
            break
        return result

    def saveZone(self, dirname):
        ckt = DSSGlobals.activeCircuit
        # We are in the directory indicated by dirname
        # Run down the zone and write each element into a file
        if self.branchList is not None:
            # Open some files:
            try:
                fbranches = self.File('Branches.dss')
                # both lines and transformers
                fwbranches = self.FileWriter(fbranches, False)
                pwbranches = self.PrintWriter(fwbranches)
                nbranches = 0
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error creating Branches.dss for EnergyMeter: ' + self.getName() + '. ' + e.getMessage(), 530)
                return
            try:
                fshunts = self.File('Shunts.dss')
                fwshunts = self.FileWriter(fshunts, False)
                pwshunts = self.PrintWriter(fwshunts)
                nshunts = 0
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error creating Shunts.dss for EnergyMeter: ' + self.getName() + '. ' + e.getMessage(), 531)
                return
            try:
                floads = self.File('Loads.dss')
                fwloads = self.FileWriter(floads, False)
                pwloads = self.PrintWriter(fwloads)
                nloads = 0
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error creating Loads.dss for EnergyMeter: ' + self.getName() + '. ' + e.getMessage(), 532)
                return
            try:
                fgens = self.File('Generators.dss')
                fwgens = self.FileWriter(fgens, False)
                pwgens = self.PrintWriter(fwgens)
                ngens = 0
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error creating Generators.dss for EnergyMeter: ' + self.getName() + '. ' + e.getMessage(), 533)
                return
            try:
                fcaps = self.File('Capacitors.dss')
                fwcaps = self.FileWriter(fcaps, False)
                pwcaps = self.PrintWriter(fwcaps)
                ncaps = 0
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error creating Capacitors.dss for EnergyMeter: ' + self.getName() + '. ' + e.getMessage(), 534)
                return
            cktElem = self.branchList.getFirst()
            while cktElem is not None:
                if cktElem.isEnabled():
                    ckt.setActiveCktElement(cktElem)
                    nbranches += 1
                    Utilities.writeActiveDSSObject(pwbranches, 'New')
                    # sets hasBeenSaved(true)
                    if ckt.getActiveCktElement().hasControl():
                        ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement())
                        Utilities.writeActiveDSSObject(pwbranches, 'New')
                        # regulator control ... also, relays, switch controls
                    shuntElement = self.branchList.getFirstObject()
                    while shuntElement is not None:
                        ckt.setActiveCktElement(shuntElement)
                        if (
                            shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.LOAD_ELEMENT
                        ):
                            loadElement = shuntElement
                            if loadElement.getHasBeenAllocated():
                                # Manually set the allocation factor so it shows up
                                Parser.getInstance().setCmdString('allocationfactor=' + String.format.format('%-.4g', loadElement.getAllocationFactor()))
                                loadElement.edit()
                            ckt.setActiveCktElement(shuntElement)
                            # reset in case edit mangles it
                            nloads += 1
                            Utilities.writeActiveDSSObject(pwloads, 'New')
                        elif (
                            shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.GEN_ELEMENT
                        ):
                            ngens += 1
                            Utilities.writeActiveDSSObject(pwgens, 'New')
                            if ckt.getActiveCktElement().hasControl():
                                ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement())
                                Utilities.writeActiveDSSObject(pwgens, 'New')
                        elif (
                            shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK == DSSClassDefs.CAP_ELEMENT
                        ):
                            ncaps += 1
                            Utilities.writeActiveDSSObject(pwcaps, 'New')
                            if ckt.getActiveCktElement().hasControl():
                                ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement())
                                Utilities.writeActiveDSSObject(pwcaps, 'New')
                        else:
                            nshunts += 1
                            Utilities.writeActiveDSSObject(pwshunts, 'New')
                        shuntElement = self.branchList.getNextObject()
                # if enabled
                cktElem = self.branchList.goForward()
            pwbranches.close()
            pwshunts.close()
            pwloads.close()
            pwgens.close()
            pwcaps.close()
            # TODO Auto-generated catch block
            # If any records were written to the file, record their relative names
            try:
                fwbranches.close()
                fwshunts.close()
                fwloads.close()
                fwgens.close()
                fwcaps.close()
            except IOException, e:
                e.printStackTrace()
            if nbranches > 0:
                DSSGlobals.savedFileList.add(dirname + '/Branches.dss')
            else:
                fbranches.delete()
            if nshunts > 0:
                DSSGlobals.savedFileList.add(dirname + '/Shunts.dss')
            else:
                fshunts.delete()
            if nloads > 0:
                DSSGlobals.savedFileList.add(dirname + '/Loads.dss')
            else:
                floads.delete()
            if ngens > 0:
                DSSGlobals.savedFileList.add(dirname + '/Generators.dss')
            else:
                fgens.delete()
            if ncaps > 0:
                DSSGlobals.savedFileList.add(dirname + '/Capacitors.dss')
            else:
                fcaps.delete()

    def setDragHandRegister(self, reg, value):
        # FIXME Protected method in OpenDSS
        if value > self.registers[reg]:
            self.registers[reg] = value
            self.derivatives[reg] = value
            # use this for demand interval data

    def closeDemandIntervalFile(self):
        # FIXME Protected method in OpenDSS
        # Write registers to totals file
        try:
            if self.thisMeterDI_FileIsOpen:
                self.DI_File.close()
                self.thisMeterDI_FileIsOpen = False
                if self.VPhaseReportFileIsOpen:
                    self.VPhaseFile.close()
                self.VPhaseReportFileIsOpen = False
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error closing demand interval file for meter \"' + self.getName() + '\"', 534)
        meterTotalsPrinter = self.PrintWriter(DSSGlobals.energyMeterClass.getMeterTotals())
        meterTotalsPrinter.print_('\"' + self.getName() + '\"')
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            meterTotalsPrinter.printf(', %-g', self.registers[i])
        print 
        meterTotalsPrinter.close()

    def openDemandIntervalFile(self):
        try:
            if self.thisMeterDI_FileIsOpen:
                self.closeDemandIntervalFile()
            if DSSGlobals.energyMeterClass.isDIVerbose():
                self.DI_File = self.FileWriter(self.makeDIFileName())
                DI_Printer = self.PrintWriter(self.DI_File)
                self.thisMeterDI_FileIsOpen = True
                DI_Printer.print_('\"Hour\"')
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    DI_Printer.print_(', \"' + self.registerNames[i] + '\"')
                print 
                DI_Printer.close()
                # Phase voltage report, if requested
                if self.phaseVoltageReport:
                    self.VPhaseFile = self.FileWriter(self.makeVPhaseReportFileName())
                    VPhase_Printer = self.PrintWriter(self.VPhaseFile)
                    self.VPhaseReportFileIsOpen = True
                    VPhase_Printer.write('\"Hour\"')
                    _1 = True
                    i = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            i += 1
                        if not (i < self.maxVBaseCount):
                            break
                        VBase = self.VBaseList[i] * DSSGlobals.SQRT3
                        if VBase > 0.0:
                            _2 = True
                            j = 0
                            while True:
                                if _2 is True:
                                    _2 = False
                                else:
                                    j += 1
                                if not (j < 3):
                                    break
                                VPhase_Printer.printf(', %.3gkV_Phs_%d_Max', VBase, j)
                                VPhase_Printer.printf(', %.3gkV_Phs_%d_Min', VBase, j)
                                VPhase_Printer.printf(', %.3gkV_Phs_%d_Avg', VBase, j)
                    print 
                    VPhase_Printer.close()
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + self.getName() + '.csv' + ' for writing.' + DSSGlobals.CRLF + e.getMessage(), 535)

    def myCountAvg(self, value, count):
        if count == 0:
            return 0.0
        else:
            return value / count

    def writeDemandIntervalData(self):
        # FIXME Protected method in OpenDSS
        sol = DSSGlobals.activeCircuit.getSolution()
        if DSSGlobals.energyMeterClass.isDIVerbose() and self.thisMeterDI_FileIsOpen:
            DI_Printer = self.PrintWriter(self.DI_File)
            DI_Printer.printf('%-.6g', sol.getDblHour())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < EnergyMeter.NUM_EM_REGISTERS):
                    break
                DI_Printer.printf(', %-.6g', self.derivatives[i])
            print 
            DI_Printer.close()
        # Add to class demand interval registers
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            DSSGlobals.energyMeterClass.getDI_RegisterTotals()[i] += self.derivatives[i] * self.totalsMask[i]
            # Phase voltage report, if requested
        if self.VPhaseReportFileIsOpen:
            VPhase_Printer = self.PrintWriter(self.VPhaseFile)
            VPhase_Printer.printf('%-.6g', sol.getDblHour())
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.maxVBaseCount):
                    break
                if self.VBaseList[i] > 0.0:
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < 3):
                            break
                        VPhase_Printer.printf(', %-.6g', 0.001 * self.VPhaseMax[self.jiIndex(j, i)])
                        VPhase_Printer.printf(', %-.6g', 0.001 * self.VPhaseMin[self.jiIndex(j, i)])
                        VPhase_Printer.printf(', %-.6g', 0.001 * self.myCountAvg(self.VPhaseAccum[self.jiIndex(j, i)], self.VPhaseAccumCount[self.jiIndex(j, i)]))
            print 

    def appendDemandIntervalFile(self):
        """Only called if "SaveDemandInterval"."""
        if self.thisMeterDI_FileIsOpen:
            return
        try:
            if DSSGlobals.energyMeterClass.isDIVerbose():
                fileName = self.makeDIFileName()
                # creates directory if it doesn't exist
                # File must exist
                if self.File(fileName).exists():
                    self.DI_File = self.FileWriter(fileName, True)
                else:
                    self.DI_File = self.FileWriter(fileName, False)
                self.thisMeterDI_FileIsOpen = True
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + self.getName() + '.csv' + ' for appending.' + DSSGlobals.CRLF + e.getMessage(), 537)

    def assignVoltBaseRegisterNames(self):
        ireg = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.maxVBaseCount):
                break
            if self.VBaseList[i] > 0.0:
                VBase = self.VBaseList[i] * DSSGlobals.SQRT3
                self.registerNames[i + EnergyMeter.REG_VBASE_START] = String.format.format('%.3g kV Losses', VBase)
                self.registerNames[i + (1 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('%.3g kV Line Loss', VBase)
                self.registerNames[i + (2 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('%.3g kV Load Loss', VBase)
                self.registerNames[i + (3 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('%.3g kV No Load Loss', VBase)
                self.registerNames[i + (4 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('%.3g kV Load Energy', VBase)
            else:
                self.registerNames[i + EnergyMeter.REG_VBASE_START] = String.format.format('Aux%d', ireg)
                ireg += 1
                self.registerNames[i + (1 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('Aux%d', ireg)
                ireg += 1
                self.registerNames[i + (2 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('Aux%d', ireg)
                ireg += 1
                self.registerNames[i + (3 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('Aux%d', ireg)
                ireg += 1
                self.registerNames[i + (4 * self.maxVBaseCount) + EnergyMeter.REG_VBASE_START] = String.format.format('Aux%d', ireg)
                ireg += 1
        _1 = True
        i = EnergyMeter.REG_VBASE_START + 5
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            # TODO Check zero based indexing
            self.registerNames[i] = String.format.format('Aux%d', ireg)
            ireg += 1

    def makeFeederObj(self):
        # FIXME Private method in OpenDSS
        if self.meteredElement is not None:
            # Globals.getFeederClass().newObject(getName());  // newObject creates only if not existent else inits and desynchs
            # setFeederObj( (FeederObj) DSSGlobals.activeCircuit.getActiveCktElement() );
            # getFeederObj().setBus(1, MeteredElement.getBus(MeteredTerminal));  // TODO Check zero based indexing
            # getFeederObj().setNPhases(MeteredElement.getNPhases());
            # getFeederObj().setNConds(MeteredElement.getNConds());
            # getFeederObj().setEnabled(DSSGlobals.activeCircuit.isRadialSolution());
            pass
        else:
            DSSGlobals.doSimpleMsg('Error: Attempted to make Feeder object without instantiating meteredElement in EnergyMeter.' + self.getName(), 544)

    def removeFeederObj(self):
        if self.feederObj is not None:
            self.feederObj.setEnabled(False)
            self.feederObj.setCktElementFeederFlags(False)

    def enableFeeder(self):
        """HasFeeder has to be true before FeederObj will be re-enabled."""
        if self.hasFeeder:
            if self.feederObj is None:
                self.makeFeederObj()
            else:
                self.feederObj.setEnabled(True)
            self.feederObj.setCktElementFeederFlags(True)

    def makeDIFileName(self):
        return DSSGlobals.energyMeterClass.getDI_Dir() + '/' + self.getName() + '.csv'

    def getRegisterNames(self):
        return self.registerNames

    def setRegisterNames(self, names):
        self.registerNames = names

    def getBranchList(self):
        return self.branchList

    def setBranchList(self, list):
        self.branchList = list

    def getRegisters(self):
        return self.registers

    def setRegisters(self, values):
        self.registers = values

    def getDerivatives(self):
        return self.derivatives

    def setDerivatives(self, deriv):
        self.derivatives = deriv

    def getTotalsMask(self):
        return self.totalsMask

    def setTotalsMask(self, totals):
        # FIXME Private members in OpenDSS
        self.totalsMask = totals

    def isFirstSampleAfterReset(self):
        return self.firstSampleAfterReset

    def setFirstSampleAfterReset(self, firstSample):
        self.firstSampleAfterReset = firstSample

    def isExcessFlag(self):
        return self.excessFlag

    def setExcessFlag(self, excess):
        self.excessFlag = excess

    def zoneIsRadial(self):
        return self.zoneIsRadial

    def setZoneIsRadial(self, isRadial):
        self.zoneIsRadial = isRadial

    def isVoltageUEOnly(self):
        return self.voltageUEOnly

    def setVoltageUEOnly(self, value):
        self.voltageUEOnly = value

    def isLocalOnly(self):
        return self.localOnly

    def setLocalOnly(self, local):
        self.localOnly = local

    def hasFeeder(self):
        return self.hasFeeder

    def setHasFeeder(self, value):
        self.hasFeeder = value

    def isLosses(self):
        return self.losses

    def setLosses(self, value):
        self.losses = value

    def isLineLosses(self):
        return self.lineLosses

    def setLineLosses(self, losses):
        self.lineLosses = losses

    def isXfmrLosses(self):
        return self.xfmrLosses

    def setXfmrLosses(self, losses):
        self.xfmrLosses = losses

    def isSeqLosses(self):
        return self.seqLosses

    def setSeqLosses(self, losses):
        self.seqLosses = losses

    def is3PhaseLosses(self):
        return self.threePhaseLosses

    def set3PhaseLosses(self, losses):
        self.threePhaseLosses = losses

    def isVBaseLosses(self):
        return self.VBaseLosses

    def setVBaseLosses(self, losses):
        self.VBaseLosses = losses

    def isPhaseVoltageReport(self):
        return self.phaseVoltageReport

    def setPhaseVoltageReport(self, report):
        self.phaseVoltageReport = report

    def getFeederObj(self):
        return self.feederObj

    def setFeederObj(self, feeder):
        self.feederObj = feeder

    def getDefinedZoneList(self):
        return self.definedZoneList

    def setDefinedZoneList(self, list):
        self.definedZoneList = list

    def getDefinedZoneListSize(self):
        return self.definedZoneListSize

    def setDefinedZoneListSize(self, size):
        self.definedZoneListSize = size

    def getMaxZoneKVANorm(self):
        return self.maxZoneKVANorm

    def setMaxZoneKVANorm(self, max):
        self.maxZoneKVANorm = max

    def getMaxZoneKVAEmerg(self):
        return self.maxZoneKVAEmerg

    def setMaxZoneKVAEmerg(self, max):
        self.maxZoneKVAEmerg = max

    def getVBaseTotalLosses(self):
        return self.VBaseTotalLosses

    def setVBaseTotalLosses(self, losses):
        self.VBaseTotalLosses = losses

    def getVBaseLineLosses(self):
        return self.VBaseLineLosses

    def setVBaseLineLosses(self, losses):
        self.VBaseLineLosses = losses

    def getVBaseLoadLosses(self):
        return self.VBaseLoadLosses

    def setVBaseLoadLosses(self, losses):
        self.VBaseLoadLosses = losses

    def getVBaseNoLoadLosses(self):
        return self.VBaseNoLoadLosses

    def setVBaseNoLoadLosses(self, losses):
        self.VBaseNoLoadLosses = losses

    def getVBaseLoad(self):
        return self.VBaseLoad

    def setVBaseLoad(self, load):
        self.VBaseLoad = load

    def getVBaseList(self):
        return self.VBaseList

    def setVBaseList(self, list):
        self.VBaseList = list

    def getVBaseCount(self):
        return self.VBaseCount

    def setVBaseCount(self, count):
        self.VBaseCount = count

    def getMaxVBaseCount(self):
        return self.maxVBaseCount

    def setMaxVBaseCount(self, count):
        self.maxVBaseCount = count

    def getVPhaseMax(self):
        return self.VPhaseMax

    def setVPhaseMax(self, max):
        self.VPhaseMax = max

    def getVPhaseMin(self):
        return self.VPhaseMin

    def setVPhaseMin(self, min):
        self.VPhaseMin = min

    def getVPhaseAccum(self):
        return self.VPhaseAccum

    def setVPhaseAccum(self, accum):
        self.VPhaseAccum = accum

    def getVPhaseAccumCount(self):
        return self.VPhaseAccumCount

    def setVPhaseAccumCount(self, count):
        self.VPhaseAccumCount = count

    def getVPhaseFile(self):
        return self.VPhaseFile

    def setVPhaseFile(self, file):
        self.VPhaseFile = file

    def isVPhaseReportFileOpen(self):
        return self.VPhaseReportFileIsOpen

    def setVPhaseReportFileOpen(self, isOpen):
        self.VPhaseReportFileIsOpen = isOpen

    def getDIFile(self):
        return self.DI_File

    def setDIFile(self, file):
        self.DI_File = file

    def isThisMeterDIFileOpen(self):
        return self.thisMeterDI_FileIsOpen

    def setThisMeterDIFileOpen(self, isOpen):
        self.thisMeterDI_FileIsOpen = isOpen

from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.HashListImpl import (HashListImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.executive.impl.DSSExecutive import (DSSExecutive,)
from dss.common.AutoAdd import (AutoAdd,)
from dss.common.Solution import (Solution,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class AutoAddImpl(object, AutoAdd):
    """Unit for processing the AutoAdd solution functions.

    Note: Make sure this class in instantiated after EnergyMeter class.

    There is one of these per circuit.
    """
    generatorClass = None
    capacitorClass = None
    busIdxList = None
    busIdxListSize = None
    busIdxListCreated = None
    lastAddedGenerator = None
    lastAddedCapacitor = None
    busIndex = None
    phases = None
    Ycap = None
    GenVA = None
    kWLosses = None
    baseLosses = None
    puLossImprovement = None
    kWEEN = None
    baseEEN = None
    puEENImprovement = None
    log = None
    # log file
    progressCount = None
    # AutoAdd mode variables
    genKW = None
    genPF = None
    genKVAr = None
    capKVAr = None
    addType = None
    modeChanged = None

    @classmethod
    def sumSelectedRegisters(cls, mtr, regs, count):
        result = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < count):
                break
            result += mtr.getRegisters()[regs[i]] * mtr.getTotalsMask()[regs[i]]
        return result

    def __init__(self):
        self.busIdxListCreated = False
        self.generatorClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('generator'))
        self.capacitorClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('capacitor'))
        # autoAdd defaults
        self.genKW = 1000.0
        self.genPF = 1.0
        self.capKVAr = 600.0
        self.addType = DSSGlobals.GENADD
        self.lastAddedGenerator = 0
        self.lastAddedCapacitor = 0
        self.modeChanged = True

    def makeBusList(self):
        """Make a list of unique bus names.
        If autoAddBusList in activeCircuit is not nil, use this list.
        Else, use the element lists in energy meters.
        If no energy meters, use all the buses in the active circuit.
        """
        if self.busIdxListCreated:
            self.busIdxList = [None] * 0
        busListCreatedHere = False
        self.busIdxListCreated = False
        # autoAddBusList exists in active circuit, use it (see set autoBusList)
        if DSSGlobals.activeCircuit.getAutoAddBusList().listSize() > 0:
            busList = DSSGlobals.activeCircuit.getAutoAddBusList()
        elif len(DSSGlobals.activeCircuit.getEnergyMeters()) == 0:
            # no energy meters in circuit
            # include all buses in the circuit
            self.busIdxListSize = DSSGlobals.activeCircuit.getBusList().listSize()
            self.busIdxList = Utilities.resizeArray(self.busIdxList, self.busIdxListSize)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.busIdxListSize):
                    break
                self.busIdxList[i] = i
            self.busIdxListCreated = True
            return
        else:
            # Construct bus list from energy meters zone lists
            # include only buses in energy meter lists
            # consider all meters
            busListCreatedHere = True
            busList = HashListImpl(DSSGlobals.activeCircuit.getNumBuses())
            for pMeter in DSSGlobals.activeCircuit.getEnergyMeters():
                if pMeter.getBranchList() is not None:
                    PDElem = pMeter.getBranchList().getFirst()
                    while # add only unique bus names PDElem is not None:
                        _1 = True
                        i = 0
                        while True:
                            if _1 is True:
                                _1 = False
                            else:
                                i += 1
                            if not (i < PDElem.getNTerms()):
                                break
                            bName = Utilities.stripExtension(PDElem.getBus(i))
                            retval = busList.find(bName)
                            if retval == -1:
                                # TODO Check zero based indexing
                                busList.add(bName)
                                # return value is index of bus
                        PDElem = pMeter.getBranchList().goForward()
        # make busIdxList from busList
        self.busIdxListSize = busList.listSize()
        self.busIdxList = Utilities.resizeArray(self.busIdxList, self.busIdxListSize)
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.busIdxListSize):
                break
            self.busIdxList[i] = DSSGlobals.activeCircuit.getBusList().find(busList.get(i))
        if busListCreatedHere:
            busList = None
        self.busIdxListCreated = True

    def getWeightedLosses(self):
        """Returns losses in metered part of circuit + weighted EEN values.

        If no meters, returns just total losses in circuit.

        Base everything on gen kW.
        """
        self.ComputekWLosses_EEN()
        if len(DSSGlobals.activeCircuit.getEnergyMeters()) == 0:
            # no energymeters in circuit
            # just go by total system losses
            self.puLossImprovement = (self.baseLosses - self.kWLosses) / self.genKW
            self.puEENImprovement = 0.0
            result = self.puLossImprovement
        else:
            ckt = DSSGlobals.activeCircuit
            self.puLossImprovement = (self.baseLosses - self.kWLosses) / self.genKW
            self.puEENImprovement = (self.baseEEN - self.kWEEN) / self.genKW
            result = (ckt.getLossWeight() * self.puLossImprovement) + (ckt.getUEWeight() * self.puEENImprovement)
        return result

    def appendToFile(self, whichFile, s):
        # FIXME Implement this method.
        raise self.UnsupportedOperationException()

    def getUniqueGenName(self):
        trialName = ''
        done = False
        while not done:
            done = True
            self.lastAddedGenerator += 1
            trialName = 'Gadd' + String.valueOf.valueOf(self.lastAddedGenerator)
            if self.generatorClass.find(trialName) is not None:
                done = False
        return trialName

    def getUniqueCapName(self):
        trialName = ''
        done = False
        while not done:
            done = True
            self.lastAddedCapacitor += 1
            trialName = 'Cadd' + String.valueOf.valueOf(self.lastAddedCapacitor)
            if self.capacitorClass.find(trialName) is not None:
                done = False
        return trialName

    def solve(self):
        """Automatically add caps or generators.

        Automatically add a specified size of generator or capacitor at the location
        that results in the lowest losses in either metered part of circuit or
        total circuit, if no meters.

        If metered, EEN is also added in with a selected weighting factor (see
        set ueweight= ... command).

        Thus, this algorithm placed generators and capacitors to minimize losses and
        potential unserved energy.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        # Algorithm:
        #     1) makes a list of buses to check, either
        #         a. Previously defined list
        #         b. Meter zone lists
        #         c. All buses, if neither of the above
        #     2) Inject a current corresponding to the generator
        #     3) Check test criteria
        #     4) Save result
        #     5) Add generator/capacitor to circuit

        result = 0
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if sol.getLoadModel() == DSSGlobals.ADMITTANCE:
            sol.setLoadModel(DSSGlobals.POWERFLOW)
            sol.setSystemYChanged(True)
            # force rebuild of system Y without loads
        # Do a preliminary snapshot solution to force definition of meter zones
        # and set bus lists

        DSSGlobals.energyMeterClass.resetAll()
        if sol.isSystemYChanged() or ckt.isBusNameRedefined():
            sol.solveSnap()
            self.modeChanged = True
        DSSGlobals.energyMeterClass.sampleAll()
        # Check to see if bus base voltages have been defined.
        if ckt.getBuses()[ckt.getNumBuses()].getKVBase() == 0.0:
            sol.setVoltageBases()
        if self.modeChanged:
            self.makeBusList()
            # make list of buses to check
            self.modeChanged = False
            # Keep same busIdxList if no changes
        sol.setIntervalHrs(1.0)
        # Start up Log File
        F = self.File(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'AutoAddLog.csv')
        # TODO Auto-generated catch block
        # for this solution mode, only the peak load condition is taken into account
        # load is adjusted for growth by year.
        try:
            FStream = self.FileWriter(F, False)
            FLog = self.BufferedWriter(FStream)
            FLog.write('\"Bus\", \"Base kV\", \"kW Losses\", \"% Improvement\", \"kW UE\", \"% Improvement\", \"Weighted Total\", \"Iterations\"')
            FLog.newLine()
            FLog.close()
            # close it now after clearing it out
        except IOException, e1:
            e1.printStackTrace()
        sol.setGeneratorDispRef()
        # Turn regulators and caps off while we are searching
        sol.setControlMode(DSSGlobals.CONTROLSOFF)
        self.setBaseLosses()
        # Establish base values
        _0 = self.addType
        _1 = False
        while True:
            if _0 == DSSGlobals.GENADD:
                _1 = True
                if ckt.isPositiveSequence():
                    testGenKW = self.genKW / 3.0
                else:
                    testGenKW = self.genKW
                if self.genPF != 0.0:
                    self.genKVAr = testGenKW * self.Math.sqrt((1.0 / self.Math.pow(self.genPF, 2)) - 1.0)
                    if self.genPF < 0.0:
                        self.genKVAr = -self.genKVAr
                else:
                    # Someone specified 0.0 PF
                    self.genPF = 1.0
                    self.genKVAr = 0.0
                minLossBus = 0
                # null string
                maxLossImproveFactor = -1e+50
                # very large negative number
                minBusPhases = 3
                # Progress meter
                DSSGlobals.DSSForms.progressCaption('AutoAdding Generators')
                progressMax = self.busIdxListSize
                self.progressCount = 0
                DSSGlobals.DSSForms.progressFormCaption(String.format.format('Testing %d buses. Please Wait... ', self.busIdxListSize))
                DSSGlobals.DSSForms.showPctProgress(0)
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.busIdxListSize):
                        break
                    self.progressCount += 1
                    self.busIndex = self.busIdxList[i]
                    if self.busIndex > 0:
                        # TODO Check zero based indexing
                        testBus = ckt.getBusList().get(self.busIndex)
                        # DSSForms.progressFormCaption("Testing bus" + TestBus);
                        if (self.progressCount % 20 == 0) or (i == self.busIdxListSize):
                            DSSGlobals.DSSForms.progressFormCaption(String.format.format('Testing bus %d/%d. ', i, self.busIdxListSize))
                            DSSGlobals.DSSForms.showPctProgress((100 * self.progressCount) / progressMax)
                        DSSGlobals.energyMeterClass.resetAll()
                        # Get the number of phases at this bus and the node ref and add into the aux current array
                        # Assume either a 3-phase or 1-phase generator
                        if ckt.getBuses()[self.busIndex].getNumNodesThisBus() < 3:
                            self.phases = 1
                        else:
                            self.phases = 3
                        self.GenVA = Complex((1000.0 * testGenKW) / self.phases, (1000.0 * self.genKVAr) / self.phases)
                        # - - - - - - - - - Solution - - - - - - - - - - - - - - -
                        ckt.setIsSolved(False)
                        sol.setUseAuxCurrents(True)
                        # calls injCurrents on callback
                        sol.solveSnap()
                        if ckt.isSolved():
                            # Only do this if solution converged else something might break
                            # in meter sampling.

                            DSSGlobals.energyMeterClass.sampleAll()
                            lossImproveFactor = self.getWeightedLosses()
                            # TODO Auto-generated catch block
                            try:
                                FStream = self.FileWriter(F, True)
                                # append
                                FLog = self.BufferedWriter(FStream)
                                FLog.write(String.format.format('\"%s\", %-g', testBus, ckt.getBuses()[self.busIndex].getKVBase() * DSSGlobals.SQRT3))
                                FLog.write(String.format.format(', %-g, %-g', self.kWLosses, self.puLossImprovement * 100.0))
                                FLog.write(String.format.format(', %-g, %-g', self.kWEEN, self.puEENImprovement * 100.0))
                                FLog.write(String.format.format(', %-g, %d', lossImproveFactor, sol.getIteration()))
                                FLog.newLine()
                                FLog.close()
                            except IOException, e:
                                e.printStackTrace()
                            if lossImproveFactor > maxLossImproveFactor:
                                maxLossImproveFactor = lossImproveFactor
                                minLossBus = self.busIndex
                                minBusPhases = self.phases
                    if DSSGlobals.solutionAbort:
                        break
                # Put control mode back to default before inserting generator for real
                sol.setControlMode(DSSGlobals.CTRLSTATIC)
                sol.setUseAuxCurrents(False)
                if minLossBus > 0:
                    exec_ = DSSExecutive.getInstance()
                    if minBusPhases >= 3:
                        kVrat = ckt.getBuses()[minLossBus].getKVBase() * DSSGlobals.SQRT3
                    else:
                        kVrat = ckt.getBuses()[minLossBus].getKVBase()
                    commandString = 'New, generator.' + self.getUniqueGenName() + ', bus1=\"' + ckt.getBusList().get(minLossBus) + '\", phases=' + String.valueOf.valueOf(minBusPhases) + ', kv=' + String.format.format('%-g', kVrat) + ', kw=' + String.format.format('%-g', testGenKW) + ', ' + String.format.format('%5.2f', self.genPF) + String.format.format('! Factor =  %-g (%-.3g, %-.3g)', maxLossImproveFactor, ckt.getLossWeight(), ckt.getUEWeight())
                    exec_.setCommand(commandString)
                    # defines generator
                    # append this command to '...AutoAddedGenerators.txt'
                    self.appendToFile('Generators', commandString)
                    sol.solveSnap()
                    # force rebuilding of lists
                # return location of added generator
                DSSGlobals.globalResult = ckt.getBusList().get(minLossBus) + String.format.format(', %-g', maxLossImproveFactor)
                DSSGlobals.DSSForms.progressHide()
                break
            if (_1 is True) or (_0 == DSSGlobals.CAPADD):
                _1 = True
                minLossBus = 0
                # null string
                maxLossImproveFactor = -1e+50
                # very large negative number
                minBusPhases = 3
                if ckt.isPositiveSequence():
                    testCapKVAr = self.capKVAr / 3.0
                else:
                    testCapKVAr = self.capKVAr
                # Progress meter
                DSSGlobals.DSSForms.progressCaption('AutoAdding Capacitors')
                progressMax = self.busIdxListSize
                self.progressCount = 0
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.busIdxListSize):
                        break
                    self.progressCount += 1
                    # Make sure test bus is actually in the circuit
                    self.busIndex = self.busIdxList[i]
                    if self.busIndex > 0:
                        testBus = ckt.getBusList().get(self.busIndex)
                        DSSGlobals.DSSForms.progressFormCaption('Testing bus ' + testBus)
                        DSSGlobals.DSSForms.showPctProgress((100 * self.progressCount) / progressMax)
                        DSSGlobals.energyMeterClass.resetAll()
                        # Get the number of phases at this bus and the node ref and add into the aux current array
                        # Assume either a 3-phase or 1-phase capacitor
                        if ckt.getBuses()[self.busIndex].getNumNodesThisBus() < 3:
                            self.phases = 1
                        else:
                            self.phases = 3
                        # apply the capacitor at the bus rating
                        kVrat = ckt.getBuses()[self.busIndex].getKVBase()
                        # L-N Base kV
                        self.Ycap = (testCapKVAr * 0.001) / self.phases / (kVrat * kVrat)
                        # - - - - - - - - - Solution - - - - - - - - - - - - - - -
                        ckt.setIsSolved(False)
                        sol.setUseAuxCurrents(True)
                        # calls injCurrents on callback
                        sol.solveSnap()
                        if ckt.isSolved():
                            # Only do this if solution converged else something might break in meter sampling
                            DSSGlobals.energyMeterClass.sampleAll()
                            lossImproveFactor = self.getWeightedLosses()
                            # TODO: handle exception
                            try:
                                FStream = self.FileWriter(F, True)
                                # append
                                FLog = self.BufferedWriter(FStream)
                                FLog.write(String.format.format('\"%s\", %-g', testBus, ckt.getBuses()[self.busIndex].getKVBase() * DSSGlobals.SQRT3))
                                FLog.write(String.format.format(', %-g, %-g', self.kWLosses, self.puLossImprovement * 100.0))
                                FLog.write(String.format.format(', %-g, %-g', self.kWEEN, self.puEENImprovement * 100.0))
                                FLog.write(String.format.format(', %-g, %d', lossImproveFactor, sol.getIteration()))
                                FLog.newLine()
                                FLog.close()
                            except IOException, e:
                                pass # astStmt: [Stmt([]), None]
                            if lossImproveFactor > maxLossImproveFactor:
                                maxLossImproveFactor = lossImproveFactor
                                minLossBus = self.busIndex
                                minBusPhases = self.phases
                    if DSSGlobals.solutionAbort:
                        break
                # Put control mode back to default before inserting capacitor for real
                sol.setControlMode(DSSGlobals.CTRLSTATIC)
                sol.setUseAuxCurrents(False)
                if minLossBus > 0:
                    exec_ = DSSExecutive.getInstance()
                    if minBusPhases >= 3:
                        kVrat = ckt.getBuses()[minLossBus].getKVBase() * DSSGlobals.SQRT3
                    else:
                        kVrat = ckt.getBuses()[minLossBus].getKVBase()
                    commandString = 'New, Capacitor.' + self.getUniqueCapName() + ', bus1=\"' + ckt.getBusList().get(minLossBus) + '\", phases=' + String.valueOf.valueOf(minBusPhases) + ', kvar=' + String.format.format('%-g', testCapKVAr) + ', kv=' + String.format.format('%-g', kVrat)
                    exec_.setCommand(commandString)
                    # Defines capacitor
                    # append this command to 'DSSAutoAddedCapacitors.txt'
                    self.appendToFile('Capacitors', commandString)
                    sol.solveSnap()
                    # for rebuilding of lists, etc.
                # return location of added generator
                DSSGlobals.globalResult = ckt.getBusList().get(minLossBus)
                break
            break
        return result

    def addCurrents(self, solveType):
        """Compute injection currents for generator or capacitor and add into
        system currents array.
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        _0 = self.addType
        _1 = False
        while True:
            if _0 == DSSGlobals.GENADD:
                _1 = True
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.phases):
                        break
                    nRef = ckt.getBuses()[self.busIndex].getRef(i)
                    if nRef > 0:
                        # add in only non-ground currents  TODO Check zero indexing
                        busV = sol.getNodeV()[nRef]
                        if (busV.getReal() != 0.0) or (busV.getImaginary() != 0.0):
                            # Current into the system network
                            _3 = solveType
                            _4 = False
                            while True:
                                if _3 == Solution.NEWTONSOLVE:
                                    _4 = True
                                    sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(self.GenVA.divide(busV).conjugate().negate())
                                    # terminal current
                                    break
                                if (_4 is True) or (_3 == Solution.NORMALSOLVE):
                                    _4 = True
                                    sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(self.GenVA.divide(busV).conjugate())
                                    # injection Current
                                    break
                                break
                break
            if (_1 is True) or (_0 == DSSGlobals.CAPADD):
                _1 = True
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.phases):
                        break
                    nRef = ckt.getBuses()[self.busIndex].getRef(i)
                    if nRef > 0:
                        busV = sol.getNodeV()[nRef]
                        if (busV.getReal() != 0.0) or (busV.getImaginary() != 0.0):
                            # Current into the system network
                            _6 = solveType
                            _7 = False
                            while True:
                                if _6 == Solution.NEWTONSOLVE:
                                    _7 = True
                                    sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(Complex(0.0, self.Ycap).multiply(busV))
                                    # terminal current
                                    break
                                if (_7 is True) or (_6 == Solution.NORMALSOLVE):
                                    _7 = True
                                    sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(Complex(0.0, -self.Ycap).multiply(busV))
                                    # injection current
                                    break
                                break
                        # constant Y model
                break
            break

    def ComputekWLosses_EEN(self):
        ckt = DSSGlobals.activeCircuit
        if len(ckt.getEnergyMeters()) == 0:
            # no energymeters in circuit
            # just go by total system losses
            self.kWLosses = ckt.getLosses().getReal() * 0.001
            self.kWEEN = 0.0
        else:
            # sum losses in energy meters and add EEN
            self.kWLosses = 0.0
            self.kWEEN = 0.0
            for pMeter in ckt.getEnergyMeters():
                self.kWLosses = self.kWLosses + self.sumSelectedRegisters(pMeter, ckt.getLossRegs(), ckt.getNumLossRegs())
                self.kWEEN = self.kWEEN + self.sumSelectedRegisters(pMeter, ckt.getUERegs(), ckt.getNumUERegs())

    def setBaseLosses(self):
        self.ComputekWLosses_EEN()
        self.baseLosses = self.kWLosses
        self.baseEEN = self.kWEEN

    def getGenKW(self):
        return self.genKW

    def setGenKW(self, genkW):
        self.genKW = genkW

    def getGenPF(self):
        return self.genPF

    def setGenPF(self, genpf):
        self.genPF = genpf

    def getGenKVAr(self):
        return self.genKVAr

    def setGenKVAr(self, genkvar):
        self.genKVAr = genkvar

    def getCapKVAr(self):
        return self.capKVAr

    def setCapKVAr(self, capkvar):
        self.capKVAr = capkvar

    def getAddType(self):
        return self.addType

    def setAddType(self, addtype):
        self.addType = addtype

    def isModeChanged(self):
        return self.modeChanged

    def setModeChanged(self, modeChanged):
        self.modeChanged = modeChanged

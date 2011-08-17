from dss.shared.impl.CktTreeImpl import (CktTreeImpl,)
from dss.meter.impl.EnergyMeterObjImpl import (EnergyMeterObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.meter.impl.MeterClassImpl import (MeterClassImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.impl.SystemMeterImpl import (SystemMeterImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.meter.EnergyMeter import (EnergyMeter,)
# from java.io.BufferedWriter import (BufferedWriter,)
# from java.io.File import (File,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.IOException import (IOException,)
# from java.io.PrintWriter import (PrintWriter,)
# from java.util.List import (List,)


class EnergyMeterImpl(MeterClassImpl, EnergyMeter):
    # adjacency lists for PC and PD elements at each bus, built for faster searches
    busAdjPC = None
    # also includes shunt PD elements
    busAdjPD = None
    activeEnergyMeterObj = None
    generatorClass = None
    saveDemandInterval = None
    DI_Verbose = None
    overloadFile = None
    voltageFile = None
    DI_RegisterTotals = None
    DI_Dir = None
    DI_Totals = None
    meterTotals = None
    systemMeter = None
    doOverloadReport = None
    doVoltageExceptionReport = None
    overloadFileIsOpen = None
    voltageFileIsOpen = None

    def __init__(self):
        super(EnergyMeterImpl, self)()
        self.className = 'EnergyMeter'
        self.classType = self.classType + DSSClassDefs.ENERGY_METER
        self.activeElement = -1
        # Initialise demand interval options to off
        self.saveDemandInterval = False
        self.DI_Verbose = False
        self.doOverloadReport = False
        # saveDemandInterval must be true for this to have an effect
        self.overloadFileIsOpen = False
        self.voltageFileIsOpen = False
        self.DI_Dir = ''
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)
        self.generatorClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('generator'))
        self.systemMeter = SystemMeterImpl()

    def defineProperties(self):
        self.numProperties = EnergyMeter.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'element'
        self.propertyName[1] = 'terminal'
        self.propertyName[2] = 'action'
        self.propertyName[3] = 'option'
        self.propertyName[4] = 'kVAnormal'
        self.propertyName[5] = 'kVAemerg'
        self.propertyName[6] = 'peakcurrent'
        self.propertyName[7] = 'Zonelist'
        self.propertyName[8] = 'LocalOnly'
        self.propertyName[9] = 'Mask'
        self.propertyName[10] = 'Losses'
        self.propertyName[11] = 'LineLosses'
        self.propertyName[12] = 'XfmrLosses'
        self.propertyName[13] = 'SeqLosses'
        self.propertyName[14] = '3phaseLosses'
        self.propertyName[15] = 'VbaseLosses'
        # segregate losses by voltage base
        self.propertyName[16] = 'PhaseVoltageReport'
        # compute avg phase voltages in zone
        # PropertyName[10] = "Feeder";  **** removed - not used
        self.propertyHelp[0] = 'Name (Full Object name) of element to which the monitor is connected.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the monitor is connected. ' + '1 or 2, typically.'
        self.propertyHelp[2] = '{Clear (reset) | Save | Take | Zonedump | Allocate | Reduce} ' + DSSGlobals.CRLF + DSSGlobals.CRLF + '(A)llocate = Allocate loads on the meter zone to match PeakCurrent.' + DSSGlobals.CRLF + '(C)lear = reset all registers to zero' + DSSGlobals.CRLF + '(R)educe = reduces zone by merging lines (see Set Keeplist & ReduceOption)' + DSSGlobals.CRLF + '(S)ave = saves the current register values to a file.' + DSSGlobals.CRLF + '   File name is \"MTR_metername.csv\".' + DSSGlobals.CRLF + '(T)ake = Takes a sample at present solution' + DSSGlobals.CRLF + '(Z)onedump = Dump names of elements in meter zone to a file' + DSSGlobals.CRLF + '   File name is \"Zone_metername.csv\".'
        self.propertyHelp[3] = 'Enter a string ARRAY of any combination of the following. Options processed left-to-right:' + DSSGlobals.CRLF + DSSGlobals.CRLF + '(E)xcess : (default) UE/EEN is estimate of energy over capacity ' + DSSGlobals.CRLF + '(T)otal : UE/EEN is total energy after capacity exceeded' + DSSGlobals.CRLF + '(R)adial : (default) Treats zone as a radial circuit' + DSSGlobals.CRLF + '(M)esh : Treats zone as meshed network (not radial).' + DSSGlobals.CRLF + '(C)ombined : (default) Load UE/EEN computed from combination of overload and undervoltage.' + DSSGlobals.CRLF + '(V)oltage : Load UE/EEN computed based on voltage only.' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Example: option=(E, R)'
        self.propertyHelp[4] = 'Upper limit on kVA load in the zone, Normal configuration. Default is 0.0 (ignored). ' + 'Overrides limits on individual lines for overload EEN. ' + 'With \"LocalOnly=Yes\" option, uses only load in metered branch.'
        self.propertyHelp[5] = 'Upper limit on kVA load in the zone, Emergency configuration. Default is 0.0 (ignored). ' + 'Overrides limits on individual lines for overload UE. ' + 'With \"LocalOnly=Yes\" option, uses only load in metered branch.'
        self.propertyHelp[6] = 'ARRAY of current magnitudes representing the peak currents measured at this location ' + 'for the load allocation function.  Default is (400, 400, 400). Enter one current for each phase'
        self.propertyHelp[7] = 'ARRAY of full element names for this meter's zone.  Default is for meter to find it's own zone. ' + 'If specified, DSS uses this list instead.  Can access the names in a single-column text file.  Examples: ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'zonelist=[line.L1, transformer.T1, Line.L3] ' + DSSGlobals.CRLF + 'zonelist=(file=branchlist.txt)'
        self.propertyHelp[8] = '{Yes | No}  Default is NO.  If Yes, meter considers only the monitored element ' + 'for EEN and UE calcs.  Uses whole zone for losses.'
        self.propertyHelp[9] = 'Mask for adding registers whenever all meters are totalized.  Array of floating point numbers ' + 'representing the multiplier to be used for summing each register from this meter. ' + 'Default = (1, 1, 1, 1, ... ).  You only have to enter as many as are changed (positional). ' + 'Useful when two meters monitor same energy, etc.'
        self.propertyHelp[10] = '{Yes | No}  Default is YES. Compute Zone losses. If NO, then no losses at all are computed.'
        self.propertyHelp[11] = '{Yes | No}  Default is YES. Compute Line losses. If NO, then none of the losses are computed.'
        self.propertyHelp[12] = '{Yes | No}  Default is YES. Compute Transformer losses. If NO, transformers are ignored in loss calculations.'
        self.propertyHelp[13] = '{Yes | No}  Default is YES. Compute Sequence losses in lines and segregate by line mode losses and zero mode losses.'
        self.propertyHelp[14] = '{Yes | No}  Default is YES. Compute Line losses and segregate by 3-phase and other (1- and 2-phase) line losses. '
        self.propertyHelp[15] = '{Yes | No}  Default is YES. Compute losses and segregate by voltage base. If NO, then voltage-based tabulation is not reported.'
        self.propertyHelp[16] = '{Yes | No}  Default is NO.  Report min, max, and average phase voltages for the zone and tabulate by voltage base. ' + 'Demand Intervals must be turned on (Set Demand=true) and voltage bases must be defined for this property to take effect. ' + 'Result is in a separate report file.'
        # PropertyHelp[10] = "{Yes/True | No/False}  Default is NO. If set to Yes, a Feeder object is created corresponding to " +
        # 				"the energymeter.  Feeder is enabled if Radial=Yes; diabled if Radial=No.  Feeder is " +
        # 				"synched automatically with the meter zone.  Do not create feeders for zones in meshed transmission systems.";

        self.activeProperty = EnergyMeter.NumPropsThisClass - 1
        super(EnergyMeterImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(EnergyMeterObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeEnergyMeterObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeEnergyMeterObj)
        result = 0
        doRecalc = False
        aem = self.activeEnergyMeterObj
        aem.setMeteredElementChanged(False)
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                aem.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + aem.getName() + '\"', 520)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    aem.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    aem.setMeteredTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    _2 = param.toLowerCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'a':
                            _3 = True
                            aem.allocateLoad()
                            break
                        if (_3 is True) or (_2 == 'c'):
                            _3 = True
                            aem.resetRegisters()
                            break
                        if (_3 is True) or (_2 == 'r'):
                            _3 = True
                            aem.reduceZone()
                            break
                        if (_3 is True) or (_2 == 's'):
                            _3 = True
                            aem.saveRegisters()
                            break
                        if (_3 is True) or (_2 == 't'):
                            _3 = True
                            aem.takeSample()
                            break
                        if (_3 is True) or (_2 == 'z'):
                            _3 = True
                            aem.zoneDump()
                            break
                        break
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    self.processOptions(param)
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    aem.setMaxZoneKVANorm(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    aem.setMaxZoneKVAEmerg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    parser.parseAsVector(aem.getNPhases(), aem.getSensorCurrent())
                    # inits to zero
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    Utilities.interpretAndAllocStrArray(param, aem.getDefinedZoneListSize(), aem.getDefinedZoneList())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    aem.setLocalOnly(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    self.interpretRegisterMaskArray(aem.getTotalsMask())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    aem.setLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    aem.setLineLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    aem.setXfmrLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    aem.setSeqLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    aem.set3PhaseLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    aem.setVBaseLosses(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    aem.setPhaseVoltageReport(Utilities.interpretYesNo(param))
                    break
                    # case 10: aem.setHasFeeder(Utilities.interpretYesNo(Param)); break;
                if True:
                    _1 = True
                    self.classEdit(self.activeEnergyMeterObj, paramPointer - EnergyMeter.NumPropsThisClass)
                    break
                break
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 0:
                    _5 = True
                    aem.setMeteredElementChanged(True)
                    doRecalc = True
                    break
                if (_5 is True) or (_4 == 1):
                    _5 = True
                    aem.setMeteredElementChanged(True)
                    doRecalc = True
                    break
                if (_5 is True) or (_4 == 10):
                    _5 = True
                    if aem.hasFeeder():
                        doRecalc = True
                    else:
                        aem.removeFeederObj()
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        if doRecalc:
            aem.recalcElementData()
            # when some basic data have changed
        return result

    def makeLike(self, energyMeterName):
        result = 0
        # See if we can find this EnergyMeter name in the present collection
        otherEnergyMeter = self.find(energyMeterName)
        if otherEnergyMeter is not None:
            aem = self.activeEnergyMeterObj
            aem.setNPhases(otherEnergyMeter.getNPhases())
            aem.setNConds(otherEnergyMeter.getNConds())
            # force reallocation of terminal stuff
            aem.setElementName(otherEnergyMeter.getElementName())
            aem.setMeteredElement(otherEnergyMeter.getMeteredElement())
            # pointer to target circuit element
            aem.setMeteredTerminal(otherEnergyMeter.getMeteredTerminal())
            aem.setExcessFlag(otherEnergyMeter.isExcessFlag())
            aem.setMaxZoneKVANorm(otherEnergyMeter.getMaxZoneKVANorm())
            aem.setMaxZoneKVAEmerg(otherEnergyMeter.getMaxZoneKVAEmerg())
            aem.setDefinedZoneListSize(otherEnergyMeter.getDefinedZoneListSize())
            aem.setDefinedZoneList([None] * aem.getDefinedZoneListSize())
            # copy strings over (actually incr ref count on string)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < aem.getDefinedZoneListSize()):
                    break
                aem.getDefinedZoneList()[i] = otherEnergyMeter.getDefinedZoneList()[i]
            aem.setLocalOnly(otherEnergyMeter.isLocalOnly())
            aem.setVoltageUEOnly(otherEnergyMeter.isVoltageUEOnly())
            # Boolean flags
            aem.setLosses(otherEnergyMeter.isLosses())
            aem.setLineLosses(otherEnergyMeter.isLineLosses())
            aem.setXfmrLosses(otherEnergyMeter.isXfmrLosses())
            aem.setSeqLosses(otherEnergyMeter.isSeqLosses())
            aem.set3PhaseLosses(otherEnergyMeter.is3PhaseLosses())
            aem.setVBaseLosses(otherEnergyMeter.isVBaseLosses())
            aem.setPhaseVoltageReport(otherEnergyMeter.isPhaseVoltageReport())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < aem.getParentClass().getNumProperties()):
                    break
                aem.setPropertyValue(i, otherEnergyMeter.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in EnergyMeter makeLike: \"' + energyMeterName + '\" not found.', 521)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement EnergyMeter.init', -1)
        return 0

    def resetMeterZonesAll(self):
        ckt = DSSGlobals.activeCircuit
        if len(ckt.getEnergyMeters()) == 0:
            return
            # initialize the checked flag for all circuit elements.
        for pCktElement in ckt.getCktElements():
            pCktElement.setChecked(False)
            pCktElement.setIsolated(True)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < pCktElement.getNTerms()):
                    break
                pCktElement.getTerminals()[i].setChecked(False)
        # Clear some things that will be set by the meter zone
        for PDElem in ckt.getPDElements():
            PDElem.setMeterObj(None)
            PDElem.setSensorObj(None)
            PDElem.setParentPDElement(None)
        for PCElem in ckt.getPCElements():
            PCElem.setMeterObj(None)
            PCElem.setSensorObj(None)
        # set up the bus adjacency lists for faster searches to build meter zone lists
        CktTreeImpl.buildActiveBusAdjacencyLists(self.busAdjPD, self.busAdjPC)
        # Set hasMeter flag for all cktElements
        self.setHasMeterFlag()
        DSSGlobals.sensorClass.setHasSensorFlag()
        # set all sensor branch flags, too.
        # initialise the checked flag for all buses
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            ckt.getBuses()[i].setBusChecked(False)
        for pMeter in ckt.getEnergyMeters():
            if pMeter.isEnabled():
                pMeter.makeMeterZoneLists()
        CktTreeImpl.freeAndNilBusAdjacencyLists(self.busAdjPD, self.busAdjPC)

    def resetAll(self):
        """Reset all meters in active circuit to zero."""
        ckt = DSSGlobals.activeCircuit
        if DSSGlobals.DIFilesAreOpen:
            self.closeAllDIFiles()
        if self.saveDemandInterval:
            # Make directories to save data
            dir = File(ckt.getCaseName())
            if not dir.exists():
                try:
                    dir.mkdir()
                except Exception, e:
                    DSSGlobals.doSimpleMsg('Error making directory: \"' + ckt.getCaseName() + '\". ' + e.getMessage(), 522)
            self.DI_Dir = ckt.getCaseName() + '/DI_yr_' + String.valueOf.valueOf(ckt.getSolution().getYear()).trim()
            dir = File(self.DI_Dir)
            if not dir.exists():
                try:
                    dir.mkdir()
                except Exception, e:
                    DSSGlobals.doSimpleMsg('Error making demand interval directory: \"' + self.DI_Dir + '\". ' + e.getMessage(), 523)
            self.createDI_Totals()
            # TODO: handle exception
            try:
                self.DI_Totals.close()
            except IOException, e:
                pass # astStmt: [Stmt([]), None]
        for pMeter in ckt.getEnergyMeters():
            pMeter.resetRegisters()
        self.systemMeter.reset()
        # reset generator objects, too
        self.generatorClass.resetRegistersAll()
        DSSGlobals.storageClass.resetRegistersAll()

    def sampleAll(self):
        """Force all meters in active circuit to sample."""
        ckt = DSSGlobals.activeCircuit
        for meter in ckt.getEnergyMeters():
            if meter.isEnabled():
                meter.takeSample()
        self.systemMeter.takeSample()
        if self.saveDemandInterval:
            # Write totals demand interval file
            DI_TotalsPrinter = PrintWriter(self.DI_Totals)
            DI_TotalsPrinter.printf('%-.6g ', ckt.getSolution().getDblHour())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.NUM_EM_REGISTERS):
                    break
                DI_TotalsPrinter.printf(', %-.6g', self.DI_RegisterTotals[i])
            print 
            DI_TotalsPrinter.close()
            self.clearDI_Totals()
            if self.overloadFileIsOpen:
                self.writeOverloadReport()
            if self.voltageFileIsOpen:
                self.writeVoltageReport()
        # sample generator and storage objects, too
        self.generatorClass.sampleAll()
        DSSGlobals.storageClass.sampleAll()
        # samples energymeter part of storage elements (not update)

    def saveAll(self):
        """Force all EnergyMeters in the circuit to take a sample."""
        ckt = DSSGlobals.activeCircuit
        for meter in ckt.getEnergyMeters():
            if meter.isEnabled():
                meter.saveRegisters()
        self.systemMeter.save()

    def setHasMeterFlag(self):
        """Set the hasMeter flag for all cktElement;"""
        ckt = DSSGlobals.activeCircuit
        # Initialize all to false
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(ckt.getPDElements())):
                break
            cktElem = ckt.getPDElements().get(i)
            cktElem.setHasEnergyMeter(False)
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < len(ckt.getEnergyMeters())):
                break
            thisMeter = ckt.getEnergyMeters().get(i)
            if thisMeter.getMeteredElement() is not None:
                thisMeter.getMeteredElement().setHasEnergyMeter(True)

    def processOptions(self, opts):
        s2 = ' '
        DSSGlobals.auxParser.setCmdString(opts)
        # load up aux parser
        aem = self.activeEnergyMeterObj
        # Loop until no more options found
        while len(s2) > 0:
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            s2 = DSSGlobals.auxParser.makeString().toLowerCase()
            if len(s2) > 0:
                _0 = s2[0]
                _1 = False
                while True:
                    if _0 == 'e':
                        _1 = True
                        aem.setExcessFlag(True)
                        break
                    if (_1 is True) or (_0 == 't'):
                        _1 = True
                        aem.setExcessFlag(False)
                        break
                    if (_1 is True) or (_0 == 'r'):
                        _1 = True
                        aem.setZoneIsRadial(True)
                        break
                    if (_1 is True) or (_0 == 'm'):
                        _1 = True
                        aem.setZoneIsRadial(False)
                        break
                    if (_1 is True) or (_0 == 'c'):
                        _1 = True
                        aem.setVoltageUEOnly(False)
                        break
                    if (_1 is True) or (_0 == 'v'):
                        _1 = True
                        aem.setVoltageUEOnly(True)
                        break
                    break

    def closeAllDIFiles(self):
        if self.saveDemandInterval:
            # While closing DI files, write all meter registers to one file
            # Close all the DI file for each meter
            try:
                self.createMeterTotals()
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error on rewrite of totals file: ' + e.getMessage(), 536)
            for meter in DSSGlobals.activeCircuit.getEnergyMeters():
                if meter.isEnabled():
                    meter.closeDemandIntervalFile()
            self.writeTotalsFile()
            # sum all EnergyMeter registers to "Totals.csv"
            self.systemMeter.closeDemandIntervalFile()
            self.systemMeter.save()
            try:
                self.meterTotals.close()
                self.DI_Totals.close()
                DSSGlobals.DIFilesAreOpen = False
                if self.overloadFileIsOpen:
                    self.overloadFile.close()
                    self.overloadFileIsOpen = False
                if self.voltageFileIsOpen:
                    self.voltageFile.close()
                    self.voltageFileIsOpen = False
            except IOException, e:
                DSSGlobals.doSimpleMsg('Error closing file: ' + e.getMessage(), 537)

    def appendAllDIFiles(self):
        if self.saveDemandInterval:
            self.clearDI_Totals()
            # clears accumulator arrays
            for meter in DSSGlobals.activeCircuit.getEnergyMeters():
                if meter.isEnabled():
                    meter.appendDemandIntervalFile()
            self.systemMeter.appendDemandIntervalFile()
            # Open DI_Totals
            try:
                DI_TotalsFile = File(self.DI_Dir + '/DI_Totals.csv')
                # File Must Exist
                if DI_TotalsFile.exists():
                    self.DI_Totals = FileWriter(DI_TotalsFile, True)
                else:
                    self.createDI_Totals()
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + self.getName() + '.csv' + ' for appending.' + DSSGlobals.CRLF + e.getMessage(), 538)
            DSSGlobals.DIFilesAreOpen = True

    def setSaveDemandInterval(self, value):
        self.saveDemandInterval = value
        self.resetAll()

    def writeOverloadReport(self):
        """Scans the active circuit for overloaded PD elements and writes each to a file.
        This is called only if in Demand Interval (DI) mode and the file is open.
        """
        ckt = DSSGlobals.activeCircuit
        pw = PrintWriter(self.overloadFile)
        # Check PDElements only
        for PDElem in ckt.getPDElements():
            if PDElem.isEnabled() and not PDElem.isShunt():
                # ignore shunts
                if (PDElem.getNormAmps() > 0.0) or (PDElem.getEmergAmps() > 0.0):
                    PDElem.computeITerminal()
                    CMax = PDElem.maxTerminalOneIMag()
                    # for now, check only terminal 1 for overloads
                    if (CMax > PDElem.getNormAmps()) or (CMax > PDElem.getEmergAmps()):
                        pw.printf('%-.6g,', ckt.getSolution().getDblHour())
                        pw.printf(' %s, %-.4g, %-.4g,', Utilities.fullName(PDElem), PDElem.getNormAmps(), PDElem.getEmergAmps())
                        if PDElem.getNormAmps() > 0.0:
                            pw.printf(' %-.7g,', (CMax / PDElem.getNormAmps()) * 100.0)
                        else:
                            pw.print_(' 0.0,')
                        if PDElem.getEmergAmps() > 0.0:
                            pw.printf(' %-.7g,', (CMax / PDElem.getEmergAmps()) * 100.0)
                        else:
                            pw.print_(' 0.0,')
                        # Find bus of first terminal
                        pw.printf(' %-.3g ', ckt.getBuses()[ckt.getMapNodeToBus()[PDElem.getNodeRef()[0]].busRef].getKVBase())
                        print 
        pw.close()

    def clearDI_Totals(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            self.DI_RegisterTotals[i] = 0.0

    def createDI_Totals(self):
        try:
            self.DI_Totals = FileWriter(self.DI_Dir + '/DI_Totals.csv')
            # FileWriter DI_TotalsStream = new FileWriter(DI_Totals, false);
            DI_TotalsBuffer = BufferedWriter(self.DI_Totals)
            DI_TotalsBuffer.write('Time')
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(DSSGlobals.activeCircuit.getEnergyMeters())):
                    break
                meter = DSSGlobals.activeCircuit.getEnergyMeters().get(i)
                DI_TotalsBuffer.write(', \"' + meter.getRegisterNames()[i] + '\"')
            DI_TotalsBuffer.newLine()
            DI_TotalsBuffer.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error creating: \"' + self.DI_Dir + '/DI_Totals.csv\": ' + e.getMessage(), 539)

    def createMeterTotals(self):
        ckt = DSSGlobals.activeCircuit
        # TODO Auto-generated catch block
        try:
            meterTotalsFile = File(self.DI_Dir + '/EnergyMeterTotals.csv')
            self.meterTotals = FileWriter(meterTotalsFile)
            meterTotalsBuffer = BufferedWriter(self.meterTotals)
            meterTotalsBuffer.write('Name')
            mtr = ckt.getEnergyMeters().get(0)
            if mtr is not None:
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    meterTotalsBuffer.write(', \"' + mtr.getRegisterNames()[i] + '\"')
            meterTotalsBuffer.newLine()
            meterTotalsBuffer.close()
        except IOException, e:
            e.printStackTrace()

    def setDIVerbose(self, value):
        self.DI_Verbose = value
        self.resetAll()

    def writeTotalsFile(self):
        regSum = [None] * EnergyMeter.NUM_EM_REGISTERS
        # Sum up all registers of all meters and write to Totals.csv
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            regSum[i] = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < len(DSSGlobals.activeCircuit.getEnergyMeters())):
                break
            mtr = DSSGlobals.activeCircuit.getEnergyMeters().get(i)
            if mtr.isEnabled():
                _2 = True
                j = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j += 1
                    if not (j < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    regSum[j] = regSum[j] + (mtr.getRegisters()[j] * mtr.getTotalsMask()[j])
        # Write the file
        try:
            f = File(self.DI_Dir + '/Totals.csv')
            fw = FileWriter(f, False)
            bw = BufferedWriter(fw)
            bw.write('Year')
            mtr = DSSGlobals.activeCircuit.getEnergyMeters().get(0)
            if mtr is not None:
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < EnergyMeter.NUM_EM_REGISTERS):
                        break
                    bw.write(', \"' + mtr.getRegisterNames()[i] + '\"')
            bw.newLine()
            bw.write(DSSGlobals.activeCircuit.getSolution().getYear())
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < EnergyMeter.NUM_EM_REGISTERS):
                    break
                bw.write(String.format.format(', %-g ', regSum[i]))
            bw.newLine()
            bw.close()
            fw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file Totals.csv.' + DSSGlobals.CRLF + e.getMessage(), 543)

    def writeVoltageReport(self):
        # For any bus with a defined voltage base, test for > Vmax or < Vmin
        overCount = 0
        underCount = 0
        ckt = DSSGlobals.activeCircuit
        overVMax = ckt.getNormalMinVolts()
        underVMin = ckt.getNormalMaxVolts()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            bus = ckt.getBuses()[i]
            if bus.getKVBase() > 0.0:
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < bus.getNumNodesThisBus()):
                        break
                    VMagPU = (ckt.getSolution().getNodeV()[bus.getRef(j)].abs() / bus.getKVBase()) * 0.001
                    if VMagPU > 0.1:
                        # ignore neutral buses
                        underVMin = self.Math.min(underVMin, VMagPU)
                        overVMax = self.Math.max(overVMax, VMagPU)
                        if VMagPU < ckt.getNormalMinVolts():
                            underCount += 1
                            break
                            # next i
                        elif VMagPU > ckt.getNormalMaxVolts():
                            overCount += 1
                            break
        # for i
        VoltagePrinter = PrintWriter(self.voltageFile)
        VoltagePrinter.printf('%-.6g,', ckt.getSolution().getDblHour())
        VoltagePrinter.printf(' %d, %-.6g, %d, %-.6g', underCount, underVMin, overCount, overVMax)
        print 
        VoltagePrinter.close()

    def interpretRegisterMaskArray(self, mask):
        n = Parser.getInstance().parseAsVector(self.NUM_EM_REGISTERS, mask)
        _0 = True
        i = n
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < EnergyMeter.NUM_EM_REGISTERS):
                break
            # TODO Check zero based indexing
            mask[i] = 1.0
            # set the rest to 1

    def openAllDIFiles(self):
        """Similar to "append", by creates the files."""
        if self.saveDemandInterval:
            self.clearDI_Totals()
            # clears accumulator arrays
            for meter in DSSGlobals.activeCircuit.getEnergyMeters():
                if meter.isEnabled():
                    meter.openDemandIntervalFile()
            self.systemMeter.openDemandIntervalFile()
            # Optional exception reporting
            if self.doOverloadReport:
                self.openOverloadReportFile()
            if self.doVoltageExceptionReport:
                self.openVoltageReportFile()
                # Open DI_Totals
            try:
                self.createDI_Totals()
                # TODO Add throws exception
            except Exception, e:
                DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + self.getName() + '.csv' + ' for appending.' + DSSGlobals.CRLF + e.getMessage(), 538)
            DSSGlobals.DIFilesAreOpen = True

    def openOverloadReportFile(self):
        try:
            if self.overloadFileIsOpen:
                self.overloadFile.close()
            self.overloadFile = FileWriter(DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_Overloads.csv')
            pw = PrintWriter(self.overloadFile)
            self.overloadFileIsOpen = True
            pw.print_('\"Hour\", \"Element\", \"Normal Amps\", \"Emerg Amps\", \"% Normal\", \"% Emerg\", \"kVBase\"')
            print 
            pw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_Overloads.csv\"  for writing.' + DSSGlobals.CRLF + e.getMessage(), 541)

    def openVoltageReportFile(self):
        try:
            if self.voltageFileIsOpen:
                self.voltageFile.close()
            self.voltageFile = FileWriter(DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_VoltExceptions.csv')
            self.voltageFileIsOpen = True
            pw = PrintWriter(self.voltageFile)
            print '\"Hour\", \"Undervoltages\", \"Min Voltage\", \"Overvoltage\", \"Max Voltage\"'
            pw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_VoltExceptions.csv\" for writing.' + DSSGlobals.CRLF + e.getMessage(), 541)

    def isSaveDemandInterval(self):
        return self.saveDemandInterval

    def isDIVerbose(self):
        return self.DI_Verbose

    def getDI_RegisterTotals(self):
        return self.DI_RegisterTotals

    def setDI_RegisterTotals(self, totals):
        self.DI_RegisterTotals = totals

    def getDI_Dir(self):
        return self.DI_Dir

    def setDI_Dir(self, dir):
        self.DI_Dir = dir

    def getDI_Totals(self):
        return self.DI_Totals

    def setDI_Totals(self, totals):
        self.DI_Totals = totals

    def getMeterTotals(self):
        return self.meterTotals

    def setMeterTotals(self, totals):
        self.meterTotals = totals

    def getSystemMeter(self):
        return self.systemMeter

    def setSystemMeter(self, meter):
        self.systemMeter = meter

    def isDo_OverloadReport(self):
        return self.doOverloadReport

    def setDoOverloadReport(self, doReport):
        self.doOverloadReport = doReport

    def isDo_VoltageExceptionReport(self):
        return self.doVoltageExceptionReport

    def setDoVoltageExceptionReport(self, doReport):
        self.doVoltageExceptionReport = doReport

    def isOverLoadFileIsOpen(self):
        return self.overloadFileIsOpen

    def setOverLoadFileIsOpen(self, fileIsOpen):
        self.overloadFileIsOpen = fileIsOpen

    def isVoltageFileIsOpen(self):
        return self.voltageFileIsOpen

    def setVoltageFileIsOpen(self, fileIsOpen):
        self.voltageFileIsOpen = fileIsOpen

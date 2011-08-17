from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.meter.SystemMeter import (SystemMeter,)
from dss.meter.EnergyMeter import (EnergyMeter,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SystemMeterImpl(object, SystemMeter):
    registerArray = [None] * EnergyMeter.NUM_EM_REGISTERS
    kWh = MutableDouble()
    dkWh = MutableDouble()
    kvarh = MutableDouble()
    dkvarh = MutableDouble()
    Losseskwh = MutableDouble()
    dLosseskWh = MutableDouble()
    Losseskvarh = MutableDouble()
    dlosseskvarh = MutableDouble()
    peakKW = None
    peakKVA = None
    peakLossesKW = None
    firstSampleAfterReset = None
    thisMeterDIFileIsOpen = None
    systemDIFile = None
    cPower = None
    cLosses = None

    def __init__(self):
        # FIXME Protected method in OpenDSS
        self.clear()
        self.thisMeterDIFileIsOpen = False

    def appendDemandIntervalFile(self):
        """Only called if "SaveDemandInterval"."""
        fileName = ''
        if self.thisMeterDIFileIsOpen:
            return
        try:
            fileName = DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_SystemMeter.csv'
            # File must exist
            if self.File(fileName).exists():
                self.systemDIFile = self.FileWriter(fileName, True)
            else:
                self.systemDIFile = self.FileWriter(fileName, False)
            self.thisMeterDIFileIsOpen = True
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"' + fileName + ' for appending.' + DSSGlobals.CRLF + e.getMessage(), 540)

    def clear(self):
        # FIXME Protected method in OpenDSS
        self.kWh.setValue(0.0)
        self.kvarh.setValue(0.0)
        self.peakKW = 0.0
        self.peakKVA = 0.0
        self.Losseskwh.setValue(0.0)
        self.Losseskvarh.setValue(0.0)
        self.peakLossesKW = 0.0
        self.dkWh.setValue(0.0)
        self.dkvarh.setValue(0.0)
        self.dLosseskWh.setValue(0.0)
        self.dlosseskvarh.setValue(0.0)
        self.firstSampleAfterReset = True

    def closeDemandIntervalFile(self):
        if self.thisMeterDIFileIsOpen:
            try:
                self.systemDIFile.close()
                self.thisMeterDIFileIsOpen = False
            except IOException, e:
                DSSGlobals.doSimpleMsg('Error closing demand interval file.' + DSSGlobals.CRLF + e.getMessage(), 540)

    def integrate(self, reg, value, deriv):
        # FIXME Protected method in OpenDSS
        ckt = DSSGlobals.activeCircuit
        if ckt.isTrapezoidalIntegration():
            # Trapezoidal rule integration
            if not self.firstSampleAfterReset:
                reg.add(0.5 * ckt.getSolution().getIntervalHrs() * (value + deriv.doubleValue()))
        else:
            # Plain Euler integration
            reg.add(ckt.getSolution().getIntervalHrs() * value)
        deriv.setValue(value)

    def openDemandIntervalFile(self):
        try:
            if self.thisMeterDIFileIsOpen:
                self.systemDIFile.close()
            self.systemDIFile = self.FileWriter(DSSGlobals.energyMeterClass.getDI_Dir() + '/DI_SystemMeter.csv')
            systemDIPrinter = self.PrintWriter(self.systemDIFile)
            self.thisMeterDIFileIsOpen = True
            systemDIPrinter.print_('\"Hour\", ')
            self.writeRegisterNames(systemDIPrinter)
            print 
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening demand interval file \"DI_SystemMeter.csv\"  for writing.' + DSSGlobals.CRLF + e.getMessage(), 541)

    def reset(self):
        self.clear()

    def save(self):
        csvName = 'SystemMeter.csv'
        # If we are doing a simulation and saving interval data, create this in the
        # same directory as the demand interval data.

        try:
            if DSSGlobals.energyMeterClass.isSaveDemandInterval():
                Folder = DSSGlobals.energyMeterClass.getDI_Dir() + '/'
            else:
                Folder = DSSGlobals.DSSDataDirectory
            fw = self.FileWriter(Folder + csvName, False)
            pw = self.PrintWriter(fw)
            DSSGlobals.globalResult = csvName
            pw.write('Year, ')
            self.writeRegisterNames(pw)
            print 
            pw.print_(DSSGlobals.activeCircuit.getSolution().getYear())
            self.writeRegisters(pw)
            print 
            pw.close()
            fw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening system meter file \"' + DSSGlobals.CRLF + csvName + '\": ' + e.getMessage(), 542)

    def takeSample(self):
        """Get total system energy out of the sources."""
        self.cPower = Utilities.getTotalPowerFromSources().multiply(0.001)
        # convert to kW
        self.integrate(self.kWh, self.cPower.getReal(), self.dkWh)
        self.integrate(self.kvarh, self.cPower.getImaginary(), self.dkvarh)
        self.peakKW = self.Math.max(self.cPower.getReal(), self.peakKW)
        self.peakKVA = self.Math.max(self.cPower.abs(), self.peakKVA)
        # Get total circuit losses
        self.cLosses = DSSGlobals.activeCircuit.getLosses()
        # PD elements except shunts
        self.cLosses = self.cLosses.multiply(0.001)
        # convert to kW
        self.integrate(self.Losseskwh, self.cLosses.getReal(), self.dLosseskWh)
        self.integrate(self.Losseskvarh, self.cLosses.getImaginary(), self.dlosseskvarh)
        self.peakLossesKW = self.Math.max(self.cLosses.getReal(), self.peakLossesKW)
        self.firstSampleAfterReset = False
        if self.thisMeterDIFileIsOpen:
            self.writeDemandIntervalData()

    def writeDemandIntervalData(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        pw = self.PrintWriter(self.systemDIFile)
        pw.printf('%-.6g', sol.getDblHour())
        pw.printf(', %-g', self.cPower.getReal())
        pw.printf(', %-g', self.cPower.getImaginary())
        pw.printf(', %-g', self.peakKW)
        pw.printf(', %-g', self.peakKVA)
        pw.printf(', %-g', self.cLosses.getReal())
        pw.printf(', %-g', self.cLosses.getImaginary())
        pw.printf(', %-g', self.peakLossesKW)
        print 
        pw.close()

    def writeRegisterNames(self, f):
        f.print_('kWh, kvarh, \"Peak kW\", \"peak kVA\", \"Losses kWh\", \"Losses kvarh\", \"Peak Losses kW\"')

    def writeRegisters(self, f):
        f.printf(', %-g', self.kWh.doubleValue())
        f.printf(', %-g', self.kvarh.doubleValue())
        f.printf(', %-g', self.peakKW)
        f.printf(', %-g', self.peakKVA)
        f.printf(', %-g', self.Losseskwh.doubleValue())
        f.printf(', %-g', self.Losseskvarh.doubleValue())
        f.printf(', %-g', self.peakLossesKW)

from __pyjamas__ import (ARGERROR,)
from dss.meter.Monitor import (Monitor,)
from dss.meter.MonitorObj import (MonitorObj,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.impl.MeterElementImpl import (MeterElementImpl,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
# from java.io.CharArrayWriter import (CharArrayWriter,)
# from java.io.PrintStream import (PrintStream,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class MonitorObjImpl(MeterElementImpl, MonitorObj):
    strBuffer = str()
    bufferSize = None
    hour = None
    sec = None
    monBuffer = None
    bufPtr = None
    currentBuffer = None
    voltageBuffer = None
    numStateVars = None
    stateBuffer = None
    includeResidual = None
    VIPolar = None
    PPolar = None
    fileSignature = None
    fileVersion = None
    # private double BaseFrequency;
    bufferFile = None
    isFileOpen = None
    validMonitor = None
    mode = None
    monitorStream = None
    sampleCount = None
    # this is the number of samples taken

    def __init__(self, parClass, monitorName):
        super(MonitorObjImpl, self)(parClass)
        self.setName(monitorName.toLowerCase())
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.currentBuffer = None
        self.voltageBuffer = None
        self.stateBuffer = None
        self.baseFrequency = 60.0
        self.hour = 0
        self.sec = 0.0
        self.mode = 0
        # standard mode: V & I, complex values
        self.bufferSize = 1024
        # makes a 4K buffer
        self.monBuffer = [None] * self.bufferSize
        self.bufPtr = 0
        # default to first circuit element (source)
        self.elementName = DSSGlobals.activeCircuit.getCktElements().get(0).getName()
        self.meteredElement = None
        self.bufferFile = ''
        self.monitorStream = CharArrayWriter()
        # create memory stream
        self.isFileOpen = False
        self.meteredTerminal = 1
        self.includeResidual = False
        self.VIPolar = True
        self.PPolar = True
        self.fileSignature = 43756
        self.fileVersion = 1
        self.sampleCount = 0
        self.objType = parClass.getDSSClassType()
        # MON_ELEMENT;
        self.initPropertyValues(0)

    def convertBlanks(self, s):
        """Convert spaces to underscores."""
        s.replace(' ', '_')

    def recalcElementData(self):
        self.validMonitor = False
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # monitored element must already exist
            self.meteredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            _0 = self.mode & Monitor.MODEMASK
            _1 = False
            while True:
                if _0 == 2:
                    _1 = True
                    if (
                        self.meteredElement.getDSSObjType() & DSSClassDefs.CLASSMASK != DSSClassDefs.XFMR_ELEMENT
                    ):
                        DSSGlobals.doSimpleMsg(self.meteredElement.getName() + ' is not a transformer!', 663)
                        return
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    if (
                        self.meteredElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK != DSSClassDefs.PC_ELEMENT
                    ):
                        DSSGlobals.doSimpleMsg(self.meteredElement.getName() + ' must be a power conversion element (Load or Generator)!', 664)
                        return
                    break
                break
            if self.meteredTerminal > self.meteredElement.getNTerms():
                DSSGlobals.doErrorMsg('Monitor: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Respecify terminal no.', 665)
            else:
                self.setNPhases(self.meteredElement.getNPhases())
                self.setNConds(self.meteredElement.getNConds())
                # sets name of i-th terminal's connected bus in monitor's bus list
                # this value will be used to set the NodeRef array (see takeSample)
                self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
                # make a name for the buffer file
                self.bufferFile = DSSGlobals.circuitName_ + 'Mon_' + self.getName() + '.mon'
                # ActiveCircuit.CurrentDirectory +
                # removed 10/19/99 ConvertBlanks(BufferFile); // turn blanks into '_'
                # Allocate buffers
                _2 = self.mode & Monitor.MODEMASK
                _3 = False
                while True:
                    if _2 == 3:
                        _3 = True
                        self.numStateVars = self.meteredElement.numVariables()
                        self.stateBuffer = Utilities.resizeArray(self.stateBuffer, self.numStateVars)
                        break
                    if True:
                        _3 = True
                        self.currentBuffer = Utilities.resizeArray(self.currentBuffer, self.meteredElement.getYorder())
                        self.voltageBuffer = Utilities.resizeArray(self.voltageBuffer, self.meteredElement.getNConds())
                        break
                    break
                self.clearMonitorStream()
                self.validMonitor = True
        else:
            self.meteredElement = None
            # element not found
            DSSGlobals.doErrorMsg('Monitor: \"' + self.getName() + '\"', 'Circuit element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 666)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.meteredElement is not None:
            self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
            self.setNPhases(self.meteredElement.getNPhases())
            self.setNConds(self.meteredElement.getNConds())
            _0 = self.mode & Monitor.MODEMASK
            _1 = False
            while True:
                if _0 == 3:
                    _1 = True
                    self.numStateVars = self.meteredElement.numVariables()
                    self.stateBuffer = Utilities.resizeArray(self.stateBuffer, self.numStateVars)
                    break
                if True:
                    _1 = True
                    self.currentBuffer = Utilities.resizeArray(self.currentBuffer, self.meteredElement.getYorder())
                    self.voltageBuffer = Utilities.resizeArray(self.voltageBuffer, self.meteredElement.getNConds())
                    break
                break
            self.clearMonitorStream()
            self.validMonitor = True
        super(MonitorObjImpl, self).makePosSequence()

    def calcYPrim(self):
        # A Monitor is a zero current source; Yprim is always zero.
        pass

    def clearMonitorStream(self):
        # int RecordSize;
        # int strPtr;
        # if (!IsFileOpen) openMonitorFile();  // always opens for appending
        # buffer.seek(0);  // back to BOF
        # buffer.truncate();  // get rid of anything remaining
        try:
            self.monitorStream.reset()
            self.sampleCount = 0
            isPosSeq = False
            self.strBuffer.delete(0, len(self.strBuffer))
            # clear buffer
            # strPtr = 0;  // init string
            if DSSGlobals.activeCircuit.getSolution().isHarmonicModel():
                self.strBuffer.__add__('Freq, Harmonic, ')
            else:
                self.strBuffer.__add__('hour, t(sec), ')
            _0 = self.mode & Monitor.MODEMASK
            _1 = False
            while True:
                if _0 == 2:
                    _1 = True
                    self.strBuffer.__add__('Tap (pu)')
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.numStateVars):
                            break
                        nameOfState = String.valueOf.valueOf(self.meteredElement.getVariable(i))
                        self.strBuffer.__add__(nameOfState)
                        if i < self.numStateVars:
                            self.strBuffer.__add__(', ')
                    break
                if True:
                    _1 = True
                    if self.mode & Monitor.SEQUENCEMASK > 0 and self.nPhases == 3:
                        # convert to symmetrical components
                        isPosSeq = True
                        numVI = 3
                    else:
                        numVI = self.nConds
                    # convert voltage buffer to power kW, kVAr
                    if self.mode & Monitor.MODEMASK == 1:
                        isPower = True
                    else:
                        isPower = False
                    _3 = self.mode & (Monitor.MAGNITUDEMASK + Monitor.POSSEQONLYMASK)
                    _4 = False
                    while True:
                        if _3 == 32:
                            _4 = True
                            if not isPower:
                                # for (i = 0; i < NumVI; i++) RecordSize += 1;
                                # if (IncludeResidual) RecordSize += 2;
                                _5 = True
                                i = 0
                                while True:
                                    if _5 is True:
                                        _5 = False
                                    else:
                                        i += 1
                                    if not (i < numVI):
                                        break
                                    self.strBuffer.__add__(String.format.format('|V|%d (volts)', i))
                                    self.strBuffer.__add__(', ')
                                if self.includeResidual:
                                    self.strBuffer.__add__('|VN| (volts)')
                                    self.strBuffer.__add__(', ')
                                _6 = True
                                i = 0
                                while True:
                                    if _6 is True:
                                        _6 = False
                                    else:
                                        i += 1
                                    if not (i < numVI):
                                        break
                                    self.strBuffer.__add__('|I|' + String.valueOf.valueOf(i) + ' (amps)')
                                    if i < numVI:
                                        self.strBuffer.__add__(', ')
                                if self.includeResidual:
                                    self.strBuffer.__add__(',|IN| (volts)')
                            else:
                                _7 = True
                                i = 0
                                while True:
                                    if _7 is True:
                                        _7 = False
                                    else:
                                        i += 1
                                    if not (i < numVI):
                                        break
                                    self.strBuffer.__add__('S' + String.valueOf.valueOf(i) + ' (kVA)')
                                    if i < numVI:
                                        self.strBuffer.__add__(', ')
                            break
                        if (_4 is True) or (_3 == 64):
                            _4 = True
                            if not isPower:
                                # RecordSize = RecordSize + 2;
                                if self.VIPolar:
                                    self.strBuffer.__add__('V1, V1ang, I1, I1ang')
                                else:
                                    self.strBuffer.__add__('V1.re, V1.im, I1.re, I1.im')
                            elif self.PPolar:
                                self.strBuffer.__add__('S1 (kVA), Ang ')
                            else:
                                self.strBuffer.__add__('P1 (kW), Q1 (kvar)')
                            break
                        if (_4 is True) or (_3 == 96):
                            _4 = True
                            if not isPower:
                                # RecordSize = RecordSize + 1;
                                self.strBuffer.__add__('V, I ')
                            elif self.PPolar:
                                self.strBuffer.__add__('S1 (kVA)')
                            else:
                                self.strBuffer.__add__('P1 (kW)')
                            break
                        if True:
                            _4 = True
                            if not isPower:
                                if isPosSeq:
                                    iMin = 0
                                    iMax = numVI - 1
                                else:
                                    iMin = 1
                                    iMax = numVI
                                # RecordSize = RecordSize + NumVI * 2;
                                # if (IncludeResidual) RecordSize += 4;
                                _8 = True
                                i = iMin
                                while True:
                                    if _8 is True:
                                        _8 = False
                                    else:
                                        i += 1
                                    if not (i < iMax):
                                        break
                                    if self.VIPolar:
                                        self.strBuffer.__add__('V' + String.valueOf.valueOf(i) + ', VAngle' + String.valueOf.valueOf(i))
                                    else:
                                        self.strBuffer.__add__('V' + String.valueOf.valueOf(i) + '.re, V' + String.valueOf.valueOf(i) + '.im')
                                    self.strBuffer.__add__(', ')
                                if self.includeResidual:
                                    if self.VIPolar:
                                        self.strBuffer.__add__('VN, VNAngle')
                                    else:
                                        self.strBuffer.__add__('VN.re, VN.im')
                                    self.strBuffer.__add__(', ')
                                _9 = True
                                i = iMin
                                while True:
                                    if _9 is True:
                                        _9 = False
                                    else:
                                        i += 1
                                    if not (i < iMax):
                                        break
                                    if self.VIPolar:
                                        self.strBuffer.__add__('I' + String.valueOf.valueOf(i) + ', IAngle' + String.valueOf.valueOf(i))
                                    else:
                                        self.strBuffer.__add__('I' + String.valueOf.valueOf(i) + '.re, I' + String.valueOf.valueOf(i) + '.im')
                                    if i < numVI:
                                        self.strBuffer.__add__(', ')
                                if self.includeResidual:
                                    if self.VIPolar:
                                        self.strBuffer.__add__(', IN, INAngle')
                                    else:
                                        self.strBuffer.__add__(', IN.re, IN.im')
                            else:
                                if isPosSeq:
                                    iMin = 0
                                    iMax = numVI - 1
                                else:
                                    iMin = 1
                                    iMax = numVI
                                _10 = True
                                i = iMin
                                while True:
                                    if _10 is True:
                                        _10 = False
                                    else:
                                        i += 1
                                    if not (i < iMax):
                                        break
                                    if self.PPolar:
                                        self.strBuffer.__add__('S' + String.valueOf.valueOf(i) + ' (kVA), Ang' + String.valueOf.valueOf(i))
                                    else:
                                        self.strBuffer.__add__('P' + String.valueOf.valueOf(i) + ' (kW), Q' + String.valueOf.valueOf(i) + ' (kvar)')
                                    if i < numVI:
                                        self.strBuffer.__add__(', ')
                                break
                        break
                    break
                break
            # switch
            # recordSize is the number of singles in the sample (after the hour and sec)
            # write header to monitor stream
            # write id so we know it is a DSS monitor file and which version in case we
            # change it down the road
            # MonitorStream.write(FileSignature, Sizeof(FileSignature) );
            # MonitorStream.write(FileVersion,   Sizeof(FileVersion) );
            # MonitorStream.write(RecordSize,    Sizeof(RecordSize) );
            # MonitorStream.write(Mode,          Sizeof(Mode)       );
            self.monitorStream.write(str(self.strBuffer).toCharArray())
            # So the file now looks like:
            #   FileSignature (4 bytes)    32-bit Integers
            #   FileVersion   (4)
            #   RecordSize    (4)
            #   Mode          (4)
            #   String        (256)
            # 
            #   hr   (4)       all singles
            #   Sec  (4)
            #   Sample  (4*RecordSize)
            #   ...

            # closeMonitorFile();  // ready now for appending
        except Exception, e:
            DSSGlobals.doErrorMsg('Cannot open Monitor file.', e.getMessage(), 'Monitor: \"' + self.getName() + '\"', 670)

    def openMonitorStream(self):
        if not self.isFileOpen:
            # MonitorStream.seek(-1);  // positioned at end of stream
            self.isFileOpen = True

    def closeMonitorStream(self):
        try:
            if self.isFileOpen:
                # only close open files
                # MonitorStream.seek(0);  // just move stream position to the beginning
                self.isFileOpen = False
        except Exception, e:
            DSSGlobals.doErrorMsg('Cannot open monitor stream.', e.getMessage(), 'Monitor: \"' + self.getName() + '\"', 671)

    def save(self):
        """Saves present buffer to monitor file."""
        if not self.isFileOpen:
            self.openMonitorStream()
            # position to end of stream
            # Write present monitor buffer to monitor stream
            # MonitorStream.write(MonBuffer);
        self.bufPtr = 0
        # reset buffer for next

    def resetIt(self):
        self.bufPtr = 0
        self.clearMonitorStream()

    def takeSample(self):
        residualCurr = None
        residualVolt = None
        V012 = [None] * 3
        I012 = [None] * 3
        sol = DSSGlobals.activeCircuit.getSolution()
        if not (self.validMonitor and self.isEnabled()):
            return
        self.sampleCount += 1
        self.hour = sol.getIntHour()
        self.sec = sol.getDynaVars().t
        offset = (self.meteredTerminal - 1) * self.meteredElement.getNConds()
        # save time unless harmonics mode and then save frequency and harmonic
        if sol.isHarmonicModel():
            self.addDblsToBuffer(sol.getFrequency(), 1)
            # put freq in hour slot as a double
            self.addDblsToBuffer(sol.getHarmonic(), 1)
            # put harmonic in time slot in buffer
        else:
            dHour = self.hour
            # convert to double
            self.addDblsToBuffer(dHour, 1)
            # put hours in buffer as a double
            self.addDblsToBuffer(self.sec, 1)
            # stick time in sec in buffer
        _0 = self.mode & Monitor.MODEMASK
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.meteredElement.computeITerminal()
                # only does calc if needed
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.meteredElement.getYorder()):
                        break
                    self.currentBuffer[i] = self.meteredElement.getITerminal()[i]
                try:
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.nConds):
                            break
                        # nodeRef is set by the main circuit object
                        # it is the index of the terminal into the system node list
                        self.voltageBuffer[i] = sol.getNodeV()[self.nodeRef[i]]
                except Exception, e:
                    DSSGlobals.doSimpleMsg(e.getMessage() + DSSGlobals.CRLF + 'NodeRef is invalid. Try solving a snapshot or direct before solving in a mode that takes a monitor sample.', 672)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                self.meteredElement.computeITerminal()
                # only does calc if needed
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.meteredElement.getYorder()):
                        break
                    self.currentBuffer[i] = self.meteredElement.getITerminal()[i]
                try:
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < self.nConds):
                            break
                        # NodeRef is set by the main circuit object
                        # it is the index of the terminal into the system node list
                        self.voltageBuffer[i] = sol.getNodeV()[self.nodeRef[i]]
                except Exception, e:
                    DSSGlobals.doSimpleMsg(e.getMessage() + DSSGlobals.CRLF + 'NodeRef is invalid. Try solving a snapshot or direct before solving in a mode that takes a monitor sample.', 672)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                self.addDblToBuffer(self.meteredElement.getPresentTap(self.meteredTerminal))
                return
                # done with this mode now
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.meteredElement.getAllVariables(self.stateBuffer)
                self.addDblsToBuffer(self.stateBuffer, self.numStateVars)
                return
                # done with this mode now
            if True:
                _1 = True
                return
                # ignore invalid mask
            break
        if self.mode & Monitor.SEQUENCEMASK > 0 and self.nPhases == 3:
            # convert to symmetrical components
            MathUtil.phase2SymComp(self.voltageBuffer, V012)
            MathUtil.phase2SymComp(self.currentBuffer[offset + 1], I012)
            numVI = 3
            isSequence = True
            # replace voltage and current buffer with sequence quantities
            _6 = True
            i = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    i += 1
                if not (i < 3):
                    break
                self.voltageBuffer[i] = V012[i]
            _7 = True
            i = 0
            while True:
                if _7 is True:
                    _7 = False
                else:
                    i += 1
                if not (i < 3):
                    break
                self.currentBuffer[offset + i] = I012[i]
        else:
            numVI = self.nConds
            isSequence = False
        isPower = False
        # init so compiler won't complain
        _8 = self.mode & Monitor.MODEMASK
        _9 = False
        while True:
            if _8 == 0:
                _9 = True
                isPower = False
                if self.includeResidual:
                    if self.VIPolar:
                        residualVolt = Utilities.residualPolar(self.voltageBuffer[0], self.nPhases)
                        residualCurr = Utilities.residualPolar(self.currentBuffer[offset + 1], self.nPhases)
                        # TODO Check zero based indexing
                    else:
                        residualVolt = Utilities.residual(self.voltageBuffer[0], self.nPhases)
                        residualCurr = Utilities.residual(self.currentBuffer[offset + 1], self.nPhases)
                        # TODO Check zero based indexing
                if self.VIPolar:
                    Utilities.convertComplexArrayToPolar(self.voltageBuffer, numVI)
                    Utilities.convertComplexArrayToPolar(self.currentBuffer, numVI * self.meteredElement.getNTerms())
                    # get all of current buffer
                break
            if (_9 is True) or (_8 == 1):
                _9 = True
                MathUtil.calckPowers(self.voltageBuffer, self.voltageBuffer, self.currentBuffer[offset + 1], numVI)
                if isSequence or DSSGlobals.activeCircuit.isPositiveSequence():
                    Utilities.mulArray(self.voltageBuffer, 3.0, numVI)
                    # convert to total power
                if self.PPolar:
                    Utilities.convertComplexArrayToPolar(self.voltageBuffer, numVI)
                isPower = True
                break
            break
        # now check to see what to write to disk
        _10 = self.mode & (Monitor.MAGNITUDEMASK + Monitor.POSSEQONLYMASK)
        _11 = False
        while True:
            if _10 == 32:
                _11 = True
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < numVI):
                        break
                    self.addDblToBuffer(self.voltageBuffer[i].getReal())
                if self.includeResidual:
                    self.addDblToBuffer(residualVolt.getReal())
                if not isPower:
                    _13 = True
                    i = 0
                    while True:
                        if _13 is True:
                            _13 = False
                        else:
                            i += 1
                        if not (i < numVI):
                            break
                        self.addDblToBuffer(self.currentBuffer[offset + i].getReal())
                    if self.includeResidual:
                        self.addDblToBuffer(residualCurr.getReal())
                break
            if (_11 is True) or (_10 == 64):
                _11 = True
                if isSequence:
                    self.addDblsToBuffer(self.voltageBuffer[1].getReal(), 2)
                    if not isPower:
                        self.addDblsToBuffer(self.currentBuffer[offset + 2].getReal(), 2)
                elif not isPower:
                    sum = Complex.ZERO
                    _14 = True
                    i = 0
                    while True:
                        if _14 is True:
                            _14 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        sum = sum.add(self.voltageBuffer[i])
                    self.addDblsToBuffer(sum.getReal(), 2)
                else:
                    # average the phase magnitudes and sum angles
                    sum = Complex.ZERO
                    _15 = True
                    i = 0
                    while True:
                        if _15 is True:
                            _15 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        sum = sum.add(self.voltageBuffer[i])
                    sum = Complex(sum.getReal() / self.nPhases, sum.getImaginary())
                    self.addDblsToBuffer(sum.getReal(), 2)
                    sum = Complex.ZERO
                    _16 = True
                    i = 0
                    while True:
                        if _16 is True:
                            _16 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        sum = sum.add(self.currentBuffer[i])
                    sum = Complex(sum.getReal() / self.nPhases, sum.getImaginary())
                    self.addDblsToBuffer(sum.getReal(), 2)
                break
            if (_11 is True) or (_10 == 96):
                _11 = True
                if isSequence:
                    self.addDblToBuffer(self.voltageBuffer[1].getReal())
                    # first double is magnitude
                    if not isPower:
                        self.addDblToBuffer(self.currentBuffer[offset + 2].getReal())
                else:
                    dSum = 0.0
                    _17 = True
                    i = 0
                    while True:
                        if _17 is True:
                            _17 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        dSum = dSum + self.voltageBuffer[i].getReal()
                        # VoltageBuffer[i].abs();
                    if not isPower:
                        dSum = dSum / self.nPhases
                    self.addDblToBuffer(dSum)
                    if not isPower:
                        dSum = 0.0
                        _18 = True
                        i = 0
                        while True:
                            if _18 is True:
                                _18 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            dSum = dSum + self.currentBuffer[offset + i].getReal()
                            # CurrentBuffer[Offset+i].abs();
                        dSum = dSum / self.nPhases
                        self.addDblToBuffer(dSum)
                break
            if True:
                _11 = True
                self.addDblsToBuffer(self.voltageBuffer[1].getReal(), numVI * 2)
                if not isPower:
                    if self.includeResidual:
                        self.addDblsToBuffer(ComplexUtil.asArray(residualVolt), 2)
                    self.addDblsToBuffer(self.currentBuffer[offset + 1].getReal(), numVI * 2)
                    if self.includeResidual:
                        self.addDblsToBuffer(ComplexUtil.asArray(residualCurr), 2)
                break
            break

    def addDblsToBuffer(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 2:
            dbl, nDoubles = _0
            self.addDblToBuffer(dbl)
            dbl, nDoubles = _0
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nDoubles):
                    break
                self.addDblToBuffer(dbl[i])
        else:
            raise ARGERROR(2, 2)

    def addDblToBuffer(self, dbl):
        # first check to see if there's enough room
        # if not, save to monitorStream first
        if self.bufPtr == self.bufferSize:
            self.save()
        self.bufPtr += 1
        self.monBuffer[self.bufPtr] = dbl

    def translateToCSV(self, show):
        nRead = 0
        recordSize = 0
        s = 0
        sngBuffer = [None] * 100
        self.save()
        # save present buffer
        self.closeMonitorStream()
        # position at beginning
        csvName = self.getFileName()
        # MonitorStream.seek(0);  // start at the beginning of the stream
        # MonitorStream.read(Fsignature);
        # MonitorStream.read(Fversion);
        # MonitorStream.read(RecordSize);
        # MonitorStream.read(Mode);
        # MonitorStream.read(StrBuffer);
        try:
            f = self.File(csvName)
            # make CSV file
            fw = self.FileWriter(f, False)
            bw = self.BufferedWriter(fw)
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening CSVFile \"' + csvName + '\" for writing' + DSSGlobals.CRLF + e.getMessage(), 672)
            return
        pStr = len(self.strBuffer)
        # FBuffer.write(pStr);
        # FBuffer.newLine();
        recordBytes = recordSize
        # while (!(MonitorStream.position() >= MonitorStream.size())) {
        # MonitorStream.read(hr);
        # MonitorStream.read(s);
        # Nread = MonitorStream.read(sngBuffer, RecordBytes);
        # }
        # if (Nread < RecordBytes) break;
        # FBuffer.write(hr);  // hours
        try:
            bw.write(', ' + s)
            # sec
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < recordSize):
                    break
                bw.write(', ' + String.format.format('%-.6g', sngBuffer[i]))
            bw.newLine()
            self.closeMonitorStream()
            bw.close()
            fw.close()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error writing CSV file \"' + csvName + '\" ' + DSSGlobals.CRLF + e.getMessage(), 673)
        if show:
            Utilities.fireOffEditor(csvName)
        DSSGlobals.globalResult = csvName

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

    def dumpProperties(self, f, complete):
        super(MonitorObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 
            print '// BufferSize=' + self.bufferSize
            print '// Hour=' + self.hour
            print '// Sec=' + self.sec
            print '// BaseFrequency=' + self.baseFrequency
            print '// Bufptr=' + self.bufPtr
            print '// Buffer='
            k = 0
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.bufPtr):
                    break
                f.print_(self.monBuffer[i] + ', ')
                k += 1
                if k == 2 + (self.nConds * 4):
                    print 
                    k = 0
            print 

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '')
        # "element";
        self.setPropertyValue(1, '1')
        # "terminal";
        self.setPropertyValue(2, '0')
        # "mode";
        self.setPropertyValue(3, '')
        # "action";  // buffer=clear|save
        self.setPropertyValue(4, 'NO')
        self.setPropertyValue(5, 'YES')
        self.setPropertyValue(6, 'YES')
        super(MonitorObjImpl, self).initPropertyValues(Monitor.NumPropsThisClass)

    def TOPExport(self, objName):
        raise self.UnsupportedOperationException()

    def getFileName(self):
        return DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + 'Mon_' + self.getName() + '.csv'

    def getMode(self):
        return self.mode

    def setMode(self, value):
        self.mode = value

    def getMonitorStream(self):
        return self.monitorStream

    def setMonitorStream(self, stream):
        self.monitorStream = stream

    def getSampleCount(self):
        return self.sampleCount

    def setSampleCount(self, count):
        # FIXME Private members in OpenDSS
        self.sampleCount = count

    def getBufferSize(self):
        return self.bufferSize

    def setBufferSize(self, size):
        self.bufferSize = size

    def getHour(self):
        return self.hour

    def setHour(self, hr):
        self.hour = hr

    def getSec(self):
        return self.sec

    def setSec(self, s):
        self.sec = s

    def getMonBuffer(self):
        return self.monBuffer

    def setMonBuffer(self, buffer):
        self.monBuffer = buffer

    def getBufPtr(self):
        return self.bufPtr

    def setBufPtr(self, buf):
        self.bufPtr = buf

    def getCurrentBuffer(self):
        return self.currentBuffer

    def setCurrentBuffer(self, buffer):
        self.currentBuffer = buffer

    def getVoltageBuffer(self):
        return self.voltageBuffer

    def setVoltageBuffer(self, buffer):
        self.voltageBuffer = buffer

    def getNumStateVars(self):
        return self.numStateVars

    def setNumStateVars(self, num):
        self.numStateVars = num

    def getStateBuffer(self):
        return self.stateBuffer

    def setStateBuffer(self, buffer):
        self.stateBuffer = buffer

    def isIncludeResidual(self):
        return self.includeResidual

    def setIncludeResidual(self, include):
        self.includeResidual = include

    def isVIPolar(self):
        return self.VIPolar

    def setVIPolar(self, polar):
        self.VIPolar = polar

    def isPPolar(self):
        return self.PPolar

    def setPPolar(self, polar):
        self.PPolar = polar

    def getFileSignature(self):
        return self.fileSignature

    def setFileSignature(self, signature):
        self.fileSignature = signature

    def getFileVersion(self):
        return self.fileVersion

    def setFileVersion(self, version):
        self.fileVersion = version

    def getBufferFile(self):
        return self.bufferFile

    def setBufferFile(self, buffer):
        self.bufferFile = buffer

    def isFileOpen(self):
        return self.isFileOpen

    def setFileOpen(self, isOpen):
        self.isFileOpen = isOpen

    def isValidMonitor(self):
        return self.validMonitor

    def setValidMonitor(self, valid):
        self.validMonitor = valid

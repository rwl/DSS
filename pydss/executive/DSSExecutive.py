from dss.executive.impl.ExecCommands import (ExecCommands,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.executive.Executive import (Executive,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.executive.impl.ExecOptions import (ExecOptions,)
# from java.io.FileWriter import (FileWriter,)


class DSSExecutive(object, Executive):
    recorderOn = None
    recorderFile = None
    recorderFileWriter = None

    def __init__(self):
        """Private constructor prevents instantiation from other classes"""
        super(DSSExecutive, self)()
        execCmd = ExecCommands.getInstance()
        execCmd.setCommandList(CommandListImpl(execCmd.getExecCommand()))
        execOpts = ExecOptions.getInstance()
        execOpts.setOptionList(execOpts.getOptionList())
        # Instantiate all DSS class definitions, intrinsic and user-defined
        DSSClassDefs.createDSSClasses()
        # default buffer for 2 active circuits
        DSSGlobals.circuits = list(2)
        DSSGlobals.numCircuits = 0
        DSSGlobals.activeCircuit = None
        Parser.getInstance()
        # create global parser object
        ExecCommands.getInstance().setLastCmdLine('')
        ExecCommands.getInstance().setRedirFile('')
        self.setRecorderOn(False)
        self.recorderFile = ''
        # DSSGlobals.getInstance().readDSS_Registry();

    class DSSExecutiveHolder(object):
        INSTANCE = DSSExecutive()

    @classmethod
    def getInstance(cls):
        return cls.DSSExecutiveHolder.INSTANCE

    def finalize(self):
        DSSGlobals.writeDSS_Registry()
        DSSGlobals.clearAllCircuits()
        ExecCommands.getInstance().setCommandList(None)
        ExecOptions.getInstance().setOptionList(None)
        DSSGlobals.circuits = None
        Parser.getInstance()
        DSSClassDefs.disposeDSSClasses()

    def getLastError(self):
        return DSSGlobals.lastErrorMessage

    def getErrorResult(self):
        return DSSGlobals.errorNumber

    def createDefaultDSSItems(self):
        """Create default loadshapes, growthshapes, and other general DSS objects
        used by all circuits.
        """
        # this load shape used for generator dispatching, etc. loads may refer to it, also.
        self.setCommand('new loadshape.default npts=24 1.0 mult=(.677 .6256 .6087 .5833 .58028 .6025 .657 .7477 .832 .88 .94 .989 .985 .98 .9898 .999 1 .958 .936 .913 .876 .876 .828 .756)')
        if DSSGlobals.cmdResult == 0:
            self.setCommand('new growthshape.default 2 year=\"1 20\" mult=(1.025 1.025)')
            # 20 years at 2.5%
            self.setCommand('new spectrum.default 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 33 20 14 11 9 7) Angle=(0 0 0 0 0 0 0)')
            self.setCommand('new spectrum.defaultload 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 1.5 20 14 1 9 7) Angle=(0 180 180 180 180 180 180)')
            self.setCommand('new spectrum.defaultgen 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 5 3 1.5 1 .7 .5) Angle=(0 0 0 0 0 0 0)')
            self.setCommand('new spectrum.defaultvsource 1  Harmonic=(1 )  %mag=(100 ) Angle=(0 ) ')
            self.setCommand('new spectrum.linear 1  Harmonic=(1 )  %mag=(100 ) Angle=(0 ) ')
            self.setCommand('new spectrum.pwm6 13  Harmonic=(1 3 5 7 9 11 13 15 17 19 21 23 25) %mag=(100 4.4 76.5 62.7 2.9 24.8 12.7 0.5 7.1 8.4 0.9 4.4 3.3) Angle=(-103 -5 28 -180 -33 -59 79 36 -253 -124 3 -30 86)')
            self.setCommand('new spectrum.dc6 10  Harmonic=(1 3 5 7 9 11 13 15 17 19)  %mag=(100 1.2 33.6 1.6 0.4 8.7  1.2  0.3  4.5 1.3) Angle=(-75 28 156 29 -91 49 54 148 -57 -46)')
            self.setCommand('New TCC_Curve.A 5 c_array=(1, 2.5, 4.5, 8.0, 14.)  t_array=(0.15 0.07 .05 .045 .045) ')
            self.setCommand('New TCC_Curve.D 5 c_array=(1, 2.5, 4.5, 8.0, 14.)  t_array=(6 0.7 .2 .06 .02)')
            self.setCommand('New TCC_Curve.TLink 7 c_array=(2 2.1 3 4 6 22 50)  t_array=(300 100 10.1 4.0 1.4 0.1  0.02)')
            self.setCommand('New TCC_Curve.KLink 6 c_array=(2 2.2 3 4 6 30)    t_array=(300 20 4 1.3 0.41 0.02)')

    def getCommand(self):
        return ExecCommands.getInstance().getLastCmdLine()

    def setCommand(self, value):
        ExecCommands.getInstance().processCommand(value)

    def clear(self):
        if DSSGlobals.numCircuits > 0:
            # First get rid of all existing stuff
            DSSGlobals.clearAllCircuits()
            DSSClassDefs.disposeDSSClasses()
            # Start over
            DSSClassDefs.createDSSClasses()
            self.createDefaultDSSItems()
            DSSGlobals.DSSForms.setRebuildHelpForm(True)
            # because class strings have changed

    def setRecorderOn(self, value):
        try:
            if value:
                if not self.recorderOn:
                    self.recorderFile = DSSGlobals.DSSDataDirectory + 'DSSRecorder.dss'
                    self.recorderFileWriter = FileWriter(self.recorderFile)
            elif self.recorderOn:
                self.recorderFileWriter.close()
        except IOException, e:
            DSSGlobals.doErrorMsg('setRecorderOn', e.getMessage(), 'Lack of write access', 678)
        DSSGlobals.globalResult = self.recorderFile
        self.recorderOn = value

    def isRecorderOn(self):
        return self.recorderOn

    def writeToRecorderFile(self, s):
        print s

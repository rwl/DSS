from dss.general.impl.SpectrumObjImpl import (SpectrumObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.Spectrum import (Spectrum,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class SpectrumImpl(DSSClassImpl, Spectrum):
    activeSpectrumObj = None

    def __init__(self):
        super(SpectrumImpl, self)()
        self.className = 'Spectrum'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = Spectrum.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'NumHarm'
        self.propertyName[1] = 'harmonic'
        self.propertyName[2] = '%mag'
        self.propertyName[3] = 'angle'
        self.propertyName[4] = 'CSVFile'
        self.propertyHelp[0] = 'Number of frequencies in this spectrum. (See CSVFile)'
        self.propertyHelp[1] = 'Array of harmonic values. You can also use the syntax' + CRLF + 'harmonic = (file=filename)     !for text file one value per line' + CRLF + 'harmonic = (dblfile=filename)  !for packed file of doubles' + CRLF + 'harmonic = (sngfile=filename)  !for packed file of singles '
        self.propertyHelp[2] = 'Array of magnitude values, assumed to be in PERCENT. You can also use the syntax' + CRLF + '%mag = (file=filename)     !for text file one value per line' + CRLF + '%mag = (dblfile=filename)  !for packed file of doubles' + CRLF + '%mag = (sngfile=filename)  !for packed file of singles '
        self.propertyHelp[3] = 'Array of phase angle values, degrees.You can also use the syntax' + CRLF + 'angle = (file=filename)     !for text file one value per line' + CRLF + 'angle = (dblfile=filename)  !for packed file of doubles' + CRLF + 'angle = (sngfile=filename)  !for packed file of singles '
        self.propertyHelp[4] = 'File of spectrum points with (harmonic, magnitude-percent, angle-degrees) values, one set of 3 per line, in CSV format. ' + 'If fewer than NUMHARM frequencies found in the file, NUMHARM is set to the smaller value.'
        self.activeProperty = Spectrum.NumPropsThisClass - 1
        super(SpectrumImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = SpectrumObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of Parser
        self.activeSpectrumObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeSpectrumObj
        aso = self.activeSpectrumObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
                if paramPointer > 0 and paramPointer <= self.numProperties:
                    # TODO Check zero based indexing
                    aso.setPropertyValue(paramPointer, param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == 0:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Object \"' + self.getName() + '\"', 650)
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        aso.setNumHarm(parser.makeInteger())
                        aso.setAngleArray(Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()))
                        # make a dummy angle array
                        _2 = True
                        i = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                i += 1
                            if not (i < aso.getNumHarm()):
                                break
                            aso.getAngleArray()[i] = 0.0
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        aso.setHarmArray(Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()))
                        Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getHarmArray())
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        aso.setPUMagArray(Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()))
                        Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getPUMagArray())
                        _3 = True
                        i = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < aso.getNumHarm()):
                                break
                            aso.getPUMagArray()[i] = aso.getPUMagArray()[i] * 0.01
                            # convert to per unit
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        aso.setAngleArray(Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()))
                        Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getAngleArray())
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        self.doCSVFile(param)
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeSpectrumObj, paramPointer - Spectrum.NumPropsThisClass)
                        break
                    break
                paramName = parser.getNextParam()
                param = parser.makeString()
        if (
            aso.getHarmArray() is not None and aso.getPUMagArray() is not None and aso.getAngleArray() is not None
        ):
            aso.setMultArray()
        return result

    def makeLike(self, name):
        result = 0
        # See if we can find this line code in the present collection
        otherSpectrum = self.find(name)
        if otherSpectrum is not None:
            aso = self.activeSpectrumObj
            aso.setNumHarm(otherSpectrum.getNumHarm())
            aso.setHarmArray(Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()))
            aso.setPUMagArray(Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()))
            aso.setAngleArray(Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < aso.getNumHarm()):
                    break
                aso.getHarmArray()[i] = otherSpectrum.getHarmArray()[i]
                aso.getPUMagArray()[i] = otherSpectrum.getPUMagArray()[i]
                aso.getAngleArray()[i] = otherSpectrum.getAngleArray()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < aso.getParentClass().getNumProperties()):
                    break
                aso.setPropertyValue(i, otherSpectrum.getPropertyValue(i))
                result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Spectrum.makeLike(): \"' + name + '\" not found.', 651)
        return result

    def getCode(self):
        """Returns active spectrum code string."""
        spectrum = self.elementList.getActive()
        return spectrum.getName()

    def setCode(self, value):
        """Sets the active spectrum."""
        self.activeSpectrumObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            spectrum = self.elementList.get(i)
            if spectrum.getName().equalsIgnoreCase(value):
                self.activeSpectrumObj = spectrum
                return
        DSSGlobals.doSimpleMsg('Spectrum: \"' + value + '\" not found.', 652)

    def doCSVFile(self, fileName):
        try:
            fis = self.FileInputStream(fileName)
            dis = self.DataInputStream(fis)
            br = StringIO(self.InputStreamReader(dis))
            aso = self.activeSpectrumObj
            aso.setHarmArray(Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()))
            aso.setPUMagArray(Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()))
            aso.setAngleArray(Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()))
            i = 0
            while (
                s = # TODO: Check zero based indexingbr.readline() is not None and i < aso.getNumHarm()
            ):
                i += 1
                # use aux parser, which allows for formats
                parser = DSSGlobals.auxParser
                parser.setCmdString(s)
                parser.getNextParam()
                aso.getHarmArray()[i] = parser.makeDouble()
                parser.getNextParam()
                aso.getPUMagArray()[i] = parser.makeDouble() * 0.01
                parser.getNextParam()
                aso.getAngleArray()[i] = parser.makeDouble()
            fis.close()
            dis.close()
            br.close()
            if i != aso.getNumHarm():
                aso.setNumHarm(i)
                # reset number of points
            fis.close()
            dis.close()
            br.close()
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error processing CSV file: \"' + fileName + '. ' + e.getMessage(), 654)
            return

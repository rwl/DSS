from dss.common.impl.Utilities import (Utilities,)
from dss.general.impl.GrowthShapeObjImpl import (GrowthShapeObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.general.GrowthShape import (GrowthShape,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class GrowthShapeImpl(DSSClassImpl, GrowthShape):
    activeGrowthShapeObj = None

    def __init__(self):
        super(GrowthShapeImpl, self)()
        self.className = 'GrowthShape'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(False)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = GrowthShape.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'npts'
        # number of points to expect
        self.propertyName[1] = 'year'
        # vextor of year values
        self.propertyName[2] = 'mult'
        # vector of multiplier values corresponding to years
        self.propertyName[3] = 'csvfile'
        # switch input to a csvfile                (year, mult)
        self.propertyName[4] = 'sngfile'
        # switch input to a binary file of singles (year, mult)
        self.propertyName[5] = 'dblfile'
        # switch input to a binary file of doubles (year, mult)
        self.propertyHelp[0] = 'Number of points to expect in subsequent vector.'
        self.propertyHelp[1] = 'Array of year values, or a text file spec, corresponding to the multipliers. ' + 'Enter only those years where the growth changes. ' + 'May be any integer sequence -- just so it is consistent. See help on Mult.'
        self.propertyHelp[2] = 'Array of growth multiplier values, or a text file spec, corresponding to the year values. ' + 'Enter the multiplier by which you would multiply the previous year's load to get the present year's.' + CRLF + CRLF + 'Examples:' + CRLF + CRLF + '  Year = [1, 2, 5]   Mult=[1.05, 1.025, 1.02].' + CRLF + '  Year= (File=years.txt) Mult= (file=mults.txt).' + CRLF + CRLF + 'Text files contain one value per line.'
        self.propertyHelp[3] = 'Switch input of growth curve data to a csv file containing (year, mult) points, one per line.'
        self.propertyHelp[4] = 'Switch input of growth curve data to a binary file of singles ' + 'containing (year, mult) points, packed one after another.'
        self.propertyHelp[5] = 'Switch input of growth curve data to a binary file of doubles ' + 'containing (year, mult) points, packed one after another.'
        self.activeProperty = GrowthShape.NumPropsThisClass - 1
        super(GrowthShapeImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        # create a new object of this class and add to list.
        DSSGlobals.activeDSSObject = GrowthShapeObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        result = 0
        # continue parsing with contents of parser
        self.activeGrowthShapeObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeGrowthShapeObj
        pShape = self.activeGrowthShapeObj
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                # TODO Check zero based indexing
                pShape.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Object \"' + self.getName() + '.' + self.getName() + '\"', 600)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    pShape.setNPts(Parser.getInstance().makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    pShape.setYear(Utilities.resizeArray(pShape.getYear(), pShape.getNPts()))
                    YrBuffer = [None] * pShape.getNPts()
                    Utilities.interpretDblArray(param, pShape.getNPts(), YrBuffer)
                    # Parser.parseAsVector(pShape.getNpts(), Yrbuffer);
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < pShape.getNPts()):
                            break
                        pShape.getYear()[i] = self.Math.round(YrBuffer[i])
                    pShape.setBaseYear(pShape.getYear()[0])
                    YrBuffer = None
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    pShape.setMultiplier(Utilities.resizeArray(pShape.getMultiplier(), pShape.getNPts()))
                    Utilities.interpretDblArray(param, pShape.getNPts(), pShape.getMultiplier())
                    # Parser.parseAsVector(pShape.getNpts(), pShape.getMultiplier());
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    self.doCSVFile(param)
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.doSngFile(param)
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doDblFile(param)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeGrowthShapeObj, paramPointer - GrowthShape.NumPropsThisClass)
                    break
                break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        pShape.reCalcYearMult()
        return result

    def makeLike(self, shapeName):
        # See if we can find this line code in the present collection
        otherGrowthShape = self.find(shapeName)
        if otherGrowthShape is not None:
            pShape = self.activeGrowthShapeObj
            pShape.setNPts(otherGrowthShape.getNPts())
            pShape.setMultiplier(Utilities.resizeArray(pShape.getMultiplier(), pShape.getNPts()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < pShape.getNPts()):
                    break
                pShape.getMultiplier()[i] = otherGrowthShape.getMultiplier()[i]
            pShape.setYear(Utilities.resizeArray(pShape.getYear(), pShape.getNPts()))
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < pShape.getNPts()):
                    break
                pShape.getYear()[i] = otherGrowthShape.getYear()[i]
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < pShape.getParentClass().getNumProperties()):
                    break
                pShape.setPropertyValue(i, otherGrowthShape.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in GrowthShape makeLike: \"' + shapeName + '\" not found.', 601)
        return 0

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement GrowthShape.init()', -1)
        return 0

    def getCode(self):
        """Returns active GrowthShape string."""
        return self.elementList.getActive().getName()

    def setCode(self, value):
        """Sets the active GrowthShape."""
        self.activeGrowthShapeObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pShape = self.elementList.get(i)
            if pShape.getName().equalsIgnoreCase(value):
                self.activeGrowthShapeObj = pShape
                return
        DSSGlobals.doSimpleMsg('GrowthShape: \"' + value + '\" not found.', 602)

    def doCSVFile(self, fileName):
        try:
            fis = self.FileInputStream(fileName)
            dis = self.DataInputStream(fis)
            br = StringIO(self.InputStreamReader(dis))
            pShape = self.activeGrowthShapeObj
            i = 0
            while (
                s = # TODO: Check zero based indexingbr.readline() is not None and i < pShape.getNPts()
            ):
                i += 1
                # use aux parser to allow flexible formats
                parser = DSSGlobals.auxParser
                parser.setCmdString(s)
                parser.getNextParam()
                pShape.getYear()[i] = parser.makeInteger()
                parser.getNextParam()
                pShape.getMultiplier()[i] = parser.makeDouble()
            fis.close()
            dis.close()
            br.close()
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error processing CSV file: \"' + fileName + '. ' + e.getMessage(), 604)
            return

    def doSngFile(self, fileName):
        # FIXME Implement this method
        raise self.UnsupportedOperationException()

    def doDblFile(self, fileName):
        # FIXME Implement this method
        raise self.UnsupportedOperationException()

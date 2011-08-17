from dss.common.impl.Utilities import (Utilities,)
from dss.general.impl.XYCurveObjImpl import (XYCurveObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.XYCurve import (XYCurve,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class XYCurveImpl(DSSClassImpl, XYCurve):
    activeXYCurveObj = None
    tempPointsBuffer = None

    def __init__(self):
        super(XYCurveImpl, self)()
        self.className = 'XYcurve'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.tempPointsBuffer = None
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = XYCurve.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'npts'
        # number of points to expect
        self.propertyName[1] = 'Points'
        self.propertyName[2] = 'Yarray'
        # vector of Y values
        self.propertyName[3] = 'Xarray'
        # vector of X values corresponding to Y values
        self.propertyName[4] = 'csvfile'
        # switch input to a csvfile
        self.propertyName[5] = 'sngfile'
        # switch input to a binary file of singles
        self.propertyName[6] = 'dblfile'
        # switch input to a binary file of singles
        self.propertyName[7] = 'x'
        self.propertyName[8] = 'y'
        # define property help values
        self.propertyHelp[0] = 'Max number of points to expect in curve. This could get reset to the actual number of points defined ' + 'if less than specified.'
        # number of points to expect
        self.propertyHelp[1] = 'One way to enter the points in a curve. Enter x and y values as one array ' + 'in the order [x1, y1, x2, y2, ...]. For example:' + CRLF + CRLF + 'Points=[1,100 2,200 3, 300] ' + CRLF + CRLF + 'Values separated by commas or white space. Zero fills arrays if insufficient number of values.'
        self.propertyHelp[2] = 'Alternate way to enter Y values. Enter an array of Y values corresponding to the X values.  ' + 'You can also use the syntax: ' + CRLF + 'Yarray = (file=filename)     !for text file one value per line' + CRLF + 'Yarray = (dblfile=filename)  !for packed file of doubles' + CRLF + 'Yarray = (sngfile=filename)  !for packed file of singles ' + CRLF + CRLF + 'Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.'
        # vextor of hour values
        self.propertyHelp[3] = 'Alternate way to enter X values. Enter an array of X values corresponding to the Y values.  ' + 'You can also use the syntax: ' + CRLF + 'Xarray = (file=filename)     !for text file one value per line' + CRLF + 'Xarray = (dblfile=filename)  !for packed file of doubles' + CRLF + 'Xarray = (sngfile=filename)  !for packed file of singles ' + CRLF + CRLF + 'Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.'
        # vextor of hour values
        self.propertyHelp[4] = 'Switch input of  X-Y curve data to a CSV file ' + 'containing X, Y points one per line. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a csvfile
        self.propertyHelp[5] = 'Switch input of  X-Y curve data to a binary file of SINGLES ' + 'containing X, Y points packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[6] = 'Switch input of  X-Y  curve data to a binary file of DOUBLES ' + 'containing X, Y points packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[7] = 'Enter an value and then retrieve the interpolated Y value from the Y property.'
        self.propertyHelp[8] = 'Enter an value and then retrieve the interpolated X value from the X property.'
        self.activeProperty = XYCurve.NumPropsThisClass - 1
        super(XYCurveImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = XYCurveObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        """Uses global parser."""
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeXYCurveObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeXYCurveObj
        xyc = self.activeXYCurveObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                xyc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + xyc.getName() + '\"', 610)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    xyc.setNumPoints(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    self.tempPointsBuffer = Utilities.resizeArray(self.tempPointsBuffer, xyc.getNumPoints() * 2)
                    # allow possible resetting (to a lower value) of num points when specifying temperatures not hours
                    xyc.setNumPoints(Utilities.interpretDblArray(param, xyc.getNumPoints() * 2, self.tempPointsBuffer) / 2)
                    # parser.parseAsVector(Npts, Temperatures);
                    xyc.setYValues(Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()))
                    xyc.setXValues(Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()))
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < xyc.getNumPoints()):
                            break
                        xyc.getXValues()[i] = self.tempPointsBuffer[(2 * i) - 1]
                        xyc.getYValues()[i] = self.tempPointsBuffer[2 * i]
                    xyc.setX(xyc.getXValues()[0])
                    xyc.setY(xyc.getYValues()[0])
                    self.tempPointsBuffer = None
                    # throw away temp array
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    xyc.setYValues(Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()))
                    xyc.setNumPoints(Utilities.interpretDblArray(param, xyc.getNumPoints(), xyc.getYValues()))
                    xyc.setY(xyc.getYValues()[0])
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    xyc.setXValues(Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()))
                    xyc.setNumPoints(Utilities.interpretDblArray(param, xyc.getNumPoints(), xyc.getXValues()))
                    xyc.setX(xyc.getXValues()[0])
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    self.doCSVFile(param)
                    # file of x,y points, one to a line
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.doSngFile(param)
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doDblFile(param)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    xyc.setX(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    xyc.setY(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeXYCurveObj, paramPointer - XYCurve.NumPropsThisClass)
                    break
                break
            _3 = paramPointer
            _4 = False
            while True:
                if _3 == 4:
                    _4 = True
                    xyc.setX(xyc.getXValues()[0])
                    xyc.setY(xyc.getYValues()[0])
                    break
                if (_4 is True) or (_3 == 5):
                    _4 = True
                    xyc.setX(xyc.getXValues()[0])
                    xyc.setY(xyc.getYValues()[0])
                    break
                if (_4 is True) or (_3 == 6):
                    _4 = True
                    xyc.setX(xyc.getXValues()[0])
                    xyc.setY(xyc.getYValues()[0])
                    break
                break
            _5 = paramPointer
            _6 = False
            while True:
                if _5 == 1:
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                if (_6 is True) or (_5 == 2):
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                if (_6 is True) or (_5 == 3):
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                if (_6 is True) or (_5 == 4):
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                if (_6 is True) or (_5 == 5):
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                if (_6 is True) or (_5 == 6):
                    _6 = True
                    xyc.setArrayPropertyIndex(paramPointer)
                    xyc.setNumPoints(xyc.getNumPoints())
                    # FIXME Keep properties in order for save command
                    xyc.setLastValueAccessed(0)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def getCode(self):
        return self.elementList.getActive().getName()

    def setCode(self, value):
        self.activeXYCurveObj = None
        pXYCurveObj = self.elementList.getFirst()
        while pXYCurveObj is not None:
            if pXYCurveObj.getName().equalsIgnoreCase(value):
                self.activeXYCurveObj = pXYCurveObj
                return
            pXYCurveObj = self.elementList.getNext()
        DSSGlobals.doSimpleMsg('XYCurve: \"' + value + '\" not found.', 612)

    def doCSVFile(self, fileName):
        try:
            fis = self.FileInputStream(fileName)
            dis = self.DataInputStream(fis)
            br = StringIO(self.InputStreamReader(dis))
            xyc = self.activeXYCurveObj
            xyc.setXValues(Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()))
            xyc.setYValues(Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()))
            i = -1
            # TODO Check zero based indexing
            while (
                s = # TODO: Check zero based indexingbr.readline() is not None and i < xyc.getNumPoints()
            ):
                i += 1
                # Aux parser allows commas or white space
                parser = DSSGlobals.auxParser
                parser.setCmdString(s)
                parser.getNextParam()
                xyc.getXValues()[i] = parser.makeDouble()
                parser.getNextParam()
                xyc.getYValues()[i] = parser.makeDouble()
            fis.close()
            dis.close()
            br.close()
            if i != xyc.getNumPoints():
                # TODO: Check zero based indexing
                xyc.setNumPoints(i)
            fis.close()
            dis.close()
            br.close()
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error processing XYCurve CSV file: \"' + fileName + '. ' + e.getMessage(), 604)
            return

    def doSngFile(self, fileName):
        raise self.UnsupportedOperationException()

    def doDblFile(self, fileName):
        raise self.UnsupportedOperationException()

    def makeLike(self, curveName):
        result = 0
        # See if we can find this curve in the present collection
        otherXYCurve = self.find(curveName)
        if otherXYCurve is not None:
            xyc = self.activeXYCurveObj
            xyc.setNumPoints(otherXYCurve.getNumPoints())
            xyc.setXValues(Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()))
            xyc.setYValues(Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < xyc.getNumPoints()):
                    break
                xyc.getXValues()[i] = otherXYCurve.getXValues()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < xyc.getNumPoints()):
                    break
                xyc.getYValues()[i] = otherXYCurve.getYValues()[i]
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < xyc.getParentClass().getNumProperties()):
                    break
                xyc.setPropertyValue(i, otherXYCurve.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in XYCurve makeLike: \"' + curveName + '\" not found.', 611)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement XYcurve.init', -1)
        return 0

    def find(self, objName):
        """Find an obj of this class by name."""
        if (len(objName) == 0) or objName.equalsIgnoreCase('none'):
            return None
        else:
            return super(XYCurveImpl, self).find(objName)

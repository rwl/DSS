from dss.general.impl.PriceShapeObjImpl import (PriceShapeObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.general.PriceShape import (PriceShape,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class PriceShapeImpl(DSSClassImpl, PriceShape):
    activePriceShapeObj = None

    def __init__(self):
        super(PriceShapeImpl, self)()
        self.className = 'PriceShape'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = PriceShape.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'npts'
        # number of points to expect
        self.propertyName[1] = 'interval'
        # default = 1.0;
        self.propertyName[2] = 'price'
        # vector of price values
        self.propertyName[3] = 'hour'
        # vector of hour values
        self.propertyName[4] = 'mean'
        # set the mean Price (otherwise computed)
        self.propertyName[5] = 'stddev'
        # set the std dev of the Price (otherwise computed)
        self.propertyName[6] = 'csvfile'
        # switch input to a csvfile
        self.propertyName[7] = 'sngfile'
        # switch input to a binary file of singles
        self.propertyName[8] = 'dblfile'
        # switch input to a binary file of singles
        self.propertyName[9] = 'sinterval'
        # interval in seconds
        self.propertyName[10] = 'minterval'
        # interval in minutes
        self.propertyName[11] = 'action'
        # define property help values
        self.propertyHelp[0] = 'Max number of points to expect in price shape vectors. This gets reset to the number of Price values ' + 'found if less than specified.'
        # number of points to expect
        self.propertyHelp[1] = 'Time interval for fixed interval data, hrs. Default = 1. ' + 'If set = 0 then time data (in hours) is expected using either the Hour property or input files. ' + CRLF + CRLF + 'See also \"sinterval\" and \"minterval\".'
        # default = 1.0;
        self.propertyHelp[2] = 'Array of Price values.  Units should be compatible with the object using the data. ' + 'You can also use the syntax: ' + CRLF + 'Price = (file=filename)     !for text file one value per line' + CRLF + 'Price = (dblfile=filename)  !for packed file of doubles' + CRLF + 'Price = (sngfile=filename)  !for packed file of singles ' + CRLF + CRLF + 'Note: this property will reset Npts if the  number of values in the files are fewer.'
        # vextor of hour values
        self.propertyHelp[3] = 'Array of hour values. Only necessary to define this property for variable interval data.' + ' If the data are fixed interval, do not use this property. ' + 'You can also use the syntax: ' + CRLF + 'hour = (file=filename)     !for text file one value per line' + CRLF + 'hour = (dblfile=filename)  !for packed file of doubles' + CRLF + 'hour = (sngfile=filename)  !for packed file of singles '
        # vextor of hour values
        self.propertyHelp[4] = 'Mean of the Price curve values.  This is computed on demand the first time a ' + 'value is needed.  However, you may set it to another value independently. ' + 'Used for Monte Carlo load simulations.'
        # set the mean (otherwise computed)
        self.propertyHelp[5] = 'Standard deviation of the Prices.  This is computed on demand the first time a ' + 'value is needed.  However, you may set it to another value independently.' + 'Is overwritten if you subsequently read in a curve' + CRLF + CRLF + 'Used for Monte Carlo load simulations.'
        # set the std dev (otherwise computed)
        self.propertyHelp[6] = 'Switch input of  Price curve data to a csv file ' + 'containing (hour, Price) points, or simply (Price) values for fixed time interval data, one per line. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a csvfile
        self.propertyHelp[7] = 'Switch input of  Price curve data to a binary file of singles ' + 'containing (hour, Price) points, or simply (Price) values for fixed time interval data, packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[8] = 'Switch input of  Price curve data to a binary file of doubles ' + 'containing (hour, Price) points, or simply (Price) values for fixed time interval data, packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[9] = 'Specify fixed interval in SECONDS. Alternate way to specify Interval property.'
        self.propertyHelp[10] = 'Specify fixed interval in MINUTES. Alternate way to specify Interval property.'
        self.propertyHelp[11] = '{DblSave | SngSave} After defining Price curve data... ' + 'Setting action=DblSave or SngSave will cause the present \"Price\" values to be written to ' + 'either a packed file of double or single. The filename is the PriceShape name. '
        # action
        self.activeProperty = PriceShape.NumPropsThisClass - 1
        super(PriceShapeImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, ObjName):
        DSSGlobals.activeDSSObject = PriceShapeObjImpl(self, ObjName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        """Uses global parser."""
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activePriceShapeObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activePriceShapeObj
        aps = self.activePriceShapeObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                aps.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + aps.getName() + '\"', 610)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    aps.setNumPoints(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    aps.setInterval(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    aps.setPriceValues(Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()))
                    # allow possible resetting (to a lower value) of num points when specifying prices not hours
                    aps.setNumPoints(Utilities.interpretDblArray(param, aps.getNumPoints(), aps.getPriceValues()))
                    # parser.parseAsVector(Npts, Prices);
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    aps.setHours(Utilities.resizeArray(aps.getHours(), aps.getNumPoints()))
                    Utilities.interpretDblArray(param, aps.getNumPoints(), aps.getHours())
                    # parser.parseAsVector(Npts, Hours);
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    aps.setMean(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    aps.setStdDev(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doCSVFile(param)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    self.doSngFile(param)
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    self.doDblFile(param)
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    aps.setInterval(parser.makeDouble() / 3600.0)
                    # convert seconds to hr
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    aps.setInterval(parser.makeDouble() / 60.0)
                    # convert minutes to hr
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    _2 = param.toLowerCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'd':
                            _3 = True
                            aps.saveToDblFile()
                            break
                        if (_3 is True) or (_2 == 's'):
                            _3 = True
                            aps.saveToSngFile()
                            break
                        break
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activePriceShapeObj, paramPointer - PriceShape.NumPropsThisClass)
                    break
                break
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 2:
                    _5 = True
                    aps.setStdDevCalculated(False)
                    # now calculated on demand
                    aps.setArrayPropertyIndex(paramPointer)
                    aps.setNumPoints(aps.getNumPoints())
                    # keep properties in order for save command  FIXME
                    break
                if (_5 is True) or (_4 == 6):
                    _5 = True
                    aps.setStdDevCalculated(False)
                    # now calculated on demand
                    aps.setArrayPropertyIndex(paramPointer)
                    aps.setNumPoints(aps.getNumPoints())
                    # keep properties in order for save command
                    break
                if (_5 is True) or (_4 == 7):
                    _5 = True
                    aps.setStdDevCalculated(False)
                    # now calculated on demand
                    aps.setArrayPropertyIndex(paramPointer)
                    aps.setNumPoints(aps.getNumPoints())
                    # keep properties in order for save command
                    break
                if (_5 is True) or (_4 == 8):
                    _5 = True
                    aps.setStdDevCalculated(False)
                    # now calculated on demand
                    aps.setArrayPropertyIndex(paramPointer)
                    aps.setNumPoints(aps.getNumPoints())
                    # keep properties in order for save command
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def find(self, objName):
        if (len(objName) == 0) or objName.equalsIgnoreCase('none'):
            return None
        else:
            return super(PriceShapeImpl, self).find(objName)

    def makeLike(self, shapeName):
        result = 0
        # See if we can find this line code in the present collection
        otherPriceShape = self.find(shapeName)
        if otherPriceShape is not None:
            aps = self.activePriceShapeObj
            aps.setNumPoints(otherPriceShape.getNumPoints())
            aps.setInterval(otherPriceShape.getInterval())
            aps.setPriceValues(Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < aps.getNumPoints()):
                    break
                aps.getPriceValues()[i] = otherPriceShape.getPriceValues()[i]
            if aps.getInterval() > 0.0:
                aps.setHours([None] * 0)
            else:
                aps.setHours(Utilities.resizeArray(aps.getHours(), aps.getNumPoints()))
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < aps.getNumPoints()):
                    break
                aps.getHours()[i] = otherPriceShape.getHours()[i]
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < aps.getParentClass().getNumProperties()):
                    break
                aps.setPropertyValue(i, otherPriceShape.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in PriceShape makeLike: \"' + shapeName + '\" not found.', 611)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement PriceShape.init', -1)
        return 0

    def getCode(self):
        return self.elementList.getActive().getName()

    def setCode(self, value):
        self.activePriceShapeObj = None
        pPriceShapeObj = self.elementList.getFirst()
        while pPriceShapeObj is not None:
            if pPriceShapeObj.getName().equalsIgnoreCase(value):
                self.activePriceShapeObj = pPriceShapeObj
                return
            pPriceShapeObj = self.elementList.getNext()
        DSSGlobals.doSimpleMsg('PriceShape: \"' + value + '\" not found.', 612)

    def doCSVFile(self, fileName):
        try:
            fis = self.FileInputStream(fileName)
            dis = self.DataInputStream(fis)
            br = StringIO(self.InputStreamReader(dis))
            aps = self.activePriceShapeObj
            aps.setPriceValues(Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()))
            if aps.getInterval() == 0.0:
                aps.setHours(Utilities.resizeArray(aps.getHours(), aps.getNumPoints()))
            i = 0
            while (
                s = # TODO: Check zero based indexingbr.readline() is not None and i < aps.getNumPoints()
            ):
                i += 1
                # Aux parser allows commas or white space
                parser = DSSGlobals.auxParser
                parser.setCmdString(s)
                if aps.getInterval() == 0.0:
                    parser.getNextParam()
                    aps.getHours()[i] = parser.makeDouble()
                parser.getNextParam()
                aps.getPriceValues()[i] = parser.makeDouble()
            fis.close()
            dis.close()
            br.close()
            if i != aps.getNumPoints():
                # TODO: Check zero based indexing
                aps.setNumPoints(i)
            fis.close()
            dis.close()
            br.close()
        except IOException, e:
            DSSGlobals.doSimpleMsg('Error processing CSV file: \"' + fileName + '. ' + e.getMessage(), 604)
            return

    def doSngFile(self, fileName):
        raise self.UnsupportedOperationException()
        # FIXME

    def doDblFile(self, fileName):
        raise self.UnsupportedOperationException()

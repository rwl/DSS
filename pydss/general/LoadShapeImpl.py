from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.LoadShape import (LoadShape,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.general.impl.LoadShapeObjImpl import (LoadShapeObjImpl,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
try:
    from cStringIO import (StringIO,)
except ImportError, e:
    from StringIO import (StringIO,)


class LoadShapeImpl(DSSClassImpl, LoadShape):
    activeLoadShapeObj = None

    def __init__(self):
        super(LoadShapeImpl, self)()
        self.className = 'LoadShape'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = LoadShape.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'npts'
        # number of points to expect
        self.propertyName[1] = 'interval'
        # default = 1.0;
        self.propertyName[2] = 'mult'
        # vector of power multiplier values
        self.propertyName[3] = 'hour'
        # vextor of hour values
        self.propertyName[4] = 'mean'
        # set the mean (otherwise computed)
        self.propertyName[5] = 'stddev'
        # set the std dev (otherwise computed)
        self.propertyName[6] = 'csvfile'
        # switch input to a csvfile
        self.propertyName[7] = 'sngfile'
        # switch input to a binary file of singles
        self.propertyName[8] = 'dblfile'
        # switch input to a binary file of singles
        self.propertyName[9] = 'action'
        # actions  Normalize
        self.propertyName[10] = 'qmult'
        # Q multiplier
        self.propertyName[11] = 'UseActual'
        # flag to signify to use actual value
        self.propertyName[12] = 'Pmax'
        # maxP value
        self.propertyName[13] = 'Qmax'
        # maxQ
        self.propertyName[14] = 'sinterval'
        # interval in seconds
        self.propertyName[15] = 'minterval'
        # interval in minutes
        # define property help values
        self.propertyHelp[0] = 'Max number of points to expect in load shape vectors. This gets reset to the number of multiplier values found (in files only) if less than specified.'
        # Number of points to expect
        self.propertyHelp[1] = 'Time interval for fixed interval data (hrs). Default = 1. ' + 'If set = 0 then time data (in hours) is expected using either the Hour property or input files. ' + CRLF + CRLF + 'See also \"sinterval\" and \"minterval\".'
        # default = 1.0;
        self.propertyHelp[2] = 'Array of multiplier values for active power (P).  You can also use the syntax: ' + CRLF + 'mult = (file=filename)     !for text file one value per line' + CRLF + 'mult = (dblfile=filename)  !for packed file of doubles' + CRLF + 'mult = (sngfile=filename)  !for packed file of singles ' + CRLF + CRLF + 'Note: this property will reset Npts if the  number of values in the files are fewer.'
        # vextor of hour values
        self.propertyHelp[3] = 'Array of hour values. Only necessary to define for variable interval data.' + ' If the data are fixed interval, do not use this property. ' + 'You can also use the syntax: ' + CRLF + 'hour = (file=filename)     !for text file one value per line' + CRLF + 'hour = (dblfile=filename)  !for packed file of doubles' + CRLF + 'hour = (sngfile=filename)  !for packed file of singles '
        # vextor of hour values
        self.propertyHelp[4] = 'Mean of the active power multipliers.  This is computed on demand the first time a ' + 'value is needed.  However, you may set it to another value independently. ' + 'Used for Monte Carlo load simulations.'
        # set the mean (otherwise computed)
        self.propertyHelp[5] = 'Standard deviation of active power multipliers.  This is computed on demand the first time a ' + 'value is needed.  However, you may set it to another value independently.' + 'Is overwritten if you subsequently read in a curve' + CRLF + CRLF + 'Used for Monte Carlo load simulations.'
        # set the std dev (otherwise computed)
        self.propertyHelp[6] = 'Switch input of active power load curve data to a csv file ' + 'containing (hour, mult) points, or simply (mult) values for fixed time interval data, one per line. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # Switch input to a csvfile
        self.propertyHelp[7] = 'Switch input of active power load curve data to a binary file of singles ' + 'containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[8] = 'Switch input of active power load curve data to a binary file of doubles ' + 'containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. ' + 'NOTE: This action may reset the number of points to a lower value.'
        # switch input to a binary file of singles
        self.propertyHelp[9] = '{NORMALIZE | DblSave | SngSave} After defining load curve data, setting action=normalize ' + 'will modify the multipliers so that the peak is 1.0. ' + 'The mean and std deviation are recomputed.' + CRLF + CRLF + 'Setting action=DblSave or SngSave will cause the present mult and qmult values to be written to ' + 'either a packed file of double or single. The filename is the loadshape name. The mult array will have a ' + '\"_P\" appended on the file name and the qmult array, if it exists, will have \"_Q\" appended.'
        # Action
        self.propertyHelp[10] = 'Array of multiplier values for reactive power (Q).  You can also use the syntax: ' + CRLF + 'qmult = (file=filename)     !for text file one value per line' + CRLF + 'qmult = (dblfile=filename)  !for packed file of doubles' + CRLF + 'qmult = (sngfile=filename)  !for packed file of singles '
        # vector of qmultiplier values
        self.propertyHelp[11] = '{Yes | No* | True | False*} If true, signals to Load, Generator, or other objects to ' + 'use the return value as the actual kW, kvar value rather than a multiplier. Nominally for AMI data.'
        self.propertyHelp[12] = 'kW value at the time of max power. Is automatically set upon reading in a loadshape. ' + 'Use this property to override the value automatically computed or to retrieve the value computed.'
        self.propertyHelp[13] = 'kvar value at the time of max kW power. Is automatically set upon reading in a loadshape. ' + 'Use this property to override the value automatically computed or to retrieve the value computed.'
        self.propertyHelp[14] = 'Specify fixed interval in SECONDS. Alternate way to specify Interval property.'
        self.propertyHelp[15] = 'Specify fixed interval in MINUTES. Alternate way to specify Interval property.'
        self.activeProperty = LoadShape.NumPropsThisClass - 1
        super(LoadShapeImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = LoadShapeObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeLoadShapeObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeLoadShapeObj
        als = self.activeLoadShapeObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
                if paramPointer > 0 and paramPointer <= self.numProperties:
                    als.setPropertyValue(paramPointer, param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + als.getName() + '\"', 610)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        als.setNumPoints(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        als.setInterval(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        als.setPMultipliers(Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()))
                        # allow possible resetting (to a lower value) of num points when specifying multipliers not Hours
                        als.setNumPoints(Utilities.interpretDblArray(param, als.getNumPoints(), als.getPMultipliers()))
                        # parser.parseAsVector(Npts, Multipliers);
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        als.setHours(Utilities.resizeArray(als.getHours(), als.getNumPoints()))
                        Utilities.interpretDblArray(param, als.getNumPoints(), als.getHours())
                        # parser.parseAsVector(Npts, Hours);
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        als.setMean(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        als.setStdDev(parser.makeDouble())
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
                        _2 = param.toLowerCase()[0]
                        _3 = False
                        while True:
                            if _2 == 'n':
                                _3 = True
                                als.normalize()
                                break
                            if (_3 is True) or (_2 == 'd'):
                                _3 = True
                                als.saveToDblFile()
                                break
                            if (_3 is True) or (_2 == 's'):
                                _3 = True
                                als.saveToSngFile()
                                break
                            break
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        als.setQMultipliers(Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()))
                        Utilities.interpretDblArray(param, als.getNumPoints(), als.getQMultipliers())
                        # parser.parseAsVector(Npts, Multipliers);
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        als.setUseActual(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        als.setMaxP(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        als.setMaxQ(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        als.setInterval(parser.makeDouble() / 3600.0)
                        # convert seconds to hr
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        als.setInterval(parser.makeDouble() / 60.0)
                        # convert minutes to hr
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeLoadShapeObj, paramPointer - LoadShape.NumPropsThisClass)
                        break
                    break
                # TODO Check zero based indexing
                _4 = paramPointer
                _5 = False
                while True:
                    if _4 == 2:
                        _5 = True
                        als.setStdDevCalculated(False)
                        # now calculated on demand
                        als.setArrayPropertyIndex(paramPointer)
                        als.setNumPoints(als.getNumPoints())
                        # keep properties in order for save command
                        break
                    if (_5 is True) or (_4 == 6):
                        _5 = True
                        als.setStdDevCalculated(False)
                        als.setArrayPropertyIndex(paramPointer)
                        als.setNumPoints(als.getNumPoints())
                        break
                    if (_5 is True) or (_4 == 7):
                        _5 = True
                        als.setStdDevCalculated(False)
                        als.setArrayPropertyIndex(paramPointer)
                        als.setNumPoints(als.getNumPoints())
                        break
                    if (_5 is True) or (_4 == 8):
                        _5 = True
                        als.setStdDevCalculated(False)
                        als.setArrayPropertyIndex(paramPointer)
                        als.setNumPoints(als.getNumPoints())
                        break
                    if (_5 is True) or (_4 == 10):
                        _5 = True
                        als.setStdDevCalculated(False)
                        als.setArrayPropertyIndex(paramPointer)
                        als.setNumPoints(als.getNumPoints())
                        break
                    break
                paramName = parser.getNextParam()
                param = parser.makeString()
        if als.getPMultipliers() is not None:
            als.setMaxPandQ()
        return result

    def find(self, objName):
        """Find an obj of this class by name."""
        if (len(objName) == 0) or objName.equalsIgnoreCase('none'):
            return None
        else:
            return super(LoadShapeImpl, self).find(objName)

    def makeLike(self, shapeName):
        result = 0
        # See if we can find this line code in the present collection
        otherLoadShape = self.find(shapeName)
        if otherLoadShape is not None:
            als = self.activeLoadShapeObj
            als.setNumPoints(otherLoadShape.getNumPoints())
            als.setInterval(otherLoadShape.getInterval())
            als.setPMultipliers(Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < als.getNumPoints()):
                    break
                als.getPMultipliers()[i] = otherLoadShape.getPMultipliers()[i]
            if otherLoadShape.getQMultipliers() is not None:
                als.setQMultipliers(Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()))
            als.setQMultipliers(Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()))
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < als.getNumPoints()):
                    break
                als.getQMultipliers()[i] = otherLoadShape.getQMultipliers()[i]
            if als.getInterval() > 0.0:
                als.setHours([None] * 0)
            else:
                als.setHours(Utilities.resizeArray(als.getHours(), als.getNumPoints()))
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < als.getNumPoints()):
                        break
                    als.getHours()[i] = otherLoadShape.getHours()[i]
            als.setMaxPandQ()
            als.setUseActual(otherLoadShape.isUseActual())
            # als.setMaxP(OtherLoadShape.getMaxP());
            # 			als.setMaxQ(OtherLoadShape.getMaxQ());
            # 			als.setMean(OtherLoadShape.getMean());
            # 			als.setStdDev(OtherLoadShape.getStdDev());

            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < als.getParentClass().getNumProperties()):
                    break
                als.setPropertyValue(i, otherLoadShape.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in LoadShape makeLike: \"' + shapeName + '\" not found.', 611)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement LoadShape.init()', -1)
        return 0

    def getCode(self):
        """Returns active LoadShape string."""
        pShape = self.elementList.getActive()
        return pShape.getName()

    def setCode(self, value):
        """Sets the active LoadShape."""
        self.activeLoadShapeObj = None
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
                self.activeLoadShapeObj = pShape
                return
        DSSGlobals.doSimpleMsg('LoadShape: \"' + value + '\" not found.', 612)

    def doCSVFile(self, fileName):
        try:
            fis = self.FileInputStream(fileName)
            dis = self.DataInputStream(fis)
            br = StringIO(self.InputStreamReader(dis))
            als = self.activeLoadShapeObj
            als.setPMultipliers(Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()))
            if als.getInterval() == 0.0:
                als.setHours(Utilities.resizeArray(als.getHours(), als.getNumPoints()))
            i = 0
            while (
                s = # TODO: Check zero based indexingbr.readline() is not None and i < als.getNumPoints()
            ):
                i += 1
                # aux parser allows commas or white space
                parser = DSSGlobals.auxParser
                parser.setCmdString(s)
                if als.getInterval() == 0.0:
                    parser.getNextParam()
                    als.getHours()[i] = parser.makeDouble()
                parser.getNextParam()
                als.getPMultipliers()[i] = parser.makeDouble()
            fis.close()
            dis.close()
            br.close()
            if i != als.getNumPoints():
                als.setNumPoints(i)
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

    def TOPExport(self, objName):
        # FIXME Implement this method
        raise self.UnsupportedOperationException()

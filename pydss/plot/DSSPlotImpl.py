from __pyjamas__ import (ARGERROR,)
from dss.plot.DSSPlot import (DSSPlot,)
# from java.awt.Color import (Color,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)


class DSSPlotImpl(object, DSSPlot):
    DSSPlotObj = None
    addMarkerColor = None
    addMarkerCode = None
    addMarkerSize = None
    singlePhLineStyle = None
    threePhLineStyle = None

    class PlotType(object):
        AUTO_ADD_LOG_PLOT = 'AUTO_ADD_LOG_PLOT'
        CIRCUIT_PLOT = 'CIRCUIT_PLOT'
        GENERAL_DATA_PLOT = 'GENERAL_DATA_PLOT'
        GENERAL_CIRCUIT_PLOT = 'GENERAL_CIRCUIT_PLOT'
        MONITOR_PLOT = 'MONITOR_PLOT'
        DAISY_PLOT = 'DAISY_PLOT'
        METER_ZONES = 'METER_ZONES'
        LOAD_SHAPE = 'LOAD_SHAPE'
        TSHAPE = 'TSHAPE'
        PRICE_SHAPE = 'PRICE_SHAPE'
        PROFILE = 'PROFILE'
        _values = [AUTO_ADD_LOG_PLOT, CIRCUIT_PLOT, GENERAL_DATA_PLOT, GENERAL_CIRCUIT_PLOT, MONITOR_PLOT, DAISY_PLOT, METER_ZONES, LOAD_SHAPE, TSHAPE, PRICE_SHAPE, PROFILE]

        @classmethod
        def values(cls):
            return cls._enum_values[:]

    class PlotQuantity(object):
        VOLTAGE = 'VOLTAGE'
        CURRENT = 'CURRENT'
        POWER = 'POWER'
        LOSSES = 'LOSSES'
        CAPACITY = 'CAPACITY'
        NONE = 'NONE'
        _values = [VOLTAGE, CURRENT, POWER, LOSSES, CAPACITY, NONE]

        @classmethod
        def values(cls):
            return cls._enum_values[:]

    activeColorIdx = None
    colorArray = [None] * 17
    line = None
    transf = None
    bus1Idx = None
    bus2Idx = None
    generalCircuitPlotQuantity = None
    maxLineThickness = None
    plotType = None
    maxScale = None
    minScale = None
    dots = None
    labels = None
    showLoops = None
    showSubs = None
    # applies to meterZone plots only
    quantity = None
    objectName = None
    feederName = None
    valueIndex = None
    markerIdx = None
    # for general & autoAdd
    phasesToPlot = None
    # profile plot
    channels = None
    # for monitor plot
    bases = None
    # for monitor plot
    color1 = None
    color2 = None
    color3 = None
    # Tri-color plots
    triColorMax = None
    triColorMid = None
    maxScaleIsSpecified = None
    minScaleIsSpecified = None
    daisyBusList = None

    def __init__(self):
        # TODO Auto-generated constructor stub
        pass

    def doGeneralPlot(self):
        pass

    def doAutoAddPlot(self):
        pass

    def doTheDaisies(self):
        pass

    def doCircuitPlot(self):
        pass

    def doGeneralCircuitPlot(self):
        pass

    def doMeterZonePlot(self):
        pass

    def doMonitorPlot(self):
        pass

    def doProfilePlot(self):
        pass

    def markSubTransformers(self):
        """Misc support procedures"""
        pass

    def markTheTransformers(self):
        pass

    def doBusLabels(self, idx1, idx2):
        pass

    def doBusLabel(self, idx, busLabel):
        pass

    def labelBuses(self):
        pass

    def loadGeneralLineData(self):
        pass

    def setColorArray(self):
        pass

    def setMaxScale(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            pass # astStmt: [Stmt([]), None]
        elif _1 == 1:
            max, = _0
            self.maxScale = max
        else:
            raise ARGERROR(0, 1)

    def getColor(self):
        return 0

    def thickness(self):
        return 0

    def maxCurrent(self):
        return 0.0

    def nextColor(self):
        return None

    def quantityString(self):
        # private PenStyle style(int code) {
        # return null;
        # }
        return None

    def getAutoColor(self, scale):
        return None

    def getMarker(self, idx):
        return None

    def coordinateSame(self, i1, i2):
        return False

    def interpolateGradientColor(self, color1, color2, ratio):
        return None

    def setMaxLineThickness(self, value):
        pass

    def getMaxLineThickness(self):
        return self.maxLineThickness

    def getPlotType(self):
        return self.plotType

    def setPlotType(self, type):
        self.plotType = type

    def getMaxScale(self):
        return self.maxScale

    def getMinScale(self):
        return self.minScale

    def setMinScale(self, min):
        self.minScale = min

    def isDots(self):
        return self.dots

    def setDots(self, value):
        self.dots = value

    def isLabels(self):
        return self.labels

    def setLabels(self, lbl):
        self.labels = lbl

    def isShowLoops(self):
        return self.showLoops

    def setShowLoops(self, show):
        self.showLoops = show

    def isShowSubs(self):
        return self.showSubs

    def setShowSubs(self, show):
        self.showSubs = show

    def getQuantity(self):
        return self.quantity

    def setQuantity(self, qty):
        self.quantity = qty

    def getObjectName(self):
        return self.objectName

    def setObjectName(self, name):
        self.objectName = name

    def getFeederName(self):
        return self.feederName

    def setFeederName(self, name):
        self.feederName = name

    def getValueIndex(self):
        return self.valueIndex

    def setValueIndex(self, index):
        self.valueIndex = index

    def getMarkerIdx(self):
        return self.markerIdx

    def setMarkerIdx(self, idx):
        self.markerIdx = idx

    def getPhasesToPlot(self):
        return self.phasesToPlot

    def setPhasesToPlot(self, phases):
        self.phasesToPlot = phases

    def getChannels(self):
        return self.channels

    def setChannels(self, values):
        self.channels = values

    def getBases(self):
        return self.bases

    def setBases(self, values):
        self.bases = values

    def getColor1(self):
        return self.color1

    def setColor1(self, value):
        self.color1 = value

    def getColor2(self):
        return self.color2

    def setColor2(self, value):
        self.color2 = value

    def getColor3(self):
        return self.color3

    def setColor3(self, value):
        self.color3 = value

    def getTriColorMax(self):
        return self.triColorMax

    def setTriColorMax(self, max):
        self.triColorMax = max

    def getTriColorMid(self):
        return self.triColorMid

    def setTriColorMid(self, mid):
        self.triColorMid = mid

    def isMaxScaleIsSpecified(self):
        return self.maxScaleIsSpecified

    def setMaxScaleIsSpecified(self, isSpecified):
        self.maxScaleIsSpecified = isSpecified

    def isMinScaleIsSpecified(self):
        return self.minScaleIsSpecified

    def setMinScaleIsSpecified(self, isSpecified):
        self.minScaleIsSpecified = isSpecified

    def getDaisyBusList(self):
        return self.daisyBusList

    def setDaisyBusList(self, list):
        self.daisyBusList = list

    def execute(self):
        pass

    def setDefaults(self):
        pass

    def doLoadShapePlot(self, loadShapeName):
        pass

    def doTempShapePlot(self, tempShapeName):
        pass

    def doPriceShapePlot(self, priceShapeName):
        pass

    def doDI_Plot(self, caseName, caseYear, iRegisters, peakDay, meterName):
        pass

    def doCompareCases(self, caseName1, caseName2, whichFile, reg):
        pass

    def doYearlyCurvePlot(self, caseNames, whichFile, iRegisters):
        pass

    def doVisualizationPlot(self, element, quantity):
        pass

    @classmethod
    def setDSSPlotObj(cls, plotObj):
        cls.DSSPlotObj = plotObj

    @classmethod
    def getDSSPlotObj(cls):
        return cls.DSSPlotObj

    @classmethod
    def getAddMarkerColor(cls):
        return cls.addMarkerColor

    @classmethod
    def setAddMarkerColor(cls, color):
        cls.addMarkerColor = color

    @classmethod
    def getAddMarkerCode(cls):
        return cls.addMarkerCode

    @classmethod
    def setAddMarkerCode(cls, code):
        cls.addMarkerCode = code

    @classmethod
    def getAddMarkerSize(cls):
        return cls.addMarkerSize

    @classmethod
    def setAddMarkerSize(cls, size):
        cls.addMarkerSize = size

    @classmethod
    def getSinglePhLineStyle(cls):
        return cls.singlePhLineStyle

    @classmethod
    def setSinglePhLineStyle(cls, style):
        cls.singlePhLineStyle = style

    @classmethod
    def getThreePhLineStyle(cls):
        return cls.threePhLineStyle

    @classmethod
    def setThreePhLineStyle(cls, style):
        cls.threePhLineStyle = style

from dss.plot.impl.DSSPlotImpl import (DSSPlotImpl, PlotQuantity, PlotType,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.parser.impl.Parser import (Parser,)
# from com.epri.dss.plot.impl.DSSPlotImpl.PlotQuantity import (PlotQuantity,)
# from com.epri.dss.plot.impl.DSSPlotImpl.PlotType import (PlotType,)


class PlotOptions(object):
    CRLF = DSSGlobals.CRLF
    NumPlotOptions = 17
    plotOption = None
    plotHelp = None
    plotCommands = None
    # Private constructor prevents instantiation from other classes

    def __init__(self):
        self.defineOptions()
        self.plotCommands = CommandListImpl(self.plotOption)
        self.plotCommands.setAbbrevAllowed(True)

    class PlotOptionsHolder(object):
        """PlotOptionsHolder is loaded on the first execution of
        PlotOptions.getInstance() or the first access to
        PlotOptionsHolder.INSTANCE, not before.
        """
        INSTANCE = PlotOptions()

    @classmethod
    def getInstance(cls):
        return cls.PlotOptionsHolder.INSTANCE

    def defineOptions(self):
        self.plotOption = [None] * self.NumPlotOptions
        self.plotOption[0] = 'type'
        self.plotOption[1] = 'quantity'
        self.plotOption[2] = 'max'
        self.plotOption[3] = 'dots'
        self.plotOption[4] = 'labels'
        self.plotOption[5] = 'object'
        self.plotOption[6] = 'showloops'
        self.plotOption[7] = 'r3'
        self.plotOption[8] = 'r2'
        self.plotOption[9] = 'c1'
        self.plotOption[10] = 'c2'
        self.plotOption[11] = 'c3'
        self.plotOption[12] = 'channels'
        self.plotOption[13] = 'bases'
        self.plotOption[14] = 'subs'
        self.plotOption[15] = 'thickness'
        self.plotOption[16] = 'buslist'
        self.plotOption[17] = 'min'
        self.plotOption[18] = '3phLinestyle'
        self.plotOption[19] = '1phLinestyle'
        self.plotOption[20] = 'phases'
        self.plotHelp = [None] * self.NumPlotOptions
        self.plotHelp[0] = 'One of {Circuit | Monitor | Daisy | Zones | AutoAdd | ' + self.CRLF + 'General (bus data) | Loadshape Tshape | Priceshape | Profile} ' + self.CRLF + 'A \"Daisy\" plot is a special circuit plot that places a marker at each Generator location ' + 'or at buses in the BusList property, if defined. ' + 'A Zones plot shows the meter zones (see help on Object). ' + 'Autoadd shows the autoadded generators. General plot shows quantities associated with buses ' + 'using gradient colors between C1 and C2. Values are read from a file (see Object). ' + 'Loadshape plots the specified loadshape. Examples:' + self.CRLF + self.CRLF + 'Plot type=circuit quantity=power' + self.CRLF + 'Plot Circuit Losses' + self.CRLF + 'Plot Circuit quantity=3 object=mybranchdata.csv' + self.CRLF + 'Plot daisy power max=5000 dots=N Buslist=[file=MyBusList.txt]' + self.CRLF + 'Plot General quantity=1 object=mybusdata.csv' + self.CRLF + 'Plot Loadshape object=myloadshape' + self.CRLF + 'Plot Tshape object=mytemperatureshape' + self.CRLF + 'Plot Priceshape object=mypriceshape' + self.CRLF + 'Plot Profile' + self.CRLF + 'Plot Profile Phases=Primary'
        self.plotHelp[1] = 'One of {Voltage | Current | Power | Losses | Capacity | (Value Index for General, AutoAdd, or Circuit[w/ file]) }'
        self.plotHelp[2] = 'Enter 0 or the value corresponding to max scale or line thickness in the circuit plots. ' + 'Power and Losses in kW.'
        self.plotHelp[3] = 'Yes or No*. Places a marker on the circuit plot at the bus location. See Set Markercode under options.'
        self.plotHelp[4] = 'Yes or No*. If yes, bus labels (abbreviated) are printed on the circuit plot.'
        self.plotHelp[5] = 'Object to be plotted. One of [Meter Name (zones plot) | Monitor Name | LoadShape Name | File Name for General bus data | File Name Circuit branch data]'
        self.plotHelp[6] = '{Yes | No*} Shows loops on Circuit plot. Requires an EnergyMeter to be defined.'
        self.plotHelp[7] = 'pu value for tri-color plot max range [default=.85 of max scale]. Corresponds to color C3.'
        self.plotHelp[8] = 'pu value for tri-color plot mid range [default=.50 of max scale]. Corresponds to color C2.'
        self.plotHelp[9] = 'RGB color number for color C1. This is the default color for circuit plots. Default is blue. See options in the Plot menu.'
        self.plotHelp[10] = 'RGB color number for color C2. Used for gradients and tricolor plots such as circuit voltage.'
        self.plotHelp[11] = 'RGB color number for color C3. Used for gradients and tricolor plots such a circuit voltage.'
        self.plotHelp[12] = 'Array of channel numbers for monitor plot. Example' + self.CRLF + self.CRLF + 'Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5]' + self.CRLF + self.CRLF + 'Do \"Show Monitor MyMonitor\" to see channel definitions.'
        self.plotHelp[13] = 'Array of base values for each channel for monitor plot. Useful for creating per unit plots. Default is 1.0 for each channel.  Set Base= property after defining channels.' + self.CRLF + self.CRLF + 'Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5] Bases=[2400 2400 2400]' + self.CRLF + self.CRLF + 'Do \"Show Monitor MyMonitor\" to see channel range and definitions.'
        self.plotHelp[14] = '{Yes | No*} Displays a marker at each transformer declared to be a substation. ' + 'At least one bus coordinate must be defined for the transformer. ' + 'See MarkTransformer and TransMarkerCode options.'
        self.plotHelp[15] = 'Max thickness allowed for lines in circuit plots (default=7).'
        self.plotHelp[16] = '{Array of Bus Names | File=filename } This is for the Daisy plot. ' + self.CRLF + self.CRLF + 'Plot daisy power max=5000 dots=N Buslist=[file=MyBusList.txt]' + self.CRLF + self.CRLF + 'A \"daisy\" marker is plotted for ' + 'each bus in the list. Bus name may be repeated, which results in multiple markers distributed around the bus location. ' + 'This gives the appearance of a daisy if there are several symbols at a bus. Not needed for plotting active generators.'
        self.plotHelp[17] = 'Enter 0 (the default value) or the value corresponding to min value corresponding to color C1 in General bus data plots.'
        self.plotHelp[18] = 'Line style for drawing 3-phase lines. A number in the range of [1..7].Default is 1 (solid). Use 3 for dotted; 2 for dashed.'
        self.plotHelp[19] = 'Line style for drawing 1-phase lines. A number in the range of [1..7].Default is 1 (solid). Use 3 for dotted; 2 for dashed.'
        self.plotHelp[20] = '{default* | ALL | PRIMARY | LL3ph | LLALL | LLPRIMARY | (phase number)} For Profile plot. Specify which phases you want plotted.' + self.CRLF + self.CRLF + 'default = plot only nodes 1-3 at 3-phase buses (default)' + self.CRLF + 'ALL = plot all nodes' + self.CRLF + 'PRIMARY = plot all nodes -- primary only (voltage > 1kV)' + self.CRLF + 'LL3ph = 3-ph buses only -- L-L voltages)' + self.CRLF + 'LLALL = plot all nodes -- L-L voltages)' + self.CRLF + 'LLPRIMARY = plot all nodes -- L-L voltages primary only)' + self.CRLF + '(phase number) = plot all nodes on selected phase' + self.CRLF + self.CRLF + 'Note: Only nodes downline from an energy meter are plotted.'

    def doPlotCmd(self):
        """Produce a plot with the DSSGraphX object."""
        parser = Parser.getInstance()
        dblBuffer = [None] * 50
        result = 0
        if DSSGlobals.noFormsAllowed:
            result = 1
            return result
        if DSSPlotImpl.getDSSPlotObj() is None:
            DSSPlotImpl.setDSSPlotObj(DSSPlotImpl())
        DSSPlotImpl.getDSSPlotObj().setDefaults()
        # Get next parameter on command line
        paramPointer = 0
        paramName = parser.getNextParam().toUpperCase()
        param = parser.makeString().toUpperCase()
        while # Interpret Parameterlen(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.plotCommands.getCommand(paramName)
            plot = DSSPlotImpl.getDSSPlotObj()
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    _2 = param[0]
                    _3 = False
                    while True:
                        if _2 == 'A':
                            _3 = True
                            plot.setPlotType(PlotType.AUTO_ADD_LOG_PLOT)
                            plot.setObjectName(DSSGlobals.circuitName_ + 'AutoAddLog.csv')
                            plot.setValueIndex(2)
                            break
                        if (_3 is True) or (_2 == 'C'):
                            _3 = True
                            plot.setPlotType(PlotType.CIRCUIT_PLOT)
                            break
                        if (_3 is True) or (_2 == 'G'):
                            _3 = True
                            plot.setPlotType(PlotType.GENERAL_DATA_PLOT)
                            break
                        if (_3 is True) or (_2 == 'L'):
                            _3 = True
                            plot.setPlotType(PlotType.LOAD_SHAPE)
                            break
                        if (_3 is True) or (_2 == 'M'):
                            _3 = True
                            plot.setPlotType(PlotType.MONITOR_PLOT)
                            break
                        if (_3 is True) or (_2 == 'P'):
                            _3 = True
                            if Utilities.compareTextShortest('pro', param) == 0:
                                plot.setPlotType(PlotType.PROFILE)
                            else:
                                plot.setPlotType(PlotType.PRICE_SHAPE)
                            break
                        if (_3 is True) or (_2 == 'T'):
                            _3 = True
                            plot.setPlotType(PlotType.TSHAPE)
                            break
                        if (_3 is True) or (_2 == 'D'):
                            _3 = True
                            plot.setPlotType(PlotType.DAISY_PLOT)
                            plot.getDaisyBusList().clear()
                            break
                        if (_3 is True) or (_2 == 'Z'):
                            _3 = True
                            plot.setPlotType(PlotType.METER_ZONES)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    _4 = param[0]
                    _5 = False
                    while True:
                        if _4 == 'V':
                            _5 = True
                            plot.setQuantity(PlotQuantity.VOLTAGE)
                            break
                        if (_5 is True) or (_4 == 'C'):
                            _5 = True
                            _6 = param[1]
                            _7 = False
                            while True:
                                if _6 == 'A':
                                    _7 = True
                                    plot.setQuantity(PlotQuantity.CAPACITY)
                                    break
                                if (_7 is True) or (_6 == 'U'):
                                    _7 = True
                                    plot.setQuantity(PlotQuantity.CURRENT)
                                    break
                                break
                            break
                        if (_5 is True) or (_4 == 'P'):
                            _5 = True
                            plot.setQuantity(PlotQuantity.POWER)
                            break
                        if (_5 is True) or (_4 == 'L'):
                            _5 = True
                            plot.setQuantity(PlotQuantity.LOSSES)
                            break
                        if True:
                            _5 = True
                            plot.setQuantity(PlotQuantity.NONE)
                            plot.setValueIndex(parser.makeInteger())
                            break
                        break
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    plot.setMaxScale(parser.makeDouble())
                    if plot.getMaxScale() > 0.0:
                        plot.setMaxScaleIsSpecified(True)
                        # Indicate the user wants a particular value
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    plot.setDots(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    plot.setLabels(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    plot.setObjectName(parser.makeString())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    plot.setShowLoops(Utilities.interpretYesNo(param))
                    if plot.isShowLoops():
                        plot.setPlotType(PlotType.METER_ZONES)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    plot.setTriColorMax(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    plot.setTriColorMid(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    plot.setColor1(Utilities.interpretColor(param))
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    plot.setColor2(Utilities.interpretColor(param))
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    plot.setColor3(Utilities.interpretColor(param))
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    numChannels = parser.parseAsVector(51, dblBuffer)
                    # allow up to 50 channels
                    if numChannels > 0:
                        # Else take the defaults
                        plot.setChannels([None] * numChannels)
                        _8 = True
                        i = 0
                        while True:
                            if _8 is True:
                                _8 = False
                            else:
                                i += 1
                            if not (i < numChannels - 1):
                                break
                            # TODO Check zero indexing
                            plot.getChannels()[i] = dblBuffer[i]
                        plot.setBases([None] * numChannels)
                        _9 = True
                        i = 0
                        while True:
                            if _9 is True:
                                _9 = False
                            else:
                                i += 1
                            if not (i < numChannels - 1):
                                break
                            # TODO Check zero indexing
                            plot.getBases()[i] = 1.0
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    numChannels = parser.parseAsVector(51, dblBuffer)
                    # allow up to 50 channels
                    if numChannels > 0:
                        plot.setBases([None] * numChannels)
                        _10 = True
                        i = 0
                        while True:
                            if _10 is True:
                                _10 = False
                            else:
                                i += 1
                            if not (i < numChannels - 1):
                                break
                            # TODO Check zero indexing
                            plot.getBases()[i] = dblBuffer[i]
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    plot.setShowSubs(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    plot.setMaxLineThickness(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    Utilities.interpretStringListArray(param, plot.getDaisyBusList())
                    # read in Bus list
                    break
                    # case 17:
                    # 				plot.setMinScale(parser.makeDouble());
                    # 				plot.setMinScaleIsSpecified(true);    // Indicate the user wants a particular value
                    # 				break;

                    # case 18:
                    # 				plot.setThreePhLineStyle = parser.makeInteger();
                    # 				break;

                    # case 19:
                    # 				plot.setSinglePhLineStyle = parser.makeInteger();
                    # 				break;

                    # case 20:  // Parse off phase(s) to plot
                    # 				plot.setPhasesToPlot(PROFILE3PH); // the default
                    # 				if (Utilities.compareTextShortest(Param, "default") == 0) {
                    # 					plot.setPhasesToPlot(PROFILE3PH);
                    # 				} else if (Utilities.compareTextShortest(Param, "all") == 0) {
                    # 					plot.setPhasesToPlot(PROFILEALL);
                    # 				} else if (Utilities.compareTextShortest(Param, "primary") == 0) {
                    # 					plot.setPhasesToPlot(PROFILEALLPRI) {
                    # 				} else if (Param.length() == 1) {
                    # 					plot.setPhasesToPlot(parser.makeInteger());
                    # 				}
                    # 				break;

                break
            paramName = parser.getNextParam().toUpperCase()
            param = parser.makeString().toUpperCase()
        if DSSGlobals.activeCircuit.isSolved():
            DSSPlotImpl.getDSSPlotObj().setQuantity(PlotQuantity.NONE)
        DSSPlotImpl.getDSSPlotObj().execute()
        # makes a new plot based on these options
        return result

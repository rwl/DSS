# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

import os
import logging

from dss.util import \
    integer, boolean, real, tilde, lbrack, rbrack, equals, dot, comma

dot = dot.suppress()
comma = comma.suppress()
equals = equals.suppress()

from pyparsing import \
    Literal, Word, ZeroOrMore, Optional, Or, OneOrMore, delimitedList, \
    CaselessLiteral, Combine, restOfLine, quotedString, CaselessKeyword, \
    oneOf, ParseException, alphas, Keyword, printables, alphanums, Group, \
    MatchFirst, Dict, dblQuotedString


from dss.common.Circuit import Circuit
from dss.control.CapacitorControl import CapacitorControl
from dss.control.GeneratorDispatcher import  GeneratorDispatcher
from dss.control.Recloser import Recloser
from dss.control.RegulatorControl import RegulatorControl
from dss.control.Relay import Relay
from dss.control.StorageController import StorageController
from dss.control.SwitchControl import SwitchControl

from dss.conversion.CurrentSource import CurrentSource
from dss.conversion.Equivalent import Equivalent
from dss.conversion.Generator import Generator
from dss.conversion.Load import Load
from dss.conversion.VoltageSource import VoltageSource
from dss.conversion.Storage import Storage

from dss.delivery.Capacitor import Capacitor
from dss.delivery.Fault import Fault
from dss.delivery.Fuse import Fuse
from dss.delivery.Line import Line
from dss.delivery.Reactor import Reactor
from dss.delivery.Transformer import Transformer

from dss.general.LineCode import LineCode
from dss.general.LineGeometry import LineGeometry
from dss.general.LineSpacing import LineSpacing
from dss.general.LoadShape import LoadShape
from dss.general.Spectrum import Spectrum
from dss.general.TimeCurrentCurve import TimeCurrentCurve
from dss.general.WireData import WireData
from dss.general.TransformerCode import TransformerCode

from dss.meter.EnergyMeter import EnergyMeter
from dss.meter.Monitor import Monitor
from dss.meter.Sensor import Sensor

logger = logging.getLogger(__name__)


CLSMAP = {
    "circuit": Circuit,
    "capacitorcontrol": CapacitorControl,
    "generatordispatcher": GeneratorDispatcher,
    "recloser": Recloser,
    "regulatorcontrol": RegulatorControl,
    "regcontrol": RegulatorControl,
    "relay": Relay,
    "storagecontroller": StorageController,
    "switchcontrol": SwitchControl,
    "currentsource": CurrentSource,
    "equivalent": Equivalent,
    "generator": Generator,
    "load": Load,
    "voltagesource": VoltageSource,
    "storage": Storage,
    "capacitor": Capacitor,
    "fault": Fault,
    "fuse": Fuse,
    "line": Line,
    "reactor": Reactor,
    "transformer": Transformer,
#    "growthshape": GrowthShape,
    "linecode": LineCode,
    "linegeometry": LineGeometry,
    "linespacing": LineSpacing,
    "loadshape": LoadShape,
    "spectrum": Spectrum,
    "timecurrentcurve": TimeCurrentCurve,
    "wiredata": WireData,
    "transformercode": TransformerCode,
    "energymeter": EnergyMeter,
    "monitor": Monitor,
    "sensor": Sensor
}


class DSSParser(object):

    active_obj = None
    prev_cls = None

    def __init__(self):
        self._dssparser = self.dSSDefinition()


#    def parse_dss(self, filename):
#        logger.info("Parsing DSS file [%s]." % os.path.basename(filename))
#
#        dssparser = self.dss_definition()
#
#        tokens = dssparser.parseFile(filename)
#
#        if len(tokens) == 1:
#            return tokens[0]
#        else:
#            return [t for t in tokens]


    def parseString(self, data):
        try:
            tokens = self._dssparser.parseString(data)
#            print "TOKENS:", tokens
            return tokens
        except ParseException, err:
            print err.line
            print " "*(err.column-1) + "^"
            print err
            return None


    def dSSDefinition(self):
        object = CaselessKeyword("Object")
        bus1 = CaselessKeyword("Bus1")
        bus2 = CaselessKeyword("Bus2")

        # The command language is of the form:
        #     Command parm1, parm2 parm3 parm 4 ....
        # Parameters (parm1, etc) may be separated by commas (,) or white space
        # (blank, tab). If a parameter includes a delimiter, enclose it in
        # either double quotes ("), single quotes(') or parentheses (... ).
        # While any of these will work, double or single quotes are preferred
        # for strings. Brackets [..] are preferred for arrays, and curly braces
        # {..} are preferred for in-line math.

#        abbreviation = Or(cmd_keyword, name)
#        abbreviation.setParseAction(self._push_abbreviation)

        arg = Word(alphanums)

        clear = CaselessKeyword("Clear")


#        cmd_verb = oneOf([clear, more])


#        command = oneOf(["new", "clear"], caseless=True)#.setResultsName("cmd")

#        command = MatchFirst([new, clear]).setResultsName("command")

        array_str_delim = Literal("[]{}()\"'").suppress()
        row_delim = Literal("|")
        value_delim = comma | Literal(" ")
        cls_obj_bus_node_delim = dot
        key_val_sep = equals
        continuation = tilde



        array = array_str_delim + delimitedList(real) + array_str_delim
        matrix = array_str_delim + delimitedList(
            delimitedList(real), delim="|") + array_str_delim
        symmetrical_matrix = matrix

        name = arg
        key = arg.setResultsName("key")
        value = Word(alphanums, alphanums+"_"+".") #Or(array )
        positional = Group(value)# + comma
        named = Group(key + equals.suppress() + value)# + Optional(comma)
#        default_order = OneOrMore(positional)

#        args_kwargs = ZeroOrMore(positional) + ZeroOrMore(named)
#        args = ZeroOrMore(positional)
#        kwargs = ZeroOrMore(named).setResultsName("kwargs")

#        params = ZeroOrMore(positional) + ZeroOrMore(named)
        params = ZeroOrMore(named | positional).setResultsName("params")


#        obj_prop_name = clsname + dot + element_name + dot + key
#        prop_query = Keyword("?") + obj_prop_name

        commands = self.commandConstruct()
#        command_syntax = command + params

        sequence = ZeroOrMore(commands)

        # )
#        params.setParseAction(self.pushParameters)
#        command_syntax.setParseAction(self.pushCommandSyntax)

        return sequence


    def quantityConstruct(self):
        quantity = Or([CaselessLiteral("Currents"),
                      CaselessLiteral("Monitor") + Word(alphanums),
                      CaselessLiteral("Faults"),
                      CaselessLiteral("Elements"),
                      CaselessLiteral("Buses"),
                      CaselessLiteral("Panel"),
                      CaselessLiteral("Meter"),
                      CaselessLiteral("Generators"),
                      CaselessLiteral("Losses"),
#                      CaselessLiteral("Powers") + [MVA|kVA*] [Seq* | Elements],
#                      CaselessLiteral("Voltages") + [LL |LN*] [Seq* | Nodes | Elements],
                      CaselessLiteral("Zone"),
                      CaselessLiteral("AutoAdded"),
                      CaselessLiteral("Taps"),
                      CaselessLiteral("Overloads"),
                      CaselessLiteral("Unserved"),
                      CaselessLiteral("EVentlog"),
                      CaselessLiteral("Variables"),
                      CaselessLiteral("Isolated"),
                      CaselessLiteral("Ratings"),
                      CaselessLiteral("Loops"),
                      CaselessLiteral("Yprim"),
                      CaselessLiteral("Y"),
#                      CaselessLiteral("BusFlow") + arg + [MVA|kVA*] [Seq* | Elements]
        ])

        return quantity


    def exportQuantityConstruct(self):
        arg = Word(alphanums)

        export_quantity = Or([CaselessKeyword("Voltages"),
                             CaselessKeyword("SeqVoltages"),
                             CaselessKeyword("Currents"),
                             CaselessKeyword("Overloads"),
                             CaselessKeyword("SeqCurrents"),
                             CaselessKeyword("Powers"),
                             CaselessKeyword("Faultstudy"),
                             CaselessKeyword("Loads"),
                             CaselessKeyword("Monitors") + arg,
                             CaselessKeyword("Voltages"),
                             CaselessKeyword("Meters") + arg,
                             CaselessKeyword("Generators") + arg,
                             CaselessKeyword("YPrims") + arg,
                             CaselessKeyword("Y") + arg,
                             CaselessKeyword("SeqZ") + arg])

        return export_quantity


    def commandConstruct(self):
        arg = Word(alphanums)

        comment_line = Literal("\\") + restOfLine
        inline_comment = Literal("!") + restOfLine

        # Object parameter
        clsname = oneOf(" ".join(CLSMAP.keys()), caseless=True)
        element_name = arg
        obj = CaselessKeyword("Object").suppress()
        clsname_prefix = Group(Optional(clsname + dot) + element_name)
        obj_param = (obj + equals + clsname_prefix) | clsname_prefix

        # Parameters
        key = arg.setResultsName("key")
        value = MatchFirst([integer, real,
                            #array,
                            dblQuotedString,
                            Word(alphas, alphanums+"_")
                            ])
        value = Word(alphanums, alphanums+"_"+".")

        positional = Group(value)# + comma
        named = Group(key + equals.suppress() + value)# + Optional(comma)
        params = Group(ZeroOrMore(named | positional)).setResultsName("params")

        # Commands
        about = CaselessKeyword("About")
        allocate_loads = CaselessKeyword("AllocateLoads")
        build_y = CaselessKeyword("BuildY")
        calc_volt_bases = CaselessKeyword("CalcVoltageBases")
        cd = CaselessKeyword("CD") + arg.setResultsName("dir_name")
        ckt_losses = CaselessKeyword("CktLosses")
        clear = CaselessKeyword("Clear")
        close = CaselessKeyword("Close") + arg + arg + arg
        close_di = CaselessKeyword("CloseDI")
#        compare_cases = [Case1=]casename [case2=]casename [register=](register number) [meter=]
#        {Totals* | SystemMeter | metername}
        compile = CaselessKeyword("Compile") + arg.setResultsName("file_name")
        redirect = CaselessKeyword("Redirect") + arg.setResultsName("file_name")
        currents = CaselessKeyword("Currents")

        # [case=]casename [year=]yr [registers=](reg1, reg2,...) [peak=]y/n [meter=]metername
        di_plot = CaselessKeyword("DI_plot")
        disable = CaselessKeyword("Disable") + arg.setResultsName("obj")
        doscmd = CaselessKeyword("DOScmd")
        dump = CaselessKeyword("Dump") + arg + Optional(Literal("debug"))
        edit = CaselessKeyword("Edit") + arg + arg
        enable = CaselessKeyword("Enable") + arg.setResultsName("obj")
        estimate = CaselessKeyword("Estimate")

        export_quantity = self.exportQuantityConstruct()
        export = CaselessKeyword("Export") + export_quantity + \
            arg.setResultsName("filename_or_switch")
        fileedit = CaselessKeyword("Fileedit") + arg
        get = CaselessKeyword("Get") + OneOrMore(arg)
        help = CaselessKeyword("Help")
        init = CaselessKeyword("Init")
        interpolate = CaselessKeyword("Interpolate") + Word("All")
        losses = CaselessKeyword("Losses")
        more = Or([CaselessKeyword("More"), CaselessKeyword("M"), tilde])
        new = CaselessKeyword("New") + obj_param + params
#        obj_prop_name + equals + value
        opencmd = CaselessKeyword("Open") + arg + arg + arg
        phase_losses = CaselessKeyword("PhaseLosses")
        plot = CaselessKeyword("Plot") + arg
        powers = CaselessKeyword("Powers")
#        reconductor = CaselessKeyword("Reconductor") +
        reset = CaselessKeyword("Reset") + \
            Or(CaselessLiteral("Meters"), CaselessLiteral("Monitors"))
        rotate = CaselessKeyword("Rotate") + arg.setResultsName("angle")
        seq_currents = CaselessKeyword("SeqCurrents")
        seq_powers = CaselessKeyword("SeqPowers")
        seq_voltages = CaselessKeyword("SeqVoltages")
        set_ = CaselessKeyword("Set")# + named + options
        setkv_base = CaselessKeyword("SetkVBase")

        quantity = self.quantityConstruct()
        show = CaselessKeyword("Show") + quantity

        solve = CaselessKeyword("Solve")
        totals = CaselessKeyword("Totals")
        varnames = CaselessLiteral("Varnames")
        var_values = CaselessKeyword("VarValues")
        vdiff = CaselessKeyword("Vdiff")
#        visualize = CaselessKeyword("Visualize") + [What=] {Currents* | Voltages | Powers} [element=]full_element_name(class.name)
        voltages = CaselessKeyword("Voltages")
#        yearly_curves = CaselessKeyword("YearlyCurves") + [cases=](case1, case2, ...) [registers=](reg1, reg2, ...) [meter=]{Totals* | SystemMeter | metername}
        y_sc = CaselessKeyword("Ysc")
        z_sc = CaselessKeyword("Zsc")
        z_sc10 = CaselessKeyword("Zsc10")
        z_sc_refresh = CaselessKeyword("ZscRefresh")

        commands = MatchFirst([comment_line, inline_comment,
            new, more, clear, redirect, compile, set_, solve, plot, help,
            about, edit, reset, calc_volt_bases, build_y, allocate_loads,
            cd, ckt_losses, close, close_di, #compare_cases,
            currents, di_plot, disable, doscmd, dump, enable,
            estimate, export, fileedit, get, init, interpolate, losses,
            opencmd, phase_losses, powers, #reconductor,
            rotate, seq_currents, seq_powers, seq_voltages,
            setkv_base,
            show, totals, varnames, var_values, vdiff,# visualize,
            voltages, #yearly_curves,
            y_sc, z_sc, z_sc10, z_sc_refresh])

        # Actions
        new.setParseAction(self.onNewCommand)
        more.setParseAction(self.onMoreCommand)
        obj_param.setParseAction(self.pushObjectParam)

        return commands


    def pushObjectParam(self, tokens):
        print "OBJ:", tokens[0].asList()
        params = tokens[0].asList()

        d = {}
        if len(params) == 1:
#            d["object"] = None
#            d["name"] = params[0]
            return [None, params[0]]
        elif len(params) == 2:
#            d["object"] = params[0]
#            d["name"] = params[1]
            return [params[0], params[1]]
        else:
            raise ParseException

#        return d


#    def pushCommandSyntax(self, tokens):
#        print "CMD:", tokens, tokens.keys()#
#
#        print tokens["params"]
##        tokens["args"] = args
##        tokens["kwargs"] = kwargs
#
#        d = {
#             "args": args, "kwargs": kwargs}
#
#        return d


#    def pushParameters(self, tokens):
#        print "PARAM:", tokens, tokens.keys()#, tokens["params"]
#
#        args = [p[0] for p in tokens["params"].asList() if len(p) == 1]
#        kwargs = dict([p for p in tokens["params"].asList() if len(p) == 2])
#
#        return [[args, kwargs]]
#

    def onNewCommand(self, tokens):
        print "NEW:", tokens[3].asList()

        d = {"command": tokens[0],
             "object": tokens[1],
             "name": tokens[2],
             "args": [p[0] for p in tokens[3].asList() if len(p) == 1],
             "kwargs": dict([p for p in tokens[3].asList() if len(p) == 2])
             }

        return d


    def onMoreCommand(self, tokens):
        pass

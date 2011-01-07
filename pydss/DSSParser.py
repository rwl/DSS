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

from pydss.util import \
    integer, boolean, real, tilde, lbrack, rbrack, equals, dot, comma

from pyparsing import \
    Literal, Word, ZeroOrMore, Optional, Or, OneOrMore, delimitedList, \
    CaselessLiteral, Combine, restOfLine, quotedString, CaselessKeyword, \
    oneOf, ParseException, alphas, Keyword

logger = logging.getLogger(__name__)


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

        arg = Word(alphas)

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

        export = CaselessKeyword("Export") + export_quantity + \
            arg.setResultsName("filename_or_switch")
        fileedit = CaselessKeyword("Fileedit") + arg
        get = CaselessKeyword("Get") + OneOrMore(arg)
        help = CaselessKeyword("Help")
        init = CaselessKeyword("Init")
        interpolate = CaselessKeyword("Interpolate") + Word("All")
        losses = CaselessKeyword("Losses")
        more = Or([CaselessKeyword("More"), CaselessKeyword("M"), tilde])
        more.setParseAction(self._pushMoreCommand)
        new = CaselessKeyword("New") + arg + arg
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
#        set = CaselessKeyword("Set") + named + options
        setkv_base = CaselessKeyword("SetkVBase")

        quantity = Or([CaselessLiteral("Currents"),
                      CaselessLiteral("Monitor") + arg,
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

        obj = CaselessKeyword("Object")

        clear = CaselessKeyword("Clear")


#        cmd_verb = oneOf([clear, more])


        command = arg#cmd_keyword

        array_str_delim = Literal("[]{}()\"'").suppress()
        row_delim = Literal("|")
        value_delim = comma | Literal(" ")
        cls_obj_bus_node_delim = dot
        key_val_sep = equals
        continuation = tilde
        comment_line = Literal("\\") + restOfLine
        inline_comment = Literal("!") + restOfLine



        array = array_str_delim + delimitedList(real) + array_str_delim
        matrix = array_str_delim + delimitedList(
            delimitedList(real), delim="|") + array_str_delim
        symmetrical_matrix = matrix

        name = arg
        key = arg
        value = Or(array )
        positional = value
        named = key + equals.suppress() + value
#        default_order = OneOrMore(positional)
        param = OneOrMore(Or(positional, named))

        clsname = arg
        element_name = arg

        clsname_prefix = Optional(clsname + dot) + element_name
        obj_param = Or(value, Combine(obj + equals + value))



        obj_prop_name = clsname + dot + element_name + dot + key
        prop_query = Keyword("?") + obj_prop_name


        command_syntax = command + OneOrMore(param)
        command_syntax.setParseAction(self._pushCommandSyntax)

        sequence = ZeroOrMore(command_syntax)

        return sequence


    def _pushCommandSyntax(self, tokens):
        tokens["title"]

    def _pushMoreCommand(self, tokens):
        tokens["command"]

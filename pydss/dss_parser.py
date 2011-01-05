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

from pydss.parsing_util import \
    integer, boolean, real, tilde, lbrack, rbrack, equals, dot, comma

from pyparsing import \
    Literal, Word, ZeroOrMore, Optional, OneOrMore, delimitedList, \
    alphas, Combine, restOfLine, quotedString, CaselessKeyword, oneOf

logger = logging.getLogger(__name__)


class DSSParser(object):

    active_obj = None
    prev_cls = None

    def parse_dss(self, filename):
        logger.info("Parsing DSS file [%s]." % os.path.basename(filename))

        dssparser = self.dss_definition()

        tokens = dssparser.parseFile(filename)

        if len(tokens) == 1:
            return tokens[0]
        else:
            return [t for t in tokens]


    def dss_definition(self):
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

        about = CaselessKeyword("About")
        allocate_loads = CaselessKeyword("AllocateLoads")
        build_y = CaselessKeyword("BuildY")
        calc_volt_bases = CaselessKeyword("CalcVoltageBases")
        cd = CaselessKeyword("CD") + Word.setResultsName("dir_name")
        ckt_losses = CaselessKeyword("CktLosses")
        clear = CaselessKeyword("Clear")
        close = CaselessKeyword("Close") + Word + Word + Word
        close_di = CaselessKeyword("CloseDI")
#        compare_cases = [Case1=]casename [case2=]casename [register=](register number) [meter=]
#        {Totals* | SystemMeter | metername}
        compile = CaselessKeyword("Compile") + Word.setResultsName("file_name")
        redirect = CaselessKeyword("Redirect") + Word.setResultsName("file_name")
        currents = CaselessKeyword("Currents")

        # [case=]casename [year=]yr [registers=](reg1, reg2,...) [peak=]y/n [meter=]metername
        di_plot = CaselessKeyword("DI_plot")
        disable = CaselessKeyword("Disable") + Word.setResultsName("obj")
        doscmd = CaselessKeyword("DOScmd")
        dump = CaselessKeyword("Dump") + Word + Optional(Literal("debug"))

        obj = CaselessKeyword("Object")

        clear = CaselessKeyword("Clear")
        more = tilde + restOfLine
        more.setParseAction(self._push_command)

        cmd_verb = oneOf(clear, more)


        command = cmd_keyword

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

        name =
        key =
        value = Or(array )
        positional = value
        named = Combine(key + equals.suppress() + value)
#        default_order = OneOrMore(positional)
        param = OneOrMore(Or(positional, named))

        clsname_prefix = Optional(clsname + dot) + element_name
        obj_param = Or(value, Combine(obj + equals + value))



        obj_prop_name = clsname + dot + element_name + dot + key
        prop_query = Keyword("?") + obj_prop_name


        command_syntax = command + OneOrMore(param)
        command_syntax.setParseAction(self._push_command_syntax)

        sequence = ZeroOrMore(command_syntax)

        return sequence


    def _push_command_syntax(self, tokens):
        tokens["title"]

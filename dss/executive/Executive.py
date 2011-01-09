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

from dss.executive.ExecCommands import ExecCommands
from dss.executive.ExecOptions import ExecOptions
from dss.DSSParser import DSSParser

class ExecParser(DSSParser):

    def __init__(self, executive):
        super(ExecParser, self).__init__()
        self.executive = executive

    def onNewCommand(self, toks):
        toks = super(ExecParser, self).onNewCommand(toks)

        print "TOKS:", toks

        self.executive.commands.new(toks["object"], toks["name"],
                                    toks["args"], toks["kwargs"])


    def onMoreCommand(self, tokens):
        pass


class Executive(object):

    def __init__(self, maxCircuits=1):
        """Initialises a new 'Executive' instance.
        """
        self._command = None

        self.maxCircuits = maxCircuits

        self.commands = ExecCommands(self)

        self.options = ExecOptions(self)

        self.parser = ExecParser(self)

        self.activeCircuit = None

        self.circuits = []

    def _getCommand(self):
        return self._command

    def _setCommand(self, cmd):
        self._command = cmd
        self.parser.parseString(cmd)

    command = property(_getCommand, _setCommand)


    def addCircuit(self, new):
        if len(self.circuits) > self.maxCircuits:
            raise ValueError

        self.circuits.append(new)
        self.activeCircuit = new

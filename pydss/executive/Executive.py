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

from pydss.executive.ExecCommands import ExecCommands
from pydss.executive.ExecOptions import ExecOptions
from pydss.DSSParser import DSSParser

class ExecParser(DSSParser):

    def __init__(self, executive):
        super(ExecParser, self).__init__()
        self.executive = executive

    def onNew(self, tokens):
        self.executive.commands.new(tokens)


class Executive(object):

    def __init__(self, maxCircuits=0):
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
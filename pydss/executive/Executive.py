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

class Executive(object):

    def __init__(self, command='', maxCircuits=0, execCommands=None,
            execOptions=None, activeCircuit=None, circuits=None):
        """Initialises a new 'Executive' instance.
        """
        self.command = command

        self.maxCircuits = maxCircuits

        self.execCommands = execCommands

        self.execOptions = execOptions

        self.activeCircuit = activeCircuit

        self.circuits = [] if circuits is None else circuits

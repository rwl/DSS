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

from dss.DSSParser import CLSMAP

from dss.common.Circuit import Circuit


class ExecCommands(object):
    """Defines commands for the executive.
    """
    def __init__(self, executive):
        self.executive = executive


    def new(self, object, name, *args, **kwargs):
        if not object:
            object = self.executive.activeElement
        else:
            self.executive.activeElement = object

        klass = CLSMAP[object.lower()]
        new = klass(name, *args, **kwargs)

        if isinstance(new, Circuit):
            self.executive.addCircuit(new)
        else:
            self.executive.activeCircuit.add(new)


    def edit(self):
        pass
    def select(self, elementName, terminalNumber):
        pass
    def show(self, option):
        pass
    def enable(self):
        pass
    def plot(self):
        pass
    def compile(self, fileName):
        pass
    def dump(self, cls, obj, debug):
        pass
    def close(self, element, terminal, conductor):
        pass
    def help(self):
        pass
    def what(self, element, property):
        pass
    def panel(self):
        pass
    def clear(self):
        pass
    def calcVoltageBases(self):
        pass
    def buildY(self):
        pass
    def initialise(self):
        pass
    def fileEdit(self, fileName):
        pass
    def currents(self):
        pass
    def seqVoltages(self):
        pass
    def seqPower(self):
        pass
    def phaseLosses(self):
        pass
    def allocateLoads(self):
        pass
    def totals(self):
        pass
    def classes(self):
        pass
    def Zsc(self):
        pass
    def ZscRefresh(self):
        pass
    def puVoltages(self):
        pass
    def varNames(self):
        pass
    def makeBusList(self):
        pass
    def reduce(self, type):
        pass
    def alignFile(self, fileName):
        pass
    def rotate(self):
        pass
    def summary(self):
        pass
    def diPlot(self):
        pass
    def yearlyCurves(self):
        pass
    def closeDI(self):
        pass

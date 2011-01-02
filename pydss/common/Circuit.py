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


class Circuit(object):
    """Defines a container of circuit elements.
    """

    def __init__(self, name='', numNodes=0, generatorDispatchReference=0.0, genMultiplier=0.0, solved=False, busNameRedefined=False, control_busNameRedefined=False, loadMultiplier=0.0, defaultGrowthFactor=0.0, defaultHourMult=0.0, priceSignal=0.0, solution=None, controlQueue=None, busList=None, faults=None, voltageSources=None, currentSources=None, sensors=None, monitors=None, energyMeters=None, generators=None, transformers=None, capControls=None, regControls=None, lines=None, loads=None, shuntCapacitors=None, feeder=None):
        """Initialises a new 'Circuit' instance.
        """

        self.name = name


        self.numNodes = numNodes


        self.generatorDispatchReference = generatorDispatchReference

        #: Global multiplier for every generator.
        self.genMultiplier = genMultiplier


        self.solved = solved


        self.busNameRedefined = busNameRedefined

        #: Lets control devices know the bus list has changed.
        self.control_busNameRedefined = control_busNameRedefined

        #: Global multiplier for every load.
        self.loadMultiplier = loadMultiplier


        self.defaultGrowthFactor = defaultGrowthFactor


        self.defaultHourMult = defaultHourMult


        self.priceSignal = priceSignal

        self._solution = None
        self.solution = solution

        self.controlQueue = controlQueue

        self._busList = []
        self.busList = [] if busList is None else busList

        self.faults = [] if faults is None else faults

        self.voltageSources = [] if voltageSources is None else voltageSources

        self.currentSources = [] if currentSources is None else currentSources

        self.sensors = [] if sensors is None else sensors

        self.monitors = [] if monitors is None else monitors

        self.energyMeters = [] if energyMeters is None else energyMeters

        self._generators = []
        self.generators = [] if generators is None else generators

        self.transformers = [] if transformers is None else transformers

        self.capControls = [] if capControls is None else capControls

        self.regControls = [] if regControls is None else regControls

        self.lines = [] if lines is None else lines

        self.loads = [] if loads is None else loads

        self.shuntCapacitors = [] if shuntCapacitors is None else shuntCapacitors

        self.feeder = [] if feeder is None else feeder


    def getsolution(self):
        
        return self._solution

    def setsolution(self, value):
        if self._solution is not None:
            self._solution._circuit = None

        self._solution = value
        if self._solution is not None:
            self._solution.circuit = None
            self._solution._circuit = self

    solution = property(getsolution, setsolution)

    controlQueue = None

    def getbusList(self):
        
        return self._busList

    def setbusList(self, value):
        for x in self._busList:
            x.circuit = None
        for y in value:
            y._circuit = self
        self._busList = value

    busList = property(getbusList, setbusList)

    def addbusList(self, *busList):
        for obj in busList:
            obj.circuit = self

    def removebusList(self, *busList):
        for obj in busList:
            obj.circuit = None

    def add_faults(self, *faults):
        for obj in faults:
            self.faults.append(obj)

    def remove_faults(self, *faults):
        for obj in faults:
            self.faults.remove(obj)

    def add_voltageSources(self, *voltageSources):
        for obj in voltageSources:
            self.voltageSources.append(obj)

    def remove_voltageSources(self, *voltageSources):
        for obj in voltageSources:
            self.voltageSources.remove(obj)

    def add_currentSources(self, *currentSources):
        for obj in currentSources:
            self.currentSources.append(obj)

    def remove_currentSources(self, *currentSources):
        for obj in currentSources:
            self.currentSources.remove(obj)

    def add_sensors(self, *sensors):
        for obj in sensors:
            self.sensors.append(obj)

    def remove_sensors(self, *sensors):
        for obj in sensors:
            self.sensors.remove(obj)

    def add_monitors(self, *monitors):
        for obj in monitors:
            self.monitors.append(obj)

    def remove_monitors(self, *monitors):
        for obj in monitors:
            self.monitors.remove(obj)

    def add_energyMeters(self, *energyMeters):
        for obj in energyMeters:
            self.energyMeters.append(obj)

    def remove_energyMeters(self, *energyMeters):
        for obj in energyMeters:
            self.energyMeters.remove(obj)

    def getgenerators(self):
        
        return self._generators

    def setgenerators(self, value):
        for x in self._generators:
            x.circuit = None
        for y in value:
            y._circuit = self
        self._generators = value

    generators = property(getgenerators, setgenerators)

    def addgenerators(self, *generators):
        for obj in generators:
            obj.circuit = self

    def removegenerators(self, *generators):
        for obj in generators:
            obj.circuit = None

    def add_transformers(self, *transformers):
        for obj in transformers:
            self.transformers.append(obj)

    def remove_transformers(self, *transformers):
        for obj in transformers:
            self.transformers.remove(obj)

    def add_capControls(self, *capControls):
        for obj in capControls:
            self.capControls.append(obj)

    def remove_capControls(self, *capControls):
        for obj in capControls:
            self.capControls.remove(obj)

    def add_regControls(self, *regControls):
        for obj in regControls:
            self.regControls.append(obj)

    def remove_regControls(self, *regControls):
        for obj in regControls:
            self.regControls.remove(obj)

    def add_lines(self, *lines):
        for obj in lines:
            self.lines.append(obj)

    def remove_lines(self, *lines):
        for obj in lines:
            self.lines.remove(obj)

    def add_loads(self, *loads):
        for obj in loads:
            self.loads.append(obj)

    def remove_loads(self, *loads):
        for obj in loads:
            self.loads.remove(obj)

    def add_shuntCapacitors(self, *shuntCapacitors):
        for obj in shuntCapacitors:
            self.shuntCapacitors.append(obj)

    def remove_shuntCapacitors(self, *shuntCapacitors):
        for obj in shuntCapacitors:
            self.shuntCapacitors.remove(obj)

    def add_feeder(self, *feeder):
        for obj in feeder:
            self.feeder.append(obj)

    def remove_feeder(self, *feeder):
        for obj in feeder:
            self.feeder.remove(obj)

    def reProcessBusDefs(self):
        pass
    def reCalcInvalidYPrims(self):
        pass
    def getCircuitElements(self):
        pass
    def getPCElements(self):
        pass
    def getMeterElements(self):
        pass

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

    def __init__(self, name, generatorDispatchReference=0.0,
            genMultiplier=0.0, solved=False, busNameRedefined=False,
            control_busNameRedefined=False, loadMultiplier=0.0,
            defaultGrowthFactor=0.0, defaultHourMult=0.0, priceSignal=0.0,
            solution=None, controlQueue=None, busList=None, faults=None,
            voltageSources=None, currentSources=None, sensors=None,
            monitors=None, energyMeters=None, generators=None,
            transformers=None, capControls=None, regControls=None, lines=None,
            loads=None, shuntCapacitors=None, feeder=None):
        """Initialises a new 'Circuit' instance.
        """
        self.name = name

        self.numNodes = 0

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


    def add(self, element, uid=None):
        """ Adds an elements to the circuit model.
        """
#        if uid is None:
#            if element.m_rid == "":
#                uid = uuid.uuid4().hex
#            else:
#                uid = element.mrid
#
#        self.elements[uid] = element
        element.model = self


    def remove(self, uid):
        """Removes the element corresponding the the specified UID.
        """
        del self.elements[uid]


    def topologicalAnalysis(self):
        """Returns a list of TopologicalNode objects that contain connectivity
        nodes that, in the current state, connect all non-primary elements
        between primary elements (Transformers, ACLineSegments etc.).
        """
#        nodes = [e for e in self.elements.values() \
#                 if isinstance(e, ConnectivityNode)]
#
#        tn = TopologicalNode()
#
#        for element in delivery:
#            t1 = element.terminals[0]
#            t2 = element.terminals[1]
#
#            cn1 = t1.connectivity_node
#
#            if cn1 in nodes:
#                for terminal in cn1.terminals:
#                    c_eq = terminal.conducting_equipment
#
#                    if type(c_eu) in primary_types:
#                        nodes.remove(cn1)
#                        tn = TopologicalNode() # Start a new node.
#                    else:
#                        tn.connectivity_nodes.append(cn1)
#                        nodes.remove(cn1)
#
#            cn2 = t2.connectivity_node
#
#        for element in conversion:
#            pass


    def loadElements(self, file_name):
        """Loads model elements from a CIM RDF/XML file and replaces the
        existing elements.
        """
#        reader = CIMReader()
#        uri_element_map = reader(file_name)
#        self.elements = uri_element_map.values()


    def addElements(self, file_name):
        """Loads model elements from a CIM RDF/XML file and adds them to the
        existing list of elements.
        """
#        element_map = read_cim(file_name)
#        self.elements.update(element_map)

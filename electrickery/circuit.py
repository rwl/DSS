# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Defines a container of CIM elements that can simulate power flow.
"""

import uuid
from cim import Model
from cim.topology import ConnectivityNode, TopologicalNode
from reader import read_cim

class Circuit(Model):
    """ Simulates power flow in a CIM.
    """
    v_sources = []

    frequency = 60.0

    def add(self, element, uid=None):
        """ Adds an elements to the circuit model.
        """
        if uid is None:
            if element.m_rid == "":
                uid = uuid.uuid4().hex
            else:
                uid = element.mrid

        self.elements[uid] = element
        element.model = self


    def remove(self, uid):
        """ Removes the element corresponding the the specified UID.
        """
        del self.elements[uid]


    def peform_topologial_analysis(self):
        """ Returns a list of TopologicalNode objects that contain connectivity
            nodes that, in the current state, connect all non-primary elements
            between primary elements (Transformers, ACLineSegments etc.).
        """
        nodes = [e for e in self.elements.values() \
                 if isinstance(e, ConnectivityNode)]

        tn = TopologicalNode()

        for element in delivery:
            t1 = element.terminals[0]
            t2 = element.terminals[1]

            cn1 = t1.connectivity_node

            if cn1 in nodes:
                for terminal in cn1.terminals:
                    c_eq = terminal.conducting_equipment

                    if type(c_eu) in primary_types:
                        nodes.remove(cn1)
                        tn = TopologicalNode() # Start a new node.
                    else:
                        tn.connectivity_nodes.append(cn1)
                        nodes.remove(cn1)

            cn2 = t2.connectivity_node

        for element in conversion:
            pass


    def load_elements(self, file_name):
        """ Loads model elements from a CIM RDF/XML file and replaces the
            existing elements.
        """
        self.elements = read_cim(file_name)


    def add_elements(self, file_name):
        """ Loads model elements from a CIM RDF/XML file and adds them to the
            existing list of elements.
        """
        element_map = read_cim(file_name)
        self.elements.update(element_map)
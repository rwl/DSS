# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Defines a parser for CIM RDF/XML files that creates a representation in
    Graphviz's Dot language.
"""

import sys
import logging
import rdfxml

from cim14.iec61970.core import Terminal, ConductingEquipment
from cim14.iec61970.topology import ConnectivityNode

logging.basicConfig(stream=sys.stdout, level=logging.ERROR,
    format="%(levelname)s: %(message)s")
logger = logging.getLogger(__name__)

def cim2dot(model):
    """ Returns a Dot language representation of the model.
    """
    elements = model.elements.values()
    terminals = [e for e in elements if isinstance(e, Terminal)]
    nodes     = [e for e in elements if isinstance(e, ConnectivityNode)]
    equipment = [e for e in elements if isinstance(e, ConductingEquipment)]

    indent = "        "
    conn   = "--"

    s = "graph G {\n"

    for node in nodes:
        s += "%s [label=%s, shape='point'];\n" % (node.m_rid, node.name)

    for equip in equipment:
        s += "%s [label=%s];\n" % (equip.m_rid, equip.name)

    for terminal in terminals:
        equip = terminal.conducting_equipment
        node  = terminal.connectivity_node

        if (equip is not None) and (node is not None):
            s += "%s %s %s;\n" % (equip.m_rid, conn, node.m_rid)
        else:
            logger.error("Unconnected terminal [%s] found." % terminal)

    s += "}\n"

    return s



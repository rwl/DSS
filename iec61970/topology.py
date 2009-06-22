# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class TopologicalNode(IdentifiedObject):
    """ A set of connectivity nodes that, in the current network state, are connected together through any type of closed switches, including  jumpers. Topological nodes can change as the current network state changes (i.e., switches, breakers, etc. change state).
    """
    # True if node energized 
    energized = False

    # Net injection active power 
    net_injection_p = 0.0

    # Net injection reactive power 
    net_injection_q = 0.0

    # Phase angle of node 
    phase_angle = 0.0

    # The observability status of the node. 
    observability_flag = False

    # Voltage of node 
    voltage = 0.0

    # True if node is load carrying 
    load_carrying = False

    reporting_group = None

    angle_ref_topological_island = None

    connectivity_node_container = None

    # A topological node belongs to a topological island
    topological_island = None

    # Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
    connectivity_nodes = []

    terminal = []

    control_area = None

    # <<< topological_node
    # @generated
    def __init__(self, energized=False, net_injection_p=0.0, net_injection_q=0.0, phase_angle=0.0, observability_flag=False, voltage=0.0, load_carrying=False, reporting_group=None, angle_ref_topological_island=None, connectivity_node_container=None, topological_island=None, connectivity_nodes=[], terminal=[], control_area=None, **kw_args):
        """ Initialises a new 'TopologicalNode' instance.
        """
        self.energized = energized
        self.net_injection_p = net_injection_p
        self.net_injection_q = net_injection_q
        self.phase_angle = phase_angle
        self.observability_flag = observability_flag
        self.voltage = voltage
        self.load_carrying = load_carrying
        self.reporting_group = reporting_group
        self.angle_ref_topological_island = angle_ref_topological_island
        self.connectivity_node_container = connectivity_node_container
        self.topological_island = topological_island
        self.connectivity_nodes = connectivity_nodes
        self.terminal = terminal
        self.control_area = control_area

        super(TopologicalNode, self).__init__(**kw_args)
    # >>> topological_node


class TopologicalIsland(IdentifiedObject):
    """ An electrically connected subset of the network. Topological islands can change as the current network state changes (i.e., disconnect switches, breakers, etc. change state).
    """
    angle_ref_topological_node = None

    # A topological node belongs to a topological island
    topological_nodes = []

    # <<< topological_island
    # @generated
    def __init__(self, angle_ref_topological_node=None, topological_nodes=[], **kw_args):
        """ Initialises a new 'TopologicalIsland' instance.
        """
        self.angle_ref_topological_node = angle_ref_topological_node
        self.topological_nodes = topological_nodes

        super(TopologicalIsland, self).__init__(**kw_args)
    # >>> topological_island


class ConnectivityNode(IdentifiedObject):
    """ Connectivity nodes are points where terminals of conducting equipment are connected together with zero impedance.
    """
    member_of_equipment_container = None

    bus_name_marker = None

    # Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
    terminals = []

    # Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
    topological_node = None

    # <<< connectivity_node
    # @generated
    def __init__(self, member_of_equipment_container=None, bus_name_marker=None, terminals=[], topological_node=None, **kw_args):
        """ Initialises a new 'ConnectivityNode' instance.
        """
        self.member_of_equipment_container = member_of_equipment_container
        self.bus_name_marker = bus_name_marker
        self.terminals = terminals
        self.topological_node = topological_node

        super(ConnectivityNode, self).__init__(**kw_args)
    # >>> connectivity_node


class BusNameMarker(IdentifiedObject):
    """ Used to apply user standard names to topology buses. Typically used for 'bus/branch' case generation. Associated with one or more ConnectivityNodes that are normally a part of the bus name.    The associated ConnectivityNodes are to be connected by non-retained switches. For a ring bus station configuration, all busbar connectivity nodes in the ring are typically associated.   For a breaker and a half scheme, both busbars would be associated.  For a ring bus, all busbars would be associated.  For a 'straight' busbar configuration, only the main connectivity node at the busbar would be associated.
    """
    control_area = None

    connectivity_node = []

    reporting_group = None

    # <<< bus_name_marker
    # @generated
    def __init__(self, control_area=None, connectivity_node=[], reporting_group=None, **kw_args):
        """ Initialises a new 'BusNameMarker' instance.
        """
        self.control_area = control_area
        self.connectivity_node = connectivity_node
        self.reporting_group = reporting_group

        super(BusNameMarker, self).__init__(**kw_args)
    # >>> bus_name_marker


# <<< topology
# @generated
# >>> topology

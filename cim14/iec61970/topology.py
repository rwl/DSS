# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class BusNameMarker(IdentifiedObject):
    """ Used to apply user standard names to topology buses. Typically used for 'bus/branch' case generation. Associated with one or more ConnectivityNodes that are normally a part of the bus name.    The associated ConnectivityNodes are to be connected by non-retained switches. For a ring bus station configuration, all busbar connectivity nodes in the ring are typically associated.   For a breaker and a half scheme, both busbars would be associated.  For a ring bus, all busbars would be associated.  For a 'straight' busbar configuration, only the main connectivity node at the busbar would be associated.
    """
    # The control area into which the BusNameMarker is included.
    control_area = None

    # The reporting group to which this BusNameMarker belongs.
    reporting_group = None

    # The list of nodes which have the same bus name in the normal  topology.  Note that this list of ConnectivityNodes should be connected by objects derived from Switch that are normally closed.
    connectivity_node = []

    # <<< bus_name_marker
    # @generated
    def __init__(self, control_area=None, reporting_group=None, connectivity_node=[], **kw_args):
        """ Initialises a new 'BusNameMarker' instance.
        """
        self.control_area = control_area
        self.reporting_group = reporting_group
        self.connectivity_node = connectivity_node

        super(BusNameMarker, self).__init__(**kw_args)
    # >>> bus_name_marker


class TopologicalNode(IdentifiedObject):
    """ A set of connectivity nodes that, in the current network state, are connected together through any type of closed switches, including  jumpers. Topological nodes can change as the current network state changes (i.e., switches, breakers, etc. change state).
    """
    # The observability status of the node. 
    observability_flag = False

    # Ratio of positive sequence reactance per postive sequence resistance. 
    x_per_r = 0.0

    # The ratio of zero sequence reactance per positive sequence reactance. 
    x0_per_x = 0.0

    # True if node is load carrying 
    load_carrying = False

    # Net injection active power 
    net_injection_p = ''

    # Phase angle of node 
    phase_angle = ''

    # Voltage of node 
    voltage = ''

    # The ratio of zero sequence resistance to positive sequence resistance. 
    r0_per_r = 0.0

    # The short circuit apparent power drawn at this node when faulted. 
    s_short_circuit = ''

    # True if node energized 
    energized = False

    # Net injection reactive power 
    net_injection_q = ''

    # The island for which the node is an angle reference.   Normally there is one angle reference node for each island.
    angle_ref_topological_island = None

    # The connectivity node container to which the toplogical node belongs.
    connectivity_node_container = None

    # Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
    connectivity_nodes = []

    # The state voltage associated with the topological node.
    sv_voltage = None

    # The reporting group to which the topological node belongs.
    reporting_group = None

    # The injection state associated with the topological node.
    sv_injection = None

    # The control area into which the node is included.
    control_area = None

    # The terminals associated with the topological node.   This can be used as an alternative to the connectivity node path to terminal, thus making it unneccesary to model connedtivity nodes in some cases.   Note that the if connectivity nodes are in the model, this association would proably not be used.
    terminal = []

    # A topological node belongs to a topological island
    topological_island = None

    # <<< topological_node
    # @generated
    def __init__(self, observability_flag=False, x_per_r=0.0, x0_per_x=0.0, load_carrying=False, net_injection_p='', phase_angle='', voltage='', r0_per_r=0.0, s_short_circuit='', energized=False, net_injection_q='', angle_ref_topological_island=None, connectivity_node_container=None, connectivity_nodes=[], sv_voltage=None, reporting_group=None, sv_injection=None, control_area=None, terminal=[], topological_island=None, **kw_args):
        """ Initialises a new 'TopologicalNode' instance.
        """
        self.observability_flag = observability_flag
        self.x_per_r = x_per_r
        self.x0_per_x = x0_per_x
        self.load_carrying = load_carrying
        self.net_injection_p = net_injection_p
        self.phase_angle = phase_angle
        self.voltage = voltage
        self.r0_per_r = r0_per_r
        self.s_short_circuit = s_short_circuit
        self.energized = energized
        self.net_injection_q = net_injection_q
        self.angle_ref_topological_island = angle_ref_topological_island
        self.connectivity_node_container = connectivity_node_container
        self.connectivity_nodes = connectivity_nodes
        self.sv_voltage = sv_voltage
        self.reporting_group = reporting_group
        self.sv_injection = sv_injection
        self.control_area = control_area
        self.terminal = terminal
        self.topological_island = topological_island

        super(TopologicalNode, self).__init__(**kw_args)
    # >>> topological_node


class TopologicalIsland(IdentifiedObject):
    """ An electrically connected subset of the network. Topological islands can change as the current network state changes (i.e., disconnect switches, breakers, etc. change state).
    """
    # A topological node belongs to a topological island
    topological_nodes = []

    # The angle reference for the island.   Normally there is one TopologicalNode that is selected as the angle reference for each island.   Other reference schemes exist, so the association is optional.
    angle_ref_topological_node = None

    # <<< topological_island
    # @generated
    def __init__(self, topological_nodes=[], angle_ref_topological_node=None, **kw_args):
        """ Initialises a new 'TopologicalIsland' instance.
        """
        self.topological_nodes = topological_nodes
        self.angle_ref_topological_node = angle_ref_topological_node

        super(TopologicalIsland, self).__init__(**kw_args)
    # >>> topological_island


class ConnectivityNode(IdentifiedObject):
    """ Connectivity nodes are points where terminals of conducting equipment are connected together with zero impedance.
    """
    # Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
    topological_node = None

    # Container of this connectivity node.
    connectivity_node_container = None

    pnode = None

    # The associated name of the bus (TopologicalNode) containing the ConnectivityNode is derived by an algorithm that uses the bus name marker.
    bus_name_marker = None

    loss_penalty_factors = []

    # Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
    terminals = []

    node_constraint_terms = []

    # <<< connectivity_node
    # @generated
    def __init__(self, topological_node=None, connectivity_node_container=None, pnode=None, bus_name_marker=None, loss_penalty_factors=[], terminals=[], node_constraint_terms=[], **kw_args):
        """ Initialises a new 'ConnectivityNode' instance.
        """
        self.topological_node = topological_node
        self.connectivity_node_container = connectivity_node_container
        self.pnode = pnode
        self.bus_name_marker = bus_name_marker
        self.loss_penalty_factors = loss_penalty_factors
        self.terminals = terminals
        self.node_constraint_terms = node_constraint_terms

        super(ConnectivityNode, self).__init__(**kw_args)
    # >>> connectivity_node


# <<< topology
# @generated
# >>> topology

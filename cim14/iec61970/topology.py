# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Topology"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Topology"

class BusNameMarker(IdentifiedObject):
    """ Used to apply user standard names to topology buses. Typically used for 'bus/branch' case generation. Associated with one or more ConnectivityNodes that are normally a part of the bus name.    The associated ConnectivityNodes are to be connected by non-retained switches. For a ring bus station configuration, all busbar connectivity nodes in the ring are typically associated.   For a breaker and a half scheme, both busbars would be associated.  For a ring bus, all busbars would be associated.  For a 'straight' busbar configuration, only the main connectivity node at the busbar would be associated.
    """
    def get_control_area(self):
        """ The control area into which the BusNameMarker is included.
        """
        return self._control_area

    def set_control_area(self, value):
        if self._control_area is not None:
            filtered = [x for x in self.control_area.bus_name_marker if x != self]
            self._control_area._bus_name_marker = filtered
            
        self._control_area = value
        if self._control_area is not None:
            self._control_area._bus_name_marker.append(self)

    control_area = property(get_control_area, set_control_area)

    def get_reporting_group(self):
        """ The reporting group to which this BusNameMarker belongs.
        """
        return self._reporting_group

    def set_reporting_group(self, value):
        if self._reporting_group is not None:
            filtered = [x for x in self.reporting_group.bus_name_marker if x != self]
            self._reporting_group._bus_name_marker = filtered
            
        self._reporting_group = value
        if self._reporting_group is not None:
            self._reporting_group._bus_name_marker.append(self)

    reporting_group = property(get_reporting_group, set_reporting_group)

    def get_connectivity_node(self):
        """ The list of nodes which have the same bus name in the normal  topology.  Note that this list of ConnectivityNodes should be connected by objects derived from Switch that are normally closed.
        """
        return self._connectivity_node

    def set_connectivity_node(self, value):
        for x in self._connectivity_node:
            x._bus_name_marker = None
        for y in value:
            y._bus_name_marker = self
        self._connectivity_node = value
            
    connectivity_node = property(get_connectivity_node, set_connectivity_node)
    
    def add_connectivity_node(self, *connectivity_node):
        for obj in connectivity_node:
            obj._bus_name_marker = self
            self._connectivity_node.append(obj)
        
    def remove_connectivity_node(self, *connectivity_node):
        for obj in connectivity_node:
            obj._bus_name_marker = None
            self._connectivity_node.remove(obj)

    # <<< bus_name_marker
    # @generated
    def __init__(self, control_area=None, reporting_group=None, connectivity_node=[], **kw_args):
        """ Initialises a new 'BusNameMarker' instance.
        """
        self._control_area = None
        self.control_area = control_area
        self._reporting_group = None
        self.reporting_group = reporting_group
        self._connectivity_node = []
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

    def get_angle_ref_topological_island(self):
        """ The island for which the node is an angle reference.   Normally there is one angle reference node for each island.
        """
        return self._angle_ref_topological_island

    def set_angle_ref_topological_island(self, value):
        if self._angle_ref_topological_island is not None:
            self._angle_ref_topological_island._angle_ref_topological_node = None
            
        self._angle_ref_topological_island = value
        if self._angle_ref_topological_island is not None:
            self._angle_ref_topological_island._angle_ref_topological_node = self
            
    angle_ref_topological_island = property(get_angle_ref_topological_island, set_angle_ref_topological_island)

    def get_connectivity_node_container(self):
        """ The connectivity node container to which the toplogical node belongs.
        """
        return self._connectivity_node_container

    def set_connectivity_node_container(self, value):
        if self._connectivity_node_container is not None:
            filtered = [x for x in self.connectivity_node_container.topological_node if x != self]
            self._connectivity_node_container._topological_node = filtered
            
        self._connectivity_node_container = value
        if self._connectivity_node_container is not None:
            self._connectivity_node_container._topological_node.append(self)

    connectivity_node_container = property(get_connectivity_node_container, set_connectivity_node_container)

    def get_connectivity_nodes(self):
        """ Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
        """
        return self._connectivity_nodes

    def set_connectivity_nodes(self, value):
        for x in self._connectivity_nodes:
            x._topological_node = None
        for y in value:
            y._topological_node = self
        self._connectivity_nodes = value
            
    connectivity_nodes = property(get_connectivity_nodes, set_connectivity_nodes)
    
    def add_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            obj._topological_node = self
            self._connectivity_nodes.append(obj)
        
    def remove_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            obj._topological_node = None
            self._connectivity_nodes.remove(obj)

    def get_sv_voltage(self):
        """ The state voltage associated with the topological node.
        """
        return self._sv_voltage

    def set_sv_voltage(self, value):
        if self._sv_voltage is not None:
            self._sv_voltage._topological_node = None
            
        self._sv_voltage = value
        if self._sv_voltage is not None:
            self._sv_voltage._topological_node = self
            
    sv_voltage = property(get_sv_voltage, set_sv_voltage)

    def get_reporting_group(self):
        """ The reporting group to which the topological node belongs.
        """
        return self._reporting_group

    def set_reporting_group(self, value):
        if self._reporting_group is not None:
            filtered = [x for x in self.reporting_group.topological_node if x != self]
            self._reporting_group._topological_node = filtered
            
        self._reporting_group = value
        if self._reporting_group is not None:
            self._reporting_group._topological_node.append(self)

    reporting_group = property(get_reporting_group, set_reporting_group)

    def get_sv_injection(self):
        """ The injection state associated with the topological node.
        """
        return self._sv_injection

    def set_sv_injection(self, value):
        if self._sv_injection is not None:
            self._sv_injection._topological_node = None
            
        self._sv_injection = value
        if self._sv_injection is not None:
            self._sv_injection._topological_node = self
            
    sv_injection = property(get_sv_injection, set_sv_injection)

    def get_control_area(self):
        """ The control area into which the node is included.
        """
        return self._control_area

    def set_control_area(self, value):
        if self._control_area is not None:
            filtered = [x for x in self.control_area.topological_node if x != self]
            self._control_area._topological_node = filtered
            
        self._control_area = value
        if self._control_area is not None:
            self._control_area._topological_node.append(self)

    control_area = property(get_control_area, set_control_area)

    def get_terminal(self):
        """ The terminals associated with the topological node.   This can be used as an alternative to the connectivity node path to terminal, thus making it unneccesary to model connedtivity nodes in some cases.   Note that the if connectivity nodes are in the model, this association would proably not be used.
        """
        return self._terminal

    def set_terminal(self, value):
        for x in self._terminal:
            x._topological_node = None
        for y in value:
            y._topological_node = self
        self._terminal = value
            
    terminal = property(get_terminal, set_terminal)
    
    def add_terminal(self, *terminal):
        for obj in terminal:
            obj._topological_node = self
            self._terminal.append(obj)
        
    def remove_terminal(self, *terminal):
        for obj in terminal:
            obj._topological_node = None
            self._terminal.remove(obj)

    def get_topological_island(self):
        """ A topological node belongs to a topological island
        """
        return self._topological_island

    def set_topological_island(self, value):
        if self._topological_island is not None:
            filtered = [x for x in self.topological_island.topological_nodes if x != self]
            self._topological_island._topological_nodes = filtered
            
        self._topological_island = value
        if self._topological_island is not None:
            self._topological_island._topological_nodes.append(self)

    topological_island = property(get_topological_island, set_topological_island)

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
        self._angle_ref_topological_island = None
        self.angle_ref_topological_island = angle_ref_topological_island
        self._connectivity_node_container = None
        self.connectivity_node_container = connectivity_node_container
        self._connectivity_nodes = []
        self.connectivity_nodes = connectivity_nodes
        self._sv_voltage = None
        self.sv_voltage = sv_voltage
        self._reporting_group = None
        self.reporting_group = reporting_group
        self._sv_injection = None
        self.sv_injection = sv_injection
        self._control_area = None
        self.control_area = control_area
        self._terminal = []
        self.terminal = terminal
        self._topological_island = None
        self.topological_island = topological_island

        super(TopologicalNode, self).__init__(**kw_args)
    # >>> topological_node


class TopologicalIsland(IdentifiedObject):
    """ An electrically connected subset of the network. Topological islands can change as the current network state changes (i.e., disconnect switches, breakers, etc. change state).
    """
    def get_topological_nodes(self):
        """ A topological node belongs to a topological island
        """
        return self._topological_nodes

    def set_topological_nodes(self, value):
        for x in self._topological_nodes:
            x._topological_island = None
        for y in value:
            y._topological_island = self
        self._topological_nodes = value
            
    topological_nodes = property(get_topological_nodes, set_topological_nodes)
    
    def add_topological_nodes(self, *topological_nodes):
        for obj in topological_nodes:
            obj._topological_island = self
            self._topological_nodes.append(obj)
        
    def remove_topological_nodes(self, *topological_nodes):
        for obj in topological_nodes:
            obj._topological_island = None
            self._topological_nodes.remove(obj)

    def get_angle_ref_topological_node(self):
        """ The angle reference for the island.   Normally there is one TopologicalNode that is selected as the angle reference for each island.   Other reference schemes exist, so the association is optional.
        """
        return self._angle_ref_topological_node

    def set_angle_ref_topological_node(self, value):
        if self._angle_ref_topological_node is not None:
            self._angle_ref_topological_node._angle_ref_topological_island = None
            
        self._angle_ref_topological_node = value
        if self._angle_ref_topological_node is not None:
            self._angle_ref_topological_node._angle_ref_topological_island = self
            
    angle_ref_topological_node = property(get_angle_ref_topological_node, set_angle_ref_topological_node)

    # <<< topological_island
    # @generated
    def __init__(self, topological_nodes=[], angle_ref_topological_node=None, **kw_args):
        """ Initialises a new 'TopologicalIsland' instance.
        """
        self._topological_nodes = []
        self.topological_nodes = topological_nodes
        self._angle_ref_topological_node = None
        self.angle_ref_topological_node = angle_ref_topological_node

        super(TopologicalIsland, self).__init__(**kw_args)
    # >>> topological_island


class ConnectivityNode(IdentifiedObject):
    """ Connectivity nodes are points where terminals of conducting equipment are connected together with zero impedance.
    """
    def get_topological_node(self):
        """ Several ConnectivityNode(s) may combine together to form a single TopologicalNode, depending on the current state of the network.
        """
        return self._topological_node

    def set_topological_node(self, value):
        if self._topological_node is not None:
            filtered = [x for x in self.topological_node.connectivity_nodes if x != self]
            self._topological_node._connectivity_nodes = filtered
            
        self._topological_node = value
        if self._topological_node is not None:
            self._topological_node._connectivity_nodes.append(self)

    topological_node = property(get_topological_node, set_topological_node)

    def get_connectivity_node_container(self):
        """ Container of this connectivity node.
        """
        return self._connectivity_node_container

    def set_connectivity_node_container(self, value):
        if self._connectivity_node_container is not None:
            filtered = [x for x in self.connectivity_node_container.connectivity_nodes if x != self]
            self._connectivity_node_container._connectivity_nodes = filtered
            
        self._connectivity_node_container = value
        if self._connectivity_node_container is not None:
            self._connectivity_node_container._connectivity_nodes.append(self)

    connectivity_node_container = property(get_connectivity_node_container, set_connectivity_node_container)

    def get_pnode(self):
        """ 
        """
        return self._pnode

    def set_pnode(self, value):
        if self._pnode is not None:
            self._pnode._connectivity_node = None
            
        self._pnode = value
        if self._pnode is not None:
            self._pnode._connectivity_node = self
            
    pnode = property(get_pnode, set_pnode)

    def get_bus_name_marker(self):
        """ The associated name of the bus (TopologicalNode) containing the ConnectivityNode is derived by an algorithm that uses the bus name marker.
        """
        return self._bus_name_marker

    def set_bus_name_marker(self, value):
        if self._bus_name_marker is not None:
            filtered = [x for x in self.bus_name_marker.connectivity_node if x != self]
            self._bus_name_marker._connectivity_node = filtered
            
        self._bus_name_marker = value
        if self._bus_name_marker is not None:
            self._bus_name_marker._connectivity_node.append(self)

    bus_name_marker = property(get_bus_name_marker, set_bus_name_marker)

    loss_penalty_factors = []
    
    def add_loss_penalty_factors(self, *loss_penalty_factors):
        for obj in loss_penalty_factors:
	        self._loss_penalty_factors.append(obj)
        
    def remove_loss_penalty_factors(self, *loss_penalty_factors):
        for obj in loss_penalty_factors:
	        self._loss_penalty_factors.remove(obj)

    def get_terminals(self):
        """ Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
        """
        return self._terminals

    def set_terminals(self, value):
        for x in self._terminals:
            x._connectivity_node = None
        for y in value:
            y._connectivity_node = self
        self._terminals = value
            
    terminals = property(get_terminals, set_terminals)
    
    def add_terminals(self, *terminals):
        for obj in terminals:
            obj._connectivity_node = self
            self._terminals.append(obj)
        
    def remove_terminals(self, *terminals):
        for obj in terminals:
            obj._connectivity_node = None
            self._terminals.remove(obj)

    def get_node_constraint_terms(self):
        """ 
        """
        return self._node_constraint_terms

    def set_node_constraint_terms(self, value):
        for x in self._node_constraint_terms:
            x._connectivity_node = None
        for y in value:
            y._connectivity_node = self
        self._node_constraint_terms = value
            
    node_constraint_terms = property(get_node_constraint_terms, set_node_constraint_terms)
    
    def add_node_constraint_terms(self, *node_constraint_terms):
        for obj in node_constraint_terms:
            obj._connectivity_node = self
            self._node_constraint_terms.append(obj)
        
    def remove_node_constraint_terms(self, *node_constraint_terms):
        for obj in node_constraint_terms:
            obj._connectivity_node = None
            self._node_constraint_terms.remove(obj)

    # <<< connectivity_node
    # @generated
    def __init__(self, topological_node=None, connectivity_node_container=None, pnode=None, bus_name_marker=None, loss_penalty_factors=[], terminals=[], node_constraint_terms=[], **kw_args):
        """ Initialises a new 'ConnectivityNode' instance.
        """
        self._topological_node = None
        self.topological_node = topological_node
        self._connectivity_node_container = None
        self.connectivity_node_container = connectivity_node_container
        self._pnode = None
        self.pnode = pnode
        self._bus_name_marker = None
        self.bus_name_marker = bus_name_marker
        self._loss_penalty_factors = []
        self.loss_penalty_factors = loss_penalty_factors
        self._terminals = []
        self.terminals = terminals
        self._node_constraint_terms = []
        self.node_constraint_terms = node_constraint_terms

        super(ConnectivityNode, self).__init__(**kw_args)
    # >>> connectivity_node


# <<< topology
# @generated
# >>> topology

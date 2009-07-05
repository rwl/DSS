# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.OperationalLimits"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.OperationalLimits"

class OperationalLimitType(Element):
    """ A type of limit.  The meaning of a specific limit is described in this class.
    """
    # <<< operational_limit_type
    # @generated
    def __init__(self, direction='absolute_value', acceptable_duration=0.0, operational_limit=None, **kw_args):
        """ Initialises a new 'OperationalLimitType' instance.
        """
        # The direction of the limit. Values are: "absolute_value", "high", "low"
        self.direction = direction
        # The nominal acceptable duration of the limit.  Limits are commonly expressed in terms of the a time limit for which the limit is normally acceptable.   The actual acceptable duration of a specific limit may depend on other local factors such as temperature or wind speed. 
        self.acceptable_duration = acceptable_duration
        
        self._operational_limit = []
        if operational_limit is None:
            self.operational_limit = []
        else:
            self.operational_limit = operational_limit

        super(OperationalLimitType, self).__init__(**kw_args)
    # >>> operational_limit_type
        
    # <<< operational_limit
    # @generated
    def get_operational_limit(self):
        """ The operational limits associated with this type of limit.
        """
        return self._operational_limit

    def set_operational_limit(self, value):
        for x in self._operational_limit:
            x.operational_limit_type = None
        for y in value:
            y.operational_limit_type = self
        self._operational_limit = value
            
    operational_limit = property(get_operational_limit, set_operational_limit)
    
    def add_operational_limit(self, *operational_limit):
        for obj in operational_limit:
            obj._operational_limit_type = self
            if obj not in self._operational_limit:
                self._operational_limit.append(obj)
        
    def remove_operational_limit(self, *operational_limit):
        for obj in operational_limit:
            obj._operational_limit_type = None
            self._operational_limit.remove(obj)
    # >>> operational_limit



class BranchGroup(IdentifiedObject):
    """ A group of branch terminals whose directed flow summation is to be monitored. Abranch group need not form a cutset of the network.
    """
    # <<< branch_group
    # @generated
    def __init__(self, monitor_reactive_power=False, monitor_active_power=False, maximum_active_power=0.0, minimum_reactive_power=0.0, maximum_reactive_power=0.0, minimum_active_power=0.0, branch_group_terminal=None, **kw_args):
        """ Initialises a new 'BranchGroup' instance.
        """
        # Monitor the reactive power flow. 
        self.monitor_reactive_power = monitor_reactive_power
        # Monitor the active power flow. 
        self.monitor_active_power = monitor_active_power
        # The maximum active power flow. 
        self.maximum_active_power = maximum_active_power
        # The minimum reactive power flow. 
        self.minimum_reactive_power = minimum_reactive_power
        # The maximum reactive power flow. 
        self.maximum_reactive_power = maximum_reactive_power
        # The minimum active power flow. 
        self.minimum_active_power = minimum_active_power
        
        self._branch_group_terminal = []
        if branch_group_terminal is None:
            self.branch_group_terminal = []
        else:
            self.branch_group_terminal = branch_group_terminal

        super(BranchGroup, self).__init__(**kw_args)
    # >>> branch_group
        
    # <<< branch_group_terminal
    # @generated
    def get_branch_group_terminal(self):
        """ The directed branch group terminals to be summed.
        """
        return self._branch_group_terminal

    def set_branch_group_terminal(self, value):
        for x in self._branch_group_terminal:
            x.branch_group = None
        for y in value:
            y.branch_group = self
        self._branch_group_terminal = value
            
    branch_group_terminal = property(get_branch_group_terminal, set_branch_group_terminal)
    
    def add_branch_group_terminal(self, *branch_group_terminal):
        for obj in branch_group_terminal:
            obj._branch_group = self
            if obj not in self._branch_group_terminal:
                self._branch_group_terminal.append(obj)
        
    def remove_branch_group_terminal(self, *branch_group_terminal):
        for obj in branch_group_terminal:
            obj._branch_group = None
            self._branch_group_terminal.remove(obj)
    # >>> branch_group_terminal



class BranchGroupTerminal(Element):
    """ A specific directed terminal flow for a branch group.
    """
    # <<< branch_group_terminal
    # @generated
    def __init__(self, positive_flow_in=False, terminal=None, branch_group=None, **kw_args):
        """ Initialises a new 'BranchGroupTerminal' instance.
        """
        # The flow into the terminal is summed if set true.   The flow out of the terminanl is summed if set false. 
        self.positive_flow_in = positive_flow_in
        
        self._terminal = None
        self.terminal = terminal
        self._branch_group = None
        self.branch_group = branch_group

        super(BranchGroupTerminal, self).__init__(**kw_args)
    # >>> branch_group_terminal
        
    # <<< terminal
    # @generated
    def get_terminal(self):
        """ The terminal to be summed.
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            filtered = [x for x in self.terminal.branch_group_terminal if x != self]
            self._terminal._branch_group_terminal = filtered
            
        self._terminal = value
        if self._terminal is not None:
            if self not in self._terminal._branch_group_terminal:
                self._terminal._branch_group_terminal.append(self)

    terminal = property(get_terminal, set_terminal)
    # >>> terminal

    # <<< branch_group
    # @generated
    def get_branch_group(self):
        """ The branch group to which the directed branch group terminals belong.
        """
        return self._branch_group

    def set_branch_group(self, value):
        if self._branch_group is not None:
            filtered = [x for x in self.branch_group.branch_group_terminal if x != self]
            self._branch_group._branch_group_terminal = filtered
            
        self._branch_group = value
        if self._branch_group is not None:
            if self not in self._branch_group._branch_group_terminal:
                self._branch_group._branch_group_terminal.append(self)

    branch_group = property(get_branch_group, set_branch_group)
    # >>> branch_group



class OperationalLimit(IdentifiedObject):
    """ A value associated with a specific kind of limit.
    """
    # <<< operational_limit
    # @generated
    def __init__(self, type='', operational_limit_type=None, operational_limit_set=None, **kw_args):
        """ Initialises a new 'OperationalLimit' instance.
        """
        # Used to specify high/low and limit levels. 
        self.type = type
        
        self._operational_limit_type = None
        self.operational_limit_type = operational_limit_type
        self._operational_limit_set = None
        self.operational_limit_set = operational_limit_set

        super(OperationalLimit, self).__init__(**kw_args)
    # >>> operational_limit
        
    # <<< operational_limit_type
    # @generated
    def get_operational_limit_type(self):
        """ The limit type associated with this limit.
        """
        return self._operational_limit_type

    def set_operational_limit_type(self, value):
        if self._operational_limit_type is not None:
            filtered = [x for x in self.operational_limit_type.operational_limit if x != self]
            self._operational_limit_type._operational_limit = filtered
            
        self._operational_limit_type = value
        if self._operational_limit_type is not None:
            if self not in self._operational_limit_type._operational_limit:
                self._operational_limit_type._operational_limit.append(self)

    operational_limit_type = property(get_operational_limit_type, set_operational_limit_type)
    # >>> operational_limit_type

    # <<< operational_limit_set
    # @generated
    def get_operational_limit_set(self):
        """ The limit set to which the limit values belong.
        """
        return self._operational_limit_set

    def set_operational_limit_set(self, value):
        if self._operational_limit_set is not None:
            filtered = [x for x in self.operational_limit_set.operational_limit_value if x != self]
            self._operational_limit_set._operational_limit_value = filtered
            
        self._operational_limit_set = value
        if self._operational_limit_set is not None:
            if self not in self._operational_limit_set._operational_limit_value:
                self._operational_limit_set._operational_limit_value.append(self)

    operational_limit_set = property(get_operational_limit_set, set_operational_limit_set)
    # >>> operational_limit_set



class OperationalLimitSet(IdentifiedObject):
    """ A set of limits associated with equipmnet.  Sets of limits might apply to a specific temperature, or season for example. A set of limits may contain may different severities of limit levels that would apply to the same equipment.   The set may contain limits of different types such as apparent power and current limits or high and low voltage limits  that are logically applied together as a set.
    """
    # <<< operational_limit_set
    # @generated
    def __init__(self, equipment=None, terminal=None, operational_limit_value=None, **kw_args):
        """ Initialises a new 'OperationalLimitSet' instance.
        """
        
        self._equipment = None
        self.equipment = equipment
        self._terminal = None
        self.terminal = terminal
        self._operational_limit_value = []
        if operational_limit_value is None:
            self.operational_limit_value = []
        else:
            self.operational_limit_value = operational_limit_value

        super(OperationalLimitSet, self).__init__(**kw_args)
    # >>> operational_limit_set
        
    # <<< equipment
    # @generated
    def get_equipment(self):
        """ The equpment to which the limit set applies.
        """
        return self._equipment

    def set_equipment(self, value):
        if self._equipment is not None:
            filtered = [x for x in self.equipment.operational_limit_set if x != self]
            self._equipment._operational_limit_set = filtered
            
        self._equipment = value
        if self._equipment is not None:
            if self not in self._equipment._operational_limit_set:
                self._equipment._operational_limit_set.append(self)

    equipment = property(get_equipment, set_equipment)
    # >>> equipment

    # <<< terminal
    # @generated
    def get_terminal(self):
        """ The terminal specifically associated to this operational limit set.  If no terminal is associated, all terminals of the equipment are implied.
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            filtered = [x for x in self.terminal.operational_limit_set if x != self]
            self._terminal._operational_limit_set = filtered
            
        self._terminal = value
        if self._terminal is not None:
            if self not in self._terminal._operational_limit_set:
                self._terminal._operational_limit_set.append(self)

    terminal = property(get_terminal, set_terminal)
    # >>> terminal

    # <<< operational_limit_value
    # @generated
    def get_operational_limit_value(self):
        """ Values of equipment limits.
        """
        return self._operational_limit_value

    def set_operational_limit_value(self, value):
        for x in self._operational_limit_value:
            x.operational_limit_set = None
        for y in value:
            y.operational_limit_set = self
        self._operational_limit_value = value
            
    operational_limit_value = property(get_operational_limit_value, set_operational_limit_value)
    
    def add_operational_limit_value(self, *operational_limit_value):
        for obj in operational_limit_value:
            obj._operational_limit_set = self
            if obj not in self._operational_limit_value:
                self._operational_limit_value.append(obj)
        
    def remove_operational_limit_value(self, *operational_limit_value):
        for obj in operational_limit_value:
            obj._operational_limit_set = None
            self._operational_limit_value.remove(obj)
    # >>> operational_limit_value



class ApparentPowerLimit(OperationalLimit):
    """ Apparent power limit.
    """
    # <<< apparent_power_limit
    # @generated
    def __init__(self, value=0.0, **kw_args):
        """ Initialises a new 'ApparentPowerLimit' instance.
        """
        # The apparent power limit. 
        self.value = value
        

        super(ApparentPowerLimit, self).__init__(**kw_args)
    # >>> apparent_power_limit
        


class VoltageLimit(OperationalLimit):
    """ Operational limit applied to voltage.
    """
    # <<< voltage_limit
    # @generated
    def __init__(self, value=0.0, **kw_args):
        """ Initialises a new 'VoltageLimit' instance.
        """
        # Limit on voltage. High or low limit depends on the OperatoinalLimit.limitKind 
        self.value = value
        

        super(VoltageLimit, self).__init__(**kw_args)
    # >>> voltage_limit
        


class CurrentLimit(OperationalLimit):
    """ Operational limit on current.
    """
    # <<< current_limit
    # @generated
    def __init__(self, value=0.0, **kw_args):
        """ Initialises a new 'CurrentLimit' instance.
        """
        # Limit on current flow. 
        self.value = value
        

        super(CurrentLimit, self).__init__(**kw_args)
    # >>> current_limit
        


class ActivePowerLimit(OperationalLimit):
    """ Limit on active power flow.
    """
    # <<< active_power_limit
    # @generated
    def __init__(self, value=0.0, **kw_args):
        """ Initialises a new 'ActivePowerLimit' instance.
        """
        # Value of active power limit. 
        self.value = value
        

        super(ActivePowerLimit, self).__init__(**kw_args)
    # >>> active_power_limit
        


# <<< operational_limits
# @generated
# >>> operational_limits

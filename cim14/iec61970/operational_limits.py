# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class OperationalLimitType(Root):
    """ A type of limit.  The meaning of a specific limit is described in this class.
    """
    # The direction of the limit. Values are: "absolute_value", "high", "low"
    direction = 'absolute_value'

    # The nominal acceptable duration of the limit.  Limits are commonly expressed in terms of the a time limit for which the limit is normally acceptable.   The actual acceptable duration of a specific limit may depend on other local factors such as temperature or wind speed. 
    acceptable_duration = ''

    # The operational limits associated with this type of limit.
    operational_limit = []

    # <<< operational_limit_type
    # @generated
    def __init__(self, direction='absolute_value', acceptable_duration='', operational_limit=[], **kw_args):
        """ Initialises a new 'OperationalLimitType' instance.
        """
        self.direction = direction
        self.acceptable_duration = acceptable_duration
        self.operational_limit = operational_limit

        super(OperationalLimitType, self).__init__(**kw_args)
    # >>> operational_limit_type


class BranchGroup(IdentifiedObject):
    """ A group of branch terminals whose directed flow summation is to be monitored. Abranch group need not form a cutset of the network.
    """
    # Monitor the reactive power flow. 
    monitor_reactive_power = False

    # Monitor the active power flow. 
    monitor_active_power = False

    # The maximum active power flow. 
    maximum_active_power = ''

    # The minimum reactive power flow. 
    minimum_reactive_power = ''

    # The maximum reactive power flow. 
    maximum_reactive_power = ''

    # The minimum active power flow. 
    minimum_active_power = ''

    # The directed branch group terminals to be summed.
    branch_group_terminal = []

    # <<< branch_group
    # @generated
    def __init__(self, monitor_reactive_power=False, monitor_active_power=False, maximum_active_power='', minimum_reactive_power='', maximum_reactive_power='', minimum_active_power='', branch_group_terminal=[], **kw_args):
        """ Initialises a new 'BranchGroup' instance.
        """
        self.monitor_reactive_power = monitor_reactive_power
        self.monitor_active_power = monitor_active_power
        self.maximum_active_power = maximum_active_power
        self.minimum_reactive_power = minimum_reactive_power
        self.maximum_reactive_power = maximum_reactive_power
        self.minimum_active_power = minimum_active_power
        self.branch_group_terminal = branch_group_terminal

        super(BranchGroup, self).__init__(**kw_args)
    # >>> branch_group


class BranchGroupTerminal(Root):
    """ A specific directed terminal flow for a branch group.
    """
    # The flow into the terminal is summed if set true.   The flow out of the terminanl is summed if set false. 
    positive_flow_in = False

    # The terminal to be summed.
    terminal = None

    # The branch group to which the directed branch group terminals belong.
    branch_group = None

    # <<< branch_group_terminal
    # @generated
    def __init__(self, positive_flow_in=False, terminal=None, branch_group=None, **kw_args):
        """ Initialises a new 'BranchGroupTerminal' instance.
        """
        self.positive_flow_in = positive_flow_in
        self.terminal = terminal
        self.branch_group = branch_group

        super(BranchGroupTerminal, self).__init__(**kw_args)
    # >>> branch_group_terminal


class OperationalLimit(IdentifiedObject):
    """ A value associated with a specific kind of limit.
    """
    # Used to specify high/low and limit levels. 
    type = ''

    # The limit type associated with this limit.
    operational_limit_type = None

    # The limit set to which the limit values belong.
    operational_limit_set = None

    # <<< operational_limit
    # @generated
    def __init__(self, type='', operational_limit_type=None, operational_limit_set=None, **kw_args):
        """ Initialises a new 'OperationalLimit' instance.
        """
        self.type = type
        self.operational_limit_type = operational_limit_type
        self.operational_limit_set = operational_limit_set

        super(OperationalLimit, self).__init__(**kw_args)
    # >>> operational_limit


class OperationalLimitSet(IdentifiedObject):
    """ A set of limits associated with equipmnet.  Sets of limits might apply to a specific temperature, or season for example. A set of limits may contain may different severities of limit levels that would apply to the same equipment.   The set may contain limits of different types such as apparent power and current limits or high and low voltage limits  that are logically applied together as a set.
    """
    # The equpment to which the limit set applies.
    equipment = None

    # The terminal specifically associated to this operational limit set.  If no terminal is associated, all terminals of the equipment are implied.
    terminal = None

    # Values of equipment limits.
    operational_limit_value = []

    # <<< operational_limit_set
    # @generated
    def __init__(self, equipment=None, terminal=None, operational_limit_value=[], **kw_args):
        """ Initialises a new 'OperationalLimitSet' instance.
        """
        self.equipment = equipment
        self.terminal = terminal
        self.operational_limit_value = operational_limit_value

        super(OperationalLimitSet, self).__init__(**kw_args)
    # >>> operational_limit_set


class ApparentPowerLimit(OperationalLimit):
    """ Apparent power limit.
    """
    # The apparent power limit. 
    value = ''

    # <<< apparent_power_limit
    # @generated
    def __init__(self, value='', **kw_args):
        """ Initialises a new 'ApparentPowerLimit' instance.
        """
        self.value = value

        super(ApparentPowerLimit, self).__init__(**kw_args)
    # >>> apparent_power_limit


class VoltageLimit(OperationalLimit):
    """ Operational limit applied to voltage.
    """
    # Limit on voltage. High or low limit depends on the OperatoinalLimit.limitKind 
    value = ''

    # <<< voltage_limit
    # @generated
    def __init__(self, value='', **kw_args):
        """ Initialises a new 'VoltageLimit' instance.
        """
        self.value = value

        super(VoltageLimit, self).__init__(**kw_args)
    # >>> voltage_limit


class CurrentLimit(OperationalLimit):
    """ Operational limit on current.
    """
    # Limit on current flow. 
    value = ''

    # <<< current_limit
    # @generated
    def __init__(self, value='', **kw_args):
        """ Initialises a new 'CurrentLimit' instance.
        """
        self.value = value

        super(CurrentLimit, self).__init__(**kw_args)
    # >>> current_limit


class ActivePowerLimit(OperationalLimit):
    """ Limit on active power flow.
    """
    # Value of active power limit. 
    value = ''

    # <<< active_power_limit
    # @generated
    def __init__(self, value='', **kw_args):
        """ Initialises a new 'ActivePowerLimit' instance.
        """
        self.value = value

        super(ActivePowerLimit, self).__init__(**kw_args)
    # >>> active_power_limit


# <<< operational_limits
# @generated
# >>> operational_limits

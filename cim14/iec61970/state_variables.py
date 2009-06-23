# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.StateVariables"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.StateVariables"

class StateVariable(Element):
    """ An abstract class for state variables.
    """
    pass
    # <<< state_variable
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'StateVariable' instance.
        """

        super(StateVariable, self).__init__(**kw_args)
    # >>> state_variable


class SvVoltage(StateVariable):
    """ State variable for voltage.
    """
    # The voltage angle in radians of the topological node. 
    angle = ''

    # The voltage magnitude of the topological node. 
    v = ''

    # The topological node associated with the voltage state.
    topological_node = None

    # <<< sv_voltage
    # @generated
    def __init__(self, angle='', v='', topological_node=None, **kw_args):
        """ Initialises a new 'SvVoltage' instance.
        """
        self.angle = angle
        self.v = v
        self.topological_node = topological_node

        super(SvVoltage, self).__init__(**kw_args)
    # >>> sv_voltage


class SvShuntCompensatorSections(StateVariable):
    """ State variable for the number of sections in service for a shunt compensator.
    """
    # The number of sections in service. 
    sections = 0

    # The number of sections in service as a continous variable. 
    continuous_sections = 0.0

    # The shunt compensator for which the state applies.
    shunt_compensator = None

    # <<< sv_shunt_compensator_sections
    # @generated
    def __init__(self, sections=0, continuous_sections=0.0, shunt_compensator=None, **kw_args):
        """ Initialises a new 'SvShuntCompensatorSections' instance.
        """
        self.sections = sections
        self.continuous_sections = continuous_sections
        self.shunt_compensator = shunt_compensator

        super(SvShuntCompensatorSections, self).__init__(**kw_args)
    # >>> sv_shunt_compensator_sections


class SvTapStep(StateVariable):
    """ State variable for transformer tap step.     Normally a profile specifies only one of the attributes 'position'or 'tapRatio'.
    """
    # The integer tap position. 
    position = 0

    # The floating point tap position. 
    continuous_position = 0.0

    # The tap changer associated with the tap step state.
    tap_changer = None

    # <<< sv_tap_step
    # @generated
    def __init__(self, position=0, continuous_position=0.0, tap_changer=None, **kw_args):
        """ Initialises a new 'SvTapStep' instance.
        """
        self.position = position
        self.continuous_position = continuous_position
        self.tap_changer = tap_changer

        super(SvTapStep, self).__init__(**kw_args)
    # >>> sv_tap_step


class SvPowerFlow(StateVariable):
    """ State variable for power flow.
    """
    # The active power flow into the terminal. 
    p = ''

    # The reactive power flow into the terminal. 
    q = ''

    # The terminal associated with the power flow state.
    terminal = None

    # <<< sv_power_flow
    # @generated
    def __init__(self, p='', q='', terminal=None, **kw_args):
        """ Initialises a new 'SvPowerFlow' instance.
        """
        self.p = p
        self.q = q
        self.terminal = terminal

        super(SvPowerFlow, self).__init__(**kw_args)
    # >>> sv_power_flow


class SvInjection(StateVariable):
    """ Injectixon state variable.
    """
    # The activive power injected into the bus at this location. 
    p_net_injection = ''

    # The activive power injected into the bus at this location. 
    q_net_injection = ''

    # The topological node associated with the state injection.
    topological_node = None

    # <<< sv_injection
    # @generated
    def __init__(self, p_net_injection='', q_net_injection='', topological_node=None, **kw_args):
        """ Initialises a new 'SvInjection' instance.
        """
        self.p_net_injection = p_net_injection
        self.q_net_injection = q_net_injection
        self.topological_node = topological_node

        super(SvInjection, self).__init__(**kw_args)
    # >>> sv_injection


class SvStatus(StateVariable):
    """ State variable for status.
    """
    # The in service status as a result of topology processing. 
    in_service = False

    # The conducting equipment associated with the status state.
    conducting_equipment = None

    # <<< sv_status
    # @generated
    def __init__(self, in_service=False, conducting_equipment=None, **kw_args):
        """ Initialises a new 'SvStatus' instance.
        """
        self.in_service = in_service
        self.conducting_equipment = conducting_equipment

        super(SvStatus, self).__init__(**kw_args)
    # >>> sv_status


# <<< state_variables
# @generated
# >>> state_variables

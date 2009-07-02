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
    # <<< sv_voltage
    # @generated
    def __init__(self, angle='', v='', topological_node=None, **kw_args):
        """ Initialises a new 'SvVoltage' instance.
        """
        # The voltage angle in radians of the topological node. 
        self.angle = ''
        # The voltage magnitude of the topological node. 
        self.v = ''
        
        self._topological_node = None
        self.topological_node = topological_node

        super(SvVoltage, self).__init__(**kw_args)
    # >>> sv_voltage
        
    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological node associated with the voltage state.
        """
        return self._topological_node

    def set_topological_node(self, value):
        if self._topological_node is not None:
            self._topological_node._sv_voltage = None
            
        self._topological_node = value
        if self._topological_node is not None:
            self._topological_node._sv_voltage = self
            
    topological_node = property(get_topological_node, set_topological_node)
    # >>> topological_node



class SvShuntCompensatorSections(StateVariable):
    """ State variable for the number of sections in service for a shunt compensator.
    """
    # <<< sv_shunt_compensator_sections
    # @generated
    def __init__(self, sections=0, continuous_sections=0.0, shunt_compensator=None, **kw_args):
        """ Initialises a new 'SvShuntCompensatorSections' instance.
        """
        # The number of sections in service. 
        self.sections = 0
        # The number of sections in service as a continous variable. 
        self.continuous_sections = 0.0
        
        self._shunt_compensator = None
        self.shunt_compensator = shunt_compensator

        super(SvShuntCompensatorSections, self).__init__(**kw_args)
    # >>> sv_shunt_compensator_sections
        
    # <<< shunt_compensator
    # @generated
    def get_shunt_compensator(self):
        """ The shunt compensator for which the state applies.
        """
        return self._shunt_compensator

    def set_shunt_compensator(self, value):
        if self._shunt_compensator is not None:
            self._shunt_compensator._sv_shunt_compensator_sections = None
            
        self._shunt_compensator = value
        if self._shunt_compensator is not None:
            self._shunt_compensator._sv_shunt_compensator_sections = self
            
    shunt_compensator = property(get_shunt_compensator, set_shunt_compensator)
    # >>> shunt_compensator



class SvTapStep(StateVariable):
    """ State variable for transformer tap step.     Normally a profile specifies only one of the attributes 'position'or 'tapRatio'.
    """
    # <<< sv_tap_step
    # @generated
    def __init__(self, position=0, continuous_position=0.0, tap_changer=None, **kw_args):
        """ Initialises a new 'SvTapStep' instance.
        """
        # The integer tap position. 
        self.position = 0
        # The floating point tap position. 
        self.continuous_position = 0.0
        
        self._tap_changer = None
        self.tap_changer = tap_changer

        super(SvTapStep, self).__init__(**kw_args)
    # >>> sv_tap_step
        
    # <<< tap_changer
    # @generated
    def get_tap_changer(self):
        """ The tap changer associated with the tap step state.
        """
        return self._tap_changer

    def set_tap_changer(self, value):
        if self._tap_changer is not None:
            self._tap_changer._sv_tap_step = None
            
        self._tap_changer = value
        if self._tap_changer is not None:
            self._tap_changer._sv_tap_step = self
            
    tap_changer = property(get_tap_changer, set_tap_changer)
    # >>> tap_changer



class SvPowerFlow(StateVariable):
    """ State variable for power flow.
    """
    # <<< sv_power_flow
    # @generated
    def __init__(self, p='', q='', terminal=None, **kw_args):
        """ Initialises a new 'SvPowerFlow' instance.
        """
        # The active power flow into the terminal. 
        self.p = ''
        # The reactive power flow into the terminal. 
        self.q = ''
        
        self._terminal = None
        self.terminal = terminal

        super(SvPowerFlow, self).__init__(**kw_args)
    # >>> sv_power_flow
        
    # <<< terminal
    # @generated
    def get_terminal(self):
        """ The terminal associated with the power flow state.
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            self._terminal._sv_power_flow = None
            
        self._terminal = value
        if self._terminal is not None:
            self._terminal._sv_power_flow = self
            
    terminal = property(get_terminal, set_terminal)
    # >>> terminal



class SvInjection(StateVariable):
    """ Injectixon state variable.
    """
    # <<< sv_injection
    # @generated
    def __init__(self, p_net_injection='', q_net_injection='', topological_node=None, **kw_args):
        """ Initialises a new 'SvInjection' instance.
        """
        # The activive power injected into the bus at this location. 
        self.p_net_injection = ''
        # The activive power injected into the bus at this location. 
        self.q_net_injection = ''
        
        self._topological_node = None
        self.topological_node = topological_node

        super(SvInjection, self).__init__(**kw_args)
    # >>> sv_injection
        
    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological node associated with the state injection.
        """
        return self._topological_node

    def set_topological_node(self, value):
        if self._topological_node is not None:
            self._topological_node._sv_injection = None
            
        self._topological_node = value
        if self._topological_node is not None:
            self._topological_node._sv_injection = self
            
    topological_node = property(get_topological_node, set_topological_node)
    # >>> topological_node



class SvStatus(StateVariable):
    """ State variable for status.
    """
    # <<< sv_status
    # @generated
    def __init__(self, in_service=False, conducting_equipment=None, **kw_args):
        """ Initialises a new 'SvStatus' instance.
        """
        # The in service status as a result of topology processing. 
        self.in_service = False
        
        self._conducting_equipment = None
        self.conducting_equipment = conducting_equipment

        super(SvStatus, self).__init__(**kw_args)
    # >>> sv_status
        
    # <<< conducting_equipment
    # @generated
    def get_conducting_equipment(self):
        """ The conducting equipment associated with the status state.
        """
        return self._conducting_equipment

    def set_conducting_equipment(self, value):
        if self._conducting_equipment is not None:
            self._conducting_equipment._sv_status = None
            
        self._conducting_equipment = value
        if self._conducting_equipment is not None:
            self._conducting_equipment._sv_status = self
            
    conducting_equipment = property(get_conducting_equipment, set_conducting_equipment)
    # >>> conducting_equipment



# <<< state_variables
# @generated
# >>> state_variables

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import ConductingEquipment
from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import EquipmentContainer
from cim14.iec61970.core import Curve
from cim14.iec61970.core import RegularIntervalSchedule
from cim14.iec61970.core import Equipment
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Wires"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Wires"

class TransformerWinding(ConductingEquipment):
    """ A winding is associated with each defined terminal of a transformer (or phase shifter).
    """
    # <<< transformer_winding
    # @generated
    def __init__(self, grounded=False, r0=0.0, emergency_s=0.0, insulation_u=0.0, xground=0.0, x=0.0, g0=0.0, b0=0.0, connection_type='zn', x0=0.0, rground=0.0, short_term_s=0.0, g=0.0, rated_s=0.0, b=0.0, winding_type='primary', r=0.0, rated_u=0.0, from_winding_insulations=None, to_winding_test=None, ratio_tap_changer=None, power_transformer=None, from_winding_test=None, phase_tap_changer=None, to_winding_insulations=None, **kw_args):
        """ Initialises a new 'TransformerWinding' instance.
        """
        # Set if the winding is grounded. 
        self.grounded = grounded
        # Zero sequence series resistance of the winding. 
        self.r0 = r0
        # The apparent power that the winding can carry  under emergency conditions. 
        self.emergency_s = emergency_s
        # Basic insulation level voltage rating 
        self.insulation_u = insulation_u
        # Ground reactance path through connected grounding transformer. 
        self.xground = xground
        # Positive sequence series reactance of the winding. 
        self.x = x
        # Zero sequence magnetizing branch conductance. 
        self.g0 = g0
        # Zero sequence magnetizing branch susceptance. 
        self.b0 = b0
        # The type of connection of the winding. Values are: "zn", "y", "d", "z", "yn"
        self.connection_type = connection_type
        # Zero sequence series reactance of the winding. 
        self.x0 = x0
        # Ground resistance path through connected grounding transformer. 
        self.rground = rground
        # Apparent power that the winding can carry for a short period of time. 
        self.short_term_s = short_term_s
        # Magnetizing branch conductance (G mag). 
        self.g = g
        # The normal apparent power rating for the winding 
        self.rated_s = rated_s
        # Magnetizing branch susceptance (B mag). 
        self.b = b
        # The type of winding. Values are: "primary", "secondary", "quaternary", "tertiary"
        self.winding_type = winding_type
        # Positive sequence series resistance of the winding. 
        self.r = r
        # The rated voltage (phase-to-phase) of the winding, usually the same as the neutral voltage. 
        self.rated_u = rated_u
        
        self._from_winding_insulations = []
        if from_winding_insulations is None:
            self.from_winding_insulations = []
        else:
            self.from_winding_insulations = from_winding_insulations
        self._to_winding_test = []
        if to_winding_test is None:
            self.to_winding_test = []
        else:
            self.to_winding_test = to_winding_test
        self._ratio_tap_changer = None
        self.ratio_tap_changer = ratio_tap_changer
        self._power_transformer = None
        self.power_transformer = power_transformer
        self._from_winding_test = []
        if from_winding_test is None:
            self.from_winding_test = []
        else:
            self.from_winding_test = from_winding_test
        self._phase_tap_changer = None
        self.phase_tap_changer = phase_tap_changer
        self._to_winding_insulations = []
        if to_winding_insulations is None:
            self.to_winding_insulations = []
        else:
            self.to_winding_insulations = to_winding_insulations

        super(TransformerWinding, self).__init__(**kw_args)
    # >>> transformer_winding
        
    # <<< from_winding_insulations
    # @generated
    def get_from_winding_insulations(self):
        """ 
        """
        return self._from_winding_insulations

    def set_from_winding_insulations(self, value):
        for x in self._from_winding_insulations:
            x.from_transformer_winding = None
        for y in value:
            y.from_transformer_winding = self
        self._from_winding_insulations = value
            
    from_winding_insulations = property(get_from_winding_insulations, set_from_winding_insulations)
    
    def add_from_winding_insulations(self, *from_winding_insulations):
        for obj in from_winding_insulations:
            obj._from_transformer_winding = self
            if obj not in self._from_winding_insulations:
                self._from_winding_insulations.append(obj)
        
    def remove_from_winding_insulations(self, *from_winding_insulations):
        for obj in from_winding_insulations:
            obj._from_transformer_winding = None
            self._from_winding_insulations.remove(obj)
    # >>> from_winding_insulations

    # <<< to_winding_test
    # @generated
    def get_to_winding_test(self):
        """ The winding winding tests for which the transformer winding (terminal) participates as the 'to' end of the test.
        """
        return self._to_winding_test

    def set_to_winding_test(self, value):
        for x in self._to_winding_test:
            x.to_transformer_winding = None
        for y in value:
            y.to_transformer_winding = self
        self._to_winding_test = value
            
    to_winding_test = property(get_to_winding_test, set_to_winding_test)
    
    def add_to_winding_test(self, *to_winding_test):
        for obj in to_winding_test:
            obj._to_transformer_winding = self
            if obj not in self._to_winding_test:
                self._to_winding_test.append(obj)
        
    def remove_to_winding_test(self, *to_winding_test):
        for obj in to_winding_test:
            obj._to_transformer_winding = None
            self._to_winding_test.remove(obj)
    # >>> to_winding_test

    # <<< ratio_tap_changer
    # @generated
    def get_ratio_tap_changer(self):
        """ The ratio tap changer associated with the transformer winding.
        """
        return self._ratio_tap_changer

    def set_ratio_tap_changer(self, value):
        if self._ratio_tap_changer is not None:
            self._ratio_tap_changer._transformer_winding = None
            
        self._ratio_tap_changer = value
        if self._ratio_tap_changer is not None:
            self._ratio_tap_changer._transformer_winding = self
            
    ratio_tap_changer = property(get_ratio_tap_changer, set_ratio_tap_changer)
    # >>> ratio_tap_changer

    # <<< power_transformer
    # @generated
    def get_power_transformer(self):
        """ A transformer has windings
        """
        return self._power_transformer

    def set_power_transformer(self, value):
        if self._power_transformer is not None:
            filtered = [x for x in self.power_transformer.transformer_windings if x != self]
            self._power_transformer._transformer_windings = filtered
            
        self._power_transformer = value
        if self._power_transformer is not None:
            if self not in self._power_transformer._transformer_windings:
                self._power_transformer._transformer_windings.append(self)

    power_transformer = property(get_power_transformer, set_power_transformer)
    # >>> power_transformer

    # <<< from_winding_test
    # @generated
    def get_from_winding_test(self):
        """ The transformer winding tests for which the transformer winding (terminal) participates as the 'from' part of the test.
        """
        return self._from_winding_test

    def set_from_winding_test(self, value):
        for x in self._from_winding_test:
            x.from_transformer_winding = None
        for y in value:
            y.from_transformer_winding = self
        self._from_winding_test = value
            
    from_winding_test = property(get_from_winding_test, set_from_winding_test)
    
    def add_from_winding_test(self, *from_winding_test):
        for obj in from_winding_test:
            obj._from_transformer_winding = self
            if obj not in self._from_winding_test:
                self._from_winding_test.append(obj)
        
    def remove_from_winding_test(self, *from_winding_test):
        for obj in from_winding_test:
            obj._from_transformer_winding = None
            self._from_winding_test.remove(obj)
    # >>> from_winding_test

    # <<< phase_tap_changer
    # @generated
    def get_phase_tap_changer(self):
        """ The phase tap changer associated with the transformer winding.
        """
        return self._phase_tap_changer

    def set_phase_tap_changer(self, value):
        if self._phase_tap_changer is not None:
            self._phase_tap_changer._transformer_winding = None
            
        self._phase_tap_changer = value
        if self._phase_tap_changer is not None:
            self._phase_tap_changer._transformer_winding = self
            
    phase_tap_changer = property(get_phase_tap_changer, set_phase_tap_changer)
    # >>> phase_tap_changer

    # <<< to_winding_insulations
    # @generated
    def get_to_winding_insulations(self):
        """ 
        """
        return self._to_winding_insulations

    def set_to_winding_insulations(self, value):
        for x in self._to_winding_insulations:
            x.to_transformer_winding = None
        for y in value:
            y.to_transformer_winding = self
        self._to_winding_insulations = value
            
    to_winding_insulations = property(get_to_winding_insulations, set_to_winding_insulations)
    
    def add_to_winding_insulations(self, *to_winding_insulations):
        for obj in to_winding_insulations:
            obj._to_transformer_winding = self
            if obj not in self._to_winding_insulations:
                self._to_winding_insulations.append(obj)
        
    def remove_to_winding_insulations(self, *to_winding_insulations):
        for obj in to_winding_insulations:
            obj._to_transformer_winding = None
            self._to_winding_insulations.remove(obj)
    # >>> to_winding_insulations



class RegulatingControl(PowerSystemResource):
    """ Specifies a set of equipment that works together to control a power system quantity such as voltage or flow.
    """
    # <<< regulating_control
    # @generated
    def __init__(self, target_range=0.0, discrete=False, target_value=0.0, mode='admittance', tap_changer=None, regulating_cond_eq=None, regulation_schedule=None, terminal=None, **kw_args):
        """ Initialises a new 'RegulatingControl' instance.
        """
        # This is the case input target range.   This performs the same function as the value2 attribute on the regulation schedule in the case that schedules are not used.   The units of those appropriate for the mode. 
        self.target_range = target_range
        # The regulation is performed in a discrete mode. 
        self.discrete = discrete
        # The target value specified for case input.   This value can be used for the target value wihout the use of schedules. The value has the units appropriate to the mode attribute. 
        self.target_value = target_value
        # The regulating control mode presently available.  This specifications allows for determining the kind of regualation without need for obtaining the units from a schedule. Values are: "admittance", "fixed", "active_power", "current_flow", "voltage", "reactive_power"
        self.mode = mode
        
        self._tap_changer = []
        if tap_changer is None:
            self.tap_changer = []
        else:
            self.tap_changer = tap_changer
        self._regulating_cond_eq = []
        if regulating_cond_eq is None:
            self.regulating_cond_eq = []
        else:
            self.regulating_cond_eq = regulating_cond_eq
        self._regulation_schedule = None
        self.regulation_schedule = regulation_schedule
        self._terminal = None
        self.terminal = terminal

        super(RegulatingControl, self).__init__(**kw_args)
    # >>> regulating_control
        
    # <<< tap_changer
    # @generated
    def get_tap_changer(self):
        """ copy from reg conduting eq
        """
        return self._tap_changer

    def set_tap_changer(self, value):
        for x in self._tap_changer:
            x.regulating_control = None
        for y in value:
            y.regulating_control = self
        self._tap_changer = value
            
    tap_changer = property(get_tap_changer, set_tap_changer)
    
    def add_tap_changer(self, *tap_changer):
        for obj in tap_changer:
            obj._regulating_control = self
            if obj not in self._tap_changer:
                self._tap_changer.append(obj)
        
    def remove_tap_changer(self, *tap_changer):
        for obj in tap_changer:
            obj._regulating_control = None
            self._tap_changer.remove(obj)
    # >>> tap_changer

    # <<< regulating_cond_eq
    # @generated
    def get_regulating_cond_eq(self):
        """ copy from reg cond eq
        """
        return self._regulating_cond_eq

    def set_regulating_cond_eq(self, value):
        for x in self._regulating_cond_eq:
            x.regulating_control = None
        for y in value:
            y.regulating_control = self
        self._regulating_cond_eq = value
            
    regulating_cond_eq = property(get_regulating_cond_eq, set_regulating_cond_eq)
    
    def add_regulating_cond_eq(self, *regulating_cond_eq):
        for obj in regulating_cond_eq:
            obj._regulating_control = self
            if obj not in self._regulating_cond_eq:
                self._regulating_cond_eq.append(obj)
        
    def remove_regulating_cond_eq(self, *regulating_cond_eq):
        for obj in regulating_cond_eq:
            obj._regulating_control = None
            self._regulating_cond_eq.remove(obj)
    # >>> regulating_cond_eq

    # <<< regulation_schedule
    # @generated
    def get_regulation_schedule(self):
        """ Schedule for this Regulating regulating control.
        """
        return self._regulation_schedule

    def set_regulation_schedule(self, value):
        if self._regulation_schedule is not None:
            filtered = [x for x in self.regulation_schedule.regulating_control if x != self]
            self._regulation_schedule._regulating_control = filtered
            
        self._regulation_schedule = value
        if self._regulation_schedule is not None:
            if self not in self._regulation_schedule._regulating_control:
                self._regulation_schedule._regulating_control.append(self)

    regulation_schedule = property(get_regulation_schedule, set_regulation_schedule)
    # >>> regulation_schedule

    # <<< terminal
    # @generated
    def get_terminal(self):
        """ The terminal associated with this regulating control.
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            filtered = [x for x in self.terminal.regulating_control if x != self]
            self._terminal._regulating_control = filtered
            
        self._terminal = value
        if self._terminal is not None:
            if self not in self._terminal._regulating_control:
                self._terminal._regulating_control.append(self)

    terminal = property(get_terminal, set_terminal)
    # >>> terminal



class RegulatingCondEq(ConductingEquipment):
    """ RegulatingCondEq is a type of ConductingEquipment that can regulate Measurements and have a RegulationSchedule.
    """
    # <<< regulating_cond_eq
    # @generated
    def __init__(self, regulating_control=None, controls=None, **kw_args):
        """ Initialises a new 'RegulatingCondEq' instance.
        """
        
        self._regulating_control = None
        self.regulating_control = regulating_control
        self._controls = []
        if controls is None:
            self.controls = []
        else:
            self.controls = controls

        super(RegulatingCondEq, self).__init__(**kw_args)
    # >>> regulating_cond_eq
        
    # <<< regulating_control
    # @generated
    def get_regulating_control(self):
        """ copy from ...
        """
        return self._regulating_control

    def set_regulating_control(self, value):
        if self._regulating_control is not None:
            filtered = [x for x in self.regulating_control.regulating_cond_eq if x != self]
            self._regulating_control._regulating_cond_eq = filtered
            
        self._regulating_control = value
        if self._regulating_control is not None:
            if self not in self._regulating_control._regulating_cond_eq:
                self._regulating_control._regulating_cond_eq.append(self)

    regulating_control = property(get_regulating_control, set_regulating_control)
    # >>> regulating_control

    # <<< controls
    # @generated
    def get_controls(self):
        """ The controller outputs used to actually govern a regulating device, e.g. the magnetization of a synchronous machine or capacitor bank breaker actuator.
        """
        return self._controls

    def set_controls(self, value):
        for x in self._controls:
            x.regulating_cond_eq = None
        for y in value:
            y.regulating_cond_eq = self
        self._controls = value
            
    controls = property(get_controls, set_controls)
    
    def add_controls(self, *controls):
        for obj in controls:
            obj._regulating_cond_eq = self
            if obj not in self._controls:
                self._controls.append(obj)
        
    def remove_controls(self, *controls):
        for obj in controls:
            obj._regulating_cond_eq = None
            self._controls.remove(obj)
    # >>> controls



class RectifierInverter(ConductingEquipment):
    """ Bi-directional AC-DC conversion equipment that can be used to control DC current, DC voltage, DC power flow, or firing angle.
    """
    # <<< rectifier_inverter
    # @generated
    def __init__(self, min_p=0.0, operating_mode='', compound_resistance=0.0, rated_u=0.0, commutating_reactance=0.0, bridges=0, max_u=0.0, frequency=0.0, max_p=0.0, min_compound_voltage=0.0, min_u=0.0, commutating_resistance=0.0, **kw_args):
        """ Initialises a new 'RectifierInverter' instance.
        """
        # The minimum active power on the DC side at which the converter should operate. 
        self.min_p = min_p
        # Operating mode for the converter. 
        self.operating_mode = operating_mode
        # Compounding resistance. 
        self.compound_resistance = compound_resistance
        # Rectifier/inverter primary base voltage 
        self.rated_u = rated_u
        # Commutating reactance at AC bus frequency. 
        self.commutating_reactance = commutating_reactance
        # Number of bridges 
        self.bridges = bridges
        # The maximum voltage on the DC side at which the converter should operate. 
        self.max_u = max_u
        # Frequency on the AC side. 
        self.frequency = frequency
        # The maximum active power on the DC side at which the fconverter should operate. 
        self.max_p = max_p
        # Minimum compounded DC voltage 
        self.min_compound_voltage = min_compound_voltage
        # The minimum voltage on the DC side at which the converter should operate. 
        self.min_u = min_u
        # Commutating resistance. 
        self.commutating_resistance = commutating_resistance
        

        super(RectifierInverter, self).__init__(**kw_args)
    # >>> rectifier_inverter
        


class Switch(ConductingEquipment):
    """ A generic device designed to close, or open, or both, one or more electric circuits.
    """
    # <<< switch
    # @generated
    def __init__(self, switch_on_count=0, normal_open=False, retained=False, switch_on_date='', connect_disconnect_functions=None, composite_switch=None, load_mgmt_functions=None, switching_operations=None, **kw_args):
        """ Initialises a new 'Switch' instance.
        """
        # The switch on count since the switch was last reset or initialized. 
        self.switch_on_count = switch_on_count
        # The attribute is used in cases when no Measurement for the status value is present. If the Switch has a status measurment the Discrete.normalValue is expected to match with the Switch.normalOpen. 
        self.normal_open = normal_open
        # Branch is retained in a bus branch model. 
        self.retained = retained
        # The date and time when the switch was last switched on. 
        self.switch_on_date = switch_on_date
        
        self._connect_disconnect_functions = []
        if connect_disconnect_functions is None:
            self.connect_disconnect_functions = []
        else:
            self.connect_disconnect_functions = connect_disconnect_functions
        self._composite_switch = None
        self.composite_switch = composite_switch
        self._load_mgmt_functions = []
        if load_mgmt_functions is None:
            self.load_mgmt_functions = []
        else:
            self.load_mgmt_functions = load_mgmt_functions
        self._switching_operations = []
        if switching_operations is None:
            self.switching_operations = []
        else:
            self.switching_operations = switching_operations

        super(Switch, self).__init__(**kw_args)
    # >>> switch
        
    # <<< connect_disconnect_functions
    # @generated
    def get_connect_disconnect_functions(self):
        """ 
        """
        return self._connect_disconnect_functions

    def set_connect_disconnect_functions(self, value):
        for p in self._connect_disconnect_functions:
            filtered = [q for q in p.switches if q != self]
            self._connect_disconnect_functions._switches = filtered
        for r in value:
            if self not in r._switches:
                r._switches.append(self)
        self._connect_disconnect_functions = value
            
    connect_disconnect_functions = property(get_connect_disconnect_functions, set_connect_disconnect_functions)
    
    def add_connect_disconnect_functions(self, *connect_disconnect_functions):
        for obj in connect_disconnect_functions:
            if self not in obj._switches:
                obj._switches.append(self)
            self._connect_disconnect_functions.append(obj)
        
    def remove_connect_disconnect_functions(self, *connect_disconnect_functions):
        for obj in connect_disconnect_functions:
            if self in obj._switches:
                obj._switches.remove(self)
            self._connect_disconnect_functions.remove(obj)
    # >>> connect_disconnect_functions

    # <<< composite_switch
    # @generated
    def get_composite_switch(self):
        """ Composite switch this Switch belongs to
        """
        return self._composite_switch

    def set_composite_switch(self, value):
        if self._composite_switch is not None:
            filtered = [x for x in self.composite_switch.switches if x != self]
            self._composite_switch._switches = filtered
            
        self._composite_switch = value
        if self._composite_switch is not None:
            if self not in self._composite_switch._switches:
                self._composite_switch._switches.append(self)

    composite_switch = property(get_composite_switch, set_composite_switch)
    # >>> composite_switch

    # <<< load_mgmt_functions
    # @generated
    def get_load_mgmt_functions(self):
        """ 
        """
        return self._load_mgmt_functions

    def set_load_mgmt_functions(self, value):
        for p in self._load_mgmt_functions:
            filtered = [q for q in p.switches if q != self]
            self._load_mgmt_functions._switches = filtered
        for r in value:
            if self not in r._switches:
                r._switches.append(self)
        self._load_mgmt_functions = value
            
    load_mgmt_functions = property(get_load_mgmt_functions, set_load_mgmt_functions)
    
    def add_load_mgmt_functions(self, *load_mgmt_functions):
        for obj in load_mgmt_functions:
            if self not in obj._switches:
                obj._switches.append(self)
            self._load_mgmt_functions.append(obj)
        
    def remove_load_mgmt_functions(self, *load_mgmt_functions):
        for obj in load_mgmt_functions:
            if self in obj._switches:
                obj._switches.remove(self)
            self._load_mgmt_functions.remove(obj)
    # >>> load_mgmt_functions

    # <<< switching_operations
    # @generated
    def get_switching_operations(self):
        """ A switch may be operated by many schedules.
        """
        return self._switching_operations

    def set_switching_operations(self, value):
        for p in self._switching_operations:
            filtered = [q for q in p.switches if q != self]
            self._switching_operations._switches = filtered
        for r in value:
            if self not in r._switches:
                r._switches.append(self)
        self._switching_operations = value
            
    switching_operations = property(get_switching_operations, set_switching_operations)
    
    def add_switching_operations(self, *switching_operations):
        for obj in switching_operations:
            if self not in obj._switches:
                obj._switches.append(self)
            self._switching_operations.append(obj)
        
    def remove_switching_operations(self, *switching_operations):
        for obj in switching_operations:
            if self in obj._switches:
                obj._switches.remove(self)
            self._switching_operations.remove(obj)
    # >>> switching_operations



class Line(EquipmentContainer):
    """ A component part of a system extending between adjacent substations or from a substation to an adjacent interconnection point.
    """
    # <<< line
    # @generated
    def __init__(self, transmission_right_of_way=None, region=None, flowgates=None, **kw_args):
        """ Initialises a new 'Line' instance.
        """
        
        self._transmission_right_of_way = None
        self.transmission_right_of_way = transmission_right_of_way
        self._region = None
        self.region = region
        self._flowgates = []
        if flowgates is None:
            self.flowgates = []
        else:
            self.flowgates = flowgates

        super(Line, self).__init__(**kw_args)
    # >>> line
        
    # <<< transmission_right_of_way
    # @generated
    def get_transmission_right_of_way(self):
        """ A transmission line can be part of a transmission corridor
        """
        return self._transmission_right_of_way

    def set_transmission_right_of_way(self, value):
        if self._transmission_right_of_way is not None:
            filtered = [x for x in self.transmission_right_of_way.lines if x != self]
            self._transmission_right_of_way._lines = filtered
            
        self._transmission_right_of_way = value
        if self._transmission_right_of_way is not None:
            if self not in self._transmission_right_of_way._lines:
                self._transmission_right_of_way._lines.append(self)

    transmission_right_of_way = property(get_transmission_right_of_way, set_transmission_right_of_way)
    # >>> transmission_right_of_way

    # <<< region
    # @generated
    def get_region(self):
        """ A Line can be contained by a SubGeographical Region.
        """
        return self._region

    def set_region(self, value):
        if self._region is not None:
            filtered = [x for x in self.region.lines if x != self]
            self._region._lines = filtered
            
        self._region = value
        if self._region is not None:
            if self not in self._region._lines:
                self._region._lines.append(self)

    region = property(get_region, set_region)
    # >>> region

    # <<< flowgates
    # @generated
    def get_flowgates(self):
        """ 
        """
        return self._flowgates

    def set_flowgates(self, value):
        for p in self._flowgates:
            filtered = [q for q in p.lines if q != self]
            self._flowgates._lines = filtered
        for r in value:
            if self not in r._lines:
                r._lines.append(self)
        self._flowgates = value
            
    flowgates = property(get_flowgates, set_flowgates)
    
    def add_flowgates(self, *flowgates):
        for obj in flowgates:
            if self not in obj._lines:
                obj._lines.append(self)
            self._flowgates.append(obj)
        
    def remove_flowgates(self, *flowgates):
        for obj in flowgates:
            if self in obj._lines:
                obj._lines.remove(self)
            self._flowgates.remove(obj)
    # >>> flowgates



class ReactiveCapabilityCurve(Curve):
    """ Reactive power rating envelope versus the synchronous machine's active power, in both the generating and motoring modes. For each active power value there is a corresponding high and low reactive power limit  value. Typically there will be a separate curve for each coolant condition, such as hydrogen pressure.  The Y1 axis values represent reactive minimum and the Y2 axis values represent reactive maximum.
    """
    # <<< reactive_capability_curve
    # @generated
    def __init__(self, hydrogen_pressure=0.0, coolant_temperature=0.0, synchronous_machines=None, initially_used_by_synchronous_machines=None, **kw_args):
        """ Initialises a new 'ReactiveCapabilityCurve' instance.
        """
        # The hydrogen coolant pressure 
        self.hydrogen_pressure = hydrogen_pressure
        # The machine's coolant temperature (e.g., ambient air or stator circulating water). 
        self.coolant_temperature = coolant_temperature
        
        self._synchronous_machines = []
        if synchronous_machines is None:
            self.synchronous_machines = []
        else:
            self.synchronous_machines = synchronous_machines
        self._initially_used_by_synchronous_machines = []
        if initially_used_by_synchronous_machines is None:
            self.initially_used_by_synchronous_machines = []
        else:
            self.initially_used_by_synchronous_machines = initially_used_by_synchronous_machines

        super(ReactiveCapabilityCurve, self).__init__(**kw_args)
    # >>> reactive_capability_curve
        
    # <<< synchronous_machines
    # @generated
    def get_synchronous_machines(self):
        """ Synchronous machines using this curve.
        """
        return self._synchronous_machines

    def set_synchronous_machines(self, value):
        for p in self._synchronous_machines:
            filtered = [q for q in p.reactive_capability_curves if q != self]
            self._synchronous_machines._reactive_capability_curves = filtered
        for r in value:
            if self not in r._reactive_capability_curves:
                r._reactive_capability_curves.append(self)
        self._synchronous_machines = value
            
    synchronous_machines = property(get_synchronous_machines, set_synchronous_machines)
    
    def add_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
            if self not in obj._reactive_capability_curves:
                obj._reactive_capability_curves.append(self)
            self._synchronous_machines.append(obj)
        
    def remove_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
            if self in obj._reactive_capability_curves:
                obj._reactive_capability_curves.remove(self)
            self._synchronous_machines.remove(obj)
    # >>> synchronous_machines

    # <<< initially_used_by_synchronous_machines
    # @generated
    def get_initially_used_by_synchronous_machines(self):
        """ Synchronous machines using this curve as default.
        """
        return self._initially_used_by_synchronous_machines

    def set_initially_used_by_synchronous_machines(self, value):
        for x in self._initially_used_by_synchronous_machines:
            x.initial_reactive_capability_curve = None
        for y in value:
            y.initial_reactive_capability_curve = self
        self._initially_used_by_synchronous_machines = value
            
    initially_used_by_synchronous_machines = property(get_initially_used_by_synchronous_machines, set_initially_used_by_synchronous_machines)
    
    def add_initially_used_by_synchronous_machines(self, *initially_used_by_synchronous_machines):
        for obj in initially_used_by_synchronous_machines:
            obj._initial_reactive_capability_curve = self
            if obj not in self._initially_used_by_synchronous_machines:
                self._initially_used_by_synchronous_machines.append(obj)
        
    def remove_initially_used_by_synchronous_machines(self, *initially_used_by_synchronous_machines):
        for obj in initially_used_by_synchronous_machines:
            obj._initial_reactive_capability_curve = None
            self._initially_used_by_synchronous_machines.remove(obj)
    # >>> initially_used_by_synchronous_machines



class SeriesCompensator(ConductingEquipment):
    """ A Series Compensator is a series capacitor or reactor or an AC transmission line without charging susceptance.  It is a two terminal device.
    """
    # <<< series_compensator
    # @generated
    def __init__(self, r=0.0, x=0.0, **kw_args):
        """ Initialises a new 'SeriesCompensator' instance.
        """
        # Positive sequence resistance. 
        self.r = r
        # Positive sequence reactance. 
        self.x = x
        

        super(SeriesCompensator, self).__init__(**kw_args)
    # >>> series_compensator
        


class EnergyConsumer(ConductingEquipment):
    """ Generic user of energy - a  point of consumption on the power system model
    """
    # <<< energy_consumer
    # @generated
    def __init__(self, customer_count=0, pfixed=0.0, qfixed_pct=0.0, pfixed_pct=0.0, qfixed=0.0, service_delivery_points=None, phase_loads=None, power_cut_zone=None, load_response=None, **kw_args):
        """ Initialises a new 'EnergyConsumer' instance.
        """
        # Number of individual customers represented by this Demand 
        self.customer_count = customer_count
        # Active power of the load that is a fixed quantity. 
        self.pfixed = pfixed
        # Fixed reactive power as per cent of load group fixed reactive power. 
        self.qfixed_pct = qfixed_pct
        # Fixed active power as per cent of load group fixed active power 
        self.pfixed_pct = pfixed_pct
        # Reactive power of the load that is a fixed quantity. 
        self.qfixed = qfixed
        
        self._service_delivery_points = []
        if service_delivery_points is None:
            self.service_delivery_points = []
        else:
            self.service_delivery_points = service_delivery_points
        self._phase_loads = []
        if phase_loads is None:
            self.phase_loads = []
        else:
            self.phase_loads = phase_loads
        self._power_cut_zone = None
        self.power_cut_zone = power_cut_zone
        self._load_response = None
        self.load_response = load_response

        super(EnergyConsumer, self).__init__(**kw_args)
    # >>> energy_consumer
        
    # <<< service_delivery_points
    # @generated
    def get_service_delivery_points(self):
        """ 
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x.energy_consumer = None
        for y in value:
            y.energy_consumer = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._energy_consumer = self
            if obj not in self._service_delivery_points:
                self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._energy_consumer = None
            self._service_delivery_points.remove(obj)
    # >>> service_delivery_points

    # <<< phase_loads
    # @generated
    def get_phase_loads(self):
        """ 
        """
        return self._phase_loads

    def set_phase_loads(self, value):
        for x in self._phase_loads:
            x.energy_consumer = None
        for y in value:
            y.energy_consumer = self
        self._phase_loads = value
            
    phase_loads = property(get_phase_loads, set_phase_loads)
    
    def add_phase_loads(self, *phase_loads):
        for obj in phase_loads:
            obj._energy_consumer = self
            if obj not in self._phase_loads:
                self._phase_loads.append(obj)
        
    def remove_phase_loads(self, *phase_loads):
        for obj in phase_loads:
            obj._energy_consumer = None
            self._phase_loads.remove(obj)
    # >>> phase_loads

    # <<< power_cut_zone
    # @generated
    def get_power_cut_zone(self):
        """ An energy consumer is assigned to a power cut zone
        """
        return self._power_cut_zone

    def set_power_cut_zone(self, value):
        if self._power_cut_zone is not None:
            filtered = [x for x in self.power_cut_zone.energy_consumers if x != self]
            self._power_cut_zone._energy_consumers = filtered
            
        self._power_cut_zone = value
        if self._power_cut_zone is not None:
            if self not in self._power_cut_zone._energy_consumers:
                self._power_cut_zone._energy_consumers.append(self)

    power_cut_zone = property(get_power_cut_zone, set_power_cut_zone)
    # >>> power_cut_zone

    # <<< load_response
    # @generated
    def get_load_response(self):
        """ The load response characteristic of this load.
        """
        return self._load_response

    def set_load_response(self, value):
        if self._load_response is not None:
            filtered = [x for x in self.load_response.energy_consumer if x != self]
            self._load_response._energy_consumer = filtered
            
        self._load_response = value
        if self._load_response is not None:
            if self not in self._load_response._energy_consumer:
                self._load_response._energy_consumer.append(self)

    load_response = property(get_load_response, set_load_response)
    # >>> load_response



class RegulationSchedule(RegularIntervalSchedule):
    """ A pre-established pattern over time for a controlled variable, e.g., busbar voltage.
    """
    # <<< regulation_schedule
    # @generated
    def __init__(self, line_drop_r=0.0, line_drop_compensation=False, line_drop_x=0.0, regulating_control=None, voltage_control_zones=None, **kw_args):
        """ Initialises a new 'RegulationSchedule' instance.
        """
        # Line drop resistance. 
        self.line_drop_r = line_drop_r
        # Flag to indicate that line drop compensation is to be applied 
        self.line_drop_compensation = line_drop_compensation
        # Line drop reactance. 
        self.line_drop_x = line_drop_x
        
        self._regulating_control = []
        if regulating_control is None:
            self.regulating_control = []
        else:
            self.regulating_control = regulating_control
        self._voltage_control_zones = []
        if voltage_control_zones is None:
            self.voltage_control_zones = []
        else:
            self.voltage_control_zones = voltage_control_zones

        super(RegulationSchedule, self).__init__(**kw_args)
    # >>> regulation_schedule
        
    # <<< regulating_control
    # @generated
    def get_regulating_control(self):
        """ Regulating controls that have this Schedule.
        """
        return self._regulating_control

    def set_regulating_control(self, value):
        for x in self._regulating_control:
            x.regulation_schedule = None
        for y in value:
            y.regulation_schedule = self
        self._regulating_control = value
            
    regulating_control = property(get_regulating_control, set_regulating_control)
    
    def add_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._regulation_schedule = self
            if obj not in self._regulating_control:
                self._regulating_control.append(obj)
        
    def remove_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._regulation_schedule = None
            self._regulating_control.remove(obj)
    # >>> regulating_control

    # <<< voltage_control_zones
    # @generated
    def get_voltage_control_zones(self):
        """ A VoltageControlZone may have a  voltage regulation schedule.
        """
        return self._voltage_control_zones

    def set_voltage_control_zones(self, value):
        for x in self._voltage_control_zones:
            x.regulation_schedule = None
        for y in value:
            y.regulation_schedule = self
        self._voltage_control_zones = value
            
    voltage_control_zones = property(get_voltage_control_zones, set_voltage_control_zones)
    
    def add_voltage_control_zones(self, *voltage_control_zones):
        for obj in voltage_control_zones:
            obj._regulation_schedule = self
            if obj not in self._voltage_control_zones:
                self._voltage_control_zones.append(obj)
        
    def remove_voltage_control_zones(self, *voltage_control_zones):
        for obj in voltage_control_zones:
            obj._regulation_schedule = None
            self._voltage_control_zones.remove(obj)
    # >>> voltage_control_zones



class Connector(ConductingEquipment):
    """ A conductor, or group of conductors, with negligible impedance, that serve to connect other conducting equipment within a single substation and are modelled with a single logical terminal.
    """
    pass
    # <<< connector
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Connector' instance.
        """
        

        super(Connector, self).__init__(**kw_args)
    # >>> connector
        


class Resistor(ConductingEquipment):
    """ Resistor, typically used in filter configurations or as earthing resistor for transformers.  Used for electrical model of distribution networks.
    """
    # <<< resistor
    # @generated
    def __init__(self, resistor_type_asset=None, resistor_asset=None, **kw_args):
        """ Initialises a new 'Resistor' instance.
        """
        
        self._resistor_type_asset = None
        self.resistor_type_asset = resistor_type_asset
        self._resistor_asset = None
        self.resistor_asset = resistor_asset

        super(Resistor, self).__init__(**kw_args)
    # >>> resistor
        
    # <<< resistor_type_asset
    # @generated
    def get_resistor_type_asset(self):
        """ 
        """
        return self._resistor_type_asset

    def set_resistor_type_asset(self, value):
        if self._resistor_type_asset is not None:
            filtered = [x for x in self.resistor_type_asset.resistors if x != self]
            self._resistor_type_asset._resistors = filtered
            
        self._resistor_type_asset = value
        if self._resistor_type_asset is not None:
            if self not in self._resistor_type_asset._resistors:
                self._resistor_type_asset._resistors.append(self)

    resistor_type_asset = property(get_resistor_type_asset, set_resistor_type_asset)
    # >>> resistor_type_asset

    # <<< resistor_asset
    # @generated
    def get_resistor_asset(self):
        """ 
        """
        return self._resistor_asset

    def set_resistor_asset(self, value):
        if self._resistor_asset is not None:
            self._resistor_asset._resistor = None
            
        self._resistor_asset = value
        if self._resistor_asset is not None:
            self._resistor_asset._resistor = self
            
    resistor_asset = property(get_resistor_asset, set_resistor_asset)
    # >>> resistor_asset



class CompositeSwitch(Equipment):
    """ A model of a set of individual Switches normally enclosed within the same cabinet and possibly with interlocks that restrict the combination of switch positions. These are typically found in medium voltage distribution networks.  A CompositeSwitch could represent a Ring-Main-Unit (RMU), or pad-mounted switchgear, with primitive internal devices such as an internal bus-bar plus 3 or 4 internal switches each of which may individually be open or closed. A CompositeSwitch and a set of contained Switches can also be used to represent a multi-position switch e.g. a switch that can connect a circuit to Ground, Open or Busbar.
    """
    # <<< composite_switch
    # @generated
    def __init__(self, composite_switch_type='', switches=None, **kw_args):
        """ Initialises a new 'CompositeSwitch' instance.
        """
        # An alphanumeric code that can be used as a reference to extar information such as the description of the interlocking scheme if any 
        self.composite_switch_type = composite_switch_type
        
        self._switches = []
        if switches is None:
            self.switches = []
        else:
            self.switches = switches

        super(CompositeSwitch, self).__init__(**kw_args)
    # >>> composite_switch
        
    # <<< switches
    # @generated
    def get_switches(self):
        """ Switches contained in this Composite switch.
        """
        return self._switches

    def set_switches(self, value):
        for x in self._switches:
            x.composite_switch = None
        for y in value:
            y.composite_switch = self
        self._switches = value
            
    switches = property(get_switches, set_switches)
    
    def add_switches(self, *switches):
        for obj in switches:
            obj._composite_switch = self
            if obj not in self._switches:
                self._switches.append(obj)
        
    def remove_switches(self, *switches):
        for obj in switches:
            obj._composite_switch = None
            self._switches.remove(obj)
    # >>> switches



class WindingTest(IdentifiedObject):
    """ Physical winding test data for the winding/tap pairs of a transformer (or phase shifter). This test data can be used to derive other attributes of specific transformer or phase shifter models.
    """
    # <<< winding_test
    # @generated
    def __init__(self, voltage=0.0, exciting_current=0.0, no_load_loss=0.0, leakage_impedance=0.0, load_loss=0.0, to_tap_step=0, phase_shift=0.0, from_tap_step=0, from_transformer_winding=None, to_transformer_winding=None, transformer_observations=None, **kw_args):
        """ Initialises a new 'WindingTest' instance.
        """
        # The voltage measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
        self.voltage = voltage
        # The exciting current on open-circuit test, expressed as a percentage of rated current, at nominal voltage 
        self.exciting_current = exciting_current
        # The no load loss kW 'to' winding open-circuited) from the test report. 
        self.no_load_loss = no_load_loss
        # The leakage impedance measured at the 'from' winding  with the 'to' winding short-circuited and all other windings open-circuited.  Leakage impedance is expressed in units based on the apparent power and voltage ratings of the 'from' winding. 
        self.leakage_impedance = leakage_impedance
        # The load loss kW ('to' winding short-circuited) from the test report. 
        self.load_loss = load_loss
        # The tap step number for the 'to' winding of the test pair. 
        self.to_tap_step = to_tap_step
        # The phase shift measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
        self.phase_shift = phase_shift
        # The tap step number for the 'from' winding of the test pair. 
        self.from_tap_step = from_tap_step
        
        self._from_transformer_winding = None
        self.from_transformer_winding = from_transformer_winding
        self._to_transformer_winding = None
        self.to_transformer_winding = to_transformer_winding
        self._transformer_observations = []
        if transformer_observations is None:
            self.transformer_observations = []
        else:
            self.transformer_observations = transformer_observations

        super(WindingTest, self).__init__(**kw_args)
    # >>> winding_test
        
    # <<< from_transformer_winding
    # @generated
    def get_from_transformer_winding(self):
        """ The winding from which the test was conducted
        """
        return self._from_transformer_winding

    def set_from_transformer_winding(self, value):
        if self._from_transformer_winding is not None:
            filtered = [x for x in self.from_transformer_winding.from_winding_test if x != self]
            self._from_transformer_winding._from_winding_test = filtered
            
        self._from_transformer_winding = value
        if self._from_transformer_winding is not None:
            if self not in self._from_transformer_winding._from_winding_test:
                self._from_transformer_winding._from_winding_test.append(self)

    from_transformer_winding = property(get_from_transformer_winding, set_from_transformer_winding)
    # >>> from_transformer_winding

    # <<< to_transformer_winding
    # @generated
    def get_to_transformer_winding(self):
        """ The winding to which the test was conducted.  Note that although the 'from' side of the test is required, the 'to' side of a test is not always required.
        """
        return self._to_transformer_winding

    def set_to_transformer_winding(self, value):
        if self._to_transformer_winding is not None:
            filtered = [x for x in self.to_transformer_winding.to_winding_test if x != self]
            self._to_transformer_winding._to_winding_test = filtered
            
        self._to_transformer_winding = value
        if self._to_transformer_winding is not None:
            if self not in self._to_transformer_winding._to_winding_test:
                self._to_transformer_winding._to_winding_test.append(self)

    to_transformer_winding = property(get_to_transformer_winding, set_to_transformer_winding)
    # >>> to_transformer_winding

    # <<< transformer_observations
    # @generated
    def get_transformer_observations(self):
        """ 
        """
        return self._transformer_observations

    def set_transformer_observations(self, value):
        for p in self._transformer_observations:
            filtered = [q for q in p.winding_tests if q != self]
            self._transformer_observations._winding_tests = filtered
        for r in value:
            if self not in r._winding_tests:
                r._winding_tests.append(self)
        self._transformer_observations = value
            
    transformer_observations = property(get_transformer_observations, set_transformer_observations)
    
    def add_transformer_observations(self, *transformer_observations):
        for obj in transformer_observations:
            if self not in obj._winding_tests:
                obj._winding_tests.append(self)
            self._transformer_observations.append(obj)
        
    def remove_transformer_observations(self, *transformer_observations):
        for obj in transformer_observations:
            if self in obj._winding_tests:
                obj._winding_tests.remove(self)
            self._transformer_observations.remove(obj)
    # >>> transformer_observations



class TapChanger(PowerSystemResource):
    """ Mechanism for changing transformer winding tap positions.
    """
    # <<< tap_changer
    # @generated
    def __init__(self, normal_step=0, low_step=0, step_voltage_increment=0.0, high_step=0, initial_delay=0.0, type='phase_control', tcul_control_mode='local', subsequent_delay=0.0, neutral_step=0, neutral_u=0.0, sv_tap_step=None, regulating_control=None, **kw_args):
        """ Initialises a new 'TapChanger' instance.
        """
        # The tap step position used in 'normal' network operation for this winding. For a 'Fixed' tap changer indicates the current physical tap setting. 
        self.normal_step = normal_step
        # Lowest possible tap step position, retard from neutral 
        self.low_step = low_step
        # Tap step increment, in per cent of nominal voltage, per step position. 
        self.step_voltage_increment = step_voltage_increment
        # Highest possible tap step position, advance from neutral 
        self.high_step = high_step
        # For an LTC, the delay for initial tap changer operation (first step change) 
        self.initial_delay = initial_delay
        # The type of tap changer. Indicates the ability of the transformer to perform various regulation tasks. The tap changer must be also be associated wtih a RegulationControl object before any regulation is possible. Values are: "phase_control", "fixed", "voltage_control", "voltage_and_phase_control"
        self.type = type
        # For an LTC, the tap changer control mode. Values are: "local", "off", "volt", "active", "reactive"
        self.tcul_control_mode = tcul_control_mode
        # For an LTC, the delay for subsequent tap changer operation (second and later step changes) 
        self.subsequent_delay = subsequent_delay
        # The neutral tap step position for this winding. 
        self.neutral_step = neutral_step
        # Voltage at which the winding operates at the neutral tap setting. 
        self.neutral_u = neutral_u
        
        self._sv_tap_step = None
        self.sv_tap_step = sv_tap_step
        self._regulating_control = None
        self.regulating_control = regulating_control

        super(TapChanger, self).__init__(**kw_args)
    # >>> tap_changer
        
    # <<< sv_tap_step
    # @generated
    def get_sv_tap_step(self):
        """ The tap step state associated with the tap changer.
        """
        return self._sv_tap_step

    def set_sv_tap_step(self, value):
        if self._sv_tap_step is not None:
            self._sv_tap_step._tap_changer = None
            
        self._sv_tap_step = value
        if self._sv_tap_step is not None:
            self._sv_tap_step._tap_changer = self
            
    sv_tap_step = property(get_sv_tap_step, set_sv_tap_step)
    # >>> sv_tap_step

    # <<< regulating_control
    # @generated
    def get_regulating_control(self):
        """ 
        """
        return self._regulating_control

    def set_regulating_control(self, value):
        if self._regulating_control is not None:
            filtered = [x for x in self.regulating_control.tap_changer if x != self]
            self._regulating_control._tap_changer = filtered
            
        self._regulating_control = value
        if self._regulating_control is not None:
            if self not in self._regulating_control._tap_changer:
                self._regulating_control._tap_changer.append(self)

    regulating_control = property(get_regulating_control, set_regulating_control)
    # >>> regulating_control



class HeatExchanger(Equipment):
    """ Equipment for the cooling of electrical equipment and the extraction of heat
    """
    # <<< heat_exchanger
    # @generated
    def __init__(self, power_transformer=None, **kw_args):
        """ Initialises a new 'HeatExchanger' instance.
        """
        
        self._power_transformer = None
        self.power_transformer = power_transformer

        super(HeatExchanger, self).__init__(**kw_args)
    # >>> heat_exchanger
        
    # <<< power_transformer
    # @generated
    def get_power_transformer(self):
        """ A transformer may have a heat exchanger
        """
        return self._power_transformer

    def set_power_transformer(self, value):
        if self._power_transformer is not None:
            self._power_transformer._heat_exchanger = None
            
        self._power_transformer = value
        if self._power_transformer is not None:
            self._power_transformer._heat_exchanger = self
            
    power_transformer = property(get_power_transformer, set_power_transformer)
    # >>> power_transformer



class MutualCoupling(IdentifiedObject):
    """ This class represents the zero sequence line mutual coupling.
    """
    # <<< mutual_coupling
    # @generated
    def __init__(self, b0ch=0.0, g0ch=0.0, r0=0.0, x0=0.0, first_terminal=None, second_terminal=None, **kw_args):
        """ Initialises a new 'MutualCoupling' instance.
        """
        # Zero sequence mutual coupling shunt (charging) susceptance, uniformly distributed, of the entire line section. 
        self.b0ch = b0ch
        # Zero sequence mutual coupling shunt (charging) conductance, uniformly distributed, of the entire line section. 
        self.g0ch = g0ch
        # Zero sequence branch-to-branch mutual impedance coupling, resistance 
        self.r0 = r0
        # Zero sequence branch-to-branch mutual impedance coupling, reactance 
        self.x0 = x0
        
        self._first_terminal = None
        self.first_terminal = first_terminal
        self._second_terminal = None
        self.second_terminal = second_terminal

        super(MutualCoupling, self).__init__(**kw_args)
    # >>> mutual_coupling
        
    # <<< first_terminal
    # @generated
    def get_first_terminal(self):
        """ The starting terminal for the calculation of distances along the first branch of the mutual coupling.  Normally MutualCoupling would only be used for terminals of AC line segments.  The first and second terminals of a mutual coupling should point to different AC line segments.
        """
        return self._first_terminal

    def set_first_terminal(self, value):
        if self._first_terminal is not None:
            filtered = [x for x in self.first_terminal.has_first_mutual_coupling if x != self]
            self._first_terminal._has_first_mutual_coupling = filtered
            
        self._first_terminal = value
        if self._first_terminal is not None:
            if self not in self._first_terminal._has_first_mutual_coupling:
                self._first_terminal._has_first_mutual_coupling.append(self)

    first_terminal = property(get_first_terminal, set_first_terminal)
    # >>> first_terminal

    # <<< second_terminal
    # @generated
    def get_second_terminal(self):
        """ The starting terminal for the calculation of distances along the second branch of the mutual coupling.
        """
        return self._second_terminal

    def set_second_terminal(self, value):
        if self._second_terminal is not None:
            filtered = [x for x in self.second_terminal.has_second_mutual_coupling if x != self]
            self._second_terminal._has_second_mutual_coupling = filtered
            
        self._second_terminal = value
        if self._second_terminal is not None:
            if self not in self._second_terminal._has_second_mutual_coupling:
                self._second_terminal._has_second_mutual_coupling.append(self)

    second_terminal = property(get_second_terminal, set_second_terminal)
    # >>> second_terminal



class EnergySource(ConductingEquipment):
    """ A generic equivalent for an energy supplier on a transmission or distribution voltage level.
    """
    # <<< energy_source
    # @generated
    def __init__(self, voltage_angle=0.0, voltage_magnitude=0.0, r0=0.0, xn=0.0, x=0.0, nominal_voltage=0.0, rn=0.0, x0=0.0, active_power=0.0, r=0.0, **kw_args):
        """ Initialises a new 'EnergySource' instance.
        """
        # Phase angle of a-phase open circuit. 
        self.voltage_angle = voltage_angle
        # Phase-to-phase open circuit voltage magnitude. 
        self.voltage_magnitude = voltage_magnitude
        # Zero sequence Thevenin resistance. 
        self.r0 = r0
        # Negative sequence Thevenin reactance. 
        self.xn = xn
        # Positive sequence Thevenin reactance. 
        self.x = x
        # Phase-to-phase nominal voltage. 
        self.nominal_voltage = nominal_voltage
        # Negative sequence Thevenin resistance. 
        self.rn = rn
        # Zero sequence Thevenin reactance. 
        self.x0 = x0
        # High voltage source load 
        self.active_power = active_power
        # Positive sequence Thevenin resistance. 
        self.r = r
        

        super(EnergySource, self).__init__(**kw_args)
    # >>> energy_source
        


class VoltageControlZone(PowerSystemResource):
    """ An area of the power system network which is defined for secondary voltage control purposes. A voltage control zone consists of a collection of substations with a designated bus bar section whose voltage will be controlled.
    """
    # <<< voltage_control_zone
    # @generated
    def __init__(self, regulation_schedule=None, busbar_section=None, **kw_args):
        """ Initialises a new 'VoltageControlZone' instance.
        """
        
        self._regulation_schedule = None
        self.regulation_schedule = regulation_schedule
        self._busbar_section = None
        self.busbar_section = busbar_section

        super(VoltageControlZone, self).__init__(**kw_args)
    # >>> voltage_control_zone
        
    # <<< regulation_schedule
    # @generated
    def get_regulation_schedule(self):
        """ A VoltageControlZone may have a  voltage regulation schedule.
        """
        return self._regulation_schedule

    def set_regulation_schedule(self, value):
        if self._regulation_schedule is not None:
            filtered = [x for x in self.regulation_schedule.voltage_control_zones if x != self]
            self._regulation_schedule._voltage_control_zones = filtered
            
        self._regulation_schedule = value
        if self._regulation_schedule is not None:
            if self not in self._regulation_schedule._voltage_control_zones:
                self._regulation_schedule._voltage_control_zones.append(self)

    regulation_schedule = property(get_regulation_schedule, set_regulation_schedule)
    # >>> regulation_schedule

    # <<< busbar_section
    # @generated
    def get_busbar_section(self):
        """ A VoltageControlZone is controlled by a designated BusbarSection.
        """
        return self._busbar_section

    def set_busbar_section(self, value):
        if self._busbar_section is not None:
            self._busbar_section._voltage_control_zone = None
            
        self._busbar_section = value
        if self._busbar_section is not None:
            self._busbar_section._voltage_control_zone = self
            
    busbar_section = property(get_busbar_section, set_busbar_section)
    # >>> busbar_section



class Conductor(ConductingEquipment):
    """ Combination of conducting material with consistent electrical characteristics, building a single electrical system, used to carry current between points in the power system.
    """
    # <<< conductor
    # @generated
    def __init__(self, g0ch=0.0, gch=0.0, x=0.0, x0=0.0, bch=0.0, b0ch=0.0, r0=0.0, r=0.0, linear_conductor_type_assets=None, conductor_type=None, linear_conductor_assets=None, **kw_args):
        """ Initialises a new 'Conductor' instance.
        """
        # Zero sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
        self.g0ch = g0ch
        # Positive sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
        self.gch = gch
        # Positive sequence series reactance of the entire line section. 
        self.x = x
        # Zero sequence series reactance of the entire line section. 
        self.x0 = x0
        # Positive sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
        self.bch = bch
        # Zero sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
        self.b0ch = b0ch
        # Zero sequence series resistance of the entire line section. 
        self.r0 = r0
        # Positive sequence series resistance of the entire line section. 
        self.r = r
        
        self._linear_conductor_type_assets = []
        if linear_conductor_type_assets is None:
            self.linear_conductor_type_assets = []
        else:
            self.linear_conductor_type_assets = linear_conductor_type_assets
        self._conductor_type = None
        self.conductor_type = conductor_type
        self._linear_conductor_assets = []
        if linear_conductor_assets is None:
            self.linear_conductor_assets = []
        else:
            self.linear_conductor_assets = linear_conductor_assets

        super(Conductor, self).__init__(**kw_args)
    # >>> conductor
        
    # <<< linear_conductor_type_assets
    # @generated
    def get_linear_conductor_type_assets(self):
        """ 
        """
        return self._linear_conductor_type_assets

    def set_linear_conductor_type_assets(self, value):
        for p in self._linear_conductor_type_assets:
            filtered = [q for q in p.conductors if q != self]
            self._linear_conductor_type_assets._conductors = filtered
        for r in value:
            if self not in r._conductors:
                r._conductors.append(self)
        self._linear_conductor_type_assets = value
            
    linear_conductor_type_assets = property(get_linear_conductor_type_assets, set_linear_conductor_type_assets)
    
    def add_linear_conductor_type_assets(self, *linear_conductor_type_assets):
        for obj in linear_conductor_type_assets:
            if self not in obj._conductors:
                obj._conductors.append(self)
            self._linear_conductor_type_assets.append(obj)
        
    def remove_linear_conductor_type_assets(self, *linear_conductor_type_assets):
        for obj in linear_conductor_type_assets:
            if self in obj._conductors:
                obj._conductors.remove(self)
            self._linear_conductor_type_assets.remove(obj)
    # >>> linear_conductor_type_assets

    # <<< conductor_type
    # @generated
    def get_conductor_type(self):
        """ Conductor type physically describing sections of this conductor.
        """
        return self._conductor_type

    def set_conductor_type(self, value):
        if self._conductor_type is not None:
            filtered = [x for x in self.conductor_type.conductors if x != self]
            self._conductor_type._conductors = filtered
            
        self._conductor_type = value
        if self._conductor_type is not None:
            if self not in self._conductor_type._conductors:
                self._conductor_type._conductors.append(self)

    conductor_type = property(get_conductor_type, set_conductor_type)
    # >>> conductor_type

    # <<< linear_conductor_assets
    # @generated
    def get_linear_conductor_assets(self):
        """ 
        """
        return self._linear_conductor_assets

    def set_linear_conductor_assets(self, value):
        for p in self._linear_conductor_assets:
            filtered = [q for q in p.conductors if q != self]
            self._linear_conductor_assets._conductors = filtered
        for r in value:
            if self not in r._conductors:
                r._conductors.append(self)
        self._linear_conductor_assets = value
            
    linear_conductor_assets = property(get_linear_conductor_assets, set_linear_conductor_assets)
    
    def add_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
            if self not in obj._conductors:
                obj._conductors.append(self)
            self._linear_conductor_assets.append(obj)
        
    def remove_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
            if self in obj._conductors:
                obj._conductors.remove(self)
            self._linear_conductor_assets.remove(obj)
    # >>> linear_conductor_assets



class Plant(EquipmentContainer):
    """ A Plant is a collection of equipment for purposes of generation.
    """
    pass
    # <<< plant
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Plant' instance.
        """
        

        super(Plant, self).__init__(**kw_args)
    # >>> plant
        


class Ground(ConductingEquipment):
    """ A common point for connecting grounded conducting equipment such as shunt capacitors. The power system model can have more than one ground.
    """
    # <<< ground
    # @generated
    def __init__(self, winding_insulations=None, **kw_args):
        """ Initialises a new 'Ground' instance.
        """
        
        self._winding_insulations = []
        if winding_insulations is None:
            self.winding_insulations = []
        else:
            self.winding_insulations = winding_insulations

        super(Ground, self).__init__(**kw_args)
    # >>> ground
        
    # <<< winding_insulations
    # @generated
    def get_winding_insulations(self):
        """ 
        """
        return self._winding_insulations

    def set_winding_insulations(self, value):
        for x in self._winding_insulations:
            x.ground = None
        for y in value:
            y.ground = self
        self._winding_insulations = value
            
    winding_insulations = property(get_winding_insulations, set_winding_insulations)
    
    def add_winding_insulations(self, *winding_insulations):
        for obj in winding_insulations:
            obj._ground = self
            if obj not in self._winding_insulations:
                self._winding_insulations.append(obj)
        
    def remove_winding_insulations(self, *winding_insulations):
        for obj in winding_insulations:
            obj._ground = None
            self._winding_insulations.remove(obj)
    # >>> winding_insulations



class PowerTransformer(Equipment):
    """ An electrical device consisting of  two or more coupled windings, with or without a magnetic core, for introducing mutual coupling between electric circuits. Transformers can be used to control voltage and phase shift (active power flow).
    """
    # <<< power_transformer
    # @generated
    def __init__(self, bmag_sat=0.0, mag_sat_flux=0.0, phases='abn', mag_base_u=0.0, transformer_windings=None, heat_exchanger=None, flowgates=None, **kw_args):
        """ Initialises a new 'PowerTransformer' instance.
        """
        # Core shunt magnetizing susceptance in the saturation region. 
        self.bmag_sat = bmag_sat
        # Core magnetizing saturation curve knee flux level. 
        self.mag_sat_flux = mag_sat_flux
        # Describes the phases carried by a power transformer. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
        self.phases = phases
        # The reference voltage at which the magnetizing saturation measurements were made 
        self.mag_base_u = mag_base_u
        
        self._transformer_windings = []
        if transformer_windings is None:
            self.transformer_windings = []
        else:
            self.transformer_windings = transformer_windings
        self._heat_exchanger = None
        self.heat_exchanger = heat_exchanger
        self._flowgates = []
        if flowgates is None:
            self.flowgates = []
        else:
            self.flowgates = flowgates

        super(PowerTransformer, self).__init__(**kw_args)
    # >>> power_transformer
        
    # <<< transformer_windings
    # @generated
    def get_transformer_windings(self):
        """ A transformer has windings
        """
        return self._transformer_windings

    def set_transformer_windings(self, value):
        for x in self._transformer_windings:
            x.power_transformer = None
        for y in value:
            y.power_transformer = self
        self._transformer_windings = value
            
    transformer_windings = property(get_transformer_windings, set_transformer_windings)
    
    def add_transformer_windings(self, *transformer_windings):
        for obj in transformer_windings:
            obj._power_transformer = self
            if obj not in self._transformer_windings:
                self._transformer_windings.append(obj)
        
    def remove_transformer_windings(self, *transformer_windings):
        for obj in transformer_windings:
            obj._power_transformer = None
            self._transformer_windings.remove(obj)
    # >>> transformer_windings

    # <<< heat_exchanger
    # @generated
    def get_heat_exchanger(self):
        """ A transformer may have a heat exchanger
        """
        return self._heat_exchanger

    def set_heat_exchanger(self, value):
        if self._heat_exchanger is not None:
            self._heat_exchanger._power_transformer = None
            
        self._heat_exchanger = value
        if self._heat_exchanger is not None:
            self._heat_exchanger._power_transformer = self
            
    heat_exchanger = property(get_heat_exchanger, set_heat_exchanger)
    # >>> heat_exchanger

    # <<< flowgates
    # @generated
    def get_flowgates(self):
        """ 
        """
        return self._flowgates

    def set_flowgates(self, value):
        for p in self._flowgates:
            filtered = [q for q in p.power_transormers if q != self]
            self._flowgates._power_transormers = filtered
        for r in value:
            if self not in r._power_transormers:
                r._power_transormers.append(self)
        self._flowgates = value
            
    flowgates = property(get_flowgates, set_flowgates)
    
    def add_flowgates(self, *flowgates):
        for obj in flowgates:
            if self not in obj._power_transormers:
                obj._power_transormers.append(self)
            self._flowgates.append(obj)
        
    def remove_flowgates(self, *flowgates):
        for obj in flowgates:
            if self in obj._power_transormers:
                obj._power_transormers.remove(self)
            self._flowgates.remove(obj)
    # >>> flowgates



class Disconnector(Switch):
    """ A manually operated or motor operated mechanical switching device used for changing the connections in a circuit, or for isolating a circuit or equipment from a source of power. It is required to open or close circuits when negligible current is broken or made.
    """
    pass
    # <<< disconnector
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Disconnector' instance.
        """
        

        super(Disconnector, self).__init__(**kw_args)
    # >>> disconnector
        


class GroundDisconnector(Switch):
    """ A manually operated or motor operated mechanical switching device used for isolating a circuit or equipment from Ground.
    """
    pass
    # <<< ground_disconnector
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GroundDisconnector' instance.
        """
        

        super(GroundDisconnector, self).__init__(**kw_args)
    # >>> ground_disconnector
        


class Jumper(Switch):
    """ A short section of conductor with negligible impedance which can be manually removed and replaced if the circuit is de-energized. Note that zero-impedance branches can be modelled by an ACLineSegment with a zero impedance ConductorType
    """
    pass
    # <<< jumper
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Jumper' instance.
        """
        

        super(Jumper, self).__init__(**kw_args)
    # >>> jumper
        


class BusbarSection(Connector):
    """ A conductor, or group of conductors, with negligible impedance, that serve to connect other conducting equipment within a single substation.  Voltage measurements are typically obtained from VoltageTransformers that are connected to busbar sections. A bus bar section may have many physical terminals but for analysis is modelled with exactly one logical terminal.
    """
    # <<< busbar_section
    # @generated
    def __init__(self, voltage_control_zone=None, **kw_args):
        """ Initialises a new 'BusbarSection' instance.
        """
        
        self._voltage_control_zone = None
        self.voltage_control_zone = voltage_control_zone

        super(BusbarSection, self).__init__(**kw_args)
    # >>> busbar_section
        
    # <<< voltage_control_zone
    # @generated
    def get_voltage_control_zone(self):
        """ A VoltageControlZone is controlled by a designated BusbarSection.
        """
        return self._voltage_control_zone

    def set_voltage_control_zone(self, value):
        if self._voltage_control_zone is not None:
            self._voltage_control_zone._busbar_section = None
            
        self._voltage_control_zone = value
        if self._voltage_control_zone is not None:
            self._voltage_control_zone._busbar_section = self
            
    voltage_control_zone = property(get_voltage_control_zone, set_voltage_control_zone)
    # >>> voltage_control_zone



class SynchronousMachine(RegulatingCondEq):
    """ An electromechanical device that operates synchronously with the network. It is a single machine operating either as a generator or synchronous condenser or pump.
    """
    # <<< synchronous_machine
    # @generated
    def __init__(self, type='generator', max_u=0.0, min_u=0.0, x_direct_subtrans=0.0, coolant_type='water', condenser_p=0.0, x_quad_sync=0.0, r0=0.0, a_vrto_manual_lead=0.0, x0=0.0, reference_priority=0, r=0.0, x_quad_trans=0.0, r2=0.0, base_q=0.0, inertia=0.0, x2=0.0, a_vrto_manual_lag=0.0, x_direct_trans=0.0, q_percent=0.0, coolant_condition=0.0, manual_to_avr=0.0, operating_mode='generator', x_direct_sync=0.0, max_q=0.0, damping=0.0, rated_s=0.0, min_q=0.0, x_quad_subtrans=0.0, x=0.0, initial_reactive_capability_curve=None, hydro_pump=None, reactive_capability_curves=None, generating_unit=None, prime_movers=None, **kw_args):
        """ Initialises a new 'SynchronousMachine' instance.
        """
        # Modes that this synchronous machine can operate in. Values are: "generator", "condenser", "generator_or_condenser"
        self.type = type
        # Maximum voltage limit for the unit. 
        self.max_u = max_u
        # Minimum voltage  limit for the unit. 
        self.min_u = min_u
        # Direct-axis subtransient reactance, also known as X'd. 
        self.x_direct_subtrans = x_direct_subtrans
        # Method of cooling the machine. Values are: "water", "air", "hydrogen_gas"
        self.coolant_type = coolant_type
        # Active power consumed when in condenser mode operation. 
        self.condenser_p = condenser_p
        # Quadrature-axis synchronous reactance (Xq) , the ratio of the component of reactive armature voltage, due to the quadrature-axis component of armature current, to this component of current, under steady state conditions and at rated frequency. 
        self.x_quad_sync = x_quad_sync
        # Zero sequence resistance of the synchronous machine. 
        self.r0 = r0
        # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a leading MVAr violation. 
        self.a_vrto_manual_lead = a_vrto_manual_lead
        # Zero sequence reactance of the synchronous machine. 
        self.x0 = x0
        # Priority of unit for reference bus selection. 0 = don t care (default) 1 = highest priority. 2 is less than 1 and so on. 
        self.reference_priority = reference_priority
        # Positive sequence resistance of the synchronous machine. 
        self.r = r
        # Quadrature-axis transient reactance, also known as X'q. 
        self.x_quad_trans = x_quad_trans
        # Negative sequence resistance. 
        self.r2 = r2
        # Default base reactive power value. This value represents the initial reactive power that can be used by any application function. 
        self.base_q = base_q
        # The energy stored in the rotor when operating at rated speed. This value is used in the accelerating power reference frame for  operator training simulator solutions. 
        self.inertia = inertia
        # Negative sequence reactance. 
        self.x2 = x2
        # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a lagging MVAr violation. 
        self.a_vrto_manual_lag = a_vrto_manual_lag
        # Direct-axis transient reactance, also known as X'd. 
        self.x_direct_trans = x_direct_trans
        # Percent of the coordinated reactive control that comes from this machine. 
        self.q_percent = q_percent
        # Temperature or pressure of coolant medium 
        self.coolant_condition = coolant_condition
        # Time delay required when switching from Manual to Automatic Voltage Regulation. This value is used in the accelerating power reference frame for powerflow solutions 
        self.manual_to_avr = manual_to_avr
        # Current mode of operation. Values are: "generator", "condenser"
        self.operating_mode = operating_mode
        # Direct-axis synchronous reactance. The quotient of a sustained value of that AC component of armature voltage that is produced by the total direct-axis flux due to direct-axis armature current and the value of the AC component of this current, the machine running at rated speed. (Xd) 
        self.x_direct_sync = x_direct_sync
        # Maximum reactive power limit. This is the maximum (nameplate) limit for the unit. 
        self.max_q = max_q
        # Damping torque coefficient, a proportionality constant that, when multiplied by the angular velocity of the rotor poles with respect to the magnetic field (frequency), results in the damping torque. 
        self.damping = damping
        # Nameplate apparent power rating for the unit 
        self.rated_s = rated_s
        # Minimum reactive power limit for the unit. 
        self.min_q = min_q
        # Quadrature-axis subtransient reactance, also known as X'q. 
        self.x_quad_subtrans = x_quad_subtrans
        # Positive sequence reactance of the synchronous machine. 
        self.x = x
        
        self._initial_reactive_capability_curve = None
        self.initial_reactive_capability_curve = initial_reactive_capability_curve
        self._hydro_pump = None
        self.hydro_pump = hydro_pump
        self._reactive_capability_curves = []
        if reactive_capability_curves is None:
            self.reactive_capability_curves = []
        else:
            self.reactive_capability_curves = reactive_capability_curves
        self._generating_unit = None
        self.generating_unit = generating_unit
        self._prime_movers = []
        if prime_movers is None:
            self.prime_movers = []
        else:
            self.prime_movers = prime_movers

        super(SynchronousMachine, self).__init__(**kw_args)
    # >>> synchronous_machine
        
    # <<< initial_reactive_capability_curve
    # @generated
    def get_initial_reactive_capability_curve(self):
        """ The default ReactiveCapabilityCurve for use by a SynchronousMachine
        """
        return self._initial_reactive_capability_curve

    def set_initial_reactive_capability_curve(self, value):
        if self._initial_reactive_capability_curve is not None:
            filtered = [x for x in self.initial_reactive_capability_curve.initially_used_by_synchronous_machines if x != self]
            self._initial_reactive_capability_curve._initially_used_by_synchronous_machines = filtered
            
        self._initial_reactive_capability_curve = value
        if self._initial_reactive_capability_curve is not None:
            if self not in self._initial_reactive_capability_curve._initially_used_by_synchronous_machines:
                self._initial_reactive_capability_curve._initially_used_by_synchronous_machines.append(self)

    initial_reactive_capability_curve = property(get_initial_reactive_capability_curve, set_initial_reactive_capability_curve)
    # >>> initial_reactive_capability_curve

    # <<< hydro_pump
    # @generated
    def get_hydro_pump(self):
        """ The synchronous machine drives the turbine which moves the water from a low elevation to a higher elevation. The direction of machine rotation for pumping may or may not be the same as for generating.
        """
        return self._hydro_pump

    def set_hydro_pump(self, value):
        if self._hydro_pump is not None:
            self._hydro_pump._synchronous_machine = None
            
        self._hydro_pump = value
        if self._hydro_pump is not None:
            self._hydro_pump._synchronous_machine = self
            
    hydro_pump = property(get_hydro_pump, set_hydro_pump)
    # >>> hydro_pump

    # <<< reactive_capability_curves
    # @generated
    def get_reactive_capability_curves(self):
        """ All available Reactive capability curves for this SynchronousMachine.
        """
        return self._reactive_capability_curves

    def set_reactive_capability_curves(self, value):
        for p in self._reactive_capability_curves:
            filtered = [q for q in p.synchronous_machines if q != self]
            self._reactive_capability_curves._synchronous_machines = filtered
        for r in value:
            if self not in r._synchronous_machines:
                r._synchronous_machines.append(self)
        self._reactive_capability_curves = value
            
    reactive_capability_curves = property(get_reactive_capability_curves, set_reactive_capability_curves)
    
    def add_reactive_capability_curves(self, *reactive_capability_curves):
        for obj in reactive_capability_curves:
            if self not in obj._synchronous_machines:
                obj._synchronous_machines.append(self)
            self._reactive_capability_curves.append(obj)
        
    def remove_reactive_capability_curves(self, *reactive_capability_curves):
        for obj in reactive_capability_curves:
            if self in obj._synchronous_machines:
                obj._synchronous_machines.remove(self)
            self._reactive_capability_curves.remove(obj)
    # >>> reactive_capability_curves

    # <<< generating_unit
    # @generated
    def get_generating_unit(self):
        """ A synchronous machine may operate as a generator and as such becomes a member of a generating unit
        """
        return self._generating_unit

    def set_generating_unit(self, value):
        if self._generating_unit is not None:
            filtered = [x for x in self.generating_unit.synchronous_machines if x != self]
            self._generating_unit._synchronous_machines = filtered
            
        self._generating_unit = value
        if self._generating_unit is not None:
            if self not in self._generating_unit._synchronous_machines:
                self._generating_unit._synchronous_machines.append(self)

    generating_unit = property(get_generating_unit, set_generating_unit)
    # >>> generating_unit

    # <<< prime_movers
    # @generated
    def get_prime_movers(self):
        """ Prime movers that drive this SynchronousMachine.
        """
        return self._prime_movers

    def set_prime_movers(self, value):
        for p in self._prime_movers:
            filtered = [q for q in p.synchronous_machines if q != self]
            self._prime_movers._synchronous_machines = filtered
        for r in value:
            if self not in r._synchronous_machines:
                r._synchronous_machines.append(self)
        self._prime_movers = value
            
    prime_movers = property(get_prime_movers, set_prime_movers)
    
    def add_prime_movers(self, *prime_movers):
        for obj in prime_movers:
            if self not in obj._synchronous_machines:
                obj._synchronous_machines.append(self)
            self._prime_movers.append(obj)
        
    def remove_prime_movers(self, *prime_movers):
        for obj in prime_movers:
            if self in obj._synchronous_machines:
                obj._synchronous_machines.remove(self)
            self._prime_movers.remove(obj)
    # >>> prime_movers



class StaticVarCompensator(RegulatingCondEq):
    """ A facility for providing variable and controllable shunt reactive power. The SVC typically consists of a stepdown transformer, filter, thyristor-controlled reactor, and thyristor-switched capacitor arms.  The SVC may operate in fixed MVar output mode or in voltage control mode.  When in voltage control mode, the output of the SVC will be proportional to the deviation of voltage at the controlled bus from the voltage setpoint.  The SVC characteristic slope defines the proportion.  If the voltage at the controlled bus is equal to the voltage setpoint, the SVC MVar output is zero.
    """
    # <<< static_var_compensator
    # @generated
    def __init__(self, capacitive_rating=0.0, voltage_set_point=0.0, s_vccontrol_mode='off', slope=0.0, inductive_rating=0.0, **kw_args):
        """ Initialises a new 'StaticVarCompensator' instance.
        """
        # Maximum available capacitive reactive power 
        self.capacitive_rating = capacitive_rating
        # The reactive power output of the SVC is proportional to the difference between the voltage at the regulated bus and the voltage setpoint.  When the regulated bus voltage is equal to the voltage setpoint, the reactive power output is zero. 
        self.voltage_set_point = voltage_set_point
        # SVC control mode. Values are: "off", "reactive_power", "voltage"
        self.s_vccontrol_mode = s_vccontrol_mode
        # The characteristics slope of an SVC defines how the reactive power output changes in proportion to the difference between the regulated bus voltage and the voltage setpoint. 
        self.slope = slope
        # Maximum available inductive reactive power 
        self.inductive_rating = inductive_rating
        

        super(StaticVarCompensator, self).__init__(**kw_args)
    # >>> static_var_compensator
        


class RatioTapChanger(TapChanger):
    """ A tap changer that changes the voltage ratio impacting the voltage magnitude but not direclty the phase angle across the transformer..
    """
    # <<< ratio_tap_changer
    # @generated
    def __init__(self, transformer_winding=None, **kw_args):
        """ Initialises a new 'RatioTapChanger' instance.
        """
        
        self._transformer_winding = None
        self.transformer_winding = transformer_winding

        super(RatioTapChanger, self).__init__(**kw_args)
    # >>> ratio_tap_changer
        
    # <<< transformer_winding
    # @generated
    def get_transformer_winding(self):
        """ The transformer winding to which the ratio tap changer belongs.
        """
        return self._transformer_winding

    def set_transformer_winding(self, value):
        if self._transformer_winding is not None:
            self._transformer_winding._ratio_tap_changer = None
            
        self._transformer_winding = value
        if self._transformer_winding is not None:
            self._transformer_winding._ratio_tap_changer = self
            
    transformer_winding = property(get_transformer_winding, set_transformer_winding)
    # >>> transformer_winding



class DCLineSegment(Conductor):
    """ A wire or combination of wires not insulated from one another, with consistent electrical characteristics, used to carry direct current between points in the DC region of the power system.
    """
    # <<< dcline_segment
    # @generated
    def __init__(self, dc_segment_resistance=0.0, dc_segment_inductance=0.0, **kw_args):
        """ Initialises a new 'DCLineSegment' instance.
        """
        # Resistance of the DC line segment. 
        self.dc_segment_resistance = dc_segment_resistance
        # Inductance of the DC line segment. 
        self.dc_segment_inductance = dc_segment_inductance
        

        super(DCLineSegment, self).__init__(**kw_args)
    # >>> dcline_segment
        


class FrequencyConverter(RegulatingCondEq):
    """ A device to convert from one frequency to another (e.g., frequency F1 to F2) comprises a pair of FrequencyConverter instances. One converts from F1 to DC, the other converts the DC to F2.
    """
    # <<< frequency_converter
    # @generated
    def __init__(self, max_p=0.0, frequency=0.0, min_p=0.0, operating_mode='', min_u=0.0, max_u=0.0, **kw_args):
        """ Initialises a new 'FrequencyConverter' instance.
        """
        # The maximum active power on the DC side at which the frequence converter should operate. 
        self.max_p = max_p
        # Frequency on the AC side. 
        self.frequency = frequency
        # The minimum active power on the DC side at which the frequence converter should operate. 
        self.min_p = min_p
        # Operating mode for the frequency converter 
        self.operating_mode = operating_mode
        # The minimum voltage on the DC side at which the frequency converter should operate. 
        self.min_u = min_u
        # The maximum voltage on the DC side at which the frequency converter should operate. 
        self.max_u = max_u
        

        super(FrequencyConverter, self).__init__(**kw_args)
    # >>> frequency_converter
        


class ACLineSegment(Conductor):
    """ A wire or combination of wires, with consistent electrical characteristics, building a single electrical system, used to carry alternating current between points in the power system.
    """
    pass
    # <<< acline_segment
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ACLineSegment' instance.
        """
        

        super(ACLineSegment, self).__init__(**kw_args)
    # >>> acline_segment
        


class ShuntCompensator(RegulatingCondEq):
    """ A shunt capacitor or reactor or switchable bank of shunt capacitors or reactors. A section of a shunt compensator is an individual capacitor or reactor.  A negative value for reactivePerSection indicates that the compensator is a reactor. ShuntCompensator is a single terminal device.  Ground is implied.
    """
    # <<< shunt_compensator
    # @generated
    def __init__(self, nom_q=0.0, max_u=0.0, a_vrdelay=0.0, nom_u=0.0, voltage_sensitivity=0.0, reactive_per_section=0.0, maximum_sections=0, switch_on_date='', normal_sections=0, b0_per_section=0.0, min_u=0.0, g0_per_section=0.0, y_per_section=0.0, switch_on_count=0, sv_shunt_compensator_sections=None, **kw_args):
        """ Initialises a new 'ShuntCompensator' instance.
        """
        # Nominal reactive power output of the capacitor bank at the nominal voltage. This number should be positive. 
        self.nom_q = nom_q
        # The maximum voltage at which the capacitor bank should operate. 
        self.max_u = max_u
        # Time delay required for the device to be connected or disconnected by automatic voltage regulation (AVR). 
        self.a_vrdelay = a_vrdelay
        # The nominal voltage at which the nominal reactive power was measured. This should normally be within 10% of the voltage at which the capacitor is connected to the network. 
        self.nom_u = nom_u
        # Voltage sensitivity required for the device to regulate the bus voltage, in voltage/reactive power. 
        self.voltage_sensitivity = voltage_sensitivity
        # For a capacitor bank, the size in reactive power of each switchable section at the nominal voltage. 
        self.reactive_per_section = reactive_per_section
        # For a capacitor bank, the maximum number of sections that may be switched in. 
        self.maximum_sections = maximum_sections
        # The date and time when the capacitor bank was last switched on. 
        self.switch_on_date = switch_on_date
        # For a capacitor bank, the normal number of sections switched in. This number should correspond to the nominal reactive power (nomQ). 
        self.normal_sections = normal_sections
        # Zero sequence shunt (charging) susceptance per section 
        self.b0_per_section = b0_per_section
        # The minimum voltage at which the capacitor bank should operate. 
        self.min_u = min_u
        # Zero sequence shunt (charging) conductance per section 
        self.g0_per_section = g0_per_section
        # For a capacitor bank, the admittance of each switchable section. Calculated using the reactive power per section and corrected for network voltage. 
        self.y_per_section = y_per_section
        # The switch on count since the capacitor count was last reset or initialized. 
        self.switch_on_count = switch_on_count
        
        self._sv_shunt_compensator_sections = None
        self.sv_shunt_compensator_sections = sv_shunt_compensator_sections

        super(ShuntCompensator, self).__init__(**kw_args)
    # >>> shunt_compensator
        
    # <<< sv_shunt_compensator_sections
    # @generated
    def get_sv_shunt_compensator_sections(self):
        """ The state for the number of shunt compensator sections in service.
        """
        return self._sv_shunt_compensator_sections

    def set_sv_shunt_compensator_sections(self, value):
        if self._sv_shunt_compensator_sections is not None:
            self._sv_shunt_compensator_sections._shunt_compensator = None
            
        self._sv_shunt_compensator_sections = value
        if self._sv_shunt_compensator_sections is not None:
            self._sv_shunt_compensator_sections._shunt_compensator = self
            
    sv_shunt_compensator_sections = property(get_sv_shunt_compensator_sections, set_sv_shunt_compensator_sections)
    # >>> sv_shunt_compensator_sections



class ProtectedSwitch(Switch):
    """ A ProtectedSwitch is a switching device that can be operated by ProtectionEquipment.
    """
    # <<< protected_switch
    # @generated
    def __init__(self, reclose_sequences=None, protection_equipments=None, **kw_args):
        """ Initialises a new 'ProtectedSwitch' instance.
        """
        
        self._reclose_sequences = []
        if reclose_sequences is None:
            self.reclose_sequences = []
        else:
            self.reclose_sequences = reclose_sequences
        self._protection_equipments = []
        if protection_equipments is None:
            self.protection_equipments = []
        else:
            self.protection_equipments = protection_equipments

        super(ProtectedSwitch, self).__init__(**kw_args)
    # >>> protected_switch
        
    # <<< reclose_sequences
    # @generated
    def get_reclose_sequences(self):
        """ A breaker may have zero or more automatic reclosures after a trip occurs.
        """
        return self._reclose_sequences

    def set_reclose_sequences(self, value):
        for x in self._reclose_sequences:
            x.protected_switch = None
        for y in value:
            y.protected_switch = self
        self._reclose_sequences = value
            
    reclose_sequences = property(get_reclose_sequences, set_reclose_sequences)
    
    def add_reclose_sequences(self, *reclose_sequences):
        for obj in reclose_sequences:
            obj._protected_switch = self
            if obj not in self._reclose_sequences:
                self._reclose_sequences.append(obj)
        
    def remove_reclose_sequences(self, *reclose_sequences):
        for obj in reclose_sequences:
            obj._protected_switch = None
            self._reclose_sequences.remove(obj)
    # >>> reclose_sequences

    # <<< protection_equipments
    # @generated
    def get_protection_equipments(self):
        """ Protection equipments that operate this ProtectedSwitch.
        """
        return self._protection_equipments

    def set_protection_equipments(self, value):
        for p in self._protection_equipments:
            filtered = [q for q in p.protected_switches if q != self]
            self._protection_equipments._protected_switches = filtered
        for r in value:
            if self not in r._protected_switches:
                r._protected_switches.append(self)
        self._protection_equipments = value
            
    protection_equipments = property(get_protection_equipments, set_protection_equipments)
    
    def add_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            if self not in obj._protected_switches:
                obj._protected_switches.append(self)
            self._protection_equipments.append(obj)
        
    def remove_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            if self in obj._protected_switches:
                obj._protected_switches.remove(self)
            self._protection_equipments.remove(obj)
    # >>> protection_equipments



class Fuse(Switch):
    """ An overcurrent protective device with a circuit opening fusible part that is heated and severed by the passage of overcurrent through it. A fuse is considered a switching device because it breaks current.
    """
    # <<< fuse
    # @generated
    def __init__(self, amp_rating=0.0, **kw_args):
        """ Initialises a new 'Fuse' instance.
        """
        # Fault interrupting current rating. 
        self.amp_rating = amp_rating
        

        super(Fuse, self).__init__(**kw_args)
    # >>> fuse
        


class PhaseTapChanger(TapChanger):
    """ A specialization of a voltage tap changer that has detailed modeling for phase shifting capabilities.   A phase shifting tap changer is also in general a voltage magnitude transformer.    The symmetrical and asymmetrical transformer tap changer models are defined here.
    """
    # <<< phase_tap_changer
    # @generated
    def __init__(self, nominal_voltage_out_of_phase=0.0, x_step_max=0.0, phase_tap_changer_type='unknown', x_step_min=0.0, winding_connection_angle=0.0, voltage_step_increment_out_of_phase=0.0, step_phase_shift_increment=0.0, transformer_winding=None, **kw_args):
        """ Initialises a new 'PhaseTapChanger' instance.
        """
        # Similar to TapChanger.nominalVoltage, but this is the nominal voltage in the out of phase winding at the nominal tap step. A typical case may have zero voltage at the nominal step, indicating no phase shift at the nominal voltage. 
        self.nominal_voltage_out_of_phase = nominal_voltage_out_of_phase
        # The reactance at the maximum tap step. 
        self.x_step_max = x_step_max
        # The type of phase shifter construction. Values are: "unknown", "asymmetrical", "symmetrical"
        self.phase_tap_changer_type = phase_tap_changer_type
        # The reactance at the minimum tap step. 
        self.x_step_min = x_step_min
        # The phase angle between the in-phase winding and the out-of -phase winding used for creating phase shift.   It is only possible to have a symmemtrical transformer if this angle is 90 degrees. 
        self.winding_connection_angle = winding_connection_angle
        # The voltage step increment on the out of phase winding.    This voltage step on the out of phase winding of the phase shifter.  Similar to TapChanger.voltageStepIncrement, but it is applied only to the out of phase winding. 
        self.voltage_step_increment_out_of_phase = voltage_step_increment_out_of_phase
        # Phase shift per step position. A positive value indicates a positive phase shift from the winding where the tap is located to the other winding (for a two-winding transformer). The actual phase shift increment might be more accureatly computed from the symmetrical or asymmetrical models or a tap step table lookup if those are available. 
        self.step_phase_shift_increment = step_phase_shift_increment
        
        self._transformer_winding = None
        self.transformer_winding = transformer_winding

        super(PhaseTapChanger, self).__init__(**kw_args)
    # >>> phase_tap_changer
        
    # <<< transformer_winding
    # @generated
    def get_transformer_winding(self):
        """ The transformer winding to which the phase tap changer belongs.
        """
        return self._transformer_winding

    def set_transformer_winding(self, value):
        if self._transformer_winding is not None:
            self._transformer_winding._phase_tap_changer = None
            
        self._transformer_winding = value
        if self._transformer_winding is not None:
            self._transformer_winding._phase_tap_changer = self
            
    transformer_winding = property(get_transformer_winding, set_transformer_winding)
    # >>> transformer_winding



class Breaker(ProtectedSwitch):
    """ A mechanical switching device capable of making, carrying, and breaking currents under normal circuit conditions and also making, carrying for a specified time, and breaking currents under specified abnormal circuit conditions e.g.  those of short circuit.
    """
    # <<< breaker
    # @generated
    def __init__(self, in_transit_time=0.0, rated_current=0.0, **kw_args):
        """ Initialises a new 'Breaker' instance.
        """
        # The transition time from open to close. 
        self.in_transit_time = in_transit_time
        # Fault interrupting current rating. 
        self.rated_current = rated_current
        

        super(Breaker, self).__init__(**kw_args)
    # >>> breaker
        


class Junction(Connector):
    """ A point where one or more conducting equipments are connected with zero resistance.
    """
    pass
    # <<< junction
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Junction' instance.
        """
        

        super(Junction, self).__init__(**kw_args)
    # >>> junction
        


class LoadBreakSwitch(ProtectedSwitch):
    """ A mechanical switching device capable of making, carrying, and breaking currents under normal operating conditions.
    """
    # <<< load_break_switch
    # @generated
    def __init__(self, rated_current=0.0, **kw_args):
        """ Initialises a new 'LoadBreakSwitch' instance.
        """
        # Current carrying capacity of a wire or cable under stated thermal conditions. 
        self.rated_current = rated_current
        

        super(LoadBreakSwitch, self).__init__(**kw_args)
    # >>> load_break_switch
        


# <<< wires
# @generated
# >>> wires

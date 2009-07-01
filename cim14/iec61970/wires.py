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
    # Set if the winding is grounded. 
    grounded = False

    # Zero sequence series resistance of the winding. 
    r0 = ''

    # The apparent power that the winding can carry  under emergency conditions. 
    emergency_s = ''

    # Basic insulation level voltage rating 
    insulation_u = ''

    # Ground reactance path through connected grounding transformer. 
    xground = ''

    # Positive sequence series reactance of the winding. 
    x = ''

    # Zero sequence magnetizing branch conductance. 
    g0 = ''

    # Zero sequence magnetizing branch susceptance. 
    b0 = ''

    # The type of connection of the winding. Values are: "zn", "y", "d", "z", "yn"
    connection_type = 'zn'

    # Zero sequence series reactance of the winding. 
    x0 = ''

    # Ground resistance path through connected grounding transformer. 
    rground = ''

    # Apparent power that the winding can carry for a short period of time. 
    short_term_s = ''

    # Magnetizing branch conductance (G mag). 
    g = ''

    # The normal apparent power rating for the winding 
    rated_s = ''

    # Magnetizing branch susceptance (B mag). 
    b = ''

    # The type of winding. Values are: "primary", "secondary", "quaternary", "tertiary"
    winding_type = 'primary'

    # Positive sequence series resistance of the winding. 
    r = ''

    # The rated voltage (phase-to-phase) of the winding, usually the same as the neutral voltage. 
    rated_u = ''

    def get_from_winding_insulations(self):
        """ 
        """
        return self._from_winding_insulations

    def set_from_winding_insulations(self, value):
        for x in self._from_winding_insulations:
            x._from_transformer_winding = None
        for y in value:
            y._from_transformer_winding = self
        self._from_winding_insulations = value
            
    from_winding_insulations = property(get_from_winding_insulations, set_from_winding_insulations)
    
    def add_from_winding_insulations(self, *from_winding_insulations):
        for obj in from_winding_insulations:
            obj._from_transformer_winding = self
            self._from_winding_insulations.append(obj)
        
    def remove_from_winding_insulations(self, *from_winding_insulations):
        for obj in from_winding_insulations:
            obj._from_transformer_winding = None
            self._from_winding_insulations.remove(obj)

    def get_to_winding_test(self):
        """ The winding winding tests for which the transformer winding (terminal) participates as the 'to' end of the test.
        """
        return self._to_winding_test

    def set_to_winding_test(self, value):
        for x in self._to_winding_test:
            x._to_transformer_winding = None
        for y in value:
            y._to_transformer_winding = self
        self._to_winding_test = value
            
    to_winding_test = property(get_to_winding_test, set_to_winding_test)
    
    def add_to_winding_test(self, *to_winding_test):
        for obj in to_winding_test:
            obj._to_transformer_winding = self
            self._to_winding_test.append(obj)
        
    def remove_to_winding_test(self, *to_winding_test):
        for obj in to_winding_test:
            obj._to_transformer_winding = None
            self._to_winding_test.remove(obj)

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
            self._power_transformer._transformer_windings.append(self)

    power_transformer = property(get_power_transformer, set_power_transformer)

    def get_from_winding_test(self):
        """ The transformer winding tests for which the transformer winding (terminal) participates as the 'from' part of the test.
        """
        return self._from_winding_test

    def set_from_winding_test(self, value):
        for x in self._from_winding_test:
            x._from_transformer_winding = None
        for y in value:
            y._from_transformer_winding = self
        self._from_winding_test = value
            
    from_winding_test = property(get_from_winding_test, set_from_winding_test)
    
    def add_from_winding_test(self, *from_winding_test):
        for obj in from_winding_test:
            obj._from_transformer_winding = self
            self._from_winding_test.append(obj)
        
    def remove_from_winding_test(self, *from_winding_test):
        for obj in from_winding_test:
            obj._from_transformer_winding = None
            self._from_winding_test.remove(obj)

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

    def get_to_winding_insulations(self):
        """ 
        """
        return self._to_winding_insulations

    def set_to_winding_insulations(self, value):
        for x in self._to_winding_insulations:
            x._to_transformer_winding = None
        for y in value:
            y._to_transformer_winding = self
        self._to_winding_insulations = value
            
    to_winding_insulations = property(get_to_winding_insulations, set_to_winding_insulations)
    
    def add_to_winding_insulations(self, *to_winding_insulations):
        for obj in to_winding_insulations:
            obj._to_transformer_winding = self
            self._to_winding_insulations.append(obj)
        
    def remove_to_winding_insulations(self, *to_winding_insulations):
        for obj in to_winding_insulations:
            obj._to_transformer_winding = None
            self._to_winding_insulations.remove(obj)

    # <<< transformer_winding
    # @generated
    def __init__(self, grounded=False, r0='', emergency_s='', insulation_u='', xground='', x='', g0='', b0='', connection_type='zn', x0='', rground='', short_term_s='', g='', rated_s='', b='', winding_type='primary', r='', rated_u='', from_winding_insulations=[], to_winding_test=[], ratio_tap_changer=None, power_transformer=None, from_winding_test=[], phase_tap_changer=None, to_winding_insulations=[], **kw_args):
        """ Initialises a new 'TransformerWinding' instance.
        """
        self.grounded = grounded
        self.r0 = r0
        self.emergency_s = emergency_s
        self.insulation_u = insulation_u
        self.xground = xground
        self.x = x
        self.g0 = g0
        self.b0 = b0
        self.connection_type = connection_type
        self.x0 = x0
        self.rground = rground
        self.short_term_s = short_term_s
        self.g = g
        self.rated_s = rated_s
        self.b = b
        self.winding_type = winding_type
        self.r = r
        self.rated_u = rated_u
        self._from_winding_insulations = []
        self.from_winding_insulations = from_winding_insulations
        self._to_winding_test = []
        self.to_winding_test = to_winding_test
        self._ratio_tap_changer = None
        self.ratio_tap_changer = ratio_tap_changer
        self._power_transformer = None
        self.power_transformer = power_transformer
        self._from_winding_test = []
        self.from_winding_test = from_winding_test
        self._phase_tap_changer = None
        self.phase_tap_changer = phase_tap_changer
        self._to_winding_insulations = []
        self.to_winding_insulations = to_winding_insulations

        super(TransformerWinding, self).__init__(**kw_args)
    # >>> transformer_winding


class RegulatingControl(PowerSystemResource):
    """ Specifies a set of equipment that works together to control a power system quantity such as voltage or flow.
    """
    # This is the case input target range.   This performs the same function as the value2 attribute on the regulation schedule in the case that schedules are not used.   The units of those appropriate for the mode. 
    target_range = 0.0

    # The regulation is performed in a discrete mode. 
    discrete = False

    # The target value specified for case input.   This value can be used for the target value wihout the use of schedules. The value has the units appropriate to the mode attribute. 
    target_value = 0.0

    # The regulating control mode presently available.  This specifications allows for determining the kind of regualation without need for obtaining the units from a schedule. Values are: "admittance", "fixed", "active_power", "current_flow", "voltage", "reactive_power"
    mode = 'admittance'

    def get_tap_changer(self):
        """ copy from reg conduting eq
        """
        return self._tap_changer

    def set_tap_changer(self, value):
        for x in self._tap_changer:
            x._regulating_control = None
        for y in value:
            y._regulating_control = self
        self._tap_changer = value
            
    tap_changer = property(get_tap_changer, set_tap_changer)
    
    def add_tap_changer(self, *tap_changer):
        for obj in tap_changer:
            obj._regulating_control = self
            self._tap_changer.append(obj)
        
    def remove_tap_changer(self, *tap_changer):
        for obj in tap_changer:
            obj._regulating_control = None
            self._tap_changer.remove(obj)

    def get_regulating_cond_eq(self):
        """ copy from reg cond eq
        """
        return self._regulating_cond_eq

    def set_regulating_cond_eq(self, value):
        for x in self._regulating_cond_eq:
            x._regulating_control = None
        for y in value:
            y._regulating_control = self
        self._regulating_cond_eq = value
            
    regulating_cond_eq = property(get_regulating_cond_eq, set_regulating_cond_eq)
    
    def add_regulating_cond_eq(self, *regulating_cond_eq):
        for obj in regulating_cond_eq:
            obj._regulating_control = self
            self._regulating_cond_eq.append(obj)
        
    def remove_regulating_cond_eq(self, *regulating_cond_eq):
        for obj in regulating_cond_eq:
            obj._regulating_control = None
            self._regulating_cond_eq.remove(obj)

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
            self._regulation_schedule._regulating_control.append(self)

    regulation_schedule = property(get_regulation_schedule, set_regulation_schedule)

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
            self._terminal._regulating_control.append(self)

    terminal = property(get_terminal, set_terminal)

    # <<< regulating_control
    # @generated
    def __init__(self, target_range=0.0, discrete=False, target_value=0.0, mode='admittance', tap_changer=[], regulating_cond_eq=[], regulation_schedule=None, terminal=None, **kw_args):
        """ Initialises a new 'RegulatingControl' instance.
        """
        self.target_range = target_range
        self.discrete = discrete
        self.target_value = target_value
        self.mode = mode
        self._tap_changer = []
        self.tap_changer = tap_changer
        self._regulating_cond_eq = []
        self.regulating_cond_eq = regulating_cond_eq
        self._regulation_schedule = None
        self.regulation_schedule = regulation_schedule
        self._terminal = None
        self.terminal = terminal

        super(RegulatingControl, self).__init__(**kw_args)
    # >>> regulating_control


class RegulatingCondEq(ConductingEquipment):
    """ RegulatingCondEq is a type of ConductingEquipment that can regulate Measurements and have a RegulationSchedule.
    """
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
            self._regulating_control._regulating_cond_eq.append(self)

    regulating_control = property(get_regulating_control, set_regulating_control)

    def get_controls(self):
        """ The controller outputs used to actually govern a regulating device, e.g. the magnetization of a synchronous machine or capacitor bank breaker actuator.
        """
        return self._controls

    def set_controls(self, value):
        for x in self._controls:
            x._regulating_cond_eq = None
        for y in value:
            y._regulating_cond_eq = self
        self._controls = value
            
    controls = property(get_controls, set_controls)
    
    def add_controls(self, *controls):
        for obj in controls:
            obj._regulating_cond_eq = self
            self._controls.append(obj)
        
    def remove_controls(self, *controls):
        for obj in controls:
            obj._regulating_cond_eq = None
            self._controls.remove(obj)

    # <<< regulating_cond_eq
    # @generated
    def __init__(self, regulating_control=None, controls=[], **kw_args):
        """ Initialises a new 'RegulatingCondEq' instance.
        """
        self._regulating_control = None
        self.regulating_control = regulating_control
        self._controls = []
        self.controls = controls

        super(RegulatingCondEq, self).__init__(**kw_args)
    # >>> regulating_cond_eq


class RectifierInverter(ConductingEquipment):
    """ Bi-directional AC-DC conversion equipment that can be used to control DC current, DC voltage, DC power flow, or firing angle.
    """
    # The minimum active power on the DC side at which the converter should operate. 
    min_p = ''

    # Operating mode for the converter. 
    operating_mode = ''

    # Compounding resistance. 
    compound_resistance = ''

    # Rectifier/inverter primary base voltage 
    rated_u = ''

    # Commutating reactance at AC bus frequency. 
    commutating_reactance = ''

    # Number of bridges 
    bridges = 0

    # The maximum voltage on the DC side at which the converter should operate. 
    max_u = ''

    # Frequency on the AC side. 
    frequency = ''

    # The maximum active power on the DC side at which the fconverter should operate. 
    max_p = ''

    # Minimum compounded DC voltage 
    min_compound_voltage = ''

    # The minimum voltage on the DC side at which the converter should operate. 
    min_u = ''

    # Commutating resistance. 
    commutating_resistance = ''

    # <<< rectifier_inverter
    # @generated
    def __init__(self, min_p='', operating_mode='', compound_resistance='', rated_u='', commutating_reactance='', bridges=0, max_u='', frequency='', max_p='', min_compound_voltage='', min_u='', commutating_resistance='', **kw_args):
        """ Initialises a new 'RectifierInverter' instance.
        """
        self.min_p = min_p
        self.operating_mode = operating_mode
        self.compound_resistance = compound_resistance
        self.rated_u = rated_u
        self.commutating_reactance = commutating_reactance
        self.bridges = bridges
        self.max_u = max_u
        self.frequency = frequency
        self.max_p = max_p
        self.min_compound_voltage = min_compound_voltage
        self.min_u = min_u
        self.commutating_resistance = commutating_resistance

        super(RectifierInverter, self).__init__(**kw_args)
    # >>> rectifier_inverter


class Switch(ConductingEquipment):
    """ A generic device designed to close, or open, or both, one or more electric circuits.
    """
    # The switch on count since the switch was last reset or initialized. 
    switch_on_count = 0

    # The attribute is used in cases when no Measurement for the status value is present. If the Switch has a status measurment the Discrete.normalValue is expected to match with the Switch.normalOpen. 
    normal_open = False

    # Branch is retained in a bus branch model. 
    retained = False

    # The date and time when the switch was last switched on. 
    switch_on_date = ''

    connect_disconnect_functions = []
    
    def add_connect_disconnect_functions(self, *connect_disconnect_functions):
        for obj in connect_disconnect_functions:
	        self._connect_disconnect_functions.append(obj)
        
    def remove_connect_disconnect_functions(self, *connect_disconnect_functions):
        for obj in connect_disconnect_functions:
	        self._connect_disconnect_functions.remove(obj)

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
            self._composite_switch._switches.append(self)

    composite_switch = property(get_composite_switch, set_composite_switch)

    load_mgmt_functions = []
    
    def add_load_mgmt_functions(self, *load_mgmt_functions):
        for obj in load_mgmt_functions:
	        self._load_mgmt_functions.append(obj)
        
    def remove_load_mgmt_functions(self, *load_mgmt_functions):
        for obj in load_mgmt_functions:
	        self._load_mgmt_functions.remove(obj)

    switching_operations = []
    
    def add_switching_operations(self, *switching_operations):
        for obj in switching_operations:
	        self._switching_operations.append(obj)
        
    def remove_switching_operations(self, *switching_operations):
        for obj in switching_operations:
	        self._switching_operations.remove(obj)

    # <<< switch
    # @generated
    def __init__(self, switch_on_count=0, normal_open=False, retained=False, switch_on_date='', connect_disconnect_functions=[], composite_switch=None, load_mgmt_functions=[], switching_operations=[], **kw_args):
        """ Initialises a new 'Switch' instance.
        """
        self.switch_on_count = switch_on_count
        self.normal_open = normal_open
        self.retained = retained
        self.switch_on_date = switch_on_date
        self._connect_disconnect_functions = []
        self.connect_disconnect_functions = connect_disconnect_functions
        self._composite_switch = None
        self.composite_switch = composite_switch
        self._load_mgmt_functions = []
        self.load_mgmt_functions = load_mgmt_functions
        self._switching_operations = []
        self.switching_operations = switching_operations

        super(Switch, self).__init__(**kw_args)
    # >>> switch


class Line(EquipmentContainer):
    """ A component part of a system extending between adjacent substations or from a substation to an adjacent interconnection point.
    """
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
            self._transmission_right_of_way._lines.append(self)

    transmission_right_of_way = property(get_transmission_right_of_way, set_transmission_right_of_way)

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
            self._region._lines.append(self)

    region = property(get_region, set_region)

    flowgates = []
    
    def add_flowgates(self, *flowgates):
        for obj in flowgates:
	        self._flowgates.append(obj)
        
    def remove_flowgates(self, *flowgates):
        for obj in flowgates:
	        self._flowgates.remove(obj)

    # <<< line
    # @generated
    def __init__(self, transmission_right_of_way=None, region=None, flowgates=[], **kw_args):
        """ Initialises a new 'Line' instance.
        """
        self._transmission_right_of_way = None
        self.transmission_right_of_way = transmission_right_of_way
        self._region = None
        self.region = region
        self._flowgates = []
        self.flowgates = flowgates

        super(Line, self).__init__(**kw_args)
    # >>> line


class ReactiveCapabilityCurve(Curve):
    """ Reactive power rating envelope versus the synchronous machine's active power, in both the generating and motoring modes. For each active power value there is a corresponding high and low reactive power limit  value. Typically there will be a separate curve for each coolant condition, such as hydrogen pressure.  The Y1 axis values represent reactive minimum and the Y2 axis values represent reactive maximum.
    """
    # The hydrogen coolant pressure 
    hydrogen_pressure = ''

    # The machine's coolant temperature (e.g., ambient air or stator circulating water). 
    coolant_temperature = ''

    synchronous_machines = []
    
    def add_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
	        self._synchronous_machines.append(obj)
        
    def remove_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
	        self._synchronous_machines.remove(obj)

    def get_initially_used_by_synchronous_machines(self):
        """ Synchronous machines using this curve as default.
        """
        return self._initially_used_by_synchronous_machines

    def set_initially_used_by_synchronous_machines(self, value):
        for x in self._initially_used_by_synchronous_machines:
            x._initial_reactive_capability_curve = None
        for y in value:
            y._initial_reactive_capability_curve = self
        self._initially_used_by_synchronous_machines = value
            
    initially_used_by_synchronous_machines = property(get_initially_used_by_synchronous_machines, set_initially_used_by_synchronous_machines)
    
    def add_initially_used_by_synchronous_machines(self, *initially_used_by_synchronous_machines):
        for obj in initially_used_by_synchronous_machines:
            obj._initial_reactive_capability_curve = self
            self._initially_used_by_synchronous_machines.append(obj)
        
    def remove_initially_used_by_synchronous_machines(self, *initially_used_by_synchronous_machines):
        for obj in initially_used_by_synchronous_machines:
            obj._initial_reactive_capability_curve = None
            self._initially_used_by_synchronous_machines.remove(obj)

    # <<< reactive_capability_curve
    # @generated
    def __init__(self, hydrogen_pressure='', coolant_temperature='', synchronous_machines=[], initially_used_by_synchronous_machines=[], **kw_args):
        """ Initialises a new 'ReactiveCapabilityCurve' instance.
        """
        self.hydrogen_pressure = hydrogen_pressure
        self.coolant_temperature = coolant_temperature
        self._synchronous_machines = []
        self.synchronous_machines = synchronous_machines
        self._initially_used_by_synchronous_machines = []
        self.initially_used_by_synchronous_machines = initially_used_by_synchronous_machines

        super(ReactiveCapabilityCurve, self).__init__(**kw_args)
    # >>> reactive_capability_curve


class SeriesCompensator(ConductingEquipment):
    """ A Series Compensator is a series capacitor or reactor or an AC transmission line without charging susceptance.  It is a two terminal device.
    """
    # Positive sequence resistance. 
    r = ''

    # Positive sequence reactance. 
    x = ''

    # <<< series_compensator
    # @generated
    def __init__(self, r='', x='', **kw_args):
        """ Initialises a new 'SeriesCompensator' instance.
        """
        self.r = r
        self.x = x

        super(SeriesCompensator, self).__init__(**kw_args)
    # >>> series_compensator


class EnergyConsumer(ConductingEquipment):
    """ Generic user of energy - a  point of consumption on the power system model
    """
    # Number of individual customers represented by this Demand 
    customer_count = 0

    # Active power of the load that is a fixed quantity. 
    pfixed = ''

    # Fixed reactive power as per cent of load group fixed reactive power. 
    qfixed_pct = ''

    # Fixed active power as per cent of load group fixed active power 
    pfixed_pct = ''

    # Reactive power of the load that is a fixed quantity. 
    qfixed = ''

    def get_service_delivery_points(self):
        """ 
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x._energy_consumer = None
        for y in value:
            y._energy_consumer = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._energy_consumer = self
            self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._energy_consumer = None
            self._service_delivery_points.remove(obj)

    def get_phase_loads(self):
        """ 
        """
        return self._phase_loads

    def set_phase_loads(self, value):
        for x in self._phase_loads:
            x._energy_consumer = None
        for y in value:
            y._energy_consumer = self
        self._phase_loads = value
            
    phase_loads = property(get_phase_loads, set_phase_loads)
    
    def add_phase_loads(self, *phase_loads):
        for obj in phase_loads:
            obj._energy_consumer = self
            self._phase_loads.append(obj)
        
    def remove_phase_loads(self, *phase_loads):
        for obj in phase_loads:
            obj._energy_consumer = None
            self._phase_loads.remove(obj)

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
            self._power_cut_zone._energy_consumers.append(self)

    power_cut_zone = property(get_power_cut_zone, set_power_cut_zone)

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
            self._load_response._energy_consumer.append(self)

    load_response = property(get_load_response, set_load_response)

    # <<< energy_consumer
    # @generated
    def __init__(self, customer_count=0, pfixed='', qfixed_pct='', pfixed_pct='', qfixed='', service_delivery_points=[], phase_loads=[], power_cut_zone=None, load_response=None, **kw_args):
        """ Initialises a new 'EnergyConsumer' instance.
        """
        self.customer_count = customer_count
        self.pfixed = pfixed
        self.qfixed_pct = qfixed_pct
        self.pfixed_pct = pfixed_pct
        self.qfixed = qfixed
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._phase_loads = []
        self.phase_loads = phase_loads
        self._power_cut_zone = None
        self.power_cut_zone = power_cut_zone
        self._load_response = None
        self.load_response = load_response

        super(EnergyConsumer, self).__init__(**kw_args)
    # >>> energy_consumer


class RegulationSchedule(RegularIntervalSchedule):
    """ A pre-established pattern over time for a controlled variable, e.g., busbar voltage.
    """
    # Line drop resistance. 
    line_drop_r = ''

    # Flag to indicate that line drop compensation is to be applied 
    line_drop_compensation = False

    # Line drop reactance. 
    line_drop_x = ''

    def get_regulating_control(self):
        """ Regulating controls that have this Schedule.
        """
        return self._regulating_control

    def set_regulating_control(self, value):
        for x in self._regulating_control:
            x._regulation_schedule = None
        for y in value:
            y._regulation_schedule = self
        self._regulating_control = value
            
    regulating_control = property(get_regulating_control, set_regulating_control)
    
    def add_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._regulation_schedule = self
            self._regulating_control.append(obj)
        
    def remove_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._regulation_schedule = None
            self._regulating_control.remove(obj)

    def get_voltage_control_zones(self):
        """ A VoltageControlZone may have a  voltage regulation schedule.
        """
        return self._voltage_control_zones

    def set_voltage_control_zones(self, value):
        for x in self._voltage_control_zones:
            x._regulation_schedule = None
        for y in value:
            y._regulation_schedule = self
        self._voltage_control_zones = value
            
    voltage_control_zones = property(get_voltage_control_zones, set_voltage_control_zones)
    
    def add_voltage_control_zones(self, *voltage_control_zones):
        for obj in voltage_control_zones:
            obj._regulation_schedule = self
            self._voltage_control_zones.append(obj)
        
    def remove_voltage_control_zones(self, *voltage_control_zones):
        for obj in voltage_control_zones:
            obj._regulation_schedule = None
            self._voltage_control_zones.remove(obj)

    # <<< regulation_schedule
    # @generated
    def __init__(self, line_drop_r='', line_drop_compensation=False, line_drop_x='', regulating_control=[], voltage_control_zones=[], **kw_args):
        """ Initialises a new 'RegulationSchedule' instance.
        """
        self.line_drop_r = line_drop_r
        self.line_drop_compensation = line_drop_compensation
        self.line_drop_x = line_drop_x
        self._regulating_control = []
        self.regulating_control = regulating_control
        self._voltage_control_zones = []
        self.voltage_control_zones = voltage_control_zones

        super(RegulationSchedule, self).__init__(**kw_args)
    # >>> regulation_schedule


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
            self._resistor_type_asset._resistors.append(self)

    resistor_type_asset = property(get_resistor_type_asset, set_resistor_type_asset)

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


class CompositeSwitch(Equipment):
    """ A model of a set of individual Switches normally enclosed within the same cabinet and possibly with interlocks that restrict the combination of switch positions. These are typically found in medium voltage distribution networks.  A CompositeSwitch could represent a Ring-Main-Unit (RMU), or pad-mounted switchgear, with primitive internal devices such as an internal bus-bar plus 3 or 4 internal switches each of which may individually be open or closed. A CompositeSwitch and a set of contained Switches can also be used to represent a multi-position switch e.g. a switch that can connect a circuit to Ground, Open or Busbar.
    """
    # An alphanumeric code that can be used as a reference to extar information such as the description of the interlocking scheme if any 
    composite_switch_type = ''

    def get_switches(self):
        """ Switches contained in this Composite switch.
        """
        return self._switches

    def set_switches(self, value):
        for x in self._switches:
            x._composite_switch = None
        for y in value:
            y._composite_switch = self
        self._switches = value
            
    switches = property(get_switches, set_switches)
    
    def add_switches(self, *switches):
        for obj in switches:
            obj._composite_switch = self
            self._switches.append(obj)
        
    def remove_switches(self, *switches):
        for obj in switches:
            obj._composite_switch = None
            self._switches.remove(obj)

    # <<< composite_switch
    # @generated
    def __init__(self, composite_switch_type='', switches=[], **kw_args):
        """ Initialises a new 'CompositeSwitch' instance.
        """
        self.composite_switch_type = composite_switch_type
        self._switches = []
        self.switches = switches

        super(CompositeSwitch, self).__init__(**kw_args)
    # >>> composite_switch


class WindingTest(IdentifiedObject):
    """ Physical winding test data for the winding/tap pairs of a transformer (or phase shifter). This test data can be used to derive other attributes of specific transformer or phase shifter models.
    """
    # The voltage measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
    voltage = ''

    # The exciting current on open-circuit test, expressed as a percentage of rated current, at nominal voltage 
    exciting_current = ''

    # The no load loss kW 'to' winding open-circuited) from the test report. 
    no_load_loss = ''

    # The leakage impedance measured at the 'from' winding  with the 'to' winding short-circuited and all other windings open-circuited.  Leakage impedance is expressed in units based on the apparent power and voltage ratings of the 'from' winding. 
    leakage_impedance = ''

    # The load loss kW ('to' winding short-circuited) from the test report. 
    load_loss = ''

    # The tap step number for the 'to' winding of the test pair. 
    to_tap_step = 0

    # The phase shift measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
    phase_shift = ''

    # The tap step number for the 'from' winding of the test pair. 
    from_tap_step = 0

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
            self._from_transformer_winding._from_winding_test.append(self)

    from_transformer_winding = property(get_from_transformer_winding, set_from_transformer_winding)

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
            self._to_transformer_winding._to_winding_test.append(self)

    to_transformer_winding = property(get_to_transformer_winding, set_to_transformer_winding)

    transformer_observations = []
    
    def add_transformer_observations(self, *transformer_observations):
        for obj in transformer_observations:
	        self._transformer_observations.append(obj)
        
    def remove_transformer_observations(self, *transformer_observations):
        for obj in transformer_observations:
	        self._transformer_observations.remove(obj)

    # <<< winding_test
    # @generated
    def __init__(self, voltage='', exciting_current='', no_load_loss='', leakage_impedance='', load_loss='', to_tap_step=0, phase_shift='', from_tap_step=0, from_transformer_winding=None, to_transformer_winding=None, transformer_observations=[], **kw_args):
        """ Initialises a new 'WindingTest' instance.
        """
        self.voltage = voltage
        self.exciting_current = exciting_current
        self.no_load_loss = no_load_loss
        self.leakage_impedance = leakage_impedance
        self.load_loss = load_loss
        self.to_tap_step = to_tap_step
        self.phase_shift = phase_shift
        self.from_tap_step = from_tap_step
        self._from_transformer_winding = None
        self.from_transformer_winding = from_transformer_winding
        self._to_transformer_winding = None
        self.to_transformer_winding = to_transformer_winding
        self._transformer_observations = []
        self.transformer_observations = transformer_observations

        super(WindingTest, self).__init__(**kw_args)
    # >>> winding_test


class TapChanger(PowerSystemResource):
    """ Mechanism for changing transformer winding tap positions.
    """
    # The tap step position used in 'normal' network operation for this winding. For a 'Fixed' tap changer indicates the current physical tap setting. 
    normal_step = 0

    # Lowest possible tap step position, retard from neutral 
    low_step = 0

    # Tap step increment, in per cent of nominal voltage, per step position. 
    step_voltage_increment = ''

    # Highest possible tap step position, advance from neutral 
    high_step = 0

    # For an LTC, the delay for initial tap changer operation (first step change) 
    initial_delay = ''

    # The type of tap changer. Indicates the ability of the transformer to perform various regulation tasks. The tap changer must be also be associated wtih a RegulationControl object before any regulation is possible. Values are: "phase_control", "fixed", "voltage_control", "voltage_and_phase_control"
    type = 'phase_control'

    # For an LTC, the tap changer control mode. Values are: "local", "off", "volt", "active", "reactive"
    tcul_control_mode = 'local'

    # For an LTC, the delay for subsequent tap changer operation (second and later step changes) 
    subsequent_delay = ''

    # The neutral tap step position for this winding. 
    neutral_step = 0

    # Voltage at which the winding operates at the neutral tap setting. 
    neutral_u = ''

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
            self._regulating_control._tap_changer.append(self)

    regulating_control = property(get_regulating_control, set_regulating_control)

    # <<< tap_changer
    # @generated
    def __init__(self, normal_step=0, low_step=0, step_voltage_increment='', high_step=0, initial_delay='', type='phase_control', tcul_control_mode='local', subsequent_delay='', neutral_step=0, neutral_u='', sv_tap_step=None, regulating_control=None, **kw_args):
        """ Initialises a new 'TapChanger' instance.
        """
        self.normal_step = normal_step
        self.low_step = low_step
        self.step_voltage_increment = step_voltage_increment
        self.high_step = high_step
        self.initial_delay = initial_delay
        self.type = type
        self.tcul_control_mode = tcul_control_mode
        self.subsequent_delay = subsequent_delay
        self.neutral_step = neutral_step
        self.neutral_u = neutral_u
        self._sv_tap_step = None
        self.sv_tap_step = sv_tap_step
        self._regulating_control = None
        self.regulating_control = regulating_control

        super(TapChanger, self).__init__(**kw_args)
    # >>> tap_changer


class HeatExchanger(Equipment):
    """ Equipment for the cooling of electrical equipment and the extraction of heat
    """
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

    # <<< heat_exchanger
    # @generated
    def __init__(self, power_transformer=None, **kw_args):
        """ Initialises a new 'HeatExchanger' instance.
        """
        self._power_transformer = None
        self.power_transformer = power_transformer

        super(HeatExchanger, self).__init__(**kw_args)
    # >>> heat_exchanger


class MutualCoupling(IdentifiedObject):
    """ This class represents the zero sequence line mutual coupling.
    """
    # Zero sequence mutual coupling shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    b0ch = ''

    # Zero sequence mutual coupling shunt (charging) conductance, uniformly distributed, of the entire line section. 
    g0ch = ''

    # Zero sequence branch-to-branch mutual impedance coupling, resistance 
    r0 = ''

    # Zero sequence branch-to-branch mutual impedance coupling, reactance 
    x0 = ''

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
            self._first_terminal._has_first_mutual_coupling.append(self)

    first_terminal = property(get_first_terminal, set_first_terminal)

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
            self._second_terminal._has_second_mutual_coupling.append(self)

    second_terminal = property(get_second_terminal, set_second_terminal)

    # <<< mutual_coupling
    # @generated
    def __init__(self, b0ch='', g0ch='', r0='', x0='', first_terminal=None, second_terminal=None, **kw_args):
        """ Initialises a new 'MutualCoupling' instance.
        """
        self.b0ch = b0ch
        self.g0ch = g0ch
        self.r0 = r0
        self.x0 = x0
        self._first_terminal = None
        self.first_terminal = first_terminal
        self._second_terminal = None
        self.second_terminal = second_terminal

        super(MutualCoupling, self).__init__(**kw_args)
    # >>> mutual_coupling


class EnergySource(ConductingEquipment):
    """ A generic equivalent for an energy supplier on a transmission or distribution voltage level.
    """
    # Phase angle of a-phase open circuit. 
    voltage_angle = ''

    # Phase-to-phase open circuit voltage magnitude. 
    voltage_magnitude = ''

    # Zero sequence Thevenin resistance. 
    r0 = ''

    # Negative sequence Thevenin reactance. 
    xn = ''

    # Positive sequence Thevenin reactance. 
    x = ''

    # Phase-to-phase nominal voltage. 
    nominal_voltage = ''

    # Negative sequence Thevenin resistance. 
    rn = ''

    # Zero sequence Thevenin reactance. 
    x0 = ''

    # High voltage source load 
    active_power = ''

    # Positive sequence Thevenin resistance. 
    r = ''

    # <<< energy_source
    # @generated
    def __init__(self, voltage_angle='', voltage_magnitude='', r0='', xn='', x='', nominal_voltage='', rn='', x0='', active_power='', r='', **kw_args):
        """ Initialises a new 'EnergySource' instance.
        """
        self.voltage_angle = voltage_angle
        self.voltage_magnitude = voltage_magnitude
        self.r0 = r0
        self.xn = xn
        self.x = x
        self.nominal_voltage = nominal_voltage
        self.rn = rn
        self.x0 = x0
        self.active_power = active_power
        self.r = r

        super(EnergySource, self).__init__(**kw_args)
    # >>> energy_source


class VoltageControlZone(PowerSystemResource):
    """ An area of the power system network which is defined for secondary voltage control purposes. A voltage control zone consists of a collection of substations with a designated bus bar section whose voltage will be controlled.
    """
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
            self._regulation_schedule._voltage_control_zones.append(self)

    regulation_schedule = property(get_regulation_schedule, set_regulation_schedule)

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


class Conductor(ConductingEquipment):
    """ Combination of conducting material with consistent electrical characteristics, building a single electrical system, used to carry current between points in the power system.
    """
    # Zero sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
    g0ch = ''

    # Positive sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
    gch = ''

    # Positive sequence series reactance of the entire line section. 
    x = ''

    # Zero sequence series reactance of the entire line section. 
    x0 = ''

    # Positive sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    bch = ''

    # Zero sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    b0ch = ''

    # Zero sequence series resistance of the entire line section. 
    r0 = ''

    # Positive sequence series resistance of the entire line section. 
    r = ''

    linear_conductor_type_assets = []
    
    def add_linear_conductor_type_assets(self, *linear_conductor_type_assets):
        for obj in linear_conductor_type_assets:
	        self._linear_conductor_type_assets.append(obj)
        
    def remove_linear_conductor_type_assets(self, *linear_conductor_type_assets):
        for obj in linear_conductor_type_assets:
	        self._linear_conductor_type_assets.remove(obj)

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
            self._conductor_type._conductors.append(self)

    conductor_type = property(get_conductor_type, set_conductor_type)

    linear_conductor_assets = []
    
    def add_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
	        self._linear_conductor_assets.append(obj)
        
    def remove_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
	        self._linear_conductor_assets.remove(obj)

    # <<< conductor
    # @generated
    def __init__(self, g0ch='', gch='', x='', x0='', bch='', b0ch='', r0='', r='', linear_conductor_type_assets=[], conductor_type=None, linear_conductor_assets=[], **kw_args):
        """ Initialises a new 'Conductor' instance.
        """
        self.g0ch = g0ch
        self.gch = gch
        self.x = x
        self.x0 = x0
        self.bch = bch
        self.b0ch = b0ch
        self.r0 = r0
        self.r = r
        self._linear_conductor_type_assets = []
        self.linear_conductor_type_assets = linear_conductor_type_assets
        self._conductor_type = None
        self.conductor_type = conductor_type
        self._linear_conductor_assets = []
        self.linear_conductor_assets = linear_conductor_assets

        super(Conductor, self).__init__(**kw_args)
    # >>> conductor


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
    def get_winding_insulations(self):
        """ 
        """
        return self._winding_insulations

    def set_winding_insulations(self, value):
        for x in self._winding_insulations:
            x._ground = None
        for y in value:
            y._ground = self
        self._winding_insulations = value
            
    winding_insulations = property(get_winding_insulations, set_winding_insulations)
    
    def add_winding_insulations(self, *winding_insulations):
        for obj in winding_insulations:
            obj._ground = self
            self._winding_insulations.append(obj)
        
    def remove_winding_insulations(self, *winding_insulations):
        for obj in winding_insulations:
            obj._ground = None
            self._winding_insulations.remove(obj)

    # <<< ground
    # @generated
    def __init__(self, winding_insulations=[], **kw_args):
        """ Initialises a new 'Ground' instance.
        """
        self._winding_insulations = []
        self.winding_insulations = winding_insulations

        super(Ground, self).__init__(**kw_args)
    # >>> ground


class PowerTransformer(Equipment):
    """ An electrical device consisting of  two or more coupled windings, with or without a magnetic core, for introducing mutual coupling between electric circuits. Transformers can be used to control voltage and phase shift (active power flow).
    """
    # Core shunt magnetizing susceptance in the saturation region. 
    bmag_sat = ''

    # Core magnetizing saturation curve knee flux level. 
    mag_sat_flux = ''

    # Describes the phases carried by a power transformer. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phases = 'abn'

    # The reference voltage at which the magnetizing saturation measurements were made 
    mag_base_u = ''

    def get_transformer_windings(self):
        """ A transformer has windings
        """
        return self._transformer_windings

    def set_transformer_windings(self, value):
        for x in self._transformer_windings:
            x._power_transformer = None
        for y in value:
            y._power_transformer = self
        self._transformer_windings = value
            
    transformer_windings = property(get_transformer_windings, set_transformer_windings)
    
    def add_transformer_windings(self, *transformer_windings):
        for obj in transformer_windings:
            obj._power_transformer = self
            self._transformer_windings.append(obj)
        
    def remove_transformer_windings(self, *transformer_windings):
        for obj in transformer_windings:
            obj._power_transformer = None
            self._transformer_windings.remove(obj)

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

    flowgates = []
    
    def add_flowgates(self, *flowgates):
        for obj in flowgates:
	        self._flowgates.append(obj)
        
    def remove_flowgates(self, *flowgates):
        for obj in flowgates:
	        self._flowgates.remove(obj)

    # <<< power_transformer
    # @generated
    def __init__(self, bmag_sat='', mag_sat_flux='', phases='abn', mag_base_u='', transformer_windings=[], heat_exchanger=None, flowgates=[], **kw_args):
        """ Initialises a new 'PowerTransformer' instance.
        """
        self.bmag_sat = bmag_sat
        self.mag_sat_flux = mag_sat_flux
        self.phases = phases
        self.mag_base_u = mag_base_u
        self._transformer_windings = []
        self.transformer_windings = transformer_windings
        self._heat_exchanger = None
        self.heat_exchanger = heat_exchanger
        self._flowgates = []
        self.flowgates = flowgates

        super(PowerTransformer, self).__init__(**kw_args)
    # >>> power_transformer


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

    # <<< busbar_section
    # @generated
    def __init__(self, voltage_control_zone=None, **kw_args):
        """ Initialises a new 'BusbarSection' instance.
        """
        self._voltage_control_zone = None
        self.voltage_control_zone = voltage_control_zone

        super(BusbarSection, self).__init__(**kw_args)
    # >>> busbar_section


class SynchronousMachine(RegulatingCondEq):
    """ An electromechanical device that operates synchronously with the network. It is a single machine operating either as a generator or synchronous condenser or pump.
    """
    # Modes that this synchronous machine can operate in. Values are: "generator", "condenser", "generator_or_condenser"
    type = 'generator'

    # Maximum voltage limit for the unit. 
    max_u = ''

    # Minimum voltage  limit for the unit. 
    min_u = ''

    # Direct-axis subtransient reactance, also known as X'd. 
    x_direct_subtrans = ''

    # Method of cooling the machine. Values are: "water", "air", "hydrogen_gas"
    coolant_type = 'water'

    # Active power consumed when in condenser mode operation. 
    condenser_p = ''

    # Quadrature-axis synchronous reactance (Xq) , the ratio of the component of reactive armature voltage, due to the quadrature-axis component of armature current, to this component of current, under steady state conditions and at rated frequency. 
    x_quad_sync = ''

    # Zero sequence resistance of the synchronous machine. 
    r0 = ''

    # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a leading MVAr violation. 
    a_vrto_manual_lead = ''

    # Zero sequence reactance of the synchronous machine. 
    x0 = ''

    # Priority of unit for reference bus selection. 0 = don t care (default) 1 = highest priority. 2 is less than 1 and so on. 
    reference_priority = 0

    # Positive sequence resistance of the synchronous machine. 
    r = ''

    # Quadrature-axis transient reactance, also known as X'q. 
    x_quad_trans = ''

    # Negative sequence resistance. 
    r2 = ''

    # Default base reactive power value. This value represents the initial reactive power that can be used by any application function. 
    base_q = ''

    # The energy stored in the rotor when operating at rated speed. This value is used in the accelerating power reference frame for  operator training simulator solutions. 
    inertia = ''

    # Negative sequence reactance. 
    x2 = ''

    # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a lagging MVAr violation. 
    a_vrto_manual_lag = ''

    # Direct-axis transient reactance, also known as X'd. 
    x_direct_trans = ''

    # Percent of the coordinated reactive control that comes from this machine. 
    q_percent = ''

    # Temperature or pressure of coolant medium 
    coolant_condition = 0.0

    # Time delay required when switching from Manual to Automatic Voltage Regulation. This value is used in the accelerating power reference frame for powerflow solutions 
    manual_to_avr = ''

    # Current mode of operation. Values are: "generator", "condenser"
    operating_mode = 'generator'

    # Direct-axis synchronous reactance. The quotient of a sustained value of that AC component of armature voltage that is produced by the total direct-axis flux due to direct-axis armature current and the value of the AC component of this current, the machine running at rated speed. (Xd) 
    x_direct_sync = ''

    # Maximum reactive power limit. This is the maximum (nameplate) limit for the unit. 
    max_q = ''

    # Damping torque coefficient, a proportionality constant that, when multiplied by the angular velocity of the rotor poles with respect to the magnetic field (frequency), results in the damping torque. 
    damping = ''

    # Nameplate apparent power rating for the unit 
    rated_s = ''

    # Minimum reactive power limit for the unit. 
    min_q = ''

    # Quadrature-axis subtransient reactance, also known as X'q. 
    x_quad_subtrans = ''

    # Positive sequence reactance of the synchronous machine. 
    x = ''

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
            self._initial_reactive_capability_curve._initially_used_by_synchronous_machines.append(self)

    initial_reactive_capability_curve = property(get_initial_reactive_capability_curve, set_initial_reactive_capability_curve)

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

    reactive_capability_curves = []
    
    def add_reactive_capability_curves(self, *reactive_capability_curves):
        for obj in reactive_capability_curves:
	        self._reactive_capability_curves.append(obj)
        
    def remove_reactive_capability_curves(self, *reactive_capability_curves):
        for obj in reactive_capability_curves:
	        self._reactive_capability_curves.remove(obj)

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
            self._generating_unit._synchronous_machines.append(self)

    generating_unit = property(get_generating_unit, set_generating_unit)

    prime_movers = []
    
    def add_prime_movers(self, *prime_movers):
        for obj in prime_movers:
	        self._prime_movers.append(obj)
        
    def remove_prime_movers(self, *prime_movers):
        for obj in prime_movers:
	        self._prime_movers.remove(obj)

    # <<< synchronous_machine
    # @generated
    def __init__(self, type='generator', max_u='', min_u='', x_direct_subtrans='', coolant_type='water', condenser_p='', x_quad_sync='', r0='', a_vrto_manual_lead='', x0='', reference_priority=0, r='', x_quad_trans='', r2='', base_q='', inertia='', x2='', a_vrto_manual_lag='', x_direct_trans='', q_percent='', coolant_condition=0.0, manual_to_avr='', operating_mode='generator', x_direct_sync='', max_q='', damping='', rated_s='', min_q='', x_quad_subtrans='', x='', initial_reactive_capability_curve=None, hydro_pump=None, reactive_capability_curves=[], generating_unit=None, prime_movers=[], **kw_args):
        """ Initialises a new 'SynchronousMachine' instance.
        """
        self.type = type
        self.max_u = max_u
        self.min_u = min_u
        self.x_direct_subtrans = x_direct_subtrans
        self.coolant_type = coolant_type
        self.condenser_p = condenser_p
        self.x_quad_sync = x_quad_sync
        self.r0 = r0
        self.a_vrto_manual_lead = a_vrto_manual_lead
        self.x0 = x0
        self.reference_priority = reference_priority
        self.r = r
        self.x_quad_trans = x_quad_trans
        self.r2 = r2
        self.base_q = base_q
        self.inertia = inertia
        self.x2 = x2
        self.a_vrto_manual_lag = a_vrto_manual_lag
        self.x_direct_trans = x_direct_trans
        self.q_percent = q_percent
        self.coolant_condition = coolant_condition
        self.manual_to_avr = manual_to_avr
        self.operating_mode = operating_mode
        self.x_direct_sync = x_direct_sync
        self.max_q = max_q
        self.damping = damping
        self.rated_s = rated_s
        self.min_q = min_q
        self.x_quad_subtrans = x_quad_subtrans
        self.x = x
        self._initial_reactive_capability_curve = None
        self.initial_reactive_capability_curve = initial_reactive_capability_curve
        self._hydro_pump = None
        self.hydro_pump = hydro_pump
        self._reactive_capability_curves = []
        self.reactive_capability_curves = reactive_capability_curves
        self._generating_unit = None
        self.generating_unit = generating_unit
        self._prime_movers = []
        self.prime_movers = prime_movers

        super(SynchronousMachine, self).__init__(**kw_args)
    # >>> synchronous_machine


class StaticVarCompensator(RegulatingCondEq):
    """ A facility for providing variable and controllable shunt reactive power. The SVC typically consists of a stepdown transformer, filter, thyristor-controlled reactor, and thyristor-switched capacitor arms.  The SVC may operate in fixed MVar output mode or in voltage control mode.  When in voltage control mode, the output of the SVC will be proportional to the deviation of voltage at the controlled bus from the voltage setpoint.  The SVC characteristic slope defines the proportion.  If the voltage at the controlled bus is equal to the voltage setpoint, the SVC MVar output is zero.
    """
    # Maximum available capacitive reactive power 
    capacitive_rating = ''

    # The reactive power output of the SVC is proportional to the difference between the voltage at the regulated bus and the voltage setpoint.  When the regulated bus voltage is equal to the voltage setpoint, the reactive power output is zero. 
    voltage_set_point = ''

    # SVC control mode. Values are: "off", "reactive_power", "voltage"
    s_vccontrol_mode = 'off'

    # The characteristics slope of an SVC defines how the reactive power output changes in proportion to the difference between the regulated bus voltage and the voltage setpoint. 
    slope = ''

    # Maximum available inductive reactive power 
    inductive_rating = ''

    # <<< static_var_compensator
    # @generated
    def __init__(self, capacitive_rating='', voltage_set_point='', s_vccontrol_mode='off', slope='', inductive_rating='', **kw_args):
        """ Initialises a new 'StaticVarCompensator' instance.
        """
        self.capacitive_rating = capacitive_rating
        self.voltage_set_point = voltage_set_point
        self.s_vccontrol_mode = s_vccontrol_mode
        self.slope = slope
        self.inductive_rating = inductive_rating

        super(StaticVarCompensator, self).__init__(**kw_args)
    # >>> static_var_compensator


class RatioTapChanger(TapChanger):
    """ A tap changer that changes the voltage ratio impacting the voltage magnitude but not direclty the phase angle across the transformer..
    """
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

    # <<< ratio_tap_changer
    # @generated
    def __init__(self, transformer_winding=None, **kw_args):
        """ Initialises a new 'RatioTapChanger' instance.
        """
        self._transformer_winding = None
        self.transformer_winding = transformer_winding

        super(RatioTapChanger, self).__init__(**kw_args)
    # >>> ratio_tap_changer


class DCLineSegment(Conductor):
    """ A wire or combination of wires not insulated from one another, with consistent electrical characteristics, used to carry direct current between points in the DC region of the power system.
    """
    # Resistance of the DC line segment. 
    dc_segment_resistance = ''

    # Inductance of the DC line segment. 
    dc_segment_inductance = ''

    # <<< dcline_segment
    # @generated
    def __init__(self, dc_segment_resistance='', dc_segment_inductance='', **kw_args):
        """ Initialises a new 'DCLineSegment' instance.
        """
        self.dc_segment_resistance = dc_segment_resistance
        self.dc_segment_inductance = dc_segment_inductance

        super(DCLineSegment, self).__init__(**kw_args)
    # >>> dcline_segment


class FrequencyConverter(RegulatingCondEq):
    """ A device to convert from one frequency to another (e.g., frequency F1 to F2) comprises a pair of FrequencyConverter instances. One converts from F1 to DC, the other converts the DC to F2.
    """
    # The maximum active power on the DC side at which the frequence converter should operate. 
    max_p = ''

    # Frequency on the AC side. 
    frequency = ''

    # The minimum active power on the DC side at which the frequence converter should operate. 
    min_p = ''

    # Operating mode for the frequency converter 
    operating_mode = ''

    # The minimum voltage on the DC side at which the frequency converter should operate. 
    min_u = ''

    # The maximum voltage on the DC side at which the frequency converter should operate. 
    max_u = ''

    # <<< frequency_converter
    # @generated
    def __init__(self, max_p='', frequency='', min_p='', operating_mode='', min_u='', max_u='', **kw_args):
        """ Initialises a new 'FrequencyConverter' instance.
        """
        self.max_p = max_p
        self.frequency = frequency
        self.min_p = min_p
        self.operating_mode = operating_mode
        self.min_u = min_u
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
    # Nominal reactive power output of the capacitor bank at the nominal voltage. This number should be positive. 
    nom_q = ''

    # The maximum voltage at which the capacitor bank should operate. 
    max_u = ''

    # Time delay required for the device to be connected or disconnected by automatic voltage regulation (AVR). 
    a_vrdelay = ''

    # The nominal voltage at which the nominal reactive power was measured. This should normally be within 10% of the voltage at which the capacitor is connected to the network. 
    nom_u = ''

    # Voltage sensitivity required for the device to regulate the bus voltage, in voltage/reactive power. 
    voltage_sensitivity = ''

    # For a capacitor bank, the size in reactive power of each switchable section at the nominal voltage. 
    reactive_per_section = ''

    # For a capacitor bank, the maximum number of sections that may be switched in. 
    maximum_sections = 0

    # The date and time when the capacitor bank was last switched on. 
    switch_on_date = ''

    # For a capacitor bank, the normal number of sections switched in. This number should correspond to the nominal reactive power (nomQ). 
    normal_sections = 0

    # Zero sequence shunt (charging) susceptance per section 
    b0_per_section = ''

    # The minimum voltage at which the capacitor bank should operate. 
    min_u = ''

    # Zero sequence shunt (charging) conductance per section 
    g0_per_section = ''

    # For a capacitor bank, the admittance of each switchable section. Calculated using the reactive power per section and corrected for network voltage. 
    y_per_section = ''

    # The switch on count since the capacitor count was last reset or initialized. 
    switch_on_count = 0

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

    # <<< shunt_compensator
    # @generated
    def __init__(self, nom_q='', max_u='', a_vrdelay='', nom_u='', voltage_sensitivity='', reactive_per_section='', maximum_sections=0, switch_on_date='', normal_sections=0, b0_per_section='', min_u='', g0_per_section='', y_per_section='', switch_on_count=0, sv_shunt_compensator_sections=None, **kw_args):
        """ Initialises a new 'ShuntCompensator' instance.
        """
        self.nom_q = nom_q
        self.max_u = max_u
        self.a_vrdelay = a_vrdelay
        self.nom_u = nom_u
        self.voltage_sensitivity = voltage_sensitivity
        self.reactive_per_section = reactive_per_section
        self.maximum_sections = maximum_sections
        self.switch_on_date = switch_on_date
        self.normal_sections = normal_sections
        self.b0_per_section = b0_per_section
        self.min_u = min_u
        self.g0_per_section = g0_per_section
        self.y_per_section = y_per_section
        self.switch_on_count = switch_on_count
        self._sv_shunt_compensator_sections = None
        self.sv_shunt_compensator_sections = sv_shunt_compensator_sections

        super(ShuntCompensator, self).__init__(**kw_args)
    # >>> shunt_compensator


class ProtectedSwitch(Switch):
    """ A ProtectedSwitch is a switching device that can be operated by ProtectionEquipment.
    """
    def get_reclose_sequences(self):
        """ A breaker may have zero or more automatic reclosures after a trip occurs.
        """
        return self._reclose_sequences

    def set_reclose_sequences(self, value):
        for x in self._reclose_sequences:
            x._protected_switch = None
        for y in value:
            y._protected_switch = self
        self._reclose_sequences = value
            
    reclose_sequences = property(get_reclose_sequences, set_reclose_sequences)
    
    def add_reclose_sequences(self, *reclose_sequences):
        for obj in reclose_sequences:
            obj._protected_switch = self
            self._reclose_sequences.append(obj)
        
    def remove_reclose_sequences(self, *reclose_sequences):
        for obj in reclose_sequences:
            obj._protected_switch = None
            self._reclose_sequences.remove(obj)

    protection_equipments = []
    
    def add_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
	        self._protection_equipments.append(obj)
        
    def remove_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
	        self._protection_equipments.remove(obj)

    # <<< protected_switch
    # @generated
    def __init__(self, reclose_sequences=[], protection_equipments=[], **kw_args):
        """ Initialises a new 'ProtectedSwitch' instance.
        """
        self._reclose_sequences = []
        self.reclose_sequences = reclose_sequences
        self._protection_equipments = []
        self.protection_equipments = protection_equipments

        super(ProtectedSwitch, self).__init__(**kw_args)
    # >>> protected_switch


class Fuse(Switch):
    """ An overcurrent protective device with a circuit opening fusible part that is heated and severed by the passage of overcurrent through it. A fuse is considered a switching device because it breaks current.
    """
    # Fault interrupting current rating. 
    amp_rating = ''

    # <<< fuse
    # @generated
    def __init__(self, amp_rating='', **kw_args):
        """ Initialises a new 'Fuse' instance.
        """
        self.amp_rating = amp_rating

        super(Fuse, self).__init__(**kw_args)
    # >>> fuse


class PhaseTapChanger(TapChanger):
    """ A specialization of a voltage tap changer that has detailed modeling for phase shifting capabilities.   A phase shifting tap changer is also in general a voltage magnitude transformer.    The symmetrical and asymmetrical transformer tap changer models are defined here.
    """
    # Similar to TapChanger.nominalVoltage, but this is the nominal voltage in the out of phase winding at the nominal tap step. A typical case may have zero voltage at the nominal step, indicating no phase shift at the nominal voltage. 
    nominal_voltage_out_of_phase = ''

    # The reactance at the maximum tap step. 
    x_step_max = ''

    # The type of phase shifter construction. Values are: "unknown", "asymmetrical", "symmetrical"
    phase_tap_changer_type = 'unknown'

    # The reactance at the minimum tap step. 
    x_step_min = ''

    # The phase angle between the in-phase winding and the out-of -phase winding used for creating phase shift.   It is only possible to have a symmemtrical transformer if this angle is 90 degrees. 
    winding_connection_angle = ''

    # The voltage step increment on the out of phase winding.    This voltage step on the out of phase winding of the phase shifter.  Similar to TapChanger.voltageStepIncrement, but it is applied only to the out of phase winding. 
    voltage_step_increment_out_of_phase = ''

    # Phase shift per step position. A positive value indicates a positive phase shift from the winding where the tap is located to the other winding (for a two-winding transformer). The actual phase shift increment might be more accureatly computed from the symmetrical or asymmetrical models or a tap step table lookup if those are available. 
    step_phase_shift_increment = ''

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

    # <<< phase_tap_changer
    # @generated
    def __init__(self, nominal_voltage_out_of_phase='', x_step_max='', phase_tap_changer_type='unknown', x_step_min='', winding_connection_angle='', voltage_step_increment_out_of_phase='', step_phase_shift_increment='', transformer_winding=None, **kw_args):
        """ Initialises a new 'PhaseTapChanger' instance.
        """
        self.nominal_voltage_out_of_phase = nominal_voltage_out_of_phase
        self.x_step_max = x_step_max
        self.phase_tap_changer_type = phase_tap_changer_type
        self.x_step_min = x_step_min
        self.winding_connection_angle = winding_connection_angle
        self.voltage_step_increment_out_of_phase = voltage_step_increment_out_of_phase
        self.step_phase_shift_increment = step_phase_shift_increment
        self._transformer_winding = None
        self.transformer_winding = transformer_winding

        super(PhaseTapChanger, self).__init__(**kw_args)
    # >>> phase_tap_changer


class Breaker(ProtectedSwitch):
    """ A mechanical switching device capable of making, carrying, and breaking currents under normal circuit conditions and also making, carrying for a specified time, and breaking currents under specified abnormal circuit conditions e.g.  those of short circuit.
    """
    # The transition time from open to close. 
    in_transit_time = ''

    # Fault interrupting current rating. 
    rated_current = ''

    # <<< breaker
    # @generated
    def __init__(self, in_transit_time='', rated_current='', **kw_args):
        """ Initialises a new 'Breaker' instance.
        """
        self.in_transit_time = in_transit_time
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
    # Current carrying capacity of a wire or cable under stated thermal conditions. 
    rated_current = ''

    # <<< load_break_switch
    # @generated
    def __init__(self, rated_current='', **kw_args):
        """ Initialises a new 'LoadBreakSwitch' instance.
        """
        self.rated_current = rated_current

        super(LoadBreakSwitch, self).__init__(**kw_args)
    # >>> load_break_switch


# <<< wires
# @generated
# >>> wires

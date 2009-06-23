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

    from_winding_insulations = []

    # The winding winding tests for which the transformer winding (terminal) participates as the 'to' end of the test.
    to_winding_test = []

    # The ratio tap changer associated with the transformer winding.
    ratio_tap_changer = None

    # A transformer has windings
    power_transformer = None

    # The transformer winding tests for which the transformer winding (terminal) participates as the 'from' part of the test.
    from_winding_test = []

    # The phase tap changer associated with the transformer winding.
    phase_tap_changer = None

    to_winding_insulations = []

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
        self.from_winding_insulations = from_winding_insulations
        self.to_winding_test = to_winding_test
        self.ratio_tap_changer = ratio_tap_changer
        self.power_transformer = power_transformer
        self.from_winding_test = from_winding_test
        self.phase_tap_changer = phase_tap_changer
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

    # copy from reg conduting eq
    tap_changer = []

    # copy from reg cond eq
    regulating_cond_eq = []

    # Schedule for this Regulating regulating control.
    regulation_schedule = None

    # The terminal associated with this regulating control.
    terminal = None

    # <<< regulating_control
    # @generated
    def __init__(self, target_range=0.0, discrete=False, target_value=0.0, mode='admittance', tap_changer=[], regulating_cond_eq=[], regulation_schedule=None, terminal=None, **kw_args):
        """ Initialises a new 'RegulatingControl' instance.
        """
        self.target_range = target_range
        self.discrete = discrete
        self.target_value = target_value
        self.mode = mode
        self.tap_changer = tap_changer
        self.regulating_cond_eq = regulating_cond_eq
        self.regulation_schedule = regulation_schedule
        self.terminal = terminal

        super(RegulatingControl, self).__init__(**kw_args)
    # >>> regulating_control


class RegulatingCondEq(ConductingEquipment):
    """ RegulatingCondEq is a type of ConductingEquipment that can regulate Measurements and have a RegulationSchedule.
    """
    # copy from ...
    regulating_control = None

    # The controller outputs used to actually govern a regulating device, e.g. the magnetization of a synchronous machine or capacitor bank breaker actuator.
    controls = []

    # <<< regulating_cond_eq
    # @generated
    def __init__(self, regulating_control=None, controls=[], **kw_args):
        """ Initialises a new 'RegulatingCondEq' instance.
        """
        self.regulating_control = regulating_control
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

    # Composite switch this Switch belongs to
    composite_switch = None

    load_mgmt_functions = []

    # A switch may be operated by many schedules.
    switching_operations = []

    # <<< switch
    # @generated
    def __init__(self, switch_on_count=0, normal_open=False, retained=False, switch_on_date='', connect_disconnect_functions=[], composite_switch=None, load_mgmt_functions=[], switching_operations=[], **kw_args):
        """ Initialises a new 'Switch' instance.
        """
        self.switch_on_count = switch_on_count
        self.normal_open = normal_open
        self.retained = retained
        self.switch_on_date = switch_on_date
        self.connect_disconnect_functions = connect_disconnect_functions
        self.composite_switch = composite_switch
        self.load_mgmt_functions = load_mgmt_functions
        self.switching_operations = switching_operations

        super(Switch, self).__init__(**kw_args)
    # >>> switch


class Line(EquipmentContainer):
    """ A component part of a system extending between adjacent substations or from a substation to an adjacent interconnection point.
    """
    # A transmission line can be part of a transmission corridor
    transmission_right_of_way = None

    # A Line can be contained by a SubGeographical Region.
    region = None

    flowgates = []

    # <<< line
    # @generated
    def __init__(self, transmission_right_of_way=None, region=None, flowgates=[], **kw_args):
        """ Initialises a new 'Line' instance.
        """
        self.transmission_right_of_way = transmission_right_of_way
        self.region = region
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

    # Synchronous machines using this curve.
    synchronous_machines = []

    # Synchronous machines using this curve as default.
    initially_used_by_synchronous_machines = []

    # <<< reactive_capability_curve
    # @generated
    def __init__(self, hydrogen_pressure='', coolant_temperature='', synchronous_machines=[], initially_used_by_synchronous_machines=[], **kw_args):
        """ Initialises a new 'ReactiveCapabilityCurve' instance.
        """
        self.hydrogen_pressure = hydrogen_pressure
        self.coolant_temperature = coolant_temperature
        self.synchronous_machines = synchronous_machines
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

    service_delivery_points = []

    phase_loads = []

    # An energy consumer is assigned to a power cut zone
    power_cut_zone = None

    # The load response characteristic of this load.
    load_response = None

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
        self.service_delivery_points = service_delivery_points
        self.phase_loads = phase_loads
        self.power_cut_zone = power_cut_zone
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

    # Regulating controls that have this Schedule.
    regulating_control = []

    # A VoltageControlZone may have a  voltage regulation schedule.
    voltage_control_zones = []

    # <<< regulation_schedule
    # @generated
    def __init__(self, line_drop_r='', line_drop_compensation=False, line_drop_x='', regulating_control=[], voltage_control_zones=[], **kw_args):
        """ Initialises a new 'RegulationSchedule' instance.
        """
        self.line_drop_r = line_drop_r
        self.line_drop_compensation = line_drop_compensation
        self.line_drop_x = line_drop_x
        self.regulating_control = regulating_control
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
    resistor_type_asset = None

    resistor_asset = None

    # <<< resistor
    # @generated
    def __init__(self, resistor_type_asset=None, resistor_asset=None, **kw_args):
        """ Initialises a new 'Resistor' instance.
        """
        self.resistor_type_asset = resistor_type_asset
        self.resistor_asset = resistor_asset

        super(Resistor, self).__init__(**kw_args)
    # >>> resistor


class CompositeSwitch(Equipment):
    """ A model of a set of individual Switches normally enclosed within the same cabinet and possibly with interlocks that restrict the combination of switch positions. These are typically found in medium voltage distribution networks.  A CompositeSwitch could represent a Ring-Main-Unit (RMU), or pad-mounted switchgear, with primitive internal devices such as an internal bus-bar plus 3 or 4 internal switches each of which may individually be open or closed. A CompositeSwitch and a set of contained Switches can also be used to represent a multi-position switch e.g. a switch that can connect a circuit to Ground, Open or Busbar.
    """
    # An alphanumeric code that can be used as a reference to extar information such as the description of the interlocking scheme if any 
    composite_switch_type = ''

    # Switches contained in this Composite switch.
    switches = []

    # <<< composite_switch
    # @generated
    def __init__(self, composite_switch_type='', switches=[], **kw_args):
        """ Initialises a new 'CompositeSwitch' instance.
        """
        self.composite_switch_type = composite_switch_type
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

    # The winding from which the test was conducted
    from_transformer_winding = None

    # The winding to which the test was conducted.  Note that although the 'from' side of the test is required, the 'to' side of a test is not always required.
    to_transformer_winding = None

    transformer_observations = []

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
        self.from_transformer_winding = from_transformer_winding
        self.to_transformer_winding = to_transformer_winding
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

    # The tap step state associated with the tap changer.
    sv_tap_step = None

    regulating_control = None

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
        self.sv_tap_step = sv_tap_step
        self.regulating_control = regulating_control

        super(TapChanger, self).__init__(**kw_args)
    # >>> tap_changer


class HeatExchanger(Equipment):
    """ Equipment for the cooling of electrical equipment and the extraction of heat
    """
    # A transformer may have a heat exchanger
    power_transformer = None

    # <<< heat_exchanger
    # @generated
    def __init__(self, power_transformer=None, **kw_args):
        """ Initialises a new 'HeatExchanger' instance.
        """
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

    # The starting terminal for the calculation of distances along the first branch of the mutual coupling.  Normally MutualCoupling would only be used for terminals of AC line segments.  The first and second terminals of a mutual coupling should point to different AC line segments.
    first_terminal = None

    # The starting terminal for the calculation of distances along the second branch of the mutual coupling.
    second_terminal = None

    # <<< mutual_coupling
    # @generated
    def __init__(self, b0ch='', g0ch='', r0='', x0='', first_terminal=None, second_terminal=None, **kw_args):
        """ Initialises a new 'MutualCoupling' instance.
        """
        self.b0ch = b0ch
        self.g0ch = g0ch
        self.r0 = r0
        self.x0 = x0
        self.first_terminal = first_terminal
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
    # A VoltageControlZone may have a  voltage regulation schedule.
    regulation_schedule = None

    # A VoltageControlZone is controlled by a designated BusbarSection.
    busbar_section = None

    # <<< voltage_control_zone
    # @generated
    def __init__(self, regulation_schedule=None, busbar_section=None, **kw_args):
        """ Initialises a new 'VoltageControlZone' instance.
        """
        self.regulation_schedule = regulation_schedule
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

    # Conductor type physically describing sections of this conductor.
    conductor_type = None

    linear_conductor_assets = []

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
        self.linear_conductor_type_assets = linear_conductor_type_assets
        self.conductor_type = conductor_type
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
    winding_insulations = []

    # <<< ground
    # @generated
    def __init__(self, winding_insulations=[], **kw_args):
        """ Initialises a new 'Ground' instance.
        """
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

    # A transformer has windings
    transformer_windings = []

    # A transformer may have a heat exchanger
    heat_exchanger = None

    flowgates = []

    # <<< power_transformer
    # @generated
    def __init__(self, bmag_sat='', mag_sat_flux='', phases='abn', mag_base_u='', transformer_windings=[], heat_exchanger=None, flowgates=[], **kw_args):
        """ Initialises a new 'PowerTransformer' instance.
        """
        self.bmag_sat = bmag_sat
        self.mag_sat_flux = mag_sat_flux
        self.phases = phases
        self.mag_base_u = mag_base_u
        self.transformer_windings = transformer_windings
        self.heat_exchanger = heat_exchanger
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
    # A VoltageControlZone is controlled by a designated BusbarSection.
    voltage_control_zone = None

    # <<< busbar_section
    # @generated
    def __init__(self, voltage_control_zone=None, **kw_args):
        """ Initialises a new 'BusbarSection' instance.
        """
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

    # The default ReactiveCapabilityCurve for use by a SynchronousMachine
    initial_reactive_capability_curve = None

    # The synchronous machine drives the turbine which moves the water from a low elevation to a higher elevation. The direction of machine rotation for pumping may or may not be the same as for generating.
    hydro_pump = None

    # All available Reactive capability curves for this SynchronousMachine.
    reactive_capability_curves = []

    # A synchronous machine may operate as a generator and as such becomes a member of a generating unit
    generating_unit = None

    # Prime movers that drive this SynchronousMachine.
    prime_movers = []

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
        self.initial_reactive_capability_curve = initial_reactive_capability_curve
        self.hydro_pump = hydro_pump
        self.reactive_capability_curves = reactive_capability_curves
        self.generating_unit = generating_unit
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
    # The transformer winding to which the ratio tap changer belongs.
    transformer_winding = None

    # <<< ratio_tap_changer
    # @generated
    def __init__(self, transformer_winding=None, **kw_args):
        """ Initialises a new 'RatioTapChanger' instance.
        """
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

    # The state for the number of shunt compensator sections in service.
    sv_shunt_compensator_sections = None

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
        self.sv_shunt_compensator_sections = sv_shunt_compensator_sections

        super(ShuntCompensator, self).__init__(**kw_args)
    # >>> shunt_compensator


class ProtectedSwitch(Switch):
    """ A ProtectedSwitch is a switching device that can be operated by ProtectionEquipment.
    """
    # A breaker may have zero or more automatic reclosures after a trip occurs.
    reclose_sequences = []

    # Protection equipments that operate this ProtectedSwitch.
    protection_equipments = []

    # <<< protected_switch
    # @generated
    def __init__(self, reclose_sequences=[], protection_equipments=[], **kw_args):
        """ Initialises a new 'ProtectedSwitch' instance.
        """
        self.reclose_sequences = reclose_sequences
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

    # The transformer winding to which the phase tap changer belongs.
    transformer_winding = None

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

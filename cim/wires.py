# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim.core import RegularIntervalSchedule
from cim.core import ConductingEquipment
from cim.core import IdentifiedObject
from cim.core import PowerSystemResource
from cim.core import EquipmentContainer
from cim.core import Equipment
from cim import Element
from cim.core import Curve

# <<< imports
# @generated
# >>> imports

class RegulationSchedule(RegularIntervalSchedule):
    """ A pre-established pattern over time for a controlled variable, e.g., busbar voltage.
    """
    # Line drop resistance. 
    line_drop_r = 0.0

    # Flag to indicate that line drop compensation is to be applied 
    line_drop_compensation = False

    # Line drop reactance. 
    line_drop_x = 0.0

    regulating_control = []

    # A VoltageControlZone may have a  voltage regulation schedule.
    voltage_control_zones = []

    # <<< regulation_schedule
    # @generated
    def __init__(self, line_drop_r=0.0, line_drop_compensation=False, line_drop_x=0.0, regulating_control=[], voltage_control_zones=[], **kw_args):
        """ Initialises a new 'RegulationSchedule' instance.
        """
        self.line_drop_r = line_drop_r
        self.line_drop_compensation = line_drop_compensation
        self.line_drop_x = line_drop_x
        self.regulating_control = regulating_control
        self.voltage_control_zones = voltage_control_zones

        super(RegulationSchedule, self).__init__(**kw_args)
    # >>> regulation_schedule


class TransformerWinding(ConductingEquipment):
    """ A winding is associated with each defined terminal of a transformer (or phase shifter).
    """
    # The rated voltage (phase-to-phase) of the winding, usually the same as the neutral voltage. 
    rated_u = 0.0

    # Ground resistance path through connected grounding transformer. 
    rground = 0.0

    # Zero sequence magnetizing branch susceptance. 
    b0 = 0.0

    # Positive sequence series resistance of the winding. 
    r = 0.0

    # Ground reactance path through connected grounding transformer. 
    xground = 0.0

    # Magnetizing branch susceptance (B mag). 
    b = 0.0

    # Zero sequence series resistance of the winding. 
    r0 = 0.0

    # Set if the winding is grounded. 
    grounded = False

    # Apparent power that the winding can carry for a short period of time. 
    short_term_s = 0.0

    # The normal apparent power rating for the winding 
    rated_s = 0.0

    # Positive sequence series reactance of the winding. 
    x = 0.0

    # The type of winding. Values are: "quaternary", "tertiary", "primary", "secondary"
    winding_type = 'quaternary'

    # The apparent power that the winding can carry  under emergency conditions. 
    emergency_s = 0.0

    # Basic insulation level voltage rating 
    insulation_u = 0.0

    # Magnetizing branch conductance (G mag). 
    g = 0.0

    # Zero sequence magnetizing branch conductance. 
    g0 = 0.0

    # Zero sequence series reactance of the winding. 
    x0 = 0.0

    # The type of connection of the winding. Values are: "d", "z", "y"
    connection_type = 'd'

    # The winding to which the test was conducted
    to_winding_test = []

    # The winding from which the test was conducted
    from_winding_test = []

    # A transformer winding may have tap changers, separately for voltage and phase angle.  If a TransformerWinding does not have an associated TapChanger, the winding is assumed to be fixed tap.
    tap_changers = []

    # A transformer has windings
    member_of_power_transformer = None

    # <<< transformer_winding
    # @generated
    def __init__(self, rated_u=0.0, rground=0.0, b0=0.0, r=0.0, xground=0.0, b=0.0, r0=0.0, grounded=False, short_term_s=0.0, rated_s=0.0, x=0.0, winding_type='quaternary', emergency_s=0.0, insulation_u=0.0, g=0.0, g0=0.0, x0=0.0, connection_type='d', to_winding_test=[], from_winding_test=[], tap_changers=[], member_of_power_transformer=None, **kw_args):
        """ Initialises a new 'TransformerWinding' instance.
        """
        self.rated_u = rated_u
        self.rground = rground
        self.b0 = b0
        self.r = r
        self.xground = xground
        self.b = b
        self.r0 = r0
        self.grounded = grounded
        self.short_term_s = short_term_s
        self.rated_s = rated_s
        self.x = x
        self.winding_type = winding_type
        self.emergency_s = emergency_s
        self.insulation_u = insulation_u
        self.g = g
        self.g0 = g0
        self.x0 = x0
        self.connection_type = connection_type
        self.to_winding_test = to_winding_test
        self.from_winding_test = from_winding_test
        self.tap_changers = tap_changers
        self.member_of_power_transformer = member_of_power_transformer

        super(TransformerWinding, self).__init__(**kw_args)
    # >>> transformer_winding


class EnergySource(ConductingEquipment):
    """ A generic equivalent for an energy supplier on a transmission or distribution voltage level.
    """
    # Zero sequence Thevenin resistance. 
    r0 = 0.0

    # Phase-to-phase nominal voltage. 
    nominal_voltage = 0.0

    # Positive sequence Thevenin resistance. 
    r = 0.0

    # Phase-to-phase open circuit voltage magnitude. 
    voltage_magnitude = 0.0

    # Positive sequence Thevenin reactance. 
    x = 0.0

    # Phase angle of a-phase open circuit. 
    voltage_angle = 0.0

    # Negative sequence Thevenin resistance. 
    rn = 0.0

    # High voltage source load 
    active_power = 0.0

    # Negative sequence Thevenin reactance. 
    xn = 0.0

    # Zero sequence Thevenin reactance. 
    x0 = 0.0

    # <<< energy_source
    # @generated
    def __init__(self, r0=0.0, nominal_voltage=0.0, r=0.0, voltage_magnitude=0.0, x=0.0, voltage_angle=0.0, rn=0.0, active_power=0.0, xn=0.0, x0=0.0, **kw_args):
        """ Initialises a new 'EnergySource' instance.
        """
        self.r0 = r0
        self.nominal_voltage = nominal_voltage
        self.r = r
        self.voltage_magnitude = voltage_magnitude
        self.x = x
        self.voltage_angle = voltage_angle
        self.rn = rn
        self.active_power = active_power
        self.xn = xn
        self.x0 = x0

        super(EnergySource, self).__init__(**kw_args)
    # >>> energy_source


class SeriesCompensator(ConductingEquipment):
    """ A Series Compensator is a series capacitor or reactor or an AC transmission line without charging susceptance.
    """
    # Positive sequence reactance. 
    x = 0.0

    # Positive sequence resistance. 
    r = 0.0

    # <<< series_compensator
    # @generated
    def __init__(self, x=0.0, r=0.0, **kw_args):
        """ Initialises a new 'SeriesCompensator' instance.
        """
        self.x = x
        self.r = r

        super(SeriesCompensator, self).__init__(**kw_args)
    # >>> series_compensator


class WireType(IdentifiedObject):
    """ Wire conductor (per IEEE specs). A specific type of wire or combination of wires, not insulated from each other, suitable for carrying electrical current.
    """
    # Number of conductor strands in the (symmetrical) bundle (1-12) 
    phase_conductor_count = 0

    # Geometric Mean Radius. If we replace the conductor by a thin walled tube of radius GMR, then its reactance is identical to the reactance of the actual conductor. 
    g_mr = 0.0

    # Distance between conductor strands in a (symmetrical) bundle. 
    phase_conductor_spacing = 0.0

    # Current carrying capacity of a wire or cable under stated thermal conditions 
    rated_current = 0.0

    # The radius of the conductor 
    radius = 0.0

    # The resistance per unit length of the conductor 
    resistance = 0.0

    # A WireType is mounted at a specified place in a WireArrangement.
    wire_arrangements = []

    # <<< wire_type
    # @generated
    def __init__(self, phase_conductor_count=0, g_mr=0.0, phase_conductor_spacing=0.0, rated_current=0.0, radius=0.0, resistance=0.0, wire_arrangements=[], **kw_args):
        """ Initialises a new 'WireType' instance.
        """
        self.phase_conductor_count = phase_conductor_count
        self.g_mr = g_mr
        self.phase_conductor_spacing = phase_conductor_spacing
        self.rated_current = rated_current
        self.radius = radius
        self.resistance = resistance
        self.wire_arrangements = wire_arrangements

        super(WireType, self).__init__(**kw_args)
    # >>> wire_type


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


class RegulatingCondEq(ConductingEquipment):
    """ RegulatingCondEq is a type of ConductingEquipment that can regulate Measurements and have a RegulationSchedule.
    """
    regulating_control = None

    # The association gives the control output that is used to actually govern a regulating device, e.g. the magnetization of a synchronous machine or capacitor bank breaker actuators.
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


class Conductor(ConductingEquipment):
    """ Combination of conducting material with consistent electrical characteristics, building a single electrical system, used to carry current between points in the power system.
    """
    # Zero sequence series resistance of the entire line section. 
    r0 = 0.0

    # Zero sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
    g0ch = 0.0

    # Segment length for calculating line section capabilities 
    length = 0.0

    # Positive sequence series reactance of the entire line section. 
    x = 0.0

    # Positive sequence shunt (charging) conductance, uniformly distributed, of the entire line section. 
    gch = 0.0

    # Zero sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    b0ch = 0.0

    # Positive sequence shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    bch = 0.0

    # Zero sequence series reactance of the entire line section. 
    x0 = 0.0

    # Positive sequence series resistance of the entire line section. 
    r = 0.0

    # Sections of conductor are physically described by a conductor type
    conductor_type = None

    # <<< conductor
    # @generated
    def __init__(self, r0=0.0, g0ch=0.0, length=0.0, x=0.0, gch=0.0, b0ch=0.0, bch=0.0, x0=0.0, r=0.0, conductor_type=None, **kw_args):
        """ Initialises a new 'Conductor' instance.
        """
        self.r0 = r0
        self.g0ch = g0ch
        self.length = length
        self.x = x
        self.gch = gch
        self.b0ch = b0ch
        self.bch = bch
        self.x0 = x0
        self.r = r
        self.conductor_type = conductor_type

        super(Conductor, self).__init__(**kw_args)
    # >>> conductor


class Line(EquipmentContainer):
    """ A component part of a system extending between adjacent substations or from a substation to an adjacent interconnection point.
    """
    # A Line can be contained by a SubGeographical Region.
    region = None

    # <<< line
    # @generated
    def __init__(self, region=None, **kw_args):
        """ Initialises a new 'Line' instance.
        """
        self.region = region

        super(Line, self).__init__(**kw_args)
    # >>> line


class Ground(ConductingEquipment):
    """ A common point for connecting grounded conducting equipment such as shunt capacitors. The power system model can have more than one ground.
    """
    pass
    # <<< ground
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Ground' instance.
        """

        super(Ground, self).__init__(**kw_args)
    # >>> ground


class TapChanger(PowerSystemResource):
    """ Mechanism for changing transformer winding tap positions.
    """
    # The neutral tap step position for this winding. 
    neutral_step = 0

    # Lowest possible tap step position, retard from neutral 
    low_step = 0

    # For an LTC, the tap changer control mode. Values are: "volt", "off", "local", "reactive", "active"
    tcul_control_mode = 'volt'

    # Voltage at which the winding operates at the neutral tap setting. 
    neutral_u = 0.0

    # The tap step position used in 'normal' network operation for this winding. For a 'Fixed' tap changer indicates the current physical tap setting. 
    normal_step = 0

    # Highest possible tap step position, advance from neutral 
    high_step = 0

    # The type of tap changer. Indicates the ability of the transformer to perform various regulation tasks. The tap changer must be also be associated wtih a RegulationControl object before any regulation is possible. Values are: "voltage_control", "fixed", "phase_control", "voltage_and_phase_control"
    type = 'voltage_control'

    # Tap step increment, in per cent of nominal voltage, per step position. 
    step_voltage_increment = 0.0

    # For an LTC, the delay for subsequent tap changer operation (second and later step changes) 
    subsequent_delay = 0.0

    # For an LTC, the delay for initial tap changer operation (first step change) 
    initial_delay = 0.0

    # Phase shift per step position. A positive value indicates a positive phase shift from the winding where the tap is located to the other winding (for a two-winding transformer). 
    step_phase_shift_increment = 0.0

    # A transformer winding may have tap changers, separately for voltage and phase angle.  If a TransformerWinding does not have an associated TapChanger, the winding is assumed to be fixed tap.
    transformer_winding = None

    regulating_control = None

    # <<< tap_changer
    # @generated
    def __init__(self, neutral_step=0, low_step=0, tcul_control_mode='volt', neutral_u=0.0, normal_step=0, high_step=0, type='voltage_control', step_voltage_increment=0.0, subsequent_delay=0.0, initial_delay=0.0, step_phase_shift_increment=0.0, transformer_winding=None, regulating_control=None, **kw_args):
        """ Initialises a new 'TapChanger' instance.
        """
        self.neutral_step = neutral_step
        self.low_step = low_step
        self.tcul_control_mode = tcul_control_mode
        self.neutral_u = neutral_u
        self.normal_step = normal_step
        self.high_step = high_step
        self.type = type
        self.step_voltage_increment = step_voltage_increment
        self.subsequent_delay = subsequent_delay
        self.initial_delay = initial_delay
        self.step_phase_shift_increment = step_phase_shift_increment
        self.transformer_winding = transformer_winding
        self.regulating_control = regulating_control

        super(TapChanger, self).__init__(**kw_args)
    # >>> tap_changer


class CompositeSwitch(Equipment):
    """ A model of a set of individual Switches normally enclosed within the same cabinet and possibly with interlocks that restrict the combination of switch positions. These are typically found in medium voltage distribution networks.  A CompositeSwitch could represent a Ring-Main-Unit (RMU), or pad-mounted switchgear, with primitive internal devices such as an internal bus-bar plus 3 or 4 internal switches each of which may individually be open or closed. A CompositeSwitch and a set of contained Switches can also be used to represent a multi-position switch e.g. a switch that can connect a circuit to Ground, Open or Busbar.
    """
    # An alphanumeric code that can be used as a reference to extar information such as the description of the interlocking scheme if any 
    composite_switch_type = ''

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


class WireArrangement(IdentifiedObject):
    """ Identification, spacing and configuration of the wires of a ConductorType, with reference to their type.
    """
    # Mounting point where wire One is mounted. 
    mounting_point_x = 0

    # Mounting point where wire One is mounted. 
    mounting_point_y = 0

    # A ConductorType is made up of wires that can be configured in several ways.
    conductor_type = None

    # A WireType is mounted at a specified place in a WireArrangement.
    wire_type = None

    # <<< wire_arrangement
    # @generated
    def __init__(self, mounting_point_x=0, mounting_point_y=0, conductor_type=None, wire_type=None, **kw_args):
        """ Initialises a new 'WireArrangement' instance.
        """
        self.mounting_point_x = mounting_point_x
        self.mounting_point_y = mounting_point_y
        self.conductor_type = conductor_type
        self.wire_type = wire_type

        super(WireArrangement, self).__init__(**kw_args)
    # >>> wire_arrangement


class PowerTransformer(Equipment):
    """ An electrical device consisting of  two or more coupled windings, with or without a magnetic core, for introducing mutual coupling between electric circuits. Transformers can be used to control voltage and phase shift (active power flow).
    """
    # Type of transformer cooling 
    transf_cooling_type = ''

    # Describes the phases carried by a power transformer. Values are: "bcn", "acn", "ab", "a", "b", "abcn", "ac", "n", "an", "c", "abn", "bn", "abc", "bc", "cn"
    phases = 'bcn'

    # Core shunt magnetizing susceptance in the saturation region. 
    bmag_sat = 0.0

    # Core magnetizing saturation curve knee flux level. 
    mag_sat_flux = 0.0

    # The reference voltage at which the magnetizing saturation measurements were made 
    mag_base_u = 0.0

    # A transformer has windings
    contains_transformer_windings = []

    # A transformer may have a heat exchanger
    heat_exchanger = None

    # <<< power_transformer
    # @generated
    def __init__(self, transf_cooling_type='', phases='bcn', bmag_sat=0.0, mag_sat_flux=0.0, mag_base_u=0.0, contains_transformer_windings=[], heat_exchanger=None, **kw_args):
        """ Initialises a new 'PowerTransformer' instance.
        """
        self.transf_cooling_type = transf_cooling_type
        self.phases = phases
        self.bmag_sat = bmag_sat
        self.mag_sat_flux = mag_sat_flux
        self.mag_base_u = mag_base_u
        self.contains_transformer_windings = contains_transformer_windings
        self.heat_exchanger = heat_exchanger

        super(PowerTransformer, self).__init__(**kw_args)
    # >>> power_transformer


class MutualCoupling(Element):
    """ This class represents the zero sequence line mutual coupling.
    """
    # Zero sequence branch-to-branch mutual impedance coupling, reactance 
    x0 = 0.0

    # Distance from the second line's from bus to end of coupled region 
    distance22 = 0.0

    # Distance from the first line's from bus to start of coupled region 
    distance11 = 0.0

    # Distance from the first line's from bus to end of coupled region 
    distance12 = 0.0

    # Zero sequence branch-to-branch mutual impedance coupling, resistance 
    r0 = 0.0

    # Zero sequence mutual coupling shunt (charging) susceptance, uniformly distributed, of the entire line section. 
    b0ch = 0.0

    # Zero sequence mutual coupling shunt (charging) conductance, uniformly distributed, of the entire line section. 
    g0ch = 0.0

    # Distance from the second line's from bus to start of coupled region 
    distance21 = 0.0

    first_acline_segment = None

    second_acline_segment = None

    # <<< mutual_coupling
    # @generated
    def __init__(self, x0=0.0, distance22=0.0, distance11=0.0, distance12=0.0, r0=0.0, b0ch=0.0, g0ch=0.0, distance21=0.0, first_acline_segment=None, second_acline_segment=None, **kw_args):
        """ Initialises a new 'MutualCoupling' instance.
        """
        self.x0 = x0
        self.distance22 = distance22
        self.distance11 = distance11
        self.distance12 = distance12
        self.r0 = r0
        self.b0ch = b0ch
        self.g0ch = g0ch
        self.distance21 = distance21
        self.first_acline_segment = first_acline_segment
        self.second_acline_segment = second_acline_segment

        super(MutualCoupling, self).__init__(**kw_args)
    # >>> mutual_coupling


class RectifierInverter(ConductingEquipment):
    """ Bi-directional AC-DC conversion equipment that can be used to control DC current, DC voltage, DC power flow, or firing angle.
    """
    # Rectifier/inverter primary base voltage 
    rated_u = 0.0

    # Operating mode for the converter. 
    operating_mode = ''

    # Frequency on the AC side. 
    frequency = 0.0

    # The maximum voltage on the DC side at which the converter should operate. 
    max_u = 0.0

    # Commutating resistance. 
    commutating_resistance = 0.0

    # The minimum active power on the DC side at which the converter should operate. 
    min_p = 0.0

    # Number of bridges 
    bridges = 0

    # Commutating reactance at AC bus frequency. 
    commutating_reactance = 0.0

    # The maximum active power on the DC side at which the fconverter should operate. 
    max_p = 0.0

    # Minimum compounded DC voltage 
    min_compound_voltage = 0.0

    # Compounding resistance. 
    compound_resistance = 0.0

    # The minimum voltage on the DC side at which the converter should operate. 
    min_u = 0.0

    # <<< rectifier_inverter
    # @generated
    def __init__(self, rated_u=0.0, operating_mode='', frequency=0.0, max_u=0.0, commutating_resistance=0.0, min_p=0.0, bridges=0, commutating_reactance=0.0, max_p=0.0, min_compound_voltage=0.0, compound_resistance=0.0, min_u=0.0, **kw_args):
        """ Initialises a new 'RectifierInverter' instance.
        """
        self.rated_u = rated_u
        self.operating_mode = operating_mode
        self.frequency = frequency
        self.max_u = max_u
        self.commutating_resistance = commutating_resistance
        self.min_p = min_p
        self.bridges = bridges
        self.commutating_reactance = commutating_reactance
        self.max_p = max_p
        self.min_compound_voltage = min_compound_voltage
        self.compound_resistance = compound_resistance
        self.min_u = min_u

        super(RectifierInverter, self).__init__(**kw_args)
    # >>> rectifier_inverter


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


class EnergyConsumer(ConductingEquipment):
    """ Generic user of energy - a  point of consumption on the power system model
    """
    # Number of individual customers represented by this Demand 
    customer_count = 0

    # Reactive power of the load that is a fixed quantity. 
    qfixed = 0.0

    # Active power of the load that is a fixed quantity. 
    pfixed = 0.0

    # Fixed reactive power as per cent of load group fixed reactive power. 
    qfixed_pct = 0.0

    # Fixed active power as per cent of load group fixed active power 
    pfixed_pct = 0.0

    # An energy consumer is assigned to a power cut zone
    power_cut_zone = None

    load_response = None

    # <<< energy_consumer
    # @generated
    def __init__(self, customer_count=0, qfixed=0.0, pfixed=0.0, qfixed_pct=0.0, pfixed_pct=0.0, power_cut_zone=None, load_response=None, **kw_args):
        """ Initialises a new 'EnergyConsumer' instance.
        """
        self.customer_count = customer_count
        self.qfixed = qfixed
        self.pfixed = pfixed
        self.qfixed_pct = qfixed_pct
        self.pfixed_pct = pfixed_pct
        self.power_cut_zone = power_cut_zone
        self.load_response = load_response

        super(EnergyConsumer, self).__init__(**kw_args)
    # >>> energy_consumer


class Switch(ConductingEquipment):
    """ A generic device designed to close, or open, or both, one or more electric circuits.
    """
    # Branch is retained in a bus branch model. 
    retained = False

    # The date and time when the switch was last switched on. 
    switch_on_date = ''

    # The attribute is used in cases when no Measurement for the status value is present. If the Switch has a status measurment the Discrete.normalValue is expected to match with the Switch.normalOpen. 
    normal_open = False

    # The switch on count since the switch was last reset or initialized. 
    switch_on_count = 0

    # A switch may be operated by many schedules.
    switching_operations = []

    composite_switch = None

    # <<< switch
    # @generated
    def __init__(self, retained=False, switch_on_date='', normal_open=False, switch_on_count=0, switching_operations=[], composite_switch=None, **kw_args):
        """ Initialises a new 'Switch' instance.
        """
        self.retained = retained
        self.switch_on_date = switch_on_date
        self.normal_open = normal_open
        self.switch_on_count = switch_on_count
        self.switching_operations = switching_operations
        self.composite_switch = composite_switch

        super(Switch, self).__init__(**kw_args)
    # >>> switch


class RegulatingControl(PowerSystemResource):
    """ Specifies a set of equipment that works together to control a power system quantity such as voltage or flow.
    """
    # The target value specified for case input.   This value can be used for the target value wihout the use of schedules. The value has the units appropriate to the mode attribute. 
    target_value = 0.0

    # The regulation is performed in a discrete mode. 
    discrete = False

    # This is the case input target range.   This performs the same function as the value2 attribute on the regulation schedule in the case that schedules are not used.   The units of those appropriate for the mode. 
    target_range = 0.0

    # The regulating control mode presently available.  This specifications allows for determining the kind of regualation without need for obtaining the units from a schedule. Values are: "reactive_power", "current_flow", "fixed", "active_power", "voltage"
    mode = 'reactive_power'

    regulating_cond_eq = []

    tap_changer = []

    terminal = None

    regulation_schedule = None

    # <<< regulating_control
    # @generated
    def __init__(self, target_value=0.0, discrete=False, target_range=0.0, mode='reactive_power', regulating_cond_eq=[], tap_changer=[], terminal=None, regulation_schedule=None, **kw_args):
        """ Initialises a new 'RegulatingControl' instance.
        """
        self.target_value = target_value
        self.discrete = discrete
        self.target_range = target_range
        self.mode = mode
        self.regulating_cond_eq = regulating_cond_eq
        self.tap_changer = tap_changer
        self.terminal = terminal
        self.regulation_schedule = regulation_schedule

        super(RegulatingControl, self).__init__(**kw_args)
    # >>> regulating_control


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


class WindingTest(IdentifiedObject):
    """ Physical winding test data for the winding/tap pairs of a transformer (or phase shifter). This test data can be used to derive other attributes of specific transformer or phase shifter models.
    """
    # The load loss kW ('to' winding short-circuited) from the test report. 
    load_loss = 0.0

    # The tap step number for the 'to' winding of the test pair. 
    to_tap_step = 0

    # The tap step number for the 'from' winding of the test pair. 
    from_tap_step = 0

    # The no load loss kW 'to' winding open-circuited) from the test report. 
    no_load_loss = 0.0

    # The voltage measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
    voltage = 0.0

    # The phase shift measured at the open-circuited 'to' winding, with the 'from' winding set to the 'from' winding's rated voltage and all other windings open-circuited. 
    phase_shift = 0.0

    # The leakage impedance measured at the 'from' winding  with the 'to' winding short-circuited and all other windings open-circuited.  Leakage impedance is expressed in units based on the apparent power and voltage ratings of the 'from' winding. 
    leakage_impedance = 0.0

    # The exciting current on open-circuit test, expressed as a percentage of rated current, at nominal voltage 
    exciting_current = 0.0

    # The winding to which the test was conducted
    to_transformer_winding = None

    # The winding from which the test was conducted
    from_transformer_winding = None

    # <<< winding_test
    # @generated
    def __init__(self, load_loss=0.0, to_tap_step=0, from_tap_step=0, no_load_loss=0.0, voltage=0.0, phase_shift=0.0, leakage_impedance=0.0, exciting_current=0.0, to_transformer_winding=None, from_transformer_winding=None, **kw_args):
        """ Initialises a new 'WindingTest' instance.
        """
        self.load_loss = load_loss
        self.to_tap_step = to_tap_step
        self.from_tap_step = from_tap_step
        self.no_load_loss = no_load_loss
        self.voltage = voltage
        self.phase_shift = phase_shift
        self.leakage_impedance = leakage_impedance
        self.exciting_current = exciting_current
        self.to_transformer_winding = to_transformer_winding
        self.from_transformer_winding = from_transformer_winding

        super(WindingTest, self).__init__(**kw_args)
    # >>> winding_test


class ConductorType(IdentifiedObject):
    """ Wire or cable conductor (per IEEE specs). A specific type of wire or combination of wires not insulated from one another, suitable for carrying electric current. It may be bare or insulated.
    """
    # Reactance of the sheath for cable conductors. 
    sheath_reactance = 0.0

    # Resistance of the sheath for cable conductors. 
    sheath_resistance = 0.0

    # Sections of conductor are physically described by a conductor type
    conductors = []

    # A ConductorType is made up of wires that can be configured in several ways.
    wire_arrangements = []

    # <<< conductor_type
    # @generated
    def __init__(self, sheath_reactance=0.0, sheath_resistance=0.0, conductors=[], wire_arrangements=[], **kw_args):
        """ Initialises a new 'ConductorType' instance.
        """
        self.sheath_reactance = sheath_reactance
        self.sheath_resistance = sheath_resistance
        self.conductors = conductors
        self.wire_arrangements = wire_arrangements

        super(ConductorType, self).__init__(**kw_args)
    # >>> conductor_type


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


class ReactiveCapabilityCurve(Curve):
    """ Reactive power rating envelope versus the synchronous machine's active power, in both the generating and motoring modes. For each active power value there is a corresponding high and low reactive power limit  value. Typically there will be a separate curve for each coolant condition, such as hydrogen pressure.  The Y1 axis values represent reactive minimum and the Y2 axis values represent reactive maximum.
    """
    # The machine's coolant temperature (e.g., ambient air or stator circulating water). 
    coolant_temperature = 0.0

    # The hydrogen coolant pressure 
    hydrogen_pressure = 0.0

    synchronous_machines = []

    # Defines the default MVArCapabilityCurve for use by a SynchronousMachine.
    initially_used_by_synchronous_machine = []

    # <<< reactive_capability_curve
    # @generated
    def __init__(self, coolant_temperature=0.0, hydrogen_pressure=0.0, synchronous_machines=[], initially_used_by_synchronous_machine=[], **kw_args):
        """ Initialises a new 'ReactiveCapabilityCurve' instance.
        """
        self.coolant_temperature = coolant_temperature
        self.hydrogen_pressure = hydrogen_pressure
        self.synchronous_machines = synchronous_machines
        self.initially_used_by_synchronous_machine = initially_used_by_synchronous_machine

        super(ReactiveCapabilityCurve, self).__init__(**kw_args)
    # >>> reactive_capability_curve


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


class FrequencyConverter(RegulatingCondEq):
    """ A device to convert from one frequency to another (e.g., frequency F1 to F2) comprises a pair of FrequencyConverter instances. One converts from F1 to DC, the other converts the DC to F2.
    """
    # The maximum active power on the DC side at which the frequence converter should operate. 
    max_p = 0.0

    # Frequency on the AC side. 
    frequency = 0.0

    # The minimum voltage on the DC side at which the frequency converter should operate. 
    min_u = 0.0

    # The minimum active power on the DC side at which the frequence converter should operate. 
    min_p = 0.0

    # Operating mode for the frequency converter 
    operating_mode = ''

    # The maximum voltage on the DC side at which the frequency converter should operate. 
    max_u = 0.0

    # <<< frequency_converter
    # @generated
    def __init__(self, max_p=0.0, frequency=0.0, min_u=0.0, min_p=0.0, operating_mode='', max_u=0.0, **kw_args):
        """ Initialises a new 'FrequencyConverter' instance.
        """
        self.max_p = max_p
        self.frequency = frequency
        self.min_u = min_u
        self.min_p = min_p
        self.operating_mode = operating_mode
        self.max_u = max_u

        super(FrequencyConverter, self).__init__(**kw_args)
    # >>> frequency_converter


class ProtectedSwitch(Switch):
    """ A ProtectedSwitch is a switching device that can be operated by ProtectionEquipment.
    """
    # Circuit breakers may be operated by protection relays.
    operated_by_protection_equipments = []

    # A breaker may have zero or more automatic reclosures after a trip occurs.
    reclose_sequences = []

    # <<< protected_switch
    # @generated
    def __init__(self, operated_by_protection_equipments=[], reclose_sequences=[], **kw_args):
        """ Initialises a new 'ProtectedSwitch' instance.
        """
        self.operated_by_protection_equipments = operated_by_protection_equipments
        self.reclose_sequences = reclose_sequences

        super(ProtectedSwitch, self).__init__(**kw_args)
    # >>> protected_switch


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


class DCLineSegment(Conductor):
    """ A wire or combination of wires not insulated from one another, with consistent electrical characteristics, used to carry direct current between points in the DC region of the power system.
    """
    # Inductance of the DC line segment. 
    dc_segment_inductance = 0.0

    # Resistance of the DC line segment. 
    dc_segment_resistance = 0.0

    # <<< dcline_segment
    # @generated
    def __init__(self, dc_segment_inductance=0.0, dc_segment_resistance=0.0, **kw_args):
        """ Initialises a new 'DCLineSegment' instance.
        """
        self.dc_segment_inductance = dc_segment_inductance
        self.dc_segment_resistance = dc_segment_resistance

        super(DCLineSegment, self).__init__(**kw_args)
    # >>> dcline_segment


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


class ACLineSegment(Conductor):
    """ A wire or combination of wires, with consistent electrical characteristics, building a single electrical system, used to carry alternating current between points in the power system.
    """
    has_first_mutual_coupling = []

    has_second_mutual_coupling = []

    # <<< acline_segment
    # @generated
    def __init__(self, has_first_mutual_coupling=[], has_second_mutual_coupling=[], **kw_args):
        """ Initialises a new 'ACLineSegment' instance.
        """
        self.has_first_mutual_coupling = has_first_mutual_coupling
        self.has_second_mutual_coupling = has_second_mutual_coupling

        super(ACLineSegment, self).__init__(**kw_args)
    # >>> acline_segment


class ShuntCompensator(RegulatingCondEq):
    """ A shunt capacitor or reactor or switchable bank of shunt capacitors or reactors. A section of a shunt compensator is an individual capacitor or reactor.  Negative values for mVArPerSection and nominalMVAr indicate that the compensator is a reactor.
    """
    # The date and time when the capacitor bank was last switched on. 
    switch_on_date = ''

    # The maximum voltage at which the capacitor bank should operate. 
    max_u = 0.0

    # Time delay required for the device to be connected or disconnected by automatic voltage regulation (AVR). 
    a_vrdelay = 0.0

    # For a capacitor bank, the maximum number of sections that may be switched in. 
    maximum_sections = 0

    # For a capacitor bank, the admittance of each switchable section. Calculated using the reactive power per section and corrected for network voltage. 
    y_per_section = 0.0

    # Voltage sensitivity required for the device to regulate the bus voltage, in voltage/reactive power. 
    voltage_sensitivity = 0.0

    # The switch on count since the capacitor count was last reset or initialized. 
    switch_on_count = 0

    # For a capacitor bank, the normal number of sections switched in. This number should correspond to the nominal reactive power (nomQ). 
    normal_sections = 0

    # Nominal reactive power output of the capacitor bank at the nominal voltage. This number should be positive. 
    nom_q = 0.0

    # For a capacitor bank, the size in reactive power of each switchable section at the nominal voltage. 
    reactive_per_section = 0.0

    # Zero sequence shunt (charging) conductance per section 
    g0_per_section = 0.0

    # The nominal voltage at which the nominal reactive power was measured. This should normally be within 10% of the voltage at which the capacitor is connected to the network. 
    nom_u = 0.0

    # The minimum voltage at which the capacitor bank should operate. 
    min_u = 0.0

    # Zero sequence shunt (charging) susceptance per section 
    b0_per_section = 0.0

    # <<< shunt_compensator
    # @generated
    def __init__(self, switch_on_date='', max_u=0.0, a_vrdelay=0.0, maximum_sections=0, y_per_section=0.0, voltage_sensitivity=0.0, switch_on_count=0, normal_sections=0, nom_q=0.0, reactive_per_section=0.0, g0_per_section=0.0, nom_u=0.0, min_u=0.0, b0_per_section=0.0, **kw_args):
        """ Initialises a new 'ShuntCompensator' instance.
        """
        self.switch_on_date = switch_on_date
        self.max_u = max_u
        self.a_vrdelay = a_vrdelay
        self.maximum_sections = maximum_sections
        self.y_per_section = y_per_section
        self.voltage_sensitivity = voltage_sensitivity
        self.switch_on_count = switch_on_count
        self.normal_sections = normal_sections
        self.nom_q = nom_q
        self.reactive_per_section = reactive_per_section
        self.g0_per_section = g0_per_section
        self.nom_u = nom_u
        self.min_u = min_u
        self.b0_per_section = b0_per_section

        super(ShuntCompensator, self).__init__(**kw_args)
    # >>> shunt_compensator


class Fuse(Switch):
    """ An overcurrent protective device with a circuit opening fusible part that is heated and severed by the passage of overcurrent through it. A fuse is considered a switching device because it breaks current.
    """
    # Fault interrupting current rating. 
    amp_rating = 0.0

    # <<< fuse
    # @generated
    def __init__(self, amp_rating=0.0, **kw_args):
        """ Initialises a new 'Fuse' instance.
        """
        self.amp_rating = amp_rating

        super(Fuse, self).__init__(**kw_args)
    # >>> fuse


class SynchronousMachine(RegulatingCondEq):
    """ An electromechanical device that operates synchronously with the network. It is a single machine operating either as a generator or synchronous condenser or pump.
    """
    # Maximum reactive power limit. This is the maximum (nameplate) limit for the unit. 
    max_q = 0.0

    # Method of cooling the machine. Values are: "hydrogen_gas", "water", "air"
    coolant_type = 'hydrogen_gas'

    # Quadrature-axis subtransient reactance, also known as X'q. 
    x_quad_subtrans = 0.0

    # Quadrature-axis synchronous reactance (Xq) , the ratio of the component of reactive armature voltage, due to the quadrature-axis component of armature current, to this component of current, under steady state conditions and at rated frequency. 
    x_quad_sync = 0.0

    # Current mode of operation. Values are: "generator", "condenser"
    operating_mode = 'generator'

    # Minimum reactive power limit for the unit. 
    min_q = 0.0

    # Zero sequence reactance of the synchronous machine. 
    x0 = 0.0

    # Time delay required when switching from Manual to Automatic Voltage Regulation. This value is used in the accelerating power reference frame for powerflow solutions 
    manual_to_avr = 0.0

    # Maximum voltage limit for the unit. 
    max_u = 0.0

    # Negative sequence resistance. 
    r2 = 0.0

    # Direct-axis transient reactance, also known as X'd. 
    x_direct_trans = 0.0

    # Damping torque coefficient, a proportionality constant that, when multiplied by the angular velocity of the rotor poles with respect to the magnetic field (frequency), results in the damping torque. 
    damping = 0.0

    # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a lagging MVAr violation. 
    a_vrto_manual_lag = 0.0

    # Positive sequence reactance of the synchronous machine. 
    x = 0.0

    # Minimum voltage  limit for the unit. 
    min_u = 0.0

    # The energy stored in the rotor when operating at rated speed. This value is used in the accelerating power reference frame for  operator training simulator solutions. 
    inertia = 0.0

    # Time delay required when switching from Automatic Voltage Regulation (AVR) to Manual for a leading MVAr violation. 
    a_vrto_manual_lead = 0.0

    # Positive sequence resistance of the synchronous machine. 
    r = 0.0

    # Nameplate apparent power rating for the unit 
    rated_s = 0.0

    # Active power consumed when in condenser mode operation. 
    condenser_p = 0.0

    # Default base reactive power value. This value represents the initial reactive power that can be used by any application function. 
    base_q = 0.0

    # Quadrature-axis transient reactance, also known as X'q. 
    x_quad_trans = 0.0

    # Zero sequence resistance of the synchronous machine. 
    r0 = 0.0

    # Priority of unit for reference bus selection. 0 = don t care (default) 1 = highest priority. 2 is less than 1 and so on. 
    reference_priority = 0

    # Percent of the coordinated reactive control that comes from this machine. 
    q_percent = 0.0

    # Direct-axis synchronous reactance. The quotient of a sustained value of that AC component of armature voltage that is produced by the total direct-axis flux due to direct-axis armature current and the value of the AC component of this current, the machine running at rated speed. (Xd) 
    x_direct_sync = 0.0

    # Negative sequence reactance. 
    x2 = 0.0

    # Temperature or pressure of coolant medium 
    coolant_condition = 0.0

    # Modes that this synchronous machine can operate in. Values are: "generator_or_condenser", "condenser", "generator"
    type = 'generator_or_condenser'

    # Direct-axis subtransient reactance, also known as X'd. 
    x_direct_subtrans = 0.0

    reactive_capability_curves = []

    # The synchronous machine drives the turbine which moves the water from a low elevation to a higher elevation. The direction of machine rotation for pumping may or may not be the same as for generating.
    drives_hydro_pump = None

    # A synchronous machine may operate as a generator and as such becomes a member of a generating unit
    member_of_generating_unit = None

    # Defines the default MVArCapabilityCurve for use by a SynchronousMachine.
    initial_reactive_capability_curve = None

    driven_by_prime_mover = []

    # <<< synchronous_machine
    # @generated
    def __init__(self, max_q=0.0, coolant_type='hydrogen_gas', x_quad_subtrans=0.0, x_quad_sync=0.0, operating_mode='generator', min_q=0.0, x0=0.0, manual_to_avr=0.0, max_u=0.0, r2=0.0, x_direct_trans=0.0, damping=0.0, a_vrto_manual_lag=0.0, x=0.0, min_u=0.0, inertia=0.0, a_vrto_manual_lead=0.0, r=0.0, rated_s=0.0, condenser_p=0.0, base_q=0.0, x_quad_trans=0.0, r0=0.0, reference_priority=0, q_percent=0.0, x_direct_sync=0.0, x2=0.0, coolant_condition=0.0, type='generator_or_condenser', x_direct_subtrans=0.0, reactive_capability_curves=[], drives_hydro_pump=None, member_of_generating_unit=None, initial_reactive_capability_curve=None, driven_by_prime_mover=[], **kw_args):
        """ Initialises a new 'SynchronousMachine' instance.
        """
        self.max_q = max_q
        self.coolant_type = coolant_type
        self.x_quad_subtrans = x_quad_subtrans
        self.x_quad_sync = x_quad_sync
        self.operating_mode = operating_mode
        self.min_q = min_q
        self.x0 = x0
        self.manual_to_avr = manual_to_avr
        self.max_u = max_u
        self.r2 = r2
        self.x_direct_trans = x_direct_trans
        self.damping = damping
        self.a_vrto_manual_lag = a_vrto_manual_lag
        self.x = x
        self.min_u = min_u
        self.inertia = inertia
        self.a_vrto_manual_lead = a_vrto_manual_lead
        self.r = r
        self.rated_s = rated_s
        self.condenser_p = condenser_p
        self.base_q = base_q
        self.x_quad_trans = x_quad_trans
        self.r0 = r0
        self.reference_priority = reference_priority
        self.q_percent = q_percent
        self.x_direct_sync = x_direct_sync
        self.x2 = x2
        self.coolant_condition = coolant_condition
        self.type = type
        self.x_direct_subtrans = x_direct_subtrans
        self.reactive_capability_curves = reactive_capability_curves
        self.drives_hydro_pump = drives_hydro_pump
        self.member_of_generating_unit = member_of_generating_unit
        self.initial_reactive_capability_curve = initial_reactive_capability_curve
        self.driven_by_prime_mover = driven_by_prime_mover

        super(SynchronousMachine, self).__init__(**kw_args)
    # >>> synchronous_machine


class StaticVarCompensator(RegulatingCondEq):
    """ A facility for providing variable and controllable shunt reactive power. The SVC typically consists of a stepdown transformer, filter, thyristor-controlled reactor, and thyristor-switched capacitor arms.  The SVC may operate in fixed MVar output mode or in voltage control mode.  When in voltage control mode, the output of the SVC will be proportional to the deviation of voltage at the controlled bus from the voltage setpoint.  The SVC characteristic slope defines the proportion.  If the voltage at the controlled bus is equal to the voltage setpoint, the SVC MVar output is zero.
    """
    # The reactive power output of the SVC is proportional to the difference between the voltage at the regulated bus and the voltage setpoint.  When the regulated bus voltage is equal to the voltage setpoint, the reactive power output is zero. 
    voltage_set_point = 0.0

    # Maximum available capacitive reactive power 
    capacitive_rating = 0.0

    # SVC control mode. Values are: "voltage", "reactive_power", "off"
    s_vccontrol_mode = 'voltage'

    # The characteristics slope of an SVC defines how the reactive power output changes in proportion to the difference between the regulated bus voltage and the voltage setpoint. 
    slope = 0.0

    # Maximum available inductive reactive power 
    inductive_rating = 0.0

    # <<< static_var_compensator
    # @generated
    def __init__(self, voltage_set_point=0.0, capacitive_rating=0.0, s_vccontrol_mode='voltage', slope=0.0, inductive_rating=0.0, **kw_args):
        """ Initialises a new 'StaticVarCompensator' instance.
        """
        self.voltage_set_point = voltage_set_point
        self.capacitive_rating = capacitive_rating
        self.s_vccontrol_mode = s_vccontrol_mode
        self.slope = slope
        self.inductive_rating = inductive_rating

        super(StaticVarCompensator, self).__init__(**kw_args)
    # >>> static_var_compensator


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


class Breaker(ProtectedSwitch):
    """ A mechanical switching device capable of making, carrying, and breaking currents under normal circuit conditions and also making, carrying for a specified time, and breaking currents under specified abnormal circuit conditions e.g.  those of short circuit.
    """
    # The transition time from open to close. 
    in_transit_time = 0.0

    # Fault interrupting current rating. 
    rated_current = 0.0

    # <<< breaker
    # @generated
    def __init__(self, in_transit_time=0.0, rated_current=0.0, **kw_args):
        """ Initialises a new 'Breaker' instance.
        """
        self.in_transit_time = in_transit_time
        self.rated_current = rated_current

        super(Breaker, self).__init__(**kw_args)
    # >>> breaker


class LoadBreakSwitch(ProtectedSwitch):
    """ A mechanical switching device capable of making, carrying, and breaking currents under normal operating conditions.
    """
    # Current carrying capacity of a wire or cable under stated thermal conditions. 
    rated_current = 0.0

    # <<< load_break_switch
    # @generated
    def __init__(self, rated_current=0.0, **kw_args):
        """ Initialises a new 'LoadBreakSwitch' instance.
        """
        self.rated_current = rated_current

        super(LoadBreakSwitch, self).__init__(**kw_args)
    # >>> load_break_switch


# <<< wires
if __name__ == "__main__":
    svc = StaticVarCompensator(name='svc1', slope=1.2)
    print svc.name, svc.slope
# >>> wires

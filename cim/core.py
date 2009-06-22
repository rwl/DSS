# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim import Element

# <<< imports
# @generated
# >>> imports

class IrregularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points varies.
    """
    # The time is relative the BasicTimeSchedule.startTime. 
    time = 0.0

    # The second value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
    value2 = 0.0

    # The first value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
    value1 = 0.0

    # An IrregularTimePoint belongs to an IrregularIntervalSchedule.
    interval_schedule = None

    # <<< irregular_time_point
    # @generated
    def __init__(self, time=0.0, value2=0.0, value1=0.0, interval_schedule=None, **kw_args):
        """ Initialises a new 'IrregularTimePoint' instance.
        """
        self.time = time
        self.value2 = value2
        self.value1 = value1
        self.interval_schedule = interval_schedule

        super(IrregularTimePoint, self).__init__(**kw_args)
    # >>> irregular_time_point


class OperatingShare(Element):
    """ Specifies the contract relationship between a PowerSystemResource and a contract participant.
    """
    # Percentage ownership for this device.   The percentage indicates the percentage ownership of the PSROwner for the PowerSystemResource.  The total percentage ownership for a PowerSystemResource should add to 100%. 
    percentage = 0.0

    operating_participant = None

    power_system_resource = None

    # <<< operating_share
    # @generated
    def __init__(self, percentage=0.0, operating_participant=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'OperatingShare' instance.
        """
        self.percentage = percentage
        self.operating_participant = operating_participant
        self.power_system_resource = power_system_resource

        super(OperatingShare, self).__init__(**kw_args)
    # >>> operating_share


class CurveData(Element):
    """ Data point values for defining a curve or schedule
    """
    # The data value of the X-axis variable,  depending on the X-axis units 
    xvalue = 0.0

    # The data value of the  first Y-axis variable, depending on the Y-axis units 
    y1value = 0.0

    # The data value of the second Y-axis variable (if present), depending on the Y-axis units 
    y2value = 0.0

    # The point data values that define a curve
    curve_schedule = None

    # <<< curve_data
    # @generated
    def __init__(self, xvalue=0.0, y1value=0.0, y2value=0.0, curve_schedule=None, **kw_args):
        """ Initialises a new 'CurveData' instance.
        """
        self.xvalue = xvalue
        self.y1value = y1value
        self.y2value = y2value
        self.curve_schedule = curve_schedule

        super(CurveData, self).__init__(**kw_args)
    # >>> curve_data


class RegularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points is constant.
    """
    # The first value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
    value1 = 0.0

    # The second value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
    value2 = 0.0

    # The position of the RegularTimePoint in the sequence. Note that time points don't have to be sequential, i.e. time points may be omitted. The actual time for a RegularTimePoint is computed by multiplying the RegularIntervalSchedule.timeStep with the RegularTimePoint.sequenceNumber and add the BasicIntervalSchedule.startTime. 
    sequence_number = 0

    # A RegularTimePoint belongs to a RegularIntervalSchedule.
    interval_schedule = None

    # <<< regular_time_point
    # @generated
    def __init__(self, value1=0.0, value2=0.0, sequence_number=0, interval_schedule=None, **kw_args):
        """ Initialises a new 'RegularTimePoint' instance.
        """
        self.value1 = value1
        self.value2 = value2
        self.sequence_number = sequence_number
        self.interval_schedule = interval_schedule

        super(RegularTimePoint, self).__init__(**kw_args)
    # >>> regular_time_point


class IdentifiedObject(Element):
    """ This is a root class to provide common naming attributes for all classes needing naming attributes
    """
    # The name is a free text human readable name of the object. It may be non unique and may not correlate to a naming hierarchy. 
    name = ''

    # The localName is a human readable name of the object. It is only used with objects organized in a naming hierarchy. The simplest naming hierarchy has just one parent (the root) giving a flat naming hierarchy. However, the naming hierarchy usually has several levels, e.g. Substation, VoltageLevel, Equipment etc. Children of the same parent have names that are unique among them. If the uniqueness requirement cannot be met IdentifiedObject.localName shall not be used, use IdentifiedObject.name  instead. 
    local_name = ''

    # The description is a free human readable text describing or naming the object. It may be non unique and may not correlate to a naming hierarchy. 
    description = ''

    # The aliasName is free text human readable name of the object alternative to IdentifiedObject.name. It may be non unique and may not correlate to a naming hierarchy. 
    alias_name = ''

    # A Model Authority issues mRIDs. Given that each Model Authority has a unique id and this id is part of the mRID, then the mRID is globally unique. 
    m_rid = ''

    # The pathname is a system unique name composed from all IdentifiedObject.localNames in a naming hierarchy path from the object to the root. 
    path_name = ''

    # An IdentifiedObject belongs to a Modeling Authority Set for purposes of defining a group of data maintained by the same Modeling Authority.
    modeling_authority_set = None

    # <<< identified_object
    # @generated
    def __init__(self, name='', local_name='', description='', alias_name='', m_rid='', path_name='', modeling_authority_set=None, **kw_args):
        """ Initialises a new 'IdentifiedObject' instance.
        """
        self.name = name
        self.local_name = local_name
        self.description = description
        self.alias_name = alias_name
        self.m_rid = m_rid
        self.path_name = path_name
        self.modeling_authority_set = modeling_authority_set

        super(IdentifiedObject, self).__init__(**kw_args)
    # >>> identified_object


class Curve(IdentifiedObject):
    """ Relationship between an independent variable (X-axis) and one or two dependent  variables (Y1-axis and Y2-axis). Curves can also serve as schedules.
    """
    # The Y1-axis units of measure. Values are: "v_var", "varh", "none", "v", "j", "ohm", "h", "rad", "pa", "s", "g", "w", "vah", "f", "h", "w_hz", "hz", "n", "var", "w_s", "s", "j_s", "m2", "m", "m3", "min", "s-1", "deg", "wh", "va", "a", "_c", "kg_j", "hz-1"
    y1_unit = 'v_var'

    # The style or shape of the curve. Values are: "straight_line_yvalues", "ramp_yvalue", "constant_yvalue", "formula"
    curve_style = 'straight_line_yvalues'

    # Multiplier for Y2-axis. Values are: "c", "k", "g", "m", "t", "micro", "n", "none", "d", "m", "p"
    y2_multiplier = 'c'

    # The Y2-axis units of measure. Values are: "v_var", "varh", "none", "v", "j", "ohm", "h", "rad", "pa", "s", "g", "w", "vah", "f", "h", "w_hz", "hz", "n", "var", "w_s", "s", "j_s", "m2", "m", "m3", "min", "s-1", "deg", "wh", "va", "a", "_c", "kg_j", "hz-1"
    y2_unit = 'v_var'

    # Multiplier for Y1-axis Values are: "c", "k", "g", "m", "t", "micro", "n", "none", "d", "m", "p"
    y1_multiplier = 'c'

    # Multiplier for X-axis. Values are: "c", "k", "g", "m", "t", "micro", "n", "none", "d", "m", "p"
    x_multiplier = 'c'

    # The X-axis units of measure. Values are: "v_var", "varh", "none", "v", "j", "ohm", "h", "rad", "pa", "s", "g", "w", "vah", "f", "h", "w_hz", "hz", "n", "var", "w_s", "s", "j_s", "m2", "m", "m3", "min", "s-1", "deg", "wh", "va", "a", "_c", "kg_j", "hz-1"
    x_unit = 'v_var'

    # The point data values that define a curve
    curve_schedule_datas = []

    # <<< curve
    # @generated
    def __init__(self, y1_unit='v_var', curve_style='straight_line_yvalues', y2_multiplier='c', y2_unit='v_var', y1_multiplier='c', x_multiplier='c', x_unit='v_var', curve_schedule_datas=[], **kw_args):
        """ Initialises a new 'Curve' instance.
        """
        self.y1_unit = y1_unit
        self.curve_style = curve_style
        self.y2_multiplier = y2_multiplier
        self.y2_unit = y2_unit
        self.y1_multiplier = y1_multiplier
        self.x_multiplier = x_multiplier
        self.x_unit = x_unit
        self.curve_schedule_datas = curve_schedule_datas

        super(Curve, self).__init__(**kw_args)
    # >>> curve


class ReportingGroup(IdentifiedObject):
    """ A reporting group is used for various ad-hoc groupings used for reporting.
    """
    power_system_resource = []

    reporting_super_group = None

    bus_name_marker = []

    topological_node = []

    # <<< reporting_group
    # @generated
    def __init__(self, power_system_resource=[], reporting_super_group=None, bus_name_marker=[], topological_node=[], **kw_args):
        """ Initialises a new 'ReportingGroup' instance.
        """
        self.power_system_resource = power_system_resource
        self.reporting_super_group = reporting_super_group
        self.bus_name_marker = bus_name_marker
        self.topological_node = topological_node

        super(ReportingGroup, self).__init__(**kw_args)
    # >>> reporting_group


class ModelingAuthoritySet(IdentifiedObject):
    """ A Modeling Authority Set is a group of objects in a network model where the data is supplied and maintained by the same Modeling Authority.
    """
    # An IdentifiedObject belongs to a Modeling Authority Set for purposes of defining a group of data maintained by the same Modeling Authority.
    identified_objects = []

    # A Modeling Authority set supplies and maintains the data for the objects in a Modeling Authority Set.
    modeling_authority = None

    # <<< modeling_authority_set
    # @generated
    def __init__(self, identified_objects=[], modeling_authority=None, **kw_args):
        """ Initialises a new 'ModelingAuthoritySet' instance.
        """
        self.identified_objects = identified_objects
        self.modeling_authority = modeling_authority

        super(ModelingAuthoritySet, self).__init__(**kw_args)
    # >>> modeling_authority_set


class OperatingParticipant(IdentifiedObject):
    """ An operator of multiple PowerSystemResource objects. Note multple OperatingParticipants may operate the same PowerSystemResource object.   This can be used for modeling jointly owned units where each owner operates as a contractual share.
    """
    operating_share = []

    # <<< operating_participant
    # @generated
    def __init__(self, operating_share=[], **kw_args):
        """ Initialises a new 'OperatingParticipant' instance.
        """
        self.operating_share = operating_share

        super(OperatingParticipant, self).__init__(**kw_args)
    # >>> operating_participant


class ReportingSuperGroup(IdentifiedObject):
    """ A reporting super group, groups reporting groups for a higher level report.
    """
    reporting_group = []

    # <<< reporting_super_group
    # @generated
    def __init__(self, reporting_group=[], **kw_args):
        """ Initialises a new 'ReportingSuperGroup' instance.
        """
        self.reporting_group = reporting_group

        super(ReportingSuperGroup, self).__init__(**kw_args)
    # >>> reporting_super_group


class GeographicalRegion(IdentifiedObject):
    """ A geographical region of a power system network model.
    """
    # The association is used in the naming hierarchy.
    regions = []

    # <<< geographical_region
    # @generated
    def __init__(self, regions=[], **kw_args):
        """ Initialises a new 'GeographicalRegion' instance.
        """
        self.regions = regions

        super(GeographicalRegion, self).__init__(**kw_args)
    # >>> geographical_region


class Unit(IdentifiedObject):
    """ Quantity being measured. The Unit.name shall be unique among all specified quantities and describe the quantity. The Unit.aliasName is meant to be used for localization.
    """
    controls = []

    # The Protection Equipments having the Unit.
    protection_equipments = []

    measurements = []

    # <<< unit
    # @generated
    def __init__(self, controls=[], protection_equipments=[], measurements=[], **kw_args):
        """ Initialises a new 'Unit' instance.
        """
        self.controls = controls
        self.protection_equipments = protection_equipments
        self.measurements = measurements

        super(Unit, self).__init__(**kw_args)
    # >>> unit


class ModelingAuthority(IdentifiedObject):
    """ A Modeling Authority is an entity responsible for supplying and maintaining the data defining a specific set of objects in a network model.
    """
    # A Modeling Authority set supplies and maintains the data for the objects in a Modeling Authority Set.
    modeling_authority_sets = []

    # <<< modeling_authority
    # @generated
    def __init__(self, modeling_authority_sets=[], **kw_args):
        """ Initialises a new 'ModelingAuthority' instance.
        """
        self.modeling_authority_sets = modeling_authority_sets

        super(ModelingAuthority, self).__init__(**kw_args)
    # >>> modeling_authority


class BaseVoltage(IdentifiedObject):
    """ Collection of BaseVoltages which is used to verify that the BusbarSection.BaseVoltage and other voltage attributes in the CIM are given a value existing in the collection.
    """
    # The PowerSystemResource's base voltage. 
    nominal_voltage = 0.0

    # If true, this is a direct current base voltage and items assigned to this base voltage are also associated with a direct current capabilities.   False indicates alternating current. 
    is_dc = False

    # Use association to ConductingEquipment only when there is no VoltageLevel container used.
    conducting_equipment = []

    voltage_level = []

    # <<< base_voltage
    # @generated
    def __init__(self, nominal_voltage=0.0, is_dc=False, conducting_equipment=[], voltage_level=[], **kw_args):
        """ Initialises a new 'BaseVoltage' instance.
        """
        self.nominal_voltage = nominal_voltage
        self.is_dc = is_dc
        self.conducting_equipment = conducting_equipment
        self.voltage_level = voltage_level

        super(BaseVoltage, self).__init__(**kw_args)
    # >>> base_voltage


class BasicIntervalSchedule(IdentifiedObject):
    """ Schedule of values at points in time.
    """
    # Value2 units of measure. Values are: "v_var", "varh", "none", "v", "j", "ohm", "h", "rad", "pa", "s", "g", "w", "vah", "f", "h", "w_hz", "hz", "n", "var", "w_s", "s", "j_s", "m2", "m", "m3", "min", "s-1", "deg", "wh", "va", "a", "_c", "kg_j", "hz-1"
    value2_unit = 'v_var'

    # The time for the first time point. 
    start_time = ''

    # Multiplier for value2. Values are: "c", "k", "g", "m", "t", "micro", "n", "none", "d", "m", "p"
    value2_multiplier = 'c'

    # Value1 units of measure. Values are: "v_var", "varh", "none", "v", "j", "ohm", "h", "rad", "pa", "s", "g", "w", "vah", "f", "h", "w_hz", "hz", "n", "var", "w_s", "s", "j_s", "m2", "m", "m3", "min", "s-1", "deg", "wh", "va", "a", "_c", "kg_j", "hz-1"
    value1_unit = 'v_var'

    # Multiplier for value1. Values are: "c", "k", "g", "m", "t", "micro", "n", "none", "d", "m", "p"
    value1_multiplier = 'c'

    # <<< basic_interval_schedule
    # @generated
    def __init__(self, value2_unit='v_var', start_time='', value2_multiplier='c', value1_unit='v_var', value1_multiplier='c', **kw_args):
        """ Initialises a new 'BasicIntervalSchedule' instance.
        """
        self.value2_unit = value2_unit
        self.start_time = start_time
        self.value2_multiplier = value2_multiplier
        self.value1_unit = value1_unit
        self.value1_multiplier = value1_multiplier

        super(BasicIntervalSchedule, self).__init__(**kw_args)
    # >>> basic_interval_schedule


class RegularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them is constant.
    """
    # The time between each pair of subsequent RegularTimePoints. 
    time_step = 0.0

    # The time for the last time point. 
    end_time = ''

    # A RegularTimePoint belongs to a RegularIntervalSchedule.
    time_points = []

    # <<< regular_interval_schedule
    # @generated
    def __init__(self, time_step=0.0, end_time='', time_points=[], **kw_args):
        """ Initialises a new 'RegularIntervalSchedule' instance.
        """
        self.time_step = time_step
        self.end_time = end_time
        self.time_points = time_points

        super(RegularIntervalSchedule, self).__init__(**kw_args)
    # >>> regular_interval_schedule


class IrregularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them varies.
    """
    # An IrregularTimePoint belongs to an IrregularIntervalSchedule.
    time_points = []

    # <<< irregular_interval_schedule
    # @generated
    def __init__(self, time_points=[], **kw_args):
        """ Initialises a new 'IrregularIntervalSchedule' instance.
        """
        self.time_points = time_points

        super(IrregularIntervalSchedule, self).__init__(**kw_args)
    # >>> irregular_interval_schedule


class Terminal(IdentifiedObject):
    """ An electrical connection point to a piece of conducting equipment. Terminals are connected at physical connection points called 'connectivity nodes'.
    """
    # A terminal may participate in zero, one, or two control areas as a tie flow.
    tie_flow = []

    operational_limit_set = []

    branch_group_terminal = []

    # ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
    conducting_equipment = None

    regulating_control = []

    # One or more measurements may be associated with a terminal in the network. Measurement-Terminal defines where the measurement is placed in the network topology. Some Measurements represent quantities related to a particular sensor position, e.g. a voltage transformer (PT) at a busbar or a current transformer (CT) at the bar between a breaker and an isolator. The sensing position is captured by the Measurement - Terminal association that makes it possible to place the sensing position at a  well defined place. The place is defined by the connection of the Terminal to ConductingEquipment.
    measurements = []

    topological_node = None

    # Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
    connectivity_node = None

    # <<< terminal
    # @generated
    def __init__(self, tie_flow=[], operational_limit_set=[], branch_group_terminal=[], conducting_equipment=None, regulating_control=[], measurements=[], topological_node=None, connectivity_node=None, **kw_args):
        """ Initialises a new 'Terminal' instance.
        """
        self.tie_flow = tie_flow
        self.operational_limit_set = operational_limit_set
        self.branch_group_terminal = branch_group_terminal
        self.conducting_equipment = conducting_equipment
        self.regulating_control = regulating_control
        self.measurements = measurements
        self.topological_node = topological_node
        self.connectivity_node = connectivity_node

        super(Terminal, self).__init__(**kw_args)
    # >>> terminal


class SubGeographicalRegion(IdentifiedObject):
    """ A subset of a geographical region of a power system network model.
    """
    # The association is used in the naming hierarchy.
    region = None

    # A Line can be contained by a SubGeographical Region.
    lines = []

    # The association is used in the naming hierarchy.
    substations = []

    # <<< sub_geographical_region
    # @generated
    def __init__(self, region=None, lines=[], substations=[], **kw_args):
        """ Initialises a new 'SubGeographicalRegion' instance.
        """
        self.region = region
        self.lines = lines
        self.substations = substations

        super(SubGeographicalRegion, self).__init__(**kw_args)
    # >>> sub_geographical_region


class PowerSystemResource(IdentifiedObject):
    """ A power system resource can be an item of equipment such as a Switch, an EquipmentContainer containing many individual items of equipment such as a  Substation, or an organisational entity such as Company or SubControlArea.  This provides for the nesting of collections of PowerSystemResources within other PowerSystemResources. For example, a Switch could be a member of a Substation and a Substation could be a member of a division of a Company.
    """
    psrtype = None

    # A power system resource may be part of one or more companies
    operated_by_companies = []

    reporting_group = []

    operating_share = []

    psr_lists = []

    # A power system resource may have an outage schedule
    outage_schedule = None

    # Measurement-PSR defines the measurements in the naming hierarchy.
    contains_measurements = []

    # <<< power_system_resource
    # @generated
    def __init__(self, psrtype=None, operated_by_companies=[], reporting_group=[], operating_share=[], psr_lists=[], outage_schedule=None, contains_measurements=[], **kw_args):
        """ Initialises a new 'PowerSystemResource' instance.
        """
        self.psrtype = psrtype
        self.operated_by_companies = operated_by_companies
        self.reporting_group = reporting_group
        self.operating_share = operating_share
        self.psr_lists = psr_lists
        self.outage_schedule = outage_schedule
        self.contains_measurements = contains_measurements

        super(PowerSystemResource, self).__init__(**kw_args)
    # >>> power_system_resource


class BasePower(IdentifiedObject):
    """ The BasePower class defines the base power used in the per unit calculations.
    """
    # Definition of base power. 
    base_power = 0.0

    # <<< base_power
    # @generated
    def __init__(self, base_power=0.0, **kw_args):
        """ Initialises a new 'BasePower' instance.
        """
        self.base_power = base_power

        super(BasePower, self).__init__(**kw_args)
    # >>> base_power


class PSRType(IdentifiedObject):
    """ Classifying instances of the same class, e.g. overhead and underground ACLineSegments. This classification mechanism is intended to provide flexibility outside the scope of this standard, i.e. provide customisation that is non standard.
    """
    power_system_resource = []

    # <<< psrtype
    # @generated
    def __init__(self, power_system_resource=[], **kw_args):
        """ Initialises a new 'PSRType' instance.
        """
        self.power_system_resource = power_system_resource

        super(PSRType, self).__init__(**kw_args)
    # >>> psrtype


class PsrList(IdentifiedObject):
    """ Arbitrary list of PowerSystemResources. Can be used for various purposes, including grouping for report generation.
    """
    # Type of power system resources in this list. 
    type_psrlist = ''

    power_system_resources = []

    # <<< psr_list
    # @generated
    def __init__(self, type_psrlist='', power_system_resources=[], **kw_args):
        """ Initialises a new 'PsrList' instance.
        """
        self.type_psrlist = type_psrlist
        self.power_system_resources = power_system_resources

        super(PsrList, self).__init__(**kw_args)
    # >>> psr_list


class Company(IdentifiedObject):
    """ A company is a legal entity that owns and operates power system resources and is a party to interchange and transmission contracts.
    """
    # The type of company. Values are: "is_private", "pool", "municipal"
    company_type = 'is_private'

    # A power system resource may be part of one or more companies
    operates_psrs = []

    # <<< company
    # @generated
    def __init__(self, company_type='is_private', operates_psrs=[], **kw_args):
        """ Initialises a new 'Company' instance.
        """
        self.company_type = company_type
        self.operates_psrs = operates_psrs

        super(Company, self).__init__(**kw_args)
    # >>> company


class ConnectivityNodeContainer(PowerSystemResource):
    """ A base class for all objects that may contain ConnectivityNodes or TopologicalNodes.
    """
    topological_node = []

    connectivity_nodes = []

    # <<< connectivity_node_container
    # @generated
    def __init__(self, topological_node=[], connectivity_nodes=[], **kw_args):
        """ Initialises a new 'ConnectivityNodeContainer' instance.
        """
        self.topological_node = topological_node
        self.connectivity_nodes = connectivity_nodes

        super(ConnectivityNodeContainer, self).__init__(**kw_args)
    # >>> connectivity_node_container


class EquipmentContainer(ConnectivityNodeContainer):
    """ A modeling construct to provide a root class for all Equipment classes
    """
    # The association is used in the naming hierarchy.
    contains_equipments = []

    # <<< equipment_container
    # @generated
    def __init__(self, contains_equipments=[], **kw_args):
        """ Initialises a new 'EquipmentContainer' instance.
        """
        self.contains_equipments = contains_equipments

        super(EquipmentContainer, self).__init__(**kw_args)
    # >>> equipment_container


class Equipment(PowerSystemResource):
    """ The parts of a power system that are physical devices, electronic or mechanical
    """
    # The equipment is normally in service. 
    normal_ily_in_service = False

    operational_limit_set = []

    contingency_equipment = []

    # The association is used in the naming hierarchy.
    member_of_equipment_container = None

    # <<< equipment
    # @generated
    def __init__(self, normal_ily_in_service=False, operational_limit_set=[], contingency_equipment=[], member_of_equipment_container=None, **kw_args):
        """ Initialises a new 'Equipment' instance.
        """
        self.normal_ily_in_service = normal_ily_in_service
        self.operational_limit_set = operational_limit_set
        self.contingency_equipment = contingency_equipment
        self.member_of_equipment_container = member_of_equipment_container

        super(Equipment, self).__init__(**kw_args)
    # >>> equipment


class Bay(EquipmentContainer):
    """ A collection of power system resources (within a given substation) including conducting equipment, protection relays, measurements, and telemetry.
    """
    # Bus bar configuration. Values are: "main_with_transfer", "ring_bus", "double_bus", "single_bus"
    bus_bar_configuration = 'main_with_transfer'

    # Indicates the presence/absence of active/reactive power measurements. 
    bay_power_meas_flag = False

    # Breaker configuration. Values are: "no_breaker", "double_breaker", "breaker_and_ahalf", "single_breaker"
    breaker_configuration = 'no_breaker'

    # Indicates the presence/absence of energy measurements. 
    bay_energy_meas_flag = False

    # The association is used in the naming hierarchy.
    member_of_substation = None

    # The association is used in the naming hierarchy.
    member_of_voltage_level = None

    # <<< bay
    # @generated
    def __init__(self, bus_bar_configuration='main_with_transfer', bay_power_meas_flag=False, breaker_configuration='no_breaker', bay_energy_meas_flag=False, member_of_substation=None, member_of_voltage_level=None, **kw_args):
        """ Initialises a new 'Bay' instance.
        """
        self.bus_bar_configuration = bus_bar_configuration
        self.bay_power_meas_flag = bay_power_meas_flag
        self.breaker_configuration = breaker_configuration
        self.bay_energy_meas_flag = bay_energy_meas_flag
        self.member_of_substation = member_of_substation
        self.member_of_voltage_level = member_of_voltage_level

        super(Bay, self).__init__(**kw_args)
    # >>> bay


class VoltageLevel(EquipmentContainer):
    """ A collection of equipment at one common system voltage forming a switchgear. The equipment typically consist of breakers, busbars, instrumentation, control, regulation and protection devices as well as assemblies of all these.
    """
    # The bus bar's low voltage limit 
    low_voltage_limit = 0.0

    # The bus bar's high voltage limit 
    high_voltage_limit = 0.0

    # The association is used in the naming hierarchy.
    contains_bays = []

    base_voltage = None

    # The association is used in the naming hierarchy.
    member_of_substation = None

    # <<< voltage_level
    # @generated
    def __init__(self, low_voltage_limit=0.0, high_voltage_limit=0.0, contains_bays=[], base_voltage=None, member_of_substation=None, **kw_args):
        """ Initialises a new 'VoltageLevel' instance.
        """
        self.low_voltage_limit = low_voltage_limit
        self.high_voltage_limit = high_voltage_limit
        self.contains_bays = contains_bays
        self.base_voltage = base_voltage
        self.member_of_substation = member_of_substation

        super(VoltageLevel, self).__init__(**kw_args)
    # >>> voltage_level


class Substation(EquipmentContainer):
    """ A collection of equipment for purposes other than generation or utilization, through which electric energy in bulk is passed for the purposes of switching or modifying its characteristics.
    """
    # The association is used in the naming hierarchy.
    contains_voltage_levels = []

    # The association is used in the naming hierarchy.
    contains_bays = []

    # The association is used in the naming hierarchy.
    region = None

    # <<< substation
    # @generated
    def __init__(self, contains_voltage_levels=[], contains_bays=[], region=None, **kw_args):
        """ Initialises a new 'Substation' instance.
        """
        self.contains_voltage_levels = contains_voltage_levels
        self.contains_bays = contains_bays
        self.region = region

        super(Substation, self).__init__(**kw_args)
    # >>> substation


class ConductingEquipment(Equipment):
    """ The parts of the power system that are designed to carry current or that are conductively connected therewith. ConductingEquipment is contained within an EquipmentContainer that may be a Substation, or a VoltageLevel or a Bay within a Substation.
    """
    # Describes the phases carried by a conducting equipment. Values are: "bcn", "acn", "ab", "a", "b", "abcn", "ac", "n", "an", "c", "abn", "bn", "abc", "bc", "cn"
    phases = 'bcn'

    # ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
    terminals = []

    # Protection equipment may be used to protect specific Conducting Equipment. Multiple equipment may be protected or monitored by multiple protection equipment.
    protection_equipments = []

    # Use association to ConductingEquipment only when there is no VoltageLevel container used.
    base_voltage = None

    # Conducting equipment may have multiple clearance tags for authorized field work
    clearance_tags = []

    # <<< conducting_equipment
    # @generated
    def __init__(self, phases='bcn', terminals=[], protection_equipments=[], base_voltage=None, clearance_tags=[], **kw_args):
        """ Initialises a new 'ConductingEquipment' instance.
        """
        self.phases = phases
        self.terminals = terminals
        self.protection_equipments = protection_equipments
        self.base_voltage = base_voltage
        self.clearance_tags = clearance_tags

        super(ConductingEquipment, self).__init__(**kw_args)
    # >>> conducting_equipment


# <<< core
# @generated
# >>> core

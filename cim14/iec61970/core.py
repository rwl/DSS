# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Core"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Core"

class RegularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points is constant.
    """
    # The first value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
    value1 = 0.0

    # The position of the RegularTimePoint in the sequence. Note that time points don't have to be sequential, i.e. time points may be omitted. The actual time for a RegularTimePoint is computed by multiplying the RegularIntervalSchedule.timeStep with the RegularTimePoint.sequenceNumber and add the BasicIntervalSchedule.startTime. 
    sequence_number = 0

    # The second value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
    value2 = 0.0

    # A RegularTimePoint belongs to a RegularIntervalSchedule.
    interval_schedule = None

    # <<< regular_time_point
    # @generated
    def __init__(self, value1=0.0, sequence_number=0, value2=0.0, interval_schedule=None, **kw_args):
        """ Initialises a new 'RegularTimePoint' instance.
        """
        self.value1 = value1
        self.sequence_number = sequence_number
        self.value2 = value2
        self.interval_schedule = interval_schedule

        super(RegularTimePoint, self).__init__(**kw_args)
    # >>> regular_time_point


class OperatingShare(Element):
    """ Specifies the contract relationship between a PowerSystemResource and a contract participant.
    """
    # Percentage ownership for this device.   The percentage indicates the percentage ownership of the PSROwner for the PowerSystemResource.  The total percentage ownership for a PowerSystemResource should add to 100%. 
    percentage = ''

    # The PowerSystemResource to which the attribues apply.   The percentage ownership of all owners of a PowerSystemResource should add to 100%.
    power_system_resource = None

    # The linkage to a owners  and its linkage attributes like percentage ownership.   The ownership percentage should add to 100% for all owners of a PowerSystemResource, but a PSROwner may own any percentage of any number of PowerSystemResource objects.
    operating_participant = None

    # <<< operating_share
    # @generated
    def __init__(self, percentage='', power_system_resource=None, operating_participant=None, **kw_args):
        """ Initialises a new 'OperatingShare' instance.
        """
        self.percentage = percentage
        self.power_system_resource = power_system_resource
        self.operating_participant = operating_participant

        super(OperatingShare, self).__init__(**kw_args)
    # >>> operating_share


class IdentifiedObject(Element):
    """ This is a root class to provide common naming attributes for all classes needing naming attributes
    """
    # A Model Authority issues mRIDs. Given that each Model Authority has a unique id and this id is part of the mRID, then the mRID is globally unique. 
    m_rid = ''

    # The name is a free text human readable name of the object. It may be non unique and may not correlate to a naming hierarchy. 
    name = ''

    # The pathname is a system unique name composed from all IdentifiedObject.localNames in a naming hierarchy path from the object to the root. 
    path_name = ''

    # The description is a free human readable text describing or naming the object. It may be non unique and may not correlate to a naming hierarchy. 
    description = ''

    # The localName is a human readable name of the object. It is only used with objects organized in a naming hierarchy. The simplest naming hierarchy has just one parent (the root) giving a flat naming hierarchy. However, the naming hierarchy usually has several levels, e.g. Substation, VoltageLevel, Equipment etc. Children of the same parent have names that are unique among them. If the uniqueness requirement cannot be met IdentifiedObject.localName shall not be used, use IdentifiedObject.name  instead. 
    local_name = ''

    # The aliasName is free text human readable name of the object alternative to IdentifiedObject.name. It may be non unique and may not correlate to a naming hierarchy. 
    alias_name = ''

    # An IdentifiedObject belongs to a Modeling Authority Set for purposes of defining a group of data maintained by the same Modeling Authority.
    modeling_authority_set = None

    # <<< identified_object
    # @generated
    def __init__(self, m_rid='', name='', path_name='', description='', local_name='', alias_name='', modeling_authority_set=None, **kw_args):
        """ Initialises a new 'IdentifiedObject' instance.
        """
        self.m_rid = m_rid
        self.name = name
        self.path_name = path_name
        self.description = description
        self.local_name = local_name
        self.alias_name = alias_name
        self.modeling_authority_set = modeling_authority_set

        super(IdentifiedObject, self).__init__(**kw_args)
    # >>> identified_object


class IrregularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points varies.
    """
    # The second value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
    value2 = 0.0

    # The time is relative the BasicTimeSchedule.startTime. 
    time = ''

    # The first value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
    value1 = 0.0

    # An IrregularTimePoint belongs to an IrregularIntervalSchedule.
    interval_schedule = None

    # <<< irregular_time_point
    # @generated
    def __init__(self, value2=0.0, time='', value1=0.0, interval_schedule=None, **kw_args):
        """ Initialises a new 'IrregularTimePoint' instance.
        """
        self.value2 = value2
        self.time = time
        self.value1 = value1
        self.interval_schedule = interval_schedule

        super(IrregularTimePoint, self).__init__(**kw_args)
    # >>> irregular_time_point


class CurveData(Element):
    """ Data point values for defining a curve or schedule
    """
    # The data value of the second Y-axis variable (if present), depending on the Y-axis units 
    y2value = 0.0

    # The data value of the X-axis variable,  depending on the X-axis units 
    xvalue = 0.0

    # The data value of the  first Y-axis variable, depending on the Y-axis units 
    y1value = 0.0

    # The Curve defined by this CurveData.
    curve = None

    # <<< curve_data
    # @generated
    def __init__(self, y2value=0.0, xvalue=0.0, y1value=0.0, curve=None, **kw_args):
        """ Initialises a new 'CurveData' instance.
        """
        self.y2value = y2value
        self.xvalue = xvalue
        self.y1value = y1value
        self.curve = curve

        super(CurveData, self).__init__(**kw_args)
    # >>> curve_data


class PowerSystemResource(IdentifiedObject):
    """ A power system resource can be an item of equipment such as a Switch, an EquipmentContainer containing many individual items of equipment such as a  Substation, or an organisational entity such as Company or SubControlArea.  This provides for the nesting of collections of PowerSystemResources within other PowerSystemResources. For example, a Switch could be a member of a Substation and a Substation could be a member of a division of a Company.
    """
    activity_records = []

    # A power system resource may have an outage schedule
    outage_schedule = None

    schedule_steps = []

    # Reporting groups to which this PSR belongs.
    reporting_group = []

    circuit_sections = []

    # The Measurements that are included in the naming hierarchy where the PSR is the containing object
    measurements = []

    network_data_sets = []

    # The linkage to any number of operating share objects.
    operating_share = []

    psr_lists = []

    safety_documents = []

    circuits = []

    # PSRType (custom classification) for this PowerSystemResource.
    psrtype = None

    psrstatus = None

    asset_roles = []

    document_roles = []

    erp_organisation_roles = []

    change_items = []

    location_roles = []

    # <<< power_system_resource
    # @generated
    def __init__(self, activity_records=[], outage_schedule=None, schedule_steps=[], reporting_group=[], circuit_sections=[], measurements=[], network_data_sets=[], operating_share=[], psr_lists=[], safety_documents=[], circuits=[], psrtype=None, psrstatus=None, asset_roles=[], document_roles=[], erp_organisation_roles=[], change_items=[], location_roles=[], **kw_args):
        """ Initialises a new 'PowerSystemResource' instance.
        """
        self.activity_records = activity_records
        self.outage_schedule = outage_schedule
        self.schedule_steps = schedule_steps
        self.reporting_group = reporting_group
        self.circuit_sections = circuit_sections
        self.measurements = measurements
        self.network_data_sets = network_data_sets
        self.operating_share = operating_share
        self.psr_lists = psr_lists
        self.safety_documents = safety_documents
        self.circuits = circuits
        self.psrtype = psrtype
        self.psrstatus = psrstatus
        self.asset_roles = asset_roles
        self.document_roles = document_roles
        self.erp_organisation_roles = erp_organisation_roles
        self.change_items = change_items
        self.location_roles = location_roles

        super(PowerSystemResource, self).__init__(**kw_args)
    # >>> power_system_resource


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


class OperatingParticipant(IdentifiedObject):
    """ An operator of multiple PowerSystemResource objects. Note multple OperatingParticipants may operate the same PowerSystemResource object.   This can be used for modeling jointly owned units where each owner operates as a contractual share.
    """
    # The operating shares of an operating participant.   An operating participant can be reused for any number of PSR's.
    operating_share = []

    # <<< operating_participant
    # @generated
    def __init__(self, operating_share=[], **kw_args):
        """ Initialises a new 'OperatingParticipant' instance.
        """
        self.operating_share = operating_share

        super(OperatingParticipant, self).__init__(**kw_args)
    # >>> operating_participant


class ConnectivityNodeContainer(PowerSystemResource):
    """ A base class for all objects that may contain ConnectivityNodes or TopologicalNodes.
    """
    # Connectivity nodes contained by this container.
    connectivity_nodes = []

    # The topological nodes which belong to this connectivity node container.
    topological_node = []

    # <<< connectivity_node_container
    # @generated
    def __init__(self, connectivity_nodes=[], topological_node=[], **kw_args):
        """ Initialises a new 'ConnectivityNodeContainer' instance.
        """
        self.connectivity_nodes = connectivity_nodes
        self.topological_node = topological_node

        super(ConnectivityNodeContainer, self).__init__(**kw_args)
    # >>> connectivity_node_container


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
    # The Protection Equipments having the Unit.
    protection_equipments = []

    # The Measurements having the Unit
    measurements = []

    # The Controls having the Unit.
    controls = []

    # <<< unit
    # @generated
    def __init__(self, protection_equipments=[], measurements=[], controls=[], **kw_args):
        """ Initialises a new 'Unit' instance.
        """
        self.protection_equipments = protection_equipments
        self.measurements = measurements
        self.controls = controls

        super(Unit, self).__init__(**kw_args)
    # >>> unit


class BasicIntervalSchedule(IdentifiedObject):
    """ Schedule of values at points in time.
    """
    # Value2 units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    value2_unit = '_c'

    # Value1 units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    value1_unit = '_c'

    # Multiplier for value2. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    value2_multiplier = 'micro'

    # Multiplier for value1. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    value1_multiplier = 'micro'

    # The time for the first time point. 
    start_time = ''

    # <<< basic_interval_schedule
    # @generated
    def __init__(self, value2_unit='_c', value1_unit='_c', value2_multiplier='micro', value1_multiplier='micro', start_time='', **kw_args):
        """ Initialises a new 'BasicIntervalSchedule' instance.
        """
        self.value2_unit = value2_unit
        self.value1_unit = value1_unit
        self.value2_multiplier = value2_multiplier
        self.value1_multiplier = value1_multiplier
        self.start_time = start_time

        super(BasicIntervalSchedule, self).__init__(**kw_args)
    # >>> basic_interval_schedule


class PSRType(IdentifiedObject):
    """ Classifying instances of the same class, e.g. overhead and underground ACLineSegments. This classification mechanism is intended to provide flexibility outside the scope of this standard, i.e. provide customisation that is non standard.
    """
    # Power system resources classified with this PSRType.
    power_system_resources = []

    # <<< psrtype
    # @generated
    def __init__(self, power_system_resources=[], **kw_args):
        """ Initialises a new 'PSRType' instance.
        """
        self.power_system_resources = power_system_resources

        super(PSRType, self).__init__(**kw_args)
    # >>> psrtype


class EquipmentContainer(ConnectivityNodeContainer):
    """ A modeling construct to provide a root class for all Equipment classes
    """
    # The association is used in the naming hierarchy.
    equipments = []

    # <<< equipment_container
    # @generated
    def __init__(self, equipments=[], **kw_args):
        """ Initialises a new 'EquipmentContainer' instance.
        """
        self.equipments = equipments

        super(EquipmentContainer, self).__init__(**kw_args)
    # >>> equipment_container


class Equipment(PowerSystemResource):
    """ The parts of a power system that are physical devices, electronic or mechanical
    """
    # The equipment is normally in service. 
    normal_ily_in_service = False

    # Indicates if the equipment is real equipment (false) or an equivalent. 
    equivalent = False

    # The contingency element associated with the equipment.
    contingency_equipment = []

    customer_agreements = []

    # The equipment limit sets associated with the equipment.
    operational_limit_set = []

    # The association is used in the naming hierarchy.
    equipment_container = None

    # <<< equipment
    # @generated
    def __init__(self, normal_ily_in_service=False, equivalent=False, contingency_equipment=[], customer_agreements=[], operational_limit_set=[], equipment_container=None, **kw_args):
        """ Initialises a new 'Equipment' instance.
        """
        self.normal_ily_in_service = normal_ily_in_service
        self.equivalent = equivalent
        self.contingency_equipment = contingency_equipment
        self.customer_agreements = customer_agreements
        self.operational_limit_set = operational_limit_set
        self.equipment_container = equipment_container

        super(Equipment, self).__init__(**kw_args)
    # >>> equipment


class ReportingGroup(IdentifiedObject):
    """ A reporting group is used for various ad-hoc groupings used for reporting.
    """
    # The BusNameMarkers that belong to this reporting group.
    bus_name_marker = []

    # Reporting super group to which this reporting group belongs.
    reporting_super_group = None

    # The topological nodes that belong to the reporting group.
    topological_node = []

    # PSR's which belong to this reporting group.
    power_system_resource = []

    # <<< reporting_group
    # @generated
    def __init__(self, bus_name_marker=[], reporting_super_group=None, topological_node=[], power_system_resource=[], **kw_args):
        """ Initialises a new 'ReportingGroup' instance.
        """
        self.bus_name_marker = bus_name_marker
        self.reporting_super_group = reporting_super_group
        self.topological_node = topological_node
        self.power_system_resource = power_system_resource

        super(ReportingGroup, self).__init__(**kw_args)
    # >>> reporting_group


class ReportingSuperGroup(IdentifiedObject):
    """ A reporting super group, groups reporting groups for a higher level report.
    """
    # Reporting groups that are grouped under this group group.
    reporting_group = []

    # <<< reporting_super_group
    # @generated
    def __init__(self, reporting_group=[], **kw_args):
        """ Initialises a new 'ReportingSuperGroup' instance.
        """
        self.reporting_group = reporting_group

        super(ReportingSuperGroup, self).__init__(**kw_args)
    # >>> reporting_super_group


class SubGeographicalRegion(IdentifiedObject):
    """ A subset of a geographical region of a power system network model.
    """
    # The association is used in the naming hierarchy.
    substations = []

    # The association is used in the naming hierarchy.
    region = None

    # A Line can be contained by a SubGeographical Region.
    lines = []

    # <<< sub_geographical_region
    # @generated
    def __init__(self, substations=[], region=None, lines=[], **kw_args):
        """ Initialises a new 'SubGeographicalRegion' instance.
        """
        self.substations = substations
        self.region = region
        self.lines = lines

        super(SubGeographicalRegion, self).__init__(**kw_args)
    # >>> sub_geographical_region


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


class Curve(IdentifiedObject):
    """ Relationship between an independent variable (X-axis) and one or two dependent  variables (Y1-axis and Y2-axis). Curves can also serve as schedules.
    """
    # Multiplier for Y2-axis. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    y2_multiplier = 'micro'

    # The Y2-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    y2_unit = '_c'

    # Multiplier for Y1-axis Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    y1_multiplier = 'micro'

    # The Y1-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    y1_unit = '_c'

    # The style or shape of the curve. Values are: "straight_line_yvalues", "constant_yvalue", "formula", "ramp_yvalue"
    curve_style = 'straight_line_yvalues'

    # Multiplier for X-axis. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    x_multiplier = 'micro'

    # The X-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    x_unit = '_c'

    # The point data values that define a curve
    curve_datas = []

    # <<< curve
    # @generated
    def __init__(self, y2_multiplier='micro', y2_unit='_c', y1_multiplier='micro', y1_unit='_c', curve_style='straight_line_yvalues', x_multiplier='micro', x_unit='_c', curve_datas=[], **kw_args):
        """ Initialises a new 'Curve' instance.
        """
        self.y2_multiplier = y2_multiplier
        self.y2_unit = y2_unit
        self.y1_multiplier = y1_multiplier
        self.y1_unit = y1_unit
        self.curve_style = curve_style
        self.x_multiplier = x_multiplier
        self.x_unit = x_unit
        self.curve_datas = curve_datas

        super(Curve, self).__init__(**kw_args)
    # >>> curve


class BaseVoltage(IdentifiedObject):
    """ Collection of BaseVoltages which is used to verify that the BusbarSection.BaseVoltage and other voltage attributes in the CIM are given a value existing in the collection.
    """
    # If true, this is a direct current base voltage and items assigned to this base voltage are also associated with a direct current capabilities.   False indicates alternating current. 
    is_dc = False

    # The PowerSystemResource's base voltage. 
    nominal_voltage = ''

    # The VoltageLevels having this BaseVoltage.
    voltage_level = []

    # Use association to ConductingEquipment only when there is no VoltageLevel container used.
    conducting_equipment = []

    # <<< base_voltage
    # @generated
    def __init__(self, is_dc=False, nominal_voltage='', voltage_level=[], conducting_equipment=[], **kw_args):
        """ Initialises a new 'BaseVoltage' instance.
        """
        self.is_dc = is_dc
        self.nominal_voltage = nominal_voltage
        self.voltage_level = voltage_level
        self.conducting_equipment = conducting_equipment

        super(BaseVoltage, self).__init__(**kw_args)
    # >>> base_voltage


class Bay(EquipmentContainer):
    """ A collection of power system resources (within a given substation) including conducting equipment, protection relays, measurements, and telemetry.
    """
    # Indicates the presence/absence of active/reactive power measurements. 
    bay_power_meas_flag = False

    # Bus bar configuration. Values are: "double_bus", "main_with_transfer", "single_bus", "ring_bus"
    bus_bar_configuration = 'double_bus'

    # Breaker configuration. Values are: "single_breaker", "no_breaker", "breaker_and_ahalf", "double_breaker"
    breaker_configuration = 'single_breaker'

    # Indicates the presence/absence of energy measurements. 
    bay_energy_meas_flag = False

    # The association is used in the naming hierarchy.
    substation = None

    # The association is used in the naming hierarchy.
    voltage_level = None

    # <<< bay
    # @generated
    def __init__(self, bay_power_meas_flag=False, bus_bar_configuration='double_bus', breaker_configuration='single_breaker', bay_energy_meas_flag=False, substation=None, voltage_level=None, **kw_args):
        """ Initialises a new 'Bay' instance.
        """
        self.bay_power_meas_flag = bay_power_meas_flag
        self.bus_bar_configuration = bus_bar_configuration
        self.breaker_configuration = breaker_configuration
        self.bay_energy_meas_flag = bay_energy_meas_flag
        self.substation = substation
        self.voltage_level = voltage_level

        super(Bay, self).__init__(**kw_args)
    # >>> bay


class RegularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them is constant.
    """
    # The time for the last time point. 
    end_time = ''

    # The time between each pair of subsequent RegularTimePoints. 
    time_step = ''

    # The point data values that define a curve
    time_points = []

    # <<< regular_interval_schedule
    # @generated
    def __init__(self, end_time='', time_step='', time_points=[], **kw_args):
        """ Initialises a new 'RegularIntervalSchedule' instance.
        """
        self.end_time = end_time
        self.time_step = time_step
        self.time_points = time_points

        super(RegularIntervalSchedule, self).__init__(**kw_args)
    # >>> regular_interval_schedule


class BasePower(IdentifiedObject):
    """ The BasePower class defines the base power used in the per unit calculations.
    """
    # Definition of base power. 
    base_power = ''

    # <<< base_power
    # @generated
    def __init__(self, base_power='', **kw_args):
        """ Initialises a new 'BasePower' instance.
        """
        self.base_power = base_power

        super(BasePower, self).__init__(**kw_args)
    # >>> base_power


class Terminal(IdentifiedObject):
    """ An electrical connection point to a piece of conducting equipment. Terminals are connected at physical connection points called 'connectivity nodes'.
    """
    # The terminal connection status.   True implies the terminal is connected, and false implies the terminal is not connected. This is the result of topoplogical processing of a detailed Connectivity node and Switch model whether present in the model or not.   A terminal that is not connected cannot support a current flow.   A terminal that is connected may have flow.  In general a multi-terminal device may simultaneously have connected and disconnected terminals.  No other aspect of the algorithm 
    connected = False

    # One or more measurements may be associated with a terminal in the network. Measurement-Terminal defines where the measurement is placed in the network topology. Some Measurements represent quantities related to a particular sensor position, e.g. a voltage transformer (PT) at a busbar or a current transformer (CT) at the bar between a breaker and an isolator. The sensing position is captured by the Measurement - Terminal association that makes it possible to place the sensing position at a  well defined place. The place is defined by the connection of the Terminal to ConductingEquipment.
    measurements = []

    terminal_constraints = []

    # Mutual couplings with the branch associated as the first branch.
    has_second_mutual_coupling = []

    # The operatinal limits sets that applie specifically to this terminal.  Other operational limits sets may apply to this terminal through the association to Equipment.
    operational_limit_set = []

    # The control area tie flows to which this terminal associates.
    tie_flow = []

    # The power flow state associated with the terminal.
    sv_power_flow = None

    # The terminal is regulated by a control.
    regulating_control = []

    # ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
    conducting_equipment = None

    # Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
    connectivity_node = None

    # Mutual couplings associated with the branch as the first branch.
    has_first_mutual_coupling = []

    # The topological node associated with the terminal.   This can be used as an alternative to the connectivity node path to topological node, thus making it unneccesary to model connedtivity nodes in some cases.   Note that the if connectivity nodes are in the model, this association would proably not be used.
    topological_node = None

    # The directed branch group terminals for which the terminal is monitored.
    branch_group_terminal = []

    bushing_asset = None

    # <<< terminal
    # @generated
    def __init__(self, connected=False, measurements=[], terminal_constraints=[], has_second_mutual_coupling=[], operational_limit_set=[], tie_flow=[], sv_power_flow=None, regulating_control=[], conducting_equipment=None, connectivity_node=None, has_first_mutual_coupling=[], topological_node=None, branch_group_terminal=[], bushing_asset=None, **kw_args):
        """ Initialises a new 'Terminal' instance.
        """
        self.connected = connected
        self.measurements = measurements
        self.terminal_constraints = terminal_constraints
        self.has_second_mutual_coupling = has_second_mutual_coupling
        self.operational_limit_set = operational_limit_set
        self.tie_flow = tie_flow
        self.sv_power_flow = sv_power_flow
        self.regulating_control = regulating_control
        self.conducting_equipment = conducting_equipment
        self.connectivity_node = connectivity_node
        self.has_first_mutual_coupling = has_first_mutual_coupling
        self.topological_node = topological_node
        self.branch_group_terminal = branch_group_terminal
        self.bushing_asset = bushing_asset

        super(Terminal, self).__init__(**kw_args)
    # >>> terminal


class Substation(EquipmentContainer):
    """ A collection of equipment for purposes other than generation or utilization, through which electric energy in bulk is passed for the purposes of switching or modifying its characteristics.
    """
    # The association is used in the naming hierarchy.
    region = None

    # The association is used in the naming hierarchy.
    voltage_levels = []

    substation_asset = None

    # The association is used in the naming hierarchy.
    bays = []

    # <<< substation
    # @generated
    def __init__(self, region=None, voltage_levels=[], substation_asset=None, bays=[], **kw_args):
        """ Initialises a new 'Substation' instance.
        """
        self.region = region
        self.voltage_levels = voltage_levels
        self.substation_asset = substation_asset
        self.bays = bays

        super(Substation, self).__init__(**kw_args)
    # >>> substation


class VoltageLevel(EquipmentContainer):
    """ A collection of equipment at one common system voltage forming a switchgear. The equipment typically consist of breakers, busbars, instrumentation, control, regulation and protection devices as well as assemblies of all these.
    """
    # The bus bar's low voltage limit 
    low_voltage_limit = ''

    # The bus bar's high voltage limit 
    high_voltage_limit = ''

    # The association is used in the naming hierarchy.
    bays = []

    # The base voltage used for all equipment within the VoltageLevel.
    base_voltage = None

    # The association is used in the naming hierarchy.
    substation = None

    # <<< voltage_level
    # @generated
    def __init__(self, low_voltage_limit='', high_voltage_limit='', bays=[], base_voltage=None, substation=None, **kw_args):
        """ Initialises a new 'VoltageLevel' instance.
        """
        self.low_voltage_limit = low_voltage_limit
        self.high_voltage_limit = high_voltage_limit
        self.bays = bays
        self.base_voltage = base_voltage
        self.substation = substation

        super(VoltageLevel, self).__init__(**kw_args)
    # >>> voltage_level


class IrregularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them varies.
    """
    # The point data values that define a curve
    time_points = []

    # <<< irregular_interval_schedule
    # @generated
    def __init__(self, time_points=[], **kw_args):
        """ Initialises a new 'IrregularIntervalSchedule' instance.
        """
        self.time_points = time_points

        super(IrregularIntervalSchedule, self).__init__(**kw_args)
    # >>> irregular_interval_schedule


class ConductingEquipment(Equipment):
    """ The parts of the power system that are designed to carry current or that are conductively connected therewith. ConductingEquipment is contained within an EquipmentContainer that may be a Substation, or a VoltageLevel or a Bay within a Substation.
    """
    # Describes the phases carried by a conducting equipment. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phases = 'abn'

    electrical_assets = []

    # Protection equipment may be used to protect specific Conducting Equipment. Multiple equipment may be protected or monitored by multiple protection equipment.
    protection_equipments = []

    outage_step_roles = []

    # The status state associated with the conducting equipment.
    sv_status = None

    # Conducting equipment may have multiple clearance tags for authorized field work
    clearance_tags = []

    # Use association to ConductingEquipment only when there is no VoltageLevel container used.
    base_voltage = None

    # ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
    terminals = []

    # <<< conducting_equipment
    # @generated
    def __init__(self, phases='abn', electrical_assets=[], protection_equipments=[], outage_step_roles=[], sv_status=None, clearance_tags=[], base_voltage=None, terminals=[], **kw_args):
        """ Initialises a new 'ConductingEquipment' instance.
        """
        self.phases = phases
        self.electrical_assets = electrical_assets
        self.protection_equipments = protection_equipments
        self.outage_step_roles = outage_step_roles
        self.sv_status = sv_status
        self.clearance_tags = clearance_tags
        self.base_voltage = base_voltage
        self.terminals = terminals

        super(ConductingEquipment, self).__init__(**kw_args)
    # >>> conducting_equipment


# <<< core
# @generated
# >>> core

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Document
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfCommon"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfCommon"

class ScheduledEvent(IdentifiedObject):
    """ Signifies an event to trigger one or more activities, such as reading a meter, recalculating a bill, requesting work, when generating units must be scheduled for maintenance, when a transformer is scheduled to be refurbished, etc.
    """
    # Duration of the scheduled event, for example, the time to ramp between values. 
    duration = ''

    # Category of scheduled event. 
    category = ''

    status = None

    activity_record = None

    time_point = None

    assets = []

    schedule_parameter_info = None

    document = None

    # <<< scheduled_event
    # @generated
    def __init__(self, duration='', category='', status=None, activity_record=None, time_point=None, assets=[], schedule_parameter_info=None, document=None, **kw_args):
        """ Initialises a new 'ScheduledEvent' instance.
        """
        self.duration = duration
        self.category = category
        self.status = status
        self.activity_record = activity_record
        self.time_point = time_point
        self.assets = assets
        self.schedule_parameter_info = schedule_parameter_info
        self.document = document

        super(ScheduledEvent, self).__init__(**kw_args)
    # >>> scheduled_event


class ChangeItem(IdentifiedObject):
    """ Description for a single change within an ordered list of changes.
    """
    # Kind of change for the associated object. Values are: "modify", "add", "delete"
    kind = 'modify'

    # Relative order of this ChangeItem in an ordered sequence of changes. 
    sequence_number = 0

    status = None

    power_system_resource = None

    measurement = None

    document = None

    change_set = None

    network_data_set = None

    gml_selector = None

    location = None

    erp_person = None

    asset = None

    organisation = None

    gml_observation = None

    # <<< change_item
    # @generated
    def __init__(self, kind='modify', sequence_number=0, status=None, power_system_resource=None, measurement=None, document=None, change_set=None, network_data_set=None, gml_selector=None, location=None, erp_person=None, asset=None, organisation=None, gml_observation=None, **kw_args):
        """ Initialises a new 'ChangeItem' instance.
        """
        self.kind = kind
        self.sequence_number = sequence_number
        self.status = status
        self.power_system_resource = power_system_resource
        self.measurement = measurement
        self.document = document
        self.change_set = change_set
        self.network_data_set = network_data_set
        self.gml_selector = gml_selector
        self.location = location
        self.erp_person = erp_person
        self.asset = asset
        self.organisation = organisation
        self.gml_observation = gml_observation

        super(ChangeItem, self).__init__(**kw_args)
    # >>> change_item


class Role(IdentifiedObject):
    """ Enumeration of potential roles that might be played by one object relative to another.
    """
    # Category of role. 
    category = ''

    status = None

    # <<< role
    # @generated
    def __init__(self, category='', status=None, **kw_args):
        """ Initialises a new 'Role' instance.
        """
        self.category = category
        self.status = status

        super(Role, self).__init__(**kw_args)
    # >>> role


class Craft(IdentifiedObject):
    """ Craft of a person or a crew. Examples include overhead electric, underground electric, high pressure gas, etc. This ensures necessary knowledge and skills before being allowed to perform certain types of work.
    """
    # Category by utility's work mangement standards and practices. 
    category = ''

    status = None

    erp_persons = []

    capabilities = []

    skills = []

    # <<< craft
    # @generated
    def __init__(self, category='', status=None, erp_persons=[], capabilities=[], skills=[], **kw_args):
        """ Initialises a new 'Craft' instance.
        """
        self.category = category
        self.status = status
        self.erp_persons = erp_persons
        self.capabilities = capabilities
        self.skills = skills

        super(Craft, self).__init__(**kw_args)
    # >>> craft


class ScheduleParameterInfo(IdentifiedObject):
    """ Schedule parameters for an activity that is to occur, is occurring, or has completed.
    """
    # Estimated date and time for activity execution (with earliest possibility of activity initiation and latest possibility of activity completion). 
    estimated_window = ''

    status = None

    # Requested date and time interval for activity execution.
    requested_window = None

    scheduled_events = []

    for_inspection_data_set = None

    documents = []

    # <<< schedule_parameter_info
    # @generated
    def __init__(self, estimated_window='', status=None, requested_window=None, scheduled_events=[], for_inspection_data_set=None, documents=[], **kw_args):
        """ Initialises a new 'ScheduleParameterInfo' instance.
        """
        self.estimated_window = estimated_window
        self.status = status
        self.requested_window = requested_window
        self.scheduled_events = scheduled_events
        self.for_inspection_data_set = for_inspection_data_set
        self.documents = documents

        super(ScheduleParameterInfo, self).__init__(**kw_args)
    # >>> schedule_parameter_info


class Diagram(Document):
    """ GML and/or other means are used for rendering objects on various types of displays(geographic, schematic, other) and maps associated with various coordinate systems.
    """
    # Kind of this diagram. Values are: "geographic", "design_sketch", "schematic", "other", "internal_view"
    kind = 'geographic'

    gml_coordinate_system = None

    gml_diagram_objects = []

    design_locations = []

    # <<< diagram
    # @generated
    def __init__(self, kind='geographic', gml_coordinate_system=None, gml_diagram_objects=[], design_locations=[], **kw_args):
        """ Initialises a new 'Diagram' instance.
        """
        self.kind = kind
        self.gml_coordinate_system = gml_coordinate_system
        self.gml_diagram_objects = gml_diagram_objects
        self.design_locations = design_locations

        super(Diagram, self).__init__(**kw_args)
    # >>> diagram


class BankAccount(Document):
    """ Bank account.
    """
    # Account reference number. 
    account_number = ''

    # Bank that provides this BankAccount.
    bank = None

    # ServiceSupplier that is owner of this BankAccount.
    service_supplier = None

    # All bank statements generated from this bank account.
    bank_statements = []

    # <<< bank_account
    # @generated
    def __init__(self, account_number='', bank=None, service_supplier=None, bank_statements=[], **kw_args):
        """ Initialises a new 'BankAccount' instance.
        """
        self.account_number = account_number
        self.bank = bank
        self.service_supplier = service_supplier
        self.bank_statements = bank_statements

        super(BankAccount, self).__init__(**kw_args)
    # >>> bank_account


class BusinessRole(IdentifiedObject):
    """ A business role that this organisation plays. A single organisation typically performs many functions, each one described as a role.
    """
    # Category by utility's corporate standards and practices. 
    category = ''

    status = None

    organisations = []

    # <<< business_role
    # @generated
    def __init__(self, category='', status=None, organisations=[], **kw_args):
        """ Initialises a new 'BusinessRole' instance.
        """
        self.category = category
        self.status = status
        self.organisations = organisations

        super(BusinessRole, self).__init__(**kw_args)
    # >>> business_role


class Skill(Document):
    """ Proficiency level of a craft, which is required to operate or maintain a particular type of asset and/or perform certain types of work.
    """
    # Date and time skill certification expires. 
    expiration_date_time = ''

    # Date and time skill became effective. 
    effective_date_time = ''

    # Level of skill for a Craft. Values are: "other", "apprentice", "standard", "master"
    level = 'other'

    # Date of certification. 
    certified_date = ''

    crafts = []

    qualification_requirements = []

    erp_person = None

    # <<< skill
    # @generated
    def __init__(self, expiration_date_time='', effective_date_time='', level='other', certified_date='', crafts=[], qualification_requirements=[], erp_person=None, **kw_args):
        """ Initialises a new 'Skill' instance.
        """
        self.expiration_date_time = expiration_date_time
        self.effective_date_time = effective_date_time
        self.level = level
        self.certified_date = certified_date
        self.crafts = crafts
        self.qualification_requirements = qualification_requirements
        self.erp_person = erp_person

        super(Skill, self).__init__(**kw_args)
    # >>> skill


class BusinessPlan(Document):
    """ A BusinessPlan is an organized sequence of predetermined actions required to complete a future organizational objective. It is a type of document that typically references a schedule, physical and/or logical resources (assets and/or PowerSystemResources), locations, etc.
    """
    pass
    # <<< business_plan
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'BusinessPlan' instance.
        """

        super(BusinessPlan, self).__init__(**kw_args)
    # >>> business_plan


class MarketRole(IdentifiedObject):
    """ Role an organisation plays in a market. Examples include one or more of values defined in MarketRoleKind.
    """
    # Kind of role an organisation plays in a market. Values are: "energy_service_consumer", "transmission_service_provider", "transmission_owner", "interchange_authority", "transmission_operator", "transmission_planner", "standards_developer", "planning_authority", "load_serving_entity", "competitive_retailer", "compliance_monitor", "resource_planner", "generator_owner", "distribution_provider", "reliability_authority", "balancing_authority", "other", "purchasing_selling_entity", "generator_operator"
    kind = 'energy_service_consumer'

    status = None

    organisations = []

    # <<< market_role
    # @generated
    def __init__(self, kind='energy_service_consumer', status=None, organisations=[], **kw_args):
        """ Initialises a new 'MarketRole' instance.
        """
        self.kind = kind
        self.status = status
        self.organisations = organisations

        super(MarketRole, self).__init__(**kw_args)
    # >>> market_role


class Ratio(Element):
    """ Fraction specified explicitly with a numerator and denominator, which can be used to calculate the quotient.
    """
    # The part of a fraction that is above the line and signifies the number to be divided by the denominator. 
    numerator = 0.0

    # The part of a fraction that is below the line and that functions as the divisor of the numerator. 
    denominator = 0.0

    # <<< ratio
    # @generated
    def __init__(self, numerator=0.0, denominator=0.0, **kw_args):
        """ Initialises a new 'Ratio' instance.
        """
        self.numerator = numerator
        self.denominator = denominator

        super(Ratio, self).__init__(**kw_args)
    # >>> ratio


class DocDocRole(Role):
    """ Roles played between Documents and other Documents.
    """
    to_document = None

    from_document = None

    # <<< doc_doc_role
    # @generated
    def __init__(self, to_document=None, from_document=None, **kw_args):
        """ Initialises a new 'DocDocRole' instance.
        """
        self.to_document = to_document
        self.from_document = from_document

        super(DocDocRole, self).__init__(**kw_args)
    # >>> doc_doc_role


class DocPsrRole(Role):
    """ Potential roles that might played by a document relative to a type of PowerSystemResource.
    """
    document = None

    power_system_resource = None

    # <<< doc_psr_role
    # @generated
    def __init__(self, document=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'DocPsrRole' instance.
        """
        self.document = document
        self.power_system_resource = power_system_resource

        super(DocPsrRole, self).__init__(**kw_args)
    # >>> doc_psr_role


class Map(Diagram):
    """ A type of diagram that is usually printed on paper. It normally depicts part of the earth's surface, showing utility assets, right of ways, topological data, coordinates, grids, etc. Maps vary depending on whether they are used for dispatch, design, schematic, etc.
    """
    # Page number for particular set of maps specified by 'category'. 
    page_number = 0

    # Map grid number. 
    map_loc_grid = ''

    # <<< map
    # @generated
    def __init__(self, page_number=0, map_loc_grid='', **kw_args):
        """ Initialises a new 'Map' instance.
        """
        self.page_number = page_number
        self.map_loc_grid = map_loc_grid

        super(Map, self).__init__(**kw_args)
    # >>> map


# <<< inf_common
# @generated
# >>> inf_common

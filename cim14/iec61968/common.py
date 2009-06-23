# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Common"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Common"

class TelephoneNumber(IdentifiedObject):
    """ Telephone number.
    """
    # Country code. 
    country_code = ''

    # (if applicable) City code. 
    city_code = ''

    # Main (local) part of this telephone number. 
    local_number = ''

    # (if applicable) Extension for this telephone number. 
    extension = ''

    # Area or region code. 
    area_code = ''

    # Organisation owning this telephone number.
    organisation = None

    # Location owning this telephone number.
    location = None

    # <<< telephone_number
    # @generated
    def __init__(self, country_code='', city_code='', local_number='', extension='', area_code='', organisation=None, location=None, **kw_args):
        """ Initialises a new 'TelephoneNumber' instance.
        """
        self.country_code = country_code
        self.city_code = city_code
        self.local_number = local_number
        self.extension = extension
        self.area_code = area_code
        self.organisation = organisation
        self.location = location

        super(TelephoneNumber, self).__init__(**kw_args)
    # >>> telephone_number


class Location(IdentifiedObject):
    """ The place, scene, or point of something where someone or something has been, is, and/or will be at a given moment in time. It may be: - Spatial location of an actual or planned structure, or a set of point-oriented structures (as a substation, structure, building, town, etc.) or diagram objects, which may be defined as a point or polygon, or, - Path of an underground or overhead conductor, or a linear diagram object.
    """
    # Utility-specific code for the location. 
    corporate_code = ''

    # (if applicable) Direction that allows field crews to quickly find a given asset. For a given location, such as a street address, this is the relative direction in wich to find the asset. For example, a Streetlight may be located at the 'NW' (northwest) corner of the customer's site, or a ServiceDeliveryPoint may be located on the second floor of an appartment building. 
    direction = ''

    # True if the first and last point (in the sequence of associated PositionPoints) are to be connected, thus forming a polygon rather than merely a sequence of line segments. 
    is_polygon = False

    # (if applicable) Reference to geographical information source, often external to the utility. 
    geo_info_reference = ''

    # Category by utility's corporate standards and practices, relative to the location itself (e.g., geographical, functional accounting, etc., not a given property that happens to exist at that location). 
    category = ''

    # Secondary address of the location. For example, PO Box address may have different ZIP code than that in the 'mainAddress'.
    secondary_address = None

    # Status of this location.
    status = None

    # Main address of the location.
    main_address = None

    gml_selectors = []

    to_location_roles = []

    erp_person_roles = []

    change_items = []

    power_system_resource_roles = []

    land_properties = []

    red_lines = []

    erp_organisation_roles = []

    hazards = []

    from_location_roles = []

    measurements = []

    asset_roles = []

    document_roles = []

    # Sequence of position points describing this location.
    position_points = []

    # All electronic addresses of this location.
    electronic_addresses = []

    # All telephone numbers of this location.
    telephone_numbers = []

    dimensions_info = None

    routes = []

    gml_observatins = []

    activity_records = []

    crews = []

    # <<< location
    # @generated
    def __init__(self, corporate_code='', direction='', is_polygon=False, geo_info_reference='', category='', secondary_address=None, status=None, main_address=None, gml_selectors=[], to_location_roles=[], erp_person_roles=[], change_items=[], power_system_resource_roles=[], land_properties=[], red_lines=[], erp_organisation_roles=[], hazards=[], from_location_roles=[], measurements=[], asset_roles=[], document_roles=[], position_points=[], electronic_addresses=[], telephone_numbers=[], dimensions_info=None, routes=[], gml_observatins=[], activity_records=[], crews=[], **kw_args):
        """ Initialises a new 'Location' instance.
        """
        self.corporate_code = corporate_code
        self.direction = direction
        self.is_polygon = is_polygon
        self.geo_info_reference = geo_info_reference
        self.category = category
        self.secondary_address = secondary_address
        self.status = status
        self.main_address = main_address
        self.gml_selectors = gml_selectors
        self.to_location_roles = to_location_roles
        self.erp_person_roles = erp_person_roles
        self.change_items = change_items
        self.power_system_resource_roles = power_system_resource_roles
        self.land_properties = land_properties
        self.red_lines = red_lines
        self.erp_organisation_roles = erp_organisation_roles
        self.hazards = hazards
        self.from_location_roles = from_location_roles
        self.measurements = measurements
        self.asset_roles = asset_roles
        self.document_roles = document_roles
        self.position_points = position_points
        self.electronic_addresses = electronic_addresses
        self.telephone_numbers = telephone_numbers
        self.dimensions_info = dimensions_info
        self.routes = routes
        self.gml_observatins = gml_observatins
        self.activity_records = activity_records
        self.crews = crews

        super(Location, self).__init__(**kw_args)
    # >>> location


class ActivityRecord(IdentifiedObject):
    """ Records activity for an entity at a point in time; activity may be for an event that has already occurred or for a planned activity.
    """
    # Severity level of event resulting in this activity record. 
    severity = ''

    # Reason for event resulting in this activity record, typically supplied when user initiated. 
    reason = ''

    # Category of event resulting in this activity record. 
    category = ''

    # Date and time this activity record has been created (different from the 'status.dateTime', which is the time of a status change of the associated object, if applicable). 
    created_date_time = ''

    # Inofrmation on consequence of event resulting in this activity record.
    status = None

    organisations = []

    market_factors = []

    locations = []

    # All assets for which this activity record has been created.
    assets = []

    power_system_resources = []

    # All documents for which this activity record has been created.
    documents = []

    scheduled_event = None

    erp_persons = []

    # <<< activity_record
    # @generated
    def __init__(self, severity='', reason='', category='', created_date_time='', status=None, organisations=[], market_factors=[], locations=[], assets=[], power_system_resources=[], documents=[], scheduled_event=None, erp_persons=[], **kw_args):
        """ Initialises a new 'ActivityRecord' instance.
        """
        self.severity = severity
        self.reason = reason
        self.category = category
        self.created_date_time = created_date_time
        self.status = status
        self.organisations = organisations
        self.market_factors = market_factors
        self.locations = locations
        self.assets = assets
        self.power_system_resources = power_system_resources
        self.documents = documents
        self.scheduled_event = scheduled_event
        self.erp_persons = erp_persons

        super(ActivityRecord, self).__init__(**kw_args)
    # >>> activity_record


class PositionPoint(Element):
    """ Set of spatial coordinates that determine a point. A sequence of PositionPoints can be used to describe: - physical location of non-point oriented objects like cables or lines, or - area of an object like a substation, a geographical zone or a diagram object.
    """
    # X axis position. 
    x_position = ''

    # Y axis position. 
    y_position = ''

    # Zero-relative sequence number of this point within a series of points. 
    sequence_number = 0

    # (if applicable) Z axis position. 
    z_position = ''

    # Location that this position point describes.
    location = None

    # <<< position_point
    # @generated
    def __init__(self, x_position='', y_position='', sequence_number=0, z_position='', location=None, **kw_args):
        """ Initialises a new 'PositionPoint' instance.
        """
        self.x_position = x_position
        self.y_position = y_position
        self.sequence_number = sequence_number
        self.z_position = z_position
        self.location = location

        super(PositionPoint, self).__init__(**kw_args)
    # >>> position_point


class StreetAddress(Element):
    """ General purpose street address information.
    """
    # Street detail.
    street_detail = None

    # Town detail.
    town_detail = None

    # Status of this address.
    status = None

    # <<< street_address
    # @generated
    def __init__(self, street_detail=None, town_detail=None, status=None, **kw_args):
        """ Initialises a new 'StreetAddress' instance.
        """
        self.street_detail = street_detail
        self.town_detail = town_detail
        self.status = status

        super(StreetAddress, self).__init__(**kw_args)
    # >>> street_address


class TownDetail(Element):
    """ Town details, in the context of address.
    """
    # Town name. 
    name = ''

    # Town section. For example, it is common for there to be 36 sections per township. 
    section = ''

    # Name of the state or province. 
    state_or_province = ''

    # Town code. 
    code = ''

    # Name of the country. 
    country = ''

    # <<< town_detail
    # @generated
    def __init__(self, name='', section='', state_or_province='', code='', country='', **kw_args):
        """ Initialises a new 'TownDetail' instance.
        """
        self.name = name
        self.section = section
        self.state_or_province = state_or_province
        self.code = code
        self.country = country

        super(TownDetail, self).__init__(**kw_args)
    # >>> town_detail


class UserAttribute(Element):
    """ Generic name-value pair class, with optional sequence number and units for value; can be used to model parts of information exchange when concrete types are not known in advance.
    """
    # Value of an attribute, including unit information. 
    value = ''

    # Sequence number for this attribute in a list of attributes. 
    sequence_number = 0

    # Name of an attribute. 
    name = ''

    property_specification = None

    procedure_data_sets = []

    property_assets = []

    erp_ledger_entries = []

    erp_statement_line_items = []

    bill_determinants = []

    pass_through_bills = []

    rating_specification = None

    # Transaction for which this snapshot has been recorded.
    transaction = None

    erp_invoice_line_items = []

    rating_assets = []

    procedure = None

    # <<< user_attribute
    # @generated
    def __init__(self, value='', sequence_number=0, name='', property_specification=None, procedure_data_sets=[], property_assets=[], erp_ledger_entries=[], erp_statement_line_items=[], bill_determinants=[], pass_through_bills=[], rating_specification=None, transaction=None, erp_invoice_line_items=[], rating_assets=[], procedure=None, **kw_args):
        """ Initialises a new 'UserAttribute' instance.
        """
        self.value = value
        self.sequence_number = sequence_number
        self.name = name
        self.property_specification = property_specification
        self.procedure_data_sets = procedure_data_sets
        self.property_assets = property_assets
        self.erp_ledger_entries = erp_ledger_entries
        self.erp_statement_line_items = erp_statement_line_items
        self.bill_determinants = bill_determinants
        self.pass_through_bills = pass_through_bills
        self.rating_specification = rating_specification
        self.transaction = transaction
        self.erp_invoice_line_items = erp_invoice_line_items
        self.rating_assets = rating_assets
        self.procedure = procedure

        super(UserAttribute, self).__init__(**kw_args)
    # >>> user_attribute


class Document(IdentifiedObject):
    """ Parent class for different groupings of information collected and managed as a part of a business process. It will frequently contain references to other objects, such as assets, people and power system resources.
    """
    # Date and time this document was last modified. Documents may potentially be modified many times during their lifetime. 
    last_modified_date_time = ''

    # Document subject. 
    subject = ''

    # Document title. 
    title = ''

    # Revision number for this document. 
    revision_number = ''

    # Date and time that this document was created. 
    created_date_time = ''

    # Utility-specific categorisation of this document, according to their corporate standards, practices, and existing IT systems (e.g., for management of assets, maintenance, work, outage, customers, etc.). 
    category = ''

    # Status of this document. For status of subject metter this document represents (e.g., Agreement, Work), use 'status' attribute. Example values for 'docStatus.status' are draft, approved, cancelled, etc.
    doc_status = None

    # Status of subject metter (e.g., Agreement, Work) this document represents. For status of the document itself, use 'docStatus' attribute.
    status = None

    from_document_roles = []

    schedule_parameter_infos = []

    change_items = []

    network_data_sets = []

    # All activity records created for this document.
    activity_records = []

    power_system_resource_roles = []

    location_roles = []

    change_sets = []

    erp_person_roles = []

    asset_roles = []

    scheduled_events = []

    electronic_address = None

    # Measurements are specified in types of documents, such as procedures.
    measurements = []

    to_document_roles = []

    erp_organisation_roles = []

    # <<< document
    # @generated
    def __init__(self, last_modified_date_time='', subject='', title='', revision_number='', created_date_time='', category='', doc_status=None, status=None, from_document_roles=[], schedule_parameter_infos=[], change_items=[], network_data_sets=[], activity_records=[], power_system_resource_roles=[], location_roles=[], change_sets=[], erp_person_roles=[], asset_roles=[], scheduled_events=[], electronic_address=None, measurements=[], to_document_roles=[], erp_organisation_roles=[], **kw_args):
        """ Initialises a new 'Document' instance.
        """
        self.last_modified_date_time = last_modified_date_time
        self.subject = subject
        self.title = title
        self.revision_number = revision_number
        self.created_date_time = created_date_time
        self.category = category
        self.doc_status = doc_status
        self.status = status
        self.from_document_roles = from_document_roles
        self.schedule_parameter_infos = schedule_parameter_infos
        self.change_items = change_items
        self.network_data_sets = network_data_sets
        self.activity_records = activity_records
        self.power_system_resource_roles = power_system_resource_roles
        self.location_roles = location_roles
        self.change_sets = change_sets
        self.erp_person_roles = erp_person_roles
        self.asset_roles = asset_roles
        self.scheduled_events = scheduled_events
        self.electronic_address = electronic_address
        self.measurements = measurements
        self.to_document_roles = to_document_roles
        self.erp_organisation_roles = erp_organisation_roles

        super(Document, self).__init__(**kw_args)
    # >>> document


class ElectronicAddress(IdentifiedObject):
    """ Electronic address information.
    """
    # Password needed to log in. 
    password = ''

    # Radio address. 
    radio = ''

    # Email address. 
    email = ''

    # World Wide Web address. 
    web = ''

    # User ID needed to log in, which can be for an individual person, an organisation, a location, etc. 
    user_id = ''

    # Address on local area network. 
    lan = ''

    # Status of this electronic address.
    status = None

    # Organisation owning this electronic address.
    organisation = None

    # All locations having this electronic address.
    locations = []

    document = None

    cashier = None

    # Asset owning this electronic address.
    asset = None

    erp_telephone_numbers = []

    erp_person = None

    # <<< electronic_address
    # @generated
    def __init__(self, password='', radio='', email='', web='', user_id='', lan='', status=None, organisation=None, locations=[], document=None, cashier=None, asset=None, erp_telephone_numbers=[], erp_person=None, **kw_args):
        """ Initialises a new 'ElectronicAddress' instance.
        """
        self.password = password
        self.radio = radio
        self.email = email
        self.web = web
        self.user_id = user_id
        self.lan = lan
        self.status = status
        self.organisation = organisation
        self.locations = locations
        self.document = document
        self.cashier = cashier
        self.asset = asset
        self.erp_telephone_numbers = erp_telephone_numbers
        self.erp_person = erp_person

        super(ElectronicAddress, self).__init__(**kw_args)
    # >>> electronic_address


class Status(Element):
    """ Current status information relevant to an entity.
    """
    # Date and time for which status 'value' applies. 
    date_time = ''

    # Pertinent information regarding the current 'value', as free form text. 
    remark = ''

    # Reason code or explanation for why an object went to the current status 'value'. 
    reason = ''

    # Status value at 'dateTime'; prior status changes may have been kept in instances of ActivityRecords associated with the object to which this Status applies. 
    value = ''

    # <<< status
    # @generated
    def __init__(self, date_time='', remark='', reason='', value='', **kw_args):
        """ Initialises a new 'Status' instance.
        """
        self.date_time = date_time
        self.remark = remark
        self.reason = reason
        self.value = value

        super(Status, self).__init__(**kw_args)
    # >>> status


class PostalAddress(Element):
    """ General purpose postal address information.
    """
    # Postal code for the address. 
    postal_code = ''

    # Post office box. 
    po_box = ''

    # Town detail.
    town_detail = None

    # Street detail.
    street_detail = None

    # <<< postal_address
    # @generated
    def __init__(self, postal_code='', po_box='', town_detail=None, street_detail=None, **kw_args):
        """ Initialises a new 'PostalAddress' instance.
        """
        self.postal_code = postal_code
        self.po_box = po_box
        self.town_detail = town_detail
        self.street_detail = street_detail

        super(PostalAddress, self).__init__(**kw_args)
    # >>> postal_address


class DateTimeInterval(Element):
    """ Interval of date and time.
    """
    # Date and time that this interval started. 
    start = ''

    # Date and time that this interval ended. 
    end = ''

    # <<< date_time_interval
    # @generated
    def __init__(self, start='', end='', **kw_args):
        """ Initialises a new 'DateTimeInterval' instance.
        """
        self.start = start
        self.end = end

        super(DateTimeInterval, self).__init__(**kw_args)
    # >>> date_time_interval


class Organisation(IdentifiedObject):
    """ Organisation that might have roles as utility, contractor, supplier, manufacturer, customer, etc.
    """
    # Street address.
    street_address = None

    # Postal address, potentially different than 'streetAddress' (e.g., another city).
    postal_address = None

    business_roles = []

    # All telephone numbers of this organisation.
    telephone_numbers = []

    market_roles = []

    # All electronic addresses of this organisation.
    electronic_addresses = []

    # <<< organisation
    # @generated
    def __init__(self, street_address=None, postal_address=None, business_roles=[], telephone_numbers=[], market_roles=[], electronic_addresses=[], **kw_args):
        """ Initialises a new 'Organisation' instance.
        """
        self.street_address = street_address
        self.postal_address = postal_address
        self.business_roles = business_roles
        self.telephone_numbers = telephone_numbers
        self.market_roles = market_roles
        self.electronic_addresses = electronic_addresses

        super(Organisation, self).__init__(**kw_args)
    # >>> organisation


class TimePoint(IdentifiedObject):
    """ A point in time within a sequence of points in time relative to a TimeSchedule.
    """
    # (if sequence-based) Relative sequence number for this time point. 
    sequence_number = 0

    # Absolute date and time for this time point. For calendar-based time point, it is typically manually entered, while for interval-based or sequence-based time point it is derived. 
    absolute_time = ''

    # (if interval-based) A point in time relative to scheduled start time in 'TimeSchedule.scheduleInterval.start'. 
    relative_time_interval = ''

    # Status of this time point.
    status = None

    # Interval defining the window of time that this time point is valid (for example, seasonal, only on weekends, not on weekends, only 8:00 to 5:00, etc.).
    window = None

    scheduled_events = []

    # Time schedule owning this time point.
    time_schedule = None

    # <<< time_point
    # @generated
    def __init__(self, sequence_number=0, absolute_time='', relative_time_interval='', status=None, window=None, scheduled_events=[], time_schedule=None, **kw_args):
        """ Initialises a new 'TimePoint' instance.
        """
        self.sequence_number = sequence_number
        self.absolute_time = absolute_time
        self.relative_time_interval = relative_time_interval
        self.status = status
        self.window = window
        self.scheduled_events = scheduled_events
        self.time_schedule = time_schedule

        super(TimePoint, self).__init__(**kw_args)
    # >>> time_point


class StreetDetail(Element):
    """ Street details, in the context of address.
    """
    # Suffix to the street name. For example: North, South, East, West. 
    suffix = ''

    # (if applicable) In certain cases the physical location of the place of interest does not have a direct point of entry from the street, but may be located inside a larger structure such as a building, complex, office block, apartment, etc. 
    building_name = ''

    # Name of the street. 
    name = ''

    # True if this street is within the legal geographical boundaries of the specified town (default). 
    within_town_limits = False

    # Prefix to the street name. For example: North, South, East, West. 
    prefix = ''

    # (if applicable) Utilities often make use of external reference systems, such as those of the town-planner's department or surveyor general's mapping system, that allocate global reference codes to streets. 
    code = ''

    # Designator of the specific location on the street. 
    number = ''

    # Additional address information, for example a mailstop. 
    address_general = ''

    # Type of street. Examples include: street, circle, boulevard, avenue, road, drive, etc. 
    type = ''

    # Number of the apartment or suite. 
    suite_number = ''

    # <<< street_detail
    # @generated
    def __init__(self, suffix='', building_name='', name='', within_town_limits=False, prefix='', code='', number='', address_general='', type='', suite_number='', **kw_args):
        """ Initialises a new 'StreetDetail' instance.
        """
        self.suffix = suffix
        self.building_name = building_name
        self.name = name
        self.within_town_limits = within_town_limits
        self.prefix = prefix
        self.code = code
        self.number = number
        self.address_general = address_general
        self.type = type
        self.suite_number = suite_number

        super(StreetDetail, self).__init__(**kw_args)
    # >>> street_detail


class TimeSchedule(Document):
    """ Description of anything that changes through time. Time schedule is used to perform a single-valued function of time. Use inherited 'category' attribute to give additional information on this schedule, such as: periodic (hourly, daily, weekly, monthly, etc.), day of the month, by date, calendar (specific times and dates).
    """
    # Interval at which the scheduled action repeats (e.g., first Monday of every month, last day of the month, etc.). 
    recurrence_pattern = ''

    # True if this schedule is deactivated (disabled). 
    disabled = False

    # The offset from midnight (i.e., 0 hours, 0 minutes, 0 seconds) for the periodic time points to begin. For example, for an interval meter that is set up for five minute intervals ('recurrencePeriod'=300=5 min), setting 'offset'=120=2 min would result in scheduled events to read the meter executing at 2, 7, 12, 17, 22, 27, 32, 37, 42, 47, 52, and 57 minutes past each hour. 
    offset = ''

    # Duration between time points, from the beginning of one period to the beginning of the next period. Note that a device like a meter may have multiple interval periods (e.g., 1, 5, 15, 30, or 60 minutes). 
    recurrence_period = ''

    # Schedule date and time interval.
    schedule_interval = None

    # Sequence of time points belonging to this time schedule.
    time_points = []

    # <<< time_schedule
    # @generated
    def __init__(self, recurrence_pattern='', disabled=False, offset='', recurrence_period='', schedule_interval=None, time_points=[], **kw_args):
        """ Initialises a new 'TimeSchedule' instance.
        """
        self.recurrence_pattern = recurrence_pattern
        self.disabled = disabled
        self.offset = offset
        self.recurrence_period = recurrence_period
        self.schedule_interval = schedule_interval
        self.time_points = time_points

        super(TimeSchedule, self).__init__(**kw_args)
    # >>> time_schedule


class Agreement(Document):
    """ Formal agreement between two parties defining the terms and conditions for a set of services. The specifics of the services are, in turn, defined via one or more service agreements.
    """
    # Date this agreement was consumated among associated persons and/or organisations. 
    sign_date = ''

    # Date and time interval this agreement is valid (from going into effect to termination).
    validity_interval = None

    # <<< agreement
    # @generated
    def __init__(self, sign_date='', validity_interval=None, **kw_args):
        """ Initialises a new 'Agreement' instance.
        """
        self.sign_date = sign_date
        self.validity_interval = validity_interval

        super(Agreement, self).__init__(**kw_args)
    # >>> agreement


# <<< common
# @generated
# >>> common

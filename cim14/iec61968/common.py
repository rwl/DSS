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

    def get_organisation(self):
        """ Organisation owning this telephone number.
        """
        return self._organisation

    def set_organisation(self, value):
        if self._organisation is not None:
            filtered = [x for x in self.organisation.telephone_numbers if x != self]
            self._organisation._telephone_numbers = filtered
            
        self._organisation = value
        if self._organisation is not None:
            self._organisation._telephone_numbers.append(self)

    organisation = property(get_organisation, set_organisation)

    def get_location(self):
        """ Location owning this telephone number.
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.telephone_numbers if x != self]
            self._location._telephone_numbers = filtered
            
        self._location = value
        if self._location is not None:
            self._location._telephone_numbers.append(self)

    location = property(get_location, set_location)

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
        self._organisation = None
        self.organisation = organisation
        self._location = None
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
    
    def add_gml_selectors(self, *gml_selectors):
        for obj in gml_selectors:
	        self._gml_selectors.append(obj)
        
    def remove_gml_selectors(self, *gml_selectors):
        for obj in gml_selectors:
	        self._gml_selectors.remove(obj)

    def get_to_location_roles(self):
        """ 
        """
        return self._to_location_roles

    def set_to_location_roles(self, value):
        for x in self._to_location_roles:
            x._from_location = None
        for y in value:
            y._from_location = self
        self._to_location_roles = value
            
    to_location_roles = property(get_to_location_roles, set_to_location_roles)
    
    def add_to_location_roles(self, *to_location_roles):
        for obj in to_location_roles:
            obj._from_location = self
            self._to_location_roles.append(obj)
        
    def remove_to_location_roles(self, *to_location_roles):
        for obj in to_location_roles:
            obj._from_location = None
            self._to_location_roles.remove(obj)

    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x._location = None
        for y in value:
            y._location = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._location = self
            self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._location = None
            self._erp_person_roles.remove(obj)

    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x._location = None
        for y in value:
            y._location = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._location = self
            self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._location = None
            self._change_items.remove(obj)

    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x._location = None
        for y in value:
            y._location = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._location = self
            self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._location = None
            self._power_system_resource_roles.remove(obj)

    land_properties = []
    
    def add_land_properties(self, *land_properties):
        for obj in land_properties:
	        self._land_properties.append(obj)
        
    def remove_land_properties(self, *land_properties):
        for obj in land_properties:
	        self._land_properties.remove(obj)

    red_lines = []
    
    def add_red_lines(self, *red_lines):
        for obj in red_lines:
	        self._red_lines.append(obj)
        
    def remove_red_lines(self, *red_lines):
        for obj in red_lines:
	        self._red_lines.remove(obj)

    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x._location = None
        for y in value:
            y._location = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._location = self
            self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._location = None
            self._erp_organisation_roles.remove(obj)

    hazards = []
    
    def add_hazards(self, *hazards):
        for obj in hazards:
	        self._hazards.append(obj)
        
    def remove_hazards(self, *hazards):
        for obj in hazards:
	        self._hazards.remove(obj)

    def get_from_location_roles(self):
        """ 
        """
        return self._from_location_roles

    def set_from_location_roles(self, value):
        for x in self._from_location_roles:
            x._to_location = None
        for y in value:
            y._to_location = self
        self._from_location_roles = value
            
    from_location_roles = property(get_from_location_roles, set_from_location_roles)
    
    def add_from_location_roles(self, *from_location_roles):
        for obj in from_location_roles:
            obj._to_location = self
            self._from_location_roles.append(obj)
        
    def remove_from_location_roles(self, *from_location_roles):
        for obj in from_location_roles:
            obj._to_location = None
            self._from_location_roles.remove(obj)

    measurements = []
    
    def add_measurements(self, *measurements):
        for obj in measurements:
	        self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
	        self._measurements.remove(obj)

    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x._location = None
        for y in value:
            y._location = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._location = self
            self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._location = None
            self._asset_roles.remove(obj)

    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x._location = None
        for y in value:
            y._location = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._location = self
            self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._location = None
            self._document_roles.remove(obj)

    def get_position_points(self):
        """ Sequence of position points describing this location.
        """
        return self._position_points

    def set_position_points(self, value):
        for x in self._position_points:
            x._location = None
        for y in value:
            y._location = self
        self._position_points = value
            
    position_points = property(get_position_points, set_position_points)
    
    def add_position_points(self, *position_points):
        for obj in position_points:
            obj._location = self
            self._position_points.append(obj)
        
    def remove_position_points(self, *position_points):
        for obj in position_points:
            obj._location = None
            self._position_points.remove(obj)

    electronic_addresses = []
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
	        self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
	        self._electronic_addresses.remove(obj)

    def get_telephone_numbers(self):
        """ All telephone numbers of this location.
        """
        return self._telephone_numbers

    def set_telephone_numbers(self, value):
        for x in self._telephone_numbers:
            x._location = None
        for y in value:
            y._location = self
        self._telephone_numbers = value
            
    telephone_numbers = property(get_telephone_numbers, set_telephone_numbers)
    
    def add_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._location = self
            self._telephone_numbers.append(obj)
        
    def remove_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._location = None
            self._telephone_numbers.remove(obj)

    def get_dimensions_info(self):
        """ 
        """
        return self._dimensions_info

    def set_dimensions_info(self, value):
        if self._dimensions_info is not None:
            filtered = [x for x in self.dimensions_info.locations if x != self]
            self._dimensions_info._locations = filtered
            
        self._dimensions_info = value
        if self._dimensions_info is not None:
            self._dimensions_info._locations.append(self)

    dimensions_info = property(get_dimensions_info, set_dimensions_info)

    routes = []
    
    def add_routes(self, *routes):
        for obj in routes:
	        self._routes.append(obj)
        
    def remove_routes(self, *routes):
        for obj in routes:
	        self._routes.remove(obj)

    gml_observatins = []
    
    def add_gml_observatins(self, *gml_observatins):
        for obj in gml_observatins:
	        self._gml_observatins.append(obj)
        
    def remove_gml_observatins(self, *gml_observatins):
        for obj in gml_observatins:
	        self._gml_observatins.remove(obj)

    activity_records = []
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.remove(obj)

    crews = []
    
    def add_crews(self, *crews):
        for obj in crews:
	        self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
	        self._crews.remove(obj)

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
        self._gml_selectors = []
        self.gml_selectors = gml_selectors
        self._to_location_roles = []
        self.to_location_roles = to_location_roles
        self._erp_person_roles = []
        self.erp_person_roles = erp_person_roles
        self._change_items = []
        self.change_items = change_items
        self._power_system_resource_roles = []
        self.power_system_resource_roles = power_system_resource_roles
        self._land_properties = []
        self.land_properties = land_properties
        self._red_lines = []
        self.red_lines = red_lines
        self._erp_organisation_roles = []
        self.erp_organisation_roles = erp_organisation_roles
        self._hazards = []
        self.hazards = hazards
        self._from_location_roles = []
        self.from_location_roles = from_location_roles
        self._measurements = []
        self.measurements = measurements
        self._asset_roles = []
        self.asset_roles = asset_roles
        self._document_roles = []
        self.document_roles = document_roles
        self._position_points = []
        self.position_points = position_points
        self._electronic_addresses = []
        self.electronic_addresses = electronic_addresses
        self._telephone_numbers = []
        self.telephone_numbers = telephone_numbers
        self._dimensions_info = None
        self.dimensions_info = dimensions_info
        self._routes = []
        self.routes = routes
        self._gml_observatins = []
        self.gml_observatins = gml_observatins
        self._activity_records = []
        self.activity_records = activity_records
        self._crews = []
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
    
    def add_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.remove(obj)

    market_factors = []
    
    def add_market_factors(self, *market_factors):
        for obj in market_factors:
	        self._market_factors.append(obj)
        
    def remove_market_factors(self, *market_factors):
        for obj in market_factors:
	        self._market_factors.remove(obj)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    assets = []
    
    def add_assets(self, *assets):
        for obj in assets:
	        self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
	        self._assets.remove(obj)

    power_system_resources = []
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
	        self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
	        self._power_system_resources.remove(obj)

    documents = []
    
    def add_documents(self, *documents):
        for obj in documents:
	        self._documents.append(obj)
        
    def remove_documents(self, *documents):
        for obj in documents:
	        self._documents.remove(obj)

    def get_scheduled_event(self):
        """ 
        """
        return self._scheduled_event

    def set_scheduled_event(self, value):
        if self._scheduled_event is not None:
            self._scheduled_event._activity_record = None
            
        self._scheduled_event = value
        if self._scheduled_event is not None:
            self._scheduled_event._activity_record = self
            
    scheduled_event = property(get_scheduled_event, set_scheduled_event)

    erp_persons = []
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.remove(obj)

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
        self._organisations = []
        self.organisations = organisations
        self._market_factors = []
        self.market_factors = market_factors
        self._locations = []
        self.locations = locations
        self._assets = []
        self.assets = assets
        self._power_system_resources = []
        self.power_system_resources = power_system_resources
        self._documents = []
        self.documents = documents
        self._scheduled_event = None
        self.scheduled_event = scheduled_event
        self._erp_persons = []
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

    def get_location(self):
        """ Location that this position point describes.
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.position_points if x != self]
            self._location._position_points = filtered
            
        self._location = value
        if self._location is not None:
            self._location._position_points.append(self)

    location = property(get_location, set_location)

    # <<< position_point
    # @generated
    def __init__(self, x_position='', y_position='', sequence_number=0, z_position='', location=None, **kw_args):
        """ Initialises a new 'PositionPoint' instance.
        """
        self.x_position = x_position
        self.y_position = y_position
        self.sequence_number = sequence_number
        self.z_position = z_position
        self._location = None
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

    def get_property_specification(self):
        """ 
        """
        return self._property_specification

    def set_property_specification(self, value):
        if self._property_specification is not None:
            filtered = [x for x in self.property_specification.asset_properites if x != self]
            self._property_specification._asset_properites = filtered
            
        self._property_specification = value
        if self._property_specification is not None:
            self._property_specification._asset_properites.append(self)

    property_specification = property(get_property_specification, set_property_specification)

    procedure_data_sets = []
    
    def add_procedure_data_sets(self, *procedure_data_sets):
        for obj in procedure_data_sets:
	        self._procedure_data_sets.append(obj)
        
    def remove_procedure_data_sets(self, *procedure_data_sets):
        for obj in procedure_data_sets:
	        self._procedure_data_sets.remove(obj)

    property_assets = []
    
    def add_property_assets(self, *property_assets):
        for obj in property_assets:
	        self._property_assets.append(obj)
        
    def remove_property_assets(self, *property_assets):
        for obj in property_assets:
	        self._property_assets.remove(obj)

    erp_ledger_entries = []
    
    def add_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
	        self._erp_ledger_entries.append(obj)
        
    def remove_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
	        self._erp_ledger_entries.remove(obj)

    erp_statement_line_items = []
    
    def add_erp_statement_line_items(self, *erp_statement_line_items):
        for obj in erp_statement_line_items:
	        self._erp_statement_line_items.append(obj)
        
    def remove_erp_statement_line_items(self, *erp_statement_line_items):
        for obj in erp_statement_line_items:
	        self._erp_statement_line_items.remove(obj)

    bill_determinants = []
    
    def add_bill_determinants(self, *bill_determinants):
        for obj in bill_determinants:
	        self._bill_determinants.append(obj)
        
    def remove_bill_determinants(self, *bill_determinants):
        for obj in bill_determinants:
	        self._bill_determinants.remove(obj)

    pass_through_bills = []
    
    def add_pass_through_bills(self, *pass_through_bills):
        for obj in pass_through_bills:
	        self._pass_through_bills.append(obj)
        
    def remove_pass_through_bills(self, *pass_through_bills):
        for obj in pass_through_bills:
	        self._pass_through_bills.remove(obj)

    def get_rating_specification(self):
        """ 
        """
        return self._rating_specification

    def set_rating_specification(self, value):
        if self._rating_specification is not None:
            filtered = [x for x in self.rating_specification.ratings if x != self]
            self._rating_specification._ratings = filtered
            
        self._rating_specification = value
        if self._rating_specification is not None:
            self._rating_specification._ratings.append(self)

    rating_specification = property(get_rating_specification, set_rating_specification)

    def get_transaction(self):
        """ Transaction for which this snapshot has been recorded.
        """
        return self._transaction

    def set_transaction(self, value):
        if self._transaction is not None:
            filtered = [x for x in self.transaction.user_attributes if x != self]
            self._transaction._user_attributes = filtered
            
        self._transaction = value
        if self._transaction is not None:
            self._transaction._user_attributes.append(self)

    transaction = property(get_transaction, set_transaction)

    erp_invoice_line_items = []
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
	        self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
	        self._erp_invoice_line_items.remove(obj)

    rating_assets = []
    
    def add_rating_assets(self, *rating_assets):
        for obj in rating_assets:
	        self._rating_assets.append(obj)
        
    def remove_rating_assets(self, *rating_assets):
        for obj in rating_assets:
	        self._rating_assets.remove(obj)

    def get_procedure(self):
        """ 
        """
        return self._procedure

    def set_procedure(self, value):
        if self._procedure is not None:
            filtered = [x for x in self.procedure.procedure_values if x != self]
            self._procedure._procedure_values = filtered
            
        self._procedure = value
        if self._procedure is not None:
            self._procedure._procedure_values.append(self)

    procedure = property(get_procedure, set_procedure)

    # <<< user_attribute
    # @generated
    def __init__(self, value='', sequence_number=0, name='', property_specification=None, procedure_data_sets=[], property_assets=[], erp_ledger_entries=[], erp_statement_line_items=[], bill_determinants=[], pass_through_bills=[], rating_specification=None, transaction=None, erp_invoice_line_items=[], rating_assets=[], procedure=None, **kw_args):
        """ Initialises a new 'UserAttribute' instance.
        """
        self.value = value
        self.sequence_number = sequence_number
        self.name = name
        self._property_specification = None
        self.property_specification = property_specification
        self._procedure_data_sets = []
        self.procedure_data_sets = procedure_data_sets
        self._property_assets = []
        self.property_assets = property_assets
        self._erp_ledger_entries = []
        self.erp_ledger_entries = erp_ledger_entries
        self._erp_statement_line_items = []
        self.erp_statement_line_items = erp_statement_line_items
        self._bill_determinants = []
        self.bill_determinants = bill_determinants
        self._pass_through_bills = []
        self.pass_through_bills = pass_through_bills
        self._rating_specification = None
        self.rating_specification = rating_specification
        self._transaction = None
        self.transaction = transaction
        self._erp_invoice_line_items = []
        self.erp_invoice_line_items = erp_invoice_line_items
        self._rating_assets = []
        self.rating_assets = rating_assets
        self._procedure = None
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

    def get_from_document_roles(self):
        """ 
        """
        return self._from_document_roles

    def set_from_document_roles(self, value):
        for x in self._from_document_roles:
            x._to_document = None
        for y in value:
            y._to_document = self
        self._from_document_roles = value
            
    from_document_roles = property(get_from_document_roles, set_from_document_roles)
    
    def add_from_document_roles(self, *from_document_roles):
        for obj in from_document_roles:
            obj._to_document = self
            self._from_document_roles.append(obj)
        
    def remove_from_document_roles(self, *from_document_roles):
        for obj in from_document_roles:
            obj._to_document = None
            self._from_document_roles.remove(obj)

    schedule_parameter_infos = []
    
    def add_schedule_parameter_infos(self, *schedule_parameter_infos):
        for obj in schedule_parameter_infos:
	        self._schedule_parameter_infos.append(obj)
        
    def remove_schedule_parameter_infos(self, *schedule_parameter_infos):
        for obj in schedule_parameter_infos:
	        self._schedule_parameter_infos.remove(obj)

    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x._document = None
        for y in value:
            y._document = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._document = self
            self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._document = None
            self._change_items.remove(obj)

    network_data_sets = []
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
	        self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
	        self._network_data_sets.remove(obj)

    activity_records = []
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.remove(obj)

    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x._document = None
        for y in value:
            y._document = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._document = self
            self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._document = None
            self._power_system_resource_roles.remove(obj)

    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x._document = None
        for y in value:
            y._document = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._document = self
            self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._document = None
            self._location_roles.remove(obj)

    change_sets = []
    
    def add_change_sets(self, *change_sets):
        for obj in change_sets:
	        self._change_sets.append(obj)
        
    def remove_change_sets(self, *change_sets):
        for obj in change_sets:
	        self._change_sets.remove(obj)

    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x._document = None
        for y in value:
            y._document = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._document = self
            self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._document = None
            self._erp_person_roles.remove(obj)

    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x._document = None
        for y in value:
            y._document = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._document = self
            self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._document = None
            self._asset_roles.remove(obj)

    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for x in self._scheduled_events:
            x._document = None
        for y in value:
            y._document = self
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._document = self
            self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._document = None
            self._scheduled_events.remove(obj)

    def get_electronic_address(self):
        """ 
        """
        return self._electronic_address

    def set_electronic_address(self, value):
        if self._electronic_address is not None:
            self._electronic_address._document = None
            
        self._electronic_address = value
        if self._electronic_address is not None:
            self._electronic_address._document = self
            
    electronic_address = property(get_electronic_address, set_electronic_address)

    measurements = []
    
    def add_measurements(self, *measurements):
        for obj in measurements:
	        self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
	        self._measurements.remove(obj)

    def get_to_document_roles(self):
        """ 
        """
        return self._to_document_roles

    def set_to_document_roles(self, value):
        for x in self._to_document_roles:
            x._from_document = None
        for y in value:
            y._from_document = self
        self._to_document_roles = value
            
    to_document_roles = property(get_to_document_roles, set_to_document_roles)
    
    def add_to_document_roles(self, *to_document_roles):
        for obj in to_document_roles:
            obj._from_document = self
            self._to_document_roles.append(obj)
        
    def remove_to_document_roles(self, *to_document_roles):
        for obj in to_document_roles:
            obj._from_document = None
            self._to_document_roles.remove(obj)

    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x._document = None
        for y in value:
            y._document = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._document = self
            self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._document = None
            self._erp_organisation_roles.remove(obj)

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
        self._from_document_roles = []
        self.from_document_roles = from_document_roles
        self._schedule_parameter_infos = []
        self.schedule_parameter_infos = schedule_parameter_infos
        self._change_items = []
        self.change_items = change_items
        self._network_data_sets = []
        self.network_data_sets = network_data_sets
        self._activity_records = []
        self.activity_records = activity_records
        self._power_system_resource_roles = []
        self.power_system_resource_roles = power_system_resource_roles
        self._location_roles = []
        self.location_roles = location_roles
        self._change_sets = []
        self.change_sets = change_sets
        self._erp_person_roles = []
        self.erp_person_roles = erp_person_roles
        self._asset_roles = []
        self.asset_roles = asset_roles
        self._scheduled_events = []
        self.scheduled_events = scheduled_events
        self._electronic_address = None
        self.electronic_address = electronic_address
        self._measurements = []
        self.measurements = measurements
        self._to_document_roles = []
        self.to_document_roles = to_document_roles
        self._erp_organisation_roles = []
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

    def get_organisation(self):
        """ Organisation owning this electronic address.
        """
        return self._organisation

    def set_organisation(self, value):
        if self._organisation is not None:
            filtered = [x for x in self.organisation.electronic_addresses if x != self]
            self._organisation._electronic_addresses = filtered
            
        self._organisation = value
        if self._organisation is not None:
            self._organisation._electronic_addresses.append(self)

    organisation = property(get_organisation, set_organisation)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            self._document._electronic_address = None
            
        self._document = value
        if self._document is not None:
            self._document._electronic_address = self
            
    document = property(get_document, set_document)

    def get_cashier(self):
        """ 
        """
        return self._cashier

    def set_cashier(self, value):
        if self._cashier is not None:
            filtered = [x for x in self.cashier.electronic_addresses if x != self]
            self._cashier._electronic_addresses = filtered
            
        self._cashier = value
        if self._cashier is not None:
            self._cashier._electronic_addresses.append(self)

    cashier = property(get_cashier, set_cashier)

    def get_asset(self):
        """ Asset owning this electronic address.
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            filtered = [x for x in self.asset.electronic_addresses if x != self]
            self._asset._electronic_addresses = filtered
            
        self._asset = value
        if self._asset is not None:
            self._asset._electronic_addresses.append(self)

    asset = property(get_asset, set_asset)

    def get_erp_telephone_numbers(self):
        """ 
        """
        return self._erp_telephone_numbers

    def set_erp_telephone_numbers(self, value):
        for x in self._erp_telephone_numbers:
            x._electronic_address = None
        for y in value:
            y._electronic_address = self
        self._erp_telephone_numbers = value
            
    erp_telephone_numbers = property(get_erp_telephone_numbers, set_erp_telephone_numbers)
    
    def add_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            obj._electronic_address = self
            self._erp_telephone_numbers.append(obj)
        
    def remove_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            obj._electronic_address = None
            self._erp_telephone_numbers.remove(obj)

    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.electronic_addresses if x != self]
            self._erp_person._electronic_addresses = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            self._erp_person._electronic_addresses.append(self)

    erp_person = property(get_erp_person, set_erp_person)

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
        self._organisation = None
        self.organisation = organisation
        self._locations = []
        self.locations = locations
        self._document = None
        self.document = document
        self._cashier = None
        self.cashier = cashier
        self._asset = None
        self.asset = asset
        self._erp_telephone_numbers = []
        self.erp_telephone_numbers = erp_telephone_numbers
        self._erp_person = None
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
    
    def add_business_roles(self, *business_roles):
        for obj in business_roles:
	        self._business_roles.append(obj)
        
    def remove_business_roles(self, *business_roles):
        for obj in business_roles:
	        self._business_roles.remove(obj)

    def get_telephone_numbers(self):
        """ All telephone numbers of this organisation.
        """
        return self._telephone_numbers

    def set_telephone_numbers(self, value):
        for x in self._telephone_numbers:
            x._organisation = None
        for y in value:
            y._organisation = self
        self._telephone_numbers = value
            
    telephone_numbers = property(get_telephone_numbers, set_telephone_numbers)
    
    def add_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._organisation = self
            self._telephone_numbers.append(obj)
        
    def remove_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._organisation = None
            self._telephone_numbers.remove(obj)

    market_roles = []
    
    def add_market_roles(self, *market_roles):
        for obj in market_roles:
	        self._market_roles.append(obj)
        
    def remove_market_roles(self, *market_roles):
        for obj in market_roles:
	        self._market_roles.remove(obj)

    def get_electronic_addresses(self):
        """ All electronic addresses of this organisation.
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x._organisation = None
        for y in value:
            y._organisation = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._organisation = self
            self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._organisation = None
            self._electronic_addresses.remove(obj)

    # <<< organisation
    # @generated
    def __init__(self, street_address=None, postal_address=None, business_roles=[], telephone_numbers=[], market_roles=[], electronic_addresses=[], **kw_args):
        """ Initialises a new 'Organisation' instance.
        """
        self.street_address = street_address
        self.postal_address = postal_address
        self._business_roles = []
        self.business_roles = business_roles
        self._telephone_numbers = []
        self.telephone_numbers = telephone_numbers
        self._market_roles = []
        self.market_roles = market_roles
        self._electronic_addresses = []
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

    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for x in self._scheduled_events:
            x._time_point = None
        for y in value:
            y._time_point = self
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._time_point = self
            self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._time_point = None
            self._scheduled_events.remove(obj)

    def get_time_schedule(self):
        """ Time schedule owning this time point.
        """
        return self._time_schedule

    def set_time_schedule(self, value):
        if self._time_schedule is not None:
            filtered = [x for x in self.time_schedule.time_points if x != self]
            self._time_schedule._time_points = filtered
            
        self._time_schedule = value
        if self._time_schedule is not None:
            self._time_schedule._time_points.append(self)

    time_schedule = property(get_time_schedule, set_time_schedule)

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
        self._scheduled_events = []
        self.scheduled_events = scheduled_events
        self._time_schedule = None
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

    def get_time_points(self):
        """ Sequence of time points belonging to this time schedule.
        """
        return self._time_points

    def set_time_points(self, value):
        for x in self._time_points:
            x._time_schedule = None
        for y in value:
            y._time_schedule = self
        self._time_points = value
            
    time_points = property(get_time_points, set_time_points)
    
    def add_time_points(self, *time_points):
        for obj in time_points:
            obj._time_schedule = self
            self._time_points.append(obj)
        
    def remove_time_points(self, *time_points):
        for obj in time_points:
            obj._time_schedule = None
            self._time_points.remove(obj)

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
        self._time_points = []
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

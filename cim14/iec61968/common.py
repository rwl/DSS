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
    # <<< telephone_number
    # @generated
    def __init__(self, country_code='', city_code='', local_number='', extension='', area_code='', organisation=None, location=None, **kw_args):
        """ Initialises a new 'TelephoneNumber' instance.
        """
        # Country code. 
        self.country_code = country_code
        # (if applicable) City code. 
        self.city_code = city_code
        # Main (local) part of this telephone number. 
        self.local_number = local_number
        # (if applicable) Extension for this telephone number. 
        self.extension = extension
        # Area or region code. 
        self.area_code = area_code
        
        self._organisation = None
        self.organisation = organisation
        self._location = None
        self.location = location

        super(TelephoneNumber, self).__init__(**kw_args)
    # >>> telephone_number
        
    # <<< organisation
    # @generated
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
            if self not in self._organisation._telephone_numbers:
                self._organisation._telephone_numbers.append(self)

    organisation = property(get_organisation, set_organisation)
    # >>> organisation

    # <<< location
    # @generated
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
            if self not in self._location._telephone_numbers:
                self._location._telephone_numbers.append(self)

    location = property(get_location, set_location)
    # >>> location



class Location(IdentifiedObject):
    """ The place, scene, or point of something where someone or something has been, is, and/or will be at a given moment in time. It may be: - Spatial location of an actual or planned structure, or a set of point-oriented structures (as a substation, structure, building, town, etc.) or diagram objects, which may be defined as a point or polygon, or, - Path of an underground or overhead conductor, or a linear diagram object.
    """
    # <<< location
    # @generated
    def __init__(self, corporate_code='', direction='', is_polygon=False, geo_info_reference='', category='', secondary_address=None, status=None, main_address=None, gml_selectors=None, to_location_roles=None, erp_person_roles=None, change_items=None, power_system_resource_roles=None, land_properties=None, red_lines=None, erp_organisation_roles=None, hazards=None, from_location_roles=None, measurements=None, asset_roles=None, document_roles=None, position_points=None, electronic_addresses=None, telephone_numbers=None, dimensions_info=None, routes=None, gml_observatins=None, activity_records=None, crews=None, **kw_args):
        """ Initialises a new 'Location' instance.
        """
        # Utility-specific code for the location. 
        self.corporate_code = corporate_code
        # (if applicable) Direction that allows field crews to quickly find a given asset. For a given location, such as a street address, this is the relative direction in wich to find the asset. For example, a Streetlight may be located at the 'NW' (northwest) corner of the customer's site, or a ServiceDeliveryPoint may be located on the second floor of an appartment building. 
        self.direction = direction
        # True if the first and last point (in the sequence of associated PositionPoints) are to be connected, thus forming a polygon rather than merely a sequence of line segments. 
        self.is_polygon = is_polygon
        # (if applicable) Reference to geographical information source, often external to the utility. 
        self.geo_info_reference = geo_info_reference
        # Category by utility's corporate standards and practices, relative to the location itself (e.g., geographical, functional accounting, etc., not a given property that happens to exist at that location). 
        self.category = category
        
        self.secondary_address = secondary_address
        self.status = status
        self.main_address = main_address
        self._gml_selectors = []
        if gml_selectors is None:
            self.gml_selectors = []
        else:
            self.gml_selectors = gml_selectors
        self._to_location_roles = []
        if to_location_roles is None:
            self.to_location_roles = []
        else:
            self.to_location_roles = to_location_roles
        self._erp_person_roles = []
        if erp_person_roles is None:
            self.erp_person_roles = []
        else:
            self.erp_person_roles = erp_person_roles
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items
        self._power_system_resource_roles = []
        if power_system_resource_roles is None:
            self.power_system_resource_roles = []
        else:
            self.power_system_resource_roles = power_system_resource_roles
        self._land_properties = []
        if land_properties is None:
            self.land_properties = []
        else:
            self.land_properties = land_properties
        self._red_lines = []
        if red_lines is None:
            self.red_lines = []
        else:
            self.red_lines = red_lines
        self._erp_organisation_roles = []
        if erp_organisation_roles is None:
            self.erp_organisation_roles = []
        else:
            self.erp_organisation_roles = erp_organisation_roles
        self._hazards = []
        if hazards is None:
            self.hazards = []
        else:
            self.hazards = hazards
        self._from_location_roles = []
        if from_location_roles is None:
            self.from_location_roles = []
        else:
            self.from_location_roles = from_location_roles
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._asset_roles = []
        if asset_roles is None:
            self.asset_roles = []
        else:
            self.asset_roles = asset_roles
        self._document_roles = []
        if document_roles is None:
            self.document_roles = []
        else:
            self.document_roles = document_roles
        self._position_points = []
        if position_points is None:
            self.position_points = []
        else:
            self.position_points = position_points
        self._electronic_addresses = []
        if electronic_addresses is None:
            self.electronic_addresses = []
        else:
            self.electronic_addresses = electronic_addresses
        self._telephone_numbers = []
        if telephone_numbers is None:
            self.telephone_numbers = []
        else:
            self.telephone_numbers = telephone_numbers
        self._dimensions_info = None
        self.dimensions_info = dimensions_info
        self._routes = []
        if routes is None:
            self.routes = []
        else:
            self.routes = routes
        self._gml_observatins = []
        if gml_observatins is None:
            self.gml_observatins = []
        else:
            self.gml_observatins = gml_observatins
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews

        super(Location, self).__init__(**kw_args)
    # >>> location
        
    # <<< secondary_address
    # @generated
    # Secondary address of the location. For example, PO Box address may have different ZIP code than that in the 'mainAddress'.
    secondary_address = None
    # >>> secondary_address

    # <<< status
    # @generated
    # Status of this location.
    status = None
    # >>> status

    # <<< main_address
    # @generated
    # Main address of the location.
    main_address = None
    # >>> main_address

    # <<< gml_selectors
    # @generated
    def get_gml_selectors(self):
        """ 
        """
        return self._gml_selectors

    def set_gml_selectors(self, value):
        for p in self._gml_selectors:
            filtered = [q for q in p.locations if q != self]
            self._gml_selectors._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._gml_selectors = value
            
    gml_selectors = property(get_gml_selectors, set_gml_selectors)
    
    def add_gml_selectors(self, *gml_selectors):
        for obj in gml_selectors:
            if self not in obj._locations:
                obj._locations.append(self)
            self._gml_selectors.append(obj)
        
    def remove_gml_selectors(self, *gml_selectors):
        for obj in gml_selectors:
            if self in obj._locations:
                obj._locations.remove(self)
            self._gml_selectors.remove(obj)
    # >>> gml_selectors

    # <<< to_location_roles
    # @generated
    def get_to_location_roles(self):
        """ 
        """
        return self._to_location_roles

    def set_to_location_roles(self, value):
        for x in self._to_location_roles:
            x.from_location = None
        for y in value:
            y.from_location = self
        self._to_location_roles = value
            
    to_location_roles = property(get_to_location_roles, set_to_location_roles)
    
    def add_to_location_roles(self, *to_location_roles):
        for obj in to_location_roles:
            obj._from_location = self
            if obj not in self._to_location_roles:
                self._to_location_roles.append(obj)
        
    def remove_to_location_roles(self, *to_location_roles):
        for obj in to_location_roles:
            obj._from_location = None
            self._to_location_roles.remove(obj)
    # >>> to_location_roles

    # <<< erp_person_roles
    # @generated
    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x.location = None
        for y in value:
            y.location = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._location = self
            if obj not in self._erp_person_roles:
                self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._location = None
            self._erp_person_roles.remove(obj)
    # >>> erp_person_roles

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.location = None
        for y in value:
            y.location = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._location = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._location = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< power_system_resource_roles
    # @generated
    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x.location = None
        for y in value:
            y.location = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._location = self
            if obj not in self._power_system_resource_roles:
                self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._location = None
            self._power_system_resource_roles.remove(obj)
    # >>> power_system_resource_roles

    # <<< land_properties
    # @generated
    def get_land_properties(self):
        """ 
        """
        return self._land_properties

    def set_land_properties(self, value):
        for p in self._land_properties:
            filtered = [q for q in p.locations if q != self]
            self._land_properties._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._land_properties = value
            
    land_properties = property(get_land_properties, set_land_properties)
    
    def add_land_properties(self, *land_properties):
        for obj in land_properties:
            if self not in obj._locations:
                obj._locations.append(self)
            self._land_properties.append(obj)
        
    def remove_land_properties(self, *land_properties):
        for obj in land_properties:
            if self in obj._locations:
                obj._locations.remove(self)
            self._land_properties.remove(obj)
    # >>> land_properties

    # <<< red_lines
    # @generated
    def get_red_lines(self):
        """ 
        """
        return self._red_lines

    def set_red_lines(self, value):
        for p in self._red_lines:
            filtered = [q for q in p.locations if q != self]
            self._red_lines._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._red_lines = value
            
    red_lines = property(get_red_lines, set_red_lines)
    
    def add_red_lines(self, *red_lines):
        for obj in red_lines:
            if self not in obj._locations:
                obj._locations.append(self)
            self._red_lines.append(obj)
        
    def remove_red_lines(self, *red_lines):
        for obj in red_lines:
            if self in obj._locations:
                obj._locations.remove(self)
            self._red_lines.remove(obj)
    # >>> red_lines

    # <<< erp_organisation_roles
    # @generated
    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x.location = None
        for y in value:
            y.location = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._location = self
            if obj not in self._erp_organisation_roles:
                self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._location = None
            self._erp_organisation_roles.remove(obj)
    # >>> erp_organisation_roles

    # <<< hazards
    # @generated
    def get_hazards(self):
        """ 
        """
        return self._hazards

    def set_hazards(self, value):
        for p in self._hazards:
            filtered = [q for q in p.locations if q != self]
            self._hazards._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._hazards = value
            
    hazards = property(get_hazards, set_hazards)
    
    def add_hazards(self, *hazards):
        for obj in hazards:
            if self not in obj._locations:
                obj._locations.append(self)
            self._hazards.append(obj)
        
    def remove_hazards(self, *hazards):
        for obj in hazards:
            if self in obj._locations:
                obj._locations.remove(self)
            self._hazards.remove(obj)
    # >>> hazards

    # <<< from_location_roles
    # @generated
    def get_from_location_roles(self):
        """ 
        """
        return self._from_location_roles

    def set_from_location_roles(self, value):
        for x in self._from_location_roles:
            x.to_location = None
        for y in value:
            y.to_location = self
        self._from_location_roles = value
            
    from_location_roles = property(get_from_location_roles, set_from_location_roles)
    
    def add_from_location_roles(self, *from_location_roles):
        for obj in from_location_roles:
            obj._to_location = self
            if obj not in self._from_location_roles:
                self._from_location_roles.append(obj)
        
    def remove_from_location_roles(self, *from_location_roles):
        for obj in from_location_roles:
            obj._to_location = None
            self._from_location_roles.remove(obj)
    # >>> from_location_roles

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ 
        """
        return self._measurements

    def set_measurements(self, value):
        for p in self._measurements:
            filtered = [q for q in p.locations if q != self]
            self._measurements._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            if self not in obj._locations:
                obj._locations.append(self)
            self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            if self in obj._locations:
                obj._locations.remove(self)
            self._measurements.remove(obj)
    # >>> measurements

    # <<< asset_roles
    # @generated
    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x.location = None
        for y in value:
            y.location = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._location = self
            if obj not in self._asset_roles:
                self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._location = None
            self._asset_roles.remove(obj)
    # >>> asset_roles

    # <<< document_roles
    # @generated
    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x.location = None
        for y in value:
            y.location = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._location = self
            if obj not in self._document_roles:
                self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._location = None
            self._document_roles.remove(obj)
    # >>> document_roles

    # <<< position_points
    # @generated
    def get_position_points(self):
        """ Sequence of position points describing this location.
        """
        return self._position_points

    def set_position_points(self, value):
        for x in self._position_points:
            x.location = None
        for y in value:
            y.location = self
        self._position_points = value
            
    position_points = property(get_position_points, set_position_points)
    
    def add_position_points(self, *position_points):
        for obj in position_points:
            obj._location = self
            if obj not in self._position_points:
                self._position_points.append(obj)
        
    def remove_position_points(self, *position_points):
        for obj in position_points:
            obj._location = None
            self._position_points.remove(obj)
    # >>> position_points

    # <<< electronic_addresses
    # @generated
    def get_electronic_addresses(self):
        """ All electronic addresses of this location.
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for p in self._electronic_addresses:
            filtered = [q for q in p.locations if q != self]
            self._electronic_addresses._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            if self not in obj._locations:
                obj._locations.append(self)
            self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            if self in obj._locations:
                obj._locations.remove(self)
            self._electronic_addresses.remove(obj)
    # >>> electronic_addresses

    # <<< telephone_numbers
    # @generated
    def get_telephone_numbers(self):
        """ All telephone numbers of this location.
        """
        return self._telephone_numbers

    def set_telephone_numbers(self, value):
        for x in self._telephone_numbers:
            x.location = None
        for y in value:
            y.location = self
        self._telephone_numbers = value
            
    telephone_numbers = property(get_telephone_numbers, set_telephone_numbers)
    
    def add_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._location = self
            if obj not in self._telephone_numbers:
                self._telephone_numbers.append(obj)
        
    def remove_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._location = None
            self._telephone_numbers.remove(obj)
    # >>> telephone_numbers

    # <<< dimensions_info
    # @generated
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
            if self not in self._dimensions_info._locations:
                self._dimensions_info._locations.append(self)

    dimensions_info = property(get_dimensions_info, set_dimensions_info)
    # >>> dimensions_info

    # <<< routes
    # @generated
    def get_routes(self):
        """ 
        """
        return self._routes

    def set_routes(self, value):
        for p in self._routes:
            filtered = [q for q in p.locations if q != self]
            self._routes._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._routes = value
            
    routes = property(get_routes, set_routes)
    
    def add_routes(self, *routes):
        for obj in routes:
            if self not in obj._locations:
                obj._locations.append(self)
            self._routes.append(obj)
        
    def remove_routes(self, *routes):
        for obj in routes:
            if self in obj._locations:
                obj._locations.remove(self)
            self._routes.remove(obj)
    # >>> routes

    # <<< gml_observatins
    # @generated
    def get_gml_observatins(self):
        """ 
        """
        return self._gml_observatins

    def set_gml_observatins(self, value):
        for p in self._gml_observatins:
            filtered = [q for q in p.locations if q != self]
            self._gml_observatins._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._gml_observatins = value
            
    gml_observatins = property(get_gml_observatins, set_gml_observatins)
    
    def add_gml_observatins(self, *gml_observatins):
        for obj in gml_observatins:
            if self not in obj._locations:
                obj._locations.append(self)
            self._gml_observatins.append(obj)
        
    def remove_gml_observatins(self, *gml_observatins):
        for obj in gml_observatins:
            if self in obj._locations:
                obj._locations.remove(self)
            self._gml_observatins.remove(obj)
    # >>> gml_observatins

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ 
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self._activity_records:
            filtered = [q for q in p.locations if q != self]
            self._activity_records._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._locations:
                obj._locations.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._locations:
                obj._locations.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records

    # <<< crews
    # @generated
    def get_crews(self):
        """ 
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.locations if q != self]
            self._crews._locations = filtered
        for r in value:
            if self not in r._locations:
                r._locations.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._locations:
                obj._locations.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._locations:
                obj._locations.remove(self)
            self._crews.remove(obj)
    # >>> crews



class ActivityRecord(IdentifiedObject):
    """ Records activity for an entity at a point in time; activity may be for an event that has already occurred or for a planned activity.
    """
    # <<< activity_record
    # @generated
    def __init__(self, severity='', reason='', category='', created_date_time='', status=None, organisations=None, market_factors=None, locations=None, assets=None, power_system_resources=None, documents=None, scheduled_event=None, erp_persons=None, **kw_args):
        """ Initialises a new 'ActivityRecord' instance.
        """
        # Severity level of event resulting in this activity record. 
        self.severity = severity
        # Reason for event resulting in this activity record, typically supplied when user initiated. 
        self.reason = reason
        # Category of event resulting in this activity record. 
        self.category = category
        # Date and time this activity record has been created (different from the 'status.dateTime', which is the time of a status change of the associated object, if applicable). 
        self.created_date_time = created_date_time
        
        self.status = status
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations
        self._market_factors = []
        if market_factors is None:
            self.market_factors = []
        else:
            self.market_factors = market_factors
        self._locations = []
        if locations is None:
            self.locations = []
        else:
            self.locations = locations
        self._assets = []
        if assets is None:
            self.assets = []
        else:
            self.assets = assets
        self._power_system_resources = []
        if power_system_resources is None:
            self.power_system_resources = []
        else:
            self.power_system_resources = power_system_resources
        self._documents = []
        if documents is None:
            self.documents = []
        else:
            self.documents = documents
        self._scheduled_event = None
        self.scheduled_event = scheduled_event
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons

        super(ActivityRecord, self).__init__(**kw_args)
    # >>> activity_record
        
    # <<< status
    # @generated
    # Inofrmation on consequence of event resulting in this activity record.
    status = None
    # >>> status

    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self._organisations:
            filtered = [q for q in p.activity_records if q != self]
            self._organisations._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._organisations.remove(obj)
    # >>> organisations

    # <<< market_factors
    # @generated
    def get_market_factors(self):
        """ 
        """
        return self._market_factors

    def set_market_factors(self, value):
        for p in self._market_factors:
            filtered = [q for q in p.activity_records if q != self]
            self._market_factors._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._market_factors = value
            
    market_factors = property(get_market_factors, set_market_factors)
    
    def add_market_factors(self, *market_factors):
        for obj in market_factors:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._market_factors.append(obj)
        
    def remove_market_factors(self, *market_factors):
        for obj in market_factors:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._market_factors.remove(obj)
    # >>> market_factors

    # <<< locations
    # @generated
    def get_locations(self):
        """ 
        """
        return self._locations

    def set_locations(self, value):
        for p in self._locations:
            filtered = [q for q in p.activity_records if q != self]
            self._locations._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._locations = value
            
    locations = property(get_locations, set_locations)
    
    def add_locations(self, *locations):
        for obj in locations:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._locations.remove(obj)
    # >>> locations

    # <<< assets
    # @generated
    def get_assets(self):
        """ All assets for which this activity record has been created.
        """
        return self._assets

    def set_assets(self, value):
        for p in self._assets:
            filtered = [q for q in p.activity_records if q != self]
            self._assets._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._assets.remove(obj)
    # >>> assets

    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.activity_records if q != self]
            self._power_system_resources._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources

    # <<< documents
    # @generated
    def get_documents(self):
        """ All documents for which this activity record has been created.
        """
        return self._documents

    def set_documents(self, value):
        for p in self._documents:
            filtered = [q for q in p.activity_records if q != self]
            self._documents._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._documents = value
            
    documents = property(get_documents, set_documents)
    
    def add_documents(self, *documents):
        for obj in documents:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._documents.append(obj)
        
    def remove_documents(self, *documents):
        for obj in documents:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._documents.remove(obj)
    # >>> documents

    # <<< scheduled_event
    # @generated
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
    # >>> scheduled_event

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self._erp_persons:
            filtered = [q for q in p.activity_records if q != self]
            self._erp_persons._activity_records = filtered
        for r in value:
            if self not in r._activity_records:
                r._activity_records.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._activity_records:
                obj._activity_records.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._activity_records:
                obj._activity_records.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons



class PositionPoint(Element):
    """ Set of spatial coordinates that determine a point. A sequence of PositionPoints can be used to describe: - physical location of non-point oriented objects like cables or lines, or - area of an object like a substation, a geographical zone or a diagram object.
    """
    # <<< position_point
    # @generated
    def __init__(self, x_position='', y_position='', sequence_number=0, z_position='', location=None, **kw_args):
        """ Initialises a new 'PositionPoint' instance.
        """
        # X axis position. 
        self.x_position = x_position
        # Y axis position. 
        self.y_position = y_position
        # Zero-relative sequence number of this point within a series of points. 
        self.sequence_number = sequence_number
        # (if applicable) Z axis position. 
        self.z_position = z_position
        
        self._location = None
        self.location = location

        super(PositionPoint, self).__init__(**kw_args)
    # >>> position_point
        
    # <<< location
    # @generated
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
            if self not in self._location._position_points:
                self._location._position_points.append(self)

    location = property(get_location, set_location)
    # >>> location



class StreetAddress(Element):
    """ General purpose street address information.
    """
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
        
    # <<< street_detail
    # @generated
    # Street detail.
    street_detail = None
    # >>> street_detail

    # <<< town_detail
    # @generated
    # Town detail.
    town_detail = None
    # >>> town_detail

    # <<< status
    # @generated
    # Status of this address.
    status = None
    # >>> status



class TownDetail(Element):
    """ Town details, in the context of address.
    """
    # <<< town_detail
    # @generated
    def __init__(self, name='', section='', state_or_province='', code='', country='', **kw_args):
        """ Initialises a new 'TownDetail' instance.
        """
        # Town name. 
        self.name = name
        # Town section. For example, it is common for there to be 36 sections per township. 
        self.section = section
        # Name of the state or province. 
        self.state_or_province = state_or_province
        # Town code. 
        self.code = code
        # Name of the country. 
        self.country = country
        

        super(TownDetail, self).__init__(**kw_args)
    # >>> town_detail
        


class UserAttribute(Element):
    """ Generic name-value pair class, with optional sequence number and units for value; can be used to model parts of information exchange when concrete types are not known in advance.
    """
    # <<< user_attribute
    # @generated
    def __init__(self, value='', sequence_number=0, name='', property_specification=None, procedure_data_sets=None, property_assets=None, erp_ledger_entries=None, erp_statement_line_items=None, bill_determinants=None, pass_through_bills=None, rating_specification=None, transaction=None, erp_invoice_line_items=None, rating_assets=None, procedure=None, **kw_args):
        """ Initialises a new 'UserAttribute' instance.
        """
        # Value of an attribute, including unit information. 
        self.value = value
        # Sequence number for this attribute in a list of attributes. 
        self.sequence_number = sequence_number
        # Name of an attribute. 
        self.name = name
        
        self._property_specification = None
        self.property_specification = property_specification
        self._procedure_data_sets = []
        if procedure_data_sets is None:
            self.procedure_data_sets = []
        else:
            self.procedure_data_sets = procedure_data_sets
        self._property_assets = []
        if property_assets is None:
            self.property_assets = []
        else:
            self.property_assets = property_assets
        self._erp_ledger_entries = []
        if erp_ledger_entries is None:
            self.erp_ledger_entries = []
        else:
            self.erp_ledger_entries = erp_ledger_entries
        self._erp_statement_line_items = []
        if erp_statement_line_items is None:
            self.erp_statement_line_items = []
        else:
            self.erp_statement_line_items = erp_statement_line_items
        self._bill_determinants = []
        if bill_determinants is None:
            self.bill_determinants = []
        else:
            self.bill_determinants = bill_determinants
        self._pass_through_bills = []
        if pass_through_bills is None:
            self.pass_through_bills = []
        else:
            self.pass_through_bills = pass_through_bills
        self._rating_specification = None
        self.rating_specification = rating_specification
        self._transaction = None
        self.transaction = transaction
        self._erp_invoice_line_items = []
        if erp_invoice_line_items is None:
            self.erp_invoice_line_items = []
        else:
            self.erp_invoice_line_items = erp_invoice_line_items
        self._rating_assets = []
        if rating_assets is None:
            self.rating_assets = []
        else:
            self.rating_assets = rating_assets
        self._procedure = None
        self.procedure = procedure

        super(UserAttribute, self).__init__(**kw_args)
    # >>> user_attribute
        
    # <<< property_specification
    # @generated
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
            if self not in self._property_specification._asset_properites:
                self._property_specification._asset_properites.append(self)

    property_specification = property(get_property_specification, set_property_specification)
    # >>> property_specification

    # <<< procedure_data_sets
    # @generated
    def get_procedure_data_sets(self):
        """ 
        """
        return self._procedure_data_sets

    def set_procedure_data_sets(self, value):
        for p in self._procedure_data_sets:
            filtered = [q for q in p.properties if q != self]
            self._procedure_data_sets._properties = filtered
        for r in value:
            if self not in r._properties:
                r._properties.append(self)
        self._procedure_data_sets = value
            
    procedure_data_sets = property(get_procedure_data_sets, set_procedure_data_sets)
    
    def add_procedure_data_sets(self, *procedure_data_sets):
        for obj in procedure_data_sets:
            if self not in obj._properties:
                obj._properties.append(self)
            self._procedure_data_sets.append(obj)
        
    def remove_procedure_data_sets(self, *procedure_data_sets):
        for obj in procedure_data_sets:
            if self in obj._properties:
                obj._properties.remove(self)
            self._procedure_data_sets.remove(obj)
    # >>> procedure_data_sets

    # <<< property_assets
    # @generated
    def get_property_assets(self):
        """ 
        """
        return self._property_assets

    def set_property_assets(self, value):
        for p in self._property_assets:
            filtered = [q for q in p.properties if q != self]
            self._property_assets._properties = filtered
        for r in value:
            if self not in r._properties:
                r._properties.append(self)
        self._property_assets = value
            
    property_assets = property(get_property_assets, set_property_assets)
    
    def add_property_assets(self, *property_assets):
        for obj in property_assets:
            if self not in obj._properties:
                obj._properties.append(self)
            self._property_assets.append(obj)
        
    def remove_property_assets(self, *property_assets):
        for obj in property_assets:
            if self in obj._properties:
                obj._properties.remove(self)
            self._property_assets.remove(obj)
    # >>> property_assets

    # <<< erp_ledger_entries
    # @generated
    def get_erp_ledger_entries(self):
        """ 
        """
        return self._erp_ledger_entries

    def set_erp_ledger_entries(self, value):
        for p in self._erp_ledger_entries:
            filtered = [q for q in p.user_attributes if q != self]
            self._erp_ledger_entries._user_attributes = filtered
        for r in value:
            if self not in r._user_attributes:
                r._user_attributes.append(self)
        self._erp_ledger_entries = value
            
    erp_ledger_entries = property(get_erp_ledger_entries, set_erp_ledger_entries)
    
    def add_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            if self not in obj._user_attributes:
                obj._user_attributes.append(self)
            self._erp_ledger_entries.append(obj)
        
    def remove_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            if self in obj._user_attributes:
                obj._user_attributes.remove(self)
            self._erp_ledger_entries.remove(obj)
    # >>> erp_ledger_entries

    # <<< erp_statement_line_items
    # @generated
    def get_erp_statement_line_items(self):
        """ 
        """
        return self._erp_statement_line_items

    def set_erp_statement_line_items(self, value):
        for p in self._erp_statement_line_items:
            filtered = [q for q in p.user_attributes if q != self]
            self._erp_statement_line_items._user_attributes = filtered
        for r in value:
            if self not in r._user_attributes:
                r._user_attributes.append(self)
        self._erp_statement_line_items = value
            
    erp_statement_line_items = property(get_erp_statement_line_items, set_erp_statement_line_items)
    
    def add_erp_statement_line_items(self, *erp_statement_line_items):
        for obj in erp_statement_line_items:
            if self not in obj._user_attributes:
                obj._user_attributes.append(self)
            self._erp_statement_line_items.append(obj)
        
    def remove_erp_statement_line_items(self, *erp_statement_line_items):
        for obj in erp_statement_line_items:
            if self in obj._user_attributes:
                obj._user_attributes.remove(self)
            self._erp_statement_line_items.remove(obj)
    # >>> erp_statement_line_items

    # <<< bill_determinants
    # @generated
    def get_bill_determinants(self):
        """ 
        """
        return self._bill_determinants

    def set_bill_determinants(self, value):
        for p in self._bill_determinants:
            filtered = [q for q in p.user_attributes if q != self]
            self._bill_determinants._user_attributes = filtered
        for r in value:
            if self not in r._user_attributes:
                r._user_attributes.append(self)
        self._bill_determinants = value
            
    bill_determinants = property(get_bill_determinants, set_bill_determinants)
    
    def add_bill_determinants(self, *bill_determinants):
        for obj in bill_determinants:
            if self not in obj._user_attributes:
                obj._user_attributes.append(self)
            self._bill_determinants.append(obj)
        
    def remove_bill_determinants(self, *bill_determinants):
        for obj in bill_determinants:
            if self in obj._user_attributes:
                obj._user_attributes.remove(self)
            self._bill_determinants.remove(obj)
    # >>> bill_determinants

    # <<< pass_through_bills
    # @generated
    def get_pass_through_bills(self):
        """ 
        """
        return self._pass_through_bills

    def set_pass_through_bills(self, value):
        for p in self._pass_through_bills:
            filtered = [q for q in p.user_attributes if q != self]
            self._pass_through_bills._user_attributes = filtered
        for r in value:
            if self not in r._user_attributes:
                r._user_attributes.append(self)
        self._pass_through_bills = value
            
    pass_through_bills = property(get_pass_through_bills, set_pass_through_bills)
    
    def add_pass_through_bills(self, *pass_through_bills):
        for obj in pass_through_bills:
            if self not in obj._user_attributes:
                obj._user_attributes.append(self)
            self._pass_through_bills.append(obj)
        
    def remove_pass_through_bills(self, *pass_through_bills):
        for obj in pass_through_bills:
            if self in obj._user_attributes:
                obj._user_attributes.remove(self)
            self._pass_through_bills.remove(obj)
    # >>> pass_through_bills

    # <<< rating_specification
    # @generated
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
            if self not in self._rating_specification._ratings:
                self._rating_specification._ratings.append(self)

    rating_specification = property(get_rating_specification, set_rating_specification)
    # >>> rating_specification

    # <<< transaction
    # @generated
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
            if self not in self._transaction._user_attributes:
                self._transaction._user_attributes.append(self)

    transaction = property(get_transaction, set_transaction)
    # >>> transaction

    # <<< erp_invoice_line_items
    # @generated
    def get_erp_invoice_line_items(self):
        """ 
        """
        return self._erp_invoice_line_items

    def set_erp_invoice_line_items(self, value):
        for p in self._erp_invoice_line_items:
            filtered = [q for q in p.user_attributes if q != self]
            self._erp_invoice_line_items._user_attributes = filtered
        for r in value:
            if self not in r._user_attributes:
                r._user_attributes.append(self)
        self._erp_invoice_line_items = value
            
    erp_invoice_line_items = property(get_erp_invoice_line_items, set_erp_invoice_line_items)
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self not in obj._user_attributes:
                obj._user_attributes.append(self)
            self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self in obj._user_attributes:
                obj._user_attributes.remove(self)
            self._erp_invoice_line_items.remove(obj)
    # >>> erp_invoice_line_items

    # <<< rating_assets
    # @generated
    def get_rating_assets(self):
        """ 
        """
        return self._rating_assets

    def set_rating_assets(self, value):
        for p in self._rating_assets:
            filtered = [q for q in p.ratings if q != self]
            self._rating_assets._ratings = filtered
        for r in value:
            if self not in r._ratings:
                r._ratings.append(self)
        self._rating_assets = value
            
    rating_assets = property(get_rating_assets, set_rating_assets)
    
    def add_rating_assets(self, *rating_assets):
        for obj in rating_assets:
            if self not in obj._ratings:
                obj._ratings.append(self)
            self._rating_assets.append(obj)
        
    def remove_rating_assets(self, *rating_assets):
        for obj in rating_assets:
            if self in obj._ratings:
                obj._ratings.remove(self)
            self._rating_assets.remove(obj)
    # >>> rating_assets

    # <<< procedure
    # @generated
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
            if self not in self._procedure._procedure_values:
                self._procedure._procedure_values.append(self)

    procedure = property(get_procedure, set_procedure)
    # >>> procedure



class Document(IdentifiedObject):
    """ Parent class for different groupings of information collected and managed as a part of a business process. It will frequently contain references to other objects, such as assets, people and power system resources.
    """
    # <<< document
    # @generated
    def __init__(self, last_modified_date_time='', subject='', title='', revision_number='', created_date_time='', category='', doc_status=None, status=None, from_document_roles=None, schedule_parameter_infos=None, change_items=None, network_data_sets=None, activity_records=None, power_system_resource_roles=None, location_roles=None, change_sets=None, erp_person_roles=None, asset_roles=None, scheduled_events=None, electronic_address=None, measurements=None, to_document_roles=None, erp_organisation_roles=None, **kw_args):
        """ Initialises a new 'Document' instance.
        """
        # Date and time this document was last modified. Documents may potentially be modified many times during their lifetime. 
        self.last_modified_date_time = last_modified_date_time
        # Document subject. 
        self.subject = subject
        # Document title. 
        self.title = title
        # Revision number for this document. 
        self.revision_number = revision_number
        # Date and time that this document was created. 
        self.created_date_time = created_date_time
        # Utility-specific categorisation of this document, according to their corporate standards, practices, and existing IT systems (e.g., for management of assets, maintenance, work, outage, customers, etc.). 
        self.category = category
        
        self.doc_status = doc_status
        self.status = status
        self._from_document_roles = []
        if from_document_roles is None:
            self.from_document_roles = []
        else:
            self.from_document_roles = from_document_roles
        self._schedule_parameter_infos = []
        if schedule_parameter_infos is None:
            self.schedule_parameter_infos = []
        else:
            self.schedule_parameter_infos = schedule_parameter_infos
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items
        self._network_data_sets = []
        if network_data_sets is None:
            self.network_data_sets = []
        else:
            self.network_data_sets = network_data_sets
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records
        self._power_system_resource_roles = []
        if power_system_resource_roles is None:
            self.power_system_resource_roles = []
        else:
            self.power_system_resource_roles = power_system_resource_roles
        self._location_roles = []
        if location_roles is None:
            self.location_roles = []
        else:
            self.location_roles = location_roles
        self._change_sets = []
        if change_sets is None:
            self.change_sets = []
        else:
            self.change_sets = change_sets
        self._erp_person_roles = []
        if erp_person_roles is None:
            self.erp_person_roles = []
        else:
            self.erp_person_roles = erp_person_roles
        self._asset_roles = []
        if asset_roles is None:
            self.asset_roles = []
        else:
            self.asset_roles = asset_roles
        self._scheduled_events = []
        if scheduled_events is None:
            self.scheduled_events = []
        else:
            self.scheduled_events = scheduled_events
        self._electronic_address = None
        self.electronic_address = electronic_address
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._to_document_roles = []
        if to_document_roles is None:
            self.to_document_roles = []
        else:
            self.to_document_roles = to_document_roles
        self._erp_organisation_roles = []
        if erp_organisation_roles is None:
            self.erp_organisation_roles = []
        else:
            self.erp_organisation_roles = erp_organisation_roles

        super(Document, self).__init__(**kw_args)
    # >>> document
        
    # <<< doc_status
    # @generated
    # Status of this document. For status of subject metter this document represents (e.g., Agreement, Work), use 'status' attribute. Example values for 'docStatus.status' are draft, approved, cancelled, etc.
    doc_status = None
    # >>> doc_status

    # <<< status
    # @generated
    # Status of subject metter (e.g., Agreement, Work) this document represents. For status of the document itself, use 'docStatus' attribute.
    status = None
    # >>> status

    # <<< from_document_roles
    # @generated
    def get_from_document_roles(self):
        """ 
        """
        return self._from_document_roles

    def set_from_document_roles(self, value):
        for x in self._from_document_roles:
            x.to_document = None
        for y in value:
            y.to_document = self
        self._from_document_roles = value
            
    from_document_roles = property(get_from_document_roles, set_from_document_roles)
    
    def add_from_document_roles(self, *from_document_roles):
        for obj in from_document_roles:
            obj._to_document = self
            if obj not in self._from_document_roles:
                self._from_document_roles.append(obj)
        
    def remove_from_document_roles(self, *from_document_roles):
        for obj in from_document_roles:
            obj._to_document = None
            self._from_document_roles.remove(obj)
    # >>> from_document_roles

    # <<< schedule_parameter_infos
    # @generated
    def get_schedule_parameter_infos(self):
        """ 
        """
        return self._schedule_parameter_infos

    def set_schedule_parameter_infos(self, value):
        for p in self._schedule_parameter_infos:
            filtered = [q for q in p.documents if q != self]
            self._schedule_parameter_infos._documents = filtered
        for r in value:
            if self not in r._documents:
                r._documents.append(self)
        self._schedule_parameter_infos = value
            
    schedule_parameter_infos = property(get_schedule_parameter_infos, set_schedule_parameter_infos)
    
    def add_schedule_parameter_infos(self, *schedule_parameter_infos):
        for obj in schedule_parameter_infos:
            if self not in obj._documents:
                obj._documents.append(self)
            self._schedule_parameter_infos.append(obj)
        
    def remove_schedule_parameter_infos(self, *schedule_parameter_infos):
        for obj in schedule_parameter_infos:
            if self in obj._documents:
                obj._documents.remove(self)
            self._schedule_parameter_infos.remove(obj)
    # >>> schedule_parameter_infos

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.document = None
        for y in value:
            y.document = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._document = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._document = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.documents if q != self]
            self._network_data_sets._documents = filtered
        for r in value:
            if self not in r._documents:
                r._documents.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._documents:
                obj._documents.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._documents:
                obj._documents.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ All activity records created for this document.
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self._activity_records:
            filtered = [q for q in p.documents if q != self]
            self._activity_records._documents = filtered
        for r in value:
            if self not in r._documents:
                r._documents.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._documents:
                obj._documents.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._documents:
                obj._documents.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records

    # <<< power_system_resource_roles
    # @generated
    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x.document = None
        for y in value:
            y.document = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._document = self
            if obj not in self._power_system_resource_roles:
                self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._document = None
            self._power_system_resource_roles.remove(obj)
    # >>> power_system_resource_roles

    # <<< location_roles
    # @generated
    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x.document = None
        for y in value:
            y.document = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._document = self
            if obj not in self._location_roles:
                self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._document = None
            self._location_roles.remove(obj)
    # >>> location_roles

    # <<< change_sets
    # @generated
    def get_change_sets(self):
        """ 
        """
        return self._change_sets

    def set_change_sets(self, value):
        for p in self._change_sets:
            filtered = [q for q in p.documents if q != self]
            self._change_sets._documents = filtered
        for r in value:
            if self not in r._documents:
                r._documents.append(self)
        self._change_sets = value
            
    change_sets = property(get_change_sets, set_change_sets)
    
    def add_change_sets(self, *change_sets):
        for obj in change_sets:
            if self not in obj._documents:
                obj._documents.append(self)
            self._change_sets.append(obj)
        
    def remove_change_sets(self, *change_sets):
        for obj in change_sets:
            if self in obj._documents:
                obj._documents.remove(self)
            self._change_sets.remove(obj)
    # >>> change_sets

    # <<< erp_person_roles
    # @generated
    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x.document = None
        for y in value:
            y.document = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._document = self
            if obj not in self._erp_person_roles:
                self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._document = None
            self._erp_person_roles.remove(obj)
    # >>> erp_person_roles

    # <<< asset_roles
    # @generated
    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x.document = None
        for y in value:
            y.document = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._document = self
            if obj not in self._asset_roles:
                self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._document = None
            self._asset_roles.remove(obj)
    # >>> asset_roles

    # <<< scheduled_events
    # @generated
    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for x in self._scheduled_events:
            x.document = None
        for y in value:
            y.document = self
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._document = self
            if obj not in self._scheduled_events:
                self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._document = None
            self._scheduled_events.remove(obj)
    # >>> scheduled_events

    # <<< electronic_address
    # @generated
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
    # >>> electronic_address

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ Measurements are specified in types of documents, such as procedures.
        """
        return self._measurements

    def set_measurements(self, value):
        for p in self._measurements:
            filtered = [q for q in p.documents if q != self]
            self._measurements._documents = filtered
        for r in value:
            if self not in r._documents:
                r._documents.append(self)
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            if self not in obj._documents:
                obj._documents.append(self)
            self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            if self in obj._documents:
                obj._documents.remove(self)
            self._measurements.remove(obj)
    # >>> measurements

    # <<< to_document_roles
    # @generated
    def get_to_document_roles(self):
        """ 
        """
        return self._to_document_roles

    def set_to_document_roles(self, value):
        for x in self._to_document_roles:
            x.from_document = None
        for y in value:
            y.from_document = self
        self._to_document_roles = value
            
    to_document_roles = property(get_to_document_roles, set_to_document_roles)
    
    def add_to_document_roles(self, *to_document_roles):
        for obj in to_document_roles:
            obj._from_document = self
            if obj not in self._to_document_roles:
                self._to_document_roles.append(obj)
        
    def remove_to_document_roles(self, *to_document_roles):
        for obj in to_document_roles:
            obj._from_document = None
            self._to_document_roles.remove(obj)
    # >>> to_document_roles

    # <<< erp_organisation_roles
    # @generated
    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x.document = None
        for y in value:
            y.document = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._document = self
            if obj not in self._erp_organisation_roles:
                self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._document = None
            self._erp_organisation_roles.remove(obj)
    # >>> erp_organisation_roles



class ElectronicAddress(IdentifiedObject):
    """ Electronic address information.
    """
    # <<< electronic_address
    # @generated
    def __init__(self, password='', radio='', email='', web='', user_id='', lan='', status=None, organisation=None, locations=None, document=None, cashier=None, asset=None, erp_telephone_numbers=None, erp_person=None, **kw_args):
        """ Initialises a new 'ElectronicAddress' instance.
        """
        # Password needed to log in. 
        self.password = password
        # Radio address. 
        self.radio = radio
        # Email address. 
        self.email = email
        # World Wide Web address. 
        self.web = web
        # User ID needed to log in, which can be for an individual person, an organisation, a location, etc. 
        self.user_id = user_id
        # Address on local area network. 
        self.lan = lan
        
        self.status = status
        self._organisation = None
        self.organisation = organisation
        self._locations = []
        if locations is None:
            self.locations = []
        else:
            self.locations = locations
        self._document = None
        self.document = document
        self._cashier = None
        self.cashier = cashier
        self._asset = None
        self.asset = asset
        self._erp_telephone_numbers = []
        if erp_telephone_numbers is None:
            self.erp_telephone_numbers = []
        else:
            self.erp_telephone_numbers = erp_telephone_numbers
        self._erp_person = None
        self.erp_person = erp_person

        super(ElectronicAddress, self).__init__(**kw_args)
    # >>> electronic_address
        
    # <<< status
    # @generated
    # Status of this electronic address.
    status = None
    # >>> status

    # <<< organisation
    # @generated
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
            if self not in self._organisation._electronic_addresses:
                self._organisation._electronic_addresses.append(self)

    organisation = property(get_organisation, set_organisation)
    # >>> organisation

    # <<< locations
    # @generated
    def get_locations(self):
        """ All locations having this electronic address.
        """
        return self._locations

    def set_locations(self, value):
        for p in self._locations:
            filtered = [q for q in p.electronic_addresses if q != self]
            self._locations._electronic_addresses = filtered
        for r in value:
            if self not in r._electronic_addresses:
                r._electronic_addresses.append(self)
        self._locations = value
            
    locations = property(get_locations, set_locations)
    
    def add_locations(self, *locations):
        for obj in locations:
            if self not in obj._electronic_addresses:
                obj._electronic_addresses.append(self)
            self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
            if self in obj._electronic_addresses:
                obj._electronic_addresses.remove(self)
            self._locations.remove(obj)
    # >>> locations

    # <<< document
    # @generated
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
    # >>> document

    # <<< cashier
    # @generated
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
            if self not in self._cashier._electronic_addresses:
                self._cashier._electronic_addresses.append(self)

    cashier = property(get_cashier, set_cashier)
    # >>> cashier

    # <<< asset
    # @generated
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
            if self not in self._asset._electronic_addresses:
                self._asset._electronic_addresses.append(self)

    asset = property(get_asset, set_asset)
    # >>> asset

    # <<< erp_telephone_numbers
    # @generated
    def get_erp_telephone_numbers(self):
        """ 
        """
        return self._erp_telephone_numbers

    def set_erp_telephone_numbers(self, value):
        for x in self._erp_telephone_numbers:
            x.electronic_address = None
        for y in value:
            y.electronic_address = self
        self._erp_telephone_numbers = value
            
    erp_telephone_numbers = property(get_erp_telephone_numbers, set_erp_telephone_numbers)
    
    def add_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            obj._electronic_address = self
            if obj not in self._erp_telephone_numbers:
                self._erp_telephone_numbers.append(obj)
        
    def remove_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            obj._electronic_address = None
            self._erp_telephone_numbers.remove(obj)
    # >>> erp_telephone_numbers

    # <<< erp_person
    # @generated
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
            if self not in self._erp_person._electronic_addresses:
                self._erp_person._electronic_addresses.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person



class Status(Element):
    """ Current status information relevant to an entity.
    """
    # <<< status
    # @generated
    def __init__(self, date_time='', remark='', reason='', value='', **kw_args):
        """ Initialises a new 'Status' instance.
        """
        # Date and time for which status 'value' applies. 
        self.date_time = date_time
        # Pertinent information regarding the current 'value', as free form text. 
        self.remark = remark
        # Reason code or explanation for why an object went to the current status 'value'. 
        self.reason = reason
        # Status value at 'dateTime'; prior status changes may have been kept in instances of ActivityRecords associated with the object to which this Status applies. 
        self.value = value
        

        super(Status, self).__init__(**kw_args)
    # >>> status
        


class PostalAddress(Element):
    """ General purpose postal address information.
    """
    # <<< postal_address
    # @generated
    def __init__(self, postal_code='', po_box='', town_detail=None, street_detail=None, **kw_args):
        """ Initialises a new 'PostalAddress' instance.
        """
        # Postal code for the address. 
        self.postal_code = postal_code
        # Post office box. 
        self.po_box = po_box
        
        self.town_detail = town_detail
        self.street_detail = street_detail

        super(PostalAddress, self).__init__(**kw_args)
    # >>> postal_address
        
    # <<< town_detail
    # @generated
    # Town detail.
    town_detail = None
    # >>> town_detail

    # <<< street_detail
    # @generated
    # Street detail.
    street_detail = None
    # >>> street_detail



class DateTimeInterval(Element):
    """ Interval of date and time.
    """
    # <<< date_time_interval
    # @generated
    def __init__(self, start='', end='', **kw_args):
        """ Initialises a new 'DateTimeInterval' instance.
        """
        # Date and time that this interval started. 
        self.start = start
        # Date and time that this interval ended. 
        self.end = end
        

        super(DateTimeInterval, self).__init__(**kw_args)
    # >>> date_time_interval
        


class Organisation(IdentifiedObject):
    """ Organisation that might have roles as utility, contractor, supplier, manufacturer, customer, etc.
    """
    # <<< organisation
    # @generated
    def __init__(self, street_address=None, postal_address=None, business_roles=None, telephone_numbers=None, market_roles=None, electronic_addresses=None, **kw_args):
        """ Initialises a new 'Organisation' instance.
        """
        
        self.street_address = street_address
        self.postal_address = postal_address
        self._business_roles = []
        if business_roles is None:
            self.business_roles = []
        else:
            self.business_roles = business_roles
        self._telephone_numbers = []
        if telephone_numbers is None:
            self.telephone_numbers = []
        else:
            self.telephone_numbers = telephone_numbers
        self._market_roles = []
        if market_roles is None:
            self.market_roles = []
        else:
            self.market_roles = market_roles
        self._electronic_addresses = []
        if electronic_addresses is None:
            self.electronic_addresses = []
        else:
            self.electronic_addresses = electronic_addresses

        super(Organisation, self).__init__(**kw_args)
    # >>> organisation
        
    # <<< street_address
    # @generated
    # Street address.
    street_address = None
    # >>> street_address

    # <<< postal_address
    # @generated
    # Postal address, potentially different than 'streetAddress' (e.g., another city).
    postal_address = None
    # >>> postal_address

    # <<< business_roles
    # @generated
    def get_business_roles(self):
        """ 
        """
        return self._business_roles

    def set_business_roles(self, value):
        for p in self._business_roles:
            filtered = [q for q in p.organisations if q != self]
            self._business_roles._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._business_roles = value
            
    business_roles = property(get_business_roles, set_business_roles)
    
    def add_business_roles(self, *business_roles):
        for obj in business_roles:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._business_roles.append(obj)
        
    def remove_business_roles(self, *business_roles):
        for obj in business_roles:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._business_roles.remove(obj)
    # >>> business_roles

    # <<< telephone_numbers
    # @generated
    def get_telephone_numbers(self):
        """ All telephone numbers of this organisation.
        """
        return self._telephone_numbers

    def set_telephone_numbers(self, value):
        for x in self._telephone_numbers:
            x.organisation = None
        for y in value:
            y.organisation = self
        self._telephone_numbers = value
            
    telephone_numbers = property(get_telephone_numbers, set_telephone_numbers)
    
    def add_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._organisation = self
            if obj not in self._telephone_numbers:
                self._telephone_numbers.append(obj)
        
    def remove_telephone_numbers(self, *telephone_numbers):
        for obj in telephone_numbers:
            obj._organisation = None
            self._telephone_numbers.remove(obj)
    # >>> telephone_numbers

    # <<< market_roles
    # @generated
    def get_market_roles(self):
        """ 
        """
        return self._market_roles

    def set_market_roles(self, value):
        for p in self._market_roles:
            filtered = [q for q in p.organisations if q != self]
            self._market_roles._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._market_roles = value
            
    market_roles = property(get_market_roles, set_market_roles)
    
    def add_market_roles(self, *market_roles):
        for obj in market_roles:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._market_roles.append(obj)
        
    def remove_market_roles(self, *market_roles):
        for obj in market_roles:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._market_roles.remove(obj)
    # >>> market_roles

    # <<< electronic_addresses
    # @generated
    def get_electronic_addresses(self):
        """ All electronic addresses of this organisation.
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x.organisation = None
        for y in value:
            y.organisation = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._organisation = self
            if obj not in self._electronic_addresses:
                self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._organisation = None
            self._electronic_addresses.remove(obj)
    # >>> electronic_addresses



class TimePoint(IdentifiedObject):
    """ A point in time within a sequence of points in time relative to a TimeSchedule.
    """
    # <<< time_point
    # @generated
    def __init__(self, sequence_number=0, absolute_time='', relative_time_interval=0.0, status=None, window=None, scheduled_events=None, time_schedule=None, **kw_args):
        """ Initialises a new 'TimePoint' instance.
        """
        # (if sequence-based) Relative sequence number for this time point. 
        self.sequence_number = sequence_number
        # Absolute date and time for this time point. For calendar-based time point, it is typically manually entered, while for interval-based or sequence-based time point it is derived. 
        self.absolute_time = absolute_time
        # (if interval-based) A point in time relative to scheduled start time in 'TimeSchedule.scheduleInterval.start'. 
        self.relative_time_interval = relative_time_interval
        
        self.status = status
        self.window = window
        self._scheduled_events = []
        if scheduled_events is None:
            self.scheduled_events = []
        else:
            self.scheduled_events = scheduled_events
        self._time_schedule = None
        self.time_schedule = time_schedule

        super(TimePoint, self).__init__(**kw_args)
    # >>> time_point
        
    # <<< status
    # @generated
    # Status of this time point.
    status = None
    # >>> status

    # <<< window
    # @generated
    # Interval defining the window of time that this time point is valid (for example, seasonal, only on weekends, not on weekends, only 8:00 to 5:00, etc.).
    window = None
    # >>> window

    # <<< scheduled_events
    # @generated
    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for x in self._scheduled_events:
            x.time_point = None
        for y in value:
            y.time_point = self
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._time_point = self
            if obj not in self._scheduled_events:
                self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._time_point = None
            self._scheduled_events.remove(obj)
    # >>> scheduled_events

    # <<< time_schedule
    # @generated
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
            if self not in self._time_schedule._time_points:
                self._time_schedule._time_points.append(self)

    time_schedule = property(get_time_schedule, set_time_schedule)
    # >>> time_schedule



class StreetDetail(Element):
    """ Street details, in the context of address.
    """
    # <<< street_detail
    # @generated
    def __init__(self, suffix='', building_name='', name='', within_town_limits=False, prefix='', code='', number='', address_general='', type='', suite_number='', **kw_args):
        """ Initialises a new 'StreetDetail' instance.
        """
        # Suffix to the street name. For example: North, South, East, West. 
        self.suffix = suffix
        # (if applicable) In certain cases the physical location of the place of interest does not have a direct point of entry from the street, but may be located inside a larger structure such as a building, complex, office block, apartment, etc. 
        self.building_name = building_name
        # Name of the street. 
        self.name = name
        # True if this street is within the legal geographical boundaries of the specified town (default). 
        self.within_town_limits = within_town_limits
        # Prefix to the street name. For example: North, South, East, West. 
        self.prefix = prefix
        # (if applicable) Utilities often make use of external reference systems, such as those of the town-planner's department or surveyor general's mapping system, that allocate global reference codes to streets. 
        self.code = code
        # Designator of the specific location on the street. 
        self.number = number
        # Additional address information, for example a mailstop. 
        self.address_general = address_general
        # Type of street. Examples include: street, circle, boulevard, avenue, road, drive, etc. 
        self.type = type
        # Number of the apartment or suite. 
        self.suite_number = suite_number
        

        super(StreetDetail, self).__init__(**kw_args)
    # >>> street_detail
        


class TimeSchedule(Document):
    """ Description of anything that changes through time. Time schedule is used to perform a single-valued function of time. Use inherited 'category' attribute to give additional information on this schedule, such as: periodic (hourly, daily, weekly, monthly, etc.), day of the month, by date, calendar (specific times and dates).
    """
    # <<< time_schedule
    # @generated
    def __init__(self, recurrence_pattern='', disabled=False, offset=0.0, recurrence_period=0.0, schedule_interval=None, time_points=None, **kw_args):
        """ Initialises a new 'TimeSchedule' instance.
        """
        # Interval at which the scheduled action repeats (e.g., first Monday of every month, last day of the month, etc.). 
        self.recurrence_pattern = recurrence_pattern
        # True if this schedule is deactivated (disabled). 
        self.disabled = disabled
        # The offset from midnight (i.e., 0 hours, 0 minutes, 0 seconds) for the periodic time points to begin. For example, for an interval meter that is set up for five minute intervals ('recurrencePeriod'=300=5 min), setting 'offset'=120=2 min would result in scheduled events to read the meter executing at 2, 7, 12, 17, 22, 27, 32, 37, 42, 47, 52, and 57 minutes past each hour. 
        self.offset = offset
        # Duration between time points, from the beginning of one period to the beginning of the next period. Note that a device like a meter may have multiple interval periods (e.g., 1, 5, 15, 30, or 60 minutes). 
        self.recurrence_period = recurrence_period
        
        self.schedule_interval = schedule_interval
        self._time_points = []
        if time_points is None:
            self.time_points = []
        else:
            self.time_points = time_points

        super(TimeSchedule, self).__init__(**kw_args)
    # >>> time_schedule
        
    # <<< schedule_interval
    # @generated
    # Schedule date and time interval.
    schedule_interval = None
    # >>> schedule_interval

    # <<< time_points
    # @generated
    def get_time_points(self):
        """ Sequence of time points belonging to this time schedule.
        """
        return self._time_points

    def set_time_points(self, value):
        for x in self._time_points:
            x.time_schedule = None
        for y in value:
            y.time_schedule = self
        self._time_points = value
            
    time_points = property(get_time_points, set_time_points)
    
    def add_time_points(self, *time_points):
        for obj in time_points:
            obj._time_schedule = self
            if obj not in self._time_points:
                self._time_points.append(obj)
        
    def remove_time_points(self, *time_points):
        for obj in time_points:
            obj._time_schedule = None
            self._time_points.remove(obj)
    # >>> time_points



class Agreement(Document):
    """ Formal agreement between two parties defining the terms and conditions for a set of services. The specifics of the services are, in turn, defined via one or more service agreements.
    """
    # <<< agreement
    # @generated
    def __init__(self, sign_date='', validity_interval=None, **kw_args):
        """ Initialises a new 'Agreement' instance.
        """
        # Date this agreement was consumated among associated persons and/or organisations. 
        self.sign_date = sign_date
        
        self.validity_interval = validity_interval

        super(Agreement, self).__init__(**kw_args)
    # >>> agreement
        
    # <<< validity_interval
    # @generated
    # Date and time interval this agreement is valid (from going into effect to termination).
    validity_interval = None
    # >>> validity_interval



# <<< common
# @generated
# >>> common

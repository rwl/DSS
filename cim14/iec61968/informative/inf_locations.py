# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.informative.inf_common import Role
from cim14.iec61968.common import Agreement
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Location

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfLocations"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfLocations"

class PsrLocRole(Role):
    """ Roles played between Power System Resources and Locations.
    """
    def get_power_system_resource(self):
        """ 
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.location_roles if x != self]
            self._power_system_resource._location_roles = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            self._power_system_resource._location_roles.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)

    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.power_system_resource_roles if x != self]
            self._location._power_system_resource_roles = filtered
            
        self._location = value
        if self._location is not None:
            self._location._power_system_resource_roles.append(self)

    location = property(get_location, set_location)

    # <<< psr_loc_role
    # @generated
    def __init__(self, power_system_resource=None, location=None, **kw_args):
        """ Initialises a new 'PsrLocRole' instance.
        """
        self._power_system_resource = None
        self.power_system_resource = power_system_resource
        self._location = None
        self.location = location

        super(PsrLocRole, self).__init__(**kw_args)
    # >>> psr_loc_role


class OrgPropertyRole(Role):
    """ Roles played between Organisations and a given piece of property. For example, the Organisation may be the owner, renter, occupier, taxiing authority, etc.
    """
    land_property = []
    
    def add_land_property(self, *land_property):
        for obj in land_property:
	        self._land_property.append(obj)
        
    def remove_land_property(self, *land_property):
        for obj in land_property:
	        self._land_property.remove(obj)

    def get_erp_organisation(self):
        """ 
        """
        return self._erp_organisation

    def set_erp_organisation(self, value):
        if self._erp_organisation is not None:
            filtered = [x for x in self.erp_organisation.land_property_roles if x != self]
            self._erp_organisation._land_property_roles = filtered
            
        self._erp_organisation = value
        if self._erp_organisation is not None:
            self._erp_organisation._land_property_roles.append(self)

    erp_organisation = property(get_erp_organisation, set_erp_organisation)

    # <<< org_property_role
    # @generated
    def __init__(self, land_property=[], erp_organisation=None, **kw_args):
        """ Initialises a new 'OrgPropertyRole' instance.
        """
        self._land_property = []
        self.land_property = land_property
        self._erp_organisation = None
        self.erp_organisation = erp_organisation

        super(OrgPropertyRole, self).__init__(**kw_args)
    # >>> org_property_role


class LocationGrant(Agreement):
    """ A grant provides a right, as defined by type, for a parcel of land. Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    # Property related information that describes the Grant's land parcel. For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    def get_land_property(self):
        """ Land property this location grant applies to.
        """
        return self._land_property

    def set_land_property(self, value):
        if self._land_property is not None:
            filtered = [x for x in self.land_property.location_grants if x != self]
            self._land_property._location_grants = filtered
            
        self._land_property = value
        if self._land_property is not None:
            self._land_property._location_grants.append(self)

    land_property = property(get_land_property, set_land_property)

    # <<< location_grant
    # @generated
    def __init__(self, property_data='', land_property=None, **kw_args):
        """ Initialises a new 'LocationGrant' instance.
        """
        self.property_data = property_data
        self._land_property = None
        self.land_property = land_property

        super(LocationGrant, self).__init__(**kw_args)
    # >>> location_grant


class RightOfWay(Agreement):
    """ A right-of-way (ROW) is for land where it is lawful to use for a public road, an electric power line, etc. Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    # Property related information that describes the ROW's land parcel. For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    land_properties = []
    
    def add_land_properties(self, *land_properties):
        for obj in land_properties:
	        self._land_properties.append(obj)
        
    def remove_land_properties(self, *land_properties):
        for obj in land_properties:
	        self._land_properties.remove(obj)

    # <<< right_of_way
    # @generated
    def __init__(self, property_data='', land_properties=[], **kw_args):
        """ Initialises a new 'RightOfWay' instance.
        """
        self.property_data = property_data
        self._land_properties = []
        self.land_properties = land_properties

        super(RightOfWay, self).__init__(**kw_args)
    # >>> right_of_way


class OrgLocRole(Role):
    """ Roles played between Organisations and Locations, for example a service territory or school district. Note that roles dealing with use of a specific piece of property should be defined based on the relationship between OccupationsOfProperty and Location.
    """
    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.erp_organisation_roles if x != self]
            self._location._erp_organisation_roles = filtered
            
        self._location = value
        if self._location is not None:
            self._location._erp_organisation_roles.append(self)

    location = property(get_location, set_location)

    def get_erp_organisation(self):
        """ 
        """
        return self._erp_organisation

    def set_erp_organisation(self, value):
        if self._erp_organisation is not None:
            filtered = [x for x in self.erp_organisation.location_roles if x != self]
            self._erp_organisation._location_roles = filtered
            
        self._erp_organisation = value
        if self._erp_organisation is not None:
            self._erp_organisation._location_roles.append(self)

    erp_organisation = property(get_erp_organisation, set_erp_organisation)

    # <<< org_loc_role
    # @generated
    def __init__(self, location=None, erp_organisation=None, **kw_args):
        """ Initialises a new 'OrgLocRole' instance.
        """
        self._location = None
        self.location = location
        self._erp_organisation = None
        self.erp_organisation = erp_organisation

        super(OrgLocRole, self).__init__(**kw_args)
    # >>> org_loc_role


class RedLine(IdentifiedObject):
    """ This class is used for handling the accompanying annotations, time stamp, author, etc. of designs, drawings and maps. A red line can be associated with any Location object.
    """
    status = None

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    # <<< red_line
    # @generated
    def __init__(self, status=None, locations=[], **kw_args):
        """ Initialises a new 'RedLine' instance.
        """
        self.status = status
        self._locations = []
        self.locations = locations

        super(RedLine, self).__init__(**kw_args)
    # >>> red_line


class Hazard(IdentifiedObject):
    """ A hazard is any object or condition that is a danger for causing loss or perils to an asset and/or people. Examples of hazards are trees growing under overhead power lines, a park being located by a substation (i.e., children climb fence to recover a ball), a lake near an overhead distribution line (fishing pole/line contacting power lines), etc.
    """
    # Category by utility's corporate standards and practices. 
    category = ''

    status = None

    assets = []
    
    def add_assets(self, *assets):
        for obj in assets:
	        self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
	        self._assets.remove(obj)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    # <<< hazard
    # @generated
    def __init__(self, category='', status=None, assets=[], locations=[], **kw_args):
        """ Initialises a new 'Hazard' instance.
        """
        self.category = category
        self.status = status
        self._assets = []
        self.assets = assets
        self._locations = []
        self.locations = locations

        super(Hazard, self).__init__(**kw_args)
    # >>> hazard


class PersonPropertyRole(Role):
    """ The role of a person relative to a given piece of property. Examples of roles include: owner, renter, contractor, etc.
    """
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.land_property_roles if x != self]
            self._erp_person._land_property_roles = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            self._erp_person._land_property_roles.append(self)

    erp_person = property(get_erp_person, set_erp_person)

    def get_land_property(self):
        """ 
        """
        return self._land_property

    def set_land_property(self, value):
        if self._land_property is not None:
            filtered = [x for x in self.land_property.erp_person_roles if x != self]
            self._land_property._erp_person_roles = filtered
            
        self._land_property = value
        if self._land_property is not None:
            self._land_property._erp_person_roles.append(self)

    land_property = property(get_land_property, set_land_property)

    # <<< person_property_role
    # @generated
    def __init__(self, erp_person=None, land_property=None, **kw_args):
        """ Initialises a new 'PersonPropertyRole' instance.
        """
        self._erp_person = None
        self.erp_person = erp_person
        self._land_property = None
        self.land_property = land_property

        super(PersonPropertyRole, self).__init__(**kw_args)
    # >>> person_property_role


class ErpPersonLocRole(Role):
    """ Roles played between People and Locations. Some Locations are somewhat static, like the person's home address. Other may be dynamic, for example when the person is part of a crew driving around in truck.
    """
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.location_roles if x != self]
            self._erp_person._location_roles = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            self._erp_person._location_roles.append(self)

    erp_person = property(get_erp_person, set_erp_person)

    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.erp_person_roles if x != self]
            self._location._erp_person_roles = filtered
            
        self._location = value
        if self._location is not None:
            self._location._erp_person_roles.append(self)

    location = property(get_location, set_location)

    # <<< erp_person_loc_role
    # @generated
    def __init__(self, erp_person=None, location=None, **kw_args):
        """ Initialises a new 'ErpPersonLocRole' instance.
        """
        self._erp_person = None
        self.erp_person = erp_person
        self._location = None
        self.location = location

        super(ErpPersonLocRole, self).__init__(**kw_args)
    # >>> erp_person_loc_role


class Route(IdentifiedObject):
    """ Route that is followed, for example by service crews.
    """
    # Category by utility's work management standards and practices. 
    category = ''

    status = None

    def get_crews(self):
        """ 
        """
        return self._crews

    def set_crews(self, value):
        for x in self._crews:
            x._route = None
        for y in value:
            y._route = self
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            obj._route = self
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            obj._route = None
            self._crews.remove(obj)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    # <<< route
    # @generated
    def __init__(self, category='', status=None, crews=[], locations=[], **kw_args):
        """ Initialises a new 'Route' instance.
        """
        self.category = category
        self.status = status
        self._crews = []
        self.crews = crews
        self._locations = []
        self.locations = locations

        super(Route, self).__init__(**kw_args)
    # >>> route


class Zone(Location):
    """ Area divided off from other areas. It may be part of the electrical network, a land area where special restrictions apply, weather areas, etc. For weather, it is an area where a set of relatively homogenous weather measurements apply.
    """
    # Kind of this zone. Values are: "special_restriction_land", "weather_zone", "other", "electrical_network"
    kind = 'special_restriction_land'

    # <<< zone
    # @generated
    def __init__(self, kind='special_restriction_land', **kw_args):
        """ Initialises a new 'Zone' instance.
        """
        self.kind = kind

        super(Zone, self).__init__(**kw_args)
    # >>> zone


class AssetLocRole(Role):
    """ Roles played between Assets and Locations.
    """
    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.asset_roles if x != self]
            self._location._asset_roles = filtered
            
        self._location = value
        if self._location is not None:
            self._location._asset_roles.append(self)

    location = property(get_location, set_location)

    def get_asset(self):
        """ 
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            filtered = [x for x in self.asset.location_roles if x != self]
            self._asset._location_roles = filtered
            
        self._asset = value
        if self._asset is not None:
            self._asset._location_roles.append(self)

    asset = property(get_asset, set_asset)

    # <<< asset_loc_role
    # @generated
    def __init__(self, location=None, asset=None, **kw_args):
        """ Initialises a new 'AssetLocRole' instance.
        """
        self._location = None
        self.location = location
        self._asset = None
        self.asset = asset

        super(AssetLocRole, self).__init__(**kw_args)
    # >>> asset_loc_role


class LandProperty(IdentifiedObject):
    """ Information about a particular piece of (land) property such as its use. Ownership of the property may be determined through associations to Organisations and/or ErpPersons.
    """
    # Kind of (land) property, categorised according to its main functional use from the utility's perspective. Values are: "customer_premise", "building", "external", "store", "grid_supply_point", "substation", "depot"
    kind = 'customer_premise'

    # Demographics around the site. Values are: "urban", "other", "rural"
    demographic_kind = 'urban'

    # Reference allocated by the governing organisation (such as municipality) to this piece of land that has a formal reference to Surveyor General's records. The governing organisation is specified in associated Organisation. 
    external_record_reference = ''

    status = None

    def get_location_grants(self):
        """ All location grants this land property has.
        """
        return self._location_grants

    def set_location_grants(self, value):
        for x in self._location_grants:
            x._land_property = None
        for y in value:
            y._land_property = self
        self._location_grants = value
            
    location_grants = property(get_location_grants, set_location_grants)
    
    def add_location_grants(self, *location_grants):
        for obj in location_grants:
            obj._land_property = self
            self._location_grants.append(obj)
        
    def remove_location_grants(self, *location_grants):
        for obj in location_grants:
            obj._land_property = None
            self._location_grants.remove(obj)

    asset_containers = []
    
    def add_asset_containers(self, *asset_containers):
        for obj in asset_containers:
	        self._asset_containers.append(obj)
        
    def remove_asset_containers(self, *asset_containers):
        for obj in asset_containers:
	        self._asset_containers.remove(obj)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    erp_organisation_roles = []
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
	        self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
	        self._erp_organisation_roles.remove(obj)

    def get_erp_site_level_datas(self):
        """ 
        """
        return self._erp_site_level_datas

    def set_erp_site_level_datas(self, value):
        for x in self._erp_site_level_datas:
            x._land_property = None
        for y in value:
            y._land_property = self
        self._erp_site_level_datas = value
            
    erp_site_level_datas = property(get_erp_site_level_datas, set_erp_site_level_datas)
    
    def add_erp_site_level_datas(self, *erp_site_level_datas):
        for obj in erp_site_level_datas:
            obj._land_property = self
            self._erp_site_level_datas.append(obj)
        
    def remove_erp_site_level_datas(self, *erp_site_level_datas):
        for obj in erp_site_level_datas:
            obj._land_property = None
            self._erp_site_level_datas.remove(obj)

    right_of_ways = []
    
    def add_right_of_ways(self, *right_of_ways):
        for obj in right_of_ways:
	        self._right_of_ways.append(obj)
        
    def remove_right_of_ways(self, *right_of_ways):
        for obj in right_of_ways:
	        self._right_of_ways.remove(obj)

    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x._land_property = None
        for y in value:
            y._land_property = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._land_property = self
            self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._land_property = None
            self._erp_person_roles.remove(obj)

    # <<< land_property
    # @generated
    def __init__(self, kind='customer_premise', demographic_kind='urban', external_record_reference='', status=None, location_grants=[], asset_containers=[], locations=[], erp_organisation_roles=[], erp_site_level_datas=[], right_of_ways=[], erp_person_roles=[], **kw_args):
        """ Initialises a new 'LandProperty' instance.
        """
        self.kind = kind
        self.demographic_kind = demographic_kind
        self.external_record_reference = external_record_reference
        self.status = status
        self._location_grants = []
        self.location_grants = location_grants
        self._asset_containers = []
        self.asset_containers = asset_containers
        self._locations = []
        self.locations = locations
        self._erp_organisation_roles = []
        self.erp_organisation_roles = erp_organisation_roles
        self._erp_site_level_datas = []
        self.erp_site_level_datas = erp_site_level_datas
        self._right_of_ways = []
        self.right_of_ways = right_of_ways
        self._erp_person_roles = []
        self.erp_person_roles = erp_person_roles

        super(LandProperty, self).__init__(**kw_args)
    # >>> land_property


class DocLocRole(Role):
    """ Roles played between Documents and Locations. For example, as ErpAddress is a type of Location and WorkBilling is a type of Document, a role is the address for which to mail the invoice. As a TroubleTicket is a type of Document, one instance of Location may be the address for which the trouble is reported.
    """
    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.document_roles if x != self]
            self._location._document_roles = filtered
            
        self._location = value
        if self._location is not None:
            self._location._document_roles.append(self)

    location = property(get_location, set_location)

    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.location_roles if x != self]
            self._document._location_roles = filtered
            
        self._document = value
        if self._document is not None:
            self._document._location_roles.append(self)

    document = property(get_document, set_document)

    # <<< doc_loc_role
    # @generated
    def __init__(self, location=None, document=None, **kw_args):
        """ Initialises a new 'DocLocRole' instance.
        """
        self._location = None
        self.location = location
        self._document = None
        self.document = document

        super(DocLocRole, self).__init__(**kw_args)
    # >>> doc_loc_role


class LocLocRole(Role):
    """ The relationship between one Location and another Location. One 'roleType' is 'Directions,' for which an additional attribute serves for providing a textual description for navigating from one location to another location.
    """
    # Detailed directional information. 
    direction_info = ''

    def get_from_location(self):
        """ 
        """
        return self._from_location

    def set_from_location(self, value):
        if self._from_location is not None:
            filtered = [x for x in self.from_location.to_location_roles if x != self]
            self._from_location._to_location_roles = filtered
            
        self._from_location = value
        if self._from_location is not None:
            self._from_location._to_location_roles.append(self)

    from_location = property(get_from_location, set_from_location)

    def get_to_location(self):
        """ 
        """
        return self._to_location

    def set_to_location(self, value):
        if self._to_location is not None:
            filtered = [x for x in self.to_location.from_location_roles if x != self]
            self._to_location._from_location_roles = filtered
            
        self._to_location = value
        if self._to_location is not None:
            self._to_location._from_location_roles.append(self)

    to_location = property(get_to_location, set_to_location)

    # <<< loc_loc_role
    # @generated
    def __init__(self, direction_info='', from_location=None, to_location=None, **kw_args):
        """ Initialises a new 'LocLocRole' instance.
        """
        self.direction_info = direction_info
        self._from_location = None
        self.from_location = from_location
        self._to_location = None
        self.to_location = to_location

        super(LocLocRole, self).__init__(**kw_args)
    # >>> loc_loc_role


# <<< inf_locations
# @generated
# >>> inf_locations

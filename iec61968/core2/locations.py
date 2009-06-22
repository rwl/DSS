# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from  import 
from iec61968.core2.toplevel import Agreement
from  import 
from iec61968.core2.identifiedobjectinheritance import Role
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class ElectronicAddress():
    """  Electronic location information.
    """
    #  Address on local area network. 
    lan = ''

    #  Email address. 
    email = ''

    #  World Wide Web address. 
    web = ''

    #  Radio address. 
    radio = ''

    # The user ID needed to log in, which can be for an individual person (associated with ErpPerson), an Organisation, a Location, etc. 
    user_id = ''

    # The password needed to log in. 
    password = ''

    locations = []

    asset = None

    erp_person = None

    organisation = None

    document = None

    # <<< electronic_address
    # @generated
    def __init__(self, lan='', email='', web='', radio='', user_id='', password='', locations=[], asset=None, erp_person=None, organisation=None, document=None, **kw_args):
        """ Initialises a new 'ElectronicAddress' instance.
        """
        self.lan = lan
        self.email = email
        self.web = web
        self.radio = radio
        self.user_id = user_id
        self.password = password
        self.locations = locations
        self.asset = asset
        self.erp_person = erp_person
        self.organisation = organisation
        self.document = document

        super(ElectronicAddress, self).__init__(**kw_args)
    # >>> electronic_address


class Location():
    """ The place, scene, or point of something  where someone or something has been, is, and/or will be at a given moment in time.  It may be the spatial location of an actual or planned structure or set of point-oriented structures (as a substation, structure, building, town, etc.), which may be defined as a point or polygon.  It may also be the path of a underground or overhead conductor. 
    """
    # The type of location: geographical, functional accounting, other. 
    location_type = ''

    # A codified representation of the location. 
    location_code = ''

    # Flag is true if the first and last point in the a sequence of coordinate pairs are to be connected, thus forming a polygon rather than merely a sequence of line segments. 
    polygon_flag = ''

    # In general, this field is used to help field crews quickly find a given asset.  For a given location, such as a street address, this is the relative direction in wich to find this asset.  For example, a Streetlight may be located at the 'NW' (northwest) corner of the customer's site.  Or a ServiceDeliveryPoint may be located on the second floor of an appartment building.   
    direction = ''

    activity_records = []

    power_system_resources = []

    assets = []

    routes = []

    dimensions = None

    property = []

    red_lines = []

    erp_addresses = []

    electronic_addresses = []

    erp_persons = []

    hazards = []

    documents = []

    organisations = []

    change_items = []

    crews = []

    gml_observatins = []

    measurements = []

    gml_positions = []

    gml_selectors = []

    # <<< location
    # @generated
    def __init__(self, location_type='', location_code='', polygon_flag='', direction='', activity_records=[], power_system_resources=[], assets=[], routes=[], dimensions=None, property=[], red_lines=[], erp_addresses=[], electronic_addresses=[], erp_persons=[], hazards=[], documents=[], organisations=[], change_items=[], crews=[], gml_observatins=[], measurements=[], gml_positions=[], gml_selectors=[], **kw_args):
        """ Initialises a new 'Location' instance.
        """
        self.location_type = location_type
        self.location_code = location_code
        self.polygon_flag = polygon_flag
        self.direction = direction
        self.activity_records = activity_records
        self.power_system_resources = power_system_resources
        self.assets = assets
        self.routes = routes
        self.dimensions = dimensions
        self.property = property
        self.red_lines = red_lines
        self.erp_addresses = erp_addresses
        self.electronic_addresses = electronic_addresses
        self.erp_persons = erp_persons
        self.hazards = hazards
        self.documents = documents
        self.organisations = organisations
        self.change_items = change_items
        self.crews = crews
        self.gml_observatins = gml_observatins
        self.measurements = measurements
        self.gml_positions = gml_positions
        self.gml_selectors = gml_selectors

        super(Location, self).__init__(**kw_args)
    # >>> location


class LocationGrant(Agreement):
    """  A grant provides a right, as defined by type, for a parcel of land.  Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    #  Type of grant. 
    grant_type = ''

    #  Date that grant became official. 
    grant_date = ''

    #  Property related information that describes the Grant's land parcel.  For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    property = None

    # <<< location_grant
    # @generated
    def __init__(self, grant_type='', grant_date='', property_data='', property=None, **kw_args):
        """ Initialises a new 'LocationGrant' instance.
        """
        self.grant_type = grant_type
        self.grant_date = grant_date
        self.property_data = property_data
        self.property = property

        super(LocationGrant, self).__init__(**kw_args)
    # >>> location_grant


class RightOfWay(Agreement):
    """  A right-of-way (ROW) is for land where it is lawful to use for a public road, an electric power line, etc. Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    #  Type of ROW. 
    right_of_way_type = ''

    #  Property related information that describes the ROW's land parcel.  For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    properties = []

    # <<< right_of_way
    # @generated
    def __init__(self, right_of_way_type='', property_data='', properties=[], **kw_args):
        """ Initialises a new 'RightOfWay' instance.
        """
        self.right_of_way_type = right_of_way_type
        self.property_data = property_data
        self.properties = properties

        super(RightOfWay, self).__init__(**kw_args)
    # >>> right_of_way


class Property():
    """  Information about a particular piece of property such as its use.  Ownership of the property may be determined through associations to Organisations and/or ErpPersons. 
    """
    # Demographics around the site, for example, rural or urban. 
    demographic = ''

    # Examples of the propertyType attribute include: Building: A site enclosed within a building. CustomerPremise: A site with a customer. Depot: A storehouse for supplies that also serves as a station for supporting crews. Store: A place of storage (e.g., a warehouse) to put aside, or accumulate, material and equipment for use when needed.  Substation: A transmission network switchyard. GridSupplyPoint (GSP): a substation where the distribution and transmission networks meet and hence have mixed ownership and mixed operational control. External: Property owned or used by an external party that is not a customer. 
    property_type = ''

    asset_containers = []

    locations = []

    erp_site_level_data = []

    organisations = []

    erp_persons = []

    right_of_ways = []

    location_grants = []

    # <<< property
    # @generated
    def __init__(self, demographic='', property_type='', asset_containers=[], locations=[], erp_site_level_data=[], organisations=[], erp_persons=[], right_of_ways=[], location_grants=[], **kw_args):
        """ Initialises a new 'Property' instance.
        """
        self.demographic = demographic
        self.property_type = property_type
        self.asset_containers = asset_containers
        self.locations = locations
        self.erp_site_level_data = erp_site_level_data
        self.organisations = organisations
        self.erp_persons = erp_persons
        self.right_of_ways = right_of_ways
        self.location_grants = location_grants

        super(Property, self).__init__(**kw_args)
    # >>> property


class LocLocRole(Role):
    """ The relationship between one Location and another Location.  One 'roleType' is 'Directions,' for which an additional attribute is provided  for providing a textual description for navigating from one location to another location.
    """
    # Detailed directional information. 
    directions = ''

    # <<< loc_loc_role
    # @generated
    def __init__(self, directions='', **kw_args):
        """ Initialises a new 'LocLocRole' instance.
        """
        self.directions = directions

        super(LocLocRole, self).__init__(**kw_args)
    # >>> loc_loc_role


class Route():
    """ A route that is followed, for example by a service crews and meter readers. 
    """
    locations = []

    crew = []

    # <<< route
    # @generated
    def __init__(self, locations=[], crew=[], **kw_args):
        """ Initialises a new 'Route' instance.
        """
        self.locations = locations
        self.crew = crew

        super(Route, self).__init__(**kw_args)
    # >>> route


class RedLine():
    """ This class is used for handling the accompanying annotations, time stamp, author, etc. of designs, drawings and maps.  A red line can be associated with any Location object.
    """
    locations = []

    # <<< red_line
    # @generated
    def __init__(self, locations=[], **kw_args):
        """ Initialises a new 'RedLine' instance.
        """
        self.locations = locations

        super(RedLine, self).__init__(**kw_args)
    # >>> red_line


class AssetLocRole(Role):
    """ Roles played between Assets and Locations.
    """
    pass
    # <<< asset_loc_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'AssetLocRole' instance.
        """

        super(AssetLocRole, self).__init__(**kw_args)
    # >>> asset_loc_role


class PsrLocRole(Role):
    """ Roles played between Power System Resources and Locations.
    """
    pass
    # <<< psr_loc_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'PsrLocRole' instance.
        """

        super(PsrLocRole, self).__init__(**kw_args)
    # >>> psr_loc_role


class ErpPersonLocationRole(Role):
    """ Roles played between People and Locations.  Some Locations are somewhat static, like the person's home address.  Other locations may be dynamic, for example when the person is part of a crew driving around in truck.
    """
    pass
    # <<< erp_person_location_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ErpPersonLocationRole' instance.
        """

        super(ErpPersonLocationRole, self).__init__(**kw_args)
    # >>> erp_person_location_role


class PersonPropertyRole(Role):
    """ The role of a person relative to a given piece of property.  Examples of roles include: owner, renter, contractor, etc.
    """
    pass
    # <<< person_property_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'PersonPropertyRole' instance.
        """

        super(PersonPropertyRole, self).__init__(**kw_args)
    # >>> person_property_role


class OrgPropertyRole(Role):
    """ Roles played between Organisations and a given piece of property.  For example, the Organisation may be the owner, renter, occupier, taxiing authority, etc.
    """
    pass
    # <<< org_property_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'OrgPropertyRole' instance.
        """

        super(OrgPropertyRole, self).__init__(**kw_args)
    # >>> org_property_role


class Hazard():
    """ A hazard is any object or condition that is a danger for causing loss or perils to an asset and/or people.  Examples of hazards are trees growing under overhead power lines, a park being located by a substation (i.e., children climb fence to recover a ball), a lake near an overhead distribution line (fishing pole/line contacting power lines), etc.  
    """
    assets = []

    locations = []

    # <<< hazard
    # @generated
    def __init__(self, assets=[], locations=[], **kw_args):
        """ Initialises a new 'Hazard' instance.
        """
        self.assets = assets
        self.locations = locations

        super(Hazard, self).__init__(**kw_args)
    # >>> hazard


class LocationsVersion(object):
 
    version = ''

 
    date = ''

    # <<< locations_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'LocationsVersion' instance.
        """
        self.version = version
        self.date = date

        super(LocationsVersion, self).__init__(**kw_args)
    # >>> locations_version


class ServiceLocation(Location):
    """ Service location is the customer premise where one or more service delivery points are provided.  The location may be a pont of a polygon depending on the specific circumstances. 
    """
    #  Service location code. 
    service_loc_code = ''

    #  Service status. 
    service_loc_status = ''

    # Time and date of service location status.  
    service_loc_status_date = ''

    # Remarks regarding the current status. 
    service_loc_status_remarks = ''

    # The method for the service person to access the appropriate service locations.  For example, a description of where to obtain a key if the facility is unmanned and secured. 
    access_method = ''

    # Problems previously encountered when visiting or performing work on this site.  Examples include: bad dog, viloent customer, verbally abusive occupant, obstructions, safety hazards, etc. 
    site_access_problems = ''

    # True if an inspection is needed of facilities at this service location.  This could be requested by a customer, due to suspected tampering, environmental concerns (e.g., a fire in the vicinity), or to correct incompatible data. 
    inspect_req = ''

    meter_assets = []

    customer_areements = []

    sdplocations = []

    # <<< service_location
    # @generated
    def __init__(self, service_loc_code='', service_loc_status='', service_loc_status_date='', service_loc_status_remarks='', access_method='', site_access_problems='', inspect_req='', meter_assets=[], customer_areements=[], sdplocations=[], **kw_args):
        """ Initialises a new 'ServiceLocation' instance.
        """
        self.service_loc_code = service_loc_code
        self.service_loc_status = service_loc_status
        self.service_loc_status_date = service_loc_status_date
        self.service_loc_status_remarks = service_loc_status_remarks
        self.access_method = access_method
        self.site_access_problems = site_access_problems
        self.inspect_req = inspect_req
        self.meter_assets = meter_assets
        self.customer_areements = customer_areements
        self.sdplocations = sdplocations

        super(ServiceLocation, self).__init__(**kw_args)
    # >>> service_location


class Zone(Location):
    """ An area that is divided off from other areas.  It may be part of the electrical network, a land area where special restrictions apply, weather areas, etc.  For weather, it is an area where a set of relatively homogenous weather measurements apply.
    """
    # Type of zone: electrical network, special restriction on land area, weather zone, other. 
    zone_type = ''

    # <<< zone
    # @generated
    def __init__(self, zone_type='', **kw_args):
        """ Initialises a new 'Zone' instance.
        """
        self.zone_type = zone_type

        super(Zone, self).__init__(**kw_args)
    # >>> zone


class SDPLocation(Location):
    """ The location of an individual service delivery point.  For residential or most businesses, it is typically the location of a meter on the utility customer's premises.  There may be multiple service delivery points at one service location.  For transmission, it is the point(s) of interconnection on the transmission provider's transmission system where capacity and/or energy transmitted by the transmission provider is made available to the receiving party. The point(s) of delivery is specified in the Service Agreement.
    """
    #  Service delivery point location code. 
    s_dpcode = ''

    #  Service delivery point location status as of sDPLocStatusDate. 
    s_dploc_status = ''

    # Time and date of service delivery point location status.  
    s_dploc_status_date = ''

    # Remarks regarding the current status. 
    s_dploc_status_remarks = ''

    #  Date certificate of occupancy was provided for this location.   Value is zero if a valid certificate of occupancy does not exists for this location Service location code. 
    occupancy_date = ''

    # The method for the service person to access this service delivery point location.  For example, a description of where to obtain a key if the facility is unmanned and secured. 
    access_method = ''

    # Problems previously encountered when visiting or performing work at this service delivery point.  Examples include: bad dog, viloent customer, verbally abusive occupant, obstructions, safety hazards, etc. 
    site_access_problems = ''

    service_delivery_points = []

    service_locations = None

    # <<< sdplocation
    # @generated
    def __init__(self, s_dpcode='', s_dploc_status='', s_dploc_status_date='', s_dploc_status_remarks='', occupancy_date='', access_method='', site_access_problems='', service_delivery_points=[], service_locations=None, **kw_args):
        """ Initialises a new 'SDPLocation' instance.
        """
        self.s_dpcode = s_dpcode
        self.s_dploc_status = s_dploc_status
        self.s_dploc_status_date = s_dploc_status_date
        self.s_dploc_status_remarks = s_dploc_status_remarks
        self.occupancy_date = occupancy_date
        self.access_method = access_method
        self.site_access_problems = site_access_problems
        self.service_delivery_points = service_delivery_points
        self.service_locations = service_locations

        super(SDPLocation, self).__init__(**kw_args)
    # >>> sdplocation


# <<< locations
# @generated
# >>> locations

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
    power_system_resource = None

    location = None

    # <<< psr_loc_role
    # @generated
    def __init__(self, power_system_resource=None, location=None, **kw_args):
        """ Initialises a new 'PsrLocRole' instance.
        """
        self.power_system_resource = power_system_resource
        self.location = location

        super(PsrLocRole, self).__init__(**kw_args)
    # >>> psr_loc_role


class OrgPropertyRole(Role):
    """ Roles played between Organisations and a given piece of property. For example, the Organisation may be the owner, renter, occupier, taxiing authority, etc.
    """
    land_property = []

    erp_organisation = None

    # <<< org_property_role
    # @generated
    def __init__(self, land_property=[], erp_organisation=None, **kw_args):
        """ Initialises a new 'OrgPropertyRole' instance.
        """
        self.land_property = land_property
        self.erp_organisation = erp_organisation

        super(OrgPropertyRole, self).__init__(**kw_args)
    # >>> org_property_role


class LocationGrant(Agreement):
    """ A grant provides a right, as defined by type, for a parcel of land. Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    # Property related information that describes the Grant's land parcel. For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    # Land property this location grant applies to.
    land_property = None

    # <<< location_grant
    # @generated
    def __init__(self, property_data='', land_property=None, **kw_args):
        """ Initialises a new 'LocationGrant' instance.
        """
        self.property_data = property_data
        self.land_property = land_property

        super(LocationGrant, self).__init__(**kw_args)
    # >>> location_grant


class RightOfWay(Agreement):
    """ A right-of-way (ROW) is for land where it is lawful to use for a public road, an electric power line, etc. Note that the association to Location, Asset, Organisation, etc. for the Grant is inherited from Agreement, a type of Document.
    """
    # Property related information that describes the ROW's land parcel. For example, it may be a deed book number, deed book page number, and parcel number. 
    property_data = ''

    # All land properties this right of way applies to.
    land_properties = []

    # <<< right_of_way
    # @generated
    def __init__(self, property_data='', land_properties=[], **kw_args):
        """ Initialises a new 'RightOfWay' instance.
        """
        self.property_data = property_data
        self.land_properties = land_properties

        super(RightOfWay, self).__init__(**kw_args)
    # >>> right_of_way


class OrgLocRole(Role):
    """ Roles played between Organisations and Locations, for example a service territory or school district. Note that roles dealing with use of a specific piece of property should be defined based on the relationship between OccupationsOfProperty and Location.
    """
    location = None

    erp_organisation = None

    # <<< org_loc_role
    # @generated
    def __init__(self, location=None, erp_organisation=None, **kw_args):
        """ Initialises a new 'OrgLocRole' instance.
        """
        self.location = location
        self.erp_organisation = erp_organisation

        super(OrgLocRole, self).__init__(**kw_args)
    # >>> org_loc_role


class RedLine(IdentifiedObject):
    """ This class is used for handling the accompanying annotations, time stamp, author, etc. of designs, drawings and maps. A red line can be associated with any Location object.
    """
    status = None

    locations = []

    # <<< red_line
    # @generated
    def __init__(self, status=None, locations=[], **kw_args):
        """ Initialises a new 'RedLine' instance.
        """
        self.status = status
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

    # The point or polygon location of a given hazard.
    locations = []

    # <<< hazard
    # @generated
    def __init__(self, category='', status=None, assets=[], locations=[], **kw_args):
        """ Initialises a new 'Hazard' instance.
        """
        self.category = category
        self.status = status
        self.assets = assets
        self.locations = locations

        super(Hazard, self).__init__(**kw_args)
    # >>> hazard


class PersonPropertyRole(Role):
    """ The role of a person relative to a given piece of property. Examples of roles include: owner, renter, contractor, etc.
    """
    erp_person = None

    land_property = None

    # <<< person_property_role
    # @generated
    def __init__(self, erp_person=None, land_property=None, **kw_args):
        """ Initialises a new 'PersonPropertyRole' instance.
        """
        self.erp_person = erp_person
        self.land_property = land_property

        super(PersonPropertyRole, self).__init__(**kw_args)
    # >>> person_property_role


class ErpPersonLocRole(Role):
    """ Roles played between People and Locations. Some Locations are somewhat static, like the person's home address. Other may be dynamic, for example when the person is part of a crew driving around in truck.
    """
    erp_person = None

    location = None

    # <<< erp_person_loc_role
    # @generated
    def __init__(self, erp_person=None, location=None, **kw_args):
        """ Initialises a new 'ErpPersonLocRole' instance.
        """
        self.erp_person = erp_person
        self.location = location

        super(ErpPersonLocRole, self).__init__(**kw_args)
    # >>> erp_person_loc_role


class Route(IdentifiedObject):
    """ Route that is followed, for example by service crews.
    """
    # Category by utility's work management standards and practices. 
    category = ''

    status = None

    crews = []

    locations = []

    # <<< route
    # @generated
    def __init__(self, category='', status=None, crews=[], locations=[], **kw_args):
        """ Initialises a new 'Route' instance.
        """
        self.category = category
        self.status = status
        self.crews = crews
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
    location = None

    asset = None

    # <<< asset_loc_role
    # @generated
    def __init__(self, location=None, asset=None, **kw_args):
        """ Initialises a new 'AssetLocRole' instance.
        """
        self.location = location
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

    # All location grants this land property has.
    location_grants = []

    asset_containers = []

    # The spatail description of a piece of property.
    locations = []

    erp_organisation_roles = []

    erp_site_level_datas = []

    # All rights of way this land property has.
    right_of_ways = []

    erp_person_roles = []

    # <<< land_property
    # @generated
    def __init__(self, kind='customer_premise', demographic_kind='urban', external_record_reference='', status=None, location_grants=[], asset_containers=[], locations=[], erp_organisation_roles=[], erp_site_level_datas=[], right_of_ways=[], erp_person_roles=[], **kw_args):
        """ Initialises a new 'LandProperty' instance.
        """
        self.kind = kind
        self.demographic_kind = demographic_kind
        self.external_record_reference = external_record_reference
        self.status = status
        self.location_grants = location_grants
        self.asset_containers = asset_containers
        self.locations = locations
        self.erp_organisation_roles = erp_organisation_roles
        self.erp_site_level_datas = erp_site_level_datas
        self.right_of_ways = right_of_ways
        self.erp_person_roles = erp_person_roles

        super(LandProperty, self).__init__(**kw_args)
    # >>> land_property


class DocLocRole(Role):
    """ Roles played between Documents and Locations. For example, as ErpAddress is a type of Location and WorkBilling is a type of Document, a role is the address for which to mail the invoice. As a TroubleTicket is a type of Document, one instance of Location may be the address for which the trouble is reported.
    """
    location = None

    document = None

    # <<< doc_loc_role
    # @generated
    def __init__(self, location=None, document=None, **kw_args):
        """ Initialises a new 'DocLocRole' instance.
        """
        self.location = location
        self.document = document

        super(DocLocRole, self).__init__(**kw_args)
    # >>> doc_loc_role


class LocLocRole(Role):
    """ The relationship between one Location and another Location. One 'roleType' is 'Directions,' for which an additional attribute serves for providing a textual description for navigating from one location to another location.
    """
    # Detailed directional information. 
    direction_info = ''

    from_location = None

    to_location = None

    # <<< loc_loc_role
    # @generated
    def __init__(self, direction_info='', from_location=None, to_location=None, **kw_args):
        """ Initialises a new 'LocLocRole' instance.
        """
        self.direction_info = direction_info
        self.from_location = from_location
        self.to_location = to_location

        super(LocLocRole, self).__init__(**kw_args)
    # >>> loc_loc_role


# <<< inf_locations
# @generated
# >>> inf_locations

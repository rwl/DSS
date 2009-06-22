# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Asset basics package; it is the top level of asset hierarchy.
"""

from  import 
from  import 
from iec61968.documentation.documentinheritance import Document
from iec61968.core2.identifiedobjectinheritance import Role
from  import 
from  import 
from  import 
from iec61968.core2.activityrecords import ActivityRecord
from iec61968.core2.collections import Collection
from  import 
from  import 
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class Asset():
    """  A tangible resource of the utility, including power system equipment, cabinets, buildings, etc.  For electrical network equipment, the role of the asset is defined in the Wires Model (refer to IEC 61970-301).  This role is associated with an asset description, which places emphasis on the  physical characteristics of the equipment fulfilling that role.  This is the parent class for asset sub classes.
    """
    #  Code for this type of asset. 
    code = ''

    #  Uniquely Tracked Commodity (UTC) number. 
    utc = ''

    #  Serial number of asset. 
    serial_number = ''

    #  General classification for this for this type of asset.  Note that specializations of TypeAsset have a type that is intended to be unique to that type of asset, which can be enumerated for that type of asset in accordance with a utility's standards. 
    asset_type = ''

    #  Data asset was manufactured. 
    manufactured_date = ''

    #  True if asset is considered critical for some reason.  For example, a pole with critical attachments. 
    critical = ''

    #  Condition of asset in inventory or at time of installation.  Examples include new, rebuilt, overhaul required, other.  Refer to inspection data for information on the most current condition of the asset. 
    initial_condition = ''

    #  Asset usage.  Examples include: Distribution Overhead, Distribution Underground, Transmission, Substation, Streetlight, Customer Substation, Unknown, Other. 
    asset_usage = ''

    #  Purchase price (amount, units) of asset. 
    purchase_price = ''

    # True  if asset has passed acceptance test and may be placed in or is in service.  The type of tests or group of tests is identified in'testType'.  It is returned to a false state if asset is removed from service and is required to be tested again before being placed back in service, possibly in a new location. The date of each acceptance test (device may go through multiple test during its life cycle) is determined in AssetStatus. 
    test_status = ''

    # The date and time this asset was last tested using the 'testType' and yiedling the currnet status in 'testStatus'. 
    test_date = ''

    # The type of test or group of tests that was conducted on 'testDate'. 
    test_type = ''

    # Date status of association was entered. 
    status_date = ''

    # Status at the time of 'statusDate.'  Asset is new construction (installed, ready to be placed in operation), in-service, decommissioned (out-of-service), removed, existing, proposed abandoned, proposed install, proposed remove, proposed replace, unknown, other.  For history of status changes, refer to assoicated activity records. 
    status = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Reason code or explanation for why this asset went to the current status. 
    status_reason = ''

    # Even for the same model and version number, many assets are manufactured in lots.  the lotNumber identifies the specific lot for this asset. 
    lot_number = ''

    # Date current installation was completed, which may not be the same as the in-service date.  Asset may have been installed at other locations previously.  Value is null if asset is (1) not currently installed (e.g., stored in a depot) or (2) not intended to be installed (e.g., vehicle, tool). 
    installation_date = ''

    # 'As built' phase or phases that the asset is associatied with.  'N/A' is the value when not applicable. 'N/S is the value for a phase oriented device that is not in service (not connected to a phase or phases).  Possible values { ABCN , ABC, ABN, ACN, BCN, AB, AC, BC, AN, BN, CN, A, B, C, N, N/A, N/S }. 
    phases = ''

    # The way this particular asset is being used in this installation.  For example, the application of a bushing when attached to a specific transformer winding would be one of the following: H1, H2, H3, H0, X1, X2, X3, X0, Y1, Y2, Y3, Y0. 
    application = ''

    # Zero for new devices.  Whenver an asset is reconditioned, this is the percentage off expected life for the asset when it was new. 
    initial_loss_of_life = ''

    power_system_resources = []

    organisations = []

    asset_groupings = []

    to_assets = []

    from_assets = []

    documents = []

    asset_lists = []

    ratings = []

    asset_properties = []

    erp_inventory = None

    mediums = []

    financial_properties = None

    asset_property_curves = []

    reliability_properties = []

    measurements = []

    scheduled_events = []

    readings = []

    asset_functions = []

    dimensions = None

    asset_container = None

    activity_records = []

    locations = []

    electronic_addresses = []

    hazards = []

    change_items = []

    erp_item_master = None

    erp_rec_delivery_items = []

    # <<< asset
    # @generated
    def __init__(self, code='', utc='', serial_number='', asset_type='', manufactured_date='', critical='', initial_condition='', asset_usage='', purchase_price='', test_status='', test_date='', test_type='', status_date='', status='', status_remarks='', status_reason='', lot_number='', installation_date='', phases='', application='', initial_loss_of_life='', power_system_resources=[], organisations=[], asset_groupings=[], to_assets=[], from_assets=[], documents=[], asset_lists=[], ratings=[], asset_properties=[], erp_inventory=None, mediums=[], financial_properties=None, asset_property_curves=[], reliability_properties=[], measurements=[], scheduled_events=[], readings=[], asset_functions=[], dimensions=None, asset_container=None, activity_records=[], locations=[], electronic_addresses=[], hazards=[], change_items=[], erp_item_master=None, erp_rec_delivery_items=[], **kw_args):
        """ Initialises a new 'Asset' instance.
        """
        self.code = code
        self.utc = utc
        self.serial_number = serial_number
        self.asset_type = asset_type
        self.manufactured_date = manufactured_date
        self.critical = critical
        self.initial_condition = initial_condition
        self.asset_usage = asset_usage
        self.purchase_price = purchase_price
        self.test_status = test_status
        self.test_date = test_date
        self.test_type = test_type
        self.status_date = status_date
        self.status = status
        self.status_remarks = status_remarks
        self.status_reason = status_reason
        self.lot_number = lot_number
        self.installation_date = installation_date
        self.phases = phases
        self.application = application
        self.initial_loss_of_life = initial_loss_of_life
        self.power_system_resources = power_system_resources
        self.organisations = organisations
        self.asset_groupings = asset_groupings
        self.to_assets = to_assets
        self.from_assets = from_assets
        self.documents = documents
        self.asset_lists = asset_lists
        self.ratings = ratings
        self.asset_properties = asset_properties
        self.erp_inventory = erp_inventory
        self.mediums = mediums
        self.financial_properties = financial_properties
        self.asset_property_curves = asset_property_curves
        self.reliability_properties = reliability_properties
        self.measurements = measurements
        self.scheduled_events = scheduled_events
        self.readings = readings
        self.asset_functions = asset_functions
        self.dimensions = dimensions
        self.asset_container = asset_container
        self.activity_records = activity_records
        self.locations = locations
        self.electronic_addresses = electronic_addresses
        self.hazards = hazards
        self.change_items = change_items
        self.erp_item_master = erp_item_master
        self.erp_rec_delivery_items = erp_rec_delivery_items

        super(Asset, self).__init__(**kw_args)
    # >>> asset


class AssetProperty():
    """ An AssetProperty is a name-value pair concept that provides the means for utilities to describe various myriad properties for a given asset, asset type (AssetModel), or other object that is not included in the standard model.
    """
    #  Type of property. 
    property_type = ''

    #  Value holds the actual value of the property for the object. 
    property_value = ''

    #  Units property is expressed in. 
    units = ''

    # Value multiplier. 
    multiplier = ''

    specification = None

    assets = []

    data_sets = []

    # <<< asset_property
    # @generated
    def __init__(self, property_type='', property_value='', units='', multiplier='', specification=None, assets=[], data_sets=[], **kw_args):
        """ Initialises a new 'AssetProperty' instance.
        """
        self.property_type = property_type
        self.property_value = property_value
        self.units = units
        self.multiplier = multiplier
        self.specification = specification
        self.assets = assets
        self.data_sets = data_sets

        super(AssetProperty, self).__init__(**kw_args)
    # >>> asset_property


class AssetModel(Document):
    """ Documentation for a type of an asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    #  Manufacturer's model number. 
    model_number = ''

    #  Version number for product model, which indicates vintage of the product. 
    model_version = ''

    #  Indicates if this type of asset is an approved coporate standard or otherwise: standard, experimental, under evaluation, other. 
    corp_standard = ''

    # The intended usage for this type of asset.  Examples include: Distribution Overhead, Distribution Underground, Transmission, Substation, Streetlight, Customer Substation, Unknown, Other. 
    usage = ''

    # Total manufactured weight of asset. 
    weight_total = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_inventory_counts = []

    asset_catalogue_items = []

    type_asset = None

    # <<< asset_model
    # @generated
    def __init__(self, model_number='', model_version='', corp_standard='', usage='', weight_total='', status='', status_date_time='', status_remarks='', erp_inventory_counts=[], asset_catalogue_items=[], type_asset=None, **kw_args):
        """ Initialises a new 'AssetModel' instance.
        """
        self.model_number = model_number
        self.model_version = model_version
        self.corp_standard = corp_standard
        self.usage = usage
        self.weight_total = weight_total
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_inventory_counts = erp_inventory_counts
        self.asset_catalogue_items = asset_catalogue_items
        self.type_asset = type_asset

        super(AssetModel, self).__init__(**kw_args)
    # >>> asset_model


class OrgAssetRole(Role):
    """ The roles played between an Organisations and an Asset.
    """
    # If the role type is 'owner,' this indicate the percentage of ownership.  
    percent_ownership = ''

    # <<< org_asset_role
    # @generated
    def __init__(self, percent_ownership='', **kw_args):
        """ Initialises a new 'OrgAssetRole' instance.
        """
        self.percent_ownership = percent_ownership

        super(OrgAssetRole, self).__init__(**kw_args)
    # >>> org_asset_role


class Rating():
    """ Rating is a name-value pair concept that provides the means for utilities to describe various myriad ratings for a given asset, asset type (AssetModel), or other object that is not included in the standard model.
    """
    # Type of rating for the object. 
    rating_type = ''

    #  Describes what is the rating means e.g. '3PhaseAmps', 'KVA' etc  (propertyName,description). 
    property = ''

    #  Value holds the actual value of the rating for the associated object and can be used to set the initial value of operational MeasurementLimits. 
    rating_value = ''

    #  Units property is expressed in. 
    units = ''

    # Value multiplier. 
    multiplier = ''

    assets = []

    specification = None

    limit = None

    # <<< rating
    # @generated
    def __init__(self, rating_type='', property='', rating_value='', units='', multiplier='', assets=[], specification=None, limit=None, **kw_args):
        """ Initialises a new 'Rating' instance.
        """
        self.rating_type = rating_type
        self.property = property
        self.rating_value = rating_value
        self.units = units
        self.multiplier = multiplier
        self.assets = assets
        self.specification = specification
        self.limit = limit

        super(Rating, self).__init__(**kw_args)
    # >>> rating


class AssetGrouping():
    """ User defined category for various purposes like maintenance, inspection, etc.   For example, an AssetGrouping is used to specify fixed asset group.
    """
    assets = []

    # <<< asset_grouping
    # @generated
    def __init__(self, assets=[], **kw_args):
        """ Initialises a new 'AssetGrouping' instance.
        """
        self.assets = assets

        super(AssetGrouping, self).__init__(**kw_args)
    # >>> asset_grouping


class AssetListRole(Role):
    """ Roles played between Asset Lists and Assets.
    """
    pass
    # <<< asset_list_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'AssetListRole' instance.
        """

        super(AssetListRole, self).__init__(**kw_args)
    # >>> asset_list_role


class QualificationRequirement():
    """  Certain skills are required and must be certified in order for a person (typically a member of a crew) to be qualified to work on types of equipment.
    """
    #  Work task identifier. 
    qualification_id = ''

    specifications = []

    skills = []

    work_tasks = []

    culabor_items = []

    # <<< qualification_requirement
    # @generated
    def __init__(self, qualification_id='', specifications=[], skills=[], work_tasks=[], culabor_items=[], **kw_args):
        """ Initialises a new 'QualificationRequirement' instance.
        """
        self.qualification_id = qualification_id
        self.specifications = specifications
        self.skills = skills
        self.work_tasks = work_tasks
        self.culabor_items = culabor_items

        super(QualificationRequirement, self).__init__(**kw_args)
    # >>> qualification_requirement


class OrgPsrRole(Role):
    """ Roles played between Organisations and Power System Resources.
    """
    pass
    # <<< org_psr_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'OrgPsrRole' instance.
        """

        super(OrgPsrRole, self).__init__(**kw_args)
    # >>> org_psr_role


class AssetAssetRole(Role):
    """ Roles played between Assets and other Assets.
    """
    pass
    # <<< asset_asset_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'AssetAssetRole' instance.
        """

        super(AssetAssetRole, self).__init__(**kw_args)
    # >>> asset_asset_role


class FailureEvent(ActivityRecord):
    """ An event, a type of ActivityRecord, where an asset has failed to perform its functions within specified parameters.
    """
    # Code for asset failure. 
    failure_code = ''

    # How the asset failure was isolated from the system: Breaker Operation, Fuse, Burned in the clear, Manually Isolated, Other. 
    isolation_method = ''

    # The method used for locating the faulted part of the asset.  For example, cable options include: Cap Discharge-Thumping, Bridge Method, Visual Inspection, Other. 
    fault_locating_method = ''

    # Location of failure on an object. 
    location_of_failure = ''

    # <<< failure_event
    # @generated
    def __init__(self, failure_code='', isolation_method='', fault_locating_method='', location_of_failure='', **kw_args):
        """ Initialises a new 'FailureEvent' instance.
        """
        self.failure_code = failure_code
        self.isolation_method = isolation_method
        self.fault_locating_method = fault_locating_method
        self.location_of_failure = location_of_failure

        super(FailureEvent, self).__init__(**kw_args)
    # >>> failure_event


class DocAssetRole(Role):
    """ Roles played between Documents and Assets.
    """
    pass
    # <<< doc_asset_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocAssetRole' instance.
        """

        super(DocAssetRole, self).__init__(**kw_args)
    # >>> doc_asset_role


class AssetList(Collection):
    """ A type of collection, an Asset List is list of physical assets.  It is similar to an Equipment List, which is a list of equipment.  It may be a user defined category for various purposes like maintenance, inspection, etc.   For example, an AssetListg is used to specify fixed asset group.
    """
    assets = []

    work_task = None

    # <<< asset_list
    # @generated
    def __init__(self, assets=[], work_task=None, **kw_args):
        """ Initialises a new 'AssetList' instance.
        """
        self.assets = assets
        self.work_task = work_task

        super(AssetList, self).__init__(**kw_args)
    # >>> asset_list


class Dimensions():
    """ As applicable, the basic linear, area, or volume dimensions of an asset, asset type (AssetModel) or other type of object (such as land area).  Units and multipliers are specified per dimension.
    """
    #  Length measurement. 
    size_length = ''

    #  Depth measurement. 
    size_depth = ''

    #  Width measurement. 
    size_width = ''

    #  Diameter measurement. 
    size_diameter = ''

    # A description of the orientation of the object relative to the dimensions.  As an example, a vault may have north-south orientation for the sizeLength measurement and sizeDepth may be the height of the vault. 
    orientation = ''

    specifications = []

    assets = []

    locations = []

    # <<< dimensions
    # @generated
    def __init__(self, size_length='', size_depth='', size_width='', size_diameter='', orientation='', specifications=[], assets=[], locations=[], **kw_args):
        """ Initialises a new 'Dimensions' instance.
        """
        self.size_length = size_length
        self.size_depth = size_depth
        self.size_width = size_width
        self.size_diameter = size_diameter
        self.orientation = orientation
        self.specifications = specifications
        self.assets = assets
        self.locations = locations

        super(Dimensions, self).__init__(**kw_args)
    # >>> dimensions


class AssetCatalogue(Collection):
    """ An Asset Catalogue is a collection of information regarding available types of products and materials that are used to build or install an Asset(s), to maintain an Asset(s) or to operate an Asset(s).  Each catagoue item is for a specific product available from a specific supplier. 
    """
    asset_catalogue_items = []

    network_data_sets = []

    # <<< asset_catalogue
    # @generated
    def __init__(self, asset_catalogue_items=[], network_data_sets=[], **kw_args):
        """ Initialises a new 'AssetCatalogue' instance.
        """
        self.asset_catalogue_items = asset_catalogue_items
        self.network_data_sets = network_data_sets

        super(AssetCatalogue, self).__init__(**kw_args)
    # >>> asset_catalogue


class DocPsrRole(Role):
    """ Potential roles that might played by a document relative to a type of PowerSystemResource.
    """
    pass
    # <<< doc_psr_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocPsrRole' instance.
        """

        super(DocPsrRole, self).__init__(**kw_args)
    # >>> doc_psr_role


class Specification(Document):
    """ A specification can be used for various purposes relative to an asset, a logical device (PowerSystemResource), location, etc. Examples include documents supplied by manufacturers such as asset installation instructions, asset maintenance instructions, etc.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of specification.  
    spec_type = ''

    asset_properites = []

    ratings = []

    dimensions = []

    qualification_requirements = []

    mediums = []

    asset_property_curves = []

    reliability_properties = []

    # <<< specification
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', spec_type='', asset_properites=[], ratings=[], dimensions=[], qualification_requirements=[], mediums=[], asset_property_curves=[], reliability_properties=[], **kw_args):
        """ Initialises a new 'Specification' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.spec_type = spec_type
        self.asset_properites = asset_properites
        self.ratings = ratings
        self.dimensions = dimensions
        self.qualification_requirements = qualification_requirements
        self.mediums = mediums
        self.asset_property_curves = asset_property_curves
        self.reliability_properties = reliability_properties

        super(Specification, self).__init__(**kw_args)
    # >>> specification


class Medium():
    """ A substance that either (1) provides the means of transmission of a force or effect, such as hydraulic fluid, or (2) is used for a surrounding or enveloping substance, such as oil in a transformer or circuit breaker.  
    """
    # The type of medium: gas, liquid, solid 
    type_medium = ''

    # The volume of the medium specified for this applicaiton.  Note that the actual volume is a type of measurement associated witht the asset. 
    volume_spec = ''

    specification = None

    assets = []

    # <<< medium
    # @generated
    def __init__(self, type_medium='', volume_spec='', specification=None, assets=[], **kw_args):
        """ Initialises a new 'Medium' instance.
        """
        self.type_medium = type_medium
        self.volume_spec = volume_spec
        self.specification = specification
        self.assets = assets

        super(Medium, self).__init__(**kw_args)
    # >>> medium


class AssetPsrRole(Role):
    """ Roles played between Assets and Power System Resources.
    """
    pass
    # <<< asset_psr_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'AssetPsrRole' instance.
        """

        super(AssetPsrRole, self).__init__(**kw_args)
    # >>> asset_psr_role


class FinancialProperties():
    """ Various current financial properties associated with a particular asset.  Historical properties may be determined by activity records associate with the asset.
    """
    #  Value of asset as of the valueDate. 
    financial_value = ''

    # The date and time at which the financial value was last established. 
    value_date = ''

    #  Date asset's financial value was put in plant for regulatory accounting purposes (e.g., for rate base calculations).  This is sometime referred to as the 'in-service date.' 
    plant_transfer_date = ''

    #  Date warranty on asset expires. 
    warranty_date = ''

    #  Date asset was purchased. 
    purchase_date = ''

    #  Purchase order identifier. 
    purchase_order_number = ''

    # The actual purchase cost of this particular asset. 
    actual_purchase_cost = ''

    # The account to which this actual material item is charged. 
    account = ''

    # Unit of measure for the quantity. 
    unit_of_measure = ''

    # Multiplier for this quantity. 
    multiplier = ''

    # The quantity of the asset if per unit length, for example conductor. 
    quantity = ''

    # Description of the cost. 
    cost_description = ''

    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    asset = None

    # <<< financial_properties
    # @generated
    def __init__(self, financial_value='', value_date='', plant_transfer_date='', warranty_date='', purchase_date='', purchase_order_number='', actual_purchase_cost='', account='', unit_of_measure='', multiplier='', quantity='', cost_description='', cost_type='', asset=None, **kw_args):
        """ Initialises a new 'FinancialProperties' instance.
        """
        self.financial_value = financial_value
        self.value_date = value_date
        self.plant_transfer_date = plant_transfer_date
        self.warranty_date = warranty_date
        self.purchase_date = purchase_date
        self.purchase_order_number = purchase_order_number
        self.actual_purchase_cost = actual_purchase_cost
        self.account = account
        self.unit_of_measure = unit_of_measure
        self.multiplier = multiplier
        self.quantity = quantity
        self.cost_description = cost_description
        self.cost_type = cost_type
        self.asset = asset

        super(FinancialProperties, self).__init__(**kw_args)
    # >>> financial_properties


class AssetPropertyCurve():
    """ An Asset Property that is described through curves rather than as a data point.  The relationship is to be defined between an independent variable (X-axis) and one or two dependent variables (Y1-axis and Y2-axis).
    """
    assets = []

    specification = None

    # <<< asset_property_curve
    # @generated
    def __init__(self, assets=[], specification=None, **kw_args):
        """ Initialises a new 'AssetPropertyCurve' instance.
        """
        self.assets = assets
        self.specification = specification

        super(AssetPropertyCurve, self).__init__(**kw_args)
    # >>> asset_property_curve


class AssetCatalogueItem(Document):
    """ An AssetCatalogueItem provides pricing and other relevant information about a specific manufacturer's product, an AssetModel, and its price from a given supplier.  A single AssetModel may be availble from multiple suppliers.  Note that manufacturer and supplier are both types of organisation, which the association is inherited from Document.
    """
    # The  unit cost for an asset model from a specific supplier..  It is either for a unit cost or cost per unit length.  Cost is for material or asset only and does not include labor to install/construct or configure it. 
    unit_cost = ''

    asset_catalogue = None

    asset_model = None

    erp_poline_items = []

    erp_quote_line_items = []

    # <<< asset_catalogue_item
    # @generated
    def __init__(self, unit_cost='', asset_catalogue=None, asset_model=None, erp_poline_items=[], erp_quote_line_items=[], **kw_args):
        """ Initialises a new 'AssetCatalogueItem' instance.
        """
        self.unit_cost = unit_cost
        self.asset_catalogue = asset_catalogue
        self.asset_model = asset_model
        self.erp_poline_items = erp_poline_items
        self.erp_quote_line_items = erp_quote_line_items

        super(AssetCatalogueItem, self).__init__(**kw_args)
    # >>> asset_catalogue_item


class PSRStatus(object):
    """ The current status of the PowerSystemResource.  History of the status is tracked through instances of ActivityRecord.
    """
    # Status of the PowerSystemResource at the time of 'statusDate.'  This status is regarding the role (vs. the status of the physical asset performing the PowerSystemResource role).  Current status may be: planned, in-service, proposed remove, proposed replace, out-of-service, unknown, other.  For history of status changes, refer to assoicated activity records. 
    current_status = ''

    # Date current status was entered. 
    status_date = ''

    power_system_resource = None

    # <<< psrstatus
    # @generated
    def __init__(self, current_status='', status_date='', power_system_resource=None, **kw_args):
        """ Initialises a new 'PSRStatus' instance.
        """
        self.current_status = current_status
        self.status_date = status_date
        self.power_system_resource = power_system_resource

        super(PSRStatus, self).__init__(**kw_args)
    # >>> psrstatus


class ReliabilityProperties():
    """ Information regarding the experienced and expected reliability of a specific asset, type of asset, or asset model.
    """
    # Momentary failure rate (temporary failures/kft-year). 
    mom_failure_rate = ''

    # Mean time to repair (MTTR - hours). 
    m_ttr = ''

    specification = None

    assets = []

    # <<< reliability_properties
    # @generated
    def __init__(self, mom_failure_rate='', m_ttr='', specification=None, assets=[], **kw_args):
        """ Initialises a new 'ReliabilityProperties' instance.
        """
        self.mom_failure_rate = mom_failure_rate
        self.m_ttr = m_ttr
        self.specification = specification
        self.assets = assets

        super(ReliabilityProperties, self).__init__(**kw_args)
    # >>> reliability_properties


class AssetBasicsVersion(object):
 
    version = ''

 
    date = ''

    # <<< asset_basics_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'AssetBasicsVersion' instance.
        """
        self.version = version
        self.date = date

        super(AssetBasicsVersion, self).__init__(**kw_args)
    # >>> asset_basics_version


class TypeAssetCatalogue(Collection):
    """ A Type Asset Catalogue is a collection of information regarding generic types of assets that may be used for design purposes.  A TypeAsset is not associated with a particular manufacturer.
    """
    type_assets = []

    # <<< type_asset_catalogue
    # @generated
    def __init__(self, type_assets=[], **kw_args):
        """ Initialises a new 'TypeAssetCatalogue' instance.
        """
        self.type_assets = type_assets

        super(TypeAssetCatalogue, self).__init__(**kw_args)
    # >>> type_asset_catalogue


# <<< assetbasics
# @generated
# >>> assetbasics

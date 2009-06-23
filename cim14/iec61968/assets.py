# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Assets"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Assets"

class Seal(IdentifiedObject):
    """ Physically controls access to AssetContainers.
    """
    # Kind of seal. Values are: "steel", "other", "lock", "lead"
    kind = 'steel'

    # Date and time this seal has been applied. 
    applied_date_time = ''

    # (reserved word) Seal number. 
    seal_number = ''

    # Condition of seal. Values are: "locked", "other", "broken", "missing", "open"
    condition = 'locked'

    # Asset container to which this seal is applied.
    asset_container = None

    # <<< seal
    # @generated
    def __init__(self, kind='steel', applied_date_time='', seal_number='', condition='locked', asset_container=None, **kw_args):
        """ Initialises a new 'Seal' instance.
        """
        self.kind = kind
        self.applied_date_time = applied_date_time
        self.seal_number = seal_number
        self.condition = condition
        self.asset_container = asset_container

        super(Seal, self).__init__(**kw_args)
    # >>> seal


class AcceptanceTest(Element):
    """ Acceptance test for assets.
    """
    # Type of test or group of tests that was conducted on 'dateTime'. 
    type = ''

    # Date and time the asset was last tested using the 'type' of test and yiedling the currnet status in 'success' attribute. 
    date_time = ''

    # True if asset has passed acceptance test and may be placed in or is in service. It is set to false if asset is removed from service and is required to be tested again before being placed back in service, possibly in a new location. Since asset may go through multiple tests during its life cycle, the date of each acceptance test may be recorded in Asset.ActivityRecord.status.dateTime. 
    success = False

    # <<< acceptance_test
    # @generated
    def __init__(self, type='', date_time='', success=False, **kw_args):
        """ Initialises a new 'AcceptanceTest' instance.
        """
        self.type = type
        self.date_time = date_time
        self.success = success

        super(AcceptanceTest, self).__init__(**kw_args)
    # >>> acceptance_test


class Asset(IdentifiedObject):
    """ Tangible resource of the utility, including power system equipment, cabinets, buildings, etc. For electrical network equipment, the role of the asset is defined through PowerSystemResource and its subclasses, defined mainly in the Wires model (refer to IEC61970-301 and model package IEC61970::Wires). Asset description places emphasis on the physical characteristics of the equipment fulfilling that role.
    """
    # Purchase price of asset. 
    purchase_price = ''

    # The way this particular asset is being used in this installation. For example, the application of a bushing when attached to a specific transformer winding would be one of the following: H1, H2, H3, H0, X1, X2, X3, X0, Y1, Y2, Y3, Y0. 
    application = ''

    # Whenever an asset is reconditioned, percentage of expected life for the asset when it was new; zero for new devices. 
    initial_loss_of_life = ''

    # Condition of asset in inventory or at time of installation. Examples include new, rebuilt, overhaul required, other. Refer to inspection data for information on the most current condition of the asset. 
    initial_condition = ''

    # Code for this type of asset. 
    corporate_code = ''

    # (if applicable) Date current installation was completed, which may not be the same as the in-service date. Asset may have been installed at other locations previously. Ignored if asset is (1) not currently installed (e.g., stored in a depot) or (2) not intended to be installed (e.g., vehicle, tool). 
    installation_date = ''

    # Date this asset was manufactured. 
    manufactured_date = ''

    # Extension mechanism to accommodate utility-specific categorisation of Asset and its subtypes, according to their corporate standards, practices, and existing IT systems (e.g., for management of assets, maintenance, work, outage, customers, etc.). 
    category = ''

    # Uniquely Tracked Commodity (UTC) number. 
    utc_number = ''

    # True if asset is considered critical for some reason (for example, a pole with critical attachments). 
    critical = False

    # Lot number for this asset. Even for the same model and version number, many assets are manufactured in lots. 
    lot_number = ''

    # Serial number of this asset. 
    serial_number = ''

    # Information on acceptance test.
    acceptance_test = None

    # Status of this asset.
    status = None

    to_asset_roles = []

    erp_rec_delivery_items = []

    asset_functions = []

    # All electronic addresses of this asset.
    electronic_addresses = []

    dimensions_info = None

    erp_item_master = None

    # UserAttributes used to specify further properties of this asset. Use 'name' to specify what kind of property it is, and 'value.value' attribute for the actual value.
    properties = []

    erp_inventory = None

    scheduled_events = []

    change_items = []

    # UserAttributes used to specify ratings of this asset. Ratings also can be used to set the initial value of operational measurement limits. Use 'name' to specify what kind of rating it is (e.g., voltage, current), and 'value' attribute for the actual value and unit information of the rating.
    ratings = []

    asset_container = None

    work_task = None

    document_roles = []

    from_asset_roles = []

    financial_info = None

    asset_property_curves = []

    erp_organisation_roles = []

    measurements = []

    hazards = []

    mediums = []

    reliability_infos = []

    location_roles = []

    power_system_resource_roles = []

    # All activity records created for this asset.
    activity_records = []

    # <<< asset
    # @generated
    def __init__(self, purchase_price='', application='', initial_loss_of_life='', initial_condition='', corporate_code='', installation_date='', manufactured_date='', category='', utc_number='', critical=False, lot_number='', serial_number='', acceptance_test=None, status=None, to_asset_roles=[], erp_rec_delivery_items=[], asset_functions=[], electronic_addresses=[], dimensions_info=None, erp_item_master=None, properties=[], erp_inventory=None, scheduled_events=[], change_items=[], ratings=[], asset_container=None, work_task=None, document_roles=[], from_asset_roles=[], financial_info=None, asset_property_curves=[], erp_organisation_roles=[], measurements=[], hazards=[], mediums=[], reliability_infos=[], location_roles=[], power_system_resource_roles=[], activity_records=[], **kw_args):
        """ Initialises a new 'Asset' instance.
        """
        self.purchase_price = purchase_price
        self.application = application
        self.initial_loss_of_life = initial_loss_of_life
        self.initial_condition = initial_condition
        self.corporate_code = corporate_code
        self.installation_date = installation_date
        self.manufactured_date = manufactured_date
        self.category = category
        self.utc_number = utc_number
        self.critical = critical
        self.lot_number = lot_number
        self.serial_number = serial_number
        self.acceptance_test = acceptance_test
        self.status = status
        self.to_asset_roles = to_asset_roles
        self.erp_rec_delivery_items = erp_rec_delivery_items
        self.asset_functions = asset_functions
        self.electronic_addresses = electronic_addresses
        self.dimensions_info = dimensions_info
        self.erp_item_master = erp_item_master
        self.properties = properties
        self.erp_inventory = erp_inventory
        self.scheduled_events = scheduled_events
        self.change_items = change_items
        self.ratings = ratings
        self.asset_container = asset_container
        self.work_task = work_task
        self.document_roles = document_roles
        self.from_asset_roles = from_asset_roles
        self.financial_info = financial_info
        self.asset_property_curves = asset_property_curves
        self.erp_organisation_roles = erp_organisation_roles
        self.measurements = measurements
        self.hazards = hazards
        self.mediums = mediums
        self.reliability_infos = reliability_infos
        self.location_roles = location_roles
        self.power_system_resource_roles = power_system_resource_roles
        self.activity_records = activity_records

        super(Asset, self).__init__(**kw_args)
    # >>> asset


class ElectricalInfo(IdentifiedObject):
    """ Electrical properties of an asset or of an asset model (product by a manufacturer). Can also be used to define electrical properties for each phase individually. Not every attribute will be required for each type of asset or asset model. For example, a transformer may only have requirments for 'ratedVoltage', 'ratedApparentPower' and 'phaseCount' attributes, while a LinearConductor will have 'r', 'x', 'b' and 'g' requirements per unit length on top of a 'ratedCurrent' and 'ratedVoltage'.
    """
    # Zero sequence conductance. 
    g0 = ''

    # Positive sequence series reactance. 
    x = ''

    # Zero sequence series resistance. 
    r0 = ''

    # For an installed asset, this is the total number of electrical wires that are physically connected to it. For an AssetModel, this is the total number of wires that can potentially be connected to this asset type. This is particularly useful to understand overall electrical configurations for distribution secondary where the number of wires can not be derived from phase information alone. For example, 120v 2 Wires; 240v 2 Wires; 480v 1Ph 2 Wires; 120/240v 1Ph; 120/208v 3Ph Y; 120/208v 1Ph Y; 120/240v 3Ph D; 240/480v 1Ph 3 Wires; 480v 3Ph D; 240/480v 3Ph D; 277/480v 3Ph Y. 
    wire_count = 0

    # Zero sequence series reactance. 
    x0 = ''

    # Rated voltage. 
    rated_voltage = ''

    # Positive sequence conductance. 
    g = ''

    # Zero sequence susceptance. 
    b0 = ''

    # Positive sequence susceptance. 
    b = ''

    # Rated apparent power. 
    rated_apparent_power = ''

    # Positive sequence series resistance. 
    r = ''

    # Number of potential phases the asset supports, typically 0, 1 or 3. The actual phases connected are determined from 'ConductingEquipment.phases' attribute in the ConductingEquipment subclass associated with the asset or from 'ElectricalAsset.phaseCode' attribute. 
    phase_count = 0

    # Basic Insulation Level (BIL) for switchgear, insulators, etc. A reference insulation level expressed as the impulse crest voltage of a nominal wave, typically 1.2 X 50 microsecond. This is a measure of the ability of the insulation to withstand very high voltage surges. 
    bil = ''

    # Rated current. 
    rated_current = ''

    # Frequency at which stated device ratings apply, typically 50Hz or 60Hz. 
    frequency = ''

    # All end device assets having this set of electrical properties.
    end_device_assets = []

    electrical_assets = []

    electrical_asset_model_roles = []

    electrical_type_asset_roles = []

    # <<< electrical_info
    # @generated
    def __init__(self, g0='', x='', r0='', wire_count=0, x0='', rated_voltage='', g='', b0='', b='', rated_apparent_power='', r='', phase_count=0, bil='', rated_current='', frequency='', end_device_assets=[], electrical_assets=[], electrical_asset_model_roles=[], electrical_type_asset_roles=[], **kw_args):
        """ Initialises a new 'ElectricalInfo' instance.
        """
        self.g0 = g0
        self.x = x
        self.r0 = r0
        self.wire_count = wire_count
        self.x0 = x0
        self.rated_voltage = rated_voltage
        self.g = g
        self.b0 = b0
        self.b = b
        self.rated_apparent_power = rated_apparent_power
        self.r = r
        self.phase_count = phase_count
        self.bil = bil
        self.rated_current = rated_current
        self.frequency = frequency
        self.end_device_assets = end_device_assets
        self.electrical_assets = electrical_assets
        self.electrical_asset_model_roles = electrical_asset_model_roles
        self.electrical_type_asset_roles = electrical_type_asset_roles

        super(ElectricalInfo, self).__init__(**kw_args)
    # >>> electrical_info


class AssetContainer(Asset):
    """ Asset that is aggregation of other assets such as conductors, transformers, switchgear, land, fences, buildings, equipment, vehicles, etc.
    """
    # All seals applied to this asset container.
    seals = []

    land_properties = []

    assets = []

    # <<< asset_container
    # @generated
    def __init__(self, seals=[], land_properties=[], assets=[], **kw_args):
        """ Initialises a new 'AssetContainer' instance.
        """
        self.seals = seals
        self.land_properties = land_properties
        self.assets = assets

        super(AssetContainer, self).__init__(**kw_args)
    # >>> asset_container


class AssetFunction(Asset):
    """ Function performed by an asset. Often, function is a module (or a board that plugs into a backplane) that can be replaced or updated without impacting the rest of the asset. Therefore functions are treated as assets because they have life-cycles that are independent of the asset containing the function.
    """
    # Configuration specified for this function. 
    config_id = ''

    # Hardware version. 
    hardware_id = ''

    # Name of program. 
    program_id = ''

    # Firmware version. 
    firmware_id = ''

    # Password needed to access this function. 
    password = ''

    asset = None

    asset_function_asset_model = None

    # <<< asset_function
    # @generated
    def __init__(self, config_id='', hardware_id='', program_id='', firmware_id='', password='', asset=None, asset_function_asset_model=None, **kw_args):
        """ Initialises a new 'AssetFunction' instance.
        """
        self.config_id = config_id
        self.hardware_id = hardware_id
        self.program_id = program_id
        self.firmware_id = firmware_id
        self.password = password
        self.asset = asset
        self.asset_function_asset_model = asset_function_asset_model

        super(AssetFunction, self).__init__(**kw_args)
    # >>> asset_function


class ComMediaAsset(Asset):
    """ Communication media such as fiber optic cable, power-line, telephone, etc.
    """
    pass
    # <<< com_media_asset
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ComMediaAsset' instance.
        """

        super(ComMediaAsset, self).__init__(**kw_args)
    # >>> com_media_asset


# <<< assets
# @generated
# >>> assets

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
    # <<< seal
    # @generated
    def __init__(self, kind='steel', applied_date_time='', seal_number='', condition='locked', asset_container=None, **kw_args):
        """ Initialises a new 'Seal' instance.
        """
        # Kind of seal. Values are: "steel", "other", "lock", "lead"
        self.kind = 'steel'
        # Date and time this seal has been applied. 
        self.applied_date_time = ''
        # (reserved word) Seal number. 
        self.seal_number = ''
        # Condition of seal. Values are: "locked", "other", "broken", "missing", "open"
        self.condition = 'locked'
        
        self._asset_container = None
        self.asset_container = asset_container

        super(Seal, self).__init__(**kw_args)
    # >>> seal
        
    # <<< asset_container
    # @generated
    def get_asset_container(self):
        """ Asset container to which this seal is applied.
        """
        return self._asset_container

    def set_asset_container(self, value):
        if self._asset_container is not None:
            filtered = [x for x in self.asset_container.seals if x != self]
            self._asset_container._seals = filtered
            
        self._asset_container = value
        if self._asset_container is not None:
            self._asset_container._seals.append(self)

    asset_container = property(get_asset_container, set_asset_container)
    # >>> asset_container



class AcceptanceTest(Element):
    """ Acceptance test for assets.
    """
    # <<< acceptance_test
    # @generated
    def __init__(self, type='', date_time='', success=False, **kw_args):
        """ Initialises a new 'AcceptanceTest' instance.
        """
        # Type of test or group of tests that was conducted on 'dateTime'. 
        self.type = ''
        # Date and time the asset was last tested using the 'type' of test and yiedling the currnet status in 'success' attribute. 
        self.date_time = ''
        # True if asset has passed acceptance test and may be placed in or is in service. It is set to false if asset is removed from service and is required to be tested again before being placed back in service, possibly in a new location. Since asset may go through multiple tests during its life cycle, the date of each acceptance test may be recorded in Asset.ActivityRecord.status.dateTime. 
        self.success = False
        

        super(AcceptanceTest, self).__init__(**kw_args)
    # >>> acceptance_test
        


class Asset(IdentifiedObject):
    """ Tangible resource of the utility, including power system equipment, cabinets, buildings, etc. For electrical network equipment, the role of the asset is defined through PowerSystemResource and its subclasses, defined mainly in the Wires model (refer to IEC61970-301 and model package IEC61970::Wires). Asset description places emphasis on the physical characteristics of the equipment fulfilling that role.
    """
    # <<< asset
    # @generated
    def __init__(self, purchase_price='', application='', initial_loss_of_life='', initial_condition='', corporate_code='', installation_date='', manufactured_date='', category='', utc_number='', critical=False, lot_number='', serial_number='', acceptance_test=None, status=None, to_asset_roles=[], erp_rec_delivery_items=[], asset_functions=[], electronic_addresses=[], dimensions_info=None, erp_item_master=None, properties=[], erp_inventory=None, scheduled_events=[], change_items=[], ratings=[], asset_container=None, work_task=None, document_roles=[], from_asset_roles=[], financial_info=None, asset_property_curves=[], erp_organisation_roles=[], measurements=[], hazards=[], mediums=[], reliability_infos=[], location_roles=[], power_system_resource_roles=[], activity_records=[], **kw_args):
        """ Initialises a new 'Asset' instance.
        """
        # Purchase price of asset. 
        self.purchase_price = ''
        # The way this particular asset is being used in this installation. For example, the application of a bushing when attached to a specific transformer winding would be one of the following: H1, H2, H3, H0, X1, X2, X3, X0, Y1, Y2, Y3, Y0. 
        self.application = ''
        # Whenever an asset is reconditioned, percentage of expected life for the asset when it was new; zero for new devices. 
        self.initial_loss_of_life = ''
        # Condition of asset in inventory or at time of installation. Examples include new, rebuilt, overhaul required, other. Refer to inspection data for information on the most current condition of the asset. 
        self.initial_condition = ''
        # Code for this type of asset. 
        self.corporate_code = ''
        # (if applicable) Date current installation was completed, which may not be the same as the in-service date. Asset may have been installed at other locations previously. Ignored if asset is (1) not currently installed (e.g., stored in a depot) or (2) not intended to be installed (e.g., vehicle, tool). 
        self.installation_date = ''
        # Date this asset was manufactured. 
        self.manufactured_date = ''
        # Extension mechanism to accommodate utility-specific categorisation of Asset and its subtypes, according to their corporate standards, practices, and existing IT systems (e.g., for management of assets, maintenance, work, outage, customers, etc.). 
        self.category = ''
        # Uniquely Tracked Commodity (UTC) number. 
        self.utc_number = ''
        # True if asset is considered critical for some reason (for example, a pole with critical attachments). 
        self.critical = False
        # Lot number for this asset. Even for the same model and version number, many assets are manufactured in lots. 
        self.lot_number = ''
        # Serial number of this asset. 
        self.serial_number = ''
        
        self.acceptance_test = acceptance_test
        self.status = status
        self._to_asset_roles = []
        self.to_asset_roles = to_asset_roles
        self._erp_rec_delivery_items = []
        self.erp_rec_delivery_items = erp_rec_delivery_items
        self._asset_functions = []
        self.asset_functions = asset_functions
        self._electronic_addresses = []
        self.electronic_addresses = electronic_addresses
        self._dimensions_info = None
        self.dimensions_info = dimensions_info
        self._erp_item_master = None
        self.erp_item_master = erp_item_master
        self._properties = []
        self.properties = properties
        self._erp_inventory = None
        self.erp_inventory = erp_inventory
        self._scheduled_events = []
        self.scheduled_events = scheduled_events
        self._change_items = []
        self.change_items = change_items
        self._ratings = []
        self.ratings = ratings
        self._asset_container = None
        self.asset_container = asset_container
        self._work_task = None
        self.work_task = work_task
        self._document_roles = []
        self.document_roles = document_roles
        self._from_asset_roles = []
        self.from_asset_roles = from_asset_roles
        self._financial_info = None
        self.financial_info = financial_info
        self._asset_property_curves = []
        self.asset_property_curves = asset_property_curves
        self._erp_organisation_roles = []
        self.erp_organisation_roles = erp_organisation_roles
        self._measurements = []
        self.measurements = measurements
        self._hazards = []
        self.hazards = hazards
        self._mediums = []
        self.mediums = mediums
        self._reliability_infos = []
        self.reliability_infos = reliability_infos
        self._location_roles = []
        self.location_roles = location_roles
        self._power_system_resource_roles = []
        self.power_system_resource_roles = power_system_resource_roles
        self._activity_records = []
        self.activity_records = activity_records

        super(Asset, self).__init__(**kw_args)
    # >>> asset
        
    # <<< acceptance_test
    # @generated
    # Information on acceptance test.
    acceptance_test = None
    # >>> acceptance_test

    # <<< status
    # @generated
    # Status of this asset.
    status = None
    # >>> status

    # <<< to_asset_roles
    # @generated
    def get_to_asset_roles(self):
        """ 
        """
        return self._to_asset_roles

    def set_to_asset_roles(self, value):
        for x in self._to_asset_roles:
            x._from_asset = None
        for y in value:
            y._from_asset = self
        self._to_asset_roles = value
            
    to_asset_roles = property(get_to_asset_roles, set_to_asset_roles)
    
    def add_to_asset_roles(self, *to_asset_roles):
        for obj in to_asset_roles:
            obj._from_asset = self
            self._to_asset_roles.append(obj)
        
    def remove_to_asset_roles(self, *to_asset_roles):
        for obj in to_asset_roles:
            obj._from_asset = None
            self._to_asset_roles.remove(obj)
    # >>> to_asset_roles

    # <<< erp_rec_delivery_items
    # @generated
    def get_erp_rec_delivery_items(self):
        """ 
        """
        return self._erp_rec_delivery_items

    def set_erp_rec_delivery_items(self, value):
        for p in self._erp_rec_delivery_items:
            filtered = [q for q in p.assets if q != self]
            self._erp_rec_delivery_items._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._erp_rec_delivery_items = value
            
    erp_rec_delivery_items = property(get_erp_rec_delivery_items, set_erp_rec_delivery_items)
    
    def add_erp_rec_delivery_items(self, *erp_rec_delivery_items):
        for obj in erp_rec_delivery_items:
            if self not in obj._assets:
                obj._assets.append(self)
            self._erp_rec_delivery_items.append(obj)
        
    def remove_erp_rec_delivery_items(self, *erp_rec_delivery_items):
        for obj in erp_rec_delivery_items:
            if self in obj._assets:
                obj._assets.remove(self)
            self._erp_rec_delivery_items.remove(obj)
    # >>> erp_rec_delivery_items

    # <<< asset_functions
    # @generated
    def get_asset_functions(self):
        """ 
        """
        return self._asset_functions

    def set_asset_functions(self, value):
        for x in self._asset_functions:
            x._asset = None
        for y in value:
            y._asset = self
        self._asset_functions = value
            
    asset_functions = property(get_asset_functions, set_asset_functions)
    
    def add_asset_functions(self, *asset_functions):
        for obj in asset_functions:
            obj._asset = self
            self._asset_functions.append(obj)
        
    def remove_asset_functions(self, *asset_functions):
        for obj in asset_functions:
            obj._asset = None
            self._asset_functions.remove(obj)
    # >>> asset_functions

    # <<< electronic_addresses
    # @generated
    def get_electronic_addresses(self):
        """ All electronic addresses of this asset.
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x._asset = None
        for y in value:
            y._asset = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._asset = self
            self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._asset = None
            self._electronic_addresses.remove(obj)
    # >>> electronic_addresses

    # <<< dimensions_info
    # @generated
    def get_dimensions_info(self):
        """ 
        """
        return self._dimensions_info

    def set_dimensions_info(self, value):
        if self._dimensions_info is not None:
            filtered = [x for x in self.dimensions_info.assets if x != self]
            self._dimensions_info._assets = filtered
            
        self._dimensions_info = value
        if self._dimensions_info is not None:
            self._dimensions_info._assets.append(self)

    dimensions_info = property(get_dimensions_info, set_dimensions_info)
    # >>> dimensions_info

    # <<< erp_item_master
    # @generated
    def get_erp_item_master(self):
        """ 
        """
        return self._erp_item_master

    def set_erp_item_master(self, value):
        if self._erp_item_master is not None:
            self._erp_item_master._asset = None
            
        self._erp_item_master = value
        if self._erp_item_master is not None:
            self._erp_item_master._asset = self
            
    erp_item_master = property(get_erp_item_master, set_erp_item_master)
    # >>> erp_item_master

    # <<< properties
    # @generated
    def get_properties(self):
        """ UserAttributes used to specify further properties of this asset. Use 'name' to specify what kind of property it is, and 'value.value' attribute for the actual value.
        """
        return self._properties

    def set_properties(self, value):
        for p in self._properties:
            filtered = [q for q in p.property_assets if q != self]
            self._properties._property_assets = filtered
        for r in value:
            if self not in r._property_assets:
                r._property_assets.append(self)
        self._properties = value
            
    properties = property(get_properties, set_properties)
    
    def add_properties(self, *properties):
        for obj in properties:
            if self not in obj._property_assets:
                obj._property_assets.append(self)
            self._properties.append(obj)
        
    def remove_properties(self, *properties):
        for obj in properties:
            if self in obj._property_assets:
                obj._property_assets.remove(self)
            self._properties.remove(obj)
    # >>> properties

    # <<< erp_inventory
    # @generated
    def get_erp_inventory(self):
        """ 
        """
        return self._erp_inventory

    def set_erp_inventory(self, value):
        if self._erp_inventory is not None:
            self._erp_inventory._asset = None
            
        self._erp_inventory = value
        if self._erp_inventory is not None:
            self._erp_inventory._asset = self
            
    erp_inventory = property(get_erp_inventory, set_erp_inventory)
    # >>> erp_inventory

    # <<< scheduled_events
    # @generated
    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for p in self._scheduled_events:
            filtered = [q for q in p.assets if q != self]
            self._scheduled_events._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            if self not in obj._assets:
                obj._assets.append(self)
            self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            if self in obj._assets:
                obj._assets.remove(self)
            self._scheduled_events.remove(obj)
    # >>> scheduled_events

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x._asset = None
        for y in value:
            y._asset = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._asset = self
            self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._asset = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< ratings
    # @generated
    def get_ratings(self):
        """ UserAttributes used to specify ratings of this asset. Ratings also can be used to set the initial value of operational measurement limits. Use 'name' to specify what kind of rating it is (e.g., voltage, current), and 'value' attribute for the actual value and unit information of the rating.
        """
        return self._ratings

    def set_ratings(self, value):
        for p in self._ratings:
            filtered = [q for q in p.rating_assets if q != self]
            self._ratings._rating_assets = filtered
        for r in value:
            if self not in r._rating_assets:
                r._rating_assets.append(self)
        self._ratings = value
            
    ratings = property(get_ratings, set_ratings)
    
    def add_ratings(self, *ratings):
        for obj in ratings:
            if self not in obj._rating_assets:
                obj._rating_assets.append(self)
            self._ratings.append(obj)
        
    def remove_ratings(self, *ratings):
        for obj in ratings:
            if self in obj._rating_assets:
                obj._rating_assets.remove(self)
            self._ratings.remove(obj)
    # >>> ratings

    # <<< asset_container
    # @generated
    def get_asset_container(self):
        """ 
        """
        return self._asset_container

    def set_asset_container(self, value):
        if self._asset_container is not None:
            filtered = [x for x in self.asset_container.assets if x != self]
            self._asset_container._assets = filtered
            
        self._asset_container = value
        if self._asset_container is not None:
            self._asset_container._assets.append(self)

    asset_container = property(get_asset_container, set_asset_container)
    # >>> asset_container

    # <<< work_task
    # @generated
    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.assets if x != self]
            self._work_task._assets = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._assets.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task

    # <<< document_roles
    # @generated
    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x._asset = None
        for y in value:
            y._asset = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._asset = self
            self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._asset = None
            self._document_roles.remove(obj)
    # >>> document_roles

    # <<< from_asset_roles
    # @generated
    def get_from_asset_roles(self):
        """ 
        """
        return self._from_asset_roles

    def set_from_asset_roles(self, value):
        for x in self._from_asset_roles:
            x._to_asset = None
        for y in value:
            y._to_asset = self
        self._from_asset_roles = value
            
    from_asset_roles = property(get_from_asset_roles, set_from_asset_roles)
    
    def add_from_asset_roles(self, *from_asset_roles):
        for obj in from_asset_roles:
            obj._to_asset = self
            self._from_asset_roles.append(obj)
        
    def remove_from_asset_roles(self, *from_asset_roles):
        for obj in from_asset_roles:
            obj._to_asset = None
            self._from_asset_roles.remove(obj)
    # >>> from_asset_roles

    # <<< financial_info
    # @generated
    def get_financial_info(self):
        """ 
        """
        return self._financial_info

    def set_financial_info(self, value):
        if self._financial_info is not None:
            self._financial_info._asset = None
            
        self._financial_info = value
        if self._financial_info is not None:
            self._financial_info._asset = self
            
    financial_info = property(get_financial_info, set_financial_info)
    # >>> financial_info

    # <<< asset_property_curves
    # @generated
    def get_asset_property_curves(self):
        """ 
        """
        return self._asset_property_curves

    def set_asset_property_curves(self, value):
        for p in self._asset_property_curves:
            filtered = [q for q in p.assets if q != self]
            self._asset_property_curves._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._asset_property_curves = value
            
    asset_property_curves = property(get_asset_property_curves, set_asset_property_curves)
    
    def add_asset_property_curves(self, *asset_property_curves):
        for obj in asset_property_curves:
            if self not in obj._assets:
                obj._assets.append(self)
            self._asset_property_curves.append(obj)
        
    def remove_asset_property_curves(self, *asset_property_curves):
        for obj in asset_property_curves:
            if self in obj._assets:
                obj._assets.remove(self)
            self._asset_property_curves.remove(obj)
    # >>> asset_property_curves

    # <<< erp_organisation_roles
    # @generated
    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x._asset = None
        for y in value:
            y._asset = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._asset = self
            self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._asset = None
            self._erp_organisation_roles.remove(obj)
    # >>> erp_organisation_roles

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ 
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x._asset = None
        for y in value:
            y._asset = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._asset = self
            self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._asset = None
            self._measurements.remove(obj)
    # >>> measurements

    # <<< hazards
    # @generated
    def get_hazards(self):
        """ 
        """
        return self._hazards

    def set_hazards(self, value):
        for p in self._hazards:
            filtered = [q for q in p.assets if q != self]
            self._hazards._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._hazards = value
            
    hazards = property(get_hazards, set_hazards)
    
    def add_hazards(self, *hazards):
        for obj in hazards:
            if self not in obj._assets:
                obj._assets.append(self)
            self._hazards.append(obj)
        
    def remove_hazards(self, *hazards):
        for obj in hazards:
            if self in obj._assets:
                obj._assets.remove(self)
            self._hazards.remove(obj)
    # >>> hazards

    # <<< mediums
    # @generated
    def get_mediums(self):
        """ 
        """
        return self._mediums

    def set_mediums(self, value):
        for p in self._mediums:
            filtered = [q for q in p.assets if q != self]
            self._mediums._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._mediums = value
            
    mediums = property(get_mediums, set_mediums)
    
    def add_mediums(self, *mediums):
        for obj in mediums:
            if self not in obj._assets:
                obj._assets.append(self)
            self._mediums.append(obj)
        
    def remove_mediums(self, *mediums):
        for obj in mediums:
            if self in obj._assets:
                obj._assets.remove(self)
            self._mediums.remove(obj)
    # >>> mediums

    # <<< reliability_infos
    # @generated
    def get_reliability_infos(self):
        """ 
        """
        return self._reliability_infos

    def set_reliability_infos(self, value):
        for p in self._reliability_infos:
            filtered = [q for q in p.assets if q != self]
            self._reliability_infos._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._reliability_infos = value
            
    reliability_infos = property(get_reliability_infos, set_reliability_infos)
    
    def add_reliability_infos(self, *reliability_infos):
        for obj in reliability_infos:
            if self not in obj._assets:
                obj._assets.append(self)
            self._reliability_infos.append(obj)
        
    def remove_reliability_infos(self, *reliability_infos):
        for obj in reliability_infos:
            if self in obj._assets:
                obj._assets.remove(self)
            self._reliability_infos.remove(obj)
    # >>> reliability_infos

    # <<< location_roles
    # @generated
    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x._asset = None
        for y in value:
            y._asset = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._asset = self
            self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._asset = None
            self._location_roles.remove(obj)
    # >>> location_roles

    # <<< power_system_resource_roles
    # @generated
    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x._asset = None
        for y in value:
            y._asset = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._asset = self
            self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._asset = None
            self._power_system_resource_roles.remove(obj)
    # >>> power_system_resource_roles

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ All activity records created for this asset.
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self._activity_records:
            filtered = [q for q in p.assets if q != self]
            self._activity_records._assets = filtered
        for r in value:
            if self not in r._assets:
                r._assets.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._assets:
                obj._assets.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._assets:
                obj._assets.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records



class ElectricalInfo(IdentifiedObject):
    """ Electrical properties of an asset or of an asset model (product by a manufacturer). Can also be used to define electrical properties for each phase individually. Not every attribute will be required for each type of asset or asset model. For example, a transformer may only have requirments for 'ratedVoltage', 'ratedApparentPower' and 'phaseCount' attributes, while a LinearConductor will have 'r', 'x', 'b' and 'g' requirements per unit length on top of a 'ratedCurrent' and 'ratedVoltage'.
    """
    # <<< electrical_info
    # @generated
    def __init__(self, g0='', x='', r0='', wire_count=0, x0='', rated_voltage='', g='', b0='', b='', rated_apparent_power='', r='', phase_count=0, bil='', rated_current='', frequency='', end_device_assets=[], electrical_assets=[], electrical_asset_model_roles=[], electrical_type_asset_roles=[], **kw_args):
        """ Initialises a new 'ElectricalInfo' instance.
        """
        # Zero sequence conductance. 
        self.g0 = ''
        # Positive sequence series reactance. 
        self.x = ''
        # Zero sequence series resistance. 
        self.r0 = ''
        # For an installed asset, this is the total number of electrical wires that are physically connected to it. For an AssetModel, this is the total number of wires that can potentially be connected to this asset type. This is particularly useful to understand overall electrical configurations for distribution secondary where the number of wires can not be derived from phase information alone. For example, 120v 2 Wires; 240v 2 Wires; 480v 1Ph 2 Wires; 120/240v 1Ph; 120/208v 3Ph Y; 120/208v 1Ph Y; 120/240v 3Ph D; 240/480v 1Ph 3 Wires; 480v 3Ph D; 240/480v 3Ph D; 277/480v 3Ph Y. 
        self.wire_count = 0
        # Zero sequence series reactance. 
        self.x0 = ''
        # Rated voltage. 
        self.rated_voltage = ''
        # Positive sequence conductance. 
        self.g = ''
        # Zero sequence susceptance. 
        self.b0 = ''
        # Positive sequence susceptance. 
        self.b = ''
        # Rated apparent power. 
        self.rated_apparent_power = ''
        # Positive sequence series resistance. 
        self.r = ''
        # Number of potential phases the asset supports, typically 0, 1 or 3. The actual phases connected are determined from 'ConductingEquipment.phases' attribute in the ConductingEquipment subclass associated with the asset or from 'ElectricalAsset.phaseCode' attribute. 
        self.phase_count = 0
        # Basic Insulation Level (BIL) for switchgear, insulators, etc. A reference insulation level expressed as the impulse crest voltage of a nominal wave, typically 1.2 X 50 microsecond. This is a measure of the ability of the insulation to withstand very high voltage surges. 
        self.bil = ''
        # Rated current. 
        self.rated_current = ''
        # Frequency at which stated device ratings apply, typically 50Hz or 60Hz. 
        self.frequency = ''
        
        self._end_device_assets = []
        self.end_device_assets = end_device_assets
        self._electrical_assets = []
        self.electrical_assets = electrical_assets
        self._electrical_asset_model_roles = []
        self.electrical_asset_model_roles = electrical_asset_model_roles
        self._electrical_type_asset_roles = []
        self.electrical_type_asset_roles = electrical_type_asset_roles

        super(ElectricalInfo, self).__init__(**kw_args)
    # >>> electrical_info
        
    # <<< end_device_assets
    # @generated
    def get_end_device_assets(self):
        """ All end device assets having this set of electrical properties.
        """
        return self._end_device_assets

    def set_end_device_assets(self, value):
        for p in self._end_device_assets:
            filtered = [q for q in p.electrical_infos if q != self]
            self._end_device_assets._electrical_infos = filtered
        for r in value:
            if self not in r._electrical_infos:
                r._electrical_infos.append(self)
        self._end_device_assets = value
            
    end_device_assets = property(get_end_device_assets, set_end_device_assets)
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            if self not in obj._electrical_infos:
                obj._electrical_infos.append(self)
            self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            if self in obj._electrical_infos:
                obj._electrical_infos.remove(self)
            self._end_device_assets.remove(obj)
    # >>> end_device_assets

    # <<< electrical_assets
    # @generated
    def get_electrical_assets(self):
        """ 
        """
        return self._electrical_assets

    def set_electrical_assets(self, value):
        for p in self._electrical_assets:
            filtered = [q for q in p.electrical_infos if q != self]
            self._electrical_assets._electrical_infos = filtered
        for r in value:
            if self not in r._electrical_infos:
                r._electrical_infos.append(self)
        self._electrical_assets = value
            
    electrical_assets = property(get_electrical_assets, set_electrical_assets)
    
    def add_electrical_assets(self, *electrical_assets):
        for obj in electrical_assets:
            if self not in obj._electrical_infos:
                obj._electrical_infos.append(self)
            self._electrical_assets.append(obj)
        
    def remove_electrical_assets(self, *electrical_assets):
        for obj in electrical_assets:
            if self in obj._electrical_infos:
                obj._electrical_infos.remove(self)
            self._electrical_assets.remove(obj)
    # >>> electrical_assets

    # <<< electrical_asset_model_roles
    # @generated
    def get_electrical_asset_model_roles(self):
        """ 
        """
        return self._electrical_asset_model_roles

    def set_electrical_asset_model_roles(self, value):
        for x in self._electrical_asset_model_roles:
            x._electrical_info = None
        for y in value:
            y._electrical_info = self
        self._electrical_asset_model_roles = value
            
    electrical_asset_model_roles = property(get_electrical_asset_model_roles, set_electrical_asset_model_roles)
    
    def add_electrical_asset_model_roles(self, *electrical_asset_model_roles):
        for obj in electrical_asset_model_roles:
            obj._electrical_info = self
            self._electrical_asset_model_roles.append(obj)
        
    def remove_electrical_asset_model_roles(self, *electrical_asset_model_roles):
        for obj in electrical_asset_model_roles:
            obj._electrical_info = None
            self._electrical_asset_model_roles.remove(obj)
    # >>> electrical_asset_model_roles

    # <<< electrical_type_asset_roles
    # @generated
    def get_electrical_type_asset_roles(self):
        """ 
        """
        return self._electrical_type_asset_roles

    def set_electrical_type_asset_roles(self, value):
        for x in self._electrical_type_asset_roles:
            x._electrical_info = None
        for y in value:
            y._electrical_info = self
        self._electrical_type_asset_roles = value
            
    electrical_type_asset_roles = property(get_electrical_type_asset_roles, set_electrical_type_asset_roles)
    
    def add_electrical_type_asset_roles(self, *electrical_type_asset_roles):
        for obj in electrical_type_asset_roles:
            obj._electrical_info = self
            self._electrical_type_asset_roles.append(obj)
        
    def remove_electrical_type_asset_roles(self, *electrical_type_asset_roles):
        for obj in electrical_type_asset_roles:
            obj._electrical_info = None
            self._electrical_type_asset_roles.remove(obj)
    # >>> electrical_type_asset_roles



class AssetContainer(Asset):
    """ Asset that is aggregation of other assets such as conductors, transformers, switchgear, land, fences, buildings, equipment, vehicles, etc.
    """
    # <<< asset_container
    # @generated
    def __init__(self, seals=[], land_properties=[], assets=[], **kw_args):
        """ Initialises a new 'AssetContainer' instance.
        """
        
        self._seals = []
        self.seals = seals
        self._land_properties = []
        self.land_properties = land_properties
        self._assets = []
        self.assets = assets

        super(AssetContainer, self).__init__(**kw_args)
    # >>> asset_container
        
    # <<< seals
    # @generated
    def get_seals(self):
        """ All seals applied to this asset container.
        """
        return self._seals

    def set_seals(self, value):
        for x in self._seals:
            x._asset_container = None
        for y in value:
            y._asset_container = self
        self._seals = value
            
    seals = property(get_seals, set_seals)
    
    def add_seals(self, *seals):
        for obj in seals:
            obj._asset_container = self
            self._seals.append(obj)
        
    def remove_seals(self, *seals):
        for obj in seals:
            obj._asset_container = None
            self._seals.remove(obj)
    # >>> seals

    # <<< land_properties
    # @generated
    def get_land_properties(self):
        """ 
        """
        return self._land_properties

    def set_land_properties(self, value):
        for p in self._land_properties:
            filtered = [q for q in p.asset_containers if q != self]
            self._land_properties._asset_containers = filtered
        for r in value:
            if self not in r._asset_containers:
                r._asset_containers.append(self)
        self._land_properties = value
            
    land_properties = property(get_land_properties, set_land_properties)
    
    def add_land_properties(self, *land_properties):
        for obj in land_properties:
            if self not in obj._asset_containers:
                obj._asset_containers.append(self)
            self._land_properties.append(obj)
        
    def remove_land_properties(self, *land_properties):
        for obj in land_properties:
            if self in obj._asset_containers:
                obj._asset_containers.remove(self)
            self._land_properties.remove(obj)
    # >>> land_properties

    # <<< assets
    # @generated
    def get_assets(self):
        """ 
        """
        return self._assets

    def set_assets(self, value):
        for x in self._assets:
            x._asset_container = None
        for y in value:
            y._asset_container = self
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            obj._asset_container = self
            self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            obj._asset_container = None
            self._assets.remove(obj)
    # >>> assets



class AssetFunction(Asset):
    """ Function performed by an asset. Often, function is a module (or a board that plugs into a backplane) that can be replaced or updated without impacting the rest of the asset. Therefore functions are treated as assets because they have life-cycles that are independent of the asset containing the function.
    """
    # <<< asset_function
    # @generated
    def __init__(self, config_id='', hardware_id='', program_id='', firmware_id='', password='', asset=None, asset_function_asset_model=None, **kw_args):
        """ Initialises a new 'AssetFunction' instance.
        """
        # Configuration specified for this function. 
        self.config_id = ''
        # Hardware version. 
        self.hardware_id = ''
        # Name of program. 
        self.program_id = ''
        # Firmware version. 
        self.firmware_id = ''
        # Password needed to access this function. 
        self.password = ''
        
        self._asset = None
        self.asset = asset
        self._asset_function_asset_model = None
        self.asset_function_asset_model = asset_function_asset_model

        super(AssetFunction, self).__init__(**kw_args)
    # >>> asset_function
        
    # <<< asset
    # @generated
    def get_asset(self):
        """ 
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            filtered = [x for x in self.asset.asset_functions if x != self]
            self._asset._asset_functions = filtered
            
        self._asset = value
        if self._asset is not None:
            self._asset._asset_functions.append(self)

    asset = property(get_asset, set_asset)
    # >>> asset

    # <<< asset_function_asset_model
    # @generated
    def get_asset_function_asset_model(self):
        """ 
        """
        return self._asset_function_asset_model

    def set_asset_function_asset_model(self, value):
        if self._asset_function_asset_model is not None:
            filtered = [x for x in self.asset_function_asset_model.asset_functions if x != self]
            self._asset_function_asset_model._asset_functions = filtered
            
        self._asset_function_asset_model = value
        if self._asset_function_asset_model is not None:
            self._asset_function_asset_model._asset_functions.append(self)

    asset_function_asset_model = property(get_asset_function_asset_model, set_asset_function_asset_model)
    # >>> asset_function_asset_model



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

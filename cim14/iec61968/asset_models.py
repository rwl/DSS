# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.AssetModels"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.AssetModels"

class AssetModel(Document):
    """ Documentation for a particular product model made by a manufacturer. There are typically many instances of an asset associated with a single asset model.
    """
    # <<< asset_model
    # @generated
    def __init__(self, model_number='', weight_total='', model_version='', corporate_standard_kind='standard', usage_kind='other', type_asset=None, asset_model_catalogue_items=[], erp_inventory_counts=[], **kw_args):
        """ Initialises a new 'AssetModel' instance.
        """
        # Manufacturer's model number. 
        self.model_number = ''
        # Total manufactured weight of asset. 
        self.weight_total = ''
        # Version number for product model, which indicates vintage of the product. 
        self.model_version = ''
        # Kind of coporate standard for this asset model. Values are: "standard", "other", "experimental", "under_evaluation"
        self.corporate_standard_kind = 'standard'
        # Intended usage for this asset model. Values are: "other", "substation", "unknown", "customer_substation", "distribution_overhead", "distribution_underground", "streetlight", "transmission"
        self.usage_kind = 'other'
        
        self._type_asset = None
        self.type_asset = type_asset
        self._asset_model_catalogue_items = []
        self.asset_model_catalogue_items = asset_model_catalogue_items
        self._erp_inventory_counts = []
        self.erp_inventory_counts = erp_inventory_counts

        super(AssetModel, self).__init__(**kw_args)
    # >>> asset_model
        
    # <<< type_asset
    # @generated
    def get_type_asset(self):
        """ A type of asset may be satisified with many different types of asset models.
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            filtered = [x for x in self.type_asset.asset_models if x != self]
            self._type_asset._asset_models = filtered
            
        self._type_asset = value
        if self._type_asset is not None:
            self._type_asset._asset_models.append(self)

    type_asset = property(get_type_asset, set_type_asset)
    # >>> type_asset

    # <<< asset_model_catalogue_items
    # @generated
    def get_asset_model_catalogue_items(self):
        """ 
        """
        return self._asset_model_catalogue_items

    def set_asset_model_catalogue_items(self, value):
        for x in self._asset_model_catalogue_items:
            x._asset_model = None
        for y in value:
            y._asset_model = self
        self._asset_model_catalogue_items = value
            
    asset_model_catalogue_items = property(get_asset_model_catalogue_items, set_asset_model_catalogue_items)
    
    def add_asset_model_catalogue_items(self, *asset_model_catalogue_items):
        for obj in asset_model_catalogue_items:
            obj._asset_model = self
            self._asset_model_catalogue_items.append(obj)
        
    def remove_asset_model_catalogue_items(self, *asset_model_catalogue_items):
        for obj in asset_model_catalogue_items:
            obj._asset_model = None
            self._asset_model_catalogue_items.remove(obj)
    # >>> asset_model_catalogue_items

    # <<< erp_inventory_counts
    # @generated
    def get_erp_inventory_counts(self):
        """ 
        """
        return self._erp_inventory_counts

    def set_erp_inventory_counts(self, value):
        for x in self._erp_inventory_counts:
            x._asset_model = None
        for y in value:
            y._asset_model = self
        self._erp_inventory_counts = value
            
    erp_inventory_counts = property(get_erp_inventory_counts, set_erp_inventory_counts)
    
    def add_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._asset_model = self
            self._erp_inventory_counts.append(obj)
        
    def remove_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._asset_model = None
            self._erp_inventory_counts.remove(obj)
    # >>> erp_inventory_counts



class EndDeviceModel(AssetModel):
    """ Documentation for particular end device product model made by a manufacturer.
    """
    # <<< end_device_model
    # @generated
    def __init__(self, end_device_assets=[], end_device_type_asset=None, **kw_args):
        """ Initialises a new 'EndDeviceModel' instance.
        """
        
        self._end_device_assets = []
        self.end_device_assets = end_device_assets
        self._end_device_type_asset = None
        self.end_device_type_asset = end_device_type_asset

        super(EndDeviceModel, self).__init__(**kw_args)
    # >>> end_device_model
        
    # <<< end_device_assets
    # @generated
    def get_end_device_assets(self):
        """ All end device assets being of this model.
        """
        return self._end_device_assets

    def set_end_device_assets(self, value):
        for x in self._end_device_assets:
            x._end_device_model = None
        for y in value:
            y._end_device_model = self
        self._end_device_assets = value
            
    end_device_assets = property(get_end_device_assets, set_end_device_assets)
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._end_device_model = self
            self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._end_device_model = None
            self._end_device_assets.remove(obj)
    # >>> end_device_assets

    # <<< end_device_type_asset
    # @generated
    def get_end_device_type_asset(self):
        """ 
        """
        return self._end_device_type_asset

    def set_end_device_type_asset(self, value):
        if self._end_device_type_asset is not None:
            filtered = [x for x in self.end_device_type_asset.end_device_models if x != self]
            self._end_device_type_asset._end_device_models = filtered
            
        self._end_device_type_asset = value
        if self._end_device_type_asset is not None:
            self._end_device_type_asset._end_device_models.append(self)

    end_device_type_asset = property(get_end_device_type_asset, set_end_device_type_asset)
    # >>> end_device_type_asset



# <<< asset_models
# @generated
# >>> asset_models

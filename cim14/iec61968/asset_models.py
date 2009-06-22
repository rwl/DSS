# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

class AssetModel(Document):
    """ Documentation for a particular product model made by a manufacturer. There are typically many instances of an asset associated with a single asset model.
    """
    # Manufacturer's model number. 
    model_number = ''

    # Total manufactured weight of asset. 
    weight_total = ''

    # Version number for product model, which indicates vintage of the product. 
    model_version = ''

    # Kind of coporate standard for this asset model. Values are: "standard", "other", "experimental", "under_evaluation"
    corporate_standard_kind = 'standard'

    # Intended usage for this asset model. Values are: "other", "substation", "unknown", "customer_substation", "distribution_overhead", "distribution_underground", "streetlight", "transmission"
    usage_kind = 'other'

    # A type of asset may be satisified with many different types of asset models.
    type_asset = None

    asset_model_catalogue_items = []

    erp_inventory_counts = []

    # <<< asset_model
    # @generated
    def __init__(self, model_number='', weight_total='', model_version='', corporate_standard_kind='standard', usage_kind='other', type_asset=None, asset_model_catalogue_items=[], erp_inventory_counts=[], **kw_args):
        """ Initialises a new 'AssetModel' instance.
        """
        self.model_number = model_number
        self.weight_total = weight_total
        self.model_version = model_version
        self.corporate_standard_kind = corporate_standard_kind
        self.usage_kind = usage_kind
        self.type_asset = type_asset
        self.asset_model_catalogue_items = asset_model_catalogue_items
        self.erp_inventory_counts = erp_inventory_counts

        super(AssetModel, self).__init__(**kw_args)
    # >>> asset_model


class EndDeviceModel(AssetModel):
    """ Documentation for particular end device product model made by a manufacturer.
    """
    # All end device assets being of this model.
    end_device_assets = []

    end_device_type_asset = None

    # <<< end_device_model
    # @generated
    def __init__(self, end_device_assets=[], end_device_type_asset=None, **kw_args):
        """ Initialises a new 'EndDeviceModel' instance.
        """
        self.end_device_assets = end_device_assets
        self.end_device_type_asset = end_device_type_asset

        super(EndDeviceModel, self).__init__(**kw_args)
    # >>> end_device_model


# <<< asset_models
# @generated
# >>> asset_models

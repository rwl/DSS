# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document
from cim14.iec61968.asset_models import AssetModel
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfAssetModels"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfAssetModels"

class AssetModelCatalogueItem(Document):
    """ Provides pricing and other relevant information about a specific manufacturer's product (i.e., AssetModel), and its price from a given supplier. A single AssetModel may be availble from multiple suppliers. Note that manufacturer and supplier are both types of organisation, which the association is inherited from Document.
    """
    # <<< asset_model_catalogue_item
    # @generated
    def __init__(self, unit_cost=0.0, erp_poline_items=None, asset_model=None, asset_model_catalogue=None, erp_quote_line_items=None, **kw_args):
        """ Initialises a new 'AssetModelCatalogueItem' instance.
        """
        # Unit cost for an asset model from a specific supplier, either for a unit cost or cost per unit length. Cost is for material or asset only and does not include labor to install/construct or configure it. 
        self.unit_cost = unit_cost
        
        self._erp_poline_items = []
        if erp_poline_items is None:
            self.erp_poline_items = []
        else:
            self.erp_poline_items = erp_poline_items
        self._asset_model = None
        self.asset_model = asset_model
        self._asset_model_catalogue = None
        self.asset_model_catalogue = asset_model_catalogue
        self._erp_quote_line_items = []
        if erp_quote_line_items is None:
            self.erp_quote_line_items = []
        else:
            self.erp_quote_line_items = erp_quote_line_items

        super(AssetModelCatalogueItem, self).__init__(**kw_args)
    # >>> asset_model_catalogue_item
        
    # <<< erp_poline_items
    # @generated
    def get_erp_poline_items(self):
        """ 
        """
        return self._erp_poline_items

    def set_erp_poline_items(self, value):
        for x in self._erp_poline_items:
            x.asset_model_catalogue_item = None
        for y in value:
            y.asset_model_catalogue_item = self
        self._erp_poline_items = value
            
    erp_poline_items = property(get_erp_poline_items, set_erp_poline_items)
    
    def add_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._asset_model_catalogue_item = self
            if obj not in self._erp_poline_items:
                self._erp_poline_items.append(obj)
        
    def remove_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._asset_model_catalogue_item = None
            self._erp_poline_items.remove(obj)
    # >>> erp_poline_items

    # <<< asset_model
    # @generated
    def get_asset_model(self):
        """ 
        """
        return self._asset_model

    def set_asset_model(self, value):
        if self._asset_model is not None:
            filtered = [x for x in self.asset_model.asset_model_catalogue_items if x != self]
            self._asset_model._asset_model_catalogue_items = filtered
            
        self._asset_model = value
        if self._asset_model is not None:
            if self not in self._asset_model._asset_model_catalogue_items:
                self._asset_model._asset_model_catalogue_items.append(self)

    asset_model = property(get_asset_model, set_asset_model)
    # >>> asset_model

    # <<< asset_model_catalogue
    # @generated
    def get_asset_model_catalogue(self):
        """ 
        """
        return self._asset_model_catalogue

    def set_asset_model_catalogue(self, value):
        if self._asset_model_catalogue is not None:
            filtered = [x for x in self.asset_model_catalogue.asset_model_catalogue_items if x != self]
            self._asset_model_catalogue._asset_model_catalogue_items = filtered
            
        self._asset_model_catalogue = value
        if self._asset_model_catalogue is not None:
            if self not in self._asset_model_catalogue._asset_model_catalogue_items:
                self._asset_model_catalogue._asset_model_catalogue_items.append(self)

    asset_model_catalogue = property(get_asset_model_catalogue, set_asset_model_catalogue)
    # >>> asset_model_catalogue

    # <<< erp_quote_line_items
    # @generated
    def get_erp_quote_line_items(self):
        """ 
        """
        return self._erp_quote_line_items

    def set_erp_quote_line_items(self, value):
        for x in self._erp_quote_line_items:
            x.asset_model_catalogue_item = None
        for y in value:
            y.asset_model_catalogue_item = self
        self._erp_quote_line_items = value
            
    erp_quote_line_items = property(get_erp_quote_line_items, set_erp_quote_line_items)
    
    def add_erp_quote_line_items(self, *erp_quote_line_items):
        for obj in erp_quote_line_items:
            obj._asset_model_catalogue_item = self
            if obj not in self._erp_quote_line_items:
                self._erp_quote_line_items.append(obj)
        
    def remove_erp_quote_line_items(self, *erp_quote_line_items):
        for obj in erp_quote_line_items:
            obj._asset_model_catalogue_item = None
            self._erp_quote_line_items.remove(obj)
    # >>> erp_quote_line_items



class CompositeSwitchAssetModel(AssetModel):
    """ Documentation for a type of a composite switch asset of a particular product model made by a manufacturer.
    """
    # <<< composite_switch_asset_model
    # @generated
    def __init__(self, composite_switch_type_asset=None, composite_switch_assets=None, composite_switch_info=None, **kw_args):
        """ Initialises a new 'CompositeSwitchAssetModel' instance.
        """
        
        self._composite_switch_type_asset = None
        self.composite_switch_type_asset = composite_switch_type_asset
        self._composite_switch_assets = []
        if composite_switch_assets is None:
            self.composite_switch_assets = []
        else:
            self.composite_switch_assets = composite_switch_assets
        self._composite_switch_info = None
        self.composite_switch_info = composite_switch_info

        super(CompositeSwitchAssetModel, self).__init__(**kw_args)
    # >>> composite_switch_asset_model
        
    # <<< composite_switch_type_asset
    # @generated
    def get_composite_switch_type_asset(self):
        """ 
        """
        return self._composite_switch_type_asset

    def set_composite_switch_type_asset(self, value):
        if self._composite_switch_type_asset is not None:
            filtered = [x for x in self.composite_switch_type_asset.composite_switch_asset_models if x != self]
            self._composite_switch_type_asset._composite_switch_asset_models = filtered
            
        self._composite_switch_type_asset = value
        if self._composite_switch_type_asset is not None:
            if self not in self._composite_switch_type_asset._composite_switch_asset_models:
                self._composite_switch_type_asset._composite_switch_asset_models.append(self)

    composite_switch_type_asset = property(get_composite_switch_type_asset, set_composite_switch_type_asset)
    # >>> composite_switch_type_asset

    # <<< composite_switch_assets
    # @generated
    def get_composite_switch_assets(self):
        """ 
        """
        return self._composite_switch_assets

    def set_composite_switch_assets(self, value):
        for x in self._composite_switch_assets:
            x.composite_switch_asset_model = None
        for y in value:
            y.composite_switch_asset_model = self
        self._composite_switch_assets = value
            
    composite_switch_assets = property(get_composite_switch_assets, set_composite_switch_assets)
    
    def add_composite_switch_assets(self, *composite_switch_assets):
        for obj in composite_switch_assets:
            obj._composite_switch_asset_model = self
            if obj not in self._composite_switch_assets:
                self._composite_switch_assets.append(obj)
        
    def remove_composite_switch_assets(self, *composite_switch_assets):
        for obj in composite_switch_assets:
            obj._composite_switch_asset_model = None
            self._composite_switch_assets.remove(obj)
    # >>> composite_switch_assets

    # <<< composite_switch_info
    # @generated
    def get_composite_switch_info(self):
        """ 
        """
        return self._composite_switch_info

    def set_composite_switch_info(self, value):
        if self._composite_switch_info is not None:
            self._composite_switch_info._composite_switch_asset_model = None
            
        self._composite_switch_info = value
        if self._composite_switch_info is not None:
            self._composite_switch_info._composite_switch_asset_model = self
            
    composite_switch_info = property(get_composite_switch_info, set_composite_switch_info)
    # >>> composite_switch_info



class WorkEquipmentAssetModel(AssetModel):
    """ Documentation for a type of an equipment used for work of a particular product model made by a manufacturer.
    """
    # <<< work_equipment_asset_model
    # @generated
    def __init__(self, work_equipment_assets=None, work_equipment_type_asset=None, **kw_args):
        """ Initialises a new 'WorkEquipmentAssetModel' instance.
        """
        
        self._work_equipment_assets = []
        if work_equipment_assets is None:
            self.work_equipment_assets = []
        else:
            self.work_equipment_assets = work_equipment_assets
        self._work_equipment_type_asset = None
        self.work_equipment_type_asset = work_equipment_type_asset

        super(WorkEquipmentAssetModel, self).__init__(**kw_args)
    # >>> work_equipment_asset_model
        
    # <<< work_equipment_assets
    # @generated
    def get_work_equipment_assets(self):
        """ 
        """
        return self._work_equipment_assets

    def set_work_equipment_assets(self, value):
        for x in self._work_equipment_assets:
            x.work_equipment_asset_model = None
        for y in value:
            y.work_equipment_asset_model = self
        self._work_equipment_assets = value
            
    work_equipment_assets = property(get_work_equipment_assets, set_work_equipment_assets)
    
    def add_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._work_equipment_asset_model = self
            if obj not in self._work_equipment_assets:
                self._work_equipment_assets.append(obj)
        
    def remove_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._work_equipment_asset_model = None
            self._work_equipment_assets.remove(obj)
    # >>> work_equipment_assets

    # <<< work_equipment_type_asset
    # @generated
    def get_work_equipment_type_asset(self):
        """ 
        """
        return self._work_equipment_type_asset

    def set_work_equipment_type_asset(self, value):
        if self._work_equipment_type_asset is not None:
            filtered = [x for x in self.work_equipment_type_asset.work_equipment_asset_models if x != self]
            self._work_equipment_type_asset._work_equipment_asset_models = filtered
            
        self._work_equipment_type_asset = value
        if self._work_equipment_type_asset is not None:
            if self not in self._work_equipment_type_asset._work_equipment_asset_models:
                self._work_equipment_type_asset._work_equipment_asset_models.append(self)

    work_equipment_type_asset = property(get_work_equipment_type_asset, set_work_equipment_type_asset)
    # >>> work_equipment_type_asset



class AssetFunctionAssetModel(AssetModel):
    """ Documentation for a type of an asset function of a particular product model made by a manufacturer.(Organisation). Asset Functions are typically component parts of Assets or Asset Containers.
    """
    # <<< asset_function_asset_model
    # @generated
    def __init__(self, asset_functions=None, asset_function_type_asset=None, **kw_args):
        """ Initialises a new 'AssetFunctionAssetModel' instance.
        """
        
        self._asset_functions = []
        if asset_functions is None:
            self.asset_functions = []
        else:
            self.asset_functions = asset_functions
        self._asset_function_type_asset = None
        self.asset_function_type_asset = asset_function_type_asset

        super(AssetFunctionAssetModel, self).__init__(**kw_args)
    # >>> asset_function_asset_model
        
    # <<< asset_functions
    # @generated
    def get_asset_functions(self):
        """ 
        """
        return self._asset_functions

    def set_asset_functions(self, value):
        for x in self._asset_functions:
            x.asset_function_asset_model = None
        for y in value:
            y.asset_function_asset_model = self
        self._asset_functions = value
            
    asset_functions = property(get_asset_functions, set_asset_functions)
    
    def add_asset_functions(self, *asset_functions):
        for obj in asset_functions:
            obj._asset_function_asset_model = self
            if obj not in self._asset_functions:
                self._asset_functions.append(obj)
        
    def remove_asset_functions(self, *asset_functions):
        for obj in asset_functions:
            obj._asset_function_asset_model = None
            self._asset_functions.remove(obj)
    # >>> asset_functions

    # <<< asset_function_type_asset
    # @generated
    def get_asset_function_type_asset(self):
        """ 
        """
        return self._asset_function_type_asset

    def set_asset_function_type_asset(self, value):
        if self._asset_function_type_asset is not None:
            filtered = [x for x in self.asset_function_type_asset.asset_function_asset_models if x != self]
            self._asset_function_type_asset._asset_function_asset_models = filtered
            
        self._asset_function_type_asset = value
        if self._asset_function_type_asset is not None:
            if self not in self._asset_function_type_asset._asset_function_asset_models:
                self._asset_function_type_asset._asset_function_asset_models.append(self)

    asset_function_type_asset = property(get_asset_function_type_asset, set_asset_function_type_asset)
    # >>> asset_function_type_asset



class CabinetModel(AssetModel):
    """ Documentation for a type of Cabinet of a particular product model made by a manufacturer.
    """
    # <<< cabinet_model
    # @generated
    def __init__(self, cabinets=None, cabinet_type_asset=None, **kw_args):
        """ Initialises a new 'CabinetModel' instance.
        """
        
        self._cabinets = []
        if cabinets is None:
            self.cabinets = []
        else:
            self.cabinets = cabinets
        self._cabinet_type_asset = None
        self.cabinet_type_asset = cabinet_type_asset

        super(CabinetModel, self).__init__(**kw_args)
    # >>> cabinet_model
        
    # <<< cabinets
    # @generated
    def get_cabinets(self):
        """ 
        """
        return self._cabinets

    def set_cabinets(self, value):
        for x in self._cabinets:
            x.cabinet_model = None
        for y in value:
            y.cabinet_model = self
        self._cabinets = value
            
    cabinets = property(get_cabinets, set_cabinets)
    
    def add_cabinets(self, *cabinets):
        for obj in cabinets:
            obj._cabinet_model = self
            if obj not in self._cabinets:
                self._cabinets.append(obj)
        
    def remove_cabinets(self, *cabinets):
        for obj in cabinets:
            obj._cabinet_model = None
            self._cabinets.remove(obj)
    # >>> cabinets

    # <<< cabinet_type_asset
    # @generated
    def get_cabinet_type_asset(self):
        """ 
        """
        return self._cabinet_type_asset

    def set_cabinet_type_asset(self, value):
        if self._cabinet_type_asset is not None:
            filtered = [x for x in self.cabinet_type_asset.cabinet_models if x != self]
            self._cabinet_type_asset._cabinet_models = filtered
            
        self._cabinet_type_asset = value
        if self._cabinet_type_asset is not None:
            if self not in self._cabinet_type_asset._cabinet_models:
                self._cabinet_type_asset._cabinet_models.append(self)

    cabinet_type_asset = property(get_cabinet_type_asset, set_cabinet_type_asset)
    # >>> cabinet_type_asset



class PoleModel(AssetModel):
    """ A type of pole supplied by a given manufacturer.
    """
    # <<< pole_model
    # @generated
    def __init__(self, species_type='', classification='', poles=None, pole_type_asset=None, **kw_args):
        """ Initialises a new 'PoleModel' instance.
        """
        # Pole species. Aluminum, Aluminum Davit, Concrete, Fiberglass, Galvanized Davit, Galvanized, Steel Davit Primed, Steel Davit, Steel Standard Primed, Steel, Truncated, Wood-Treated, Wood-Hard, Wood-Salt Treated, Wood-Soft, Wood, Other, Unknown. 
        self.species_type = species_type
        # Pole class: 1, 2, 3, 4, 5, 6, 7, H1, H2, Other, Unknown. 
        self.classification = classification
        
        self._poles = []
        if poles is None:
            self.poles = []
        else:
            self.poles = poles
        self._pole_type_asset = None
        self.pole_type_asset = pole_type_asset

        super(PoleModel, self).__init__(**kw_args)
    # >>> pole_model
        
    # <<< poles
    # @generated
    def get_poles(self):
        """ 
        """
        return self._poles

    def set_poles(self, value):
        for x in self._poles:
            x.pole_model = None
        for y in value:
            y.pole_model = self
        self._poles = value
            
    poles = property(get_poles, set_poles)
    
    def add_poles(self, *poles):
        for obj in poles:
            obj._pole_model = self
            if obj not in self._poles:
                self._poles.append(obj)
        
    def remove_poles(self, *poles):
        for obj in poles:
            obj._pole_model = None
            self._poles.remove(obj)
    # >>> poles

    # <<< pole_type_asset
    # @generated
    def get_pole_type_asset(self):
        """ 
        """
        return self._pole_type_asset

    def set_pole_type_asset(self, value):
        if self._pole_type_asset is not None:
            filtered = [x for x in self.pole_type_asset.pole_models if x != self]
            self._pole_type_asset._pole_models = filtered
            
        self._pole_type_asset = value
        if self._pole_type_asset is not None:
            if self not in self._pole_type_asset._pole_models:
                self._pole_type_asset._pole_models.append(self)

    pole_type_asset = property(get_pole_type_asset, set_pole_type_asset)
    # >>> pole_type_asset



class TowerAssetModel(AssetModel):
    """ A type of tower supplied by a given manufacturer or constructed from a common design.
    """
    # <<< tower_asset_model
    # @generated
    def __init__(self, towers=None, tower_type_asset=None, **kw_args):
        """ Initialises a new 'TowerAssetModel' instance.
        """
        
        self._towers = []
        if towers is None:
            self.towers = []
        else:
            self.towers = towers
        self._tower_type_asset = None
        self.tower_type_asset = tower_type_asset

        super(TowerAssetModel, self).__init__(**kw_args)
    # >>> tower_asset_model
        
    # <<< towers
    # @generated
    def get_towers(self):
        """ 
        """
        return self._towers

    def set_towers(self, value):
        for x in self._towers:
            x.tower_asset_model = None
        for y in value:
            y.tower_asset_model = self
        self._towers = value
            
    towers = property(get_towers, set_towers)
    
    def add_towers(self, *towers):
        for obj in towers:
            obj._tower_asset_model = self
            if obj not in self._towers:
                self._towers.append(obj)
        
    def remove_towers(self, *towers):
        for obj in towers:
            obj._tower_asset_model = None
            self._towers.remove(obj)
    # >>> towers

    # <<< tower_type_asset
    # @generated
    def get_tower_type_asset(self):
        """ 
        """
        return self._tower_type_asset

    def set_tower_type_asset(self, value):
        if self._tower_type_asset is not None:
            filtered = [x for x in self.tower_type_asset.tower_asset_models if x != self]
            self._tower_type_asset._tower_asset_models = filtered
            
        self._tower_type_asset = value
        if self._tower_type_asset is not None:
            if self not in self._tower_type_asset._tower_asset_models:
                self._tower_type_asset._tower_asset_models.append(self)

    tower_type_asset = property(get_tower_type_asset, set_tower_type_asset)
    # >>> tower_type_asset



class ElectricalAssetModel(AssetModel):
    """ Documentation for a type of ElectricalAsset of a particular product model made by a manufacturer.
    """
    # <<< electrical_asset_model
    # @generated
    def __init__(self, electrical_info_roles=None, **kw_args):
        """ Initialises a new 'ElectricalAssetModel' instance.
        """
        
        self._electrical_info_roles = []
        if electrical_info_roles is None:
            self.electrical_info_roles = []
        else:
            self.electrical_info_roles = electrical_info_roles

        super(ElectricalAssetModel, self).__init__(**kw_args)
    # >>> electrical_asset_model
        
    # <<< electrical_info_roles
    # @generated
    def get_electrical_info_roles(self):
        """ 
        """
        return self._electrical_info_roles

    def set_electrical_info_roles(self, value):
        for x in self._electrical_info_roles:
            x.electrical_asset_model = None
        for y in value:
            y.electrical_asset_model = self
        self._electrical_info_roles = value
            
    electrical_info_roles = property(get_electrical_info_roles, set_electrical_info_roles)
    
    def add_electrical_info_roles(self, *electrical_info_roles):
        for obj in electrical_info_roles:
            obj._electrical_asset_model = self
            if obj not in self._electrical_info_roles:
                self._electrical_info_roles.append(obj)
        
    def remove_electrical_info_roles(self, *electrical_info_roles):
        for obj in electrical_info_roles:
            obj._electrical_asset_model = None
            self._electrical_info_roles.remove(obj)
    # >>> electrical_info_roles



class VehicleAssetModel(AssetModel):
    """ Documentation for a type of a vehicle of a particular product model made by a manufacturer.
    """
    # <<< vehicle_asset_model
    # @generated
    def __init__(self, vehicle_type_asset=None, vehicles=None, **kw_args):
        """ Initialises a new 'VehicleAssetModel' instance.
        """
        
        self._vehicle_type_asset = None
        self.vehicle_type_asset = vehicle_type_asset
        self._vehicles = []
        if vehicles is None:
            self.vehicles = []
        else:
            self.vehicles = vehicles

        super(VehicleAssetModel, self).__init__(**kw_args)
    # >>> vehicle_asset_model
        
    # <<< vehicle_type_asset
    # @generated
    def get_vehicle_type_asset(self):
        """ 
        """
        return self._vehicle_type_asset

    def set_vehicle_type_asset(self, value):
        if self._vehicle_type_asset is not None:
            filtered = [x for x in self.vehicle_type_asset.vehicle_asset_models if x != self]
            self._vehicle_type_asset._vehicle_asset_models = filtered
            
        self._vehicle_type_asset = value
        if self._vehicle_type_asset is not None:
            if self not in self._vehicle_type_asset._vehicle_asset_models:
                self._vehicle_type_asset._vehicle_asset_models.append(self)

    vehicle_type_asset = property(get_vehicle_type_asset, set_vehicle_type_asset)
    # >>> vehicle_type_asset

    # <<< vehicles
    # @generated
    def get_vehicles(self):
        """ 
        """
        return self._vehicles

    def set_vehicles(self, value):
        for x in self._vehicles:
            x.vehicle_asset_model = None
        for y in value:
            y.vehicle_asset_model = self
        self._vehicles = value
            
    vehicles = property(get_vehicles, set_vehicles)
    
    def add_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._vehicle_asset_model = self
            if obj not in self._vehicles:
                self._vehicles.append(obj)
        
    def remove_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._vehicle_asset_model = None
            self._vehicles.remove(obj)
    # >>> vehicles



class AssetModelCatalogue(IdentifiedObject):
    """ Catalogue of available types of products and materials that are used to build or install, maintain or operate an Asset. Each catalogue item is for a specific product (AssetModel) available from a specific supplier.
    """
    # <<< asset_model_catalogue
    # @generated
    def __init__(self, status=None, asset_model_catalogue_items=None, **kw_args):
        """ Initialises a new 'AssetModelCatalogue' instance.
        """
        
        self.status = status
        self._asset_model_catalogue_items = []
        if asset_model_catalogue_items is None:
            self.asset_model_catalogue_items = []
        else:
            self.asset_model_catalogue_items = asset_model_catalogue_items

        super(AssetModelCatalogue, self).__init__(**kw_args)
    # >>> asset_model_catalogue
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< asset_model_catalogue_items
    # @generated
    def get_asset_model_catalogue_items(self):
        """ 
        """
        return self._asset_model_catalogue_items

    def set_asset_model_catalogue_items(self, value):
        for x in self._asset_model_catalogue_items:
            x.asset_model_catalogue = None
        for y in value:
            y.asset_model_catalogue = self
        self._asset_model_catalogue_items = value
            
    asset_model_catalogue_items = property(get_asset_model_catalogue_items, set_asset_model_catalogue_items)
    
    def add_asset_model_catalogue_items(self, *asset_model_catalogue_items):
        for obj in asset_model_catalogue_items:
            obj._asset_model_catalogue = self
            if obj not in self._asset_model_catalogue_items:
                self._asset_model_catalogue_items.append(obj)
        
    def remove_asset_model_catalogue_items(self, *asset_model_catalogue_items):
        for obj in asset_model_catalogue_items:
            obj._asset_model_catalogue = None
            self._asset_model_catalogue_items.remove(obj)
    # >>> asset_model_catalogue_items



class ToolAssetModel(AssetModel):
    """ Documentation for a type of a tool of a particular product model made by a manufacturer.
    """
    # <<< tool_asset_model
    # @generated
    def __init__(self, tool_type_asset=None, tools=None, **kw_args):
        """ Initialises a new 'ToolAssetModel' instance.
        """
        
        self._tool_type_asset = None
        self.tool_type_asset = tool_type_asset
        self._tools = []
        if tools is None:
            self.tools = []
        else:
            self.tools = tools

        super(ToolAssetModel, self).__init__(**kw_args)
    # >>> tool_asset_model
        
    # <<< tool_type_asset
    # @generated
    def get_tool_type_asset(self):
        """ 
        """
        return self._tool_type_asset

    def set_tool_type_asset(self, value):
        if self._tool_type_asset is not None:
            filtered = [x for x in self.tool_type_asset.tool_asset_models if x != self]
            self._tool_type_asset._tool_asset_models = filtered
            
        self._tool_type_asset = value
        if self._tool_type_asset is not None:
            if self not in self._tool_type_asset._tool_asset_models:
                self._tool_type_asset._tool_asset_models.append(self)

    tool_type_asset = property(get_tool_type_asset, set_tool_type_asset)
    # >>> tool_type_asset

    # <<< tools
    # @generated
    def get_tools(self):
        """ 
        """
        return self._tools

    def set_tools(self, value):
        for x in self._tools:
            x.tool_asset_model = None
        for y in value:
            y.tool_asset_model = self
        self._tools = value
            
    tools = property(get_tools, set_tools)
    
    def add_tools(self, *tools):
        for obj in tools:
            obj._tool_asset_model = self
            if obj not in self._tools:
                self._tools.append(obj)
        
    def remove_tools(self, *tools):
        for obj in tools:
            obj._tool_asset_model = None
            self._tools.remove(obj)
    # >>> tools



class TransformerAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a transformer of a particular product model made by a manufacturer.
    """
    # <<< transformer_asset_model
    # @generated
    def __init__(self, core_kind='shell', winding_insulation_kind='thermally_upgraded_paper', auto_transformer=False, core_coils_weight=0.0, solid_insulation_weight=0.0, neutral_bil=0.0, oil_preservation_kind='nitrogen_blanket', transformer_info=None, transformer_type_asset=None, transformer_assets=None, **kw_args):
        """ Initialises a new 'TransformerAssetModel' instance.
        """
        # Core kind of this transformer product. Values are: "shell", "core"
        self.core_kind = core_kind
        # Type of insultation used for transformer windings: Paper, Thermally Upgraded Paper, Nomex, other Values are: "thermally_upgraded_paper", "other", "nomex", "paper"
        self.winding_insulation_kind = winding_insulation_kind
        # True if this is an autotransformer, false otherwise. 
        self.auto_transformer = auto_transformer
        # Weight of core and coils in transformer. 
        self.core_coils_weight = core_coils_weight
        # Weight of solid insultation in transformer. 
        self.solid_insulation_weight = solid_insulation_weight
        # Basic Insulation Level of Neutral 
        self.neutral_bil = neutral_bil
        # Kind of oil preservation system. Values are: "nitrogen_blanket", "conservator", "free_breathing", "other"
        self.oil_preservation_kind = oil_preservation_kind
        
        self._transformer_info = None
        self.transformer_info = transformer_info
        self._transformer_type_asset = None
        self.transformer_type_asset = transformer_type_asset
        self._transformer_assets = []
        if transformer_assets is None:
            self.transformer_assets = []
        else:
            self.transformer_assets = transformer_assets

        super(TransformerAssetModel, self).__init__(**kw_args)
    # >>> transformer_asset_model
        
    # <<< transformer_info
    # @generated
    def get_transformer_info(self):
        """ 
        """
        return self._transformer_info

    def set_transformer_info(self, value):
        if self._transformer_info is not None:
            filtered = [x for x in self.transformer_info.transformer_asset_models if x != self]
            self._transformer_info._transformer_asset_models = filtered
            
        self._transformer_info = value
        if self._transformer_info is not None:
            if self not in self._transformer_info._transformer_asset_models:
                self._transformer_info._transformer_asset_models.append(self)

    transformer_info = property(get_transformer_info, set_transformer_info)
    # >>> transformer_info

    # <<< transformer_type_asset
    # @generated
    def get_transformer_type_asset(self):
        """ 
        """
        return self._transformer_type_asset

    def set_transformer_type_asset(self, value):
        if self._transformer_type_asset is not None:
            filtered = [x for x in self.transformer_type_asset.transformer_asset_models if x != self]
            self._transformer_type_asset._transformer_asset_models = filtered
            
        self._transformer_type_asset = value
        if self._transformer_type_asset is not None:
            if self not in self._transformer_type_asset._transformer_asset_models:
                self._transformer_type_asset._transformer_asset_models.append(self)

    transformer_type_asset = property(get_transformer_type_asset, set_transformer_type_asset)
    # >>> transformer_type_asset

    # <<< transformer_assets
    # @generated
    def get_transformer_assets(self):
        """ 
        """
        return self._transformer_assets

    def set_transformer_assets(self, value):
        for x in self._transformer_assets:
            x.transformer_asset_model = None
        for y in value:
            y.transformer_asset_model = self
        self._transformer_assets = value
            
    transformer_assets = property(get_transformer_assets, set_transformer_assets)
    
    def add_transformer_assets(self, *transformer_assets):
        for obj in transformer_assets:
            obj._transformer_asset_model = self
            if obj not in self._transformer_assets:
                self._transformer_assets.append(obj)
        
    def remove_transformer_assets(self, *transformer_assets):
        for obj in transformer_assets:
            obj._transformer_asset_model = None
            self._transformer_assets.remove(obj)
    # >>> transformer_assets



class GeneratorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of generation equipment of a particular product model made by a manufacturer.
    """
    # <<< generator_asset_model
    # @generated
    def __init__(self, generator_type_asset=None, generator_assets=None, **kw_args):
        """ Initialises a new 'GeneratorAssetModel' instance.
        """
        
        self._generator_type_asset = None
        self.generator_type_asset = generator_type_asset
        self._generator_assets = []
        if generator_assets is None:
            self.generator_assets = []
        else:
            self.generator_assets = generator_assets

        super(GeneratorAssetModel, self).__init__(**kw_args)
    # >>> generator_asset_model
        
    # <<< generator_type_asset
    # @generated
    def get_generator_type_asset(self):
        """ 
        """
        return self._generator_type_asset

    def set_generator_type_asset(self, value):
        if self._generator_type_asset is not None:
            filtered = [x for x in self.generator_type_asset.generator_asset_models if x != self]
            self._generator_type_asset._generator_asset_models = filtered
            
        self._generator_type_asset = value
        if self._generator_type_asset is not None:
            if self not in self._generator_type_asset._generator_asset_models:
                self._generator_type_asset._generator_asset_models.append(self)

    generator_type_asset = property(get_generator_type_asset, set_generator_type_asset)
    # >>> generator_type_asset

    # <<< generator_assets
    # @generated
    def get_generator_assets(self):
        """ 
        """
        return self._generator_assets

    def set_generator_assets(self, value):
        for x in self._generator_assets:
            x.generator_asset_model = None
        for y in value:
            y.generator_asset_model = self
        self._generator_assets = value
            
    generator_assets = property(get_generator_assets, set_generator_assets)
    
    def add_generator_assets(self, *generator_assets):
        for obj in generator_assets:
            obj._generator_asset_model = self
            if obj not in self._generator_assets:
                self._generator_assets.append(obj)
        
    def remove_generator_assets(self, *generator_assets):
        for obj in generator_assets:
            obj._generator_asset_model = None
            self._generator_assets.remove(obj)
    # >>> generator_assets



class StreetlightAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a streelight of a particular product model made by a manufacturer.
    """
    # <<< streetlight_asset_model
    # @generated
    def __init__(self, lamp_kind='high_pressure_sodium', light_rating=0.0, streetlight_type_assets=None, streetlights=None, **kw_args):
        """ Initialises a new 'StreetlightAssetModel' instance.
        """
        # Lamp kind supplied from manufacturer (vs. one that has been replaced in the field). Values are: "high_pressure_sodium", "other", "mercury_vapor", "metal_halide"
        self.lamp_kind = lamp_kind
        # Power rating of light as supplied by the manufacturer. 
        self.light_rating = light_rating
        
        self._streetlight_type_assets = []
        if streetlight_type_assets is None:
            self.streetlight_type_assets = []
        else:
            self.streetlight_type_assets = streetlight_type_assets
        self._streetlights = []
        if streetlights is None:
            self.streetlights = []
        else:
            self.streetlights = streetlights

        super(StreetlightAssetModel, self).__init__(**kw_args)
    # >>> streetlight_asset_model
        
    # <<< streetlight_type_assets
    # @generated
    def get_streetlight_type_assets(self):
        """ 
        """
        return self._streetlight_type_assets

    def set_streetlight_type_assets(self, value):
        for p in self.streetlight_type_assets:
            filtered = [q for q in p.streetlight_asset_models if q != self]
            p._streetlight_asset_models = filtered
        for r in value:
            if self not in r._streetlight_asset_models:
                r._streetlight_asset_models.append(self)
        self._streetlight_type_assets = value
            
    streetlight_type_assets = property(get_streetlight_type_assets, set_streetlight_type_assets)
    
    def add_streetlight_type_assets(self, *streetlight_type_assets):
        for obj in streetlight_type_assets:
            if self not in obj._streetlight_asset_models:
                obj._streetlight_asset_models.append(self)
            self._streetlight_type_assets.append(obj)
        
    def remove_streetlight_type_assets(self, *streetlight_type_assets):
        for obj in streetlight_type_assets:
            if self in obj._streetlight_asset_models:
                obj._streetlight_asset_models.remove(self)
            self._streetlight_type_assets.remove(obj)
    # >>> streetlight_type_assets

    # <<< streetlights
    # @generated
    def get_streetlights(self):
        """ 
        """
        return self._streetlights

    def set_streetlights(self, value):
        for x in self._streetlights:
            x.streetlight_asset_model = None
        for y in value:
            y.streetlight_asset_model = self
        self._streetlights = value
            
    streetlights = property(get_streetlights, set_streetlights)
    
    def add_streetlights(self, *streetlights):
        for obj in streetlights:
            obj._streetlight_asset_model = self
            if obj not in self._streetlights:
                self._streetlights.append(obj)
        
    def remove_streetlights(self, *streetlights):
        for obj in streetlights:
            obj._streetlight_asset_model = None
            self._streetlights.remove(obj)
    # >>> streetlights



class ProtectionEquipmentAssetModel(ElectricalAssetModel):
    """ Documentation for a type of protection equipment asset of a particular product model made by a manufacturer.
    """
    # <<< protection_equipment_asset_model
    # @generated
    def __init__(self, protection_equipment_assets=None, protection_equipment_type_asset=None, **kw_args):
        """ Initialises a new 'ProtectionEquipmentAssetModel' instance.
        """
        
        self._protection_equipment_assets = []
        if protection_equipment_assets is None:
            self.protection_equipment_assets = []
        else:
            self.protection_equipment_assets = protection_equipment_assets
        self._protection_equipment_type_asset = None
        self.protection_equipment_type_asset = protection_equipment_type_asset

        super(ProtectionEquipmentAssetModel, self).__init__(**kw_args)
    # >>> protection_equipment_asset_model
        
    # <<< protection_equipment_assets
    # @generated
    def get_protection_equipment_assets(self):
        """ 
        """
        return self._protection_equipment_assets

    def set_protection_equipment_assets(self, value):
        for x in self._protection_equipment_assets:
            x.protection_equipment_asset_model = None
        for y in value:
            y.protection_equipment_asset_model = self
        self._protection_equipment_assets = value
            
    protection_equipment_assets = property(get_protection_equipment_assets, set_protection_equipment_assets)
    
    def add_protection_equipment_assets(self, *protection_equipment_assets):
        for obj in protection_equipment_assets:
            obj._protection_equipment_asset_model = self
            if obj not in self._protection_equipment_assets:
                self._protection_equipment_assets.append(obj)
        
    def remove_protection_equipment_assets(self, *protection_equipment_assets):
        for obj in protection_equipment_assets:
            obj._protection_equipment_asset_model = None
            self._protection_equipment_assets.remove(obj)
    # >>> protection_equipment_assets

    # <<< protection_equipment_type_asset
    # @generated
    def get_protection_equipment_type_asset(self):
        """ 
        """
        return self._protection_equipment_type_asset

    def set_protection_equipment_type_asset(self, value):
        if self._protection_equipment_type_asset is not None:
            filtered = [x for x in self.protection_equipment_type_asset.protection_equipment_asset_models if x != self]
            self._protection_equipment_type_asset._protection_equipment_asset_models = filtered
            
        self._protection_equipment_type_asset = value
        if self._protection_equipment_type_asset is not None:
            if self not in self._protection_equipment_type_asset._protection_equipment_asset_models:
                self._protection_equipment_type_asset._protection_equipment_asset_models.append(self)

    protection_equipment_type_asset = property(get_protection_equipment_type_asset, set_protection_equipment_type_asset)
    # >>> protection_equipment_type_asset



class FaultIndicatorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an FaultIndicator asset of a particular product model made by a manufacturer.
    """
    # <<< fault_indicator_asset_model
    # @generated
    def __init__(self, fault_indicator_type_asset=None, fault_indicator_assets=None, **kw_args):
        """ Initialises a new 'FaultIndicatorAssetModel' instance.
        """
        
        self._fault_indicator_type_asset = None
        self.fault_indicator_type_asset = fault_indicator_type_asset
        self._fault_indicator_assets = []
        if fault_indicator_assets is None:
            self.fault_indicator_assets = []
        else:
            self.fault_indicator_assets = fault_indicator_assets

        super(FaultIndicatorAssetModel, self).__init__(**kw_args)
    # >>> fault_indicator_asset_model
        
    # <<< fault_indicator_type_asset
    # @generated
    def get_fault_indicator_type_asset(self):
        """ 
        """
        return self._fault_indicator_type_asset

    def set_fault_indicator_type_asset(self, value):
        if self._fault_indicator_type_asset is not None:
            filtered = [x for x in self.fault_indicator_type_asset.fault_indicator_asset_models if x != self]
            self._fault_indicator_type_asset._fault_indicator_asset_models = filtered
            
        self._fault_indicator_type_asset = value
        if self._fault_indicator_type_asset is not None:
            if self not in self._fault_indicator_type_asset._fault_indicator_asset_models:
                self._fault_indicator_type_asset._fault_indicator_asset_models.append(self)

    fault_indicator_type_asset = property(get_fault_indicator_type_asset, set_fault_indicator_type_asset)
    # >>> fault_indicator_type_asset

    # <<< fault_indicator_assets
    # @generated
    def get_fault_indicator_assets(self):
        """ 
        """
        return self._fault_indicator_assets

    def set_fault_indicator_assets(self, value):
        for x in self._fault_indicator_assets:
            x.fault_indicator_asset_model = None
        for y in value:
            y.fault_indicator_asset_model = self
        self._fault_indicator_assets = value
            
    fault_indicator_assets = property(get_fault_indicator_assets, set_fault_indicator_assets)
    
    def add_fault_indicator_assets(self, *fault_indicator_assets):
        for obj in fault_indicator_assets:
            obj._fault_indicator_asset_model = self
            if obj not in self._fault_indicator_assets:
                self._fault_indicator_assets.append(obj)
        
    def remove_fault_indicator_assets(self, *fault_indicator_assets):
        for obj in fault_indicator_assets:
            obj._fault_indicator_asset_model = None
            self._fault_indicator_assets.remove(obj)
    # >>> fault_indicator_assets



class LinearConductorAssetModel(ElectricalAssetModel):
    """ A type of linear conductor made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    # <<< linear_conductor_asset_model
    # @generated
    def __init__(self, insulated=False, usage='transmission', insulation_kind='unbelted_pilc', radius=0, g_mr=0.0, size='', linear_conductor_type_asset=None, linear_conductor_assets=None, **kw_args):
        """ Initialises a new 'LinearConductorAssetModel' instance.
        """
        # True if conductor is insultated. 
        self.insulated = insulated
        # Usage of this linear conductor product. Values are: "transmission", "other", "distribution"
        self.usage = usage
        # Kind of insulation material. Values are: "unbelted_pilc", "varnished_cambric_cloth", "tree_resistant_high_molecular_weight_polyethylene", "butyl", "low_capacitance_rubber", "high_molecular_weight_polyethylene", "other", "tree_retardant_crosslinked_polyethylene", "crosslinked_polyethylene", "silicon_rubber", "oil_paper", "ethylene_propylene_rubber", "varnished_dacron_glass", "rubber", "ozone_resistant_rubber", "belted_pilc", "asbestos_and_varnished_cambric"
        self.insulation_kind = insulation_kind
        # Radius of the conductor 
        self.radius = radius
        # Geometric Mean Radius. If the conductor were replaced by a thin walled tube of radius gMR then its reactance would be identical to that of the actual conductor 
        self.g_mr = g_mr
        # Commonly referred to size for this type of conductor. 
        self.size = size
        
        self._linear_conductor_type_asset = None
        self.linear_conductor_type_asset = linear_conductor_type_asset
        self._linear_conductor_assets = []
        if linear_conductor_assets is None:
            self.linear_conductor_assets = []
        else:
            self.linear_conductor_assets = linear_conductor_assets

        super(LinearConductorAssetModel, self).__init__(**kw_args)
    # >>> linear_conductor_asset_model
        
    # <<< linear_conductor_type_asset
    # @generated
    def get_linear_conductor_type_asset(self):
        """ 
        """
        return self._linear_conductor_type_asset

    def set_linear_conductor_type_asset(self, value):
        if self._linear_conductor_type_asset is not None:
            filtered = [x for x in self.linear_conductor_type_asset.linear_conductor_asset_models if x != self]
            self._linear_conductor_type_asset._linear_conductor_asset_models = filtered
            
        self._linear_conductor_type_asset = value
        if self._linear_conductor_type_asset is not None:
            if self not in self._linear_conductor_type_asset._linear_conductor_asset_models:
                self._linear_conductor_type_asset._linear_conductor_asset_models.append(self)

    linear_conductor_type_asset = property(get_linear_conductor_type_asset, set_linear_conductor_type_asset)
    # >>> linear_conductor_type_asset

    # <<< linear_conductor_assets
    # @generated
    def get_linear_conductor_assets(self):
        """ 
        """
        return self._linear_conductor_assets

    def set_linear_conductor_assets(self, value):
        for x in self._linear_conductor_assets:
            x.linear_conductor_asset_model = None
        for y in value:
            y.linear_conductor_asset_model = self
        self._linear_conductor_assets = value
            
    linear_conductor_assets = property(get_linear_conductor_assets, set_linear_conductor_assets)
    
    def add_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
            obj._linear_conductor_asset_model = self
            if obj not in self._linear_conductor_assets:
                self._linear_conductor_assets.append(obj)
        
    def remove_linear_conductor_assets(self, *linear_conductor_assets):
        for obj in linear_conductor_assets:
            obj._linear_conductor_asset_model = None
            self._linear_conductor_assets.remove(obj)
    # >>> linear_conductor_assets



class SwitchAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a switch asset of a particular product model made by a manufacturer.
    """
    # <<< switch_asset_model
    # @generated
    def __init__(self, switch_info=None, switch_type_asset=None, switch_assets=None, **kw_args):
        """ Initialises a new 'SwitchAssetModel' instance.
        """
        
        self._switch_info = None
        self.switch_info = switch_info
        self._switch_type_asset = None
        self.switch_type_asset = switch_type_asset
        self._switch_assets = []
        if switch_assets is None:
            self.switch_assets = []
        else:
            self.switch_assets = switch_assets

        super(SwitchAssetModel, self).__init__(**kw_args)
    # >>> switch_asset_model
        
    # <<< switch_info
    # @generated
    def get_switch_info(self):
        """ 
        """
        return self._switch_info

    def set_switch_info(self, value):
        if self._switch_info is not None:
            self._switch_info._switch_asset_model = None
            
        self._switch_info = value
        if self._switch_info is not None:
            self._switch_info._switch_asset_model = self
            
    switch_info = property(get_switch_info, set_switch_info)
    # >>> switch_info

    # <<< switch_type_asset
    # @generated
    def get_switch_type_asset(self):
        """ 
        """
        return self._switch_type_asset

    def set_switch_type_asset(self, value):
        if self._switch_type_asset is not None:
            filtered = [x for x in self.switch_type_asset.switch_asset_models if x != self]
            self._switch_type_asset._switch_asset_models = filtered
            
        self._switch_type_asset = value
        if self._switch_type_asset is not None:
            if self not in self._switch_type_asset._switch_asset_models:
                self._switch_type_asset._switch_asset_models.append(self)

    switch_type_asset = property(get_switch_type_asset, set_switch_type_asset)
    # >>> switch_type_asset

    # <<< switch_assets
    # @generated
    def get_switch_assets(self):
        """ 
        """
        return self._switch_assets

    def set_switch_assets(self, value):
        for x in self._switch_assets:
            x.switch_asset_model = None
        for y in value:
            y.switch_asset_model = self
        self._switch_assets = value
            
    switch_assets = property(get_switch_assets, set_switch_assets)
    
    def add_switch_assets(self, *switch_assets):
        for obj in switch_assets:
            obj._switch_asset_model = self
            if obj not in self._switch_assets:
                self._switch_assets.append(obj)
        
    def remove_switch_assets(self, *switch_assets):
        for obj in switch_assets:
            obj._switch_asset_model = None
            self._switch_assets.remove(obj)
    # >>> switch_assets



class PotentialTransformerAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Potential Transformer (PT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    # <<< potential_transformer_asset_model
    # @generated
    def __init__(self, potential_transformer_type_asset=None, potential_transformer_assets=None, potential_transformer_info=None, **kw_args):
        """ Initialises a new 'PotentialTransformerAssetModel' instance.
        """
        
        self._potential_transformer_type_asset = None
        self.potential_transformer_type_asset = potential_transformer_type_asset
        self._potential_transformer_assets = []
        if potential_transformer_assets is None:
            self.potential_transformer_assets = []
        else:
            self.potential_transformer_assets = potential_transformer_assets
        self._potential_transformer_info = None
        self.potential_transformer_info = potential_transformer_info

        super(PotentialTransformerAssetModel, self).__init__(**kw_args)
    # >>> potential_transformer_asset_model
        
    # <<< potential_transformer_type_asset
    # @generated
    def get_potential_transformer_type_asset(self):
        """ 
        """
        return self._potential_transformer_type_asset

    def set_potential_transformer_type_asset(self, value):
        if self._potential_transformer_type_asset is not None:
            filtered = [x for x in self.potential_transformer_type_asset.potential_transformer_asset_models if x != self]
            self._potential_transformer_type_asset._potential_transformer_asset_models = filtered
            
        self._potential_transformer_type_asset = value
        if self._potential_transformer_type_asset is not None:
            if self not in self._potential_transformer_type_asset._potential_transformer_asset_models:
                self._potential_transformer_type_asset._potential_transformer_asset_models.append(self)

    potential_transformer_type_asset = property(get_potential_transformer_type_asset, set_potential_transformer_type_asset)
    # >>> potential_transformer_type_asset

    # <<< potential_transformer_assets
    # @generated
    def get_potential_transformer_assets(self):
        """ 
        """
        return self._potential_transformer_assets

    def set_potential_transformer_assets(self, value):
        for x in self._potential_transformer_assets:
            x.potential_transformer_asset_model = None
        for y in value:
            y.potential_transformer_asset_model = self
        self._potential_transformer_assets = value
            
    potential_transformer_assets = property(get_potential_transformer_assets, set_potential_transformer_assets)
    
    def add_potential_transformer_assets(self, *potential_transformer_assets):
        for obj in potential_transformer_assets:
            obj._potential_transformer_asset_model = self
            if obj not in self._potential_transformer_assets:
                self._potential_transformer_assets.append(obj)
        
    def remove_potential_transformer_assets(self, *potential_transformer_assets):
        for obj in potential_transformer_assets:
            obj._potential_transformer_asset_model = None
            self._potential_transformer_assets.remove(obj)
    # >>> potential_transformer_assets

    # <<< potential_transformer_info
    # @generated
    def get_potential_transformer_info(self):
        """ 
        """
        return self._potential_transformer_info

    def set_potential_transformer_info(self, value):
        if self._potential_transformer_info is not None:
            filtered = [x for x in self.potential_transformer_info.potential_transformer_asset_models if x != self]
            self._potential_transformer_info._potential_transformer_asset_models = filtered
            
        self._potential_transformer_info = value
        if self._potential_transformer_info is not None:
            if self not in self._potential_transformer_info._potential_transformer_asset_models:
                self._potential_transformer_info._potential_transformer_asset_models.append(self)

    potential_transformer_info = property(get_potential_transformer_info, set_potential_transformer_info)
    # >>> potential_transformer_info



class ResistorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a resistor asset of a particular product model made by a manufacturer.
    """
    # <<< resistor_asset_model
    # @generated
    def __init__(self, resistor_assets=None, resistor_type_asset=None, **kw_args):
        """ Initialises a new 'ResistorAssetModel' instance.
        """
        
        self._resistor_assets = []
        if resistor_assets is None:
            self.resistor_assets = []
        else:
            self.resistor_assets = resistor_assets
        self._resistor_type_asset = None
        self.resistor_type_asset = resistor_type_asset

        super(ResistorAssetModel, self).__init__(**kw_args)
    # >>> resistor_asset_model
        
    # <<< resistor_assets
    # @generated
    def get_resistor_assets(self):
        """ 
        """
        return self._resistor_assets

    def set_resistor_assets(self, value):
        for x in self._resistor_assets:
            x.resistor_asset_model = None
        for y in value:
            y.resistor_asset_model = self
        self._resistor_assets = value
            
    resistor_assets = property(get_resistor_assets, set_resistor_assets)
    
    def add_resistor_assets(self, *resistor_assets):
        for obj in resistor_assets:
            obj._resistor_asset_model = self
            if obj not in self._resistor_assets:
                self._resistor_assets.append(obj)
        
    def remove_resistor_assets(self, *resistor_assets):
        for obj in resistor_assets:
            obj._resistor_asset_model = None
            self._resistor_assets.remove(obj)
    # >>> resistor_assets

    # <<< resistor_type_asset
    # @generated
    def get_resistor_type_asset(self):
        """ 
        """
        return self._resistor_type_asset

    def set_resistor_type_asset(self, value):
        if self._resistor_type_asset is not None:
            filtered = [x for x in self.resistor_type_asset.resistor_asset_models if x != self]
            self._resistor_type_asset._resistor_asset_models = filtered
            
        self._resistor_type_asset = value
        if self._resistor_type_asset is not None:
            if self not in self._resistor_type_asset._resistor_asset_models:
                self._resistor_type_asset._resistor_asset_models.append(self)

    resistor_type_asset = property(get_resistor_type_asset, set_resistor_type_asset)
    # >>> resistor_type_asset



class MeterAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a meter asset of a particular product model made by a manufacturer.
    """
    # <<< meter_asset_model
    # @generated
    def __init__(self, form='', load_profile_meter=False, kwh_meter=False, reactive_meter=False, wire_count=0, demand_meter=False, max_register_count=0, time_of_use_meter=False, k_vah_meter=False, k_h=0.0, q_meter=False, interval_data_meter=False, register_ratio=0.0, meter_type_asset=None, meter_assets=None, **kw_args):
        """ Initialises a new 'MeterAssetModel' instance.
        """
        # Meter form number. 
        self.form = form
        # True when the meter or the installed AMR option is capable of capturing kWh interval data. 
        self.load_profile_meter = load_profile_meter
        # True when the meter is capable of metering real energy in kWh. 
        self.kwh_meter = kwh_meter
        # True when the meter is capable of metering reactive energy in kVArh. 
        self.reactive_meter = reactive_meter
        # Number of wires. 
        self.wire_count = wire_count
        # True when the meter or installed AMR option is capable of capturing demand data. 
        self.demand_meter = demand_meter
        # Maximum number of registers this meter model can support. The actual number in use is based on the number of Registers associated with a given MeterAsset. 
        self.max_register_count = max_register_count
        # True when the meter or meter+AMR module are capable of offering TOU data. 
        self.time_of_use_meter = time_of_use_meter
        # True when the meter is capable of metering apparent energy in kVAh. 
        self.k_vah_meter = k_vah_meter
        # Meter kh (watthour) constant. This constant is the number of watthours that must be applied to the meter to cause one disk revolution for an electromechanical meter or the number of watthours represented by one increment pulse for an electronic meter. 
        self.k_h = k_h
        # True when the meter is capable of metering reactive energy in kQh. 
        self.q_meter = q_meter
        # True when the meter or the installed AMR option is capable of capturing interval data for a user selectable measurement (kWh, Volts, or some other billing or engineering quantity). 
        self.interval_data_meter = interval_data_meter
        # Meter register ratio. 
        self.register_ratio = register_ratio
        
        self._meter_type_asset = None
        self.meter_type_asset = meter_type_asset
        self._meter_assets = []
        if meter_assets is None:
            self.meter_assets = []
        else:
            self.meter_assets = meter_assets

        super(MeterAssetModel, self).__init__(**kw_args)
    # >>> meter_asset_model
        
    # <<< meter_type_asset
    # @generated
    def get_meter_type_asset(self):
        """ 
        """
        return self._meter_type_asset

    def set_meter_type_asset(self, value):
        if self._meter_type_asset is not None:
            filtered = [x for x in self.meter_type_asset.meter_asset_models if x != self]
            self._meter_type_asset._meter_asset_models = filtered
            
        self._meter_type_asset = value
        if self._meter_type_asset is not None:
            if self not in self._meter_type_asset._meter_asset_models:
                self._meter_type_asset._meter_asset_models.append(self)

    meter_type_asset = property(get_meter_type_asset, set_meter_type_asset)
    # >>> meter_type_asset

    # <<< meter_assets
    # @generated
    def get_meter_assets(self):
        """ 
        """
        return self._meter_assets

    def set_meter_assets(self, value):
        for x in self._meter_assets:
            x.meter_asset_model = None
        for y in value:
            y.meter_asset_model = self
        self._meter_assets = value
            
    meter_assets = property(get_meter_assets, set_meter_assets)
    
    def add_meter_assets(self, *meter_assets):
        for obj in meter_assets:
            obj._meter_asset_model = self
            if obj not in self._meter_assets:
                self._meter_assets.append(obj)
        
    def remove_meter_assets(self, *meter_assets):
        for obj in meter_assets:
            obj._meter_asset_model = None
            self._meter_assets.remove(obj)
    # >>> meter_assets



class SurgeProtectorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an SurgeProtector asset of a particular product model made by a manufacturer.
    """
    # <<< surge_protector_asset_model
    # @generated
    def __init__(self, surge_protector_assets=None, surge_protector_type_asset=None, **kw_args):
        """ Initialises a new 'SurgeProtectorAssetModel' instance.
        """
        
        self._surge_protector_assets = []
        if surge_protector_assets is None:
            self.surge_protector_assets = []
        else:
            self.surge_protector_assets = surge_protector_assets
        self._surge_protector_type_asset = None
        self.surge_protector_type_asset = surge_protector_type_asset

        super(SurgeProtectorAssetModel, self).__init__(**kw_args)
    # >>> surge_protector_asset_model
        
    # <<< surge_protector_assets
    # @generated
    def get_surge_protector_assets(self):
        """ 
        """
        return self._surge_protector_assets

    def set_surge_protector_assets(self, value):
        for x in self._surge_protector_assets:
            x.surge_protector_asset_model = None
        for y in value:
            y.surge_protector_asset_model = self
        self._surge_protector_assets = value
            
    surge_protector_assets = property(get_surge_protector_assets, set_surge_protector_assets)
    
    def add_surge_protector_assets(self, *surge_protector_assets):
        for obj in surge_protector_assets:
            obj._surge_protector_asset_model = self
            if obj not in self._surge_protector_assets:
                self._surge_protector_assets.append(obj)
        
    def remove_surge_protector_assets(self, *surge_protector_assets):
        for obj in surge_protector_assets:
            obj._surge_protector_asset_model = None
            self._surge_protector_assets.remove(obj)
    # >>> surge_protector_assets

    # <<< surge_protector_type_asset
    # @generated
    def get_surge_protector_type_asset(self):
        """ 
        """
        return self._surge_protector_type_asset

    def set_surge_protector_type_asset(self, value):
        if self._surge_protector_type_asset is not None:
            filtered = [x for x in self.surge_protector_type_asset.surge_protector_asset_models if x != self]
            self._surge_protector_type_asset._surge_protector_asset_models = filtered
            
        self._surge_protector_type_asset = value
        if self._surge_protector_type_asset is not None:
            if self not in self._surge_protector_type_asset._surge_protector_asset_models:
                self._surge_protector_type_asset._surge_protector_asset_models.append(self)

    surge_protector_type_asset = property(get_surge_protector_type_asset, set_surge_protector_type_asset)
    # >>> surge_protector_type_asset



class OverheadConductorAssetModel(LinearConductorAssetModel):
    """ A type of linear conductor model made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    # <<< overhead_conductor_asset_model
    # @generated
    def __init__(self, overhead_conductor_type_asset=None, **kw_args):
        """ Initialises a new 'OverheadConductorAssetModel' instance.
        """
        
        self._overhead_conductor_type_asset = None
        self.overhead_conductor_type_asset = overhead_conductor_type_asset

        super(OverheadConductorAssetModel, self).__init__(**kw_args)
    # >>> overhead_conductor_asset_model
        
    # <<< overhead_conductor_type_asset
    # @generated
    def get_overhead_conductor_type_asset(self):
        """ 
        """
        return self._overhead_conductor_type_asset

    def set_overhead_conductor_type_asset(self, value):
        if self._overhead_conductor_type_asset is not None:
            filtered = [x for x in self.overhead_conductor_type_asset.overhead_conductor_models if x != self]
            self._overhead_conductor_type_asset._overhead_conductor_models = filtered
            
        self._overhead_conductor_type_asset = value
        if self._overhead_conductor_type_asset is not None:
            if self not in self._overhead_conductor_type_asset._overhead_conductor_models:
                self._overhead_conductor_type_asset._overhead_conductor_models.append(self)

    overhead_conductor_type_asset = property(get_overhead_conductor_type_asset, set_overhead_conductor_type_asset)
    # >>> overhead_conductor_type_asset



class ShuntCompensatorAssetModel(ElectricalAssetModel):
    """ For application as shunt capacitor or reactor or switchable bank of shunt capacitors or reactors, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer (Organisation). There are typically many instances of an asset associated with a single asset model.
    """
    # <<< shunt_compensator_asset_model
    # @generated
    def __init__(self, shunt_compensator_assets=None, shunt_compensator_type_asset=None, shunt_impedance_info=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorAssetModel' instance.
        """
        
        self._shunt_compensator_assets = []
        if shunt_compensator_assets is None:
            self.shunt_compensator_assets = []
        else:
            self.shunt_compensator_assets = shunt_compensator_assets
        self._shunt_compensator_type_asset = None
        self.shunt_compensator_type_asset = shunt_compensator_type_asset
        self._shunt_impedance_info = None
        self.shunt_impedance_info = shunt_impedance_info

        super(ShuntCompensatorAssetModel, self).__init__(**kw_args)
    # >>> shunt_compensator_asset_model
        
    # <<< shunt_compensator_assets
    # @generated
    def get_shunt_compensator_assets(self):
        """ 
        """
        return self._shunt_compensator_assets

    def set_shunt_compensator_assets(self, value):
        for x in self._shunt_compensator_assets:
            x.shunt_compensator_asset_model = None
        for y in value:
            y.shunt_compensator_asset_model = self
        self._shunt_compensator_assets = value
            
    shunt_compensator_assets = property(get_shunt_compensator_assets, set_shunt_compensator_assets)
    
    def add_shunt_compensator_assets(self, *shunt_compensator_assets):
        for obj in shunt_compensator_assets:
            obj._shunt_compensator_asset_model = self
            if obj not in self._shunt_compensator_assets:
                self._shunt_compensator_assets.append(obj)
        
    def remove_shunt_compensator_assets(self, *shunt_compensator_assets):
        for obj in shunt_compensator_assets:
            obj._shunt_compensator_asset_model = None
            self._shunt_compensator_assets.remove(obj)
    # >>> shunt_compensator_assets

    # <<< shunt_compensator_type_asset
    # @generated
    def get_shunt_compensator_type_asset(self):
        """ 
        """
        return self._shunt_compensator_type_asset

    def set_shunt_compensator_type_asset(self, value):
        if self._shunt_compensator_type_asset is not None:
            filtered = [x for x in self.shunt_compensator_type_asset.shunt_compensator_asset_models if x != self]
            self._shunt_compensator_type_asset._shunt_compensator_asset_models = filtered
            
        self._shunt_compensator_type_asset = value
        if self._shunt_compensator_type_asset is not None:
            if self not in self._shunt_compensator_type_asset._shunt_compensator_asset_models:
                self._shunt_compensator_type_asset._shunt_compensator_asset_models.append(self)

    shunt_compensator_type_asset = property(get_shunt_compensator_type_asset, set_shunt_compensator_type_asset)
    # >>> shunt_compensator_type_asset

    # <<< shunt_impedance_info
    # @generated
    def get_shunt_impedance_info(self):
        """ 
        """
        return self._shunt_impedance_info

    def set_shunt_impedance_info(self, value):
        if self._shunt_impedance_info is not None:
            self._shunt_impedance_info._shunt_compensator_asset_model = None
            
        self._shunt_impedance_info = value
        if self._shunt_impedance_info is not None:
            self._shunt_impedance_info._shunt_compensator_asset_model = self
            
    shunt_impedance_info = property(get_shunt_impedance_info, set_shunt_impedance_info)
    # >>> shunt_impedance_info



class CableAssetModel(LinearConductorAssetModel):
    """ Documentation for a type of a Cable of a particular product model made by a manufacturer.
    """
    # <<< cable_asset_model
    # @generated
    def __init__(self, insulating_compound='', sheath_material='', shield_kind='superclean', center_to_neutral=0.0, insulation_thickness=0.0, neutral_cond_design='', outer_jacket_kind='other', diameter_to_neutral=0.0, construction_kind='segmental', nominal_temp=0.0, conductor_material_kind='copper', strand_fill_flag=False, conductor_count=0, sheath_neutral=False, cable_type_asset=None, **kw_args):
        """ Initialises a new 'CableAssetModel' instance.
        """
        # Insulating compound name. 
        self.insulating_compound = insulating_compound
        # Sheath material: Lead, Copper, Steel, Aluminum, None. 
        self.sheath_material = sheath_material
        # Kind of conductor shield of this cable product. Values are: "superclean", "free_form", "conventional", "supersmooth", "other"
        self.shield_kind = shield_kind
        # Center to neutral conductor spacing for a concentric neutral cable (blank for a transmission model). 
        self.center_to_neutral = center_to_neutral
        # Thickness of the insulation. 
        self.insulation_thickness = insulation_thickness
        # Neutral conductor design: Concentric Neutral, Copper Tape, Aluminum Tape, Lead insulation, Other. 
        self.neutral_cond_design = neutral_cond_design
        # Kind of outer jacket of this cable. Values are: "other", "linear_low_density_polyethylene", "insulating", "semiconducting", "polyethylene", "pvc", "none"
        self.outer_jacket_kind = outer_jacket_kind
        # Diameter of a concentric neutral strand for a concentric neutral cable. 
        self.diameter_to_neutral = diameter_to_neutral
        # Kind of construction of this cable product. Values are: "segmental", "compressed", "other", "sector", "stranded", "solid", "compacted"
        self.construction_kind = construction_kind
        # Maximum nominal design operating temperature. 
        self.nominal_temp = nominal_temp
        # Kind of conductor material of this cable product. Values are: "copper", "other", "aluminum"
        self.conductor_material_kind = conductor_material_kind
        # True if wire strands are extruded in a way to fill the voids in the cable. 
        self.strand_fill_flag = strand_fill_flag
        # Number of conductors physically contained in the cable. 
        self.conductor_count = conductor_count
        # True if sheath is used as a neutral. 
        self.sheath_neutral = sheath_neutral
        
        self._cable_type_asset = None
        self.cable_type_asset = cable_type_asset

        super(CableAssetModel, self).__init__(**kw_args)
    # >>> cable_asset_model
        
    # <<< cable_type_asset
    # @generated
    def get_cable_type_asset(self):
        """ 
        """
        return self._cable_type_asset

    def set_cable_type_asset(self, value):
        if self._cable_type_asset is not None:
            filtered = [x for x in self.cable_type_asset.cable_asset_models if x != self]
            self._cable_type_asset._cable_asset_models = filtered
            
        self._cable_type_asset = value
        if self._cable_type_asset is not None:
            if self not in self._cable_type_asset._cable_asset_models:
                self._cable_type_asset._cable_asset_models.append(self)

    cable_type_asset = property(get_cable_type_asset, set_cable_type_asset)
    # >>> cable_type_asset



class RecloserAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a recloser asset of a particular product model made by a manufacturer.
    """
    # <<< recloser_asset_model
    # @generated
    def __init__(self, recloser_assets=None, recloser_type_asset=None, recloser_info=None, **kw_args):
        """ Initialises a new 'RecloserAssetModel' instance.
        """
        
        self._recloser_assets = []
        if recloser_assets is None:
            self.recloser_assets = []
        else:
            self.recloser_assets = recloser_assets
        self._recloser_type_asset = None
        self.recloser_type_asset = recloser_type_asset
        self._recloser_info = None
        self.recloser_info = recloser_info

        super(RecloserAssetModel, self).__init__(**kw_args)
    # >>> recloser_asset_model
        
    # <<< recloser_assets
    # @generated
    def get_recloser_assets(self):
        """ 
        """
        return self._recloser_assets

    def set_recloser_assets(self, value):
        for x in self._recloser_assets:
            x.recloser_asset_model = None
        for y in value:
            y.recloser_asset_model = self
        self._recloser_assets = value
            
    recloser_assets = property(get_recloser_assets, set_recloser_assets)
    
    def add_recloser_assets(self, *recloser_assets):
        for obj in recloser_assets:
            obj._recloser_asset_model = self
            if obj not in self._recloser_assets:
                self._recloser_assets.append(obj)
        
    def remove_recloser_assets(self, *recloser_assets):
        for obj in recloser_assets:
            obj._recloser_asset_model = None
            self._recloser_assets.remove(obj)
    # >>> recloser_assets

    # <<< recloser_type_asset
    # @generated
    def get_recloser_type_asset(self):
        """ 
        """
        return self._recloser_type_asset

    def set_recloser_type_asset(self, value):
        if self._recloser_type_asset is not None:
            filtered = [x for x in self.recloser_type_asset.recloser_asset_models if x != self]
            self._recloser_type_asset._recloser_asset_models = filtered
            
        self._recloser_type_asset = value
        if self._recloser_type_asset is not None:
            if self not in self._recloser_type_asset._recloser_asset_models:
                self._recloser_type_asset._recloser_asset_models.append(self)

    recloser_type_asset = property(get_recloser_type_asset, set_recloser_type_asset)
    # >>> recloser_type_asset

    # <<< recloser_info
    # @generated
    def get_recloser_info(self):
        """ 
        """
        return self._recloser_info

    def set_recloser_info(self, value):
        if self._recloser_info is not None:
            filtered = [x for x in self.recloser_info.recloser_asset_models if x != self]
            self._recloser_info._recloser_asset_models = filtered
            
        self._recloser_info = value
        if self._recloser_info is not None:
            if self not in self._recloser_info._recloser_asset_models:
                self._recloser_info._recloser_asset_models.append(self)

    recloser_info = property(get_recloser_info, set_recloser_info)
    # >>> recloser_info



class ComFunctionAssetModel(ElectricalAssetModel):
    """ Documentation for a type of communication function of a particular product model made by a manufacturer.
    """
    # <<< com_function_asset_model
    # @generated
    def __init__(self, com_function_type_asset=None, **kw_args):
        """ Initialises a new 'ComFunctionAssetModel' instance.
        """
        
        self._com_function_type_asset = None
        self.com_function_type_asset = com_function_type_asset

        super(ComFunctionAssetModel, self).__init__(**kw_args)
    # >>> com_function_asset_model
        
    # <<< com_function_type_asset
    # @generated
    def get_com_function_type_asset(self):
        """ 
        """
        return self._com_function_type_asset

    def set_com_function_type_asset(self, value):
        if self._com_function_type_asset is not None:
            filtered = [x for x in self.com_function_type_asset.com_function_asset_models if x != self]
            self._com_function_type_asset._com_function_asset_models = filtered
            
        self._com_function_type_asset = value
        if self._com_function_type_asset is not None:
            if self not in self._com_function_type_asset._com_function_asset_models:
                self._com_function_type_asset._com_function_asset_models.append(self)

    com_function_type_asset = property(get_com_function_type_asset, set_com_function_type_asset)
    # >>> com_function_type_asset



class BusbarAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a busbar asset of a particular product model made by a manufacturer.
    """
    # <<< busbar_asset_model
    # @generated
    def __init__(self, busbar_assets=None, busbar_asset_model=None, **kw_args):
        """ Initialises a new 'BusbarAssetModel' instance.
        """
        
        self._busbar_assets = []
        if busbar_assets is None:
            self.busbar_assets = []
        else:
            self.busbar_assets = busbar_assets
        self._busbar_asset_model = None
        self.busbar_asset_model = busbar_asset_model

        super(BusbarAssetModel, self).__init__(**kw_args)
    # >>> busbar_asset_model
        
    # <<< busbar_assets
    # @generated
    def get_busbar_assets(self):
        """ 
        """
        return self._busbar_assets

    def set_busbar_assets(self, value):
        for x in self._busbar_assets:
            x.busbar_asset_model = None
        for y in value:
            y.busbar_asset_model = self
        self._busbar_assets = value
            
    busbar_assets = property(get_busbar_assets, set_busbar_assets)
    
    def add_busbar_assets(self, *busbar_assets):
        for obj in busbar_assets:
            obj._busbar_asset_model = self
            if obj not in self._busbar_assets:
                self._busbar_assets.append(obj)
        
    def remove_busbar_assets(self, *busbar_assets):
        for obj in busbar_assets:
            obj._busbar_asset_model = None
            self._busbar_assets.remove(obj)
    # >>> busbar_assets

    # <<< busbar_asset_model
    # @generated
    def get_busbar_asset_model(self):
        """ 
        """
        return self._busbar_asset_model

    def set_busbar_asset_model(self, value):
        if self._busbar_asset_model is not None:
            filtered = [x for x in self.busbar_asset_model.busbar_type_assets if x != self]
            self._busbar_asset_model._busbar_type_assets = filtered
            
        self._busbar_asset_model = value
        if self._busbar_asset_model is not None:
            if self not in self._busbar_asset_model._busbar_type_assets:
                self._busbar_asset_model._busbar_type_assets.append(self)

    busbar_asset_model = property(get_busbar_asset_model, set_busbar_asset_model)
    # >>> busbar_asset_model



class TapChangerModel(ElectricalAssetModel):
    """ Documentation for a type of a tap changer of a particular product model made by a manufacturer.
    """
    # <<< tap_changer_model
    # @generated
    def __init__(self, tap_count=0, switching_kind='resistive', tap_changer_assets=None, tap_changer_type_asset=None, **kw_args):
        """ Initialises a new 'TapChangerModel' instance.
        """
        # Number of taps. 
        self.tap_count = tap_count
        # Switching kind of tap changer. Values are: "resistive", "other", "reactive", "vacuum"
        self.switching_kind = switching_kind
        
        self._tap_changer_assets = []
        if tap_changer_assets is None:
            self.tap_changer_assets = []
        else:
            self.tap_changer_assets = tap_changer_assets
        self._tap_changer_type_asset = None
        self.tap_changer_type_asset = tap_changer_type_asset

        super(TapChangerModel, self).__init__(**kw_args)
    # >>> tap_changer_model
        
    # <<< tap_changer_assets
    # @generated
    def get_tap_changer_assets(self):
        """ 
        """
        return self._tap_changer_assets

    def set_tap_changer_assets(self, value):
        for x in self._tap_changer_assets:
            x.tap_changer_model = None
        for y in value:
            y.tap_changer_model = self
        self._tap_changer_assets = value
            
    tap_changer_assets = property(get_tap_changer_assets, set_tap_changer_assets)
    
    def add_tap_changer_assets(self, *tap_changer_assets):
        for obj in tap_changer_assets:
            obj._tap_changer_model = self
            if obj not in self._tap_changer_assets:
                self._tap_changer_assets.append(obj)
        
    def remove_tap_changer_assets(self, *tap_changer_assets):
        for obj in tap_changer_assets:
            obj._tap_changer_model = None
            self._tap_changer_assets.remove(obj)
    # >>> tap_changer_assets

    # <<< tap_changer_type_asset
    # @generated
    def get_tap_changer_type_asset(self):
        """ 
        """
        return self._tap_changer_type_asset

    def set_tap_changer_type_asset(self, value):
        if self._tap_changer_type_asset is not None:
            filtered = [x for x in self.tap_changer_type_asset.tap_changer_models if x != self]
            self._tap_changer_type_asset._tap_changer_models = filtered
            
        self._tap_changer_type_asset = value
        if self._tap_changer_type_asset is not None:
            if self not in self._tap_changer_type_asset._tap_changer_models:
                self._tap_changer_type_asset._tap_changer_models.append(self)

    tap_changer_type_asset = property(get_tap_changer_type_asset, set_tap_changer_type_asset)
    # >>> tap_changer_type_asset



class BushingModel(ElectricalAssetModel):
    """ Documentation for a type of a bushing of a particular product model made by a manufacturer.
    """
    # <<< bushing_model
    # @generated
    def __init__(self, insulation_kind='compound', bushing_asset=None, bushing_type_asset=None, **kw_args):
        """ Initialises a new 'BushingModel' instance.
        """
        # Kind of insulation used in this bushing model. Values are: "compound", "other", "solid_porcelain", "paperoil"
        self.insulation_kind = insulation_kind
        
        self._bushing_asset = None
        self.bushing_asset = bushing_asset
        self._bushing_type_asset = None
        self.bushing_type_asset = bushing_type_asset

        super(BushingModel, self).__init__(**kw_args)
    # >>> bushing_model
        
    # <<< bushing_asset
    # @generated
    def get_bushing_asset(self):
        """ 
        """
        return self._bushing_asset

    def set_bushing_asset(self, value):
        if self._bushing_asset is not None:
            self._bushing_asset._bushing_model = None
            
        self._bushing_asset = value
        if self._bushing_asset is not None:
            self._bushing_asset._bushing_model = self
            
    bushing_asset = property(get_bushing_asset, set_bushing_asset)
    # >>> bushing_asset

    # <<< bushing_type_asset
    # @generated
    def get_bushing_type_asset(self):
        """ 
        """
        return self._bushing_type_asset

    def set_bushing_type_asset(self, value):
        if self._bushing_type_asset is not None:
            filtered = [x for x in self.bushing_type_asset.bushing_models if x != self]
            self._bushing_type_asset._bushing_models = filtered
            
        self._bushing_type_asset = value
        if self._bushing_type_asset is not None:
            if self not in self._bushing_type_asset._bushing_models:
                self._bushing_type_asset._bushing_models.append(self)

    bushing_type_asset = property(get_bushing_type_asset, set_bushing_type_asset)
    # >>> bushing_type_asset



class BreakerAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a breaker asset of a particular product model made by a manufacturer.
    """
    # <<< breaker_asset_model
    # @generated
    def __init__(self, breaker_type_asset=None, breaker_info=None, breaker_assets=None, **kw_args):
        """ Initialises a new 'BreakerAssetModel' instance.
        """
        
        self._breaker_type_asset = None
        self.breaker_type_asset = breaker_type_asset
        self._breaker_info = None
        self.breaker_info = breaker_info
        self._breaker_assets = []
        if breaker_assets is None:
            self.breaker_assets = []
        else:
            self.breaker_assets = breaker_assets

        super(BreakerAssetModel, self).__init__(**kw_args)
    # >>> breaker_asset_model
        
    # <<< breaker_type_asset
    # @generated
    def get_breaker_type_asset(self):
        """ 
        """
        return self._breaker_type_asset

    def set_breaker_type_asset(self, value):
        if self._breaker_type_asset is not None:
            filtered = [x for x in self.breaker_type_asset.breaker_asset_models if x != self]
            self._breaker_type_asset._breaker_asset_models = filtered
            
        self._breaker_type_asset = value
        if self._breaker_type_asset is not None:
            if self not in self._breaker_type_asset._breaker_asset_models:
                self._breaker_type_asset._breaker_asset_models.append(self)

    breaker_type_asset = property(get_breaker_type_asset, set_breaker_type_asset)
    # >>> breaker_type_asset

    # <<< breaker_info
    # @generated
    def get_breaker_info(self):
        """ 
        """
        return self._breaker_info

    def set_breaker_info(self, value):
        if self._breaker_info is not None:
            filtered = [x for x in self.breaker_info.breaker_asset_models if x != self]
            self._breaker_info._breaker_asset_models = filtered
            
        self._breaker_info = value
        if self._breaker_info is not None:
            if self not in self._breaker_info._breaker_asset_models:
                self._breaker_info._breaker_asset_models.append(self)

    breaker_info = property(get_breaker_info, set_breaker_info)
    # >>> breaker_info

    # <<< breaker_assets
    # @generated
    def get_breaker_assets(self):
        """ 
        """
        return self._breaker_assets

    def set_breaker_assets(self, value):
        for x in self._breaker_assets:
            x.breaker_asset_model = None
        for y in value:
            y.breaker_asset_model = self
        self._breaker_assets = value
            
    breaker_assets = property(get_breaker_assets, set_breaker_assets)
    
    def add_breaker_assets(self, *breaker_assets):
        for obj in breaker_assets:
            obj._breaker_asset_model = self
            if obj not in self._breaker_assets:
                self._breaker_assets.append(obj)
        
    def remove_breaker_assets(self, *breaker_assets):
        for obj in breaker_assets:
            obj._breaker_asset_model = None
            self._breaker_assets.remove(obj)
    # >>> breaker_assets



class CurrentTransformerAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Current Transformer (CT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    # <<< current_transformer_asset_model
    # @generated
    def __init__(self, current_transformer_info=None, current_transformer_type_asset=None, current_transformer_assets=None, **kw_args):
        """ Initialises a new 'CurrentTransformerAssetModel' instance.
        """
        
        self._current_transformer_info = None
        self.current_transformer_info = current_transformer_info
        self._current_transformer_type_asset = None
        self.current_transformer_type_asset = current_transformer_type_asset
        self._current_transformer_assets = []
        if current_transformer_assets is None:
            self.current_transformer_assets = []
        else:
            self.current_transformer_assets = current_transformer_assets

        super(CurrentTransformerAssetModel, self).__init__(**kw_args)
    # >>> current_transformer_asset_model
        
    # <<< current_transformer_info
    # @generated
    def get_current_transformer_info(self):
        """ 
        """
        return self._current_transformer_info

    def set_current_transformer_info(self, value):
        if self._current_transformer_info is not None:
            filtered = [x for x in self.current_transformer_info.current_transformer_assert_models if x != self]
            self._current_transformer_info._current_transformer_assert_models = filtered
            
        self._current_transformer_info = value
        if self._current_transformer_info is not None:
            if self not in self._current_transformer_info._current_transformer_assert_models:
                self._current_transformer_info._current_transformer_assert_models.append(self)

    current_transformer_info = property(get_current_transformer_info, set_current_transformer_info)
    # >>> current_transformer_info

    # <<< current_transformer_type_asset
    # @generated
    def get_current_transformer_type_asset(self):
        """ 
        """
        return self._current_transformer_type_asset

    def set_current_transformer_type_asset(self, value):
        if self._current_transformer_type_asset is not None:
            filtered = [x for x in self.current_transformer_type_asset.current_transformer_asset_models if x != self]
            self._current_transformer_type_asset._current_transformer_asset_models = filtered
            
        self._current_transformer_type_asset = value
        if self._current_transformer_type_asset is not None:
            if self not in self._current_transformer_type_asset._current_transformer_asset_models:
                self._current_transformer_type_asset._current_transformer_asset_models.append(self)

    current_transformer_type_asset = property(get_current_transformer_type_asset, set_current_transformer_type_asset)
    # >>> current_transformer_type_asset

    # <<< current_transformer_assets
    # @generated
    def get_current_transformer_assets(self):
        """ 
        """
        return self._current_transformer_assets

    def set_current_transformer_assets(self, value):
        for x in self._current_transformer_assets:
            x.current_transformer_asset_model = None
        for y in value:
            y.current_transformer_asset_model = self
        self._current_transformer_assets = value
            
    current_transformer_assets = property(get_current_transformer_assets, set_current_transformer_assets)
    
    def add_current_transformer_assets(self, *current_transformer_assets):
        for obj in current_transformer_assets:
            obj._current_transformer_asset_model = self
            if obj not in self._current_transformer_assets:
                self._current_transformer_assets.append(obj)
        
    def remove_current_transformer_assets(self, *current_transformer_assets):
        for obj in current_transformer_assets:
            obj._current_transformer_asset_model = None
            self._current_transformer_assets.remove(obj)
    # >>> current_transformer_assets



class FACTSDeviceAssetModel(ElectricalAssetModel):
    """ A particular model of FACTS device provided from a manufacturer. A FACTS devices are used for the dynamic control of voltage, impedance and phase angle of high voltage AC transmission lines. FACTS device types include: - SVC = Static Var Compensator - STATCOM = Static Synchronous Compensator - TCPAR = Thyristor Controlled Phase-Angle Regulator - TCSC = Thyristor Controlled Series Capacitor - TCVL = Thyristor Controlled Voltage Limiter - TSBR = Thyristor Switched Braking Resistor - TSSC = Thyristor Switched Series Capacitor - UPFC = Unified Power Flow Controller
    """
    # <<< factsdevice_asset_model
    # @generated
    def __init__(self, factsdevice_assets=None, factsdevice_type_asset=None, **kw_args):
        """ Initialises a new 'FACTSDeviceAssetModel' instance.
        """
        
        self._factsdevice_assets = []
        if factsdevice_assets is None:
            self.factsdevice_assets = []
        else:
            self.factsdevice_assets = factsdevice_assets
        self._factsdevice_type_asset = None
        self.factsdevice_type_asset = factsdevice_type_asset

        super(FACTSDeviceAssetModel, self).__init__(**kw_args)
    # >>> factsdevice_asset_model
        
    # <<< factsdevice_assets
    # @generated
    def get_factsdevice_assets(self):
        """ 
        """
        return self._factsdevice_assets

    def set_factsdevice_assets(self, value):
        for x in self._factsdevice_assets:
            x.factsdevice_asset_model = None
        for y in value:
            y.factsdevice_asset_model = self
        self._factsdevice_assets = value
            
    factsdevice_assets = property(get_factsdevice_assets, set_factsdevice_assets)
    
    def add_factsdevice_assets(self, *factsdevice_assets):
        for obj in factsdevice_assets:
            obj._factsdevice_asset_model = self
            if obj not in self._factsdevice_assets:
                self._factsdevice_assets.append(obj)
        
    def remove_factsdevice_assets(self, *factsdevice_assets):
        for obj in factsdevice_assets:
            obj._factsdevice_asset_model = None
            self._factsdevice_assets.remove(obj)
    # >>> factsdevice_assets

    # <<< factsdevice_type_asset
    # @generated
    def get_factsdevice_type_asset(self):
        """ 
        """
        return self._factsdevice_type_asset

    def set_factsdevice_type_asset(self, value):
        if self._factsdevice_type_asset is not None:
            filtered = [x for x in self.factsdevice_type_asset.factsdevice_asset_models if x != self]
            self._factsdevice_type_asset._factsdevice_asset_models = filtered
            
        self._factsdevice_type_asset = value
        if self._factsdevice_type_asset is not None:
            if self not in self._factsdevice_type_asset._factsdevice_asset_models:
                self._factsdevice_type_asset._factsdevice_asset_models.append(self)

    factsdevice_type_asset = property(get_factsdevice_type_asset, set_factsdevice_type_asset)
    # >>> factsdevice_type_asset



class SeriesCompensatorAssetModel(ElectricalAssetModel):
    """ For application as a series capacitor or reactor, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer.
    """
    # <<< series_compensator_asset_model
    # @generated
    def __init__(self, series_compensator_asset=None, shunt_compensator_type_asset=None, **kw_args):
        """ Initialises a new 'SeriesCompensatorAssetModel' instance.
        """
        
        self._series_compensator_asset = None
        self.series_compensator_asset = series_compensator_asset
        self._shunt_compensator_type_asset = None
        self.shunt_compensator_type_asset = shunt_compensator_type_asset

        super(SeriesCompensatorAssetModel, self).__init__(**kw_args)
    # >>> series_compensator_asset_model
        
    # <<< series_compensator_asset
    # @generated
    def get_series_compensator_asset(self):
        """ 
        """
        return self._series_compensator_asset

    def set_series_compensator_asset(self, value):
        if self._series_compensator_asset is not None:
            self._series_compensator_asset._series_compensator_asset_model = None
            
        self._series_compensator_asset = value
        if self._series_compensator_asset is not None:
            self._series_compensator_asset._series_compensator_asset_model = self
            
    series_compensator_asset = property(get_series_compensator_asset, set_series_compensator_asset)
    # >>> series_compensator_asset

    # <<< shunt_compensator_type_asset
    # @generated
    def get_shunt_compensator_type_asset(self):
        """ 
        """
        return self._shunt_compensator_type_asset

    def set_shunt_compensator_type_asset(self, value):
        if self._shunt_compensator_type_asset is not None:
            filtered = [x for x in self.shunt_compensator_type_asset.shunt_compensator_asset_models if x != self]
            self._shunt_compensator_type_asset._shunt_compensator_asset_models = filtered
            
        self._shunt_compensator_type_asset = value
        if self._shunt_compensator_type_asset is not None:
            if self not in self._shunt_compensator_type_asset._shunt_compensator_asset_models:
                self._shunt_compensator_type_asset._shunt_compensator_asset_models.append(self)

    shunt_compensator_type_asset = property(get_shunt_compensator_type_asset, set_shunt_compensator_type_asset)
    # >>> shunt_compensator_type_asset



class SVCAssetModel(FACTSDeviceAssetModel):
    """ Documentation for a type of a Static Var Compensator of a particular product model made by a manufacturer.
    """
    # <<< svcasset_model
    # @generated
    def __init__(self, svctype_asset=None, svcassets=None, svc_info=None, **kw_args):
        """ Initialises a new 'SVCAssetModel' instance.
        """
        
        self._svctype_asset = None
        self.svctype_asset = svctype_asset
        self._svcassets = []
        if svcassets is None:
            self.svcassets = []
        else:
            self.svcassets = svcassets
        self._svc_info = None
        self.svc_info = svc_info

        super(SVCAssetModel, self).__init__(**kw_args)
    # >>> svcasset_model
        
    # <<< svctype_asset
    # @generated
    def get_svctype_asset(self):
        """ 
        """
        return self._svctype_asset

    def set_svctype_asset(self, value):
        if self._svctype_asset is not None:
            filtered = [x for x in self.svctype_asset.svcasset_models if x != self]
            self._svctype_asset._svcasset_models = filtered
            
        self._svctype_asset = value
        if self._svctype_asset is not None:
            if self not in self._svctype_asset._svcasset_models:
                self._svctype_asset._svcasset_models.append(self)

    svctype_asset = property(get_svctype_asset, set_svctype_asset)
    # >>> svctype_asset

    # <<< svcassets
    # @generated
    def get_svcassets(self):
        """ 
        """
        return self._svcassets

    def set_svcassets(self, value):
        for x in self._svcassets:
            x.svcasset_model = None
        for y in value:
            y.svcasset_model = self
        self._svcassets = value
            
    svcassets = property(get_svcassets, set_svcassets)
    
    def add_svcassets(self, *svcassets):
        for obj in svcassets:
            obj._svcasset_model = self
            if obj not in self._svcassets:
                self._svcassets.append(obj)
        
    def remove_svcassets(self, *svcassets):
        for obj in svcassets:
            obj._svcasset_model = None
            self._svcassets.remove(obj)
    # >>> svcassets

    # <<< svc_info
    # @generated
    def get_svc_info(self):
        """ 
        """
        return self._svc_info

    def set_svc_info(self, value):
        if self._svc_info is not None:
            self._svc_info._svcasset_model = None
            
        self._svc_info = value
        if self._svc_info is not None:
            self._svc_info._svcasset_model = self
            
    svc_info = property(get_svc_info, set_svc_info)
    # >>> svc_info



# <<< inf_asset_models
# @generated
# >>> inf_asset_models

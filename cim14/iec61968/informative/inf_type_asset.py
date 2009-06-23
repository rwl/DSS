# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.informative.inf_common import Role
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfTypeAsset"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfTypeAsset"

class ElecAMElecPropRole(Role):
    """ ElectricalAssetModel - ElectrialProperties Role. Used to define what the properties refers to in the AssetModel. e.g. for a Transformer, it will have an association to an instance of ElectricalProperties for each Winding, with the roleType defining whether it is the primary, secondary, tertiary, quartiary winding.
    """
    electrical_asset_model = None

    electrical_info = None

    # <<< elec_amelec_prop_role
    # @generated
    def __init__(self, electrical_asset_model=None, electrical_info=None, **kw_args):
        """ Initialises a new 'ElecAMElecPropRole' instance.
        """
        self.electrical_asset_model = electrical_asset_model
        self.electrical_info = electrical_info

        super(ElecAMElecPropRole, self).__init__(**kw_args)
    # >>> elec_amelec_prop_role


class Connection(IdentifiedObject):
    """ A structure can have multiple connection points for electrical connections (e.g. line) each with multiple mounting points, one for each phase. e.g. a Tower may have three Connections, two with three mounting points, one for each phase and a third with a single mounting point for the neutral line. A pole, on the other hand, may have a single Connection with one, two or three mounting points depending on whether it is carrying 1,2 or 3 phases.
    """
    structure_type_assets = []

    mounting_points = []

    # <<< connection
    # @generated
    def __init__(self, structure_type_assets=[], mounting_points=[], **kw_args):
        """ Initialises a new 'Connection' instance.
        """
        self.structure_type_assets = structure_type_assets
        self.mounting_points = mounting_points

        super(Connection, self).__init__(**kw_args)
    # >>> connection


class ElecTAElecPropRole(Role):
    """ ElectricalTypeAsset - ElectrialProperties Role. Used to define what the properties refers to in the TypeAsset. e.g. for a Transformer, it will have an association to an instance of ElectricalProperties for each Winding, with the roleType defining whether it is the primary, secondary, tertiary, quartiary winding.
    """
    electrical_type_asset = None

    electrical_info = None

    # <<< elec_taelec_prop_role
    # @generated
    def __init__(self, electrical_type_asset=None, electrical_info=None, **kw_args):
        """ Initialises a new 'ElecTAElecPropRole' instance.
        """
        self.electrical_type_asset = electrical_type_asset
        self.electrical_info = electrical_info

        super(ElecTAElecPropRole, self).__init__(**kw_args)
    # >>> elec_taelec_prop_role


class TypeAsset(Document):
    """ Whereas an AssetModel is a particular model and version of a vendor's product, a TypeAsset is documentation for a generic asset or material item that may be used for design purposes. Any number of AssetModels may be used to perform this generic function. The primary role of the TypeAsset is typically defined by the PowereSystemResource it is associated with.
    """
    # True if item is a stock item (default). 
    stock_item = False

    # The value, unit of measure, and multiplier for the quantity. 
    quantity = ''

    # Estimated unit cost (or cost per unit length) of the this type of asset. It does not include labor to install/construct or configure it. 
    estimated_unit_cost = ''

    erp_req_line_items = []

    # A type of asset may be satisified with many different types of asset models.
    asset_models = []

    erp_bom_item_datas = []

    type_asset_catalogue = None

    erp_inventory_issues = []

    cuwork_equipment_asset = None

    cuasset = None

    # <<< type_asset
    # @generated
    def __init__(self, stock_item=False, quantity='', estimated_unit_cost='', erp_req_line_items=[], asset_models=[], erp_bom_item_datas=[], type_asset_catalogue=None, erp_inventory_issues=[], cuwork_equipment_asset=None, cuasset=None, **kw_args):
        """ Initialises a new 'TypeAsset' instance.
        """
        self.stock_item = stock_item
        self.quantity = quantity
        self.estimated_unit_cost = estimated_unit_cost
        self.erp_req_line_items = erp_req_line_items
        self.asset_models = asset_models
        self.erp_bom_item_datas = erp_bom_item_datas
        self.type_asset_catalogue = type_asset_catalogue
        self.erp_inventory_issues = erp_inventory_issues
        self.cuwork_equipment_asset = cuwork_equipment_asset
        self.cuasset = cuasset

        super(TypeAsset, self).__init__(**kw_args)
    # >>> type_asset


class MountingPoint(IdentifiedObject):
    """ Point on a structure that a connection may have a conductor connected to. Defined with an x and y coordinate plus a phase. A connection may have multiple mounting points, one for each phase.
    """
 
    x_coord = 0

 Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phase_code = 'abn'

 
    y_coord = 0

    connections = []

    overhead_conductors = []

    # <<< mounting_point
    # @generated
    def __init__(self, x_coord=0, phase_code='abn', y_coord=0, connections=[], overhead_conductors=[], **kw_args):
        """ Initialises a new 'MountingPoint' instance.
        """
        self.x_coord = x_coord
        self.phase_code = phase_code
        self.y_coord = y_coord
        self.connections = connections
        self.overhead_conductors = overhead_conductors

        super(MountingPoint, self).__init__(**kw_args)
    # >>> mounting_point


class TypeAssetCatalogue(IdentifiedObject):
    """ Catalogue of generic types of assets (TypeAsset) that may be used for design purposes. It is not associated with a particular manufacturer.
    """
    status = None

    type_assets = []

    # <<< type_asset_catalogue
    # @generated
    def __init__(self, status=None, type_assets=[], **kw_args):
        """ Initialises a new 'TypeAssetCatalogue' instance.
        """
        self.status = status
        self.type_assets = type_assets

        super(TypeAssetCatalogue, self).__init__(**kw_args)
    # >>> type_asset_catalogue


class ToolTypeAsset(TypeAsset):
    """ Documentation for a generic tool that may be used for various purposes such as work planning.
    """
    tool_asset_models = []

    # <<< tool_type_asset
    # @generated
    def __init__(self, tool_asset_models=[], **kw_args):
        """ Initialises a new 'ToolTypeAsset' instance.
        """
        self.tool_asset_models = tool_asset_models

        super(ToolTypeAsset, self).__init__(**kw_args)
    # >>> tool_type_asset


class AssetFunctionTypeAsset(TypeAsset):
    """ Documentation for a generic Asset Function that may be used for various purposes such as work planning.
    """
    asset_function_asset_models = []

    # <<< asset_function_type_asset
    # @generated
    def __init__(self, asset_function_asset_models=[], **kw_args):
        """ Initialises a new 'AssetFunctionTypeAsset' instance.
        """
        self.asset_function_asset_models = asset_function_asset_models

        super(AssetFunctionTypeAsset, self).__init__(**kw_args)
    # >>> asset_function_type_asset


class SubstationTypeAsset(TypeAsset):
    """ Documentation for a type of substation that may be used for design purposes.
    """
    pass
    # <<< substation_type_asset
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'SubstationTypeAsset' instance.
        """

        super(SubstationTypeAsset, self).__init__(**kw_args)
    # >>> substation_type_asset


class WorkEquipmentTypeAsset(TypeAsset):
    """ Documentation for generic equipment that may be used for various purposes such as work planning.
    """
    work_equipment_asset_models = []

    # <<< work_equipment_type_asset
    # @generated
    def __init__(self, work_equipment_asset_models=[], **kw_args):
        """ Initialises a new 'WorkEquipmentTypeAsset' instance.
        """
        self.work_equipment_asset_models = work_equipment_asset_models

        super(WorkEquipmentTypeAsset, self).__init__(**kw_args)
    # >>> work_equipment_type_asset


class ComEquipmentTypeAsset(TypeAsset):
    """ Documentation for a piece of Communication Equipment (e.g., gateway, router, network hub, etc.) that may be used for design purposes.
    """
    pass
    # <<< com_equipment_type_asset
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ComEquipmentTypeAsset' instance.
        """

        super(ComEquipmentTypeAsset, self).__init__(**kw_args)
    # >>> com_equipment_type_asset


class VehicleTypeAsset(TypeAsset):
    """ Documentation for a generic vehicle that may be used for various purposes such as work planning.
    """
    vehicle_asset_models = []

    # <<< vehicle_type_asset
    # @generated
    def __init__(self, vehicle_asset_models=[], **kw_args):
        """ Initialises a new 'VehicleTypeAsset' instance.
        """
        self.vehicle_asset_models = vehicle_asset_models

        super(VehicleTypeAsset, self).__init__(**kw_args)
    # >>> vehicle_type_asset


class EndDeviceTypeAsset(TypeAsset):
    """ Documentation for generic End Device that may be used for various purposes such as work planning.
    """
    end_device_models = []

    # <<< end_device_type_asset
    # @generated
    def __init__(self, end_device_models=[], **kw_args):
        """ Initialises a new 'EndDeviceTypeAsset' instance.
        """
        self.end_device_models = end_device_models

        super(EndDeviceTypeAsset, self).__init__(**kw_args)
    # >>> end_device_type_asset


class ElectricalTypeAsset(TypeAsset):
    """ Generic TypeAsset for all types of component in the network that have electrical characteristics.
    """
    electrical_info_roles = []

    # <<< electrical_type_asset
    # @generated
    def __init__(self, electrical_info_roles=[], **kw_args):
        """ Initialises a new 'ElectricalTypeAsset' instance.
        """
        self.electrical_info_roles = electrical_info_roles

        super(ElectricalTypeAsset, self).__init__(**kw_args)
    # >>> electrical_type_asset


class ProtectionEquipmentTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic protection equiment that may be used for design purposes.
    """
    # Default ground trip setting for this type of relay, if applicable. 
    default_ground_trip = ''

    # Default phase trip setting for this type of relay, if applicable. 
    default_phase_trip = ''

    protection_equipment_asset_models = []

    # <<< protection_equipment_type_asset
    # @generated
    def __init__(self, default_ground_trip='', default_phase_trip='', protection_equipment_asset_models=[], **kw_args):
        """ Initialises a new 'ProtectionEquipmentTypeAsset' instance.
        """
        self.default_ground_trip = default_ground_trip
        self.default_phase_trip = default_phase_trip
        self.protection_equipment_asset_models = protection_equipment_asset_models

        super(ProtectionEquipmentTypeAsset, self).__init__(**kw_args)
    # >>> protection_equipment_type_asset


class BreakerTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic breaker asset that may be used for design purposes.
    """
    breaker_info = None

    breaker_asset_models = []

    # <<< breaker_type_asset
    # @generated
    def __init__(self, breaker_info=None, breaker_asset_models=[], **kw_args):
        """ Initialises a new 'BreakerTypeAsset' instance.
        """
        self.breaker_info = breaker_info
        self.breaker_asset_models = breaker_asset_models

        super(BreakerTypeAsset, self).__init__(**kw_args)
    # >>> breaker_type_asset


class TransformerTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic transformers that may be used for various purposes such as work planning. The operating voltages can be found via the ElectricalProperties class. e.g. a two winding transformer will have two instances of ElectricalProperties and the primary/secondaryWinding can be found from the ratedKV attributes of each and the ElecTAElecPropRole used to define which is the primaryWinding and which is the secondaryWinding.
    """
    # Maximum loss of power in the transformer core. 
    core_loss = ''

    # Number of windings. 
    winding_count = 0

    transformer_asset_models = []

    transformer_info = None

    # <<< transformer_type_asset
    # @generated
    def __init__(self, core_loss='', winding_count=0, transformer_asset_models=[], transformer_info=None, **kw_args):
        """ Initialises a new 'TransformerTypeAsset' instance.
        """
        self.core_loss = core_loss
        self.winding_count = winding_count
        self.transformer_asset_models = transformer_asset_models
        self.transformer_info = transformer_info

        super(TransformerTypeAsset, self).__init__(**kw_args)
    # >>> transformer_type_asset


class BushingTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic bushing that may be used for various purposes such as work planning.
    """
    bushing_models = []

    # <<< bushing_type_asset
    # @generated
    def __init__(self, bushing_models=[], **kw_args):
        """ Initialises a new 'BushingTypeAsset' instance.
        """
        self.bushing_models = bushing_models

        super(BushingTypeAsset, self).__init__(**kw_args)
    # >>> bushing_type_asset


class StructureTypeAsset(TypeAsset):
    """ A Type of Structural Asset with properties common to a large number of asset models.
    """
    # Maximum rated voltage of the equipment that can be mounted on/contained within the structure. 
    rated_voltage = ''

    mount_connections = []

    # <<< structure_type_asset
    # @generated
    def __init__(self, rated_voltage='', mount_connections=[], **kw_args):
        """ Initialises a new 'StructureTypeAsset' instance.
        """
        self.rated_voltage = rated_voltage
        self.mount_connections = mount_connections

        super(StructureTypeAsset, self).__init__(**kw_args)
    # >>> structure_type_asset


class CompositeSwitchTypeAsset(TypeAsset):
    """ Documentation for a generic composite switch asset that may be used for design purposes. A composite wwitch is an amalgamation of multiple Switches.
    """
    composite_switch_asset_models = []

    switch_types_assets = []

    composite_switch_info = None

    # <<< composite_switch_type_asset
    # @generated
    def __init__(self, composite_switch_asset_models=[], switch_types_assets=[], composite_switch_info=None, **kw_args):
        """ Initialises a new 'CompositeSwitchTypeAsset' instance.
        """
        self.composite_switch_asset_models = composite_switch_asset_models
        self.switch_types_assets = switch_types_assets
        self.composite_switch_info = composite_switch_info

        super(CompositeSwitchTypeAsset, self).__init__(**kw_args)
    # >>> composite_switch_type_asset


class DuctTypeAsset(StructureTypeAsset):
    """ A Duct contains underground cables and is contained within a duct bank. The xCoord and yCoord attributes define its positioning within the DuctBank.
    """
    # Y position of the duct within the duct bank. 
    y_coord = 0

    # X position of the duct within the duct bank. 
    x_coord = 0

    cable_assets = []

    duct_bank_type_asset = None

    # <<< duct_type_asset
    # @generated
    def __init__(self, y_coord=0, x_coord=0, cable_assets=[], duct_bank_type_asset=None, **kw_args):
        """ Initialises a new 'DuctTypeAsset' instance.
        """
        self.y_coord = y_coord
        self.x_coord = x_coord
        self.cable_assets = cable_assets
        self.duct_bank_type_asset = duct_bank_type_asset

        super(DuctTypeAsset, self).__init__(**kw_args)
    # >>> duct_type_asset


class CabinetTypeAsset(StructureTypeAsset):
    """ Documentation for a generic cabinet that may be used for various purposes such as work planning.
    """
    cabinet_models = []

    # <<< cabinet_type_asset
    # @generated
    def __init__(self, cabinet_models=[], **kw_args):
        """ Initialises a new 'CabinetTypeAsset' instance.
        """
        self.cabinet_models = cabinet_models

        super(CabinetTypeAsset, self).__init__(**kw_args)
    # >>> cabinet_type_asset


class SurgeProtectorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic surge arrestor that may be used for design purposes.
    """
 
    maximum_energy_absorption = 0.0

 
    maximum_continous_operating_voltage = ''

 
    maximum_current_rating = ''

 
    nominal_design_voltage = ''

    surge_protectors = []

    surge_protector_asset_models = []

    # <<< surge_protector_type_asset
    # @generated
    def __init__(self, maximum_energy_absorption=0.0, maximum_continous_operating_voltage='', maximum_current_rating='', nominal_design_voltage='', surge_protectors=[], surge_protector_asset_models=[], **kw_args):
        """ Initialises a new 'SurgeProtectorTypeAsset' instance.
        """
        self.maximum_energy_absorption = maximum_energy_absorption
        self.maximum_continous_operating_voltage = maximum_continous_operating_voltage
        self.maximum_current_rating = maximum_current_rating
        self.nominal_design_voltage = nominal_design_voltage
        self.surge_protectors = surge_protectors
        self.surge_protector_asset_models = surge_protector_asset_models

        super(SurgeProtectorTypeAsset, self).__init__(**kw_args)
    # >>> surge_protector_type_asset


class CurrentTransformerTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic Current Transformer (CT) that may be used for various purposes such as work planning.
    """
 
    ct_class = ''

    # Maximum primary current where the CT still displays linear characteristicts. 
    knee_point_current = ''

    # CT accuracy classification 
    accuracy_class = ''

    # Power burden of the CT core 
    core_burden = ''

    # Maximum voltage across the secondary terminals where the CT still displays linear characteristicts. 
    knee_point_voltage = ''

    # Number of cores. 
    core_count = 0

    # eg. metering, protection, etc 
    usage = ''

 
    accuracy_limit = ''

    # Maximum ratio between the primary and secondary current.
    max_ratio = None

    # Nominal ratio between the primary and secondary current; i.e. 100:5
    nominal_ratio = None

    current_transformer_info = None

    current_transformers = []

    current_transformer_asset_models = []

    # <<< current_transformer_type_asset
    # @generated
    def __init__(self, ct_class='', knee_point_current='', accuracy_class='', core_burden='', knee_point_voltage='', core_count=0, usage='', accuracy_limit='', max_ratio=None, nominal_ratio=None, current_transformer_info=None, current_transformers=[], current_transformer_asset_models=[], **kw_args):
        """ Initialises a new 'CurrentTransformerTypeAsset' instance.
        """
        self.ct_class = ct_class
        self.knee_point_current = knee_point_current
        self.accuracy_class = accuracy_class
        self.core_burden = core_burden
        self.knee_point_voltage = knee_point_voltage
        self.core_count = core_count
        self.usage = usage
        self.accuracy_limit = accuracy_limit
        self.max_ratio = max_ratio
        self.nominal_ratio = nominal_ratio
        self.current_transformer_info = current_transformer_info
        self.current_transformers = current_transformers
        self.current_transformer_asset_models = current_transformer_asset_models

        super(CurrentTransformerTypeAsset, self).__init__(**kw_args)
    # >>> current_transformer_type_asset


class TowerTypeAsset(StructureTypeAsset):
    """ Documentation for a generic tower that may be used for various purposes such as work planning. A transmission tower carrying two 3-phase circuits will have 2 instances of Connection, each of which will have 3 MountingPoint instances, one for each phase all with coordinates relative to a common origin on the tower. (It may also have a 3rd Connection with a single MountingPoint for the Neutral line).
    """
    tower_asset_models = []

    # <<< tower_type_asset
    # @generated
    def __init__(self, tower_asset_models=[], **kw_args):
        """ Initialises a new 'TowerTypeAsset' instance.
        """
        self.tower_asset_models = tower_asset_models

        super(TowerTypeAsset, self).__init__(**kw_args)
    # >>> tower_type_asset


class RecloserTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic recloser asset that may be used for design purposes.
    """
    recloser_asset_models = []

    recloser_info = None

    # <<< recloser_type_asset
    # @generated
    def __init__(self, recloser_asset_models=[], recloser_info=None, **kw_args):
        """ Initialises a new 'RecloserTypeAsset' instance.
        """
        self.recloser_asset_models = recloser_asset_models
        self.recloser_info = recloser_info

        super(RecloserTypeAsset, self).__init__(**kw_args)
    # >>> recloser_type_asset


class PotentialTransformerTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic Potential Transformer (PT) that may be used for various purposes such as work planning.
    """
 
    accuracy_class = ''

 
    pt_class = ''

    nominal_ratio = None

    potential_transformers = []

    potential_transformer_asset_models = []

    potential_transformer_info = None

    # <<< potential_transformer_type_asset
    # @generated
    def __init__(self, accuracy_class='', pt_class='', nominal_ratio=None, potential_transformers=[], potential_transformer_asset_models=[], potential_transformer_info=None, **kw_args):
        """ Initialises a new 'PotentialTransformerTypeAsset' instance.
        """
        self.accuracy_class = accuracy_class
        self.pt_class = pt_class
        self.nominal_ratio = nominal_ratio
        self.potential_transformers = potential_transformers
        self.potential_transformer_asset_models = potential_transformer_asset_models
        self.potential_transformer_info = potential_transformer_info

        super(PotentialTransformerTypeAsset, self).__init__(**kw_args)
    # >>> potential_transformer_type_asset


class DuctBankTypeAsset(StructureTypeAsset):
    """ A DuctBank contains multiple Ducts. The DuctBank itself should have no connections, since these are defined by the individual ducts within it. However, it will have a ConstructionType and the material it is constructed from.
    """
    duct_banks = []

    duct_type_assets = []

    # <<< duct_bank_type_asset
    # @generated
    def __init__(self, duct_banks=[], duct_type_assets=[], **kw_args):
        """ Initialises a new 'DuctBankTypeAsset' instance.
        """
        self.duct_banks = duct_banks
        self.duct_type_assets = duct_type_assets

        super(DuctBankTypeAsset, self).__init__(**kw_args)
    # >>> duct_bank_type_asset


class FaultIndicatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic fault indicator that may be used for design purposes.
    """
    # Kind of reset mechanisim of this fault indicator. Values are: "remote", "automatic", "other", "manual"
    reset_kind = 'remote'

    fault_indicators = []

    fault_indicator_asset_models = []

    # <<< fault_indicator_type_asset
    # @generated
    def __init__(self, reset_kind='remote', fault_indicators=[], fault_indicator_asset_models=[], **kw_args):
        """ Initialises a new 'FaultIndicatorTypeAsset' instance.
        """
        self.reset_kind = reset_kind
        self.fault_indicators = fault_indicators
        self.fault_indicator_asset_models = fault_indicator_asset_models

        super(FaultIndicatorTypeAsset, self).__init__(**kw_args)
    # >>> fault_indicator_type_asset


class SwitchTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic switch asset that may be used for design purposes.
    """
    switch_asset_models = []

    composite_switch_type_asset = None

    switch_info = None

    # <<< switch_type_asset
    # @generated
    def __init__(self, switch_asset_models=[], composite_switch_type_asset=None, switch_info=None, **kw_args):
        """ Initialises a new 'SwitchTypeAsset' instance.
        """
        self.switch_asset_models = switch_asset_models
        self.composite_switch_type_asset = composite_switch_type_asset
        self.switch_info = switch_info

        super(SwitchTypeAsset, self).__init__(**kw_args)
    # >>> switch_type_asset


class SeriesCompensatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic series compensator that may be used for design purposes.
    """
    shunt_compensator_asset_models = []

    # <<< series_compensator_type_asset
    # @generated
    def __init__(self, shunt_compensator_asset_models=[], **kw_args):
        """ Initialises a new 'SeriesCompensatorTypeAsset' instance.
        """
        self.shunt_compensator_asset_models = shunt_compensator_asset_models

        super(SeriesCompensatorTypeAsset, self).__init__(**kw_args)
    # >>> series_compensator_type_asset


class GeneratorTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic generation equipment that may be used for various purposes such as work planning. It defines both the Real and Reactive power properties (modelled at the PSR level as a GeneratingUnit + SynchronousMachine)
    """
    # Maximum real power limit. 
    max_p = ''

    # Minimum real power generated. 
    min_p = ''

    # Quadrature-axis subtransient reactance 
    x_quad_subtrans = ''

    # Quadrature-axis synchronous reactance 
    x_quad_sync = ''

    # Direct-axis Transient reactance 
    x_direct_trans = ''

    # Direct-axis Transient resistance 
    r_direct_trans = ''

    # Quadrature-axis transient reactance. 
    x_quad_trans = ''

    # Direct-axis subtransient resistance 
    r_direct_subtrans = ''

    # Quadrature-axis synchronous resistance 
    r_quad_sync = ''

    # Direct-axis synchronous resistance 
    r_direct_sync = ''

    # Quadrature-axis Transient resistance 
    r_quad_trans = ''

    # Maximum reactive power limit. 
    max_q = ''

    # Minimum reactive power generated. 
    min_q = ''

    # Direct-axis subtransient reactance 
    x_direct_subtrans = ''

    # Direct-axis synchronous reactance 
    x_direct_sync = ''

    # Quadrature-axis subtransient resistance 
    r_quad_subtrans = ''

    generator_asset_models = []

    # <<< generator_type_asset
    # @generated
    def __init__(self, max_p='', min_p='', x_quad_subtrans='', x_quad_sync='', x_direct_trans='', r_direct_trans='', x_quad_trans='', r_direct_subtrans='', r_quad_sync='', r_direct_sync='', r_quad_trans='', max_q='', min_q='', x_direct_subtrans='', x_direct_sync='', r_quad_subtrans='', generator_asset_models=[], **kw_args):
        """ Initialises a new 'GeneratorTypeAsset' instance.
        """
        self.max_p = max_p
        self.min_p = min_p
        self.x_quad_subtrans = x_quad_subtrans
        self.x_quad_sync = x_quad_sync
        self.x_direct_trans = x_direct_trans
        self.r_direct_trans = r_direct_trans
        self.x_quad_trans = x_quad_trans
        self.r_direct_subtrans = r_direct_subtrans
        self.r_quad_sync = r_quad_sync
        self.r_direct_sync = r_direct_sync
        self.r_quad_trans = r_quad_trans
        self.max_q = max_q
        self.min_q = min_q
        self.x_direct_subtrans = x_direct_subtrans
        self.x_direct_sync = x_direct_sync
        self.r_quad_subtrans = r_quad_subtrans
        self.generator_asset_models = generator_asset_models

        super(GeneratorTypeAsset, self).__init__(**kw_args)
    # >>> generator_type_asset


class PoleTypeAsset(StructureTypeAsset):
    """ Documentation for a generic pole that may be used for various purposes such as work planning. A pole typically has a single Connection with 1,2 or 3 mounting points.
    """
    # Diameter of the pole. 
    diameter = ''

    # Length of the pole (inclusive of any section of the pole that may be underground post-installation). 
    length = ''

    pole_models = []

    # <<< pole_type_asset
    # @generated
    def __init__(self, diameter='', length='', pole_models=[], **kw_args):
        """ Initialises a new 'PoleTypeAsset' instance.
        """
        self.diameter = diameter
        self.length = length
        self.pole_models = pole_models

        super(PoleTypeAsset, self).__init__(**kw_args)
    # >>> pole_type_asset


class FACTSDeviceTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic Flexible alternating current transmission systems (FACTS) devices that may be used for various purposes such as work planning.
    """
    factsdevice_asset_models = []

    # <<< factsdevice_type_asset
    # @generated
    def __init__(self, factsdevice_asset_models=[], **kw_args):
        """ Initialises a new 'FACTSDeviceTypeAsset' instance.
        """
        self.factsdevice_asset_models = factsdevice_asset_models

        super(FACTSDeviceTypeAsset, self).__init__(**kw_args)
    # >>> factsdevice_type_asset


class SVCTypeAsset(FACTSDeviceTypeAsset):
    """ Documentation for a generic Static Var Compensator (SVC) that may be used for various purposes such as work planning.
    """
    svcasset_models = []

    svc_infos = []

    # <<< svctype_asset
    # @generated
    def __init__(self, svcasset_models=[], svc_infos=[], **kw_args):
        """ Initialises a new 'SVCTypeAsset' instance.
        """
        self.svcasset_models = svcasset_models
        self.svc_infos = svc_infos

        super(SVCTypeAsset, self).__init__(**kw_args)
    # >>> svctype_asset


class StreetlightTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic streetlight that may be used for various purposes such as work planning. Use 'category' for utility specific categorisation, such as luminar, grid light, lantern, open bottom, flood, etc.
    """
    # Nominal (as designed) power rating of light. 
    light_rating = ''

    streetlight_asset_models = []

    # <<< streetlight_type_asset
    # @generated
    def __init__(self, light_rating='', streetlight_asset_models=[], **kw_args):
        """ Initialises a new 'StreetlightTypeAsset' instance.
        """
        self.light_rating = light_rating
        self.streetlight_asset_models = streetlight_asset_models

        super(StreetlightTypeAsset, self).__init__(**kw_args)
    # >>> streetlight_type_asset


class BusbarTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic busbar that may be used for design purposes. It is typically associated with PoserSystemResource BusbarSection.
    """
    busbar_type_assets = []

    # <<< busbar_type_asset
    # @generated
    def __init__(self, busbar_type_assets=[], **kw_args):
        """ Initialises a new 'BusbarTypeAsset' instance.
        """
        self.busbar_type_assets = busbar_type_assets

        super(BusbarTypeAsset, self).__init__(**kw_args)
    # >>> busbar_type_asset


class LinearConductorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic linear conductor that may be used for various purposes such as work planning. ElectricalProperties are defined as being per unit length (which is defined by 'unitLength').
    """
    # Radius of the conductor 
    radius = 0

    # True if conductor is insultated. 
    insulated = False

    # Commonly referred to size for this type of conductor. 
    size = ''

    # Geometric mean radius (GMR): If the conductor were replaced by a thin walled tube of radius with this value, then its reactance would be identical to that of the actual conductor. 
    gmr = ''

    # Length of each unit of the LinearConductor which has characteristics defined by the associated ElectricalProperties. 
    unit_length = 0

    conductor_type = None

    linear_conductor_asset_models = []

    conductors = []

    linear_conductor_assets = []

    # <<< linear_conductor_type_asset
    # @generated
    def __init__(self, radius=0, insulated=False, size='', gmr='', unit_length=0, conductor_type=None, linear_conductor_asset_models=[], conductors=[], linear_conductor_assets=[], **kw_args):
        """ Initialises a new 'LinearConductorTypeAsset' instance.
        """
        self.radius = radius
        self.insulated = insulated
        self.size = size
        self.gmr = gmr
        self.unit_length = unit_length
        self.conductor_type = conductor_type
        self.linear_conductor_asset_models = linear_conductor_asset_models
        self.conductors = conductors
        self.linear_conductor_assets = linear_conductor_assets

        super(LinearConductorTypeAsset, self).__init__(**kw_args)
    # >>> linear_conductor_type_asset


class MeterTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic meter that may be used for design purposes. Rather than being associated with CustomerMeter, it is associated with EnergyConsumer as it may be used for many applications, such as tie line metering, in addition to customer metering.
    """
    meter_asset_models = []

    # <<< meter_type_asset
    # @generated
    def __init__(self, meter_asset_models=[], **kw_args):
        """ Initialises a new 'MeterTypeAsset' instance.
        """
        self.meter_asset_models = meter_asset_models

        super(MeterTypeAsset, self).__init__(**kw_args)
    # >>> meter_type_asset


class ShuntCompensatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic shunt compensator that may be used for design purposes.
    """
    # Maximum allowed Apparent Power loss 
    max_power_loss = ''

    shunt_impedance_info = None

    shunt_compensator_asset_models = []

    # <<< shunt_compensator_type_asset
    # @generated
    def __init__(self, max_power_loss='', shunt_impedance_info=None, shunt_compensator_asset_models=[], **kw_args):
        """ Initialises a new 'ShuntCompensatorTypeAsset' instance.
        """
        self.max_power_loss = max_power_loss
        self.shunt_impedance_info = shunt_impedance_info
        self.shunt_compensator_asset_models = shunt_compensator_asset_models

        super(ShuntCompensatorTypeAsset, self).__init__(**kw_args)
    # >>> shunt_compensator_type_asset


class ComFunctionTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic communication function that may be used for various purposes such as work planning.
    """
    com_function_asset_models = []

    # <<< com_function_type_asset
    # @generated
    def __init__(self, com_function_asset_models=[], **kw_args):
        """ Initialises a new 'ComFunctionTypeAsset' instance.
        """
        self.com_function_asset_models = com_function_asset_models

        super(ComFunctionTypeAsset, self).__init__(**kw_args)
    # >>> com_function_type_asset


class TapChangerTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic tap changers that may be used for various purposes such as work planning.
    """
    # Tap step increment, in per cent of nominal voltage, per step position. 
    step_voltage_increment = ''

    # Phase shift, in degrees, per step position 
    step_phase_increment = ''

    # Highest possible tap step position, advance from neutral 
    high_step = 0

    # The neutral tap step position for this type of winding. 
    neutral_step = 0

    # Maximum allowed delay for isubsequent tap changer operations 
    subsequent_delay = ''

    # Maximum allowed delay for initial tap changer operation (first step change) 
    initial_delay = ''

    # Lowest possible tap step position, retard from neutral 
    low_step = 0

    tap_changer_models = []

    # <<< tap_changer_type_asset
    # @generated
    def __init__(self, step_voltage_increment='', step_phase_increment='', high_step=0, neutral_step=0, subsequent_delay='', initial_delay='', low_step=0, tap_changer_models=[], **kw_args):
        """ Initialises a new 'TapChangerTypeAsset' instance.
        """
        self.step_voltage_increment = step_voltage_increment
        self.step_phase_increment = step_phase_increment
        self.high_step = high_step
        self.neutral_step = neutral_step
        self.subsequent_delay = subsequent_delay
        self.initial_delay = initial_delay
        self.low_step = low_step
        self.tap_changer_models = tap_changer_models

        super(TapChangerTypeAsset, self).__init__(**kw_args)
    # >>> tap_changer_type_asset


class ResistorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic resistor that may be used for design purposes.
    """
    resistor_asset_models = []

    resistors = []

    # <<< resistor_type_asset
    # @generated
    def __init__(self, resistor_asset_models=[], resistors=[], **kw_args):
        """ Initialises a new 'ResistorTypeAsset' instance.
        """
        self.resistor_asset_models = resistor_asset_models
        self.resistors = resistors

        super(ResistorTypeAsset, self).__init__(**kw_args)
    # >>> resistor_type_asset


class OverheadConductorTypeAsset(LinearConductorTypeAsset):
    """ Documentation for a generic Overhead Conductor that may be used for various purposes such as work planning. It is a specialisation of LinearConductor that includes attributes specific to overhead lines.
    """
    # Spacing between the individual conductor strands for a single overhead conductor phase. 
    conductor_spacing = ''

    # Number of conductor strands for a particular overhead conductor phase. Separate phases and their spacings are modelled by the MountingPoint positions for the structure the Overhead Conductor is supported by (e.g. pole, tower) 
    conductor_count = 0

    overhead_conductor_models = []

    # <<< overhead_conductor_type_asset
    # @generated
    def __init__(self, conductor_spacing='', conductor_count=0, overhead_conductor_models=[], **kw_args):
        """ Initialises a new 'OverheadConductorTypeAsset' instance.
        """
        self.conductor_spacing = conductor_spacing
        self.conductor_count = conductor_count
        self.overhead_conductor_models = overhead_conductor_models

        super(OverheadConductorTypeAsset, self).__init__(**kw_args)
    # >>> overhead_conductor_type_asset


class CableTypeAsset(LinearConductorTypeAsset):
    """ Documentation for a generic cable that may be used for various purposes such as work planning. It is a specialisation of LinearConductor that includes attributes specific to underground cables.
    """
    # Outside diameter over the shield (inches) for a non concentric neutral cable. 
    outside_diameter = ''

    # Phase conductor spacing for a three conductor cable if a distribution model is given. 
    conductor_spacing = ''

    # Thickness of the shielding 
    shield_thickness = ''

    cable_asset_models = []

    # <<< cable_type_asset
    # @generated
    def __init__(self, outside_diameter='', conductor_spacing='', shield_thickness='', cable_asset_models=[], **kw_args):
        """ Initialises a new 'CableTypeAsset' instance.
        """
        self.outside_diameter = outside_diameter
        self.conductor_spacing = conductor_spacing
        self.shield_thickness = shield_thickness
        self.cable_asset_models = cable_asset_models

        super(CableTypeAsset, self).__init__(**kw_args)
    # >>> cable_type_asset


# <<< inf_type_asset
# @generated
# >>> inf_type_asset

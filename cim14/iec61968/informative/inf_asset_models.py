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
    # Unit cost for an asset model from a specific supplier, either for a unit cost or cost per unit length. Cost is for material or asset only and does not include labor to install/construct or configure it. 
    unit_cost = ''

    erp_poline_items = []

    asset_model = None

    asset_model_catalogue = None

    erp_quote_line_items = []

    # <<< asset_model_catalogue_item
    # @generated
    def __init__(self, unit_cost='', erp_poline_items=[], asset_model=None, asset_model_catalogue=None, erp_quote_line_items=[], **kw_args):
        """ Initialises a new 'AssetModelCatalogueItem' instance.
        """
        self.unit_cost = unit_cost
        self.erp_poline_items = erp_poline_items
        self.asset_model = asset_model
        self.asset_model_catalogue = asset_model_catalogue
        self.erp_quote_line_items = erp_quote_line_items

        super(AssetModelCatalogueItem, self).__init__(**kw_args)
    # >>> asset_model_catalogue_item


class CompositeSwitchAssetModel(AssetModel):
    """ Documentation for a type of a composite switch asset of a particular product model made by a manufacturer.
    """
    composite_switch_type_asset = None

    composite_switch_assets = []

    composite_switch_info = None

    # <<< composite_switch_asset_model
    # @generated
    def __init__(self, composite_switch_type_asset=None, composite_switch_assets=[], composite_switch_info=None, **kw_args):
        """ Initialises a new 'CompositeSwitchAssetModel' instance.
        """
        self.composite_switch_type_asset = composite_switch_type_asset
        self.composite_switch_assets = composite_switch_assets
        self.composite_switch_info = composite_switch_info

        super(CompositeSwitchAssetModel, self).__init__(**kw_args)
    # >>> composite_switch_asset_model


class WorkEquipmentAssetModel(AssetModel):
    """ Documentation for a type of an equipment used for work of a particular product model made by a manufacturer.
    """
    work_equipment_assets = []

    work_equipment_type_asset = None

    # <<< work_equipment_asset_model
    # @generated
    def __init__(self, work_equipment_assets=[], work_equipment_type_asset=None, **kw_args):
        """ Initialises a new 'WorkEquipmentAssetModel' instance.
        """
        self.work_equipment_assets = work_equipment_assets
        self.work_equipment_type_asset = work_equipment_type_asset

        super(WorkEquipmentAssetModel, self).__init__(**kw_args)
    # >>> work_equipment_asset_model


class AssetFunctionAssetModel(AssetModel):
    """ Documentation for a type of an asset function of a particular product model made by a manufacturer.(Organisation). Asset Functions are typically component parts of Assets or Asset Containers.
    """
    asset_functions = []

    asset_function_type_asset = None

    # <<< asset_function_asset_model
    # @generated
    def __init__(self, asset_functions=[], asset_function_type_asset=None, **kw_args):
        """ Initialises a new 'AssetFunctionAssetModel' instance.
        """
        self.asset_functions = asset_functions
        self.asset_function_type_asset = asset_function_type_asset

        super(AssetFunctionAssetModel, self).__init__(**kw_args)
    # >>> asset_function_asset_model


class CabinetModel(AssetModel):
    """ Documentation for a type of Cabinet of a particular product model made by a manufacturer.
    """
    cabinets = []

    cabinet_type_asset = None

    # <<< cabinet_model
    # @generated
    def __init__(self, cabinets=[], cabinet_type_asset=None, **kw_args):
        """ Initialises a new 'CabinetModel' instance.
        """
        self.cabinets = cabinets
        self.cabinet_type_asset = cabinet_type_asset

        super(CabinetModel, self).__init__(**kw_args)
    # >>> cabinet_model


class PoleModel(AssetModel):
    """ A type of pole supplied by a given manufacturer.
    """
    # Pole species. Aluminum, Aluminum Davit, Concrete, Fiberglass, Galvanized Davit, Galvanized, Steel Davit Primed, Steel Davit, Steel Standard Primed, Steel, Truncated, Wood-Treated, Wood-Hard, Wood-Salt Treated, Wood-Soft, Wood, Other, Unknown. 
    species_type = ''

    # Pole class: 1, 2, 3, 4, 5, 6, 7, H1, H2, Other, Unknown. 
    classification = ''

    poles = []

    pole_type_asset = None

    # <<< pole_model
    # @generated
    def __init__(self, species_type='', classification='', poles=[], pole_type_asset=None, **kw_args):
        """ Initialises a new 'PoleModel' instance.
        """
        self.species_type = species_type
        self.classification = classification
        self.poles = poles
        self.pole_type_asset = pole_type_asset

        super(PoleModel, self).__init__(**kw_args)
    # >>> pole_model


class TowerAssetModel(AssetModel):
    """ A type of tower supplied by a given manufacturer or constructed from a common design.
    """
    towers = []

    tower_type_asset = None

    # <<< tower_asset_model
    # @generated
    def __init__(self, towers=[], tower_type_asset=None, **kw_args):
        """ Initialises a new 'TowerAssetModel' instance.
        """
        self.towers = towers
        self.tower_type_asset = tower_type_asset

        super(TowerAssetModel, self).__init__(**kw_args)
    # >>> tower_asset_model


class ElectricalAssetModel(AssetModel):
    """ Documentation for a type of ElectricalAsset of a particular product model made by a manufacturer.
    """
    electrical_info_roles = []

    # <<< electrical_asset_model
    # @generated
    def __init__(self, electrical_info_roles=[], **kw_args):
        """ Initialises a new 'ElectricalAssetModel' instance.
        """
        self.electrical_info_roles = electrical_info_roles

        super(ElectricalAssetModel, self).__init__(**kw_args)
    # >>> electrical_asset_model


class VehicleAssetModel(AssetModel):
    """ Documentation for a type of a vehicle of a particular product model made by a manufacturer.
    """
    vehicle_type_asset = None

    vehicles = []

    # <<< vehicle_asset_model
    # @generated
    def __init__(self, vehicle_type_asset=None, vehicles=[], **kw_args):
        """ Initialises a new 'VehicleAssetModel' instance.
        """
        self.vehicle_type_asset = vehicle_type_asset
        self.vehicles = vehicles

        super(VehicleAssetModel, self).__init__(**kw_args)
    # >>> vehicle_asset_model


class AssetModelCatalogue(IdentifiedObject):
    """ Catalogue of available types of products and materials that are used to build or install, maintain or operate an Asset. Each catalogue item is for a specific product (AssetModel) available from a specific supplier.
    """
    status = None

    asset_model_catalogue_items = []

    # <<< asset_model_catalogue
    # @generated
    def __init__(self, status=None, asset_model_catalogue_items=[], **kw_args):
        """ Initialises a new 'AssetModelCatalogue' instance.
        """
        self.status = status
        self.asset_model_catalogue_items = asset_model_catalogue_items

        super(AssetModelCatalogue, self).__init__(**kw_args)
    # >>> asset_model_catalogue


class ToolAssetModel(AssetModel):
    """ Documentation for a type of a tool of a particular product model made by a manufacturer.
    """
    tool_type_asset = None

    tools = []

    # <<< tool_asset_model
    # @generated
    def __init__(self, tool_type_asset=None, tools=[], **kw_args):
        """ Initialises a new 'ToolAssetModel' instance.
        """
        self.tool_type_asset = tool_type_asset
        self.tools = tools

        super(ToolAssetModel, self).__init__(**kw_args)
    # >>> tool_asset_model


class TransformerAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a transformer of a particular product model made by a manufacturer.
    """
    # Core kind of this transformer product. Values are: "shell", "core"
    core_kind = 'shell'

    # Type of insultation used for transformer windings: Paper, Thermally Upgraded Paper, Nomex, other Values are: "thermally_upgraded_paper", "other", "nomex", "paper"
    winding_insulation_kind = 'thermally_upgraded_paper'

    # True if this is an autotransformer, false otherwise. 
    auto_transformer = False

    # Weight of core and coils in transformer. 
    core_coils_weight = ''

    # Weight of solid insultation in transformer. 
    solid_insulation_weight = ''

    # Basic Insulation Level of Neutral 
    neutral_bil = ''

    # Kind of oil preservation system. Values are: "nitrogen_blanket", "conservator", "free_breathing", "other"
    oil_preservation_kind = 'nitrogen_blanket'

    transformer_info = None

    transformer_type_asset = None

    transformer_assets = []

    # <<< transformer_asset_model
    # @generated
    def __init__(self, core_kind='shell', winding_insulation_kind='thermally_upgraded_paper', auto_transformer=False, core_coils_weight='', solid_insulation_weight='', neutral_bil='', oil_preservation_kind='nitrogen_blanket', transformer_info=None, transformer_type_asset=None, transformer_assets=[], **kw_args):
        """ Initialises a new 'TransformerAssetModel' instance.
        """
        self.core_kind = core_kind
        self.winding_insulation_kind = winding_insulation_kind
        self.auto_transformer = auto_transformer
        self.core_coils_weight = core_coils_weight
        self.solid_insulation_weight = solid_insulation_weight
        self.neutral_bil = neutral_bil
        self.oil_preservation_kind = oil_preservation_kind
        self.transformer_info = transformer_info
        self.transformer_type_asset = transformer_type_asset
        self.transformer_assets = transformer_assets

        super(TransformerAssetModel, self).__init__(**kw_args)
    # >>> transformer_asset_model


class GeneratorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of generation equipment of a particular product model made by a manufacturer.
    """
    generator_type_asset = None

    generator_assets = []

    # <<< generator_asset_model
    # @generated
    def __init__(self, generator_type_asset=None, generator_assets=[], **kw_args):
        """ Initialises a new 'GeneratorAssetModel' instance.
        """
        self.generator_type_asset = generator_type_asset
        self.generator_assets = generator_assets

        super(GeneratorAssetModel, self).__init__(**kw_args)
    # >>> generator_asset_model


class StreetlightAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a streelight of a particular product model made by a manufacturer.
    """
    # Lamp kind supplied from manufacturer (vs. one that has been replaced in the field). Values are: "high_pressure_sodium", "other", "mercury_vapor", "metal_halide"
    lamp_kind = 'high_pressure_sodium'

    # Power rating of light as supplied by the manufacturer. 
    light_rating = ''

    streetlight_type_assets = []

    streetlights = []

    # <<< streetlight_asset_model
    # @generated
    def __init__(self, lamp_kind='high_pressure_sodium', light_rating='', streetlight_type_assets=[], streetlights=[], **kw_args):
        """ Initialises a new 'StreetlightAssetModel' instance.
        """
        self.lamp_kind = lamp_kind
        self.light_rating = light_rating
        self.streetlight_type_assets = streetlight_type_assets
        self.streetlights = streetlights

        super(StreetlightAssetModel, self).__init__(**kw_args)
    # >>> streetlight_asset_model


class ProtectionEquipmentAssetModel(ElectricalAssetModel):
    """ Documentation for a type of protection equipment asset of a particular product model made by a manufacturer.
    """
    protection_equipment_assets = []

    protection_equipment_type_asset = None

    # <<< protection_equipment_asset_model
    # @generated
    def __init__(self, protection_equipment_assets=[], protection_equipment_type_asset=None, **kw_args):
        """ Initialises a new 'ProtectionEquipmentAssetModel' instance.
        """
        self.protection_equipment_assets = protection_equipment_assets
        self.protection_equipment_type_asset = protection_equipment_type_asset

        super(ProtectionEquipmentAssetModel, self).__init__(**kw_args)
    # >>> protection_equipment_asset_model


class FaultIndicatorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an FaultIndicator asset of a particular product model made by a manufacturer.
    """
    fault_indicator_type_asset = None

    fault_indicator_assets = []

    # <<< fault_indicator_asset_model
    # @generated
    def __init__(self, fault_indicator_type_asset=None, fault_indicator_assets=[], **kw_args):
        """ Initialises a new 'FaultIndicatorAssetModel' instance.
        """
        self.fault_indicator_type_asset = fault_indicator_type_asset
        self.fault_indicator_assets = fault_indicator_assets

        super(FaultIndicatorAssetModel, self).__init__(**kw_args)
    # >>> fault_indicator_asset_model


class LinearConductorAssetModel(ElectricalAssetModel):
    """ A type of linear conductor made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    # True if conductor is insultated. 
    insulated = False

    # Usage of this linear conductor product. Values are: "transmission", "other", "distribution"
    usage = 'transmission'

    # Kind of insulation material. Values are: "unbelted_pilc", "varnished_cambric_cloth", "tree_resistant_high_molecular_weight_polyethylene", "butyl", "low_capacitance_rubber", "high_molecular_weight_polyethylene", "other", "tree_retardant_crosslinked_polyethylene", "crosslinked_polyethylene", "silicon_rubber", "oil_paper", "ethylene_propylene_rubber", "varnished_dacron_glass", "rubber", "ozone_resistant_rubber", "belted_pilc", "asbestos_and_varnished_cambric"
    insulation_kind = 'unbelted_pilc'

    # Radius of the conductor 
    radius = 0

    # Geometric Mean Radius. If the conductor were replaced by a thin walled tube of radius gMR then its reactance would be identical to that of the actual conductor 
    g_mr = ''

    # Commonly referred to size for this type of conductor. 
    size = ''

    linear_conductor_type_asset = None

    linear_conductor_assets = []

    # <<< linear_conductor_asset_model
    # @generated
    def __init__(self, insulated=False, usage='transmission', insulation_kind='unbelted_pilc', radius=0, g_mr='', size='', linear_conductor_type_asset=None, linear_conductor_assets=[], **kw_args):
        """ Initialises a new 'LinearConductorAssetModel' instance.
        """
        self.insulated = insulated
        self.usage = usage
        self.insulation_kind = insulation_kind
        self.radius = radius
        self.g_mr = g_mr
        self.size = size
        self.linear_conductor_type_asset = linear_conductor_type_asset
        self.linear_conductor_assets = linear_conductor_assets

        super(LinearConductorAssetModel, self).__init__(**kw_args)
    # >>> linear_conductor_asset_model


class SwitchAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a switch asset of a particular product model made by a manufacturer.
    """
    switch_info = None

    switch_type_asset = None

    switch_assets = []

    # <<< switch_asset_model
    # @generated
    def __init__(self, switch_info=None, switch_type_asset=None, switch_assets=[], **kw_args):
        """ Initialises a new 'SwitchAssetModel' instance.
        """
        self.switch_info = switch_info
        self.switch_type_asset = switch_type_asset
        self.switch_assets = switch_assets

        super(SwitchAssetModel, self).__init__(**kw_args)
    # >>> switch_asset_model


class PotentialTransformerAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Potential Transformer (PT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    potential_transformer_type_asset = None

    potential_transformer_assets = []

    potential_transformer_info = None

    # <<< potential_transformer_asset_model
    # @generated
    def __init__(self, potential_transformer_type_asset=None, potential_transformer_assets=[], potential_transformer_info=None, **kw_args):
        """ Initialises a new 'PotentialTransformerAssetModel' instance.
        """
        self.potential_transformer_type_asset = potential_transformer_type_asset
        self.potential_transformer_assets = potential_transformer_assets
        self.potential_transformer_info = potential_transformer_info

        super(PotentialTransformerAssetModel, self).__init__(**kw_args)
    # >>> potential_transformer_asset_model


class ResistorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a resistor asset of a particular product model made by a manufacturer.
    """
    resistor_assets = []

    resistor_type_asset = None

    # <<< resistor_asset_model
    # @generated
    def __init__(self, resistor_assets=[], resistor_type_asset=None, **kw_args):
        """ Initialises a new 'ResistorAssetModel' instance.
        """
        self.resistor_assets = resistor_assets
        self.resistor_type_asset = resistor_type_asset

        super(ResistorAssetModel, self).__init__(**kw_args)
    # >>> resistor_asset_model


class MeterAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a meter asset of a particular product model made by a manufacturer.
    """
    # Meter form number. 
    form = ''

    # True when the meter or the installed AMR option is capable of capturing kWh interval data. 
    load_profile_meter = False

    # True when the meter is capable of metering real energy in kWh. 
    kwh_meter = False

    # True when the meter is capable of metering reactive energy in kVArh. 
    reactive_meter = False

    # Number of wires. 
    wire_count = 0

    # True when the meter or installed AMR option is capable of capturing demand data. 
    demand_meter = False

    # Maximum number of registers this meter model can support. The actual number in use is based on the number of Registers associated with a given MeterAsset. 
    max_register_count = 0

    # True when the meter or meter+AMR module are capable of offering TOU data. 
    time_of_use_meter = False

    # True when the meter is capable of metering apparent energy in kVAh. 
    k_vah_meter = False

    # Meter kh (watthour) constant. This constant is the number of watthours that must be applied to the meter to cause one disk revolution for an electromechanical meter or the number of watthours represented by one increment pulse for an electronic meter. 
    k_h = 0.0

    # True when the meter is capable of metering reactive energy in kQh. 
    q_meter = False

    # True when the meter or the installed AMR option is capable of capturing interval data for a user selectable measurement (kWh, Volts, or some other billing or engineering quantity). 
    interval_data_meter = False

    # Meter register ratio. 
    register_ratio = 0.0

    meter_type_asset = None

    meter_assets = []

    # <<< meter_asset_model
    # @generated
    def __init__(self, form='', load_profile_meter=False, kwh_meter=False, reactive_meter=False, wire_count=0, demand_meter=False, max_register_count=0, time_of_use_meter=False, k_vah_meter=False, k_h=0.0, q_meter=False, interval_data_meter=False, register_ratio=0.0, meter_type_asset=None, meter_assets=[], **kw_args):
        """ Initialises a new 'MeterAssetModel' instance.
        """
        self.form = form
        self.load_profile_meter = load_profile_meter
        self.kwh_meter = kwh_meter
        self.reactive_meter = reactive_meter
        self.wire_count = wire_count
        self.demand_meter = demand_meter
        self.max_register_count = max_register_count
        self.time_of_use_meter = time_of_use_meter
        self.k_vah_meter = k_vah_meter
        self.k_h = k_h
        self.q_meter = q_meter
        self.interval_data_meter = interval_data_meter
        self.register_ratio = register_ratio
        self.meter_type_asset = meter_type_asset
        self.meter_assets = meter_assets

        super(MeterAssetModel, self).__init__(**kw_args)
    # >>> meter_asset_model


class SurgeProtectorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an SurgeProtector asset of a particular product model made by a manufacturer.
    """
    surge_protector_assets = []

    surge_protector_type_asset = None

    # <<< surge_protector_asset_model
    # @generated
    def __init__(self, surge_protector_assets=[], surge_protector_type_asset=None, **kw_args):
        """ Initialises a new 'SurgeProtectorAssetModel' instance.
        """
        self.surge_protector_assets = surge_protector_assets
        self.surge_protector_type_asset = surge_protector_type_asset

        super(SurgeProtectorAssetModel, self).__init__(**kw_args)
    # >>> surge_protector_asset_model


class OverheadConductorAssetModel(LinearConductorAssetModel):
    """ A type of linear conductor model made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    overhead_conductor_type_asset = None

    # <<< overhead_conductor_asset_model
    # @generated
    def __init__(self, overhead_conductor_type_asset=None, **kw_args):
        """ Initialises a new 'OverheadConductorAssetModel' instance.
        """
        self.overhead_conductor_type_asset = overhead_conductor_type_asset

        super(OverheadConductorAssetModel, self).__init__(**kw_args)
    # >>> overhead_conductor_asset_model


class ShuntCompensatorAssetModel(ElectricalAssetModel):
    """ For application as shunt capacitor or reactor or switchable bank of shunt capacitors or reactors, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer (Organisation). There are typically many instances of an asset associated with a single asset model.
    """
    shunt_compensator_assets = []

    shunt_compensator_type_asset = None

    shunt_impedance_info = None

    # <<< shunt_compensator_asset_model
    # @generated
    def __init__(self, shunt_compensator_assets=[], shunt_compensator_type_asset=None, shunt_impedance_info=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorAssetModel' instance.
        """
        self.shunt_compensator_assets = shunt_compensator_assets
        self.shunt_compensator_type_asset = shunt_compensator_type_asset
        self.shunt_impedance_info = shunt_impedance_info

        super(ShuntCompensatorAssetModel, self).__init__(**kw_args)
    # >>> shunt_compensator_asset_model


class CableAssetModel(LinearConductorAssetModel):
    """ Documentation for a type of a Cable of a particular product model made by a manufacturer.
    """
    # Insulating compound name. 
    insulating_compound = ''

    # Sheath material: Lead, Copper, Steel, Aluminum, None. 
    sheath_material = ''

    # Kind of conductor shield of this cable product. Values are: "superclean", "free_form", "conventional", "supersmooth", "other"
    shield_kind = 'superclean'

    # Center to neutral conductor spacing for a concentric neutral cable (blank for a transmission model). 
    center_to_neutral = ''

    # Thickness of the insulation. 
    insulation_thickness = ''

    # Neutral conductor design: Concentric Neutral, Copper Tape, Aluminum Tape, Lead insulation, Other. 
    neutral_cond_design = ''

    # Kind of outer jacket of this cable. Values are: "other", "linear_low_density_polyethylene", "insulating", "semiconducting", "polyethylene", "pvc", "none"
    outer_jacket_kind = 'other'

    # Diameter of a concentric neutral strand for a concentric neutral cable. 
    diameter_to_neutral = ''

    # Kind of construction of this cable product. Values are: "segmental", "compressed", "other", "sector", "stranded", "solid", "compacted"
    construction_kind = 'segmental'

    # Maximum nominal design operating temperature. 
    nominal_temp = ''

    # Kind of conductor material of this cable product. Values are: "copper", "other", "aluminum"
    conductor_material_kind = 'copper'

    # True if wire strands are extruded in a way to fill the voids in the cable. 
    strand_fill_flag = False

    # Number of conductors physically contained in the cable. 
    conductor_count = 0

    # True if sheath is used as a neutral. 
    sheath_neutral = False

    cable_type_asset = None

    # <<< cable_asset_model
    # @generated
    def __init__(self, insulating_compound='', sheath_material='', shield_kind='superclean', center_to_neutral='', insulation_thickness='', neutral_cond_design='', outer_jacket_kind='other', diameter_to_neutral='', construction_kind='segmental', nominal_temp='', conductor_material_kind='copper', strand_fill_flag=False, conductor_count=0, sheath_neutral=False, cable_type_asset=None, **kw_args):
        """ Initialises a new 'CableAssetModel' instance.
        """
        self.insulating_compound = insulating_compound
        self.sheath_material = sheath_material
        self.shield_kind = shield_kind
        self.center_to_neutral = center_to_neutral
        self.insulation_thickness = insulation_thickness
        self.neutral_cond_design = neutral_cond_design
        self.outer_jacket_kind = outer_jacket_kind
        self.diameter_to_neutral = diameter_to_neutral
        self.construction_kind = construction_kind
        self.nominal_temp = nominal_temp
        self.conductor_material_kind = conductor_material_kind
        self.strand_fill_flag = strand_fill_flag
        self.conductor_count = conductor_count
        self.sheath_neutral = sheath_neutral
        self.cable_type_asset = cable_type_asset

        super(CableAssetModel, self).__init__(**kw_args)
    # >>> cable_asset_model


class RecloserAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a recloser asset of a particular product model made by a manufacturer.
    """
    recloser_assets = []

    recloser_type_asset = None

    recloser_info = None

    # <<< recloser_asset_model
    # @generated
    def __init__(self, recloser_assets=[], recloser_type_asset=None, recloser_info=None, **kw_args):
        """ Initialises a new 'RecloserAssetModel' instance.
        """
        self.recloser_assets = recloser_assets
        self.recloser_type_asset = recloser_type_asset
        self.recloser_info = recloser_info

        super(RecloserAssetModel, self).__init__(**kw_args)
    # >>> recloser_asset_model


class ComFunctionAssetModel(ElectricalAssetModel):
    """ Documentation for a type of communication function of a particular product model made by a manufacturer.
    """
    com_function_type_asset = None

    # <<< com_function_asset_model
    # @generated
    def __init__(self, com_function_type_asset=None, **kw_args):
        """ Initialises a new 'ComFunctionAssetModel' instance.
        """
        self.com_function_type_asset = com_function_type_asset

        super(ComFunctionAssetModel, self).__init__(**kw_args)
    # >>> com_function_asset_model


class BusbarAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a busbar asset of a particular product model made by a manufacturer.
    """
    busbar_assets = []

    busbar_asset_model = None

    # <<< busbar_asset_model
    # @generated
    def __init__(self, busbar_assets=[], busbar_asset_model=None, **kw_args):
        """ Initialises a new 'BusbarAssetModel' instance.
        """
        self.busbar_assets = busbar_assets
        self.busbar_asset_model = busbar_asset_model

        super(BusbarAssetModel, self).__init__(**kw_args)
    # >>> busbar_asset_model


class TapChangerModel(ElectricalAssetModel):
    """ Documentation for a type of a tap changer of a particular product model made by a manufacturer.
    """
    # Number of taps. 
    tap_count = 0

    # Switching kind of tap changer. Values are: "resistive", "other", "reactive", "vacuum"
    switching_kind = 'resistive'

    tap_changer_assets = []

    tap_changer_type_asset = None

    # <<< tap_changer_model
    # @generated
    def __init__(self, tap_count=0, switching_kind='resistive', tap_changer_assets=[], tap_changer_type_asset=None, **kw_args):
        """ Initialises a new 'TapChangerModel' instance.
        """
        self.tap_count = tap_count
        self.switching_kind = switching_kind
        self.tap_changer_assets = tap_changer_assets
        self.tap_changer_type_asset = tap_changer_type_asset

        super(TapChangerModel, self).__init__(**kw_args)
    # >>> tap_changer_model


class BushingModel(ElectricalAssetModel):
    """ Documentation for a type of a bushing of a particular product model made by a manufacturer.
    """
    # Kind of insulation used in this bushing model. Values are: "compound", "other", "solid_porcelain", "paperoil"
    insulation_kind = 'compound'

    bushing_asset = None

    bushing_type_asset = None

    # <<< bushing_model
    # @generated
    def __init__(self, insulation_kind='compound', bushing_asset=None, bushing_type_asset=None, **kw_args):
        """ Initialises a new 'BushingModel' instance.
        """
        self.insulation_kind = insulation_kind
        self.bushing_asset = bushing_asset
        self.bushing_type_asset = bushing_type_asset

        super(BushingModel, self).__init__(**kw_args)
    # >>> bushing_model


class BreakerAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a breaker asset of a particular product model made by a manufacturer.
    """
    breaker_type_asset = None

    breaker_info = None

    breaker_assets = []

    # <<< breaker_asset_model
    # @generated
    def __init__(self, breaker_type_asset=None, breaker_info=None, breaker_assets=[], **kw_args):
        """ Initialises a new 'BreakerAssetModel' instance.
        """
        self.breaker_type_asset = breaker_type_asset
        self.breaker_info = breaker_info
        self.breaker_assets = breaker_assets

        super(BreakerAssetModel, self).__init__(**kw_args)
    # >>> breaker_asset_model


class CurrentTransformerAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Current Transformer (CT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    current_transformer_info = None

    current_transformer_type_asset = None

    current_transformer_assets = []

    # <<< current_transformer_asset_model
    # @generated
    def __init__(self, current_transformer_info=None, current_transformer_type_asset=None, current_transformer_assets=[], **kw_args):
        """ Initialises a new 'CurrentTransformerAssetModel' instance.
        """
        self.current_transformer_info = current_transformer_info
        self.current_transformer_type_asset = current_transformer_type_asset
        self.current_transformer_assets = current_transformer_assets

        super(CurrentTransformerAssetModel, self).__init__(**kw_args)
    # >>> current_transformer_asset_model


class FACTSDeviceAssetModel(ElectricalAssetModel):
    """ A particular model of FACTS device provided from a manufacturer. A FACTS devices are used for the dynamic control of voltage, impedance and phase angle of high voltage AC transmission lines. FACTS device types include: - SVC = Static Var Compensator - STATCOM = Static Synchronous Compensator - TCPAR = Thyristor Controlled Phase-Angle Regulator - TCSC = Thyristor Controlled Series Capacitor - TCVL = Thyristor Controlled Voltage Limiter - TSBR = Thyristor Switched Braking Resistor - TSSC = Thyristor Switched Series Capacitor - UPFC = Unified Power Flow Controller
    """
    factsdevice_assets = []

    factsdevice_type_asset = None

    # <<< factsdevice_asset_model
    # @generated
    def __init__(self, factsdevice_assets=[], factsdevice_type_asset=None, **kw_args):
        """ Initialises a new 'FACTSDeviceAssetModel' instance.
        """
        self.factsdevice_assets = factsdevice_assets
        self.factsdevice_type_asset = factsdevice_type_asset

        super(FACTSDeviceAssetModel, self).__init__(**kw_args)
    # >>> factsdevice_asset_model


class SeriesCompensatorAssetModel(ElectricalAssetModel):
    """ For application as a series capacitor or reactor, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer.
    """
    series_compensator_asset = None

    shunt_compensator_type_asset = None

    # <<< series_compensator_asset_model
    # @generated
    def __init__(self, series_compensator_asset=None, shunt_compensator_type_asset=None, **kw_args):
        """ Initialises a new 'SeriesCompensatorAssetModel' instance.
        """
        self.series_compensator_asset = series_compensator_asset
        self.shunt_compensator_type_asset = shunt_compensator_type_asset

        super(SeriesCompensatorAssetModel, self).__init__(**kw_args)
    # >>> series_compensator_asset_model


class SVCAssetModel(FACTSDeviceAssetModel):
    """ Documentation for a type of a Static Var Compensator of a particular product model made by a manufacturer.
    """
    svctype_asset = None

    svcassets = []

    svc_info = None

    # <<< svcasset_model
    # @generated
    def __init__(self, svctype_asset=None, svcassets=[], svc_info=None, **kw_args):
        """ Initialises a new 'SVCAssetModel' instance.
        """
        self.svctype_asset = svctype_asset
        self.svcassets = svcassets
        self.svc_info = svc_info

        super(SVCAssetModel, self).__init__(**kw_args)
    # >>> svcasset_model


# <<< inf_asset_models
# @generated
# >>> inf_asset_models

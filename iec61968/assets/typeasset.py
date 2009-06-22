# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from  import 
from  import 
from iec61968.core2.identifiedobjectinheritance import Role
from iec61968.documentation.documentinheritance import Document

# <<< imports
# @generated
# >>> imports

class MountingPoint():
    """ Point on a structure that a connection may have a conductor  connected to. Defined with an x and y coordinate plus a phase. A connection may have multiple mounting points, one for each phases
    """
 
    x_coord = ''

 
    y_coord = ''

 
    phase = ''

    ohconductors = []

    connections = []

    # <<< mounting_point
    # @generated
    def __init__(self, x_coord='', y_coord='', phase='', ohconductors=[], connections=[], **kw_args):
        """ Initialises a new 'MountingPoint' instance.
        """
        self.x_coord = x_coord
        self.y_coord = y_coord
        self.phase = phase
        self.ohconductors = ohconductors
        self.connections = connections

        super(MountingPoint, self).__init__(**kw_args)
    # >>> mounting_point


class ElectricalProperties():
    """ Used to define either the required electrical properties of a type of asset, the actual electrical properties of an asset model or the actual electrical properties of a particular asset.  An ElectricalTypeAsset or ElectricalAssetModel may have multiple ElectricalProperties associated with it, with the ElecTAElecPropRole/ElecAMElecPropRole defining which roles in the Asset these properties refer to. e.g. a Transformer TypeAsset or AssetModel will have an ElectricalProperties association for each winding.  This can also be used to define ElectricalProperties for each phase individually by using the phases attribute thus allowing an ElectricalTypeAsset or ElectricalAssetModel to have an individual entry for each phase.  Not every property will be required for each typeof ElectricalTypeAsset. e.g. a Transformer may only have requirments for ratedKV, ratedMVA and phases, while a LinearConductor will have r,x,b and g requirements per unitLength on top of a ratedAmps and ratedKV.
    """
    # Positive sequence series resistance 
    r = ''

    # Positive sequence series reactance 
    x = ''

    # Zero sequence series resistance 
    r0 = ''

    # Zero sequence series reactance 
    x0 = ''

    # Positive sequence susceptance 
    b = ''

    # Zero sequence susceptance 
    b0 = ''

    # Positive sequence conductance 
    g = ''

    # Zero sequence conductance 
    g0 = ''

    # Rated voltage 
    rated_v = ''

    # Rated Apparent Power 
    rated_va = ''

    # Rated Current 
    rated_amps = ''

    # Basic Insulation Level (BIL) for switchgear, insulators, etc.  A reference insulation level expressed as the impulse crest voltage of a nominal wave, typically 1.2 X 50 microsecond.  This is a measure of the ability of the insulation to withstand very high voltage surges.  
    bil = ''

    # Frequency at which stated device ratings apply, typically 50 Hz or 60 Hz. 
    frequency = ''

    # This is the number of potential phases this type of asset supports, typically 0, 1 or 3.  The actual phases connected are determined from the 'phases' attribute in the associtead Wires hierarchy class (inherited from ConductingEquipment).  The number of phases connected may be dervied from the 'phases' attribute.   
    num_phases = ''

    # For an installed asset, this is the total number of electrical wires that are physically connected to it.  For a TypeAsset or AssetModel, this is the total number of wires that can potentially be connected to this asset type.  This is particularly useful to understand overall electrical configurations for distribution secondary where the number of wires can not be derived from phase information alone.  For example, 120v 2 Wires; 240v 2 Wires; 480v 1Ph 2 Wires; 120/240v 1Ph; 120/208v 3Ph Y; 120/208v 1Ph Y; 120/240v 3Ph D; 240/480v 1Ph 3 Wires; 480v 3Ph D; 240/480v 3Ph D; 277/480v 3Ph Y.  
    num_wires = ''

    electrical_assets = []

    electrical_type_assets = []

    electrical_asset_models = []

    meter_assets = []

    # <<< electrical_properties
    # @generated
    def __init__(self, r='', x='', r0='', x0='', b='', b0='', g='', g0='', rated_v='', rated_va='', rated_amps='', bil='', frequency='', num_phases='', num_wires='', electrical_assets=[], electrical_type_assets=[], electrical_asset_models=[], meter_assets=[], **kw_args):
        """ Initialises a new 'ElectricalProperties' instance.
        """
        self.r = r
        self.x = x
        self.r0 = r0
        self.x0 = x0
        self.b = b
        self.b0 = b0
        self.g = g
        self.g0 = g0
        self.rated_v = rated_v
        self.rated_va = rated_va
        self.rated_amps = rated_amps
        self.bil = bil
        self.frequency = frequency
        self.num_phases = num_phases
        self.num_wires = num_wires
        self.electrical_assets = electrical_assets
        self.electrical_type_assets = electrical_type_assets
        self.electrical_asset_models = electrical_asset_models
        self.meter_assets = meter_assets

        super(ElectricalProperties, self).__init__(**kw_args)
    # >>> electrical_properties


class Connection():
    """ A structure can have multiple connection points for electrical connections (e.g. line) each with multiple mounting points, one for each phase. e.g. a Tower may have three Connections, two with three mounting points, one for each phase and a third with a single mounting point for the neutral line. A pole, on the other hand, may have a single Connection with one, two or three mounting points depending on whether it is carrying 1,2 or 3 phases.
    """
    structural_type_assets = []

    contains_mounting_points = []

    # <<< connection
    # @generated
    def __init__(self, structural_type_assets=[], contains_mounting_points=[], **kw_args):
        """ Initialises a new 'Connection' instance.
        """
        self.structural_type_assets = structural_type_assets
        self.contains_mounting_points = contains_mounting_points

        super(Connection, self).__init__(**kw_args)
    # >>> connection


class ElecTAElecPropRole(Role):
    """ ElectricalTypeAsset - ElectrialProperties Role. Used to define what the properties refers to in the TypeAsset. e.g. for a Transformer, it will have an association to an instance of ElectricalProperties for each Winding, with the roleType defining whether it is the primary, secondary, tertiary, quartiary winding.
    """
    pass
    # <<< elec_taelec_prop_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ElecTAElecPropRole' instance.
        """

        super(ElecTAElecPropRole, self).__init__(**kw_args)
    # >>> elec_taelec_prop_role


class ElecAMElecPropRole(Role):
    """ ElectricalAssetModel - ElectrialProperties Role. Used to define what the properties refers to in the AssetModel. e.g. for a Transformer, it will have an association to an instance of ElectricalProperties for each Winding, with the roleType defining whether it is the primary, secondary, tertiary, quartiary winding.
    """
    pass
    # <<< elec_amelec_prop_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ElecAMElecPropRole' instance.
        """

        super(ElecAMElecPropRole, self).__init__(**kw_args)
    # >>> elec_amelec_prop_role


class TypeAsset(Document):
    """ Whereas an AssetModel is a particular model and version of a vendor'r product, a TypeAsset is documentation for a generic asset or material item that may be used for design purposes.  Any number of AssetModels may be used to perform this generic function.  The primary role of the TypeAsset is typically defined by the PowereSystemResource it is associated with.
    """
    # Intended usage of TypeAsset.  Examples include: Distribution Overhead, Distribution Underground, Transmission, Substation, Streetlight, Customer Substation, Unknown, Other. 
    intended_usage = ''

    # True if the type of asset is a stock item. 
    stock_flag = ''

    # The estimated unit cost of the this type of asset.  It is either for a unit cost or cost per unit length.  Cost is for material or asset only and does not include labor to install/construct or configure it. 
    est_unit_cost = ''

    # Classification for this type of asset.  Additional detail is often provided in specializations with 'type'.  As some assets do not have specialization classes, this classification may be the only type information provided. 
    classification = ''

    # True  if item is a stock item, false otherwise. 
    stock_item = ''

    # Unit of measure for the quantity. 
    unit_of_measure = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    type_asset_catalogue = None

    erp_bom_item_data = []

    erp_req_line_items = []

    erp_inventory_issues = []

    asset_models = []

    cuasset = None

    cuequipment_asset = None

    # <<< type_asset
    # @generated
    def __init__(self, intended_usage='', stock_flag='', est_unit_cost='', classification='', stock_item='', unit_of_measure='', status='', status_date_time='', status_remarks='', type_asset_catalogue=None, erp_bom_item_data=[], erp_req_line_items=[], erp_inventory_issues=[], asset_models=[], cuasset=None, cuequipment_asset=None, **kw_args):
        """ Initialises a new 'TypeAsset' instance.
        """
        self.intended_usage = intended_usage
        self.stock_flag = stock_flag
        self.est_unit_cost = est_unit_cost
        self.classification = classification
        self.stock_item = stock_item
        self.unit_of_measure = unit_of_measure
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_asset_catalogue = type_asset_catalogue
        self.erp_bom_item_data = erp_bom_item_data
        self.erp_req_line_items = erp_req_line_items
        self.erp_inventory_issues = erp_inventory_issues
        self.asset_models = asset_models
        self.cuasset = cuasset
        self.cuequipment_asset = cuequipment_asset

        super(TypeAsset, self).__init__(**kw_args)
    # >>> type_asset


class TypeAssetVersion(object):
 
    version = ''

 
    date = ''

    # <<< type_asset_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'TypeAssetVersion' instance.
        """
        self.version = version
        self.date = date

        super(TypeAssetVersion, self).__init__(**kw_args)
    # >>> type_asset_version


class StructuralTypeAsset(TypeAsset):
    """ A Type of Structural Asset with properties common to a large number of asset models. 
    """
    # Material the structure is made of: e.g. wood, steel, concrete 
    material = ''

    # The maximum rated voltage of the equipment that can be mounted on/contained within the structure. 
    rated_kv = ''

    connection_mounts = []

    # <<< structural_type_asset
    # @generated
    def __init__(self, material='', rated_kv='', connection_mounts=[], **kw_args):
        """ Initialises a new 'StructuralTypeAsset' instance.
        """
        self.material = material
        self.rated_kv = rated_kv
        self.connection_mounts = connection_mounts

        super(StructuralTypeAsset, self).__init__(**kw_args)
    # >>> structural_type_asset


class ElectricalTypeAsset(TypeAsset):
    """ Generic TypeAsset for all types of component in the network that have electrical characteristics
    """
    electrical_properties = []

    # <<< electrical_type_asset
    # @generated
    def __init__(self, electrical_properties=[], **kw_args):
        """ Initialises a new 'ElectricalTypeAsset' instance.
        """
        self.electrical_properties = electrical_properties

        super(ElectricalTypeAsset, self).__init__(**kw_args)
    # >>> electrical_type_asset


class TransformerTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic transformers that may be used for various purposes such as work planning. The operating voltages can be found via the ElectricalProperties class. e.g. a two winding transformer will have two instances of ElectricalProperties and the primary/secondaryWinding can be found from the ratedKV attributes of each and the ElecTAElecPropRole used to define which is the primaryWinding and which is the secondaryWinding
    """
    # The type of transformer:  Power transformer; Voltage regulator; Autotransformer; Secondary transformer; Other 
    transformer_type = ''

 
    number_windings = ''

    # Maximum loss of power in the transformer core 
    core_loss = ''

    transformer_models = []

    transformer_properties = None

    # <<< transformer_type_asset
    # @generated
    def __init__(self, transformer_type='', number_windings='', core_loss='', transformer_models=[], transformer_properties=None, **kw_args):
        """ Initialises a new 'TransformerTypeAsset' instance.
        """
        self.transformer_type = transformer_type
        self.number_windings = number_windings
        self.core_loss = core_loss
        self.transformer_models = transformer_models
        self.transformer_properties = transformer_properties

        super(TransformerTypeAsset, self).__init__(**kw_args)
    # >>> transformer_type_asset


class TapChangerTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic tap changers that may be used for various purposes such as work planning.  
    """
    # Highest possible tap step position, advance from neutral 
    high_step = ''

    # Lowest possible tap step position, retard from neutral 
    low_step = ''

    # The neutral tap step position for this type of winding. 
    neutral_step = ''

    # Maximum allowed delay for initial tap changer operation (first step change) 
    initial_delay = ''

    # Maximum allowed delay for isubsequent tap changer operations 
    subsequent_delay = ''

    # Tap step increment, in per cent of nominal voltage, per step position. 
    step_voltage_increment = ''

    # Phase shift, in degrees, per step position 
    step_phase_increment = ''

    # Type of tap changer. 
    tap_changer_type = ''

    tap_changer_models = []

    # <<< tap_changer_type_asset
    # @generated
    def __init__(self, high_step='', low_step='', neutral_step='', initial_delay='', subsequent_delay='', step_voltage_increment='', step_phase_increment='', tap_changer_type='', tap_changer_models=[], **kw_args):
        """ Initialises a new 'TapChangerTypeAsset' instance.
        """
        self.high_step = high_step
        self.low_step = low_step
        self.neutral_step = neutral_step
        self.initial_delay = initial_delay
        self.subsequent_delay = subsequent_delay
        self.step_voltage_increment = step_voltage_increment
        self.step_phase_increment = step_phase_increment
        self.tap_changer_type = tap_changer_type
        self.tap_changer_models = tap_changer_models

        super(TapChangerTypeAsset, self).__init__(**kw_args)
    # >>> tap_changer_type_asset


class GeneratorTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic generation equipment that may be used for various purposes such as work planning. It defines both the Real and Reactive power properties (modelled at the PSR level as a GeneratingUnit + SynchronousMachine)
    """
    # Type of generator: e.g. Synchronous, Inductive etc. 
    generator_type = ''

    # Maximum VAr limit 
    max_var = ''

    # Maximum Real Power limit 
    max_w = ''

    # Minimum VAr generated 
    min_var = ''

    # Minimum Real Power Generated 
    min_w = ''

    # Direct-axis subtransient resistance 
    r_direct_subtr = ''

    # Direct-axis synchronoust resistance 
    r_direct_sync = ''

    # Direct-axis Transient resistance 
    r_direct_trans = ''

    # Quadrature-axis subtransient resistance 
    r_quad_subtr = ''

    # Quadrature-axis synchronous resistance 
    r_quad_sync = ''

    # Quadrature-axis Transient resistance 
    r_quad_trans = ''

    # Direct-axis subtransient reactance 
    x_direct_subtr = ''

    # Direct-axis synchronous reactance 
    x_direct_sync = ''

    # Direct-axis Transient reactance 
    x_direct_trans = ''

    # Quadrature-axis subtransient reactance 
    x_quad_subtr = ''

    # Quadrature-axis synchronous reactance 
    x_quad_sync = ''

    # Quadrature-axis Transient reactance 
    x_quad_trans = ''

    generator_asset_models = []

    # <<< generator_type_asset
    # @generated
    def __init__(self, generator_type='', max_var='', max_w='', min_var='', min_w='', r_direct_subtr='', r_direct_sync='', r_direct_trans='', r_quad_subtr='', r_quad_sync='', r_quad_trans='', x_direct_subtr='', x_direct_sync='', x_direct_trans='', x_quad_subtr='', x_quad_sync='', x_quad_trans='', generator_asset_models=[], **kw_args):
        """ Initialises a new 'GeneratorTypeAsset' instance.
        """
        self.generator_type = generator_type
        self.max_var = max_var
        self.max_w = max_w
        self.min_var = min_var
        self.min_w = min_w
        self.r_direct_subtr = r_direct_subtr
        self.r_direct_sync = r_direct_sync
        self.r_direct_trans = r_direct_trans
        self.r_quad_subtr = r_quad_subtr
        self.r_quad_sync = r_quad_sync
        self.r_quad_trans = r_quad_trans
        self.x_direct_subtr = x_direct_subtr
        self.x_direct_sync = x_direct_sync
        self.x_direct_trans = x_direct_trans
        self.x_quad_subtr = x_quad_subtr
        self.x_quad_sync = x_quad_sync
        self.x_quad_trans = x_quad_trans
        self.generator_asset_models = generator_asset_models

        super(GeneratorTypeAsset, self).__init__(**kw_args)
    # >>> generator_type_asset


class ShuntCompensatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic shunt compensator that may be used for design purposes.  It is typically associated with PoserSystemResource ShuntCompensator.
    """
    # Type of shunt compensator. 
    shunt_compensator_type = ''

    # Maximum allowed Apparent Power loss 
    max_power_loss = ''

    shunt_compensator_asset_models = []

    shunt_impedance_properties = None

    # <<< shunt_compensator_type_asset
    # @generated
    def __init__(self, shunt_compensator_type='', max_power_loss='', shunt_compensator_asset_models=[], shunt_impedance_properties=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorTypeAsset' instance.
        """
        self.shunt_compensator_type = shunt_compensator_type
        self.max_power_loss = max_power_loss
        self.shunt_compensator_asset_models = shunt_compensator_asset_models
        self.shunt_impedance_properties = shunt_impedance_properties

        super(ShuntCompensatorTypeAsset, self).__init__(**kw_args)
    # >>> shunt_compensator_type_asset


class SwitchTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic switch asset that may be used for design purposes. 
    """
    # The type of switch. 
    switch_type = ''

    switch_asset_models = []

    switch_properties = None

    composite_switch_type_asset = None

    # <<< switch_type_asset
    # @generated
    def __init__(self, switch_type='', switch_asset_models=[], switch_properties=None, composite_switch_type_asset=None, **kw_args):
        """ Initialises a new 'SwitchTypeAsset' instance.
        """
        self.switch_type = switch_type
        self.switch_asset_models = switch_asset_models
        self.switch_properties = switch_properties
        self.composite_switch_type_asset = composite_switch_type_asset

        super(SwitchTypeAsset, self).__init__(**kw_args)
    # >>> switch_type_asset


class LinearConductorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic linear conductor that may be used for various purposes such as work planning. A type of linear conductor. Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    # The length of each unit of the LinearConductor which has characteristics defined by the associated ElectricalProperties 
    unit_length = ''

    # Defines whether the conductor is insultated or not 
    insulated = ''

    # Radius of the conductor 
    radius = ''

    # Geometric Mean Radius. If the conductor were replaced by a thin walled tube of radius gMR then its reactance would be identical to that of the actual conductor 
    g_mr = ''

    # Type of insulation material e.g. Asbestos & Varnished cambric, Butyl, EPR, HMPWE, Low Capacitance rubber etc. 
    insulation_type = ''

    # Commonly referred to size for this type of conductor. 
    size = ''

    linear_conductor_assets = []

    conductors = []

    linear_conductor_asset_models = []

    conductor_type = None

    # <<< linear_conductor_type_asset
    # @generated
    def __init__(self, unit_length='', insulated='', radius='', g_mr='', insulation_type='', size='', linear_conductor_assets=[], conductors=[], linear_conductor_asset_models=[], conductor_type=None, **kw_args):
        """ Initialises a new 'LinearConductorTypeAsset' instance.
        """
        self.unit_length = unit_length
        self.insulated = insulated
        self.radius = radius
        self.g_mr = g_mr
        self.insulation_type = insulation_type
        self.size = size
        self.linear_conductor_assets = linear_conductor_assets
        self.conductors = conductors
        self.linear_conductor_asset_models = linear_conductor_asset_models
        self.conductor_type = conductor_type

        super(LinearConductorTypeAsset, self).__init__(**kw_args)
    # >>> linear_conductor_type_asset


class PoleTypeAsset(StructuralTypeAsset):
    """ Documentation for a generic pole that may be used for various purposes such as work planning.  A pole typically has a single Connection with 1,2 or 3 mounting points.
    """
    # Diameter of the pole 
    diameter = ''

    # Length of the pole (inclusive of any section of the pole that may be underground post-installation) 
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


class TowerTypeAsset(StructuralTypeAsset):
    """ Documentation for a generic tower that may be used for various purposes such as work planning.  A tower on a network carrying one or more circuits.  A transmission tower carrying two 3-phase circuits will have 2 instances of Connection, each of which will have 3 MountingPoint instances, one for each phase all with coordinates relative to a common origin on the tower. (It may also have a 3rd Connection with a single MountingPoint for the Neutral line).
    """
    # Type of tower e.g. Suspension, Tension 
    tower_type = ''

    tower_asset_models = []

    # <<< tower_type_asset
    # @generated
    def __init__(self, tower_type='', tower_asset_models=[], **kw_args):
        """ Initialises a new 'TowerTypeAsset' instance.
        """
        self.tower_type = tower_type
        self.tower_asset_models = tower_asset_models

        super(TowerTypeAsset, self).__init__(**kw_args)
    # >>> tower_type_asset


class CableTypeAsset(LinearConductorTypeAsset):
    """ Documentation for a generic cable that may be used for various purposes such as work planning. It is a specialisation of LinearConductor that includes attributes specific to underground cables.
    """
    # Type of shield on the cable e.g. Freeform, Supersmooth, Conventional etc. 
    shield_type = ''

    # Thickness of the shielding 
    shield_thickness = ''

    # Outside diameter over the shield (inches) for a non concentric neutral cable. 
    outside_diameter = ''

    # Phase conductor spacing for a three conductor cable if a distribution model is given. 
    conductor_spacing = ''

    cable_asset_models = []

    # <<< cable_type_asset
    # @generated
    def __init__(self, shield_type='', shield_thickness='', outside_diameter='', conductor_spacing='', cable_asset_models=[], **kw_args):
        """ Initialises a new 'CableTypeAsset' instance.
        """
        self.shield_type = shield_type
        self.shield_thickness = shield_thickness
        self.outside_diameter = outside_diameter
        self.conductor_spacing = conductor_spacing
        self.cable_asset_models = cable_asset_models

        super(CableTypeAsset, self).__init__(**kw_args)
    # >>> cable_type_asset


class OHConductorTypeAsset(LinearConductorTypeAsset):
    """ Documentation for a generic Overhead Conductor that may be used for various purposes such as work planning.  It is a specialisation of LinearConductor that includes attributes specific to overhead lines.  
    """
    # Number of conductor strands for a particular overhead conductor phase. Separate phases and their spacings are modelled by the MountingPoint positions for the structure the Overhead Conductor is supported by (e.g. pole, tower) 
    conductor_count = ''

    # Spacing between the individual conductor strands for a single overhead conductor phase.  
    conductor_spacing = ''

    ohconductor_models = []

    # <<< ohconductor_type_asset
    # @generated
    def __init__(self, conductor_count='', conductor_spacing='', ohconductor_models=[], **kw_args):
        """ Initialises a new 'OHConductorTypeAsset' instance.
        """
        self.conductor_count = conductor_count
        self.conductor_spacing = conductor_spacing
        self.ohconductor_models = ohconductor_models

        super(OHConductorTypeAsset, self).__init__(**kw_args)
    # >>> ohconductor_type_asset


class SubstationTypeAsset(TypeAsset):
    # Type of substation: primary, secondary 
    substation_type = ''

    # <<< substation_type_asset
    # @generated
    def __init__(self, substation_type='', **kw_args):
        """ Initialises a new 'SubstationTypeAsset' instance.
        """
        self.substation_type = substation_type

        super(SubstationTypeAsset, self).__init__(**kw_args)
    # >>> substation_type_asset


class DuctTypeAsset(StructuralTypeAsset):
    """ A Duct contains underground cables and is contained within a duct bank.  The xCoord and yCoord attributes define its positioning within the DuctBank
    """
    # X Position of the duct within the duct bank 
    x_coord = ''

    # Y position of the duct within the duct bank 
    y_coord = ''

    cable_assets = []

    duct_type_assets = []

    # <<< duct_type_asset
    # @generated
    def __init__(self, x_coord='', y_coord='', cable_assets=[], duct_type_assets=[], **kw_args):
        """ Initialises a new 'DuctTypeAsset' instance.
        """
        self.x_coord = x_coord
        self.y_coord = y_coord
        self.cable_assets = cable_assets
        self.duct_type_assets = duct_type_assets

        super(DuctTypeAsset, self).__init__(**kw_args)
    # >>> duct_type_asset


class DuctBankTypeAsset(StructuralTypeAsset):
    """ A DuctBank contains multiple Ducts. The DuctBank itself should have no connections, since these are defined by the individual ducts within it. However, it will have a ConstructionType and the material it is constructed from.
    """
    duct_banks = []

    duct_bank_type_asset = None

    # <<< duct_bank_type_asset
    # @generated
    def __init__(self, duct_banks=[], duct_bank_type_asset=None, **kw_args):
        """ Initialises a new 'DuctBankTypeAsset' instance.
        """
        self.duct_banks = duct_banks
        self.duct_bank_type_asset = duct_bank_type_asset

        super(DuctBankTypeAsset, self).__init__(**kw_args)
    # >>> duct_bank_type_asset


class SVCProperties(ElectricalProperties):
    """ The properties for an SVC, allowing the capacitive and inductive ratings for each phase to be specified individually if required.
    """
    # Maximum capacitive reactive power 
    capacitive_rating = ''

    # Maximum inductive reactive power 
    inductive_rating = ''

    svcasset_model = None

    svcasset = None

    svctype_assets = []

    # <<< svcproperties
    # @generated
    def __init__(self, capacitive_rating='', inductive_rating='', svcasset_model=None, svcasset=None, svctype_assets=[], **kw_args):
        """ Initialises a new 'SVCProperties' instance.
        """
        self.capacitive_rating = capacitive_rating
        self.inductive_rating = inductive_rating
        self.svcasset_model = svcasset_model
        self.svcasset = svcasset
        self.svctype_assets = svctype_assets

        super(SVCProperties, self).__init__(**kw_args)
    # >>> svcproperties


class CompositeSwitchTypeAsset(TypeAsset):
    """ Documentation for a generic composite switch asset that may be used for design purposes. A composite wwitch is an amalgamation of multiple Switches.
    """
    # Type of composite switche.g. Throwover, ESCO Throwover, RAL, GRAL, Regulator Bypass, UG Multi Switch 
    composite_switch_type = ''

    composite_switch_asset_models = []

    composite_switch_properties = None

    switch_types_assets = []

    # <<< composite_switch_type_asset
    # @generated
    def __init__(self, composite_switch_type='', composite_switch_asset_models=[], composite_switch_properties=None, switch_types_assets=[], **kw_args):
        """ Initialises a new 'CompositeSwitchTypeAsset' instance.
        """
        self.composite_switch_type = composite_switch_type
        self.composite_switch_asset_models = composite_switch_asset_models
        self.composite_switch_properties = composite_switch_properties
        self.switch_types_assets = switch_types_assets

        super(CompositeSwitchTypeAsset, self).__init__(**kw_args)
    # >>> composite_switch_type_asset


class MeterTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic meter that may be used for design purposes. Rather than being associated with CustomerMeter, it is associated with EnergyConsumer as it may be used for many applications, such as tie line metering, in addition to customer metering.
    """
    # The specific type of meter. 
    type_meter = ''

    meter_asset_models = []

    service_delivery_points = []

    # <<< meter_type_asset
    # @generated
    def __init__(self, type_meter='', meter_asset_models=[], service_delivery_points=[], **kw_args):
        """ Initialises a new 'MeterTypeAsset' instance.
        """
        self.type_meter = type_meter
        self.meter_asset_models = meter_asset_models
        self.service_delivery_points = service_delivery_points

        super(MeterTypeAsset, self).__init__(**kw_args)
    # >>> meter_type_asset


class StreetlightTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic streetlight that may be used for various purposes such as work planning. 
    """
    # The specific type of streetlight.  For example, luminar, grid light, lantern, open bottom, flood, etc.  
    type_streetlight = ''

    #  Power rating of light as designed. 
    light_rating_nominal = ''

    # Lamp type, such as high pressure sodium, mercury vapor, metal halide, etc. 
    lamp_type = ''

    streetlight_asset_models = []

    # <<< streetlight_type_asset
    # @generated
    def __init__(self, type_streetlight='', light_rating_nominal='', lamp_type='', streetlight_asset_models=[], **kw_args):
        """ Initialises a new 'StreetlightTypeAsset' instance.
        """
        self.type_streetlight = type_streetlight
        self.light_rating_nominal = light_rating_nominal
        self.lamp_type = lamp_type
        self.streetlight_asset_models = streetlight_asset_models

        super(StreetlightTypeAsset, self).__init__(**kw_args)
    # >>> streetlight_type_asset


class BusbarTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic busbar that may be used for design purposes. It is typically associated with PoserSystemResource BusbarSection.
    """
    # The type of busbar. 
    busbar_type = ''

    busbar_type_asset = []

    # <<< busbar_type_asset
    # @generated
    def __init__(self, busbar_type='', busbar_type_asset=[], **kw_args):
        """ Initialises a new 'BusbarTypeAsset' instance.
        """
        self.busbar_type = busbar_type
        self.busbar_type_asset = busbar_type_asset

        super(BusbarTypeAsset, self).__init__(**kw_args)
    # >>> busbar_type_asset


class CTTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic Current Transformer (CT) that may be used for various purposes such as work planning.  Typically used as current transducer for the purpose of metering- or protection.
    """
 
    class = ''

    # CT accuracy classification 
    accuracy_class = ''

 
    accuracy_limit = ''

    # CT's may have multiple cores, 
    number_of_cores = ''

    # eg. metering, protection, etc 
    usage = ''

    # Power burden of the CT core 
    core_burden = ''

    # Maximum voltage across the secondary terminals where the CT still displays linear characteristicts. 
    knee_point_voltage = ''

    # Maximum primary current where the CT still displays linear characteristicts. 
    knee_point_current = ''

    # Nominal ratio between the primary and secondary current; i.e. 100:5 
    ct_ratio = ''

    # Maximum ratio between the primary and secondary current;  
    ct_max_ratio = ''

    # Type of CT. 
    ct_type = ''

    ctasset_models = []

    ct = []

    ctproperties = None

    # <<< cttype_asset
    # @generated
    def __init__(self, class='', accuracy_class='', accuracy_limit='', number_of_cores='', usage='', core_burden='', knee_point_voltage='', knee_point_current='', ct_ratio='', ct_max_ratio='', ct_type='', ctasset_models=[], ct=[], ctproperties=None, **kw_args):
        """ Initialises a new 'CTTypeAsset' instance.
        """
        self.class = class
        self.accuracy_class = accuracy_class
        self.accuracy_limit = accuracy_limit
        self.number_of_cores = number_of_cores
        self.usage = usage
        self.core_burden = core_burden
        self.knee_point_voltage = knee_point_voltage
        self.knee_point_current = knee_point_current
        self.ct_ratio = ct_ratio
        self.ct_max_ratio = ct_max_ratio
        self.ct_type = ct_type
        self.ctasset_models = ctasset_models
        self.ct = ct
        self.ctproperties = ctproperties

        super(CTTypeAsset, self).__init__(**kw_args)
    # >>> cttype_asset


class FACTSDeviceTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic Flexible alternating current transmission systems (FACTS) devices that may be used for various purposes such as work planning. FACTS devices are used for the dynamic control of voltage, impedance and phase angle of high voltage AC lines.  FACTS devices include: SVC = Static Var Compensator STATCOM = Static Synchronous Compensator TCPAR = Thyristor Controlled Phase-Angle Regulator TCSC = Thyristor Controlled Series Capacitor TCVL = Thyristor Controlled Voltage Limiter TSBR = Thyristor Switched Braking Resistor TSSC = Thyristor Switched Series Capacitor UPFC = Unified Power Flow Controller
    """
    # FACTS devices include: SVC = Static Var Compensator STATCOM = Static Synchronous Compensator TCPAR = Thyristor Controlled Phase-Angle Regulator TCSC = Thyristor Controlled Series Capacitor TCVL = Thyristor Controlled Voltage Limiter TSBR = Thyristor Switched Braking Resistor TSSC = Thyristor Switched Series Capacitor UPFC = Unified Power Flow Controller 
    facts_type = ''

    factsdevice_asset_models = []

    # <<< factsdevice_type_asset
    # @generated
    def __init__(self, facts_type='', factsdevice_asset_models=[], **kw_args):
        """ Initialises a new 'FACTSDeviceTypeAsset' instance.
        """
        self.facts_type = facts_type
        self.factsdevice_asset_models = factsdevice_asset_models

        super(FACTSDeviceTypeAsset, self).__init__(**kw_args)
    # >>> factsdevice_type_asset


class FaultIndicatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic fault indicator that may be used for design purposes. 
    """
    # The type of fault indicator. 
    fault_indicator_type = ''

    # Device reset mechanisim type. The following values are used: Automatic, Manual, Remote, Other. 
    reset_type = ''

    fault_indicator = []

    fault_indicator_asset_models = []

    # <<< fault_indicator_type_asset
    # @generated
    def __init__(self, fault_indicator_type='', reset_type='', fault_indicator=[], fault_indicator_asset_models=[], **kw_args):
        """ Initialises a new 'FaultIndicatorTypeAsset' instance.
        """
        self.fault_indicator_type = fault_indicator_type
        self.reset_type = reset_type
        self.fault_indicator = fault_indicator
        self.fault_indicator_asset_models = fault_indicator_asset_models

        super(FaultIndicatorTypeAsset, self).__init__(**kw_args)
    # >>> fault_indicator_type_asset


class PTTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic Potential Transformer (PT) that may be used for various purposes such as work planning.  Also known as a Voltage transformer.  A PT is typically used as voltage transducer for the purpose of metering-, protection-, or sometimes auxiliary substation supply.
    """
 
    class = ''

 
    accuracy_class = ''

 
    pt_ratio = ''

    # The type of PT. 
    pttype = ''

 
    construction_type = ''

    pt = []

    ptasset_models = []

    ptproperties = None

    # <<< pttype_asset
    # @generated
    def __init__(self, class='', accuracy_class='', pt_ratio='', pttype='', construction_type='', pt=[], ptasset_models=[], ptproperties=None, **kw_args):
        """ Initialises a new 'PTTypeAsset' instance.
        """
        self.class = class
        self.accuracy_class = accuracy_class
        self.pt_ratio = pt_ratio
        self.pttype = pttype
        self.construction_type = construction_type
        self.pt = pt
        self.ptasset_models = ptasset_models
        self.ptproperties = ptproperties

        super(PTTypeAsset, self).__init__(**kw_args)
    # >>> pttype_asset


class ResistorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic resistor that may be used for design purposes. 
    """
    # Type of resistor. 
    resistor_type = ''

    resistor = []

    resistor_asset_models = []

    # <<< resistor_type_asset
    # @generated
    def __init__(self, resistor_type='', resistor=[], resistor_asset_models=[], **kw_args):
        """ Initialises a new 'ResistorTypeAsset' instance.
        """
        self.resistor_type = resistor_type
        self.resistor = resistor
        self.resistor_asset_models = resistor_asset_models

        super(ResistorTypeAsset, self).__init__(**kw_args)
    # >>> resistor_type_asset


class SurgeProtectorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic surge arrestor that may be used for design purposes. It contains the common, generic attributes found for Surge Arrestors, which are used to prevent dangerous overvoltages, flashovers, and serious damage to equipment.
    """
    # The  type of surge protection. 
    surge_protector_type = ''

 
    nominal_design_voltage = ''

 
    maximum_current_rating = ''

 
    maximum_continous_operating_voltage = ''

 
    maximum_energy_absorption = ''

    surge_protector = []

    surge_protector_asset_models = []

    # <<< surge_protector_type_asset
    # @generated
    def __init__(self, surge_protector_type='', nominal_design_voltage='', maximum_current_rating='', maximum_continous_operating_voltage='', maximum_energy_absorption='', surge_protector=[], surge_protector_asset_models=[], **kw_args):
        """ Initialises a new 'SurgeProtectorTypeAsset' instance.
        """
        self.surge_protector_type = surge_protector_type
        self.nominal_design_voltage = nominal_design_voltage
        self.maximum_current_rating = maximum_current_rating
        self.maximum_continous_operating_voltage = maximum_continous_operating_voltage
        self.maximum_energy_absorption = maximum_energy_absorption
        self.surge_protector = surge_protector
        self.surge_protector_asset_models = surge_protector_asset_models

        super(SurgeProtectorTypeAsset, self).__init__(**kw_args)
    # >>> surge_protector_type_asset


class VehicleTypeAsset(TypeAsset):
    """ Documentation for a generic vehicle that may be used for various purposes such as work planning. 
    """
    # The type of vehicle. 
    vehicle_type = ''

    vehicle_asset_models = []

    # <<< vehicle_type_asset
    # @generated
    def __init__(self, vehicle_type='', vehicle_asset_models=[], **kw_args):
        """ Initialises a new 'VehicleTypeAsset' instance.
        """
        self.vehicle_type = vehicle_type
        self.vehicle_asset_models = vehicle_asset_models

        super(VehicleTypeAsset, self).__init__(**kw_args)
    # >>> vehicle_type_asset


class ToolTypeAsset(TypeAsset):
    """ Documentation for a generic tool that may be used for various purposes such as work planning. 
    """
    # The type of tool. 
    type_tool = ''

    tool_asset_models = []

    # <<< tool_type_asset
    # @generated
    def __init__(self, type_tool='', tool_asset_models=[], **kw_args):
        """ Initialises a new 'ToolTypeAsset' instance.
        """
        self.type_tool = type_tool
        self.tool_asset_models = tool_asset_models

        super(ToolTypeAsset, self).__init__(**kw_args)
    # >>> tool_type_asset


class EquipmentTypeAsset(TypeAsset):
    """ Documentation for generic equipment that may be used for various purposes such as work planning.  Types of equipment are used to perform units of work by crews, office staff, etc.
    """
    # The type of equipment. 
    equipment_type = ''

    equipment_asset_models = []

    # <<< equipment_type_asset
    # @generated
    def __init__(self, equipment_type='', equipment_asset_models=[], **kw_args):
        """ Initialises a new 'EquipmentTypeAsset' instance.
        """
        self.equipment_type = equipment_type
        self.equipment_asset_models = equipment_asset_models

        super(EquipmentTypeAsset, self).__init__(**kw_args)
    # >>> equipment_type_asset


class ProtectEquipTypeAsset(ElectricalTypeAsset):
    """ Documentation for generic protection equiment that may be used for design purposes. It is a type of electrical device designed to respond to input conditions in a prescribed manner and after specified conditions are met to cause contact operation or similar abrupt change in associated electric control circuits, or simply to display the detected condition.  It is closely associtaed with the ProtectionEquipment.
    """
    # The type of protection equipment. 
    p_etype = ''

    # Default ground trip setting (Amps) for this type of relay, if applicable. 
    def_ground_trip = ''

    # Default phase trip setting (Amps) for this type of relay, if applicable. 
    def_phase_trip = ''

    protect_equip_asset_models = []

    # <<< protect_equip_type_asset
    # @generated
    def __init__(self, p_etype='', def_ground_trip='', def_phase_trip='', protect_equip_asset_models=[], **kw_args):
        """ Initialises a new 'ProtectEquipTypeAsset' instance.
        """
        self.p_etype = p_etype
        self.def_ground_trip = def_ground_trip
        self.def_phase_trip = def_phase_trip
        self.protect_equip_asset_models = protect_equip_asset_models

        super(ProtectEquipTypeAsset, self).__init__(**kw_args)
    # >>> protect_equip_type_asset


class BreakerTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic breaker asset that may be used for design purposes. 
    """
    # The type of breaker. 
    breaker_type = ''

    breaker_asset_models = []

    breaker_properties = None

    # <<< breaker_type_asset
    # @generated
    def __init__(self, breaker_type='', breaker_asset_models=[], breaker_properties=None, **kw_args):
        """ Initialises a new 'BreakerTypeAsset' instance.
        """
        self.breaker_type = breaker_type
        self.breaker_asset_models = breaker_asset_models
        self.breaker_properties = breaker_properties

        super(BreakerTypeAsset, self).__init__(**kw_args)
    # >>> breaker_type_asset


class RecloserTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic recloser asset that may be used for design purposes. 
    """
    # Type of recloser. 
    recloser_type = ''

    recloser_asset_models = []

    recloser_properties = None

    # <<< recloser_type_asset
    # @generated
    def __init__(self, recloser_type='', recloser_asset_models=[], recloser_properties=None, **kw_args):
        """ Initialises a new 'RecloserTypeAsset' instance.
        """
        self.recloser_type = recloser_type
        self.recloser_asset_models = recloser_asset_models
        self.recloser_properties = recloser_properties

        super(RecloserTypeAsset, self).__init__(**kw_args)
    # >>> recloser_type_asset


class BushingTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic bushing that may be used for various purposes such as work planning.  A bushing nsulates and protects from abrasion a conductor that passes through it.
    """
    # The type of bushing. 
    bushing_type = ''

    bushing_models = []

    # <<< bushing_type_asset
    # @generated
    def __init__(self, bushing_type='', bushing_models=[], **kw_args):
        """ Initialises a new 'BushingTypeAsset' instance.
        """
        self.bushing_type = bushing_type
        self.bushing_models = bushing_models

        super(BushingTypeAsset, self).__init__(**kw_args)
    # >>> bushing_type_asset


class CabinetTypeAsset(StructuralTypeAsset):
    """ Documentation for a generic cabinet that may be used for various purposes such as work planning.
    """
    # The type of cabinet. 
    type_cabinet = ''

    cabinet_models = []

    # <<< cabinet_type_asset
    # @generated
    def __init__(self, type_cabinet='', cabinet_models=[], **kw_args):
        """ Initialises a new 'CabinetTypeAsset' instance.
        """
        self.type_cabinet = type_cabinet
        self.cabinet_models = cabinet_models

        super(CabinetTypeAsset, self).__init__(**kw_args)
    # >>> cabinet_type_asset


class ComFuncTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic communication function that may be used for various purposes such as work planning. 
    """
    # The type of meter communication module. 
    type_mcm = ''

    com_func_asset_models = []

    # <<< com_func_type_asset
    # @generated
    def __init__(self, type_mcm='', com_func_asset_models=[], **kw_args):
        """ Initialises a new 'ComFuncTypeAsset' instance.
        """
        self.type_mcm = type_mcm
        self.com_func_asset_models = com_func_asset_models

        super(ComFuncTypeAsset, self).__init__(**kw_args)
    # >>> com_func_type_asset


class SeriesCompensatorTypeAsset(ElectricalTypeAsset):
    """ Documentation for a generic series compensator that may be used for design purposes.  It is typically associated with PoserSystemResource SeriesCompensator.
    """
    # Type of series compensator. 
    series_compensator_type = ''

    shunt_compensator_asset_models = []

    # <<< series_compensator_type_asset
    # @generated
    def __init__(self, series_compensator_type='', shunt_compensator_asset_models=[], **kw_args):
        """ Initialises a new 'SeriesCompensatorTypeAsset' instance.
        """
        self.series_compensator_type = series_compensator_type
        self.shunt_compensator_asset_models = shunt_compensator_asset_models

        super(SeriesCompensatorTypeAsset, self).__init__(**kw_args)
    # >>> series_compensator_type_asset


class CommEquipTypeAsset(TypeAsset):
    pass
    # <<< comm_equip_type_asset
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'CommEquipTypeAsset' instance.
        """

        super(CommEquipTypeAsset, self).__init__(**kw_args)
    # >>> comm_equip_type_asset


class EndDeviceTypeAsset(TypeAsset):
    """ Documentation for generic End Device that may be used for various purposes such as work planning.  
    """
    # The type of End Device. 
    type_end_device = ''

    end_device_models = []

    # <<< end_device_type_asset
    # @generated
    def __init__(self, type_end_device='', end_device_models=[], **kw_args):
        """ Initialises a new 'EndDeviceTypeAsset' instance.
        """
        self.type_end_device = type_end_device
        self.end_device_models = end_device_models

        super(EndDeviceTypeAsset, self).__init__(**kw_args)
    # >>> end_device_type_asset


class AssetFunctiontionTypeAsset(TypeAsset):
    """ Documentation for a generic Asset Function that may be used for various purposes such as work planning.  Asset Functions are typically component parts of Assets or Asset Containers.
    """
    # The type of Asset Function, such as a communication module, device controller, metering function, etc. 
    type_asset_function = ''

    asset_function_models = []

    # <<< asset_functiontion_type_asset
    # @generated
    def __init__(self, type_asset_function='', asset_function_models=[], **kw_args):
        """ Initialises a new 'AssetFunctiontionTypeAsset' instance.
        """
        self.type_asset_function = type_asset_function
        self.asset_function_models = asset_function_models

        super(AssetFunctiontionTypeAsset, self).__init__(**kw_args)
    # >>> asset_functiontion_type_asset


class SVCTypeAsset(FACTSDeviceTypeAsset):
    """ Documentation for a generic Static Var Compensator (SVC) that may be used for various purposes such as work planning.  SVC is classified as a FACTS type device.   
    """
    # The type of SVC. 
    svc_type = ''

    svcasset_models = []

    svcproperties = []

    # <<< svctype_asset
    # @generated
    def __init__(self, svc_type='', svcasset_models=[], svcproperties=[], **kw_args):
        """ Initialises a new 'SVCTypeAsset' instance.
        """
        self.svc_type = svc_type
        self.svcasset_models = svcasset_models
        self.svcproperties = svcproperties

        super(SVCTypeAsset, self).__init__(**kw_args)
    # >>> svctype_asset


# <<< typeasset
# @generated
# >>> typeasset

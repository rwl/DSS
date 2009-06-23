# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.assets import AssetContainer
from cim14.iec61968.informative.inf_common import Role
from cim14.iec61968.assets import Asset
from cim14.iec61968.assets import ElectricalInfo
from cim14.iec61970.core import Curve
from cim14.iec61968.common import ActivityRecord
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfAssets"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfAssets"

class ProcedureDataSet(Document):
    """ A data set recorded each time a procedure is executed. Observed results are captured in associated measurement values and/or values for properties relevant to the type of procedure performed.
    """
    measurement_values = []

    transformer_observations = []

    procedure = None

    # UserAttributes used to specify further properties of this procedure data set. Use 'name' to specify what kind of property it is, and 'value.value' attribute for the actual value.
    properties = []

    # <<< procedure_data_set
    # @generated
    def __init__(self, measurement_values=[], transformer_observations=[], procedure=None, properties=[], **kw_args):
        """ Initialises a new 'ProcedureDataSet' instance.
        """
        self.measurement_values = measurement_values
        self.transformer_observations = transformer_observations
        self.procedure = procedure
        self.properties = properties

        super(ProcedureDataSet, self).__init__(**kw_args)
    # >>> procedure_data_set


class PowerRating(IdentifiedObject):
    """ There are often stages of power which are associated with stages of cooling. For instance, a transformer may be rated 121kV on the primary, 15kV on the secondary and 4kV on the tertiary winding. These are voltage ratings and the power ratings are generally the same for all three windings and independent of the voltage ratings, there are instances where the tertiary may have a lower power rating. For example, for three stages, the power rating may be 15/20/25 MVA and the cooling is OA/FA/FOA. The 15 MVA rating goes with the OA cooling (Oil and Air cooling). This is called the self cooled rating as there are no external cooling enhancements. The 20 MVA rating goes with the FA cooling (Forced Air cooling), this means that when the fans are running and thus enhancing the cooling characteristics, the transformer can operate at a power level of 20 MVA. The 25 MVA rating goes with the FOA cooling (Forced Oil and Air cooling), this means that when the fans and pumps are running and thus enhancing the cooling characteristics even more than before, the transformer can operate at a power level of 25 MVA. This 15/20/25 MVA does not state how the power is split between the various windings. It may be 25 MVA input on the primary, 25 MVA output on the secondary and 0 MVA output on the tertiary. It may also operate at 25 MVA input on the primary, 17 MVA output on the secondary and 8 MVA output on the tertiary.
    """
    # Kind of cooling system. Values are: "self_cooling", "forced_oil_and_air", "forced_air", "other"
    cooling_kind = 'self_cooling'

    # Stage of cooling and associated power rating. 
    stage = 0

    # The power rating associated with type of cooling specified for this stage. 
    power_rating = ''

    transformer_assets = []

    # <<< power_rating
    # @generated
    def __init__(self, cooling_kind='self_cooling', stage=0, power_rating='', transformer_assets=[], **kw_args):
        """ Initialises a new 'PowerRating' instance.
        """
        self.cooling_kind = cooling_kind
        self.stage = stage
        self.power_rating = power_rating
        self.transformer_assets = transformer_assets

        super(PowerRating, self).__init__(**kw_args)
    # >>> power_rating


class Facility(AssetContainer):
    """ A facility may contain buildings, storage facilities, switching facilities, power generation, manufacturing facilities, maintenance facilities, etc.
    """
    # Kind of this facility. 
    kind = ''

    # <<< facility
    # @generated
    def __init__(self, kind='', **kw_args):
        """ Initialises a new 'Facility' instance.
        """
        self.kind = kind

        super(Facility, self).__init__(**kw_args)
    # >>> facility


class Specification(Document):
    """ Specification can be used for various purposes relative to an asset, a logical device (PowerSystemResource), location, etc. Examples include documents supplied by manufacturers such as asset installation instructions, asset maintenance instructions, etc.
    """
    # UserAttributes used to specify further properties of the asset covered with this specification. Use 'name' to specify what kind of property it is, and 'value.value' attribute for the actual value.
    asset_properites = []

    mediums = []

    qualification_requirements = []

    asset_property_curves = []

    dimensions_infos = []

    # UserAttributes used to specify ratings of the asset covered by this specification. Ratings also can be used to set the initial value of operational measurement limits. Use 'name' to specify what kind of rating it is (e.g., voltage, current), and 'value' attribute for the actual value and unit information of the rating.
    ratings = []

    reliability_infos = []

    # <<< specification
    # @generated
    def __init__(self, asset_properites=[], mediums=[], qualification_requirements=[], asset_property_curves=[], dimensions_infos=[], ratings=[], reliability_infos=[], **kw_args):
        """ Initialises a new 'Specification' instance.
        """
        self.asset_properites = asset_properites
        self.mediums = mediums
        self.qualification_requirements = qualification_requirements
        self.asset_property_curves = asset_property_curves
        self.dimensions_infos = dimensions_infos
        self.ratings = ratings
        self.reliability_infos = reliability_infos

        super(Specification, self).__init__(**kw_args)
    # >>> specification


class SubstationAsset(AssetContainer):
    """ A grouping of assets such as conductors, transformers, switchgear, etc. When located on the ground surface, it is usually surrounded by some kind of fence with a locked gate. It may also be located inside buildings, in underground vaults, and on structures. Use 'category' for utility-specific categorisation (such as Air Cooled, Gas Insultated, etc.).
    """
    # Function of this substation asset. Values are: "distribution", "transmission", "sub_transmission", "industrial", "other", "generation"
    function = 'distribution'

    substation = None

    # <<< substation_asset
    # @generated
    def __init__(self, function='distribution', substation=None, **kw_args):
        """ Initialises a new 'SubstationAsset' instance.
        """
        self.function = function
        self.substation = substation

        super(SubstationAsset, self).__init__(**kw_args)
    # >>> substation_asset


class DocAssetRole(Role):
    """ Roles played between Documents and Assets.
    """
    asset = None

    document = None

    # <<< doc_asset_role
    # @generated
    def __init__(self, asset=None, document=None, **kw_args):
        """ Initialises a new 'DocAssetRole' instance.
        """
        self.asset = asset
        self.document = document

        super(DocAssetRole, self).__init__(**kw_args)
    # >>> doc_asset_role


class Procedure(Document):
    """ A documented procedure for various types of Work or Work Tasks. One or more procedures guide a compatible unit, a standard way of performing a unit of work. The type of procedure is defined in Procedure.type. For example, when type=Inspection, this procedure coupled with Schedule and other information provides the key items of an inspection plan. Another type of Procedure is a Diagnosis. Note that each specific values and settings to be used in a procedure is intended to be described in an instance of ProcedureValue. A maintenance ticket, a type of Work, is generated whenever maintenance is determined to be needed as a result of an inspection or diagnosis.
    """
    # The textual description of the procedure, which references instances of ProcedureValue as appropriate. 
    instruction = ''

    # Kind of this procedure. Values are: "test", "diagnosis", "inspection", "other", "maintenance"
    kind = 'test'

    # Code for this kind of procedure. 
    corporate_code = ''

    # Sequence number in a sequence of procedures being performed. 
    sequence_number = ''

    compatible_units = []

    limits = []

    procedure_data_sets = []

    # UserAttributes used to specify procedure values. An example is to have an instance for each of the following settings when conducting a test: voltage, current, frequency, temperature specified in 'name' attribute, and the corresponding value and units in 'value' attribute.
    procedure_values = []

    # <<< procedure
    # @generated
    def __init__(self, instruction='', kind='test', corporate_code='', sequence_number='', compatible_units=[], limits=[], procedure_data_sets=[], procedure_values=[], **kw_args):
        """ Initialises a new 'Procedure' instance.
        """
        self.instruction = instruction
        self.kind = kind
        self.corporate_code = corporate_code
        self.sequence_number = sequence_number
        self.compatible_units = compatible_units
        self.limits = limits
        self.procedure_data_sets = procedure_data_sets
        self.procedure_values = procedure_values

        super(Procedure, self).__init__(**kw_args)
    # >>> procedure


class Cabinet(AssetContainer):
    """ Enclosure that offers protection to the equipment it contains and/or safety to people/animals outside it.
    """
    cabinet_model = None

    # <<< cabinet
    # @generated
    def __init__(self, cabinet_model=None, **kw_args):
        """ Initialises a new 'Cabinet' instance.
        """
        self.cabinet_model = cabinet_model

        super(Cabinet, self).__init__(**kw_args)
    # >>> cabinet


class DimensionsInfo(IdentifiedObject):
    """ As applicable, the basic linear, area, or volume dimensions of an asset, asset type (AssetModel) or other type of object (such as land area). Units and multipliers are specified per dimension.
    """
    # A description of the orientation of the object relative to the dimensions. As an example, a vault may have north-south orientation for the sizeLength measurement and sizeDepth may be the height of the vault. 
    orientation = ''

    # Length measurement. 
    size_length = ''

    # Depth measurement. 
    size_depth = ''

    # Width measurement. 
    size_width = ''

    # Diameter measurement. 
    size_diameter = ''

    assets = []

    locations = []

    specifications = []

    # <<< dimensions_info
    # @generated
    def __init__(self, orientation='', size_length='', size_depth='', size_width='', size_diameter='', assets=[], locations=[], specifications=[], **kw_args):
        """ Initialises a new 'DimensionsInfo' instance.
        """
        self.orientation = orientation
        self.size_length = size_length
        self.size_depth = size_depth
        self.size_width = size_width
        self.size_diameter = size_diameter
        self.assets = assets
        self.locations = locations
        self.specifications = specifications

        super(DimensionsInfo, self).__init__(**kw_args)
    # >>> dimensions_info


class TapChangerAsset(Asset):
    """ Physical asset performing TapChanger function.
    """
    tap_changer_model = None

    # <<< tap_changer_asset
    # @generated
    def __init__(self, tap_changer_model=None, **kw_args):
        """ Initialises a new 'TapChangerAsset' instance.
        """
        self.tap_changer_model = tap_changer_model

        super(TapChangerAsset, self).__init__(**kw_args)
    # >>> tap_changer_asset


class Structure(AssetContainer):
    """ Construction holding assets such as conductors, transformers, switchgear, etc.
    """
    # True if weeds are to be removed around asset. 
    remove_weed = False

    # Material this structure is made of. Values are: "other", "steel", "concrete", "wood"
    material_kind = 'other'

    # Date weed were last removed. 
    weed_removed_date = ''

    # Name of fumigant. 
    fumigant_name = ''

    # Date fumigant was last applied. 
    fumigant_applied_date = ''

    # Visible height of structure above ground level for overhead construction (e.g., Pole or Tower) or below ground level for an underground vault, manhole, etc. Refer to associated DimensionPropertiesInfo for other types of dimensions. 
    height = ''

    structure_supports = []

    # <<< structure
    # @generated
    def __init__(self, remove_weed=False, material_kind='other', weed_removed_date='', fumigant_name='', fumigant_applied_date='', height='', structure_supports=[], **kw_args):
        """ Initialises a new 'Structure' instance.
        """
        self.remove_weed = remove_weed
        self.material_kind = material_kind
        self.weed_removed_date = weed_removed_date
        self.fumigant_name = fumigant_name
        self.fumigant_applied_date = fumigant_applied_date
        self.height = height
        self.structure_supports = structure_supports

        super(Structure, self).__init__(**kw_args)
    # >>> structure


class StructureSupport(Asset):
    """ Support for Structures.
    """
    # Length of anchor lead or guy. 
    length = ''

    # Number of rods used for an anchor. 
    rod_count = 0

    # Length of rod used for an anchor. 
    rod_length = ''

    # Size of anchor or guy. 
    size = ''

    # Direction of supporting anchor or guy. 
    direction = ''

    secured_structure = None

    # <<< structure_support
    # @generated
    def __init__(self, length='', rod_count=0, rod_length='', size='', direction='', secured_structure=None, **kw_args):
        """ Initialises a new 'StructureSupport' instance.
        """
        self.length = length
        self.rod_count = rod_count
        self.rod_length = rod_length
        self.size = size
        self.direction = direction
        self.secured_structure = secured_structure

        super(StructureSupport, self).__init__(**kw_args)
    # >>> structure_support


class SwitchInfo(ElectricalInfo):
    """ Properties of a switch.
    """
    # True if device is capable of being operated by remote control. 
    remote = False

    # The lowest value of current that the switch can make, carry and break in uninterrupted duty at the rated voltage under specified operating conditions without suffering significant deterioration of its performance. 
    minimum_current = ''

    # The maximum rms voltage that may be applied across an open contact without breaking down the dielectric properties of the switch in the open position. 
    dielectric_strength = ''

    # Breaking capacity, or short circuit rating, is the maximum rated current which the device can safely interrupt at the rated voltage. 
    interrupting_rating = ''

    # True if switch has load breaking capabiity. Unless specified false, this is always assumed to be true for breakers and reclosers. 
    load_break = False

    # Number of poles (i.e. of current carrying conductors that are switched). 
    pole_count = 0

    # True if multi-phase switch controls all phases concurrently. 
    gang = False

    # The highest value of current the switch can carry in the closed position at the rated voltage under specified operating conditions without suffering significant deterioration of its performance. 
    withstand_current = ''

    # The highest value of current the switch can make at the rated voltage under specified operating conditions without suffering significant deterioration of its performance. 
    making_capacity = ''

    switch_type_asset = None

    switch_asset_model = None

    switch_assets = []

    # <<< switch_info
    # @generated
    def __init__(self, remote=False, minimum_current='', dielectric_strength='', interrupting_rating='', load_break=False, pole_count=0, gang=False, withstand_current='', making_capacity='', switch_type_asset=None, switch_asset_model=None, switch_assets=[], **kw_args):
        """ Initialises a new 'SwitchInfo' instance.
        """
        self.remote = remote
        self.minimum_current = minimum_current
        self.dielectric_strength = dielectric_strength
        self.interrupting_rating = interrupting_rating
        self.load_break = load_break
        self.pole_count = pole_count
        self.gang = gang
        self.withstand_current = withstand_current
        self.making_capacity = making_capacity
        self.switch_type_asset = switch_type_asset
        self.switch_asset_model = switch_asset_model
        self.switch_assets = switch_assets

        super(SwitchInfo, self).__init__(**kw_args)
    # >>> switch_info


class ElectricalAsset(Asset):
    """ An asset that has (or can have) a role in the electrical network.
    """
    # True if the asset is physically connected to electrical network (as opposed to being in a warehouse, being refurbished, etc.). Note that this attribute is not intended to imply energization status and/or whether the asset is actually being used. 
    is_connected = False

    # If 'isConnected' is true, then this is the as-built phase(s) that the asset is associatied with. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phase_code = 'abn'

    conducting_equipment = None

    electrical_infos = []

    # <<< electrical_asset
    # @generated
    def __init__(self, is_connected=False, phase_code='abn', conducting_equipment=None, electrical_infos=[], **kw_args):
        """ Initialises a new 'ElectricalAsset' instance.
        """
        self.is_connected = is_connected
        self.phase_code = phase_code
        self.conducting_equipment = conducting_equipment
        self.electrical_infos = electrical_infos

        super(ElectricalAsset, self).__init__(**kw_args)
    # >>> electrical_asset


class ShuntImpedanceInfo(ElectricalInfo):
    """ Properties of a shunt impedance.
    """
    # Lower control setting. 
    local_on_level = ''

    # True if the locally controlled capacitor has voltage override capability. 
    local_override = False

    # The size of the individual units that make up the bank. 
    cell_size = ''

    # Kind of local controller. Values are: "none", "voltage", "power_factor", "time", "reactive_power", "temperature", "current"
    local_control_kind = 'none'

    # For locally controlled shunt impedances which have a voltage override feature, the high voltage override value. If the voltage is above this value, the shunt impedance will be turned off regardless of the other local controller settings. 
    high_voltage_override = ''

    # For VAR, amp, or power factor locally controlled shunt impedances, the index of the regulation branch. 
    reg_branch = ''

    # Kind of control (if any). Values are: "remote_with_local_override", "local_only", "fixed", "remote_only"
    control_kind = 'remote_with_local_override'

    # True if open is normal status for a fixed capacitor bank, otherwise normal status is closed. 
    normal_open = False

    # Upper control setting. 
    local_off_level = ''

    # (For VAR, amp, or power factor locally controlled shunt impedances) Kind of regulation branch. Values are: "breaker", "recloser", "sectionner", "line", "transformer", "switch", "other", "fuse"
    reg_branch_kind = 'breaker'

    # For locally controlled shunt impedances which have a voltage override feature, the low voltage override value. If the voltage is below this value, the shunt impedance will be turned on regardless of the other local controller settings. 
    low_voltage_override = ''

    # IdmsShuntImpedanceData.maxNumSwitchOps 
    max_switch_operation_count = 0

    # For VAR, amp, or power factor locally controlled shunt impedances, the end of the branch that is regulated. The field has the following values: from side, to side, and tertiary (only if the branch is a transformer). 
    reg_branch_end = 0

    # Time interval between consecutive switching operations. 
    switch_operation_cycle = ''

    # For VAR, amp, or power factor locally controlled shunt impedances, the flow direction: in, out. 
    branch_direct = 0

    # Phases that are measured for controlling the device. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    sensing_phase_code = 'abn'

    # True if regulated voltages are measured line to line, otherwise they are measured line to ground. 
    v_reg_line_line = False

    shunt_compensator_type_asset = None

    shunt_compensator_asset_model = None

    shunt_compensator_assets = []

    # <<< shunt_impedance_info
    # @generated
    def __init__(self, local_on_level='', local_override=False, cell_size='', local_control_kind='none', high_voltage_override='', reg_branch='', control_kind='remote_with_local_override', normal_open=False, local_off_level='', reg_branch_kind='breaker', low_voltage_override='', max_switch_operation_count=0, reg_branch_end=0, switch_operation_cycle='', branch_direct=0, sensing_phase_code='abn', v_reg_line_line=False, shunt_compensator_type_asset=None, shunt_compensator_asset_model=None, shunt_compensator_assets=[], **kw_args):
        """ Initialises a new 'ShuntImpedanceInfo' instance.
        """
        self.local_on_level = local_on_level
        self.local_override = local_override
        self.cell_size = cell_size
        self.local_control_kind = local_control_kind
        self.high_voltage_override = high_voltage_override
        self.reg_branch = reg_branch
        self.control_kind = control_kind
        self.normal_open = normal_open
        self.local_off_level = local_off_level
        self.reg_branch_kind = reg_branch_kind
        self.low_voltage_override = low_voltage_override
        self.max_switch_operation_count = max_switch_operation_count
        self.reg_branch_end = reg_branch_end
        self.switch_operation_cycle = switch_operation_cycle
        self.branch_direct = branch_direct
        self.sensing_phase_code = sensing_phase_code
        self.v_reg_line_line = v_reg_line_line
        self.shunt_compensator_type_asset = shunt_compensator_type_asset
        self.shunt_compensator_asset_model = shunt_compensator_asset_model
        self.shunt_compensator_assets = shunt_compensator_assets

        super(ShuntImpedanceInfo, self).__init__(**kw_args)
    # >>> shunt_impedance_info


class WindingInsulation(IdentifiedObject):
    """ Winding insulation condition as a result of a test.
    """
    # As of statusDate, the leakage reactance measured at the 'from' winding with the 'to' winding short-circuited and all other windings open-circuited. 
    leakage_reactance = ''

    # For testType, status of Winding Insulation Resistance as of statusDate. Typical values are: Acceptable, Questionable, Failed. 
    insulation_resistance = ''

    # Status of Winding Insulation Power Factor as of statusDate: Acceptable, Minor Deterioration or Moisture Absorption, Major Deterioration or Moisture Absorption, Failed. 
    insulation_pfstatus = ''

    status = None

    from_transformer_winding = None

    ground = None

    transformer_observation = None

    to_transformer_winding = None

    # <<< winding_insulation
    # @generated
    def __init__(self, leakage_reactance='', insulation_resistance='', insulation_pfstatus='', status=None, from_transformer_winding=None, ground=None, transformer_observation=None, to_transformer_winding=None, **kw_args):
        """ Initialises a new 'WindingInsulation' instance.
        """
        self.leakage_reactance = leakage_reactance
        self.insulation_resistance = insulation_resistance
        self.insulation_pfstatus = insulation_pfstatus
        self.status = status
        self.from_transformer_winding = from_transformer_winding
        self.ground = ground
        self.transformer_observation = transformer_observation
        self.to_transformer_winding = to_transformer_winding

        super(WindingInsulation, self).__init__(**kw_args)
    # >>> winding_insulation


class PotentialTransformerInfo(ElectricalInfo):
    """ Used to define either the required additional electrical properties of a type of a Potential Transformer (PT), or a PT Model.
    """
    # Ratio for the secondary winding tap changer.
    secondary_ratio = None

    # Ratio for the primary winding tap changer.
    primary_ratio = None

    # Ratio for the tertiary winding tap changer.
    tertiary_ratio = None

    potential_transformer_assets = []

    potential_transformer_type_asset = None

    potential_transformer_asset_models = []

    # <<< potential_transformer_info
    # @generated
    def __init__(self, secondary_ratio=None, primary_ratio=None, tertiary_ratio=None, potential_transformer_assets=[], potential_transformer_type_asset=None, potential_transformer_asset_models=[], **kw_args):
        """ Initialises a new 'PotentialTransformerInfo' instance.
        """
        self.secondary_ratio = secondary_ratio
        self.primary_ratio = primary_ratio
        self.tertiary_ratio = tertiary_ratio
        self.potential_transformer_assets = potential_transformer_assets
        self.potential_transformer_type_asset = potential_transformer_type_asset
        self.potential_transformer_asset_models = potential_transformer_asset_models

        super(PotentialTransformerInfo, self).__init__(**kw_args)
    # >>> potential_transformer_info


class SVCInfo(ElectricalInfo):
    """ Properties for an SVC, allowing the capacitive and inductive ratings for each phase to be specified individually if required.
    """
    # Maximum inductive reactive power 
    inductive_rating = ''

    # Maximum capacitive reactive power 
    capacitive_rating = ''

    svcasset_model = None

    svcasset = None

    svctype_assets = []

    # <<< svcinfo
    # @generated
    def __init__(self, inductive_rating='', capacitive_rating='', svcasset_model=None, svcasset=None, svctype_assets=[], **kw_args):
        """ Initialises a new 'SVCInfo' instance.
        """
        self.inductive_rating = inductive_rating
        self.capacitive_rating = capacitive_rating
        self.svcasset_model = svcasset_model
        self.svcasset = svcasset
        self.svctype_assets = svctype_assets

        super(SVCInfo, self).__init__(**kw_args)
    # >>> svcinfo


class CompositeSwitchAsset(Asset):
    """ Physical asset that performs a given CompositeSwitch's role.
    """
    # Kind of composite switch. Values are: "other", "throw_over", "ug_multi_switch", "esco_throw_over", "gral", "ral", "regulator_bypass"
    kind = 'other'

    composite_switch_info = None

    composite_switch_asset_model = None

    # <<< composite_switch_asset
    # @generated
    def __init__(self, kind='other', composite_switch_info=None, composite_switch_asset_model=None, **kw_args):
        """ Initialises a new 'CompositeSwitchAsset' instance.
        """
        self.kind = kind
        self.composite_switch_info = composite_switch_info
        self.composite_switch_asset_model = composite_switch_asset_model

        super(CompositeSwitchAsset, self).__init__(**kw_args)
    # >>> composite_switch_asset


class Medium(IdentifiedObject):
    """ A substance that either (1) provides the means of transmission of a force or effect, such as hydraulic fluid, or (2) is used for a surrounding or enveloping substance, such as oil in a transformer or circuit breaker.
    """
    # The volume of the medium specified for this application. Note that the actual volume is a type of measurement associated witht the asset. 
    volume_spec = ''

    # Kind of this medium. Values are: "gas", "solid", "liquid"
    kind = 'gas'

    assets = []

    specification = None

    # <<< medium
    # @generated
    def __init__(self, volume_spec='', kind='gas', assets=[], specification=None, **kw_args):
        """ Initialises a new 'Medium' instance.
        """
        self.volume_spec = volume_spec
        self.kind = kind
        self.assets = assets
        self.specification = specification

        super(Medium, self).__init__(**kw_args)
    # >>> medium


class AssetAssetRole(Role):
    """ Roles played between Assets and other Assets.
    """
    from_asset = None

    to_asset = None

    # <<< asset_asset_role
    # @generated
    def __init__(self, from_asset=None, to_asset=None, **kw_args):
        """ Initialises a new 'AssetAssetRole' instance.
        """
        self.from_asset = from_asset
        self.to_asset = to_asset

        super(AssetAssetRole, self).__init__(**kw_args)
    # >>> asset_asset_role


class WireArrangement(IdentifiedObject):
    """ Identification, spacing and configuration of the wires of a ConductorType, with reference to their type.
    """
    # Mounting point where wire One is mounted. 
    mounting_point_y = 0

    # Mounting point where wire One is mounted. 
    mounting_point_x = 0

    # Wire type mounted at a specified place in this wire arrangement.
    wire_type = None

    # Conductor type owning this wire arangement.
    conductor_type = None

    # <<< wire_arrangement
    # @generated
    def __init__(self, mounting_point_y=0, mounting_point_x=0, wire_type=None, conductor_type=None, **kw_args):
        """ Initialises a new 'WireArrangement' instance.
        """
        self.mounting_point_y = mounting_point_y
        self.mounting_point_x = mounting_point_x
        self.wire_type = wire_type
        self.conductor_type = conductor_type

        super(WireArrangement, self).__init__(**kw_args)
    # >>> wire_arrangement


class ReliabilityInfo(IdentifiedObject):
    """ Information regarding the experienced and expected reliability of a specific asset, type of asset, or asset model.
    """
    # Momentary failure rate (temporary failures/kft-year). 
    mom_failure_rate = ''

    # Mean time to repair (MTTR - hours). 
    m_ttr = ''

    specification = None

    assets = []

    # <<< reliability_info
    # @generated
    def __init__(self, mom_failure_rate='', m_ttr='', specification=None, assets=[], **kw_args):
        """ Initialises a new 'ReliabilityInfo' instance.
        """
        self.mom_failure_rate = mom_failure_rate
        self.m_ttr = m_ttr
        self.specification = specification
        self.assets = assets

        super(ReliabilityInfo, self).__init__(**kw_args)
    # >>> reliability_info


class TransformerInfo(ElectricalInfo):
    """ Additional electrical properties of a type of transformer, of a transformer model, or the actual ones of a particular transformer asset.
    """
    # Secondary winding line drop compensation resistance. 
    line_drp_rs = ''

    # Secondary winding line drop compensation reactance. 
    line_drp_xs = ''

    # True if secondary winding tap changer has reverse regulation capability. 
    rev_reg_s = False

    # Impedance Secondary to Tertiary. 
    impedance_xy = ''

    # True if transformer is grounded. 
    grounded = False

    # Ground resistance path through connected grounding transformer. 
    r_ground = ''

    # Primary winding line drop compensation resistance. 
    line_drp_rp = ''

    # Magnetization power factor. 
    mag_pf = 0.0

    # Primary winding line drop compensation reactance. 
    line_drp_xp = ''

    # Apparent power that the winding can carry under emergency conditions. 
    emergency_apparent_power = ''

    # Impedance Primary to Tertiary. 
    impedance_hy = ''

    # Ground reactance path through connected grounding transformer. 
    x_ground = ''

    # True if primary winding tap changer has reverse regulation capability. 
    rev_reg_p = False

    # Tertiary winding line drop compensation resistance. 
    line_drp_rt = ''

    # Tertiary winding line drop compensation reactance. 
    line_drp_xt = ''

    # True if tertiary winding tap changer has reverse regulation capability. 
    rev_reg_t = False

    # Impedance Primary to Secondary. 
    impedance_hx = ''

    # Details on winding, allowing to specify winding code such as DYn11, DYn1 or DY11.
    winding_code = None

    transformer_type_assets = []

    transformer_assets = []

    transformer_asset_models = []

    # <<< transformer_info
    # @generated
    def __init__(self, line_drp_rs='', line_drp_xs='', rev_reg_s=False, impedance_xy='', grounded=False, r_ground='', line_drp_rp='', mag_pf=0.0, line_drp_xp='', emergency_apparent_power='', impedance_hy='', x_ground='', rev_reg_p=False, line_drp_rt='', line_drp_xt='', rev_reg_t=False, impedance_hx='', winding_code=None, transformer_type_assets=[], transformer_assets=[], transformer_asset_models=[], **kw_args):
        """ Initialises a new 'TransformerInfo' instance.
        """
        self.line_drp_rs = line_drp_rs
        self.line_drp_xs = line_drp_xs
        self.rev_reg_s = rev_reg_s
        self.impedance_xy = impedance_xy
        self.grounded = grounded
        self.r_ground = r_ground
        self.line_drp_rp = line_drp_rp
        self.mag_pf = mag_pf
        self.line_drp_xp = line_drp_xp
        self.emergency_apparent_power = emergency_apparent_power
        self.impedance_hy = impedance_hy
        self.x_ground = x_ground
        self.rev_reg_p = rev_reg_p
        self.line_drp_rt = line_drp_rt
        self.line_drp_xt = line_drp_xt
        self.rev_reg_t = rev_reg_t
        self.impedance_hx = impedance_hx
        self.winding_code = winding_code
        self.transformer_type_assets = transformer_type_assets
        self.transformer_assets = transformer_assets
        self.transformer_asset_models = transformer_asset_models

        super(TransformerInfo, self).__init__(**kw_args)
    # >>> transformer_info


class ComEquipmentAsset(AssetContainer):
    """ Communicaiton equipment, other than media, such as gateways, routers, controllers, etc.
    """
    # All device functions of this communication equipment asset.
    device_functions = []

    # <<< com_equipment_asset
    # @generated
    def __init__(self, device_functions=[], **kw_args):
        """ Initialises a new 'ComEquipmentAsset' instance.
        """
        self.device_functions = device_functions

        super(ComEquipmentAsset, self).__init__(**kw_args)
    # >>> com_equipment_asset


class ConductorType(IdentifiedObject):
    """ Wire or cable conductor (per IEEE specs). A specific type of wire or combination of wires not insulated from one another, suitable for carrying electric current. It may be bare or insulated.
    """
    # Reactance of the sheath for cable conductors. 
    sheath_reactance = ''

    # Resistance of the sheath for cable conductors. 
    sheath_resistance = ''

    # All wire arrangements for this conductor type.
    wire_arrangements = []

    linear_conductor_assets = []

    # All conductors this conductor type physically describes.
    conductors = []

    linear_conductro_type_asset = None

    # <<< conductor_type
    # @generated
    def __init__(self, sheath_reactance='', sheath_resistance='', wire_arrangements=[], linear_conductor_assets=[], conductors=[], linear_conductro_type_asset=None, **kw_args):
        """ Initialises a new 'ConductorType' instance.
        """
        self.sheath_reactance = sheath_reactance
        self.sheath_resistance = sheath_resistance
        self.wire_arrangements = wire_arrangements
        self.linear_conductor_assets = linear_conductor_assets
        self.conductors = conductors
        self.linear_conductro_type_asset = linear_conductro_type_asset

        super(ConductorType, self).__init__(**kw_args)
    # >>> conductor_type


class WireType(IdentifiedObject):
    """ Wire conductor (per IEEE specs). A specific type of wire or combination of wires, not insulated from each other, suitable for carrying electrical current.
    """
    # Geometric Mean Radius. If we replace the conductor by a thin walled tube of radius GMR, then its reactance is identical to the reactance of the actual conductor. 
    g_mr = ''

    # The resistance per unit length of the conductor 
    resistance = ''

    # Current carrying capacity of a wire or cable under stated thermal conditions 
    rated_current = ''

    # Distance between conductor strands in a (symmetrical) bundle. 
    phase_conductor_spacing = ''

    # The radius of the conductor 
    radius = ''

    # Number of conductor strands in the (symmetrical) bundle (1-12) 
    phase_conductor_count = 0

    # All wire arrangements this wire type is mounted in.
    wire_arrangements = []

    # <<< wire_type
    # @generated
    def __init__(self, g_mr='', resistance='', rated_current='', phase_conductor_spacing='', radius='', phase_conductor_count=0, wire_arrangements=[], **kw_args):
        """ Initialises a new 'WireType' instance.
        """
        self.g_mr = g_mr
        self.resistance = resistance
        self.rated_current = rated_current
        self.phase_conductor_spacing = phase_conductor_spacing
        self.radius = radius
        self.phase_conductor_count = phase_conductor_count
        self.wire_arrangements = wire_arrangements

        super(WireType, self).__init__(**kw_args)
    # >>> wire_type


class BushingInsulationPF(IdentifiedObject):
    """ Bushing insulation power factor condition as a result of a test. Typical status values are: Acceptable, Minor Deterioration or Moisture Absorption, Major Deterioration or Moisture Absorption, Failed.
    """
    # Kind of test for this bushing. Values are: "c1", "c2"
    test_kind = 'c1'

    status = None

    transformer_observation = None

    bushing_asset = None

    # <<< bushing_insulation_pf
    # @generated
    def __init__(self, test_kind='c1', status=None, transformer_observation=None, bushing_asset=None, **kw_args):
        """ Initialises a new 'BushingInsulationPF' instance.
        """
        self.test_kind = test_kind
        self.status = status
        self.transformer_observation = transformer_observation
        self.bushing_asset = bushing_asset

        super(BushingInsulationPF, self).__init__(**kw_args)
    # >>> bushing_insulation_pf


class CurrentTransformerInfo(ElectricalInfo):
    """ Used to define either the required additional electrical properties of a type of Current Transformer (CT) or a CT Model.
    """
    # Full load secondary (FLS) rating for tertiary winding. 
    tertiary_fls_rating = ''

    # Full load secondary (FLS) rating for secondary winding. 
    secondary_fls_rating = ''

    # Full load secondary (FLS) rating for primary winding. 
    primary_fls_rating = ''

    # Ratio for the secondary winding tap changer.
    secondary_ratio = None

    # Ratio for the tertiary winding tap changer.
    tertiary_ratio = None

    # Ratio for the primary winding tap changer.
    primary_ratio = None

    current_transformer_assert_models = []

    current_transformer_type_asset = None

    current_transformer_assets = []

    # <<< current_transformer_info
    # @generated
    def __init__(self, tertiary_fls_rating='', secondary_fls_rating='', primary_fls_rating='', secondary_ratio=None, tertiary_ratio=None, primary_ratio=None, current_transformer_assert_models=[], current_transformer_type_asset=None, current_transformer_assets=[], **kw_args):
        """ Initialises a new 'CurrentTransformerInfo' instance.
        """
        self.tertiary_fls_rating = tertiary_fls_rating
        self.secondary_fls_rating = secondary_fls_rating
        self.primary_fls_rating = primary_fls_rating
        self.secondary_ratio = secondary_ratio
        self.tertiary_ratio = tertiary_ratio
        self.primary_ratio = primary_ratio
        self.current_transformer_assert_models = current_transformer_assert_models
        self.current_transformer_type_asset = current_transformer_type_asset
        self.current_transformer_assets = current_transformer_assets

        super(CurrentTransformerInfo, self).__init__(**kw_args)
    # >>> current_transformer_info


class OrgAssetRole(Role):
    """ The roles played between an Organisations and an Asset.
    """
    # If the role type is 'owner,' this indicate the percentage of ownership. 
    percent_ownership = 0.0

    erp_organisation = None

    asset = None

    # <<< org_asset_role
    # @generated
    def __init__(self, percent_ownership=0.0, erp_organisation=None, asset=None, **kw_args):
        """ Initialises a new 'OrgAssetRole' instance.
        """
        self.percent_ownership = percent_ownership
        self.erp_organisation = erp_organisation
        self.asset = asset

        super(OrgAssetRole, self).__init__(**kw_args)
    # >>> org_asset_role


class Vehicle(Asset):
    """ A vehicle is a type of utility asset.
    """
    # Odometer reading of this vehicle as of the 'odometerReadingDateTime'. Refer to associated ActivityRecords for earlier readings. 
    odometer_reading = ''

    # Date and time the last odometer reading was recorded. 
    odometer_read_date_time = ''

    # The general categorization type of vehicle as categorized by the utility's asset management standards and practices. Note: (1) Vehicle model is defined by VehicleAssetModel, and (2) Specific people and organizations and their roles relative to this vehicle may be determined by the inherited Asset-ErpPerson and Asset-Organization associations. Values are: "crew", "other", "user", "contractor"
    usage_kind = 'crew'

    vehicle_asset_model = None

    crew = None

    # <<< vehicle
    # @generated
    def __init__(self, odometer_reading='', odometer_read_date_time='', usage_kind='crew', vehicle_asset_model=None, crew=None, **kw_args):
        """ Initialises a new 'Vehicle' instance.
        """
        self.odometer_reading = odometer_reading
        self.odometer_read_date_time = odometer_read_date_time
        self.usage_kind = usage_kind
        self.vehicle_asset_model = vehicle_asset_model
        self.crew = crew

        super(Vehicle, self).__init__(**kw_args)
    # >>> vehicle


class AssetPropertyCurve(Curve):
    """ An Asset Property that is described through curves rather than as a data point. The relationship is to be defined between an independent variable (X-axis) and one or two dependent variables (Y1-axis and Y2-axis).
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


class Tool(Asset):
    """ Utility asset typically used by utility resources like crews and persons. As is the case for other assets, tools must be maintained.
    """
    # Date the tool was last caibrated, if applicable. 
    last_calibration_date = ''

    crew = None

    tool_asset_model = None

    # <<< tool
    # @generated
    def __init__(self, last_calibration_date='', crew=None, tool_asset_model=None, **kw_args):
        """ Initialises a new 'Tool' instance.
        """
        self.last_calibration_date = last_calibration_date
        self.crew = crew
        self.tool_asset_model = tool_asset_model

        super(Tool, self).__init__(**kw_args)
    # >>> tool


class FailureEvent(ActivityRecord):
    """ An event where an asset has failed to perform its functions within specified parameters.
    """
    # The method used for locating the faulted part of the asset. For example, cable options include: Cap Discharge-Thumping, Bridge Method, Visual Inspection, Other. 
    fault_locating_method = ''

    # How the asset failure was isolated from the system. Values are: "manually_isolated", "other", "breaker_operation", "fuse", "burned_in_the_clear"
    failure_isolation_method = 'manually_isolated'

    # Code for asset failure. 
    corporate_code = ''

    # Failure location on an object. 
    location = ''

    # <<< failure_event
    # @generated
    def __init__(self, fault_locating_method='', failure_isolation_method='manually_isolated', corporate_code='', location='', **kw_args):
        """ Initialises a new 'FailureEvent' instance.
        """
        self.fault_locating_method = fault_locating_method
        self.failure_isolation_method = failure_isolation_method
        self.corporate_code = corporate_code
        self.location = location

        super(FailureEvent, self).__init__(**kw_args)
    # >>> failure_event


class TransformerObservation(IdentifiedObject):
    """ Common information captured during transformer inspections and/or diagrnotics. Note that some properties may be measured through other means and therefore have measurement values in addition to the observed values recorded here.
    """
    # Frequency Response Analysis. Typical values are: acceptable, slight movement, significant movement, failed, near failure. A graphic of the response diagram, which is a type of document, may be associated with this analysis through the recursive document relationship of the ProcedureDataSet. 
    freq_resp = ''

    # Top oil temperature. 
    top_oil_temp = ''

    # Bushing temperature. 
    bushing_temp = ''

    # Hotspot oil temperature. 
    hot_spot_temp = ''

    # Oil Quality Analysis-Dielectric Strength. 
    oil_dielectric_strength = ''

    # Dissolved Gas Analysis. Typical values are: Acceptable, Overheating, Corona, Sparking, Arcing. 
    dga = ''

    # Pump vibration, with typical values being: nominal, high. 
    pump_vibration = ''

    # Overall measure of furfural in oil and mechanical strength of paper. DP, the degree of polymerization, is the strength of the paper. Furfural is a measure of furfural compounds, often expressed in parts per million. 
    furfural_dp = ''

    # Water Content expressed in parts per million. 
    water_content = ''

    # Oil Quality Analysis-Neutralization Number - Number - Mg KOH. 
    oil_neutralization_number = ''

    # The level of oil in the transformer. 
    oil_level = ''

    # Oil Quality Analysis-Color. 
    oil_color = ''

    # Oil Quality Analysis- inter facial tension (IFT) - number-Dynes/CM. 
    oil_ift = ''

    status = None

    procedure_data_sets = []

    winding_insulation_pfs = []

    bushing_insultation_pfs = []

    transformer_asset = None

    winding_tests = []

    # <<< transformer_observation
    # @generated
    def __init__(self, freq_resp='', top_oil_temp='', bushing_temp='', hot_spot_temp='', oil_dielectric_strength='', dga='', pump_vibration='', furfural_dp='', water_content='', oil_neutralization_number='', oil_level='', oil_color='', oil_ift='', status=None, procedure_data_sets=[], winding_insulation_pfs=[], bushing_insultation_pfs=[], transformer_asset=None, winding_tests=[], **kw_args):
        """ Initialises a new 'TransformerObservation' instance.
        """
        self.freq_resp = freq_resp
        self.top_oil_temp = top_oil_temp
        self.bushing_temp = bushing_temp
        self.hot_spot_temp = hot_spot_temp
        self.oil_dielectric_strength = oil_dielectric_strength
        self.dga = dga
        self.pump_vibration = pump_vibration
        self.furfural_dp = furfural_dp
        self.water_content = water_content
        self.oil_neutralization_number = oil_neutralization_number
        self.oil_level = oil_level
        self.oil_color = oil_color
        self.oil_ift = oil_ift
        self.status = status
        self.procedure_data_sets = procedure_data_sets
        self.winding_insulation_pfs = winding_insulation_pfs
        self.bushing_insultation_pfs = bushing_insultation_pfs
        self.transformer_asset = transformer_asset
        self.winding_tests = winding_tests

        super(TransformerObservation, self).__init__(**kw_args)
    # >>> transformer_observation


class CompositeSwitchInfo(IdentifiedObject):
    """ Properties of a composite switch.
    """
    # Supported number of phases, typically 0, 1 or 3. 
    phase_count = 0

    # True if multi-phase switch controls all phases concurrently. 
    gang = False

    # True if device is capable of being operated by remote control. 
    remote = False

    # Rated voltage. 
    rated_voltage = ''

    # Breaking capacity, or short circuit rating, is the maximum rated current which the device can safely interrupt at the rated voltage. 
    interrupting_rating = ''

    # Phases carried, if applicable. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phase_code = 'abn'

    # Number of switch states represented by the composite switch. 
    switch_state_count = 0

    # Initial operating mode, with the following values: Automatic, Manual. 
    init_op_mode = ''

    composite_switch_type_asset = None

    composite_switch_asset_model = None

    composite_switch_assets = []

    # <<< composite_switch_info
    # @generated
    def __init__(self, phase_count=0, gang=False, remote=False, rated_voltage='', interrupting_rating='', phase_code='abn', switch_state_count=0, init_op_mode='', composite_switch_type_asset=None, composite_switch_asset_model=None, composite_switch_assets=[], **kw_args):
        """ Initialises a new 'CompositeSwitchInfo' instance.
        """
        self.phase_count = phase_count
        self.gang = gang
        self.remote = remote
        self.rated_voltage = rated_voltage
        self.interrupting_rating = interrupting_rating
        self.phase_code = phase_code
        self.switch_state_count = switch_state_count
        self.init_op_mode = init_op_mode
        self.composite_switch_type_asset = composite_switch_type_asset
        self.composite_switch_asset_model = composite_switch_asset_model
        self.composite_switch_assets = composite_switch_assets

        super(CompositeSwitchInfo, self).__init__(**kw_args)
    # >>> composite_switch_info


class AssetPsrRole(Role):
    """ Roles played between Assets and Power System Resources.
    """
    asset = None

    power_system_resource = None

    # <<< asset_psr_role
    # @generated
    def __init__(self, asset=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'AssetPsrRole' instance.
        """
        self.asset = asset
        self.power_system_resource = power_system_resource

        super(AssetPsrRole, self).__init__(**kw_args)
    # >>> asset_psr_role


class PSRStatus(IdentifiedObject):
    """ The current status of the PowerSystemResource. History of the status is tracked through instances of ActivityRecord.
    """
    status = None

    power_system_resource = None

    # <<< psrstatus
    # @generated
    def __init__(self, status=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'PSRStatus' instance.
        """
        self.status = status
        self.power_system_resource = power_system_resource

        super(PSRStatus, self).__init__(**kw_args)
    # >>> psrstatus


class WorkEquipmentAsset(Asset):
    """ Various equipment used to perform units of work by crews, office staff, etc.
    """
    crew = None

    work_equipment_asset_model = None

    usages = []

    # <<< work_equipment_asset
    # @generated
    def __init__(self, crew=None, work_equipment_asset_model=None, usages=[], **kw_args):
        """ Initialises a new 'WorkEquipmentAsset' instance.
        """
        self.crew = crew
        self.work_equipment_asset_model = work_equipment_asset_model
        self.usages = usages

        super(WorkEquipmentAsset, self).__init__(**kw_args)
    # >>> work_equipment_asset


class DuctBank(Asset):
    """ A duct bank may contain many ducts. Each duct contains individual lines that are expressed as linear assets (thereby describing each line's physical asset characteristics), which are each associated with ACLineSegments and other classes describing their electrical characteristics.
    """
    # Number of ducts in duct bank. 
    duct_count = 0

    # Number of circuits in duct bank. Refer to associations between a duct (LinearAsset) and an ACLineSegment to understand which circuits are in which ducts. 
    circuit_count = 0

    duct_band_type_asset = None

    cable_assets = []

    # <<< duct_bank
    # @generated
    def __init__(self, duct_count=0, circuit_count=0, duct_band_type_asset=None, cable_assets=[], **kw_args):
        """ Initialises a new 'DuctBank' instance.
        """
        self.duct_count = duct_count
        self.circuit_count = circuit_count
        self.duct_band_type_asset = duct_band_type_asset
        self.cable_assets = cable_assets

        super(DuctBank, self).__init__(**kw_args)
    # >>> duct_bank


class WindingInfo(Element):
    """ Details on winding. For example, to express winding code 'DYn11', set attributes as follows: 'windingConnectionKind' = Yn and 'phaseAngle' = 11.
    """
    # Kind of winding connection. Values are: "zn", "y", "d", "z", "yn"
    winding_connection_kind = 'zn'

    # Winding phase angle where 360 degrees are represented with clock hours, so the valid values are {0, ..., 11}. 
    phase_angle = 0

    # <<< winding_info
    # @generated
    def __init__(self, winding_connection_kind='zn', phase_angle=0, **kw_args):
        """ Initialises a new 'WindingInfo' instance.
        """
        self.winding_connection_kind = winding_connection_kind
        self.phase_angle = phase_angle

        super(WindingInfo, self).__init__(**kw_args)
    # >>> winding_info


class FinancialInfo(IdentifiedObject):
    """ Various current financial properties associated with a particular asset. Historical properties may be determined by ActivityRecords associated with the asset.
    """
    # The quantity of the asset if per unit length, for example conductor. 
    quantity = ''

    # Date asset's financial value was put in plant for regulatory accounting purposes (e.g., for rate base calculations). This is sometime referred to as the 'in-service date.' 
    plant_transfer_date = ''

    # Date warranty on asset expires. 
    warranty_date = ''

    # Purchase order identifier. 
    purchase_order_number = ''

    # Description of the cost. 
    cost_description = ''

    # The actual purchase cost of this particular asset. 
    actual_purchase_cost = ''

    # Value of asset as of 'valueDate'. 
    financial_value = ''

    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    # The account to which this actual material item is charged. 
    account = ''

    # The date and time at which the financial value was last established. 
    value_date = ''

    # Date asset was purchased. 
    purchase_date = ''

    asset = None

    # <<< financial_info
    # @generated
    def __init__(self, quantity='', plant_transfer_date='', warranty_date='', purchase_order_number='', cost_description='', actual_purchase_cost='', financial_value='', cost_type='', account='', value_date='', purchase_date='', asset=None, **kw_args):
        """ Initialises a new 'FinancialInfo' instance.
        """
        self.quantity = quantity
        self.plant_transfer_date = plant_transfer_date
        self.warranty_date = warranty_date
        self.purchase_order_number = purchase_order_number
        self.cost_description = cost_description
        self.actual_purchase_cost = actual_purchase_cost
        self.financial_value = financial_value
        self.cost_type = cost_type
        self.account = account
        self.value_date = value_date
        self.purchase_date = purchase_date
        self.asset = asset

        super(FinancialInfo, self).__init__(**kw_args)
    # >>> financial_info


class Anchor(StructureSupport):
    """ A type of support for structures, used to hold poles secure.
    """
    # Kind of this anchor. Values are: "unknown", "multi_helix", "other", "rod", "concrete", "helix", "screw"
    kind = 'unknown'

    # <<< anchor
    # @generated
    def __init__(self, kind='unknown', **kw_args):
        """ Initialises a new 'Anchor' instance.
        """
        self.kind = kind

        super(Anchor, self).__init__(**kw_args)
    # >>> anchor


class PotentialTransformerAsset(ElectricalAsset):
    """ Physical asset performing Potential Transformer (PT) function.
    """
    potential_transformer_info = None

    potential_transformer = None

    potential_transformer_asset_model = None

    # <<< potential_transformer_asset
    # @generated
    def __init__(self, potential_transformer_info=None, potential_transformer=None, potential_transformer_asset_model=None, **kw_args):
        """ Initialises a new 'PotentialTransformerAsset' instance.
        """
        self.potential_transformer_info = potential_transformer_info
        self.potential_transformer = potential_transformer
        self.potential_transformer_asset_model = potential_transformer_asset_model

        super(PotentialTransformerAsset, self).__init__(**kw_args)
    # >>> potential_transformer_asset


class RecloserAsset(ElectricalAsset):
    """ Physical recloser performing a reclosing function, which is modeled through Breaker.
    """
    recloser_asset_model = None

    recloser_info = None

    # <<< recloser_asset
    # @generated
    def __init__(self, recloser_asset_model=None, recloser_info=None, **kw_args):
        """ Initialises a new 'RecloserAsset' instance.
        """
        self.recloser_asset_model = recloser_asset_model
        self.recloser_info = recloser_info

        super(RecloserAsset, self).__init__(**kw_args)
    # >>> recloser_asset


class CurrentTransformerAsset(ElectricalAsset):
    """ Physical asset performing Current Transformer (CT) function.
    """
    # Type of CT as categorized by the utility's asset management standards and practices. 
    type_ct = ''

    current_transformer = None

    current_transformer_info = None

    current_transformer_asset_model = None

    # <<< current_transformer_asset
    # @generated
    def __init__(self, type_ct='', current_transformer=None, current_transformer_info=None, current_transformer_asset_model=None, **kw_args):
        """ Initialises a new 'CurrentTransformerAsset' instance.
        """
        self.type_ct = type_ct
        self.current_transformer = current_transformer
        self.current_transformer_info = current_transformer_info
        self.current_transformer_asset_model = current_transformer_asset_model

        super(CurrentTransformerAsset, self).__init__(**kw_args)
    # >>> current_transformer_asset


class Streetlight(ElectricalAsset):
    """ Streetlight asset.
    """
    # Length of arm of this specific asset. Note that a new light may be placed on an existing arm. 
    arm_length = ''

    # Lamp kind currently installed. Values are: "high_pressure_sodium", "other", "mercury_vapor", "metal_halide"
    lamp_kind = 'high_pressure_sodium'

    # Actual power rating of light. 
    light_rating = ''

    # Streetlight(s) may be attached to a pole.
    attached_to_pole = None

    streetlight_asset_model = None

    # <<< streetlight
    # @generated
    def __init__(self, arm_length='', lamp_kind='high_pressure_sodium', light_rating='', attached_to_pole=None, streetlight_asset_model=None, **kw_args):
        """ Initialises a new 'Streetlight' instance.
        """
        self.arm_length = arm_length
        self.lamp_kind = lamp_kind
        self.light_rating = light_rating
        self.attached_to_pole = attached_to_pole
        self.streetlight_asset_model = streetlight_asset_model

        super(Streetlight, self).__init__(**kw_args)
    # >>> streetlight


class LinearConductorAsset(ElectricalAsset):
    """ Physical asset used to perform the conductor's role.
    """
    # True when orientation is horizontal (e.g., transmission and distribution lines), false if vertical (e.g. a riser for underground to overhead service). 
    is_horizontal = False

    # True if linear asset has an insulator around the core material. 
    insulated = False

    # Description of the method used for grounding the linear conductor. For a cable, the grounding/bonding shield may be multi-point, single-point, cross cable, or other. 
    grounding_method = ''

    circuit_section = None

    conductor_type = None

    linear_conductor_type_asset = None

    conductors = []

    linear_conductor_asset_model = None

    # <<< linear_conductor_asset
    # @generated
    def __init__(self, is_horizontal=False, insulated=False, grounding_method='', circuit_section=None, conductor_type=None, linear_conductor_type_asset=None, conductors=[], linear_conductor_asset_model=None, **kw_args):
        """ Initialises a new 'LinearConductorAsset' instance.
        """
        self.is_horizontal = is_horizontal
        self.insulated = insulated
        self.grounding_method = grounding_method
        self.circuit_section = circuit_section
        self.conductor_type = conductor_type
        self.linear_conductor_type_asset = linear_conductor_type_asset
        self.conductors = conductors
        self.linear_conductor_asset_model = linear_conductor_asset_model

        super(LinearConductorAsset, self).__init__(**kw_args)
    # >>> linear_conductor_asset


class ProtectionEquipmentAsset(ElectricalAsset):
    """ Physical asset performing ProtectionEquipment function.
    """
    # Actual phase trip for this type of relay, if applicable. 
    phase_trip = ''

    # Actual ground trip for this type of relay, if applicable. 
    ground_trip = ''

    protection_equipment_asset_model = None

    # <<< protection_equipment_asset
    # @generated
    def __init__(self, phase_trip='', ground_trip='', protection_equipment_asset_model=None, **kw_args):
        """ Initialises a new 'ProtectionEquipmentAsset' instance.
        """
        self.phase_trip = phase_trip
        self.ground_trip = ground_trip
        self.protection_equipment_asset_model = protection_equipment_asset_model

        super(ProtectionEquipmentAsset, self).__init__(**kw_args)
    # >>> protection_equipment_asset


class Pole(Structure):
    """ A long, slender piece of wood, metal, etc. usually rounded that stands vertically from the ground and is used for mounting various types of overhead equipment. Dimensions of Pole are specified in associated DimensionsInfo.
    """
    # Kind of base for this pole. Values are: "dirt", "cement", "other", "asphalt", "unknown"
    base_kind = 'dirt'

    # Kind of treatment for this pole. Values are: "green_stain", "gray_stain", "other", "natural", "butt", "full", "unknown", "penta"
    treatment_kind = 'green_stain'

    # True if a block of material has been attached to base of pole in ground for stability. This technique is used primarily when anchors can not be used. 
    breast_block = False

    # Joint pole agreement reference number. 
    jpa_reference = ''

    # Date pole was last treated with preservative. 
    treated_date = ''

    # Kind of preservative for this pole. Values are: "penta", "other", "creosote", "chemonite", "cellon", "unknown", "naphthena"
    preservative_kind = 'penta'

    # The framing structure mounted on the pole. 
    construction = ''

    # Streetlight(s) may be attached to a pole.
    support_streetlights = []

    pole_model = None

    # <<< pole
    # @generated
    def __init__(self, base_kind='dirt', treatment_kind='green_stain', breast_block=False, jpa_reference='', treated_date='', preservative_kind='penta', construction='', support_streetlights=[], pole_model=None, **kw_args):
        """ Initialises a new 'Pole' instance.
        """
        self.base_kind = base_kind
        self.treatment_kind = treatment_kind
        self.breast_block = breast_block
        self.jpa_reference = jpa_reference
        self.treated_date = treated_date
        self.preservative_kind = preservative_kind
        self.construction = construction
        self.support_streetlights = support_streetlights
        self.pole_model = pole_model

        super(Pole, self).__init__(**kw_args)
    # >>> pole


class CableAsset(LinearConductorAsset):
    """ Insultated physical cable for performing the Conductor role used in undergrond and other applications..
    """
    duct_bank_type_asset = None

    duct_banks = []

    # <<< cable_asset
    # @generated
    def __init__(self, duct_bank_type_asset=None, duct_banks=[], **kw_args):
        """ Initialises a new 'CableAsset' instance.
        """
        self.duct_bank_type_asset = duct_bank_type_asset
        self.duct_banks = duct_banks

        super(CableAsset, self).__init__(**kw_args)
    # >>> cable_asset


class FACTSDeviceAsset(ElectricalAsset):
    """ Physical asset used to perform the FACTSDevice's role.
    """
    # Kind of FACTS device. Values are: "tsbr", "tssc", "svc", "statcom", "tcvl", "tcpar", "tcsc", "upfc"
    kind = 'tsbr'

    factsdevice_asset_model = None

    # <<< factsdevice_asset
    # @generated
    def __init__(self, kind='tsbr', factsdevice_asset_model=None, **kw_args):
        """ Initialises a new 'FACTSDeviceAsset' instance.
        """
        self.kind = kind
        self.factsdevice_asset_model = factsdevice_asset_model

        super(FACTSDeviceAsset, self).__init__(**kw_args)
    # >>> factsdevice_asset


class TestDataSet(ProcedureDataSet):
    """ Test results, usually obtained by a lab or other independent organisation.
    """
    # Conclusion drawn from test results. 
    conclusion = ''

    # The date the specimen was received by the lab. 
    spec_to_lab_date = ''

    # Identifier of specimen used in inspection or test. 
    specimen_id = ''

    # The date the conclusion form the test was issued by the lab. 
    conclusion_date = ''

    # <<< test_data_set
    # @generated
    def __init__(self, conclusion='', spec_to_lab_date='', specimen_id='', conclusion_date='', **kw_args):
        """ Initialises a new 'TestDataSet' instance.
        """
        self.conclusion = conclusion
        self.spec_to_lab_date = spec_to_lab_date
        self.specimen_id = specimen_id
        self.conclusion_date = conclusion_date

        super(TestDataSet, self).__init__(**kw_args)
    # >>> test_data_set


class FaultIndicatorAsset(ElectricalAsset):
    """ Physical asset performing FaultIndicator function.
    """
    fault_indicator_asset_model = None

    fault_indicator = None

    # <<< fault_indicator_asset
    # @generated
    def __init__(self, fault_indicator_asset_model=None, fault_indicator=None, **kw_args):
        """ Initialises a new 'FaultIndicatorAsset' instance.
        """
        self.fault_indicator_asset_model = fault_indicator_asset_model
        self.fault_indicator = fault_indicator

        super(FaultIndicatorAsset, self).__init__(**kw_args)
    # >>> fault_indicator_asset


class TransformerAsset(ElectricalAsset):
    """ A specific physical (vs. logical) transformer.
    """
    # 24-hour overload rating. 
    day_over_load_rating = ''

    # Nominal voltage rating for alternate configuration for secondary winding. 
    alt_secondary_nom_voltage = ''

    # Function of this transformer. Values are: "autotransformer", "other", "secondary_transformer", "voltage_regulator", "power_transformer"
    function = 'autotransformer'

    # True if windings can be re-configured to result in a different input or output voltage. 
    reconfig_winding = False

    # Nominal voltage rating for alternate configuration for primary winding. 
    alt_primary_nom_voltage = ''

    # Kind of construction for this transformer. Values are: "padmount_dead_front", "padmounted", "valut", "one_phase", "network", "underground", "subway", "overhead", "three_phase", "padmount_live_front", "padmount_loop_through", "padmount_feed_through", "dry_type", "aerial", "vault_three_phase", "unknown"
    construction_kind = 'padmount_dead_front'

    # 1-hour overload rating. 
    hour_over_load_rating = ''

    # Date and time this asset was last reconditioned or had a major overhaul. 
    reconditioned_date_time = ''

    transformer_observations = []

    transformer_asset_model = None

    power_ratings = []

    transformer_info = None

    # <<< transformer_asset
    # @generated
    def __init__(self, day_over_load_rating='', alt_secondary_nom_voltage='', function='autotransformer', reconfig_winding=False, alt_primary_nom_voltage='', construction_kind='padmount_dead_front', hour_over_load_rating='', reconditioned_date_time='', transformer_observations=[], transformer_asset_model=None, power_ratings=[], transformer_info=None, **kw_args):
        """ Initialises a new 'TransformerAsset' instance.
        """
        self.day_over_load_rating = day_over_load_rating
        self.alt_secondary_nom_voltage = alt_secondary_nom_voltage
        self.function = function
        self.reconfig_winding = reconfig_winding
        self.alt_primary_nom_voltage = alt_primary_nom_voltage
        self.construction_kind = construction_kind
        self.hour_over_load_rating = hour_over_load_rating
        self.reconditioned_date_time = reconditioned_date_time
        self.transformer_observations = transformer_observations
        self.transformer_asset_model = transformer_asset_model
        self.power_ratings = power_ratings
        self.transformer_info = transformer_info

        super(TransformerAsset, self).__init__(**kw_args)
    # >>> transformer_asset


class OverheadConductorAsset(LinearConductorAsset):
    """ Physical conductor performing the Conductor role that is used in overhead applications.
    """
    mounting_point = None

    # <<< overhead_conductor_asset
    # @generated
    def __init__(self, mounting_point=None, **kw_args):
        """ Initialises a new 'OverheadConductorAsset' instance.
        """
        self.mounting_point = mounting_point

        super(OverheadConductorAsset, self).__init__(**kw_args)
    # >>> overhead_conductor_asset


class BusbarAsset(ElectricalAsset):
    """ Physical asset used to perform the BusbarSection's role.
    """
    busbar_asset_model = None

    # <<< busbar_asset
    # @generated
    def __init__(self, busbar_asset_model=None, **kw_args):
        """ Initialises a new 'BusbarAsset' instance.
        """
        self.busbar_asset_model = busbar_asset_model

        super(BusbarAsset, self).__init__(**kw_args)
    # >>> busbar_asset


class SeriesCompensatorAsset(ElectricalAsset):
    """ For a a series capacitor or reactor, this is the physical asset performing the SeriesCompensator role (PSR).
    """
    series_compensator_asset_model = None

    # <<< series_compensator_asset
    # @generated
    def __init__(self, series_compensator_asset_model=None, **kw_args):
        """ Initialises a new 'SeriesCompensatorAsset' instance.
        """
        self.series_compensator_asset_model = series_compensator_asset_model

        super(SeriesCompensatorAsset, self).__init__(**kw_args)
    # >>> series_compensator_asset


class SurgeProtectorAsset(ElectricalAsset):
    """ Physical asset performing SurgeProtector function.
    """
    surge_protector = None

    surge_protector_asset_model = None

    # <<< surge_protector_asset
    # @generated
    def __init__(self, surge_protector=None, surge_protector_asset_model=None, **kw_args):
        """ Initialises a new 'SurgeProtectorAsset' instance.
        """
        self.surge_protector = surge_protector
        self.surge_protector_asset_model = surge_protector_asset_model

        super(SurgeProtectorAsset, self).__init__(**kw_args)
    # >>> surge_protector_asset


class UndergroundStructure(Structure):
    """ Abstract class for underground structures. Typical structure types are: BURD, Enclosure, Hand Hole, Manhole, Pad/Slab, Subsurface Enclosure, Trench, Tunnel, Vault, Pull/Splice Box.
    """
    # Date vault was sealed. 
    sealing_date = ''

    # Date sealing warranty expires. 
    sealing_warranty_expires = ''

    # Primary material of underground structure. 
    material = ''

    # True if vault is ventilating. 
    ventilation = False

    # <<< underground_structure
    # @generated
    def __init__(self, sealing_date='', sealing_warranty_expires='', material='', ventilation=False, **kw_args):
        """ Initialises a new 'UndergroundStructure' instance.
        """
        self.sealing_date = sealing_date
        self.sealing_warranty_expires = sealing_warranty_expires
        self.material = material
        self.ventilation = ventilation

        super(UndergroundStructure, self).__init__(**kw_args)
    # >>> underground_structure


class GeneratorAsset(ElectricalAsset):
    """ Physical asset performing the Generator role.
    """
    generator_asset_model = None

    # <<< generator_asset
    # @generated
    def __init__(self, generator_asset_model=None, **kw_args):
        """ Initialises a new 'GeneratorAsset' instance.
        """
        self.generator_asset_model = generator_asset_model

        super(GeneratorAsset, self).__init__(**kw_args)
    # >>> generator_asset


class BreakerInfo(SwitchInfo):
    """ Properties of breakers.
    """
    # Phase trip rating. 
    phase_trip = ''

    breaker_asset_models = []

    breaker_assets = []

    breaker_type_asset = None

    # <<< breaker_info
    # @generated
    def __init__(self, phase_trip='', breaker_asset_models=[], breaker_assets=[], breaker_type_asset=None, **kw_args):
        """ Initialises a new 'BreakerInfo' instance.
        """
        self.phase_trip = phase_trip
        self.breaker_asset_models = breaker_asset_models
        self.breaker_assets = breaker_assets
        self.breaker_type_asset = breaker_type_asset

        super(BreakerInfo, self).__init__(**kw_args)
    # >>> breaker_info


class RecloserInfo(SwitchInfo):
    """ Properties of reclosers.
    """
    # True if normal status of ground trip is enabled. 
    ground_trip_normal_enabled = False

    # Phase trip rating. 
    phase_trip_rating = ''

    # True if device has ground trip capability. 
    ground_trip_capable = False

    # Ground trip rating. 
    ground_trip_rating = ''

    # Total number of phase reclose operations. 
    reclose_lockout_count = 0

    recloser_assets = []

    recloser_asset_models = []

    recloser_type_asset = None

    # <<< recloser_info
    # @generated
    def __init__(self, ground_trip_normal_enabled=False, phase_trip_rating='', ground_trip_capable=False, ground_trip_rating='', reclose_lockout_count=0, recloser_assets=[], recloser_asset_models=[], recloser_type_asset=None, **kw_args):
        """ Initialises a new 'RecloserInfo' instance.
        """
        self.ground_trip_normal_enabled = ground_trip_normal_enabled
        self.phase_trip_rating = phase_trip_rating
        self.ground_trip_capable = ground_trip_capable
        self.ground_trip_rating = ground_trip_rating
        self.reclose_lockout_count = reclose_lockout_count
        self.recloser_assets = recloser_assets
        self.recloser_asset_models = recloser_asset_models
        self.recloser_type_asset = recloser_type_asset

        super(RecloserInfo, self).__init__(**kw_args)
    # >>> recloser_info


class Tower(Structure):
    """ Large structure used to carry transmission lines, subtransmission lines, and/or other equipment/lines (e.g., communication). Dimensions of the Tower are specified in associated DimensionsInfo class.
    """
    # Construction structure on the tower. Values are: "tension", "suspension"
    construction_kind = 'tension'

    tower_asset_model = None

    # <<< tower
    # @generated
    def __init__(self, construction_kind='tension', tower_asset_model=None, **kw_args):
        """ Initialises a new 'Tower' instance.
        """
        self.construction_kind = construction_kind
        self.tower_asset_model = tower_asset_model

        super(Tower, self).__init__(**kw_args)
    # >>> tower


class JointAsset(ElectricalAsset):
    """ Physical asset connecting two or more cable assets. It includes the portion of cable under wipes, welds, or other seals.
    """
    # Configuration of joint. Values are: "wires3to1", "wires1to1", "other", "wires2to1"
    configuration_kind = 'wires3to1'

    # Material used to fill the joint. Values are: "no_void", "no_fill_prefab", "other", "petrolatum", "insoluseal", "oil", "air_no_filling", "epoxy", "asphaltic", "bluefill254"
    fill_kind = 'no_void'

    # The type of insulation around the joint, classified according to the utility's asset management standards and practices. 
    insulation = ''

    # <<< joint_asset
    # @generated
    def __init__(self, configuration_kind='wires3to1', fill_kind='no_void', insulation='', **kw_args):
        """ Initialises a new 'JointAsset' instance.
        """
        self.configuration_kind = configuration_kind
        self.fill_kind = fill_kind
        self.insulation = insulation

        super(JointAsset, self).__init__(**kw_args)
    # >>> joint_asset


class SwitchAsset(ElectricalAsset):
    """ Physical asset performing Switch function.
    """
    switch_info = None

    switch_asset_model = None

    # <<< switch_asset
    # @generated
    def __init__(self, switch_info=None, switch_asset_model=None, **kw_args):
        """ Initialises a new 'SwitchAsset' instance.
        """
        self.switch_info = switch_info
        self.switch_asset_model = switch_asset_model

        super(SwitchAsset, self).__init__(**kw_args)
    # >>> switch_asset


class BushingAsset(ElectricalAsset):
    """ Physical bushing that insulates and protects from abrasion a conductor that passes through it. It is associated with a specific Terminal, which is in turn associated with a ConductingEquipment.
    """
    # Factory Measured Insulation Power Factor measured between the Power Factor tap and the bushing conductor. 
    c1_power_factor = 0.0

    # Factory Measured Capacitance measured between the Power Factor tap and the bushing conductor. 
    c1_capacitance = ''

    # Factory Measured Capacitance measured between the Power Factor tap and ground. 
    c2_capacitance = ''

    # Factory Measured Insulation Power Factor measured between the Power Factor tap and ground. 
    c2_power_factor = 0.0

    bushing_insulation_pfs = []

    terminal = None

    bushing_model = None

    # <<< bushing_asset
    # @generated
    def __init__(self, c1_power_factor=0.0, c1_capacitance='', c2_capacitance='', c2_power_factor=0.0, bushing_insulation_pfs=[], terminal=None, bushing_model=None, **kw_args):
        """ Initialises a new 'BushingAsset' instance.
        """
        self.c1_power_factor = c1_power_factor
        self.c1_capacitance = c1_capacitance
        self.c2_capacitance = c2_capacitance
        self.c2_power_factor = c2_power_factor
        self.bushing_insulation_pfs = bushing_insulation_pfs
        self.terminal = terminal
        self.bushing_model = bushing_model

        super(BushingAsset, self).__init__(**kw_args)
    # >>> bushing_asset


class BreakerAsset(ElectricalAsset):
    """ Physical asset performing Breaker role.
    """
    breaker_asset_model = None

    breaker_info = None

    # <<< breaker_asset
    # @generated
    def __init__(self, breaker_asset_model=None, breaker_info=None, **kw_args):
        """ Initialises a new 'BreakerAsset' instance.
        """
        self.breaker_asset_model = breaker_asset_model
        self.breaker_info = breaker_info

        super(BreakerAsset, self).__init__(**kw_args)
    # >>> breaker_asset


class ShuntCompensatorAsset(ElectricalAsset):
    """ For a shunt capacitor or reactor or switchable bank of shunt capacitors or reactors, this is the physical asset performing the ShuntCompensator role (PSR).
    """
    shunt_compensator_asset_model = None

    shunt_impedance_info = None

    # <<< shunt_compensator_asset
    # @generated
    def __init__(self, shunt_compensator_asset_model=None, shunt_impedance_info=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorAsset' instance.
        """
        self.shunt_compensator_asset_model = shunt_compensator_asset_model
        self.shunt_impedance_info = shunt_impedance_info

        super(ShuntCompensatorAsset, self).__init__(**kw_args)
    # >>> shunt_compensator_asset


class SVCAsset(FACTSDeviceAsset):
    """ Physical asset performing StaticVarCompensator function.
    """
    svc_info = None

    svcasset_model = None

    # <<< svcasset
    # @generated
    def __init__(self, svc_info=None, svcasset_model=None, **kw_args):
        """ Initialises a new 'SVCAsset' instance.
        """
        self.svc_info = svc_info
        self.svcasset_model = svcasset_model

        super(SVCAsset, self).__init__(**kw_args)
    # >>> svcasset


class ResistorAsset(ElectricalAsset):
    """ Physical asset performing Resistor function.
    """
    resistor = None

    resistor_asset_model = None

    # <<< resistor_asset
    # @generated
    def __init__(self, resistor=None, resistor_asset_model=None, **kw_args):
        """ Initialises a new 'ResistorAsset' instance.
        """
        self.resistor = resistor
        self.resistor_asset_model = resistor_asset_model

        super(ResistorAsset, self).__init__(**kw_args)
    # >>> resistor_asset


class Guy(StructureSupport):
    """ A type of support for structures.
    """
    pass
    # <<< guy
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Guy' instance.
        """

        super(Guy, self).__init__(**kw_args)
    # >>> guy


class Manhole(UndergroundStructure):
    """ Provides access at key locations to underground cables, equipment, etc. housed inside a protective vault.
    """
    pass
    # <<< manhole
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Manhole' instance.
        """

        super(Manhole, self).__init__(**kw_args)
    # >>> manhole


# <<< inf_assets
# @generated
# >>> inf_assets

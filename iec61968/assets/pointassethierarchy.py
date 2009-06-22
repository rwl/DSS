# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Point assets package.
"""

from iec61968.assets.assetbasics import Asset
from iec61968.assets.assetbasics import AssetModel
from  import 
from iec61968.assets.typeasset import ElectricalProperties
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from iec61968.assets.assetcontainers import AssetContainer

# <<< imports
# @generated
# >>> imports

class Tool(Asset):
    """ A tool is a type of utility asset typically used by utility resources like crews and persons (ErpPersons).  As is the case for other assets, tools must be maintained.
    """
    #  Date the tool was last caibrated, if applicable. 
    last_calibration_date = ''

    tool_asset_model = None

    crew = None

    # <<< tool
    # @generated
    def __init__(self, last_calibration_date='', tool_asset_model=None, crew=None, **kw_args):
        """ Initialises a new 'Tool' instance.
        """
        self.last_calibration_date = last_calibration_date
        self.tool_asset_model = tool_asset_model
        self.crew = crew

        super(Tool, self).__init__(**kw_args)
    # >>> tool


class Vehicle(Asset):
    """  A vehicle is a type of utility asset.
    """
    # The general categorization  type of vehicle: crew, user, contractor, other.  Note: (1) the vehicle model is defined by VehicleAssetModel and (2) Specific people and organizations and their roles relative to this vehicle may be determined by the inherited Asset-ErpPerson and Asset-Organization associations. 
    vehicle_type = ''

    # The odometer reading of this vehicle as of the 'odometerReadingDate'.  Refer to associated activity records for earlier readings. 
    odometer_reading = ''

    # The time and date the last odometer reading was recorded. 
    odometer_reading_date = ''

    vehicle_asset_model = None

    crew = None

    # <<< vehicle
    # @generated
    def __init__(self, vehicle_type='', odometer_reading='', odometer_reading_date='', vehicle_asset_model=None, crew=None, **kw_args):
        """ Initialises a new 'Vehicle' instance.
        """
        self.vehicle_type = vehicle_type
        self.odometer_reading = odometer_reading
        self.odometer_reading_date = odometer_reading_date
        self.vehicle_asset_model = vehicle_asset_model
        self.crew = crew

        super(Vehicle, self).__init__(**kw_args)
    # >>> vehicle


class ElectricalAssetModel(AssetModel):
    """ A device asset is the physical asset used to perform the conducting equipment's role.  It is associated with ConductingEquipment of the Wiresl Package
    """
    electrial_properties = []

    # <<< electrical_asset_model
    # @generated
    def __init__(self, electrial_properties=[], **kw_args):
        """ Initialises a new 'ElectricalAssetModel' instance.
        """
        self.electrial_properties = electrial_properties

        super(ElectricalAssetModel, self).__init__(**kw_args)
    # >>> electrical_asset_model


class PowerRating():
    """ There are often stages of power which are associated with stages of cooling.  For instance, a transformer may be rated 121kV on the primary, 15kV on the secondary and 4kV on the tertiary winding.  These are voltage ratings and the power ratings are generally the same for all three windings and independent of the voltage ratings, there are instances where the tertiary may have a lower power rating.   For example, for three stages, the power rating may be 15/20/25 MVA and the cooling is OA/FA/FOA.  The 15 MVA rating goes with the OA cooling (Oil and Air cooling). This is called the self cooled rating as there are no external cooling enhancements.  The 20 MVA rating goes with the FA cooling (Forced Air cooling), this means that when the fans are running and thus enhancing the cooling characteristics, the transformer can operate at a power level of 20 MVA.  The 25 MVA rating goes with the FOA cooling (Forced Oil and Air cooling), this means that when the fans and pumps are running and thus enhancing the cooling characteristics even more than before, the transformer can operate at a power level of 25 MVA.   This 15/20/25 MVA does not state how the power is split between the various windings.  It may be 25 MVA input on the primary, 25 MVA output on the secondary and 0 MVA output on the tertiary.  It may also operate at 25 MVA input on the primary, 17 MVA output on the secondary and 8 MVA output on the tertiary.  
    """
    # Type of cooling system associated with this transformer: self cooling, forced air, forced oil and air, other 
    cooling_type = ''

    # Stage of cooling and associated power rating. 
    stage = ''

    # The power rating associated with type of cooling specified for this stage. 
    power_rating = ''

    transformer_assets = []

    # <<< power_rating
    # @generated
    def __init__(self, cooling_type='', stage='', power_rating='', transformer_assets=[], **kw_args):
        """ Initialises a new 'PowerRating' instance.
        """
        self.cooling_type = cooling_type
        self.stage = stage
        self.power_rating = power_rating
        self.transformer_assets = transformer_assets

        super(PowerRating, self).__init__(**kw_args)
    # >>> power_rating


class ElectricalAsset(Asset):
    """ A asset that has (or can have) a role in the electrical network.
    """
    electrical_properties = []

    conducting_equipment = None

    # <<< electrical_asset
    # @generated
    def __init__(self, electrical_properties=[], conducting_equipment=None, **kw_args):
        """ Initialises a new 'ElectricalAsset' instance.
        """
        self.electrical_properties = electrical_properties
        self.conducting_equipment = conducting_equipment

        super(ElectricalAsset, self).__init__(**kw_args)
    # >>> electrical_asset


class CompositeSwitchAssetModel(AssetModel):
    """ Documentation for a type of a composite switch asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    composite_switch_type_asset = None

    composite_switch_assets = []

    composite_switch_properties = None

    # <<< composite_switch_asset_model
    # @generated
    def __init__(self, composite_switch_type_asset=None, composite_switch_assets=[], composite_switch_properties=None, **kw_args):
        """ Initialises a new 'CompositeSwitchAssetModel' instance.
        """
        self.composite_switch_type_asset = composite_switch_type_asset
        self.composite_switch_assets = composite_switch_assets
        self.composite_switch_properties = composite_switch_properties

        super(CompositeSwitchAssetModel, self).__init__(**kw_args)
    # >>> composite_switch_asset_model


class TapChangerAsset(Asset):
    """ A physical TapChanger performing the role for TapChanger (PSR).
    """
    # Type of tap changer. 
    tap_changer_type = ''

    tap_changer_model = None

    # <<< tap_changer_asset
    # @generated
    def __init__(self, tap_changer_type='', tap_changer_model=None, **kw_args):
        """ Initialises a new 'TapChangerAsset' instance.
        """
        self.tap_changer_type = tap_changer_type
        self.tap_changer_model = tap_changer_model

        super(TapChangerAsset, self).__init__(**kw_args)
    # >>> tap_changer_asset


class TransformerProperties(ElectricalProperties):
    """ Used to define additional electrical properties of a type of transformer, of a transformer model, or of the actual ones of a particular transformer asset. 
    """
    # Code indicating the windingConnectionType (Delta, wye, zigzag, etc) and the phaseShift (1, 11, etc). Typical values are DYn11, DYn1, DY11, etc) 
    winding_code = ''

    # The MVA that the winding can carry  under emergency conditions.  
    emergency_mva = ''

    # Ground resistance path through connected grounding transformer. 
    r_ground = ''

    # Ground reactance path through connected grounding transformer.  
    x_ground = ''

    # Impedance Primary to Secondary. 
    impedance_hx = ''

    # Impedance Primary to Tertiary 
    impedance_hy = ''

    # Impedance Secondary to Tertiary 
    impedance_xy = ''

    # Primary winding line drop compensation resistance (ohms).  
    line_drp_rp = ''

    # Primary winding line drop compensation reactance (ohms).  
    line_drp_xp = ''

    # Secondary winding line drop compensation resistance (ohms).  
    line_drp_rs = ''

    # Secondary winding line drop compensation reactance (ohms).  
    line_drp_xs = ''

    # Tertiary winding line drop compensation resistance (ohms).  
    line_drp_rt = ''

    # Tertiary winding line drop compensation reactance (ohms).  
    line_drp_xt = ''

    # True if primary winding  tap changer has reverse regulation capability.  
    rev_reg_p = ''

    # True if secondary winding  tap changer has reverse regulation capability.  
    rev_reg_s = ''

    # True if tertiary winding  tap changer has reverse regulation capability.  
    rev_reg_t = ''

    # Magnetization power factor  
    mag_pf = ''

    # True if transformer is grounded. 
    grounded = ''

    transformer_assets = []

    transformer_models = []

    transformer_type_assets = []

    # <<< transformer_properties
    # @generated
    def __init__(self, winding_code='', emergency_mva='', r_ground='', x_ground='', impedance_hx='', impedance_hy='', impedance_xy='', line_drp_rp='', line_drp_xp='', line_drp_rs='', line_drp_xs='', line_drp_rt='', line_drp_xt='', rev_reg_p='', rev_reg_s='', rev_reg_t='', mag_pf='', grounded='', transformer_assets=[], transformer_models=[], transformer_type_assets=[], **kw_args):
        """ Initialises a new 'TransformerProperties' instance.
        """
        self.winding_code = winding_code
        self.emergency_mva = emergency_mva
        self.r_ground = r_ground
        self.x_ground = x_ground
        self.impedance_hx = impedance_hx
        self.impedance_hy = impedance_hy
        self.impedance_xy = impedance_xy
        self.line_drp_rp = line_drp_rp
        self.line_drp_xp = line_drp_xp
        self.line_drp_rs = line_drp_rs
        self.line_drp_xs = line_drp_xs
        self.line_drp_rt = line_drp_rt
        self.line_drp_xt = line_drp_xt
        self.rev_reg_p = rev_reg_p
        self.rev_reg_s = rev_reg_s
        self.rev_reg_t = rev_reg_t
        self.mag_pf = mag_pf
        self.grounded = grounded
        self.transformer_assets = transformer_assets
        self.transformer_models = transformer_models
        self.transformer_type_assets = transformer_type_assets

        super(TransformerProperties, self).__init__(**kw_args)
    # >>> transformer_properties


class VehicleAssetModel(AssetModel):
    """ Documentation for a type of a vehicle of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
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


class EquipmentAsset(Asset):
    """ Various types of equipment used to perform units of work by crews, office staff, etc.
    """
    # The type of equipment. 
    type_equipment = ''

    equipment_asset_model = None

    equipment_lists = []

    usages = []

    crew = None

    # <<< equipment_asset
    # @generated
    def __init__(self, type_equipment='', equipment_asset_model=None, equipment_lists=[], usages=[], crew=None, **kw_args):
        """ Initialises a new 'EquipmentAsset' instance.
        """
        self.type_equipment = type_equipment
        self.equipment_asset_model = equipment_asset_model
        self.equipment_lists = equipment_lists
        self.usages = usages
        self.crew = crew

        super(EquipmentAsset, self).__init__(**kw_args)
    # >>> equipment_asset


class EquipmentAssetModel(AssetModel):
    """ Documentation for a type of an equipment of a particular product model made by a manufacturer (Organisation).  Types of equipment, among other purposes, are used to perform units of work by crews, office staff, etc.
    """
    equipment_type_asset = None

    equipment_assets = []

    # <<< equipment_asset_model
    # @generated
    def __init__(self, equipment_type_asset=None, equipment_assets=[], **kw_args):
        """ Initialises a new 'EquipmentAssetModel' instance.
        """
        self.equipment_type_asset = equipment_type_asset
        self.equipment_assets = equipment_assets

        super(EquipmentAssetModel, self).__init__(**kw_args)
    # >>> equipment_asset_model


class ToolAssetModel(AssetModel):
    """ Documentation for a type of a tool of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
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


class SurgeProtector():
    """ Shunt devices installed on the network, usually in the proximity of electrical equipment in order to protect the said equipment against transient voltage spikes caused by lightning- or switching activity.
    """
    surge_protector_asset = None

    surge_protector_type_asset = None

    # <<< surge_protector
    # @generated
    def __init__(self, surge_protector_asset=None, surge_protector_type_asset=None, **kw_args):
        """ Initialises a new 'SurgeProtector' instance.
        """
        self.surge_protector_asset = surge_protector_asset
        self.surge_protector_type_asset = surge_protector_type_asset

        super(SurgeProtector, self).__init__(**kw_args)
    # >>> surge_protector


class CT():
    """ A Current Transformer (CT) is an instrument transformer that is used to measure electrical qualities of the circuit that is being protected and/or monitored.  A typical secondary current rating of a CT would be 5A.  Typically used as current transducer for the purpose of metering- or protection.
    """
    # CT classification; i.e. class 10P 
    class_ct = ''

    # CT accuracy classification. 
    accuracy_class = ''

    # The limit (as % of rated current) for which the CT remains accurate within specified limits. 
    accuracy_limit = ''

    # For multi-ratio CT's, the maximum permissable ratio attainable 
    ct_max_ratio = ''

    # CT's may have multiple cores,  
    number_of_cores = ''

    # Intended usage of the CT; i.e. metering, protection 
    usage = ''

    cttype_asset = None

    ctasset = None

    # <<< ct
    # @generated
    def __init__(self, class_ct='', accuracy_class='', accuracy_limit='', ct_max_ratio='', number_of_cores='', usage='', cttype_asset=None, ctasset=None, **kw_args):
        """ Initialises a new 'CT' instance.
        """
        self.class_ct = class_ct
        self.accuracy_class = accuracy_class
        self.accuracy_limit = accuracy_limit
        self.ct_max_ratio = ct_max_ratio
        self.number_of_cores = number_of_cores
        self.usage = usage
        self.cttype_asset = cttype_asset
        self.ctasset = ctasset

        super(CT, self).__init__(**kw_args)
    # >>> ct


class FaultIndicator():
    """ A Faultindicator is typically only an indicator (which may or may not be remotely monitored), and not a piece of equipment that actually initiates a protection event. It is used for FLISR (Fault Location, Isolation and Restoration) purposes, assisting with the dispatch of crews to “most likely” part of the network (i.e. assists with determining circuit section where the fault most likely happened).  
    """
    fault_indicator_type_asset = None

    fault_indicator_assets = []

    # <<< fault_indicator
    # @generated
    def __init__(self, fault_indicator_type_asset=None, fault_indicator_assets=[], **kw_args):
        """ Initialises a new 'FaultIndicator' instance.
        """
        self.fault_indicator_type_asset = fault_indicator_type_asset
        self.fault_indicator_assets = fault_indicator_assets

        super(FaultIndicator, self).__init__(**kw_args)
    # >>> fault_indicator


class PT():
    """ A Potential Transformer (PT) is an instrument transformer that is used to measure electrical qualities of the circuit that is being protected and/or monitored.  A typical secondary voltage rating of a PT would be 120V.  A PT is typically used as voltage transducer for the purpose of metering-, protection-, or sometimes auxiliary substation supply.
    """
    # PT classification. 
    class_pt = ''

    # PT accuracy classification 
    accuracy_class = ''

    # Nominal ratio between the primary and secondary voltage. 
    pt_ratio = ''

    # The type of PT. 
    pt_type = ''

    # The type of construction for the PT 
    construction_type = ''

    pttype_asset = None

    ptasset = None

    # <<< pt
    # @generated
    def __init__(self, class_pt='', accuracy_class='', pt_ratio='', pt_type='', construction_type='', pttype_asset=None, ptasset=None, **kw_args):
        """ Initialises a new 'PT' instance.
        """
        self.class_pt = class_pt
        self.accuracy_class = accuracy_class
        self.pt_ratio = pt_ratio
        self.pt_type = pt_type
        self.construction_type = construction_type
        self.pttype_asset = pttype_asset
        self.ptasset = ptasset

        super(PT, self).__init__(**kw_args)
    # >>> pt


class Resistor():
    """ Resistor, typically used in filter configurations or as earthing resistor for transformers. 
    """
    resistor_type_asset = None

    resistor_asset = None

    # <<< resistor
    # @generated
    def __init__(self, resistor_type_asset=None, resistor_asset=None, **kw_args):
        """ Initialises a new 'Resistor' instance.
        """
        self.resistor_type_asset = resistor_type_asset
        self.resistor_asset = resistor_asset

        super(Resistor, self).__init__(**kw_args)
    # >>> resistor


class CTProperties(ElectricalProperties):
    """ Used to define either the required additional electrical properties of a type of Current Transformer (CT) or a CT Model.  A Current Transformer (CT) is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    # The CT ratio for the primary winding tap changer.  
    c_tratio_p = ''

    # The CT ratio for the secondary winding tap changer.  
    c_tratio_s = ''

    # The CT ratio for the tertiary winding tap changer.  
    c_tratio_t = ''

    # Primary winding CT full load secondary rating in Amps.  
    c_tfull_load_sec_rating_p = ''

    # Secondary winding CT full load secondary rating in Amps.  
    c_tfull_load_sec_rating_s = ''

    # Tertiary winding CT full load secondary rating in Amps.  
    c_tfull_load_sec_rating_t = ''

    cttype_asset = None

    ctassert_models = []

    ctassets = []

    # <<< ctproperties
    # @generated
    def __init__(self, c_tratio_p='', c_tratio_s='', c_tratio_t='', c_tfull_load_sec_rating_p='', c_tfull_load_sec_rating_s='', c_tfull_load_sec_rating_t='', cttype_asset=None, ctassert_models=[], ctassets=[], **kw_args):
        """ Initialises a new 'CTProperties' instance.
        """
        self.c_tratio_p = c_tratio_p
        self.c_tratio_s = c_tratio_s
        self.c_tratio_t = c_tratio_t
        self.c_tfull_load_sec_rating_p = c_tfull_load_sec_rating_p
        self.c_tfull_load_sec_rating_s = c_tfull_load_sec_rating_s
        self.c_tfull_load_sec_rating_t = c_tfull_load_sec_rating_t
        self.cttype_asset = cttype_asset
        self.ctassert_models = ctassert_models
        self.ctassets = ctassets

        super(CTProperties, self).__init__(**kw_args)
    # >>> ctproperties


class PTProperties(ElectricalProperties):
    """ Used to define either the required additional electrical properties of a type of a Potential Transformer (PT), or a PT Model.
    """
    # The PT ratio for the primary winding tap changer.  
    p_tratio_p = ''

    # The PT ratio for the secondary winding tap changer.  
    p_tratio_s = ''

    # The PT ratio for the tertiary winding tap changer.  
    p_tratio_t = ''

    ptasset_models = []

    pttype_asset = None

    ptassets = []

    # <<< ptproperties
    # @generated
    def __init__(self, p_tratio_p='', p_tratio_s='', p_tratio_t='', ptasset_models=[], pttype_asset=None, ptassets=[], **kw_args):
        """ Initialises a new 'PTProperties' instance.
        """
        self.p_tratio_p = p_tratio_p
        self.p_tratio_s = p_tratio_s
        self.p_tratio_t = p_tratio_t
        self.ptasset_models = ptasset_models
        self.pttype_asset = pttype_asset
        self.ptassets = ptassets

        super(PTProperties, self).__init__(**kw_args)
    # >>> ptproperties


class ShuntImpedanceProperties(ElectricalProperties):
    """ Properties of shunt impedances.
    """
    # Enumeration indicating the type of control (if any). The field has the following values: Fixed, Local only, Remote only, Remote with local override. 
    control_type = ''

    # Lower control setting. 
    local_on_level = ''

    # Upper control setting. 
    local_off_level = ''

    # Associated local controller type, none power factor, time dependent, temperature dependent, VAR dependent, current dependent; voltage dependent.  
    local_control_type = ''

    # IdmsShuntImpedanceData.maxNumSwitchOps 
    max_num_switch_ops = ''

    # Time interval between consecutive switching operations (hours).  
    switch_ops_time_interval = ''

    # Normal status for a fixed capacitor bank (true = open, false = closed).  
    normal_open = ''

    # The phases that are measured for controlling the device.  
    sensing_phases = ''

    # Flag indicating that regulated voltages are measured line to line or line to ground (true = line to line).  
    v_reg_line_line = ''

    # For VAR, amp, or power factor locally controlled shunt impedances,  the index of the regulation branch.  
    reg_branch = ''

    # For VAR, amp, or power factor locally controlled shunt impedances,  the type of regulation branch (LINE, TF, SWITCH, BREAKER, RECLOSER, FUSE, SECT, or other two node device)  
    reg_branch_type = ''

    # For VAR, amp, or power factor locally controlled shunt impedances,  the flow direction: in, out.    
    branch_direct = ''

    # For VAR, amp, or power factor locally controlled shunt impedances,  the end of the branch that is regulated. The field has the following values, from side,  to side, and tertiary (only if the branch is a transformer).    
    reg_branch_end = ''

    # Flag indicating the locally controlled capacitor has voltage override capability (true = has override capability).  
    local_override = ''

    # For locally controlled shunt impedances which have a voltage override feature, the low voltage override value (in per unit). If the voltage is below this value, the shunt impedance will be turned on regardless of the other local controller settings.  
    low_level_over = ''

    # For locally controlled shunt impedances which have a voltage override feature, the high voltage override value (in per unit). If the voltage is above this value, the shunt impedance will be turned off regardless of the other local controller settings.  
    high_level_over = ''

    # The size of the individual units that make up the bank (kVAR). 
    cell_size = ''

    shunt_compensator_type_asset = None

    shunt_compensator_asset_model = None

    shunt_compensator_assets = []

    # <<< shunt_impedance_properties
    # @generated
    def __init__(self, control_type='', local_on_level='', local_off_level='', local_control_type='', max_num_switch_ops='', switch_ops_time_interval='', normal_open='', sensing_phases='', v_reg_line_line='', reg_branch='', reg_branch_type='', branch_direct='', reg_branch_end='', local_override='', low_level_over='', high_level_over='', cell_size='', shunt_compensator_type_asset=None, shunt_compensator_asset_model=None, shunt_compensator_assets=[], **kw_args):
        """ Initialises a new 'ShuntImpedanceProperties' instance.
        """
        self.control_type = control_type
        self.local_on_level = local_on_level
        self.local_off_level = local_off_level
        self.local_control_type = local_control_type
        self.max_num_switch_ops = max_num_switch_ops
        self.switch_ops_time_interval = switch_ops_time_interval
        self.normal_open = normal_open
        self.sensing_phases = sensing_phases
        self.v_reg_line_line = v_reg_line_line
        self.reg_branch = reg_branch
        self.reg_branch_type = reg_branch_type
        self.branch_direct = branch_direct
        self.reg_branch_end = reg_branch_end
        self.local_override = local_override
        self.low_level_over = low_level_over
        self.high_level_over = high_level_over
        self.cell_size = cell_size
        self.shunt_compensator_type_asset = shunt_compensator_type_asset
        self.shunt_compensator_asset_model = shunt_compensator_asset_model
        self.shunt_compensator_assets = shunt_compensator_assets

        super(ShuntImpedanceProperties, self).__init__(**kw_args)
    # >>> shunt_impedance_properties


class CompositeSwitchAsset(Asset):
    """ The physical asset that performs a given CompositeSwitch's role.
    """
    # Type of composite switche.g. Throwover, ESCO Throwover, RAL, GRAL, Regulator Bypass, UG Multi Switch 
    composite_switch_type = ''

    composite_switch_asset_model = None

    composite_switch_properties = None

    # <<< composite_switch_asset
    # @generated
    def __init__(self, composite_switch_type='', composite_switch_asset_model=None, composite_switch_properties=None, **kw_args):
        """ Initialises a new 'CompositeSwitchAsset' instance.
        """
        self.composite_switch_type = composite_switch_type
        self.composite_switch_asset_model = composite_switch_asset_model
        self.composite_switch_properties = composite_switch_properties

        super(CompositeSwitchAsset, self).__init__(**kw_args)
    # >>> composite_switch_asset


class CompositeSwitchPorperties():
    """ Properties of a composite switch.
    """
    # Initial operating mode, with the following values: Automatic, Manual. 
    init_op_mode = ''

    # Number of switch states represented by the composite switch 
    num_switch_states = ''

    # Breaking capacity, or short circuit rating, is the maximum rated current which the device can safely interrupt at the rated voltage. 
    interrupting_rating = ''

    #  True if device is capable of being operated by remote control. 
    remote = False

    #  True if multi-phase switch controls all phases concurrently. 
    gang = False

    # Rated voltage 
    rated_v = ''

    # Describes the phases carried, if applicable. Possible values { ABCN , ABC, ABN, ACN, BCN, AB, AC, BC, AN, BN, CN, A, B, C, N }. 
    phases = ''

    # The number of phases this type of asset supports, typically 0, 1 or 3. 
    number_phases = ''

    composite_switch_asset_model = None

    composite_switch_type_asset = None

    composite_switch_assets = []

    # <<< composite_switch_porperties
    # @generated
    def __init__(self, init_op_mode='', num_switch_states='', interrupting_rating='', remote=False, gang=False, rated_v='', phases='', number_phases='', composite_switch_asset_model=None, composite_switch_type_asset=None, composite_switch_assets=[], **kw_args):
        """ Initialises a new 'CompositeSwitchPorperties' instance.
        """
        self.init_op_mode = init_op_mode
        self.num_switch_states = num_switch_states
        self.interrupting_rating = interrupting_rating
        self.remote = remote
        self.gang = gang
        self.rated_v = rated_v
        self.phases = phases
        self.number_phases = number_phases
        self.composite_switch_asset_model = composite_switch_asset_model
        self.composite_switch_type_asset = composite_switch_type_asset
        self.composite_switch_assets = composite_switch_assets

        super(CompositeSwitchPorperties, self).__init__(**kw_args)
    # >>> composite_switch_porperties


class SwitchProperties(ElectricalProperties):
    """ Properties of a switch.
    """
    #  True if multi-phase switch controls all phases concurrently. 
    gang = ''

    #  True if device is capable of being operated by remote control. 
    remote = ''

    # Breaking capacity, or short circuit rating, is the maximum rated current which the device can safely interrupt at the rated voltage. 
    interrupting_rating = ''

    # True if switch has load breaking capabiity.  Unless specified false, this is always assumed to be true for breakers and reclosers, which are types of switches. 
    load_break = ''

    # The lowest value of current that the switch can make, carry and break in uninterrupted duty at the rated voltage under specified operating conditions without suffering significant deterioration of its performance. 
    minimum_current = ''

    # The highest value of current the switch can make at the rated voltage under specified operating conditions without suffering significant deterioration of its performance. 
    making_capacity = ''

    # The number of poles of the switch {i.e. the number of current carrying conductors that are switched} 
    num_poles = ''

    # The highest value of current the switch can carry in the closed position at the rated voltage under specified operating conditions without suffering significant deterioration of its performance.  
    withstand_current = ''

    # The maximum rms voltage that may be applied across an open contact without breaking down the dielectric properties of the switch in the open position.  
    dielectric_strength = ''

    switch_type_asset = None

    switch_asset_model = None

    switch_assets = []

    # <<< switch_properties
    # @generated
    def __init__(self, gang='', remote='', interrupting_rating='', load_break='', minimum_current='', making_capacity='', num_poles='', withstand_current='', dielectric_strength='', switch_type_asset=None, switch_asset_model=None, switch_assets=[], **kw_args):
        """ Initialises a new 'SwitchProperties' instance.
        """
        self.gang = gang
        self.remote = remote
        self.interrupting_rating = interrupting_rating
        self.load_break = load_break
        self.minimum_current = minimum_current
        self.making_capacity = making_capacity
        self.num_poles = num_poles
        self.withstand_current = withstand_current
        self.dielectric_strength = dielectric_strength
        self.switch_type_asset = switch_type_asset
        self.switch_asset_model = switch_asset_model
        self.switch_assets = switch_assets

        super(SwitchProperties, self).__init__(**kw_args)
    # >>> switch_properties


class PointAssetsVersion(object):
 
    version = ''

 
    date = ''

    # <<< point_assets_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'PointAssetsVersion' instance.
        """
        self.version = version
        self.date = date

        super(PointAssetsVersion, self).__init__(**kw_args)
    # >>> point_assets_version


class ComEquipAsset(AssetContainer):
    """ Communicaiton equipment, other than media, such as gateways, routers, controllers, etc.
    """
    # The type of equipment. 
    type_com_equip = ''

    # <<< com_equip_asset
    # @generated
    def __init__(self, type_com_equip='', **kw_args):
        """ Initialises a new 'ComEquipAsset' instance.
        """
        self.type_com_equip = type_com_equip

        super(ComEquipAsset, self).__init__(**kw_args)
    # >>> com_equip_asset


class ComMediaAsset(Asset):
    """ Communication media such as fiber optic cable, power-line, telephone, etc.
    """
    # The type of communication media be used. 
    type_com_media = ''

    # <<< com_media_asset
    # @generated
    def __init__(self, type_com_media='', **kw_args):
        """ Initialises a new 'ComMediaAsset' instance.
        """
        self.type_com_media = type_com_media

        super(ComMediaAsset, self).__init__(**kw_args)
    # >>> com_media_asset


class AssetFunctionModel(AssetModel):
    """ Documentation for a type of an asset function of a particular product model made by a manufacturer (Organisation).    Asset Functions are typically component parts of Assets or Asset Containers.
    """
    asset_functions = []

    asset_function_type_asset = None

    # <<< asset_function_model
    # @generated
    def __init__(self, asset_functions=[], asset_function_type_asset=None, **kw_args):
        """ Initialises a new 'AssetFunctionModel' instance.
        """
        self.asset_functions = asset_functions
        self.asset_function_type_asset = asset_function_type_asset

        super(AssetFunctionModel, self).__init__(**kw_args)
    # >>> asset_function_model


class AssetFunction(Asset):
    """ A function performed by an asset.  Often a function is a module (or a board that plugs into a backplane) that can be replaced or updated without impacting the rest of the asset.  Therefore functions are treated as assets because they have life-cycles that are indepent of the asset containing the function.
    """
    # Name of program.  
    program_id = ''

    # Firmware version.  
    firmware_id = ''

    # Hardware version.  
    hardware_id = ''

 
    password = ''

    # The configuration specified for this function. 
    config_id = ''

    # The type of Asset Function, such as a communication module, device controller, metering function, etc. 
    type_asset_function = ''

    asset_function_model = None

    asset = None

    # <<< asset_function
    # @generated
    def __init__(self, program_id='', firmware_id='', hardware_id='', password='', config_id='', type_asset_function='', asset_function_model=None, asset=None, **kw_args):
        """ Initialises a new 'AssetFunction' instance.
        """
        self.program_id = program_id
        self.firmware_id = firmware_id
        self.hardware_id = hardware_id
        self.password = password
        self.config_id = config_id
        self.type_asset_function = type_asset_function
        self.asset_function_model = asset_function_model
        self.asset = asset

        super(AssetFunction, self).__init__(**kw_args)
    # >>> asset_function


class SwitchAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a switch asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    switch_type_asset = None

    switch_assets = []

    switch_properties = None

    # <<< switch_asset_model
    # @generated
    def __init__(self, switch_type_asset=None, switch_assets=[], switch_properties=None, **kw_args):
        """ Initialises a new 'SwitchAssetModel' instance.
        """
        self.switch_type_asset = switch_type_asset
        self.switch_assets = switch_assets
        self.switch_properties = switch_properties

        super(SwitchAssetModel, self).__init__(**kw_args)
    # >>> switch_asset_model


class Streetlight(ElectricalAsset):
    """ Streetlight asset.
    """
    #  Power rating of light. 
    light_rating_actual = ''

    # The specific type of streetlight.  For example, luminar, grid light, lantern, open bottom, flood, etc.  
    type_streetlight = ''

    # Lamp type currently installed, such as high pressure sodium, mercury vapor, metal halide, etc. 
    actual_lamp_type = ''

    #  Length of arm of this specific asset.   Note that a new light may be placed on an existing arm.   
    arm_length = ''

    attached_to_pole = None

    streetlight_asset_model = None

    # <<< streetlight
    # @generated
    def __init__(self, light_rating_actual='', type_streetlight='', actual_lamp_type='', arm_length='', attached_to_pole=None, streetlight_asset_model=None, **kw_args):
        """ Initialises a new 'Streetlight' instance.
        """
        self.light_rating_actual = light_rating_actual
        self.type_streetlight = type_streetlight
        self.actual_lamp_type = actual_lamp_type
        self.arm_length = arm_length
        self.attached_to_pole = attached_to_pole
        self.streetlight_asset_model = streetlight_asset_model

        super(Streetlight, self).__init__(**kw_args)
    # >>> streetlight


class MeterAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an meter asset of a particular product model made by a manufacturer (Organisation).   These types of meters are used to measure power consumption.  There are typically many instances of an asset associated with a single asset model.
    """
    #  Meter register ratio. 
    reg_ratio = ''

    #  Number of wires. 
    num_wires = ''

    #  Meter form number. 
    form = ''

    # Meter kh (watthour) constant. This constant is the number of watthours that must be applied to the meter to cause one disk revolution for an electromechanical meter or the number of watthours represented by one increment pulse for an electronic meter. 
    k_h = ''

    # The maximum number of registers this type of meter is capable of supporting.  The actual number in use is  based on the number of Registers associated with a given MeterAsset.  
    max_registers_capable = ''

    # True when the meter or meter+AMR module are capable of offering TOU data. 
    time_of_use_meter = ''

    # True when the meter is capable of metering real energy in kWh. 
    kwh_meter = ''

    # True when the meter is capable of metering apparent energy in kVAh. 
    k_vah_meter = ''

    # True when the meter or installed AMR option is capable of capturing demand data. 
    demand_meter = ''

    # True when the meter is capable of metering reactive energy in kVArh. 
    reactive_meter = ''

    # True when the meter is capable of metering reactive energy in kQh. 
    q_meter = ''

    # True when the meter or the installed AMR option is capable of capturing kWh interval data. 
    load_profile_meter = ''

    # TRUE when the meter or the installed AMR option is capable of capturing interval data for a user selectable measurement. The measurement might be kWh, Volts, or some other billing or engineering quantity. 
    interval_data_meter = ''

    meter_assets = []

    meter_type_asset = None

    service_delivery_points = []

    # <<< meter_asset_model
    # @generated
    def __init__(self, reg_ratio='', num_wires='', form='', k_h='', max_registers_capable='', time_of_use_meter='', kwh_meter='', k_vah_meter='', demand_meter='', reactive_meter='', q_meter='', load_profile_meter='', interval_data_meter='', meter_assets=[], meter_type_asset=None, service_delivery_points=[], **kw_args):
        """ Initialises a new 'MeterAssetModel' instance.
        """
        self.reg_ratio = reg_ratio
        self.num_wires = num_wires
        self.form = form
        self.k_h = k_h
        self.max_registers_capable = max_registers_capable
        self.time_of_use_meter = time_of_use_meter
        self.kwh_meter = kwh_meter
        self.k_vah_meter = k_vah_meter
        self.demand_meter = demand_meter
        self.reactive_meter = reactive_meter
        self.q_meter = q_meter
        self.load_profile_meter = load_profile_meter
        self.interval_data_meter = interval_data_meter
        self.meter_assets = meter_assets
        self.meter_type_asset = meter_type_asset
        self.service_delivery_points = service_delivery_points

        super(MeterAssetModel, self).__init__(**kw_args)
    # >>> meter_asset_model


class JointAsset(ElectricalAsset):
    """ A joint connects two or more cable assets.  The joint includes the portion of cable under wipes, welds, or other seals.
    """
    # Configuration of joint.  Examples include: 3 Wire to 1 Wire, 2 Wire to 1 Wire, 1 Wire to 1 Wire, Other. 
    configuration = ''

    # Material Used to fill the joint.  Examples include: No fill-prefab joint, Air-no filling, Petrolatum, Asphaltic, Oil, 254 Bluefill, No-Void, Epoxy, Insoluseal, Other. 
    fill = ''

    # The type of insulation around the joint. 
    insulation_type = ''

    # <<< joint_asset
    # @generated
    def __init__(self, configuration='', fill='', insulation_type='', **kw_args):
        """ Initialises a new 'JointAsset' instance.
        """
        self.configuration = configuration
        self.fill = fill
        self.insulation_type = insulation_type

        super(JointAsset, self).__init__(**kw_args)
    # >>> joint_asset


class TransformerModel(ElectricalAssetModel):
    """ Documentation for a type of a transformer of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    # The constructive type of the transformer e.g. CORE, SHELL  
    core_type = ''

    # True if this is an autotransformer, false otherwise. 
    auto_transformer = ''

    # Type of insultation used for transformer windings: Paper, Thermally Upgraded Paper, Nomex, other 
    winding_insulation = ''

    # Weight of solid insultation in transformer. 
    weight_solid_insulation = ''

    # Weight of core and coils in transformer. 
    weight_core_coils = ''

    # Oil Preservation System: Free Breathing, Nitrogen Blanket, Conservator, other 
    oil_preservation_system = ''

    # Basic Insulation Level of Neutral 
    neutral_bil = ''

    transformer_assets = []

    transformer_type_asset = None

    transformer_properties = None

    # <<< transformer_model
    # @generated
    def __init__(self, core_type='', auto_transformer='', winding_insulation='', weight_solid_insulation='', weight_core_coils='', oil_preservation_system='', neutral_bil='', transformer_assets=[], transformer_type_asset=None, transformer_properties=None, **kw_args):
        """ Initialises a new 'TransformerModel' instance.
        """
        self.core_type = core_type
        self.auto_transformer = auto_transformer
        self.winding_insulation = winding_insulation
        self.weight_solid_insulation = weight_solid_insulation
        self.weight_core_coils = weight_core_coils
        self.oil_preservation_system = oil_preservation_system
        self.neutral_bil = neutral_bil
        self.transformer_assets = transformer_assets
        self.transformer_type_asset = transformer_type_asset
        self.transformer_properties = transformer_properties

        super(TransformerModel, self).__init__(**kw_args)
    # >>> transformer_model


class TransformerAsset(ElectricalAsset):
    """ A specific physical (vs. logical) transformer.
    """
    # Date this asset was last reconditioned or had a major overhaul. 
    reconditioned_date = ''

    # Can the windings be re-configured to result in a different input or output voltage?  True if yes.  
    reconfig_windings = ''

    # Nominal voltage rating for alternate configuration for Primary Winding.  
    alt_primary_nom_voltage = ''

    # Nominal voltage rating for alternate configuration for Secondary Winding.  
    alt_secondary_nom_voltage = ''

    # 1 hour Overload Rating 
    hour_over_load_rating = ''

    # 24 hour Overload Rating 
    day_over_load_rating = ''

    # The type of transformer:  Power transformer; Voltage regulator; Autotransformer; Secondary transformer; Other 
    transformer_type = ''

    # Associated construction type.  Example values include: 1 phase, 3 phase, Aerial, Overhead, Dry type, Network, Padmount  dead front, Padmount - feed through, Padmount - live front,Padmount - loop through,Padmounted, Subway, Underground, Vault, Vault - three phase, and  Unknown. 
    construction_type = ''

    transformer_observations = []

    power_ratings = []

    transformer_model = None

    transformer_properties = None

    # <<< transformer_asset
    # @generated
    def __init__(self, reconditioned_date='', reconfig_windings='', alt_primary_nom_voltage='', alt_secondary_nom_voltage='', hour_over_load_rating='', day_over_load_rating='', transformer_type='', construction_type='', transformer_observations=[], power_ratings=[], transformer_model=None, transformer_properties=None, **kw_args):
        """ Initialises a new 'TransformerAsset' instance.
        """
        self.reconditioned_date = reconditioned_date
        self.reconfig_windings = reconfig_windings
        self.alt_primary_nom_voltage = alt_primary_nom_voltage
        self.alt_secondary_nom_voltage = alt_secondary_nom_voltage
        self.hour_over_load_rating = hour_over_load_rating
        self.day_over_load_rating = day_over_load_rating
        self.transformer_type = transformer_type
        self.construction_type = construction_type
        self.transformer_observations = transformer_observations
        self.power_ratings = power_ratings
        self.transformer_model = transformer_model
        self.transformer_properties = transformer_properties

        super(TransformerAsset, self).__init__(**kw_args)
    # >>> transformer_asset


class BushingModel(ElectricalAssetModel):
    """ Documentation for a type of a bushing of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    # Type of insulation used in bushing: Paper Oil, Compound, Solid Porcelain, other 
    insultation_type = ''

    bushing_asset = None

    bushing_type_asset = None

    # <<< bushing_model
    # @generated
    def __init__(self, insultation_type='', bushing_asset=None, bushing_type_asset=None, **kw_args):
        """ Initialises a new 'BushingModel' instance.
        """
        self.insultation_type = insultation_type
        self.bushing_asset = bushing_asset
        self.bushing_type_asset = bushing_type_asset

        super(BushingModel, self).__init__(**kw_args)
    # >>> bushing_model


class TapChangerModel(ElectricalAssetModel):
    """ Documentation for a type of a tap changer of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    # The number of taps. 
    number_taps = ''

    # Type of tap changer:  Resistive, Reactive, Vacuum, Other 
    ltc_type = ''

    tap_changer_assets = []

    tap_changer_type_asset = None

    # <<< tap_changer_model
    # @generated
    def __init__(self, number_taps='', ltc_type='', tap_changer_assets=[], tap_changer_type_asset=None, **kw_args):
        """ Initialises a new 'TapChangerModel' instance.
        """
        self.number_taps = number_taps
        self.ltc_type = ltc_type
        self.tap_changer_assets = tap_changer_assets
        self.tap_changer_type_asset = tap_changer_type_asset

        super(TapChangerModel, self).__init__(**kw_args)
    # >>> tap_changer_model


class BushingAsset(ElectricalAsset):
    """ A specific physical Bushing, which insulates and protects from abrasion a conductor that passes through it.  Note that a BushingAsset is associated with a specific Terminal which is associated with an instance of a type of ConductingEquipment.  
    """
    # Factory Measured Insulation Power Factor measured between the Power Factor tap and the bushing conductor. 
    c1_power_factor = ''

    # Factory Measured Capacitance measured between the Power Factor tap and the bushing conductor. 
    c1_capacitance = ''

    # Factory Measured Insulation Power Factor measured between the Power Factor tap and ground. 
    c2_power_factor = ''

    # Factory Measured Capacitance measured between the Power Factor tap and ground. 
    c2_capacitance = ''

    # The type of bushing. 
    bushing_type = ''

    bushing_insulation_pfs = []

    terminal = None

    bushing_model = None

    # <<< bushing_asset
    # @generated
    def __init__(self, c1_power_factor='', c1_capacitance='', c2_power_factor='', c2_capacitance='', bushing_type='', bushing_insulation_pfs=[], terminal=None, bushing_model=None, **kw_args):
        """ Initialises a new 'BushingAsset' instance.
        """
        self.c1_power_factor = c1_power_factor
        self.c1_capacitance = c1_capacitance
        self.c2_power_factor = c2_power_factor
        self.c2_capacitance = c2_capacitance
        self.bushing_type = bushing_type
        self.bushing_insulation_pfs = bushing_insulation_pfs
        self.terminal = terminal
        self.bushing_model = bushing_model

        super(BushingAsset, self).__init__(**kw_args)
    # >>> bushing_asset


class StreetlightAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a streelight of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    #  Power rating of light as supplied by the manufacturer. 
    light_rating_supplied = ''

    # Lamp type supplied from manufacturer (vs. one that has been replaced in the field), such as high pressure sodium, mercury vapor, metal halide, etc. 
    lamp_type_supplied = ''

    streetlights = []

    streetlight_type_asset = []

    # <<< streetlight_asset_model
    # @generated
    def __init__(self, light_rating_supplied='', lamp_type_supplied='', streetlights=[], streetlight_type_asset=[], **kw_args):
        """ Initialises a new 'StreetlightAssetModel' instance.
        """
        self.light_rating_supplied = light_rating_supplied
        self.lamp_type_supplied = lamp_type_supplied
        self.streetlights = streetlights
        self.streetlight_type_asset = streetlight_type_asset

        super(StreetlightAssetModel, self).__init__(**kw_args)
    # >>> streetlight_asset_model


class ShuntCompensatorAssetModel(ElectricalAssetModel):
    """ For application as shunt capacitor or reactor or switchable bank of shunt capacitors or reactors, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    shunt_compensator_assets = []

    shunt_compensator_type_asset = None

    shunt_impedance_properties = None

    # <<< shunt_compensator_asset_model
    # @generated
    def __init__(self, shunt_compensator_assets=[], shunt_compensator_type_asset=None, shunt_impedance_properties=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorAssetModel' instance.
        """
        self.shunt_compensator_assets = shunt_compensator_assets
        self.shunt_compensator_type_asset = shunt_compensator_type_asset
        self.shunt_impedance_properties = shunt_impedance_properties

        super(ShuntCompensatorAssetModel, self).__init__(**kw_args)
    # >>> shunt_compensator_asset_model


class GeneratorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of generation equipment of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    generator_assets = []

    generator_type_asset = None

    # <<< generator_asset_model
    # @generated
    def __init__(self, generator_assets=[], generator_type_asset=None, **kw_args):
        """ Initialises a new 'GeneratorAssetModel' instance.
        """
        self.generator_assets = generator_assets
        self.generator_type_asset = generator_type_asset

        super(GeneratorAssetModel, self).__init__(**kw_args)
    # >>> generator_asset_model


class ShuntCompensatorAsset(ElectricalAsset):
    """ For a shunt capacitor or reactor or switchable bank of shunt capacitors or reactors, this is the physical asset performing the ShuntCompensator role (PSR).
    """
    # Type of shunt compensator. 
    shunt_compensator_type = ''

    shunt_compensator_asset_model = None

    shunt_impedance_properties = None

    # <<< shunt_compensator_asset
    # @generated
    def __init__(self, shunt_compensator_type='', shunt_compensator_asset_model=None, shunt_impedance_properties=None, **kw_args):
        """ Initialises a new 'ShuntCompensatorAsset' instance.
        """
        self.shunt_compensator_type = shunt_compensator_type
        self.shunt_compensator_asset_model = shunt_compensator_asset_model
        self.shunt_impedance_properties = shunt_impedance_properties

        super(ShuntCompensatorAsset, self).__init__(**kw_args)
    # >>> shunt_compensator_asset


class GeneratorAsset(ElectricalAsset):
    """ A physical piece of generation equipment performing the Generator role.
    """
    # Type of generator: e.g. Synchronous, Inductive etc. 
    generator_type = ''

    generator_asset_model = None

    # <<< generator_asset
    # @generated
    def __init__(self, generator_type='', generator_asset_model=None, **kw_args):
        """ Initialises a new 'GeneratorAsset' instance.
        """
        self.generator_type = generator_type
        self.generator_asset_model = generator_asset_model

        super(GeneratorAsset, self).__init__(**kw_args)
    # >>> generator_asset


class SwitchAsset(ElectricalAsset):
    """ The physical asset performing Switch function.
    """
 
    switch_type = ''

    switch_asset_models = None

    switch_properties = None

    # <<< switch_asset
    # @generated
    def __init__(self, switch_type='', switch_asset_models=None, switch_properties=None, **kw_args):
        """ Initialises a new 'SwitchAsset' instance.
        """
        self.switch_type = switch_type
        self.switch_asset_models = switch_asset_models
        self.switch_properties = switch_properties

        super(SwitchAsset, self).__init__(**kw_args)
    # >>> switch_asset


class SurgeProtectorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an SurgeProtector asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    surge_protector_type_asset = None

    surge_protector_asset = []

    # <<< surge_protector_asset_model
    # @generated
    def __init__(self, surge_protector_type_asset=None, surge_protector_asset=[], **kw_args):
        """ Initialises a new 'SurgeProtectorAssetModel' instance.
        """
        self.surge_protector_type_asset = surge_protector_type_asset
        self.surge_protector_asset = surge_protector_asset

        super(SurgeProtectorAssetModel, self).__init__(**kw_args)
    # >>> surge_protector_asset_model


class BusbarAsset(ElectricalAsset):
    """ A BusbarAsset is the physical asset used to perform the BusbarSection's role. 
    """
    # The type of busbar. 
    busbar_type = ''

    busbar_asset_model = None

    # <<< busbar_asset
    # @generated
    def __init__(self, busbar_type='', busbar_asset_model=None, **kw_args):
        """ Initialises a new 'BusbarAsset' instance.
        """
        self.busbar_type = busbar_type
        self.busbar_asset_model = busbar_asset_model

        super(BusbarAsset, self).__init__(**kw_args)
    # >>> busbar_asset


class BusbarAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a busbar asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    busbar_asset = []

    busbar_asset_model = None

    # <<< busbar_asset_model
    # @generated
    def __init__(self, busbar_asset=[], busbar_asset_model=None, **kw_args):
        """ Initialises a new 'BusbarAssetModel' instance.
        """
        self.busbar_asset = busbar_asset
        self.busbar_asset_model = busbar_asset_model

        super(BusbarAssetModel, self).__init__(**kw_args)
    # >>> busbar_asset_model


class CTAsset(ElectricalAsset):
    """ The physical asset performing  Current Transformer (CT) function, which is used to measure electrical qualities of the circuit that is being protected and/or monitored.  Typically used as current transducer for the purpose of metering- or protection.
    """
    # Type of CT. 
    ct_type = ''

    ctasset_model = None

    ct = None

    ctproperties = None

    # <<< ctasset
    # @generated
    def __init__(self, ct_type='', ctasset_model=None, ct=None, ctproperties=None, **kw_args):
        """ Initialises a new 'CTAsset' instance.
        """
        self.ct_type = ct_type
        self.ctasset_model = ctasset_model
        self.ct = ct
        self.ctproperties = ctproperties

        super(CTAsset, self).__init__(**kw_args)
    # >>> ctasset


class CTAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Current Transformer (CT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    cttype_asset = None

    ctasset = []

    ctproperties = None

    # <<< ctasset_model
    # @generated
    def __init__(self, cttype_asset=None, ctasset=[], ctproperties=None, **kw_args):
        """ Initialises a new 'CTAssetModel' instance.
        """
        self.cttype_asset = cttype_asset
        self.ctasset = ctasset
        self.ctproperties = ctproperties

        super(CTAssetModel, self).__init__(**kw_args)
    # >>> ctasset_model


class FaultIndicationAsset(ElectricalAsset):
    """ A physical Faultindicator, which is often used for FLISR (Fault Location, Isolation and Restoration) purposes, assisting with the dispatch of crews to “most likely” part of the network (i.e. assists with determining circuit section where the fault most likely happened).
    """
    # The type of fault indicator. 
    fault_indicator_type = ''

    fault_indicator = None

    fault_indicator_asset_model = None

    # <<< fault_indication_asset
    # @generated
    def __init__(self, fault_indicator_type='', fault_indicator=None, fault_indicator_asset_model=None, **kw_args):
        """ Initialises a new 'FaultIndicationAsset' instance.
        """
        self.fault_indicator_type = fault_indicator_type
        self.fault_indicator = fault_indicator
        self.fault_indicator_asset_model = fault_indicator_asset_model

        super(FaultIndicationAsset, self).__init__(**kw_args)
    # >>> fault_indication_asset


class FaultIndicatorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of an FaultIndicator asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    fault_indicator_type_asset = None

    fault_indicator_asset = []

    # <<< fault_indicator_asset_model
    # @generated
    def __init__(self, fault_indicator_type_asset=None, fault_indicator_asset=[], **kw_args):
        """ Initialises a new 'FaultIndicatorAssetModel' instance.
        """
        self.fault_indicator_type_asset = fault_indicator_type_asset
        self.fault_indicator_asset = fault_indicator_asset

        super(FaultIndicatorAssetModel, self).__init__(**kw_args)
    # >>> fault_indicator_asset_model


class PTAsset(ElectricalAsset):
    """ The physical asset performing  Potential Transformer (PT) function, which is used to measure electrical qualities of the circuit that is being protected and/or monitored.  A PT is typically used as voltage transducer for the purpose of metering-, protection-, or sometimes auxiliary substation supply.
    """
    # The type of PT. 
    pttype = ''

    ptasset_model = None

    pt = None

    ptproperties = None

    # <<< ptasset
    # @generated
    def __init__(self, pttype='', ptasset_model=None, pt=None, ptproperties=None, **kw_args):
        """ Initialises a new 'PTAsset' instance.
        """
        self.pttype = pttype
        self.ptasset_model = ptasset_model
        self.pt = pt
        self.ptproperties = ptproperties

        super(PTAsset, self).__init__(**kw_args)
    # >>> ptasset


class PTAssetModel(ElectricalAssetModel):
    """ A particular model supplied by a manufacturer of a Potential Transformer (PT), wich is used to measure electrical qualities of the circuit that is being protected and/or monitored.
    """
    ptasset = []

    pttype_asset = None

    ptproperties = None

    # <<< ptasset_model
    # @generated
    def __init__(self, ptasset=[], pttype_asset=None, ptproperties=None, **kw_args):
        """ Initialises a new 'PTAssetModel' instance.
        """
        self.ptasset = ptasset
        self.pttype_asset = pttype_asset
        self.ptproperties = ptproperties

        super(PTAssetModel, self).__init__(**kw_args)
    # >>> ptasset_model


class ResistorAsset(ElectricalAsset):
    """ An ResistorAsset is the physical asset used to perform the Resistor's role. 
    """
    # Type of resistor. 
    resistor_type = ''

    resistor = None

    resistor_asset_model = None

    # <<< resistor_asset
    # @generated
    def __init__(self, resistor_type='', resistor=None, resistor_asset_model=None, **kw_args):
        """ Initialises a new 'ResistorAsset' instance.
        """
        self.resistor_type = resistor_type
        self.resistor = resistor
        self.resistor_asset_model = resistor_asset_model

        super(ResistorAsset, self).__init__(**kw_args)
    # >>> resistor_asset


class ResistorAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a resistor asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    resistor_type_asset = None

    resistor_asset = []

    # <<< resistor_asset_model
    # @generated
    def __init__(self, resistor_type_asset=None, resistor_asset=[], **kw_args):
        """ Initialises a new 'ResistorAssetModel' instance.
        """
        self.resistor_type_asset = resistor_type_asset
        self.resistor_asset = resistor_asset

        super(ResistorAssetModel, self).__init__(**kw_args)
    # >>> resistor_asset_model


class SurgeProtectorAsset(ElectricalAsset):
    """ An SurgeProtector asset is the physical asset used to perform the SurgeProtector's role. 
    """
    # The  type of surge protection. 
    surge_protector_type = ''

    surge_protector = None

    surge_protector_asset_model = None

    # <<< surge_protector_asset
    # @generated
    def __init__(self, surge_protector_type='', surge_protector=None, surge_protector_asset_model=None, **kw_args):
        """ Initialises a new 'SurgeProtectorAsset' instance.
        """
        self.surge_protector_type = surge_protector_type
        self.surge_protector = surge_protector
        self.surge_protector_asset_model = surge_protector_asset_model

        super(SurgeProtectorAsset, self).__init__(**kw_args)
    # >>> surge_protector_asset


class ProtectEquipAssetModel(ElectricalAssetModel):
    """ Documentation for a type of protection equipment asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    portect_equip_assets = []

    protect_equip_type_asset = None

    # <<< protect_equip_asset_model
    # @generated
    def __init__(self, portect_equip_assets=[], protect_equip_type_asset=None, **kw_args):
        """ Initialises a new 'ProtectEquipAssetModel' instance.
        """
        self.portect_equip_assets = portect_equip_assets
        self.protect_equip_type_asset = protect_equip_type_asset

        super(ProtectEquipAssetModel, self).__init__(**kw_args)
    # >>> protect_equip_asset_model


class ProtectEquipAsset(ElectricalAsset):
    """ The physical electrical device designed to respond to input conditions in a prescribed manner and after specified conditions are met to cause contact operation or similar abrupt change in associated electric control circuits, or simply to display the detected condition.  It is closely associtaed with the ProtectionEquipment.
    """
    # The actual ground trip (Amps) for this type of relay, if applicable. 
    ground_trip = ''

    # The actual phase trip (Amps) for this type of relay, if applicable. 
    phase_trip = ''

    # The type of protection equipment. 
    p_etype = ''

    protect_equip_asset_model = None

    # <<< protect_equip_asset
    # @generated
    def __init__(self, ground_trip='', phase_trip='', p_etype='', protect_equip_asset_model=None, **kw_args):
        """ Initialises a new 'ProtectEquipAsset' instance.
        """
        self.ground_trip = ground_trip
        self.phase_trip = phase_trip
        self.p_etype = p_etype
        self.protect_equip_asset_model = protect_equip_asset_model

        super(ProtectEquipAsset, self).__init__(**kw_args)
    # >>> protect_equip_asset


class FACTSDeviceAsset(ElectricalAsset):
    """ An FACTSDevice asset is the physical asset used to perform the FACTSDevice's role. 
    """
    # FACTS devices include: SVC = Static Var Compensator STATCOM = Static Synchronous Compensator TCPAR = Thyristor Controlled Phase-Angle Regulator TCSC = Thyristor Controlled Series Capacitor TCVL = Thyristor Controlled Voltage Limiter TSBR = Thyristor Switched Braking Resistor TSSC = Thyristor Switched Series Capacitor UPFC = Unified Power Flow Controller 
    facts_type = ''

    factsdevice_asset_model = None

    # <<< factsdevice_asset
    # @generated
    def __init__(self, facts_type='', factsdevice_asset_model=None, **kw_args):
        """ Initialises a new 'FACTSDeviceAsset' instance.
        """
        self.facts_type = facts_type
        self.factsdevice_asset_model = factsdevice_asset_model

        super(FACTSDeviceAsset, self).__init__(**kw_args)
    # >>> factsdevice_asset


class FACTSDeviceAssetModel(ElectricalAssetModel):
    """ A particular model of FACTS device provided from a manufacturer.  A FACTS devices are used for the dynamic control of voltage, impedance and phase angle of high voltage AC transmission lines.  FACTS device types include: - SVC = Static Var Compensator - STATCOM = Static Synchronous Compensator - TCPAR = Thyristor Controlled Phase-Angle Regulator - TCSC = Thyristor Controlled Series Capacitor - TCVL = Thyristor Controlled Voltage Limiter - TSBR = Thyristor Switched Braking Resistor - TSSC = Thyristor Switched Series Capacitor - UPFC = Unified Power Flow Controller
    """
    factsdevice_type_asset = None

    factsdevice_assets = []

    # <<< factsdevice_asset_model
    # @generated
    def __init__(self, factsdevice_type_asset=None, factsdevice_assets=[], **kw_args):
        """ Initialises a new 'FACTSDeviceAssetModel' instance.
        """
        self.factsdevice_type_asset = factsdevice_type_asset
        self.factsdevice_assets = factsdevice_assets

        super(FACTSDeviceAssetModel, self).__init__(**kw_args)
    # >>> factsdevice_asset_model


class BreakerAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a breaker asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    breaker_asset = []

    breaker_type_asset = None

    breaker_properties = None

    # <<< breaker_asset_model
    # @generated
    def __init__(self, breaker_asset=[], breaker_type_asset=None, breaker_properties=None, **kw_args):
        """ Initialises a new 'BreakerAssetModel' instance.
        """
        self.breaker_asset = breaker_asset
        self.breaker_type_asset = breaker_type_asset
        self.breaker_properties = breaker_properties

        super(BreakerAssetModel, self).__init__(**kw_args)
    # >>> breaker_asset_model


class RecloserAssetModel(ElectricalAssetModel):
    """ Documentation for a type of a recloser asset of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
    """
    recloser_assets = []

    recloser_type_asset = None

    recloser_properties = None

    # <<< recloser_asset_model
    # @generated
    def __init__(self, recloser_assets=[], recloser_type_asset=None, recloser_properties=None, **kw_args):
        """ Initialises a new 'RecloserAssetModel' instance.
        """
        self.recloser_assets = recloser_assets
        self.recloser_type_asset = recloser_type_asset
        self.recloser_properties = recloser_properties

        super(RecloserAssetModel, self).__init__(**kw_args)
    # >>> recloser_asset_model


class BreakerAsset(ElectricalAsset):
    """ A physical breaker performing the Breaker role.
    """
    # The type of breaker. 
    breaker_type = ''

    breaker_asset_model = None

    breaker_properties = None

    # <<< breaker_asset
    # @generated
    def __init__(self, breaker_type='', breaker_asset_model=None, breaker_properties=None, **kw_args):
        """ Initialises a new 'BreakerAsset' instance.
        """
        self.breaker_type = breaker_type
        self.breaker_asset_model = breaker_asset_model
        self.breaker_properties = breaker_properties

        super(BreakerAsset, self).__init__(**kw_args)
    # >>> breaker_asset


class RecloserAsset(ElectricalAsset):
    """ A physical recloser performing a reclosing function, which is modeled through the PSR Breaker.
    """
    # Type of recloser. 
    recloser_type = ''

    recloser_asset_model = None

    recloser_properties = None

    # <<< recloser_asset
    # @generated
    def __init__(self, recloser_type='', recloser_asset_model=None, recloser_properties=None, **kw_args):
        """ Initialises a new 'RecloserAsset' instance.
        """
        self.recloser_type = recloser_type
        self.recloser_asset_model = recloser_asset_model
        self.recloser_properties = recloser_properties

        super(RecloserAsset, self).__init__(**kw_args)
    # >>> recloser_asset


class RecloserProperties(SwitchProperties):
    """ Properties of reclosers.
    """
    # Phase trip rating (amps). 
    phase_trip = ''

    # Total number of phase reclose operations. 
    num_reclose_lockout = ''

    # Flag indicating device has ground trip capability (true = has ground trip capability). 
    ground_trip_capable = ''

    # Normal status of ground trip (true = enabled). 
    ground_trip_normal_enabled = ''

    # Ground trip rating (amps). 
    ground_trip_rating = ''

    recloser_type_asset = None

    recloser_asset_models = []

    recloser_assets = []

    # <<< recloser_properties
    # @generated
    def __init__(self, phase_trip='', num_reclose_lockout='', ground_trip_capable='', ground_trip_normal_enabled='', ground_trip_rating='', recloser_type_asset=None, recloser_asset_models=[], recloser_assets=[], **kw_args):
        """ Initialises a new 'RecloserProperties' instance.
        """
        self.phase_trip = phase_trip
        self.num_reclose_lockout = num_reclose_lockout
        self.ground_trip_capable = ground_trip_capable
        self.ground_trip_normal_enabled = ground_trip_normal_enabled
        self.ground_trip_rating = ground_trip_rating
        self.recloser_type_asset = recloser_type_asset
        self.recloser_asset_models = recloser_asset_models
        self.recloser_assets = recloser_assets

        super(RecloserProperties, self).__init__(**kw_args)
    # >>> recloser_properties


class BreakerProperties(SwitchProperties):
    """ Properties of reclosers.
    """
    # Phase trip rating (amps). 
    phase_trip = ''

    breaker_asset_models = []

    breaker_type_asset = None

    breaker_assets = []

    # <<< breaker_properties
    # @generated
    def __init__(self, phase_trip='', breaker_asset_models=[], breaker_type_asset=None, breaker_assets=[], **kw_args):
        """ Initialises a new 'BreakerProperties' instance.
        """
        self.phase_trip = phase_trip
        self.breaker_asset_models = breaker_asset_models
        self.breaker_type_asset = breaker_type_asset
        self.breaker_assets = breaker_assets

        super(BreakerProperties, self).__init__(**kw_args)
    # >>> breaker_properties


class ComFuncAssetModel(ElectricalAssetModel):
    """ Documentation for a type of communication function of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    com_func_type_asset = None

    com_functions = []

    # <<< com_func_asset_model
    # @generated
    def __init__(self, com_func_type_asset=None, com_functions=[], **kw_args):
        """ Initialises a new 'ComFuncAssetModel' instance.
        """
        self.com_func_type_asset = com_func_type_asset
        self.com_functions = com_functions

        super(ComFuncAssetModel, self).__init__(**kw_args)
    # >>> com_func_asset_model


class SeriesCompensatorAsset(ElectricalAsset):
    """ For a a series capacitor or reactor, this is the physical asset performing the SeriesCompensator role (PSR).
    """
    # Type of series compensator. 
    series_compensator_type = ''

    series_compensator_asset_model = None

    # <<< series_compensator_asset
    # @generated
    def __init__(self, series_compensator_type='', series_compensator_asset_model=None, **kw_args):
        """ Initialises a new 'SeriesCompensatorAsset' instance.
        """
        self.series_compensator_type = series_compensator_type
        self.series_compensator_asset_model = series_compensator_asset_model

        super(SeriesCompensatorAsset, self).__init__(**kw_args)
    # >>> series_compensator_asset


class SeriesCompensatorAssetModel(ElectricalAssetModel):
    """ For application as a series capacitor or reactor, this is documentation for a type of a capacitor or reactor of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model. 
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
    """ Documentation for a type of a Static Var Compensator of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    svcassets = []

    svctype_asset = None

    svcproperties = None

    # <<< svcasset_model
    # @generated
    def __init__(self, svcassets=[], svctype_asset=None, svcproperties=None, **kw_args):
        """ Initialises a new 'SVCAssetModel' instance.
        """
        self.svcassets = svcassets
        self.svctype_asset = svctype_asset
        self.svcproperties = svcproperties

        super(SVCAssetModel, self).__init__(**kw_args)
    # >>> svcasset_model


class SVCAsset(FACTSDeviceAsset):
    """ A physical StaticVarCompensator performing the role of the SVCompensator.
    """
    # The type of SVC. 
    svc_type = ''

    svcasset_model = None

    svcproperties = None

    # <<< svcasset
    # @generated
    def __init__(self, svc_type='', svcasset_model=None, svcproperties=None, **kw_args):
        """ Initialises a new 'SVCAsset' instance.
        """
        self.svc_type = svc_type
        self.svcasset_model = svcasset_model
        self.svcproperties = svcproperties

        super(SVCAsset, self).__init__(**kw_args)
    # >>> svcasset


# <<< pointassethierarchy
# @generated
# >>> pointassethierarchy

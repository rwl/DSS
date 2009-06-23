# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import Curve
from cim14.iec61970.core import Equipment
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61970.core import RegularIntervalSchedule

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Generation.Production"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Generation.Production"

class Reservoir(PowerSystemResource):
    """ A water storage facility within a hydro system, including: ponds, lakes, lagoons, and rivers. The storage is usually behind some type of dam.
    """
    # The length of the spillway crest 
    spillway_crest_length = ''

    # River outlet works for riparian right releases or other purposes 
    river_outlet_works = ''

    # Normal minimum operating level below which the penstocks will draw air 
    normal_min_operate_level = ''

    # Total capacity of reservoir 
    gross_capacity = ''

    # Spillway crest level above which water will spill 
    spillway_crest_level = ''

    # The reservoir's energy storage rating in energy for given head conditions 
    energy_storage_rating = 0.0

    # Full supply level, above which water will spill. This can be the spillway crest level or the top of closed gates. 
    full_supply_level = ''

    # Type of spillway gate, including parameters 
    spill_way_gate_type = ''

    # The flow capacity of the spillway in cubic meters per second 
    spillway_capacity = 0.0

    # Storage volume between the full supply level and the normal minimum operating level 
    active_storage_capacity = ''

    # The spillway water travel delay to the next downstream reservoir 
    spill_travel_delay = ''

    # Generators are supplied water from or pumps discharge water to an upstream reservoir
    upstream_from_hydro_power_plants = []

    # A reservoir may spill into a downstream reservoir
    spills_into_reservoirs = []

    # A reservoir may have a water level target schedule.
    target_level_schedule = None

    # A reservoir may spill into a downstream reservoir
    spills_from_reservoir = None

    # A reservoir may have a level versus volume relationship.
    level_vs_volume_curves = []

    # A reservoir may have a 'natural' inflow forecast.
    inflow_forecasts = []

    # Generators discharge water to or pumps are supplied water from a downstream reservoir
    hydro_power_plants = []

    # <<< reservoir
    # @generated
    def __init__(self, spillway_crest_length='', river_outlet_works='', normal_min_operate_level='', gross_capacity='', spillway_crest_level='', energy_storage_rating=0.0, full_supply_level='', spill_way_gate_type='', spillway_capacity=0.0, active_storage_capacity='', spill_travel_delay='', upstream_from_hydro_power_plants=[], spills_into_reservoirs=[], target_level_schedule=None, spills_from_reservoir=None, level_vs_volume_curves=[], inflow_forecasts=[], hydro_power_plants=[], **kw_args):
        """ Initialises a new 'Reservoir' instance.
        """
        self.spillway_crest_length = spillway_crest_length
        self.river_outlet_works = river_outlet_works
        self.normal_min_operate_level = normal_min_operate_level
        self.gross_capacity = gross_capacity
        self.spillway_crest_level = spillway_crest_level
        self.energy_storage_rating = energy_storage_rating
        self.full_supply_level = full_supply_level
        self.spill_way_gate_type = spill_way_gate_type
        self.spillway_capacity = spillway_capacity
        self.active_storage_capacity = active_storage_capacity
        self.spill_travel_delay = spill_travel_delay
        self.upstream_from_hydro_power_plants = upstream_from_hydro_power_plants
        self.spills_into_reservoirs = spills_into_reservoirs
        self.target_level_schedule = target_level_schedule
        self.spills_from_reservoir = spills_from_reservoir
        self.level_vs_volume_curves = level_vs_volume_curves
        self.inflow_forecasts = inflow_forecasts
        self.hydro_power_plants = hydro_power_plants

        super(Reservoir, self).__init__(**kw_args)
    # >>> reservoir


class GrossToNetActivePowerCurve(Curve):
    """ Relationship between the generating unit's gross active power output on the X-axis (measured at the terminals of the machine(s)) and the generating unit's net active power output on the Y-axis (based on utility-defined measurements at the power station). Station service loads, when modeled, should be treated as non-conforming bus loads. There may be more than one curve, depending on the auxiliary equipment that is in service.
    """
    # A generating unit may have a gross active power to net active power curve, describing the losses and auxiliary power requirements of the unit
    generating_unit = None

    # <<< gross_to_net_active_power_curve
    # @generated
    def __init__(self, generating_unit=None, **kw_args):
        """ Initialises a new 'GrossToNetActivePowerCurve' instance.
        """
        self.generating_unit = generating_unit

        super(GrossToNetActivePowerCurve, self).__init__(**kw_args)
    # >>> gross_to_net_active_power_curve


class ShutdownCurve(Curve):
    """ Relationship between the rate in gross active power/minute (Y-axis) at which a unit should be shutdown and its present gross MW output (X-axis)
    """
    # Fixed shutdown cost 
    shutdown_cost = ''

    # The date and time of the most recent generating unit shutdown 
    shutdown_date = ''

    # A thermal generating unit may have a shutdown curve
    thermal_generating_unit = None

    # <<< shutdown_curve
    # @generated
    def __init__(self, shutdown_cost='', shutdown_date='', thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'ShutdownCurve' instance.
        """
        self.shutdown_cost = shutdown_cost
        self.shutdown_date = shutdown_date
        self.thermal_generating_unit = thermal_generating_unit

        super(ShutdownCurve, self).__init__(**kw_args)
    # >>> shutdown_curve


class HydroPump(PowerSystemResource):
    """ A synchronous motor-driven pump, typically associated with a pumped storage plant
    """
    # The pumping power under minimum head conditions, usually at full gate. 
    pump_power_at_min_head = ''

    # The pumping discharge (m3/sec) under minimum head conditions, usually at full gate 
    pump_disch_at_min_head = 0.0

    # The pumping discharge (m3/sec) under maximum head conditions, usually at full gate 
    pump_disch_at_max_head = 0.0

    # The pumping power under maximum head conditions, usually at full gate 
    pump_power_at_max_head = ''

    # The hydro pump has a pumping schedule over time, indicating when pumping is to occur.
    hydro_pump_op_schedule = None

    # The synchronous machine drives the turbine which moves the water from a low elevation to a higher elevation. The direction of machine rotation for pumping may or may not be the same as for generating.
    synchronous_machine = None

    # The hydro pump may be a member of a pumped storage plant or a pump for distributing water
    hydro_power_plant = None

    # <<< hydro_pump
    # @generated
    def __init__(self, pump_power_at_min_head='', pump_disch_at_min_head=0.0, pump_disch_at_max_head=0.0, pump_power_at_max_head='', hydro_pump_op_schedule=None, synchronous_machine=None, hydro_power_plant=None, **kw_args):
        """ Initialises a new 'HydroPump' instance.
        """
        self.pump_power_at_min_head = pump_power_at_min_head
        self.pump_disch_at_min_head = pump_disch_at_min_head
        self.pump_disch_at_max_head = pump_disch_at_max_head
        self.pump_power_at_max_head = pump_power_at_max_head
        self.hydro_pump_op_schedule = hydro_pump_op_schedule
        self.synchronous_machine = synchronous_machine
        self.hydro_power_plant = hydro_power_plant

        super(HydroPump, self).__init__(**kw_args)
    # >>> hydro_pump


class EmissionCurve(Curve):
    """ Relationship between the unit's emission rate in units of mass per hour (Y-axis) and output active power (X-axis) for a given type of emission. This curve applies when only one type of fuel is being burned.
    """
    # Flag is set to true when output is expressed in net active power 
    is_net_gross_p = False

    # The type of emission, which also gives the production rate measurement unit. The y1AxisUnits of the curve contains the unit of measure (e.g. kg) and the emissionType is the type of emission (e.g. sulfer dioxide). Values are: "nitrogen_oxide", "chlorine", "hydrogen_sulfide", "sulfur_dioxide", "carbon_disulfide", "carbon_dioxide"
    emission_type = 'nitrogen_oxide'

    # The emission content per quantity of fuel burned 
    emission_content = ''

    # A thermal generating unit may have  one or more emission curves
    thermal_generating_unit = None

    # <<< emission_curve
    # @generated
    def __init__(self, is_net_gross_p=False, emission_type='nitrogen_oxide', emission_content='', thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'EmissionCurve' instance.
        """
        self.is_net_gross_p = is_net_gross_p
        self.emission_type = emission_type
        self.emission_content = emission_content
        self.thermal_generating_unit = thermal_generating_unit

        super(EmissionCurve, self).__init__(**kw_args)
    # >>> emission_curve


class AirCompressor(PowerSystemResource):
    """ Combustion turbine air compressor which is an integral part of a compressed air energy storage (CAES) plant
    """
    # Rating of the CAES air compressor 
    air_compressor_rating = 0.0

    # A CAES air compressor is driven by combustion turbine
    combustion_turbine = None

    # An air compressor may be a member of a compressed air energy storage plant
    caesplant = None

    # <<< air_compressor
    # @generated
    def __init__(self, air_compressor_rating=0.0, combustion_turbine=None, caesplant=None, **kw_args):
        """ Initialises a new 'AirCompressor' instance.
        """
        self.air_compressor_rating = air_compressor_rating
        self.combustion_turbine = combustion_turbine
        self.caesplant = caesplant

        super(AirCompressor, self).__init__(**kw_args)
    # >>> air_compressor


class EmissionAccount(Curve):
    """ Accounts for tracking emissions usage and credits for thermal generating units. A unit may have zero or more emission accounts, and will typically have one for tracking usage and one for tracking credits.
    """
    # The type of emission, for example sulfur dioxide (SO2). The y1AxisUnits of the curve contains the unit of measure (e.g. kg) and the emissionType is the type of emission (e.g. sulfer dioxide). Values are: "nitrogen_oxide", "chlorine", "hydrogen_sulfide", "sulfur_dioxide", "carbon_disulfide", "carbon_dioxide"
    emission_type = 'nitrogen_oxide'

    # The source of the emission value. Values are: "calculated", "measured"
    emission_value_source = 'calculated'

    # A thermal generating unit may have one or more emission allowance accounts
    thermal_generating_unit = None

    # <<< emission_account
    # @generated
    def __init__(self, emission_type='nitrogen_oxide', emission_value_source='calculated', thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'EmissionAccount' instance.
        """
        self.emission_type = emission_type
        self.emission_value_source = emission_value_source
        self.thermal_generating_unit = thermal_generating_unit

        super(EmissionAccount, self).__init__(**kw_args)
    # >>> emission_account


class LevelVsVolumeCurve(Curve):
    """ Relationship between reservoir volume and reservoir level. The  volume is at the y-axis and the reservoir level at the x-axis.
    """
    # A reservoir may have a level versus volume relationship.
    reservoir = None

    # <<< level_vs_volume_curve
    # @generated
    def __init__(self, reservoir=None, **kw_args):
        """ Initialises a new 'LevelVsVolumeCurve' instance.
        """
        self.reservoir = reservoir

        super(LevelVsVolumeCurve, self).__init__(**kw_args)
    # >>> level_vs_volume_curve


class GeneratingUnit(Equipment):
    """ A single or set of synchronous machines for converting mechanical power into alternating-current power. For example, individual machines within a set may be defined for scheduling purposes while a single control signal is derived for the set. In this case there would be a GeneratingUnit for each member of the set and an additional GeneratingUnit corresponding to the set.
    """
    # The source of controls for a generating unit. Values are: "unavailable", "off_agc", "on_agc", "plant_control"
    gen_control_source = 'unavailable'

    # Time it takes to get the unit on-line, from the time that the prime mover mechanical power is applied 
    startup_time = ''

    # This is the minimum operating active power limit the dispatcher can enter for this unit. 
    min_operating_p = ''

    # High limit for secondary (AGC) control 
    high_control_limit = ''

    # Minimum time interval between unit shutdown and startup 
    minimum_off_time = ''

 
    energy_min_p = ''

    # For dispatchable units, this value represents the economic active power basepoint, for units that are not dispatchable, this value represents the fixed generation value. The value must be between the operating low and high limits. 
    base_p = ''

    # Low economic active power limit that must be greater than or equal to the minimum operating active power limit 
    min_economic_p = ''

 
    spin_reserve_ramp = ''

    # Pulse high limit which is the largest control pulse that the unit can respond to 
    control_pulse_high = ''

    # Low limit for secondary (AGC) control 
    low_control_limit = ''

    # Governor Speed Changer Droop.   This is the change in generator power output divided by the change in frequency normalized by the nominal power of the generator and the nominal frequency and expressed in percent and negated. A positive value of speed change droop provides additional generator output upon a drop in frequency. 
    governor_scd = ''

    # The unit control mode. Values are: "pulse", "setpoint"
    gen_control_mode = 'pulse'

    # Unit control error deadband. When a unit's desired active power change is less than this deadband, then no control pulses will be sent to the unit. 
    control_deadband = ''

 
    step_change = ''

    # The net rated maximum capacity determined by subtracting the auxiliary power used to operate the internal plant machinery from the rated gross maximum capacity 
    rated_net_max_p = ''

    # Generating unit economic participation factor 
    short_pf = 0.0

    # Operating mode for secondary control. Values are: "mrn", "manual", "reg", "lfc", "edc", "fixed", "agc", "off"
    gen_operating_mode = 'mrn'

    # Governor Motor Position Limit 
    governor_mpl = ''

    # Default Initial active power  which is used to store a powerflow result for the initial active power for this unit in this network configuration 
    initial_p = ''

 
    disp_reserve_flag = False

    # Unit response rate which specifies the active power change for a control pulse of one second in the most responsive loading level of the unit. 
    control_response_rate = ''

    # The planned unused capacity which can be used to support automatic control overruns. 
    auto_cntrl_margin_p = ''

    # The efficiency of the unit in converting mechanical energy, from the prime mover, into electrical energy. 
    efficiency = ''

    # The gross rated minimum generation level which the unit can safely operate at while delivering power to the transmission grid 
    rated_gross_min_p = ''

    # Detail level of the generator model data 
    model_detail = ''

 
    lower_ramp_rate = ''

    # Maximum high economic active power limit, that should not exceed the maximum operating active power limit 
    max_economic_p = ''

    # Pulse low limit which is the smallest control pulse that the unit can respond to 
    control_pulse_low = ''

    # Maximum allowable spinning reserve. Spinning reserve will never be considered greater than this value regardless of the current operating point. 
    maximum_allowable_spinning_reserve = ''

    # The unit's gross rated maximum capacity (Book Value). 
    rated_gross_max_p = ''

    # Generating unit economic participation factor 
    normal_pf = 0.0

    # The variable cost component of production per unit of ActivePower. 
    variable_cost = ''

    # The initial startup cost incurred for each start of the GeneratingUnit. 
    startup_cost = ''

    # Generating unit economic participation factor 
    long_pf = 0.0

    # The planned unused capacity (spinning reserve) which can be used to support emergency load 
    alloc_spin_res_p = ''

    # Generating unit economic participation factor 
    tie_line_pf = 0.0

 
    fast_start_flag = False

    # The nominal power of the generating unit.  Used to give precise meaning to percentage based attributes such as the govenor speed change droop (govenorSCD attribute). 
    nominal_p = ''

    # This is the maximum operating active power limit the dispatcher can enter for this unit 
    max_operating_p = ''

 
    fuel_priority = 0

 
    raise_ramp_rate = ''

    # Defined as: 1 / ( 1 - Incremental Transmission Loss); with the Incremental Transmission Loss expressed as a plus or minus value. The typical range of penalty factors is (0.9 to 1.1). 
    penalty_factor = 0.0

    registered_generator = None

    # ControlArea specifications for this generating unit.
    control_area_generating_unit = []

    # A GeneratingUnit injects energy into a SubControlArea.
    sub_control_area = None

    # A generating unit may have an operating schedule, indicating the planned operation of the unit
    gen_unit_op_schedule = None

    # A GenerationProvider operates one or more GeneratingUnits.
    operated_by_generation_provider = None

    # A synchronous machine may operate as a generator and as such becomes a member of a generating unit
    synchronous_machines = []

    # A generating unit may have a gross active power to net active power curve, describing the losses and auxiliary power requirements of the unit
    gross_to_net_active_power_curves = []

    # A generating unit may have one or more cost curves, depending upon fuel mixture and fuel cost.
    gen_unit_op_cost_curves = []

    # <<< generating_unit
    # @generated
    def __init__(self, gen_control_source='unavailable', startup_time='', min_operating_p='', high_control_limit='', minimum_off_time='', energy_min_p='', base_p='', min_economic_p='', spin_reserve_ramp='', control_pulse_high='', low_control_limit='', governor_scd='', gen_control_mode='pulse', control_deadband='', step_change='', rated_net_max_p='', short_pf=0.0, gen_operating_mode='mrn', governor_mpl='', initial_p='', disp_reserve_flag=False, control_response_rate='', auto_cntrl_margin_p='', efficiency='', rated_gross_min_p='', model_detail='', lower_ramp_rate='', max_economic_p='', control_pulse_low='', maximum_allowable_spinning_reserve='', rated_gross_max_p='', normal_pf=0.0, variable_cost='', startup_cost='', long_pf=0.0, alloc_spin_res_p='', tie_line_pf=0.0, fast_start_flag=False, nominal_p='', max_operating_p='', fuel_priority=0, raise_ramp_rate='', penalty_factor=0.0, registered_generator=None, control_area_generating_unit=[], sub_control_area=None, gen_unit_op_schedule=None, operated_by_generation_provider=None, synchronous_machines=[], gross_to_net_active_power_curves=[], gen_unit_op_cost_curves=[], **kw_args):
        """ Initialises a new 'GeneratingUnit' instance.
        """
        self.gen_control_source = gen_control_source
        self.startup_time = startup_time
        self.min_operating_p = min_operating_p
        self.high_control_limit = high_control_limit
        self.minimum_off_time = minimum_off_time
        self.energy_min_p = energy_min_p
        self.base_p = base_p
        self.min_economic_p = min_economic_p
        self.spin_reserve_ramp = spin_reserve_ramp
        self.control_pulse_high = control_pulse_high
        self.low_control_limit = low_control_limit
        self.governor_scd = governor_scd
        self.gen_control_mode = gen_control_mode
        self.control_deadband = control_deadband
        self.step_change = step_change
        self.rated_net_max_p = rated_net_max_p
        self.short_pf = short_pf
        self.gen_operating_mode = gen_operating_mode
        self.governor_mpl = governor_mpl
        self.initial_p = initial_p
        self.disp_reserve_flag = disp_reserve_flag
        self.control_response_rate = control_response_rate
        self.auto_cntrl_margin_p = auto_cntrl_margin_p
        self.efficiency = efficiency
        self.rated_gross_min_p = rated_gross_min_p
        self.model_detail = model_detail
        self.lower_ramp_rate = lower_ramp_rate
        self.max_economic_p = max_economic_p
        self.control_pulse_low = control_pulse_low
        self.maximum_allowable_spinning_reserve = maximum_allowable_spinning_reserve
        self.rated_gross_max_p = rated_gross_max_p
        self.normal_pf = normal_pf
        self.variable_cost = variable_cost
        self.startup_cost = startup_cost
        self.long_pf = long_pf
        self.alloc_spin_res_p = alloc_spin_res_p
        self.tie_line_pf = tie_line_pf
        self.fast_start_flag = fast_start_flag
        self.nominal_p = nominal_p
        self.max_operating_p = max_operating_p
        self.fuel_priority = fuel_priority
        self.raise_ramp_rate = raise_ramp_rate
        self.penalty_factor = penalty_factor
        self.registered_generator = registered_generator
        self.control_area_generating_unit = control_area_generating_unit
        self.sub_control_area = sub_control_area
        self.gen_unit_op_schedule = gen_unit_op_schedule
        self.operated_by_generation_provider = operated_by_generation_provider
        self.synchronous_machines = synchronous_machines
        self.gross_to_net_active_power_curves = gross_to_net_active_power_curves
        self.gen_unit_op_cost_curves = gen_unit_op_cost_curves

        super(GeneratingUnit, self).__init__(**kw_args)
    # >>> generating_unit


class FuelAllocationSchedule(Curve):
    """ The amount of fuel of a given type which is allocated for consumption over a specified period of time
    """
    # The minimum amount fuel that is allocated for consumption for the scheduled time period, e.g., based on a 'take-or-pay' contract 
    min_fuel_allocation = 0.0

    # The start time and date of the fuel allocation schedule 
    fuel_allocation_start_date = ''

    # The maximum amount fuel that is allocated for consumption for the scheduled time period 
    max_fuel_allocation = 0.0

    # The type of fuel, which also indicates the corresponding measurement unit Values are: "coal", "oil", "gas"
    fuel_type = 'coal'

    # The end time and date of the fuel allocation schedule 
    fuel_allocation_end_date = ''

    # A fuel allocation schedule must have a fossil fuel
    fossil_fuel = None

    # A thermal generating unit may have one or more fuel allocation schedules
    thermal_generating_unit = None

    # <<< fuel_allocation_schedule
    # @generated
    def __init__(self, min_fuel_allocation=0.0, fuel_allocation_start_date='', max_fuel_allocation=0.0, fuel_type='coal', fuel_allocation_end_date='', fossil_fuel=None, thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'FuelAllocationSchedule' instance.
        """
        self.min_fuel_allocation = min_fuel_allocation
        self.fuel_allocation_start_date = fuel_allocation_start_date
        self.max_fuel_allocation = max_fuel_allocation
        self.fuel_type = fuel_type
        self.fuel_allocation_end_date = fuel_allocation_end_date
        self.fossil_fuel = fossil_fuel
        self.thermal_generating_unit = thermal_generating_unit

        super(FuelAllocationSchedule, self).__init__(**kw_args)
    # >>> fuel_allocation_schedule


class CAESPlant(PowerSystemResource):
    """ Compressed air energy storage plant
    """
    # The rated energy storage capacity. 
    energy_storage_capacity = ''

    # The CAES plant's gross rated generating capacity 
    rated_capacity_p = ''

    # A thermal generating unit may be a member of a compressed air energy storage plant
    thermal_generating_unit = None

    # An air compressor may be a member of a compressed air energy storage plant
    air_compressor = None

    # <<< caesplant
    # @generated
    def __init__(self, energy_storage_capacity='', rated_capacity_p='', thermal_generating_unit=None, air_compressor=None, **kw_args):
        """ Initialises a new 'CAESPlant' instance.
        """
        self.energy_storage_capacity = energy_storage_capacity
        self.rated_capacity_p = rated_capacity_p
        self.thermal_generating_unit = thermal_generating_unit
        self.air_compressor = air_compressor

        super(CAESPlant, self).__init__(**kw_args)
    # >>> caesplant


class TargetLevelSchedule(Curve):
    """ Reservoir water level targets from advanced studies or 'rule curves'. Typically in one hour increments for up to 10 days
    """
    # Low target level limit, below which the reservoir operation will be penalized 
    low_level_limit = ''

    # High target level limit, above which the reservoir operation will be penalized 
    high_level_limit = ''

    # A reservoir may have a water level target schedule.
    reservoir = None

    # <<< target_level_schedule
    # @generated
    def __init__(self, low_level_limit='', high_level_limit='', reservoir=None, **kw_args):
        """ Initialises a new 'TargetLevelSchedule' instance.
        """
        self.low_level_limit = low_level_limit
        self.high_level_limit = high_level_limit
        self.reservoir = reservoir

        super(TargetLevelSchedule, self).__init__(**kw_args)
    # >>> target_level_schedule


class HeatRateCurve(Curve):
    """ Relationship between unit heat rate per active power (Y-axis) and  unit output (X-axis). The heat input is from all fuels.
    """
    # Flag is set to true when output is expressed in net active power 
    is_net_gross_p = False

    # A thermal generating unit may have a heat rate curve
    thermal_generating_unit = None

    # <<< heat_rate_curve
    # @generated
    def __init__(self, is_net_gross_p=False, thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'HeatRateCurve' instance.
        """
        self.is_net_gross_p = is_net_gross_p
        self.thermal_generating_unit = thermal_generating_unit

        super(HeatRateCurve, self).__init__(**kw_args)
    # >>> heat_rate_curve


class GenUnitOpCostCurve(Curve):
    """ Relationship between unit operating cost (Y-axis) and unit output active power (X-axis). The operating cost curve for thermal units is derived from heat input and fuel costs. The operating cost curve for hydro units is derived from water flow rates and equivalent water costs.
    """
    # Flag is set to true when output is expressed in net active power 
    is_net_gross_p = False

    # A generating unit may have one or more cost curves, depending upon fuel mixture and fuel cost.
    generating_unit = None

    # <<< gen_unit_op_cost_curve
    # @generated
    def __init__(self, is_net_gross_p=False, generating_unit=None, **kw_args):
        """ Initialises a new 'GenUnitOpCostCurve' instance.
        """
        self.is_net_gross_p = is_net_gross_p
        self.generating_unit = generating_unit

        super(GenUnitOpCostCurve, self).__init__(**kw_args)
    # >>> gen_unit_op_cost_curve


class StartupModel(IdentifiedObject):
    """ Unit start up characteristics depending on how long the unit has been off line
    """
    # Startup priority within control area where lower numbers indicate higher priorities.  More than one unit in an area may be assigned the same priority. 
    startup_priority = 0

    # Total miscellaneous start up costs 
    startup_cost = ''

    # The amount of heat input per time uint required for hot standby operation 
    hot_standby_heat = ''

    # The unit's auxiliary active power consumption to maintain standby mode 
    stby_aux_p = ''

    # The opportunity cost associated with the return in monetary unit. This represents the restart's 'share' of the unit depreciation and risk of an event which would damage the unit. 
    risk_factor_cost = ''

    # The date and time of the most recent generating unit startup 
    startup_date = ''

    # The minimum number of hours the unit must be down before restart 
    minimum_down_time = ''

    # Fixed Maintenance Cost 
    fixed_maint_cost = ''

    # Incremental Maintenance Cost 
    incremental_maint_cost = ''

    # The minimum number of hours the unit must be operating before being allowed to shut down 
    minimum_run_time = ''

    # The unit's startup model may have a startup ignition fuel curve
    start_ign_fuel_curve = None

    # The unit's startup model may have a startup main fuel curve
    start_main_fuel_curve = None

    # A thermal generating unit may have a startup model
    thermal_generating_unit = None

    # The unit's startup model may have a startup ramp curve
    start_ramp_curve = None

    # <<< startup_model
    # @generated
    def __init__(self, startup_priority=0, startup_cost='', hot_standby_heat='', stby_aux_p='', risk_factor_cost='', startup_date='', minimum_down_time='', fixed_maint_cost='', incremental_maint_cost='', minimum_run_time='', start_ign_fuel_curve=None, start_main_fuel_curve=None, thermal_generating_unit=None, start_ramp_curve=None, **kw_args):
        """ Initialises a new 'StartupModel' instance.
        """
        self.startup_priority = startup_priority
        self.startup_cost = startup_cost
        self.hot_standby_heat = hot_standby_heat
        self.stby_aux_p = stby_aux_p
        self.risk_factor_cost = risk_factor_cost
        self.startup_date = startup_date
        self.minimum_down_time = minimum_down_time
        self.fixed_maint_cost = fixed_maint_cost
        self.incremental_maint_cost = incremental_maint_cost
        self.minimum_run_time = minimum_run_time
        self.start_ign_fuel_curve = start_ign_fuel_curve
        self.start_main_fuel_curve = start_main_fuel_curve
        self.thermal_generating_unit = thermal_generating_unit
        self.start_ramp_curve = start_ramp_curve

        super(StartupModel, self).__init__(**kw_args)
    # >>> startup_model


class FossilFuel(IdentifiedObject):
    """ The fossil fuel consumed by the non-nuclear thermal generating units, e.g., coal, oil, gas
    """
    # The fuel's fraction of pollution credit per unit of heat content 
    fuel_sulfur = ''

    # Handling and processing cost associated with this fuel 
    fuel_handling_cost = ''

    # The efficiency factor for the fuel (per unit) in terms of the effective energy absorbed 
    fuel_eff_factor = ''

    # The cost in terms of heat value for the given type of fuel 
    fuel_cost = ''

    # Relative amount of the given type of fuel, when multiple fuels are being consumed. 
    fuel_mixture = ''

    # The type of fossil fuel, such as coal, oil, or gas. Values are: "coal", "oil", "gas"
    fossil_fuel_type = 'coal'

    # The active power output level of the unit at which the given type of fuel is switched off. This fuel (e.g., oil) is sometimes used to stabilize the base fuel (e.g., coal) at low active power output levels. 
    low_breakpoint_p = ''

    # The amount of heat per weight (or volume) of the given type of fuel 
    fuel_heat_content = 0.0

    # The active power output level of the unit at which the given type of fuel is switched on. This fuel (e.g., oil) is sometimes used to supplement the base fuel (e.g., coal) at high active power output levels. 
    high_breakpoint_p = ''

    # The cost of fuel used for economic dispatching which includes: fuel cost, transportation cost,  and incremental maintenance cost 
    fuel_dispatch_cost = ''

    # A fuel allocation schedule must have a fossil fuel
    fuel_allocation_schedules = []

    # A thermal generating unit may have one or more fossil fuels
    thermal_generating_unit = None

    # <<< fossil_fuel
    # @generated
    def __init__(self, fuel_sulfur='', fuel_handling_cost='', fuel_eff_factor='', fuel_cost='', fuel_mixture='', fossil_fuel_type='coal', low_breakpoint_p='', fuel_heat_content=0.0, high_breakpoint_p='', fuel_dispatch_cost='', fuel_allocation_schedules=[], thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'FossilFuel' instance.
        """
        self.fuel_sulfur = fuel_sulfur
        self.fuel_handling_cost = fuel_handling_cost
        self.fuel_eff_factor = fuel_eff_factor
        self.fuel_cost = fuel_cost
        self.fuel_mixture = fuel_mixture
        self.fossil_fuel_type = fossil_fuel_type
        self.low_breakpoint_p = low_breakpoint_p
        self.fuel_heat_content = fuel_heat_content
        self.high_breakpoint_p = high_breakpoint_p
        self.fuel_dispatch_cost = fuel_dispatch_cost
        self.fuel_allocation_schedules = fuel_allocation_schedules
        self.thermal_generating_unit = thermal_generating_unit

        super(FossilFuel, self).__init__(**kw_args)
    # >>> fossil_fuel


class StartMainFuelCurve(Curve):
    """ The quantity of main fuel (Y-axis) used to restart and repay the auxiliary power consumed versus the number of hours (X-axis) the unit was off line
    """
    # Type of main fuel Values are: "coal", "oil", "gas"
    main_fuel_type = 'coal'

    # The unit's startup model may have a startup main fuel curve
    startup_model = None

    # <<< start_main_fuel_curve
    # @generated
    def __init__(self, main_fuel_type='coal', startup_model=None, **kw_args):
        """ Initialises a new 'StartMainFuelCurve' instance.
        """
        self.main_fuel_type = main_fuel_type
        self.startup_model = startup_model

        super(StartMainFuelCurve, self).__init__(**kw_args)
    # >>> start_main_fuel_curve


class CogenerationPlant(PowerSystemResource):
    """ A set of thermal generating units for the production of electrical energy and process steam (usually from the output of the steam turbines). The steam sendout is typically used for industrial purposes or for municipal heating and cooling.
    """
    # The low pressure steam sendout 
    cogen_lpsendout_rating = 0.0

    # The high pressure steam rating 
    cogen_hpsteam_rating = 0.0

    # The rated output active power of the cogeneration plant 
    rated_p = ''

    # The high pressure steam sendout 
    cogen_hpsendout_rating = 0.0

    # The low pressure steam rating 
    cogen_lpsteam_rating = 0.0

    # A cogeneration plant has a steam sendout schedule
    steam_sendout_schedule = None

    # A thermal generating unit may be a member of a cogeneration plant
    thermal_generating_units = []

    # <<< cogeneration_plant
    # @generated
    def __init__(self, cogen_lpsendout_rating=0.0, cogen_hpsteam_rating=0.0, rated_p='', cogen_hpsendout_rating=0.0, cogen_lpsteam_rating=0.0, steam_sendout_schedule=None, thermal_generating_units=[], **kw_args):
        """ Initialises a new 'CogenerationPlant' instance.
        """
        self.cogen_lpsendout_rating = cogen_lpsendout_rating
        self.cogen_hpsteam_rating = cogen_hpsteam_rating
        self.rated_p = rated_p
        self.cogen_hpsendout_rating = cogen_hpsendout_rating
        self.cogen_lpsteam_rating = cogen_lpsteam_rating
        self.steam_sendout_schedule = steam_sendout_schedule
        self.thermal_generating_units = thermal_generating_units

        super(CogenerationPlant, self).__init__(**kw_args)
    # >>> cogeneration_plant


class HydroGeneratingEfficiencyCurve(Curve):
    """ Relationship between unit efficiency in percent and unit output active power for a given net head in meters. The relationship between efficiency, discharge, head, and power output is expressed as follows:   E =KP/HQ Where:  (E=percentage)  (P=active power)  (H=height)  (Q=volume/time unit)  (K=constant) For example, a curve instance for a given net head could relate efficiency (Y-axis) versus active power output (X-axis) or versus discharge on the X-axis.
    """
    # A hydro generating unit has an efficiency curve
    hydro_generating_unit = None

    # <<< hydro_generating_efficiency_curve
    # @generated
    def __init__(self, hydro_generating_unit=None, **kw_args):
        """ Initialises a new 'HydroGeneratingEfficiencyCurve' instance.
        """
        self.hydro_generating_unit = hydro_generating_unit

        super(HydroGeneratingEfficiencyCurve, self).__init__(**kw_args)
    # >>> hydro_generating_efficiency_curve


class GenUnitOpSchedule(RegularIntervalSchedule):
    """ The generating unit's Operator-approved current operating schedule (or plan), typically produced with the aid of unit commitment type analyses. The X-axis represents absolute time. The Y1-axis represents the status (0=off-line and unavailable: 1=available: 2=must run: 3=must run at fixed power value: etc.). The Y2-axis represents the must run fixed power value where required.
    """
    # A generating unit may have an operating schedule, indicating the planned operation of the unit
    generating_unit = None

    # <<< gen_unit_op_schedule
    # @generated
    def __init__(self, generating_unit=None, **kw_args):
        """ Initialises a new 'GenUnitOpSchedule' instance.
        """
        self.generating_unit = generating_unit

        super(GenUnitOpSchedule, self).__init__(**kw_args)
    # >>> gen_unit_op_schedule


class PenstockLossCurve(Curve):
    """ Relationship between penstock head loss (in meters) and  total discharge through the penstock (in cubic meters per second). One or more turbines may be connected to the same penstock.
    """
    # A hydro generating unit has a penstock loss curve
    hydro_generating_unit = None

    # <<< penstock_loss_curve
    # @generated
    def __init__(self, hydro_generating_unit=None, **kw_args):
        """ Initialises a new 'PenstockLossCurve' instance.
        """
        self.hydro_generating_unit = hydro_generating_unit

        super(PenstockLossCurve, self).__init__(**kw_args)
    # >>> penstock_loss_curve


class HeatInputCurve(Curve):
    """ Relationship between unit heat input in energy per time for main fuel (Y1-axis) and supplemental fuel (Y2-axis) versus unit output in active power (X-axis). The quantity of main fuel used to sustain generation at this output level is prorated for throttling between definition points. The quantity of supplemental fuel used at this output level is fixed and not prorated.
    """
    # Power output - auxiliary power multiplier adjustment factor. 
    aux_power_mult = ''

    # Heat input - offset adjustment factor. 
    heat_input_offset = ''

    # Flag is set to true when output is expressed in net active power 
    is_net_gross_p = False

    # Heat input - efficiency multiplier adjustment factor. 
    heat_input_eff = ''

    # Power output - auxiliary power offset adjustment factor 
    aux_power_offset = ''

    # A thermal generating unit may have a heat input curve
    thermal_generating_unit = None

    # <<< heat_input_curve
    # @generated
    def __init__(self, aux_power_mult='', heat_input_offset='', is_net_gross_p=False, heat_input_eff='', aux_power_offset='', thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'HeatInputCurve' instance.
        """
        self.aux_power_mult = aux_power_mult
        self.heat_input_offset = heat_input_offset
        self.is_net_gross_p = is_net_gross_p
        self.heat_input_eff = heat_input_eff
        self.aux_power_offset = aux_power_offset
        self.thermal_generating_unit = thermal_generating_unit

        super(HeatInputCurve, self).__init__(**kw_args)
    # >>> heat_input_curve


class StartIgnFuelCurve(Curve):
    """ The quantity of ignition fuel (Y-axis) used to restart and repay the auxiliary power consumed versus the number of hours (X-axis) the unit was off line
    """
    # Type of ignition fuel Values are: "coal", "oil", "gas"
    ignition_fuel_type = 'coal'

    # The unit's startup model may have a startup ignition fuel curve
    startup_model = None

    # <<< start_ign_fuel_curve
    # @generated
    def __init__(self, ignition_fuel_type='coal', startup_model=None, **kw_args):
        """ Initialises a new 'StartIgnFuelCurve' instance.
        """
        self.ignition_fuel_type = ignition_fuel_type
        self.startup_model = startup_model

        super(StartIgnFuelCurve, self).__init__(**kw_args)
    # >>> start_ign_fuel_curve


class TailbayLossCurve(Curve):
    """ Relationship between tailbay head loss hight (y-axis) and the total discharge into the power station's tailbay volume per time unit (x-axis) . There could be more than one curve depending on the level of the tailbay reservoir or river level
    """
    # A hydro generating unit has a tailbay loss curve
    hydro_generating_unit = None

    # <<< tailbay_loss_curve
    # @generated
    def __init__(self, hydro_generating_unit=None, **kw_args):
        """ Initialises a new 'TailbayLossCurve' instance.
        """
        self.hydro_generating_unit = hydro_generating_unit

        super(TailbayLossCurve, self).__init__(**kw_args)
    # >>> tailbay_loss_curve


class StartRampCurve(Curve):
    """ Rate in gross active power/minute (Y-axis) at which a unit can be loaded versus the number of hours (X-axis) the unit was off line
    """
    # The startup ramp rate in gross for a unit that is on hot standby 
    hot_standby_ramp = ''

    # The unit's startup model may have a startup ramp curve
    startup_model = None

    # <<< start_ramp_curve
    # @generated
    def __init__(self, hot_standby_ramp='', startup_model=None, **kw_args):
        """ Initialises a new 'StartRampCurve' instance.
        """
        self.hot_standby_ramp = hot_standby_ramp
        self.startup_model = startup_model

        super(StartRampCurve, self).__init__(**kw_args)
    # >>> start_ramp_curve


class IncrementalHeatRateCurve(Curve):
    """ Relationship between unit incremental heat rate in (delta energy/time) per (delta active power) and unit output in active power. The IHR curve represents the slope of the HeatInputCurve. Note that the 'incremental heat rate' and the 'heat rate' have the same engineering units.
    """
    # Flag is set to true when output is expressed in net active power 
    is_net_gross_p = False

    # A thermal generating unit may have an incremental heat rate curve
    thermal_generating_unit = None

    # <<< incremental_heat_rate_curve
    # @generated
    def __init__(self, is_net_gross_p=False, thermal_generating_unit=None, **kw_args):
        """ Initialises a new 'IncrementalHeatRateCurve' instance.
        """
        self.is_net_gross_p = is_net_gross_p
        self.thermal_generating_unit = thermal_generating_unit

        super(IncrementalHeatRateCurve, self).__init__(**kw_args)
    # >>> incremental_heat_rate_curve


class CombinedCyclePlant(PowerSystemResource):
    """ A set of combustion turbines and steam turbines where the exhaust heat from the combustion turbines is recovered to make steam for the steam turbines, resulting in greater overall plant efficiency
    """
    # The combined cycle plant's active power output rating 
    comb_cycle_plant_rating = ''

    # A thermal generating unit may be a member of a combined cycle plant
    thermal_generating_units = []

    # <<< combined_cycle_plant
    # @generated
    def __init__(self, comb_cycle_plant_rating='', thermal_generating_units=[], **kw_args):
        """ Initialises a new 'CombinedCyclePlant' instance.
        """
        self.comb_cycle_plant_rating = comb_cycle_plant_rating
        self.thermal_generating_units = thermal_generating_units

        super(CombinedCyclePlant, self).__init__(**kw_args)
    # >>> combined_cycle_plant


class InflowForecast(RegularIntervalSchedule):
    """ Natural water inflow to a reservoir, usually forecasted from predicted rain and snowmelt. Typically in one hour increments for up to 10 days. The forecast is given in average cubic meters per second over the time increment.
    """
    # A reservoir may have a 'natural' inflow forecast.
    reservoir = None

    # <<< inflow_forecast
    # @generated
    def __init__(self, reservoir=None, **kw_args):
        """ Initialises a new 'InflowForecast' instance.
        """
        self.reservoir = reservoir

        super(InflowForecast, self).__init__(**kw_args)
    # >>> inflow_forecast


class SteamSendoutSchedule(RegularIntervalSchedule):
    """ The cogeneration plant's steam sendout schedule in volume per time unit.
    """
    # A cogeneration plant has a steam sendout schedule
    cogeneration_plant = None

    # <<< steam_sendout_schedule
    # @generated
    def __init__(self, cogeneration_plant=None, **kw_args):
        """ Initialises a new 'SteamSendoutSchedule' instance.
        """
        self.cogeneration_plant = cogeneration_plant

        super(SteamSendoutSchedule, self).__init__(**kw_args)
    # >>> steam_sendout_schedule


class HydroPowerPlant(PowerSystemResource):
    """ A hydro power station which can generate or pump. When generating, the generator turbines receive there water from an upper reservoir. When pumping, the pumps receive their water from a lower reservoir.
    """
    # The hydro plant's generating rating active power for rated head conditions 
    gen_rated_p = ''

    # The plant's rated gross head 
    plant_rated_head = ''

    # Water travel delay from tailbay to next downstream hydro power station 
    discharge_travel_delay = ''

    # The level at which the surge tank spills 
    surge_tank_crest_level = ''

    # Type and configuration of hydro plant penstock(s) 
    penstock_type = ''

    # The type of hydro power plant. Values are: "major_storage", "run_of_river", "minor_storage", "pumped_storage"
    hydro_plant_type = 'major_storage'

    # Total plant discharge capacity in cubic meters per second 
    plant_discharge_capacity = 0.0

    # The hydro plant's pumping rating active power for rated head conditions 
    pump_rated_p = ''

    # A code describing the type (or absence) of surge tank that is associated with the hydro power plant 
    surge_tank_code = ''

    # Generators discharge water to or pumps are supplied water from a downstream reservoir
    reservoir = None

    # The hydro pump may be a member of a pumped storage plant or a pump for distributing water
    hydro_pumps = []

    # Generators are supplied water from or pumps discharge water to an upstream reservoir
    gen_source_pump_discharge_reservoir = None

    # The hydro generating unit belongs to a hydro power plant
    hydro_generating_units = []

    # <<< hydro_power_plant
    # @generated
    def __init__(self, gen_rated_p='', plant_rated_head='', discharge_travel_delay='', surge_tank_crest_level='', penstock_type='', hydro_plant_type='major_storage', plant_discharge_capacity=0.0, pump_rated_p='', surge_tank_code='', reservoir=None, hydro_pumps=[], gen_source_pump_discharge_reservoir=None, hydro_generating_units=[], **kw_args):
        """ Initialises a new 'HydroPowerPlant' instance.
        """
        self.gen_rated_p = gen_rated_p
        self.plant_rated_head = plant_rated_head
        self.discharge_travel_delay = discharge_travel_delay
        self.surge_tank_crest_level = surge_tank_crest_level
        self.penstock_type = penstock_type
        self.hydro_plant_type = hydro_plant_type
        self.plant_discharge_capacity = plant_discharge_capacity
        self.pump_rated_p = pump_rated_p
        self.surge_tank_code = surge_tank_code
        self.reservoir = reservoir
        self.hydro_pumps = hydro_pumps
        self.gen_source_pump_discharge_reservoir = gen_source_pump_discharge_reservoir
        self.hydro_generating_units = hydro_generating_units

        super(HydroPowerPlant, self).__init__(**kw_args)
    # >>> hydro_power_plant


class HydroPumpOpSchedule(RegularIntervalSchedule):
    """ The hydro pump's Operator-approved current operating schedule (or plan), typically produced with the aid of unit commitment type analyses.The unit's operating schedule status is typically given as: (0=unavailable)  (1=avilable to startup or shutdown)  (2=must pump)
    """
    # The hydro pump has a pumping schedule over time, indicating when pumping is to occur.
    hydro_pump = None

    # <<< hydro_pump_op_schedule
    # @generated
    def __init__(self, hydro_pump=None, **kw_args):
        """ Initialises a new 'HydroPumpOpSchedule' instance.
        """
        self.hydro_pump = hydro_pump

        super(HydroPumpOpSchedule, self).__init__(**kw_args)
    # >>> hydro_pump_op_schedule


class ThermalGeneratingUnit(GeneratingUnit):
    """ A generating unit whose prime mover could be a steam turbine, combustion turbine, or diesel engine.
    """
    # Operating and maintenance cost for the thermal unit 
    o_mcost = ''

    # A thermal generating unit may have a heat input curve
    heat_input_curve = None

    # A thermal generating unit may have  one or more emission curves
    emission_curves = []

    # A thermal generating unit may have an incremental heat rate curve
    incremental_heat_rate_curve = None

    # A thermal generating unit may have one or more fuel allocation schedules
    fuel_allocation_schedules = []

    # A thermal generating unit may be a member of a cogeneration plant
    cogeneration_plant = None

    # A thermal generating unit may have a startup model
    startup_model = None

    # A thermal generating unit may have one or more fossil fuels
    fossil_fuels = []

    # A thermal generating unit may be a member of a combined cycle plant
    combined_cycle_plant = None

    # A thermal generating unit may be a member of a compressed air energy storage plant
    caesplant = None

    # A thermal generating unit may have a heat rate curve
    heat_rate_curve = None

    # A thermal generating unit may have one or more emission allowance accounts
    emmission_accounts = []

    # A thermal generating unit may have a shutdown curve
    shutdown_curve = None

    # <<< thermal_generating_unit
    # @generated
    def __init__(self, o_mcost='', heat_input_curve=None, emission_curves=[], incremental_heat_rate_curve=None, fuel_allocation_schedules=[], cogeneration_plant=None, startup_model=None, fossil_fuels=[], combined_cycle_plant=None, caesplant=None, heat_rate_curve=None, emmission_accounts=[], shutdown_curve=None, **kw_args):
        """ Initialises a new 'ThermalGeneratingUnit' instance.
        """
        self.o_mcost = o_mcost
        self.heat_input_curve = heat_input_curve
        self.emission_curves = emission_curves
        self.incremental_heat_rate_curve = incremental_heat_rate_curve
        self.fuel_allocation_schedules = fuel_allocation_schedules
        self.cogeneration_plant = cogeneration_plant
        self.startup_model = startup_model
        self.fossil_fuels = fossil_fuels
        self.combined_cycle_plant = combined_cycle_plant
        self.caesplant = caesplant
        self.heat_rate_curve = heat_rate_curve
        self.emmission_accounts = emmission_accounts
        self.shutdown_curve = shutdown_curve

        super(ThermalGeneratingUnit, self).__init__(**kw_args)
    # >>> thermal_generating_unit


class HydroGeneratingUnit(GeneratingUnit):
    """ A generating unit whose prime mover is a hydraulic turbine (e.g., Francis, Pelton, Kaplan)
    """
    # The equivalent cost of water that drives the hydro turbine, expressed as cost per volume. 
    hydro_unit_water_cost = 0.0

    # Energy conversion capability for generating. Values are: "generator", "pump_and_generator"
    energy_conversion_capability = 'generator'

    # A hydro generating unit has a penstock loss curve
    penstock_loss_curve = None

    # A hydro generating unit has a tailbay loss curve
    tailbay_loss_curve = []

    # The hydro generating unit belongs to a hydro power plant
    hydro_power_plant = None

    # A hydro generating unit has an efficiency curve
    hydro_generating_efficiency_curves = []

    # <<< hydro_generating_unit
    # @generated
    def __init__(self, hydro_unit_water_cost=0.0, energy_conversion_capability='generator', penstock_loss_curve=None, tailbay_loss_curve=[], hydro_power_plant=None, hydro_generating_efficiency_curves=[], **kw_args):
        """ Initialises a new 'HydroGeneratingUnit' instance.
        """
        self.hydro_unit_water_cost = hydro_unit_water_cost
        self.energy_conversion_capability = energy_conversion_capability
        self.penstock_loss_curve = penstock_loss_curve
        self.tailbay_loss_curve = tailbay_loss_curve
        self.hydro_power_plant = hydro_power_plant
        self.hydro_generating_efficiency_curves = hydro_generating_efficiency_curves

        super(HydroGeneratingUnit, self).__init__(**kw_args)
    # >>> hydro_generating_unit


class WindGeneratingUnit(GeneratingUnit):
    """ A wind driven generating unit.
    """
    pass
    # <<< wind_generating_unit
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'WindGeneratingUnit' instance.
        """

        super(WindGeneratingUnit, self).__init__(**kw_args)
    # >>> wind_generating_unit


class NuclearGeneratingUnit(GeneratingUnit):
    """ A nuclear generating unit.
    """
    pass
    # <<< nuclear_generating_unit
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'NuclearGeneratingUnit' instance.
        """

        super(NuclearGeneratingUnit, self).__init__(**kw_args)
    # >>> nuclear_generating_unit


# <<< production
# @generated
# >>> production

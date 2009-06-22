# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim.core import Curve
from cim.core import PowerSystemResource

# <<< imports
# @generated
# >>> imports

class CTTempActivePowerCurve(Curve):
    """ Relationship between the combustion turbine's power output rating in gross active power (X-axis) and the ambient air temperature (Y-axis)
    """
    # A combustion turbine may have a active power versus ambient temperature relationship
    combustion_turbine = None

    # <<< cttemp_active_power_curve
    # @generated
    def __init__(self, combustion_turbine=None, **kw_args):
        """ Initialises a new 'CTTempActivePowerCurve' instance.
        """
        self.combustion_turbine = combustion_turbine

        super(CTTempActivePowerCurve, self).__init__(**kw_args)
    # >>> cttemp_active_power_curve


class PrimeMover(PowerSystemResource):
    """ The machine used to develop mechanical energy used to drive a generator.
    """
    # Rating of prime mover 
    prime_mover_rating = 0.0

    drives_synchronous_machines = []

    # <<< prime_mover
    # @generated
    def __init__(self, prime_mover_rating=0.0, drives_synchronous_machines=[], **kw_args):
        """ Initialises a new 'PrimeMover' instance.
        """
        self.prime_mover_rating = prime_mover_rating
        self.drives_synchronous_machines = drives_synchronous_machines

        super(PrimeMover, self).__init__(**kw_args)
    # >>> prime_mover


class SteamSupply(PowerSystemResource):
    """ Steam supply for steam turbine
    """
    # Rating of steam supply 
    steam_supply_rating = 0.0

    # Steam turbines may have steam supplied by a steam supply
    steam_turbines = []

    # <<< steam_supply
    # @generated
    def __init__(self, steam_supply_rating=0.0, steam_turbines=[], **kw_args):
        """ Initialises a new 'SteamSupply' instance.
        """
        self.steam_supply_rating = steam_supply_rating
        self.steam_turbines = steam_turbines

        super(SteamSupply, self).__init__(**kw_args)
    # >>> steam_supply


class SteamTurbine(PrimeMover):
    """ Steam turbine
    """
    # Fraction Of Power From Shaft 2 Intermediate Pressure Turbine output 
    shaft2_power_ip = 0.0

    # Fraction Of Power From Shaft 2 First Low Pressure Turbine output 
    shaft2_power_lp1 = 0.0

    # Fraction Of Power From Shaft 2 Second Low Pressure Turbine output 
    shaft2_power_lp2 = 0.0

    # Fraction Of Power From Shaft 2 High Pressure Turbine output 
    shaft2_power_hp = 0.0

    # Second Reheater Time Constant 
    reheater2_tc = 0.0

    # Fraction Of Power From Shaft 1 Intermediate Pressure Turbine output 
    shaft1_power_ip = 0.0

    # Steam Chest Time Constant 
    steam_chest_tc = 0.0

    # Fraction Of Power From Shaft 1 First Low Pressure Turbine output 
    shaft1_power_lp1 = 0.0

    # Crossover Time Constant 
    crossover_tc = 0.0

    # First Reheater Time Constant 
    reheater1_tc = 0.0

    # Fraction Of Power From Shaft 1 High Pressure Turbine output 
    shaft1_power_hp = 0.0

    # Fraction Of Power From Shaft 1 Second Low Pressure Turbine output 
    shaft1_power_lp2 = 0.0

    # Steam turbines may have steam supplied by a steam supply
    steam_supplys = []

    # <<< steam_turbine
    # @generated
    def __init__(self, shaft2_power_ip=0.0, shaft2_power_lp1=0.0, shaft2_power_lp2=0.0, shaft2_power_hp=0.0, reheater2_tc=0.0, shaft1_power_ip=0.0, steam_chest_tc=0.0, shaft1_power_lp1=0.0, crossover_tc=0.0, reheater1_tc=0.0, shaft1_power_hp=0.0, shaft1_power_lp2=0.0, steam_supplys=[], **kw_args):
        """ Initialises a new 'SteamTurbine' instance.
        """
        self.shaft2_power_ip = shaft2_power_ip
        self.shaft2_power_lp1 = shaft2_power_lp1
        self.shaft2_power_lp2 = shaft2_power_lp2
        self.shaft2_power_hp = shaft2_power_hp
        self.reheater2_tc = reheater2_tc
        self.shaft1_power_ip = shaft1_power_ip
        self.steam_chest_tc = steam_chest_tc
        self.shaft1_power_lp1 = shaft1_power_lp1
        self.crossover_tc = crossover_tc
        self.reheater1_tc = reheater1_tc
        self.shaft1_power_hp = shaft1_power_hp
        self.shaft1_power_lp2 = shaft1_power_lp2
        self.steam_supplys = steam_supplys

        super(SteamTurbine, self).__init__(**kw_args)
    # >>> steam_turbine


class PWRSteamSupply(SteamSupply):
    """ Pressurized water reactor used as a steam supply to a steam turbine
    """
    # Cold Leg Feedback Gain 2 
    cold_leg_fg2 = 0.0

    # Steam Pressure Drop Lag Time Constant 
    steam_pressure_drop_lag_tc = 0.0

    # Cold Leg Feedback Lead Time Constant 
    cold_leg_fblead_tc2 = 0.0

    # Cold Leg Feedback Lag Time Constant 
    cold_leg_fblag_tc = 0.0

    # Hot Leg To Cold Leg Gain 
    hot_leg_to_cold_leg_gain = 0.0

    # Feedback Factor 
    feedback_factor = 0.0

    # Core Neutronics And Heat Transfer 
    core_neutronics_ht = 0.0

    # Throttle Pressure Factor 
    throttle_pressure_factor = 0.0

    # Core Heat Transfer Lag Time Constant 
    core_htlag_tc1 = 0.0

    # Throttle Pressure Setpoint 
    throttle_pressure_sp = 0.0

    # Core Neutronics Effective Time Constant 
    core_neutronics_eff_tc = 0.0

    # Cold Leg Feedback Gain 1 
    cold_leg_fg1 = 0.0

    # Steam Pressure Feedback Gain 
    steam_pressure_fg = 0.0

    # Hot Leg Steam Gain 
    hot_leg_steam_gain = 0.0

    # Core Heat Transfer Lag Time Constant 
    core_htlag_tc2 = 0.0

    # Cold Leg Lag Time Constant 
    cold_leg_lag_tc = 0.0

    # Pressure Control Gain 
    pressure_cg = 0.0

    # Cold Leg Feedback Lead Time Constant 
    cold_leg_fblead_tc1 = 0.0

    # Steam Flow Feedback Gain 
    steam_flow_fg = 0.0

    # Hot Leg Lag Time Constant 
    hot_leg_lag_tc = 0.0

    # <<< pwrsteam_supply
    # @generated
    def __init__(self, cold_leg_fg2=0.0, steam_pressure_drop_lag_tc=0.0, cold_leg_fblead_tc2=0.0, cold_leg_fblag_tc=0.0, hot_leg_to_cold_leg_gain=0.0, feedback_factor=0.0, core_neutronics_ht=0.0, throttle_pressure_factor=0.0, core_htlag_tc1=0.0, throttle_pressure_sp=0.0, core_neutronics_eff_tc=0.0, cold_leg_fg1=0.0, steam_pressure_fg=0.0, hot_leg_steam_gain=0.0, core_htlag_tc2=0.0, cold_leg_lag_tc=0.0, pressure_cg=0.0, cold_leg_fblead_tc1=0.0, steam_flow_fg=0.0, hot_leg_lag_tc=0.0, **kw_args):
        """ Initialises a new 'PWRSteamSupply' instance.
        """
        self.cold_leg_fg2 = cold_leg_fg2
        self.steam_pressure_drop_lag_tc = steam_pressure_drop_lag_tc
        self.cold_leg_fblead_tc2 = cold_leg_fblead_tc2
        self.cold_leg_fblag_tc = cold_leg_fblag_tc
        self.hot_leg_to_cold_leg_gain = hot_leg_to_cold_leg_gain
        self.feedback_factor = feedback_factor
        self.core_neutronics_ht = core_neutronics_ht
        self.throttle_pressure_factor = throttle_pressure_factor
        self.core_htlag_tc1 = core_htlag_tc1
        self.throttle_pressure_sp = throttle_pressure_sp
        self.core_neutronics_eff_tc = core_neutronics_eff_tc
        self.cold_leg_fg1 = cold_leg_fg1
        self.steam_pressure_fg = steam_pressure_fg
        self.hot_leg_steam_gain = hot_leg_steam_gain
        self.core_htlag_tc2 = core_htlag_tc2
        self.cold_leg_lag_tc = cold_leg_lag_tc
        self.pressure_cg = pressure_cg
        self.cold_leg_fblead_tc1 = cold_leg_fblead_tc1
        self.steam_flow_fg = steam_flow_fg
        self.hot_leg_lag_tc = hot_leg_lag_tc

        super(PWRSteamSupply, self).__init__(**kw_args)
    # >>> pwrsteam_supply


class CombustionTurbine(PrimeMover):
    """ A prime mover that is typically fueled by gas or light oil
    """
    # Reference temperature at which the output of the turbine was defined. 
    reference_temp = 0.0

    # Per unit change in power per (versus) unit change in ambient temperature 
    power_variation_by_temp = 0.0

    # Flag that is set to true if the combustion turbine is associated with a heat recovery boiler 
    heat_recovery_flag = False

    # Off-nominal voltage effect on turbine auxiliaries. Per unit reduction in auxiliary active power consumption versus per unit reduction in auxiliary bus voltage (from a specified voltage level). 
    aux_power_versus_voltage = 0.0

    # Default ambient temperature to be used in modeling applications 
    ambient_temp = 0.0

    # The time constant for the turbine. 
    time_constant = 0.0

    # Off-nominal frequency effect on turbine auxiliaries. Per unit reduction in auxiliary active power consumption versus per unit reduction in frequency (from rated frequency). 
    aux_power_versus_frequency = 0.0

    # Off-nominal frequency effect on turbine capability. Per unit reduction in unit active power capability versus per unit reduction in frequency (from rated frequency). 
    capability_versus_frequency = 0.0

    # A CAES air compressor is driven by combustion turbine
    drives_air_compressor = None

    # A combustion turbine may have a active power versus ambient temperature relationship
    cttemp_active_power_curve = None

    # A combustion turbine may have a heat recovery boiler for making steam
    heat_recovery_boiler = None

    # <<< combustion_turbine
    # @generated
    def __init__(self, reference_temp=0.0, power_variation_by_temp=0.0, heat_recovery_flag=False, aux_power_versus_voltage=0.0, ambient_temp=0.0, time_constant=0.0, aux_power_versus_frequency=0.0, capability_versus_frequency=0.0, drives_air_compressor=None, cttemp_active_power_curve=None, heat_recovery_boiler=None, **kw_args):
        """ Initialises a new 'CombustionTurbine' instance.
        """
        self.reference_temp = reference_temp
        self.power_variation_by_temp = power_variation_by_temp
        self.heat_recovery_flag = heat_recovery_flag
        self.aux_power_versus_voltage = aux_power_versus_voltage
        self.ambient_temp = ambient_temp
        self.time_constant = time_constant
        self.aux_power_versus_frequency = aux_power_versus_frequency
        self.capability_versus_frequency = capability_versus_frequency
        self.drives_air_compressor = drives_air_compressor
        self.cttemp_active_power_curve = cttemp_active_power_curve
        self.heat_recovery_boiler = heat_recovery_boiler

        super(CombustionTurbine, self).__init__(**kw_args)
    # >>> combustion_turbine


class BWRSteamSupply(SteamSupply):
    """ Boiling water reactor used as a steam supply to a steam turbine
    """
    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux2 = 0.0

    # Initial Lower Limit 
    lower_limit = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux6 = 0.0

    # Pressure Limit 
    pressure_limit = 0.0

    # Initial Upper Limit 
    upper_limit = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux3 = 0.0

    # Rod Pattern 
    rod_pattern = 0.0

    # Proportional Gain 
    proportional_gain = 0.0

    # In-Core Thermal Time Constant 
    in_core_thermal_tc = 0.0

    # High Power Limit 
    high_power_limit = 0.0

    # Constant Associated With Rod Pattern 
    rod_pattern_constant = 0.0

    # Low Power Limit 
    low_power_limit = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux7 = 0.0

    # Pressure Setpoint Time Constant 
    pressure_setpoint_tc1 = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux4 = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux1 = 0.0

    # Integral Gain 
    integral_gain = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux8 = 0.0

    # Pressure Setpoint Gain Adjuster 
    pressure_setpoint_ga = 0.0

    # Pressure Setpoint Time Constant 
    pressure_setpoint_tc2 = 0.0

    # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
    rf_aux5 = 0.0

    # <<< bwrsteam_supply
    # @generated
    def __init__(self, rf_aux2=0.0, lower_limit=0.0, rf_aux6=0.0, pressure_limit=0.0, upper_limit=0.0, rf_aux3=0.0, rod_pattern=0.0, proportional_gain=0.0, in_core_thermal_tc=0.0, high_power_limit=0.0, rod_pattern_constant=0.0, low_power_limit=0.0, rf_aux7=0.0, pressure_setpoint_tc1=0.0, rf_aux4=0.0, rf_aux1=0.0, integral_gain=0.0, rf_aux8=0.0, pressure_setpoint_ga=0.0, pressure_setpoint_tc2=0.0, rf_aux5=0.0, **kw_args):
        """ Initialises a new 'BWRSteamSupply' instance.
        """
        self.rf_aux2 = rf_aux2
        self.lower_limit = lower_limit
        self.rf_aux6 = rf_aux6
        self.pressure_limit = pressure_limit
        self.upper_limit = upper_limit
        self.rf_aux3 = rf_aux3
        self.rod_pattern = rod_pattern
        self.proportional_gain = proportional_gain
        self.in_core_thermal_tc = in_core_thermal_tc
        self.high_power_limit = high_power_limit
        self.rod_pattern_constant = rod_pattern_constant
        self.low_power_limit = low_power_limit
        self.rf_aux7 = rf_aux7
        self.pressure_setpoint_tc1 = pressure_setpoint_tc1
        self.rf_aux4 = rf_aux4
        self.rf_aux1 = rf_aux1
        self.integral_gain = integral_gain
        self.rf_aux8 = rf_aux8
        self.pressure_setpoint_ga = pressure_setpoint_ga
        self.pressure_setpoint_tc2 = pressure_setpoint_tc2
        self.rf_aux5 = rf_aux5

        super(BWRSteamSupply, self).__init__(**kw_args)
    # >>> bwrsteam_supply


class HydroTurbine(PrimeMover):
    """ A water driven prime mover. Typical turbine types are: Francis, Kaplan, and Pelton.
    """
    # Maximum efficiency active power at minimum head conditions 
    min_head_max_p = 0.0

    # Gate Rate Limit 
    gate_rate_limit = 0.0

    # Speed Regulation 
    speed_regulation = 0.0

    # Transient Regulation 
    transient_regulation = 0.0

    # Transient Droop Time Constant 
    transient_droop_time = 0.0

    # Maximum efficiency active power at maximum head conditions 
    max_head_max_p = 0.0

    # Rated turbine active power 
    turbine_rating = 0.0

    # Water Starting Time 
    water_starting_time = 0.0

    # Type of turbine. Values are: "pelton", "francis", "kaplan"
    turbine_type = 'pelton'

    # Gate Upper Limit 
    gate_upper_limit = 0.0

    # Rated speed in number of revolutions. 
    speed_rating = 0.0

    # <<< hydro_turbine
    # @generated
    def __init__(self, min_head_max_p=0.0, gate_rate_limit=0.0, speed_regulation=0.0, transient_regulation=0.0, transient_droop_time=0.0, max_head_max_p=0.0, turbine_rating=0.0, water_starting_time=0.0, turbine_type='pelton', gate_upper_limit=0.0, speed_rating=0.0, **kw_args):
        """ Initialises a new 'HydroTurbine' instance.
        """
        self.min_head_max_p = min_head_max_p
        self.gate_rate_limit = gate_rate_limit
        self.speed_regulation = speed_regulation
        self.transient_regulation = transient_regulation
        self.transient_droop_time = transient_droop_time
        self.max_head_max_p = max_head_max_p
        self.turbine_rating = turbine_rating
        self.water_starting_time = water_starting_time
        self.turbine_type = turbine_type
        self.gate_upper_limit = gate_upper_limit
        self.speed_rating = speed_rating

        super(HydroTurbine, self).__init__(**kw_args)
    # >>> hydro_turbine


class FossilSteamSupply(SteamSupply):
    """ Fossil fueled boiler (e.g., coal, oil, gas)
    """
    # Superheater Pipe Pressure Drop Constant 
    super_heater_pipe_pd = 0.0

    # Active power Maximum Error Rate Limit 
    max_error_rate_p = 0.0

    # Off nominal voltage effect on auxiliary real power. Per unit active power variation versus per unit voltage variation. 
    aux_powerversus_voltage = 0.0

    # Pressure Control Proportional Gain ratio 
    pressure_ctrl_pg = 0.0

    # Secondary Superheater Capacity 
    super_heater2_capacity = 0.0

    # The control mode of the boiler Values are: "coordinated", "following"
    boiler_control_mode = 'coordinated'

    # Mechanical Power Sensor Lag 
    mech_power_sensor_lag = 0.0

    # Pressure Error Bias ratio 
    control_peb = 0.0

    # Fuel Demand Limit 
    fuel_demand_limit = 0.0

    # Proportional Constant 
    control_pc = 0.0

    # Pressure Feedback Indicator 
    pressure_feedback = 0

    # Off nominal frequency effect on auxiliary real power. Per unit active power variation versus per unit frequency variation. 
    aux_power_versus_frequency = 0.0

    # Feedwater Integral Gain ratio 
    feed_water_ig = 0.0

    # Time Constant 
    control_tc = 0.0

    # Active power Error Bias ratio 
    control_error_bias_p = 0.0

    # Feedwater Time Constant rato 
    feed_water_tc = 0.0

    # Feedwater Proportional Gain ratio 
    feed_water_pg = 0.0

    # Fuel Delay 
    fuel_supply_delay = 0.0

    # Pressure Control Derivative Gain ratio 
    pressure_ctrl_dg = 0.0

    # Pressure Control Integral Gain ratio 
    pressure_ctrl_ig = 0.0

    # Fuel Supply Time Constant 
    fuel_supply_tc = 0.0

    # Integral Constant 
    control_ic = 0.0

    # Throttle Pressure Setpoint 
    throttle_pressure_sp = 0.0

    # Drum/Primary Superheater Capacity 
    super_heater1_capacity = 0.0

    # Pressure Error Deadband 
    control_ped = 0.0

    # Active power Minimum Error Rate Limit 
    min_error_rate_p = 0.0

    # <<< fossil_steam_supply
    # @generated
    def __init__(self, super_heater_pipe_pd=0.0, max_error_rate_p=0.0, aux_powerversus_voltage=0.0, pressure_ctrl_pg=0.0, super_heater2_capacity=0.0, boiler_control_mode='coordinated', mech_power_sensor_lag=0.0, control_peb=0.0, fuel_demand_limit=0.0, control_pc=0.0, pressure_feedback=0, aux_power_versus_frequency=0.0, feed_water_ig=0.0, control_tc=0.0, control_error_bias_p=0.0, feed_water_tc=0.0, feed_water_pg=0.0, fuel_supply_delay=0.0, pressure_ctrl_dg=0.0, pressure_ctrl_ig=0.0, fuel_supply_tc=0.0, control_ic=0.0, throttle_pressure_sp=0.0, super_heater1_capacity=0.0, control_ped=0.0, min_error_rate_p=0.0, **kw_args):
        """ Initialises a new 'FossilSteamSupply' instance.
        """
        self.super_heater_pipe_pd = super_heater_pipe_pd
        self.max_error_rate_p = max_error_rate_p
        self.aux_powerversus_voltage = aux_powerversus_voltage
        self.pressure_ctrl_pg = pressure_ctrl_pg
        self.super_heater2_capacity = super_heater2_capacity
        self.boiler_control_mode = boiler_control_mode
        self.mech_power_sensor_lag = mech_power_sensor_lag
        self.control_peb = control_peb
        self.fuel_demand_limit = fuel_demand_limit
        self.control_pc = control_pc
        self.pressure_feedback = pressure_feedback
        self.aux_power_versus_frequency = aux_power_versus_frequency
        self.feed_water_ig = feed_water_ig
        self.control_tc = control_tc
        self.control_error_bias_p = control_error_bias_p
        self.feed_water_tc = feed_water_tc
        self.feed_water_pg = feed_water_pg
        self.fuel_supply_delay = fuel_supply_delay
        self.pressure_ctrl_dg = pressure_ctrl_dg
        self.pressure_ctrl_ig = pressure_ctrl_ig
        self.fuel_supply_tc = fuel_supply_tc
        self.control_ic = control_ic
        self.throttle_pressure_sp = throttle_pressure_sp
        self.super_heater1_capacity = super_heater1_capacity
        self.control_ped = control_ped
        self.min_error_rate_p = min_error_rate_p

        super(FossilSteamSupply, self).__init__(**kw_args)
    # >>> fossil_steam_supply


class Subcritical(FossilSteamSupply):
    """ Once-through subcritical boiler
    """
    pass
    # <<< subcritical
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Subcritical' instance.
        """

        super(Subcritical, self).__init__(**kw_args)
    # >>> subcritical


class Supercritical(FossilSteamSupply):
    """ Once-through supercritical boiler
    """
    pass
    # <<< supercritical
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Supercritical' instance.
        """

        super(Supercritical, self).__init__(**kw_args)
    # >>> supercritical


class HeatRecoveryBoiler(FossilSteamSupply):
    """ The heat recovery system associated with combustion turbines in order to produce steam for combined cycle plants
    """
    # The steam supply rating in kilopounds per hour, if dual pressure boiler 
    steam_supply_rating2 = 0.0

    # A combustion turbine may have a heat recovery boiler for making steam
    combustion_turbines = []

    # <<< heat_recovery_boiler
    # @generated
    def __init__(self, steam_supply_rating2=0.0, combustion_turbines=[], **kw_args):
        """ Initialises a new 'HeatRecoveryBoiler' instance.
        """
        self.steam_supply_rating2 = steam_supply_rating2
        self.combustion_turbines = combustion_turbines

        super(HeatRecoveryBoiler, self).__init__(**kw_args)
    # >>> heat_recovery_boiler


class DrumBoiler(FossilSteamSupply):
    """ Drum boiler
    """
    # Rating of drum boiler in steam units 
    drum_boiler_rating = 0.0

    # <<< drum_boiler
    # @generated
    def __init__(self, drum_boiler_rating=0.0, **kw_args):
        """ Initialises a new 'DrumBoiler' instance.
        """
        self.drum_boiler_rating = drum_boiler_rating

        super(DrumBoiler, self).__init__(**kw_args)
    # >>> drum_boiler


# <<< generation_dynamics
# @generated
# >>> generation_dynamics

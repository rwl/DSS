# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import Curve

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Generation.GenerationDynamics"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Generation.GenerationDynamics"

class SteamSupply(PowerSystemResource):
    """ Steam supply for steam turbine
    """
    # <<< steam_supply
    # @generated
    def __init__(self, steam_supply_rating=0.0, steam_turbines=[], **kw_args):
        """ Initialises a new 'SteamSupply' instance.
        """
        # Rating of steam supply 
        self.steam_supply_rating = 0.0
        
        self._steam_turbines = []
        self.steam_turbines = steam_turbines

        super(SteamSupply, self).__init__(**kw_args)
    # >>> steam_supply
        
    # <<< steam_turbines
    # @generated
    def get_steam_turbines(self):
        """ Steam turbines may have steam supplied by a steam supply
        """
        return self._steam_turbines

    def set_steam_turbines(self, value):
        for p in self._steam_turbines:
            filtered = [q for q in p.steam_supplys if q != self]
            self._steam_turbines._steam_supplys = filtered
        for r in value:
            if self not in r._steam_supplys:
                r._steam_supplys.append(self)
        self._steam_turbines = value
            
    steam_turbines = property(get_steam_turbines, set_steam_turbines)
    
    def add_steam_turbines(self, *steam_turbines):
        for obj in steam_turbines:
            if self not in obj._steam_supplys:
                obj._steam_supplys.append(self)
            self._steam_turbines.append(obj)
        
    def remove_steam_turbines(self, *steam_turbines):
        for obj in steam_turbines:
            if self in obj._steam_supplys:
                obj._steam_supplys.remove(self)
            self._steam_turbines.remove(obj)
    # >>> steam_turbines



class PrimeMover(PowerSystemResource):
    """ The machine used to develop mechanical energy used to drive a generator.
    """
    # <<< prime_mover
    # @generated
    def __init__(self, prime_mover_rating=0.0, synchronous_machines=[], **kw_args):
        """ Initialises a new 'PrimeMover' instance.
        """
        # Rating of prime mover 
        self.prime_mover_rating = 0.0
        
        self._synchronous_machines = []
        self.synchronous_machines = synchronous_machines

        super(PrimeMover, self).__init__(**kw_args)
    # >>> prime_mover
        
    # <<< synchronous_machines
    # @generated
    def get_synchronous_machines(self):
        """ Synchronous machines this Prime mover drives.
        """
        return self._synchronous_machines

    def set_synchronous_machines(self, value):
        for p in self._synchronous_machines:
            filtered = [q for q in p.prime_movers if q != self]
            self._synchronous_machines._prime_movers = filtered
        for r in value:
            if self not in r._prime_movers:
                r._prime_movers.append(self)
        self._synchronous_machines = value
            
    synchronous_machines = property(get_synchronous_machines, set_synchronous_machines)
    
    def add_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
            if self not in obj._prime_movers:
                obj._prime_movers.append(self)
            self._synchronous_machines.append(obj)
        
    def remove_synchronous_machines(self, *synchronous_machines):
        for obj in synchronous_machines:
            if self in obj._prime_movers:
                obj._prime_movers.remove(self)
            self._synchronous_machines.remove(obj)
    # >>> synchronous_machines



class CTTempActivePowerCurve(Curve):
    """ Relationship between the combustion turbine's power output rating in gross active power (X-axis) and the ambient air temperature (Y-axis)
    """
    # <<< cttemp_active_power_curve
    # @generated
    def __init__(self, combustion_turbine=None, **kw_args):
        """ Initialises a new 'CTTempActivePowerCurve' instance.
        """
        
        self._combustion_turbine = None
        self.combustion_turbine = combustion_turbine

        super(CTTempActivePowerCurve, self).__init__(**kw_args)
    # >>> cttemp_active_power_curve
        
    # <<< combustion_turbine
    # @generated
    def get_combustion_turbine(self):
        """ A combustion turbine may have an active power versus ambient temperature relationship
        """
        return self._combustion_turbine

    def set_combustion_turbine(self, value):
        if self._combustion_turbine is not None:
            self._combustion_turbine._cttemp_active_power_curve = None
            
        self._combustion_turbine = value
        if self._combustion_turbine is not None:
            self._combustion_turbine._cttemp_active_power_curve = self
            
    combustion_turbine = property(get_combustion_turbine, set_combustion_turbine)
    # >>> combustion_turbine



class BWRSteamSupply(SteamSupply):
    """ Boiling water reactor used as a steam supply to a steam turbine
    """
    # <<< bwrsteam_supply
    # @generated
    def __init__(self, pressure_limit='', pressure_setpoint_ga=0.0, in_core_thermal_tc='', rf_aux6='', pressure_setpoint_tc1='', high_power_limit='', rf_aux4='', rf_aux2='', rf_aux8='', lower_limit='', integral_gain=0.0, rf_aux5='', rod_pattern='', rf_aux1='', pressure_setpoint_tc2='', rf_aux7='', low_power_limit='', rf_aux3='', rod_pattern_constant=0.0, proportional_gain=0.0, upper_limit='', **kw_args):
        """ Initialises a new 'BWRSteamSupply' instance.
        """
        # Pressure Limit 
        self.pressure_limit = ''
        # Pressure Setpoint Gain Adjuster 
        self.pressure_setpoint_ga = 0.0
        # In-Core Thermal Time Constant 
        self.in_core_thermal_tc = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux6 = ''
        # Pressure Setpoint Time Constant 
        self.pressure_setpoint_tc1 = ''
        # High Power Limit 
        self.high_power_limit = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux4 = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux2 = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux8 = ''
        # Initial Lower Limit 
        self.lower_limit = ''
        # Integral Gain 
        self.integral_gain = 0.0
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux5 = ''
        # Rod Pattern 
        self.rod_pattern = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux1 = ''
        # Pressure Setpoint Time Constant 
        self.pressure_setpoint_tc2 = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux7 = ''
        # Low Power Limit 
        self.low_power_limit = ''
        # Coefficient for modeling the effect of off-nominal frequency and voltage on recirculation and core flow, which affects the BWR power output. 
        self.rf_aux3 = ''
        # Constant Associated With Rod Pattern 
        self.rod_pattern_constant = 0.0
        # Proportional Gain 
        self.proportional_gain = 0.0
        # Initial Upper Limit 
        self.upper_limit = ''
        

        super(BWRSteamSupply, self).__init__(**kw_args)
    # >>> bwrsteam_supply
        


class HydroTurbine(PrimeMover):
    """ A water driven prime mover. Typical turbine types are: Francis, Kaplan, and Pelton.
    """
    # <<< hydro_turbine
    # @generated
    def __init__(self, transient_regulation='', water_starting_time='', gate_rate_limit=0.0, transient_droop_time='', speed_rating='', max_head_max_p='', turbine_type='kaplan', gate_upper_limit='', speed_regulation='', min_head_max_p='', turbine_rating='', **kw_args):
        """ Initialises a new 'HydroTurbine' instance.
        """
        # Transient Regulation 
        self.transient_regulation = ''
        # Water Starting Time 
        self.water_starting_time = ''
        # Gate Rate Limit 
        self.gate_rate_limit = 0.0
        # Transient Droop Time Constant 
        self.transient_droop_time = ''
        # Rated speed in number of revolutions. 
        self.speed_rating = ''
        # Maximum efficiency active power at maximum head conditions 
        self.max_head_max_p = ''
        # Type of turbine. Values are: "kaplan", "pelton", "francis"
        self.turbine_type = 'kaplan'
        # Gate Upper Limit 
        self.gate_upper_limit = ''
        # Speed Regulation 
        self.speed_regulation = ''
        # Maximum efficiency active power at minimum head conditions 
        self.min_head_max_p = ''
        # Rated turbine active power 
        self.turbine_rating = ''
        

        super(HydroTurbine, self).__init__(**kw_args)
    # >>> hydro_turbine
        


class FossilSteamSupply(SteamSupply):
    """ Fossil fueled boiler (e.g., coal, oil, gas)
    """
    # <<< fossil_steam_supply
    # @generated
    def __init__(self, pressure_ctrl_pg=0.0, pressure_ctrl_ig=0.0, aux_power_versus_frequency='', throttle_pressure_sp='', aux_power_versus_voltage='', super_heater_pipe_pd=0.0, pressure_ctrl_dg=0.0, super_heater2_capacity=0.0, control_error_bias_p=0.0, control_pc=0.0, mech_power_sensor_lag='', boiler_control_mode='following', control_tc=0.0, feed_water_tc='', feed_water_pg=0.0, fuel_supply_tc='', min_error_rate_p=0.0, control_ic=0.0, fuel_demand_limit='', pressure_feedback=0, control_peb=0.0, fuel_supply_delay='', feed_water_ig=0.0, super_heater1_capacity=0.0, control_ped='', max_error_rate_p=0.0, **kw_args):
        """ Initialises a new 'FossilSteamSupply' instance.
        """
        # Pressure Control Proportional Gain ratio 
        self.pressure_ctrl_pg = 0.0
        # Pressure Control Integral Gain ratio 
        self.pressure_ctrl_ig = 0.0
        # Off nominal frequency effect on auxiliary real power. Per unit active power variation versus per unit frequency variation. 
        self.aux_power_versus_frequency = ''
        # Throttle Pressure Setpoint 
        self.throttle_pressure_sp = ''
        # Off nominal voltage effect on auxiliary real power. Per unit active power variation versus per unit voltage variation. 
        self.aux_power_versus_voltage = ''
        # Superheater Pipe Pressure Drop Constant 
        self.super_heater_pipe_pd = 0.0
        # Pressure Control Derivative Gain ratio 
        self.pressure_ctrl_dg = 0.0
        # Secondary Superheater Capacity 
        self.super_heater2_capacity = 0.0
        # Active power Error Bias ratio 
        self.control_error_bias_p = 0.0
        # Proportional Constant 
        self.control_pc = 0.0
        # Mechanical Power Sensor Lag 
        self.mech_power_sensor_lag = ''
        # The control mode of the boiler Values are: "following", "coordinated"
        self.boiler_control_mode = 'following'
        # Time Constant 
        self.control_tc = 0.0
        # Feedwater Time Constant rato 
        self.feed_water_tc = ''
        # Feedwater Proportional Gain ratio 
        self.feed_water_pg = 0.0
        # Fuel Supply Time Constant 
        self.fuel_supply_tc = ''
        # Active power Minimum Error Rate Limit 
        self.min_error_rate_p = 0.0
        # Integral Constant 
        self.control_ic = 0.0
        # Fuel Demand Limit 
        self.fuel_demand_limit = ''
        # Pressure Feedback Indicator 
        self.pressure_feedback = 0
        # Pressure Error Bias ratio 
        self.control_peb = 0.0
        # Fuel Delay 
        self.fuel_supply_delay = ''
        # Feedwater Integral Gain ratio 
        self.feed_water_ig = 0.0
        # Drum/Primary Superheater Capacity 
        self.super_heater1_capacity = 0.0
        # Pressure Error Deadband 
        self.control_ped = ''
        # Active power Maximum Error Rate Limit 
        self.max_error_rate_p = 0.0
        

        super(FossilSteamSupply, self).__init__(**kw_args)
    # >>> fossil_steam_supply
        


class PWRSteamSupply(SteamSupply):
    """ Pressurized water reactor used as a steam supply to a steam turbine
    """
    # <<< pwrsteam_supply
    # @generated
    def __init__(self, cold_leg_fblead_tc1='', throttle_pressure_sp='', cold_leg_fg1='', cold_leg_fblag_tc='', core_htlag_tc1='', hot_leg_lag_tc='', core_neutronics_ht='', steam_pressure_fg='', throttle_pressure_factor='', hot_leg_to_cold_leg_gain='', steam_pressure_drop_lag_tc='', pressure_cg='', cold_leg_fblead_tc2='', feedback_factor='', steam_flow_fg='', cold_leg_lag_tc='', hot_leg_steam_gain='', core_neutronics_eff_tc='', cold_leg_fg2='', core_htlag_tc2='', **kw_args):
        """ Initialises a new 'PWRSteamSupply' instance.
        """
        # Cold Leg Feedback Lead Time Constant 
        self.cold_leg_fblead_tc1 = ''
        # Throttle Pressure Setpoint 
        self.throttle_pressure_sp = ''
        # Cold Leg Feedback Gain 1 
        self.cold_leg_fg1 = ''
        # Cold Leg Feedback Lag Time Constant 
        self.cold_leg_fblag_tc = ''
        # Core Heat Transfer Lag Time Constant 
        self.core_htlag_tc1 = ''
        # Hot Leg Lag Time Constant 
        self.hot_leg_lag_tc = ''
        # Core Neutronics And Heat Transfer 
        self.core_neutronics_ht = ''
        # Steam Pressure Feedback Gain 
        self.steam_pressure_fg = ''
        # Throttle Pressure Factor 
        self.throttle_pressure_factor = ''
        # Hot Leg To Cold Leg Gain 
        self.hot_leg_to_cold_leg_gain = ''
        # Steam Pressure Drop Lag Time Constant 
        self.steam_pressure_drop_lag_tc = ''
        # Pressure Control Gain 
        self.pressure_cg = ''
        # Cold Leg Feedback Lead Time Constant 
        self.cold_leg_fblead_tc2 = ''
        # Feedback Factor 
        self.feedback_factor = ''
        # Steam Flow Feedback Gain 
        self.steam_flow_fg = ''
        # Cold Leg Lag Time Constant 
        self.cold_leg_lag_tc = ''
        # Hot Leg Steam Gain 
        self.hot_leg_steam_gain = ''
        # Core Neutronics Effective Time Constant 
        self.core_neutronics_eff_tc = ''
        # Cold Leg Feedback Gain 2 
        self.cold_leg_fg2 = ''
        # Core Heat Transfer Lag Time Constant 
        self.core_htlag_tc2 = ''
        

        super(PWRSteamSupply, self).__init__(**kw_args)
    # >>> pwrsteam_supply
        


class CombustionTurbine(PrimeMover):
    """ A prime mover that is typically fueled by gas or light oil
    """
    # <<< combustion_turbine
    # @generated
    def __init__(self, time_constant='', reference_temp='', aux_power_versus_voltage='', power_variation_by_temp='', capability_versus_frequency='', ambient_temp='', heat_recovery_flag=False, aux_power_versus_frequency='', air_compressor=None, heat_recovery_boiler=None, cttemp_active_power_curve=None, **kw_args):
        """ Initialises a new 'CombustionTurbine' instance.
        """
        # The time constant for the turbine. 
        self.time_constant = ''
        # Reference temperature at which the output of the turbine was defined. 
        self.reference_temp = ''
        # Off-nominal voltage effect on turbine auxiliaries. Per unit reduction in auxiliary active power consumption versus per unit reduction in auxiliary bus voltage (from a specified voltage level). 
        self.aux_power_versus_voltage = ''
        # Per unit change in power per (versus) unit change in ambient temperature 
        self.power_variation_by_temp = ''
        # Off-nominal frequency effect on turbine capability. Per unit reduction in unit active power capability versus per unit reduction in frequency (from rated frequency). 
        self.capability_versus_frequency = ''
        # Default ambient temperature to be used in modeling applications 
        self.ambient_temp = ''
        # Flag that is set to true if the combustion turbine is associated with a heat recovery boiler 
        self.heat_recovery_flag = False
        # Off-nominal frequency effect on turbine auxiliaries. Per unit reduction in auxiliary active power consumption versus per unit reduction in frequency (from rated frequency). 
        self.aux_power_versus_frequency = ''
        
        self._air_compressor = None
        self.air_compressor = air_compressor
        self._heat_recovery_boiler = None
        self.heat_recovery_boiler = heat_recovery_boiler
        self._cttemp_active_power_curve = None
        self.cttemp_active_power_curve = cttemp_active_power_curve

        super(CombustionTurbine, self).__init__(**kw_args)
    # >>> combustion_turbine
        
    # <<< air_compressor
    # @generated
    def get_air_compressor(self):
        """ A CAES air compressor is driven by combustion turbine
        """
        return self._air_compressor

    def set_air_compressor(self, value):
        if self._air_compressor is not None:
            self._air_compressor._combustion_turbine = None
            
        self._air_compressor = value
        if self._air_compressor is not None:
            self._air_compressor._combustion_turbine = self
            
    air_compressor = property(get_air_compressor, set_air_compressor)
    # >>> air_compressor

    # <<< heat_recovery_boiler
    # @generated
    def get_heat_recovery_boiler(self):
        """ A combustion turbine may have a heat recovery boiler for making steam
        """
        return self._heat_recovery_boiler

    def set_heat_recovery_boiler(self, value):
        if self._heat_recovery_boiler is not None:
            filtered = [x for x in self.heat_recovery_boiler.combustion_turbines if x != self]
            self._heat_recovery_boiler._combustion_turbines = filtered
            
        self._heat_recovery_boiler = value
        if self._heat_recovery_boiler is not None:
            self._heat_recovery_boiler._combustion_turbines.append(self)

    heat_recovery_boiler = property(get_heat_recovery_boiler, set_heat_recovery_boiler)
    # >>> heat_recovery_boiler

    # <<< cttemp_active_power_curve
    # @generated
    def get_cttemp_active_power_curve(self):
        """ A combustion turbine may have an active power versus ambient temperature relationship
        """
        return self._cttemp_active_power_curve

    def set_cttemp_active_power_curve(self, value):
        if self._cttemp_active_power_curve is not None:
            self._cttemp_active_power_curve._combustion_turbine = None
            
        self._cttemp_active_power_curve = value
        if self._cttemp_active_power_curve is not None:
            self._cttemp_active_power_curve._combustion_turbine = self
            
    cttemp_active_power_curve = property(get_cttemp_active_power_curve, set_cttemp_active_power_curve)
    # >>> cttemp_active_power_curve



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
        


class SteamTurbine(PrimeMover):
    """ Steam turbine
    """
    # <<< steam_turbine
    # @generated
    def __init__(self, steam_chest_tc='', shaft2_power_lp2=0.0, crossover_tc='', shaft1_power_lp1=0.0, reheater1_tc='', shaft1_power_hp=0.0, shaft2_power_hp=0.0, shaft2_power_lp1=0.0, shaft1_power_ip=0.0, shaft1_power_lp2=0.0, reheater2_tc='', shaft2_power_ip=0.0, steam_supplys=[], **kw_args):
        """ Initialises a new 'SteamTurbine' instance.
        """
        # Steam Chest Time Constant 
        self.steam_chest_tc = ''
        # Fraction Of Power From Shaft 2 Second Low Pressure Turbine output 
        self.shaft2_power_lp2 = 0.0
        # Crossover Time Constant 
        self.crossover_tc = ''
        # Fraction Of Power From Shaft 1 First Low Pressure Turbine output 
        self.shaft1_power_lp1 = 0.0
        # First Reheater Time Constant 
        self.reheater1_tc = ''
        # Fraction Of Power From Shaft 1 High Pressure Turbine output 
        self.shaft1_power_hp = 0.0
        # Fraction Of Power From Shaft 2 High Pressure Turbine output 
        self.shaft2_power_hp = 0.0
        # Fraction Of Power From Shaft 2 First Low Pressure Turbine output 
        self.shaft2_power_lp1 = 0.0
        # Fraction Of Power From Shaft 1 Intermediate Pressure Turbine output 
        self.shaft1_power_ip = 0.0
        # Fraction Of Power From Shaft 1 Second Low Pressure Turbine output 
        self.shaft1_power_lp2 = 0.0
        # Second Reheater Time Constant 
        self.reheater2_tc = ''
        # Fraction Of Power From Shaft 2 Intermediate Pressure Turbine output 
        self.shaft2_power_ip = 0.0
        
        self._steam_supplys = []
        self.steam_supplys = steam_supplys

        super(SteamTurbine, self).__init__(**kw_args)
    # >>> steam_turbine
        
    # <<< steam_supplys
    # @generated
    def get_steam_supplys(self):
        """ Steam turbines may have steam supplied by a steam supply
        """
        return self._steam_supplys

    def set_steam_supplys(self, value):
        for p in self._steam_supplys:
            filtered = [q for q in p.steam_turbines if q != self]
            self._steam_supplys._steam_turbines = filtered
        for r in value:
            if self not in r._steam_turbines:
                r._steam_turbines.append(self)
        self._steam_supplys = value
            
    steam_supplys = property(get_steam_supplys, set_steam_supplys)
    
    def add_steam_supplys(self, *steam_supplys):
        for obj in steam_supplys:
            if self not in obj._steam_turbines:
                obj._steam_turbines.append(self)
            self._steam_supplys.append(obj)
        
    def remove_steam_supplys(self, *steam_supplys):
        for obj in steam_supplys:
            if self in obj._steam_turbines:
                obj._steam_turbines.remove(self)
            self._steam_supplys.remove(obj)
    # >>> steam_supplys



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
        


class DrumBoiler(FossilSteamSupply):
    """ Drum boiler
    """
    # <<< drum_boiler
    # @generated
    def __init__(self, drum_boiler_rating=0.0, **kw_args):
        """ Initialises a new 'DrumBoiler' instance.
        """
        # Rating of drum boiler in steam units 
        self.drum_boiler_rating = 0.0
        

        super(DrumBoiler, self).__init__(**kw_args)
    # >>> drum_boiler
        


class HeatRecoveryBoiler(FossilSteamSupply):
    """ The heat recovery system associated with combustion turbines in order to produce steam for combined cycle plants
    """
    # <<< heat_recovery_boiler
    # @generated
    def __init__(self, steam_supply_rating2=0.0, combustion_turbines=[], **kw_args):
        """ Initialises a new 'HeatRecoveryBoiler' instance.
        """
        # The steam supply rating in kilopounds per hour, if dual pressure boiler 
        self.steam_supply_rating2 = 0.0
        
        self._combustion_turbines = []
        self.combustion_turbines = combustion_turbines

        super(HeatRecoveryBoiler, self).__init__(**kw_args)
    # >>> heat_recovery_boiler
        
    # <<< combustion_turbines
    # @generated
    def get_combustion_turbines(self):
        """ A combustion turbine may have a heat recovery boiler for making steam
        """
        return self._combustion_turbines

    def set_combustion_turbines(self, value):
        for x in self._combustion_turbines:
            x._heat_recovery_boiler = None
        for y in value:
            y._heat_recovery_boiler = self
        self._combustion_turbines = value
            
    combustion_turbines = property(get_combustion_turbines, set_combustion_turbines)
    
    def add_combustion_turbines(self, *combustion_turbines):
        for obj in combustion_turbines:
            obj._heat_recovery_boiler = self
            self._combustion_turbines.append(obj)
        
    def remove_combustion_turbines(self, *combustion_turbines):
        for obj in combustion_turbines:
            obj._heat_recovery_boiler = None
            self._combustion_turbines.remove(obj)
    # >>> combustion_turbines



# <<< generation_dynamics
# @generated
# >>> generation_dynamics

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim.core import IdentifiedObject
from cim import Element
from cim.wires import EnergyConsumer
from cim.core import PowerSystemResource
from cim.core import RegularIntervalSchedule

# <<< imports
# @generated
# >>> imports

class LoadResponseCharacteristic(IdentifiedObject):
    """ Models the characteristic response of the load demand due to to changes in system conditions such as voltage and frequency. This is not related to demand response.
    """
    # Portion of reactive power load modeled as constant power. Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_power = 0.0

    # Exponent of per unit voltage effecting real power.   This model used only when 'useExponentModel' is true. 
    p_voltage_exponent = 0.0

    # Exponent of per unit frequency effecting active power 
    p_frequency_exponent = 0.0

    # Portion of reactive power load modeled as constant impedance.  Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_impedance = 0.0

    # Portion of active power load modeled as constant power. Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_power = 0.0

    # Portion of active power load modeled as constant impedance.  Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_impedance = 0.0

    # Portion of reactive power load modeled as constant current. Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_current = 0.0

    # Exponent of per unit frequency effecting reactive power 
    q_frequency_exponent = 0.0

    # Indicates the exponential voltage dependency model (pVoltateExponent and qVoltageExponent) is to be used.   If false, the coeficient model (consisting of pConstantImpedance, pConstantCurrent, pConstantPower, qConstantImpedance, qConstantCurrent, and qConstantPower) is to be used. 
    exponent_model = False

    # Exponent of per unit voltage effecting reactive power.   This model used only when 'useExponentModel' is true. 
    q_voltage_exponent = 0.0

    # Portion of active power load modeled as constant current. Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_current = 0.0

    energy_consumer = []

    # <<< load_response_characteristic
    # @generated
    def __init__(self, q_constant_power=0.0, p_voltage_exponent=0.0, p_frequency_exponent=0.0, q_constant_impedance=0.0, p_constant_power=0.0, p_constant_impedance=0.0, q_constant_current=0.0, q_frequency_exponent=0.0, exponent_model=False, q_voltage_exponent=0.0, p_constant_current=0.0, energy_consumer=[], **kw_args):
        """ Initialises a new 'LoadResponseCharacteristic' instance.
        """
        self.q_constant_power = q_constant_power
        self.p_voltage_exponent = p_voltage_exponent
        self.p_frequency_exponent = p_frequency_exponent
        self.q_constant_impedance = q_constant_impedance
        self.p_constant_power = p_constant_power
        self.p_constant_impedance = p_constant_impedance
        self.q_constant_current = q_constant_current
        self.q_frequency_exponent = q_frequency_exponent
        self.exponent_model = exponent_model
        self.q_voltage_exponent = q_voltage_exponent
        self.p_constant_current = p_constant_current
        self.energy_consumer = energy_consumer

        super(LoadResponseCharacteristic, self).__init__(**kw_args)
    # >>> load_response_characteristic


class Season(Element):
    """ A specified time period of the year, e.g., Spring, Summer, Fall, Winter
    """
    # Date season ends 
    end_date = ''

    # Name of the Season Values are: "summer", "fall", "winter", "spring"
    name = 'summer'

    # Date season starts 
    start_date = ''

    # Load demand models can be based on seasons
    season_day_type_schedules = []

    # <<< season
    # @generated
    def __init__(self, end_date='', name='summer', start_date='', season_day_type_schedules=[], **kw_args):
        """ Initialises a new 'Season' instance.
        """
        self.end_date = end_date
        self.name = name
        self.start_date = start_date
        self.season_day_type_schedules = season_day_type_schedules

        super(Season, self).__init__(**kw_args)
    # >>> season


class ConformLoad(EnergyConsumer):
    """ ConformLoad represent loads that follow a daily load change pattern where the pattern can be used to scale the load with a system load.
    """
    # Consumers may be assigned to a load area.
    load_group = None

    # <<< conform_load
    # @generated
    def __init__(self, load_group=None, **kw_args):
        """ Initialises a new 'ConformLoad' instance.
        """
        self.load_group = load_group

        super(ConformLoad, self).__init__(**kw_args)
    # >>> conform_load


class PowerCutZone(PowerSystemResource):
    """ An area or zone of the power system which is used for load shedding purposes.
    """
    # First level (amount) of load to cut as a percentage of total zone load 
    cut_level1 = 0.0

    # Second level (amount) of load to cut as a percentage of total zone load 
    cut_level2 = 0.0

    # An energy consumer is assigned to a power cut zone
    energy_consumers = []

    # <<< power_cut_zone
    # @generated
    def __init__(self, cut_level1=0.0, cut_level2=0.0, energy_consumers=[], **kw_args):
        """ Initialises a new 'PowerCutZone' instance.
        """
        self.cut_level1 = cut_level1
        self.cut_level2 = cut_level2
        self.energy_consumers = energy_consumers

        super(PowerCutZone, self).__init__(**kw_args)
    # >>> power_cut_zone


class StationSupply(EnergyConsumer):
    """ Station supply with load derived from the station output.
    """
    pass
    # <<< station_supply
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'StationSupply' instance.
        """

        super(StationSupply, self).__init__(**kw_args)
    # >>> station_supply


class DayType(IdentifiedObject):
    """ Group of similar days, e.g., Mon/Tue/Wed, Thu/Fri, Sat/Sun, Holiday1, Holiday2
    """
    # Load demand models can be based on day type
    season_day_type_schedules = []

    # <<< day_type
    # @generated
    def __init__(self, season_day_type_schedules=[], **kw_args):
        """ Initialises a new 'DayType' instance.
        """
        self.season_day_type_schedules = season_day_type_schedules

        super(DayType, self).__init__(**kw_args)
    # >>> day_type


class LoadGroup(IdentifiedObject):
    """ The class is the third level in a hierarchical structure for grouping of loads for the purpose of load flow load scaling.
    """
    sub_load_area = None

    # <<< load_group
    # @generated
    def __init__(self, sub_load_area=None, **kw_args):
        """ Initialises a new 'LoadGroup' instance.
        """
        self.sub_load_area = sub_load_area

        super(LoadGroup, self).__init__(**kw_args)
    # >>> load_group


class EnergyArea(IdentifiedObject):
    """ The class describes an area having energy production or consumption. The class is the basis for further specialization.
    """
    control_area = None

    # <<< energy_area
    # @generated
    def __init__(self, control_area=None, **kw_args):
        """ Initialises a new 'EnergyArea' instance.
        """
        self.control_area = control_area

        super(EnergyArea, self).__init__(**kw_args)
    # >>> energy_area


class SeasonDayTypeSchedule(RegularIntervalSchedule):
    """ The schedule specialize RegularIntervalSchedule with type curve data for a specific type of day and season. This means that curves of this type cover a 24 hour period.
    """
    # Load demand models can be based on day type
    day_type = None

    # Load demand models can be based on seasons
    season = None

    # <<< season_day_type_schedule
    # @generated
    def __init__(self, day_type=None, season=None, **kw_args):
        """ Initialises a new 'SeasonDayTypeSchedule' instance.
        """
        self.day_type = day_type
        self.season = season

        super(SeasonDayTypeSchedule, self).__init__(**kw_args)
    # >>> season_day_type_schedule


class NonConformLoad(EnergyConsumer):
    """ NonConformLoad represent loads that do not follow a daily load change pattern and changes are not correlated with the daily load change pattern.
    """
    load_group = None

    # <<< non_conform_load
    # @generated
    def __init__(self, load_group=None, **kw_args):
        """ Initialises a new 'NonConformLoad' instance.
        """
        self.load_group = load_group

        super(NonConformLoad, self).__init__(**kw_args)
    # >>> non_conform_load


class SubLoadArea(EnergyArea):
    """ The class is the second level in a hierarchical structure for grouping of loads for the purpose of load flow load scaling.
    """
    # The SubLoadAreas in the LoadArea.
    load_area = None

    load_groups = []

    # <<< sub_load_area
    # @generated
    def __init__(self, load_area=None, load_groups=[], **kw_args):
        """ Initialises a new 'SubLoadArea' instance.
        """
        self.load_area = load_area
        self.load_groups = load_groups

        super(SubLoadArea, self).__init__(**kw_args)
    # >>> sub_load_area


class Load(ConformLoad):
    """ A generic equivalent for an energy consumer on a transmission or distribution voltage level. It may be under load management and also has cold load pick up characteristics.
    """
    # The feeder's contribution to load management. 
    feeder_load_mgt_factor = 0.0

    # The rated individual phase current. 
    phase_rated_current = 0.0

    # The amount of nominal feeder active power that is picked up cold. 
    cold_pick_up_factor_p = 0.0

    # The amount of nominal feeder reactive power that is picked up cold. 
    cold_pick_up_factor_q = 0.0

    # Permit assignment of loads on a participation factor basis. Given three equivalent loads with factors of 10, 25 and 15, a feeder load of 100 amps could be allocated on the feeder as 20, 50 and 30 amps. 
    load_allocation_factor = 0.0

    # <<< load
    # @generated
    def __init__(self, feeder_load_mgt_factor=0.0, phase_rated_current=0.0, cold_pick_up_factor_p=0.0, cold_pick_up_factor_q=0.0, load_allocation_factor=0.0, **kw_args):
        """ Initialises a new 'Load' instance.
        """
        self.feeder_load_mgt_factor = feeder_load_mgt_factor
        self.phase_rated_current = phase_rated_current
        self.cold_pick_up_factor_p = cold_pick_up_factor_p
        self.cold_pick_up_factor_q = cold_pick_up_factor_q
        self.load_allocation_factor = load_allocation_factor

        super(Load, self).__init__(**kw_args)
    # >>> load


class ConformLoadGroup(LoadGroup):
    """ Load that follows a daily and seasonal load variation pattern.
    """
    # Consumers may be assigned to a load area.
    energy_consumers = []

    conform_load_schedules = []

    # <<< conform_load_group
    # @generated
    def __init__(self, energy_consumers=[], conform_load_schedules=[], **kw_args):
        """ Initialises a new 'ConformLoadGroup' instance.
        """
        self.energy_consumers = energy_consumers
        self.conform_load_schedules = conform_load_schedules

        super(ConformLoadGroup, self).__init__(**kw_args)
    # >>> conform_load_group


class LoadArea(EnergyArea):
    """ The class is the root or first level in a hierarchical structure for grouping of loads for the purpose of load flow load scaling.
    """
    # The SubLoadAreas in the LoadArea.
    sub_load_areas = []

    # <<< load_area
    # @generated
    def __init__(self, sub_load_areas=[], **kw_args):
        """ Initialises a new 'LoadArea' instance.
        """
        self.sub_load_areas = sub_load_areas

        super(LoadArea, self).__init__(**kw_args)
    # >>> load_area


class ConformLoadSchedule(SeasonDayTypeSchedule):
    """ A curve of load  versus time (X-axis) showing the active power values (Y1-axis) and reactive power (Y2-axis) for each unit of the period covered. This curve represents a typical pattern of load over the time period for a given day type and season.
    """
    conform_load_group = None

    # <<< conform_load_schedule
    # @generated
    def __init__(self, conform_load_group=None, **kw_args):
        """ Initialises a new 'ConformLoadSchedule' instance.
        """
        self.conform_load_group = conform_load_group

        super(ConformLoadSchedule, self).__init__(**kw_args)
    # >>> conform_load_schedule


class NonConformLoadSchedule(SeasonDayTypeSchedule):
    """ An active power (Y1-axis) and reactive power (Y2-axis) schedule (curves) versus time (X-axis) for non-conforming loads, e.g., large industrial load or power station service (where modeled)
    """
    non_conform_load_group = None

    # <<< non_conform_load_schedule
    # @generated
    def __init__(self, non_conform_load_group=None, **kw_args):
        """ Initialises a new 'NonConformLoadSchedule' instance.
        """
        self.non_conform_load_group = non_conform_load_group

        super(NonConformLoadSchedule, self).__init__(**kw_args)
    # >>> non_conform_load_schedule


class CustomerLoad(ConformLoad):
    """ A meter for measuring customer energy consumption. The typeName attribute indicates the type of customer meter.
    """
    pass
    # <<< customer_load
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'CustomerLoad' instance.
        """

        super(CustomerLoad, self).__init__(**kw_args)
    # >>> customer_load


class NonConformLoadGroup(LoadGroup):
    """ Loads that do not follow a daily and seasonal load variation pattern.
    """
    non_conform_load_schedules = []

    energy_consumers = []

    # <<< non_conform_load_group
    # @generated
    def __init__(self, non_conform_load_schedules=[], energy_consumers=[], **kw_args):
        """ Initialises a new 'NonConformLoadGroup' instance.
        """
        self.non_conform_load_schedules = non_conform_load_schedules
        self.energy_consumers = energy_consumers

        super(NonConformLoadGroup, self).__init__(**kw_args)
    # >>> non_conform_load_group


class InductionMotorLoad(NonConformLoad):
    """ Large three phase induction motor load. The typeName attribute indicates the type of induction motor (1 = wound rotor) (2 = squirrel cage).
    """
    pass
    # <<< induction_motor_load
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'InductionMotorLoad' instance.
        """

        super(InductionMotorLoad, self).__init__(**kw_args)
    # >>> induction_motor_load


# <<< load_model
# @generated
# >>> load_model

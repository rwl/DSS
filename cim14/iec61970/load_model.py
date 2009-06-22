# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import RegularIntervalSchedule
from cim14.iec61970.core import IdentifiedObject
from cim14 import Root
from cim14.iec61970.wires import EnergyConsumer
from cim14.iec61970.core import PowerSystemResource

# <<< imports
# @generated
# >>> imports

class SeasonDayTypeSchedule(RegularIntervalSchedule):
    """ The schedule specialize RegularIntervalSchedule with type curve data for a specific type of day and season. This means that curves of this type cover a 24 hour period.
    """
    # DayType for the Schedule.
    day_type = None

    # Season for the Schedule.
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


class LoadGroup(IdentifiedObject):
    """ The class is the third level in a hierarchical structure for grouping of loads for the purpose of load flow load scaling.
    """
    registered_loads = []

    # The SubLoadArea where the Loadgroup belongs.
    sub_load_area = None

    # <<< load_group
    # @generated
    def __init__(self, registered_loads=[], sub_load_area=None, **kw_args):
        """ Initialises a new 'LoadGroup' instance.
        """
        self.registered_loads = registered_loads
        self.sub_load_area = sub_load_area

        super(LoadGroup, self).__init__(**kw_args)
    # >>> load_group


class Season(Root):
    """ A specified time period of the year, e.g., Spring, Summer, Fall, Winter
    """
    # Date season ends 
    end_date = ''

    # Name of the Season Values are: "spring", "winter", "summer", "fall"
    name = 'spring'

    # Date season starts 
    start_date = ''

    # Schedules that use this Season.
    season_day_type_schedules = []

    # Capacity Benefit Margin may differ based on the season
    capacity_benefit_margin = []

    # Limits may differ based on the season
    violation_limits = []

    # <<< season
    # @generated
    def __init__(self, end_date='', name='spring', start_date='', season_day_type_schedules=[], capacity_benefit_margin=[], violation_limits=[], **kw_args):
        """ Initialises a new 'Season' instance.
        """
        self.end_date = end_date
        self.name = name
        self.start_date = start_date
        self.season_day_type_schedules = season_day_type_schedules
        self.capacity_benefit_margin = capacity_benefit_margin
        self.violation_limits = violation_limits

        super(Season, self).__init__(**kw_args)
    # >>> season


class NonConformLoad(EnergyConsumer):
    """ NonConformLoad represent loads that do not follow a daily load change pattern and changes are not correlated with the daily load change pattern.
    """
    # Group of this ConformLoad.
    load_group = None

    # <<< non_conform_load
    # @generated
    def __init__(self, load_group=None, **kw_args):
        """ Initialises a new 'NonConformLoad' instance.
        """
        self.load_group = load_group

        super(NonConformLoad, self).__init__(**kw_args)
    # >>> non_conform_load


class EnergyArea(IdentifiedObject):
    """ The class describes an area having energy production or consumption. The class is the basis for further specialization.
    """
    # The control area specification that is used for the load forecast.
    control_area = None

    # <<< energy_area
    # @generated
    def __init__(self, control_area=None, **kw_args):
        """ Initialises a new 'EnergyArea' instance.
        """
        self.control_area = control_area

        super(EnergyArea, self).__init__(**kw_args)
    # >>> energy_area


class DayType(IdentifiedObject):
    """ Group of similar days, e.g., Mon/Tue/Wed, Thu/Fri, Sat/Sun, Holiday1, Holiday2
    """
    # Schedules that use this DayType.
    season_day_type_schedules = []

    # <<< day_type
    # @generated
    def __init__(self, season_day_type_schedules=[], **kw_args):
        """ Initialises a new 'DayType' instance.
        """
        self.season_day_type_schedules = season_day_type_schedules

        super(DayType, self).__init__(**kw_args)
    # >>> day_type


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


class ConformLoad(EnergyConsumer):
    """ ConformLoad represent loads that follow a daily load change pattern where the pattern can be used to scale the load with a system load.
    """
    # Group of this ConformLoad.
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
    # Second level (amount) of load to cut as a percentage of total zone load 
    cut_level2 = ''

    # First level (amount) of load to cut as a percentage of total zone load 
    cut_level1 = ''

    # An energy consumer is assigned to a power cut zone
    energy_consumers = []

    # <<< power_cut_zone
    # @generated
    def __init__(self, cut_level2='', cut_level1='', energy_consumers=[], **kw_args):
        """ Initialises a new 'PowerCutZone' instance.
        """
        self.cut_level2 = cut_level2
        self.cut_level1 = cut_level1
        self.energy_consumers = energy_consumers

        super(PowerCutZone, self).__init__(**kw_args)
    # >>> power_cut_zone


class LoadResponseCharacteristic(IdentifiedObject):
    """ Models the characteristic response of the load demand due to to changes in system conditions such as voltage and frequency. This is not related to demand response.
    """
    # Portion of active power load modeled as constant impedance.  Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_impedance = 0.0

    # Portion of reactive power load modeled as constant power. Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_power = 0.0

    # Exponent of per unit voltage effecting real power.   This model used only when 'useExponentModel' is true. 
    p_voltage_exponent = 0.0

    # Portion of reactive power load modeled as constant current. Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_current = 0.0

    # Exponent of per unit frequency effecting reactive power 
    q_frequency_exponent = 0.0

    # Exponent of per unit frequency effecting active power 
    p_frequency_exponent = 0.0

    # Portion of active power load modeled as constant current. Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_current = 0.0

    # Portion of reactive power load modeled as constant impedance.  Used only if the useExponentModel is false.    This value is noralized against the sum of qZ, qI, and qP. 
    q_constant_impedance = 0.0

    # Indicates the exponential voltage dependency model (pVoltateExponent and qVoltageExponent) is to be used.   If false, the coeficient model (consisting of pConstantImpedance, pConstantCurrent, pConstantPower, qConstantImpedance, qConstantCurrent, and qConstantPower) is to be used. 
    exponent_model = False

    # Exponent of per unit voltage effecting reactive power.   This model used only when 'useExponentModel' is true. 
    q_voltage_exponent = 0.0

    # Portion of active power load modeled as constant power. Used only if the useExponentModel is false.    This value is noralized against the sum of pZ, pI, and pP. 
    p_constant_power = 0.0

    # The set of loads that have the response characteristics.
    energy_consumer = []

    # <<< load_response_characteristic
    # @generated
    def __init__(self, p_constant_impedance=0.0, q_constant_power=0.0, p_voltage_exponent=0.0, q_constant_current=0.0, q_frequency_exponent=0.0, p_frequency_exponent=0.0, p_constant_current=0.0, q_constant_impedance=0.0, exponent_model=False, q_voltage_exponent=0.0, p_constant_power=0.0, energy_consumer=[], **kw_args):
        """ Initialises a new 'LoadResponseCharacteristic' instance.
        """
        self.p_constant_impedance = p_constant_impedance
        self.q_constant_power = q_constant_power
        self.p_voltage_exponent = p_voltage_exponent
        self.q_constant_current = q_constant_current
        self.q_frequency_exponent = q_frequency_exponent
        self.p_frequency_exponent = p_frequency_exponent
        self.p_constant_current = p_constant_current
        self.q_constant_impedance = q_constant_impedance
        self.exponent_model = exponent_model
        self.q_voltage_exponent = q_voltage_exponent
        self.p_constant_power = p_constant_power
        self.energy_consumer = energy_consumer

        super(LoadResponseCharacteristic, self).__init__(**kw_args)
    # >>> load_response_characteristic


class ConformLoadGroup(LoadGroup):
    """ Load that follows a daily and seasonal load variation pattern.
    """
    # Conform loads assigned to this ConformLoadGroup.
    energy_consumers = []

    # The ConformLoadSchedules in the ConformLoadGroup.
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


class NonConformLoadSchedule(SeasonDayTypeSchedule):
    """ An active power (Y1-axis) and reactive power (Y2-axis) schedule (curves) versus time (X-axis) for non-conforming loads, e.g., large industrial load or power station service (where modeled)
    """
    # The NonConformLoadGroup where the NonConformLoadSchedule belongs.
    non_conform_load_group = None

    # <<< non_conform_load_schedule
    # @generated
    def __init__(self, non_conform_load_group=None, **kw_args):
        """ Initialises a new 'NonConformLoadSchedule' instance.
        """
        self.non_conform_load_group = non_conform_load_group

        super(NonConformLoadSchedule, self).__init__(**kw_args)
    # >>> non_conform_load_schedule


class SubLoadArea(EnergyArea):
    """ The class is the second level in a hierarchical structure for grouping of loads for the purpose of load flow load scaling.
    """
    # The Loadgroups in the SubLoadArea.
    load_groups = []

    # The LoadArea where the SubLoadArea belongs.
    load_area = None

    # <<< sub_load_area
    # @generated
    def __init__(self, load_groups=[], load_area=None, **kw_args):
        """ Initialises a new 'SubLoadArea' instance.
        """
        self.load_groups = load_groups
        self.load_area = load_area

        super(SubLoadArea, self).__init__(**kw_args)
    # >>> sub_load_area


class ConformLoadSchedule(SeasonDayTypeSchedule):
    """ A curve of load  versus time (X-axis) showing the active power values (Y1-axis) and reactive power (Y2-axis) for each unit of the period covered. This curve represents a typical pattern of load over the time period for a given day type and season.
    """
    # The ConformLoadGroup where the ConformLoadSchedule belongs.
    conform_load_group = None

    # <<< conform_load_schedule
    # @generated
    def __init__(self, conform_load_group=None, **kw_args):
        """ Initialises a new 'ConformLoadSchedule' instance.
        """
        self.conform_load_group = conform_load_group

        super(ConformLoadSchedule, self).__init__(**kw_args)
    # >>> conform_load_schedule


class NonConformLoadGroup(LoadGroup):
    """ Loads that do not follow a daily and seasonal load variation pattern.
    """
    # Conform loads assigned to this ConformLoadGroup.
    energy_consumers = []

    # The NonConformLoadSchedules in the NonConformLoadGroup.
    non_conform_load_schedules = []

    # <<< non_conform_load_group
    # @generated
    def __init__(self, energy_consumers=[], non_conform_load_schedules=[], **kw_args):
        """ Initialises a new 'NonConformLoadGroup' instance.
        """
        self.energy_consumers = energy_consumers
        self.non_conform_load_schedules = non_conform_load_schedules

        super(NonConformLoadGroup, self).__init__(**kw_args)
    # >>> non_conform_load_group


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


# <<< load_model
# @generated
# >>> load_model

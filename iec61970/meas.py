# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970.core import IdentifiedObject
from iec61970 import Element

# <<< imports
# @generated
# >>> imports

class Measurement(IdentifiedObject):
    """ A Measurement represents any measured, calculated or non-measured non-calculated quantity. Any piece of equipment may contain Measurements, e.g. a substation may have temperature measurements and door open indications, a transformer may have oil temperature and tank pressure measurements, a bay may contain a number of power flow measurements and a Breaker may contain a switch status measurement.  The PSR - Measurement association is intended to capture this use of Measurement and is included in the naming hierarchy based on EquipmentContainer. The naming hierarchy typically has Measurements as leafs, e.g. Substation-VoltageLevel-Bay-Switch-Measurement. Some Measurements represent quantities related to a particular sensor location in the network, e.g. a voltage transformer (PT) at a busbar or a current transformer (CT) at the bar between a breaker and an isolator. The sensing position is not captured in the PSR - Measurement association. Instead it is captured by the Measurement - Terminal association that is used to define the sensing location in the network topology. The location is defined by the connection of the Terminal to ConductingEquipment.  Two possible paths exist: 1) Measurement-Terminal- ConnectivityNode-Terminal-ConductingEquipment 2) Measurement-Terminal-ConductingEquipment Alternative 2 is the only allowed use.  When the sensor location is needed both Measurement-PSR and Measurement-Terminal are used. The Measurement-Terminal association is never used alone.
    """
    # One or more measurements may be associated with a terminal in the network. Measurement-Terminal defines where the measurement is placed in the network topology. Some Measurements represent quantities related to a particular sensor position, e.g. a voltage transformer (PT) at a busbar or a current transformer (CT) at the bar between a breaker and an isolator. The sensing position is captured by the Measurement - Terminal association that makes it possible to place the sensing position at a  well defined place. The place is defined by the connection of the Terminal to ConductingEquipment.
    terminal = None

    # Measurement-PSR defines the measurements in the naming hierarchy.
    member_of_psr = None

    unit = None

    # A measurement has a measurement type.
    measurement_type = None

    # <<< measurement
    # @generated
    def __init__(self, terminal=None, member_of_psr=None, unit=None, measurement_type=None, **kw_args):
        """ Initialises a new 'Measurement' instance.
        """
        self.terminal = terminal
        self.member_of_psr = member_of_psr
        self.unit = unit
        self.measurement_type = measurement_type

        super(Measurement, self).__init__(**kw_args)
    # >>> measurement


class Control(IdentifiedObject):
    """ Control is used for supervisory/device control. It represents control outputs that are used to change the state in a process, e.g. close or open breaker, a set point value or a raise lower command.
    """
    # Indicates that a client is currently sending control commands that has not completed 
    operation_in_progress = False

    # The last time a control output was sent 
    time_stamp = ''

    unit = None

    control_type = None

    remote_control = None

    # The association gives the control output that is used to actually govern a regulating device, e.g. the magnetization of a synchronous machine or capacitor bank breaker actuators.
    controlled_by_regulating_cond_eq = None

    # <<< control
    # @generated
    def __init__(self, operation_in_progress=False, time_stamp='', unit=None, control_type=None, remote_control=None, controlled_by_regulating_cond_eq=None, **kw_args):
        """ Initialises a new 'Control' instance.
        """
        self.operation_in_progress = operation_in_progress
        self.time_stamp = time_stamp
        self.unit = unit
        self.control_type = control_type
        self.remote_control = remote_control
        self.controlled_by_regulating_cond_eq = controlled_by_regulating_cond_eq

        super(Control, self).__init__(**kw_args)
    # >>> control


class ControlType(IdentifiedObject):
    """ Specifies the type of Control, e.g. BreakerOn/Off, GeneratorVoltageSetPoint, TieLineFlow etc. The ControlType.name shall be unique among all specified types and describe the type. The ControlType.aliasName is meant to be used for localization.
    """
    controls = []

    # <<< control_type
    # @generated
    def __init__(self, controls=[], **kw_args):
        """ Initialises a new 'ControlType' instance.
        """
        self.controls = controls

        super(ControlType, self).__init__(**kw_args)
    # >>> control_type


class LimitSet(IdentifiedObject):
    """ Specifies a set of Limits that are associated with a Measurement. A Measurement may have several LimitSets corresponding to seasonal or other changing conditions. The condition is captured in the name and description attributes. The same LimitSet may be used for several Measurements. In particular percentage limits are used this way.
    """
    # Tells if the limit values are in percentage of normalValue or the specified Unit for Measurements and Controls. 
    is_percentage_limits = False

    # <<< limit_set
    # @generated
    def __init__(self, is_percentage_limits=False, **kw_args):
        """ Initialises a new 'LimitSet' instance.
        """
        self.is_percentage_limits = is_percentage_limits

        super(LimitSet, self).__init__(**kw_args)
    # >>> limit_set


class MeasurementValue(IdentifiedObject):
    """ The current state for a measurement. A state value is an instance of a measurement from a specific source. Measurements can be associated with many state values, each representing a different source for the measurement.
    """
    # The limit, expressed as a percentage of the sensor maximum, that errors will not exceed when the sensor is used under  reference conditions. 
    sensor_accuracy = 0.0

    # The time when the value was last updated 
    time_stamp = ''

    # A MeasurementValue has a MeasurementValueQuality associated with it.
    measurement_value_quality = None

    # Links to the physical telemetered point associated with this measurement.
    remote_source = None

    measurement_value_source = None

    # <<< measurement_value
    # @generated
    def __init__(self, sensor_accuracy=0.0, time_stamp='', measurement_value_quality=None, remote_source=None, measurement_value_source=None, **kw_args):
        """ Initialises a new 'MeasurementValue' instance.
        """
        self.sensor_accuracy = sensor_accuracy
        self.time_stamp = time_stamp
        self.measurement_value_quality = measurement_value_quality
        self.remote_source = remote_source
        self.measurement_value_source = measurement_value_source

        super(MeasurementValue, self).__init__(**kw_args)
    # >>> measurement_value


class ValueAliasSet(IdentifiedObject):
    """ Describes the translation of a set of values into a name and is intendend to facilitate cusom translations. Each ValueAliasSet has a name, description etc. A specific Measurement may represent a discrete state like Open, Closed, Intermediate etc. This requires a translation from the MeasurementValue.value number to a string, e.g. 0->'Invalid', 1->'Open', 2->'Closed', 3->'Intermediate'. Each ValueToAlias member in ValueAliasSet.Value describe a mapping for one particular value to a name.
    """
    values = []

    commands = []

    measurements = []

    # <<< value_alias_set
    # @generated
    def __init__(self, values=[], commands=[], measurements=[], **kw_args):
        """ Initialises a new 'ValueAliasSet' instance.
        """
        self.values = values
        self.commands = commands
        self.measurements = measurements

        super(ValueAliasSet, self).__init__(**kw_args)
    # >>> value_alias_set


class Quality61850(Element):
    """ Quality flags in this class are as defined in IEC 61850, except for estimatorReplaced, which has been included in this class for convenience.
    """
    # Measurement value is beyond a predefined range of value. 
    out_of_range = False

    # Measurement value is beyond the capability of being  represented properly. For example, a counter value overflows from maximum count back to a value of zero. 
    over_flow = False

    # Measurement value is blocked and hence unavailable for transmission. 
    operator_blocked = False

    # A correlation function has detected that the value is not consitent with other values. Typically set by a network State Estimator. 
    suspect = False

    # To prevent some overload of the communication it is sensible to detect and suppress oscillating (fast changing) binary inputs. If a signal changes in a defined time (tosc) twice in the same direction (from 0 to 1 or from 1 to 0) then oscillation is detected and the detail quality identifier 'oscillatory' is set. If it is detected a configured numbers of transient changes could be passed by. In this time the validity status 'questionable' is set. If after this defined numbers of changes the signal is still in the oscillating state the value shall be set either to the opposite state of the previous stable value or to a defined default value. In this case the validity status 'questionable' is reset and 'invalid' is set as long as the signal is oscillating. If it is configured such that no transient changes should be passed by then the validity status 'invalid' is set immediately in addition to the detail quality identifier 'oscillatory' (used for status information only). 
    oscillatory = False

    # Measurement value is transmitted for test purposes. 
    test = False

    # Value has been replaced by State Estimator. estimatorReplaced is not an IEC61850 quality bit but has been put in this class for convenience. 
    estimator_replaced = False

    # Source gives information related to the origin of a value. The value may be acquired from the process, defaulted or substituted. Values are: "defaulted", "substituted", "process"
    source = 'defaulted'

    # This identifier indicates that a supervision function has detected an internal or external failure, e.g. communication failure. 
    failure = False

    # Measurement value is old and possibly invalid, as it has not been successfully updated during a specified time interval. 
    old_data = False

    # Measurement value may be incorrect due to a reference being out of calibration. 
    bad_reference = False

    # Validity of the measurement value. Values are: "good", "questionable", "invalid"
    validity = 'good'

    # <<< quality61850
    # @generated
    def __init__(self, out_of_range=False, over_flow=False, operator_blocked=False, suspect=False, oscillatory=False, test=False, estimator_replaced=False, source='defaulted', failure=False, old_data=False, bad_reference=False, validity='good', **kw_args):
        """ Initialises a new 'Quality61850' instance.
        """
        self.out_of_range = out_of_range
        self.over_flow = over_flow
        self.operator_blocked = operator_blocked
        self.suspect = suspect
        self.oscillatory = oscillatory
        self.test = test
        self.estimator_replaced = estimator_replaced
        self.source = source
        self.failure = failure
        self.old_data = old_data
        self.bad_reference = bad_reference
        self.validity = validity

        super(Quality61850, self).__init__(**kw_args)
    # >>> quality61850


class Limit(IdentifiedObject):
    """ Specifies one limit value for a Measurement. A Measurement typically has several limits that are kept together by the LimitSet class. The actual meaning and use of a Limit instance (i.e., if it is an alarm or warning limit or if it is a high or low limit) is not captured in the Limit class. However the name of a Limit instance may indicate both meaning and use.
    """
    pass
    # <<< limit
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Limit' instance.
        """

        super(Limit, self).__init__(**kw_args)
    # >>> limit


class MeasurementType(IdentifiedObject):
    """ Specifies the type of Measurement, e.g. IndoorTemperature, OutDoorTemperature, BusVoltage, GeneratorVoltage, LineFlow etc. The MeasurementType.name shall be unique among all specified types and describe the type. The MeasurementType.aliasName is meant to be used for localization.
    """
    # A measurement has a measurement type.
    measurements = []

    # <<< measurement_type
    # @generated
    def __init__(self, measurements=[], **kw_args):
        """ Initialises a new 'MeasurementType' instance.
        """
        self.measurements = measurements

        super(MeasurementType, self).__init__(**kw_args)
    # >>> measurement_type


class ValueToAlias(IdentifiedObject):
    """ Describes the translation of one particular value into a name, e.g. 1->'Open'
    """
    # The value that is mapped 
    value = 0

    value_alias_set = None

    # <<< value_to_alias
    # @generated
    def __init__(self, value=0, value_alias_set=None, **kw_args):
        """ Initialises a new 'ValueToAlias' instance.
        """
        self.value = value
        self.value_alias_set = value_alias_set

        super(ValueToAlias, self).__init__(**kw_args)
    # >>> value_to_alias


class MeasurementValueSource(IdentifiedObject):
    """ MeasurementValueSource describes the alternative sources updating a MeasurementValue. User conventions for how to use the MeasurementValueSource attributes are described in the introduction to IEC 61970-301.
    """
    measurement_values = []

    # <<< measurement_value_source
    # @generated
    def __init__(self, measurement_values=[], **kw_args):
        """ Initialises a new 'MeasurementValueSource' instance.
        """
        self.measurement_values = measurement_values

        super(MeasurementValueSource, self).__init__(**kw_args)
    # >>> measurement_value_source


class Discrete(Measurement):
    """ Discrete represents a discrete Measurement, i.e. a Measurement reprsenting discrete values, e.g. a Breaker position.
    """
    # Normal value range maximum for any of the MeasurementValue.values. Used for scaling, e.g. in bar graphs or of telemetered raw values. 
    max_value = 0

    # Normal value range minimum for any of the MeasurementValue.values. Used for scaling, e.g. in bar graphs or of telemetered raw values 
    min_value = 0

    # Normal measurement value, e.g., used for percentage calculations. 
    normal_value = 0

    value_alias_set = None

    controlled_by_control = None

    contain_measurement_values = []

    # <<< discrete
    # @generated
    def __init__(self, max_value=0, min_value=0, normal_value=0, value_alias_set=None, controlled_by_control=None, contain_measurement_values=[], **kw_args):
        """ Initialises a new 'Discrete' instance.
        """
        self.max_value = max_value
        self.min_value = min_value
        self.normal_value = normal_value
        self.value_alias_set = value_alias_set
        self.controlled_by_control = controlled_by_control
        self.contain_measurement_values = contain_measurement_values

        super(Discrete, self).__init__(**kw_args)
    # >>> discrete


class SetPoint(Control):
    """ A SetPoint is an analog control used for supervisory control.
    """
    # Normal value range maximum for any of the Control.value. Used for scaling, e.g. in bar graphs. 
    max_value = 0.0

    # Normal value for Control.value e.g. used for percentage scaling 
    normal_value = 0.0

    # Normal value range minimum for any of the Control.value. Used for scaling, e.g. in bar graphs. 
    min_value = 0.0

    # The value representing the actuator output 
    value = 0.0

    # The Control variable associated with the Measurement
    measured_by_measurement = None

    # <<< set_point
    # @generated
    def __init__(self, max_value=0.0, normal_value=0.0, min_value=0.0, value=0.0, measured_by_measurement=None, **kw_args):
        """ Initialises a new 'SetPoint' instance.
        """
        self.max_value = max_value
        self.normal_value = normal_value
        self.min_value = min_value
        self.value = value
        self.measured_by_measurement = measured_by_measurement

        super(SetPoint, self).__init__(**kw_args)
    # >>> set_point


class DiscreteValue(MeasurementValue):
    """ DiscreteValue represents a discrete MeasurementValue.
    """
    # The value to supervise. 
    value = 0

    member_of_measurement = None

    # <<< discrete_value
    # @generated
    def __init__(self, value=0, member_of_measurement=None, **kw_args):
        """ Initialises a new 'DiscreteValue' instance.
        """
        self.value = value
        self.member_of_measurement = member_of_measurement

        super(DiscreteValue, self).__init__(**kw_args)
    # >>> discrete_value


class Accumulator(Measurement):
    """ Accumulator represents a accumulated (counted) Measurement, e.g. an energy value.
    """
    # Normal value range maximum for any of the MeasurementValue.values. Used for scaling, e.g. in bar graphs or of telemetered raw values. 
    max_value = 0

    contain_measurement_values = []

    # A measurement may have zero or more limit ranges defined for it.
    limit_sets = []

    # <<< accumulator
    # @generated
    def __init__(self, max_value=0, contain_measurement_values=[], limit_sets=[], **kw_args):
        """ Initialises a new 'Accumulator' instance.
        """
        self.max_value = max_value
        self.contain_measurement_values = contain_measurement_values
        self.limit_sets = limit_sets

        super(Accumulator, self).__init__(**kw_args)
    # >>> accumulator


class AnalogLimit(Limit):
    """ Limit values for Analog measurements
    """
    # The value to supervise against. 
    value = 0.0

    limit_set = None

    # <<< analog_limit
    # @generated
    def __init__(self, value=0.0, limit_set=None, **kw_args):
        """ Initialises a new 'AnalogLimit' instance.
        """
        self.value = value
        self.limit_set = limit_set

        super(AnalogLimit, self).__init__(**kw_args)
    # >>> analog_limit


class StringMeasurementValue(MeasurementValue):
    """ StringMeasurementValue represents a measurement value of type string.
    """
    # The value to supervise. 
    value = ''

    member_of_measurement = None

    # <<< string_measurement_value
    # @generated
    def __init__(self, value='', member_of_measurement=None, **kw_args):
        """ Initialises a new 'StringMeasurementValue' instance.
        """
        self.value = value
        self.member_of_measurement = member_of_measurement

        super(StringMeasurementValue, self).__init__(**kw_args)
    # >>> string_measurement_value


class AccumulatorLimit(Limit):
    """ Limit values for Accumulator measurements
    """
    # The value to supervise against. The value is positive. 
    value = 0

    limit_set = None

    # <<< accumulator_limit
    # @generated
    def __init__(self, value=0, limit_set=None, **kw_args):
        """ Initialises a new 'AccumulatorLimit' instance.
        """
        self.value = value
        self.limit_set = limit_set

        super(AccumulatorLimit, self).__init__(**kw_args)
    # >>> accumulator_limit


class StringMeasurement(Measurement):
    """ StringMeasurement represents a measurement with values of type string.
    """
    contains_measurement_values = []

    # <<< string_measurement
    # @generated
    def __init__(self, contains_measurement_values=[], **kw_args):
        """ Initialises a new 'StringMeasurement' instance.
        """
        self.contains_measurement_values = contains_measurement_values

        super(StringMeasurement, self).__init__(**kw_args)
    # >>> string_measurement


class AnalogValue(MeasurementValue):
    """ AnalogValue represents an analog MeasurementValue.
    """
    # The value to supervise. 
    value = 0.0

    member_of_measurement = None

    alt_generating_unit = []

    alt_tie_meas = []

    # <<< analog_value
    # @generated
    def __init__(self, value=0.0, member_of_measurement=None, alt_generating_unit=[], alt_tie_meas=[], **kw_args):
        """ Initialises a new 'AnalogValue' instance.
        """
        self.value = value
        self.member_of_measurement = member_of_measurement
        self.alt_generating_unit = alt_generating_unit
        self.alt_tie_meas = alt_tie_meas

        super(AnalogValue, self).__init__(**kw_args)
    # >>> analog_value


class MeasurementValueQuality(Quality61850):
    """ Measurement quality flags. Bits 0-10 are defined for substation automation in draft IEC 61850 part 7-3. Bits 11-15 are reserved for future expansion by that document. Bits 16-31 are reserved for EMS applications.
    """
    # A MeasurementValue has a MeasurementValueQuality associated with it.
    measurement_value = None

    # <<< measurement_value_quality
    # @generated
    def __init__(self, measurement_value=None, **kw_args):
        """ Initialises a new 'MeasurementValueQuality' instance.
        """
        self.measurement_value = measurement_value

        super(MeasurementValueQuality, self).__init__(**kw_args)
    # >>> measurement_value_quality


class AccumulatorLimitSet(LimitSet):
    """ An AccumulatorLimitSet specifies a set of Limits that are associated with an Accumulator measurement.
    """
    # A measurement may have zero or more limit ranges defined for it.
    measurements = []

    limits = []

    # <<< accumulator_limit_set
    # @generated
    def __init__(self, measurements=[], limits=[], **kw_args):
        """ Initialises a new 'AccumulatorLimitSet' instance.
        """
        self.measurements = measurements
        self.limits = limits

        super(AccumulatorLimitSet, self).__init__(**kw_args)
    # >>> accumulator_limit_set


class AnalogLimitSet(LimitSet):
    """ An AnalogLimitSet specifies a set of Limits that are associated with an Analog measurement.
    """
    limits = []

    # A measurement may have zero or more limit ranges defined for it.
    measurements = []

    # <<< analog_limit_set
    # @generated
    def __init__(self, limits=[], measurements=[], **kw_args):
        """ Initialises a new 'AnalogLimitSet' instance.
        """
        self.limits = limits
        self.measurements = measurements

        super(AnalogLimitSet, self).__init__(**kw_args)
    # >>> analog_limit_set


class Command(Control):
    """ A Command is a discrete control used for supervisory control.
    """
    # The value representing the actuator output 
    value = 0

    # Normal value for Control.value e.g. used for percentage scaling 
    normal_value = 0

    value_alias_set = None

    measured_by_measurement = None

    # <<< command
    # @generated
    def __init__(self, value=0, normal_value=0, value_alias_set=None, measured_by_measurement=None, **kw_args):
        """ Initialises a new 'Command' instance.
        """
        self.value = value
        self.normal_value = normal_value
        self.value_alias_set = value_alias_set
        self.measured_by_measurement = measured_by_measurement

        super(Command, self).__init__(**kw_args)
    # >>> command


class Analog(Measurement):
    """ Analog represents an analog Measurement.
    """
    # If true then this measurement is an active power, reactive power or current with the convention that a positive value measured at the Terminal means power is flowing into the related PowerSystemResource. 
    positive_flow_in = False

    # Normal measurement value, e.g., used for percentage calculations. 
    normal_value = 0.0

    # Normal value range minimum for any of the MeasurementValue.values. Used for scaling, e.g. in bar graphs or of telemetered raw values 
    min_value = 0.0

    # Normal value range maximum for any of the MeasurementValue.values. Used for scaling, e.g. in bar graphs or of telemetered raw values. 
    max_value = 0.0

    contain_measurement_values = []

    # A measurement may have zero or more limit ranges defined for it.
    limit_sets = []

    # The Control variable associated with the Measurement
    controlled_by_control = None

    # <<< analog
    # @generated
    def __init__(self, positive_flow_in=False, normal_value=0.0, min_value=0.0, max_value=0.0, contain_measurement_values=[], limit_sets=[], controlled_by_control=None, **kw_args):
        """ Initialises a new 'Analog' instance.
        """
        self.positive_flow_in = positive_flow_in
        self.normal_value = normal_value
        self.min_value = min_value
        self.max_value = max_value
        self.contain_measurement_values = contain_measurement_values
        self.limit_sets = limit_sets
        self.controlled_by_control = controlled_by_control

        super(Analog, self).__init__(**kw_args)
    # >>> analog


class AccumulatorValue(MeasurementValue):
    """ AccumulatorValue represents a accumulated (counted) MeasurementValue.
    """
    # The value to supervise. The value is positive. 
    value = 0

    member_of_measurement = None

    # <<< accumulator_value
    # @generated
    def __init__(self, value=0, member_of_measurement=None, **kw_args):
        """ Initialises a new 'AccumulatorValue' instance.
        """
        self.value = value
        self.member_of_measurement = member_of_measurement

        super(AccumulatorValue, self).__init__(**kw_args)
    # >>> accumulator_value


# <<< meas
# @generated
# >>> meas

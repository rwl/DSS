# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.work import Work
from cim14 import Root
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import ActivityRecord
from cim14.iec61968.common import Location
from cim14.iec61968.assets import AssetFunction
from cim14.iec61970.meas import MeasurementValue
from cim14.iec61968.assets import AssetContainer

# <<< imports
# @generated
# >>> imports

class MeterServiceWork(Work):
    """ Work involving meters.
    """
    # Old meter asset replaced by this work.
    old_meter_asset = None

    # Meter asset on which this non-replacement work is performed.
    meter_asset = None

    # <<< meter_service_work
    # @generated
    def __init__(self, old_meter_asset=None, meter_asset=None, **kw_args):
        """ Initialises a new 'MeterServiceWork' instance.
        """
        self.old_meter_asset = old_meter_asset
        self.meter_asset = meter_asset

        super(MeterServiceWork, self).__init__(**kw_args)
    # >>> meter_service_work


class IntervalBlock(Root):
    """ Time sequence of Readings of the same ReadingType. Contained IntervalReadings may need conversion through the application of an offset and a scalar defined in associated Pending.
    """
    # Meter reading containing this interval block.
    meter_reading = None

    # Pending conversion to apply to interval reading values contained by this block (after which the resulting reading type is different than the original because it reflects the conversion result).
    pending = None

    # Type information for interval reading values contained in this block.
    reading_type = None

    # Interval reading contained in this block.
    interval_readings = []

    # <<< interval_block
    # @generated
    def __init__(self, meter_reading=None, pending=None, reading_type=None, interval_readings=[], **kw_args):
        """ Initialises a new 'IntervalBlock' instance.
        """
        self.meter_reading = meter_reading
        self.pending = pending
        self.reading_type = reading_type
        self.interval_readings = interval_readings

        super(IntervalBlock, self).__init__(**kw_args)
    # >>> interval_block


class ReadingQuality(Root):
    """ Quality of a specific reading value or interval reading value. Note that more than one Quality may be applicable to a given Reading. Typically not unsed unless problems or unusual conditions occur (i.e., quality for each Reading is assumed to be 'Good' unless stated otherwise in associated ReadingQuality).
    """
    # Quality, to be specified if different than 'Good'. 
    quality = ''

    # Interval reading value to which this quality applies.
    interval_reading = None

    # Reading value to which this quality applies.
    reading = None

    # <<< reading_quality
    # @generated
    def __init__(self, quality='', interval_reading=None, reading=None, **kw_args):
        """ Initialises a new 'ReadingQuality' instance.
        """
        self.quality = quality
        self.interval_reading = interval_reading
        self.reading = reading

        super(ReadingQuality, self).__init__(**kw_args)
    # >>> reading_quality


class EndDeviceGroup(IdentifiedObject):
    """ Abstraction for management of group communications within a two-way AMR system or the data for a group of related meters. Commands can be issued to all of the meters that belong to a meter group using a defined group address and the underlying AMR communication infrastructure.
    """
    # Address of this end device group. 
    group_address = 0

    # All end device assets this end device group refers to.
    end_device_assets = []

    # All end device controls sending commands to this end device group.
    end_device_controls = []

    # Demand response program for this group of end devices.
    demand_response_program = None

    # <<< end_device_group
    # @generated
    def __init__(self, group_address=0, end_device_assets=[], end_device_controls=[], demand_response_program=None, **kw_args):
        """ Initialises a new 'EndDeviceGroup' instance.
        """
        self.group_address = group_address
        self.end_device_assets = end_device_assets
        self.end_device_controls = end_device_controls
        self.demand_response_program = demand_response_program

        super(EndDeviceGroup, self).__init__(**kw_args)
    # >>> end_device_group


class Register(IdentifiedObject):
    """ Display for quantity that is metered on an end device such as a meter.
    """
    # Number of digits (dials on a mechanical meter) to the left of the decimal place; default is 5. 
    left_digit_count = 0

    # Number of digits (dials on a mechanical meter) to the right of the decimal place. 
    right_digit_count = 0

    # Device function metering quantities displayed by this register.
    device_function = None

    # Reading type for values displayed by this register.
    reading_type = None

    # <<< register
    # @generated
    def __init__(self, left_digit_count=0, right_digit_count=0, device_function=None, reading_type=None, **kw_args):
        """ Initialises a new 'Register' instance.
        """
        self.left_digit_count = left_digit_count
        self.right_digit_count = right_digit_count
        self.device_function = device_function
        self.reading_type = reading_type

        super(Register, self).__init__(**kw_args)
    # >>> register


class ServiceDeliveryPoint(IdentifiedObject):
    """ Logical point on the network where the ownership of the service changes hands. It is one of potentially many service points within a ServiceLocation, delivering service in accordance with a CustomerAgreement. Used at the place where a meter may be installed.
    """
    # Remarks about this service delivery point, for example the reason for it being rated with a non-nominal priority. 
    service_delivery_remark = ''

    # Current that this service delivery point is configured to deliver. 
    rated_current = ''

    # Power that this service delivery point is configured to deliver. 
    rated_power = ''

    # Nominal service voltage. 
    nominal_service_voltage = 0

    # Budget bill code. 
    budget_bill = ''

    # Estimated load. 
    estimated_load = ''

    # Load management code. 
    load_mgmt = ''

    # Phase configuration kind. Values are: "two_phase_two_wire", "other", "two_phase_three_wire", "three_phase_four_wire", "three_phase_two_wire", "one_phase_three_wire", "three_phase_three_wire", "one_phase_two_wire"
    phase_config = 'two_phase_two_wire'

    # True if as a result of an inspection or otherwise, there is a reason to suspect that a previous billing may have been performed with erroneous data. Value should be reset once this potential discrepancy has been resolved. 
    check_billing = False

    # Billing cycle. 
    billing_cycle = ''

    # True if grounded. 
    grounded = False

    # Cumulative totalizing register of consumed service at this service delivery point over its lifetime. 
    consumption_real_energy = ''

    # Priority of service for this service delivery point. Note that service delivery points at the same service location can have different priorities. 
    service_priority = ''

    # (optional for medium voltage connections) Reference to the low side terminal of a CT or PT that obtain readings from a medium or high voltage point. 
    ctpt_reference = 0

    # All meter readings obtained from this service delivery point.
    meter_readings = []

    # All locations of this service delivery point.
    sdplocations = []

    # Service category delivered by this service delivery point.
    service_category = None

    # Service location where the service delivered by this service delivery point is consumed.
    service_location = None

    # ServiceSupplier (Utility) utilising this service delivery point to deliver a service.
    service_supplier = None

    # All pricing structures applicable to this service delivery point (with prepayment meter running as a stand-alone device, with no CustomerAgreement or Customer).
    pricing_structures = []

    # All end device assets at this service delivery point.
    end_device_assets = []

    power_quality_pricings = []

    # Customer agreement regulating this service delivery point.
    customer_agreement = None

    energy_consumer = None

    # <<< service_delivery_point
    # @generated
    def __init__(self, service_delivery_remark='', rated_current='', rated_power='', nominal_service_voltage=0, budget_bill='', estimated_load='', load_mgmt='', phase_config='two_phase_two_wire', check_billing=False, billing_cycle='', grounded=False, consumption_real_energy='', service_priority='', ctpt_reference=0, meter_readings=[], sdplocations=[], service_category=None, service_location=None, service_supplier=None, pricing_structures=[], end_device_assets=[], power_quality_pricings=[], customer_agreement=None, energy_consumer=None, **kw_args):
        """ Initialises a new 'ServiceDeliveryPoint' instance.
        """
        self.service_delivery_remark = service_delivery_remark
        self.rated_current = rated_current
        self.rated_power = rated_power
        self.nominal_service_voltage = nominal_service_voltage
        self.budget_bill = budget_bill
        self.estimated_load = estimated_load
        self.load_mgmt = load_mgmt
        self.phase_config = phase_config
        self.check_billing = check_billing
        self.billing_cycle = billing_cycle
        self.grounded = grounded
        self.consumption_real_energy = consumption_real_energy
        self.service_priority = service_priority
        self.ctpt_reference = ctpt_reference
        self.meter_readings = meter_readings
        self.sdplocations = sdplocations
        self.service_category = service_category
        self.service_location = service_location
        self.service_supplier = service_supplier
        self.pricing_structures = pricing_structures
        self.end_device_assets = end_device_assets
        self.power_quality_pricings = power_quality_pricings
        self.customer_agreement = customer_agreement
        self.energy_consumer = energy_consumer

        super(ServiceDeliveryPoint, self).__init__(**kw_args)
    # >>> service_delivery_point


class MeterReading(IdentifiedObject):
    """ Set of values obtained from the meter.
    """
    # Date and time interval of the data items contained within this meter reading.
    values_interval = None

    # Service delivery point from which this meter reading (set of values) has been obtained.
    service_delivery_point = None

    # (could be deprecated in the future) Customer agreement for this meter reading.
    customer_agreement = None

    # All reading values contained within this meter reading.
    readings = []

    # All interval blocks contained in this meter reading.
    interval_blocks = []

    # All end device events associated with this set of measured values.
    end_device_events = []

    # Meter asset providing this meter reading.
    meter_asset = None

    # <<< meter_reading
    # @generated
    def __init__(self, values_interval=None, service_delivery_point=None, customer_agreement=None, readings=[], interval_blocks=[], end_device_events=[], meter_asset=None, **kw_args):
        """ Initialises a new 'MeterReading' instance.
        """
        self.values_interval = values_interval
        self.service_delivery_point = service_delivery_point
        self.customer_agreement = customer_agreement
        self.readings = readings
        self.interval_blocks = interval_blocks
        self.end_device_events = end_device_events
        self.meter_asset = meter_asset

        super(MeterReading, self).__init__(**kw_args)
    # >>> meter_reading


class EndDeviceControl(IdentifiedObject):
    """ Instructs an EndDeviceAsset (or EndDeviceGroup) to perform a specified action.
    """
    # Type of control. 
    type = ''

    # Level of a demand response program request, where 0=emergency. Note: Attribute is not defined on DemandResponseProgram as it is not its inherent property (it serves to conrol it). 
    dr_program_level = 0

    # Whether a demand response program request is mandatory. Note: Attribute is not defined on DemandResponseProgram as it is not its inherent property (it serves to conrol it). 
    dr_program_mandatory = False

    # (if applicable) Price signal used as parameter for this end device control. 
    price_signal = ''

    # (if control has scheduled duration) Date and time interval the control has been scheduled to execute within.
    scheduled_interval = None

    # Demand response program for this end device control.
    demand_response_program = None

    # Could be deprecated in the future.
    customer_agreement = None

    # End device asset receiving commands from this end device control.
    end_device_asset = None

    # End device group receiving commands from this end device control.
    end_device_group = None

    # <<< end_device_control
    # @generated
    def __init__(self, type='', dr_program_level=0, dr_program_mandatory=False, price_signal='', scheduled_interval=None, demand_response_program=None, customer_agreement=None, end_device_asset=None, end_device_group=None, **kw_args):
        """ Initialises a new 'EndDeviceControl' instance.
        """
        self.type = type
        self.dr_program_level = dr_program_level
        self.dr_program_mandatory = dr_program_mandatory
        self.price_signal = price_signal
        self.scheduled_interval = scheduled_interval
        self.demand_response_program = demand_response_program
        self.customer_agreement = customer_agreement
        self.end_device_asset = end_device_asset
        self.end_device_group = end_device_group

        super(EndDeviceControl, self).__init__(**kw_args)
    # >>> end_device_control


class ReadingType(IdentifiedObject):
    """ Type of data conveyed by a specific Reading.
    """
    # Logical positioning of this measurement data. 
    channel_number = 0

    # Unit for the reading value. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
    unit = '_c'

    # True for systems that must operate in 'reverse' chronological order. 
    reverse_chronology = False

    # Demand configuration such as block, rolling, logarithmic and sizes such as 15 minutes, 30 minutes, 5 minutes subinterval. 
    dynamic_configuration = ''

    # Multiplier for 'unit'. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    multiplier = 'micro'

    # (if incremental reading value) Length of increment interval. 
    interval_length = ''

    # Numeric type to be expected for the associated IntervalBlock.value (e.g. unsignedInteger). 
    default_value_data_type = ''

    # Kind of reading. Values are: "other", "volume", "phase_angle", "demand", "voltage", "voltage_angle", "energy", "power_factor", "pressure", "current_angle", "time", "current", "power", "date"
    kind = 'other'

    # Characteristics of a data value conveyed by a specific Reading, which allow an application to understand how a specific Reading is to be interpreted. 
    default_quality = ''

    # All blocks containing interval reading values with this type information.
    interval_blocks = []

    # Pending conversion that produced this reading type.
    pending = None

    # All reading values with this type information.
    readings = []

    # Register displaying values with this type information.
    register = None

    # <<< reading_type
    # @generated
    def __init__(self, channel_number=0, unit='_c', reverse_chronology=False, dynamic_configuration='', multiplier='micro', interval_length='', default_value_data_type='', kind='other', default_quality='', interval_blocks=[], pending=None, readings=[], register=None, **kw_args):
        """ Initialises a new 'ReadingType' instance.
        """
        self.channel_number = channel_number
        self.unit = unit
        self.reverse_chronology = reverse_chronology
        self.dynamic_configuration = dynamic_configuration
        self.multiplier = multiplier
        self.interval_length = interval_length
        self.default_value_data_type = default_value_data_type
        self.kind = kind
        self.default_quality = default_quality
        self.interval_blocks = interval_blocks
        self.pending = pending
        self.readings = readings
        self.register = register

        super(ReadingType, self).__init__(**kw_args)
    # >>> reading_type


class EndDeviceEvent(ActivityRecord):
    """ Event detected by a DeviceFunction associated with EndDeviceAsset.
    """
    # (if user initiated) ID of user who initiated this end device event. 
    user_id = ''

    # Device function that reported this end device event.
    device_function = None

    # Set of measured values to which this event applies.
    meter_reading = None

    # <<< end_device_event
    # @generated
    def __init__(self, user_id='', device_function=None, meter_reading=None, **kw_args):
        """ Initialises a new 'EndDeviceEvent' instance.
        """
        self.user_id = user_id
        self.device_function = device_function
        self.meter_reading = meter_reading

        super(EndDeviceEvent, self).__init__(**kw_args)
    # >>> end_device_event


class SDPLocation(Location):
    """ Location of an individual service delivery point. For residential or most businesses, it is typically the location of a meter on the customer's premises. For transmission, it is the point(s) of interconnection on the transmission provider's transmission system where capacity and/or energy transmitted by the transmission provider is made available to the receiving party. The point(s) of delivery is specified in the Service Agreement.
    """
    # Method for the service person to access this service delivery point location. For example, a description of where to obtain a key if the facility is unmanned and secured. 
    access_method = ''

    # Problems previously encountered when visiting or performing work at this service delivery point location. Examples include: bad dog, violent customer, verbally abusive occupant, obstructions, safety hazards, etc. 
    site_access_problem = ''

    # Date when certificate of occupancy was provided for this location, 0 if valid certificate of occupancy does not exist for (inherited) 'Location.corporateCode'. 
    occupancy_date = ''

    # Remarks about this location. 
    remark = ''

    # All service delivery points at this location.
    service_delivery_points = []

    # <<< sdplocation
    # @generated
    def __init__(self, access_method='', site_access_problem='', occupancy_date='', remark='', service_delivery_points=[], **kw_args):
        """ Initialises a new 'SDPLocation' instance.
        """
        self.access_method = access_method
        self.site_access_problem = site_access_problem
        self.occupancy_date = occupancy_date
        self.remark = remark
        self.service_delivery_points = service_delivery_points

        super(SDPLocation, self).__init__(**kw_args)
    # >>> sdplocation


class Pending(Root):
    """ When present, a scalar conversion that is associatied with IntervalBlock and which needs to be applied to every contained IntervalReading value. This conversion results in a new associated ReadingType, reflecting the true dimensions of interval reading values after the conversion.
    """
    # (if applicable) Offset to be added as well as multiplication using scalars. 
    offset = 0

    # (if scalar is floating number) When multiplied with 'IntervalReading.value', it causes a unit of measure conversion to occur, resulting in the 'ReadingType.unit'. 
    scalar_float = 0.0

    # (if scalar is integer or rational number)  When the scalar is a simple integer, and this attribute is presented alone and multiplied with 'IntervalReading.value', it causes a unit of measure conversion to occur, resulting in the 'ReadingType.unit'. It is never used in conjuction with 'scalarFloat', only with 'scalarDenominator'. 
    scalar_numerator = 0

    # Whether scalars should be applied before adding the 'offset'. 
    multiply_before_add = False

    # (if scalar is rational number) When 'IntervalReading.value' is multiplied by this attribute and divided by 'scalarDenominator, it causes a unit of measure conversion to occur, resulting in the 'ReadingType.unit'. 
    scalar_denominator = 0

    # Reading type resulting from this pending conversion.
    reading_type = None

    # All blocks of interval reading values to which this pending conversion applies.
    interval_blocks = []

    # <<< pending
    # @generated
    def __init__(self, offset=0, scalar_float=0.0, scalar_numerator=0, multiply_before_add=False, scalar_denominator=0, reading_type=None, interval_blocks=[], **kw_args):
        """ Initialises a new 'Pending' instance.
        """
        self.offset = offset
        self.scalar_float = scalar_float
        self.scalar_numerator = scalar_numerator
        self.multiply_before_add = multiply_before_add
        self.scalar_denominator = scalar_denominator
        self.reading_type = reading_type
        self.interval_blocks = interval_blocks

        super(Pending, self).__init__(**kw_args)
    # >>> pending


class DeviceFunction(AssetFunction):
    """ Function performed by a device such as a meter, communication equipment, controllers, etc.
    """
    # True if the device function is disabled (deactivated). Default is false (i.e., function is enabled). 
    disabled = False

    # End device asset that performs this function.
    end_device_asset = None

    # Communication equipment asset performing this device function.
    com_equipment_asset = None

    # All registers for quantities metered by this device function.
    registers = []

    # All events reported by this device function.
    end_device_events = []

    # <<< device_function
    # @generated
    def __init__(self, disabled=False, end_device_asset=None, com_equipment_asset=None, registers=[], end_device_events=[], **kw_args):
        """ Initialises a new 'DeviceFunction' instance.
        """
        self.disabled = disabled
        self.end_device_asset = end_device_asset
        self.com_equipment_asset = com_equipment_asset
        self.registers = registers
        self.end_device_events = end_device_events

        super(DeviceFunction, self).__init__(**kw_args)
    # >>> device_function


class Reading(MeasurementValue):
    """ Specific value measured by a meter or other asset. Each Reading is associated with a specific ReadingType.
    """
    # Value of this reading. 
    value = 0.0

    # Type information for this reading value.
    reading_type = None

    # All meter readings (sets of values) containing this reading value.
    meter_readings = []

    # Used only if quality of this reading value is different than 'Good'.
    reading_qualities = []

    end_device_asset = None

    # <<< reading
    # @generated
    def __init__(self, value=0.0, reading_type=None, meter_readings=[], reading_qualities=[], end_device_asset=None, **kw_args):
        """ Initialises a new 'Reading' instance.
        """
        self.value = value
        self.reading_type = reading_type
        self.meter_readings = meter_readings
        self.reading_qualities = reading_qualities
        self.end_device_asset = end_device_asset

        super(Reading, self).__init__(**kw_args)
    # >>> reading


class EndDeviceAsset(AssetContainer):
    """ AssetContainer that performs one or more end device functions. One type of EndDeviceAsset is a MeterAsset which can perform metering, load management, connect/disconnect, accounting functions, etc. Some EndDeviceAssets, such as ones monitoring and controlling air conditioner, refrigerator, pool pumps may be connected to a MeterAsset. All EndDeviceAssets may have communication capability defined by the associated ComFunction(s). An EndDeviceAsset may be owned by a consumer, a service provider, utility or otherwise. There may be a related end device function that identifies a sensor or control point within a metering applicaiton or communications systems (e.g., water, gas, electricity). Some devices may use an optical port that conforms to the ANSI C12.18 standard for communications.
    """
    # True if this end device asset is capable of supporting the means to report historical power interruption data. 
    outage_report = False

    # True if this end device asset is capable of supporting load control functions through either the meter or the AMR option. To determine whether this functionality is installed and enabled, check the 'DeviceFunction.disabled' attribute of the respective function contained by this end device asset. 
    load_control = False

    # True if this EndDeviceAsset is capable of supporting detection and monitoring of reverse flow. 
    reverse_flow_handling = False

    # True if this end device asset is capable of supporting the presention of metered values to a user or another system (always true for a meter, but might not be true for a load control unit.) 
    metrology = False

    # Time zone offset relative to GMT for the location of this end device. 
    time_zone_offset = ''

    # Automated meter reading (AMR) system responsible for communications to this end device. 
    amr_system = ''

    # True if this end device asset is capable of supporting the autonomous application of daylight savings time (DST). 
    dst_enabled = False

    # True if this end device asset is capable of supporting on-request reads for this end device. 
    read_request = False

    # True if this end device asset is capable of supporting demand response functions. To determine whether this functionality is installed and enabled, check the 'DeviceFunction.disabled' attribute of the respective function contained by this end device asset. 
    demand_response = False

    # True if this end device asset is capable of supporting remote whole-house disconnect capability. To determine whether this functionality is installed and enabled, check the 'DeviceFunction.disabled' attribute of the ConnectDisconnectFunction contained by this end device asset. 
    disconnect = False

    # True if this end device asset is capable of supporting one or more relays. The relays may be programmable in the meter and tied to TOU, time pulse, load control or other functions. To determine whether this functionality is installed and enabled, check the 'DeviceFunction.disabled' attribute of the respective function contained by this end device asset. 
    relay_capable = False

    # All sets of electrical properties for this end device asset.
    electrical_infos = []

    # Customer owning this end device asset.
    customer = None

    # All device functions this end device asset performs.
    device_functions = []

    # All end device groups referring to this end device asset.
    end_device_groups = []

    # Product documentation for this end device asset.
    end_device_model = None

    # All end device controls sending commands to this end device asset.
    end_device_controls = []

    readings = []

    # Service location whose service delivery is measured by this end device asset.
    service_location = None

    # Service delivery point to which this end device asset belongs.
    service_delivery_point = None

    # <<< end_device_asset
    # @generated
    def __init__(self, outage_report=False, load_control=False, reverse_flow_handling=False, metrology=False, time_zone_offset='', amr_system='', dst_enabled=False, read_request=False, demand_response=False, disconnect=False, relay_capable=False, electrical_infos=[], customer=None, device_functions=[], end_device_groups=[], end_device_model=None, end_device_controls=[], readings=[], service_location=None, service_delivery_point=None, **kw_args):
        """ Initialises a new 'EndDeviceAsset' instance.
        """
        self.outage_report = outage_report
        self.load_control = load_control
        self.reverse_flow_handling = reverse_flow_handling
        self.metrology = metrology
        self.time_zone_offset = time_zone_offset
        self.amr_system = amr_system
        self.dst_enabled = dst_enabled
        self.read_request = read_request
        self.demand_response = demand_response
        self.disconnect = disconnect
        self.relay_capable = relay_capable
        self.electrical_infos = electrical_infos
        self.customer = customer
        self.device_functions = device_functions
        self.end_device_groups = end_device_groups
        self.end_device_model = end_device_model
        self.end_device_controls = end_device_controls
        self.readings = readings
        self.service_location = service_location
        self.service_delivery_point = service_delivery_point

        super(EndDeviceAsset, self).__init__(**kw_args)
    # >>> end_device_asset


class IntervalReading(MeasurementValue):
    """ Data captured at regular intervals of time. Interval data could be captured as incremental data, absolute data, or relative data. The source for the data is usually a tariff quantity or an engineering quantity. Data is typically captured in time-tagged, uniform, fixed-length intervals of 5, 10, 15, 30, or 60 minutes. Note: Interval Data is sometimes also called 'Interval Data Readings' (IDR).
    """
    # Value of this interval reading. 
    value = 0.0

    # Used only if quality of this interval reading value is different than 'Good'.
    reading_qualities = []

    # All blocks containing this interval reading.
    interval_blocks = []

    # <<< interval_reading
    # @generated
    def __init__(self, value=0.0, reading_qualities=[], interval_blocks=[], **kw_args):
        """ Initialises a new 'IntervalReading' instance.
        """
        self.value = value
        self.reading_qualities = reading_qualities
        self.interval_blocks = interval_blocks

        super(IntervalReading, self).__init__(**kw_args)
    # >>> interval_reading


class DemandResponseProgram(IdentifiedObject):
    """ Demand response program.
    """
    # Type of demand response program; examples are CPP (critical-peak pricing), RTP (real-time pricing), DLC (direct load control), DBP (demand bidding program), BIP (base interruptible program). Note that possible types change a lot and it would be impossible to enumerate them all. 
    type = ''

    # Interval within which the program is valid.
    validity_interval = None

    # All groups of end devices with this demand response program.
    end_device_groups = []

    # All end device controls with this demand response program.
    end_device_controls = []

    # All customer agreements with this demand response program.
    customer_agreements = []

    # <<< demand_response_program
    # @generated
    def __init__(self, type='', validity_interval=None, end_device_groups=[], end_device_controls=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'DemandResponseProgram' instance.
        """
        self.type = type
        self.validity_interval = validity_interval
        self.end_device_groups = end_device_groups
        self.end_device_controls = end_device_controls
        self.customer_agreements = customer_agreements

        super(DemandResponseProgram, self).__init__(**kw_args)
    # >>> demand_response_program


class ElectricMeteringFunction(DeviceFunction):
    """ Functionality performed by an electric meter.
    """
    # Current transformer ratio used to convert associated quantities to real measurements. 
    transformer_ctratio = 0.0

    # Customer billing value = meter multiplier * transformer ratios * reading value. The multiplier identifies the scaling value to apply to the reported value after delivery of the tagged item. 
    billing_multiplier = 0.0

    # True if transformer ratios have been already applied to the associated quantities. 
    transformer_ratios_applied = False

    # An additional multiplier that may be used for normalization of the demand value to an hourly value. For example, if the demand interval were set to 15 minutes, the demand multiplier would be 4. If the meter design is such that the demand value reported and displayed is compensated for by the meter itself and no additional scaling is required outside of the meter, the value of the demand multiplier should be '1'. 
    demand_multiplier = 0.0

    # Meter kWh multiplier, used as a multiplier for a meter register reading to determine the actual amount of usage for which to bill a customer. 
    k_wh_multiplier = 0

    # Voltage transformer ratio used to convert associated quantities to real measurements. 
    transformer_vtratio = 0.0

    # True if the billingMultiplier ratio has already been applied to the associated quantities. 
    billing_multiplier_applied = False

    # The current class of the meter. Typical current classes in North America are 10, 20, 100, 200, or 320 A. 
    current_rating = ''

    # The service voltage at which the meter is designed to operate. Typical voltage ratings in North America are 120, 240, 277 or 480V. 
    voltage_rating = ''

    # Meter kW (pulse) multiplier, used as a multiplier for a meter register reading to determine the actual amount of usage for which to bill a customer. 
    k_wmultiplier = 0

    # True if the demandMultiplier ratio has already been applied to the associated quantities. 
    demand_multiplier_applied = False

    # Configuration for this electric metering function.
    metering_function_configuration = None

    # <<< electric_metering_function
    # @generated
    def __init__(self, transformer_ctratio=0.0, billing_multiplier=0.0, transformer_ratios_applied=False, demand_multiplier=0.0, k_wh_multiplier=0, transformer_vtratio=0.0, billing_multiplier_applied=False, current_rating='', voltage_rating='', k_wmultiplier=0, demand_multiplier_applied=False, metering_function_configuration=None, **kw_args):
        """ Initialises a new 'ElectricMeteringFunction' instance.
        """
        self.transformer_ctratio = transformer_ctratio
        self.billing_multiplier = billing_multiplier
        self.transformer_ratios_applied = transformer_ratios_applied
        self.demand_multiplier = demand_multiplier
        self.k_wh_multiplier = k_wh_multiplier
        self.transformer_vtratio = transformer_vtratio
        self.billing_multiplier_applied = billing_multiplier_applied
        self.current_rating = current_rating
        self.voltage_rating = voltage_rating
        self.k_wmultiplier = k_wmultiplier
        self.demand_multiplier_applied = demand_multiplier_applied
        self.metering_function_configuration = metering_function_configuration

        super(ElectricMeteringFunction, self).__init__(**kw_args)
    # >>> electric_metering_function


class MeterAsset(EndDeviceAsset):
    """ Physical asset that performs the metering role of the ServiceDeliveryPoint. Used for measuring consumption and detection of events.
    """
    # Meter kh (watthour) constant. It is the number of watthours that must be applied to the meter to cause one disk revolution for an electromechanical meter or the number of watthours represented by one increment pulse for an electronic meter. 
    k_h = 0.0

    # Meter form designation per ANSI C12.10 or other applicable standard. An alphanumeric designation denoting the circuit arrangement for which the meter is applicable and its specific terminal arrangement. 
    form_number = ''

    # Display multiplier used to produce a displayed value from a register value. 
    k_r = 0.0

    # All non-replacement works on this meter asset.
    meter_service_works = []

    meter_asset_model = None

    # All meter readings provided by this meter asset.
    meter_readings = []

    # All vending transactions on this meter asset.
    vending_transactions = []

    # All works on replacement of this old meter asset.
    meter_replacement_works = []

    # <<< meter_asset
    # @generated
    def __init__(self, k_h=0.0, form_number='', k_r=0.0, meter_service_works=[], meter_asset_model=None, meter_readings=[], vending_transactions=[], meter_replacement_works=[], **kw_args):
        """ Initialises a new 'MeterAsset' instance.
        """
        self.k_h = k_h
        self.form_number = form_number
        self.k_r = k_r
        self.meter_service_works = meter_service_works
        self.meter_asset_model = meter_asset_model
        self.meter_readings = meter_readings
        self.vending_transactions = vending_transactions
        self.meter_replacement_works = meter_replacement_works

        super(MeterAsset, self).__init__(**kw_args)
    # >>> meter_asset


class ComFunction(DeviceFunction):
    """ Communication function of communication equipment or a device such as a meter.
    """
    # Communication ID number (e.g. port number, serial number, data collector ID, etc.) of the parent device associated to this AMR module. Note: If someone swaps out a meter, they may inadvertently disrupt the AMR system. Some technologies route readings from nearby meters through a common collection point on an electricity meter. Removal of such a meter disrupts AMR for numerous nearby meters. 
    amr_router = ''

    # True when the AMR module can both send and receive messages. Default is false (i.e., module can only send). 
    two_way = False

    # Communication ID number (e.g. serial number, IP address, telephone number, etc.) of the AMR module which serves this meter. 
    amr_address = ''

    # <<< com_function
    # @generated
    def __init__(self, amr_router='', two_way=False, amr_address='', **kw_args):
        """ Initialises a new 'ComFunction' instance.
        """
        self.amr_router = amr_router
        self.two_way = two_way
        self.amr_address = amr_address

        super(ComFunction, self).__init__(**kw_args)
    # >>> com_function


# <<< metering
# @generated
# >>> metering

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from  import 
from  import 
from  import 
from  import 
from iec61968.assets.assetcontainers import AssetContainer
from iec61968.work.workinitiation import Work
from  import 
from  import 
from iec61968.assets.pointassethierarchy import AssetFunction
from  import 
from  import 
from iec61968.core2.identifiedobjectinheritance import Role
from iec61968.core2.activityrecords import ActivityRecord
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class MeterGroup():
    """ A meter group is used for the management of group communications within a two-way AMR system or the data for a group of related meters. Commands can be issued to all of the meters that belong to a meter group using a defined group address and the underlying AMR communication infrastructure. 
    """
    # Address of meter group 
    group_address = ''

    meter_assets = []

    customer_agreements = []

    meter_controls = []

    com_functions = []

    time_schedules = []

    # <<< meter_group
    # @generated
    def __init__(self, group_address='', meter_assets=[], customer_agreements=[], meter_controls=[], com_functions=[], time_schedules=[], **kw_args):
        """ Initialises a new 'MeterGroup' instance.
        """
        self.group_address = group_address
        self.meter_assets = meter_assets
        self.customer_agreements = customer_agreements
        self.meter_controls = meter_controls
        self.com_functions = com_functions
        self.time_schedules = time_schedules

        super(MeterGroup, self).__init__(**kw_args)
    # >>> meter_group


class ServiceDeliveryPoint():
    """ There can be multiple service points at a single service location, each one identified as a meter service point.  A service point exists within a service location and is used at the place where a meter may be installed.  Properties include: 1) serviceType (gas, electric, water) 2) servicePhase 3) voltageLevel 4) vtdtReference (optional, where meters are indirectly connected for a medium voltage service)
    """
    # Service type: gas, water, electric. 
    service_type = ''

    # Phase type: delta, wye. 
    phase_type = ''

    # This field is optional for medium voltage connections.  ctptReference is used to identify the specific CT or PT that is used to obtain a reading from a medium or high voltage point ( a reference  to the low side terminal of a CT or PT).  
    ctpt_reference = ''

    # TRUE if grounded. 
    grounded = ''

    # Nominal service voltage.  
    nominal_service_voltage = ''

    # The priority of service for this service point.  Note that service points can have different priorities at the same service location.  
    service_priority = ''

    # Remarks about the location, for example the reason for it being rated with a non-nominal priority.   
    location_remarks = ''

    #  Billing cycle. 
    billing_cycle = ''

    #  Load management code. 
    load_mgmt = ''

    # Estimated load in amps.  Note: This could be replaced in the future to identify a collection of loads and associated power factors 
    est_load = ''

    #  Budget bill code. 
    budget_bill = ''

    # True if as a result of an inspection or otherwise, there is a reason to suspect that a previous billing may have been performed with erroneous data.   This flag should be reset once this potential discrepancy has been resolved.  
    check_billing = ''

    power_transformer = None

    phase_loads = []

    power_quality_pricing = []

    sdplocations = []

    meter_type_asset = None

    meter_asset_model = None

    meter_assets = []

    meter_readings = []

    # <<< service_delivery_point
    # @generated
    def __init__(self, service_type='', phase_type='', ctpt_reference='', grounded='', nominal_service_voltage='', service_priority='', location_remarks='', billing_cycle='', load_mgmt='', est_load='', budget_bill='', check_billing='', power_transformer=None, phase_loads=[], power_quality_pricing=[], sdplocations=[], meter_type_asset=None, meter_asset_model=None, meter_assets=[], meter_readings=[], **kw_args):
        """ Initialises a new 'ServiceDeliveryPoint' instance.
        """
        self.service_type = service_type
        self.phase_type = phase_type
        self.ctpt_reference = ctpt_reference
        self.grounded = grounded
        self.nominal_service_voltage = nominal_service_voltage
        self.service_priority = service_priority
        self.location_remarks = location_remarks
        self.billing_cycle = billing_cycle
        self.load_mgmt = load_mgmt
        self.est_load = est_load
        self.budget_bill = budget_bill
        self.check_billing = check_billing
        self.power_transformer = power_transformer
        self.phase_loads = phase_loads
        self.power_quality_pricing = power_quality_pricing
        self.sdplocations = sdplocations
        self.meter_type_asset = meter_type_asset
        self.meter_asset_model = meter_asset_model
        self.meter_assets = meter_assets
        self.meter_readings = meter_readings

        super(ServiceDeliveryPoint, self).__init__(**kw_args)
    # >>> service_delivery_point


class MeterReading():
    """ Meter readings are used to convey quantities that are measured by a meter. 
    """
    # Start time of the data items contained within the MeterReading 
    start_time = ''

    # End time of the data items contained within the MeterReading 
    end_time = ''

    meter_service_request = None

    meter_asset = None

    service_delivery_point = None

    customer_agreement = None

    interval_blocks = []

    activity_records = []

    readings = []

    com_function = None

    # <<< meter_reading
    # @generated
    def __init__(self, start_time='', end_time='', meter_service_request=None, meter_asset=None, service_delivery_point=None, customer_agreement=None, interval_blocks=[], activity_records=[], readings=[], com_function=None, **kw_args):
        """ Initialises a new 'MeterReading' instance.
        """
        self.start_time = start_time
        self.end_time = end_time
        self.meter_service_request = meter_service_request
        self.meter_asset = meter_asset
        self.service_delivery_point = service_delivery_point
        self.customer_agreement = customer_agreement
        self.interval_blocks = interval_blocks
        self.activity_records = activity_records
        self.readings = readings
        self.com_function = com_function

        super(MeterReading, self).__init__(**kw_args)
    # >>> meter_reading


class ReadingType():
    """ A ReadingType is used to identify the type of data that is conveyed by a specific Reading. 
    """
    # This attribute can be used as a further descriptor for a TOU rate or some other condition which characterizes the metering. Typical categories include: Energy, Power, Demand, Voltage, Current, Voltage Angle, Current Angle, Phase Angle, Power Factor, Pressure, Volume, Date, Time. 
    meas_type_category = ''

    # If no interval length is specified, it implies that the readings are absolute values rather than incremental values. Time is measured in seconds. 
    interval_length = ''

    # By default (true), intervals are presented in 'forward' chronological order. This field is provided for systems that must operate in 'reverse' chronological order. 
    forward_chronology = ''

    # The numeric type to be expected for the associated Block.value (e.g. dataType= unsignedInteger). 
    default_value_data_type = ''

    # This quality code is to be supplied to each block.value unless a block.validityStatus is supplied instead. (e.g. status =  'Good'). 
    default_quality = ''

    # Demand configuration such as block, rolling, logrithmic and sizes such as 15 minutes, 30 minutes, 5 minutes subinterval. 
    dynamic_configuration = ''

    # Logical positioning of this measurement data. 
    channel_number = ''

    readings = []

    interval_blocks = []

    meter_function_configuration = None

    pending = None

    unit = None

    register = None

    # <<< reading_type
    # @generated
    def __init__(self, meas_type_category='', interval_length='', forward_chronology='', default_value_data_type='', default_quality='', dynamic_configuration='', channel_number='', readings=[], interval_blocks=[], meter_function_configuration=None, pending=None, unit=None, register=None, **kw_args):
        """ Initialises a new 'ReadingType' instance.
        """
        self.meas_type_category = meas_type_category
        self.interval_length = interval_length
        self.forward_chronology = forward_chronology
        self.default_value_data_type = default_value_data_type
        self.default_quality = default_quality
        self.dynamic_configuration = dynamic_configuration
        self.channel_number = channel_number
        self.readings = readings
        self.interval_blocks = interval_blocks
        self.meter_function_configuration = meter_function_configuration
        self.pending = pending
        self.unit = unit
        self.register = register

        super(ReadingType, self).__init__(**kw_args)
    # >>> reading_type


class Reading():
    """ A Reading is used to convey a specific value measured by a meter or other asset. Each Reading is associated with a specific Readin Type. 
    """
    # Value of the reading.  
    value = ''

    asset = None

    reading_types = []

    reading_qualities = []

    meter_readings = []

    # <<< reading
    # @generated
    def __init__(self, value='', asset=None, reading_types=[], reading_qualities=[], meter_readings=[], **kw_args):
        """ Initialises a new 'Reading' instance.
        """
        self.value = value
        self.asset = asset
        self.reading_types = reading_types
        self.reading_qualities = reading_qualities
        self.meter_readings = meter_readings

        super(Reading, self).__init__(**kw_args)
    # >>> reading


class IntervalBlock(object):
    """ An IntervalBlock is used within a MeterReading to identify a time sequence of Readings of the same readingType. 
    """
    reading_types = []

    meter_reading = None

    ireadings = []

    # <<< interval_block
    # @generated
    def __init__(self, reading_types=[], meter_reading=None, ireadings=[], **kw_args):
        """ Initialises a new 'IntervalBlock' instance.
        """
        self.reading_types = reading_types
        self.meter_reading = meter_reading
        self.ireadings = ireadings

        super(IntervalBlock, self).__init__(**kw_args)
    # >>> interval_block


class EndDeviceAsset(AssetContainer):
    """ This type of AssetContainer performs one or more EndDevice functions.  One type of EndDeviceAsset is a Meter Asset which can perform metering, load management, connect/disconnect, accounting functions, etc.  Some EndDeviceAssets, such as ones monitoring and controling air conditioner, refrigerator, pool pumps may be connected to a MeterAsset.  All EndDeviceAssets may have communication capability defined by the associated ComFunction(s).  An EndDeviceAsset may be owned by a consumer, a service provider, utility or otherwise.   The EndDeviceFunction is the closest device to a sensor or control point within a metering applicaiton or communications systems.  e.g., water, gas, electricity,   C1218 Device is something using an optical port for coummunications. 
    """
    # TRUE when this EndDeviceAsset is capable of supporting remote whole-house disconnect capability.  To determine if this functionality is installed and enabled, check the 'enabled' attribute of the associated device function contained by this EndDeviceAsset.   
    disconnect = ''

    # TRUE when this EndDeviceAsset is capable of supporting load control functions through either the meter or the AMR option.   To determine if this functionality is installed and enabled, check the 'enabled' attribute of the associated device function contained by this EndDeviceAsset.   
    load_control = ''

    # TRUE when this EndDeviceAsset is capable of supporting  detection and monitoring of reverse flow.  
    reverse_flow_handling = ''

    # TRUE when this EndDeviceAsset is capable of supporting demand response functions.   To determine if this functionality is installed and enabled, check the 'enabled' attribute of the associated device function contained by this EndDeviceAsset.   
    demand_response = ''

    # TRUE when tthis EndDeviceAsset is capable of supporting the presention of metered values to a user or another system. (This is always true when the End Device is a meter. It might not be true if the End Device is a load control unit.)  
    metrology = ''

    # TRUE when this EndDeviceAsset is capable of supporting the means to report historical power interruption data. 
    outage_report = ''

    # TRUEthis EndDeviceAsset is capable of supporting one or more relays. The relays may be programmable in the meter and tied to TOU, time pulse, Load Control or other functions.  To determine if this functionality is installed and enabled, check the 'enabled' attribute of the associated device function contained by this EndDeviceAsset.   
    relays = ''

    # TRUE when this EndDeviceAsset is capable of supporting on-request reads for this end device. 
    read_request = ''

    # TRUE when this EndDeviceAsset is capable of supporting the autonomous application of daylight savings time (DST). 
    dst_enabled = ''

    # The time zone offset (in minutes) relative to GMT for the location of the end device. 
    time_zone_offset = ''

    # The metering system responsible for communications to the end device.  
    amr_system = ''

    # The type of End Device. 
    type_end_device = ''

    end_device_model = None

    device_functions = []

    customer_data = None

    # <<< end_device_asset
    # @generated
    def __init__(self, disconnect='', load_control='', reverse_flow_handling='', demand_response='', metrology='', outage_report='', relays='', read_request='', dst_enabled='', time_zone_offset='', amr_system='', type_end_device='', end_device_model=None, device_functions=[], customer_data=None, **kw_args):
        """ Initialises a new 'EndDeviceAsset' instance.
        """
        self.disconnect = disconnect
        self.load_control = load_control
        self.reverse_flow_handling = reverse_flow_handling
        self.demand_response = demand_response
        self.metrology = metrology
        self.outage_report = outage_report
        self.relays = relays
        self.read_request = read_request
        self.dst_enabled = dst_enabled
        self.time_zone_offset = time_zone_offset
        self.amr_system = amr_system
        self.type_end_device = type_end_device
        self.end_device_model = end_device_model
        self.device_functions = device_functions
        self.customer_data = customer_data

        super(EndDeviceAsset, self).__init__(**kw_args)
    # >>> end_device_asset


class MeterServiceWork(Work):
    """ MeterServiceRequests are used to manage work involving meters. 
    """
    meter_asset = None

    meter_readings = []

    # <<< meter_service_work
    # @generated
    def __init__(self, meter_asset=None, meter_readings=[], **kw_args):
        """ Initialises a new 'MeterServiceWork' instance.
        """
        self.meter_asset = meter_asset
        self.meter_readings = meter_readings

        super(MeterServiceWork, self).__init__(**kw_args)
    # >>> meter_service_work


class MeteringFunctionConfiguration():
    """ The configuration of data for a given meter function.
    """
    electric_meter_functions = []

    water_meter_functions = []

    gas_meter_functions = []

    reading_types = []

    # <<< metering_function_configuration
    # @generated
    def __init__(self, electric_meter_functions=[], water_meter_functions=[], gas_meter_functions=[], reading_types=[], **kw_args):
        """ Initialises a new 'MeteringFunctionConfiguration' instance.
        """
        self.electric_meter_functions = electric_meter_functions
        self.water_meter_functions = water_meter_functions
        self.gas_meter_functions = gas_meter_functions
        self.reading_types = reading_types

        super(MeteringFunctionConfiguration, self).__init__(**kw_args)
    # >>> metering_function_configuration


class ReadingQuality(object):
    """ The qulity of a speicif reading.  Note that more than one Quality may be applicable to a given ReadingValue.  This field is not necessary unless problems or unusual conditions occur because Quality for each ReadingValue is assumed to be 'Good' unless stated here otherwise.  
    """
    # Quality for each ReadingValue is assemed to be 'Good' unless stated here otherwise. 
    quality = ''

    ireading = None

    reading = None

    # <<< reading_quality
    # @generated
    def __init__(self, quality='', ireading=None, reading=None, **kw_args):
        """ Initialises a new 'ReadingQuality' instance.
        """
        self.quality = quality
        self.ireading = ireading
        self.reading = reading

        super(ReadingQuality, self).__init__(**kw_args)
    # >>> reading_quality


class IReading():
    """ Interval Reading
    """
    # Value of the reading.  
    value = ''

    reading_qualities = []

    interval_blocks = []

    # <<< ireading
    # @generated
    def __init__(self, value='', reading_qualities=[], interval_blocks=[], **kw_args):
        """ Initialises a new 'IReading' instance.
        """
        self.value = value
        self.reading_qualities = reading_qualities
        self.interval_blocks = interval_blocks

        super(IReading, self).__init__(**kw_args)
    # >>> ireading


class DeviceFunction(AssetFunction):
    """ A function performed by a device such as a meter, communication equipment, contorllers, etc..
    """
    # True if the device function is enabled (activated). 
    enabled = ''

    end_device_asset = None

    meter_events = []

    com_ports = []

    registers = []

    # <<< device_function
    # @generated
    def __init__(self, enabled='', end_device_asset=None, meter_events=[], com_ports=[], registers=[], **kw_args):
        """ Initialises a new 'DeviceFunction' instance.
        """
        self.enabled = enabled
        self.end_device_asset = end_device_asset
        self.meter_events = meter_events
        self.com_ports = com_ports
        self.registers = registers

        super(DeviceFunction, self).__init__(**kw_args)
    # >>> device_function


class CreditRegister():
    """ Accumulated credits transacted per credit type for a given function.  There could be several of these registers, one for each credit type; depending on the application.   
    """
    # The value is a credit amount in favour of the customer. The units are either in currency units or service units, depending on the value of accountingMode.  
    credit = ''

    # Several different types of credit are typically implemented in the case of a prepayment meter.  For example: credit transferred by means of a token carrier, or credit advanced automatically inside the meter under certain conditions, or credit held in reserved to be released under emergency conditions, or credit granted by local authority as a basic life support mechanism and may be dispensed automatically by the meter under certain conditions or credit available under severe climate conditions such as during winter over a weekend.  Values are: "token_credit", "advance_credit", "reserve_credit", "grant_credit", "lifeline_credit", "other"
    credit_type = 'token_credit'

    spaccounting_function = None

    # <<< credit_register
    # @generated
    def __init__(self, credit='', credit_type='token_credit', spaccounting_function=None, **kw_args):
        """ Initialises a new 'CreditRegister' instance.
        """
        self.credit = credit
        self.credit_type = credit_type
        self.spaccounting_function = spaccounting_function

        super(CreditRegister, self).__init__(**kw_args)
    # >>> credit_register


class ChargeRegister():
    """ Accumulated charges transacted per charge type for a given function.  There could be several of these registers, one for each charge type; depending on the application, 
    """
    # The value is a charge amount in favour of the supplier. The units are either in currency units or service units, depending on the value of accountingMode.  
    charge = ''

    # Several different types of charges are typically implemented in the case of a prepayment meter. For example: a charge according to a tariff for consumption and possibly a demand component, or a charge for a debt that is loaded in the meter to be recovered on a time basis, or a standing charge to be levied at the end of each billing period, or a tax charge loaded in the meter to be recovered on a consumption basis or a time basis.  Values are: "consumption_charge", "demand_charge", "auxiliary_charge", "tax_charge", "other"
    charge_type = 'consumption_charge'

    spaccounting_function = None

    # <<< charge_register
    # @generated
    def __init__(self, charge='', charge_type='consumption_charge', spaccounting_function=None, **kw_args):
        """ Initialises a new 'ChargeRegister' instance.
        """
        self.charge = charge
        self.charge_type = charge_type
        self.spaccounting_function = spaccounting_function

        super(ChargeRegister, self).__init__(**kw_args)
    # >>> charge_register


class MeterReadingPurpose(Role):
    """ The purpose of the meter reading, such as initial reading, final reading, peridic reading, demand reading, etc.  This information is oftend used to distinguish final and initial readings when there is a tenancy change at a service location.
    """
    pass
    # <<< meter_reading_purpose
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'MeterReadingPurpose' instance.
        """

        super(MeterReadingPurpose, self).__init__(**kw_args)
    # >>> meter_reading_purpose


class Pending(object):
    """ When present, it means that the data values can be converted from the MeasurementType and unit names in ReadingType to instead become the MeasurementType and Unit name described under 'Pending' by application of the accompanying 'pending' scalar.
    """
    # When the scalar needs to be presented as a floating value the ScalarFloat element will be populated. When multiplied with the Reading data value it causes a unit of measure conversion to occur – resulting in the Pending.UnitType unit of measure.   
    scalar_float = ''

    # When the scalar needs to be presented as an integer (or rational number) the ScalarNumerator element will be populated. When the scalar is a simple integer, and ScalarNumerator is presented alone and multiplied with the Reading data value, it causes a unit of measure conversion to occur – resulting in the Pending.UnitType unit of measure. ScalarNumerator may be used in conjunction with ScalarDenominator, but not with ScalarFloat.  
    scalar_numerator = ''

    # When the scalar needs to be presented as a rational number, the ScalarDenominator element will be populated. When the Reading data value is multiplied by the ScalarNumerator, and divided by the ScalarDenominator, it causes a unit of measure conversion to occur – resulting in the Pending.UnitType unit of measure.  
    scalar_denominator = ''

    reading_type = None

    unit_type = None

    # <<< pending
    # @generated
    def __init__(self, scalar_float='', scalar_numerator='', scalar_denominator='', reading_type=None, unit_type=None, **kw_args):
        """ Initialises a new 'Pending' instance.
        """
        self.scalar_float = scalar_float
        self.scalar_numerator = scalar_numerator
        self.scalar_denominator = scalar_denominator
        self.reading_type = reading_type
        self.unit_type = unit_type

        super(Pending, self).__init__(**kw_args)
    # >>> pending


class LoadMgmtRecord(ActivityRecord):
    """ A log of actual measured load reductions as a result of load shed operations.
    """
    # The measured reduction of the total load power as a result of the load shed activation. Thus it is the difference in power before and after the load shed operation.
    load_reduction = None

    load_mgmt_function = None

    # <<< load_mgmt_record
    # @generated
    def __init__(self, load_reduction=None, load_mgmt_function=None, **kw_args):
        """ Initialises a new 'LoadMgmtRecord' instance.
        """
        self.load_reduction = load_reduction
        self.load_mgmt_function = load_mgmt_function

        super(LoadMgmtRecord, self).__init__(**kw_args)
    # >>> load_mgmt_record


class MeterControl():
    """ A meter control is used to instruct a meter to perform a specified action. 
    """
    # Type of control.  
    control_type = ''

    # Start time for controls that have a scheduled duration.  
    start_time = ''

    # End time for events that have a scheduled duration.  
    end_time = ''

    # Price is a parameter used for some MeterControls, such as real-time price signals.  
    price = ''

    # Optional argument that can be supplied with a control.  
    argument = ''

    meter_asset = None

    meter_group = None

    customer_agreement = None

    # <<< meter_control
    # @generated
    def __init__(self, control_type='', start_time='', end_time='', price='', argument='', meter_asset=None, meter_group=None, customer_agreement=None, **kw_args):
        """ Initialises a new 'MeterControl' instance.
        """
        self.control_type = control_type
        self.start_time = start_time
        self.end_time = end_time
        self.price = price
        self.argument = argument
        self.meter_asset = meter_asset
        self.meter_group = meter_group
        self.customer_agreement = customer_agreement

        super(MeterControl, self).__init__(**kw_args)
    # >>> meter_control


class MeteringVersion(object):
 
    version = ''

 
    date = ''

    # <<< metering_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'MeteringVersion' instance.
        """
        self.version = version
        self.date = date

        super(MeteringVersion, self).__init__(**kw_args)
    # >>> metering_version


class UnitOfMeasure(object):
    """ While there may be many aspects to describing a measurement, three of them are very popular and are clustered together as the 'UnitType'. The MeasurementType element can be used to indicate flow direction as well as the type of measurement. Possible enumerations include: NetEnergy, ForwardEnergy, ReverseEnergy, TotalEnergy, ForwardMaxDemand, etc. The Unit name goes on to further qualify the energy (or power) as being real (Wh, W) or reactive (VArh, VAr). If it is needed, the Unit.Multiplier element is available to carry the metric prefix for the unit of measure (e.g. 'k', 'M'.) 
    """
 
    multiplier = ''

 
    unit_symbol = ''

    measurement = None

    pending = []

    reading_types = []

    # <<< unit_of_measure
    # @generated
    def __init__(self, multiplier='', unit_symbol='', measurement=None, pending=[], reading_types=[], **kw_args):
        """ Initialises a new 'UnitOfMeasure' instance.
        """
        self.multiplier = multiplier
        self.unit_symbol = unit_symbol
        self.measurement = measurement
        self.pending = pending
        self.reading_types = reading_types

        super(UnitOfMeasure, self).__init__(**kw_args)
    # >>> unit_of_measure


class ComPort():
    """ Port information used for communication connectivity purposes.  The 'port' names the physical association to another device from the perspective of the parent asset. For example, a communications module may need to list the meters which it can talk to as meter serial number '123' is on 'port 0', S/N '456' is on port '1', etc.. A meter or load control device may need to know that a hot-water heater load is on 'port 0', and an air-conditioner on 'port 1'. 
    """
    device_function = None

    # <<< com_port
    # @generated
    def __init__(self, device_function=None, **kw_args):
        """ Initialises a new 'ComPort' instance.
        """
        self.device_function = device_function

        super(ComPort, self).__init__(**kw_args)
    # >>> com_port


class Register():
    """ A quantity that is metered on an end device such as a meter. 
    """
    # The number of digits (dials on a mechanical meter) to the right of the decimal place. 
    num_digits_right = ''

    # The number of digits (dials on a mechanical meter) to the left of the decimal place. 
    num_digits_left = ''

    device_function = None

    reading_type = None

    # <<< register
    # @generated
    def __init__(self, num_digits_right='', num_digits_left='', device_function=None, reading_type=None, **kw_args):
        """ Initialises a new 'Register' instance.
        """
        self.num_digits_right = num_digits_right
        self.num_digits_left = num_digits_left
        self.device_function = device_function
        self.reading_type = reading_type

        super(Register, self).__init__(**kw_args)
    # >>> register


class MeterAsset(EndDeviceAsset):
    """ The physical asset that performs the metering role of the Meter Service Point.  They are used for measuring consumption and detection of events
    """
    #  Meter seal number. 
    seal_no = ''

    # The type of seal, such as Steel, Lead, Lock. 
    seal_type = ''

    # Meter form designation per ANSI C12.10 or other applicable standard.  An alphanumeric designation denoting the circuit arrangement for which the meter is applicable and its specific terminal arrangement. 
    form_number = ''

    # Display multiplier used to produce a displayed value from a register value.  
    k_r = ''

    # Meter kh (watthour) constant. This constant is the number of watthours that must be applied to the meter to cause one disk revolution for an electromechanical meter or the number of watthours represented by one increment pulse for an electronic meter.  
    k_h = ''

    # The specific type of meter. 
    type_meter = ''

    service_location = None

    meter_asset_model = None

    meter_groups = []

    service_delivery_point = None

    meter_service_requests = []

    meter_readings = []

    meter_controls = []

    electrical_properties = []

    # <<< meter_asset
    # @generated
    def __init__(self, seal_no='', seal_type='', form_number='', k_r='', k_h='', type_meter='', service_location=None, meter_asset_model=None, meter_groups=[], service_delivery_point=None, meter_service_requests=[], meter_readings=[], meter_controls=[], electrical_properties=[], **kw_args):
        """ Initialises a new 'MeterAsset' instance.
        """
        self.seal_no = seal_no
        self.seal_type = seal_type
        self.form_number = form_number
        self.k_r = k_r
        self.k_h = k_h
        self.type_meter = type_meter
        self.service_location = service_location
        self.meter_asset_model = meter_asset_model
        self.meter_groups = meter_groups
        self.service_delivery_point = service_delivery_point
        self.meter_service_requests = meter_service_requests
        self.meter_readings = meter_readings
        self.meter_controls = meter_controls
        self.electrical_properties = electrical_properties

        super(MeterAsset, self).__init__(**kw_args)
    # >>> meter_asset


class ElectricMeteringFunction(DeviceFunction):
    """ Functionality performed by an electric meter.
    """
    # Meter kW (pulse) multiplier.  Meter Multiplier Value is used as a multiplier for a Meter Register Reading to determine the actual amount of usage for which to bill a customer.   
    k_wmultiplier = ''

    #  Meter kWh Multiplier.   Meter Multiplier Value is used as a multiplier for a Meter Register Reading to determine the actual amount of usage for which to bill a customer.   
    k_wh_multiplier = ''

    # True if these ratios have been already applied to the associated quantities. 
    transformer_ratios_applied = ''

    # Current transformer ratio used to convert associated quantities to real measurements. 
    transformer_ctratio = ''

    # Voltage transformer ratio used to convert associated quantities to real measurements. 
    transformer_vtratio = ''

    # The service voltage at which the meter is designed to operate. Typical voltage ratings in North America are 120, 240, 277 or 480V.  
    voltage_rating = ''

    # The current class of the meter. Typical current classes in North America are 10, 20, 100, 200, or 320 Amps. 
    current_rating = ''

    # True if the billingMultiplier ratio has already been applied to the associated quantities. 
    billing_multiplier_applied = ''

    # Customer billing value = meter multiplier * transformer ratios * reading value.  The multiplier identifies the scaling value to apply to the reported value after delivery of the tagged item. 
    billing_multiplier = ''

    # True if the demandMultiplier ratio has already been applied to the associated quantities. 
    demand_multiplier_applied = ''

    # An additional multiplier that may be used for normalization of the demand value to an hourly value.  For example, if the demand interval were set to 15 minutes, the demand multiplier would be 4.  If the meter design is such that the demand value reported and displayed is compensated for by the meter itself and no additional scaling is required outside of the meter, the value of the demand multiplier should be '1'. 
    demand_multiplier = ''

    meter_function_configuarion = None

    # <<< electric_metering_function
    # @generated
    def __init__(self, k_wmultiplier='', k_wh_multiplier='', transformer_ratios_applied='', transformer_ctratio='', transformer_vtratio='', voltage_rating='', current_rating='', billing_multiplier_applied='', billing_multiplier='', demand_multiplier_applied='', demand_multiplier='', meter_function_configuarion=None, **kw_args):
        """ Initialises a new 'ElectricMeteringFunction' instance.
        """
        self.k_wmultiplier = k_wmultiplier
        self.k_wh_multiplier = k_wh_multiplier
        self.transformer_ratios_applied = transformer_ratios_applied
        self.transformer_ctratio = transformer_ctratio
        self.transformer_vtratio = transformer_vtratio
        self.voltage_rating = voltage_rating
        self.current_rating = current_rating
        self.billing_multiplier_applied = billing_multiplier_applied
        self.billing_multiplier = billing_multiplier
        self.demand_multiplier_applied = demand_multiplier_applied
        self.demand_multiplier = demand_multiplier
        self.meter_function_configuarion = meter_function_configuarion

        super(ElectricMeteringFunction, self).__init__(**kw_args)
    # >>> electric_metering_function


class GasMeteingrFunction(DeviceFunction):
    """ Functionality performed by an gas meter.
    """
    meter_function_configuration = None

    # <<< gas_meteingr_function
    # @generated
    def __init__(self, meter_function_configuration=None, **kw_args):
        """ Initialises a new 'GasMeteingrFunction' instance.
        """
        self.meter_function_configuration = meter_function_configuration

        super(GasMeteingrFunction, self).__init__(**kw_args)
    # >>> gas_meteingr_function


class WaterMeteringFunction(DeviceFunction):
    """ Functionality performed by an water meter.
    """
    meter_function_configuration = None

    # <<< water_metering_function
    # @generated
    def __init__(self, meter_function_configuration=None, **kw_args):
        """ Initialises a new 'WaterMeteringFunction' instance.
        """
        self.meter_function_configuration = meter_function_configuration

        super(WaterMeteringFunction, self).__init__(**kw_args)
    # >>> water_metering_function


class ComFunction(DeviceFunction):
    """ Communication function of communication equipment or a device such as a meter. 
    """
    # The communication ID number (e.g. serial number, IP address, telephone number, etc.) of the AMR module which serves this meter. 
    amr_address = ''

    # The communication ID number (e.g. port number, serial number, data collector ID, etc.) of the parent device associated to this AMR module.  Note: The thought here is that if someone swaps out a meter, they may inadvertently disrupt the AMR system. Some technologies route readings from nearby meters through a common collection point on an electricity meter. Removal of such a meter disrupts AMR for numerous nearby meters.  
    amr_router = ''

    # This field is set to TRUE indicate if the AMR module can only send, but can not receive messages. 
    one_way = ''

    # The type of communication function. 
    type_com_func = ''

    com_func_asset_model = None

    meter_readins = []

    meter_groups = []

    # <<< com_function
    # @generated
    def __init__(self, amr_address='', amr_router='', one_way='', type_com_func='', com_func_asset_model=None, meter_readins=[], meter_groups=[], **kw_args):
        """ Initialises a new 'ComFunction' instance.
        """
        self.amr_address = amr_address
        self.amr_router = amr_router
        self.one_way = one_way
        self.type_com_func = type_com_func
        self.com_func_asset_model = com_func_asset_model
        self.meter_readins = meter_readins
        self.meter_groups = meter_groups

        super(ComFunction, self).__init__(**kw_args)
    # >>> com_function


class LoadMgmtFunction(DeviceFunction):
    """ Load management function is a collective function at an end device that manages the customer load.
    """
    # True if the currently active schedule is being manually over-ridden to either shed load or to limit load. 
    manual_over_ride = ''

    # True if the currently active schedule is being remotely over-ridden to either shed load or to limit load. 
    remote_over_ride = ''

    # After a command had been received to activate the mannualOverRide state or remoteOverRideState, the normal (halted) schedule will resume after this specified number of minutes had elapsed. 
    over_ride_time_out = ''

    # The present state of the load being either shed (noLoad), limited (limitedLoad) or fully connected (fullLoad). This refers only to the portion of the customer load that is under control of the LoadMgmtFunction. Values are: "no_load", "limited_load"
    load_status = 'no_load'

    # Specifies whether LoadMgmtFunction operates under manual control or automatic control. Values are: "automatic", "manual"
    operation = 'automatic'

    # The basis of Load Management scheduling used here: Time Based, Tariff Based, Remote Control and Manual Control. Values are: "time_based", "tariff_based", "remote_control", "manual_control"
    scheduling_basis = 'time_based'

    load_mgmt_records = []

    switches = []

    # <<< load_mgmt_function
    # @generated
    def __init__(self, manual_over_ride='', remote_over_ride='', over_ride_time_out='', load_status='no_load', operation='automatic', scheduling_basis='time_based', load_mgmt_records=[], switches=[], **kw_args):
        """ Initialises a new 'LoadMgmtFunction' instance.
        """
        self.manual_over_ride = manual_over_ride
        self.remote_over_ride = remote_over_ride
        self.over_ride_time_out = over_ride_time_out
        self.load_status = load_status
        self.operation = operation
        self.scheduling_basis = scheduling_basis
        self.load_mgmt_records = load_mgmt_records
        self.switches = switches

        super(LoadMgmtFunction, self).__init__(**kw_args)
    # >>> load_mgmt_function


class ConnectDisconnectFunction(DeviceFunction):
    """ A function that will disconnect or reconnect the customer's load under defined conditions. 
    """
    # If disconnection can be operated locally, then this attribute defines whether the operation happens automatically or manually. Values are: "automatic", "manual"
    local_discon_op = 'automatic'

    # If reconnection can be operated locally, then this attribute defines whether the operation happens automatically or manually.  Values are: "automatic", "manual"
    local_recon_op = 'automatic'

    # If disconnection can be operated remotely, then this attribute defines whether the operation happens automatically or manually. Values are: "automatic", "manual"
    remote_discon_op = 'automatic'

    # If reconnection can be operated remotely, then this attribute defines whether the operation happens automatically or manually.  Values are: "automatic", "manual"
    remote_recon_op = 'automatic'

    # The switch may disconnect the service either instantaneously or at the end of a specified time delay after the disconnect signal had been given. This is typically the case for over current circuit-breakers which are classified as either instantaneous or slow acting  Values are: "instant", "delayed"
    disc_delay = 'instant'

    # An event is either connect or a disconnect action. A running cumulative count is kept of events for the lifetime of the function or until the value is cleared.  
    event_count = ''

    # This attribute indicates whether the function is in the connected state or in the disconnected state.  Values are: "connected", "disconnected"
    connection_state = 'connected'

    switches = []

    # <<< connect_disconnect_function
    # @generated
    def __init__(self, local_discon_op='automatic', local_recon_op='automatic', remote_discon_op='automatic', remote_recon_op='automatic', disc_delay='instant', event_count='', connection_state='connected', switches=[], **kw_args):
        """ Initialises a new 'ConnectDisconnectFunction' instance.
        """
        self.local_discon_op = local_discon_op
        self.local_recon_op = local_recon_op
        self.remote_discon_op = remote_discon_op
        self.remote_recon_op = remote_recon_op
        self.disc_delay = disc_delay
        self.event_count = event_count
        self.connection_state = connection_state
        self.switches = switches

        super(ConnectDisconnectFunction, self).__init__(**kw_args)
    # >>> connect_disconnect_function


class SPAccountingFunction(DeviceFunction):
    """ Service point accounting function, particularly for payment meter.
    """
    # This attribute defines that this function is keeping account for one of the service types [electricity, water, gas, time, heat, refuse, sewerage, rates, etc] Values are: "electricity", "gas", "water", "time", "heat", "refuse", "sewerage", "rates", "tv_license", "other"
    service_type = 'electricity'

    # This attribute defines whether this function keeps account in units of currency {USD, CAD, GBP, EUR, etc} or in units of service {kWh, liter, hours, kilo joules, etc} Values are: "energy_based", "currency_based"
    accouning_mode = 'energy_based'

    # The value is the balance of the sum of credits minus the sum of charges from the CreditRegisters and the ChargeRegisters. The sum might be complex function. The units are either in currency units or service units, depending on the value of accountingMode.   
    available_credit = ''

    # The value is a predefined set point for the level of the availableCredit to reach when a warning will be indicated to the customer. It serves to warn the customer that it is time to purchase more credit in the case of a prepayment meter implementation. The units are either in currency units or service units, depending on the value of accountingMode.  
    low_credit_warning_level = ''

    # The value is a predefined set point for the level of the availableCredit to reach when the service will be interrupted. In the case of a prepayment meter the interruption is typically implemented by means of an electro-mechanical switch and the value is typically set = 0. The units are either in currency units or service units, depending on the value of accountingMode.  
    credit_expiry_level = ''

    credit_registers = []

    charge_registers = []

    # <<< spaccounting_function
    # @generated
    def __init__(self, service_type='electricity', accouning_mode='energy_based', available_credit='', low_credit_warning_level='', credit_expiry_level='', credit_registers=[], charge_registers=[], **kw_args):
        """ Initialises a new 'SPAccountingFunction' instance.
        """
        self.service_type = service_type
        self.accouning_mode = accouning_mode
        self.available_credit = available_credit
        self.low_credit_warning_level = low_credit_warning_level
        self.credit_expiry_level = credit_expiry_level
        self.credit_registers = credit_registers
        self.charge_registers = charge_registers

        super(SPAccountingFunction, self).__init__(**kw_args)
    # >>> spaccounting_function


class LoadShedFunction(LoadMgmtFunction):
    """ A kind of LoadMgmtFunction that sheds a part of the customer load.
    """
    # The value of the load that is connected to the shedding switch. Typically this is a noted nominal value rather than a measured value.
    switched_load = None

    # <<< load_shed_function
    # @generated
    def __init__(self, switched_load=None, **kw_args):
        """ Initialises a new 'LoadShedFunction' instance.
        """
        self.switched_load = switched_load

        super(LoadShedFunction, self).__init__(**kw_args)
    # >>> load_shed_function


class LoadLimitFunction(LoadMgmtFunction):
    """ A kind of LoadMgmtFunction that limits the customer load to a given value. 
    """
    # Specifies if the switch will reconnect under manual control or automatically. Values are: "automatic", "manual"
    reconnect_operation = 'automatic'

    # From the point when the maximumLoad threshold is crossed there may be a finite delay before the switch actually disconnects the load. Typically this is to buffer against transient load fluctuations.  
    disconnect_time_delay = ''

    # From the point when the load recovers from an overload condition and crosses the maximumLoad threshold going down, there may be a finite time delay before the switch actually reconnects the load. Typically this is to give overload conditions sufficient time to clear, thus preventing unnecessary load switching activity. 
    reconnect_time_delay = ''

    # The power level, to which the customer load is being limited when this function activates. When the maximum load is exceeded the switch will typically open to shed the complete customer load.
    maximum_load = None

    # <<< load_limit_function
    # @generated
    def __init__(self, reconnect_operation='automatic', disconnect_time_delay='', reconnect_time_delay='', maximum_load=None, **kw_args):
        """ Initialises a new 'LoadLimitFunction' instance.
        """
        self.reconnect_operation = reconnect_operation
        self.disconnect_time_delay = disconnect_time_delay
        self.reconnect_time_delay = reconnect_time_delay
        self.maximum_load = maximum_load

        super(LoadLimitFunction, self).__init__(**kw_args)
    # >>> load_limit_function


# <<< metering
# @generated
# >>> metering

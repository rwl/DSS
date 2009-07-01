# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.work import Work
from cim14 import Element
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import ActivityRecord
from cim14.iec61968.common import Location
from cim14.iec61968.assets import AssetFunction
from cim14.iec61970.meas import MeasurementValue
from cim14.iec61968.assets import AssetContainer

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Metering"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Metering"

class MeterServiceWork(Work):
    """ Work involving meters.
    """
    def get_old_meter_asset(self):
        """ Old meter asset replaced by this work.
        """
        return self._old_meter_asset

    def set_old_meter_asset(self, value):
        if self._old_meter_asset is not None:
            filtered = [x for x in self.old_meter_asset.meter_replacement_works if x != self]
            self._old_meter_asset._meter_replacement_works = filtered
            
        self._old_meter_asset = value
        if self._old_meter_asset is not None:
            self._old_meter_asset._meter_replacement_works.append(self)

    old_meter_asset = property(get_old_meter_asset, set_old_meter_asset)

    def get_meter_asset(self):
        """ Meter asset on which this non-replacement work is performed.
        """
        return self._meter_asset

    def set_meter_asset(self, value):
        if self._meter_asset is not None:
            filtered = [x for x in self.meter_asset.meter_service_works if x != self]
            self._meter_asset._meter_service_works = filtered
            
        self._meter_asset = value
        if self._meter_asset is not None:
            self._meter_asset._meter_service_works.append(self)

    meter_asset = property(get_meter_asset, set_meter_asset)

    # <<< meter_service_work
    # @generated
    def __init__(self, old_meter_asset=None, meter_asset=None, **kw_args):
        """ Initialises a new 'MeterServiceWork' instance.
        """
        self._old_meter_asset = None
        self.old_meter_asset = old_meter_asset
        self._meter_asset = None
        self.meter_asset = meter_asset

        super(MeterServiceWork, self).__init__(**kw_args)
    # >>> meter_service_work


class IntervalBlock(Element):
    """ Time sequence of Readings of the same ReadingType. Contained IntervalReadings may need conversion through the application of an offset and a scalar defined in associated Pending.
    """
    def get_meter_reading(self):
        """ Meter reading containing this interval block.
        """
        return self._meter_reading

    def set_meter_reading(self, value):
        if self._meter_reading is not None:
            filtered = [x for x in self.meter_reading.interval_blocks if x != self]
            self._meter_reading._interval_blocks = filtered
            
        self._meter_reading = value
        if self._meter_reading is not None:
            self._meter_reading._interval_blocks.append(self)

    meter_reading = property(get_meter_reading, set_meter_reading)

    def get_pending(self):
        """ Pending conversion to apply to interval reading values contained by this block (after which the resulting reading type is different than the original because it reflects the conversion result).
        """
        return self._pending

    def set_pending(self, value):
        if self._pending is not None:
            filtered = [x for x in self.pending.interval_blocks if x != self]
            self._pending._interval_blocks = filtered
            
        self._pending = value
        if self._pending is not None:
            self._pending._interval_blocks.append(self)

    pending = property(get_pending, set_pending)

    def get_reading_type(self):
        """ Type information for interval reading values contained in this block.
        """
        return self._reading_type

    def set_reading_type(self, value):
        if self._reading_type is not None:
            filtered = [x for x in self.reading_type.interval_blocks if x != self]
            self._reading_type._interval_blocks = filtered
            
        self._reading_type = value
        if self._reading_type is not None:
            self._reading_type._interval_blocks.append(self)

    reading_type = property(get_reading_type, set_reading_type)

    interval_readings = []
    
    def add_interval_readings(self, *interval_readings):
        for obj in interval_readings:
	        self._interval_readings.append(obj)
        
    def remove_interval_readings(self, *interval_readings):
        for obj in interval_readings:
	        self._interval_readings.remove(obj)

    # <<< interval_block
    # @generated
    def __init__(self, meter_reading=None, pending=None, reading_type=None, interval_readings=[], **kw_args):
        """ Initialises a new 'IntervalBlock' instance.
        """
        self._meter_reading = None
        self.meter_reading = meter_reading
        self._pending = None
        self.pending = pending
        self._reading_type = None
        self.reading_type = reading_type
        self._interval_readings = []
        self.interval_readings = interval_readings

        super(IntervalBlock, self).__init__(**kw_args)
    # >>> interval_block


class ReadingQuality(Element):
    """ Quality of a specific reading value or interval reading value. Note that more than one Quality may be applicable to a given Reading. Typically not unsed unless problems or unusual conditions occur (i.e., quality for each Reading is assumed to be 'Good' unless stated otherwise in associated ReadingQuality).
    """
    # Quality, to be specified if different than 'Good'. 
    quality = ''

    def get_interval_reading(self):
        """ Interval reading value to which this quality applies.
        """
        return self._interval_reading

    def set_interval_reading(self, value):
        if self._interval_reading is not None:
            filtered = [x for x in self.interval_reading.reading_qualities if x != self]
            self._interval_reading._reading_qualities = filtered
            
        self._interval_reading = value
        if self._interval_reading is not None:
            self._interval_reading._reading_qualities.append(self)

    interval_reading = property(get_interval_reading, set_interval_reading)

    def get_reading(self):
        """ Reading value to which this quality applies.
        """
        return self._reading

    def set_reading(self, value):
        if self._reading is not None:
            filtered = [x for x in self.reading.reading_qualities if x != self]
            self._reading._reading_qualities = filtered
            
        self._reading = value
        if self._reading is not None:
            self._reading._reading_qualities.append(self)

    reading = property(get_reading, set_reading)

    # <<< reading_quality
    # @generated
    def __init__(self, quality='', interval_reading=None, reading=None, **kw_args):
        """ Initialises a new 'ReadingQuality' instance.
        """
        self.quality = quality
        self._interval_reading = None
        self.interval_reading = interval_reading
        self._reading = None
        self.reading = reading

        super(ReadingQuality, self).__init__(**kw_args)
    # >>> reading_quality


class EndDeviceGroup(IdentifiedObject):
    """ Abstraction for management of group communications within a two-way AMR system or the data for a group of related meters. Commands can be issued to all of the meters that belong to a meter group using a defined group address and the underlying AMR communication infrastructure.
    """
    # Address of this end device group. 
    group_address = 0

    end_device_assets = []
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
	        self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
	        self._end_device_assets.remove(obj)

    def get_end_device_controls(self):
        """ All end device controls sending commands to this end device group.
        """
        return self._end_device_controls

    def set_end_device_controls(self, value):
        for x in self._end_device_controls:
            x._end_device_group = None
        for y in value:
            y._end_device_group = self
        self._end_device_controls = value
            
    end_device_controls = property(get_end_device_controls, set_end_device_controls)
    
    def add_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._end_device_group = self
            self._end_device_controls.append(obj)
        
    def remove_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._end_device_group = None
            self._end_device_controls.remove(obj)

    def get_demand_response_program(self):
        """ Demand response program for this group of end devices.
        """
        return self._demand_response_program

    def set_demand_response_program(self, value):
        if self._demand_response_program is not None:
            filtered = [x for x in self.demand_response_program.end_device_groups if x != self]
            self._demand_response_program._end_device_groups = filtered
            
        self._demand_response_program = value
        if self._demand_response_program is not None:
            self._demand_response_program._end_device_groups.append(self)

    demand_response_program = property(get_demand_response_program, set_demand_response_program)

    # <<< end_device_group
    # @generated
    def __init__(self, group_address=0, end_device_assets=[], end_device_controls=[], demand_response_program=None, **kw_args):
        """ Initialises a new 'EndDeviceGroup' instance.
        """
        self.group_address = group_address
        self._end_device_assets = []
        self.end_device_assets = end_device_assets
        self._end_device_controls = []
        self.end_device_controls = end_device_controls
        self._demand_response_program = None
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

    def get_device_function(self):
        """ Device function metering quantities displayed by this register.
        """
        return self._device_function

    def set_device_function(self, value):
        if self._device_function is not None:
            filtered = [x for x in self.device_function.registers if x != self]
            self._device_function._registers = filtered
            
        self._device_function = value
        if self._device_function is not None:
            self._device_function._registers.append(self)

    device_function = property(get_device_function, set_device_function)

    def get_reading_type(self):
        """ Reading type for values displayed by this register.
        """
        return self._reading_type

    def set_reading_type(self, value):
        if self._reading_type is not None:
            self._reading_type._register = None
            
        self._reading_type = value
        if self._reading_type is not None:
            self._reading_type._register = self
            
    reading_type = property(get_reading_type, set_reading_type)

    # <<< register
    # @generated
    def __init__(self, left_digit_count=0, right_digit_count=0, device_function=None, reading_type=None, **kw_args):
        """ Initialises a new 'Register' instance.
        """
        self.left_digit_count = left_digit_count
        self.right_digit_count = right_digit_count
        self._device_function = None
        self.device_function = device_function
        self._reading_type = None
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

    def get_meter_readings(self):
        """ All meter readings obtained from this service delivery point.
        """
        return self._meter_readings

    def set_meter_readings(self, value):
        for x in self._meter_readings:
            x._service_delivery_point = None
        for y in value:
            y._service_delivery_point = self
        self._meter_readings = value
            
    meter_readings = property(get_meter_readings, set_meter_readings)
    
    def add_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._service_delivery_point = self
            self._meter_readings.append(obj)
        
    def remove_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._service_delivery_point = None
            self._meter_readings.remove(obj)

    sdplocations = []
    
    def add_sdplocations(self, *sdplocations):
        for obj in sdplocations:
	        self._sdplocations.append(obj)
        
    def remove_sdplocations(self, *sdplocations):
        for obj in sdplocations:
	        self._sdplocations.remove(obj)

    def get_service_category(self):
        """ Service category delivered by this service delivery point.
        """
        return self._service_category

    def set_service_category(self, value):
        if self._service_category is not None:
            filtered = [x for x in self.service_category.service_delivery_points if x != self]
            self._service_category._service_delivery_points = filtered
            
        self._service_category = value
        if self._service_category is not None:
            self._service_category._service_delivery_points.append(self)

    service_category = property(get_service_category, set_service_category)

    def get_service_location(self):
        """ Service location where the service delivered by this service delivery point is consumed.
        """
        return self._service_location

    def set_service_location(self, value):
        if self._service_location is not None:
            filtered = [x for x in self.service_location.service_delivery_points if x != self]
            self._service_location._service_delivery_points = filtered
            
        self._service_location = value
        if self._service_location is not None:
            self._service_location._service_delivery_points.append(self)

    service_location = property(get_service_location, set_service_location)

    def get_service_supplier(self):
        """ ServiceSupplier (Utility) utilising this service delivery point to deliver a service.
        """
        return self._service_supplier

    def set_service_supplier(self, value):
        if self._service_supplier is not None:
            filtered = [x for x in self.service_supplier.service_delivery_points if x != self]
            self._service_supplier._service_delivery_points = filtered
            
        self._service_supplier = value
        if self._service_supplier is not None:
            self._service_supplier._service_delivery_points.append(self)

    service_supplier = property(get_service_supplier, set_service_supplier)

    pricing_structures = []
    
    def add_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.append(obj)
        
    def remove_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.remove(obj)

    def get_end_device_assets(self):
        """ All end device assets at this service delivery point.
        """
        return self._end_device_assets

    def set_end_device_assets(self, value):
        for x in self._end_device_assets:
            x._service_delivery_point = None
        for y in value:
            y._service_delivery_point = self
        self._end_device_assets = value
            
    end_device_assets = property(get_end_device_assets, set_end_device_assets)
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._service_delivery_point = self
            self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._service_delivery_point = None
            self._end_device_assets.remove(obj)

    power_quality_pricings = []
    
    def add_power_quality_pricings(self, *power_quality_pricings):
        for obj in power_quality_pricings:
	        self._power_quality_pricings.append(obj)
        
    def remove_power_quality_pricings(self, *power_quality_pricings):
        for obj in power_quality_pricings:
	        self._power_quality_pricings.remove(obj)

    def get_customer_agreement(self):
        """ Customer agreement regulating this service delivery point.
        """
        return self._customer_agreement

    def set_customer_agreement(self, value):
        if self._customer_agreement is not None:
            filtered = [x for x in self.customer_agreement.service_delivery_points if x != self]
            self._customer_agreement._service_delivery_points = filtered
            
        self._customer_agreement = value
        if self._customer_agreement is not None:
            self._customer_agreement._service_delivery_points.append(self)

    customer_agreement = property(get_customer_agreement, set_customer_agreement)

    def get_energy_consumer(self):
        """ 
        """
        return self._energy_consumer

    def set_energy_consumer(self, value):
        if self._energy_consumer is not None:
            filtered = [x for x in self.energy_consumer.service_delivery_points if x != self]
            self._energy_consumer._service_delivery_points = filtered
            
        self._energy_consumer = value
        if self._energy_consumer is not None:
            self._energy_consumer._service_delivery_points.append(self)

    energy_consumer = property(get_energy_consumer, set_energy_consumer)

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
        self._meter_readings = []
        self.meter_readings = meter_readings
        self._sdplocations = []
        self.sdplocations = sdplocations
        self._service_category = None
        self.service_category = service_category
        self._service_location = None
        self.service_location = service_location
        self._service_supplier = None
        self.service_supplier = service_supplier
        self._pricing_structures = []
        self.pricing_structures = pricing_structures
        self._end_device_assets = []
        self.end_device_assets = end_device_assets
        self._power_quality_pricings = []
        self.power_quality_pricings = power_quality_pricings
        self._customer_agreement = None
        self.customer_agreement = customer_agreement
        self._energy_consumer = None
        self.energy_consumer = energy_consumer

        super(ServiceDeliveryPoint, self).__init__(**kw_args)
    # >>> service_delivery_point


class MeterReading(IdentifiedObject):
    """ Set of values obtained from the meter.
    """
    # Date and time interval of the data items contained within this meter reading.
    values_interval = None

    def get_service_delivery_point(self):
        """ Service delivery point from which this meter reading (set of values) has been obtained.
        """
        return self._service_delivery_point

    def set_service_delivery_point(self, value):
        if self._service_delivery_point is not None:
            filtered = [x for x in self.service_delivery_point.meter_readings if x != self]
            self._service_delivery_point._meter_readings = filtered
            
        self._service_delivery_point = value
        if self._service_delivery_point is not None:
            self._service_delivery_point._meter_readings.append(self)

    service_delivery_point = property(get_service_delivery_point, set_service_delivery_point)

    def get_customer_agreement(self):
        """ (could be deprecated in the future) Customer agreement for this meter reading.
        """
        return self._customer_agreement

    def set_customer_agreement(self, value):
        if self._customer_agreement is not None:
            filtered = [x for x in self.customer_agreement.meter_readings if x != self]
            self._customer_agreement._meter_readings = filtered
            
        self._customer_agreement = value
        if self._customer_agreement is not None:
            self._customer_agreement._meter_readings.append(self)

    customer_agreement = property(get_customer_agreement, set_customer_agreement)

    readings = []
    
    def add_readings(self, *readings):
        for obj in readings:
	        self._readings.append(obj)
        
    def remove_readings(self, *readings):
        for obj in readings:
	        self._readings.remove(obj)

    def get_interval_blocks(self):
        """ All interval blocks contained in this meter reading.
        """
        return self._interval_blocks

    def set_interval_blocks(self, value):
        for x in self._interval_blocks:
            x._meter_reading = None
        for y in value:
            y._meter_reading = self
        self._interval_blocks = value
            
    interval_blocks = property(get_interval_blocks, set_interval_blocks)
    
    def add_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._meter_reading = self
            self._interval_blocks.append(obj)
        
    def remove_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._meter_reading = None
            self._interval_blocks.remove(obj)

    def get_end_device_events(self):
        """ All end device events associated with this set of measured values.
        """
        return self._end_device_events

    def set_end_device_events(self, value):
        for x in self._end_device_events:
            x._meter_reading = None
        for y in value:
            y._meter_reading = self
        self._end_device_events = value
            
    end_device_events = property(get_end_device_events, set_end_device_events)
    
    def add_end_device_events(self, *end_device_events):
        for obj in end_device_events:
            obj._meter_reading = self
            self._end_device_events.append(obj)
        
    def remove_end_device_events(self, *end_device_events):
        for obj in end_device_events:
            obj._meter_reading = None
            self._end_device_events.remove(obj)

    def get_meter_asset(self):
        """ Meter asset providing this meter reading.
        """
        return self._meter_asset

    def set_meter_asset(self, value):
        if self._meter_asset is not None:
            filtered = [x for x in self.meter_asset.meter_readings if x != self]
            self._meter_asset._meter_readings = filtered
            
        self._meter_asset = value
        if self._meter_asset is not None:
            self._meter_asset._meter_readings.append(self)

    meter_asset = property(get_meter_asset, set_meter_asset)

    # <<< meter_reading
    # @generated
    def __init__(self, values_interval=None, service_delivery_point=None, customer_agreement=None, readings=[], interval_blocks=[], end_device_events=[], meter_asset=None, **kw_args):
        """ Initialises a new 'MeterReading' instance.
        """
        self.values_interval = values_interval
        self._service_delivery_point = None
        self.service_delivery_point = service_delivery_point
        self._customer_agreement = None
        self.customer_agreement = customer_agreement
        self._readings = []
        self.readings = readings
        self._interval_blocks = []
        self.interval_blocks = interval_blocks
        self._end_device_events = []
        self.end_device_events = end_device_events
        self._meter_asset = None
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

    def get_demand_response_program(self):
        """ Demand response program for this end device control.
        """
        return self._demand_response_program

    def set_demand_response_program(self, value):
        if self._demand_response_program is not None:
            filtered = [x for x in self.demand_response_program.end_device_controls if x != self]
            self._demand_response_program._end_device_controls = filtered
            
        self._demand_response_program = value
        if self._demand_response_program is not None:
            self._demand_response_program._end_device_controls.append(self)

    demand_response_program = property(get_demand_response_program, set_demand_response_program)

    def get_customer_agreement(self):
        """ Could be deprecated in the future.
        """
        return self._customer_agreement

    def set_customer_agreement(self, value):
        if self._customer_agreement is not None:
            filtered = [x for x in self.customer_agreement.end_device_controls if x != self]
            self._customer_agreement._end_device_controls = filtered
            
        self._customer_agreement = value
        if self._customer_agreement is not None:
            self._customer_agreement._end_device_controls.append(self)

    customer_agreement = property(get_customer_agreement, set_customer_agreement)

    def get_end_device_asset(self):
        """ End device asset receiving commands from this end device control.
        """
        return self._end_device_asset

    def set_end_device_asset(self, value):
        if self._end_device_asset is not None:
            filtered = [x for x in self.end_device_asset.end_device_controls if x != self]
            self._end_device_asset._end_device_controls = filtered
            
        self._end_device_asset = value
        if self._end_device_asset is not None:
            self._end_device_asset._end_device_controls.append(self)

    end_device_asset = property(get_end_device_asset, set_end_device_asset)

    def get_end_device_group(self):
        """ End device group receiving commands from this end device control.
        """
        return self._end_device_group

    def set_end_device_group(self, value):
        if self._end_device_group is not None:
            filtered = [x for x in self.end_device_group.end_device_controls if x != self]
            self._end_device_group._end_device_controls = filtered
            
        self._end_device_group = value
        if self._end_device_group is not None:
            self._end_device_group._end_device_controls.append(self)

    end_device_group = property(get_end_device_group, set_end_device_group)

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
        self._demand_response_program = None
        self.demand_response_program = demand_response_program
        self._customer_agreement = None
        self.customer_agreement = customer_agreement
        self._end_device_asset = None
        self.end_device_asset = end_device_asset
        self._end_device_group = None
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

    def get_interval_blocks(self):
        """ All blocks containing interval reading values with this type information.
        """
        return self._interval_blocks

    def set_interval_blocks(self, value):
        for x in self._interval_blocks:
            x._reading_type = None
        for y in value:
            y._reading_type = self
        self._interval_blocks = value
            
    interval_blocks = property(get_interval_blocks, set_interval_blocks)
    
    def add_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._reading_type = self
            self._interval_blocks.append(obj)
        
    def remove_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._reading_type = None
            self._interval_blocks.remove(obj)

    def get_pending(self):
        """ Pending conversion that produced this reading type.
        """
        return self._pending

    def set_pending(self, value):
        if self._pending is not None:
            self._pending._reading_type = None
            
        self._pending = value
        if self._pending is not None:
            self._pending._reading_type = self
            
    pending = property(get_pending, set_pending)

    def get_readings(self):
        """ All reading values with this type information.
        """
        return self._readings

    def set_readings(self, value):
        for x in self._readings:
            x._reading_type = None
        for y in value:
            y._reading_type = self
        self._readings = value
            
    readings = property(get_readings, set_readings)
    
    def add_readings(self, *readings):
        for obj in readings:
            obj._reading_type = self
            self._readings.append(obj)
        
    def remove_readings(self, *readings):
        for obj in readings:
            obj._reading_type = None
            self._readings.remove(obj)

    def get_register(self):
        """ Register displaying values with this type information.
        """
        return self._register

    def set_register(self, value):
        if self._register is not None:
            self._register._reading_type = None
            
        self._register = value
        if self._register is not None:
            self._register._reading_type = self
            
    register = property(get_register, set_register)

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
        self._interval_blocks = []
        self.interval_blocks = interval_blocks
        self._pending = None
        self.pending = pending
        self._readings = []
        self.readings = readings
        self._register = None
        self.register = register

        super(ReadingType, self).__init__(**kw_args)
    # >>> reading_type


class EndDeviceEvent(ActivityRecord):
    """ Event detected by a DeviceFunction associated with EndDeviceAsset.
    """
    # (if user initiated) ID of user who initiated this end device event. 
    user_id = ''

    def get_device_function(self):
        """ Device function that reported this end device event.
        """
        return self._device_function

    def set_device_function(self, value):
        if self._device_function is not None:
            filtered = [x for x in self.device_function.end_device_events if x != self]
            self._device_function._end_device_events = filtered
            
        self._device_function = value
        if self._device_function is not None:
            self._device_function._end_device_events.append(self)

    device_function = property(get_device_function, set_device_function)

    def get_meter_reading(self):
        """ Set of measured values to which this event applies.
        """
        return self._meter_reading

    def set_meter_reading(self, value):
        if self._meter_reading is not None:
            filtered = [x for x in self.meter_reading.end_device_events if x != self]
            self._meter_reading._end_device_events = filtered
            
        self._meter_reading = value
        if self._meter_reading is not None:
            self._meter_reading._end_device_events.append(self)

    meter_reading = property(get_meter_reading, set_meter_reading)

    # <<< end_device_event
    # @generated
    def __init__(self, user_id='', device_function=None, meter_reading=None, **kw_args):
        """ Initialises a new 'EndDeviceEvent' instance.
        """
        self.user_id = user_id
        self._device_function = None
        self.device_function = device_function
        self._meter_reading = None
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

    service_delivery_points = []
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
	        self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
	        self._service_delivery_points.remove(obj)

    # <<< sdplocation
    # @generated
    def __init__(self, access_method='', site_access_problem='', occupancy_date='', remark='', service_delivery_points=[], **kw_args):
        """ Initialises a new 'SDPLocation' instance.
        """
        self.access_method = access_method
        self.site_access_problem = site_access_problem
        self.occupancy_date = occupancy_date
        self.remark = remark
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points

        super(SDPLocation, self).__init__(**kw_args)
    # >>> sdplocation


class Pending(Element):
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

    def get_reading_type(self):
        """ Reading type resulting from this pending conversion.
        """
        return self._reading_type

    def set_reading_type(self, value):
        if self._reading_type is not None:
            self._reading_type._pending = None
            
        self._reading_type = value
        if self._reading_type is not None:
            self._reading_type._pending = self
            
    reading_type = property(get_reading_type, set_reading_type)

    def get_interval_blocks(self):
        """ All blocks of interval reading values to which this pending conversion applies.
        """
        return self._interval_blocks

    def set_interval_blocks(self, value):
        for x in self._interval_blocks:
            x._pending = None
        for y in value:
            y._pending = self
        self._interval_blocks = value
            
    interval_blocks = property(get_interval_blocks, set_interval_blocks)
    
    def add_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._pending = self
            self._interval_blocks.append(obj)
        
    def remove_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
            obj._pending = None
            self._interval_blocks.remove(obj)

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
        self._reading_type = None
        self.reading_type = reading_type
        self._interval_blocks = []
        self.interval_blocks = interval_blocks

        super(Pending, self).__init__(**kw_args)
    # >>> pending


class DeviceFunction(AssetFunction):
    """ Function performed by a device such as a meter, communication equipment, controllers, etc.
    """
    # True if the device function is disabled (deactivated). Default is false (i.e., function is enabled). 
    disabled = False

    def get_end_device_asset(self):
        """ End device asset that performs this function.
        """
        return self._end_device_asset

    def set_end_device_asset(self, value):
        if self._end_device_asset is not None:
            filtered = [x for x in self.end_device_asset.device_functions if x != self]
            self._end_device_asset._device_functions = filtered
            
        self._end_device_asset = value
        if self._end_device_asset is not None:
            self._end_device_asset._device_functions.append(self)

    end_device_asset = property(get_end_device_asset, set_end_device_asset)

    def get_com_equipment_asset(self):
        """ Communication equipment asset performing this device function.
        """
        return self._com_equipment_asset

    def set_com_equipment_asset(self, value):
        if self._com_equipment_asset is not None:
            filtered = [x for x in self.com_equipment_asset.device_functions if x != self]
            self._com_equipment_asset._device_functions = filtered
            
        self._com_equipment_asset = value
        if self._com_equipment_asset is not None:
            self._com_equipment_asset._device_functions.append(self)

    com_equipment_asset = property(get_com_equipment_asset, set_com_equipment_asset)

    def get_registers(self):
        """ All registers for quantities metered by this device function.
        """
        return self._registers

    def set_registers(self, value):
        for x in self._registers:
            x._device_function = None
        for y in value:
            y._device_function = self
        self._registers = value
            
    registers = property(get_registers, set_registers)
    
    def add_registers(self, *registers):
        for obj in registers:
            obj._device_function = self
            self._registers.append(obj)
        
    def remove_registers(self, *registers):
        for obj in registers:
            obj._device_function = None
            self._registers.remove(obj)

    def get_end_device_events(self):
        """ All events reported by this device function.
        """
        return self._end_device_events

    def set_end_device_events(self, value):
        for x in self._end_device_events:
            x._device_function = None
        for y in value:
            y._device_function = self
        self._end_device_events = value
            
    end_device_events = property(get_end_device_events, set_end_device_events)
    
    def add_end_device_events(self, *end_device_events):
        for obj in end_device_events:
            obj._device_function = self
            self._end_device_events.append(obj)
        
    def remove_end_device_events(self, *end_device_events):
        for obj in end_device_events:
            obj._device_function = None
            self._end_device_events.remove(obj)

    # <<< device_function
    # @generated
    def __init__(self, disabled=False, end_device_asset=None, com_equipment_asset=None, registers=[], end_device_events=[], **kw_args):
        """ Initialises a new 'DeviceFunction' instance.
        """
        self.disabled = disabled
        self._end_device_asset = None
        self.end_device_asset = end_device_asset
        self._com_equipment_asset = None
        self.com_equipment_asset = com_equipment_asset
        self._registers = []
        self.registers = registers
        self._end_device_events = []
        self.end_device_events = end_device_events

        super(DeviceFunction, self).__init__(**kw_args)
    # >>> device_function


class Reading(MeasurementValue):
    """ Specific value measured by a meter or other asset. Each Reading is associated with a specific ReadingType.
    """
    # Value of this reading. 
    value = 0.0

    def get_reading_type(self):
        """ Type information for this reading value.
        """
        return self._reading_type

    def set_reading_type(self, value):
        if self._reading_type is not None:
            filtered = [x for x in self.reading_type.readings if x != self]
            self._reading_type._readings = filtered
            
        self._reading_type = value
        if self._reading_type is not None:
            self._reading_type._readings.append(self)

    reading_type = property(get_reading_type, set_reading_type)

    meter_readings = []
    
    def add_meter_readings(self, *meter_readings):
        for obj in meter_readings:
	        self._meter_readings.append(obj)
        
    def remove_meter_readings(self, *meter_readings):
        for obj in meter_readings:
	        self._meter_readings.remove(obj)

    def get_reading_qualities(self):
        """ Used only if quality of this reading value is different than 'Good'.
        """
        return self._reading_qualities

    def set_reading_qualities(self, value):
        for x in self._reading_qualities:
            x._reading = None
        for y in value:
            y._reading = self
        self._reading_qualities = value
            
    reading_qualities = property(get_reading_qualities, set_reading_qualities)
    
    def add_reading_qualities(self, *reading_qualities):
        for obj in reading_qualities:
            obj._reading = self
            self._reading_qualities.append(obj)
        
    def remove_reading_qualities(self, *reading_qualities):
        for obj in reading_qualities:
            obj._reading = None
            self._reading_qualities.remove(obj)

    def get_end_device_asset(self):
        """ 
        """
        return self._end_device_asset

    def set_end_device_asset(self, value):
        if self._end_device_asset is not None:
            filtered = [x for x in self.end_device_asset.readings if x != self]
            self._end_device_asset._readings = filtered
            
        self._end_device_asset = value
        if self._end_device_asset is not None:
            self._end_device_asset._readings.append(self)

    end_device_asset = property(get_end_device_asset, set_end_device_asset)

    # <<< reading
    # @generated
    def __init__(self, value=0.0, reading_type=None, meter_readings=[], reading_qualities=[], end_device_asset=None, **kw_args):
        """ Initialises a new 'Reading' instance.
        """
        self.value = value
        self._reading_type = None
        self.reading_type = reading_type
        self._meter_readings = []
        self.meter_readings = meter_readings
        self._reading_qualities = []
        self.reading_qualities = reading_qualities
        self._end_device_asset = None
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

    electrical_infos = []
    
    def add_electrical_infos(self, *electrical_infos):
        for obj in electrical_infos:
	        self._electrical_infos.append(obj)
        
    def remove_electrical_infos(self, *electrical_infos):
        for obj in electrical_infos:
	        self._electrical_infos.remove(obj)

    def get_customer(self):
        """ Customer owning this end device asset.
        """
        return self._customer

    def set_customer(self, value):
        if self._customer is not None:
            filtered = [x for x in self.customer.end_device_assets if x != self]
            self._customer._end_device_assets = filtered
            
        self._customer = value
        if self._customer is not None:
            self._customer._end_device_assets.append(self)

    customer = property(get_customer, set_customer)

    def get_device_functions(self):
        """ All device functions this end device asset performs.
        """
        return self._device_functions

    def set_device_functions(self, value):
        for x in self._device_functions:
            x._end_device_asset = None
        for y in value:
            y._end_device_asset = self
        self._device_functions = value
            
    device_functions = property(get_device_functions, set_device_functions)
    
    def add_device_functions(self, *device_functions):
        for obj in device_functions:
            obj._end_device_asset = self
            self._device_functions.append(obj)
        
    def remove_device_functions(self, *device_functions):
        for obj in device_functions:
            obj._end_device_asset = None
            self._device_functions.remove(obj)

    end_device_groups = []
    
    def add_end_device_groups(self, *end_device_groups):
        for obj in end_device_groups:
	        self._end_device_groups.append(obj)
        
    def remove_end_device_groups(self, *end_device_groups):
        for obj in end_device_groups:
	        self._end_device_groups.remove(obj)

    def get_end_device_model(self):
        """ Product documentation for this end device asset.
        """
        return self._end_device_model

    def set_end_device_model(self, value):
        if self._end_device_model is not None:
            filtered = [x for x in self.end_device_model.end_device_assets if x != self]
            self._end_device_model._end_device_assets = filtered
            
        self._end_device_model = value
        if self._end_device_model is not None:
            self._end_device_model._end_device_assets.append(self)

    end_device_model = property(get_end_device_model, set_end_device_model)

    def get_end_device_controls(self):
        """ All end device controls sending commands to this end device asset.
        """
        return self._end_device_controls

    def set_end_device_controls(self, value):
        for x in self._end_device_controls:
            x._end_device_asset = None
        for y in value:
            y._end_device_asset = self
        self._end_device_controls = value
            
    end_device_controls = property(get_end_device_controls, set_end_device_controls)
    
    def add_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._end_device_asset = self
            self._end_device_controls.append(obj)
        
    def remove_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._end_device_asset = None
            self._end_device_controls.remove(obj)

    def get_readings(self):
        """ 
        """
        return self._readings

    def set_readings(self, value):
        for x in self._readings:
            x._end_device_asset = None
        for y in value:
            y._end_device_asset = self
        self._readings = value
            
    readings = property(get_readings, set_readings)
    
    def add_readings(self, *readings):
        for obj in readings:
            obj._end_device_asset = self
            self._readings.append(obj)
        
    def remove_readings(self, *readings):
        for obj in readings:
            obj._end_device_asset = None
            self._readings.remove(obj)

    def get_service_location(self):
        """ Service location whose service delivery is measured by this end device asset.
        """
        return self._service_location

    def set_service_location(self, value):
        if self._service_location is not None:
            filtered = [x for x in self.service_location.end_device_assets if x != self]
            self._service_location._end_device_assets = filtered
            
        self._service_location = value
        if self._service_location is not None:
            self._service_location._end_device_assets.append(self)

    service_location = property(get_service_location, set_service_location)

    def get_service_delivery_point(self):
        """ Service delivery point to which this end device asset belongs.
        """
        return self._service_delivery_point

    def set_service_delivery_point(self, value):
        if self._service_delivery_point is not None:
            filtered = [x for x in self.service_delivery_point.end_device_assets if x != self]
            self._service_delivery_point._end_device_assets = filtered
            
        self._service_delivery_point = value
        if self._service_delivery_point is not None:
            self._service_delivery_point._end_device_assets.append(self)

    service_delivery_point = property(get_service_delivery_point, set_service_delivery_point)

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
        self._electrical_infos = []
        self.electrical_infos = electrical_infos
        self._customer = None
        self.customer = customer
        self._device_functions = []
        self.device_functions = device_functions
        self._end_device_groups = []
        self.end_device_groups = end_device_groups
        self._end_device_model = None
        self.end_device_model = end_device_model
        self._end_device_controls = []
        self.end_device_controls = end_device_controls
        self._readings = []
        self.readings = readings
        self._service_location = None
        self.service_location = service_location
        self._service_delivery_point = None
        self.service_delivery_point = service_delivery_point

        super(EndDeviceAsset, self).__init__(**kw_args)
    # >>> end_device_asset


class IntervalReading(MeasurementValue):
    """ Data captured at regular intervals of time. Interval data could be captured as incremental data, absolute data, or relative data. The source for the data is usually a tariff quantity or an engineering quantity. Data is typically captured in time-tagged, uniform, fixed-length intervals of 5, 10, 15, 30, or 60 minutes. Note: Interval Data is sometimes also called 'Interval Data Readings' (IDR).
    """
    # Value of this interval reading. 
    value = 0.0

    def get_reading_qualities(self):
        """ Used only if quality of this interval reading value is different than 'Good'.
        """
        return self._reading_qualities

    def set_reading_qualities(self, value):
        for x in self._reading_qualities:
            x._interval_reading = None
        for y in value:
            y._interval_reading = self
        self._reading_qualities = value
            
    reading_qualities = property(get_reading_qualities, set_reading_qualities)
    
    def add_reading_qualities(self, *reading_qualities):
        for obj in reading_qualities:
            obj._interval_reading = self
            self._reading_qualities.append(obj)
        
    def remove_reading_qualities(self, *reading_qualities):
        for obj in reading_qualities:
            obj._interval_reading = None
            self._reading_qualities.remove(obj)

    interval_blocks = []
    
    def add_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
	        self._interval_blocks.append(obj)
        
    def remove_interval_blocks(self, *interval_blocks):
        for obj in interval_blocks:
	        self._interval_blocks.remove(obj)

    # <<< interval_reading
    # @generated
    def __init__(self, value=0.0, reading_qualities=[], interval_blocks=[], **kw_args):
        """ Initialises a new 'IntervalReading' instance.
        """
        self.value = value
        self._reading_qualities = []
        self.reading_qualities = reading_qualities
        self._interval_blocks = []
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

    def get_end_device_groups(self):
        """ All groups of end devices with this demand response program.
        """
        return self._end_device_groups

    def set_end_device_groups(self, value):
        for x in self._end_device_groups:
            x._demand_response_program = None
        for y in value:
            y._demand_response_program = self
        self._end_device_groups = value
            
    end_device_groups = property(get_end_device_groups, set_end_device_groups)
    
    def add_end_device_groups(self, *end_device_groups):
        for obj in end_device_groups:
            obj._demand_response_program = self
            self._end_device_groups.append(obj)
        
    def remove_end_device_groups(self, *end_device_groups):
        for obj in end_device_groups:
            obj._demand_response_program = None
            self._end_device_groups.remove(obj)

    def get_end_device_controls(self):
        """ All end device controls with this demand response program.
        """
        return self._end_device_controls

    def set_end_device_controls(self, value):
        for x in self._end_device_controls:
            x._demand_response_program = None
        for y in value:
            y._demand_response_program = self
        self._end_device_controls = value
            
    end_device_controls = property(get_end_device_controls, set_end_device_controls)
    
    def add_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._demand_response_program = self
            self._end_device_controls.append(obj)
        
    def remove_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._demand_response_program = None
            self._end_device_controls.remove(obj)

    def get_customer_agreements(self):
        """ All customer agreements with this demand response program.
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x._demand_response_program = None
        for y in value:
            y._demand_response_program = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._demand_response_program = self
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._demand_response_program = None
            self._customer_agreements.remove(obj)

    # <<< demand_response_program
    # @generated
    def __init__(self, type='', validity_interval=None, end_device_groups=[], end_device_controls=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'DemandResponseProgram' instance.
        """
        self.type = type
        self.validity_interval = validity_interval
        self._end_device_groups = []
        self.end_device_groups = end_device_groups
        self._end_device_controls = []
        self.end_device_controls = end_device_controls
        self._customer_agreements = []
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

    def get_metering_function_configuration(self):
        """ Configuration for this electric metering function.
        """
        return self._metering_function_configuration

    def set_metering_function_configuration(self, value):
        if self._metering_function_configuration is not None:
            filtered = [x for x in self.metering_function_configuration.electric_metering_functions if x != self]
            self._metering_function_configuration._electric_metering_functions = filtered
            
        self._metering_function_configuration = value
        if self._metering_function_configuration is not None:
            self._metering_function_configuration._electric_metering_functions.append(self)

    metering_function_configuration = property(get_metering_function_configuration, set_metering_function_configuration)

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
        self._metering_function_configuration = None
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

    def get_meter_service_works(self):
        """ All non-replacement works on this meter asset.
        """
        return self._meter_service_works

    def set_meter_service_works(self, value):
        for x in self._meter_service_works:
            x._meter_asset = None
        for y in value:
            y._meter_asset = self
        self._meter_service_works = value
            
    meter_service_works = property(get_meter_service_works, set_meter_service_works)
    
    def add_meter_service_works(self, *meter_service_works):
        for obj in meter_service_works:
            obj._meter_asset = self
            self._meter_service_works.append(obj)
        
    def remove_meter_service_works(self, *meter_service_works):
        for obj in meter_service_works:
            obj._meter_asset = None
            self._meter_service_works.remove(obj)

    def get_meter_asset_model(self):
        """ 
        """
        return self._meter_asset_model

    def set_meter_asset_model(self, value):
        if self._meter_asset_model is not None:
            filtered = [x for x in self.meter_asset_model.meter_assets if x != self]
            self._meter_asset_model._meter_assets = filtered
            
        self._meter_asset_model = value
        if self._meter_asset_model is not None:
            self._meter_asset_model._meter_assets.append(self)

    meter_asset_model = property(get_meter_asset_model, set_meter_asset_model)

    def get_meter_readings(self):
        """ All meter readings provided by this meter asset.
        """
        return self._meter_readings

    def set_meter_readings(self, value):
        for x in self._meter_readings:
            x._meter_asset = None
        for y in value:
            y._meter_asset = self
        self._meter_readings = value
            
    meter_readings = property(get_meter_readings, set_meter_readings)
    
    def add_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._meter_asset = self
            self._meter_readings.append(obj)
        
    def remove_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._meter_asset = None
            self._meter_readings.remove(obj)

    def get_vending_transactions(self):
        """ All vending transactions on this meter asset.
        """
        return self._vending_transactions

    def set_vending_transactions(self, value):
        for x in self._vending_transactions:
            x._meter_asset = None
        for y in value:
            y._meter_asset = self
        self._vending_transactions = value
            
    vending_transactions = property(get_vending_transactions, set_vending_transactions)
    
    def add_vending_transactions(self, *vending_transactions):
        for obj in vending_transactions:
            obj._meter_asset = self
            self._vending_transactions.append(obj)
        
    def remove_vending_transactions(self, *vending_transactions):
        for obj in vending_transactions:
            obj._meter_asset = None
            self._vending_transactions.remove(obj)

    def get_meter_replacement_works(self):
        """ All works on replacement of this old meter asset.
        """
        return self._meter_replacement_works

    def set_meter_replacement_works(self, value):
        for x in self._meter_replacement_works:
            x._old_meter_asset = None
        for y in value:
            y._old_meter_asset = self
        self._meter_replacement_works = value
            
    meter_replacement_works = property(get_meter_replacement_works, set_meter_replacement_works)
    
    def add_meter_replacement_works(self, *meter_replacement_works):
        for obj in meter_replacement_works:
            obj._old_meter_asset = self
            self._meter_replacement_works.append(obj)
        
    def remove_meter_replacement_works(self, *meter_replacement_works):
        for obj in meter_replacement_works:
            obj._old_meter_asset = None
            self._meter_replacement_works.remove(obj)

    # <<< meter_asset
    # @generated
    def __init__(self, k_h=0.0, form_number='', k_r=0.0, meter_service_works=[], meter_asset_model=None, meter_readings=[], vending_transactions=[], meter_replacement_works=[], **kw_args):
        """ Initialises a new 'MeterAsset' instance.
        """
        self.k_h = k_h
        self.form_number = form_number
        self.k_r = k_r
        self._meter_service_works = []
        self.meter_service_works = meter_service_works
        self._meter_asset_model = None
        self.meter_asset_model = meter_asset_model
        self._meter_readings = []
        self.meter_readings = meter_readings
        self._vending_transactions = []
        self.vending_transactions = vending_transactions
        self._meter_replacement_works = []
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

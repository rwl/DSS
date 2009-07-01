# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element
from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.control_area import ControlArea
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Agreement
from cim14.iec61970.core import RegularIntervalSchedule
from cim14.iec61970.core import Curve
from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.EnergyScheduling"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#EnergyScheduling"

class TieLine(Element):
    def get_customer_consumer(self):
        """ A CustomerConsumer may ring its perimeter with metering, which can create a unique SubControlArea at the collection of metering points, called a TieLine.
        """
        return self._customer_consumer

    def set_customer_consumer(self, value):
        if self._customer_consumer is not None:
            filtered = [x for x in self.customer_consumer.tie_lines if x != self]
            self._customer_consumer._tie_lines = filtered
            
        self._customer_consumer = value
        if self._customer_consumer is not None:
            self._customer_consumer._tie_lines.append(self)

    customer_consumer = property(get_customer_consumer, set_customer_consumer)

    control_area_operators = []
    
    def add_control_area_operators(self, *control_area_operators):
        for obj in control_area_operators:
	        self._control_area_operators.append(obj)
        
    def remove_control_area_operators(self, *control_area_operators):
        for obj in control_area_operators:
	        self._control_area_operators.remove(obj)

    def get_side_a_sub_control_area(self):
        """ The SubControlArea is on the A side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
        """
        return self._side_a_sub_control_area

    def set_side_a_sub_control_area(self, value):
        if self._side_a_sub_control_area is not None:
            filtered = [x for x in self.side_a_sub_control_area.side_a_tie_lines if x != self]
            self._side_a_sub_control_area._side_a_tie_lines = filtered
            
        self._side_a_sub_control_area = value
        if self._side_a_sub_control_area is not None:
            self._side_a_sub_control_area._side_a_tie_lines.append(self)

    side_a_sub_control_area = property(get_side_a_sub_control_area, set_side_a_sub_control_area)

    def get_side_a_host_control_area(self):
        """ A HostControlArea can have zero or more tie lines.
        """
        return self._side_a_host_control_area

    def set_side_a_host_control_area(self, value):
        if self._side_a_host_control_area is not None:
            filtered = [x for x in self.side_a_host_control_area.side_a_tie_lines if x != self]
            self._side_a_host_control_area._side_a_tie_lines = filtered
            
        self._side_a_host_control_area = value
        if self._side_a_host_control_area is not None:
            self._side_a_host_control_area._side_a_tie_lines.append(self)

    side_a_host_control_area = property(get_side_a_host_control_area, set_side_a_host_control_area)

    def get_side_b_host_control_area(self):
        """ A HostControlArea can have zero or more tie lines.
        """
        return self._side_b_host_control_area

    def set_side_b_host_control_area(self, value):
        if self._side_b_host_control_area is not None:
            filtered = [x for x in self.side_b_host_control_area.side_b_tie_lines if x != self]
            self._side_b_host_control_area._side_b_tie_lines = filtered
            
        self._side_b_host_control_area = value
        if self._side_b_host_control_area is not None:
            self._side_b_host_control_area._side_b_tie_lines.append(self)

    side_b_host_control_area = property(get_side_b_host_control_area, set_side_b_host_control_area)

    def get_side_b_sub_control_area(self):
        """ The SubControlArea is on the B side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
        """
        return self._side_b_sub_control_area

    def set_side_b_sub_control_area(self, value):
        if self._side_b_sub_control_area is not None:
            filtered = [x for x in self.side_b_sub_control_area.side_b_tie_lines if x != self]
            self._side_b_sub_control_area._side_b_tie_lines = filtered
            
        self._side_b_sub_control_area = value
        if self._side_b_sub_control_area is not None:
            self._side_b_sub_control_area._side_b_tie_lines.append(self)

    side_b_sub_control_area = property(get_side_b_sub_control_area, set_side_b_sub_control_area)

    def get_dynamic_energy_transaction(self):
        """ A dynamic energy transaction can act as a pseudo tie line.
        """
        return self._dynamic_energy_transaction

    def set_dynamic_energy_transaction(self, value):
        if self._dynamic_energy_transaction is not None:
            filtered = [x for x in self.dynamic_energy_transaction.tie_lines if x != self]
            self._dynamic_energy_transaction._tie_lines = filtered
            
        self._dynamic_energy_transaction = value
        if self._dynamic_energy_transaction is not None:
            self._dynamic_energy_transaction._tie_lines.append(self)

    dynamic_energy_transaction = property(get_dynamic_energy_transaction, set_dynamic_energy_transaction)

    # <<< tie_line
    # @generated
    def __init__(self, customer_consumer=None, control_area_operators=[], side_a_sub_control_area=None, side_a_host_control_area=None, side_b_host_control_area=None, side_b_sub_control_area=None, dynamic_energy_transaction=None, **kw_args):
        """ Initialises a new 'TieLine' instance.
        """
        self._customer_consumer = None
        self.customer_consumer = customer_consumer
        self._control_area_operators = []
        self.control_area_operators = control_area_operators
        self._side_a_sub_control_area = None
        self.side_a_sub_control_area = side_a_sub_control_area
        self._side_a_host_control_area = None
        self.side_a_host_control_area = side_a_host_control_area
        self._side_b_host_control_area = None
        self.side_b_host_control_area = side_b_host_control_area
        self._side_b_sub_control_area = None
        self.side_b_sub_control_area = side_b_sub_control_area
        self._dynamic_energy_transaction = None
        self.dynamic_energy_transaction = dynamic_energy_transaction

        super(TieLine, self).__init__(**kw_args)
    # >>> tie_line


class TransmissionCorridor(PowerSystemResource):
    """ A corridor containing one or more rights of way
    """
    def get_transmission_right_of_ways(self):
        """ A transmission right-of-way is a member of a transmission corridor
        """
        return self._transmission_right_of_ways

    def set_transmission_right_of_ways(self, value):
        for x in self._transmission_right_of_ways:
            x._transmission_corridor = None
        for y in value:
            y._transmission_corridor = self
        self._transmission_right_of_ways = value
            
    transmission_right_of_ways = property(get_transmission_right_of_ways, set_transmission_right_of_ways)
    
    def add_transmission_right_of_ways(self, *transmission_right_of_ways):
        for obj in transmission_right_of_ways:
            obj._transmission_corridor = self
            self._transmission_right_of_ways.append(obj)
        
    def remove_transmission_right_of_ways(self, *transmission_right_of_ways):
        for obj in transmission_right_of_ways:
            obj._transmission_corridor = None
            self._transmission_right_of_ways.remove(obj)

    def get_contained_in(self):
        """ A TransmissionPath is contained in a TransmissionCorridor.
        """
        return self._contained_in

    def set_contained_in(self, value):
        for x in self._contained_in:
            x._for = None
        for y in value:
            y._for = self
        self._contained_in = value
            
    contained_in = property(get_contained_in, set_contained_in)
    
    def add_contained_in(self, *contained_in):
        for obj in contained_in:
            obj._for = self
            self._contained_in.append(obj)
        
    def remove_contained_in(self, *contained_in):
        for obj in contained_in:
            obj._for = None
            self._contained_in.remove(obj)

    # <<< transmission_corridor
    # @generated
    def __init__(self, transmission_right_of_ways=[], contained_in=[], **kw_args):
        """ Initialises a new 'TransmissionCorridor' instance.
        """
        self._transmission_right_of_ways = []
        self.transmission_right_of_ways = transmission_right_of_ways
        self._contained_in = []
        self.contained_in = contained_in

        super(TransmissionCorridor, self).__init__(**kw_args)
    # >>> transmission_corridor


class SubControlArea(ControlArea):
    """ SubControlArea replacement classed moved into EnergySchedulingPackage.  An area defined for the purpose of tracking interchange with surrounding areas via tie points; may or may not serve as a control area.
    """
    def get_flowgate(self):
        """ A control area may own 0 to n flowgates A flowgate must be owned by exactly 1 control area
        """
        return self._flowgate

    def set_flowgate(self, value):
        for x in self._flowgate:
            x._sub_control_area = None
        for y in value:
            y._sub_control_area = self
        self._flowgate = value
            
    flowgate = property(get_flowgate, set_flowgate)
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._sub_control_area = self
            self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._sub_control_area = None
            self._flowgate.remove(obj)

    def get_part_of(self):
        """ A transmission path's service point is part of an interchange area
        """
        return self._part_of

    def set_part_of(self, value):
        for x in self._part_of:
            x._member_of = None
        for y in value:
            y._member_of = self
        self._part_of = value
            
    part_of = property(get_part_of, set_part_of)
    
    def add_part_of(self, *part_of):
        for obj in part_of:
            obj._member_of = self
            self._part_of.append(obj)
        
    def remove_part_of(self, *part_of):
        for obj in part_of:
            obj._member_of = None
            self._part_of.remove(obj)

    def get_export_energy_transactions(self):
        """ Energy is transferred between interchange areas
        """
        return self._export_energy_transactions

    def set_export_energy_transactions(self, value):
        for x in self._export_energy_transactions:
            x._export_sub_control_area = None
        for y in value:
            y._export_sub_control_area = self
        self._export_energy_transactions = value
            
    export_energy_transactions = property(get_export_energy_transactions, set_export_energy_transactions)
    
    def add_export_energy_transactions(self, *export_energy_transactions):
        for obj in export_energy_transactions:
            obj._export_sub_control_area = self
            self._export_energy_transactions.append(obj)
        
    def remove_export_energy_transactions(self, *export_energy_transactions):
        for obj in export_energy_transactions:
            obj._export_sub_control_area = None
            self._export_energy_transactions.remove(obj)

    def get_side_a_tie_lines(self):
        """ The SubControlArea is on the A side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
        """
        return self._side_a_tie_lines

    def set_side_a_tie_lines(self, value):
        for x in self._side_a_tie_lines:
            x._side_a_sub_control_area = None
        for y in value:
            y._side_a_sub_control_area = self
        self._side_a_tie_lines = value
            
    side_a_tie_lines = property(get_side_a_tie_lines, set_side_a_tie_lines)
    
    def add_side_a_tie_lines(self, *side_a_tie_lines):
        for obj in side_a_tie_lines:
            obj._side_a_sub_control_area = self
            self._side_a_tie_lines.append(obj)
        
    def remove_side_a_tie_lines(self, *side_a_tie_lines):
        for obj in side_a_tie_lines:
            obj._side_a_sub_control_area = None
            self._side_a_tie_lines.remove(obj)

    def get_generating_units(self):
        """ A GeneratingUnit injects energy into a SubControlArea.
        """
        return self._generating_units

    def set_generating_units(self, value):
        for x in self._generating_units:
            x._sub_control_area = None
        for y in value:
            y._sub_control_area = self
        self._generating_units = value
            
    generating_units = property(get_generating_units, set_generating_units)
    
    def add_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._sub_control_area = self
            self._generating_units.append(obj)
        
    def remove_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._sub_control_area = None
            self._generating_units.remove(obj)

    def get_host_control_area(self):
        """ The interchange area  may operate as a control area
        """
        return self._host_control_area

    def set_host_control_area(self, value):
        if self._host_control_area is not None:
            filtered = [x for x in self.host_control_area.sub_control_areas if x != self]
            self._host_control_area._sub_control_areas = filtered
            
        self._host_control_area = value
        if self._host_control_area is not None:
            self._host_control_area._sub_control_areas.append(self)

    host_control_area = property(get_host_control_area, set_host_control_area)

    def get_import_energy_transactions(self):
        """ Energy is transferred between interchange areas
        """
        return self._import_energy_transactions

    def set_import_energy_transactions(self, value):
        for x in self._import_energy_transactions:
            x._import_sub_control_area = None
        for y in value:
            y._import_sub_control_area = self
        self._import_energy_transactions = value
            
    import_energy_transactions = property(get_import_energy_transactions, set_import_energy_transactions)
    
    def add_import_energy_transactions(self, *import_energy_transactions):
        for obj in import_energy_transactions:
            obj._import_sub_control_area = self
            self._import_energy_transactions.append(obj)
        
    def remove_import_energy_transactions(self, *import_energy_transactions):
        for obj in import_energy_transactions:
            obj._import_sub_control_area = None
            self._import_energy_transactions.remove(obj)

    def get_side_b_tie_lines(self):
        """ The SubControlArea is on the B side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
        """
        return self._side_b_tie_lines

    def set_side_b_tie_lines(self, value):
        for x in self._side_b_tie_lines:
            x._side_b_sub_control_area = None
        for y in value:
            y._side_b_sub_control_area = self
        self._side_b_tie_lines = value
            
    side_b_tie_lines = property(get_side_b_tie_lines, set_side_b_tie_lines)
    
    def add_side_b_tie_lines(self, *side_b_tie_lines):
        for obj in side_b_tie_lines:
            obj._side_b_sub_control_area = self
            self._side_b_tie_lines.append(obj)
        
    def remove_side_b_tie_lines(self, *side_b_tie_lines):
        for obj in side_b_tie_lines:
            obj._side_b_sub_control_area = None
            self._side_b_tie_lines.remove(obj)

    # <<< sub_control_area
    # @generated
    def __init__(self, flowgate=[], part_of=[], export_energy_transactions=[], side_a_tie_lines=[], generating_units=[], host_control_area=None, import_energy_transactions=[], side_b_tie_lines=[], **kw_args):
        """ Initialises a new 'SubControlArea' instance.
        """
        self._flowgate = []
        self.flowgate = flowgate
        self._part_of = []
        self.part_of = part_of
        self._export_energy_transactions = []
        self.export_energy_transactions = export_energy_transactions
        self._side_a_tie_lines = []
        self.side_a_tie_lines = side_a_tie_lines
        self._generating_units = []
        self.generating_units = generating_units
        self._host_control_area = None
        self.host_control_area = host_control_area
        self._import_energy_transactions = []
        self.import_energy_transactions = import_energy_transactions
        self._side_b_tie_lines = []
        self.side_b_tie_lines = side_b_tie_lines

        super(SubControlArea, self).__init__(**kw_args)
    # >>> sub_control_area


class EnergySchedulingVersion(Element):
 
    date = ''

    # v 4 moved SubControlArea 
    version = ''

    # <<< energy_scheduling_version
    # @generated
    def __init__(self, date='', version='', **kw_args):
        """ Initialises a new 'EnergySchedulingVersion' instance.
        """
        self.date = date
        self.version = version

        super(EnergySchedulingVersion, self).__init__(**kw_args)
    # >>> energy_scheduling_version


class TransmissionRightOfWay(PowerSystemResource):
    """ A collection of transmission lines that are close proximity to each other.
    """
    def get_transmission_corridor(self):
        """ A transmission right-of-way is a member of a transmission corridor
        """
        return self._transmission_corridor

    def set_transmission_corridor(self, value):
        if self._transmission_corridor is not None:
            filtered = [x for x in self.transmission_corridor.transmission_right_of_ways if x != self]
            self._transmission_corridor._transmission_right_of_ways = filtered
            
        self._transmission_corridor = value
        if self._transmission_corridor is not None:
            self._transmission_corridor._transmission_right_of_ways.append(self)

    transmission_corridor = property(get_transmission_corridor, set_transmission_corridor)

    def get_lines(self):
        """ A transmission line can be part of a transmission corridor
        """
        return self._lines

    def set_lines(self, value):
        for x in self._lines:
            x._transmission_right_of_way = None
        for y in value:
            y._transmission_right_of_way = self
        self._lines = value
            
    lines = property(get_lines, set_lines)
    
    def add_lines(self, *lines):
        for obj in lines:
            obj._transmission_right_of_way = self
            self._lines.append(obj)
        
    def remove_lines(self, *lines):
        for obj in lines:
            obj._transmission_right_of_way = None
            self._lines.remove(obj)

    # <<< transmission_right_of_way
    # @generated
    def __init__(self, transmission_corridor=None, lines=[], **kw_args):
        """ Initialises a new 'TransmissionRightOfWay' instance.
        """
        self._transmission_corridor = None
        self.transmission_corridor = transmission_corridor
        self._lines = []
        self.lines = lines

        super(TransmissionRightOfWay, self).__init__(**kw_args)
    # >>> transmission_right_of_way


class HostControlArea(IdentifiedObject):
    """ A HostControlArea has a set of tie points and a set of generator controls (i.e., AGC). It also has a total load, including transmission and distribution losses.
    """
    # The area's present control mode: (CF = constant frequency) or (CTL = constant tie-line) or (TLB = tie-line bias) or (OFF = off control) Values are: "cf", "tlb", "off", "ctl"
    area_control_mode = 'cf'

    # The present power system frequency set point for automatic generation control 
    freq_set_point = ''

    def get_receive_dynamic_schedules(self):
        """ A control area can receive dynamic schedules from other control areas
        """
        return self._receive_dynamic_schedules

    def set_receive_dynamic_schedules(self, value):
        for x in self._receive_dynamic_schedules:
            x._receive_host_control_area = None
        for y in value:
            y._receive_host_control_area = self
        self._receive_dynamic_schedules = value
            
    receive_dynamic_schedules = property(get_receive_dynamic_schedules, set_receive_dynamic_schedules)
    
    def add_receive_dynamic_schedules(self, *receive_dynamic_schedules):
        for obj in receive_dynamic_schedules:
            obj._receive_host_control_area = self
            self._receive_dynamic_schedules.append(obj)
        
    def remove_receive_dynamic_schedules(self, *receive_dynamic_schedules):
        for obj in receive_dynamic_schedules:
            obj._receive_host_control_area = None
            self._receive_dynamic_schedules.remove(obj)

    def get_side_a_tie_lines(self):
        """ A HostControlArea can have zero or more tie lines.
        """
        return self._side_a_tie_lines

    def set_side_a_tie_lines(self, value):
        for x in self._side_a_tie_lines:
            x._side_a_host_control_area = None
        for y in value:
            y._side_a_host_control_area = self
        self._side_a_tie_lines = value
            
    side_a_tie_lines = property(get_side_a_tie_lines, set_side_a_tie_lines)
    
    def add_side_a_tie_lines(self, *side_a_tie_lines):
        for obj in side_a_tie_lines:
            obj._side_a_host_control_area = self
            self._side_a_tie_lines.append(obj)
        
    def remove_side_a_tie_lines(self, *side_a_tie_lines):
        for obj in side_a_tie_lines:
            obj._side_a_host_control_area = None
            self._side_a_tie_lines.remove(obj)

    def get_side_b_tie_lines(self):
        """ A HostControlArea can have zero or more tie lines.
        """
        return self._side_b_tie_lines

    def set_side_b_tie_lines(self, value):
        for x in self._side_b_tie_lines:
            x._side_b_host_control_area = None
        for y in value:
            y._side_b_host_control_area = self
        self._side_b_tie_lines = value
            
    side_b_tie_lines = property(get_side_b_tie_lines, set_side_b_tie_lines)
    
    def add_side_b_tie_lines(self, *side_b_tie_lines):
        for obj in side_b_tie_lines:
            obj._side_b_host_control_area = self
            self._side_b_tie_lines.append(obj)
        
    def remove_side_b_tie_lines(self, *side_b_tie_lines):
        for obj in side_b_tie_lines:
            obj._side_b_host_control_area = None
            self._side_b_tie_lines.remove(obj)

    def get_send_dynamic_schedules(self):
        """ A control area can send dynamic schedules to other control areas
        """
        return self._send_dynamic_schedules

    def set_send_dynamic_schedules(self, value):
        for x in self._send_dynamic_schedules:
            x._send_host_control_area = None
        for y in value:
            y._send_host_control_area = self
        self._send_dynamic_schedules = value
            
    send_dynamic_schedules = property(get_send_dynamic_schedules, set_send_dynamic_schedules)
    
    def add_send_dynamic_schedules(self, *send_dynamic_schedules):
        for obj in send_dynamic_schedules:
            obj._send_host_control_area = self
            self._send_dynamic_schedules.append(obj)
        
    def remove_send_dynamic_schedules(self, *send_dynamic_schedules):
        for obj in send_dynamic_schedules:
            obj._send_host_control_area = None
            self._send_dynamic_schedules.remove(obj)

    def get_sub_control_areas(self):
        """ The interchange area  may operate as a control area
        """
        return self._sub_control_areas

    def set_sub_control_areas(self, value):
        for x in self._sub_control_areas:
            x._host_control_area = None
        for y in value:
            y._host_control_area = self
        self._sub_control_areas = value
            
    sub_control_areas = property(get_sub_control_areas, set_sub_control_areas)
    
    def add_sub_control_areas(self, *sub_control_areas):
        for obj in sub_control_areas:
            obj._host_control_area = self
            self._sub_control_areas.append(obj)
        
    def remove_sub_control_areas(self, *sub_control_areas):
        for obj in sub_control_areas:
            obj._host_control_area = None
            self._sub_control_areas.remove(obj)

    def get_inadvertent_accounts(self):
        """ A control area can have one or more net inadvertent interchange accounts
        """
        return self._inadvertent_accounts

    def set_inadvertent_accounts(self, value):
        for x in self._inadvertent_accounts:
            x._host_control_area = None
        for y in value:
            y._host_control_area = self
        self._inadvertent_accounts = value
            
    inadvertent_accounts = property(get_inadvertent_accounts, set_inadvertent_accounts)
    
    def add_inadvertent_accounts(self, *inadvertent_accounts):
        for obj in inadvertent_accounts:
            obj._host_control_area = self
            self._inadvertent_accounts.append(obj)
        
    def remove_inadvertent_accounts(self, *inadvertent_accounts):
        for obj in inadvertent_accounts:
            obj._host_control_area = None
            self._inadvertent_accounts.remove(obj)

    def get_controls(self):
        """ A ControlAreaCompany controls a ControlArea.
        """
        return self._controls

    def set_controls(self, value):
        if self._controls is not None:
            self._controls._controlled_by = None
            
        self._controls = value
        if self._controls is not None:
            self._controls._controlled_by = self
            
    controls = property(get_controls, set_controls)

    def get_area_reserve_spec(self):
        """ A control area has one or more area reserve specifications
        """
        return self._area_reserve_spec

    def set_area_reserve_spec(self, value):
        if self._area_reserve_spec is not None:
            filtered = [x for x in self.area_reserve_spec.host_control_areas if x != self]
            self._area_reserve_spec._host_control_areas = filtered
            
        self._area_reserve_spec = value
        if self._area_reserve_spec is not None:
            self._area_reserve_spec._host_control_areas.append(self)

    area_reserve_spec = property(get_area_reserve_spec, set_area_reserve_spec)

    # <<< host_control_area
    # @generated
    def __init__(self, area_control_mode='cf', freq_set_point='', receive_dynamic_schedules=[], side_a_tie_lines=[], side_b_tie_lines=[], send_dynamic_schedules=[], sub_control_areas=[], inadvertent_accounts=[], controls=None, area_reserve_spec=None, **kw_args):
        """ Initialises a new 'HostControlArea' instance.
        """
        self.area_control_mode = area_control_mode
        self.freq_set_point = freq_set_point
        self._receive_dynamic_schedules = []
        self.receive_dynamic_schedules = receive_dynamic_schedules
        self._side_a_tie_lines = []
        self.side_a_tie_lines = side_a_tie_lines
        self._side_b_tie_lines = []
        self.side_b_tie_lines = side_b_tie_lines
        self._send_dynamic_schedules = []
        self.send_dynamic_schedules = send_dynamic_schedules
        self._sub_control_areas = []
        self.sub_control_areas = sub_control_areas
        self._inadvertent_accounts = []
        self.inadvertent_accounts = inadvertent_accounts
        self._controls = None
        self.controls = controls
        self._area_reserve_spec = None
        self.area_reserve_spec = area_reserve_spec

        super(HostControlArea, self).__init__(**kw_args)
    # >>> host_control_area


class EnergyProduct(Agreement):
    """ An EnergyProduct is offered commercially as a ContractOrTariff.
    """
    def get_generation_provider(self):
        """ 
        """
        return self._generation_provider

    def set_generation_provider(self, value):
        if self._generation_provider is not None:
            filtered = [x for x in self.generation_provider.energy_products if x != self]
            self._generation_provider._energy_products = filtered
            
        self._generation_provider = value
        if self._generation_provider is not None:
            self._generation_provider._energy_products.append(self)

    generation_provider = property(get_generation_provider, set_generation_provider)

    def get_energy_transactions(self):
        """ The 'Source' for an EnergyTransaction is an EnergyProduct which is injected into a ControlArea.
        """
        return self._energy_transactions

    def set_energy_transactions(self, value):
        for x in self._energy_transactions:
            x._energy_product = None
        for y in value:
            y._energy_product = self
        self._energy_transactions = value
            
    energy_transactions = property(get_energy_transactions, set_energy_transactions)
    
    def add_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
            obj._energy_product = self
            self._energy_transactions.append(obj)
        
    def remove_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
            obj._energy_product = None
            self._energy_transactions.remove(obj)

    resold_by_marketers = []
    
    def add_resold_by_marketers(self, *resold_by_marketers):
        for obj in resold_by_marketers:
	        self._resold_by_marketers.append(obj)
        
    def remove_resold_by_marketers(self, *resold_by_marketers):
        for obj in resold_by_marketers:
	        self._resold_by_marketers.remove(obj)

    service_point = []
    
    def add_service_point(self, *service_point):
        for obj in service_point:
	        self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
	        self._service_point.remove(obj)

    def get_title_held_by_marketer(self):
        """ A Marketer holds title to an EnergyProduct.
        """
        return self._title_held_by_marketer

    def set_title_held_by_marketer(self, value):
        if self._title_held_by_marketer is not None:
            filtered = [x for x in self.title_held_by_marketer.holds_title_to_energy_products if x != self]
            self._title_held_by_marketer._holds_title_to_energy_products = filtered
            
        self._title_held_by_marketer = value
        if self._title_held_by_marketer is not None:
            self._title_held_by_marketer._holds_title_to_energy_products.append(self)

    title_held_by_marketer = property(get_title_held_by_marketer, set_title_held_by_marketer)

    # <<< energy_product
    # @generated
    def __init__(self, generation_provider=None, energy_transactions=[], resold_by_marketers=[], service_point=[], title_held_by_marketer=None, **kw_args):
        """ Initialises a new 'EnergyProduct' instance.
        """
        self._generation_provider = None
        self.generation_provider = generation_provider
        self._energy_transactions = []
        self.energy_transactions = energy_transactions
        self._resold_by_marketers = []
        self.resold_by_marketers = resold_by_marketers
        self._service_point = []
        self.service_point = service_point
        self._title_held_by_marketer = None
        self.title_held_by_marketer = title_held_by_marketer

        super(EnergyProduct, self).__init__(**kw_args)
    # >>> energy_product


class ProfileData(Element):
    """ Data for profile.
    """
    # Stop date/time for this profile. 
    stop_date_time = ''

    # Energy level for the profile 
    energy_level = ''

    # Active power capacity level for the profile. 
    capacity_level = ''

    # Sequence to provide item numbering for the profile. { greater than or equal to 1 } 
    sequence_number = 0

    # Start date/time for this profile. 
    start_date_time = ''

    profile = []
    
    def add_profile(self, *profile):
        for obj in profile:
	        self._profile.append(obj)
        
    def remove_profile(self, *profile):
        for obj in profile:
	        self._profile.remove(obj)

    # <<< profile_data
    # @generated
    def __init__(self, stop_date_time='', energy_level='', capacity_level='', sequence_number=0, start_date_time='', profile=[], **kw_args):
        """ Initialises a new 'ProfileData' instance.
        """
        self.stop_date_time = stop_date_time
        self.energy_level = energy_level
        self.capacity_level = capacity_level
        self.sequence_number = sequence_number
        self.start_date_time = start_date_time
        self._profile = []
        self.profile = profile

        super(ProfileData, self).__init__(**kw_args)
    # >>> profile_data


class DynamicSchedule(RegularIntervalSchedule):
    """ A continuously variable component of a control area's active power net interchange schedule. Dynamic schedules are sent and received by control areas.
    """
    # The 'active' or 'inactive' status of the dynamic schedule 
    dyn_sched_status = ''

    # Dynamic schedule sign reversal required (yes/no) 
    dyn_sched_sign_rev = False

    def get_receive_host_control_area(self):
        """ A control area can receive dynamic schedules from other control areas
        """
        return self._receive_host_control_area

    def set_receive_host_control_area(self, value):
        if self._receive_host_control_area is not None:
            filtered = [x for x in self.receive_host_control_area.receive_dynamic_schedules if x != self]
            self._receive_host_control_area._receive_dynamic_schedules = filtered
            
        self._receive_host_control_area = value
        if self._receive_host_control_area is not None:
            self._receive_host_control_area._receive_dynamic_schedules.append(self)

    receive_host_control_area = property(get_receive_host_control_area, set_receive_host_control_area)

    def get_measurement(self):
        """ A measurement is a data source for dynamic interchange schedules
        """
        return self._measurement

    def set_measurement(self, value):
        if self._measurement is not None:
            filtered = [x for x in self.measurement.dynamic_schedules if x != self]
            self._measurement._dynamic_schedules = filtered
            
        self._measurement = value
        if self._measurement is not None:
            self._measurement._dynamic_schedules.append(self)

    measurement = property(get_measurement, set_measurement)

    def get_send_host_control_area(self):
        """ A control area can send dynamic schedules to other control areas
        """
        return self._send_host_control_area

    def set_send_host_control_area(self, value):
        if self._send_host_control_area is not None:
            filtered = [x for x in self.send_host_control_area.send_dynamic_schedules if x != self]
            self._send_host_control_area._send_dynamic_schedules = filtered
            
        self._send_host_control_area = value
        if self._send_host_control_area is not None:
            self._send_host_control_area._send_dynamic_schedules.append(self)

    send_host_control_area = property(get_send_host_control_area, set_send_host_control_area)

    # <<< dynamic_schedule
    # @generated
    def __init__(self, dyn_sched_status='', dyn_sched_sign_rev=False, receive_host_control_area=None, measurement=None, send_host_control_area=None, **kw_args):
        """ Initialises a new 'DynamicSchedule' instance.
        """
        self.dyn_sched_status = dyn_sched_status
        self.dyn_sched_sign_rev = dyn_sched_sign_rev
        self._receive_host_control_area = None
        self.receive_host_control_area = receive_host_control_area
        self._measurement = None
        self.measurement = measurement
        self._send_host_control_area = None
        self.send_host_control_area = send_host_control_area

        super(DynamicSchedule, self).__init__(**kw_args)
    # >>> dynamic_schedule


class AvailableTransmissionCapacity(Curve):
    """ Amount of possible flow by direction.
    """
    schedule_for = []
    
    def add_schedule_for(self, *schedule_for):
        for obj in schedule_for:
	        self._schedule_for.append(obj)
        
    def remove_schedule_for(self, *schedule_for):
        for obj in schedule_for:
	        self._schedule_for.remove(obj)

    # <<< available_transmission_capacity
    # @generated
    def __init__(self, schedule_for=[], **kw_args):
        """ Initialises a new 'AvailableTransmissionCapacity' instance.
        """
        self._schedule_for = []
        self.schedule_for = schedule_for

        super(AvailableTransmissionCapacity, self).__init__(**kw_args)
    # >>> available_transmission_capacity


class AreaReserveSpec(Element):
    """ The control area's reserve specification
    """
    # Lower active power regulating margin requirement, the amount of generation that can be dropped by control in 10 minutes 
    lower_reg_margin_reqt = ''

    # Operating active power reserve requirement, where operating reserve is the generating capability that is fully available within 30 minutes. Operating reserve is composed of primary reserve (t less than 10 min) and secondary reserve (10 less than t less than 30 min). 
    op_reserve_reqt = ''

    # Primary active power reserve requirement, where primary reserve is generating capability that is fully available within 10 minutes. Primary reserve is composed of spinning reserve and quick-start reserve. 
    primary_reserve_reqt = ''

    # Spinning active power reserve requirement, spinning reserve is generating capability that is presently synchronized to the network and is fully available within 10 minutes 
    spinning_reserve_reqt = ''

    # The Identification or name of the control area's reserve specification. A particular specification could correspond to pre-defined power system conditions, e.g., emergency situations. 
    area_reserve_spec_name = ''

    # Raise active power regulating margin requirement, the amount of generation that can be picked up by control in 10 minutes 
    raise_reg_margin_reqt = ''

    def get_host_control_areas(self):
        """ A control area has one or more area reserve specifications
        """
        return self._host_control_areas

    def set_host_control_areas(self, value):
        for x in self._host_control_areas:
            x._area_reserve_spec = None
        for y in value:
            y._area_reserve_spec = self
        self._host_control_areas = value
            
    host_control_areas = property(get_host_control_areas, set_host_control_areas)
    
    def add_host_control_areas(self, *host_control_areas):
        for obj in host_control_areas:
            obj._area_reserve_spec = self
            self._host_control_areas.append(obj)
        
    def remove_host_control_areas(self, *host_control_areas):
        for obj in host_control_areas:
            obj._area_reserve_spec = None
            self._host_control_areas.remove(obj)

    def get_reserve_energy_transaction(self):
        """ A Reserve type of energy transaction can count towards an area reserve specification.
        """
        return self._reserve_energy_transaction

    def set_reserve_energy_transaction(self, value):
        if self._reserve_energy_transaction is not None:
            filtered = [x for x in self.reserve_energy_transaction.area_reserve_spec if x != self]
            self._reserve_energy_transaction._area_reserve_spec = filtered
            
        self._reserve_energy_transaction = value
        if self._reserve_energy_transaction is not None:
            self._reserve_energy_transaction._area_reserve_spec.append(self)

    reserve_energy_transaction = property(get_reserve_energy_transaction, set_reserve_energy_transaction)

    # <<< area_reserve_spec
    # @generated
    def __init__(self, lower_reg_margin_reqt='', op_reserve_reqt='', primary_reserve_reqt='', spinning_reserve_reqt='', area_reserve_spec_name='', raise_reg_margin_reqt='', host_control_areas=[], reserve_energy_transaction=None, **kw_args):
        """ Initialises a new 'AreaReserveSpec' instance.
        """
        self.lower_reg_margin_reqt = lower_reg_margin_reqt
        self.op_reserve_reqt = op_reserve_reqt
        self.primary_reserve_reqt = primary_reserve_reqt
        self.spinning_reserve_reqt = spinning_reserve_reqt
        self.area_reserve_spec_name = area_reserve_spec_name
        self.raise_reg_margin_reqt = raise_reg_margin_reqt
        self._host_control_areas = []
        self.host_control_areas = host_control_areas
        self._reserve_energy_transaction = None
        self.reserve_energy_transaction = reserve_energy_transaction

        super(AreaReserveSpec, self).__init__(**kw_args)
    # >>> area_reserve_spec


class InadvertentAccount(Curve):
    """ An account for tracking inadvertent interchange versus time for each control area. A control area may have more than one inadvertent account in order to track inadvertent over one or more specific tie points in addition to the usual overall net inadvertent. Separate accounts would also be used to track designated time periods, such as on-peak and off-peak.
    """
    def get_host_control_area(self):
        """ A control area can have one or more net inadvertent interchange accounts
        """
        return self._host_control_area

    def set_host_control_area(self, value):
        if self._host_control_area is not None:
            filtered = [x for x in self.host_control_area.inadvertent_accounts if x != self]
            self._host_control_area._inadvertent_accounts = filtered
            
        self._host_control_area = value
        if self._host_control_area is not None:
            self._host_control_area._inadvertent_accounts.append(self)

    host_control_area = property(get_host_control_area, set_host_control_area)

    # <<< inadvertent_account
    # @generated
    def __init__(self, host_control_area=None, **kw_args):
        """ Initialises a new 'InadvertentAccount' instance.
        """
        self._host_control_area = None
        self.host_control_area = host_control_area

        super(InadvertentAccount, self).__init__(**kw_args)
    # >>> inadvertent_account


class EnergyTransaction(Document):
    """ Specifies the schedule for energy transfers between interchange areas that are necessary to satisfy the associated interchange transaction.
    """
    # Delivery point active power 
    delivery_point_p = ''

    # Receipt point active power 
    receipt_point_p = ''

    # Maximum congestion charges in monetary units 
    congest_charge_max = ''

    # Firm interchange flag indicates whether or not this energy transaction can be changed without potential financial consequences. 
    firm_interchange_flag = False

 
    reason = ''

    # Transaction minimum active power if dispatchable 
    energy_min = ''

    def get_import_sub_control_area(self):
        """ Energy is transferred between interchange areas
        """
        return self._import_sub_control_area

    def set_import_sub_control_area(self, value):
        if self._import_sub_control_area is not None:
            filtered = [x for x in self.import_sub_control_area.import_energy_transactions if x != self]
            self._import_sub_control_area._import_energy_transactions = filtered
            
        self._import_sub_control_area = value
        if self._import_sub_control_area is not None:
            self._import_sub_control_area._import_energy_transactions.append(self)

    import_sub_control_area = property(get_import_sub_control_area, set_import_sub_control_area)

    def get_loss_profiles(self):
        """ An EnergyTransaction may have a LossProfile.
        """
        return self._loss_profiles

    def set_loss_profiles(self, value):
        for x in self._loss_profiles:
            x._energy_transaction = None
        for y in value:
            y._energy_transaction = self
        self._loss_profiles = value
            
    loss_profiles = property(get_loss_profiles, set_loss_profiles)
    
    def add_loss_profiles(self, *loss_profiles):
        for obj in loss_profiles:
            obj._energy_transaction = self
            self._loss_profiles.append(obj)
        
    def remove_loss_profiles(self, *loss_profiles):
        for obj in loss_profiles:
            obj._energy_transaction = None
            self._loss_profiles.remove(obj)

    def get_energy_profiles(self):
        """ An EnergyTransaction must have at least one EnergyProfile.
        """
        return self._energy_profiles

    def set_energy_profiles(self, value):
        for x in self._energy_profiles:
            x._energy_transaction = None
        for y in value:
            y._energy_transaction = self
        self._energy_profiles = value
            
    energy_profiles = property(get_energy_profiles, set_energy_profiles)
    
    def add_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._energy_transaction = self
            self._energy_profiles.append(obj)
        
    def remove_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._energy_transaction = None
            self._energy_profiles.remove(obj)

    def get_energy_product(self):
        """ The 'Source' for an EnergyTransaction is an EnergyProduct which is injected into a ControlArea.
        """
        return self._energy_product

    def set_energy_product(self, value):
        if self._energy_product is not None:
            filtered = [x for x in self.energy_product.energy_transactions if x != self]
            self._energy_product._energy_transactions = filtered
            
        self._energy_product = value
        if self._energy_product is not None:
            self._energy_product._energy_transactions.append(self)

    energy_product = property(get_energy_product, set_energy_product)

    def get_energy_trans_id(self):
        """ 
        """
        return self._energy_trans_id

    def set_energy_trans_id(self, value):
        for x in self._energy_trans_id:
            x._energy_trans_id = None
        for y in value:
            y._energy_trans_id = self
        self._energy_trans_id = value
            
    energy_trans_id = property(get_energy_trans_id, set_energy_trans_id)
    
    def add_energy_trans_id(self, *energy_trans_id):
        for obj in energy_trans_id:
            obj._energy_trans_id = self
            self._energy_trans_id.append(obj)
        
    def remove_energy_trans_id(self, *energy_trans_id):
        for obj in energy_trans_id:
            obj._energy_trans_id = None
            self._energy_trans_id.remove(obj)

    def get_curtailment_profiles(self):
        """ An EnergyTransaction may be curtailed by any of the participating entities.
        """
        return self._curtailment_profiles

    def set_curtailment_profiles(self, value):
        for x in self._curtailment_profiles:
            x._energy_transaction = None
        for y in value:
            y._energy_transaction = self
        self._curtailment_profiles = value
            
    curtailment_profiles = property(get_curtailment_profiles, set_curtailment_profiles)
    
    def add_curtailment_profiles(self, *curtailment_profiles):
        for obj in curtailment_profiles:
            obj._energy_transaction = self
            self._curtailment_profiles.append(obj)
        
    def remove_curtailment_profiles(self, *curtailment_profiles):
        for obj in curtailment_profiles:
            obj._energy_transaction = None
            self._curtailment_profiles.remove(obj)

    def get_export_sub_control_area(self):
        """ Energy is transferred between interchange areas
        """
        return self._export_sub_control_area

    def set_export_sub_control_area(self, value):
        if self._export_sub_control_area is not None:
            filtered = [x for x in self.export_sub_control_area.export_energy_transactions if x != self]
            self._export_sub_control_area._export_energy_transactions = filtered
            
        self._export_sub_control_area = value
        if self._export_sub_control_area is not None:
            self._export_sub_control_area._export_energy_transactions.append(self)

    export_sub_control_area = property(get_export_sub_control_area, set_export_sub_control_area)

    energy_price_curves = []
    
    def add_energy_price_curves(self, *energy_price_curves):
        for obj in energy_price_curves:
	        self._energy_price_curves.append(obj)
        
    def remove_energy_price_curves(self, *energy_price_curves):
        for obj in energy_price_curves:
	        self._energy_price_curves.remove(obj)

    # <<< energy_transaction
    # @generated
    def __init__(self, delivery_point_p='', receipt_point_p='', congest_charge_max='', firm_interchange_flag=False, reason='', energy_min='', import_sub_control_area=None, loss_profiles=[], energy_profiles=[], energy_product=None, energy_trans_id=[], curtailment_profiles=[], export_sub_control_area=None, energy_price_curves=[], **kw_args):
        """ Initialises a new 'EnergyTransaction' instance.
        """
        self.delivery_point_p = delivery_point_p
        self.receipt_point_p = receipt_point_p
        self.congest_charge_max = congest_charge_max
        self.firm_interchange_flag = firm_interchange_flag
        self.reason = reason
        self.energy_min = energy_min
        self._import_sub_control_area = None
        self.import_sub_control_area = import_sub_control_area
        self._loss_profiles = []
        self.loss_profiles = loss_profiles
        self._energy_profiles = []
        self.energy_profiles = energy_profiles
        self._energy_product = None
        self.energy_product = energy_product
        self._energy_trans_id = []
        self.energy_trans_id = energy_trans_id
        self._curtailment_profiles = []
        self.curtailment_profiles = curtailment_profiles
        self._export_sub_control_area = None
        self.export_sub_control_area = export_sub_control_area
        self._energy_price_curves = []
        self.energy_price_curves = energy_price_curves

        super(EnergyTransaction, self).__init__(**kw_args)
    # >>> energy_transaction


class Profile(IdentifiedObject):
    """ A profile is a simpler curve type.
    """
    profile_datas = []
    
    def add_profile_datas(self, *profile_datas):
        for obj in profile_datas:
	        self._profile_datas.append(obj)
        
    def remove_profile_datas(self, *profile_datas):
        for obj in profile_datas:
	        self._profile_datas.remove(obj)

    # <<< profile
    # @generated
    def __init__(self, profile_datas=[], **kw_args):
        """ Initialises a new 'Profile' instance.
        """
        self._profile_datas = []
        self.profile_datas = profile_datas

        super(Profile, self).__init__(**kw_args)
    # >>> profile


class Reserve(EnergyTransaction):
    def get_area_reserve_spec(self):
        """ A Reserve type of energy transaction can count towards an area reserve specification.
        """
        return self._area_reserve_spec

    def set_area_reserve_spec(self, value):
        for x in self._area_reserve_spec:
            x._reserve_energy_transaction = None
        for y in value:
            y._reserve_energy_transaction = self
        self._area_reserve_spec = value
            
    area_reserve_spec = property(get_area_reserve_spec, set_area_reserve_spec)
    
    def add_area_reserve_spec(self, *area_reserve_spec):
        for obj in area_reserve_spec:
            obj._reserve_energy_transaction = self
            self._area_reserve_spec.append(obj)
        
    def remove_area_reserve_spec(self, *area_reserve_spec):
        for obj in area_reserve_spec:
            obj._reserve_energy_transaction = None
            self._area_reserve_spec.remove(obj)

    # <<< reserve
    # @generated
    def __init__(self, area_reserve_spec=[], **kw_args):
        """ Initialises a new 'Reserve' instance.
        """
        self._area_reserve_spec = []
        self.area_reserve_spec = area_reserve_spec

        super(Reserve, self).__init__(**kw_args)
    # >>> reserve


class Dynamic(EnergyTransaction):
    """ A dynamic energy transaction has more complex relationships than a simple block type. It behaves like a pseudo tie line.
    """
    def get_tie_lines(self):
        """ A dynamic energy transaction can act as a pseudo tie line.
        """
        return self._tie_lines

    def set_tie_lines(self, value):
        for x in self._tie_lines:
            x._dynamic_energy_transaction = None
        for y in value:
            y._dynamic_energy_transaction = self
        self._tie_lines = value
            
    tie_lines = property(get_tie_lines, set_tie_lines)
    
    def add_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._dynamic_energy_transaction = self
            self._tie_lines.append(obj)
        
    def remove_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._dynamic_energy_transaction = None
            self._tie_lines.remove(obj)

    # <<< dynamic
    # @generated
    def __init__(self, tie_lines=[], **kw_args):
        """ Initialises a new 'Dynamic' instance.
        """
        self._tie_lines = []
        self.tie_lines = tie_lines

        super(Dynamic, self).__init__(**kw_args)
    # >>> dynamic


class EnergyProfile(Profile):
    """ Specifies the start time, stop time, level for an EnergyTransaction.
    """
    def get_transaction_bid(self):
        """ 
        """
        return self._transaction_bid

    def set_transaction_bid(self, value):
        if self._transaction_bid is not None:
            filtered = [x for x in self.transaction_bid.energy_profiles if x != self]
            self._transaction_bid._energy_profiles = filtered
            
        self._transaction_bid = value
        if self._transaction_bid is not None:
            self._transaction_bid._energy_profiles.append(self)

    transaction_bid = property(get_transaction_bid, set_transaction_bid)

    def get_energy_transaction(self):
        """ An EnergyTransaction must have at least one EnergyProfile.
        """
        return self._energy_transaction

    def set_energy_transaction(self, value):
        if self._energy_transaction is not None:
            filtered = [x for x in self.energy_transaction.energy_profiles if x != self]
            self._energy_transaction._energy_profiles = filtered
            
        self._energy_transaction = value
        if self._energy_transaction is not None:
            self._energy_transaction._energy_profiles.append(self)

    energy_transaction = property(get_energy_transaction, set_energy_transaction)

    # <<< energy_profile
    # @generated
    def __init__(self, transaction_bid=None, energy_transaction=None, **kw_args):
        """ Initialises a new 'EnergyProfile' instance.
        """
        self._transaction_bid = None
        self.transaction_bid = transaction_bid
        self._energy_transaction = None
        self.energy_transaction = energy_transaction

        super(EnergyProfile, self).__init__(**kw_args)
    # >>> energy_profile


class LossProfile(Profile):
    """ LossProfile is associated with an EnerrgyTransaction and must be completely contained within the time frame of the EnergyProfile associated with this EnergyTransaction.
    """
    def get_energy_transaction(self):
        """ An EnergyTransaction may have a LossProfile.
        """
        return self._energy_transaction

    def set_energy_transaction(self, value):
        if self._energy_transaction is not None:
            filtered = [x for x in self.energy_transaction.loss_profiles if x != self]
            self._energy_transaction._loss_profiles = filtered
            
        self._energy_transaction = value
        if self._energy_transaction is not None:
            self._energy_transaction._loss_profiles.append(self)

    energy_transaction = property(get_energy_transaction, set_energy_transaction)

    def get_has_loss_(self):
        """ Part of the LossProfile for an EnergyTransaction may be a loss for a TransmissionProvider.
        """
        return self._has_loss_

    def set_has_loss_(self, value):
        if self._has_loss_ is not None:
            filtered = [x for x in self.has_loss_.for_ if x != self]
            self._has_loss_._for_ = filtered
            
        self._has_loss_ = value
        if self._has_loss_ is not None:
            self._has_loss_._for_.append(self)

    has_loss_ = property(get_has_loss_, set_has_loss_)

    # <<< loss_profile
    # @generated
    def __init__(self, energy_transaction=None, has_loss_=None, **kw_args):
        """ Initialises a new 'LossProfile' instance.
        """
        self._energy_transaction = None
        self.energy_transaction = energy_transaction
        self._has_loss_ = None
        self.has_loss_ = has_loss_

        super(LossProfile, self).__init__(**kw_args)
    # >>> loss_profile


class Block(EnergyTransaction):
    """ A block is a simple transaction type, with no additional relationships.
    """
    pass
    # <<< block
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'Block' instance.
        """

        super(Block, self).__init__(**kw_args)
    # >>> block


class CurtailmentProfile(Profile):
    """ Curtailing entity must be providing at least one service to the EnergyTransaction. The CurtailmentProfile must be completely contained within the EnergyProfile timeframe for this EnergyTransaction.
    """
    def get_energy_transaction(self):
        """ An EnergyTransaction may be curtailed by any of the participating entities.
        """
        return self._energy_transaction

    def set_energy_transaction(self, value):
        if self._energy_transaction is not None:
            filtered = [x for x in self.energy_transaction.curtailment_profiles if x != self]
            self._energy_transaction._curtailment_profiles = filtered
            
        self._energy_transaction = value
        if self._energy_transaction is not None:
            self._energy_transaction._curtailment_profiles.append(self)

    energy_transaction = property(get_energy_transaction, set_energy_transaction)

    # <<< curtailment_profile
    # @generated
    def __init__(self, energy_transaction=None, **kw_args):
        """ Initialises a new 'CurtailmentProfile' instance.
        """
        self._energy_transaction = None
        self.energy_transaction = energy_transaction

        super(CurtailmentProfile, self).__init__(**kw_args)
    # >>> curtailment_profile


# <<< energy_scheduling
# @generated
# >>> energy_scheduling

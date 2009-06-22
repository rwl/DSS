# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root
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

class TieLine(Root):
    # A CustomerConsumer may ring its perimeter with metering, which can create a unique SubControlArea at the collection of metering points, called a TieLine.
    customer_consumer = None

    # A ControlAreaOperator has a collection of tie points that ring the ControlArea, called a TieLine.
    control_area_operators = []

    # The SubControlArea is on the A side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
    side_a_sub_control_area = None

    # A HostControlArea can have zero or more tie lines.
    side_a_host_control_area = None

    # A HostControlArea can have zero or more tie lines.
    side_b_host_control_area = None

    # The SubControlArea is on the B side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
    side_b_sub_control_area = None

    # A dynamic energy transaction can act as a pseudo tie line.
    dynamic_energy_transaction = None

    # <<< tie_line
    # @generated
    def __init__(self, customer_consumer=None, control_area_operators=[], side_a_sub_control_area=None, side_a_host_control_area=None, side_b_host_control_area=None, side_b_sub_control_area=None, dynamic_energy_transaction=None, **kw_args):
        """ Initialises a new 'TieLine' instance.
        """
        self.customer_consumer = customer_consumer
        self.control_area_operators = control_area_operators
        self.side_a_sub_control_area = side_a_sub_control_area
        self.side_a_host_control_area = side_a_host_control_area
        self.side_b_host_control_area = side_b_host_control_area
        self.side_b_sub_control_area = side_b_sub_control_area
        self.dynamic_energy_transaction = dynamic_energy_transaction

        super(TieLine, self).__init__(**kw_args)
    # >>> tie_line


class TransmissionCorridor(PowerSystemResource):
    """ A corridor containing one or more rights of way
    """
    # A transmission right-of-way is a member of a transmission corridor
    transmission_right_of_ways = []

    # A TransmissionPath is contained in a TransmissionCorridor.
    contained_in = []

    # <<< transmission_corridor
    # @generated
    def __init__(self, transmission_right_of_ways=[], contained_in=[], **kw_args):
        """ Initialises a new 'TransmissionCorridor' instance.
        """
        self.transmission_right_of_ways = transmission_right_of_ways
        self.contained_in = contained_in

        super(TransmissionCorridor, self).__init__(**kw_args)
    # >>> transmission_corridor


class SubControlArea(ControlArea):
    """ SubControlArea replacement classed moved into EnergySchedulingPackage.  An area defined for the purpose of tracking interchange with surrounding areas via tie points; may or may not serve as a control area.
    """
    # A control area may own 0 to n flowgates A flowgate must be owned by exactly 1 control area
    flowgate = []

    # A transmission path's service point is part of an interchange area
    part_of = []

    # Energy is transferred between interchange areas
    export_energy_transactions = []

    # The SubControlArea is on the A side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
    side_a_tie_lines = []

    # A GeneratingUnit injects energy into a SubControlArea.
    generating_units = []

    # The interchange area  may operate as a control area
    host_control_area = None

    # Energy is transferred between interchange areas
    import_energy_transactions = []

    # The SubControlArea is on the B side of a collection of metered points which define the SubControlArea's boundary for a ControlAreaOperator or CustomerConsumer.
    side_b_tie_lines = []

    # <<< sub_control_area
    # @generated
    def __init__(self, flowgate=[], part_of=[], export_energy_transactions=[], side_a_tie_lines=[], generating_units=[], host_control_area=None, import_energy_transactions=[], side_b_tie_lines=[], **kw_args):
        """ Initialises a new 'SubControlArea' instance.
        """
        self.flowgate = flowgate
        self.part_of = part_of
        self.export_energy_transactions = export_energy_transactions
        self.side_a_tie_lines = side_a_tie_lines
        self.generating_units = generating_units
        self.host_control_area = host_control_area
        self.import_energy_transactions = import_energy_transactions
        self.side_b_tie_lines = side_b_tie_lines

        super(SubControlArea, self).__init__(**kw_args)
    # >>> sub_control_area


class EnergySchedulingVersion(Root):
 
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
    # A transmission right-of-way is a member of a transmission corridor
    transmission_corridor = None

    # A transmission line can be part of a transmission corridor
    lines = []

    # <<< transmission_right_of_way
    # @generated
    def __init__(self, transmission_corridor=None, lines=[], **kw_args):
        """ Initialises a new 'TransmissionRightOfWay' instance.
        """
        self.transmission_corridor = transmission_corridor
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

    # A control area can receive dynamic schedules from other control areas
    receive_dynamic_schedules = []

    # A HostControlArea can have zero or more tie lines.
    side_a_tie_lines = []

    # A HostControlArea can have zero or more tie lines.
    side_b_tie_lines = []

    # A control area can send dynamic schedules to other control areas
    send_dynamic_schedules = []

    # The interchange area  may operate as a control area
    sub_control_areas = []

    # A control area can have one or more net inadvertent interchange accounts
    inadvertent_accounts = []

    # A ControlAreaCompany controls a ControlArea.
    controls = None

    # A control area has one or more area reserve specifications
    area_reserve_spec = None

    # <<< host_control_area
    # @generated
    def __init__(self, area_control_mode='cf', freq_set_point='', receive_dynamic_schedules=[], side_a_tie_lines=[], side_b_tie_lines=[], send_dynamic_schedules=[], sub_control_areas=[], inadvertent_accounts=[], controls=None, area_reserve_spec=None, **kw_args):
        """ Initialises a new 'HostControlArea' instance.
        """
        self.area_control_mode = area_control_mode
        self.freq_set_point = freq_set_point
        self.receive_dynamic_schedules = receive_dynamic_schedules
        self.side_a_tie_lines = side_a_tie_lines
        self.side_b_tie_lines = side_b_tie_lines
        self.send_dynamic_schedules = send_dynamic_schedules
        self.sub_control_areas = sub_control_areas
        self.inadvertent_accounts = inadvertent_accounts
        self.controls = controls
        self.area_reserve_spec = area_reserve_spec

        super(HostControlArea, self).__init__(**kw_args)
    # >>> host_control_area


class EnergyProduct(Agreement):
    """ An EnergyProduct is offered commercially as a ContractOrTariff.
    """
    generation_provider = None

    # The 'Source' for an EnergyTransaction is an EnergyProduct which is injected into a ControlArea.
    energy_transactions = []

    # A Marketer may resell an EnergyProduct.
    resold_by_marketers = []

    # An EnergyProduct injects energy into a service point.
    service_point = []

    # A Marketer holds title to an EnergyProduct.
    title_held_by_marketer = None

    # <<< energy_product
    # @generated
    def __init__(self, generation_provider=None, energy_transactions=[], resold_by_marketers=[], service_point=[], title_held_by_marketer=None, **kw_args):
        """ Initialises a new 'EnergyProduct' instance.
        """
        self.generation_provider = generation_provider
        self.energy_transactions = energy_transactions
        self.resold_by_marketers = resold_by_marketers
        self.service_point = service_point
        self.title_held_by_marketer = title_held_by_marketer

        super(EnergyProduct, self).__init__(**kw_args)
    # >>> energy_product


class ProfileData(Root):
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

    # A profile has profile data associated with it.
    profile = []

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

    # A control area can receive dynamic schedules from other control areas
    receive_host_control_area = None

    # A measurement is a data source for dynamic interchange schedules
    measurement = None

    # A control area can send dynamic schedules to other control areas
    send_host_control_area = None

    # <<< dynamic_schedule
    # @generated
    def __init__(self, dyn_sched_status='', dyn_sched_sign_rev=False, receive_host_control_area=None, measurement=None, send_host_control_area=None, **kw_args):
        """ Initialises a new 'DynamicSchedule' instance.
        """
        self.dyn_sched_status = dyn_sched_status
        self.dyn_sched_sign_rev = dyn_sched_sign_rev
        self.receive_host_control_area = receive_host_control_area
        self.measurement = measurement
        self.send_host_control_area = send_host_control_area

        super(DynamicSchedule, self).__init__(**kw_args)
    # >>> dynamic_schedule


class AvailableTransmissionCapacity(Curve):
    """ Amount of possible flow by direction.
    """
    # A transmission schedule posts the available transmission capacity for a transmission line.
    schedule_for = []

    # <<< available_transmission_capacity
    # @generated
    def __init__(self, schedule_for=[], **kw_args):
        """ Initialises a new 'AvailableTransmissionCapacity' instance.
        """
        self.schedule_for = schedule_for

        super(AvailableTransmissionCapacity, self).__init__(**kw_args)
    # >>> available_transmission_capacity


class AreaReserveSpec(Root):
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

    # A control area has one or more area reserve specifications
    host_control_areas = []

    # A Reserve type of energy transaction can count towards an area reserve specification.
    reserve_energy_transaction = None

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
        self.host_control_areas = host_control_areas
        self.reserve_energy_transaction = reserve_energy_transaction

        super(AreaReserveSpec, self).__init__(**kw_args)
    # >>> area_reserve_spec


class InadvertentAccount(Curve):
    """ An account for tracking inadvertent interchange versus time for each control area. A control area may have more than one inadvertent account in order to track inadvertent over one or more specific tie points in addition to the usual overall net inadvertent. Separate accounts would also be used to track designated time periods, such as on-peak and off-peak.
    """
    # A control area can have one or more net inadvertent interchange accounts
    host_control_area = None

    # <<< inadvertent_account
    # @generated
    def __init__(self, host_control_area=None, **kw_args):
        """ Initialises a new 'InadvertentAccount' instance.
        """
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

    # Energy is transferred between interchange areas
    import_sub_control_area = None

    # An EnergyTransaction may have a LossProfile.
    loss_profiles = []

    # An EnergyTransaction must have at least one EnergyProfile.
    energy_profiles = []

    # The 'Source' for an EnergyTransaction is an EnergyProduct which is injected into a ControlArea.
    energy_product = None

    energy_trans_id = []

    # An EnergyTransaction may be curtailed by any of the participating entities.
    curtailment_profiles = []

    # Energy is transferred between interchange areas
    export_sub_control_area = None

    energy_price_curves = []

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
        self.import_sub_control_area = import_sub_control_area
        self.loss_profiles = loss_profiles
        self.energy_profiles = energy_profiles
        self.energy_product = energy_product
        self.energy_trans_id = energy_trans_id
        self.curtailment_profiles = curtailment_profiles
        self.export_sub_control_area = export_sub_control_area
        self.energy_price_curves = energy_price_curves

        super(EnergyTransaction, self).__init__(**kw_args)
    # >>> energy_transaction


class Profile(IdentifiedObject):
    """ A profile is a simpler curve type.
    """
    # A profile has profile data associated with it.
    profile_datas = []

    # <<< profile
    # @generated
    def __init__(self, profile_datas=[], **kw_args):
        """ Initialises a new 'Profile' instance.
        """
        self.profile_datas = profile_datas

        super(Profile, self).__init__(**kw_args)
    # >>> profile


class Reserve(EnergyTransaction):
    # A Reserve type of energy transaction can count towards an area reserve specification.
    area_reserve_spec = []

    # <<< reserve
    # @generated
    def __init__(self, area_reserve_spec=[], **kw_args):
        """ Initialises a new 'Reserve' instance.
        """
        self.area_reserve_spec = area_reserve_spec

        super(Reserve, self).__init__(**kw_args)
    # >>> reserve


class Dynamic(EnergyTransaction):
    """ A dynamic energy transaction has more complex relationships than a simple block type. It behaves like a pseudo tie line.
    """
    # A dynamic energy transaction can act as a pseudo tie line.
    tie_lines = []

    # <<< dynamic
    # @generated
    def __init__(self, tie_lines=[], **kw_args):
        """ Initialises a new 'Dynamic' instance.
        """
        self.tie_lines = tie_lines

        super(Dynamic, self).__init__(**kw_args)
    # >>> dynamic


class EnergyProfile(Profile):
    """ Specifies the start time, stop time, level for an EnergyTransaction.
    """
    transaction_bid = None

    # An EnergyTransaction must have at least one EnergyProfile.
    energy_transaction = None

    # <<< energy_profile
    # @generated
    def __init__(self, transaction_bid=None, energy_transaction=None, **kw_args):
        """ Initialises a new 'EnergyProfile' instance.
        """
        self.transaction_bid = transaction_bid
        self.energy_transaction = energy_transaction

        super(EnergyProfile, self).__init__(**kw_args)
    # >>> energy_profile


class LossProfile(Profile):
    """ LossProfile is associated with an EnerrgyTransaction and must be completely contained within the time frame of the EnergyProfile associated with this EnergyTransaction.
    """
    # An EnergyTransaction may have a LossProfile.
    energy_transaction = None

    # Part of the LossProfile for an EnergyTransaction may be a loss for a TransmissionProvider.
    has_loss_ = None

    # <<< loss_profile
    # @generated
    def __init__(self, energy_transaction=None, has_loss_=None, **kw_args):
        """ Initialises a new 'LossProfile' instance.
        """
        self.energy_transaction = energy_transaction
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
    # An EnergyTransaction may be curtailed by any of the participating entities.
    energy_transaction = None

    # <<< curtailment_profile
    # @generated
    def __init__(self, energy_transaction=None, **kw_args):
        """ Initialises a new 'CurtailmentProfile' instance.
        """
        self.energy_transaction = energy_transaction

        super(CurtailmentProfile, self).__init__(**kw_args)
    # >>> curtailment_profile


# <<< energy_scheduling
# @generated
# >>> energy_scheduling

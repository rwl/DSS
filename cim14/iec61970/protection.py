# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import Equipment
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class ProtectionEquipment(Equipment):
    """ An electrical device designed to respond to input conditions in a prescribed manner and after specified conditions are met to cause contact operation or similar abrupt change in associated electric control circuits, or simply to display the detected condition. Protection equipment are associated with conducting equipment and usually operate circuit breakers.
    """
    # The minimum allowable value. 
    low_limit = 0.0

    # Direction same as positive active power flow value. 
    power_direction_flag = False

    # The maximum allowable value. 
    high_limit = 0.0

    # The time delay from detection of abnormal conditions to relay operation. 
    relay_delay_time = ''

    # Protection equipment may be used to protect specific Conducting Equipment. Multiple equipment may be protected or monitored by multiple protection equipment.
    conducting_equipments = []

    # The Unit for the Protection Equipment.
    unit = None

    # Protected switches operated by this ProtectionEquipment.
    protected_switches = []

    # <<< protection_equipment
    # @generated
    def __init__(self, low_limit=0.0, power_direction_flag=False, high_limit=0.0, relay_delay_time='', conducting_equipments=[], unit=None, protected_switches=[], **kw_args):
        """ Initialises a new 'ProtectionEquipment' instance.
        """
        self.low_limit = low_limit
        self.power_direction_flag = power_direction_flag
        self.high_limit = high_limit
        self.relay_delay_time = relay_delay_time
        self.conducting_equipments = conducting_equipments
        self.unit = unit
        self.protected_switches = protected_switches

        super(ProtectionEquipment, self).__init__(**kw_args)
    # >>> protection_equipment


class FaultIndicator(Equipment):
    """ A FaultIndicator is typically only an indicator (which may or may not be remotely monitored), and not a piece of equipment that actually initiates a protection event. It is used for FLISR (Fault Location, Isolation and Restoration) purposes, assisting with the dispatch of crews to 'most likely' part of the network (i.e. assists with determining circuit section where the fault most likely happened).
    """
    fault_indicator_type_asset = None

    fault_indicator_assets = []

    # <<< fault_indicator
    # @generated
    def __init__(self, fault_indicator_type_asset=None, fault_indicator_assets=[], **kw_args):
        """ Initialises a new 'FaultIndicator' instance.
        """
        self.fault_indicator_type_asset = fault_indicator_type_asset
        self.fault_indicator_assets = fault_indicator_assets

        super(FaultIndicator, self).__init__(**kw_args)
    # >>> fault_indicator


class SurgeProtector(Equipment):
    """ Shunt device, installed on the network, usually in the proximity of electrical equipment in order to protect the said equipment against transient voltage spikes caused by lightning or switching activity.
    """
    surge_protector_asset = None

    surge_protector_type_asset = None

    # <<< surge_protector
    # @generated
    def __init__(self, surge_protector_asset=None, surge_protector_type_asset=None, **kw_args):
        """ Initialises a new 'SurgeProtector' instance.
        """
        self.surge_protector_asset = surge_protector_asset
        self.surge_protector_type_asset = surge_protector_type_asset

        super(SurgeProtector, self).__init__(**kw_args)
    # >>> surge_protector


class RecloseSequence(IdentifiedObject):
    """ A reclose sequence (open and close) is defined for each possible reclosure of a breaker.
    """
    # Indicates the ordinal position of the reclose step relative to other steps in the sequence. 
    reclose_step = 0

    # Indicates the time lapse before the reclose step will execute a reclose. 
    reclose_delay = ''

    # A breaker may have zero or more automatic reclosures after a trip occurs.
    protected_switch = None

    # <<< reclose_sequence
    # @generated
    def __init__(self, reclose_step=0, reclose_delay='', protected_switch=None, **kw_args):
        """ Initialises a new 'RecloseSequence' instance.
        """
        self.reclose_step = reclose_step
        self.reclose_delay = reclose_delay
        self.protected_switch = protected_switch

        super(RecloseSequence, self).__init__(**kw_args)
    # >>> reclose_sequence


class SynchrocheckRelay(ProtectionEquipment):
    """ A device that operates when two AC circuits are within the desired limits of frequency, phase angle, and voltage, to permit or to cause the paralleling of these two circuits. Used to prevent the paralleling of non-synchronous topological islands.
    """
    # The maximum allowable frequency difference across the open device 
    max_freq_diff = ''

    # The maximum allowable difference voltage across the open device 
    max_volt_diff = ''

    # The maximum allowable voltage vector phase angle difference across the open device 
    max_angle_diff = ''

    # <<< synchrocheck_relay
    # @generated
    def __init__(self, max_freq_diff='', max_volt_diff='', max_angle_diff='', **kw_args):
        """ Initialises a new 'SynchrocheckRelay' instance.
        """
        self.max_freq_diff = max_freq_diff
        self.max_volt_diff = max_volt_diff
        self.max_angle_diff = max_angle_diff

        super(SynchrocheckRelay, self).__init__(**kw_args)
    # >>> synchrocheck_relay


class CurrentRelay(ProtectionEquipment):
    """ A device that checks current flow values in any direction or designated direction
    """
    # Inverse time delay #2 for current limit #2 
    time_delay2 = ''

    # Current limit #2 for inverse time pickup 
    current_limit2 = ''

    # Inverse time delay #1 for current limit #1 
    time_delay1 = ''

    # Inverse time delay #3 for current limit #3 
    time_delay3 = ''

    # Current limit #1 for inverse time pickup 
    current_limit1 = ''

    # Set true if the current relay has inverse time characteristic. 
    inverse_time_flag = False

    # Current limit #3 for inverse time pickup 
    current_limit3 = ''

    # <<< current_relay
    # @generated
    def __init__(self, time_delay2='', current_limit2='', time_delay1='', time_delay3='', current_limit1='', inverse_time_flag=False, current_limit3='', **kw_args):
        """ Initialises a new 'CurrentRelay' instance.
        """
        self.time_delay2 = time_delay2
        self.current_limit2 = current_limit2
        self.time_delay1 = time_delay1
        self.time_delay3 = time_delay3
        self.current_limit1 = current_limit1
        self.inverse_time_flag = inverse_time_flag
        self.current_limit3 = current_limit3

        super(CurrentRelay, self).__init__(**kw_args)
    # >>> current_relay


# <<< protection
# @generated
# >>> protection

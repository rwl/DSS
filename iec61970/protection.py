# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970.core import Equipment
from iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class ProtectionEquipment(Equipment):
    """ An electrical device designed to respond to input conditions in a prescribed manner and after specified conditions are met to cause contact operation or similar abrupt change in associated electric control circuits, or simply to display the detected condition. Protection equipment are associated with conducting equipment and usually operate circuit breakers.
    """
    # Direction same as positive active power flow value. 
    power_direction_flag = False

    # The minimum allowable value. 
    low_limit = 0.0

    # The maximum allowable value. 
    high_limit = 0.0

    # The time delay from detection of abnormal conditions to relay operation. 
    relay_delay_time = 0.0

    # Circuit breakers may be operated by protection relays.
    operates_breakers = []

    # Protection equipment may be used to protect specific Conducting Equipment. Multiple equipment may be protected or monitored by multiple protection equipment.
    conducting_equipments = []

    # The Protection Equipments having the Unit.
    unit = None

    # <<< protection_equipment
    # @generated
    def __init__(self, power_direction_flag=False, low_limit=0.0, high_limit=0.0, relay_delay_time=0.0, operates_breakers=[], conducting_equipments=[], unit=None, **kw_args):
        """ Initialises a new 'ProtectionEquipment' instance.
        """
        self.power_direction_flag = power_direction_flag
        self.low_limit = low_limit
        self.high_limit = high_limit
        self.relay_delay_time = relay_delay_time
        self.operates_breakers = operates_breakers
        self.conducting_equipments = conducting_equipments
        self.unit = unit

        super(ProtectionEquipment, self).__init__(**kw_args)
    # >>> protection_equipment


class RecloseSequence(IdentifiedObject):
    """ A reclose sequence (open and close) is defined for each possible reclosure of a breaker.
    """
    # Indicates the time lapse before the reclose step will execute a reclose. 
    reclose_delay = 0.0

    # Indicates the ordinal position of the reclose step relative to other steps in the sequence. 
    reclose_step = 0

    # A breaker may have zero or more automatic reclosures after a trip occurs.
    breaker = None

    # <<< reclose_sequence
    # @generated
    def __init__(self, reclose_delay=0.0, reclose_step=0, breaker=None, **kw_args):
        """ Initialises a new 'RecloseSequence' instance.
        """
        self.reclose_delay = reclose_delay
        self.reclose_step = reclose_step
        self.breaker = breaker

        super(RecloseSequence, self).__init__(**kw_args)
    # >>> reclose_sequence


class SynchrocheckRelay(ProtectionEquipment):
    """ A device that operates when two AC circuits are within the desired limits of frequency, phase angle, and voltage, to permit or to cause the paralleling of these two circuits. Used to prevent the paralleling of non-synchronous topological islands.
    """
    # The maximum allowable difference voltage across the open device 
    max_volt_diff = 0.0

    # The maximum allowable frequency difference across the open device 
    max_freq_diff = 0.0

    # The maximum allowable voltage vector phase angle difference across the open device 
    max_angle_diff = 0.0

    # <<< synchrocheck_relay
    # @generated
    def __init__(self, max_volt_diff=0.0, max_freq_diff=0.0, max_angle_diff=0.0, **kw_args):
        """ Initialises a new 'SynchrocheckRelay' instance.
        """
        self.max_volt_diff = max_volt_diff
        self.max_freq_diff = max_freq_diff
        self.max_angle_diff = max_angle_diff

        super(SynchrocheckRelay, self).__init__(**kw_args)
    # >>> synchrocheck_relay


class CurrentRelay(ProtectionEquipment):
    """ A device that checks current flow values in any direction or designated direction
    """
    # Current limit #3 for inverse time pickup 
    current_limit3 = 0.0

    # Current limit #1 for inverse time pickup 
    current_limit1 = 0.0

    # Inverse time delay #3 for current limit #3 
    time_delay3 = 0.0

    # Set true if the current relay has inverse time characteristic. 
    inverse_time_flag = False

    # Inverse time delay #1 for current limit #1 
    time_delay1 = 0.0

    # Current limit #2 for inverse time pickup 
    current_limit2 = 0.0

    # Inverse time delay #2 for current limit #2 
    time_delay2 = 0.0

    # <<< current_relay
    # @generated
    def __init__(self, current_limit3=0.0, current_limit1=0.0, time_delay3=0.0, inverse_time_flag=False, time_delay1=0.0, current_limit2=0.0, time_delay2=0.0, **kw_args):
        """ Initialises a new 'CurrentRelay' instance.
        """
        self.current_limit3 = current_limit3
        self.current_limit1 = current_limit1
        self.time_delay3 = time_delay3
        self.inverse_time_flag = inverse_time_flag
        self.time_delay1 = time_delay1
        self.current_limit2 = current_limit2
        self.time_delay2 = time_delay2

        super(CurrentRelay, self).__init__(**kw_args)
    # >>> current_relay


# <<< protection
# @generated
# >>> protection

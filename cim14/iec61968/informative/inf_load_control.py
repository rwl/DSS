# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.metering import DeviceFunction
from cim14.iec61968.common import ActivityRecord

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfLoadControl"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfLoadControl"

class LoadMgmtFunction(DeviceFunction):
    """ A collective function at an end device that manages the customer load.
    """
    # The basis of Load Management scheduling used here: Time Based, Tariff Based, Remote Control and Manual Control. Values are: "manual_control", "time_based", "remote_control", "tariff_based"
    scheduling_basis = 'manual_control'

    # True if the currently active schedule is being remotely over-ridden to either shed load or to limit load. 
    remote_over_ride = False

    # True if the currently active schedule is being manually over-ridden to either shed load or to limit load. 
    manual_over_ride = False

    # The present state of the load being either shed (noLoad), limited (limitedLoad) or fully connected (fullLoad). This refers only to the portion of the customer load that is under control of the LoadMgmtFunction. Values are: "limited_load", "full_load", "no_load"
    load_status = 'limited_load'

    # After a command had been received to activate the mannualOverRide state or remoteOverRideState, the normal (halted) schedule will resume after this specified time duration had elapsed. 
    over_ride_time_out = ''

    # True if LoadMgmtFunction operates under automatic control, otherwise it operates under manual control. 
    is_auto_op = False

    switches = []
    
    def add_switches(self, *switches):
        for obj in switches:
	        self._switches.append(obj)
        
    def remove_switches(self, *switches):
        for obj in switches:
	        self._switches.remove(obj)

    def get_load_mgmt_records(self):
        """ 
        """
        return self._load_mgmt_records

    def set_load_mgmt_records(self, value):
        for x in self._load_mgmt_records:
            x._load_mgmt_function = None
        for y in value:
            y._load_mgmt_function = self
        self._load_mgmt_records = value
            
    load_mgmt_records = property(get_load_mgmt_records, set_load_mgmt_records)
    
    def add_load_mgmt_records(self, *load_mgmt_records):
        for obj in load_mgmt_records:
            obj._load_mgmt_function = self
            self._load_mgmt_records.append(obj)
        
    def remove_load_mgmt_records(self, *load_mgmt_records):
        for obj in load_mgmt_records:
            obj._load_mgmt_function = None
            self._load_mgmt_records.remove(obj)

    # <<< load_mgmt_function
    # @generated
    def __init__(self, scheduling_basis='manual_control', remote_over_ride=False, manual_over_ride=False, load_status='limited_load', over_ride_time_out='', is_auto_op=False, switches=[], load_mgmt_records=[], **kw_args):
        """ Initialises a new 'LoadMgmtFunction' instance.
        """
        self.scheduling_basis = scheduling_basis
        self.remote_over_ride = remote_over_ride
        self.manual_over_ride = manual_over_ride
        self.load_status = load_status
        self.over_ride_time_out = over_ride_time_out
        self.is_auto_op = is_auto_op
        self._switches = []
        self.switches = switches
        self._load_mgmt_records = []
        self.load_mgmt_records = load_mgmt_records

        super(LoadMgmtFunction, self).__init__(**kw_args)
    # >>> load_mgmt_function


class LoadMgmtRecord(ActivityRecord):
    """ A log of actual measured load reductions as a result of load shed operations.
    """
    # The measured reduction of the total load power as a result of the load shed activation. Thus it is the difference in power before and after the load shed operation. 
    load_reduction = ''

    def get_load_mgmt_function(self):
        """ 
        """
        return self._load_mgmt_function

    def set_load_mgmt_function(self, value):
        if self._load_mgmt_function is not None:
            filtered = [x for x in self.load_mgmt_function.load_mgmt_records if x != self]
            self._load_mgmt_function._load_mgmt_records = filtered
            
        self._load_mgmt_function = value
        if self._load_mgmt_function is not None:
            self._load_mgmt_function._load_mgmt_records.append(self)

    load_mgmt_function = property(get_load_mgmt_function, set_load_mgmt_function)

    # <<< load_mgmt_record
    # @generated
    def __init__(self, load_reduction='', load_mgmt_function=None, **kw_args):
        """ Initialises a new 'LoadMgmtRecord' instance.
        """
        self.load_reduction = load_reduction
        self._load_mgmt_function = None
        self.load_mgmt_function = load_mgmt_function

        super(LoadMgmtRecord, self).__init__(**kw_args)
    # >>> load_mgmt_record


class LoadShedFunction(LoadMgmtFunction):
    """ A kind of LoadMgmtFunction that sheds a part of the customer load.
    """
    # The value of the load that is connected to the shedding switch. Typically this is a noted nominal value rather than a measured value. 
    switched_load = ''

    # <<< load_shed_function
    # @generated
    def __init__(self, switched_load='', **kw_args):
        """ Initialises a new 'LoadShedFunction' instance.
        """
        self.switched_load = switched_load

        super(LoadShedFunction, self).__init__(**kw_args)
    # >>> load_shed_function


class LoadLimitFunction(LoadMgmtFunction):
    """ A kind of LoadMgmtFunction that limits the customer load to a given value.
    """
    # The power level, to which the customer load is being limited when this function activates. When the maximum load is exceeded the switch will typically open to shed the complete customer load. 
    maximum_load = ''

    # From the point when the load recovers from an overload condition and crosses the maximumLoad threshold going down, there may be a finite time delay before the switch actually reconnects the load. Typically this is to give overload conditions sufficient time to clear, thus preventing unnecessary load switching activity. 
    reconnect_time_delay = ''

    # True if the switch will reconnect automatically, otherwise it will reconnect under manual control. 
    is_auto_recon_op = False

    # From the point when the maximumLoad threshold is crossed there may be a finite delay before the switch actually disconnects the load. Typically this is to buffer against transient load fluctuations. 
    disconnect_time_delay = ''

    # <<< load_limit_function
    # @generated
    def __init__(self, maximum_load='', reconnect_time_delay='', is_auto_recon_op=False, disconnect_time_delay='', **kw_args):
        """ Initialises a new 'LoadLimitFunction' instance.
        """
        self.maximum_load = maximum_load
        self.reconnect_time_delay = reconnect_time_delay
        self.is_auto_recon_op = is_auto_recon_op
        self.disconnect_time_delay = disconnect_time_delay

        super(LoadLimitFunction, self).__init__(**kw_args)
    # >>> load_limit_function


# <<< inf_load_control
# @generated
# >>> inf_load_control

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.metering import DeviceFunction
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.LoadControl"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.LoadControl"

class ConnectDisconnectFunction(DeviceFunction):
    """ A function that will disconnect or reconnect the customer's load under defined conditions.
    """
    # <<< connect_disconnect_function
    # @generated
    def __init__(self, event_count=0, is_connected=False, is_local_auto_recon_op=False, is_local_auto_discon_op=False, is_remote_auto_recon_op=False, is_remote_auto_discon_op=False, is_delayed_discon=False, rcd_info=None, switches=None, **kw_args):
        """ Initialises a new 'ConnectDisconnectFunction' instance.
        """
        # Running cumulative count of (connect or disconnect) events, for the lifetime of this function or until the value is cleared. 
        self.event_count = event_count
        # True if this function is in the connected state. 
        self.is_connected = is_connected
        # If set true and if reconnection can be operated locally, then the operation happens automatically. Otherwise, it is manually. 
        self.is_local_auto_recon_op = is_local_auto_recon_op
        # (if disconnection can be operated locally) If set true, the operation happens automatically, otherwise it happens manually. 
        self.is_local_auto_discon_op = is_local_auto_discon_op
        # If set true and if reconnection can be operated remotely, then the operation happens automatically. If false and if reconnection can be operated remotely, then the operation happens manually. 
        self.is_remote_auto_recon_op = is_remote_auto_recon_op
        # If set true and if disconnection can be operated remotely, then the operation happens automatically. If set false and if disconnection can be operated remotely, then the operation happens manually. 
        self.is_remote_auto_discon_op = is_remote_auto_discon_op
        # If set true, the switch may disconnect the service at the end of a specified time delay after the disconnect signal had been given. If set false, the switch may disconnect the service immediately after the disconnect signal had been given. This is typically the case for over current circuit-breakers which are classified as either instantaneous or slow acting. 
        self.is_delayed_discon = is_delayed_discon
        
        self.rcd_info = rcd_info
        self._switches = []
        if switches is None:
            self.switches = []
        else:
            self.switches = switches

        super(ConnectDisconnectFunction, self).__init__(**kw_args)
    # >>> connect_disconnect_function
        
    # <<< rcd_info
    # @generated
    # Information on remote connect disconnect switch.
    rcd_info = None
    # >>> rcd_info

    # <<< switches
    # @generated
    def get_switches(self):
        """ 
        """
        return self._switches

    def set_switches(self, value):
        for p in self.switches:
            filtered = [q for q in p.connect_disconnect_functions if q != self]
            p._connect_disconnect_functions = filtered
        for r in value:
            if self not in r._connect_disconnect_functions:
                r._connect_disconnect_functions.append(self)
        self._switches = value
            
    switches = property(get_switches, set_switches)
    
    def add_switches(self, *switches):
        for obj in switches:
            if self not in obj._connect_disconnect_functions:
                obj._connect_disconnect_functions.append(self)
            self._switches.append(obj)
        
    def remove_switches(self, *switches):
        for obj in switches:
            if self in obj._connect_disconnect_functions:
                obj._connect_disconnect_functions.remove(self)
            self._switches.remove(obj)
    # >>> switches



class RemoteConnectDisconnectInfo(Element):
    """ Details of remote connect disconnect function.
    """
    # <<< remote_connect_disconnect_info
    # @generated
    def __init__(self, armed_timeout=0.0, is_arm_disconnect=False, use_pushbutton=False, needs_power_limit_check=False, power_limit=0.0, is_energy_limiting=False, needs_voltage_limit_check=False, customer_voltage_limit=0.0, is_arm_connect=False, energy_limit=0.0, energy_usage_start_date_time='', energy_usage_warning=0.0, **kw_args):
        """ Initialises a new 'RemoteConnectDisconnectInfo' instance.
        """
        # Setting of the timeout elapsed time. 
        self.armed_timeout = armed_timeout
        # True if the RCD switch must be armed before a disconnect action can be initiated. 
        self.is_arm_disconnect = is_arm_disconnect
        # True if pushbutton must be used for connect. 
        self.use_pushbutton = use_pushbutton
        # True if load limit must be checked to issue an immediate disconnect (after a connect) if load is over the limit. 
        self.needs_power_limit_check = needs_power_limit_check
        # Load limit above which the connect should either not take place or should cause an immediate disconnect. 
        self.power_limit = power_limit
        # True if the energy usage is limited and the customer will be disconnected if they go over the limit. 
        self.is_energy_limiting = is_energy_limiting
        # True if voltage limit must be checked to prevent connect if voltage is over the limit. 
        self.needs_voltage_limit_check = needs_voltage_limit_check
        # Voltage limit on customer side of RCD switch above which the connect should not be made. 
        self.customer_voltage_limit = customer_voltage_limit
        # True if the RCD switch must be armed before a connect action can be initiated. 
        self.is_arm_connect = is_arm_connect
        # Limit of energy before disconnect. 
        self.energy_limit = energy_limit
        # Start date and time to accumulate energy for energy usage limiting. 
        self.energy_usage_start_date_time = energy_usage_start_date_time
        # Warning energy limit, used to trigger event code that energy usage is nearing limit. 
        self.energy_usage_warning = energy_usage_warning
        

        super(RemoteConnectDisconnectInfo, self).__init__(**kw_args)
    # >>> remote_connect_disconnect_info
        


# <<< load_control
# @generated
# >>> load_control

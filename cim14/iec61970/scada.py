# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.SCADA"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.SCADA"

class CommunicationLink(PowerSystemResource):
    """ The connection to remote units is through one or more communication links. Reduntant links may exist. The CommunicationLink class inherit PowerSystemResource. The intention is to allow CommunicationLinks to have Measurements. These Measurements can be used to model link status as operational, out of service, unit failure etc.
    """
    # <<< communication_link
    # @generated
    def __init__(self, remote_units=None, **kw_args):
        """ Initialises a new 'CommunicationLink' instance.
        """
        
        self._remote_units = []
        if remote_units is None:
            self.remote_units = []
        else:
            self.remote_units = remote_units

        super(CommunicationLink, self).__init__(**kw_args)
    # >>> communication_link
        
    # <<< remote_units
    # @generated
    def get_remote_units(self):
        """ RTUs may be attached to communication links.
        """
        return self._remote_units

    def set_remote_units(self, value):
        for p in self.remote_units:
            filtered = [q for q in p.communication_links if q != self]
            p._communication_links = filtered
        for r in value:
            if self not in r._communication_links:
                r._communication_links.append(self)
        self._remote_units = value
            
    remote_units = property(get_remote_units, set_remote_units)
    
    def add_remote_units(self, *remote_units):
        for obj in remote_units:
            if self not in obj._communication_links:
                obj._communication_links.append(self)
            self._remote_units.append(obj)
        
    def remove_remote_units(self, *remote_units):
        for obj in remote_units:
            if self in obj._communication_links:
                obj._communication_links.remove(self)
            self._remote_units.remove(obj)
    # >>> remote_units



class RemotePoint(IdentifiedObject):
    """ For a RTU remote points correspond to telemetered values or control outputs. Other units (e.g. control centers) usually also contain calculated values.
    """
    # <<< remote_point
    # @generated
    def __init__(self, remote_unit=None, **kw_args):
        """ Initialises a new 'RemotePoint' instance.
        """
        
        self._remote_unit = None
        self.remote_unit = remote_unit

        super(RemotePoint, self).__init__(**kw_args)
    # >>> remote_point
        
    # <<< remote_unit
    # @generated
    def get_remote_unit(self):
        """ Remote unit this point belongs to.
        """
        return self._remote_unit

    def set_remote_unit(self, value):
        if self._remote_unit is not None:
            filtered = [x for x in self.remote_unit.remote_points if x != self]
            self._remote_unit._remote_points = filtered
            
        self._remote_unit = value
        if self._remote_unit is not None:
            if self not in self._remote_unit._remote_points:
                self._remote_unit._remote_points.append(self)

    remote_unit = property(get_remote_unit, set_remote_unit)
    # >>> remote_unit



class RemoteUnit(PowerSystemResource):
    """ A remote unit can be a RTU, IED, substation control system, control center etc. The communication with the remote unit can be through various standard protocols (e.g. IEC 61870, IEC 61850) or non standard protocols (e.g. DNP, RP570 etc.). A remote unit contain remote data points that might be telemetered, collected or calculated. The RemoteUnit class inherit PowerSystemResource. The intention is to allow RemotUnits to have Measurements. These Measurements can be used to model unit status as operational, out of service, unit failure etc.
    """
    # <<< remote_unit
    # @generated
    def __init__(self, remote_unit_type='control_center', communication_links=None, remote_points=None, **kw_args):
        """ Initialises a new 'RemoteUnit' instance.
        """
        # Type of remote unit. Values are: "control_center", "ied", "rtu", "substation_control_system"
        self.remote_unit_type = remote_unit_type
        
        self._communication_links = []
        if communication_links is None:
            self.communication_links = []
        else:
            self.communication_links = communication_links
        self._remote_points = []
        if remote_points is None:
            self.remote_points = []
        else:
            self.remote_points = remote_points

        super(RemoteUnit, self).__init__(**kw_args)
    # >>> remote_unit
        
    # <<< communication_links
    # @generated
    def get_communication_links(self):
        """ RTUs may be attached to communication links.
        """
        return self._communication_links

    def set_communication_links(self, value):
        for p in self.communication_links:
            filtered = [q for q in p.remote_units if q != self]
            p._remote_units = filtered
        for r in value:
            if self not in r._remote_units:
                r._remote_units.append(self)
        self._communication_links = value
            
    communication_links = property(get_communication_links, set_communication_links)
    
    def add_communication_links(self, *communication_links):
        for obj in communication_links:
            if self not in obj._remote_units:
                obj._remote_units.append(self)
            self._communication_links.append(obj)
        
    def remove_communication_links(self, *communication_links):
        for obj in communication_links:
            if self in obj._remote_units:
                obj._remote_units.remove(self)
            self._communication_links.remove(obj)
    # >>> communication_links

    # <<< remote_points
    # @generated
    def get_remote_points(self):
        """ Remote points this Remote unit contains.
        """
        return self._remote_points

    def set_remote_points(self, value):
        for x in self._remote_points:
            x.remote_unit = None
        for y in value:
            y.remote_unit = self
        self._remote_points = value
            
    remote_points = property(get_remote_points, set_remote_points)
    
    def add_remote_points(self, *remote_points):
        for obj in remote_points:
            obj._remote_unit = self
            if obj not in self._remote_points:
                self._remote_points.append(obj)
        
    def remove_remote_points(self, *remote_points):
        for obj in remote_points:
            obj._remote_unit = None
            self._remote_points.remove(obj)
    # >>> remote_points



class RemoteSource(RemotePoint):
    """ Remote sources are state variables that are telemetered or calculated within the remote unit.
    """
    # <<< remote_source
    # @generated
    def __init__(self, deadband=0.0, scan_interval=0.0, sensor_maximum=0.0, sensor_minimum=0.0, measurement_value=None, **kw_args):
        """ Initialises a new 'RemoteSource' instance.
        """
        # The smallest change in value to be reported. 
        self.deadband = deadband
        # The time interval between scans. 
        self.scan_interval = scan_interval
        # The maximum value the telemetry item can return. 
        self.sensor_maximum = sensor_maximum
        # The minimum value the telemetry item can return. 
        self.sensor_minimum = sensor_minimum
        
        self._measurement_value = None
        self.measurement_value = measurement_value

        super(RemoteSource, self).__init__(**kw_args)
    # >>> remote_source
        
    # <<< measurement_value
    # @generated
    def get_measurement_value(self):
        """ Link to the physical telemetered point associated with this measurement.
        """
        return self._measurement_value

    def set_measurement_value(self, value):
        if self._measurement_value is not None:
            self._measurement_value._remote_source = None
            
        self._measurement_value = value
        if self._measurement_value is not None:
            self._measurement_value._remote_source = self
            
    measurement_value = property(get_measurement_value, set_measurement_value)
    # >>> measurement_value



class RemoteControl(RemotePoint):
    """ Remote controls are ouputs that are sent by the remote unit to actuators in the process.
    """
    # <<< remote_control
    # @generated
    def __init__(self, actuator_maximum=0.0, actuator_minimum=0.0, remote_controlled=False, control=None, **kw_args):
        """ Initialises a new 'RemoteControl' instance.
        """
        # The maximum set point value accepted by the remote control point. 
        self.actuator_maximum = actuator_maximum
        # The minimum set point value accepted by the remote control point. 
        self.actuator_minimum = actuator_minimum
        # Set to true if the actuator is remotely controlled. 
        self.remote_controlled = remote_controlled
        
        self._control = None
        self.control = control

        super(RemoteControl, self).__init__(**kw_args)
    # >>> remote_control
        
    # <<< control
    # @generated
    def get_control(self):
        """ The Control for the RemoteControl point.
        """
        return self._control

    def set_control(self, value):
        if self._control is not None:
            self._control._remote_control = None
            
        self._control = value
        if self._control is not None:
            self._control._remote_control = self
            
    control = property(get_control, set_control)
    # >>> control



# <<< scada
# @generated
# >>> scada

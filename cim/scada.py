# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim.core import IdentifiedObject
from cim.core import PowerSystemResource

# <<< imports
# @generated
# >>> imports

class RemotePoint(IdentifiedObject):
    """ For a RTU remote points correspond to telemetered values or control outputs. Other units (e.g. control centers) usually also contain calculated values.
    """
    member_of_remote_unit = None

    # <<< remote_point
    # @generated
    def __init__(self, member_of_remote_unit=None, **kw_args):
        """ Initialises a new 'RemotePoint' instance.
        """
        self.member_of_remote_unit = member_of_remote_unit

        super(RemotePoint, self).__init__(**kw_args)
    # >>> remote_point


class CommunicationLink(PowerSystemResource):
    """ The connection to remote units is through one or more communication links. Reduntant links may exist. The CommunicationLink class inherit PowerSystemResource. The intention is to allow CommunicationLinks to have Measurements. These Measurements can be used to model link status as operational, out of service, unit failure etc.
    """
    # RTUs may be attached to communication links.
    contain_remote_units = []

    # <<< communication_link
    # @generated
    def __init__(self, contain_remote_units=[], **kw_args):
        """ Initialises a new 'CommunicationLink' instance.
        """
        self.contain_remote_units = contain_remote_units

        super(CommunicationLink, self).__init__(**kw_args)
    # >>> communication_link


class RemoteUnit(PowerSystemResource):
    """ A remote unit can be a RTU, IED, substation control system, control center etc. The communication with the remote unit can be through various standard protocols (e.g. IEC 61870, IEC 61850) or non standard protocols (e.g. DNP, RP570 etc.). A remote unit contain remote data points that might be telemetered, collected or calculated. The RemoteUnit class inherit PowerSystemResource. The intention is to allow RemotUnits to have Measurements. These Measurements can be used to model unit status as operational, out of service, unit failure etc.
    """
    # Type of remote unit. Values are: "rtu", "ied", "control_center", "substation_control_system"
    remote_unit_type = 'rtu'

    # RTUs may be attached to communication links.
    member_of_communication_links = []

    contains_remote_points = []

    # <<< remote_unit
    # @generated
    def __init__(self, remote_unit_type='rtu', member_of_communication_links=[], contains_remote_points=[], **kw_args):
        """ Initialises a new 'RemoteUnit' instance.
        """
        self.remote_unit_type = remote_unit_type
        self.member_of_communication_links = member_of_communication_links
        self.contains_remote_points = contains_remote_points

        super(RemoteUnit, self).__init__(**kw_args)
    # >>> remote_unit


class RemoteControl(RemotePoint):
    """ Remote controls are ouputs that are sent by the remote unit to actuators in the process.
    """
    # Set to true if the actuator is remotely controlled. 
    remote_controlled = False

    # The minimum set point value accepted by the remote control point. 
    actuator_minimum = 0.0

    # The maximum set point value accepted by the remote control point. 
    actuator_maximum = 0.0

    control = None

    # <<< remote_control
    # @generated
    def __init__(self, remote_controlled=False, actuator_minimum=0.0, actuator_maximum=0.0, control=None, **kw_args):
        """ Initialises a new 'RemoteControl' instance.
        """
        self.remote_controlled = remote_controlled
        self.actuator_minimum = actuator_minimum
        self.actuator_maximum = actuator_maximum
        self.control = control

        super(RemoteControl, self).__init__(**kw_args)
    # >>> remote_control


class RemoteSource(RemotePoint):
    """ Remote sources are state variables that are telemetered or calculated within the remote unit.
    """
    # The minimum value the telemetry item can return. 
    sensor_minimum = 0.0

    # The maximum value the telemetry item can return. 
    sensor_maximum = 0.0

    # The time interval between scans. 
    scan_interval = 0.0

    # The smallest change in value to be reported. 
    deadband = 0.0

    # Links to the physical telemetered point associated with this measurement.
    measurement_value = None

    # <<< remote_source
    # @generated
    def __init__(self, sensor_minimum=0.0, sensor_maximum=0.0, scan_interval=0.0, deadband=0.0, measurement_value=None, **kw_args):
        """ Initialises a new 'RemoteSource' instance.
        """
        self.sensor_minimum = sensor_minimum
        self.sensor_maximum = sensor_maximum
        self.scan_interval = scan_interval
        self.deadband = deadband
        self.measurement_value = measurement_value

        super(RemoteSource, self).__init__(**kw_args)
    # >>> remote_source


# <<< scada
# @generated
# >>> scada

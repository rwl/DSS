# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import PowerSystemResource
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.ControlArea"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.ControlArea"

class ControlArea(PowerSystemResource):
    """ A <b>control area </b>is a grouping of <b>generating units</b> and/or loads and a cutset of tie lines (as <b>terminals</b>) which may be used for a variety of purposes including automatic generation control, powerflow solution area interchange control specification, and input to load forecasting.   Note that any number of overlapping control area specifications can be superimposed on the physical model.
    """
    # The specified positive net interchange into the control area. 
    net_interchange = ''

    # The type of control area defintion used to determine if this is used for automatic generation control, for planning interchange control, or other purposes. Values are: "forecast", "interchange", "agc"
    type = 'forecast'

    # Active power net interchange tolerance 
    p_tolerance = ''

    # The generating unit specificaitons for the control area.
    control_area_generating_unit = []

    # The tie flows associated with the control area.
    tie_flow = []

    # BusNameMarker objects that belong to the control area.
    bus_name_marker = []

    # The energy area that is forecast from this control area specification.
    energy_area = None

    # The topological nodes included in the control area.
    topological_node = []

    # <<< control_area
    # @generated
    def __init__(self, net_interchange='', type='forecast', p_tolerance='', control_area_generating_unit=[], tie_flow=[], bus_name_marker=[], energy_area=None, topological_node=[], **kw_args):
        """ Initialises a new 'ControlArea' instance.
        """
        self.net_interchange = net_interchange
        self.type = type
        self.p_tolerance = p_tolerance
        self.control_area_generating_unit = control_area_generating_unit
        self.tie_flow = tie_flow
        self.bus_name_marker = bus_name_marker
        self.energy_area = energy_area
        self.topological_node = topological_node

        super(ControlArea, self).__init__(**kw_args)
    # >>> control_area


class AltTieMeas(Element):
    """ A prioritized measurement to be used for the tie flow as part of the control area specification.
    """
    # Priority of a measurement usage.   Lower numbers have first priority. 
    priority = 0

    # The tie flow of the alternate measurements.
    tie_flow = None

    # The specific analog value used as a source.
    analog_value = None

    # <<< alt_tie_meas
    # @generated
    def __init__(self, priority=0, tie_flow=None, analog_value=None, **kw_args):
        """ Initialises a new 'AltTieMeas' instance.
        """
        self.priority = priority
        self.tie_flow = tie_flow
        self.analog_value = analog_value

        super(AltTieMeas, self).__init__(**kw_args)
    # >>> alt_tie_meas


class TieFlow(Element):
    """ A flow specification in terms of location and direction for a control area.
    """
    # The flow is positive into the terminal.  A flow is positive if it is an import into the control area. 
    positive_flow_in = False

    # The primary and alternate tie flow measurements associated with the tie flow.
    alt_tie_meas = []

    # The control area of the tie flows.
    control_area = None

    # The terminal to which this tie flow belongs.
    terminal = None

    # <<< tie_flow
    # @generated
    def __init__(self, positive_flow_in=False, alt_tie_meas=[], control_area=None, terminal=None, **kw_args):
        """ Initialises a new 'TieFlow' instance.
        """
        self.positive_flow_in = positive_flow_in
        self.alt_tie_meas = alt_tie_meas
        self.control_area = control_area
        self.terminal = terminal

        super(TieFlow, self).__init__(**kw_args)
    # >>> tie_flow


class AltGeneratingUnitMeas(Element):
    """ A prioritized measurement to be used for the generating unit in the control area specificaiton.
    """
    # Priority of a measurement usage.   Lower numbers have first priority. 
    priority = 0

    # The specific analog value used as a source.
    analog_value = None

    # The control aread generating unit to which the prioritized measurement assignment is applied.
    control_area_generating_unit = None

    # <<< alt_generating_unit_meas
    # @generated
    def __init__(self, priority=0, analog_value=None, control_area_generating_unit=None, **kw_args):
        """ Initialises a new 'AltGeneratingUnitMeas' instance.
        """
        self.priority = priority
        self.analog_value = analog_value
        self.control_area_generating_unit = control_area_generating_unit

        super(AltGeneratingUnitMeas, self).__init__(**kw_args)
    # >>> alt_generating_unit_meas


class ControlAreaGeneratingUnit(Element):
    """ A control area generating unit. This class is needed so that alternate control area definitions may include the same generating unit.   Note only one instance within a control area should reference a specific generating unit.
    """
    # The link to prioritized measurements for this GeneratingUnit.
    alt_generating_unit_meas = []

    # The parent control area for the generating unit specifications.
    control_area = None

    # The generating unit specified for this control area.  Note that a control area should include a GeneratingUnit only once.
    generating_unit = None

    # <<< control_area_generating_unit
    # @generated
    def __init__(self, alt_generating_unit_meas=[], control_area=None, generating_unit=None, **kw_args):
        """ Initialises a new 'ControlAreaGeneratingUnit' instance.
        """
        self.alt_generating_unit_meas = alt_generating_unit_meas
        self.control_area = control_area
        self.generating_unit = generating_unit

        super(ControlAreaGeneratingUnit, self).__init__(**kw_args)
    # >>> control_area_generating_unit


# <<< control_area
# @generated
# >>> control_area

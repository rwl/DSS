# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970 import Element
from iec61970.core import PowerSystemResource

# <<< imports
# @generated
# >>> imports

class ControlAreaGeneratingUnit(Element):
    """ A control area generating unit. This class is needed so that alternate control area definitions may include the same generating unit.   Note only one instance within a control area should reference a specific generating unit.
    """
    generating_unit = None

    alt_generating_unit_meas = []

    control_area = None

    # <<< control_area_generating_unit
    # @generated
    def __init__(self, generating_unit=None, alt_generating_unit_meas=[], control_area=None, **kw_args):
        """ Initialises a new 'ControlAreaGeneratingUnit' instance.
        """
        self.generating_unit = generating_unit
        self.alt_generating_unit_meas = alt_generating_unit_meas
        self.control_area = control_area

        super(ControlAreaGeneratingUnit, self).__init__(**kw_args)
    # >>> control_area_generating_unit


class ControlArea(PowerSystemResource):
    """ A <b>control area </b>is a grouping of <b>generating units</b> and/or loads and a cutset of tie lines (as <b>terminals</b>) which may be used for a variety of purposes including automatic generation control, powerflow solution area interchange control specification, and input to load forecasting.   Note that any number of overlapping control area specifications can be superimposed on the physical model.
    """
    # The type of control area defintion used to determine if this is used for automatic generation control, for planning interchange control, or other purposes. Values are: "agc", "interchange", "forecast"
    type = 'agc'

    # Active power net interchange tolerance 
    p_tolerance = 0.0

    # The specified positive net interchange into the control area. 
    net_interchange = 0.0

    bus_name_marker = []

    energy_area = None

    topological_node = []

    control_area_generating_unit = []

    tie_flow = []

    # <<< control_area
    # @generated
    def __init__(self, type='agc', p_tolerance=0.0, net_interchange=0.0, bus_name_marker=[], energy_area=None, topological_node=[], control_area_generating_unit=[], tie_flow=[], **kw_args):
        """ Initialises a new 'ControlArea' instance.
        """
        self.type = type
        self.p_tolerance = p_tolerance
        self.net_interchange = net_interchange
        self.bus_name_marker = bus_name_marker
        self.energy_area = energy_area
        self.topological_node = topological_node
        self.control_area_generating_unit = control_area_generating_unit
        self.tie_flow = tie_flow

        super(ControlArea, self).__init__(**kw_args)
    # >>> control_area


class AltGeneratingUnitMeas(Element):
    """ A prioritized measurement to be used for the generating unit in the control area specificaiton.
    """
    # Priority of a measurement usage.   Lower numbers have first priority. 
    priority = 0

    control_area_generating_unit = None

    analog_value = None

    # <<< alt_generating_unit_meas
    # @generated
    def __init__(self, priority=0, control_area_generating_unit=None, analog_value=None, **kw_args):
        """ Initialises a new 'AltGeneratingUnitMeas' instance.
        """
        self.priority = priority
        self.control_area_generating_unit = control_area_generating_unit
        self.analog_value = analog_value

        super(AltGeneratingUnitMeas, self).__init__(**kw_args)
    # >>> alt_generating_unit_meas


class TieFlow(Element):
    """ A flow specification in terms of location and direction for a control area.
    """
    # The flow is positive into the terminal.  A flow is positive if it is an import into the control area. 
    positive_flow_in = False

    # A terminal may participate in zero, one, or two control areas as a tie flow.
    terminal = None

    alt_tie_meas = []

    control_area = None

    # <<< tie_flow
    # @generated
    def __init__(self, positive_flow_in=False, terminal=None, alt_tie_meas=[], control_area=None, **kw_args):
        """ Initialises a new 'TieFlow' instance.
        """
        self.positive_flow_in = positive_flow_in
        self.terminal = terminal
        self.alt_tie_meas = alt_tie_meas
        self.control_area = control_area

        super(TieFlow, self).__init__(**kw_args)
    # >>> tie_flow


class AltTieMeas(Element):
    """ A prioritized measurement to be used for the tie flow as part of the control area specification.
    """
    # Priority of a measurement usage.   Lower numbers have first priority. 
    priority = 0

    tie_flow = None

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


# <<< control_area
# @generated
# >>> control_area

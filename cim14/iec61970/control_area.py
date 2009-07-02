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
    # <<< control_area
    # @generated
    def __init__(self, net_interchange='', type='forecast', p_tolerance='', control_area_generating_unit=[], tie_flow=[], bus_name_marker=[], energy_area=None, topological_node=[], **kw_args):
        """ Initialises a new 'ControlArea' instance.
        """
        # The specified positive net interchange into the control area. 
        self.net_interchange = ''
        # The type of control area defintion used to determine if this is used for automatic generation control, for planning interchange control, or other purposes. Values are: "forecast", "interchange", "agc"
        self.type = 'forecast'
        # Active power net interchange tolerance 
        self.p_tolerance = ''
        
        self._control_area_generating_unit = []
        self.control_area_generating_unit = control_area_generating_unit
        self._tie_flow = []
        self.tie_flow = tie_flow
        self._bus_name_marker = []
        self.bus_name_marker = bus_name_marker
        self._energy_area = None
        self.energy_area = energy_area
        self._topological_node = []
        self.topological_node = topological_node

        super(ControlArea, self).__init__(**kw_args)
    # >>> control_area
        
    # <<< control_area_generating_unit
    # @generated
    def get_control_area_generating_unit(self):
        """ The generating unit specificaitons for the control area.
        """
        return self._control_area_generating_unit

    def set_control_area_generating_unit(self, value):
        for x in self._control_area_generating_unit:
            x._control_area = None
        for y in value:
            y._control_area = self
        self._control_area_generating_unit = value
            
    control_area_generating_unit = property(get_control_area_generating_unit, set_control_area_generating_unit)
    
    def add_control_area_generating_unit(self, *control_area_generating_unit):
        for obj in control_area_generating_unit:
            obj._control_area = self
            self._control_area_generating_unit.append(obj)
        
    def remove_control_area_generating_unit(self, *control_area_generating_unit):
        for obj in control_area_generating_unit:
            obj._control_area = None
            self._control_area_generating_unit.remove(obj)
    # >>> control_area_generating_unit

    # <<< tie_flow
    # @generated
    def get_tie_flow(self):
        """ The tie flows associated with the control area.
        """
        return self._tie_flow

    def set_tie_flow(self, value):
        for x in self._tie_flow:
            x._control_area = None
        for y in value:
            y._control_area = self
        self._tie_flow = value
            
    tie_flow = property(get_tie_flow, set_tie_flow)
    
    def add_tie_flow(self, *tie_flow):
        for obj in tie_flow:
            obj._control_area = self
            self._tie_flow.append(obj)
        
    def remove_tie_flow(self, *tie_flow):
        for obj in tie_flow:
            obj._control_area = None
            self._tie_flow.remove(obj)
    # >>> tie_flow

    # <<< bus_name_marker
    # @generated
    def get_bus_name_marker(self):
        """ BusNameMarker objects that belong to the control area.
        """
        return self._bus_name_marker

    def set_bus_name_marker(self, value):
        for x in self._bus_name_marker:
            x._control_area = None
        for y in value:
            y._control_area = self
        self._bus_name_marker = value
            
    bus_name_marker = property(get_bus_name_marker, set_bus_name_marker)
    
    def add_bus_name_marker(self, *bus_name_marker):
        for obj in bus_name_marker:
            obj._control_area = self
            self._bus_name_marker.append(obj)
        
    def remove_bus_name_marker(self, *bus_name_marker):
        for obj in bus_name_marker:
            obj._control_area = None
            self._bus_name_marker.remove(obj)
    # >>> bus_name_marker

    # <<< energy_area
    # @generated
    def get_energy_area(self):
        """ The energy area that is forecast from this control area specification.
        """
        return self._energy_area

    def set_energy_area(self, value):
        if self._energy_area is not None:
            self._energy_area._control_area = None
            
        self._energy_area = value
        if self._energy_area is not None:
            self._energy_area._control_area = self
            
    energy_area = property(get_energy_area, set_energy_area)
    # >>> energy_area

    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological nodes included in the control area.
        """
        return self._topological_node

    def set_topological_node(self, value):
        for x in self._topological_node:
            x._control_area = None
        for y in value:
            y._control_area = self
        self._topological_node = value
            
    topological_node = property(get_topological_node, set_topological_node)
    
    def add_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._control_area = self
            self._topological_node.append(obj)
        
    def remove_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._control_area = None
            self._topological_node.remove(obj)
    # >>> topological_node



class AltTieMeas(Element):
    """ A prioritized measurement to be used for the tie flow as part of the control area specification.
    """
    # <<< alt_tie_meas
    # @generated
    def __init__(self, priority=0, tie_flow=None, analog_value=None, **kw_args):
        """ Initialises a new 'AltTieMeas' instance.
        """
        # Priority of a measurement usage.   Lower numbers have first priority. 
        self.priority = 0
        
        self._tie_flow = None
        self.tie_flow = tie_flow
        self._analog_value = None
        self.analog_value = analog_value

        super(AltTieMeas, self).__init__(**kw_args)
    # >>> alt_tie_meas
        
    # <<< tie_flow
    # @generated
    def get_tie_flow(self):
        """ The tie flow of the alternate measurements.
        """
        return self._tie_flow

    def set_tie_flow(self, value):
        if self._tie_flow is not None:
            filtered = [x for x in self.tie_flow.alt_tie_meas if x != self]
            self._tie_flow._alt_tie_meas = filtered
            
        self._tie_flow = value
        if self._tie_flow is not None:
            self._tie_flow._alt_tie_meas.append(self)

    tie_flow = property(get_tie_flow, set_tie_flow)
    # >>> tie_flow

    # <<< analog_value
    # @generated
    def get_analog_value(self):
        """ The specific analog value used as a source.
        """
        return self._analog_value

    def set_analog_value(self, value):
        if self._analog_value is not None:
            filtered = [x for x in self.analog_value.alt_tie_meas if x != self]
            self._analog_value._alt_tie_meas = filtered
            
        self._analog_value = value
        if self._analog_value is not None:
            self._analog_value._alt_tie_meas.append(self)

    analog_value = property(get_analog_value, set_analog_value)
    # >>> analog_value



class TieFlow(Element):
    """ A flow specification in terms of location and direction for a control area.
    """
    # <<< tie_flow
    # @generated
    def __init__(self, positive_flow_in=False, alt_tie_meas=[], control_area=None, terminal=None, **kw_args):
        """ Initialises a new 'TieFlow' instance.
        """
        # The flow is positive into the terminal.  A flow is positive if it is an import into the control area. 
        self.positive_flow_in = False
        
        self._alt_tie_meas = []
        self.alt_tie_meas = alt_tie_meas
        self._control_area = None
        self.control_area = control_area
        self._terminal = None
        self.terminal = terminal

        super(TieFlow, self).__init__(**kw_args)
    # >>> tie_flow
        
    # <<< alt_tie_meas
    # @generated
    def get_alt_tie_meas(self):
        """ The primary and alternate tie flow measurements associated with the tie flow.
        """
        return self._alt_tie_meas

    def set_alt_tie_meas(self, value):
        for x in self._alt_tie_meas:
            x._tie_flow = None
        for y in value:
            y._tie_flow = self
        self._alt_tie_meas = value
            
    alt_tie_meas = property(get_alt_tie_meas, set_alt_tie_meas)
    
    def add_alt_tie_meas(self, *alt_tie_meas):
        for obj in alt_tie_meas:
            obj._tie_flow = self
            self._alt_tie_meas.append(obj)
        
    def remove_alt_tie_meas(self, *alt_tie_meas):
        for obj in alt_tie_meas:
            obj._tie_flow = None
            self._alt_tie_meas.remove(obj)
    # >>> alt_tie_meas

    # <<< control_area
    # @generated
    def get_control_area(self):
        """ The control area of the tie flows.
        """
        return self._control_area

    def set_control_area(self, value):
        if self._control_area is not None:
            filtered = [x for x in self.control_area.tie_flow if x != self]
            self._control_area._tie_flow = filtered
            
        self._control_area = value
        if self._control_area is not None:
            self._control_area._tie_flow.append(self)

    control_area = property(get_control_area, set_control_area)
    # >>> control_area

    # <<< terminal
    # @generated
    def get_terminal(self):
        """ The terminal to which this tie flow belongs.
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            filtered = [x for x in self.terminal.tie_flow if x != self]
            self._terminal._tie_flow = filtered
            
        self._terminal = value
        if self._terminal is not None:
            self._terminal._tie_flow.append(self)

    terminal = property(get_terminal, set_terminal)
    # >>> terminal



class AltGeneratingUnitMeas(Element):
    """ A prioritized measurement to be used for the generating unit in the control area specificaiton.
    """
    # <<< alt_generating_unit_meas
    # @generated
    def __init__(self, priority=0, analog_value=None, control_area_generating_unit=None, **kw_args):
        """ Initialises a new 'AltGeneratingUnitMeas' instance.
        """
        # Priority of a measurement usage.   Lower numbers have first priority. 
        self.priority = 0
        
        self._analog_value = None
        self.analog_value = analog_value
        self._control_area_generating_unit = None
        self.control_area_generating_unit = control_area_generating_unit

        super(AltGeneratingUnitMeas, self).__init__(**kw_args)
    # >>> alt_generating_unit_meas
        
    # <<< analog_value
    # @generated
    def get_analog_value(self):
        """ The specific analog value used as a source.
        """
        return self._analog_value

    def set_analog_value(self, value):
        if self._analog_value is not None:
            filtered = [x for x in self.analog_value.alt_generating_unit if x != self]
            self._analog_value._alt_generating_unit = filtered
            
        self._analog_value = value
        if self._analog_value is not None:
            self._analog_value._alt_generating_unit.append(self)

    analog_value = property(get_analog_value, set_analog_value)
    # >>> analog_value

    # <<< control_area_generating_unit
    # @generated
    def get_control_area_generating_unit(self):
        """ The control aread generating unit to which the prioritized measurement assignment is applied.
        """
        return self._control_area_generating_unit

    def set_control_area_generating_unit(self, value):
        if self._control_area_generating_unit is not None:
            filtered = [x for x in self.control_area_generating_unit.alt_generating_unit_meas if x != self]
            self._control_area_generating_unit._alt_generating_unit_meas = filtered
            
        self._control_area_generating_unit = value
        if self._control_area_generating_unit is not None:
            self._control_area_generating_unit._alt_generating_unit_meas.append(self)

    control_area_generating_unit = property(get_control_area_generating_unit, set_control_area_generating_unit)
    # >>> control_area_generating_unit



class ControlAreaGeneratingUnit(Element):
    """ A control area generating unit. This class is needed so that alternate control area definitions may include the same generating unit.   Note only one instance within a control area should reference a specific generating unit.
    """
    # <<< control_area_generating_unit
    # @generated
    def __init__(self, alt_generating_unit_meas=[], control_area=None, generating_unit=None, **kw_args):
        """ Initialises a new 'ControlAreaGeneratingUnit' instance.
        """
        
        self._alt_generating_unit_meas = []
        self.alt_generating_unit_meas = alt_generating_unit_meas
        self._control_area = None
        self.control_area = control_area
        self._generating_unit = None
        self.generating_unit = generating_unit

        super(ControlAreaGeneratingUnit, self).__init__(**kw_args)
    # >>> control_area_generating_unit
        
    # <<< alt_generating_unit_meas
    # @generated
    def get_alt_generating_unit_meas(self):
        """ The link to prioritized measurements for this GeneratingUnit.
        """
        return self._alt_generating_unit_meas

    def set_alt_generating_unit_meas(self, value):
        for x in self._alt_generating_unit_meas:
            x._control_area_generating_unit = None
        for y in value:
            y._control_area_generating_unit = self
        self._alt_generating_unit_meas = value
            
    alt_generating_unit_meas = property(get_alt_generating_unit_meas, set_alt_generating_unit_meas)
    
    def add_alt_generating_unit_meas(self, *alt_generating_unit_meas):
        for obj in alt_generating_unit_meas:
            obj._control_area_generating_unit = self
            self._alt_generating_unit_meas.append(obj)
        
    def remove_alt_generating_unit_meas(self, *alt_generating_unit_meas):
        for obj in alt_generating_unit_meas:
            obj._control_area_generating_unit = None
            self._alt_generating_unit_meas.remove(obj)
    # >>> alt_generating_unit_meas

    # <<< control_area
    # @generated
    def get_control_area(self):
        """ The parent control area for the generating unit specifications.
        """
        return self._control_area

    def set_control_area(self, value):
        if self._control_area is not None:
            filtered = [x for x in self.control_area.control_area_generating_unit if x != self]
            self._control_area._control_area_generating_unit = filtered
            
        self._control_area = value
        if self._control_area is not None:
            self._control_area._control_area_generating_unit.append(self)

    control_area = property(get_control_area, set_control_area)
    # >>> control_area

    # <<< generating_unit
    # @generated
    def get_generating_unit(self):
        """ The generating unit specified for this control area.  Note that a control area should include a GeneratingUnit only once.
        """
        return self._generating_unit

    def set_generating_unit(self, value):
        if self._generating_unit is not None:
            filtered = [x for x in self.generating_unit.control_area_generating_unit if x != self]
            self._generating_unit._control_area_generating_unit = filtered
            
        self._generating_unit = value
        if self._generating_unit is not None:
            self._generating_unit._control_area_generating_unit.append(self)

    generating_unit = property(get_generating_unit, set_generating_unit)
    # >>> generating_unit



# <<< control_area
# @generated
# >>> control_area

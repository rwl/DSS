# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Contingency"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Contingency"

class ContingencyElement(IdentifiedObject):
    """ An element of a system event to be studied by contingency analysis, representing a change in status of a single piece of equipment.
    """
    def get_contingency(self):
        """ A contingency element belongs to one contingency.
        """
        return self._contingency

    def set_contingency(self, value):
        if self._contingency is not None:
            filtered = [x for x in self.contingency.contingency_element if x != self]
            self._contingency._contingency_element = filtered
            
        self._contingency = value
        if self._contingency is not None:
            self._contingency._contingency_element.append(self)

    contingency = property(get_contingency, set_contingency)

    # <<< contingency_element
    # @generated
    def __init__(self, contingency=None, **kw_args):
        """ Initialises a new 'ContingencyElement' instance.
        """
        self._contingency = None
        self.contingency = contingency

        super(ContingencyElement, self).__init__(**kw_args)
    # >>> contingency_element


class Contingency(IdentifiedObject):
    """ An event threatening system reliability, consisting of one or more contingency elements.
    """
    # Set true if must study this contingency. 
    must_study = False

    def get_contingency_element(self):
        """ A contingency can have any number of contingency elements.
        """
        return self._contingency_element

    def set_contingency_element(self, value):
        for x in self._contingency_element:
            x._contingency = None
        for y in value:
            y._contingency = self
        self._contingency_element = value
            
    contingency_element = property(get_contingency_element, set_contingency_element)
    
    def add_contingency_element(self, *contingency_element):
        for obj in contingency_element:
            obj._contingency = self
            self._contingency_element.append(obj)
        
    def remove_contingency_element(self, *contingency_element):
        for obj in contingency_element:
            obj._contingency = None
            self._contingency_element.remove(obj)

    def get_contingency_constraint_limit(self):
        """ 
        """
        return self._contingency_constraint_limit

    def set_contingency_constraint_limit(self, value):
        for x in self._contingency_constraint_limit:
            x._contingency = None
        for y in value:
            y._contingency = self
        self._contingency_constraint_limit = value
            
    contingency_constraint_limit = property(get_contingency_constraint_limit, set_contingency_constraint_limit)
    
    def add_contingency_constraint_limit(self, *contingency_constraint_limit):
        for obj in contingency_constraint_limit:
            obj._contingency = self
            self._contingency_constraint_limit.append(obj)
        
    def remove_contingency_constraint_limit(self, *contingency_constraint_limit):
        for obj in contingency_constraint_limit:
            obj._contingency = None
            self._contingency_constraint_limit.remove(obj)

    # <<< contingency
    # @generated
    def __init__(self, must_study=False, contingency_element=[], contingency_constraint_limit=[], **kw_args):
        """ Initialises a new 'Contingency' instance.
        """
        self.must_study = must_study
        self._contingency_element = []
        self.contingency_element = contingency_element
        self._contingency_constraint_limit = []
        self.contingency_constraint_limit = contingency_constraint_limit

        super(Contingency, self).__init__(**kw_args)
    # >>> contingency


class ContingencyEquipment(ContingencyElement):
    """ A equipment to which the in service status is to change such as a power transformer or AC line segment.
    """
    # The status for the associated equipment when in the contingency state.   This status is independent of the case to which the contingency is originally applied, but defines the equipment status when the contingency is applied. Values are: "out_of_service", "in_service"
    contingent_status = 'out_of_service'

    def get_equipment(self):
        """ The single piece of equipment to which to apply the contingency.
        """
        return self._equipment

    def set_equipment(self, value):
        if self._equipment is not None:
            filtered = [x for x in self.equipment.contingency_equipment if x != self]
            self._equipment._contingency_equipment = filtered
            
        self._equipment = value
        if self._equipment is not None:
            self._equipment._contingency_equipment.append(self)

    equipment = property(get_equipment, set_equipment)

    # <<< contingency_equipment
    # @generated
    def __init__(self, contingent_status='out_of_service', equipment=None, **kw_args):
        """ Initialises a new 'ContingencyEquipment' instance.
        """
        self.contingent_status = contingent_status
        self._equipment = None
        self.equipment = equipment

        super(ContingencyEquipment, self).__init__(**kw_args)
    # >>> contingency_equipment


# <<< contingency
# @generated
# >>> contingency

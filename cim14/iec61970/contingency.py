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
    # A contingency element belongs to one contingency.
    contingency = None

    # <<< contingency_element
    # @generated
    def __init__(self, contingency=None, **kw_args):
        """ Initialises a new 'ContingencyElement' instance.
        """
        self.contingency = contingency

        super(ContingencyElement, self).__init__(**kw_args)
    # >>> contingency_element


class Contingency(IdentifiedObject):
    """ An event threatening system reliability, consisting of one or more contingency elements.
    """
    # Set true if must study this contingency. 
    must_study = False

    # A contingency can have any number of contingency elements.
    contingency_element = []

    contingency_constraint_limit = []

    # <<< contingency
    # @generated
    def __init__(self, must_study=False, contingency_element=[], contingency_constraint_limit=[], **kw_args):
        """ Initialises a new 'Contingency' instance.
        """
        self.must_study = must_study
        self.contingency_element = contingency_element
        self.contingency_constraint_limit = contingency_constraint_limit

        super(Contingency, self).__init__(**kw_args)
    # >>> contingency


class ContingencyEquipment(ContingencyElement):
    """ A equipment to which the in service status is to change such as a power transformer or AC line segment.
    """
    # The status for the associated equipment when in the contingency state.   This status is independent of the case to which the contingency is originally applied, but defines the equipment status when the contingency is applied. Values are: "out_of_service", "in_service"
    contingent_status = 'out_of_service'

    # The single piece of equipment to which to apply the contingency.
    equipment = None

    # <<< contingency_equipment
    # @generated
    def __init__(self, contingent_status='out_of_service', equipment=None, **kw_args):
        """ Initialises a new 'ContingencyEquipment' instance.
        """
        self.contingent_status = contingent_status
        self.equipment = equipment

        super(ContingencyEquipment, self).__init__(**kw_args)
    # >>> contingency_equipment


# <<< contingency
# @generated
# >>> contingency

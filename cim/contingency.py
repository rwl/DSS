# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class Contingency(IdentifiedObject):
    """ An event threatening system reliability, consisting of one or more contingency elements.
    """
    # Set true if must study this contingency. 
    must_study = False

    contingency_element = []

    # <<< contingency
    # @generated
    def __init__(self, must_study=False, contingency_element=[], **kw_args):
        """ Initialises a new 'Contingency' instance.
        """
        self.must_study = must_study
        self.contingency_element = contingency_element

        super(Contingency, self).__init__(**kw_args)
    # >>> contingency


class ContingencyElement(IdentifiedObject):
    """ An element of a system event to be studied by contingency analysis, representing a change in status of a single piece of equipment.
    """
    contingency = None

    # <<< contingency_element
    # @generated
    def __init__(self, contingency=None, **kw_args):
        """ Initialises a new 'ContingencyElement' instance.
        """
        self.contingency = contingency

        super(ContingencyElement, self).__init__(**kw_args)
    # >>> contingency_element


class ContingencyEquipment(ContingencyElement):
    """ A equipment to which the in service status is to change such as a power transformer or AC line segment.
    """
    # The status for the associated equipment when in the contingency state.   This status is independent of the case to which the contingency is originally applied, but defines the equipment status when the contingency is applied. Values are: "out_of_service", "in_service"
    contingent_status = 'out_of_service'

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

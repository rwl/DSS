# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970.core import ConnectivityNodeContainer
from iec61970.core import ConductingEquipment

# <<< imports
# @generated
# >>> imports

class EquivalentNetwork(ConnectivityNodeContainer):
    """ A class that represents an external meshed network that has been reduced to an electrically equivalent model. The ConnectivityNodes contained in the equivalent are intended to reflect internal nodes of the equivalent. The boundary Connectivity nodes where the equivalent connects outside itself are NOT contained by the equivalent.
    """
    equivalent_equipments = []

    # <<< equivalent_network
    # @generated
    def __init__(self, equivalent_equipments=[], **kw_args):
        """ Initialises a new 'EquivalentNetwork' instance.
        """
        self.equivalent_equipments = equivalent_equipments

        super(EquivalentNetwork, self).__init__(**kw_args)
    # >>> equivalent_network


class EquivalentEquipment(ConductingEquipment):
    """ The class represents equivalent objects that are the result of a network reduction. The class is the base for equivalent objects of different types.
    """
    equivalent_network = None

    # <<< equivalent_equipment
    # @generated
    def __init__(self, equivalent_network=None, **kw_args):
        """ Initialises a new 'EquivalentEquipment' instance.
        """
        self.equivalent_network = equivalent_network

        super(EquivalentEquipment, self).__init__(**kw_args)
    # >>> equivalent_equipment


class EquivalentBranch(EquivalentEquipment):
    """ The class represents equivalent branches.
    """
    # Positive sequence series reactance of the reduced branch. 
    x = 0.0

    # Positive sequence series resistance of the reduced branch. 
    r = 0.0

    # <<< equivalent_branch
    # @generated
    def __init__(self, x=0.0, r=0.0, **kw_args):
        """ Initialises a new 'EquivalentBranch' instance.
        """
        self.x = x
        self.r = r

        super(EquivalentBranch, self).__init__(**kw_args)
    # >>> equivalent_branch


class EquivalentShunt(EquivalentEquipment):
    """ The class represents equivalent shunts.
    """
    # Positive sequence shunt conductance. 
    g = 0.0

    # Positive sequence shunt susceptance. 
    b = 0.0

    # <<< equivalent_shunt
    # @generated
    def __init__(self, g=0.0, b=0.0, **kw_args):
        """ Initialises a new 'EquivalentShunt' instance.
        """
        self.g = g
        self.b = b

        super(EquivalentShunt, self).__init__(**kw_args)
    # >>> equivalent_shunt


# <<< equivalents
# @generated
# >>> equivalents

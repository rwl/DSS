# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import ConductingEquipment
from cim14.iec61970.core import ConnectivityNodeContainer

# <<< imports
# @generated
# >>> imports

class EquivalentEquipment(ConductingEquipment):
    """ The class represents equivalent objects that are the result of a network reduction. The class is the base for equivalent objects of different types.
    """
    # The equivalent where the reduced model belongs.
    equivalent_network = None

    # <<< equivalent_equipment
    # @generated
    def __init__(self, equivalent_network=None, **kw_args):
        """ Initialises a new 'EquivalentEquipment' instance.
        """
        self.equivalent_network = equivalent_network

        super(EquivalentEquipment, self).__init__(**kw_args)
    # >>> equivalent_equipment


class EquivalentNetwork(ConnectivityNodeContainer):
    """ A class that represents an external meshed network that has been reduced to an electrically equivalent model. The ConnectivityNodes contained in the equivalent are intended to reflect internal nodes of the equivalent. The boundary Connectivity nodes where the equivalent connects outside itself are NOT contained by the equivalent.
    """
    # The associated reduced equivalents.
    equivalent_equipments = []

    # <<< equivalent_network
    # @generated
    def __init__(self, equivalent_equipments=[], **kw_args):
        """ Initialises a new 'EquivalentNetwork' instance.
        """
        self.equivalent_equipments = equivalent_equipments

        super(EquivalentNetwork, self).__init__(**kw_args)
    # >>> equivalent_network


class EquivalentShunt(EquivalentEquipment):
    """ The class represents equivalent shunts.
    """
    # Positive sequence shunt conductance. 
    g = ''

    # Positive sequence shunt susceptance. 
    b = ''

    # <<< equivalent_shunt
    # @generated
    def __init__(self, g='', b='', **kw_args):
        """ Initialises a new 'EquivalentShunt' instance.
        """
        self.g = g
        self.b = b

        super(EquivalentShunt, self).__init__(**kw_args)
    # >>> equivalent_shunt


class EquivalentBranch(EquivalentEquipment):
    """ The class represents equivalent branches.
    """
    # Positive sequence series reactance of the reduced branch. 
    x = ''

    # Positive sequence series resistance of the reduced branch. 
    r = ''

    # <<< equivalent_branch
    # @generated
    def __init__(self, x='', r='', **kw_args):
        """ Initialises a new 'EquivalentBranch' instance.
        """
        self.x = x
        self.r = r

        super(EquivalentBranch, self).__init__(**kw_args)
    # >>> equivalent_branch


# <<< equivalents
# @generated
# >>> equivalents

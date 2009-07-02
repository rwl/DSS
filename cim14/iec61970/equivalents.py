# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import ConductingEquipment
from cim14.iec61970.core import ConnectivityNodeContainer

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Equivalents"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Equivalents"

class EquivalentEquipment(ConductingEquipment):
    """ The class represents equivalent objects that are the result of a network reduction. The class is the base for equivalent objects of different types.
    """
    # <<< equivalent_equipment
    # @generated
    def __init__(self, equivalent_network=None, **kw_args):
        """ Initialises a new 'EquivalentEquipment' instance.
        """
        
        self._equivalent_network = None
        self.equivalent_network = equivalent_network

        super(EquivalentEquipment, self).__init__(**kw_args)
    # >>> equivalent_equipment
        
    # <<< equivalent_network
    # @generated
    def get_equivalent_network(self):
        """ The equivalent where the reduced model belongs.
        """
        return self._equivalent_network

    def set_equivalent_network(self, value):
        if self._equivalent_network is not None:
            filtered = [x for x in self.equivalent_network.equivalent_equipments if x != self]
            self._equivalent_network._equivalent_equipments = filtered
            
        self._equivalent_network = value
        if self._equivalent_network is not None:
            self._equivalent_network._equivalent_equipments.append(self)

    equivalent_network = property(get_equivalent_network, set_equivalent_network)
    # >>> equivalent_network



class EquivalentNetwork(ConnectivityNodeContainer):
    """ A class that represents an external meshed network that has been reduced to an electrically equivalent model. The ConnectivityNodes contained in the equivalent are intended to reflect internal nodes of the equivalent. The boundary Connectivity nodes where the equivalent connects outside itself are NOT contained by the equivalent.
    """
    # <<< equivalent_network
    # @generated
    def __init__(self, equivalent_equipments=[], **kw_args):
        """ Initialises a new 'EquivalentNetwork' instance.
        """
        
        self._equivalent_equipments = []
        self.equivalent_equipments = equivalent_equipments

        super(EquivalentNetwork, self).__init__(**kw_args)
    # >>> equivalent_network
        
    # <<< equivalent_equipments
    # @generated
    def get_equivalent_equipments(self):
        """ The associated reduced equivalents.
        """
        return self._equivalent_equipments

    def set_equivalent_equipments(self, value):
        for x in self._equivalent_equipments:
            x._equivalent_network = None
        for y in value:
            y._equivalent_network = self
        self._equivalent_equipments = value
            
    equivalent_equipments = property(get_equivalent_equipments, set_equivalent_equipments)
    
    def add_equivalent_equipments(self, *equivalent_equipments):
        for obj in equivalent_equipments:
            obj._equivalent_network = self
            self._equivalent_equipments.append(obj)
        
    def remove_equivalent_equipments(self, *equivalent_equipments):
        for obj in equivalent_equipments:
            obj._equivalent_network = None
            self._equivalent_equipments.remove(obj)
    # >>> equivalent_equipments



class EquivalentShunt(EquivalentEquipment):
    """ The class represents equivalent shunts.
    """
    # <<< equivalent_shunt
    # @generated
    def __init__(self, g='', b='', **kw_args):
        """ Initialises a new 'EquivalentShunt' instance.
        """
        # Positive sequence shunt conductance. 
        self.g = ''
        # Positive sequence shunt susceptance. 
        self.b = ''
        

        super(EquivalentShunt, self).__init__(**kw_args)
    # >>> equivalent_shunt
        


class EquivalentBranch(EquivalentEquipment):
    """ The class represents equivalent branches.
    """
    # <<< equivalent_branch
    # @generated
    def __init__(self, x='', r='', **kw_args):
        """ Initialises a new 'EquivalentBranch' instance.
        """
        # Positive sequence series reactance of the reduced branch. 
        self.x = ''
        # Positive sequence series resistance of the reduced branch. 
        self.r = ''
        

        super(EquivalentBranch, self).__init__(**kw_args)
    # >>> equivalent_branch
        


# <<< equivalents
# @generated
# >>> equivalents

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.core2.collections import Collection
from iec61968.documentation.documentinheritance import Document
from  import 

# <<< imports
# @generated
# >>> imports

class ChangeSet(Collection):
    """ The updates required in a transaction for an existing data set are grouped into a single ChangeSet.  In data sets (e.g., NetworkDataSet), each major step in the ChangeSet is described through a separate ChangeItem associated with the data set.  Within each data set, each inidividual object change is described with a seperate ChangeItem associated with the object.  
    """
    network_data_sets = []

    land_bases = []

    load_data_sets = []

    # <<< change_set
    # @generated
    def __init__(self, network_data_sets=[], land_bases=[], load_data_sets=[], **kw_args):
        """ Initialises a new 'ChangeSet' instance.
        """
        self.network_data_sets = network_data_sets
        self.land_bases = land_bases
        self.load_data_sets = load_data_sets

        super(ChangeSet, self).__init__(**kw_args)
    # >>> change_set


class NetworkDataSet(Collection):
    """ Categorized as a type of document, a network data set is a model of a portion of the electrical network that includes a list of the equipment, along with relevant connectivity, electrical characteristics, geographical location, and various parameters associated with the equipment.
    """
    load_data_set = None

    power_system_resources = []

    change_sets = []

    circuits = []

    asset_catalogues = []

    circuit_sections = []

    # <<< network_data_set
    # @generated
    def __init__(self, load_data_set=None, power_system_resources=[], change_sets=[], circuits=[], asset_catalogues=[], circuit_sections=[], **kw_args):
        """ Initialises a new 'NetworkDataSet' instance.
        """
        self.load_data_set = load_data_set
        self.power_system_resources = power_system_resources
        self.change_sets = change_sets
        self.circuits = circuits
        self.asset_catalogues = asset_catalogues
        self.circuit_sections = circuit_sections

        super(NetworkDataSet, self).__init__(**kw_args)
    # >>> network_data_set


class LandBase(Document):
    """ Land base data.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    change_sets = []

    # <<< land_base
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', change_sets=[], **kw_args):
        """ Initialises a new 'LandBase' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.change_sets = change_sets

        super(LandBase, self).__init__(**kw_args)
    # >>> land_base


class LoadDataSet(Collection):
    """ Customer, supply point or area load data.  May include Meter records for customers (where regulations allow).
    """
    network_data_set = None

    load_demand_models = []

    change_sets = []

    load_areas = []

    # <<< load_data_set
    # @generated
    def __init__(self, network_data_set=None, load_demand_models=[], change_sets=[], load_areas=[], **kw_args):
        """ Initialises a new 'LoadDataSet' instance.
        """
        self.network_data_set = network_data_set
        self.load_demand_models = load_demand_models
        self.change_sets = change_sets
        self.load_areas = load_areas

        super(LoadDataSet, self).__init__(**kw_args)
    # >>> load_data_set


class Circuit(Collection):
    """ A static collection of conducting equipment originating at a main distribution center and supplying one or more secondary distribution centers, one or more branch-circuit distribution centers, or any combination of these two types of equipment.  It is the source to the next normally open point.
    """
    # The code for this circuit.  Some companies use this attribute to identify the circuit colour used for reference by operators, draftsmen, etc. on displays, maps, etc. 
    code = ''

    # Rated (not actual) voltage of the circuit. 
    rated_v = ''

    network_data_sets = []

    circuit_sections = []

    power_system_resources = []

    # <<< circuit
    # @generated
    def __init__(self, code='', rated_v='', network_data_sets=[], circuit_sections=[], power_system_resources=[], **kw_args):
        """ Initialises a new 'Circuit' instance.
        """
        self.code = code
        self.rated_v = rated_v
        self.network_data_sets = network_data_sets
        self.circuit_sections = circuit_sections
        self.power_system_resources = power_system_resources

        super(Circuit, self).__init__(**kw_args)
    # >>> circuit


class ChangeItem():
    """ The change type (add, delete, modify) and sequence number for this change within a set of changes.  The changes for this change item are articulated in an instances of data sets, lists, catalogues, and other message types.
    """
    # Type of change for the associated object: add, delete, modify. 
    change_type = ''

    # Relative order of this change in an ordered sequence of changes. 
    sequence_num = ''

    asset = None

    power_system_resource = None

    document = None

    erp_person = None

    organisation = None

    collections = []

    measurement = None

    gml_observation = None

    location = None

    gml_selector = None

    # <<< change_item
    # @generated
    def __init__(self, change_type='', sequence_num='', asset=None, power_system_resource=None, document=None, erp_person=None, organisation=None, collections=[], measurement=None, gml_observation=None, location=None, gml_selector=None, **kw_args):
        """ Initialises a new 'ChangeItem' instance.
        """
        self.change_type = change_type
        self.sequence_num = sequence_num
        self.asset = asset
        self.power_system_resource = power_system_resource
        self.document = document
        self.erp_person = erp_person
        self.organisation = organisation
        self.collections = collections
        self.measurement = measurement
        self.gml_observation = gml_observation
        self.location = location
        self.gml_selector = gml_selector

        super(ChangeItem, self).__init__(**kw_args)
    # >>> change_item


class CircuitSection(Collection):
    """ A section of circuit located between two sectionalizing devices.  A circuit section may contain other circuit sections, for example, a lateral tapped off a primary.
    """
    linear_conductors = []

    power_system_resources = []

    circuits = []

    member_of_circuit_section = None

    contains_circuit_sections = []

    network_data_set = []

    # <<< circuit_section
    # @generated
    def __init__(self, linear_conductors=[], power_system_resources=[], circuits=[], member_of_circuit_section=None, contains_circuit_sections=[], network_data_set=[], **kw_args):
        """ Initialises a new 'CircuitSection' instance.
        """
        self.linear_conductors = linear_conductors
        self.power_system_resources = power_system_resources
        self.circuits = circuits
        self.member_of_circuit_section = member_of_circuit_section
        self.contains_circuit_sections = contains_circuit_sections
        self.network_data_set = network_data_set

        super(CircuitSection, self).__init__(**kw_args)
    # >>> circuit_section


class DataSetsVersion(object):
 
    version = ''

 
    date = ''

    # <<< data_sets_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'DataSetsVersion' instance.
        """
        self.version = version
        self.date = date

        super(DataSetsVersion, self).__init__(**kw_args)
    # >>> data_sets_version


# <<< datasets
# @generated
# >>> datasets

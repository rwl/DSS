# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from iec61968.core2.activityrecords import History

# <<< imports
# @generated
# >>> imports

class Collection():
    """  Class for management of dynamic aggregation.
    """
    #  Type of collection. 
    collection_type = ''

    #  Number of items in collection. 
    collection_quantity = ''

    # Status at the time of 'statusDate.'  The Collection may be planned, active, retired, unknown, other.  For history of status changes, refer to assoicated activity records. 
    current_status = ''

    # Date currentStatus was entered. 
    status_date = ''

    documents = []

    change_items = []

    # <<< collection
    # @generated
    def __init__(self, collection_type='', collection_quantity='', current_status='', status_date='', documents=[], change_items=[], **kw_args):
        """ Initialises a new 'Collection' instance.
        """
        self.collection_type = collection_type
        self.collection_quantity = collection_quantity
        self.current_status = current_status
        self.status_date = status_date
        self.documents = documents
        self.change_items = change_items

        super(Collection, self).__init__(**kw_args)
    # >>> collection


class CollectionsVersion(object):
 
    version = ''

 
    date = ''

    # <<< collections_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'CollectionsVersion' instance.
        """
        self.version = version
        self.date = date

        super(CollectionsVersion, self).__init__(**kw_args)
    # >>> collections_version


class OutageHistory(History):
    """ The history of outages (OutageRecords) for a given customer.
    """
    pass
    # <<< outage_history
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'OutageHistory' instance.
        """

        super(OutageHistory, self).__init__(**kw_args)
    # >>> outage_history


class MeasurementList(Collection):
    """ A list of measurements and their  values.
    """
    measurements = []

    # <<< measurement_list
    # @generated
    def __init__(self, measurements=[], **kw_args):
        """ Initialises a new 'MeasurementList' instance.
        """
        self.measurements = measurements

        super(MeasurementList, self).__init__(**kw_args)
    # >>> measurement_list


class EquipmentList(Collection):
    """  A list of EquipmentAssets to be used together for some purpose such as a work task or procedure.
    """
    equipment_assets = []

    isolated_by_safety_documents = None

    work_task = None

    # <<< equipment_list
    # @generated
    def __init__(self, equipment_assets=[], isolated_by_safety_documents=None, work_task=None, **kw_args):
        """ Initialises a new 'EquipmentList' instance.
        """
        self.equipment_assets = equipment_assets
        self.isolated_by_safety_documents = isolated_by_safety_documents
        self.work_task = work_task

        super(EquipmentList, self).__init__(**kw_args)
    # >>> equipment_list


class PsrList(Collection):
    """ Used for various purposes, a collection of PowerSystemResources.
    """
    power_system_resources = []

    outage = None

    # <<< psr_list
    # @generated
    def __init__(self, power_system_resources=[], outage=None, **kw_args):
        """ Initialises a new 'PsrList' instance.
        """
        self.power_system_resources = power_system_resources
        self.outage = outage

        super(PsrList, self).__init__(**kw_args)
    # >>> psr_list


# <<< collections
# @generated
# >>> collections

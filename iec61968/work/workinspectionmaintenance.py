# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Work Ispection and Maintenance Package.
"""

from iec61968.assets.assetworktrigger import DataSet
from iec61968.documentation.documentinheritance import Document
from  import 

# <<< imports
# @generated
# >>> imports

class MaintenanceDataSet(DataSet):
    """ The result of a maintenance activity, a type of Procedure, for a given attribute of an asset is documentated in an MaintenanceDataSet. 
    """
    # Code for the type of maintenance performed. 
    maint_code = ''

    # Description of the condition of the asset just prior to maintenance being performed. 
    condition_before = ''

    # Condition of asset just following maintenance procedure. 
    condition_after = ''

    # Date and time maintenance procedure was completed. 
    maint_date = ''

    # <<< maintenance_data_set
    # @generated
    def __init__(self, maint_code='', condition_before='', condition_after='', maint_date='', **kw_args):
        """ Initialises a new 'MaintenanceDataSet' instance.
        """
        self.maint_code = maint_code
        self.condition_before = condition_before
        self.condition_after = condition_after
        self.maint_date = maint_date

        super(MaintenanceDataSet, self).__init__(**kw_args)
    # >>> maintenance_data_set


class InspectionDataSet(DataSet):
    """ The result of one inspection, a type of Procedure, for a given attribute of an asset is documentated in an InspectionDataSet. 
    """
    # A general description of the conditions of the location where the asset resides. 
    location_conditions = ''

    # Date and time this inspections was completed. 
    inspect_date = ''

    according_to_schedule = []

    # <<< inspection_data_set
    # @generated
    def __init__(self, location_conditions='', inspect_date='', according_to_schedule=[], **kw_args):
        """ Initialises a new 'InspectionDataSet' instance.
        """
        self.location_conditions = location_conditions
        self.inspect_date = inspect_date
        self.according_to_schedule = according_to_schedule

        super(InspectionDataSet, self).__init__(**kw_args)
    # >>> inspection_data_set


class Procedure(Document):
    """ A documented procedure for various types of Work or Work Tasks.  A compatible unit, a standard way of performing a unit of work, is guided by one or more procedures.   The type of procedure is defined in Procedure.type. For example, when type=Inspection, this procedure coupled with Schedule and other information provides the key items of an inspection plan.  Another type of Procedure is a Diagnosis.  Note that each specific values and settings to be used in a procedure is intended to be described in an instance of ProcedureValue.  A maintenance ticket, a type of Work, is generated whenever maintenance is determined to be needed as a result of an inspection or diagnosis.
    """
    #  Code for this type of procedure.  
    code = ''

    # The sequence number in a sequence of procedures being performed. 
    sequence_no = ''

    # The textual description of the procedure, which references instances of ProcedureValue as appropriate. 
    procedure_text = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of procedure. 
    procedure_type = ''

    limits = []

    data_sets = []

    procedure_values = []

    compatible_units = []

    # <<< procedure
    # @generated
    def __init__(self, code='', sequence_no='', procedure_text='', status='', status_date_time='', status_remarks='', procedure_type='', limits=[], data_sets=[], procedure_values=[], compatible_units=[], **kw_args):
        """ Initialises a new 'Procedure' instance.
        """
        self.code = code
        self.sequence_no = sequence_no
        self.procedure_text = procedure_text
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.procedure_type = procedure_type
        self.limits = limits
        self.data_sets = data_sets
        self.procedure_values = procedure_values
        self.compatible_units = compatible_units

        super(Procedure, self).__init__(**kw_args)
    # >>> procedure


class ProcedureValue():
    """ ProcedureValue is a name-value pair concept that provides the means for utilities to describe various values and settings to be used when performing proecdures on assets.  An example is to have an instance for each of the following settings when conducting a test: voltage, current, frequency, temperature.
    """
    # The type of value. 
    value_type = ''

    #  Describes what is the value means e.g. '3PhaseAmps', 'VA' etc  
    property = ''

    #  Value holds the actual value of for the procedure, 
    value = ''

    #  Units property is expressed in. 
    units = ''

    # Unit multiplier. 
    multiplier = ''

    procedures = None

    # <<< procedure_value
    # @generated
    def __init__(self, value_type='', property='', value='', units='', multiplier='', procedures=None, **kw_args):
        """ Initialises a new 'ProcedureValue' instance.
        """
        self.value_type = value_type
        self.property = property
        self.value = value
        self.units = units
        self.multiplier = multiplier
        self.procedures = procedures

        super(ProcedureValue, self).__init__(**kw_args)
    # >>> procedure_value


class WorkInspectionMaintenanceVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_inspection_maintenance_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkInspectionMaintenanceVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkInspectionMaintenanceVersion, self).__init__(**kw_args)
    # >>> work_inspection_maintenance_version


# <<< workinspectionmaintenance
# @generated
# >>> workinspectionmaintenance

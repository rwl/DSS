# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.documentation.documentinheritance import Document
from  import 
from iec61968.core2.identifiedobjectinheritance import Role

# <<< imports
# @generated
# >>> imports

class OperationalRestriction(Document):
    """ An Operational Restriction is a type of document that can be associated with a device to describe any sort of restrictions compared with the original manufacturer's specification e.g. temporary maximum loadings, maximum switching current, do not operate if bus couplers are open etc etc.  In the UK, for example, if a breaker or switch ever mal-operates, this is reported centrally and utilities use their asset systems to identify all the installed devices of the same manufacturer's type. They then apply operational restrictions in the operational systems to warn operators of potential problems. After appropriate inspection and maintenance, the operational restrictions may be removed. 
    """
    #  Date and time restriction is applied. 
    start_date_time = ''

    #  Date and time restriction is lifted. 
    end_date_time = ''

    #  Description of restriction. 
    restriction = ''

    #  Reason for applying restriction. 
    reason = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of restriction.  
    restriction_type = ''

    # <<< operational_restriction
    # @generated
    def __init__(self, start_date_time='', end_date_time='', restriction='', reason='', status='', status_date_time='', status_remarks='', restriction_type='', **kw_args):
        """ Initialises a new 'OperationalRestriction' instance.
        """
        self.start_date_time = start_date_time
        self.end_date_time = end_date_time
        self.restriction = restriction
        self.reason = reason
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.restriction_type = restriction_type

        super(OperationalRestriction, self).__init__(**kw_args)
    # >>> operational_restriction


class SafetyDocument(Document):
    """  A safety document is used during the course of work on the electrical system for safety purposes. There are many types of safety documents that could be defined based upon organisational practices. Note: ClearanceTag is a special case of a Safety Document.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of safety document.  
    safety_doc_type = ''

    isolate_equipment_list = None

    schedule_steps = []

    power_system_resource = None

    clearance_tags = []

    # <<< safety_document
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', safety_doc_type='', isolate_equipment_list=None, schedule_steps=[], power_system_resource=None, clearance_tags=[], **kw_args):
        """ Initialises a new 'SafetyDocument' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.safety_doc_type = safety_doc_type
        self.isolate_equipment_list = isolate_equipment_list
        self.schedule_steps = schedule_steps
        self.power_system_resource = power_system_resource
        self.clearance_tags = clearance_tags

        super(SafetyDocument, self).__init__(**kw_args)
    # >>> safety_document


class ScheduleStep():
    """  A single step within a SwitchingSchedule. Could be a switching operation, applying a network alteration or issuing a safety document.  Note: Inherited name from Naming.name is used to hold Sequence Number. 
    """
    #  Control actions required to perform this step. 
    required_control_action = ''

    # The time and date that the Required Control Action was issued. 
    instructed_date_time = ''

    # The time and date that the Required Control Action was completed. 
    completed_date_time = ''

    # Status of this schedule step.   
    status = ''

    # Desired end state for the associated PowerSystemResource as a result of this schedule step. 
    desired_end_state = ''

    power_system_resources = []

    switching_schedule = None

    erp_persons = None

    safety_document = None

    # <<< schedule_step
    # @generated
    def __init__(self, required_control_action='', instructed_date_time='', completed_date_time='', status='', desired_end_state='', power_system_resources=[], switching_schedule=None, erp_persons=None, safety_document=None, **kw_args):
        """ Initialises a new 'ScheduleStep' instance.
        """
        self.required_control_action = required_control_action
        self.instructed_date_time = instructed_date_time
        self.completed_date_time = completed_date_time
        self.status = status
        self.desired_end_state = desired_end_state
        self.power_system_resources = power_system_resources
        self.switching_schedule = switching_schedule
        self.erp_persons = erp_persons
        self.safety_document = safety_document

        super(ScheduleStep, self).__init__(**kw_args)
    # >>> schedule_step


class SwitchingSchedule(Document):
    """ A switching schedule is a document which identifies a sequence of switching operations which are to be performed on the electrical distribution network.  For example, a set of steps to isolate an item of plant with regard to safety, equipment ratings, and standards of customer service. Note 1:  that this (IEC 61968) SwitchingSchedule is intended to describe the full operational details for switching for real time operation which includes other operations such as grounding, applying safety documents etc.  Note 2: The association to ErpContact suits the UK practice of quoting specific names (e.g the crew foreman). The association to Crew is for US practice.
    """
    #  Start time of switching. 
    start_date_time = ''

    #  Completion time of switching. 
    end_date_time = ''

    #  Reason for switching. 
    reason = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    schedule_steps = []

    work_tasks = None

    # <<< switching_schedule
    # @generated
    def __init__(self, start_date_time='', end_date_time='', reason='', status='', status_date_time='', status_remarks='', schedule_steps=[], work_tasks=None, **kw_args):
        """ Initialises a new 'SwitchingSchedule' instance.
        """
        self.start_date_time = start_date_time
        self.end_date_time = end_date_time
        self.reason = reason
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.schedule_steps = schedule_steps
        self.work_tasks = work_tasks

        super(SwitchingSchedule, self).__init__(**kw_args)
    # >>> switching_schedule


class ErpPersonScheduleStepRole(Role):
    """ Roles played between Persons and Schedule Steps.
    """
    pass
    # <<< erp_person_schedule_step_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ErpPersonScheduleStepRole' instance.
        """

        super(ErpPersonScheduleStepRole, self).__init__(**kw_args)
    # >>> erp_person_schedule_step_role


class OperationalVersion(object):
 
    version = ''

 
    date = ''

    # <<< operational_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'OperationalVersion' instance.
        """
        self.version = version
        self.date = date

        super(OperationalVersion, self).__init__(**kw_args)
    # >>> operational_version


# <<< operational
# @generated
# >>> operational

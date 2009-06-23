# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61970.core import IrregularIntervalSchedule

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Outage"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Outage"

class SwitchingOperation(IdentifiedObject):
    """ A SwitchingOperation is used to define individual switch operations for an OutageSchedule. This OutageSchedule may be associated with another item of Substation such as a Transformer, Line, or Generator; or with the Switch itself as a PowerSystemResource. A Switch may be referenced by many OutageSchedules.
    """
    # The switch position that shall result from this SwitchingOperation Values are: "open", "close"
    new_state = 'open'

    # Time of operation in same units as OutageSchedule.xAxixUnits. 
    operation_time = ''

    # An OutageSchedule may operate many switches.
    outage_schedule = None

    # A switch may be operated by many schedules.
    switches = []

    # <<< switching_operation
    # @generated
    def __init__(self, new_state='open', operation_time='', outage_schedule=None, switches=[], **kw_args):
        """ Initialises a new 'SwitchingOperation' instance.
        """
        self.new_state = new_state
        self.operation_time = operation_time
        self.outage_schedule = outage_schedule
        self.switches = switches

        super(SwitchingOperation, self).__init__(**kw_args)
    # >>> switching_operation


class OutageSchedule(IrregularIntervalSchedule):
    """ The period of time that a piece of equipment is out of service, for example, for maintenance or testing; including the equipment's active power rating while under maintenance. The X-axis represents absolute time and the Y-axis represents the equipment's available rating while out of service.
    """
    planned_outage = None

    # A power system resource may have an outage schedule
    power_system_resource = None

    # An OutageSchedule may operate many switches.
    switching_operations = []

    # <<< outage_schedule
    # @generated
    def __init__(self, planned_outage=None, power_system_resource=None, switching_operations=[], **kw_args):
        """ Initialises a new 'OutageSchedule' instance.
        """
        self.planned_outage = planned_outage
        self.power_system_resource = power_system_resource
        self.switching_operations = switching_operations

        super(OutageSchedule, self).__init__(**kw_args)
    # >>> outage_schedule


class ClearanceTag(IdentifiedObject):
    """ A clearance tag that is used to authorize and schedule work on conducting equipment in the field. Tagged equipment is not available for commercial service.
    """
    # The time at which the clearance tag is scheduled to be set. 
    work_start_time = ''

    # Set true if equipment phasing must be checked 
    phase_check_req_flag = False

    # Set true if equipment must be deenergized 
    deenergize_req_flag = False

    # The time at which the clearance tag is scheduled to be removed 
    work_end_time = ''

    # The time at which the clearance tag was issued 
    tag_issue_time = ''

    # Description of the work to be performed 
    work_description = ''

    # Set true if equipment must be grounded 
    ground_req_flag = False

    # The name of the person who is authorized to issue the tag 
    authority_name = ''

    # Conducting equipment may have multiple clearance tags for authorized field work
    conducting_equipment = None

    safety_documents = []

    # The type of tag, depending on the purpose of the work to be performed and/or the type of supervisory control allowed.
    clearance_tag_type = None

    # <<< clearance_tag
    # @generated
    def __init__(self, work_start_time='', phase_check_req_flag=False, deenergize_req_flag=False, work_end_time='', tag_issue_time='', work_description='', ground_req_flag=False, authority_name='', conducting_equipment=None, safety_documents=[], clearance_tag_type=None, **kw_args):
        """ Initialises a new 'ClearanceTag' instance.
        """
        self.work_start_time = work_start_time
        self.phase_check_req_flag = phase_check_req_flag
        self.deenergize_req_flag = deenergize_req_flag
        self.work_end_time = work_end_time
        self.tag_issue_time = tag_issue_time
        self.work_description = work_description
        self.ground_req_flag = ground_req_flag
        self.authority_name = authority_name
        self.conducting_equipment = conducting_equipment
        self.safety_documents = safety_documents
        self.clearance_tag_type = clearance_tag_type

        super(ClearanceTag, self).__init__(**kw_args)
    # >>> clearance_tag


class ClearanceTagType(IdentifiedObject):
    """ Type of ClearanceTag. Could indicate the type of work to be performed and/or the type of supervisory control.
    """
    # The ClearanceTags currently being defined for this type.
    clearance_tags = []

    # <<< clearance_tag_type
    # @generated
    def __init__(self, clearance_tags=[], **kw_args):
        """ Initialises a new 'ClearanceTagType' instance.
        """
        self.clearance_tags = clearance_tags

        super(ClearanceTagType, self).__init__(**kw_args)
    # >>> clearance_tag_type


# <<< outage
# @generated
# >>> outage

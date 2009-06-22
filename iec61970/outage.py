# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61970.core import IdentifiedObject
from iec61970.core import IrregularIntervalSchedule

# <<< imports
# @generated
# >>> imports

class ClearanceTagType(IdentifiedObject):
    """ Type of ClearanceTag. Could indicate the type of work to be performed and/or the type of supervisory control.
    """
    clearance_tags = []

    # <<< clearance_tag_type
    # @generated
    def __init__(self, clearance_tags=[], **kw_args):
        """ Initialises a new 'ClearanceTagType' instance.
        """
        self.clearance_tags = clearance_tags

        super(ClearanceTagType, self).__init__(**kw_args)
    # >>> clearance_tag_type


class SwitchingOperation(IdentifiedObject):
    """ A SwitchingOperation is used to define individual switch operations for an OutageSchedule. This OutageSchedule may be associated with another item of Substation such as a Transformer, Line, or Generator; or with the Switch itself as a PowerSystemResource. A Switch may be referenced by many OutageSchedules.
    """
    # The switch position that shall result from this SwitchingOperation Values are: "close", "open"
    new_state = 'close'

    # Time of operation in same units as OutageSchedule.xAxixUnits. 
    operation_time = ''

    # A switch may be operated by many schedules.
    switches = []

    # An OutageSchedule may operate many switches.
    outage_schedule = None

    # <<< switching_operation
    # @generated
    def __init__(self, new_state='close', operation_time='', switches=[], outage_schedule=None, **kw_args):
        """ Initialises a new 'SwitchingOperation' instance.
        """
        self.new_state = new_state
        self.operation_time = operation_time
        self.switches = switches
        self.outage_schedule = outage_schedule

        super(SwitchingOperation, self).__init__(**kw_args)
    # >>> switching_operation


class OutageSchedule(IrregularIntervalSchedule):
    """ The period of time that a piece of equipment is out of service, for example, for maintenance or testing; including the equipment's active power rating while under maintenance. The X-axis represents absolute time and the Y-axis represents the equipment's available rating while out of service.
    """
    # An OutageSchedule may operate many switches.
    switching_operations = []

    # A power system resource may have an outage schedule
    psr = None

    # <<< outage_schedule
    # @generated
    def __init__(self, switching_operations=[], psr=None, **kw_args):
        """ Initialises a new 'OutageSchedule' instance.
        """
        self.switching_operations = switching_operations
        self.psr = psr

        super(OutageSchedule, self).__init__(**kw_args)
    # >>> outage_schedule


class ClearanceTag(IdentifiedObject):
    """ A clearance tag that is used to authorize and schedule work on conducting equipment in the field. Tagged equipment is not available for commercial service.
    """
    # Description of the work to be performed 
    work_description = ''

    # The time at which the clearance tag is scheduled to be set. 
    work_start_time = ''

    # The name of the person who is authorized to issue the tag 
    authority_name = ''

    # Set true if equipment must be deenergized 
    deenergize_req_flag = False

    # The time at which the clearance tag is scheduled to be removed 
    work_end_time = ''

    # The time at which the clearance tag was issued 
    tag_issue_time = ''

    # Set true if equipment must be grounded 
    ground_req_flag = False

    # Set true if equipment phasing must be checked 
    phase_check_req_flag = False

    # Conducting equipment may have multiple clearance tags for authorized field work
    conducting_equipment = None

    clearance_tag_type = None

    # <<< clearance_tag
    # @generated
    def __init__(self, work_description='', work_start_time='', authority_name='', deenergize_req_flag=False, work_end_time='', tag_issue_time='', ground_req_flag=False, phase_check_req_flag=False, conducting_equipment=None, clearance_tag_type=None, **kw_args):
        """ Initialises a new 'ClearanceTag' instance.
        """
        self.work_description = work_description
        self.work_start_time = work_start_time
        self.authority_name = authority_name
        self.deenergize_req_flag = deenergize_req_flag
        self.work_end_time = work_end_time
        self.tag_issue_time = tag_issue_time
        self.ground_req_flag = ground_req_flag
        self.phase_check_req_flag = phase_check_req_flag
        self.conducting_equipment = conducting_equipment
        self.clearance_tag_type = clearance_tag_type

        super(ClearanceTag, self).__init__(**kw_args)
    # >>> clearance_tag


# <<< outage
# @generated
# >>> outage

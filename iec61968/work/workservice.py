# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.core2.activityrecords import ScheduledEvent

# <<< imports
# @generated
# >>> imports

class Appointment(ScheduledEvent):
    """ A meeting time and location.
    """
    # The time at which the appointment begins. 
    start_time = ''

    # The time at which the meeting is to be completed. 
    end_time = ''

    # Information about the appointment. 
    remarks = ''

    # Flag is true if requested to call customer when someone is about to arrive at their premises. 
    call_ahead = ''

    erp_persons = []

    erp_address = None

    call_backs = None

    # <<< appointment
    # @generated
    def __init__(self, start_time='', end_time='', remarks='', call_ahead='', erp_persons=[], erp_address=None, call_backs=None, **kw_args):
        """ Initialises a new 'Appointment' instance.
        """
        self.start_time = start_time
        self.end_time = end_time
        self.remarks = remarks
        self.call_ahead = call_ahead
        self.erp_persons = erp_persons
        self.erp_address = erp_address
        self.call_backs = call_backs

        super(Appointment, self).__init__(**kw_args)
    # >>> appointment


class WorkServiceVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_service_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkServiceVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkServiceVersion, self).__init__(**kw_args)
    # >>> work_service_version


# <<< workservice
# @generated
# >>> workservice

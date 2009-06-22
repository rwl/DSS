# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from iec61968.documentation.documentinheritance import Document
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class ActivityRecord():
    """ An ActivityRecord records activity for an Asset, Location, PowerSystemResource, Customer, ErpContact (e.g.,  operator, market participant, etc.), or other object at a point in time.  An activity may be for an event that has already occurred or for a planned activity.   The PowerSystemResource relationship records events regarding the logical function being provided by the resource in the electrical network (independent of the particular asset providing the function).  The Asset relationship records history about the particular equipment, independent of where it is currently being used in the electrical network.   The Location relationship records events associated with the geographical location.  The Customer relationship records history regarding the customer independent of the logical network or particular assets being used to serve the customer.
    """
    # Status at the time of 'statusDateTime.'  For an asset, this is new construction (installed, ready to be placed in operation), in-service, decommissioned (out-of-service), removed, existing, proposed abandoned, proposed install, proposed remove, proposed replace, unknown, other. 
    status = ''

    # Reason code or explanation for why object went to this status. 
    status_reason = ''

    # Text that was recorded at the time specified in 'statusDateTime.' 
    remarks = ''

    # Status as of this time and date. 
    status_date_time = ''

    # The type of event resulting in this Activity Record. 
    event_type = ''

    # The category of this Activity Record. 
    category = ''

    # Description of reason for the event causing this Activity Record, typically supplied when user initiated. 
    reason = ''

    # Time stamp for creation of this Activity Record.  Note that this time is different from the statusDateTime, which is the time of a status change of the associated object, if applicable. 
    time_stamp = ''

    # The severity level of this event. 
    severity = ''

    scheduled_event = None

    documents = []

    organisations = []

    power_system_resources = []

    locations = []

    assets = []

    erp_persons = []

    meter_reading = None

    # <<< activity_record
    # @generated
    def __init__(self, status='', status_reason='', remarks='', status_date_time='', event_type='', category='', reason='', time_stamp='', severity='', scheduled_event=None, documents=[], organisations=[], power_system_resources=[], locations=[], assets=[], erp_persons=[], meter_reading=None, **kw_args):
        """ Initialises a new 'ActivityRecord' instance.
        """
        self.status = status
        self.status_reason = status_reason
        self.remarks = remarks
        self.status_date_time = status_date_time
        self.event_type = event_type
        self.category = category
        self.reason = reason
        self.time_stamp = time_stamp
        self.severity = severity
        self.scheduled_event = scheduled_event
        self.documents = documents
        self.organisations = organisations
        self.power_system_resources = power_system_resources
        self.locations = locations
        self.assets = assets
        self.erp_persons = erp_persons
        self.meter_reading = meter_reading

        super(ActivityRecord, self).__init__(**kw_args)
    # >>> activity_record


class History(Document):
    """  List of Documents and/or Events which form a history.
    """
    pass
    # <<< history
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'History' instance.
        """

        super(History, self).__init__(**kw_args)
    # >>> history


class TimePoint():
    """ A point in time within a sequence of points in time relative to a TimeSchedule.
    """
    # A point in time relative to a scheduled start time (TimeSchedule.subjectStatusDate).  Null if TimePoint is an absolute time or sequence based. 
    relative_time_interval = ''

    # The absolute time and date for this time point. Typically manually entered if TimePoint is calendar based, derived if interval based or sequence based. 
    absolute_time = ''

    # The relative sequence number for this time point.  Null if TimePoint is interval based or an absolute time. 
    sequence_number = ''

    # Beginning of the window of time that this time point is valid.  For example, seasonal, only on weekends, not on weekends, only 8:00 to 5:00, etc. 
    window_start = ''

    # End of the window of time that this time point is valid.   
    window_end = ''

    time_schedule = None

    scheduled_events = []

    # <<< time_point
    # @generated
    def __init__(self, relative_time_interval='', absolute_time='', sequence_number='', window_start='', window_end='', time_schedule=None, scheduled_events=[], **kw_args):
        """ Initialises a new 'TimePoint' instance.
        """
        self.relative_time_interval = relative_time_interval
        self.absolute_time = absolute_time
        self.sequence_number = sequence_number
        self.window_start = window_start
        self.window_end = window_end
        self.time_schedule = time_schedule
        self.scheduled_events = scheduled_events

        super(TimePoint, self).__init__(**kw_args)
    # >>> time_point


class TimeSchedule(Document):
    """ A 'schedule' is used for anything that needs to be described in terms of how it changes through time.  An object of this class is used to perform a single-valued function of time.  
    """
    # Type of time schedule such as: periodic (hourly, daily, weekly, monthly, etc.), day of the month, by date, calendar (specific times and dates). 
    time_schedule_type = ''

    # Specifies the interval at which an appointment repeats.   Example include: first Monday of every month; last day of the month; etc. 
    recurrence_pattern = ''

    # Seconds between time points, from the beinning of one period to the beginning of the next period.  Note that a device like a meter may have multiple interval periods (e.g., 1, 5, 15, 30, or 60 minutes). 
    recurrence_period = ''

    #  Schedule date. 
    schedule_start_date = ''

    #  Scheduled finish date. 
    schedule_finish_date = ''

    # The offset from midnight (i.e., 0 hours, 0 minutes, 0 seconds) for the periodic time points to begin.  For example, for an interval meter that is set up for five minute intervals, setting recurrencePeriod=300 (5 minutes) and offset=120 (2 minutes) would result in scheduled events to read the meter executing at 2, 7, 12, 17, 22, 27, 32, 37, 42, 47, 52, and 57 minutes past each hour. 
    offset = ''

    # True if the schedule is activated (enabled). 
    enabled = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Reason code or explanation for why this went to the current status. 
    status_reason = ''

    time_points = []

    meter_groups = []

    # <<< time_schedule
    # @generated
    def __init__(self, time_schedule_type='', recurrence_pattern='', recurrence_period='', schedule_start_date='', schedule_finish_date='', offset='', enabled='', status='', status_date_time='', status_remarks='', status_reason='', time_points=[], meter_groups=[], **kw_args):
        """ Initialises a new 'TimeSchedule' instance.
        """
        self.time_schedule_type = time_schedule_type
        self.recurrence_pattern = recurrence_pattern
        self.recurrence_period = recurrence_period
        self.schedule_start_date = schedule_start_date
        self.schedule_finish_date = schedule_finish_date
        self.offset = offset
        self.enabled = enabled
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.status_reason = status_reason
        self.time_points = time_points
        self.meter_groups = meter_groups

        super(TimeSchedule, self).__init__(**kw_args)
    # >>> time_schedule


class ScheduledEvent():
    """ Signifies an event to trigger one or more activities, such as reading a meter, recalculating a bill, requesting work, when generating units must be scheduled for maintenance, when a transformers is scheduled to be refurbished, etc..
    """
    # The type of event that has been scheduled. 
    event_type = ''

    # Duration of the scheduled event, for example, the time to ramp between values. 
    event_duration = ''

    assets = []

    time_point = None

    activity_record = None

    document = None

    schedule_parameters = None

    # <<< scheduled_event
    # @generated
    def __init__(self, event_type='', event_duration='', assets=[], time_point=None, activity_record=None, document=None, schedule_parameters=None, **kw_args):
        """ Initialises a new 'ScheduledEvent' instance.
        """
        self.event_type = event_type
        self.event_duration = event_duration
        self.assets = assets
        self.time_point = time_point
        self.activity_record = activity_record
        self.document = document
        self.schedule_parameters = schedule_parameters

        super(ScheduledEvent, self).__init__(**kw_args)
    # >>> scheduled_event


class ScheduleParameters():
    """ Schedule parameters for an activity that is to occur, is occurring, or has completed.
    """
    #  Requested date for activity initiation. 
    requested_start_date_time = ''

    # Requested date for activity completion. 
    requested_finish_date_time = ''

    #  Estimated date for earliest possibility of activity initiation. 
    earliest_start_date_time = ''

    #  Estimate for latest date activity should be completed. 
    latest_finish_date_time = ''

    for_inspection_data_sets = None

    scheduled_events = []

    documents = []

    # <<< schedule_parameters
    # @generated
    def __init__(self, requested_start_date_time='', requested_finish_date_time='', earliest_start_date_time='', latest_finish_date_time='', for_inspection_data_sets=None, scheduled_events=[], documents=[], **kw_args):
        """ Initialises a new 'ScheduleParameters' instance.
        """
        self.requested_start_date_time = requested_start_date_time
        self.requested_finish_date_time = requested_finish_date_time
        self.earliest_start_date_time = earliest_start_date_time
        self.latest_finish_date_time = latest_finish_date_time
        self.for_inspection_data_sets = for_inspection_data_sets
        self.scheduled_events = scheduled_events
        self.documents = documents

        super(ScheduleParameters, self).__init__(**kw_args)
    # >>> schedule_parameters


class BusinessPlan(Document):
    """ A BusinessPlan is an organized sequence of predetermined actions required to complete a future organizational objective.  It is a type of document that typically references a schedule, physical and/or logical resources (assets and/or PowerSystemResources), locations, etc.
    """
    pass
    # <<< business_plan
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'BusinessPlan' instance.
        """

        super(BusinessPlan, self).__init__(**kw_args)
    # >>> business_plan


class ActivityRecordsVersion(object):
 
    version = ''

 
    date = ''

    # <<< activity_records_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'ActivityRecordsVersion' instance.
        """
        self.version = version
        self.date = date

        super(ActivityRecordsVersion, self).__init__(**kw_args)
    # >>> activity_records_version


class MeterEvent(ActivityRecord):
    """ A meter event is used to convey events that are detected by a meter
    """
    # User ID for events that are user initiated 
    user_id = ''

    # Optional argument that can be supplied with an event 
    argument = ''

    device_function = None

    # <<< meter_event
    # @generated
    def __init__(self, user_id='', argument='', device_function=None, **kw_args):
        """ Initialises a new 'MeterEvent' instance.
        """
        self.user_id = user_id
        self.argument = argument
        self.device_function = device_function

        super(MeterEvent, self).__init__(**kw_args)
    # >>> meter_event


# <<< activityrecords
# @generated
# >>> activityrecords

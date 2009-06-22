# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.documentation.documentinheritance import Document
from iec61968.core2.activityrecords import ActivityRecord
from  import 

# <<< imports
# @generated
# >>> imports

class TroubleTicket(Document):
    """  A trouble ticket is a document which is used to report electrical trouble. The trouble may either be an outage or non-outage problem, such as power quality.  It must be always be associated with an Incident Record.  Note that a separate Activity Record is created for each call associated with an instance of Trouble Ticket.  The time of a call is stored in ActivityRecord.createdOn and comments are recorded in ActivityRecord.remarks.
    """
    #  Code which describes the type of trouble. 
    type_of_trouble = ''

    # Time trouble call first received.  The time of subsequent calls by the same customer for the same trouble are recorded in associated Activity Records. 
    time_of_comm = ''

    #  Priority of trouble call. 
    priority = ''

    #  Time of restoration of resolution of trouble. 
    restoration_time = ''

    # Code for a reported hazard condition. 
    hazard_code = ''

    # Flag set true if person reporting trouble requested a communication back when sigificant information became available about cause of the outage and the estimated restoration time.  The person and their contact information is maintained in the assoicated Customer informaiton.  Communication back results are recorded in an assoicated ActivityRecord.remarks. 
    comm_back_ertflag = ''

    # Flag set true if person reporting trouble requested a communication back when to confirm power has been restored.  The person and their contact information is maintained in the assoicated Customer informaiton.  communication back results are recorded in an assoicated ActivityRecord.remarks. 
    comm_back_restore_flag = ''

    # Estimated restoration time last provided to customer. 
    e_rt = ''

    # Communication from a customer may be via telephone call, email, written letter, etc. 
    type_of_communication = ''

    # The time and date at which this source of trouble started. 
    start_time_of_problem = ''

    # Descriptive information about the problem reported when trouble ticket was created. 
    problem_info = ''

    # Flag is true if requested to customer when someone is about to arrive at their  premises. 
    call_back = ''

    # Advice already given to the customer at time when trouble was first reported. 
    advice = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    incident_record = None

    customer_data = None

    call_backs = []

    # <<< trouble_ticket
    # @generated
    def __init__(self, type_of_trouble='', time_of_comm='', priority='', restoration_time='', hazard_code='', comm_back_ertflag='', comm_back_restore_flag='', e_rt='', type_of_communication='', start_time_of_problem='', problem_info='', call_back='', advice='', status='', status_date_time='', status_remarks='', incident_record=None, customer_data=None, call_backs=[], **kw_args):
        """ Initialises a new 'TroubleTicket' instance.
        """
        self.type_of_trouble = type_of_trouble
        self.time_of_comm = time_of_comm
        self.priority = priority
        self.restoration_time = restoration_time
        self.hazard_code = hazard_code
        self.comm_back_ertflag = comm_back_ertflag
        self.comm_back_restore_flag = comm_back_restore_flag
        self.e_rt = e_rt
        self.type_of_communication = type_of_communication
        self.start_time_of_problem = start_time_of_problem
        self.problem_info = problem_info
        self.call_back = call_back
        self.advice = advice
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.incident_record = incident_record
        self.customer_data = customer_data
        self.call_backs = call_backs

        super(TroubleTicket, self).__init__(**kw_args)
    # >>> trouble_ticket


class Outage(Document):
    """  A planned outage involves network operations which will affect the supply of power to customers.  Note that the list of Power System Resources for the Outage may be the same or a superset of the ones per OutageStep.  
    """
 Values are: "fixed", "flexible", "forced"
    outage_type = 'fixed'

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_list = None

    outage_schedules = []

    psr_list = None

    # <<< outage
    # @generated
    def __init__(self, outage_type='fixed', status='', status_date_time='', status_remarks='', customer_list=None, outage_schedules=[], psr_list=None, **kw_args):
        """ Initialises a new 'Outage' instance.
        """
        self.outage_type = outage_type
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_list = customer_list
        self.outage_schedules = outage_schedules
        self.psr_list = psr_list

        super(Outage, self).__init__(**kw_args)
    # >>> outage


class OutageNotification(Document):
    """  A document containing information to be sent to customers notifying that an outage will take place. This is used to generate mailing lists for customers.
    """
    # The number of possible interruptions that the customer may expect for this event. 
    num_possible_interruptions = ''

    # The likely duration of the interruption(s). 
    duation = ''

    # Details of the outage 'reason' 
    reason = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_list = []

    # <<< outage_notification
    # @generated
    def __init__(self, num_possible_interruptions='', duation='', reason='', status='', status_date_time='', status_remarks='', customer_list=[], **kw_args):
        """ Initialises a new 'OutageNotification' instance.
        """
        self.num_possible_interruptions = num_possible_interruptions
        self.duation = duation
        self.reason = reason
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_list = customer_list

        super(OutageNotification, self).__init__(**kw_args)
    # >>> outage_notification


class ComplianceEvent(ActivityRecord):
    """ Compliance events are used for reporting regulatory or contract compliance issues and/or variances.  These might be created as a consequence of local business processes and associated rules.   Note: it is anticipated that this class will be customised extensively to meet local implementation needs.
    """
    # Type of compliance event indicating, for example, types of regulatory and/or contractual compliance events where expected performance will not be met or reported as mandated. 
    compliance_type = ''

    # The deadline for compliance. 
    deadline = ''

    # <<< compliance_event
    # @generated
    def __init__(self, compliance_type='', deadline='', **kw_args):
        """ Initialises a new 'ComplianceEvent' instance.
        """
        self.compliance_type = compliance_type
        self.deadline = deadline

        super(ComplianceEvent, self).__init__(**kw_args)
    # >>> compliance_event


class TroubleTicketsVersion(object):
 
    version = ''

 
    date = ''

    # <<< trouble_tickets_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'TroubleTicketsVersion' instance.
        """
        self.version = version
        self.date = date

        super(TroubleTicketsVersion, self).__init__(**kw_args)
    # >>> trouble_tickets_version


class CallBacks():
    """ Information about a planned Call Back or a CallBack that has occurred, from the utility to a customer regarding the status and plans about resolving trouble, performing work, etc.
    """
    # Additional contact details that are not provided for ErpPerson with ErpTelephoneNumber 
    call_back_contact_details = ''

    # Advice already given to the customer during this call back. 
    advice = ''

    # Descriptive information about the problem reported during this call back. 
    problem_info = ''

    # Comments by customer during this call back. 
    comments = ''

    # The time and date when this call back occurred.  Null if it has not occurred yet. 
    time_call_back = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    trouble_tickets = []

    erp_persons = []

    appointment = []

    # <<< call_backs
    # @generated
    def __init__(self, call_back_contact_details='', advice='', problem_info='', comments='', time_call_back='', status='', status_date_time='', status_remarks='', trouble_tickets=[], erp_persons=[], appointment=[], **kw_args):
        """ Initialises a new 'CallBacks' instance.
        """
        self.call_back_contact_details = call_back_contact_details
        self.advice = advice
        self.problem_info = problem_info
        self.comments = comments
        self.time_call_back = time_call_back
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.trouble_tickets = trouble_tickets
        self.erp_persons = erp_persons
        self.appointment = appointment

        super(CallBacks, self).__init__(**kw_args)
    # >>> call_backs


# <<< troubletickets
# @generated
# >>> troubletickets

# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document
from cim14.iec61968.informative.inf_common import Role
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import ActivityRecord

# <<< imports
# @generated
# >>> imports

class OperationalRestriction(Document):
    """ A document that can be associated with a device to describe any sort of restrictions compared with the original manufacturer's specification e.g. temporary maximum loadings, maximum switching current, do not operate if bus couplers are open etc etc.  In the UK, for example, if a breaker or switch ever mal-operates, this is reported centrally and utilities use their asset systems to identify all the installed devices of the same manufacturer's type. They then apply operational restrictions in the operational systems to warn operators of potential problems. After appropriate inspection and maintenance, the operational restrictions may be removed.
    """
    # Date and time restriction is applied. 
    start_date_time = ''

    # Reason for applying restriction. 
    reason = ''

    # Date and time restriction is lifted. 
    end_date_time = ''

    # <<< operational_restriction
    # @generated
    def __init__(self, start_date_time='', reason='', end_date_time='', **kw_args):
        """ Initialises a new 'OperationalRestriction' instance.
        """
        self.start_date_time = start_date_time
        self.reason = reason
        self.end_date_time = end_date_time

        super(OperationalRestriction, self).__init__(**kw_args)
    # >>> operational_restriction


class ErpPersonScheduleStepRole(Role):
    """ Roles played between Persons and Schedule Steps.
    """
    switching_step = None

    erp_person = None

    # <<< erp_person_schedule_step_role
    # @generated
    def __init__(self, switching_step=None, erp_person=None, **kw_args):
        """ Initialises a new 'ErpPersonScheduleStepRole' instance.
        """
        self.switching_step = switching_step
        self.erp_person = erp_person

        super(ErpPersonScheduleStepRole, self).__init__(**kw_args)
    # >>> erp_person_schedule_step_role


class OutageReport(Document):
    """ Document with statistics of an outage.
    """
    # Total Customer Minutes Lost (CML). 
    total_cml = ''

    # Average Customer Minutes Lost (CML) for this outage. 
    average_cml = ''

    # Total number of outaged customers. 
    customer_count = 0

    # Total outage duration. 
    outage_duration = ''

    # reference to related document
    outage_record = None

    # OutageHistory of a customer, which may include this OutageReport.
    outage_history = None

    # <<< outage_report
    # @generated
    def __init__(self, total_cml='', average_cml='', customer_count=0, outage_duration='', outage_record=None, outage_history=None, **kw_args):
        """ Initialises a new 'OutageReport' instance.
        """
        self.total_cml = total_cml
        self.average_cml = average_cml
        self.customer_count = customer_count
        self.outage_duration = outage_duration
        self.outage_record = outage_record
        self.outage_history = outage_history

        super(OutageReport, self).__init__(**kw_args)
    # >>> outage_report


class IncidentCode(IdentifiedObject):
    """ Classification of incident types. Multiple incident codes may apply to a given incident. The primary overall incident cause is recorded in 'IncidentRecord.category', and the main code in 'name'.
    """
    # Additional level of classification detail (as extension to the main code found in 'name'). 
    sub_code = ''

    incident_records = []

    # <<< incident_code
    # @generated
    def __init__(self, sub_code='', incident_records=[], **kw_args):
        """ Initialises a new 'IncidentCode' instance.
        """
        self.sub_code = sub_code
        self.incident_records = incident_records

        super(IncidentCode, self).__init__(**kw_args)
    # >>> incident_code


class ComplianceEvent(ActivityRecord):
    """ Compliance events are used for reporting regulatory or contract compliance issues and/or variances. These might be created as a consequence of local business processes and associated rules. It is anticipated that this class will be customised extensively to meet local implementation needs. Use inherited 'category' to indicate that, for example, expected performance will not be met or reported as mandated.
    """
    # The deadline for compliance. 
    deadline = ''

    # Type of compliance event indicating, for example, types of regulatory and/or contractual compliance events where expected performance will not be met or reported as mandated. 
    compliance_type = ''

    # <<< compliance_event
    # @generated
    def __init__(self, deadline='', compliance_type='', **kw_args):
        """ Initialises a new 'ComplianceEvent' instance.
        """
        self.deadline = deadline
        self.compliance_type = compliance_type

        super(ComplianceEvent, self).__init__(**kw_args)
    # >>> compliance_event


class OutageCode(IdentifiedObject):
    """ Classification of outage types. Multiple outage codes may apply to a given outage or outage step.The primary overall outage type is recorded in OutageRecord.outageType. There may be more than one classification per outage step and/or per outage record. Example codes/subcodes include: weather/ice, weather/lightning, wildlife/squirrel, wildlife/bird, burned/overload, burned/weather, wire down/accident, wire down/tree, wire down/vandalism, etc. The typical 'outage code' is in the inherited 'name' attribute. The code is described in the inherited 'description' attribute.
    """
    # The main code is stored in the inherited .name. This sub-code provides an additional level of classification detail. 
    sub_code = ''

    outage_records = []

    outage_steps = []

    # <<< outage_code
    # @generated
    def __init__(self, sub_code='', outage_records=[], outage_steps=[], **kw_args):
        """ Initialises a new 'OutageCode' instance.
        """
        self.sub_code = sub_code
        self.outage_records = outage_records
        self.outage_steps = outage_steps

        super(OutageCode, self).__init__(**kw_args)
    # >>> outage_code


class NetworkDataSet(IdentifiedObject):
    """ Categorized as a type of document, model of a portion of the electrical network that includes a list of the equipment, along with relevant connectivity, electrical characteristics, geographical location, and various parameters associated with the equipment.
    """
    # Category of network data set. 
    category = ''

    status = None

    land_bases = []

    power_system_resources = []

    circuits = []

    documents = []

    change_sets = []

    # A NetworkDataSet may contain sections of circuits (vs. whole circuits).
    circuit_sections = []

    change_items = []

    # <<< network_data_set
    # @generated
    def __init__(self, category='', status=None, land_bases=[], power_system_resources=[], circuits=[], documents=[], change_sets=[], circuit_sections=[], change_items=[], **kw_args):
        """ Initialises a new 'NetworkDataSet' instance.
        """
        self.category = category
        self.status = status
        self.land_bases = land_bases
        self.power_system_resources = power_system_resources
        self.circuits = circuits
        self.documents = documents
        self.change_sets = change_sets
        self.circuit_sections = circuit_sections
        self.change_items = change_items

        super(NetworkDataSet, self).__init__(**kw_args)
    # >>> network_data_set


class LandBase(Document):
    """ Land base data.
    """
    change_sets = []

    network_data_sets = []

    # <<< land_base
    # @generated
    def __init__(self, change_sets=[], network_data_sets=[], **kw_args):
        """ Initialises a new 'LandBase' instance.
        """
        self.change_sets = change_sets
        self.network_data_sets = network_data_sets

        super(LandBase, self).__init__(**kw_args)
    # >>> land_base


class PlannedOutage(Document):
    """ Planned outage involves network operations which will affect the supply of power to customers. Note that the list of Power System Resources for the PlannedOutage may be the same or a superset of the ones per OutageStep.
    """
    # Kind of outage. Values are: "fixed", "forced", "flexible"
    kind = 'fixed'

    outage_schedules = []

    # All customers affected by this work. Derived from WorkOrder.connectedCustomers
    customer_datas = []

    # <<< planned_outage
    # @generated
    def __init__(self, kind='fixed', outage_schedules=[], customer_datas=[], **kw_args):
        """ Initialises a new 'PlannedOutage' instance.
        """
        self.kind = kind
        self.outage_schedules = outage_schedules
        self.customer_datas = customer_datas

        super(PlannedOutage, self).__init__(**kw_args)
    # >>> planned_outage


class CircuitSection(IdentifiedObject):
    """ Section of circuit located between two sectionalizing devices. It may contain other circuit sections, for example, a lateral tapped off a primary.
    """
    # Kind of this circuit section. Values are: "nominally_connected", "electrically_connected", "as_built", "other"
    connection_kind = 'nominally_connected'

    power_system_resources = []

    member_of_circuit_section = None

    linear_conductors = []

    contains_circuit_sections = []

    network_data_sets = []

    circuits = []

    # <<< circuit_section
    # @generated
    def __init__(self, connection_kind='nominally_connected', power_system_resources=[], member_of_circuit_section=None, linear_conductors=[], contains_circuit_sections=[], network_data_sets=[], circuits=[], **kw_args):
        """ Initialises a new 'CircuitSection' instance.
        """
        self.connection_kind = connection_kind
        self.power_system_resources = power_system_resources
        self.member_of_circuit_section = member_of_circuit_section
        self.linear_conductors = linear_conductors
        self.contains_circuit_sections = contains_circuit_sections
        self.network_data_sets = network_data_sets
        self.circuits = circuits

        super(CircuitSection, self).__init__(**kw_args)
    # >>> circuit_section


class TroubleTicket(Document):
    """ A document used to report electrical trouble. The trouble may either be an outage or non-outage problem, such as power quality. It must always be associated with an Incident Record. Note that a separate Activity Record is created for each call associated with an instance of Trouble Ticket. The time of a call is stored in ActivityRecord.createdOn and comments are recorded in ActivityRecord.remarks.
    """
    # Date and time at which this source of trouble started. 
    start_date_time = ''

    # Date and time trouble call first received. The date and time of subsequent calls by the same customer for the same trouble are recorded in associated Activity Records. 
    first_call_date_time = ''

    # True if person reporting trouble requested a call back to confirm power has been restored. The person and their contact information is maintained in the assoicated Customer informaiton. Call back results are recorded in assoicated 'ActivityRecord.Status.remarks'. 
    inform_after_restored = False

    # Priority of trouble call. 
    priority = ''

    # Estimated restoration date and time last provided to the customer. 
    estimated_restore_date_time = ''

    # True if person reporting trouble requested a call back when sigificant information became available about cause of the outage and the estimated restoration time. The person and their contact information are maintained in the assoicated Customer information. Call back results are recorded in assoicated 'ActivityRecord.Status.remarks'. 
    inform_before_restored = False

    # Advice already given to the customer at time when trouble was first reported. 
    advice = ''

    # Time of restoration of resolution of trouble. 
    restoration_time = ''

    # Code for a reported hazard condition. 
    hazard_code = ''

    # True if requested to customer when someone is about to arrive at their premises. 
    call_back = False

    # Means the customer used to report trouble (default is 'call'). Values are: "letter", "email", "other", "call"
    reporting_kind = 'letter'

    call_backs = []

    customer_data = None

    incident_record = None

    # <<< trouble_ticket
    # @generated
    def __init__(self, start_date_time='', first_call_date_time='', inform_after_restored=False, priority='', estimated_restore_date_time='', inform_before_restored=False, advice='', restoration_time='', hazard_code='', call_back=False, reporting_kind='letter', call_backs=[], customer_data=None, incident_record=None, **kw_args):
        """ Initialises a new 'TroubleTicket' instance.
        """
        self.start_date_time = start_date_time
        self.first_call_date_time = first_call_date_time
        self.inform_after_restored = inform_after_restored
        self.priority = priority
        self.estimated_restore_date_time = estimated_restore_date_time
        self.inform_before_restored = inform_before_restored
        self.advice = advice
        self.restoration_time = restoration_time
        self.hazard_code = hazard_code
        self.call_back = call_back
        self.reporting_kind = reporting_kind
        self.call_backs = call_backs
        self.customer_data = customer_data
        self.incident_record = incident_record

        super(TroubleTicket, self).__init__(**kw_args)
    # >>> trouble_ticket


class OutageNotification(Document):
    """ A document containing information to be sent to customers notifying that an outage will take place. This is used to generate mailing lists for customers.
    """
    # Details of the outage 'reason'. 
    reason = ''

    # Likely duration of the interruption(s). 
    duration = ''

    # Number of possible interruptions that the customer may expect for this event. 
    expected_interruption_count = 0

    customer_datas = []

    # <<< outage_notification
    # @generated
    def __init__(self, reason='', duration='', expected_interruption_count=0, customer_datas=[], **kw_args):
        """ Initialises a new 'OutageNotification' instance.
        """
        self.reason = reason
        self.duration = duration
        self.expected_interruption_count = expected_interruption_count
        self.customer_datas = customer_datas

        super(OutageNotification, self).__init__(**kw_args)
    # >>> outage_notification


class OutageStep(IdentifiedObject):
    """ Holds an outage start and end time for each supply point of an outage record. The supply point for a given step is the associated PowerSystemResource instance.
    """
    # True if injuries reported by caller or engineer. 
    injury = False

    # Average Customer Minutes Lost (CML) for this supply point for this outage. 
    average_cml = ''

    # Number of customers with high reliability required. 
    special_customer_count = 0

    # Number of customers connected to the PowerSystemResource. 
    total_customer_count = 0

 
    job_priority = ''

    # Estimated time of restoration. 
    estimated_restore_date_time = ''

    # True if fatalities reported by caller or engineer. 
    fatality = False

    # True if damage reported by caller or engineer. 
    damage = False

    # Number of customers with critical needs, e.g., with a dialysis machine. 
    critical_customer_count = 0

    # True if shocks reported by caller or engineer. 
    shock_reported = False

    # Number of customers phoning in. 
    caller_count = 0

    # Total Customer Minutes Lost (CML) for this supply point for this outage. 
    total_cml = ''

    status = None

    # Date and time interval between loss and restoration of power.
    no_power_interval = None

    conducting_equipment_roles = []

    outage_record = None

    # Multiple outage codes may apply to an outage step.
    outage_codes = []

    crews = []

    # <<< outage_step
    # @generated
    def __init__(self, injury=False, average_cml='', special_customer_count=0, total_customer_count=0, job_priority='', estimated_restore_date_time='', fatality=False, damage=False, critical_customer_count=0, shock_reported=False, caller_count=0, total_cml='', status=None, no_power_interval=None, conducting_equipment_roles=[], outage_record=None, outage_codes=[], crews=[], **kw_args):
        """ Initialises a new 'OutageStep' instance.
        """
        self.injury = injury
        self.average_cml = average_cml
        self.special_customer_count = special_customer_count
        self.total_customer_count = total_customer_count
        self.job_priority = job_priority
        self.estimated_restore_date_time = estimated_restore_date_time
        self.fatality = fatality
        self.damage = damage
        self.critical_customer_count = critical_customer_count
        self.shock_reported = shock_reported
        self.caller_count = caller_count
        self.total_cml = total_cml
        self.status = status
        self.no_power_interval = no_power_interval
        self.conducting_equipment_roles = conducting_equipment_roles
        self.outage_record = outage_record
        self.outage_codes = outage_codes
        self.crews = crews

        super(OutageStep, self).__init__(**kw_args)
    # >>> outage_step


class OutageStepPsrRole(Role):
    """ Roles played between Power System Resources and Outage Steps. Examples roles include: normal supply, actual supply, interrupting device, restoration device.
    """
    conducting_equipment = None

    outage_step = None

    # <<< outage_step_psr_role
    # @generated
    def __init__(self, conducting_equipment=None, outage_step=None, **kw_args):
        """ Initialises a new 'OutageStepPsrRole' instance.
        """
        self.conducting_equipment = conducting_equipment
        self.outage_step = outage_step

        super(OutageStepPsrRole, self).__init__(**kw_args)
    # >>> outage_step_psr_role


class IncidentRecord(Document):
    """ Document describing the incident reported in a TroubleTicket. If the incident has to do with an outage, this will be associated with an OutageRecord. Primary cause of the incident is captured in 'category'.
    """
    # Date and time incident was resolved for all customers impacted by this incident. 
    end_date_time = ''

    # Date and time first customer was impacted by the incident. 
    start_date_time = ''

    trouble_tickets = []

    incident_codes = []

    # <<< incident_record
    # @generated
    def __init__(self, end_date_time='', start_date_time='', trouble_tickets=[], incident_codes=[], **kw_args):
        """ Initialises a new 'IncidentRecord' instance.
        """
        self.end_date_time = end_date_time
        self.start_date_time = start_date_time
        self.trouble_tickets = trouble_tickets
        self.incident_codes = incident_codes

        super(IncidentRecord, self).__init__(**kw_args)
    # >>> incident_record


class SwitchingSchedule(Document):
    """ Document describing a sequence of steps to perform an item of work, for example to isolate some plant with regard to safety, equipment ratings, and standards of customer service. Note 1: SwitchingSchedule is intended to describe the full operational details for switching for real time operation which includes other operations such as grounding, applying safety documents etc.  Note 2: The association to ErpPerson suits the UK practice of quoting specific names (e.g the crew foreman). The association to Crew is for US practice.
    """
    # Reason for switching. 
    reason = ''

    # Start date and time of switching. 
    start_date_time = ''

    # Completion date and time of switching. 
    end_date_time = ''

    # All Crews executing this SwitchingSchedule.
    crews = []

    schedule_steps = []

    work_task = None

    # <<< switching_schedule
    # @generated
    def __init__(self, reason='', start_date_time='', end_date_time='', crews=[], schedule_steps=[], work_task=None, **kw_args):
        """ Initialises a new 'SwitchingSchedule' instance.
        """
        self.reason = reason
        self.start_date_time = start_date_time
        self.end_date_time = end_date_time
        self.crews = crews
        self.schedule_steps = schedule_steps
        self.work_task = work_task

        super(SwitchingSchedule, self).__init__(**kw_args)
    # >>> switching_schedule


class OutageRecord(Document):
    """ A document describing details of an outage in part of the electrical network, typically produced by a SCADA system following a breaker trip, or within a Trouble Call System by grouping customer calls. This has an associated OutageStep for each supply point. Primary cause of the outage is captured in 'category'. In some countries all outage restoration is performed using a SwitchingSchedule which complements the OutageRecord and records the ErpPersons (crew) and any planned Work. In other systems, it may be acceptable to manage outages including new WorkTasks without switching schedules. Note: The relationship between OutageRecord and ErpPerson and Crew is inherited as each is a type of Document.
    """
    # Overall action taken to resolve outage (details are in 'WorkTasks'). 
    action_taken = ''

    # Value of ErpOrganisation.mode at the time of OutageRecord.startDateTime. 
    mode = ''

    # The damage code relative to the associated PowerSystemResource(s) and/or Asset(s). Examples include broken, burnout, failure, flashed (burned), manually operated, wire down, no damage - rolling blackout, none. 
    damage_code = ''

    # True if planned, false otherwise (for example due to a breaker trip). 
    is_planned = False

    # Date and time restoration was completed for all customers impacted by this outage. 
    end_date_time = ''

    outage_steps = []

    # Multiple outage codes may apply to an outage record.
    outage_codes = []

    outage_report = None

    # <<< outage_record
    # @generated
    def __init__(self, action_taken='', mode='', damage_code='', is_planned=False, end_date_time='', outage_steps=[], outage_codes=[], outage_report=None, **kw_args):
        """ Initialises a new 'OutageRecord' instance.
        """
        self.action_taken = action_taken
        self.mode = mode
        self.damage_code = damage_code
        self.is_planned = is_planned
        self.end_date_time = end_date_time
        self.outage_steps = outage_steps
        self.outage_codes = outage_codes
        self.outage_report = outage_report

        super(OutageRecord, self).__init__(**kw_args)
    # >>> outage_record


class Circuit(IdentifiedObject):
    """ Static collection of conducting equipment originating at a main distribution center and supplying one or more secondary distribution centers, one or more branch-circuit distribution centers, or any combination of these two types of equipment. It is the source to the next normally open point.
    """
    # Rated voltage of the circuit. 
    rated_voltage = ''

    # Kind of this circuit. Values are: "nominally_connected", "electrically_connected", "as_built", "other"
    connection_kind = 'nominally_connected'

    power_system_resources = []

    network_data_sets = []

    circuit_sections = []

    # <<< circuit
    # @generated
    def __init__(self, rated_voltage='', connection_kind='nominally_connected', power_system_resources=[], network_data_sets=[], circuit_sections=[], **kw_args):
        """ Initialises a new 'Circuit' instance.
        """
        self.rated_voltage = rated_voltage
        self.connection_kind = connection_kind
        self.power_system_resources = power_system_resources
        self.network_data_sets = network_data_sets
        self.circuit_sections = circuit_sections

        super(Circuit, self).__init__(**kw_args)
    # >>> circuit


class ChangeSet(IdentifiedObject):
    """ The updates required in a transaction for an existing data set are grouped into a single ChangeSet. In data sets (e.g., NetworkDataSet), each major step in the ChangeSet is described through a separate ChangeItem associated with the data set. Within each data set, each inidividual object change is described with a seperate ChangeItem associated with the object.
    """
    status = None

    land_bases = []

    change_items = []

    documents = []

    network_data_sets = []

    # <<< change_set
    # @generated
    def __init__(self, status=None, land_bases=[], change_items=[], documents=[], network_data_sets=[], **kw_args):
        """ Initialises a new 'ChangeSet' instance.
        """
        self.status = status
        self.land_bases = land_bases
        self.change_items = change_items
        self.documents = documents
        self.network_data_sets = network_data_sets

        super(ChangeSet, self).__init__(**kw_args)
    # >>> change_set


class OrgPsrRole(Role):
    """ Roles played between Organisations and Power System Resources.
    """
    erp_organisation = None

    power_system_resource = None

    # <<< org_psr_role
    # @generated
    def __init__(self, erp_organisation=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'OrgPsrRole' instance.
        """
        self.erp_organisation = erp_organisation
        self.power_system_resource = power_system_resource

        super(OrgPsrRole, self).__init__(**kw_args)
    # >>> org_psr_role


class CallBack(IdentifiedObject):
    """ Information about a planned CallBack or a CallBack that has occurred, from the utility to a customer regarding the status and plans about resolving trouble, performing work, etc.
    """
    # Comments by customer during this call back. 
    comment = ''

    # Additional contact details that are not provided for ErpPerson with ErpTelephoneNumber. 
    contact_detail = ''

    # Advice already given to the customer during this call back. 
    advice = ''

    # Descriptiion of the problem reported during this call back. 
    problem_info = ''

    # (if callback already occured) Date and time when this call back occurred. 
    date_time = ''

    status = None

    appointments = []

    erp_persons = []

    trouble_tickets = []

    # <<< call_back
    # @generated
    def __init__(self, comment='', contact_detail='', advice='', problem_info='', date_time='', status=None, appointments=[], erp_persons=[], trouble_tickets=[], **kw_args):
        """ Initialises a new 'CallBack' instance.
        """
        self.comment = comment
        self.contact_detail = contact_detail
        self.advice = advice
        self.problem_info = problem_info
        self.date_time = date_time
        self.status = status
        self.appointments = appointments
        self.erp_persons = erp_persons
        self.trouble_tickets = trouble_tickets

        super(CallBack, self).__init__(**kw_args)
    # >>> call_back


class SwitchingStep(IdentifiedObject):
    """ A single step within a SwitchingSchedule. Could be a switching operation (applying a network alteration), or issuing a safety document. Note: Inherited attribute IdentifiedObject.name is used to hold the sequence number.
    """
    # The time and date that the Required Control Action was issued. 
    instructed_date_time = ''

    # Desired end state for the associated PowerSystemResource as a result of this schedule step. 
    desired_end_state = ''

    # Information regarding this switching schedule step. 
    text = ''

    # The time and date that the Required Control Action was completed. 
    completed_date_time = ''

    # Control actions required to perform this step. 
    required_control_action = ''

    # Status of this SwitchingStep. Values are: "confirmed", "skipped", "proposed", "instructed", "aborted"
    status_kind = 'confirmed'

    erp_person_role = None

    power_system_resources = []

    switching_schedule = None

    safety_document = None

    # <<< switching_step
    # @generated
    def __init__(self, instructed_date_time='', desired_end_state='', text='', completed_date_time='', required_control_action='', status_kind='confirmed', erp_person_role=None, power_system_resources=[], switching_schedule=None, safety_document=None, **kw_args):
        """ Initialises a new 'SwitchingStep' instance.
        """
        self.instructed_date_time = instructed_date_time
        self.desired_end_state = desired_end_state
        self.text = text
        self.completed_date_time = completed_date_time
        self.required_control_action = required_control_action
        self.status_kind = status_kind
        self.erp_person_role = erp_person_role
        self.power_system_resources = power_system_resources
        self.switching_schedule = switching_schedule
        self.safety_document = safety_document

        super(SwitchingStep, self).__init__(**kw_args)
    # >>> switching_step


class SafetyDocument(Document):
    """ Document used during the course of work on the electrical system for safety purposes. Note: ClearanceTag is a special case of a Safety Document.
    """
    schedule_steps = []

    clearance_tags = []

    power_system_resource = None

    # <<< safety_document
    # @generated
    def __init__(self, schedule_steps=[], clearance_tags=[], power_system_resource=None, **kw_args):
        """ Initialises a new 'SafetyDocument' instance.
        """
        self.schedule_steps = schedule_steps
        self.clearance_tags = clearance_tags
        self.power_system_resource = power_system_resource

        super(SafetyDocument, self).__init__(**kw_args)
    # >>> safety_document


# <<< inf_operations
# @generated
# >>> inf_operations

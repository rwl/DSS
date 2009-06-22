# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.documentation.documentinheritance import Document
from  import 
from  import 
from iec61968.core2.identifiedobjectinheritance import Role
from  import 

# <<< imports
# @generated
# >>> imports

class OutageRecord(Document):
    """  A document describing details of an outage in part of the electrical network. Typically produced  by a SCADA system following a breaker trip or within a Trouble Call System by grouping customer calls. This has an associated OutageStep for each supply point.  In some countries all outage restoration is performed using a SwitchingSchedule which complements the OutageRecord and records the ErpPersons (crew) and any planned Work. In other systems, it may be acceptable to manage outages including new WorkTasks without switching schedules. Note: The relationship between OutageRecord and ErpPerson and Crew is inherited as each is a type of Document.
    """
    #  Type of outage, often express as a cause code.  While many cause codes may be applicable to a given outage, the primary cause in captured here. Values are: "fixed", "flexible", "forced"
    outage_type = 'fixed'

    # Time restoration was completed for all customers impacted by this outage. 
    end_date_time = ''

    #  false if unplanned, for example due to a breaker trip. 
    is_planned = ''

    #  date and time first customer went off-supply 
    start_date_time = ''

    # Current status of the overall outage.  Note that each status change is recorded in an associated Activity Record. 
    current_status = ''

    # Overall action taken to resolve outage.  Reference associated work tasks for details. 
    action_taken = ''

    # The damage code relative to the associated PowerSystemResource(s) and/or Asset(s). Examples include broken, burnout, failure, flashed (burned), manually operated, wire down, no damage - rolling blackout, none.  
    damage_code = ''

    # The value of Organisation.mode at the time of OutageRecord.startDateTime.  Some utilities use text to describe various modes like nominal, storm, emergency, other.  Other utilities use severity ratings where, for example, 0 is a nominal condition and 5 is the most severe condition.  This information is often required for outage reporting purposes. 
    mode = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    outage_report = None

    outage_codes = []

    # <<< outage_record
    # @generated
    def __init__(self, outage_type='fixed', end_date_time='', is_planned='', start_date_time='', current_status='', action_taken='', damage_code='', mode='', status='', status_date_time='', status_remarks='', outage_report=None, outage_codes=[], **kw_args):
        """ Initialises a new 'OutageRecord' instance.
        """
        self.outage_type = outage_type
        self.end_date_time = end_date_time
        self.is_planned = is_planned
        self.start_date_time = start_date_time
        self.current_status = current_status
        self.action_taken = action_taken
        self.damage_code = damage_code
        self.mode = mode
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.outage_report = outage_report
        self.outage_codes = outage_codes

        super(OutageRecord, self).__init__(**kw_args)
    # >>> outage_record


class OutageReport(Document):
    """  A document describing the statistics related to an outage.
    """
    #  Number of customers for the Outage Record 
    total_customers = ''

    #  Total outage time. 
    total_duration = ''

    # The average cusotmer minutes of interruption for this outage. 
    ave_customer_mins_lost = ''

    # Total minutes of customer interruption. 
    total_customer_mins_lost = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of Outage Report.  
    type_out_rep = ''

    outage_record = None

    # <<< outage_report
    # @generated
    def __init__(self, total_customers='', total_duration='', ave_customer_mins_lost='', total_customer_mins_lost='', status='', status_date_time='', status_remarks='', type_out_rep='', outage_record=None, **kw_args):
        """ Initialises a new 'OutageReport' instance.
        """
        self.total_customers = total_customers
        self.total_duration = total_duration
        self.ave_customer_mins_lost = ave_customer_mins_lost
        self.total_customer_mins_lost = total_customer_mins_lost
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_out_rep = type_out_rep
        self.outage_record = outage_record

        super(OutageReport, self).__init__(**kw_args)
    # >>> outage_report


class OutageStep():
    """ Holds an Outage History (start time, end time) for each supply point of an outage record.   The supply point for a given step is the associated PowerSystemResource instance.
    """
    # Time power was lost. 
    off_date_time = ''

    # Time power was restored. 
    on_date_time = ''

 
    job_priority = ''

    #  Number of customers connected to the PowerSystemResource. 
    number_total_customers = ''

    #  Number of customers with high reliability required. 
    number_special_customers = ''

    #  Number of customers with critical needs, e.g.,  with a dialysis machine. 
    number_critical_customers = ''

    #  number of customers phoning in 
    number_callers = ''

    #  Fatalities reported by caller or engineer? True if yes. 
    fatality = ''

    #  Injuries reported by caller or engineer? True if yes. 
    injury = ''

    #  Shocks reported by caller or engineer? Ture if yes 
    shocks = ''

    #  Damage reported by caller or engineer? True if yes. 
    damage = ''

    # Estimated time of restoration. 
    est_restore_date_time = ''

    # The average cusotmer minutes of interruption for this supply point for this outage. 
    average_cust_min_intrpt = ''

    # Total minutes of customer interruption for this supply point for this outage. 
    customer_minutes_lost = ''

    # Current status of this outage step.  
    current_status = ''

    power_system_resource = None

    outage_codes = []

    crews = []

    # <<< outage_step
    # @generated
    def __init__(self, off_date_time='', on_date_time='', job_priority='', number_total_customers='', number_special_customers='', number_critical_customers='', number_callers='', fatality='', injury='', shocks='', damage='', est_restore_date_time='', average_cust_min_intrpt='', customer_minutes_lost='', current_status='', power_system_resource=None, outage_codes=[], crews=[], **kw_args):
        """ Initialises a new 'OutageStep' instance.
        """
        self.off_date_time = off_date_time
        self.on_date_time = on_date_time
        self.job_priority = job_priority
        self.number_total_customers = number_total_customers
        self.number_special_customers = number_special_customers
        self.number_critical_customers = number_critical_customers
        self.number_callers = number_callers
        self.fatality = fatality
        self.injury = injury
        self.shocks = shocks
        self.damage = damage
        self.est_restore_date_time = est_restore_date_time
        self.average_cust_min_intrpt = average_cust_min_intrpt
        self.customer_minutes_lost = customer_minutes_lost
        self.current_status = current_status
        self.power_system_resource = power_system_resource
        self.outage_codes = outage_codes
        self.crews = crews

        super(OutageStep, self).__init__(**kw_args)
    # >>> outage_step


class OutageCode():
    """ Classification of outage types.  Multiple outage codes may apply to a given a given outage or outage step.  The primary overall outage type is recorded in OutageRecord.outageType.  There may be more than one classification per outage step and/or per outage record.  Example codes/subcodes include: weather/ice, weather/lightning, wildlife/squirrel, wildlife/bird, burned/overload, burned/weather, wire down/accident, wire down/tree, wire down/vandalism, etc.  The typical 'outage code' is in the inherited 'name' attribute.  The code is described in the inherited 'description' attribute. 
    """
    # The main code is stored in the inherited .name.  This sub-code provides an additional level of classification detail. 
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


class OutageStepPsrRole(Role):
    """ Roles played between Power System Resources and Outage Steps.  Examples roles include: normal supply, actual supply, interrupting device, restoration device.
    """
    pass
    # <<< outage_step_psr_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'OutageStepPsrRole' instance.
        """

        super(OutageStepPsrRole, self).__init__(**kw_args)
    # >>> outage_step_psr_role


class IncidentRecord(Document):
    """ Each Trouble Ticket is associated with an Incident Record.  If the incident has to do with an outage, the Incident Record will be associated with an Outage Record.
    """
    # Date and time first customer was impacted by the incident. 
    start_date_time = ''

    # Time incident was resolved for all customers impacted by this incident. 
    end_date_time = ''

    #  Type of incident, often express as a cause code.  While many cause codes may be applicable to a given incident, the primary cause in captured here. 
    incident_type = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    incident_codes = []

    trouble_tickets = []

    # <<< incident_record
    # @generated
    def __init__(self, start_date_time='', end_date_time='', incident_type='', status='', status_date_time='', status_remarks='', incident_codes=[], trouble_tickets=[], **kw_args):
        """ Initialises a new 'IncidentRecord' instance.
        """
        self.start_date_time = start_date_time
        self.end_date_time = end_date_time
        self.incident_type = incident_type
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.incident_codes = incident_codes
        self.trouble_tickets = trouble_tickets

        super(IncidentRecord, self).__init__(**kw_args)
    # >>> incident_record


class IncidentCode():
    """ Classification of incident types.  Multiple incident codes may apply to a given a given incident.  The primary overall incident type is recorded in IncidentRecord.incidentType.
    """
    # The main code is stored in the inherited .name.  This sub-code provides an additional level of classification detail. 
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


class OutagesVersion(object):
 
    version = ''

 
    date = ''

    # <<< outages_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'OutagesVersion' instance.
        """
        self.version = version
        self.date = date

        super(OutagesVersion, self).__init__(**kw_args)
    # >>> outages_version


# <<< outages
# @generated
# >>> outages

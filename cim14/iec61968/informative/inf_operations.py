# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document
from cim14.iec61968.informative.inf_common import Role
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import ActivityRecord

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfOperations"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfOperations"

class OperationalRestriction(Document):
    """ A document that can be associated with a device to describe any sort of restrictions compared with the original manufacturer's specification e.g. temporary maximum loadings, maximum switching current, do not operate if bus couplers are open etc etc.  In the UK, for example, if a breaker or switch ever mal-operates, this is reported centrally and utilities use their asset systems to identify all the installed devices of the same manufacturer's type. They then apply operational restrictions in the operational systems to warn operators of potential problems. After appropriate inspection and maintenance, the operational restrictions may be removed.
    """
    # <<< operational_restriction
    # @generated
    def __init__(self, start_date_time='', reason='', end_date_time='', **kw_args):
        """ Initialises a new 'OperationalRestriction' instance.
        """
        # Date and time restriction is applied. 
        self.start_date_time = ''
        # Reason for applying restriction. 
        self.reason = ''
        # Date and time restriction is lifted. 
        self.end_date_time = ''
        

        super(OperationalRestriction, self).__init__(**kw_args)
    # >>> operational_restriction
        


class ErpPersonScheduleStepRole(Role):
    """ Roles played between Persons and Schedule Steps.
    """
    # <<< erp_person_schedule_step_role
    # @generated
    def __init__(self, switching_step=None, erp_person=None, **kw_args):
        """ Initialises a new 'ErpPersonScheduleStepRole' instance.
        """
        
        self._switching_step = None
        self.switching_step = switching_step
        self._erp_person = None
        self.erp_person = erp_person

        super(ErpPersonScheduleStepRole, self).__init__(**kw_args)
    # >>> erp_person_schedule_step_role
        
    # <<< switching_step
    # @generated
    def get_switching_step(self):
        """ 
        """
        return self._switching_step

    def set_switching_step(self, value):
        if self._switching_step is not None:
            self._switching_step._erp_person_role = None
            
        self._switching_step = value
        if self._switching_step is not None:
            self._switching_step._erp_person_role = self
            
    switching_step = property(get_switching_step, set_switching_step)
    # >>> switching_step

    # <<< erp_person
    # @generated
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.switching_step_roles if x != self]
            self._erp_person._switching_step_roles = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            self._erp_person._switching_step_roles.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person



class OutageReport(Document):
    """ Document with statistics of an outage.
    """
    # <<< outage_report
    # @generated
    def __init__(self, total_cml='', average_cml='', customer_count=0, outage_duration='', outage_record=None, outage_history=None, **kw_args):
        """ Initialises a new 'OutageReport' instance.
        """
        # Total Customer Minutes Lost (CML). 
        self.total_cml = ''
        # Average Customer Minutes Lost (CML) for this outage. 
        self.average_cml = ''
        # Total number of outaged customers. 
        self.customer_count = 0
        # Total outage duration. 
        self.outage_duration = ''
        
        self._outage_record = None
        self.outage_record = outage_record
        self._outage_history = None
        self.outage_history = outage_history

        super(OutageReport, self).__init__(**kw_args)
    # >>> outage_report
        
    # <<< outage_record
    # @generated
    def get_outage_record(self):
        """ reference to related document
        """
        return self._outage_record

    def set_outage_record(self, value):
        if self._outage_record is not None:
            self._outage_record._outage_report = None
            
        self._outage_record = value
        if self._outage_record is not None:
            self._outage_record._outage_report = self
            
    outage_record = property(get_outage_record, set_outage_record)
    # >>> outage_record

    # <<< outage_history
    # @generated
    def get_outage_history(self):
        """ OutageHistory of a customer, which may include this OutageReport.
        """
        return self._outage_history

    def set_outage_history(self, value):
        if self._outage_history is not None:
            filtered = [x for x in self.outage_history.outage_reports if x != self]
            self._outage_history._outage_reports = filtered
            
        self._outage_history = value
        if self._outage_history is not None:
            self._outage_history._outage_reports.append(self)

    outage_history = property(get_outage_history, set_outage_history)
    # >>> outage_history



class IncidentCode(IdentifiedObject):
    """ Classification of incident types. Multiple incident codes may apply to a given incident. The primary overall incident cause is recorded in 'IncidentRecord.category', and the main code in 'name'.
    """
    # <<< incident_code
    # @generated
    def __init__(self, sub_code='', incident_records=[], **kw_args):
        """ Initialises a new 'IncidentCode' instance.
        """
        # Additional level of classification detail (as extension to the main code found in 'name'). 
        self.sub_code = ''
        
        self._incident_records = []
        self.incident_records = incident_records

        super(IncidentCode, self).__init__(**kw_args)
    # >>> incident_code
        
    # <<< incident_records
    # @generated
    def get_incident_records(self):
        """ 
        """
        return self._incident_records

    def set_incident_records(self, value):
        for p in self._incident_records:
            filtered = [q for q in p.incident_codes if q != self]
            self._incident_records._incident_codes = filtered
        for r in value:
            if self not in r._incident_codes:
                r._incident_codes.append(self)
        self._incident_records = value
            
    incident_records = property(get_incident_records, set_incident_records)
    
    def add_incident_records(self, *incident_records):
        for obj in incident_records:
            if self not in obj._incident_codes:
                obj._incident_codes.append(self)
            self._incident_records.append(obj)
        
    def remove_incident_records(self, *incident_records):
        for obj in incident_records:
            if self in obj._incident_codes:
                obj._incident_codes.remove(self)
            self._incident_records.remove(obj)
    # >>> incident_records



class ComplianceEvent(ActivityRecord):
    """ Compliance events are used for reporting regulatory or contract compliance issues and/or variances. These might be created as a consequence of local business processes and associated rules. It is anticipated that this class will be customised extensively to meet local implementation needs. Use inherited 'category' to indicate that, for example, expected performance will not be met or reported as mandated.
    """
    # <<< compliance_event
    # @generated
    def __init__(self, deadline='', compliance_type='', **kw_args):
        """ Initialises a new 'ComplianceEvent' instance.
        """
        # The deadline for compliance. 
        self.deadline = ''
        # Type of compliance event indicating, for example, types of regulatory and/or contractual compliance events where expected performance will not be met or reported as mandated. 
        self.compliance_type = ''
        

        super(ComplianceEvent, self).__init__(**kw_args)
    # >>> compliance_event
        


class OutageCode(IdentifiedObject):
    """ Classification of outage types. Multiple outage codes may apply to a given outage or outage step.The primary overall outage type is recorded in OutageRecord.outageType. There may be more than one classification per outage step and/or per outage record. Example codes/subcodes include: weather/ice, weather/lightning, wildlife/squirrel, wildlife/bird, burned/overload, burned/weather, wire down/accident, wire down/tree, wire down/vandalism, etc. The typical 'outage code' is in the inherited 'name' attribute. The code is described in the inherited 'description' attribute.
    """
    # <<< outage_code
    # @generated
    def __init__(self, sub_code='', outage_records=[], outage_steps=[], **kw_args):
        """ Initialises a new 'OutageCode' instance.
        """
        # The main code is stored in the inherited .name. This sub-code provides an additional level of classification detail. 
        self.sub_code = ''
        
        self._outage_records = []
        self.outage_records = outage_records
        self._outage_steps = []
        self.outage_steps = outage_steps

        super(OutageCode, self).__init__(**kw_args)
    # >>> outage_code
        
    # <<< outage_records
    # @generated
    def get_outage_records(self):
        """ 
        """
        return self._outage_records

    def set_outage_records(self, value):
        for p in self._outage_records:
            filtered = [q for q in p.outage_codes if q != self]
            self._outage_records._outage_codes = filtered
        for r in value:
            if self not in r._outage_codes:
                r._outage_codes.append(self)
        self._outage_records = value
            
    outage_records = property(get_outage_records, set_outage_records)
    
    def add_outage_records(self, *outage_records):
        for obj in outage_records:
            if self not in obj._outage_codes:
                obj._outage_codes.append(self)
            self._outage_records.append(obj)
        
    def remove_outage_records(self, *outage_records):
        for obj in outage_records:
            if self in obj._outage_codes:
                obj._outage_codes.remove(self)
            self._outage_records.remove(obj)
    # >>> outage_records

    # <<< outage_steps
    # @generated
    def get_outage_steps(self):
        """ 
        """
        return self._outage_steps

    def set_outage_steps(self, value):
        for p in self._outage_steps:
            filtered = [q for q in p.outage_codes if q != self]
            self._outage_steps._outage_codes = filtered
        for r in value:
            if self not in r._outage_codes:
                r._outage_codes.append(self)
        self._outage_steps = value
            
    outage_steps = property(get_outage_steps, set_outage_steps)
    
    def add_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            if self not in obj._outage_codes:
                obj._outage_codes.append(self)
            self._outage_steps.append(obj)
        
    def remove_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            if self in obj._outage_codes:
                obj._outage_codes.remove(self)
            self._outage_steps.remove(obj)
    # >>> outage_steps



class NetworkDataSet(IdentifiedObject):
    """ Categorized as a type of document, model of a portion of the electrical network that includes a list of the equipment, along with relevant connectivity, electrical characteristics, geographical location, and various parameters associated with the equipment.
    """
    # <<< network_data_set
    # @generated
    def __init__(self, category='', status=None, land_bases=[], power_system_resources=[], circuits=[], documents=[], change_sets=[], circuit_sections=[], change_items=[], **kw_args):
        """ Initialises a new 'NetworkDataSet' instance.
        """
        # Category of network data set. 
        self.category = ''
        
        self.status = status
        self._land_bases = []
        self.land_bases = land_bases
        self._power_system_resources = []
        self.power_system_resources = power_system_resources
        self._circuits = []
        self.circuits = circuits
        self._documents = []
        self.documents = documents
        self._change_sets = []
        self.change_sets = change_sets
        self._circuit_sections = []
        self.circuit_sections = circuit_sections
        self._change_items = []
        self.change_items = change_items

        super(NetworkDataSet, self).__init__(**kw_args)
    # >>> network_data_set
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< land_bases
    # @generated
    def get_land_bases(self):
        """ 
        """
        return self._land_bases

    def set_land_bases(self, value):
        for p in self._land_bases:
            filtered = [q for q in p.network_data_sets if q != self]
            self._land_bases._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._land_bases = value
            
    land_bases = property(get_land_bases, set_land_bases)
    
    def add_land_bases(self, *land_bases):
        for obj in land_bases:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._land_bases.append(obj)
        
    def remove_land_bases(self, *land_bases):
        for obj in land_bases:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._land_bases.remove(obj)
    # >>> land_bases

    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.network_data_sets if q != self]
            self._power_system_resources._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources

    # <<< circuits
    # @generated
    def get_circuits(self):
        """ 
        """
        return self._circuits

    def set_circuits(self, value):
        for p in self._circuits:
            filtered = [q for q in p.network_data_sets if q != self]
            self._circuits._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._circuits = value
            
    circuits = property(get_circuits, set_circuits)
    
    def add_circuits(self, *circuits):
        for obj in circuits:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._circuits.append(obj)
        
    def remove_circuits(self, *circuits):
        for obj in circuits:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._circuits.remove(obj)
    # >>> circuits

    # <<< documents
    # @generated
    def get_documents(self):
        """ 
        """
        return self._documents

    def set_documents(self, value):
        for p in self._documents:
            filtered = [q for q in p.network_data_sets if q != self]
            self._documents._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._documents = value
            
    documents = property(get_documents, set_documents)
    
    def add_documents(self, *documents):
        for obj in documents:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._documents.append(obj)
        
    def remove_documents(self, *documents):
        for obj in documents:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._documents.remove(obj)
    # >>> documents

    # <<< change_sets
    # @generated
    def get_change_sets(self):
        """ 
        """
        return self._change_sets

    def set_change_sets(self, value):
        for p in self._change_sets:
            filtered = [q for q in p.network_data_sets if q != self]
            self._change_sets._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._change_sets = value
            
    change_sets = property(get_change_sets, set_change_sets)
    
    def add_change_sets(self, *change_sets):
        for obj in change_sets:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._change_sets.append(obj)
        
    def remove_change_sets(self, *change_sets):
        for obj in change_sets:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._change_sets.remove(obj)
    # >>> change_sets

    # <<< circuit_sections
    # @generated
    def get_circuit_sections(self):
        """ A NetworkDataSet may contain sections of circuits (vs. whole circuits).
        """
        return self._circuit_sections

    def set_circuit_sections(self, value):
        for p in self._circuit_sections:
            filtered = [q for q in p.network_data_sets if q != self]
            self._circuit_sections._network_data_sets = filtered
        for r in value:
            if self not in r._network_data_sets:
                r._network_data_sets.append(self)
        self._circuit_sections = value
            
    circuit_sections = property(get_circuit_sections, set_circuit_sections)
    
    def add_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self not in obj._network_data_sets:
                obj._network_data_sets.append(self)
            self._circuit_sections.append(obj)
        
    def remove_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self in obj._network_data_sets:
                obj._network_data_sets.remove(self)
            self._circuit_sections.remove(obj)
    # >>> circuit_sections

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x._network_data_set = None
        for y in value:
            y._network_data_set = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._network_data_set = self
            self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._network_data_set = None
            self._change_items.remove(obj)
    # >>> change_items



class LandBase(Document):
    """ Land base data.
    """
    # <<< land_base
    # @generated
    def __init__(self, change_sets=[], network_data_sets=[], **kw_args):
        """ Initialises a new 'LandBase' instance.
        """
        
        self._change_sets = []
        self.change_sets = change_sets
        self._network_data_sets = []
        self.network_data_sets = network_data_sets

        super(LandBase, self).__init__(**kw_args)
    # >>> land_base
        
    # <<< change_sets
    # @generated
    def get_change_sets(self):
        """ 
        """
        return self._change_sets

    def set_change_sets(self, value):
        for p in self._change_sets:
            filtered = [q for q in p.land_bases if q != self]
            self._change_sets._land_bases = filtered
        for r in value:
            if self not in r._land_bases:
                r._land_bases.append(self)
        self._change_sets = value
            
    change_sets = property(get_change_sets, set_change_sets)
    
    def add_change_sets(self, *change_sets):
        for obj in change_sets:
            if self not in obj._land_bases:
                obj._land_bases.append(self)
            self._change_sets.append(obj)
        
    def remove_change_sets(self, *change_sets):
        for obj in change_sets:
            if self in obj._land_bases:
                obj._land_bases.remove(self)
            self._change_sets.remove(obj)
    # >>> change_sets

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.land_bases if q != self]
            self._network_data_sets._land_bases = filtered
        for r in value:
            if self not in r._land_bases:
                r._land_bases.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._land_bases:
                obj._land_bases.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._land_bases:
                obj._land_bases.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets



class PlannedOutage(Document):
    """ Planned outage involves network operations which will affect the supply of power to customers. Note that the list of Power System Resources for the PlannedOutage may be the same or a superset of the ones per OutageStep.
    """
    # <<< planned_outage
    # @generated
    def __init__(self, kind='fixed', outage_schedules=[], customer_datas=[], **kw_args):
        """ Initialises a new 'PlannedOutage' instance.
        """
        # Kind of outage. Values are: "fixed", "forced", "flexible"
        self.kind = 'fixed'
        
        self._outage_schedules = []
        self.outage_schedules = outage_schedules
        self._customer_datas = []
        self.customer_datas = customer_datas

        super(PlannedOutage, self).__init__(**kw_args)
    # >>> planned_outage
        
    # <<< outage_schedules
    # @generated
    def get_outage_schedules(self):
        """ 
        """
        return self._outage_schedules

    def set_outage_schedules(self, value):
        for x in self._outage_schedules:
            x._planned_outage = None
        for y in value:
            y._planned_outage = self
        self._outage_schedules = value
            
    outage_schedules = property(get_outage_schedules, set_outage_schedules)
    
    def add_outage_schedules(self, *outage_schedules):
        for obj in outage_schedules:
            obj._planned_outage = self
            self._outage_schedules.append(obj)
        
    def remove_outage_schedules(self, *outage_schedules):
        for obj in outage_schedules:
            obj._planned_outage = None
            self._outage_schedules.remove(obj)
    # >>> outage_schedules

    # <<< customer_datas
    # @generated
    def get_customer_datas(self):
        """ All customers affected by this work. Derived from WorkOrder.connectedCustomers
        """
        return self._customer_datas

    def set_customer_datas(self, value):
        for x in self._customer_datas:
            x._planned_outage = None
        for y in value:
            y._planned_outage = self
        self._customer_datas = value
            
    customer_datas = property(get_customer_datas, set_customer_datas)
    
    def add_customer_datas(self, *customer_datas):
        for obj in customer_datas:
            obj._planned_outage = self
            self._customer_datas.append(obj)
        
    def remove_customer_datas(self, *customer_datas):
        for obj in customer_datas:
            obj._planned_outage = None
            self._customer_datas.remove(obj)
    # >>> customer_datas



class CircuitSection(IdentifiedObject):
    """ Section of circuit located between two sectionalizing devices. It may contain other circuit sections, for example, a lateral tapped off a primary.
    """
    # <<< circuit_section
    # @generated
    def __init__(self, connection_kind='nominally_connected', power_system_resources=[], member_of_circuit_section=None, linear_conductors=[], contains_circuit_sections=[], network_data_sets=[], circuits=[], **kw_args):
        """ Initialises a new 'CircuitSection' instance.
        """
        # Kind of this circuit section. Values are: "nominally_connected", "electrically_connected", "as_built", "other"
        self.connection_kind = 'nominally_connected'
        
        self._power_system_resources = []
        self.power_system_resources = power_system_resources
        self._member_of_circuit_section = None
        self.member_of_circuit_section = member_of_circuit_section
        self._linear_conductors = []
        self.linear_conductors = linear_conductors
        self._contains_circuit_sections = []
        self.contains_circuit_sections = contains_circuit_sections
        self._network_data_sets = []
        self.network_data_sets = network_data_sets
        self._circuits = []
        self.circuits = circuits

        super(CircuitSection, self).__init__(**kw_args)
    # >>> circuit_section
        
    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.circuit_sections if q != self]
            self._power_system_resources._circuit_sections = filtered
        for r in value:
            if self not in r._circuit_sections:
                r._circuit_sections.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._circuit_sections:
                obj._circuit_sections.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._circuit_sections:
                obj._circuit_sections.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources

    # <<< member_of_circuit_section
    # @generated
    def get_member_of_circuit_section(self):
        """ 
        """
        return self._member_of_circuit_section

    def set_member_of_circuit_section(self, value):
        if self._member_of_circuit_section is not None:
            filtered = [x for x in self.member_of_circuit_section.contains_circuit_sections if x != self]
            self._member_of_circuit_section._contains_circuit_sections = filtered
            
        self._member_of_circuit_section = value
        if self._member_of_circuit_section is not None:
            self._member_of_circuit_section._contains_circuit_sections.append(self)

    member_of_circuit_section = property(get_member_of_circuit_section, set_member_of_circuit_section)
    # >>> member_of_circuit_section

    # <<< linear_conductors
    # @generated
    def get_linear_conductors(self):
        """ 
        """
        return self._linear_conductors

    def set_linear_conductors(self, value):
        for x in self._linear_conductors:
            x._circuit_section = None
        for y in value:
            y._circuit_section = self
        self._linear_conductors = value
            
    linear_conductors = property(get_linear_conductors, set_linear_conductors)
    
    def add_linear_conductors(self, *linear_conductors):
        for obj in linear_conductors:
            obj._circuit_section = self
            self._linear_conductors.append(obj)
        
    def remove_linear_conductors(self, *linear_conductors):
        for obj in linear_conductors:
            obj._circuit_section = None
            self._linear_conductors.remove(obj)
    # >>> linear_conductors

    # <<< contains_circuit_sections
    # @generated
    def get_contains_circuit_sections(self):
        """ 
        """
        return self._contains_circuit_sections

    def set_contains_circuit_sections(self, value):
        for x in self._contains_circuit_sections:
            x._member_of_circuit_section = None
        for y in value:
            y._member_of_circuit_section = self
        self._contains_circuit_sections = value
            
    contains_circuit_sections = property(get_contains_circuit_sections, set_contains_circuit_sections)
    
    def add_contains_circuit_sections(self, *contains_circuit_sections):
        for obj in contains_circuit_sections:
            obj._member_of_circuit_section = self
            self._contains_circuit_sections.append(obj)
        
    def remove_contains_circuit_sections(self, *contains_circuit_sections):
        for obj in contains_circuit_sections:
            obj._member_of_circuit_section = None
            self._contains_circuit_sections.remove(obj)
    # >>> contains_circuit_sections

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.circuit_sections if q != self]
            self._network_data_sets._circuit_sections = filtered
        for r in value:
            if self not in r._circuit_sections:
                r._circuit_sections.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._circuit_sections:
                obj._circuit_sections.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._circuit_sections:
                obj._circuit_sections.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets

    # <<< circuits
    # @generated
    def get_circuits(self):
        """ 
        """
        return self._circuits

    def set_circuits(self, value):
        for p in self._circuits:
            filtered = [q for q in p.circuit_sections if q != self]
            self._circuits._circuit_sections = filtered
        for r in value:
            if self not in r._circuit_sections:
                r._circuit_sections.append(self)
        self._circuits = value
            
    circuits = property(get_circuits, set_circuits)
    
    def add_circuits(self, *circuits):
        for obj in circuits:
            if self not in obj._circuit_sections:
                obj._circuit_sections.append(self)
            self._circuits.append(obj)
        
    def remove_circuits(self, *circuits):
        for obj in circuits:
            if self in obj._circuit_sections:
                obj._circuit_sections.remove(self)
            self._circuits.remove(obj)
    # >>> circuits



class TroubleTicket(Document):
    """ A document used to report electrical trouble. The trouble may either be an outage or non-outage problem, such as power quality. It must always be associated with an Incident Record. Note that a separate Activity Record is created for each call associated with an instance of Trouble Ticket. The time of a call is stored in ActivityRecord.createdOn and comments are recorded in ActivityRecord.remarks.
    """
    # <<< trouble_ticket
    # @generated
    def __init__(self, start_date_time='', first_call_date_time='', inform_after_restored=False, priority='', estimated_restore_date_time='', inform_before_restored=False, advice='', restoration_time='', hazard_code='', call_back=False, reporting_kind='letter', call_backs=[], customer_data=None, incident_record=None, **kw_args):
        """ Initialises a new 'TroubleTicket' instance.
        """
        # Date and time at which this source of trouble started. 
        self.start_date_time = ''
        # Date and time trouble call first received. The date and time of subsequent calls by the same customer for the same trouble are recorded in associated Activity Records. 
        self.first_call_date_time = ''
        # True if person reporting trouble requested a call back to confirm power has been restored. The person and their contact information is maintained in the assoicated Customer informaiton. Call back results are recorded in assoicated 'ActivityRecord.Status.remarks'. 
        self.inform_after_restored = False
        # Priority of trouble call. 
        self.priority = ''
        # Estimated restoration date and time last provided to the customer. 
        self.estimated_restore_date_time = ''
        # True if person reporting trouble requested a call back when sigificant information became available about cause of the outage and the estimated restoration time. The person and their contact information are maintained in the assoicated Customer information. Call back results are recorded in assoicated 'ActivityRecord.Status.remarks'. 
        self.inform_before_restored = False
        # Advice already given to the customer at time when trouble was first reported. 
        self.advice = ''
        # Time of restoration of resolution of trouble. 
        self.restoration_time = ''
        # Code for a reported hazard condition. 
        self.hazard_code = ''
        # True if requested to customer when someone is about to arrive at their premises. 
        self.call_back = False
        # Means the customer used to report trouble (default is 'call'). Values are: "letter", "email", "other", "call"
        self.reporting_kind = 'letter'
        
        self._call_backs = []
        self.call_backs = call_backs
        self._customer_data = None
        self.customer_data = customer_data
        self._incident_record = None
        self.incident_record = incident_record

        super(TroubleTicket, self).__init__(**kw_args)
    # >>> trouble_ticket
        
    # <<< call_backs
    # @generated
    def get_call_backs(self):
        """ 
        """
        return self._call_backs

    def set_call_backs(self, value):
        for p in self._call_backs:
            filtered = [q for q in p.trouble_tickets if q != self]
            self._call_backs._trouble_tickets = filtered
        for r in value:
            if self not in r._trouble_tickets:
                r._trouble_tickets.append(self)
        self._call_backs = value
            
    call_backs = property(get_call_backs, set_call_backs)
    
    def add_call_backs(self, *call_backs):
        for obj in call_backs:
            if self not in obj._trouble_tickets:
                obj._trouble_tickets.append(self)
            self._call_backs.append(obj)
        
    def remove_call_backs(self, *call_backs):
        for obj in call_backs:
            if self in obj._trouble_tickets:
                obj._trouble_tickets.remove(self)
            self._call_backs.remove(obj)
    # >>> call_backs

    # <<< customer_data
    # @generated
    def get_customer_data(self):
        """ 
        """
        return self._customer_data

    def set_customer_data(self, value):
        if self._customer_data is not None:
            filtered = [x for x in self.customer_data.trouble_tickets if x != self]
            self._customer_data._trouble_tickets = filtered
            
        self._customer_data = value
        if self._customer_data is not None:
            self._customer_data._trouble_tickets.append(self)

    customer_data = property(get_customer_data, set_customer_data)
    # >>> customer_data

    # <<< incident_record
    # @generated
    def get_incident_record(self):
        """ 
        """
        return self._incident_record

    def set_incident_record(self, value):
        if self._incident_record is not None:
            filtered = [x for x in self.incident_record.trouble_tickets if x != self]
            self._incident_record._trouble_tickets = filtered
            
        self._incident_record = value
        if self._incident_record is not None:
            self._incident_record._trouble_tickets.append(self)

    incident_record = property(get_incident_record, set_incident_record)
    # >>> incident_record



class OutageNotification(Document):
    """ A document containing information to be sent to customers notifying that an outage will take place. This is used to generate mailing lists for customers.
    """
    # <<< outage_notification
    # @generated
    def __init__(self, reason='', duration='', expected_interruption_count=0, customer_datas=[], **kw_args):
        """ Initialises a new 'OutageNotification' instance.
        """
        # Details of the outage 'reason'. 
        self.reason = ''
        # Likely duration of the interruption(s). 
        self.duration = ''
        # Number of possible interruptions that the customer may expect for this event. 
        self.expected_interruption_count = 0
        
        self._customer_datas = []
        self.customer_datas = customer_datas

        super(OutageNotification, self).__init__(**kw_args)
    # >>> outage_notification
        
    # <<< customer_datas
    # @generated
    def get_customer_datas(self):
        """ 
        """
        return self._customer_datas

    def set_customer_datas(self, value):
        for p in self._customer_datas:
            filtered = [q for q in p.outage_notifications if q != self]
            self._customer_datas._outage_notifications = filtered
        for r in value:
            if self not in r._outage_notifications:
                r._outage_notifications.append(self)
        self._customer_datas = value
            
    customer_datas = property(get_customer_datas, set_customer_datas)
    
    def add_customer_datas(self, *customer_datas):
        for obj in customer_datas:
            if self not in obj._outage_notifications:
                obj._outage_notifications.append(self)
            self._customer_datas.append(obj)
        
    def remove_customer_datas(self, *customer_datas):
        for obj in customer_datas:
            if self in obj._outage_notifications:
                obj._outage_notifications.remove(self)
            self._customer_datas.remove(obj)
    # >>> customer_datas



class OutageStep(IdentifiedObject):
    """ Holds an outage start and end time for each supply point of an outage record. The supply point for a given step is the associated PowerSystemResource instance.
    """
    # <<< outage_step
    # @generated
    def __init__(self, injury=False, average_cml='', special_customer_count=0, total_customer_count=0, job_priority='', estimated_restore_date_time='', fatality=False, damage=False, critical_customer_count=0, shock_reported=False, caller_count=0, total_cml='', status=None, no_power_interval=None, conducting_equipment_roles=[], outage_record=None, outage_codes=[], crews=[], **kw_args):
        """ Initialises a new 'OutageStep' instance.
        """
        # True if injuries reported by caller or engineer. 
        self.injury = False
        # Average Customer Minutes Lost (CML) for this supply point for this outage. 
        self.average_cml = ''
        # Number of customers with high reliability required. 
        self.special_customer_count = 0
        # Number of customers connected to the PowerSystemResource. 
        self.total_customer_count = 0
 
        self.job_priority = ''
        # Estimated time of restoration. 
        self.estimated_restore_date_time = ''
        # True if fatalities reported by caller or engineer. 
        self.fatality = False
        # True if damage reported by caller or engineer. 
        self.damage = False
        # Number of customers with critical needs, e.g., with a dialysis machine. 
        self.critical_customer_count = 0
        # True if shocks reported by caller or engineer. 
        self.shock_reported = False
        # Number of customers phoning in. 
        self.caller_count = 0
        # Total Customer Minutes Lost (CML) for this supply point for this outage. 
        self.total_cml = ''
        
        self.status = status
        self.no_power_interval = no_power_interval
        self._conducting_equipment_roles = []
        self.conducting_equipment_roles = conducting_equipment_roles
        self._outage_record = None
        self.outage_record = outage_record
        self._outage_codes = []
        self.outage_codes = outage_codes
        self._crews = []
        self.crews = crews

        super(OutageStep, self).__init__(**kw_args)
    # >>> outage_step
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< no_power_interval
    # @generated
    # Date and time interval between loss and restoration of power.
    no_power_interval = None
    # >>> no_power_interval

    # <<< conducting_equipment_roles
    # @generated
    def get_conducting_equipment_roles(self):
        """ 
        """
        return self._conducting_equipment_roles

    def set_conducting_equipment_roles(self, value):
        for x in self._conducting_equipment_roles:
            x._outage_step = None
        for y in value:
            y._outage_step = self
        self._conducting_equipment_roles = value
            
    conducting_equipment_roles = property(get_conducting_equipment_roles, set_conducting_equipment_roles)
    
    def add_conducting_equipment_roles(self, *conducting_equipment_roles):
        for obj in conducting_equipment_roles:
            obj._outage_step = self
            self._conducting_equipment_roles.append(obj)
        
    def remove_conducting_equipment_roles(self, *conducting_equipment_roles):
        for obj in conducting_equipment_roles:
            obj._outage_step = None
            self._conducting_equipment_roles.remove(obj)
    # >>> conducting_equipment_roles

    # <<< outage_record
    # @generated
    def get_outage_record(self):
        """ 
        """
        return self._outage_record

    def set_outage_record(self, value):
        if self._outage_record is not None:
            filtered = [x for x in self.outage_record.outage_steps if x != self]
            self._outage_record._outage_steps = filtered
            
        self._outage_record = value
        if self._outage_record is not None:
            self._outage_record._outage_steps.append(self)

    outage_record = property(get_outage_record, set_outage_record)
    # >>> outage_record

    # <<< outage_codes
    # @generated
    def get_outage_codes(self):
        """ Multiple outage codes may apply to an outage step.
        """
        return self._outage_codes

    def set_outage_codes(self, value):
        for p in self._outage_codes:
            filtered = [q for q in p.outage_steps if q != self]
            self._outage_codes._outage_steps = filtered
        for r in value:
            if self not in r._outage_steps:
                r._outage_steps.append(self)
        self._outage_codes = value
            
    outage_codes = property(get_outage_codes, set_outage_codes)
    
    def add_outage_codes(self, *outage_codes):
        for obj in outage_codes:
            if self not in obj._outage_steps:
                obj._outage_steps.append(self)
            self._outage_codes.append(obj)
        
    def remove_outage_codes(self, *outage_codes):
        for obj in outage_codes:
            if self in obj._outage_steps:
                obj._outage_steps.remove(self)
            self._outage_codes.remove(obj)
    # >>> outage_codes

    # <<< crews
    # @generated
    def get_crews(self):
        """ 
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.outage_steps if q != self]
            self._crews._outage_steps = filtered
        for r in value:
            if self not in r._outage_steps:
                r._outage_steps.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._outage_steps:
                obj._outage_steps.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._outage_steps:
                obj._outage_steps.remove(self)
            self._crews.remove(obj)
    # >>> crews



class OutageStepPsrRole(Role):
    """ Roles played between Power System Resources and Outage Steps. Examples roles include: normal supply, actual supply, interrupting device, restoration device.
    """
    # <<< outage_step_psr_role
    # @generated
    def __init__(self, conducting_equipment=None, outage_step=None, **kw_args):
        """ Initialises a new 'OutageStepPsrRole' instance.
        """
        
        self._conducting_equipment = None
        self.conducting_equipment = conducting_equipment
        self._outage_step = None
        self.outage_step = outage_step

        super(OutageStepPsrRole, self).__init__(**kw_args)
    # >>> outage_step_psr_role
        
    # <<< conducting_equipment
    # @generated
    def get_conducting_equipment(self):
        """ 
        """
        return self._conducting_equipment

    def set_conducting_equipment(self, value):
        if self._conducting_equipment is not None:
            filtered = [x for x in self.conducting_equipment.outage_step_roles if x != self]
            self._conducting_equipment._outage_step_roles = filtered
            
        self._conducting_equipment = value
        if self._conducting_equipment is not None:
            self._conducting_equipment._outage_step_roles.append(self)

    conducting_equipment = property(get_conducting_equipment, set_conducting_equipment)
    # >>> conducting_equipment

    # <<< outage_step
    # @generated
    def get_outage_step(self):
        """ 
        """
        return self._outage_step

    def set_outage_step(self, value):
        if self._outage_step is not None:
            filtered = [x for x in self.outage_step.conducting_equipment_roles if x != self]
            self._outage_step._conducting_equipment_roles = filtered
            
        self._outage_step = value
        if self._outage_step is not None:
            self._outage_step._conducting_equipment_roles.append(self)

    outage_step = property(get_outage_step, set_outage_step)
    # >>> outage_step



class IncidentRecord(Document):
    """ Document describing the incident reported in a TroubleTicket. If the incident has to do with an outage, this will be associated with an OutageRecord. Primary cause of the incident is captured in 'category'.
    """
    # <<< incident_record
    # @generated
    def __init__(self, end_date_time='', start_date_time='', trouble_tickets=[], incident_codes=[], **kw_args):
        """ Initialises a new 'IncidentRecord' instance.
        """
        # Date and time incident was resolved for all customers impacted by this incident. 
        self.end_date_time = ''
        # Date and time first customer was impacted by the incident. 
        self.start_date_time = ''
        
        self._trouble_tickets = []
        self.trouble_tickets = trouble_tickets
        self._incident_codes = []
        self.incident_codes = incident_codes

        super(IncidentRecord, self).__init__(**kw_args)
    # >>> incident_record
        
    # <<< trouble_tickets
    # @generated
    def get_trouble_tickets(self):
        """ 
        """
        return self._trouble_tickets

    def set_trouble_tickets(self, value):
        for x in self._trouble_tickets:
            x._incident_record = None
        for y in value:
            y._incident_record = self
        self._trouble_tickets = value
            
    trouble_tickets = property(get_trouble_tickets, set_trouble_tickets)
    
    def add_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            obj._incident_record = self
            self._trouble_tickets.append(obj)
        
    def remove_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            obj._incident_record = None
            self._trouble_tickets.remove(obj)
    # >>> trouble_tickets

    # <<< incident_codes
    # @generated
    def get_incident_codes(self):
        """ 
        """
        return self._incident_codes

    def set_incident_codes(self, value):
        for p in self._incident_codes:
            filtered = [q for q in p.incident_records if q != self]
            self._incident_codes._incident_records = filtered
        for r in value:
            if self not in r._incident_records:
                r._incident_records.append(self)
        self._incident_codes = value
            
    incident_codes = property(get_incident_codes, set_incident_codes)
    
    def add_incident_codes(self, *incident_codes):
        for obj in incident_codes:
            if self not in obj._incident_records:
                obj._incident_records.append(self)
            self._incident_codes.append(obj)
        
    def remove_incident_codes(self, *incident_codes):
        for obj in incident_codes:
            if self in obj._incident_records:
                obj._incident_records.remove(self)
            self._incident_codes.remove(obj)
    # >>> incident_codes



class SwitchingSchedule(Document):
    """ Document describing a sequence of steps to perform an item of work, for example to isolate some plant with regard to safety, equipment ratings, and standards of customer service. Note 1: SwitchingSchedule is intended to describe the full operational details for switching for real time operation which includes other operations such as grounding, applying safety documents etc.  Note 2: The association to ErpPerson suits the UK practice of quoting specific names (e.g the crew foreman). The association to Crew is for US practice.
    """
    # <<< switching_schedule
    # @generated
    def __init__(self, reason='', start_date_time='', end_date_time='', crews=[], schedule_steps=[], work_task=None, **kw_args):
        """ Initialises a new 'SwitchingSchedule' instance.
        """
        # Reason for switching. 
        self.reason = ''
        # Start date and time of switching. 
        self.start_date_time = ''
        # Completion date and time of switching. 
        self.end_date_time = ''
        
        self._crews = []
        self.crews = crews
        self._schedule_steps = []
        self.schedule_steps = schedule_steps
        self._work_task = None
        self.work_task = work_task

        super(SwitchingSchedule, self).__init__(**kw_args)
    # >>> switching_schedule
        
    # <<< crews
    # @generated
    def get_crews(self):
        """ All Crews executing this SwitchingSchedule.
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.switching_schedules if q != self]
            self._crews._switching_schedules = filtered
        for r in value:
            if self not in r._switching_schedules:
                r._switching_schedules.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._switching_schedules:
                obj._switching_schedules.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._switching_schedules:
                obj._switching_schedules.remove(self)
            self._crews.remove(obj)
    # >>> crews

    # <<< schedule_steps
    # @generated
    def get_schedule_steps(self):
        """ 
        """
        return self._schedule_steps

    def set_schedule_steps(self, value):
        for x in self._schedule_steps:
            x._switching_schedule = None
        for y in value:
            y._switching_schedule = self
        self._schedule_steps = value
            
    schedule_steps = property(get_schedule_steps, set_schedule_steps)
    
    def add_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            obj._switching_schedule = self
            self._schedule_steps.append(obj)
        
    def remove_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            obj._switching_schedule = None
            self._schedule_steps.remove(obj)
    # >>> schedule_steps

    # <<< work_task
    # @generated
    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.switching_schedules if x != self]
            self._work_task._switching_schedules = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._switching_schedules.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task



class OutageRecord(Document):
    """ A document describing details of an outage in part of the electrical network, typically produced by a SCADA system following a breaker trip, or within a Trouble Call System by grouping customer calls. This has an associated OutageStep for each supply point. Primary cause of the outage is captured in 'category'. In some countries all outage restoration is performed using a SwitchingSchedule which complements the OutageRecord and records the ErpPersons (crew) and any planned Work. In other systems, it may be acceptable to manage outages including new WorkTasks without switching schedules. Note: The relationship between OutageRecord and ErpPerson and Crew is inherited as each is a type of Document.
    """
    # <<< outage_record
    # @generated
    def __init__(self, action_taken='', mode='', damage_code='', is_planned=False, end_date_time='', outage_steps=[], outage_codes=[], outage_report=None, **kw_args):
        """ Initialises a new 'OutageRecord' instance.
        """
        # Overall action taken to resolve outage (details are in 'WorkTasks'). 
        self.action_taken = ''
        # Value of ErpOrganisation.mode at the time of OutageRecord.startDateTime. 
        self.mode = ''
        # The damage code relative to the associated PowerSystemResource(s) and/or Asset(s). Examples include broken, burnout, failure, flashed (burned), manually operated, wire down, no damage - rolling blackout, none. 
        self.damage_code = ''
        # True if planned, false otherwise (for example due to a breaker trip). 
        self.is_planned = False
        # Date and time restoration was completed for all customers impacted by this outage. 
        self.end_date_time = ''
        
        self._outage_steps = []
        self.outage_steps = outage_steps
        self._outage_codes = []
        self.outage_codes = outage_codes
        self._outage_report = None
        self.outage_report = outage_report

        super(OutageRecord, self).__init__(**kw_args)
    # >>> outage_record
        
    # <<< outage_steps
    # @generated
    def get_outage_steps(self):
        """ 
        """
        return self._outage_steps

    def set_outage_steps(self, value):
        for x in self._outage_steps:
            x._outage_record = None
        for y in value:
            y._outage_record = self
        self._outage_steps = value
            
    outage_steps = property(get_outage_steps, set_outage_steps)
    
    def add_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            obj._outage_record = self
            self._outage_steps.append(obj)
        
    def remove_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            obj._outage_record = None
            self._outage_steps.remove(obj)
    # >>> outage_steps

    # <<< outage_codes
    # @generated
    def get_outage_codes(self):
        """ Multiple outage codes may apply to an outage record.
        """
        return self._outage_codes

    def set_outage_codes(self, value):
        for p in self._outage_codes:
            filtered = [q for q in p.outage_records if q != self]
            self._outage_codes._outage_records = filtered
        for r in value:
            if self not in r._outage_records:
                r._outage_records.append(self)
        self._outage_codes = value
            
    outage_codes = property(get_outage_codes, set_outage_codes)
    
    def add_outage_codes(self, *outage_codes):
        for obj in outage_codes:
            if self not in obj._outage_records:
                obj._outage_records.append(self)
            self._outage_codes.append(obj)
        
    def remove_outage_codes(self, *outage_codes):
        for obj in outage_codes:
            if self in obj._outage_records:
                obj._outage_records.remove(self)
            self._outage_codes.remove(obj)
    # >>> outage_codes

    # <<< outage_report
    # @generated
    def get_outage_report(self):
        """ 
        """
        return self._outage_report

    def set_outage_report(self, value):
        if self._outage_report is not None:
            self._outage_report._outage_record = None
            
        self._outage_report = value
        if self._outage_report is not None:
            self._outage_report._outage_record = self
            
    outage_report = property(get_outage_report, set_outage_report)
    # >>> outage_report



class Circuit(IdentifiedObject):
    """ Static collection of conducting equipment originating at a main distribution center and supplying one or more secondary distribution centers, one or more branch-circuit distribution centers, or any combination of these two types of equipment. It is the source to the next normally open point.
    """
    # <<< circuit
    # @generated
    def __init__(self, rated_voltage='', connection_kind='nominally_connected', power_system_resources=[], network_data_sets=[], circuit_sections=[], **kw_args):
        """ Initialises a new 'Circuit' instance.
        """
        # Rated voltage of the circuit. 
        self.rated_voltage = ''
        # Kind of this circuit. Values are: "nominally_connected", "electrically_connected", "as_built", "other"
        self.connection_kind = 'nominally_connected'
        
        self._power_system_resources = []
        self.power_system_resources = power_system_resources
        self._network_data_sets = []
        self.network_data_sets = network_data_sets
        self._circuit_sections = []
        self.circuit_sections = circuit_sections

        super(Circuit, self).__init__(**kw_args)
    # >>> circuit
        
    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.circuits if q != self]
            self._power_system_resources._circuits = filtered
        for r in value:
            if self not in r._circuits:
                r._circuits.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._circuits:
                obj._circuits.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._circuits:
                obj._circuits.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.circuits if q != self]
            self._network_data_sets._circuits = filtered
        for r in value:
            if self not in r._circuits:
                r._circuits.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._circuits:
                obj._circuits.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._circuits:
                obj._circuits.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets

    # <<< circuit_sections
    # @generated
    def get_circuit_sections(self):
        """ 
        """
        return self._circuit_sections

    def set_circuit_sections(self, value):
        for p in self._circuit_sections:
            filtered = [q for q in p.circuits if q != self]
            self._circuit_sections._circuits = filtered
        for r in value:
            if self not in r._circuits:
                r._circuits.append(self)
        self._circuit_sections = value
            
    circuit_sections = property(get_circuit_sections, set_circuit_sections)
    
    def add_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self not in obj._circuits:
                obj._circuits.append(self)
            self._circuit_sections.append(obj)
        
    def remove_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self in obj._circuits:
                obj._circuits.remove(self)
            self._circuit_sections.remove(obj)
    # >>> circuit_sections



class ChangeSet(IdentifiedObject):
    """ The updates required in a transaction for an existing data set are grouped into a single ChangeSet. In data sets (e.g., NetworkDataSet), each major step in the ChangeSet is described through a separate ChangeItem associated with the data set. Within each data set, each inidividual object change is described with a seperate ChangeItem associated with the object.
    """
    # <<< change_set
    # @generated
    def __init__(self, status=None, land_bases=[], change_items=[], documents=[], network_data_sets=[], **kw_args):
        """ Initialises a new 'ChangeSet' instance.
        """
        
        self.status = status
        self._land_bases = []
        self.land_bases = land_bases
        self._change_items = []
        self.change_items = change_items
        self._documents = []
        self.documents = documents
        self._network_data_sets = []
        self.network_data_sets = network_data_sets

        super(ChangeSet, self).__init__(**kw_args)
    # >>> change_set
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< land_bases
    # @generated
    def get_land_bases(self):
        """ 
        """
        return self._land_bases

    def set_land_bases(self, value):
        for p in self._land_bases:
            filtered = [q for q in p.change_sets if q != self]
            self._land_bases._change_sets = filtered
        for r in value:
            if self not in r._change_sets:
                r._change_sets.append(self)
        self._land_bases = value
            
    land_bases = property(get_land_bases, set_land_bases)
    
    def add_land_bases(self, *land_bases):
        for obj in land_bases:
            if self not in obj._change_sets:
                obj._change_sets.append(self)
            self._land_bases.append(obj)
        
    def remove_land_bases(self, *land_bases):
        for obj in land_bases:
            if self in obj._change_sets:
                obj._change_sets.remove(self)
            self._land_bases.remove(obj)
    # >>> land_bases

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x._change_set = None
        for y in value:
            y._change_set = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._change_set = self
            self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._change_set = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< documents
    # @generated
    def get_documents(self):
        """ 
        """
        return self._documents

    def set_documents(self, value):
        for p in self._documents:
            filtered = [q for q in p.change_sets if q != self]
            self._documents._change_sets = filtered
        for r in value:
            if self not in r._change_sets:
                r._change_sets.append(self)
        self._documents = value
            
    documents = property(get_documents, set_documents)
    
    def add_documents(self, *documents):
        for obj in documents:
            if self not in obj._change_sets:
                obj._change_sets.append(self)
            self._documents.append(obj)
        
    def remove_documents(self, *documents):
        for obj in documents:
            if self in obj._change_sets:
                obj._change_sets.remove(self)
            self._documents.remove(obj)
    # >>> documents

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.change_sets if q != self]
            self._network_data_sets._change_sets = filtered
        for r in value:
            if self not in r._change_sets:
                r._change_sets.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._change_sets:
                obj._change_sets.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._change_sets:
                obj._change_sets.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets



class OrgPsrRole(Role):
    """ Roles played between Organisations and Power System Resources.
    """
    # <<< org_psr_role
    # @generated
    def __init__(self, erp_organisation=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'OrgPsrRole' instance.
        """
        
        self._erp_organisation = None
        self.erp_organisation = erp_organisation
        self._power_system_resource = None
        self.power_system_resource = power_system_resource

        super(OrgPsrRole, self).__init__(**kw_args)
    # >>> org_psr_role
        
    # <<< erp_organisation
    # @generated
    def get_erp_organisation(self):
        """ 
        """
        return self._erp_organisation

    def set_erp_organisation(self, value):
        if self._erp_organisation is not None:
            filtered = [x for x in self.erp_organisation.power_system_resource_roles if x != self]
            self._erp_organisation._power_system_resource_roles = filtered
            
        self._erp_organisation = value
        if self._erp_organisation is not None:
            self._erp_organisation._power_system_resource_roles.append(self)

    erp_organisation = property(get_erp_organisation, set_erp_organisation)
    # >>> erp_organisation

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ 
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.erp_organisation_roles if x != self]
            self._power_system_resource._erp_organisation_roles = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            self._power_system_resource._erp_organisation_roles.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource



class CallBack(IdentifiedObject):
    """ Information about a planned CallBack or a CallBack that has occurred, from the utility to a customer regarding the status and plans about resolving trouble, performing work, etc.
    """
    # <<< call_back
    # @generated
    def __init__(self, comment='', contact_detail='', advice='', problem_info='', date_time='', status=None, appointments=[], erp_persons=[], trouble_tickets=[], **kw_args):
        """ Initialises a new 'CallBack' instance.
        """
        # Comments by customer during this call back. 
        self.comment = ''
        # Additional contact details that are not provided for ErpPerson with ErpTelephoneNumber. 
        self.contact_detail = ''
        # Advice already given to the customer during this call back. 
        self.advice = ''
        # Descriptiion of the problem reported during this call back. 
        self.problem_info = ''
        # (if callback already occured) Date and time when this call back occurred. 
        self.date_time = ''
        
        self.status = status
        self._appointments = []
        self.appointments = appointments
        self._erp_persons = []
        self.erp_persons = erp_persons
        self._trouble_tickets = []
        self.trouble_tickets = trouble_tickets

        super(CallBack, self).__init__(**kw_args)
    # >>> call_back
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< appointments
    # @generated
    def get_appointments(self):
        """ 
        """
        return self._appointments

    def set_appointments(self, value):
        for x in self._appointments:
            x._call_back = None
        for y in value:
            y._call_back = self
        self._appointments = value
            
    appointments = property(get_appointments, set_appointments)
    
    def add_appointments(self, *appointments):
        for obj in appointments:
            obj._call_back = self
            self._appointments.append(obj)
        
    def remove_appointments(self, *appointments):
        for obj in appointments:
            obj._call_back = None
            self._appointments.remove(obj)
    # >>> appointments

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self._erp_persons:
            filtered = [q for q in p.call_backs if q != self]
            self._erp_persons._call_backs = filtered
        for r in value:
            if self not in r._call_backs:
                r._call_backs.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._call_backs:
                obj._call_backs.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._call_backs:
                obj._call_backs.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons

    # <<< trouble_tickets
    # @generated
    def get_trouble_tickets(self):
        """ 
        """
        return self._trouble_tickets

    def set_trouble_tickets(self, value):
        for p in self._trouble_tickets:
            filtered = [q for q in p.call_backs if q != self]
            self._trouble_tickets._call_backs = filtered
        for r in value:
            if self not in r._call_backs:
                r._call_backs.append(self)
        self._trouble_tickets = value
            
    trouble_tickets = property(get_trouble_tickets, set_trouble_tickets)
    
    def add_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            if self not in obj._call_backs:
                obj._call_backs.append(self)
            self._trouble_tickets.append(obj)
        
    def remove_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            if self in obj._call_backs:
                obj._call_backs.remove(self)
            self._trouble_tickets.remove(obj)
    # >>> trouble_tickets



class SwitchingStep(IdentifiedObject):
    """ A single step within a SwitchingSchedule. Could be a switching operation (applying a network alteration), or issuing a safety document. Note: Inherited attribute IdentifiedObject.name is used to hold the sequence number.
    """
    # <<< switching_step
    # @generated
    def __init__(self, instructed_date_time='', desired_end_state='', text='', completed_date_time='', required_control_action='', status_kind='confirmed', erp_person_role=None, power_system_resources=[], switching_schedule=None, safety_document=None, **kw_args):
        """ Initialises a new 'SwitchingStep' instance.
        """
        # The time and date that the Required Control Action was issued. 
        self.instructed_date_time = ''
        # Desired end state for the associated PowerSystemResource as a result of this schedule step. 
        self.desired_end_state = ''
        # Information regarding this switching schedule step. 
        self.text = ''
        # The time and date that the Required Control Action was completed. 
        self.completed_date_time = ''
        # Control actions required to perform this step. 
        self.required_control_action = ''
        # Status of this SwitchingStep. Values are: "confirmed", "skipped", "proposed", "instructed", "aborted"
        self.status_kind = 'confirmed'
        
        self._erp_person_role = None
        self.erp_person_role = erp_person_role
        self._power_system_resources = []
        self.power_system_resources = power_system_resources
        self._switching_schedule = None
        self.switching_schedule = switching_schedule
        self._safety_document = None
        self.safety_document = safety_document

        super(SwitchingStep, self).__init__(**kw_args)
    # >>> switching_step
        
    # <<< erp_person_role
    # @generated
    def get_erp_person_role(self):
        """ 
        """
        return self._erp_person_role

    def set_erp_person_role(self, value):
        if self._erp_person_role is not None:
            self._erp_person_role._switching_step = None
            
        self._erp_person_role = value
        if self._erp_person_role is not None:
            self._erp_person_role._switching_step = self
            
    erp_person_role = property(get_erp_person_role, set_erp_person_role)
    # >>> erp_person_role

    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.schedule_steps if q != self]
            self._power_system_resources._schedule_steps = filtered
        for r in value:
            if self not in r._schedule_steps:
                r._schedule_steps.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._schedule_steps:
                obj._schedule_steps.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._schedule_steps:
                obj._schedule_steps.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources

    # <<< switching_schedule
    # @generated
    def get_switching_schedule(self):
        """ 
        """
        return self._switching_schedule

    def set_switching_schedule(self, value):
        if self._switching_schedule is not None:
            filtered = [x for x in self.switching_schedule.schedule_steps if x != self]
            self._switching_schedule._schedule_steps = filtered
            
        self._switching_schedule = value
        if self._switching_schedule is not None:
            self._switching_schedule._schedule_steps.append(self)

    switching_schedule = property(get_switching_schedule, set_switching_schedule)
    # >>> switching_schedule

    # <<< safety_document
    # @generated
    def get_safety_document(self):
        """ 
        """
        return self._safety_document

    def set_safety_document(self, value):
        if self._safety_document is not None:
            filtered = [x for x in self.safety_document.schedule_steps if x != self]
            self._safety_document._schedule_steps = filtered
            
        self._safety_document = value
        if self._safety_document is not None:
            self._safety_document._schedule_steps.append(self)

    safety_document = property(get_safety_document, set_safety_document)
    # >>> safety_document



class SafetyDocument(Document):
    """ Document used during the course of work on the electrical system for safety purposes. Note: ClearanceTag is a special case of a Safety Document.
    """
    # <<< safety_document
    # @generated
    def __init__(self, schedule_steps=[], clearance_tags=[], power_system_resource=None, **kw_args):
        """ Initialises a new 'SafetyDocument' instance.
        """
        
        self._schedule_steps = []
        self.schedule_steps = schedule_steps
        self._clearance_tags = []
        self.clearance_tags = clearance_tags
        self._power_system_resource = None
        self.power_system_resource = power_system_resource

        super(SafetyDocument, self).__init__(**kw_args)
    # >>> safety_document
        
    # <<< schedule_steps
    # @generated
    def get_schedule_steps(self):
        """ 
        """
        return self._schedule_steps

    def set_schedule_steps(self, value):
        for x in self._schedule_steps:
            x._safety_document = None
        for y in value:
            y._safety_document = self
        self._schedule_steps = value
            
    schedule_steps = property(get_schedule_steps, set_schedule_steps)
    
    def add_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            obj._safety_document = self
            self._schedule_steps.append(obj)
        
    def remove_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            obj._safety_document = None
            self._schedule_steps.remove(obj)
    # >>> schedule_steps

    # <<< clearance_tags
    # @generated
    def get_clearance_tags(self):
        """ 
        """
        return self._clearance_tags

    def set_clearance_tags(self, value):
        for p in self._clearance_tags:
            filtered = [q for q in p.safety_documents if q != self]
            self._clearance_tags._safety_documents = filtered
        for r in value:
            if self not in r._safety_documents:
                r._safety_documents.append(self)
        self._clearance_tags = value
            
    clearance_tags = property(get_clearance_tags, set_clearance_tags)
    
    def add_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            if self not in obj._safety_documents:
                obj._safety_documents.append(self)
            self._clearance_tags.append(obj)
        
    def remove_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            if self in obj._safety_documents:
                obj._safety_documents.remove(self)
            self._clearance_tags.remove(obj)
    # >>> clearance_tags

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ 
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.safety_documents if x != self]
            self._power_system_resource._safety_documents = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            self._power_system_resource._safety_documents.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource



# <<< inf_operations
# @generated
# >>> inf_operations

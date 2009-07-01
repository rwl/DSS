# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.informative.inf_assets import ProcedureDataSet
from cim14.iec61968.informative.inf_common import ScheduledEvent
from cim14.iec61968.common import Location
from cim14.iec61968.common import ActivityRecord

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfWork"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfWork"

class Request(Document):
    """ A request for work, service or project.
    """
    # The corporate code for this request. 
    corporate_code = ''

    # Based on the current 'Status.status', the action that is needed before this Request can transition to the desired state, such as initiating the requested Work. For example, missing or additionally needed information may be required from the requesting organisation before a work Design may be created. 
    action_needed = ''

    # The priority of this request. 
    priority = ''

    def get_erp_quote_line_item(self):
        """ 
        """
        return self._erp_quote_line_item

    def set_erp_quote_line_item(self, value):
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._request = None
            
        self._erp_quote_line_item = value
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._request = self
            
    erp_quote_line_item = property(get_erp_quote_line_item, set_erp_quote_line_item)

    def get_organisation(self):
        """ 
        """
        return self._organisation

    def set_organisation(self, value):
        if self._organisation is not None:
            filtered = [x for x in self.organisation.requests if x != self]
            self._organisation._requests = filtered
            
        self._organisation = value
        if self._organisation is not None:
            self._organisation._requests.append(self)

    organisation = property(get_organisation, set_organisation)

    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x._request = None
        for y in value:
            y._request = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._request = self
            self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._request = None
            self._works.remove(obj)

    projects = []
    
    def add_projects(self, *projects):
        for obj in projects:
	        self._projects.append(obj)
        
    def remove_projects(self, *projects):
        for obj in projects:
	        self._projects.remove(obj)

    # <<< request
    # @generated
    def __init__(self, corporate_code='', action_needed='', priority='', erp_quote_line_item=None, organisation=None, works=[], projects=[], **kw_args):
        """ Initialises a new 'Request' instance.
        """
        self.corporate_code = corporate_code
        self.action_needed = action_needed
        self.priority = priority
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._organisation = None
        self.organisation = organisation
        self._works = []
        self.works = works
        self._projects = []
        self.projects = projects

        super(Request, self).__init__(**kw_args)
    # >>> request


class CostType(IdentifiedObject):
    """ A categorization for resources, often costs, in accounting transactions. Examples include: material components, building in service, coal sales, overhead, etc.
    """
    # True if an amount can be assigned to the resource element (e.g., building in service, transmission plant, software development capital); false otherwise (e.g., internal labor, material components). 
    amount_assignment_flag = False

    # A codified representation of the resource element. 
    code = ''

    # The level of the resource element in the hierarchy of resource elements (recursive relationship). 
    level = ''

    # The stage for which this costType applies: estimated design, estimated actual or actual actual. 
    stage = ''

    status = None

    def get_parent_cost_type(self):
        """ 
        """
        return self._parent_cost_type

    def set_parent_cost_type(self, value):
        if self._parent_cost_type is not None:
            filtered = [x for x in self.parent_cost_type.child_cost_types if x != self]
            self._parent_cost_type._child_cost_types = filtered
            
        self._parent_cost_type = value
        if self._parent_cost_type is not None:
            self._parent_cost_type._child_cost_types.append(self)

    parent_cost_type = property(get_parent_cost_type, set_parent_cost_type)

    def get_child_cost_types(self):
        """ 
        """
        return self._child_cost_types

    def set_child_cost_types(self, value):
        for x in self._child_cost_types:
            x._parent_cost_type = None
        for y in value:
            y._parent_cost_type = self
        self._child_cost_types = value
            
    child_cost_types = property(get_child_cost_types, set_child_cost_types)
    
    def add_child_cost_types(self, *child_cost_types):
        for obj in child_cost_types:
            obj._parent_cost_type = self
            self._child_cost_types.append(obj)
        
    def remove_child_cost_types(self, *child_cost_types):
        for obj in child_cost_types:
            obj._parent_cost_type = None
            self._child_cost_types.remove(obj)

    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x._cost_type = None
        for y in value:
            y._cost_type = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cost_type = self
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cost_type = None
            self._compatible_units.remove(obj)

    erp_journal_entries = []
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
	        self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
	        self._erp_journal_entries.remove(obj)

    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x._cost_type = None
        for y in value:
            y._cost_type = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._cost_type = self
            self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._cost_type = None
            self._work_cost_details.remove(obj)

    # <<< cost_type
    # @generated
    def __init__(self, amount_assignment_flag=False, code='', level='', stage='', status=None, parent_cost_type=None, child_cost_types=[], compatible_units=[], erp_journal_entries=[], work_cost_details=[], **kw_args):
        """ Initialises a new 'CostType' instance.
        """
        self.amount_assignment_flag = amount_assignment_flag
        self.code = code
        self.level = level
        self.stage = stage
        self.status = status
        self._parent_cost_type = None
        self.parent_cost_type = parent_cost_type
        self._child_cost_types = []
        self.child_cost_types = child_cost_types
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._erp_journal_entries = []
        self.erp_journal_entries = erp_journal_entries
        self._work_cost_details = []
        self.work_cost_details = work_cost_details

        super(CostType, self).__init__(**kw_args)
    # >>> cost_type


class Usage(IdentifiedObject):
    """ The way material and assets are used to perform a certain type of work task. The way is described in text in the inheritied description attribute.
    """
    status = None

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.usages if x != self]
            self._work_task._usages = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._usages.append(self)

    work_task = property(get_work_task, set_work_task)

    def get_work_equipment_asset(self):
        """ 
        """
        return self._work_equipment_asset

    def set_work_equipment_asset(self, value):
        if self._work_equipment_asset is not None:
            filtered = [x for x in self.work_equipment_asset.usages if x != self]
            self._work_equipment_asset._usages = filtered
            
        self._work_equipment_asset = value
        if self._work_equipment_asset is not None:
            self._work_equipment_asset._usages.append(self)

    work_equipment_asset = property(get_work_equipment_asset, set_work_equipment_asset)

    def get_material_item(self):
        """ 
        """
        return self._material_item

    def set_material_item(self, value):
        if self._material_item is not None:
            filtered = [x for x in self.material_item.usages if x != self]
            self._material_item._usages = filtered
            
        self._material_item = value
        if self._material_item is not None:
            self._material_item._usages.append(self)

    material_item = property(get_material_item, set_material_item)

    # <<< usage
    # @generated
    def __init__(self, status=None, work_task=None, work_equipment_asset=None, material_item=None, **kw_args):
        """ Initialises a new 'Usage' instance.
        """
        self.status = status
        self._work_task = None
        self.work_task = work_task
        self._work_equipment_asset = None
        self.work_equipment_asset = work_equipment_asset
        self._material_item = None
        self.material_item = material_item

        super(Usage, self).__init__(**kw_args)
    # >>> usage


class QualificationRequirement(IdentifiedObject):
    """ Certain skills are required and must be certified in order for a person (typically a member of a crew) to be qualified to work on types of equipment.
    """
    # Qualification identifier. 
    qualification_id = ''

    culabor_items = []
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
	        self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
	        self._culabor_items.remove(obj)

    skills = []
    
    def add_skills(self, *skills):
        for obj in skills:
	        self._skills.append(obj)
        
    def remove_skills(self, *skills):
        for obj in skills:
	        self._skills.remove(obj)

    specifications = []
    
    def add_specifications(self, *specifications):
        for obj in specifications:
	        self._specifications.append(obj)
        
    def remove_specifications(self, *specifications):
        for obj in specifications:
	        self._specifications.remove(obj)

    work_tasks = []
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.remove(obj)

    # <<< qualification_requirement
    # @generated
    def __init__(self, qualification_id='', culabor_items=[], skills=[], specifications=[], work_tasks=[], **kw_args):
        """ Initialises a new 'QualificationRequirement' instance.
        """
        self.qualification_id = qualification_id
        self._culabor_items = []
        self.culabor_items = culabor_items
        self._skills = []
        self.skills = skills
        self._specifications = []
        self.specifications = specifications
        self._work_tasks = []
        self.work_tasks = work_tasks

        super(QualificationRequirement, self).__init__(**kw_args)
    # >>> qualification_requirement


class DiagnosisDataSet(ProcedureDataSet):
    """ The result of a problem (typically an asset failure) diagnosis.
    """
    # Effect of problem. 
    effect = ''

    # Root origin of problem determined during diagnosis. 
    root_origin = ''

    # Code for diagnosed probem category. 
    final_code = ''

    # Date and time preliminary assessment of problem was performed. 
    preliminary_date = ''

    # Remarks pertaining to root cause findings during problem diagnosis. 
    root_remark = ''

    # Phase(s) diagnosed. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
    phase_code = 'abn'

    # Cause of problem determined during diagnosis. 
    final_cause = ''

    # Root cause of problem determined during diagnosis. 
    root_cause = ''

    # Remarks pertaining to preliminary assessment of problem. 
    preliminary_remark = ''

    # Date and time diagnosis was completed. 
    final_date = ''

    # Origin of problem determined during diagnosis. 
    final_origin = ''

    # Failuer mode, for example: Failure to Insulate; Failure to conduct; Failure to contain oil; Failure to provide ground plane; Other. 
    failure_mode = ''

    # Remarks pertaining to findings during problem diagnosis. 
    final_remark = ''

    # Code for problem category determined during preliminary assessment. 
    preliminary_code = ''

    # <<< diagnosis_data_set
    # @generated
    def __init__(self, effect='', root_origin='', final_code='', preliminary_date='', root_remark='', phase_code='abn', final_cause='', root_cause='', preliminary_remark='', final_date='', final_origin='', failure_mode='', final_remark='', preliminary_code='', **kw_args):
        """ Initialises a new 'DiagnosisDataSet' instance.
        """
        self.effect = effect
        self.root_origin = root_origin
        self.final_code = final_code
        self.preliminary_date = preliminary_date
        self.root_remark = root_remark
        self.phase_code = phase_code
        self.final_cause = final_cause
        self.root_cause = root_cause
        self.preliminary_remark = preliminary_remark
        self.final_date = final_date
        self.final_origin = final_origin
        self.failure_mode = failure_mode
        self.final_remark = final_remark
        self.preliminary_code = preliminary_code

        super(DiagnosisDataSet, self).__init__(**kw_args)
    # >>> diagnosis_data_set


class TypeMaterial(Document):
    """ Documentation for a generic material item that may be used for design, work and other purposes. Any number of MaterialItems manufactured by various vendors may be used to perform this TypeMaterial. Note that class analagous to 'AssetModel' is not used for material items. This is because in some cases, for example, a utility sets up a Master material record for a 3 inch long half inch diameter steel bolt and they do not necessarily care what specific supplier is providing the material item. As different vendors are used to supply the part, the Stock Code of the material item can stay the same. In other cases, each time the vendor changes, a new stock code is set up so they can track material used by vendor. Therefore a Material Item 'Model' is not typically needed.
    """
    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    # The value, unit of measure, and multiplier for the quantity. 
    quantity = ''

    # The estimated unit cost of this type of material, either for a unit cost or cost per unit length. Cost is for material or asset only and does not include labor to install/construct or configure it. 
    est_unit_cost = ''

    # True if item is a stock item (default). 
    stock_item = False

    def get_erp_issue_inventories(self):
        """ 
        """
        return self._erp_issue_inventories

    def set_erp_issue_inventories(self, value):
        for x in self._erp_issue_inventories:
            x._type_material = None
        for y in value:
            y._type_material = self
        self._erp_issue_inventories = value
            
    erp_issue_inventories = property(get_erp_issue_inventories, set_erp_issue_inventories)
    
    def add_erp_issue_inventories(self, *erp_issue_inventories):
        for obj in erp_issue_inventories:
            obj._type_material = self
            self._erp_issue_inventories.append(obj)
        
    def remove_erp_issue_inventories(self, *erp_issue_inventories):
        for obj in erp_issue_inventories:
            obj._type_material = None
            self._erp_issue_inventories.remove(obj)

    def get_erp_req_line_items(self):
        """ 
        """
        return self._erp_req_line_items

    def set_erp_req_line_items(self, value):
        for x in self._erp_req_line_items:
            x._type_material = None
        for y in value:
            y._type_material = self
        self._erp_req_line_items = value
            
    erp_req_line_items = property(get_erp_req_line_items, set_erp_req_line_items)
    
    def add_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._type_material = self
            self._erp_req_line_items.append(obj)
        
    def remove_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._type_material = None
            self._erp_req_line_items.remove(obj)

    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x._type_material = None
        for y in value:
            y._type_material = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._type_material = self
            self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._type_material = None
            self._material_items.remove(obj)

    def get_cumaterial_items(self):
        """ 
        """
        return self._cumaterial_items

    def set_cumaterial_items(self, value):
        for x in self._cumaterial_items:
            x._type_material = None
        for y in value:
            y._type_material = self
        self._cumaterial_items = value
            
    cumaterial_items = property(get_cumaterial_items, set_cumaterial_items)
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            obj._type_material = self
            self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            obj._type_material = None
            self._cumaterial_items.remove(obj)

    # <<< type_material
    # @generated
    def __init__(self, cost_type='', quantity='', est_unit_cost='', stock_item=False, erp_issue_inventories=[], erp_req_line_items=[], material_items=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'TypeMaterial' instance.
        """
        self.cost_type = cost_type
        self.quantity = quantity
        self.est_unit_cost = est_unit_cost
        self.stock_item = stock_item
        self._erp_issue_inventories = []
        self.erp_issue_inventories = erp_issue_inventories
        self._erp_req_line_items = []
        self.erp_req_line_items = erp_req_line_items
        self._material_items = []
        self.material_items = material_items
        self._cumaterial_items = []
        self.cumaterial_items = cumaterial_items

        super(TypeMaterial, self).__init__(**kw_args)
    # >>> type_material


class CULaborCode(IdentifiedObject):
    """ Labor code associated with various compatible unit labor items.
    """
    # Labor code. 
    code = ''

    status = None

    def get_culabor_items(self):
        """ 
        """
        return self._culabor_items

    def set_culabor_items(self, value):
        for x in self._culabor_items:
            x._culabor_code = None
        for y in value:
            y._culabor_code = self
        self._culabor_items = value
            
    culabor_items = property(get_culabor_items, set_culabor_items)
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            obj._culabor_code = self
            self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            obj._culabor_code = None
            self._culabor_items.remove(obj)

    # <<< culabor_code
    # @generated
    def __init__(self, code='', status=None, culabor_items=[], **kw_args):
        """ Initialises a new 'CULaborCode' instance.
        """
        self.code = code
        self.status = status
        self._culabor_items = []
        self.culabor_items = culabor_items

        super(CULaborCode, self).__init__(**kw_args)
    # >>> culabor_code


class EquipmentItem(IdentifiedObject):
    """ An equipment item, such as a vehicle, used for a work order.
    """
    # The cost for vehicle usage. 
    cost = ''

    # Code for type of vehicle. 
    code = ''

    status = None

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.equipment_items if x != self]
            self._work_task._equipment_items = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._equipment_items.append(self)

    work_task = property(get_work_task, set_work_task)

    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            filtered = [x for x in self.work_cost_detail.equipment_items if x != self]
            self._work_cost_detail._equipment_items = filtered
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._equipment_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    # <<< equipment_item
    # @generated
    def __init__(self, cost='', code='', status=None, work_task=None, work_cost_detail=None, **kw_args):
        """ Initialises a new 'EquipmentItem' instance.
        """
        self.cost = cost
        self.code = code
        self.status = status
        self._work_task = None
        self.work_task = work_task
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail

        super(EquipmentItem, self).__init__(**kw_args)
    # >>> equipment_item


class ShiftPattern(IdentifiedObject):
    """ The patterns of shifts worked by people or crews.
    """
    # Number of cycles for a temporary shift. 
    cycle_count = 0

    # Type of assignement intended to be worked on this shift, for example, temporary, standard, etc. 
    assignment_type = ''

    # Date and time interval for which this shift pattern is valid (when it became effective and when it expires).
    validity_interval = None

    status = None

    crews = []
    
    def add_crews(self, *crews):
        for obj in crews:
	        self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
	        self._crews.remove(obj)

    # <<< shift_pattern
    # @generated
    def __init__(self, cycle_count=0, assignment_type='', validity_interval=None, status=None, crews=[], **kw_args):
        """ Initialises a new 'ShiftPattern' instance.
        """
        self.cycle_count = cycle_count
        self.assignment_type = assignment_type
        self.validity_interval = validity_interval
        self.status = status
        self._crews = []
        self.crews = crews

        super(ShiftPattern, self).__init__(**kw_args)
    # >>> shift_pattern


class AccessPermit(Document):
    """ A permit is sometimes needed to provide legal access to land or equipment. For example, local authority permission for road works.
    """
    # Permit identifier. 
    permit_id = ''

    # Permit expiration date. 
    expiration_date = ''

    # Total cost of permit. 
    payment = 0.0

    # Date that permit became official. 
    date_effective = ''

    # Permit application number that is used by municipality, state, province, etc. 
    application_number = ''

    # <<< access_permit
    # @generated
    def __init__(self, permit_id='', expiration_date='', payment=0.0, date_effective='', application_number='', **kw_args):
        """ Initialises a new 'AccessPermit' instance.
        """
        self.permit_id = permit_id
        self.expiration_date = expiration_date
        self.payment = payment
        self.date_effective = date_effective
        self.application_number = application_number

        super(AccessPermit, self).__init__(**kw_args)
    # >>> access_permit


class Appointment(ScheduledEvent):
    """ Meeting time and location.
    """
    # Information about the appointment. 
    remark = ''

    # True if requested to call customer when someone is about to arrive at their premises. 
    call_ahead = False

    # Date and time reserved for appointment.
    meeting_interval = None

    # Address for appointment.
    address = None

    def get_call_back(self):
        """ 
        """
        return self._call_back

    def set_call_back(self, value):
        if self._call_back is not None:
            filtered = [x for x in self.call_back.appointments if x != self]
            self._call_back._appointments = filtered
            
        self._call_back = value
        if self._call_back is not None:
            self._call_back._appointments.append(self)

    call_back = property(get_call_back, set_call_back)

    erp_persons = []
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.remove(obj)

    # <<< appointment
    # @generated
    def __init__(self, remark='', call_ahead=False, meeting_interval=None, address=None, call_back=None, erp_persons=[], **kw_args):
        """ Initialises a new 'Appointment' instance.
        """
        self.remark = remark
        self.call_ahead = call_ahead
        self.meeting_interval = meeting_interval
        self.address = address
        self._call_back = None
        self.call_back = call_back
        self._erp_persons = []
        self.erp_persons = erp_persons

        super(Appointment, self).__init__(**kw_args)
    # >>> appointment


class Project(Document):
    """ A collection of related work. For construction projects and maintenance projects, multiple phases may be performed.
    """
    # Overall project budget. 
    budget = ''

    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x._project = None
        for y in value:
            y._project = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._project = self
            self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._project = None
            self._works.remove(obj)

    def get_business_case(self):
        """ 
        """
        return self._business_case

    def set_business_case(self, value):
        if self._business_case is not None:
            filtered = [x for x in self.business_case.projects if x != self]
            self._business_case._projects = filtered
            
        self._business_case = value
        if self._business_case is not None:
            self._business_case._projects.append(self)

    business_case = property(get_business_case, set_business_case)

    def get_erp_project_accounting(self):
        """ 
        """
        return self._erp_project_accounting

    def set_erp_project_accounting(self, value):
        if self._erp_project_accounting is not None:
            filtered = [x for x in self.erp_project_accounting.projects if x != self]
            self._erp_project_accounting._projects = filtered
            
        self._erp_project_accounting = value
        if self._erp_project_accounting is not None:
            self._erp_project_accounting._projects.append(self)

    erp_project_accounting = property(get_erp_project_accounting, set_erp_project_accounting)

    def get_parent_project(self):
        """ 
        """
        return self._parent_project

    def set_parent_project(self, value):
        if self._parent_project is not None:
            filtered = [x for x in self.parent_project.sub_projects if x != self]
            self._parent_project._sub_projects = filtered
            
        self._parent_project = value
        if self._parent_project is not None:
            self._parent_project._sub_projects.append(self)

    parent_project = property(get_parent_project, set_parent_project)

    def get_sub_projects(self):
        """ 
        """
        return self._sub_projects

    def set_sub_projects(self, value):
        for x in self._sub_projects:
            x._parent_project = None
        for y in value:
            y._parent_project = self
        self._sub_projects = value
            
    sub_projects = property(get_sub_projects, set_sub_projects)
    
    def add_sub_projects(self, *sub_projects):
        for obj in sub_projects:
            obj._parent_project = self
            self._sub_projects.append(obj)
        
    def remove_sub_projects(self, *sub_projects):
        for obj in sub_projects:
            obj._parent_project = None
            self._sub_projects.remove(obj)

    requests = []
    
    def add_requests(self, *requests):
        for obj in requests:
	        self._requests.append(obj)
        
    def remove_requests(self, *requests):
        for obj in requests:
	        self._requests.remove(obj)

    # <<< project
    # @generated
    def __init__(self, budget='', works=[], business_case=None, erp_project_accounting=None, parent_project=None, sub_projects=[], requests=[], **kw_args):
        """ Initialises a new 'Project' instance.
        """
        self.budget = budget
        self._works = []
        self.works = works
        self._business_case = None
        self.business_case = business_case
        self._erp_project_accounting = None
        self.erp_project_accounting = erp_project_accounting
        self._parent_project = None
        self.parent_project = parent_project
        self._sub_projects = []
        self.sub_projects = sub_projects
        self._requests = []
        self.requests = requests

        super(Project, self).__init__(**kw_args)
    # >>> project


class DesignLocationCU(IdentifiedObject):
    """ Compatible unit at a given design location.
    """
    # A code that helps direct accounting (capital, expense, or accounting treatment). 
    cu_account = ''

    # The quantity of the CU being assigned to this location. 
    cu_quantity = ''

    # As the same CU can be used for different purposes and accounting purposes, usage must be specified. Examples include: distribution, transmission, substation. 
    cu_usage = ''

    # True if associated electrical equipment is intended to be energized while work is being performed. 
    energization_flag = False

    # Year when a CU that represents an asset is removed. 
    year_removal = ''

    # A code that instructs the crew what action to perform. Values are: "install", "abandon", "remove", "transfer"
    cu_action = 'install'

    status = None

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    def get_design_location(self):
        """ 
        """
        return self._design_location

    def set_design_location(self, value):
        if self._design_location is not None:
            filtered = [x for x in self.design_location.design_location_cus if x != self]
            self._design_location._design_location_cus = filtered
            
        self._design_location = value
        if self._design_location is not None:
            self._design_location._design_location_cus.append(self)

    design_location = property(get_design_location, set_design_location)

    cugroups = []
    
    def add_cugroups(self, *cugroups):
        for obj in cugroups:
	        self._cugroups.append(obj)
        
    def remove_cugroups(self, *cugroups):
        for obj in cugroups:
	        self._cugroups.remove(obj)

    work_tasks = []
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.remove(obj)

    condition_factors = []
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.remove(obj)

    designs = []
    
    def add_designs(self, *designs):
        for obj in designs:
	        self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
	        self._designs.remove(obj)

    # <<< design_location_cu
    # @generated
    def __init__(self, cu_account='', cu_quantity='', cu_usage='', energization_flag=False, year_removal='', cu_action='install', status=None, compatible_units=[], design_location=None, cugroups=[], work_tasks=[], condition_factors=[], designs=[], **kw_args):
        """ Initialises a new 'DesignLocationCU' instance.
        """
        self.cu_account = cu_account
        self.cu_quantity = cu_quantity
        self.cu_usage = cu_usage
        self.energization_flag = energization_flag
        self.year_removal = year_removal
        self.cu_action = cu_action
        self.status = status
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._design_location = None
        self.design_location = design_location
        self._cugroups = []
        self.cugroups = cugroups
        self._work_tasks = []
        self.work_tasks = work_tasks
        self._condition_factors = []
        self.condition_factors = condition_factors
        self._designs = []
        self.designs = designs

        super(DesignLocationCU, self).__init__(**kw_args)
    # >>> design_location_cu


class Assignment(Document):
    """ An assignment is given to an ErpPerson, Crew, Organisation, Equipment Item, Tool, etc. and may be used to perform Work, WorkTasks, Procedures, etc. TimeSchedules may be set up directly for Assignments or indirectly via the associated WorkTask. Note that these associations are all inherited through the recursive relationship on Document.
    """
    # Date and time assignment became effective. 
    effective_date = ''

    # Date and time assignment expires. 
    expiration_date = ''

    crews = []
    
    def add_crews(self, *crews):
        for obj in crews:
	        self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
	        self._crews.remove(obj)

    # <<< assignment
    # @generated
    def __init__(self, effective_date='', expiration_date='', crews=[], **kw_args):
        """ Initialises a new 'Assignment' instance.
        """
        self.effective_date = effective_date
        self.expiration_date = expiration_date
        self._crews = []
        self.crews = crews

        super(Assignment, self).__init__(**kw_args)
    # >>> assignment


class CUMaterialItem(IdentifiedObject):
    """ Compatible unit of a consumable supply item. For example, nuts, bolts, brackets, glue, etc.
    """
    # Code for material. 
    corporate_code = ''

    # Quantity of the TypeMaterial for this CU, used to determine estimated costs based on a per unit cost or a cost per unit length specified in the TypeMaterial. 
    quantity = ''

    status = None

    def get_type_material(self):
        """ 
        """
        return self._type_material

    def set_type_material(self, value):
        if self._type_material is not None:
            filtered = [x for x in self.type_material.cumaterial_items if x != self]
            self._type_material._cumaterial_items = filtered
            
        self._type_material = value
        if self._type_material is not None:
            self._type_material._cumaterial_items.append(self)

    type_material = property(get_type_material, set_type_material)

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    property_units = []
    
    def add_property_units(self, *property_units):
        for obj in property_units:
	        self._property_units.append(obj)
        
    def remove_property_units(self, *property_units):
        for obj in property_units:
	        self._property_units.remove(obj)

    # <<< cumaterial_item
    # @generated
    def __init__(self, corporate_code='', quantity='', status=None, type_material=None, compatible_units=[], property_units=[], **kw_args):
        """ Initialises a new 'CUMaterialItem' instance.
        """
        self.corporate_code = corporate_code
        self.quantity = quantity
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._property_units = []
        self.property_units = property_units

        super(CUMaterialItem, self).__init__(**kw_args)
    # >>> cumaterial_item


class WorkLocation(Location):
    """ Information about a particular location for various forms of work such as a one call request.
    """
    # The names of streets at the nearest intersection to work area. 
    nearest_intersection = ''

    # Name, identifier, or description of the subdivision, if applicable, in which work is to occur. 
    subdivision = ''

    # Name, identifier, or description of the block, if applicable, in which work is to occur. 
    block = ''

    # Name, identifier, or description of the lot, if applicable, in which work is to occur. 
    lot = ''

    def get_one_call_request(self):
        """ 
        """
        return self._one_call_request

    def set_one_call_request(self, value):
        if self._one_call_request is not None:
            filtered = [x for x in self.one_call_request.work_locations if x != self]
            self._one_call_request._work_locations = filtered
            
        self._one_call_request = value
        if self._one_call_request is not None:
            self._one_call_request._work_locations.append(self)

    one_call_request = property(get_one_call_request, set_one_call_request)

    design_locations = []
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.remove(obj)

    # <<< work_location
    # @generated
    def __init__(self, nearest_intersection='', subdivision='', block='', lot='', one_call_request=None, design_locations=[], **kw_args):
        """ Initialises a new 'WorkLocation' instance.
        """
        self.nearest_intersection = nearest_intersection
        self.subdivision = subdivision
        self.block = block
        self.lot = lot
        self._one_call_request = None
        self.one_call_request = one_call_request
        self._design_locations = []
        self.design_locations = design_locations

        super(WorkLocation, self).__init__(**kw_args)
    # >>> work_location


class PropertyUnit(IdentifiedObject):
    """ Unit of property for reporting purposes.
    """
    # Activity code identifies a specific and distinguishable work action. Values are: "install", "abandon", "remove", "transfer"
    activity_code = 'install'

    # Used for property record accounting. For example, in the USA, this would be a FERC account. 
    property_account = ''

    # A code that identifies appropriate type of property accounts such as distribution, streetlgihts, communications. 
    accounting_usage = ''

    status = None

    work_cost_details = []
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
	        self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
	        self._work_cost_details.remove(obj)

    cumaterial_items = []
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
	        self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
	        self._cumaterial_items.remove(obj)

    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x._property_unit = None
        for y in value:
            y._property_unit = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._property_unit = self
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._property_unit = None
            self._compatible_units.remove(obj)

    # <<< property_unit
    # @generated
    def __init__(self, activity_code='install', property_account='', accounting_usage='', status=None, work_cost_details=[], cumaterial_items=[], compatible_units=[], **kw_args):
        """ Initialises a new 'PropertyUnit' instance.
        """
        self.activity_code = activity_code
        self.property_account = property_account
        self.accounting_usage = accounting_usage
        self.status = status
        self._work_cost_details = []
        self.work_cost_details = work_cost_details
        self._cumaterial_items = []
        self.cumaterial_items = cumaterial_items
        self._compatible_units = []
        self.compatible_units = compatible_units

        super(PropertyUnit, self).__init__(**kw_args)
    # >>> property_unit


class CUContractorItem(IdentifiedObject):
    """ Compatible unit contractor item.
    """
    # Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    # The amount that a given contractor will charge for performing this unit of work. 
    bid_amount = ''

    status = None

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    # <<< cucontractor_item
    # @generated
    def __init__(self, activity_code='', bid_amount='', status=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUContractorItem' instance.
        """
        self.activity_code = activity_code
        self.bid_amount = bid_amount
        self.status = status
        self._compatible_units = []
        self.compatible_units = compatible_units

        super(CUContractorItem, self).__init__(**kw_args)
    # >>> cucontractor_item


class MaintenanceDataSet(ProcedureDataSet):
    """ The result of a maintenance activity, a type of Procedure, for a given attribute of an asset is documentated in an MaintenanceDataSet.
    """
    # Description of the condition of the asset just prior to maintenance being performed. 
    condition_before = ''

    # Code for the type of maintenance performed. 
    maint_code = ''

    # Date and time maintenance procedure was completed. 
    maint_date = ''

    # Condition of asset just following maintenance procedure. 
    condition_after = ''

    # <<< maintenance_data_set
    # @generated
    def __init__(self, condition_before='', maint_code='', maint_date='', condition_after='', **kw_args):
        """ Initialises a new 'MaintenanceDataSet' instance.
        """
        self.condition_before = condition_before
        self.maint_code = maint_code
        self.maint_date = maint_date
        self.condition_after = condition_after

        super(MaintenanceDataSet, self).__init__(**kw_args)
    # >>> maintenance_data_set


class WorkFlowStep(IdentifiedObject):
    """ A pre-defined set of work steps for a given type of work.
    """
    # Used to define dependencies of each work flow step, which is for the instance of WorkTask associated with a given instance of WorkFlow. 
    sequence_number = 0

    status = None

    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x._work_flow_step = None
        for y in value:
            y._work_flow_step = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._work_flow_step = self
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._work_flow_step = None
            self._work_tasks.remove(obj)

    def get_work(self):
        """ 
        """
        return self._work

    def set_work(self, value):
        if self._work is not None:
            filtered = [x for x in self.work.work_flow_steps if x != self]
            self._work._work_flow_steps = filtered
            
        self._work = value
        if self._work is not None:
            self._work._work_flow_steps.append(self)

    work = property(get_work, set_work)

    # <<< work_flow_step
    # @generated
    def __init__(self, sequence_number=0, status=None, work_tasks=[], work=None, **kw_args):
        """ Initialises a new 'WorkFlowStep' instance.
        """
        self.sequence_number = sequence_number
        self.status = status
        self._work_tasks = []
        self.work_tasks = work_tasks
        self._work = None
        self.work = work

        super(WorkFlowStep, self).__init__(**kw_args)
    # >>> work_flow_step


class Capability(IdentifiedObject):
    """ Capabilities of a crew.
    """
    # Capability performance factor. 
    performance_factor = ''

    # Category by utility's work management standards and practices. 
    category = ''

    # Date and time interval for which this capability is valid (when it became effective and when it expires).
    validity_interval = None

    status = None

    def get_crew(self):
        """ 
        """
        return self._crew

    def set_crew(self, value):
        if self._crew is not None:
            filtered = [x for x in self.crew.capabilities if x != self]
            self._crew._capabilities = filtered
            
        self._crew = value
        if self._crew is not None:
            self._crew._capabilities.append(self)

    crew = property(get_crew, set_crew)

    crafts = []
    
    def add_crafts(self, *crafts):
        for obj in crafts:
	        self._crafts.append(obj)
        
    def remove_crafts(self, *crafts):
        for obj in crafts:
	        self._crafts.remove(obj)

    work_tasks = []
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.remove(obj)

    # <<< capability
    # @generated
    def __init__(self, performance_factor='', category='', validity_interval=None, status=None, crew=None, crafts=[], work_tasks=[], **kw_args):
        """ Initialises a new 'Capability' instance.
        """
        self.performance_factor = performance_factor
        self.category = category
        self.validity_interval = validity_interval
        self.status = status
        self._crew = None
        self.crew = crew
        self._crafts = []
        self.crafts = crafts
        self._work_tasks = []
        self.work_tasks = work_tasks

        super(Capability, self).__init__(**kw_args)
    # >>> capability


class InspectionDataSet(ProcedureDataSet):
    """ Documents the result of one inspection, a type of Procedure, for a given attribute of an asset.
    """
    # Date and time this inspections was completed. 
    inspect_date = ''

    # A general description of the conditions of the location where the asset resides. 
    location_condition = ''

    def get_according_to_schedules(self):
        """ 
        """
        return self._according_to_schedules

    def set_according_to_schedules(self, value):
        for x in self._according_to_schedules:
            x._for_inspection_data_set = None
        for y in value:
            y._for_inspection_data_set = self
        self._according_to_schedules = value
            
    according_to_schedules = property(get_according_to_schedules, set_according_to_schedules)
    
    def add_according_to_schedules(self, *according_to_schedules):
        for obj in according_to_schedules:
            obj._for_inspection_data_set = self
            self._according_to_schedules.append(obj)
        
    def remove_according_to_schedules(self, *according_to_schedules):
        for obj in according_to_schedules:
            obj._for_inspection_data_set = None
            self._according_to_schedules.remove(obj)

    # <<< inspection_data_set
    # @generated
    def __init__(self, inspect_date='', location_condition='', according_to_schedules=[], **kw_args):
        """ Initialises a new 'InspectionDataSet' instance.
        """
        self.inspect_date = inspect_date
        self.location_condition = location_condition
        self._according_to_schedules = []
        self.according_to_schedules = according_to_schedules

        super(InspectionDataSet, self).__init__(**kw_args)
    # >>> inspection_data_set


class CULaborItem(IdentifiedObject):
    """ Compatible unit labor item.
    """
    # Estimated time to perform work. 
    labor_duration = ''

    # Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    # The labor rate applied for work. 
    labor_rate = ''

    status = None

    qualification_requirements = []
    
    def add_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
	        self._qualification_requirements.append(obj)
        
    def remove_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
	        self._qualification_requirements.remove(obj)

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    def get_culabor_code(self):
        """ 
        """
        return self._culabor_code

    def set_culabor_code(self, value):
        if self._culabor_code is not None:
            filtered = [x for x in self.culabor_code.culabor_items if x != self]
            self._culabor_code._culabor_items = filtered
            
        self._culabor_code = value
        if self._culabor_code is not None:
            self._culabor_code._culabor_items.append(self)

    culabor_code = property(get_culabor_code, set_culabor_code)

    # <<< culabor_item
    # @generated
    def __init__(self, labor_duration='', activity_code='', labor_rate='', status=None, qualification_requirements=[], compatible_units=[], culabor_code=None, **kw_args):
        """ Initialises a new 'CULaborItem' instance.
        """
        self.labor_duration = labor_duration
        self.activity_code = activity_code
        self.labor_rate = labor_rate
        self.status = status
        self._qualification_requirements = []
        self.qualification_requirements = qualification_requirements
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._culabor_code = None
        self.culabor_code = culabor_code

        super(CULaborItem, self).__init__(**kw_args)
    # >>> culabor_item


class CompatibleUnit(Document):
    """ A pre-planned job model containing labor, material, and accounting requirements for standardized job planning.
    """
    # The quantity, unit of measure, and multiplier at the CU level that applies to the materials. 
    quantity = ''

    # Estimated total cost for perfoming CU. 
    est_cost = ''

    cuwork_equipment_items = []
    
    def add_cuwork_equipment_items(self, *cuwork_equipment_items):
        for obj in cuwork_equipment_items:
	        self._cuwork_equipment_items.append(obj)
        
    def remove_cuwork_equipment_items(self, *cuwork_equipment_items):
        for obj in cuwork_equipment_items:
	        self._cuwork_equipment_items.remove(obj)

    procedures = []
    
    def add_procedures(self, *procedures):
        for obj in procedures:
	        self._procedures.append(obj)
        
    def remove_procedures(self, *procedures):
        for obj in procedures:
	        self._procedures.remove(obj)

    def get_cugroup(self):
        """ 
        """
        return self._cugroup

    def set_cugroup(self, value):
        if self._cugroup is not None:
            filtered = [x for x in self.cugroup.compatible_units if x != self]
            self._cugroup._compatible_units = filtered
            
        self._cugroup = value
        if self._cugroup is not None:
            self._cugroup._compatible_units.append(self)

    cugroup = property(get_cugroup, set_cugroup)

    cuassets = []
    
    def add_cuassets(self, *cuassets):
        for obj in cuassets:
	        self._cuassets.append(obj)
        
    def remove_cuassets(self, *cuassets):
        for obj in cuassets:
	        self._cuassets.remove(obj)

    def get_cost_type(self):
        """ 
        """
        return self._cost_type

    def set_cost_type(self, value):
        if self._cost_type is not None:
            filtered = [x for x in self.cost_type.compatible_units if x != self]
            self._cost_type._compatible_units = filtered
            
        self._cost_type = value
        if self._cost_type is not None:
            self._cost_type._compatible_units.append(self)

    cost_type = property(get_cost_type, set_cost_type)

    cucontractor_items = []
    
    def add_cucontractor_items(self, *cucontractor_items):
        for obj in cucontractor_items:
	        self._cucontractor_items.append(obj)
        
    def remove_cucontractor_items(self, *cucontractor_items):
        for obj in cucontractor_items:
	        self._cucontractor_items.remove(obj)

    culabor_items = []
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
	        self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
	        self._culabor_items.remove(obj)

    def get_cuallowable_action(self):
        """ 
        """
        return self._cuallowable_action

    def set_cuallowable_action(self, value):
        if self._cuallowable_action is not None:
            filtered = [x for x in self.cuallowable_action.compatible_units if x != self]
            self._cuallowable_action._compatible_units = filtered
            
        self._cuallowable_action = value
        if self._cuallowable_action is not None:
            self._cuallowable_action._compatible_units.append(self)

    cuallowable_action = property(get_cuallowable_action, set_cuallowable_action)

    def get_property_unit(self):
        """ 
        """
        return self._property_unit

    def set_property_unit(self, value):
        if self._property_unit is not None:
            filtered = [x for x in self.property_unit.compatible_units if x != self]
            self._property_unit._compatible_units = filtered
            
        self._property_unit = value
        if self._property_unit is not None:
            self._property_unit._compatible_units.append(self)

    property_unit = property(get_property_unit, set_property_unit)

    design_location_cus = []
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.remove(obj)

    cumaterial_items = []
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
	        self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
	        self._cumaterial_items.remove(obj)

    # <<< compatible_unit
    # @generated
    def __init__(self, quantity='', est_cost='', cuwork_equipment_items=[], procedures=[], cugroup=None, cuassets=[], cost_type=None, cucontractor_items=[], culabor_items=[], cuallowable_action=None, property_unit=None, design_location_cus=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'CompatibleUnit' instance.
        """
        self.quantity = quantity
        self.est_cost = est_cost
        self._cuwork_equipment_items = []
        self.cuwork_equipment_items = cuwork_equipment_items
        self._procedures = []
        self.procedures = procedures
        self._cugroup = None
        self.cugroup = cugroup
        self._cuassets = []
        self.cuassets = cuassets
        self._cost_type = None
        self.cost_type = cost_type
        self._cucontractor_items = []
        self.cucontractor_items = cucontractor_items
        self._culabor_items = []
        self.culabor_items = culabor_items
        self._cuallowable_action = None
        self.cuallowable_action = cuallowable_action
        self._property_unit = None
        self.property_unit = property_unit
        self._design_location_cus = []
        self.design_location_cus = design_location_cus
        self._cumaterial_items = []
        self.cumaterial_items = cumaterial_items

        super(CompatibleUnit, self).__init__(**kw_args)
    # >>> compatible_unit


class MaterialItem(IdentifiedObject):
    """ The physical consumable supply used for work and other purposes. It includes items such as nuts, bolts, brackets, glue, etc.
    """
    # The quantity of material used. 
    quantity = ''

    # External reference identifier for this actual material item such as a purchase order number, a serial number, etc. 
    external_ref_id = ''

    # Description of the cost. 
    cost_description = ''

    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    # Code for material. 
    material_code = ''

    # The account to which this actual material item is charged. 
    account = ''

    # The actual cost of this particular material in this particular quantity. 
    actual_cost = ''

    status = None

    def get_type_material(self):
        """ 
        """
        return self._type_material

    def set_type_material(self, value):
        if self._type_material is not None:
            filtered = [x for x in self.type_material.material_items if x != self]
            self._type_material._material_items = filtered
            
        self._type_material = value
        if self._type_material is not None:
            self._type_material._material_items.append(self)

    type_material = property(get_type_material, set_type_material)

    def get_erp_inventory_counts(self):
        """ 
        """
        return self._erp_inventory_counts

    def set_erp_inventory_counts(self, value):
        for x in self._erp_inventory_counts:
            x._material_item = None
        for y in value:
            y._material_item = self
        self._erp_inventory_counts = value
            
    erp_inventory_counts = property(get_erp_inventory_counts, set_erp_inventory_counts)
    
    def add_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._material_item = self
            self._erp_inventory_counts.append(obj)
        
    def remove_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._material_item = None
            self._erp_inventory_counts.remove(obj)

    def get_usages(self):
        """ 
        """
        return self._usages

    def set_usages(self, value):
        for x in self._usages:
            x._material_item = None
        for y in value:
            y._material_item = self
        self._usages = value
            
    usages = property(get_usages, set_usages)
    
    def add_usages(self, *usages):
        for obj in usages:
            obj._material_item = self
            self._usages.append(obj)
        
    def remove_usages(self, *usages):
        for obj in usages:
            obj._material_item = None
            self._usages.remove(obj)

    def get_design_location(self):
        """ 
        """
        return self._design_location

    def set_design_location(self, value):
        if self._design_location is not None:
            filtered = [x for x in self.design_location.material_items if x != self]
            self._design_location._material_items = filtered
            
        self._design_location = value
        if self._design_location is not None:
            self._design_location._material_items.append(self)

    design_location = property(get_design_location, set_design_location)

    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            filtered = [x for x in self.work_cost_detail.material_items if x != self]
            self._work_cost_detail._material_items = filtered
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._material_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    erp_rec_delv_line_items = []
    
    def add_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
	        self._erp_rec_delv_line_items.append(obj)
        
    def remove_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
	        self._erp_rec_delv_line_items.remove(obj)

    def get_erp_poline_items(self):
        """ 
        """
        return self._erp_poline_items

    def set_erp_poline_items(self, value):
        for x in self._erp_poline_items:
            x._material_item = None
        for y in value:
            y._material_item = self
        self._erp_poline_items = value
            
    erp_poline_items = property(get_erp_poline_items, set_erp_poline_items)
    
    def add_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._material_item = self
            self._erp_poline_items.append(obj)
        
    def remove_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._material_item = None
            self._erp_poline_items.remove(obj)

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.material_items if x != self]
            self._work_task._material_items = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._material_items.append(self)

    work_task = property(get_work_task, set_work_task)

    # <<< material_item
    # @generated
    def __init__(self, quantity='', external_ref_id='', cost_description='', cost_type='', material_code='', account='', actual_cost='', status=None, type_material=None, erp_inventory_counts=[], usages=[], design_location=None, work_cost_detail=None, erp_rec_delv_line_items=[], erp_poline_items=[], work_task=None, **kw_args):
        """ Initialises a new 'MaterialItem' instance.
        """
        self.quantity = quantity
        self.external_ref_id = external_ref_id
        self.cost_description = cost_description
        self.cost_type = cost_type
        self.material_code = material_code
        self.account = account
        self.actual_cost = actual_cost
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._erp_inventory_counts = []
        self.erp_inventory_counts = erp_inventory_counts
        self._usages = []
        self.usages = usages
        self._design_location = None
        self.design_location = design_location
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._erp_rec_delv_line_items = []
        self.erp_rec_delv_line_items = erp_rec_delv_line_items
        self._erp_poline_items = []
        self.erp_poline_items = erp_poline_items
        self._work_task = None
        self.work_task = work_task

        super(MaterialItem, self).__init__(**kw_args)
    # >>> material_item


class OneCallRequest(Document):
    """ A request for other utilities to mark their underground facilities prior to commencement of construction and/or maintenance.
    """
    # True if explosives have been or are planned to be used. 
    explosives_used = False

    # True if work location has been marked, for example for a dig area. 
    marked_indicator = False

    # Instructions for marking a dig area, if applicable. 
    marking_instruction = ''

    def get_work_locations(self):
        """ 
        """
        return self._work_locations

    def set_work_locations(self, value):
        for x in self._work_locations:
            x._one_call_request = None
        for y in value:
            y._one_call_request = self
        self._work_locations = value
            
    work_locations = property(get_work_locations, set_work_locations)
    
    def add_work_locations(self, *work_locations):
        for obj in work_locations:
            obj._one_call_request = self
            self._work_locations.append(obj)
        
    def remove_work_locations(self, *work_locations):
        for obj in work_locations:
            obj._one_call_request = None
            self._work_locations.remove(obj)

    # <<< one_call_request
    # @generated
    def __init__(self, explosives_used=False, marked_indicator=False, marking_instruction='', work_locations=[], **kw_args):
        """ Initialises a new 'OneCallRequest' instance.
        """
        self.explosives_used = explosives_used
        self.marked_indicator = marked_indicator
        self.marking_instruction = marking_instruction
        self._work_locations = []
        self.work_locations = work_locations

        super(OneCallRequest, self).__init__(**kw_args)
    # >>> one_call_request


class WorkCostSummary(Document):
    """ A roll up by cost category for the entire cost of a work order. For example, total labor.
    """
    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            self._work_cost_detail._work_cost_summary = None
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._work_cost_summary = self
            
    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    # <<< work_cost_summary
    # @generated
    def __init__(self, work_cost_detail=None, **kw_args):
        """ Initialises a new 'WorkCostSummary' instance.
        """
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail

        super(WorkCostSummary, self).__init__(**kw_args)
    # >>> work_cost_summary


class WorkStatusEntry(ActivityRecord):
    """ A type of ActivityRecord that records information about the status of an item, such as a Work or WorkTask, at a point in time.
    """
    # Estimated percentage of completion of this individual work task or overall work order. 
    percent_complete = ''

    # <<< work_status_entry
    # @generated
    def __init__(self, percent_complete='', **kw_args):
        """ Initialises a new 'WorkStatusEntry' instance.
        """
        self.percent_complete = percent_complete

        super(WorkStatusEntry, self).__init__(**kw_args)
    # >>> work_status_entry


class MiscCostItem(IdentifiedObject):
    """ Various cost items that are not associated with compatible units. Examples include rental equipment, labor, materials, contractor costs, permits - anything not covered in a CU.
    """
    # This drives the accounting treatment for this misc. item. 
    account = ''

    # External Reference ID (e.g. PO#, Serial #) 
    external_ref_id = ''

    # The cost per unit for this misc. item. 
    cost_per_unit = ''

    # The cost category for accounting, such as material, labor, vehicle, contractor, equipment, overhead. 
    cost_type = ''

    # The quantity of the misc. item being assigned to this location. 
    quantity = ''

    status = None

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.misc_cost_items if x != self]
            self._work_task._misc_cost_items = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._misc_cost_items.append(self)

    work_task = property(get_work_task, set_work_task)

    def get_design_location(self):
        """ 
        """
        return self._design_location

    def set_design_location(self, value):
        if self._design_location is not None:
            filtered = [x for x in self.design_location.misc_cost_items if x != self]
            self._design_location._misc_cost_items = filtered
            
        self._design_location = value
        if self._design_location is not None:
            self._design_location._misc_cost_items.append(self)

    design_location = property(get_design_location, set_design_location)

    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            filtered = [x for x in self.work_cost_detail.misc_cost_items if x != self]
            self._work_cost_detail._misc_cost_items = filtered
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._misc_cost_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    # <<< misc_cost_item
    # @generated
    def __init__(self, account='', external_ref_id='', cost_per_unit='', cost_type='', quantity='', status=None, work_task=None, design_location=None, work_cost_detail=None, **kw_args):
        """ Initialises a new 'MiscCostItem' instance.
        """
        self.account = account
        self.external_ref_id = external_ref_id
        self.cost_per_unit = cost_per_unit
        self.cost_type = cost_type
        self.quantity = quantity
        self.status = status
        self._work_task = None
        self.work_task = work_task
        self._design_location = None
        self.design_location = design_location
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail

        super(MiscCostItem, self).__init__(**kw_args)
    # >>> misc_cost_item


class Crew(IdentifiedObject):
    """ A crew is a group of people (ErpPersons) with specific skills, tools, and vehicles.
    """
    # Category by utility's work management standards and practices. 
    category = ''

    work_tasks = []
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
	        self._work_tasks.remove(obj)

    def get_vehicles(self):
        """ 
        """
        return self._vehicles

    def set_vehicles(self, value):
        for x in self._vehicles:
            x._crew = None
        for y in value:
            y._crew = self
        self._vehicles = value
            
    vehicles = property(get_vehicles, set_vehicles)
    
    def add_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._crew = self
            self._vehicles.append(obj)
        
    def remove_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._crew = None
            self._vehicles.remove(obj)

    def get_capabilities(self):
        """ 
        """
        return self._capabilities

    def set_capabilities(self, value):
        for x in self._capabilities:
            x._crew = None
        for y in value:
            y._crew = self
        self._capabilities = value
            
    capabilities = property(get_capabilities, set_capabilities)
    
    def add_capabilities(self, *capabilities):
        for obj in capabilities:
            obj._crew = self
            self._capabilities.append(obj)
        
    def remove_capabilities(self, *capabilities):
        for obj in capabilities:
            obj._crew = None
            self._capabilities.remove(obj)

    def get_route(self):
        """ 
        """
        return self._route

    def set_route(self, value):
        if self._route is not None:
            filtered = [x for x in self.route.crews if x != self]
            self._route._crews = filtered
            
        self._route = value
        if self._route is not None:
            self._route._crews.append(self)

    route = property(get_route, set_route)

    def get_work_equipment_assets(self):
        """ 
        """
        return self._work_equipment_assets

    def set_work_equipment_assets(self, value):
        for x in self._work_equipment_assets:
            x._crew = None
        for y in value:
            y._crew = self
        self._work_equipment_assets = value
            
    work_equipment_assets = property(get_work_equipment_assets, set_work_equipment_assets)
    
    def add_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._crew = self
            self._work_equipment_assets.append(obj)
        
    def remove_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._crew = None
            self._work_equipment_assets.remove(obj)

    shift_patterns = []
    
    def add_shift_patterns(self, *shift_patterns):
        for obj in shift_patterns:
	        self._shift_patterns.append(obj)
        
    def remove_shift_patterns(self, *shift_patterns):
        for obj in shift_patterns:
	        self._shift_patterns.remove(obj)

    switching_schedules = []
    
    def add_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
	        self._switching_schedules.append(obj)
        
    def remove_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
	        self._switching_schedules.remove(obj)

    def get_tools(self):
        """ 
        """
        return self._tools

    def set_tools(self, value):
        for x in self._tools:
            x._crew = None
        for y in value:
            y._crew = self
        self._tools = value
            
    tools = property(get_tools, set_tools)
    
    def add_tools(self, *tools):
        for obj in tools:
            obj._crew = self
            self._tools.append(obj)
        
    def remove_tools(self, *tools):
        for obj in tools:
            obj._crew = None
            self._tools.remove(obj)

    outage_steps = []
    
    def add_outage_steps(self, *outage_steps):
        for obj in outage_steps:
	        self._outage_steps.append(obj)
        
    def remove_outage_steps(self, *outage_steps):
        for obj in outage_steps:
	        self._outage_steps.remove(obj)

    locations = []
    
    def add_locations(self, *locations):
        for obj in locations:
	        self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
	        self._locations.remove(obj)

    crew_members = []
    
    def add_crew_members(self, *crew_members):
        for obj in crew_members:
	        self._crew_members.append(obj)
        
    def remove_crew_members(self, *crew_members):
        for obj in crew_members:
	        self._crew_members.remove(obj)

    organisations = []
    
    def add_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.remove(obj)

    assignments = []
    
    def add_assignments(self, *assignments):
        for obj in assignments:
	        self._assignments.append(obj)
        
    def remove_assignments(self, *assignments):
        for obj in assignments:
	        self._assignments.remove(obj)

    # <<< crew
    # @generated
    def __init__(self, category='', work_tasks=[], vehicles=[], capabilities=[], route=None, work_equipment_assets=[], shift_patterns=[], switching_schedules=[], tools=[], outage_steps=[], locations=[], crew_members=[], organisations=[], assignments=[], **kw_args):
        """ Initialises a new 'Crew' instance.
        """
        self.category = category
        self._work_tasks = []
        self.work_tasks = work_tasks
        self._vehicles = []
        self.vehicles = vehicles
        self._capabilities = []
        self.capabilities = capabilities
        self._route = None
        self.route = route
        self._work_equipment_assets = []
        self.work_equipment_assets = work_equipment_assets
        self._shift_patterns = []
        self.shift_patterns = shift_patterns
        self._switching_schedules = []
        self.switching_schedules = switching_schedules
        self._tools = []
        self.tools = tools
        self._outage_steps = []
        self.outage_steps = outage_steps
        self._locations = []
        self.locations = locations
        self._crew_members = []
        self.crew_members = crew_members
        self._organisations = []
        self.organisations = organisations
        self._assignments = []
        self.assignments = assignments

        super(Crew, self).__init__(**kw_args)
    # >>> crew


class WorkCostDetail(Document):
    """ A collection of all of the individual cost items collected from multiple sources.
    """
    # True if 'amount' attribute is a debit, false if it is a credit. 
    debit_flag = False

    # The type of cost. 
    type_work_cost = ''

    # Amount in designated currency for work, either a total or an individual element. As defined in the attribute 'type,' multiple instances are applicable to each work for: planned cost, actual cost, authorized cost, budgeted cost, forecasted cost, other. 
    amount = ''

    # Date that amount is posted to the work. 
    transaction_date = ''

    def get_erp_project_accounting(self):
        """ 
        """
        return self._erp_project_accounting

    def set_erp_project_accounting(self, value):
        if self._erp_project_accounting is not None:
            filtered = [x for x in self.erp_project_accounting.work_cost_details if x != self]
            self._erp_project_accounting._work_cost_details = filtered
            
        self._erp_project_accounting = value
        if self._erp_project_accounting is not None:
            self._erp_project_accounting._work_cost_details.append(self)

    erp_project_accounting = property(get_erp_project_accounting, set_erp_project_accounting)

    def get_design(self):
        """ 
        """
        return self._design

    def set_design(self, value):
        if self._design is not None:
            filtered = [x for x in self.design.work_cost_details if x != self]
            self._design._work_cost_details = filtered
            
        self._design = value
        if self._design is not None:
            self._design._work_cost_details.append(self)

    design = property(get_design, set_design)

    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x._work_cost_detail = None
        for y in value:
            y._work_cost_detail = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._work_cost_detail = self
            self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._work_cost_detail = None
            self._material_items.remove(obj)

    def get_work_cost_summary(self):
        """ 
        """
        return self._work_cost_summary

    def set_work_cost_summary(self, value):
        if self._work_cost_summary is not None:
            self._work_cost_summary._work_cost_detail = None
            
        self._work_cost_summary = value
        if self._work_cost_summary is not None:
            self._work_cost_summary._work_cost_detail = self
            
    work_cost_summary = property(get_work_cost_summary, set_work_cost_summary)

    def get_overhead_cost(self):
        """ 
        """
        return self._overhead_cost

    def set_overhead_cost(self, value):
        if self._overhead_cost is not None:
            filtered = [x for x in self.overhead_cost.work_cost_details if x != self]
            self._overhead_cost._work_cost_details = filtered
            
        self._overhead_cost = value
        if self._overhead_cost is not None:
            self._overhead_cost._work_cost_details.append(self)

    overhead_cost = property(get_overhead_cost, set_overhead_cost)

    def get_labor_items(self):
        """ 
        """
        return self._labor_items

    def set_labor_items(self, value):
        for x in self._labor_items:
            x._work_cost_detail = None
        for y in value:
            y._work_cost_detail = self
        self._labor_items = value
            
    labor_items = property(get_labor_items, set_labor_items)
    
    def add_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_cost_detail = self
            self._labor_items.append(obj)
        
    def remove_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_cost_detail = None
            self._labor_items.remove(obj)

    works = []
    
    def add_works(self, *works):
        for obj in works:
	        self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
	        self._works.remove(obj)

    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x._work_cost_detail = None
        for y in value:
            y._work_cost_detail = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_cost_detail = self
            self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_cost_detail = None
            self._misc_cost_items.remove(obj)

    def get_equipment_items(self):
        """ 
        """
        return self._equipment_items

    def set_equipment_items(self, value):
        for x in self._equipment_items:
            x._work_cost_detail = None
        for y in value:
            y._work_cost_detail = self
        self._equipment_items = value
            
    equipment_items = property(get_equipment_items, set_equipment_items)
    
    def add_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_cost_detail = self
            self._equipment_items.append(obj)
        
    def remove_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_cost_detail = None
            self._equipment_items.remove(obj)

    def get_cost_type(self):
        """ 
        """
        return self._cost_type

    def set_cost_type(self, value):
        if self._cost_type is not None:
            filtered = [x for x in self.cost_type.work_cost_details if x != self]
            self._cost_type._work_cost_details = filtered
            
        self._cost_type = value
        if self._cost_type is not None:
            self._cost_type._work_cost_details.append(self)

    cost_type = property(get_cost_type, set_cost_type)

    def get_contractor_items(self):
        """ 
        """
        return self._contractor_items

    def set_contractor_items(self, value):
        for x in self._contractor_items:
            x._work_cost_detail = None
        for y in value:
            y._work_cost_detail = self
        self._contractor_items = value
            
    contractor_items = property(get_contractor_items, set_contractor_items)
    
    def add_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_cost_detail = self
            self._contractor_items.append(obj)
        
    def remove_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_cost_detail = None
            self._contractor_items.remove(obj)

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.work_cost_details if x != self]
            self._work_task._work_cost_details = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._work_cost_details.append(self)

    work_task = property(get_work_task, set_work_task)

    property_units = []
    
    def add_property_units(self, *property_units):
        for obj in property_units:
	        self._property_units.append(obj)
        
    def remove_property_units(self, *property_units):
        for obj in property_units:
	        self._property_units.remove(obj)

    # <<< work_cost_detail
    # @generated
    def __init__(self, debit_flag=False, type_work_cost='', amount='', transaction_date='', erp_project_accounting=None, design=None, material_items=[], work_cost_summary=None, overhead_cost=None, labor_items=[], works=[], misc_cost_items=[], equipment_items=[], cost_type=None, contractor_items=[], work_task=None, property_units=[], **kw_args):
        """ Initialises a new 'WorkCostDetail' instance.
        """
        self.debit_flag = debit_flag
        self.type_work_cost = type_work_cost
        self.amount = amount
        self.transaction_date = transaction_date
        self._erp_project_accounting = None
        self.erp_project_accounting = erp_project_accounting
        self._design = None
        self.design = design
        self._material_items = []
        self.material_items = material_items
        self._work_cost_summary = None
        self.work_cost_summary = work_cost_summary
        self._overhead_cost = None
        self.overhead_cost = overhead_cost
        self._labor_items = []
        self.labor_items = labor_items
        self._works = []
        self.works = works
        self._misc_cost_items = []
        self.misc_cost_items = misc_cost_items
        self._equipment_items = []
        self.equipment_items = equipment_items
        self._cost_type = None
        self.cost_type = cost_type
        self._contractor_items = []
        self.contractor_items = contractor_items
        self._work_task = None
        self.work_task = work_task
        self._property_units = []
        self.property_units = property_units

        super(WorkCostDetail, self).__init__(**kw_args)
    # >>> work_cost_detail


class CUAllowableAction(IdentifiedObject):
    """ Allowed actions: Install, Remove, Transfer, Abandon, etc.
    """
    status = None

    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x._cuallowable_action = None
        for y in value:
            y._cuallowable_action = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cuallowable_action = self
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cuallowable_action = None
            self._compatible_units.remove(obj)

    # <<< cuallowable_action
    # @generated
    def __init__(self, status=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUAllowableAction' instance.
        """
        self.status = status
        self._compatible_units = []
        self.compatible_units = compatible_units

        super(CUAllowableAction, self).__init__(**kw_args)
    # >>> cuallowable_action


class BusinessCase(Document):
    """ Business justification for capital expenditures, usually addressing operations and maintenance costs as well.
    """
    # A codified representation of the business case (i.e., codes for highway relocation, replace substation transformers, etc.). 
    corporate_code = ''

    def get_projects(self):
        """ 
        """
        return self._projects

    def set_projects(self, value):
        for x in self._projects:
            x._business_case = None
        for y in value:
            y._business_case = self
        self._projects = value
            
    projects = property(get_projects, set_projects)
    
    def add_projects(self, *projects):
        for obj in projects:
            obj._business_case = self
            self._projects.append(obj)
        
    def remove_projects(self, *projects):
        for obj in projects:
            obj._business_case = None
            self._projects.remove(obj)

    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x._business_case = None
        for y in value:
            y._business_case = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._business_case = self
            self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._business_case = None
            self._works.remove(obj)

    # <<< business_case
    # @generated
    def __init__(self, corporate_code='', projects=[], works=[], **kw_args):
        """ Initialises a new 'BusinessCase' instance.
        """
        self.corporate_code = corporate_code
        self._projects = []
        self.projects = projects
        self._works = []
        self.works = works

        super(BusinessCase, self).__init__(**kw_args)
    # >>> business_case


class LaborItem(IdentifiedObject):
    """ Labor used for work order.
    """
    # Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    # Time required to perform work. 
    labor_duration = ''

    # The labor rate applied for work. 
    labor_rate = ''

    # Total cost for labor. Note that this may not be able to be derived from labor rate and time charged. 
    cost = ''

    status = None

    erp_persons = []
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
	        self._erp_persons.remove(obj)

    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            filtered = [x for x in self.work_cost_detail.labor_items if x != self]
            self._work_cost_detail._labor_items = filtered
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._labor_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.labor_items if x != self]
            self._work_task._labor_items = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._labor_items.append(self)

    work_task = property(get_work_task, set_work_task)

    # <<< labor_item
    # @generated
    def __init__(self, activity_code='', labor_duration='', labor_rate='', cost='', status=None, erp_persons=[], work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'LaborItem' instance.
        """
        self.activity_code = activity_code
        self.labor_duration = labor_duration
        self.labor_rate = labor_rate
        self.cost = cost
        self.status = status
        self._erp_persons = []
        self.erp_persons = erp_persons
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._work_task = None
        self.work_task = work_task

        super(LaborItem, self).__init__(**kw_args)
    # >>> labor_item


class Design(Document):
    """ A design for consideration by customers, potential customers, or internal work. Note that the Version of design is the revision attribute that is inherited from Document.
    """
    # Estimated cost (not price) of design. 
    cost_estimate = ''

    # Kind of this design. Values are: "other", "as_built", "estimated"
    kind = 'other'

    # Price to customer for implementing design. 
    price = ''

    def get_erp_boms(self):
        """ 
        """
        return self._erp_boms

    def set_erp_boms(self, value):
        for x in self._erp_boms:
            x._design = None
        for y in value:
            y._design = self
        self._erp_boms = value
            
    erp_boms = property(get_erp_boms, set_erp_boms)
    
    def add_erp_boms(self, *erp_boms):
        for obj in erp_boms:
            obj._design = self
            self._erp_boms.append(obj)
        
    def remove_erp_boms(self, *erp_boms):
        for obj in erp_boms:
            obj._design = None
            self._erp_boms.remove(obj)

    def get_erp_quote_line_item(self):
        """ 
        """
        return self._erp_quote_line_item

    def set_erp_quote_line_item(self, value):
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._design = None
            
        self._erp_quote_line_item = value
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._design = self
            
    erp_quote_line_item = property(get_erp_quote_line_item, set_erp_quote_line_item)

    design_locations = []
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.remove(obj)

    def get_work(self):
        """ 
        """
        return self._work

    def set_work(self, value):
        if self._work is not None:
            filtered = [x for x in self.work.designs if x != self]
            self._work._designs = filtered
            
        self._work = value
        if self._work is not None:
            self._work._designs.append(self)

    work = property(get_work, set_work)

    condition_factors = []
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.remove(obj)

    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x._design = None
        for y in value:
            y._design = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._design = self
            self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._design = None
            self._work_cost_details.remove(obj)

    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x._design = None
        for y in value:
            y._design = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._design = self
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._design = None
            self._work_tasks.remove(obj)

    design_locations_cus = []
    
    def add_design_locations_cus(self, *design_locations_cus):
        for obj in design_locations_cus:
	        self._design_locations_cus.append(obj)
        
    def remove_design_locations_cus(self, *design_locations_cus):
        for obj in design_locations_cus:
	        self._design_locations_cus.remove(obj)

    # <<< design
    # @generated
    def __init__(self, cost_estimate='', kind='other', price='', erp_boms=[], erp_quote_line_item=None, design_locations=[], work=None, condition_factors=[], work_cost_details=[], work_tasks=[], design_locations_cus=[], **kw_args):
        """ Initialises a new 'Design' instance.
        """
        self.cost_estimate = cost_estimate
        self.kind = kind
        self.price = price
        self._erp_boms = []
        self.erp_boms = erp_boms
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._design_locations = []
        self.design_locations = design_locations
        self._work = None
        self.work = work
        self._condition_factors = []
        self.condition_factors = condition_factors
        self._work_cost_details = []
        self.work_cost_details = work_cost_details
        self._work_tasks = []
        self.work_tasks = work_tasks
        self._design_locations_cus = []
        self.design_locations_cus = design_locations_cus

        super(Design, self).__init__(**kw_args)
    # >>> design


class WorkTask(Document):
    """ A set of tasks is required to implement a design.
    """
    # If specified, override schedule and perform this task in accordance with instructions specified here. 
    sched_override = ''

    # The priority of this work task. 
    priority = ''

    qualification_requirements = []
    
    def add_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
	        self._qualification_requirements.append(obj)
        
    def remove_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
	        self._qualification_requirements.remove(obj)

    def get_design(self):
        """ 
        """
        return self._design

    def set_design(self, value):
        if self._design is not None:
            filtered = [x for x in self.design.work_tasks if x != self]
            self._design._work_tasks = filtered
            
        self._design = value
        if self._design is not None:
            self._design._work_tasks.append(self)

    design = property(get_design, set_design)

    design_location_cus = []
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.remove(obj)

    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_task = self
            self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_task = None
            self._misc_cost_items.remove(obj)

    def get_switching_schedules(self):
        """ 
        """
        return self._switching_schedules

    def set_switching_schedules(self, value):
        for x in self._switching_schedules:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._switching_schedules = value
            
    switching_schedules = property(get_switching_schedules, set_switching_schedules)
    
    def add_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            obj._work_task = self
            self._switching_schedules.append(obj)
        
    def remove_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            obj._work_task = None
            self._switching_schedules.remove(obj)

    capabilities = []
    
    def add_capabilities(self, *capabilities):
        for obj in capabilities:
	        self._capabilities.append(obj)
        
    def remove_capabilities(self, *capabilities):
        for obj in capabilities:
	        self._capabilities.remove(obj)

    def get_usages(self):
        """ 
        """
        return self._usages

    def set_usages(self, value):
        for x in self._usages:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._usages = value
            
    usages = property(get_usages, set_usages)
    
    def add_usages(self, *usages):
        for obj in usages:
            obj._work_task = self
            self._usages.append(obj)
        
    def remove_usages(self, *usages):
        for obj in usages:
            obj._work_task = None
            self._usages.remove(obj)

    def get_overhead_cost(self):
        """ 
        """
        return self._overhead_cost

    def set_overhead_cost(self, value):
        if self._overhead_cost is not None:
            filtered = [x for x in self.overhead_cost.work_tasks if x != self]
            self._overhead_cost._work_tasks = filtered
            
        self._overhead_cost = value
        if self._overhead_cost is not None:
            self._overhead_cost._work_tasks.append(self)

    overhead_cost = property(get_overhead_cost, set_overhead_cost)

    def get_work_flow_step(self):
        """ 
        """
        return self._work_flow_step

    def set_work_flow_step(self, value):
        if self._work_flow_step is not None:
            filtered = [x for x in self.work_flow_step.work_tasks if x != self]
            self._work_flow_step._work_tasks = filtered
            
        self._work_flow_step = value
        if self._work_flow_step is not None:
            self._work_flow_step._work_tasks.append(self)

    work_flow_step = property(get_work_flow_step, set_work_flow_step)

    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._work_task = self
            self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._work_task = None
            self._material_items.remove(obj)

    def get_labor_items(self):
        """ 
        """
        return self._labor_items

    def set_labor_items(self, value):
        for x in self._labor_items:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._labor_items = value
            
    labor_items = property(get_labor_items, set_labor_items)
    
    def add_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_task = self
            self._labor_items.append(obj)
        
    def remove_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_task = None
            self._labor_items.remove(obj)

    crews = []
    
    def add_crews(self, *crews):
        for obj in crews:
	        self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
	        self._crews.remove(obj)

    def get_work(self):
        """ 
        """
        return self._work

    def set_work(self, value):
        if self._work is not None:
            filtered = [x for x in self.work.work_tasks if x != self]
            self._work._work_tasks = filtered
            
        self._work = value
        if self._work is not None:
            self._work._work_tasks.append(self)

    work = property(get_work, set_work)

    def get_contractor_items(self):
        """ 
        """
        return self._contractor_items

    def set_contractor_items(self, value):
        for x in self._contractor_items:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._contractor_items = value
            
    contractor_items = property(get_contractor_items, set_contractor_items)
    
    def add_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_task = self
            self._contractor_items.append(obj)
        
    def remove_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_task = None
            self._contractor_items.remove(obj)

    def get_equipment_items(self):
        """ 
        """
        return self._equipment_items

    def set_equipment_items(self, value):
        for x in self._equipment_items:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._equipment_items = value
            
    equipment_items = property(get_equipment_items, set_equipment_items)
    
    def add_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_task = self
            self._equipment_items.append(obj)
        
    def remove_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_task = None
            self._equipment_items.remove(obj)

    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._work_task = self
            self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._work_task = None
            self._work_cost_details.remove(obj)

    def get_assets(self):
        """ 
        """
        return self._assets

    def set_assets(self, value):
        for x in self._assets:
            x._work_task = None
        for y in value:
            y._work_task = self
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            obj._work_task = self
            self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            obj._work_task = None
            self._assets.remove(obj)

    # <<< work_task
    # @generated
    def __init__(self, sched_override='', priority='', qualification_requirements=[], design=None, design_location_cus=[], misc_cost_items=[], switching_schedules=[], capabilities=[], usages=[], overhead_cost=None, work_flow_step=None, material_items=[], labor_items=[], crews=[], work=None, contractor_items=[], equipment_items=[], work_cost_details=[], assets=[], **kw_args):
        """ Initialises a new 'WorkTask' instance.
        """
        self.sched_override = sched_override
        self.priority = priority
        self._qualification_requirements = []
        self.qualification_requirements = qualification_requirements
        self._design = None
        self.design = design
        self._design_location_cus = []
        self.design_location_cus = design_location_cus
        self._misc_cost_items = []
        self.misc_cost_items = misc_cost_items
        self._switching_schedules = []
        self.switching_schedules = switching_schedules
        self._capabilities = []
        self.capabilities = capabilities
        self._usages = []
        self.usages = usages
        self._overhead_cost = None
        self.overhead_cost = overhead_cost
        self._work_flow_step = None
        self.work_flow_step = work_flow_step
        self._material_items = []
        self.material_items = material_items
        self._labor_items = []
        self.labor_items = labor_items
        self._crews = []
        self.crews = crews
        self._work = None
        self.work = work
        self._contractor_items = []
        self.contractor_items = contractor_items
        self._equipment_items = []
        self.equipment_items = equipment_items
        self._work_cost_details = []
        self.work_cost_details = work_cost_details
        self._assets = []
        self.assets = assets

        super(WorkTask, self).__init__(**kw_args)
    # >>> work_task


class NonStandardItem(Document):
    """ This document provides information for non-standard items like customer contributions (e.g., customer digs trench), vouchers (e.g., credit), and contractor bids.
    """
    # The category of non-standard work. 
    code = ''

    # The projected cost for this item. 
    amount = ''

    # <<< non_standard_item
    # @generated
    def __init__(self, code='', amount='', **kw_args):
        """ Initialises a new 'NonStandardItem' instance.
        """
        self.code = code
        self.amount = amount

        super(NonStandardItem, self).__init__(**kw_args)
    # >>> non_standard_item


class ConditionFactor(IdentifiedObject):
    """ This is to specify the various condition factors for a design that may alter the cost estimate or the allocation.
    """
    # Kind of this condition factor. Values are: "material", "travel", "other", "account_allocation", "labor"
    kind = 'material'

    # The actual value of the condition factor, such as labor flat fee or percentage. 
    cf_value = ''

    status = None

    designs = []
    
    def add_designs(self, *designs):
        for obj in designs:
	        self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
	        self._designs.remove(obj)

    design_location_cus = []
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.remove(obj)

    design_locations = []
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
	        self._design_locations.remove(obj)

    # <<< condition_factor
    # @generated
    def __init__(self, kind='material', cf_value='', status=None, designs=[], design_location_cus=[], design_locations=[], **kw_args):
        """ Initialises a new 'ConditionFactor' instance.
        """
        self.kind = kind
        self.cf_value = cf_value
        self.status = status
        self._designs = []
        self.designs = designs
        self._design_location_cus = []
        self.design_location_cus = design_location_cus
        self._design_locations = []
        self.design_locations = design_locations

        super(ConditionFactor, self).__init__(**kw_args)
    # >>> condition_factor


class CUGroup(IdentifiedObject):
    """ A Compatible Unit Group identifies a set of compatible units which may be jointly utilized for estimating and designating jobs.
    """
    status = None

    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x._cugroup = None
        for y in value:
            y._cugroup = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cugroup = self
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cugroup = None
            self._compatible_units.remove(obj)

    design_location_cus = []
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
	        self._design_location_cus.remove(obj)

    child_cugroups = []
    
    def add_child_cugroups(self, *child_cugroups):
        for obj in child_cugroups:
	        self._child_cugroups.append(obj)
        
    def remove_child_cugroups(self, *child_cugroups):
        for obj in child_cugroups:
	        self._child_cugroups.remove(obj)

    parent_cugroups = []
    
    def add_parent_cugroups(self, *parent_cugroups):
        for obj in parent_cugroups:
	        self._parent_cugroups.append(obj)
        
    def remove_parent_cugroups(self, *parent_cugroups):
        for obj in parent_cugroups:
	        self._parent_cugroups.remove(obj)

    # <<< cugroup
    # @generated
    def __init__(self, status=None, compatible_units=[], design_location_cus=[], child_cugroups=[], parent_cugroups=[], **kw_args):
        """ Initialises a new 'CUGroup' instance.
        """
        self.status = status
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._design_location_cus = []
        self.design_location_cus = design_location_cus
        self._child_cugroups = []
        self.child_cugroups = child_cugroups
        self._parent_cugroups = []
        self.parent_cugroups = parent_cugroups

        super(CUGroup, self).__init__(**kw_args)
    # >>> cugroup


class ContractorItem(IdentifiedObject):
    """ Contractor information for work task.
    """
    # The amount that a given contractor will charge for performing this unit of work. 
    bid_amount = ''

    # The total amount charged. 
    cost = ''

    # Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    status = None

    erp_payables = []
    
    def add_erp_payables(self, *erp_payables):
        for obj in erp_payables:
	        self._erp_payables.append(obj)
        
    def remove_erp_payables(self, *erp_payables):
        for obj in erp_payables:
	        self._erp_payables.remove(obj)

    def get_work_cost_detail(self):
        """ 
        """
        return self._work_cost_detail

    def set_work_cost_detail(self, value):
        if self._work_cost_detail is not None:
            filtered = [x for x in self.work_cost_detail.contractor_items if x != self]
            self._work_cost_detail._contractor_items = filtered
            
        self._work_cost_detail = value
        if self._work_cost_detail is not None:
            self._work_cost_detail._contractor_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)

    def get_work_task(self):
        """ 
        """
        return self._work_task

    def set_work_task(self, value):
        if self._work_task is not None:
            filtered = [x for x in self.work_task.contractor_items if x != self]
            self._work_task._contractor_items = filtered
            
        self._work_task = value
        if self._work_task is not None:
            self._work_task._contractor_items.append(self)

    work_task = property(get_work_task, set_work_task)

    # <<< contractor_item
    # @generated
    def __init__(self, bid_amount='', cost='', activity_code='', status=None, erp_payables=[], work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'ContractorItem' instance.
        """
        self.bid_amount = bid_amount
        self.cost = cost
        self.activity_code = activity_code
        self.status = status
        self._erp_payables = []
        self.erp_payables = erp_payables
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._work_task = None
        self.work_task = work_task

        super(ContractorItem, self).__init__(**kw_args)
    # >>> contractor_item


class CUAsset(IdentifiedObject):
    """ Compatible unit for various types of assets such as transformers switches, substation fences, poles, etc..
    """
    # Quantity of the type asset within the CU. 
    quantity = ''

    # The code for this type of asset. 
    type_asset_code = ''

    status = None

    def get_type_asset(self):
        """ 
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            self._type_asset._cuasset = None
            
        self._type_asset = value
        if self._type_asset is not None:
            self._type_asset._cuasset = self
            
    type_asset = property(get_type_asset, set_type_asset)

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    # <<< cuasset
    # @generated
    def __init__(self, quantity='', type_asset_code='', status=None, type_asset=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUAsset' instance.
        """
        self.quantity = quantity
        self.type_asset_code = type_asset_code
        self.status = status
        self._type_asset = None
        self.type_asset = type_asset
        self._compatible_units = []
        self.compatible_units = compatible_units

        super(CUAsset, self).__init__(**kw_args)
    # >>> cuasset


class DesignLocation(IdentifiedObject):
    """ A logical part of the design (e.g., pole and all equipment on a pole). This includes points and spans.
    """
    # The legth of the span from the previous pole to this pole. 
    span_length = ''

    status = None

    diagrams = []
    
    def add_diagrams(self, *diagrams):
        for obj in diagrams:
	        self._diagrams.append(obj)
        
    def remove_diagrams(self, *diagrams):
        for obj in diagrams:
	        self._diagrams.remove(obj)

    def get_erp_bom_item_datas(self):
        """ 
        """
        return self._erp_bom_item_datas

    def set_erp_bom_item_datas(self, value):
        for x in self._erp_bom_item_datas:
            x._design_location = None
        for y in value:
            y._design_location = self
        self._erp_bom_item_datas = value
            
    erp_bom_item_datas = property(get_erp_bom_item_datas, set_erp_bom_item_datas)
    
    def add_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._design_location = self
            self._erp_bom_item_datas.append(obj)
        
    def remove_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._design_location = None
            self._erp_bom_item_datas.remove(obj)

    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x._design_location = None
        for y in value:
            y._design_location = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._design_location = self
            self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._design_location = None
            self._misc_cost_items.remove(obj)

    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for x in self._design_location_cus:
            x._design_location = None
        for y in value:
            y._design_location = self
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            obj._design_location = self
            self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            obj._design_location = None
            self._design_location_cus.remove(obj)

    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x._design_location = None
        for y in value:
            y._design_location = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._design_location = self
            self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._design_location = None
            self._material_items.remove(obj)

    condition_factors = []
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
	        self._condition_factors.remove(obj)

    work_locations = []
    
    def add_work_locations(self, *work_locations):
        for obj in work_locations:
	        self._work_locations.append(obj)
        
    def remove_work_locations(self, *work_locations):
        for obj in work_locations:
	        self._work_locations.remove(obj)

    designs = []
    
    def add_designs(self, *designs):
        for obj in designs:
	        self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
	        self._designs.remove(obj)

    # <<< design_location
    # @generated
    def __init__(self, span_length='', status=None, diagrams=[], erp_bom_item_datas=[], misc_cost_items=[], design_location_cus=[], material_items=[], condition_factors=[], work_locations=[], designs=[], **kw_args):
        """ Initialises a new 'DesignLocation' instance.
        """
        self.span_length = span_length
        self.status = status
        self._diagrams = []
        self.diagrams = diagrams
        self._erp_bom_item_datas = []
        self.erp_bom_item_datas = erp_bom_item_datas
        self._misc_cost_items = []
        self.misc_cost_items = misc_cost_items
        self._design_location_cus = []
        self.design_location_cus = design_location_cus
        self._material_items = []
        self.material_items = material_items
        self._condition_factors = []
        self.condition_factors = condition_factors
        self._work_locations = []
        self.work_locations = work_locations
        self._designs = []
        self.designs = designs

        super(DesignLocation, self).__init__(**kw_args)
    # >>> design_location


class OverheadCost(IdentifiedObject):
    """ Overhead cost applied to work order.
    """
    # The overhead cost to be applied. 
    cost = ''

    # Overhead code. 
    code = ''

    status = None

    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x._overhead_cost = None
        for y in value:
            y._overhead_cost = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._overhead_cost = self
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._overhead_cost = None
            self._work_tasks.remove(obj)

    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x._overhead_cost = None
        for y in value:
            y._overhead_cost = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._overhead_cost = self
            self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._overhead_cost = None
            self._work_cost_details.remove(obj)

    # <<< overhead_cost
    # @generated
    def __init__(self, cost='', code='', status=None, work_tasks=[], work_cost_details=[], **kw_args):
        """ Initialises a new 'OverheadCost' instance.
        """
        self.cost = cost
        self.code = code
        self.status = status
        self._work_tasks = []
        self.work_tasks = work_tasks
        self._work_cost_details = []
        self.work_cost_details = work_cost_details

        super(OverheadCost, self).__init__(**kw_args)
    # >>> overhead_cost


class Regulation(Document):
    """ Special requirements and/or regulations may pertain to certain types of assets or work. For example, fire protection and scaffolding.
    """
    # External reference to regulation, if applicable. 
    reference_number = ''

    # <<< regulation
    # @generated
    def __init__(self, reference_number='', **kw_args):
        """ Initialises a new 'Regulation' instance.
        """
        self.reference_number = reference_number

        super(Regulation, self).__init__(**kw_args)
    # >>> regulation


class InfoQuestion(Document):
    """ Questions and answers associated with a type of document for purposes of clarification. Questions may be predefined or ad hoc.
    """
    # Answer to question. 
    answer = ''

    # Remarks to qualify the question in this situation. 
    question_remark = ''

    # The question code. If blank, refer to questionText. 
    question_code = ''

    # The category of the question. 
    question_category = ''

    # The date and time the quesiton was answered. 
    answer_date_time = ''

    # Remarks to qualify the answer. 
    answer_remark = ''

    # For non-coded questions, the question is provided here. 
    question_text = ''

    # <<< info_question
    # @generated
    def __init__(self, answer='', question_remark='', question_code='', question_category='', answer_date_time='', answer_remark='', question_text='', **kw_args):
        """ Initialises a new 'InfoQuestion' instance.
        """
        self.answer = answer
        self.question_remark = question_remark
        self.question_code = question_code
        self.question_category = question_category
        self.answer_date_time = answer_date_time
        self.answer_remark = answer_remark
        self.question_text = question_text

        super(InfoQuestion, self).__init__(**kw_args)
    # >>> info_question


class CUWorkEquipmentItem(IdentifiedObject):
    """ Compatible unit for various types of WorkEquipmentAssets, including vehicles.
    """
    # The equipment type code. 
    equip_code = ''

    # Standard usage rate for the type of vehicle. 
    rate = ''

    status = None

    compatible_units = []
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
	        self._compatible_units.remove(obj)

    def get_type_asset(self):
        """ 
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            self._type_asset._cuwork_equipment_asset = None
            
        self._type_asset = value
        if self._type_asset is not None:
            self._type_asset._cuwork_equipment_asset = self
            
    type_asset = property(get_type_asset, set_type_asset)

    # <<< cuwork_equipment_item
    # @generated
    def __init__(self, equip_code='', rate='', status=None, compatible_units=[], type_asset=None, **kw_args):
        """ Initialises a new 'CUWorkEquipmentItem' instance.
        """
        self.equip_code = equip_code
        self.rate = rate
        self.status = status
        self._compatible_units = []
        self.compatible_units = compatible_units
        self._type_asset = None
        self.type_asset = type_asset

        super(CUWorkEquipmentItem, self).__init__(**kw_args)
    # >>> cuwork_equipment_item


# <<< inf_work
# @generated
# >>> inf_work

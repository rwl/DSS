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
    # <<< request
    # @generated
    def __init__(self, corporate_code='', action_needed='', priority='', erp_quote_line_item=None, organisation=None, works=None, projects=None, **kw_args):
        """ Initialises a new 'Request' instance.
        """
        # The corporate code for this request. 
        self.corporate_code = corporate_code
        # Based on the current 'Status.status', the action that is needed before this Request can transition to the desired state, such as initiating the requested Work. For example, missing or additionally needed information may be required from the requesting organisation before a work Design may be created. 
        self.action_needed = action_needed
        # The priority of this request. 
        self.priority = priority
        
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._organisation = None
        self.organisation = organisation
        self._works = []
        if works is None:
            self.works = []
        else:
            self.works = works
        self._projects = []
        if projects is None:
            self.projects = []
        else:
            self.projects = projects

        super(Request, self).__init__(**kw_args)
    # >>> request
        
    # <<< erp_quote_line_item
    # @generated
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
    # >>> erp_quote_line_item

    # <<< organisation
    # @generated
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
            if self not in self._organisation._requests:
                self._organisation._requests.append(self)

    organisation = property(get_organisation, set_organisation)
    # >>> organisation

    # <<< works
    # @generated
    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x.request = None
        for y in value:
            y.request = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._request = self
            if obj not in self._works:
                self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._request = None
            self._works.remove(obj)
    # >>> works

    # <<< projects
    # @generated
    def get_projects(self):
        """ 
        """
        return self._projects

    def set_projects(self, value):
        for p in self._projects:
            filtered = [q for q in p.requests if q != self]
            self._projects._requests = filtered
        for r in value:
            if self not in r._requests:
                r._requests.append(self)
        self._projects = value
            
    projects = property(get_projects, set_projects)
    
    def add_projects(self, *projects):
        for obj in projects:
            if self not in obj._requests:
                obj._requests.append(self)
            self._projects.append(obj)
        
    def remove_projects(self, *projects):
        for obj in projects:
            if self in obj._requests:
                obj._requests.remove(self)
            self._projects.remove(obj)
    # >>> projects



class CostType(IdentifiedObject):
    """ A categorization for resources, often costs, in accounting transactions. Examples include: material components, building in service, coal sales, overhead, etc.
    """
    # <<< cost_type
    # @generated
    def __init__(self, amount_assignment_flag=False, code='', level='', stage='', status=None, parent_cost_type=None, child_cost_types=None, compatible_units=None, erp_journal_entries=None, work_cost_details=None, **kw_args):
        """ Initialises a new 'CostType' instance.
        """
        # True if an amount can be assigned to the resource element (e.g., building in service, transmission plant, software development capital); false otherwise (e.g., internal labor, material components). 
        self.amount_assignment_flag = amount_assignment_flag
        # A codified representation of the resource element. 
        self.code = code
        # The level of the resource element in the hierarchy of resource elements (recursive relationship). 
        self.level = level
        # The stage for which this costType applies: estimated design, estimated actual or actual actual. 
        self.stage = stage
        
        self.status = status
        self._parent_cost_type = None
        self.parent_cost_type = parent_cost_type
        self._child_cost_types = []
        if child_cost_types is None:
            self.child_cost_types = []
        else:
            self.child_cost_types = child_cost_types
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._erp_journal_entries = []
        if erp_journal_entries is None:
            self.erp_journal_entries = []
        else:
            self.erp_journal_entries = erp_journal_entries
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details

        super(CostType, self).__init__(**kw_args)
    # >>> cost_type
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< parent_cost_type
    # @generated
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
            if self not in self._parent_cost_type._child_cost_types:
                self._parent_cost_type._child_cost_types.append(self)

    parent_cost_type = property(get_parent_cost_type, set_parent_cost_type)
    # >>> parent_cost_type

    # <<< child_cost_types
    # @generated
    def get_child_cost_types(self):
        """ 
        """
        return self._child_cost_types

    def set_child_cost_types(self, value):
        for x in self._child_cost_types:
            x.parent_cost_type = None
        for y in value:
            y.parent_cost_type = self
        self._child_cost_types = value
            
    child_cost_types = property(get_child_cost_types, set_child_cost_types)
    
    def add_child_cost_types(self, *child_cost_types):
        for obj in child_cost_types:
            obj._parent_cost_type = self
            if obj not in self._child_cost_types:
                self._child_cost_types.append(obj)
        
    def remove_child_cost_types(self, *child_cost_types):
        for obj in child_cost_types:
            obj._parent_cost_type = None
            self._child_cost_types.remove(obj)
    # >>> child_cost_types

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x.cost_type = None
        for y in value:
            y.cost_type = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cost_type = self
            if obj not in self._compatible_units:
                self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cost_type = None
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< erp_journal_entries
    # @generated
    def get_erp_journal_entries(self):
        """ 
        """
        return self._erp_journal_entries

    def set_erp_journal_entries(self, value):
        for p in self._erp_journal_entries:
            filtered = [q for q in p.cost_types if q != self]
            self._erp_journal_entries._cost_types = filtered
        for r in value:
            if self not in r._cost_types:
                r._cost_types.append(self)
        self._erp_journal_entries = value
            
    erp_journal_entries = property(get_erp_journal_entries, set_erp_journal_entries)
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self not in obj._cost_types:
                obj._cost_types.append(self)
            self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self in obj._cost_types:
                obj._cost_types.remove(self)
            self._erp_journal_entries.remove(obj)
    # >>> erp_journal_entries

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x.cost_type = None
        for y in value:
            y.cost_type = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._cost_type = self
            if obj not in self._work_cost_details:
                self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._cost_type = None
            self._work_cost_details.remove(obj)
    # >>> work_cost_details



class Usage(IdentifiedObject):
    """ The way material and assets are used to perform a certain type of work task. The way is described in text in the inheritied description attribute.
    """
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
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_task
    # @generated
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
            if self not in self._work_task._usages:
                self._work_task._usages.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task

    # <<< work_equipment_asset
    # @generated
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
            if self not in self._work_equipment_asset._usages:
                self._work_equipment_asset._usages.append(self)

    work_equipment_asset = property(get_work_equipment_asset, set_work_equipment_asset)
    # >>> work_equipment_asset

    # <<< material_item
    # @generated
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
            if self not in self._material_item._usages:
                self._material_item._usages.append(self)

    material_item = property(get_material_item, set_material_item)
    # >>> material_item



class QualificationRequirement(IdentifiedObject):
    """ Certain skills are required and must be certified in order for a person (typically a member of a crew) to be qualified to work on types of equipment.
    """
    # <<< qualification_requirement
    # @generated
    def __init__(self, qualification_id='', culabor_items=None, skills=None, specifications=None, work_tasks=None, **kw_args):
        """ Initialises a new 'QualificationRequirement' instance.
        """
        # Qualification identifier. 
        self.qualification_id = qualification_id
        
        self._culabor_items = []
        if culabor_items is None:
            self.culabor_items = []
        else:
            self.culabor_items = culabor_items
        self._skills = []
        if skills is None:
            self.skills = []
        else:
            self.skills = skills
        self._specifications = []
        if specifications is None:
            self.specifications = []
        else:
            self.specifications = specifications
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks

        super(QualificationRequirement, self).__init__(**kw_args)
    # >>> qualification_requirement
        
    # <<< culabor_items
    # @generated
    def get_culabor_items(self):
        """ 
        """
        return self._culabor_items

    def set_culabor_items(self, value):
        for p in self._culabor_items:
            filtered = [q for q in p.qualification_requirements if q != self]
            self._culabor_items._qualification_requirements = filtered
        for r in value:
            if self not in r._qualification_requirements:
                r._qualification_requirements.append(self)
        self._culabor_items = value
            
    culabor_items = property(get_culabor_items, set_culabor_items)
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            if self not in obj._qualification_requirements:
                obj._qualification_requirements.append(self)
            self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            if self in obj._qualification_requirements:
                obj._qualification_requirements.remove(self)
            self._culabor_items.remove(obj)
    # >>> culabor_items

    # <<< skills
    # @generated
    def get_skills(self):
        """ 
        """
        return self._skills

    def set_skills(self, value):
        for p in self._skills:
            filtered = [q for q in p.qualification_requirements if q != self]
            self._skills._qualification_requirements = filtered
        for r in value:
            if self not in r._qualification_requirements:
                r._qualification_requirements.append(self)
        self._skills = value
            
    skills = property(get_skills, set_skills)
    
    def add_skills(self, *skills):
        for obj in skills:
            if self not in obj._qualification_requirements:
                obj._qualification_requirements.append(self)
            self._skills.append(obj)
        
    def remove_skills(self, *skills):
        for obj in skills:
            if self in obj._qualification_requirements:
                obj._qualification_requirements.remove(self)
            self._skills.remove(obj)
    # >>> skills

    # <<< specifications
    # @generated
    def get_specifications(self):
        """ 
        """
        return self._specifications

    def set_specifications(self, value):
        for p in self._specifications:
            filtered = [q for q in p.qualification_requirements if q != self]
            self._specifications._qualification_requirements = filtered
        for r in value:
            if self not in r._qualification_requirements:
                r._qualification_requirements.append(self)
        self._specifications = value
            
    specifications = property(get_specifications, set_specifications)
    
    def add_specifications(self, *specifications):
        for obj in specifications:
            if self not in obj._qualification_requirements:
                obj._qualification_requirements.append(self)
            self._specifications.append(obj)
        
    def remove_specifications(self, *specifications):
        for obj in specifications:
            if self in obj._qualification_requirements:
                obj._qualification_requirements.remove(self)
            self._specifications.remove(obj)
    # >>> specifications

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for p in self._work_tasks:
            filtered = [q for q in p.qualification_requirements if q != self]
            self._work_tasks._qualification_requirements = filtered
        for r in value:
            if self not in r._qualification_requirements:
                r._qualification_requirements.append(self)
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self not in obj._qualification_requirements:
                obj._qualification_requirements.append(self)
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self in obj._qualification_requirements:
                obj._qualification_requirements.remove(self)
            self._work_tasks.remove(obj)
    # >>> work_tasks



class DiagnosisDataSet(ProcedureDataSet):
    """ The result of a problem (typically an asset failure) diagnosis.
    """
    # <<< diagnosis_data_set
    # @generated
    def __init__(self, effect='', root_origin='', final_code='', preliminary_date='', root_remark='', phase_code='abn', final_cause='', root_cause='', preliminary_remark='', final_date='', final_origin='', failure_mode='', final_remark='', preliminary_code='', **kw_args):
        """ Initialises a new 'DiagnosisDataSet' instance.
        """
        # Effect of problem. 
        self.effect = effect
        # Root origin of problem determined during diagnosis. 
        self.root_origin = root_origin
        # Code for diagnosed probem category. 
        self.final_code = final_code
        # Date and time preliminary assessment of problem was performed. 
        self.preliminary_date = preliminary_date
        # Remarks pertaining to root cause findings during problem diagnosis. 
        self.root_remark = root_remark
        # Phase(s) diagnosed. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
        self.phase_code = phase_code
        # Cause of problem determined during diagnosis. 
        self.final_cause = final_cause
        # Root cause of problem determined during diagnosis. 
        self.root_cause = root_cause
        # Remarks pertaining to preliminary assessment of problem. 
        self.preliminary_remark = preliminary_remark
        # Date and time diagnosis was completed. 
        self.final_date = final_date
        # Origin of problem determined during diagnosis. 
        self.final_origin = final_origin
        # Failuer mode, for example: Failure to Insulate; Failure to conduct; Failure to contain oil; Failure to provide ground plane; Other. 
        self.failure_mode = failure_mode
        # Remarks pertaining to findings during problem diagnosis. 
        self.final_remark = final_remark
        # Code for problem category determined during preliminary assessment. 
        self.preliminary_code = preliminary_code
        

        super(DiagnosisDataSet, self).__init__(**kw_args)
    # >>> diagnosis_data_set
        


class TypeMaterial(Document):
    """ Documentation for a generic material item that may be used for design, work and other purposes. Any number of MaterialItems manufactured by various vendors may be used to perform this TypeMaterial. Note that class analagous to 'AssetModel' is not used for material items. This is because in some cases, for example, a utility sets up a Master material record for a 3 inch long half inch diameter steel bolt and they do not necessarily care what specific supplier is providing the material item. As different vendors are used to supply the part, the Stock Code of the material item can stay the same. In other cases, each time the vendor changes, a new stock code is set up so they can track material used by vendor. Therefore a Material Item 'Model' is not typically needed.
    """
    # <<< type_material
    # @generated
    def __init__(self, cost_type='', quantity='', est_unit_cost=0.0, stock_item=False, erp_issue_inventories=None, erp_req_line_items=None, material_items=None, cumaterial_items=None, **kw_args):
        """ Initialises a new 'TypeMaterial' instance.
        """
        # The category of cost to which this Material Item belongs. 
        self.cost_type = cost_type
        # The value, unit of measure, and multiplier for the quantity. 
        self.quantity = quantity
        # The estimated unit cost of this type of material, either for a unit cost or cost per unit length. Cost is for material or asset only and does not include labor to install/construct or configure it. 
        self.est_unit_cost = est_unit_cost
        # True if item is a stock item (default). 
        self.stock_item = stock_item
        
        self._erp_issue_inventories = []
        if erp_issue_inventories is None:
            self.erp_issue_inventories = []
        else:
            self.erp_issue_inventories = erp_issue_inventories
        self._erp_req_line_items = []
        if erp_req_line_items is None:
            self.erp_req_line_items = []
        else:
            self.erp_req_line_items = erp_req_line_items
        self._material_items = []
        if material_items is None:
            self.material_items = []
        else:
            self.material_items = material_items
        self._cumaterial_items = []
        if cumaterial_items is None:
            self.cumaterial_items = []
        else:
            self.cumaterial_items = cumaterial_items

        super(TypeMaterial, self).__init__(**kw_args)
    # >>> type_material
        
    # <<< erp_issue_inventories
    # @generated
    def get_erp_issue_inventories(self):
        """ 
        """
        return self._erp_issue_inventories

    def set_erp_issue_inventories(self, value):
        for x in self._erp_issue_inventories:
            x.type_material = None
        for y in value:
            y.type_material = self
        self._erp_issue_inventories = value
            
    erp_issue_inventories = property(get_erp_issue_inventories, set_erp_issue_inventories)
    
    def add_erp_issue_inventories(self, *erp_issue_inventories):
        for obj in erp_issue_inventories:
            obj._type_material = self
            if obj not in self._erp_issue_inventories:
                self._erp_issue_inventories.append(obj)
        
    def remove_erp_issue_inventories(self, *erp_issue_inventories):
        for obj in erp_issue_inventories:
            obj._type_material = None
            self._erp_issue_inventories.remove(obj)
    # >>> erp_issue_inventories

    # <<< erp_req_line_items
    # @generated
    def get_erp_req_line_items(self):
        """ 
        """
        return self._erp_req_line_items

    def set_erp_req_line_items(self, value):
        for x in self._erp_req_line_items:
            x.type_material = None
        for y in value:
            y.type_material = self
        self._erp_req_line_items = value
            
    erp_req_line_items = property(get_erp_req_line_items, set_erp_req_line_items)
    
    def add_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._type_material = self
            if obj not in self._erp_req_line_items:
                self._erp_req_line_items.append(obj)
        
    def remove_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._type_material = None
            self._erp_req_line_items.remove(obj)
    # >>> erp_req_line_items

    # <<< material_items
    # @generated
    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x.type_material = None
        for y in value:
            y.type_material = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._type_material = self
            if obj not in self._material_items:
                self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._type_material = None
            self._material_items.remove(obj)
    # >>> material_items

    # <<< cumaterial_items
    # @generated
    def get_cumaterial_items(self):
        """ 
        """
        return self._cumaterial_items

    def set_cumaterial_items(self, value):
        for x in self._cumaterial_items:
            x.type_material = None
        for y in value:
            y.type_material = self
        self._cumaterial_items = value
            
    cumaterial_items = property(get_cumaterial_items, set_cumaterial_items)
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            obj._type_material = self
            if obj not in self._cumaterial_items:
                self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            obj._type_material = None
            self._cumaterial_items.remove(obj)
    # >>> cumaterial_items



class CULaborCode(IdentifiedObject):
    """ Labor code associated with various compatible unit labor items.
    """
    # <<< culabor_code
    # @generated
    def __init__(self, code='', status=None, culabor_items=None, **kw_args):
        """ Initialises a new 'CULaborCode' instance.
        """
        # Labor code. 
        self.code = code
        
        self.status = status
        self._culabor_items = []
        if culabor_items is None:
            self.culabor_items = []
        else:
            self.culabor_items = culabor_items

        super(CULaborCode, self).__init__(**kw_args)
    # >>> culabor_code
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< culabor_items
    # @generated
    def get_culabor_items(self):
        """ 
        """
        return self._culabor_items

    def set_culabor_items(self, value):
        for x in self._culabor_items:
            x.culabor_code = None
        for y in value:
            y.culabor_code = self
        self._culabor_items = value
            
    culabor_items = property(get_culabor_items, set_culabor_items)
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            obj._culabor_code = self
            if obj not in self._culabor_items:
                self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            obj._culabor_code = None
            self._culabor_items.remove(obj)
    # >>> culabor_items



class EquipmentItem(IdentifiedObject):
    """ An equipment item, such as a vehicle, used for a work order.
    """
    # <<< equipment_item
    # @generated
    def __init__(self, cost=0.0, code='', status=None, work_task=None, work_cost_detail=None, **kw_args):
        """ Initialises a new 'EquipmentItem' instance.
        """
        # The cost for vehicle usage. 
        self.cost = cost
        # Code for type of vehicle. 
        self.code = code
        
        self.status = status
        self._work_task = None
        self.work_task = work_task
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail

        super(EquipmentItem, self).__init__(**kw_args)
    # >>> equipment_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_task
    # @generated
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
            if self not in self._work_task._equipment_items:
                self._work_task._equipment_items.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task

    # <<< work_cost_detail
    # @generated
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
            if self not in self._work_cost_detail._equipment_items:
                self._work_cost_detail._equipment_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)
    # >>> work_cost_detail



class ShiftPattern(IdentifiedObject):
    """ The patterns of shifts worked by people or crews.
    """
    # <<< shift_pattern
    # @generated
    def __init__(self, cycle_count=0, assignment_type='', validity_interval=None, status=None, crews=None, **kw_args):
        """ Initialises a new 'ShiftPattern' instance.
        """
        # Number of cycles for a temporary shift. 
        self.cycle_count = cycle_count
        # Type of assignement intended to be worked on this shift, for example, temporary, standard, etc. 
        self.assignment_type = assignment_type
        
        self.validity_interval = validity_interval
        self.status = status
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews

        super(ShiftPattern, self).__init__(**kw_args)
    # >>> shift_pattern
        
    # <<< validity_interval
    # @generated
    # Date and time interval for which this shift pattern is valid (when it became effective and when it expires).
    validity_interval = None
    # >>> validity_interval

    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< crews
    # @generated
    def get_crews(self):
        """ 
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.shift_patterns if q != self]
            self._crews._shift_patterns = filtered
        for r in value:
            if self not in r._shift_patterns:
                r._shift_patterns.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._shift_patterns:
                obj._shift_patterns.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._shift_patterns:
                obj._shift_patterns.remove(self)
            self._crews.remove(obj)
    # >>> crews



class AccessPermit(Document):
    """ A permit is sometimes needed to provide legal access to land or equipment. For example, local authority permission for road works.
    """
    # <<< access_permit
    # @generated
    def __init__(self, permit_id='', expiration_date='', payment=0.0, date_effective='', application_number='', **kw_args):
        """ Initialises a new 'AccessPermit' instance.
        """
        # Permit identifier. 
        self.permit_id = permit_id
        # Permit expiration date. 
        self.expiration_date = expiration_date
        # Total cost of permit. 
        self.payment = payment
        # Date that permit became official. 
        self.date_effective = date_effective
        # Permit application number that is used by municipality, state, province, etc. 
        self.application_number = application_number
        

        super(AccessPermit, self).__init__(**kw_args)
    # >>> access_permit
        


class Appointment(ScheduledEvent):
    """ Meeting time and location.
    """
    # <<< appointment
    # @generated
    def __init__(self, remark='', call_ahead=False, meeting_interval=None, address=None, call_back=None, erp_persons=None, **kw_args):
        """ Initialises a new 'Appointment' instance.
        """
        # Information about the appointment. 
        self.remark = remark
        # True if requested to call customer when someone is about to arrive at their premises. 
        self.call_ahead = call_ahead
        
        self.meeting_interval = meeting_interval
        self.address = address
        self._call_back = None
        self.call_back = call_back
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons

        super(Appointment, self).__init__(**kw_args)
    # >>> appointment
        
    # <<< meeting_interval
    # @generated
    # Date and time reserved for appointment.
    meeting_interval = None
    # >>> meeting_interval

    # <<< address
    # @generated
    # Address for appointment.
    address = None
    # >>> address

    # <<< call_back
    # @generated
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
            if self not in self._call_back._appointments:
                self._call_back._appointments.append(self)

    call_back = property(get_call_back, set_call_back)
    # >>> call_back

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self._erp_persons:
            filtered = [q for q in p.appointments if q != self]
            self._erp_persons._appointments = filtered
        for r in value:
            if self not in r._appointments:
                r._appointments.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._appointments:
                obj._appointments.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._appointments:
                obj._appointments.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons



class Project(Document):
    """ A collection of related work. For construction projects and maintenance projects, multiple phases may be performed.
    """
    # <<< project
    # @generated
    def __init__(self, budget=0.0, works=None, business_case=None, erp_project_accounting=None, parent_project=None, sub_projects=None, requests=None, **kw_args):
        """ Initialises a new 'Project' instance.
        """
        # Overall project budget. 
        self.budget = budget
        
        self._works = []
        if works is None:
            self.works = []
        else:
            self.works = works
        self._business_case = None
        self.business_case = business_case
        self._erp_project_accounting = None
        self.erp_project_accounting = erp_project_accounting
        self._parent_project = None
        self.parent_project = parent_project
        self._sub_projects = []
        if sub_projects is None:
            self.sub_projects = []
        else:
            self.sub_projects = sub_projects
        self._requests = []
        if requests is None:
            self.requests = []
        else:
            self.requests = requests

        super(Project, self).__init__(**kw_args)
    # >>> project
        
    # <<< works
    # @generated
    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x.project = None
        for y in value:
            y.project = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._project = self
            if obj not in self._works:
                self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._project = None
            self._works.remove(obj)
    # >>> works

    # <<< business_case
    # @generated
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
            if self not in self._business_case._projects:
                self._business_case._projects.append(self)

    business_case = property(get_business_case, set_business_case)
    # >>> business_case

    # <<< erp_project_accounting
    # @generated
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
            if self not in self._erp_project_accounting._projects:
                self._erp_project_accounting._projects.append(self)

    erp_project_accounting = property(get_erp_project_accounting, set_erp_project_accounting)
    # >>> erp_project_accounting

    # <<< parent_project
    # @generated
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
            if self not in self._parent_project._sub_projects:
                self._parent_project._sub_projects.append(self)

    parent_project = property(get_parent_project, set_parent_project)
    # >>> parent_project

    # <<< sub_projects
    # @generated
    def get_sub_projects(self):
        """ 
        """
        return self._sub_projects

    def set_sub_projects(self, value):
        for x in self._sub_projects:
            x.parent_project = None
        for y in value:
            y.parent_project = self
        self._sub_projects = value
            
    sub_projects = property(get_sub_projects, set_sub_projects)
    
    def add_sub_projects(self, *sub_projects):
        for obj in sub_projects:
            obj._parent_project = self
            if obj not in self._sub_projects:
                self._sub_projects.append(obj)
        
    def remove_sub_projects(self, *sub_projects):
        for obj in sub_projects:
            obj._parent_project = None
            self._sub_projects.remove(obj)
    # >>> sub_projects

    # <<< requests
    # @generated
    def get_requests(self):
        """ 
        """
        return self._requests

    def set_requests(self, value):
        for p in self._requests:
            filtered = [q for q in p.projects if q != self]
            self._requests._projects = filtered
        for r in value:
            if self not in r._projects:
                r._projects.append(self)
        self._requests = value
            
    requests = property(get_requests, set_requests)
    
    def add_requests(self, *requests):
        for obj in requests:
            if self not in obj._projects:
                obj._projects.append(self)
            self._requests.append(obj)
        
    def remove_requests(self, *requests):
        for obj in requests:
            if self in obj._projects:
                obj._projects.remove(self)
            self._requests.remove(obj)
    # >>> requests



class DesignLocationCU(IdentifiedObject):
    """ Compatible unit at a given design location.
    """
    # <<< design_location_cu
    # @generated
    def __init__(self, cu_account='', cu_quantity=0, cu_usage='', energization_flag=False, year_removal='', cu_action='install', status=None, compatible_units=None, design_location=None, cugroups=None, work_tasks=None, condition_factors=None, designs=None, **kw_args):
        """ Initialises a new 'DesignLocationCU' instance.
        """
        # A code that helps direct accounting (capital, expense, or accounting treatment). 
        self.cu_account = cu_account
        # The quantity of the CU being assigned to this location. 
        self.cu_quantity = cu_quantity
        # As the same CU can be used for different purposes and accounting purposes, usage must be specified. Examples include: distribution, transmission, substation. 
        self.cu_usage = cu_usage
        # True if associated electrical equipment is intended to be energized while work is being performed. 
        self.energization_flag = energization_flag
        # Year when a CU that represents an asset is removed. 
        self.year_removal = year_removal
        # A code that instructs the crew what action to perform. Values are: "install", "abandon", "remove", "transfer"
        self.cu_action = cu_action
        
        self.status = status
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._design_location = None
        self.design_location = design_location
        self._cugroups = []
        if cugroups is None:
            self.cugroups = []
        else:
            self.cugroups = cugroups
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks
        self._condition_factors = []
        if condition_factors is None:
            self.condition_factors = []
        else:
            self.condition_factors = condition_factors
        self._designs = []
        if designs is None:
            self.designs = []
        else:
            self.designs = designs

        super(DesignLocationCU, self).__init__(**kw_args)
    # >>> design_location_cu
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.design_location_cus if q != self]
            self._compatible_units._design_location_cus = filtered
        for r in value:
            if self not in r._design_location_cus:
                r._design_location_cus.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._design_location_cus:
                obj._design_location_cus.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._design_location_cus:
                obj._design_location_cus.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< design_location
    # @generated
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
            if self not in self._design_location._design_location_cus:
                self._design_location._design_location_cus.append(self)

    design_location = property(get_design_location, set_design_location)
    # >>> design_location

    # <<< cugroups
    # @generated
    def get_cugroups(self):
        """ 
        """
        return self._cugroups

    def set_cugroups(self, value):
        for p in self._cugroups:
            filtered = [q for q in p.design_location_cus if q != self]
            self._cugroups._design_location_cus = filtered
        for r in value:
            if self not in r._design_location_cus:
                r._design_location_cus.append(self)
        self._cugroups = value
            
    cugroups = property(get_cugroups, set_cugroups)
    
    def add_cugroups(self, *cugroups):
        for obj in cugroups:
            if self not in obj._design_location_cus:
                obj._design_location_cus.append(self)
            self._cugroups.append(obj)
        
    def remove_cugroups(self, *cugroups):
        for obj in cugroups:
            if self in obj._design_location_cus:
                obj._design_location_cus.remove(self)
            self._cugroups.remove(obj)
    # >>> cugroups

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for p in self._work_tasks:
            filtered = [q for q in p.design_location_cus if q != self]
            self._work_tasks._design_location_cus = filtered
        for r in value:
            if self not in r._design_location_cus:
                r._design_location_cus.append(self)
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self not in obj._design_location_cus:
                obj._design_location_cus.append(self)
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self in obj._design_location_cus:
                obj._design_location_cus.remove(self)
            self._work_tasks.remove(obj)
    # >>> work_tasks

    # <<< condition_factors
    # @generated
    def get_condition_factors(self):
        """ 
        """
        return self._condition_factors

    def set_condition_factors(self, value):
        for p in self._condition_factors:
            filtered = [q for q in p.design_location_cus if q != self]
            self._condition_factors._design_location_cus = filtered
        for r in value:
            if self not in r._design_location_cus:
                r._design_location_cus.append(self)
        self._condition_factors = value
            
    condition_factors = property(get_condition_factors, set_condition_factors)
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self not in obj._design_location_cus:
                obj._design_location_cus.append(self)
            self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self in obj._design_location_cus:
                obj._design_location_cus.remove(self)
            self._condition_factors.remove(obj)
    # >>> condition_factors

    # <<< designs
    # @generated
    def get_designs(self):
        """ 
        """
        return self._designs

    def set_designs(self, value):
        for p in self._designs:
            filtered = [q for q in p.design_locations_cus if q != self]
            self._designs._design_locations_cus = filtered
        for r in value:
            if self not in r._design_locations_cus:
                r._design_locations_cus.append(self)
        self._designs = value
            
    designs = property(get_designs, set_designs)
    
    def add_designs(self, *designs):
        for obj in designs:
            if self not in obj._design_locations_cus:
                obj._design_locations_cus.append(self)
            self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
            if self in obj._design_locations_cus:
                obj._design_locations_cus.remove(self)
            self._designs.remove(obj)
    # >>> designs



class Assignment(Document):
    """ An assignment is given to an ErpPerson, Crew, Organisation, Equipment Item, Tool, etc. and may be used to perform Work, WorkTasks, Procedures, etc. TimeSchedules may be set up directly for Assignments or indirectly via the associated WorkTask. Note that these associations are all inherited through the recursive relationship on Document.
    """
    # <<< assignment
    # @generated
    def __init__(self, effective_date='', expiration_date='', crews=None, **kw_args):
        """ Initialises a new 'Assignment' instance.
        """
        # Date and time assignment became effective. 
        self.effective_date = effective_date
        # Date and time assignment expires. 
        self.expiration_date = expiration_date
        
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews

        super(Assignment, self).__init__(**kw_args)
    # >>> assignment
        
    # <<< crews
    # @generated
    def get_crews(self):
        """ All Crews having this Assignment.
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.assignments if q != self]
            self._crews._assignments = filtered
        for r in value:
            if self not in r._assignments:
                r._assignments.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._assignments:
                obj._assignments.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._assignments:
                obj._assignments.remove(self)
            self._crews.remove(obj)
    # >>> crews



class CUMaterialItem(IdentifiedObject):
    """ Compatible unit of a consumable supply item. For example, nuts, bolts, brackets, glue, etc.
    """
    # <<< cumaterial_item
    # @generated
    def __init__(self, corporate_code='', quantity=0, status=None, type_material=None, compatible_units=None, property_units=None, **kw_args):
        """ Initialises a new 'CUMaterialItem' instance.
        """
        # Code for material. 
        self.corporate_code = corporate_code
        # Quantity of the TypeMaterial for this CU, used to determine estimated costs based on a per unit cost or a cost per unit length specified in the TypeMaterial. 
        self.quantity = quantity
        
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._property_units = []
        if property_units is None:
            self.property_units = []
        else:
            self.property_units = property_units

        super(CUMaterialItem, self).__init__(**kw_args)
    # >>> cumaterial_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< type_material
    # @generated
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
            if self not in self._type_material._cumaterial_items:
                self._type_material._cumaterial_items.append(self)

    type_material = property(get_type_material, set_type_material)
    # >>> type_material

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.cumaterial_items if q != self]
            self._compatible_units._cumaterial_items = filtered
        for r in value:
            if self not in r._cumaterial_items:
                r._cumaterial_items.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._cumaterial_items:
                obj._cumaterial_items.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._cumaterial_items:
                obj._cumaterial_items.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< property_units
    # @generated
    def get_property_units(self):
        """ 
        """
        return self._property_units

    def set_property_units(self, value):
        for p in self._property_units:
            filtered = [q for q in p.cumaterial_items if q != self]
            self._property_units._cumaterial_items = filtered
        for r in value:
            if self not in r._cumaterial_items:
                r._cumaterial_items.append(self)
        self._property_units = value
            
    property_units = property(get_property_units, set_property_units)
    
    def add_property_units(self, *property_units):
        for obj in property_units:
            if self not in obj._cumaterial_items:
                obj._cumaterial_items.append(self)
            self._property_units.append(obj)
        
    def remove_property_units(self, *property_units):
        for obj in property_units:
            if self in obj._cumaterial_items:
                obj._cumaterial_items.remove(self)
            self._property_units.remove(obj)
    # >>> property_units



class WorkLocation(Location):
    """ Information about a particular location for various forms of work such as a one call request.
    """
    # <<< work_location
    # @generated
    def __init__(self, nearest_intersection='', subdivision='', block='', lot='', one_call_request=None, design_locations=None, **kw_args):
        """ Initialises a new 'WorkLocation' instance.
        """
        # The names of streets at the nearest intersection to work area. 
        self.nearest_intersection = nearest_intersection
        # Name, identifier, or description of the subdivision, if applicable, in which work is to occur. 
        self.subdivision = subdivision
        # Name, identifier, or description of the block, if applicable, in which work is to occur. 
        self.block = block
        # Name, identifier, or description of the lot, if applicable, in which work is to occur. 
        self.lot = lot
        
        self._one_call_request = None
        self.one_call_request = one_call_request
        self._design_locations = []
        if design_locations is None:
            self.design_locations = []
        else:
            self.design_locations = design_locations

        super(WorkLocation, self).__init__(**kw_args)
    # >>> work_location
        
    # <<< one_call_request
    # @generated
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
            if self not in self._one_call_request._work_locations:
                self._one_call_request._work_locations.append(self)

    one_call_request = property(get_one_call_request, set_one_call_request)
    # >>> one_call_request

    # <<< design_locations
    # @generated
    def get_design_locations(self):
        """ 
        """
        return self._design_locations

    def set_design_locations(self, value):
        for p in self._design_locations:
            filtered = [q for q in p.work_locations if q != self]
            self._design_locations._work_locations = filtered
        for r in value:
            if self not in r._work_locations:
                r._work_locations.append(self)
        self._design_locations = value
            
    design_locations = property(get_design_locations, set_design_locations)
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
            if self not in obj._work_locations:
                obj._work_locations.append(self)
            self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
            if self in obj._work_locations:
                obj._work_locations.remove(self)
            self._design_locations.remove(obj)
    # >>> design_locations



class PropertyUnit(IdentifiedObject):
    """ Unit of property for reporting purposes.
    """
    # <<< property_unit
    # @generated
    def __init__(self, activity_code='install', property_account='', accounting_usage='', status=None, work_cost_details=None, cumaterial_items=None, compatible_units=None, **kw_args):
        """ Initialises a new 'PropertyUnit' instance.
        """
        # Activity code identifies a specific and distinguishable work action. Values are: "install", "abandon", "remove", "transfer"
        self.activity_code = activity_code
        # Used for property record accounting. For example, in the USA, this would be a FERC account. 
        self.property_account = property_account
        # A code that identifies appropriate type of property accounts such as distribution, streetlgihts, communications. 
        self.accounting_usage = accounting_usage
        
        self.status = status
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details
        self._cumaterial_items = []
        if cumaterial_items is None:
            self.cumaterial_items = []
        else:
            self.cumaterial_items = cumaterial_items
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units

        super(PropertyUnit, self).__init__(**kw_args)
    # >>> property_unit
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for p in self._work_cost_details:
            filtered = [q for q in p.property_units if q != self]
            self._work_cost_details._property_units = filtered
        for r in value:
            if self not in r._property_units:
                r._property_units.append(self)
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            if self not in obj._property_units:
                obj._property_units.append(self)
            self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            if self in obj._property_units:
                obj._property_units.remove(self)
            self._work_cost_details.remove(obj)
    # >>> work_cost_details

    # <<< cumaterial_items
    # @generated
    def get_cumaterial_items(self):
        """ 
        """
        return self._cumaterial_items

    def set_cumaterial_items(self, value):
        for p in self._cumaterial_items:
            filtered = [q for q in p.property_units if q != self]
            self._cumaterial_items._property_units = filtered
        for r in value:
            if self not in r._property_units:
                r._property_units.append(self)
        self._cumaterial_items = value
            
    cumaterial_items = property(get_cumaterial_items, set_cumaterial_items)
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            if self not in obj._property_units:
                obj._property_units.append(self)
            self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            if self in obj._property_units:
                obj._property_units.remove(self)
            self._cumaterial_items.remove(obj)
    # >>> cumaterial_items

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x.property_unit = None
        for y in value:
            y.property_unit = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._property_unit = self
            if obj not in self._compatible_units:
                self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._property_unit = None
            self._compatible_units.remove(obj)
    # >>> compatible_units



class CUContractorItem(IdentifiedObject):
    """ Compatible unit contractor item.
    """
    # <<< cucontractor_item
    # @generated
    def __init__(self, activity_code='', bid_amount=0.0, status=None, compatible_units=None, **kw_args):
        """ Initialises a new 'CUContractorItem' instance.
        """
        # Activity code identifies a specific and distinguishable unit of work. 
        self.activity_code = activity_code
        # The amount that a given contractor will charge for performing this unit of work. 
        self.bid_amount = bid_amount
        
        self.status = status
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units

        super(CUContractorItem, self).__init__(**kw_args)
    # >>> cucontractor_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.cucontractor_items if q != self]
            self._compatible_units._cucontractor_items = filtered
        for r in value:
            if self not in r._cucontractor_items:
                r._cucontractor_items.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._cucontractor_items:
                obj._cucontractor_items.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._cucontractor_items:
                obj._cucontractor_items.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units



class MaintenanceDataSet(ProcedureDataSet):
    """ The result of a maintenance activity, a type of Procedure, for a given attribute of an asset is documentated in an MaintenanceDataSet.
    """
    # <<< maintenance_data_set
    # @generated
    def __init__(self, condition_before='', maint_code='', maint_date='', condition_after='', **kw_args):
        """ Initialises a new 'MaintenanceDataSet' instance.
        """
        # Description of the condition of the asset just prior to maintenance being performed. 
        self.condition_before = condition_before
        # Code for the type of maintenance performed. 
        self.maint_code = maint_code
        # Date and time maintenance procedure was completed. 
        self.maint_date = maint_date
        # Condition of asset just following maintenance procedure. 
        self.condition_after = condition_after
        

        super(MaintenanceDataSet, self).__init__(**kw_args)
    # >>> maintenance_data_set
        


class WorkFlowStep(IdentifiedObject):
    """ A pre-defined set of work steps for a given type of work.
    """
    # <<< work_flow_step
    # @generated
    def __init__(self, sequence_number=0, status=None, work_tasks=None, work=None, **kw_args):
        """ Initialises a new 'WorkFlowStep' instance.
        """
        # Used to define dependencies of each work flow step, which is for the instance of WorkTask associated with a given instance of WorkFlow. 
        self.sequence_number = sequence_number
        
        self.status = status
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks
        self._work = None
        self.work = work

        super(WorkFlowStep, self).__init__(**kw_args)
    # >>> work_flow_step
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x.work_flow_step = None
        for y in value:
            y.work_flow_step = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._work_flow_step = self
            if obj not in self._work_tasks:
                self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._work_flow_step = None
            self._work_tasks.remove(obj)
    # >>> work_tasks

    # <<< work
    # @generated
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
            if self not in self._work._work_flow_steps:
                self._work._work_flow_steps.append(self)

    work = property(get_work, set_work)
    # >>> work



class Capability(IdentifiedObject):
    """ Capabilities of a crew.
    """
    # <<< capability
    # @generated
    def __init__(self, performance_factor='', category='', validity_interval=None, status=None, crew=None, crafts=None, work_tasks=None, **kw_args):
        """ Initialises a new 'Capability' instance.
        """
        # Capability performance factor. 
        self.performance_factor = performance_factor
        # Category by utility's work management standards and practices. 
        self.category = category
        
        self.validity_interval = validity_interval
        self.status = status
        self._crew = None
        self.crew = crew
        self._crafts = []
        if crafts is None:
            self.crafts = []
        else:
            self.crafts = crafts
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks

        super(Capability, self).__init__(**kw_args)
    # >>> capability
        
    # <<< validity_interval
    # @generated
    # Date and time interval for which this capability is valid (when it became effective and when it expires).
    validity_interval = None
    # >>> validity_interval

    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< crew
    # @generated
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
            if self not in self._crew._capabilities:
                self._crew._capabilities.append(self)

    crew = property(get_crew, set_crew)
    # >>> crew

    # <<< crafts
    # @generated
    def get_crafts(self):
        """ 
        """
        return self._crafts

    def set_crafts(self, value):
        for p in self._crafts:
            filtered = [q for q in p.capabilities if q != self]
            self._crafts._capabilities = filtered
        for r in value:
            if self not in r._capabilities:
                r._capabilities.append(self)
        self._crafts = value
            
    crafts = property(get_crafts, set_crafts)
    
    def add_crafts(self, *crafts):
        for obj in crafts:
            if self not in obj._capabilities:
                obj._capabilities.append(self)
            self._crafts.append(obj)
        
    def remove_crafts(self, *crafts):
        for obj in crafts:
            if self in obj._capabilities:
                obj._capabilities.remove(self)
            self._crafts.remove(obj)
    # >>> crafts

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for p in self._work_tasks:
            filtered = [q for q in p.capabilities if q != self]
            self._work_tasks._capabilities = filtered
        for r in value:
            if self not in r._capabilities:
                r._capabilities.append(self)
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self not in obj._capabilities:
                obj._capabilities.append(self)
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self in obj._capabilities:
                obj._capabilities.remove(self)
            self._work_tasks.remove(obj)
    # >>> work_tasks



class InspectionDataSet(ProcedureDataSet):
    """ Documents the result of one inspection, a type of Procedure, for a given attribute of an asset.
    """
    # <<< inspection_data_set
    # @generated
    def __init__(self, inspect_date='', location_condition='', according_to_schedules=None, **kw_args):
        """ Initialises a new 'InspectionDataSet' instance.
        """
        # Date and time this inspections was completed. 
        self.inspect_date = inspect_date
        # A general description of the conditions of the location where the asset resides. 
        self.location_condition = location_condition
        
        self._according_to_schedules = []
        if according_to_schedules is None:
            self.according_to_schedules = []
        else:
            self.according_to_schedules = according_to_schedules

        super(InspectionDataSet, self).__init__(**kw_args)
    # >>> inspection_data_set
        
    # <<< according_to_schedules
    # @generated
    def get_according_to_schedules(self):
        """ 
        """
        return self._according_to_schedules

    def set_according_to_schedules(self, value):
        for x in self._according_to_schedules:
            x.for_inspection_data_set = None
        for y in value:
            y.for_inspection_data_set = self
        self._according_to_schedules = value
            
    according_to_schedules = property(get_according_to_schedules, set_according_to_schedules)
    
    def add_according_to_schedules(self, *according_to_schedules):
        for obj in according_to_schedules:
            obj._for_inspection_data_set = self
            if obj not in self._according_to_schedules:
                self._according_to_schedules.append(obj)
        
    def remove_according_to_schedules(self, *according_to_schedules):
        for obj in according_to_schedules:
            obj._for_inspection_data_set = None
            self._according_to_schedules.remove(obj)
    # >>> according_to_schedules



class CULaborItem(IdentifiedObject):
    """ Compatible unit labor item.
    """
    # <<< culabor_item
    # @generated
    def __init__(self, labor_duration=0.0, activity_code='', labor_rate=0.0, status=None, qualification_requirements=None, compatible_units=None, culabor_code=None, **kw_args):
        """ Initialises a new 'CULaborItem' instance.
        """
        # Estimated time to perform work. 
        self.labor_duration = labor_duration
        # Activity code identifies a specific and distinguishable unit of work. 
        self.activity_code = activity_code
        # The labor rate applied for work. 
        self.labor_rate = labor_rate
        
        self.status = status
        self._qualification_requirements = []
        if qualification_requirements is None:
            self.qualification_requirements = []
        else:
            self.qualification_requirements = qualification_requirements
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._culabor_code = None
        self.culabor_code = culabor_code

        super(CULaborItem, self).__init__(**kw_args)
    # >>> culabor_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< qualification_requirements
    # @generated
    def get_qualification_requirements(self):
        """ 
        """
        return self._qualification_requirements

    def set_qualification_requirements(self, value):
        for p in self._qualification_requirements:
            filtered = [q for q in p.culabor_items if q != self]
            self._qualification_requirements._culabor_items = filtered
        for r in value:
            if self not in r._culabor_items:
                r._culabor_items.append(self)
        self._qualification_requirements = value
            
    qualification_requirements = property(get_qualification_requirements, set_qualification_requirements)
    
    def add_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self not in obj._culabor_items:
                obj._culabor_items.append(self)
            self._qualification_requirements.append(obj)
        
    def remove_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self in obj._culabor_items:
                obj._culabor_items.remove(self)
            self._qualification_requirements.remove(obj)
    # >>> qualification_requirements

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.culabor_items if q != self]
            self._compatible_units._culabor_items = filtered
        for r in value:
            if self not in r._culabor_items:
                r._culabor_items.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._culabor_items:
                obj._culabor_items.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._culabor_items:
                obj._culabor_items.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< culabor_code
    # @generated
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
            if self not in self._culabor_code._culabor_items:
                self._culabor_code._culabor_items.append(self)

    culabor_code = property(get_culabor_code, set_culabor_code)
    # >>> culabor_code



class CompatibleUnit(Document):
    """ A pre-planned job model containing labor, material, and accounting requirements for standardized job planning.
    """
    # <<< compatible_unit
    # @generated
    def __init__(self, quantity='', est_cost=0.0, cuwork_equipment_items=None, procedures=None, cugroup=None, cuassets=None, cost_type=None, cucontractor_items=None, culabor_items=None, cuallowable_action=None, property_unit=None, design_location_cus=None, cumaterial_items=None, **kw_args):
        """ Initialises a new 'CompatibleUnit' instance.
        """
        # The quantity, unit of measure, and multiplier at the CU level that applies to the materials. 
        self.quantity = quantity
        # Estimated total cost for perfoming CU. 
        self.est_cost = est_cost
        
        self._cuwork_equipment_items = []
        if cuwork_equipment_items is None:
            self.cuwork_equipment_items = []
        else:
            self.cuwork_equipment_items = cuwork_equipment_items
        self._procedures = []
        if procedures is None:
            self.procedures = []
        else:
            self.procedures = procedures
        self._cugroup = None
        self.cugroup = cugroup
        self._cuassets = []
        if cuassets is None:
            self.cuassets = []
        else:
            self.cuassets = cuassets
        self._cost_type = None
        self.cost_type = cost_type
        self._cucontractor_items = []
        if cucontractor_items is None:
            self.cucontractor_items = []
        else:
            self.cucontractor_items = cucontractor_items
        self._culabor_items = []
        if culabor_items is None:
            self.culabor_items = []
        else:
            self.culabor_items = culabor_items
        self._cuallowable_action = None
        self.cuallowable_action = cuallowable_action
        self._property_unit = None
        self.property_unit = property_unit
        self._design_location_cus = []
        if design_location_cus is None:
            self.design_location_cus = []
        else:
            self.design_location_cus = design_location_cus
        self._cumaterial_items = []
        if cumaterial_items is None:
            self.cumaterial_items = []
        else:
            self.cumaterial_items = cumaterial_items

        super(CompatibleUnit, self).__init__(**kw_args)
    # >>> compatible_unit
        
    # <<< cuwork_equipment_items
    # @generated
    def get_cuwork_equipment_items(self):
        """ 
        """
        return self._cuwork_equipment_items

    def set_cuwork_equipment_items(self, value):
        for p in self._cuwork_equipment_items:
            filtered = [q for q in p.compatible_units if q != self]
            self._cuwork_equipment_items._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._cuwork_equipment_items = value
            
    cuwork_equipment_items = property(get_cuwork_equipment_items, set_cuwork_equipment_items)
    
    def add_cuwork_equipment_items(self, *cuwork_equipment_items):
        for obj in cuwork_equipment_items:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._cuwork_equipment_items.append(obj)
        
    def remove_cuwork_equipment_items(self, *cuwork_equipment_items):
        for obj in cuwork_equipment_items:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._cuwork_equipment_items.remove(obj)
    # >>> cuwork_equipment_items

    # <<< procedures
    # @generated
    def get_procedures(self):
        """ 
        """
        return self._procedures

    def set_procedures(self, value):
        for p in self._procedures:
            filtered = [q for q in p.compatible_units if q != self]
            self._procedures._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._procedures = value
            
    procedures = property(get_procedures, set_procedures)
    
    def add_procedures(self, *procedures):
        for obj in procedures:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._procedures.append(obj)
        
    def remove_procedures(self, *procedures):
        for obj in procedures:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._procedures.remove(obj)
    # >>> procedures

    # <<< cugroup
    # @generated
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
            if self not in self._cugroup._compatible_units:
                self._cugroup._compatible_units.append(self)

    cugroup = property(get_cugroup, set_cugroup)
    # >>> cugroup

    # <<< cuassets
    # @generated
    def get_cuassets(self):
        """ 
        """
        return self._cuassets

    def set_cuassets(self, value):
        for p in self._cuassets:
            filtered = [q for q in p.compatible_units if q != self]
            self._cuassets._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._cuassets = value
            
    cuassets = property(get_cuassets, set_cuassets)
    
    def add_cuassets(self, *cuassets):
        for obj in cuassets:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._cuassets.append(obj)
        
    def remove_cuassets(self, *cuassets):
        for obj in cuassets:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._cuassets.remove(obj)
    # >>> cuassets

    # <<< cost_type
    # @generated
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
            if self not in self._cost_type._compatible_units:
                self._cost_type._compatible_units.append(self)

    cost_type = property(get_cost_type, set_cost_type)
    # >>> cost_type

    # <<< cucontractor_items
    # @generated
    def get_cucontractor_items(self):
        """ 
        """
        return self._cucontractor_items

    def set_cucontractor_items(self, value):
        for p in self._cucontractor_items:
            filtered = [q for q in p.compatible_units if q != self]
            self._cucontractor_items._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._cucontractor_items = value
            
    cucontractor_items = property(get_cucontractor_items, set_cucontractor_items)
    
    def add_cucontractor_items(self, *cucontractor_items):
        for obj in cucontractor_items:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._cucontractor_items.append(obj)
        
    def remove_cucontractor_items(self, *cucontractor_items):
        for obj in cucontractor_items:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._cucontractor_items.remove(obj)
    # >>> cucontractor_items

    # <<< culabor_items
    # @generated
    def get_culabor_items(self):
        """ 
        """
        return self._culabor_items

    def set_culabor_items(self, value):
        for p in self._culabor_items:
            filtered = [q for q in p.compatible_units if q != self]
            self._culabor_items._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._culabor_items = value
            
    culabor_items = property(get_culabor_items, set_culabor_items)
    
    def add_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._culabor_items.append(obj)
        
    def remove_culabor_items(self, *culabor_items):
        for obj in culabor_items:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._culabor_items.remove(obj)
    # >>> culabor_items

    # <<< cuallowable_action
    # @generated
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
            if self not in self._cuallowable_action._compatible_units:
                self._cuallowable_action._compatible_units.append(self)

    cuallowable_action = property(get_cuallowable_action, set_cuallowable_action)
    # >>> cuallowable_action

    # <<< property_unit
    # @generated
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
            if self not in self._property_unit._compatible_units:
                self._property_unit._compatible_units.append(self)

    property_unit = property(get_property_unit, set_property_unit)
    # >>> property_unit

    # <<< design_location_cus
    # @generated
    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for p in self._design_location_cus:
            filtered = [q for q in p.compatible_units if q != self]
            self._design_location_cus._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._design_location_cus.remove(obj)
    # >>> design_location_cus

    # <<< cumaterial_items
    # @generated
    def get_cumaterial_items(self):
        """ 
        """
        return self._cumaterial_items

    def set_cumaterial_items(self, value):
        for p in self._cumaterial_items:
            filtered = [q for q in p.compatible_units if q != self]
            self._cumaterial_items._compatible_units = filtered
        for r in value:
            if self not in r._compatible_units:
                r._compatible_units.append(self)
        self._cumaterial_items = value
            
    cumaterial_items = property(get_cumaterial_items, set_cumaterial_items)
    
    def add_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            if self not in obj._compatible_units:
                obj._compatible_units.append(self)
            self._cumaterial_items.append(obj)
        
    def remove_cumaterial_items(self, *cumaterial_items):
        for obj in cumaterial_items:
            if self in obj._compatible_units:
                obj._compatible_units.remove(self)
            self._cumaterial_items.remove(obj)
    # >>> cumaterial_items



class MaterialItem(IdentifiedObject):
    """ The physical consumable supply used for work and other purposes. It includes items such as nuts, bolts, brackets, glue, etc.
    """
    # <<< material_item
    # @generated
    def __init__(self, quantity=0, external_ref_id='', cost_description='', cost_type='', material_code='', account='', actual_cost=0.0, status=None, type_material=None, erp_inventory_counts=None, usages=None, design_location=None, work_cost_detail=None, erp_rec_delv_line_items=None, erp_poline_items=None, work_task=None, **kw_args):
        """ Initialises a new 'MaterialItem' instance.
        """
        # The quantity of material used. 
        self.quantity = quantity
        # External reference identifier for this actual material item such as a purchase order number, a serial number, etc. 
        self.external_ref_id = external_ref_id
        # Description of the cost. 
        self.cost_description = cost_description
        # The category of cost to which this Material Item belongs. 
        self.cost_type = cost_type
        # Code for material. 
        self.material_code = material_code
        # The account to which this actual material item is charged. 
        self.account = account
        # The actual cost of this particular material in this particular quantity. 
        self.actual_cost = actual_cost
        
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._erp_inventory_counts = []
        if erp_inventory_counts is None:
            self.erp_inventory_counts = []
        else:
            self.erp_inventory_counts = erp_inventory_counts
        self._usages = []
        if usages is None:
            self.usages = []
        else:
            self.usages = usages
        self._design_location = None
        self.design_location = design_location
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._erp_rec_delv_line_items = []
        if erp_rec_delv_line_items is None:
            self.erp_rec_delv_line_items = []
        else:
            self.erp_rec_delv_line_items = erp_rec_delv_line_items
        self._erp_poline_items = []
        if erp_poline_items is None:
            self.erp_poline_items = []
        else:
            self.erp_poline_items = erp_poline_items
        self._work_task = None
        self.work_task = work_task

        super(MaterialItem, self).__init__(**kw_args)
    # >>> material_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< type_material
    # @generated
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
            if self not in self._type_material._material_items:
                self._type_material._material_items.append(self)

    type_material = property(get_type_material, set_type_material)
    # >>> type_material

    # <<< erp_inventory_counts
    # @generated
    def get_erp_inventory_counts(self):
        """ 
        """
        return self._erp_inventory_counts

    def set_erp_inventory_counts(self, value):
        for x in self._erp_inventory_counts:
            x.material_item = None
        for y in value:
            y.material_item = self
        self._erp_inventory_counts = value
            
    erp_inventory_counts = property(get_erp_inventory_counts, set_erp_inventory_counts)
    
    def add_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._material_item = self
            if obj not in self._erp_inventory_counts:
                self._erp_inventory_counts.append(obj)
        
    def remove_erp_inventory_counts(self, *erp_inventory_counts):
        for obj in erp_inventory_counts:
            obj._material_item = None
            self._erp_inventory_counts.remove(obj)
    # >>> erp_inventory_counts

    # <<< usages
    # @generated
    def get_usages(self):
        """ 
        """
        return self._usages

    def set_usages(self, value):
        for x in self._usages:
            x.material_item = None
        for y in value:
            y.material_item = self
        self._usages = value
            
    usages = property(get_usages, set_usages)
    
    def add_usages(self, *usages):
        for obj in usages:
            obj._material_item = self
            if obj not in self._usages:
                self._usages.append(obj)
        
    def remove_usages(self, *usages):
        for obj in usages:
            obj._material_item = None
            self._usages.remove(obj)
    # >>> usages

    # <<< design_location
    # @generated
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
            if self not in self._design_location._material_items:
                self._design_location._material_items.append(self)

    design_location = property(get_design_location, set_design_location)
    # >>> design_location

    # <<< work_cost_detail
    # @generated
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
            if self not in self._work_cost_detail._material_items:
                self._work_cost_detail._material_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)
    # >>> work_cost_detail

    # <<< erp_rec_delv_line_items
    # @generated
    def get_erp_rec_delv_line_items(self):
        """ 
        """
        return self._erp_rec_delv_line_items

    def set_erp_rec_delv_line_items(self, value):
        for p in self._erp_rec_delv_line_items:
            filtered = [q for q in p.material_items if q != self]
            self._erp_rec_delv_line_items._material_items = filtered
        for r in value:
            if self not in r._material_items:
                r._material_items.append(self)
        self._erp_rec_delv_line_items = value
            
    erp_rec_delv_line_items = property(get_erp_rec_delv_line_items, set_erp_rec_delv_line_items)
    
    def add_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
            if self not in obj._material_items:
                obj._material_items.append(self)
            self._erp_rec_delv_line_items.append(obj)
        
    def remove_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
            if self in obj._material_items:
                obj._material_items.remove(self)
            self._erp_rec_delv_line_items.remove(obj)
    # >>> erp_rec_delv_line_items

    # <<< erp_poline_items
    # @generated
    def get_erp_poline_items(self):
        """ 
        """
        return self._erp_poline_items

    def set_erp_poline_items(self, value):
        for x in self._erp_poline_items:
            x.material_item = None
        for y in value:
            y.material_item = self
        self._erp_poline_items = value
            
    erp_poline_items = property(get_erp_poline_items, set_erp_poline_items)
    
    def add_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._material_item = self
            if obj not in self._erp_poline_items:
                self._erp_poline_items.append(obj)
        
    def remove_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._material_item = None
            self._erp_poline_items.remove(obj)
    # >>> erp_poline_items

    # <<< work_task
    # @generated
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
            if self not in self._work_task._material_items:
                self._work_task._material_items.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task



class OneCallRequest(Document):
    """ A request for other utilities to mark their underground facilities prior to commencement of construction and/or maintenance.
    """
    # <<< one_call_request
    # @generated
    def __init__(self, explosives_used=False, marked_indicator=False, marking_instruction='', work_locations=None, **kw_args):
        """ Initialises a new 'OneCallRequest' instance.
        """
        # True if explosives have been or are planned to be used. 
        self.explosives_used = explosives_used
        # True if work location has been marked, for example for a dig area. 
        self.marked_indicator = marked_indicator
        # Instructions for marking a dig area, if applicable. 
        self.marking_instruction = marking_instruction
        
        self._work_locations = []
        if work_locations is None:
            self.work_locations = []
        else:
            self.work_locations = work_locations

        super(OneCallRequest, self).__init__(**kw_args)
    # >>> one_call_request
        
    # <<< work_locations
    # @generated
    def get_work_locations(self):
        """ 
        """
        return self._work_locations

    def set_work_locations(self, value):
        for x in self._work_locations:
            x.one_call_request = None
        for y in value:
            y.one_call_request = self
        self._work_locations = value
            
    work_locations = property(get_work_locations, set_work_locations)
    
    def add_work_locations(self, *work_locations):
        for obj in work_locations:
            obj._one_call_request = self
            if obj not in self._work_locations:
                self._work_locations.append(obj)
        
    def remove_work_locations(self, *work_locations):
        for obj in work_locations:
            obj._one_call_request = None
            self._work_locations.remove(obj)
    # >>> work_locations



class WorkCostSummary(Document):
    """ A roll up by cost category for the entire cost of a work order. For example, total labor.
    """
    # <<< work_cost_summary
    # @generated
    def __init__(self, work_cost_detail=None, **kw_args):
        """ Initialises a new 'WorkCostSummary' instance.
        """
        
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail

        super(WorkCostSummary, self).__init__(**kw_args)
    # >>> work_cost_summary
        
    # <<< work_cost_detail
    # @generated
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
    # >>> work_cost_detail



class WorkStatusEntry(ActivityRecord):
    """ A type of ActivityRecord that records information about the status of an item, such as a Work or WorkTask, at a point in time.
    """
    # <<< work_status_entry
    # @generated
    def __init__(self, percent_complete=0.0, **kw_args):
        """ Initialises a new 'WorkStatusEntry' instance.
        """
        # Estimated percentage of completion of this individual work task or overall work order. 
        self.percent_complete = percent_complete
        

        super(WorkStatusEntry, self).__init__(**kw_args)
    # >>> work_status_entry
        


class MiscCostItem(IdentifiedObject):
    """ Various cost items that are not associated with compatible units. Examples include rental equipment, labor, materials, contractor costs, permits - anything not covered in a CU.
    """
    # <<< misc_cost_item
    # @generated
    def __init__(self, account='', external_ref_id='', cost_per_unit=0.0, cost_type='', quantity=0, status=None, work_task=None, design_location=None, work_cost_detail=None, **kw_args):
        """ Initialises a new 'MiscCostItem' instance.
        """
        # This drives the accounting treatment for this misc. item. 
        self.account = account
        # External Reference ID (e.g. PO#, Serial #) 
        self.external_ref_id = external_ref_id
        # The cost per unit for this misc. item. 
        self.cost_per_unit = cost_per_unit
        # The cost category for accounting, such as material, labor, vehicle, contractor, equipment, overhead. 
        self.cost_type = cost_type
        # The quantity of the misc. item being assigned to this location. 
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
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_task
    # @generated
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
            if self not in self._work_task._misc_cost_items:
                self._work_task._misc_cost_items.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task

    # <<< design_location
    # @generated
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
            if self not in self._design_location._misc_cost_items:
                self._design_location._misc_cost_items.append(self)

    design_location = property(get_design_location, set_design_location)
    # >>> design_location

    # <<< work_cost_detail
    # @generated
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
            if self not in self._work_cost_detail._misc_cost_items:
                self._work_cost_detail._misc_cost_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)
    # >>> work_cost_detail



class Crew(IdentifiedObject):
    """ A crew is a group of people (ErpPersons) with specific skills, tools, and vehicles.
    """
    # <<< crew
    # @generated
    def __init__(self, category='', work_tasks=None, vehicles=None, capabilities=None, route=None, work_equipment_assets=None, shift_patterns=None, switching_schedules=None, tools=None, outage_steps=None, locations=None, crew_members=None, organisations=None, assignments=None, **kw_args):
        """ Initialises a new 'Crew' instance.
        """
        # Category by utility's work management standards and practices. 
        self.category = category
        
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks
        self._vehicles = []
        if vehicles is None:
            self.vehicles = []
        else:
            self.vehicles = vehicles
        self._capabilities = []
        if capabilities is None:
            self.capabilities = []
        else:
            self.capabilities = capabilities
        self._route = None
        self.route = route
        self._work_equipment_assets = []
        if work_equipment_assets is None:
            self.work_equipment_assets = []
        else:
            self.work_equipment_assets = work_equipment_assets
        self._shift_patterns = []
        if shift_patterns is None:
            self.shift_patterns = []
        else:
            self.shift_patterns = shift_patterns
        self._switching_schedules = []
        if switching_schedules is None:
            self.switching_schedules = []
        else:
            self.switching_schedules = switching_schedules
        self._tools = []
        if tools is None:
            self.tools = []
        else:
            self.tools = tools
        self._outage_steps = []
        if outage_steps is None:
            self.outage_steps = []
        else:
            self.outage_steps = outage_steps
        self._locations = []
        if locations is None:
            self.locations = []
        else:
            self.locations = locations
        self._crew_members = []
        if crew_members is None:
            self.crew_members = []
        else:
            self.crew_members = crew_members
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations
        self._assignments = []
        if assignments is None:
            self.assignments = []
        else:
            self.assignments = assignments

        super(Crew, self).__init__(**kw_args)
    # >>> crew
        
    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ All WorkTasks this Crew participates in.
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for p in self._work_tasks:
            filtered = [q for q in p.crews if q != self]
            self._work_tasks._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self not in obj._crews:
                obj._crews.append(self)
            self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            if self in obj._crews:
                obj._crews.remove(self)
            self._work_tasks.remove(obj)
    # >>> work_tasks

    # <<< vehicles
    # @generated
    def get_vehicles(self):
        """ 
        """
        return self._vehicles

    def set_vehicles(self, value):
        for x in self._vehicles:
            x.crew = None
        for y in value:
            y.crew = self
        self._vehicles = value
            
    vehicles = property(get_vehicles, set_vehicles)
    
    def add_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._crew = self
            if obj not in self._vehicles:
                self._vehicles.append(obj)
        
    def remove_vehicles(self, *vehicles):
        for obj in vehicles:
            obj._crew = None
            self._vehicles.remove(obj)
    # >>> vehicles

    # <<< capabilities
    # @generated
    def get_capabilities(self):
        """ 
        """
        return self._capabilities

    def set_capabilities(self, value):
        for x in self._capabilities:
            x.crew = None
        for y in value:
            y.crew = self
        self._capabilities = value
            
    capabilities = property(get_capabilities, set_capabilities)
    
    def add_capabilities(self, *capabilities):
        for obj in capabilities:
            obj._crew = self
            if obj not in self._capabilities:
                self._capabilities.append(obj)
        
    def remove_capabilities(self, *capabilities):
        for obj in capabilities:
            obj._crew = None
            self._capabilities.remove(obj)
    # >>> capabilities

    # <<< route
    # @generated
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
            if self not in self._route._crews:
                self._route._crews.append(self)

    route = property(get_route, set_route)
    # >>> route

    # <<< work_equipment_assets
    # @generated
    def get_work_equipment_assets(self):
        """ 
        """
        return self._work_equipment_assets

    def set_work_equipment_assets(self, value):
        for x in self._work_equipment_assets:
            x.crew = None
        for y in value:
            y.crew = self
        self._work_equipment_assets = value
            
    work_equipment_assets = property(get_work_equipment_assets, set_work_equipment_assets)
    
    def add_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._crew = self
            if obj not in self._work_equipment_assets:
                self._work_equipment_assets.append(obj)
        
    def remove_work_equipment_assets(self, *work_equipment_assets):
        for obj in work_equipment_assets:
            obj._crew = None
            self._work_equipment_assets.remove(obj)
    # >>> work_equipment_assets

    # <<< shift_patterns
    # @generated
    def get_shift_patterns(self):
        """ 
        """
        return self._shift_patterns

    def set_shift_patterns(self, value):
        for p in self._shift_patterns:
            filtered = [q for q in p.crews if q != self]
            self._shift_patterns._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._shift_patterns = value
            
    shift_patterns = property(get_shift_patterns, set_shift_patterns)
    
    def add_shift_patterns(self, *shift_patterns):
        for obj in shift_patterns:
            if self not in obj._crews:
                obj._crews.append(self)
            self._shift_patterns.append(obj)
        
    def remove_shift_patterns(self, *shift_patterns):
        for obj in shift_patterns:
            if self in obj._crews:
                obj._crews.remove(self)
            self._shift_patterns.remove(obj)
    # >>> shift_patterns

    # <<< switching_schedules
    # @generated
    def get_switching_schedules(self):
        """ All SwitchingSchedules executed by this Crew.
        """
        return self._switching_schedules

    def set_switching_schedules(self, value):
        for p in self._switching_schedules:
            filtered = [q for q in p.crews if q != self]
            self._switching_schedules._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._switching_schedules = value
            
    switching_schedules = property(get_switching_schedules, set_switching_schedules)
    
    def add_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            if self not in obj._crews:
                obj._crews.append(self)
            self._switching_schedules.append(obj)
        
    def remove_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            if self in obj._crews:
                obj._crews.remove(self)
            self._switching_schedules.remove(obj)
    # >>> switching_schedules

    # <<< tools
    # @generated
    def get_tools(self):
        """ 
        """
        return self._tools

    def set_tools(self, value):
        for x in self._tools:
            x.crew = None
        for y in value:
            y.crew = self
        self._tools = value
            
    tools = property(get_tools, set_tools)
    
    def add_tools(self, *tools):
        for obj in tools:
            obj._crew = self
            if obj not in self._tools:
                self._tools.append(obj)
        
    def remove_tools(self, *tools):
        for obj in tools:
            obj._crew = None
            self._tools.remove(obj)
    # >>> tools

    # <<< outage_steps
    # @generated
    def get_outage_steps(self):
        """ 
        """
        return self._outage_steps

    def set_outage_steps(self, value):
        for p in self._outage_steps:
            filtered = [q for q in p.crews if q != self]
            self._outage_steps._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._outage_steps = value
            
    outage_steps = property(get_outage_steps, set_outage_steps)
    
    def add_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            if self not in obj._crews:
                obj._crews.append(self)
            self._outage_steps.append(obj)
        
    def remove_outage_steps(self, *outage_steps):
        for obj in outage_steps:
            if self in obj._crews:
                obj._crews.remove(self)
            self._outage_steps.remove(obj)
    # >>> outage_steps

    # <<< locations
    # @generated
    def get_locations(self):
        """ 
        """
        return self._locations

    def set_locations(self, value):
        for p in self._locations:
            filtered = [q for q in p.crews if q != self]
            self._locations._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._locations = value
            
    locations = property(get_locations, set_locations)
    
    def add_locations(self, *locations):
        for obj in locations:
            if self not in obj._crews:
                obj._crews.append(self)
            self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
            if self in obj._crews:
                obj._crews.remove(self)
            self._locations.remove(obj)
    # >>> locations

    # <<< crew_members
    # @generated
    def get_crew_members(self):
        """ All ErpPersons that are members of this Crew.
        """
        return self._crew_members

    def set_crew_members(self, value):
        for p in self._crew_members:
            filtered = [q for q in p.crews if q != self]
            self._crew_members._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._crew_members = value
            
    crew_members = property(get_crew_members, set_crew_members)
    
    def add_crew_members(self, *crew_members):
        for obj in crew_members:
            if self not in obj._crews:
                obj._crews.append(self)
            self._crew_members.append(obj)
        
    def remove_crew_members(self, *crew_members):
        for obj in crew_members:
            if self in obj._crews:
                obj._crews.remove(self)
            self._crew_members.remove(obj)
    # >>> crew_members

    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self._organisations:
            filtered = [q for q in p.crews if q != self]
            self._organisations._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._crews:
                obj._crews.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._crews:
                obj._crews.remove(self)
            self._organisations.remove(obj)
    # >>> organisations

    # <<< assignments
    # @generated
    def get_assignments(self):
        """ All Assignments for this Crew.
        """
        return self._assignments

    def set_assignments(self, value):
        for p in self._assignments:
            filtered = [q for q in p.crews if q != self]
            self._assignments._crews = filtered
        for r in value:
            if self not in r._crews:
                r._crews.append(self)
        self._assignments = value
            
    assignments = property(get_assignments, set_assignments)
    
    def add_assignments(self, *assignments):
        for obj in assignments:
            if self not in obj._crews:
                obj._crews.append(self)
            self._assignments.append(obj)
        
    def remove_assignments(self, *assignments):
        for obj in assignments:
            if self in obj._crews:
                obj._crews.remove(self)
            self._assignments.remove(obj)
    # >>> assignments



class WorkCostDetail(Document):
    """ A collection of all of the individual cost items collected from multiple sources.
    """
    # <<< work_cost_detail
    # @generated
    def __init__(self, debit_flag=False, type_work_cost='', amount=0.0, transaction_date='', erp_project_accounting=None, design=None, material_items=None, work_cost_summary=None, overhead_cost=None, labor_items=None, works=None, misc_cost_items=None, equipment_items=None, cost_type=None, contractor_items=None, work_task=None, property_units=None, **kw_args):
        """ Initialises a new 'WorkCostDetail' instance.
        """
        # True if 'amount' attribute is a debit, false if it is a credit. 
        self.debit_flag = debit_flag
        # The type of cost. 
        self.type_work_cost = type_work_cost
        # Amount in designated currency for work, either a total or an individual element. As defined in the attribute 'type,' multiple instances are applicable to each work for: planned cost, actual cost, authorized cost, budgeted cost, forecasted cost, other. 
        self.amount = amount
        # Date that amount is posted to the work. 
        self.transaction_date = transaction_date
        
        self._erp_project_accounting = None
        self.erp_project_accounting = erp_project_accounting
        self._design = None
        self.design = design
        self._material_items = []
        if material_items is None:
            self.material_items = []
        else:
            self.material_items = material_items
        self._work_cost_summary = None
        self.work_cost_summary = work_cost_summary
        self._overhead_cost = None
        self.overhead_cost = overhead_cost
        self._labor_items = []
        if labor_items is None:
            self.labor_items = []
        else:
            self.labor_items = labor_items
        self._works = []
        if works is None:
            self.works = []
        else:
            self.works = works
        self._misc_cost_items = []
        if misc_cost_items is None:
            self.misc_cost_items = []
        else:
            self.misc_cost_items = misc_cost_items
        self._equipment_items = []
        if equipment_items is None:
            self.equipment_items = []
        else:
            self.equipment_items = equipment_items
        self._cost_type = None
        self.cost_type = cost_type
        self._contractor_items = []
        if contractor_items is None:
            self.contractor_items = []
        else:
            self.contractor_items = contractor_items
        self._work_task = None
        self.work_task = work_task
        self._property_units = []
        if property_units is None:
            self.property_units = []
        else:
            self.property_units = property_units

        super(WorkCostDetail, self).__init__(**kw_args)
    # >>> work_cost_detail
        
    # <<< erp_project_accounting
    # @generated
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
            if self not in self._erp_project_accounting._work_cost_details:
                self._erp_project_accounting._work_cost_details.append(self)

    erp_project_accounting = property(get_erp_project_accounting, set_erp_project_accounting)
    # >>> erp_project_accounting

    # <<< design
    # @generated
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
            if self not in self._design._work_cost_details:
                self._design._work_cost_details.append(self)

    design = property(get_design, set_design)
    # >>> design

    # <<< material_items
    # @generated
    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x.work_cost_detail = None
        for y in value:
            y.work_cost_detail = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._work_cost_detail = self
            if obj not in self._material_items:
                self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._work_cost_detail = None
            self._material_items.remove(obj)
    # >>> material_items

    # <<< work_cost_summary
    # @generated
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
    # >>> work_cost_summary

    # <<< overhead_cost
    # @generated
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
            if self not in self._overhead_cost._work_cost_details:
                self._overhead_cost._work_cost_details.append(self)

    overhead_cost = property(get_overhead_cost, set_overhead_cost)
    # >>> overhead_cost

    # <<< labor_items
    # @generated
    def get_labor_items(self):
        """ 
        """
        return self._labor_items

    def set_labor_items(self, value):
        for x in self._labor_items:
            x.work_cost_detail = None
        for y in value:
            y.work_cost_detail = self
        self._labor_items = value
            
    labor_items = property(get_labor_items, set_labor_items)
    
    def add_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_cost_detail = self
            if obj not in self._labor_items:
                self._labor_items.append(obj)
        
    def remove_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_cost_detail = None
            self._labor_items.remove(obj)
    # >>> labor_items

    # <<< works
    # @generated
    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for p in self._works:
            filtered = [q for q in p.work_cost_details if q != self]
            self._works._work_cost_details = filtered
        for r in value:
            if self not in r._work_cost_details:
                r._work_cost_details.append(self)
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            if self not in obj._work_cost_details:
                obj._work_cost_details.append(self)
            self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            if self in obj._work_cost_details:
                obj._work_cost_details.remove(self)
            self._works.remove(obj)
    # >>> works

    # <<< misc_cost_items
    # @generated
    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x.work_cost_detail = None
        for y in value:
            y.work_cost_detail = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_cost_detail = self
            if obj not in self._misc_cost_items:
                self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_cost_detail = None
            self._misc_cost_items.remove(obj)
    # >>> misc_cost_items

    # <<< equipment_items
    # @generated
    def get_equipment_items(self):
        """ 
        """
        return self._equipment_items

    def set_equipment_items(self, value):
        for x in self._equipment_items:
            x.work_cost_detail = None
        for y in value:
            y.work_cost_detail = self
        self._equipment_items = value
            
    equipment_items = property(get_equipment_items, set_equipment_items)
    
    def add_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_cost_detail = self
            if obj not in self._equipment_items:
                self._equipment_items.append(obj)
        
    def remove_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_cost_detail = None
            self._equipment_items.remove(obj)
    # >>> equipment_items

    # <<< cost_type
    # @generated
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
            if self not in self._cost_type._work_cost_details:
                self._cost_type._work_cost_details.append(self)

    cost_type = property(get_cost_type, set_cost_type)
    # >>> cost_type

    # <<< contractor_items
    # @generated
    def get_contractor_items(self):
        """ 
        """
        return self._contractor_items

    def set_contractor_items(self, value):
        for x in self._contractor_items:
            x.work_cost_detail = None
        for y in value:
            y.work_cost_detail = self
        self._contractor_items = value
            
    contractor_items = property(get_contractor_items, set_contractor_items)
    
    def add_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_cost_detail = self
            if obj not in self._contractor_items:
                self._contractor_items.append(obj)
        
    def remove_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_cost_detail = None
            self._contractor_items.remove(obj)
    # >>> contractor_items

    # <<< work_task
    # @generated
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
            if self not in self._work_task._work_cost_details:
                self._work_task._work_cost_details.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task

    # <<< property_units
    # @generated
    def get_property_units(self):
        """ 
        """
        return self._property_units

    def set_property_units(self, value):
        for p in self._property_units:
            filtered = [q for q in p.work_cost_details if q != self]
            self._property_units._work_cost_details = filtered
        for r in value:
            if self not in r._work_cost_details:
                r._work_cost_details.append(self)
        self._property_units = value
            
    property_units = property(get_property_units, set_property_units)
    
    def add_property_units(self, *property_units):
        for obj in property_units:
            if self not in obj._work_cost_details:
                obj._work_cost_details.append(self)
            self._property_units.append(obj)
        
    def remove_property_units(self, *property_units):
        for obj in property_units:
            if self in obj._work_cost_details:
                obj._work_cost_details.remove(self)
            self._property_units.remove(obj)
    # >>> property_units



class CUAllowableAction(IdentifiedObject):
    """ Allowed actions: Install, Remove, Transfer, Abandon, etc.
    """
    # <<< cuallowable_action
    # @generated
    def __init__(self, status=None, compatible_units=None, **kw_args):
        """ Initialises a new 'CUAllowableAction' instance.
        """
        
        self.status = status
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units

        super(CUAllowableAction, self).__init__(**kw_args)
    # >>> cuallowable_action
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x.cuallowable_action = None
        for y in value:
            y.cuallowable_action = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cuallowable_action = self
            if obj not in self._compatible_units:
                self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cuallowable_action = None
            self._compatible_units.remove(obj)
    # >>> compatible_units



class BusinessCase(Document):
    """ Business justification for capital expenditures, usually addressing operations and maintenance costs as well.
    """
    # <<< business_case
    # @generated
    def __init__(self, corporate_code='', projects=None, works=None, **kw_args):
        """ Initialises a new 'BusinessCase' instance.
        """
        # A codified representation of the business case (i.e., codes for highway relocation, replace substation transformers, etc.). 
        self.corporate_code = corporate_code
        
        self._projects = []
        if projects is None:
            self.projects = []
        else:
            self.projects = projects
        self._works = []
        if works is None:
            self.works = []
        else:
            self.works = works

        super(BusinessCase, self).__init__(**kw_args)
    # >>> business_case
        
    # <<< projects
    # @generated
    def get_projects(self):
        """ 
        """
        return self._projects

    def set_projects(self, value):
        for x in self._projects:
            x.business_case = None
        for y in value:
            y.business_case = self
        self._projects = value
            
    projects = property(get_projects, set_projects)
    
    def add_projects(self, *projects):
        for obj in projects:
            obj._business_case = self
            if obj not in self._projects:
                self._projects.append(obj)
        
    def remove_projects(self, *projects):
        for obj in projects:
            obj._business_case = None
            self._projects.remove(obj)
    # >>> projects

    # <<< works
    # @generated
    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x.business_case = None
        for y in value:
            y.business_case = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._business_case = self
            if obj not in self._works:
                self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._business_case = None
            self._works.remove(obj)
    # >>> works



class LaborItem(IdentifiedObject):
    """ Labor used for work order.
    """
    # <<< labor_item
    # @generated
    def __init__(self, activity_code='', labor_duration=0.0, labor_rate=0.0, cost=0.0, status=None, erp_persons=None, work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'LaborItem' instance.
        """
        # Activity code identifies a specific and distinguishable unit of work. 
        self.activity_code = activity_code
        # Time required to perform work. 
        self.labor_duration = labor_duration
        # The labor rate applied for work. 
        self.labor_rate = labor_rate
        # Total cost for labor. Note that this may not be able to be derived from labor rate and time charged. 
        self.cost = cost
        
        self.status = status
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._work_task = None
        self.work_task = work_task

        super(LaborItem, self).__init__(**kw_args)
    # >>> labor_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self._erp_persons:
            filtered = [q for q in p.labor_items if q != self]
            self._erp_persons._labor_items = filtered
        for r in value:
            if self not in r._labor_items:
                r._labor_items.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._labor_items:
                obj._labor_items.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._labor_items:
                obj._labor_items.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons

    # <<< work_cost_detail
    # @generated
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
            if self not in self._work_cost_detail._labor_items:
                self._work_cost_detail._labor_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)
    # >>> work_cost_detail

    # <<< work_task
    # @generated
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
            if self not in self._work_task._labor_items:
                self._work_task._labor_items.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task



class Design(Document):
    """ A design for consideration by customers, potential customers, or internal work. Note that the Version of design is the revision attribute that is inherited from Document.
    """
    # <<< design
    # @generated
    def __init__(self, cost_estimate=0.0, kind='other', price=0.0, erp_boms=None, erp_quote_line_item=None, design_locations=None, work=None, condition_factors=None, work_cost_details=None, work_tasks=None, design_locations_cus=None, **kw_args):
        """ Initialises a new 'Design' instance.
        """
        # Estimated cost (not price) of design. 
        self.cost_estimate = cost_estimate
        # Kind of this design. Values are: "other", "as_built", "estimated"
        self.kind = kind
        # Price to customer for implementing design. 
        self.price = price
        
        self._erp_boms = []
        if erp_boms is None:
            self.erp_boms = []
        else:
            self.erp_boms = erp_boms
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._design_locations = []
        if design_locations is None:
            self.design_locations = []
        else:
            self.design_locations = design_locations
        self._work = None
        self.work = work
        self._condition_factors = []
        if condition_factors is None:
            self.condition_factors = []
        else:
            self.condition_factors = condition_factors
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks
        self._design_locations_cus = []
        if design_locations_cus is None:
            self.design_locations_cus = []
        else:
            self.design_locations_cus = design_locations_cus

        super(Design, self).__init__(**kw_args)
    # >>> design
        
    # <<< erp_boms
    # @generated
    def get_erp_boms(self):
        """ 
        """
        return self._erp_boms

    def set_erp_boms(self, value):
        for x in self._erp_boms:
            x.design = None
        for y in value:
            y.design = self
        self._erp_boms = value
            
    erp_boms = property(get_erp_boms, set_erp_boms)
    
    def add_erp_boms(self, *erp_boms):
        for obj in erp_boms:
            obj._design = self
            if obj not in self._erp_boms:
                self._erp_boms.append(obj)
        
    def remove_erp_boms(self, *erp_boms):
        for obj in erp_boms:
            obj._design = None
            self._erp_boms.remove(obj)
    # >>> erp_boms

    # <<< erp_quote_line_item
    # @generated
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
    # >>> erp_quote_line_item

    # <<< design_locations
    # @generated
    def get_design_locations(self):
        """ 
        """
        return self._design_locations

    def set_design_locations(self, value):
        for p in self._design_locations:
            filtered = [q for q in p.designs if q != self]
            self._design_locations._designs = filtered
        for r in value:
            if self not in r._designs:
                r._designs.append(self)
        self._design_locations = value
            
    design_locations = property(get_design_locations, set_design_locations)
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
            if self not in obj._designs:
                obj._designs.append(self)
            self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
            if self in obj._designs:
                obj._designs.remove(self)
            self._design_locations.remove(obj)
    # >>> design_locations

    # <<< work
    # @generated
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
            if self not in self._work._designs:
                self._work._designs.append(self)

    work = property(get_work, set_work)
    # >>> work

    # <<< condition_factors
    # @generated
    def get_condition_factors(self):
        """ 
        """
        return self._condition_factors

    def set_condition_factors(self, value):
        for p in self._condition_factors:
            filtered = [q for q in p.designs if q != self]
            self._condition_factors._designs = filtered
        for r in value:
            if self not in r._designs:
                r._designs.append(self)
        self._condition_factors = value
            
    condition_factors = property(get_condition_factors, set_condition_factors)
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self not in obj._designs:
                obj._designs.append(self)
            self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self in obj._designs:
                obj._designs.remove(self)
            self._condition_factors.remove(obj)
    # >>> condition_factors

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x.design = None
        for y in value:
            y.design = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._design = self
            if obj not in self._work_cost_details:
                self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._design = None
            self._work_cost_details.remove(obj)
    # >>> work_cost_details

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x.design = None
        for y in value:
            y.design = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._design = self
            if obj not in self._work_tasks:
                self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._design = None
            self._work_tasks.remove(obj)
    # >>> work_tasks

    # <<< design_locations_cus
    # @generated
    def get_design_locations_cus(self):
        """ 
        """
        return self._design_locations_cus

    def set_design_locations_cus(self, value):
        for p in self._design_locations_cus:
            filtered = [q for q in p.designs if q != self]
            self._design_locations_cus._designs = filtered
        for r in value:
            if self not in r._designs:
                r._designs.append(self)
        self._design_locations_cus = value
            
    design_locations_cus = property(get_design_locations_cus, set_design_locations_cus)
    
    def add_design_locations_cus(self, *design_locations_cus):
        for obj in design_locations_cus:
            if self not in obj._designs:
                obj._designs.append(self)
            self._design_locations_cus.append(obj)
        
    def remove_design_locations_cus(self, *design_locations_cus):
        for obj in design_locations_cus:
            if self in obj._designs:
                obj._designs.remove(self)
            self._design_locations_cus.remove(obj)
    # >>> design_locations_cus



class WorkTask(Document):
    """ A set of tasks is required to implement a design.
    """
    # <<< work_task
    # @generated
    def __init__(self, sched_override='', priority='', qualification_requirements=None, design=None, design_location_cus=None, misc_cost_items=None, switching_schedules=None, capabilities=None, usages=None, overhead_cost=None, work_flow_step=None, material_items=None, labor_items=None, crews=None, work=None, contractor_items=None, equipment_items=None, work_cost_details=None, assets=None, **kw_args):
        """ Initialises a new 'WorkTask' instance.
        """
        # If specified, override schedule and perform this task in accordance with instructions specified here. 
        self.sched_override = sched_override
        # The priority of this work task. 
        self.priority = priority
        
        self._qualification_requirements = []
        if qualification_requirements is None:
            self.qualification_requirements = []
        else:
            self.qualification_requirements = qualification_requirements
        self._design = None
        self.design = design
        self._design_location_cus = []
        if design_location_cus is None:
            self.design_location_cus = []
        else:
            self.design_location_cus = design_location_cus
        self._misc_cost_items = []
        if misc_cost_items is None:
            self.misc_cost_items = []
        else:
            self.misc_cost_items = misc_cost_items
        self._switching_schedules = []
        if switching_schedules is None:
            self.switching_schedules = []
        else:
            self.switching_schedules = switching_schedules
        self._capabilities = []
        if capabilities is None:
            self.capabilities = []
        else:
            self.capabilities = capabilities
        self._usages = []
        if usages is None:
            self.usages = []
        else:
            self.usages = usages
        self._overhead_cost = None
        self.overhead_cost = overhead_cost
        self._work_flow_step = None
        self.work_flow_step = work_flow_step
        self._material_items = []
        if material_items is None:
            self.material_items = []
        else:
            self.material_items = material_items
        self._labor_items = []
        if labor_items is None:
            self.labor_items = []
        else:
            self.labor_items = labor_items
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews
        self._work = None
        self.work = work
        self._contractor_items = []
        if contractor_items is None:
            self.contractor_items = []
        else:
            self.contractor_items = contractor_items
        self._equipment_items = []
        if equipment_items is None:
            self.equipment_items = []
        else:
            self.equipment_items = equipment_items
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details
        self._assets = []
        if assets is None:
            self.assets = []
        else:
            self.assets = assets

        super(WorkTask, self).__init__(**kw_args)
    # >>> work_task
        
    # <<< qualification_requirements
    # @generated
    def get_qualification_requirements(self):
        """ 
        """
        return self._qualification_requirements

    def set_qualification_requirements(self, value):
        for p in self._qualification_requirements:
            filtered = [q for q in p.work_tasks if q != self]
            self._qualification_requirements._work_tasks = filtered
        for r in value:
            if self not in r._work_tasks:
                r._work_tasks.append(self)
        self._qualification_requirements = value
            
    qualification_requirements = property(get_qualification_requirements, set_qualification_requirements)
    
    def add_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self not in obj._work_tasks:
                obj._work_tasks.append(self)
            self._qualification_requirements.append(obj)
        
    def remove_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self in obj._work_tasks:
                obj._work_tasks.remove(self)
            self._qualification_requirements.remove(obj)
    # >>> qualification_requirements

    # <<< design
    # @generated
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
            if self not in self._design._work_tasks:
                self._design._work_tasks.append(self)

    design = property(get_design, set_design)
    # >>> design

    # <<< design_location_cus
    # @generated
    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for p in self._design_location_cus:
            filtered = [q for q in p.work_tasks if q != self]
            self._design_location_cus._work_tasks = filtered
        for r in value:
            if self not in r._work_tasks:
                r._work_tasks.append(self)
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self not in obj._work_tasks:
                obj._work_tasks.append(self)
            self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self in obj._work_tasks:
                obj._work_tasks.remove(self)
            self._design_location_cus.remove(obj)
    # >>> design_location_cus

    # <<< misc_cost_items
    # @generated
    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_task = self
            if obj not in self._misc_cost_items:
                self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._work_task = None
            self._misc_cost_items.remove(obj)
    # >>> misc_cost_items

    # <<< switching_schedules
    # @generated
    def get_switching_schedules(self):
        """ 
        """
        return self._switching_schedules

    def set_switching_schedules(self, value):
        for x in self._switching_schedules:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._switching_schedules = value
            
    switching_schedules = property(get_switching_schedules, set_switching_schedules)
    
    def add_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            obj._work_task = self
            if obj not in self._switching_schedules:
                self._switching_schedules.append(obj)
        
    def remove_switching_schedules(self, *switching_schedules):
        for obj in switching_schedules:
            obj._work_task = None
            self._switching_schedules.remove(obj)
    # >>> switching_schedules

    # <<< capabilities
    # @generated
    def get_capabilities(self):
        """ 
        """
        return self._capabilities

    def set_capabilities(self, value):
        for p in self._capabilities:
            filtered = [q for q in p.work_tasks if q != self]
            self._capabilities._work_tasks = filtered
        for r in value:
            if self not in r._work_tasks:
                r._work_tasks.append(self)
        self._capabilities = value
            
    capabilities = property(get_capabilities, set_capabilities)
    
    def add_capabilities(self, *capabilities):
        for obj in capabilities:
            if self not in obj._work_tasks:
                obj._work_tasks.append(self)
            self._capabilities.append(obj)
        
    def remove_capabilities(self, *capabilities):
        for obj in capabilities:
            if self in obj._work_tasks:
                obj._work_tasks.remove(self)
            self._capabilities.remove(obj)
    # >>> capabilities

    # <<< usages
    # @generated
    def get_usages(self):
        """ 
        """
        return self._usages

    def set_usages(self, value):
        for x in self._usages:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._usages = value
            
    usages = property(get_usages, set_usages)
    
    def add_usages(self, *usages):
        for obj in usages:
            obj._work_task = self
            if obj not in self._usages:
                self._usages.append(obj)
        
    def remove_usages(self, *usages):
        for obj in usages:
            obj._work_task = None
            self._usages.remove(obj)
    # >>> usages

    # <<< overhead_cost
    # @generated
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
            if self not in self._overhead_cost._work_tasks:
                self._overhead_cost._work_tasks.append(self)

    overhead_cost = property(get_overhead_cost, set_overhead_cost)
    # >>> overhead_cost

    # <<< work_flow_step
    # @generated
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
            if self not in self._work_flow_step._work_tasks:
                self._work_flow_step._work_tasks.append(self)

    work_flow_step = property(get_work_flow_step, set_work_flow_step)
    # >>> work_flow_step

    # <<< material_items
    # @generated
    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._work_task = self
            if obj not in self._material_items:
                self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._work_task = None
            self._material_items.remove(obj)
    # >>> material_items

    # <<< labor_items
    # @generated
    def get_labor_items(self):
        """ 
        """
        return self._labor_items

    def set_labor_items(self, value):
        for x in self._labor_items:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._labor_items = value
            
    labor_items = property(get_labor_items, set_labor_items)
    
    def add_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_task = self
            if obj not in self._labor_items:
                self._labor_items.append(obj)
        
    def remove_labor_items(self, *labor_items):
        for obj in labor_items:
            obj._work_task = None
            self._labor_items.remove(obj)
    # >>> labor_items

    # <<< crews
    # @generated
    def get_crews(self):
        """ All Crews participating in this WorkTask.
        """
        return self._crews

    def set_crews(self, value):
        for p in self._crews:
            filtered = [q for q in p.work_tasks if q != self]
            self._crews._work_tasks = filtered
        for r in value:
            if self not in r._work_tasks:
                r._work_tasks.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._work_tasks:
                obj._work_tasks.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._work_tasks:
                obj._work_tasks.remove(self)
            self._crews.remove(obj)
    # >>> crews

    # <<< work
    # @generated
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
            if self not in self._work._work_tasks:
                self._work._work_tasks.append(self)

    work = property(get_work, set_work)
    # >>> work

    # <<< contractor_items
    # @generated
    def get_contractor_items(self):
        """ 
        """
        return self._contractor_items

    def set_contractor_items(self, value):
        for x in self._contractor_items:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._contractor_items = value
            
    contractor_items = property(get_contractor_items, set_contractor_items)
    
    def add_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_task = self
            if obj not in self._contractor_items:
                self._contractor_items.append(obj)
        
    def remove_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            obj._work_task = None
            self._contractor_items.remove(obj)
    # >>> contractor_items

    # <<< equipment_items
    # @generated
    def get_equipment_items(self):
        """ 
        """
        return self._equipment_items

    def set_equipment_items(self, value):
        for x in self._equipment_items:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._equipment_items = value
            
    equipment_items = property(get_equipment_items, set_equipment_items)
    
    def add_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_task = self
            if obj not in self._equipment_items:
                self._equipment_items.append(obj)
        
    def remove_equipment_items(self, *equipment_items):
        for obj in equipment_items:
            obj._work_task = None
            self._equipment_items.remove(obj)
    # >>> equipment_items

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._work_task = self
            if obj not in self._work_cost_details:
                self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._work_task = None
            self._work_cost_details.remove(obj)
    # >>> work_cost_details

    # <<< assets
    # @generated
    def get_assets(self):
        """ 
        """
        return self._assets

    def set_assets(self, value):
        for x in self._assets:
            x.work_task = None
        for y in value:
            y.work_task = self
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            obj._work_task = self
            if obj not in self._assets:
                self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            obj._work_task = None
            self._assets.remove(obj)
    # >>> assets



class NonStandardItem(Document):
    """ This document provides information for non-standard items like customer contributions (e.g., customer digs trench), vouchers (e.g., credit), and contractor bids.
    """
    # <<< non_standard_item
    # @generated
    def __init__(self, code='', amount=0.0, **kw_args):
        """ Initialises a new 'NonStandardItem' instance.
        """
        # The category of non-standard work. 
        self.code = code
        # The projected cost for this item. 
        self.amount = amount
        

        super(NonStandardItem, self).__init__(**kw_args)
    # >>> non_standard_item
        


class ConditionFactor(IdentifiedObject):
    """ This is to specify the various condition factors for a design that may alter the cost estimate or the allocation.
    """
    # <<< condition_factor
    # @generated
    def __init__(self, kind='material', cf_value='', status=None, designs=None, design_location_cus=None, design_locations=None, **kw_args):
        """ Initialises a new 'ConditionFactor' instance.
        """
        # Kind of this condition factor. Values are: "material", "travel", "other", "account_allocation", "labor"
        self.kind = kind
        # The actual value of the condition factor, such as labor flat fee or percentage. 
        self.cf_value = cf_value
        
        self.status = status
        self._designs = []
        if designs is None:
            self.designs = []
        else:
            self.designs = designs
        self._design_location_cus = []
        if design_location_cus is None:
            self.design_location_cus = []
        else:
            self.design_location_cus = design_location_cus
        self._design_locations = []
        if design_locations is None:
            self.design_locations = []
        else:
            self.design_locations = design_locations

        super(ConditionFactor, self).__init__(**kw_args)
    # >>> condition_factor
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< designs
    # @generated
    def get_designs(self):
        """ 
        """
        return self._designs

    def set_designs(self, value):
        for p in self._designs:
            filtered = [q for q in p.condition_factors if q != self]
            self._designs._condition_factors = filtered
        for r in value:
            if self not in r._condition_factors:
                r._condition_factors.append(self)
        self._designs = value
            
    designs = property(get_designs, set_designs)
    
    def add_designs(self, *designs):
        for obj in designs:
            if self not in obj._condition_factors:
                obj._condition_factors.append(self)
            self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
            if self in obj._condition_factors:
                obj._condition_factors.remove(self)
            self._designs.remove(obj)
    # >>> designs

    # <<< design_location_cus
    # @generated
    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for p in self._design_location_cus:
            filtered = [q for q in p.condition_factors if q != self]
            self._design_location_cus._condition_factors = filtered
        for r in value:
            if self not in r._condition_factors:
                r._condition_factors.append(self)
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self not in obj._condition_factors:
                obj._condition_factors.append(self)
            self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self in obj._condition_factors:
                obj._condition_factors.remove(self)
            self._design_location_cus.remove(obj)
    # >>> design_location_cus

    # <<< design_locations
    # @generated
    def get_design_locations(self):
        """ 
        """
        return self._design_locations

    def set_design_locations(self, value):
        for p in self._design_locations:
            filtered = [q for q in p.condition_factors if q != self]
            self._design_locations._condition_factors = filtered
        for r in value:
            if self not in r._condition_factors:
                r._condition_factors.append(self)
        self._design_locations = value
            
    design_locations = property(get_design_locations, set_design_locations)
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
            if self not in obj._condition_factors:
                obj._condition_factors.append(self)
            self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
            if self in obj._condition_factors:
                obj._condition_factors.remove(self)
            self._design_locations.remove(obj)
    # >>> design_locations



class CUGroup(IdentifiedObject):
    """ A Compatible Unit Group identifies a set of compatible units which may be jointly utilized for estimating and designating jobs.
    """
    # <<< cugroup
    # @generated
    def __init__(self, status=None, compatible_units=None, design_location_cus=None, child_cugroups=None, parent_cugroups=None, **kw_args):
        """ Initialises a new 'CUGroup' instance.
        """
        
        self.status = status
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._design_location_cus = []
        if design_location_cus is None:
            self.design_location_cus = []
        else:
            self.design_location_cus = design_location_cus
        self._child_cugroups = []
        if child_cugroups is None:
            self.child_cugroups = []
        else:
            self.child_cugroups = child_cugroups
        self._parent_cugroups = []
        if parent_cugroups is None:
            self.parent_cugroups = []
        else:
            self.parent_cugroups = parent_cugroups

        super(CUGroup, self).__init__(**kw_args)
    # >>> cugroup
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for x in self._compatible_units:
            x.cugroup = None
        for y in value:
            y.cugroup = self
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cugroup = self
            if obj not in self._compatible_units:
                self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            obj._cugroup = None
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< design_location_cus
    # @generated
    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for p in self._design_location_cus:
            filtered = [q for q in p.cugroups if q != self]
            self._design_location_cus._cugroups = filtered
        for r in value:
            if self not in r._cugroups:
                r._cugroups.append(self)
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self not in obj._cugroups:
                obj._cugroups.append(self)
            self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            if self in obj._cugroups:
                obj._cugroups.remove(self)
            self._design_location_cus.remove(obj)
    # >>> design_location_cus

    # <<< child_cugroups
    # @generated
    def get_child_cugroups(self):
        """ 
        """
        return self._child_cugroups

    def set_child_cugroups(self, value):
        for p in self._child_cugroups:
            filtered = [q for q in p.parent_cugroups if q != self]
            self._child_cugroups._parent_cugroups = filtered
        for r in value:
            if self not in r._parent_cugroups:
                r._parent_cugroups.append(self)
        self._child_cugroups = value
            
    child_cugroups = property(get_child_cugroups, set_child_cugroups)
    
    def add_child_cugroups(self, *child_cugroups):
        for obj in child_cugroups:
            if self not in obj._parent_cugroups:
                obj._parent_cugroups.append(self)
            self._child_cugroups.append(obj)
        
    def remove_child_cugroups(self, *child_cugroups):
        for obj in child_cugroups:
            if self in obj._parent_cugroups:
                obj._parent_cugroups.remove(self)
            self._child_cugroups.remove(obj)
    # >>> child_cugroups

    # <<< parent_cugroups
    # @generated
    def get_parent_cugroups(self):
        """ 
        """
        return self._parent_cugroups

    def set_parent_cugroups(self, value):
        for p in self._parent_cugroups:
            filtered = [q for q in p.child_cugroups if q != self]
            self._parent_cugroups._child_cugroups = filtered
        for r in value:
            if self not in r._child_cugroups:
                r._child_cugroups.append(self)
        self._parent_cugroups = value
            
    parent_cugroups = property(get_parent_cugroups, set_parent_cugroups)
    
    def add_parent_cugroups(self, *parent_cugroups):
        for obj in parent_cugroups:
            if self not in obj._child_cugroups:
                obj._child_cugroups.append(self)
            self._parent_cugroups.append(obj)
        
    def remove_parent_cugroups(self, *parent_cugroups):
        for obj in parent_cugroups:
            if self in obj._child_cugroups:
                obj._child_cugroups.remove(self)
            self._parent_cugroups.remove(obj)
    # >>> parent_cugroups



class ContractorItem(IdentifiedObject):
    """ Contractor information for work task.
    """
    # <<< contractor_item
    # @generated
    def __init__(self, bid_amount=0.0, cost=0.0, activity_code='', status=None, erp_payables=None, work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'ContractorItem' instance.
        """
        # The amount that a given contractor will charge for performing this unit of work. 
        self.bid_amount = bid_amount
        # The total amount charged. 
        self.cost = cost
        # Activity code identifies a specific and distinguishable unit of work. 
        self.activity_code = activity_code
        
        self.status = status
        self._erp_payables = []
        if erp_payables is None:
            self.erp_payables = []
        else:
            self.erp_payables = erp_payables
        self._work_cost_detail = None
        self.work_cost_detail = work_cost_detail
        self._work_task = None
        self.work_task = work_task

        super(ContractorItem, self).__init__(**kw_args)
    # >>> contractor_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_payables
    # @generated
    def get_erp_payables(self):
        """ 
        """
        return self._erp_payables

    def set_erp_payables(self, value):
        for p in self._erp_payables:
            filtered = [q for q in p.contractor_items if q != self]
            self._erp_payables._contractor_items = filtered
        for r in value:
            if self not in r._contractor_items:
                r._contractor_items.append(self)
        self._erp_payables = value
            
    erp_payables = property(get_erp_payables, set_erp_payables)
    
    def add_erp_payables(self, *erp_payables):
        for obj in erp_payables:
            if self not in obj._contractor_items:
                obj._contractor_items.append(self)
            self._erp_payables.append(obj)
        
    def remove_erp_payables(self, *erp_payables):
        for obj in erp_payables:
            if self in obj._contractor_items:
                obj._contractor_items.remove(self)
            self._erp_payables.remove(obj)
    # >>> erp_payables

    # <<< work_cost_detail
    # @generated
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
            if self not in self._work_cost_detail._contractor_items:
                self._work_cost_detail._contractor_items.append(self)

    work_cost_detail = property(get_work_cost_detail, set_work_cost_detail)
    # >>> work_cost_detail

    # <<< work_task
    # @generated
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
            if self not in self._work_task._contractor_items:
                self._work_task._contractor_items.append(self)

    work_task = property(get_work_task, set_work_task)
    # >>> work_task



class CUAsset(IdentifiedObject):
    """ Compatible unit for various types of assets such as transformers switches, substation fences, poles, etc..
    """
    # <<< cuasset
    # @generated
    def __init__(self, quantity=0, type_asset_code='', status=None, type_asset=None, compatible_units=None, **kw_args):
        """ Initialises a new 'CUAsset' instance.
        """
        # Quantity of the type asset within the CU. 
        self.quantity = quantity
        # The code for this type of asset. 
        self.type_asset_code = type_asset_code
        
        self.status = status
        self._type_asset = None
        self.type_asset = type_asset
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units

        super(CUAsset, self).__init__(**kw_args)
    # >>> cuasset
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< type_asset
    # @generated
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
    # >>> type_asset

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.cuassets if q != self]
            self._compatible_units._cuassets = filtered
        for r in value:
            if self not in r._cuassets:
                r._cuassets.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._cuassets:
                obj._cuassets.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._cuassets:
                obj._cuassets.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units



class DesignLocation(IdentifiedObject):
    """ A logical part of the design (e.g., pole and all equipment on a pole). This includes points and spans.
    """
    # <<< design_location
    # @generated
    def __init__(self, span_length=0.0, status=None, diagrams=None, erp_bom_item_datas=None, misc_cost_items=None, design_location_cus=None, material_items=None, condition_factors=None, work_locations=None, designs=None, **kw_args):
        """ Initialises a new 'DesignLocation' instance.
        """
        # The legth of the span from the previous pole to this pole. 
        self.span_length = span_length
        
        self.status = status
        self._diagrams = []
        if diagrams is None:
            self.diagrams = []
        else:
            self.diagrams = diagrams
        self._erp_bom_item_datas = []
        if erp_bom_item_datas is None:
            self.erp_bom_item_datas = []
        else:
            self.erp_bom_item_datas = erp_bom_item_datas
        self._misc_cost_items = []
        if misc_cost_items is None:
            self.misc_cost_items = []
        else:
            self.misc_cost_items = misc_cost_items
        self._design_location_cus = []
        if design_location_cus is None:
            self.design_location_cus = []
        else:
            self.design_location_cus = design_location_cus
        self._material_items = []
        if material_items is None:
            self.material_items = []
        else:
            self.material_items = material_items
        self._condition_factors = []
        if condition_factors is None:
            self.condition_factors = []
        else:
            self.condition_factors = condition_factors
        self._work_locations = []
        if work_locations is None:
            self.work_locations = []
        else:
            self.work_locations = work_locations
        self._designs = []
        if designs is None:
            self.designs = []
        else:
            self.designs = designs

        super(DesignLocation, self).__init__(**kw_args)
    # >>> design_location
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< diagrams
    # @generated
    def get_diagrams(self):
        """ 
        """
        return self._diagrams

    def set_diagrams(self, value):
        for p in self._diagrams:
            filtered = [q for q in p.design_locations if q != self]
            self._diagrams._design_locations = filtered
        for r in value:
            if self not in r._design_locations:
                r._design_locations.append(self)
        self._diagrams = value
            
    diagrams = property(get_diagrams, set_diagrams)
    
    def add_diagrams(self, *diagrams):
        for obj in diagrams:
            if self not in obj._design_locations:
                obj._design_locations.append(self)
            self._diagrams.append(obj)
        
    def remove_diagrams(self, *diagrams):
        for obj in diagrams:
            if self in obj._design_locations:
                obj._design_locations.remove(self)
            self._diagrams.remove(obj)
    # >>> diagrams

    # <<< erp_bom_item_datas
    # @generated
    def get_erp_bom_item_datas(self):
        """ 
        """
        return self._erp_bom_item_datas

    def set_erp_bom_item_datas(self, value):
        for x in self._erp_bom_item_datas:
            x.design_location = None
        for y in value:
            y.design_location = self
        self._erp_bom_item_datas = value
            
    erp_bom_item_datas = property(get_erp_bom_item_datas, set_erp_bom_item_datas)
    
    def add_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._design_location = self
            if obj not in self._erp_bom_item_datas:
                self._erp_bom_item_datas.append(obj)
        
    def remove_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._design_location = None
            self._erp_bom_item_datas.remove(obj)
    # >>> erp_bom_item_datas

    # <<< misc_cost_items
    # @generated
    def get_misc_cost_items(self):
        """ 
        """
        return self._misc_cost_items

    def set_misc_cost_items(self, value):
        for x in self._misc_cost_items:
            x.design_location = None
        for y in value:
            y.design_location = self
        self._misc_cost_items = value
            
    misc_cost_items = property(get_misc_cost_items, set_misc_cost_items)
    
    def add_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._design_location = self
            if obj not in self._misc_cost_items:
                self._misc_cost_items.append(obj)
        
    def remove_misc_cost_items(self, *misc_cost_items):
        for obj in misc_cost_items:
            obj._design_location = None
            self._misc_cost_items.remove(obj)
    # >>> misc_cost_items

    # <<< design_location_cus
    # @generated
    def get_design_location_cus(self):
        """ 
        """
        return self._design_location_cus

    def set_design_location_cus(self, value):
        for x in self._design_location_cus:
            x.design_location = None
        for y in value:
            y.design_location = self
        self._design_location_cus = value
            
    design_location_cus = property(get_design_location_cus, set_design_location_cus)
    
    def add_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            obj._design_location = self
            if obj not in self._design_location_cus:
                self._design_location_cus.append(obj)
        
    def remove_design_location_cus(self, *design_location_cus):
        for obj in design_location_cus:
            obj._design_location = None
            self._design_location_cus.remove(obj)
    # >>> design_location_cus

    # <<< material_items
    # @generated
    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for x in self._material_items:
            x.design_location = None
        for y in value:
            y.design_location = self
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            obj._design_location = self
            if obj not in self._material_items:
                self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            obj._design_location = None
            self._material_items.remove(obj)
    # >>> material_items

    # <<< condition_factors
    # @generated
    def get_condition_factors(self):
        """ 
        """
        return self._condition_factors

    def set_condition_factors(self, value):
        for p in self._condition_factors:
            filtered = [q for q in p.design_locations if q != self]
            self._condition_factors._design_locations = filtered
        for r in value:
            if self not in r._design_locations:
                r._design_locations.append(self)
        self._condition_factors = value
            
    condition_factors = property(get_condition_factors, set_condition_factors)
    
    def add_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self not in obj._design_locations:
                obj._design_locations.append(self)
            self._condition_factors.append(obj)
        
    def remove_condition_factors(self, *condition_factors):
        for obj in condition_factors:
            if self in obj._design_locations:
                obj._design_locations.remove(self)
            self._condition_factors.remove(obj)
    # >>> condition_factors

    # <<< work_locations
    # @generated
    def get_work_locations(self):
        """ 
        """
        return self._work_locations

    def set_work_locations(self, value):
        for p in self._work_locations:
            filtered = [q for q in p.design_locations if q != self]
            self._work_locations._design_locations = filtered
        for r in value:
            if self not in r._design_locations:
                r._design_locations.append(self)
        self._work_locations = value
            
    work_locations = property(get_work_locations, set_work_locations)
    
    def add_work_locations(self, *work_locations):
        for obj in work_locations:
            if self not in obj._design_locations:
                obj._design_locations.append(self)
            self._work_locations.append(obj)
        
    def remove_work_locations(self, *work_locations):
        for obj in work_locations:
            if self in obj._design_locations:
                obj._design_locations.remove(self)
            self._work_locations.remove(obj)
    # >>> work_locations

    # <<< designs
    # @generated
    def get_designs(self):
        """ 
        """
        return self._designs

    def set_designs(self, value):
        for p in self._designs:
            filtered = [q for q in p.design_locations if q != self]
            self._designs._design_locations = filtered
        for r in value:
            if self not in r._design_locations:
                r._design_locations.append(self)
        self._designs = value
            
    designs = property(get_designs, set_designs)
    
    def add_designs(self, *designs):
        for obj in designs:
            if self not in obj._design_locations:
                obj._design_locations.append(self)
            self._designs.append(obj)
        
    def remove_designs(self, *designs):
        for obj in designs:
            if self in obj._design_locations:
                obj._design_locations.remove(self)
            self._designs.remove(obj)
    # >>> designs



class OverheadCost(IdentifiedObject):
    """ Overhead cost applied to work order.
    """
    # <<< overhead_cost
    # @generated
    def __init__(self, cost=0.0, code='', status=None, work_tasks=None, work_cost_details=None, **kw_args):
        """ Initialises a new 'OverheadCost' instance.
        """
        # The overhead cost to be applied. 
        self.cost = cost
        # Overhead code. 
        self.code = code
        
        self.status = status
        self._work_tasks = []
        if work_tasks is None:
            self.work_tasks = []
        else:
            self.work_tasks = work_tasks
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details

        super(OverheadCost, self).__init__(**kw_args)
    # >>> overhead_cost
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< work_tasks
    # @generated
    def get_work_tasks(self):
        """ 
        """
        return self._work_tasks

    def set_work_tasks(self, value):
        for x in self._work_tasks:
            x.overhead_cost = None
        for y in value:
            y.overhead_cost = self
        self._work_tasks = value
            
    work_tasks = property(get_work_tasks, set_work_tasks)
    
    def add_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._overhead_cost = self
            if obj not in self._work_tasks:
                self._work_tasks.append(obj)
        
    def remove_work_tasks(self, *work_tasks):
        for obj in work_tasks:
            obj._overhead_cost = None
            self._work_tasks.remove(obj)
    # >>> work_tasks

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x.overhead_cost = None
        for y in value:
            y.overhead_cost = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._overhead_cost = self
            if obj not in self._work_cost_details:
                self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._overhead_cost = None
            self._work_cost_details.remove(obj)
    # >>> work_cost_details



class Regulation(Document):
    """ Special requirements and/or regulations may pertain to certain types of assets or work. For example, fire protection and scaffolding.
    """
    # <<< regulation
    # @generated
    def __init__(self, reference_number='', **kw_args):
        """ Initialises a new 'Regulation' instance.
        """
        # External reference to regulation, if applicable. 
        self.reference_number = reference_number
        

        super(Regulation, self).__init__(**kw_args)
    # >>> regulation
        


class InfoQuestion(Document):
    """ Questions and answers associated with a type of document for purposes of clarification. Questions may be predefined or ad hoc.
    """
    # <<< info_question
    # @generated
    def __init__(self, answer='', question_remark='', question_code='', question_category='', answer_date_time='', answer_remark='', question_text='', **kw_args):
        """ Initialises a new 'InfoQuestion' instance.
        """
        # Answer to question. 
        self.answer = answer
        # Remarks to qualify the question in this situation. 
        self.question_remark = question_remark
        # The question code. If blank, refer to questionText. 
        self.question_code = question_code
        # The category of the question. 
        self.question_category = question_category
        # The date and time the quesiton was answered. 
        self.answer_date_time = answer_date_time
        # Remarks to qualify the answer. 
        self.answer_remark = answer_remark
        # For non-coded questions, the question is provided here. 
        self.question_text = question_text
        

        super(InfoQuestion, self).__init__(**kw_args)
    # >>> info_question
        


class CUWorkEquipmentItem(IdentifiedObject):
    """ Compatible unit for various types of WorkEquipmentAssets, including vehicles.
    """
    # <<< cuwork_equipment_item
    # @generated
    def __init__(self, equip_code='', rate=0.0, status=None, compatible_units=None, type_asset=None, **kw_args):
        """ Initialises a new 'CUWorkEquipmentItem' instance.
        """
        # The equipment type code. 
        self.equip_code = equip_code
        # Standard usage rate for the type of vehicle. 
        self.rate = rate
        
        self.status = status
        self._compatible_units = []
        if compatible_units is None:
            self.compatible_units = []
        else:
            self.compatible_units = compatible_units
        self._type_asset = None
        self.type_asset = type_asset

        super(CUWorkEquipmentItem, self).__init__(**kw_args)
    # >>> cuwork_equipment_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< compatible_units
    # @generated
    def get_compatible_units(self):
        """ 
        """
        return self._compatible_units

    def set_compatible_units(self, value):
        for p in self._compatible_units:
            filtered = [q for q in p.cuwork_equipment_items if q != self]
            self._compatible_units._cuwork_equipment_items = filtered
        for r in value:
            if self not in r._cuwork_equipment_items:
                r._cuwork_equipment_items.append(self)
        self._compatible_units = value
            
    compatible_units = property(get_compatible_units, set_compatible_units)
    
    def add_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self not in obj._cuwork_equipment_items:
                obj._cuwork_equipment_items.append(self)
            self._compatible_units.append(obj)
        
    def remove_compatible_units(self, *compatible_units):
        for obj in compatible_units:
            if self in obj._cuwork_equipment_items:
                obj._cuwork_equipment_items.remove(self)
            self._compatible_units.remove(obj)
    # >>> compatible_units

    # <<< type_asset
    # @generated
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
    # >>> type_asset



# <<< inf_work
# @generated
# >>> inf_work

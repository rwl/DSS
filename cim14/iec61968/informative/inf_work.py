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

class Request(Document):
    """ A request for work, service or project.
    """
    # The corporate code for this request. 
    corporate_code = ''

    # Based on the current 'Status.status', the action that is needed before this Request can transition to the desired state, such as initiating the requested Work. For example, missing or additionally needed information may be required from the requesting organisation before a work Design may be created. 
    action_needed = ''

    # The priority of this request. 
    priority = ''

    erp_quote_line_item = None

    organisation = None

    works = []

    projects = []

    # <<< request
    # @generated
    def __init__(self, corporate_code='', action_needed='', priority='', erp_quote_line_item=None, organisation=None, works=[], projects=[], **kw_args):
        """ Initialises a new 'Request' instance.
        """
        self.corporate_code = corporate_code
        self.action_needed = action_needed
        self.priority = priority
        self.erp_quote_line_item = erp_quote_line_item
        self.organisation = organisation
        self.works = works
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

    parent_cost_type = None

    child_cost_types = []

    compatible_units = []

    erp_journal_entries = []

    work_cost_details = []

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
        self.parent_cost_type = parent_cost_type
        self.child_cost_types = child_cost_types
        self.compatible_units = compatible_units
        self.erp_journal_entries = erp_journal_entries
        self.work_cost_details = work_cost_details

        super(CostType, self).__init__(**kw_args)
    # >>> cost_type


class Usage(IdentifiedObject):
    """ The way material and assets are used to perform a certain type of work task. The way is described in text in the inheritied description attribute.
    """
    status = None

    work_task = None

    work_equipment_asset = None

    material_item = None

    # <<< usage
    # @generated
    def __init__(self, status=None, work_task=None, work_equipment_asset=None, material_item=None, **kw_args):
        """ Initialises a new 'Usage' instance.
        """
        self.status = status
        self.work_task = work_task
        self.work_equipment_asset = work_equipment_asset
        self.material_item = material_item

        super(Usage, self).__init__(**kw_args)
    # >>> usage


class QualificationRequirement(IdentifiedObject):
    """ Certain skills are required and must be certified in order for a person (typically a member of a crew) to be qualified to work on types of equipment.
    """
    # Qualification identifier. 
    qualification_id = ''

    culabor_items = []

    skills = []

    specifications = []

    work_tasks = []

    # <<< qualification_requirement
    # @generated
    def __init__(self, qualification_id='', culabor_items=[], skills=[], specifications=[], work_tasks=[], **kw_args):
        """ Initialises a new 'QualificationRequirement' instance.
        """
        self.qualification_id = qualification_id
        self.culabor_items = culabor_items
        self.skills = skills
        self.specifications = specifications
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

    erp_issue_inventories = []

    erp_req_line_items = []

    material_items = []

    cumaterial_items = []

    # <<< type_material
    # @generated
    def __init__(self, cost_type='', quantity='', est_unit_cost='', stock_item=False, erp_issue_inventories=[], erp_req_line_items=[], material_items=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'TypeMaterial' instance.
        """
        self.cost_type = cost_type
        self.quantity = quantity
        self.est_unit_cost = est_unit_cost
        self.stock_item = stock_item
        self.erp_issue_inventories = erp_issue_inventories
        self.erp_req_line_items = erp_req_line_items
        self.material_items = material_items
        self.cumaterial_items = cumaterial_items

        super(TypeMaterial, self).__init__(**kw_args)
    # >>> type_material


class CULaborCode(IdentifiedObject):
    """ Labor code associated with various compatible unit labor items.
    """
    # Labor code. 
    code = ''

    status = None

    culabor_items = []

    # <<< culabor_code
    # @generated
    def __init__(self, code='', status=None, culabor_items=[], **kw_args):
        """ Initialises a new 'CULaborCode' instance.
        """
        self.code = code
        self.status = status
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

    work_task = None

    work_cost_detail = None

    # <<< equipment_item
    # @generated
    def __init__(self, cost='', code='', status=None, work_task=None, work_cost_detail=None, **kw_args):
        """ Initialises a new 'EquipmentItem' instance.
        """
        self.cost = cost
        self.code = code
        self.status = status
        self.work_task = work_task
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

    # <<< shift_pattern
    # @generated
    def __init__(self, cycle_count=0, assignment_type='', validity_interval=None, status=None, crews=[], **kw_args):
        """ Initialises a new 'ShiftPattern' instance.
        """
        self.cycle_count = cycle_count
        self.assignment_type = assignment_type
        self.validity_interval = validity_interval
        self.status = status
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

    call_back = None

    erp_persons = []

    # <<< appointment
    # @generated
    def __init__(self, remark='', call_ahead=False, meeting_interval=None, address=None, call_back=None, erp_persons=[], **kw_args):
        """ Initialises a new 'Appointment' instance.
        """
        self.remark = remark
        self.call_ahead = call_ahead
        self.meeting_interval = meeting_interval
        self.address = address
        self.call_back = call_back
        self.erp_persons = erp_persons

        super(Appointment, self).__init__(**kw_args)
    # >>> appointment


class Project(Document):
    """ A collection of related work. For construction projects and maintenance projects, multiple phases may be performed.
    """
    # Overall project budget. 
    budget = ''

    works = []

    business_case = None

    erp_project_accounting = None

    parent_project = None

    sub_projects = []

    requests = []

    # <<< project
    # @generated
    def __init__(self, budget='', works=[], business_case=None, erp_project_accounting=None, parent_project=None, sub_projects=[], requests=[], **kw_args):
        """ Initialises a new 'Project' instance.
        """
        self.budget = budget
        self.works = works
        self.business_case = business_case
        self.erp_project_accounting = erp_project_accounting
        self.parent_project = parent_project
        self.sub_projects = sub_projects
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

    design_location = None

    cugroups = []

    work_tasks = []

    condition_factors = []

    designs = []

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
        self.compatible_units = compatible_units
        self.design_location = design_location
        self.cugroups = cugroups
        self.work_tasks = work_tasks
        self.condition_factors = condition_factors
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

    # All Crews having this Assignment.
    crews = []

    # <<< assignment
    # @generated
    def __init__(self, effective_date='', expiration_date='', crews=[], **kw_args):
        """ Initialises a new 'Assignment' instance.
        """
        self.effective_date = effective_date
        self.expiration_date = expiration_date
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

    type_material = None

    compatible_units = []

    property_units = []

    # <<< cumaterial_item
    # @generated
    def __init__(self, corporate_code='', quantity='', status=None, type_material=None, compatible_units=[], property_units=[], **kw_args):
        """ Initialises a new 'CUMaterialItem' instance.
        """
        self.corporate_code = corporate_code
        self.quantity = quantity
        self.status = status
        self.type_material = type_material
        self.compatible_units = compatible_units
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

    one_call_request = None

    design_locations = []

    # <<< work_location
    # @generated
    def __init__(self, nearest_intersection='', subdivision='', block='', lot='', one_call_request=None, design_locations=[], **kw_args):
        """ Initialises a new 'WorkLocation' instance.
        """
        self.nearest_intersection = nearest_intersection
        self.subdivision = subdivision
        self.block = block
        self.lot = lot
        self.one_call_request = one_call_request
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

    cumaterial_items = []

    compatible_units = []

    # <<< property_unit
    # @generated
    def __init__(self, activity_code='install', property_account='', accounting_usage='', status=None, work_cost_details=[], cumaterial_items=[], compatible_units=[], **kw_args):
        """ Initialises a new 'PropertyUnit' instance.
        """
        self.activity_code = activity_code
        self.property_account = property_account
        self.accounting_usage = accounting_usage
        self.status = status
        self.work_cost_details = work_cost_details
        self.cumaterial_items = cumaterial_items
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

    # <<< cucontractor_item
    # @generated
    def __init__(self, activity_code='', bid_amount='', status=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUContractorItem' instance.
        """
        self.activity_code = activity_code
        self.bid_amount = bid_amount
        self.status = status
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

    work_tasks = []

    work = None

    # <<< work_flow_step
    # @generated
    def __init__(self, sequence_number=0, status=None, work_tasks=[], work=None, **kw_args):
        """ Initialises a new 'WorkFlowStep' instance.
        """
        self.sequence_number = sequence_number
        self.status = status
        self.work_tasks = work_tasks
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

    crew = None

    crafts = []

    work_tasks = []

    # <<< capability
    # @generated
    def __init__(self, performance_factor='', category='', validity_interval=None, status=None, crew=None, crafts=[], work_tasks=[], **kw_args):
        """ Initialises a new 'Capability' instance.
        """
        self.performance_factor = performance_factor
        self.category = category
        self.validity_interval = validity_interval
        self.status = status
        self.crew = crew
        self.crafts = crafts
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

    according_to_schedules = []

    # <<< inspection_data_set
    # @generated
    def __init__(self, inspect_date='', location_condition='', according_to_schedules=[], **kw_args):
        """ Initialises a new 'InspectionDataSet' instance.
        """
        self.inspect_date = inspect_date
        self.location_condition = location_condition
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

    compatible_units = []

    culabor_code = None

    # <<< culabor_item
    # @generated
    def __init__(self, labor_duration='', activity_code='', labor_rate='', status=None, qualification_requirements=[], compatible_units=[], culabor_code=None, **kw_args):
        """ Initialises a new 'CULaborItem' instance.
        """
        self.labor_duration = labor_duration
        self.activity_code = activity_code
        self.labor_rate = labor_rate
        self.status = status
        self.qualification_requirements = qualification_requirements
        self.compatible_units = compatible_units
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

    procedures = []

    cugroup = None

    cuassets = []

    cost_type = None

    cucontractor_items = []

    culabor_items = []

    cuallowable_action = None

    property_unit = None

    design_location_cus = []

    cumaterial_items = []

    # <<< compatible_unit
    # @generated
    def __init__(self, quantity='', est_cost='', cuwork_equipment_items=[], procedures=[], cugroup=None, cuassets=[], cost_type=None, cucontractor_items=[], culabor_items=[], cuallowable_action=None, property_unit=None, design_location_cus=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'CompatibleUnit' instance.
        """
        self.quantity = quantity
        self.est_cost = est_cost
        self.cuwork_equipment_items = cuwork_equipment_items
        self.procedures = procedures
        self.cugroup = cugroup
        self.cuassets = cuassets
        self.cost_type = cost_type
        self.cucontractor_items = cucontractor_items
        self.culabor_items = culabor_items
        self.cuallowable_action = cuallowable_action
        self.property_unit = property_unit
        self.design_location_cus = design_location_cus
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

    type_material = None

    erp_inventory_counts = []

    usages = []

    design_location = None

    work_cost_detail = None

    erp_rec_delv_line_items = []

    erp_poline_items = []

    work_task = None

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
        self.type_material = type_material
        self.erp_inventory_counts = erp_inventory_counts
        self.usages = usages
        self.design_location = design_location
        self.work_cost_detail = work_cost_detail
        self.erp_rec_delv_line_items = erp_rec_delv_line_items
        self.erp_poline_items = erp_poline_items
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

    work_locations = []

    # <<< one_call_request
    # @generated
    def __init__(self, explosives_used=False, marked_indicator=False, marking_instruction='', work_locations=[], **kw_args):
        """ Initialises a new 'OneCallRequest' instance.
        """
        self.explosives_used = explosives_used
        self.marked_indicator = marked_indicator
        self.marking_instruction = marking_instruction
        self.work_locations = work_locations

        super(OneCallRequest, self).__init__(**kw_args)
    # >>> one_call_request


class WorkCostSummary(Document):
    """ A roll up by cost category for the entire cost of a work order. For example, total labor.
    """
    work_cost_detail = None

    # <<< work_cost_summary
    # @generated
    def __init__(self, work_cost_detail=None, **kw_args):
        """ Initialises a new 'WorkCostSummary' instance.
        """
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

    work_task = None

    design_location = None

    work_cost_detail = None

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
        self.work_task = work_task
        self.design_location = design_location
        self.work_cost_detail = work_cost_detail

        super(MiscCostItem, self).__init__(**kw_args)
    # >>> misc_cost_item


class Crew(IdentifiedObject):
    """ A crew is a group of people (ErpPersons) with specific skills, tools, and vehicles.
    """
    # Category by utility's work management standards and practices. 
    category = ''

    # All WorkTasks this Crew participates in.
    work_tasks = []

    vehicles = []

    capabilities = []

    route = None

    work_equipment_assets = []

    shift_patterns = []

    # All SwitchingSchedules executed by this Crew.
    switching_schedules = []

    tools = []

    outage_steps = []

    locations = []

    # All ErpPersons that are members of this Crew.
    crew_members = []

    organisations = []

    # All Assignments for this Crew.
    assignments = []

    # <<< crew
    # @generated
    def __init__(self, category='', work_tasks=[], vehicles=[], capabilities=[], route=None, work_equipment_assets=[], shift_patterns=[], switching_schedules=[], tools=[], outage_steps=[], locations=[], crew_members=[], organisations=[], assignments=[], **kw_args):
        """ Initialises a new 'Crew' instance.
        """
        self.category = category
        self.work_tasks = work_tasks
        self.vehicles = vehicles
        self.capabilities = capabilities
        self.route = route
        self.work_equipment_assets = work_equipment_assets
        self.shift_patterns = shift_patterns
        self.switching_schedules = switching_schedules
        self.tools = tools
        self.outage_steps = outage_steps
        self.locations = locations
        self.crew_members = crew_members
        self.organisations = organisations
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

    erp_project_accounting = None

    design = None

    material_items = []

    work_cost_summary = None

    overhead_cost = None

    labor_items = []

    works = []

    misc_cost_items = []

    equipment_items = []

    cost_type = None

    contractor_items = []

    work_task = None

    property_units = []

    # <<< work_cost_detail
    # @generated
    def __init__(self, debit_flag=False, type_work_cost='', amount='', transaction_date='', erp_project_accounting=None, design=None, material_items=[], work_cost_summary=None, overhead_cost=None, labor_items=[], works=[], misc_cost_items=[], equipment_items=[], cost_type=None, contractor_items=[], work_task=None, property_units=[], **kw_args):
        """ Initialises a new 'WorkCostDetail' instance.
        """
        self.debit_flag = debit_flag
        self.type_work_cost = type_work_cost
        self.amount = amount
        self.transaction_date = transaction_date
        self.erp_project_accounting = erp_project_accounting
        self.design = design
        self.material_items = material_items
        self.work_cost_summary = work_cost_summary
        self.overhead_cost = overhead_cost
        self.labor_items = labor_items
        self.works = works
        self.misc_cost_items = misc_cost_items
        self.equipment_items = equipment_items
        self.cost_type = cost_type
        self.contractor_items = contractor_items
        self.work_task = work_task
        self.property_units = property_units

        super(WorkCostDetail, self).__init__(**kw_args)
    # >>> work_cost_detail


class CUAllowableAction(IdentifiedObject):
    """ Allowed actions: Install, Remove, Transfer, Abandon, etc.
    """
    status = None

    compatible_units = []

    # <<< cuallowable_action
    # @generated
    def __init__(self, status=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUAllowableAction' instance.
        """
        self.status = status
        self.compatible_units = compatible_units

        super(CUAllowableAction, self).__init__(**kw_args)
    # >>> cuallowable_action


class BusinessCase(Document):
    """ Business justification for capital expenditures, usually addressing operations and maintenance costs as well.
    """
    # A codified representation of the business case (i.e., codes for highway relocation, replace substation transformers, etc.). 
    corporate_code = ''

    projects = []

    works = []

    # <<< business_case
    # @generated
    def __init__(self, corporate_code='', projects=[], works=[], **kw_args):
        """ Initialises a new 'BusinessCase' instance.
        """
        self.corporate_code = corporate_code
        self.projects = projects
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

    work_cost_detail = None

    work_task = None

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
        self.erp_persons = erp_persons
        self.work_cost_detail = work_cost_detail
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

    erp_boms = []

    erp_quote_line_item = None

    design_locations = []

    work = None

    condition_factors = []

    work_cost_details = []

    work_tasks = []

    design_locations_cus = []

    # <<< design
    # @generated
    def __init__(self, cost_estimate='', kind='other', price='', erp_boms=[], erp_quote_line_item=None, design_locations=[], work=None, condition_factors=[], work_cost_details=[], work_tasks=[], design_locations_cus=[], **kw_args):
        """ Initialises a new 'Design' instance.
        """
        self.cost_estimate = cost_estimate
        self.kind = kind
        self.price = price
        self.erp_boms = erp_boms
        self.erp_quote_line_item = erp_quote_line_item
        self.design_locations = design_locations
        self.work = work
        self.condition_factors = condition_factors
        self.work_cost_details = work_cost_details
        self.work_tasks = work_tasks
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

    design = None

    design_location_cus = []

    misc_cost_items = []

    switching_schedules = []

    capabilities = []

    usages = []

    overhead_cost = None

    work_flow_step = None

    material_items = []

    labor_items = []

    # All Crews participating in this WorkTask.
    crews = []

    work = None

    contractor_items = []

    equipment_items = []

    work_cost_details = []

    assets = []

    # <<< work_task
    # @generated
    def __init__(self, sched_override='', priority='', qualification_requirements=[], design=None, design_location_cus=[], misc_cost_items=[], switching_schedules=[], capabilities=[], usages=[], overhead_cost=None, work_flow_step=None, material_items=[], labor_items=[], crews=[], work=None, contractor_items=[], equipment_items=[], work_cost_details=[], assets=[], **kw_args):
        """ Initialises a new 'WorkTask' instance.
        """
        self.sched_override = sched_override
        self.priority = priority
        self.qualification_requirements = qualification_requirements
        self.design = design
        self.design_location_cus = design_location_cus
        self.misc_cost_items = misc_cost_items
        self.switching_schedules = switching_schedules
        self.capabilities = capabilities
        self.usages = usages
        self.overhead_cost = overhead_cost
        self.work_flow_step = work_flow_step
        self.material_items = material_items
        self.labor_items = labor_items
        self.crews = crews
        self.work = work
        self.contractor_items = contractor_items
        self.equipment_items = equipment_items
        self.work_cost_details = work_cost_details
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

    design_location_cus = []

    design_locations = []

    # <<< condition_factor
    # @generated
    def __init__(self, kind='material', cf_value='', status=None, designs=[], design_location_cus=[], design_locations=[], **kw_args):
        """ Initialises a new 'ConditionFactor' instance.
        """
        self.kind = kind
        self.cf_value = cf_value
        self.status = status
        self.designs = designs
        self.design_location_cus = design_location_cus
        self.design_locations = design_locations

        super(ConditionFactor, self).__init__(**kw_args)
    # >>> condition_factor


class CUGroup(IdentifiedObject):
    """ A Compatible Unit Group identifies a set of compatible units which may be jointly utilized for estimating and designating jobs.
    """
    status = None

    compatible_units = []

    design_location_cus = []

    child_cugroups = []

    parent_cugroups = []

    # <<< cugroup
    # @generated
    def __init__(self, status=None, compatible_units=[], design_location_cus=[], child_cugroups=[], parent_cugroups=[], **kw_args):
        """ Initialises a new 'CUGroup' instance.
        """
        self.status = status
        self.compatible_units = compatible_units
        self.design_location_cus = design_location_cus
        self.child_cugroups = child_cugroups
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

    work_cost_detail = None

    work_task = None

    # <<< contractor_item
    # @generated
    def __init__(self, bid_amount='', cost='', activity_code='', status=None, erp_payables=[], work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'ContractorItem' instance.
        """
        self.bid_amount = bid_amount
        self.cost = cost
        self.activity_code = activity_code
        self.status = status
        self.erp_payables = erp_payables
        self.work_cost_detail = work_cost_detail
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

    type_asset = None

    compatible_units = []

    # <<< cuasset
    # @generated
    def __init__(self, quantity='', type_asset_code='', status=None, type_asset=None, compatible_units=[], **kw_args):
        """ Initialises a new 'CUAsset' instance.
        """
        self.quantity = quantity
        self.type_asset_code = type_asset_code
        self.status = status
        self.type_asset = type_asset
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

    erp_bom_item_datas = []

    misc_cost_items = []

    design_location_cus = []

    material_items = []

    condition_factors = []

    work_locations = []

    designs = []

    # <<< design_location
    # @generated
    def __init__(self, span_length='', status=None, diagrams=[], erp_bom_item_datas=[], misc_cost_items=[], design_location_cus=[], material_items=[], condition_factors=[], work_locations=[], designs=[], **kw_args):
        """ Initialises a new 'DesignLocation' instance.
        """
        self.span_length = span_length
        self.status = status
        self.diagrams = diagrams
        self.erp_bom_item_datas = erp_bom_item_datas
        self.misc_cost_items = misc_cost_items
        self.design_location_cus = design_location_cus
        self.material_items = material_items
        self.condition_factors = condition_factors
        self.work_locations = work_locations
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

    work_tasks = []

    work_cost_details = []

    # <<< overhead_cost
    # @generated
    def __init__(self, cost='', code='', status=None, work_tasks=[], work_cost_details=[], **kw_args):
        """ Initialises a new 'OverheadCost' instance.
        """
        self.cost = cost
        self.code = code
        self.status = status
        self.work_tasks = work_tasks
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

    type_asset = None

    # <<< cuwork_equipment_item
    # @generated
    def __init__(self, equip_code='', rate='', status=None, compatible_units=[], type_asset=None, **kw_args):
        """ Initialises a new 'CUWorkEquipmentItem' instance.
        """
        self.equip_code = equip_code
        self.rate = rate
        self.status = status
        self.compatible_units = compatible_units
        self.type_asset = type_asset

        super(CUWorkEquipmentItem, self).__init__(**kw_args)
    # >>> cuwork_equipment_item


# <<< inf_work
# @generated
# >>> inf_work

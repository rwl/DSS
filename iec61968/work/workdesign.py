# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from iec61968.documentation.documentinheritance import Document
from  import 
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class DesignLocationCU():
    """  Compatible Unit at a given design location.  
    """
    # The quantity of the CU being assigned to this location. 
    cu_quantity = ''

    # A code that drives instructs the crew what action to perform. 
    cu_action = ''

    # As the same CU can be used for different purposes and accounting purposes, usage must be specified.  Examples include: distribution, transmission, substation. 
    cu_usage = ''

    # A code that helps direct accounting (capital, expense, or accounting treatment). 
    cu_account = ''

    # True if associated electrical equipment is intended to be energized while work is being performed. 
    energization_flag = ''

    # This is the year when a CU that represents an asset is removed 
    year_removal = ''

    compatible_units = []

    cugroups = []

    design_location = None

    condition_factors = []

    work_tasks = []

    designs = []

    # <<< design_location_cu
    # @generated
    def __init__(self, cu_quantity='', cu_action='', cu_usage='', cu_account='', energization_flag='', year_removal='', compatible_units=[], cugroups=[], design_location=None, condition_factors=[], work_tasks=[], designs=[], **kw_args):
        """ Initialises a new 'DesignLocationCU' instance.
        """
        self.cu_quantity = cu_quantity
        self.cu_action = cu_action
        self.cu_usage = cu_usage
        self.cu_account = cu_account
        self.energization_flag = energization_flag
        self.year_removal = year_removal
        self.compatible_units = compatible_units
        self.cugroups = cugroups
        self.design_location = design_location
        self.condition_factors = condition_factors
        self.work_tasks = work_tasks
        self.designs = designs

        super(DesignLocationCU, self).__init__(**kw_args)
    # >>> design_location_cu


class CompatibleUnit(Document):
    """  A compatible unit is a pre-planned job model containing labor, material, and accounting requirements for standardized job planning.
    """
    # The unit of measure at the CU level that applies to the materials. 
    unit_of_measure = ''

    # Estimated total cost for perfoming CU. 
    est_cost = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    design_location_cus = []

    cost_type = None

    cuallowable_action = None

    cugroup = None

    cucontractor_items = []

    culabor_items = []

    cumaterial_items = []

    cuequipment_items = []

    property_unit = None

    cuasset = []

    procedures = []

    # <<< compatible_unit
    # @generated
    def __init__(self, unit_of_measure='', est_cost='', status='', status_date_time='', status_remarks='', design_location_cus=[], cost_type=None, cuallowable_action=None, cugroup=None, cucontractor_items=[], culabor_items=[], cumaterial_items=[], cuequipment_items=[], property_unit=None, cuasset=[], procedures=[], **kw_args):
        """ Initialises a new 'CompatibleUnit' instance.
        """
        self.unit_of_measure = unit_of_measure
        self.est_cost = est_cost
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.design_location_cus = design_location_cus
        self.cost_type = cost_type
        self.cuallowable_action = cuallowable_action
        self.cugroup = cugroup
        self.cucontractor_items = cucontractor_items
        self.culabor_items = culabor_items
        self.cumaterial_items = cumaterial_items
        self.cuequipment_items = cuequipment_items
        self.property_unit = property_unit
        self.cuasset = cuasset
        self.procedures = procedures

        super(CompatibleUnit, self).__init__(**kw_args)
    # >>> compatible_unit


class Design(Document):
    """ A design for consideration by customers, potential customers, or internal work. Note that the Version of design is the revision attribute that is inherited from Document.
    """
    #  Price to customer for implementing design. 
    price = ''

    # Design type: Estimated, As-built, Other.  
    design_type = ''

    # Estimated cost (not price) of design. 
    cost_estimate = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    work_tasks = []

    work = None

    design_locations = []

    erp_boms = []

    work_cost_details = []

    condition_factors = []

    erp_quote_line_item = None

    design_locations_cus = []

    # <<< design
    # @generated
    def __init__(self, price='', design_type='', cost_estimate='', status='', status_date_time='', status_remarks='', work_tasks=[], work=None, design_locations=[], erp_boms=[], work_cost_details=[], condition_factors=[], erp_quote_line_item=None, design_locations_cus=[], **kw_args):
        """ Initialises a new 'Design' instance.
        """
        self.price = price
        self.design_type = design_type
        self.cost_estimate = cost_estimate
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.work_tasks = work_tasks
        self.work = work
        self.design_locations = design_locations
        self.erp_boms = erp_boms
        self.work_cost_details = work_cost_details
        self.condition_factors = condition_factors
        self.erp_quote_line_item = erp_quote_line_item
        self.design_locations_cus = design_locations_cus

        super(Design, self).__init__(**kw_args)
    # >>> design


class DesignLocation():
    """  A logical part of the design (e.g., pole and all equipment on a pole).  This includes points and spans.
    """
    # The legth of the span from the previous pole to this pole. 
    span_length = ''

    designs = []

    design_location_cus = []

    misc_cost_items = []

    erp_bom_item_data = []

    condition_factors = []

    diagrams = []

    work_locations = []

    # <<< design_location
    # @generated
    def __init__(self, span_length='', designs=[], design_location_cus=[], misc_cost_items=[], erp_bom_item_data=[], condition_factors=[], diagrams=[], work_locations=[], **kw_args):
        """ Initialises a new 'DesignLocation' instance.
        """
        self.span_length = span_length
        self.designs = designs
        self.design_location_cus = design_location_cus
        self.misc_cost_items = misc_cost_items
        self.erp_bom_item_data = erp_bom_item_data
        self.condition_factors = condition_factors
        self.diagrams = diagrams
        self.work_locations = work_locations

        super(DesignLocation, self).__init__(**kw_args)
    # >>> design_location


class WorkTask(Document):
    """  A set of tasks is required to implement a design.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Reason code or explanation for why this went to the current status. 
    status_reason = ''

    # The type of Work Task.  
    type_work_task = ''

    # The priority of this work task.  
    task_priority = ''

    # If other than 'None', override schedule and perform this task in accordance with instructions specified here.   
    sched_override = ''

    contractor_items = []

    labor_items = []

    overhead = None

    work_cost_details = []

    misc_cost_items = []

    equipment_items = []

    design = None

    design_location_cus = []

    work_flow_step = None

    material_items = []

    qualification_requirements = []

    switching_schedule = []

    work = None

    asset_lists = []

    equipment_lists = []

    capabilities = []

    usages = []

    # <<< work_task
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', status_reason='', type_work_task='', task_priority='', sched_override='', contractor_items=[], labor_items=[], overhead=None, work_cost_details=[], misc_cost_items=[], equipment_items=[], design=None, design_location_cus=[], work_flow_step=None, material_items=[], qualification_requirements=[], switching_schedule=[], work=None, asset_lists=[], equipment_lists=[], capabilities=[], usages=[], **kw_args):
        """ Initialises a new 'WorkTask' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.status_reason = status_reason
        self.type_work_task = type_work_task
        self.task_priority = task_priority
        self.sched_override = sched_override
        self.contractor_items = contractor_items
        self.labor_items = labor_items
        self.overhead = overhead
        self.work_cost_details = work_cost_details
        self.misc_cost_items = misc_cost_items
        self.equipment_items = equipment_items
        self.design = design
        self.design_location_cus = design_location_cus
        self.work_flow_step = work_flow_step
        self.material_items = material_items
        self.qualification_requirements = qualification_requirements
        self.switching_schedule = switching_schedule
        self.work = work
        self.asset_lists = asset_lists
        self.equipment_lists = equipment_lists
        self.capabilities = capabilities
        self.usages = usages

        super(WorkTask, self).__init__(**kw_args)
    # >>> work_task


class MiscCostItem():
    """ Various cost items that are not associated with compatible units.  Examples include rental equipment, labor, materials, contractor costs, permits - anything not covered in a CU.
    """
    # The quantity of the misc. item being assigned to this location. 
    quantity = ''

    # The unit of measure for this misc. item. 
    units = ''

    # The cost per unit for this misc. item. 
    cost_per_unit = ''

    # The cost category for accounting, such as material, labor, vehicle, contractor, equipment, overhead. 
    cost_type = ''

    # This drives the accounting treatment for this misc. item. 
    account = ''

    # External Reference ID (e.g. PO#, Serial #)  
    external_ref_id = ''

    work_cost_detail = None

    work_task = None

    design_location = None

    # <<< misc_cost_item
    # @generated
    def __init__(self, quantity='', units='', cost_per_unit='', cost_type='', account='', external_ref_id='', work_cost_detail=None, work_task=None, design_location=None, **kw_args):
        """ Initialises a new 'MiscCostItem' instance.
        """
        self.quantity = quantity
        self.units = units
        self.cost_per_unit = cost_per_unit
        self.cost_type = cost_type
        self.account = account
        self.external_ref_id = external_ref_id
        self.work_cost_detail = work_cost_detail
        self.work_task = work_task
        self.design_location = design_location

        super(MiscCostItem, self).__init__(**kw_args)
    # >>> misc_cost_item


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


class ConditionFactor():
    """ This is to specify the various condition factors for a design that may alter the cost estimate or the allocation.
    """
    # The actual value of the condition factor, such as labor flat fee or percentage. 
    cf_value = ''

    # The type of condition factos such as labor, account allocation, mateiral, travel, etc. 
    cf_type = ''

    design_location_cus = []

    design_locations = []

    designs = []

    # <<< condition_factor
    # @generated
    def __init__(self, cf_value='', cf_type='', design_location_cus=[], design_locations=[], designs=[], **kw_args):
        """ Initialises a new 'ConditionFactor' instance.
        """
        self.cf_value = cf_value
        self.cf_type = cf_type
        self.design_location_cus = design_location_cus
        self.design_locations = design_locations
        self.designs = designs

        super(ConditionFactor, self).__init__(**kw_args)
    # >>> condition_factor


class CUAllowableAction():
    """ Allowed actions: Install, Remove, Transfer, Abandon, etc.
    """
    compatible_units = []

    # <<< cuallowable_action
    # @generated
    def __init__(self, compatible_units=[], **kw_args):
        """ Initialises a new 'CUAllowableAction' instance.
        """
        self.compatible_units = compatible_units

        super(CUAllowableAction, self).__init__(**kw_args)
    # >>> cuallowable_action


class WorkDesignVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_design_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkDesignVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkDesignVersion, self).__init__(**kw_args)
    # >>> work_design_version


# <<< workdesign
# @generated
# >>> workdesign

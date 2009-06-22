# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from  import 
from  import 
from  import 
from iec61968.documentation.documentinheritance import Document
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class ContractorItem():
    """  Contractor information for work task.
    """
    #  Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    #  The amount that a given contractor will charge for performing this unit of work. 
    bid_amount = ''

    # The total amount charged. 
    cost = ''

    work_task = None

    work_cost_detail = None

    erp_payables = []

    # <<< contractor_item
    # @generated
    def __init__(self, activity_code='', bid_amount='', cost='', work_task=None, work_cost_detail=None, erp_payables=[], **kw_args):
        """ Initialises a new 'ContractorItem' instance.
        """
        self.activity_code = activity_code
        self.bid_amount = bid_amount
        self.cost = cost
        self.work_task = work_task
        self.work_cost_detail = work_cost_detail
        self.erp_payables = erp_payables

        super(ContractorItem, self).__init__(**kw_args)
    # >>> contractor_item


class LaborItem():
    """  Labor used for work order.
    """
    #  Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    #  The number of hours required to perform work. 
    labor_hours = ''

    #  The labor rate applied for work. 
    labor_rate = ''

    # Total cost for labor.  Note that this may not be able to be derived from labor rate and time charged. 
    cost = ''

    work_tasks = None

    work_cost_detail = None

    erp_persons = []

    # <<< labor_item
    # @generated
    def __init__(self, activity_code='', labor_hours='', labor_rate='', cost='', work_tasks=None, work_cost_detail=None, erp_persons=[], **kw_args):
        """ Initialises a new 'LaborItem' instance.
        """
        self.activity_code = activity_code
        self.labor_hours = labor_hours
        self.labor_rate = labor_rate
        self.cost = cost
        self.work_tasks = work_tasks
        self.work_cost_detail = work_cost_detail
        self.erp_persons = erp_persons

        super(LaborItem, self).__init__(**kw_args)
    # >>> labor_item


class Overhead():
    """  Overhead cost applied to work order.
    """
    #  Overhead code. 
    code = ''

    # The overhead cost to be applied. 
    cost = ''

    work_tasks = []

    work_cost_detail = []

    # <<< overhead
    # @generated
    def __init__(self, code='', cost='', work_tasks=[], work_cost_detail=[], **kw_args):
        """ Initialises a new 'Overhead' instance.
        """
        self.code = code
        self.cost = cost
        self.work_tasks = work_tasks
        self.work_cost_detail = work_cost_detail

        super(Overhead, self).__init__(**kw_args)
    # >>> overhead


class PropertyUnit():
    """  Unit of property for reporting purposes.
    """
    #  Activity code identifies a specific and distinguishable type of work to: Install, Remove, Abandon, Transfer. 
    activity_code = ''

    # This is used for property record accounting.  For example, in the USA, this would be a FERC account. 
    property_account = ''

    # A code that identifies appropriate type of property accounts such as distribution, streetlgihts, communications. 
    accounting_usage = ''

    work_cost_detail = []

    compatible_units = []

    cumaterial_items = []

    # <<< property_unit
    # @generated
    def __init__(self, activity_code='', property_account='', accounting_usage='', work_cost_detail=[], compatible_units=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'PropertyUnit' instance.
        """
        self.activity_code = activity_code
        self.property_account = property_account
        self.accounting_usage = accounting_usage
        self.work_cost_detail = work_cost_detail
        self.compatible_units = compatible_units
        self.cumaterial_items = cumaterial_items

        super(PropertyUnit, self).__init__(**kw_args)
    # >>> property_unit


class WorkCostDetail(Document):
    """ A collection of all of the individual cost items collected from multiple sources.  
    """
    # Amount in designated currency for work, either a total or an individual element.  As defined in the attribute 'type,' multiple instances are applicable to each work for: planned cost, actual cost, authorized cost, budgeted cost, forecasted cost, other.    
    amount = ''

    # True if 'amount' attribute is a debit, false if it is a credit. 
    debit_flag = ''

    # Date that amount is posted to the work. 
    transaction_date = ''

    # The type of cost. 
    cost_type = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    work = []

    labor_items = []

    material_items = []

    contractor_items = []

    overhead = None

    property_units = []

    erp_project_accounting = None

    misc_cost_item = []

    work_task = None

    work_cost_summary = None

    equipment_items = []

    design = None

    # <<< work_cost_detail
    # @generated
    def __init__(self, amount='', debit_flag='', transaction_date='', cost_type='', status='', status_date_time='', status_remarks='', work=[], labor_items=[], material_items=[], contractor_items=[], overhead=None, property_units=[], erp_project_accounting=None, misc_cost_item=[], work_task=None, work_cost_summary=None, equipment_items=[], design=None, **kw_args):
        """ Initialises a new 'WorkCostDetail' instance.
        """
        self.amount = amount
        self.debit_flag = debit_flag
        self.transaction_date = transaction_date
        self.cost_type = cost_type
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.work = work
        self.labor_items = labor_items
        self.material_items = material_items
        self.contractor_items = contractor_items
        self.overhead = overhead
        self.property_units = property_units
        self.erp_project_accounting = erp_project_accounting
        self.misc_cost_item = misc_cost_item
        self.work_task = work_task
        self.work_cost_summary = work_cost_summary
        self.equipment_items = equipment_items
        self.design = design

        super(WorkCostDetail, self).__init__(**kw_args)
    # >>> work_cost_detail


class CostType():
    """ A categorization for resources, often costs, in accounting transactions.  Examples include: material components, building in service, coal sales, overhead, etc.
    """
    # A codified representation of the resource element. 
    code = ''

    # The level of the resource element in the hierarchy of resource elements (recursive relationship). 
    level = ''

    # True if an amount can be assigned to the resource element (e.g., building in service, transmission plant, software development capital); false otherwise (e.g., internal labor, material components). 
    amount_assignment_flag = ''

    # The stage for which this costType applies: estimated design, estimated actual or actual actual.  
    stage = ''

    parent_cost_type = None

    child_cost_type = []

    erp_journal_entries = []

    compatible_units = []

    # <<< cost_type
    # @generated
    def __init__(self, code='', level='', amount_assignment_flag='', stage='', parent_cost_type=None, child_cost_type=[], erp_journal_entries=[], compatible_units=[], **kw_args):
        """ Initialises a new 'CostType' instance.
        """
        self.code = code
        self.level = level
        self.amount_assignment_flag = amount_assignment_flag
        self.stage = stage
        self.parent_cost_type = parent_cost_type
        self.child_cost_type = child_cost_type
        self.erp_journal_entries = erp_journal_entries
        self.compatible_units = compatible_units

        super(CostType, self).__init__(**kw_args)
    # >>> cost_type


class WorkCostSummary(Document):
    """ A roll up by cost category for the entire cost of a work order.  For example, total lablor.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    work_cost_detail = None

    # <<< work_cost_summary
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', work_cost_detail=None, **kw_args):
        """ Initialises a new 'WorkCostSummary' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.work_cost_detail = work_cost_detail

        super(WorkCostSummary, self).__init__(**kw_args)
    # >>> work_cost_summary


class EquipmentItem():
    """  An equipment item, such as a vehicle, used for a work order.
    """
    # Code for type of vehicle. 
    code = ''

    # The cost for vehcile usage. 
    cost = ''

    work_cost_detail = None

    work_task = None

    # <<< equipment_item
    # @generated
    def __init__(self, code='', cost='', work_cost_detail=None, work_task=None, **kw_args):
        """ Initialises a new 'EquipmentItem' instance.
        """
        self.code = code
        self.cost = cost
        self.work_cost_detail = work_cost_detail
        self.work_task = work_task

        super(EquipmentItem, self).__init__(**kw_args)
    # >>> equipment_item


class WorkClosingVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_closing_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkClosingVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkClosingVersion, self).__init__(**kw_args)
    # >>> work_closing_version


# <<< workclosing
# @generated
# >>> workclosing

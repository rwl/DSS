# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from iec61968.documentation.documentinheritance import Document
from  import 
from iec61968.core2.locations import Location
from iec61968.core2.activityrecords import ActivityRecord

# <<< imports
# @generated
# >>> imports

class MaterialItem():
    """ The physical consumable supply used for work and other purposes.  It includes items such as nuts, bolts, brackets, glue, etc.
    """
    #  Code for material. 
    material_code = ''

    # The actual cost of this particular material in this particular quantity. 
    actual_cost = ''

    # The quantity of material used. 
    quantity = ''

    # Unit of measure for the quantity. 
    unit_of_measure = ''

    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    # External reference identifier for this actual material item such as a purchase order number, a serial number, etc. 
    external_ref_id = ''

    # Description of the cost. 
    cost_description = ''

    # The account to which this actual material item is charged. 
    account = ''

    erp_rec_delv_line_items = []

    erp_inventory_count = []

    erp_poline_items = []

    work_cost_detail = None

    work_task = None

    usages = []

    type_material = None

    # <<< material_item
    # @generated
    def __init__(self, material_code='', actual_cost='', quantity='', unit_of_measure='', cost_type='', external_ref_id='', cost_description='', account='', erp_rec_delv_line_items=[], erp_inventory_count=[], erp_poline_items=[], work_cost_detail=None, work_task=None, usages=[], type_material=None, **kw_args):
        """ Initialises a new 'MaterialItem' instance.
        """
        self.material_code = material_code
        self.actual_cost = actual_cost
        self.quantity = quantity
        self.unit_of_measure = unit_of_measure
        self.cost_type = cost_type
        self.external_ref_id = external_ref_id
        self.cost_description = cost_description
        self.account = account
        self.erp_rec_delv_line_items = erp_rec_delv_line_items
        self.erp_inventory_count = erp_inventory_count
        self.erp_poline_items = erp_poline_items
        self.work_cost_detail = work_cost_detail
        self.work_task = work_task
        self.usages = usages
        self.type_material = type_material

        super(MaterialItem, self).__init__(**kw_args)
    # >>> material_item


class AccessPermit(Document):
    """ A permit is sometimes needed to provide legal access to land or equipment.  For example, local authority permission for road works.
    """
    # Permit identifier. 
    permit_id = ''

    #  Type of permit.  Examples include pole, electrical underground, etc. 
    permit_type = ''

    #  Date that permit became official. 
    date_effective = ''

    #  Permit application number that is used by municipality, state, province, etc. 
    application_number = ''

    #  Permit expiration date. 
    expiration_date = ''

    #  Total cost of permit. 
    payment = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # <<< access_permit
    # @generated
    def __init__(self, permit_id='', permit_type='', date_effective='', application_number='', expiration_date='', payment='', status='', status_date_time='', status_remarks='', **kw_args):
        """ Initialises a new 'AccessPermit' instance.
        """
        self.permit_id = permit_id
        self.permit_type = permit_type
        self.date_effective = date_effective
        self.application_number = application_number
        self.expiration_date = expiration_date
        self.payment = payment
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks

        super(AccessPermit, self).__init__(**kw_args)
    # >>> access_permit


class Regulation(Document):
    """  Special requirements and/or regulations may pertain to certain types of assets or work.  For example, fire protection and scaffolding.
    """
    #  Regulation identifier. 
    regulation_id = ''

    #  Type of regulation.  Examples include fire protection and scaffolding. 
    regulation_type = ''

    #  External reference to regulation, if applicable. 
    reference_number = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # <<< regulation
    # @generated
    def __init__(self, regulation_id='', regulation_type='', reference_number='', status='', status_date_time='', status_remarks='', **kw_args):
        """ Initialises a new 'Regulation' instance.
        """
        self.regulation_id = regulation_id
        self.regulation_type = regulation_type
        self.reference_number = reference_number
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks

        super(Regulation, self).__init__(**kw_args)
    # >>> regulation


class OneCallRequest(Document):
    """ A request for other utilities to mark their underground facilities prior to commencement of construction and/or maintenance.
    """
    # True if work location has been marked, for example for a dig area. 
    marked_indicator = ''

    # True if explosives have been or are planned to be used. 
    explosives_used = ''

    # Instructions for marking a dig area, if applicable. 
    marking_instructions = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of One Call Request. 
    one_cal_req_type = ''

    work_locations = []

    # <<< one_call_request
    # @generated
    def __init__(self, marked_indicator='', explosives_used='', marking_instructions='', status='', status_date_time='', status_remarks='', one_cal_req_type='', work_locations=[], **kw_args):
        """ Initialises a new 'OneCallRequest' instance.
        """
        self.marked_indicator = marked_indicator
        self.explosives_used = explosives_used
        self.marking_instructions = marking_instructions
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.one_cal_req_type = one_cal_req_type
        self.work_locations = work_locations

        super(OneCallRequest, self).__init__(**kw_args)
    # >>> one_call_request


class Usage():
    """ The way material and assets are used to perform a certain type of work task.  The way is is described in text in the inheritied description attribute.
    """
    material_item = None

    work_task = None

    equipment_asset = None

    # <<< usage
    # @generated
    def __init__(self, material_item=None, work_task=None, equipment_asset=None, **kw_args):
        """ Initialises a new 'Usage' instance.
        """
        self.material_item = material_item
        self.work_task = work_task
        self.equipment_asset = equipment_asset

        super(Usage, self).__init__(**kw_args)
    # >>> usage


class WorkScheduleVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_schedule_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkScheduleVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkScheduleVersion, self).__init__(**kw_args)
    # >>> work_schedule_version


class WorkLocation(Location):
    """ For various forms of work such as a one call request, information about a particular location.
    """
    # The names of streets at the nearest intersection to work area. 
    nearest_intersection = ''

    # Name, identifier, or description of the subdivision, if applicable, in which work is to occur. 
    subdivision = ''

    # Name, identifier, or description of the lot, if applicable, in which work is to occur. 
    lot = ''

    # Name, identifier, or description of the block, if applicable, in which work is to occur. 
    block = ''

    design_locations = []

    one_call_request = None

    # <<< work_location
    # @generated
    def __init__(self, nearest_intersection='', subdivision='', lot='', block='', design_locations=[], one_call_request=None, **kw_args):
        """ Initialises a new 'WorkLocation' instance.
        """
        self.nearest_intersection = nearest_intersection
        self.subdivision = subdivision
        self.lot = lot
        self.block = block
        self.design_locations = design_locations
        self.one_call_request = one_call_request

        super(WorkLocation, self).__init__(**kw_args)
    # >>> work_location


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


# <<< workschedule
# @generated
# >>> workschedule

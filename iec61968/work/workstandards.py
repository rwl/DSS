# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from iec61968.documentation.documentinheritance import Document

# <<< imports
# @generated
# >>> imports

class CUContractorItem():
    """  Compatible unit contractor item.
    """
    #  Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    #  The amount that a given contractor will charge for performing this unit of work. 
    bid_amount = ''

    compatible_units = []

    # <<< cucontractor_item
    # @generated
    def __init__(self, activity_code='', bid_amount='', compatible_units=[], **kw_args):
        """ Initialises a new 'CUContractorItem' instance.
        """
        self.activity_code = activity_code
        self.bid_amount = bid_amount
        self.compatible_units = compatible_units

        super(CUContractorItem, self).__init__(**kw_args)
    # >>> cucontractor_item


class CUGroup():
    """  A Compatible Unit Group identifies a set of compatible units which may be jointly utilized for estimating and designating jobs.
    """
    design_location_cus = []

    parent_cugroup = []

    child_cugroup = []

    compatible_units = []

    # <<< cugroup
    # @generated
    def __init__(self, design_location_cus=[], parent_cugroup=[], child_cugroup=[], compatible_units=[], **kw_args):
        """ Initialises a new 'CUGroup' instance.
        """
        self.design_location_cus = design_location_cus
        self.parent_cugroup = parent_cugroup
        self.child_cugroup = child_cugroup
        self.compatible_units = compatible_units

        super(CUGroup, self).__init__(**kw_args)
    # >>> cugroup


class CULaborItem():
    """  Compatible unit labor item.
    """
    #  Activity code identifies a specific and distinguishable unit of work. 
    activity_code = ''

    #  the number of hours estimated to perform work. 
    labor_hours = ''

    #  The labor rate applied for work. 
    labor_rate = ''

    compatible_units = []

    qualification_requirements = []

    culabor_code = None

    # <<< culabor_item
    # @generated
    def __init__(self, activity_code='', labor_hours='', labor_rate='', compatible_units=[], qualification_requirements=[], culabor_code=None, **kw_args):
        """ Initialises a new 'CULaborItem' instance.
        """
        self.activity_code = activity_code
        self.labor_hours = labor_hours
        self.labor_rate = labor_rate
        self.compatible_units = compatible_units
        self.qualification_requirements = qualification_requirements
        self.culabor_code = culabor_code

        super(CULaborItem, self).__init__(**kw_args)
    # >>> culabor_item


class CUMaterialItem():
    """ Compatible unit of a consumable supply item.  For example, nuts, bolts, brackets, glue, etc.
    """
    #  Code for material. 
    type_material_code = ''

    # The quantity of the TypeMaterial for this CU.  This value is used to determine estimated costs based on a per unit cost or a cost per unit length specified in the TypeMaterial. 
    cu_quantity = ''

    compatible_units = []

    property_units = []

    type_material = None

    # <<< cumaterial_item
    # @generated
    def __init__(self, type_material_code='', cu_quantity='', compatible_units=[], property_units=[], type_material=None, **kw_args):
        """ Initialises a new 'CUMaterialItem' instance.
        """
        self.type_material_code = type_material_code
        self.cu_quantity = cu_quantity
        self.compatible_units = compatible_units
        self.property_units = property_units
        self.type_material = type_material

        super(CUMaterialItem, self).__init__(**kw_args)
    # >>> cumaterial_item


class CUEquipmentItem():
    """ Compatible unit for various types of EquipmentAssets, including vehicles.  Note that EquipmentAsset inherits the association between Asset and AssetModel, and is therefore associated with TypeAsset.
    """
    # The equipment type code. 
    equip_code = ''

    # Standard usage rate for the type of vehicle. 
    rate = ''

    compatible_units = []

    type_asset = None

    # <<< cuequipment_item
    # @generated
    def __init__(self, equip_code='', rate='', compatible_units=[], type_asset=None, **kw_args):
        """ Initialises a new 'CUEquipmentItem' instance.
        """
        self.equip_code = equip_code
        self.rate = rate
        self.compatible_units = compatible_units
        self.type_asset = type_asset

        super(CUEquipmentItem, self).__init__(**kw_args)
    # >>> cuequipment_item


class CULaborCode():
    """ Labor code associated with various compatible unit labor items.
    """
    # Labor code. 
    code = ''

    culabor_items = []

    # <<< culabor_code
    # @generated
    def __init__(self, code='', culabor_items=[], **kw_args):
        """ Initialises a new 'CULaborCode' instance.
        """
        self.code = code
        self.culabor_items = culabor_items

        super(CULaborCode, self).__init__(**kw_args)
    # >>> culabor_code


class CUAsset():
    """ Compatible unit for various types of assets such as transformers switches, substation fences, poles, etc..
    """
    # The code for this type of asset. 
    type_asset_code = ''

    # The quantity of the type asset within the CU 
    cu_quantity = ''

    compatible_units = []

    type_asset = None

    # <<< cuasset
    # @generated
    def __init__(self, type_asset_code='', cu_quantity='', compatible_units=[], type_asset=None, **kw_args):
        """ Initialises a new 'CUAsset' instance.
        """
        self.type_asset_code = type_asset_code
        self.cu_quantity = cu_quantity
        self.compatible_units = compatible_units
        self.type_asset = type_asset

        super(CUAsset, self).__init__(**kw_args)
    # >>> cuasset


class WorkStandardsVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_standards_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkStandardsVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkStandardsVersion, self).__init__(**kw_args)
    # >>> work_standards_version


class TypeMaterial(Document):
    """ A TypeMaterial is documentation for a generic material item that may be used for design, work and other purposes.  Any number of MaterialItems manufactured by various vendors may be used to perform this TypeMaterial.  Note that class analagous to 'AssetModel' is not used for material items.  This is because in some cases, for example, a utility sets up a Master material record for a 3 inch long ¾ inch steel bolt and they do not necessarily care what specific supplier is providing the material item.  As different vendors are used to supply the part, the Stock Code of the material item can stay the same.  In other cases, each time the vendor changes, a new stock code is set up so they can track material used by vendor.  Therefore a Material Item 'Model' is not typically needed.
    """
    # True if item is a stock item, false otherwise. 
    stock_item = ''

    # The estimated unit cost of the this type of asset.  It is either for a unit cost or cost per unit length.  Cost is for material or asset only and does not include labor to install/construct or configure it. 
    est_unit_cost = ''

    # Unit of measure for the quantity. 
    unit_of_measure = ''

    # The category of cost to which this Material Item belongs. 
    cost_type = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_req_line_item = []

    erp_issue_inventory = []

    material_items = []

    cumaterial_items = []

    # <<< type_material
    # @generated
    def __init__(self, stock_item='', est_unit_cost='', unit_of_measure='', cost_type='', status='', status_date_time='', status_remarks='', erp_req_line_item=[], erp_issue_inventory=[], material_items=[], cumaterial_items=[], **kw_args):
        """ Initialises a new 'TypeMaterial' instance.
        """
        self.stock_item = stock_item
        self.est_unit_cost = est_unit_cost
        self.unit_of_measure = unit_of_measure
        self.cost_type = cost_type
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_req_line_item = erp_req_line_item
        self.erp_issue_inventory = erp_issue_inventory
        self.material_items = material_items
        self.cumaterial_items = cumaterial_items

        super(TypeMaterial, self).__init__(**kw_args)
    # >>> type_material


# <<< workstandards
# @generated
# >>> workstandards

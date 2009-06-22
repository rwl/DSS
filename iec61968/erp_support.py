# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" The Enterprise Resource Planning (ERP) Support  Package contains portions of the model defined by ERP standards like those proposed by the Open Applications Group (OAG).  This package is provided to facilitate integration among electric utility applications (CIM) and enterprise resource planning (ERP) applications (as defined by OAG).  Rather than inventing new CIM classes that accomplish similar functionality as in existing ERP models, the preferred approach is to use and extend ERP classes as appropriate in other packages.     If a model other that the OAG standard is used as a basis for ERP integration, the utility classes labeld 'Erp...' should be associated with the appropriate classes of that standard. In fact, definitions of 'Erp...' classes are based on OAG Nouns to facilitate this process. 
"""

from iec61968.documentation.documentinheritance import Document
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class ErpSalesOrder(Document):
    """ General purpose Sales Order is used for utility service orders, etc.    As used by the OAG, the SalesOrder is a step beyond a PurchaseOrder in that the receiving entity of the order also communicates SalesInformoration about the Order along with the Order itself. 
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of sales order. 
    type_so = ''

    # <<< erp_sales_order
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_so='', **kw_args):
        """ Initialises a new 'ErpSalesOrder' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_so = type_so

        super(ErpSalesOrder, self).__init__(**kw_args)
    # >>> erp_sales_order


class ErpAddress():
    """ General purpose address information used for utilities.  Where sufficient, fields are used from the ERP model.  However, to support additional querying that is typical of utilities, the address line is broken into more specific fields.
    """
    # The designator of the specific location on the street. 
    street_number = ''

    # The name of the street. 
    street_name = ''

    # Suffix to the street name.  For examle, North, South, East, West.   
    street_suffix = ''

    # Prefix to the street name.  For examle, North, South, East, West.   
    street_prefix = ''

    # Type of street.  Examples include: street, circle, boulevard, avenue, road, drive,  etc. 
    street_type = ''

    # The post office box. 
    po_box = ''

    # The number of the apartment or suite. 
    suite_number = ''

    # Additional address information, for example a mailstop. 
    address_general = ''

    # The code for a particular town. 
    town_code = ''

    # A Section is part of a township. For example, it is common for there to be 36 section per township. 
    section = ''

    # Name of city. 
    city = ''

    # Name of the state or province. 
    state_or_province = ''

    # Name of the country. 
    country = ''

    # Postal code for the address. 
    postal_code = ''

    # True if this location is within the legal geographical boundaries of the specified city. 
    within_city_limits = ''

    locations = []

    appointments = []

    # <<< erp_address
    # @generated
    def __init__(self, street_number='', street_name='', street_suffix='', street_prefix='', street_type='', po_box='', suite_number='', address_general='', town_code='', section='', city='', state_or_province='', country='', postal_code='', within_city_limits='', locations=[], appointments=[], **kw_args):
        """ Initialises a new 'ErpAddress' instance.
        """
        self.street_number = street_number
        self.street_name = street_name
        self.street_suffix = street_suffix
        self.street_prefix = street_prefix
        self.street_type = street_type
        self.po_box = po_box
        self.suite_number = suite_number
        self.address_general = address_general
        self.town_code = town_code
        self.section = section
        self.city = city
        self.state_or_province = state_or_province
        self.country = country
        self.postal_code = postal_code
        self.within_city_limits = within_city_limits
        self.locations = locations
        self.appointments = appointments

        super(ErpAddress, self).__init__(**kw_args)
    # >>> erp_address


class ErpPerson():
    """ General purpose information for name and other information to contact people.
    """
    #  Person's last (family, sir) name. 
    last_name = ''

    # Person's first name. 
    first_name = ''

    #  Middle name(s) or initial(s). 
    m_name = ''

    #  Special service needs for the person (contact) are described; examples include life support, etc. 
    special_needs = ''

    # Unique identifier for person relative to it's governing authority, for example a federal tax identifier (such as a Social Security number in the United States). 
    government_id = ''

    # A prefix or title for the person's name, such as Miss, Mister, Doctor, etc. 
    prefix = ''

    # A suffix for the person's name, such as II, III, etc. 
    suffix = ''

    measurement_value = []

    customer_data = None

    activity_records = []

    locations = []

    properties = []

    organisations = []

    documents = []

    erp_personnel = None

    erp_competency = None

    skills = []

    electronic_addresses = []

    crafts = []

    change_items = []

    schedule_steps = []

    call_backs = []

    erp_telephone_numbers = []

    labor_items = []

    appointments = []

    # <<< erp_person
    # @generated
    def __init__(self, last_name='', first_name='', m_name='', special_needs='', government_id='', prefix='', suffix='', measurement_value=[], customer_data=None, activity_records=[], locations=[], properties=[], organisations=[], documents=[], erp_personnel=None, erp_competency=None, skills=[], electronic_addresses=[], crafts=[], change_items=[], schedule_steps=[], call_backs=[], erp_telephone_numbers=[], labor_items=[], appointments=[], **kw_args):
        """ Initialises a new 'ErpPerson' instance.
        """
        self.last_name = last_name
        self.first_name = first_name
        self.m_name = m_name
        self.special_needs = special_needs
        self.government_id = government_id
        self.prefix = prefix
        self.suffix = suffix
        self.measurement_value = measurement_value
        self.customer_data = customer_data
        self.activity_records = activity_records
        self.locations = locations
        self.properties = properties
        self.organisations = organisations
        self.documents = documents
        self.erp_personnel = erp_personnel
        self.erp_competency = erp_competency
        self.skills = skills
        self.electronic_addresses = electronic_addresses
        self.crafts = crafts
        self.change_items = change_items
        self.schedule_steps = schedule_steps
        self.call_backs = call_backs
        self.erp_telephone_numbers = erp_telephone_numbers
        self.labor_items = labor_items
        self.appointments = appointments

        super(ErpPerson, self).__init__(**kw_args)
    # >>> erp_person


class ErpPersonnel():
    """ Information that applies to the basic data about a utility person.  This is used by ERP applications to transfer Personnel data for a worker.
    """
    erp_persons = []

    # <<< erp_personnel
    # @generated
    def __init__(self, erp_persons=[], **kw_args):
        """ Initialises a new 'ErpPersonnel' instance.
        """
        self.erp_persons = erp_persons

        super(ErpPersonnel, self).__init__(**kw_args)
    # >>> erp_personnel


class ErpCompetency():
    """ Information that describes aptitudes of an utility employee.  Unlike Skills that a ErpPerson must be certified to perform before undertaking certain type of assignments (to be able to perfrom a Craft), ErpCompetency has more to do with typical Human Resource (HR) matters such as schooling, training, etc. 
    """
    erp_persons = []

    # <<< erp_competency
    # @generated
    def __init__(self, erp_persons=[], **kw_args):
        """ Initialises a new 'ErpCompetency' instance.
        """
        self.erp_persons = erp_persons

        super(ErpCompetency, self).__init__(**kw_args)
    # >>> erp_competency


class ErpSiteLevelData():
    """ For a utility, general information that describes physical locations of organizations or the location codes and their meanings.    This enables ERP applications to ensure that the physical location identifiers are synchronized between the business applications.  
    """
    property = None

    # <<< erp_site_level_data
    # @generated
    def __init__(self, property=None, **kw_args):
        """ Initialises a new 'ErpSiteLevelData' instance.
        """
        self.property = property

        super(ErpSiteLevelData, self).__init__(**kw_args)
    # >>> erp_site_level_data


class ErpInventory():
    """ Utility inventory related information about an item or part. This data type is not for describing the item and its attributes.  This is used by ERP applications  to enable the synchronization of Inventory data that exists on separate Item Master databases.  This data is not the master data that describes the attributes of the item such as dimensions, weight, or unit of measure.  This is data that describes the ITEM as it exists at a specific location.
    """
    asset = None

    # <<< erp_inventory
    # @generated
    def __init__(self, asset=None, **kw_args):
        """ Initialises a new 'ErpInventory' instance.
        """
        self.asset = asset

        super(ErpInventory, self).__init__(**kw_args)
    # >>> erp_inventory


class ErpIssueInventory():
    """ The ErpIssueInventory can be used to request an application to process an issue or request information about an issue. 
    """
    type_asset = None

    type_material = None

    # <<< erp_issue_inventory
    # @generated
    def __init__(self, type_asset=None, type_material=None, **kw_args):
        """ Initialises a new 'ErpIssueInventory' instance.
        """
        self.type_asset = type_asset
        self.type_material = type_material

        super(ErpIssueInventory, self).__init__(**kw_args)
    # >>> erp_issue_inventory


class ErpPayable(Document):
    """ Payable is a transaction that represents an invoice from a supplier.  A payable (or voucher) is an open item, approved and ready for payment, in the Accounts Payable ledger. 
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of payable. 
    type_payable = ''

    erp_payable_line_items = []

    contractor_items = []

    # <<< erp_payable
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_payable='', erp_payable_line_items=[], contractor_items=[], **kw_args):
        """ Initialises a new 'ErpPayable' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_payable = type_payable
        self.erp_payable_line_items = erp_payable_line_items
        self.contractor_items = contractor_items

        super(ErpPayable, self).__init__(**kw_args)
    # >>> erp_payable


class ErpProjectAccounting(Document):
    """ Utility Project Accounting information.  This is used by ERP applications to enable all relevant sub-systems that submit single sided transactions to transfer information with a Project Accounting Application. This would include, but not necessarily be limited to: Accounts Payable, Accounts Receivable, Budget, Order Management, Purchasing, Time and Labor, Travel and Expense. 
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_time_entries = []

    work_cost_detail = []

    work = []

    projects = []

    # <<< erp_project_accounting
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', erp_time_entries=[], work_cost_detail=[], work=[], projects=[], **kw_args):
        """ Initialises a new 'ErpProjectAccounting' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_time_entries = erp_time_entries
        self.work_cost_detail = work_cost_detail
        self.work = work
        self.projects = projects

        super(ErpProjectAccounting, self).__init__(**kw_args)
    # >>> erp_project_accounting


class ErpBOM(Document):
    """ Information that generally describes the Bill of Material Structure and it's contents for a utility.  This is used by ERP systems to transfer of Bill of Material information between two business applications.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_bom_item_data = []

    design = None

    # <<< erp_bom
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', erp_bom_item_data=[], design=None, **kw_args):
        """ Initialises a new 'ErpBOM' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_bom_item_data = erp_bom_item_data
        self.design = design

        super(ErpBOM, self).__init__(**kw_args)
    # >>> erp_bom


class ErpRequisition(Document):
    """ General information that applies to a utility requisition that is a request for the purchase of goods or services.  Typically, a requisition leads to the creation of a purchase order to a specific supplier.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of requisition. 
    type_req = ''

    erp_req_line_items = []

    # <<< erp_requisition
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_req='', erp_req_line_items=[], **kw_args):
        """ Initialises a new 'ErpRequisition' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_req = type_req
        self.erp_req_line_items = erp_req_line_items

        super(ErpRequisition, self).__init__(**kw_args)
    # >>> erp_requisition


class ErpChartOfAccounts(Document):
    """ Chart of Accounts represents the accounting structure of a business.   Each account represents a financial aspect of a business, such as its Accounts Payable, or the value of its inventory, or its office supply expenses.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # <<< erp_chart_of_accounts
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', **kw_args):
        """ Initialises a new 'ErpChartOfAccounts' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks

        super(ErpChartOfAccounts, self).__init__(**kw_args)
    # >>> erp_chart_of_accounts


class ErpEngChangeOrder(Document):
    """ General Utility Engineering Change Order information.  
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of engineering change order. 
    type_eco = ''

    # <<< erp_eng_change_order
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_eco='', **kw_args):
        """ Initialises a new 'ErpEngChangeOrder' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_eco = type_eco

        super(ErpEngChangeOrder, self).__init__(**kw_args)
    # >>> erp_eng_change_order


class ErpInventoryCount():
    """ This is related to Inventory physical counts organized by AssetModel.  Note that a count of a type of asset can be accomplished by the association inherited by AssetModel (from Document) to Asset.  It enables ERP applications to transfer an inventory count between ERP and the actual physical inventory location. This count may be a cycle count or a physical count. 
    """
    asset_model = None

    material_item = None

    # <<< erp_inventory_count
    # @generated
    def __init__(self, asset_model=None, material_item=None, **kw_args):
        """ Initialises a new 'ErpInventoryCount' instance.
        """
        self.asset_model = asset_model
        self.material_item = material_item

        super(ErpInventoryCount, self).__init__(**kw_args)
    # >>> erp_inventory_count


class ErpLedgerBudget(Document):
    """ This is for information for utility Ledger Budgets.  They support the transfer budget amounts between all possible source applications throughout an enterprise and a general ledger or budget application.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of ledger budget. 
    type_lb = ''

    erp_led_bud_line_items = []

    # <<< erp_ledger_budget
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_lb='', erp_led_bud_line_items=[], **kw_args):
        """ Initialises a new 'ErpLedgerBudget' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_lb = type_lb
        self.erp_led_bud_line_items = erp_led_bud_line_items

        super(ErpLedgerBudget, self).__init__(**kw_args)
    # >>> erp_ledger_budget


class ErpPurchaseOrder(Document):
    """ A PurchaseOrder is a document that communicates an order to purchase goods from a buyer to a supplier. The PurchaseOrder carries information to and from the buyer and supplier. The PurchaseOrder is a legally binding document once both Parties agree to the contents and the specified terms and conditions of the order.  
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of purchase order. 
    type_po = ''

    erp_poline_items = []

    # <<< erp_purchase_order
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_po='', erp_poline_items=[], **kw_args):
        """ Initialises a new 'ErpPurchaseOrder' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_po = type_po
        self.erp_poline_items = erp_poline_items

        super(ErpPurchaseOrder, self).__init__(**kw_args)
    # >>> erp_purchase_order


class ErpReceiveDelivery(Document):
    """ This represents a transaction for an Organisation receiving goods or services.  It may be used to indicate receipt of goods in conjunction with a purchase order. A receivable is an open (unpaid) item in the Accounts Receivable ledger.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_rec_delv_line_items = []

    # <<< erp_receive_delivery
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', erp_rec_delv_line_items=[], **kw_args):
        """ Initialises a new 'ErpReceiveDelivery' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_rec_delv_line_items = erp_rec_delv_line_items

        super(ErpReceiveDelivery, self).__init__(**kw_args)
    # >>> erp_receive_delivery


class ErpBomItemData():
    """ An individual item on a bill of materials.
    """
    erp_bom = None

    type_asset = None

    design_location = None

    # <<< erp_bom_item_data
    # @generated
    def __init__(self, erp_bom=None, type_asset=None, design_location=None, **kw_args):
        """ Initialises a new 'ErpBomItemData' instance.
        """
        self.erp_bom = erp_bom
        self.type_asset = type_asset
        self.design_location = design_location

        super(ErpBomItemData, self).__init__(**kw_args)
    # >>> erp_bom_item_data


class ErpTelephoneNumber():
    """ The telephone number for a person or organisation.
    """
    # The country code for the telephone number.  
    country_code = ''

    # The area or region code for a telephone number. 
    area_code = ''

    # The city code, if applicable, for a telephone number. 
    city_code = ''

    # The main (local part of a) telephone number. 
    local_number = ''

    # The extension, if applicable, for a telephone number. 
    extension = ''

    # The purpose of the telephone: home, mobile, home fax, office, office fax, switchboard, other. 
    usage = ''

    organisation = None

    erp_persons = []

    # <<< erp_telephone_number
    # @generated
    def __init__(self, country_code='', area_code='', city_code='', local_number='', extension='', usage='', organisation=None, erp_persons=[], **kw_args):
        """ Initialises a new 'ErpTelephoneNumber' instance.
        """
        self.country_code = country_code
        self.area_code = area_code
        self.city_code = city_code
        self.local_number = local_number
        self.extension = extension
        self.usage = usage
        self.organisation = organisation
        self.erp_persons = erp_persons

        super(ErpTelephoneNumber, self).__init__(**kw_args)
    # >>> erp_telephone_number


class ErpItemMater():
    """ Item represents any unique purchased part for manufactured product tracked by ERP systems for a utility.    Item, as used by the OAG, refers to the basic information about an item, including its attributes, cost, and locations.  It does not include item quantities.  Compare to the  'Inventory,' which includes all quantities and other location-specific information.  
    """
    asset = None

    # <<< erp_item_mater
    # @generated
    def __init__(self, asset=None, **kw_args):
        """ Initialises a new 'ErpItemMater' instance.
        """
        self.asset = asset

        super(ErpItemMater, self).__init__(**kw_args)
    # >>> erp_item_mater


class ErpInvoiceLineItem(Document):
    """ An individual line item on an invoice.
    """
    # Line item number on invoice statement. 
    line_number = ''

    # The amount due for this line item. 
    line_amount = ''

    # Date line item will be posted to the General Ledger.  
    date_gl = ''

    # Type of line item such as initial, recalculation, other. 
    line_type = ''

    # The start of a bill period for the line item. 
    start = ''

    # The end of a bill period for the line item. 
    end = ''

    # The version number of the bill run. 
    line_version = ''

    # The previous line item charge amount.  
    previous_amount = ''

    # The net line item charge amount. 
    net_amount = ''

    # GL account code, must be a valid combination. 
    account_gl = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

 
    attribute_lists = ''

    work_billing_info = []

    customer_billing_info = []

    erp_invoice = None

    container_erp_invoice_line_item = None

    component_erp_invoice_line_item = []

    erp_journal_entry = []

    erp_payable_line_item = None

    erp_payment = []

    erp_rec_delv_line_item = None

    erp_quote_line_item = None

    erp_rec_line_item = None

    market_factors = []

    # <<< erp_invoice_line_item
    # @generated
    def __init__(self, line_number='', line_amount='', date_gl='', line_type='', start='', end='', line_version='', previous_amount='', net_amount='', account_gl='', status='', status_date_time='', status_remarks='', attribute_lists='', work_billing_info=[], customer_billing_info=[], erp_invoice=None, container_erp_invoice_line_item=None, component_erp_invoice_line_item=[], erp_journal_entry=[], erp_payable_line_item=None, erp_payment=[], erp_rec_delv_line_item=None, erp_quote_line_item=None, erp_rec_line_item=None, market_factors=[], **kw_args):
        """ Initialises a new 'ErpInvoiceLineItem' instance.
        """
        self.line_number = line_number
        self.line_amount = line_amount
        self.date_gl = date_gl
        self.line_type = line_type
        self.start = start
        self.end = end
        self.line_version = line_version
        self.previous_amount = previous_amount
        self.net_amount = net_amount
        self.account_gl = account_gl
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.attribute_lists = attribute_lists
        self.work_billing_info = work_billing_info
        self.customer_billing_info = customer_billing_info
        self.erp_invoice = erp_invoice
        self.container_erp_invoice_line_item = container_erp_invoice_line_item
        self.component_erp_invoice_line_item = component_erp_invoice_line_item
        self.erp_journal_entry = erp_journal_entry
        self.erp_payable_line_item = erp_payable_line_item
        self.erp_payment = erp_payment
        self.erp_rec_delv_line_item = erp_rec_delv_line_item
        self.erp_quote_line_item = erp_quote_line_item
        self.erp_rec_line_item = erp_rec_line_item
        self.market_factors = market_factors

        super(ErpInvoiceLineItem, self).__init__(**kw_args)
    # >>> erp_invoice_line_item


class ErpInvoice(Document):
    """ An invoice is a roll up of invoice line items.  The whole invoice has a due date and amount to be paid, with information such as customer, banks etc. being obtained through associations.  The invoice roll up is based on individual line items that each contain amounts and descriptions for specific services or products.
    """
    # The calculated date upon which the  Invoice amount is due. 
    due_date = ''

    # Total amount due on this invoice based on line items and applicable adjustments. 
    invoice_amount = ''

    # Used to indicate the date on which the customer billing statement/invoice was printed/mailed.   
    mailed_date = ''

    # Bill Media contains a coded value that represents the media by which the Customer Billing Statement was delivered (Paper, EDI, Other). 
    bill_media = ''

    # The type of invoice transfer. 
    transfer_type = ''

    # The date of which the invoice is issued. 
    transaction_date = ''

    # The number of an invoice to be reference by this invoice.  
    reference_number = ''

    # Type of invoice: Sales or Purchase. 
    sale_or_purchase = ''

    # True if payment is to be paid by a Customer to accept a particular ErpQuote (with associated Design) and have work initiated, at which time an associated ErpInvoice should automatically be generated.  Eprpayment.subjectStatus satisfies terms specificed in the ErpQuote. 
    pro_forma = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of invoice. 
    type_invoice = ''

    customer_account = None

    erp_invoice_line_items = []

    # <<< erp_invoice
    # @generated
    def __init__(self, due_date='', invoice_amount='', mailed_date='', bill_media='', transfer_type='', transaction_date='', reference_number='', sale_or_purchase='', pro_forma='', status='', status_date_time='', status_remarks='', type_invoice='', customer_account=None, erp_invoice_line_items=[], **kw_args):
        """ Initialises a new 'ErpInvoice' instance.
        """
        self.due_date = due_date
        self.invoice_amount = invoice_amount
        self.mailed_date = mailed_date
        self.bill_media = bill_media
        self.transfer_type = transfer_type
        self.transaction_date = transaction_date
        self.reference_number = reference_number
        self.sale_or_purchase = sale_or_purchase
        self.pro_forma = pro_forma
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_invoice = type_invoice
        self.customer_account = customer_account
        self.erp_invoice_line_items = erp_invoice_line_items

        super(ErpInvoice, self).__init__(**kw_args)
    # >>> erp_invoice


class ErpBankAccount(Document):
    """ Relationship under a particular name, usually evidenced by  a deposit against which withdrawals can be made.  Types of bank accounts include: demand, time, custodial, joint, trustee, corporate, special, and regular accounts. A statement of transactions during a fiscal period and the resulting balance is maintained on each account. 
    """
    # Bank ABA 
    bank_aba = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # <<< erp_bank_account
    # @generated
    def __init__(self, bank_aba='', status='', status_date_time='', status_remarks='', **kw_args):
        """ Initialises a new 'ErpBankAccount' instance.
        """
        self.bank_aba = bank_aba
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks

        super(ErpBankAccount, self).__init__(**kw_args)
    # >>> erp_bank_account


class ErpJournal(Document):
    """ A book for recording accounting transactions as they occur.  Transactions and adjustments are first recorded in a journal, which is like a diary of instructions, advising which account to be charged and by how much.  A journal represents a change in the balances of a business's financial accounts.  Many tasks or transactions throughout an enterprise will result in the creation of a journal.  Some examples are creating a customer invoice, paying a vendor, transferring inventory, or paying employees.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of journal.  
    type_journal = ''

    erp_journal_entries = []

    # <<< erp_journal
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_journal='', erp_journal_entries=[], **kw_args):
        """ Initialises a new 'ErpJournal' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_journal = type_journal
        self.erp_journal_entries = erp_journal_entries

        super(ErpJournal, self).__init__(**kw_args)
    # >>> erp_journal


class ErpJournalEntry():
    """ Details of an individual entry in a journal, which is to be posted to a ledger on the posting date.
    """
    # Account identifier for this entry. 
    account_id = ''

    # The amount of the debit or credit for this account. 
    amount = ''

    # The date and time this entry is to be posted to the ledger. 
    posting_date = ''

    # Date and time journal entry was recorded. 
    transaction_date = ''

    # Remarks and explanation concerning entry. 
    explanation = ''

    # The identifer of the source for this entry. 
    source_id = ''

    erp_journal = None

    erp_ledger_entry = None

    erp_invoice_line_item = None

    erp_payable_line_item = []

    cost_types = []

    # <<< erp_journal_entry
    # @generated
    def __init__(self, account_id='', amount='', posting_date='', transaction_date='', explanation='', source_id='', erp_journal=None, erp_ledger_entry=None, erp_invoice_line_item=None, erp_payable_line_item=[], cost_types=[], **kw_args):
        """ Initialises a new 'ErpJournalEntry' instance.
        """
        self.account_id = account_id
        self.amount = amount
        self.posting_date = posting_date
        self.transaction_date = transaction_date
        self.explanation = explanation
        self.source_id = source_id
        self.erp_journal = erp_journal
        self.erp_ledger_entry = erp_ledger_entry
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_payable_line_item = erp_payable_line_item
        self.cost_types = cost_types

        super(ErpJournalEntry, self).__init__(**kw_args)
    # >>> erp_journal_entry


class ErpLedger(Document):
    """ In accounting transactions, a ledger is a book containing accounts to which debits and credits are posted from journals, where transactions are initially recorded.  Journal entries are periodically posted to the ledger.  Ledger Actual  represents actual amounts by account within ledger within company or business area.  Actual amounts may be generated in a source application and then loaded to a specific ledger within the enterprise general ledger or budget application.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of ledger. 
    type_ledger = ''

    erp_ledger_entries = []

    # <<< erp_ledger
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_ledger='', erp_ledger_entries=[], **kw_args):
        """ Initialises a new 'ErpLedger' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_ledger = type_ledger
        self.erp_ledger_entries = erp_ledger_entries

        super(ErpLedger, self).__init__(**kw_args)
    # >>> erp_ledger


class ErpLedgerEntry():
    """ Details of an individual entry in a ledger, which was posted from a journal on the posted date.
    """
    # Account identifier for this entry. 
    account_id = ''

    # The type of account. For example: Normal, Reversal, Statistical, Estimate. 
    account_type = ''

    # The amount of the debit or credit for this account. 
    amount = ''

    # The date and time this entry was posted to the ledger. 
    posted_date = ''

    # Date and time journal entry was recorded. 
    transaction_date = ''

 
    attribute_lists = ''

    erp_ledger = None

    erp_jounal_entry = None

    erp_ledger_entry = None

    # <<< erp_ledger_entry
    # @generated
    def __init__(self, account_id='', account_type='', amount='', posted_date='', transaction_date='', attribute_lists='', erp_ledger=None, erp_jounal_entry=None, erp_ledger_entry=None, **kw_args):
        """ Initialises a new 'ErpLedgerEntry' instance.
        """
        self.account_id = account_id
        self.account_type = account_type
        self.amount = amount
        self.posted_date = posted_date
        self.transaction_date = transaction_date
        self.attribute_lists = attribute_lists
        self.erp_ledger = erp_ledger
        self.erp_jounal_entry = erp_jounal_entry
        self.erp_ledger_entry = erp_ledger_entry

        super(ErpLedgerEntry, self).__init__(**kw_args)
    # >>> erp_ledger_entry


class ErpTimeSheet(Document):
    """ Time sheets for employees and contractors.    Note that ErpTimeSheet inherits the relationship to ErpPerson  from Document.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_time_entries = []

    # <<< erp_time_sheet
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', erp_time_entries=[], **kw_args):
        """ Initialises a new 'ErpTimeSheet' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_time_entries = erp_time_entries

        super(ErpTimeSheet, self).__init__(**kw_args)
    # >>> erp_time_sheet


class ErpTimeEntry():
    """ An individual entry on a ErpTimeSheet. 
    """
    erp_time_sheet = None

    erp_project_accounting = None

    # <<< erp_time_entry
    # @generated
    def __init__(self, erp_time_sheet=None, erp_project_accounting=None, **kw_args):
        """ Initialises a new 'ErpTimeEntry' instance.
        """
        self.erp_time_sheet = erp_time_sheet
        self.erp_project_accounting = erp_project_accounting

        super(ErpTimeEntry, self).__init__(**kw_args)
    # >>> erp_time_entry


class ErpPOLineItem(Document):
    """ Of an ErpPurchaseOrder, this is an individually ordered item or product along with the quantity, price and other descriptive information.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Type of line item. 
    line_type = ''

    erp_rec_del_line_item = None

    erp_purchase_order = None

    erp_req_line_item = None

    asset_catalogue_item = None

    material_item = None

    # <<< erp_poline_item
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', line_type='', erp_rec_del_line_item=None, erp_purchase_order=None, erp_req_line_item=None, asset_catalogue_item=None, material_item=None, **kw_args):
        """ Initialises a new 'ErpPOLineItem' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.line_type = line_type
        self.erp_rec_del_line_item = erp_rec_del_line_item
        self.erp_purchase_order = erp_purchase_order
        self.erp_req_line_item = erp_req_line_item
        self.asset_catalogue_item = asset_catalogue_item
        self.material_item = material_item

        super(ErpPOLineItem, self).__init__(**kw_args)
    # >>> erp_poline_item


class ErpRecDelvLineItem():
    """ Of an ErpReceiveDelivery, this is an individually received good or service by the Organisation receiving goods or services.  It may be used to indicate receipt of goods in conjunction with a purchase order line item.
    """
    erp_poline_item = None

    erp_receive_delivery = None

    erp_invoice_line_item = None

    assets = []

    material_items = []

    # <<< erp_rec_delv_line_item
    # @generated
    def __init__(self, erp_poline_item=None, erp_receive_delivery=None, erp_invoice_line_item=None, assets=[], material_items=[], **kw_args):
        """ Initialises a new 'ErpRecDelvLineItem' instance.
        """
        self.erp_poline_item = erp_poline_item
        self.erp_receive_delivery = erp_receive_delivery
        self.erp_invoice_line_item = erp_invoice_line_item
        self.assets = assets
        self.material_items = material_items

        super(ErpRecDelvLineItem, self).__init__(**kw_args)
    # >>> erp_rec_delv_line_item


class ErpReqLineItem():
    """ Information that describes a requested item and its attributes.
    """
 
    code = ''

    #  Quantity of item requisitioned. 
    quantity = ''

 
    delivery_date = ''

    #  Cost of material 
    cost = ''

    erp_poline_item = None

    erp_requisition = None

    type_asset = None

    erp_quote_line_item = None

    type_material = None

    # <<< erp_req_line_item
    # @generated
    def __init__(self, code='', quantity='', delivery_date='', cost='', erp_poline_item=None, erp_requisition=None, type_asset=None, erp_quote_line_item=None, type_material=None, **kw_args):
        """ Initialises a new 'ErpReqLineItem' instance.
        """
        self.code = code
        self.quantity = quantity
        self.delivery_date = delivery_date
        self.cost = cost
        self.erp_poline_item = erp_poline_item
        self.erp_requisition = erp_requisition
        self.type_asset = type_asset
        self.erp_quote_line_item = erp_quote_line_item
        self.type_material = type_material

        super(ErpReqLineItem, self).__init__(**kw_args)
    # >>> erp_req_line_item


class ErpQuote(Document):
    """ An ErpQuote is a document describing the prices of goods or services provided by a supplier.  The Quote includes the terms of the purchase, delivery proposals, identification of goods or services ordered, as well as their quantities.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of quote. 
    type_quote = ''

    erp_quote_line_items = []

    # <<< erp_quote
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', type_quote='', erp_quote_line_items=[], **kw_args):
        """ Initialises a new 'ErpQuote' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_quote = type_quote
        self.erp_quote_line_items = erp_quote_line_items

        super(ErpQuote, self).__init__(**kw_args)
    # >>> erp_quote


class ErpQuoteLineItem():
    """ Of an ErpQuote, the item or product quoted along with quantity, price and other descriptive information.
    """
    erp_quote = None

    erp_invoice_line_item = None

    asset_catalogue_item = None

    erp_req_line_item = None

    design = None

    request = None

    # <<< erp_quote_line_item
    # @generated
    def __init__(self, erp_quote=None, erp_invoice_line_item=None, asset_catalogue_item=None, erp_req_line_item=None, design=None, request=None, **kw_args):
        """ Initialises a new 'ErpQuoteLineItem' instance.
        """
        self.erp_quote = erp_quote
        self.erp_invoice_line_item = erp_invoice_line_item
        self.asset_catalogue_item = asset_catalogue_item
        self.erp_req_line_item = erp_req_line_item
        self.design = design
        self.request = request

        super(ErpQuoteLineItem, self).__init__(**kw_args)
    # >>> erp_quote_line_item


class ErpPayableLineItem():
    """ Of an ErpPayable, a line item references an ErpInvoiceLineitem or other source such as credit memos.
    """
    erp_payable = None

    erp_invoice_line_item = None

    erp_payments = []

    erp_journal_entry = []

    # <<< erp_payable_line_item
    # @generated
    def __init__(self, erp_payable=None, erp_invoice_line_item=None, erp_payments=[], erp_journal_entry=[], **kw_args):
        """ Initialises a new 'ErpPayableLineItem' instance.
        """
        self.erp_payable = erp_payable
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_payments = erp_payments
        self.erp_journal_entry = erp_journal_entry

        super(ErpPayableLineItem, self).__init__(**kw_args)
    # >>> erp_payable_line_item


class ErpPayment(Document):
    """ Payment infromation and status for any individual line item of an ErpInvoice (e.g., when payment is from a customer).  ErpPayable is also updated when payment is to a supplier and ErpReceivable is updated when payment is from a customer.  Multiple payments can be made against a single line item and an individual payment can apply to more that one line item.
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Payment terms (e.g., net 30).  
    payment_terms = ''

    erp_payable_line_items = []

    erp_invoice_line_items = []

    erp_rec_line_items = []

    # <<< erp_payment
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', payment_terms='', erp_payable_line_items=[], erp_invoice_line_items=[], erp_rec_line_items=[], **kw_args):
        """ Initialises a new 'ErpPayment' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.payment_terms = payment_terms
        self.erp_payable_line_items = erp_payable_line_items
        self.erp_invoice_line_items = erp_invoice_line_items
        self.erp_rec_line_items = erp_rec_line_items

        super(ErpPayment, self).__init__(**kw_args)
    # >>> erp_payment


class ErpReceivable(Document):
    """ A receivable is a transaction representing an invoice, credit memo or debit memo to a customer.  A receivable is an open (unpaid) item in the Accounts Receivable ledger.
    """
    # The type of receivable. 
    type_receivable = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_rec_line_items = []

    # <<< erp_receivable
    # @generated
    def __init__(self, type_receivable='', status='', status_date_time='', status_remarks='', erp_rec_line_items=[], **kw_args):
        """ Initialises a new 'ErpReceivable' instance.
        """
        self.type_receivable = type_receivable
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_rec_line_items = erp_rec_line_items

        super(ErpReceivable, self).__init__(**kw_args)
    # >>> erp_receivable


class ErpRecLineItem():
    erp_receivable = None

    erp_invoice_line_item = None

    erp_payments = []

    # <<< erp_rec_line_item
    # @generated
    def __init__(self, erp_receivable=None, erp_invoice_line_item=None, erp_payments=[], **kw_args):
        """ Initialises a new 'ErpRecLineItem' instance.
        """
        self.erp_receivable = erp_receivable
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_payments = erp_payments

        super(ErpRecLineItem, self).__init__(**kw_args)
    # >>> erp_rec_line_item


class ErpLedBudLineItem():
    erp_ledger_budget = None

    erp_led_bud_line_item = None

    # <<< erp_led_bud_line_item
    # @generated
    def __init__(self, erp_ledger_budget=None, erp_led_bud_line_item=None, **kw_args):
        """ Initialises a new 'ErpLedBudLineItem' instance.
        """
        self.erp_ledger_budget = erp_ledger_budget
        self.erp_led_bud_line_item = erp_led_bud_line_item

        super(ErpLedBudLineItem, self).__init__(**kw_args)
    # >>> erp_led_bud_line_item


class Erp_SupportVersion(object):
 
    version = ''

 
    date = ''

    # <<< erp_support_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'Erp_SupportVersion' instance.
        """
        self.version = version
        self.date = date

        super(Erp_SupportVersion, self).__init__(**kw_args)
    # >>> erp_support_version


# <<< erp_support
# @generated
# >>> erp_support

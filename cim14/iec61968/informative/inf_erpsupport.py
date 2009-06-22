# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Document
from cim14.iec61968.informative.inf_common import Role
from cim14.iec61968.informative.inf_common import BankAccount
from cim14.iec61968.common import Organisation
from cim14.iec61968.common import TelephoneNumber

# <<< imports
# @generated
# >>> imports

class ErpLedgerEntry(IdentifiedObject):
    """ Details of an individual entry in a ledger, which was posted from a journal on the posted date.
    """
    # Date and time journal entry was recorded. 
    transaction_date_time = ''

    # Kind of account for this entry. Values are: "normal", "statistical", "estimate", "reversal"
    account_kind = 'normal'

    # The amount of the debit or credit for this account. 
    amount = ''

    # Account identifier for this entry. 
    account_id = ''

    # Date and time this entry was posted to the ledger. 
    posted_date_time = ''

    status = None

    settlements = []

    erp_jounal_entry = None

    erp_ledger = None

    erp_ledger_entry = None

    user_attributes = []

    # <<< erp_ledger_entry
    # @generated
    def __init__(self, transaction_date_time='', account_kind='normal', amount='', account_id='', posted_date_time='', status=None, settlements=[], erp_jounal_entry=None, erp_ledger=None, erp_ledger_entry=None, user_attributes=[], **kw_args):
        """ Initialises a new 'ErpLedgerEntry' instance.
        """
        self.transaction_date_time = transaction_date_time
        self.account_kind = account_kind
        self.amount = amount
        self.account_id = account_id
        self.posted_date_time = posted_date_time
        self.status = status
        self.settlements = settlements
        self.erp_jounal_entry = erp_jounal_entry
        self.erp_ledger = erp_ledger
        self.erp_ledger_entry = erp_ledger_entry
        self.user_attributes = user_attributes

        super(ErpLedgerEntry, self).__init__(**kw_args)
    # >>> erp_ledger_entry


class ErpLedger(Document):
    """ In accounting transactions, a ledger is a book containing accounts to which debits and credits are posted from journals, where transactions are initially recorded. Journal entries are periodically posted to the ledger. Ledger Actual represents actual amounts by account within ledger within company or business area. Actual amounts may be generated in a source application and then loaded to a specific ledger within the enterprise general ledger or budget application.
    """
    erp_ledger_entries = []

    # <<< erp_ledger
    # @generated
    def __init__(self, erp_ledger_entries=[], **kw_args):
        """ Initialises a new 'ErpLedger' instance.
        """
        self.erp_ledger_entries = erp_ledger_entries

        super(ErpLedger, self).__init__(**kw_args)
    # >>> erp_ledger


class ErpRecLineItem(IdentifiedObject):
    """ Individual entry of an ErpReceivable, it is a particular transaction representing an invoice, credit memo or debit memo to a customer.
    """
    status = None

    erp_receivable = None

    erp_payments = []

    erp_invoice_line_item = None

    erp_journal_entries = []

    # <<< erp_rec_line_item
    # @generated
    def __init__(self, status=None, erp_receivable=None, erp_payments=[], erp_invoice_line_item=None, erp_journal_entries=[], **kw_args):
        """ Initialises a new 'ErpRecLineItem' instance.
        """
        self.status = status
        self.erp_receivable = erp_receivable
        self.erp_payments = erp_payments
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_journal_entries = erp_journal_entries

        super(ErpRecLineItem, self).__init__(**kw_args)
    # >>> erp_rec_line_item


class DocOrgRole(Role):
    """ Roles played between Organisations and Documents.
    """
    document = None

    erp_organisation = None

    # <<< doc_org_role
    # @generated
    def __init__(self, document=None, erp_organisation=None, **kw_args):
        """ Initialises a new 'DocOrgRole' instance.
        """
        self.document = document
        self.erp_organisation = erp_organisation

        super(DocOrgRole, self).__init__(**kw_args)
    # >>> doc_org_role


class ErpIssueInventory(IdentifiedObject):
    """ Can be used to request an application to process an issue or request information about an issue.
    """
    status = None

    type_material = None

    type_asset = None

    # <<< erp_issue_inventory
    # @generated
    def __init__(self, status=None, type_material=None, type_asset=None, **kw_args):
        """ Initialises a new 'ErpIssueInventory' instance.
        """
        self.status = status
        self.type_material = type_material
        self.type_asset = type_asset

        super(ErpIssueInventory, self).__init__(**kw_args)
    # >>> erp_issue_inventory


class ErpInventory(IdentifiedObject):
    """ Utility inventory-related information about an item or part (and not for description of the item and its attributes). It is used by ERP applications to enable the synchronization of Inventory data that exists on separate Item Master databases. This data is not the master data that describes the attributes of the item such as dimensions, weight, or unit of measure - it describes the item as it exists at a specific location.
    """
    status = None

    asset = None

    # <<< erp_inventory
    # @generated
    def __init__(self, status=None, asset=None, **kw_args):
        """ Initialises a new 'ErpInventory' instance.
        """
        self.status = status
        self.asset = asset

        super(ErpInventory, self).__init__(**kw_args)
    # >>> erp_inventory


class ErpPurchaseOrder(Document):
    """ A document that communicates an order to purchase goods from a buyer to a supplier. The PurchaseOrder carries information to and from the buyer and supplier. It is a legally binding document once both Parties agree to the contents and the specified terms and conditions of the order.
    """
    erp_poline_items = []

    # <<< erp_purchase_order
    # @generated
    def __init__(self, erp_poline_items=[], **kw_args):
        """ Initialises a new 'ErpPurchaseOrder' instance.
        """
        self.erp_poline_items = erp_poline_items

        super(ErpPurchaseOrder, self).__init__(**kw_args)
    # >>> erp_purchase_order


class ErpPayableLineItem(IdentifiedObject):
    """ Of an ErpPayable, a line item references an ErpInvoiceLineitem or other source such as credit memos.
    """
    status = None

    erp_journal_entries = []

    erp_payable = None

    erp_invoice_line_item = None

    erp_payments = []

    # <<< erp_payable_line_item
    # @generated
    def __init__(self, status=None, erp_journal_entries=[], erp_payable=None, erp_invoice_line_item=None, erp_payments=[], **kw_args):
        """ Initialises a new 'ErpPayableLineItem' instance.
        """
        self.status = status
        self.erp_journal_entries = erp_journal_entries
        self.erp_payable = erp_payable
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_payments = erp_payments

        super(ErpPayableLineItem, self).__init__(**kw_args)
    # >>> erp_payable_line_item


class ErpQuote(Document):
    """ Document describing the prices of goods or services provided by a supplier. It includes the terms of the purchase, delivery proposals, identification of goods or services ordered, as well as their quantities.
    """
    erp_quote_line_items = []

    # <<< erp_quote
    # @generated
    def __init__(self, erp_quote_line_items=[], **kw_args):
        """ Initialises a new 'ErpQuote' instance.
        """
        self.erp_quote_line_items = erp_quote_line_items

        super(ErpQuote, self).__init__(**kw_args)
    # >>> erp_quote


class ErpBOM(Document):
    """ Information that generally describes the Bill of Material Structure and its contents for a utility.  This is used by ERP systems to transfer Bill of Material information between two business applications.
    """
    erp_bom_item_datas = []

    design = None

    # <<< erp_bom
    # @generated
    def __init__(self, erp_bom_item_datas=[], design=None, **kw_args):
        """ Initialises a new 'ErpBOM' instance.
        """
        self.erp_bom_item_datas = erp_bom_item_datas
        self.design = design

        super(ErpBOM, self).__init__(**kw_args)
    # >>> erp_bom


class ErpInvoice(Document):
    """ A roll up of invoice line items. The whole invoice has a due date and amount to be paid, with information such as customer, banks etc. being obtained through associations. The invoice roll up is based on individual line items that each contain amounts and descriptions for specific services or products.
    """
    # The date of which the invoice is issued. 
    transaction_date = ''

    # The type of invoice transfer. 
    transfer_type = ''

    # Calculated date upon which the Invoice amount is due. 
    due_date = ''

    # Kind of invoice (default is 'sales'). Values are: "sales", "purchase"
    kind = 'sales'

    # The number of an invoice to be reference by this invoice. 
    reference_number = ''

    # Total amount due on this invoice based on line items and applicable adjustments. 
    amount = ''

    # Kind of media by which the CustomerBillingInfo was delivered. Values are: "electronic", "other", "paper"
    bill_media_kind = 'electronic'

    # True if payment is to be paid by a Customer to accept a particular ErpQuote (with associated Design) and have work initiated, at which time an associated ErpInvoice should automatically be generated. EprPayment.subjectStatus satisfies terms specificed in the ErpQuote. 
    pro_forma = False

    # Date on which the customer billing statement/invoice was printed/mailed. 
    mailed_date = ''

    customer_account = None

    erp_invoice_line_items = []

    # <<< erp_invoice
    # @generated
    def __init__(self, transaction_date='', transfer_type='', due_date='', kind='sales', reference_number='', amount='', bill_media_kind='electronic', pro_forma=False, mailed_date='', customer_account=None, erp_invoice_line_items=[], **kw_args):
        """ Initialises a new 'ErpInvoice' instance.
        """
        self.transaction_date = transaction_date
        self.transfer_type = transfer_type
        self.due_date = due_date
        self.kind = kind
        self.reference_number = reference_number
        self.amount = amount
        self.bill_media_kind = bill_media_kind
        self.pro_forma = pro_forma
        self.mailed_date = mailed_date
        self.customer_account = customer_account
        self.erp_invoice_line_items = erp_invoice_line_items

        super(ErpInvoice, self).__init__(**kw_args)
    # >>> erp_invoice


class ErpLedBudLineItem(IdentifiedObject):
    """ Individual entry of a given Ledger Budget, typically containing information such as amount, accounting date, accounting period, and is associated with the applicable general ledger account.
    """
    status = None

    erp_led_bud_line_item = None

    erp_ledger_budget = None

    # <<< erp_led_bud_line_item
    # @generated
    def __init__(self, status=None, erp_led_bud_line_item=None, erp_ledger_budget=None, **kw_args):
        """ Initialises a new 'ErpLedBudLineItem' instance.
        """
        self.status = status
        self.erp_led_bud_line_item = erp_led_bud_line_item
        self.erp_ledger_budget = erp_ledger_budget

        super(ErpLedBudLineItem, self).__init__(**kw_args)
    # >>> erp_led_bud_line_item


class ErpPayment(Document):
    """ Payment infromation and status for any individual line item of an ErpInvoice (e.g., when payment is from a customer). ErpPayable is also updated when payment is to a supplier and ErpReceivable is updated when payment is from a customer. Multiple payments can be made against a single line item and an individual payment can apply to more that one line item.
    """
    # Payment terms (e.g., net 30). 
    terms_payment = ''

    erp_rec_line_items = []

    erp_invoice_line_items = []

    erp_payable_line_items = []

    # <<< erp_payment
    # @generated
    def __init__(self, terms_payment='', erp_rec_line_items=[], erp_invoice_line_items=[], erp_payable_line_items=[], **kw_args):
        """ Initialises a new 'ErpPayment' instance.
        """
        self.terms_payment = terms_payment
        self.erp_rec_line_items = erp_rec_line_items
        self.erp_invoice_line_items = erp_invoice_line_items
        self.erp_payable_line_items = erp_payable_line_items

        super(ErpPayment, self).__init__(**kw_args)
    # >>> erp_payment


class ErpEngChangeOrder(Document):
    """ General Utility Engineering Change Order information.
    """
    pass
    # <<< erp_eng_change_order
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ErpEngChangeOrder' instance.
        """

        super(ErpEngChangeOrder, self).__init__(**kw_args)
    # >>> erp_eng_change_order


class ErpInventoryCount(IdentifiedObject):
    """ This is related to Inventory physical counts organized by AssetModel. Note that a count of a type of asset can be accomplished by the association inherited by AssetModel (from Document) to Asset. It enables ERP applications to transfer an inventory count between ERP and the actual physical inventory location. This count may be a cycle count or a physical count.
    """
    status = None

    material_item = None

    asset_model = None

    # <<< erp_inventory_count
    # @generated
    def __init__(self, status=None, material_item=None, asset_model=None, **kw_args):
        """ Initialises a new 'ErpInventoryCount' instance.
        """
        self.status = status
        self.material_item = material_item
        self.asset_model = asset_model

        super(ErpInventoryCount, self).__init__(**kw_args)
    # >>> erp_inventory_count


class ErpTimeSheet(Document):
    """ Time sheet for employees and contractors. Note that ErpTimeSheet inherits the relationship to ErpPerson from Document.
    """
    erp_time_entries = []

    # <<< erp_time_sheet
    # @generated
    def __init__(self, erp_time_entries=[], **kw_args):
        """ Initialises a new 'ErpTimeSheet' instance.
        """
        self.erp_time_entries = erp_time_entries

        super(ErpTimeSheet, self).__init__(**kw_args)
    # >>> erp_time_sheet


class OrgOrgRole(Role):
    """ Roles played between Organisations and other Organisations. This includes role ups for ogranisations, cost centers, profit centers, regulatory reporting, etc. Note that the parent and child relationship is indicated by the name on each end of the association.
    """
    # Identifiers of the organisation held by another organisation, such as a government agency (federal, state, province, city, county), financial institution (Dun and Bradstreet), etc. 
    client_id = ''

    parent_organisation = None

    child_organisation = None

    # <<< org_org_role
    # @generated
    def __init__(self, client_id='', parent_organisation=None, child_organisation=None, **kw_args):
        """ Initialises a new 'OrgOrgRole' instance.
        """
        self.client_id = client_id
        self.parent_organisation = parent_organisation
        self.child_organisation = child_organisation

        super(OrgOrgRole, self).__init__(**kw_args)
    # >>> org_org_role


class ErpItemMaster(IdentifiedObject):
    """ Any unique purchased part for manufactured product tracked by ERP systems for a utility. Item, as used by the OAG, refers to the basic information about an item, including its attributes, cost, and locations. It does not include item quantities. Compare to the Inventory, which includes all quantities and other location-specific information.
    """
    status = None

    asset = None

    # <<< erp_item_master
    # @generated
    def __init__(self, status=None, asset=None, **kw_args):
        """ Initialises a new 'ErpItemMaster' instance.
        """
        self.status = status
        self.asset = asset

        super(ErpItemMaster, self).__init__(**kw_args)
    # >>> erp_item_master


class ErpProjectAccounting(Document):
    """ Utility Project Accounting information, used by ERP applications to enable all relevant sub-systems that submit single sided transactions to transfer information with a Project Accounting Application. This would include, but not necessarily be limited to: Accounts Payable, Accounts Receivable, Budget, Order Management, Purchasing, Time and Labor, Travel and Expense.
    """
    works = []

    work_cost_details = []

    projects = []

    erp_time_entries = []

    # <<< erp_project_accounting
    # @generated
    def __init__(self, works=[], work_cost_details=[], projects=[], erp_time_entries=[], **kw_args):
        """ Initialises a new 'ErpProjectAccounting' instance.
        """
        self.works = works
        self.work_cost_details = work_cost_details
        self.projects = projects
        self.erp_time_entries = erp_time_entries

        super(ErpProjectAccounting, self).__init__(**kw_args)
    # >>> erp_project_accounting


class ErpReceivable(Document):
    """ Transaction representing an invoice, credit memo or debit memo to a customer. It is an open (unpaid) item in the Accounts Receivable ledger.
    """
    erp_rec_line_items = []

    # <<< erp_receivable
    # @generated
    def __init__(self, erp_rec_line_items=[], **kw_args):
        """ Initialises a new 'ErpReceivable' instance.
        """
        self.erp_rec_line_items = erp_rec_line_items

        super(ErpReceivable, self).__init__(**kw_args)
    # >>> erp_receivable


class ErpBankAccount(BankAccount):
    """ Relationship under a particular name, usually evidenced by a deposit against which withdrawals can be made. Types of bank accounts include: demand, time, custodial, joint, trustee, corporate, special, and regular accounts. A statement of transactions during a fiscal period and the resulting balance is maintained on each account. For Payment metering, the account is associated with Bank and Supplier, reflecting details of the bank account used for depositing revenue collected by TokenVendor. The name of the account holder should be specified in 'name' attribute.
    """
    # Bank ABA. 
    bank_aba = ''

    # <<< erp_bank_account
    # @generated
    def __init__(self, bank_aba='', **kw_args):
        """ Initialises a new 'ErpBankAccount' instance.
        """
        self.bank_aba = bank_aba

        super(ErpBankAccount, self).__init__(**kw_args)
    # >>> erp_bank_account


class OrgErpPersonRole(Role):
    """ Roles played between Persons and Organisations.
    """
    # Identifiers of the person held by an organisation, such as a government agency (federal, state, province, city, county), financial institutions, etc. 
    client_id = ''

    erp_organisation = None

    erp_person = None

    # <<< org_erp_person_role
    # @generated
    def __init__(self, client_id='', erp_organisation=None, erp_person=None, **kw_args):
        """ Initialises a new 'OrgErpPersonRole' instance.
        """
        self.client_id = client_id
        self.erp_organisation = erp_organisation
        self.erp_person = erp_person

        super(OrgErpPersonRole, self).__init__(**kw_args)
    # >>> org_erp_person_role


class ErpTimeEntry(IdentifiedObject):
    """ An individual entry on an ErpTimeSheet.
    """
    status = None

    erp_time_sheet = None

    erp_project_accounting = None

    # <<< erp_time_entry
    # @generated
    def __init__(self, status=None, erp_time_sheet=None, erp_project_accounting=None, **kw_args):
        """ Initialises a new 'ErpTimeEntry' instance.
        """
        self.status = status
        self.erp_time_sheet = erp_time_sheet
        self.erp_project_accounting = erp_project_accounting

        super(ErpTimeEntry, self).__init__(**kw_args)
    # >>> erp_time_entry


class ErpPayable(Document):
    """ A transaction that represents an invoice from a supplier. A payable (or voucher) is an open item, approved and ready for payment, in the Accounts Payable ledger.
    """
    erp_payable_line_items = []

    contractor_items = []

    # <<< erp_payable
    # @generated
    def __init__(self, erp_payable_line_items=[], contractor_items=[], **kw_args):
        """ Initialises a new 'ErpPayable' instance.
        """
        self.erp_payable_line_items = erp_payable_line_items
        self.contractor_items = contractor_items

        super(ErpPayable, self).__init__(**kw_args)
    # >>> erp_payable


class ErpPerson(IdentifiedObject):
    """ General purpose information for name and other information to contact people.
    """
    # Person's first name. 
    first_name = ''

    # A suffix for the person's name, such as II, III, etc. 
    suffix = ''

    # A prefix or title for the person's name, such as Miss, Mister, Doctor, etc. 
    prefix = ''

    # Unique identifier for person relative to its governing authority, for example a federal tax identifier (such as a Social Security number in the United States). 
    government_id = ''

    # Person's last (family, sir) name. 
    last_name = ''

    # Middle name(s) or initial(s). 
    m_name = ''

    # Special service needs for the person (contact) are described; examples include life support, etc. 
    special_need = ''

    # Category of this person relative to utility operations, classified according to the utility's corporate standards and practices. Examples include employee, contractor, agent, not affiliated, etc. Note that this field is not used to indicate whether this person is a customer of the utility. Often an employee or contractor is also a customer. Customer information is gained with relationship to Organisation and CustomerData. In similar fashion, this field does not indicate the various roles this person may fill as part of utility operations. 
    category = ''

    status = None

    crafts = []

    labor_items = []

    erp_personnel = None

    location_roles = []

    electronic_addresses = []

    switching_step_roles = []

    activity_records = []

    land_property_roles = []

    erp_organisation_roles = []

    call_backs = []

    change_items = []

    skills = []

    erp_competency = None

    erp_telephone_numbers = []

    customer_data = None

    document_roles = []

    # All Crews to which this ErpPerson belongs.
    crews = []

    measurement_values = []

    appointments = []

    # <<< erp_person
    # @generated
    def __init__(self, first_name='', suffix='', prefix='', government_id='', last_name='', m_name='', special_need='', category='', status=None, crafts=[], labor_items=[], erp_personnel=None, location_roles=[], electronic_addresses=[], switching_step_roles=[], activity_records=[], land_property_roles=[], erp_organisation_roles=[], call_backs=[], change_items=[], skills=[], erp_competency=None, erp_telephone_numbers=[], customer_data=None, document_roles=[], crews=[], measurement_values=[], appointments=[], **kw_args):
        """ Initialises a new 'ErpPerson' instance.
        """
        self.first_name = first_name
        self.suffix = suffix
        self.prefix = prefix
        self.government_id = government_id
        self.last_name = last_name
        self.m_name = m_name
        self.special_need = special_need
        self.category = category
        self.status = status
        self.crafts = crafts
        self.labor_items = labor_items
        self.erp_personnel = erp_personnel
        self.location_roles = location_roles
        self.electronic_addresses = electronic_addresses
        self.switching_step_roles = switching_step_roles
        self.activity_records = activity_records
        self.land_property_roles = land_property_roles
        self.erp_organisation_roles = erp_organisation_roles
        self.call_backs = call_backs
        self.change_items = change_items
        self.skills = skills
        self.erp_competency = erp_competency
        self.erp_telephone_numbers = erp_telephone_numbers
        self.customer_data = customer_data
        self.document_roles = document_roles
        self.crews = crews
        self.measurement_values = measurement_values
        self.appointments = appointments

        super(ErpPerson, self).__init__(**kw_args)
    # >>> erp_person


class ErpQuoteLineItem(IdentifiedObject):
    """ Of an ErpQuote, the item or product quoted along with quantity, price and other descriptive information.
    """
    status = None

    asset_model_catalogue_item = None

    design = None

    erp_quote = None

    # Some utilities provide quotes to customer for services, where the customer accepts the quote by making a payment. An invoice is required for this to occur.
    erp_invoice_line_item = None

    erp_req_line_item = None

    request = None

    # <<< erp_quote_line_item
    # @generated
    def __init__(self, status=None, asset_model_catalogue_item=None, design=None, erp_quote=None, erp_invoice_line_item=None, erp_req_line_item=None, request=None, **kw_args):
        """ Initialises a new 'ErpQuoteLineItem' instance.
        """
        self.status = status
        self.asset_model_catalogue_item = asset_model_catalogue_item
        self.design = design
        self.erp_quote = erp_quote
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_req_line_item = erp_req_line_item
        self.request = request

        super(ErpQuoteLineItem, self).__init__(**kw_args)
    # >>> erp_quote_line_item


class ErpJournalEntry(IdentifiedObject):
    """ Details of an individual entry in a journal, which is to be posted to a ledger on the posting date.
    """
    # Date and time this entry is to be posted to the ledger. 
    posting_date_time = ''

    # The amount of the debit or credit for this account. 
    amount = ''

    # The identifer of the source for this entry. 
    source_id = ''

    # Date and time journal entry was recorded. 
    transaction_date_time = ''

    # Account identifier for this entry. 
    account_id = ''

    status = None

    erp_rec_line_items = []

    erp_journal = None

    erp_payable_line_items = []

    erp_ledger_entry = None

    erp_invoice_line_item = None

    cost_types = []

    # <<< erp_journal_entry
    # @generated
    def __init__(self, posting_date_time='', amount='', source_id='', transaction_date_time='', account_id='', status=None, erp_rec_line_items=[], erp_journal=None, erp_payable_line_items=[], erp_ledger_entry=None, erp_invoice_line_item=None, cost_types=[], **kw_args):
        """ Initialises a new 'ErpJournalEntry' instance.
        """
        self.posting_date_time = posting_date_time
        self.amount = amount
        self.source_id = source_id
        self.transaction_date_time = transaction_date_time
        self.account_id = account_id
        self.status = status
        self.erp_rec_line_items = erp_rec_line_items
        self.erp_journal = erp_journal
        self.erp_payable_line_items = erp_payable_line_items
        self.erp_ledger_entry = erp_ledger_entry
        self.erp_invoice_line_item = erp_invoice_line_item
        self.cost_types = cost_types

        super(ErpJournalEntry, self).__init__(**kw_args)
    # >>> erp_journal_entry


class ErpBomItemData(IdentifiedObject):
    """ An individual item on a bill of materials.
    """
    type_asset = None

    erp_bom = None

    design_location = None

    # <<< erp_bom_item_data
    # @generated
    def __init__(self, type_asset=None, erp_bom=None, design_location=None, **kw_args):
        """ Initialises a new 'ErpBomItemData' instance.
        """
        self.type_asset = type_asset
        self.erp_bom = erp_bom
        self.design_location = design_location

        super(ErpBomItemData, self).__init__(**kw_args)
    # >>> erp_bom_item_data


class ErpCompetency(IdentifiedObject):
    """ Information that describes aptitudes of a utility employee. Unlike Skills that an ErpPerson must be certified to perform before undertaking certain type of assignments (to be able to perfrom a Craft), ErpCompetency has more to do with typical Human Resource (HR) matters such as schooling, training, etc.
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


class ErpSalesOrder(Document):
    """ General purpose Sales Order is used for utility service orders, etc. As used by the OAG, the SalesOrder is a step beyond a PurchaseOrder in that the receiving entity of the order also communicates SalesInformoration about the Order along with the Order itself.
    """
    pass
    # <<< erp_sales_order
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ErpSalesOrder' instance.
        """

        super(ErpSalesOrder, self).__init__(**kw_args)
    # >>> erp_sales_order


class ErpLedgerBudget(Document):
    """ Information for utility Ledger Budgets. They support the transfer budget amounts between all possible source applications throughout an enterprise and a general ledger or budget application.
    """
    erp_led_bud_line_items = []

    # <<< erp_ledger_budget
    # @generated
    def __init__(self, erp_led_bud_line_items=[], **kw_args):
        """ Initialises a new 'ErpLedgerBudget' instance.
        """
        self.erp_led_bud_line_items = erp_led_bud_line_items

        super(ErpLedgerBudget, self).__init__(**kw_args)
    # >>> erp_ledger_budget


class ErpOrganisation(Organisation):
    """ Identifies organisations that might have roles as utilities, contractors, suppliers, manufacturers, customers, etc. Organisations may also have parent-child relationships to identify departments within an organisation, or parent company relationships. The organization may be internal (e.g., departments) or external to the utility. There may be multiple organizations of a given 'category', each with a unique 'code'.
    """
    # True if organisation 'opted out', i.e., has requested that their contact information not be shared with other organisations for purposes of solicitation. 
    opt_out = False

    # Operational mode of the organisation, often required for outage reporting purposes. Some utilities use text to describe various modes (like nominal, emergency, storm, other), while others use severity ratings (for example, 0 is a nominal condition and 5 is the most severe condition). 
    mode = ''

    # True if organisation is cost center. 
    is_cost_center = False

    # Designated code for organisation. 
    code = ''

    # True if organisation is profit center. 
    is_profit_center = False

    # Unique identifier for organisation relative to its governing authority, for example a federal tax identifier. 
    government_id = ''

    # Category by utility's corporate standards and practices. 
    category = ''

    # Unique identifier for a given organisation (business). In the USA, this is a 'Dunns' or D&amp;B number. This identifier is typically in addition to the identifiers that organizations assign (on an internal basis) to each of their locations. Note that a unique identifier can be set up for each location of an organisation. This requirement is supported through the recursive Organisation-Organisation relationship, where each child Organisation can have a specified physical location. 
    industry_id = ''

    registered_resources = []

    parent_organisation_roles = []

    location_roles = []

    requests = []

    erp_person_roles = []

    change_items = []

    child_organisation_roles = []

    activity_records = []

    int_sched_agreement = []

    land_property_roles = []

    power_system_resource_roles = []

    asset_roles = []

    violation_limits = []

    document_roles = []

    crews = []

    # <<< erp_organisation
    # @generated
    def __init__(self, opt_out=False, mode='', is_cost_center=False, code='', is_profit_center=False, government_id='', category='', industry_id='', registered_resources=[], parent_organisation_roles=[], location_roles=[], requests=[], erp_person_roles=[], change_items=[], child_organisation_roles=[], activity_records=[], int_sched_agreement=[], land_property_roles=[], power_system_resource_roles=[], asset_roles=[], violation_limits=[], document_roles=[], crews=[], **kw_args):
        """ Initialises a new 'ErpOrganisation' instance.
        """
        self.opt_out = opt_out
        self.mode = mode
        self.is_cost_center = is_cost_center
        self.code = code
        self.is_profit_center = is_profit_center
        self.government_id = government_id
        self.category = category
        self.industry_id = industry_id
        self.registered_resources = registered_resources
        self.parent_organisation_roles = parent_organisation_roles
        self.location_roles = location_roles
        self.requests = requests
        self.erp_person_roles = erp_person_roles
        self.change_items = change_items
        self.child_organisation_roles = child_organisation_roles
        self.activity_records = activity_records
        self.int_sched_agreement = int_sched_agreement
        self.land_property_roles = land_property_roles
        self.power_system_resource_roles = power_system_resource_roles
        self.asset_roles = asset_roles
        self.violation_limits = violation_limits
        self.document_roles = document_roles
        self.crews = crews

        super(ErpOrganisation, self).__init__(**kw_args)
    # >>> erp_organisation


class ErpInvoiceLineItem(Document):
    """ An individual line item on an invoice.
    """
    # The previous line item charge amount. 
    previous_amount = 0.0

    # Line item number on invoice statement. 
    line_number = ''

    # GL account code, must be a valid combination. 
    account_gl = ''

    # The amount due for this line item. 
    line_amount = 0.0

    # The start of a bill period for the line item. 
    start = ''

    # The end of a bill period for the line item. 
    end = ''

    # Kind of line item. Values are: "other", "recalculation", "initial"
    kind = 'other'

    # Date line item will be posted to the General Ledger. 
    date_gl = ''

    # The version number of the bill run. 
    line_version = ''

    # The net line item charge amount. 
    net_amount = 0.0

    component_erp_invoice_line_items = []

    erp_payable_line_item = None

    erp_rec_delv_line_item = None

    # Customer billing for services rendered.
    customer_billing_infos = []

    erp_payments = []

    erp_rec_line_item = None

    market_factors = []

    settlements = []

    erp_invoice = None

    erp_quote_line_item = None

    work_billing_infos = []

    user_attributes = []

    erp_journal_entries = []

    container_erp_invoice_line_item = None

    # <<< erp_invoice_line_item
    # @generated
    def __init__(self, previous_amount=0.0, line_number='', account_gl='', line_amount=0.0, start='', end='', kind='other', date_gl='', line_version='', net_amount=0.0, component_erp_invoice_line_items=[], erp_payable_line_item=None, erp_rec_delv_line_item=None, customer_billing_infos=[], erp_payments=[], erp_rec_line_item=None, market_factors=[], settlements=[], erp_invoice=None, erp_quote_line_item=None, work_billing_infos=[], user_attributes=[], erp_journal_entries=[], container_erp_invoice_line_item=None, **kw_args):
        """ Initialises a new 'ErpInvoiceLineItem' instance.
        """
        self.previous_amount = previous_amount
        self.line_number = line_number
        self.account_gl = account_gl
        self.line_amount = line_amount
        self.start = start
        self.end = end
        self.kind = kind
        self.date_gl = date_gl
        self.line_version = line_version
        self.net_amount = net_amount
        self.component_erp_invoice_line_items = component_erp_invoice_line_items
        self.erp_payable_line_item = erp_payable_line_item
        self.erp_rec_delv_line_item = erp_rec_delv_line_item
        self.customer_billing_infos = customer_billing_infos
        self.erp_payments = erp_payments
        self.erp_rec_line_item = erp_rec_line_item
        self.market_factors = market_factors
        self.settlements = settlements
        self.erp_invoice = erp_invoice
        self.erp_quote_line_item = erp_quote_line_item
        self.work_billing_infos = work_billing_infos
        self.user_attributes = user_attributes
        self.erp_journal_entries = erp_journal_entries
        self.container_erp_invoice_line_item = container_erp_invoice_line_item

        super(ErpInvoiceLineItem, self).__init__(**kw_args)
    # >>> erp_invoice_line_item


class DocErpPersonRole(Role):
    """ Roles played between Persons and Documents.
    """
    erp_person = None

    document = None

    # <<< doc_erp_person_role
    # @generated
    def __init__(self, erp_person=None, document=None, **kw_args):
        """ Initialises a new 'DocErpPersonRole' instance.
        """
        self.erp_person = erp_person
        self.document = document

        super(DocErpPersonRole, self).__init__(**kw_args)
    # >>> doc_erp_person_role


class ErpRequisition(Document):
    """ General information that applies to a utility requisition that is a request for the purchase of goods or services. Typically, a requisition leads to the creation of a purchase order to a specific supplier.
    """
    erp_req_line_items = []

    # <<< erp_requisition
    # @generated
    def __init__(self, erp_req_line_items=[], **kw_args):
        """ Initialises a new 'ErpRequisition' instance.
        """
        self.erp_req_line_items = erp_req_line_items

        super(ErpRequisition, self).__init__(**kw_args)
    # >>> erp_requisition


class ErpReceiveDelivery(Document):
    """ Transaction for an Organisation receiving goods or services that may be used to indicate receipt of goods in conjunction with a purchase order. A receivable is an open (unpaid) item in the Accounts Receivable ledger.
    """
    erp_rec_delv_line_items = []

    # <<< erp_receive_delivery
    # @generated
    def __init__(self, erp_rec_delv_line_items=[], **kw_args):
        """ Initialises a new 'ErpReceiveDelivery' instance.
        """
        self.erp_rec_delv_line_items = erp_rec_delv_line_items

        super(ErpReceiveDelivery, self).__init__(**kw_args)
    # >>> erp_receive_delivery


class ErpChartOfAccounts(Document):
    """ Accounting structure of a business. Each account represents a financial aspect of a business, such as its Accounts Payable, or the value of its inventory, or its office supply expenses.
    """
    pass
    # <<< erp_chart_of_accounts
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ErpChartOfAccounts' instance.
        """

        super(ErpChartOfAccounts, self).__init__(**kw_args)
    # >>> erp_chart_of_accounts


class ErpPersonnel(IdentifiedObject):
    """ Information that applies to the basic data about a utility person, used by ERP applications to transfer Personnel data for a worker.
    """
    status = None

    erp_persons = []

    # <<< erp_personnel
    # @generated
    def __init__(self, status=None, erp_persons=[], **kw_args):
        """ Initialises a new 'ErpPersonnel' instance.
        """
        self.status = status
        self.erp_persons = erp_persons

        super(ErpPersonnel, self).__init__(**kw_args)
    # >>> erp_personnel


class ErpRecDelvLineItem(IdentifiedObject):
    """ Of an ErpReceiveDelivery, this is an individually received good or service by the Organisation receiving goods or services. It may be used to indicate receipt of goods in conjunction with a purchase order line item.
    """
    status = None

    material_items = []

    erp_invoice_line_item = None

    erp_receive_delivery = None

    erp_poline_item = None

    assets = []

    # <<< erp_rec_delv_line_item
    # @generated
    def __init__(self, status=None, material_items=[], erp_invoice_line_item=None, erp_receive_delivery=None, erp_poline_item=None, assets=[], **kw_args):
        """ Initialises a new 'ErpRecDelvLineItem' instance.
        """
        self.status = status
        self.material_items = material_items
        self.erp_invoice_line_item = erp_invoice_line_item
        self.erp_receive_delivery = erp_receive_delivery
        self.erp_poline_item = erp_poline_item
        self.assets = assets

        super(ErpRecDelvLineItem, self).__init__(**kw_args)
    # >>> erp_rec_delv_line_item


class ErpTelephoneNumber(TelephoneNumber):
    """ The telephone number for a person or organisation.
    """
    # The purpose of the telephone: home, mobile, home fax, office, office fax, switchboard, other. 
    usage = ''

    status = None

    electronic_address = None

    erp_persons = []

    # <<< erp_telephone_number
    # @generated
    def __init__(self, usage='', status=None, electronic_address=None, erp_persons=[], **kw_args):
        """ Initialises a new 'ErpTelephoneNumber' instance.
        """
        self.usage = usage
        self.status = status
        self.electronic_address = electronic_address
        self.erp_persons = erp_persons

        super(ErpTelephoneNumber, self).__init__(**kw_args)
    # >>> erp_telephone_number


class ErpSiteLevelData(IdentifiedObject):
    """ For a utility, general information that describes physical locations of organizations or the location codes and their meanings. This enables ERP applications to ensure that the physical location identifiers are synchronized between the business applications.
    """
    status = None

    land_property = None

    # <<< erp_site_level_data
    # @generated
    def __init__(self, status=None, land_property=None, **kw_args):
        """ Initialises a new 'ErpSiteLevelData' instance.
        """
        self.status = status
        self.land_property = land_property

        super(ErpSiteLevelData, self).__init__(**kw_args)
    # >>> erp_site_level_data


class ErpReqLineItem(IdentifiedObject):
    """ Information that describes a requested item and its attributes.
    """
    # Quantity of item requisitioned. 
    quantity = 0

 
    code = ''

 
    delivery_date = ''

    # Cost of material 
    cost = ''

    status = None

    type_material = None

    type_asset = None

    erp_requisition = None

    erp_quote_line_item = None

    erp_poline_item = None

    # <<< erp_req_line_item
    # @generated
    def __init__(self, quantity=0, code='', delivery_date='', cost='', status=None, type_material=None, type_asset=None, erp_requisition=None, erp_quote_line_item=None, erp_poline_item=None, **kw_args):
        """ Initialises a new 'ErpReqLineItem' instance.
        """
        self.quantity = quantity
        self.code = code
        self.delivery_date = delivery_date
        self.cost = cost
        self.status = status
        self.type_material = type_material
        self.type_asset = type_asset
        self.erp_requisition = erp_requisition
        self.erp_quote_line_item = erp_quote_line_item
        self.erp_poline_item = erp_poline_item

        super(ErpReqLineItem, self).__init__(**kw_args)
    # >>> erp_req_line_item


class ErpJournal(Document):
    """ Book for recording accounting transactions as they occur. Transactions and adjustments are first recorded in a journal, which is like a diary of instructions, advising which account to be charged and by how much. A journal represents a change in the balances of a business's financial accounts. Many tasks or transactions throughout an enterprise will result in the creation of a journal. Some examples are creating a customer invoice, paying a vendor, transferring inventory, or paying employees.
    """
    erp_journal_entries = []

    # <<< erp_journal
    # @generated
    def __init__(self, erp_journal_entries=[], **kw_args):
        """ Initialises a new 'ErpJournal' instance.
        """
        self.erp_journal_entries = erp_journal_entries

        super(ErpJournal, self).__init__(**kw_args)
    # >>> erp_journal


class ErpPOLineItem(Document):
    """ Of an ErpPurchaseOrder, this is an individually ordered item or product along with the quantity, price and other descriptive information.
    """
    erp_rec_del_line_item = None

    erp_req_line_item = None

    erp_purchase_order = None

    asset_model_catalogue_item = None

    material_item = None

    # <<< erp_poline_item
    # @generated
    def __init__(self, erp_rec_del_line_item=None, erp_req_line_item=None, erp_purchase_order=None, asset_model_catalogue_item=None, material_item=None, **kw_args):
        """ Initialises a new 'ErpPOLineItem' instance.
        """
        self.erp_rec_del_line_item = erp_rec_del_line_item
        self.erp_req_line_item = erp_req_line_item
        self.erp_purchase_order = erp_purchase_order
        self.asset_model_catalogue_item = asset_model_catalogue_item
        self.material_item = material_item

        super(ErpPOLineItem, self).__init__(**kw_args)
    # >>> erp_poline_item


# <<< inf_erpsupport
# @generated
# >>> inf_erpsupport

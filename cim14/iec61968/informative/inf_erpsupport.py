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

ns_prefix = "cim.IEC61968.Informative.InfERPSupport"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfERPSupport"

class ErpLedgerEntry(IdentifiedObject):
    """ Details of an individual entry in a ledger, which was posted from a journal on the posted date.
    """
    # <<< erp_ledger_entry
    # @generated
    def __init__(self, transaction_date_time='', account_kind='normal', amount=0.0, account_id='', posted_date_time='', status=None, settlements=None, erp_jounal_entry=None, erp_ledger=None, erp_ledger_entry=None, user_attributes=None, **kw_args):
        """ Initialises a new 'ErpLedgerEntry' instance.
        """
        # Date and time journal entry was recorded. 
        self.transaction_date_time = transaction_date_time
        # Kind of account for this entry. Values are: "normal", "statistical", "estimate", "reversal"
        self.account_kind = account_kind
        # The amount of the debit or credit for this account. 
        self.amount = amount
        # Account identifier for this entry. 
        self.account_id = account_id
        # Date and time this entry was posted to the ledger. 
        self.posted_date_time = posted_date_time
        
        self.status = status
        self._settlements = []
        if settlements is None:
            self.settlements = []
        else:
            self.settlements = settlements
        self._erp_jounal_entry = None
        self.erp_jounal_entry = erp_jounal_entry
        self._erp_ledger = None
        self.erp_ledger = erp_ledger
        self._erp_ledger_entry = None
        self.erp_ledger_entry = erp_ledger_entry
        self._user_attributes = []
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes

        super(ErpLedgerEntry, self).__init__(**kw_args)
    # >>> erp_ledger_entry
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< settlements
    # @generated
    def get_settlements(self):
        """ 
        """
        return self._settlements

    def set_settlements(self, value):
        for p in self.settlements:
            filtered = [q for q in p.erp_ledger_entries if q != self]
            p._erp_ledger_entries = filtered
        for r in value:
            if self not in r._erp_ledger_entries:
                r._erp_ledger_entries.append(self)
        self._settlements = value
            
    settlements = property(get_settlements, set_settlements)
    
    def add_settlements(self, *settlements):
        for obj in settlements:
            if self not in obj._erp_ledger_entries:
                obj._erp_ledger_entries.append(self)
            self._settlements.append(obj)
        
    def remove_settlements(self, *settlements):
        for obj in settlements:
            if self in obj._erp_ledger_entries:
                obj._erp_ledger_entries.remove(self)
            self._settlements.remove(obj)
    # >>> settlements

    # <<< erp_jounal_entry
    # @generated
    def get_erp_jounal_entry(self):
        """ 
        """
        return self._erp_jounal_entry

    def set_erp_jounal_entry(self, value):
        if self._erp_jounal_entry is not None:
            self._erp_jounal_entry._erp_ledger_entry = None
            
        self._erp_jounal_entry = value
        if self._erp_jounal_entry is not None:
            self._erp_jounal_entry._erp_ledger_entry = self
            
    erp_jounal_entry = property(get_erp_jounal_entry, set_erp_jounal_entry)
    # >>> erp_jounal_entry

    # <<< erp_ledger
    # @generated
    def get_erp_ledger(self):
        """ 
        """
        return self._erp_ledger

    def set_erp_ledger(self, value):
        if self._erp_ledger is not None:
            filtered = [x for x in self.erp_ledger.erp_ledger_entries if x != self]
            self._erp_ledger._erp_ledger_entries = filtered
            
        self._erp_ledger = value
        if self._erp_ledger is not None:
            if self not in self._erp_ledger._erp_ledger_entries:
                self._erp_ledger._erp_ledger_entries.append(self)

    erp_ledger = property(get_erp_ledger, set_erp_ledger)
    # >>> erp_ledger

    # <<< erp_ledger_entry
    # @generated
    def get_erp_ledger_entry(self):
        """ 
        """
        return self._erp_ledger_entry

    def set_erp_ledger_entry(self, value):
        if self._erp_ledger_entry is not None:
            self._erp_ledger_entry._erp_led_bud_line_item = None
            
        self._erp_ledger_entry = value
        if self._erp_ledger_entry is not None:
            self._erp_ledger_entry._erp_led_bud_line_item = self
            
    erp_ledger_entry = property(get_erp_ledger_entry, set_erp_ledger_entry)
    # >>> erp_ledger_entry

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ 
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for p in self.user_attributes:
            filtered = [q for q in p.erp_ledger_entries if q != self]
            p._erp_ledger_entries = filtered
        for r in value:
            if self not in r._erp_ledger_entries:
                r._erp_ledger_entries.append(self)
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self not in obj._erp_ledger_entries:
                obj._erp_ledger_entries.append(self)
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self in obj._erp_ledger_entries:
                obj._erp_ledger_entries.remove(self)
            self._user_attributes.remove(obj)
    # >>> user_attributes



class ErpLedger(Document):
    """ In accounting transactions, a ledger is a book containing accounts to which debits and credits are posted from journals, where transactions are initially recorded. Journal entries are periodically posted to the ledger. Ledger Actual represents actual amounts by account within ledger within company or business area. Actual amounts may be generated in a source application and then loaded to a specific ledger within the enterprise general ledger or budget application.
    """
    # <<< erp_ledger
    # @generated
    def __init__(self, erp_ledger_entries=None, **kw_args):
        """ Initialises a new 'ErpLedger' instance.
        """
        
        self._erp_ledger_entries = []
        if erp_ledger_entries is None:
            self.erp_ledger_entries = []
        else:
            self.erp_ledger_entries = erp_ledger_entries

        super(ErpLedger, self).__init__(**kw_args)
    # >>> erp_ledger
        
    # <<< erp_ledger_entries
    # @generated
    def get_erp_ledger_entries(self):
        """ 
        """
        return self._erp_ledger_entries

    def set_erp_ledger_entries(self, value):
        for x in self._erp_ledger_entries:
            x.erp_ledger = None
        for y in value:
            y.erp_ledger = self
        self._erp_ledger_entries = value
            
    erp_ledger_entries = property(get_erp_ledger_entries, set_erp_ledger_entries)
    
    def add_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            obj._erp_ledger = self
            if obj not in self._erp_ledger_entries:
                self._erp_ledger_entries.append(obj)
        
    def remove_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            obj._erp_ledger = None
            self._erp_ledger_entries.remove(obj)
    # >>> erp_ledger_entries



class ErpRecLineItem(IdentifiedObject):
    """ Individual entry of an ErpReceivable, it is a particular transaction representing an invoice, credit memo or debit memo to a customer.
    """
    # <<< erp_rec_line_item
    # @generated
    def __init__(self, status=None, erp_receivable=None, erp_payments=None, erp_invoice_line_item=None, erp_journal_entries=None, **kw_args):
        """ Initialises a new 'ErpRecLineItem' instance.
        """
        
        self.status = status
        self._erp_receivable = None
        self.erp_receivable = erp_receivable
        self._erp_payments = []
        if erp_payments is None:
            self.erp_payments = []
        else:
            self.erp_payments = erp_payments
        self._erp_invoice_line_item = None
        self.erp_invoice_line_item = erp_invoice_line_item
        self._erp_journal_entries = []
        if erp_journal_entries is None:
            self.erp_journal_entries = []
        else:
            self.erp_journal_entries = erp_journal_entries

        super(ErpRecLineItem, self).__init__(**kw_args)
    # >>> erp_rec_line_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_receivable
    # @generated
    def get_erp_receivable(self):
        """ 
        """
        return self._erp_receivable

    def set_erp_receivable(self, value):
        if self._erp_receivable is not None:
            filtered = [x for x in self.erp_receivable.erp_rec_line_items if x != self]
            self._erp_receivable._erp_rec_line_items = filtered
            
        self._erp_receivable = value
        if self._erp_receivable is not None:
            if self not in self._erp_receivable._erp_rec_line_items:
                self._erp_receivable._erp_rec_line_items.append(self)

    erp_receivable = property(get_erp_receivable, set_erp_receivable)
    # >>> erp_receivable

    # <<< erp_payments
    # @generated
    def get_erp_payments(self):
        """ 
        """
        return self._erp_payments

    def set_erp_payments(self, value):
        for p in self.erp_payments:
            filtered = [q for q in p.erp_rec_line_items if q != self]
            p._erp_rec_line_items = filtered
        for r in value:
            if self not in r._erp_rec_line_items:
                r._erp_rec_line_items.append(self)
        self._erp_payments = value
            
    erp_payments = property(get_erp_payments, set_erp_payments)
    
    def add_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self not in obj._erp_rec_line_items:
                obj._erp_rec_line_items.append(self)
            self._erp_payments.append(obj)
        
    def remove_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self in obj._erp_rec_line_items:
                obj._erp_rec_line_items.remove(self)
            self._erp_payments.remove(obj)
    # >>> erp_payments

    # <<< erp_invoice_line_item
    # @generated
    def get_erp_invoice_line_item(self):
        """ 
        """
        return self._erp_invoice_line_item

    def set_erp_invoice_line_item(self, value):
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_rec_line_item = None
            
        self._erp_invoice_line_item = value
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_rec_line_item = self
            
    erp_invoice_line_item = property(get_erp_invoice_line_item, set_erp_invoice_line_item)
    # >>> erp_invoice_line_item

    # <<< erp_journal_entries
    # @generated
    def get_erp_journal_entries(self):
        """ 
        """
        return self._erp_journal_entries

    def set_erp_journal_entries(self, value):
        for p in self.erp_journal_entries:
            filtered = [q for q in p.erp_rec_line_items if q != self]
            p._erp_rec_line_items = filtered
        for r in value:
            if self not in r._erp_rec_line_items:
                r._erp_rec_line_items.append(self)
        self._erp_journal_entries = value
            
    erp_journal_entries = property(get_erp_journal_entries, set_erp_journal_entries)
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self not in obj._erp_rec_line_items:
                obj._erp_rec_line_items.append(self)
            self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self in obj._erp_rec_line_items:
                obj._erp_rec_line_items.remove(self)
            self._erp_journal_entries.remove(obj)
    # >>> erp_journal_entries



class DocOrgRole(Role):
    """ Roles played between Organisations and Documents.
    """
    # <<< doc_org_role
    # @generated
    def __init__(self, document=None, erp_organisation=None, **kw_args):
        """ Initialises a new 'DocOrgRole' instance.
        """
        
        self._document = None
        self.document = document
        self._erp_organisation = None
        self.erp_organisation = erp_organisation

        super(DocOrgRole, self).__init__(**kw_args)
    # >>> doc_org_role
        
    # <<< document
    # @generated
    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.erp_organisation_roles if x != self]
            self._document._erp_organisation_roles = filtered
            
        self._document = value
        if self._document is not None:
            if self not in self._document._erp_organisation_roles:
                self._document._erp_organisation_roles.append(self)

    document = property(get_document, set_document)
    # >>> document

    # <<< erp_organisation
    # @generated
    def get_erp_organisation(self):
        """ 
        """
        return self._erp_organisation

    def set_erp_organisation(self, value):
        if self._erp_organisation is not None:
            filtered = [x for x in self.erp_organisation.document_roles if x != self]
            self._erp_organisation._document_roles = filtered
            
        self._erp_organisation = value
        if self._erp_organisation is not None:
            if self not in self._erp_organisation._document_roles:
                self._erp_organisation._document_roles.append(self)

    erp_organisation = property(get_erp_organisation, set_erp_organisation)
    # >>> erp_organisation



class ErpIssueInventory(IdentifiedObject):
    """ Can be used to request an application to process an issue or request information about an issue.
    """
    # <<< erp_issue_inventory
    # @generated
    def __init__(self, status=None, type_material=None, type_asset=None, **kw_args):
        """ Initialises a new 'ErpIssueInventory' instance.
        """
        
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._type_asset = None
        self.type_asset = type_asset

        super(ErpIssueInventory, self).__init__(**kw_args)
    # >>> erp_issue_inventory
        
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
            filtered = [x for x in self.type_material.erp_issue_inventories if x != self]
            self._type_material._erp_issue_inventories = filtered
            
        self._type_material = value
        if self._type_material is not None:
            if self not in self._type_material._erp_issue_inventories:
                self._type_material._erp_issue_inventories.append(self)

    type_material = property(get_type_material, set_type_material)
    # >>> type_material

    # <<< type_asset
    # @generated
    def get_type_asset(self):
        """ 
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            filtered = [x for x in self.type_asset.erp_inventory_issues if x != self]
            self._type_asset._erp_inventory_issues = filtered
            
        self._type_asset = value
        if self._type_asset is not None:
            if self not in self._type_asset._erp_inventory_issues:
                self._type_asset._erp_inventory_issues.append(self)

    type_asset = property(get_type_asset, set_type_asset)
    # >>> type_asset



class ErpInventory(IdentifiedObject):
    """ Utility inventory-related information about an item or part (and not for description of the item and its attributes). It is used by ERP applications to enable the synchronization of Inventory data that exists on separate Item Master databases. This data is not the master data that describes the attributes of the item such as dimensions, weight, or unit of measure - it describes the item as it exists at a specific location.
    """
    # <<< erp_inventory
    # @generated
    def __init__(self, status=None, asset=None, **kw_args):
        """ Initialises a new 'ErpInventory' instance.
        """
        
        self.status = status
        self._asset = None
        self.asset = asset

        super(ErpInventory, self).__init__(**kw_args)
    # >>> erp_inventory
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< asset
    # @generated
    def get_asset(self):
        """ 
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            self._asset._erp_inventory = None
            
        self._asset = value
        if self._asset is not None:
            self._asset._erp_inventory = self
            
    asset = property(get_asset, set_asset)
    # >>> asset



class ErpPurchaseOrder(Document):
    """ A document that communicates an order to purchase goods from a buyer to a supplier. The PurchaseOrder carries information to and from the buyer and supplier. It is a legally binding document once both Parties agree to the contents and the specified terms and conditions of the order.
    """
    # <<< erp_purchase_order
    # @generated
    def __init__(self, erp_poline_items=None, **kw_args):
        """ Initialises a new 'ErpPurchaseOrder' instance.
        """
        
        self._erp_poline_items = []
        if erp_poline_items is None:
            self.erp_poline_items = []
        else:
            self.erp_poline_items = erp_poline_items

        super(ErpPurchaseOrder, self).__init__(**kw_args)
    # >>> erp_purchase_order
        
    # <<< erp_poline_items
    # @generated
    def get_erp_poline_items(self):
        """ 
        """
        return self._erp_poline_items

    def set_erp_poline_items(self, value):
        for x in self._erp_poline_items:
            x.erp_purchase_order = None
        for y in value:
            y.erp_purchase_order = self
        self._erp_poline_items = value
            
    erp_poline_items = property(get_erp_poline_items, set_erp_poline_items)
    
    def add_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._erp_purchase_order = self
            if obj not in self._erp_poline_items:
                self._erp_poline_items.append(obj)
        
    def remove_erp_poline_items(self, *erp_poline_items):
        for obj in erp_poline_items:
            obj._erp_purchase_order = None
            self._erp_poline_items.remove(obj)
    # >>> erp_poline_items



class ErpPayableLineItem(IdentifiedObject):
    """ Of an ErpPayable, a line item references an ErpInvoiceLineitem or other source such as credit memos.
    """
    # <<< erp_payable_line_item
    # @generated
    def __init__(self, status=None, erp_journal_entries=None, erp_payable=None, erp_invoice_line_item=None, erp_payments=None, **kw_args):
        """ Initialises a new 'ErpPayableLineItem' instance.
        """
        
        self.status = status
        self._erp_journal_entries = []
        if erp_journal_entries is None:
            self.erp_journal_entries = []
        else:
            self.erp_journal_entries = erp_journal_entries
        self._erp_payable = None
        self.erp_payable = erp_payable
        self._erp_invoice_line_item = None
        self.erp_invoice_line_item = erp_invoice_line_item
        self._erp_payments = []
        if erp_payments is None:
            self.erp_payments = []
        else:
            self.erp_payments = erp_payments

        super(ErpPayableLineItem, self).__init__(**kw_args)
    # >>> erp_payable_line_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_journal_entries
    # @generated
    def get_erp_journal_entries(self):
        """ 
        """
        return self._erp_journal_entries

    def set_erp_journal_entries(self, value):
        for p in self.erp_journal_entries:
            filtered = [q for q in p.erp_payable_line_items if q != self]
            p._erp_payable_line_items = filtered
        for r in value:
            if self not in r._erp_payable_line_items:
                r._erp_payable_line_items.append(self)
        self._erp_journal_entries = value
            
    erp_journal_entries = property(get_erp_journal_entries, set_erp_journal_entries)
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self not in obj._erp_payable_line_items:
                obj._erp_payable_line_items.append(self)
            self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            if self in obj._erp_payable_line_items:
                obj._erp_payable_line_items.remove(self)
            self._erp_journal_entries.remove(obj)
    # >>> erp_journal_entries

    # <<< erp_payable
    # @generated
    def get_erp_payable(self):
        """ 
        """
        return self._erp_payable

    def set_erp_payable(self, value):
        if self._erp_payable is not None:
            filtered = [x for x in self.erp_payable.erp_payable_line_items if x != self]
            self._erp_payable._erp_payable_line_items = filtered
            
        self._erp_payable = value
        if self._erp_payable is not None:
            if self not in self._erp_payable._erp_payable_line_items:
                self._erp_payable._erp_payable_line_items.append(self)

    erp_payable = property(get_erp_payable, set_erp_payable)
    # >>> erp_payable

    # <<< erp_invoice_line_item
    # @generated
    def get_erp_invoice_line_item(self):
        """ 
        """
        return self._erp_invoice_line_item

    def set_erp_invoice_line_item(self, value):
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_payable_line_item = None
            
        self._erp_invoice_line_item = value
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_payable_line_item = self
            
    erp_invoice_line_item = property(get_erp_invoice_line_item, set_erp_invoice_line_item)
    # >>> erp_invoice_line_item

    # <<< erp_payments
    # @generated
    def get_erp_payments(self):
        """ 
        """
        return self._erp_payments

    def set_erp_payments(self, value):
        for p in self.erp_payments:
            filtered = [q for q in p.erp_payable_line_items if q != self]
            p._erp_payable_line_items = filtered
        for r in value:
            if self not in r._erp_payable_line_items:
                r._erp_payable_line_items.append(self)
        self._erp_payments = value
            
    erp_payments = property(get_erp_payments, set_erp_payments)
    
    def add_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self not in obj._erp_payable_line_items:
                obj._erp_payable_line_items.append(self)
            self._erp_payments.append(obj)
        
    def remove_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self in obj._erp_payable_line_items:
                obj._erp_payable_line_items.remove(self)
            self._erp_payments.remove(obj)
    # >>> erp_payments



class ErpQuote(Document):
    """ Document describing the prices of goods or services provided by a supplier. It includes the terms of the purchase, delivery proposals, identification of goods or services ordered, as well as their quantities.
    """
    # <<< erp_quote
    # @generated
    def __init__(self, erp_quote_line_items=None, **kw_args):
        """ Initialises a new 'ErpQuote' instance.
        """
        
        self._erp_quote_line_items = []
        if erp_quote_line_items is None:
            self.erp_quote_line_items = []
        else:
            self.erp_quote_line_items = erp_quote_line_items

        super(ErpQuote, self).__init__(**kw_args)
    # >>> erp_quote
        
    # <<< erp_quote_line_items
    # @generated
    def get_erp_quote_line_items(self):
        """ 
        """
        return self._erp_quote_line_items

    def set_erp_quote_line_items(self, value):
        for x in self._erp_quote_line_items:
            x.erp_quote = None
        for y in value:
            y.erp_quote = self
        self._erp_quote_line_items = value
            
    erp_quote_line_items = property(get_erp_quote_line_items, set_erp_quote_line_items)
    
    def add_erp_quote_line_items(self, *erp_quote_line_items):
        for obj in erp_quote_line_items:
            obj._erp_quote = self
            if obj not in self._erp_quote_line_items:
                self._erp_quote_line_items.append(obj)
        
    def remove_erp_quote_line_items(self, *erp_quote_line_items):
        for obj in erp_quote_line_items:
            obj._erp_quote = None
            self._erp_quote_line_items.remove(obj)
    # >>> erp_quote_line_items



class ErpBOM(Document):
    """ Information that generally describes the Bill of Material Structure and its contents for a utility.  This is used by ERP systems to transfer Bill of Material information between two business applications.
    """
    # <<< erp_bom
    # @generated
    def __init__(self, erp_bom_item_datas=None, design=None, **kw_args):
        """ Initialises a new 'ErpBOM' instance.
        """
        
        self._erp_bom_item_datas = []
        if erp_bom_item_datas is None:
            self.erp_bom_item_datas = []
        else:
            self.erp_bom_item_datas = erp_bom_item_datas
        self._design = None
        self.design = design

        super(ErpBOM, self).__init__(**kw_args)
    # >>> erp_bom
        
    # <<< erp_bom_item_datas
    # @generated
    def get_erp_bom_item_datas(self):
        """ 
        """
        return self._erp_bom_item_datas

    def set_erp_bom_item_datas(self, value):
        for x in self._erp_bom_item_datas:
            x.erp_bom = None
        for y in value:
            y.erp_bom = self
        self._erp_bom_item_datas = value
            
    erp_bom_item_datas = property(get_erp_bom_item_datas, set_erp_bom_item_datas)
    
    def add_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._erp_bom = self
            if obj not in self._erp_bom_item_datas:
                self._erp_bom_item_datas.append(obj)
        
    def remove_erp_bom_item_datas(self, *erp_bom_item_datas):
        for obj in erp_bom_item_datas:
            obj._erp_bom = None
            self._erp_bom_item_datas.remove(obj)
    # >>> erp_bom_item_datas

    # <<< design
    # @generated
    def get_design(self):
        """ 
        """
        return self._design

    def set_design(self, value):
        if self._design is not None:
            filtered = [x for x in self.design.erp_boms if x != self]
            self._design._erp_boms = filtered
            
        self._design = value
        if self._design is not None:
            if self not in self._design._erp_boms:
                self._design._erp_boms.append(self)

    design = property(get_design, set_design)
    # >>> design



class ErpInvoice(Document):
    """ A roll up of invoice line items. The whole invoice has a due date and amount to be paid, with information such as customer, banks etc. being obtained through associations. The invoice roll up is based on individual line items that each contain amounts and descriptions for specific services or products.
    """
    # <<< erp_invoice
    # @generated
    def __init__(self, transaction_date='', transfer_type='', due_date='', kind='sales', reference_number='', amount=0.0, bill_media_kind='electronic', pro_forma=False, mailed_date='', customer_account=None, erp_invoice_line_items=None, **kw_args):
        """ Initialises a new 'ErpInvoice' instance.
        """
        # The date of which the invoice is issued. 
        self.transaction_date = transaction_date
        # The type of invoice transfer. 
        self.transfer_type = transfer_type
        # Calculated date upon which the Invoice amount is due. 
        self.due_date = due_date
        # Kind of invoice (default is 'sales'). Values are: "sales", "purchase"
        self.kind = kind
        # The number of an invoice to be reference by this invoice. 
        self.reference_number = reference_number
        # Total amount due on this invoice based on line items and applicable adjustments. 
        self.amount = amount
        # Kind of media by which the CustomerBillingInfo was delivered. Values are: "electronic", "other", "paper"
        self.bill_media_kind = bill_media_kind
        # True if payment is to be paid by a Customer to accept a particular ErpQuote (with associated Design) and have work initiated, at which time an associated ErpInvoice should automatically be generated. EprPayment.subjectStatus satisfies terms specificed in the ErpQuote. 
        self.pro_forma = pro_forma
        # Date on which the customer billing statement/invoice was printed/mailed. 
        self.mailed_date = mailed_date
        
        self._customer_account = None
        self.customer_account = customer_account
        self._erp_invoice_line_items = []
        if erp_invoice_line_items is None:
            self.erp_invoice_line_items = []
        else:
            self.erp_invoice_line_items = erp_invoice_line_items

        super(ErpInvoice, self).__init__(**kw_args)
    # >>> erp_invoice
        
    # <<< customer_account
    # @generated
    def get_customer_account(self):
        """ 
        """
        return self._customer_account

    def set_customer_account(self, value):
        if self._customer_account is not None:
            filtered = [x for x in self.customer_account.erp_invoicees if x != self]
            self._customer_account._erp_invoicees = filtered
            
        self._customer_account = value
        if self._customer_account is not None:
            if self not in self._customer_account._erp_invoicees:
                self._customer_account._erp_invoicees.append(self)

    customer_account = property(get_customer_account, set_customer_account)
    # >>> customer_account

    # <<< erp_invoice_line_items
    # @generated
    def get_erp_invoice_line_items(self):
        """ 
        """
        return self._erp_invoice_line_items

    def set_erp_invoice_line_items(self, value):
        for x in self._erp_invoice_line_items:
            x.erp_invoice = None
        for y in value:
            y.erp_invoice = self
        self._erp_invoice_line_items = value
            
    erp_invoice_line_items = property(get_erp_invoice_line_items, set_erp_invoice_line_items)
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            obj._erp_invoice = self
            if obj not in self._erp_invoice_line_items:
                self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            obj._erp_invoice = None
            self._erp_invoice_line_items.remove(obj)
    # >>> erp_invoice_line_items



class ErpLedBudLineItem(IdentifiedObject):
    """ Individual entry of a given Ledger Budget, typically containing information such as amount, accounting date, accounting period, and is associated with the applicable general ledger account.
    """
    # <<< erp_led_bud_line_item
    # @generated
    def __init__(self, status=None, erp_led_bud_line_item=None, erp_ledger_budget=None, **kw_args):
        """ Initialises a new 'ErpLedBudLineItem' instance.
        """
        
        self.status = status
        self._erp_led_bud_line_item = None
        self.erp_led_bud_line_item = erp_led_bud_line_item
        self._erp_ledger_budget = None
        self.erp_ledger_budget = erp_ledger_budget

        super(ErpLedBudLineItem, self).__init__(**kw_args)
    # >>> erp_led_bud_line_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_led_bud_line_item
    # @generated
    def get_erp_led_bud_line_item(self):
        """ 
        """
        return self._erp_led_bud_line_item

    def set_erp_led_bud_line_item(self, value):
        if self._erp_led_bud_line_item is not None:
            self._erp_led_bud_line_item._erp_ledger_entry = None
            
        self._erp_led_bud_line_item = value
        if self._erp_led_bud_line_item is not None:
            self._erp_led_bud_line_item._erp_ledger_entry = self
            
    erp_led_bud_line_item = property(get_erp_led_bud_line_item, set_erp_led_bud_line_item)
    # >>> erp_led_bud_line_item

    # <<< erp_ledger_budget
    # @generated
    def get_erp_ledger_budget(self):
        """ 
        """
        return self._erp_ledger_budget

    def set_erp_ledger_budget(self, value):
        if self._erp_ledger_budget is not None:
            filtered = [x for x in self.erp_ledger_budget.erp_led_bud_line_items if x != self]
            self._erp_ledger_budget._erp_led_bud_line_items = filtered
            
        self._erp_ledger_budget = value
        if self._erp_ledger_budget is not None:
            if self not in self._erp_ledger_budget._erp_led_bud_line_items:
                self._erp_ledger_budget._erp_led_bud_line_items.append(self)

    erp_ledger_budget = property(get_erp_ledger_budget, set_erp_ledger_budget)
    # >>> erp_ledger_budget



class ErpPayment(Document):
    """ Payment infromation and status for any individual line item of an ErpInvoice (e.g., when payment is from a customer). ErpPayable is also updated when payment is to a supplier and ErpReceivable is updated when payment is from a customer. Multiple payments can be made against a single line item and an individual payment can apply to more that one line item.
    """
    # <<< erp_payment
    # @generated
    def __init__(self, terms_payment='', erp_rec_line_items=None, erp_invoice_line_items=None, erp_payable_line_items=None, **kw_args):
        """ Initialises a new 'ErpPayment' instance.
        """
        # Payment terms (e.g., net 30). 
        self.terms_payment = terms_payment
        
        self._erp_rec_line_items = []
        if erp_rec_line_items is None:
            self.erp_rec_line_items = []
        else:
            self.erp_rec_line_items = erp_rec_line_items
        self._erp_invoice_line_items = []
        if erp_invoice_line_items is None:
            self.erp_invoice_line_items = []
        else:
            self.erp_invoice_line_items = erp_invoice_line_items
        self._erp_payable_line_items = []
        if erp_payable_line_items is None:
            self.erp_payable_line_items = []
        else:
            self.erp_payable_line_items = erp_payable_line_items

        super(ErpPayment, self).__init__(**kw_args)
    # >>> erp_payment
        
    # <<< erp_rec_line_items
    # @generated
    def get_erp_rec_line_items(self):
        """ 
        """
        return self._erp_rec_line_items

    def set_erp_rec_line_items(self, value):
        for p in self.erp_rec_line_items:
            filtered = [q for q in p.erp_payments if q != self]
            p._erp_payments = filtered
        for r in value:
            if self not in r._erp_payments:
                r._erp_payments.append(self)
        self._erp_rec_line_items = value
            
    erp_rec_line_items = property(get_erp_rec_line_items, set_erp_rec_line_items)
    
    def add_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            if self not in obj._erp_payments:
                obj._erp_payments.append(self)
            self._erp_rec_line_items.append(obj)
        
    def remove_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            if self in obj._erp_payments:
                obj._erp_payments.remove(self)
            self._erp_rec_line_items.remove(obj)
    # >>> erp_rec_line_items

    # <<< erp_invoice_line_items
    # @generated
    def get_erp_invoice_line_items(self):
        """ 
        """
        return self._erp_invoice_line_items

    def set_erp_invoice_line_items(self, value):
        for p in self.erp_invoice_line_items:
            filtered = [q for q in p.erp_payments if q != self]
            p._erp_payments = filtered
        for r in value:
            if self not in r._erp_payments:
                r._erp_payments.append(self)
        self._erp_invoice_line_items = value
            
    erp_invoice_line_items = property(get_erp_invoice_line_items, set_erp_invoice_line_items)
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self not in obj._erp_payments:
                obj._erp_payments.append(self)
            self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self in obj._erp_payments:
                obj._erp_payments.remove(self)
            self._erp_invoice_line_items.remove(obj)
    # >>> erp_invoice_line_items

    # <<< erp_payable_line_items
    # @generated
    def get_erp_payable_line_items(self):
        """ 
        """
        return self._erp_payable_line_items

    def set_erp_payable_line_items(self, value):
        for p in self.erp_payable_line_items:
            filtered = [q for q in p.erp_payments if q != self]
            p._erp_payments = filtered
        for r in value:
            if self not in r._erp_payments:
                r._erp_payments.append(self)
        self._erp_payable_line_items = value
            
    erp_payable_line_items = property(get_erp_payable_line_items, set_erp_payable_line_items)
    
    def add_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            if self not in obj._erp_payments:
                obj._erp_payments.append(self)
            self._erp_payable_line_items.append(obj)
        
    def remove_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            if self in obj._erp_payments:
                obj._erp_payments.remove(self)
            self._erp_payable_line_items.remove(obj)
    # >>> erp_payable_line_items



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
    # <<< erp_inventory_count
    # @generated
    def __init__(self, status=None, material_item=None, asset_model=None, **kw_args):
        """ Initialises a new 'ErpInventoryCount' instance.
        """
        
        self.status = status
        self._material_item = None
        self.material_item = material_item
        self._asset_model = None
        self.asset_model = asset_model

        super(ErpInventoryCount, self).__init__(**kw_args)
    # >>> erp_inventory_count
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< material_item
    # @generated
    def get_material_item(self):
        """ 
        """
        return self._material_item

    def set_material_item(self, value):
        if self._material_item is not None:
            filtered = [x for x in self.material_item.erp_inventory_counts if x != self]
            self._material_item._erp_inventory_counts = filtered
            
        self._material_item = value
        if self._material_item is not None:
            if self not in self._material_item._erp_inventory_counts:
                self._material_item._erp_inventory_counts.append(self)

    material_item = property(get_material_item, set_material_item)
    # >>> material_item

    # <<< asset_model
    # @generated
    def get_asset_model(self):
        """ 
        """
        return self._asset_model

    def set_asset_model(self, value):
        if self._asset_model is not None:
            filtered = [x for x in self.asset_model.erp_inventory_counts if x != self]
            self._asset_model._erp_inventory_counts = filtered
            
        self._asset_model = value
        if self._asset_model is not None:
            if self not in self._asset_model._erp_inventory_counts:
                self._asset_model._erp_inventory_counts.append(self)

    asset_model = property(get_asset_model, set_asset_model)
    # >>> asset_model



class ErpTimeSheet(Document):
    """ Time sheet for employees and contractors. Note that ErpTimeSheet inherits the relationship to ErpPerson from Document.
    """
    # <<< erp_time_sheet
    # @generated
    def __init__(self, erp_time_entries=None, **kw_args):
        """ Initialises a new 'ErpTimeSheet' instance.
        """
        
        self._erp_time_entries = []
        if erp_time_entries is None:
            self.erp_time_entries = []
        else:
            self.erp_time_entries = erp_time_entries

        super(ErpTimeSheet, self).__init__(**kw_args)
    # >>> erp_time_sheet
        
    # <<< erp_time_entries
    # @generated
    def get_erp_time_entries(self):
        """ 
        """
        return self._erp_time_entries

    def set_erp_time_entries(self, value):
        for x in self._erp_time_entries:
            x.erp_time_sheet = None
        for y in value:
            y.erp_time_sheet = self
        self._erp_time_entries = value
            
    erp_time_entries = property(get_erp_time_entries, set_erp_time_entries)
    
    def add_erp_time_entries(self, *erp_time_entries):
        for obj in erp_time_entries:
            obj._erp_time_sheet = self
            if obj not in self._erp_time_entries:
                self._erp_time_entries.append(obj)
        
    def remove_erp_time_entries(self, *erp_time_entries):
        for obj in erp_time_entries:
            obj._erp_time_sheet = None
            self._erp_time_entries.remove(obj)
    # >>> erp_time_entries



class OrgOrgRole(Role):
    """ Roles played between Organisations and other Organisations. This includes role ups for ogranisations, cost centers, profit centers, regulatory reporting, etc. Note that the parent and child relationship is indicated by the name on each end of the association.
    """
    # <<< org_org_role
    # @generated
    def __init__(self, client_id='', parent_organisation=None, child_organisation=None, **kw_args):
        """ Initialises a new 'OrgOrgRole' instance.
        """
        # Identifiers of the organisation held by another organisation, such as a government agency (federal, state, province, city, county), financial institution (Dun and Bradstreet), etc. 
        self.client_id = client_id
        
        self._parent_organisation = None
        self.parent_organisation = parent_organisation
        self._child_organisation = None
        self.child_organisation = child_organisation

        super(OrgOrgRole, self).__init__(**kw_args)
    # >>> org_org_role
        
    # <<< parent_organisation
    # @generated
    def get_parent_organisation(self):
        """ 
        """
        return self._parent_organisation

    def set_parent_organisation(self, value):
        if self._parent_organisation is not None:
            filtered = [x for x in self.parent_organisation.child_organisation_roles if x != self]
            self._parent_organisation._child_organisation_roles = filtered
            
        self._parent_organisation = value
        if self._parent_organisation is not None:
            if self not in self._parent_organisation._child_organisation_roles:
                self._parent_organisation._child_organisation_roles.append(self)

    parent_organisation = property(get_parent_organisation, set_parent_organisation)
    # >>> parent_organisation

    # <<< child_organisation
    # @generated
    def get_child_organisation(self):
        """ 
        """
        return self._child_organisation

    def set_child_organisation(self, value):
        if self._child_organisation is not None:
            filtered = [x for x in self.child_organisation.parent_organisation_roles if x != self]
            self._child_organisation._parent_organisation_roles = filtered
            
        self._child_organisation = value
        if self._child_organisation is not None:
            if self not in self._child_organisation._parent_organisation_roles:
                self._child_organisation._parent_organisation_roles.append(self)

    child_organisation = property(get_child_organisation, set_child_organisation)
    # >>> child_organisation



class ErpItemMaster(IdentifiedObject):
    """ Any unique purchased part for manufactured product tracked by ERP systems for a utility. Item, as used by the OAG, refers to the basic information about an item, including its attributes, cost, and locations. It does not include item quantities. Compare to the Inventory, which includes all quantities and other location-specific information.
    """
    # <<< erp_item_master
    # @generated
    def __init__(self, status=None, asset=None, **kw_args):
        """ Initialises a new 'ErpItemMaster' instance.
        """
        
        self.status = status
        self._asset = None
        self.asset = asset

        super(ErpItemMaster, self).__init__(**kw_args)
    # >>> erp_item_master
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< asset
    # @generated
    def get_asset(self):
        """ 
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            self._asset._erp_item_master = None
            
        self._asset = value
        if self._asset is not None:
            self._asset._erp_item_master = self
            
    asset = property(get_asset, set_asset)
    # >>> asset



class ErpProjectAccounting(Document):
    """ Utility Project Accounting information, used by ERP applications to enable all relevant sub-systems that submit single sided transactions to transfer information with a Project Accounting Application. This would include, but not necessarily be limited to: Accounts Payable, Accounts Receivable, Budget, Order Management, Purchasing, Time and Labor, Travel and Expense.
    """
    # <<< erp_project_accounting
    # @generated
    def __init__(self, works=None, work_cost_details=None, projects=None, erp_time_entries=None, **kw_args):
        """ Initialises a new 'ErpProjectAccounting' instance.
        """
        
        self._works = []
        if works is None:
            self.works = []
        else:
            self.works = works
        self._work_cost_details = []
        if work_cost_details is None:
            self.work_cost_details = []
        else:
            self.work_cost_details = work_cost_details
        self._projects = []
        if projects is None:
            self.projects = []
        else:
            self.projects = projects
        self._erp_time_entries = []
        if erp_time_entries is None:
            self.erp_time_entries = []
        else:
            self.erp_time_entries = erp_time_entries

        super(ErpProjectAccounting, self).__init__(**kw_args)
    # >>> erp_project_accounting
        
    # <<< works
    # @generated
    def get_works(self):
        """ 
        """
        return self._works

    def set_works(self, value):
        for x in self._works:
            x.erp_project_accounting = None
        for y in value:
            y.erp_project_accounting = self
        self._works = value
            
    works = property(get_works, set_works)
    
    def add_works(self, *works):
        for obj in works:
            obj._erp_project_accounting = self
            if obj not in self._works:
                self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
            obj._erp_project_accounting = None
            self._works.remove(obj)
    # >>> works

    # <<< work_cost_details
    # @generated
    def get_work_cost_details(self):
        """ 
        """
        return self._work_cost_details

    def set_work_cost_details(self, value):
        for x in self._work_cost_details:
            x.erp_project_accounting = None
        for y in value:
            y.erp_project_accounting = self
        self._work_cost_details = value
            
    work_cost_details = property(get_work_cost_details, set_work_cost_details)
    
    def add_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._erp_project_accounting = self
            if obj not in self._work_cost_details:
                self._work_cost_details.append(obj)
        
    def remove_work_cost_details(self, *work_cost_details):
        for obj in work_cost_details:
            obj._erp_project_accounting = None
            self._work_cost_details.remove(obj)
    # >>> work_cost_details

    # <<< projects
    # @generated
    def get_projects(self):
        """ 
        """
        return self._projects

    def set_projects(self, value):
        for x in self._projects:
            x.erp_project_accounting = None
        for y in value:
            y.erp_project_accounting = self
        self._projects = value
            
    projects = property(get_projects, set_projects)
    
    def add_projects(self, *projects):
        for obj in projects:
            obj._erp_project_accounting = self
            if obj not in self._projects:
                self._projects.append(obj)
        
    def remove_projects(self, *projects):
        for obj in projects:
            obj._erp_project_accounting = None
            self._projects.remove(obj)
    # >>> projects

    # <<< erp_time_entries
    # @generated
    def get_erp_time_entries(self):
        """ 
        """
        return self._erp_time_entries

    def set_erp_time_entries(self, value):
        for x in self._erp_time_entries:
            x.erp_project_accounting = None
        for y in value:
            y.erp_project_accounting = self
        self._erp_time_entries = value
            
    erp_time_entries = property(get_erp_time_entries, set_erp_time_entries)
    
    def add_erp_time_entries(self, *erp_time_entries):
        for obj in erp_time_entries:
            obj._erp_project_accounting = self
            if obj not in self._erp_time_entries:
                self._erp_time_entries.append(obj)
        
    def remove_erp_time_entries(self, *erp_time_entries):
        for obj in erp_time_entries:
            obj._erp_project_accounting = None
            self._erp_time_entries.remove(obj)
    # >>> erp_time_entries



class ErpReceivable(Document):
    """ Transaction representing an invoice, credit memo or debit memo to a customer. It is an open (unpaid) item in the Accounts Receivable ledger.
    """
    # <<< erp_receivable
    # @generated
    def __init__(self, erp_rec_line_items=None, **kw_args):
        """ Initialises a new 'ErpReceivable' instance.
        """
        
        self._erp_rec_line_items = []
        if erp_rec_line_items is None:
            self.erp_rec_line_items = []
        else:
            self.erp_rec_line_items = erp_rec_line_items

        super(ErpReceivable, self).__init__(**kw_args)
    # >>> erp_receivable
        
    # <<< erp_rec_line_items
    # @generated
    def get_erp_rec_line_items(self):
        """ 
        """
        return self._erp_rec_line_items

    def set_erp_rec_line_items(self, value):
        for x in self._erp_rec_line_items:
            x.erp_receivable = None
        for y in value:
            y.erp_receivable = self
        self._erp_rec_line_items = value
            
    erp_rec_line_items = property(get_erp_rec_line_items, set_erp_rec_line_items)
    
    def add_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            obj._erp_receivable = self
            if obj not in self._erp_rec_line_items:
                self._erp_rec_line_items.append(obj)
        
    def remove_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            obj._erp_receivable = None
            self._erp_rec_line_items.remove(obj)
    # >>> erp_rec_line_items



class ErpBankAccount(BankAccount):
    """ Relationship under a particular name, usually evidenced by a deposit against which withdrawals can be made. Types of bank accounts include: demand, time, custodial, joint, trustee, corporate, special, and regular accounts. A statement of transactions during a fiscal period and the resulting balance is maintained on each account. For Payment metering, the account is associated with Bank and Supplier, reflecting details of the bank account used for depositing revenue collected by TokenVendor. The name of the account holder should be specified in 'name' attribute.
    """
    # <<< erp_bank_account
    # @generated
    def __init__(self, bank_aba='', **kw_args):
        """ Initialises a new 'ErpBankAccount' instance.
        """
        # Bank ABA. 
        self.bank_aba = bank_aba
        

        super(ErpBankAccount, self).__init__(**kw_args)
    # >>> erp_bank_account
        


class OrgErpPersonRole(Role):
    """ Roles played between Persons and Organisations.
    """
    # <<< org_erp_person_role
    # @generated
    def __init__(self, client_id='', erp_organisation=None, erp_person=None, **kw_args):
        """ Initialises a new 'OrgErpPersonRole' instance.
        """
        # Identifiers of the person held by an organisation, such as a government agency (federal, state, province, city, county), financial institutions, etc. 
        self.client_id = client_id
        
        self._erp_organisation = None
        self.erp_organisation = erp_organisation
        self._erp_person = None
        self.erp_person = erp_person

        super(OrgErpPersonRole, self).__init__(**kw_args)
    # >>> org_erp_person_role
        
    # <<< erp_organisation
    # @generated
    def get_erp_organisation(self):
        """ 
        """
        return self._erp_organisation

    def set_erp_organisation(self, value):
        if self._erp_organisation is not None:
            filtered = [x for x in self.erp_organisation.erp_person_roles if x != self]
            self._erp_organisation._erp_person_roles = filtered
            
        self._erp_organisation = value
        if self._erp_organisation is not None:
            if self not in self._erp_organisation._erp_person_roles:
                self._erp_organisation._erp_person_roles.append(self)

    erp_organisation = property(get_erp_organisation, set_erp_organisation)
    # >>> erp_organisation

    # <<< erp_person
    # @generated
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.erp_organisation_roles if x != self]
            self._erp_person._erp_organisation_roles = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            if self not in self._erp_person._erp_organisation_roles:
                self._erp_person._erp_organisation_roles.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person



class ErpTimeEntry(IdentifiedObject):
    """ An individual entry on an ErpTimeSheet.
    """
    # <<< erp_time_entry
    # @generated
    def __init__(self, status=None, erp_time_sheet=None, erp_project_accounting=None, **kw_args):
        """ Initialises a new 'ErpTimeEntry' instance.
        """
        
        self.status = status
        self._erp_time_sheet = None
        self.erp_time_sheet = erp_time_sheet
        self._erp_project_accounting = None
        self.erp_project_accounting = erp_project_accounting

        super(ErpTimeEntry, self).__init__(**kw_args)
    # >>> erp_time_entry
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_time_sheet
    # @generated
    def get_erp_time_sheet(self):
        """ 
        """
        return self._erp_time_sheet

    def set_erp_time_sheet(self, value):
        if self._erp_time_sheet is not None:
            filtered = [x for x in self.erp_time_sheet.erp_time_entries if x != self]
            self._erp_time_sheet._erp_time_entries = filtered
            
        self._erp_time_sheet = value
        if self._erp_time_sheet is not None:
            if self not in self._erp_time_sheet._erp_time_entries:
                self._erp_time_sheet._erp_time_entries.append(self)

    erp_time_sheet = property(get_erp_time_sheet, set_erp_time_sheet)
    # >>> erp_time_sheet

    # <<< erp_project_accounting
    # @generated
    def get_erp_project_accounting(self):
        """ 
        """
        return self._erp_project_accounting

    def set_erp_project_accounting(self, value):
        if self._erp_project_accounting is not None:
            filtered = [x for x in self.erp_project_accounting.erp_time_entries if x != self]
            self._erp_project_accounting._erp_time_entries = filtered
            
        self._erp_project_accounting = value
        if self._erp_project_accounting is not None:
            if self not in self._erp_project_accounting._erp_time_entries:
                self._erp_project_accounting._erp_time_entries.append(self)

    erp_project_accounting = property(get_erp_project_accounting, set_erp_project_accounting)
    # >>> erp_project_accounting



class ErpPayable(Document):
    """ A transaction that represents an invoice from a supplier. A payable (or voucher) is an open item, approved and ready for payment, in the Accounts Payable ledger.
    """
    # <<< erp_payable
    # @generated
    def __init__(self, erp_payable_line_items=None, contractor_items=None, **kw_args):
        """ Initialises a new 'ErpPayable' instance.
        """
        
        self._erp_payable_line_items = []
        if erp_payable_line_items is None:
            self.erp_payable_line_items = []
        else:
            self.erp_payable_line_items = erp_payable_line_items
        self._contractor_items = []
        if contractor_items is None:
            self.contractor_items = []
        else:
            self.contractor_items = contractor_items

        super(ErpPayable, self).__init__(**kw_args)
    # >>> erp_payable
        
    # <<< erp_payable_line_items
    # @generated
    def get_erp_payable_line_items(self):
        """ 
        """
        return self._erp_payable_line_items

    def set_erp_payable_line_items(self, value):
        for x in self._erp_payable_line_items:
            x.erp_payable = None
        for y in value:
            y.erp_payable = self
        self._erp_payable_line_items = value
            
    erp_payable_line_items = property(get_erp_payable_line_items, set_erp_payable_line_items)
    
    def add_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            obj._erp_payable = self
            if obj not in self._erp_payable_line_items:
                self._erp_payable_line_items.append(obj)
        
    def remove_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            obj._erp_payable = None
            self._erp_payable_line_items.remove(obj)
    # >>> erp_payable_line_items

    # <<< contractor_items
    # @generated
    def get_contractor_items(self):
        """ 
        """
        return self._contractor_items

    def set_contractor_items(self, value):
        for p in self.contractor_items:
            filtered = [q for q in p.erp_payables if q != self]
            p._erp_payables = filtered
        for r in value:
            if self not in r._erp_payables:
                r._erp_payables.append(self)
        self._contractor_items = value
            
    contractor_items = property(get_contractor_items, set_contractor_items)
    
    def add_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            if self not in obj._erp_payables:
                obj._erp_payables.append(self)
            self._contractor_items.append(obj)
        
    def remove_contractor_items(self, *contractor_items):
        for obj in contractor_items:
            if self in obj._erp_payables:
                obj._erp_payables.remove(self)
            self._contractor_items.remove(obj)
    # >>> contractor_items



class ErpPerson(IdentifiedObject):
    """ General purpose information for name and other information to contact people.
    """
    # <<< erp_person
    # @generated
    def __init__(self, first_name='', suffix='', prefix='', government_id='', last_name='', m_name='', special_need='', category='', status=None, crafts=None, labor_items=None, erp_personnel=None, location_roles=None, electronic_addresses=None, switching_step_roles=None, activity_records=None, land_property_roles=None, erp_organisation_roles=None, call_backs=None, change_items=None, skills=None, erp_competency=None, erp_telephone_numbers=None, customer_data=None, document_roles=None, crews=None, measurement_values=None, appointments=None, **kw_args):
        """ Initialises a new 'ErpPerson' instance.
        """
        # Person's first name. 
        self.first_name = first_name
        # A suffix for the person's name, such as II, III, etc. 
        self.suffix = suffix
        # A prefix or title for the person's name, such as Miss, Mister, Doctor, etc. 
        self.prefix = prefix
        # Unique identifier for person relative to its governing authority, for example a federal tax identifier (such as a Social Security number in the United States). 
        self.government_id = government_id
        # Person's last (family, sir) name. 
        self.last_name = last_name
        # Middle name(s) or initial(s). 
        self.m_name = m_name
        # Special service needs for the person (contact) are described; examples include life support, etc. 
        self.special_need = special_need
        # Category of this person relative to utility operations, classified according to the utility's corporate standards and practices. Examples include employee, contractor, agent, not affiliated, etc. Note that this field is not used to indicate whether this person is a customer of the utility. Often an employee or contractor is also a customer. Customer information is gained with relationship to Organisation and CustomerData. In similar fashion, this field does not indicate the various roles this person may fill as part of utility operations. 
        self.category = category
        
        self.status = status
        self._crafts = []
        if crafts is None:
            self.crafts = []
        else:
            self.crafts = crafts
        self._labor_items = []
        if labor_items is None:
            self.labor_items = []
        else:
            self.labor_items = labor_items
        self._erp_personnel = None
        self.erp_personnel = erp_personnel
        self._location_roles = []
        if location_roles is None:
            self.location_roles = []
        else:
            self.location_roles = location_roles
        self._electronic_addresses = []
        if electronic_addresses is None:
            self.electronic_addresses = []
        else:
            self.electronic_addresses = electronic_addresses
        self._switching_step_roles = []
        if switching_step_roles is None:
            self.switching_step_roles = []
        else:
            self.switching_step_roles = switching_step_roles
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records
        self._land_property_roles = []
        if land_property_roles is None:
            self.land_property_roles = []
        else:
            self.land_property_roles = land_property_roles
        self._erp_organisation_roles = []
        if erp_organisation_roles is None:
            self.erp_organisation_roles = []
        else:
            self.erp_organisation_roles = erp_organisation_roles
        self._call_backs = []
        if call_backs is None:
            self.call_backs = []
        else:
            self.call_backs = call_backs
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items
        self._skills = []
        if skills is None:
            self.skills = []
        else:
            self.skills = skills
        self._erp_competency = None
        self.erp_competency = erp_competency
        self._erp_telephone_numbers = []
        if erp_telephone_numbers is None:
            self.erp_telephone_numbers = []
        else:
            self.erp_telephone_numbers = erp_telephone_numbers
        self._customer_data = None
        self.customer_data = customer_data
        self._document_roles = []
        if document_roles is None:
            self.document_roles = []
        else:
            self.document_roles = document_roles
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews
        self._measurement_values = []
        if measurement_values is None:
            self.measurement_values = []
        else:
            self.measurement_values = measurement_values
        self._appointments = []
        if appointments is None:
            self.appointments = []
        else:
            self.appointments = appointments

        super(ErpPerson, self).__init__(**kw_args)
    # >>> erp_person
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< crafts
    # @generated
    def get_crafts(self):
        """ 
        """
        return self._crafts

    def set_crafts(self, value):
        for p in self.crafts:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._crafts = value
            
    crafts = property(get_crafts, set_crafts)
    
    def add_crafts(self, *crafts):
        for obj in crafts:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._crafts.append(obj)
        
    def remove_crafts(self, *crafts):
        for obj in crafts:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._crafts.remove(obj)
    # >>> crafts

    # <<< labor_items
    # @generated
    def get_labor_items(self):
        """ 
        """
        return self._labor_items

    def set_labor_items(self, value):
        for p in self.labor_items:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._labor_items = value
            
    labor_items = property(get_labor_items, set_labor_items)
    
    def add_labor_items(self, *labor_items):
        for obj in labor_items:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._labor_items.append(obj)
        
    def remove_labor_items(self, *labor_items):
        for obj in labor_items:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._labor_items.remove(obj)
    # >>> labor_items

    # <<< erp_personnel
    # @generated
    def get_erp_personnel(self):
        """ 
        """
        return self._erp_personnel

    def set_erp_personnel(self, value):
        if self._erp_personnel is not None:
            filtered = [x for x in self.erp_personnel.erp_persons if x != self]
            self._erp_personnel._erp_persons = filtered
            
        self._erp_personnel = value
        if self._erp_personnel is not None:
            if self not in self._erp_personnel._erp_persons:
                self._erp_personnel._erp_persons.append(self)

    erp_personnel = property(get_erp_personnel, set_erp_personnel)
    # >>> erp_personnel

    # <<< location_roles
    # @generated
    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._erp_person = self
            if obj not in self._location_roles:
                self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._erp_person = None
            self._location_roles.remove(obj)
    # >>> location_roles

    # <<< electronic_addresses
    # @generated
    def get_electronic_addresses(self):
        """ 
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._erp_person = self
            if obj not in self._electronic_addresses:
                self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._erp_person = None
            self._electronic_addresses.remove(obj)
    # >>> electronic_addresses

    # <<< switching_step_roles
    # @generated
    def get_switching_step_roles(self):
        """ 
        """
        return self._switching_step_roles

    def set_switching_step_roles(self, value):
        for x in self._switching_step_roles:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._switching_step_roles = value
            
    switching_step_roles = property(get_switching_step_roles, set_switching_step_roles)
    
    def add_switching_step_roles(self, *switching_step_roles):
        for obj in switching_step_roles:
            obj._erp_person = self
            if obj not in self._switching_step_roles:
                self._switching_step_roles.append(obj)
        
    def remove_switching_step_roles(self, *switching_step_roles):
        for obj in switching_step_roles:
            obj._erp_person = None
            self._switching_step_roles.remove(obj)
    # >>> switching_step_roles

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ 
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self.activity_records:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records

    # <<< land_property_roles
    # @generated
    def get_land_property_roles(self):
        """ 
        """
        return self._land_property_roles

    def set_land_property_roles(self, value):
        for x in self._land_property_roles:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._land_property_roles = value
            
    land_property_roles = property(get_land_property_roles, set_land_property_roles)
    
    def add_land_property_roles(self, *land_property_roles):
        for obj in land_property_roles:
            obj._erp_person = self
            if obj not in self._land_property_roles:
                self._land_property_roles.append(obj)
        
    def remove_land_property_roles(self, *land_property_roles):
        for obj in land_property_roles:
            obj._erp_person = None
            self._land_property_roles.remove(obj)
    # >>> land_property_roles

    # <<< erp_organisation_roles
    # @generated
    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._erp_person = self
            if obj not in self._erp_organisation_roles:
                self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._erp_person = None
            self._erp_organisation_roles.remove(obj)
    # >>> erp_organisation_roles

    # <<< call_backs
    # @generated
    def get_call_backs(self):
        """ 
        """
        return self._call_backs

    def set_call_backs(self, value):
        for p in self.call_backs:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._call_backs = value
            
    call_backs = property(get_call_backs, set_call_backs)
    
    def add_call_backs(self, *call_backs):
        for obj in call_backs:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._call_backs.append(obj)
        
    def remove_call_backs(self, *call_backs):
        for obj in call_backs:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._call_backs.remove(obj)
    # >>> call_backs

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._erp_person = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._erp_person = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< skills
    # @generated
    def get_skills(self):
        """ 
        """
        return self._skills

    def set_skills(self, value):
        for x in self._skills:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._skills = value
            
    skills = property(get_skills, set_skills)
    
    def add_skills(self, *skills):
        for obj in skills:
            obj._erp_person = self
            if obj not in self._skills:
                self._skills.append(obj)
        
    def remove_skills(self, *skills):
        for obj in skills:
            obj._erp_person = None
            self._skills.remove(obj)
    # >>> skills

    # <<< erp_competency
    # @generated
    def get_erp_competency(self):
        """ 
        """
        return self._erp_competency

    def set_erp_competency(self, value):
        if self._erp_competency is not None:
            filtered = [x for x in self.erp_competency.erp_persons if x != self]
            self._erp_competency._erp_persons = filtered
            
        self._erp_competency = value
        if self._erp_competency is not None:
            if self not in self._erp_competency._erp_persons:
                self._erp_competency._erp_persons.append(self)

    erp_competency = property(get_erp_competency, set_erp_competency)
    # >>> erp_competency

    # <<< erp_telephone_numbers
    # @generated
    def get_erp_telephone_numbers(self):
        """ 
        """
        return self._erp_telephone_numbers

    def set_erp_telephone_numbers(self, value):
        for p in self.erp_telephone_numbers:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._erp_telephone_numbers = value
            
    erp_telephone_numbers = property(get_erp_telephone_numbers, set_erp_telephone_numbers)
    
    def add_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._erp_telephone_numbers.append(obj)
        
    def remove_erp_telephone_numbers(self, *erp_telephone_numbers):
        for obj in erp_telephone_numbers:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._erp_telephone_numbers.remove(obj)
    # >>> erp_telephone_numbers

    # <<< customer_data
    # @generated
    def get_customer_data(self):
        """ 
        """
        return self._customer_data

    def set_customer_data(self, value):
        if self._customer_data is not None:
            filtered = [x for x in self.customer_data.erp_persons if x != self]
            self._customer_data._erp_persons = filtered
            
        self._customer_data = value
        if self._customer_data is not None:
            if self not in self._customer_data._erp_persons:
                self._customer_data._erp_persons.append(self)

    customer_data = property(get_customer_data, set_customer_data)
    # >>> customer_data

    # <<< document_roles
    # @generated
    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._erp_person = self
            if obj not in self._document_roles:
                self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._erp_person = None
            self._document_roles.remove(obj)
    # >>> document_roles

    # <<< crews
    # @generated
    def get_crews(self):
        """ All Crews to which this ErpPerson belongs.
        """
        return self._crews

    def set_crews(self, value):
        for p in self.crews:
            filtered = [q for q in p.crew_members if q != self]
            p._crew_members = filtered
        for r in value:
            if self not in r._crew_members:
                r._crew_members.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._crew_members:
                obj._crew_members.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._crew_members:
                obj._crew_members.remove(self)
            self._crews.remove(obj)
    # >>> crews

    # <<< measurement_values
    # @generated
    def get_measurement_values(self):
        """ 
        """
        return self._measurement_values

    def set_measurement_values(self, value):
        for x in self._measurement_values:
            x.erp_person = None
        for y in value:
            y.erp_person = self
        self._measurement_values = value
            
    measurement_values = property(get_measurement_values, set_measurement_values)
    
    def add_measurement_values(self, *measurement_values):
        for obj in measurement_values:
            obj._erp_person = self
            if obj not in self._measurement_values:
                self._measurement_values.append(obj)
        
    def remove_measurement_values(self, *measurement_values):
        for obj in measurement_values:
            obj._erp_person = None
            self._measurement_values.remove(obj)
    # >>> measurement_values

    # <<< appointments
    # @generated
    def get_appointments(self):
        """ 
        """
        return self._appointments

    def set_appointments(self, value):
        for p in self.appointments:
            filtered = [q for q in p.erp_persons if q != self]
            p._erp_persons = filtered
        for r in value:
            if self not in r._erp_persons:
                r._erp_persons.append(self)
        self._appointments = value
            
    appointments = property(get_appointments, set_appointments)
    
    def add_appointments(self, *appointments):
        for obj in appointments:
            if self not in obj._erp_persons:
                obj._erp_persons.append(self)
            self._appointments.append(obj)
        
    def remove_appointments(self, *appointments):
        for obj in appointments:
            if self in obj._erp_persons:
                obj._erp_persons.remove(self)
            self._appointments.remove(obj)
    # >>> appointments



class ErpQuoteLineItem(IdentifiedObject):
    """ Of an ErpQuote, the item or product quoted along with quantity, price and other descriptive information.
    """
    # <<< erp_quote_line_item
    # @generated
    def __init__(self, status=None, asset_model_catalogue_item=None, design=None, erp_quote=None, erp_invoice_line_item=None, erp_req_line_item=None, request=None, **kw_args):
        """ Initialises a new 'ErpQuoteLineItem' instance.
        """
        
        self.status = status
        self._asset_model_catalogue_item = None
        self.asset_model_catalogue_item = asset_model_catalogue_item
        self._design = None
        self.design = design
        self._erp_quote = None
        self.erp_quote = erp_quote
        self._erp_invoice_line_item = None
        self.erp_invoice_line_item = erp_invoice_line_item
        self._erp_req_line_item = None
        self.erp_req_line_item = erp_req_line_item
        self._request = None
        self.request = request

        super(ErpQuoteLineItem, self).__init__(**kw_args)
    # >>> erp_quote_line_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< asset_model_catalogue_item
    # @generated
    def get_asset_model_catalogue_item(self):
        """ 
        """
        return self._asset_model_catalogue_item

    def set_asset_model_catalogue_item(self, value):
        if self._asset_model_catalogue_item is not None:
            filtered = [x for x in self.asset_model_catalogue_item.erp_quote_line_items if x != self]
            self._asset_model_catalogue_item._erp_quote_line_items = filtered
            
        self._asset_model_catalogue_item = value
        if self._asset_model_catalogue_item is not None:
            if self not in self._asset_model_catalogue_item._erp_quote_line_items:
                self._asset_model_catalogue_item._erp_quote_line_items.append(self)

    asset_model_catalogue_item = property(get_asset_model_catalogue_item, set_asset_model_catalogue_item)
    # >>> asset_model_catalogue_item

    # <<< design
    # @generated
    def get_design(self):
        """ 
        """
        return self._design

    def set_design(self, value):
        if self._design is not None:
            self._design._erp_quote_line_item = None
            
        self._design = value
        if self._design is not None:
            self._design._erp_quote_line_item = self
            
    design = property(get_design, set_design)
    # >>> design

    # <<< erp_quote
    # @generated
    def get_erp_quote(self):
        """ 
        """
        return self._erp_quote

    def set_erp_quote(self, value):
        if self._erp_quote is not None:
            filtered = [x for x in self.erp_quote.erp_quote_line_items if x != self]
            self._erp_quote._erp_quote_line_items = filtered
            
        self._erp_quote = value
        if self._erp_quote is not None:
            if self not in self._erp_quote._erp_quote_line_items:
                self._erp_quote._erp_quote_line_items.append(self)

    erp_quote = property(get_erp_quote, set_erp_quote)
    # >>> erp_quote

    # <<< erp_invoice_line_item
    # @generated
    def get_erp_invoice_line_item(self):
        """ Some utilities provide quotes to customer for services, where the customer accepts the quote by making a payment. An invoice is required for this to occur.
        """
        return self._erp_invoice_line_item

    def set_erp_invoice_line_item(self, value):
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_quote_line_item = None
            
        self._erp_invoice_line_item = value
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_quote_line_item = self
            
    erp_invoice_line_item = property(get_erp_invoice_line_item, set_erp_invoice_line_item)
    # >>> erp_invoice_line_item

    # <<< erp_req_line_item
    # @generated
    def get_erp_req_line_item(self):
        """ 
        """
        return self._erp_req_line_item

    def set_erp_req_line_item(self, value):
        if self._erp_req_line_item is not None:
            self._erp_req_line_item._erp_quote_line_item = None
            
        self._erp_req_line_item = value
        if self._erp_req_line_item is not None:
            self._erp_req_line_item._erp_quote_line_item = self
            
    erp_req_line_item = property(get_erp_req_line_item, set_erp_req_line_item)
    # >>> erp_req_line_item

    # <<< request
    # @generated
    def get_request(self):
        """ 
        """
        return self._request

    def set_request(self, value):
        if self._request is not None:
            self._request._erp_quote_line_item = None
            
        self._request = value
        if self._request is not None:
            self._request._erp_quote_line_item = self
            
    request = property(get_request, set_request)
    # >>> request



class ErpJournalEntry(IdentifiedObject):
    """ Details of an individual entry in a journal, which is to be posted to a ledger on the posting date.
    """
    # <<< erp_journal_entry
    # @generated
    def __init__(self, posting_date_time='', amount=0.0, source_id='', transaction_date_time='', account_id='', status=None, erp_rec_line_items=None, erp_journal=None, erp_payable_line_items=None, erp_ledger_entry=None, erp_invoice_line_item=None, cost_types=None, **kw_args):
        """ Initialises a new 'ErpJournalEntry' instance.
        """
        # Date and time this entry is to be posted to the ledger. 
        self.posting_date_time = posting_date_time
        # The amount of the debit or credit for this account. 
        self.amount = amount
        # The identifer of the source for this entry. 
        self.source_id = source_id
        # Date and time journal entry was recorded. 
        self.transaction_date_time = transaction_date_time
        # Account identifier for this entry. 
        self.account_id = account_id
        
        self.status = status
        self._erp_rec_line_items = []
        if erp_rec_line_items is None:
            self.erp_rec_line_items = []
        else:
            self.erp_rec_line_items = erp_rec_line_items
        self._erp_journal = None
        self.erp_journal = erp_journal
        self._erp_payable_line_items = []
        if erp_payable_line_items is None:
            self.erp_payable_line_items = []
        else:
            self.erp_payable_line_items = erp_payable_line_items
        self._erp_ledger_entry = None
        self.erp_ledger_entry = erp_ledger_entry
        self._erp_invoice_line_item = None
        self.erp_invoice_line_item = erp_invoice_line_item
        self._cost_types = []
        if cost_types is None:
            self.cost_types = []
        else:
            self.cost_types = cost_types

        super(ErpJournalEntry, self).__init__(**kw_args)
    # >>> erp_journal_entry
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_rec_line_items
    # @generated
    def get_erp_rec_line_items(self):
        """ 
        """
        return self._erp_rec_line_items

    def set_erp_rec_line_items(self, value):
        for p in self.erp_rec_line_items:
            filtered = [q for q in p.erp_journal_entries if q != self]
            p._erp_journal_entries = filtered
        for r in value:
            if self not in r._erp_journal_entries:
                r._erp_journal_entries.append(self)
        self._erp_rec_line_items = value
            
    erp_rec_line_items = property(get_erp_rec_line_items, set_erp_rec_line_items)
    
    def add_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            if self not in obj._erp_journal_entries:
                obj._erp_journal_entries.append(self)
            self._erp_rec_line_items.append(obj)
        
    def remove_erp_rec_line_items(self, *erp_rec_line_items):
        for obj in erp_rec_line_items:
            if self in obj._erp_journal_entries:
                obj._erp_journal_entries.remove(self)
            self._erp_rec_line_items.remove(obj)
    # >>> erp_rec_line_items

    # <<< erp_journal
    # @generated
    def get_erp_journal(self):
        """ 
        """
        return self._erp_journal

    def set_erp_journal(self, value):
        if self._erp_journal is not None:
            filtered = [x for x in self.erp_journal.erp_journal_entries if x != self]
            self._erp_journal._erp_journal_entries = filtered
            
        self._erp_journal = value
        if self._erp_journal is not None:
            if self not in self._erp_journal._erp_journal_entries:
                self._erp_journal._erp_journal_entries.append(self)

    erp_journal = property(get_erp_journal, set_erp_journal)
    # >>> erp_journal

    # <<< erp_payable_line_items
    # @generated
    def get_erp_payable_line_items(self):
        """ 
        """
        return self._erp_payable_line_items

    def set_erp_payable_line_items(self, value):
        for p in self.erp_payable_line_items:
            filtered = [q for q in p.erp_journal_entries if q != self]
            p._erp_journal_entries = filtered
        for r in value:
            if self not in r._erp_journal_entries:
                r._erp_journal_entries.append(self)
        self._erp_payable_line_items = value
            
    erp_payable_line_items = property(get_erp_payable_line_items, set_erp_payable_line_items)
    
    def add_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            if self not in obj._erp_journal_entries:
                obj._erp_journal_entries.append(self)
            self._erp_payable_line_items.append(obj)
        
    def remove_erp_payable_line_items(self, *erp_payable_line_items):
        for obj in erp_payable_line_items:
            if self in obj._erp_journal_entries:
                obj._erp_journal_entries.remove(self)
            self._erp_payable_line_items.remove(obj)
    # >>> erp_payable_line_items

    # <<< erp_ledger_entry
    # @generated
    def get_erp_ledger_entry(self):
        """ 
        """
        return self._erp_ledger_entry

    def set_erp_ledger_entry(self, value):
        if self._erp_ledger_entry is not None:
            self._erp_ledger_entry._erp_jounal_entry = None
            
        self._erp_ledger_entry = value
        if self._erp_ledger_entry is not None:
            self._erp_ledger_entry._erp_jounal_entry = self
            
    erp_ledger_entry = property(get_erp_ledger_entry, set_erp_ledger_entry)
    # >>> erp_ledger_entry

    # <<< erp_invoice_line_item
    # @generated
    def get_erp_invoice_line_item(self):
        """ 
        """
        return self._erp_invoice_line_item

    def set_erp_invoice_line_item(self, value):
        if self._erp_invoice_line_item is not None:
            filtered = [x for x in self.erp_invoice_line_item.erp_journal_entries if x != self]
            self._erp_invoice_line_item._erp_journal_entries = filtered
            
        self._erp_invoice_line_item = value
        if self._erp_invoice_line_item is not None:
            if self not in self._erp_invoice_line_item._erp_journal_entries:
                self._erp_invoice_line_item._erp_journal_entries.append(self)

    erp_invoice_line_item = property(get_erp_invoice_line_item, set_erp_invoice_line_item)
    # >>> erp_invoice_line_item

    # <<< cost_types
    # @generated
    def get_cost_types(self):
        """ 
        """
        return self._cost_types

    def set_cost_types(self, value):
        for p in self.cost_types:
            filtered = [q for q in p.erp_journal_entries if q != self]
            p._erp_journal_entries = filtered
        for r in value:
            if self not in r._erp_journal_entries:
                r._erp_journal_entries.append(self)
        self._cost_types = value
            
    cost_types = property(get_cost_types, set_cost_types)
    
    def add_cost_types(self, *cost_types):
        for obj in cost_types:
            if self not in obj._erp_journal_entries:
                obj._erp_journal_entries.append(self)
            self._cost_types.append(obj)
        
    def remove_cost_types(self, *cost_types):
        for obj in cost_types:
            if self in obj._erp_journal_entries:
                obj._erp_journal_entries.remove(self)
            self._cost_types.remove(obj)
    # >>> cost_types



class ErpBomItemData(IdentifiedObject):
    """ An individual item on a bill of materials.
    """
    # <<< erp_bom_item_data
    # @generated
    def __init__(self, type_asset=None, erp_bom=None, design_location=None, **kw_args):
        """ Initialises a new 'ErpBomItemData' instance.
        """
        
        self._type_asset = None
        self.type_asset = type_asset
        self._erp_bom = None
        self.erp_bom = erp_bom
        self._design_location = None
        self.design_location = design_location

        super(ErpBomItemData, self).__init__(**kw_args)
    # >>> erp_bom_item_data
        
    # <<< type_asset
    # @generated
    def get_type_asset(self):
        """ 
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            filtered = [x for x in self.type_asset.erp_bom_item_datas if x != self]
            self._type_asset._erp_bom_item_datas = filtered
            
        self._type_asset = value
        if self._type_asset is not None:
            if self not in self._type_asset._erp_bom_item_datas:
                self._type_asset._erp_bom_item_datas.append(self)

    type_asset = property(get_type_asset, set_type_asset)
    # >>> type_asset

    # <<< erp_bom
    # @generated
    def get_erp_bom(self):
        """ 
        """
        return self._erp_bom

    def set_erp_bom(self, value):
        if self._erp_bom is not None:
            filtered = [x for x in self.erp_bom.erp_bom_item_datas if x != self]
            self._erp_bom._erp_bom_item_datas = filtered
            
        self._erp_bom = value
        if self._erp_bom is not None:
            if self not in self._erp_bom._erp_bom_item_datas:
                self._erp_bom._erp_bom_item_datas.append(self)

    erp_bom = property(get_erp_bom, set_erp_bom)
    # >>> erp_bom

    # <<< design_location
    # @generated
    def get_design_location(self):
        """ 
        """
        return self._design_location

    def set_design_location(self, value):
        if self._design_location is not None:
            filtered = [x for x in self.design_location.erp_bom_item_datas if x != self]
            self._design_location._erp_bom_item_datas = filtered
            
        self._design_location = value
        if self._design_location is not None:
            if self not in self._design_location._erp_bom_item_datas:
                self._design_location._erp_bom_item_datas.append(self)

    design_location = property(get_design_location, set_design_location)
    # >>> design_location



class ErpCompetency(IdentifiedObject):
    """ Information that describes aptitudes of a utility employee. Unlike Skills that an ErpPerson must be certified to perform before undertaking certain type of assignments (to be able to perfrom a Craft), ErpCompetency has more to do with typical Human Resource (HR) matters such as schooling, training, etc.
    """
    # <<< erp_competency
    # @generated
    def __init__(self, erp_persons=None, **kw_args):
        """ Initialises a new 'ErpCompetency' instance.
        """
        
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons

        super(ErpCompetency, self).__init__(**kw_args)
    # >>> erp_competency
        
    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for x in self._erp_persons:
            x.erp_competency = None
        for y in value:
            y.erp_competency = self
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._erp_competency = self
            if obj not in self._erp_persons:
                self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._erp_competency = None
            self._erp_persons.remove(obj)
    # >>> erp_persons



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
    # <<< erp_ledger_budget
    # @generated
    def __init__(self, erp_led_bud_line_items=None, **kw_args):
        """ Initialises a new 'ErpLedgerBudget' instance.
        """
        
        self._erp_led_bud_line_items = []
        if erp_led_bud_line_items is None:
            self.erp_led_bud_line_items = []
        else:
            self.erp_led_bud_line_items = erp_led_bud_line_items

        super(ErpLedgerBudget, self).__init__(**kw_args)
    # >>> erp_ledger_budget
        
    # <<< erp_led_bud_line_items
    # @generated
    def get_erp_led_bud_line_items(self):
        """ 
        """
        return self._erp_led_bud_line_items

    def set_erp_led_bud_line_items(self, value):
        for x in self._erp_led_bud_line_items:
            x.erp_ledger_budget = None
        for y in value:
            y.erp_ledger_budget = self
        self._erp_led_bud_line_items = value
            
    erp_led_bud_line_items = property(get_erp_led_bud_line_items, set_erp_led_bud_line_items)
    
    def add_erp_led_bud_line_items(self, *erp_led_bud_line_items):
        for obj in erp_led_bud_line_items:
            obj._erp_ledger_budget = self
            if obj not in self._erp_led_bud_line_items:
                self._erp_led_bud_line_items.append(obj)
        
    def remove_erp_led_bud_line_items(self, *erp_led_bud_line_items):
        for obj in erp_led_bud_line_items:
            obj._erp_ledger_budget = None
            self._erp_led_bud_line_items.remove(obj)
    # >>> erp_led_bud_line_items



class ErpOrganisation(Organisation):
    """ Identifies organisations that might have roles as utilities, contractors, suppliers, manufacturers, customers, etc. Organisations may also have parent-child relationships to identify departments within an organisation, or parent company relationships. The organization may be internal (e.g., departments) or external to the utility. There may be multiple organizations of a given 'category', each with a unique 'code'.
    """
    # <<< erp_organisation
    # @generated
    def __init__(self, opt_out=False, mode='', is_cost_center=False, code='', is_profit_center=False, government_id='', category='', industry_id='', registered_resources=None, parent_organisation_roles=None, location_roles=None, requests=None, erp_person_roles=None, change_items=None, child_organisation_roles=None, activity_records=None, int_sched_agreement=None, land_property_roles=None, power_system_resource_roles=None, asset_roles=None, violation_limits=None, document_roles=None, crews=None, **kw_args):
        """ Initialises a new 'ErpOrganisation' instance.
        """
        # True if organisation 'opted out', i.e., has requested that their contact information not be shared with other organisations for purposes of solicitation. 
        self.opt_out = opt_out
        # Operational mode of the organisation, often required for outage reporting purposes. Some utilities use text to describe various modes (like nominal, emergency, storm, other), while others use severity ratings (for example, 0 is a nominal condition and 5 is the most severe condition). 
        self.mode = mode
        # True if organisation is cost center. 
        self.is_cost_center = is_cost_center
        # Designated code for organisation. 
        self.code = code
        # True if organisation is profit center. 
        self.is_profit_center = is_profit_center
        # Unique identifier for organisation relative to its governing authority, for example a federal tax identifier. 
        self.government_id = government_id
        # Category by utility's corporate standards and practices. 
        self.category = category
        # Unique identifier for a given organisation (business). In the USA, this is a 'Dunns' or D&amp;B number. This identifier is typically in addition to the identifiers that organizations assign (on an internal basis) to each of their locations. Note that a unique identifier can be set up for each location of an organisation. This requirement is supported through the recursive Organisation-Organisation relationship, where each child Organisation can have a specified physical location. 
        self.industry_id = industry_id
        
        self._registered_resources = []
        if registered_resources is None:
            self.registered_resources = []
        else:
            self.registered_resources = registered_resources
        self._parent_organisation_roles = []
        if parent_organisation_roles is None:
            self.parent_organisation_roles = []
        else:
            self.parent_organisation_roles = parent_organisation_roles
        self._location_roles = []
        if location_roles is None:
            self.location_roles = []
        else:
            self.location_roles = location_roles
        self._requests = []
        if requests is None:
            self.requests = []
        else:
            self.requests = requests
        self._erp_person_roles = []
        if erp_person_roles is None:
            self.erp_person_roles = []
        else:
            self.erp_person_roles = erp_person_roles
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items
        self._child_organisation_roles = []
        if child_organisation_roles is None:
            self.child_organisation_roles = []
        else:
            self.child_organisation_roles = child_organisation_roles
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records
        self._int_sched_agreement = []
        if int_sched_agreement is None:
            self.int_sched_agreement = []
        else:
            self.int_sched_agreement = int_sched_agreement
        self._land_property_roles = []
        if land_property_roles is None:
            self.land_property_roles = []
        else:
            self.land_property_roles = land_property_roles
        self._power_system_resource_roles = []
        if power_system_resource_roles is None:
            self.power_system_resource_roles = []
        else:
            self.power_system_resource_roles = power_system_resource_roles
        self._asset_roles = []
        if asset_roles is None:
            self.asset_roles = []
        else:
            self.asset_roles = asset_roles
        self._violation_limits = []
        if violation_limits is None:
            self.violation_limits = []
        else:
            self.violation_limits = violation_limits
        self._document_roles = []
        if document_roles is None:
            self.document_roles = []
        else:
            self.document_roles = document_roles
        self._crews = []
        if crews is None:
            self.crews = []
        else:
            self.crews = crews

        super(ErpOrganisation, self).__init__(**kw_args)
    # >>> erp_organisation
        
    # <<< registered_resources
    # @generated
    def get_registered_resources(self):
        """ 
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for x in self._registered_resources:
            x.organisation = None
        for y in value:
            y.organisation = self
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._organisation = self
            if obj not in self._registered_resources:
                self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._organisation = None
            self._registered_resources.remove(obj)
    # >>> registered_resources

    # <<< parent_organisation_roles
    # @generated
    def get_parent_organisation_roles(self):
        """ 
        """
        return self._parent_organisation_roles

    def set_parent_organisation_roles(self, value):
        for x in self._parent_organisation_roles:
            x.child_organisation = None
        for y in value:
            y.child_organisation = self
        self._parent_organisation_roles = value
            
    parent_organisation_roles = property(get_parent_organisation_roles, set_parent_organisation_roles)
    
    def add_parent_organisation_roles(self, *parent_organisation_roles):
        for obj in parent_organisation_roles:
            obj._child_organisation = self
            if obj not in self._parent_organisation_roles:
                self._parent_organisation_roles.append(obj)
        
    def remove_parent_organisation_roles(self, *parent_organisation_roles):
        for obj in parent_organisation_roles:
            obj._child_organisation = None
            self._parent_organisation_roles.remove(obj)
    # >>> parent_organisation_roles

    # <<< location_roles
    # @generated
    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._erp_organisation = self
            if obj not in self._location_roles:
                self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._erp_organisation = None
            self._location_roles.remove(obj)
    # >>> location_roles

    # <<< requests
    # @generated
    def get_requests(self):
        """ 
        """
        return self._requests

    def set_requests(self, value):
        for x in self._requests:
            x.organisation = None
        for y in value:
            y.organisation = self
        self._requests = value
            
    requests = property(get_requests, set_requests)
    
    def add_requests(self, *requests):
        for obj in requests:
            obj._organisation = self
            if obj not in self._requests:
                self._requests.append(obj)
        
    def remove_requests(self, *requests):
        for obj in requests:
            obj._organisation = None
            self._requests.remove(obj)
    # >>> requests

    # <<< erp_person_roles
    # @generated
    def get_erp_person_roles(self):
        """ 
        """
        return self._erp_person_roles

    def set_erp_person_roles(self, value):
        for x in self._erp_person_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._erp_person_roles = value
            
    erp_person_roles = property(get_erp_person_roles, set_erp_person_roles)
    
    def add_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._erp_organisation = self
            if obj not in self._erp_person_roles:
                self._erp_person_roles.append(obj)
        
    def remove_erp_person_roles(self, *erp_person_roles):
        for obj in erp_person_roles:
            obj._erp_organisation = None
            self._erp_person_roles.remove(obj)
    # >>> erp_person_roles

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.organisation = None
        for y in value:
            y.organisation = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._organisation = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._organisation = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< child_organisation_roles
    # @generated
    def get_child_organisation_roles(self):
        """ 
        """
        return self._child_organisation_roles

    def set_child_organisation_roles(self, value):
        for x in self._child_organisation_roles:
            x.parent_organisation = None
        for y in value:
            y.parent_organisation = self
        self._child_organisation_roles = value
            
    child_organisation_roles = property(get_child_organisation_roles, set_child_organisation_roles)
    
    def add_child_organisation_roles(self, *child_organisation_roles):
        for obj in child_organisation_roles:
            obj._parent_organisation = self
            if obj not in self._child_organisation_roles:
                self._child_organisation_roles.append(obj)
        
    def remove_child_organisation_roles(self, *child_organisation_roles):
        for obj in child_organisation_roles:
            obj._parent_organisation = None
            self._child_organisation_roles.remove(obj)
    # >>> child_organisation_roles

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ 
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self.activity_records:
            filtered = [q for q in p.organisations if q != self]
            p._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records

    # <<< int_sched_agreement
    # @generated
    def get_int_sched_agreement(self):
        """ 
        """
        return self._int_sched_agreement

    def set_int_sched_agreement(self, value):
        for p in self.int_sched_agreement:
            filtered = [q for q in p.organisations if q != self]
            p._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._int_sched_agreement = value
            
    int_sched_agreement = property(get_int_sched_agreement, set_int_sched_agreement)
    
    def add_int_sched_agreement(self, *int_sched_agreement):
        for obj in int_sched_agreement:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._int_sched_agreement.append(obj)
        
    def remove_int_sched_agreement(self, *int_sched_agreement):
        for obj in int_sched_agreement:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._int_sched_agreement.remove(obj)
    # >>> int_sched_agreement

    # <<< land_property_roles
    # @generated
    def get_land_property_roles(self):
        """ 
        """
        return self._land_property_roles

    def set_land_property_roles(self, value):
        for x in self._land_property_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._land_property_roles = value
            
    land_property_roles = property(get_land_property_roles, set_land_property_roles)
    
    def add_land_property_roles(self, *land_property_roles):
        for obj in land_property_roles:
            obj._erp_organisation = self
            if obj not in self._land_property_roles:
                self._land_property_roles.append(obj)
        
    def remove_land_property_roles(self, *land_property_roles):
        for obj in land_property_roles:
            obj._erp_organisation = None
            self._land_property_roles.remove(obj)
    # >>> land_property_roles

    # <<< power_system_resource_roles
    # @generated
    def get_power_system_resource_roles(self):
        """ 
        """
        return self._power_system_resource_roles

    def set_power_system_resource_roles(self, value):
        for x in self._power_system_resource_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._power_system_resource_roles = value
            
    power_system_resource_roles = property(get_power_system_resource_roles, set_power_system_resource_roles)
    
    def add_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._erp_organisation = self
            if obj not in self._power_system_resource_roles:
                self._power_system_resource_roles.append(obj)
        
    def remove_power_system_resource_roles(self, *power_system_resource_roles):
        for obj in power_system_resource_roles:
            obj._erp_organisation = None
            self._power_system_resource_roles.remove(obj)
    # >>> power_system_resource_roles

    # <<< asset_roles
    # @generated
    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._erp_organisation = self
            if obj not in self._asset_roles:
                self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._erp_organisation = None
            self._asset_roles.remove(obj)
    # >>> asset_roles

    # <<< violation_limits
    # @generated
    def get_violation_limits(self):
        """ 
        """
        return self._violation_limits

    def set_violation_limits(self, value):
        for p in self.violation_limits:
            filtered = [q for q in p.organisations if q != self]
            p._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._violation_limits = value
            
    violation_limits = property(get_violation_limits, set_violation_limits)
    
    def add_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._violation_limits.append(obj)
        
    def remove_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._violation_limits.remove(obj)
    # >>> violation_limits

    # <<< document_roles
    # @generated
    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x.erp_organisation = None
        for y in value:
            y.erp_organisation = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._erp_organisation = self
            if obj not in self._document_roles:
                self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._erp_organisation = None
            self._document_roles.remove(obj)
    # >>> document_roles

    # <<< crews
    # @generated
    def get_crews(self):
        """ 
        """
        return self._crews

    def set_crews(self, value):
        for p in self.crews:
            filtered = [q for q in p.organisations if q != self]
            p._organisations = filtered
        for r in value:
            if self not in r._organisations:
                r._organisations.append(self)
        self._crews = value
            
    crews = property(get_crews, set_crews)
    
    def add_crews(self, *crews):
        for obj in crews:
            if self not in obj._organisations:
                obj._organisations.append(self)
            self._crews.append(obj)
        
    def remove_crews(self, *crews):
        for obj in crews:
            if self in obj._organisations:
                obj._organisations.remove(self)
            self._crews.remove(obj)
    # >>> crews



class ErpInvoiceLineItem(Document):
    """ An individual line item on an invoice.
    """
    # <<< erp_invoice_line_item
    # @generated
    def __init__(self, previous_amount=0.0, line_number='', account_gl='', line_amount=0.0, start='', end='', kind='other', date_gl='', line_version='', net_amount=0.0, component_erp_invoice_line_items=None, erp_payable_line_item=None, erp_rec_delv_line_item=None, customer_billing_infos=None, erp_payments=None, erp_rec_line_item=None, market_factors=None, settlements=None, erp_invoice=None, erp_quote_line_item=None, work_billing_infos=None, user_attributes=None, erp_journal_entries=None, container_erp_invoice_line_item=None, **kw_args):
        """ Initialises a new 'ErpInvoiceLineItem' instance.
        """
        # The previous line item charge amount. 
        self.previous_amount = previous_amount
        # Line item number on invoice statement. 
        self.line_number = line_number
        # GL account code, must be a valid combination. 
        self.account_gl = account_gl
        # The amount due for this line item. 
        self.line_amount = line_amount
        # The start of a bill period for the line item. 
        self.start = start
        # The end of a bill period for the line item. 
        self.end = end
        # Kind of line item. Values are: "other", "recalculation", "initial"
        self.kind = kind
        # Date line item will be posted to the General Ledger. 
        self.date_gl = date_gl
        # The version number of the bill run. 
        self.line_version = line_version
        # The net line item charge amount. 
        self.net_amount = net_amount
        
        self._component_erp_invoice_line_items = []
        if component_erp_invoice_line_items is None:
            self.component_erp_invoice_line_items = []
        else:
            self.component_erp_invoice_line_items = component_erp_invoice_line_items
        self._erp_payable_line_item = None
        self.erp_payable_line_item = erp_payable_line_item
        self._erp_rec_delv_line_item = None
        self.erp_rec_delv_line_item = erp_rec_delv_line_item
        self._customer_billing_infos = []
        if customer_billing_infos is None:
            self.customer_billing_infos = []
        else:
            self.customer_billing_infos = customer_billing_infos
        self._erp_payments = []
        if erp_payments is None:
            self.erp_payments = []
        else:
            self.erp_payments = erp_payments
        self._erp_rec_line_item = None
        self.erp_rec_line_item = erp_rec_line_item
        self._market_factors = []
        if market_factors is None:
            self.market_factors = []
        else:
            self.market_factors = market_factors
        self._settlements = []
        if settlements is None:
            self.settlements = []
        else:
            self.settlements = settlements
        self._erp_invoice = None
        self.erp_invoice = erp_invoice
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._work_billing_infos = []
        if work_billing_infos is None:
            self.work_billing_infos = []
        else:
            self.work_billing_infos = work_billing_infos
        self._user_attributes = []
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes
        self._erp_journal_entries = []
        if erp_journal_entries is None:
            self.erp_journal_entries = []
        else:
            self.erp_journal_entries = erp_journal_entries
        self._container_erp_invoice_line_item = None
        self.container_erp_invoice_line_item = container_erp_invoice_line_item

        super(ErpInvoiceLineItem, self).__init__(**kw_args)
    # >>> erp_invoice_line_item
        
    # <<< component_erp_invoice_line_items
    # @generated
    def get_component_erp_invoice_line_items(self):
        """ 
        """
        return self._component_erp_invoice_line_items

    def set_component_erp_invoice_line_items(self, value):
        for x in self._component_erp_invoice_line_items:
            x.container_erp_invoice_line_item = None
        for y in value:
            y.container_erp_invoice_line_item = self
        self._component_erp_invoice_line_items = value
            
    component_erp_invoice_line_items = property(get_component_erp_invoice_line_items, set_component_erp_invoice_line_items)
    
    def add_component_erp_invoice_line_items(self, *component_erp_invoice_line_items):
        for obj in component_erp_invoice_line_items:
            obj._container_erp_invoice_line_item = self
            if obj not in self._component_erp_invoice_line_items:
                self._component_erp_invoice_line_items.append(obj)
        
    def remove_component_erp_invoice_line_items(self, *component_erp_invoice_line_items):
        for obj in component_erp_invoice_line_items:
            obj._container_erp_invoice_line_item = None
            self._component_erp_invoice_line_items.remove(obj)
    # >>> component_erp_invoice_line_items

    # <<< erp_payable_line_item
    # @generated
    def get_erp_payable_line_item(self):
        """ 
        """
        return self._erp_payable_line_item

    def set_erp_payable_line_item(self, value):
        if self._erp_payable_line_item is not None:
            self._erp_payable_line_item._erp_invoice_line_item = None
            
        self._erp_payable_line_item = value
        if self._erp_payable_line_item is not None:
            self._erp_payable_line_item._erp_invoice_line_item = self
            
    erp_payable_line_item = property(get_erp_payable_line_item, set_erp_payable_line_item)
    # >>> erp_payable_line_item

    # <<< erp_rec_delv_line_item
    # @generated
    def get_erp_rec_delv_line_item(self):
        """ 
        """
        return self._erp_rec_delv_line_item

    def set_erp_rec_delv_line_item(self, value):
        if self._erp_rec_delv_line_item is not None:
            self._erp_rec_delv_line_item._erp_invoice_line_item = None
            
        self._erp_rec_delv_line_item = value
        if self._erp_rec_delv_line_item is not None:
            self._erp_rec_delv_line_item._erp_invoice_line_item = self
            
    erp_rec_delv_line_item = property(get_erp_rec_delv_line_item, set_erp_rec_delv_line_item)
    # >>> erp_rec_delv_line_item

    # <<< customer_billing_infos
    # @generated
    def get_customer_billing_infos(self):
        """ Customer billing for services rendered.
        """
        return self._customer_billing_infos

    def set_customer_billing_infos(self, value):
        for p in self.customer_billing_infos:
            filtered = [q for q in p.erp_invoice_line_items if q != self]
            p._erp_invoice_line_items = filtered
        for r in value:
            if self not in r._erp_invoice_line_items:
                r._erp_invoice_line_items.append(self)
        self._customer_billing_infos = value
            
    customer_billing_infos = property(get_customer_billing_infos, set_customer_billing_infos)
    
    def add_customer_billing_infos(self, *customer_billing_infos):
        for obj in customer_billing_infos:
            if self not in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.append(self)
            self._customer_billing_infos.append(obj)
        
    def remove_customer_billing_infos(self, *customer_billing_infos):
        for obj in customer_billing_infos:
            if self in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.remove(self)
            self._customer_billing_infos.remove(obj)
    # >>> customer_billing_infos

    # <<< erp_payments
    # @generated
    def get_erp_payments(self):
        """ 
        """
        return self._erp_payments

    def set_erp_payments(self, value):
        for p in self.erp_payments:
            filtered = [q for q in p.erp_invoice_line_items if q != self]
            p._erp_invoice_line_items = filtered
        for r in value:
            if self not in r._erp_invoice_line_items:
                r._erp_invoice_line_items.append(self)
        self._erp_payments = value
            
    erp_payments = property(get_erp_payments, set_erp_payments)
    
    def add_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self not in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.append(self)
            self._erp_payments.append(obj)
        
    def remove_erp_payments(self, *erp_payments):
        for obj in erp_payments:
            if self in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.remove(self)
            self._erp_payments.remove(obj)
    # >>> erp_payments

    # <<< erp_rec_line_item
    # @generated
    def get_erp_rec_line_item(self):
        """ 
        """
        return self._erp_rec_line_item

    def set_erp_rec_line_item(self, value):
        if self._erp_rec_line_item is not None:
            self._erp_rec_line_item._erp_invoice_line_item = None
            
        self._erp_rec_line_item = value
        if self._erp_rec_line_item is not None:
            self._erp_rec_line_item._erp_invoice_line_item = self
            
    erp_rec_line_item = property(get_erp_rec_line_item, set_erp_rec_line_item)
    # >>> erp_rec_line_item

    # <<< market_factors
    # @generated
    def get_market_factors(self):
        """ 
        """
        return self._market_factors

    def set_market_factors(self, value):
        for p in self.market_factors:
            filtered = [q for q in p.erp_invoices if q != self]
            p._erp_invoices = filtered
        for r in value:
            if self not in r._erp_invoices:
                r._erp_invoices.append(self)
        self._market_factors = value
            
    market_factors = property(get_market_factors, set_market_factors)
    
    def add_market_factors(self, *market_factors):
        for obj in market_factors:
            if self not in obj._erp_invoices:
                obj._erp_invoices.append(self)
            self._market_factors.append(obj)
        
    def remove_market_factors(self, *market_factors):
        for obj in market_factors:
            if self in obj._erp_invoices:
                obj._erp_invoices.remove(self)
            self._market_factors.remove(obj)
    # >>> market_factors

    # <<< settlements
    # @generated
    def get_settlements(self):
        """ 
        """
        return self._settlements

    def set_settlements(self, value):
        for p in self.settlements:
            filtered = [q for q in p.erp_invoice_line_items if q != self]
            p._erp_invoice_line_items = filtered
        for r in value:
            if self not in r._erp_invoice_line_items:
                r._erp_invoice_line_items.append(self)
        self._settlements = value
            
    settlements = property(get_settlements, set_settlements)
    
    def add_settlements(self, *settlements):
        for obj in settlements:
            if self not in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.append(self)
            self._settlements.append(obj)
        
    def remove_settlements(self, *settlements):
        for obj in settlements:
            if self in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.remove(self)
            self._settlements.remove(obj)
    # >>> settlements

    # <<< erp_invoice
    # @generated
    def get_erp_invoice(self):
        """ 
        """
        return self._erp_invoice

    def set_erp_invoice(self, value):
        if self._erp_invoice is not None:
            filtered = [x for x in self.erp_invoice.erp_invoice_line_items if x != self]
            self._erp_invoice._erp_invoice_line_items = filtered
            
        self._erp_invoice = value
        if self._erp_invoice is not None:
            if self not in self._erp_invoice._erp_invoice_line_items:
                self._erp_invoice._erp_invoice_line_items.append(self)

    erp_invoice = property(get_erp_invoice, set_erp_invoice)
    # >>> erp_invoice

    # <<< erp_quote_line_item
    # @generated
    def get_erp_quote_line_item(self):
        """ 
        """
        return self._erp_quote_line_item

    def set_erp_quote_line_item(self, value):
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._erp_invoice_line_item = None
            
        self._erp_quote_line_item = value
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._erp_invoice_line_item = self
            
    erp_quote_line_item = property(get_erp_quote_line_item, set_erp_quote_line_item)
    # >>> erp_quote_line_item

    # <<< work_billing_infos
    # @generated
    def get_work_billing_infos(self):
        """ 
        """
        return self._work_billing_infos

    def set_work_billing_infos(self, value):
        for p in self.work_billing_infos:
            filtered = [q for q in p.erp_line_items if q != self]
            p._erp_line_items = filtered
        for r in value:
            if self not in r._erp_line_items:
                r._erp_line_items.append(self)
        self._work_billing_infos = value
            
    work_billing_infos = property(get_work_billing_infos, set_work_billing_infos)
    
    def add_work_billing_infos(self, *work_billing_infos):
        for obj in work_billing_infos:
            if self not in obj._erp_line_items:
                obj._erp_line_items.append(self)
            self._work_billing_infos.append(obj)
        
    def remove_work_billing_infos(self, *work_billing_infos):
        for obj in work_billing_infos:
            if self in obj._erp_line_items:
                obj._erp_line_items.remove(self)
            self._work_billing_infos.remove(obj)
    # >>> work_billing_infos

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ 
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for p in self.user_attributes:
            filtered = [q for q in p.erp_invoice_line_items if q != self]
            p._erp_invoice_line_items = filtered
        for r in value:
            if self not in r._erp_invoice_line_items:
                r._erp_invoice_line_items.append(self)
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self not in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.append(self)
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self in obj._erp_invoice_line_items:
                obj._erp_invoice_line_items.remove(self)
            self._user_attributes.remove(obj)
    # >>> user_attributes

    # <<< erp_journal_entries
    # @generated
    def get_erp_journal_entries(self):
        """ 
        """
        return self._erp_journal_entries

    def set_erp_journal_entries(self, value):
        for x in self._erp_journal_entries:
            x.erp_invoice_line_item = None
        for y in value:
            y.erp_invoice_line_item = self
        self._erp_journal_entries = value
            
    erp_journal_entries = property(get_erp_journal_entries, set_erp_journal_entries)
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            obj._erp_invoice_line_item = self
            if obj not in self._erp_journal_entries:
                self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            obj._erp_invoice_line_item = None
            self._erp_journal_entries.remove(obj)
    # >>> erp_journal_entries

    # <<< container_erp_invoice_line_item
    # @generated
    def get_container_erp_invoice_line_item(self):
        """ 
        """
        return self._container_erp_invoice_line_item

    def set_container_erp_invoice_line_item(self, value):
        if self._container_erp_invoice_line_item is not None:
            filtered = [x for x in self.container_erp_invoice_line_item.component_erp_invoice_line_items if x != self]
            self._container_erp_invoice_line_item._component_erp_invoice_line_items = filtered
            
        self._container_erp_invoice_line_item = value
        if self._container_erp_invoice_line_item is not None:
            if self not in self._container_erp_invoice_line_item._component_erp_invoice_line_items:
                self._container_erp_invoice_line_item._component_erp_invoice_line_items.append(self)

    container_erp_invoice_line_item = property(get_container_erp_invoice_line_item, set_container_erp_invoice_line_item)
    # >>> container_erp_invoice_line_item



class DocErpPersonRole(Role):
    """ Roles played between Persons and Documents.
    """
    # <<< doc_erp_person_role
    # @generated
    def __init__(self, erp_person=None, document=None, **kw_args):
        """ Initialises a new 'DocErpPersonRole' instance.
        """
        
        self._erp_person = None
        self.erp_person = erp_person
        self._document = None
        self.document = document

        super(DocErpPersonRole, self).__init__(**kw_args)
    # >>> doc_erp_person_role
        
    # <<< erp_person
    # @generated
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.document_roles if x != self]
            self._erp_person._document_roles = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            if self not in self._erp_person._document_roles:
                self._erp_person._document_roles.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person

    # <<< document
    # @generated
    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.erp_person_roles if x != self]
            self._document._erp_person_roles = filtered
            
        self._document = value
        if self._document is not None:
            if self not in self._document._erp_person_roles:
                self._document._erp_person_roles.append(self)

    document = property(get_document, set_document)
    # >>> document



class ErpRequisition(Document):
    """ General information that applies to a utility requisition that is a request for the purchase of goods or services. Typically, a requisition leads to the creation of a purchase order to a specific supplier.
    """
    # <<< erp_requisition
    # @generated
    def __init__(self, erp_req_line_items=None, **kw_args):
        """ Initialises a new 'ErpRequisition' instance.
        """
        
        self._erp_req_line_items = []
        if erp_req_line_items is None:
            self.erp_req_line_items = []
        else:
            self.erp_req_line_items = erp_req_line_items

        super(ErpRequisition, self).__init__(**kw_args)
    # >>> erp_requisition
        
    # <<< erp_req_line_items
    # @generated
    def get_erp_req_line_items(self):
        """ 
        """
        return self._erp_req_line_items

    def set_erp_req_line_items(self, value):
        for x in self._erp_req_line_items:
            x.erp_requisition = None
        for y in value:
            y.erp_requisition = self
        self._erp_req_line_items = value
            
    erp_req_line_items = property(get_erp_req_line_items, set_erp_req_line_items)
    
    def add_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._erp_requisition = self
            if obj not in self._erp_req_line_items:
                self._erp_req_line_items.append(obj)
        
    def remove_erp_req_line_items(self, *erp_req_line_items):
        for obj in erp_req_line_items:
            obj._erp_requisition = None
            self._erp_req_line_items.remove(obj)
    # >>> erp_req_line_items



class ErpReceiveDelivery(Document):
    """ Transaction for an Organisation receiving goods or services that may be used to indicate receipt of goods in conjunction with a purchase order. A receivable is an open (unpaid) item in the Accounts Receivable ledger.
    """
    # <<< erp_receive_delivery
    # @generated
    def __init__(self, erp_rec_delv_line_items=None, **kw_args):
        """ Initialises a new 'ErpReceiveDelivery' instance.
        """
        
        self._erp_rec_delv_line_items = []
        if erp_rec_delv_line_items is None:
            self.erp_rec_delv_line_items = []
        else:
            self.erp_rec_delv_line_items = erp_rec_delv_line_items

        super(ErpReceiveDelivery, self).__init__(**kw_args)
    # >>> erp_receive_delivery
        
    # <<< erp_rec_delv_line_items
    # @generated
    def get_erp_rec_delv_line_items(self):
        """ 
        """
        return self._erp_rec_delv_line_items

    def set_erp_rec_delv_line_items(self, value):
        for x in self._erp_rec_delv_line_items:
            x.erp_receive_delivery = None
        for y in value:
            y.erp_receive_delivery = self
        self._erp_rec_delv_line_items = value
            
    erp_rec_delv_line_items = property(get_erp_rec_delv_line_items, set_erp_rec_delv_line_items)
    
    def add_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
            obj._erp_receive_delivery = self
            if obj not in self._erp_rec_delv_line_items:
                self._erp_rec_delv_line_items.append(obj)
        
    def remove_erp_rec_delv_line_items(self, *erp_rec_delv_line_items):
        for obj in erp_rec_delv_line_items:
            obj._erp_receive_delivery = None
            self._erp_rec_delv_line_items.remove(obj)
    # >>> erp_rec_delv_line_items



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
    # <<< erp_personnel
    # @generated
    def __init__(self, status=None, erp_persons=None, **kw_args):
        """ Initialises a new 'ErpPersonnel' instance.
        """
        
        self.status = status
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons

        super(ErpPersonnel, self).__init__(**kw_args)
    # >>> erp_personnel
        
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
        for x in self._erp_persons:
            x.erp_personnel = None
        for y in value:
            y.erp_personnel = self
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._erp_personnel = self
            if obj not in self._erp_persons:
                self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._erp_personnel = None
            self._erp_persons.remove(obj)
    # >>> erp_persons



class ErpRecDelvLineItem(IdentifiedObject):
    """ Of an ErpReceiveDelivery, this is an individually received good or service by the Organisation receiving goods or services. It may be used to indicate receipt of goods in conjunction with a purchase order line item.
    """
    # <<< erp_rec_delv_line_item
    # @generated
    def __init__(self, status=None, material_items=None, erp_invoice_line_item=None, erp_receive_delivery=None, erp_poline_item=None, assets=None, **kw_args):
        """ Initialises a new 'ErpRecDelvLineItem' instance.
        """
        
        self.status = status
        self._material_items = []
        if material_items is None:
            self.material_items = []
        else:
            self.material_items = material_items
        self._erp_invoice_line_item = None
        self.erp_invoice_line_item = erp_invoice_line_item
        self._erp_receive_delivery = None
        self.erp_receive_delivery = erp_receive_delivery
        self._erp_poline_item = None
        self.erp_poline_item = erp_poline_item
        self._assets = []
        if assets is None:
            self.assets = []
        else:
            self.assets = assets

        super(ErpRecDelvLineItem, self).__init__(**kw_args)
    # >>> erp_rec_delv_line_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< material_items
    # @generated
    def get_material_items(self):
        """ 
        """
        return self._material_items

    def set_material_items(self, value):
        for p in self.material_items:
            filtered = [q for q in p.erp_rec_delv_line_items if q != self]
            p._erp_rec_delv_line_items = filtered
        for r in value:
            if self not in r._erp_rec_delv_line_items:
                r._erp_rec_delv_line_items.append(self)
        self._material_items = value
            
    material_items = property(get_material_items, set_material_items)
    
    def add_material_items(self, *material_items):
        for obj in material_items:
            if self not in obj._erp_rec_delv_line_items:
                obj._erp_rec_delv_line_items.append(self)
            self._material_items.append(obj)
        
    def remove_material_items(self, *material_items):
        for obj in material_items:
            if self in obj._erp_rec_delv_line_items:
                obj._erp_rec_delv_line_items.remove(self)
            self._material_items.remove(obj)
    # >>> material_items

    # <<< erp_invoice_line_item
    # @generated
    def get_erp_invoice_line_item(self):
        """ 
        """
        return self._erp_invoice_line_item

    def set_erp_invoice_line_item(self, value):
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_rec_delv_line_item = None
            
        self._erp_invoice_line_item = value
        if self._erp_invoice_line_item is not None:
            self._erp_invoice_line_item._erp_rec_delv_line_item = self
            
    erp_invoice_line_item = property(get_erp_invoice_line_item, set_erp_invoice_line_item)
    # >>> erp_invoice_line_item

    # <<< erp_receive_delivery
    # @generated
    def get_erp_receive_delivery(self):
        """ 
        """
        return self._erp_receive_delivery

    def set_erp_receive_delivery(self, value):
        if self._erp_receive_delivery is not None:
            filtered = [x for x in self.erp_receive_delivery.erp_rec_delv_line_items if x != self]
            self._erp_receive_delivery._erp_rec_delv_line_items = filtered
            
        self._erp_receive_delivery = value
        if self._erp_receive_delivery is not None:
            if self not in self._erp_receive_delivery._erp_rec_delv_line_items:
                self._erp_receive_delivery._erp_rec_delv_line_items.append(self)

    erp_receive_delivery = property(get_erp_receive_delivery, set_erp_receive_delivery)
    # >>> erp_receive_delivery

    # <<< erp_poline_item
    # @generated
    def get_erp_poline_item(self):
        """ 
        """
        return self._erp_poline_item

    def set_erp_poline_item(self, value):
        if self._erp_poline_item is not None:
            self._erp_poline_item._erp_rec_del_line_item = None
            
        self._erp_poline_item = value
        if self._erp_poline_item is not None:
            self._erp_poline_item._erp_rec_del_line_item = self
            
    erp_poline_item = property(get_erp_poline_item, set_erp_poline_item)
    # >>> erp_poline_item

    # <<< assets
    # @generated
    def get_assets(self):
        """ 
        """
        return self._assets

    def set_assets(self, value):
        for p in self.assets:
            filtered = [q for q in p.erp_rec_delivery_items if q != self]
            p._erp_rec_delivery_items = filtered
        for r in value:
            if self not in r._erp_rec_delivery_items:
                r._erp_rec_delivery_items.append(self)
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            if self not in obj._erp_rec_delivery_items:
                obj._erp_rec_delivery_items.append(self)
            self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            if self in obj._erp_rec_delivery_items:
                obj._erp_rec_delivery_items.remove(self)
            self._assets.remove(obj)
    # >>> assets



class ErpTelephoneNumber(TelephoneNumber):
    """ The telephone number for a person or organisation.
    """
    # <<< erp_telephone_number
    # @generated
    def __init__(self, usage='', status=None, electronic_address=None, erp_persons=None, **kw_args):
        """ Initialises a new 'ErpTelephoneNumber' instance.
        """
        # The purpose of the telephone: home, mobile, home fax, office, office fax, switchboard, other. 
        self.usage = usage
        
        self.status = status
        self._electronic_address = None
        self.electronic_address = electronic_address
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons

        super(ErpTelephoneNumber, self).__init__(**kw_args)
    # >>> erp_telephone_number
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< electronic_address
    # @generated
    def get_electronic_address(self):
        """ 
        """
        return self._electronic_address

    def set_electronic_address(self, value):
        if self._electronic_address is not None:
            filtered = [x for x in self.electronic_address.erp_telephone_numbers if x != self]
            self._electronic_address._erp_telephone_numbers = filtered
            
        self._electronic_address = value
        if self._electronic_address is not None:
            if self not in self._electronic_address._erp_telephone_numbers:
                self._electronic_address._erp_telephone_numbers.append(self)

    electronic_address = property(get_electronic_address, set_electronic_address)
    # >>> electronic_address

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self.erp_persons:
            filtered = [q for q in p.erp_telephone_numbers if q != self]
            p._erp_telephone_numbers = filtered
        for r in value:
            if self not in r._erp_telephone_numbers:
                r._erp_telephone_numbers.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._erp_telephone_numbers:
                obj._erp_telephone_numbers.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._erp_telephone_numbers:
                obj._erp_telephone_numbers.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons



class ErpSiteLevelData(IdentifiedObject):
    """ For a utility, general information that describes physical locations of organizations or the location codes and their meanings. This enables ERP applications to ensure that the physical location identifiers are synchronized between the business applications.
    """
    # <<< erp_site_level_data
    # @generated
    def __init__(self, status=None, land_property=None, **kw_args):
        """ Initialises a new 'ErpSiteLevelData' instance.
        """
        
        self.status = status
        self._land_property = None
        self.land_property = land_property

        super(ErpSiteLevelData, self).__init__(**kw_args)
    # >>> erp_site_level_data
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< land_property
    # @generated
    def get_land_property(self):
        """ 
        """
        return self._land_property

    def set_land_property(self, value):
        if self._land_property is not None:
            filtered = [x for x in self.land_property.erp_site_level_datas if x != self]
            self._land_property._erp_site_level_datas = filtered
            
        self._land_property = value
        if self._land_property is not None:
            if self not in self._land_property._erp_site_level_datas:
                self._land_property._erp_site_level_datas.append(self)

    land_property = property(get_land_property, set_land_property)
    # >>> land_property



class ErpReqLineItem(IdentifiedObject):
    """ Information that describes a requested item and its attributes.
    """
    # <<< erp_req_line_item
    # @generated
    def __init__(self, quantity=0, code='', delivery_date='', cost=0.0, status=None, type_material=None, type_asset=None, erp_requisition=None, erp_quote_line_item=None, erp_poline_item=None, **kw_args):
        """ Initialises a new 'ErpReqLineItem' instance.
        """
        # Quantity of item requisitioned. 
        self.quantity = quantity
 
        self.code = code
 
        self.delivery_date = delivery_date
        # Cost of material 
        self.cost = cost
        
        self.status = status
        self._type_material = None
        self.type_material = type_material
        self._type_asset = None
        self.type_asset = type_asset
        self._erp_requisition = None
        self.erp_requisition = erp_requisition
        self._erp_quote_line_item = None
        self.erp_quote_line_item = erp_quote_line_item
        self._erp_poline_item = None
        self.erp_poline_item = erp_poline_item

        super(ErpReqLineItem, self).__init__(**kw_args)
    # >>> erp_req_line_item
        
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
            filtered = [x for x in self.type_material.erp_req_line_items if x != self]
            self._type_material._erp_req_line_items = filtered
            
        self._type_material = value
        if self._type_material is not None:
            if self not in self._type_material._erp_req_line_items:
                self._type_material._erp_req_line_items.append(self)

    type_material = property(get_type_material, set_type_material)
    # >>> type_material

    # <<< type_asset
    # @generated
    def get_type_asset(self):
        """ 
        """
        return self._type_asset

    def set_type_asset(self, value):
        if self._type_asset is not None:
            filtered = [x for x in self.type_asset.erp_req_line_items if x != self]
            self._type_asset._erp_req_line_items = filtered
            
        self._type_asset = value
        if self._type_asset is not None:
            if self not in self._type_asset._erp_req_line_items:
                self._type_asset._erp_req_line_items.append(self)

    type_asset = property(get_type_asset, set_type_asset)
    # >>> type_asset

    # <<< erp_requisition
    # @generated
    def get_erp_requisition(self):
        """ 
        """
        return self._erp_requisition

    def set_erp_requisition(self, value):
        if self._erp_requisition is not None:
            filtered = [x for x in self.erp_requisition.erp_req_line_items if x != self]
            self._erp_requisition._erp_req_line_items = filtered
            
        self._erp_requisition = value
        if self._erp_requisition is not None:
            if self not in self._erp_requisition._erp_req_line_items:
                self._erp_requisition._erp_req_line_items.append(self)

    erp_requisition = property(get_erp_requisition, set_erp_requisition)
    # >>> erp_requisition

    # <<< erp_quote_line_item
    # @generated
    def get_erp_quote_line_item(self):
        """ 
        """
        return self._erp_quote_line_item

    def set_erp_quote_line_item(self, value):
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._erp_req_line_item = None
            
        self._erp_quote_line_item = value
        if self._erp_quote_line_item is not None:
            self._erp_quote_line_item._erp_req_line_item = self
            
    erp_quote_line_item = property(get_erp_quote_line_item, set_erp_quote_line_item)
    # >>> erp_quote_line_item

    # <<< erp_poline_item
    # @generated
    def get_erp_poline_item(self):
        """ 
        """
        return self._erp_poline_item

    def set_erp_poline_item(self, value):
        if self._erp_poline_item is not None:
            self._erp_poline_item._erp_req_line_item = None
            
        self._erp_poline_item = value
        if self._erp_poline_item is not None:
            self._erp_poline_item._erp_req_line_item = self
            
    erp_poline_item = property(get_erp_poline_item, set_erp_poline_item)
    # >>> erp_poline_item



class ErpJournal(Document):
    """ Book for recording accounting transactions as they occur. Transactions and adjustments are first recorded in a journal, which is like a diary of instructions, advising which account to be charged and by how much. A journal represents a change in the balances of a business's financial accounts. Many tasks or transactions throughout an enterprise will result in the creation of a journal. Some examples are creating a customer invoice, paying a vendor, transferring inventory, or paying employees.
    """
    # <<< erp_journal
    # @generated
    def __init__(self, erp_journal_entries=None, **kw_args):
        """ Initialises a new 'ErpJournal' instance.
        """
        
        self._erp_journal_entries = []
        if erp_journal_entries is None:
            self.erp_journal_entries = []
        else:
            self.erp_journal_entries = erp_journal_entries

        super(ErpJournal, self).__init__(**kw_args)
    # >>> erp_journal
        
    # <<< erp_journal_entries
    # @generated
    def get_erp_journal_entries(self):
        """ 
        """
        return self._erp_journal_entries

    def set_erp_journal_entries(self, value):
        for x in self._erp_journal_entries:
            x.erp_journal = None
        for y in value:
            y.erp_journal = self
        self._erp_journal_entries = value
            
    erp_journal_entries = property(get_erp_journal_entries, set_erp_journal_entries)
    
    def add_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            obj._erp_journal = self
            if obj not in self._erp_journal_entries:
                self._erp_journal_entries.append(obj)
        
    def remove_erp_journal_entries(self, *erp_journal_entries):
        for obj in erp_journal_entries:
            obj._erp_journal = None
            self._erp_journal_entries.remove(obj)
    # >>> erp_journal_entries



class ErpPOLineItem(Document):
    """ Of an ErpPurchaseOrder, this is an individually ordered item or product along with the quantity, price and other descriptive information.
    """
    # <<< erp_poline_item
    # @generated
    def __init__(self, erp_rec_del_line_item=None, erp_req_line_item=None, erp_purchase_order=None, asset_model_catalogue_item=None, material_item=None, **kw_args):
        """ Initialises a new 'ErpPOLineItem' instance.
        """
        
        self._erp_rec_del_line_item = None
        self.erp_rec_del_line_item = erp_rec_del_line_item
        self._erp_req_line_item = None
        self.erp_req_line_item = erp_req_line_item
        self._erp_purchase_order = None
        self.erp_purchase_order = erp_purchase_order
        self._asset_model_catalogue_item = None
        self.asset_model_catalogue_item = asset_model_catalogue_item
        self._material_item = None
        self.material_item = material_item

        super(ErpPOLineItem, self).__init__(**kw_args)
    # >>> erp_poline_item
        
    # <<< erp_rec_del_line_item
    # @generated
    def get_erp_rec_del_line_item(self):
        """ 
        """
        return self._erp_rec_del_line_item

    def set_erp_rec_del_line_item(self, value):
        if self._erp_rec_del_line_item is not None:
            self._erp_rec_del_line_item._erp_poline_item = None
            
        self._erp_rec_del_line_item = value
        if self._erp_rec_del_line_item is not None:
            self._erp_rec_del_line_item._erp_poline_item = self
            
    erp_rec_del_line_item = property(get_erp_rec_del_line_item, set_erp_rec_del_line_item)
    # >>> erp_rec_del_line_item

    # <<< erp_req_line_item
    # @generated
    def get_erp_req_line_item(self):
        """ 
        """
        return self._erp_req_line_item

    def set_erp_req_line_item(self, value):
        if self._erp_req_line_item is not None:
            self._erp_req_line_item._erp_poline_item = None
            
        self._erp_req_line_item = value
        if self._erp_req_line_item is not None:
            self._erp_req_line_item._erp_poline_item = self
            
    erp_req_line_item = property(get_erp_req_line_item, set_erp_req_line_item)
    # >>> erp_req_line_item

    # <<< erp_purchase_order
    # @generated
    def get_erp_purchase_order(self):
        """ 
        """
        return self._erp_purchase_order

    def set_erp_purchase_order(self, value):
        if self._erp_purchase_order is not None:
            filtered = [x for x in self.erp_purchase_order.erp_poline_items if x != self]
            self._erp_purchase_order._erp_poline_items = filtered
            
        self._erp_purchase_order = value
        if self._erp_purchase_order is not None:
            if self not in self._erp_purchase_order._erp_poline_items:
                self._erp_purchase_order._erp_poline_items.append(self)

    erp_purchase_order = property(get_erp_purchase_order, set_erp_purchase_order)
    # >>> erp_purchase_order

    # <<< asset_model_catalogue_item
    # @generated
    def get_asset_model_catalogue_item(self):
        """ 
        """
        return self._asset_model_catalogue_item

    def set_asset_model_catalogue_item(self, value):
        if self._asset_model_catalogue_item is not None:
            filtered = [x for x in self.asset_model_catalogue_item.erp_poline_items if x != self]
            self._asset_model_catalogue_item._erp_poline_items = filtered
            
        self._asset_model_catalogue_item = value
        if self._asset_model_catalogue_item is not None:
            if self not in self._asset_model_catalogue_item._erp_poline_items:
                self._asset_model_catalogue_item._erp_poline_items.append(self)

    asset_model_catalogue_item = property(get_asset_model_catalogue_item, set_asset_model_catalogue_item)
    # >>> asset_model_catalogue_item

    # <<< material_item
    # @generated
    def get_material_item(self):
        """ 
        """
        return self._material_item

    def set_material_item(self, value):
        if self._material_item is not None:
            filtered = [x for x in self.material_item.erp_poline_items if x != self]
            self._material_item._erp_poline_items = filtered
            
        self._material_item = value
        if self._material_item is not None:
            if self not in self._material_item._erp_poline_items:
                self._material_item._erp_poline_items.append(self)

    material_item = property(get_material_item, set_material_item)
    # >>> material_item



# <<< inf_erpsupport
# @generated
# >>> inf_erpsupport

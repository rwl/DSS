# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14 import Element
from cim14.iec61968.common import Agreement
from cim14.iec61968.common import Organisation
from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.PaymentMetering"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.PaymentMetering"

class PointOfSale(IdentifiedObject):
    """ Logical point where transactions take place with operational interaction between Cashier and the payment system; in certain cases PointOfSale interacts directly with the end customer, in which case Cashier might not be a real person: for example a self-service kiosk or over the internet.
    """
    # Local description for where this pont of sale is physically located. 
    location = ''

    def get_cashier_shifts(self):
        """ All shifts this point of sale operated in.
        """
        return self._cashier_shifts

    def set_cashier_shifts(self, value):
        for x in self._cashier_shifts:
            x._point_of_sale = None
        for y in value:
            y._point_of_sale = self
        self._cashier_shifts = value
            
    cashier_shifts = property(get_cashier_shifts, set_cashier_shifts)
    
    def add_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._point_of_sale = self
            self._cashier_shifts.append(obj)
        
    def remove_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._point_of_sale = None
            self._cashier_shifts.remove(obj)

    def get_tokens(self):
        """ All Tokens sold or dispensed at this PointOfSale.
        """
        return self._tokens

    def set_tokens(self, value):
        for x in self._tokens:
            x._point_of_sale = None
        for y in value:
            y._point_of_sale = self
        self._tokens = value
            
    tokens = property(get_tokens, set_tokens)
    
    def add_tokens(self, *tokens):
        for obj in tokens:
            obj._point_of_sale = self
            self._tokens.append(obj)
        
    def remove_tokens(self, *tokens):
        for obj in tokens:
            obj._point_of_sale = None
            self._tokens.remove(obj)

    def get_vendor(self):
        """ Vendor that controls this PointOfSale.
        """
        return self._vendor

    def set_vendor(self, value):
        if self._vendor is not None:
            filtered = [x for x in self.vendor.point_of_sales if x != self]
            self._vendor._point_of_sales = filtered
            
        self._vendor = value
        if self._vendor is not None:
            self._vendor._point_of_sales.append(self)

    vendor = property(get_vendor, set_vendor)

    # <<< point_of_sale
    # @generated
    def __init__(self, location='', cashier_shifts=[], tokens=[], vendor=None, **kw_args):
        """ Initialises a new 'PointOfSale' instance.
        """
        self.location = location
        self._cashier_shifts = []
        self.cashier_shifts = cashier_shifts
        self._tokens = []
        self.tokens = tokens
        self._vendor = None
        self.vendor = vendor

        super(PointOfSale, self).__init__(**kw_args)
    # >>> point_of_sale


class Cheque(Element):
    """ The actual tender when it is a type of cheque.
    """
    # The magnetic ink character recognition number printed on the cheque. 
    micr_number = ''

    # Cheque reference number as printed on the cheque. 
    cheque_number = ''

    # Kind of cheque. Values are: "postal_order", "other", "bank_order"
    kind = 'postal_order'

    # Date when cheque becomes valid. 
    date = ''

    # Details of the account holder and bank.
    bank_account_detail = None

    def get_tender(self):
        """ Payment tender the cheque is being used for.
        """
        return self._tender

    def set_tender(self, value):
        if self._tender is not None:
            self._tender._cheque = None
            
        self._tender = value
        if self._tender is not None:
            self._tender._cheque = self
            
    tender = property(get_tender, set_tender)

    # <<< cheque
    # @generated
    def __init__(self, micr_number='', cheque_number='', kind='postal_order', date='', bank_account_detail=None, tender=None, **kw_args):
        """ Initialises a new 'Cheque' instance.
        """
        self.micr_number = micr_number
        self.cheque_number = cheque_number
        self.kind = kind
        self.date = date
        self.bank_account_detail = bank_account_detail
        self._tender = None
        self.tender = tender

        super(Cheque, self).__init__(**kw_args)
    # >>> cheque


class Vendor(IdentifiedObject):
    """ The entity that owns PointOfSale and contracts with Cashier to receipt payments and vend tokens using the payment system. Vendor has a private contract with and is managed by Merchant who is a type of Organisation. Vendor is accountable to Merchant for revenue collected, who is in turn accountable to Supplier.
    """
    def get_merchant_account(self):
        """ Merchant account against which this vendor sells tokens or recept payments.
        """
        return self._merchant_account

    def set_merchant_account(self, value):
        if self._merchant_account is not None:
            filtered = [x for x in self.merchant_account.vendors if x != self]
            self._merchant_account._vendors = filtered
            
        self._merchant_account = value
        if self._merchant_account is not None:
            self._merchant_account._vendors.append(self)

    merchant_account = property(get_merchant_account, set_merchant_account)

    def get_cashiers(self):
        """ All Cachiers managed by this Vendor.
        """
        return self._cashiers

    def set_cashiers(self, value):
        for x in self._cashiers:
            x._vendor = None
        for y in value:
            y._vendor = self
        self._cashiers = value
            
    cashiers = property(get_cashiers, set_cashiers)
    
    def add_cashiers(self, *cashiers):
        for obj in cashiers:
            obj._vendor = self
            self._cashiers.append(obj)
        
    def remove_cashiers(self, *cashiers):
        for obj in cashiers:
            obj._vendor = None
            self._cashiers.remove(obj)

    def get_bank_statements(self):
        """ All BankStatements reflecting deposits made by this Vendor.
        """
        return self._bank_statements

    def set_bank_statements(self, value):
        for x in self._bank_statements:
            x._vendor = None
        for y in value:
            y._vendor = self
        self._bank_statements = value
            
    bank_statements = property(get_bank_statements, set_bank_statements)
    
    def add_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._vendor = self
            self._bank_statements.append(obj)
        
    def remove_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._vendor = None
            self._bank_statements.remove(obj)

    def get_point_of_sales(self):
        """ All points of sale this Vendor controls.
        """
        return self._point_of_sales

    def set_point_of_sales(self, value):
        for x in self._point_of_sales:
            x._vendor = None
        for y in value:
            y._vendor = self
        self._point_of_sales = value
            
    point_of_sales = property(get_point_of_sales, set_point_of_sales)
    
    def add_point_of_sales(self, *point_of_sales):
        for obj in point_of_sales:
            obj._vendor = self
            self._point_of_sales.append(obj)
        
    def remove_point_of_sales(self, *point_of_sales):
        for obj in point_of_sales:
            obj._vendor = None
            self._point_of_sales.remove(obj)

    def get_vendor_shifts(self):
        """ All vendor shifts opened and owned by this vendor.
        """
        return self._vendor_shifts

    def set_vendor_shifts(self, value):
        for x in self._vendor_shifts:
            x._vendor = None
        for y in value:
            y._vendor = self
        self._vendor_shifts = value
            
    vendor_shifts = property(get_vendor_shifts, set_vendor_shifts)
    
    def add_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._vendor = self
            self._vendor_shifts.append(obj)
        
    def remove_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._vendor = None
            self._vendor_shifts.remove(obj)

    # <<< vendor
    # @generated
    def __init__(self, merchant_account=None, cashiers=[], bank_statements=[], point_of_sales=[], vendor_shifts=[], **kw_args):
        """ Initialises a new 'Vendor' instance.
        """
        self._merchant_account = None
        self.merchant_account = merchant_account
        self._cashiers = []
        self.cashiers = cashiers
        self._bank_statements = []
        self.bank_statements = bank_statements
        self._point_of_sales = []
        self.point_of_sales = point_of_sales
        self._vendor_shifts = []
        self.vendor_shifts = vendor_shifts

        super(Vendor, self).__init__(**kw_args)
    # >>> vendor


class Transaction(IdentifiedObject):
    """ The record of details of payment for service or token sale.
    """
    # Number of service units not reflected in 'serviceUnitsEnergy' due to process rounding or truncating errors. 
    service_units_error = ''

    # Kind of transaction. Values are: "token_sale_payment", "token_free_issue", "transaction_reversal", "other", "token_exchange", "token_grant", "token_cancellation", "auxiliary_charge_payment", "account_payment", "diverse_payment", "service_charge_payment", "tax_charge_payment", "meter_configuration_token"
    kind = 'token_sale_payment'

    # Reference to the entity that is the source of 'amount' (for example: customer for token purchase; or supplier for free issue token). 
    donor_reference = ''

    # Actual amount of service units that is being paid for. 
    service_units_energy = ''

    # Formal reference for use with diverse payment (traffic fine for example). 
    diverse_reference = ''

    # Reference to the entity that is the recipient of 'amount' (for example, supplier for service charge payment; or tax receiver for VAT). 
    receiver_reference = ''

    # (if 'kind' is transactionReversal) Reference to the original transaction that is being reversed by this transaction. 
    reversed_id = ''

    # Transaction amount, rounding, date and note for this transaction line.
    line = None

    def get_cashier_shift(self):
        """ Cashier shift during which this transaction was recorded.
        """
        return self._cashier_shift

    def set_cashier_shift(self, value):
        if self._cashier_shift is not None:
            filtered = [x for x in self.cashier_shift.transactions if x != self]
            self._cashier_shift._transactions = filtered
            
        self._cashier_shift = value
        if self._cashier_shift is not None:
            self._cashier_shift._transactions.append(self)

    cashier_shift = property(get_cashier_shift, set_cashier_shift)

    def get_vendor_shift(self):
        """ Vendor shift during which this transaction was recorded.
        """
        return self._vendor_shift

    def set_vendor_shift(self, value):
        if self._vendor_shift is not None:
            filtered = [x for x in self.vendor_shift.transactions if x != self]
            self._vendor_shift._transactions = filtered
            
        self._vendor_shift = value
        if self._vendor_shift is not None:
            self._vendor_shift._transactions.append(self)

    vendor_shift = property(get_vendor_shift, set_vendor_shift)

    def get_auxiliary_account(self):
        """ Auxiliary account for this payment transaction.
        """
        return self._auxiliary_account

    def set_auxiliary_account(self, value):
        if self._auxiliary_account is not None:
            filtered = [x for x in self.auxiliary_account.payment_transactions if x != self]
            self._auxiliary_account._payment_transactions = filtered
            
        self._auxiliary_account = value
        if self._auxiliary_account is not None:
            self._auxiliary_account._payment_transactions.append(self)

    auxiliary_account = property(get_auxiliary_account, set_auxiliary_account)

    def get_customer_account(self):
        """ Customer account for this payment transaction.
        """
        return self._customer_account

    def set_customer_account(self, value):
        if self._customer_account is not None:
            filtered = [x for x in self.customer_account.payment_transactions if x != self]
            self._customer_account._payment_transactions = filtered
            
        self._customer_account = value
        if self._customer_account is not None:
            self._customer_account._payment_transactions.append(self)

    customer_account = property(get_customer_account, set_customer_account)

    def get_pricing_structure(self):
        """ Pricing structure applicable for this transaction.
        """
        return self._pricing_structure

    def set_pricing_structure(self, value):
        if self._pricing_structure is not None:
            filtered = [x for x in self.pricing_structure.transactions if x != self]
            self._pricing_structure._transactions = filtered
            
        self._pricing_structure = value
        if self._pricing_structure is not None:
            self._pricing_structure._transactions.append(self)

    pricing_structure = property(get_pricing_structure, set_pricing_structure)

    def get_receipt(self):
        """ The receipted payment for which this transaction has been recorded.
        """
        return self._receipt

    def set_receipt(self, value):
        if self._receipt is not None:
            filtered = [x for x in self.receipt.transactions if x != self]
            self._receipt._transactions = filtered
            
        self._receipt = value
        if self._receipt is not None:
            self._receipt._transactions.append(self)

    receipt = property(get_receipt, set_receipt)

    def get_user_attributes(self):
        """ All snapshots of meter parameters recorded at the time of this transaction. Use 'name' and 'value.value' attributes to specify name and value of a parameter from meter.
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for x in self._user_attributes:
            x._transaction = None
        for y in value:
            y._transaction = self
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            obj._transaction = self
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            obj._transaction = None
            self._user_attributes.remove(obj)

    def get_meter_asset(self):
        """ Meter asset for this vending transaction.
        """
        return self._meter_asset

    def set_meter_asset(self, value):
        if self._meter_asset is not None:
            filtered = [x for x in self.meter_asset.vending_transactions if x != self]
            self._meter_asset._vending_transactions = filtered
            
        self._meter_asset = value
        if self._meter_asset is not None:
            self._meter_asset._vending_transactions.append(self)

    meter_asset = property(get_meter_asset, set_meter_asset)

    # <<< transaction
    # @generated
    def __init__(self, service_units_error='', kind='token_sale_payment', donor_reference='', service_units_energy='', diverse_reference='', receiver_reference='', reversed_id='', line=None, cashier_shift=None, vendor_shift=None, auxiliary_account=None, customer_account=None, pricing_structure=None, receipt=None, user_attributes=[], meter_asset=None, **kw_args):
        """ Initialises a new 'Transaction' instance.
        """
        self.service_units_error = service_units_error
        self.kind = kind
        self.donor_reference = donor_reference
        self.service_units_energy = service_units_energy
        self.diverse_reference = diverse_reference
        self.receiver_reference = receiver_reference
        self.reversed_id = reversed_id
        self.line = line
        self._cashier_shift = None
        self.cashier_shift = cashier_shift
        self._vendor_shift = None
        self.vendor_shift = vendor_shift
        self._auxiliary_account = None
        self.auxiliary_account = auxiliary_account
        self._customer_account = None
        self.customer_account = customer_account
        self._pricing_structure = None
        self.pricing_structure = pricing_structure
        self._receipt = None
        self.receipt = receipt
        self._user_attributes = []
        self.user_attributes = user_attributes
        self._meter_asset = None
        self.meter_asset = meter_asset

        super(Transaction, self).__init__(**kw_args)
    # >>> transaction


class ConsumptionTariffInterval(Element):
    """ One of a sequence of intervals defined in terms of consumption quantity of a service such as electricity, water, gas, etc. It is typically used in association with TariffProfile to define the steps or blocks in a step tariff structure, where startValue simultaneously defines the entry value of this step and the closing value of the previous step. Where consumption is &gt;= startValue it falls within this interval and where consumption is &lt; startValue it falls within the previous interval.
    """
    # The lowest level of consumption that defines the starting point of this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
    start_value = ''

    # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
    sequence_number = 0

    charges = []
    
    def add_charges(self, *charges):
        for obj in charges:
	        self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
	        self._charges.remove(obj)

    tariff_profiles = []
    
    def add_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.append(obj)
        
    def remove_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.remove(obj)

    # <<< consumption_tariff_interval
    # @generated
    def __init__(self, start_value='', sequence_number=0, charges=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'ConsumptionTariffInterval' instance.
        """
        self.start_value = start_value
        self.sequence_number = sequence_number
        self._charges = []
        self.charges = charges
        self._tariff_profiles = []
        self.tariff_profiles = tariff_profiles

        super(ConsumptionTariffInterval, self).__init__(**kw_args)
    # >>> consumption_tariff_interval


class BankAccountDetail(Element):
    """ Details of a bank account.
    """
    # Operational account reference number. 
    account_number = ''

    # Name of bank where account is held. 
    bank_name = ''

    # National identity number (or equivalent) of account holder. 
    holder_id = ''

    # Branch of bank where account is held. 
    branch_code = ''

    # Name of account holder. 
    holder_name = ''

    # <<< bank_account_detail
    # @generated
    def __init__(self, account_number='', bank_name='', holder_id='', branch_code='', holder_name='', **kw_args):
        """ Initialises a new 'BankAccountDetail' instance.
        """
        self.account_number = account_number
        self.bank_name = bank_name
        self.holder_id = holder_id
        self.branch_code = branch_code
        self.holder_name = holder_name

        super(BankAccountDetail, self).__init__(**kw_args)
    # >>> bank_account_detail


class Cashier(IdentifiedObject):
    """ The operator of the point of sale for the duration of CashierShift. Cashier is under the exclusive management control of Vendor.
    """
    def get_cashier_shifts(self):
        """ All shifts operated by this cashier.
        """
        return self._cashier_shifts

    def set_cashier_shifts(self, value):
        for x in self._cashier_shifts:
            x._cashier = None
        for y in value:
            y._cashier = self
        self._cashier_shifts = value
            
    cashier_shifts = property(get_cashier_shifts, set_cashier_shifts)
    
    def add_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._cashier = self
            self._cashier_shifts.append(obj)
        
    def remove_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._cashier = None
            self._cashier_shifts.remove(obj)

    def get_vendor(self):
        """ Vendor that manages this Cachier.
        """
        return self._vendor

    def set_vendor(self, value):
        if self._vendor is not None:
            filtered = [x for x in self.vendor.cashiers if x != self]
            self._vendor._cashiers = filtered
            
        self._vendor = value
        if self._vendor is not None:
            self._vendor._cashiers.append(self)

    vendor = property(get_vendor, set_vendor)

    def get_electronic_addresses(self):
        """ 
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x._cashier = None
        for y in value:
            y._cashier = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._cashier = self
            self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._cashier = None
            self._electronic_addresses.remove(obj)

    # <<< cashier
    # @generated
    def __init__(self, cashier_shifts=[], vendor=None, electronic_addresses=[], **kw_args):
        """ Initialises a new 'Cashier' instance.
        """
        self._cashier_shifts = []
        self.cashier_shifts = cashier_shifts
        self._vendor = None
        self.vendor = vendor
        self._electronic_addresses = []
        self.electronic_addresses = electronic_addresses

        super(Cashier, self).__init__(**kw_args)
    # >>> cashier


class MerchantAgreement(Agreement):
    """ A formal controlling contractual agreement between Supplier and Merchant, in terms of which Merchant is authorised to vend tokens and receipt payments on behalf of Supplier. Merchant is accountable to Supplier for revenue collected at PointOfSale.
    """
    def get_merchant_accounts(self):
        """ All merchant accounts instantiated as a result of this merchant agreement.
        """
        return self._merchant_accounts

    def set_merchant_accounts(self, value):
        for x in self._merchant_accounts:
            x._merchant_agreement = None
        for y in value:
            y._merchant_agreement = self
        self._merchant_accounts = value
            
    merchant_accounts = property(get_merchant_accounts, set_merchant_accounts)
    
    def add_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            obj._merchant_agreement = self
            self._merchant_accounts.append(obj)
        
    def remove_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            obj._merchant_agreement = None
            self._merchant_accounts.remove(obj)

    # <<< merchant_agreement
    # @generated
    def __init__(self, merchant_accounts=[], **kw_args):
        """ Initialises a new 'MerchantAgreement' instance.
        """
        self._merchant_accounts = []
        self.merchant_accounts = merchant_accounts

        super(MerchantAgreement, self).__init__(**kw_args)
    # >>> merchant_agreement


class TimeTariffInterval(Element):
    """ One of a sequence of time intervals defined in terms of real time. It is typically used in association with TariffProfile to define the intervals in a time of use tariff structure, where startDateTime simultaneously determines the starting point of this interval and the ending point of the previous interval.
    """
    # A reatime marker that defines the starting time (typically it is the time of day) for this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
    start_date_time = ''

    # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
    sequence_number = 0

    charges = []
    
    def add_charges(self, *charges):
        for obj in charges:
	        self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
	        self._charges.remove(obj)

    tariff_profiles = []
    
    def add_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.append(obj)
        
    def remove_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.remove(obj)

    # <<< time_tariff_interval
    # @generated
    def __init__(self, start_date_time='', sequence_number=0, charges=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'TimeTariffInterval' instance.
        """
        self.start_date_time = start_date_time
        self.sequence_number = sequence_number
        self._charges = []
        self.charges = charges
        self._tariff_profiles = []
        self.tariff_profiles = tariff_profiles

        super(TimeTariffInterval, self).__init__(**kw_args)
    # >>> time_tariff_interval


class Transactor(IdentifiedObject):
    """ The entity that ultimately executes the transaction and who is in control of the process; typically this is embodied in secure software running on a server that may employ secure hardware encryption devices for secure transaction processing.
    """
    merchant_accounts = []
    
    def add_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
	        self._merchant_accounts.append(obj)
        
    def remove_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
	        self._merchant_accounts.remove(obj)

    # <<< transactor
    # @generated
    def __init__(self, merchant_accounts=[], **kw_args):
        """ Initialises a new 'Transactor' instance.
        """
        self._merchant_accounts = []
        self.merchant_accounts = merchant_accounts

        super(Transactor, self).__init__(**kw_args)
    # >>> transactor


class ServiceSupplier(Organisation):
    """ Organisation that provides services to Customers.
    """
    # Kind of supplier. Values are: "retailer", "other", "utility"
    kind = 'retailer'

    # Unique transaction reference prefix number issued to an entity by the International Standards Organisation for the purpose of tagging onto electronic financial transactions, as defined in ISO/IEC 7812-1 and ISO/IEC 7812-2. 
    issuer_identification_number = ''

    def get_service_delivery_points(self):
        """ All service delivery points this service supplier utilises to deliver a service.
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x._service_supplier = None
        for y in value:
            y._service_supplier = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_supplier = self
            self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_supplier = None
            self._service_delivery_points.remove(obj)

    def get_bank_accounts(self):
        """ All BackAccounts this ServiceSupplier owns.
        """
        return self._bank_accounts

    def set_bank_accounts(self, value):
        for x in self._bank_accounts:
            x._service_supplier = None
        for y in value:
            y._service_supplier = self
        self._bank_accounts = value
            
    bank_accounts = property(get_bank_accounts, set_bank_accounts)
    
    def add_bank_accounts(self, *bank_accounts):
        for obj in bank_accounts:
            obj._service_supplier = self
            self._bank_accounts.append(obj)
        
    def remove_bank_accounts(self, *bank_accounts):
        for obj in bank_accounts:
            obj._service_supplier = None
            self._bank_accounts.remove(obj)

    def get_customer_agreements(self):
        """ All customer agreements of this service supplier.
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x._service_supplier = None
        for y in value:
            y._service_supplier = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_supplier = self
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_supplier = None
            self._customer_agreements.remove(obj)

    # <<< service_supplier
    # @generated
    def __init__(self, kind='retailer', issuer_identification_number='', service_delivery_points=[], bank_accounts=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'ServiceSupplier' instance.
        """
        self.kind = kind
        self.issuer_identification_number = issuer_identification_number
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._bank_accounts = []
        self.bank_accounts = bank_accounts
        self._customer_agreements = []
        self.customer_agreements = customer_agreements

        super(ServiceSupplier, self).__init__(**kw_args)
    # >>> service_supplier


class Receipt(IdentifiedObject):
    """ Record of total receipted payment from customer.
    """
    # True if this receipted payment is manually bankable, otherwise it is an electronic funds transfer. 
    is_bankable = False

    # Receipted amount with rounding, date and note.
    line = None

    def get_transactions(self):
        """ All transactions recorded for this receipted payment.
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x._receipt = None
        for y in value:
            y._receipt = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._receipt = self
            self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._receipt = None
            self._transactions.remove(obj)

    def get_cashier_shift(self):
        """ Cashier shift during which this receipt was recorded.
        """
        return self._cashier_shift

    def set_cashier_shift(self, value):
        if self._cashier_shift is not None:
            filtered = [x for x in self.cashier_shift.receipts if x != self]
            self._cashier_shift._receipts = filtered
            
        self._cashier_shift = value
        if self._cashier_shift is not None:
            self._cashier_shift._receipts.append(self)

    cashier_shift = property(get_cashier_shift, set_cashier_shift)

    def get_vendor_shift(self):
        """ Vendor shift during which this receipt was recorded.
        """
        return self._vendor_shift

    def set_vendor_shift(self, value):
        if self._vendor_shift is not None:
            filtered = [x for x in self.vendor_shift.receipts if x != self]
            self._vendor_shift._receipts = filtered
            
        self._vendor_shift = value
        if self._vendor_shift is not None:
            self._vendor_shift._receipts.append(self)

    vendor_shift = property(get_vendor_shift, set_vendor_shift)

    def get_tenders(self):
        """ All payments received in the form of tenders recorded by this receipt.
        """
        return self._tenders

    def set_tenders(self, value):
        for x in self._tenders:
            x._receipt = None
        for y in value:
            y._receipt = self
        self._tenders = value
            
    tenders = property(get_tenders, set_tenders)
    
    def add_tenders(self, *tenders):
        for obj in tenders:
            obj._receipt = self
            self._tenders.append(obj)
        
    def remove_tenders(self, *tenders):
        for obj in tenders:
            obj._receipt = None
            self._tenders.remove(obj)

    # <<< receipt
    # @generated
    def __init__(self, is_bankable=False, line=None, transactions=[], cashier_shift=None, vendor_shift=None, tenders=[], **kw_args):
        """ Initialises a new 'Receipt' instance.
        """
        self.is_bankable = is_bankable
        self.line = line
        self._transactions = []
        self.transactions = transactions
        self._cashier_shift = None
        self.cashier_shift = cashier_shift
        self._vendor_shift = None
        self.vendor_shift = vendor_shift
        self._tenders = []
        self.tenders = tenders

        super(Receipt, self).__init__(**kw_args)
    # >>> receipt


class Due(Element):
    """ Details on amounts due for an account.
    """
    # Part of 'current' that constitutes the arrears portion. 
    arrears = ''

    # Current total amount now due: current = principle + arrears + interest + charges. Typically the rule for settlement priority is: interest dues, then arrears dues, then current dues, then charge dues. 
    current = ''

    # Part of 'current' that constitutes the portion of the principle amount currently due. 
    principle = ''

    # Part of 'current' that constitutes the charge portion: 'charges' = 'Charge.fixedPortion' + 'Charge.variablePortion'. 
    charges = ''

    # Part of 'current' that constitutes the interest portion. 
    interest = ''

    # <<< due
    # @generated
    def __init__(self, arrears='', current='', principle='', charges='', interest='', **kw_args):
        """ Initialises a new 'Due' instance.
        """
        self.arrears = arrears
        self.current = current
        self.principle = principle
        self.charges = charges
        self.interest = interest

        super(Due, self).__init__(**kw_args)
    # >>> due


class Charge(IdentifiedObject):
    """ A charge element associated with other entities such as tariff structures, auxiliary agreements or other charge elements. The total charge amount applicable to this instance of Charge is the sum of fixedPortion plus percentagePortion.
    """
    # The kind of charge to be applied. Values are: "consumption_charge", "auxiliary_charge", "other", "tax_charge", "demand_charge"
    kind = 'consumption_charge'

    # The variable portion of this charge element, calculated as a percentage of the total amount of a parent charge. 
    variable_portion = ''

    # The fixed portion of this charge element.
    fixed_portion = None

    time_tariff_intervals = []
    
    def add_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
	        self._time_tariff_intervals.append(obj)
        
    def remove_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
	        self._time_tariff_intervals.remove(obj)

    def get_parent_charge(self):
        """ 
        """
        return self._parent_charge

    def set_parent_charge(self, value):
        if self._parent_charge is not None:
            filtered = [x for x in self.parent_charge.child_charges if x != self]
            self._parent_charge._child_charges = filtered
            
        self._parent_charge = value
        if self._parent_charge is not None:
            self._parent_charge._child_charges.append(self)

    parent_charge = property(get_parent_charge, set_parent_charge)

    auxiliary_accounts = []
    
    def add_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
	        self._auxiliary_accounts.append(obj)
        
    def remove_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
	        self._auxiliary_accounts.remove(obj)

    def get_child_charges(self):
        """ All sub-components of this complex charge.
        """
        return self._child_charges

    def set_child_charges(self, value):
        for x in self._child_charges:
            x._parent_charge = None
        for y in value:
            y._parent_charge = self
        self._child_charges = value
            
    child_charges = property(get_child_charges, set_child_charges)
    
    def add_child_charges(self, *child_charges):
        for obj in child_charges:
            obj._parent_charge = self
            self._child_charges.append(obj)
        
    def remove_child_charges(self, *child_charges):
        for obj in child_charges:
            obj._parent_charge = None
            self._child_charges.remove(obj)

    consumption_tariff_intervals = []
    
    def add_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
	        self._consumption_tariff_intervals.append(obj)
        
    def remove_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
	        self._consumption_tariff_intervals.remove(obj)

    # <<< charge
    # @generated
    def __init__(self, kind='consumption_charge', variable_portion='', fixed_portion=None, time_tariff_intervals=[], parent_charge=None, auxiliary_accounts=[], child_charges=[], consumption_tariff_intervals=[], **kw_args):
        """ Initialises a new 'Charge' instance.
        """
        self.kind = kind
        self.variable_portion = variable_portion
        self.fixed_portion = fixed_portion
        self._time_tariff_intervals = []
        self.time_tariff_intervals = time_tariff_intervals
        self._parent_charge = None
        self.parent_charge = parent_charge
        self._auxiliary_accounts = []
        self.auxiliary_accounts = auxiliary_accounts
        self._child_charges = []
        self.child_charges = child_charges
        self._consumption_tariff_intervals = []
        self.consumption_tariff_intervals = consumption_tariff_intervals

        super(Charge, self).__init__(**kw_args)
    # >>> charge


class TariffProfile(Document):
    """ A schedule of charges; structure associated with Tariff that allows the definition of complex tarif structures such as step and time of use when used in conjunction with TimeTariffInterval and Charge. Inherited 'status.value' is defined in the context of the utility's business rules, for example: active, inactive, etc.
    """
    # The frequency at which the tariff charge schedule is repeated Examples are: once off on a specified date and time; hourly; daily; weekly; monthly; 3-monthly; 6-monthly; 12-monthly; etc. At the end of each cycle, the business rules are reset to start from the beginning again. 
    tariff_cycle = ''

    tariffs = []
    
    def add_tariffs(self, *tariffs):
        for obj in tariffs:
	        self._tariffs.append(obj)
        
    def remove_tariffs(self, *tariffs):
        for obj in tariffs:
	        self._tariffs.remove(obj)

    consumption_tariff_intervals = []
    
    def add_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
	        self._consumption_tariff_intervals.append(obj)
        
    def remove_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
	        self._consumption_tariff_intervals.remove(obj)

    time_tariff_intervals = []
    
    def add_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
	        self._time_tariff_intervals.append(obj)
        
    def remove_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
	        self._time_tariff_intervals.remove(obj)

    # <<< tariff_profile
    # @generated
    def __init__(self, tariff_cycle='', tariffs=[], consumption_tariff_intervals=[], time_tariff_intervals=[], **kw_args):
        """ Initialises a new 'TariffProfile' instance.
        """
        self.tariff_cycle = tariff_cycle
        self._tariffs = []
        self.tariffs = tariffs
        self._consumption_tariff_intervals = []
        self.consumption_tariff_intervals = consumption_tariff_intervals
        self._time_tariff_intervals = []
        self.time_tariff_intervals = time_tariff_intervals

        super(TariffProfile, self).__init__(**kw_args)
    # >>> tariff_profile


class MerchantAccount(Document):
    """ The operating account controlled by MerchantAgreement, against which Vendor may vend tokens or receipt payments. Transactions via VendorShift debit the account and bank deposits via BankStatement credit the account.
    """
    # The current operating balance of this account. 
    current_balance = ''

    # The balance of this account after taking into account any pending debits from VendorShift.merchantDebitAmount and pending credits from BankStatement.merchantCreditAmount or credits (see also BankStatement attributes and VendorShift attributes). 
    provisional_balance = ''

    def get_vendors(self):
        """ All vendors selling tokens or receipt payments against this merchant account.
        """
        return self._vendors

    def set_vendors(self, value):
        for x in self._vendors:
            x._merchant_account = None
        for y in value:
            y._merchant_account = self
        self._vendors = value
            
    vendors = property(get_vendors, set_vendors)
    
    def add_vendors(self, *vendors):
        for obj in vendors:
            obj._merchant_account = self
            self._vendors.append(obj)
        
    def remove_vendors(self, *vendors):
        for obj in vendors:
            obj._merchant_account = None
            self._vendors.remove(obj)

    def get_bank_statements(self):
        """ 
        """
        return self._bank_statements

    def set_bank_statements(self, value):
        for x in self._bank_statements:
            x._merchant_account = None
        for y in value:
            y._merchant_account = self
        self._bank_statements = value
            
    bank_statements = property(get_bank_statements, set_bank_statements)
    
    def add_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._merchant_account = self
            self._bank_statements.append(obj)
        
    def remove_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._merchant_account = None
            self._bank_statements.remove(obj)

    def get_vendor_shifts(self):
        """ All vendor shifts that operate on this merchant account.
        """
        return self._vendor_shifts

    def set_vendor_shifts(self, value):
        for x in self._vendor_shifts:
            x._merchant_account = None
        for y in value:
            y._merchant_account = self
        self._vendor_shifts = value
            
    vendor_shifts = property(get_vendor_shifts, set_vendor_shifts)
    
    def add_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._merchant_account = self
            self._vendor_shifts.append(obj)
        
    def remove_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._merchant_account = None
            self._vendor_shifts.remove(obj)

    def get_merchant_agreement(self):
        """ Merchant agreement that instantiated this merchant account.
        """
        return self._merchant_agreement

    def set_merchant_agreement(self, value):
        if self._merchant_agreement is not None:
            filtered = [x for x in self.merchant_agreement.merchant_accounts if x != self]
            self._merchant_agreement._merchant_accounts = filtered
            
        self._merchant_agreement = value
        if self._merchant_agreement is not None:
            self._merchant_agreement._merchant_accounts.append(self)

    merchant_agreement = property(get_merchant_agreement, set_merchant_agreement)

    transactors = []
    
    def add_transactors(self, *transactors):
        for obj in transactors:
	        self._transactors.append(obj)
        
    def remove_transactors(self, *transactors):
        for obj in transactors:
	        self._transactors.remove(obj)

    # <<< merchant_account
    # @generated
    def __init__(self, current_balance='', provisional_balance='', vendors=[], bank_statements=[], vendor_shifts=[], merchant_agreement=None, transactors=[], **kw_args):
        """ Initialises a new 'MerchantAccount' instance.
        """
        self.current_balance = current_balance
        self.provisional_balance = provisional_balance
        self._vendors = []
        self.vendors = vendors
        self._bank_statements = []
        self.bank_statements = bank_statements
        self._vendor_shifts = []
        self.vendor_shifts = vendor_shifts
        self._merchant_agreement = None
        self.merchant_agreement = merchant_agreement
        self._transactors = []
        self.transactors = transactors

        super(MerchantAccount, self).__init__(**kw_args)
    # >>> merchant_account


class Card(Element):
    """ Documentation of the tender when it is a type of card (credit, debit, etc).
    """
    # The date when this card expires. 
    expiry_date = ''

    # The primary account number. 
    pan = ''

    # The card verification number. 
    cv_number = ''

    # Name of account holder. 
    account_holder_name = ''

    def get_tender(self):
        """ Payment tender this card is being used for.
        """
        return self._tender

    def set_tender(self, value):
        if self._tender is not None:
            self._tender._card = None
            
        self._tender = value
        if self._tender is not None:
            self._tender._card = self
            
    tender = property(get_tender, set_tender)

    # <<< card
    # @generated
    def __init__(self, expiry_date='', pan='', cv_number='', account_holder_name='', tender=None, **kw_args):
        """ Initialises a new 'Card' instance.
        """
        self.expiry_date = expiry_date
        self.pan = pan
        self.cv_number = cv_number
        self.account_holder_name = account_holder_name
        self._tender = None
        self.tender = tender

        super(Card, self).__init__(**kw_args)
    # >>> card


class AuxiliaryAccount(Document):
    """ Variable and dynamic part of AuxiliaryAgreement, generally representing the current state of the account related to the outstanding balance defined in AuxiliaryAgreement.
    """
    # The initial principle amount, with which this account was instantiated. 
    principle_amount = ''

    # The total amount currently remaining on this account that is required to be paid in order to settle the account to zero. This excludes any due amounts not yet paid. 
    balance = ''

    # Details of the last credit transaction performed on this account.
    last_credit = None

    # Current amounts now due for payment on this account.
    due = None

    # Details of the last debit transaction performed on this account.
    last_debit = None

    def get_payment_transactions(self):
        """ All payments against this account.
        """
        return self._payment_transactions

    def set_payment_transactions(self, value):
        for x in self._payment_transactions:
            x._auxiliary_account = None
        for y in value:
            y._auxiliary_account = self
        self._payment_transactions = value
            
    payment_transactions = property(get_payment_transactions, set_payment_transactions)
    
    def add_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._auxiliary_account = self
            self._payment_transactions.append(obj)
        
    def remove_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._auxiliary_account = None
            self._payment_transactions.remove(obj)

    def get_auxiliary_agreement(self):
        """ Auxiliary agreement regulating this account.
        """
        return self._auxiliary_agreement

    def set_auxiliary_agreement(self, value):
        if self._auxiliary_agreement is not None:
            filtered = [x for x in self.auxiliary_agreement.auxiliary_accounts if x != self]
            self._auxiliary_agreement._auxiliary_accounts = filtered
            
        self._auxiliary_agreement = value
        if self._auxiliary_agreement is not None:
            self._auxiliary_agreement._auxiliary_accounts.append(self)

    auxiliary_agreement = property(get_auxiliary_agreement, set_auxiliary_agreement)

    charges = []
    
    def add_charges(self, *charges):
        for obj in charges:
	        self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
	        self._charges.remove(obj)

    # <<< auxiliary_account
    # @generated
    def __init__(self, principle_amount='', balance='', last_credit=None, due=None, last_debit=None, payment_transactions=[], auxiliary_agreement=None, charges=[], **kw_args):
        """ Initialises a new 'AuxiliaryAccount' instance.
        """
        self.principle_amount = principle_amount
        self.balance = balance
        self.last_credit = last_credit
        self.due = due
        self.last_debit = last_debit
        self._payment_transactions = []
        self.payment_transactions = payment_transactions
        self._auxiliary_agreement = None
        self.auxiliary_agreement = auxiliary_agreement
        self._charges = []
        self.charges = charges

        super(AuxiliaryAccount, self).__init__(**kw_args)
    # >>> auxiliary_account


class AccountingUnit(Element):
    """ Unit for accounting; use either 'energyUnit' or 'currencyUnit' to specify the unit for 'value'.
    """
    # Value expressed in applicable units. 
    value = 0.0

    # Multiplier for the 'energyUnit' or 'monetaryUnit'. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
    multiplier = 'micro'

    # Unit of service. 
    energy_unit = ''

    # Unit of currency. Values are: "rur", "inr", "cad", "dkk", "cny", "usd", "sek", "aud", "jpy", "gbp", "eur", "nok", "chf", "other"
    monetary_unit = 'rur'

    # <<< accounting_unit
    # @generated
    def __init__(self, value=0.0, multiplier='micro', energy_unit='', monetary_unit='rur', **kw_args):
        """ Initialises a new 'AccountingUnit' instance.
        """
        self.value = value
        self.multiplier = multiplier
        self.energy_unit = energy_unit
        self.monetary_unit = monetary_unit

        super(AccountingUnit, self).__init__(**kw_args)
    # >>> accounting_unit


class AccountMovement(Element):
    """ Credit/debit movements for an account.
    """
    # Reason for credit/debit transaction on an account. Example: payment received/arrears interest levied. 
    reason = ''

    # Amount that was credited to/debited from an account. For example: payment received/interest charge on arrears. 
    amount = ''

    # Date and time when the credit/debit transaction was performed. 
    date_time = ''

    # <<< account_movement
    # @generated
    def __init__(self, reason='', amount='', date_time='', **kw_args):
        """ Initialises a new 'AccountMovement' instance.
        """
        self.reason = reason
        self.amount = amount
        self.date_time = date_time

        super(AccountMovement, self).__init__(**kw_args)
    # >>> account_movement


class Shift(IdentifiedObject):
    """ Generally referring to a period of operation or work performed. Whether shift is open/closed can be derived from attributes 'activiryInterval.start' and 'activityInterval.end'. The grand total for receipts (i.e., cumulative total of all actual receipted amounts during this shift; bankable + non-bankable; excludes rounding error totals) can be derived from Receipt attributes: =sum(Receipt.receiptAmount) ; includes bankable and non-bankable receipts. Must also reconcile against: =sum(receiptsGrandTotalBankable + receiptsGrandTotalNonBankable). must also reconcile against ReceiptSummary: =sum(ReceiptSummary.receiptsTotal). The attributes with 'GrandTotal' defined in this class may need to be used when the source data is periodically flushed from the system and then these cannot be derived.
    """
    # Cumulative amount in error due to process rounding not reflected in transactionsGandTotal. Values are obtained from Transaction attributes: =sum(Transaction.transactionRounding). 
    transactions_grand_total_rounding = ''

    # Cumulative total of transacted amounts during this shift. Values are obtained from Transaction attributes: =sum(Transaction.transactionAmount). It must also reconcile against TransactionSummary: =sum(TransactionSummary.transactionsTotal). 
    transactions_grand_total = ''

    # Total of amounts receipted during this shift that can be manually banked (cash and cheques for example). Values are obtained from Receipt attributes: =sum(Receipt.receiptAmount) for all Receipt.bankable = true. 
    receipts_grand_total_bankable = ''

    # Total of amounts receipted during this shift that cannot be manually banked (card payments for example). Values are obtained from Receipt attributes: =sum(Receipt.receiptAmount) for all Receipt.bankable = false. 
    receipts_grand_total_non_bankable = ''

    # Cumulative amount in error due to process rounding not reflected in receiptsGrandTotal. Values are obtained from Receipt attributes: =sum(Receipt.receiptRounding). 
    receipts_grand_total_rounding = ''

    # Interval for activity of this shift.
    activity_interval = None

    def get_receipt_summaries(self):
        """ All receipt summaries for this shift.
        """
        return self._receipt_summaries

    def set_receipt_summaries(self, value):
        for x in self._receipt_summaries:
            x._shift = None
        for y in value:
            y._shift = self
        self._receipt_summaries = value
            
    receipt_summaries = property(get_receipt_summaries, set_receipt_summaries)
    
    def add_receipt_summaries(self, *receipt_summaries):
        for obj in receipt_summaries:
            obj._shift = self
            self._receipt_summaries.append(obj)
        
    def remove_receipt_summaries(self, *receipt_summaries):
        for obj in receipt_summaries:
            obj._shift = None
            self._receipt_summaries.remove(obj)

    def get_transaction_summaries(self):
        """ All transaction summaries recorded for this shift.
        """
        return self._transaction_summaries

    def set_transaction_summaries(self, value):
        for x in self._transaction_summaries:
            x._shift = None
        for y in value:
            y._shift = self
        self._transaction_summaries = value
            
    transaction_summaries = property(get_transaction_summaries, set_transaction_summaries)
    
    def add_transaction_summaries(self, *transaction_summaries):
        for obj in transaction_summaries:
            obj._shift = self
            self._transaction_summaries.append(obj)
        
    def remove_transaction_summaries(self, *transaction_summaries):
        for obj in transaction_summaries:
            obj._shift = None
            self._transaction_summaries.remove(obj)

    # <<< shift
    # @generated
    def __init__(self, transactions_grand_total_rounding='', transactions_grand_total='', receipts_grand_total_bankable='', receipts_grand_total_non_bankable='', receipts_grand_total_rounding='', activity_interval=None, receipt_summaries=[], transaction_summaries=[], **kw_args):
        """ Initialises a new 'Shift' instance.
        """
        self.transactions_grand_total_rounding = transactions_grand_total_rounding
        self.transactions_grand_total = transactions_grand_total
        self.receipts_grand_total_bankable = receipts_grand_total_bankable
        self.receipts_grand_total_non_bankable = receipts_grand_total_non_bankable
        self.receipts_grand_total_rounding = receipts_grand_total_rounding
        self.activity_interval = activity_interval
        self._receipt_summaries = []
        self.receipt_summaries = receipt_summaries
        self._transaction_summaries = []
        self.transaction_summaries = transaction_summaries

        super(Shift, self).__init__(**kw_args)
    # >>> shift


class LineDetail(Element):
    """ Details on an amount line, with rounding, date and note.
    """
    # Totalised monetary value of all errors due to process rounding or truncating that is not reflected in 'amount'. 
    rounding = ''

    # Free format note relevant to this line. 
    note = ''

    # Date and time when this line was created in the application process. 
    date_time = ''

    # Amount for this line item. 
    amount = ''

    # <<< line_detail
    # @generated
    def __init__(self, rounding='', note='', date_time='', amount='', **kw_args):
        """ Initialises a new 'LineDetail' instance.
        """
        self.rounding = rounding
        self.note = note
        self.date_time = date_time
        self.amount = amount

        super(LineDetail, self).__init__(**kw_args)
    # >>> line_detail


class Tender(IdentifiedObject):
    """ Tender is what is 'offered' by the customer towards making a payment and is often more than the required payment (hence the need for 'change'). The payment is thus that part of the Tender that goes towards settlement of a particular transaction. Tender is modelled as an aggregation of Cheque and Card. Both these tender types can exist in a single tender bid thus 'accountHolderName' must exist separately in each of Cheque and Card as each could have a different account holder name.
    """
    # Amount tendered by customer. 
    amount = ''

    # Difference between amount tendered by customer and the amount charged by point of sale. 
    change = ''

    # Kind of tender from customer. Values are: "cheque", "cash", "card", "other", "unspecified"
    kind = 'cheque'

    def get_receipt(self):
        """ Receipt that recorded this receiving of a payment in the form of tenders.
        """
        return self._receipt

    def set_receipt(self, value):
        if self._receipt is not None:
            filtered = [x for x in self.receipt.tenders if x != self]
            self._receipt._tenders = filtered
            
        self._receipt = value
        if self._receipt is not None:
            self._receipt._tenders.append(self)

    receipt = property(get_receipt, set_receipt)

    def get_card(self):
        """ Card used to tender payment.
        """
        return self._card

    def set_card(self, value):
        if self._card is not None:
            self._card._tender = None
            
        self._card = value
        if self._card is not None:
            self._card._tender = self
            
    card = property(get_card, set_card)

    def get_cheque(self):
        """ Cheque used to tender payment.
        """
        return self._cheque

    def set_cheque(self, value):
        if self._cheque is not None:
            self._cheque._tender = None
            
        self._cheque = value
        if self._cheque is not None:
            self._cheque._tender = self
            
    cheque = property(get_cheque, set_cheque)

    # <<< tender
    # @generated
    def __init__(self, amount='', change='', kind='cheque', receipt=None, card=None, cheque=None, **kw_args):
        """ Initialises a new 'Tender' instance.
        """
        self.amount = amount
        self.change = change
        self.kind = kind
        self._receipt = None
        self.receipt = receipt
        self._card = None
        self.card = card
        self._cheque = None
        self.cheque = cheque

        super(Tender, self).__init__(**kw_args)
    # >>> tender


class AuxiliaryAgreement(Agreement):
    """ An ad-hoc auxiliary account agreement associated with a customer agreement, not part of the customer's account, but typically subject to formal agreement between customer and supplier (utility). Typically this is used to collect revenue owing by the customer for other services or arrears accrued with the utility for other services. It is typically linked to a prepaid token purchase transaction, thus forcing the customer to make a payment towards settlement of the auxiliary account balance whenever he needs to purchase a prepaid token for electricity. The present status of AuxiliaryAgreement can be defined in the context of the utility's business rules, for example: enabled, disabled, pending, over recovered, under recovered, written off, etc.
    """
    # A local reference to this AuxiliaryAgreement defined in the context of the implementation and not related to IdentifiedObject.mRID. 
    aux_ref = ''

    # The percentage of the transaction amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement when payments are not in arrears. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
    vend_portion = ''

    # The percentage of the transaction amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement when payments are in arrears. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
    vend_portion_arrear = ''

    # The fixed amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
    fixed_amount = ''

    # The interest per annum to be charged prorata on AuxiliaryAccount.dueArrears at the end of each payCycle. 
    arrears_interest = ''

    # The minimum amount that must be paid at any transaction towards settling this AuxiliryAgreement or reducing the balance. 
    min_amount = ''

    # The frequency for automatically recurring auxiliary charges, where AuxiliaryAccount.initialCharge is recursively added to AuxiliaryAccount.dueCurrent at the start of each auxCycle. For example: on a specified date and time; hourly; daily; weekly; monthly; 3-monthly; 6-monthly; 12-monthly; etc. 
    aux_cycle = ''

    # Sub-category of this AuxiliaryAgreement as sub-classification of the inherited 'category'. 
    sub_category = ''

    # The contractually expected payment frequency (by the customer). Examples are: ad-hoc; on specified date; hourly, daily, weekly, monthly. etc. 
    pay_cycle = ''

    # The coded priority indicating the priority that this AuxiliaryAgreement has above other AuxiliaryAgreements (associated with the same customer agreement) when it comes to competing for settlement from a payment transaction or token purchase. 
    aux_priority_code = ''

    def get_auxiliary_accounts(self):
        """ All auxiliary accounts regulated by this agreement.
        """
        return self._auxiliary_accounts

    def set_auxiliary_accounts(self, value):
        for x in self._auxiliary_accounts:
            x._auxiliary_agreement = None
        for y in value:
            y._auxiliary_agreement = self
        self._auxiliary_accounts = value
            
    auxiliary_accounts = property(get_auxiliary_accounts, set_auxiliary_accounts)
    
    def add_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            obj._auxiliary_agreement = self
            self._auxiliary_accounts.append(obj)
        
    def remove_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            obj._auxiliary_agreement = None
            self._auxiliary_accounts.remove(obj)

    def get_customer_agreement(self):
        """ Customer agreement this (non-service related) auxiliary agreement refers to.
        """
        return self._customer_agreement

    def set_customer_agreement(self, value):
        if self._customer_agreement is not None:
            filtered = [x for x in self.customer_agreement.auxiliary_agreements if x != self]
            self._customer_agreement._auxiliary_agreements = filtered
            
        self._customer_agreement = value
        if self._customer_agreement is not None:
            self._customer_agreement._auxiliary_agreements.append(self)

    customer_agreement = property(get_customer_agreement, set_customer_agreement)

    # <<< auxiliary_agreement
    # @generated
    def __init__(self, aux_ref='', vend_portion='', vend_portion_arrear='', fixed_amount='', arrears_interest='', min_amount='', aux_cycle='', sub_category='', pay_cycle='', aux_priority_code='', auxiliary_accounts=[], customer_agreement=None, **kw_args):
        """ Initialises a new 'AuxiliaryAgreement' instance.
        """
        self.aux_ref = aux_ref
        self.vend_portion = vend_portion
        self.vend_portion_arrear = vend_portion_arrear
        self.fixed_amount = fixed_amount
        self.arrears_interest = arrears_interest
        self.min_amount = min_amount
        self.aux_cycle = aux_cycle
        self.sub_category = sub_category
        self.pay_cycle = pay_cycle
        self.aux_priority_code = aux_priority_code
        self._auxiliary_accounts = []
        self.auxiliary_accounts = auxiliary_accounts
        self._customer_agreement = None
        self.customer_agreement = customer_agreement

        super(AuxiliaryAgreement, self).__init__(**kw_args)
    # >>> auxiliary_agreement


class VendorShift(Shift):
    """ The operating shift for a vendor during which he may transact against the merchant's account. It aggregates transactions and receipts during the shift and periodically debits a merchant account. The totals in VendorShift should always = sum of totals aggregated in all cashier shifts that were open under the particular vendor shift.
    """
    # The amount that is to be debited from the merchant account for this vendor shift. This amount reflects the sum(PaymentTransaction.transactionAmount). 
    merchant_debit_amount = ''

    # = true if merchantDebitAmount has been debited from MerchantAccount; typically happens at the end of VendorShift when it closes. 
    posted = False

    def get_transactions(self):
        """ 
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x._vendor_shift = None
        for y in value:
            y._vendor_shift = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._vendor_shift = self
            self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._vendor_shift = None
            self._transactions.remove(obj)

    def get_receipts(self):
        """ 
        """
        return self._receipts

    def set_receipts(self, value):
        for x in self._receipts:
            x._vendor_shift = None
        for y in value:
            y._vendor_shift = self
        self._receipts = value
            
    receipts = property(get_receipts, set_receipts)
    
    def add_receipts(self, *receipts):
        for obj in receipts:
            obj._vendor_shift = self
            self._receipts.append(obj)
        
    def remove_receipts(self, *receipts):
        for obj in receipts:
            obj._vendor_shift = None
            self._receipts.remove(obj)

    def get_vendor(self):
        """ Vendor that opens and owns this vendor shift.
        """
        return self._vendor

    def set_vendor(self, value):
        if self._vendor is not None:
            filtered = [x for x in self.vendor.vendor_shifts if x != self]
            self._vendor._vendor_shifts = filtered
            
        self._vendor = value
        if self._vendor is not None:
            self._vendor._vendor_shifts.append(self)

    vendor = property(get_vendor, set_vendor)

    def get_merchant_account(self):
        """ Merchant acocunt this vendor shift periodically debits (based on aggreated transactions).
        """
        return self._merchant_account

    def set_merchant_account(self, value):
        if self._merchant_account is not None:
            filtered = [x for x in self.merchant_account.vendor_shifts if x != self]
            self._merchant_account._vendor_shifts = filtered
            
        self._merchant_account = value
        if self._merchant_account is not None:
            self._merchant_account._vendor_shifts.append(self)

    merchant_account = property(get_merchant_account, set_merchant_account)

    # <<< vendor_shift
    # @generated
    def __init__(self, merchant_debit_amount='', posted=False, transactions=[], receipts=[], vendor=None, merchant_account=None, **kw_args):
        """ Initialises a new 'VendorShift' instance.
        """
        self.merchant_debit_amount = merchant_debit_amount
        self.posted = posted
        self._transactions = []
        self.transactions = transactions
        self._receipts = []
        self.receipts = receipts
        self._vendor = None
        self.vendor = vendor
        self._merchant_account = None
        self.merchant_account = merchant_account

        super(VendorShift, self).__init__(**kw_args)
    # >>> vendor_shift


class CashierShift(Shift):
    """ The operating shift for a cashier, during which he may transact against the CashierShift, subject to VendorShift being open.
    """
    # The amount of cash that the cashier brings with him to start his shift and that he will take away at the end of his shift; i.e. the cash float does not get banked. 
    cash_float = ''

    def get_transactions(self):
        """ 
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x._cashier_shift = None
        for y in value:
            y._cashier_shift = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._cashier_shift = self
            self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._cashier_shift = None
            self._transactions.remove(obj)

    def get_point_of_sale(self):
        """ Point of sale that is in operation during this shift.
        """
        return self._point_of_sale

    def set_point_of_sale(self, value):
        if self._point_of_sale is not None:
            filtered = [x for x in self.point_of_sale.cashier_shifts if x != self]
            self._point_of_sale._cashier_shifts = filtered
            
        self._point_of_sale = value
        if self._point_of_sale is not None:
            self._point_of_sale._cashier_shifts.append(self)

    point_of_sale = property(get_point_of_sale, set_point_of_sale)

    def get_cashier(self):
        """ Cashier operating this shift.
        """
        return self._cashier

    def set_cashier(self, value):
        if self._cashier is not None:
            filtered = [x for x in self.cashier.cashier_shifts if x != self]
            self._cashier._cashier_shifts = filtered
            
        self._cashier = value
        if self._cashier is not None:
            self._cashier._cashier_shifts.append(self)

    cashier = property(get_cashier, set_cashier)

    def get_receipts(self):
        """ All Receipts recorded for this Shift.
        """
        return self._receipts

    def set_receipts(self, value):
        for x in self._receipts:
            x._cashier_shift = None
        for y in value:
            y._cashier_shift = self
        self._receipts = value
            
    receipts = property(get_receipts, set_receipts)
    
    def add_receipts(self, *receipts):
        for obj in receipts:
            obj._cashier_shift = self
            self._receipts.append(obj)
        
    def remove_receipts(self, *receipts):
        for obj in receipts:
            obj._cashier_shift = None
            self._receipts.remove(obj)

    # <<< cashier_shift
    # @generated
    def __init__(self, cash_float='', transactions=[], point_of_sale=None, cashier=None, receipts=[], **kw_args):
        """ Initialises a new 'CashierShift' instance.
        """
        self.cash_float = cash_float
        self._transactions = []
        self.transactions = transactions
        self._point_of_sale = None
        self.point_of_sale = point_of_sale
        self._cashier = None
        self.cashier = cashier
        self._receipts = []
        self.receipts = receipts

        super(CashierShift, self).__init__(**kw_args)
    # >>> cashier_shift


# <<< payment_metering
# @generated
# >>> payment_metering

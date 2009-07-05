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
    # <<< point_of_sale
    # @generated
    def __init__(self, location='', cashier_shifts=None, tokens=None, vendor=None, **kw_args):
        """ Initialises a new 'PointOfSale' instance.
        """
        # Local description for where this pont of sale is physically located. 
        self.location = location
        
        self._cashier_shifts = []
        if cashier_shifts is None:
            self.cashier_shifts = []
        else:
            self.cashier_shifts = cashier_shifts
        self._tokens = []
        if tokens is None:
            self.tokens = []
        else:
            self.tokens = tokens
        self._vendor = None
        self.vendor = vendor

        super(PointOfSale, self).__init__(**kw_args)
    # >>> point_of_sale
        
    # <<< cashier_shifts
    # @generated
    def get_cashier_shifts(self):
        """ All shifts this point of sale operated in.
        """
        return self._cashier_shifts

    def set_cashier_shifts(self, value):
        for x in self._cashier_shifts:
            x.point_of_sale = None
        for y in value:
            y.point_of_sale = self
        self._cashier_shifts = value
            
    cashier_shifts = property(get_cashier_shifts, set_cashier_shifts)
    
    def add_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._point_of_sale = self
            if obj not in self._cashier_shifts:
                self._cashier_shifts.append(obj)
        
    def remove_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._point_of_sale = None
            self._cashier_shifts.remove(obj)
    # >>> cashier_shifts

    # <<< tokens
    # @generated
    def get_tokens(self):
        """ All Tokens sold or dispensed at this PointOfSale.
        """
        return self._tokens

    def set_tokens(self, value):
        for x in self._tokens:
            x.point_of_sale = None
        for y in value:
            y.point_of_sale = self
        self._tokens = value
            
    tokens = property(get_tokens, set_tokens)
    
    def add_tokens(self, *tokens):
        for obj in tokens:
            obj._point_of_sale = self
            if obj not in self._tokens:
                self._tokens.append(obj)
        
    def remove_tokens(self, *tokens):
        for obj in tokens:
            obj._point_of_sale = None
            self._tokens.remove(obj)
    # >>> tokens

    # <<< vendor
    # @generated
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
            if self not in self._vendor._point_of_sales:
                self._vendor._point_of_sales.append(self)

    vendor = property(get_vendor, set_vendor)
    # >>> vendor



class Cheque(Element):
    """ The actual tender when it is a type of cheque.
    """
    # <<< cheque
    # @generated
    def __init__(self, micr_number='', cheque_number='', kind='postal_order', date='', bank_account_detail=None, tender=None, **kw_args):
        """ Initialises a new 'Cheque' instance.
        """
        # The magnetic ink character recognition number printed on the cheque. 
        self.micr_number = micr_number
        # Cheque reference number as printed on the cheque. 
        self.cheque_number = cheque_number
        # Kind of cheque. Values are: "postal_order", "other", "bank_order"
        self.kind = kind
        # Date when cheque becomes valid. 
        self.date = date
        
        self.bank_account_detail = bank_account_detail
        self._tender = None
        self.tender = tender

        super(Cheque, self).__init__(**kw_args)
    # >>> cheque
        
    # <<< bank_account_detail
    # @generated
    # Details of the account holder and bank.
    bank_account_detail = None
    # >>> bank_account_detail

    # <<< tender
    # @generated
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
    # >>> tender



class Vendor(IdentifiedObject):
    """ The entity that owns PointOfSale and contracts with Cashier to receipt payments and vend tokens using the payment system. Vendor has a private contract with and is managed by Merchant who is a type of Organisation. Vendor is accountable to Merchant for revenue collected, who is in turn accountable to Supplier.
    """
    # <<< vendor
    # @generated
    def __init__(self, merchant_account=None, cashiers=None, bank_statements=None, point_of_sales=None, vendor_shifts=None, **kw_args):
        """ Initialises a new 'Vendor' instance.
        """
        
        self._merchant_account = None
        self.merchant_account = merchant_account
        self._cashiers = []
        if cashiers is None:
            self.cashiers = []
        else:
            self.cashiers = cashiers
        self._bank_statements = []
        if bank_statements is None:
            self.bank_statements = []
        else:
            self.bank_statements = bank_statements
        self._point_of_sales = []
        if point_of_sales is None:
            self.point_of_sales = []
        else:
            self.point_of_sales = point_of_sales
        self._vendor_shifts = []
        if vendor_shifts is None:
            self.vendor_shifts = []
        else:
            self.vendor_shifts = vendor_shifts

        super(Vendor, self).__init__(**kw_args)
    # >>> vendor
        
    # <<< merchant_account
    # @generated
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
            if self not in self._merchant_account._vendors:
                self._merchant_account._vendors.append(self)

    merchant_account = property(get_merchant_account, set_merchant_account)
    # >>> merchant_account

    # <<< cashiers
    # @generated
    def get_cashiers(self):
        """ All Cachiers managed by this Vendor.
        """
        return self._cashiers

    def set_cashiers(self, value):
        for x in self._cashiers:
            x.vendor = None
        for y in value:
            y.vendor = self
        self._cashiers = value
            
    cashiers = property(get_cashiers, set_cashiers)
    
    def add_cashiers(self, *cashiers):
        for obj in cashiers:
            obj._vendor = self
            if obj not in self._cashiers:
                self._cashiers.append(obj)
        
    def remove_cashiers(self, *cashiers):
        for obj in cashiers:
            obj._vendor = None
            self._cashiers.remove(obj)
    # >>> cashiers

    # <<< bank_statements
    # @generated
    def get_bank_statements(self):
        """ All BankStatements reflecting deposits made by this Vendor.
        """
        return self._bank_statements

    def set_bank_statements(self, value):
        for x in self._bank_statements:
            x.vendor = None
        for y in value:
            y.vendor = self
        self._bank_statements = value
            
    bank_statements = property(get_bank_statements, set_bank_statements)
    
    def add_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._vendor = self
            if obj not in self._bank_statements:
                self._bank_statements.append(obj)
        
    def remove_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._vendor = None
            self._bank_statements.remove(obj)
    # >>> bank_statements

    # <<< point_of_sales
    # @generated
    def get_point_of_sales(self):
        """ All points of sale this Vendor controls.
        """
        return self._point_of_sales

    def set_point_of_sales(self, value):
        for x in self._point_of_sales:
            x.vendor = None
        for y in value:
            y.vendor = self
        self._point_of_sales = value
            
    point_of_sales = property(get_point_of_sales, set_point_of_sales)
    
    def add_point_of_sales(self, *point_of_sales):
        for obj in point_of_sales:
            obj._vendor = self
            if obj not in self._point_of_sales:
                self._point_of_sales.append(obj)
        
    def remove_point_of_sales(self, *point_of_sales):
        for obj in point_of_sales:
            obj._vendor = None
            self._point_of_sales.remove(obj)
    # >>> point_of_sales

    # <<< vendor_shifts
    # @generated
    def get_vendor_shifts(self):
        """ All vendor shifts opened and owned by this vendor.
        """
        return self._vendor_shifts

    def set_vendor_shifts(self, value):
        for x in self._vendor_shifts:
            x.vendor = None
        for y in value:
            y.vendor = self
        self._vendor_shifts = value
            
    vendor_shifts = property(get_vendor_shifts, set_vendor_shifts)
    
    def add_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._vendor = self
            if obj not in self._vendor_shifts:
                self._vendor_shifts.append(obj)
        
    def remove_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._vendor = None
            self._vendor_shifts.remove(obj)
    # >>> vendor_shifts



class Transaction(IdentifiedObject):
    """ The record of details of payment for service or token sale.
    """
    # <<< transaction
    # @generated
    def __init__(self, service_units_error=0.0, kind='token_sale_payment', donor_reference='', service_units_energy=0.0, diverse_reference='', receiver_reference='', reversed_id='', line=None, cashier_shift=None, vendor_shift=None, auxiliary_account=None, customer_account=None, pricing_structure=None, receipt=None, user_attributes=None, meter_asset=None, **kw_args):
        """ Initialises a new 'Transaction' instance.
        """
        # Number of service units not reflected in 'serviceUnitsEnergy' due to process rounding or truncating errors. 
        self.service_units_error = service_units_error
        # Kind of transaction. Values are: "token_sale_payment", "token_free_issue", "transaction_reversal", "other", "token_exchange", "token_grant", "token_cancellation", "auxiliary_charge_payment", "account_payment", "diverse_payment", "service_charge_payment", "tax_charge_payment", "meter_configuration_token"
        self.kind = kind
        # Reference to the entity that is the source of 'amount' (for example: customer for token purchase; or supplier for free issue token). 
        self.donor_reference = donor_reference
        # Actual amount of service units that is being paid for. 
        self.service_units_energy = service_units_energy
        # Formal reference for use with diverse payment (traffic fine for example). 
        self.diverse_reference = diverse_reference
        # Reference to the entity that is the recipient of 'amount' (for example, supplier for service charge payment; or tax receiver for VAT). 
        self.receiver_reference = receiver_reference
        # (if 'kind' is transactionReversal) Reference to the original transaction that is being reversed by this transaction. 
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
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes
        self._meter_asset = None
        self.meter_asset = meter_asset

        super(Transaction, self).__init__(**kw_args)
    # >>> transaction
        
    # <<< line
    # @generated
    # Transaction amount, rounding, date and note for this transaction line.
    line = None
    # >>> line

    # <<< cashier_shift
    # @generated
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
            if self not in self._cashier_shift._transactions:
                self._cashier_shift._transactions.append(self)

    cashier_shift = property(get_cashier_shift, set_cashier_shift)
    # >>> cashier_shift

    # <<< vendor_shift
    # @generated
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
            if self not in self._vendor_shift._transactions:
                self._vendor_shift._transactions.append(self)

    vendor_shift = property(get_vendor_shift, set_vendor_shift)
    # >>> vendor_shift

    # <<< auxiliary_account
    # @generated
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
            if self not in self._auxiliary_account._payment_transactions:
                self._auxiliary_account._payment_transactions.append(self)

    auxiliary_account = property(get_auxiliary_account, set_auxiliary_account)
    # >>> auxiliary_account

    # <<< customer_account
    # @generated
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
            if self not in self._customer_account._payment_transactions:
                self._customer_account._payment_transactions.append(self)

    customer_account = property(get_customer_account, set_customer_account)
    # >>> customer_account

    # <<< pricing_structure
    # @generated
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
            if self not in self._pricing_structure._transactions:
                self._pricing_structure._transactions.append(self)

    pricing_structure = property(get_pricing_structure, set_pricing_structure)
    # >>> pricing_structure

    # <<< receipt
    # @generated
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
            if self not in self._receipt._transactions:
                self._receipt._transactions.append(self)

    receipt = property(get_receipt, set_receipt)
    # >>> receipt

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ All snapshots of meter parameters recorded at the time of this transaction. Use 'name' and 'value.value' attributes to specify name and value of a parameter from meter.
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for x in self._user_attributes:
            x.transaction = None
        for y in value:
            y.transaction = self
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            obj._transaction = self
            if obj not in self._user_attributes:
                self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            obj._transaction = None
            self._user_attributes.remove(obj)
    # >>> user_attributes

    # <<< meter_asset
    # @generated
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
            if self not in self._meter_asset._vending_transactions:
                self._meter_asset._vending_transactions.append(self)

    meter_asset = property(get_meter_asset, set_meter_asset)
    # >>> meter_asset



class ConsumptionTariffInterval(Element):
    """ One of a sequence of intervals defined in terms of consumption quantity of a service such as electricity, water, gas, etc. It is typically used in association with TariffProfile to define the steps or blocks in a step tariff structure, where startValue simultaneously defines the entry value of this step and the closing value of the previous step. Where consumption is &gt;= startValue it falls within this interval and where consumption is &lt; startValue it falls within the previous interval.
    """
    # <<< consumption_tariff_interval
    # @generated
    def __init__(self, start_value=0.0, sequence_number=0, charges=None, tariff_profiles=None, **kw_args):
        """ Initialises a new 'ConsumptionTariffInterval' instance.
        """
        # The lowest level of consumption that defines the starting point of this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
        self.start_value = start_value
        # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
        self.sequence_number = sequence_number
        
        self._charges = []
        if charges is None:
            self.charges = []
        else:
            self.charges = charges
        self._tariff_profiles = []
        if tariff_profiles is None:
            self.tariff_profiles = []
        else:
            self.tariff_profiles = tariff_profiles

        super(ConsumptionTariffInterval, self).__init__(**kw_args)
    # >>> consumption_tariff_interval
        
    # <<< charges
    # @generated
    def get_charges(self):
        """ All charges used to define this consumption tariff interval.
        """
        return self._charges

    def set_charges(self, value):
        for p in self._charges:
            filtered = [q for q in p.consumption_tariff_intervals if q != self]
            self._charges._consumption_tariff_intervals = filtered
        for r in value:
            if self not in r._consumption_tariff_intervals:
                r._consumption_tariff_intervals.append(self)
        self._charges = value
            
    charges = property(get_charges, set_charges)
    
    def add_charges(self, *charges):
        for obj in charges:
            if self not in obj._consumption_tariff_intervals:
                obj._consumption_tariff_intervals.append(self)
            self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
            if self in obj._consumption_tariff_intervals:
                obj._consumption_tariff_intervals.remove(self)
            self._charges.remove(obj)
    # >>> charges

    # <<< tariff_profiles
    # @generated
    def get_tariff_profiles(self):
        """ All tariff profiles defined by this consumption tariff interval.
        """
        return self._tariff_profiles

    def set_tariff_profiles(self, value):
        for p in self._tariff_profiles:
            filtered = [q for q in p.consumption_tariff_intervals if q != self]
            self._tariff_profiles._consumption_tariff_intervals = filtered
        for r in value:
            if self not in r._consumption_tariff_intervals:
                r._consumption_tariff_intervals.append(self)
        self._tariff_profiles = value
            
    tariff_profiles = property(get_tariff_profiles, set_tariff_profiles)
    
    def add_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
            if self not in obj._consumption_tariff_intervals:
                obj._consumption_tariff_intervals.append(self)
            self._tariff_profiles.append(obj)
        
    def remove_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
            if self in obj._consumption_tariff_intervals:
                obj._consumption_tariff_intervals.remove(self)
            self._tariff_profiles.remove(obj)
    # >>> tariff_profiles



class BankAccountDetail(Element):
    """ Details of a bank account.
    """
    # <<< bank_account_detail
    # @generated
    def __init__(self, account_number='', bank_name='', holder_id='', branch_code='', holder_name='', **kw_args):
        """ Initialises a new 'BankAccountDetail' instance.
        """
        # Operational account reference number. 
        self.account_number = account_number
        # Name of bank where account is held. 
        self.bank_name = bank_name
        # National identity number (or equivalent) of account holder. 
        self.holder_id = holder_id
        # Branch of bank where account is held. 
        self.branch_code = branch_code
        # Name of account holder. 
        self.holder_name = holder_name
        

        super(BankAccountDetail, self).__init__(**kw_args)
    # >>> bank_account_detail
        


class Cashier(IdentifiedObject):
    """ The operator of the point of sale for the duration of CashierShift. Cashier is under the exclusive management control of Vendor.
    """
    # <<< cashier
    # @generated
    def __init__(self, cashier_shifts=None, vendor=None, electronic_addresses=None, **kw_args):
        """ Initialises a new 'Cashier' instance.
        """
        
        self._cashier_shifts = []
        if cashier_shifts is None:
            self.cashier_shifts = []
        else:
            self.cashier_shifts = cashier_shifts
        self._vendor = None
        self.vendor = vendor
        self._electronic_addresses = []
        if electronic_addresses is None:
            self.electronic_addresses = []
        else:
            self.electronic_addresses = electronic_addresses

        super(Cashier, self).__init__(**kw_args)
    # >>> cashier
        
    # <<< cashier_shifts
    # @generated
    def get_cashier_shifts(self):
        """ All shifts operated by this cashier.
        """
        return self._cashier_shifts

    def set_cashier_shifts(self, value):
        for x in self._cashier_shifts:
            x.cashier = None
        for y in value:
            y.cashier = self
        self._cashier_shifts = value
            
    cashier_shifts = property(get_cashier_shifts, set_cashier_shifts)
    
    def add_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._cashier = self
            if obj not in self._cashier_shifts:
                self._cashier_shifts.append(obj)
        
    def remove_cashier_shifts(self, *cashier_shifts):
        for obj in cashier_shifts:
            obj._cashier = None
            self._cashier_shifts.remove(obj)
    # >>> cashier_shifts

    # <<< vendor
    # @generated
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
            if self not in self._vendor._cashiers:
                self._vendor._cashiers.append(self)

    vendor = property(get_vendor, set_vendor)
    # >>> vendor

    # <<< electronic_addresses
    # @generated
    def get_electronic_addresses(self):
        """ 
        """
        return self._electronic_addresses

    def set_electronic_addresses(self, value):
        for x in self._electronic_addresses:
            x.cashier = None
        for y in value:
            y.cashier = self
        self._electronic_addresses = value
            
    electronic_addresses = property(get_electronic_addresses, set_electronic_addresses)
    
    def add_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._cashier = self
            if obj not in self._electronic_addresses:
                self._electronic_addresses.append(obj)
        
    def remove_electronic_addresses(self, *electronic_addresses):
        for obj in electronic_addresses:
            obj._cashier = None
            self._electronic_addresses.remove(obj)
    # >>> electronic_addresses



class MerchantAgreement(Agreement):
    """ A formal controlling contractual agreement between Supplier and Merchant, in terms of which Merchant is authorised to vend tokens and receipt payments on behalf of Supplier. Merchant is accountable to Supplier for revenue collected at PointOfSale.
    """
    # <<< merchant_agreement
    # @generated
    def __init__(self, merchant_accounts=None, **kw_args):
        """ Initialises a new 'MerchantAgreement' instance.
        """
        
        self._merchant_accounts = []
        if merchant_accounts is None:
            self.merchant_accounts = []
        else:
            self.merchant_accounts = merchant_accounts

        super(MerchantAgreement, self).__init__(**kw_args)
    # >>> merchant_agreement
        
    # <<< merchant_accounts
    # @generated
    def get_merchant_accounts(self):
        """ All merchant accounts instantiated as a result of this merchant agreement.
        """
        return self._merchant_accounts

    def set_merchant_accounts(self, value):
        for x in self._merchant_accounts:
            x.merchant_agreement = None
        for y in value:
            y.merchant_agreement = self
        self._merchant_accounts = value
            
    merchant_accounts = property(get_merchant_accounts, set_merchant_accounts)
    
    def add_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            obj._merchant_agreement = self
            if obj not in self._merchant_accounts:
                self._merchant_accounts.append(obj)
        
    def remove_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            obj._merchant_agreement = None
            self._merchant_accounts.remove(obj)
    # >>> merchant_accounts



class TimeTariffInterval(Element):
    """ One of a sequence of time intervals defined in terms of real time. It is typically used in association with TariffProfile to define the intervals in a time of use tariff structure, where startDateTime simultaneously determines the starting point of this interval and the ending point of the previous interval.
    """
    # <<< time_tariff_interval
    # @generated
    def __init__(self, start_date_time='', sequence_number=0, charges=None, tariff_profiles=None, **kw_args):
        """ Initialises a new 'TimeTariffInterval' instance.
        """
        # A reatime marker that defines the starting time (typically it is the time of day) for this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
        self.start_date_time = start_date_time
        # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
        self.sequence_number = sequence_number
        
        self._charges = []
        if charges is None:
            self.charges = []
        else:
            self.charges = charges
        self._tariff_profiles = []
        if tariff_profiles is None:
            self.tariff_profiles = []
        else:
            self.tariff_profiles = tariff_profiles

        super(TimeTariffInterval, self).__init__(**kw_args)
    # >>> time_tariff_interval
        
    # <<< charges
    # @generated
    def get_charges(self):
        """ All charges used to define this time tariff interval.
        """
        return self._charges

    def set_charges(self, value):
        for p in self._charges:
            filtered = [q for q in p.time_tariff_intervals if q != self]
            self._charges._time_tariff_intervals = filtered
        for r in value:
            if self not in r._time_tariff_intervals:
                r._time_tariff_intervals.append(self)
        self._charges = value
            
    charges = property(get_charges, set_charges)
    
    def add_charges(self, *charges):
        for obj in charges:
            if self not in obj._time_tariff_intervals:
                obj._time_tariff_intervals.append(self)
            self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
            if self in obj._time_tariff_intervals:
                obj._time_tariff_intervals.remove(self)
            self._charges.remove(obj)
    # >>> charges

    # <<< tariff_profiles
    # @generated
    def get_tariff_profiles(self):
        """ All tariff profiles defined by this time tariff interval.
        """
        return self._tariff_profiles

    def set_tariff_profiles(self, value):
        for p in self._tariff_profiles:
            filtered = [q for q in p.time_tariff_intervals if q != self]
            self._tariff_profiles._time_tariff_intervals = filtered
        for r in value:
            if self not in r._time_tariff_intervals:
                r._time_tariff_intervals.append(self)
        self._tariff_profiles = value
            
    tariff_profiles = property(get_tariff_profiles, set_tariff_profiles)
    
    def add_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
            if self not in obj._time_tariff_intervals:
                obj._time_tariff_intervals.append(self)
            self._tariff_profiles.append(obj)
        
    def remove_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
            if self in obj._time_tariff_intervals:
                obj._time_tariff_intervals.remove(self)
            self._tariff_profiles.remove(obj)
    # >>> tariff_profiles



class Transactor(IdentifiedObject):
    """ The entity that ultimately executes the transaction and who is in control of the process; typically this is embodied in secure software running on a server that may employ secure hardware encryption devices for secure transaction processing.
    """
    # <<< transactor
    # @generated
    def __init__(self, merchant_accounts=None, **kw_args):
        """ Initialises a new 'Transactor' instance.
        """
        
        self._merchant_accounts = []
        if merchant_accounts is None:
            self.merchant_accounts = []
        else:
            self.merchant_accounts = merchant_accounts

        super(Transactor, self).__init__(**kw_args)
    # >>> transactor
        
    # <<< merchant_accounts
    # @generated
    def get_merchant_accounts(self):
        """ All merchant accounts registered with this transactor.
        """
        return self._merchant_accounts

    def set_merchant_accounts(self, value):
        for p in self._merchant_accounts:
            filtered = [q for q in p.transactors if q != self]
            self._merchant_accounts._transactors = filtered
        for r in value:
            if self not in r._transactors:
                r._transactors.append(self)
        self._merchant_accounts = value
            
    merchant_accounts = property(get_merchant_accounts, set_merchant_accounts)
    
    def add_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            if self not in obj._transactors:
                obj._transactors.append(self)
            self._merchant_accounts.append(obj)
        
    def remove_merchant_accounts(self, *merchant_accounts):
        for obj in merchant_accounts:
            if self in obj._transactors:
                obj._transactors.remove(self)
            self._merchant_accounts.remove(obj)
    # >>> merchant_accounts



class ServiceSupplier(Organisation):
    """ Organisation that provides services to Customers.
    """
    # <<< service_supplier
    # @generated
    def __init__(self, kind='retailer', issuer_identification_number='', service_delivery_points=None, bank_accounts=None, customer_agreements=None, **kw_args):
        """ Initialises a new 'ServiceSupplier' instance.
        """
        # Kind of supplier. Values are: "retailer", "other", "utility"
        self.kind = kind
        # Unique transaction reference prefix number issued to an entity by the International Standards Organisation for the purpose of tagging onto electronic financial transactions, as defined in ISO/IEC 7812-1 and ISO/IEC 7812-2. 
        self.issuer_identification_number = issuer_identification_number
        
        self._service_delivery_points = []
        if service_delivery_points is None:
            self.service_delivery_points = []
        else:
            self.service_delivery_points = service_delivery_points
        self._bank_accounts = []
        if bank_accounts is None:
            self.bank_accounts = []
        else:
            self.bank_accounts = bank_accounts
        self._customer_agreements = []
        if customer_agreements is None:
            self.customer_agreements = []
        else:
            self.customer_agreements = customer_agreements

        super(ServiceSupplier, self).__init__(**kw_args)
    # >>> service_supplier
        
    # <<< service_delivery_points
    # @generated
    def get_service_delivery_points(self):
        """ All service delivery points this service supplier utilises to deliver a service.
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x.service_supplier = None
        for y in value:
            y.service_supplier = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_supplier = self
            if obj not in self._service_delivery_points:
                self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_supplier = None
            self._service_delivery_points.remove(obj)
    # >>> service_delivery_points

    # <<< bank_accounts
    # @generated
    def get_bank_accounts(self):
        """ All BackAccounts this ServiceSupplier owns.
        """
        return self._bank_accounts

    def set_bank_accounts(self, value):
        for x in self._bank_accounts:
            x.service_supplier = None
        for y in value:
            y.service_supplier = self
        self._bank_accounts = value
            
    bank_accounts = property(get_bank_accounts, set_bank_accounts)
    
    def add_bank_accounts(self, *bank_accounts):
        for obj in bank_accounts:
            obj._service_supplier = self
            if obj not in self._bank_accounts:
                self._bank_accounts.append(obj)
        
    def remove_bank_accounts(self, *bank_accounts):
        for obj in bank_accounts:
            obj._service_supplier = None
            self._bank_accounts.remove(obj)
    # >>> bank_accounts

    # <<< customer_agreements
    # @generated
    def get_customer_agreements(self):
        """ All customer agreements of this service supplier.
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x.service_supplier = None
        for y in value:
            y.service_supplier = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_supplier = self
            if obj not in self._customer_agreements:
                self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_supplier = None
            self._customer_agreements.remove(obj)
    # >>> customer_agreements



class Receipt(IdentifiedObject):
    """ Record of total receipted payment from customer.
    """
    # <<< receipt
    # @generated
    def __init__(self, is_bankable=False, line=None, transactions=None, cashier_shift=None, vendor_shift=None, tenders=None, **kw_args):
        """ Initialises a new 'Receipt' instance.
        """
        # True if this receipted payment is manually bankable, otherwise it is an electronic funds transfer. 
        self.is_bankable = is_bankable
        
        self.line = line
        self._transactions = []
        if transactions is None:
            self.transactions = []
        else:
            self.transactions = transactions
        self._cashier_shift = None
        self.cashier_shift = cashier_shift
        self._vendor_shift = None
        self.vendor_shift = vendor_shift
        self._tenders = []
        if tenders is None:
            self.tenders = []
        else:
            self.tenders = tenders

        super(Receipt, self).__init__(**kw_args)
    # >>> receipt
        
    # <<< line
    # @generated
    # Receipted amount with rounding, date and note.
    line = None
    # >>> line

    # <<< transactions
    # @generated
    def get_transactions(self):
        """ All transactions recorded for this receipted payment.
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x.receipt = None
        for y in value:
            y.receipt = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._receipt = self
            if obj not in self._transactions:
                self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._receipt = None
            self._transactions.remove(obj)
    # >>> transactions

    # <<< cashier_shift
    # @generated
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
            if self not in self._cashier_shift._receipts:
                self._cashier_shift._receipts.append(self)

    cashier_shift = property(get_cashier_shift, set_cashier_shift)
    # >>> cashier_shift

    # <<< vendor_shift
    # @generated
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
            if self not in self._vendor_shift._receipts:
                self._vendor_shift._receipts.append(self)

    vendor_shift = property(get_vendor_shift, set_vendor_shift)
    # >>> vendor_shift

    # <<< tenders
    # @generated
    def get_tenders(self):
        """ All payments received in the form of tenders recorded by this receipt.
        """
        return self._tenders

    def set_tenders(self, value):
        for x in self._tenders:
            x.receipt = None
        for y in value:
            y.receipt = self
        self._tenders = value
            
    tenders = property(get_tenders, set_tenders)
    
    def add_tenders(self, *tenders):
        for obj in tenders:
            obj._receipt = self
            if obj not in self._tenders:
                self._tenders.append(obj)
        
    def remove_tenders(self, *tenders):
        for obj in tenders:
            obj._receipt = None
            self._tenders.remove(obj)
    # >>> tenders



class Due(Element):
    """ Details on amounts due for an account.
    """
    # <<< due
    # @generated
    def __init__(self, arrears=0.0, current=0.0, principle=0.0, charges=0.0, interest=0.0, **kw_args):
        """ Initialises a new 'Due' instance.
        """
        # Part of 'current' that constitutes the arrears portion. 
        self.arrears = arrears
        # Current total amount now due: current = principle + arrears + interest + charges. Typically the rule for settlement priority is: interest dues, then arrears dues, then current dues, then charge dues. 
        self.current = current
        # Part of 'current' that constitutes the portion of the principle amount currently due. 
        self.principle = principle
        # Part of 'current' that constitutes the charge portion: 'charges' = 'Charge.fixedPortion' + 'Charge.variablePortion'. 
        self.charges = charges
        # Part of 'current' that constitutes the interest portion. 
        self.interest = interest
        

        super(Due, self).__init__(**kw_args)
    # >>> due
        


class Charge(IdentifiedObject):
    """ A charge element associated with other entities such as tariff structures, auxiliary agreements or other charge elements. The total charge amount applicable to this instance of Charge is the sum of fixedPortion plus percentagePortion.
    """
    # <<< charge
    # @generated
    def __init__(self, kind='consumption_charge', variable_portion=0.0, fixed_portion=None, time_tariff_intervals=None, parent_charge=None, auxiliary_accounts=None, child_charges=None, consumption_tariff_intervals=None, **kw_args):
        """ Initialises a new 'Charge' instance.
        """
        # The kind of charge to be applied. Values are: "consumption_charge", "auxiliary_charge", "other", "tax_charge", "demand_charge"
        self.kind = kind
        # The variable portion of this charge element, calculated as a percentage of the total amount of a parent charge. 
        self.variable_portion = variable_portion
        
        self.fixed_portion = fixed_portion
        self._time_tariff_intervals = []
        if time_tariff_intervals is None:
            self.time_tariff_intervals = []
        else:
            self.time_tariff_intervals = time_tariff_intervals
        self._parent_charge = None
        self.parent_charge = parent_charge
        self._auxiliary_accounts = []
        if auxiliary_accounts is None:
            self.auxiliary_accounts = []
        else:
            self.auxiliary_accounts = auxiliary_accounts
        self._child_charges = []
        if child_charges is None:
            self.child_charges = []
        else:
            self.child_charges = child_charges
        self._consumption_tariff_intervals = []
        if consumption_tariff_intervals is None:
            self.consumption_tariff_intervals = []
        else:
            self.consumption_tariff_intervals = consumption_tariff_intervals

        super(Charge, self).__init__(**kw_args)
    # >>> charge
        
    # <<< fixed_portion
    # @generated
    # The fixed portion of this charge element.
    fixed_portion = None
    # >>> fixed_portion

    # <<< time_tariff_intervals
    # @generated
    def get_time_tariff_intervals(self):
        """ Tariff intervals to which this time-based charge must be levied.
        """
        return self._time_tariff_intervals

    def set_time_tariff_intervals(self, value):
        for p in self._time_tariff_intervals:
            filtered = [q for q in p.charges if q != self]
            self._time_tariff_intervals._charges = filtered
        for r in value:
            if self not in r._charges:
                r._charges.append(self)
        self._time_tariff_intervals = value
            
    time_tariff_intervals = property(get_time_tariff_intervals, set_time_tariff_intervals)
    
    def add_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
            if self not in obj._charges:
                obj._charges.append(self)
            self._time_tariff_intervals.append(obj)
        
    def remove_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
            if self in obj._charges:
                obj._charges.remove(self)
            self._time_tariff_intervals.remove(obj)
    # >>> time_tariff_intervals

    # <<< parent_charge
    # @generated
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
            if self not in self._parent_charge._child_charges:
                self._parent_charge._child_charges.append(self)

    parent_charge = property(get_parent_charge, set_parent_charge)
    # >>> parent_charge

    # <<< auxiliary_accounts
    # @generated
    def get_auxiliary_accounts(self):
        """ All auxiliary accounts to which this charge must be levied.
        """
        return self._auxiliary_accounts

    def set_auxiliary_accounts(self, value):
        for p in self._auxiliary_accounts:
            filtered = [q for q in p.charges if q != self]
            self._auxiliary_accounts._charges = filtered
        for r in value:
            if self not in r._charges:
                r._charges.append(self)
        self._auxiliary_accounts = value
            
    auxiliary_accounts = property(get_auxiliary_accounts, set_auxiliary_accounts)
    
    def add_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            if self not in obj._charges:
                obj._charges.append(self)
            self._auxiliary_accounts.append(obj)
        
    def remove_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            if self in obj._charges:
                obj._charges.remove(self)
            self._auxiliary_accounts.remove(obj)
    # >>> auxiliary_accounts

    # <<< child_charges
    # @generated
    def get_child_charges(self):
        """ All sub-components of this complex charge.
        """
        return self._child_charges

    def set_child_charges(self, value):
        for x in self._child_charges:
            x.parent_charge = None
        for y in value:
            y.parent_charge = self
        self._child_charges = value
            
    child_charges = property(get_child_charges, set_child_charges)
    
    def add_child_charges(self, *child_charges):
        for obj in child_charges:
            obj._parent_charge = self
            if obj not in self._child_charges:
                self._child_charges.append(obj)
        
    def remove_child_charges(self, *child_charges):
        for obj in child_charges:
            obj._parent_charge = None
            self._child_charges.remove(obj)
    # >>> child_charges

    # <<< consumption_tariff_intervals
    # @generated
    def get_consumption_tariff_intervals(self):
        """ Tariff intervals to which this consumption-based charge must be levied.
        """
        return self._consumption_tariff_intervals

    def set_consumption_tariff_intervals(self, value):
        for p in self._consumption_tariff_intervals:
            filtered = [q for q in p.charges if q != self]
            self._consumption_tariff_intervals._charges = filtered
        for r in value:
            if self not in r._charges:
                r._charges.append(self)
        self._consumption_tariff_intervals = value
            
    consumption_tariff_intervals = property(get_consumption_tariff_intervals, set_consumption_tariff_intervals)
    
    def add_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
            if self not in obj._charges:
                obj._charges.append(self)
            self._consumption_tariff_intervals.append(obj)
        
    def remove_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
            if self in obj._charges:
                obj._charges.remove(self)
            self._consumption_tariff_intervals.remove(obj)
    # >>> consumption_tariff_intervals



class TariffProfile(Document):
    """ A schedule of charges; structure associated with Tariff that allows the definition of complex tarif structures such as step and time of use when used in conjunction with TimeTariffInterval and Charge. Inherited 'status.value' is defined in the context of the utility's business rules, for example: active, inactive, etc.
    """
    # <<< tariff_profile
    # @generated
    def __init__(self, tariff_cycle='', tariffs=None, consumption_tariff_intervals=None, time_tariff_intervals=None, **kw_args):
        """ Initialises a new 'TariffProfile' instance.
        """
        # The frequency at which the tariff charge schedule is repeated Examples are: once off on a specified date and time; hourly; daily; weekly; monthly; 3-monthly; 6-monthly; 12-monthly; etc. At the end of each cycle, the business rules are reset to start from the beginning again. 
        self.tariff_cycle = tariff_cycle
        
        self._tariffs = []
        if tariffs is None:
            self.tariffs = []
        else:
            self.tariffs = tariffs
        self._consumption_tariff_intervals = []
        if consumption_tariff_intervals is None:
            self.consumption_tariff_intervals = []
        else:
            self.consumption_tariff_intervals = consumption_tariff_intervals
        self._time_tariff_intervals = []
        if time_tariff_intervals is None:
            self.time_tariff_intervals = []
        else:
            self.time_tariff_intervals = time_tariff_intervals

        super(TariffProfile, self).__init__(**kw_args)
    # >>> tariff_profile
        
    # <<< tariffs
    # @generated
    def get_tariffs(self):
        """ All tariffs defined by this tariff profile.
        """
        return self._tariffs

    def set_tariffs(self, value):
        for p in self._tariffs:
            filtered = [q for q in p.tariff_profiles if q != self]
            self._tariffs._tariff_profiles = filtered
        for r in value:
            if self not in r._tariff_profiles:
                r._tariff_profiles.append(self)
        self._tariffs = value
            
    tariffs = property(get_tariffs, set_tariffs)
    
    def add_tariffs(self, *tariffs):
        for obj in tariffs:
            if self not in obj._tariff_profiles:
                obj._tariff_profiles.append(self)
            self._tariffs.append(obj)
        
    def remove_tariffs(self, *tariffs):
        for obj in tariffs:
            if self in obj._tariff_profiles:
                obj._tariff_profiles.remove(self)
            self._tariffs.remove(obj)
    # >>> tariffs

    # <<< consumption_tariff_intervals
    # @generated
    def get_consumption_tariff_intervals(self):
        """ All consumption tariff intervals used to define this tariff profile.
        """
        return self._consumption_tariff_intervals

    def set_consumption_tariff_intervals(self, value):
        for p in self._consumption_tariff_intervals:
            filtered = [q for q in p.tariff_profiles if q != self]
            self._consumption_tariff_intervals._tariff_profiles = filtered
        for r in value:
            if self not in r._tariff_profiles:
                r._tariff_profiles.append(self)
        self._consumption_tariff_intervals = value
            
    consumption_tariff_intervals = property(get_consumption_tariff_intervals, set_consumption_tariff_intervals)
    
    def add_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
            if self not in obj._tariff_profiles:
                obj._tariff_profiles.append(self)
            self._consumption_tariff_intervals.append(obj)
        
    def remove_consumption_tariff_intervals(self, *consumption_tariff_intervals):
        for obj in consumption_tariff_intervals:
            if self in obj._tariff_profiles:
                obj._tariff_profiles.remove(self)
            self._consumption_tariff_intervals.remove(obj)
    # >>> consumption_tariff_intervals

    # <<< time_tariff_intervals
    # @generated
    def get_time_tariff_intervals(self):
        """ All time tariff intervals used to define this tariff profile.
        """
        return self._time_tariff_intervals

    def set_time_tariff_intervals(self, value):
        for p in self._time_tariff_intervals:
            filtered = [q for q in p.tariff_profiles if q != self]
            self._time_tariff_intervals._tariff_profiles = filtered
        for r in value:
            if self not in r._tariff_profiles:
                r._tariff_profiles.append(self)
        self._time_tariff_intervals = value
            
    time_tariff_intervals = property(get_time_tariff_intervals, set_time_tariff_intervals)
    
    def add_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
            if self not in obj._tariff_profiles:
                obj._tariff_profiles.append(self)
            self._time_tariff_intervals.append(obj)
        
    def remove_time_tariff_intervals(self, *time_tariff_intervals):
        for obj in time_tariff_intervals:
            if self in obj._tariff_profiles:
                obj._tariff_profiles.remove(self)
            self._time_tariff_intervals.remove(obj)
    # >>> time_tariff_intervals



class MerchantAccount(Document):
    """ The operating account controlled by MerchantAgreement, against which Vendor may vend tokens or receipt payments. Transactions via VendorShift debit the account and bank deposits via BankStatement credit the account.
    """
    # <<< merchant_account
    # @generated
    def __init__(self, current_balance=0.0, provisional_balance=0.0, vendors=None, bank_statements=None, vendor_shifts=None, merchant_agreement=None, transactors=None, **kw_args):
        """ Initialises a new 'MerchantAccount' instance.
        """
        # The current operating balance of this account. 
        self.current_balance = current_balance
        # The balance of this account after taking into account any pending debits from VendorShift.merchantDebitAmount and pending credits from BankStatement.merchantCreditAmount or credits (see also BankStatement attributes and VendorShift attributes). 
        self.provisional_balance = provisional_balance
        
        self._vendors = []
        if vendors is None:
            self.vendors = []
        else:
            self.vendors = vendors
        self._bank_statements = []
        if bank_statements is None:
            self.bank_statements = []
        else:
            self.bank_statements = bank_statements
        self._vendor_shifts = []
        if vendor_shifts is None:
            self.vendor_shifts = []
        else:
            self.vendor_shifts = vendor_shifts
        self._merchant_agreement = None
        self.merchant_agreement = merchant_agreement
        self._transactors = []
        if transactors is None:
            self.transactors = []
        else:
            self.transactors = transactors

        super(MerchantAccount, self).__init__(**kw_args)
    # >>> merchant_account
        
    # <<< vendors
    # @generated
    def get_vendors(self):
        """ All vendors selling tokens or receipt payments against this merchant account.
        """
        return self._vendors

    def set_vendors(self, value):
        for x in self._vendors:
            x.merchant_account = None
        for y in value:
            y.merchant_account = self
        self._vendors = value
            
    vendors = property(get_vendors, set_vendors)
    
    def add_vendors(self, *vendors):
        for obj in vendors:
            obj._merchant_account = self
            if obj not in self._vendors:
                self._vendors.append(obj)
        
    def remove_vendors(self, *vendors):
        for obj in vendors:
            obj._merchant_account = None
            self._vendors.remove(obj)
    # >>> vendors

    # <<< bank_statements
    # @generated
    def get_bank_statements(self):
        """ 
        """
        return self._bank_statements

    def set_bank_statements(self, value):
        for x in self._bank_statements:
            x.merchant_account = None
        for y in value:
            y.merchant_account = self
        self._bank_statements = value
            
    bank_statements = property(get_bank_statements, set_bank_statements)
    
    def add_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._merchant_account = self
            if obj not in self._bank_statements:
                self._bank_statements.append(obj)
        
    def remove_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._merchant_account = None
            self._bank_statements.remove(obj)
    # >>> bank_statements

    # <<< vendor_shifts
    # @generated
    def get_vendor_shifts(self):
        """ All vendor shifts that operate on this merchant account.
        """
        return self._vendor_shifts

    def set_vendor_shifts(self, value):
        for x in self._vendor_shifts:
            x.merchant_account = None
        for y in value:
            y.merchant_account = self
        self._vendor_shifts = value
            
    vendor_shifts = property(get_vendor_shifts, set_vendor_shifts)
    
    def add_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._merchant_account = self
            if obj not in self._vendor_shifts:
                self._vendor_shifts.append(obj)
        
    def remove_vendor_shifts(self, *vendor_shifts):
        for obj in vendor_shifts:
            obj._merchant_account = None
            self._vendor_shifts.remove(obj)
    # >>> vendor_shifts

    # <<< merchant_agreement
    # @generated
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
            if self not in self._merchant_agreement._merchant_accounts:
                self._merchant_agreement._merchant_accounts.append(self)

    merchant_agreement = property(get_merchant_agreement, set_merchant_agreement)
    # >>> merchant_agreement

    # <<< transactors
    # @generated
    def get_transactors(self):
        """ All transactors this merchant account is registered with.
        """
        return self._transactors

    def set_transactors(self, value):
        for p in self._transactors:
            filtered = [q for q in p.merchant_accounts if q != self]
            self._transactors._merchant_accounts = filtered
        for r in value:
            if self not in r._merchant_accounts:
                r._merchant_accounts.append(self)
        self._transactors = value
            
    transactors = property(get_transactors, set_transactors)
    
    def add_transactors(self, *transactors):
        for obj in transactors:
            if self not in obj._merchant_accounts:
                obj._merchant_accounts.append(self)
            self._transactors.append(obj)
        
    def remove_transactors(self, *transactors):
        for obj in transactors:
            if self in obj._merchant_accounts:
                obj._merchant_accounts.remove(self)
            self._transactors.remove(obj)
    # >>> transactors



class Card(Element):
    """ Documentation of the tender when it is a type of card (credit, debit, etc).
    """
    # <<< card
    # @generated
    def __init__(self, expiry_date='', pan='', cv_number='', account_holder_name='', tender=None, **kw_args):
        """ Initialises a new 'Card' instance.
        """
        # The date when this card expires. 
        self.expiry_date = expiry_date
        # The primary account number. 
        self.pan = pan
        # The card verification number. 
        self.cv_number = cv_number
        # Name of account holder. 
        self.account_holder_name = account_holder_name
        
        self._tender = None
        self.tender = tender

        super(Card, self).__init__(**kw_args)
    # >>> card
        
    # <<< tender
    # @generated
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
    # >>> tender



class AuxiliaryAccount(Document):
    """ Variable and dynamic part of AuxiliaryAgreement, generally representing the current state of the account related to the outstanding balance defined in AuxiliaryAgreement.
    """
    # <<< auxiliary_account
    # @generated
    def __init__(self, principle_amount=0.0, balance=0.0, last_credit=None, due=None, last_debit=None, payment_transactions=None, auxiliary_agreement=None, charges=None, **kw_args):
        """ Initialises a new 'AuxiliaryAccount' instance.
        """
        # The initial principle amount, with which this account was instantiated. 
        self.principle_amount = principle_amount
        # The total amount currently remaining on this account that is required to be paid in order to settle the account to zero. This excludes any due amounts not yet paid. 
        self.balance = balance
        
        self.last_credit = last_credit
        self.due = due
        self.last_debit = last_debit
        self._payment_transactions = []
        if payment_transactions is None:
            self.payment_transactions = []
        else:
            self.payment_transactions = payment_transactions
        self._auxiliary_agreement = None
        self.auxiliary_agreement = auxiliary_agreement
        self._charges = []
        if charges is None:
            self.charges = []
        else:
            self.charges = charges

        super(AuxiliaryAccount, self).__init__(**kw_args)
    # >>> auxiliary_account
        
    # <<< last_credit
    # @generated
    # Details of the last credit transaction performed on this account.
    last_credit = None
    # >>> last_credit

    # <<< due
    # @generated
    # Current amounts now due for payment on this account.
    due = None
    # >>> due

    # <<< last_debit
    # @generated
    # Details of the last debit transaction performed on this account.
    last_debit = None
    # >>> last_debit

    # <<< payment_transactions
    # @generated
    def get_payment_transactions(self):
        """ All payments against this account.
        """
        return self._payment_transactions

    def set_payment_transactions(self, value):
        for x in self._payment_transactions:
            x.auxiliary_account = None
        for y in value:
            y.auxiliary_account = self
        self._payment_transactions = value
            
    payment_transactions = property(get_payment_transactions, set_payment_transactions)
    
    def add_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._auxiliary_account = self
            if obj not in self._payment_transactions:
                self._payment_transactions.append(obj)
        
    def remove_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._auxiliary_account = None
            self._payment_transactions.remove(obj)
    # >>> payment_transactions

    # <<< auxiliary_agreement
    # @generated
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
            if self not in self._auxiliary_agreement._auxiliary_accounts:
                self._auxiliary_agreement._auxiliary_accounts.append(self)

    auxiliary_agreement = property(get_auxiliary_agreement, set_auxiliary_agreement)
    # >>> auxiliary_agreement

    # <<< charges
    # @generated
    def get_charges(self):
        """ All charges levied on this account.
        """
        return self._charges

    def set_charges(self, value):
        for p in self._charges:
            filtered = [q for q in p.auxiliary_accounts if q != self]
            self._charges._auxiliary_accounts = filtered
        for r in value:
            if self not in r._auxiliary_accounts:
                r._auxiliary_accounts.append(self)
        self._charges = value
            
    charges = property(get_charges, set_charges)
    
    def add_charges(self, *charges):
        for obj in charges:
            if self not in obj._auxiliary_accounts:
                obj._auxiliary_accounts.append(self)
            self._charges.append(obj)
        
    def remove_charges(self, *charges):
        for obj in charges:
            if self in obj._auxiliary_accounts:
                obj._auxiliary_accounts.remove(self)
            self._charges.remove(obj)
    # >>> charges



class AccountingUnit(Element):
    """ Unit for accounting; use either 'energyUnit' or 'currencyUnit' to specify the unit for 'value'.
    """
    # <<< accounting_unit
    # @generated
    def __init__(self, value=0.0, multiplier='micro', energy_unit=0.0, monetary_unit='rur', **kw_args):
        """ Initialises a new 'AccountingUnit' instance.
        """
        # Value expressed in applicable units. 
        self.value = value
        # Multiplier for the 'energyUnit' or 'monetaryUnit'. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.multiplier = multiplier
        # Unit of service. 
        self.energy_unit = energy_unit
        # Unit of currency. Values are: "rur", "inr", "cad", "dkk", "cny", "usd", "sek", "aud", "jpy", "gbp", "eur", "nok", "chf", "other"
        self.monetary_unit = monetary_unit
        

        super(AccountingUnit, self).__init__(**kw_args)
    # >>> accounting_unit
        


class AccountMovement(Element):
    """ Credit/debit movements for an account.
    """
    # <<< account_movement
    # @generated
    def __init__(self, reason='', amount=0.0, date_time='', **kw_args):
        """ Initialises a new 'AccountMovement' instance.
        """
        # Reason for credit/debit transaction on an account. Example: payment received/arrears interest levied. 
        self.reason = reason
        # Amount that was credited to/debited from an account. For example: payment received/interest charge on arrears. 
        self.amount = amount
        # Date and time when the credit/debit transaction was performed. 
        self.date_time = date_time
        

        super(AccountMovement, self).__init__(**kw_args)
    # >>> account_movement
        


class Shift(IdentifiedObject):
    """ Generally referring to a period of operation or work performed. Whether shift is open/closed can be derived from attributes 'activiryInterval.start' and 'activityInterval.end'. The grand total for receipts (i.e., cumulative total of all actual receipted amounts during this shift; bankable + non-bankable; excludes rounding error totals) can be derived from Receipt attributes: =sum(Receipt.receiptAmount) ; includes bankable and non-bankable receipts. Must also reconcile against: =sum(receiptsGrandTotalBankable + receiptsGrandTotalNonBankable). must also reconcile against ReceiptSummary: =sum(ReceiptSummary.receiptsTotal). The attributes with 'GrandTotal' defined in this class may need to be used when the source data is periodically flushed from the system and then these cannot be derived.
    """
    # <<< shift
    # @generated
    def __init__(self, transactions_grand_total_rounding=0.0, transactions_grand_total=0.0, receipts_grand_total_bankable=0.0, receipts_grand_total_non_bankable=0.0, receipts_grand_total_rounding=0.0, activity_interval=None, receipt_summaries=None, transaction_summaries=None, **kw_args):
        """ Initialises a new 'Shift' instance.
        """
        # Cumulative amount in error due to process rounding not reflected in transactionsGandTotal. Values are obtained from Transaction attributes: =sum(Transaction.transactionRounding). 
        self.transactions_grand_total_rounding = transactions_grand_total_rounding
        # Cumulative total of transacted amounts during this shift. Values are obtained from Transaction attributes: =sum(Transaction.transactionAmount). It must also reconcile against TransactionSummary: =sum(TransactionSummary.transactionsTotal). 
        self.transactions_grand_total = transactions_grand_total
        # Total of amounts receipted during this shift that can be manually banked (cash and cheques for example). Values are obtained from Receipt attributes: =sum(Receipt.receiptAmount) for all Receipt.bankable = true. 
        self.receipts_grand_total_bankable = receipts_grand_total_bankable
        # Total of amounts receipted during this shift that cannot be manually banked (card payments for example). Values are obtained from Receipt attributes: =sum(Receipt.receiptAmount) for all Receipt.bankable = false. 
        self.receipts_grand_total_non_bankable = receipts_grand_total_non_bankable
        # Cumulative amount in error due to process rounding not reflected in receiptsGrandTotal. Values are obtained from Receipt attributes: =sum(Receipt.receiptRounding). 
        self.receipts_grand_total_rounding = receipts_grand_total_rounding
        
        self.activity_interval = activity_interval
        self._receipt_summaries = []
        if receipt_summaries is None:
            self.receipt_summaries = []
        else:
            self.receipt_summaries = receipt_summaries
        self._transaction_summaries = []
        if transaction_summaries is None:
            self.transaction_summaries = []
        else:
            self.transaction_summaries = transaction_summaries

        super(Shift, self).__init__(**kw_args)
    # >>> shift
        
    # <<< activity_interval
    # @generated
    # Interval for activity of this shift.
    activity_interval = None
    # >>> activity_interval

    # <<< receipt_summaries
    # @generated
    def get_receipt_summaries(self):
        """ All receipt summaries for this shift.
        """
        return self._receipt_summaries

    def set_receipt_summaries(self, value):
        for x in self._receipt_summaries:
            x.shift = None
        for y in value:
            y.shift = self
        self._receipt_summaries = value
            
    receipt_summaries = property(get_receipt_summaries, set_receipt_summaries)
    
    def add_receipt_summaries(self, *receipt_summaries):
        for obj in receipt_summaries:
            obj._shift = self
            if obj not in self._receipt_summaries:
                self._receipt_summaries.append(obj)
        
    def remove_receipt_summaries(self, *receipt_summaries):
        for obj in receipt_summaries:
            obj._shift = None
            self._receipt_summaries.remove(obj)
    # >>> receipt_summaries

    # <<< transaction_summaries
    # @generated
    def get_transaction_summaries(self):
        """ All transaction summaries recorded for this shift.
        """
        return self._transaction_summaries

    def set_transaction_summaries(self, value):
        for x in self._transaction_summaries:
            x.shift = None
        for y in value:
            y.shift = self
        self._transaction_summaries = value
            
    transaction_summaries = property(get_transaction_summaries, set_transaction_summaries)
    
    def add_transaction_summaries(self, *transaction_summaries):
        for obj in transaction_summaries:
            obj._shift = self
            if obj not in self._transaction_summaries:
                self._transaction_summaries.append(obj)
        
    def remove_transaction_summaries(self, *transaction_summaries):
        for obj in transaction_summaries:
            obj._shift = None
            self._transaction_summaries.remove(obj)
    # >>> transaction_summaries



class LineDetail(Element):
    """ Details on an amount line, with rounding, date and note.
    """
    # <<< line_detail
    # @generated
    def __init__(self, rounding=0.0, note='', date_time='', amount=0.0, **kw_args):
        """ Initialises a new 'LineDetail' instance.
        """
        # Totalised monetary value of all errors due to process rounding or truncating that is not reflected in 'amount'. 
        self.rounding = rounding
        # Free format note relevant to this line. 
        self.note = note
        # Date and time when this line was created in the application process. 
        self.date_time = date_time
        # Amount for this line item. 
        self.amount = amount
        

        super(LineDetail, self).__init__(**kw_args)
    # >>> line_detail
        


class Tender(IdentifiedObject):
    """ Tender is what is 'offered' by the customer towards making a payment and is often more than the required payment (hence the need for 'change'). The payment is thus that part of the Tender that goes towards settlement of a particular transaction. Tender is modelled as an aggregation of Cheque and Card. Both these tender types can exist in a single tender bid thus 'accountHolderName' must exist separately in each of Cheque and Card as each could have a different account holder name.
    """
    # <<< tender
    # @generated
    def __init__(self, amount=0.0, change=0.0, kind='cheque', receipt=None, card=None, cheque=None, **kw_args):
        """ Initialises a new 'Tender' instance.
        """
        # Amount tendered by customer. 
        self.amount = amount
        # Difference between amount tendered by customer and the amount charged by point of sale. 
        self.change = change
        # Kind of tender from customer. Values are: "cheque", "cash", "card", "other", "unspecified"
        self.kind = kind
        
        self._receipt = None
        self.receipt = receipt
        self._card = None
        self.card = card
        self._cheque = None
        self.cheque = cheque

        super(Tender, self).__init__(**kw_args)
    # >>> tender
        
    # <<< receipt
    # @generated
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
            if self not in self._receipt._tenders:
                self._receipt._tenders.append(self)

    receipt = property(get_receipt, set_receipt)
    # >>> receipt

    # <<< card
    # @generated
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
    # >>> card

    # <<< cheque
    # @generated
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
    # >>> cheque



class AuxiliaryAgreement(Agreement):
    """ An ad-hoc auxiliary account agreement associated with a customer agreement, not part of the customer's account, but typically subject to formal agreement between customer and supplier (utility). Typically this is used to collect revenue owing by the customer for other services or arrears accrued with the utility for other services. It is typically linked to a prepaid token purchase transaction, thus forcing the customer to make a payment towards settlement of the auxiliary account balance whenever he needs to purchase a prepaid token for electricity. The present status of AuxiliaryAgreement can be defined in the context of the utility's business rules, for example: enabled, disabled, pending, over recovered, under recovered, written off, etc.
    """
    # <<< auxiliary_agreement
    # @generated
    def __init__(self, aux_ref='', vend_portion=0.0, vend_portion_arrear=0.0, fixed_amount=0.0, arrears_interest=0.0, min_amount=0.0, aux_cycle='', sub_category='', pay_cycle='', aux_priority_code='', auxiliary_accounts=None, customer_agreement=None, **kw_args):
        """ Initialises a new 'AuxiliaryAgreement' instance.
        """
        # A local reference to this AuxiliaryAgreement defined in the context of the implementation and not related to IdentifiedObject.mRID. 
        self.aux_ref = aux_ref
        # The percentage of the transaction amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement when payments are not in arrears. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
        self.vend_portion = vend_portion
        # The percentage of the transaction amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement when payments are in arrears. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
        self.vend_portion_arrear = vend_portion_arrear
        # The fixed amount that must be collected from each vending transaction towards settlement of this AuxiliaryAgreement. Note that there may be multiple tokens vended per vending transaction, but this is not relevant. 
        self.fixed_amount = fixed_amount
        # The interest per annum to be charged prorata on AuxiliaryAccount.dueArrears at the end of each payCycle. 
        self.arrears_interest = arrears_interest
        # The minimum amount that must be paid at any transaction towards settling this AuxiliryAgreement or reducing the balance. 
        self.min_amount = min_amount
        # The frequency for automatically recurring auxiliary charges, where AuxiliaryAccount.initialCharge is recursively added to AuxiliaryAccount.dueCurrent at the start of each auxCycle. For example: on a specified date and time; hourly; daily; weekly; monthly; 3-monthly; 6-monthly; 12-monthly; etc. 
        self.aux_cycle = aux_cycle
        # Sub-category of this AuxiliaryAgreement as sub-classification of the inherited 'category'. 
        self.sub_category = sub_category
        # The contractually expected payment frequency (by the customer). Examples are: ad-hoc; on specified date; hourly, daily, weekly, monthly. etc. 
        self.pay_cycle = pay_cycle
        # The coded priority indicating the priority that this AuxiliaryAgreement has above other AuxiliaryAgreements (associated with the same customer agreement) when it comes to competing for settlement from a payment transaction or token purchase. 
        self.aux_priority_code = aux_priority_code
        
        self._auxiliary_accounts = []
        if auxiliary_accounts is None:
            self.auxiliary_accounts = []
        else:
            self.auxiliary_accounts = auxiliary_accounts
        self._customer_agreement = None
        self.customer_agreement = customer_agreement

        super(AuxiliaryAgreement, self).__init__(**kw_args)
    # >>> auxiliary_agreement
        
    # <<< auxiliary_accounts
    # @generated
    def get_auxiliary_accounts(self):
        """ All auxiliary accounts regulated by this agreement.
        """
        return self._auxiliary_accounts

    def set_auxiliary_accounts(self, value):
        for x in self._auxiliary_accounts:
            x.auxiliary_agreement = None
        for y in value:
            y.auxiliary_agreement = self
        self._auxiliary_accounts = value
            
    auxiliary_accounts = property(get_auxiliary_accounts, set_auxiliary_accounts)
    
    def add_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            obj._auxiliary_agreement = self
            if obj not in self._auxiliary_accounts:
                self._auxiliary_accounts.append(obj)
        
    def remove_auxiliary_accounts(self, *auxiliary_accounts):
        for obj in auxiliary_accounts:
            obj._auxiliary_agreement = None
            self._auxiliary_accounts.remove(obj)
    # >>> auxiliary_accounts

    # <<< customer_agreement
    # @generated
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
            if self not in self._customer_agreement._auxiliary_agreements:
                self._customer_agreement._auxiliary_agreements.append(self)

    customer_agreement = property(get_customer_agreement, set_customer_agreement)
    # >>> customer_agreement



class VendorShift(Shift):
    """ The operating shift for a vendor during which he may transact against the merchant's account. It aggregates transactions and receipts during the shift and periodically debits a merchant account. The totals in VendorShift should always = sum of totals aggregated in all cashier shifts that were open under the particular vendor shift.
    """
    # <<< vendor_shift
    # @generated
    def __init__(self, merchant_debit_amount=0.0, posted=False, transactions=None, receipts=None, vendor=None, merchant_account=None, **kw_args):
        """ Initialises a new 'VendorShift' instance.
        """
        # The amount that is to be debited from the merchant account for this vendor shift. This amount reflects the sum(PaymentTransaction.transactionAmount). 
        self.merchant_debit_amount = merchant_debit_amount
        # = true if merchantDebitAmount has been debited from MerchantAccount; typically happens at the end of VendorShift when it closes. 
        self.posted = posted
        
        self._transactions = []
        if transactions is None:
            self.transactions = []
        else:
            self.transactions = transactions
        self._receipts = []
        if receipts is None:
            self.receipts = []
        else:
            self.receipts = receipts
        self._vendor = None
        self.vendor = vendor
        self._merchant_account = None
        self.merchant_account = merchant_account

        super(VendorShift, self).__init__(**kw_args)
    # >>> vendor_shift
        
    # <<< transactions
    # @generated
    def get_transactions(self):
        """ 
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x.vendor_shift = None
        for y in value:
            y.vendor_shift = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._vendor_shift = self
            if obj not in self._transactions:
                self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._vendor_shift = None
            self._transactions.remove(obj)
    # >>> transactions

    # <<< receipts
    # @generated
    def get_receipts(self):
        """ 
        """
        return self._receipts

    def set_receipts(self, value):
        for x in self._receipts:
            x.vendor_shift = None
        for y in value:
            y.vendor_shift = self
        self._receipts = value
            
    receipts = property(get_receipts, set_receipts)
    
    def add_receipts(self, *receipts):
        for obj in receipts:
            obj._vendor_shift = self
            if obj not in self._receipts:
                self._receipts.append(obj)
        
    def remove_receipts(self, *receipts):
        for obj in receipts:
            obj._vendor_shift = None
            self._receipts.remove(obj)
    # >>> receipts

    # <<< vendor
    # @generated
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
            if self not in self._vendor._vendor_shifts:
                self._vendor._vendor_shifts.append(self)

    vendor = property(get_vendor, set_vendor)
    # >>> vendor

    # <<< merchant_account
    # @generated
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
            if self not in self._merchant_account._vendor_shifts:
                self._merchant_account._vendor_shifts.append(self)

    merchant_account = property(get_merchant_account, set_merchant_account)
    # >>> merchant_account



class CashierShift(Shift):
    """ The operating shift for a cashier, during which he may transact against the CashierShift, subject to VendorShift being open.
    """
    # <<< cashier_shift
    # @generated
    def __init__(self, cash_float=0.0, transactions=None, point_of_sale=None, cashier=None, receipts=None, **kw_args):
        """ Initialises a new 'CashierShift' instance.
        """
        # The amount of cash that the cashier brings with him to start his shift and that he will take away at the end of his shift; i.e. the cash float does not get banked. 
        self.cash_float = cash_float
        
        self._transactions = []
        if transactions is None:
            self.transactions = []
        else:
            self.transactions = transactions
        self._point_of_sale = None
        self.point_of_sale = point_of_sale
        self._cashier = None
        self.cashier = cashier
        self._receipts = []
        if receipts is None:
            self.receipts = []
        else:
            self.receipts = receipts

        super(CashierShift, self).__init__(**kw_args)
    # >>> cashier_shift
        
    # <<< transactions
    # @generated
    def get_transactions(self):
        """ 
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x.cashier_shift = None
        for y in value:
            y.cashier_shift = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._cashier_shift = self
            if obj not in self._transactions:
                self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._cashier_shift = None
            self._transactions.remove(obj)
    # >>> transactions

    # <<< point_of_sale
    # @generated
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
            if self not in self._point_of_sale._cashier_shifts:
                self._point_of_sale._cashier_shifts.append(self)

    point_of_sale = property(get_point_of_sale, set_point_of_sale)
    # >>> point_of_sale

    # <<< cashier
    # @generated
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
            if self not in self._cashier._cashier_shifts:
                self._cashier._cashier_shifts.append(self)

    cashier = property(get_cashier, set_cashier)
    # >>> cashier

    # <<< receipts
    # @generated
    def get_receipts(self):
        """ All Receipts recorded for this Shift.
        """
        return self._receipts

    def set_receipts(self, value):
        for x in self._receipts:
            x.cashier_shift = None
        for y in value:
            y.cashier_shift = self
        self._receipts = value
            
    receipts = property(get_receipts, set_receipts)
    
    def add_receipts(self, *receipts):
        for obj in receipts:
            obj._cashier_shift = self
            if obj not in self._receipts:
                self._receipts.append(obj)
        
    def remove_receipts(self, *receipts):
        for obj in receipts:
            obj._cashier_shift = None
            self._receipts.remove(obj)
    # >>> receipts



# <<< payment_metering
# @generated
# >>> payment_metering

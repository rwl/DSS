# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14 import Root
from cim14.iec61968.common import Agreement
from cim14.iec61968.common import Organisation
from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

class PointOfSale(IdentifiedObject):
    """ Logical point where transactions take place with operational interaction between Cashier and the payment system; in certain cases PointOfSale interacts directly with the end customer, in which case Cashier might not be a real person: for example a self-service kiosk or over the internet.
    """
    # Local description for where this pont of sale is physically located. 
    location = ''

    # All shifts this point of sale operated in.
    cashier_shifts = []

    # All Tokens sold or dispensed at this PointOfSale.
    tokens = []

    # Vendor that controls this PointOfSale.
    vendor = None

    # <<< point_of_sale
    # @generated
    def __init__(self, location='', cashier_shifts=[], tokens=[], vendor=None, **kw_args):
        """ Initialises a new 'PointOfSale' instance.
        """
        self.location = location
        self.cashier_shifts = cashier_shifts
        self.tokens = tokens
        self.vendor = vendor

        super(PointOfSale, self).__init__(**kw_args)
    # >>> point_of_sale


class Cheque(Root):
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

    # Payment tender the cheque is being used for.
    tender = None

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
        self.tender = tender

        super(Cheque, self).__init__(**kw_args)
    # >>> cheque


class Vendor(IdentifiedObject):
    """ The entity that owns PointOfSale and contracts with Cashier to receipt payments and vend tokens using the payment system. Vendor has a private contract with and is managed by Merchant who is a type of Organisation. Vendor is accountable to Merchant for revenue collected, who is in turn accountable to Supplier.
    """
    # Merchant account against which this vendor sells tokens or recept payments.
    merchant_account = None

    # All Cachiers managed by this Vendor.
    cashiers = []

    # All BankStatements reflecting deposits made by this Vendor.
    bank_statements = []

    # All points of sale this Vendor controls.
    point_of_sales = []

    # All vendor shifts opened and owned by this vendor.
    vendor_shifts = []

    # <<< vendor
    # @generated
    def __init__(self, merchant_account=None, cashiers=[], bank_statements=[], point_of_sales=[], vendor_shifts=[], **kw_args):
        """ Initialises a new 'Vendor' instance.
        """
        self.merchant_account = merchant_account
        self.cashiers = cashiers
        self.bank_statements = bank_statements
        self.point_of_sales = point_of_sales
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

    # Cashier shift during which this transaction was recorded.
    cashier_shift = None

    # Vendor shift during which this transaction was recorded.
    vendor_shift = None

    # Auxiliary account for this payment transaction.
    auxiliary_account = None

    # Customer account for this payment transaction.
    customer_account = None

    # Pricing structure applicable for this transaction.
    pricing_structure = None

    # The receipted payment for which this transaction has been recorded.
    receipt = None

    # All snapshots of meter parameters recorded at the time of this transaction. Use 'name' and 'value.value' attributes to specify name and value of a parameter from meter.
    user_attributes = []

    # Meter asset for this vending transaction.
    meter_asset = None

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
        self.cashier_shift = cashier_shift
        self.vendor_shift = vendor_shift
        self.auxiliary_account = auxiliary_account
        self.customer_account = customer_account
        self.pricing_structure = pricing_structure
        self.receipt = receipt
        self.user_attributes = user_attributes
        self.meter_asset = meter_asset

        super(Transaction, self).__init__(**kw_args)
    # >>> transaction


class ConsumptionTariffInterval(Root):
    """ One of a sequence of intervals defined in terms of consumption quantity of a service such as electricity, water, gas, etc. It is typically used in association with TariffProfile to define the steps or blocks in a step tariff structure, where startValue simultaneously defines the entry value of this step and the closing value of the previous step. Where consumption is &gt;= startValue it falls within this interval and where consumption is &lt; startValue it falls within the previous interval.
    """
    # The lowest level of consumption that defines the starting point of this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
    start_value = ''

    # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
    sequence_number = 0

    # All charges used to define this consumption tariff interval.
    charges = []

    # All tariff profiles defined by this consumption tariff interval.
    tariff_profiles = []

    # <<< consumption_tariff_interval
    # @generated
    def __init__(self, start_value='', sequence_number=0, charges=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'ConsumptionTariffInterval' instance.
        """
        self.start_value = start_value
        self.sequence_number = sequence_number
        self.charges = charges
        self.tariff_profiles = tariff_profiles

        super(ConsumptionTariffInterval, self).__init__(**kw_args)
    # >>> consumption_tariff_interval


class BankAccountDetail(Root):
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
    # All shifts operated by this cashier.
    cashier_shifts = []

    # Vendor that manages this Cachier.
    vendor = None

    electronic_addresses = []

    # <<< cashier
    # @generated
    def __init__(self, cashier_shifts=[], vendor=None, electronic_addresses=[], **kw_args):
        """ Initialises a new 'Cashier' instance.
        """
        self.cashier_shifts = cashier_shifts
        self.vendor = vendor
        self.electronic_addresses = electronic_addresses

        super(Cashier, self).__init__(**kw_args)
    # >>> cashier


class MerchantAgreement(Agreement):
    """ A formal controlling contractual agreement between Supplier and Merchant, in terms of which Merchant is authorised to vend tokens and receipt payments on behalf of Supplier. Merchant is accountable to Supplier for revenue collected at PointOfSale.
    """
    # All merchant accounts instantiated as a result of this merchant agreement.
    merchant_accounts = []

    # <<< merchant_agreement
    # @generated
    def __init__(self, merchant_accounts=[], **kw_args):
        """ Initialises a new 'MerchantAgreement' instance.
        """
        self.merchant_accounts = merchant_accounts

        super(MerchantAgreement, self).__init__(**kw_args)
    # >>> merchant_agreement


class TimeTariffInterval(Root):
    """ One of a sequence of time intervals defined in terms of real time. It is typically used in association with TariffProfile to define the intervals in a time of use tariff structure, where startDateTime simultaneously determines the starting point of this interval and the ending point of the previous interval.
    """
    # A reatime marker that defines the starting time (typically it is the time of day) for this interval. The interval extends to the start of the next interval or until it is reset to the start of the first interval by TariffProfile.tariffCycle. 
    start_date_time = ''

    # A sequential reference that defines the identity of this interval and its relative position with respect to other intervals in a sequence of intervals. 
    sequence_number = 0

    # All charges used to define this time tariff interval.
    charges = []

    # All tariff profiles defined by this time tariff interval.
    tariff_profiles = []

    # <<< time_tariff_interval
    # @generated
    def __init__(self, start_date_time='', sequence_number=0, charges=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'TimeTariffInterval' instance.
        """
        self.start_date_time = start_date_time
        self.sequence_number = sequence_number
        self.charges = charges
        self.tariff_profiles = tariff_profiles

        super(TimeTariffInterval, self).__init__(**kw_args)
    # >>> time_tariff_interval


class Transactor(IdentifiedObject):
    """ The entity that ultimately executes the transaction and who is in control of the process; typically this is embodied in secure software running on a server that may employ secure hardware encryption devices for secure transaction processing.
    """
    # All merchant accounts registered with this transactor.
    merchant_accounts = []

    # <<< transactor
    # @generated
    def __init__(self, merchant_accounts=[], **kw_args):
        """ Initialises a new 'Transactor' instance.
        """
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

    # All service delivery points this service supplier utilises to deliver a service.
    service_delivery_points = []

    # All BackAccounts this ServiceSupplier owns.
    bank_accounts = []

    # All customer agreements of this service supplier.
    customer_agreements = []

    # <<< service_supplier
    # @generated
    def __init__(self, kind='retailer', issuer_identification_number='', service_delivery_points=[], bank_accounts=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'ServiceSupplier' instance.
        """
        self.kind = kind
        self.issuer_identification_number = issuer_identification_number
        self.service_delivery_points = service_delivery_points
        self.bank_accounts = bank_accounts
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

    # All transactions recorded for this receipted payment.
    transactions = []

    # Cashier shift during which this receipt was recorded.
    cashier_shift = None

    # Vendor shift during which this receipt was recorded.
    vendor_shift = None

    # All payments received in the form of tenders recorded by this receipt.
    tenders = []

    # <<< receipt
    # @generated
    def __init__(self, is_bankable=False, line=None, transactions=[], cashier_shift=None, vendor_shift=None, tenders=[], **kw_args):
        """ Initialises a new 'Receipt' instance.
        """
        self.is_bankable = is_bankable
        self.line = line
        self.transactions = transactions
        self.cashier_shift = cashier_shift
        self.vendor_shift = vendor_shift
        self.tenders = tenders

        super(Receipt, self).__init__(**kw_args)
    # >>> receipt


class Due(Root):
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

    # Tariff intervals to which this time-based charge must be levied.
    time_tariff_intervals = []

    parent_charge = None

    # All auxiliary accounts to which this charge must be levied.
    auxiliary_accounts = []

    # All sub-components of this complex charge.
    child_charges = []

    # Tariff intervals to which this consumption-based charge must be levied.
    consumption_tariff_intervals = []

    # <<< charge
    # @generated
    def __init__(self, kind='consumption_charge', variable_portion='', fixed_portion=None, time_tariff_intervals=[], parent_charge=None, auxiliary_accounts=[], child_charges=[], consumption_tariff_intervals=[], **kw_args):
        """ Initialises a new 'Charge' instance.
        """
        self.kind = kind
        self.variable_portion = variable_portion
        self.fixed_portion = fixed_portion
        self.time_tariff_intervals = time_tariff_intervals
        self.parent_charge = parent_charge
        self.auxiliary_accounts = auxiliary_accounts
        self.child_charges = child_charges
        self.consumption_tariff_intervals = consumption_tariff_intervals

        super(Charge, self).__init__(**kw_args)
    # >>> charge


class TariffProfile(Document):
    """ A schedule of charges; structure associated with Tariff that allows the definition of complex tarif structures such as step and time of use when used in conjunction with TimeTariffInterval and Charge. Inherited 'status.value' is defined in the context of the utility's business rules, for example: active, inactive, etc.
    """
    # The frequency at which the tariff charge schedule is repeated Examples are: once off on a specified date and time; hourly; daily; weekly; monthly; 3-monthly; 6-monthly; 12-monthly; etc. At the end of each cycle, the business rules are reset to start from the beginning again. 
    tariff_cycle = ''

    # All tariffs defined by this tariff profile.
    tariffs = []

    # All consumption tariff intervals used to define this tariff profile.
    consumption_tariff_intervals = []

    # All time tariff intervals used to define this tariff profile.
    time_tariff_intervals = []

    # <<< tariff_profile
    # @generated
    def __init__(self, tariff_cycle='', tariffs=[], consumption_tariff_intervals=[], time_tariff_intervals=[], **kw_args):
        """ Initialises a new 'TariffProfile' instance.
        """
        self.tariff_cycle = tariff_cycle
        self.tariffs = tariffs
        self.consumption_tariff_intervals = consumption_tariff_intervals
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

    # All vendors selling tokens or receipt payments against this merchant account.
    vendors = []

    bank_statements = []

    # All vendor shifts that operate on this merchant account.
    vendor_shifts = []

    # Merchant agreement that instantiated this merchant account.
    merchant_agreement = None

    # All transactors this merchant account is registered with.
    transactors = []

    # <<< merchant_account
    # @generated
    def __init__(self, current_balance='', provisional_balance='', vendors=[], bank_statements=[], vendor_shifts=[], merchant_agreement=None, transactors=[], **kw_args):
        """ Initialises a new 'MerchantAccount' instance.
        """
        self.current_balance = current_balance
        self.provisional_balance = provisional_balance
        self.vendors = vendors
        self.bank_statements = bank_statements
        self.vendor_shifts = vendor_shifts
        self.merchant_agreement = merchant_agreement
        self.transactors = transactors

        super(MerchantAccount, self).__init__(**kw_args)
    # >>> merchant_account


class Card(Root):
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

    # Payment tender this card is being used for.
    tender = None

    # <<< card
    # @generated
    def __init__(self, expiry_date='', pan='', cv_number='', account_holder_name='', tender=None, **kw_args):
        """ Initialises a new 'Card' instance.
        """
        self.expiry_date = expiry_date
        self.pan = pan
        self.cv_number = cv_number
        self.account_holder_name = account_holder_name
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

    # All payments against this account.
    payment_transactions = []

    # Auxiliary agreement regulating this account.
    auxiliary_agreement = None

    # All charges levied on this account.
    charges = []

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
        self.payment_transactions = payment_transactions
        self.auxiliary_agreement = auxiliary_agreement
        self.charges = charges

        super(AuxiliaryAccount, self).__init__(**kw_args)
    # >>> auxiliary_account


class AccountingUnit(Root):
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


class AccountMovement(Root):
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

    # All receipt summaries for this shift.
    receipt_summaries = []

    # All transaction summaries recorded for this shift.
    transaction_summaries = []

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
        self.receipt_summaries = receipt_summaries
        self.transaction_summaries = transaction_summaries

        super(Shift, self).__init__(**kw_args)
    # >>> shift


class LineDetail(Root):
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

    # Receipt that recorded this receiving of a payment in the form of tenders.
    receipt = None

    # Card used to tender payment.
    card = None

    # Cheque used to tender payment.
    cheque = None

    # <<< tender
    # @generated
    def __init__(self, amount='', change='', kind='cheque', receipt=None, card=None, cheque=None, **kw_args):
        """ Initialises a new 'Tender' instance.
        """
        self.amount = amount
        self.change = change
        self.kind = kind
        self.receipt = receipt
        self.card = card
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

    # All auxiliary accounts regulated by this agreement.
    auxiliary_accounts = []

    # Customer agreement this (non-service related) auxiliary agreement refers to.
    customer_agreement = None

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
        self.auxiliary_accounts = auxiliary_accounts
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

    transactions = []

    receipts = []

    # Vendor that opens and owns this vendor shift.
    vendor = None

    # Merchant acocunt this vendor shift periodically debits (based on aggreated transactions).
    merchant_account = None

    # <<< vendor_shift
    # @generated
    def __init__(self, merchant_debit_amount='', posted=False, transactions=[], receipts=[], vendor=None, merchant_account=None, **kw_args):
        """ Initialises a new 'VendorShift' instance.
        """
        self.merchant_debit_amount = merchant_debit_amount
        self.posted = posted
        self.transactions = transactions
        self.receipts = receipts
        self.vendor = vendor
        self.merchant_account = merchant_account

        super(VendorShift, self).__init__(**kw_args)
    # >>> vendor_shift


class CashierShift(Shift):
    """ The operating shift for a cashier, during which he may transact against the CashierShift, subject to VendorShift being open.
    """
    # The amount of cash that the cashier brings with him to start his shift and that he will take away at the end of his shift; i.e. the cash float does not get banked. 
    cash_float = ''

    transactions = []

    # Point of sale that is in operation during this shift.
    point_of_sale = None

    # Cashier operating this shift.
    cashier = None

    # All Receipts recorded for this Shift.
    receipts = []

    # <<< cashier_shift
    # @generated
    def __init__(self, cash_float='', transactions=[], point_of_sale=None, cashier=None, receipts=[], **kw_args):
        """ Initialises a new 'CashierShift' instance.
        """
        self.cash_float = cash_float
        self.transactions = transactions
        self.point_of_sale = point_of_sale
        self.cashier = cashier
        self.receipts = receipts

        super(CashierShift, self).__init__(**kw_args)
    # >>> cashier_shift


# <<< payment_metering
# @generated
# >>> payment_metering

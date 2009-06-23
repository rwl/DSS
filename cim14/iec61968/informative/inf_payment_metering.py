# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element
from cim14.iec61968.common import Document
from cim14.iec61968.metering import DeviceFunction
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Agreement
from cim14.iec61968.common import Organisation

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfPaymentMetering"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfPaymentMetering"

class TransactionSummary(Element):
    """ The record of detail of payment transactions pertaining to one shift of operation (one record per 'transactionKind').
    """
    # 'Transaction.kind' for which 'transactionsTotal' is given. Values are: "token_sale_payment", "token_free_issue", "transaction_reversal", "other", "token_exchange", "token_grant", "token_cancellation", "auxiliary_charge_payment", "account_payment", "diverse_payment", "service_charge_payment", "tax_charge_payment", "meter_configuration_token"
    transaction_kind = 'token_sale_payment'

    # Totalised amount transacted during the shift for this specific 'transactionKind', i.e., sum of 'Transaction.line.amount' per 'Transaction.kind'. Cumulative amount of rounding errors due to process rounding not reflected in 'LineDetail.amount' for 'transactionKind', i.e., sum of 'Transaction.line.rounding' per 'Transaction.kind'.
    line = None

    # Shift to which this summary applies.
    shift = None

    # <<< transaction_summary
    # @generated
    def __init__(self, transaction_kind='token_sale_payment', line=None, shift=None, **kw_args):
        """ Initialises a new 'TransactionSummary' instance.
        """
        self.transaction_kind = transaction_kind
        self.line = line
        self.shift = shift

        super(TransactionSummary, self).__init__(**kw_args)
    # >>> transaction_summary


class BankStatement(Document):
    """ A type of Document that records bank deposits made by Vendor of revenue collected at PointOfSale.
    """
    # The amount that is deposited into the bank via BankAccount. 
    deposit_amount = ''

    # The date and time the deposit is made. 
    deposit_date_time = ''

    # The amount on this statement that is to be credited to MerchantAccount. 
    merchant_credit_amount = ''

    # True if mechantCreditAmount has been cerdited to MerchantAccount; typically happens when bank statement details are captured into payment system. 
    posted = False

    merchant_account = None

    # The Vendor that made this BankStatement (by making deposit).
    vendor = None

    # BankAccount that generated this bank statement.
    bank_account = None

    # <<< bank_statement
    # @generated
    def __init__(self, deposit_amount='', deposit_date_time='', merchant_credit_amount='', posted=False, merchant_account=None, vendor=None, bank_account=None, **kw_args):
        """ Initialises a new 'BankStatement' instance.
        """
        self.deposit_amount = deposit_amount
        self.deposit_date_time = deposit_date_time
        self.merchant_credit_amount = merchant_credit_amount
        self.posted = posted
        self.merchant_account = merchant_account
        self.vendor = vendor
        self.bank_account = bank_account

        super(BankStatement, self).__init__(**kw_args)
    # >>> bank_statement


class SDPAccountingFunction(DeviceFunction):
    """ Service delivery point accounting function, particularly for payment meter.
    """
    # The value is the balance of the sum of credits minus the sum of charges from the CreditRegisters and the ChargeRegisters. The sum might be complex function. The units are either in currency units or service units, depending on the value of accountingMode.
    available_credit = None

    # The value is a predefined set point for the level of the availableCredit to reach when the service will be interrupted. In the case of a prepayment meter the interruption is typically implemented by means of an electro-mechanical switch and the value is typically set = 0. The units are either in currency units or service units, depending on the value of accountingMode.
    credit_expiry_level = None

    # The value is a predefined set point for the level of the availableCredit to reach when a warning will be indicated to the customer. It serves to warn the customer that it is time to purchase more credit in the case of a prepayment meter implementation. The units are either in currency units or service units, depending on the value of accountingMode.
    low_credit_warning_level = None

    charge_registers = []

    credit_registers = []

    service_kind = None

    # <<< sdpaccounting_function
    # @generated
    def __init__(self, available_credit=None, credit_expiry_level=None, low_credit_warning_level=None, charge_registers=[], credit_registers=[], service_kind=None, **kw_args):
        """ Initialises a new 'SDPAccountingFunction' instance.
        """
        self.available_credit = available_credit
        self.credit_expiry_level = credit_expiry_level
        self.low_credit_warning_level = low_credit_warning_level
        self.charge_registers = charge_registers
        self.credit_registers = credit_registers
        self.service_kind = service_kind

        super(SDPAccountingFunction, self).__init__(**kw_args)
    # >>> sdpaccounting_function


class ReceiptSummary(Element):
    """ Record of detail of receipts pertaining to one shift of operation (one record per 'tenderKind').
    """
    # 'Tender.kind' for which 'receiptsTotal' is given. Values are: "cheque", "cash", "card", "other", "unspecified"
    tender_kind = 'cheque'

    # Totalised amount receipted during the shift for 'tenderKind', i.e., sum of ('Tender.amount' - 'Tender.change') per 'Tender.kind'.
    line = None

    # Shift for which this summary is given.
    shift = None

    # <<< receipt_summary
    # @generated
    def __init__(self, tender_kind='cheque', line=None, shift=None, **kw_args):
        """ Initialises a new 'ReceiptSummary' instance.
        """
        self.tender_kind = tender_kind
        self.line = line
        self.shift = shift

        super(ReceiptSummary, self).__init__(**kw_args)
    # >>> receipt_summary


class CreditRegister(IdentifiedObject):
    """ Accumulated credits transacted per CreditKind for a given function. There could be several of these registers, one for each CreditKind; depending on the application.
    """
    # Several different types of credit are typically implemented in the case of a prepayment meter.  For example: credit transferred by means of a token carrier, or credit advanced automatically inside the meter under certain conditions, or credit held in reserved to be released under emergency conditions, or credit granted by local authority as a basic life support mechanism and may be dispensed automatically by the meter under certain conditions or credit available under severe climate conditions such as during winter over a weekend. Values are: "reserve_credit", "lifeline_credit", "other", "grant_credit", "token_credit", "advance_credit"
    credit_kind = 'reserve_credit'

    # Credit amount in favour of the customer. The units are either in currency units or service units, depending on the value of 'AccountingUnit.accountingMode'.
    credit_amount = None

    sdpaccounting_function = None

    # <<< credit_register
    # @generated
    def __init__(self, credit_kind='reserve_credit', credit_amount=None, sdpaccounting_function=None, **kw_args):
        """ Initialises a new 'CreditRegister' instance.
        """
        self.credit_kind = credit_kind
        self.credit_amount = credit_amount
        self.sdpaccounting_function = sdpaccounting_function

        super(CreditRegister, self).__init__(**kw_args)
    # >>> credit_register


class TSPAgreement(Agreement):
    """ A contractual agreement between a supplier (utility) and a transaction service provider (a type of organisation) that governs the terms and conditions, under which authorisation the transaction service provider may process transactions on behalf of the supplier.
    """
    pass
    # <<< tspagreement
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'TSPAgreement' instance.
        """

        super(TSPAgreement, self).__init__(**kw_args)
    # >>> tspagreement


class Bank(Organisation):
    """ Organisation that is a commercial bank, agency, or other institution that offers a similar service.
    """
    # International bank account number defined in ISO 13616; for countries where IBAN is not in operation, the existing BIC or SWIFT codes may be used instead (see ISO 9362). 
    iban = ''

    # Bank identifier code as defined in ISO 9362; for use in countries wher IBAN is not yet in operation. 
    bic = ''

    # Codified reference to the particular branch of the bank where BankAccount is held. 
    branch_code = ''

    # All BankAccounts this Bank provides.
    bank_accounts = []

    # <<< bank
    # @generated
    def __init__(self, iban='', bic='', branch_code='', bank_accounts=[], **kw_args):
        """ Initialises a new 'Bank' instance.
        """
        self.iban = iban
        self.bic = bic
        self.branch_code = branch_code
        self.bank_accounts = bank_accounts

        super(Bank, self).__init__(**kw_args)
    # >>> bank


class ChargeRegister(IdentifiedObject):
    """ Accumulated charges transacted per ChargeKind for a given function. There could be several of these registers, one for each ChargeKind; depending on the application.
    """
    # Several different types of charges are typically implemented in the case of a prepayment meter. For example: a charge according to a tariff for consumption and possibly a demand component, or a charge for a debt that is loaded in the meter to be recovered on a time basis, or a standing charge to be levied at the end of each billing period, or a tax charge loaded in the meter to be recovered on a consumption basis or a time basis. Values are: "consumption_charge", "auxiliary_charge", "other", "tax_charge", "demand_charge"
    charge_kind = 'consumption_charge'

    # Charge amount in favour of the supplier. The units are either in currency units or service units, depending on the value of 'AccountingUnit.accountingMode'.
    charge_amount = None

    spaccounting_function = None

    # <<< charge_register
    # @generated
    def __init__(self, charge_kind='consumption_charge', charge_amount=None, spaccounting_function=None, **kw_args):
        """ Initialises a new 'ChargeRegister' instance.
        """
        self.charge_kind = charge_kind
        self.charge_amount = charge_amount
        self.spaccounting_function = spaccounting_function

        super(ChargeRegister, self).__init__(**kw_args)
    # >>> charge_register


class Token(IdentifiedObject):
    """ The token that is transferred to the payment meter.
    """
    # Free-format note relevant to this token. 
    comment = ''

    # Coded representation of the token that is transferred to the payment meter. 
    code = ''

    # PointOfSale tha sold or dispensed this Token.
    point_of_sale = None

    # <<< token
    # @generated
    def __init__(self, comment='', code='', point_of_sale=None, **kw_args):
        """ Initialises a new 'Token' instance.
        """
        self.comment = comment
        self.code = code
        self.point_of_sale = point_of_sale

        super(Token, self).__init__(**kw_args)
    # >>> token


# <<< inf_payment_metering
# @generated
# >>> inf_payment_metering

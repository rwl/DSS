# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Agreement
from cim14.iec61968.common import Document
from cim14.iec61970.core import Curve

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfCustomers"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfCustomers"

class PhaseLoad(IdentifiedObject):
    """ Model's load with the unified characteristics. It can be one phase of a multi-phase load and also can be a three-phase load with unified characteristics. The aggregate relationship between DistributionLoad and PhaseLoad allows a distribution load to have any type configuration like single phase, two-phase or three-phase.
    """
    # Percentage of the reactive power of the load which is constant. 
    q_constant_power_pct = ''

    # Percentage of the active power of the load which has constant impedance. 
    p_constant_impedance_pct = ''

    # Voltage dependence of the reactive part of the qConstantCurrentPct. 
    q_volt_dependance_factor = 0.0

    # Voltage dependence of the active part of the pConstantCurrentPct. 
    p_volt_dependance_factor = 0.0

    # Percentage of the reactive power of the load which has constant impedance. 
    q_constant_impedance_pct = ''

    # Percentage of the active power of the load which is constant. 
    p_constant_power_pct = ''

    # Percentage of the reactive power of the load which has constant current. 
    q_constant_current_pct = ''

    # Percentage of the active power of the load which has constant current. 
    p_constant_current_pct = ''

    energy_consumer = None

    # <<< phase_load
    # @generated
    def __init__(self, q_constant_power_pct='', p_constant_impedance_pct='', q_volt_dependance_factor=0.0, p_volt_dependance_factor=0.0, q_constant_impedance_pct='', p_constant_power_pct='', q_constant_current_pct='', p_constant_current_pct='', energy_consumer=None, **kw_args):
        """ Initialises a new 'PhaseLoad' instance.
        """
        self.q_constant_power_pct = q_constant_power_pct
        self.p_constant_impedance_pct = p_constant_impedance_pct
        self.q_volt_dependance_factor = q_volt_dependance_factor
        self.p_volt_dependance_factor = p_volt_dependance_factor
        self.q_constant_impedance_pct = q_constant_impedance_pct
        self.p_constant_power_pct = p_constant_power_pct
        self.q_constant_current_pct = q_constant_current_pct
        self.p_constant_current_pct = p_constant_current_pct
        self.energy_consumer = energy_consumer

        super(PhaseLoad, self).__init__(**kw_args)
    # >>> phase_load


class ExternalCustomerAgreement(Agreement):
    """ A type of customer agreement involving an external agency. For example, a customer may form a contracts with an Energy Service Supplier if Direct Access is permitted.
    """
    pass
    # <<< external_customer_agreement
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ExternalCustomerAgreement' instance.
        """

        super(ExternalCustomerAgreement, self).__init__(**kw_args)
    # >>> external_customer_agreement


class CustomerBillingInfo(Document):
    """ The creation of the monthly customer billing statements is the method employed to notify Customers of charges, adjustments and credits applied to their account for Services and Products. The actuall billing occurs through an ErpInvoice. The CustomerBillingInfo includes information from the payment, collection, meter reading, installed meter, service, site, customer, customer account, customer agreement, services and pricing subject areas. Each component price shows up as a separate line item on the ErpInvoice. The Customer Billing Statement may include collection and account messages, marketing/civic event messages and bill inserts. One Customer Billing Statement is produced for all Agreements under a CustomerAccount per billing cycle date defined in 'CustomerAccount.billingCycle'. The history of CustomerBillingInfo, Invoices and Payments is to be maintained in associated ActivityRecords.
    """
    # Outstanding balance on the CustomerAccount as of the statement date. 
    out_balance = ''

    # Calculated date upon which a customer billing amount is due, used in the invoicing process to determine when a Customer's Payment is delinquent. It takes into consideration the regulatory criteria and the Customer's requested due date. In the absence of a Customer requested due date, the due date is typically calculated from the regulated number of days and the 'billingDate'. 
    due_date = ''

    # Kind of bill customer receives. Values are: "separate_ess_udc", "consolidated_udc", "consolidated_ess", "other"
    kind = 'separate_ess_udc'

    # Date of the last payment received from the customer. It is retained in the Customer Billing system, although the details of each payment are tracked in the ERP system. 
    last_payment_date = ''

    # Monthly amortized amount due during each billing cycle for the CustomerAccount balance for which the Payment Plan is set-up. 
    pymt_plan_amt = ''

    # Amount of the last payment received from the customer. It is retained in the Customer Billing system, although the details of each payment are tracked in the ERP system. 
    last_payment_amt = ''

    # Type of payment plan. 
    pymt_plan_type = ''

    # Business date designated for the billing run which produced this CustomerBillingInfo. 
    billing_date = ''

    erp_invoice_line_items = []

    customer_account = None

    # <<< customer_billing_info
    # @generated
    def __init__(self, out_balance='', due_date='', kind='separate_ess_udc', last_payment_date='', pymt_plan_amt='', last_payment_amt='', pymt_plan_type='', billing_date='', erp_invoice_line_items=[], customer_account=None, **kw_args):
        """ Initialises a new 'CustomerBillingInfo' instance.
        """
        self.out_balance = out_balance
        self.due_date = due_date
        self.kind = kind
        self.last_payment_date = last_payment_date
        self.pymt_plan_amt = pymt_plan_amt
        self.last_payment_amt = last_payment_amt
        self.pymt_plan_type = pymt_plan_type
        self.billing_date = billing_date
        self.erp_invoice_line_items = erp_invoice_line_items
        self.customer_account = customer_account

        super(CustomerBillingInfo, self).__init__(**kw_args)
    # >>> customer_billing_info


class OutageHistory(Document):
    """ A document collecting OutageReports, that allows utilities to examine the number of outages suffered by a customer. Also provides data to calculate the total supply interruption to any customer over a given period.
    """
    # OutageReports per customer for which this OutageHistory is created.
    outage_reports = []

    # <<< outage_history
    # @generated
    def __init__(self, outage_reports=[], **kw_args):
        """ Initialises a new 'OutageHistory' instance.
        """
        self.outage_reports = outage_reports

        super(OutageHistory, self).__init__(**kw_args)
    # >>> outage_history


class ServiceGuarantee(Document):
    """ A service guarantee, often imposed by a regulator, defines conditions that, if not satisfied, will result in the utility making a monetary payment to the customer. Note that guarantee's identifier is in the 'name' attribute and the status of the guarantee is in the 'Status.status' attribute. Example service requirements include: 1) If power is not restored within 24 hours, customers can claim $50 for residential customers or $100 for commercial and industrial customers. In addition for each extra period of 12 hours the customer's supply has not been activated, the customer can claim $25. 2) If a customer has a question about their electricity bill, the utility will investigate and respond to the inquiry within 15 working days. If utility fails to meet its guarantee, utility will automatically pay the customer $50.
    """
    # The amount to be paid by the service provider to the customer for each vilations of the 'service Requirement'. 
    pay_amount = ''

    # Explanation of the requirement and conditions for satisfying it. 
    service_requirement = ''

    # The end of the period in which this service guantee applies. 
    end_date = ''

    # The beginning of the period in which this service guantee applies. 
    start_date = ''

    # True if utility must autmatically pay the specified amount whenever the condition is not satisified, otherwise customer must make a claim to receive payment. 
    automatic_pay = False

    # <<< service_guarantee
    # @generated
    def __init__(self, pay_amount='', service_requirement='', end_date='', start_date='', automatic_pay=False, **kw_args):
        """ Initialises a new 'ServiceGuarantee' instance.
        """
        self.pay_amount = pay_amount
        self.service_requirement = service_requirement
        self.end_date = end_date
        self.start_date = start_date
        self.automatic_pay = automatic_pay

        super(ServiceGuarantee, self).__init__(**kw_args)
    # >>> service_guarantee


class WorkBillingInfo(Document):
    """ Billing information for work performed for the customer. The history of Work Billing Info, Invoices, and Payments is to be maintained in associated ActivityRecords.
    """
    # Amount of price on deposit. 
    deposit = ''

    # Date by which payment for bill is expected from client. 
    due_date = ''

    # Amount of bill. 
    work_price = 0.0

    # Date bill was issued to client. 
    issue_date = ''

    # Date payment was received from client. 
    received_date = ''

    # Estimated cost for work. 
    cost_estimate = ''

    # Discount from standard price. 
    discount = 0.0

    customer_account = None

    works = []

    erp_line_items = []

    # <<< work_billing_info
    # @generated
    def __init__(self, deposit='', due_date='', work_price=0.0, issue_date='', received_date='', cost_estimate='', discount=0.0, customer_account=None, works=[], erp_line_items=[], **kw_args):
        """ Initialises a new 'WorkBillingInfo' instance.
        """
        self.deposit = deposit
        self.due_date = due_date
        self.work_price = work_price
        self.issue_date = issue_date
        self.received_date = received_date
        self.cost_estimate = cost_estimate
        self.discount = discount
        self.customer_account = customer_account
        self.works = works
        self.erp_line_items = erp_line_items

        super(WorkBillingInfo, self).__init__(**kw_args)
    # >>> work_billing_info


class SubscribePowerCurve(Curve):
    """ Price curve for specifying the cost of energy (X) at points in time (y1) according to a prcing structure, which is based on a tariff.
    """
    pricing_structure = None

    # <<< subscribe_power_curve
    # @generated
    def __init__(self, pricing_structure=None, **kw_args):
        """ Initialises a new 'SubscribePowerCurve' instance.
        """
        self.pricing_structure = pricing_structure

        super(SubscribePowerCurve, self).__init__(**kw_args)
    # >>> subscribe_power_curve


class PowerQualityPricing(Document):
    """ Pricing can be based on power quality.
    """
    # Value of uninterrupted service (Cost per active power). 
    value_uninterrupted_service_p = 0.0

    # Threshold minimum power factor for this PricingStructure, specified in instances where a special charge is levied if the actual power factor for a Service falls below the value specified here. 
    power_factor_min = 0.0

    # Voltage limit violation cost (Cost per unit Voltage). 
    volt_limit_viol_cost = 0.0

    # Normal low voltage limit. 
    normal_low_volt_limit = ''

    # Emergency low voltage limit. 
    emergency_low_volt_limit = ''

    # Value of uninterrupted service (Cost per energy). 
    value_uninterrupted_service_energy = 0.0

    # Voltage imbalance violation cost (Cost per unit Voltage). 
    volt_imbalance_viol_cost = 0.0

    # Emergency high voltage limit. 
    emergency_high_volt_limit = ''

    # Normal high voltage limit. 
    normal_high_volt_limit = ''

    pricing_structure = None

    service_delivery_points = []

    # <<< power_quality_pricing
    # @generated
    def __init__(self, value_uninterrupted_service_p=0.0, power_factor_min=0.0, volt_limit_viol_cost=0.0, normal_low_volt_limit='', emergency_low_volt_limit='', value_uninterrupted_service_energy=0.0, volt_imbalance_viol_cost=0.0, emergency_high_volt_limit='', normal_high_volt_limit='', pricing_structure=None, service_delivery_points=[], **kw_args):
        """ Initialises a new 'PowerQualityPricing' instance.
        """
        self.value_uninterrupted_service_p = value_uninterrupted_service_p
        self.power_factor_min = power_factor_min
        self.volt_limit_viol_cost = volt_limit_viol_cost
        self.normal_low_volt_limit = normal_low_volt_limit
        self.emergency_low_volt_limit = emergency_low_volt_limit
        self.value_uninterrupted_service_energy = value_uninterrupted_service_energy
        self.volt_imbalance_viol_cost = volt_imbalance_viol_cost
        self.emergency_high_volt_limit = emergency_high_volt_limit
        self.normal_high_volt_limit = normal_high_volt_limit
        self.pricing_structure = pricing_structure
        self.service_delivery_points = service_delivery_points

        super(PowerQualityPricing, self).__init__(**kw_args)
    # >>> power_quality_pricing


class StandardIndustryCode(Document):
    """ The Standard Industrial Classification (SIC) are the codes that identify the type of products/service an industry is involved in, and used for statutory reporting purposes. For example, in the USA these codes are located by the federal government, and then published in a book entitled 'The Standard Industrial Classification Manual'. The codes are arranged in a hierarchical structure. Note that Residential Service Agreements are not classified according to the SIC codes.
    """
    # Standard alphanumeric code assigned to a particular product/service within an industry. 
    code = ''

    customer_agreements = []

    # <<< standard_industry_code
    # @generated
    def __init__(self, code='', customer_agreements=[], **kw_args):
        """ Initialises a new 'StandardIndustryCode' instance.
        """
        self.code = code
        self.customer_agreements = customer_agreements

        super(StandardIndustryCode, self).__init__(**kw_args)
    # >>> standard_industry_code


# <<< inf_customers
# @generated
# >>> inf_customers

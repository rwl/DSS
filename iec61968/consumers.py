# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" This package is used to define detailed customer models as are needed for 61968.
"""

from iec61968.documentation.documentinheritance import Document
from iec61968.core2.toplevel import Agreement
from  import 
from iec61968.core2.collections import Collection
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class WorkBillingInfo(Document):
    """  Billing information for work performed for the customer.    The history of Work Billing Info, Invoices, and Payments is to be maintained in associated ActivityRecords.
    """
    #  Date bill was issued to client. 
    issue_date = ''

    #  Date by which payment for bill is expected from client. 
    due_date = ''

    #  Date payment was received from client. 
    received_date = ''

    #  Amount of bill. 
    work_price = ''

    # Estimated cost for work.  
    cost_estimate = ''

    # Amount of price on deposit. 
    deposit = ''

    # Discount from standard price. 
    discount = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_account = None

    erp_line_items = []

    work = []

    # <<< work_billing_info
    # @generated
    def __init__(self, issue_date='', due_date='', received_date='', work_price='', cost_estimate='', deposit='', discount='', status='', status_date_time='', status_remarks='', customer_account=None, erp_line_items=[], work=[], **kw_args):
        """ Initialises a new 'WorkBillingInfo' instance.
        """
        self.issue_date = issue_date
        self.due_date = due_date
        self.received_date = received_date
        self.work_price = work_price
        self.cost_estimate = cost_estimate
        self.deposit = deposit
        self.discount = discount
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_account = customer_account
        self.erp_line_items = erp_line_items
        self.work = work

        super(WorkBillingInfo, self).__init__(**kw_args)
    # >>> work_billing_info


class CustomerAccount(Document):
    """ The CustomerAccount defines the assignment of a group of products and services purchased by the Customer through a CustomerAgreement.  A CustomerAccount collects together the Service Agreements, Security Agreements, Contract Agreements, and Non-Service Agreeements to create billings(invoices) for a Customer and receive  payment on those Agreements.  It is used as a mechanism for customer billing and payment.
    """
    # Billing cycle identifies cycle day on which the related Customer Account will normally be billed.  It is used in determining when to produce the billing statement for the Customer Account. 
    billing_cycle = ''

    #  Budget bill code. 
    budget_bill = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_data = None

    work_billing_info = []

    customer_agreements = []

    erp_invoicees = []

    customer_billing_info = []

    # <<< customer_account
    # @generated
    def __init__(self, billing_cycle='', budget_bill='', status='', status_date_time='', status_remarks='', customer_data=None, work_billing_info=[], customer_agreements=[], erp_invoicees=[], customer_billing_info=[], **kw_args):
        """ Initialises a new 'CustomerAccount' instance.
        """
        self.billing_cycle = billing_cycle
        self.budget_bill = budget_bill
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_data = customer_data
        self.work_billing_info = work_billing_info
        self.customer_agreements = customer_agreements
        self.erp_invoicees = erp_invoicees
        self.customer_billing_info = customer_billing_info

        super(CustomerAccount, self).__init__(**kw_args)
    # >>> customer_account


class CustomerAgreement(Agreement):
    """ The CustomerAgreement is an agreement between the Customer and the utility to pay for service at a specific ServiceLocation.  The Service Agreement, a type of CustomerAgreement, records certain billing information about the type of service provided at the ServiceLocation.  It is used during charge creation to determine the type of Service.  CustomerAccount contains common information from the various types of ServiceAgreements.
    """
    # The type of service that supports the customer agreement: electric single-rate, electric time-of-use, streetlight, water, gas, other. 
    type_service = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_account = None

    service_locations = []

    pricing_structures = []

    standard_industry_code = None

    equipment = []

    meter_groups = []

    meter_readings = []

    meter_controls = []

    # <<< customer_agreement
    # @generated
    def __init__(self, type_service='', status='', status_date_time='', status_remarks='', customer_account=None, service_locations=[], pricing_structures=[], standard_industry_code=None, equipment=[], meter_groups=[], meter_readings=[], meter_controls=[], **kw_args):
        """ Initialises a new 'CustomerAgreement' instance.
        """
        self.type_service = type_service
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_account = customer_account
        self.service_locations = service_locations
        self.pricing_structures = pricing_structures
        self.standard_industry_code = standard_industry_code
        self.equipment = equipment
        self.meter_groups = meter_groups
        self.meter_readings = meter_readings
        self.meter_controls = meter_controls

        super(CustomerAgreement, self).__init__(**kw_args)
    # >>> customer_agreement


class PowerQualityPricing(Document):
    """ Pricing can be based on power quality.  
    """
    #  Normal high voltage limit. 
    normal_high_volt_limit = ''

    #  Normal low voltage limit. 
    normal_low_volt_limit = ''

    #  Emergency high voltage limit. 
    emergency_high_volt_limit = ''

    #  Emergency low voltage limit. 
    emergency_low_volt_limit = ''

    #  Value of uninterrupted service (Cost per kW). 
    value_uninterrupted_service_kw = ''

    #  Value of uninterrupted service (Cost per kWh). 
    value_uninterrupted_service_kwh = ''

    #  Voltage limit violation cost (Cost per unit Voltage). 
    volt_limit_viol_cost = ''

    #  Voltage imbalance violation cost (Cost per unit Voltage). 
    volt_imbalance_viol_cost = ''

    # This specifies the threshold minimum power factor for a Pricing Structure.  It is specified in instances where a special charge is levied if the actual power factor for a Service falls below the value specified in this attribute.     	 
    power_factor_min = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    pricing_structure = None

    service_delivery_points = []

    # <<< power_quality_pricing
    # @generated
    def __init__(self, normal_high_volt_limit='', normal_low_volt_limit='', emergency_high_volt_limit='', emergency_low_volt_limit='', value_uninterrupted_service_kw='', value_uninterrupted_service_kwh='', volt_limit_viol_cost='', volt_imbalance_viol_cost='', power_factor_min='', status='', status_date_time='', status_remarks='', pricing_structure=None, service_delivery_points=[], **kw_args):
        """ Initialises a new 'PowerQualityPricing' instance.
        """
        self.normal_high_volt_limit = normal_high_volt_limit
        self.normal_low_volt_limit = normal_low_volt_limit
        self.emergency_high_volt_limit = emergency_high_volt_limit
        self.emergency_low_volt_limit = emergency_low_volt_limit
        self.value_uninterrupted_service_kw = value_uninterrupted_service_kw
        self.value_uninterrupted_service_kwh = value_uninterrupted_service_kwh
        self.volt_limit_viol_cost = volt_limit_viol_cost
        self.volt_imbalance_viol_cost = volt_imbalance_viol_cost
        self.power_factor_min = power_factor_min
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.pricing_structure = pricing_structure
        self.service_delivery_points = service_delivery_points

        super(PowerQualityPricing, self).__init__(**kw_args)
    # >>> power_quality_pricing


class Tariff(Document):
    """  A document, approved by the responsible regulatory agency, listing the terms and conditions, including a schedule of prices, under which utility services will be provided.  The Tariff has a unique number within the state or province.  For Rate Schedules it is frequently allocated by the affiliated Public Utilities Commission. 
    """
    #  Date tarrif was activated. 
    start_date = ''

    #  Date tarrif was terminated.  Value is null if tarrif is active. 
    end_date = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    pricing_structures = []

    # <<< tariff
    # @generated
    def __init__(self, start_date='', end_date='', status='', status_date_time='', status_remarks='', pricing_structures=[], **kw_args):
        """ Initialises a new 'Tariff' instance.
        """
        self.start_date = start_date
        self.end_date = end_date
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.pricing_structures = pricing_structures

        super(Tariff, self).__init__(**kw_args)
    # >>> tariff


class CustomerData():
    """ A Customer is a type of Organisation.  For that type of Organisation, CustomerData is required for services supplied by other Organisations.  Note that many of the fields sommonly associated with a customer are defined in ERP classes, such as ErpPerson.
    """
    # Does overall customer (vs. ErpPerson) have special service needs? Examples include life support, hospitals, etc. 
    special_needs = ''

    # True if  this an important customer.  Note that since special needs are reflected in another attribute (specialNeeds), special needs are not a consideration here.  
    vip = ''

    # Type of customer.  Examples include: residential, residential & commercial, residential & streetlight, residential-streetlight-others, residential-farm-service, commercial-industrial, pumping load,wind machine, energy service supplier, energy service scheduler, other, internal use. 
    customer_type = ''

    # Public Utility Commission identification number, if applicable. 
    puc_number = ''

    customer_accounts = []

    organisation = None

    erp_persons = []

    trouble_tickets = []

    work = []

    end_device_assets = []

    # <<< customer_data
    # @generated
    def __init__(self, special_needs='', vip='', customer_type='', puc_number='', customer_accounts=[], organisation=None, erp_persons=[], trouble_tickets=[], work=[], end_device_assets=[], **kw_args):
        """ Initialises a new 'CustomerData' instance.
        """
        self.special_needs = special_needs
        self.vip = vip
        self.customer_type = customer_type
        self.puc_number = puc_number
        self.customer_accounts = customer_accounts
        self.organisation = organisation
        self.erp_persons = erp_persons
        self.trouble_tickets = trouble_tickets
        self.work = work
        self.end_device_assets = end_device_assets

        super(CustomerData, self).__init__(**kw_args)
    # >>> customer_data


class StandardIndustryCode(Document):
    """ The Standard Industrial Classification are the codes that identify the type of products/service an industry is      involved in.  For example, in the USA these codes are located by the federal government, and then published in a book entitled 'The Standard Industrial Classification Manual'.  The codes are arranged in a hierarchical structure.  It is used for statutory reporting purposes.  Note that Residential Service Agreements are not classified according to the SIC codes. 
    """
    # SIC Code is a standard alphanumeric code assigned to a particular product/service within an industry.   
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


class PricingStructure(Document):
    """ A grouping of pricing components and prices used in the creation of customer charges and the eligibility criteria under which these terms may be offered to a customer.  The reasons for grouping includes state, customer classification, site characteristics, classification (i.e. fee price structure, deposit price structure, electric service price structure, etc.) and accounting requirements. 
    """
    # Code is a unique user-allocated key for a Pricing  Structure.  It is used by company representatives to identify the correct price structure for allocating to a customer.  For rate schedules it is often prefixed by a state code.    	 
    code = ''

    # The Tax Exemption Indicator is the indicator that specifies whether the Pricing Structure is taxable or not.  true=Taxable Pricing Structure, false=Non-Taxable Pricing Structure                
    tax_exemption = ''

    # Daily Estimate Usage Quantity is a manually entered usage quantity typically entered by customer accounting.  It is used in place of actual computed estimated average when history of usage is not available.  
    daily_est_usage = ''

    # Daily Ceiling Usage Quantity is the absolute maximum valid non-demand usage quantity used in validating a customer's billed non-demand usage.   
    daily_ceiling_usage = ''

    # Daily Floor Usage Quantity is the absolute minimum valid non-demand usage quantity used in validating a customer's billed non-demand usage.  
    daily_floor_usage = ''

    # Revenue Class is an accounting classification of the type of revenue collected for the Customer Agreement.   It is typically used to break down accounts for revenue accounting.  Typical classes include: Residential, Non-residential, Commercial, Industrial, Irrigation, Street Light, Other.  Revenue Class is often used to determine the grace period allowed, before collection actions are taken on a customer. (grace periods vary between revenue classes)    	 
    revenue_class = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Service type: gas, water, electric. 
    service_type = ''

    customer_agreements = []

    power_quality_pricings = []

    tariffs = []

    subscribe_power_curve = None

    # <<< pricing_structure
    # @generated
    def __init__(self, code='', tax_exemption='', daily_est_usage='', daily_ceiling_usage='', daily_floor_usage='', revenue_class='', status='', status_date_time='', status_remarks='', service_type='', customer_agreements=[], power_quality_pricings=[], tariffs=[], subscribe_power_curve=None, **kw_args):
        """ Initialises a new 'PricingStructure' instance.
        """
        self.code = code
        self.tax_exemption = tax_exemption
        self.daily_est_usage = daily_est_usage
        self.daily_ceiling_usage = daily_ceiling_usage
        self.daily_floor_usage = daily_floor_usage
        self.revenue_class = revenue_class
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.service_type = service_type
        self.customer_agreements = customer_agreements
        self.power_quality_pricings = power_quality_pricings
        self.tariffs = tariffs
        self.subscribe_power_curve = subscribe_power_curve

        super(PricingStructure, self).__init__(**kw_args)
    # >>> pricing_structure


class CustomerBillingInfo(Document):
    """ The creation of the monthly customer billing statements is the method employed to notify Customers of charges, adjustments and credits applied to their account for Services and Products.  The actuall billing occurs through an ErpInvoice.  The CustomerBillingInfo includes information from the payment, collection, meter reading, installed meter, service, site, customer, customer account, customer agreement, services and pricing subject areas.   Each component price shows up as a separate line item on the ErpInvoice.  The Customer Billing Statement may include collection and account messages, marketing/civic event messages and bill inserts.  One Customer Billing Statement is produced for all Agreements under a Customer Account per billing month.  The Customer Billing Statement is produced on the billing cycle date assigned to a Customer Account.      The history of Customer Billing Info, Invoices, and Payments is to be maintained in associated ActivityRecords.
    """
    # The business date designated for the billing run which produced the Customer Billing Statement.  
    billing_date = ''

    # Due Date contains the calculated date upon which a Customer Billing amount is due, which is used in the invoicing process.  It is used to determine when a Customer's Payment is delinquent.  This due date takes into consideration the regulatory criteria and the Customer's requested due date.  In the absence of a Customer requested due date, this statement due date is typically calculated from the regulated number of days and the bill date of the Customer Billing Statement.  
    due_date = ''

    # This is the outstanding balance on the Customer Account as of the statement date.    	 
    out_balance = ''

    # This is the monthly amortized amount due during each billing cycle for the Customer Account balance for which the Payment Plan is set-up.   
    pymt_plan_amt = ''

    # Type of payment plan. 
    pymt_plan_type = ''

    # Customer receives a (1) consolidated bill from energy service supplier (ESS), (2) consolidated bill from utility distribution compnay (UDC), (3) separate bills from  ESS and UDC, (4) other. 
    billing_type = ''

    # The details of each payment are tracked in the ERP system.  However, the date of the last payment received from the customer is retained in CustomerBilling. 
    last_payment_date = ''

    # The details of each payment are tracked in the ERP system.  However, the amount of the last payment is maintained in CustomerBilling. 
    last_payment_amt = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    customer_account = None

    erp_invoice_line_items = []

    # <<< customer_billing_info
    # @generated
    def __init__(self, billing_date='', due_date='', out_balance='', pymt_plan_amt='', pymt_plan_type='', billing_type='', last_payment_date='', last_payment_amt='', status='', status_date_time='', status_remarks='', customer_account=None, erp_invoice_line_items=[], **kw_args):
        """ Initialises a new 'CustomerBillingInfo' instance.
        """
        self.billing_date = billing_date
        self.due_date = due_date
        self.out_balance = out_balance
        self.pymt_plan_amt = pymt_plan_amt
        self.pymt_plan_type = pymt_plan_type
        self.billing_type = billing_type
        self.last_payment_date = last_payment_date
        self.last_payment_amt = last_payment_amt
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.customer_account = customer_account
        self.erp_invoice_line_items = erp_invoice_line_items

        super(CustomerBillingInfo, self).__init__(**kw_args)
    # >>> customer_billing_info


class CustomerList(Collection):
    """  Generic part of a document containing references to many Customers. Could be list of connected customers or list of affected customers.
    """
    organisations = []

    outage = None

    outage_notifications = []

    # <<< customer_list
    # @generated
    def __init__(self, organisations=[], outage=None, outage_notifications=[], **kw_args):
        """ Initialises a new 'CustomerList' instance.
        """
        self.organisations = organisations
        self.outage = outage
        self.outage_notifications = outage_notifications

        super(CustomerList, self).__init__(**kw_args)
    # >>> customer_list


class SubcribePowerCurve():
    """ Price curve for specifying the cost of energy (X) at points in time (y1) according to a prcing structure, which is based on a tariff.
    """
    pricing_structure = None

    # <<< subcribe_power_curve
    # @generated
    def __init__(self, pricing_structure=None, **kw_args):
        """ Initialises a new 'SubcribePowerCurve' instance.
        """
        self.pricing_structure = pricing_structure

        super(SubcribePowerCurve, self).__init__(**kw_args)
    # >>> subcribe_power_curve


class ServiceGuarantee(Document):
    """ A service guarantee, often imposed by a by a regulator, defines conditions that, if not satisfied, will result in the utility making a monetary payment to the customer.  Note that guarantee's identifier is in the .name attribute and the status of the guarantee is in the .subjectStatus attribute.  Example Service Requirements include: 1) If power is not restored within 24 hours, customers can claim $50 for residential customers or $100 for commercial and industrial  customers.  In addition for each extra period of 12 hours the customer's supply has not been activated, the customer can claim $25 2) If a customer has a question about their electricity bill, the utility will investigate and respond to the inquiry within 15 working days.  If utility fails to meet its guarantee, utility will automatically pay the customer $50.
    """
    # Explanation of the requirement and conditions for satisfying it. 
    service_requirement = ''

    # The amount to be paid by the service provider to the customer for each vilations of the Service Requirement. 
    pay_amount = ''

    # True if utility must autmatically pay the specified amount whenver the condition is not satisified.  Otherwise, customer must make a claim to receive payment. 
    automatic_pay = ''

    # The beginning of the period in which this service guantee applies. 
    start_date = ''

    # The end of the period in which this service guantee applies. 
    end_date = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of service guarantee.  
    type_sg = ''

    # <<< service_guarantee
    # @generated
    def __init__(self, service_requirement='', pay_amount='', automatic_pay='', start_date='', end_date='', status='', status_date_time='', status_remarks='', type_sg='', **kw_args):
        """ Initialises a new 'ServiceGuarantee' instance.
        """
        self.service_requirement = service_requirement
        self.pay_amount = pay_amount
        self.automatic_pay = automatic_pay
        self.start_date = start_date
        self.end_date = end_date
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_sg = type_sg

        super(ServiceGuarantee, self).__init__(**kw_args)
    # >>> service_guarantee


class PhaseLoad():
    """ The class PhaseLoad models load with the unified characteristics. It can be one phase of a multi-phase load and also can be a three-phase load with unified characteristics. The aggregate relationship between DistributionLoad and PhaseLoad allows a distribution load to have any type configuration like single phase, two-phase or three-phase
    """
    # Percentage of the active power of the load which is constant. (between 0 and 100) 
    p_constant_power_pct = ''

    # Percentage of the active power of the load which has constant current (between 0 and 100) 
    p_constant_current_pct = ''

    # Percentage of the active power of the load which has constant impedance (between 0 and 100) 
    p_constant_impedance_pct = ''

    # Percentage of the reactive power of the load which is constant (between 0 and 100) 
    q_constant_power_pct = ''

    # Percentage of the reactive power of the load which has constant current (between 0 and 100) 
    q_constant_current_pct = ''

    # Percentage of the reactive power of the load which has constant impedance (between 0 and 100) 
    q_constant_impedance_pct = ''

    # Specifies the voltage dependence of the active part of the  pConstantCurrentPct.  
    p_volt_dependance_factor = ''

    # Specifies the voltage dependence of the reactive part of the  qConstantCurrentPct.  
    q_volt_dependance_factor = ''

    service_delivery_point = None

    energy_consumer = None

    # <<< phase_load
    # @generated
    def __init__(self, p_constant_power_pct='', p_constant_current_pct='', p_constant_impedance_pct='', q_constant_power_pct='', q_constant_current_pct='', q_constant_impedance_pct='', p_volt_dependance_factor='', q_volt_dependance_factor='', service_delivery_point=None, energy_consumer=None, **kw_args):
        """ Initialises a new 'PhaseLoad' instance.
        """
        self.p_constant_power_pct = p_constant_power_pct
        self.p_constant_current_pct = p_constant_current_pct
        self.p_constant_impedance_pct = p_constant_impedance_pct
        self.q_constant_power_pct = q_constant_power_pct
        self.q_constant_current_pct = q_constant_current_pct
        self.q_constant_impedance_pct = q_constant_impedance_pct
        self.p_volt_dependance_factor = p_volt_dependance_factor
        self.q_volt_dependance_factor = q_volt_dependance_factor
        self.service_delivery_point = service_delivery_point
        self.energy_consumer = energy_consumer

        super(PhaseLoad, self).__init__(**kw_args)
    # >>> phase_load


class ConsumersVersion(object):
 
    version = ''

 
    date = ''

    # <<< consumers_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'ConsumersVersion' instance.
        """
        self.version = version
        self.date = date

        super(ConsumersVersion, self).__init__(**kw_args)
    # >>> consumers_version


class ExternalCustomerAgreement(CustomerAgreement):
    """ A type of customer agreement involving an external agency.  For example, a customer may form a contracts with an Energy Service Supplier if Direct Access is permitted.
    """
    pass
    # <<< external_customer_agreement
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ExternalCustomerAgreement' instance.
        """

        super(ExternalCustomerAgreement, self).__init__(**kw_args)
    # >>> external_customer_agreement


# <<< consumers
# @generated
# >>> consumers

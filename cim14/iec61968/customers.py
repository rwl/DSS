# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Organisation
from cim14.iec61968.common import Location
from cim14.iec61968.common import Agreement
from cim14.iec61968.common import Document
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class Customer(Organisation):
    """ Organisation receiving services from ServiceSupplier.
    """
    # True if this is an important customer. Importance is for matters different than those in 'specialNeed' attribute. 
    vip = False

    # (if applicable) Public Utility Commission identification number. 
    puc_number = ''

    # Kind of customer. Values are: "energy_service_scheduler", "wind_machine", "residential_and_commercial", "internal_use", "residential_streetlight_others", "energy_service_supplier", "commercial_industrial", "other", "pumping_load", "residential_and_streetlight", "residential", "residential_farm_service"
    kind = 'energy_service_scheduler'

    # True if customer organisation has special service needs such as life support, hospitals, etc. 
    special_need = ''

    # Status of this customer.
    status = None

    # All agreements of this customer.
    customer_agreements = []

    # All the works performed for this customer.
    works = []

    planned_outage = None

    erp_persons = []

    outage_notifications = []

    trouble_tickets = []

    # All end device assets of this customer.
    end_device_assets = []

    # <<< customer
    # @generated
    def __init__(self, vip=False, puc_number='', kind='energy_service_scheduler', special_need='', status=None, customer_agreements=[], works=[], planned_outage=None, erp_persons=[], outage_notifications=[], trouble_tickets=[], end_device_assets=[], **kw_args):
        """ Initialises a new 'Customer' instance.
        """
        self.vip = vip
        self.puc_number = puc_number
        self.kind = kind
        self.special_need = special_need
        self.status = status
        self.customer_agreements = customer_agreements
        self.works = works
        self.planned_outage = planned_outage
        self.erp_persons = erp_persons
        self.outage_notifications = outage_notifications
        self.trouble_tickets = trouble_tickets
        self.end_device_assets = end_device_assets

        super(Customer, self).__init__(**kw_args)
    # >>> customer


class ServiceLocation(Location):
    """ A customer ServiceLocation has one or more ServiceDeliveryPoint(s), which in turn relate to Meters. The location may be a point or a polygon, depending on the specific circumstances. For distribution, the ServiceLocation is typically the location of the utility customer's premise. Because a customer's premise may have one or more meters, the ServiceDeliveryPoint is used to define the actual conducting equipment that the EndDeviceAsset attaches to at the utility customer's ServiceLocation. For transmission, it is the point(s) of interconnection on the transmission provider's transmission system where capacity and/or energy transmitted by the transmission provider is made available to the receiving party.
    """
    # Problems previously encountered when visiting or performing work on this site. Examples include: bad dog, viloent customer, verbally abusive occupant, obstructions, safety hazards, etc. 
    site_access_problem = ''

    # Method for the service person to access the appropriate service locations. For example, a description of where to obtain a key if the facility is unmanned and secured. 
    access_method = ''

    # True if inspection is needed of facilities at this service location. This could be requested by a customer, due to suspected tampering, environmental concerns (e.g., a fire in the vicinity), or to correct incompatible data. 
    needs_inspection = False

    # All service delivery points delivering service (of the same type) to this service location.
    service_delivery_points = []

    # All customer agreements regulating this service location.
    customer_agreements = []

    # All end device assets that measure the service delivered to this service location.
    end_device_assets = []

    # <<< service_location
    # @generated
    def __init__(self, site_access_problem='', access_method='', needs_inspection=False, service_delivery_points=[], customer_agreements=[], end_device_assets=[], **kw_args):
        """ Initialises a new 'ServiceLocation' instance.
        """
        self.site_access_problem = site_access_problem
        self.access_method = access_method
        self.needs_inspection = needs_inspection
        self.service_delivery_points = service_delivery_points
        self.customer_agreements = customer_agreements
        self.end_device_assets = end_device_assets

        super(ServiceLocation, self).__init__(**kw_args)
    # >>> service_location


class CustomerAgreement(Agreement):
    """ Agreement between the Customer and the ServiceSupplier to pay for service at a specific ServiceLocation. It records certain billing information about the type of service provided at the ServiceLocation and is used during charge creation to determine the type of service.
    """
    # All service delivery points regulated by this customer agreement.
    service_delivery_points = []

    # Customer for this agreement.
    customer = None

    # Service supplier for this customer agreement.
    service_supplier = None

    # Customer account owning this agreement.
    customer_account = None

    standard_industry_code = None

    # Could be deprecated in the future.
    end_device_controls = []

    service_category = None

    equipments = []

    # All (non-service related) auxiliary agreements that refer to this customer agreement.
    auxiliary_agreements = []

    # All service locations regulated by this customer agreement.
    service_locations = []

    # All pricing structures applicable to this customer agreement.
    pricing_structures = []

    # Demand response program for this customer agreement.
    demand_response_program = None

    # (could be deprecated in the future) All meter readings for this customer agreement.
    meter_readings = []

    # <<< customer_agreement
    # @generated
    def __init__(self, service_delivery_points=[], customer=None, service_supplier=None, customer_account=None, standard_industry_code=None, end_device_controls=[], service_category=None, equipments=[], auxiliary_agreements=[], service_locations=[], pricing_structures=[], demand_response_program=None, meter_readings=[], **kw_args):
        """ Initialises a new 'CustomerAgreement' instance.
        """
        self.service_delivery_points = service_delivery_points
        self.customer = customer
        self.service_supplier = service_supplier
        self.customer_account = customer_account
        self.standard_industry_code = standard_industry_code
        self.end_device_controls = end_device_controls
        self.service_category = service_category
        self.equipments = equipments
        self.auxiliary_agreements = auxiliary_agreements
        self.service_locations = service_locations
        self.pricing_structures = pricing_structures
        self.demand_response_program = demand_response_program
        self.meter_readings = meter_readings

        super(CustomerAgreement, self).__init__(**kw_args)
    # >>> customer_agreement


class CustomerAccount(Document):
    """ Assignment of a group of products and services purchased by the Customer through a CustomerAgreement, used as a mechanism for customer billing and payment. It contains common information from the various types of CustomerAgreements to create billings (invoices) for a Customer and receive payment.
    """
    # Budget bill code. 
    budget_bill = ''

    # Cycle day on which this customer account will normally be billed, used to determine when to produce the CustomerBillingInfo for this customer account. 
    billing_cycle = ''

    customer_billing_infos = []

    # All payment transactions for this customer account.
    payment_transactions = []

    erp_invoicees = []

    work_billing_infos = []

    # All agreements for this customer account.
    customer_agreements = []

    # <<< customer_account
    # @generated
    def __init__(self, budget_bill='', billing_cycle='', customer_billing_infos=[], payment_transactions=[], erp_invoicees=[], work_billing_infos=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'CustomerAccount' instance.
        """
        self.budget_bill = budget_bill
        self.billing_cycle = billing_cycle
        self.customer_billing_infos = customer_billing_infos
        self.payment_transactions = payment_transactions
        self.erp_invoicees = erp_invoicees
        self.work_billing_infos = work_billing_infos
        self.customer_agreements = customer_agreements

        super(CustomerAccount, self).__init__(**kw_args)
    # >>> customer_account


class Tariff(Document):
    """ Document, approved by the responsible regulatory agency, listing the terms and conditions, including a schedule of prices, under which utility services will be provided. It has a unique number within the state or province. For Rate Schedules it is frequently allocated by the affiliated Public Utilities Commission.
    """
    # Date tarrif was activated. 
    start_date = ''

    # (if tariff became inactive) Date tarrif was terminated. 
    end_date = ''

    # All pricing structures using this tariff.
    pricing_structures = []

    # All tariff profiles using this tariff.
    tariff_profiles = []

    # <<< tariff
    # @generated
    def __init__(self, start_date='', end_date='', pricing_structures=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'Tariff' instance.
        """
        self.start_date = start_date
        self.end_date = end_date
        self.pricing_structures = pricing_structures
        self.tariff_profiles = tariff_profiles

        super(Tariff, self).__init__(**kw_args)
    # >>> tariff


class ServiceCategory(IdentifiedObject):
    """ Category of service provided to the customer.
    """
    # Kind of service. Values are: "refuse", "electricty", "other", "water", "tv_licence", "sewerage", "time", "internet", "gas", "rates", "heat"
    kind = 'refuse'

    # All pricing structures applicable to this service category.
    pricing_structures = []

    customer_agreements = []

    # All service delivery points that deliver this category of service.
    service_delivery_points = []

    spaccounting_functions = []

    # <<< service_category
    # @generated
    def __init__(self, kind='refuse', pricing_structures=[], customer_agreements=[], service_delivery_points=[], spaccounting_functions=[], **kw_args):
        """ Initialises a new 'ServiceCategory' instance.
        """
        self.kind = kind
        self.pricing_structures = pricing_structures
        self.customer_agreements = customer_agreements
        self.service_delivery_points = service_delivery_points
        self.spaccounting_functions = spaccounting_functions

        super(ServiceCategory, self).__init__(**kw_args)
    # >>> service_category


class PricingStructure(Document):
    """ Grouping of pricing components and prices used in the creation of customer charges and the eligibility criteria under which these terms may be offered to a customer. The reasons for grouping include state, customer classification, site characteristics, classification (i.e. fee price structure, deposit price structure, electric service price structure, etc.) and accounting requirements.
    """
    # Absolute minimum valid non-demand usage quantity used in validating a customer's billed non-demand usage. 
    daily_floor_usage = 0

    # Absolute maximum valid non-demand usage quantity used in validating a customer's billed non-demand usage. 
    daily_ceiling_usage = 0

    # True if this pricing structure is not taxable. 
    tax_exemption = False

    # Unique user-allocated key for this pricing structure, used by company representatives to identify the correct price structure for allocating to a customer. For rate schedules it is often prefixed by a state code. 
    code = ''

    # Used in place of actual computed estimated average when history of usage is not available, and typically manually entered by customer accounting. 
    daily_estimated_usage = 0

    # (Accounting) Kind of revenue, often used to determine the grace period allowed, before collection actions are taken on a customer (grace periods vary between revenue classes). Values are: "residential", "industrial", "non_residential", "irrigation", "commercial", "street_light", "other"
    revenue_kind = 'residential'

    # SubscribePowerCurve specifies the cost according to a prcing structure.
    subscribe_power_curve = None

    # All service delivery points (with prepayment meter running as a stand-alone device, with no CustomerAgreement or Customer) to which this pricing structure applies.
    service_delivery_points = []

    # All customer agreements with this pricing structure.
    customer_agreements = []

    # All transactions applying this pricing structure.
    transactions = []

    # All tariffs used by this pricing structure.
    tariffs = []

    power_quality_pricings = []

    # Service category to which this pricing structure applies.
    service_category = None

    # <<< pricing_structure
    # @generated
    def __init__(self, daily_floor_usage=0, daily_ceiling_usage=0, tax_exemption=False, code='', daily_estimated_usage=0, revenue_kind='residential', subscribe_power_curve=None, service_delivery_points=[], customer_agreements=[], transactions=[], tariffs=[], power_quality_pricings=[], service_category=None, **kw_args):
        """ Initialises a new 'PricingStructure' instance.
        """
        self.daily_floor_usage = daily_floor_usage
        self.daily_ceiling_usage = daily_ceiling_usage
        self.tax_exemption = tax_exemption
        self.code = code
        self.daily_estimated_usage = daily_estimated_usage
        self.revenue_kind = revenue_kind
        self.subscribe_power_curve = subscribe_power_curve
        self.service_delivery_points = service_delivery_points
        self.customer_agreements = customer_agreements
        self.transactions = transactions
        self.tariffs = tariffs
        self.power_quality_pricings = power_quality_pricings
        self.service_category = service_category

        super(PricingStructure, self).__init__(**kw_args)
    # >>> pricing_structure


# <<< customers
# @generated
# >>> customers

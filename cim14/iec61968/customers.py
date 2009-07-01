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

ns_prefix = "cim.IEC61968.Customers"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Customers"

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

    def get_customer_agreements(self):
        """ All agreements of this customer.
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x._customer = None
        for y in value:
            y._customer = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._customer = self
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._customer = None
            self._customer_agreements.remove(obj)

    works = []
    
    def add_works(self, *works):
        for obj in works:
	        self._works.append(obj)
        
    def remove_works(self, *works):
        for obj in works:
	        self._works.remove(obj)

    def get_planned_outage(self):
        """ 
        """
        return self._planned_outage

    def set_planned_outage(self, value):
        if self._planned_outage is not None:
            filtered = [x for x in self.planned_outage.customer_datas if x != self]
            self._planned_outage._customer_datas = filtered
            
        self._planned_outage = value
        if self._planned_outage is not None:
            self._planned_outage._customer_datas.append(self)

    planned_outage = property(get_planned_outage, set_planned_outage)

    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for x in self._erp_persons:
            x._customer_data = None
        for y in value:
            y._customer_data = self
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._customer_data = self
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            obj._customer_data = None
            self._erp_persons.remove(obj)

    outage_notifications = []
    
    def add_outage_notifications(self, *outage_notifications):
        for obj in outage_notifications:
	        self._outage_notifications.append(obj)
        
    def remove_outage_notifications(self, *outage_notifications):
        for obj in outage_notifications:
	        self._outage_notifications.remove(obj)

    def get_trouble_tickets(self):
        """ 
        """
        return self._trouble_tickets

    def set_trouble_tickets(self, value):
        for x in self._trouble_tickets:
            x._customer_data = None
        for y in value:
            y._customer_data = self
        self._trouble_tickets = value
            
    trouble_tickets = property(get_trouble_tickets, set_trouble_tickets)
    
    def add_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            obj._customer_data = self
            self._trouble_tickets.append(obj)
        
    def remove_trouble_tickets(self, *trouble_tickets):
        for obj in trouble_tickets:
            obj._customer_data = None
            self._trouble_tickets.remove(obj)

    def get_end_device_assets(self):
        """ All end device assets of this customer.
        """
        return self._end_device_assets

    def set_end_device_assets(self, value):
        for x in self._end_device_assets:
            x._customer = None
        for y in value:
            y._customer = self
        self._end_device_assets = value
            
    end_device_assets = property(get_end_device_assets, set_end_device_assets)
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._customer = self
            self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._customer = None
            self._end_device_assets.remove(obj)

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
        self._customer_agreements = []
        self.customer_agreements = customer_agreements
        self._works = []
        self.works = works
        self._planned_outage = None
        self.planned_outage = planned_outage
        self._erp_persons = []
        self.erp_persons = erp_persons
        self._outage_notifications = []
        self.outage_notifications = outage_notifications
        self._trouble_tickets = []
        self.trouble_tickets = trouble_tickets
        self._end_device_assets = []
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

    def get_service_delivery_points(self):
        """ All service delivery points delivering service (of the same type) to this service location.
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x._service_location = None
        for y in value:
            y._service_location = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_location = self
            self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_location = None
            self._service_delivery_points.remove(obj)

    customer_agreements = []
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
	        self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
	        self._customer_agreements.remove(obj)

    def get_end_device_assets(self):
        """ All end device assets that measure the service delivered to this service location.
        """
        return self._end_device_assets

    def set_end_device_assets(self, value):
        for x in self._end_device_assets:
            x._service_location = None
        for y in value:
            y._service_location = self
        self._end_device_assets = value
            
    end_device_assets = property(get_end_device_assets, set_end_device_assets)
    
    def add_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._service_location = self
            self._end_device_assets.append(obj)
        
    def remove_end_device_assets(self, *end_device_assets):
        for obj in end_device_assets:
            obj._service_location = None
            self._end_device_assets.remove(obj)

    # <<< service_location
    # @generated
    def __init__(self, site_access_problem='', access_method='', needs_inspection=False, service_delivery_points=[], customer_agreements=[], end_device_assets=[], **kw_args):
        """ Initialises a new 'ServiceLocation' instance.
        """
        self.site_access_problem = site_access_problem
        self.access_method = access_method
        self.needs_inspection = needs_inspection
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._customer_agreements = []
        self.customer_agreements = customer_agreements
        self._end_device_assets = []
        self.end_device_assets = end_device_assets

        super(ServiceLocation, self).__init__(**kw_args)
    # >>> service_location


class CustomerAgreement(Agreement):
    """ Agreement between the Customer and the ServiceSupplier to pay for service at a specific ServiceLocation. It records certain billing information about the type of service provided at the ServiceLocation and is used during charge creation to determine the type of service.
    """
    def get_service_delivery_points(self):
        """ All service delivery points regulated by this customer agreement.
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x._customer_agreement = None
        for y in value:
            y._customer_agreement = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._customer_agreement = self
            self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._customer_agreement = None
            self._service_delivery_points.remove(obj)

    def get_customer(self):
        """ Customer for this agreement.
        """
        return self._customer

    def set_customer(self, value):
        if self._customer is not None:
            filtered = [x for x in self.customer.customer_agreements if x != self]
            self._customer._customer_agreements = filtered
            
        self._customer = value
        if self._customer is not None:
            self._customer._customer_agreements.append(self)

    customer = property(get_customer, set_customer)

    def get_service_supplier(self):
        """ Service supplier for this customer agreement.
        """
        return self._service_supplier

    def set_service_supplier(self, value):
        if self._service_supplier is not None:
            filtered = [x for x in self.service_supplier.customer_agreements if x != self]
            self._service_supplier._customer_agreements = filtered
            
        self._service_supplier = value
        if self._service_supplier is not None:
            self._service_supplier._customer_agreements.append(self)

    service_supplier = property(get_service_supplier, set_service_supplier)

    def get_customer_account(self):
        """ Customer account owning this agreement.
        """
        return self._customer_account

    def set_customer_account(self, value):
        if self._customer_account is not None:
            filtered = [x for x in self.customer_account.customer_agreements if x != self]
            self._customer_account._customer_agreements = filtered
            
        self._customer_account = value
        if self._customer_account is not None:
            self._customer_account._customer_agreements.append(self)

    customer_account = property(get_customer_account, set_customer_account)

    def get_standard_industry_code(self):
        """ 
        """
        return self._standard_industry_code

    def set_standard_industry_code(self, value):
        if self._standard_industry_code is not None:
            filtered = [x for x in self.standard_industry_code.customer_agreements if x != self]
            self._standard_industry_code._customer_agreements = filtered
            
        self._standard_industry_code = value
        if self._standard_industry_code is not None:
            self._standard_industry_code._customer_agreements.append(self)

    standard_industry_code = property(get_standard_industry_code, set_standard_industry_code)

    def get_end_device_controls(self):
        """ Could be deprecated in the future.
        """
        return self._end_device_controls

    def set_end_device_controls(self, value):
        for x in self._end_device_controls:
            x._customer_agreement = None
        for y in value:
            y._customer_agreement = self
        self._end_device_controls = value
            
    end_device_controls = property(get_end_device_controls, set_end_device_controls)
    
    def add_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._customer_agreement = self
            self._end_device_controls.append(obj)
        
    def remove_end_device_controls(self, *end_device_controls):
        for obj in end_device_controls:
            obj._customer_agreement = None
            self._end_device_controls.remove(obj)

    def get_service_category(self):
        """ 
        """
        return self._service_category

    def set_service_category(self, value):
        if self._service_category is not None:
            filtered = [x for x in self.service_category.customer_agreements if x != self]
            self._service_category._customer_agreements = filtered
            
        self._service_category = value
        if self._service_category is not None:
            self._service_category._customer_agreements.append(self)

    service_category = property(get_service_category, set_service_category)

    equipments = []
    
    def add_equipments(self, *equipments):
        for obj in equipments:
	        self._equipments.append(obj)
        
    def remove_equipments(self, *equipments):
        for obj in equipments:
	        self._equipments.remove(obj)

    def get_auxiliary_agreements(self):
        """ All (non-service related) auxiliary agreements that refer to this customer agreement.
        """
        return self._auxiliary_agreements

    def set_auxiliary_agreements(self, value):
        for x in self._auxiliary_agreements:
            x._customer_agreement = None
        for y in value:
            y._customer_agreement = self
        self._auxiliary_agreements = value
            
    auxiliary_agreements = property(get_auxiliary_agreements, set_auxiliary_agreements)
    
    def add_auxiliary_agreements(self, *auxiliary_agreements):
        for obj in auxiliary_agreements:
            obj._customer_agreement = self
            self._auxiliary_agreements.append(obj)
        
    def remove_auxiliary_agreements(self, *auxiliary_agreements):
        for obj in auxiliary_agreements:
            obj._customer_agreement = None
            self._auxiliary_agreements.remove(obj)

    service_locations = []
    
    def add_service_locations(self, *service_locations):
        for obj in service_locations:
	        self._service_locations.append(obj)
        
    def remove_service_locations(self, *service_locations):
        for obj in service_locations:
	        self._service_locations.remove(obj)

    pricing_structures = []
    
    def add_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.append(obj)
        
    def remove_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.remove(obj)

    def get_demand_response_program(self):
        """ Demand response program for this customer agreement.
        """
        return self._demand_response_program

    def set_demand_response_program(self, value):
        if self._demand_response_program is not None:
            filtered = [x for x in self.demand_response_program.customer_agreements if x != self]
            self._demand_response_program._customer_agreements = filtered
            
        self._demand_response_program = value
        if self._demand_response_program is not None:
            self._demand_response_program._customer_agreements.append(self)

    demand_response_program = property(get_demand_response_program, set_demand_response_program)

    def get_meter_readings(self):
        """ (could be deprecated in the future) All meter readings for this customer agreement.
        """
        return self._meter_readings

    def set_meter_readings(self, value):
        for x in self._meter_readings:
            x._customer_agreement = None
        for y in value:
            y._customer_agreement = self
        self._meter_readings = value
            
    meter_readings = property(get_meter_readings, set_meter_readings)
    
    def add_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._customer_agreement = self
            self._meter_readings.append(obj)
        
    def remove_meter_readings(self, *meter_readings):
        for obj in meter_readings:
            obj._customer_agreement = None
            self._meter_readings.remove(obj)

    # <<< customer_agreement
    # @generated
    def __init__(self, service_delivery_points=[], customer=None, service_supplier=None, customer_account=None, standard_industry_code=None, end_device_controls=[], service_category=None, equipments=[], auxiliary_agreements=[], service_locations=[], pricing_structures=[], demand_response_program=None, meter_readings=[], **kw_args):
        """ Initialises a new 'CustomerAgreement' instance.
        """
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._customer = None
        self.customer = customer
        self._service_supplier = None
        self.service_supplier = service_supplier
        self._customer_account = None
        self.customer_account = customer_account
        self._standard_industry_code = None
        self.standard_industry_code = standard_industry_code
        self._end_device_controls = []
        self.end_device_controls = end_device_controls
        self._service_category = None
        self.service_category = service_category
        self._equipments = []
        self.equipments = equipments
        self._auxiliary_agreements = []
        self.auxiliary_agreements = auxiliary_agreements
        self._service_locations = []
        self.service_locations = service_locations
        self._pricing_structures = []
        self.pricing_structures = pricing_structures
        self._demand_response_program = None
        self.demand_response_program = demand_response_program
        self._meter_readings = []
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

    def get_customer_billing_infos(self):
        """ 
        """
        return self._customer_billing_infos

    def set_customer_billing_infos(self, value):
        for x in self._customer_billing_infos:
            x._customer_account = None
        for y in value:
            y._customer_account = self
        self._customer_billing_infos = value
            
    customer_billing_infos = property(get_customer_billing_infos, set_customer_billing_infos)
    
    def add_customer_billing_infos(self, *customer_billing_infos):
        for obj in customer_billing_infos:
            obj._customer_account = self
            self._customer_billing_infos.append(obj)
        
    def remove_customer_billing_infos(self, *customer_billing_infos):
        for obj in customer_billing_infos:
            obj._customer_account = None
            self._customer_billing_infos.remove(obj)

    def get_payment_transactions(self):
        """ All payment transactions for this customer account.
        """
        return self._payment_transactions

    def set_payment_transactions(self, value):
        for x in self._payment_transactions:
            x._customer_account = None
        for y in value:
            y._customer_account = self
        self._payment_transactions = value
            
    payment_transactions = property(get_payment_transactions, set_payment_transactions)
    
    def add_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._customer_account = self
            self._payment_transactions.append(obj)
        
    def remove_payment_transactions(self, *payment_transactions):
        for obj in payment_transactions:
            obj._customer_account = None
            self._payment_transactions.remove(obj)

    def get_erp_invoicees(self):
        """ 
        """
        return self._erp_invoicees

    def set_erp_invoicees(self, value):
        for x in self._erp_invoicees:
            x._customer_account = None
        for y in value:
            y._customer_account = self
        self._erp_invoicees = value
            
    erp_invoicees = property(get_erp_invoicees, set_erp_invoicees)
    
    def add_erp_invoicees(self, *erp_invoicees):
        for obj in erp_invoicees:
            obj._customer_account = self
            self._erp_invoicees.append(obj)
        
    def remove_erp_invoicees(self, *erp_invoicees):
        for obj in erp_invoicees:
            obj._customer_account = None
            self._erp_invoicees.remove(obj)

    def get_work_billing_infos(self):
        """ 
        """
        return self._work_billing_infos

    def set_work_billing_infos(self, value):
        for x in self._work_billing_infos:
            x._customer_account = None
        for y in value:
            y._customer_account = self
        self._work_billing_infos = value
            
    work_billing_infos = property(get_work_billing_infos, set_work_billing_infos)
    
    def add_work_billing_infos(self, *work_billing_infos):
        for obj in work_billing_infos:
            obj._customer_account = self
            self._work_billing_infos.append(obj)
        
    def remove_work_billing_infos(self, *work_billing_infos):
        for obj in work_billing_infos:
            obj._customer_account = None
            self._work_billing_infos.remove(obj)

    def get_customer_agreements(self):
        """ All agreements for this customer account.
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x._customer_account = None
        for y in value:
            y._customer_account = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._customer_account = self
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._customer_account = None
            self._customer_agreements.remove(obj)

    # <<< customer_account
    # @generated
    def __init__(self, budget_bill='', billing_cycle='', customer_billing_infos=[], payment_transactions=[], erp_invoicees=[], work_billing_infos=[], customer_agreements=[], **kw_args):
        """ Initialises a new 'CustomerAccount' instance.
        """
        self.budget_bill = budget_bill
        self.billing_cycle = billing_cycle
        self._customer_billing_infos = []
        self.customer_billing_infos = customer_billing_infos
        self._payment_transactions = []
        self.payment_transactions = payment_transactions
        self._erp_invoicees = []
        self.erp_invoicees = erp_invoicees
        self._work_billing_infos = []
        self.work_billing_infos = work_billing_infos
        self._customer_agreements = []
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

    pricing_structures = []
    
    def add_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.append(obj)
        
    def remove_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
	        self._pricing_structures.remove(obj)

    tariff_profiles = []
    
    def add_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.append(obj)
        
    def remove_tariff_profiles(self, *tariff_profiles):
        for obj in tariff_profiles:
	        self._tariff_profiles.remove(obj)

    # <<< tariff
    # @generated
    def __init__(self, start_date='', end_date='', pricing_structures=[], tariff_profiles=[], **kw_args):
        """ Initialises a new 'Tariff' instance.
        """
        self.start_date = start_date
        self.end_date = end_date
        self._pricing_structures = []
        self.pricing_structures = pricing_structures
        self._tariff_profiles = []
        self.tariff_profiles = tariff_profiles

        super(Tariff, self).__init__(**kw_args)
    # >>> tariff


class ServiceCategory(IdentifiedObject):
    """ Category of service provided to the customer.
    """
    # Kind of service. Values are: "refuse", "electricty", "other", "water", "tv_licence", "sewerage", "time", "internet", "gas", "rates", "heat"
    kind = 'refuse'

    def get_pricing_structures(self):
        """ All pricing structures applicable to this service category.
        """
        return self._pricing_structures

    def set_pricing_structures(self, value):
        for x in self._pricing_structures:
            x._service_category = None
        for y in value:
            y._service_category = self
        self._pricing_structures = value
            
    pricing_structures = property(get_pricing_structures, set_pricing_structures)
    
    def add_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
            obj._service_category = self
            self._pricing_structures.append(obj)
        
    def remove_pricing_structures(self, *pricing_structures):
        for obj in pricing_structures:
            obj._service_category = None
            self._pricing_structures.remove(obj)

    def get_customer_agreements(self):
        """ 
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for x in self._customer_agreements:
            x._service_category = None
        for y in value:
            y._service_category = self
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_category = self
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            obj._service_category = None
            self._customer_agreements.remove(obj)

    def get_service_delivery_points(self):
        """ All service delivery points that deliver this category of service.
        """
        return self._service_delivery_points

    def set_service_delivery_points(self, value):
        for x in self._service_delivery_points:
            x._service_category = None
        for y in value:
            y._service_category = self
        self._service_delivery_points = value
            
    service_delivery_points = property(get_service_delivery_points, set_service_delivery_points)
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_category = self
            self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
            obj._service_category = None
            self._service_delivery_points.remove(obj)

    def get_spaccounting_functions(self):
        """ 
        """
        return self._spaccounting_functions

    def set_spaccounting_functions(self, value):
        for x in self._spaccounting_functions:
            x._service_kind = None
        for y in value:
            y._service_kind = self
        self._spaccounting_functions = value
            
    spaccounting_functions = property(get_spaccounting_functions, set_spaccounting_functions)
    
    def add_spaccounting_functions(self, *spaccounting_functions):
        for obj in spaccounting_functions:
            obj._service_kind = self
            self._spaccounting_functions.append(obj)
        
    def remove_spaccounting_functions(self, *spaccounting_functions):
        for obj in spaccounting_functions:
            obj._service_kind = None
            self._spaccounting_functions.remove(obj)

    # <<< service_category
    # @generated
    def __init__(self, kind='refuse', pricing_structures=[], customer_agreements=[], service_delivery_points=[], spaccounting_functions=[], **kw_args):
        """ Initialises a new 'ServiceCategory' instance.
        """
        self.kind = kind
        self._pricing_structures = []
        self.pricing_structures = pricing_structures
        self._customer_agreements = []
        self.customer_agreements = customer_agreements
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._spaccounting_functions = []
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

    def get_subscribe_power_curve(self):
        """ SubscribePowerCurve specifies the cost according to a prcing structure.
        """
        return self._subscribe_power_curve

    def set_subscribe_power_curve(self, value):
        if self._subscribe_power_curve is not None:
            self._subscribe_power_curve._pricing_structure = None
            
        self._subscribe_power_curve = value
        if self._subscribe_power_curve is not None:
            self._subscribe_power_curve._pricing_structure = self
            
    subscribe_power_curve = property(get_subscribe_power_curve, set_subscribe_power_curve)

    service_delivery_points = []
    
    def add_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
	        self._service_delivery_points.append(obj)
        
    def remove_service_delivery_points(self, *service_delivery_points):
        for obj in service_delivery_points:
	        self._service_delivery_points.remove(obj)

    customer_agreements = []
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
	        self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
	        self._customer_agreements.remove(obj)

    def get_transactions(self):
        """ All transactions applying this pricing structure.
        """
        return self._transactions

    def set_transactions(self, value):
        for x in self._transactions:
            x._pricing_structure = None
        for y in value:
            y._pricing_structure = self
        self._transactions = value
            
    transactions = property(get_transactions, set_transactions)
    
    def add_transactions(self, *transactions):
        for obj in transactions:
            obj._pricing_structure = self
            self._transactions.append(obj)
        
    def remove_transactions(self, *transactions):
        for obj in transactions:
            obj._pricing_structure = None
            self._transactions.remove(obj)

    tariffs = []
    
    def add_tariffs(self, *tariffs):
        for obj in tariffs:
	        self._tariffs.append(obj)
        
    def remove_tariffs(self, *tariffs):
        for obj in tariffs:
	        self._tariffs.remove(obj)

    def get_power_quality_pricings(self):
        """ 
        """
        return self._power_quality_pricings

    def set_power_quality_pricings(self, value):
        for x in self._power_quality_pricings:
            x._pricing_structure = None
        for y in value:
            y._pricing_structure = self
        self._power_quality_pricings = value
            
    power_quality_pricings = property(get_power_quality_pricings, set_power_quality_pricings)
    
    def add_power_quality_pricings(self, *power_quality_pricings):
        for obj in power_quality_pricings:
            obj._pricing_structure = self
            self._power_quality_pricings.append(obj)
        
    def remove_power_quality_pricings(self, *power_quality_pricings):
        for obj in power_quality_pricings:
            obj._pricing_structure = None
            self._power_quality_pricings.remove(obj)

    def get_service_category(self):
        """ Service category to which this pricing structure applies.
        """
        return self._service_category

    def set_service_category(self, value):
        if self._service_category is not None:
            filtered = [x for x in self.service_category.pricing_structures if x != self]
            self._service_category._pricing_structures = filtered
            
        self._service_category = value
        if self._service_category is not None:
            self._service_category._pricing_structures.append(self)

    service_category = property(get_service_category, set_service_category)

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
        self._subscribe_power_curve = None
        self.subscribe_power_curve = subscribe_power_curve
        self._service_delivery_points = []
        self.service_delivery_points = service_delivery_points
        self._customer_agreements = []
        self.customer_agreements = customer_agreements
        self._transactions = []
        self.transactions = transactions
        self._tariffs = []
        self.tariffs = tariffs
        self._power_quality_pricings = []
        self.power_quality_pricings = power_quality_pricings
        self._service_category = None
        self.service_category = service_category

        super(PricingStructure, self).__init__(**kw_args)
    # >>> pricing_structure


# <<< customers
# @generated
# >>> customers

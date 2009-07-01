# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.informative.inf_erpsupport import ErpOrganisation
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Agreement
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.Financial"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#Financial"

class GenerationProvider(ErpOrganisation):
    """ The energy seller in the energy marketplace.
    """
    def get_generating_units(self):
        """ A GenerationProvider operates one or more GeneratingUnits.
        """
        return self._generating_units

    def set_generating_units(self, value):
        for x in self._generating_units:
            x._operated_by_generation_provider = None
        for y in value:
            y._operated_by_generation_provider = self
        self._generating_units = value
            
    generating_units = property(get_generating_units, set_generating_units)
    
    def add_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._operated_by_generation_provider = self
            self._generating_units.append(obj)
        
    def remove_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._operated_by_generation_provider = None
            self._generating_units.remove(obj)

    def get_service_point(self):
        """ A GenerationProvider has one or more ServicePoints where energy is injected into the network.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x._generation_provider = None
        for y in value:
            y._generation_provider = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._generation_provider = self
            self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._generation_provider = None
            self._service_point.remove(obj)

    def get_energy_products(self):
        """ 
        """
        return self._energy_products

    def set_energy_products(self, value):
        for x in self._energy_products:
            x._generation_provider = None
        for y in value:
            y._generation_provider = self
        self._energy_products = value
            
    energy_products = property(get_energy_products, set_energy_products)
    
    def add_energy_products(self, *energy_products):
        for obj in energy_products:
            obj._generation_provider = self
            self._energy_products.append(obj)
        
    def remove_energy_products(self, *energy_products):
        for obj in energy_products:
            obj._generation_provider = None
            self._energy_products.remove(obj)

    # <<< generation_provider
    # @generated
    def __init__(self, generating_units=[], service_point=[], energy_products=[], **kw_args):
        """ Initialises a new 'GenerationProvider' instance.
        """
        self._generating_units = []
        self.generating_units = generating_units
        self._service_point = []
        self.service_point = service_point
        self._energy_products = []
        self.energy_products = energy_products

        super(GenerationProvider, self).__init__(**kw_args)
    # >>> generation_provider


class TransmissionProduct(IdentifiedObject):
    location_for = []
    
    def add_location_for(self, *location_for):
        for obj in location_for:
	        self._location_for.append(obj)
        
    def remove_location_for(self, *location_for):
        for obj in location_for:
	        self._location_for.remove(obj)

    offers = []
    
    def add_offers(self, *offers):
        for obj in offers:
	        self._offers.append(obj)
        
    def remove_offers(self, *offers):
        for obj in offers:
	        self._offers.remove(obj)

    def get_transmission_provider(self):
        """ A TransmissionProvider offers a TransmissionProduct.
        """
        return self._transmission_provider

    def set_transmission_provider(self, value):
        if self._transmission_provider is not None:
            filtered = [x for x in self.transmission_provider.transmission_products if x != self]
            self._transmission_provider._transmission_products = filtered
            
        self._transmission_provider = value
        if self._transmission_provider is not None:
            self._transmission_provider._transmission_products.append(self)

    transmission_provider = property(get_transmission_provider, set_transmission_provider)

    # <<< transmission_product
    # @generated
    def __init__(self, location_for=[], offers=[], transmission_provider=None, **kw_args):
        """ Initialises a new 'TransmissionProduct' instance.
        """
        self._location_for = []
        self.location_for = location_for
        self._offers = []
        self.offers = offers
        self._transmission_provider = None
        self.transmission_provider = transmission_provider

        super(TransmissionProduct, self).__init__(**kw_args)
    # >>> transmission_product


class CustomerConsumer(ErpOrganisation):
    """ The energy buyer in the energy marketplace.
    """
    def get_service_point(self):
        """ A CustomerConsumer may have one or more ServicePoints.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x._customer_consumer = None
        for y in value:
            y._customer_consumer = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._customer_consumer = self
            self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._customer_consumer = None
            self._service_point.remove(obj)

    def get_tie_lines(self):
        """ A  ControlAreaOperator or CustomerConsumer may ring their perimeter with metering, which can create a unique SubControlArea at the collection of metering points, called a TieLine.
        """
        return self._tie_lines

    def set_tie_lines(self, value):
        for x in self._tie_lines:
            x._customer_consumer = None
        for y in value:
            y._customer_consumer = self
        self._tie_lines = value
            
    tie_lines = property(get_tie_lines, set_tie_lines)
    
    def add_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._customer_consumer = self
            self._tie_lines.append(obj)
        
    def remove_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._customer_consumer = None
            self._tie_lines.remove(obj)

    # <<< customer_consumer
    # @generated
    def __init__(self, service_point=[], tie_lines=[], **kw_args):
        """ Initialises a new 'CustomerConsumer' instance.
        """
        self._service_point = []
        self.service_point = service_point
        self._tie_lines = []
        self.tie_lines = tie_lines

        super(CustomerConsumer, self).__init__(**kw_args)
    # >>> customer_consumer


class ControlAreaOperator(ErpOrganisation):
    """ Operates the Control Area. Approves and implements energy transactions. Verifies both Inter-Control Area and Intra-Control Area transactions for the power system before granting approval (and implementing) the transactions.
    """
    def get_controlled_by(self):
        """ A ControlAreaCompany controls a ControlArea.
        """
        return self._controlled_by

    def set_controlled_by(self, value):
        if self._controlled_by is not None:
            self._controlled_by._controls = None
            
        self._controlled_by = value
        if self._controlled_by is not None:
            self._controlled_by._controls = self
            
    controlled_by = property(get_controlled_by, set_controlled_by)

    def get_ancillary_service(self):
        """ Sale of ancillary services provided by ControlAreaOperators.
        """
        return self._ancillary_service

    def set_ancillary_service(self, value):
        for x in self._ancillary_service:
            x._control_area_operator = None
        for y in value:
            y._control_area_operator = self
        self._ancillary_service = value
            
    ancillary_service = property(get_ancillary_service, set_ancillary_service)
    
    def add_ancillary_service(self, *ancillary_service):
        for obj in ancillary_service:
            obj._control_area_operator = self
            self._ancillary_service.append(obj)
        
    def remove_ancillary_service(self, *ancillary_service):
        for obj in ancillary_service:
            obj._control_area_operator = None
            self._ancillary_service.remove(obj)

    tie_lines = []
    
    def add_tie_lines(self, *tie_lines):
        for obj in tie_lines:
	        self._tie_lines.append(obj)
        
    def remove_tie_lines(self, *tie_lines):
        for obj in tie_lines:
	        self._tie_lines.remove(obj)

    # <<< control_area_operator
    # @generated
    def __init__(self, controlled_by=None, ancillary_service=[], tie_lines=[], **kw_args):
        """ Initialises a new 'ControlAreaOperator' instance.
        """
        self._controlled_by = None
        self.controlled_by = controlled_by
        self._ancillary_service = []
        self.ancillary_service = ancillary_service
        self._tie_lines = []
        self.tie_lines = tie_lines

        super(ControlAreaOperator, self).__init__(**kw_args)
    # >>> control_area_operator


class IntSchedAgreement(Agreement):
    """ A type of agreement that provides the default method by which interchange schedules are to be integrated to obtain hourly energy schedules for accounting.
    """
    organisations = []
    
    def add_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.remove(obj)

    # <<< int_sched_agreement
    # @generated
    def __init__(self, organisations=[], **kw_args):
        """ Initialises a new 'IntSchedAgreement' instance.
        """
        self._organisations = []
        self.organisations = organisations

        super(IntSchedAgreement, self).__init__(**kw_args)
    # >>> int_sched_agreement


class Marketer(ErpOrganisation):
    """ Matches buyers and sellers, and secures transmission (and other ancillary services) needed to complete the energy transaction.
    """
    def get_holds_title_to_energy_products(self):
        """ A Marketer holds title to an EnergyProduct.
        """
        return self._holds_title_to_energy_products

    def set_holds_title_to_energy_products(self, value):
        for x in self._holds_title_to_energy_products:
            x._title_held_by_marketer = None
        for y in value:
            y._title_held_by_marketer = self
        self._holds_title_to_energy_products = value
            
    holds_title_to_energy_products = property(get_holds_title_to_energy_products, set_holds_title_to_energy_products)
    
    def add_holds_title_to_energy_products(self, *holds_title_to_energy_products):
        for obj in holds_title_to_energy_products:
            obj._title_held_by_marketer = self
            self._holds_title_to_energy_products.append(obj)
        
    def remove_holds_title_to_energy_products(self, *holds_title_to_energy_products):
        for obj in holds_title_to_energy_products:
            obj._title_held_by_marketer = None
            self._holds_title_to_energy_products.remove(obj)

    def get_resold_by(self):
        """ A ServiceReservation may be resold by a Marketer.
        """
        return self._resold_by

    def set_resold_by(self, value):
        if self._resold_by is not None:
            self._resold_by._resells = None
            
        self._resold_by = value
        if self._resold_by is not None:
            self._resold_by._resells = self
            
    resold_by = property(get_resold_by, set_resold_by)

    def get_held_by(self):
        """ A Marketer holds title to a ServiceReservation.
        """
        return self._held_by

    def set_held_by(self, value):
        for x in self._held_by:
            x._holds = None
        for y in value:
            y._holds = self
        self._held_by = value
            
    held_by = property(get_held_by, set_held_by)
    
    def add_held_by(self, *held_by):
        for obj in held_by:
            obj._holds = self
            self._held_by.append(obj)
        
    def remove_held_by(self, *held_by):
        for obj in held_by:
            obj._holds = None
            self._held_by.remove(obj)

    resells_energy_product = []
    
    def add_resells_energy_product(self, *resells_energy_product):
        for obj in resells_energy_product:
	        self._resells_energy_product.append(obj)
        
    def remove_resells_energy_product(self, *resells_energy_product):
        for obj in resells_energy_product:
	        self._resells_energy_product.remove(obj)

    # <<< marketer
    # @generated
    def __init__(self, holds_title_to_energy_products=[], resold_by=None, held_by=[], resells_energy_product=[], **kw_args):
        """ Initialises a new 'Marketer' instance.
        """
        self._holds_title_to_energy_products = []
        self.holds_title_to_energy_products = holds_title_to_energy_products
        self._resold_by = None
        self.resold_by = resold_by
        self._held_by = []
        self.held_by = held_by
        self._resells_energy_product = []
        self.resells_energy_product = resells_energy_product

        super(Marketer, self).__init__(**kw_args)
    # >>> marketer


class FinancialVersion(Element):
 
    date = ''

 
    version = ''

    # <<< financial_version
    # @generated
    def __init__(self, date='', version='', **kw_args):
        """ Initialises a new 'FinancialVersion' instance.
        """
        self.date = date
        self.version = version

        super(FinancialVersion, self).__init__(**kw_args)
    # >>> financial_version


class OpenAccessProduct(Agreement):
    """ Contracts for services offered commercially.
    """
    def get_ancillary_services(self):
        """ AncillaryServices are sold through a contract which offers a particular OpenAccessProduct.
        """
        return self._ancillary_services

    def set_ancillary_services(self, value):
        for x in self._ancillary_services:
            x._open_access_product = None
        for y in value:
            y._open_access_product = self
        self._ancillary_services = value
            
    ancillary_services = property(get_ancillary_services, set_ancillary_services)
    
    def add_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            obj._open_access_product = self
            self._ancillary_services.append(obj)
        
    def remove_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            obj._open_access_product = None
            self._ancillary_services.remove(obj)

    def get_provided_by_transmission_service(self):
        """ A TransmissionService is sold according to the terms of a particular OpenAccessProduct agreement.
        """
        return self._provided_by_transmission_service

    def set_provided_by_transmission_service(self, value):
        for x in self._provided_by_transmission_service:
            x._trans_contract_for = None
        for y in value:
            y._trans_contract_for = self
        self._provided_by_transmission_service = value
            
    provided_by_transmission_service = property(get_provided_by_transmission_service, set_provided_by_transmission_service)
    
    def add_provided_by_transmission_service(self, *provided_by_transmission_service):
        for obj in provided_by_transmission_service:
            obj._trans_contract_for = self
            self._provided_by_transmission_service.append(obj)
        
    def remove_provided_by_transmission_service(self, *provided_by_transmission_service):
        for obj in provided_by_transmission_service:
            obj._trans_contract_for = None
            self._provided_by_transmission_service.remove(obj)

    # <<< open_access_product
    # @generated
    def __init__(self, ancillary_services=[], provided_by_transmission_service=[], **kw_args):
        """ Initialises a new 'OpenAccessProduct' instance.
        """
        self._ancillary_services = []
        self.ancillary_services = ancillary_services
        self._provided_by_transmission_service = []
        self.provided_by_transmission_service = provided_by_transmission_service

        super(OpenAccessProduct, self).__init__(**kw_args)
    # >>> open_access_product


class TransmissionProvider(ErpOrganisation):
    """ Provider of the transmission capacity (interconnecting wires between Generation and Consumption) required to fulfill and Energy Transaction's energy exchange. Posts information for transmission paths and AvailableTransmissionCapacities on a reservation node. Buys and sells its products and services on the same reservation node.
    """
    def get_service_point(self):
        """ A TransmissionProvider registers one or more ServicePoints.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x._transmission_provider = None
        for y in value:
            y._transmission_provider = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._transmission_provider = self
            self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._transmission_provider = None
            self._service_point.remove(obj)

    def get_transmission_products(self):
        """ A TransmissionProvider offers a TransmissionProduct.
        """
        return self._transmission_products

    def set_transmission_products(self, value):
        for x in self._transmission_products:
            x._transmission_provider = None
        for y in value:
            y._transmission_provider = self
        self._transmission_products = value
            
    transmission_products = property(get_transmission_products, set_transmission_products)
    
    def add_transmission_products(self, *transmission_products):
        for obj in transmission_products:
            obj._transmission_provider = self
            self._transmission_products.append(obj)
        
    def remove_transmission_products(self, *transmission_products):
        for obj in transmission_products:
            obj._transmission_provider = None
            self._transmission_products.remove(obj)

    flowgate = []
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
	        self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
	        self._flowgate.remove(obj)

    def get_for_(self):
        """ Part of the LossProfile for an EnergyTransaction may be a loss for a TransmissionProvider.
        """
        return self._for_

    def set_for_(self, value):
        for x in self._for_:
            x._has_loss_ = None
        for y in value:
            y._has_loss_ = self
        self._for_ = value
            
    for_ = property(get_for_, set_for_)
    
    def add_for_(self, *for_):
        for obj in for_:
            obj._has_loss_ = self
            self._for_.append(obj)
        
    def remove_for_(self, *for_):
        for obj in for_:
            obj._has_loss_ = None
            self._for_.remove(obj)

    def get_offered_by(self):
        """ The combination of a TransmissionProduct on a TransmissionPath is a TransmissionService, for which the TransmissionProvider must post one or two ATC's (AvailableTransmissionCapacity - Amount of possible flow by  direction).
        """
        return self._offered_by

    def set_offered_by(self, value):
        for x in self._offered_by:
            x._offers = None
        for y in value:
            y._offers = self
        self._offered_by = value
            
    offered_by = property(get_offered_by, set_offered_by)
    
    def add_offered_by(self, *offered_by):
        for obj in offered_by:
            obj._offers = self
            self._offered_by.append(obj)
        
    def remove_offered_by(self, *offered_by):
        for obj in offered_by:
            obj._offers = None
            self._offered_by.remove(obj)

    def get_sold_by(self):
        """ A TransmissionProvider sells the right to transmit energy across the wires in a ServiceReservation.
        """
        return self._sold_by

    def set_sold_by(self, value):
        for x in self._sold_by:
            x._sells = None
        for y in value:
            y._sells = self
        self._sold_by = value
            
    sold_by = property(get_sold_by, set_sold_by)
    
    def add_sold_by(self, *sold_by):
        for obj in sold_by:
            obj._sells = self
            self._sold_by.append(obj)
        
    def remove_sold_by(self, *sold_by):
        for obj in sold_by:
            obj._sells = None
            self._sold_by.remove(obj)

    ancillary_services = []
    
    def add_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
	        self._ancillary_services.append(obj)
        
    def remove_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
	        self._ancillary_services.remove(obj)

    # <<< transmission_provider
    # @generated
    def __init__(self, service_point=[], transmission_products=[], flowgate=[], for_=[], offered_by=[], sold_by=[], ancillary_services=[], **kw_args):
        """ Initialises a new 'TransmissionProvider' instance.
        """
        self._service_point = []
        self.service_point = service_point
        self._transmission_products = []
        self.transmission_products = transmission_products
        self._flowgate = []
        self.flowgate = flowgate
        self._for_ = []
        self.for_ = for_
        self._offered_by = []
        self.offered_by = offered_by
        self._sold_by = []
        self.sold_by = sold_by
        self._ancillary_services = []
        self.ancillary_services = ancillary_services

        super(TransmissionProvider, self).__init__(**kw_args)
    # >>> transmission_provider


# <<< financial
# @generated
# >>> financial

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
    # <<< generation_provider
    # @generated
    def __init__(self, generating_units=None, service_point=None, energy_products=None, **kw_args):
        """ Initialises a new 'GenerationProvider' instance.
        """
        
        self._generating_units = []
        if generating_units is None:
            self.generating_units = []
        else:
            self.generating_units = generating_units
        self._service_point = []
        if service_point is None:
            self.service_point = []
        else:
            self.service_point = service_point
        self._energy_products = []
        if energy_products is None:
            self.energy_products = []
        else:
            self.energy_products = energy_products

        super(GenerationProvider, self).__init__(**kw_args)
    # >>> generation_provider
        
    # <<< generating_units
    # @generated
    def get_generating_units(self):
        """ A GenerationProvider operates one or more GeneratingUnits.
        """
        return self._generating_units

    def set_generating_units(self, value):
        for x in self._generating_units:
            x.operated_by_generation_provider = None
        for y in value:
            y.operated_by_generation_provider = self
        self._generating_units = value
            
    generating_units = property(get_generating_units, set_generating_units)
    
    def add_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._operated_by_generation_provider = self
            if obj not in self._generating_units:
                self._generating_units.append(obj)
        
    def remove_generating_units(self, *generating_units):
        for obj in generating_units:
            obj._operated_by_generation_provider = None
            self._generating_units.remove(obj)
    # >>> generating_units

    # <<< service_point
    # @generated
    def get_service_point(self):
        """ A GenerationProvider has one or more ServicePoints where energy is injected into the network.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x.generation_provider = None
        for y in value:
            y.generation_provider = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._generation_provider = self
            if obj not in self._service_point:
                self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._generation_provider = None
            self._service_point.remove(obj)
    # >>> service_point

    # <<< energy_products
    # @generated
    def get_energy_products(self):
        """ 
        """
        return self._energy_products

    def set_energy_products(self, value):
        for x in self._energy_products:
            x.generation_provider = None
        for y in value:
            y.generation_provider = self
        self._energy_products = value
            
    energy_products = property(get_energy_products, set_energy_products)
    
    def add_energy_products(self, *energy_products):
        for obj in energy_products:
            obj._generation_provider = self
            if obj not in self._energy_products:
                self._energy_products.append(obj)
        
    def remove_energy_products(self, *energy_products):
        for obj in energy_products:
            obj._generation_provider = None
            self._energy_products.remove(obj)
    # >>> energy_products



class TransmissionProduct(IdentifiedObject):
    # <<< transmission_product
    # @generated
    def __init__(self, location_for=None, offers=None, transmission_provider=None, **kw_args):
        """ Initialises a new 'TransmissionProduct' instance.
        """
        
        self._location_for = []
        if location_for is None:
            self.location_for = []
        else:
            self.location_for = location_for
        self._offers = []
        if offers is None:
            self.offers = []
        else:
            self.offers = offers
        self._transmission_provider = None
        self.transmission_provider = transmission_provider

        super(TransmissionProduct, self).__init__(**kw_args)
    # >>> transmission_product
        
    # <<< location_for
    # @generated
    def get_location_for(self):
        """ A transmission product is located on a transmission path.
        """
        return self._location_for

    def set_location_for(self, value):
        for p in self.location_for:
            filtered = [q for q in p.located_on if q != self]
            p._located_on = filtered
        for r in value:
            if self not in r._located_on:
                r._located_on.append(self)
        self._location_for = value
            
    location_for = property(get_location_for, set_location_for)
    
    def add_location_for(self, *location_for):
        for obj in location_for:
            if self not in obj._located_on:
                obj._located_on.append(self)
            self._location_for.append(obj)
        
    def remove_location_for(self, *location_for):
        for obj in location_for:
            if self in obj._located_on:
                obj._located_on.remove(self)
            self._location_for.remove(obj)
    # >>> location_for

    # <<< offers
    # @generated
    def get_offers(self):
        """ A transmission product is offered as a transmission service along a transmission path.
        """
        return self._offers

    def set_offers(self, value):
        for p in self.offers:
            filtered = [q for q in p.offered_as if q != self]
            p._offered_as = filtered
        for r in value:
            if self not in r._offered_as:
                r._offered_as.append(self)
        self._offers = value
            
    offers = property(get_offers, set_offers)
    
    def add_offers(self, *offers):
        for obj in offers:
            if self not in obj._offered_as:
                obj._offered_as.append(self)
            self._offers.append(obj)
        
    def remove_offers(self, *offers):
        for obj in offers:
            if self in obj._offered_as:
                obj._offered_as.remove(self)
            self._offers.remove(obj)
    # >>> offers

    # <<< transmission_provider
    # @generated
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
            if self not in self._transmission_provider._transmission_products:
                self._transmission_provider._transmission_products.append(self)

    transmission_provider = property(get_transmission_provider, set_transmission_provider)
    # >>> transmission_provider



class CustomerConsumer(ErpOrganisation):
    """ The energy buyer in the energy marketplace.
    """
    # <<< customer_consumer
    # @generated
    def __init__(self, service_point=None, tie_lines=None, **kw_args):
        """ Initialises a new 'CustomerConsumer' instance.
        """
        
        self._service_point = []
        if service_point is None:
            self.service_point = []
        else:
            self.service_point = service_point
        self._tie_lines = []
        if tie_lines is None:
            self.tie_lines = []
        else:
            self.tie_lines = tie_lines

        super(CustomerConsumer, self).__init__(**kw_args)
    # >>> customer_consumer
        
    # <<< service_point
    # @generated
    def get_service_point(self):
        """ A CustomerConsumer may have one or more ServicePoints.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x.customer_consumer = None
        for y in value:
            y.customer_consumer = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._customer_consumer = self
            if obj not in self._service_point:
                self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._customer_consumer = None
            self._service_point.remove(obj)
    # >>> service_point

    # <<< tie_lines
    # @generated
    def get_tie_lines(self):
        """ A  ControlAreaOperator or CustomerConsumer may ring their perimeter with metering, which can create a unique SubControlArea at the collection of metering points, called a TieLine.
        """
        return self._tie_lines

    def set_tie_lines(self, value):
        for x in self._tie_lines:
            x.customer_consumer = None
        for y in value:
            y.customer_consumer = self
        self._tie_lines = value
            
    tie_lines = property(get_tie_lines, set_tie_lines)
    
    def add_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._customer_consumer = self
            if obj not in self._tie_lines:
                self._tie_lines.append(obj)
        
    def remove_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            obj._customer_consumer = None
            self._tie_lines.remove(obj)
    # >>> tie_lines



class ControlAreaOperator(ErpOrganisation):
    """ Operates the Control Area. Approves and implements energy transactions. Verifies both Inter-Control Area and Intra-Control Area transactions for the power system before granting approval (and implementing) the transactions.
    """
    # <<< control_area_operator
    # @generated
    def __init__(self, controlled_by=None, ancillary_service=None, tie_lines=None, **kw_args):
        """ Initialises a new 'ControlAreaOperator' instance.
        """
        
        self._controlled_by = None
        self.controlled_by = controlled_by
        self._ancillary_service = []
        if ancillary_service is None:
            self.ancillary_service = []
        else:
            self.ancillary_service = ancillary_service
        self._tie_lines = []
        if tie_lines is None:
            self.tie_lines = []
        else:
            self.tie_lines = tie_lines

        super(ControlAreaOperator, self).__init__(**kw_args)
    # >>> control_area_operator
        
    # <<< controlled_by
    # @generated
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
    # >>> controlled_by

    # <<< ancillary_service
    # @generated
    def get_ancillary_service(self):
        """ Sale of ancillary services provided by ControlAreaOperators.
        """
        return self._ancillary_service

    def set_ancillary_service(self, value):
        for x in self._ancillary_service:
            x.control_area_operator = None
        for y in value:
            y.control_area_operator = self
        self._ancillary_service = value
            
    ancillary_service = property(get_ancillary_service, set_ancillary_service)
    
    def add_ancillary_service(self, *ancillary_service):
        for obj in ancillary_service:
            obj._control_area_operator = self
            if obj not in self._ancillary_service:
                self._ancillary_service.append(obj)
        
    def remove_ancillary_service(self, *ancillary_service):
        for obj in ancillary_service:
            obj._control_area_operator = None
            self._ancillary_service.remove(obj)
    # >>> ancillary_service

    # <<< tie_lines
    # @generated
    def get_tie_lines(self):
        """ A ControlAreaOperator has a collection of tie points that ring the ControlArea, called a TieLine.
        """
        return self._tie_lines

    def set_tie_lines(self, value):
        for p in self.tie_lines:
            filtered = [q for q in p.control_area_operators if q != self]
            p._control_area_operators = filtered
        for r in value:
            if self not in r._control_area_operators:
                r._control_area_operators.append(self)
        self._tie_lines = value
            
    tie_lines = property(get_tie_lines, set_tie_lines)
    
    def add_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            if self not in obj._control_area_operators:
                obj._control_area_operators.append(self)
            self._tie_lines.append(obj)
        
    def remove_tie_lines(self, *tie_lines):
        for obj in tie_lines:
            if self in obj._control_area_operators:
                obj._control_area_operators.remove(self)
            self._tie_lines.remove(obj)
    # >>> tie_lines



class IntSchedAgreement(Agreement):
    """ A type of agreement that provides the default method by which interchange schedules are to be integrated to obtain hourly energy schedules for accounting.
    """
    # <<< int_sched_agreement
    # @generated
    def __init__(self, organisations=None, **kw_args):
        """ Initialises a new 'IntSchedAgreement' instance.
        """
        
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations

        super(IntSchedAgreement, self).__init__(**kw_args)
    # >>> int_sched_agreement
        
    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self.organisations:
            filtered = [q for q in p.int_sched_agreement if q != self]
            p._int_sched_agreement = filtered
        for r in value:
            if self not in r._int_sched_agreement:
                r._int_sched_agreement.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._int_sched_agreement:
                obj._int_sched_agreement.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._int_sched_agreement:
                obj._int_sched_agreement.remove(self)
            self._organisations.remove(obj)
    # >>> organisations



class Marketer(ErpOrganisation):
    """ Matches buyers and sellers, and secures transmission (and other ancillary services) needed to complete the energy transaction.
    """
    # <<< marketer
    # @generated
    def __init__(self, holds_title_to_energy_products=None, resold_by=None, held_by=None, resells_energy_product=None, **kw_args):
        """ Initialises a new 'Marketer' instance.
        """
        
        self._holds_title_to_energy_products = []
        if holds_title_to_energy_products is None:
            self.holds_title_to_energy_products = []
        else:
            self.holds_title_to_energy_products = holds_title_to_energy_products
        self._resold_by = None
        self.resold_by = resold_by
        self._held_by = []
        if held_by is None:
            self.held_by = []
        else:
            self.held_by = held_by
        self._resells_energy_product = []
        if resells_energy_product is None:
            self.resells_energy_product = []
        else:
            self.resells_energy_product = resells_energy_product

        super(Marketer, self).__init__(**kw_args)
    # >>> marketer
        
    # <<< holds_title_to_energy_products
    # @generated
    def get_holds_title_to_energy_products(self):
        """ A Marketer holds title to an EnergyProduct.
        """
        return self._holds_title_to_energy_products

    def set_holds_title_to_energy_products(self, value):
        for x in self._holds_title_to_energy_products:
            x.title_held_by_marketer = None
        for y in value:
            y.title_held_by_marketer = self
        self._holds_title_to_energy_products = value
            
    holds_title_to_energy_products = property(get_holds_title_to_energy_products, set_holds_title_to_energy_products)
    
    def add_holds_title_to_energy_products(self, *holds_title_to_energy_products):
        for obj in holds_title_to_energy_products:
            obj._title_held_by_marketer = self
            if obj not in self._holds_title_to_energy_products:
                self._holds_title_to_energy_products.append(obj)
        
    def remove_holds_title_to_energy_products(self, *holds_title_to_energy_products):
        for obj in holds_title_to_energy_products:
            obj._title_held_by_marketer = None
            self._holds_title_to_energy_products.remove(obj)
    # >>> holds_title_to_energy_products

    # <<< resold_by
    # @generated
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
    # >>> resold_by

    # <<< held_by
    # @generated
    def get_held_by(self):
        """ A Marketer holds title to a ServiceReservation.
        """
        return self._held_by

    def set_held_by(self, value):
        for x in self._held_by:
            x.holds = None
        for y in value:
            y.holds = self
        self._held_by = value
            
    held_by = property(get_held_by, set_held_by)
    
    def add_held_by(self, *held_by):
        for obj in held_by:
            obj._holds = self
            if obj not in self._held_by:
                self._held_by.append(obj)
        
    def remove_held_by(self, *held_by):
        for obj in held_by:
            obj._holds = None
            self._held_by.remove(obj)
    # >>> held_by

    # <<< resells_energy_product
    # @generated
    def get_resells_energy_product(self):
        """ A Marketer may resell an EnergyProduct.
        """
        return self._resells_energy_product

    def set_resells_energy_product(self, value):
        for p in self.resells_energy_product:
            filtered = [q for q in p.resold_by_marketers if q != self]
            p._resold_by_marketers = filtered
        for r in value:
            if self not in r._resold_by_marketers:
                r._resold_by_marketers.append(self)
        self._resells_energy_product = value
            
    resells_energy_product = property(get_resells_energy_product, set_resells_energy_product)
    
    def add_resells_energy_product(self, *resells_energy_product):
        for obj in resells_energy_product:
            if self not in obj._resold_by_marketers:
                obj._resold_by_marketers.append(self)
            self._resells_energy_product.append(obj)
        
    def remove_resells_energy_product(self, *resells_energy_product):
        for obj in resells_energy_product:
            if self in obj._resold_by_marketers:
                obj._resold_by_marketers.remove(self)
            self._resells_energy_product.remove(obj)
    # >>> resells_energy_product



class FinancialVersion(Element):
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
    # <<< open_access_product
    # @generated
    def __init__(self, ancillary_services=None, provided_by_transmission_service=None, **kw_args):
        """ Initialises a new 'OpenAccessProduct' instance.
        """
        
        self._ancillary_services = []
        if ancillary_services is None:
            self.ancillary_services = []
        else:
            self.ancillary_services = ancillary_services
        self._provided_by_transmission_service = []
        if provided_by_transmission_service is None:
            self.provided_by_transmission_service = []
        else:
            self.provided_by_transmission_service = provided_by_transmission_service

        super(OpenAccessProduct, self).__init__(**kw_args)
    # >>> open_access_product
        
    # <<< ancillary_services
    # @generated
    def get_ancillary_services(self):
        """ AncillaryServices are sold through a contract which offers a particular OpenAccessProduct.
        """
        return self._ancillary_services

    def set_ancillary_services(self, value):
        for x in self._ancillary_services:
            x.open_access_product = None
        for y in value:
            y.open_access_product = self
        self._ancillary_services = value
            
    ancillary_services = property(get_ancillary_services, set_ancillary_services)
    
    def add_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            obj._open_access_product = self
            if obj not in self._ancillary_services:
                self._ancillary_services.append(obj)
        
    def remove_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            obj._open_access_product = None
            self._ancillary_services.remove(obj)
    # >>> ancillary_services

    # <<< provided_by_transmission_service
    # @generated
    def get_provided_by_transmission_service(self):
        """ A TransmissionService is sold according to the terms of a particular OpenAccessProduct agreement.
        """
        return self._provided_by_transmission_service

    def set_provided_by_transmission_service(self, value):
        for x in self._provided_by_transmission_service:
            x.trans_contract_for = None
        for y in value:
            y.trans_contract_for = self
        self._provided_by_transmission_service = value
            
    provided_by_transmission_service = property(get_provided_by_transmission_service, set_provided_by_transmission_service)
    
    def add_provided_by_transmission_service(self, *provided_by_transmission_service):
        for obj in provided_by_transmission_service:
            obj._trans_contract_for = self
            if obj not in self._provided_by_transmission_service:
                self._provided_by_transmission_service.append(obj)
        
    def remove_provided_by_transmission_service(self, *provided_by_transmission_service):
        for obj in provided_by_transmission_service:
            obj._trans_contract_for = None
            self._provided_by_transmission_service.remove(obj)
    # >>> provided_by_transmission_service



class TransmissionProvider(ErpOrganisation):
    """ Provider of the transmission capacity (interconnecting wires between Generation and Consumption) required to fulfill and Energy Transaction's energy exchange. Posts information for transmission paths and AvailableTransmissionCapacities on a reservation node. Buys and sells its products and services on the same reservation node.
    """
    # <<< transmission_provider
    # @generated
    def __init__(self, service_point=None, transmission_products=None, flowgate=None, for_=None, offered_by=None, sold_by=None, ancillary_services=None, **kw_args):
        """ Initialises a new 'TransmissionProvider' instance.
        """
        
        self._service_point = []
        if service_point is None:
            self.service_point = []
        else:
            self.service_point = service_point
        self._transmission_products = []
        if transmission_products is None:
            self.transmission_products = []
        else:
            self.transmission_products = transmission_products
        self._flowgate = []
        if flowgate is None:
            self.flowgate = []
        else:
            self.flowgate = flowgate
        self._for_ = []
        if for_ is None:
            self.for_ = []
        else:
            self.for_ = for_
        self._offered_by = []
        if offered_by is None:
            self.offered_by = []
        else:
            self.offered_by = offered_by
        self._sold_by = []
        if sold_by is None:
            self.sold_by = []
        else:
            self.sold_by = sold_by
        self._ancillary_services = []
        if ancillary_services is None:
            self.ancillary_services = []
        else:
            self.ancillary_services = ancillary_services

        super(TransmissionProvider, self).__init__(**kw_args)
    # >>> transmission_provider
        
    # <<< service_point
    # @generated
    def get_service_point(self):
        """ A TransmissionProvider registers one or more ServicePoints.
        """
        return self._service_point

    def set_service_point(self, value):
        for x in self._service_point:
            x.transmission_provider = None
        for y in value:
            y.transmission_provider = self
        self._service_point = value
            
    service_point = property(get_service_point, set_service_point)
    
    def add_service_point(self, *service_point):
        for obj in service_point:
            obj._transmission_provider = self
            if obj not in self._service_point:
                self._service_point.append(obj)
        
    def remove_service_point(self, *service_point):
        for obj in service_point:
            obj._transmission_provider = None
            self._service_point.remove(obj)
    # >>> service_point

    # <<< transmission_products
    # @generated
    def get_transmission_products(self):
        """ A TransmissionProvider offers a TransmissionProduct.
        """
        return self._transmission_products

    def set_transmission_products(self, value):
        for x in self._transmission_products:
            x.transmission_provider = None
        for y in value:
            y.transmission_provider = self
        self._transmission_products = value
            
    transmission_products = property(get_transmission_products, set_transmission_products)
    
    def add_transmission_products(self, *transmission_products):
        for obj in transmission_products:
            obj._transmission_provider = self
            if obj not in self._transmission_products:
                self._transmission_products.append(obj)
        
    def remove_transmission_products(self, *transmission_products):
        for obj in transmission_products:
            obj._transmission_provider = None
            self._transmission_products.remove(obj)
    # >>> transmission_products

    # <<< flowgate
    # @generated
    def get_flowgate(self):
        """ A flowgate can be reciprocal flowgate for 0 to n transmission providers. A transmission provider may be a reciprocal entity for 0 to n flowgates.
        """
        return self._flowgate

    def set_flowgate(self, value):
        for p in self.flowgate:
            filtered = [q for q in p.transmission_provider if q != self]
            p._transmission_provider = filtered
        for r in value:
            if self not in r._transmission_provider:
                r._transmission_provider.append(self)
        self._flowgate = value
            
    flowgate = property(get_flowgate, set_flowgate)
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
            if self not in obj._transmission_provider:
                obj._transmission_provider.append(self)
            self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
            if self in obj._transmission_provider:
                obj._transmission_provider.remove(self)
            self._flowgate.remove(obj)
    # >>> flowgate

    # <<< for_
    # @generated
    def get_for_(self):
        """ Part of the LossProfile for an EnergyTransaction may be a loss for a TransmissionProvider.
        """
        return self._for_

    def set_for_(self, value):
        for x in self._for_:
            x.has_loss_ = None
        for y in value:
            y.has_loss_ = self
        self._for_ = value
            
    for_ = property(get_for_, set_for_)
    
    def add_for_(self, *for_):
        for obj in for_:
            obj._has_loss_ = self
            if obj not in self._for_:
                self._for_.append(obj)
        
    def remove_for_(self, *for_):
        for obj in for_:
            obj._has_loss_ = None
            self._for_.remove(obj)
    # >>> for_

    # <<< offered_by
    # @generated
    def get_offered_by(self):
        """ The combination of a TransmissionProduct on a TransmissionPath is a TransmissionService, for which the TransmissionProvider must post one or two ATC's (AvailableTransmissionCapacity - Amount of possible flow by  direction).
        """
        return self._offered_by

    def set_offered_by(self, value):
        for x in self._offered_by:
            x.offers = None
        for y in value:
            y.offers = self
        self._offered_by = value
            
    offered_by = property(get_offered_by, set_offered_by)
    
    def add_offered_by(self, *offered_by):
        for obj in offered_by:
            obj._offers = self
            if obj not in self._offered_by:
                self._offered_by.append(obj)
        
    def remove_offered_by(self, *offered_by):
        for obj in offered_by:
            obj._offers = None
            self._offered_by.remove(obj)
    # >>> offered_by

    # <<< sold_by
    # @generated
    def get_sold_by(self):
        """ A TransmissionProvider sells the right to transmit energy across the wires in a ServiceReservation.
        """
        return self._sold_by

    def set_sold_by(self, value):
        for x in self._sold_by:
            x.sells = None
        for y in value:
            y.sells = self
        self._sold_by = value
            
    sold_by = property(get_sold_by, set_sold_by)
    
    def add_sold_by(self, *sold_by):
        for obj in sold_by:
            obj._sells = self
            if obj not in self._sold_by:
                self._sold_by.append(obj)
        
    def remove_sold_by(self, *sold_by):
        for obj in sold_by:
            obj._sells = None
            self._sold_by.remove(obj)
    # >>> sold_by

    # <<< ancillary_services
    # @generated
    def get_ancillary_services(self):
        """ A TransmissionProvider offers AncillaryServices. One type of AncillaryServices is a shipping and handling fee to manage the services purchased, another is the reactive power support used to control the voltage on the  transmission system.  This is the amount needed to support the path or amount necessary to maintain the proper voltage at a ServicePoint.
        """
        return self._ancillary_services

    def set_ancillary_services(self, value):
        for p in self.ancillary_services:
            filtered = [q for q in p.transmission_providers if q != self]
            p._transmission_providers = filtered
        for r in value:
            if self not in r._transmission_providers:
                r._transmission_providers.append(self)
        self._ancillary_services = value
            
    ancillary_services = property(get_ancillary_services, set_ancillary_services)
    
    def add_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            if self not in obj._transmission_providers:
                obj._transmission_providers.append(self)
            self._ancillary_services.append(obj)
        
    def remove_ancillary_services(self, *ancillary_services):
        for obj in ancillary_services:
            if self in obj._transmission_providers:
                obj._transmission_providers.remove(self)
            self._ancillary_services.remove(obj)
    # >>> ancillary_services



# <<< financial
# @generated
# >>> financial

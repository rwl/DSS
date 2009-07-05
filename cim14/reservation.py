# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.Reservation"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#Reservation"

class TransmissionPath(Element):
    """ An electrical connection, link, or line consisting of one or more parallel transmission elements between two areas of the interconnected electric systems, or portions thereof. TransmissionCorridor and TransmissionRightOfWay refer to legal aspects. The TransmissionPath refers to the segments between a TransmissionProvider's ServicePoints.
    """
    # <<< transmission_path
    # @generated
    def __init__(self, parallel_path_flag=False, avail_transfer_capability=0.0, total_transfer_capability=0.0, offered_on=None, delivery_point_for=None, point_of_receipt_for=None, located_on=None, for=None, **kw_args):
        """ Initialises a new 'TransmissionPath' instance.
        """
        # Flag which indicates if the transmission path is also a designated interconnection 'parallel path' 
        self.parallel_path_flag = parallel_path_flag
        # The available transmission capability of a transmission path for the reference direction 
        self.avail_transfer_capability = avail_transfer_capability
        # The total transmission capability of a transmission path in the reference direction 
        self.total_transfer_capability = total_transfer_capability
        
        self._offered_on = []
        if offered_on is None:
            self.offered_on = []
        else:
            self.offered_on = offered_on
        self._delivery_point_for = None
        self.delivery_point_for = delivery_point_for
        self._point_of_receipt_for = None
        self.point_of_receipt_for = point_of_receipt_for
        self._located_on = []
        if located_on is None:
            self.located_on = []
        else:
            self.located_on = located_on
        self._for = None
        self.for = for

        super(TransmissionPath, self).__init__(**kw_args)
    # >>> transmission_path
        
    # <<< offered_on
    # @generated
    def get_offered_on(self):
        """ A transmission service is offered on a transmission path.
        """
        return self._offered_on

    def set_offered_on(self, value):
        for p in self.offered_on:
            filtered = [q for q in p.offering if q != self]
            p._offering = filtered
        for r in value:
            if self not in r._offering:
                r._offering.append(self)
        self._offered_on = value
            
    offered_on = property(get_offered_on, set_offered_on)
    
    def add_offered_on(self, *offered_on):
        for obj in offered_on:
            if self not in obj._offering:
                obj._offering.append(self)
            self._offered_on.append(obj)
        
    def remove_offered_on(self, *offered_on):
        for obj in offered_on:
            if self in obj._offering:
                obj._offering.remove(self)
            self._offered_on.remove(obj)
    # >>> offered_on

    # <<< delivery_point_for
    # @generated
    def get_delivery_point_for(self):
        """ A transmission path has a 'point-of-delivery' service point
        """
        return self._delivery_point_for

    def set_delivery_point_for(self, value):
        if self._delivery_point_for is not None:
            filtered = [x for x in self.delivery_point_for.has_apod_ if x != self]
            self._delivery_point_for._has_apod_ = filtered
            
        self._delivery_point_for = value
        if self._delivery_point_for is not None:
            if self not in self._delivery_point_for._has_apod_:
                self._delivery_point_for._has_apod_.append(self)

    delivery_point_for = property(get_delivery_point_for, set_delivery_point_for)
    # >>> delivery_point_for

    # <<< point_of_receipt_for
    # @generated
    def get_point_of_receipt_for(self):
        """ A transmission path has a 'point-of-receipt' service point
        """
        return self._point_of_receipt_for

    def set_point_of_receipt_for(self, value):
        if self._point_of_receipt_for is not None:
            filtered = [x for x in self.point_of_receipt_for.has_apor_ if x != self]
            self._point_of_receipt_for._has_apor_ = filtered
            
        self._point_of_receipt_for = value
        if self._point_of_receipt_for is not None:
            if self not in self._point_of_receipt_for._has_apor_:
                self._point_of_receipt_for._has_apor_.append(self)

    point_of_receipt_for = property(get_point_of_receipt_for, set_point_of_receipt_for)
    # >>> point_of_receipt_for

    # <<< located_on
    # @generated
    def get_located_on(self):
        """ A transmission product is located on a transmission path.
        """
        return self._located_on

    def set_located_on(self, value):
        for p in self.located_on:
            filtered = [q for q in p.location_for if q != self]
            p._location_for = filtered
        for r in value:
            if self not in r._location_for:
                r._location_for.append(self)
        self._located_on = value
            
    located_on = property(get_located_on, set_located_on)
    
    def add_located_on(self, *located_on):
        for obj in located_on:
            if self not in obj._location_for:
                obj._location_for.append(self)
            self._located_on.append(obj)
        
    def remove_located_on(self, *located_on):
        for obj in located_on:
            if self in obj._location_for:
                obj._location_for.remove(self)
            self._located_on.remove(obj)
    # >>> located_on

    # <<< for
    # @generated
    def get_for(self):
        """ A TransmissionPath is contained in a TransmissionCorridor.
        """
        return self._for

    def set_for(self, value):
        if self._for is not None:
            filtered = [x for x in self.for.contained_in if x != self]
            self._for._contained_in = filtered
            
        self._for = value
        if self._for is not None:
            if self not in self._for._contained_in:
                self._for._contained_in.append(self)

    for = property(get_for, set_for)
    # >>> for



class TiePoint(IdentifiedObject):
    """ Site of an interface between interchange areas. The tie point can be a network branch (e.g., transmission line or transformer) or a switching device. For transmission lines, the interchange area boundary is usually at a designated point such as the middle of the line. Line end metering is then corrected for line losses.
    """
    # <<< tie_point
    # @generated
    def __init__(self, tie_point_mwrating=0.0, by_measurements=None, for_measurements=None, declared_service_point=None, **kw_args):
        """ Initialises a new 'TiePoint' instance.
        """
        # The MW rating of the tie point 
        self.tie_point_mwrating = tie_point_mwrating
        
        self._by_measurements = []
        if by_measurements is None:
            self.by_measurements = []
        else:
            self.by_measurements = by_measurements
        self._for_measurements = []
        if for_measurements is None:
            self.for_measurements = []
        else:
            self.for_measurements = for_measurements
        self._declared_service_point = None
        self.declared_service_point = declared_service_point

        super(TiePoint, self).__init__(**kw_args)
    # >>> tie_point
        
    # <<< by_measurements
    # @generated
    def get_by_measurements(self):
        """ A measurement is made on the B side of a tie point
        """
        return self._by_measurements

    def set_by_measurements(self, value):
        for x in self._by_measurements:
            x.by_tie_point = None
        for y in value:
            y.by_tie_point = self
        self._by_measurements = value
            
    by_measurements = property(get_by_measurements, set_by_measurements)
    
    def add_by_measurements(self, *by_measurements):
        for obj in by_measurements:
            obj._by_tie_point = self
            if obj not in self._by_measurements:
                self._by_measurements.append(obj)
        
    def remove_by_measurements(self, *by_measurements):
        for obj in by_measurements:
            obj._by_tie_point = None
            self._by_measurements.remove(obj)
    # >>> by_measurements

    # <<< for_measurements
    # @generated
    def get_for_measurements(self):
        """ A measurement is made on the A side of a tie point
        """
        return self._for_measurements

    def set_for_measurements(self, value):
        for x in self._for_measurements:
            x.for_tie_point = None
        for y in value:
            y.for_tie_point = self
        self._for_measurements = value
            
    for_measurements = property(get_for_measurements, set_for_measurements)
    
    def add_for_measurements(self, *for_measurements):
        for obj in for_measurements:
            obj._for_tie_point = self
            if obj not in self._for_measurements:
                self._for_measurements.append(obj)
        
    def remove_for_measurements(self, *for_measurements):
        for obj in for_measurements:
            obj._for_tie_point = None
            self._for_measurements.remove(obj)
    # >>> for_measurements

    # <<< declared_service_point
    # @generated
    def get_declared_service_point(self):
        """ A tiepoint may be declared as a service point.
        """
        return self._declared_service_point

    def set_declared_service_point(self, value):
        if self._declared_service_point is not None:
            self._declared_service_point._declare_tie_point = None
            
        self._declared_service_point = value
        if self._declared_service_point is not None:
            self._declared_service_point._declare_tie_point = self
            
    declared_service_point = property(get_declared_service_point, set_declared_service_point)
    # >>> declared_service_point



class AncillaryService(IdentifiedObject):
    """ All of these services relate  to various aspects of insuring that the production of energy matches consumption of energy at any given time.  They are very critical to the security and reliability of the interconnected network. Some examples of AncillaryServices include Operating/Supplemental Reserve, Energy Imbalance Service, Operating/Spinning Reserve, Reactive Supply and Voltage Control, and Regulation and Frequency Response.
    """
    # <<< ancillary_service
    # @generated
    def __init__(self, open_access_product=None, control_area_operator=None, reserved_by_service_reservation=None, transmission_providers=None, **kw_args):
        """ Initialises a new 'AncillaryService' instance.
        """
        
        self._open_access_product = None
        self.open_access_product = open_access_product
        self._control_area_operator = None
        self.control_area_operator = control_area_operator
        self._reserved_by_service_reservation = None
        self.reserved_by_service_reservation = reserved_by_service_reservation
        self._transmission_providers = []
        if transmission_providers is None:
            self.transmission_providers = []
        else:
            self.transmission_providers = transmission_providers

        super(AncillaryService, self).__init__(**kw_args)
    # >>> ancillary_service
        
    # <<< open_access_product
    # @generated
    def get_open_access_product(self):
        """ AncillaryServices are sold through a contract which offers a particular OpenAccessProduct.
        """
        return self._open_access_product

    def set_open_access_product(self, value):
        if self._open_access_product is not None:
            filtered = [x for x in self.open_access_product.ancillary_services if x != self]
            self._open_access_product._ancillary_services = filtered
            
        self._open_access_product = value
        if self._open_access_product is not None:
            if self not in self._open_access_product._ancillary_services:
                self._open_access_product._ancillary_services.append(self)

    open_access_product = property(get_open_access_product, set_open_access_product)
    # >>> open_access_product

    # <<< control_area_operator
    # @generated
    def get_control_area_operator(self):
        """ Sale of ancillary services provided by ControlAreaOperators.
        """
        return self._control_area_operator

    def set_control_area_operator(self, value):
        if self._control_area_operator is not None:
            filtered = [x for x in self.control_area_operator.ancillary_service if x != self]
            self._control_area_operator._ancillary_service = filtered
            
        self._control_area_operator = value
        if self._control_area_operator is not None:
            if self not in self._control_area_operator._ancillary_service:
                self._control_area_operator._ancillary_service.append(self)

    control_area_operator = property(get_control_area_operator, set_control_area_operator)
    # >>> control_area_operator

    # <<< reserved_by_service_reservation
    # @generated
    def get_reserved_by_service_reservation(self):
        """ A ServiceReservation guarantees a certain AncillaryService.
        """
        return self._reserved_by_service_reservation

    def set_reserved_by_service_reservation(self, value):
        if self._reserved_by_service_reservation is not None:
            filtered = [x for x in self.reserved_by_service_reservation.reserves_ancillary_services if x != self]
            self._reserved_by_service_reservation._reserves_ancillary_services = filtered
            
        self._reserved_by_service_reservation = value
        if self._reserved_by_service_reservation is not None:
            if self not in self._reserved_by_service_reservation._reserves_ancillary_services:
                self._reserved_by_service_reservation._reserves_ancillary_services.append(self)

    reserved_by_service_reservation = property(get_reserved_by_service_reservation, set_reserved_by_service_reservation)
    # >>> reserved_by_service_reservation

    # <<< transmission_providers
    # @generated
    def get_transmission_providers(self):
        """ A TransmissionProvider offers AncillaryServices. One type of AncillaryServices is a shipping and handling fee to manage the services purchased, another is the reactive power support used to control the voltage on the  transmission system.  This is the amount needed to support the path or amount necessary to maintain the proper voltage at a ServicePoint.
        """
        return self._transmission_providers

    def set_transmission_providers(self, value):
        for p in self.transmission_providers:
            filtered = [q for q in p.ancillary_services if q != self]
            p._ancillary_services = filtered
        for r in value:
            if self not in r._ancillary_services:
                r._ancillary_services.append(self)
        self._transmission_providers = value
            
    transmission_providers = property(get_transmission_providers, set_transmission_providers)
    
    def add_transmission_providers(self, *transmission_providers):
        for obj in transmission_providers:
            if self not in obj._ancillary_services:
                obj._ancillary_services.append(self)
            self._transmission_providers.append(obj)
        
    def remove_transmission_providers(self, *transmission_providers):
        for obj in transmission_providers:
            if self in obj._ancillary_services:
                obj._ancillary_services.remove(self)
            self._transmission_providers.remove(obj)
    # >>> transmission_providers



class ServiceReservation(Element):
    """ A ServiceReservation is a reservation for either AncillaryServices or TransmissionServices. In the case of TransmissionServices, this is the right to some amount of AvailableTransmissionCapacity for a product on a path in a direction for some specific period of time
    """
    # <<< service_reservation
    # @generated
    def __init__(self, reserves_ancillary_services=None, reserves_transmission_service=None, holds=None, resells=None, sells=None, **kw_args):
        """ Initialises a new 'ServiceReservation' instance.
        """
        
        self._reserves_ancillary_services = []
        if reserves_ancillary_services is None:
            self.reserves_ancillary_services = []
        else:
            self.reserves_ancillary_services = reserves_ancillary_services
        self._reserves_transmission_service = []
        if reserves_transmission_service is None:
            self.reserves_transmission_service = []
        else:
            self.reserves_transmission_service = reserves_transmission_service
        self._holds = None
        self.holds = holds
        self._resells = None
        self.resells = resells
        self._sells = None
        self.sells = sells

        super(ServiceReservation, self).__init__(**kw_args)
    # >>> service_reservation
        
    # <<< reserves_ancillary_services
    # @generated
    def get_reserves_ancillary_services(self):
        """ A ServiceReservation guarantees a certain AncillaryService.
        """
        return self._reserves_ancillary_services

    def set_reserves_ancillary_services(self, value):
        for x in self._reserves_ancillary_services:
            x.reserved_by_service_reservation = None
        for y in value:
            y.reserved_by_service_reservation = self
        self._reserves_ancillary_services = value
            
    reserves_ancillary_services = property(get_reserves_ancillary_services, set_reserves_ancillary_services)
    
    def add_reserves_ancillary_services(self, *reserves_ancillary_services):
        for obj in reserves_ancillary_services:
            obj._reserved_by_service_reservation = self
            if obj not in self._reserves_ancillary_services:
                self._reserves_ancillary_services.append(obj)
        
    def remove_reserves_ancillary_services(self, *reserves_ancillary_services):
        for obj in reserves_ancillary_services:
            obj._reserved_by_service_reservation = None
            self._reserves_ancillary_services.remove(obj)
    # >>> reserves_ancillary_services

    # <<< reserves_transmission_service
    # @generated
    def get_reserves_transmission_service(self):
        """ A service reservation reserves a particular transmission service.
        """
        return self._reserves_transmission_service

    def set_reserves_transmission_service(self, value):
        for p in self.reserves_transmission_service:
            filtered = [q for q in p.reserved_by_service_reservation if q != self]
            p._reserved_by_service_reservation = filtered
        for r in value:
            if self not in r._reserved_by_service_reservation:
                r._reserved_by_service_reservation.append(self)
        self._reserves_transmission_service = value
            
    reserves_transmission_service = property(get_reserves_transmission_service, set_reserves_transmission_service)
    
    def add_reserves_transmission_service(self, *reserves_transmission_service):
        for obj in reserves_transmission_service:
            if self not in obj._reserved_by_service_reservation:
                obj._reserved_by_service_reservation.append(self)
            self._reserves_transmission_service.append(obj)
        
    def remove_reserves_transmission_service(self, *reserves_transmission_service):
        for obj in reserves_transmission_service:
            if self in obj._reserved_by_service_reservation:
                obj._reserved_by_service_reservation.remove(self)
            self._reserves_transmission_service.remove(obj)
    # >>> reserves_transmission_service

    # <<< holds
    # @generated
    def get_holds(self):
        """ A Marketer holds title to a ServiceReservation.
        """
        return self._holds

    def set_holds(self, value):
        if self._holds is not None:
            filtered = [x for x in self.holds.held_by if x != self]
            self._holds._held_by = filtered
            
        self._holds = value
        if self._holds is not None:
            if self not in self._holds._held_by:
                self._holds._held_by.append(self)

    holds = property(get_holds, set_holds)
    # >>> holds

    # <<< resells
    # @generated
    def get_resells(self):
        """ A ServiceReservation may be resold by a Marketer.
        """
        return self._resells

    def set_resells(self, value):
        if self._resells is not None:
            self._resells._resold_by = None
            
        self._resells = value
        if self._resells is not None:
            self._resells._resold_by = self
            
    resells = property(get_resells, set_resells)
    # >>> resells

    # <<< sells
    # @generated
    def get_sells(self):
        """ A TransmissionProvider sells the right to transmit energy across the wires in a ServiceReservation.
        """
        return self._sells

    def set_sells(self, value):
        if self._sells is not None:
            filtered = [x for x in self.sells.sold_by if x != self]
            self._sells._sold_by = filtered
            
        self._sells = value
        if self._sells is not None:
            if self not in self._sells._sold_by:
                self._sells._sold_by.append(self)

    sells = property(get_sells, set_sells)
    # >>> sells



class ServicePoint(IdentifiedObject):
    """ Each ServicePoint is contained within (or on the boundary of) an ElectronicIinterchangeArea. ServicePoints are defined termination points of a transmission path (down to distribution level or to a customer - generation or consumption or both).
    """
    # <<< service_point
    # @generated
    def __init__(self, has_apod_=None, customer_consumer=None, has_apor_=None, transmission_provider=None, declare_tie_point=None, generation_provider=None, member_of=None, energy_products=None, **kw_args):
        """ Initialises a new 'ServicePoint' instance.
        """
        
        self._has_apod_ = []
        if has_apod_ is None:
            self.has_apod_ = []
        else:
            self.has_apod_ = has_apod_
        self._customer_consumer = None
        self.customer_consumer = customer_consumer
        self._has_apor_ = []
        if has_apor_ is None:
            self.has_apor_ = []
        else:
            self.has_apor_ = has_apor_
        self._transmission_provider = None
        self.transmission_provider = transmission_provider
        self._declare_tie_point = None
        self.declare_tie_point = declare_tie_point
        self._generation_provider = None
        self.generation_provider = generation_provider
        self._member_of = None
        self.member_of = member_of
        self._energy_products = []
        if energy_products is None:
            self.energy_products = []
        else:
            self.energy_products = energy_products

        super(ServicePoint, self).__init__(**kw_args)
    # >>> service_point
        
    # <<< has_apod_
    # @generated
    def get_has_apod_(self):
        """ A transmission path has a 'point-of-delivery' service point
        """
        return self._has_apod_

    def set_has_apod_(self, value):
        for x in self._has_apod_:
            x.delivery_point_for = None
        for y in value:
            y.delivery_point_for = self
        self._has_apod_ = value
            
    has_apod_ = property(get_has_apod_, set_has_apod_)
    
    def add_has_apod_(self, *has_apod_):
        for obj in has_apod_:
            obj._delivery_point_for = self
            if obj not in self._has_apod_:
                self._has_apod_.append(obj)
        
    def remove_has_apod_(self, *has_apod_):
        for obj in has_apod_:
            obj._delivery_point_for = None
            self._has_apod_.remove(obj)
    # >>> has_apod_

    # <<< customer_consumer
    # @generated
    def get_customer_consumer(self):
        """ A CustomerConsumer may have one or more ServicePoints.
        """
        return self._customer_consumer

    def set_customer_consumer(self, value):
        if self._customer_consumer is not None:
            filtered = [x for x in self.customer_consumer.service_point if x != self]
            self._customer_consumer._service_point = filtered
            
        self._customer_consumer = value
        if self._customer_consumer is not None:
            if self not in self._customer_consumer._service_point:
                self._customer_consumer._service_point.append(self)

    customer_consumer = property(get_customer_consumer, set_customer_consumer)
    # >>> customer_consumer

    # <<< has_apor_
    # @generated
    def get_has_apor_(self):
        """ A transmission path has a 'point-of-receipt' service point
        """
        return self._has_apor_

    def set_has_apor_(self, value):
        for x in self._has_apor_:
            x.point_of_receipt_for = None
        for y in value:
            y.point_of_receipt_for = self
        self._has_apor_ = value
            
    has_apor_ = property(get_has_apor_, set_has_apor_)
    
    def add_has_apor_(self, *has_apor_):
        for obj in has_apor_:
            obj._point_of_receipt_for = self
            if obj not in self._has_apor_:
                self._has_apor_.append(obj)
        
    def remove_has_apor_(self, *has_apor_):
        for obj in has_apor_:
            obj._point_of_receipt_for = None
            self._has_apor_.remove(obj)
    # >>> has_apor_

    # <<< transmission_provider
    # @generated
    def get_transmission_provider(self):
        """ A TransmissionProvider registers one or more ServicePoints.
        """
        return self._transmission_provider

    def set_transmission_provider(self, value):
        if self._transmission_provider is not None:
            filtered = [x for x in self.transmission_provider.service_point if x != self]
            self._transmission_provider._service_point = filtered
            
        self._transmission_provider = value
        if self._transmission_provider is not None:
            if self not in self._transmission_provider._service_point:
                self._transmission_provider._service_point.append(self)

    transmission_provider = property(get_transmission_provider, set_transmission_provider)
    # >>> transmission_provider

    # <<< declare_tie_point
    # @generated
    def get_declare_tie_point(self):
        """ A tiepoint may be declared as a service point.
        """
        return self._declare_tie_point

    def set_declare_tie_point(self, value):
        if self._declare_tie_point is not None:
            self._declare_tie_point._declared_service_point = None
            
        self._declare_tie_point = value
        if self._declare_tie_point is not None:
            self._declare_tie_point._declared_service_point = self
            
    declare_tie_point = property(get_declare_tie_point, set_declare_tie_point)
    # >>> declare_tie_point

    # <<< generation_provider
    # @generated
    def get_generation_provider(self):
        """ A GenerationProvider has one or more ServicePoints where energy is injected into the network.
        """
        return self._generation_provider

    def set_generation_provider(self, value):
        if self._generation_provider is not None:
            filtered = [x for x in self.generation_provider.service_point if x != self]
            self._generation_provider._service_point = filtered
            
        self._generation_provider = value
        if self._generation_provider is not None:
            if self not in self._generation_provider._service_point:
                self._generation_provider._service_point.append(self)

    generation_provider = property(get_generation_provider, set_generation_provider)
    # >>> generation_provider

    # <<< member_of
    # @generated
    def get_member_of(self):
        """ A transmission path's service point is part of an interchange area
        """
        return self._member_of

    def set_member_of(self, value):
        if self._member_of is not None:
            filtered = [x for x in self.member_of.part_of if x != self]
            self._member_of._part_of = filtered
            
        self._member_of = value
        if self._member_of is not None:
            if self not in self._member_of._part_of:
                self._member_of._part_of.append(self)

    member_of = property(get_member_of, set_member_of)
    # >>> member_of

    # <<< energy_products
    # @generated
    def get_energy_products(self):
        """ An EnergyProduct injects energy into a service point.
        """
        return self._energy_products

    def set_energy_products(self, value):
        for p in self.energy_products:
            filtered = [q for q in p.service_point if q != self]
            p._service_point = filtered
        for r in value:
            if self not in r._service_point:
                r._service_point.append(self)
        self._energy_products = value
            
    energy_products = property(get_energy_products, set_energy_products)
    
    def add_energy_products(self, *energy_products):
        for obj in energy_products:
            if self not in obj._service_point:
                obj._service_point.append(self)
            self._energy_products.append(obj)
        
    def remove_energy_products(self, *energy_products):
        for obj in energy_products:
            if self in obj._service_point:
                obj._service_point.remove(self)
            self._energy_products.remove(obj)
    # >>> energy_products



class TransmissionService(IdentifiedObject):
    """ Transmission products along posted transmission path.
    """
    # <<< transmission_service
    # @generated
    def __init__(self, reserved_by_service_reservation=None, offering=None, trans_contract_for=None, scheduled_by=None, offers=None, offered_as=None, **kw_args):
        """ Initialises a new 'TransmissionService' instance.
        """
        
        self._reserved_by_service_reservation = []
        if reserved_by_service_reservation is None:
            self.reserved_by_service_reservation = []
        else:
            self.reserved_by_service_reservation = reserved_by_service_reservation
        self._offering = []
        if offering is None:
            self.offering = []
        else:
            self.offering = offering
        self._trans_contract_for = None
        self.trans_contract_for = trans_contract_for
        self._scheduled_by = []
        if scheduled_by is None:
            self.scheduled_by = []
        else:
            self.scheduled_by = scheduled_by
        self._offers = None
        self.offers = offers
        self._offered_as = []
        if offered_as is None:
            self.offered_as = []
        else:
            self.offered_as = offered_as

        super(TransmissionService, self).__init__(**kw_args)
    # >>> transmission_service
        
    # <<< reserved_by_service_reservation
    # @generated
    def get_reserved_by_service_reservation(self):
        """ A service reservation reserves a particular transmission service.
        """
        return self._reserved_by_service_reservation

    def set_reserved_by_service_reservation(self, value):
        for p in self.reserved_by_service_reservation:
            filtered = [q for q in p.reserves_transmission_service if q != self]
            p._reserves_transmission_service = filtered
        for r in value:
            if self not in r._reserves_transmission_service:
                r._reserves_transmission_service.append(self)
        self._reserved_by_service_reservation = value
            
    reserved_by_service_reservation = property(get_reserved_by_service_reservation, set_reserved_by_service_reservation)
    
    def add_reserved_by_service_reservation(self, *reserved_by_service_reservation):
        for obj in reserved_by_service_reservation:
            if self not in obj._reserves_transmission_service:
                obj._reserves_transmission_service.append(self)
            self._reserved_by_service_reservation.append(obj)
        
    def remove_reserved_by_service_reservation(self, *reserved_by_service_reservation):
        for obj in reserved_by_service_reservation:
            if self in obj._reserves_transmission_service:
                obj._reserves_transmission_service.remove(self)
            self._reserved_by_service_reservation.remove(obj)
    # >>> reserved_by_service_reservation

    # <<< offering
    # @generated
    def get_offering(self):
        """ A transmission service is offered on a transmission path.
        """
        return self._offering

    def set_offering(self, value):
        for p in self.offering:
            filtered = [q for q in p.offered_on if q != self]
            p._offered_on = filtered
        for r in value:
            if self not in r._offered_on:
                r._offered_on.append(self)
        self._offering = value
            
    offering = property(get_offering, set_offering)
    
    def add_offering(self, *offering):
        for obj in offering:
            if self not in obj._offered_on:
                obj._offered_on.append(self)
            self._offering.append(obj)
        
    def remove_offering(self, *offering):
        for obj in offering:
            if self in obj._offered_on:
                obj._offered_on.remove(self)
            self._offering.remove(obj)
    # >>> offering

    # <<< trans_contract_for
    # @generated
    def get_trans_contract_for(self):
        """ A TransmissionService is sold according to the terms of a particular OpenAccessProduct agreement.
        """
        return self._trans_contract_for

    def set_trans_contract_for(self, value):
        if self._trans_contract_for is not None:
            filtered = [x for x in self.trans_contract_for.provided_by_transmission_service if x != self]
            self._trans_contract_for._provided_by_transmission_service = filtered
            
        self._trans_contract_for = value
        if self._trans_contract_for is not None:
            if self not in self._trans_contract_for._provided_by_transmission_service:
                self._trans_contract_for._provided_by_transmission_service.append(self)

    trans_contract_for = property(get_trans_contract_for, set_trans_contract_for)
    # >>> trans_contract_for

    # <<< scheduled_by
    # @generated
    def get_scheduled_by(self):
        """ A transmission schedule posts the available transmission capacity for a transmission line.
        """
        return self._scheduled_by

    def set_scheduled_by(self, value):
        for p in self.scheduled_by:
            filtered = [q for q in p.schedule_for if q != self]
            p._schedule_for = filtered
        for r in value:
            if self not in r._schedule_for:
                r._schedule_for.append(self)
        self._scheduled_by = value
            
    scheduled_by = property(get_scheduled_by, set_scheduled_by)
    
    def add_scheduled_by(self, *scheduled_by):
        for obj in scheduled_by:
            if self not in obj._schedule_for:
                obj._schedule_for.append(self)
            self._scheduled_by.append(obj)
        
    def remove_scheduled_by(self, *scheduled_by):
        for obj in scheduled_by:
            if self in obj._schedule_for:
                obj._schedule_for.remove(self)
            self._scheduled_by.remove(obj)
    # >>> scheduled_by

    # <<< offers
    # @generated
    def get_offers(self):
        """ The combination of a TransmissionProduct on a TransmissionPath is a TransmissionService, for which the TransmissionProvider must post one or two ATC's (AvailableTransmissionCapacity - Amount of possible flow by  direction).
        """
        return self._offers

    def set_offers(self, value):
        if self._offers is not None:
            filtered = [x for x in self.offers.offered_by if x != self]
            self._offers._offered_by = filtered
            
        self._offers = value
        if self._offers is not None:
            if self not in self._offers._offered_by:
                self._offers._offered_by.append(self)

    offers = property(get_offers, set_offers)
    # >>> offers

    # <<< offered_as
    # @generated
    def get_offered_as(self):
        """ A transmission product is offered as a transmission service along a transmission path.
        """
        return self._offered_as

    def set_offered_as(self, value):
        for p in self.offered_as:
            filtered = [q for q in p.offers if q != self]
            p._offers = filtered
        for r in value:
            if self not in r._offers:
                r._offers.append(self)
        self._offered_as = value
            
    offered_as = property(get_offered_as, set_offered_as)
    
    def add_offered_as(self, *offered_as):
        for obj in offered_as:
            if self not in obj._offers:
                obj._offers.append(self)
            self._offered_as.append(obj)
        
    def remove_offered_as(self, *offered_as):
        for obj in offered_as:
            if self in obj._offers:
                obj._offers.remove(self)
            self._offered_as.remove(obj)
    # >>> offered_as



class ReservationVersion(Element):
    # <<< reservation_version
    # @generated
    def __init__(self, date='', version='', **kw_args):
        """ Initialises a new 'ReservationVersion' instance.
        """
 
        self.date = date
 
        self.version = version
        

        super(ReservationVersion, self).__init__(**kw_args)
    # >>> reservation_version
        


# <<< reservation
# @generated
# >>> reservation

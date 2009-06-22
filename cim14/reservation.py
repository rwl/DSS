# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root
from cim14.iec61970.core import IdentifiedObject

# <<< imports
# @generated
# >>> imports

class TransmissionPath(Root):
    """ An electrical connection, link, or line consisting of one or more parallel transmission elements between two areas of the interconnected electric systems, or portions thereof. TransmissionCorridor and TransmissionRightOfWay refer to legal aspects. The TransmissionPath refers to the segments between a TransmissionProvider's ServicePoints.
    """
    # Flag which indicates if the transmission path is also a designated interconnection 'parallel path' 
    parallel_path_flag = False

    # The available transmission capability of a transmission path for the reference direction 
    avail_transfer_capability = ''

    # The total transmission capability of a transmission path in the reference direction 
    total_transfer_capability = ''

    # A transmission service is offered on a transmission path.
    offered_on = []

    # A transmission path has a 'point-of-delivery' service point
    delivery_point_for = None

    # A transmission path has a 'point-of-receipt' service point
    point_of_receipt_for = None

    # A transmission product is located on a transmission path.
    located_on = []

    # A TransmissionPath is contained in a TransmissionCorridor.
    for = None

    # <<< transmission_path
    # @generated
    def __init__(self, parallel_path_flag=False, avail_transfer_capability='', total_transfer_capability='', offered_on=[], delivery_point_for=None, point_of_receipt_for=None, located_on=[], for=None, **kw_args):
        """ Initialises a new 'TransmissionPath' instance.
        """
        self.parallel_path_flag = parallel_path_flag
        self.avail_transfer_capability = avail_transfer_capability
        self.total_transfer_capability = total_transfer_capability
        self.offered_on = offered_on
        self.delivery_point_for = delivery_point_for
        self.point_of_receipt_for = point_of_receipt_for
        self.located_on = located_on
        self.for = for

        super(TransmissionPath, self).__init__(**kw_args)
    # >>> transmission_path


class TiePoint(IdentifiedObject):
    """ Site of an interface between interchange areas. The tie point can be a network branch (e.g., transmission line or transformer) or a switching device. For transmission lines, the interchange area boundary is usually at a designated point such as the middle of the line. Line end metering is then corrected for line losses.
    """
    # The MW rating of the tie point 
    tie_point_mwrating = ''

    # A measurement is made on the B side of a tie point
    by_measurements = []

    # A measurement is made on the A side of a tie point
    for_measurements = []

    # A tiepoint may be declared as a service point.
    declared_service_point = None

    # <<< tie_point
    # @generated
    def __init__(self, tie_point_mwrating='', by_measurements=[], for_measurements=[], declared_service_point=None, **kw_args):
        """ Initialises a new 'TiePoint' instance.
        """
        self.tie_point_mwrating = tie_point_mwrating
        self.by_measurements = by_measurements
        self.for_measurements = for_measurements
        self.declared_service_point = declared_service_point

        super(TiePoint, self).__init__(**kw_args)
    # >>> tie_point


class AncillaryService(IdentifiedObject):
    """ All of these services relate  to various aspects of insuring that the production of energy matches consumption of energy at any given time.  They are very critical to the security and reliability of the interconnected network. Some examples of AncillaryServices include Operating/Supplemental Reserve, Energy Imbalance Service, Operating/Spinning Reserve, Reactive Supply and Voltage Control, and Regulation and Frequency Response.
    """
    # AncillaryServices are sold through a contract which offers a particular OpenAccessProduct.
    open_access_product = None

    # Sale of ancillary services provided by ControlAreaOperators.
    control_area_operator = None

    # A ServiceReservation guarantees a certain AncillaryService.
    reserved_by_service_reservation = None

    # A TransmissionProvider offers AncillaryServices. One type of AncillaryServices is a shipping and handling fee to manage the services purchased, another is the reactive power support used to control the voltage on the  transmission system.  This is the amount needed to support the path or amount necessary to maintain the proper voltage at a ServicePoint.
    transmission_providers = []

    # <<< ancillary_service
    # @generated
    def __init__(self, open_access_product=None, control_area_operator=None, reserved_by_service_reservation=None, transmission_providers=[], **kw_args):
        """ Initialises a new 'AncillaryService' instance.
        """
        self.open_access_product = open_access_product
        self.control_area_operator = control_area_operator
        self.reserved_by_service_reservation = reserved_by_service_reservation
        self.transmission_providers = transmission_providers

        super(AncillaryService, self).__init__(**kw_args)
    # >>> ancillary_service


class ServiceReservation(Root):
    """ A ServiceReservation is a reservation for either AncillaryServices or TransmissionServices. In the case of TransmissionServices, this is the right to some amount of AvailableTransmissionCapacity for a product on a path in a direction for some specific period of time
    """
    # A ServiceReservation guarantees a certain AncillaryService.
    reserves_ancillary_services = []

    # A service reservation reserves a particular transmission service.
    reserves_transmission_service = []

    # A Marketer holds title to a ServiceReservation.
    holds = None

    # A ServiceReservation may be resold by a Marketer.
    resells = None

    # A TransmissionProvider sells the right to transmit energy across the wires in a ServiceReservation.
    sells = None

    # <<< service_reservation
    # @generated
    def __init__(self, reserves_ancillary_services=[], reserves_transmission_service=[], holds=None, resells=None, sells=None, **kw_args):
        """ Initialises a new 'ServiceReservation' instance.
        """
        self.reserves_ancillary_services = reserves_ancillary_services
        self.reserves_transmission_service = reserves_transmission_service
        self.holds = holds
        self.resells = resells
        self.sells = sells

        super(ServiceReservation, self).__init__(**kw_args)
    # >>> service_reservation


class ServicePoint(IdentifiedObject):
    """ Each ServicePoint is contained within (or on the boundary of) an ElectronicIinterchangeArea. ServicePoints are defined termination points of a transmission path (down to distribution level or to a customer - generation or consumption or both).
    """
    # A transmission path has a 'point-of-delivery' service point
    has_apod_ = []

    # A CustomerConsumer may have one or more ServicePoints.
    customer_consumer = None

    # A transmission path has a 'point-of-receipt' service point
    has_apor_ = []

    # A TransmissionProvider registers one or more ServicePoints.
    transmission_provider = None

    # A tiepoint may be declared as a service point.
    declare_tie_point = None

    # A GenerationProvider has one or more ServicePoints where energy is injected into the network.
    generation_provider = None

    # A transmission path's service point is part of an interchange area
    member_of = None

    # An EnergyProduct injects energy into a service point.
    energy_products = []

    # <<< service_point
    # @generated
    def __init__(self, has_apod_=[], customer_consumer=None, has_apor_=[], transmission_provider=None, declare_tie_point=None, generation_provider=None, member_of=None, energy_products=[], **kw_args):
        """ Initialises a new 'ServicePoint' instance.
        """
        self.has_apod_ = has_apod_
        self.customer_consumer = customer_consumer
        self.has_apor_ = has_apor_
        self.transmission_provider = transmission_provider
        self.declare_tie_point = declare_tie_point
        self.generation_provider = generation_provider
        self.member_of = member_of
        self.energy_products = energy_products

        super(ServicePoint, self).__init__(**kw_args)
    # >>> service_point


class TransmissionService(IdentifiedObject):
    """ Transmission products along posted transmission path.
    """
    # A service reservation reserves a particular transmission service.
    reserved_by_service_reservation = []

    # A transmission service is offered on a transmission path.
    offering = []

    # A TransmissionService is sold according to the terms of a particular OpenAccessProduct agreement.
    trans_contract_for = None

    # A transmission schedule posts the available transmission capacity for a transmission line.
    scheduled_by = []

    # The combination of a TransmissionProduct on a TransmissionPath is a TransmissionService, for which the TransmissionProvider must post one or two ATC's (AvailableTransmissionCapacity - Amount of possible flow by  direction).
    offers = None

    # A transmission product is offered as a transmission service along a transmission path.
    offered_as = []

    # <<< transmission_service
    # @generated
    def __init__(self, reserved_by_service_reservation=[], offering=[], trans_contract_for=None, scheduled_by=[], offers=None, offered_as=[], **kw_args):
        """ Initialises a new 'TransmissionService' instance.
        """
        self.reserved_by_service_reservation = reserved_by_service_reservation
        self.offering = offering
        self.trans_contract_for = trans_contract_for
        self.scheduled_by = scheduled_by
        self.offers = offers
        self.offered_as = offered_as

        super(TransmissionService, self).__init__(**kw_args)
    # >>> transmission_service


class ReservationVersion(Root):
 
    date = ''

 
    version = ''

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

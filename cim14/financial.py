# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.informative.inf_erpsupport import ErpOrganisation
from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Agreement
from cim14 import Root

# <<< imports
# @generated
# >>> imports

class GenerationProvider(ErpOrganisation):
    """ The energy seller in the energy marketplace.
    """
    # A GenerationProvider operates one or more GeneratingUnits.
    generating_units = []

    # A GenerationProvider has one or more ServicePoints where energy is injected into the network.
    service_point = []

    energy_products = []

    # <<< generation_provider
    # @generated
    def __init__(self, generating_units=[], service_point=[], energy_products=[], **kw_args):
        """ Initialises a new 'GenerationProvider' instance.
        """
        self.generating_units = generating_units
        self.service_point = service_point
        self.energy_products = energy_products

        super(GenerationProvider, self).__init__(**kw_args)
    # >>> generation_provider


class TransmissionProduct(IdentifiedObject):
    # A transmission product is located on a transmission path.
    location_for = []

    # A transmission product is offered as a transmission service along a transmission path.
    offers = []

    # A TransmissionProvider offers a TransmissionProduct.
    transmission_provider = None

    # <<< transmission_product
    # @generated
    def __init__(self, location_for=[], offers=[], transmission_provider=None, **kw_args):
        """ Initialises a new 'TransmissionProduct' instance.
        """
        self.location_for = location_for
        self.offers = offers
        self.transmission_provider = transmission_provider

        super(TransmissionProduct, self).__init__(**kw_args)
    # >>> transmission_product


class CustomerConsumer(ErpOrganisation):
    """ The energy buyer in the energy marketplace.
    """
    # A CustomerConsumer may have one or more ServicePoints.
    service_point = []

    # A  ControlAreaOperator or CustomerConsumer may ring their perimeter with metering, which can create a unique SubControlArea at the collection of metering points, called a TieLine.
    tie_lines = []

    # <<< customer_consumer
    # @generated
    def __init__(self, service_point=[], tie_lines=[], **kw_args):
        """ Initialises a new 'CustomerConsumer' instance.
        """
        self.service_point = service_point
        self.tie_lines = tie_lines

        super(CustomerConsumer, self).__init__(**kw_args)
    # >>> customer_consumer


class ControlAreaOperator(ErpOrganisation):
    """ Operates the Control Area. Approves and implements energy transactions. Verifies both Inter-Control Area and Intra-Control Area transactions for the power system before granting approval (and implementing) the transactions.
    """
    # A ControlAreaCompany controls a ControlArea.
    controlled_by = None

    # Sale of ancillary services provided by ControlAreaOperators.
    ancillary_service = []

    # A ControlAreaOperator has a collection of tie points that ring the ControlArea, called a TieLine.
    tie_lines = []

    # <<< control_area_operator
    # @generated
    def __init__(self, controlled_by=None, ancillary_service=[], tie_lines=[], **kw_args):
        """ Initialises a new 'ControlAreaOperator' instance.
        """
        self.controlled_by = controlled_by
        self.ancillary_service = ancillary_service
        self.tie_lines = tie_lines

        super(ControlAreaOperator, self).__init__(**kw_args)
    # >>> control_area_operator


class IntSchedAgreement(Agreement):
    """ A type of agreement that provides the default method by which interchange schedules are to be integrated to obtain hourly energy schedules for accounting.
    """
    organisations = []

    # <<< int_sched_agreement
    # @generated
    def __init__(self, organisations=[], **kw_args):
        """ Initialises a new 'IntSchedAgreement' instance.
        """
        self.organisations = organisations

        super(IntSchedAgreement, self).__init__(**kw_args)
    # >>> int_sched_agreement


class Marketer(ErpOrganisation):
    """ Matches buyers and sellers, and secures transmission (and other ancillary services) needed to complete the energy transaction.
    """
    # A Marketer holds title to an EnergyProduct.
    holds_title_to_energy_products = []

    # A ServiceReservation may be resold by a Marketer.
    resold_by = None

    # A Marketer holds title to a ServiceReservation.
    held_by = []

    # A Marketer may resell an EnergyProduct.
    resells_energy_product = []

    # <<< marketer
    # @generated
    def __init__(self, holds_title_to_energy_products=[], resold_by=None, held_by=[], resells_energy_product=[], **kw_args):
        """ Initialises a new 'Marketer' instance.
        """
        self.holds_title_to_energy_products = holds_title_to_energy_products
        self.resold_by = resold_by
        self.held_by = held_by
        self.resells_energy_product = resells_energy_product

        super(Marketer, self).__init__(**kw_args)
    # >>> marketer


class FinancialVersion(Root):
 
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
    # AncillaryServices are sold through a contract which offers a particular OpenAccessProduct.
    ancillary_services = []

    # A TransmissionService is sold according to the terms of a particular OpenAccessProduct agreement.
    provided_by_transmission_service = []

    # <<< open_access_product
    # @generated
    def __init__(self, ancillary_services=[], provided_by_transmission_service=[], **kw_args):
        """ Initialises a new 'OpenAccessProduct' instance.
        """
        self.ancillary_services = ancillary_services
        self.provided_by_transmission_service = provided_by_transmission_service

        super(OpenAccessProduct, self).__init__(**kw_args)
    # >>> open_access_product


class TransmissionProvider(ErpOrganisation):
    """ Provider of the transmission capacity (interconnecting wires between Generation and Consumption) required to fulfill and Energy Transaction's energy exchange. Posts information for transmission paths and AvailableTransmissionCapacities on a reservation node. Buys and sells its products and services on the same reservation node.
    """
    # A TransmissionProvider registers one or more ServicePoints.
    service_point = []

    # A TransmissionProvider offers a TransmissionProduct.
    transmission_products = []

    # A flowgate can be reciprocal flowgate for 0 to n transmission providers. A transmission provider may be a reciprocal entity for 0 to n flowgates.
    flowgate = []

    # Part of the LossProfile for an EnergyTransaction may be a loss for a TransmissionProvider.
    for = []

    # The combination of a TransmissionProduct on a TransmissionPath is a TransmissionService, for which the TransmissionProvider must post one or two ATC's (AvailableTransmissionCapacity - Amount of possible flow by  direction).
    offered_by = []

    # A TransmissionProvider sells the right to transmit energy across the wires in a ServiceReservation.
    sold_by = []

    # A TransmissionProvider offers AncillaryServices. One type of AncillaryServices is a shipping and handling fee to manage the services purchased, another is the reactive power support used to control the voltage on the  transmission system.  This is the amount needed to support the path or amount necessary to maintain the proper voltage at a ServicePoint.
    ancillary_services = []

    # <<< transmission_provider
    # @generated
    def __init__(self, service_point=[], transmission_products=[], flowgate=[], for=[], offered_by=[], sold_by=[], ancillary_services=[], **kw_args):
        """ Initialises a new 'TransmissionProvider' instance.
        """
        self.service_point = service_point
        self.transmission_products = transmission_products
        self.flowgate = flowgate
        self.for = for
        self.offered_by = offered_by
        self.sold_by = sold_by
        self.ancillary_services = ancillary_services

        super(TransmissionProvider, self).__init__(**kw_args)
    # >>> transmission_provider


# <<< financial
# @generated
# >>> financial

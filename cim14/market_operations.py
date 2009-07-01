# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.informative.inf_erpsupport import ErpOrganisation
from cim14.iec61970.core import Curve
from cim14.iec61968.common import Agreement
from cim14 import Element
from cim14.energy_scheduling import Profile
from cim14.iec61968.common import Document
from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import RegularIntervalSchedule
from cim14.iec61970.meas import Limit

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.MarketOperations"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#MarketOperations"

class RegisteredResource(IdentifiedObject):
    """ A resource that is registered through the RTO participant registration system. Examples include generating unit, customer meter, and a non-physical generator or load.
    """
    # Unique name obtained via RTO registration 
    rto_id = ''

    resource_groups = []
    
    def add_resource_groups(self, *resource_groups):
        for obj in resource_groups:
	        self._resource_groups.append(obj)
        
    def remove_resource_groups(self, *resource_groups):
        for obj in resource_groups:
	        self._resource_groups.remove(obj)

    def get_organisation(self):
        """ 
        """
        return self._organisation

    def set_organisation(self, value):
        if self._organisation is not None:
            filtered = [x for x in self.organisation.registered_resources if x != self]
            self._organisation._registered_resources = filtered
            
        self._organisation = value
        if self._organisation is not None:
            self._organisation._registered_resources.append(self)

    organisation = property(get_organisation, set_organisation)

    def get_pnode(self):
        """ A registered resource injects power at one or more connectivity nodes related to a pnode
        """
        return self._pnode

    def set_pnode(self, value):
        if self._pnode is not None:
            filtered = [x for x in self.pnode.registered_resources if x != self]
            self._pnode._registered_resources = filtered
            
        self._pnode = value
        if self._pnode is not None:
            self._pnode._registered_resources.append(self)

    pnode = property(get_pnode, set_pnode)

    def get_meters(self):
        """ 
        """
        return self._meters

    def set_meters(self, value):
        for x in self._meters:
            x._registered_resource = None
        for y in value:
            y._registered_resource = self
        self._meters = value
            
    meters = property(get_meters, set_meters)
    
    def add_meters(self, *meters):
        for obj in meters:
            obj._registered_resource = self
            self._meters.append(obj)
        
    def remove_meters(self, *meters):
        for obj in meters:
            obj._registered_resource = None
            self._meters.remove(obj)

    market_products = []
    
    def add_market_products(self, *market_products):
        for obj in market_products:
	        self._market_products.append(obj)
        
    def remove_market_products(self, *market_products):
        for obj in market_products:
	        self._market_products.remove(obj)

    markets = []
    
    def add_markets(self, *markets):
        for obj in markets:
	        self._markets.append(obj)
        
    def remove_markets(self, *markets):
        for obj in markets:
	        self._markets.remove(obj)

    # <<< registered_resource
    # @generated
    def __init__(self, rto_id='', resource_groups=[], organisation=None, pnode=None, meters=[], market_products=[], markets=[], **kw_args):
        """ Initialises a new 'RegisteredResource' instance.
        """
        self.rto_id = rto_id
        self._resource_groups = []
        self.resource_groups = resource_groups
        self._organisation = None
        self.organisation = organisation
        self._pnode = None
        self.pnode = pnode
        self._meters = []
        self.meters = meters
        self._market_products = []
        self.market_products = market_products
        self._markets = []
        self.markets = markets

        super(RegisteredResource, self).__init__(**kw_args)
    # >>> registered_resource


class RTO(ErpOrganisation):
    """ Regional transmission operator.
    """
    def get_security_constraints_linear(self):
        """ 
        """
        return self._security_constraints_linear

    def set_security_constraints_linear(self, value):
        for x in self._security_constraints_linear:
            x._rto = None
        for y in value:
            y._rto = self
        self._security_constraints_linear = value
            
    security_constraints_linear = property(get_security_constraints_linear, set_security_constraints_linear)
    
    def add_security_constraints_linear(self, *security_constraints_linear):
        for obj in security_constraints_linear:
            obj._rto = self
            self._security_constraints_linear.append(obj)
        
    def remove_security_constraints_linear(self, *security_constraints_linear):
        for obj in security_constraints_linear:
            obj._rto = None
            self._security_constraints_linear.remove(obj)

    resource_group_reqs = []
    
    def add_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
	        self._resource_group_reqs.append(obj)
        
    def remove_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
	        self._resource_group_reqs.remove(obj)

    def get_security_constraints(self):
        """ 
        """
        return self._security_constraints

    def set_security_constraints(self, value):
        for x in self._security_constraints:
            x._rto = None
        for y in value:
            y._rto = self
        self._security_constraints = value
            
    security_constraints = property(get_security_constraints, set_security_constraints)
    
    def add_security_constraints(self, *security_constraints):
        for obj in security_constraints:
            obj._rto = self
            self._security_constraints.append(obj)
        
    def remove_security_constraints(self, *security_constraints):
        for obj in security_constraints:
            obj._rto = None
            self._security_constraints.remove(obj)

    def get_markets(self):
        """ 
        """
        return self._markets

    def set_markets(self, value):
        for x in self._markets:
            x._rto = None
        for y in value:
            y._rto = self
        self._markets = value
            
    markets = property(get_markets, set_markets)
    
    def add_markets(self, *markets):
        for obj in markets:
            obj._rto = self
            self._markets.append(obj)
        
    def remove_markets(self, *markets):
        for obj in markets:
            obj._rto = None
            self._markets.remove(obj)

    def get_pnodes(self):
        """ 
        """
        return self._pnodes

    def set_pnodes(self, value):
        for x in self._pnodes:
            x._rto = None
        for y in value:
            y._rto = self
        self._pnodes = value
            
    pnodes = property(get_pnodes, set_pnodes)
    
    def add_pnodes(self, *pnodes):
        for obj in pnodes:
            obj._rto = self
            self._pnodes.append(obj)
        
    def remove_pnodes(self, *pnodes):
        for obj in pnodes:
            obj._rto = None
            self._pnodes.remove(obj)

    # <<< rto
    # @generated
    def __init__(self, security_constraints_linear=[], resource_group_reqs=[], security_constraints=[], markets=[], pnodes=[], **kw_args):
        """ Initialises a new 'RTO' instance.
        """
        self._security_constraints_linear = []
        self.security_constraints_linear = security_constraints_linear
        self._resource_group_reqs = []
        self.resource_group_reqs = resource_group_reqs
        self._security_constraints = []
        self.security_constraints = security_constraints
        self._markets = []
        self.markets = markets
        self._pnodes = []
        self.pnodes = pnodes

        super(RTO, self).__init__(**kw_args)
    # >>> rto


class Meter(IdentifiedObject):
    """ This is generic logical meter object.
    """
    def get_registered_resource(self):
        """ 
        """
        return self._registered_resource

    def set_registered_resource(self, value):
        if self._registered_resource is not None:
            filtered = [x for x in self.registered_resource.meters if x != self]
            self._registered_resource._meters = filtered
            
        self._registered_resource = value
        if self._registered_resource is not None:
            self._registered_resource._meters.append(self)

    registered_resource = property(get_registered_resource, set_registered_resource)

    # <<< meter
    # @generated
    def __init__(self, registered_resource=None, **kw_args):
        """ Initialises a new 'Meter' instance.
        """
        self._registered_resource = None
        self.registered_resource = registered_resource

        super(Meter, self).__init__(**kw_args)
    # >>> meter


class ContingencyConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) assigned to a constraint for a specific contingency. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
    def get_mwlimit_schedules(self):
        """ 
        """
        return self._mwlimit_schedules

    def set_mwlimit_schedules(self, value):
        if self._mwlimit_schedules is not None:
            self._mwlimit_schedules._security_constraint_limit = None
            
        self._mwlimit_schedules = value
        if self._mwlimit_schedules is not None:
            self._mwlimit_schedules._security_constraint_limit = self
            
    mwlimit_schedules = property(get_mwlimit_schedules, set_mwlimit_schedules)

    def get_contingency(self):
        """ 
        """
        return self._contingency

    def set_contingency(self, value):
        if self._contingency is not None:
            filtered = [x for x in self.contingency.contingency_constraint_limit if x != self]
            self._contingency._contingency_constraint_limit = filtered
            
        self._contingency = value
        if self._contingency is not None:
            self._contingency._contingency_constraint_limit.append(self)

    contingency = property(get_contingency, set_contingency)

    def get_security_constraint_sum(self):
        """ 
        """
        return self._security_constraint_sum

    def set_security_constraint_sum(self, value):
        if self._security_constraint_sum is not None:
            filtered = [x for x in self.security_constraint_sum.contingency_constraint_limits if x != self]
            self._security_constraint_sum._contingency_constraint_limits = filtered
            
        self._security_constraint_sum = value
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._contingency_constraint_limits.append(self)

    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)

    # <<< contingency_constraint_limit
    # @generated
    def __init__(self, mwlimit_schedules=None, contingency=None, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'ContingencyConstraintLimit' instance.
        """
        self._mwlimit_schedules = None
        self.mwlimit_schedules = mwlimit_schedules
        self._contingency = None
        self.contingency = contingency
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(ContingencyConstraintLimit, self).__init__(**kw_args)
    # >>> contingency_constraint_limit


class BidPriceCurve(Curve):
    """ Relationship between unit operating price in $/hour (Y-axis) and unit output in MW (X-axis).
    """
    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x._bid_price_curve = None
        for y in value:
            y._bid_price_curve = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid_price_curve = self
            self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid_price_curve = None
            self._product_bids.remove(obj)

    # <<< bid_price_curve
    # @generated
    def __init__(self, product_bids=[], **kw_args):
        """ Initialises a new 'BidPriceCurve' instance.
        """
        self._product_bids = []
        self.product_bids = product_bids

        super(BidPriceCurve, self).__init__(**kw_args)
    # >>> bid_price_curve


class FTR(Agreement):
    """ Financial Transmission Rights (FTR) regarding transmission capacity at a flowgate.
    """
    # Peak, Off-peak, 24-hour 
    class = ''

    # Type of rights being offered (product) allowed to be auctioned (option, obligation). 
    ftr_type = ''

    # Fixed (covers re-configuration, grandfathering) or Optimized (up for sale/purchase 
    optimized = ''

    # Buy, Sell 
    action = ''

    # Quantity, typically MWs - Seller owns all rights being offered, MWs over time on same Point of Receipt, Point of Delivery, or Resource. 
    base_energy = ''

    def get_flowgate(self):
        """ 
        """
        return self._flowgate

    def set_flowgate(self, value):
        if self._flowgate is not None:
            filtered = [x for x in self.flowgate.ftrs if x != self]
            self._flowgate._ftrs = filtered
            
        self._flowgate = value
        if self._flowgate is not None:
            self._flowgate._ftrs.append(self)

    flowgate = property(get_flowgate, set_flowgate)

    pnodes = []
    
    def add_pnodes(self, *pnodes):
        for obj in pnodes:
	        self._pnodes.append(obj)
        
    def remove_pnodes(self, *pnodes):
        for obj in pnodes:
	        self._pnodes.remove(obj)

    def get_energy_price_curve(self):
        """ 
        """
        return self._energy_price_curve

    def set_energy_price_curve(self, value):
        if self._energy_price_curve is not None:
            filtered = [x for x in self.energy_price_curve.ftrs if x != self]
            self._energy_price_curve._ftrs = filtered
            
        self._energy_price_curve = value
        if self._energy_price_curve is not None:
            self._energy_price_curve._ftrs.append(self)

    energy_price_curve = property(get_energy_price_curve, set_energy_price_curve)

    # <<< ftr
    # @generated
    def __init__(self, class='', ftr_type='', optimized='', action='', base_energy='', flowgate=None, pnodes=[], energy_price_curve=None, **kw_args):
        """ Initialises a new 'FTR' instance.
        """
        self.class = class
        self.ftr_type = ftr_type
        self.optimized = optimized
        self.action = action
        self.base_energy = base_energy
        self._flowgate = None
        self.flowgate = flowgate
        self._pnodes = []
        self.pnodes = pnodes
        self._energy_price_curve = None
        self.energy_price_curve = energy_price_curve

        super(FTR, self).__init__(**kw_args)
    # >>> ftr


class ChargeProfileData(Element):
    # The date and time of an interval. 
    time_stamp = ''

    # The value of an interval given a profile type (amount, price, or quantity), subject to the UOM. 
    value = 0.0

    # The sequence number of the profile. 
    sequence = 0

    def get_charge_profile(self):
        """ 
        """
        return self._charge_profile

    def set_charge_profile(self, value):
        if self._charge_profile is not None:
            filtered = [x for x in self.charge_profile.charge_profile_data if x != self]
            self._charge_profile._charge_profile_data = filtered
            
        self._charge_profile = value
        if self._charge_profile is not None:
            self._charge_profile._charge_profile_data.append(self)

    charge_profile = property(get_charge_profile, set_charge_profile)

    def get_bill_determinant(self):
        """ 
        """
        return self._bill_determinant

    def set_bill_determinant(self, value):
        if self._bill_determinant is not None:
            filtered = [x for x in self.bill_determinant.charge_profile_data if x != self]
            self._bill_determinant._charge_profile_data = filtered
            
        self._bill_determinant = value
        if self._bill_determinant is not None:
            self._bill_determinant._charge_profile_data.append(self)

    bill_determinant = property(get_bill_determinant, set_bill_determinant)

    # <<< charge_profile_data
    # @generated
    def __init__(self, time_stamp='', value=0.0, sequence=0, charge_profile=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfileData' instance.
        """
        self.time_stamp = time_stamp
        self.value = value
        self.sequence = sequence
        self._charge_profile = None
        self.charge_profile = charge_profile
        self._bill_determinant = None
        self.bill_determinant = bill_determinant

        super(ChargeProfileData, self).__init__(**kw_args)
    # >>> charge_profile_data


class TransmissionReliabilityMargin(IdentifiedObject):
    """ Transmission Reliability Margin (TRM) is defined as that amount of transmission transfer capability necessary to ensure that the interconnected transmission network is secure under a reasonable range of uncertainties in system conditions.
    """
    # Value of the TRM 
    trm_value = 0.0

    # the type of TRM 
    trm_type = ''

    # unit of the TRM value. Could be MW or Percentage. 
    value_unit = ''

    def get_flowgate(self):
        """ A fowgate may have 0 to 1 TRM
        """
        return self._flowgate

    def set_flowgate(self, value):
        for x in self._flowgate:
            x._transmission_reliability_margin = None
        for y in value:
            y._transmission_reliability_margin = self
        self._flowgate = value
            
    flowgate = property(get_flowgate, set_flowgate)
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._transmission_reliability_margin = self
            self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._transmission_reliability_margin = None
            self._flowgate.remove(obj)

    # <<< transmission_reliability_margin
    # @generated
    def __init__(self, trm_value=0.0, trm_type='', value_unit='', flowgate=[], **kw_args):
        """ Initialises a new 'TransmissionReliabilityMargin' instance.
        """
        self.trm_value = trm_value
        self.trm_type = trm_type
        self.value_unit = value_unit
        self._flowgate = []
        self.flowgate = flowgate

        super(TransmissionReliabilityMargin, self).__init__(**kw_args)
    # >>> transmission_reliability_margin


class UnitInitialConditions(IdentifiedObject):
    """ Resource status at the end of a given clearing period.
    """
    # Cumulative number of status changes of the resource. 
    cum_status_changes = 0

    # Resource status at the end of previous clearing period:  0 - off-line  1 - on-line production  2 - in shutdown process  3 - in startup process 
    resource_status = 0

    # Time in market trading intervals the resource is in the state as of the end of the previous clearing period. 
    time_in_status = ''

    # Resource MW output at the end of previous clearing period. 
    resource_mw = ''

    # Time and date for resourceStatus 
    status_date = ''

    def get_generating_unit(self):
        """ 
        """
        return self._generating_unit

    def set_generating_unit(self, value):
        if self._generating_unit is not None:
            filtered = [x for x in self.generating_unit.unit_initial_conditions if x != self]
            self._generating_unit._unit_initial_conditions = filtered
            
        self._generating_unit = value
        if self._generating_unit is not None:
            self._generating_unit._unit_initial_conditions.append(self)

    generating_unit = property(get_generating_unit, set_generating_unit)

    # <<< unit_initial_conditions
    # @generated
    def __init__(self, cum_status_changes=0, resource_status=0, time_in_status='', resource_mw='', status_date='', generating_unit=None, **kw_args):
        """ Initialises a new 'UnitInitialConditions' instance.
        """
        self.cum_status_changes = cum_status_changes
        self.resource_status = resource_status
        self.time_in_status = time_in_status
        self.resource_mw = resource_mw
        self.status_date = status_date
        self._generating_unit = None
        self.generating_unit = generating_unit

        super(UnitInitialConditions, self).__init__(**kw_args)
    # >>> unit_initial_conditions


class Pnode(IdentifiedObject):
    """ A pricing node is directly associated with a connectivity node.  It is a pricing location for which market participants submit their bids, offers, buy/sell CRRs, and settle.
    """
    # End date-time of the period in which the price node definition is valid 
    end_period = ''

    # True=Public; False=Private Public Pnodes: Prices are published for DA/RT and FTR Markets. Private Pnodes: Location is not usable by Market for Bidding/FTRs/Transactions 
    is_public = False

    # Price node type: Hub (H), Zone (Z), Control Area (C), ?, Bus (B) 
    type = ''

    # Price node usage:  'Control Area' 'Regulation Region' 'Price Zone' 'Spin Region' 'Non-Spin Region' 'Price Hub' 
    usage = ''

    # Start date-time of the period in which the price node definition is valid. 
    begin_period = ''

    def get_connectivity_node(self):
        """ 
        """
        return self._connectivity_node

    def set_connectivity_node(self, value):
        if self._connectivity_node is not None:
            self._connectivity_node._pnode = None
            
        self._connectivity_node = value
        if self._connectivity_node is not None:
            self._connectivity_node._pnode = self
            
    connectivity_node = property(get_connectivity_node, set_connectivity_node)

    def get_rto(self):
        """ 
        """
        return self._rto

    def set_rto(self, value):
        if self._rto is not None:
            filtered = [x for x in self.rto.pnodes if x != self]
            self._rto._pnodes = filtered
            
        self._rto = value
        if self._rto is not None:
            self._rto._pnodes.append(self)

    rto = property(get_rto, set_rto)

    def get_measurements(self):
        """ 
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x._pnode = None
        for y in value:
            y._pnode = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._pnode = self
            self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._pnode = None
            self._measurements.remove(obj)

    def get_receipt_transaction_bids(self):
        """ 
        """
        return self._receipt_transaction_bids

    def set_receipt_transaction_bids(self, value):
        for x in self._receipt_transaction_bids:
            x._receipt_pnode = None
        for y in value:
            y._receipt_pnode = self
        self._receipt_transaction_bids = value
            
    receipt_transaction_bids = property(get_receipt_transaction_bids, set_receipt_transaction_bids)
    
    def add_receipt_transaction_bids(self, *receipt_transaction_bids):
        for obj in receipt_transaction_bids:
            obj._receipt_pnode = self
            self._receipt_transaction_bids.append(obj)
        
    def remove_receipt_transaction_bids(self, *receipt_transaction_bids):
        for obj in receipt_transaction_bids:
            obj._receipt_pnode = None
            self._receipt_transaction_bids.remove(obj)

    def get_pnode_clearing(self):
        """ 
        """
        return self._pnode_clearing

    def set_pnode_clearing(self, value):
        if self._pnode_clearing is not None:
            self._pnode_clearing._pnode = None
            
        self._pnode_clearing = value
        if self._pnode_clearing is not None:
            self._pnode_clearing._pnode = self
            
    pnode_clearing = property(get_pnode_clearing, set_pnode_clearing)

    ftrs = []
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
	        self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
	        self._ftrs.remove(obj)

    def get_registered_resources(self):
        """ A registered resource injects power at one or more connectivity nodes related to a pnode
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for x in self._registered_resources:
            x._pnode = None
        for y in value:
            y._pnode = self
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._pnode = self
            self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._pnode = None
            self._registered_resources.remove(obj)

    def get_delivery_transaction_bids(self):
        """ 
        """
        return self._delivery_transaction_bids

    def set_delivery_transaction_bids(self, value):
        for x in self._delivery_transaction_bids:
            x._delivery_pnode = None
        for y in value:
            y._delivery_pnode = self
        self._delivery_transaction_bids = value
            
    delivery_transaction_bids = property(get_delivery_transaction_bids, set_delivery_transaction_bids)
    
    def add_delivery_transaction_bids(self, *delivery_transaction_bids):
        for obj in delivery_transaction_bids:
            obj._delivery_pnode = self
            self._delivery_transaction_bids.append(obj)
        
    def remove_delivery_transaction_bids(self, *delivery_transaction_bids):
        for obj in delivery_transaction_bids:
            obj._delivery_pnode = None
            self._delivery_transaction_bids.remove(obj)

    # <<< pnode
    # @generated
    def __init__(self, end_period='', is_public=False, type='', usage='', begin_period='', connectivity_node=None, rto=None, measurements=[], receipt_transaction_bids=[], pnode_clearing=None, ftrs=[], registered_resources=[], delivery_transaction_bids=[], **kw_args):
        """ Initialises a new 'Pnode' instance.
        """
        self.end_period = end_period
        self.is_public = is_public
        self.type = type
        self.usage = usage
        self.begin_period = begin_period
        self._connectivity_node = None
        self.connectivity_node = connectivity_node
        self._rto = None
        self.rto = rto
        self._measurements = []
        self.measurements = measurements
        self._receipt_transaction_bids = []
        self.receipt_transaction_bids = receipt_transaction_bids
        self._pnode_clearing = None
        self.pnode_clearing = pnode_clearing
        self._ftrs = []
        self.ftrs = ftrs
        self._registered_resources = []
        self.registered_resources = registered_resources
        self._delivery_transaction_bids = []
        self.delivery_transaction_bids = delivery_transaction_bids

        super(Pnode, self).__init__(**kw_args)
    # >>> pnode


class CapacityBenefitMargin(Profile):
    """ Capacity Benefit Margin (CBM) is defined as that amount of transmission transfer capability reserved by load serving entities to ensure access to generation from interconnected systems to meet generation reliability requirements. Reservation fo CBM by a load serving entity allows that entity to reduce its installed generating capacity below that which may otherwise have been necessary without interconnections to meet its generation reliability requirements.  CBM is modeled as a profile with values in different time periods which are represented by the profile data.
    """
    def get_season(self):
        """ Capacity Benefit Margin may differ based on the season
        """
        return self._season

    def set_season(self, value):
        if self._season is not None:
            filtered = [x for x in self.season.capacity_benefit_margin if x != self]
            self._season._capacity_benefit_margin = filtered
            
        self._season = value
        if self._season is not None:
            self._season._capacity_benefit_margin.append(self)

    season = property(get_season, set_season)

    flowgate = []
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
	        self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
	        self._flowgate.remove(obj)

    # <<< capacity_benefit_margin
    # @generated
    def __init__(self, season=None, flowgate=[], **kw_args):
        """ Initialises a new 'CapacityBenefitMargin' instance.
        """
        self._season = None
        self.season = season
        self._flowgate = []
        self.flowgate = flowgate

        super(CapacityBenefitMargin, self).__init__(**kw_args)
    # >>> capacity_benefit_margin


class EnergyPriceCurve(Curve):
    """ Relationship between a price in $/hour (Y-axis) and a MW value (X-axis).
    """
    def get_ftrs(self):
        """ 
        """
        return self._ftrs

    def set_ftrs(self, value):
        for x in self._ftrs:
            x._energy_price_curve = None
        for y in value:
            y._energy_price_curve = self
        self._ftrs = value
            
    ftrs = property(get_ftrs, set_ftrs)
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._energy_price_curve = self
            self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._energy_price_curve = None
            self._ftrs.remove(obj)

    energy_transactions = []
    
    def add_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
	        self._energy_transactions.append(obj)
        
    def remove_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
	        self._energy_transactions.remove(obj)

    # <<< energy_price_curve
    # @generated
    def __init__(self, ftrs=[], energy_transactions=[], **kw_args):
        """ Initialises a new 'EnergyPriceCurve' instance.
        """
        self._ftrs = []
        self.ftrs = ftrs
        self._energy_transactions = []
        self.energy_transactions = energy_transactions

        super(EnergyPriceCurve, self).__init__(**kw_args)
    # >>> energy_price_curve


class MarketProduct(IdentifiedObject):
    """ A product traded by an RTO (e.g., energy, 10 minute spinning reserve).  Ancillary service product examples include: Regulation Up Regulation Dn Spinning Reserve Non-Spinning Reserve Operating Reserve
    """
    def get_reserve_reqs(self):
        """ Market product associated with reserve requirement must be a reserve or regulation product.
        """
        return self._reserve_reqs

    def set_reserve_reqs(self, value):
        for x in self._reserve_reqs:
            x._market_product = None
        for y in value:
            y._market_product = self
        self._reserve_reqs = value
            
    reserve_reqs = property(get_reserve_reqs, set_reserve_reqs)
    
    def add_reserve_reqs(self, *reserve_reqs):
        for obj in reserve_reqs:
            obj._market_product = self
            self._reserve_reqs.append(obj)
        
    def remove_reserve_reqs(self, *reserve_reqs):
        for obj in reserve_reqs:
            obj._market_product = None
            self._reserve_reqs.remove(obj)

    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x._market_product = None
        for y in value:
            y._market_product = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._market_product = self
            self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._market_product = None
            self._product_bids.remove(obj)

    registered_resources = []
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.remove(obj)

    def get_market(self):
        """ 
        """
        return self._market

    def set_market(self, value):
        if self._market is not None:
            filtered = [x for x in self.market.market_products if x != self]
            self._market._market_products = filtered
            
        self._market = value
        if self._market is not None:
            self._market._market_products.append(self)

    market = property(get_market, set_market)

    # <<< market_product
    # @generated
    def __init__(self, reserve_reqs=[], product_bids=[], registered_resources=[], market=None, **kw_args):
        """ Initialises a new 'MarketProduct' instance.
        """
        self._reserve_reqs = []
        self.reserve_reqs = reserve_reqs
        self._product_bids = []
        self.product_bids = product_bids
        self._registered_resources = []
        self.registered_resources = registered_resources
        self._market = None
        self.market = market

        super(MarketProduct, self).__init__(**kw_args)
    # >>> market_product


class BilateralTransaction(Element):
    """ Bilateral transaction
    """
    # Maximum total transmission (congestion) charges in monetary units 
    total_tran_charge_max = ''

    # Transaction scope: 'Internal' 'External' 
    scope = ''

    # Market type (default=DA) DA - Day Ahead RT - Real Time HA - Hour Ahead 
    market_type = ''

    # Minimum curtailment time in number of trading intervals 
    curtail_time_min = 0

    # Maximum purchase time in number of trading intervals 
    purchase_time_max = 0

    # Maximum curtailment time in number of trading intervals 
    curtail_time_max = 0

    # Transaction type (default 1)  1 - Fixed  2 - Dispatchable continuous  3 - Dispatchable block-loading 
    transaction_type = ''

    # Minimum purchase time in number of trading intervals 
    purchase_time_min = 0

    # <<< bilateral_transaction
    # @generated
    def __init__(self, total_tran_charge_max='', scope='', market_type='', curtail_time_min=0, purchase_time_max=0, curtail_time_max=0, transaction_type='', purchase_time_min=0, **kw_args):
        """ Initialises a new 'BilateralTransaction' instance.
        """
        self.total_tran_charge_max = total_tran_charge_max
        self.scope = scope
        self.market_type = market_type
        self.curtail_time_min = curtail_time_min
        self.purchase_time_max = purchase_time_max
        self.curtail_time_max = curtail_time_max
        self.transaction_type = transaction_type
        self.purchase_time_min = purchase_time_min

        super(BilateralTransaction, self).__init__(**kw_args)
    # >>> bilateral_transaction


class PassThroughBill(Document):
    """ Pass Through Bill is used for: 1)Two sided charge transactions with or without ISO involvement (hence the ?pass thru?) 2) Specific direct charges or payments that are calculated outside or provided directly to settlements 3) Specific charge bill determinants that are externally supplied and used in charge calculations
    """
    # Disputed transaction indicator 
    is_disputed = False

    # Bill period end date 
    bill_end = ''

    # The previous bill period start date 
    previous_start = ''

    # The settlement run type, for example: prelim, final, and rerun. 
    bill_run_type = ''

    # The product identifier for determining the charge type of the transaction. 
    product_code = ''

    # The date the transaction occurs. 
    transaction_date = ''

    # The trade date 
    trade_date = ''

    # The time zone code 
    time_zone = ''

    # A flag indicating whether there is a profile data associated with the PTB. 
    is_profiled = False

    # The start date of service provided, if periodic. 
    service_start = ''

    # The tax on services taken. 
    tax_amount = ''

    # The effective date of the transaction 
    effective_date = ''

    # The company to which the PTB transaction is paid. 
    paid_to = ''

    # The price of product/service. 
    price = ''

    # The company to which the PTB transaction is sold. 
    sold_to = ''

    # The type of transaction. For example, charge customer, bill customer, matching AR/AP, or bill determinant 
    transaction_type = ''

    # The end date of service provided, if periodic. 
    service_end = ''

    # The company by which the PTB transaction service is provided. 
    provided_by = ''

    # The previous bill period end date 
    previous_end = ''

    # Bill period start date 
    bill_start = ''

    # The charge amount of the product/service. 
    amount = ''

    # The company to which the PTB transaction is billed. 
    billed_to = ''

    def get_market_statement_line_item(self):
        """ 
        """
        return self._market_statement_line_item

    def set_market_statement_line_item(self, value):
        if self._market_statement_line_item is not None:
            self._market_statement_line_item._pass_through_bill = None
            
        self._market_statement_line_item = value
        if self._market_statement_line_item is not None:
            self._market_statement_line_item._pass_through_bill = self
            
    market_statement_line_item = property(get_market_statement_line_item, set_market_statement_line_item)

    def get_charge_profiles(self):
        """ 
        """
        return self._charge_profiles

    def set_charge_profiles(self, value):
        for x in self._charge_profiles:
            x._pass_trough_bill = None
        for y in value:
            y._pass_trough_bill = self
        self._charge_profiles = value
            
    charge_profiles = property(get_charge_profiles, set_charge_profiles)
    
    def add_charge_profiles(self, *charge_profiles):
        for obj in charge_profiles:
            obj._pass_trough_bill = self
            self._charge_profiles.append(obj)
        
    def remove_charge_profiles(self, *charge_profiles):
        for obj in charge_profiles:
            obj._pass_trough_bill = None
            self._charge_profiles.remove(obj)

    user_attributes = []
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.remove(obj)

    # <<< pass_through_bill
    # @generated
    def __init__(self, is_disputed=False, bill_end='', previous_start='', bill_run_type='', product_code='', transaction_date='', trade_date='', time_zone='', is_profiled=False, service_start='', tax_amount='', effective_date='', paid_to='', price='', sold_to='', transaction_type='', service_end='', provided_by='', previous_end='', bill_start='', amount='', billed_to='', market_statement_line_item=None, charge_profiles=[], user_attributes=[], **kw_args):
        """ Initialises a new 'PassThroughBill' instance.
        """
        self.is_disputed = is_disputed
        self.bill_end = bill_end
        self.previous_start = previous_start
        self.bill_run_type = bill_run_type
        self.product_code = product_code
        self.transaction_date = transaction_date
        self.trade_date = trade_date
        self.time_zone = time_zone
        self.is_profiled = is_profiled
        self.service_start = service_start
        self.tax_amount = tax_amount
        self.effective_date = effective_date
        self.paid_to = paid_to
        self.price = price
        self.sold_to = sold_to
        self.transaction_type = transaction_type
        self.service_end = service_end
        self.provided_by = provided_by
        self.previous_end = previous_end
        self.bill_start = bill_start
        self.amount = amount
        self.billed_to = billed_to
        self._market_statement_line_item = None
        self.market_statement_line_item = market_statement_line_item
        self._charge_profiles = []
        self.charge_profiles = charge_profiles
        self._user_attributes = []
        self.user_attributes = user_attributes

        super(PassThroughBill, self).__init__(**kw_args)
    # >>> pass_through_bill


class RampRateCurve(Curve):
    """ Ramp rate as a function of resource MW output
    """
    # How ramp rate is applied (e.g., raise or lower, as when applied to a generation resource) 
    ramp_rate_type = ''

    generating_unit = []
    
    def add_generating_unit(self, *generating_unit):
        for obj in generating_unit:
	        self._generating_unit.append(obj)
        
    def remove_generating_unit(self, *generating_unit):
        for obj in generating_unit:
	        self._generating_unit.remove(obj)

    # <<< ramp_rate_curve
    # @generated
    def __init__(self, ramp_rate_type='', generating_unit=[], **kw_args):
        """ Initialises a new 'RampRateCurve' instance.
        """
        self.ramp_rate_type = ramp_rate_type
        self._generating_unit = []
        self.generating_unit = generating_unit

        super(RampRateCurve, self).__init__(**kw_args)
    # >>> ramp_rate_curve


class Bid(Document):
    """ Represents both bids to purchase and offers to sell energy or ancillary services in an RTO-sponsored market.
    """
    # Stop time and date for which bid is applicable. 
    stop_time = ''

    # Start time and date for which bid applies. 
    start_time = ''

 
    market_type = ''

    def get_bid_clearing(self):
        """ 
        """
        return self._bid_clearing

    def set_bid_clearing(self, value):
        if self._bid_clearing is not None:
            self._bid_clearing._bid = None
            
        self._bid_clearing = value
        if self._bid_clearing is not None:
            self._bid_clearing._bid = self
            
    bid_clearing = property(get_bid_clearing, set_bid_clearing)

    def get_market(self):
        """ 
        """
        return self._market

    def set_market(self, value):
        if self._market is not None:
            filtered = [x for x in self.market.bids if x != self]
            self._market._bids = filtered
            
        self._market = value
        if self._market is not None:
            self._market._bids.append(self)

    market = property(get_market, set_market)

    def get_product_bids(self):
        """ A bid comprises one or more product bids of market products
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x._bid = None
        for y in value:
            y._bid = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid = self
            self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid = None
            self._product_bids.remove(obj)

    # <<< bid
    # @generated
    def __init__(self, stop_time='', start_time='', market_type='', bid_clearing=None, market=None, product_bids=[], **kw_args):
        """ Initialises a new 'Bid' instance.
        """
        self.stop_time = stop_time
        self.start_time = start_time
        self.market_type = market_type
        self._bid_clearing = None
        self.bid_clearing = bid_clearing
        self._market = None
        self.market = market
        self._product_bids = []
        self.product_bids = product_bids

        super(Bid, self).__init__(**kw_args)
    # >>> bid


class ChargeProfile(Profile):
    """ A type of profile for financial charges
    """
    # The unit of measure applied to the value attribute of the profile data. 
    unit_of_measure = ''

    # The calculation frequency, daily or monthly. 
    frequency = ''

    # The type of profile.  It could be amount, price, or quantity. 
    type = ''

    # The number of intervals in the profile data. 
    number_interval = 0

    def get_charge_profile_data(self):
        """ 
        """
        return self._charge_profile_data

    def set_charge_profile_data(self, value):
        for x in self._charge_profile_data:
            x._charge_profile = None
        for y in value:
            y._charge_profile = self
        self._charge_profile_data = value
            
    charge_profile_data = property(get_charge_profile_data, set_charge_profile_data)
    
    def add_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._charge_profile = self
            self._charge_profile_data.append(obj)
        
    def remove_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._charge_profile = None
            self._charge_profile_data.remove(obj)

    def get_pass_trough_bill(self):
        """ 
        """
        return self._pass_trough_bill

    def set_pass_trough_bill(self, value):
        if self._pass_trough_bill is not None:
            filtered = [x for x in self.pass_trough_bill.charge_profiles if x != self]
            self._pass_trough_bill._charge_profiles = filtered
            
        self._pass_trough_bill = value
        if self._pass_trough_bill is not None:
            self._pass_trough_bill._charge_profiles.append(self)

    pass_trough_bill = property(get_pass_trough_bill, set_pass_trough_bill)

    def get_bill_determinant(self):
        """ 
        """
        return self._bill_determinant

    def set_bill_determinant(self, value):
        if self._bill_determinant is not None:
            self._bill_determinant._charge_profile = None
            
        self._bill_determinant = value
        if self._bill_determinant is not None:
            self._bill_determinant._charge_profile = self
            
    bill_determinant = property(get_bill_determinant, set_bill_determinant)

    # <<< charge_profile
    # @generated
    def __init__(self, unit_of_measure='', frequency='', type='', number_interval=0, charge_profile_data=[], pass_trough_bill=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfile' instance.
        """
        self.unit_of_measure = unit_of_measure
        self.frequency = frequency
        self.type = type
        self.number_interval = number_interval
        self._charge_profile_data = []
        self.charge_profile_data = charge_profile_data
        self._pass_trough_bill = None
        self.pass_trough_bill = pass_trough_bill
        self._bill_determinant = None
        self.bill_determinant = bill_determinant

        super(ChargeProfile, self).__init__(**kw_args)
    # >>> charge_profile


class ResourceGroupReq(IdentifiedObject):
    """ Ancillary service requirements for a market.
    """
    rtos = []
    
    def add_rtos(self, *rtos):
        for obj in rtos:
	        self._rtos.append(obj)
        
    def remove_rtos(self, *rtos):
        for obj in rtos:
	        self._rtos.remove(obj)

    def get_resource_group(self):
        """ 
        """
        return self._resource_group

    def set_resource_group(self, value):
        if self._resource_group is not None:
            filtered = [x for x in self.resource_group.resource_group_reqs if x != self]
            self._resource_group._resource_group_reqs = filtered
            
        self._resource_group = value
        if self._resource_group is not None:
            self._resource_group._resource_group_reqs.append(self)

    resource_group = property(get_resource_group, set_resource_group)

    # <<< resource_group_req
    # @generated
    def __init__(self, rtos=[], resource_group=None, **kw_args):
        """ Initialises a new 'ResourceGroupReq' instance.
        """
        self._rtos = []
        self.rtos = rtos
        self._resource_group = None
        self.resource_group = resource_group

        super(ResourceGroupReq, self).__init__(**kw_args)
    # >>> resource_group_req


class Market(IdentifiedObject):
    """ Market (e.g., DAM, HAM)  zzMarket operation control parameters.
    """
    # Market start time. 
    start = ''

    # Market end time. 
    end = ''

    # True if daylight savings time (DST) is in effect. 
    dst = False

    # Trading time interval length. 
    time_interval_length = ''

    # Local time zone. 
    local_time_zone = ''

    # Ramping time interval for energy. 
    ramp_interval_energy = ''

    # Ramping time interval for spinning reserve. 
    ramp_interval_spin_res = ''

    # The type of a market. 
    type = ''

    # Ramping time interval for regulation. 
    ramp_interval_reg = ''

    # Ramping time interval for non-spinning reserve. 
    ramp_interval_non_spin_res = ''

    def get_market_products(self):
        """ 
        """
        return self._market_products

    def set_market_products(self, value):
        for x in self._market_products:
            x._market = None
        for y in value:
            y._market = self
        self._market_products = value
            
    market_products = property(get_market_products, set_market_products)
    
    def add_market_products(self, *market_products):
        for obj in market_products:
            obj._market = self
            self._market_products.append(obj)
        
    def remove_market_products(self, *market_products):
        for obj in market_products:
            obj._market = None
            self._market_products.remove(obj)

    registered_resources = []
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.remove(obj)

    def get_rto(self):
        """ 
        """
        return self._rto

    def set_rto(self, value):
        if self._rto is not None:
            filtered = [x for x in self.rto.markets if x != self]
            self._rto._markets = filtered
            
        self._rto = value
        if self._rto is not None:
            self._rto._markets.append(self)

    rto = property(get_rto, set_rto)

    def get_settlements(self):
        """ 
        """
        return self._settlements

    def set_settlements(self, value):
        for x in self._settlements:
            x._market = None
        for y in value:
            y._market = self
        self._settlements = value
            
    settlements = property(get_settlements, set_settlements)
    
    def add_settlements(self, *settlements):
        for obj in settlements:
            obj._market = self
            self._settlements.append(obj)
        
    def remove_settlements(self, *settlements):
        for obj in settlements:
            obj._market = None
            self._settlements.remove(obj)

    def get_bids(self):
        """ 
        """
        return self._bids

    def set_bids(self, value):
        for x in self._bids:
            x._market = None
        for y in value:
            y._market = self
        self._bids = value
            
    bids = property(get_bids, set_bids)
    
    def add_bids(self, *bids):
        for obj in bids:
            obj._market = self
            self._bids.append(obj)
        
    def remove_bids(self, *bids):
        for obj in bids:
            obj._market = None
            self._bids.remove(obj)

    def get_market_factors(self):
        """ 
        """
        return self._market_factors

    def set_market_factors(self, value):
        for x in self._market_factors:
            x._market = None
        for y in value:
            y._market = self
        self._market_factors = value
            
    market_factors = property(get_market_factors, set_market_factors)
    
    def add_market_factors(self, *market_factors):
        for obj in market_factors:
            obj._market = self
            self._market_factors.append(obj)
        
    def remove_market_factors(self, *market_factors):
        for obj in market_factors:
            obj._market = None
            self._market_factors.remove(obj)

    # <<< market
    # @generated
    def __init__(self, start='', end='', dst=False, time_interval_length='', local_time_zone='', ramp_interval_energy='', ramp_interval_spin_res='', type='', ramp_interval_reg='', ramp_interval_non_spin_res='', market_products=[], registered_resources=[], rto=None, settlements=[], bids=[], market_factors=[], **kw_args):
        """ Initialises a new 'Market' instance.
        """
        self.start = start
        self.end = end
        self.dst = dst
        self.time_interval_length = time_interval_length
        self.local_time_zone = local_time_zone
        self.ramp_interval_energy = ramp_interval_energy
        self.ramp_interval_spin_res = ramp_interval_spin_res
        self.type = type
        self.ramp_interval_reg = ramp_interval_reg
        self.ramp_interval_non_spin_res = ramp_interval_non_spin_res
        self._market_products = []
        self.market_products = market_products
        self._registered_resources = []
        self.registered_resources = registered_resources
        self._rto = None
        self.rto = rto
        self._settlements = []
        self.settlements = settlements
        self._bids = []
        self.bids = bids
        self._market_factors = []
        self.market_factors = market_factors

        super(Market, self).__init__(**kw_args)
    # >>> market


class ConstraintTerm(IdentifiedObject):
    """ A constraint term is one element of a linear constraint.
    """
    # The function is an enumerated value that can be 'active', 'reactive', or 'VA' to indicate the type of flow. 
    function = ''

 
    factor = ''

    def get_security_constraint_sum(self):
        """ 
        """
        return self._security_constraint_sum

    def set_security_constraint_sum(self, value):
        if self._security_constraint_sum is not None:
            filtered = [x for x in self.security_constraint_sum.constraint_terms if x != self]
            self._security_constraint_sum._constraint_terms = filtered
            
        self._security_constraint_sum = value
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._constraint_terms.append(self)

    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)

    # <<< constraint_term
    # @generated
    def __init__(self, function='', factor='', security_constraint_sum=None, **kw_args):
        """ Initialises a new 'ConstraintTerm' instance.
        """
        self.function = function
        self.factor = factor
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(ConstraintTerm, self).__init__(**kw_args)
    # >>> constraint_term


class MarketStatementLineItem(IdentifiedObject):
    """ An individual line item on a statement.
    """
    # The unit of measure for the quantity element of the line item. 
    quantity_uom = ''

    # Previous settlement price. 
    previsou_price = 0.0

    # Net ISO settlement amount. 
    net_isoamount = 0.0

    # Previous ISO settlement quantity. 
    previous_isoquantity = 0.0

    # Current ISO settlement quantity. 
    current_isoquantity = 0.0

    # Net settlement quantity, subject to the UOM. 
    net_quantity = 0.0

    # Previous ISO settlement amount. 
    previous_isoamount = 0.0

    # Net settlement price. 
    net_price = 0.0

    # Previous settlement amount. 
    previous_amount = 0.0

    # Net settlement amount. 
    net_amount = 0.0

    # Previous settlement quantity, subject to the UOM. 
    previous_quantity = 0.0

    # Current settlement quantity, subject to the UOM. 
    current_quantity = 0.0

    # Net ISO settlement quantity. 
    net_isoquantity = 0.0

    # Current ISO settlement amount. 
    current_isoamount = 0.0

    # The number of intervals. 
    interval_number = ''

    # Current settlement price. 
    current_price = 0.0

    # The date of which the settlement is run. 
    interval_date = ''

    # Current settlement amount. 
    current_amount = 0.0

    def get_component_market_statement_line_item(self):
        """ 
        """
        return self._component_market_statement_line_item

    def set_component_market_statement_line_item(self, value):
        for x in self._component_market_statement_line_item:
            x._container_market_statement_line_item = None
        for y in value:
            y._container_market_statement_line_item = self
        self._component_market_statement_line_item = value
            
    component_market_statement_line_item = property(get_component_market_statement_line_item, set_component_market_statement_line_item)
    
    def add_component_market_statement_line_item(self, *component_market_statement_line_item):
        for obj in component_market_statement_line_item:
            obj._container_market_statement_line_item = self
            self._component_market_statement_line_item.append(obj)
        
    def remove_component_market_statement_line_item(self, *component_market_statement_line_item):
        for obj in component_market_statement_line_item:
            obj._container_market_statement_line_item = None
            self._component_market_statement_line_item.remove(obj)

    user_attributes = []
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.remove(obj)

    def get_pass_through_bill(self):
        """ 
        """
        return self._pass_through_bill

    def set_pass_through_bill(self, value):
        if self._pass_through_bill is not None:
            self._pass_through_bill._market_statement_line_item = None
            
        self._pass_through_bill = value
        if self._pass_through_bill is not None:
            self._pass_through_bill._market_statement_line_item = self
            
    pass_through_bill = property(get_pass_through_bill, set_pass_through_bill)

    def get_market_statement(self):
        """ 
        """
        return self._market_statement

    def set_market_statement(self, value):
        if self._market_statement is not None:
            filtered = [x for x in self.market_statement.market_statement_line_item if x != self]
            self._market_statement._market_statement_line_item = filtered
            
        self._market_statement = value
        if self._market_statement is not None:
            self._market_statement._market_statement_line_item.append(self)

    market_statement = property(get_market_statement, set_market_statement)

    def get_container_market_statement_line_item(self):
        """ 
        """
        return self._container_market_statement_line_item

    def set_container_market_statement_line_item(self, value):
        if self._container_market_statement_line_item is not None:
            filtered = [x for x in self.container_market_statement_line_item.component_market_statement_line_item if x != self]
            self._container_market_statement_line_item._component_market_statement_line_item = filtered
            
        self._container_market_statement_line_item = value
        if self._container_market_statement_line_item is not None:
            self._container_market_statement_line_item._component_market_statement_line_item.append(self)

    container_market_statement_line_item = property(get_container_market_statement_line_item, set_container_market_statement_line_item)

    # <<< market_statement_line_item
    # @generated
    def __init__(self, quantity_uom='', previsou_price=0.0, net_isoamount=0.0, previous_isoquantity=0.0, current_isoquantity=0.0, net_quantity=0.0, previous_isoamount=0.0, net_price=0.0, previous_amount=0.0, net_amount=0.0, previous_quantity=0.0, current_quantity=0.0, net_isoquantity=0.0, current_isoamount=0.0, interval_number='', current_price=0.0, interval_date='', current_amount=0.0, component_market_statement_line_item=[], user_attributes=[], pass_through_bill=None, market_statement=None, container_market_statement_line_item=None, **kw_args):
        """ Initialises a new 'MarketStatementLineItem' instance.
        """
        self.quantity_uom = quantity_uom
        self.previsou_price = previsou_price
        self.net_isoamount = net_isoamount
        self.previous_isoquantity = previous_isoquantity
        self.current_isoquantity = current_isoquantity
        self.net_quantity = net_quantity
        self.previous_isoamount = previous_isoamount
        self.net_price = net_price
        self.previous_amount = previous_amount
        self.net_amount = net_amount
        self.previous_quantity = previous_quantity
        self.current_quantity = current_quantity
        self.net_isoquantity = net_isoquantity
        self.current_isoamount = current_isoamount
        self.interval_number = interval_number
        self.current_price = current_price
        self.interval_date = interval_date
        self.current_amount = current_amount
        self._component_market_statement_line_item = []
        self.component_market_statement_line_item = component_market_statement_line_item
        self._user_attributes = []
        self.user_attributes = user_attributes
        self._pass_through_bill = None
        self.pass_through_bill = pass_through_bill
        self._market_statement = None
        self.market_statement = market_statement
        self._container_market_statement_line_item = None
        self.container_market_statement_line_item = container_market_statement_line_item

        super(MarketStatementLineItem, self).__init__(**kw_args)
    # >>> market_statement_line_item


class MWLimitSchedule(Curve):
    """ Maximum MW and optionally Minimum MW (Y1 and Y2, respectively)
    """
    def get_security_constraint_limit(self):
        """ 
        """
        return self._security_constraint_limit

    def set_security_constraint_limit(self, value):
        if self._security_constraint_limit is not None:
            self._security_constraint_limit._mwlimit_schedules = None
            
        self._security_constraint_limit = value
        if self._security_constraint_limit is not None:
            self._security_constraint_limit._mwlimit_schedules = self
            
    security_constraint_limit = property(get_security_constraint_limit, set_security_constraint_limit)

    # <<< mwlimit_schedule
    # @generated
    def __init__(self, security_constraint_limit=None, **kw_args):
        """ Initialises a new 'MWLimitSchedule' instance.
        """
        self._security_constraint_limit = None
        self.security_constraint_limit = security_constraint_limit

        super(MWLimitSchedule, self).__init__(**kw_args)
    # >>> mwlimit_schedule


class ProductBid(IdentifiedObject):
    """ Component of a bid that pertains to one market product.
    """
    def get_bid(self):
        """ A bid comprises one or more product bids of market products
        """
        return self._bid

    def set_bid(self, value):
        if self._bid is not None:
            filtered = [x for x in self.bid.product_bids if x != self]
            self._bid._product_bids = filtered
            
        self._bid = value
        if self._bid is not None:
            self._bid._product_bids.append(self)

    bid = property(get_bid, set_bid)

    def get_product_bid_clearing(self):
        """ 
        """
        return self._product_bid_clearing

    def set_product_bid_clearing(self, value):
        if self._product_bid_clearing is not None:
            filtered = [x for x in self.product_bid_clearing.product_bids if x != self]
            self._product_bid_clearing._product_bids = filtered
            
        self._product_bid_clearing = value
        if self._product_bid_clearing is not None:
            self._product_bid_clearing._product_bids.append(self)

    product_bid_clearing = property(get_product_bid_clearing, set_product_bid_clearing)

    def get_bid_price_curve(self):
        """ 
        """
        return self._bid_price_curve

    def set_bid_price_curve(self, value):
        if self._bid_price_curve is not None:
            filtered = [x for x in self.bid_price_curve.product_bids if x != self]
            self._bid_price_curve._product_bids = filtered
            
        self._bid_price_curve = value
        if self._bid_price_curve is not None:
            self._bid_price_curve._product_bids.append(self)

    bid_price_curve = property(get_bid_price_curve, set_bid_price_curve)

    def get_market_product(self):
        """ 
        """
        return self._market_product

    def set_market_product(self, value):
        if self._market_product is not None:
            filtered = [x for x in self.market_product.product_bids if x != self]
            self._market_product._product_bids = filtered
            
        self._market_product = value
        if self._market_product is not None:
            self._market_product._product_bids.append(self)

    market_product = property(get_market_product, set_market_product)

    # <<< product_bid
    # @generated
    def __init__(self, bid=None, product_bid_clearing=None, bid_price_curve=None, market_product=None, **kw_args):
        """ Initialises a new 'ProductBid' instance.
        """
        self._bid = None
        self.bid = bid
        self._product_bid_clearing = None
        self.product_bid_clearing = product_bid_clearing
        self._bid_price_curve = None
        self.bid_price_curve = bid_price_curve
        self._market_product = None
        self.market_product = market_product

        super(ProductBid, self).__init__(**kw_args)
    # >>> product_bid


class SecurityConstraints(IdentifiedObject):
    """ Typical for regional transmission operators (RTOs), these constraints include transmission as well as generation group constraints identified in both base case and critical contingency cases.
    """
    # Maximum MW limit 
    max_mw = ''

    # Minimum MW limit (only for transmission constraints). 
    min_mw = ''

    # Actual branch or group of branches MW flow (only for transmission constraints) 
    actual_mw = ''

    def get_rto(self):
        """ 
        """
        return self._rto

    def set_rto(self, value):
        if self._rto is not None:
            filtered = [x for x in self.rto.security_constraints if x != self]
            self._rto._security_constraints = filtered
            
        self._rto = value
        if self._rto is not None:
            self._rto._security_constraints.append(self)

    rto = property(get_rto, set_rto)

    # <<< security_constraints
    # @generated
    def __init__(self, max_mw='', min_mw='', actual_mw='', rto=None, **kw_args):
        """ Initialises a new 'SecurityConstraints' instance.
        """
        self.max_mw = max_mw
        self.min_mw = min_mw
        self.actual_mw = actual_mw
        self._rto = None
        self.rto = rto

        super(SecurityConstraints, self).__init__(**kw_args)
    # >>> security_constraints


class MarketStatement(Document):
    """ A statement is a roll up of statement line items. Each statement along with its line items provide the details of specific charges at any given time.
    """
    # The date of which this statement is issued. 
    transaction_date = ''

    # The start of a bill period. 
    start = ''

    # The end of a bill period. 
    end = ''

    # The version number of previous statement (in the case of true up). 
    reference_number = ''

    # The date of which Settlement is run. 
    trade_date = ''

    def get_market_statement_line_item(self):
        """ 
        """
        return self._market_statement_line_item

    def set_market_statement_line_item(self, value):
        for x in self._market_statement_line_item:
            x._market_statement = None
        for y in value:
            y._market_statement = self
        self._market_statement_line_item = value
            
    market_statement_line_item = property(get_market_statement_line_item, set_market_statement_line_item)
    
    def add_market_statement_line_item(self, *market_statement_line_item):
        for obj in market_statement_line_item:
            obj._market_statement = self
            self._market_statement_line_item.append(obj)
        
    def remove_market_statement_line_item(self, *market_statement_line_item):
        for obj in market_statement_line_item:
            obj._market_statement = None
            self._market_statement_line_item.remove(obj)

    # <<< market_statement
    # @generated
    def __init__(self, transaction_date='', start='', end='', reference_number='', trade_date='', market_statement_line_item=[], **kw_args):
        """ Initialises a new 'MarketStatement' instance.
        """
        self.transaction_date = transaction_date
        self.start = start
        self.end = end
        self.reference_number = reference_number
        self.trade_date = trade_date
        self._market_statement_line_item = []
        self.market_statement_line_item = market_statement_line_item

        super(MarketStatement, self).__init__(**kw_args)
    # >>> market_statement


class SensitivityPriceCurve(Curve):
    """ Optionally, this curve expresses elasticity of the associated requirement.  For example, used to reduce requirements when clearing price exceeds reasonable values when the supply quantity becomes scarce.  For example, a single point value of $1000/MW for a spinning reserve will cause a reduction in the required spinning reserve.  X axis is constrained quantity (e.g., MW) Y1 axis is money per constrained quantity
    """
    def get_reserve_req(self):
        """ 
        """
        return self._reserve_req

    def set_reserve_req(self, value):
        if self._reserve_req is not None:
            self._reserve_req._sensitivity_price_curve = None
            
        self._reserve_req = value
        if self._reserve_req is not None:
            self._reserve_req._sensitivity_price_curve = self
            
    reserve_req = property(get_reserve_req, set_reserve_req)

    # <<< sensitivity_price_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'SensitivityPriceCurve' instance.
        """
        self._reserve_req = None
        self.reserve_req = reserve_req

        super(SensitivityPriceCurve, self).__init__(**kw_args)
    # >>> sensitivity_price_curve


class Settlement(Document):
    """ Specifies a settlement run.
    """
    # The trade date on which the settlement is run. 
    trade_date = ''

    def get_market(self):
        """ 
        """
        return self._market

    def set_market(self, value):
        if self._market is not None:
            filtered = [x for x in self.market.settlements if x != self]
            self._market._settlements = filtered
            
        self._market = value
        if self._market is not None:
            self._market._settlements.append(self)

    market = property(get_market, set_market)

    erp_invoice_line_items = []
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
	        self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
	        self._erp_invoice_line_items.remove(obj)

    erp_ledger_entries = []
    
    def add_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
	        self._erp_ledger_entries.append(obj)
        
    def remove_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
	        self._erp_ledger_entries.remove(obj)

    # <<< settlement
    # @generated
    def __init__(self, trade_date='', market=None, erp_invoice_line_items=[], erp_ledger_entries=[], **kw_args):
        """ Initialises a new 'Settlement' instance.
        """
        self.trade_date = trade_date
        self._market = None
        self.market = market
        self._erp_invoice_line_items = []
        self.erp_invoice_line_items = erp_invoice_line_items
        self._erp_ledger_entries = []
        self.erp_ledger_entries = erp_ledger_entries

        super(Settlement, self).__init__(**kw_args)
    # >>> settlement


class BillDeterminant(Document):
    # The version of configuration of calculation logic in the settlement. 
    config_version = ''

    # Number of intervals of bill determiant in trade day, eg 300 for five minute intervals. 
    number_interval = 0

    # The level of precision in the current value. 
    precision_level = ''

    # The UOM for the current value of the Bill Determinant. 
    unit_of_measure = ''

    # Level in charge calculation order. 
    calculation_level = ''

    def get_charge_profile_data(self):
        """ 
        """
        return self._charge_profile_data

    def set_charge_profile_data(self, value):
        for x in self._charge_profile_data:
            x._bill_determinant = None
        for y in value:
            y._bill_determinant = self
        self._charge_profile_data = value
            
    charge_profile_data = property(get_charge_profile_data, set_charge_profile_data)
    
    def add_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._bill_determinant = self
            self._charge_profile_data.append(obj)
        
    def remove_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._bill_determinant = None
            self._charge_profile_data.remove(obj)

    def get_charge_profile(self):
        """ 
        """
        return self._charge_profile

    def set_charge_profile(self, value):
        if self._charge_profile is not None:
            self._charge_profile._bill_determinant = None
            
        self._charge_profile = value
        if self._charge_profile is not None:
            self._charge_profile._bill_determinant = self
            
    charge_profile = property(get_charge_profile, set_charge_profile)

    user_attributes = []
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
	        self._user_attributes.remove(obj)

    # <<< bill_determinant
    # @generated
    def __init__(self, config_version='', number_interval=0, precision_level='', unit_of_measure='', calculation_level='', charge_profile_data=[], charge_profile=None, user_attributes=[], **kw_args):
        """ Initialises a new 'BillDeterminant' instance.
        """
        self.config_version = config_version
        self.number_interval = number_interval
        self.precision_level = precision_level
        self.unit_of_measure = unit_of_measure
        self.calculation_level = calculation_level
        self._charge_profile_data = []
        self.charge_profile_data = charge_profile_data
        self._charge_profile = None
        self.charge_profile = charge_profile
        self._user_attributes = []
        self.user_attributes = user_attributes

        super(BillDeterminant, self).__init__(**kw_args)
    # >>> bill_determinant


class MarketFactors(Document):
    """ Aggregation of market information relative for a specific time interval.
    """
    # The start of the time interval for which requirement is defined. 
    interval_start_time = ''

    def get_market(self):
        """ 
        """
        return self._market

    def set_market(self, value):
        if self._market is not None:
            filtered = [x for x in self.market.market_factors if x != self]
            self._market._market_factors = filtered
            
        self._market = value
        if self._market is not None:
            self._market._market_factors.append(self)

    market = property(get_market, set_market)

    erp_invoices = []
    
    def add_erp_invoices(self, *erp_invoices):
        for obj in erp_invoices:
	        self._erp_invoices.append(obj)
        
    def remove_erp_invoices(self, *erp_invoices):
        for obj in erp_invoices:
	        self._erp_invoices.remove(obj)

    activity_records = []
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
	        self._activity_records.remove(obj)

    # <<< market_factors
    # @generated
    def __init__(self, interval_start_time='', market=None, erp_invoices=[], activity_records=[], **kw_args):
        """ Initialises a new 'MarketFactors' instance.
        """
        self.interval_start_time = interval_start_time
        self._market = None
        self.market = market
        self._erp_invoices = []
        self.erp_invoices = erp_invoices
        self._activity_records = []
        self.activity_records = activity_records

        super(MarketFactors, self).__init__(**kw_args)
    # >>> market_factors


class DefaultConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) applied as a default value if no specific constraint limits are specified for a contingency analysis. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
    def get_security_constraint_sum(self):
        """ 
        """
        return self._security_constraint_sum

    def set_security_constraint_sum(self, value):
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._default_constraint_limit = None
            
        self._security_constraint_sum = value
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._default_constraint_limit = self
            
    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)

    # <<< default_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'DefaultConstraintLimit' instance.
        """
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(DefaultConstraintLimit, self).__init__(**kw_args)
    # >>> default_constraint_limit


class SchedulingCoordinator(ErpOrganisation):
    """ In certain ISO/RTO operations, market participants are represented by Scheduling Coordinators (SCs) that are registered with the ISO/RTO. One participant can register multiple SCs with the ISO/RTO.  Many participants can do business with the ISO/RTO using a single SC. Each generator resource can only be scheduled by one SC. One SC can schedule multiple generators. A load scheduling point can be used by multiple SCs. Each SC can schedule load at multiple scheduling points. Each SC can have more than one load schedule at any load scheduling point as long as each load schedule at the same load scheduling point has a separate resource ID and settlement-quality meter. An inter-tie scheduling point can be used by multiple SCs. Each SC can schedule interchange at multiple inter-tie scheduling points. An SC can have multiple interchange schedules at the same inter-tie scheduling point by assigning a unique interchange identifier to each interchange schedule.
    """
    pass
    # <<< scheduling_coordinator
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'SchedulingCoordinator' instance.
        """

        super(SchedulingCoordinator, self).__init__(**kw_args)
    # >>> scheduling_coordinator


class BidSet(IdentifiedObject):
    """ As set of mutually exclusive bids for which a maximum of one may be scheduled. Of these generating bids, only one generating bid can be scheduled at a time.
    """
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x._bid_set = None
        for y in value:
            y._bid_set = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._bid_set = self
            self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._bid_set = None
            self._generating_bids.remove(obj)

    # <<< bid_set
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'BidSet' instance.
        """
        self._generating_bids = []
        self.generating_bids = generating_bids

        super(BidSet, self).__init__(**kw_args)
    # >>> bid_set


class LoadReductionPriceCurve(Curve):
    """ This is the price sensitivity that bidder expresses for allowing market load interruption.  Relationship between price (Y1-axis) vs. MW (X-axis).
    """
    def get_load_bids(self):
        """ 
        """
        return self._load_bids

    def set_load_bids(self, value):
        for x in self._load_bids:
            x._load_reduction_price_curve = None
        for y in value:
            y._load_reduction_price_curve = self
        self._load_bids = value
            
    load_bids = property(get_load_bids, set_load_bids)
    
    def add_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._load_reduction_price_curve = self
            self._load_bids.append(obj)
        
    def remove_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._load_reduction_price_curve = None
            self._load_bids.remove(obj)

    # <<< load_reduction_price_curve
    # @generated
    def __init__(self, load_bids=[], **kw_args):
        """ Initialises a new 'LoadReductionPriceCurve' instance.
        """
        self._load_bids = []
        self.load_bids = load_bids

        super(LoadReductionPriceCurve, self).__init__(**kw_args)
    # >>> load_reduction_price_curve


class BidClearing(Element):
    """ Bid clearing results posted for a given settlement period.
    """
    # Energy lost opportunity cost in monetary units. 
    lost_op_cost = ''

    # Start up cost in case of energy commodity in monetary units. 
    start_up_cost = ''

    # No-load cost in monetary units. 
    no_load_cost = ''

    def get_bid(self):
        """ 
        """
        return self._bid

    def set_bid(self, value):
        if self._bid is not None:
            self._bid._bid_clearing = None
            
        self._bid = value
        if self._bid is not None:
            self._bid._bid_clearing = self
            
    bid = property(get_bid, set_bid)

    # <<< bid_clearing
    # @generated
    def __init__(self, lost_op_cost='', start_up_cost='', no_load_cost='', bid=None, **kw_args):
        """ Initialises a new 'BidClearing' instance.
        """
        self.lost_op_cost = lost_op_cost
        self.start_up_cost = start_up_cost
        self.no_load_cost = no_load_cost
        self._bid = None
        self.bid = bid

        super(BidClearing, self).__init__(**kw_args)
    # >>> bid_clearing


class Flowgate(PowerSystemResource):
    """ A flowgate, is single or group of transmission elements intended to model MW flow impact relating to transmission limitations and transmission service usage.
    """
    # Flag to indicate if Flowgate qualified as coordinated Flowgate 
    coordinated_flag = False

    # Flag to indicate if Flowgate qualified as reciprocal Flowgate 
    reciprocal_flag = False

    # Date at which point Flowgate becomes active.  Used to insert future Flowgates or Flowgates returning from an outage condition. 
    in_service_date = ''

    # Percentage of counterflow to remove/exclude from the AFC calculation.  Integer.  Must be 100 or less. 
    counter_flow_value = 0

    # Flag to indicate if Flowgate is utilized for coordination of ATC. 
    atc_flag = False

    # Date at which point Flowgate becomes inactive. Used to insert outage condition. 
    out_of_service_date = ''

    # Standard Reliabilty Entity (e.g. in North America NERC) that has agreed per a reciprocal agreement to manage coordination on the Flowgate.  Will always be either True or False - if not a reciprocal Flowgate, will be false. 
    managing_entity_flag = False

    # The Registered Name utilized in the IDC and/or Book of Flowgates 
    idc_operational_name = ''

    # Percentage of positive impact to include in the AFC calculation.  Integer.  Must be 100 or less. 
    positive_impact_value = 0

    # Date upon which study of Flowgate to determine coordinated status was performed.  May be null is no study undertaken yet. 
    coordination_study_date = ''

    # The registered Flowgate ID Assigned by the IDC and/or Book of Flowgate. 
    idc_assigned_id = 0

    # Date at which point Flowgate should be removed from the Interchange Distribution Calculatin (IDC). 
    deletion_date = ''

    transmission_provider = []
    
    def add_transmission_provider(self, *transmission_provider):
        for obj in transmission_provider:
	        self._transmission_provider.append(obj)
        
    def remove_transmission_provider(self, *transmission_provider):
        for obj in transmission_provider:
	        self._transmission_provider.remove(obj)

    def get_sub_control_area(self):
        """ A control area may own 0 to n flowgates A flowgate must be owned by exactly 1 control area
        """
        return self._sub_control_area

    def set_sub_control_area(self, value):
        if self._sub_control_area is not None:
            filtered = [x for x in self.sub_control_area.flowgate if x != self]
            self._sub_control_area._flowgate = filtered
            
        self._sub_control_area = value
        if self._sub_control_area is not None:
            self._sub_control_area._flowgate.append(self)

    sub_control_area = property(get_sub_control_area, set_sub_control_area)

    def get_ftrs(self):
        """ 
        """
        return self._ftrs

    def set_ftrs(self, value):
        for x in self._ftrs:
            x._flowgate = None
        for y in value:
            y._flowgate = self
        self._ftrs = value
            
    ftrs = property(get_ftrs, set_ftrs)
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._flowgate = self
            self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._flowgate = None
            self._ftrs.remove(obj)

    lines = []
    
    def add_lines(self, *lines):
        for obj in lines:
	        self._lines.append(obj)
        
    def remove_lines(self, *lines):
        for obj in lines:
	        self._lines.remove(obj)

    capacity_benefit_margin = []
    
    def add_capacity_benefit_margin(self, *capacity_benefit_margin):
        for obj in capacity_benefit_margin:
	        self._capacity_benefit_margin.append(obj)
        
    def remove_capacity_benefit_margin(self, *capacity_benefit_margin):
        for obj in capacity_benefit_margin:
	        self._capacity_benefit_margin.remove(obj)

    def get_transmission_reliability_margin(self):
        """ A fowgate may have 0 to 1 TRM
        """
        return self._transmission_reliability_margin

    def set_transmission_reliability_margin(self, value):
        if self._transmission_reliability_margin is not None:
            filtered = [x for x in self.transmission_reliability_margin.flowgate if x != self]
            self._transmission_reliability_margin._flowgate = filtered
            
        self._transmission_reliability_margin = value
        if self._transmission_reliability_margin is not None:
            self._transmission_reliability_margin._flowgate.append(self)

    transmission_reliability_margin = property(get_transmission_reliability_margin, set_transmission_reliability_margin)

    def get_violation_limits(self):
        """ 
        """
        return self._violation_limits

    def set_violation_limits(self, value):
        for x in self._violation_limits:
            x._flowgate = None
        for y in value:
            y._flowgate = self
        self._violation_limits = value
            
    violation_limits = property(get_violation_limits, set_violation_limits)
    
    def add_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            obj._flowgate = self
            self._violation_limits.append(obj)
        
    def remove_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            obj._flowgate = None
            self._violation_limits.remove(obj)

    power_transormers = []
    
    def add_power_transormers(self, *power_transormers):
        for obj in power_transormers:
	        self._power_transormers.append(obj)
        
    def remove_power_transormers(self, *power_transormers):
        for obj in power_transormers:
	        self._power_transormers.remove(obj)

    # <<< flowgate
    # @generated
    def __init__(self, coordinated_flag=False, reciprocal_flag=False, in_service_date='', counter_flow_value=0, atc_flag=False, out_of_service_date='', managing_entity_flag=False, idc_operational_name='', positive_impact_value=0, coordination_study_date='', idc_assigned_id=0, deletion_date='', transmission_provider=[], sub_control_area=None, ftrs=[], lines=[], capacity_benefit_margin=[], transmission_reliability_margin=None, violation_limits=[], power_transormers=[], **kw_args):
        """ Initialises a new 'Flowgate' instance.
        """
        self.coordinated_flag = coordinated_flag
        self.reciprocal_flag = reciprocal_flag
        self.in_service_date = in_service_date
        self.counter_flow_value = counter_flow_value
        self.atc_flag = atc_flag
        self.out_of_service_date = out_of_service_date
        self.managing_entity_flag = managing_entity_flag
        self.idc_operational_name = idc_operational_name
        self.positive_impact_value = positive_impact_value
        self.coordination_study_date = coordination_study_date
        self.idc_assigned_id = idc_assigned_id
        self.deletion_date = deletion_date
        self._transmission_provider = []
        self.transmission_provider = transmission_provider
        self._sub_control_area = None
        self.sub_control_area = sub_control_area
        self._ftrs = []
        self.ftrs = ftrs
        self._lines = []
        self.lines = lines
        self._capacity_benefit_margin = []
        self.capacity_benefit_margin = capacity_benefit_margin
        self._transmission_reliability_margin = None
        self.transmission_reliability_margin = transmission_reliability_margin
        self._violation_limits = []
        self.violation_limits = violation_limits
        self._power_transormers = []
        self.power_transormers = power_transormers

        super(Flowgate, self).__init__(**kw_args)
    # >>> flowgate


class NotificationTimeCurve(Curve):
    """ Notification time curve as a function of down time.  Relationship between crew notification time (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x._notification_time_curve = None
        for y in value:
            y._notification_time_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._notification_time_curve = self
            self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._notification_time_curve = None
            self._generating_bids.remove(obj)

    # <<< notification_time_curve
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'NotificationTimeCurve' instance.
        """
        self._generating_bids = []
        self.generating_bids = generating_bids

        super(NotificationTimeCurve, self).__init__(**kw_args)
    # >>> notification_time_curve


class BaseCaseConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) assigned to a contingency analysis base case. Use CurveSchedule XAxisUnits to specify MW or MVA. To be used only if the BaseCaseConstraintLimit differs from the DefaultConstraintLimit.
    """
    def get_security_constraint_sum(self):
        """ 
        """
        return self._security_constraint_sum

    def set_security_constraint_sum(self, value):
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._base_case_constraint_limit = None
            
        self._security_constraint_sum = value
        if self._security_constraint_sum is not None:
            self._security_constraint_sum._base_case_constraint_limit = self
            
    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)

    # <<< base_case_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'BaseCaseConstraintLimit' instance.
        """
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(BaseCaseConstraintLimit, self).__init__(**kw_args)
    # >>> base_case_constraint_limit


class StartUpTimeCurve(Curve):
    """ Startup time curve as a function of down time, where time is specified in seconds.  Relationship between unit startup time (Y1-axis) vs. unit elapsed down time (X-axis).
    """
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x._start_up_time_curve = None
        for y in value:
            y._start_up_time_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_time_curve = self
            self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_time_curve = None
            self._generating_bids.remove(obj)

    # <<< start_up_time_curve
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'StartUpTimeCurve' instance.
        """
        self._generating_bids = []
        self.generating_bids = generating_bids

        super(StartUpTimeCurve, self).__init__(**kw_args)
    # >>> start_up_time_curve


class ResourceGroup(IdentifiedObject):
    """ A logical grouping of resources that are used to model location of types of requirements for ancillary services such as spinning reserve zones, regulation zones, etc.
    """
    def get_resource_group_reqs(self):
        """ 
        """
        return self._resource_group_reqs

    def set_resource_group_reqs(self, value):
        for x in self._resource_group_reqs:
            x._resource_group = None
        for y in value:
            y._resource_group = self
        self._resource_group_reqs = value
            
    resource_group_reqs = property(get_resource_group_reqs, set_resource_group_reqs)
    
    def add_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            obj._resource_group = self
            self._resource_group_reqs.append(obj)
        
    def remove_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            obj._resource_group = None
            self._resource_group_reqs.remove(obj)

    registered_resources = []
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
	        self._registered_resources.remove(obj)

    # <<< resource_group
    # @generated
    def __init__(self, resource_group_reqs=[], registered_resources=[], **kw_args):
        """ Initialises a new 'ResourceGroup' instance.
        """
        self._resource_group_reqs = []
        self.resource_group_reqs = resource_group_reqs
        self._registered_resources = []
        self.registered_resources = registered_resources

        super(ResourceGroup, self).__init__(**kw_args)
    # >>> resource_group


class ReserveReqCurve(RegularIntervalSchedule):
    """ A curve relating  reserve requirement versus time, showing the values of a specific reserve requirement for each unit of the period covered. The  curve can be based on 'absolute' time or on 'normalized' time.  X is time, typically expressed in absolute time Y1 is reserve requirement, typically expressed in MW
    """
    def get_reserve_req(self):
        """ 
        """
        return self._reserve_req

    def set_reserve_req(self, value):
        if self._reserve_req is not None:
            self._reserve_req._reserve_req_curve = None
            
        self._reserve_req = value
        if self._reserve_req is not None:
            self._reserve_req._reserve_req_curve = self
            
    reserve_req = property(get_reserve_req, set_reserve_req)

    # <<< reserve_req_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'ReserveReqCurve' instance.
        """
        self._reserve_req = None
        self.reserve_req = reserve_req

        super(ReserveReqCurve, self).__init__(**kw_args)
    # >>> reserve_req_curve


class StartUpCostCurve(Curve):
    """ Startup costs and time as a function of down time.  Relationship between unit startup cost (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x._start_up_cost_curve = None
        for y in value:
            y._start_up_cost_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_cost_curve = self
            self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_cost_curve = None
            self._generating_bids.remove(obj)

    registered_generators = []
    
    def add_registered_generators(self, *registered_generators):
        for obj in registered_generators:
	        self._registered_generators.append(obj)
        
    def remove_registered_generators(self, *registered_generators):
        for obj in registered_generators:
	        self._registered_generators.remove(obj)

    # <<< start_up_cost_curve
    # @generated
    def __init__(self, generating_bids=[], registered_generators=[], **kw_args):
        """ Initialises a new 'StartUpCostCurve' instance.
        """
        self._generating_bids = []
        self.generating_bids = generating_bids
        self._registered_generators = []
        self.registered_generators = registered_generators

        super(StartUpCostCurve, self).__init__(**kw_args)
    # >>> start_up_cost_curve


class ViolationLimit(Limit):
    """ A type of limit that indicates if it is enforced and, through association, the organisation responsible for setting the limit.
    """
    # True if limit is enforced. 
    enforced = False

    def get_season(self):
        """ Limits may differ based on the season
        """
        return self._season

    def set_season(self, value):
        if self._season is not None:
            filtered = [x for x in self.season.violation_limits if x != self]
            self._season._violation_limits = filtered
            
        self._season = value
        if self._season is not None:
            self._season._violation_limits.append(self)

    season = property(get_season, set_season)

    organisations = []
    
    def add_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
	        self._organisations.remove(obj)

    def get_measurement(self):
        """ 
        """
        return self._measurement

    def set_measurement(self, value):
        if self._measurement is not None:
            filtered = [x for x in self.measurement.violation_limits if x != self]
            self._measurement._violation_limits = filtered
            
        self._measurement = value
        if self._measurement is not None:
            self._measurement._violation_limits.append(self)

    measurement = property(get_measurement, set_measurement)

    def get_flowgate(self):
        """ 
        """
        return self._flowgate

    def set_flowgate(self, value):
        if self._flowgate is not None:
            filtered = [x for x in self.flowgate.violation_limits if x != self]
            self._flowgate._violation_limits = filtered
            
        self._flowgate = value
        if self._flowgate is not None:
            self._flowgate._violation_limits.append(self)

    flowgate = property(get_flowgate, set_flowgate)

    # <<< violation_limit
    # @generated
    def __init__(self, enforced=False, season=None, organisations=[], measurement=None, flowgate=None, **kw_args):
        """ Initialises a new 'ViolationLimit' instance.
        """
        self.enforced = enforced
        self._season = None
        self.season = season
        self._organisations = []
        self.organisations = organisations
        self._measurement = None
        self.measurement = measurement
        self._flowgate = None
        self.flowgate = flowgate

        super(ViolationLimit, self).__init__(**kw_args)
    # >>> violation_limit


class LossPenaltyFactor(MarketFactors):
    """ Loss penalty factor applied to a ConnectivityNode for a given time interval.
    """
    connectivity_nodes = []
    
    def add_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
	        self._connectivity_nodes.append(obj)
        
    def remove_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
	        self._connectivity_nodes.remove(obj)

    # <<< loss_penalty_factor
    # @generated
    def __init__(self, connectivity_nodes=[], **kw_args):
        """ Initialises a new 'LossPenaltyFactor' instance.
        """
        self._connectivity_nodes = []
        self.connectivity_nodes = connectivity_nodes

        super(LossPenaltyFactor, self).__init__(**kw_args)
    # >>> loss_penalty_factor


class ProductBidClearing(MarketFactors):
    """ Product Bid clearing results posted for a given settlement period.
    """
    # Cleared MWs. 
    cleared_mw = ''

    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x._product_bid_clearing = None
        for y in value:
            y._product_bid_clearing = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._product_bid_clearing = self
            self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._product_bid_clearing = None
            self._product_bids.remove(obj)

    # <<< product_bid_clearing
    # @generated
    def __init__(self, cleared_mw='', product_bids=[], **kw_args):
        """ Initialises a new 'ProductBidClearing' instance.
        """
        self.cleared_mw = cleared_mw
        self._product_bids = []
        self.product_bids = product_bids

        super(ProductBidClearing, self).__init__(**kw_args)
    # >>> product_bid_clearing


class AncillaryServiceClearing(MarketFactors):
    """ Ancillary services clearing results are posted for a given settlement period.
    """
    # Market clearing price (MCP) in monetary units. 
    mcp = ''

    # Cleared MWs. 
    cleared_mw = ''

    # Requirement type:  'En' - Energy  'Ru' - Regulation Up  'Rd' - Regulation Dn  'Sr' - Spinning Reserve  'Nr' - Non-Spinning Reserve  'Or' - Operating Reserve 
    commodity_type = ''

    def get_market_case_clearing(self):
        """ 
        """
        return self._market_case_clearing

    def set_market_case_clearing(self, value):
        if self._market_case_clearing is not None:
            filtered = [x for x in self.market_case_clearing.ancillary_service_clearing if x != self]
            self._market_case_clearing._ancillary_service_clearing = filtered
            
        self._market_case_clearing = value
        if self._market_case_clearing is not None:
            self._market_case_clearing._ancillary_service_clearing.append(self)

    market_case_clearing = property(get_market_case_clearing, set_market_case_clearing)

    # <<< ancillary_service_clearing
    # @generated
    def __init__(self, mcp='', cleared_mw='', commodity_type='', market_case_clearing=None, **kw_args):
        """ Initialises a new 'AncillaryServiceClearing' instance.
        """
        self.mcp = mcp
        self.cleared_mw = cleared_mw
        self.commodity_type = commodity_type
        self._market_case_clearing = None
        self.market_case_clearing = market_case_clearing

        super(AncillaryServiceClearing, self).__init__(**kw_args)
    # >>> ancillary_service_clearing


class ReserveReq(ResourceGroupReq):
    """ Requirements for minimum amount of reserve and/or regulation to be supplied by a set of qualified resources.
    """
    def get_sensitivity_price_curve(self):
        """ 
        """
        return self._sensitivity_price_curve

    def set_sensitivity_price_curve(self, value):
        if self._sensitivity_price_curve is not None:
            self._sensitivity_price_curve._reserve_req = None
            
        self._sensitivity_price_curve = value
        if self._sensitivity_price_curve is not None:
            self._sensitivity_price_curve._reserve_req = self
            
    sensitivity_price_curve = property(get_sensitivity_price_curve, set_sensitivity_price_curve)

    def get_market_product(self):
        """ Market product associated with reserve requirement must be a reserve or regulation product.
        """
        return self._market_product

    def set_market_product(self, value):
        if self._market_product is not None:
            filtered = [x for x in self.market_product.reserve_reqs if x != self]
            self._market_product._reserve_reqs = filtered
            
        self._market_product = value
        if self._market_product is not None:
            self._market_product._reserve_reqs.append(self)

    market_product = property(get_market_product, set_market_product)

    def get_reserve_req_curve(self):
        """ 
        """
        return self._reserve_req_curve

    def set_reserve_req_curve(self, value):
        if self._reserve_req_curve is not None:
            self._reserve_req_curve._reserve_req = None
            
        self._reserve_req_curve = value
        if self._reserve_req_curve is not None:
            self._reserve_req_curve._reserve_req = self
            
    reserve_req_curve = property(get_reserve_req_curve, set_reserve_req_curve)

    # <<< reserve_req
    # @generated
    def __init__(self, sensitivity_price_curve=None, market_product=None, reserve_req_curve=None, **kw_args):
        """ Initialises a new 'ReserveReq' instance.
        """
        self._sensitivity_price_curve = None
        self.sensitivity_price_curve = sensitivity_price_curve
        self._market_product = None
        self.market_product = market_product
        self._reserve_req_curve = None
        self.reserve_req_curve = reserve_req_curve

        super(ReserveReq, self).__init__(**kw_args)
    # >>> reserve_req


class RegisteredGenerator(RegisteredResource):
    # This is the minimum operating MW limit the dispatcher can enter for this unit. 
    minimum_operating_mw = ''

    # This is the maximum operating MW limit the dispatcher can enter for this unit 
    maximum_operating_mw = ''

    # High limit for secondary (AGC) control 
    high_control_limit = ''

    # Low limit for secondary (AGC) control 
    low_control_limit = ''

    # Maximum allowable spinning reserve. Spinning reserve will never be considered greater than this value regardless of the current operating point. 
    maximum_allowable_spinning_reserve = ''

    start_up_cost_curves = []
    
    def add_start_up_cost_curves(self, *start_up_cost_curves):
        for obj in start_up_cost_curves:
	        self._start_up_cost_curves.append(obj)
        
    def remove_start_up_cost_curves(self, *start_up_cost_curves):
        for obj in start_up_cost_curves:
	        self._start_up_cost_curves.remove(obj)

    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x._registered_generator = None
        for y in value:
            y._registered_generator = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._registered_generator = self
            self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._registered_generator = None
            self._generating_bids.remove(obj)

    def get_unit_initial_conditions(self):
        """ 
        """
        return self._unit_initial_conditions

    def set_unit_initial_conditions(self, value):
        for x in self._unit_initial_conditions:
            x._generating_unit = None
        for y in value:
            y._generating_unit = self
        self._unit_initial_conditions = value
            
    unit_initial_conditions = property(get_unit_initial_conditions, set_unit_initial_conditions)
    
    def add_unit_initial_conditions(self, *unit_initial_conditions):
        for obj in unit_initial_conditions:
            obj._generating_unit = self
            self._unit_initial_conditions.append(obj)
        
    def remove_unit_initial_conditions(self, *unit_initial_conditions):
        for obj in unit_initial_conditions:
            obj._generating_unit = None
            self._unit_initial_conditions.remove(obj)

    ramp_rate_curves = []
    
    def add_ramp_rate_curves(self, *ramp_rate_curves):
        for obj in ramp_rate_curves:
	        self._ramp_rate_curves.append(obj)
        
    def remove_ramp_rate_curves(self, *ramp_rate_curves):
        for obj in ramp_rate_curves:
	        self._ramp_rate_curves.remove(obj)

    def get_generating_unit(self):
        """ 
        """
        return self._generating_unit

    def set_generating_unit(self, value):
        if self._generating_unit is not None:
            self._generating_unit._registered_generator = None
            
        self._generating_unit = value
        if self._generating_unit is not None:
            self._generating_unit._registered_generator = self
            
    generating_unit = property(get_generating_unit, set_generating_unit)

    # <<< registered_generator
    # @generated
    def __init__(self, minimum_operating_mw='', maximum_operating_mw='', high_control_limit='', low_control_limit='', maximum_allowable_spinning_reserve='', start_up_cost_curves=[], generating_bids=[], unit_initial_conditions=[], ramp_rate_curves=[], generating_unit=None, **kw_args):
        """ Initialises a new 'RegisteredGenerator' instance.
        """
        self.minimum_operating_mw = minimum_operating_mw
        self.maximum_operating_mw = maximum_operating_mw
        self.high_control_limit = high_control_limit
        self.low_control_limit = low_control_limit
        self.maximum_allowable_spinning_reserve = maximum_allowable_spinning_reserve
        self._start_up_cost_curves = []
        self.start_up_cost_curves = start_up_cost_curves
        self._generating_bids = []
        self.generating_bids = generating_bids
        self._unit_initial_conditions = []
        self.unit_initial_conditions = unit_initial_conditions
        self._ramp_rate_curves = []
        self.ramp_rate_curves = ramp_rate_curves
        self._generating_unit = None
        self.generating_unit = generating_unit

        super(RegisteredGenerator, self).__init__(**kw_args)
    # >>> registered_generator


class SecurityConstraintSum(MarketFactors):
    """ Typically provided by RTO systems, constraints identified in both base case and critical contingency cases have to be transferred. A constraint has N (>=1) constraint terms. A term is represented by an instance of TerminalConstraintTerm.  The constraint expression is: minValue <= c1*x1 + c2*x2 + .... cn*xn + k <= maxValue where: - cn is ConstraintTerm.factor  - xn is the flow at the terminal Flow into the associated equipment is positive for the purpose of ConnectivityNode NodeConstraintTerm  k is SecurityConstraintsLinear.resourceMW The units of k are assumed to be same as the units of the flows, xn.  The constants, cn, are dimensionless. With these conventions, cn and k are all positive for a typical constraint such as 'weighted sum of generation must be less than limit'. Furthermore, cn are all 1.0 for a case such as 'interface flow must be less than limit', assuming the terminals are chosen on the importing side of the interface.
    """
    def get_constraint_terms(self):
        """ 
        """
        return self._constraint_terms

    def set_constraint_terms(self, value):
        for x in self._constraint_terms:
            x._security_constraint_sum = None
        for y in value:
            y._security_constraint_sum = self
        self._constraint_terms = value
            
    constraint_terms = property(get_constraint_terms, set_constraint_terms)
    
    def add_constraint_terms(self, *constraint_terms):
        for obj in constraint_terms:
            obj._security_constraint_sum = self
            self._constraint_terms.append(obj)
        
    def remove_constraint_terms(self, *constraint_terms):
        for obj in constraint_terms:
            obj._security_constraint_sum = None
            self._constraint_terms.remove(obj)

    def get_rto(self):
        """ 
        """
        return self._rto

    def set_rto(self, value):
        if self._rto is not None:
            filtered = [x for x in self.rto.security_constraints_linear if x != self]
            self._rto._security_constraints_linear = filtered
            
        self._rto = value
        if self._rto is not None:
            self._rto._security_constraints_linear.append(self)

    rto = property(get_rto, set_rto)

    def get_contingency_constraint_limits(self):
        """ 
        """
        return self._contingency_constraint_limits

    def set_contingency_constraint_limits(self, value):
        for x in self._contingency_constraint_limits:
            x._security_constraint_sum = None
        for y in value:
            y._security_constraint_sum = self
        self._contingency_constraint_limits = value
            
    contingency_constraint_limits = property(get_contingency_constraint_limits, set_contingency_constraint_limits)
    
    def add_contingency_constraint_limits(self, *contingency_constraint_limits):
        for obj in contingency_constraint_limits:
            obj._security_constraint_sum = self
            self._contingency_constraint_limits.append(obj)
        
    def remove_contingency_constraint_limits(self, *contingency_constraint_limits):
        for obj in contingency_constraint_limits:
            obj._security_constraint_sum = None
            self._contingency_constraint_limits.remove(obj)

    def get_default_constraint_limit(self):
        """ 
        """
        return self._default_constraint_limit

    def set_default_constraint_limit(self, value):
        if self._default_constraint_limit is not None:
            self._default_constraint_limit._security_constraint_sum = None
            
        self._default_constraint_limit = value
        if self._default_constraint_limit is not None:
            self._default_constraint_limit._security_constraint_sum = self
            
    default_constraint_limit = property(get_default_constraint_limit, set_default_constraint_limit)

    def get_base_case_constraint_limit(self):
        """ 
        """
        return self._base_case_constraint_limit

    def set_base_case_constraint_limit(self, value):
        if self._base_case_constraint_limit is not None:
            self._base_case_constraint_limit._security_constraint_sum = None
            
        self._base_case_constraint_limit = value
        if self._base_case_constraint_limit is not None:
            self._base_case_constraint_limit._security_constraint_sum = self
            
    base_case_constraint_limit = property(get_base_case_constraint_limit, set_base_case_constraint_limit)

    # <<< security_constraint_sum
    # @generated
    def __init__(self, constraint_terms=[], rto=None, contingency_constraint_limits=[], default_constraint_limit=None, base_case_constraint_limit=None, **kw_args):
        """ Initialises a new 'SecurityConstraintSum' instance.
        """
        self._constraint_terms = []
        self.constraint_terms = constraint_terms
        self._rto = None
        self.rto = rto
        self._contingency_constraint_limits = []
        self.contingency_constraint_limits = contingency_constraint_limits
        self._default_constraint_limit = None
        self.default_constraint_limit = default_constraint_limit
        self._base_case_constraint_limit = None
        self.base_case_constraint_limit = base_case_constraint_limit

        super(SecurityConstraintSum, self).__init__(**kw_args)
    # >>> security_constraint_sum


class RegisteredLoad(RegisteredResource):
    def get_load_area(self):
        """ 
        """
        return self._load_area

    def set_load_area(self, value):
        if self._load_area is not None:
            filtered = [x for x in self.load_area.registered_loads if x != self]
            self._load_area._registered_loads = filtered
            
        self._load_area = value
        if self._load_area is not None:
            self._load_area._registered_loads.append(self)

    load_area = property(get_load_area, set_load_area)

    def get_load_bids(self):
        """ 
        """
        return self._load_bids

    def set_load_bids(self, value):
        for x in self._load_bids:
            x._registered_load = None
        for y in value:
            y._registered_load = self
        self._load_bids = value
            
    load_bids = property(get_load_bids, set_load_bids)
    
    def add_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._registered_load = self
            self._load_bids.append(obj)
        
    def remove_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._registered_load = None
            self._load_bids.remove(obj)

    # <<< registered_load
    # @generated
    def __init__(self, load_area=None, load_bids=[], **kw_args):
        """ Initialises a new 'RegisteredLoad' instance.
        """
        self._load_area = None
        self.load_area = load_area
        self._load_bids = []
        self.load_bids = load_bids

        super(RegisteredLoad, self).__init__(**kw_args)
    # >>> registered_load


class ResourceBid(Bid):
    """ Energy bid for generation, load, or virtual type for the whole of the market-trading period (i.e., one day in day ahead market or one hour in the real time market)
    """
    # Maximum amount of energy per day which can be produced during the trading period in MWh 
    energy_max_day = ''

    # Energy product (commodity) type:  'En' - Energy  'Ru' - Regulation Up  'Rd' - Regulation Dn  'Sr' - Spinning Reserve  'Nr' - Non-Spinning Reserve  'Or' - Operating Reserve 
    commodity_type = ''

    # Maximum number of startups per week. 
    start_ups_max_week = 0

    # Maximum number of shutdowns per week. 
    shut_downs_max_week = 0

    # Maximum number of shutdowns per day. 
    shut_downs_max_day = 0

    # Maximum number of startups per day. 
    start_ups_max_day = 0

    # Minimum amount of energy per day which has to be produced during the trading period in MWh 
    energy_min_day = ''

    # True if bid is virtual.  Bid is assumed to be non-virtual if attribute is absent 
    virtual = False

    # <<< resource_bid
    # @generated
    def __init__(self, energy_max_day='', commodity_type='', start_ups_max_week=0, shut_downs_max_week=0, shut_downs_max_day=0, start_ups_max_day=0, energy_min_day='', virtual=False, **kw_args):
        """ Initialises a new 'ResourceBid' instance.
        """
        self.energy_max_day = energy_max_day
        self.commodity_type = commodity_type
        self.start_ups_max_week = start_ups_max_week
        self.shut_downs_max_week = shut_downs_max_week
        self.shut_downs_max_day = shut_downs_max_day
        self.start_ups_max_day = start_ups_max_day
        self.energy_min_day = energy_min_day
        self.virtual = virtual

        super(ResourceBid, self).__init__(**kw_args)
    # >>> resource_bid


class TerminalConstraintTerm(ConstraintTerm):
    """ A constraint term associated with a specific terminal on a physical piece of equipment.
    """
    def get_terminal(self):
        """ 
        """
        return self._terminal

    def set_terminal(self, value):
        if self._terminal is not None:
            filtered = [x for x in self.terminal.terminal_constraints if x != self]
            self._terminal._terminal_constraints = filtered
            
        self._terminal = value
        if self._terminal is not None:
            self._terminal._terminal_constraints.append(self)

    terminal = property(get_terminal, set_terminal)

    # <<< terminal_constraint_term
    # @generated
    def __init__(self, terminal=None, **kw_args):
        """ Initialises a new 'TerminalConstraintTerm' instance.
        """
        self._terminal = None
        self.terminal = terminal

        super(TerminalConstraintTerm, self).__init__(**kw_args)
    # >>> terminal_constraint_term


class PnodeClearing(MarketFactors):
    """ Pricing node clearing results posted for a given settlement period.
    """
    # Cost component of Locational Marginal Pricing (LMP) in monetary units per MW. 
    cost_lmp = ''

    # Loss component of Location Marginal Price (LMP) in monetary units per MW. 
    loss_lmp = ''

    # Congestion component of Location Marginal Price (LMP) in monetary units per MW. 
    congest_lmp = ''

    def get_pnode(self):
        """ 
        """
        return self._pnode

    def set_pnode(self, value):
        if self._pnode is not None:
            self._pnode._pnode_clearing = None
            
        self._pnode = value
        if self._pnode is not None:
            self._pnode._pnode_clearing = self
            
    pnode = property(get_pnode, set_pnode)

    # <<< pnode_clearing
    # @generated
    def __init__(self, cost_lmp='', loss_lmp='', congest_lmp='', pnode=None, **kw_args):
        """ Initialises a new 'PnodeClearing' instance.
        """
        self.cost_lmp = cost_lmp
        self.loss_lmp = loss_lmp
        self.congest_lmp = congest_lmp
        self._pnode = None
        self.pnode = pnode

        super(PnodeClearing, self).__init__(**kw_args)
    # >>> pnode_clearing


class TransactionBid(Bid):
    """ Bilateral or scheduled transactions for energy and ancillary services considered by market clearing process
    """
 
    energy_trans_id = ''

    def get_delivery_pnode(self):
        """ 
        """
        return self._delivery_pnode

    def set_delivery_pnode(self, value):
        if self._delivery_pnode is not None:
            filtered = [x for x in self.delivery_pnode.delivery_transaction_bids if x != self]
            self._delivery_pnode._delivery_transaction_bids = filtered
            
        self._delivery_pnode = value
        if self._delivery_pnode is not None:
            self._delivery_pnode._delivery_transaction_bids.append(self)

    delivery_pnode = property(get_delivery_pnode, set_delivery_pnode)

    def get_energy_trans_id(self):
        """ 
        """
        return self._energy_trans_id

    def set_energy_trans_id(self, value):
        if self._energy_trans_id is not None:
            filtered = [x for x in self.energy_trans_id.energy_trans_id if x != self]
            self._energy_trans_id._energy_trans_id = filtered
            
        self._energy_trans_id = value
        if self._energy_trans_id is not None:
            self._energy_trans_id._energy_trans_id.append(self)

    energy_trans_id = property(get_energy_trans_id, set_energy_trans_id)

    def get_energy_profiles(self):
        """ 
        """
        return self._energy_profiles

    def set_energy_profiles(self, value):
        for x in self._energy_profiles:
            x._transaction_bid = None
        for y in value:
            y._transaction_bid = self
        self._energy_profiles = value
            
    energy_profiles = property(get_energy_profiles, set_energy_profiles)
    
    def add_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._transaction_bid = self
            self._energy_profiles.append(obj)
        
    def remove_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._transaction_bid = None
            self._energy_profiles.remove(obj)

    def get_receipt_pnode(self):
        """ 
        """
        return self._receipt_pnode

    def set_receipt_pnode(self, value):
        if self._receipt_pnode is not None:
            filtered = [x for x in self.receipt_pnode.receipt_transaction_bids if x != self]
            self._receipt_pnode._receipt_transaction_bids = filtered
            
        self._receipt_pnode = value
        if self._receipt_pnode is not None:
            self._receipt_pnode._receipt_transaction_bids.append(self)

    receipt_pnode = property(get_receipt_pnode, set_receipt_pnode)

    # <<< transaction_bid
    # @generated
    def __init__(self, energy_trans_id='', delivery_pnode=None, energy_trans_id=None, energy_profiles=[], receipt_pnode=None, **kw_args):
        """ Initialises a new 'TransactionBid' instance.
        """
        self.energy_trans_id = energy_trans_id
        self._delivery_pnode = None
        self.delivery_pnode = delivery_pnode
        self._energy_trans_id = None
        self.energy_trans_id = energy_trans_id
        self._energy_profiles = []
        self.energy_profiles = energy_profiles
        self._receipt_pnode = None
        self.receipt_pnode = receipt_pnode

        super(TransactionBid, self).__init__(**kw_args)
    # >>> transaction_bid


class NodeConstraintTerm(ConstraintTerm):
    """ To be used only to constrain a quantity that cannot be associated with a terminal. For example, a registered generating unit that is not electrically connected to the network.
    """
    def get_connectivity_node(self):
        """ 
        """
        return self._connectivity_node

    def set_connectivity_node(self, value):
        if self._connectivity_node is not None:
            filtered = [x for x in self.connectivity_node.node_constraint_terms if x != self]
            self._connectivity_node._node_constraint_terms = filtered
            
        self._connectivity_node = value
        if self._connectivity_node is not None:
            self._connectivity_node._node_constraint_terms.append(self)

    connectivity_node = property(get_connectivity_node, set_connectivity_node)

    # <<< node_constraint_term
    # @generated
    def __init__(self, connectivity_node=None, **kw_args):
        """ Initialises a new 'NodeConstraintTerm' instance.
        """
        self._connectivity_node = None
        self.connectivity_node = connectivity_node

        super(NodeConstraintTerm, self).__init__(**kw_args)
    # >>> node_constraint_term


class SecurityConstraintsClearing(MarketFactors):
    """ Binding security constrained clearing results posted for a given settlement period.
    """
    # Optimal MW flow 
    mw_flow = ''

    # Security constraint shadow price. 
    shadow_price = ''

    # Binding MW limit. 
    mw_limit = ''

    # <<< security_constraints_clearing
    # @generated
    def __init__(self, mw_flow='', shadow_price='', mw_limit='', **kw_args):
        """ Initialises a new 'SecurityConstraintsClearing' instance.
        """
        self.mw_flow = mw_flow
        self.shadow_price = shadow_price
        self.mw_limit = mw_limit

        super(SecurityConstraintsClearing, self).__init__(**kw_args)
    # >>> security_constraints_clearing


class MarketCaseClearing(MarketFactors):
    """ Market case clearing results are posted for a given settlement period.
    """
    # Settlement period:  'DA - Bid-in'  'DA - Reliability'  'DA - Amp1'  'DA - Amp2'  'RT - Ex-Ante'  'RT - Ex-Post'  'RT - Amp1'  'RT - Amp2' 
    case_type = ''

    # Bid clearing results posted time and date. 
    posted_date = ''

    # Last time and date clearing results were manually modified. 
    modified_date = ''

    def get_ancillary_service_clearing(self):
        """ 
        """
        return self._ancillary_service_clearing

    def set_ancillary_service_clearing(self, value):
        for x in self._ancillary_service_clearing:
            x._market_case_clearing = None
        for y in value:
            y._market_case_clearing = self
        self._ancillary_service_clearing = value
            
    ancillary_service_clearing = property(get_ancillary_service_clearing, set_ancillary_service_clearing)
    
    def add_ancillary_service_clearing(self, *ancillary_service_clearing):
        for obj in ancillary_service_clearing:
            obj._market_case_clearing = self
            self._ancillary_service_clearing.append(obj)
        
    def remove_ancillary_service_clearing(self, *ancillary_service_clearing):
        for obj in ancillary_service_clearing:
            obj._market_case_clearing = None
            self._ancillary_service_clearing.remove(obj)

    # <<< market_case_clearing
    # @generated
    def __init__(self, case_type='', posted_date='', modified_date='', ancillary_service_clearing=[], **kw_args):
        """ Initialises a new 'MarketCaseClearing' instance.
        """
        self.case_type = case_type
        self.posted_date = posted_date
        self.modified_date = modified_date
        self._ancillary_service_clearing = []
        self.ancillary_service_clearing = ancillary_service_clearing

        super(MarketCaseClearing, self).__init__(**kw_args)
    # >>> market_case_clearing


class LoadBid(ResourceBid):
    # The fixed cost associated with committing a load reduction. 
    shutdown_cost = ''

    # Time period that is required from an order to reduce a load to the time that it takes to get to the minimum load reduction. 
    req_notice_time = ''

    # Shortest period load reduction must be maintained before load can be restored to normal levels. 
    min_load_reduction_interval = ''

    # Shortest time that load must be left at normal levels before a new load reduction. 
    min_time_bet_load_red = ''

    # Cost in $ at the minimum reduced load 
    min_load_reduction_cost = ''

    # Minimum MW for a load reduction (e.g., MW rating of a discrete pump. 
    min_load_reduction = ''

    # Minimum MW load below which it may not be reduced. 
    min_load = ''

    def get_registered_load(self):
        """ 
        """
        return self._registered_load

    def set_registered_load(self, value):
        if self._registered_load is not None:
            filtered = [x for x in self.registered_load.load_bids if x != self]
            self._registered_load._load_bids = filtered
            
        self._registered_load = value
        if self._registered_load is not None:
            self._registered_load._load_bids.append(self)

    registered_load = property(get_registered_load, set_registered_load)

    def get_load_reduction_price_curve(self):
        """ 
        """
        return self._load_reduction_price_curve

    def set_load_reduction_price_curve(self, value):
        if self._load_reduction_price_curve is not None:
            filtered = [x for x in self.load_reduction_price_curve.load_bids if x != self]
            self._load_reduction_price_curve._load_bids = filtered
            
        self._load_reduction_price_curve = value
        if self._load_reduction_price_curve is not None:
            self._load_reduction_price_curve._load_bids.append(self)

    load_reduction_price_curve = property(get_load_reduction_price_curve, set_load_reduction_price_curve)

    # <<< load_bid
    # @generated
    def __init__(self, shutdown_cost='', req_notice_time='', min_load_reduction_interval='', min_time_bet_load_red='', min_load_reduction_cost='', min_load_reduction='', min_load='', registered_load=None, load_reduction_price_curve=None, **kw_args):
        """ Initialises a new 'LoadBid' instance.
        """
        self.shutdown_cost = shutdown_cost
        self.req_notice_time = req_notice_time
        self.min_load_reduction_interval = min_load_reduction_interval
        self.min_time_bet_load_red = min_time_bet_load_red
        self.min_load_reduction_cost = min_load_reduction_cost
        self.min_load_reduction = min_load_reduction
        self.min_load = min_load
        self._registered_load = None
        self.registered_load = registered_load
        self._load_reduction_price_curve = None
        self.load_reduction_price_curve = load_reduction_price_curve

        super(LoadBid, self).__init__(**kw_args)
    # >>> load_bid


class GeneratingBid(ResourceBid):
    # Low economic MW limit that must be greater than or equal to the minimum operating MW limit 
    minimum_economic_mw = ''

    # Bid operating mode ('C' - cycling, 'F' - fixed, 'M' - must run, 'U' - unavailable) 
    operating_mode = ''

    # Minimum time interval between unit shutdown and startup 
    minimum_down_time = ''

    # Maximum high economic MW limit, that should not exceed the maximum operating MW limit 
    maximum_economic_mw = ''

    # Power rating available for unit under emergency conditions greater than or equal to maximum economic limit. 
    max_emergency_mw = ''

    # Maximum up time. 
    up_time_max = ''

    # Time it takes to get the unit on-line, from the time that the prime mover mechanical power is applied 
    startup_time = ''

    # Maximum down time. 
    down_time_max = ''

    # Resource fixed no load cost. 
    no_load_cost = ''

    # Time required for crew notification prior to start up of the unit. 
    notification_time = ''

    # Resource startup type:  1 - Fixed startup time and fixed startup cost  2 - Startup time as a function of down time and fixed startup cost  3 - Startup cost as a function of down time 
    start_up_type = 0

    # Minimum up time. 
    up_time_min = ''

    # Minimum power rating for unit under emergency conditions, which is less than or equal to the economic minimum. 
    min_emergency_mw = ''

    def get_bid_set(self):
        """ 
        """
        return self._bid_set

    def set_bid_set(self, value):
        if self._bid_set is not None:
            filtered = [x for x in self.bid_set.generating_bids if x != self]
            self._bid_set._generating_bids = filtered
            
        self._bid_set = value
        if self._bid_set is not None:
            self._bid_set._generating_bids.append(self)

    bid_set = property(get_bid_set, set_bid_set)

    def get_notification_time_curve(self):
        """ 
        """
        return self._notification_time_curve

    def set_notification_time_curve(self, value):
        if self._notification_time_curve is not None:
            filtered = [x for x in self.notification_time_curve.generating_bids if x != self]
            self._notification_time_curve._generating_bids = filtered
            
        self._notification_time_curve = value
        if self._notification_time_curve is not None:
            self._notification_time_curve._generating_bids.append(self)

    notification_time_curve = property(get_notification_time_curve, set_notification_time_curve)

    def get_start_up_cost_curve(self):
        """ 
        """
        return self._start_up_cost_curve

    def set_start_up_cost_curve(self, value):
        if self._start_up_cost_curve is not None:
            filtered = [x for x in self.start_up_cost_curve.generating_bids if x != self]
            self._start_up_cost_curve._generating_bids = filtered
            
        self._start_up_cost_curve = value
        if self._start_up_cost_curve is not None:
            self._start_up_cost_curve._generating_bids.append(self)

    start_up_cost_curve = property(get_start_up_cost_curve, set_start_up_cost_curve)

    def get_start_up_time_curve(self):
        """ 
        """
        return self._start_up_time_curve

    def set_start_up_time_curve(self, value):
        if self._start_up_time_curve is not None:
            filtered = [x for x in self.start_up_time_curve.generating_bids if x != self]
            self._start_up_time_curve._generating_bids = filtered
            
        self._start_up_time_curve = value
        if self._start_up_time_curve is not None:
            self._start_up_time_curve._generating_bids.append(self)

    start_up_time_curve = property(get_start_up_time_curve, set_start_up_time_curve)

    def get_registered_generator(self):
        """ 
        """
        return self._registered_generator

    def set_registered_generator(self, value):
        if self._registered_generator is not None:
            filtered = [x for x in self.registered_generator.generating_bids if x != self]
            self._registered_generator._generating_bids = filtered
            
        self._registered_generator = value
        if self._registered_generator is not None:
            self._registered_generator._generating_bids.append(self)

    registered_generator = property(get_registered_generator, set_registered_generator)

    # <<< generating_bid
    # @generated
    def __init__(self, minimum_economic_mw='', operating_mode='', minimum_down_time='', maximum_economic_mw='', max_emergency_mw='', up_time_max='', startup_time='', down_time_max='', no_load_cost='', notification_time='', start_up_type=0, up_time_min='', min_emergency_mw='', bid_set=None, notification_time_curve=None, start_up_cost_curve=None, start_up_time_curve=None, registered_generator=None, **kw_args):
        """ Initialises a new 'GeneratingBid' instance.
        """
        self.minimum_economic_mw = minimum_economic_mw
        self.operating_mode = operating_mode
        self.minimum_down_time = minimum_down_time
        self.maximum_economic_mw = maximum_economic_mw
        self.max_emergency_mw = max_emergency_mw
        self.up_time_max = up_time_max
        self.startup_time = startup_time
        self.down_time_max = down_time_max
        self.no_load_cost = no_load_cost
        self.notification_time = notification_time
        self.start_up_type = start_up_type
        self.up_time_min = up_time_min
        self.min_emergency_mw = min_emergency_mw
        self._bid_set = None
        self.bid_set = bid_set
        self._notification_time_curve = None
        self.notification_time_curve = notification_time_curve
        self._start_up_cost_curve = None
        self.start_up_cost_curve = start_up_cost_curve
        self._start_up_time_curve = None
        self.start_up_time_curve = start_up_time_curve
        self._registered_generator = None
        self.registered_generator = registered_generator

        super(GeneratingBid, self).__init__(**kw_args)
    # >>> generating_bid


# <<< market_operations
# @generated
# >>> market_operations

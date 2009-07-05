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
    # <<< registered_resource
    # @generated
    def __init__(self, rto_id='', resource_groups=None, organisation=None, pnode=None, meters=None, market_products=None, markets=None, **kw_args):
        """ Initialises a new 'RegisteredResource' instance.
        """
        # Unique name obtained via RTO registration 
        self.rto_id = rto_id
        
        self._resource_groups = []
        if resource_groups is None:
            self.resource_groups = []
        else:
            self.resource_groups = resource_groups
        self._organisation = None
        self.organisation = organisation
        self._pnode = None
        self.pnode = pnode
        self._meters = []
        if meters is None:
            self.meters = []
        else:
            self.meters = meters
        self._market_products = []
        if market_products is None:
            self.market_products = []
        else:
            self.market_products = market_products
        self._markets = []
        if markets is None:
            self.markets = []
        else:
            self.markets = markets

        super(RegisteredResource, self).__init__(**kw_args)
    # >>> registered_resource
        
    # <<< resource_groups
    # @generated
    def get_resource_groups(self):
        """ 
        """
        return self._resource_groups

    def set_resource_groups(self, value):
        for p in self._resource_groups:
            filtered = [q for q in p.registered_resources if q != self]
            self._resource_groups._registered_resources = filtered
        for r in value:
            if self not in r._registered_resources:
                r._registered_resources.append(self)
        self._resource_groups = value
            
    resource_groups = property(get_resource_groups, set_resource_groups)
    
    def add_resource_groups(self, *resource_groups):
        for obj in resource_groups:
            if self not in obj._registered_resources:
                obj._registered_resources.append(self)
            self._resource_groups.append(obj)
        
    def remove_resource_groups(self, *resource_groups):
        for obj in resource_groups:
            if self in obj._registered_resources:
                obj._registered_resources.remove(self)
            self._resource_groups.remove(obj)
    # >>> resource_groups

    # <<< organisation
    # @generated
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
            if self not in self._organisation._registered_resources:
                self._organisation._registered_resources.append(self)

    organisation = property(get_organisation, set_organisation)
    # >>> organisation

    # <<< pnode
    # @generated
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
            if self not in self._pnode._registered_resources:
                self._pnode._registered_resources.append(self)

    pnode = property(get_pnode, set_pnode)
    # >>> pnode

    # <<< meters
    # @generated
    def get_meters(self):
        """ 
        """
        return self._meters

    def set_meters(self, value):
        for x in self._meters:
            x.registered_resource = None
        for y in value:
            y.registered_resource = self
        self._meters = value
            
    meters = property(get_meters, set_meters)
    
    def add_meters(self, *meters):
        for obj in meters:
            obj._registered_resource = self
            if obj not in self._meters:
                self._meters.append(obj)
        
    def remove_meters(self, *meters):
        for obj in meters:
            obj._registered_resource = None
            self._meters.remove(obj)
    # >>> meters

    # <<< market_products
    # @generated
    def get_market_products(self):
        """ A registered resource is eligible to bid market products
        """
        return self._market_products

    def set_market_products(self, value):
        for p in self._market_products:
            filtered = [q for q in p.registered_resources if q != self]
            self._market_products._registered_resources = filtered
        for r in value:
            if self not in r._registered_resources:
                r._registered_resources.append(self)
        self._market_products = value
            
    market_products = property(get_market_products, set_market_products)
    
    def add_market_products(self, *market_products):
        for obj in market_products:
            if self not in obj._registered_resources:
                obj._registered_resources.append(self)
            self._market_products.append(obj)
        
    def remove_market_products(self, *market_products):
        for obj in market_products:
            if self in obj._registered_resources:
                obj._registered_resources.remove(self)
            self._market_products.remove(obj)
    # >>> market_products

    # <<< markets
    # @generated
    def get_markets(self):
        """ 
        """
        return self._markets

    def set_markets(self, value):
        for p in self._markets:
            filtered = [q for q in p.registered_resources if q != self]
            self._markets._registered_resources = filtered
        for r in value:
            if self not in r._registered_resources:
                r._registered_resources.append(self)
        self._markets = value
            
    markets = property(get_markets, set_markets)
    
    def add_markets(self, *markets):
        for obj in markets:
            if self not in obj._registered_resources:
                obj._registered_resources.append(self)
            self._markets.append(obj)
        
    def remove_markets(self, *markets):
        for obj in markets:
            if self in obj._registered_resources:
                obj._registered_resources.remove(self)
            self._markets.remove(obj)
    # >>> markets



class RTO(ErpOrganisation):
    """ Regional transmission operator.
    """
    # <<< rto
    # @generated
    def __init__(self, security_constraints_linear=None, resource_group_reqs=None, security_constraints=None, markets=None, pnodes=None, **kw_args):
        """ Initialises a new 'RTO' instance.
        """
        
        self._security_constraints_linear = []
        if security_constraints_linear is None:
            self.security_constraints_linear = []
        else:
            self.security_constraints_linear = security_constraints_linear
        self._resource_group_reqs = []
        if resource_group_reqs is None:
            self.resource_group_reqs = []
        else:
            self.resource_group_reqs = resource_group_reqs
        self._security_constraints = []
        if security_constraints is None:
            self.security_constraints = []
        else:
            self.security_constraints = security_constraints
        self._markets = []
        if markets is None:
            self.markets = []
        else:
            self.markets = markets
        self._pnodes = []
        if pnodes is None:
            self.pnodes = []
        else:
            self.pnodes = pnodes

        super(RTO, self).__init__(**kw_args)
    # >>> rto
        
    # <<< security_constraints_linear
    # @generated
    def get_security_constraints_linear(self):
        """ 
        """
        return self._security_constraints_linear

    def set_security_constraints_linear(self, value):
        for x in self._security_constraints_linear:
            x.rto = None
        for y in value:
            y.rto = self
        self._security_constraints_linear = value
            
    security_constraints_linear = property(get_security_constraints_linear, set_security_constraints_linear)
    
    def add_security_constraints_linear(self, *security_constraints_linear):
        for obj in security_constraints_linear:
            obj._rto = self
            if obj not in self._security_constraints_linear:
                self._security_constraints_linear.append(obj)
        
    def remove_security_constraints_linear(self, *security_constraints_linear):
        for obj in security_constraints_linear:
            obj._rto = None
            self._security_constraints_linear.remove(obj)
    # >>> security_constraints_linear

    # <<< resource_group_reqs
    # @generated
    def get_resource_group_reqs(self):
        """ 
        """
        return self._resource_group_reqs

    def set_resource_group_reqs(self, value):
        for p in self._resource_group_reqs:
            filtered = [q for q in p.rtos if q != self]
            self._resource_group_reqs._rtos = filtered
        for r in value:
            if self not in r._rtos:
                r._rtos.append(self)
        self._resource_group_reqs = value
            
    resource_group_reqs = property(get_resource_group_reqs, set_resource_group_reqs)
    
    def add_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            if self not in obj._rtos:
                obj._rtos.append(self)
            self._resource_group_reqs.append(obj)
        
    def remove_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            if self in obj._rtos:
                obj._rtos.remove(self)
            self._resource_group_reqs.remove(obj)
    # >>> resource_group_reqs

    # <<< security_constraints
    # @generated
    def get_security_constraints(self):
        """ 
        """
        return self._security_constraints

    def set_security_constraints(self, value):
        for x in self._security_constraints:
            x.rto = None
        for y in value:
            y.rto = self
        self._security_constraints = value
            
    security_constraints = property(get_security_constraints, set_security_constraints)
    
    def add_security_constraints(self, *security_constraints):
        for obj in security_constraints:
            obj._rto = self
            if obj not in self._security_constraints:
                self._security_constraints.append(obj)
        
    def remove_security_constraints(self, *security_constraints):
        for obj in security_constraints:
            obj._rto = None
            self._security_constraints.remove(obj)
    # >>> security_constraints

    # <<< markets
    # @generated
    def get_markets(self):
        """ 
        """
        return self._markets

    def set_markets(self, value):
        for x in self._markets:
            x.rto = None
        for y in value:
            y.rto = self
        self._markets = value
            
    markets = property(get_markets, set_markets)
    
    def add_markets(self, *markets):
        for obj in markets:
            obj._rto = self
            if obj not in self._markets:
                self._markets.append(obj)
        
    def remove_markets(self, *markets):
        for obj in markets:
            obj._rto = None
            self._markets.remove(obj)
    # >>> markets

    # <<< pnodes
    # @generated
    def get_pnodes(self):
        """ 
        """
        return self._pnodes

    def set_pnodes(self, value):
        for x in self._pnodes:
            x.rto = None
        for y in value:
            y.rto = self
        self._pnodes = value
            
    pnodes = property(get_pnodes, set_pnodes)
    
    def add_pnodes(self, *pnodes):
        for obj in pnodes:
            obj._rto = self
            if obj not in self._pnodes:
                self._pnodes.append(obj)
        
    def remove_pnodes(self, *pnodes):
        for obj in pnodes:
            obj._rto = None
            self._pnodes.remove(obj)
    # >>> pnodes



class Meter(IdentifiedObject):
    """ This is generic logical meter object.
    """
    # <<< meter
    # @generated
    def __init__(self, registered_resource=None, **kw_args):
        """ Initialises a new 'Meter' instance.
        """
        
        self._registered_resource = None
        self.registered_resource = registered_resource

        super(Meter, self).__init__(**kw_args)
    # >>> meter
        
    # <<< registered_resource
    # @generated
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
            if self not in self._registered_resource._meters:
                self._registered_resource._meters.append(self)

    registered_resource = property(get_registered_resource, set_registered_resource)
    # >>> registered_resource



class ContingencyConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) assigned to a constraint for a specific contingency. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
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
        
    # <<< mwlimit_schedules
    # @generated
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
    # >>> mwlimit_schedules

    # <<< contingency
    # @generated
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
            if self not in self._contingency._contingency_constraint_limit:
                self._contingency._contingency_constraint_limit.append(self)

    contingency = property(get_contingency, set_contingency)
    # >>> contingency

    # <<< security_constraint_sum
    # @generated
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
            if self not in self._security_constraint_sum._contingency_constraint_limits:
                self._security_constraint_sum._contingency_constraint_limits.append(self)

    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)
    # >>> security_constraint_sum



class BidPriceCurve(Curve):
    """ Relationship between unit operating price in $/hour (Y-axis) and unit output in MW (X-axis).
    """
    # <<< bid_price_curve
    # @generated
    def __init__(self, product_bids=None, **kw_args):
        """ Initialises a new 'BidPriceCurve' instance.
        """
        
        self._product_bids = []
        if product_bids is None:
            self.product_bids = []
        else:
            self.product_bids = product_bids

        super(BidPriceCurve, self).__init__(**kw_args)
    # >>> bid_price_curve
        
    # <<< product_bids
    # @generated
    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x.bid_price_curve = None
        for y in value:
            y.bid_price_curve = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid_price_curve = self
            if obj not in self._product_bids:
                self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid_price_curve = None
            self._product_bids.remove(obj)
    # >>> product_bids



class FTR(Agreement):
    """ Financial Transmission Rights (FTR) regarding transmission capacity at a flowgate.
    """
    # <<< ftr
    # @generated
    def __init__(self, class='', ftr_type='', optimized='', action='', base_energy=0.0, flowgate=None, pnodes=None, energy_price_curve=None, **kw_args):
        """ Initialises a new 'FTR' instance.
        """
        # Peak, Off-peak, 24-hour 
        self.class = class
        # Type of rights being offered (product) allowed to be auctioned (option, obligation). 
        self.ftr_type = ftr_type
        # Fixed (covers re-configuration, grandfathering) or Optimized (up for sale/purchase 
        self.optimized = optimized
        # Buy, Sell 
        self.action = action
        # Quantity, typically MWs - Seller owns all rights being offered, MWs over time on same Point of Receipt, Point of Delivery, or Resource. 
        self.base_energy = base_energy
        
        self._flowgate = None
        self.flowgate = flowgate
        self._pnodes = []
        if pnodes is None:
            self.pnodes = []
        else:
            self.pnodes = pnodes
        self._energy_price_curve = None
        self.energy_price_curve = energy_price_curve

        super(FTR, self).__init__(**kw_args)
    # >>> ftr
        
    # <<< flowgate
    # @generated
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
            if self not in self._flowgate._ftrs:
                self._flowgate._ftrs.append(self)

    flowgate = property(get_flowgate, set_flowgate)
    # >>> flowgate

    # <<< pnodes
    # @generated
    def get_pnodes(self):
        """ 
        """
        return self._pnodes

    def set_pnodes(self, value):
        for p in self._pnodes:
            filtered = [q for q in p.ftrs if q != self]
            self._pnodes._ftrs = filtered
        for r in value:
            if self not in r._ftrs:
                r._ftrs.append(self)
        self._pnodes = value
            
    pnodes = property(get_pnodes, set_pnodes)
    
    def add_pnodes(self, *pnodes):
        for obj in pnodes:
            if self not in obj._ftrs:
                obj._ftrs.append(self)
            self._pnodes.append(obj)
        
    def remove_pnodes(self, *pnodes):
        for obj in pnodes:
            if self in obj._ftrs:
                obj._ftrs.remove(self)
            self._pnodes.remove(obj)
    # >>> pnodes

    # <<< energy_price_curve
    # @generated
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
            if self not in self._energy_price_curve._ftrs:
                self._energy_price_curve._ftrs.append(self)

    energy_price_curve = property(get_energy_price_curve, set_energy_price_curve)
    # >>> energy_price_curve



class ChargeProfileData(Element):
    # <<< charge_profile_data
    # @generated
    def __init__(self, time_stamp='', value=0.0, sequence=0, charge_profile=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfileData' instance.
        """
        # The date and time of an interval. 
        self.time_stamp = time_stamp
        # The value of an interval given a profile type (amount, price, or quantity), subject to the UOM. 
        self.value = value
        # The sequence number of the profile. 
        self.sequence = sequence
        
        self._charge_profile = None
        self.charge_profile = charge_profile
        self._bill_determinant = None
        self.bill_determinant = bill_determinant

        super(ChargeProfileData, self).__init__(**kw_args)
    # >>> charge_profile_data
        
    # <<< charge_profile
    # @generated
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
            if self not in self._charge_profile._charge_profile_data:
                self._charge_profile._charge_profile_data.append(self)

    charge_profile = property(get_charge_profile, set_charge_profile)
    # >>> charge_profile

    # <<< bill_determinant
    # @generated
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
            if self not in self._bill_determinant._charge_profile_data:
                self._bill_determinant._charge_profile_data.append(self)

    bill_determinant = property(get_bill_determinant, set_bill_determinant)
    # >>> bill_determinant



class TransmissionReliabilityMargin(IdentifiedObject):
    """ Transmission Reliability Margin (TRM) is defined as that amount of transmission transfer capability necessary to ensure that the interconnected transmission network is secure under a reasonable range of uncertainties in system conditions.
    """
    # <<< transmission_reliability_margin
    # @generated
    def __init__(self, trm_value=0.0, trm_type='', value_unit='', flowgate=None, **kw_args):
        """ Initialises a new 'TransmissionReliabilityMargin' instance.
        """
        # Value of the TRM 
        self.trm_value = trm_value
        # the type of TRM 
        self.trm_type = trm_type
        # unit of the TRM value. Could be MW or Percentage. 
        self.value_unit = value_unit
        
        self._flowgate = []
        if flowgate is None:
            self.flowgate = []
        else:
            self.flowgate = flowgate

        super(TransmissionReliabilityMargin, self).__init__(**kw_args)
    # >>> transmission_reliability_margin
        
    # <<< flowgate
    # @generated
    def get_flowgate(self):
        """ A fowgate may have 0 to 1 TRM
        """
        return self._flowgate

    def set_flowgate(self, value):
        for x in self._flowgate:
            x.transmission_reliability_margin = None
        for y in value:
            y.transmission_reliability_margin = self
        self._flowgate = value
            
    flowgate = property(get_flowgate, set_flowgate)
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._transmission_reliability_margin = self
            if obj not in self._flowgate:
                self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
            obj._transmission_reliability_margin = None
            self._flowgate.remove(obj)
    # >>> flowgate



class UnitInitialConditions(IdentifiedObject):
    """ Resource status at the end of a given clearing period.
    """
    # <<< unit_initial_conditions
    # @generated
    def __init__(self, cum_status_changes=0, resource_status=0, time_in_status=0.0, resource_mw=0.0, status_date='', generating_unit=None, **kw_args):
        """ Initialises a new 'UnitInitialConditions' instance.
        """
        # Cumulative number of status changes of the resource. 
        self.cum_status_changes = cum_status_changes
        # Resource status at the end of previous clearing period:  0 - off-line  1 - on-line production  2 - in shutdown process  3 - in startup process 
        self.resource_status = resource_status
        # Time in market trading intervals the resource is in the state as of the end of the previous clearing period. 
        self.time_in_status = time_in_status
        # Resource MW output at the end of previous clearing period. 
        self.resource_mw = resource_mw
        # Time and date for resourceStatus 
        self.status_date = status_date
        
        self._generating_unit = None
        self.generating_unit = generating_unit

        super(UnitInitialConditions, self).__init__(**kw_args)
    # >>> unit_initial_conditions
        
    # <<< generating_unit
    # @generated
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
            if self not in self._generating_unit._unit_initial_conditions:
                self._generating_unit._unit_initial_conditions.append(self)

    generating_unit = property(get_generating_unit, set_generating_unit)
    # >>> generating_unit



class Pnode(IdentifiedObject):
    """ A pricing node is directly associated with a connectivity node.  It is a pricing location for which market participants submit their bids, offers, buy/sell CRRs, and settle.
    """
    # <<< pnode
    # @generated
    def __init__(self, end_period='', is_public=False, type='', usage='', begin_period='', connectivity_node=None, rto=None, measurements=None, receipt_transaction_bids=None, pnode_clearing=None, ftrs=None, registered_resources=None, delivery_transaction_bids=None, **kw_args):
        """ Initialises a new 'Pnode' instance.
        """
        # End date-time of the period in which the price node definition is valid 
        self.end_period = end_period
        # True=Public; False=Private Public Pnodes: Prices are published for DA/RT and FTR Markets. Private Pnodes: Location is not usable by Market for Bidding/FTRs/Transactions 
        self.is_public = is_public
        # Price node type: Hub (H), Zone (Z), Control Area (C), ?, Bus (B) 
        self.type = type
        # Price node usage:  'Control Area' 'Regulation Region' 'Price Zone' 'Spin Region' 'Non-Spin Region' 'Price Hub' 
        self.usage = usage
        # Start date-time of the period in which the price node definition is valid. 
        self.begin_period = begin_period
        
        self._connectivity_node = None
        self.connectivity_node = connectivity_node
        self._rto = None
        self.rto = rto
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._receipt_transaction_bids = []
        if receipt_transaction_bids is None:
            self.receipt_transaction_bids = []
        else:
            self.receipt_transaction_bids = receipt_transaction_bids
        self._pnode_clearing = None
        self.pnode_clearing = pnode_clearing
        self._ftrs = []
        if ftrs is None:
            self.ftrs = []
        else:
            self.ftrs = ftrs
        self._registered_resources = []
        if registered_resources is None:
            self.registered_resources = []
        else:
            self.registered_resources = registered_resources
        self._delivery_transaction_bids = []
        if delivery_transaction_bids is None:
            self.delivery_transaction_bids = []
        else:
            self.delivery_transaction_bids = delivery_transaction_bids

        super(Pnode, self).__init__(**kw_args)
    # >>> pnode
        
    # <<< connectivity_node
    # @generated
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
    # >>> connectivity_node

    # <<< rto
    # @generated
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
            if self not in self._rto._pnodes:
                self._rto._pnodes.append(self)

    rto = property(get_rto, set_rto)
    # >>> rto

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ 
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x.pnode = None
        for y in value:
            y.pnode = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._pnode = self
            if obj not in self._measurements:
                self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._pnode = None
            self._measurements.remove(obj)
    # >>> measurements

    # <<< receipt_transaction_bids
    # @generated
    def get_receipt_transaction_bids(self):
        """ 
        """
        return self._receipt_transaction_bids

    def set_receipt_transaction_bids(self, value):
        for x in self._receipt_transaction_bids:
            x.receipt_pnode = None
        for y in value:
            y.receipt_pnode = self
        self._receipt_transaction_bids = value
            
    receipt_transaction_bids = property(get_receipt_transaction_bids, set_receipt_transaction_bids)
    
    def add_receipt_transaction_bids(self, *receipt_transaction_bids):
        for obj in receipt_transaction_bids:
            obj._receipt_pnode = self
            if obj not in self._receipt_transaction_bids:
                self._receipt_transaction_bids.append(obj)
        
    def remove_receipt_transaction_bids(self, *receipt_transaction_bids):
        for obj in receipt_transaction_bids:
            obj._receipt_pnode = None
            self._receipt_transaction_bids.remove(obj)
    # >>> receipt_transaction_bids

    # <<< pnode_clearing
    # @generated
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
    # >>> pnode_clearing

    # <<< ftrs
    # @generated
    def get_ftrs(self):
        """ 
        """
        return self._ftrs

    def set_ftrs(self, value):
        for p in self._ftrs:
            filtered = [q for q in p.pnodes if q != self]
            self._ftrs._pnodes = filtered
        for r in value:
            if self not in r._pnodes:
                r._pnodes.append(self)
        self._ftrs = value
            
    ftrs = property(get_ftrs, set_ftrs)
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
            if self not in obj._pnodes:
                obj._pnodes.append(self)
            self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
            if self in obj._pnodes:
                obj._pnodes.remove(self)
            self._ftrs.remove(obj)
    # >>> ftrs

    # <<< registered_resources
    # @generated
    def get_registered_resources(self):
        """ A registered resource injects power at one or more connectivity nodes related to a pnode
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for x in self._registered_resources:
            x.pnode = None
        for y in value:
            y.pnode = self
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._pnode = self
            if obj not in self._registered_resources:
                self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            obj._pnode = None
            self._registered_resources.remove(obj)
    # >>> registered_resources

    # <<< delivery_transaction_bids
    # @generated
    def get_delivery_transaction_bids(self):
        """ 
        """
        return self._delivery_transaction_bids

    def set_delivery_transaction_bids(self, value):
        for x in self._delivery_transaction_bids:
            x.delivery_pnode = None
        for y in value:
            y.delivery_pnode = self
        self._delivery_transaction_bids = value
            
    delivery_transaction_bids = property(get_delivery_transaction_bids, set_delivery_transaction_bids)
    
    def add_delivery_transaction_bids(self, *delivery_transaction_bids):
        for obj in delivery_transaction_bids:
            obj._delivery_pnode = self
            if obj not in self._delivery_transaction_bids:
                self._delivery_transaction_bids.append(obj)
        
    def remove_delivery_transaction_bids(self, *delivery_transaction_bids):
        for obj in delivery_transaction_bids:
            obj._delivery_pnode = None
            self._delivery_transaction_bids.remove(obj)
    # >>> delivery_transaction_bids



class CapacityBenefitMargin(Profile):
    """ Capacity Benefit Margin (CBM) is defined as that amount of transmission transfer capability reserved by load serving entities to ensure access to generation from interconnected systems to meet generation reliability requirements. Reservation fo CBM by a load serving entity allows that entity to reduce its installed generating capacity below that which may otherwise have been necessary without interconnections to meet its generation reliability requirements.  CBM is modeled as a profile with values in different time periods which are represented by the profile data.
    """
    # <<< capacity_benefit_margin
    # @generated
    def __init__(self, season=None, flowgate=None, **kw_args):
        """ Initialises a new 'CapacityBenefitMargin' instance.
        """
        
        self._season = None
        self.season = season
        self._flowgate = []
        if flowgate is None:
            self.flowgate = []
        else:
            self.flowgate = flowgate

        super(CapacityBenefitMargin, self).__init__(**kw_args)
    # >>> capacity_benefit_margin
        
    # <<< season
    # @generated
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
            if self not in self._season._capacity_benefit_margin:
                self._season._capacity_benefit_margin.append(self)

    season = property(get_season, set_season)
    # >>> season

    # <<< flowgate
    # @generated
    def get_flowgate(self):
        """ A flowgate may have 0 to n CBM profile Each season may be a CBM which contains a set of profile data
        """
        return self._flowgate

    def set_flowgate(self, value):
        for p in self._flowgate:
            filtered = [q for q in p.capacity_benefit_margin if q != self]
            self._flowgate._capacity_benefit_margin = filtered
        for r in value:
            if self not in r._capacity_benefit_margin:
                r._capacity_benefit_margin.append(self)
        self._flowgate = value
            
    flowgate = property(get_flowgate, set_flowgate)
    
    def add_flowgate(self, *flowgate):
        for obj in flowgate:
            if self not in obj._capacity_benefit_margin:
                obj._capacity_benefit_margin.append(self)
            self._flowgate.append(obj)
        
    def remove_flowgate(self, *flowgate):
        for obj in flowgate:
            if self in obj._capacity_benefit_margin:
                obj._capacity_benefit_margin.remove(self)
            self._flowgate.remove(obj)
    # >>> flowgate



class EnergyPriceCurve(Curve):
    """ Relationship between a price in $/hour (Y-axis) and a MW value (X-axis).
    """
    # <<< energy_price_curve
    # @generated
    def __init__(self, ftrs=None, energy_transactions=None, **kw_args):
        """ Initialises a new 'EnergyPriceCurve' instance.
        """
        
        self._ftrs = []
        if ftrs is None:
            self.ftrs = []
        else:
            self.ftrs = ftrs
        self._energy_transactions = []
        if energy_transactions is None:
            self.energy_transactions = []
        else:
            self.energy_transactions = energy_transactions

        super(EnergyPriceCurve, self).__init__(**kw_args)
    # >>> energy_price_curve
        
    # <<< ftrs
    # @generated
    def get_ftrs(self):
        """ 
        """
        return self._ftrs

    def set_ftrs(self, value):
        for x in self._ftrs:
            x.energy_price_curve = None
        for y in value:
            y.energy_price_curve = self
        self._ftrs = value
            
    ftrs = property(get_ftrs, set_ftrs)
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._energy_price_curve = self
            if obj not in self._ftrs:
                self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._energy_price_curve = None
            self._ftrs.remove(obj)
    # >>> ftrs

    # <<< energy_transactions
    # @generated
    def get_energy_transactions(self):
        """ 
        """
        return self._energy_transactions

    def set_energy_transactions(self, value):
        for p in self._energy_transactions:
            filtered = [q for q in p.energy_price_curves if q != self]
            self._energy_transactions._energy_price_curves = filtered
        for r in value:
            if self not in r._energy_price_curves:
                r._energy_price_curves.append(self)
        self._energy_transactions = value
            
    energy_transactions = property(get_energy_transactions, set_energy_transactions)
    
    def add_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
            if self not in obj._energy_price_curves:
                obj._energy_price_curves.append(self)
            self._energy_transactions.append(obj)
        
    def remove_energy_transactions(self, *energy_transactions):
        for obj in energy_transactions:
            if self in obj._energy_price_curves:
                obj._energy_price_curves.remove(self)
            self._energy_transactions.remove(obj)
    # >>> energy_transactions



class MarketProduct(IdentifiedObject):
    """ A product traded by an RTO (e.g., energy, 10 minute spinning reserve).  Ancillary service product examples include: Regulation Up Regulation Dn Spinning Reserve Non-Spinning Reserve Operating Reserve
    """
    # <<< market_product
    # @generated
    def __init__(self, reserve_reqs=None, product_bids=None, registered_resources=None, market=None, **kw_args):
        """ Initialises a new 'MarketProduct' instance.
        """
        
        self._reserve_reqs = []
        if reserve_reqs is None:
            self.reserve_reqs = []
        else:
            self.reserve_reqs = reserve_reqs
        self._product_bids = []
        if product_bids is None:
            self.product_bids = []
        else:
            self.product_bids = product_bids
        self._registered_resources = []
        if registered_resources is None:
            self.registered_resources = []
        else:
            self.registered_resources = registered_resources
        self._market = None
        self.market = market

        super(MarketProduct, self).__init__(**kw_args)
    # >>> market_product
        
    # <<< reserve_reqs
    # @generated
    def get_reserve_reqs(self):
        """ Market product associated with reserve requirement must be a reserve or regulation product.
        """
        return self._reserve_reqs

    def set_reserve_reqs(self, value):
        for x in self._reserve_reqs:
            x.market_product = None
        for y in value:
            y.market_product = self
        self._reserve_reqs = value
            
    reserve_reqs = property(get_reserve_reqs, set_reserve_reqs)
    
    def add_reserve_reqs(self, *reserve_reqs):
        for obj in reserve_reqs:
            obj._market_product = self
            if obj not in self._reserve_reqs:
                self._reserve_reqs.append(obj)
        
    def remove_reserve_reqs(self, *reserve_reqs):
        for obj in reserve_reqs:
            obj._market_product = None
            self._reserve_reqs.remove(obj)
    # >>> reserve_reqs

    # <<< product_bids
    # @generated
    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x.market_product = None
        for y in value:
            y.market_product = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._market_product = self
            if obj not in self._product_bids:
                self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._market_product = None
            self._product_bids.remove(obj)
    # >>> product_bids

    # <<< registered_resources
    # @generated
    def get_registered_resources(self):
        """ A registered resource is eligible to bid market products
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for p in self._registered_resources:
            filtered = [q for q in p.market_products if q != self]
            self._registered_resources._market_products = filtered
        for r in value:
            if self not in r._market_products:
                r._market_products.append(self)
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self not in obj._market_products:
                obj._market_products.append(self)
            self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self in obj._market_products:
                obj._market_products.remove(self)
            self._registered_resources.remove(obj)
    # >>> registered_resources

    # <<< market
    # @generated
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
            if self not in self._market._market_products:
                self._market._market_products.append(self)

    market = property(get_market, set_market)
    # >>> market



class BilateralTransaction(Element):
    """ Bilateral transaction
    """
    # <<< bilateral_transaction
    # @generated
    def __init__(self, total_tran_charge_max=0.0, scope='', market_type='', curtail_time_min=0, purchase_time_max=0, curtail_time_max=0, transaction_type='', purchase_time_min=0, **kw_args):
        """ Initialises a new 'BilateralTransaction' instance.
        """
        # Maximum total transmission (congestion) charges in monetary units 
        self.total_tran_charge_max = total_tran_charge_max
        # Transaction scope: 'Internal' 'External' 
        self.scope = scope
        # Market type (default=DA) DA - Day Ahead RT - Real Time HA - Hour Ahead 
        self.market_type = market_type
        # Minimum curtailment time in number of trading intervals 
        self.curtail_time_min = curtail_time_min
        # Maximum purchase time in number of trading intervals 
        self.purchase_time_max = purchase_time_max
        # Maximum curtailment time in number of trading intervals 
        self.curtail_time_max = curtail_time_max
        # Transaction type (default 1)  1 - Fixed  2 - Dispatchable continuous  3 - Dispatchable block-loading 
        self.transaction_type = transaction_type
        # Minimum purchase time in number of trading intervals 
        self.purchase_time_min = purchase_time_min
        

        super(BilateralTransaction, self).__init__(**kw_args)
    # >>> bilateral_transaction
        


class PassThroughBill(Document):
    """ Pass Through Bill is used for: 1)Two sided charge transactions with or without ISO involvement (hence the ?pass thru?) 2) Specific direct charges or payments that are calculated outside or provided directly to settlements 3) Specific charge bill determinants that are externally supplied and used in charge calculations
    """
    # <<< pass_through_bill
    # @generated
    def __init__(self, is_disputed=False, bill_end='', previous_start='', bill_run_type='', product_code='', transaction_date='', trade_date='', time_zone='', is_profiled=False, service_start='', tax_amount=0.0, effective_date='', paid_to='', price=0.0, sold_to='', transaction_type='', service_end='', provided_by='', previous_end='', bill_start='', amount=0.0, billed_to='', market_statement_line_item=None, charge_profiles=None, user_attributes=None, **kw_args):
        """ Initialises a new 'PassThroughBill' instance.
        """
        # Disputed transaction indicator 
        self.is_disputed = is_disputed
        # Bill period end date 
        self.bill_end = bill_end
        # The previous bill period start date 
        self.previous_start = previous_start
        # The settlement run type, for example: prelim, final, and rerun. 
        self.bill_run_type = bill_run_type
        # The product identifier for determining the charge type of the transaction. 
        self.product_code = product_code
        # The date the transaction occurs. 
        self.transaction_date = transaction_date
        # The trade date 
        self.trade_date = trade_date
        # The time zone code 
        self.time_zone = time_zone
        # A flag indicating whether there is a profile data associated with the PTB. 
        self.is_profiled = is_profiled
        # The start date of service provided, if periodic. 
        self.service_start = service_start
        # The tax on services taken. 
        self.tax_amount = tax_amount
        # The effective date of the transaction 
        self.effective_date = effective_date
        # The company to which the PTB transaction is paid. 
        self.paid_to = paid_to
        # The price of product/service. 
        self.price = price
        # The company to which the PTB transaction is sold. 
        self.sold_to = sold_to
        # The type of transaction. For example, charge customer, bill customer, matching AR/AP, or bill determinant 
        self.transaction_type = transaction_type
        # The end date of service provided, if periodic. 
        self.service_end = service_end
        # The company by which the PTB transaction service is provided. 
        self.provided_by = provided_by
        # The previous bill period end date 
        self.previous_end = previous_end
        # Bill period start date 
        self.bill_start = bill_start
        # The charge amount of the product/service. 
        self.amount = amount
        # The company to which the PTB transaction is billed. 
        self.billed_to = billed_to
        
        self._market_statement_line_item = None
        self.market_statement_line_item = market_statement_line_item
        self._charge_profiles = []
        if charge_profiles is None:
            self.charge_profiles = []
        else:
            self.charge_profiles = charge_profiles
        self._user_attributes = []
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes

        super(PassThroughBill, self).__init__(**kw_args)
    # >>> pass_through_bill
        
    # <<< market_statement_line_item
    # @generated
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
    # >>> market_statement_line_item

    # <<< charge_profiles
    # @generated
    def get_charge_profiles(self):
        """ 
        """
        return self._charge_profiles

    def set_charge_profiles(self, value):
        for x in self._charge_profiles:
            x.pass_trough_bill = None
        for y in value:
            y.pass_trough_bill = self
        self._charge_profiles = value
            
    charge_profiles = property(get_charge_profiles, set_charge_profiles)
    
    def add_charge_profiles(self, *charge_profiles):
        for obj in charge_profiles:
            obj._pass_trough_bill = self
            if obj not in self._charge_profiles:
                self._charge_profiles.append(obj)
        
    def remove_charge_profiles(self, *charge_profiles):
        for obj in charge_profiles:
            obj._pass_trough_bill = None
            self._charge_profiles.remove(obj)
    # >>> charge_profiles

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ 
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for p in self._user_attributes:
            filtered = [q for q in p.pass_through_bills if q != self]
            self._user_attributes._pass_through_bills = filtered
        for r in value:
            if self not in r._pass_through_bills:
                r._pass_through_bills.append(self)
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self not in obj._pass_through_bills:
                obj._pass_through_bills.append(self)
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self in obj._pass_through_bills:
                obj._pass_through_bills.remove(self)
            self._user_attributes.remove(obj)
    # >>> user_attributes



class RampRateCurve(Curve):
    """ Ramp rate as a function of resource MW output
    """
    # <<< ramp_rate_curve
    # @generated
    def __init__(self, ramp_rate_type='', generating_unit=None, **kw_args):
        """ Initialises a new 'RampRateCurve' instance.
        """
        # How ramp rate is applied (e.g., raise or lower, as when applied to a generation resource) 
        self.ramp_rate_type = ramp_rate_type
        
        self._generating_unit = []
        if generating_unit is None:
            self.generating_unit = []
        else:
            self.generating_unit = generating_unit

        super(RampRateCurve, self).__init__(**kw_args)
    # >>> ramp_rate_curve
        
    # <<< generating_unit
    # @generated
    def get_generating_unit(self):
        """ 
        """
        return self._generating_unit

    def set_generating_unit(self, value):
        for p in self._generating_unit:
            filtered = [q for q in p.ramp_rate_curves if q != self]
            self._generating_unit._ramp_rate_curves = filtered
        for r in value:
            if self not in r._ramp_rate_curves:
                r._ramp_rate_curves.append(self)
        self._generating_unit = value
            
    generating_unit = property(get_generating_unit, set_generating_unit)
    
    def add_generating_unit(self, *generating_unit):
        for obj in generating_unit:
            if self not in obj._ramp_rate_curves:
                obj._ramp_rate_curves.append(self)
            self._generating_unit.append(obj)
        
    def remove_generating_unit(self, *generating_unit):
        for obj in generating_unit:
            if self in obj._ramp_rate_curves:
                obj._ramp_rate_curves.remove(self)
            self._generating_unit.remove(obj)
    # >>> generating_unit



class Bid(Document):
    """ Represents both bids to purchase and offers to sell energy or ancillary services in an RTO-sponsored market.
    """
    # <<< bid
    # @generated
    def __init__(self, stop_time='', start_time='', market_type='', bid_clearing=None, market=None, product_bids=None, **kw_args):
        """ Initialises a new 'Bid' instance.
        """
        # Stop time and date for which bid is applicable. 
        self.stop_time = stop_time
        # Start time and date for which bid applies. 
        self.start_time = start_time
 
        self.market_type = market_type
        
        self._bid_clearing = None
        self.bid_clearing = bid_clearing
        self._market = None
        self.market = market
        self._product_bids = []
        if product_bids is None:
            self.product_bids = []
        else:
            self.product_bids = product_bids

        super(Bid, self).__init__(**kw_args)
    # >>> bid
        
    # <<< bid_clearing
    # @generated
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
    # >>> bid_clearing

    # <<< market
    # @generated
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
            if self not in self._market._bids:
                self._market._bids.append(self)

    market = property(get_market, set_market)
    # >>> market

    # <<< product_bids
    # @generated
    def get_product_bids(self):
        """ A bid comprises one or more product bids of market products
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x.bid = None
        for y in value:
            y.bid = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid = self
            if obj not in self._product_bids:
                self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._bid = None
            self._product_bids.remove(obj)
    # >>> product_bids



class ChargeProfile(Profile):
    """ A type of profile for financial charges
    """
    # <<< charge_profile
    # @generated
    def __init__(self, unit_of_measure='', frequency='', type='', number_interval=0, charge_profile_data=None, pass_trough_bill=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfile' instance.
        """
        # The unit of measure applied to the value attribute of the profile data. 
        self.unit_of_measure = unit_of_measure
        # The calculation frequency, daily or monthly. 
        self.frequency = frequency
        # The type of profile.  It could be amount, price, or quantity. 
        self.type = type
        # The number of intervals in the profile data. 
        self.number_interval = number_interval
        
        self._charge_profile_data = []
        if charge_profile_data is None:
            self.charge_profile_data = []
        else:
            self.charge_profile_data = charge_profile_data
        self._pass_trough_bill = None
        self.pass_trough_bill = pass_trough_bill
        self._bill_determinant = None
        self.bill_determinant = bill_determinant

        super(ChargeProfile, self).__init__(**kw_args)
    # >>> charge_profile
        
    # <<< charge_profile_data
    # @generated
    def get_charge_profile_data(self):
        """ 
        """
        return self._charge_profile_data

    def set_charge_profile_data(self, value):
        for x in self._charge_profile_data:
            x.charge_profile = None
        for y in value:
            y.charge_profile = self
        self._charge_profile_data = value
            
    charge_profile_data = property(get_charge_profile_data, set_charge_profile_data)
    
    def add_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._charge_profile = self
            if obj not in self._charge_profile_data:
                self._charge_profile_data.append(obj)
        
    def remove_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._charge_profile = None
            self._charge_profile_data.remove(obj)
    # >>> charge_profile_data

    # <<< pass_trough_bill
    # @generated
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
            if self not in self._pass_trough_bill._charge_profiles:
                self._pass_trough_bill._charge_profiles.append(self)

    pass_trough_bill = property(get_pass_trough_bill, set_pass_trough_bill)
    # >>> pass_trough_bill

    # <<< bill_determinant
    # @generated
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
    # >>> bill_determinant



class ResourceGroupReq(IdentifiedObject):
    """ Ancillary service requirements for a market.
    """
    # <<< resource_group_req
    # @generated
    def __init__(self, rtos=None, resource_group=None, **kw_args):
        """ Initialises a new 'ResourceGroupReq' instance.
        """
        
        self._rtos = []
        if rtos is None:
            self.rtos = []
        else:
            self.rtos = rtos
        self._resource_group = None
        self.resource_group = resource_group

        super(ResourceGroupReq, self).__init__(**kw_args)
    # >>> resource_group_req
        
    # <<< rtos
    # @generated
    def get_rtos(self):
        """ 
        """
        return self._rtos

    def set_rtos(self, value):
        for p in self._rtos:
            filtered = [q for q in p.resource_group_reqs if q != self]
            self._rtos._resource_group_reqs = filtered
        for r in value:
            if self not in r._resource_group_reqs:
                r._resource_group_reqs.append(self)
        self._rtos = value
            
    rtos = property(get_rtos, set_rtos)
    
    def add_rtos(self, *rtos):
        for obj in rtos:
            if self not in obj._resource_group_reqs:
                obj._resource_group_reqs.append(self)
            self._rtos.append(obj)
        
    def remove_rtos(self, *rtos):
        for obj in rtos:
            if self in obj._resource_group_reqs:
                obj._resource_group_reqs.remove(self)
            self._rtos.remove(obj)
    # >>> rtos

    # <<< resource_group
    # @generated
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
            if self not in self._resource_group._resource_group_reqs:
                self._resource_group._resource_group_reqs.append(self)

    resource_group = property(get_resource_group, set_resource_group)
    # >>> resource_group



class Market(IdentifiedObject):
    """ Market (e.g., DAM, HAM)  zzMarket operation control parameters.
    """
    # <<< market
    # @generated
    def __init__(self, start='', end='', dst=False, time_interval_length=0.0, local_time_zone='', ramp_interval_energy=0.0, ramp_interval_spin_res=0.0, type='', ramp_interval_reg=0.0, ramp_interval_non_spin_res=0.0, market_products=None, registered_resources=None, rto=None, settlements=None, bids=None, market_factors=None, **kw_args):
        """ Initialises a new 'Market' instance.
        """
        # Market start time. 
        self.start = start
        # Market end time. 
        self.end = end
        # True if daylight savings time (DST) is in effect. 
        self.dst = dst
        # Trading time interval length. 
        self.time_interval_length = time_interval_length
        # Local time zone. 
        self.local_time_zone = local_time_zone
        # Ramping time interval for energy. 
        self.ramp_interval_energy = ramp_interval_energy
        # Ramping time interval for spinning reserve. 
        self.ramp_interval_spin_res = ramp_interval_spin_res
        # The type of a market. 
        self.type = type
        # Ramping time interval for regulation. 
        self.ramp_interval_reg = ramp_interval_reg
        # Ramping time interval for non-spinning reserve. 
        self.ramp_interval_non_spin_res = ramp_interval_non_spin_res
        
        self._market_products = []
        if market_products is None:
            self.market_products = []
        else:
            self.market_products = market_products
        self._registered_resources = []
        if registered_resources is None:
            self.registered_resources = []
        else:
            self.registered_resources = registered_resources
        self._rto = None
        self.rto = rto
        self._settlements = []
        if settlements is None:
            self.settlements = []
        else:
            self.settlements = settlements
        self._bids = []
        if bids is None:
            self.bids = []
        else:
            self.bids = bids
        self._market_factors = []
        if market_factors is None:
            self.market_factors = []
        else:
            self.market_factors = market_factors

        super(Market, self).__init__(**kw_args)
    # >>> market
        
    # <<< market_products
    # @generated
    def get_market_products(self):
        """ 
        """
        return self._market_products

    def set_market_products(self, value):
        for x in self._market_products:
            x.market = None
        for y in value:
            y.market = self
        self._market_products = value
            
    market_products = property(get_market_products, set_market_products)
    
    def add_market_products(self, *market_products):
        for obj in market_products:
            obj._market = self
            if obj not in self._market_products:
                self._market_products.append(obj)
        
    def remove_market_products(self, *market_products):
        for obj in market_products:
            obj._market = None
            self._market_products.remove(obj)
    # >>> market_products

    # <<< registered_resources
    # @generated
    def get_registered_resources(self):
        """ 
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for p in self._registered_resources:
            filtered = [q for q in p.markets if q != self]
            self._registered_resources._markets = filtered
        for r in value:
            if self not in r._markets:
                r._markets.append(self)
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self not in obj._markets:
                obj._markets.append(self)
            self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self in obj._markets:
                obj._markets.remove(self)
            self._registered_resources.remove(obj)
    # >>> registered_resources

    # <<< rto
    # @generated
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
            if self not in self._rto._markets:
                self._rto._markets.append(self)

    rto = property(get_rto, set_rto)
    # >>> rto

    # <<< settlements
    # @generated
    def get_settlements(self):
        """ 
        """
        return self._settlements

    def set_settlements(self, value):
        for x in self._settlements:
            x.market = None
        for y in value:
            y.market = self
        self._settlements = value
            
    settlements = property(get_settlements, set_settlements)
    
    def add_settlements(self, *settlements):
        for obj in settlements:
            obj._market = self
            if obj not in self._settlements:
                self._settlements.append(obj)
        
    def remove_settlements(self, *settlements):
        for obj in settlements:
            obj._market = None
            self._settlements.remove(obj)
    # >>> settlements

    # <<< bids
    # @generated
    def get_bids(self):
        """ 
        """
        return self._bids

    def set_bids(self, value):
        for x in self._bids:
            x.market = None
        for y in value:
            y.market = self
        self._bids = value
            
    bids = property(get_bids, set_bids)
    
    def add_bids(self, *bids):
        for obj in bids:
            obj._market = self
            if obj not in self._bids:
                self._bids.append(obj)
        
    def remove_bids(self, *bids):
        for obj in bids:
            obj._market = None
            self._bids.remove(obj)
    # >>> bids

    # <<< market_factors
    # @generated
    def get_market_factors(self):
        """ 
        """
        return self._market_factors

    def set_market_factors(self, value):
        for x in self._market_factors:
            x.market = None
        for y in value:
            y.market = self
        self._market_factors = value
            
    market_factors = property(get_market_factors, set_market_factors)
    
    def add_market_factors(self, *market_factors):
        for obj in market_factors:
            obj._market = self
            if obj not in self._market_factors:
                self._market_factors.append(obj)
        
    def remove_market_factors(self, *market_factors):
        for obj in market_factors:
            obj._market = None
            self._market_factors.remove(obj)
    # >>> market_factors



class ConstraintTerm(IdentifiedObject):
    """ A constraint term is one element of a linear constraint.
    """
    # <<< constraint_term
    # @generated
    def __init__(self, function='', factor='', security_constraint_sum=None, **kw_args):
        """ Initialises a new 'ConstraintTerm' instance.
        """
        # The function is an enumerated value that can be 'active', 'reactive', or 'VA' to indicate the type of flow. 
        self.function = function
 
        self.factor = factor
        
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(ConstraintTerm, self).__init__(**kw_args)
    # >>> constraint_term
        
    # <<< security_constraint_sum
    # @generated
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
            if self not in self._security_constraint_sum._constraint_terms:
                self._security_constraint_sum._constraint_terms.append(self)

    security_constraint_sum = property(get_security_constraint_sum, set_security_constraint_sum)
    # >>> security_constraint_sum



class MarketStatementLineItem(IdentifiedObject):
    """ An individual line item on a statement.
    """
    # <<< market_statement_line_item
    # @generated
    def __init__(self, quantity_uom='', previsou_price=0.0, net_isoamount=0.0, previous_isoquantity=0.0, current_isoquantity=0.0, net_quantity=0.0, previous_isoamount=0.0, net_price=0.0, previous_amount=0.0, net_amount=0.0, previous_quantity=0.0, current_quantity=0.0, net_isoquantity=0.0, current_isoamount=0.0, interval_number='', current_price=0.0, interval_date='', current_amount=0.0, component_market_statement_line_item=None, user_attributes=None, pass_through_bill=None, market_statement=None, container_market_statement_line_item=None, **kw_args):
        """ Initialises a new 'MarketStatementLineItem' instance.
        """
        # The unit of measure for the quantity element of the line item. 
        self.quantity_uom = quantity_uom
        # Previous settlement price. 
        self.previsou_price = previsou_price
        # Net ISO settlement amount. 
        self.net_isoamount = net_isoamount
        # Previous ISO settlement quantity. 
        self.previous_isoquantity = previous_isoquantity
        # Current ISO settlement quantity. 
        self.current_isoquantity = current_isoquantity
        # Net settlement quantity, subject to the UOM. 
        self.net_quantity = net_quantity
        # Previous ISO settlement amount. 
        self.previous_isoamount = previous_isoamount
        # Net settlement price. 
        self.net_price = net_price
        # Previous settlement amount. 
        self.previous_amount = previous_amount
        # Net settlement amount. 
        self.net_amount = net_amount
        # Previous settlement quantity, subject to the UOM. 
        self.previous_quantity = previous_quantity
        # Current settlement quantity, subject to the UOM. 
        self.current_quantity = current_quantity
        # Net ISO settlement quantity. 
        self.net_isoquantity = net_isoquantity
        # Current ISO settlement amount. 
        self.current_isoamount = current_isoamount
        # The number of intervals. 
        self.interval_number = interval_number
        # Current settlement price. 
        self.current_price = current_price
        # The date of which the settlement is run. 
        self.interval_date = interval_date
        # Current settlement amount. 
        self.current_amount = current_amount
        
        self._component_market_statement_line_item = []
        if component_market_statement_line_item is None:
            self.component_market_statement_line_item = []
        else:
            self.component_market_statement_line_item = component_market_statement_line_item
        self._user_attributes = []
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes
        self._pass_through_bill = None
        self.pass_through_bill = pass_through_bill
        self._market_statement = None
        self.market_statement = market_statement
        self._container_market_statement_line_item = None
        self.container_market_statement_line_item = container_market_statement_line_item

        super(MarketStatementLineItem, self).__init__(**kw_args)
    # >>> market_statement_line_item
        
    # <<< component_market_statement_line_item
    # @generated
    def get_component_market_statement_line_item(self):
        """ 
        """
        return self._component_market_statement_line_item

    def set_component_market_statement_line_item(self, value):
        for x in self._component_market_statement_line_item:
            x.container_market_statement_line_item = None
        for y in value:
            y.container_market_statement_line_item = self
        self._component_market_statement_line_item = value
            
    component_market_statement_line_item = property(get_component_market_statement_line_item, set_component_market_statement_line_item)
    
    def add_component_market_statement_line_item(self, *component_market_statement_line_item):
        for obj in component_market_statement_line_item:
            obj._container_market_statement_line_item = self
            if obj not in self._component_market_statement_line_item:
                self._component_market_statement_line_item.append(obj)
        
    def remove_component_market_statement_line_item(self, *component_market_statement_line_item):
        for obj in component_market_statement_line_item:
            obj._container_market_statement_line_item = None
            self._component_market_statement_line_item.remove(obj)
    # >>> component_market_statement_line_item

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ 
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for p in self._user_attributes:
            filtered = [q for q in p.erp_statement_line_items if q != self]
            self._user_attributes._erp_statement_line_items = filtered
        for r in value:
            if self not in r._erp_statement_line_items:
                r._erp_statement_line_items.append(self)
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self not in obj._erp_statement_line_items:
                obj._erp_statement_line_items.append(self)
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self in obj._erp_statement_line_items:
                obj._erp_statement_line_items.remove(self)
            self._user_attributes.remove(obj)
    # >>> user_attributes

    # <<< pass_through_bill
    # @generated
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
    # >>> pass_through_bill

    # <<< market_statement
    # @generated
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
            if self not in self._market_statement._market_statement_line_item:
                self._market_statement._market_statement_line_item.append(self)

    market_statement = property(get_market_statement, set_market_statement)
    # >>> market_statement

    # <<< container_market_statement_line_item
    # @generated
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
            if self not in self._container_market_statement_line_item._component_market_statement_line_item:
                self._container_market_statement_line_item._component_market_statement_line_item.append(self)

    container_market_statement_line_item = property(get_container_market_statement_line_item, set_container_market_statement_line_item)
    # >>> container_market_statement_line_item



class MWLimitSchedule(Curve):
    """ Maximum MW and optionally Minimum MW (Y1 and Y2, respectively)
    """
    # <<< mwlimit_schedule
    # @generated
    def __init__(self, security_constraint_limit=None, **kw_args):
        """ Initialises a new 'MWLimitSchedule' instance.
        """
        
        self._security_constraint_limit = None
        self.security_constraint_limit = security_constraint_limit

        super(MWLimitSchedule, self).__init__(**kw_args)
    # >>> mwlimit_schedule
        
    # <<< security_constraint_limit
    # @generated
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
    # >>> security_constraint_limit



class ProductBid(IdentifiedObject):
    """ Component of a bid that pertains to one market product.
    """
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
        
    # <<< bid
    # @generated
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
            if self not in self._bid._product_bids:
                self._bid._product_bids.append(self)

    bid = property(get_bid, set_bid)
    # >>> bid

    # <<< product_bid_clearing
    # @generated
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
            if self not in self._product_bid_clearing._product_bids:
                self._product_bid_clearing._product_bids.append(self)

    product_bid_clearing = property(get_product_bid_clearing, set_product_bid_clearing)
    # >>> product_bid_clearing

    # <<< bid_price_curve
    # @generated
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
            if self not in self._bid_price_curve._product_bids:
                self._bid_price_curve._product_bids.append(self)

    bid_price_curve = property(get_bid_price_curve, set_bid_price_curve)
    # >>> bid_price_curve

    # <<< market_product
    # @generated
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
            if self not in self._market_product._product_bids:
                self._market_product._product_bids.append(self)

    market_product = property(get_market_product, set_market_product)
    # >>> market_product



class SecurityConstraints(IdentifiedObject):
    """ Typical for regional transmission operators (RTOs), these constraints include transmission as well as generation group constraints identified in both base case and critical contingency cases.
    """
    # <<< security_constraints
    # @generated
    def __init__(self, max_mw=0.0, min_mw=0.0, actual_mw=0.0, rto=None, **kw_args):
        """ Initialises a new 'SecurityConstraints' instance.
        """
        # Maximum MW limit 
        self.max_mw = max_mw
        # Minimum MW limit (only for transmission constraints). 
        self.min_mw = min_mw
        # Actual branch or group of branches MW flow (only for transmission constraints) 
        self.actual_mw = actual_mw
        
        self._rto = None
        self.rto = rto

        super(SecurityConstraints, self).__init__(**kw_args)
    # >>> security_constraints
        
    # <<< rto
    # @generated
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
            if self not in self._rto._security_constraints:
                self._rto._security_constraints.append(self)

    rto = property(get_rto, set_rto)
    # >>> rto



class MarketStatement(Document):
    """ A statement is a roll up of statement line items. Each statement along with its line items provide the details of specific charges at any given time.
    """
    # <<< market_statement
    # @generated
    def __init__(self, transaction_date='', start='', end='', reference_number='', trade_date='', market_statement_line_item=None, **kw_args):
        """ Initialises a new 'MarketStatement' instance.
        """
        # The date of which this statement is issued. 
        self.transaction_date = transaction_date
        # The start of a bill period. 
        self.start = start
        # The end of a bill period. 
        self.end = end
        # The version number of previous statement (in the case of true up). 
        self.reference_number = reference_number
        # The date of which Settlement is run. 
        self.trade_date = trade_date
        
        self._market_statement_line_item = []
        if market_statement_line_item is None:
            self.market_statement_line_item = []
        else:
            self.market_statement_line_item = market_statement_line_item

        super(MarketStatement, self).__init__(**kw_args)
    # >>> market_statement
        
    # <<< market_statement_line_item
    # @generated
    def get_market_statement_line_item(self):
        """ 
        """
        return self._market_statement_line_item

    def set_market_statement_line_item(self, value):
        for x in self._market_statement_line_item:
            x.market_statement = None
        for y in value:
            y.market_statement = self
        self._market_statement_line_item = value
            
    market_statement_line_item = property(get_market_statement_line_item, set_market_statement_line_item)
    
    def add_market_statement_line_item(self, *market_statement_line_item):
        for obj in market_statement_line_item:
            obj._market_statement = self
            if obj not in self._market_statement_line_item:
                self._market_statement_line_item.append(obj)
        
    def remove_market_statement_line_item(self, *market_statement_line_item):
        for obj in market_statement_line_item:
            obj._market_statement = None
            self._market_statement_line_item.remove(obj)
    # >>> market_statement_line_item



class SensitivityPriceCurve(Curve):
    """ Optionally, this curve expresses elasticity of the associated requirement.  For example, used to reduce requirements when clearing price exceeds reasonable values when the supply quantity becomes scarce.  For example, a single point value of $1000/MW for a spinning reserve will cause a reduction in the required spinning reserve.  X axis is constrained quantity (e.g., MW) Y1 axis is money per constrained quantity
    """
    # <<< sensitivity_price_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'SensitivityPriceCurve' instance.
        """
        
        self._reserve_req = None
        self.reserve_req = reserve_req

        super(SensitivityPriceCurve, self).__init__(**kw_args)
    # >>> sensitivity_price_curve
        
    # <<< reserve_req
    # @generated
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
    # >>> reserve_req



class Settlement(Document):
    """ Specifies a settlement run.
    """
    # <<< settlement
    # @generated
    def __init__(self, trade_date='', market=None, erp_invoice_line_items=None, erp_ledger_entries=None, **kw_args):
        """ Initialises a new 'Settlement' instance.
        """
        # The trade date on which the settlement is run. 
        self.trade_date = trade_date
        
        self._market = None
        self.market = market
        self._erp_invoice_line_items = []
        if erp_invoice_line_items is None:
            self.erp_invoice_line_items = []
        else:
            self.erp_invoice_line_items = erp_invoice_line_items
        self._erp_ledger_entries = []
        if erp_ledger_entries is None:
            self.erp_ledger_entries = []
        else:
            self.erp_ledger_entries = erp_ledger_entries

        super(Settlement, self).__init__(**kw_args)
    # >>> settlement
        
    # <<< market
    # @generated
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
            if self not in self._market._settlements:
                self._market._settlements.append(self)

    market = property(get_market, set_market)
    # >>> market

    # <<< erp_invoice_line_items
    # @generated
    def get_erp_invoice_line_items(self):
        """ 
        """
        return self._erp_invoice_line_items

    def set_erp_invoice_line_items(self, value):
        for p in self._erp_invoice_line_items:
            filtered = [q for q in p.settlements if q != self]
            self._erp_invoice_line_items._settlements = filtered
        for r in value:
            if self not in r._settlements:
                r._settlements.append(self)
        self._erp_invoice_line_items = value
            
    erp_invoice_line_items = property(get_erp_invoice_line_items, set_erp_invoice_line_items)
    
    def add_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self not in obj._settlements:
                obj._settlements.append(self)
            self._erp_invoice_line_items.append(obj)
        
    def remove_erp_invoice_line_items(self, *erp_invoice_line_items):
        for obj in erp_invoice_line_items:
            if self in obj._settlements:
                obj._settlements.remove(self)
            self._erp_invoice_line_items.remove(obj)
    # >>> erp_invoice_line_items

    # <<< erp_ledger_entries
    # @generated
    def get_erp_ledger_entries(self):
        """ 
        """
        return self._erp_ledger_entries

    def set_erp_ledger_entries(self, value):
        for p in self._erp_ledger_entries:
            filtered = [q for q in p.settlements if q != self]
            self._erp_ledger_entries._settlements = filtered
        for r in value:
            if self not in r._settlements:
                r._settlements.append(self)
        self._erp_ledger_entries = value
            
    erp_ledger_entries = property(get_erp_ledger_entries, set_erp_ledger_entries)
    
    def add_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            if self not in obj._settlements:
                obj._settlements.append(self)
            self._erp_ledger_entries.append(obj)
        
    def remove_erp_ledger_entries(self, *erp_ledger_entries):
        for obj in erp_ledger_entries:
            if self in obj._settlements:
                obj._settlements.remove(self)
            self._erp_ledger_entries.remove(obj)
    # >>> erp_ledger_entries



class BillDeterminant(Document):
    # <<< bill_determinant
    # @generated
    def __init__(self, config_version='', number_interval=0, precision_level='', unit_of_measure='', calculation_level='', charge_profile_data=None, charge_profile=None, user_attributes=None, **kw_args):
        """ Initialises a new 'BillDeterminant' instance.
        """
        # The version of configuration of calculation logic in the settlement. 
        self.config_version = config_version
        # Number of intervals of bill determiant in trade day, eg 300 for five minute intervals. 
        self.number_interval = number_interval
        # The level of precision in the current value. 
        self.precision_level = precision_level
        # The UOM for the current value of the Bill Determinant. 
        self.unit_of_measure = unit_of_measure
        # Level in charge calculation order. 
        self.calculation_level = calculation_level
        
        self._charge_profile_data = []
        if charge_profile_data is None:
            self.charge_profile_data = []
        else:
            self.charge_profile_data = charge_profile_data
        self._charge_profile = None
        self.charge_profile = charge_profile
        self._user_attributes = []
        if user_attributes is None:
            self.user_attributes = []
        else:
            self.user_attributes = user_attributes

        super(BillDeterminant, self).__init__(**kw_args)
    # >>> bill_determinant
        
    # <<< charge_profile_data
    # @generated
    def get_charge_profile_data(self):
        """ 
        """
        return self._charge_profile_data

    def set_charge_profile_data(self, value):
        for x in self._charge_profile_data:
            x.bill_determinant = None
        for y in value:
            y.bill_determinant = self
        self._charge_profile_data = value
            
    charge_profile_data = property(get_charge_profile_data, set_charge_profile_data)
    
    def add_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._bill_determinant = self
            if obj not in self._charge_profile_data:
                self._charge_profile_data.append(obj)
        
    def remove_charge_profile_data(self, *charge_profile_data):
        for obj in charge_profile_data:
            obj._bill_determinant = None
            self._charge_profile_data.remove(obj)
    # >>> charge_profile_data

    # <<< charge_profile
    # @generated
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
    # >>> charge_profile

    # <<< user_attributes
    # @generated
    def get_user_attributes(self):
        """ 
        """
        return self._user_attributes

    def set_user_attributes(self, value):
        for p in self._user_attributes:
            filtered = [q for q in p.bill_determinants if q != self]
            self._user_attributes._bill_determinants = filtered
        for r in value:
            if self not in r._bill_determinants:
                r._bill_determinants.append(self)
        self._user_attributes = value
            
    user_attributes = property(get_user_attributes, set_user_attributes)
    
    def add_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self not in obj._bill_determinants:
                obj._bill_determinants.append(self)
            self._user_attributes.append(obj)
        
    def remove_user_attributes(self, *user_attributes):
        for obj in user_attributes:
            if self in obj._bill_determinants:
                obj._bill_determinants.remove(self)
            self._user_attributes.remove(obj)
    # >>> user_attributes



class MarketFactors(Document):
    """ Aggregation of market information relative for a specific time interval.
    """
    # <<< market_factors
    # @generated
    def __init__(self, interval_start_time='', market=None, erp_invoices=None, activity_records=None, **kw_args):
        """ Initialises a new 'MarketFactors' instance.
        """
        # The start of the time interval for which requirement is defined. 
        self.interval_start_time = interval_start_time
        
        self._market = None
        self.market = market
        self._erp_invoices = []
        if erp_invoices is None:
            self.erp_invoices = []
        else:
            self.erp_invoices = erp_invoices
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records

        super(MarketFactors, self).__init__(**kw_args)
    # >>> market_factors
        
    # <<< market
    # @generated
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
            if self not in self._market._market_factors:
                self._market._market_factors.append(self)

    market = property(get_market, set_market)
    # >>> market

    # <<< erp_invoices
    # @generated
    def get_erp_invoices(self):
        """ 
        """
        return self._erp_invoices

    def set_erp_invoices(self, value):
        for p in self._erp_invoices:
            filtered = [q for q in p.market_factors if q != self]
            self._erp_invoices._market_factors = filtered
        for r in value:
            if self not in r._market_factors:
                r._market_factors.append(self)
        self._erp_invoices = value
            
    erp_invoices = property(get_erp_invoices, set_erp_invoices)
    
    def add_erp_invoices(self, *erp_invoices):
        for obj in erp_invoices:
            if self not in obj._market_factors:
                obj._market_factors.append(self)
            self._erp_invoices.append(obj)
        
    def remove_erp_invoices(self, *erp_invoices):
        for obj in erp_invoices:
            if self in obj._market_factors:
                obj._market_factors.remove(self)
            self._erp_invoices.remove(obj)
    # >>> erp_invoices

    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ 
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self._activity_records:
            filtered = [q for q in p.market_factors if q != self]
            self._activity_records._market_factors = filtered
        for r in value:
            if self not in r._market_factors:
                r._market_factors.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._market_factors:
                obj._market_factors.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._market_factors:
                obj._market_factors.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records



class DefaultConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) applied as a default value if no specific constraint limits are specified for a contingency analysis. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
    # <<< default_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'DefaultConstraintLimit' instance.
        """
        
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(DefaultConstraintLimit, self).__init__(**kw_args)
    # >>> default_constraint_limit
        
    # <<< security_constraint_sum
    # @generated
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
    # >>> security_constraint_sum



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
    # <<< bid_set
    # @generated
    def __init__(self, generating_bids=None, **kw_args):
        """ Initialises a new 'BidSet' instance.
        """
        
        self._generating_bids = []
        if generating_bids is None:
            self.generating_bids = []
        else:
            self.generating_bids = generating_bids

        super(BidSet, self).__init__(**kw_args)
    # >>> bid_set
        
    # <<< generating_bids
    # @generated
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x.bid_set = None
        for y in value:
            y.bid_set = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._bid_set = self
            if obj not in self._generating_bids:
                self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._bid_set = None
            self._generating_bids.remove(obj)
    # >>> generating_bids



class LoadReductionPriceCurve(Curve):
    """ This is the price sensitivity that bidder expresses for allowing market load interruption.  Relationship between price (Y1-axis) vs. MW (X-axis).
    """
    # <<< load_reduction_price_curve
    # @generated
    def __init__(self, load_bids=None, **kw_args):
        """ Initialises a new 'LoadReductionPriceCurve' instance.
        """
        
        self._load_bids = []
        if load_bids is None:
            self.load_bids = []
        else:
            self.load_bids = load_bids

        super(LoadReductionPriceCurve, self).__init__(**kw_args)
    # >>> load_reduction_price_curve
        
    # <<< load_bids
    # @generated
    def get_load_bids(self):
        """ 
        """
        return self._load_bids

    def set_load_bids(self, value):
        for x in self._load_bids:
            x.load_reduction_price_curve = None
        for y in value:
            y.load_reduction_price_curve = self
        self._load_bids = value
            
    load_bids = property(get_load_bids, set_load_bids)
    
    def add_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._load_reduction_price_curve = self
            if obj not in self._load_bids:
                self._load_bids.append(obj)
        
    def remove_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._load_reduction_price_curve = None
            self._load_bids.remove(obj)
    # >>> load_bids



class BidClearing(Element):
    """ Bid clearing results posted for a given settlement period.
    """
    # <<< bid_clearing
    # @generated
    def __init__(self, lost_op_cost=0.0, start_up_cost=0.0, no_load_cost=0.0, bid=None, **kw_args):
        """ Initialises a new 'BidClearing' instance.
        """
        # Energy lost opportunity cost in monetary units. 
        self.lost_op_cost = lost_op_cost
        # Start up cost in case of energy commodity in monetary units. 
        self.start_up_cost = start_up_cost
        # No-load cost in monetary units. 
        self.no_load_cost = no_load_cost
        
        self._bid = None
        self.bid = bid

        super(BidClearing, self).__init__(**kw_args)
    # >>> bid_clearing
        
    # <<< bid
    # @generated
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
    # >>> bid



class Flowgate(PowerSystemResource):
    """ A flowgate, is single or group of transmission elements intended to model MW flow impact relating to transmission limitations and transmission service usage.
    """
    # <<< flowgate
    # @generated
    def __init__(self, coordinated_flag=False, reciprocal_flag=False, in_service_date='', counter_flow_value=0, atc_flag=False, out_of_service_date='', managing_entity_flag=False, idc_operational_name='', positive_impact_value=0, coordination_study_date='', idc_assigned_id=0, deletion_date='', transmission_provider=None, sub_control_area=None, ftrs=None, lines=None, capacity_benefit_margin=None, transmission_reliability_margin=None, violation_limits=None, power_transormers=None, **kw_args):
        """ Initialises a new 'Flowgate' instance.
        """
        # Flag to indicate if Flowgate qualified as coordinated Flowgate 
        self.coordinated_flag = coordinated_flag
        # Flag to indicate if Flowgate qualified as reciprocal Flowgate 
        self.reciprocal_flag = reciprocal_flag
        # Date at which point Flowgate becomes active.  Used to insert future Flowgates or Flowgates returning from an outage condition. 
        self.in_service_date = in_service_date
        # Percentage of counterflow to remove/exclude from the AFC calculation.  Integer.  Must be 100 or less. 
        self.counter_flow_value = counter_flow_value
        # Flag to indicate if Flowgate is utilized for coordination of ATC. 
        self.atc_flag = atc_flag
        # Date at which point Flowgate becomes inactive. Used to insert outage condition. 
        self.out_of_service_date = out_of_service_date
        # Standard Reliabilty Entity (e.g. in North America NERC) that has agreed per a reciprocal agreement to manage coordination on the Flowgate.  Will always be either True or False - if not a reciprocal Flowgate, will be false. 
        self.managing_entity_flag = managing_entity_flag
        # The Registered Name utilized in the IDC and/or Book of Flowgates 
        self.idc_operational_name = idc_operational_name
        # Percentage of positive impact to include in the AFC calculation.  Integer.  Must be 100 or less. 
        self.positive_impact_value = positive_impact_value
        # Date upon which study of Flowgate to determine coordinated status was performed.  May be null is no study undertaken yet. 
        self.coordination_study_date = coordination_study_date
        # The registered Flowgate ID Assigned by the IDC and/or Book of Flowgate. 
        self.idc_assigned_id = idc_assigned_id
        # Date at which point Flowgate should be removed from the Interchange Distribution Calculatin (IDC). 
        self.deletion_date = deletion_date
        
        self._transmission_provider = []
        if transmission_provider is None:
            self.transmission_provider = []
        else:
            self.transmission_provider = transmission_provider
        self._sub_control_area = None
        self.sub_control_area = sub_control_area
        self._ftrs = []
        if ftrs is None:
            self.ftrs = []
        else:
            self.ftrs = ftrs
        self._lines = []
        if lines is None:
            self.lines = []
        else:
            self.lines = lines
        self._capacity_benefit_margin = []
        if capacity_benefit_margin is None:
            self.capacity_benefit_margin = []
        else:
            self.capacity_benefit_margin = capacity_benefit_margin
        self._transmission_reliability_margin = None
        self.transmission_reliability_margin = transmission_reliability_margin
        self._violation_limits = []
        if violation_limits is None:
            self.violation_limits = []
        else:
            self.violation_limits = violation_limits
        self._power_transormers = []
        if power_transormers is None:
            self.power_transormers = []
        else:
            self.power_transormers = power_transormers

        super(Flowgate, self).__init__(**kw_args)
    # >>> flowgate
        
    # <<< transmission_provider
    # @generated
    def get_transmission_provider(self):
        """ A flowgate can be reciprocal flowgate for 0 to n transmission providers. A transmission provider may be a reciprocal entity for 0 to n flowgates.
        """
        return self._transmission_provider

    def set_transmission_provider(self, value):
        for p in self._transmission_provider:
            filtered = [q for q in p.flowgate if q != self]
            self._transmission_provider._flowgate = filtered
        for r in value:
            if self not in r._flowgate:
                r._flowgate.append(self)
        self._transmission_provider = value
            
    transmission_provider = property(get_transmission_provider, set_transmission_provider)
    
    def add_transmission_provider(self, *transmission_provider):
        for obj in transmission_provider:
            if self not in obj._flowgate:
                obj._flowgate.append(self)
            self._transmission_provider.append(obj)
        
    def remove_transmission_provider(self, *transmission_provider):
        for obj in transmission_provider:
            if self in obj._flowgate:
                obj._flowgate.remove(self)
            self._transmission_provider.remove(obj)
    # >>> transmission_provider

    # <<< sub_control_area
    # @generated
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
            if self not in self._sub_control_area._flowgate:
                self._sub_control_area._flowgate.append(self)

    sub_control_area = property(get_sub_control_area, set_sub_control_area)
    # >>> sub_control_area

    # <<< ftrs
    # @generated
    def get_ftrs(self):
        """ 
        """
        return self._ftrs

    def set_ftrs(self, value):
        for x in self._ftrs:
            x.flowgate = None
        for y in value:
            y.flowgate = self
        self._ftrs = value
            
    ftrs = property(get_ftrs, set_ftrs)
    
    def add_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._flowgate = self
            if obj not in self._ftrs:
                self._ftrs.append(obj)
        
    def remove_ftrs(self, *ftrs):
        for obj in ftrs:
            obj._flowgate = None
            self._ftrs.remove(obj)
    # >>> ftrs

    # <<< lines
    # @generated
    def get_lines(self):
        """ 
        """
        return self._lines

    def set_lines(self, value):
        for p in self._lines:
            filtered = [q for q in p.flowgates if q != self]
            self._lines._flowgates = filtered
        for r in value:
            if self not in r._flowgates:
                r._flowgates.append(self)
        self._lines = value
            
    lines = property(get_lines, set_lines)
    
    def add_lines(self, *lines):
        for obj in lines:
            if self not in obj._flowgates:
                obj._flowgates.append(self)
            self._lines.append(obj)
        
    def remove_lines(self, *lines):
        for obj in lines:
            if self in obj._flowgates:
                obj._flowgates.remove(self)
            self._lines.remove(obj)
    # >>> lines

    # <<< capacity_benefit_margin
    # @generated
    def get_capacity_benefit_margin(self):
        """ A flowgate may have 0 to n CBM profile Each season may be a CBM which contains a set of profile data
        """
        return self._capacity_benefit_margin

    def set_capacity_benefit_margin(self, value):
        for p in self._capacity_benefit_margin:
            filtered = [q for q in p.flowgate if q != self]
            self._capacity_benefit_margin._flowgate = filtered
        for r in value:
            if self not in r._flowgate:
                r._flowgate.append(self)
        self._capacity_benefit_margin = value
            
    capacity_benefit_margin = property(get_capacity_benefit_margin, set_capacity_benefit_margin)
    
    def add_capacity_benefit_margin(self, *capacity_benefit_margin):
        for obj in capacity_benefit_margin:
            if self not in obj._flowgate:
                obj._flowgate.append(self)
            self._capacity_benefit_margin.append(obj)
        
    def remove_capacity_benefit_margin(self, *capacity_benefit_margin):
        for obj in capacity_benefit_margin:
            if self in obj._flowgate:
                obj._flowgate.remove(self)
            self._capacity_benefit_margin.remove(obj)
    # >>> capacity_benefit_margin

    # <<< transmission_reliability_margin
    # @generated
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
            if self not in self._transmission_reliability_margin._flowgate:
                self._transmission_reliability_margin._flowgate.append(self)

    transmission_reliability_margin = property(get_transmission_reliability_margin, set_transmission_reliability_margin)
    # >>> transmission_reliability_margin

    # <<< violation_limits
    # @generated
    def get_violation_limits(self):
        """ 
        """
        return self._violation_limits

    def set_violation_limits(self, value):
        for x in self._violation_limits:
            x.flowgate = None
        for y in value:
            y.flowgate = self
        self._violation_limits = value
            
    violation_limits = property(get_violation_limits, set_violation_limits)
    
    def add_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            obj._flowgate = self
            if obj not in self._violation_limits:
                self._violation_limits.append(obj)
        
    def remove_violation_limits(self, *violation_limits):
        for obj in violation_limits:
            obj._flowgate = None
            self._violation_limits.remove(obj)
    # >>> violation_limits

    # <<< power_transormers
    # @generated
    def get_power_transormers(self):
        """ 
        """
        return self._power_transormers

    def set_power_transormers(self, value):
        for p in self._power_transormers:
            filtered = [q for q in p.flowgates if q != self]
            self._power_transormers._flowgates = filtered
        for r in value:
            if self not in r._flowgates:
                r._flowgates.append(self)
        self._power_transormers = value
            
    power_transormers = property(get_power_transormers, set_power_transormers)
    
    def add_power_transormers(self, *power_transormers):
        for obj in power_transormers:
            if self not in obj._flowgates:
                obj._flowgates.append(self)
            self._power_transormers.append(obj)
        
    def remove_power_transormers(self, *power_transormers):
        for obj in power_transormers:
            if self in obj._flowgates:
                obj._flowgates.remove(self)
            self._power_transormers.remove(obj)
    # >>> power_transormers



class NotificationTimeCurve(Curve):
    """ Notification time curve as a function of down time.  Relationship between crew notification time (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    # <<< notification_time_curve
    # @generated
    def __init__(self, generating_bids=None, **kw_args):
        """ Initialises a new 'NotificationTimeCurve' instance.
        """
        
        self._generating_bids = []
        if generating_bids is None:
            self.generating_bids = []
        else:
            self.generating_bids = generating_bids

        super(NotificationTimeCurve, self).__init__(**kw_args)
    # >>> notification_time_curve
        
    # <<< generating_bids
    # @generated
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x.notification_time_curve = None
        for y in value:
            y.notification_time_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._notification_time_curve = self
            if obj not in self._generating_bids:
                self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._notification_time_curve = None
            self._generating_bids.remove(obj)
    # >>> generating_bids



class BaseCaseConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) assigned to a contingency analysis base case. Use CurveSchedule XAxisUnits to specify MW or MVA. To be used only if the BaseCaseConstraintLimit differs from the DefaultConstraintLimit.
    """
    # <<< base_case_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'BaseCaseConstraintLimit' instance.
        """
        
        self._security_constraint_sum = None
        self.security_constraint_sum = security_constraint_sum

        super(BaseCaseConstraintLimit, self).__init__(**kw_args)
    # >>> base_case_constraint_limit
        
    # <<< security_constraint_sum
    # @generated
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
    # >>> security_constraint_sum



class StartUpTimeCurve(Curve):
    """ Startup time curve as a function of down time, where time is specified in seconds.  Relationship between unit startup time (Y1-axis) vs. unit elapsed down time (X-axis).
    """
    # <<< start_up_time_curve
    # @generated
    def __init__(self, generating_bids=None, **kw_args):
        """ Initialises a new 'StartUpTimeCurve' instance.
        """
        
        self._generating_bids = []
        if generating_bids is None:
            self.generating_bids = []
        else:
            self.generating_bids = generating_bids

        super(StartUpTimeCurve, self).__init__(**kw_args)
    # >>> start_up_time_curve
        
    # <<< generating_bids
    # @generated
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x.start_up_time_curve = None
        for y in value:
            y.start_up_time_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_time_curve = self
            if obj not in self._generating_bids:
                self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_time_curve = None
            self._generating_bids.remove(obj)
    # >>> generating_bids



class ResourceGroup(IdentifiedObject):
    """ A logical grouping of resources that are used to model location of types of requirements for ancillary services such as spinning reserve zones, regulation zones, etc.
    """
    # <<< resource_group
    # @generated
    def __init__(self, resource_group_reqs=None, registered_resources=None, **kw_args):
        """ Initialises a new 'ResourceGroup' instance.
        """
        
        self._resource_group_reqs = []
        if resource_group_reqs is None:
            self.resource_group_reqs = []
        else:
            self.resource_group_reqs = resource_group_reqs
        self._registered_resources = []
        if registered_resources is None:
            self.registered_resources = []
        else:
            self.registered_resources = registered_resources

        super(ResourceGroup, self).__init__(**kw_args)
    # >>> resource_group
        
    # <<< resource_group_reqs
    # @generated
    def get_resource_group_reqs(self):
        """ 
        """
        return self._resource_group_reqs

    def set_resource_group_reqs(self, value):
        for x in self._resource_group_reqs:
            x.resource_group = None
        for y in value:
            y.resource_group = self
        self._resource_group_reqs = value
            
    resource_group_reqs = property(get_resource_group_reqs, set_resource_group_reqs)
    
    def add_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            obj._resource_group = self
            if obj not in self._resource_group_reqs:
                self._resource_group_reqs.append(obj)
        
    def remove_resource_group_reqs(self, *resource_group_reqs):
        for obj in resource_group_reqs:
            obj._resource_group = None
            self._resource_group_reqs.remove(obj)
    # >>> resource_group_reqs

    # <<< registered_resources
    # @generated
    def get_registered_resources(self):
        """ 
        """
        return self._registered_resources

    def set_registered_resources(self, value):
        for p in self._registered_resources:
            filtered = [q for q in p.resource_groups if q != self]
            self._registered_resources._resource_groups = filtered
        for r in value:
            if self not in r._resource_groups:
                r._resource_groups.append(self)
        self._registered_resources = value
            
    registered_resources = property(get_registered_resources, set_registered_resources)
    
    def add_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self not in obj._resource_groups:
                obj._resource_groups.append(self)
            self._registered_resources.append(obj)
        
    def remove_registered_resources(self, *registered_resources):
        for obj in registered_resources:
            if self in obj._resource_groups:
                obj._resource_groups.remove(self)
            self._registered_resources.remove(obj)
    # >>> registered_resources



class ReserveReqCurve(RegularIntervalSchedule):
    """ A curve relating  reserve requirement versus time, showing the values of a specific reserve requirement for each unit of the period covered. The  curve can be based on 'absolute' time or on 'normalized' time.  X is time, typically expressed in absolute time Y1 is reserve requirement, typically expressed in MW
    """
    # <<< reserve_req_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'ReserveReqCurve' instance.
        """
        
        self._reserve_req = None
        self.reserve_req = reserve_req

        super(ReserveReqCurve, self).__init__(**kw_args)
    # >>> reserve_req_curve
        
    # <<< reserve_req
    # @generated
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
    # >>> reserve_req



class StartUpCostCurve(Curve):
    """ Startup costs and time as a function of down time.  Relationship between unit startup cost (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    # <<< start_up_cost_curve
    # @generated
    def __init__(self, generating_bids=None, registered_generators=None, **kw_args):
        """ Initialises a new 'StartUpCostCurve' instance.
        """
        
        self._generating_bids = []
        if generating_bids is None:
            self.generating_bids = []
        else:
            self.generating_bids = generating_bids
        self._registered_generators = []
        if registered_generators is None:
            self.registered_generators = []
        else:
            self.registered_generators = registered_generators

        super(StartUpCostCurve, self).__init__(**kw_args)
    # >>> start_up_cost_curve
        
    # <<< generating_bids
    # @generated
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x.start_up_cost_curve = None
        for y in value:
            y.start_up_cost_curve = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_cost_curve = self
            if obj not in self._generating_bids:
                self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._start_up_cost_curve = None
            self._generating_bids.remove(obj)
    # >>> generating_bids

    # <<< registered_generators
    # @generated
    def get_registered_generators(self):
        """ 
        """
        return self._registered_generators

    def set_registered_generators(self, value):
        for p in self._registered_generators:
            filtered = [q for q in p.start_up_cost_curves if q != self]
            self._registered_generators._start_up_cost_curves = filtered
        for r in value:
            if self not in r._start_up_cost_curves:
                r._start_up_cost_curves.append(self)
        self._registered_generators = value
            
    registered_generators = property(get_registered_generators, set_registered_generators)
    
    def add_registered_generators(self, *registered_generators):
        for obj in registered_generators:
            if self not in obj._start_up_cost_curves:
                obj._start_up_cost_curves.append(self)
            self._registered_generators.append(obj)
        
    def remove_registered_generators(self, *registered_generators):
        for obj in registered_generators:
            if self in obj._start_up_cost_curves:
                obj._start_up_cost_curves.remove(self)
            self._registered_generators.remove(obj)
    # >>> registered_generators



class ViolationLimit(Limit):
    """ A type of limit that indicates if it is enforced and, through association, the organisation responsible for setting the limit.
    """
    # <<< violation_limit
    # @generated
    def __init__(self, enforced=False, season=None, organisations=None, measurement=None, flowgate=None, **kw_args):
        """ Initialises a new 'ViolationLimit' instance.
        """
        # True if limit is enforced. 
        self.enforced = enforced
        
        self._season = None
        self.season = season
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations
        self._measurement = None
        self.measurement = measurement
        self._flowgate = None
        self.flowgate = flowgate

        super(ViolationLimit, self).__init__(**kw_args)
    # >>> violation_limit
        
    # <<< season
    # @generated
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
            if self not in self._season._violation_limits:
                self._season._violation_limits.append(self)

    season = property(get_season, set_season)
    # >>> season

    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self._organisations:
            filtered = [q for q in p.violation_limits if q != self]
            self._organisations._violation_limits = filtered
        for r in value:
            if self not in r._violation_limits:
                r._violation_limits.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._violation_limits:
                obj._violation_limits.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._violation_limits:
                obj._violation_limits.remove(self)
            self._organisations.remove(obj)
    # >>> organisations

    # <<< measurement
    # @generated
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
            if self not in self._measurement._violation_limits:
                self._measurement._violation_limits.append(self)

    measurement = property(get_measurement, set_measurement)
    # >>> measurement

    # <<< flowgate
    # @generated
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
            if self not in self._flowgate._violation_limits:
                self._flowgate._violation_limits.append(self)

    flowgate = property(get_flowgate, set_flowgate)
    # >>> flowgate



class LossPenaltyFactor(MarketFactors):
    """ Loss penalty factor applied to a ConnectivityNode for a given time interval.
    """
    # <<< loss_penalty_factor
    # @generated
    def __init__(self, connectivity_nodes=None, **kw_args):
        """ Initialises a new 'LossPenaltyFactor' instance.
        """
        
        self._connectivity_nodes = []
        if connectivity_nodes is None:
            self.connectivity_nodes = []
        else:
            self.connectivity_nodes = connectivity_nodes

        super(LossPenaltyFactor, self).__init__(**kw_args)
    # >>> loss_penalty_factor
        
    # <<< connectivity_nodes
    # @generated
    def get_connectivity_nodes(self):
        """ 
        """
        return self._connectivity_nodes

    def set_connectivity_nodes(self, value):
        for p in self._connectivity_nodes:
            filtered = [q for q in p.loss_penalty_factors if q != self]
            self._connectivity_nodes._loss_penalty_factors = filtered
        for r in value:
            if self not in r._loss_penalty_factors:
                r._loss_penalty_factors.append(self)
        self._connectivity_nodes = value
            
    connectivity_nodes = property(get_connectivity_nodes, set_connectivity_nodes)
    
    def add_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            if self not in obj._loss_penalty_factors:
                obj._loss_penalty_factors.append(self)
            self._connectivity_nodes.append(obj)
        
    def remove_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            if self in obj._loss_penalty_factors:
                obj._loss_penalty_factors.remove(self)
            self._connectivity_nodes.remove(obj)
    # >>> connectivity_nodes



class ProductBidClearing(MarketFactors):
    """ Product Bid clearing results posted for a given settlement period.
    """
    # <<< product_bid_clearing
    # @generated
    def __init__(self, cleared_mw=0.0, product_bids=None, **kw_args):
        """ Initialises a new 'ProductBidClearing' instance.
        """
        # Cleared MWs. 
        self.cleared_mw = cleared_mw
        
        self._product_bids = []
        if product_bids is None:
            self.product_bids = []
        else:
            self.product_bids = product_bids

        super(ProductBidClearing, self).__init__(**kw_args)
    # >>> product_bid_clearing
        
    # <<< product_bids
    # @generated
    def get_product_bids(self):
        """ 
        """
        return self._product_bids

    def set_product_bids(self, value):
        for x in self._product_bids:
            x.product_bid_clearing = None
        for y in value:
            y.product_bid_clearing = self
        self._product_bids = value
            
    product_bids = property(get_product_bids, set_product_bids)
    
    def add_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._product_bid_clearing = self
            if obj not in self._product_bids:
                self._product_bids.append(obj)
        
    def remove_product_bids(self, *product_bids):
        for obj in product_bids:
            obj._product_bid_clearing = None
            self._product_bids.remove(obj)
    # >>> product_bids



class AncillaryServiceClearing(MarketFactors):
    """ Ancillary services clearing results are posted for a given settlement period.
    """
    # <<< ancillary_service_clearing
    # @generated
    def __init__(self, mcp=0.0, cleared_mw=0.0, commodity_type='', market_case_clearing=None, **kw_args):
        """ Initialises a new 'AncillaryServiceClearing' instance.
        """
        # Market clearing price (MCP) in monetary units. 
        self.mcp = mcp
        # Cleared MWs. 
        self.cleared_mw = cleared_mw
        # Requirement type:  'En' - Energy  'Ru' - Regulation Up  'Rd' - Regulation Dn  'Sr' - Spinning Reserve  'Nr' - Non-Spinning Reserve  'Or' - Operating Reserve 
        self.commodity_type = commodity_type
        
        self._market_case_clearing = None
        self.market_case_clearing = market_case_clearing

        super(AncillaryServiceClearing, self).__init__(**kw_args)
    # >>> ancillary_service_clearing
        
    # <<< market_case_clearing
    # @generated
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
            if self not in self._market_case_clearing._ancillary_service_clearing:
                self._market_case_clearing._ancillary_service_clearing.append(self)

    market_case_clearing = property(get_market_case_clearing, set_market_case_clearing)
    # >>> market_case_clearing



class ReserveReq(ResourceGroupReq):
    """ Requirements for minimum amount of reserve and/or regulation to be supplied by a set of qualified resources.
    """
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
        
    # <<< sensitivity_price_curve
    # @generated
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
    # >>> sensitivity_price_curve

    # <<< market_product
    # @generated
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
            if self not in self._market_product._reserve_reqs:
                self._market_product._reserve_reqs.append(self)

    market_product = property(get_market_product, set_market_product)
    # >>> market_product

    # <<< reserve_req_curve
    # @generated
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
    # >>> reserve_req_curve



class RegisteredGenerator(RegisteredResource):
    # <<< registered_generator
    # @generated
    def __init__(self, minimum_operating_mw=0.0, maximum_operating_mw=0.0, high_control_limit=0.0, low_control_limit=0.0, maximum_allowable_spinning_reserve=0.0, start_up_cost_curves=None, generating_bids=None, unit_initial_conditions=None, ramp_rate_curves=None, generating_unit=None, **kw_args):
        """ Initialises a new 'RegisteredGenerator' instance.
        """
        # This is the minimum operating MW limit the dispatcher can enter for this unit. 
        self.minimum_operating_mw = minimum_operating_mw
        # This is the maximum operating MW limit the dispatcher can enter for this unit 
        self.maximum_operating_mw = maximum_operating_mw
        # High limit for secondary (AGC) control 
        self.high_control_limit = high_control_limit
        # Low limit for secondary (AGC) control 
        self.low_control_limit = low_control_limit
        # Maximum allowable spinning reserve. Spinning reserve will never be considered greater than this value regardless of the current operating point. 
        self.maximum_allowable_spinning_reserve = maximum_allowable_spinning_reserve
        
        self._start_up_cost_curves = []
        if start_up_cost_curves is None:
            self.start_up_cost_curves = []
        else:
            self.start_up_cost_curves = start_up_cost_curves
        self._generating_bids = []
        if generating_bids is None:
            self.generating_bids = []
        else:
            self.generating_bids = generating_bids
        self._unit_initial_conditions = []
        if unit_initial_conditions is None:
            self.unit_initial_conditions = []
        else:
            self.unit_initial_conditions = unit_initial_conditions
        self._ramp_rate_curves = []
        if ramp_rate_curves is None:
            self.ramp_rate_curves = []
        else:
            self.ramp_rate_curves = ramp_rate_curves
        self._generating_unit = None
        self.generating_unit = generating_unit

        super(RegisteredGenerator, self).__init__(**kw_args)
    # >>> registered_generator
        
    # <<< start_up_cost_curves
    # @generated
    def get_start_up_cost_curves(self):
        """ 
        """
        return self._start_up_cost_curves

    def set_start_up_cost_curves(self, value):
        for p in self._start_up_cost_curves:
            filtered = [q for q in p.registered_generators if q != self]
            self._start_up_cost_curves._registered_generators = filtered
        for r in value:
            if self not in r._registered_generators:
                r._registered_generators.append(self)
        self._start_up_cost_curves = value
            
    start_up_cost_curves = property(get_start_up_cost_curves, set_start_up_cost_curves)
    
    def add_start_up_cost_curves(self, *start_up_cost_curves):
        for obj in start_up_cost_curves:
            if self not in obj._registered_generators:
                obj._registered_generators.append(self)
            self._start_up_cost_curves.append(obj)
        
    def remove_start_up_cost_curves(self, *start_up_cost_curves):
        for obj in start_up_cost_curves:
            if self in obj._registered_generators:
                obj._registered_generators.remove(self)
            self._start_up_cost_curves.remove(obj)
    # >>> start_up_cost_curves

    # <<< generating_bids
    # @generated
    def get_generating_bids(self):
        """ 
        """
        return self._generating_bids

    def set_generating_bids(self, value):
        for x in self._generating_bids:
            x.registered_generator = None
        for y in value:
            y.registered_generator = self
        self._generating_bids = value
            
    generating_bids = property(get_generating_bids, set_generating_bids)
    
    def add_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._registered_generator = self
            if obj not in self._generating_bids:
                self._generating_bids.append(obj)
        
    def remove_generating_bids(self, *generating_bids):
        for obj in generating_bids:
            obj._registered_generator = None
            self._generating_bids.remove(obj)
    # >>> generating_bids

    # <<< unit_initial_conditions
    # @generated
    def get_unit_initial_conditions(self):
        """ 
        """
        return self._unit_initial_conditions

    def set_unit_initial_conditions(self, value):
        for x in self._unit_initial_conditions:
            x.generating_unit = None
        for y in value:
            y.generating_unit = self
        self._unit_initial_conditions = value
            
    unit_initial_conditions = property(get_unit_initial_conditions, set_unit_initial_conditions)
    
    def add_unit_initial_conditions(self, *unit_initial_conditions):
        for obj in unit_initial_conditions:
            obj._generating_unit = self
            if obj not in self._unit_initial_conditions:
                self._unit_initial_conditions.append(obj)
        
    def remove_unit_initial_conditions(self, *unit_initial_conditions):
        for obj in unit_initial_conditions:
            obj._generating_unit = None
            self._unit_initial_conditions.remove(obj)
    # >>> unit_initial_conditions

    # <<< ramp_rate_curves
    # @generated
    def get_ramp_rate_curves(self):
        """ 
        """
        return self._ramp_rate_curves

    def set_ramp_rate_curves(self, value):
        for p in self._ramp_rate_curves:
            filtered = [q for q in p.generating_unit if q != self]
            self._ramp_rate_curves._generating_unit = filtered
        for r in value:
            if self not in r._generating_unit:
                r._generating_unit.append(self)
        self._ramp_rate_curves = value
            
    ramp_rate_curves = property(get_ramp_rate_curves, set_ramp_rate_curves)
    
    def add_ramp_rate_curves(self, *ramp_rate_curves):
        for obj in ramp_rate_curves:
            if self not in obj._generating_unit:
                obj._generating_unit.append(self)
            self._ramp_rate_curves.append(obj)
        
    def remove_ramp_rate_curves(self, *ramp_rate_curves):
        for obj in ramp_rate_curves:
            if self in obj._generating_unit:
                obj._generating_unit.remove(self)
            self._ramp_rate_curves.remove(obj)
    # >>> ramp_rate_curves

    # <<< generating_unit
    # @generated
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
    # >>> generating_unit



class SecurityConstraintSum(MarketFactors):
    """ Typically provided by RTO systems, constraints identified in both base case and critical contingency cases have to be transferred. A constraint has N (>=1) constraint terms. A term is represented by an instance of TerminalConstraintTerm.  The constraint expression is: minValue <= c1*x1 + c2*x2 + .... cn*xn + k <= maxValue where: - cn is ConstraintTerm.factor  - xn is the flow at the terminal Flow into the associated equipment is positive for the purpose of ConnectivityNode NodeConstraintTerm  k is SecurityConstraintsLinear.resourceMW The units of k are assumed to be same as the units of the flows, xn.  The constants, cn, are dimensionless. With these conventions, cn and k are all positive for a typical constraint such as 'weighted sum of generation must be less than limit'. Furthermore, cn are all 1.0 for a case such as 'interface flow must be less than limit', assuming the terminals are chosen on the importing side of the interface.
    """
    # <<< security_constraint_sum
    # @generated
    def __init__(self, constraint_terms=None, rto=None, contingency_constraint_limits=None, default_constraint_limit=None, base_case_constraint_limit=None, **kw_args):
        """ Initialises a new 'SecurityConstraintSum' instance.
        """
        
        self._constraint_terms = []
        if constraint_terms is None:
            self.constraint_terms = []
        else:
            self.constraint_terms = constraint_terms
        self._rto = None
        self.rto = rto
        self._contingency_constraint_limits = []
        if contingency_constraint_limits is None:
            self.contingency_constraint_limits = []
        else:
            self.contingency_constraint_limits = contingency_constraint_limits
        self._default_constraint_limit = None
        self.default_constraint_limit = default_constraint_limit
        self._base_case_constraint_limit = None
        self.base_case_constraint_limit = base_case_constraint_limit

        super(SecurityConstraintSum, self).__init__(**kw_args)
    # >>> security_constraint_sum
        
    # <<< constraint_terms
    # @generated
    def get_constraint_terms(self):
        """ 
        """
        return self._constraint_terms

    def set_constraint_terms(self, value):
        for x in self._constraint_terms:
            x.security_constraint_sum = None
        for y in value:
            y.security_constraint_sum = self
        self._constraint_terms = value
            
    constraint_terms = property(get_constraint_terms, set_constraint_terms)
    
    def add_constraint_terms(self, *constraint_terms):
        for obj in constraint_terms:
            obj._security_constraint_sum = self
            if obj not in self._constraint_terms:
                self._constraint_terms.append(obj)
        
    def remove_constraint_terms(self, *constraint_terms):
        for obj in constraint_terms:
            obj._security_constraint_sum = None
            self._constraint_terms.remove(obj)
    # >>> constraint_terms

    # <<< rto
    # @generated
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
            if self not in self._rto._security_constraints_linear:
                self._rto._security_constraints_linear.append(self)

    rto = property(get_rto, set_rto)
    # >>> rto

    # <<< contingency_constraint_limits
    # @generated
    def get_contingency_constraint_limits(self):
        """ 
        """
        return self._contingency_constraint_limits

    def set_contingency_constraint_limits(self, value):
        for x in self._contingency_constraint_limits:
            x.security_constraint_sum = None
        for y in value:
            y.security_constraint_sum = self
        self._contingency_constraint_limits = value
            
    contingency_constraint_limits = property(get_contingency_constraint_limits, set_contingency_constraint_limits)
    
    def add_contingency_constraint_limits(self, *contingency_constraint_limits):
        for obj in contingency_constraint_limits:
            obj._security_constraint_sum = self
            if obj not in self._contingency_constraint_limits:
                self._contingency_constraint_limits.append(obj)
        
    def remove_contingency_constraint_limits(self, *contingency_constraint_limits):
        for obj in contingency_constraint_limits:
            obj._security_constraint_sum = None
            self._contingency_constraint_limits.remove(obj)
    # >>> contingency_constraint_limits

    # <<< default_constraint_limit
    # @generated
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
    # >>> default_constraint_limit

    # <<< base_case_constraint_limit
    # @generated
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
    # >>> base_case_constraint_limit



class RegisteredLoad(RegisteredResource):
    # <<< registered_load
    # @generated
    def __init__(self, load_area=None, load_bids=None, **kw_args):
        """ Initialises a new 'RegisteredLoad' instance.
        """
        
        self._load_area = None
        self.load_area = load_area
        self._load_bids = []
        if load_bids is None:
            self.load_bids = []
        else:
            self.load_bids = load_bids

        super(RegisteredLoad, self).__init__(**kw_args)
    # >>> registered_load
        
    # <<< load_area
    # @generated
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
            if self not in self._load_area._registered_loads:
                self._load_area._registered_loads.append(self)

    load_area = property(get_load_area, set_load_area)
    # >>> load_area

    # <<< load_bids
    # @generated
    def get_load_bids(self):
        """ 
        """
        return self._load_bids

    def set_load_bids(self, value):
        for x in self._load_bids:
            x.registered_load = None
        for y in value:
            y.registered_load = self
        self._load_bids = value
            
    load_bids = property(get_load_bids, set_load_bids)
    
    def add_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._registered_load = self
            if obj not in self._load_bids:
                self._load_bids.append(obj)
        
    def remove_load_bids(self, *load_bids):
        for obj in load_bids:
            obj._registered_load = None
            self._load_bids.remove(obj)
    # >>> load_bids



class ResourceBid(Bid):
    """ Energy bid for generation, load, or virtual type for the whole of the market-trading period (i.e., one day in day ahead market or one hour in the real time market)
    """
    # <<< resource_bid
    # @generated
    def __init__(self, energy_max_day=0.0, commodity_type='', start_ups_max_week=0, shut_downs_max_week=0, shut_downs_max_day=0, start_ups_max_day=0, energy_min_day=0.0, virtual=False, **kw_args):
        """ Initialises a new 'ResourceBid' instance.
        """
        # Maximum amount of energy per day which can be produced during the trading period in MWh 
        self.energy_max_day = energy_max_day
        # Energy product (commodity) type:  'En' - Energy  'Ru' - Regulation Up  'Rd' - Regulation Dn  'Sr' - Spinning Reserve  'Nr' - Non-Spinning Reserve  'Or' - Operating Reserve 
        self.commodity_type = commodity_type
        # Maximum number of startups per week. 
        self.start_ups_max_week = start_ups_max_week
        # Maximum number of shutdowns per week. 
        self.shut_downs_max_week = shut_downs_max_week
        # Maximum number of shutdowns per day. 
        self.shut_downs_max_day = shut_downs_max_day
        # Maximum number of startups per day. 
        self.start_ups_max_day = start_ups_max_day
        # Minimum amount of energy per day which has to be produced during the trading period in MWh 
        self.energy_min_day = energy_min_day
        # True if bid is virtual.  Bid is assumed to be non-virtual if attribute is absent 
        self.virtual = virtual
        

        super(ResourceBid, self).__init__(**kw_args)
    # >>> resource_bid
        


class TerminalConstraintTerm(ConstraintTerm):
    """ A constraint term associated with a specific terminal on a physical piece of equipment.
    """
    # <<< terminal_constraint_term
    # @generated
    def __init__(self, terminal=None, **kw_args):
        """ Initialises a new 'TerminalConstraintTerm' instance.
        """
        
        self._terminal = None
        self.terminal = terminal

        super(TerminalConstraintTerm, self).__init__(**kw_args)
    # >>> terminal_constraint_term
        
    # <<< terminal
    # @generated
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
            if self not in self._terminal._terminal_constraints:
                self._terminal._terminal_constraints.append(self)

    terminal = property(get_terminal, set_terminal)
    # >>> terminal



class PnodeClearing(MarketFactors):
    """ Pricing node clearing results posted for a given settlement period.
    """
    # <<< pnode_clearing
    # @generated
    def __init__(self, cost_lmp=0.0, loss_lmp=0.0, congest_lmp=0.0, pnode=None, **kw_args):
        """ Initialises a new 'PnodeClearing' instance.
        """
        # Cost component of Locational Marginal Pricing (LMP) in monetary units per MW. 
        self.cost_lmp = cost_lmp
        # Loss component of Location Marginal Price (LMP) in monetary units per MW. 
        self.loss_lmp = loss_lmp
        # Congestion component of Location Marginal Price (LMP) in monetary units per MW. 
        self.congest_lmp = congest_lmp
        
        self._pnode = None
        self.pnode = pnode

        super(PnodeClearing, self).__init__(**kw_args)
    # >>> pnode_clearing
        
    # <<< pnode
    # @generated
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
    # >>> pnode



class TransactionBid(Bid):
    """ Bilateral or scheduled transactions for energy and ancillary services considered by market clearing process
    """
    # <<< transaction_bid
    # @generated
    def __init__(self, energy_trans_id='', delivery_pnode=None, energy_trans_id=None, energy_profiles=None, receipt_pnode=None, **kw_args):
        """ Initialises a new 'TransactionBid' instance.
        """
 
        self.energy_trans_id = energy_trans_id
        
        self._delivery_pnode = None
        self.delivery_pnode = delivery_pnode
        self._energy_trans_id = None
        self.energy_trans_id = energy_trans_id
        self._energy_profiles = []
        if energy_profiles is None:
            self.energy_profiles = []
        else:
            self.energy_profiles = energy_profiles
        self._receipt_pnode = None
        self.receipt_pnode = receipt_pnode

        super(TransactionBid, self).__init__(**kw_args)
    # >>> transaction_bid
        
    # <<< delivery_pnode
    # @generated
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
            if self not in self._delivery_pnode._delivery_transaction_bids:
                self._delivery_pnode._delivery_transaction_bids.append(self)

    delivery_pnode = property(get_delivery_pnode, set_delivery_pnode)
    # >>> delivery_pnode

    # <<< energy_trans_id
    # @generated
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
            if self not in self._energy_trans_id._energy_trans_id:
                self._energy_trans_id._energy_trans_id.append(self)

    energy_trans_id = property(get_energy_trans_id, set_energy_trans_id)
    # >>> energy_trans_id

    # <<< energy_profiles
    # @generated
    def get_energy_profiles(self):
        """ 
        """
        return self._energy_profiles

    def set_energy_profiles(self, value):
        for x in self._energy_profiles:
            x.transaction_bid = None
        for y in value:
            y.transaction_bid = self
        self._energy_profiles = value
            
    energy_profiles = property(get_energy_profiles, set_energy_profiles)
    
    def add_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._transaction_bid = self
            if obj not in self._energy_profiles:
                self._energy_profiles.append(obj)
        
    def remove_energy_profiles(self, *energy_profiles):
        for obj in energy_profiles:
            obj._transaction_bid = None
            self._energy_profiles.remove(obj)
    # >>> energy_profiles

    # <<< receipt_pnode
    # @generated
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
            if self not in self._receipt_pnode._receipt_transaction_bids:
                self._receipt_pnode._receipt_transaction_bids.append(self)

    receipt_pnode = property(get_receipt_pnode, set_receipt_pnode)
    # >>> receipt_pnode



class NodeConstraintTerm(ConstraintTerm):
    """ To be used only to constrain a quantity that cannot be associated with a terminal. For example, a registered generating unit that is not electrically connected to the network.
    """
    # <<< node_constraint_term
    # @generated
    def __init__(self, connectivity_node=None, **kw_args):
        """ Initialises a new 'NodeConstraintTerm' instance.
        """
        
        self._connectivity_node = None
        self.connectivity_node = connectivity_node

        super(NodeConstraintTerm, self).__init__(**kw_args)
    # >>> node_constraint_term
        
    # <<< connectivity_node
    # @generated
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
            if self not in self._connectivity_node._node_constraint_terms:
                self._connectivity_node._node_constraint_terms.append(self)

    connectivity_node = property(get_connectivity_node, set_connectivity_node)
    # >>> connectivity_node



class SecurityConstraintsClearing(MarketFactors):
    """ Binding security constrained clearing results posted for a given settlement period.
    """
    # <<< security_constraints_clearing
    # @generated
    def __init__(self, mw_flow=0.0, shadow_price=0.0, mw_limit=0.0, **kw_args):
        """ Initialises a new 'SecurityConstraintsClearing' instance.
        """
        # Optimal MW flow 
        self.mw_flow = mw_flow
        # Security constraint shadow price. 
        self.shadow_price = shadow_price
        # Binding MW limit. 
        self.mw_limit = mw_limit
        

        super(SecurityConstraintsClearing, self).__init__(**kw_args)
    # >>> security_constraints_clearing
        


class MarketCaseClearing(MarketFactors):
    """ Market case clearing results are posted for a given settlement period.
    """
    # <<< market_case_clearing
    # @generated
    def __init__(self, case_type='', posted_date='', modified_date='', ancillary_service_clearing=None, **kw_args):
        """ Initialises a new 'MarketCaseClearing' instance.
        """
        # Settlement period:  'DA - Bid-in'  'DA - Reliability'  'DA - Amp1'  'DA - Amp2'  'RT - Ex-Ante'  'RT - Ex-Post'  'RT - Amp1'  'RT - Amp2' 
        self.case_type = case_type
        # Bid clearing results posted time and date. 
        self.posted_date = posted_date
        # Last time and date clearing results were manually modified. 
        self.modified_date = modified_date
        
        self._ancillary_service_clearing = []
        if ancillary_service_clearing is None:
            self.ancillary_service_clearing = []
        else:
            self.ancillary_service_clearing = ancillary_service_clearing

        super(MarketCaseClearing, self).__init__(**kw_args)
    # >>> market_case_clearing
        
    # <<< ancillary_service_clearing
    # @generated
    def get_ancillary_service_clearing(self):
        """ 
        """
        return self._ancillary_service_clearing

    def set_ancillary_service_clearing(self, value):
        for x in self._ancillary_service_clearing:
            x.market_case_clearing = None
        for y in value:
            y.market_case_clearing = self
        self._ancillary_service_clearing = value
            
    ancillary_service_clearing = property(get_ancillary_service_clearing, set_ancillary_service_clearing)
    
    def add_ancillary_service_clearing(self, *ancillary_service_clearing):
        for obj in ancillary_service_clearing:
            obj._market_case_clearing = self
            if obj not in self._ancillary_service_clearing:
                self._ancillary_service_clearing.append(obj)
        
    def remove_ancillary_service_clearing(self, *ancillary_service_clearing):
        for obj in ancillary_service_clearing:
            obj._market_case_clearing = None
            self._ancillary_service_clearing.remove(obj)
    # >>> ancillary_service_clearing



class LoadBid(ResourceBid):
    # <<< load_bid
    # @generated
    def __init__(self, shutdown_cost=0.0, req_notice_time=0.0, min_load_reduction_interval=0.0, min_time_bet_load_red=0.0, min_load_reduction_cost=0.0, min_load_reduction=0.0, min_load=0.0, registered_load=None, load_reduction_price_curve=None, **kw_args):
        """ Initialises a new 'LoadBid' instance.
        """
        # The fixed cost associated with committing a load reduction. 
        self.shutdown_cost = shutdown_cost
        # Time period that is required from an order to reduce a load to the time that it takes to get to the minimum load reduction. 
        self.req_notice_time = req_notice_time
        # Shortest period load reduction must be maintained before load can be restored to normal levels. 
        self.min_load_reduction_interval = min_load_reduction_interval
        # Shortest time that load must be left at normal levels before a new load reduction. 
        self.min_time_bet_load_red = min_time_bet_load_red
        # Cost in $ at the minimum reduced load 
        self.min_load_reduction_cost = min_load_reduction_cost
        # Minimum MW for a load reduction (e.g., MW rating of a discrete pump. 
        self.min_load_reduction = min_load_reduction
        # Minimum MW load below which it may not be reduced. 
        self.min_load = min_load
        
        self._registered_load = None
        self.registered_load = registered_load
        self._load_reduction_price_curve = None
        self.load_reduction_price_curve = load_reduction_price_curve

        super(LoadBid, self).__init__(**kw_args)
    # >>> load_bid
        
    # <<< registered_load
    # @generated
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
            if self not in self._registered_load._load_bids:
                self._registered_load._load_bids.append(self)

    registered_load = property(get_registered_load, set_registered_load)
    # >>> registered_load

    # <<< load_reduction_price_curve
    # @generated
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
            if self not in self._load_reduction_price_curve._load_bids:
                self._load_reduction_price_curve._load_bids.append(self)

    load_reduction_price_curve = property(get_load_reduction_price_curve, set_load_reduction_price_curve)
    # >>> load_reduction_price_curve



class GeneratingBid(ResourceBid):
    # <<< generating_bid
    # @generated
    def __init__(self, minimum_economic_mw=0.0, operating_mode='', minimum_down_time=0.0, maximum_economic_mw=0.0, max_emergency_mw=0.0, up_time_max=0.0, startup_time=0.0, down_time_max=0.0, no_load_cost=0.0, notification_time=0.0, start_up_type=0, up_time_min=0.0, min_emergency_mw=0.0, bid_set=None, notification_time_curve=None, start_up_cost_curve=None, start_up_time_curve=None, registered_generator=None, **kw_args):
        """ Initialises a new 'GeneratingBid' instance.
        """
        # Low economic MW limit that must be greater than or equal to the minimum operating MW limit 
        self.minimum_economic_mw = minimum_economic_mw
        # Bid operating mode ('C' - cycling, 'F' - fixed, 'M' - must run, 'U' - unavailable) 
        self.operating_mode = operating_mode
        # Minimum time interval between unit shutdown and startup 
        self.minimum_down_time = minimum_down_time
        # Maximum high economic MW limit, that should not exceed the maximum operating MW limit 
        self.maximum_economic_mw = maximum_economic_mw
        # Power rating available for unit under emergency conditions greater than or equal to maximum economic limit. 
        self.max_emergency_mw = max_emergency_mw
        # Maximum up time. 
        self.up_time_max = up_time_max
        # Time it takes to get the unit on-line, from the time that the prime mover mechanical power is applied 
        self.startup_time = startup_time
        # Maximum down time. 
        self.down_time_max = down_time_max
        # Resource fixed no load cost. 
        self.no_load_cost = no_load_cost
        # Time required for crew notification prior to start up of the unit. 
        self.notification_time = notification_time
        # Resource startup type:  1 - Fixed startup time and fixed startup cost  2 - Startup time as a function of down time and fixed startup cost  3 - Startup cost as a function of down time 
        self.start_up_type = start_up_type
        # Minimum up time. 
        self.up_time_min = up_time_min
        # Minimum power rating for unit under emergency conditions, which is less than or equal to the economic minimum. 
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
        
    # <<< bid_set
    # @generated
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
            if self not in self._bid_set._generating_bids:
                self._bid_set._generating_bids.append(self)

    bid_set = property(get_bid_set, set_bid_set)
    # >>> bid_set

    # <<< notification_time_curve
    # @generated
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
            if self not in self._notification_time_curve._generating_bids:
                self._notification_time_curve._generating_bids.append(self)

    notification_time_curve = property(get_notification_time_curve, set_notification_time_curve)
    # >>> notification_time_curve

    # <<< start_up_cost_curve
    # @generated
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
            if self not in self._start_up_cost_curve._generating_bids:
                self._start_up_cost_curve._generating_bids.append(self)

    start_up_cost_curve = property(get_start_up_cost_curve, set_start_up_cost_curve)
    # >>> start_up_cost_curve

    # <<< start_up_time_curve
    # @generated
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
            if self not in self._start_up_time_curve._generating_bids:
                self._start_up_time_curve._generating_bids.append(self)

    start_up_time_curve = property(get_start_up_time_curve, set_start_up_time_curve)
    # >>> start_up_time_curve

    # <<< registered_generator
    # @generated
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
            if self not in self._registered_generator._generating_bids:
                self._registered_generator._generating_bids.append(self)

    registered_generator = property(get_registered_generator, set_registered_generator)
    # >>> registered_generator



# <<< market_operations
# @generated
# >>> market_operations

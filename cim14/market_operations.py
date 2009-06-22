# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.informative.inf_erpsupport import ErpOrganisation
from cim14.iec61970.core import Curve
from cim14.iec61968.common import Agreement
from cim14 import Root
from cim14.energy_scheduling import Profile
from cim14.iec61968.common import Document
from cim14.iec61970.core import PowerSystemResource
from cim14.iec61970.core import RegularIntervalSchedule
from cim14.iec61970.meas import Limit

# <<< imports
# @generated
# >>> imports

class RegisteredResource(IdentifiedObject):
    """ A resource that is registered through the RTO participant registration system. Examples include generating unit, customer meter, and a non-physical generator or load.
    """
    # Unique name obtained via RTO registration
    rto_id = ''

    resource_groups = []

    organisation = None

    # A registered resource injects power at one or more connectivity nodes related to a pnode
    pnode = None

    meters = []

    # A registered resource is eligible to bid market products
    market_products = []

    markets = []

    # <<< registered_resource
    # @generated
    def __init__(self, rto_id='', resource_groups=[], organisation=None, pnode=None, meters=[], market_products=[], markets=[], **kw_args):
        """ Initialises a new 'RegisteredResource' instance.
        """
        self.rto_id = rto_id
        self.resource_groups = resource_groups
        self.organisation = organisation
        self.pnode = pnode
        self.meters = meters
        self.market_products = market_products
        self.markets = markets

        super(RegisteredResource, self).__init__(**kw_args)
    # >>> registered_resource


class RTO(ErpOrganisation):
    """ Regional transmission operator.
    """
    security_constraints_linear = []

    resource_group_reqs = []

    security_constraints = []

    markets = []

    pnodes = []

    # <<< rto
    # @generated
    def __init__(self, security_constraints_linear=[], resource_group_reqs=[], security_constraints=[], markets=[], pnodes=[], **kw_args):
        """ Initialises a new 'RTO' instance.
        """
        self.security_constraints_linear = security_constraints_linear
        self.resource_group_reqs = resource_group_reqs
        self.security_constraints = security_constraints
        self.markets = markets
        self.pnodes = pnodes

        super(RTO, self).__init__(**kw_args)
    # >>> rto


class Meter(IdentifiedObject):
    """ This is generic logical meter object.
    """
    registered_resource = None

    # <<< meter
    # @generated
    def __init__(self, registered_resource=None, **kw_args):
        """ Initialises a new 'Meter' instance.
        """
        self.registered_resource = registered_resource

        super(Meter, self).__init__(**kw_args)
    # >>> meter


class ContingencyConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA
        limit (Y1 and Y2, respectively) assigned to a constraint for a specific
        contingency. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
    mwlimit_schedules = None

    contingency = None

    security_constraint_sum = None

    # <<< contingency_constraint_limit
    # @generated
    def __init__(self, mwlimit_schedules=None, contingency=None, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'ContingencyConstraintLimit' instance.
        """
        self.mwlimit_schedules = mwlimit_schedules
        self.contingency = contingency
        self.security_constraint_sum = security_constraint_sum

        super(ContingencyConstraintLimit, self).__init__(**kw_args)
    # >>> contingency_constraint_limit


class BidPriceCurve(Curve):
    """ Relationship between unit operating price in $/hour (Y-axis) and unit
        output in MW (X-axis).
    """
    product_bids = []

    # <<< bid_price_curve
    # @generated
    def __init__(self, product_bids=[], **kw_args):
        """ Initialises a new 'BidPriceCurve' instance.
        """
        self.product_bids = product_bids

        super(BidPriceCurve, self).__init__(**kw_args)
    # >>> bid_price_curve


class FTR(Agreement):
    """ Financial Transmission Rights (FTR) regarding transmission capacity at a flowgate.
    """
    # Peak, Off-peak, 24-hour
    klass = ''

    # Type of rights being offered (product) allowed to be auctioned (option, obligation).
    ftr_type = ''

    # Fixed (covers re-configuration, grandfathering) or Optimized (up for sale/purchase
    optimized = ''

    # Buy, Sell
    action = ''

    # Quantity, typically MWs - Seller owns all rights being offered, MWs over time on same Point of Receipt, Point of Delivery, or Resource.
    base_energy = ''

    flowgate = None

    pnodes = []

    energy_price_curve = None

    # <<< ftr
    # @generated
    def __init__(self, klass='', ftr_type='', optimized='', action='', base_energy='', flowgate=None, pnodes=[], energy_price_curve=None, **kw_args):
        """ Initialises a new 'FTR' instance.
        """
        self.klass = klass
        self.ftr_type = ftr_type
        self.optimized = optimized
        self.action = action
        self.base_energy = base_energy
        self.flowgate = flowgate
        self.pnodes = pnodes
        self.energy_price_curve = energy_price_curve

        super(FTR, self).__init__(**kw_args)
    # >>> ftr


class ChargeProfileData(Root):
    # The date and time of an interval.
    time_stamp = ''

    # The value of an interval given a profile type (amount, price, or quantity), subject to the UOM.
    value = 0.0

    # The sequence number of the profile.
    sequence = 0

    charge_profile = None

    bill_determinant = None

    # <<< charge_profile_data
    # @generated
    def __init__(self, time_stamp='', value=0.0, sequence=0, charge_profile=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfileData' instance.
        """
        self.time_stamp = time_stamp
        self.value = value
        self.sequence = sequence
        self.charge_profile = charge_profile
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

    # A fowgate may have 0 to 1 TRM
    flowgate = []

    # <<< transmission_reliability_margin
    # @generated
    def __init__(self, trm_value=0.0, trm_type='', value_unit='', flowgate=[], **kw_args):
        """ Initialises a new 'TransmissionReliabilityMargin' instance.
        """
        self.trm_value = trm_value
        self.trm_type = trm_type
        self.value_unit = value_unit
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

    generating_unit = None

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

    connectivity_node = None

    rto = None

    measurements = []

    receipt_transaction_bids = []

    pnode_clearing = None

    ftrs = []

    # A registered resource injects power at one or more connectivity nodes related to a pnode
    registered_resources = []

    delivery_transaction_bids = []

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
        self.connectivity_node = connectivity_node
        self.rto = rto
        self.measurements = measurements
        self.receipt_transaction_bids = receipt_transaction_bids
        self.pnode_clearing = pnode_clearing
        self.ftrs = ftrs
        self.registered_resources = registered_resources
        self.delivery_transaction_bids = delivery_transaction_bids

        super(Pnode, self).__init__(**kw_args)
    # >>> pnode


class CapacityBenefitMargin(Profile):
    """ Capacity Benefit Margin (CBM) is defined as that amount of transmission transfer capability reserved by load serving entities to ensure access to generation from interconnected systems to meet generation reliability requirements. Reservation fo CBM by a load serving entity allows that entity to reduce its installed generating capacity below that which may otherwise have been necessary without interconnections to meet its generation reliability requirements.  CBM is modeled as a profile with values in different time periods which are represented by the profile data.
    """
    # Capacity Benefit Margin may differ based on the season
    season = None

    # A flowgate may have 0 to n CBM profile Each season may be a CBM which contains a set of profile data
    flowgate = []

    # <<< capacity_benefit_margin
    # @generated
    def __init__(self, season=None, flowgate=[], **kw_args):
        """ Initialises a new 'CapacityBenefitMargin' instance.
        """
        self.season = season
        self.flowgate = flowgate

        super(CapacityBenefitMargin, self).__init__(**kw_args)
    # >>> capacity_benefit_margin


class EnergyPriceCurve(Curve):
    """ Relationship between a price in $/hour (Y-axis) and a MW value (X-axis).
    """
    ftrs = []

    energy_transactions = []

    # <<< energy_price_curve
    # @generated
    def __init__(self, ftrs=[], energy_transactions=[], **kw_args):
        """ Initialises a new 'EnergyPriceCurve' instance.
        """
        self.ftrs = ftrs
        self.energy_transactions = energy_transactions

        super(EnergyPriceCurve, self).__init__(**kw_args)
    # >>> energy_price_curve


class MarketProduct(IdentifiedObject):
    """ A product traded by an RTO (e.g., energy, 10 minute spinning reserve).  Ancillary service product examples include: Regulation Up Regulation Dn Spinning Reserve Non-Spinning Reserve Operating Reserve
    """
    # Market product associated with reserve requirement must be a reserve or regulation product.
    reserve_reqs = []

    product_bids = []

    # A registered resource is eligible to bid market products
    registered_resources = []

    market = None

    # <<< market_product
    # @generated
    def __init__(self, reserve_reqs=[], product_bids=[], registered_resources=[], market=None, **kw_args):
        """ Initialises a new 'MarketProduct' instance.
        """
        self.reserve_reqs = reserve_reqs
        self.product_bids = product_bids
        self.registered_resources = registered_resources
        self.market = market

        super(MarketProduct, self).__init__(**kw_args)
    # >>> market_product


class BilateralTransaction(Root):
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

    market_statement_line_item = None

    charge_profiles = []

    user_attributes = []

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
        self.market_statement_line_item = market_statement_line_item
        self.charge_profiles = charge_profiles
        self.user_attributes = user_attributes

        super(PassThroughBill, self).__init__(**kw_args)
    # >>> pass_through_bill


class RampRateCurve(Curve):
    """ Ramp rate as a function of resource MW output
    """
    # How ramp rate is applied (e.g., raise or lower, as when applied to a generation resource)
    ramp_rate_type = ''

    generating_unit = []

    # <<< ramp_rate_curve
    # @generated
    def __init__(self, ramp_rate_type='', generating_unit=[], **kw_args):
        """ Initialises a new 'RampRateCurve' instance.
        """
        self.ramp_rate_type = ramp_rate_type
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

    bid_clearing = None

    market = None

    # A bid comprises one or more product bids of market products
    product_bids = []

    # <<< bid
    # @generated
    def __init__(self, stop_time='', start_time='', market_type='', bid_clearing=None, market=None, product_bids=[], **kw_args):
        """ Initialises a new 'Bid' instance.
        """
        self.stop_time = stop_time
        self.start_time = start_time
        self.market_type = market_type
        self.bid_clearing = bid_clearing
        self.market = market
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

    charge_profile_data = []

    pass_trough_bill = None

    bill_determinant = None

    # <<< charge_profile
    # @generated
    def __init__(self, unit_of_measure='', frequency='', type='', number_interval=0, charge_profile_data=[], pass_trough_bill=None, bill_determinant=None, **kw_args):
        """ Initialises a new 'ChargeProfile' instance.
        """
        self.unit_of_measure = unit_of_measure
        self.frequency = frequency
        self.type = type
        self.number_interval = number_interval
        self.charge_profile_data = charge_profile_data
        self.pass_trough_bill = pass_trough_bill
        self.bill_determinant = bill_determinant

        super(ChargeProfile, self).__init__(**kw_args)
    # >>> charge_profile


class ResourceGroupReq(IdentifiedObject):
    """ Ancillary service requirements for a market.
    """
    rtos = []

    resource_group = None

    # <<< resource_group_req
    # @generated
    def __init__(self, rtos=[], resource_group=None, **kw_args):
        """ Initialises a new 'ResourceGroupReq' instance.
        """
        self.rtos = rtos
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

    market_products = []

    registered_resources = []

    rto = None

    settlements = []

    bids = []

    market_factors = []

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
        self.market_products = market_products
        self.registered_resources = registered_resources
        self.rto = rto
        self.settlements = settlements
        self.bids = bids
        self.market_factors = market_factors

        super(Market, self).__init__(**kw_args)
    # >>> market


class ConstraintTerm(IdentifiedObject):
    """ A constraint term is one element of a linear constraint.
    """
    # The function is an enumerated value that can be 'active', 'reactive', or 'VA' to indicate the type of flow.
    function = ''


    factor = ''

    security_constraint_sum = None

    # <<< constraint_term
    # @generated
    def __init__(self, function='', factor='', security_constraint_sum=None, **kw_args):
        """ Initialises a new 'ConstraintTerm' instance.
        """
        self.function = function
        self.factor = factor
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

    component_market_statement_line_item = []

    user_attributes = []

    pass_through_bill = None

    market_statement = None

    container_market_statement_line_item = None

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
        self.component_market_statement_line_item = component_market_statement_line_item
        self.user_attributes = user_attributes
        self.pass_through_bill = pass_through_bill
        self.market_statement = market_statement
        self.container_market_statement_line_item = container_market_statement_line_item

        super(MarketStatementLineItem, self).__init__(**kw_args)
    # >>> market_statement_line_item


class MWLimitSchedule(Curve):
    """ Maximum MW and optionally Minimum MW (Y1 and Y2, respectively)
    """
    security_constraint_limit = None

    # <<< mwlimit_schedule
    # @generated
    def __init__(self, security_constraint_limit=None, **kw_args):
        """ Initialises a new 'MWLimitSchedule' instance.
        """
        self.security_constraint_limit = security_constraint_limit

        super(MWLimitSchedule, self).__init__(**kw_args)
    # >>> mwlimit_schedule


class ProductBid(IdentifiedObject):
    """ Component of a bid that pertains to one market product.
    """
    # A bid comprises one or more product bids of market products
    bid = None

    product_bid_clearing = None

    bid_price_curve = None

    market_product = None

    # <<< product_bid
    # @generated
    def __init__(self, bid=None, product_bid_clearing=None, bid_price_curve=None, market_product=None, **kw_args):
        """ Initialises a new 'ProductBid' instance.
        """
        self.bid = bid
        self.product_bid_clearing = product_bid_clearing
        self.bid_price_curve = bid_price_curve
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

    rto = None

    # <<< security_constraints
    # @generated
    def __init__(self, max_mw='', min_mw='', actual_mw='', rto=None, **kw_args):
        """ Initialises a new 'SecurityConstraints' instance.
        """
        self.max_mw = max_mw
        self.min_mw = min_mw
        self.actual_mw = actual_mw
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

    market_statement_line_item = []

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
        self.market_statement_line_item = market_statement_line_item

        super(MarketStatement, self).__init__(**kw_args)
    # >>> market_statement


class SensitivityPriceCurve(Curve):
    """ Optionally, this curve expresses elasticity of the associated requirement.  For example, used to reduce requirements when clearing price exceeds reasonable values when the supply quantity becomes scarce.  For example, a single point value of $1000/MW for a spinning reserve will cause a reduction in the required spinning reserve.  X axis is constrained quantity (e.g., MW) Y1 axis is money per constrained quantity
    """
    reserve_req = None

    # <<< sensitivity_price_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'SensitivityPriceCurve' instance.
        """
        self.reserve_req = reserve_req

        super(SensitivityPriceCurve, self).__init__(**kw_args)
    # >>> sensitivity_price_curve


class Settlement(Document):
    """ Specifies a settlement run.
    """
    # The trade date on which the settlement is run.
    trade_date = ''

    market = None

    erp_invoice_line_items = []

    erp_ledger_entries = []

    # <<< settlement
    # @generated
    def __init__(self, trade_date='', market=None, erp_invoice_line_items=[], erp_ledger_entries=[], **kw_args):
        """ Initialises a new 'Settlement' instance.
        """
        self.trade_date = trade_date
        self.market = market
        self.erp_invoice_line_items = erp_invoice_line_items
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

    charge_profile_data = []

    charge_profile = None

    user_attributes = []

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
        self.charge_profile_data = charge_profile_data
        self.charge_profile = charge_profile
        self.user_attributes = user_attributes

        super(BillDeterminant, self).__init__(**kw_args)
    # >>> bill_determinant


class MarketFactors(Document):
    """ Aggregation of market information relative for a specific time interval.
    """
    # The start of the time interval for which requirement is defined.
    interval_start_time = ''

    market = None

    erp_invoices = []

    activity_records = []

    # <<< market_factors
    # @generated
    def __init__(self, interval_start_time='', market=None, erp_invoices=[], activity_records=[], **kw_args):
        """ Initialises a new 'MarketFactors' instance.
        """
        self.interval_start_time = interval_start_time
        self.market = market
        self.erp_invoices = erp_invoices
        self.activity_records = activity_records

        super(MarketFactors, self).__init__(**kw_args)
    # >>> market_factors


class DefaultConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) applied as a default value if no specific constraint limits are specified for a contingency analysis. Use CurveSchedule XAxisUnits to specify MW or MVA.
    """
    security_constraint_sum = None

    # <<< default_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'DefaultConstraintLimit' instance.
        """
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
    generating_bids = []

    # <<< bid_set
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'BidSet' instance.
        """
        self.generating_bids = generating_bids

        super(BidSet, self).__init__(**kw_args)
    # >>> bid_set


class LoadReductionPriceCurve(Curve):
    """ This is the price sensitivity that bidder expresses for allowing market load interruption.  Relationship between price (Y1-axis) vs. MW (X-axis).
    """
    load_bids = []

    # <<< load_reduction_price_curve
    # @generated
    def __init__(self, load_bids=[], **kw_args):
        """ Initialises a new 'LoadReductionPriceCurve' instance.
        """
        self.load_bids = load_bids

        super(LoadReductionPriceCurve, self).__init__(**kw_args)
    # >>> load_reduction_price_curve


class BidClearing(Root):
    """ Bid clearing results posted for a given settlement period.
    """
    # Energy lost opportunity cost in monetary units.
    lost_op_cost = ''

    # Start up cost in case of energy commodity in monetary units.
    start_up_cost = ''

    # No-load cost in monetary units.
    no_load_cost = ''

    bid = None

    # <<< bid_clearing
    # @generated
    def __init__(self, lost_op_cost='', start_up_cost='', no_load_cost='', bid=None, **kw_args):
        """ Initialises a new 'BidClearing' instance.
        """
        self.lost_op_cost = lost_op_cost
        self.start_up_cost = start_up_cost
        self.no_load_cost = no_load_cost
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

    # A flowgate can be reciprocal flowgate for 0 to n transmission providers. A transmission provider may be a reciprocal entity for 0 to n flowgates.
    transmission_provider = []

    # A control area may own 0 to n flowgates A flowgate must be owned by exactly 1 control area
    sub_control_area = None

    ftrs = []

    lines = []

    # A flowgate may have 0 to n CBM profile Each season may be a CBM which contains a set of profile data
    capacity_benefit_margin = []

    # A fowgate may have 0 to 1 TRM
    transmission_reliability_margin = None

    violation_limits = []

    power_transormers = []

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
        self.transmission_provider = transmission_provider
        self.sub_control_area = sub_control_area
        self.ftrs = ftrs
        self.lines = lines
        self.capacity_benefit_margin = capacity_benefit_margin
        self.transmission_reliability_margin = transmission_reliability_margin
        self.violation_limits = violation_limits
        self.power_transormers = power_transormers

        super(Flowgate, self).__init__(**kw_args)
    # >>> flowgate


class NotificationTimeCurve(Curve):
    """ Notification time curve as a function of down time.  Relationship between crew notification time (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    generating_bids = []

    # <<< notification_time_curve
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'NotificationTimeCurve' instance.
        """
        self.generating_bids = generating_bids

        super(NotificationTimeCurve, self).__init__(**kw_args)
    # >>> notification_time_curve


class BaseCaseConstraintLimit(Curve):
    """ Possibly time-varying max MW or MVA and optionally Min MW limit or MVA limit (Y1 and Y2, respectively) assigned to a contingency analysis base case. Use CurveSchedule XAxisUnits to specify MW or MVA. To be used only if the BaseCaseConstraintLimit differs from the DefaultConstraintLimit.
    """
    security_constraint_sum = None

    # <<< base_case_constraint_limit
    # @generated
    def __init__(self, security_constraint_sum=None, **kw_args):
        """ Initialises a new 'BaseCaseConstraintLimit' instance.
        """
        self.security_constraint_sum = security_constraint_sum

        super(BaseCaseConstraintLimit, self).__init__(**kw_args)
    # >>> base_case_constraint_limit


class StartUpTimeCurve(Curve):
    """ Startup time curve as a function of down time, where time is specified in seconds.  Relationship between unit startup time (Y1-axis) vs. unit elapsed down time (X-axis).
    """
    generating_bids = []

    # <<< start_up_time_curve
    # @generated
    def __init__(self, generating_bids=[], **kw_args):
        """ Initialises a new 'StartUpTimeCurve' instance.
        """
        self.generating_bids = generating_bids

        super(StartUpTimeCurve, self).__init__(**kw_args)
    # >>> start_up_time_curve


class ResourceGroup(IdentifiedObject):
    """ A logical grouping of resources that are used to model location of types of requirements for ancillary services such as spinning reserve zones, regulation zones, etc.
    """
    resource_group_reqs = []

    registered_resources = []

    # <<< resource_group
    # @generated
    def __init__(self, resource_group_reqs=[], registered_resources=[], **kw_args):
        """ Initialises a new 'ResourceGroup' instance.
        """
        self.resource_group_reqs = resource_group_reqs
        self.registered_resources = registered_resources

        super(ResourceGroup, self).__init__(**kw_args)
    # >>> resource_group


class ReserveReqCurve(RegularIntervalSchedule):
    """ A curve relating  reserve requirement versus time, showing the values of a specific reserve requirement for each unit of the period covered. The  curve can be based on 'absolute' time or on 'normalized' time.  X is time, typically expressed in absolute time Y1 is reserve requirement, typically expressed in MW
    """
    reserve_req = None

    # <<< reserve_req_curve
    # @generated
    def __init__(self, reserve_req=None, **kw_args):
        """ Initialises a new 'ReserveReqCurve' instance.
        """
        self.reserve_req = reserve_req

        super(ReserveReqCurve, self).__init__(**kw_args)
    # >>> reserve_req_curve


class StartUpCostCurve(Curve):
    """ Startup costs and time as a function of down time.  Relationship between unit startup cost (Y1-axis) and unit startup time (Y2-axis) vs. unit elapsed down time (X-axis).
    """
    generating_bids = []

    registered_generators = []

    # <<< start_up_cost_curve
    # @generated
    def __init__(self, generating_bids=[], registered_generators=[], **kw_args):
        """ Initialises a new 'StartUpCostCurve' instance.
        """
        self.generating_bids = generating_bids
        self.registered_generators = registered_generators

        super(StartUpCostCurve, self).__init__(**kw_args)
    # >>> start_up_cost_curve


class ViolationLimit(Limit):
    """ A type of limit that indicates if it is enforced and, through association, the organisation responsible for setting the limit.
    """
    # True if limit is enforced.
    enforced = False

    # Limits may differ based on the season
    season = None

    organisations = []

    measurement = None

    flowgate = None

    # <<< violation_limit
    # @generated
    def __init__(self, enforced=False, season=None, organisations=[], measurement=None, flowgate=None, **kw_args):
        """ Initialises a new 'ViolationLimit' instance.
        """
        self.enforced = enforced
        self.season = season
        self.organisations = organisations
        self.measurement = measurement
        self.flowgate = flowgate

        super(ViolationLimit, self).__init__(**kw_args)
    # >>> violation_limit


class LossPenaltyFactor(MarketFactors):
    """ Loss penalty factor applied to a ConnectivityNode for a given time interval.
    """
    connectivity_nodes = []

    # <<< loss_penalty_factor
    # @generated
    def __init__(self, connectivity_nodes=[], **kw_args):
        """ Initialises a new 'LossPenaltyFactor' instance.
        """
        self.connectivity_nodes = connectivity_nodes

        super(LossPenaltyFactor, self).__init__(**kw_args)
    # >>> loss_penalty_factor


class ProductBidClearing(MarketFactors):
    """ Product Bid clearing results posted for a given settlement period.
    """
    # Cleared MWs.
    cleared_mw = ''

    product_bids = []

    # <<< product_bid_clearing
    # @generated
    def __init__(self, cleared_mw='', product_bids=[], **kw_args):
        """ Initialises a new 'ProductBidClearing' instance.
        """
        self.cleared_mw = cleared_mw
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

    market_case_clearing = None

    # <<< ancillary_service_clearing
    # @generated
    def __init__(self, mcp='', cleared_mw='', commodity_type='', market_case_clearing=None, **kw_args):
        """ Initialises a new 'AncillaryServiceClearing' instance.
        """
        self.mcp = mcp
        self.cleared_mw = cleared_mw
        self.commodity_type = commodity_type
        self.market_case_clearing = market_case_clearing

        super(AncillaryServiceClearing, self).__init__(**kw_args)
    # >>> ancillary_service_clearing


class ReserveReq(ResourceGroupReq):
    """ Requirements for minimum amount of reserve and/or regulation to be supplied by a set of qualified resources.
    """
    sensitivity_price_curve = None

    # Market product associated with reserve requirement must be a reserve or regulation product.
    market_product = None

    reserve_req_curve = None

    # <<< reserve_req
    # @generated
    def __init__(self, sensitivity_price_curve=None, market_product=None, reserve_req_curve=None, **kw_args):
        """ Initialises a new 'ReserveReq' instance.
        """
        self.sensitivity_price_curve = sensitivity_price_curve
        self.market_product = market_product
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

    generating_bids = []

    unit_initial_conditions = []

    ramp_rate_curves = []

    generating_unit = None

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
        self.start_up_cost_curves = start_up_cost_curves
        self.generating_bids = generating_bids
        self.unit_initial_conditions = unit_initial_conditions
        self.ramp_rate_curves = ramp_rate_curves
        self.generating_unit = generating_unit

        super(RegisteredGenerator, self).__init__(**kw_args)
    # >>> registered_generator


class SecurityConstraintSum(MarketFactors):
    """ Typically provided by RTO systems, constraints identified in both base case and critical contingency cases have to be transferred. A constraint has N (>=1) constraint terms. A term is represented by an instance of TerminalConstraintTerm.  The constraint expression is: minValue <= c1*x1 + c2*x2 + .... cn*xn + k <= maxValue where: - cn is ConstraintTerm.factor  - xn is the flow at the terminal Flow into the associated equipment is positive for the purpose of ConnectivityNode NodeConstraintTerm  k is SecurityConstraintsLinear.resourceMW The units of k are assumed to be same as the units of the flows, xn.  The constants, cn, are dimensionless. With these conventions, cn and k are all positive for a typical constraint such as 'weighted sum of generation must be less than limit'. Furthermore, cn are all 1.0 for a case such as 'interface flow must be less than limit', assuming the terminals are chosen on the importing side of the interface.
    """
    constraint_terms = []

    rto = None

    contingency_constraint_limits = []

    default_constraint_limit = None

    base_case_constraint_limit = None

    # <<< security_constraint_sum
    # @generated
    def __init__(self, constraint_terms=[], rto=None, contingency_constraint_limits=[], default_constraint_limit=None, base_case_constraint_limit=None, **kw_args):
        """ Initialises a new 'SecurityConstraintSum' instance.
        """
        self.constraint_terms = constraint_terms
        self.rto = rto
        self.contingency_constraint_limits = contingency_constraint_limits
        self.default_constraint_limit = default_constraint_limit
        self.base_case_constraint_limit = base_case_constraint_limit

        super(SecurityConstraintSum, self).__init__(**kw_args)
    # >>> security_constraint_sum


class RegisteredLoad(RegisteredResource):
    load_area = None

    load_bids = []

    # <<< registered_load
    # @generated
    def __init__(self, load_area=None, load_bids=[], **kw_args):
        """ Initialises a new 'RegisteredLoad' instance.
        """
        self.load_area = load_area
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
    terminal = None

    # <<< terminal_constraint_term
    # @generated
    def __init__(self, terminal=None, **kw_args):
        """ Initialises a new 'TerminalConstraintTerm' instance.
        """
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

    pnode = None

    # <<< pnode_clearing
    # @generated
    def __init__(self, cost_lmp='', loss_lmp='', congest_lmp='', pnode=None, **kw_args):
        """ Initialises a new 'PnodeClearing' instance.
        """
        self.cost_lmp = cost_lmp
        self.loss_lmp = loss_lmp
        self.congest_lmp = congest_lmp
        self.pnode = pnode

        super(PnodeClearing, self).__init__(**kw_args)
    # >>> pnode_clearing


class TransactionBid(Bid):
    """ Bilateral or scheduled transactions for energy and ancillary services considered by market clearing process
    """

    energy_trans_id = ''

    delivery_pnode = None

    energy_trans_id = None

    energy_profiles = []

    receipt_pnode = None

    # <<< transaction_bid
    # @generated
    def __init__(self, energy_trans_id='', delivery_pnode=None, energy_trans_id=None, energy_profiles=[], receipt_pnode=None, **kw_args):
        """ Initialises a new 'TransactionBid' instance.
        """
        self.energy_trans_id = energy_trans_id
        self.delivery_pnode = delivery_pnode
        self.energy_trans_id = energy_trans_id
        self.energy_profiles = energy_profiles
        self.receipt_pnode = receipt_pnode

        super(TransactionBid, self).__init__(**kw_args)
    # >>> transaction_bid


class NodeConstraintTerm(ConstraintTerm):
    """ To be used only to constrain a quantity that cannot be associated with a terminal. For example, a registered generating unit that is not electrically connected to the network.
    """
    connectivity_node = None

    # <<< node_constraint_term
    # @generated
    def __init__(self, connectivity_node=None, **kw_args):
        """ Initialises a new 'NodeConstraintTerm' instance.
        """
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

    ancillary_service_clearing = []

    # <<< market_case_clearing
    # @generated
    def __init__(self, case_type='', posted_date='', modified_date='', ancillary_service_clearing=[], **kw_args):
        """ Initialises a new 'MarketCaseClearing' instance.
        """
        self.case_type = case_type
        self.posted_date = posted_date
        self.modified_date = modified_date
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

    registered_load = None

    load_reduction_price_curve = None

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
        self.registered_load = registered_load
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

    bid_set = None

    notification_time_curve = None

    start_up_cost_curve = None

    start_up_time_curve = None

    registered_generator = None

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
        self.bid_set = bid_set
        self.notification_time_curve = notification_time_curve
        self.start_up_cost_curve = start_up_cost_curve
        self.start_up_time_curve = start_up_time_curve
        self.registered_generator = registered_generator

        super(GeneratingBid, self).__init__(**kw_args)
    # >>> generating_bid


# <<< market_operations
# @generated
# >>> market_operations

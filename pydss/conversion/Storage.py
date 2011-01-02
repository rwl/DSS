# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

from pydss.conversion.PowerConversionElement import PowerConversionElement

class Storage(PowerConversionElement):
    """The storage element is essentially a generator that can be dispatched    to either produce power or consume power commensurate with rating and    amount of stored energy.    The storage element can also produce or absorb vars within the kVA rating
    of the inverter. That is, a StorageController object requests kvar and the
    storage element provides them if it has any capacity left. The storage
    element can produce/absorb kvar while idling.
    """

    def __init__(self, kV=0.0, kW=0.0, pF=1.0, conn="Wye", kVAR=0.0, kVA=0.0,
            kWRated=0.0, kWhRated=0.0, kWhStored=0.0, pctStored=0.0,
            pctReserve=0.0, state="Idling", pctDischarge=0.0, pctCharge=0.0,
            pctEffCharge=0.0, pctEffDischarge=0.0, pctIdlingKW=0.0,
            pctIdlingKVAr=0.0, pctR=0.0, pctX=0.0, model=0, vMinPU=0.0,
            vMaxPU=0.0, dispMode="LoadMode", dischargeTrigger=0.0,
            chargeTrigger=0.0, timeChargeTrig=0.0, cls=0, userModel='',
            userData='', debugTrace=False, bus1=None, yearly=None, daily=None,
            duty=None, *args, **kw_args):
        """Initialises a new 'Storage' instance.
        """
        #: Nominal rated (1.0 per unit) voltage, kV, for Storage element.
        #  For 2- and 3-phase Storage elements, specify phase-phase kV.
        #  Otherwise, specify actual kV across each branch of the Storage
        #  element. If wye (star), specify phase-neutral kV. If delta or
        #  phase-phase connected, specify phase-phase kV.
        self.kV = kV

        #: The present kW value.  A positive value denotes power coming OUT
        #  of the element, which is the opposite of a Load element. A negative
        #  value indicates the Storage element is in Charging state. This value
        #  is modified internally depending on the dispatch mode.
        self.kW = kW

        #: Nominally, the power factor for discharging (acting as a generator).
        #  Default is 1.0. Setting this property will also set the kvar
        #  property. Enter negative for leading powerfactor (when kW and kvar
        #  have opposite signs.) A positive power factor for a generator
        #  signifies that the Storage element produces vars as is typical for
        #  a generator.
        self.pF = pF

        #: Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: The present kVAr value.  Alternative to specifying the power factor.
        #  Side effect:  the power factor value is altered to agree based on
        #  present value of kW.
        self.kVAR = kVAR

        #: kVA rating of power output. Defaults to rated kW. Used as the base
        #  for Dynamics mode and Harmonics mode values.
        self.kVA = kVA

        #: kW rating of power output. Side effect: Sets the KVA property.
        self.kWRated = kWRated

        #: Rated storage capacity in kWh. Default is 50.
        self.kWhRated = kWhRated

        #: Present amount of energy stored, kWh. Default is same as kWh rated.
        self.kWhStored = kWhStored

        #: Present amount of energy stored, % of rated kWh. Default is 100%.
        self.pctStored = pctStored

        #: Percent of rated kWh storage capacity to be held in reserve for
        #  normal operation. Default = 20. This is treated as the minimum
        #  energy discharge level unless there is an emergency. For emergency
        #  operation set this property lower. Cannot be less than zero.
        self.pctReserve = pctReserve

        #: Present operational state. In DISCHARGING mode, the Storage element
        #  acts as a generator and the kW property is positive. The element
        #  continues discharging at the scheduled output power level until the
        #  storage reaches the reserve value. Then the state reverts to IDLING.
        #  In the CHARGING state, the Storage element behaves like a Load and
        #  the kW property is negative. The element continues to charge until
        #  the max storage kWh is reached and Then switches to IDLING state. In
        #  IDLING state, the kW property shows zero. However, the resistive and
        #  reactive loss elements remain in the circuit and the power flow
        #  report will show power being consumed. Values are: "Idling",
        #  "Charging", "Discharging"
        self.state = state

        #: Discharge rate (output power) in Percent of rated kW. Default = 100.
        self.pctDischarge = pctDischarge

        #: Charging rate (input power) in Percent of rated kW. Default = 100.
        self.pctCharge = pctCharge

        #: Percent efficiency for CHARGING the storage element. Default = 90.
        self.pctEffCharge = pctEffCharge

        #: Percent efficiency for DISCHARGING the storage element.
        #  Default = 90. Idling losses are handled by %IdlingkW property and
        #  are in addition to the charging and discharging efficiency losses
        #  in the power conversion process inside the unit.
        self.pctEffDischarge = pctEffDischarge

        #: Percent of rated kW consumed while idling. Default = 1.
        self.pctIdlingKW = pctIdlingKW

        #: Percent of rated kW consumed as reactive power (kvar) while idling.
        #  Default = 0.
        self.pctIdlingKVAr = pctIdlingKVAr

        #: Equivalent percent internal resistance, ohms. Default is 0. Placed
        #  in series with internal voltage source for harmonics and dynamics
        #  modes. Use a combination of pctIdlekW and pctEffCharge and
        #  pctEffDischarge to account for losses in power flow modes.
        self.pctR = pctR

        #: Equivalent percent internal reactance, ohms. Default is 50%. Placed
        #  in series with internal voltage source for harmonics and dynamics
        #  modes. (Limits fault current to 2 pu.) Use pctIdlekVAr and kvar
        #  properties to account for any reactive power during power flow
        #  solutions.
        self.pctX = pctX

        #: Integer code for the model to use for powet output variation with
        #  voltage. Valid values are:   1: Storage element injects a CONSTANT
        #  kW at specified power factor.   2:Storage element is modeled as a
        #  CONSTANT ADMITTANCE.   3:Compute load injection from User-written
        #  Model.
        self.model = model

        #: Minimum per unit voltage for which the Model is assumed to apply.
        #  Below this value, the load model reverts to a constant impedance
        #  model.
        self.vMinPU = vMinPU

        #: Maximum per unit voltage for which the Model is assumed to apply.
        #  Above this value, the load model reverts to a constant impedance
        #  model.
        self.vMaxPU = vMaxPU

        #: In DEFAULT mode, Storage element state is triggered by the loadshape
        #  curve corresponding to the solution mode. In EXTERNAL mode, Storage
        #  element state is controlled by an external Storage controller. This
        #  mode is automatically set if this Storage element is included in the
        #  element list of a StorageController element. For the other two
        #  dispatch modes, the Storage element state is controlled by either
        #  the global default Loadlevel value or the price level. Values are:
        #  "LoadMode", "PriceMode"
        self.dispMode = dispMode

        #: Dispatch trigger value for discharging the storage. If = 0.0 the
        #  Storage element state is changed by the State command or by a
        #  StorageController object. If <> 0 the Storage element state is set
        #  to DISCHARGING when this trigger level is EXCEEDED by either the
        #  specified Loadshape curve value or the price signal or global
        #  Loadlevel value, depending on dispatch mode.
        self.dischargeTrigger = dischargeTrigger

        #: Dispatch trigger value for charging the storage. If = 0.0 the
        #  Storage element state is changed by the State command or
        #  StorageController object.  If <> 0 the Storage element state is set
        #  to CHARGING when this trigger level is GREATER than either the
        #  specified Loadshape curve value or the price signal or global
        #  Loadlevel value, depending on dispatch mode. See State property.
        self.chargeTrigger = chargeTrigger

        #: Time of day in fractional hours (0230 = 2.5) at which storage
        #  element will automatically go into charge state. Default is 2.0.
        #  Enter a negative time value to disable this feature.
        self.timeChargeTrig = timeChargeTrig

        #: An arbitrary integer number representing the class of Storage
        #  element so that Storage values may be segregated by class.
        self.cls = cls

        #: Name of DLL containing user-written model, which computes the
        #  terminal currents for Dynamics studies, overriding the default
        #  model.  Set to 'none' to negate previous setting.
        self.userModel = userModel

        #: String (in quotes or parentheses) that gets passed to user-written
        #  model for defining the data required for that model.
        self.userData = userData

        #: Turn this on to capture the progress of the Storage model for each
        #  iteration.  Creates a separate file for each Storage element named
        #  'STORAGE_name.CSV'.
        self.debugTrace = debugTrace

        #: Bus to which the Storage element is connected.
        self.bus1 = bus1

        #: Dispatch shape to use for yearly simulations.  Must be previously
        #  defined as a Loadshape object.  If this is not specified, the Daily
        #  dispatch shape, If any, is repeated during Yearly solution modes.
        #  In the default dispatch mode, the Storage element uses this
        #  loadshape to trigger State changes.
        self.yearly = yearly

        #: Dispatch shape to use for daily simulations.  Must be previously
        #  defined as a Loadshape object of 24 hrs, typically.  In the default
        #  dispatch mode, the Storage element uses this loadshape to trigger
        #  State changes.
        self.daily = daily

        #: Load shape to use for duty cycle dispatch simulations such as for
        #  solar ramp rate studies. Must be previously defined as a Loadshape
        #  object. Typically would have time intervals of 1-5 seconds. If there
        #  are fewer points in the actual shape, the shape is assumed to
        #  repeat.
        self.duty = duty

        super(Storage, self).__init__(*args, **kw_args)

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

from pydss.control.ControlElement import ControlElement

class StorageController(ControlElement):
    """A StorageController is a control element that is connected to a terminal of another circuit element and sends dispatch  signals to a fleet of energy storage elements it controls
    """

    def __init__(self, kWTarget=0.0, pctKWBand=0.0, pFTarget=0.0, pFBand=0.0, weights=0.0, modeDischarge="peakShave", modeCharge="loadShape", timeDischargeTrigger=0.0, timeChargeTrigger=0.0, pctRateKW=0.0, pctRateKVAr=0.0, pctRateCharge=0.0, pctReserve=0.0, kWhTotal=0.0, kWTotal=0.0, kWhActual=0.0, kWActual=0.0, kWNeed=0.0, pctParticipation=0.0, eventLog=False, varDispatch=False, inhibitTime=0, elements=None, yearly=None, daily=None, duty=None, *args, **kw_args):
        """Initialises a new 'StorageController' instance.
        """
        #: kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band at least until the storage is depleted.
        self.kWTarget = kWTarget

        #: Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%).No dispatch changes are attempted If the power in the monitored terminal stays within this band.
        self.pctKWBand = pctKWBand

        #: Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.
        self.pFTarget = pFTarget

        #: Bandwidth of the Target power factor of the monitored element. Of the dead band around the kvar target value. Default is 0.04 (+/- 0.02).No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.
        self.pFBand = pFBand

        #: Proportional weights corresponding to each storage element in 'elements'. The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. Default is to set all weights to 1.0.
        self.weights = weights

        #: Mode of operation for the DISCHARGE FUNCTION of this controller. In PeakShave mode (Default), the control attempts to discharge storage to keep power in the monitored element below the kWTarget. In Follow mode, the control is triggered by time and resets the kWTarget value to the present monitored element power. It then attempts to discharge storage to keep power in the monitored element below the new kWTarget. See TimeDischargeTrigger.In Support mode, the control operates oppositely of PeakShave mode: storage is discharged to keep kW power output up near the target. In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. Storage is discharged when the loadshape value is positive. In Time mode, the storage discharge is turned on at the specified pctRatekW and pctRatekvar at the specified discharge trigger time in fractional hours. Values are: "peakShave", "follow", "support", "loadShape", "time"
        self.modeDischarge = modeDischarge

        #: Mode of operation for the CHARGE FUNCTION of this controller. In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. Storage is charged when the loadshape value is negative. In Time mode, the storage charging FUNCTION is triggered at the specified pctRateCharge at the specified sharge trigger time in fractional hours. Values are: "loadShape", "time"
        self.modeCharge = modeCharge

        #: Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. In Time mode, the discharge is based on the pctRatekW property value. Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored).
        self.timeDischargeTrigger = timeDischargeTrigger

        #: Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200).When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is topped off for the next discharge cycle.
        self.timeChargeTrigger = timeChargeTrigger

        #: Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered by time.
        self.pctRateKW = pctRateKW

        #: Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered by time.
        self.pctRateKVAr = pctRateKVAr

        #: Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is entered due to a time trigger.
        self.pctRateCharge = pctRateCharge

        #: Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.
        self.pctReserve = pctReserve

        #: Total rated kWh energy storage capacity of storage elements controlled by this controller.
        self.kWhTotal = kWhTotal

        #: Total rated kW power capacity of storage elements controlled by this controller.
        self.kWTotal = kWTotal

        #: Actual kWh output of all controlled storage elements.
        self.kWhActual = kWhActual

        #: Actual kW output of all controlled storage elements.
        self.kWActual = kWActual

        #: KW needed to meet target.
        self.kWNeed = kWNeed

        #: Participation factor, %.
        self.pctParticipation = pctParticipation

        #: Log control actions to Eventlog.
        self.eventLog = eventLog

        #: Flag to indicate whether or not to disatch vars as well as watts.
        self.varDispatch = varDispatch

        #: Hours (integer) to inhibit Discharging after going into Charge mode.
        self.inhibitTime = inhibitTime

        self.elements = [] if elements is None else elements

        self.yearly = yearly

        self.daily = daily

        self.duty = duty

        super(StorageController, self).__init__(*args, **kw_args)

    def add_elements(self, *elements):
        for obj in elements:
            self.elements.append(obj)

    def remove_elements(self, *elements):
        for obj in elements:
            self.elements.remove(obj)

    yearly = None

    daily = None

    duty = None


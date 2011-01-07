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

from dss.delivery.PowerDeliveryElement import PowerDeliveryElement

class Transformer(PowerDeliveryElement):
    """The Transfomer model is implemented as a multi-terminal (two or more)
    power delivery element.  A transfomer consists of two or more Windings,
    connected in somewhat arbitray fashion (with the standard Wye-Delta
    defaults, of course).  You can specify the parameters of a winding one
    winding at a time or use arrays to set all the values.  Use the 'wdg=...'
    parameter to select a winding.  Transformers have one or more phases.  The
    number of conductors per terminal is always one more than the number of
    phases.  For wye- or star-connected windings, the extra conductor is the
    neutral point.  For delta-connected windings, the extra terminal is open
    internally (you normally leave this connected to node 0).
    """

    def __init__(self, windings=0, wdg=0, bus='', conn="Wye", kV=0.0, kVA=0.0,
            tap=0.0, rPct=0.0, rNeut=0.0, xNeut=0.0, buses='', conns="Wye",
            kVs=0.0, kVAs=0.0, taps=0.0, xHL=0.0, xHT=0.0, xLT=0.0,
            xSCArray=0.0, thermal=0.0, n=0.0, m=0.0, fLRise=0.0, hSRise=0.0,
            pctLoadLoss=0.0, pctNoLoadLoss=0.0, normHKVa=0.0, emergHKVa=0.0,
            substation=False, maxTap=0.0, minTap=0.0, numTaps=0, subName='',
            pctImage=0.0, ppmAntiFloat=0.0, *args, **kw_args):
        """Initialises a new 'Transformer' instance.
        """
        #: Number of windings, this transformers. (Also is the number of
        #  terminals)
        self.windings = windings

        #: Set this = to the number of the winding you wish to define.  Then
        #  set the values for this winding.  Repeat for each winding.
        #  Alternatively, use the array collections (buses, kvas, etc.) to
        #  define the windings. Note: impedances are BETWEEN pairs of windings;
        #  they are not the property of a single winding.
        self.wdg = wdg

        #: Bus to which this winding is connected.
        self.bus = bus

        #: Connection of this winding. Default is 'wye' with the neutral
        #  solidly grounded. Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating
        #  of the actual winding.
        self.kV = kV

        #: Base kVA rating of the winding. Side effect: forces change of max
        #  normal and emerg kva ratings.
        self.kVA = kVA

        #: Per unit tap that this winding is on.
        self.tap = tap

        #: Percent resistance this winding.  (half of total for a 2-winding).
        self.rPct = rPct

        #: Neutral resistance of wye (star)-connected winding in actual ohms.
        #  If entered as a negative value, the neutral is assumed to be open,
        #  or floating.
        self.rNeut = rNeut

        #: Neutral reactance of wye(star)-connected winding in actual ohms.
        #  May be + or -.
        self.xNeut = xNeut

        #: Use this to specify all the bus connections at once using an array.
        self.buses = buses

        #: Use this to specify all the Winding connections at once using an
        #  array. Values are: "Wye", "LN", "Delta", "LL"
        self.conns = conns

        #: Use this to specify the kV ratings of all windings at once using an
        #  array.
        self.kVs = kVs

        #: Use this to specify the kVA ratings of all windings at once using an
        #  array.
        self.kVAs = kVAs

        #: Use this to specify the p.u. tap of all windings at once using an
        #  array.
        self.taps = taps

        #: Use this to specify the percent reactance, H-L (winding 1 to
        #  winding 2).  Use for 2- or 3-winding transformers. On the kva base
        #  of winding 1.
        self.xHL = xHL

        #: Use this to specify the percent reactance, H-T (winding 1 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xHT = xHT

        #: Use this to specify the percent reactance, L-T (winding 2 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xLT = xLT

        #: Use this to specify the percent reactance between all pairs of
        #  windings as an array.  All values are on the kVA base of winding 1.
        #  The order of the values is as follows:  (x12 13 14... 23 24.. 34 ..)
        #  There will be n(n-1)/2 values, where n=number of windings.
        self.xSCArray = xSCArray

        #: Thermal time constant of the transformer in hours.  Typically
        #  about 2.
        self.thermal = thermal

        #: n Exponent for thermal properties in IEEE C57.  Typically 0.8.
        self.n = n

        #: m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0
        self.m = m

        #: Temperature rise, deg C, for full load.
        self.fLRise = fLRise

        #: Hot spot temperature rise, deg C.
        self.hSRise = hSRise

        #: Percent load loss at full load. The %R of the High and Low windings
        #  (1 and 2) are adjusted to agree at rated kVA loading.
        self.pctLoadLoss = pctLoadLoss

        #: Percent no load losses at rated excitation voltage. Converts to a
        #  resistance in parallel with the magnetising impedance in each
        #  winding.
        self.pctNoLoadLoss = pctNoLoadLoss

        #: Normal maximum kVA rating of H winding (winding 1).  Usually
        #  100% - 110% of maximum nameplate rating, depending on load shape.
        #  Defaults to 110% of kVA rating of Winding 1.
        self.normHKVa = normHKVa

        #: Emergency (contingency)  kVA rating of H winding (winding 1).
        #  Usually 140% - 150% of maximum nameplate rating, depending on load
        #  shape. Defaults to 150% of kVA rating of Winding 1.
        self.emergHKVa = emergHKVa

        #: Designates whether this transformer is to be considered a
        #  substation.
        self.substation = substation

        #: Max per unit tap for the active winding.
        self.maxTap = maxTap

        #: Min per unit tap for the active winding.
        self.minTap = minTap

        #: Total number of taps between min and max tap.
        self.numTaps = numTaps

        #: Substation Name. Optional. If specified, printed on plots.
        self.subName = subName

        #: Percent magnetizing current. Default=0.0. Magnetizing branch is in
        #  parallel with windings in each phase. Also, see 'ppm_antifloat'.
        self.pctImage = pctImage

        #: Parts per million by which the reactive term is increased to protect
        #  against accidentally floating a winding.  If positive then the
        #  effect is adding a small reactor to ground. If negative, then a
        #  capacitor.
        self.ppmAntiFloat = ppmAntiFloat

        super(Transformer, self).__init__(*args, **kw_args)

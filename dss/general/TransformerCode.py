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

class TransformerCode(object):

    def __init__(self, phases=0, windings=0, wdg=0, conn="Wye", kV=0.0,
            kVA=0.0, tap=0.0, pctR=0.0, rNeut=0.0, xNeut=0.0, conns="Wye",
            kVs=0.0, kVAs=0.0, taps=0.0, xhl=0.0, xht=0.0, xlt=0.0,
            xscArray=0.0, thermal=0.0, n=0.0, m=0.0, fLRise=0.0, hSRise=0.0,
            pctLoadLoss=0.0, pctNoLoadLoss=0.0, normHKVA=0.0, emergHKVA=0.0,
            maxTap=0.0, minTap=0.0, numTaps=0, pctIMag=0.0, ppmAntiFloat=0.0,
            pctRS=0.0, like=None):
        """Initialises a new 'TransformerCode' instance.
        """
        #: Number of phases for this transformer.
        self.phases = phases

        #: Number of windings, this transformers (Also is the number of
        #  terminals).
        self.windings = windings

        #: Set this = to the number of the winding you wish to define.  Then
        #  set the values for this winding.  Repeat for each winding.
        #  Alternatively, use the array collections (buses, kvas, etc.) to
        #  define the windings.  Note: reactances are BETWEEN pairs of
        #  windings; they are not the property of a single winding.
        self.wdg = wdg

        #: Connection of this winding. Default is 'wye' with the neutral
        #  solidly grounded. Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating
        #  of the actual winding.
        self.kV = kV

        #: Base kVA rating of the winding. Side effect: forces change of max
        #  normal and emerg kva ratings.If 2-winding transformer, forces other
        #  winding to same value. When winding 1 is defined, all other windings
        #  are defaulted to the same rating and the first two winding
        #  resistances are defaulted to the pctLoadLoss value.
        self.kVA = kVA

        #: Per unit tap that this winding is normally on.
        self.tap = tap

        #: Percent resistance this winding.  (half of total for a 2-winding).
        self.pctR = pctR

        #: Neutral resistance of wye (star)-connected winding in actual ohms.
        #  If entered as a negative value, the neutral is assumed to be open,
        #  or floating.
        self.rNeut = rNeut

        #: Neutral reactance of wye(star)-connected winding in actual ohms.
        #  May be + or -.
        self.xNeut = xNeut

        #: Use this to specify all the Winding connections at once using an
        #  array. Values are: "Wye", "LN", "Delta", "LL"
        self.conns = conns

        #: Use this to specify the kV ratings of all windings at once using an
        #  array.
        self.kVs = kVs

        #: Use this to specify the kVA ratings of all windings at once using an
        #  array.
        self.kVAs = kVAs

        #: Use this to specify the normal p.u. tap of all windings at once
        #  using an array.
        self.taps = taps

        #: Use this to specify the percent reactance, H-L (winding 1 to
        #  winding 2).  Use for 2- or 3-winding transformers. On the kva base
        #  of winding 1.
        self.xhl = xhl

        #: Use this to specify the percent reactance, H-T (winding 1 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xht = xht

        #: Use this to specify the percent reactance, L-T (winding 2 to
        #  winding 3).  Use for 3-winding transformers only. On the kVA base
        #  of winding 1.
        self.xlt = xlt

        #: Use this to specify the percent reactance between all pairs of
        #  windings as an array. All values are on the kVA base of winding 1.
        #  The order of the values is as follows:(x12 13 14... 23 24.. 34 ..)
        #  There will be n(n-1)/2 values, where n=number of windings.
        self.xscArray = xscArray

        #: Thermal time constant of the transformer in hours.  Typically
        #  about 2.
        self.thermal = thermal

        #: 'n' exponent for thermal properties in IEEE C57.  Typically 0.8.
        self.n = n

        #: 'm' exponent for thermal properties in IEEE C57.  Typically
        #  0.9 - 1.0
        self.m = m

        #: Temperature rise, deg C, for full load.
        self.fLRise = fLRise

        #: Hot spot temperature rise, deg C.
        self.hSRise = hSRise

        #: Percent load loss at full load. The %R of the High and Low windings
        #  (1 and 2) are adjusted to agree at rated kVA loading.
        self.pctLoadLoss = pctLoadLoss

        #: Percent no load losses at rated excitatation voltage. Default is 0.
        #  Converts to a resistance in parallel with the magnetizing impedance
        #  in each winding.
        self.pctNoLoadLoss = pctNoLoadLoss

        #: Normal maximum kVA rating of H winding (winding 1).  Usually
        #  100% - 110% of maximum nameplate rating, depending on load shape.
        #  Defaults to 110% of kVA rating of Winding 1.
        self.normHKVA = normHKVA

        #: Emergency (contingency)  kVA rating of H winding (winding 1).
        #  Usually 140% - 150% of maximum nameplate rating, depending on load
        #  shape. Defaults to 150% of kVA rating of Winding 1.
        self.emergHKVA = emergHKVA

        #: Max per unit tap for the active winding.
        self.maxTap = maxTap

        #: Min per unit tap for the active winding.
        self.minTap = minTap

        #: Total number of taps between min and max tap.
        self.numTaps = numTaps

        #: Percent magnetizing current. Default=0.0. Magnetizing branch is in
        #  parallel with windings in each phase. Also, see 'ppm_antifloat'.
        self.pctIMag = pctIMag

        #: Parts per million of transformer winding VA rating connected to
        #  ground to protect against accidentally floating a winding without a
        #  reference. If positive then the effect is adding a very large
        #  reactance to ground.  If negative, then a capacitor.
        self.ppmAntiFloat = ppmAntiFloat

        #: Use this property to specify all the winding %resistances using an
        #  array.
        self.pctRS = pctRS

        self.like = like

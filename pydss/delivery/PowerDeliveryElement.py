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

from pydss.common.CircuitElement import CircuitElement

class PowerDeliveryElement(CircuitElement):
    """Power delivery elements usually consist of two or more multiphase
    terminals.  Their basic function is to transport energy from one point to
    another.  On the power system, the most common power delivery elements are
    lines and transformers.  Thus, they generally have more than one terminal
    (capacitors and reactors can be an exception when shunt-connected rather
    than series-connected).  Power delivery elements are standard electrical
    elements generally completely defined in the rms steady state by their
    impedances.
    """

    def __init__(self, normAmps=0.0, emergAmps=0.0, faultRate=0.0, pctPerm=0.0,
            repair=0, *args, **kw_args):
        """Initialises a new 'PowerDeliveryElement' instance.
        """
        #: Normal rated current.
        self.normAmps = normAmps

        #: Maximum current.
        self.emergAmps = emergAmps

        #: No. of failures per year.
        self.faultRate = faultRate

        #: Percent of failures that become permanent.
        self.pctPerm = pctPerm

        #: Hours to repair.
        self.repair = repair

        super(PowerDeliveryElement, self).__init__(*args, **kw_args)

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

from scipy.sparse import csr_matrix as sparse

from dss.conversion.PowerConversionElement import PowerConversionElement

class CurrentSource(PowerConversionElement):
    """Ideal current source.  ISource maintains a positive sequence for
    harmonic scans.  If you want zero sequence, use three single-phase ISource.
    """

    def __init__(self, bus1='', amps=0.0, angle=0.0, frequency=60.0, phases=0,
            scanType="Positive", *args, **kw_args):
        """Initialises a new 'CurrentSource' instance.
        """
        #: Name of bus to which source is connected.
        self.bus1 = bus1

        #: Magnitude of current source, each phase, in Amps.
        self.amps = amps

        #: Phase angle in degrees of first phase. Phase shift between phases
        #  is assumed 120 degrees when number of phases <= 3.
        self.angle = angle

        #: Source frequency.  Defaults to circuit fundamental frequency.
        self.frequency = frequency

        #: Number of phases. For 3 or less, phase shift is 120 degrees.
        self.phases = phases

        #: Maintain specified sequence for harmonic solution.  Otherwise,
        #  angle between phases rotates with harmonic. Values are: "Positive",
        #  "Zero", "None"
        self.scanType = scanType

        #: Injection current.
        inj_current = 0.0

        super(CurrentSource, self).__init__(*args, **kw_args)


    @property
    def nPhases(self):
        """Number of phases. For 3 or less, phase shift is 120 degrees.
        Valid 'phase' values are: "BCN", "ACN", "AB", "A", "B", "ABCN",
        "AC", "N", "AN", "C", "ABN", "BN", "ABC", "BC", "CN"
        """
        phases = self.phases
        if phases in ["ABCN", "ABC"]:
            n_phases = 3
        elif phases in ["BCN", "ACN", "ABN", "AB", "BC"]:
            n_phases = 2
        elif phases in ["AN", "BN", "CN", "A", "B", "C"]:
            n_phases = 1
        else:
            n_phases = 0

        return n_phases


    def calcYPrim(self):
        """Returns the primitive Y (admittance) matrix for this element alone.
        """
        n_phases = self.nPhases
        y_order = n_phases * len(self.terminals)

        self.y_prim_series = sparse((y_order, y_order))
        self.y_prim        = sparse((y_order, y_order))

        # Yprim = 0 for Ideal Current Source; just leave it zeroed
        # TODO: Zero out rows and columns for open conductors.

        self.y_prim_invalid = False

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

class Spectrum(object):
    """Spectrum specified as Harmonic, pct magnitude and angle  Spectrum is
    shifted by the fundamental angle and stored in MultArray so that the
    fundamental is at zero degrees phase shift.
    """

    def __init__(self, nHarm=0, harmonic=0.0, pctMag=0.0, angle=0.0,
            csvFile=''):
        """Initialises a new 'Spectrum' instance.
        """
        #: Number of frequencies in this spectrum. (See csvFile)
        self.nHarm = nHarm

        #: Array of harmonic values.
        self.harmonic = harmonic

        #: Array of magnitude values, assumed to be in PERCENT.
        self.pctMag = pctMag

        #: Array of phase angle values, degrees.
        self.angle = angle

        #: File of spectrum points with (harmonic, magnitude-percent,
        #  angle-degrees) values, one set of 3 per line, in CSV format. If
        #  fewer than NUMHARM frequencies found in the file, NUMHARM is set
        #  to the smaller value.
        self.csvFile = csvFile

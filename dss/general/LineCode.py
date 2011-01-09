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

class LineCode(object):
    """The Linecode object is a general DSS object used by all circuits as a
    reference for obtaining line impedances.  Linecodes are objects that
    contain impedance characteristics for lines and cables.  The term 'line
    code' is an old term that simply refers to a code that was made up by
    programmers to describe a line construction.  In most distribution analysis
    programs, one can describe a line by its linecode and its length.
    Linecodes were defined in a separate file.  This collection of objects
    emulates the old linecode files, except that the concept is slightly more
    powerful.  Ultimately, the impedance of a line is described by its series
    impedance matrix and nodal capacitive admittance matrix.  These matrices
    may be specified directly or they can be generated by specifying the
    symmetrical component data.  Note that the impedances of lines may be
    specified directly and one does not need to use a line code, although the
    linecode will be more convenient most of the time.  There may be hundreds
    of lines, but only a few different kinds of line constructions.  LineCode
    also performs a Kron reduction, reducing out the last conductor in the
    impedance matrices, which is assumed to be a neutral conductor. This
    applies only if the impedance is specified as a matrix. If the impedance is
    defined as symmetrical components, this function does not apply because
    symmetrical component values already assume the reduction.  By specifying
    the values of Rg, Xg, and rho, the DSS will take the base frequency
    impedance matrix values and adjust the earth return component for
    frequency. Skin effect in the conductors is not modified. To represent skin
    effect, you have to define the geometry.  This assumes the impedance matrix
    is constructed as follows:
        Z_{11} + Z_{g} Z_{12} + Z_{g} Z_{13} + Z_{g}
        Z = R + jX = Z_{21} + Z_{g} Z_{22} + Z_{g} Z_{23} + Z_{g}
        Z_{31} + Z_{g} Z_{32} + Z_{g} Z_{33} + Z_{g}
    """

    def __init__(self, nPhases=0, r1=0.0, x1=0.0, r0=0.0, x0=0.0, c1=0.0,
            c0=0.0, units="none", baseFreq=0.0, normAmps=0.0, emergAmps=0.0,
            faultRate=0.0, pctPerm=0, repair=0, kron=False, rg=0.0, xg=0.0,
            rho=0.0, neutral=0, rMatrix=None, xMatrix=None, cMatrix=None):
        """Initialises a new 'LineCode' instance.
        """
        #: Number of phases in the line this line code data represents.
        #  Setting this property reinitializes the line code.  Impedance matrix
        #  is reset for default symmetrical component.
        self.nPhases = nPhases

        #: Positive-sequence Resistance, ohms per unit length.  See also
        #  rMatrix.
        self.r1 = r1

        #: Positive-sequence Reactance, ohms per unit length.  See also
        #  xMatrix.
        self.x1 = x1

        #: Zero-sequence Resistance, ohms per unit length.
        self.r0 = r0

        #: Zero-sequence Reactance, ohms per unit length.
        self.x0 = x0

        #: Positive-sequence capacitance, nF per unit length. See also cMatrix.
        self.c1 = c1

        #: Zero-sequence capacitance, nF per unit length.
        self.c0 = c0

        #: One of (ohms per ...) {none|mi|km|kft|m|me|ft|in|cm}.  Default is
        #  none; assumes units agree with length units given in Line object.
        self.units = units

        #: Frequency (Hz) at which impedances are specified.
        self.baseFreq = baseFreq

        #: Normal ampere limit on line.  This is the so-called Planning Limit.
        #  It may also be the value above which load will have to be dropped in
        #  a contingency.  Usually about 75% - 80% of the emergency (one-hour)
        #  rating.
        self.normAmps = normAmps

        #: Emergency ampere limit on line (usually one-hour rating).
        self.emergAmps = emergAmps

        #: Number of faults per unit length per year.
        self.faultRate = faultRate

        #: Percentage of the faults that become permanent (requiring a line
        #  crew to repair and a sustained interruption).
        self.pctPerm = pctPerm

        #: Hours to repair.
        self.repair = repair

        #: Perform Kron reduction on the impedance matrix after it is formed,
        #  reducing order by 1.  Do this only on initial definition after
        #  matrices are defined. Ignored for symmetrical components.
        self.kron = kron

        #: Carson earth return resistance per unit length used to compute
        #  impedance values at base frequency.  For making better frequency
        #  adjustments.
        self.rg = rg

        #: Carson earth return reactance per unit length used to compute
        #  impedance values at base frequency.  For making better frequency
        #  adjustments.
        self.xg = xg

        #: Earth resitivity (meter ohms used to compute earth correction
        #  factor).
        self.rho = rho

        #: Designates which conductor is the 'neutral' conductor that will be
        #  eliminated by Kron reduction. Default is the last conductor (nphases
        #  value). After Kron reduction is set to 0. Subsequent issuing of
        #  Kron=Yes will not do anything until this property is set to a legal
        #  value. Applies only to LineCodes defined by R, X, and C matrix.
        self.neutral = neutral

        #: Resistance matrix, lower triangle, ohms per unit length. Order of
        #  the matrix is the number of phases.  May be used to specify the
        #  impedance of any line configuration.  For balanced line models, you
        #  may use the standard symmetrical component data definition instead.
        self.rMatrix = rMatrix

        #: Reactance matrix, lower triangle, ohms per unit length. Order of the
        #  matrix is the number of phases.  May be used to specify the
        #  impedance of any line configuration.  For balanced line models, you
        #  may use the standard symmetrical component data definition instead.
        self.xMatrix = xMatrix

        #: Nodal Capacitance matrix, lower triangle, nf per unit length. Order
        #  of the matrix is the number of phases.  May be used to specify the
        #  shunt capacitance of any line configuration.  For balanced line
        #  models, you may use the standard symmetrical component data
        #  definition instead.
        self.cMatrix = cMatrix
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

from dss.common.CircuitElement import CircuitElement

class PowerConversionElement(CircuitElement):
    """Power conversion elements convert power from electrical form to some
    other form, or vice-versa.  Some may temporarily store energy and then give
    it back, as is the case for reactive elements.  Most will have only one
    connection to the power system and, therefore, only one multiphase
    terminal.  The description of the mechanical or thermal side of the power
    conversion is contained within the 'Black box' model.  The description may
    be a simple impedance or a complicated set of differential equations
    yielding a current injection equation of the form:
        ITerm(t)  = F(VTerm, [State], t)
    The function F will vary according to the type of simulation being
    performed.  The power conversion element must also be capable of reporting
    the partials matrix when necessary: dF/dV  In simple cases, this will
    simply be the primitive y (admittance) matrix; that is, the y matrix for
    this element alone.  This concept may easily be extended to multi-terminal
    devices, which would allow the representation of complex series elements
    such as fault current limiters.
    """

    def __init__(self, spectrum='', injCurrent=0.0, spectrumObj=None,
            *args, **kw_args):
        """Initialises a new 'PowerConversionElement' instance.
        """
        #: Name of harmonic spectrum for this device.
        self.spectrum = spectrum

        self.injCurrent = injCurrent

        #: The harmonic spectrum for this device.
        self.spectrumObj = spectrumObj

        super(PowerConversionElement, self).__init__(*args, **kw_args)

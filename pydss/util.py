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

from numpy import zeros, diag, array, ndarray

def triarray(lower):
    """Returns a symmetric array given only the lower triangular form. For
    example:
        >>> a = triarray([[1.2], [.3, 1.2], [.3, .3, 1.2]])
        >>> a[0, 3]
        3.0
    """
    c = 1
    for row in lower:
        assert len(row) == c
        c += 1

    n = len(lower[-1])

    a = zeros(n)

    for i, row in enumerate(lower):
        j = len(row)
        a[i, :j] = row

    return symmetrize(a)


def symmetrize(a):
    return a + a.T - diag(a.diagonal())


class SymNDArray(ndarray):
    def __setitem__(self, (i, j), value):
        ndarray.__setitem__(self, (i, j), value)
        ndarray.__setitem__(self, (j, i), value)


def symarray(input_array):
    """Returns a symmetrized version of input_array; further assignments to
    the array are automatically symmetrized."""
    return symmetrize(array(input_array)).view(SymNDArray)


if __name__ == "__main__":
    import doctest
    doctest.testmod()

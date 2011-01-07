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

class GrowthShape(object):
    """The GrowthShape object is a general DSS object used by all circuits as a
    reference for obtaining yearly growth curves.  A GrowthShape object is
    similar to a Loadshape object.  However, it is intended to represent the
    growth in load year-by-year and the way the curve is specified is entirely
    different.  You must enter the growth for the first year.  Thereafter, only
    the years where there is a change must be entered.  Otherwise it is assumed
    the growth stays the same.  Growth shapes are entered as multipliers for
    the previous year's load.  If the load grows by 2.5% in a year, the
    multiplier is entered as 1.025.  You do not need to enter subsequent years
    if the multiplier remains the same. You need only enter the years in which
    the growth rate is assumed to have changed.  The user may place the data in
    CSV or binary files as well as passing through the command interface. The
    rules are the same as for LoadShapes except that the year is always
    entered.  CSV files are text separated by commas, one interval to a line.
    There are two binary formats permitted: 1) a file of Singles; 2) a file of
    Doubles.
    """

    def __init__(self, nPts=0, year=0.0, csvFile='', sngFile='', dblFile=''):
        """Initialises a new 'GrowthShape' instance.
        """
        #: Number of points to expect in subsequent vector.
        self.nPts = nPts

        #: Array of year values, or a text file spec, corresponding to the
        #  multipliers.  Enter only those years where the growth changes.
        #  May be any integer sequence -- just so it is consistent. See help
        #  on mult.  Setting the global solution variable Year=0 causes the
        #  growth factor to default to 1.0, effectively neglecting growth.
        #  This is what you would do for all base year analyses.  You may also
        #  use the syntax:  year=(file=filename.ext) in which the array values
        #  are entered one per line in the text file referenced.
        self.year = year

        #: Switch input of growth curve data to a csv file containing (year,
        #  mult) points, one per line.
        self.csvFile = csvFile

        #: Switch input of growth curve data to a binary file of singles
        #  containing (year, mult) points, packed one after another.
        self.sngFile = sngFile

        #: Switch input of growth curve data to a binary file of doubles
        #  containing (year, mult) points, packed one after another.
        self.dblFile = dblFile

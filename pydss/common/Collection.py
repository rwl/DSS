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


class Collection(object):
    """Base Class for all DSS collection classes.  Keeps track of objects of each class, dispatches edits, etc.
    """

    def __init__(self, nProperties=0, propertyName='', propertyHelp='', propertyIdxMap='', saved=False, elementList=None):
        """Initialises a new 'Collection' instance.
        """

        self.nProperties = nProperties


        self.propertyName = propertyName


        self.propertyHelp = propertyHelp

        #: Maps property to internal command number.
        self.propertyIdxMap = propertyIdxMap


        self.saved = saved

        self.elementList = [] if elementList is None else elementList


    def add_elementList(self, *elementList):
        for obj in elementList:
            self.elementList.append(obj)

    def remove_elementList(self, *elementList):
        for obj in elementList:
            self.elementList.remove(obj)


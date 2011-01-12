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

from dss.common.Named import Named

from dss.common.Utilities import CheckForBlanks

class DSSObject(Named):

    def ClearPropSeqArray(self):
        self.PropSeqCount = 0
        for i in range(self.ParentClass.NumProperties):
            self.PrpSequence[i] = 0


    def __init__(self, ParClass, DSSObjType=0, DSSClassName='', classIndex=0,
            dirty=False, flag=False, propSequence=0.0, parentClass=None):
        """Initialises a new 'DSSObject' instance.
        """
        super(self).__init__(ParClass.Name)

        self.DSSObjType = 0  # PD, PC, Monitor, CondCode, etc.
        self.ParentClass = ParClass
        self.ClassIndex = 0
        self.HasBeenSaved = False
        #: General purpose Flag for each object  don't assume inited.
        self.Flag = False

        #: PD, PC, Monitor, CondCode, etc.
        self.DSSObjType = DSSObjType

        self.DSSClassName = DSSClassName

        #: Index into the class collection list.
        self.classIndex = classIndex

        self.dirty = dirty

        #: General purpose Flag for each object - don't assume inited.
        self.flag = flag

        self.propSequence = propSequence

        self.parentClass = [] if parentClass is None else parentClass

        ## Protected
        self.PropSeqCount = 0
        self.PropertyValue = []
        self.PrpSequence = []


    def DumpProperties(self, F, Complete=False):
        F.write('\n')
        F.write('New ' + self.DSSClassName + '.' + self.Name + '\n')


    def Edit(self):
        """Allow Calls to edit from object itself."""
        self.ParentClass.Active = self.ClassIndex
        return self.ParentClass.Edit()


    def GetPropertyValue(self, Index=0):
        # Use dssclass.propertyindex to get index by name
        # Default Behavior for all DSS Objects
        return self.PropertyValue[Index]


    def _Get_PropertyValue(self, Index=0):
        # This is virtual function that may call routine
        return self.GetPropertyValue(Index)

    def _Set_PropertyValue(self, Index=0, Value=""):
        self.PropertyValue[Index] = Value

        # Keep track of the order in which this property was accessed
        # for Save Command
        self.PropSeqCount += 1
        self.PrpSequence[Index] = self.PropSeqCount

    PropertyValue = property(_Get_PropertyValue, _Set_PropertyValue)


    def InitPropertyValues(self, ArrayOffset=0):
        self.PropertyValue[ArrayOffset + 1] = ''  # Like Property

        # Clear propertySequence Array  after initialization
        self.ClearPropSeqArray()


    def SaveWrite(self, F):
        # Write only properties that were explicitly set in the
        # final order they were actually set}
        iProp = self.GetNextPropertySet(0)  # Works on ActiveDSSObject
        while iProp > 0:
            pc = self.ParentClass
            F.write(' ', pc.PropertyName[pc.RevPropertyIdxMap[iProp]])
            F.write('=', CheckForBlanks( self.PropertyValue[iProp] ))
            iProp = self.GetNextPropertySet(iProp)


    def GetNextPropertySet(self, idx=0):
        """Find next larger property sequence number
        return 0 if none found"""
        Smallest = 9999999  # some big number

        if idx > 0:
            idx = self.PrpSequence[idx]
        for i in range(self.ParentClass.NumProperties):
            if self.PrpSequence[i] > idx:
                if self.PrpSequence[i] < Smallest:
                    Smallest = self.PrpSequence[i]
                    return i
        return 0


    def _Get_Name(self):
        return self.LocalName

    def _Set_Name(self, Value=""):
        """If renamed, then let someone know so hash list can be updated"""
        if len(self.LocalName) > 0:
            self.ParentClass.ElementNamesOutOfSynch = True
        self.LocalName = Value

    Name = property(_Get_Name, _Set_Name)

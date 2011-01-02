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

class DSSObject(object):

    def __init__(self, DSSObjType=0, DSSClassName='', classIndex=0,
            dirty=False, flag=False, propSequence=0.0, parentClass=None):
        """Initialises a new 'DSSObject' instance.
        """
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

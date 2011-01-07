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

class Electrickery(object):

    def __init__(self, wireData=None, lineGeometries=None, growthShapes=None,
            lineCodes=None, loadShapes=None, spectrums=None, executives=None):
        """Initialises a new 'Electrickery' instance.
        """
        self.wireData = [] if wireData is None else wireData

        self.lineGeometries = [] if lineGeometries is None else lineGeometries

        self.growthShapes = [] if growthShapes is None else growthShapes

        self.lineCodes = [] if lineCodes is None else lineCodes

        self.loadShapes = [] if loadShapes is None else loadShapes

        self.spectrums = [] if spectrums is None else spectrums

        self.executives = [] if executives is None else executives

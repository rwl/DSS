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

    def __init__(self, wireData=None, lineGeometries=None, growthShapes=None, lineCodes=None, loadShapes=None, spectrums=None, executives=None):
        """Initialises a new 'Electrickery' instance.
        """
        self.wireData = [] if wireData is None else wireData

        self.lineGeometries = [] if lineGeometries is None else lineGeometries

        self.growthShapes = [] if growthShapes is None else growthShapes

        self.lineCodes = [] if lineCodes is None else lineCodes

        self.loadShapes = [] if loadShapes is None else loadShapes

        self.spectrums = [] if spectrums is None else spectrums

        self.executives = [] if executives is None else executives


    def add_wireData(self, *wireData):
        for obj in wireData:
            self.wireData.append(obj)

    def remove_wireData(self, *wireData):
        for obj in wireData:
            self.wireData.remove(obj)

    def add_lineGeometries(self, *lineGeometries):
        for obj in lineGeometries:
            self.lineGeometries.append(obj)

    def remove_lineGeometries(self, *lineGeometries):
        for obj in lineGeometries:
            self.lineGeometries.remove(obj)

    def add_growthShapes(self, *growthShapes):
        for obj in growthShapes:
            self.growthShapes.append(obj)

    def remove_growthShapes(self, *growthShapes):
        for obj in growthShapes:
            self.growthShapes.remove(obj)

    def add_lineCodes(self, *lineCodes):
        for obj in lineCodes:
            self.lineCodes.append(obj)

    def remove_lineCodes(self, *lineCodes):
        for obj in lineCodes:
            self.lineCodes.remove(obj)

    def add_loadShapes(self, *loadShapes):
        for obj in loadShapes:
            self.loadShapes.append(obj)

    def remove_loadShapes(self, *loadShapes):
        for obj in loadShapes:
            self.loadShapes.remove(obj)

    def add_spectrums(self, *spectrums):
        for obj in spectrums:
            self.spectrums.append(obj)

    def remove_spectrums(self, *spectrums):
        for obj in spectrums:
            self.spectrums.remove(obj)

    def add_executives(self, *executives):
        for obj in executives:
            self.executives.append(obj)

    def remove_executives(self, *executives):
        for obj in executives:
            self.executives.remove(obj)


from dss.shared.Polar import (Polar,)


class PolarImpl(object, Polar):
    mag = None
    ang = None

    def __init__(self, mag, ang):
        self.mag = mag
        self.ang = ang

    def getMag(self):
        return self.mag

    def setMag(self, mag):
        self.mag = mag

    def getAng(self):
        return self.ang

    def setAng(self, ang):
        self.ang = ang

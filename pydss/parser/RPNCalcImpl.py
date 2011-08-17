from dss.parser.RPNCalc import (RPNCalc,)


class RPNCalcImpl(object, RPNCalc):
    MaxStackSize = 10
    DegToRad = 3.14159265359 / 180.0
    RadToDeg = 1.0 / DegToRad
    stack = [None] * MaxStackSize

    def __init__(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.MaxStackSize):
                break
            self.stack[i] = 0.0

    def aCosDeg(self):
        self.stack[0] = self.RadToDeg * self.Math.acos(self.stack[0])

    def add(self):
        self.stack[1] = self.stack[0] + self.stack[1]
        self.rollDn()

    def aSinDeg(self):
        self.stack[0] = self.RadToDeg * self.Math.asin(self.stack[0])

    def aTanDeg(self):
        self.stack[0] = self.RadToDeg * self.Math.atan(self.stack[0])

    def aTan2Deg(self):
        self.stack[1] = self.RadToDeg * self.Math.atan2(self.stack[1], self.stack[0])
        self.rollDn()

    def cosDeg(self):
        self.stack[0] = self.Math.cos(self.DegToRad * self.stack[0])

    def divide(self):
        self.stack[1] = self.stack[1] / self.stack[0]
        self.rollDn()

    def getX(self):
        return self.stack[0]

    def getY(self):
        return self.stack[1]

    def getZ(self):
        return self.stack[2]

    def multiply(self):
        self.stack[1] = self.stack[1] * self.stack[0]
        self.rollDn()

    def rollDn(self):
        _0 = True
        i = 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.MaxStackSize):
                break
            self.stack[i - 1] = self.stack[i]

    def rollUp(self):
        _0 = True
        i = 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.MaxStackSize):
                break
            self.stack[i] = self.stack[i - 1]

    def setX(self, x):
        self.rollUp()
        self.stack[0] = x

    def setY(self, y):
        self.stack[1] = y

    def setZ(self, z):
        self.stack[2] = z

    def sinDeg(self):
        self.stack[0] = self.Math.sin(self.DegToRad * self.stack[0])

    def sqrt(self):
        self.stack[0] = self.Math.sqrt(self.stack[0])

    def square(self):
        self.stack[0] = self.Math.pow(self.stack[0], 2)

    def subtract(self):
        self.stack[1] = self.stack[1] - self.stack[0]
        self.rollDn()

    def swapXY(self):
        Temp = self.stack[0]
        self.stack[0] = self.stack[1]
        self.stack[1] = Temp

    def tanDeg(self):
        self.stack[0] = self.Math.tan(self.DegToRad * self.stack[0])

    def yToTheXPower(self):
        self.stack[1] = self.Math.pow(self.stack[1], self.stack[0])
        self.rollDn()

    def enterPi(self):
        self.rollUp()
        self.stack[0] = self.Math.PI

    def eToTheX(self):
        self.stack[0] = self.Math.exp(self.stack[0])

    def natLog(self):
        self.stack[0] = self.Math.log(self.stack[0])

    def tenLog(self):
        self.stack[0] = self.Math.log10(self.stack[0])

    def inv(self):
        self.stack[0] = 1.0 / self.stack[0]

from dss.shared.impl.PolarImpl import (PolarImpl,)
from org.apache.commons.math.complex.Complex import (ComplexUtils,)
# from org.apache.commons.math.complex.Complex import (Complex,)
# from org.apache.commons.math.complex.ComplexUtils import (ComplexUtils,)


class ComplexUtil(object):

    def __init__(self):
        super(ComplexUtil, self)()

    @classmethod
    def pclx(cls, magn, angle):
        return Complex(magn * cls.Math.cos(angle), magn * cls.Math.sin(angle))

    @classmethod
    def polarDeg2Complex(cls, r, theta):
        """@param r the modulus of the complex number to create
        @param theta the argument of the complex number to create IN DEGREES
        """
        theta = theta / 57.29577951
        return ComplexUtils.polar2Complex(r, theta)

    @classmethod
    def invert(cls, c):
        dnom = (c.getReal() * c.getReal()) + (c.getImaginary() * c.getImaginary())
        return Complex(c.getReal() / dnom, -c.getImaginary() / dnom)

    @classmethod
    def divide(cls, c, rhs):
        """Return the quotient of this complex number and the given real number.
        @param rhs the real number
        @return the complex number quotient
        """
        return Complex(c.getReal() / rhs, c.getImaginary() / rhs)

    @classmethod
    def degArg(cls, c):
        arg = c.getArgument()
        return (arg * 180.0) / cls.Math.PI

    @classmethod
    def asArray(cls, c):
        return [c.getReal(), c.getImaginary()]

    @classmethod
    def complexToPolarDeg(cls, a):
        return PolarImpl(a.abs(), cls.degArg(a))

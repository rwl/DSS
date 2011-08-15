package com.epri.dss.shared.impl;

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexUtils;

import com.epri.dss.shared.Polar;

public class ComplexUtil {

	private ComplexUtil() {
		super();
	}

	public static Complex pclx(double magn, double angle) {
		return new Complex(magn * Math.cos(angle), magn * Math.sin(angle));
	}

	/**
	 *
	 * @param r the modulus of the complex number to create
	 * @param theta the argument of the complex number to create IN DEGREES
	 */
	public static Complex polarDeg2Complex(double r, double theta) {
		theta = theta / 57.29577951;
		return ComplexUtils.polar2Complex(r, theta);
	}

	public static Complex invert(Complex c) {
		double dnom = c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary();
		return new Complex(c.getReal() / dnom, -c.getImaginary() / dnom);
	}

	/**
	 * Return the quotient of this complex number and the given real number.
	 * @param rhs the real number
	 * @return the complex number quotient
	 */
	public static Complex divide(Complex c, double rhs) {
		return new Complex(c.getReal() / rhs, c.getImaginary() / rhs);
	}

	public static double degArg(Complex c) {
		double arg = c.getArgument();
		return arg * 180.0 / Math.PI;
	}

	public static double[] asArray(Complex c) {
		return new double[] {c.getReal(), c.getImaginary()};
	}

	public static Polar complexToPolarDeg(Complex a) {
		return new PolarImpl(a.abs(), degArg(a));
	}

}

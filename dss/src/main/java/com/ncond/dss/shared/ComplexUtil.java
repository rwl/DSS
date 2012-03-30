/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.shared;

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexUtils;

public class ComplexUtil {

	private ComplexUtil() {}

	public static Complex pclx(double magn, double angle) {
		return new Complex(magn * Math.cos(angle), magn * Math.sin(angle));
	}

	/**
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
	 *
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

	public static double[] complexToPolarDeg(Complex a) {
		return new double[] {a.abs(), degArg(a)};
	}


	public static double[] toArray(Complex c) {
		return new double[] {c.getReal(), c.getImaginary()};
	}

	public static double[] toArray(Complex[] x) {
		double[] a = new double[x.length * 2];
		for (int i = 0; i < x.length; i++) {
			a[(2 * i)] = x[i].getReal();
			a[(2 * i) + 1] = x[i].getImaginary();
		}
		return a;
	}

	public static double[] toArray(Complex[] x, int x_offset) {
		int i;
		double[] a;
		Complex z;

		a = new double[(2 * x.length) - (2 * x_offset)];

		for (i = 0; i < x.length - x_offset; i++) {
			z = x[i + x_offset];
			a[(2 * i)] = z.getReal();
			a[(2 * i) + 1] = z.getImaginary();
		}
		return a;
	}

	public static void fromArray(double[] x, Complex[] z) {
		double re, im;

		for (int i = 0; i < z.length; i++) {
			re = x[(2 * i)];
			im = x[(2 * i) + 1];

			z[i] = new Complex(re, im);
		}
	}

	public static void fromArray(double[] x, int x_skip, Complex[] z, int z_offset) {
		int i, j;
		double re, im;

		j = 0;
		for (i = x_skip * 2; i < x.length; i+=2) {
			re = x[i];
			im = x[i + 1];

			z[z_offset + j] = new Complex(re, im);
			j++;
		}
	}

}

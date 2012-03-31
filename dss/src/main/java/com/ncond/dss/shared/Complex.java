package com.ncond.dss.shared;


public class Complex {

	public static final Complex ZERO = new Complex(0.0, 0.0);

	public static final Complex ONE = new Complex(1.0, 0.0);

	private final double imaginary;

	private final double real;

	public Complex(double real, double imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
	}

	public double abs() {
		return Math.sqrt(real * real + imaginary * imaginary);
	}

	public double ang() {
		return Math.atan2(real, imaginary);
	}

	public Complex add(Complex addend) {
		return new Complex(real + addend.real(), imaginary
				+ addend.imag());
	}

	public Complex conj() {
		return new Complex(real, -imaginary);
	}

	public Complex div(Complex divisor) {
		double dnom = divisor.real() * divisor.real()
			+ divisor.imag() * divisor.imag();

		return new Complex(
			(real * divisor.real() + imaginary * divisor.imag()) / dnom,
			(imaginary * divisor.real() - real * divisor.imag()) / dnom);
	}

	/**
	 * Return the quotient of this complex number and the given real number.
	 *
	 * @param divisor the real number
	 * @return the complex number quotient
	 */
	public Complex div(double divisor) {
		return new Complex(real / divisor, imaginary / divisor);
	}

	public double argDeg() {
		return arg() * 180.0 / Math.PI;
	}

	public Complex mult(Complex factor) {
		return new Complex(
			real * factor.real() - imaginary * factor.imag(),
			real * factor.imag() + imaginary * factor.real()
		);
	}

	public Complex mult(double b) {
		return new Complex(real * b, imaginary * b);
	}

	public Complex neg() {
		return new Complex(-real, -imaginary);
	}


	public Complex sub(Complex B) {
		return new Complex(
			real - B.real(),
			imaginary - B.imag());
	}

	public Complex ln() {
		// algorithm: ln of mag + j(angle), radians
		double[] x = toPolar();
		return new Complex(Math.log(x[0]), x[1]);
	}

	public double[] toPolar() {
		return new double[] { abs(), ang() };
	}

	public double[] toPolarDeg() {
		return new double[] {abs(), argDeg()};
	}

	public double arg() {
		return Math.atan2(imaginary, real);
	}

	public Complex sqrt() {
		// algorithm: sqrt of magnitude/ half the angle
		double[] x = toPolar();
		return fromPolar(Math.sqrt(x[0]), x[1] / 2.0);
	}

	public Complex inv() {
		double dnom = real * real + imaginary * imaginary;
		return new Complex(real / dnom, -imaginary / dnom);
	}

	public double[] asArray() {
		return new double[] {real, imaginary};
	}

	public double real() {
		return real;
	}

	public double imag() {
		return imaginary;
	}

	@Override
	public String toString() {
		if (imaginary < 0.0) {
			return real + " - j" + Math.abs(imaginary);
		} else {
			return real + " + j" + imaginary;
		}
	}

	public static Complex fromPolar(double mag, double ang) {
	        return new Complex(
	        	mag * Math.cos(ang),
	        	mag * Math.sin(ang)
	        );
	}

	/**
	 * @param r the modulus of the complex number to create
	 * @param theta the argument of the complex number to create IN DEGREES
	 */
	public static Complex fromPolarDeg(double r, double theta) {
		theta = theta / 57.29577951;
		return Complex.fromPolar(r, theta);
	}

	public static double[] toArray(Complex[] x) {
		double[] a = new double[x.length * 2];
		for (int i = 0; i < x.length; i++) {
			a[(2 * i)] = x[i].real();
			a[(2 * i) + 1] = x[i].imag();
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
			a[(2 * i)] = z.real();
			a[(2 * i) + 1] = z.imag();
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

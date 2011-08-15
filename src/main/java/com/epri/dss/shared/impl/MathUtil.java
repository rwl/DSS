package com.epri.dss.shared.impl;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;
import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.CMatrix;

public abstract class MathUtil {

	/* Symmetrical component conversion matrices */
	private static final CMatrix As2p = getAs2p(3);
	private static final CMatrix Ap2s = getAp2s(3);
//	private static final CMatrix ClarkeF = getClarkeF(3);
//	private static final CMatrix ClarkeR = getClarkeR(3);

//	private static final double SIN2PI3 = Math.sin(2.0 * Math.PI / 3.0);

	private static CMatrix getAMatrix(int order) {
		CMatrix Amat = new CMatrixImpl(order);

		Complex a  = new Complex(-0.5, 0.866025403);
		Complex aa = new Complex(-0.5,-0.866025403);

		for (int i = 0; i < 3; i++) Amat.setElemSym(0, i, Complex.ONE);
		Amat.setElement(1, 1, aa);
		Amat.setElement(2, 2, aa);
		Amat.setElemSym(1, 2, a);

		return Amat;
	}

	private static CMatrix getAs2p(int order) {
		return getAMatrix(order);
	}

	private static CMatrix getAp2s(int order) {
		CMatrix Ap2s = getAMatrix(order);
		Ap2s.invert();
		return Ap2s;
	}

//	/**
//	 * Forward Clarke.
//	 */
//	private static CMatrix getClarkeF(int order) {
//
//		CMatrix ClarkeF = new CMatrixImpl(order);
//		ClarkeF.setElement(0, 0, new Complex(1.0, 0.0) );
//		ClarkeF.setElement(0, 1, new Complex(-0.5,0.0) );
//		ClarkeF.setElement(0, 2, new Complex(-0.5,0.0) );
//
//		ClarkeF.setElement(1, 1, new Complex(SIN2PI3, 0.0) );
//		ClarkeF.setElement(1, 2, new Complex(-SIN2PI3,0.0) );
//
//		ClarkeF.setElement(2, 0, new Complex(0.5, 0.0) );
//		ClarkeF.setElement(2, 1, new Complex(0.5, 0.0) );
//		ClarkeF.setElement(2, 2, new Complex(0.5, 0.0) );
//
//		ClarkeF.multByConst(2.0 / 3.0);  // multiply all elements by a const 2/3
//
//		return ClarkeF;
//	}

//	/**
//	 * Reverse Clarke.
//	 */
//	private static CMatrix getClarkeR(int order) {
//
//		CMatrix ClarkeR = new CMatrixImpl(order);
//		ClarkeR.setElement(0, 0, new Complex(1.0, 0.0) );
//		ClarkeR.setElement(1, 0, new Complex(-0.5,0.0) );
//		ClarkeR.setElement(2, 0, new Complex(-0.5,0.0) );
//
//		ClarkeR.setElement(1, 1, new Complex(SIN2PI3, 0.0) );
//		ClarkeR.setElement(2, 1, new Complex(-SIN2PI3,0.0) );
//
//		ClarkeR.setElement(0, 2, new Complex(1.0, 0.0) );
//		ClarkeR.setElement(1, 2, new Complex(1.0, 0.0) );
//		ClarkeR.setElement(2, 3, new Complex(1.0, 0.0) );
//
//		return null;
//	}

	private static int L;
	private static int index(int i, int j) {
		return (j - 1) * L + i;
	}
	/**
	 * Matrix= reference to matrix of doulbes
	 *   Norder = order of matrix  (assumed square)
	 *   Error 	= 0 if no error;
	 *          = 1 if not enough heap to alloc temp array
	 *          = 2 if matrix can't be inverted
	 *
	 * This routine will invert a non-symmetric matrix.  Index is assumed to
	 * follow the FORTRAN standard, not the Pascal standard.  That is the data
	 * are ordered by first subscript first, then second subscript.  This routine
	 * computes its own indexing, leaving nothing to the whims of a cantankerous compiler.
	 *
	 * It assumes that the matrix is dimensioned to exactly the number of elements
	 * needed.  Apologies to Fortran users who are accustomed to over dimensioning
	 * stuff.
	 *
	 */
	public static void ETKInvert(double[] A, int norder, MutableInt error) {  // TODO Check zero based indexing

		int j, k, LL, M, i;
		int[] LT;
		double RMY, T1;

		L = norder;
		error.setValue(0);

		/* Allocate LT */
		LT = new int[L];
		if (LT.length == 0) {
			error.setValue(1);
			return;
		}

		/* Zero LT */
		for (j = 0; j < L; j++)
			LT[j] = 0;

		T1 = 0.0;

		/* M Loop */
		// initialize a safe value of k
		k = 1;
		for (M = 0; M < L; M++) {
			for (LL = 0; LL < L; LL++) {
				if (LT[LL] != 1) {
					RMY = Math.abs(A[index(LL, LL)]) - Math.abs(T1);
					if (RMY > 0.0) {
						T1 = A[index(LL, LL)];
						k = LL;
					}
				}
			}

			/* Error Check. If RMY ends up zero, matrix is non-inversible */
			RMY = Math.abs(T1);
			if (RMY == 0.0) {
				error.setValue(2);
				return;
			}

			T1 = 0.0;
			LT[k] = 1;
			for (i = 0; i < L; i++)
				if (i != k)
					for (j = 0; j < L; j++)
						if (j != k)
							A[index(i, j)] = A[index(i, j)] - A[index(i, k)] * A[index(k, j)] / A[index(k, k)];

			A[index(k, k)] = -1.0 / A[index(k, k)];

			for (i = 0; i < L; i++) {
				if (i != k) {
					A[index(i, k)] = A[index(i, k)] * A[index(k, k)];
					A[index(k, i)] = A[index(k, i)] * A[index(k, k)];
				}
			}
		}

		for (j = 0; j < L; j++)
			for (k = 0; k < L; k++)
				A[index(j, k)] = -A[index(j, k)];

		LT = null;
	}

	public static void phase2SymComp(Complex[] Vph, Complex[] V012) {
		Ap2s.MVMult(V012, Vph);
	}

	public static void phase2SymComp(Complex Vph, Complex[] V012) {
		phase2SymComp(new Complex[] {Vph}, V012);
	}

	public static void symComp2Phase(Complex[] Vph, Complex[] V012) {
		As2p.MVMult(Vph, V012);
	}

	/**
	 * Computes total complex power given terminal voltages and currents.
	 */
	public static Complex terminalPowerIn(Complex[] V, Complex[] I, int nPhases) {
		Complex result = Complex.ZERO;
		for (int j = 0; j < nPhases; j++)
			result = result.add(V[j].multiply( I[j].conjugate() ));
		return result;
	}

	/**
	 * Compute complex power in kW and kvar in each phase.
	 */
	public static void calckPowers(Complex[] kWkVAr, Complex[] V, Complex[] I, int n) {
		for (int j = 0; j < n; j++)
			kWkVAr[j] = V[j].multiply(I[j].conjugate()).multiply(0.001);
	}

	public static void calckPowers(Complex[] kWkVAr, Complex[] V, Complex I, int n) {
		calckPowers(kWkVAr, V, new Complex[] {I}, n);
	}

	/**
	 * Returns a normally distributed random variable.
	 */
	public static double gauss(double mean, double stdDev) {
		double A = 0.0;
		for (int i = 0; i < 12; i++)
			A = A + Math.random();

		return (A - 6.0) * stdDev + mean;
	}

	/**
	 * Generates a quasi-lognormal distribution with approx 50% of values
	 * from 0 to Mean and the remainder from Mean to infinity.
	 */
	public static double quasiLognormal(double mean) {
		return Math.exp(gauss(0.0, 1.0)) * mean;
	}

	public static double sum(double[] data, int count) {
		double sum = 0;
		for (int i = 0; i < count; i++)
			sum += data[i];
		return sum;
	}

	public static void RCDMeanandStdDev(double[] pData, int nData, MutableDouble mean, MutableDouble stdDev) {
		double[] data = new double[100];
		double S;

		data = pData;  // make a double pointer
		if (nData == 1) {
			mean.setValue(data[0]);
			stdDev.setValue(data[0]);
			return;
		}
		mean.setValue(sum(data, (nData)) / nData);
		S = 0;  // sum differences from the mean, for greater accuracy
		for (int i = 0; i < nData; i++)
			S = S + Math.pow(mean.doubleValue() - data[i], 2);
		stdDev.setValue(Math.sqrt(S / (nData - 1)));
	}

	public static void curveMeanAndStdDev(double[] pY, double[] pX, int N, MutableDouble mean, MutableDouble stdDev) {
		double s, dy1, dy2;
		int i;

		if (N == 1) {
			mean.setValue(pY[0]);
			stdDev.setValue(pY[0]);
			return;
		}
		s = 0;
		for (i = 0; i < N - 1; i++)
			s += 0.5 * (pY[i] + pY[i + 1]) * (pX[i + 1] - pX[i]);
		mean.setValue(s / (pX[N] - pX[0]));  // TODO Check zero based indexing

		s = 0;  // sum differences from the mean, for greater accuracy
		for (i = 0; i < N - 1; i++) {
			dy1 = (pY[i] - mean.doubleValue());
			dy2 = (pY[i + 1] - mean.doubleValue());
			s += 0.5 * (dy1 * dy1 + dy2 * dy2) * (pX[i + 1] - pX[i]);
		}

		stdDev.setValue(Math.sqrt(s / (pX[N] - pX[0])));
	}

	/**
	 * Parallel two complex impedances.
	 */
	public static Complex parallelZ(Complex Z1, Complex Z2) {
		Complex denom = Z1.add(Z2) ;
		if ((Math.abs(denom.getReal()) > 0.0) || (Math.abs(denom.getImaginary()) > 0.0)) {
			return Z1.multiply(Z2).divide(denom);
		} else {  /* Error */
			return Complex.ZERO;
		}
	}

	/**
	 * z = I0(a)
	 */
	public static Complex Bessel_I0(Complex a) {
		int maxTerm = 1000;
		double epsilonSqr = 1.0E-20;

		int i;
		double sizeSqr = 1;
		Complex term;
		Complex zSQR25;

		Complex result = Complex.ONE;  // term 0
		zSQR25 = a.multiply(a).multiply(0.25);
		term = zSQR25;
		result = result.add(zSQR25);  // term 1

		i = 0;
		while ((i < maxTerm) && (sizeSqr > epsilonSqr)) {
			term = zSQR25.multiply(term);
			i += 1;
			term = ComplexUtil.divide(term, Math.pow(i, 2));
			result = result.add(term);  // sum = sum + term
			sizeSqr = Math.pow(term.getReal(), 2) + Math.pow(term.getImaginary(), 2);
		}

		return result;
	}

	/**
	 *
	 * @return Nema unbalance
	 */
	public static double pctNemaUnbalance(Complex[] Vph) {
		int i;
		double VAvg;
		double maxDiff;
		double[] VMag = new double[3];

		for (i = 0; i < 3; i++)
			VMag[i] = Vph[i].abs();

		VAvg = 0.0;
		for (i = 0; i < 3; i++)
			VAvg = VAvg + VMag[i];
		VAvg = VAvg / 3.0;

		maxDiff = 0.0;
		for (i = 0; i < 3; i++)
			maxDiff = Math.max(maxDiff, Math.abs( VMag[i] - VAvg ));

		if (VAvg != 0.0) {
			return maxDiff / VAvg * 100.0;  // pct difference
		} else {
			return 0;
		}
	}

	public static double getXR(Complex a) {
		double result;
		if (a.getReal() != 0.0) {
			result = a.getImaginary() / a.getReal();
			if (Math.abs(result) > 9999.0)
				result = 9999.0;
		} else{
			result = 9999.0;;
		}
		return result;
	}

	public static double sqr(double a) {
		return Math.pow(a, 2);
	}

}

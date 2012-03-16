package com.ncond.dss.general;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.LineUnits;
import com.ncond.dss.shared.MathUtil;

/**
 * This class returns a matrix ordered by phases first then remaining conductors.
 * Assumes phases are defined first.
 *
 * Manages the geometry data and calculates the impedance matrices for an overhead line
 *
 * Usage: Create with number of conductors you want
 *     Specify the number of phases. The first conductors you define with
 *     be the phases. Other conductors may be considered neutral.
 *
 *     Uses GMR for power frequency calcs so that answers match published
 *     data.
 *
 *     You only have to set R or GMR. The other will default. However, you should set
 *     both for better accuracy.
 *
 *     When you ask for Zmatrix or YCmatrix you get the full matrix unless you have executed
 *     a Kron reduction or Reduce function. Reduce eleminates all non phases. If you
 *     want the full detailed model, DO NOT REDUCE!
 *
 */
public class LineConstants {

	public static final double E0 = 8.854e-12;  // dielectric constant  F/m
	public static final double MU0 = 12.56637e-7; // hy/m
	public static final double TWO_PI = DSS.TWO_PI;

	public static final Complex CDOUBLEONE = new Complex(1, 1);
	public static final double B1 = 1.0 / (3.0 * Math.sqrt(2.0));;
	public static final double B2 = 1.0 / 16.0;
	public static final double B3 = B1 / 3.0 / 5.0;
	public static final double B4 = B2 / 4.0 / 6.0;
	public static final double D2 = B2 * Math.PI / 4.0;
	public static final double D4 = B4 * Math.PI / 4.0;
	public static final double C2 = 1.3659315;
	public static final double C4 = C2 + 1.0 / 4.0 + 1.0 / 6.0;

	protected int numConds;
	protected int numPhases;
	protected double[] X;
	protected double[] Y;
	protected double[] Rdc;  // ohms/m
	protected double[] Rac;  // ohms/m
	protected double[] GMR;  // m
	protected double[] radius;

	protected CMatrix Zmatrix;   // in ohms/m
	protected CMatrix YcMatrix;  // siemens/m   --- jwC

	protected CMatrix Zreduced;   // these two do not exist until Kron reduction
	protected CMatrix YcReduced;  // is executed

	protected double frequency;  // frequency for which impedances are computed
	protected double w;  // 2piF
	protected double rhoEarth;  // ohm-m
	protected Complex me;  // factor for earth impedance
	protected boolean rhoChanged;

	public LineConstants(int numConductors) {
		numConds = numConductors;
		setNPhases(numConds);

		X = new double[numConds];
		Y = new double[numConds];
		GMR = new double[numConds];
		radius = new double[numConds];
		Rdc = new double[numConds];
		Rac = new double[numConds];

		/* Initialize to not set */
		for (int i = 0; i < numConds; i++) {
			GMR[i] = -1.0;
			radius[i] = -1.0;
			Rdc[i] = -1.0;
		}

		Zmatrix = new CMatrix(numConds);
		YcMatrix = new CMatrix(numConds);

		setFrequency(-1.0);  // not computed
		setRhoEarth(100.0);  // default value
		rhoChanged= true;

		Zreduced = null;
		YcReduced = null;
	}

	/**
	 * Force a calc of impedances.
	 *
	 * Compute base Z and Yc matrices in ohms/m for this frequency and earth impedance.
	 */
	public void calc(double f) {
		Complex Zi, Zspacing;
		boolean powerFreq;
		Complex Lfactor;
		int i, j;
		double dij, dijp, Pfactor;
		int reducedSize;

		//rhoEarth = rho;
		setFrequency(f);  // this has side effects

		reducedSize = (Zreduced != null) ? Zreduced.order() : 0;

		Zreduced = null;
		YcReduced = null;

		Zmatrix.clear();
		YcMatrix.clear();

		/* For less than 1 kHz use GMR to better match published data */

		Lfactor = new Complex(0.0, w * MU0/ TWO_PI);

		powerFreq = f < 1000.0 && f > 40.0;

		/* Self impedances */

		for (i = 0; i < numConds; i++) {
			Zi = getZint(i);
			if (powerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = Lfactor.multiply(Math.log(1.0 / GMR[i]));  // use GMR
			} else {
				Zspacing = Lfactor.multiply(Math.log(1.0 / radius[i]));
			}

			Zmatrix.set(i, i, Zi.add(Zspacing.add(getZe(i, i))));
		}

		/* Mutual impedances */

		for (i = 0; i < numConds; i++) {
			for (j = 0; j < i; j++) {
				dij = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				Zmatrix.setSym(i, j, Lfactor.multiply( Math.log(1.0 / dij) ).add(getZe(i, j)));
			}
		}

		/* Capacitance matrix */

		Pfactor = -1.0 / TWO_PI / E0 / w;  // include frequency

		/* Construct P matrix and then invert */

		for (i = 0; i < numConds; i++)
			YcMatrix.set(i, i, new Complex(0.0, Pfactor * Math.log(2.0 * Y[i] / radius[i])));

		for (i = 0; i < numConds; i++) {
			for (j = 0; j < i; j++) {
				dij = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				dijp = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] + Y[j], 2));  // distance to image j
				YcMatrix.setSym(i, j, new Complex(0.0, Pfactor * Math.log(dijp / dij)));
			}
		}

		YcMatrix.invert();  // now should be nodal C matrix

		if (reducedSize > 0) kron(reducedSize - 1);  // was reduced so reduce again to same size

		/* else the Zmatrix is OK as last computed */

		rhoChanged = false;
	}

	public boolean conductorsInSameSpace(String[] errorMessage) {
		int i, j;
		double dij;

		/* Check all conductors to make sure none occupy the same space or are defined at 0,0 */
		boolean same = false;

		/* Check for 0 Y coordinate */
		for (i = 0; i < numConds; i++) {
			if (Y[i] <= 0.0) {
				same = true;
				errorMessage[0] += String.format("Conductor %d height must be  > 0. ", i);
				return same;
			}
		}

		/* Check for overlapping conductors */
		for (i = 0; i < numConds; i++) {
			for (j = i + 1; j < numConds; j++) {
				dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				if (dij < (radius[i] + radius[j])) {
					same = true;
					errorMessage[0] += String.format("Conductors %d and %d occupy the same space.", i, j);
					return same;
				}
			}
		}

		return same;
	}

	public double getGMR(int i, LineUnits units) {
		return GMR[i] * LineUnits.fromMeters(units);
	}

	public double getRac(int i, LineUnits units) {
		return Rac[i] * LineUnits.fromPerMeter(units);
	}

	public double getRadius(int i, LineUnits units) {
		return radius[i] * LineUnits.fromMeters(units);
	}

	public double getRdc(int i, LineUnits units) {
		return Rdc[i] * LineUnits.fromPerMeter(units);
	}

	public double getX(int i, LineUnits units) {
		return X[i] * LineUnits.fromMeters(units);
	}

	public double getY(int i, LineUnits units) {
		return Y[i] * LineUnits.fromMeters(units);
	}

	/**
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 *
	 * Makes a new YCmatrix and correct for lengths and units as it copies.
	 * Uses the reduced Zmatrix by default if it exists.
	 */
	public CMatrix getYcMatrix(double f, double length, LineUnits units) {
		int newSize;
		double unitLengthConversion;
		CMatrix Yc;
		Complex[] YcValues;

		Yc = (YcReduced != null) ? YcReduced : YcMatrix;

		newSize = Yc.order();
		CMatrix YYc = new CMatrix(newSize);

		YYc.copyFrom(Yc);
		YcValues = YYc.asArray();
		newSize = YYc.order();
		unitLengthConversion = LineUnits.fromPerMeter(units) * length;
		for (int i = 0; i < newSize * newSize; i++)
			YcValues[i] = YcValues[i].multiply(unitLengthConversion);  // a = a * b

		return YYc;
	}

	/**
	 * Earth return impedance at present frequency for ij element.
	 */
	public Complex getZe(int i, int j) {
		Complex lnArg, hterm, xterm, Ze = null;
		double mij , thetaij, Dij, Yi, Yj, re, im;
		double term1, term2, term3, term4, term5;

		Yi = Math.abs(Y[i]);
		Yj = Math.abs(Y[j]);

		switch (DSS.activeEarthModel) {
		case SIMPLECARSON:
			Ze = new Complex(w * MU0 / 8.0, (w * MU0 / TWO_PI) * Math.log(658.5 * Math.sqrt(rhoEarth / frequency)));
			break;
		case FULLCARSON:
			/* notation from Tleis book Power System Modelling and Fault Analysis */
			if (i == j) {
				thetaij = 0.0;
				Dij = 2.0 * Yi;
			} else {
				Dij = Math.sqrt(Math.pow((Yi + Yj) + Math.pow(X[i] - X[j], 2), 2));
				thetaij = Math.acos((Yi + Yj) / Dij);
			}
			mij = 2.8099e-3 * Dij * Math.sqrt(frequency / rhoEarth);

			re = Math.PI / 8.0 - B1 * mij * Math.cos(thetaij) +
				B2 * Math.pow(mij, 2) * (Math.log(Math.exp(C2) / mij) * Math.cos(2.0 * thetaij) + thetaij * Math.sin(2.0 * thetaij)) +
				B3 * mij * mij * mij * Math.cos(3.0 * thetaij) - D4 * mij * mij * mij * mij * Math.cos(4.0 * thetaij);

			term1 = 0.5 * Math.log(1.85138 / mij);
			term2 = B1 * mij * Math.cos(thetaij);
			term3 = -D2 * Math.pow(mij, 2) * Math.cos(2.0 * thetaij);
			term4 = B3 * mij * mij * mij * Math.cos(3.0 * thetaij);
			term5 = -B4 * mij * mij * mij * mij * (Math.log(Math.exp(C4) / mij) * Math.cos(4.0 * thetaij) + thetaij * Math.sin(4.0 * thetaij));
			im = term1 + term2 + term3 + term4 + term5;
			Ze = new Complex(re, im);
			Ze = new Complex(Ze.getReal(), Ze.getImaginary() + 0.5 * Math.log(Dij));  // correction term to work with DSS structure

			Ze = Ze.multiply(w * MU0 / Math.PI);
			break;

		case DERI:
			if (i != j) {
				hterm = new Complex(Yi + Yj, 0.0).add( ComplexUtil.invert(me).multiply(2.0) );
				xterm = new Complex(X[i] - X[j], 0.0);
				lnArg = hterm.multiply(hterm).add(xterm.multiply(xterm)).sqrt();
				Ze = new Complex(0.0, w * MU0 / TWO_PI).multiply(lnArg.log());
			} else {
				hterm = new Complex(Yi, 0.0).add( ComplexUtil.invert(me) );
				Ze = new Complex(0.0, w * MU0 / TWO_PI).multiply( hterm.multiply(2.0).log() );
			}
			break;
		}

		return Ze;
	}

	/**
	 * Internal impedance of i-th conductor for present frequency.
	 */
	public Complex getZint(int i) {
		Complex alpha, I0I1, Zi = null;

		switch (DSS.activeEarthModel) {
		case SIMPLECARSON:
			Zi = new Complex(Rac[i], w * MU0/ (8 * Math.PI));
			break;
		case FULLCARSON:  // no skin effect
			Zi = new Complex(Rac[i], w * MU0 / (8 * Math.PI));
			break;
		case DERI:  // with skin effect model
			/* Assume round conductor */
			alpha = CDOUBLEONE.multiply(Math.sqrt(frequency * MU0 / Rdc[i]));
			if (alpha.abs() > 35.0) {
				I0I1 = Complex.ONE;
			} else {
				I0I1 = MathUtil.besselI0(alpha).divide(MathUtil.besselI0(alpha));
			}
			Zi = CDOUBLEONE.multiply(I0I1).multiply(Math.sqrt(Rdc[i] * frequency * MU0) / 2.0);
			break;
		}

		return Zi;
	}

	/**
	 * Will auto recalc the impedance matrices if frequency is different
	 * converts to desired units when executed.
	 *
	 * Makes a new Zmatrix and correct for lengths and units as it copies.
	 * Uses the reduced Zmatrix by default if it exists.
	 */
	public CMatrix getZMatrix(double f, double length, LineUnits units) {
		int newSize, i;
		double unitLengthConversion;
		CMatrix Z;
		Complex[] Zvalues;

		if (f != frequency || rhoChanged) {
			calc(f);  // only recalcs if f changed or rho earth changed
		}

		Z = (Zreduced != null) ? Zreduced : Zmatrix;

		newSize = Z.order();
		CMatrix ZZ = new CMatrix(newSize);

		ZZ.copyFrom(Z);  // gets ohms/meter
		Zvalues = ZZ.asArray();  // ptr to the values in the new copy
		newSize = ZZ.order();

		/* Convert the values by units and length */
		unitLengthConversion = LineUnits.fromPerMeter(units) * length;
		for (i = 0; i < newSize * newSize; i++)
			Zvalues[i] = Zvalues[i].multiply(unitLengthConversion);  // a = a * b

		return null;
	}

	/**
	 * Performs a Kron reduction leaving first nOrder rows.
	 */
	public void kron(int nOrder) {
		CMatrix Ztemp = Zmatrix;
		boolean firstTime = true;

		if (frequency >= 0.0 && nOrder > 0 && nOrder < numConds) {

			/* Reduce computed matrix one row/col at a time until it is norder */

			while (Ztemp.order() > nOrder) {
				Zreduced = Ztemp.kron(Ztemp.order());  // eliminate last row

				if (!firstTime) Ztemp = null;  // Ztemp points to intermediate matrix
				Ztemp = Zreduced;

				firstTime = false;
			}

			/* Extract norder x norder portion of Yc matrx */
			YcReduced = new CMatrix(nOrder);
			for (int i = 0; i < nOrder; i++)
				for (int j = 0; j < nOrder; j++)
					YcReduced.set(i, j, YcMatrix.get(i, j));

			/* Left with reduced matrix */
		}
	}

	/**
	 * Kron reduce to num phases only.
	 */
	public void reduce() {
		kron(numPhases);
	}

	protected void setFrequency(double value) {
		setFrequency(value);
		w = TWO_PI * frequency;
		me = new Complex(0.0, w * MU0 / rhoEarth).sqrt();
	}

	public void setRhoEarth(double value) {
		if (value != rhoEarth) rhoChanged = true;
		rhoEarth = value;
		if (frequency >= 0.0)
			me = new Complex(0.0, w * MU0 / rhoEarth).sqrt();
	}

	public void setGMR(int i, LineUnits units, double value) {
		if (i >= 0 && i < numConds) {
			GMR[i] = value * LineUnits.toMeters(units);
			if (radius[i] < 0.0)
				radius[i] = GMR[i] / 0.7788;  // equivalent round conductor
		}
	}

	public void setNPhases(int value) {
		numPhases = value;
	}

	public void setRac(int i, LineUnits units, double value) {
		if (i >= 0 && i < numConds)
			Rac[i] = value * LineUnits.toPerMeter(units);
	}

	public void setRadius(int i, LineUnits units, double value) {
		if (i >= 0 && i <= numConds) {
			radius[i] = value * LineUnits.toMeters(units);
			if (GMR[i] < 0.0) {
				GMR[i] = radius[i] * 0.7788;  // default to round conductor
			}
		}
	}

	public void setRdc(int i, LineUnits units, double value) {
		if (i >= 0 && i < numConds) {
			Rdc[i] = value * LineUnits.toPerMeter(units);
		}
	}

	public void setX(int i, LineUnits units, double value) {
		if (i >= 0 && i < numConds) {
			X[i] = value * LineUnits.toMeters(units);
		}
	}

	public void setY(int i, LineUnits units, double value) {
		if (i >= 0 && i < numConds) {
			Y[i] = value * LineUnits.toMeters(units);
		}
	}

	public double getRhoEarth() {
		return rhoEarth;
	}

	public int getNumConds() {
		return numConds;
	}

	public double getFrequency() {
		return frequency;
	}

	public int getNPhases() {
		return numPhases;
	}

}

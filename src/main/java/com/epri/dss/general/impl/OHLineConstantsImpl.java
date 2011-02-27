package com.epri.dss.general.impl;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.shared.CMatrix;

public class OHLineConstantsImpl implements OHLineConstants {
	
	private static final Complex C1_j1 = new Complex(1.0, 1.0);
	private static final double b1 = 1.0 / (3.0 * Math.sqrt(2.0));
	private static final double b2 = 1.0 / 16.0;
	private static final double b3 = b1 / 3.0 / 5.0;
	private static final double b4 = b2 / 4.0 / 6.0;
	private static final double d2 = b2 * Math.PI / 4.0;
	private static final double d4 = b4 * Math.PI / 4.0;
	private static final double c2 = 1.3659315;
	private static final double c4 = c2 + 1.0 / 4.0 + 1.0 / 6.0;
	
	private int NumConds;
	private int NumPhases;
	private double[] X;
	private double[] Y;
	private double[] Rdc;   // ohms/m
	private double[] Rac;   // ohms/m
	private double[] GMR;   // m
	private double[] radius;

	private CMatrix Zmatrix;   // in ohms/m
	private CMatrix YCmatrix;   // siemens/m   --- jwC

	/* These two do not exist until Kron Reduction is executed */
	private CMatrix Zreduced;  
	private CMatrix YCreduced;  

	private double Frequency;  // Frequency for which impedances are computed
	private double w;  // 2piF
	private double rhoEarth;  // ohm-m
	private Complex me; // factor for earth impedance
	private boolean RhoChanged;

	public OHLineConstantsImpl(int NumConductors) {

		this.NumConds = NumConductors;
		this.NumPhases = this.NumConds;

		X = new double[this.NumConds];
		Y = new double[this.NumConds];
		GMR = new double[this.NumConds];
		radius = new double[this.NumConds];
		Rdc = new double[this.NumConds];
		Rac = new double[this.NumConds];

		/* Initialize to not set */
		for (int i = 0; i < this.NumConds; i++) {
			this.GMR[i] = -1.0;
			this.radius[i] = -1.0;
			this.Rdc[i] = -1.0;
		}

		this.Zmatrix = new CMatrixImpl(this.NumConds);
		this.YCmatrix = new CMatrixImpl(this.NumConds);

		this.Frequency = -1.0;  // not computed
		this.rhoEarth = 100.0;  // default value
		this.RhoChanged= true;

		this.Zreduced = null;
		this.YCreduced = null;
	}
	
	/**
	 * Force a calc of impedances.
	 * 
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	public void calc(double f) {
		Complex Zi, Zspacing;
		boolean PowerFreq;
		Complex Lfactor;
		int i, j;
		double Dij, Dijp, Pfactor;
		int ReducedSize;

		// RhoEarth = rho;
		Frequency = f;  // this has side effects

		if (Zreduced != null) { 
			ReducedSize = Zreduced.getNOrder();
			Zreduced = null;
		} else {
			ReducedSize = 0;
		}
		
		if (YCreduced != null)
			YCreduced = null;
		Zreduced = null;
		YCreduced = null;

		Zmatrix.clear();
		YCmatrix.clear();

		/* For less than 1 kHz use GMR to better match published data */

		Lfactor = new Complex(0.0, w * mu0/ TwoPi);
		if ((f < 1000.0) && (f > 40.0)) {
			PowerFreq = true;
		} else {
			PowerFreq = false;
		}

		/* Self Impedances */

		for (i = 0; i < NumConds; i++) {
			Zi = getZint(i);
			if (PowerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = Lfactor.multiply( Math.log(1.0 / GMR[i]) );  // use GMR
			} else {
				Zspacing = Lfactor.multiply( Math.log(1.0 / radius[i]) );
			}

			Zmatrix.setElement(i, i, Zi.add( Zspacing.add(getZe(i, i)) ));
		}

		/* Mutual Impedances */

		for (i = 0; i < NumConds; i++) {
			for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
				Dij = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				Zmatrix.setElemSym(i, j, Lfactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
		}

		/* Capacitance Matrix */

		Pfactor = -1.0 / TwoPi / e0 / w; // include frequency

		/* Construct P matrix and then invert */

		for (i = 0; i < NumConds; i++) 
			YCmatrix.setElement(i, i, new Complex(0.0, Pfactor * Math.log(2.0 * Y[i] / radius[i])));

		for (i = 0; i < NumConds; i++) {
			for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
				Dij = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				Dijp = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] + Y[j], 2));  // distance to image j
				YCmatrix.setElemSym(i, j, new Complex(0.0, Pfactor * Math.log(Dijp / Dij)));
			}
		}

		YCmatrix.invert();  // now should be nodal C matrix

		if (ReducedSize > 0)
			Kron(ReducedSize);  // Was reduced so reduce again to same size

		/* Else the Zmatrix is OK as last computed */

		RhoChanged = false;
	}
	
	public boolean conductorsInSameSpace(String ErrorMessage) {
		int i, j;
		double Dij;

		/* Check all conductors to make sure none occupy the same space or are defined at 0,0 */
		boolean Result = false;

		/* Check for 0 Y coordinate */
		for (i = 0; i < NumConds; i++) {
			if (Y[i] <= 0.0) {
				Result = true;
				ErrorMessage = String.format("Conductor %d height is 0. Height coordinate must be  > 0. ", i);
				return Result;
			}
		}

		/* Check for overlapping conductors */
		for (i = 0; i < NumConds; i++) {
			for (j = i + 1; j < NumConds; j++) {  // TODO Check zero based indexing
				Dij = Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				if (Dij < (radius[i] + radius[j])) {
					Result = true;
					ErrorMessage = String.format("Conductors %d and %d occupy the same space.", i, j);
					return Result;
				}
			}
		}
			
		return Result;
	}
	
	public double getGMR(int i, int units) {
		return GMR[i] * LineUnits.fromMeters(units);
	}
	
	public double getRac(int i, int units) {
		return Rac[i] * LineUnits.fromPerMeter(units);
	}
	
	public double getRadius(int i, int units) {
		return radius[i] * LineUnits.fromMeters(units);
	}
	
	public double getRdc(int i, int units) {
		return Rdc[i] * LineUnits.fromPerMeter(units);
	}
	
	public double getX(int i, int units) {
		return X[i] * LineUnits.fromMeters(units);
	}
	
	public double getY(int i, int units) {
		return Y[i] * LineUnits.fromMeters(units);
	}
	
	/**
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 * 
	 * Makes a new YCmatrix and correct for lengths and units as it copies.
	 * Uses the reduced Zmatrix by default if it exists.
	 */
	public CMatrix getYCmatrix(double f, double Lngth, int Units) {
		int NewSize;
		double UnitLengthConversion;
		CMatrix YC;
		Complex[] YCValues;

		if (YCreduced != null) {
			YC = YCreduced;
		} else {
			YC = YCmatrix;
		}

		NewSize = YC.getNOrder();
		CMatrix Result = new CMatrixImpl(NewSize);

		Result.copyFrom(YC);
		YCValues = Result.asArray(NewSize);
		UnitLengthConversion = LineUnits.fromPerMeter(Units) * Lngth;
		for (int i = 0; i < NewSize * NewSize; i++) 
			YCValues[i] = YCValues[i].multiply(UnitLengthConversion);  // a=a*b

		return Result;
	}
	
	/**
	 * Earth return impedance at present frequency for ij element.
	 */
	public Complex getZe(int i, int j) {
		Complex LnArg, hterm, xterm, Result;
		double mij , thetaij, Dij;
		double term1, term2, term3, term4, term5;

		switch (DSSGlobals.getInstance().getActiveEarthModel()) {
		case DSSGlobals.SIMPLECARSON:
			Result = new Complex(w * mu0 / 8.0, (w * mu0 / TwoPi) * Math.log(658.5 * Math.sqrt(rhoEarth / Frequency)));
		case DSSGlobals.FULLCARSON:
			/* notation from Tleis book Power System Modelling and Fault Analysis */
			if (i == j) {
				thetaij = 0.0;
				Dij = 2.0 * Y[i];
			} else {
				Dij = Math.sqrt(Math.pow((Y[i] + Y[j]) + Math.pow(X[i] - X[j], 2), 2));
				thetaij = Math.acos( (Y[i] + Y[j]) / Dij );
			}
			mij = 2.8099e-3 * Dij * Math.sqrt(Frequency / rhoEarth);

			double re = Math.PI / 8.0 - b1 * mij * Math.cos(thetaij) + b2 * Math.pow(mij, 2) * (Math.log(Math.exp(c2) / mij) * Math.cos(2.0 * thetaij) + thetaij * Math.sin(2.0 * thetaij))
					+ b3 * mij * mij * mij * Math.cos(3.0 * thetaij) - d4 * mij * mij * mij * mij * Math.cos(4.0 * thetaij);

			term1 = 0.5 * Math.log(1.85138 / mij);
			term2 = b1 * mij * Math.cos(thetaij);
			term3 = -d2 * Math.pow(mij, 2) * Math.cos(2.0 * thetaij);
			term4 = b3 * mij * mij * mij * Math.cos(3.0 * thetaij);
			term5 = -b4 * mij * mij * mij * mij * (Math.log(Math.exp(c4) / mij) * Math.cos(4.0 * thetaij) + thetaij * Math.sin(4.0 * thetaij));
			double im = term1 + term2 + term3 + term4 + term5;
			Result = new Complex(re, im);
			Result = new Complex(Result.getReal(), Result.getImaginary() + 0.5 * Math.log(Dij));  // correction term to work with DSS structure

			Result = Result.multiply(w * mu0 / Math.PI);

		case DSSGlobals.DERI:
			if (i != j) {
				hterm  = new Complex(Y[i] + Y[j], 0.0).add( me.invert().multiply(2.0) );
				xterm  = new Complex(X[i] - X[j], 0.0);
				LnArg  = hterm.multiply(hterm).add(xterm.multiply(xterm)).sqrt();
				Result = new Complex(0.0, w * mu0 / TwoPi).multiply(LnArg.log());
			} else {
				hterm  = new Complex(Y[i], 0.0).add(me.invert());
				Result = new Complex(0.0, w * mu0 / TwoPi).multiply( hterm.multiply(2.0).log() );
			}
		}
		
		return Result;
	}
	
	/**
	 * Internal impedance of i-th conductor for present frequency.
	 */
	public Complex getZint(int i) {
		Complex Alpha, I0I1, Result;

		switch (DSSGlobals.getInstance().getActiveEarthModel()) {
		case DSSGlobals.SIMPLECARSON:
			Result = new Complex(Rac[i], w * mu0/ (8 * Math.PI));
		case DSSGlobals.FULLCARSON:  // no skin effect
			Result = new Complex(Rac[i], w * mu0 / (8 * Math.PI));
		case DSSGlobals.DERI:  // with skin effect model
			/* Assume round conductor */
			Alpha = C1_j1.multiply( Math.sqrt(Frequency * mu0 / Rdc[i]) );
			if (Alpha.abs() > 35.0) {
				I0I1 = Complex.ONE;
			} else {
				I0I1 = MathUtil.Bessel_I0(Alpha).divide( MathUtil.Bessel_I1(Alpha)) );
			}
			Result = C1_j1.multiply(I0I1).multiply( Math.sqrt(Rdc[i] * Frequency * mu0) / 2.0 );
		}

		return Result;
	}

	/**
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 * 
	 * Makes a new Zmatrix and correct for lengths and units as it copies.
	 * Uses the reduced Zmatrix by default if it exists.
	 */
	public CMatrix getZmatrix(double f, double Lngth, int Units) {
		int NewSize, i;
		double UnitLengthConversion;
		CMatrix Z;
		Complex[] ZValues;

		if ((f != Frequency) || RhoChanged)
			calc(f);  // only recalcs if f changed or rho earth changed

		if (Zreduced != null) {
			Z = Zreduced;
		} else {
			Z = Zmatrix;
		}

		NewSize = Z.getNOrder();
		CMatrix Result = new CMatrixImpl(NewSize);

		Result.copyFrom(Z);  // gets ohms/meter
		ZValues = Result.asArray(NewSize);  // ptr to the values in the new copy
		/* Convert the values by units and length */
		UnitLengthConversion = LineUnits.fromPerMeter(Units) * Lngth;
		for (i = 0; i < NewSize * NewSize; i++) 
			ZValues[i] = ZValues[i].multiply(UnitLengthConversion);  // a=a*b

		return null;
	}
	
	/**
	 * Performs a Kron reduction leaving first Norder rows.
	 */
	public void Kron(int nOrder) {

		CMatrix Ztemp  = Zmatrix;
		CMatrix YCTemp = YCmatrix;
		boolean FirstTime = true;

		if ((Frequency >= 0.0) && (nOrder > 0) && (nOrder < NumConds)) {

			if (Zreduced != null)
				Zreduced = null;
			if (YCreduced != null)
				YCreduced = null;

			/* Reduce computed matrix one row/col at a time until it is norder */

			while (Ztemp.getNOrder() > nOrder) {

				Zreduced = Ztemp.kron(Ztemp.getNOrder());    // Eliminate last row
				YCreduced = YCTemp.kron(Ztemp.getNOrder());

				if (!FirstTime) {
					Ztemp = null;  // Ztemp points to intermediate matrix
					YCTemp = null;
				}
				Ztemp  = Zreduced;
				YCTemp = YCreduced;

				FirstTime = false;
			}
			
			/* Left with reduced matrix */
		}
	}
	
	/**
	 * Kron reduce to NumPhases only.
	 */
	public void reduce() {
		Kron(NumPhases);
	}
	
	private void setFrequency(double Value) {
		Frequency = Value;
		w = TwoPi * Frequency;
		me = new Complex(0.0, w * mu0 / rhoEarth).sqrt();
	}
	
	public void setRhoEarth(double Value) {
		if (Value != rhoEarth)
			RhoChanged = true;
		rhoEarth = Value;
		if (Frequency >= 0.0)
			me = new Complex(0.0, w * mu0 / rhoEarth).sqrt();
	}
	
	public void setGMR(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds)) {  // TODO Check zero based indexing
			GMR[i] = Value * LineUnits.toMeters(units);
			if (radius[i] < 0.0)
				radius[i] = GMR[i] / 0.7788;  // equivalent round conductor
		}
	}
	
	public void setNPhases(int Value) {
		NumPhases = Value;
	}
	
	public void setRac(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds))  // TODO Check zero based indexing
			Rac[i] = Value * LineUnits.toPerMeter(units);
	}
	
	public void setRadius(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds)) {  // TODO Check zero based indexing
			radius[i] = Value * LineUnits.toMeters(units);
			if (GMR[i] < 0.0)
				GMR[i] = radius[i] * 0.7788; // Default to round conductor
		}
	}
	
	public void setRdc(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds))
			Rdc[i] = Value * LineUnits.toPerMeter(units);
	}
	
	public void setX(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds))
			X[i] = Value * LineUnits.toMeters(units);
	}
	
	public void setY(int i, int units, double Value) {
		if ((i > 0) && (i <= NumConds))
			Y[i] = Value * LineUnits.toMeters(units);
	}
	
	public double getRhoEarth() {
		return rhoEarth;
	}
	
	private double getFrequency() {
		return Frequency;
	}
	
	public int getNPhases() {
		return NumPhases;
	}

}

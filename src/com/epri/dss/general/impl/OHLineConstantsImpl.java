package com.epri.dss.general.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.OHLineConstants;

public class OHLineConstantsImpl implements OHLineConstants {
	
	private static final double e0 = 8.854e-12;  // dielectric constant  F/m
	private static final double mu0 = 12.56637e-7; // hy/m
	private static final double TwoPi = 6.283185307;
	
	private int NumConds;
	private int NumPhases;
	private double[] X;
	private double[] Y;
	private double[] Rdc;   // ohms/m
	private double[] Rac;   // ohms/m
	private double[] GMR;   // m
	private double[] radius;

	private DComplexMatrix2D Zmatrix;   // in ohms/m
	private DComplexMatrix2D YCmatrix;   // siemens/m   --- jwC

	/* These two do not exist until Kron Reduction is executed */
	private DComplexMatrix2D Zreduced;  
	private DComplexMatrix2D YCreduced;  

	private double Frequency;  // Frequency for which impedances are computed
	private double w;  // 2piF
	private double rhoEarth;  // ohm-m
	private double[] me; // factor for earth impedance
	private boolean RhoChanged;

	public OHLineConstantsImpl(int NumConductors) {
		// TODO Auto-generated constructor stub
	}
	
	public double getGMR(int i, int units) {
		return 0.0;
	}
	
	public double getRadius(int i, int units) {
		return 0.0;
	}
	
	public double getRdc(int i, int units) {
		return 0.0;
	}
	
	public double getRac(int i, int units) {
		return 0.0;
	}
	
	public double getX(int i, int units) {
		return 0.0;
	}
	
	public double getY(int i, int units) {
		return 0.0;
	}
	
	/*
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 */
	public DComplexMatrix2D getYCmatrix(double f, double Lngth, int Units) {
		return null;
	}
	
	/* Earth return impedance at present frequency for ij element */
	public double[] getZe(int i, int j) {
		return null;
	}
	
	/* Internal impedance of i-th conductor for present frequency */
	public double[] getZint(int i) {
		return null;
	}

	/*
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 */
	public DComplexMatrix2D getZmatrix(double f, double Lngth, int Units) {
		return null;
	}
	
	public void setGMR(int i, int units, double Value) {
		
	}
	
	public void setRadius(int i, int units, double Value) {
		
	}
	
	public void setRdc(int i, int units, double Value) {
		
	}
	
	public void setRac(int i, int units, double Value) {
		
	}
	
	public void setX(int i, int units, double Value) {
		
	}
	
	public void setY(int i, int units, double Value) {
		
	}
	
	public void setRhoEarth(double Value) {
		
	}
	
	public double getRhoEarth() {
		return rhoEarth;
	}
	
	private void setFrequency(double Value) {
		
	}
	
	private double getFrequency() {
		return 0.0;
	}
	
	public void setNPhases(int Value) {
		
	}
	
	public int getNPhases() {
		return NumPhases;
	}
	
	public boolean conductorsInSameSpace(String ErrorMessage) {
		return false;
	}
	
	/* Force a calc of impedances */
	public void calc(double f) {
		
	}
	
	/* Performs a Kron reduction leaving first Norder rows */
	public void Kron(int nOrder) {
		
	}
	
	/* Kron reduce to NumPhases only */
	public void reduce() {
		
	}

}

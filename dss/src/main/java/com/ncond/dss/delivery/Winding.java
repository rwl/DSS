/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.types.Connection;

import static java.lang.Math.pow;


public class Winding {

	private Connection connection;

	private double kVLL, Vbase, kVA, puTap;

	// on transformer MVABase (1st winding)
	private double Rpu, RNeut, XNeut;

	private double Y_PPM;  // anti float reactance adder

	/* Tap changer data */
	private double tapIncrement, minTap, maxTap;
	private int numTaps;

	public Winding() {
		super();

		connection = Connection.WYE;
		kVLL = 12.47;
		Vbase = kVLL / DSS.SQRT3 * 1000.0;
		kVA = 1000.0;
		puTap = 1.0;
		Rpu = 0.002;
		RNeut = -1.0;  // default to open - make user specify connection
		XNeut = 0.0;
		computeAntiFloatAdder(1.0e-6, kVA / 3.0 / 1000.0);  //  1 PPM

		tapIncrement = 0.00625;
		numTaps = 32;
		maxTap = 1.10;
		minTap = 0.90;
	}

	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		Y_PPM = -PPM_Factor / (pow(Vbase, 2) / VABase1ph);
	}

	public Connection getConnection() {
		return connection;
	}

	public double getKVA() {
		return kVA;
	}

	public double getKVLL() {
		return kVLL;
	}

	public double getMaxTap() {
		return maxTap;
	}

	public double getPuTap() {
		return puTap;
	}

	public double getRNeut() {
		return RNeut;
	}

	public double getMinTap() {
		return minTap;
	}

	public int getNumTaps() {
		return numTaps;
	}

	public double getVbase() {
		return Vbase;
	}

	public double getRpu() {
		return Rpu;
	}

	public double getTapIncrement() {
		return tapIncrement;
	}

	public double getXNeut() {
		return XNeut;
	}

	public double getY_PPM() {
		return Y_PPM;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setKVLL(double kVLL) {
		this.kVLL = kVLL;
	}

	public void setKVA(double kVA) {
		this.kVA = kVA;
	}

	public void setPuTap(double puTap) {
		this.puTap = puTap;
	}

	public void setRpu(double rpu) {
		Rpu = rpu;
	}

	public void setRNeut(double rNeut) {
		RNeut = rNeut;
	}

	public void setMinTap(double minTap) {
		this.minTap = minTap;
	}

	public void setMaxTap(double maxTap) {
		this.maxTap = maxTap;
	}

	public void setNumTaps(int numTaps) {
		this.numTaps = numTaps;
	}

	public void setVbase(double vbase) {
		Vbase = vbase;
	}

	public void setXNeut(double xNeut) {
		XNeut = xNeut;
	}

	public void setY_PPM(double y_PPM) {
		Y_PPM = y_PPM;
	}

	public void setTapIncrement(double tapIncrement) {
		this.tapIncrement = tapIncrement;
	}

}

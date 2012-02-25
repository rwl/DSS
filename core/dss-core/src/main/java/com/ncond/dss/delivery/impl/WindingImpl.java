package com.ncond.dss.delivery.impl;

import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.delivery.Winding;

public class WindingImpl implements Winding {

	private int connection;
	private double kVLL,
		VBase,
		kVA,
		puTap,
		Rpu,  // on transformer MVABase (1st winding)
		RNeut,
		XNeut;
	private double Y_PPM;  // Anti float reactance adder

	/* Tap changer data */
	private double tapIncrement,
		minTap,
		maxTap;
	private int numTaps;

	public WindingImpl() {
		super();
		connection = 0;
		kVLL       = 12.47;
		VBase      = kVLL / DSSGlobals.SQRT3 * 1000.0;
		kVA        = 1000.0;
		puTap      = 1.0;
		Rpu        = 0.002;
		RNeut      = -1.0;  // default to open - make user specify connection
		XNeut      = 0.0;
		computeAntiFloatAdder(1.0e-6, kVA / 3.0 / 1000.0);  //  1 PPM

		tapIncrement = 0.00625;
		numTaps      = 32;
		maxTap       = 1.10;
		minTap       = 0.90;
	}

	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		Y_PPM = -PPM_Factor / (Math.pow(VBase, 2) / VABase1ph);
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection(int conn) {
		connection = conn;
	}

	public double getKVLL() {
		return kVLL;
	}

	public void setKVLL(double kvll) {
		kVLL = kvll;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double base) {
		VBase = base;
	}

	public double getKVA() {
		return kVA;
	}

	public void setKVA(double kva) {
		kVA = kva;
	}

	public double getPUTap() {
		return puTap;
	}

	public void setPUTap(double tap) {
		puTap = tap;
	}

	public double getRpu() {
		return Rpu;
	}

	public void setRpu(double rpu) {
		Rpu = rpu;
	}

	public double getRNeut() {
		return RNeut;
	}

	public void setRNeut(double rneut) {
		RNeut = rneut;
	}

	public double getXNeut() {
		return XNeut;
	}

	public void setXNeut(double xneut) {
		XNeut = xneut;
	}

	public double getY_PPM() {
		return Y_PPM;
	}

	public void setY_PPM(double y) {
		Y_PPM = y;
	}

	public double getTapIncrement() {
		return tapIncrement;
	}

	public void setTapIncrement(double increment) {
		tapIncrement = increment;
	}

	public double getMinTap() {
		return minTap;
	}

	public void setMinTap(double min) {
		minTap = min;
	}

	public double getMaxTap() {
		return maxTap;
	}

	public void setMaxTap(double max) {
		maxTap = max;
	}

	public int getNumTaps() {
		return numTaps;
	}

	public void setNumTaps(int num) {
		numTaps = num;
	}

}

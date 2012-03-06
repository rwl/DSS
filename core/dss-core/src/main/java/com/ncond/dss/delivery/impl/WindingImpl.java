package com.ncond.dss.delivery.impl;

import com.ncond.dss.common.impl.DSS;
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
		VBase      = kVLL / DSS.SQRT3 * 1000.0;
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

	@Override
	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		Y_PPM = -PPM_Factor / (Math.pow(VBase, 2) / VABase1ph);
	}

	@Override
	public int getConnection() {
		return connection;
	}

	@Override
	public void setConnection(int conn) {
		connection = conn;
	}

	@Override
	public double getKVLL() {
		return kVLL;
	}

	@Override
	public void setKVLL(double kvll) {
		kVLL = kvll;
	}

	@Override
	public double getVBase() {
		return VBase;
	}

	@Override
	public void setVBase(double base) {
		VBase = base;
	}

	@Override
	public double getKVA() {
		return kVA;
	}

	@Override
	public void setKVA(double kva) {
		kVA = kva;
	}

	@Override
	public double getPUTap() {
		return puTap;
	}

	@Override
	public void setPUTap(double tap) {
		puTap = tap;
	}

	@Override
	public double getRpu() {
		return Rpu;
	}

	@Override
	public void setRpu(double rpu) {
		Rpu = rpu;
	}

	@Override
	public double getRNeut() {
		return RNeut;
	}

	@Override
	public void setRNeut(double rneut) {
		RNeut = rneut;
	}

	@Override
	public double getXNeut() {
		return XNeut;
	}

	@Override
	public void setXNeut(double xneut) {
		XNeut = xneut;
	}

	@Override
	public double getY_PPM() {
		return Y_PPM;
	}

	@Override
	public void setY_PPM(double y) {
		Y_PPM = y;
	}

	@Override
	public double getTapIncrement() {
		return tapIncrement;
	}

	@Override
	public void setTapIncrement(double increment) {
		tapIncrement = increment;
	}

	@Override
	public double getMinTap() {
		return minTap;
	}

	@Override
	public void setMinTap(double min) {
		minTap = min;
	}

	@Override
	public double getMaxTap() {
		return maxTap;
	}

	@Override
	public void setMaxTap(double max) {
		maxTap = max;
	}

	@Override
	public int getNumTaps() {
		return numTaps;
	}

	@Override
	public void setNumTaps(int num) {
		numTaps = num;
	}

}

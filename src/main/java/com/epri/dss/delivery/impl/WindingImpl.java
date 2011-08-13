package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.delivery.Winding;

public class WindingImpl implements Winding {

	private int Connection;
	private double kvll,
		VBase,
		kva,
		puTap,
		Rpu,  // on transformer MVABase (1st winding)
		Rneut,
		Xneut;
	private double Y_PPM;  // Anti float reactance adder

	/* Tap changer data */
	private double TapIncrement,
		MinTap,
		MaxTap;
	private int NumTaps;

	public WindingImpl() {
		super();
		this.Connection = 0;
		this.kvll       = 12.47;
		this.VBase      = kvll / DSSGlobals.SQRT3 * 1000.0;
		this.kva        = 1000.0;
		this.puTap      = 1.0;
		this.Rpu        = 0.002;
		this.Rneut      = -1.0;  // default to open - make user specify connection
		this.Xneut      = 0.0;
		computeAntiFloatAdder(1.0e-6, kva / 3.0 / 1000.0);  //  1 PPM

		this.TapIncrement = 0.00625;
		this.NumTaps      = 32;
		this.MaxTap       = 1.10;
		this.MinTap       = 0.90;
	}

	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		Y_PPM = -PPM_Factor / (Math.pow(VBase, 2) / VABase1ph);
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public double getKVLL() {
		return kvll;
	}

	public void setKVLL(double kvll) {
		this.kvll = kvll;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public double getKVA() {
		return kva;
	}

	public void setKVA(double kva) {
		this.kva = kva;
	}

	public double getPUTap() {
		return puTap;
	}

	public void setPUTap(double puTap) {
		this.puTap = puTap;
	}

	public double getRpu() {
		return Rpu;
	}

	public void setRpu(double rpu) {
		Rpu = rpu;
	}

	public double getRNeut() {
		return Rneut;
	}

	public void setRNeut(double rneut) {
		Rneut = rneut;
	}

	public double getXNeut() {
		return Xneut;
	}

	public void setXNeut(double xneut) {
		Xneut = xneut;
	}

	public double getY_PPM() {
		return Y_PPM;
	}

	public void setY_PPM(double y_PPM) {
		Y_PPM = y_PPM;
	}

	public double getTapIncrement() {
		return TapIncrement;
	}

	public void setTapIncrement(double tapIncrement) {
		TapIncrement = tapIncrement;
	}

	public double getMinTap() {
		return MinTap;
	}

	public void setMinTap(double minTap) {
		MinTap = minTap;
	}

	public double getMaxTap() {
		return MaxTap;
	}

	public void setMaxTap(double maxTap) {
		MaxTap = maxTap;
	}

	public int getNumTaps() {
		return NumTaps;
	}

	public void setNumTaps(int numTaps) {
		NumTaps = numTaps;
	}

}

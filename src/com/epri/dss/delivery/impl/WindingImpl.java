package com.epri.dss.delivery.impl;

import com.epri.dss.delivery.Winding;

public class WindingImpl implements Winding {
	
	private int Connection;
	private double kvll,
		VBase,
		kva,
		puTap,
		Rpu,      // on transformer MVABase  (1st winding)
		Rneut,
		Xneut;
	private double Y_PPM;  // Anti Float reactance adder

	/* Tap Changer Data */
	private double TapIncrement,
		MinTap,
		MaxTap;
	private int NumTaps;

	public WindingImpl() {
		// TODO Auto-generated constructor stub
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public double getKvll() {
		return kvll;
	}

	public void setKvll(double kvll) {
		this.kvll = kvll;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public double getKva() {
		return kva;
	}

	public void setKva(double kva) {
		this.kva = kva;
	}

	public double getPuTap() {
		return puTap;
	}

	public void setPuTap(double puTap) {
		this.puTap = puTap;
	}

	public double getRpu() {
		return Rpu;
	}

	public void setRpu(double rpu) {
		Rpu = rpu;
	}

	public double getRneut() {
		return Rneut;
	}

	public void setRneut(double rneut) {
		Rneut = rneut;
	}

	public double getXneut() {
		return Xneut;
	}

	public void setXneut(double xneut) {
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
	
	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		
	}

}

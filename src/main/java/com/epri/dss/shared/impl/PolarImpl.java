package com.epri.dss.shared.impl;

import com.epri.dss.shared.Polar;

public class PolarImpl implements Polar {

	private double mag;
	private double ang;

	public PolarImpl(double mag, double ang) {
		this.mag = mag;
		this.ang = ang;
	}

	public double getMag() {
		return mag;
	}
	public void setMag(double mag) {
		this.mag = mag;
	}

	public double getAng() {
		return ang;
	}
	public void setAng(double ang) {
		this.ang = ang;
	}

}

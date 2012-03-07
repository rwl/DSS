package com.ncond.dss.delivery;

import lombok.Data;

import com.ncond.dss.common.DSS;

@Data
public class Winding {

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

	public Winding() {
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

	public void computeAntiFloatAdder(double PPM_Factor, double VABase1ph) {
		Y_PPM = -PPM_Factor / (Math.pow(VBase, 2) / VABase1ph);
	}

}

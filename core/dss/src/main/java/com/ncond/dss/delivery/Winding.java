package com.ncond.dss.delivery;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.types.Connection;

@Getter @Setter
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
		Y_PPM = -PPM_Factor / (Math.pow(Vbase, 2) / VABase1ph);
	}

}

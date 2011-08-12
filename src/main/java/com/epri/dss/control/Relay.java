package com.epri.dss.control;

import com.epri.dss.general.TCC_CurveObj;

public interface Relay extends ControlClass {

	static final int NumPropsThisClass = 29;

	static final int CURRENT = 0;  /* Default */
	static final int VOLTAGE = 1;
	static final int REVPOWER = 3;
	static final int NEGCURRENT = 4;
	static final int NEGVOLTAGE = 5;
	static final int GENERIC = 6;  /* Use this for frequency, etc. Generic over/under relay */

	TCC_CurveObj getTccCurve(String curveName);

}

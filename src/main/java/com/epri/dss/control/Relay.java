package com.epri.dss.control;

import com.epri.dss.general.TCC_CurveObj;

public interface Relay extends ControlClass {

	TCC_CurveObj getTccCurve(String CurveName);
	
}

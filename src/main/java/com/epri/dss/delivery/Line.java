package com.epri.dss.delivery;

import com.epri.dss.shared.impl.Complex;

public interface Line extends PDClass {

	static final int NumPropsThisClass = 23;
	
	// 5 kvar of capacitive reactance at 345 kV to avoid open line problem;
	static final Complex CAP_EPSILON = new Complex(0.0, 4.2e-8);  
	
}

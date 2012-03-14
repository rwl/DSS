package com.ncond.dss.common.types;

public enum Connection {
	WYE,  // Y, wye, star or LN (neutral is explicit)
	DELTA;  // delta or LL (assume neutral is at zero)
}

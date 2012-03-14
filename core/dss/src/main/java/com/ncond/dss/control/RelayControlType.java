package com.ncond.dss.control;

public enum RelayControlType {
	CURRENT,  // default
	VOLTAGE,
	REVPOWER,
	NEGCURRENT,
	NEGVOLTAGE,
	GENERIC;  // use this for frequency, etc. generic over/under relay
}

package com.ncond.dss.conversion;

public enum GeneratorModel {
	CONSTANT_PQ,  // typical fixed kW negative load
	CONSTANT_Z,
	CONSTANT_PV,
	CONSTANTP_FIXEDQ,
	CONSTANTP_FIXEDQ_X,
	USER_MODEL,
	INVERTER;
}

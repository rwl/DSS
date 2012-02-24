package com.ncond.dss.conversion;

public interface PVSystem extends PCClass {

	static final int NumPVSystemRegisters = 5;  // number of energy meter registers
	static final int NumPVSystemVariables = 4;  // no state variables that need integrating.

	static final int KV         =  2;
	static final int IRRADIANCE =  3;
	static final int PF         =  4;
	static final int MODEL      =  5;
	static final int YEARLY     =  6;
	static final int DAILY      =  7;
	static final int DUTY       =  8;
	static final int T_YEARLY   =  9;
	static final int T_DAILY    = 10;
	static final int T_DUTY     = 11;
	static final int CONNECTION = 12;
	static final int KVAR       = 13;
	static final int PCTR       = 14;
	static final int PCTX       = 15;
	static final int CLASS      = 16;
	static final int INV_EFF_CURVE = 17;
	static final int TEMP        = 18;
	static final int PMPP        = 19;
	static final int P_T_CURVE   = 20;
	static final int CUT_IN      = 21;
	static final int CUT_OUT     = 22;
	static final int VMIN_PU     = 23;
	static final int VMAX_PU     = 24;
	static final int KVA         = 25;
	static final int USER_MODEL  = 26;
	static final int USER_DATA   = 27;
	static final int DEBUG_TRACE = 28;

	static final int NumPropsThisClass = 29;

	void setRegisterNames(String[] registerNames);

	String[] getRegisterNames();

	void resetRegistersAll();

	void sampleAll();

	void updateAll();

}

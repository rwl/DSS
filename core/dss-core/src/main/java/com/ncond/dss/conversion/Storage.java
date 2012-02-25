package com.ncond.dss.conversion;

public interface Storage extends PCClass {

	static int NumStorageRegisters = 6;
	static int NumStorageVariables = 7;

	static int CHARGING    = -1;
	static int IDLING      =  0;
	static int DISCHARGING =  1;

	/* Dispatch modes */
	static int DEFAULT = 0;
	static int LOAD_MODE = 1;
	static int PRICE_MODE = 2;
	static int EXTERNAL_MODE = 3;
	static int FOLLOW = 4;

	/*
	 * To add a property,
	 *   1) add a property constant to this list
	 *   2) add a handler to the case statement in the edit function
	 *   3) add a statement(s) to initPropertyValues function to initialize the string value
	 *   4) add any special handlers to dumpProperties and getPropertyValue, if needed
	 */

	static int KV         =  2;
	static int KW         =  3;
	static int PF         =  4;
	static int MODEL      =  5;
	static int YEARLY     =  6;
	static int DAILY      =  7;
	static int DUTY       =  8;
	static int DISP_MODE   =  9;
	static int IDLE_KVAR   = 10;
	static int CONNECTION = 11;
	static int KVAR       = 12;
	static int PCTR       = 13;
	static int PCTX       = 14;
	static int IDLE_KW     = 15;
	static int CLASS      = 16;
	static int DISP_OUT_TRIG= 17;
	static int DISP_IN_TRIG = 18;
	static int CHARGE_EFF  = 19;
	static int DISCHARGE_EFF = 20;
	static int PCT_KW_OUT   = 21;
	static int VMIN_PU     = 22;
	static int VMAX_PU     = 23;
	static int STATE      = 24;
	static int KVA        = 25;
	static int KW_RATED    = 26;
	static int KWH_RATED   = 27;
	static int KWH_STORED  = 28;
	static int PCT_RESERVE = 29;
	static int USER_MODEL  = 30;
	static int USER_DATA   = 31;
	static int DEBUG_TRACE = 32;
	static int PCT_KW_IN    = 33;
	static int PCT_STORED  = 34;
	static int CHARGE_TIME = 35;

	static int NumPropsThisClass = 36;  // make this agree with the last property constant

	String[] getRegisterNames();

	void setRegisterNames(String[] registerNames);

	void resetRegistersAll();

	void sampleAll();

	void updateAll();

}

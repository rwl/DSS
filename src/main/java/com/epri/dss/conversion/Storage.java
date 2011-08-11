package com.epri.dss.conversion;

public interface Storage extends PCClass {

	static int NumStorageRegisters = 6;
	static int NumStorageVariables = 5;

	static int STORE_CHARGING    = -1;
	static int STORE_IDLING      =  0;
	static int STORE_DISCHARGING =  1;

	/* Dispatch modes */
	static int STORE_DEFAULT = 0;
	static int STORE_LOADMODE = 1;
	static int STORE_PRICEMODE = 2;
	static int STORE_EXTERNALMODE = 3;
	static int STORE_FOLLOW = 4;

	/*
	 * To add a property,
	 *   1) add a property constant to this list
	 *   2) add a handler to the case statement in the edit function
	 *   3) add a statement(s) to initPropertyValues function to initialize the string value
	 *   4) add any special handlers to dumpProperties and getPropertyValue, if needed
	 */

	static int propKV         =  2;
	static int propKW         =  3;
	static int propPF         =  4;
	static int propMODEL      =  5;
	static int propYEARLY     =  6;
	static int propDAILY      =  7;
	static int propDUTY       =  8;
	static int propDISPMODE   =  9;
	static int propIDLEKVAR   = 10;
	static int propCONNECTION = 11;
	static int propKVAR       = 12;
	static int propPCTR       = 13;
	static int propPCTX       = 14;
	static int propIDLEKW     = 15;
	static int propCLASS      = 16;
	static int propDISPOUTTRIG= 17;
	static int propDISPINTRIG = 18;
	static int propCHARGEEFF  = 19;
	static int propDISCHARGEEFF = 20;
	static int propPCTKWOUT   = 21;
	static int propVMINPU     = 22;
	static int propVMAXPU     = 23;
	static int propSTATE      = 24;
	static int propKVA        = 25;
	static int propKWRATED    = 26;
	static int propKWHRATED   = 27;
	static int propKWHSTORED  = 28;
	static int propPCTRESERVE = 29;
	static int propUSERMODEL  = 30;
	static int propUSERDATA   = 31;
	static int propDEBUGTRACE = 32;
	static int propPCTKWIN    = 33;
	static int propPCTSTORED  = 34;
	static int propCHARGETIME = 35;

	static int NumPropsThisClass = 36;  // make this agree with the last property constant

	String[] getRegisterNames();

	void setRegisterNames(String[] registerNames);

	void resetRegistersAll();

	void sampleAll();

	void updateAll();

}

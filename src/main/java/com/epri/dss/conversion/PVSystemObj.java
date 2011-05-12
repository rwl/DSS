package com.epri.dss.conversion;

/**
 * The PVsystem element is essentially a generator that consists of a PV panel and an inverter.
 *
 * The PVsystem element can also produce or absorb vars within the kVA rating of the inverter.
 *
 * The PVsystem element is assumed balanced over the no. of phases defined.
 *
 * TODO:
 *   Make connection to User model
 *   Yprim for various modes
 *   Define state vars and dynamics mode behavior
 *   Complete Harmonics mode algorithm (generator mode is implemented)
 *
 */
public interface PVSystemObj extends PCElement {

	static final int propKV         =  2;
	static final int propIrradiance =  3;
	static final int propPF         =  4;
	static final int propMODEL      =  5;
	static final int propYEARLY     =  6;
	static final int propDAILY      =  7;
	static final int propDUTY       =  8;
	static final int propTYEARLY    =  9;
	static final int propTDAILY     = 10;
	static final int propTDUTY      = 11;
	static final int propCONNECTION = 12;
	static final int propKVAR       = 13;
	static final int propPCTR       = 14;
	static final int propPCTX       = 15;
	static final int propCLASS      = 16;
	static final int propInvEffCurve= 17;
	static final int propTemp       = 18;
	static final int propPmpp       = 19;
	static final int propP_T_Curve  = 20;
	static final int propCutin      = 21;
	static final int propCutout     = 22;
	static final int propVMINPU     = 23;
	static final int propVMAXPU     = 24;
	static final int propKVA        = 25;
	static final int propUSERMODEL  = 26;
	static final int propUSERDATA   = 27;
	static final int propDEBUGTRACE = 28;

	static final int NumPropsThisClass = 29;

}

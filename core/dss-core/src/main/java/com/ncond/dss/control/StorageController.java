package com.ncond.dss.control;

public interface StorageController extends ControlClass {

	static final int ELEMENT        = 0;
	static final int TERMINAL       = 1;
	static final int KW_TARGET      = 2;
	static final int KW_BAND        = 3;
	static final int PF_TARGET      = 4;
	static final int PF_BAND        = 5;
	static final int ELEMENT_LIST   = 6;
	static final int WEIGHTS        = 7;
	static final int MODE_DISCHARGE = 8;
	static final int MODE_CHARGE    = 9;
	static final int TIME_DISCHARGE_TRIGGER = 10;
	static final int TIME_CHARGE_TRIGGER    = 11;
	static final int RATE_KW       = 12;
	static final int RATE_KVAR     = 13;
	static final int RATE_CHARGE   = 14;
	static final int RESERVE       = 15;
	static final int KWH_TOTAL     = 16;
	static final int KW_TOTAL      = 17;
	static final int KWH_ACTUAL    = 18;
	static final int KW_ACTUAL     = 19;
	static final int KW_NEED       = 20;
	static final int PARTICIPATION = 21;
	static final int YEARLY        = 22;
	static final int DAILY         = 23;
	static final int DUTY          = 24;
	static final int EVENTLOG      = 25;
	static final int VAR_DISPATCH  = 26;
	static final int INHIBIT_TIME  = 27;
	static final int T_UP_RAMP     = 28;
	static final int T_FLAT        = 29;
	static final int T_DN_RAMP     = 30;
	static final int KW_THRESHOLD  = 31;

	static final int NumPropsThisClass = 32;

	/* Control modes */
	static final int FOLLOW      = 1;
	static final int LOADSHAPE   = 2;
	static final int SUPPORT     = 3;
	static final int TIME        = 4;
	static final int PEAKSHAVE   = 5;
	static final int SCHEDULE    = 6;

	/* Other constants */
	static final int RELEASE_INHIBIT = 999;

}

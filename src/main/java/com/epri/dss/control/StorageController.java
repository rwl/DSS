package com.epri.dss.control;

public interface StorageController extends ControlClass {

	static final int propELEMENT       = 0;
	static final int propTERMINAL      = 1;
	static final int propKWTARGET      = 2;
	static final int propKWBAND        = 3;
	static final int propPFTARGET      = 4;
	static final int propPFBAND        = 5;
	static final int propELEMENTLIST   = 6;
	static final int propWEIGHTS       = 7;
	static final int propMODEDISCHARGE = 8;
	static final int propMODECHARGE    = 9;
	static final int propTIMEDISCHARGETRIGGER = 10;
	static final int propTIMECHARGETRIGGER    = 11;
	static final int propRATEKW        = 12;
	static final int propRATEKVAR      = 13;
	static final int propRATECHARGE    = 14;
	static final int propRESERVE       = 15;
	static final int propKWHTOTAL      = 16;
	static final int propKWTOTAL       = 17;
	static final int propKWHACTUAL     = 18;
	static final int propKWACTUAL      = 19;
	static final int propKWNEED        = 20;
	static final int propPARTICIPATION = 21;
	static final int propYEARLY        = 22;
	static final int propDAILY         = 23;
	static final int propDUTY          = 24;
	static final int propEVENTLOG      = 25;
	static final int propVARDISPATCH   = 26;
	static final int propINHIBITTIME   = 27;

	static final int NumPropsThisClass = 28;

	/* CONTROL MODES */
	static final int MODEFOLLOW      = 1;
	static final int MODELOADSHAPE   = 2;
	static final int MODESUPPORT     = 3;
	static final int MODETIME        = 4;
	static final int MODEPEAKSHAVE   = 5;

	/* OTHER CONSTANTS */
	static final int RELEASE_INHIBIT = 999;

}

package com.epri.dss.common.impl;

import com.epri.dss.common.DSSClass;

public class DSSClassDefs {

	/* Basic element types */
	public static final int NON_PCPD_ELEM = 1;  // A circuit Element we don't want enumerated in PD and PC Elements
	public static final int PD_ELEMENT    = 2;
	public static final int PC_ELEMENT    = 3;
	public static final int CTRL_ELEMENT  = 4;
	public static final int METER_ELEMENT = 5;

	/* Specific element Types */
	public static final int MON_ELEMENT  =  1 * 8;
	public static final int DSS_OBJECT   =  2 * 8;   // Just a general DSS object, accessible to all circuits
	public static final int SOURCE       =  3 * 8;
	public static final int XFMR_ELEMENT =  4 * 8;
	public static final int SUBSTATION   =  5 * 8;  // not used
	public static final int LINE_ELEMENT =  6 * 8;
	public static final int LOAD_ELEMENT =  7 * 8;
	public static final int FAULTOBJECT  =  8 * 8;
	public static final int ENERGY_METER =  9 * 8;
	public static final int GEN_ELEMENT  = 10 * 8;
	public static final int CAP_CONTROL  = 11 * 8;
	public static final int REG_CONTROL  = 12 * 8;
	public static final int CAP_ELEMENT  = 13 * 8;
	public static final int RELAY_CONTROL = 14 * 8;
	public static final int RECLOSER_CONTROL = 15 * 8;
	public static final int FUSE_CONTROL     = 16 * 8;
	public static final int REACTOR_ELEMENT  = 17 * 8;
	public static final int FEEDER_ELEMENT   = 18 * 8;
	public static final int GEN_CONTROL      = 19 * 8;
	public static final int SENSOR_ELEMENT   = 20 * 8;
	public static final int STORAGE_ELEMENT  = 21 * 8;
	public static final int STORAGE_CONTROL  = 22 * 8;
	public static final int SWT_CONTROL      = 23 * 8;
	
	public static void createDSSClasses() {
		
	}
	
	public static void disposeDSSClasses() {
		
	}
	
	public static DSSClass getDSSClass(String ClassName) {
		return null;
	}
	
	public static boolean setObjectClass(String ObjType) {
		return false;
	}

}

package com.ncond.dss.control;

public interface RegControl extends ControlClass {

	static final int AVGPHASES = -1;
	static final int MAXPHASE  = -2;
	static final int MINPHASE  = -3;

	static final int ACTION_TAPCHANGE = 0;
	static final int ACTION_REVERSE   = 1;

	static final int NumPropsThisClass = 26;

}

package com.ncond.dss.control;

/**
 * A VVCControl is a control element that is connected to a terminal of another
 * circuit element and sends dispatch kW signals and kvar to a set of generators it controls
 *
 * A VVCControl is defined by a New command:
 *
 * New VVCControl.Name=myname Element=devclass.name terminal=[ 1|2|...] GenList = (gen1  gen2 ...)
 *
 */
public interface VVControl extends ControlClass {

	int NumPropsThisClass = 19;

}

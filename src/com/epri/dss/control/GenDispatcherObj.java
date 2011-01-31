package com.epri.dss.control;

/**
 * A GenDispatcher is a control element that is connected to a terminal of
 * another circuit element and sends dispatch kW signals to a set of generators
 * it controls.
 * 
 * A GenDispatcher is defined by a New command:
 * 
 * New GenDispatcher.Name=myname Element=devclass.name terminal=[ 1|2|...] CapacitorList = (gen1  gen2 ...)
 *
 */
public interface GenDispatcherObj extends ControlElem {
	
	boolean makeGenList();

}

package com.epri.dss.control;

/**
 * A StorageController is a control element that is connected to a terminal
 * of another circuit element and sends dispatch  signals to a fleet of energy
 * storage elements it controls.
 * 
 * A StorageController is defined by a New command:
 * 
 * New StorageController.Name=myname Element=devclass.name terminal=[ 1|2|...] Elementlist = (elem1  elem2 ...)
 * 
 * or ... ElementList = [File=filename] where storage class elements are listed
 * one to a line. If omitted, all storage elements found in the active circuit
 * are included by default and controlled as a fleet.
 *
 */
public interface StorageControllerObj extends ControlElem {

	void setPFBand(double Value);
	
	double getPFBand();
	
	double getFleetkW();
	
	double getFleetkWh();
	
	double getFleetReserveKWh();

}

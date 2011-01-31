package com.epri.dss.control;

/**
 * A Relay is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 * 
 * The control is usually placed in the terminal of a line or transformer,
 * but it could be any element.
 * 
 * A Relay is defined by a New command:
 * 
 * New Relay.Name=myname Element=devclass.name terminal=[ 1|2|...] Switch = devclass.name   terminal=[ 1|2|...]
 * Type = [current | voltage]
 * Phase = TCCCurve
 * Ground = TCCCurve
 * OverVolt = TCCcurve
 * UnderVolt = TCCCurve
 * PhaseTrip =  Multipliers times curve
 * GroundTrip =
 * PhaseInst  =
 * GroundInst =
 * RecloseIntervals= (array of times, sec);
 * ResetTime =
 * 
 * CktElement to be controlled must already exist.
 * 
 * Voltage relay is a definite time relay that operates after the voltage
 * stays out of bounds for a fixed time interval.  It will then reclose a
 * set time after the voltage comes back in the normal range.
 *
 */
public interface RelayObj extends ControlElem {

}

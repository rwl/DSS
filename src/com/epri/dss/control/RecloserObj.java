package com.epri.dss.control;

/**
 * A Recloser is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 * 
 * The control is usually placed in the terminal of a line or transformer, but
 * it could be any element.
 * 
 * CktElement to be controlled must already exist.
 *
 */
public interface RecloserObj extends ControlElem {

}

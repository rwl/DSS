package com.epri.dss.meter;

import org.apache.commons.math.complex.Complex;

/**
 * A monitor is a circuit element that is connected to a terminal of another
 * circuit element.  It records the voltages and currents at that terminal as
 * a function of time and can report those values upon demand.
 *
 * A Monitor is defined by a New commands:
 *
 * New Type=Monitor Name=myname Element=elemname Terminal=[1,2,...] Buffer=clear|save
 *
 * Upon creation, the monitor buffer is established.  There is a file associated
 * with the buffer.  It is named "Mon_elemnameN.mon"  where N is the terminal no.
 * The file is truncated to zero at creation or buffer clearing.
 *
 * The Monitor keeps results in the in-memory buffer until it is filled.  Then it
 * appends the buffer to the associated file and resets the in-memory buffer.
 *
 * For buffer=save, the present in-memory buffer is appended to the disk file so
 * that it is saved for later reference.
 *
 * The Monitor is a passive device that takes a sample whenever its "TakeSample"
 * method is invoked.  The SampleAll method of the Monitor ckt element class will
 * force all monitors elements to take a sample.  If the present time (for the most
 * recent solution is greater than the last time entered in to the monitor buffer,
 * the sample is appended to the buffer.  Otherwise, it replaces the last entry.
 *
 * Monitor Files are simple binary files of doubles.  The first record
 * contains the number of conductors per terminal (NCond). (always use 'round' function
 * when converting this to an integer). Then subsequent records consist of time and
 * voltage and current samples for each terminal (all complex doubles) in the order
 * shown below:
 *
 * <NCond>
 *
 *          <--- All voltages first ---------------->|<--- All currents ----->|
 * <hour 1> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im> ...
 * <hour 2> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im> ...
 * <hour 3> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im> ...
 *
 * The time values will not necessarily be in a uniform time step;  they will
 * be at times samples or solutions were taken.  This could vary from several
 * hours down to a few milliseconds.
 *
 * The monitor ID can be determined from the file name.  Thus, these values can
 * be post-processed at any later time, provided that the monitors are not reset.
 *
 * Modes are:
 *    0: Standard mode - V and I,each phase, Mag and Angle
 *    1: Power each phase, complex (kw and kvars)
 *    2: Transformer Tap
 *    3: State Variables
 *  +16: Sequence components: V012, I012
 *  +32: Magnitude Only
 *  +64: Pos Seq only or average of phases
 *
 */
public interface MonitorObj extends MeterElement {

	String getFileName();

	int getMode();

	void setMode(int mode);

//	MemoryStream getMonitorStream();
//
//	void setMonitorStream(MemoryStream monitorStream);

	int getSampleCount();

	void setSampleCount(int sampleCount);

	void resetIt();

	/** Saves present buffer to file. */
	void save();

	void openMonitorStream();

	void clearMonitorStream();

	void closeMonitorStream();

	void translateToCSV(boolean show);


	// FIXME Private members in OpenDSS

	int getBufferSize();

	void setBufferSize(int bufferSize);

	int getHour();

	void setHour(int hour);

	double getSec();

	void setSec(double sec);

	float[] getMonBuffer();

	void setMonBuffer(float[] monBuffer);

	int getBufPtr();

	void setBufPtr(int bufPtr);

	Complex[] getCurrentBuffer();

	void setCurrentBuffer(Complex[] currentBuffer);

	Complex[] getVoltageBuffer();

	void setVoltageBuffer(Complex[] voltageBuffer);

	int getNumStateVars();

	void setNumStateVars(int numStateVars);

	double[] getStateBuffer();

	void setStateBuffer(double[] stateBuffer);

	boolean isIncludeResidual();

	void setIncludeResidual(boolean includeResidual);

	boolean isVIPolar();

	void setVIPolar(boolean polar);

	boolean isPPolar();

	void setPPolar(boolean polar);

	int getFileSignature();

	void setFileSignature(int fileSignature);

	int getFileVersion();

	void setFileVersion(int fileVersion);

	String getBufferFile();

	void setBufferFile(String bufferFile);

	boolean isFileOpen();

	void setFileOpen(boolean isFileOpen);

	boolean isValidMonitor();

	void setValidMonitor(boolean validMonitor);

}

package com.ncond.dss.conversion;

/**
 * Ideal current source.
 *
 * ISource maintains a positive sequence for harmonic scans.  If you want zero
 * sequence, use three single-phase ISource.
 *
 */
public interface ISourceObj extends PCElement {

	double getSrcFrequency();

	void setSrcFrequency(double srcFrequency);

	// FIXME Private members in OpenDSS

	double getAmps();

	void setAmps(double amps);

	double getAngle();

	void setAngle(double angle);

	double getPhaseShift();

	void setPhaseShift(double phaseShift);

	int getScanType();

	void setScanType(int scanType);

	int getSequenceType();

	void setSequenceType(int sequenceType);

}

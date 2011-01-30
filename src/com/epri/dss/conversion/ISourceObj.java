package com.epri.dss.conversion;

/**
 * Ideal current source.
 * 
 * Stick'em on wherever you want as many as you want.
 * 
 * ISource maintains a positive sequence for harmonic scans.  If you want zero
 * sequence, use three single-phase ISource.
 *
 */
public interface ISourceObj extends PCElement {

	double getSrcFrequency();

	void setSrcFrequency(double srcFrequency);

}

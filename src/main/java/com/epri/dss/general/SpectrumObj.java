package com.epri.dss.general;

import com.epri.dss.shared.impl.Complex;

/**
 * Harmonic spectrum specified as Harmonic, pct magnitude and angle.
 *
 * Spectrum is shifted by the fundamental angle and stored in MultArray
 * so that the fundamental is at zero degrees phase shift.
 */
public interface SpectrumObj extends DSSObject {

	int getNumHarm();

	void setNumHarm(int numHarm);

	// FIXME Private method in OpenDSS
	void setMultArray();

	// FIXME Private members in OpenDSS

	double[] getHarmArray();

	void setHarmArray(double[] harmArray);

	Complex getMult(double h);

	double[] getPUMagArray();

	void setPUMagArray(double[] puMagArray);

	double[] getAngleArray();

	void setAngleArray(double[] angleArray);

	Complex[] getMultArray();

	void setMultArray(Complex[] multArray);

}

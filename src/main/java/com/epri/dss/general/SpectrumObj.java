package com.epri.dss.general;

import com.epri.dss.shared.impl.Complex;

/**
 * Harmonic Spectrum specified as Harmonic, pct magnitude and angle.
 *
 * Spectrum is shifted by the fundamental angle and stored in MultArray
 * so that the fundamental is at zero degrees phase shift.
 */
public interface SpectrumObj extends DSSObject {

	int getNumHarm();

	void setNumHarm(int numHarm);

	double[] getHarmArray();

	void setHarmArray(double[] harmArray);

	Complex getMult(double h);

	double[] getPuMagArray();

	void setPuMagArray(double[] puMagArray);

	double[] getAngleArray();

	void setAngleArray(double[] angleArray);

	Complex[] getMultArray();

	void setMultArray(Complex[] multArray);
	
}

package com.ncond.dss.shared.impl;

import java.io.File;

public class PstCalc {

	private static final int MAXBINS = 50000;

	private static File debugFile;  // for debugging

	private static double rms_reference;  // internal rms reference value (do not change)
	private static double Fbase;  // not needed for AC signal input
	private static double tStep;  // internal timestep, may or may not equal deltaT
	private static double pstTime;
	private static double pstTimer;
	private static double pstTimeMax;
	private static double rms_input;  // nominal line-to-neutral rms input
	private static double rms_sample;
	private static double deltaT;
	private static int numPstIntervals;

	private static double[] Vin = new double[6];
	private static double[] X1 = new double[6];
	private static double[] X2 = new double[6];
	private static double[] X3 = new double[6];
	private static double[] X4 = new double[6];
	private static double[] X5 = new double[6];
	private static double[] X6 = new double[6];
	private static double[] X7 = new double[6];
	private static double[] X8 = new double[6];
	private static double[] X9 = new double[6];
	private static double[] X10 = new double[6];
	private static double[] rmsVin = new double[6];

	private static double[] bins0 = new double[MAXBINS];
	private static double[] bins1 = new double[MAXBINS];
	private static double bin_ceiling;
	private static int number_bins;

	/* Filter Coefficients */

	private static double WA2, WB2, WC2, WD2, WE2, WF2, WG2;  // weighting filter coefficients
	private static double IVAA,  IVAB,   IVAC,  IVAD,  IVAE,
		BA,  BB,  BC,  BD, BE,  BG,  BH,  BI,
		BJ,  BK, BL, BM, BN, BP,
		SA, // time constant of sliding mean filter
		internal_reference;

	private static int lamp_type;  // 0 for 120V filters, 1 for 230V filters
	private static int input_type;  // 0 for AC, 1 for 1-cycle RMS, 6 for 6-cycle rms

	private PstCalc() {

	}

	/**
	 * Function call for executing PST calculator using RMS data
	 * @return number of Pst elements computed
	 */
	public static int pstRMS(double[][] pstResult, double[] pVoltages, double freqBase, int nCyclesPerSample, int npts, int lamp) {
		Fbase = freqBase;

		// lamp_type  = 0;  // 0 for 120V filters, 1 for 230V filters
		input_type = 6;  // 0 for AC, 1 for 1-cycle RMS, 6 for 6-cycle rms

		// check for the lamp type (120 or 230), default to 120 if not read properly
		if (lamp == 230) {
			lamp_type = 1;
		} else {
			lamp_type = 0;
		}
		deltaT = nCyclesPerSample / Fbase;

		return _pst(pstResult, pVoltages, npts);
	}

	/**
	 * searches through the specified array for a bin and then
	 * interpolates (if needed)
	 */
	private static double sb(double y, double[] bins) {
		int n = 0;
		boolean found = false;

		while ((!found) && (n < number_bins)) {
			if (y <= bins[n]) {
				found = true;
			} else {
				n = n + 1;
			}
		}

		if (n > 0) {
			// interpolate
			return bin_ceiling * (n - 1) / number_bins +
				(y - bins[n - 1]) * (bin_ceiling / number_bins) / (bins[n] - bins[n - 1]);
		} else {
			return 0.0;
		}
	}

	private static void zeroOutBins() {
		int n;

		for (n = 0; n < number_bins - 1; n++) bins0[n] = 0.0;
		for (n = 0; n < number_bins - 1; n++) bins1[n] = 0.0;
	}

	/**
	 * Calculates the Pst
	 */
	private static double calcPst() {
		double num_pts;  // ?? long double Why??
		int n;

		double P01, P1s, P3s, P10s, P50s;

		num_pts = 0;
		for (n = 0; n < number_bins - 1; n++) {
			num_pts = num_pts + bins0[n];
			bins1[n] = num_pts;
		}

		for (n = 0; n < number_bins - 1; n++) {
			bins1[n] = bins1[n] / num_pts;
		}

		P01 = sb(0.999, bins1);
		P1s = (sb(0.993, bins1) + sb(0.990, bins1) + sb(0.985, bins1)) / 3.0;
		P3s = (sb(0.978, bins1) + sb(0.970, bins1) + sb(0.960, bins1)) / 3.0;
		P10s = (sb(0.940, bins1) + sb(0.920, bins1) + sb(0.900, bins1) + sb(0.870, bins1) + sb(0.830, bins1)) / 5.0;
		P50s = (sb(0.700, bins1) + sb(0.500, bins1) + sb(0.200, bins1)) / 3.0;

		// this is the Pst
		return Math.sqrt(0.0314  *P01 + 0.0525 * P1s + 0.0657 * P3s + 0.28 * P10s + 0.08 * P50s);
	}

	/**
	 * Calculates the coefficients for the weighting filter
	 */
	private static void setFilterCoefficients(int input_type) {
		double K, Lambda, W1, W2, W3, W4;

		// coefficients for input voltage adapter
		// L = 8.93125 H
		// C = 35.725 F
		// R = 1.0 Ohms

		IVAA = 8.93125 * 35.725;
		IVAB = 35.725;
		IVAC = 4.0 * IVAA / (tStep * tStep) + 1.0 - 2.0 * IVAB / tStep;
		IVAD = 2.0 - 8.0 * IVAA / (tStep * tStep);
		IVAE = 4.0 * IVAA / (tStep * tStep) + 1.0 + 2.0 * IVAB / tStep;

		// bandpass centered at 8.5Hz
		// 120V lamp
		if (lamp_type == 0) {
			K = 1.6357;
			Lambda = 26.1843893695;
			W1 = 57.0335348916;
			W2 = 18.4719490509;
			W3 = 8.76170084893;
			W4 = 108.794107576;
		} else {
			// bandpass centered at 8.8Hz
			// 230V lamp
			K = 1.74802;
			Lambda = 25.5085385419;
			W1 = 57.5221844961;
			W2 = 14.3243430315;
			W3 = 7.69910111615;
			W4 = 137.601758227;
		}

		// coefficients for bandpass
		// 1st set of substitutions
		BA = 0.314159265359;
		BB = 113.834561498;
		BC = 48361.06156533785;
		BD = 311.00180567;
		BE = 424.836367168;
		// 2nd set of substitutions
		BG = 1 + BA * tStep / 2.0;
		BH = BA * tStep / 2.0 - 1.0;
		BI = 4.0 / (tStep * tStep) + 2.0 * BB / tStep + BC;
		BJ =-8.0 / (tStep * tStep) + 2.0 * BC;
		BK = 4.0 / (tStep * tStep) - 2.0 * BB / tStep + BC;
		BL = 4.0 / (tStep * tStep) + 2.0 * BD / tStep + BC;
		BM = 4.0 / (tStep * tStep) - 2.0 * BD / tStep + BC;
		BN = 4.0 / (tStep * tStep) + 2.0 * BE / tStep + BC;
		BP = 4.0 / (tStep * tStep) - 2.0 * BE / tStep + BC;

		// coefficients for weighting filter
		WA2 = 4.0 * K * W1 * W3 * W4 / (tStep * tStep);
		WB2 = 2.0 * K * W1 * W2 * W3 * W4 / tStep;
		WC2 = 16.0 * W2 / Math.pow(tStep, 4);
		WD2 = 8.0 * W2 * (2.0 * Lambda + W3 + W4) / Math.pow(tStep, 3);
		WE2 = 4.0 * W2 * (W3 * W4 + W1 * W1 + 2.0 * Lambda * (W3 + W4)) / (tStep * tStep);
		WF2 = 2.0 * W2 * (2.0 * Lambda * W3 * W4 + W1 * W1 * (W3 + W4)) / tStep;
		WG2 = W2 * W3 * W4 * W1 * W1;

		// time constant of sliding mean filter
		SA = 0.3;

		// internal reference
		if (input_type == 0) internal_reference = 676.372;  // See "new 868 testing and scaling.xls" for derivation
		if (input_type == 1) internal_reference = 0.01106784;	// new scaling factor 7/25/05, based on 1-cycle RMS
		// using greater than 1-cycle RMS may result in errors
		if (input_type == 3) internal_reference = 0.009;	    // new scaling factor 8/3/05, based on 3-cycle RMS
		if (input_type == 6) internal_reference = 0.008449;	// new scaling factor 7/25/05, based on 6-cycle RMS
	}

	/**
	 * Put samples that get through the filter in the proper bins
	 */
	private static void gatherBins(double X10_value, double[] bins) {
		/* Find out which bin the value belongs in and increment it. */
		if (X10_value > bin_ceiling) {
			bins[number_bins - 1]++;  // increment count
		} else {
			bins[(int) (number_bins * X10_value / bin_ceiling)]++;
		}
	}

	private static void sampleShift() {
		int n;

		for (n = 5; n > 0; n--) {
			Vin[n] = Vin[n - 1];
			rmsVin[n] = rmsVin[n - 1];
			X1[n]  = X1[n - 1];
			X2[n]  = X2[n - 1];
			X3[n]  = X3[n - 1];
			X4[n]  = X4[n - 1];
			X5[n]  = X5[n - 1];
			X6[n]  = X6[n - 1];
			X7[n]  = X7[n - 1];
			X8[n]  = X8[n - 1];
			X9[n]  = X9[n - 1];
			X10[n] = X10[n - 1];
		}
	}


	/**
	 * Main flicker calculation function
	 */
	private static void getPinst() {

		/* RMS input */
		rmsVin[0] = rms_reference * rms_sample / rms_input;  // per unitize rms value

		X1[0] = (rmsVin[0] + 2.0 * rmsVin[1] + rmsVin[2] - IVAD * X1[1] - IVAC * X1[2]) / IVAE;
		X3[0] = rmsVin[0] * (1.0 - (X1[0] - 120.0) / rmsVin[0]);

		// Bandpass (HP at .05Hz and 6th order Butterworth LP at 35Hz)
		X4[0] = (X3[0] - X3[1] - BH * X4[1]) / BG;
		X5[0] = (BC * (X4[0] + 2 * X4[1] + X4[2]) - (BJ * X5[1] + BK * X5[2])) / BI;
		X6[0] = (BC * (X5[0] + 2 * X5[1] + X5[2]) - (BJ * X6[1] + BM * X6[2])) / BL;
		X7[0] = (BC * (X6[0] + 2 * X6[1] + X6[2]) - (BJ * X7[1] + BP * X7[2])) / BN;

		// Weighting filter
		X8[0] = ((WA2 + WB2) * X7[0] + 2 * WB2 * X7[1] - 2 * WA2 * X7[2] - 2 * WB2 * X7[3] +
			(WA2 - WB2) * X7[4] - (2 * WF2 + 4 * WG2 - 4 * WC2 - 2 * WD2) * X8[1] -
			(6 * WC2 - 2 * WE2 + 6 * WG2) * X8[2] - (2 * WD2 + 4 * WG2 - 4 * WC2 - 2 * WF2) * X8[3] -
			(WC2 - WD2 + WE2 - WF2 + WG2) * X8[4]) / (WC2 + WD2 + WE2 + WF2 + WG2);

		// Sliding mean filter
		X9[0] = (X8[0] * X8[0] + X8[1] * X8[1] - (1 - 2 * SA / tStep) * X9[1]) / (1 + 2 * SA / tStep);
		X10[0] =  X9[0] / internal_reference;
	}


	private static void init6Array(double[] Y, double V1, double V2, double V3, double V4, double V5, double V6) {
		Y[0] = V1;
		Y[1] = V2;
		Y[2] = V3;
		Y[3] = V4;
		Y[4] = V5;
		Y[5] = V6;
	}

	private static int _pst(double[][] pstResult, double[] VArray, int npts) {
		int pstInterval;
		double max_flicker;
		double time;   // long double???

		int Vindex;
		int iPst;

		double firstSample;
		double pstStartTime;

		int synthesizedSamples;
		double samplesPerDeltaT;  // this value is used when RMS data is used as input

		debugFile = new File("DebugOut.csv");

		rms_reference = 120.0;  // internal rms reference value (do not change)

		init6Array(Vin, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(rmsVin, rms_reference, rms_reference, rms_reference, rms_reference, rms_reference, rms_reference);  // RMS input voltage

		init6Array(X1,  rms_reference, rms_reference, rms_reference, rms_reference, rms_reference, rms_reference);
		init6Array(X2,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);  // output of block 1
		init6Array(X3,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);  // output of block 2
		init6Array(X4,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(X5,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(X6,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(X7,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(X8,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);  // output of block 3
		init6Array(X9,  0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		init6Array(X10, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);  // output of block 4

		bin_ceiling = 350.0;  // previously used 215, increased to be encompass high flicker levels
		number_bins = 16000;

		/* Allocate memory for bins */
		bins0 = new double[number_bins];  // 50k is max. # of bins
		bins1 = new double[number_bins];  // 50k is max. # of bins

		time     = 0.0;  // time clock
		pstTimer = 0.0;

		zeroOutBins();

		tStep = 1.0 / (16.0 * Fbase);  // time step for each cycle, fixed to 16 samples/cycle

		pstTimeMax = npts * deltaT;  // - 6.0 ;  //use the entire data set sent to calculate the flicker
		pstTime = Math.min(600.0, pstTimeMax);
		numPstIntervals = Math.max(1, (int) (pstTimeMax / pstTime));  // at least one interval

		if (pstResult[0] != null) pstResult[0] = null;
		pstResult[0] = new double[numPstIntervals];  // allocate result array

		setFilterCoefficients(input_type);

		/* Main RMS routine */

		samplesPerDeltaT = deltaT / tStep;

//		pw.printf("Tstep=%.8g, DeltaT=%.8g, Samples=%.8g, Pst_Time=%.8g, npts=%d ",
//				tStep, deltaT, samplesPerDeltaT, pstTime, npts);

		max_flicker = 0.0;
		firstSample = VArray[1];  // TODO Check zero based indexing
		rms_input   = firstSample;
		rms_sample  = firstSample;

		// inits filter to 1 PU for 30 s
		while (time < 30.0) {
			time = time + tStep;
			getPinst();  // computes what get's through filter (X10)
			sampleShift();
		}

		pstStartTime = time + 5.0;  // give it 5 s to settle down after real data starts

		Vindex = 1;  // TODO Check zero based indexing
		pstInterval = 0;
		for (iPst = 0; iPst < npts; iPst++) {
			rms_sample = VArray[Vindex];
			// the following loop holds the rms input samples constant over the RMS period
			for (synthesizedSamples = 0; synthesizedSamples < samplesPerDeltaT; synthesizedSamples++) {
				getPinst();  // computes what gets through filter (X10[0])

				////////////// this starts the Pst calculations //////////////
				if (time >= pstStartTime) {
					pstTimer = pstTimer + tStep;
					max_flicker = Math.max(max_flicker, X10[0]);
					gatherBins(X10[0], bins0);

					if (pstTimer >= pstTime) {
						// OK, we got everything in the bins, let's compute Pst
						pstInterval++;
						if (pstInterval <= numPstIntervals) pstResult[0][pstInterval] = calcPst();

//						pw.printf("PST Interval=%d, Pst=%.8g, Max_flicker=%.8g", pstInterval, pstResult[pstInterval], Max_Flicker);
//						pw.println("i, Vin, X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, RMSVin");
//						for (int i = 0; i < 6; i++) {
//							pw.println("%d, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g, %.8g",
//									i, Vin[i], X1[i], X2[i], X3[i], X4[i], X5[i], X6[i], X7[i], X8[i], X9[i], X10[i], rmsVin[i]);
//						}
//						for (int i = 0; i < 100; i++) {
//							pw.println("%d, %.8g, %.8g", i, bins0[i], bins1[i]));
//						}

						pstTimer = 0.0;
						zeroOutBins();  // zero bins0 and bins1 out for next time
					}
				}
				sampleShift();
				time = time + tStep;
			}
			Vindex++;
		}

//		pw.close();

		bins0 = null;
		bins1 = null;

		return pstInterval;   // should be numPstIntervals
	}

}

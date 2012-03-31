/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.general.XfmrCodeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;


@SuppressWarnings("unused")
public class TransformerObj extends PDElement {

	private int deltaDirection;
	private double ppmFloatFactor;  // parts per million winding float factor
	private double pctImag;

	protected int numWindings;
	protected int maxWindings;
	protected int[] termRef;  // keeps track of terminal connections

	protected double XHL, XHT, XLT;  // per unit
	protected double Zbase;
	protected double[] XSC;     // per unit SC measurements
	protected double VABase;    // for impedances

	protected CMatrix ZB;
	protected CMatrix Y_1Volt;
	protected CMatrix Y_Term;
	protected CMatrix Y_1Volt_NL;   // no load Y's
	protected CMatrix Y_Term_NL;

	protected double YterminalFreqMult;

	protected double normMaxHKVA;
	protected double emergMaxHKVA;
	protected double thermalTimeConst;  // hr
	protected double nThermal;
	protected double mThermal;  // exponents
	protected double FLRise;
	protected double HSRise;
	protected double pctLoadLoss;
	protected double pctNoLoadLoss;

	protected boolean XHLChanged;

	private int activeWindingIdx;
	private boolean isSubstation;
	private String substationName;
	private Winding[] windings;
	private String XfmrBank;
	private String XfmrCode;

	public TransformerObj(DSSClass parClass, String transfName) {
		super(parClass);

		setName(transfName.toLowerCase());
		objType = parClass.getClassType(); //DSSObjType + XFMR; // override PDElement (kept in both actually)

		setNumPhases(3);  // directly set conds and phases
		nConds = nPhases + 1;
		setNumWindings(2);  // must do this after setting number of phases

		activeWindingIdx = 0;

		setNumTerms(numWindings);  // force allocation of terminals and conductors

		XHL = 0.07;
		XHT = 0.35;
		XLT = 0.30;
		XHLChanged = true;  // set flag to for calc of XSC array from XHL, etc.

		deltaDirection = 1;
		substationName = "";
		XfmrBank = "";
		XfmrCode = "";

		VABase = windings[0].getKVA() * 1000.0;
		thermalTimeConst = 2.0;
		nThermal = 0.8;
		mThermal = 0.8;
		FLRise = 65.0;
		HSRise = 15.0;  // hot spot rise
		normMaxHKVA = 1.1 * windings[0].getKVA();
		emergMaxHKVA = 1.5 * windings[0].getKVA();
		pctLoadLoss = 2.0 * windings[0].getRpu() * 100.0;  // assume two windings
		ppmFloatFactor = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < numWindings; i++)
			windings[i].computeAntiFloatAdder(ppmFloatFactor, VABase / nPhases);

		/* Default the no load properties to zero */
		pctNoLoadLoss = 0.0;
		pctImag = 0.0;

		/*BaseFrequency = 60.0; set in base class to circuit fundamental freq; do not reset here*/
		setFaultRate(0.007);
		isSubstation = false;

		YterminalFreqMult = 0.0;

		YOrder = nTerms * nConds;
		initPropertyValues(0);
		recalcElementData();
	}

	public void setNumWindings(int n) {
		int i;
		int oldWdgSize, newWdgSize;

		if (n > 1) {
			oldWdgSize = (numWindings - 1) * numWindings / 2;
			numWindings = n;
			maxWindings = n;
			newWdgSize = (numWindings - 1) * numWindings / 2;
			nConds = nPhases + 1;
			setNumTerms(numWindings);

			windings = Util.resizeArray(windings, maxWindings);  // reallocate collector array
			for (i = 0; i < maxWindings; i++)
				windings[i] = new Winding();

			// array of short circuit measurements between pairs of windings
			XSC = Util.resizeArray(XSC, newWdgSize);
			for (i = oldWdgSize; i < newWdgSize; i++)
				XSC[i] = 0.30;
			termRef = Util.resizeArray(termRef, 2 * numWindings * nPhases);

			/* Reallocate impedance matrices */
			ZB         = new CMatrix(numWindings - 1);
			Y_1Volt    = new CMatrix(numWindings);
			Y_1Volt_NL = new CMatrix(numWindings);
			Y_Term     = new CMatrix(2 * numWindings);
			Y_Term_NL  = new CMatrix(2 * numWindings);
		} else {
			DSS.doSimpleMsg("Invalid number of windings: (" + n + ") for transformer " + getName(), 111);
		}
	}

	@Override
	public void recalcElementData() {
		int i, iHVolt;
		double Vfactor;
		Winding w;

		// determine delta direction
		// if high voltage is delta, delta leads wye
		// if high voltage is wye, delta lags wye
		if (windings[0].getConnection() == windings[1].getConnection()) {
			deltaDirection = 1;
		} else {
			iHVolt = (windings[0].getKVLL() >= windings[1].getKVLL()) ? 0 : 1;

			switch (windings[iHVolt].getConnection()) {
			case WYE:
				deltaDirection = 1;
				break;
			case DELTA:
				deltaDirection = -1;
				break;
			default:
				break;
			}
		}

		setTermRef();  // re-establish termRef if num windings or connection changed

		for (i = 0; i < numWindings; i++) {
			w = windings[i];
			if (w.getNumTaps() > 0) {
				w.setTapIncrement((w.getMaxTap() - w.getMinTap()) / w.getNumTaps());
			} else {
				w.setTapIncrement(0.0);
			}
		}

		if (XHLChanged) {
			/* should only happen for 2- and 3-winding transformers */
			if (numWindings <= 3) {
				for (i = 0; i < numWindings * (numWindings - 1) / 2; i++) {
					switch (i) {
					case 0:
						XSC[0] = XHL;
						break;
					case 1:
						XSC[1] = XHT;
						break;
					case 3:
						XSC[2] = XLT;
						break;
					}
				}
			}
			XHLChanged = false;
		}

		// set winding voltage bases (in volts)
		for (i = 0; i < numWindings; i++) {
			w = windings[i];  // get the actual turns voltage base for each winding
			switch (w.getConnection()) {
			case WYE:
				switch (nPhases) {
				case 2:  // assume 3-phase for 2-phase designation
				case 3:
					w.setVbase(w.getKVLL() * DSS.InvSQRT3x1000);
					break;
				default:
					w.setVbase(w.getKVLL() * 1000.0);
					break;
				}
				break;
			case DELTA:
				w.setVbase(w.getKVLL() * 1000.0);  // delta
				break;
			}
		}

		/* Base rating of winding 1 */
		VABase = windings[0].getKVA() * 1000.0;

		for (i = 0; i < numWindings; i++)
			windings[i].computeAntiFloatAdder(ppmFloatFactor, VABase / nPhases);

		/* Normal and emergency terminal current rating for UE check */
		Vfactor = 1.0;  // ensure initialization

		switch (windings[0].getConnection()) {
		case WYE:
			Vfactor = windings[0].getVbase() * 0.001;
			break;
		case DELTA:
			switch (nPhases) {
			case 1:
				Vfactor = windings[0].getVbase() * 0.001;
				break;
			case 2:
				Vfactor = windings[0].getVbase() * 0.001 / DSS.SQRT3;
				break;
			case 3:
				Vfactor = windings[0].getVbase() * 0.001 / DSS.SQRT3;
				break;
			default:
				Vfactor = windings[0].getVbase() * 0.001 * 0.5 / Math.sin(Math.PI / nPhases);
				break;
			}
			break;
		}

		/* Divide per phase kVA by voltage to neutral */
		setNormAmps(normMaxHKVA  / nPhases / Vfactor);
		setEmergAmps(emergMaxHKVA / nPhases / Vfactor);

		calcYTerminal(1.0);  // calc Y_Terminal at base frequency
	}

	/**
	 * Transformer structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter f) {
		int i, iProp;

		/* Write only properties that were explicitly set in the
		 * final order they were actually set. */
		iProp = getNextPropertySet(-1);  // works on activeDSSObject

		while (iProp >= 0) {
			/* Trap wdg= and write out array properties instead */
			switch (parentClass.getRevPropertyIdxMap(iProp)) {
			case 2:  // if wdg= was ever used write out arrays ...
				for (i = 11; i < 16; i++)
					f.printf(" %s=%s", parentClass.getPropertyName(i), getPropertyValue(i));
				for (i = 0; i < numWindings; i++)
					f.printf(" wdg=%d %sR=%.7g", i, "%", windings[i].getRpu() * 100.0);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			default:
				f.printf(" %s=%s", parentClass.getPropertyName(parentClass.getRevPropertyIdxMap(iProp)),
						Util.checkForBlanks( getPropertyValue(iProp) ));
				break;
			}
			iProp = getNextPropertySet(iProp);
		}
	}

	/**
	 * Sets an array which maps the two conductors of each winding to the phase
	 * and neutral conductors of the transformer according to the winding connection.
	 */
	protected void setTermRef() {
		int i, j, k = -1;

		switch (nPhases) {
		case 1:
			for (j = 0; j < numWindings; j++) {
				k += 1;
				termRef[k] = j * nConds + 1;
				k += 1;
				termRef[k] = (j+1) * nConds;
			}
			break;
		default:
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < numWindings; j++) {
					k += 1;
					switch (windings[j].getConnection()) {
					case WYE:
						termRef[k] = j * nConds + i;
						k += 1;
						termRef[k] = (j+1) * nConds;
						break;
					/* **** WILL THIS WORK for 2-PHASE OPEN DELTA ???? Need to check this sometime */
					case DELTA:
						termRef[k] = j * nConds + i;
						k += 1;
						termRef[k] = (j+1) * nConds + rotatePhases(i);  // connect to next phase in sequence
						break;
					}
				}
			}
			break;
		}
	}

	@Override
	public void calcYPrim() {
		double freqMultiplier;

		if (isYprimInvalid()) {
			// reallocate YPrim if something has invalidated old allocation
			YPrimSeries = new CMatrix(YOrder);
			YPrimShunt = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			/* Same size as last time; just zero out to start over */
			YPrimSeries.clear(); // zero out YPrim
			YPrimShunt.clear(); // zero out YPrim
			YPrim.clear();
		}

		// set frequency multipliers for this calculation
		YPrimFreq = DSS.activeCircuit.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		// check for rebuilding Yterminal; only rebuild if freq is different than last time
		if (freqMultiplier != YterminalFreqMult)
			calcYTerminal(freqMultiplier);

		buildYPrimComponent(YPrimSeries, Y_Term);
		buildYPrimComponent(YPrimShunt, Y_Term_NL);

		addNeutralToY(freqMultiplier);

		/* Combine the two YPrim components into YPrim */
		YPrim.copyFrom(YPrimSeries);
		YPrim.addFrom(YPrimShunt);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;
		Winding w;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		/* Basic property dump */
		pw.println("~ " + "numWindings=" + numWindings);
		pw.println("~ " + "phases=" + nPhases);

		for (i = 0; i < numWindings; i++) {
			w = windings[i];
			if (i == 0) {
				pw.println("~ " + "wdg=" + i + " bus=" + getFirstBus());
			} else {
				pw.println("~ " + "wdg=" + i + " bus=" + getNextBus());
			}

			switch (w.getConnection()) {
			case WYE:
				pw.println("~ conn=wye");
				break;
			case DELTA:
				pw.println("~ conn=delta");
				break;
			}
			pw.println("~ kv=" + w.getKVLL());
			pw.println("~ kva=" + w.getKVA());
			pw.println("~ tap=" + w.getPuTap());
			pw.println("~ %r=" + (w.getRpu() * 100.0));
			pw.println("~ rneut=" + w.getRNeut());
			pw.println("~ xneut=" + w.getXNeut());
		}

		pw.println("~ " + "xhl=" + XHL * 100.0);
		pw.println("~ " + "xht=" + XHT * 100.0);
		pw.println("~ " + "xlt=" + XLT * 100.0);
		pw.print("~ XscMatrix= \"");
		for (i = 0; i < (numWindings - 1) * numWindings / 2; i++)
			pw.print(XSC[i] * 100.0 + " ");
		pw.println("\"");
		pw.println("~ " + "NormMaxHkVA=" + normMaxHKVA);
		pw.println("~ " + "EmergMaxHkVA=" + emergMaxHKVA);
		pw.println("~ " + "thermal=" + thermalTimeConst);
		pw.println("~ " + "n=" + nThermal);
		pw.println("~ " + "m=" + mThermal);
		pw.println("~ " + "flrise=" + FLRise);
		pw.println("~ " + "hsrise=" + HSRise);
		pw.println("~ " + "%loadloss=" + pctLoadLoss);
		pw.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (i = 27; i < Transformer.NumPropsThisClass; i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		for (i = Transformer.NumPropsThisClass; i < parentClass.getNumProperties(); i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println();
			pw.println("ZB: (inverted)");
			for (i = 0; i < numWindings - 1; i++) {
				for (j = 0; j < i; j++)
					pw.print(ZB.get(i, j).real() + " ");
				pw.println();
			}
			for (i = 0; i < numWindings - 1; i++) {
				for (j = 0; j < i; j++)
					pw.print(ZB.get(i, j).imag() + " ");
				pw.println();
			}

			pw.println();
			pw.println("Y_OneVolt");
			for (i = 0; i < numWindings; i++) {
				for (j = 0; j < i; j++)
					pw.print(Y_1Volt.get(i, j).real() + " ");
				pw.println();
			}
			for (i = 0; i < numWindings; i++) {
				for (j = 0; j < i; j++)
					pw.print(Y_1Volt.get(i, j).imag() + " ");
				pw.println();
			}

			pw.println();
			pw.println("Y_Terminal");
			for (i = 0; i < 2 * numWindings; i++) {
				for (j = 0; j < i; j++)
					pw.print(Y_Term.get(i, j).real() + " ");
				pw.println();
			}
			for (i = 0; i < 2 * numWindings; i++) {
				for (j = 0; j < i; j++)
					pw.print(Y_Term.get(i, j).imag() + " ");
				pw.println();
			}
			pw.println();
			pw.print("TermRef= ");
			for (i = 0; i < 2 * numWindings * nPhases; i++)
				pw.print(termRef[i] + " ");
			pw.println();
		}
		pw.close();
	}

	public void setPresentTap(int i, double value) {
		Winding w;

		if (i >= 0 && i < numWindings) {
			w = windings[i];

			/* Range checking */
			if (value < w.getMinTap()) {
				value = w.getMinTap();
			} else if (value > w.getMaxTap()) {
				value = w.getMaxTap();
			}

			if (value != w.getPuTap()) {  /* Only if there's been a change */
				w.setPuTap(value);
				setYPrimInvalid(true);  // this property triggers setting systemYChanged=true
				recalcElementData();
			}
		}
	}

	public double getWdgResistance(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getRpu();
		} else {
			return 0.0;
		}
	}

	public double getWdgKVA(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getKVA();
		} else {
			return 0.0;
		}
	}

	public double getWdgRNeutral(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getRNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgXNeutral(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getXNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgYPPM(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getY_PPM();
		} else {
			return 0.0;
		}
	}

	public double getXSC(int i) {
		int imax = (numWindings - 1) * numWindings / 2;

		if (i >= 0 && i < imax) {
			return XSC[i];
		} else {
			return 0.0;
		}
	}

	public Connection getWdgConnection(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getConnection();
		} else {
			return Connection.WYE;
		}
	}

	public double getMinTap(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getMinTap();
		} else {
			return 0.0;
		}
	}

	public double getMaxTap(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getMaxTap();
		} else {
			return 0.0;
		}
	}

	public int getNumTaps(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getNumTaps();
		} else {
			return 0;
		}
	}

	public double getTapIncrement(int i) {
		if (i >= 0 && i < numWindings) {
			return windings[i].getTapIncrement();
		} else {
			return 0.0;
		}
	}

	/**
	 * Voltages across indicated winding.
	 *
	 * Fill Vbuffer array which must be adequately allocated by calling routine
	 * Order is number of phases.
	 */
	public void getWindingVoltages(int iWind, Complex[] Vbuffer) {
		int i, ii, k, neutTerm;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			/* Return zero if winding number improperly specified */
			if (iWind < 0 || iWind >= numWindings) {
				for (i = 0; i < nConds; i++)
					Vbuffer[i] = Complex.ZERO;
				return;
			}

			/* Load up VTemp - already allocated for all cktelements */

			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV(nodeRef[i]);

			k = iWind * nConds;  // offset for winding
			neutTerm = nPhases + k + 1;
			for (i = 0; i < nPhases; i++) {
				switch (windings[iWind].getConnection()) {
				case WYE:
					Vbuffer[i] = VTerminal[i + k].sub(VTerminal[neutTerm]);
					break;
				case DELTA:
					ii = rotatePhases(i);  // get next phase in sequence
					Vbuffer[i] = VTerminal[i + k].sub(VTerminal[ii + k]);
					break;
				}
			}

		} catch (Exception e) {
			DSS.doSimpleMsg("Error filling voltage buffer in getWindingVoltages for circuit element: Transformer." +
					getName() + DSS.CRLF +
					"Probable cause: Invalid definition of element." + DSS.CRLF +
					"System error message: " + e.getMessage(), 114);
		}
	}

	public double getBaseVoltage(int i) {
		if (i < 0 || i >= numWindings) {
			return windings[0].getVbase();
		} else {
			return windings[i].getVbase();
		}
	}

	@Override
	public void getLosses(Complex[] totalLosses, Complex[] loadLosses, Complex[] noLoadLosses) {
		Complex tot, load, noload;
		Complex[] cTemp;

		/* Calculates losses in watts, vars */
		tot = getLosses();  // side effect: computes ITerminal

		/* Compute no load losses in YPrim_Shunt */
		cTemp = new Complex[YOrder];
		computeVTerminal();
		YPrimShunt.vMult(cTemp, VTerminal);

		/* No load losses are sum of all powers coming into YPrim_Shunt from each terminal */
		noload = Complex.ZERO;
		for (int i = 0; i < YOrder; i++)
			noload = noload.add(VTerminal[i].mult(cTemp[i].conj()));

		load = tot.sub(noload);

		/* Handle pass by reference */
		totalLosses[0] = tot;
		loadLosses[0] = load;
		noLoadLosses[0] = noload;
	}

	/**
	 * Gets the property for the active winding; set the active winding before calling.
	 */
	@Override
	public String getPropertyValue(int index) {
		int i;
		String val;

		switch (index) {
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 19:
		case 36:
			val = "[";
			break;
		default:
			val = "";
			break;
		}

		switch (index) {
		case 0:
			val = String.valueOf(nPhases);
			break;
		case 1:
			val = String.valueOf(numWindings);
			break;
		case 2:
			val = String.valueOf(activeWindingIdx);  // return active winding
			break;
		case 3:
			val = getBus(activeWindingIdx);  // return bus spec for active winding
			break;
		case 4:
			switch (windings[activeWindingIdx].getConnection()) {
			case WYE:
				val = "wye ";
				break;
			case DELTA:
				val = "delta ";
				break;
			}
			break;
		case 5:
			val = String.format("%.7g", windings[activeWindingIdx].getKVLL());
			break;
		case 6:
			val = String.format("%.7g", windings[activeWindingIdx].getKVA());
			break;
		case 7:
			val = String.format("%.7g", windings[activeWindingIdx].getPuTap());
			break;
		case 8:
			val = String.format("%.7g", windings[activeWindingIdx].getRpu() * 100.0);  // %R
			break;
		case 9:
			val = String.format("%.7g", windings[activeWindingIdx].getRNeut());
			break;
		case 10:
			val = String.format("%.7g", windings[activeWindingIdx].getXNeut());
			break;
		case 11:
			for (i = 0; i < numWindings; i++)
				val = val + getBus(i) + ", ";
			break;
		case 12:
			for (i = 0; i < numWindings; i++)
				switch (windings[i].getConnection()) {
				case WYE:
					val = val + "wye, ";
					break;
				case DELTA:
					val = val + "delta, ";
					break;
				}
			break;
		case 13:
			for (i = 0; i < numWindings; i++)
				val = val + String.format("%.7g, ", windings[i].getKVLL());
			break;
		case 14:
			for (i = 0; i < numWindings; i++)
				val = val + String.format("%.7g, ", windings[i].getKVA());
			break;
		case 15:
			for (i = 0; i < numWindings; i++)
				val = val + String.format("%.7g, ", windings[i].getPuTap());  // interpretAllTaps(Param);
			break;
		case 16:
			val = String.format("%.7g", XHL * 100.0);
			break;
		case 17:
			val = String.format("%.7g", XHT * 100.0);
			break;
		case 18:
			val = String.format("%.7g", XLT * 100.0);
			break;
		case 19:
			for (i = 0; i < (numWindings - 1) * numWindings / 2; i++)
				val = val + String.format("%g, ", XSC[i] * 100.0);// Parser.ParseAsVector(((NumWindings - 1)*NumWindings div 2), Xsc);
			break;
		case 25:
			val = String.format("%.7g", pctLoadLoss);
			break;
		case 26:
			val = String.format("%.7g", pctNoLoadLoss);
			break;
		case 30:
			val = String.format("%.7g", windings[activeWindingIdx].getMaxTap());
			break;
		case 31:
			val = String.format("%.7g", windings[activeWindingIdx].getMinTap());
			break;
		case 32:
			val = String.format("%-d", windings[activeWindingIdx].getNumTaps());
			break;
		case 34:
			val = String.format("%.7g", pctImag);
			break;
		case 35:
			val = String.format("%.7g", ppmFloatFactor / 1.0e-6);
			break;
		case 36:
			for (i = 0; i < numWindings; i++)
				val = val + String.format("%.7g, ", windings[i].getRpu() * 100.0);
			break;

		default:
			val = super.getPropertyValue(index);
			break;
		}

		// Overrides
		switch (index = Transformer.NumPropsThisClass) {
		case 0:
			val = String.format("%-.5g", getNormAmps());   // normAmps
			break;
		case 1:
			val = String.format("%-.5g", getEmergAmps());  // emergAmps
			break;
		}

		switch (index) {
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 19:
		case 36:
			val += "]";
			break;
		}

		return val;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "3");  // "phases";
		setPropertyValue(1, "2");  // "windings";
		// winding definition
		setPropertyValue(2, "1");  // "wdg";
		setPropertyValue(3, getBus(0));  // "bus";
		setPropertyValue(4, "wye");  // "conn";
		setPropertyValue(5, "12.47");  // if 2 or 3-phase:  phase-phase else actual winding
		setPropertyValue(6, "1000");
		setPropertyValue(7, "1.0");
		setPropertyValue(8, "0.2");
		setPropertyValue(9, "-1");
		setPropertyValue(10, "0");

		// general data
		setPropertyValue(11, "");
		setPropertyValue(12, "");
		setPropertyValue(13, ""); // if 1-phase: actual winding rating; else phase-phase
		setPropertyValue(14, ""); // if 1-phase: actual winding rating; else phase-phase
		setPropertyValue(15, "");
		setPropertyValue(16, "7");
		setPropertyValue(17, "35");
		setPropertyValue(18, "30");
		setPropertyValue(19, "");  // x12 13 14... 23 24.. 34 ..
		setPropertyValue(20, "2");
		setPropertyValue(21, ".8");
		setPropertyValue(22, ".8");
		setPropertyValue(23, "65");
		setPropertyValue(24, "15");
		setPropertyValue(25, String.format("%.7g", pctLoadLoss));
		setPropertyValue(26, String.format("%.7g", pctNoLoadLoss));  // defaults to zero
		setPropertyValue(27, "");
		setPropertyValue(28, "");
		setPropertyValue(29, "n");  // =y/n
		setPropertyValue(30, "1.10");
		setPropertyValue(31, "0.90");
		setPropertyValue(32, "32");
		setPropertyValue(33, "");
		setPropertyValue(34, "0");
		setPropertyValue(35, "1");
		setPropertyValue(36, "");

		super.initPropertyValues(Transformer.NumPropsThisClass - 1);

		// override some inherited properties
		setPropertyValue(Transformer.NumPropsThisClass + 0, "400");    // normAmps
		setPropertyValue(Transformer.NumPropsThisClass + 1, "600");    // emergAmps
		setPropertyValue(Transformer.NumPropsThisClass + 2, "0.007");  // faultRate
		setPropertyValue(Transformer.NumPropsThisClass + 3, "100");    // pctPerm
		setPropertyValue(Transformer.NumPropsThisClass + 4, "36");     // hrsToRepair

		clearPropSeqArray();  // so the overrides don't show up on save
	}

	/**
	 * For delta connections or line-line voltages.
	 */
	public int rotatePhases(int iPhs) {
		int ret = iPhs + deltaDirection;

		// make sure result is within limits
		if (nPhases > 2) {
			// assumes 2 phase delta is open delta
			if (ret > nPhases)
				ret = 0;
			if (ret < 1)
				ret = nPhases - 1;
		} else if (ret < 1) {
			ret = 2;  // for 2-phase delta, next phase will be 3rd phase
		}

		return ret;
	}

	/**
	 * Converts default 3-phase transformer model into equivalent positive-sequence
	 * using scripting.
	 */
	@Override
	public void makePosSequence() {
		int iW, i;
		int[] n = new int[1];
		String s;
		int[] nodes = new int[5];  // integer buffer
		boolean onPhase1;
		Winding w;

		/* First, determine if we can convert this one. */
		if (nPhases == 1 || nPhases == 2) {  // disable if any terminal not connected to phase one
			for (iW = 0; iW < numWindings; iW++) {
				onPhase1 = false;
				/* Load up auxiliary parser */
				DSS.auxParser.setCommand(getBus(iW));
				DSS.auxParser.getNextParam();
				s = DSS.auxParser.parseAsBusName(n, nodes);
				if (n[0] == 0) onPhase1 = true;

				for (i = 0; i < n[0]; i++)
					if (nodes[i] == 1)
						onPhase1 = true;

				if (!onPhase1) {
					setEnabled(false);  // we won't use this one
					return;
				}
			}
		}

		/* Construct transformer definition string */
		s = "phases=1 conns=(";
		for (i = 0; i < numWindings; i++) s = s + "wye ";
		s = s + ")  buses=(";

		for (i = 0; i < numWindings; i++) s = s + getBus(i) + " ";
		s = s + ")  kVS=(";

		for (i = 0; i < numWindings; i++) {
			w = windings[i];
			if (nPhases > 1 || w.getConnection() != Connection.WYE) {
				s = s + String.format(" %-.5g", w.getKVLL() / DSS.SQRT3);
			} else {
				s = s + String.format(" %-.5g", w.getKVLL());
			}
		}
		s = s + ")  kVAs=(";

		for (i = 0; i < numWindings; i++) {
			w = windings[i];
			s = s + String.format(" %-.5g", w.getKVA() / nPhases);
		}
		s = s + ")";

		s = s + " normHkVA=" + String.format(" %-.5g %-.5g", normMaxHKVA / nPhases, emergMaxHKVA / nPhases);

		Parser.getInstance().setCommand(s);
		edit();

		super.makePosSequence();
	}

	private void addNeutralToY(double freqMultiplier) {
		int i, j;
		Complex value = null;
		Winding w;

		/* Account for neutral impedances */
		for (i = 0; i < numWindings; i++) {
			w = windings[i];
			if (w.getConnection() == Connection.WYE) {
				// handle wye, but ignore delta (and open wye)
				if (w.getRNeut() >= 0) {
					// <0 is flag for open neutral (Ignore)
					if (w.getRNeut() == 0 && w.getXNeut() == 0)
						// solidly grounded
						value = new Complex(1000000, 0);
				} else {
					// 1 microohm resistor
					value = new Complex(w.getRNeut(), w.getXNeut() * freqMultiplier).inv();
				}
				j = i * nConds;
				YPrimSeries.add(j, j, value);
			} else {
				// bump up neutral admittance a bit in case neutral is floating
				j = i * nConds;
				if (ppmFloatFactor != 0.0) {
					YPrimSeries.set(j, j, YPrimSeries.get(j, j).add(new Complex(0.0, w.getY_PPM())));
					/* YPrim_Series.setElement(j, j, CmulReal_im(GetElement(j, j), ppm_FloatFactorPlusOne)); */
				}
			}
		}
	}

	private void buildYPrimComponent(CMatrix YPrimComponent, CMatrix YTerminal) {
		Complex value;

		/* Now, put in Yprim matrix */
		/* Have to add every element of Y_terminal into Yprim somewhere */
		int nw2 = 2 * numWindings;
		for (int i = 0; i < nw2; i++) {
			for (int j = 0; j < i; j++) {
				value = YTerminal.get(i, j);
				// this value goes in Yprim nPhases times
				for (int k = 0; k < nPhases - 1; k++) {
					YPrimComponent.addSym(termRef[i + k * nw2] - 1, termRef[j + k * nw2] - 1, value);
				}
			}
		}
	}

	public double getBasekVLL(int i) {
		return windings[i].getKVLL();
	}

	private void calcYTerminal(double freqMult) {
		int i, j, k;
		Complex[] a, cTempArray1, cTempArray2;
		Complex cMinusOne;
		CMatrix At;

		// construct ZBMatrix
		ZB.clear();
		Zbase = 1.0 / (VABase / nPhases);  // base ohms on 1.0 volt basis
		for (i = 0; i < numWindings - 1; i++) {
			/* convert pu to ohms on one volt base as we go... */
			ZB.set(i, i, new Complex(
				windings[0].getRpu() + windings[i + 1].getRNeut(),
				freqMult * XSC[i]
			).mult(Zbase));
		}

		// off diagonals
		k = numWindings - 1;
		for (i = 0; i < numWindings - 1; i++) {
			for (j = i + 1; j < numWindings - 1; j++) {
				ZB.setSym(i, j, ZB.get(i, i).add( ZB.get(j, j) ).sub(new Complex(
					(windings[i + 1].getRpu() + windings[j + 1].getRpu()),
					freqMult * XSC[k]
				).mult(Zbase)).mult(0.5));
			}
			k += 1;
		}

		ZB.invert();  // mhos on one volt base

		if (ZB.getErrorCode() > 0) {
			DSS.doErrorMsg("TransformerObj.calcYPrim", "Matrix inversion error for transformer \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with tiny conductance to ground.", 117);
			ZB.clear();
			for (i = 0; i < ZB.order(); i++)
				ZB.set(i, i, new Complex(DSS.EPSILON, 0.0));
		}

		// now construct Y_oneTurn = AT * ZB.Invert * A
		/*
		 *     -1 1 0 ...
		 * A = -1 0 1 ..   order:  N-1 x N   N = NumWindings
		 *     ...
		 *                       -1 -1 ...
		 * AT = Transpose of A =  1  0 ...    N X N-1
		 *                        0  1 ..
		 */

		Y_1Volt.clear();
		Y_1Volt_NL.clear();

		/* Allocate temp complex arrays */
		cTempArray1 = new Complex[numWindings * 2];
		cTempArray2 = new Complex[numWindings * 2];

		a = new Complex[numWindings * 2];
		cMinusOne  = new Complex(-1.0, 0.0);
		At = new CMatrix(numWindings);

		for (i = 0; i < numWindings - 1; i++)
			At.set(i + 1, i, Complex.ONE);

		for (i = 0; i < numWindings - 1; i++)
			At.set(0, i, cMinusOne);

		cTempArray1[numWindings - 1] = Complex.ZERO;
		for (i = 0; i < numWindings; i++) {
			if (i == 0) {
				for (k = 0; k < numWindings - 1; k++)
					a[k] = cMinusOne;
			} else {
				for (k = 0; k < numWindings - 1; k++) {
					if (k == (i - 1)) {
						a[k] = Complex.ONE;
					} else {
						a[k] = Complex.ZERO;
					}
				}
			}
			ZB.vMult(cTempArray1, a);  /* Zb.invert * A */
			At.vMult(cTempArray2, cTempArray1);  /* AT * Result */
			for (j = 0; j < numWindings; j++)
				Y_1Volt.set(j, i, cTempArray2[j]);
		}


		/* Add magnetizing reactance to 2nd winding, assuming it is closest to the core
		 * Add both resistive element representing core losses and a reactive element representing
		 * magnetizing current. */
		Y_1Volt_NL.add(1, 1, new Complex((pctNoLoadLoss / 100.0 / Zbase), -pctImag / 100.0 / Zbase));

		// should have admittance of one phase of the transformer on a one-volt, wye-connected base

		// Now make into terminal admittance matrix and correct for actual voltage ratings
		// Y_Terminal = AT * Y_onevolt * A  where V_onevolt = A * V_terminal

		Y_Term.clear();
		Y_Term_NL.clear();
		At = new CMatrix(numWindings * 2);

		for (i = 0; i < numWindings; i++) {
			At.set(2 * i, i, new Complex(
				1.0 / (windings[i].getVbase() * windings[i].getPuTap()),
				0.0
			));
		}

		for (i = 0; i < numWindings; i++) {
			At.set(2 * i + 1, i, new Complex(  // TODO double check zero based indexing
				-1.0 / (windings[i].getVbase() * windings[i].getPuTap()),
				0.0
			));
		}

		for (i = 0; i < 2 * numWindings; i++) cTempArray1[i] = Complex.ZERO;

		for (i = 0; i < 2 * numWindings; i++) {
			for (k = 0; k < numWindings; k++) {
				if (i == 2 * k) {  // TODO double check zero based indexing
					a[k] = new Complex((1.0 / (windings[k].getVbase() * windings[k].getPuTap())), 0.0);
				} else {
					if (i == 2 * k + 1) {
						a[k] = new Complex((-1.0 / (windings[k].getVbase() * windings[k].getPuTap())), 0.0);
					} else {
						a[k] = Complex.ZERO;
					}
				}
			}

			/* Main transformer part */
			Y_1Volt.vMult(cTempArray1, a);
			At.vMult(cTempArray2, cTempArray1);  /* At * result */

			for (j = 0; j < 2 * numWindings; j++)
				Y_Term.set(j, i, cTempArray2[j]);

			/* No load part */
			Y_1Volt_NL.vMult(cTempArray1, a);
			At.vMult(cTempArray2, cTempArray1);  /* AT * Result */

			for (j = 0; j < 2 * numWindings; j++)
				Y_Term_NL.set(j, i, cTempArray2[j]);
		}

		/* Add a small admittance to the first conductor of each winding so that
		 * the matrix will always invert even if the user neglects to define a voltage
		 * reference on all sides.
		 */
		if (ppmFloatFactor != 0.0) {
			for (i = 0; i < numWindings; i++) {
				j = 2 * i;
				Y_Term.set(j, j, Y_Term.get(j, j).add( new Complex(0.0, windings[i].getY_PPM()) ));
				/* Y_Term.set(j, j, cMulRealImag(GetElement(j, j) , ppm_FloatFactorPlusOne));*/
			}
		}

		YterminalFreqMult = freqMult;
	}

	protected void fetchXfmrCode(String code) {
		int i;
		XfmrCodeObj obj;
		Winding w;

		if (Transformer.XfmrCodeClass == null)
			Transformer.XfmrCodeClass = (com.ncond.dss.general.XfmrCode) DSS.DSSClassList.get(DSS.classNames.find("xfmrcode"));

		if (Transformer.XfmrCodeClass.setActive(code)) {
			obj = (XfmrCodeObj) Transformer.XfmrCodeClass.getActiveObj();
			XfmrCode = code.toLowerCase();

			// set sizes and copy parameters
			setNumPhases(obj.getNPhases());
			setNumWindings(obj.getNumWindings());
			setNumConds(nPhases + 1);  // forces reallocation of terminals and conductors
			for (i = 0; i < numWindings; i++) {
				w = windings[i];
				w.setConnection(obj.getWinding(i).getConnection());
				w.setKVLL(obj.getWinding(i).getKVLL());
				w.setVbase(obj.getWinding(i).getVbase());
				w.setKVA(obj.getWinding(i).getKVA());
				w.setPuTap(obj.getWinding(i).getPuTap());
				w.setRpu(obj.getWinding(i).getRpu());
				w.setRNeut(obj.getWinding(i).getRNeut());
				w.setXNeut(obj.getWinding(i).getXNeut());
				w.setTapIncrement(obj.getWinding(i).getTapIncrement());
				w.setMinTap(obj.getWinding(i).getMinTap());
				w.setMaxTap(obj.getWinding(i).getMaxTap());
				w.setNumTaps(obj.getWinding(i).getNumTaps());
			}

			setTermRef();
			XHL = obj.getXHL();
			XHT = obj.getXHT();
			XLT = obj.getXLT();

			for (i = 0; i < (numWindings * (numWindings - 1) / 2); i++)
				XSC[i] = obj.getXSC()[i];

			thermalTimeConst = obj.getThermalTimeConst();
			nThermal = obj.getNThermal();
			mThermal = obj.getMThermal();
			FLRise = obj.getLRise();
			HSRise = obj.getHSRise();
			pctLoadLoss = obj.getPctLoadLoss();
			pctNoLoadLoss = obj.getPctNoLoadLoss();
			setNormMaxHKVA(obj.getNormMaxHKVA());
			setEmergMaxHKVA(obj.getEmergMaxHKVA());
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);
			YterminalFreqMult = 0.0;

			recalcElementData();
		} else {
			DSS.doSimpleMsg("Xfmr Code:" + code + " not found.", 180);
		}
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public double getPresentTap(int i) {
		return 0.0;
	}

	public double getThTau() {
		return thermalTimeConst;
	}

	public double getThN() {
		return nThermal;
	}

	public double getThM() {
		return mThermal;
	}

	public double[] getXSC() {
		return XSC;
	}

	public Winding getWinding(int idx) {
		return windings[idx];
	}

	public int getNumWindings() {
		return numWindings;
	}

	public double getNormMaxHKVA() {
		return normMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return emergMaxHKVA;
	}

	public double getNThermal() {
		return nThermal;
	}

	public double getMThermal() {
		return mThermal;
	}

	public double getFLRise() {
		return FLRise;
	}

	public double getHSRise() {
		return HSRise;
	}

	public int getActiveWindingIdx() {
		return activeWindingIdx;
	}

	public double getPpmFloatFactor() {
		return ppmFloatFactor;
	}

	public double getPctImag() {
		return pctImag;
	}

	public double getXHL() {
		return XHL;
	}

	public double getXHT() {
		return XHT;
	}

	public double getXLT() {
		return XLT;
	}

	public double getVABase() {
		return VABase;
	}

	public CMatrix getZB() {
		return ZB;
	}

	public CMatrix getY_1Volt() {
		return Y_1Volt;
	}

	public CMatrix getY_Term() {
		return Y_Term;
	}

	public CMatrix getY_1Volt_NL() {
		return Y_1Volt_NL;
	}

	public CMatrix getY_Term_NL() {
		return Y_Term_NL;
	}

	public double getThermalTimeConst() {
		return thermalTimeConst;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	public String getXfmrBank() {
		return XfmrBank;
	}

	public String getXfmrCode() {
		return XfmrCode;
	}

	public void setPpmFloatFactor(double ppmFloatFactor) {
		this.ppmFloatFactor = ppmFloatFactor;
	}

	public void setPctImag(double pctImag) {
		this.pctImag = pctImag;
	}

	public void setXHL(double xHL) {
		XHL = xHL;
	}

	public void setNormMaxHKVA(double normMaxHKVA) {
		this.normMaxHKVA = normMaxHKVA;
	}

	public void setEmergMaxHKVA(double emergMaxHKVA) {
		this.emergMaxHKVA = emergMaxHKVA;
	}

	public void setThermalTimeConst(double thermalTimeConst) {
		this.thermalTimeConst = thermalTimeConst;
	}

	public void setNThermal(double nThermal) {
		this.nThermal = nThermal;
	}

	public void setMThermal(double mThermal) {
		this.mThermal = mThermal;
	}

	public void setFLRise(double fLRise) {
		FLRise = fLRise;
	}

	public void setHSRise(double hSRise) {
		HSRise = hSRise;
	}

	public void setPctLoadLoss(double pctLoadLoss) {
		this.pctLoadLoss = pctLoadLoss;
	}

	public void setPctNoLoadLoss(double pctNoLoadLoss) {
		this.pctNoLoadLoss = pctNoLoadLoss;
	}

	public void setXHLChanged(boolean xHLChanged) {
		XHLChanged = xHLChanged;
	}

	public void setActiveWindingIdx(int activeWindingIdx) {
		this.activeWindingIdx = activeWindingIdx;
	}

	public void setSubstation(boolean isSubstation) {
		this.isSubstation = isSubstation;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public void setXfmrBank(String xfmrBank) {
		XfmrBank = xfmrBank;
	}

	public void setXfmrCode(String xfmrCode) {
		XfmrCode = xfmrCode;
	}

	public void setXHT(double xHT) {
		XHT = xHT;
	}

	public void setXLT(double xLT) {
		XLT = xLT;
	}

}

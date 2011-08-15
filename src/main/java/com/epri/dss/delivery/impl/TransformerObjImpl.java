package com.epri.dss.delivery.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Transformer;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;
import com.epri.dss.general.XfmrCodeObj;
import com.epri.dss.shared.CMatrix;

public class TransformerObjImpl extends PDElementImpl implements TransformerObj {

	private int deltaDirection;
	private double ppmFloatFactor;  // parts per million winding float factor
	private double pctImag;

	protected int numWindings;
	protected int maxWindings;
	protected int[] termRef;  // keeps track of terminal connections

	protected double XHL, XHT, XLT;  // per unit
	protected double ZBase;
	protected double[] XSC;     // per unit SC measurements
	protected double VABase;    // for impedances

	protected CMatrix ZB;
	protected CMatrix Y_1Volt;
	protected CMatrix Y_Term;
	protected CMatrix Y_1Volt_NL;   // no load Y's
	protected CMatrix Y_Term_NL;

	protected double Y_Terminal_FreqMult;

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

	private int activeWinding;
	private boolean isSubstation;
	private String substationName;
	private Winding[] winding;
	private String XfmrBank;
	private String XfmrCode;

	public TransformerObjImpl(DSSClass parClass, String transfName) {
		super(parClass);

		setName(transfName.toLowerCase());
		this.objType = parClass.getDSSClassType(); //DSSObjType + XFMR; // override PDElement (kept in both actually)

		setNPhases(3);  // directly set conds and phases
		this.nConds = this.nPhases + 1;
		setNumWindings(2);  // must do this after setting number of phases
		this.activeWinding = 0;  // TODO Check zero based indexing

		setNTerms(this.numWindings);  // force allocation of terminals and conductors

		this.XHL = 0.07;
		this.XHT = 0.35;
		this.XLT = 0.30;
		this.XHLChanged = true;  // set flag to for calc of XSC array from XHL, etc.

		this.deltaDirection = 1;
		this.substationName = "";
		this.XfmrBank = "";
		this.XfmrCode = "";

		this.VABase           = this.winding[0].getKVA() * 1000.0;
		this.thermalTimeConst = 2.0;
		this.nThermal        = 0.8;
		this.mThermal        = 0.8;
		this.FLRise           = 65.0;
		this.HSRise           = 15.0;  // hot spot rise
		this.normMaxHKVA      = 1.1 * this.winding[1].getKVA();
		this.emergMaxHKVA     = 1.5 * this.winding[1].getKVA();
		this.pctLoadLoss      = 2.0 * this.winding[1].getRpu() * 100.0;  // assume two windings
		this.ppmFloatFactor  = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < this.numWindings; i++)
			this.winding[i].computeAntiFloatAdder(this.ppmFloatFactor, this.VABase / this.nPhases);

		/* Default the no load properties to zero */
		this.pctNoLoadLoss    = 0.0;
		this.pctImag          = 0.0;

		/*this.BaseFrequency = 60.0; set in base class to circuit fundamental freq; do not reset here*/
		setFaultRate(0.007);
		this.isSubstation = false;

		this.Y_Terminal_FreqMult = 0.0;

		this.YOrder = this.nTerms * this.nConds;
		initPropertyValues(0);
		recalcElementData();
	}

	public void setNumWindings(int n) {
		int i;
		int oldWdgSize;

		if (n > 1) {
			for (i = 0; i < numWindings; i++)
				winding[i] = null;  // free old winding objects

			oldWdgSize = (numWindings - 1) * numWindings / 2;
			numWindings = n;
			maxWindings = n;
			setNConds(nPhases + 1);
			setNTerms(numWindings);

			winding = (com.epri.dss.delivery.Winding[]) Utilities.resizeArray(winding, maxWindings);  // reallocate collector array
			for (i = 0; i < maxWindings; i++)
				winding[i] = new WindingImpl();

			// array of short circuit measurements between pairs of windings
			XSC = (double[]) Utilities.resizeArray(XSC, ((numWindings - 1) * numWindings / 2));
			for (i = oldWdgSize; i < ((numWindings - 1) * numWindings / 2); i++)  // TODO Check zero based indexing
				XSC[i] = 0.30;
			termRef = (int[]) Utilities.resizeArray(termRef, 2 * numWindings * nPhases);

			/* Reallocate impedance matrices */
			ZB = null;
			Y_1Volt = null;
			Y_1Volt_NL = null;
			Y_Term = null;
			Y_Term_NL = null;

			ZB         = new CMatrixImpl(numWindings - 1);
			Y_1Volt    = new CMatrixImpl(numWindings);
			Y_1Volt_NL = new CMatrixImpl(numWindings);
			Y_Term     = new CMatrixImpl(2 * numWindings);
			Y_Term_NL  = new CMatrixImpl(2 * numWindings);
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Invalid number of windings: " + String.valueOf(n) + " for transformer " + getName(), 111);
		}
	}

	@Override
	public void recalcElementData() {
		int i, iHVolt;
		double VFactor;
//		PrintWriter F;
		Winding w;

		// determine delta direction
		// if high voltage is delta, delta leads wye
		// if high voltage is wye, delta lags wye
		if (winding[0].getConnection() == winding[1].getConnection()) {
			deltaDirection = 1;
		} else {
			if (winding[0].getKVLL() >= winding[1].getKVLL()) {
				iHVolt = 1;
			} else {
				iHVolt = 2;
			}
			switch (winding[iHVolt].getConnection()) {
			case 0:
				deltaDirection = 1;
				break;
			case 1:
				deltaDirection = -1;
				break;
			default:
				// ---old code --- DeltaDirection = Winding[1].Connection = 0? -1 : 1;
				break;
			}
		}

		setTermRef();  // re-establish termRef if num windings or connection changed

		for (i = 0; i < numWindings; i++) {
			w = winding[i];
			if (w.getNumTaps() > 0) {
				w.setTapIncrement( (w.getMaxTap() - w.getMinTap()) / w.getNumTaps() );
			} else {
				w.setTapIncrement(0.0);
			}
		}

		if (XHLChanged) {
			/* should only happen for 2- and 3-winding transformers */
			if (numWindings <= 3)
				for (i = 0; i < (numWindings * (numWindings - 1) / 2); i++) {
					switch (i) {
					case 0:  // TODO Check zero based indexing
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
			XHLChanged = false;
		}

		// set winding voltage bases (in volts)
		for (i = 0; i < numWindings; i++) {
			w = winding[i];  // get the actual turns voltage base for each winding
			switch (w.getConnection()) {
			case 0:
				switch (nPhases) {  // wye
				case 2:
					w.setVBase(w.getKVLL() * DSSGlobals.InvSQRT3x1000);  // assume 3-phase for 2-phase designation
					break;
				case 3:
					w.setVBase(w.getKVLL() * DSSGlobals.InvSQRT3x1000);
					break;
				default:
					w.setVBase(w.getKVLL() * 1000.0);
					break;
				}
				break;
			case 1:
				w.setVBase(w.getKVLL() * 1000.0);  // delta
				break;
			}
		}

		/* Base rating of winding 1 */
		VABase = winding[0].getKVA() * 1000.0;

		for (i = 0; i < numWindings; i++)
			winding[i].computeAntiFloatAdder(ppmFloatFactor, VABase / nPhases);

		/* Normal and emergency terminal current rating for UE check */
		VFactor = 1.0;  // ensure initialization
		switch (winding[0].getConnection()) {
		case 0:
			VFactor = winding[0].getVBase() * 0.001;  // wye
			break;
		case 1:
			switch (nPhases) {
			case 1:
				VFactor = winding[0].getVBase() * 0.001;
				break;
			case 2:
				VFactor = winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
				break;
			case 3:
				VFactor = winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
				break;
			default:
				VFactor = winding[0].getVBase() * 0.001 * 0.5 / Math.sin(Math.PI / nPhases);
				break;
			}
			break;
		}

		/* Divide per phase kVA by voltage to neutral */
		setNormAmps(normMaxHKVA  / nPhases / VFactor);
		setEmergAmps(emergMaxHKVA / nPhases / VFactor);

		calcY_Terminal(1.0);  // calc Y_Terminal at base frequency
	}

	/**
	 * Transformer structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter f) {
		int i;

		/* Write only properties that were explicitly set in the
		 * final order they were actually set.
		 */
		int iProp = getNextPropertySet(0);  // works on ActiveDSSObject   TODO Check zero based indexing
		while (iProp > 0) {
			/* Trap wdg= and write out array properties instead */
			switch (parentClass.getRevPropertyIdxMap()[iProp]) {
			case 2:  // if wdg= was ever used write out arrays ...   TODO Check zero based indexing
				for (i = 11; i < 16; i++)
					f.printf(" %s=%s", parentClass.getPropertyName()[i], getPropertyValue(i));
				for (i = 0; i < numWindings; i++)
					f.printf(" wdg=%d %sR=%.7g", i, "%", winding[i].getRpu() * 100.0);
				break;
			case 3:
				/* do nothing; */  // ignore these properties; use arrays instead
				break;
			case 4:
				/* do nothing; */
				break;
			case 5:
				/* do nothing; */
				break;
			case 6:
				/* do nothing; */
				break;
			case 7:
				/* do nothing; */
				break;
			case 8:
				/* do nothing; */
				break;
			default:
				f.printf(" %s=%s", parentClass.getPropertyName()[parentClass.getRevPropertyIdxMap()[iProp]],
						Utilities.checkForBlanks(getPropertyValue(iProp)));
				break;
			}
			iProp = getNextPropertySet(iProp);
		}
	}

	/**
	 * Sets an array which maps the two conductors of each winding to the phase
	 * and neutral conductors of the transformer according to the winding connection.
	 */
	// FIXME Protected method in OpenDSS
	public void setTermRef() {
		int i, j, k;

		k = -1;

		switch (nPhases) {
		case 1:
			for (j = 0; j < numWindings; j++) {
				k += 1;
				termRef[k] = (j - 1) * nConds + 1;
				k += 1;
				termRef[k] =  j * nConds;
			}
			break;
		default:
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < numWindings; j++) {
					k += 1;
					switch (winding[i].getConnection()) {
					case 0:  // Wye
						termRef[k] = (j - 1) * nConds + i;
						k += 1;
						termRef[k] = j * nConds;
						break;
					/* **** WILL THIS WORK for 2-PHASE OPEN DELTA ???? Need to check this sometime */
					case 1:  // Delta
						termRef[k] = (j - 1) * nConds + i;
						k += 1;
						termRef[k] = (j - 1) * nConds + rotatePhases(i);  // connect to next phase in sequence
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
			if (YPrimSeries != null) YPrimSeries = null;
			if (YPrimShunt != null) YPrimShunt = null;
			if (YPrim != null) YPrim = null;

			YPrimSeries = new CMatrixImpl(YOrder);
			YPrimShunt  = new CMatrixImpl(YOrder);
			YPrim        = new CMatrixImpl(YOrder);
		} else {
			/* Same size as last time; just zero out to start over */
			YPrimSeries.clear(); // zero out YPrim
			YPrimShunt.clear(); // zero out YPrim
			YPrim.clear();
		}

		// Setfrequency multipliers for this calculation
		YPrimFreq      = DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;
		// Check for rebuilding Y_Terminal; only rebuild if freq is different than last time
		if (freqMultiplier != Y_Terminal_FreqMult)
			calcY_Terminal(freqMultiplier);

		buildYPrimComponent(YPrimSeries, Y_Term);
		buildYPrimComponent(YPrimShunt,  Y_Term_NL);

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
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, j;

		super.dumpProperties(f, complete);

		/* Basic property dump */

		f.println("~ " + "NumWindings=" + numWindings);
		f.println("~ " + "phases=" + nPhases);

		for (i = 0; i < numWindings; i++) {
			Winding W = winding[i];
			if (i == 0) {
				f.println("~ " + "wdg=" + i + " bus=" + getFirstBus());
			} else {
				f.println("~ " + "wdg=" + i + " bus=" + getNextBus());
			}

			switch (W.getConnection()) {
			case 0:
				f.println("~ conn=wye");
				break;
			case 1:
				f.println("~ conn=delta");
				break;
			}
			f.println("~ kv=" + W.getKVLL());
			f.println("~ kva=" + W.getKVA());
			f.println("~ tap=" + W.getPUTap());
			f.println("~ %r=" + (W.getRpu() * 100.0));
			f.println("~ rneut=" + W.getRNeut());
			f.println("~ xneut=" + W.getXNeut());
		}

		f.println("~ " + "xhl=" + XHL * 100.0);
		f.println("~ " + "xht=" + XHT * 100.0);
		f.println("~ " + "xlt=" + XLT * 100.0);
		f.print("~ Xscmatrix= \"");
		for (i = 0; i < (numWindings - 1) * numWindings / 2; i++)
			f.print(XSC[i] * 100.0 + " ");
		f.println("\"");
		f.println("~ " + "NormMAxHkVA=" + normMaxHKVA);
		f.println("~ " + "EmergMAxHkVA=" + emergMaxHKVA);
		f.println("~ " + "thermal=" + thermalTimeConst);
		f.println("~ " + "n=" + nThermal);
		f.println("~ " + "m=" + mThermal);
		f.println("~ " + "flrise=" + FLRise);
		f.println("~ " + "hsrise=" + HSRise);
		f.println("~ " + "%loadloss=" + pctLoadLoss);
		f.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (i = 27; i < Transformer.NumPropsThisClass; i++)
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		for (i = Transformer.NumPropsThisClass; i < parentClass.getNumProperties(); i++)
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete) {
			f.println();
			f.println("ZB: (inverted)");
			for (i = 0; i < numWindings - 1; i++) {
				for (j = 0; j < i; j++)
					f.print(ZB.getElement(i, j).getReal() + " ");
				f.println();
			}
			for (i = 0; i < numWindings - 1; i++) {
				for (j = 0; j < i; j++)
					f.print(ZB.getElement(i, j).getImaginary() + " ");
				f.println();
			}

			f.println();
			f.println("Y_OneVolt");
			for (i = 0; i < numWindings; i++) {
				for (j = 0; j < i; j++)
					f.print(Y_1Volt.getElement(i, j).getReal() + " ");
					f.println();
			}
			for (i = 0; i < numWindings; i++) {
				for (j = 0; j < i; j++)
					f.print(Y_1Volt.getElement(i, j).getImaginary() + " ");
					f.println();
			}

			f.println();
			f.println("Y_Terminal");
			for (i = 0; i < 2 * numWindings; i++) {
				for (j = 0; j < i; j++)
					f.print(Y_Term.getElement(i, j).getReal() + " ");
					f.println();
			}
			for (i = 0; i < 2 * numWindings; i++) {
				for (j = 0; j < i; j++)
					f.print(Y_Term.getElement(i, j).getImaginary() + " ");
					f.println();
			}
			f.println();
			f.print("TermRef= ");
			for (i = 0; i < 2 * numWindings * nPhases; i++)
				f.print(termRef[i] + " ");
			f.println();

		}
	}

	public void setPresentTap(int i, double value) {
		double tempVal;

		if ((i >= 0) && (i < numWindings)) {
			Winding W = winding[i];
			/* Range Checking */
			tempVal = value;
			if (tempVal < W.getMinTap()) {
				tempVal = W.getMinTap();
			} else if (tempVal > W.getMaxTap()) {
				tempVal = W.getMaxTap();
			}

			if (tempVal != W.getPUTap()) {  /* Only if there's been a change */
				W.setPUTap(tempVal);
				setYPrimInvalid(true);  // this property triggers setting systemYChanged=true
				recalcElementData();
			}
		}
	}

	public double getWdgResistance(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getRpu();
		} else {
			return 0.0;
		}
	}

	public double getWdgKVA(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getKVA();
		} else {
			return 0.0;
		}
	}

	public double getWdgRNeutral(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getRNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgXNeutral(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getXNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgYPPM(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getY_PPM();
		} else {
			return 0.0;
		}
	}

	public double getXsc(int i) {
		int imax = (numWindings - 1) * numWindings / 2;

		if ((i >= 0) && (i < imax)) {
			return XSC[i];
		} else {
			return 0.0;
		}
	}

	public int getWdgConnection(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getConnection();
		} else {
			return 0;
		}
	}

	public double getMinTap(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getMinTap();
		} else {
			return 0.0;
		}
	}

	public double getMaxTap(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getMaxTap();
		} else {
			return 0.0;
		}
	}

	public int getNumTaps(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getNumTaps();
		} else {
			return 0;
		}
	}

	public double getTapIncrement(int i) {
		if ((i >= 0) && (i < numWindings)) {
			return winding[i].getTapIncrement();
		} else {
			return 0.0;
		}
	}

	/**
	 * Voltages across indicated winding.
	 * Fill VBuffer array which must be adequately allocated by calling routine
	 * Order is number of phases.
	 */
	public void getWindingVoltages(int iWind, Complex[] VBuffer) {
		int i, ii, k, neutTerm;

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			/* Return zero if winding number improperly specified */
			if ((iWind < 0) || (iWind >= numWindings)) {
				for (i = 0; i < nConds; i++)
					VBuffer[i] = Complex.ZERO;
				return;
			}

			/* Load up VTemp - already allocated for all cktelements */
			SolutionObj sol = globals.getActiveCircuit().getSolution();

			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV()[nodeRef[i]];


			k = (iWind - 1) * nConds;  // Offset for winding   TODO Check zero based indexing
			neutTerm = nPhases + k + 1;
			for (i = 0; i < nPhases; i++) {
				switch (winding[iWind].getConnection()) {
				case 0:  // wye
					VBuffer[i] = VTerminal[i + k].subtract(VTerminal[neutTerm]);
					break;
				case 1:  // delta
					ii = rotatePhases(i);  // get next phase in sequence
					VBuffer[i] = VTerminal[i + k].subtract(VTerminal[ii + k]);
					break;
				}
			}

		} catch (Exception e) {
			globals.doSimpleMsg("Error filling voltage buffer in getWindingVoltages for circuit element: Transformer."+getName()+DSSGlobals.CRLF+
					"Probable Cause: Invalid definition of element."+DSSGlobals.CRLF+
					"System error message: "+e.getMessage(), 114);
		}
	}

	public double getBaseVoltage(int i) {
		if ((i < 0) || (i >= numWindings)) {
			return winding[0].getVBase();
		} else {
			return winding[i].getVBase();
		}
	}

	@Override
	public void getLosses(double[] totalLosses, double[] loadLosses, double[] noLoadLosses) {
		Complex cTotalLosses, cLoadLosses, cNoLoadLosses;

		/* Calculates losses in watts, vars */
		cTotalLosses = getLosses();  // Side effect: computes ITerminal

		/* Compute no load losses in YPrim_Shunt */
		Complex[] cTempIterminal = new Complex[YOrder];
		computeVTerminal();
		YPrimShunt.MVMult(cTempIterminal, VTerminal);
		/* No load losses are sum of all powers coming into YPrim_Shunt from each terminal */
		cNoLoadLosses = Complex.ZERO;
		for (int i = 0; i < YOrder; i++)
			cNoLoadLosses = cNoLoadLosses.add( VTerminal[i].multiply(cTempIterminal[i].conjugate()) );

		cLoadLosses = cTotalLosses.subtract(cNoLoadLosses);

		/* Handle pass by reference */
		totalLosses[0] = cTotalLosses.getReal();
		totalLosses[1] = cTotalLosses.getImaginary();
		loadLosses[0] = cLoadLosses.getReal();
		loadLosses[1] = cLoadLosses.getImaginary();
		noLoadLosses[0] = cNoLoadLosses.getReal();
		noLoadLosses[1] = cNoLoadLosses.getImaginary();

		cTempIterminal = null;

	}

	/**
	 * Gets the property for the active winding; set the active winding before calling.
	 */
	@Override
	public String getPropertyValue(int index) {
		int i;
		String result;

		if (((index >= 11) && (index <= 15)) || (index == 19) || (index == 36)) {
			result = "[";
		} else {
			result = "";
		}

		switch (index) {
		case 0:
			result = String.valueOf(nPhases);
			break;
		case 1:
			result = String.valueOf(numWindings);
			break;
		case 2:
			result = String.valueOf(activeWinding);  // return active winding
			break;
		case 3:
			result = getBus(activeWinding);    // return bus spec for active winding
			break;
		case 4:
			switch (winding[activeWinding].getConnection()) {
			case 0:
				result = "wye ";
				break;
			case 1:
				result = "delta ";
				break;
			}
			break;
		case 5:
			result = String.format("%.7g", winding[activeWinding].getKVLL());
			break;
		case 6:
			result = String.format("%.7g", winding[activeWinding].getKVA());
			break;
		case 7:
			result = String.format("%.7g", winding[activeWinding].getPUTap());
			break;
		case 8:
			result = String.format("%.7g", winding[activeWinding].getRpu() * 100.0);  // %R
			break;
		case 9:
			result = String.format("%.7g", winding[activeWinding].getRNeut());
			break;
		case 10:
			result = String.format("%.7g", winding[activeWinding].getXNeut());
			break;

		case 11:
			for (i = 0; i < numWindings; i++)
				result = result + getBus(i) + ", ";
			break;
		case 12:
			for (i = 0; i < numWindings; i++)
				switch (winding[i].getConnection()) {
				case 0:
					result = result + "wye, ";
					break;
				case 1:
					result = result + "delta, ";
					break;
				}
			break;
		case 13:
			for (i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVLL());
			break;
		case 14:
			for (i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVA());
			break;
		case 15:
			for (i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getPUTap());  // interpretAllTaps(Param);
			break;
		case 16:
			result = String.format("%.7g", XHL * 100.0);
			break;
		case 17:
			result = String.format("%.7g", XHT * 100.0);
			break;
		case 18:
			result = String.format("%.7g", XLT * 100.0);
			break;
		case 19:
			for (i = 0; i < (numWindings - 1) * numWindings / 2; i++)
				result = result + String.format("%-g, ", XSC[i] * 100.0);// Parser.ParseAsVector(((NumWindings - 1)*NumWindings div 2), Xsc);
			break;
		case 25:
			result = String.format("%.7g", pctLoadLoss);
			break;
		case 26:
			result = String.format("%.7g", pctNoLoadLoss);
			break;
		case 30:
			result = String.format("%.7g", winding[activeWinding].getMaxTap());
			break;
		case 31:
			result = String.format("%.7g", winding[activeWinding].getMinTap());
			break;
		case 32:
			result = String.format("%-d", winding[activeWinding].getNumTaps());
			break;
		case 34:
			result = String.format("%.7g", pctImag);
			break;
		case 35:
			result = String.format("%.7g", ppmFloatFactor / 1.0e-6);
			break;
		case 36:
			for (i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getRpu() * 100.0);
			break;

		default:
			result = super.getPropertyValue(index);
			break;
		}

		// Overrides
		switch (index = Transformer.NumPropsThisClass) {
		case 0:
			result = String.format("%-.5g", getNormAmps());   // normAmps
			break;
		case 1:
			result = String.format("%-.5g", getEmergAmps());  // emergAmps
			break;
		}

		if (((index >= 11) && (index <= 15)) || (index == 19) || (index == 36))
			result = "]";

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[0] = "3";  // "phases";
		propertyValue[1] = "2";  // "windings";
		// winding definition
		propertyValue[2] = "1";  // "wdg";
		propertyValue[3] = getBus(1);  // "bus";  // TODO Check zero based indexing
		propertyValue[4] = "wye";  // "conn";
		propertyValue[5] = "12.47";  // if 2 or 3-phase:  phase-phase else actual winding
		propertyValue[6] = "1000";
		propertyValue[7] = "1.0";
		propertyValue[8] = "0.2";
		propertyValue[9] = "-1";
		propertyValue[10] = "0";

		// general data
		propertyValue[11] = "";
		propertyValue[12] = "";
		propertyValue[13] = ""; // if 1-phase: actual winding rating; else phase-phase
		propertyValue[14] = ""; // if 1-phase: actual winding rating; else phase-phase
		propertyValue[15] = "";
		propertyValue[16] = "7";
		propertyValue[17] = "35";
		propertyValue[18] = "30";
		propertyValue[19] = "";  // x12 13 14... 23 24.. 34 ..
		propertyValue[20] = "2";
		propertyValue[21] = ".8";
		propertyValue[22] = ".8";
		propertyValue[23] = "65";
		propertyValue[24] = "15";
		propertyValue[25] = String.format("%.7g", pctLoadLoss);
		propertyValue[26] = String.format("%.7g", pctNoLoadLoss);  // defaults to zero
		propertyValue[27] = "";
		propertyValue[28] = "";
		propertyValue[29] = "n";  // =y/n
		propertyValue[30] = "1.10";
		propertyValue[31] = "0.90";
		propertyValue[32] = "32";
		propertyValue[33] = "";
		propertyValue[34] = "0";
		propertyValue[35] = "1";
		propertyValue[36] = "";

		super.initPropertyValues(Transformer.NumPropsThisClass);

		// override some inherited properties
		propertyValue[Transformer.NumPropsThisClass + 1] = "400";    // normAmps  // TODO Check zero based indexing
		propertyValue[Transformer.NumPropsThisClass + 2] = "600";    // emergAmps
		propertyValue[Transformer.NumPropsThisClass + 3] = "0.007";  // faultRate
		propertyValue[Transformer.NumPropsThisClass + 4] = "100";    // pctPerm
		propertyValue[Transformer.NumPropsThisClass + 5] = "36";     // hrsToRepair

		clearPropSeqArray();  // so the overrides don't show up on save
	}

	/**
	 * For Delta connections or Line-Line voltages.
	 */
	public int rotatePhases(int iPhs) {
		int result = iPhs + deltaDirection;  // TODO Check zero based indexing

		// make sure result is within limits
		if (nPhases > 2) {
			// assumes 2 phase delta is open delta
			if (result > nPhases)
				result = 1;
			if (result < 1)
				result = nPhases;
		} else if (result < 1) {
			result = 3;  // for 2-phase delta, next phase will be 3rd phase
		}

		return result;
	}

	/**
	 * Converts default 3-phase transformer model into equivalent positive-sequence
	 * using scripting.
	 */
	@Override
	public void makePosSequence() {
		int iW, i;
		MutableInt n = new MutableInt();
		String s;
		int[] nodes = new int[5];  // integer buffer
		boolean onPhase1;

		DSSGlobals globals = DSSGlobals.getInstance();

		/* First, determine if we can convert this one. */
		if ((nPhases == 1) || (nPhases == 2)) {  // disable if any terminal not connected to phase one
			for (iW = 0; iW < numWindings; iW++) {
				onPhase1 = false;
				/* Load up auxiliary parser */
				globals.getAuxParser().setCmdString(getBus(iW));
				globals.getAuxParser().getNextParam();
				s = globals.getAuxParser().parseAsBusName(n, nodes);  // TODO Check N gets set
				if (n.intValue() == 0)
					onPhase1 = true;
				for (i = 0; i < n.intValue(); i++)
					if (nodes[i] == 1)
						onPhase1 = true;
				if (!onPhase1) {
					setEnabled(false);  // we won't use this one
					return;
				}
			}
		}

		/* Construct transformer definition string */
		s = "Phases=1  Conns=(";
		for (i = 0; i < numWindings; i++)
			s = s + "Wye ";
		s = s + ")  buses=(";

		for (i = 0; i < numWindings; i++)
			s = s + getBus(i) + " ";
		s = s + ")  kVS=(";

		for (i = 0; i < numWindings; i++) {
			Winding W = winding[i];
			if ((nPhases > 1) || (W.getConnection() != 0)) {
				s = s + String.format(" %-.5g", W.getKVLL() / DSSGlobals.SQRT3);
			} else {
				s = s + String.format(" %-.5g", W.getKVLL());
			}
		}
		s = s + ")  kVAs=(";

		for (i = 0; i < numWindings; i++) {
			Winding W = winding[i];
			s = s + String.format(" %-.5g", W.getKVA() / nPhases);
		}
		s = s + ")";

		s = s + " NormHkVA="+String.format(" %-.5g %-.5g", normMaxHKVA / nPhases, emergMaxHKVA / nPhases);

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
	}

	private void addNeutralToY(double freqMultiplier) {
		int i, j;
		Complex value = null;

		/* Account for neutral impedances */
		for (i = 0; i < numWindings; i++) {
			Winding W = winding[i];
			if (W.getConnection() == 0) {
				// handle wye, but ignore delta (and open wye)
				if (W.getRNeut() >= 0) {
					// <0 is flag for open neutral (Ignore)
					if ((W.getRNeut() == 0) && (W.getXNeut() == 0))
						// solidly grounded
						value = new Complex(1000000, 0);
				} else {
					// 1 microohm resistor
					value = new Complex(W.getRNeut(), W.getXNeut() * freqMultiplier).invert();
				}
				j = i * nConds;
				YPrimSeries.addElement(j, j, value);
			} else {
				// bump up neutral admittance a bit in case neutral is floating
				j = i * nConds;
				if (ppmFloatFactor != 0.0) {
					YPrimSeries.setElement(j, j, YPrimSeries.getElement(j, j).add( new Complex(0.0, W.getY_PPM()) ));
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
				value = YTerminal.getElement(i, j);
				// this value goes in Yprim nPhases times
				for (int k = 0; k < nPhases - 1; k++)
					YPrimComponent.addElemSym(termRef[i + k * nw2], termRef[j + k * nw2], value);
			}
		}
	}

	public double getBasekVLL(int i) {
		return winding[i].getKVLL();
	}

	private void calcY_Terminal(double freqMult) {
		int i, j, k;
		Complex[] a, cTempArray1, cTempArray2;
		Complex cMinusOne;
		CMatrix At;


		// construct ZBMatrix
		ZB.clear();
		ZBase = 1.0 / (VABase / nPhases);  // base ohms on 1.0 volt basis
		for (i = 0; i < numWindings - 1; i++)
			/* convert pu to ohms on one volt base as we go... */
			ZB.setElement(i, i, new Complex((winding[0].getRpu() + winding[i + 1].getRNeut()), freqMult * XSC[i]).multiply(ZBase));

		// off diagonals
		k = numWindings;
		for (i = 0; i < numWindings - 1; i++) {
			for (j = 0; j < numWindings - 1; j++)
				ZB.setElemSym(i, j,
					ZB.getElement(i, i).add(ZB.getElement(j, j)).subtract(
						new Complex((winding[i + 1].getRpu() + winding[j + 1].getRpu()), freqMult * XSC[k]).multiply(ZBase)).multiply(0.5) );
			k += 1;
		}

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			AssignFile(F, CircuitName_ + "Transformer_"+Name+".TXT");
//			Rewrite(F);
//			Writeln(F,"ZB before inverting...");
//			DumpComplexMatrix(F, ZB);
//		}
/* ***************************************************************************************** */

		ZB.invert();  // mhos on one volt base

		if (ZB.getInvertError() > 0) {
			DSSGlobals.getInstance().doErrorMsg("TransformerObj.calcYPrim", "Matrix inversion error for transformer \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with tiny conductance to ground.", 117);
			ZB.clear();
			for (i = 0; i < ZB.getNOrder(); i++)
				ZB.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));
		}

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			F.println("ZB after inverting...");
//			dumpComplexMatrix(F, ZB);
//		}
/* ***************************************************************************************** */

		// Now construct Y_Oneturn = AT * ZB.Invert * A
		/*     -1 1 0 ...
		A = -1 0 1 ..   order:  N-1 x N   N = NumWindings
			...
								-1 -1 ...
		AT = Transpose of A =  1  0 ...    N X N-1
								0  1 ..
		*/

		Y_1Volt.clear();
		Y_1Volt_NL.clear();

		/* Allocate temp complex arrays */
		cTempArray1 = new Complex[numWindings * 2];
		cTempArray2 = new Complex[numWindings * 2];


		a          = new Complex[numWindings * 2];
		cMinusOne  = new Complex(-1.0, 0.0);
		At         = new CMatrixImpl(numWindings);
		for (i = 0; i < numWindings - 1; i++)
			At.setElement(i + 1, i, Complex.ONE);
		for (i = 0; i < numWindings - 1; i++)
			At.setElement(1, i, cMinusOne);
		cTempArray1[numWindings] = Complex.ZERO;
		for (i = 0; i < numWindings; i++) {
			if (i == 1) {
				for (k = 0; k < numWindings - 1; k++)
					a[k] = cMinusOne;
			} else {
				for (k = 0; k < numWindings - 1; k++)
					if (k == (i - 1)) {
						a[k] = Complex.ONE;
					} else {
						a[k] = Complex.ZERO;
					}
			}
			ZB.MVMult(cTempArray1, a);  /* Zb.invert * A */
			At.MVMult(cTempArray2, cTempArray1);  /* AT * Result */
			for (j = 0; j < numWindings; j++)
				Y_1Volt.setElement(j, i, cTempArray2[j]);
		}


		/* Add magnetizing reactance to 2nd winding, assuming it is closest to the core
		 * Add both resistive element representing core losses and a reactive element representing
		 * magnetizing current.
		 */
		Y_1Volt_NL.addElement(1, 1, new Complex((pctNoLoadLoss / 100.0 / ZBase), -pctImag / 100.0 / ZBase));

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			F.println("Y_OneVolt ...");
//			dumpComplexMatrix(F, Y_OneVolt);
//		}
/* ***************************************************************************************** */
		// should have admittance of one phase of the transformer on a one-volt, wye-connected base

		// Now make into terminal admittance matrix and correct for actual voltage ratings
		// Y_Terminal = AT * Y_onevolt * A  where V_onevolt = A * V_terminal

		At = null;

		Y_Term.clear();
		Y_Term_NL.clear();
		At = new CMatrixImpl(numWindings * 2);

		for (i = 0; i < numWindings; i++)
			At.setElement(2 * i - 1, i, new Complex(1.0 / (winding[i].getVBase() * winding[i].getPUTap()), 0.0));
		for (i = 0; i < numWindings; i++)
			At.setElement(2 * i,     i, new Complex(-1.0 / (winding[i].getVBase() * winding[i].getPUTap()), 0.0));
		for (i = 0; i < 2 * numWindings; i++)
			cTempArray1[i] = Complex.ZERO;

		for (i = 0; i < 2 * numWindings; i++) {
			for (k = 0; k < numWindings; k++) {
				if (i == (2 * k - 1)) {
					a[k] = new Complex((1.0 / (winding[k].getVBase() * winding[k].getPUTap())), 0.0);
				} else {
					if (i == 2 * k) {
						a[k] = new Complex((-1.0 / (winding[k].getVBase() * winding[k].getPUTap())), 0.0);
					} else {
						a[k] = Complex.ZERO;
					}
				}
			}

			/* Main transformer part */
			Y_1Volt.MVMult(cTempArray1, a);
			At.MVMult(cTempArray2, cTempArray1);  /* AT * Result */
			for (j = 0; j < 2 * numWindings; j++)
				Y_Term.setElement(j, i, cTempArray2[j]);
			/* No load part */
			Y_1Volt_NL.MVMult(cTempArray1, a);
			At.MVMult(cTempArray2, cTempArray1);  /* AT * Result */
			for (j = 0; j < 2 * numWindings; j++)
				Y_Term_NL.setElement(j, i, cTempArray2[j]);
		}

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			F.println("Y_Terminal before adding small element to diagonals ...");
//			dumpComplexMatrix(F, Y_Terminal);
//		}
/* ***************************************************************************************** */

		/* Add a small admittance to the first conductor of each winding so that
		 * the matrix will always invert even if the user neglects to define a voltage
		 * reference on all sides
		 */
		if (ppmFloatFactor != 0.0) {
			for (i = 0; i < numWindings; i++) {
				j = 2 * i - 1;
				Y_Term.setElement(j, j, Y_Term.getElement(j, j).add( new Complex(0.0, winding[i].getY_PPM()) ));
				/* SetElement(j, j, CmulReal_im(GetElement(j, j) , ppm_FloatFactorPlusOne));*/
			}
		}

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			F.println("Y_Terminal after adding small element to diagonals ...");
//			dumpComplexMatrix(F, Y_Terminal);
//			F.close();
//		}
/* ***************************************************************************************** */

		At = null;
		a = null;
		cTempArray1 = null;
		cTempArray2 = null;

		Y_Terminal_FreqMult = freqMult;
	}

	// FIXME Private method in OpenDSS
	public void fetchXfmrCode(String code) {
		XfmrCodeObj obj;
		int i;

		DSSGlobals globals = DSSGlobals.getInstance();

		if (TransformerImpl.XfmrCodeClass == null)
			TransformerImpl.XfmrCodeClass = (com.epri.dss.general.XfmrCode) globals.getDSSClassList().get(globals.getClassNames().find("xfmrcode"));

		if (TransformerImpl.XfmrCodeClass.setActive(code)) {
			obj = (XfmrCodeObj) TransformerImpl.XfmrCodeClass.getActiveObj();
			XfmrCode = code.toLowerCase();
			// set sizes and copy parameters
			setNPhases(obj.getNPhases());
			setNumWindings(obj.getNumWindings());
			setNConds(nPhases + 1);  // forces reallocation of terminals and conductors
			for (i = 0; i < numWindings; i++) {
				Winding W = winding[i];
				W.setConnection(obj.getWinding()[i].getConnection());
				W.setKVLL(obj.getWinding()[i].getKVLL());
				W.setVBase(obj.getWinding()[i].getVBase());
				W.setKVA(obj.getWinding()[i].getKVA());
				W.setPUTap(obj.getWinding()[i].getPUTap());
				W.setRpu(obj.getWinding()[i].getRpu());
				W.setRNeut(obj.getWinding()[i].getRNeut());
				W.setXNeut(obj.getWinding()[i].getXNeut());
				W.setTapIncrement(obj.getWinding()[i].getTapIncrement());
				W.setMinTap(obj.getWinding()[i].getMinTap());
				W.setMaxTap(obj.getWinding()[i].getMaxTap());
				W.setNumTaps(obj.getWinding()[i].getNumTaps());
			}

			setTermRef();
			XHL = obj.getXHL();
			XHT = obj.getXHT();
			XLT = obj.getXLT();
			for (i = 0; i < (numWindings * (numWindings - 1) / 2); i++)
				XSC[i] = obj.getXSC()[i];
			thermalTimeConst = obj.getThermalTimeConst();
			nThermal        = obj.getNThermal();
			mThermal        = obj.getMThermal();
			FLRise           = obj.getLRise();
			HSRise           = obj.getHSRise();
			pctLoadLoss      = obj.getPctLoadLoss();
			pctNoLoadLoss    = obj.getPctNoLoadLoss();
			setNormMaxHKVA(obj.getNormMaxHKVA());
			setEmergMaxHKVA(obj.getEmergMaxHKVA());
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);
			Y_Terminal_FreqMult = 0.0;

			recalcElementData();
		} else {
			globals.doSimpleMsg("Xfmr Code:" + code + " not found.", 180);
		}
	}

	public double getPresentTap(int i) {
		return 0.0;
	}

	/* CIM accessors */

	public int getNumWindings() {
		return numWindings;
	}

	public int getActiveWinding() {
		return activeWinding;
	}

	public void setActiveWinding(int winding) {
		activeWinding = winding;
	}

	public void setSubstation(boolean value) {
		isSubstation = value;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String name) {
		substationName = name;
	}

	public Winding[] getWinding() {
		return winding;
	}

	public void setWinding(Winding[] values) {
		winding = values;
	}

	public String getXfmrBank() {
		return XfmrBank;
	}

	public void setXfmrBank(String bank) {
		XfmrBank = bank;
	}

	public String getXfmrCode() {
		return XfmrCode;
	}

	public void setXfmrCode(String code) {
		XfmrCode = code;
	}

	public double getPPM_FloatFactor() {
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

	public double getBaseVA() {
		return VABase;
	}

	public double getNormMaxHKVA() {
		return normMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return emergMaxHKVA;
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

	public double getFLRise() {
		return FLRise;
	}

	public double getHSRise() {
		return HSRise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	// FIXME Private memebers in OpenDSS

	public int getDeltaDirection() {
		return deltaDirection;
	}

	public void setDeltaDirection(int direction) {
		deltaDirection = direction;
	}

	public int getMaxWindings() {
		return maxWindings;
	}

	public void setMaxWindings(int max) {
		maxWindings = max;
	}

	public int[] getTermRef() {
		return termRef;
	}

	public void setTermRef(int[] ref) {
		termRef = ref;
	}

	public double getZBase() {
		return ZBase;
	}

	public void setZBase(double zbase) {
		ZBase = zbase;
	}

	public double[] getXSC() {
		return XSC;
	}

	public void setXSC(double[] xsc) {
		XSC = xsc;
	}

	public double getVABase() {
		return VABase;
	}

	public void setVABase(double base) {
		VABase = base;
	}

	public CMatrix getZB() {
		return ZB;
	}

	public void setZB(CMatrix zb) {
		ZB = zb;
	}

	public CMatrix getY_1Volt() {
		return Y_1Volt;
	}

	public void setY_1Volt(CMatrix value) {
		Y_1Volt = value;
	}

	public CMatrix getY_Term() {
		return Y_Term;
	}

	public void setY_Term(CMatrix value) {
		Y_Term = value;
	}

	public CMatrix getY_1Volt_NL() {
		return Y_1Volt_NL;
	}

	public void setY1VoltNL(CMatrix value) {
		Y_1Volt_NL = value;
	}

	public CMatrix getYTermNL() {
		return Y_Term_NL;
	}

	public void setYTermNL(CMatrix value) {
		Y_Term_NL = value;
	}

	public double getYTerminalFreqMult() {
		return Y_Terminal_FreqMult;
	}

	public void setYTerminalFreqMult(double mult) {
		Y_Terminal_FreqMult = mult;
	}

	public double getThermalTimeConst() {
		return thermalTimeConst;
	}

	public void setThermalTimeConst(double timeConst) {
		thermalTimeConst = timeConst;
	}

	public double getNThermal() {
		return nThermal;
	}

	public void setNThermal(double value) {
		nThermal = value;
	}

	public double getMThermal() {
		return mThermal;
	}

	public void setMThermal(double value) {
		mThermal = value;
	}

	public boolean isXHLChanged() {
		return XHLChanged;
	}

	public void setXHLChanged(boolean changed) {
		XHLChanged = changed;
	}

	public boolean isSubstation() {
		return isSubstation;
	}

	public void setPPM_FloatFactor(double factor) {
		ppmFloatFactor = factor;
	}

	public void setPctImag(double pct) {
		pctImag = pct;
	}

	public void setXHL(double value) {
		XHL = value;
	}

	public void setXHT(double value) {
		XHT = value;
	}

	public void setXLT(double value) {
		XLT = value;
	}

	public void setNormMaxHKVA(double max) {
		normMaxHKVA = max;
	}

	public void setEmergMaxHKVA(double max) {
		emergMaxHKVA = max;
	}

	public void setFLRise(double rise) {
		FLRise = rise;
	}

	public void setHSRise(double rise) {
		HSRise = rise;
	}

	public void setPctLoadLoss(double pct) {
		this.pctLoadLoss = pct;
	}

	public void setPctNoLoadLoss(double pct) {
		this.pctNoLoadLoss = pct;
	}

}

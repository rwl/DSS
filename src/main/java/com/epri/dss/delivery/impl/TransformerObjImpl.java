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

	private int DeltaDirection;
	private double ppm_FloatFactor;  // parts per million winding float factor
	private double pctImag;

	protected int NumWindings;
	protected int MaxWindings;
	protected int[] TermRef;  // keeps track of terminal connections

	protected double XHL, XHT, XLT;  // per unit
	protected double Zbase;
	protected double[] XSC;     // per unit SC measurements
	protected double VABase;    // for impedances

	protected CMatrix ZB;
	protected CMatrix Y_1Volt;
	protected CMatrix Y_Term;
	protected CMatrix Y_1Volt_NL;   // no load Y's
	protected CMatrix Y_Term_NL;

	protected double Y_Terminal_Freqmult;

	protected double NormMaxHKVA;
	protected double EmergMaxHKVA;
	protected double ThermalTimeConst;  // hr
	protected double n_thermal;
	protected double m_thermal;  // exponents
	protected double FLrise;
	protected double HSrise;
	protected double pctLoadLoss;
	protected double pctNoLoadLoss;

	protected boolean XHLChanged;

	private int ActiveWinding;
	private boolean IsSubstation;
	private String SubstationName;
	private Winding[] Winding;
	private String XfmrBank;
	private String XfmrCode;

	public TransformerObjImpl(DSSClass ParClass, String TransfName) {
		super(ParClass);

		setName(TransfName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); //DSSObjType + XFMR; // override PDElement (kept in both actually)

		setNPhases(3);  // directly set conds and phases
		this.nConds = this.nPhases + 1;
		setNumWindings(2);  // must do this after setting number of phases
		this.ActiveWinding = 0;  // TODO Check zero based indexing

		setNTerms(this.NumWindings);  // force allocation of terminals and conductors

		this.XHL = 0.07;
		this.XHT = 0.35;
		this.XLT = 0.30;
		this.XHLChanged = true;  // set flag to for calc of XSC array from XHL, etc.

		this.DeltaDirection = 1;
		this.SubstationName = "";
		this.XfmrBank = "";
		this.XfmrCode = "";

		this.VABase           = this.Winding[0].getKVA() * 1000.0;
		this.ThermalTimeConst = 2.0;
		this.n_thermal        = 0.8;
		this.m_thermal        = 0.8;
		this.FLrise           = 65.0;
		this.HSrise           = 15.0;  // hot spot rise
		this.NormMaxHKVA      = 1.1 * this.Winding[1].getKVA();
		this.EmergMaxHKVA     = 1.5 * this.Winding[1].getKVA();
		this.pctLoadLoss      = 2.0 * this.Winding[1].getRpu() * 100.0;  // assume two windings
		this.ppm_FloatFactor  = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < this.NumWindings; i++)
			this.Winding[i].computeAntiFloatAdder(this.ppm_FloatFactor, this.VABase / this.nPhases);

		/* Default the no load properties to zero */
		this.pctNoLoadLoss    = 0.0;
		this.pctImag          = 0.0;

		/*this.BaseFrequency = 60.0; set in base class to circuit fundamental freq; do not reset here*/
		setFaultRate(0.007);
		this.IsSubstation = false;

		this.Y_Terminal_Freqmult = 0.0;

		this.YOrder = this.nTerms * this.nConds;
		initPropertyValues(0);
		recalcElementData();
	}

	public void setNumWindings(int N) {
		int i;
		int OldWdgSize;

		if (N > 1) {
			for (i = 0; i < NumWindings; i++)
				Winding[i] = null;  // free old winding objects

			OldWdgSize = (NumWindings - 1) * NumWindings / 2;
			NumWindings = N;
			MaxWindings = N;
			setNConds(nPhases + 1);
			setNTerms(NumWindings);

			Winding = (com.epri.dss.delivery.Winding[]) Utilities.resizeArray(Winding, MaxWindings);  // reallocate collector array
			for (i = 0; i < MaxWindings; i++)
				Winding[i] = new WindingImpl();

			// array of short circuit measurements between pairs of windings
			XSC = (double[]) Utilities.resizeArray(XSC, ((NumWindings - 1) * NumWindings / 2));
			for (i = OldWdgSize; i < ((NumWindings - 1) * NumWindings / 2); i++)  // TODO Check zero based indexing
				XSC[i] = 0.30;
			TermRef = (int[]) Utilities.resizeArray(TermRef, 2 * NumWindings * nPhases);

			/* Reallocate impedance matrices */
			ZB = null;
			Y_1Volt = null;
			Y_1Volt_NL = null;
			Y_Term = null;
			Y_Term_NL = null;

			ZB         = new CMatrixImpl(NumWindings - 1);
			Y_1Volt    = new CMatrixImpl(NumWindings);
			Y_1Volt_NL = new CMatrixImpl(NumWindings);
			Y_Term     = new CMatrixImpl(2 * NumWindings);
			Y_Term_NL  = new CMatrixImpl(2 * NumWindings);
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Invalid number of windings: " + String.valueOf(N) + " for transformer " + getName(), 111);
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
		if (Winding[0].getConnection() == Winding[1].getConnection()) {
			DeltaDirection = 1;
		} else {
			if (Winding[0].getKVLL() >= Winding[1].getKVLL()) {
				iHVolt = 1;
			} else {
				iHVolt = 2;
			}
			switch (Winding[iHVolt].getConnection()) {
			case 0:
				DeltaDirection = 1;
				break;
			case 1:
				DeltaDirection = -1;
				break;
			default:
				// ---old code --- DeltaDirection = Winding[1].Connection = 0? -1 : 1;
				break;
			}
		}

		setTermRef();  // re-establish termRef if num windings or connection changed

		for (i = 0; i < NumWindings; i++) {
			w = Winding[i];
			if (w.getNumTaps() > 0) {
				w.setTapIncrement( (w.getMaxTap() - w.getMinTap()) / w.getNumTaps() );
			} else {
				w.setTapIncrement(0.0);
			}
		}

		if (XHLChanged) {
			/* should only happen for 2- and 3-winding transformers */
			if (NumWindings <= 3)
				for (i = 0; i < (NumWindings * (NumWindings - 1) / 2); i++) {
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
		for (i = 0; i < NumWindings; i++) {
			w = Winding[i];  // get the actual turns voltage base for each winding
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
		VABase = Winding[0].getKVA() * 1000.0;

		for (i = 0; i < NumWindings; i++)
			Winding[i].computeAntiFloatAdder(ppm_FloatFactor, VABase / nPhases);

		/* Normal and emergency terminal current rating for UE check */
		VFactor = 1.0;  // ensure initialization
		switch (Winding[0].getConnection()) {
		case 0:
			VFactor = Winding[0].getVBase() * 0.001;  // wye
			break;
		case 1:
			switch (nPhases) {
			case 1:
				VFactor = Winding[0].getVBase() * 0.001;
				break;
			case 2:
				VFactor = Winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
				break;
			case 3:
				VFactor = Winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
				break;
			default:
				VFactor = Winding[0].getVBase() * 0.001 * 0.5 / Math.sin(Math.PI / nPhases);
				break;
			}
			break;
		}

		/* Divide per phase kVA by voltage to neutral */
		setNormAmps(NormMaxHKVA  / nPhases / VFactor);
		setEmergAmps(EmergMaxHKVA / nPhases / VFactor);

		calcY_Terminal(1.0);  // calc Y_Terminal at base frequency
	}

	/**
	 * Transformer structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter F) {
		int i;

		/* Write only properties that were explicitly set in the
		 * final order they were actually set.
		 */
		int iProp = getNextPropertySet(0);  // works on ActiveDSSObject   TODO Check zero based indexing
		while (iProp > 0) {
			/* Trap wdg= and write out array properties instead */
			switch (ParentClass.getRevPropertyIdxMap()[iProp]) {
			case 2:  // if wdg= was ever used write out arrays ...   TODO Check zero based indexing
				for (i = 11; i < 16; i++)
					F.printf(" %s=%s", ParentClass.getPropertyName()[i], getPropertyValue(i));
				for (i = 0; i < NumWindings; i++)
					F.printf(" wdg=%d %sR=%.7g", i, "%", Winding[i].getRpu() * 100.0);
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
				F.printf(" %s=%s", ParentClass.getPropertyName()[ParentClass.getRevPropertyIdxMap()[iProp]],
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
			for (j = 0; j < NumWindings; j++) {
				k += 1;
				TermRef[k] = (j - 1) * nConds + 1;
				k += 1;
				TermRef[k] =  j * nConds;
			}
			break;
		default:
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < NumWindings; j++) {
					k += 1;
					switch (Winding[i].getConnection()) {
					case 0:  // Wye
						TermRef[k] = (j - 1) * nConds + i;
						k += 1;
						TermRef[k] = j * nConds;
						break;
					/* **** WILL THIS WORK for 2-PHASE OPEN DELTA ???? Need to check this sometime */
					case 1:  // Delta
						TermRef[k] = (j - 1) * nConds + i;
						k += 1;
						TermRef[k] = (j - 1) * nConds + rotatePhases(i);  // connect to next phase in sequence
						break;
					}
				}
			}
			break;
		}
	}

	@Override
	public void calcYPrim() {
		double FreqMultiplier;

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
		FreqMultiplier = YPrimFreq / baseFrequency;
		// Check for rebuilding Y_Terminal; only rebuild if freq is different than last time
		if (FreqMultiplier != Y_Terminal_Freqmult)
			calcY_Terminal(FreqMultiplier);

		buildYPrimComponent(YPrimSeries, Y_Term);
		buildYPrimComponent(YPrimShunt,  Y_Term_NL);

		addNeutralToY(FreqMultiplier);

		/* Combine the two YPrim components into YPrim */
		YPrim.copyFrom(YPrimSeries);
		YPrim.addFrom(YPrimShunt);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, j;

		super.dumpProperties(F, Complete);

		/* Basic property dump */

		F.println("~ " + "NumWindings=" + NumWindings);
		F.println("~ " + "phases=" + nPhases);

		for (i = 0; i < NumWindings; i++) {
			Winding W = Winding[i];
			if (i == 0) {
				F.println("~ " + "wdg=" + i + " bus=" + getFirstBus());
			} else {
				F.println("~ " + "wdg=" + i + " bus=" + getNextBus());
			}

			switch (W.getConnection()) {
			case 0:
				F.println("~ conn=wye");
				break;
			case 1:
				F.println("~ conn=delta");
				break;
			}
			F.println("~ kv=" + W.getKVLL());
			F.println("~ kva=" + W.getKVA());
			F.println("~ tap=" + W.getPUTap());
			F.println("~ %r=" + (W.getRpu() * 100.0));
			F.println("~ rneut=" + W.getRNeut());
			F.println("~ xneut=" + W.getXNeut());
		}

		F.println("~ " + "xhl=" + XHL * 100.0);
		F.println("~ " + "xht=" + XHT * 100.0);
		F.println("~ " + "xlt=" + XLT * 100.0);
		F.print("~ Xscmatrix= \"");
		for (i = 0; i < (NumWindings - 1) * NumWindings / 2; i++)
			F.print(XSC[i] * 100.0 + " ");
		F.println("\"");
		F.println("~ " + "NormMAxHkVA=" + NormMaxHKVA);
		F.println("~ " + "EmergMAxHkVA=" + EmergMaxHKVA);
		F.println("~ " + "thermal=" + ThermalTimeConst);
		F.println("~ " + "n=" + n_thermal);
		F.println("~ " + "m=" + m_thermal);
		F.println("~ " + "flrise=" + FLrise);
		F.println("~ " + "hsrise=" + HSrise);
		F.println("~ " + "%loadloss=" + pctLoadLoss);
		F.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (i = 27; i < Transformer.NumPropsThisClass; i++)
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		for (i = Transformer.NumPropsThisClass; i < ParentClass.getNumProperties(); i++)
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete) {
			F.println();
			F.println("ZB: (inverted)");
			for (i = 0; i < NumWindings - 1; i++) {
				for (j = 0; j < i; j++)
					F.print(ZB.getElement(i, j).getReal() + " ");
				F.println();
			}
			for (i = 0; i < NumWindings - 1; i++) {
				for (j = 0; j < i; j++)
					F.print(ZB.getElement(i, j).getImaginary() + " ");
				F.println();
			}

			F.println();
			F.println("Y_OneVolt");
			for (i = 0; i < NumWindings; i++) {
				for (j = 0; j < i; j++)
					F.print(Y_1Volt.getElement(i, j).getReal() + " ");
					F.println();
			}
			for (i = 0; i < NumWindings; i++) {
				for (j = 0; j < i; j++)
					F.print(Y_1Volt.getElement(i, j).getImaginary() + " ");
					F.println();
			}

			F.println();
			F.println("Y_Terminal");
			for (i = 0; i < 2 * NumWindings; i++) {
				for (j = 0; j < i; j++)
					F.print(Y_Term.getElement(i, j).getReal() + " ");
					F.println();
			}
			for (i = 0; i < 2 * NumWindings; i++) {
				for (j = 0; j < i; j++)
					F.print(Y_Term.getElement(i, j).getImaginary() + " ");
					F.println();
			}
			F.println();
			F.print("TermRef= ");
			for (i = 0; i < 2 * NumWindings * nPhases; i++)
				F.print(TermRef[i] + " ");
			F.println();

		}
	}

	public void setPresentTap(int i, double Value) {
		double TempVal;

		if ((i >= 0) && (i < NumWindings)) {
			Winding W = Winding[i];
			/* Range Checking */
			TempVal = Value;
			if (TempVal < W.getMinTap()) {
				TempVal = W.getMinTap();
			} else if (TempVal > W.getMaxTap()) {
				TempVal = W.getMaxTap();
			}

			if (TempVal != W.getPUTap()) {  /* Only if there's been a change */
				W.setPUTap(TempVal);
				setYPrimInvalid(true);  // this property triggers setting systemYChanged=true
				recalcElementData();
			}
		}
	}

	public double getWdgResistance(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getRpu();
		} else {
			return 0.0;
		}
	}

	public double getWdgKVA(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getKVA();
		} else {
			return 0.0;
		}
	}

	public double getWdgRNeutral(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getRNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgXNeutral(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getXNeut();
		} else {
			return 0.0;
		}
	}

	public double getWdgYPPM(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getY_PPM();
		} else {
			return 0.0;
		}
	}

	public double getXsc(int i) {
		int imax = (NumWindings - 1) * NumWindings / 2;

		if ((i >= 0) && (i < imax)) {
			return XSC[i];
		} else {
			return 0.0;
		}
	}

	public int getWdgConnection(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getConnection();
		} else {
			return 0;
		}
	}

	public double getMinTap(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getMinTap();
		} else {
			return 0.0;
		}
	}

	public double getMaxTap(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getMaxTap();
		} else {
			return 0.0;
		}
	}

	public int getNumTaps(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getNumTaps();
		} else {
			return 0;
		}
	}

	public double getTapIncrement(int i) {
		if ((i >= 0) && (i < NumWindings)) {
			return Winding[i].getTapIncrement();
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
		int i, ii, k, NeutTerm;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			/* Return zero if winding number improperly specified */
			if ((iWind < 0) || (iWind >= NumWindings)) {
				for (i = 0; i < nConds; i++)
					VBuffer[i] = Complex.ZERO;
				return;
			}

			/* Load up VTemp - already allocated for all cktelements */
			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV()[nodeRef[i]];


			k = (iWind - 1) * nConds;  // Offset for winding   TODO Check zero based indexing
			NeutTerm = nPhases + k + 1;
			for (i = 0; i < nPhases; i++) {
				switch (Winding[iWind].getConnection()) {
				case 0:  // wye
					VBuffer[i] = VTerminal[i + k].subtract(VTerminal[NeutTerm]);
					break;
				case 1:  // delta
					ii = rotatePhases(i);  // get next phase in sequence
					VBuffer[i] = VTerminal[i + k].subtract(VTerminal[ii + k]);
					break;
				}
			}

		} catch (Exception e) {
			Globals.doSimpleMsg("Error filling voltage buffer in getWindingVoltages for circuit element: Transformer."+getName()+DSSGlobals.CRLF+
					"Probable Cause: Invalid definition of element."+DSSGlobals.CRLF+
					"System error message: "+e.getMessage(), 114);
		}
	}

	public double getBaseVoltage(int i) {
		if ((i < 0) || (i >= NumWindings)) {
			return Winding[0].getVBase();
		} else {
			return Winding[i].getVBase();
		}
	}

	@Override
	public void getLosses(double[] TotalLosses, double[] LoadLosses, double[] NoLoadLosses) {
		Complex totalLosses, loadLosses, noLoadLosses;

		/* Calculates losses in watts, vars */
		totalLosses = getLosses();  // Side effect: computes ITerminal

		/* Compute no load losses in YPrim_Shunt */
		Complex[] cTempIterminal = new Complex[YOrder];
		computeVTerminal();
		YPrimShunt.MVMult(cTempIterminal, VTerminal);
		/* No load losses are sum of all powers coming into YPrim_Shunt from each terminal */
		noLoadLosses = Complex.ZERO;
		for (int i = 0; i < YOrder; i++)
			noLoadLosses = noLoadLosses.add( VTerminal[i].multiply(cTempIterminal[i].conjugate()) );

		loadLosses = totalLosses.subtract(noLoadLosses);

		/* Handle pass by reference */
		TotalLosses[0] = totalLosses.getReal();
		TotalLosses[1] = totalLosses.getImaginary();
		LoadLosses[0] = loadLosses.getReal();
		LoadLosses[1] = loadLosses.getImaginary();
		NoLoadLosses[0] = noLoadLosses.getReal();
		NoLoadLosses[1] = noLoadLosses.getImaginary();

		cTempIterminal = null;

	}

	/**
	 * Gets the property for the active winding; set the active winding before calling.
	 */
	@Override
	public String getPropertyValue(int Index) {
		int i;
		String Result;

		if (((Index >= 11) && (Index <= 15)) || (Index == 19) || (Index == 36)) {
			Result = "[";
		} else {
			Result = "";
		}

		switch (Index) {
		case 0:
			Result = String.valueOf(nPhases);
			break;
		case 1:
			Result = String.valueOf(NumWindings);
			break;
		case 2:
			Result = String.valueOf(ActiveWinding);  // return active winding
			break;
		case 3:
			Result = getBus(ActiveWinding);    // return bus spec for active winding
			break;
		case 4:
			switch (Winding[ActiveWinding].getConnection()) {
			case 0:
				Result = "wye ";
				break;
			case 1:
				Result = "delta ";
				break;
			}
			break;
		case 5:
			Result = String.format("%.7g", Winding[ActiveWinding].getKVLL());
			break;
		case 6:
			Result = String.format("%.7g", Winding[ActiveWinding].getKVA());
			break;
		case 7:
			Result = String.format("%.7g", Winding[ActiveWinding].getPUTap());
			break;
		case 8:
			Result = String.format("%.7g", Winding[ActiveWinding].getRpu() * 100.0);  // %R
			break;
		case 9:
			Result = String.format("%.7g", Winding[ActiveWinding].getRNeut());
			break;
		case 10:
			Result = String.format("%.7g", Winding[ActiveWinding].getXNeut());
			break;

		case 11:
			for (i = 0; i < NumWindings; i++)
				Result = Result + getBus(i) + ", ";
			break;
		case 12:
			for (i = 0; i < NumWindings; i++)
				switch (Winding[i].getConnection()) {
				case 0:
					Result = Result + "wye, ";
					break;
				case 1:
					Result = Result + "delta, ";
					break;
				}
			break;
		case 13:
			for (i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getKVLL());
			break;
		case 14:
			for (i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getKVA());
			break;
		case 15:
			for (i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getPUTap());  // interpretAllTaps(Param);
			break;
		case 16:
			Result = String.format("%.7g", XHL * 100.0);
			break;
		case 17:
			Result = String.format("%.7g", XHT * 100.0);
			break;
		case 18:
			Result = String.format("%.7g", XLT * 100.0);
			break;
		case 19:
			for (i = 0; i < (NumWindings - 1) * NumWindings / 2; i++)
				Result = Result + String.format("%-g, ", XSC[i] * 100.0);// Parser.ParseAsVector(((NumWindings - 1)*NumWindings div 2), Xsc);
			break;
		case 25:
			Result = String.format("%.7g", pctLoadLoss);
			break;
		case 26:
			Result = String.format("%.7g", pctNoLoadLoss);
			break;
		case 30:
			Result = String.format("%.7g", Winding[ActiveWinding].getMaxTap());
			break;
		case 31:
			Result = String.format("%.7g", Winding[ActiveWinding].getMinTap());
			break;
		case 32:
			Result = String.format("%-d", Winding[ActiveWinding].getNumTaps());
			break;
		case 34:
			Result = String.format("%.7g", pctImag);
			break;
		case 35:
			Result = String.format("%.7g", ppm_FloatFactor / 1.0e-6);
			break;
		case 36:
			for (i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getRpu() * 100.0);
			break;

		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		// Overrides
		switch (Index = Transformer.NumPropsThisClass) {
		case 0:
			Result = String.format("%-.5g", getNormAmps());   // normAmps
			break;
		case 1:
			Result = String.format("%-.5g", getEmergAmps());  // emergAmps
			break;
		}

		if (((Index >= 11) && (Index <= 15)) || (Index == 19) || (Index == 36))
			Result = "]";

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "3";  // "phases";
		PropertyValue[1] = "2";  // "windings";
		// winding definition
		PropertyValue[2] = "1";  // "wdg";
		PropertyValue[3] = getBus(1);  // "bus";  // TODO Check zero based indexing
		PropertyValue[4] = "wye";  // "conn";
		PropertyValue[5] = "12.47";  // if 2 or 3-phase:  phase-phase else actual winding
		PropertyValue[6] = "1000";
		PropertyValue[7] = "1.0";
		PropertyValue[8] = "0.2";
		PropertyValue[9] = "-1";
		PropertyValue[10] = "0";

		// general data
		PropertyValue[11] = "";
		PropertyValue[12] = "";
		PropertyValue[13] = ""; // if 1-phase: actual winding rating; else phase-phase
		PropertyValue[14] = ""; // if 1-phase: actual winding rating; else phase-phase
		PropertyValue[15] = "";
		PropertyValue[16] = "7";
		PropertyValue[17] = "35";
		PropertyValue[18] = "30";
		PropertyValue[19] = "";  // x12 13 14... 23 24.. 34 ..
		PropertyValue[20] = "2";
		PropertyValue[21] = ".8";
		PropertyValue[22] = ".8";
		PropertyValue[23] = "65";
		PropertyValue[24] = "15";
		PropertyValue[25] = String.format("%.7g", pctLoadLoss);
		PropertyValue[26] = String.format("%.7g", pctNoLoadLoss);  // defaults to zero
		PropertyValue[27] = "";
		PropertyValue[28] = "";
		PropertyValue[29] = "n";  // =y/n
		PropertyValue[30] = "1.10";
		PropertyValue[31] = "0.90";
		PropertyValue[32] = "32";
		PropertyValue[33] = "";
		PropertyValue[34] = "0";
		PropertyValue[35] = "1";
		PropertyValue[36] = "";

		super.initPropertyValues(Transformer.NumPropsThisClass);

		// override some inherited properties
		PropertyValue[Transformer.NumPropsThisClass + 1] = "400";    // normAmps  // TODO Check zero based indexing
		PropertyValue[Transformer.NumPropsThisClass + 2] = "600";    // emergAmps
		PropertyValue[Transformer.NumPropsThisClass + 3] = "0.007";  // faultRate
		PropertyValue[Transformer.NumPropsThisClass + 4] = "100";    // pctPerm
		PropertyValue[Transformer.NumPropsThisClass + 5] = "36";     // hrsToRepair

		clearPropSeqArray();  // so the overrides don't show up on save
	}

	/**
	 * For Delta connections or Line-Line voltages.
	 */
	public int rotatePhases(int iPhs) {
		int Result = iPhs + DeltaDirection;  // TODO Check zero based indexing

		// make sure result is within limits
		if (nPhases > 2) {
			// assumes 2 phase delta is open delta
			if (Result > nPhases)
				Result = 1;
			if (Result < 1)
				Result = nPhases;
		} else if (Result < 1) {
			Result = 3;  // for 2-phase delta, next phase will be 3rd phase
		}

		return Result;
	}

	/**
	 * Converts default 3-phase transformer model into equivalent positive-sequence
	 * using scripting.
	 */
	@Override
	public void makePosSequence() {
		int iW, i;
		MutableInt N = new MutableInt();
		String S;
		int[] Nodes = new int[5];  // integer buffer
		boolean OnPhase1;

		DSSGlobals Globals = DSSGlobals.getInstance();

		/* First, determine if we can convert this one. */
		if ((nPhases == 1) || (nPhases == 2)) {  // disable if any terminal not connected to phase one
			for (iW = 0; iW < NumWindings; iW++) {
				OnPhase1 = false;
				/* Load up auxiliary parser */
				Globals.getAuxParser().setCmdString(getBus(iW));
				Globals.getAuxParser().getNextParam();
				S = Globals.getAuxParser().parseAsBusName(N, Nodes);  // TODO Check N gets set
				if (N.intValue() == 0)
					OnPhase1 = true;
				for (i = 0; i < N.intValue(); i++)
					if (Nodes[i] == 1)
						OnPhase1 = true;
				if (!OnPhase1) {
					setEnabled(false);  // we won't use this one
					return;
				}
			}
		}

		/* Construct transformer definition string */
		S = "Phases=1  Conns=(";
		for (i = 0; i < NumWindings; i++)
			S = S + "Wye ";
		S = S + ")  buses=(";

		for (i = 0; i < NumWindings; i++)
			S = S + getBus(i) + " ";
		S = S + ")  kVS=(";

		for (i = 0; i < NumWindings; i++) {
			Winding W = Winding[i];
			if ((nPhases > 1) || (W.getConnection() != 0)) {
				S = S + String.format(" %-.5g", W.getKVLL() / DSSGlobals.SQRT3);
			} else {
				S = S + String.format(" %-.5g", W.getKVLL());
			}
		}
		S = S + ")  kVAs=(";

		for (i = 0; i < NumWindings; i++) {
			Winding W = Winding[i];
			S = S + String.format(" %-.5g", W.getKVA() / nPhases);
		}
		S = S + ")";

		S = S + " NormHkVA="+String.format(" %-.5g %-.5g", NormMaxHKVA / nPhases, EmergMaxHKVA / nPhases);

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();
	}

	private void addNeutralToY(double FreqMultiplier) {
		int i, j;
		Complex Value = null;

		/* Account for neutral impedances */
		for (i = 0; i < NumWindings; i++) {
			Winding W = Winding[i];
			if (W.getConnection() == 0) {
				// handle wye, but ignore delta (and open wye)
				if (W.getRNeut() >= 0) {
					// <0 is flag for open neutral (Ignore)
					if ((W.getRNeut() == 0) && (W.getXNeut() == 0))
						// solidly grounded
						Value = new Complex(1000000, 0);
				} else {
					// 1 microohm resistor
					Value = new Complex(W.getRNeut(), W.getXNeut() * FreqMultiplier).invert();
				}
				j = i * nConds;
				YPrimSeries.addElement(j, j, Value);
			} else {
				// bump up neutral admittance a bit in case neutral is floating
				j = i * nConds;
				if (ppm_FloatFactor != 0.0) {
					YPrimSeries.setElement(j, j, YPrimSeries.getElement(j, j).add( new Complex(0.0, W.getY_PPM()) ));
					/* YPrim_Series.setElement(j, j, CmulReal_im(GetElement(j, j), ppm_FloatFactorPlusOne)); */
				}
			}
		}
	}

	private void buildYPrimComponent(CMatrix YPrim_Component, CMatrix Y_Terminal) {
		Complex Value;

		/* Now, put in Yprim matrix */
		/* Have to add every element of Y_terminal into Yprim somewhere */
		int NW2 = 2 * NumWindings;
		for (int i = 0; i < NW2; i++) {
			for (int j = 0; j < i; j++) {
				Value = Y_Terminal.getElement(i, j);
				// this value goes in Yprim nPhases times
				for (int k = 0; k < nPhases - 1; k++)
					YPrim_Component.addElemSym(TermRef[i + k * NW2], TermRef[j + k * NW2], Value);
			}
		}
	}

	public double getBasekVLL(int i) {
		return Winding[i].getKVLL();
	}

	private void calcY_Terminal(double FreqMult) {
		int i, j, k;
		Complex[] A, ctempArray1, ctempArray2;
		Complex cMinusOne;
		CMatrix AT;


		// construct ZBMatrix
		ZB.clear();
		Zbase = 1.0 / (VABase / nPhases);  // base ohms on 1.0 volt basis
		for (i = 0; i < NumWindings - 1; i++)
			/* convert pu to ohms on one volt base as we go... */
			ZB.setElement(i, i, new Complex((Winding[0].getRpu() + Winding[i + 1].getRNeut()), FreqMult * XSC[i]).multiply(Zbase));

		// off diagonals
		k = NumWindings;
		for (i = 0; i < NumWindings - 1; i++) {
			for (j = 0; j < NumWindings - 1; j++)
				ZB.setElemSym(i, j,
					ZB.getElement(i, i).add(ZB.getElement(j, j)).subtract(
						new Complex((Winding[i + 1].getRpu() + Winding[j + 1].getRpu()), FreqMult * XSC[k]).multiply(Zbase)).multiply(0.5) );
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
		ctempArray1 = new Complex[NumWindings * 2];
		ctempArray2 = new Complex[NumWindings * 2];


		A          = new Complex[NumWindings * 2];
		cMinusOne  = new Complex(-1.0, 0.0);
		AT         = new CMatrixImpl(NumWindings);
		for (i = 0; i < NumWindings - 1; i++)
			AT.setElement(i + 1, i, Complex.ONE);
		for (i = 0; i < NumWindings - 1; i++)
			AT.setElement(1, i, cMinusOne);
		ctempArray1[NumWindings] = Complex.ZERO;
		for (i = 0; i < NumWindings; i++) {
			if (i == 1) {
				for (k = 0; k < NumWindings - 1; k++)
					A[k] = cMinusOne;
			} else {
				for (k = 0; k < NumWindings - 1; k++)
					if (k == (i - 1)) {
						A[k] = Complex.ONE;
					} else {
						A[k] = Complex.ZERO;
					}
			}
			ZB.MVMult(ctempArray1, A);  /* Zb.invert * A */
			AT.MVMult(ctempArray2, ctempArray1);  /* AT * Result */
			for (j = 0; j < NumWindings; j++)
				Y_1Volt.setElement(j, i, ctempArray2[j]);
		}


		/* Add magnetizing reactance to 2nd winding, assuming it is closest to the core
		 * Add both resistive element representing core losses and a reactive element representing
		 * magnetizing current.
		 */
		Y_1Volt_NL.addElement(1, 1, new Complex((pctNoLoadLoss / 100.0 / Zbase), -pctImag / 100.0 / Zbase));

/* ******************************DEBUG****************************************************** */
//		if (false) {
//			F.println("Y_OneVolt ...");
//			dumpComplexMatrix(F, Y_OneVolt);
//		}
/* ***************************************************************************************** */
		// should have admittance of one phase of the transformer on a one-volt, wye-connected base

		// Now make into terminal admittance matrix and correct for actual voltage ratings
		// Y_Terminal = AT * Y_onevolt * A  where V_onevolt = A * V_terminal

		AT = null;

		Y_Term.clear();
		Y_Term_NL.clear();
		AT = new CMatrixImpl(NumWindings * 2);

		for (i = 0; i < NumWindings; i++)
			AT.setElement(2 * i - 1, i, new Complex(1.0 / (Winding[i].getVBase() * Winding[i].getPUTap()), 0.0));
		for (i = 0; i < NumWindings; i++)
			AT.setElement(2 * i,     i, new Complex(-1.0 / (Winding[i].getVBase() * Winding[i].getPUTap()), 0.0));
		for (i = 0; i < 2 * NumWindings; i++)
			ctempArray1[i] = Complex.ZERO;

		for (i = 0; i < 2 * NumWindings; i++) {
			for (k = 0; k < NumWindings; k++) {
				if (i == (2 * k - 1)) {
					A[k] = new Complex((1.0 / (Winding[k].getVBase() * Winding[k].getPUTap())), 0.0);
				} else {
					if (i == 2 * k) {
						A[k] = new Complex((-1.0 / (Winding[k].getVBase() * Winding[k].getPUTap())), 0.0);
					} else {
						A[k] = Complex.ZERO;
					}
				}
			}

			/* Main transformer part */
			Y_1Volt.MVMult(ctempArray1, A);
			AT.MVMult(ctempArray2, ctempArray1);  /* AT * Result */
			for (j = 0; j < 2 * NumWindings; j++)
				Y_Term.setElement(j, i, ctempArray2[j]);
			/* No load part */
			Y_1Volt_NL.MVMult(ctempArray1, A);
			AT.MVMult(ctempArray2, ctempArray1);  /* AT * Result */
			for (j = 0; j < 2 * NumWindings; j++)
				Y_Term_NL.setElement(j, i, ctempArray2[j]);
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
		if (ppm_FloatFactor != 0.0) {
			for (i = 0; i < NumWindings; i++) {
				j = 2 * i - 1;
				Y_Term.setElement(j, j, Y_Term.getElement(j, j).add( new Complex(0.0, Winding[i].getY_PPM()) ));
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

		AT = null;
		A = null;
		ctempArray1 = null;
		ctempArray2 = null;

		Y_Terminal_Freqmult = FreqMult;
	}

	// FIXME Private method in OpenDSS
	public void fetchXfmrCode(String Code) {
		XfmrCodeObj Obj;
		int i;

		DSSGlobals Globals = DSSGlobals.getInstance();

		if (TransformerImpl.getXfmrCodeClass() == null)
			TransformerImpl.setXfmrCodeClass((com.epri.dss.general.XfmrCode) Globals.getDSSClassList().get(Globals.getClassNames().find("xfmrcode")));

		if (TransformerImpl.getXfmrCodeClass().setActive(Code)) {
			Obj = (XfmrCodeObj) TransformerImpl.getXfmrCodeClass().getActiveObj();
			XfmrCode = Code.toLowerCase();
			// set sizes and copy parameters
			setNPhases(Obj.getNPhases());
			setNumWindings(Obj.getNumWindings());
			setNConds(nPhases + 1);  // forces reallocation of terminals and conductors
			for (i = 0; i < NumWindings; i++) {
				Winding W = Winding[i];
				W.setConnection(Obj.getWinding()[i].getConnection());
				W.setKVLL(Obj.getWinding()[i].getKVLL());
				W.setVBase(Obj.getWinding()[i].getVBase());
				W.setKVA(Obj.getWinding()[i].getKVA());
				W.setPUTap(Obj.getWinding()[i].getPUTap());
				W.setRpu(Obj.getWinding()[i].getRpu());
				W.setRNeut(Obj.getWinding()[i].getRNeut());
				W.setXNeut(Obj.getWinding()[i].getXNeut());
				W.setTapIncrement(Obj.getWinding()[i].getTapIncrement());
				W.setMinTap(Obj.getWinding()[i].getMinTap());
				W.setMaxTap(Obj.getWinding()[i].getMaxTap());
				W.setNumTaps(Obj.getWinding()[i].getNumTaps());
			}

			setTermRef();
			XHL = Obj.getXHL();
			XHT = Obj.getXHT();
			XLT = Obj.getXLT();
			for (i = 0; i < (NumWindings * (NumWindings - 1) / 2); i++)
				XSC[i] = Obj.getXSC()[i];
			ThermalTimeConst = Obj.getThermalTimeConst();
			n_thermal        = Obj.getN_thermal();
			m_thermal        = Obj.getM_thermal();
			FLrise           = Obj.getLrise();
			HSrise           = Obj.getHSrise();
			pctLoadLoss      = Obj.getPctLoadLoss();
			pctNoLoadLoss    = Obj.getPctNoLoadLoss();
			setNormMaxHKVA(Obj.getNormMaxHKVA());
			setEmergMaxHKVA(Obj.getEmergMaxHKVA());
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);
			Y_Terminal_Freqmult = 0.0;

			recalcElementData();
		} else {
			Globals.doSimpleMsg("Xfmr Code:" + Code + " not found.", 180);
		}
	}

	public double getPresentTap(int i) {
		return 0.0;
	}

	/* CIM accessors */

	public int getNumWindings() {
		return NumWindings;
	}

	public int getActiveWinding() {
		return ActiveWinding;
	}

	public void setActiveWinding(int activeWinding) {
		ActiveWinding = activeWinding;
	}

	public void setSubstation(boolean isSubstation) {
		IsSubstation = isSubstation;
	}

	public String getSubstationName() {
		return SubstationName;
	}

	public void setSubstationName(String substationName) {
		SubstationName = substationName;
	}

	public Winding[] getWinding() {
		return Winding;
	}

	public void setWinding(Winding[] winding) {
		Winding = winding;
	}

	public String getXfmrBank() {
		return XfmrBank;
	}

	public void setXfmrBank(String xfmrBank) {
		XfmrBank = xfmrBank;
	}

	public String getXfmrCode() {
		return XfmrCode;
	}

	public void setXfmrCode(String xfmrCode) {
		XfmrCode = xfmrCode;
	}

	public double getPPM_FloatFactor() {
		return ppm_FloatFactor;
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
		return NormMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return EmergMaxHKVA;
	}

	public double getThTau() {
		return ThermalTimeConst;
	}

	public double getThN() {
		return n_thermal;
	}

	public double getThM() {
		return m_thermal;
	}

	public double getFLRise() {
		return FLrise;
	}

	public double getHSRise() {
		return HSrise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	// FIXME Private memebers in OpenDSS

	public int getDeltaDirection() {
		return DeltaDirection;
	}

	public void setDeltaDirection(int deltaDirection) {
		DeltaDirection = deltaDirection;
	}

	public int getMaxWindings() {
		return MaxWindings;
	}

	public void setMaxWindings(int maxWindings) {
		MaxWindings = maxWindings;
	}

	public int[] getTermRef() {
		return TermRef;
	}

	public void setTermRef(int[] termRef) {
		TermRef = termRef;
	}

	public double getZBase() {
		return Zbase;
	}

	public void setZBase(double zbase) {
		Zbase = zbase;
	}

	public double[] getXSC() {
		return XSC;
	}

	public void setXSC(double[] xSC) {
		XSC = xSC;
	}

	public double getVABase() {
		return VABase;
	}

	public void setVABase(double vABase) {
		VABase = vABase;
	}

	public CMatrix getZB() {
		return ZB;
	}

	public void setZB(CMatrix zB) {
		ZB = zB;
	}

	public CMatrix getY_1Volt() {
		return Y_1Volt;
	}

	public void setY_1Volt(CMatrix y_1Volt) {
		Y_1Volt = y_1Volt;
	}

	public CMatrix getY_Term() {
		return Y_Term;
	}

	public void setY_Term(CMatrix y_Term) {
		Y_Term = y_Term;
	}

	public CMatrix getY_1Volt_NL() {
		return Y_1Volt_NL;
	}

	public void setY1VoltNL(CMatrix y_1Volt_NL) {
		Y_1Volt_NL = y_1Volt_NL;
	}

	public CMatrix getYTermNL() {
		return Y_Term_NL;
	}

	public void setYTermNL(CMatrix y_Term_NL) {
		Y_Term_NL = y_Term_NL;
	}

	public double getYTerminalFreqMult() {
		return Y_Terminal_Freqmult;
	}

	public void setYTerminalFreqMult(double y_Terminal_Freqmult) {
		Y_Terminal_Freqmult = y_Terminal_Freqmult;
	}

	public double getThermalTimeConst() {
		return ThermalTimeConst;
	}

	public void setThermalTimeConst(double thermalTimeConst) {
		ThermalTimeConst = thermalTimeConst;
	}

	public double getNThermal() {
		return n_thermal;
	}

	public void setNThermal(double n_thermal) {
		this.n_thermal = n_thermal;
	}

	public double getMThermal() {
		return m_thermal;
	}

	public void setMThermal(double m_thermal) {
		this.m_thermal = m_thermal;
	}

	public boolean isXHLChanged() {
		return XHLChanged;
	}

	public void setXHLChanged(boolean xHLChanged) {
		XHLChanged = xHLChanged;
	}

	public boolean isSubstation() {
		return IsSubstation;
	}

	public void setPPM_FloatFactor(double ppm_FloatFactor) {
		this.ppm_FloatFactor = ppm_FloatFactor;
	}

	public void setPctImag(double pctImag) {
		this.pctImag = pctImag;
	}

	public void setXHL(double xHL) {
		XHL = xHL;
	}

	public void setXHT(double xHT) {
		XHT = xHT;
	}

	public void setXLT(double xLT) {
		XLT = xLT;
	}

	public void setNormMaxHKVA(double normMaxHKVA) {
		NormMaxHKVA = normMaxHKVA;
	}

	public void setEmergMaxHKVA(double emergMaxHKVA) {
		EmergMaxHKVA = emergMaxHKVA;
	}

	public void setFLRise(double fLrise) {
		FLrise = fLrise;
	}

	public void setHSRise(double hSrise) {
		HSrise = hSrise;
	}

	public void setPctLoadLoss(double pctLoadLoss) {
		this.pctLoadLoss = pctLoadLoss;
	}

	public void setPctNoLoadLoss(double pctNoLoadLoss) {
		this.pctNoLoadLoss = pctNoLoadLoss;
	}

}

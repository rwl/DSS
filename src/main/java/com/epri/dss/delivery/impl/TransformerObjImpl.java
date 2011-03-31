package com.epri.dss.delivery.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;
import com.epri.dss.shared.CMatrix;

public class TransformerObjImpl extends PDElementImpl implements TransformerObj {
	
	private int DeltaDirection;
	private double ppm_FloatFactor; //  parts per million winding float factor
	private double pctImag;
	
	protected int NumWindings;
	protected int MaxWindings;
	protected int[] TermRef;  // keeps track of terminal connections

	protected double XHL, XHT, XLT;  // per unit
	protected double Zbase;
	protected double[] XSC;     // per unit SC measurements
	protected double VABase;    // FOR impedances

	protected CMatrix ZB;
	protected CMatrix Y_1Volt;
	protected CMatrix Y_Term;
	protected CMatrix Y_1Volt_NL;   // No Load Y's
	protected CMatrix Y_Term_NL;

	protected double Y_Terminal_Freqmult;

	protected double NormMaxHKVA;
	protected double EmergMaxHKVA;
	protected double ThermalTimeConst;  // hr
	protected double n_thermal;
	protected double m_thermal;  // Exponents
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
		this.DSSObjType = ParClass.getDSSClassType(); //DSSObjType + XFMR; // override PDElement   (kept in both actually)

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds = this.nPhases + 1;
		setNumWindings(2);  // must do this after setting number of phases
		this.ActiveWinding = 0;  // TODO Check zero based indexing

		this.nTerms = this.NumWindings;  // Force allocation of terminals and conductors

		this.XHL = 0.07;
		this.XHT = 0.35;
		this.XLT = 0.30;
		this.XHLChanged = true;  // Set flag to for calc of XSC array from XHL, etc.

		this.DeltaDirection = 1;
		this.SubstationName = "";
		this.XfmrBank = "";
		this.XfmrCode = "";

		this.VABase           = this.Winding[0].getKva() * 1000.0;
		this.ThermalTimeConst = 2.0;
		this.n_thermal        = 0.8;
		this.m_thermal        = 0.8;
		this.FLrise           = 65.0;
		this.HSrise           = 15.0;  // Hot spot rise
		this.NormMaxHKVA      = 1.1 * this.Winding[1].getKva();
		this.EmergMaxHKVA     = 1.5 * this.Winding[1].getKva();
		this.pctLoadLoss      = 2.0 * this.Winding[1].getRpu() * 100.0; //  assume two windings
		this.ppm_FloatFactor  = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < this.NumWindings; i++) 
			this.Winding[i].computeAntiFloatAdder(this.ppm_FloatFactor, this.VABase / this.nPhases);

		/* Default the no load properties to zero */
		this.pctNoLoadLoss    = 0.0;
		this.pctImag          = 0.0;

		/*this.BaseFrequency = 60.0;   set in base class to circuit fundamental freq; Do not reset here*/
		setFaultRate(0.007);
		this.IsSubstation = false;

		this.Y_Terminal_Freqmult = 0.0;

		this.Yorder = this.nTerms * this.nConds;
		initPropertyValues(0);
		recalcElementData();
	}
	
	public void setNumWindings(int N) {
		int i;
		int OldWdgSize;

		if (N > 1) {
			for (i = 0; i < NumWindings; i++) 
				Winding[i] = null;  // Free old winding objects
			
			OldWdgSize = (NumWindings - 1) * NumWindings / 2;
			NumWindings = N;
			MaxWindings = N;
			nConds = nPhases + 1;
			nTerms = NumWindings;
			
			Winding = (com.epri.dss.delivery.Winding[]) Utilities.resizeArray(Winding, MaxWindings);  // Reallocate collector array
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
			DSSGlobals.getInstance().doSimpleMsg("Invalid number of windings: " + String.valueOf(N) + " for Transformer " + getName(), 111);
		}
	}
	
	@Override
	public void recalcElementData() {
		int i, iHVolt;
		double VFactor;
		PrintWriter F;
		Winding w;

		// Determine Delta Direction
		// If high voltage is delta, delta leads y
		// If high voltage is wye, delta lags wye
		if (Winding[0].getConnection() == Winding[1].getConnection()) {
			DeltaDirection = 1;
		} else {
			if (Winding[0].getKvll() >= Winding[1].getKvll()) {
				iHVolt = 1;
			} else {
				iHVolt = 2;
			}
			switch (Winding[iHVolt].getConnection()) {
			case 0:
				DeltaDirection = 1;
			case 1:
				DeltaDirection = -1;
			default:
				// ---old code --- If Winding^[2].Connection = 0 Then DeltaDirection := -1 Else DeltaDirection := 1;
			}
		}
		
		setTermRef();  // Re-establish TermRef if num windings or connection changed

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
					case 1:
						XSC[1] = XHT;
					case 3:
						XSC[2] = XLT;
					}
				}
			XHLChanged = false;
		}

		// Set winding voltage bases (in volts)
		for (i = 0; i < NumWindings; i++) {
			w = Winding[i];  // Get the actual turns voltage base for each winding
			switch (w.getConnection()) {
			case 0:
				switch (nPhases) {  // Wye
				case 2:
					w.setVBase(w.getKvll() * DSSGlobals.InvSQRT3x1000);   // assume 3-phase for 2-phase designation
				case 3:
					w.setVBase(w.getKvll() * DSSGlobals.InvSQRT3x1000);
				default:
					w.setVBase(w.getKvll() * 1000.0);
				}
			case 1:
				w.setVBase(w.getKvll() * 1000.0);     // delta
			}
		}

		/* Base rating of Winding 1 */
		VABase = Winding[0].getKva() * 1000.0;

		for (i = 0; i < NumWindings; i++) 
			Winding[i].computeAntiFloatAdder(ppm_FloatFactor, VABase / nPhases);

		/* Normal and Emergency terminal current Rating for UE check */
		VFactor = 1.0;  // ensure initialization
		switch (Winding[0].getConnection()) {
		case 0:
			VFactor = Winding[0].getVBase() * 0.001;  // wye
		case 1:
			switch (nPhases) {
			case 1:
				VFactor = Winding[0].getVBase() * 0.001;
			case 2:
				VFactor = Winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
			case 3:
				VFactor = Winding[0].getVBase() * 0.001 / DSSGlobals.SQRT3;
			default:
				VFactor = Winding[0].getVBase() * 0.001 * 0.5 / Math.sin(Math.PI / nPhases);
			}
		}

		/* Divide per phase kva by voltage to neutral */
		setNormAmps(NormMaxHKVA  / nPhases / VFactor);
		setEmergAmps(EmergMaxHKVA / nPhases / VFactor);

		calcY_Terminal(1.0);  // Calc Y_Terminal at base frequency	
	}
	
	public double getPresentTap(int i) {
		return 0.0;
	}
	
	public void setPresentTap(int i, double Value) {
		
	}
	
	public double getMinTap(int i) {
		return 0.0;
	}
	
	public double getMaxTap(int i) {
		return 0.0;
	}
	
	public double getTapIncrement(int i) {
		return 0.0;
	}
	
	public double getBaseVoltage(int i) {
		return 0.0;
	}
	
	public double getBasekVLL(int i) {
		return 0.0;
	}
	
	/* CIM accessors */
	
	public int getNumWindings() {
		return NumWindings;
	}

	public int getNumTaps(int i) {
		return 0;
	}
	
	public double getWdgResistance(int i) {
		return 0.0;
	}
	
	public int getWdgConnection(int i) {
		return 0;
	}
	
	public double getWdgKVA(int i) {
		return 0.0;
	}
	
	public double getXsc(int i) {
		return 0.0;
	}
	
	public double getWdgRneutral(int i) {
		return 0.0;
	}
	
	public double getWdgXneutral(int i) {
		return 0.0;
	}
	
	public double getWdgYPPM(int i) {
		return 0.0;
	}
	
	private double calcY_Terminal(double FreqMult) {
		return 0.0;
	}
	
	private void buildYPrimComponent(CMatrix YPrim_Component,
			CMatrix Y_Terminal) {
		
	}
	
	private void addNeutralToY(double FreqMultiplier) {
		
	}
	
	private void fetchXfmrCode(String Code) {
		
	}
	
	protected void setTermRef() {
		
	}

	public int getActiveWinding() {
		return ActiveWinding;
	}

	public void setActiveWinding(int activeWinding) {
		ActiveWinding = activeWinding;
	}

	public boolean isSubstation() {
		return IsSubstation;
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
	
	public double getPpm_FloatFactor() {
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

	public double getFLrise() {
		return FLrise;
	}

	public double getHSrise() {
		return HSrise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {
		
	}
	
	public int rotatePhases(int iPhs) {
		return 0;
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public void saveWrite(PrintStream F) {
		
	}
	
	public void getWindingVoltages(int iWind, Complex[] VBuffer) {
		
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

	public double getZbase() {
		return Zbase;
	}

	public void setZbase(double zbase) {
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

	public void setY_1Volt_NL(CMatrix y_1Volt_NL) {
		Y_1Volt_NL = y_1Volt_NL;
	}

	public CMatrix getY_Term_NL() {
		return Y_Term_NL;
	}

	public void setY_Term_NL(CMatrix y_Term_NL) {
		Y_Term_NL = y_Term_NL;
	}

	public double getY_Terminal_Freqmult() {
		return Y_Terminal_Freqmult;
	}

	public void setY_Terminal_Freqmult(double y_Terminal_Freqmult) {
		Y_Terminal_Freqmult = y_Terminal_Freqmult;
	}

	public double getThermalTimeConst() {
		return ThermalTimeConst;
	}

	public void setThermalTimeConst(double thermalTimeConst) {
		ThermalTimeConst = thermalTimeConst;
	}

	public double getN_thermal() {
		return n_thermal;
	}

	public void setN_thermal(double n_thermal) {
		this.n_thermal = n_thermal;
	}

	public double getM_thermal() {
		return m_thermal;
	}

	public void setM_thermal(double m_thermal) {
		this.m_thermal = m_thermal;
	}

	public boolean isXHLChanged() {
		return XHLChanged;
	}

	public void setXHLChanged(boolean xHLChanged) {
		XHLChanged = xHLChanged;
	}

	public boolean isIsSubstation() {
		return IsSubstation;
	}

	public void setIsSubstation(boolean isSubstation) {
		IsSubstation = isSubstation;
	}

	public void setPpm_FloatFactor(double ppm_FloatFactor) {
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

	public void setFLrise(double fLrise) {
		FLrise = fLrise;
	}

	public void setHSrise(double hSrise) {
		HSrise = hSrise;
	}

	public void setPctLoadLoss(double pctLoadLoss) {
		this.pctLoadLoss = pctLoadLoss;
	}

	public void setPctNoLoadLoss(double pctNoLoadLoss) {
		this.pctNoLoadLoss = pctNoLoadLoss;
	}

}

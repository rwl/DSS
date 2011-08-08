package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.XfmrCode;
import com.epri.dss.general.XfmrCodeObj;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;
import com.epri.dss.delivery.impl.TransformerImpl;
import com.epri.dss.delivery.impl.WindingImpl;

public class XfmrCodeObjImpl extends DSSObjectImpl implements XfmrCodeObj {

	private int NPhases;
	private int ActiveWinding;
	private int NumWindings;
	private int MaxWindings;
	private double XHL, XHT, XLT;  // per unit
	private double[] XSC;     // per unit SC measurements
	private double VABase;    // FOR impedances
	private double NormMaxHKVA;
	private double EmergMaxHKVA;
	private double ThermalTimeConst;  // hr
	private double n_thermal;
	private double m_thermal;  // Exponents
	private double Lrise;
	private double HSrise;
	private double pctLoadLoss;
	private double pctNoLoadLoss;
	private double ppm_FloatFactor; //  parts per million winding float factor
	private double pctImag;
	private Winding[] Winding;

	public XfmrCodeObjImpl(DSSClass ParClass, String XfmrCodeName) {
		super(ParClass);
		setName(XfmrCodeName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		// default values and sizes
		this.NPhases       = 3;
		this.NumWindings   = 2;
		this.MaxWindings   = 2;
		this.ActiveWinding = 1;  // TODO Check zero based indexing
		this.Winding = new Winding[MaxWindings];
		for (int i = 0; i < MaxWindings; i++)
			Winding[i] = new WindingImpl();
		XHL = 0.07;
		XHT = 0.35;
		XLT = 0.30;
		XSC = new double[(NumWindings - 1) * NumWindings / 2];
		VABase           = Winding[0].getKva() * 1000.0;
		ThermalTimeConst = 2.0;
		n_thermal        = 0.8;
		m_thermal        = 0.8;
		Lrise            = 65.0;
		HSrise           = 15.0;  // Hot spot rise
		NormMaxHKVA      = 1.1 * Winding[0].getKva();
		EmergMaxHKVA     = 1.5 * Winding[0].getKva();
		pctLoadLoss      = 2.0 * Winding[0].getRpu() * 100.0; //  assume two windings
		ppm_FloatFactor  = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < NumWindings; i++)
			Winding[i].computeAntiFloatAdder(ppm_FloatFactor, VABase / NPhases);
		pctNoLoadLoss = 0.0;
		pctImag = 0.0;

		initPropertyValues(0);
	}

	public void setNumWindings(int N) {
		if (N > 1) {
			for (int i = 0; i < NumWindings; i++)
				Winding[i] = null;  // Free old winding objects
			int OldWdgSize = (NumWindings - 1) * NumWindings / 2;
			NumWindings = N;
			MaxWindings = N;
			Winding = (com.epri.dss.delivery.Winding[]) Utilities.resizeArray(Winding, MaxWindings);  // Reallocate collector array
			for (int i = 0; i < MaxWindings; i++)
				Winding[i] = new WindingImpl();
			XSC = (double[]) Utilities.resizeArray(XSC, ((NumWindings - 1) * NumWindings / 2));
			for (int i = OldWdgSize + 1; i < (NumWindings-1) * NumWindings / 2; i++)
				XSC[i] = 0.30;   // default to something
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Invalid number of windings: " + String.valueOf(N) + " for Transformer " +
					TransformerImpl.getActiveTransfObj().getName(), 111);
		}
	}

	public void pullFromTransformer(TransformerObj obj) {
		int i;

		setNumWindings(obj.getNumWindings());
		NPhases = obj.getNPhases();
		XHL = obj.getXHL();
		XHT = obj.getXHT();
		XLT = obj.getXLT();
		VABase = obj.getBaseVA();
		NormMaxHKVA = obj.getNormMaxHKVA();
		EmergMaxHKVA = obj.getEmergMaxHKVA();
		ThermalTimeConst = obj.getThTau();
		n_thermal = obj.getThN();
		m_thermal = obj.getThM();
		Lrise = obj.getFLrise();
		HSrise = obj.getHSrise();
		pctLoadLoss = obj.getPctLoadLoss();
		pctNoLoadLoss = obj.getPctNoLoadLoss();
		ppm_FloatFactor = obj.getPpm_FloatFactor();
		pctImag = obj.getPctImag();
		for (i = 0; i < (NumWindings - 1) * NumWindings / 2; i++)
			XSC[i] = obj.getXsc(i);
		for (i = 0; i < NumWindings; i++) {
			Winding[i].setConnection(obj.getWdgConnection(i));
			Winding[i].setKvll(obj.getBasekVLL(i));
			Winding[i].setVBase(obj.getBaseVoltage(i));
			Winding[i].setKva(obj.getWdgKVA(i));
			Winding[i].setPuTap(obj.getPresentTap(i));
			Winding[i].setRpu(obj.getWdgResistance(i));
			Winding[i].setRneut(obj.getWdgRneutral(i));
			Winding[i].setXneut(obj.getWdgXneutral(i));
			Winding[i].setY_PPM(obj.getWdgYPPM(i));
			Winding[i].setTapIncrement(obj.getTapIncrement(i));
			Winding[i].setMinTap(obj.getMinTap(i));
			Winding[i].setMaxTap(obj.getMaxTap(i));
			Winding[i].setNumTaps(obj.getNumTaps(i));
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		/* Basic Property Dump */

		F.println("~ " + "NumWindings=" + NumWindings);
		F.println("~ " + "phases=" + NPhases);

		for (int i = 0; i < NumWindings; i++) {
			Winding wdg = Winding[i];
			if (i == 0) {  // TODO Check zero based indexing
				F.println("~ " + "Wdg=" + i);
			} else {
				F.println("~ " + "Wdg=" + i);
			}
			switch (wdg.getConnection()) {
			case 0:
				F.println("~ conn=wye");
				break;
			case 1:
				F.println("~ conn=delta");
				break;
			}
			F.println("~ kv=" + wdg.getKvll());
			F.println("~ kva=" + wdg.getKva());
			F.println("~ tap=" + wdg.getPuTap());
			F.println("~ %r=" + wdg.getRpu() * 100.0);
			F.println("~ rneut=" + wdg.getRneut());
			F.println("~ xneut=" + wdg.getXneut());
		}

		F.println("~ " + "xhl=" + XHL * 100.0);
		F.println("~ " + "xht=" + XHT * 100.0);
		F.println("~ " + "xlt=" + XLT * 100.0);
		F.print("~ Xscmatrix= \"");
		for (int i = 0; i < (NumWindings - 1) * NumWindings / 2; i++)
			F.print(XSC[i] * 100.0 + " ");
		F.println("\"");
		F.println("~ " + "NormMAxHkVA=" + NormMaxHKVA);
		F.println("~ " + "EmergMAxHkVA=" + EmergMaxHKVA);
		F.println("~ " + "thermal=" + ThermalTimeConst);
		F.println("~ " + "n=" + n_thermal);
		F.println("~ " + "m=" + m_thermal);
		F.println("~ " + "flrise=" + Lrise);
		F.println("~ " + "hsrise=" + HSrise);
		F.println("~ " + "%loadloss=" + pctLoadLoss);
		F.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (int i = 27; i < XfmrCode.NumPropsThisClass; i++)  // TODO Check zero based indexing
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		for (int i = XfmrCode.NumPropsThisClass + 1; i < ParentClass.getNumProperties(); i++)  // TODO Check zero based indexing
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
	}

	/**
	 * Gets the property for the active winding.
	 * Set the active winding before calling.
	 */
	@Override
	public String getPropertyValue(int Index) {
		String Result;
		switch (Index) {
		case 10:
			Result = "[";
			break;
		case 11:
			Result = "[";
			break;
		case 12:
			Result = "[";
			break;
		case 13:
			Result = "[";
			break;
		case 17:
			Result = "[";
			break;
		case 32:
			Result = "[";
			break;
		default:
			Result = "";
			break;
		}

		switch (Index) {
		case 3:
			Result = String.valueOf(ActiveWinding);  // return active winding
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
			Result = String.format("%.7g", Winding[ActiveWinding].getKvll());
			break;
		case 6:
			Result = String.format("%.7g", Winding[ActiveWinding].getKva());
			break;
		case 7:
			Result = String.format("%.7g", Winding[ActiveWinding].getPuTap());
			break;
		case 8:
			Result = String.format("%.7g", Winding[ActiveWinding].getRpu() * 100.0);   // %R
			break;
		case 9:
			Result = String.format("%.7g", Winding[ActiveWinding].getRneut());
			break;
		case 10:
			Result = String.format("%.7g", Winding[ActiveWinding].getXneut());
			break;
		case 11:
			for (int i = 0; i < NumWindings; i++)
				switch (Winding[i].getConnection()) {
				case 0:
					Result = Result + "wye, ";
					break;
				case 1:
					Result = Result + "delta, ";
					break;
				}
			break;
		case 12:
			for (int i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getKvll());
			break;
		case 13:
			for (int i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getKva());
			break;
		case 14:
			for (int i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getPuTap());
			break;
		case 18:
			for (int i = 0; i < (NumWindings - 1) * NumWindings / 2; i++)
				Result = Result + String.format("%-g, ", XSC[i] * 100.0);
		case 24:
			Result = String.format("%.7g", pctLoadLoss);
			break;
		case 25:
			Result = String.format("%.7g", pctNoLoadLoss);
			break;
		case 28:
			Result = String.format("%.7g", Winding[ActiveWinding].getMaxTap());
			break;
		case 29:
			Result = String.format("%.7g", Winding[ActiveWinding].getMinTap());
			break;
		case 30:
			Result = String.format("%-d", Winding[ActiveWinding].getNumTaps());
			break;
		case 33:
			for (int i = 0; i < NumWindings; i++)
				Result = Result + String.format("%.7g, ", Winding[i].getRpu() * 100.0);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}


		switch (Index) {
		case 10:
			Result = "]";
			break;
		case 11:
			Result = "]";
			break;
		case 12:
			Result = "]";
			break;
		case 13:
			Result = "]";
			break;
		case 17:
			Result = "]";
			break;
		case 32:
			Result = "]";
			break;
		default:
			Result = "";
			break;
		}

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "3"; //"phases";
		PropertyValue[1] = "2"; //"windings";
		PropertyValue[2] = "1"; //"wdg";
		PropertyValue[3] = "wye"; // "conn";
		PropertyValue[4] = "12.47"; // IF 2or 3-phase:  phase-phase    ELSE actual winding
		PropertyValue[5] = "1000";
		PropertyValue[6] = "1.0";
		PropertyValue[7] = "0.2";
		PropertyValue[8] = "-1";
		PropertyValue[9] = "0";
		PropertyValue[10] = "";
		PropertyValue[11] = ""; // IF 1-phase: actual winding rating; ELSE phase-phase
		PropertyValue[12] = ""; // IF 1-phase: actual winding rating; ELSE phase-phase
		PropertyValue[13] = "";
		PropertyValue[14] = "7";
		PropertyValue[15] = "35";
		PropertyValue[16] = "30";
		PropertyValue[17] = "";  // x12 13 14... 23 24.. 34 ..
		PropertyValue[18] = "2";
		PropertyValue[19] = ".8";
		PropertyValue[20] = ".8";
		PropertyValue[21] = "65";
		PropertyValue[22] = "15";
		PropertyValue[23] = "0";
		PropertyValue[24] = "0";
		PropertyValue[25] = "";
		PropertyValue[26] = "";
		PropertyValue[27] = "1.10";
		PropertyValue[28] = "0.90";
		PropertyValue[29] = "32";
		PropertyValue[30] = "0";
		PropertyValue[31] = "1";
		PropertyValue[32] = "";

		super.initPropertyValues(XfmrCode.NumPropsThisClass);
	}

	// FIXME Private members in OpenDSS

	public int getNPhases() {
		return NPhases;
	}

	public void setNPhases(int nPhases) {
		NPhases = nPhases;
	}

	public int getActiveWinding() {
		return ActiveWinding;
	}

	public void setActiveWinding(int activeWinding) {
		ActiveWinding = activeWinding;
	}

	public int getMaxWindings() {
		return MaxWindings;
	}

	public void setMaxWindings(int maxWindings) {
		MaxWindings = maxWindings;
	}

	public double getXHL() {
		return XHL;
	}

	public void setXHL(double xHL) {
		XHL = xHL;
	}

	public double getXHT() {
		return XHT;
	}

	public void setXHT(double xHT) {
		XHT = xHT;
	}

	public double getXLT() {
		return XLT;
	}

	public void setXLT(double xLT) {
		XLT = xLT;
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

	public double getNormMaxHKVA() {
		return NormMaxHKVA;
	}

	public void setNormMaxHKVA(double normMaxHKVA) {
		NormMaxHKVA = normMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return EmergMaxHKVA;
	}

	public void setEmergMaxHKVA(double emergMaxHKVA) {
		EmergMaxHKVA = emergMaxHKVA;
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

	public double getLrise() {
		return Lrise;
	}

	public void setLrise(double lrise) {
		Lrise = lrise;
	}

	public double getHSrise() {
		return HSrise;
	}

	public void setHSrise(double hSrise) {
		HSrise = hSrise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public void setPctLoadLoss(double pctLoadLoss) {
		this.pctLoadLoss = pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	public void setPctNoLoadLoss(double pctNoLoadLoss) {
		this.pctNoLoadLoss = pctNoLoadLoss;
	}

	public double getPpm_FloatFactor() {
		return ppm_FloatFactor;
	}

	public void setPpm_FloatFactor(double ppm_FloatFactor) {
		this.ppm_FloatFactor = ppm_FloatFactor;
	}

	public double getPctImag() {
		return pctImag;
	}

	public void setPctImag(double pctImag) {
		this.pctImag = pctImag;
	}

	public Winding[] getWinding() {
		return Winding;
	}

	public void setWinding(Winding[] winding) {
		Winding = winding;
	}

	public int getNumWindings() {
		return NumWindings;
	}

}

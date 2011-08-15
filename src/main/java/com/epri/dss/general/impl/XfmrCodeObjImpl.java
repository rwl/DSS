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

	private int nPhases;
	private int activeWinding;
	private int numWindings;
	private int maxWindings;
	private double XHL, XHT, XLT;  // per unit
	private double[] XSC;     // per unit SC measurements
	private double VABase;    // for impedances
	private double normMaxHKVA;
	private double emergMaxHKVA;
	private double thermalTimeConst;  // hr
	private double nThermal;
	private double mThermal;  // exponents
	private double LRise;
	private double HSRise;
	private double pctLoadLoss;
	private double pctNoLoadLoss;
	private double ppmFloatFactor;  // parts per million winding float factor
	private double pctImag;
	private Winding[] winding;

	public XfmrCodeObjImpl(DSSClass parClass, String xfmrCodeName) {
		super(parClass);
		setName(xfmrCodeName.toLowerCase());
		objType = parClass.getDSSClassType();

		// default values and sizes
		nPhases       = 3;
		numWindings   = 2;
		maxWindings   = 2;
		activeWinding = 1;  // TODO Check zero based indexing
		winding = new Winding[maxWindings];
		for (int i = 0; i < maxWindings; i++)
			winding[i] = new WindingImpl();
		XHL = 0.07;
		XHT = 0.35;
		XLT = 0.30;
		XSC = new double[(numWindings - 1) * numWindings / 2];
		VABase           = winding[0].getKVA() * 1000.0;
		thermalTimeConst = 2.0;
		nThermal        = 0.8;
		mThermal        = 0.8;
		LRise            = 65.0;
		HSRise           = 15.0;  // hot spot rise
		normMaxHKVA      = 1.1 * winding[0].getKVA();
		emergMaxHKVA     = 1.5 * winding[0].getKVA();
		pctLoadLoss      = 2.0 * winding[0].getRpu() * 100.0;  // assume two windings
		ppmFloatFactor  = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < numWindings; i++)
			winding[i].computeAntiFloatAdder(ppmFloatFactor, VABase / nPhases);
		pctNoLoadLoss = 0.0;
		pctImag = 0.0;

		initPropertyValues(0);
	}

	public void setNumWindings(int n) {
		if (n > 1) {
			for (int i = 0; i < numWindings; i++)
				winding[i] = null;  // free old winding objects
			int OldWdgSize = (numWindings - 1) * numWindings / 2;
			numWindings = n;
			maxWindings = n;
			winding = (com.epri.dss.delivery.Winding[]) Utilities.resizeArray(winding, maxWindings);  // reallocate collector array
			for (int i = 0; i < maxWindings; i++)
				winding[i] = new WindingImpl();
			XSC = (double[]) Utilities.resizeArray(XSC, ((numWindings - 1) * numWindings / 2));
			for (int i = OldWdgSize + 1; i < (numWindings-1) * numWindings / 2; i++)
				XSC[i] = 0.30;   // default to something
		} else {
			DSSGlobals.doSimpleMsg("Invalid number of windings: " + String.valueOf(n) + " for Transformer " +
					TransformerImpl.activeTransfObj.getName(), 111);
		}
	}

	public void pullFromTransformer(TransformerObj obj) {
		int i;

		setNumWindings(obj.getNumWindings());
		nPhases = obj.getNPhases();
		XHL = obj.getXHL();
		XHT = obj.getXHT();
		XLT = obj.getXLT();
		VABase = obj.getBaseVA();
		normMaxHKVA = obj.getNormMaxHKVA();
		emergMaxHKVA = obj.getEmergMaxHKVA();
		thermalTimeConst = obj.getThTau();
		nThermal = obj.getThN();
		mThermal = obj.getThM();
		LRise = obj.getFLRise();
		HSRise = obj.getHSRise();
		pctLoadLoss = obj.getPctLoadLoss();
		pctNoLoadLoss = obj.getPctNoLoadLoss();
		ppmFloatFactor = obj.getPPM_FloatFactor();
		pctImag = obj.getPctImag();
		for (i = 0; i < (numWindings - 1) * numWindings / 2; i++)
			XSC[i] = obj.getXsc(i);
		for (i = 0; i < numWindings; i++) {
			winding[i].setConnection(obj.getWdgConnection(i));
			winding[i].setKVLL(obj.getBasekVLL(i));
			winding[i].setVBase(obj.getBaseVoltage(i));
			winding[i].setKVA(obj.getWdgKVA(i));
			winding[i].setPUTap(obj.getPresentTap(i));
			winding[i].setRpu(obj.getWdgResistance(i));
			winding[i].setRNeut(obj.getWdgRNeutral(i));
			winding[i].setXNeut(obj.getWdgXNeutral(i));
			winding[i].setY_PPM(obj.getWdgYPPM(i));
			winding[i].setTapIncrement(obj.getTapIncrement(i));
			winding[i].setMinTap(obj.getMinTap(i));
			winding[i].setMaxTap(obj.getMaxTap(i));
			winding[i].setNumTaps(obj.getNumTaps(i));
		}
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		/* Basic property dump */
		f.println("~ " + "NumWindings=" + numWindings);
		f.println("~ " + "phases=" + nPhases);

		for (int i = 0; i < numWindings; i++) {
			Winding wdg = winding[i];
			if (i == 0) {  // TODO Check zero based indexing
				f.println("~ " + "Wdg=" + i);
			} else {
				f.println("~ " + "Wdg=" + i);
			}
			switch (wdg.getConnection()) {
			case 0:
				f.println("~ conn=wye");
				break;
			case 1:
				f.println("~ conn=delta");
				break;
			}
			f.println("~ kv=" + wdg.getKVLL());
			f.println("~ kva=" + wdg.getKVA());
			f.println("~ tap=" + wdg.getPUTap());
			f.println("~ %r=" + wdg.getRpu() * 100.0);
			f.println("~ rneut=" + wdg.getRNeut());
			f.println("~ xneut=" + wdg.getXNeut());
		}

		f.println("~ " + "xhl=" + XHL * 100.0);
		f.println("~ " + "xht=" + XHT * 100.0);
		f.println("~ " + "xlt=" + XLT * 100.0);
		f.print("~ Xscmatrix= \"");
		for (int i = 0; i < (numWindings - 1) * numWindings / 2; i++)
			f.print(XSC[i] * 100.0 + " ");
		f.println("\"");
		f.println("~ " + "NormMAxHkVA=" + normMaxHKVA);
		f.println("~ " + "EmergMAxHkVA=" + emergMaxHKVA);
		f.println("~ " + "thermal=" + thermalTimeConst);
		f.println("~ " + "n=" + nThermal);
		f.println("~ " + "m=" + mThermal);
		f.println("~ " + "flrise=" + LRise);
		f.println("~ " + "hsrise=" + HSRise);
		f.println("~ " + "%loadloss=" + pctLoadLoss);
		f.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (int i = 27; i < XfmrCode.NumPropsThisClass; i++)  // TODO Check zero based indexing
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		for (int i = XfmrCode.NumPropsThisClass + 1; i < parentClass.getNumProperties(); i++)  // TODO Check zero based indexing
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
	}

	/**
	 * Gets the property for the active winding.
	 * Set the active winding before calling.
	 */
	@Override
	public String getPropertyValue(int index) {
		String result;
		switch (index) {
		case 10:
			result = "[";
			break;
		case 11:
			result = "[";
			break;
		case 12:
			result = "[";
			break;
		case 13:
			result = "[";
			break;
		case 17:
			result = "[";
			break;
		case 32:
			result = "[";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 3:
			result = String.valueOf(activeWinding);  // return active winding
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
			result = String.format("%.7g", winding[activeWinding].getRpu() * 100.0);   // %R
			break;
		case 9:
			result = String.format("%.7g", winding[activeWinding].getRNeut());
			break;
		case 10:
			result = String.format("%.7g", winding[activeWinding].getXNeut());
			break;
		case 11:
			for (int i = 0; i < numWindings; i++)
				switch (winding[i].getConnection()) {
				case 0:
					result = result + "wye, ";
					break;
				case 1:
					result = result + "delta, ";
					break;
				}
			break;
		case 12:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVLL());
			break;
		case 13:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVA());
			break;
		case 14:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getPUTap());
			break;
		case 18:
			for (int i = 0; i < (numWindings - 1) * numWindings / 2; i++)
				result = result + String.format("%-g, ", XSC[i] * 100.0);
		case 24:
			result = String.format("%.7g", pctLoadLoss);
			break;
		case 25:
			result = String.format("%.7g", pctNoLoadLoss);
			break;
		case 28:
			result = String.format("%.7g", winding[activeWinding].getMaxTap());
			break;
		case 29:
			result = String.format("%.7g", winding[activeWinding].getMinTap());
			break;
		case 30:
			result = String.format("%-d", winding[activeWinding].getNumTaps());
			break;
		case 33:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getRpu() * 100.0);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}


		switch (index) {
		case 10:
			result = "]";
			break;
		case 11:
			result = "]";
			break;
		case 12:
			result = "]";
			break;
		case 13:
			result = "]";
			break;
		case 17:
			result = "]";
			break;
		case 32:
			result = "]";
			break;
		default:
			result = "";
			break;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[0] = "3"; // "phases";
		propertyValue[1] = "2"; // "windings";
		propertyValue[2] = "1"; // "wdg";
		propertyValue[3] = "wye";   // "conn";
		propertyValue[4] = "12.47"; // if 2or 3-phase: phase-phase else actual winding
		propertyValue[5] = "1000";
		propertyValue[6] = "1.0";
		propertyValue[7] = "0.2";
		propertyValue[8] = "-1";
		propertyValue[9] = "0";
		propertyValue[10] = "";
		propertyValue[11] = "";  // if 1-phase: actual winding rating; else phase-phase
		propertyValue[12] = "";  // if 1-phase: actual winding rating; else phase-phase
		propertyValue[13] = "";
		propertyValue[14] = "7";
		propertyValue[15] = "35";
		propertyValue[16] = "30";
		propertyValue[17] = "";  // x12 13 14... 23 24.. 34 ..
		propertyValue[18] = "2";
		propertyValue[19] = ".8";
		propertyValue[20] = ".8";
		propertyValue[21] = "65";
		propertyValue[22] = "15";
		propertyValue[23] = "0";
		propertyValue[24] = "0";
		propertyValue[25] = "";
		propertyValue[26] = "";
		propertyValue[27] = "1.10";
		propertyValue[28] = "0.90";
		propertyValue[29] = "32";
		propertyValue[30] = "0";
		propertyValue[31] = "1";
		propertyValue[32] = "";

		super.initPropertyValues(XfmrCode.NumPropsThisClass);
	}

	// FIXME Private members in OpenDSS

	public int getNPhases() {
		return nPhases;
	}

	public void setNPhases(int num) {
		nPhases = num;
	}

	public int getActiveWinding() {
		return activeWinding;
	}

	public void setActiveWinding(int winding) {
		activeWinding = winding;
	}

	public int getMaxWindings() {
		return maxWindings;
	}

	public void setMaxWindings(int max) {
		maxWindings = max;
	}

	public double getXHL() {
		return XHL;
	}

	public void setXHL(double value) {
		XHL = value;
	}

	public double getXHT() {
		return XHT;
	}

	public void setXHT(double value) {
		XHT = value;
	}

	public double getXLT() {
		return XLT;
	}

	public void setXLT(double value) {
		XLT = value;
	}

	public double[] getXSC() {
		return XSC;
	}

	public void setXSC(double[] value) {
		XSC = value;
	}

	public double getVABase() {
		return VABase;
	}

	public void setVABase(double base) {
		VABase = base;
	}

	public double getNormMaxHKVA() {
		return normMaxHKVA;
	}

	public void setNormMaxHKVA(double max) {
		normMaxHKVA = max;
	}

	public double getEmergMaxHKVA() {
		return emergMaxHKVA;
	}

	public void setEmergMaxHKVA(double max) {
		emergMaxHKVA = max;
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

	public void setNThermal(double n) {
		this.nThermal = n;
	}

	public double getMThermal() {
		return mThermal;
	}

	public void setMThermal(double m) {
		this.mThermal = m;
	}

	public double getLRise() {
		return LRise;
	}

	public void setLRise(double rise) {
		LRise = rise;
	}

	public double getHSRise() {
		return HSRise;
	}

	public void setHSRise(double rise) {
		HSRise = rise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public void setPctLoadLoss(double pct) {
		this.pctLoadLoss = pct;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	public void setPctNoLoadLoss(double pct) {
		this.pctNoLoadLoss = pct;
	}

	public double getPpmFloatFactor() {
		return ppmFloatFactor;
	}

	public void setPpmFloatFactor(double factor) {
		this.ppmFloatFactor = factor;
	}

	public double getPctImag() {
		return pctImag;
	}

	public void setPctImag(double pct) {
		this.pctImag = pct;
	}

	public Winding[] getWinding() {
		return winding;
	}

	public void setWinding(Winding[] values) {
		winding = values;
	}

	public int getNumWindings() {
		return numWindings;
	}

}

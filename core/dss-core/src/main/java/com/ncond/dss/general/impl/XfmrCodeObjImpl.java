package com.ncond.dss.general.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.delivery.TransformerObj;
import com.ncond.dss.delivery.Winding;
import com.ncond.dss.delivery.impl.TransformerImpl;
import com.ncond.dss.delivery.impl.WindingImpl;
import com.ncond.dss.general.XfmrCode;
import com.ncond.dss.general.XfmrCodeObj;

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
		activeWinding = 0;
		winding = new Winding[maxWindings];
		for (int i = 0; i < maxWindings; i++)
			winding[i] = new WindingImpl();
		XHL = 0.07;
		XHT = 0.35;
		XLT = 0.30;
		XSC = new double[(numWindings - 1) * numWindings / 2];
		VABase           = winding[0].getKVA() * 1000.0;
		thermalTimeConst = 2.0;
		nThermal         = 0.8;
		mThermal         = 0.8;
		LRise            = 65.0;
		HSRise           = 15.0;  // hot spot rise
		normMaxHKVA      = 1.1 * winding[0].getKVA();
		emergMaxHKVA     = 1.5 * winding[0].getKVA();
		pctLoadLoss      = 2.0 * winding[0].getRpu() * 100.0;  // assume two windings
		ppmFloatFactor   = 0.000001;
		/* Compute antifloat added for each winding */
		for (int i = 0; i < numWindings; i++)
			winding[i].computeAntiFloatAdder(ppmFloatFactor, VABase / nPhases);
		pctNoLoadLoss = 0.0;
		pctImag = 0.0;

		initPropertyValues(0);
	}

	@Override
	public void setNumWindings(int n) {
		int oldWdgSize;
		int newWdgSize;

		if (n > 1) {
			for (int i = 0; i < numWindings; i++)
				winding[i] = null;  // free old winding objects
			oldWdgSize = (numWindings - 1) * numWindings / 2;
			numWindings = n;
			maxWindings = n;
		        newWdgSize = (numWindings - 1) * numWindings / 2;
			winding = Util.resizeArray(winding, maxWindings);  // reallocate collector array
			for (int i = 0; i < maxWindings; i++)
				winding[i] = new WindingImpl();
			XSC = Util.resizeArray(XSC, newWdgSize);
			for (int i = oldWdgSize; i < newWdgSize; i++)
				XSC[i] = 0.30;   // default to something
		} else {
			DSS.doSimpleMsg("Invalid number of windings: " + String.valueOf(n) + " for Transformer " +
					TransformerImpl.activeTransfObj.getName(), 111);
		}
	}

	@Override
	public void pullFromTransformer(TransformerObj obj) {
		int i;

		setNumWindings(obj.getNumWindings());
		nPhases = obj.getNumPhases();
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
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		/* Basic property dump */
		pw.println("~ " + "numWindings=" + numWindings);
		pw.println("~ " + "phases=" + nPhases);

		for (int i = 0; i < numWindings; i++) {
			Winding wdg = winding[i];
			if (i == 0) {
				pw.println("~ " + "Wdg=" + i);
			} else {
				pw.println("~ " + "Wdg=" + i);
			}
			switch (wdg.getConnection()) {
			case 0:
				pw.println("~ conn=wye");
				break;
			case 1:
				pw.println("~ conn=delta");
				break;
			}
			pw.println("~ kv=" + wdg.getKVLL());
			pw.println("~ kva=" + wdg.getKVA());
			pw.println("~ tap=" + wdg.getPUTap());
			pw.println("~ %r=" + wdg.getRpu() * 100.0);
			pw.println("~ rneut=" + wdg.getRNeut());
			pw.println("~ xneut=" + wdg.getXNeut());
		}

		pw.println("~ " + "xhl=" + XHL * 100.0);
		pw.println("~ " + "xht=" + XHT * 100.0);
		pw.println("~ " + "xlt=" + XLT * 100.0);
		pw.print("~ Xscmatrix= \"");
		for (int i = 0; i < (numWindings - 1) * numWindings / 2; i++)
			pw.print(XSC[i] * 100.0 + " ");
		pw.println("\"");
		pw.println("~ " + "NormMAxHkVA=" + normMaxHKVA);
		pw.println("~ " + "EmergMAxHkVA=" + emergMaxHKVA);
		pw.println("~ " + "thermal=" + thermalTimeConst);
		pw.println("~ " + "n=" + nThermal);
		pw.println("~ " + "m=" + mThermal);
		pw.println("~ " + "flrise=" + LRise);
		pw.println("~ " + "hsrise=" + HSRise);
		pw.println("~ " + "%loadloss=" + pctLoadLoss);
		pw.println("~ " + "%noloadloss=" + pctNoLoadLoss);

		for (int i = 27; i < XfmrCode.NumPropsThisClass; i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		for (int i = XfmrCode.NumPropsThisClass; i < parentClass.getNumProperties(); i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		pw.close();
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
		case 2:
			result = String.valueOf(activeWinding);  // return active winding
			break;
		case 3:
			switch (winding[activeWinding].getConnection()) {
			case 0:
				result = "wye ";
				break;
			case 1:
				result = "delta ";
				break;
			}
			break;
		case 4:
			result = String.format("%.7g", winding[activeWinding].getKVLL());
			break;
		case 5:
			result = String.format("%.7g", winding[activeWinding].getKVA());
			break;
		case 6:
			result = String.format("%.7g", winding[activeWinding].getPUTap());
			break;
		case 7:
			result = String.format("%.7g", winding[activeWinding].getRpu() * 100.0);   // %R
			break;
		case 8:
			result = String.format("%.7g", winding[activeWinding].getRNeut());
			break;
		case 9:
			result = String.format("%.7g", winding[activeWinding].getXNeut());
			break;
		case 10:
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
		case 11:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVLL());
			break;
		case 12:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getKVA());
			break;
		case 13:
			for (int i = 0; i < numWindings; i++)
				result = result + String.format("%.7g, ", winding[i].getPUTap());
			break;
		case 17:
			for (int i = 0; i < (numWindings - 1) * numWindings / 2; i++)
				result = result + String.format("%-g, ", XSC[i] * 100.0);
		case 23:
			result = String.format("%.7g", pctLoadLoss);
			break;
		case 24:
			result = String.format("%.7g", pctNoLoadLoss);
			break;
		case 27:
			result = String.format("%.7g", winding[activeWinding].getMaxTap());
			break;
		case 28:
			result = String.format("%.7g", winding[activeWinding].getMinTap());
			break;
		case 29:
			result = String.format("%-d", winding[activeWinding].getNumTaps());
			break;
		case 32:
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

		setPropertyValue(0, "3"); // "phases");
		setPropertyValue(1, "2"); // "windings");
		setPropertyValue(2, "1"); // "wdg");
		setPropertyValue(3, "wye");    // "conn");
		setPropertyValue(4, "12.47");  // if 2or 3-phase: phase-phase else actual winding
		setPropertyValue(5, "1000");
		setPropertyValue(6, "1.0");
		setPropertyValue(7, "0.2");
		setPropertyValue(8, "-1");
		setPropertyValue(9, "0");
		setPropertyValue(10, "");
		setPropertyValue(11, "");  // if 1-phase: actual winding rating; else phase-phase
		setPropertyValue(12, "");  // if 1-phase: actual winding rating; else phase-phase
		setPropertyValue(13, "");
		setPropertyValue(14, "7");
		setPropertyValue(15, "35");
		setPropertyValue(16, "30");
		setPropertyValue(17, "");  // x12 13 14... 23 24.. 34 ..
		setPropertyValue(18, "2");
		setPropertyValue(19, ".8");
		setPropertyValue(20, ".8");
		setPropertyValue(21, "65");
		setPropertyValue(22, "15");
		setPropertyValue(23, "0");
		setPropertyValue(24, "0");
		setPropertyValue(25, "");
		setPropertyValue(26, "");
		setPropertyValue(27, "1.10");
		setPropertyValue(28, "0.90");
		setPropertyValue(29, "32");
		setPropertyValue(30, "0");
		setPropertyValue(31, "1");
		setPropertyValue(32, "");

		super.initPropertyValues(XfmrCode.NumPropsThisClass - 1);
	}

	// FIXME Private members in OpenDSS

	@Override
	public int getNPhases() {
		return nPhases;
	}

	@Override
	public void setNPhases(int num) {
		nPhases = num;
	}

	@Override
	public int getActiveWinding() {
		return activeWinding;
	}

	@Override
	public void setActiveWinding(int winding) {
		activeWinding = winding;
	}

	@Override
	public int getMaxWindings() {
		return maxWindings;
	}

	@Override
	public void setMaxWindings(int max) {
		maxWindings = max;
	}

	@Override
	public double getXHL() {
		return XHL;
	}

	@Override
	public void setXHL(double value) {
		XHL = value;
	}

	@Override
	public double getXHT() {
		return XHT;
	}

	@Override
	public void setXHT(double value) {
		XHT = value;
	}

	@Override
	public double getXLT() {
		return XLT;
	}

	@Override
	public void setXLT(double value) {
		XLT = value;
	}

	@Override
	public double[] getXSC() {
		return XSC;
	}

	@Override
	public void setXSC(double[] value) {
		XSC = value;
	}

	@Override
	public double getVABase() {
		return VABase;
	}

	@Override
	public void setVABase(double base) {
		VABase = base;
	}

	@Override
	public double getNormMaxHKVA() {
		return normMaxHKVA;
	}

	@Override
	public void setNormMaxHKVA(double max) {
		normMaxHKVA = max;
	}

	@Override
	public double getEmergMaxHKVA() {
		return emergMaxHKVA;
	}

	@Override
	public void setEmergMaxHKVA(double max) {
		emergMaxHKVA = max;
	}

	@Override
	public double getThermalTimeConst() {
		return thermalTimeConst;
	}

	@Override
	public void setThermalTimeConst(double timeConst) {
		thermalTimeConst = timeConst;
	}

	@Override
	public double getNThermal() {
		return nThermal;
	}

	@Override
	public void setNThermal(double n) {
		this.nThermal = n;
	}

	@Override
	public double getMThermal() {
		return mThermal;
	}

	@Override
	public void setMThermal(double m) {
		this.mThermal = m;
	}

	@Override
	public double getLRise() {
		return LRise;
	}

	@Override
	public void setLRise(double rise) {
		LRise = rise;
	}

	@Override
	public double getHSRise() {
		return HSRise;
	}

	@Override
	public void setHSRise(double rise) {
		HSRise = rise;
	}

	@Override
	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	@Override
	public void setPctLoadLoss(double pct) {
		this.pctLoadLoss = pct;
	}

	@Override
	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	@Override
	public void setPctNoLoadLoss(double pct) {
		this.pctNoLoadLoss = pct;
	}

	@Override
	public double getPpmFloatFactor() {
		return ppmFloatFactor;
	}

	@Override
	public void setPpmFloatFactor(double factor) {
		this.ppmFloatFactor = factor;
	}

	@Override
	public double getPctImag() {
		return pctImag;
	}

	@Override
	public void setPctImag(double pct) {
		this.pctImag = pct;
	}

	@Override
	public Winding[] getWinding() {
		return winding;
	}

	@Override
	public void setWinding(Winding[] values) {
		winding = values;
	}

	@Override
	public int getNumWindings() {
		return numWindings;
	}

}

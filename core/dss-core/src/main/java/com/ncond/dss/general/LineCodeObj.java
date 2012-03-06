package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.LineUnits;

/**
 * A general DSS object used by all circuits as a reference for obtaining line
 * impedances.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the code property in the LineCode class.
 * This sets the active LineCode object to be the one referenced by the code property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public class LineCodeObj extends DSSObject {

	private int neutralConductor;

	private int nPhases;

	protected boolean symComponentsModel, reduceByKron;

	protected CMatrix Z,  // base frequency series Z matrix
		Zinv,
		Yc;               // shunt capacitance matrix at base frequency

	protected double baseFrequency;

	protected double R1, X1, R0, X0, C1, C0;
	protected double normAmps, emergAmps, faultRate, pctPerm, hrsToRepair;
	protected double Rg, Xg, rho;

	protected int units;  // see LineUnits

	public LineCodeObj(DSSClass parClass, String lineCodeName) {
		super(parClass);

		setName(lineCodeName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		neutralConductor = nPhases - 1;  // initialize to last conductor
		R1 = 0.0580;  // ohms per 1000ft
		X1 = 0.1206;
		R0 = 0.1784;
		X0 = 0.4047;
		C1 = 3.4e-9;  // nf per 1000ft
		C0 = 1.6e-9;
		Z    = null;
		Zinv = null;
		Yc   = null;
		baseFrequency = DSS.activeCircuit.getFundamental();
		units = LineUnits.UNITS_NONE;  // default to none (no conversion)
		normAmps  = 400.0;
		emergAmps = 600.0;
		pctPerm   = 20.0;
		faultRate = 0.1;

		Rg  = 0.01805;  // ohms per 1000'
		Xg  = 0.155081;
		rho = 100.0;

		symComponentsModel = true;
		reduceByKron = false;
		calcMatricesFromZ1Z0();  // put some reasonable values in

		initPropertyValues(0);
	}

	private String getRMatrix() {
		String result = "[";
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				result = result + String.format("%12.8f ", Z.get(i, j).getReal());
			if (i < nPhases - 1)
				result = result + "|";
		}
		return result + "]";
	}

	private String getXMatrix() {
		String result = "[";
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				result = result + String.format("%12.8f ", Z.get(i, j).getImaginary());
			if (i < nPhases - 1)
				result = result + "|";
		}
		return result + "]";
	}

	private String getCMatrix() {
		String result = "[";
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				result = result + String.format("%12.8f ", Yc.get(i, j).getImaginary() / DSS.TWO_PI / baseFrequency * 1.e9);
			if (i < nPhases - 1)
				result = result + "|";
		}
		return result + "]";
	}

	/**
	 * Set the number of phases and reallocate phase-sensitive arrays.
	 * Need to preserve values in Z matrices.
	 */
	public void setNPhases(int value) {
		if (value > 0)
			if (nPhases != value) {  // if size is no different, we don't need to do anything
				nPhases = value;
				neutralConductor = nPhases;  // init to last conductor
				// put some reasonable values in these matrices
				calcMatricesFromZ1Z0();  // reallocs matrices
			}
	}

	public int getNPhases() {
		return nPhases;
	}

	public void calcMatricesFromZ1Z0() {
		Complex Zs, Zm, Ys, Ym, Ztemp;
		int i, j;
		double Yc1, Yc0, oneThird;

		// for a line, nPhases = nCond, for now
		Z    = new CMatrix(nPhases);
		Zinv = new CMatrix(nPhases);
		Yc   = new CMatrix(nPhases);

		oneThird = 1.0 / 3.0;  // do this to get more precision in next few statements

		Ztemp = new Complex(R1, X1).multiply(2.0);
		Zs = Ztemp.add(new Complex(R0, X0)).multiply(oneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(oneThird);

		Yc1 = DSS.TWO_PI * baseFrequency * C1;
		Yc0 = DSS.TWO_PI * baseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add(new Complex(0.0, Yc0)).multiply(oneThird);
		Ym = new Complex(0.0, Yc0).subtract(new Complex(0.0, Yc1)).multiply(oneThird);

		for (i = 0; i < nPhases; i++) {
			Z.set(i, i, Zs);
			Yc.set(i, i, Ys);
			for (j = 0; j < i; j++) {
				Z.setSym(i, j, Zm);
				Yc.setSym(i, j, Ym);
			}
		}
		Zinv.copyFrom(Z);
		Zinv.invert();
	}

	@Override
	public void dumpProperties(OutputStream out, boolean Complete) {
		super.dumpProperties(out, Complete);

		PrintWriter pw = new PrintWriter(out);

		pw.println("~ " + parentClass.getPropertyName(0) + "=" + nPhases);
		pw.println("~ " + parentClass.getPropertyName(1) + "=" + R1);
		pw.println("~ " + parentClass.getPropertyName(2) + "=" + X1);
		pw.println("~ " + parentClass.getPropertyName(3) + "=" + R0);
		pw.println("~ " + parentClass.getPropertyName(4) + "=" + X0);
		pw.println("~ " + parentClass.getPropertyName(5) + "=" + C1 * 1.0e9);
		pw.println("~ " + parentClass.getPropertyName(6) + "=" + C0 * 1.0e9);
		pw.println("~ " + parentClass.getPropertyName(7) + "=" + propertyValue[8]);
		pw.print("~ " + parentClass.getPropertyName(8) + "=\"");
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				pw.print(Z.get(i, j).getReal() + " ");
			pw.print("|");
		}
		pw.println("\"");

		pw.print("~ " + parentClass.getPropertyName(9) + "=\"");
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				pw.print(Z.get(i, j).getImaginary() + " ");
			pw.print("|");
		}
		pw.println("\"");

		pw.print("~ " + parentClass.getPropertyName(10) + "=\"");
		for (int i = 0; i < nPhases; i++) {
			for (int j = 0; j < nPhases; j++)
				pw.print((Yc.get(i, j).getImaginary() / DSS.TWO_PI / baseFrequency * 1.e9) + " ");
			pw.print("|");
		}
		pw.println("\"");

		for (int i = 11; i < 21; i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + propertyValue[i]);

		pw.println(String.format("~ %s=%d", parentClass.getPropertyName(22), neutralConductor));

		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 0:
			return String.format("%d", nPhases);
		case 1:
			return symComponentsModel ? String.format("%.5g", R1) : "----";
		case 2:
			return symComponentsModel ? String.format("%.5g", X1) : "----";
		case 3:
			return symComponentsModel ? String.format("%.5g", R0) : "----";
		case 4:
			return symComponentsModel ? String.format("%.5g", X0) : "----";
		case 5:
			return symComponentsModel ? String.format("%.5g", C1 * 1.0e9) : "----";
		case 6:
			return symComponentsModel ? String.format("%.5g", C0 * 1.0e9) : "----";
		case 7:
			return LineUnits.lineUnitsStr(units);
		case 8:
			return getRMatrix();
		case 9:
			return getXMatrix();
		case 10:
			return getCMatrix();
		case 11:
			return String.format("%.g", DSS.defaultBaseFreq);  // "baseFreq";
		case 17:
			return reduceByKron ? "Y" : "N";
		case 18:
			return String.format("%.5g", Rg);
		case 19:
			return String.format("%.5g", Xg);
		case 20:
			return String.format("%.5g", rho);
		case 21:
			return String.valueOf(neutralConductor);
		default:
			return super.getPropertyValue(index);
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, "3");      // "nphases"
		setPropertyValue(1, ".058");   // "r1"
		setPropertyValue(2, ".1206");  // "x1"
		setPropertyValue(3, "0.1784"); // "r0"
		setPropertyValue(4, "0.4047"); // "x0"
		setPropertyValue(5, "3.4");  // "c1"
		setPropertyValue(6, "1.6");  // "c0"
		setPropertyValue(7, "none"); // "units"
		setPropertyValue(8, "");     // "rmatrix"
		setPropertyValue(9, "");     // "xmatrix"
		setPropertyValue(10, "");     // "cmatrix"
		setPropertyValue(11, String.format("%6.1f", DSS.defaultBaseFreq));  // "baseFreq"
		setPropertyValue(12, "400");  // "normamps"
		setPropertyValue(13, "600");  // "emergamps"
		setPropertyValue(14, "0.1");  // "faultrate"
		setPropertyValue(15, "20");   // "pctperm"
		setPropertyValue(16, "3");    // "Hrs to repair"
		setPropertyValue(17, "N");    // "Kron"
		setPropertyValue(18, ".01805");  // "Rg"
		setPropertyValue(19, ".155081"); // "Xg"
		setPropertyValue(20, "100");     // "rho"
		setPropertyValue(21, "3");       // "Neutral"

		super.initPropertyValues(LineCode.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public void doKronReduction() {
		if (neutralConductor == -1)
			return;

		CMatrix newZ = null;
		CMatrix newYc = null;

		if (nPhases > 1) {
			try {
				newZ = Z.kron(neutralConductor);  // perform Kron reductions into temp space
				/* Have to invert the Y matrix to eliminate properly */
				Yc.invert();  // Vn = 0 not In
				newYc = Yc.kron(neutralConductor);
			} catch (Exception e) {
				DSS.doSimpleMsg(String.format("Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.", getName(), neutralConductor), 103);
			}

			// Reallocate into smaller space if Kron was successful

			if (newZ != null && newYc != null) {

				newYc.invert();  // back to Y

				nPhases = newZ.order();

				Z  = newZ;
				Yc = newYc;

				neutralConductor = -1;
				reduceByKron = false;

				/* Change property values to reflect Kron reduction for save circuit function */
				setPropertyValue(0, String.format("%d", nPhases));
				setPropertyValue(8, getRMatrix());
				setPropertyValue(9, getXMatrix());
				setPropertyValue(10, getCMatrix());

			} else {
				DSS.doSimpleMsg(String.format("Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.", getName(), neutralConductor), 103);
			}

		} else {
			DSS.doSimpleMsg("Cannot perform Kron Reduction on a 1-phase LineCode: LineCode." + getName(), 103);
		}
	}

	public boolean isSymComponentsModel() {
		return symComponentsModel;
	}

	public void setSymComponentsModel(boolean model) {
		symComponentsModel = model;
	}

	public boolean isReduceByKron() {
		return reduceByKron;
	}

	public void setReduceByKron(boolean reduce) {
		reduceByKron = reduce;
	}

	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public CMatrix getZinv() {
		return Zinv;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	public CMatrix getYC() {
		return Yc;
	}

	public void setYc(CMatrix Yc) {
		this.Yc = Yc;
	}

	public double getBaseFrequency() {
		return baseFrequency;
	}

	public void setBaseFrequency(double frequency) {
		baseFrequency = frequency;
	}

	public double getR1() {
		return R1;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public double getX1() {
		return X1;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public double getR0() {
		return R0;
	}

	public void setR0(double r0) {
		R0 = r0;
	}

	public double getX0() {
		return X0;
	}

	public void setX0(double x0) {
		X0 = x0;
	}

	public double getC1() {
		return C1;
	}

	public void setC1(double c1) {
		C1 = c1;
	}

	public double getC0() {
		return C0;
	}

	public void setC0(double c0) {
		C0 = c0;
	}

	public double getNormAmps() {
		return normAmps;
	}

	public void setNormAmps(double amps) {
		normAmps = amps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public void setEmergAmps(double amps) {
		emergAmps = amps;
	}

	public double getFaultRate() {
		return faultRate;
	}

	public void setFaultRate(double rate) {
		faultRate = rate;
	}

	public double getPctPerm() {
		return pctPerm;
	}

	public void setPctPerm(double pct) {
		pctPerm = pct;
	}

	public double getHrsToRepair() {
		return hrsToRepair;
	}

	public void setHrsToRepair(double hrs) {
		hrsToRepair = hrs;
	}

	public double getRg() {
		return Rg;
	}

	public void setRg(double rg) {
		Rg = rg;
	}

	public double getXg() {
		return Xg;
	}

	public void setXg(double xg) {
		Xg = xg;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double r) {
		this.rho = r;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int u) {
		units = u;
	}


	// FIXME Private members in OpenDSS

	public int getNeutralConductor() {
		return neutralConductor;
	}

	public void setNeutralConductor(int neutral) {
		neutralConductor = neutral;
	}

}

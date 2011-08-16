package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineCodeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import org.apache.commons.math.complex.Complex;
import com.epri.dss.shared.impl.LineUnits;

public class LineCodeObjImpl extends DSSObjectImpl implements LineCodeObj {

	private int NeutralConductor;

	private int NPhases;

	protected boolean SymComponentsModel, ReduceByKron;

	protected CMatrix Z,  // base frequency series Z matrix
		Zinv,
		Yc;               // shunt capacitance matrix at base frequency

	protected double BaseFrequency;

	protected double R1, X1, R0, X0, C1, C0;
	protected double NormAmps, EmergAmps, FaultRate, PctPerm, HrsToRepair;
	protected double Rg, Xg, rho;

	protected int Units;  // see LineUnits

	public LineCodeObjImpl(DSSClass ParClass, String LineCodeName) {
		super(ParClass);

		setName(LineCodeName.toLowerCase());
		objType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		NeutralConductor = NPhases - 1;  // initialize to last conductor  TODO Check zero indexing
		R1 = 0.0580;  // ohms per 1000 ft
		X1 = 0.1206;
		R0 = 0.1784;
		X0 = 0.4047;
		C1 = 3.4e-9;  // nf per 1000ft
		C0 = 1.6e-9;
		Z    = null;
		Zinv = null;
		Yc   = null;
		BaseFrequency = DSSGlobals.activeCircuit.getFundamental();
		Units = LineUnits.UNITS_NONE;  // default to none  (no conversion)
		NormAmps = 400.0;
		EmergAmps = 600.0;
		PctPerm = 20.0;
		FaultRate = 0.1;

		Rg = 0.01805;  // ohms per 1000'
		Xg = 0.155081;
		rho = 100.0;

		SymComponentsModel = true;
		ReduceByKron = false;
		calcMatricesFromZ1Z0();  // put some reasonable values in

		initPropertyValues(0);
	}

	private String getRMatrix() {
		String Result = "[";
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				Result = Result + String.format("%12.8f ", Z.getElement(i, j).getReal());
			if (i < NPhases)  // TODO Check zero based indexing
				Result = Result + "|";
		}
		return Result + "]";
	}

	private String getXMatrix() {
		String Result = "[";
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				Result = Result + String.format("%12.8f ", Z.getElement(i, j).getImaginary());
			if (i < NPhases)  // TODO Check zero based indexing
				Result = Result + "|";
		}
		return Result + "]";
	}

	private String getCMatrix() {
		String Result = "[";
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				Result = Result + String.format("%12.8f ", Yc.getElement(i, j).getImaginary() / DSSGlobals.TWO_PI / BaseFrequency * 1.e9);
			if (i < NPhases)  // TODO Check zero based indexing
				Result = Result + "|";
		}
		return Result + "]";
	}

	/**
	 * Set the number of phases and reallocate phase-sensitive arrays.
	 * Need to preserve values in Z matrices.
	 */
	public void setNPhases(int Value) {
		if (Value > 0)
			if (NPhases != Value) {  // if size is no different, we don't need to do anything
				NPhases = Value;
				NeutralConductor = NPhases;  // init to last conductor
				// put some reasonable values in these matrices
				calcMatricesFromZ1Z0();  // reallocs matrices
			}
	}

	public int getNPhases() {
		return NPhases;
	}

	public void calcMatricesFromZ1Z0() {
		Complex Zs, Zm, Ys, Ym, Ztemp;
		int i, j;
		double Yc1, Yc0, OneThird;

		if (Z != null)
			Z = null;
		if (Zinv != null)
			Zinv = null;
		if (Yc != null)
			Yc = null;

		// for a line, nPhases = nCond, for now
		Z    = new CMatrixImpl(NPhases);
		Zinv = new CMatrixImpl(NPhases);
		Yc   = new CMatrixImpl(NPhases);

		OneThird = 1.0 / 3.0;  // do this to get more precision in next few statements

		Ztemp = new Complex(R1, X1).multiply(2.0);
		Zs = Ztemp.add(new Complex(R0, X0)).multiply(OneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(OneThird);

		Yc1 = DSSGlobals.TWO_PI * BaseFrequency * C1;
		Yc0 = DSSGlobals.TWO_PI * BaseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add(new Complex(0.0, Yc0)).multiply(OneThird);
		Ym = new Complex(0.0, Yc0).subtract(new Complex(0.0, Yc1)).multiply(OneThird);

		for (i = 0; i < NPhases; i++) {
			Z.setElement(i, i, Zs);
			Yc.setElement(i, i, Ys);
			for (j = 0; j < i - 1; j++) {
				Z.setElemSym(i, j, Zm);
				Yc.setElemSym(i, j, Ym);
			}
		}
		Zinv.copyFrom(Z);
		Zinv.invert();
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		F.println("~ " + parentClass.getPropertyName()[1] + "=" + NPhases);
		F.println("~ " + parentClass.getPropertyName()[2] + "=" + R1);
		F.println("~ " + parentClass.getPropertyName()[3] + "=" + X1);
		F.println("~ " + parentClass.getPropertyName()[4] + "=" + R0);
		F.println("~ " + parentClass.getPropertyName()[5] + "=" + X0);
		F.println("~ " + parentClass.getPropertyName()[6] + "=" + C1 * 1.0e9);
		F.println("~ " + parentClass.getPropertyName()[7] + "=" + C0 * 1.0e9);
		F.println("~ " + parentClass.getPropertyName()[8] + "=" + propertyValue[8]);
		F.print("~ " + parentClass.getPropertyName()[9] + "=\"");
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				F.print(Z.getElement(i, j).getReal() + " ");
			F.print("|");
		}
		F.println("\"");

		F.print("~ " + parentClass.getPropertyName()[10] + "=\"");
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				F.print(Z.getElement(i, j).getImaginary() + " ");
			F.print("|");
		}
		F.println("\"");

		F.print("~ " + parentClass.getPropertyName()[11] + "=\"");
		for (int i = 0; i < NPhases; i++) {
			for (int j = 0; j < NPhases; j++)
				F.print((Yc.getElement(i, j).getImaginary() / DSSGlobals.TWO_PI / BaseFrequency * 1.e9) + " ");
			F.print("|");
		}
		F.println("\"");

		for (int i = 12; i < 21; i++)
			F.println("~ " + parentClass.getPropertyName()[i] + "=" + propertyValue[i]);

		F.println(String.format("~ %s=%d", parentClass.getPropertyName()[22], NeutralConductor));
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case 1:
			return String.format("%d", NPhases);
		case 2:
			return SymComponentsModel ? String.format("%.5g", R1) : "----";
		case 3:
			return SymComponentsModel ? String.format("%.5g", X1) : "----";
		case 4:
			return SymComponentsModel ? String.format("%.5g", R0) : "----";
		case 5:
			return SymComponentsModel ? String.format("%.5g", X0) : "----";
		case 6:
			return SymComponentsModel ? String.format("%.5g", C1 * 1.0e9) : "----";
		case 7:
			return SymComponentsModel ? String.format("%.5g", C0 * 1.0e9) : "----";
		case 8:
			return LineUnits.lineUnitsStr(Units);
		case 9:
			return getRMatrix();
		case 10:
			return getXMatrix();
		case 11:
			return getCMatrix();
		case 12:
			return String.format("%.g", DSSGlobals.defaultBaseFreq);  // "baseFreq";
		case 18:
			return ReduceByKron ? "Y" : "N";
		case 19:
			return String.format("%.5g", Rg);
		case 20:
			return String.format("%.5g", Xg);
		case 21:
			return String.format("%.5g", rho);
		case 22:
			return String.valueOf(NeutralConductor);
		default:
			return super.getPropertyValue(Index);
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		propertyValue[0] =  "3";      // "nphases";
		propertyValue[1] =  ".058";   // "r1";
		propertyValue[2] =  ".1206";  // "x1";
		propertyValue[3] =  "0.1784"; // "r0";
		propertyValue[4] =  "0.4047"; // "x0";
		propertyValue[5] =  "3.4";  // "c1";
		propertyValue[6] =  "1.6";  // "c0";
		propertyValue[7] =  "none"; // "units";
		propertyValue[8] =  "";     // "rmatrix";
		propertyValue[9] =  "";     // "xmatrix";
		propertyValue[10] = "";     // "cmatrix";
		propertyValue[11] = String.format("%6.1f", DSSGlobals.defaultBaseFreq);  // "baseFreq";
		propertyValue[12] = "400";  // "normamps";
		propertyValue[13] = "600";  // "emergamps";
		propertyValue[14] = "0.1";  // "faultrate";
		propertyValue[15] = "20";   // "pctperm";
		propertyValue[16] = "3";    // "Hrs to repair";
		propertyValue[17] = "N";    // "Kron";
		propertyValue[18] = ".01805";  // "Rg";
		propertyValue[19] = ".155081"; // "Xg";
		propertyValue[20] = "100";     // "rho";
		propertyValue[21] = "3";       // "Neutral";

		super.initPropertyValues(LineCode.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public void doKronReduction() {
		if (NeutralConductor == 0)  // TODO Check zero based indexing
			return;   // Do Nothing

		CMatrix NewZ = null;
		CMatrix NewYc = null;

		if (NPhases > 1) {
			try {
				NewZ = Z.kron(NeutralConductor);  // perform Kron reductions into temp space
				/* Have to invert the Y matrix to eliminate properly */
				Yc.invert();  // Vn = 0 not In
				NewYc = Yc.kron(NeutralConductor);
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg(String.format("Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.", getName(), NeutralConductor), 103);
			}

			// Reallocate into smaller space if Kron was successful

			if ((NewZ != null) && (NewYc != null)) {

				NewYc.invert();  // back to Y

				NPhases = NewZ.order();

				// get rid of Z and Yc and replace
				Z = null;
				Yc = null;

				Z  = NewZ;
				Yc = NewYc;

				NeutralConductor = 0;  // TODO Check zero based indexing
				ReduceByKron = false;

				/* Change property values to reflect Kron reduction for save circuit function */
				propertyValue[0] = String.format("%d", NPhases);
				propertyValue[8] = getRMatrix();
				propertyValue[9] = getXMatrix();
				propertyValue[10] = getCMatrix();

			} else {
				DSSGlobals.doSimpleMsg(String.format("Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.", getName(), NeutralConductor), 103);
			}

		} else {
			DSSGlobals.doSimpleMsg("Cannot perform Kron Reduction on a 1-phase LineCode: LineCode." + getName(), 103);
		}
	}

	public boolean isSymComponentsModel() {
		return SymComponentsModel;
	}

	public void setSymComponentsModel(boolean symComponentsModel) {
		SymComponentsModel = symComponentsModel;
	}

	public boolean isReduceByKron() {
		return ReduceByKron;
	}

	public void setReduceByKron(boolean reduceByKron) {
		ReduceByKron = reduceByKron;
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
		return BaseFrequency;
	}

	public void setBaseFrequency(double baseFrequency) {
		BaseFrequency = baseFrequency;
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
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	public double getFaultRate() {
		return FaultRate;
	}

	public void setFaultRate(double faultRate) {
		FaultRate = faultRate;
	}

	public double getPctPerm() {
		return PctPerm;
	}

	public void setPctPerm(double pctPerm) {
		PctPerm = pctPerm;
	}

	public double getHrsToRepair() {
		return HrsToRepair;
	}

	public void setHrsToRepair(double hrsToRepair) {
		HrsToRepair = hrsToRepair;
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

	public void setRho(double rho) {
		this.rho = rho;
	}

	public int getUnits() {
		return Units;
	}

	public void setUnits(int units) {
		Units = units;
	}


	// FIXME Private members in OpenDSS

	public int getNeutralConductor() {
		return NeutralConductor;
	}

	public void setNeutralConductor(int neutralConductor) {
		NeutralConductor = neutralConductor;
	}

}

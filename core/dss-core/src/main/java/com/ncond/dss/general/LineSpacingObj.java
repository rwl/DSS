package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.shared.LineUnits;

@Data
@EqualsAndHashCode(callSuper=true)
public class LineSpacingObj extends DSSObject {

	private int nConds;
	private int nPhases;
	private double[] X;
	private double[] Y;
	private int units;
	private boolean dataChanged;

	public LineSpacingObj(DSSClass parClass, String lineSpacingName) {
		super(parClass);

		setName(lineSpacingName.toLowerCase());
		objType = parClass.getClassType();

		dataChanged = true;
		X           = null;
		Y           = null;
		units       = LineUnits.UNITS_FT;

		setNWires(3);  // allocates terminals
		nPhases = 3;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < 5; i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		pw.close();
	}

	private String arrayString(double[] pf, int n) {
		// FIXME use StringBuffer
		String r = "[";
		if (n > 0)
			r = r + String.format("%-g", pf[0]);
		for (int i = 1; i < n; i++)
			r = r + String.format(",%-g", pf[i]);
		return r + "]";
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 2:
			return arrayString(X, nConds);
		case 3:
			return arrayString(Y, nConds);
		case 4:
			LineUnits.lineUnitsStr(units);
		default:
			// inherited parameters
			return super.getPropertyValue(index);
		}
	}

	public double getXCoord(int i) {
		return i < nConds ? X[i] : 0.0;
	}

	public double getYCoord(int i) {
		return i < nConds ? Y[i] : 0.0;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "3");
		setPropertyValue(1, "3");
		setPropertyValue(2, "0");
		setPropertyValue(3, "32");
		setPropertyValue(4, "ft");

		super.initPropertyValues(LineSpacing.NumPropsThisClass - 1);
	}

	public void setNWires(int value) {
		nConds = value;
		X = new double[nConds];
		Y = new double[nConds];
		units = LineUnits.UNITS_FT;
	}

	public int getNWires() {
		return nConds;
	}

}

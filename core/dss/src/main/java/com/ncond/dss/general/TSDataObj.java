package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSSClass;

@Getter @Setter
public class TSDataObj extends CableDataObj {

	private double diaShield;
	private double tapeLayer;
	private double tapeLap;

	public TSDataObj(DSSClass parClass, String TSDataName) {
		super(parClass, TSDataName);

		setName(TSDataName.toLowerCase());
		objType = parClass.getClassType();
		diaShield = -1.0;
		tapeLayer = -1.0;
		tapeLap = 20.0;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			pw.print("~ " + getParentClass().getPropertyName(i) + "=");
			switch (i) {
			case 0:
				pw.printf("%.6g", diaShield);
				break;
			case 1:
				pw.printf("%.6g", tapeLayer);
				break;
			case 2:
				pw.printf("%.2g", tapeLap);
				break;
			}
			pw.println();
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "-1");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "20.0");
		super.initPropertyValues(arrayOffset + TSData.NumPropsThisClass);
	}

}

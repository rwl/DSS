package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.GICTransformer;
import com.epri.dss.delivery.GICTransformerObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

public class GICTransformerObjImpl extends PDElementImpl implements GICTransformerObj {

	private double G1, G2;  // single G per phase (line rating)

    private int specType;

	public GICTransformerObjImpl(DSSClass parClass, String transformerName) {
		super(parClass);
		objType = parClass.getDSSClassType();
		setName(transformerName.toLowerCase());

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(2);  // force allocation of terminals and conductors

		setBus(2, getBus(1) + ".0");  // default to grounded
		isShunt = true;

		G1            = 10000.0;
		G2            = 10000.0;
		specType      = GICTransformer.SPEC_GSU;

		normAmps  = 0.0;
		emergAmps = 0.0;
		faultRate = 0.0;
		pctPerm   = 100.0;
		hrsToRepair = 0.0;

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		// nothing to do
	}

	@Override
	public void calcYPrim() {
		Complex value, value2;
		int i;

		CMatrix YPrimTemp;

		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrimShunt != null) YPrimShunt = null;
			YPrimShunt = new CMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();  // zero out YPrim
			YPrimShunt.clear();   // zero out YPrim
			YPrim.clear();
		}

		if (isShunt()) {
			YPrimTemp = YPrimShunt;
		} else {
			YPrimTemp = YPrimSeries;
		}

		// make sure randomMult is 1.0 if not solution mode MonteFault

		/* Now, put in Yprim matrix */

		/* If the fault is not on, the set zero conductance */

		switch (specType) {
		case GICTransformer.SPEC_GSU:

			value = new Complex(G1, 0.0);
			value2 = value.negate();
			for (i = 0; i < nPhases; i++) {
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i+nPhases, i+nPhases,value);
				YPrimTemp.setElemSym(i, i+nPhases, value2);
			}
			break;

		case GICTransformer.SPEC_AUTO:

			// terminals 1 and 2
			value = new Complex(G1, 0.0);
			value2 = value.negate();
			for (i = 0; i < nPhases; i++) {
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i+nPhases, i+nPhases, value);
				YPrimTemp.setElemSym(i, i+nPhases, value2);
			}
			// terminals 3 and 4
			value = new Complex(G2, 0.0);
			value2 = value.negate();
			for (i = 2*nPhases+1; i < 3*nPhases; i++) {
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i+nPhases, i+nPhases, value);
				YPrimTemp.setElemSym(i, i+nPhases, value2);
			}
			break;

		case GICTransformer.SPEC_YY:

			// terminals 1 and 2
			value = new Complex(G1, 0.0);
			value2 = value.negate();
			for (i = 0; i < nPhases; i++) {
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i+nPhases, i+nPhases, value);
				YPrimTemp.setElemSym(i, i+nPhases, value2);
			}
			// terminals 3 and 4
			value = new Complex(G2, 0.0);
			value2 = value.negate();
			for (i = 2*nPhases+1; i < 3*nPhases; i++) {
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i+nPhases, i+nPhases, value);
				YPrimTemp.setElemSym(i, i+nPhases, value2);
			}
			break;
		}

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();
		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i;

	    super.dumpProperties(f, complete);

	    DSSClass pc = getParentClass();

	    f.println("~ " + pc.getPropertyName()[0]+"="+getFirstBus());
	    f.println("~ " + pc.getPropertyName()[1]+"="+getNextBus());
	    f.println("~ " + pc.getPropertyName()[2]+"="+getNextBus());
	    f.println("~ " + pc.getPropertyName()[3]+"="+getNextBus());

	    f.println("~ " + pc.getPropertyName()[4]+"="+nPhases);
	    switch (specType) {
		case GICTransformer.SPEC_GSU:
			f.println("~ " + pc.getPropertyName()[6] + "= GSU");
			break;
		case GICTransformer.SPEC_AUTO:
			f.println("~ " + pc.getPropertyName()[6] + "= AUTO");
			break;
		case GICTransformer.SPEC_YY:
			f.println("~ " + pc.getPropertyName()[6] + "= YY");
			break;
	    }
	    f.println("~ " + pc.getPropertyName()[6]+"="+(1.0/G1));
	    f.println("~ " + pc.getPropertyName()[7]+"="+(1.0/G2));

	    for (i = GICTransformer.NumPropsThisClass; i < pc.getNumProperties(); i++)
	    	f.println("~ " + pc.getPropertyName()[i]+"="+getPropertyValue(i));
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, getBus(1));
		setPropertyValue(1, getBus(2));
		setPropertyValue(2, getBus(3));
		setPropertyValue(3, getBus(4));
		setPropertyValue(4, "3");
		setPropertyValue(5, "GSU");
		setPropertyValue(6, "0.0001");
		setPropertyValue(7, "0.0001");

		super.initPropertyValues(GICTransformer.NumPropsThisClass - 1);

		// Override Inherited Properties
		setPropertyValue(GICTransformer.NumPropsThisClass + 0, "0");  // normAmps
		setPropertyValue(GICTransformer.NumPropsThisClass + 1, "0");  // emergAmps
		setPropertyValue(GICTransformer.NumPropsThisClass + 2, "0");  // faultRate
		setPropertyValue(GICTransformer.NumPropsThisClass + 3, "0");  // pctPerm
		setPropertyValue(GICTransformer.NumPropsThisClass + 4, "0");  // hrsToRepair
	}

	@Override
	public String getPropertyValue(int index) {

		switch (index) {
		case 0:
			return getBus(1);
		case 1:
			return getBus(2);
		case 2:
			return getBus(3);
		case 3:
			return getBus(4);
		case 4:
			return String.format("%d", nPhases);
		case 6:
			return String.format("%.8g", 1.0 / G1);
		case 7:
			return String.format("%.8g", 1.0 / G2);
		default:
			return super.getPropertyValue(index);
		}
	}

	@Override
	public void makePosSequence() {
		if (nPhases != 1) {
			Parser.getInstance().setCmdString("Phases=1");
		    edit();
		}
		super.makePosSequence();
	}

	// FIXME: Private members in OpenDSS

	public double getG1() {
		return G1;
	}

	public void setG1(double g1) {
		G1 = g1;
	}

	public double getG2() {
		return G2;
	}

	public void setG2(double g2) {
		G2 = g2;
	}

	public int getSpecType() {
		return specType;
	}

	public void setSpecType(int type) {
		specType = type;
	}

}

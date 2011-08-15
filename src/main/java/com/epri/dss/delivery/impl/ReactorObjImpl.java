package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Reactor;
import com.epri.dss.delivery.ReactorObj;

public class ReactorObjImpl extends PDElementImpl implements ReactorObj {

	private double R, Rp, Gp, X, kVArRating, kVRating;
	/* If not null then overrides C */
	private double[] RMatrix, GMatrix, XMatrix, BMatrix;

	private int connection;  // 0 or 1 for wye (default) or delta, respectively
	private int specType;    // 1=kVAr, 2=r+jx, 3=r and x matrices

	private boolean isParallel;
	private boolean RpSpecified;

	public ReactorObjImpl(DSSClass parClass, String reactorName) {
		super(parClass);
		setName(reactorName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(2);   // force allocation of terminals and conductors

		setBus(2, (getBus(1) + ".0.0.0"));  // default to grounded wye   TODO Check zero based indexing

		isShunt = true;

		RMatrix = null;
		XMatrix = null;
		GMatrix = null;
		BMatrix = null;

		kVArRating = 100.0;
		kVRating   = 12.47;
		X          = Math.pow(kVRating, 2) * 1000.0 / kVArRating;
		R          = 0.0;
		Rp         = 0.0;  // indicates it has not been set to a proper value
		isParallel  = false;
		RpSpecified = false;
		connection  = 0;  // 0 or 1 for wye (default) or delta, respectively
		specType    = 1;  // 1=kVAr, 2=Cuf, 3=CMatrix
		normAmps    = kVArRating * DSSGlobals.SQRT3 / kVRating;
		emergAmps   = getNormAmps() * 1.35;
		faultRate   = 0.0005;
		pctPerm     = 100.0;
		hrsToRepair = 3.0;
		YOrder      = nTerms * nConds;
		recalcElementData();

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		double kVArPerPhase, phaseKV;
		int i;
		MutableInt checkError = new MutableInt();

		switch (specType) {
		case 1:  // kVAr
			kVArPerPhase = kVArRating / nPhases;
			switch (connection) {
			case 1:  // line-to-line
				phaseKV = kVRating;
				break;
			default:  //  line-to-neutral
				switch (nPhases) {
				case 2:
					phaseKV = kVRating / DSSGlobals.SQRT3;  // assume three phase system
					break;
				case 3:
					phaseKV = kVRating / DSSGlobals.SQRT3;
					break;
				default:
					phaseKV = kVRating;
					break;
				}
				break;
			}
			X = Math.pow(phaseKV, 2) * 1000.0 / kVArPerPhase;
			/* Leave r as specified */
			setNormAmps(kVArPerPhase / phaseKV);
			setEmergAmps(getNormAmps() * 1.35);
			break;
		case 2:  // r + jx
			// nothing to do
			break;
		case 3:  // matrices
			break;
		}

		if (RpSpecified && (Rp != 0.0)) {
			Gp = 1.0 / Rp;
		} else {
			Gp = 0.0;  // default to 0,0 if Rp=0;
		}

		if (isParallel && (specType == 3)) {

			GMatrix = (double[]) Utilities.resizeArray(GMatrix, nPhases * nPhases);
			BMatrix = (double[]) Utilities.resizeArray(BMatrix, nPhases * nPhases);

			/* Copy rMatrix to gMatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++)
				GMatrix[i] = RMatrix[i];
			MathUtil.ETKInvert(RMatrix, nPhases, checkError);
			if (checkError.intValue() > 0) {  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Error inverting R matrix for Reactor."+getName()+" - G is zeroed.", 232);
				for (i = 0; i < nPhases * nPhases; i++)
					GMatrix[i] = 0.0;
			}

			/* Copy xMatrix to bMatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++) {
				BMatrix[i] = -XMatrix[i];
				MathUtil.ETKInvert(BMatrix, nPhases, checkError);
				if (checkError.intValue() > 0) {
					DSSGlobals.getInstance().doSimpleMsg("Error inverting X matrix for Reactor."+getName()+" - B is zeroed.", 233);
					for (i = 0; i < nPhases * nPhases; i++)
						GMatrix[i] = 0.0;
				}
			}
		}
	}

	@Override
	public void calcYPrim() {
		DSSGlobals globals = DSSGlobals.getInstance();

		Complex value, value2;
		int i, j, idx;
		double freqMultiplier;
		Complex[] ZValues;
		CMatrix YPrimTemp, ZMatrix;

		// normally build only Yprim_Shunt, but if there are 2 terminals and
		// bus1 != bus2

		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			if (YPrimShunt != null) YPrimShunt = null;
			YPrimShunt = new CMatrixImpl(YOrder);
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear(); // zero out YPrim
			YPrimShunt.clear();  // zero out YPrim
			YPrim.clear();
		}

		if (isShunt()) {
			YPrimTemp = YPrimShunt;
		} else {
			YPrimTemp = YPrimSeries;
		}


		YPrimFreq = globals.getActiveCircuit().getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		/* Now, put in Yprim matrix */

		switch (specType) {
		case 1:  /* Some form of r and x specified */
			// adjust for frequency
			value = new Complex(R, X * freqMultiplier).invert();
			// add in rP value if specified
			if (RpSpecified)
				value = value.add(new Complex(Gp, 0.0));

			switch (connection) {
			case 1:  // line-line
				value2 = value.multiply(2.0);
				value = value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, value);
					YPrimTemp.setElemSym(i, i + nPhases, value.negate());
				}
				break;
			}
			break;
		case 2:  /* Some form of r and x specified */
			// adjust for frequency
			value = new Complex(R, X * freqMultiplier).invert();
			// add in rP value if specified
			if (RpSpecified)
				value = value.add(new Complex(Gp, 0.0));

			switch (connection) {
			case 1:  // line-line
				value2 = value.multiply(2.0);
				value = value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, value);
					YPrimTemp.setElemSym(i, i + nPhases, value.negate());
				}
				break;
			}
			break;
		case 3:  // Z matrix specified
			/* Compute Z matrix */

			/* Put in parallel r & l */
			if (isParallel) {  // build Z as a Y matrix

				for (i = 0; i < nPhases; i++) {
					for (j = 0; j < nPhases; j++) {
						idx = (j - 1) * nPhases + i;  // TODO Check zero based indexing
						value = new Complex(GMatrix[idx], BMatrix[idx] / freqMultiplier);
						YPrimTemp.setElement(i, j, value);
						YPrimTemp.setElement(i + nPhases, j + nPhases, value);
						YPrimTemp.setElemSym(i, j + nPhases, value.negate());
					}
				}
			} else {  // for series r and x
				ZMatrix = new CMatrixImpl(nPhases);
				ZValues = ZMatrix.asArray(nPhases);  // so we can populate array fast
				/* Put in series r & l */
				for (i = 0; i < nPhases * nPhases; i++) {
					// correct the impedances for frequency
					ZValues[i] = new Complex(RMatrix[i], XMatrix[i] * freqMultiplier);
				}

				ZMatrix.invert();  /* Invert in place - is now Y matrix */
				if (ZMatrix.getInvertError() > 0) {  /* If error, put in tiny series conductance */
					globals.doErrorMsg("ReactorObj.calcYPrim()", "Matrix inversion error for reactor \"" + getName() + "\"",
									"Invalid impedance specified. Replaced with tiny conductance.", 234);
					ZMatrix.clear();
					for (i = 0; i < nPhases; i++)
						ZMatrix.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));

					for (i = 0; i < nPhases; i++)
						for (j = 0; j < nPhases; j++) {
							value = ZMatrix.getElement(i, j);
							YPrimTemp.setElement(i, j, value);
							YPrimTemp.setElement(i + nPhases, j + nPhases, value);
							YPrimTemp.setElemSym(i, j + nPhases, value.negate());
						}

					ZMatrix = null;
				}
			}
			break;
		}

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		if (isShunt()) {
			if ((nPhases == 1) && (!globals.getActiveCircuit().isPositiveSequence())) {  // assume a neutral or grounding reactor; leave diagonal in the circuit
				for (i = 0; i < YOrder; i++)
					YPrimSeries.setElement(i, i, YPrimShunt.getElement(i, i));
			} else {
				for (i = 0; i < YOrder; i++)
					YPrimSeries.setElement(i, i, YPrimShunt.getElement(i, i).multiply(1.0e-10));
			}
		}

		YPrim.copyFrom(YPrimTemp);

		calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, j, k;

		super.dumpProperties(f, complete);

		for (k = 0; k < parentClass.getNumProperties(); k++) {
			switch (k) {
			case 6:
				if (RMatrix != null) {
					f.print(parentClass.getPropertyName()[k] + "= (");
					for (i = 0; i < nPhases; i++) {
						for (j = 0; j < i; j++)
							f.printf("%-.5g", RMatrix[(i - 1) * nPhases + j] + " ");
						if (i != nPhases)
							f.print("|");
					}
					f.println(")");
				}
				break;
			case 7:
				if (XMatrix != null) {
					f.print(parentClass.getPropertyName()[k] + "= (");
					for (i = 0; i < nPhases; i++) {
						for (j = 0; j < i; j++)
							f.printf("%-.5g", XMatrix[(i - 1) * nPhases + j] + " ");
						if (i != nPhases)
							f.print("|");
					}
					f.println(")");
				} else {
					f.println("~ " + parentClass.getPropertyName()[k] + "=" + getPropertyValue(k));
				}
				break;
			}
		}
	}

	@Override
	public void getLosses(double[] totalLosses, double[] loadLosses, double[] noLoadLosses) {
		Complex cTotalLosses, cNoLoadLosses, cLoadLosses;

		/* Only report no load losses if Rp defined and reactor is a shunt device;
		 * else do default behavior.
		 */

		if (RpSpecified && isShunt() && (Rp != 0.0)) {

			cTotalLosses = getLosses();  // side effect: computes iTerminal and vTerminal
			/* Compute losses in Rp branch from voltages across shunt element -- node to ground */
			cNoLoadLosses = Complex.ZERO;
			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
			for (int i = 0; i < nPhases; i++) {
				Complex V = sol.getNodeV()[nodeRef[i]];
				cNoLoadLosses = cNoLoadLosses.add(new Complex((Math.pow(V.getReal(), 2) + Math.pow(V.getImaginary(), 2)) / Rp, 0.0));  // V^2/Rp
			}

			if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence())
				cNoLoadLosses = cNoLoadLosses.multiply(3.0);
			cLoadLosses = cTotalLosses.subtract(cNoLoadLosses);  // subtract no load losses from total losses

			/* handle pass by reference */
			totalLosses[0] = cTotalLosses.getReal();
			totalLosses[1] = cTotalLosses.getImaginary();
			loadLosses[0] = cLoadLosses.getReal();
			loadLosses[1] = cLoadLosses.getImaginary();
			noLoadLosses[0] = cNoLoadLosses.getReal();
			noLoadLosses[1] = cNoLoadLosses.getImaginary();
		} else {
			/* do the default CktElement behaviors */
			super.getLosses(totalLosses, loadLosses, noLoadLosses);
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[1] = getBus(1);  // TODO Check zero based indexing
		propertyValue[2] = getBus(2);
		propertyValue[3] = "3";
		propertyValue[4] = "1200";
		propertyValue[5] = "12.47";
		propertyValue[6] = "wye";
		propertyValue[7] = "";
		propertyValue[8] = "";
		propertyValue[9] = "NO";  // parallel
		propertyValue[10] = "0";  // r series
		propertyValue[11] = String.format("%-.6g", X);  // X
		propertyValue[12] = "0";  // Rp

		super.initPropertyValues(Reactor.NumPropsThisClass);

		// override inherited properties
		propertyValue[Reactor.NumPropsThisClass + 1] = String.valueOf(getNormAmps());  // TODO Check zero based indexing
		propertyValue[Reactor.NumPropsThisClass + 2] = String.valueOf(getEmergAmps());
		propertyValue[Reactor.NumPropsThisClass + 3] = String.valueOf(getFaultRate());
		propertyValue[Reactor.NumPropsThisClass + 4] = String.valueOf(getPctPerm());
		propertyValue[Reactor.NumPropsThisClass + 5] = String.valueOf(getHrsToRepair());

		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String s = "";
		double kVArPerPhase, phaseKV, Rs, Rm;
		int i, j;

		if (nPhases > 1) {
			switch (specType) {
			case 1:  // kVAr
				kVArPerPhase = kVArRating / nPhases;
				if ((nPhases > 1) || (connection != 0)) {
					phaseKV = kVRating / DSSGlobals.SQRT3;
				} else {
					phaseKV = kVRating;
				}

				s = "Phases=1 " + String.format(" kV=%-.5g kvar=%-.5g", phaseKV, kVArPerPhase);
				/* Leave r as specified */
				break;
			case 2:  // r + jx
				s = "Phases=1 ";
				break;
			case 3:  // matrices
				s = "Phases=1 ";
				// r1
				Rs = 0.0;   // avg self
				for (i = 0; i < nPhases; i++)
					Rs = Rs + RMatrix[(i - 1) * nPhases + i];  // TODO Check zero based indexing
				Rs = Rs / nPhases;
				Rm = 0.0;   // avg mutual
				for (i = 1; i < nPhases; i++)
					for (j = i; j < nPhases; j++)
						Rm = Rm + RMatrix[(i - 1) * nPhases + j];
				Rm = Rm / (nPhases * (nPhases - 1.0) / 2.0);

				s = s + String.format(" R=%-.5g", (Rs - Rm));

				// x1
				Rs = 0.0;   // avg self
				for (i = 0; i < nPhases; i++)
					Rs = Rs + XMatrix[(i - 1) * nPhases + i];
				Rs = Rs / nPhases;
				Rm = 0.0;   // avg mutual
				for (i = 1; i < nPhases; i++)
					for (j = i; j < nPhases; j++)
						Rm = Rm + XMatrix[(i - 1) * nPhases + j];
				Rm = Rm / (nPhases * (nPhases - 1.0) / 2.0);

				s = s + String.format(" X=%-.5g", (Rs - Rm));
				break;
			}

			Parser.getInstance().setCmdString(s);
			edit();
		}

		super.makePosSequence();
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public double getRp() {
		return Rp;
	}

	public void setRp(double rp) {
		Rp = rp;
	}

	public double getGp() {
		return Gp;
	}

	public void setGp(double gp) {
		Gp = gp;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getKVArRating() {
		return kVArRating;
	}

	public void setKVArRating(double kvarrating) {
		this.kVArRating = kvarrating;
	}

	public double getKVRating() {
		return kVRating;
	}

	public void setKVRating(double kvrating) {
		this.kVRating = kvrating;
	}

	public double[] getRMatrix() {
		return RMatrix;
	}

	public void setRMatrix(double[] rmatrix) {
		RMatrix = rmatrix;
	}

	public double[] getGMatrix() {
		return GMatrix;
	}

	public void setGMatrix(double[] gmatrix) {
		GMatrix = gmatrix;
	}

	public double[] getXMatrix() {
		return XMatrix;
	}

	public void setXMatrix(double[] xmatrix) {
		XMatrix = xmatrix;
	}

	public double[] getBMatrix() {
		return BMatrix;
	}

	public void setBMatrix(double[] bmatrix) {
		BMatrix = bmatrix;
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection(int conn) {
		connection = conn;
	}

	public int getSpecType() {
		return specType;
	}

	public void setSpecType(int type) {
		specType = type;
	}

	public boolean isParallel() {
		return isParallel;
	}

	public void setParallel(boolean parallel) {
		isParallel = parallel;
	}

	public boolean isRpSpecified() {
		return RpSpecified;
	}

	public void setRpSpecified(boolean specified) {
		RpSpecified = specified;
	}

}

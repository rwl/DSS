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

	private double R, Rp, Gp, X, kvarrating, kvrating;
	/* If not null then overrides C */
	private double[] Rmatrix, Gmatrix, XMatrix, Bmatrix;

	private int Connection;  // 0 or 1 for wye (default) or delta, respectively
	private int SpecType;    // 1=kVAr, 2=r+jx, 3=r and x matrices

	private boolean IsParallel;
	private boolean RpSpecified;

	public ReactorObjImpl(DSSClass ParClass, String ReactorName) {
		super(ParClass);
		setName(ReactorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(2);   // force allocation of terminals and conductors

		setBus(2, (getBus(1) + ".0.0.0"));  // default to grounded wye   TODO Check zero based indexing

		this.IsShunt = true;

		this.Rmatrix = null;
		this.XMatrix = null;
		this.Gmatrix = null;
		this.Bmatrix = null;

		this.kvarrating = 100.0;
		this.kvrating   = 12.47;
		this.X          = Math.pow(kvrating, 2) * 1000.0 / this.kvarrating;
		this.R          = 0.0;
		this.Rp         = 0.0;  // indicates it has not been set to a proper value
		this.IsParallel  = false;
		this.RpSpecified = false;
		this.Connection  = 0;  // 0 or 1 for wye (default) or delta, respectively
		this.SpecType    = 1;  // 1=kVAr, 2=Cuf, 3=CMatrix
		this.NormAmps    = this.kvarrating * DSSGlobals.SQRT3 / this.kvrating;
		this.EmergAmps   = getNormAmps() * 1.35;
		this.FaultRate   = 0.0005;
		this.PctPerm     = 100.0;
		this.HrsToRepair = 3.0;
		this.YOrder      = this.nTerms * this.nConds;
		recalcElementData();

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		double KvarPerPhase, PhasekV;
		int i;
		MutableInt CheckError = new MutableInt();

		switch (SpecType) {
		case 1:  // kVAr
			KvarPerPhase = kvarrating / nPhases;
			switch (Connection) {
			case 1:  // line-to-line
				PhasekV = kvrating;
				break;
			default:  //  line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // assume three phase system
					break;
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
					break;
				default:
					PhasekV = kvrating;
					break;
				}
				break;
			}
			X = Math.pow(PhasekV, 2) * 1000.0 / KvarPerPhase;
			/* Leave r as specified */
			setNormAmps(KvarPerPhase / PhasekV);
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

		if (IsParallel && (SpecType == 3)) {

			Gmatrix = (double[]) Utilities.resizeArray(Gmatrix, nPhases * nPhases);
			Bmatrix = (double[]) Utilities.resizeArray(Bmatrix, nPhases * nPhases);

			/* Copy rMatrix to gMatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++)
				Gmatrix[i] = Rmatrix[i];
			MathUtil.ETKInvert(Rmatrix, nPhases, CheckError);
			if (CheckError.intValue() > 0) {  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Error inverting R matrix for Reactor."+getName()+" - G is zeroed.", 232);
				for (i = 0; i < nPhases * nPhases; i++)
					Gmatrix[i] = 0.0;
			}

			/* Copy xMatrix to bMatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++) {
				Bmatrix[i] = -XMatrix[i];
				MathUtil.ETKInvert(Bmatrix, nPhases, CheckError);
				if (CheckError.intValue() > 0) {
					DSSGlobals.getInstance().doSimpleMsg("Error inverting X matrix for Reactor."+getName()+" - B is zeroed.", 233);
					for (i = 0; i < nPhases * nPhases; i++)
						Gmatrix[i] = 0.0;
				}
			}
		}
	}

	@Override
	public void calcYPrim() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Complex Value, Value2;
		int i, j, idx;
		double FreqMultiplier;
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


		YPrimFreq = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier = YPrimFreq / baseFrequency;

		/* Now, put in Yprim matrix */

		switch (SpecType) {
		case 1:  /* Some form of r and x specified */
			// adjust for frequency
			Value = new Complex(R, X * FreqMultiplier).invert();
			// add in rP value if specified
			if (RpSpecified)
				Value = Value.add(new Complex(Gp, 0.0));

			switch (Connection) {
			case 1:  // line-line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, Value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value);  // elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, Value);
					YPrimTemp.setElemSym(i, i + nPhases, Value.negate());
				}
				break;
			}
			break;
		case 2:  /* Some form of r and x specified */
			// adjust for frequency
			Value = new Complex(R, X * FreqMultiplier).invert();
			// add in rP value if specified
			if (RpSpecified)
				Value = Value.add(new Complex(Gp, 0.0));

			switch (Connection) {
			case 1:  // line-line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, Value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value);  // elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, Value);
					YPrimTemp.setElemSym(i, i + nPhases, Value.negate());
				}
				break;
			}
			break;
		case 3:  // Z matrix specified
			/* Compute Z matrix */

			/* Put in parallel r & l */
			if (IsParallel) {  // build Z as a Y matrix

				for (i = 0; i < nPhases; i++) {
					for (j = 0; j < nPhases; j++) {
						idx = (j - 1) * nPhases + i;  // TODO Check zero based indexing
						Value = new Complex(Gmatrix[idx], Bmatrix[idx] / FreqMultiplier);
						YPrimTemp.setElement(i, j, Value);
						YPrimTemp.setElement(i + nPhases, j + nPhases, Value);
						YPrimTemp.setElemSym(i, j + nPhases, Value.negate());
					}
				}
			} else {  // for series r and x
				ZMatrix = new CMatrixImpl(nPhases);
				ZValues = ZMatrix.asArray(nPhases);  // so we can populate array fast
				/* Put in series r & l */
				for (i = 0; i < nPhases * nPhases; i++) {
					// correct the impedances for frequency
					ZValues[i] = new Complex(Rmatrix[i], XMatrix[i] * FreqMultiplier);
				}

				ZMatrix.invert();  /* Invert in place - is now Y matrix */
				if (ZMatrix.getInvertError() > 0) {  /* If error, put in tiny series conductance */
					Globals.doErrorMsg("ReactorObj.calcYPrim()", "Matrix inversion error for reactor \"" + getName() + "\"",
									"Invalid impedance specified. Replaced with tiny conductance.", 234);
					ZMatrix.clear();
					for (i = 0; i < nPhases; i++)
						ZMatrix.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));

					for (i = 0; i < nPhases; i++)
						for (j = 0; j < nPhases; j++) {
							Value = ZMatrix.getElement(i, j);
							YPrimTemp.setElement(i, j, Value);
							YPrimTemp.setElement(i + nPhases, j + nPhases, Value);
							YPrimTemp.setElemSym(i, j + nPhases, Value.negate());
						}

					ZMatrix = null;
				}
			}
			break;
		}

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		if (isShunt()) {
			if ((nPhases == 1) && (!Globals.getActiveCircuit().isPositiveSequence())) {  // assume a neutral or grounding reactor; leave diagonal in the circuit
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
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, j, k;

		super.dumpProperties(F, Complete);

		for (k = 0; k < ParentClass.getNumProperties(); k++) {
			switch (k) {
			case 6:
				if (Rmatrix != null) {
					F.print(ParentClass.getPropertyName()[k] + "= (");
					for (i = 0; i < nPhases; i++) {
						for (j = 0; j < i; j++)
							F.printf("%-.5g", Rmatrix[(i - 1) * nPhases + j] + " ");
						if (i != nPhases)
							F.print("|");
					}
					F.println(")");
				}
				break;
			case 7:
				if (XMatrix != null) {
					F.print(ParentClass.getPropertyName()[k] + "= (");
					for (i = 0; i < nPhases; i++) {
						for (j = 0; j < i; j++)
							F.printf("%-.5g", XMatrix[(i - 1) * nPhases + j] + " ");
						if (i != nPhases)
							F.print("|");
					}
					F.println(")");
				} else {
					F.println("~ " + ParentClass.getPropertyName()[k] + "=" + getPropertyValue(k));
				}
				break;
			}
		}
	}

	@Override
	public void getLosses(double[] TotalLosses, double[] LoadLosses, double[] NoLoadLosses) {
		Complex totLosses, noLoadLosses, loadLosses;

		/* Only report no load losses if Rp defined and reactor is a shunt device;
		 * else do default behavior.
		 */

		if (RpSpecified && isShunt() && (Rp != 0.0)) {

			totLosses = getLosses();  // side effect: computes iTerminal and vTerminal
			/* Compute losses in Rp branch from voltages across shunt element -- node to ground */
			noLoadLosses = Complex.ZERO;
			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
			for (int i = 0; i < nPhases; i++) {
				Complex V = sol.getNodeV()[nodeRef[i]];
				noLoadLosses = noLoadLosses.add(new Complex((Math.pow(V.getReal(), 2) + Math.pow(V.getImaginary(), 2)) / Rp, 0.0));  // V^2/Rp
			}

			if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence())
				noLoadLosses = noLoadLosses.multiply(3.0);
			loadLosses = totLosses.subtract(noLoadLosses);  // subtract no load losses from total losses

			/* handle pass by reference */
			TotalLosses[0] = totLosses.getReal();
			TotalLosses[1] = totLosses.getImaginary();
			LoadLosses[0] = loadLosses.getReal();
			LoadLosses[1] = loadLosses.getImaginary();
			NoLoadLosses[0] = noLoadLosses.getReal();
			NoLoadLosses[1] = noLoadLosses.getImaginary();
		} else {
			/* do the default CktElement behaviors */
			super.getLosses(TotalLosses, LoadLosses, NoLoadLosses);
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[1] = getBus(1);  // TODO Check zero based indexing
		PropertyValue[2] = getBus(2);
		PropertyValue[3] = "3";
		PropertyValue[4] = "1200";
		PropertyValue[5] = "12.47";
		PropertyValue[6] = "wye";
		PropertyValue[7] = "";
		PropertyValue[8] = "";
		PropertyValue[9] = "NO";  // parallel
		PropertyValue[10] = "0";  // r series
		PropertyValue[11] = String.format("%-.6g", X);  // X
		PropertyValue[12] = "0";  // Rp

		super.initPropertyValues(Reactor.NumPropsThisClass);

		// override inherited properties
		PropertyValue[Reactor.NumPropsThisClass + 1] = String.valueOf(getNormAmps());  // TODO Check zero based indexing
		PropertyValue[Reactor.NumPropsThisClass + 2] = String.valueOf(getEmergAmps());
		PropertyValue[Reactor.NumPropsThisClass + 3] = String.valueOf(getFaultRate());
		PropertyValue[Reactor.NumPropsThisClass + 4] = String.valueOf(getPctPerm());
		PropertyValue[Reactor.NumPropsThisClass + 5] = String.valueOf(getHrsToRepair());

		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String S = null;
		double kvarPerPhase, PhasekV, Rs, Rm;
		int i, j;

		if (nPhases > 1) {
			switch (SpecType) {
			case 1:  // kVAr
				kvarPerPhase = kvarrating / nPhases;
				if ((nPhases > 1) || (Connection != 0)) {
					PhasekV = kvrating / DSSGlobals.SQRT3;
				} else {
					PhasekV = kvrating;
				}

				S = "Phases=1 " + String.format(" kV=%-.5g kvar=%-.5g", PhasekV, kvarPerPhase);
				/* Leave r as specified */
				break;
			case 2:  // r + jx
				S = "Phases=1 ";
				break;
			case 3:  // matrices
				S = "Phases=1 ";
				// r1
				Rs = 0.0;   // avg self
				for (i = 0; i < nPhases; i++)
					Rs = Rs + Rmatrix[(i - 1) * nPhases + i];  // TODO Check zero based indexing
				Rs = Rs / nPhases;
				Rm = 0.0;   // avg mutual
				for (i = 1; i < nPhases; i++)
					for (j = i; j < nPhases; j++)
						Rm = Rm + Rmatrix[(i - 1) * nPhases + j];
				Rm = Rm / (nPhases * (nPhases - 1.0) / 2.0);

				S = S + String.format(" R=%-.5g", (Rs - Rm));

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

				S = S + String.format(" X=%-.5g", (Rs - Rm));
				break;
			}

			Parser.getInstance().setCmdString(S);
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

	public double getKvarrating() {
		return kvarrating;
	}

	public void setKvarrating(double kvarrating) {
		this.kvarrating = kvarrating;
	}

	public double getKvrating() {
		return kvrating;
	}

	public void setKvrating(double kvrating) {
		this.kvrating = kvrating;
	}

	public double[] getRmatrix() {
		return Rmatrix;
	}

	public void setRmatrix(double[] rmatrix) {
		Rmatrix = rmatrix;
	}

	public double[] getGmatrix() {
		return Gmatrix;
	}

	public void setGmatrix(double[] gmatrix) {
		Gmatrix = gmatrix;
	}

	public double[] getXMatrix() {
		return XMatrix;
	}

	public void setXMatrix(double[] xMatrix) {
		XMatrix = xMatrix;
	}

	public double[] getBmatrix() {
		return Bmatrix;
	}

	public void setBmatrix(double[] bmatrix) {
		Bmatrix = bmatrix;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public int getSpecType() {
		return SpecType;
	}

	public void setSpecType(int specType) {
		SpecType = specType;
	}

	public boolean isIsParallel() {
		return IsParallel;
	}

	public void setIsParallel(boolean isParallel) {
		IsParallel = isParallel;
	}

	public boolean isRpSpecified() {
		return RpSpecified;
	}

	public void setRpSpecified(boolean rpSpecified) {
		RpSpecified = rpSpecified;
	}

}

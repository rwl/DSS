package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

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
	private int SpecType;   // 1=kvar, 2=R+jX, 3=R and X matrices

	private boolean IsParallel;
	private boolean RpSpecified;

	public ReactorObjImpl(DSSClass ParClass, String ReactorName) {
		super(ParClass);
		setName(ReactorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();
		
		this.nPhases = 3;  // Directly set conds and phases
		this.nConds = 3;
		this.nTerms = 2;  // Force allocation of terminals and conductors

		setBus(2, (getBus(1) + ".0.0.0"));  // Default to grounded wye   TODO Check zero based indexing

		setShunt(true);

		this.Rmatrix = null;
		this.XMatrix = null;
		this.Gmatrix = null;
		this.Bmatrix = null;

		this.kvarrating = 100.0;
		this.kvrating   = 12.47;
		this.X          = Math.pow(kvrating, 2) * 1000.0 / this.kvarrating;
		this.R          = 0.0;
		this.Rp         = 0.0;  // Indicates it has not been set to a proper value
		this.IsParallel  = false;
		this.RpSpecified = false;
		this.Connection  = 0;   // 0 or 1 for wye (default) or delta, respectively
		this.SpecType    = 1; // 1=kvar, 2=Cuf, 3=Cmatrix
		setNormAmps(this.kvarrating * DSSGlobals.SQRT3 / this.kvrating);
		setEmergAmps(getNormAmps() * 1.35);
		setFaultRate(0.0005);
		setPctPerm(100.0);
		setHrsToRepair(3.0);
		this.Yorder      = this.nTerms * this.nConds;
		recalcElementData();

		initPropertyValues(0);
	}
	
	@Override
	public void recalcElementData() {
		double KvarPerPhase, PhasekV;
		int i, CheckError;

		switch (SpecType) {
		case 1:  // kvar
			KvarPerPhase = kvarrating / nPhases;
			switch (Connection) {
			case 1:  // Line-to-Line
				PhasekV = kvrating;
			default:  //  line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // Assume three phase system
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
				default:
					PhasekV = kvrating;
				}
			}
			X = Math.pow(PhasekV, 2) * 1000.0 / KvarPerPhase;
			/* Leave R as specified */
			setNormAmps(KvarPerPhase / PhasekV);
			setEmergAmps(getNormAmps() * 1.35);
		case 2:  // R + j X
			// Nothing to do
		case 3:  // Matrices
			
		}

		if (RpSpecified && (Rp != 0.0)) {
			Gp = 1.0 / Rp;
		} else {
			Gp = 0.0;  // default to 0,0 if Rp=0;
		}
			
		if (IsParallel && (SpecType == 3)) {

			Gmatrix = (double[]) Utilities.resizeArray(Gmatrix, nPhases * nPhases);
			Bmatrix = (double[]) Utilities.resizeArray(Bmatrix, nPhases * nPhases);

			/* Copy Rmatrix to Gmatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++) 
				Gmatrix[i] = Rmatrix[i];
			MathUtil.ETKInvert(Rmatrix, nPhases, CheckError);
			if (CheckError > 0) {  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Error inverting R Matrix for Reactor."+getName()+" - G is zeroed.", 232);
				for (i = 0; i < nPhases * nPhases; i++)
					Gmatrix[i] = 0.0;
			}

			/* Copy Xmatrix to Bmatrix and invert */
			for (i = 0; i < nPhases * nPhases; i++) {
				Bmatrix[i] = -XMatrix[i];
				MathUtil.ETKInvert(Bmatrix, nPhases, CheckError);
				if (CheckError > 0) {
					DSSGlobals.getInstance().doSimpleMsg("Error inverting X Matrix for Reactor."+getName()+" - B is zeroed.", 233);
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

		// Normally build only Yprim Shunt, but if there are 2 terminals and
		// Bus1 <> Bus 2

		if (isYprimInvalid()) {  // Reallocate YPrim if something has invalidated old allocation
			if (YPrim_Shunt != null) YPrim_Shunt = null;
			YPrim_Shunt = new CMatrixImpl(Yorder);
			if (Yprim_Series != null) Yprim_Series = null;
			Yprim_Series = new CMatrixImpl(Yorder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Series.clear(); // zero out YPrim
			YPrim_Shunt.clear(); // zero out YPrim
			YPrim.clear();
		}

		if (isShunt()) {
			YPrimTemp = YPrim_Shunt;
		} else {
			YPrimTemp = YPrim_Series;
		}


		YprimFreq = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;

		/* Now, Put in Yprim matrix */

		switch (SpecType) {
		case 1:  /* Some form of R and X specified */
			// Adjust for frequency
			Value = new Complex(R, X * FreqMultiplier).invert();
			// Add in Rp Value if specified
			if (RpSpecified)
				Value = Value.add(new Complex(Gp, 0.0));

			switch (Connection) {
			case 1:  // Line-Line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, Value);
					}
					// Remainder of the matrix is all zero
				}
			default:  // Wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value);     // Elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, Value);
					YPrimTemp.setElemSym(i, i + nPhases, Value.negate());
				}
			}
		case 2:  /* Some form of R and X specified */
			// Adjust for frequency
			Value = new Complex(R, X * FreqMultiplier).invert();
			// Add in Rp Value if specified
			if (RpSpecified)
				Value = Value.add(new Complex(Gp, 0.0));

			switch (Connection) {
			case 1:  // Line-Line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YPrimTemp.setElemSym(i, j, Value);
					}
					// Remainder of the matrix is all zero
				}
			default:  // Wye
				for (i = 0; i < nPhases; i++) {
					YPrimTemp.setElement(i, i, Value);     // Elements are only on the diagonals
					YPrimTemp.setElement(i + nPhases, i + nPhases, Value);
					YPrimTemp.setElemSym(i, i + nPhases, Value.negate());
				}
			}
		case 3:  // Z matrix specified
			/* Compute Z matrix */

			/* Put in Parallel R & L */
			if (IsParallel) {  // Build Z as a Y Matrix

				for (i = 0; i < nPhases; i++) {
					for (j = 0; j < nPhases; j++) {
						idx = (j - 1) * nPhases + i;  // TODO Check zero based indexing
						Value = new Complex(Gmatrix[idx], Bmatrix[idx] / FreqMultiplier);
						YPrimTemp.setElement(i, j, Value);
						YPrimTemp.setElement(i + nPhases, j + nPhases, Value);
						YPrimTemp.setElemSym(i, j + nPhases, Value.negate());
					}
				}
			} else {  // For Series R and X 
				ZMatrix = new CMatrixImpl(nPhases);
				ZValues = ZMatrix.asArray(nPhases);  // So we can stuff array fast
				/* Put in Series R & L */
				for (i = 0; i < nPhases * nPhases; i++) {
					// Correct the impedances for frequency
					ZValues[i] = new Complex(Rmatrix[i], XMatrix[i] * FreqMultiplier);
				}

				ZMatrix.invert();  /* Invert in place - is now Ymatrix */
				if (ZMatrix.getInvertError() > 0) {  /* If error, put in tiny series conductance */
					Globals.doErrorMsg("ReactorObj.calcYPrim()", "Matrix Inversion Error for Reactor \"" + getName() + "\"",
									"Invalid impedance specified. Replaced with tiny conductance.", 234);
					ZMatrix.clear();
					for (i = 0; i < nPhases; i++) {
						ZMatrix.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));
					}

					for (i = 0; i < nPhases; i++) {
						for (j = 0; j < nPhases; j++) {
							Value = ZMatrix.getElement(i, j);
							YPrimTemp.setElement(i, j, Value);
							YPrimTemp.setElement(i + nPhases, j + nPhases, Value);
							YPrimTemp.setElemSym(i, j + nPhases, Value.negate());
						}
					}

					ZMatrix = null;
				}
			}
		}

		// Set YPrim_Series based on diagonals of YPrim_shunt  so that CalcVoltages doesn't fail
		if (isShunt()) {
			if ((nPhases == 1) && (!Globals.getActiveCircuit().isPositiveSequence())) {  // assume a neutral or grounding reactor; Leave diagonal in the circuit
				for (i = 0; i < Yorder; i++) 
					YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i));
			} else {
				for (i = 0; i < Yorder; i++)
					YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));
			}
		}

		YPrim.copyFrom(YPrimTemp);
		/* Don't null YPrimTemp - It just points to an existing complex matrix */

		calcYPrim();

		setYprimInvalid(false);
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
			}
		}
	}
	
	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses, Complex NoLoadLosses) {

		/* Only report No Load Losses if Rp defined and Reactor is a shunt device;
		 * else do default behavior. 
		 */

		if (RpSpecified && isShunt() && (Rp != 0.0)) {

			TotalLosses = getLosses();  // Side effect: computes Iterminal and Vterminal
			/* Compute losses in Rp Branch from voltages across shunt element -- node to ground */
			NoLoadLosses = Complex.ZERO;
			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
			for (int i = 0; i < nPhases; i++) {
				Complex V = sol.getNodeV()[NodeRef[i]];
				NoLoadLosses = NoLoadLosses.add(new Complex((Math.pow(V.getReal(), 2) + Math.pow(V.getImaginary(), 2)) / Rp, 0.0));  // V^2/Rp
			}

			if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence())
				NoLoadLosses = NoLoadLosses.multiply(3.0);
			LoadLosses = TotalLosses.subtract(NoLoadLosses);  // Subtract no load losses from total losses

		} else {
			super.getLosses();  /* do the default Cktelement behaviors */
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
		PropertyValue[9] = "NO";  // Parallel
		PropertyValue[10] = "0";  // R series
		PropertyValue[11] = String.format("%-.6g", X);  // X
		PropertyValue[12] = "0";  //Rp

		super.initPropertyValues(Reactor.NumPropsThisClass);

		// Override inherited properties
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
			case 1:  // kvar
				kvarPerPhase = kvarrating / nPhases;
				if ((nPhases > 1) || (Connection != 0)) {
					PhasekV = kvrating / DSSGlobals.SQRT3;
				} else {
					PhasekV = kvrating;
				}

				S = "Phases=1 " + String.format(" kV=%-.5g kvar=%-.5g", PhasekV, kvarPerPhase);
				/* Leave R as specified */
			case 2:  // R + j X
				S = "Phases=1 ";
			case 3:  // Matrices
				S = "Phases=1 ";
				// R1
				Rs = 0.0;   // Avg Self
				for (i = 0; i < nPhases; i++) 
					Rs = Rs + Rmatrix[(i - 1) * nPhases + i];  // TODO Check zero based indexing
				Rs = Rs / nPhases;
				Rm = 0.0;   // Avg mutual
				for (i = 1; i < nPhases; i++) 
					for (j = i; j < nPhases; j++) 
						Rm = Rm + Rmatrix[(i - 1) * nPhases + j];
				Rm = Rm / (nPhases * (nPhases - 1.0) / 2.0);

				S = S + String.format(" R=%-.5g", (Rs - Rm));

				// X1
				Rs = 0.0;   // Avg Self
				for (i = 0; i < nPhases; i++)
					Rs = Rs + XMatrix[(i - 1) * nPhases + i];
				Rs = Rs / nPhases;
				Rm = 0.0;   // Avg mutual
				for (i = 1; i < nPhases; i++) 
					for (j = i; j < nPhases; j++) 
						Rm = Rm + XMatrix[(i - 1) * nPhases + j];
				Rm = Rm / (nPhases * (nPhases - 1.0) / 2.0);

				S = S + String.format(" X=%-.5g", (Rs - Rm));
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

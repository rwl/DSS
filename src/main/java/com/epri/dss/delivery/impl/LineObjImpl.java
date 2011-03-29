package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Line;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineCodeObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.CMatrix;

public class LineObjImpl extends PDElementImpl implements LineObj {
	
	/* keep track of last frequency computed for geometry */
	private double ZFrequency; 
	private int LineCodeUnits;
	private double UnitsConvert; // conversion factor
	private LineGeometryObj LineGeometryObj;
	private LineSpacingObj LineSpacingObj;
	private WireDataObj[] WireData;
	private boolean rhoSpecified;
	private boolean LineCodeSpecified;
	private int EarthModel;
	
	protected CMatrix Zinv;
	
	
	/* Base Frequency Series Z matrix  per unit length */
	protected CMatrix Z;   
	protected CMatrix Yc;

	protected double R1;
	protected double X1;
	protected double R0;
	protected double X0;
	protected double C1;
	protected double C0;
	protected double Len;
	protected int LengthUnits;
	protected double Rg, Xg, KXg, rho;
	protected double GeneralPlotQuantity;  // For general circuit plotting
	protected String CondCode;
	protected String GeometryCode;
	protected String SpacingCode;
	protected boolean GeometrySpecified;
	protected boolean SpacingSpecified;
	protected boolean SymComponentsChanged;
	protected boolean SymComponentsModel;
	protected boolean IsSwitch;

	public LineObjImpl(DSSClass ParClass, String LineName) {
		super(ParClass);
		setName(LineName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();  // DSSObjType + LINESECTION; // in both PDElement list and Linesection lists

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds = 3;
		this.nTerms = 2;  // Force allocation of terminals and conductors
		this.IsSwitch = false;
		this.R1 = 0.0580;  //ohms per 1000 ft
		this.X1 = 0.1206;
		this.R0 = 0.1784;
		this.X0 = 0.4047;
		this.C1 = 3.4e-9;  // nf per 1000ft
		this.C0 = 1.6e-9;
		this.Len = 1.0;   // 1 kFt
		this.Z    = null;
		this.Zinv = null;
		this.Yc   = null;
		this.CondCode = "";

		this.Rg = 0.01805;    //ohms per 1000 ft
		this.Xg = 0.155081;
		this.rho = 100.0;
		this.KXg = this.Xg / Math.log(658.5 * Math.sqrt(this.rho / this.BaseFrequency));
		this.rhoSpecified = false;

		/*Basefrequency = 60.0;*/  // set in base class
		setNormAmps(400.0);
		setEmergAmps(600.0);
		setPctPerm(20.0);
		setFaultRate(0.1);
		setHrsToRepair(3.0);

		this.SymComponentsChanged = false;
		this.SymComponentsModel = true;

		this.GeometrySpecified = false;
		this.GeometryCode      = "";
		this.LengthUnits       = LineUnits.UNITS_NONE; // Assume everything matches
		this.UnitsConvert      = 1.0;
		this.LineCodeUnits     = LineUnits.UNITS_NONE;
		this.LineCodeSpecified = false;
		this.EarthModel        = DSSGlobals.getInstance().getDefaultEarthModel();

		this.SpacingSpecified = false;
		this.LineSpacingObj = null;
		this.WireData = null;
		this.SpacingCode = "";

		this.ZFrequency = -1.0; // indicate Z not computed.
		this.LineGeometryObj = null;

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}
	
	public void fetchLineCode(String Code) {
		
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (LineImpl.getLineCodeClass() == null)
			LineImpl.setLineCodeClass((LineCode) Globals.getDSSClassList().get(Globals.getClassNames().find("linecode")));

		if (LineImpl.getLineCodeClass().setActive(Code)) {

			LineCodeObj lc = (LineCodeObj) LineImpl.getLineCodeClass().getActiveObj();

			CondCode = Code.toLowerCase();

			// Frequency compensation takes place in calcYPrim.
			BaseFrequency = lc.getBaseFrequency();
			/* Copy impedances from line code, but do not recalc because symmetrical
			 * component z's may not match what's in matrix
			 */
			if (lc.isSymComponentsModel()) {
				R1 = lc.getR1();
				X1 = lc.getX1();
				R0 = lc.getR0();
				X0 = lc.getX0();
				C1 = lc.getC1();
				C0 = lc.getC0();
				SymComponentsModel = true;
			} else {
				SymComponentsModel = false;
			}

			// Earth return impedances used to compensate for frequency
			Rg  = lc.getRg();
			Xg  = lc.getXg();
			rho = lc.getRho();
			KXg = Xg / Math.log(658.5 * Math.sqrt(rho / BaseFrequency));

			LineCodeUnits =  lc.getUnits();
			LineCodeSpecified = true;

			UnitsConvert = LineUnits.convertLineUnits(LineCodeUnits, LengthUnits);

			setNormAmps(lc.getNormAmps());
			setEmergAmps(lc.getEmergAmps());
			setFaultRate(lc.getFaultRate());
			setPctPerm(lc.getPctPerm());
			setHrsToRepair(lc.getHrsToRepair());
			updatePDProperties();

			if (getNPhases() !=  lc.getNPhases()) {
				if (Z != null) Z = null;
				if (Zinv != null) Zinv = null;

				if (Yc != null) Yc = null;


				setNPhases(lc.getNPhases());
				// For a line, nphases = ncond, for now
				Z    = new CMatrixImpl(nPhases);
				Zinv = new CMatrixImpl(nPhases);
				Yc   = new CMatrixImpl(nPhases);
			}

			if (!SymComponentsModel) {
				// Copy matrices
				Z.copyFrom(lc.getZ());
				/*Zinv.copyFrom(lc.getZinv());*/  // no need to copy Zinv
				Yc.copyFrom(lc.getYC());	
			} else {
				recalcElementData();  // Compute matrices
			}

			nConds = nPhases;  // Force Reallocation of terminal info
			Yorder = nConds * nTerms;
			//setYprimInvalid(true);  // set in Edit; this is redundant
		} else {
			Globals.doSimpleMsg("Line Code:" + Code + " not found.", 180);
		}
	}
	
	public void fetchGeometryCode(String Code) {
		
	}
	
	public void fetchLineSpacing(String Code) {
		
	}
	
	public void fetchWireList(String Code) {
		
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm, Ys, Ym, Ztemp;
		double Yc1, Yc0, OneThird;
		Complex GammaL, ExpP, ExpM, Exp2P, Exp2M, SinhGL, Tanh2GL;
		
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;
		if (Yc != null) Yc = null;

		// For a line, nphases = ncond, for now
		Z    = new CMatrixImpl(getNPhases());
		Zinv = new CMatrixImpl(getNPhases());
		Yc   = new CMatrixImpl(getNPhases());

		OneThird = 1.0 / 3.0;  // Do this to get more precision in next few statements

		/* Only time this is called is if symmetrical components are specified */

		Ztemp = new Complex(R1, X1).multiply(2.0);
		/* Handle special case for 1-phase line and/or pos seq model */
		if ((getNPhases() == 1) || ckt.isPositiveSequence()) {
			// long-line equivalent PI, but only for CktModel=Positive
			if (ckt.isPositiveSequence() && (C1 > 0)) {
				// nominal PI parameters per unit length
				Zs = new Complex(R1, X1);
				Ys = new Complex(0.0, DSSGlobals.TwoPi * BaseFrequency * C1);
				// apply the long-line correction to obtain Zm and Ym
				GammaL = Zs.multiply(Ys).sqrt();
				GammaL = GammaL.multiply(Len);
				ExpP = new Complex(Math.cos(GammaL.getImaginary()), Math.sin(GammaL.getImaginary())).multiply(Math.exp(GammaL.getReal()));
				Exp2P = new Complex(Math.cos(0.5 * GammaL.getImaginary()), Math.sin(0.5 * GammaL.getImaginary())).multiply(Math.exp(0.5 * GammaL.getReal()));
				ExpM = ExpP.invert();
				Exp2M = Exp2P.invert();
				SinhGL = ExpP.subtract(ExpM).multiply(0.5);
				Tanh2GL = Exp2P.subtract(Exp2M).divide(Exp2P.add(Exp2M));
				Zm = Zs.multiply(Len).multiply(SinhGL).divide(GammaL);
				Ym = Ys.multiply(Len).multiply(Tanh2GL).divide(GammaL.multiply(0.5));
				// rely on this function being called only once, unless R1, X1, or C1 changes
				R1 = Zm.getReal() / Len;
				X1 = Zm.getImaginary() / Len;
				C1 = Ym.getImaginary() / Len / DSSGlobals.TwoPi / BaseFrequency;
			}
			// zero sequence the same as positive sequence
			R0 = R1;
			X0 = X1;
			C0 = C1;
		}
		Zs = Ztemp.add(new Complex(R0, X0)).multiply(OneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(OneThird);

		Yc1 = DSSGlobals.TwoPi * BaseFrequency * C1;
		Yc0 = DSSGlobals.TwoPi * BaseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add(new Complex(0.0, Yc0)).multiply(OneThird);
		Ym = new Complex(0.0, Yc0).subtract(new Complex(0.0, Yc1)).multiply(OneThird);

		for (int i = 0; i < getNPhases(); i++) {
			Z.setElement(i, i, Zs);
			Yc.setElement(i, i, Ys);
			for (int j = 0; j < i; j++) {  // TODO Check zero based indexing
				Z.setElemSym(i, j, Zm);
				Yc.setElemSym(i, j, Ym);
			}
		}

		SymComponentsChanged = false;
	}
	
	@Override
	public void calcYPrim() {

		Complex Value;
		Complex[] ZinvValues;
		Complex[] ZValues;
		Complex[] YValues;

		double XgMod;
		int k, Norder = 0;

		double FreqMultiplier = 1.0;
		double LengthMultiplier = 1.0;
		
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		if (SymComponentsChanged)
			recalcElementData();

		clearYPrim();

		// Build Series YPrim
		CMatrix Y = YPrim_Series;

		/* Build Zmatrix */
		if (GeometrySpecified) {

			makeZFromGeometry(ckt.getSolution().getFrequency());  // Includes length in proper units
			if (Globals.isSolutionAbort())
				return;

		} else if (SpacingSpecified) {

			makeZFromSpacing(ckt.getSolution().getFrequency());
			if (Globals.isSolutionAbort())
				return;

		} else {  // Z is from line code or specified in line data

			LengthMultiplier = Len / UnitsConvert;   // convert to per unit length
			YprimFreq        = ckt.getSolution().getFrequency();
			FreqMultiplier   = YprimFreq / BaseFrequency;

			/* Put in Series RL */
			ZValues    = Z.asArray(Norder);
			ZinvValues = Zinv.asArray(Norder);
			// Correct the impedances for length and frequency
			// Rg increases with frequency
			// Xg modified by ln of sqrt(1/f)
			if (Xg != 0.0) {
				XgMod =  0.5 * KXg * Math.log(FreqMultiplier);
			} else {
				XgMod = 0.0;
			}
			
			for (int i = 0; i < Norder * Norder; i++) {
				ZinvValues[i] = new Complex((ZValues[i].getReal() + Rg * (FreqMultiplier - 1.0) ) * LengthMultiplier,
						(ZValues[i].getImaginary() - XgMod) * LengthMultiplier * FreqMultiplier);
			}

			Zinv.invert();  /* Invert in place */
			if (Zinv.getInvertError() > 0) {
				/* If error, put in tiny series conductance */
				// TEMc - shut this up for the CDPSM connectivity profile test, or whenever else it gets annoying
				Globals.doErrorMsg("LineObj.calcYPrim", "Matrix Inversion Error for Line \"" + getName() + "\"",
							"Invalid impedance specified. Replaced with tiny conductance.", 183);
				Zinv.clear();
				for (int i = 0; i < getNPhases(); i++) {
					Zinv.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));
				}
			} else {
				/* Now, Put in Yprim_Series matrix */
				for (int i = 0; i < getNPhases(); i++) {
					for (int j = 0; j < getNPhases(); j++) {
						Value = Zinv.getElement(i, j);
						YPrim_Series.setElement(i, j, Value);
						YPrim_Series.setElement(i + getNPhases(), j + getNPhases(), Value);
						Value = Value.negate();
						YPrim_Series.setElemSym(i, j + getNPhases(), Value);
					}
				}
			}

			YPrim.copyFrom(YPrim_Series);  // Initialize YPrim for series impedances

			// 10/3/2006 moved this to after the copy to Yprim so it doesn't affect normal line model capacitance
			// 3-30-04  ----- Rev 2-4-09 to include both sides of line
			// Increase diagonal elements of both sides of line so that we will avoid isolated bus problem
			// add equivalent of 10 kvar capacitive at 345 kV
			for (int i = 0; i < Yorder; i++)
				YPrim_Series.addElement(i, i, Line.CAP_EPSILON);
		}

		// Now Build the Shunt admittances and add into YPrim
		
		/* Put half the Shunt Capacitive Admittance at each end */
		YValues = Yc.asArray(Norder);

		if (GeometrySpecified || SpacingSpecified) {

			/* Values are already compensated for length and frequency */
			k = -1;  // TODO Check zero based indexing
			for (int j = 0; j < getNPhases(); j++) {
				for (int i = 0; i < getNPhases(); i++) {
					k += 1;    // Assume matrix in col order (1,1  2,1  3,1 ...)
					Value = YValues[k].divide(2.0);  // half at each end ...
					YPrim_Shunt.addElement(i, j, Value);
					YPrim_Shunt.addElement(i + getNPhases(), j + getNPhases(), Value);
				}
			}
		} else {
			/* Regular line model - values computed per unit length at base frequency */
			k = -1;  // TODO Check zero based indexing
			for (int j = 0; j < getNPhases(); j++) {
				for (int i = 0; i < getNPhases(); i++) {
					k += 1;  // Assume matrix in col order (1,1  2,1  3,1 ...)
					Value = new Complex(0.0, YValues[k].getImaginary() * LengthMultiplier * FreqMultiplier / 2.0);
					YPrim_Shunt.addElement( i, j, Value);
					YPrim_Shunt.addElement(i + getNPhases(), j + getNPhases(), Value);
				}
			}
		}

		/* Now Account for Open Conductors */
		/* For any conductor that is open, zero out row and column */

		YPrim.addFrom(YPrim_Shunt);
		super.calcYPrim();
		setYprimInvalid(false);
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		DSSClass pc = getParentClass();

		F.println("~ " + pc.getPropertyName()[0] + "=" + getFirstBus());
		F.println("~ " + pc.getPropertyName()[1] + "=" + getNextBus());

		F.println("~ " + pc.getPropertyName()[2] + "=" + getCondCode());
		F.println("~ " + pc.getPropertyName()[3] + "=" + getLen());
		F.println("~ " + pc.getPropertyName()[4] + "=" + getNPhases());
		F.println("~ " + pc.getPropertyName()[5] + "=" + getR1());
		F.println("~ " + pc.getPropertyName()[6] + "=" + getX1());
		F.println("~ " + pc.getPropertyName()[7] + "=" + getR0());
		F.println("~ " + pc.getPropertyName()[8] + "=" + getX0());
		F.println("~ " + pc.getPropertyName()[9] + "=" + getC1() * 1.0e9);
		F.println("~ " + pc.getPropertyName()[10] + "=" + getC0() * 1.0e9);
		F.print("~ " + pc.getPropertyName()[11] + "=" + "\"");
		for (int i = 0; i < getNPhases(); i++) {
			for (int j = 0; j < getNPhases(); j++) {
				F.print(Z.getElement(i, j).getReal() + " ");
			}
			F.print("|");
		}
		F.println("\"");
		F.print("~ " + pc.getPropertyName()[12] + "=" + "\"");
		for (int i = 0; i < getNPhases(); i++) {
			for (int j = 0; j < getNPhases(); j++) {
				F.print(Z.getElement(i, j).getImaginary() + " ");
			}
			F.print("|");
		}
		F.println("\"");
		F.print("~ " + pc.getPropertyName()[13] + "=" + "\"");
		for (int i = 0; i < getNPhases(); i++) {
			for (int j = 0; j < getNPhases(); j++) {
				F.print((Yc.getElement(i, j).getImaginary() / DSSGlobals.TwoPi / BaseFrequency * 1.e9) + " ");
			}
			F.print("|");
		}
		F.println("\"");

		F.print("~ " + pc.getPropertyName()[13] + "=");
		if (IsSwitch) {
			F.println("true");
		} else {
			F.println("false");
		}

		/* Dump the rest by default */
		for (int i = 14; i < pc.getNumProperties(); i++) {  // TODO Check zero based indexing
			F.println("~ " + pc.getPropertyName()[i] + "=" + getPropertyValue(i));
		}
	}
	
	/**
	 * Placeholder for Line module No Load Loss procedure.
	 */
	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses, Complex NoLoadLosses) {
		// For now, we'll just do the default behaviour until we implement shunt losses
		super.getLosses();
	}
	
	@Override
	public String getPropertyValue(int Index) {
		int i, j;
		double Factor;
		String Result;
		
		if ((Index >= 11) && (Index <= 13)) {
			Result = "[";
		} else {
			Result = "";
		}

		/* Report Impedance values in ohms per unit length of present length units */
		switch (Index) {
		case 0:
			Result = getBus(1);  // TODO Check zero based indexing
		case 1:
			Result = getBus(2);
		case 3:
			Result = String.format("%-.7g", Len);
		case 4:
			Result = String.format("%d", nPhases);
		case 5:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", R1 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 6:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", X1 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 7:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", R0 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 8:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", X0 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 9:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", C1 * 1.0e9 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 10:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", C0 * 1.0e9 / UnitsConvert);
			} else {
				Result = "----";
			}
		case 11:
			for (i = 0; i < getNConds(); i++) {
				for (j = 0; j < i; j++) {
					// report in per unit Length in length units
					if (GeometrySpecified || SpacingSpecified) {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getReal() / Len) + " ";
					} else {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getReal() / UnitsConvert) + " ";
					}
					if (i < getNConds())
						Result = Result + "|";
				}
			}
		case 12:
			for (i = 0; i < getNConds(); i++) {
				for (j = 0; j < i; j++) {
					// X matrix
					if (GeometrySpecified || SpacingSpecified) {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getImaginary() / Len) + " ";
					} else {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getImaginary() / UnitsConvert) + " ";
					}
				}
				if (i < nConds)
					Result = Result + "|";
			}
		case 13:  // CMatrix  nf
			Factor = DSSGlobals.TwoPi * BaseFrequency * 1.0e-9;
			for (i = 0; i < nConds; i++) {
				for (j = 0; j < i; j++) {
					if (GeometrySpecified || SpacingSpecified) {
						Result = Result + String.format("%-.7g", Yc.getElement(i, j).getImaginary() / Factor / Len) + " ";
					} else {
						Result = Result + String.format("%-.7g", Yc.getElement(i, j).getImaginary() / Factor / UnitsConvert) + " ";
					}
				}
				if (i < nConds)
					Result = Result + "|";
			}
		case 14:
			if (IsSwitch) {
				Result = "True";
			} else {
				Result = "False";
			}
		case 15:
			Result = String.format("%-g", Rg);
		case 16:
			Result = String.format("%-g", Xg);
		case 17:
			Result = String.format("%-g", rho);
		case 22:
			Result = Utilities.getEarthModel(EarthModel);
		default:
			Result = super.getPropertyValue(Index);
		}

		if ((Index >= 11) && (Index <= 13))
			Result = Result + "[";
		
		return Result;
	}
	
	@Override
	public void makePosSequence() {
		
	}
	
	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromGeometry(double f) {
		
	}
	
	private void killGeometrySpecified() {
		
	}
	
	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromSpacing(double f) {
		
	}
	
	private void killSpacingSpecified() {
		
	}
	
	private void clearYPrim() {
		
	}
	
	private void resetLengthUnits() {
		
	}
	
	private void updatePDProperties() {
		
	}

	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public CMatrix getYc() {
		return Yc;
	}

	public void setYc(CMatrix yc) {
		Yc = yc;
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

	public double getLen() {
		return Len;
	}

	public void setLen(double len) {
		Len = len;
	}

	public int getLengthUnits() {
		return LengthUnits;
	}

	public void setLengthUnits(int lengthUnits) {
		LengthUnits = lengthUnits;
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

	public double getKXg() {
		return KXg;
	}

	public void setKXg(double kXg) {
		KXg = kXg;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public double getGeneralPlotQuantity() {
		return GeneralPlotQuantity;
	}

	public void setGeneralPlotQuantity(double generalPlotQuantity) {
		GeneralPlotQuantity = generalPlotQuantity;
	}

	public String getCondCode() {
		return CondCode;
	}

	public void setCondCode(String condCode) {
		CondCode = condCode;
	}

	public String getGeometryCode() {
		return GeometryCode;
	}

	public void setGeometryCode(String geometryCode) {
		GeometryCode = geometryCode;
	}

	public String getSpacingCode() {
		return SpacingCode;
	}

	public void setSpacingCode(String spacingCode) {
		SpacingCode = spacingCode;
	}

	public boolean isGeometrySpecified() {
		return GeometrySpecified;
	}

	public void setGeometrySpecified(boolean geometrySpecified) {
		GeometrySpecified = geometrySpecified;
	}

	public boolean isSpacingSpecified() {
		return SpacingSpecified;
	}

	public void setSpacingSpecified(boolean spacingSpecified) {
		SpacingSpecified = spacingSpecified;
	}

	public boolean isSymComponentsChanged() {
		return SymComponentsChanged;
	}

	public void setSymComponentsChanged(boolean symComponentsChanged) {
		SymComponentsChanged = symComponentsChanged;
	}

	public boolean isSymComponentsModel() {
		return SymComponentsModel;
	}

	public void setSymComponentsModel(boolean symComponentsModel) {
		SymComponentsModel = symComponentsModel;
	}

	public boolean isIsSwitch() {
		return IsSwitch;
	}

	public void setIsSwitch(boolean isSwitch) {
		IsSwitch = isSwitch;
	}
	
	public void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroSeqLosses) {
		
	}
	
	public boolean mergeWith(LineObj OtherLine, boolean Series) {
		return false;
	}
	
	public void updateControlElements(String NewName, String OldName) {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}

	public boolean isLineCodeSpecified() {
		return LineCodeSpecified;
	}
	
	// FIXME Private members in OpenDSS

	public double getZFrequency() {
		return ZFrequency;
	}

	public void setZFrequency(double zFrequency) {
		ZFrequency = zFrequency;
	}

	public int getLineCodeUnits() {
		return LineCodeUnits;
	}

	public void setLineCodeUnits(int lineCodeUnits) {
		LineCodeUnits = lineCodeUnits;
	}

	public double getUnitsConvert() {
		return UnitsConvert;
	}

	public void setUnitsConvert(double unitsConvert) {
		UnitsConvert = unitsConvert;
	}

	public LineGeometryObj getLineGeometryObj() {
		return LineGeometryObj;
	}

	public void setLineGeometryObj(LineGeometryObj lineGeometryObj) {
		LineGeometryObj = lineGeometryObj;
	}

	public LineSpacingObj getLineSpacingObj() {
		return LineSpacingObj;
	}

	public void setLineSpacingObj(LineSpacingObj lineSpacingObj) {
		LineSpacingObj = lineSpacingObj;
	}

	public WireDataObj[] getWireData() {
		return WireData;
	}

	public void setWireData(WireDataObj[] wireData) {
		WireData = wireData;
	}

	public boolean getRhoSpecified() {
		return rhoSpecified;
	}

	public void setRhoSpecified(boolean rhoSpecified) {
		this.rhoSpecified = rhoSpecified;
	}

	public int getEarthModel() {
		return EarthModel;
	}

	public void setEarthModel(int earthModel) {
		EarthModel = earthModel;
	}

	public void setLineCodeSpecified(boolean lineCodeSpecified) {
		LineCodeSpecified = lineCodeSpecified;
	}

	public CMatrix getZinv() {
		return Zinv;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

}

package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.ControlElem;
import com.epri.dss.delivery.Line;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineCodeObj;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.general.impl.ConductorChoice;
import com.epri.dss.general.impl.ConductorDataImpl;
import com.epri.dss.general.impl.LineGeometryObjImpl;
import com.epri.dss.shared.CMatrix;

public class LineObjImpl extends PDElementImpl implements LineObj {

	/* keep track of last frequency computed for geometry */
	private double ZFrequency;
	private int LineCodeUnits;
	private double UnitsConvert; // conversion factor
	private LineGeometryObj LineGeometryObj;
	private LineSpacingObj LineSpacingObj;
	private ConductorDataObj[] WireData;
	private ConductorChoice PhaseChoice;
	private boolean rhoSpecified;
	private boolean LineCodeSpecified;
	private int EarthModel;
	private boolean CapSpecified;  // to make sure user specifies C in some form

	protected CMatrix Zinv;

	/* Base frequency series Z matrix per unit length */
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
	protected double GeneralPlotQuantity;  // for general circuit plotting
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
		this.DSSObjType = ParClass.getDSSClassType();  // DSSObjType + LINESECTION; // in both PD element list and line section lists

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(2);   // force allocation of terminals and conductors
		this.IsSwitch = false;
		this.R1 = 0.0580;  // ohms per 1000 ft
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

		this.Rg = 0.01805;  // ohms per 1000 ft
		this.Xg = 0.155081;
		this.rho = 100.0;
		this.KXg = this.Xg / Math.log(658.5 * Math.sqrt(this.rho / this.baseFrequency));
		this.rhoSpecified = false;
		this.CapSpecified = false;

		/*baseFrequency = 60.0;*/  // set in base class
		this.NormAmps = 400.0;
		this.EmergAmps = 600.0;
		this.PctPerm   = 20.0;
		this.FaultRate = 0.1;
		this.HrsToRepair = 3.0;

		this.SymComponentsChanged = false;
		this.SymComponentsModel = true;

		this.GeometrySpecified = false;
		this.GeometryCode      = "";
		this.LengthUnits       = LineUnits.UNITS_NONE;  // assume everything matches
		this.UnitsConvert      = 1.0;
		this.LineCodeUnits     = LineUnits.UNITS_NONE;
		this.LineCodeSpecified = false;
		this.EarthModel        = DSSGlobals.getInstance().getDefaultEarthModel();

		this.SpacingSpecified = false;
		this.LineSpacingObj = null;
		this.WireData = null;
		this.PhaseChoice = ConductorChoice.Unknown;
		this.SpacingCode = "";

		this.ZFrequency = -1.0;  // indicate Z not computed.
		this.LineGeometryObj = null;

		initPropertyValues(0);

		this.YOrder = this.nTerms * this.nConds;
		recalcElementData();
	}

	public void fetchLineCode(String Code) {

		DSSGlobals Globals = DSSGlobals.getInstance();

		if (LineImpl.getLineCodeClass() == null)
			LineImpl.setLineCodeClass((LineCode) Globals.getDSSClassList().get(Globals.getClassNames().find("linecode")));

		if (LineImpl.getLineCodeClass().setActive(Code)) {

			LineCodeObj lc = (LineCodeObj) LineImpl.getLineCodeClass().getActiveObj();

			CondCode = Code.toLowerCase();

			// frequency compensation takes place in calcYPrim
			baseFrequency = lc.getBaseFrequency();
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

			// earth return impedances used to compensate for frequency
			Rg  = lc.getRg();
			Xg  = lc.getXg();
			rho = lc.getRho();
			KXg = Xg / Math.log(658.5 * Math.sqrt(rho / baseFrequency));

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
				// for a line, nPhases = nCond, for now
				Z    = new CMatrixImpl(nPhases);
				Zinv = new CMatrixImpl(nPhases);
				Yc   = new CMatrixImpl(nPhases);
			}

			if (!SymComponentsModel) {
				// copy matrices
				Z.copyFrom(lc.getZ());
				/*Zinv.copyFrom(lc.getZinv());*/  // no need to copy Zinv
				Yc.copyFrom(lc.getYC());
			} else {
				recalcElementData();  // compute matrices
			}

			setNConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			//setYprimInvalid(true);  // set in edit; this is redundant
		} else {
			Globals.doSimpleMsg("Line Code:" + Code + " not found.", 180);
		}
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

		// for a line, nPhases = nCond, for now
		Z    = new CMatrixImpl(getNPhases());
		Zinv = new CMatrixImpl(getNPhases());
		Yc   = new CMatrixImpl(getNPhases());

		OneThird = 1.0 / 3.0;  // do this to get more precision in next few statements

		/* Only time this is called is if symmetrical components are specified */

		Ztemp = new Complex(R1, X1).multiply(2.0);
		/* Handle special case for 1-phase line and/or pos seq model */
		if ((getNPhases() == 1) || ckt.isPositiveSequence()) {
			// long-line equivalent pi, but only for cktModel=positive
			if (ckt.isPositiveSequence() && (C1 > 0)) {
				// nominal pi parameters per unit length
				Zs = new Complex(R1, X1);
				Ys = new Complex(0.0, DSSGlobals.TwoPi * baseFrequency * C1);
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
				// rely on this function being called only once, unless r1, x1, or c1 changes
				R1 = Zm.getReal() / Len;
				X1 = Zm.getImaginary() / Len;
				C1 = Ym.getImaginary() / Len / DSSGlobals.TwoPi / baseFrequency;
			}
			// zero sequence the same as positive sequence
			R0 = R1;
			X0 = X1;
			C0 = C1;
		}
		Zs = Ztemp.add(new Complex(R0, X0)).multiply(OneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(OneThird);

		Yc1 = DSSGlobals.TwoPi * baseFrequency * C1;
		Yc0 = DSSGlobals.TwoPi * baseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add( new Complex(0.0, Yc0) ).multiply(OneThird);
		Ym = new Complex(0.0, Yc0).subtract( new Complex(0.0, Yc1) ).multiply(OneThird);

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

		if (SymComponentsChanged) {
			/* Try to catch inadvertent user error when they forget to specify C1 and C0 */
			/* Check to see if user has spec'd C1 and C0. If not, adjust default values for new length units */
			if (!CapSpecified) {
				C1 = C1 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, LengthUnits);  // were defined in kft
				C0 = C0 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, LengthUnits);
				CapSpecified = true;   // so we don't do it again
			}
			recalcElementData();
		}

		clearYPrim();

		// build series YPrim
//		CMatrix Y = YPrim_Series;

		/* Build Z matrix */
		if (GeometrySpecified) {

			makeZFromGeometry(ckt.getSolution().getFrequency());  // includes length in proper units
			if (Globals.isSolutionAbort())
				return;

		} else if (SpacingSpecified) {

			makeZFromSpacing(ckt.getSolution().getFrequency());
			if (Globals.isSolutionAbort())
				return;

		} else {  // Z is from line code or specified in line data

			LengthMultiplier = Len / UnitsConvert;  // convert to per unit length
			YPrimFreq        = ckt.getSolution().getFrequency();
			FreqMultiplier   = YPrimFreq / baseFrequency;

			/* Put in series RL */
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
				Globals.doErrorMsg("LineObj.calcYPrim", "Matrix inversion error for line \"" + getName() + "\"",
							"Invalid impedance specified. Replaced with tiny conductance.", 183);
				Zinv.clear();
				for (int i = 0; i < getNPhases(); i++) {
					Zinv.setElement(i, i, new Complex(DSSGlobals.EPSILON, 0.0));
				}
			} else {
				/* Now, put in Yprim_Series matrix */
				for (int i = 0; i < getNPhases(); i++) {
					for (int j = 0; j < getNPhases(); j++) {
						Value = Zinv.getElement(i, j);
						YPrimSeries.setElement(i, j, Value);
						YPrimSeries.setElement(i + getNPhases(), j + getNPhases(), Value);
						Value = Value.negate();
						YPrimSeries.setElemSym(i, j + getNPhases(), Value);
					}
				}
			}

			YPrim.copyFrom(YPrimSeries);  // initialize YPrim for series impedances

			// 10/3/2006 moved this to after the copy to Yprim so it doesn't affect normal line model capacitance
			// 3-30-04  ----- Rev 2-4-09 to include both sides of line
			// increase diagonal elements of both sides of line so that we will avoid isolated bus problem
			// add equivalent of 10 kvar capacitive at 345 kV
			for (int i = 0; i < YOrder; i++)
				YPrimSeries.addElement(i, i, Line.CAP_EPSILON);
		}

		// now build the shunt admittances and add into YPrim

		/* Put half the shunt capacitive admittance at each end */
		YValues = Yc.asArray(Norder);

		if (GeometrySpecified || SpacingSpecified) {

			/* Values are already compensated for length and frequency */
			k = -1;  // TODO Check zero based indexing
			for (int j = 0; j < getNPhases(); j++) {
				for (int i = 0; i < getNPhases(); i++) {
					k += 1;  // assume matrix in col order (1,1  2,1  3,1 ...)
					Value = YValues[k].divide(2.0);  // half at each end ...
					YPrimShunt.addElement(i, j, Value);
					YPrimShunt.addElement(i + getNPhases(), j + getNPhases(), Value);
				}
			}
		} else {
			/* Regular line model - values computed per unit length at base frequency */
			k = -1;  // TODO Check zero based indexing
			for (int j = 0; j < getNPhases(); j++) {
				for (int i = 0; i < getNPhases(); i++) {
					k += 1;  // assume matrix in col order (1,1  2,1  3,1 ...)
					Value = new Complex(0.0, YValues[k].getImaginary() * LengthMultiplier * FreqMultiplier / 2.0);
					YPrimShunt.addElement( i, j, Value);
					YPrimShunt.addElement(i + getNPhases(), j + getNPhases(), Value);
				}
			}
		}

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */

		YPrim.addFrom(YPrimShunt);
		super.calcYPrim();
		setYPrimInvalid(false);
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
				F.print((Yc.getElement(i, j).getImaginary() / DSSGlobals.TwoPi / baseFrequency * 1.e9) + " ");
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
	 * Placeholder for Line module no load loss procedure.
	 */
	@Override
	public void getLosses(double[] TotalLosses, double[] LoadLosses, double[] NoLoadLosses) {
		// for now, we'll just do the default behaviour until we implement shunt losses
		super.getLosses(TotalLosses, LoadLosses, NoLoadLosses);
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

		/* Report impedance values in ohms per unit length of present length units */
		switch (Index) {
		case 0:
			Result = getBus(1);  // TODO Check zero based indexing
			break;
		case 1:
			Result = getBus(2);
			break;
		case 3:
			Result = String.format("%-.7g", Len);
			break;
		case 4:
			Result = String.format("%d", nPhases);
			break;
		case 5:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", R1 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 6:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", X1 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 7:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", R0 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 8:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", X0 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 9:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", C1 * 1.0e9 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 10:
			if (SymComponentsModel) {
				Result = String.format("%-.7g", C0 * 1.0e9 / UnitsConvert);
			} else {
				Result = "----";
			}
			break;
		case 11:
			for (i = 0; i < getNConds(); i++) {
				for (j = 0; j < i; j++) {
					// report in per unit length in length units
					if (GeometrySpecified || SpacingSpecified) {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getReal() / Len) + " ";
					} else {
						Result = Result + String.format("%-.7g", Z.getElement(i, j).getReal() / UnitsConvert) + " ";
					}
					if (i < getNConds())
						Result = Result + "|";
				}
			}
			break;
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
			break;
		case 13:  // CMatrix nf
			Factor = DSSGlobals.TwoPi * baseFrequency * 1.0e-9;
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
			break;
		case 14:
			if (IsSwitch) {
				Result = "True";
			} else {
				Result = "False";
			}
			break;
		case 15:
			Result = String.format("%-g", Rg);
			break;
		case 16:
			Result = String.format("%-g", Xg);
			break;
		case 17:
			Result = String.format("%-g", rho);
			break;
		case 22:
			Result = Utilities.getEarthModel(EarthModel);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		if ((Index >= 11) && (Index <= 13))
			Result = Result + "[";

		return Result;
	}

	/**
	 * Only consider 3-phase branches with pos seq >> neg seq
	 * Otherwise, we don't know whether it is a 3-phase line or just a line with 3 phases
	 */
	public void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses, Complex ZeroSeqLosses) {
		int k;

		Complex[] Vph = new Complex[2];
		Complex[] V012 = new Complex[2];
		Complex[] I012 = new Complex[2];

		PosSeqLosses  = Complex.ZERO;
		NegSeqLosses  = Complex.ZERO;
		ZeroSeqLosses = Complex.ZERO;

		/* Method: sum seq powers going into each terminal */

		if (nPhases == 3) {
			/* 3-phase lines only */
			computeITerminal();
			for (int i = 0; i < 2; i++) {
				k = (i - 1) * nPhases + 1;  // TODO Check zero based indexing
				for (int j = 0; j < 2; j++)
					Vph[j] = DSSGlobals.getInstance().getActiveCircuit().getSolution().getNodeV()[nodeRef[k + j]];
				MathUtil.phase2SymComp(Vph, V012);
				MathUtil.phase2SymComp(ITerminal[k], I012);
				PosSeqLosses = PosSeqLosses.add( V012[1].multiply(I012[1].conjugate()) );  // TODO Check zero based indexing
				NegSeqLosses = NegSeqLosses.add( V012[2].multiply(I012[2].conjugate()) );  // accumulate both line modes
				ZeroSeqLosses = ZeroSeqLosses.add( V012[0].multiply(I012[0].conjugate()) );
			}
			PosSeqLosses = PosSeqLosses.multiply(3.0);
			NegSeqLosses = NegSeqLosses.multiply(3.0);
			ZeroSeqLosses = ZeroSeqLosses.multiply(3.0);
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = getBus(1);  // TODO Check zero based indexing
		PropertyValue[1] = getBus(2);
		PropertyValue[2] = "";
		PropertyValue[3] = "1.0";  // "5.28"; Changed 2/17/00
		PropertyValue[4] = "3";
		PropertyValue[5] = ".058";
		PropertyValue[6] = ".1206";
		PropertyValue[7] = ".1784";
		PropertyValue[8] = ".4047";
		PropertyValue[9] = "3.4";
		PropertyValue[10] = "1.6";
		PropertyValue[11] = "";
		PropertyValue[12] = "";
		PropertyValue[13] = "";
		PropertyValue[14] = "false";
		PropertyValue[15] = "0.01805";
		PropertyValue[16] = "0.155081";
		PropertyValue[17] = "100";
		PropertyValue[18] = "";
		PropertyValue[19] = "NONE";
		PropertyValue[20] = "";
		PropertyValue[21] = "";
		PropertyValue[22] = Utilities.getEarthModel(DSSGlobals.SIMPLECARSON);

		super.initPropertyValues(Line.NumPropsThisClass);

		// override inherited properties just in case
		PropertyValue[Line.NumPropsThisClass + 1] = "400";  // normAmps    // TODO Check zero based indexing
		PropertyValue[Line.NumPropsThisClass + 2] = "600";  // emergAmps
		PropertyValue[Line.NumPropsThisClass + 3] = "0.1";  // faultRate
		PropertyValue[Line.NumPropsThisClass + 4] = "20";   // pctPerm
		PropertyValue[Line.NumPropsThisClass + 5] = "3";    // hrsToRepair

		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String S;
		double C1_new, Cs, Cm;
		Complex Z1, ZS, Zm;
		int i, j;

		// set to single phase and make sure r1, x1, c1 set.
		// if already single phase, let alone
		if (nPhases > 1) {
			// kill certain propertyValue elements to get a cleaner looking save
			PrpSequence[2] = 0;  // TODO Check zero based indexing
			for (i = 5; i < 13; i++)
				PrpSequence[i] = 0;

			if (IsSwitch) {
				S = " R1=1 X1=1 C1=1.1 Phases=1 Len=0.001";
			} else {
				if (SymComponentsModel) {  // keep the same Z1 and C1
					Z1 = new Complex(R1, X1);
					C1_new = C1 * 1.0e9;  // convert to nF
				} else {  // matrix was input directly, or built from physical data
					// average the diagonal and off-dialgonal elements
					ZS = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						ZS = ZS.add(Z.getElement(i, i));
					ZS = ZS.divide(nPhases);
					Zm = Complex.ZERO;
					for (i = 0; i < nPhases - 1; i++) {  // TODO Check zero based indexing
						for (j = i + 1; j < nPhases; j++) {
							Zm = Zm.add(Z.getElement(i, j));
						}
					}
					Zm = Zm.divide(nPhases * (nPhases - 1.0) / 2.0);
					Z1 = ZS.subtract(Zm);

					// do same for capacitances
					Cs = 0.0;
					for (i = 0; i < nPhases; i++)
						Cs = Cs + Yc.getElement(i, i).getImaginary();
					Cm = 0.0;
					for (i = 1; i < nPhases; i++) {  // TODO Check zero based indexing
						for (j = i + 1; j < nPhases; j++) {
							Cm = Cm + Yc.getElement(i, j).getImaginary();
						}
					}
					C1_new = (Cs - Cm) / DSSGlobals.TwoPi / baseFrequency/ (nPhases * (nPhases - 1.0) / 2.0) * 1.0e9; // nanofarads
				}
				S = String.format(" R1=%-.5g  %-.5g  C1=%-.5g Phases=1", Z1.getReal(), Z1.getImaginary(), C1_new);
			}
			// conductor current ratings
			S = S + String.format(" Normamps=%-.5g  %-.5g", getNormAmps(), getEmergAmps());
			Parser.getInstance().setCmdString(S);
			edit();
		}

		super.makePosSequence();
	}

	/**
	 * Merge this line with another line and disble the other line.
	 */
	public boolean mergeWith(LineObj OtherLine, boolean Series) {
		Complex[] Values1, Values2;
		int Order1 = 0, Order2 = 0, i, j, Common1, Common2;
		double TotalLen, wnano;
		String S = "", NewName;
		int TestBusNum;
		int LenUnitsSaved;
		Complex NewZ;
		double LenSelf, LenOther;

		boolean Result = false;
		if (OtherLine != null) {
			if (nPhases != OtherLine.getNPhases())
				return Result;  // can't merge

			LenUnitsSaved = LengthUnits;

			setYPrimInvalid(true);

			// redefine property values to make it appear that line was defined this way originally using matrices

			if (Series) {
				TotalLen = Len + OtherLine.getLen() * LineUnits.convertLineUnits(OtherLine.getLengthUnits(), LengthUnits);
			} else {
				TotalLen = 1.0;
			}

			if (Series) {
				/* redefine the bus connections */

				// find bus in common between the two lines
				Common1 = 0;
				Common2 = 0;
				i = 1;
				while ((Common1 == 0) && (i <= 2)) {
					TestBusNum = DSSGlobals.getInstance().getActiveCircuit().getMapNodeToBus()[nodeRef[1 + (i - 1) * nConds]].busRef;
					for (j = 0; j < 2; j++) {
						if (DSSGlobals.getInstance().getActiveCircuit().getMapNodeToBus()[OtherLine.getNodeRef()[1 + (j - 1) * OtherLine.getNConds()]].busRef == TestBusNum) {
							Common1 = i;
							Common2 = j;
							break;
						}
					}
					i += 1;
				}

				if (Common1 == 0)
					return Result;  // there's been an error; didn't find anything in common

				/* redefine the bus connections */
				switch (Common1) {
				case 1:
					switch (Common2) {
					case 1:
						S = "Bus1=\"" + OtherLine.getBus(2) + "\"";
						break;
					case 2:
						S = "Bus1=\"" + OtherLine.getBus(1) + "\"";
						break;
					}
					break;
				case 2:
					switch (Common2) {
					case 1:
						S = "Bus2=\"" + OtherLine.getBus(2) + "\"";
						break;
					case 2:
						S = "Bus2=\"" + OtherLine.getBus(1) + "\"";
						break;
					}
					break;
				}

				Parser.getInstance().setCmdString(S);
				edit();

			}

			/* Rename the line */
			if (Series) {
				NewName = Utilities.stripExtension(getBus(1)) + "~"  + Utilities.stripExtension(getBus(2));
			} else {
				NewName = Utilities.stripExtension(getBus(1)) + "||" + Utilities.stripExtension(getBus(2));
			}

			/* Update control element connections to this line */
			updateControlElements("line." + NewName, "line." + getName());
			updateControlElements("line." + NewName, "line." + OtherLine.getName());
			setName(NewName);

			if (Series)
				IsSwitch = false;  // not allowed on series merge.

			/* Now do the impedances */

			LenSelf = Len / UnitsConvert;  // in units of r x Data
			LenOther = OtherLine.getLen() / OtherLine.getUnitsConvert();

			if (SymComponentsModel) {
				/*------------------ Sym Component Model ---------------------*/
				if (Series) {
					S = " R1=" + String.format("%-g", (R1 * LenSelf + OtherLine.getR1() * LenOther) / TotalLen);  // ohms per unit length of this line length units
					S = S + String.format(" %-g", (X1 * LenSelf + OtherLine.getX1() * LenOther) / TotalLen);
					S = S + String.format(" %-g", (R0 * LenSelf + OtherLine.getR0() * LenOther) / TotalLen);
					S = S + String.format(" %-g", (X0 * LenSelf + OtherLine.getX0() * LenOther) / TotalLen);
					S = S + String.format(" %-g", (C1 * LenSelf + OtherLine.getC1() * LenOther) / TotalLen * 1.0e9);
					S = S + String.format(" %-g", (C0 * LenSelf + OtherLine.getC0() * LenOther) / TotalLen * 1.0e9);
				} else {
					/* Parallel */
					if (IsSwitch) {
						S = "";   /* Leave as is if switch; just dummy z anyway */
					} else if (OtherLine.isIsSwitch()) {
						S = " switch=yes";  /* This will take care of setting Z's */
					} else {
						/* ********* Will This work with length multiplier? did it ever work? ************************* */
						NewZ = MathUtil.parallelZ(new Complex(R1 * Len, X1 * Len),
								new Complex(OtherLine.getR1() * OtherLine.getLen(), OtherLine.getX1() * OtherLine.getLen()));
						S = " R1=" + String.format("%-g %-g ", NewZ.getReal(), NewZ.getImaginary());
						NewZ = MathUtil.parallelZ(new Complex(R0 * Len, X0 * Len),
								new Complex(OtherLine.getR0() * OtherLine.getLen(), OtherLine.getX0() * OtherLine.getLen()));
						S = " R0=" + String.format("%-g %-g ", NewZ.getReal(), NewZ.getImaginary());
						S = S + String.format(" %-g", (C1 * Len + OtherLine.getC1() * OtherLine.getLen()) / TotalLen * 1.0e9);
						S = S + String.format(" %-g", (C0 * Len + OtherLine.getC0() * OtherLine.getLen()) / TotalLen * 1.0e9);
					}
				}
				Parser.getInstance().setCmdString(S);   // this reset the length units
				edit();
			} else {
				/*------------------- Matrix Model ---------------------------*/
				if (!Series) {
					TotalLen = Len / 2.0;  /* we'll assume lines are equal for now */
				} else {
					/* Matrices were defined */

					// merge Z matrices
					Values1 = Z.asArray(Order1);
					Values2 = OtherLine.getZ().asArray(Order2);

					if (Order1 != Order2)
						return Result;  // lines not same size for some reason

					// Z <= (Z1 + Z2) / TotalLen to get equiv ohms per unit length
					for (i = 0; i < Order1 * Order1; i++)
						Values1[i] = Values1[i].multiply(LenSelf).add(Values2[i].multiply(LenOther)).divide(TotalLen);

					// merge Yc matrices
					Values1 = Yc.asArray(Order1);
					Values2 = OtherLine.getYc().asArray(Order2);

					if (Order1 != Order2)
						return Result;  // lines not same size for some reason

					for (i = 0; i < Order1 * Order1; i++)
						Values1[i] = Values1[i].multiply(LenSelf).add( Values2[i].multiply(LenOther) ).divide(TotalLen);

					/* R matrix */
					S = "Rmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							S = S + String.format(" %-g", Z.getElement(i, j).getReal());
						S = S + " | ";
					}
					S = S + "] Xmatrix=[";
					/* X matrix */
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							S = S + String.format(" %-g", Z.getElement(i, j).getImaginary());
						S = S + " | ";
					}
					S = S + "]";
					Parser.getInstance().setCmdString(S);
					edit();

					/* C matrix */
					wnano = DSSGlobals.TwoPi * baseFrequency / 1.0e9;
					S = "Cmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							S = S + String.format(" %-g", (Yc.getElement(i, j).getImaginary() / wnano));  // convert from mhos to nanofs
						S = S + " | ";
					}
					S = S + "] ";
					Parser.getInstance().setCmdString(S);
					edit();
				}
			}  // matrix definition

			Parser.getInstance().setCmdString(String.format(" length=%-g units=%s", TotalLen, LineUnits.lineUnitsStr(LenUnitsSaved)));
			edit();

			OtherLine.setEnabled(false);  // disable the other line
			Result = true;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in line merge: Attempt to merge with invalid (nil) line object found.", 184);
		}

		return Result;
	}

	public void updateControlElements(String NewName, String OldName) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (ControlElem pControlElem : ckt.getDSSControls())
			if (OldName.equalsIgnoreCase( pControlElem.getElementName() )) {
				Parser.getInstance().setCmdString(" Element=" + NewName);  // change name of the property
				pControlElem.edit();
			}
	}

	public void fetchLineSpacing(String Code) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Globals.getLineSpacingClass().setActive(Code)) {
			setLineSpacingObj( (LineSpacingObj) Globals.getLineSpacingClass().getActiveObj() );
			LineCodeSpecified = false;
			killGeometrySpecified();
			SpacingCode = Code.toLowerCase();

			// need to establish Yorder before makeZFromSpacing
			setNPhases( LineSpacingObj.getNPhases() );
			setNConds(nPhases);  // force reallocation of terminal info
			YOrder        = nConds * nTerms;
			setYPrimInvalid(true);    // force rebuild of Y matrix
		} else {
			Globals.doSimpleMsg("Line spacing object " + Code + " not found.", 181);
		}
	}

	public void fetchWireList(String Code) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int i, istart;

		if (getLineSpacingObj() == null)
			Globals.doSimpleMsg("Must assign the LineSpacing before wires.", 181);

		if (PhaseChoice == ConductorChoice.Unknown) {  // it's an overhead line
			LineCodeSpecified = false;
			killGeometrySpecified();
			WireData = new ConductorDataObj[LineSpacingObj.getNWires()];
			istart = 1;
			PhaseChoice = ConductorChoice.Overhead;
		} else {  // adding bare neutrals to an underground line - TODO what about repeat invocation?
			istart = LineSpacingObj.getNPhases() + 1;
		}

		Globals.getAuxParser().setCmdString(Code);
		for (i = istart; i < LineSpacingObj.getNWires(); i++) {
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			Globals.getWireDataClass().setCode(Globals.getAuxParser().makeString());
			if (ConductorDataImpl.getActiveConductorDataObj() != null) {
				WireData[i] = ConductorDataImpl.getActiveConductorDataObj();
			} else {
				Globals.doSimpleMsg("Wire " + Globals.getAuxParser().makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchCNCableList(String Code) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		int i;

		LineCodeSpecified = false;
		killGeometrySpecified();
		if (LineSpacingObj == null)
			Globals.doSimpleMsg("Must assign the LineSpacing before CN cables.", 181);

		PhaseChoice = ConductorChoice.ConcentricNeutral;
		WireData = new ConductorDataObj[LineSpacingObj.getNWires()];
		Globals.getAuxParser().setCmdString(Code);
		for (i = 0; i < LineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			Globals.getCNDataClass().setCode(Globals.getAuxParser().makeString());
			if (ConductorDataImpl.getActiveConductorDataObj() != null) {
				WireData[i] = ConductorDataImpl.getActiveConductorDataObj();
			} else {
				Globals.doSimpleMsg("CN cable " + Globals.getAuxParser().makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchTSCableList(String Code) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		int i;

		LineCodeSpecified = false;
		killGeometrySpecified();
		if (LineSpacingObj == null)
			Globals.doSimpleMsg("Must assign the LineSpacing before TS cables.", 181);

		PhaseChoice = ConductorChoice.TapeShield;
		WireData = new ConductorDataObj[LineSpacingObj.getNWires()];
		Globals.getAuxParser().setCmdString(Code);
		for (i = 0; i < LineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			Globals.getTSDataClass().setCode(Globals.getAuxParser().makeString());
			if (ConductorDataImpl.getActiveConductorDataObj() != null) {
				WireData[i] = ConductorDataImpl.getActiveConductorDataObj();
			} else {
				Globals.doSimpleMsg("TS cable " + Globals.getAuxParser().makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchGeometryCode(String Code) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (LineImpl.getLineGeometryClass() == null)
			LineImpl.setLineGeometryClass((LineGeometry) Globals.getDSSClassList().get(Globals.getClassNames().find("LineGeometry")));

		if (LineImpl.getLineGeometryClass().setActive(Code)) {
			LineCodeSpecified = false;  // cancel this flag
			SpacingSpecified  = false;

			setLineGeometryObj((LineGeometryObj) LineImpl.getLineGeometryClass().getActiveObj());
			ZFrequency = -1.0;  // init to signify not computed

			GeometryCode     = Code.toLowerCase();

			if (rhoSpecified)
				LineGeometryObj.setRhoEarth(rho);

			setNormAmps(LineGeometryObj.getNormAmps());
			setEmergAmps(LineGeometryObj.getEmergAmps());
			updatePDProperties();

			setNPhases( LineGeometryObj.getNconds() );
			setNConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);  // force rebuild of Y matrix
		} else {
			Globals.doSimpleMsg("Line geometry object:" + Code + " not found.", 181);
		}
	}

	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromGeometry(double f) {

		if (f == ZFrequency)
			return;  // already done for this frequency, no need to do anything

		if (LineGeometryObj != null) {
			/* This will make a new Z; throw away present allocations */

			if (Z != null) Z = null;
			if (Zinv != null) Zinv = null;
			if (Yc != null) Yc = null;

			DSSGlobals.getInstance().setActiveEarthModel(getEarthModel());

			Z    = getLineGeometryObj().getZmatrix(f, Len, LengthUnits);
			Yc   = getLineGeometryObj().getYCmatrix(f, Len, LengthUnits);
			/* init Zinv */
			if (Z != null) {
				Zinv = new CMatrixImpl(Z.getNOrder());  // either no. phases or no. conductors
				Zinv.copyFrom(Z);
			}

			// Z and Yc are actual total impedance for the line

			ZFrequency = f;
		}
	}

	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromSpacing(double f) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (f == ZFrequency)
			return;  // already done for this frequency, no need to do anything

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;
		if (Yc != null) Yc = null;

		// make a temporary LineGeometry to calculate line constants
		if (LineImpl.getLineGeometryClass() == null)
			LineImpl.setLineGeometryClass( (LineGeometry) Globals.getDSSClassList().get(Globals.getClassNames().find("LineGeometry")) );
		LineGeometryObj pGeo = new LineGeometryObjImpl(LineImpl.getLineGeometryClass(), "==");
		pGeo.loadSpacingAndWires(getLineSpacingObj(), getWireData());  // this sets OH, CN, or TS

		if (rhoSpecified)
			pGeo.setRhoEarth(rho);
		setNormAmps(pGeo.getNormAmps());
		setEmergAmps(pGeo.getEmergAmps());
		updatePDProperties();

		Globals.setActiveEarthModel(EarthModel);

		Z  = pGeo.getZmatrix(f, Len, LengthUnits);
		Yc = pGeo.getYCmatrix(f, Len, LengthUnits);
		if (Z != null) {
			Zinv = new CMatrixImpl(Z.getNOrder());  // either no. phases or no. conductors
			Zinv.copyFrom(Z);
		}
		pGeo = null;

		ZFrequency = f;
	}

	/**
	 * Indicate no line geometry specification if this is called.
	 */
	// FIXME Private method in OpenDSS
	public void killGeometrySpecified() {
		if (GeometrySpecified) {
			LineGeometryObj = null;
			ZFrequency      = -1.0;
			GeometrySpecified = false;
		}
	}

	// FIXME Private method in OpenDSS
	public void killSpacingSpecified() {
		if (SpacingSpecified) {
			LineSpacingObj = null;
			WireData = new WireDataObj[0];
			PhaseChoice = ConductorChoice.Unknown;
			ZFrequency = -1.0;
			SpacingSpecified = false;
		}
	}

	private void clearYPrim() {
		// line object needs both series and shunt YPrims built
		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			if (YPrimSeries != null) YPrimSeries = null;
			if (YPrimShunt != null) YPrimShunt = null;
			if (YPrim != null) YPrim = null;

			YPrimSeries = new CMatrixImpl(YOrder);
			YPrimShunt  = new CMatrixImpl(YOrder);
			YPrim        = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();   // zero out YPrim_Series
			YPrimShunt.clear();    // zero out YPrim_Shunt
			YPrim.clear();          // zero out YPrim
		}
	}

	/**
	 * If specify the impedances always assume the length units match.
	 */
	// FIXME Private method in OpenDSS
	public void resetLengthUnits() {
		UnitsConvert = 1.0;
		LengthUnits  = LineUnits.UNITS_NONE;
	}

	private int numConductorData() {
		if (WireData != null) return LineSpacingObj.getNWires();
		if (LineGeometryObj != null) return LineGeometryObj.getNWires();
		return 0;
	}

	private ConductorDataObj fetchConductorData(int i) {
		if (WireData != null) {
			if (i <= LineSpacingObj.getNWires()) return WireData[i];
		} else if (LineGeometryObj != null) {
			if (i <= LineGeometryObj.getNWires()) return LineGeometryObj.getConductorData(i);
		}
		return null;
	}

	private void updatePDProperties() {
		setPropertyValue(Line.NumPropsThisClass + 0, String.format("%-g", getNormAmps()));
		setPropertyValue(Line.NumPropsThisClass + 1, String.format("%-g", getEmergAmps()));
		setPropertyValue(Line.NumPropsThisClass + 2, String.format("%-g", getFaultRate()));
		setPropertyValue(Line.NumPropsThisClass + 3, String.format("%-g", getPctPerm()));
		setPropertyValue(Line.NumPropsThisClass + 4, String.format("%-g", getHrsToRepair()));
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

	public boolean isLineCodeSpecified() {
		return LineCodeSpecified;
	}

	public ConductorChoice getPhaseChoice() {
		return PhaseChoice;
	}

	public int numConductorsAvailable() {
		return numConductorData();
	}

	public ConductorDataObj getConductorData(int i) {
		return fetchConductorData(i);
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

	public ConductorDataObj[] getWireData() {
		return WireData;
	}

	public void setWireData(ConductorDataObj[] wireData) {
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

	public boolean isCapSpecified() {
		return CapSpecified;
	}

	public void setCapSpecified(boolean capSpecified) {
		CapSpecified = capSpecified;
	}

}

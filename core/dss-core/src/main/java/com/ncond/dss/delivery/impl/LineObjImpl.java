package com.ncond.dss.delivery.impl;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;


import org.apache.commons.math.complex.Complex;


import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.delivery.Line;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.general.ConductorDataObj;
import com.ncond.dss.general.LineCode;
import com.ncond.dss.general.LineCodeObj;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.general.LineGeometryObj;
import com.ncond.dss.general.LineSpacingObj;
import com.ncond.dss.general.WireDataObj;
import com.ncond.dss.general.impl.ConductorChoice;
import com.ncond.dss.general.impl.ConductorDataImpl;
import com.ncond.dss.general.impl.LineGeometryObjImpl;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.impl.CMatrixImpl;
import com.ncond.dss.shared.impl.ComplexUtil;
import com.ncond.dss.shared.impl.LineUnits;
import com.ncond.dss.shared.impl.MathUtil;

public class LineObjImpl extends PDElementImpl implements LineObj {

	/* keep track of last frequency computed for geometry */
	private double ZFrequency;
	private int lineCodeUnits;
	private double unitsConvert; // conversion factor
	private LineGeometryObj lineGeometryObj;
	private LineSpacingObj lineSpacingObj;
	private ConductorDataObj[] wireData;
	private ConductorChoice phaseChoice;
	private boolean rhoSpecified;
	private boolean lineCodeSpecified;
	private int earthModel;
	private boolean capSpecified;  // to make sure user specifies C in some form

	protected CMatrix ZInv;

	/* Base frequency series Z matrix per unit length */
	protected CMatrix Z;
	protected CMatrix Yc;

	protected double R1;
	protected double X1;
	protected double R0;
	protected double X0;
	protected double C1;
	protected double C0;
	protected double len;
	protected int lengthUnits;
	protected double Rg, Xg, KXg, rho;
	protected double generalPlotQuantity;  // for general circuit plotting
	protected String condCode;
	protected String geometryCode;
	protected String spacingCode;
	protected boolean geometrySpecified;
	protected boolean spacingSpecified;
	protected boolean symComponentsChanged;
	protected boolean symComponentsModel;
	protected boolean isSwitch;

	public LineObjImpl(DSSClass parClass, String lineName) {
		super(parClass);
		setName(lineName.toLowerCase());
		objType = parClass.getDSSClassType();  // DSSObjType + LINESECTION; // in both PD element list and line section lists

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(2);   // force allocation of terminals and conductors
		isSwitch = false;
		R1 = 0.0580;  // ohms per 1000 ft
		X1 = 0.1206;
		R0 = 0.1784;
		X0 = 0.4047;
		C1 = 3.4e-9;  // nf per 1000ft
		C0 = 1.6e-9;
		len = 1.0;   // 1 kFt
		Z    = null;
		ZInv = null;
		Yc   = null;
		condCode = "";

		Rg = 0.01805;  // ohms per 1000 ft
		Xg = 0.155081;
		rho = 100.0;
		KXg = Xg / Math.log(658.5 * Math.sqrt(rho / baseFrequency));
		rhoSpecified = false;
		capSpecified = false;

		/*baseFrequency = 60.0;*/  // set in base class
		normAmps = 400.0;
		emergAmps = 600.0;
		pctPerm = 20.0;
		faultRate = 0.1;
		hrsToRepair = 3.0;

		symComponentsChanged = false;
		symComponentsModel = true;

		geometrySpecified = false;
		geometryCode      = "";
		lengthUnits       = LineUnits.UNITS_NONE;  // assume everything matches
		unitsConvert      = 1.0;
		lineCodeUnits     = LineUnits.UNITS_NONE;
		lineCodeSpecified = false;
		earthModel        = DSS.defaultEarthModel;

		spacingSpecified = false;
		lineSpacingObj = null;
		wireData = null;
		phaseChoice = ConductorChoice.UNKNOWN;
		spacingCode = "";

		ZFrequency = -1.0;  // indicate Z not computed.
		lineGeometryObj = null;

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	public void fetchLineCode(String code) {

		if (LineImpl.lineCodeClass == null)
			LineImpl.lineCodeClass = (LineCode) DSS.DSSClassList.get(DSS.classNames.find("linecode"));

		if (LineImpl.lineCodeClass.setActive(code)) {

			LineCodeObj lc = (LineCodeObj) LineImpl.lineCodeClass.getActiveObj();

			condCode = code.toLowerCase();

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
				symComponentsModel = true;
			} else {
				symComponentsModel = false;
			}

			// earth return impedances used to compensate for frequency
			Rg  = lc.getRg();
			Xg  = lc.getXg();
			rho = lc.getRho();
			KXg = Xg / Math.log(658.5 * Math.sqrt(rho / baseFrequency));

			lineCodeUnits = lc.getUnits();
			lineCodeSpecified = true;

			unitsConvert = LineUnits.convertLineUnits(lineCodeUnits, lengthUnits);

			setNormAmps(lc.getNormAmps());
			setEmergAmps(lc.getEmergAmps());
			setFaultRate(lc.getFaultRate());
			setPctPerm(lc.getPctPerm());
			setHrsToRepair(lc.getHrsToRepair());
			updatePDProperties();

			if (getNumPhases() != lc.getNPhases()) {
				setNumPhases(lc.getNPhases());
				// for a line, nPhases = nCond, for now
				Z    = new CMatrixImpl(nPhases);
				ZInv = new CMatrixImpl(nPhases);
				Yc   = new CMatrixImpl(nPhases);
			}

			if (!symComponentsModel) {
				// copy matrices
				Z.copyFrom(lc.getZ());
				/*Zinv.copyFrom(lc.getZinv());*/  // no need to copy Zinv
				Yc.copyFrom(lc.getYC());
			} else {
				recalcElementData();  // compute matrices
			}

			setNumConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			//setYprimInvalid(true);  // set in edit; this is redundant
		} else {
			DSS.doSimpleMsg("Line Code:" + code + " not found.", 180);
		}
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm, Ys, Ym, ZTemp;
		double Yc1, Yc0, oneThird;
		Complex gammaL, expP, expM, exp2P, exp2M, sinhGL, tanh2GL;

		Circuit ckt = DSS.activeCircuit;

		// for a line, nPhases = nCond, for now
		Z    = new CMatrixImpl(getNumPhases());
		ZInv = new CMatrixImpl(getNumPhases());
		Yc   = new CMatrixImpl(getNumPhases());

		oneThird = 1.0 / 3.0;  // do this to get more precision in next few statements

		/* Only time this is called is if symmetrical components are specified */

		ZTemp = new Complex(R1, X1).multiply(2.0);
		/* Handle special case for 1-phase line and/or pos seq model */
		if (getNumPhases() == 1 || ckt.isPositiveSequence()) {
			// long-line equivalent pi, but only for cktModel=positive
			if (ckt.isPositiveSequence() && C1 > 0) {
				// nominal pi parameters per unit length
				Zs = new Complex(R1, X1);
				Ys = new Complex(0.0, DSS.TWO_PI * baseFrequency * C1);
				// apply the long-line correction to obtain Zm and Ym
				gammaL = Zs.multiply(Ys).sqrt();
				gammaL = gammaL.multiply(len);
				expP = new Complex(Math.cos(gammaL.getImaginary()), Math.sin(gammaL.getImaginary())).multiply(Math.exp(gammaL.getReal()));
				exp2P = new Complex(Math.cos(0.5 * gammaL.getImaginary()), Math.sin(0.5 * gammaL.getImaginary())).multiply(Math.exp(0.5 * gammaL.getReal()));
				expM = ComplexUtil.invert(expP);
				exp2M = ComplexUtil.invert(exp2P);
				sinhGL = expP.subtract(expM).multiply(0.5);
				tanh2GL = exp2P.subtract(exp2M).divide(exp2P.add(exp2M));
				Zm = Zs.multiply(len).multiply(sinhGL).divide(gammaL);
				Ym = Ys.multiply(len).multiply(tanh2GL).divide(gammaL.multiply(0.5));
				// rely on this function being called only once, unless r1, x1, or c1 changes
				R1 = Zm.getReal() / len;
				X1 = Zm.getImaginary() / len;
				C1 = Ym.getImaginary() / len / DSS.TWO_PI / baseFrequency;
			}
			// zero sequence the same as positive sequence
			R0 = R1;
			X0 = X1;
			C0 = C1;
		}
		Zs = ZTemp.add(new Complex(R0, X0)).multiply(oneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(oneThird);

		Yc1 = DSS.TWO_PI * baseFrequency * C1;
		Yc0 = DSS.TWO_PI * baseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add( new Complex(0.0, Yc0) ).multiply(oneThird);
		Ym = new Complex(0.0, Yc0).subtract( new Complex(0.0, Yc1) ).multiply(oneThird);

		for (int i = 0; i < getNumPhases(); i++) {
			Z.set(i, i, Zs);
			Yc.set(i, i, Ys);
			for (int j = 0; j < i; j++) {
				Z.setSym(i, j, Zm);
				Yc.setSym(i, j, Ym);
			}
		}

		symComponentsChanged = false;
	}

	@Override
	public void calcYPrim() {

		Complex value;
		Complex[] ZInvValues;
		Complex[] ZValues;
		Complex[] YValues;

		double XgMod;
		int k;
		int[] norder = new int[1];

		double freqMultiplier = 1.0;
		double lengthMultiplier = 1.0;

		Circuit ckt = DSS.activeCircuit;

		if (symComponentsChanged) {
			/* Try to catch inadvertent user error when they forget to specify C1 and C0 */
			/* Check to see if user has spec'd C1 and C0. If not, adjust default values for new length units */
			if (!capSpecified) {
				C1 = C1 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, lengthUnits);  // were defined in kft
				C0 = C0 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, lengthUnits);
				capSpecified = true;   // so we don't do it again
			}
			recalcElementData();
		}

		clearYPrim();

		// build series YPrim
//		CMatrix Y = YPrim_Series;

		/* Build Z matrix */
		if (geometrySpecified) {

			makeZFromGeometry(ckt.getSolution().getFrequency());  // includes length in proper units
			if (DSS.solutionAbort)
				return;

		} else if (spacingSpecified) {

			makeZFromSpacing(ckt.getSolution().getFrequency());
			if (DSS.solutionAbort)
				return;

		} else {  // Z is from line code or specified in line data

			lengthMultiplier = len / unitsConvert;  // convert to per unit length
			YPrimFreq        = ckt.getSolution().getFrequency();
			freqMultiplier   = YPrimFreq / baseFrequency;

			/* Put in series RL */
			ZValues    = Z.asArray(norder);
			ZInvValues = ZInv.asArray(norder);
			// Correct the impedances for length and frequency
			// Rg increases with frequency
			// Xg modified by ln of sqrt(1/f)
			if (Xg != 0.0) {
				XgMod =  0.5 * KXg * Math.log(freqMultiplier);
			} else {
				XgMod = 0.0;
			}

			for (int i = 0; i < norder[0] * norder[0]; i++) {
				ZInvValues[i] = new Complex((ZValues[i].getReal() + Rg * (freqMultiplier - 1.0) ) * lengthMultiplier,
						(ZValues[i].getImaginary() - XgMod) * lengthMultiplier * freqMultiplier);
			}

			ZInv.invert();  /* Invert in place */
			if (ZInv.getErrorCode() > 0) {
				/* If error, put in tiny series conductance */
				// TEMc - shut this up for the CDPSM connectivity profile test, or whenever else it gets annoying
				DSS.doErrorMsg("LineObj.calcYPrim", "Matrix inversion error for line \"" + getName() + "\"",
							"Invalid impedance specified. Replaced with tiny conductance.", 183);
				ZInv.clear();
				for (int i = 0; i < getNumPhases(); i++)
					ZInv.set(i, i, new Complex(DSS.EPSILON, 0.0));
			} else {
				/* Now, put in Yprim_Series matrix */
				for (int i = 0; i < getNumPhases(); i++) {
					for (int j = 0; j < getNumPhases(); j++) {
						value = ZInv.get(i, j);
						YPrimSeries.set(i, j, value);
						YPrimSeries.set(i + getNumPhases(), j + getNumPhases(), value);
						value = value.negate();
						YPrimSeries.setSym(i, j + getNumPhases(), value);
					}
				}
			}

			YPrim.copyFrom(YPrimSeries);  // initialize YPrim for series impedances

			// 10/3/2006 moved this to after the copy to Yprim so it doesn't affect normal line model capacitance
			// 3-30-04  ----- Rev 2-4-09 to include both sides of line
			// increase diagonal elements of both sides of line so that we will avoid isolated bus problem
			// add equivalent of 10 kvar capacitive at 345 kV
			for (int i = 0; i < YOrder; i++)
				YPrimSeries.add(i, i, Line.CAP_EPSILON);
		}

		// now build the shunt admittances and add into YPrim

		/* Put half the shunt capacitive admittance at each end */
		YValues = Yc.asArray(norder);

		if (geometrySpecified || spacingSpecified) {

			/* Values are already compensated for length and frequency */
			k = 0;
			for (int j = 0; j < getNumPhases(); j++) {
				for (int i = 0; i < getNumPhases(); i++) {
					value = ComplexUtil.divide(YValues[k], 2.0);  // half at each end ...
					YPrimShunt.add(i, j, value);
					YPrimShunt.add(i + getNumPhases(), j + getNumPhases(), value);
					k += 1;  // assume matrix in col order (1,1  2,1  3,1 ...)
				}
			}
		} else {
			/* Regular line model - values computed per unit length at base frequency */
			k = 0;
			for (int j = 0; j < getNumPhases(); j++) {
				for (int i = 0; i < getNumPhases(); i++) {
					value = new Complex(0.0, YValues[k].getImaginary() * lengthMultiplier * freqMultiplier / 2.0);
					YPrimShunt.add( i, j, value);
					YPrimShunt.add(i + getNumPhases(), j + getNumPhases(), value);
					k += 1;  // assume matrix in col order (1,1  2,1  3,1 ...)
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
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		DSSClass pc = getParentClass();

		pw.println("~ " + pc.getPropertyName(0) + "=" + getFirstBus());
		pw.println("~ " + pc.getPropertyName(1) + "=" + getNextBus());

		pw.println("~ " + pc.getPropertyName(2) + "=" + getCondCode());
		pw.println("~ " + pc.getPropertyName(3) + "=" + getLen());
		pw.println("~ " + pc.getPropertyName(4) + "=" + getNumPhases());
		pw.println("~ " + pc.getPropertyName(5) + "=" + getR1());
		pw.println("~ " + pc.getPropertyName(6) + "=" + getX1());
		pw.println("~ " + pc.getPropertyName(7) + "=" + getR0());
		pw.println("~ " + pc.getPropertyName(8) + "=" + getX0());
		pw.println("~ " + pc.getPropertyName(9) + "=" + getC1() * 1.0e9);
		pw.println("~ " + pc.getPropertyName(10) + "=" + getC0() * 1.0e9);
		pw.print("~ " + pc.getPropertyName(11) + "=" + "\"");
		for (int i = 0; i < getNumPhases(); i++) {
			for (int j = 0; j < getNumPhases(); j++)
				pw.print(Z.get(i, j).getReal() + " ");
			pw.print("|");
		}
		pw.println("\"");
		pw.print("~ " + pc.getPropertyName(12) + "=" + "\"");
		for (int i = 0; i < getNumPhases(); i++) {
			for (int j = 0; j < getNumPhases(); j++)
				pw.print(Z.get(i, j).getImaginary() + " ");
			pw.print("|");
		}
		pw.println("\"");
		pw.print("~ " + pc.getPropertyName(13) + "=" + "\"");
		for (int i = 0; i < getNumPhases(); i++) {
			for (int j = 0; j < getNumPhases(); j++)
				pw.print((Yc.get(i, j).getImaginary() / DSS.TWO_PI / baseFrequency * 1.e9) + " ");
			pw.print("|");
		}
		pw.println("\"");

		pw.print("~ " + pc.getPropertyName(13) + "=");

		pw.println(isSwitch ? "true" : "false");

		/* Dump the rest by default */
		for (int i = 14; i < pc.getNumProperties(); i++)
			pw.println("~ " + pc.getPropertyName(i) + "=" + getPropertyValue(i));

		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		int i, j;
		double factor;
		String result;

		if (index >= 11 && index <= 13) {
			result = "[";
		} else {
			result = "";
		}

		/* Report impedance values in ohms per unit length of present length units */
		switch (index) {
		case 0:
			result = getBus(0);
			break;
		case 1:
			result = getBus(1);
			break;
		case 3:
			result = String.format("%-.7g", len);
			break;
		case 4:
			result = String.format("%d", nPhases);
			break;
		case 5:
			if (symComponentsModel) {
				result = String.format("%-.7g", R1 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 6:
			if (symComponentsModel) {
				result = String.format("%-.7g", X1 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 7:
			if (symComponentsModel) {
				result = String.format("%-.7g", R0 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 8:
			if (symComponentsModel) {
				result = String.format("%-.7g", X0 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 9:
			if (symComponentsModel) {
				result = String.format("%-.7g", C1 * 1.0e9 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 10:
			if (symComponentsModel) {
				result = String.format("%-.7g", C0 * 1.0e9 / unitsConvert);
			} else {
				result = "----";
			}
			break;
		case 11:
			for (i = 0; i < getNumConds(); i++) {
				for (j = 0; j < i; j++) {
					// report in per unit length in length units
					if (geometrySpecified || spacingSpecified) {
						result = result + String.format("%-.7g", Z.get(i, j).getReal() / len) + " ";
					} else {
						result = result + String.format("%-.7g", Z.get(i, j).getReal() / unitsConvert) + " ";
					}
					if (i < nConds - 1)
						result = result + "|";
				}
			}
			break;
		case 12:
			for (i = 0; i < nConds; i++) {
				for (j = 0; j < i; j++) {
					// X matrix
					if (geometrySpecified || spacingSpecified) {
						result = result + String.format("%-.7g", Z.get(i, j).getImaginary() / len) + " ";
					} else {
						result = result + String.format("%-.7g", Z.get(i, j).getImaginary() / unitsConvert) + " ";
					}
				}
				if (i < nConds - 1)
					result = result + "|";
			}
			break;
		case 13:  // CMatrix nf
			factor = DSS.TWO_PI * baseFrequency * 1.0e-9;
			for (i = 0; i < nConds; i++) {
				for (j = 0; j < i; j++) {
					if (geometrySpecified || spacingSpecified) {
						result = result + String.format("%-.7g", Yc.get(i, j).getImaginary() / factor / len) + " ";
					} else {
						result = result + String.format("%-.7g", Yc.get(i, j).getImaginary() / factor / unitsConvert) + " ";
					}
				}
				if (i < nConds - 1)
					result = result + "|";
			}
			break;
		case 14:
			if (isSwitch) {
				result = "True";
			} else {
				result = "False";
			}
			break;
		case 15:
			result = String.format("%-g", Rg);
			break;
		case 16:
			result = String.format("%-g", Xg);
			break;
		case 17:
			result = String.format("%-g", rho);
			break;
		case 22:
			result = Util.getEarthModel(earthModel);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		if (index >= 11 && index <= 13)
			result = result + "[";

		return result;
	}

	/**
	 * Only consider 3-phase branches with pos seq >> neg seq
	 * Otherwise, we don't know whether it is a 3-phase line or just a line with 3 phases
	 */
	public void getSeqLosses(Complex posSeqLosses, Complex negSeqLosses, Complex zeroSeqLosses) {
		int k;

		Complex[] Vph = new Complex[2];
		Complex[] V012 = new Complex[2];
		Complex[] I012 = new Complex[2];

		posSeqLosses  = Complex.ZERO;
		negSeqLosses  = Complex.ZERO;
		zeroSeqLosses = Complex.ZERO;

		/* Method: sum seq powers going into each terminal */

		if (nPhases == 3) {
			/* 3-phase lines only */
			computeITerminal();
			for (int i = 0; i < 2; i++) {
				k = i * nPhases;
				for (int j = 0; j < 3; j++)
					Vph[j] = DSS.activeCircuit.getSolution().getNodeV(nodeRef[k + j]);
				MathUtil.phase2SymComp(Vph, V012);
				MathUtil.phase2SymComp(ITerminal[k], I012);
				posSeqLosses = posSeqLosses.add( V012[1].multiply(I012[1].conjugate()) );
				negSeqLosses = negSeqLosses.add( V012[2].multiply(I012[2].conjugate()) );  // accumulate both line modes
				zeroSeqLosses = zeroSeqLosses.add( V012[0].multiply(I012[0].conjugate()) );
			}
			posSeqLosses = posSeqLosses.multiply(3.0);
			negSeqLosses = negSeqLosses.multiply(3.0);
			zeroSeqLosses = zeroSeqLosses.multiply(3.0);
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, getBus(0));
		setPropertyValue(1, getBus(1));
		setPropertyValue(2, "");
		setPropertyValue(3, "1.0");  // "5.28"; Changed 2/17/00
		setPropertyValue(4, "3");
		setPropertyValue(5, ".058");
		setPropertyValue(6, ".1206");
		setPropertyValue(7, ".1784");
		setPropertyValue(8, ".4047");
		setPropertyValue(9, "3.4");
		setPropertyValue(10, "1.6");
		setPropertyValue(11, "");
		setPropertyValue(12, "");
		setPropertyValue(13, "");
		setPropertyValue(14, "false");
		setPropertyValue(15, "0.01805");
		setPropertyValue(16, "0.155081");
		setPropertyValue(17, "100");
		setPropertyValue(18, "");
		setPropertyValue(19, "NONE");
		setPropertyValue(20, "");
		setPropertyValue(21, "");
		setPropertyValue(22, Util.getEarthModel(DSS.SIMPLECARSON));

		super.initPropertyValues(Line.NumPropsThisClass - 1);

		// override inherited properties just in case
		setPropertyValue(Line.NumPropsThisClass + 0, "400");  // normAmps
		setPropertyValue(Line.NumPropsThisClass + 1, "600");  // emergAmps
		setPropertyValue(Line.NumPropsThisClass + 2, "0.1");  // faultRate
		setPropertyValue(Line.NumPropsThisClass + 3, "20");   // pctPerm
		setPropertyValue(Line.NumPropsThisClass + 4, "3");    // hrsToRepair

		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String s;
		double C1New, Cs, Cm;
		Complex Z1, ZS, Zm;
		int i, j;

		// set to single phase and make sure r1, x1, c1 set.
		// if already single phase, let alone
		if (nPhases > 1) {
			// kill certain propertyValue elements to get a cleaner looking save
			prpSequence[2] = 0;
			for (i = 5; i < 13; i++)
				prpSequence[i] = 0;

			if (isSwitch) {
				s = " R1=1 X1=1 C1=1.1 Phases=1 Len=0.001";
			} else {
				if (symComponentsModel) {  // keep the same Z1 and C1
					Z1 = new Complex(R1, X1);
					C1New = C1 * 1.0e9;  // convert to nF
				} else {  // matrix was input directly, or built from physical data
					// average the diagonal and off-dialgonal elements
					ZS = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						ZS = ZS.add( Z.get(i, i) );
					ZS = ComplexUtil.divide(ZS, nPhases);
					Zm = Complex.ZERO;
					for (i = 0; i < nPhases - 1; i++)
						for (j = i + 1; j < nPhases; j++)
							Zm = Zm.add( Z.get(i, j) );
					Zm = ComplexUtil.divide(Zm, nPhases * (nPhases - 1.0) / 2.0);
					Z1 = ZS.subtract(Zm);

					// do same for capacitances
					Cs = 0.0;
					for (i = 0; i < nPhases; i++)
						Cs = Cs + Yc.get(i, i).getImaginary();
					Cm = 0.0;
					for (i = 1; i < nPhases; i++)
						for (j = i + 1; j < nPhases; j++)
							Cm = Cm + Yc.get(i, j).getImaginary();
					C1New = (Cs - Cm) / DSS.TWO_PI / baseFrequency/ (nPhases * (nPhases - 1.0) / 2.0) * 1.0e9; // nanofarads
				}
				s = String.format(" R1=%-.5g  %-.5g  C1=%-.5g Phases=1", Z1.getReal(), Z1.getImaginary(), C1New);
			}
			// conductor current ratings
			s = s + String.format(" Normamps=%-.5g  %-.5g", getNormAmps(), getEmergAmps());
			Parser.getInstance().setCmdString(s);
			edit();
		}

		super.makePosSequence();
	}

	/**
	 * Merge this line with another line and disble the other line.
	 */
	public boolean mergeWith(LineObj otherLine, boolean series) {
		Complex[] values1, values2;
		int[] order1 = new int[1];
		int[] order2 = new int[1];
		int i, j, common1, common2;
		double totalLen, wnano;
		String s = "", newName;
		int testBusNum;
		int lenUnitsSaved;
		Complex newZ;
		double lenSelf, lenOther;

		boolean result = false;
		if (otherLine != null) {
			if (nPhases != otherLine.getNumPhases())
				return result;  // can't merge

			lenUnitsSaved = lengthUnits;

			setYPrimInvalid(true);

			// redefine property values to make it appear that line was defined this way originally using matrices

			if (series) {
				totalLen = len + otherLine.getLen() * LineUnits.convertLineUnits(otherLine.getLengthUnits(), lengthUnits);
			} else {
				totalLen = 1.0;
			}

			if (series) {
				/* redefine the bus connections */

				// find bus in common between the two lines
				common1 = -1;
				common2 = -1;
				i = 0;
				while (common1 == -1 && i < 2) {
					testBusNum = DSS.activeCircuit.getMapNodeToBus()[nodeRef[1 + i * nConds]].busRef;
					for (j = 0; j < 2; j++) {
						if (DSS.activeCircuit.getMapNodeToBus()[otherLine.getNodeRef()[1 + j * otherLine.getNumConds()]].busRef == testBusNum) {
							common1 = i;
							common2 = j;
							break;
						}
					}
					i += 1;
				}

				if (common1 == -1)
					return result;  // there's been an error; didn't find anything in common

				/* redefine the bus connections */
				switch (common1) {
				case 0:
					switch (common2) {
					case 0:
						s = "bus1=\"" + otherLine.getBus(1) + "\"";
						break;
					case 1:
						s = "bus1=\"" + otherLine.getBus(0) + "\"";
						break;
					}
					break;
				case 1:
					switch (common2) {
					case 0:
						s = "bus2=\"" + otherLine.getBus(1) + "\"";
						break;
					case 1:
						s = "bus2=\"" + otherLine.getBus(0) + "\"";
						break;
					}
					break;
				}

				Parser.getInstance().setCmdString(s);
				edit();
			}

			/* Rename the line */
			if (series) {
				newName = Util.stripExtension(getBus(0)) + "~"  + Util.stripExtension(getBus(1));
			} else {
				newName = Util.stripExtension(getBus(0)) + "||" + Util.stripExtension(getBus(1));
			}

			/* Update control element connections to this line */
			updateControlElements("line." + newName, "line." + getName());
			updateControlElements("line." + newName, "line." + otherLine.getName());
			setName(newName);

			if (series)
				isSwitch = false;  // not allowed on series merge.

			/* Now do the impedances */

			lenSelf = len / unitsConvert;  // in units of r x Data
			lenOther = otherLine.getLen() / otherLine.getUnitsConvert();

			if (symComponentsModel) {
				/*------------------ Sym Component Model ---------------------*/
				if (series) {
					s = " R1=" + String.format("%-g", (R1 * lenSelf + otherLine.getR1() * lenOther) / totalLen);  // ohms per unit length of this line length units
					s = s + String.format(" %-g", (X1 * lenSelf + otherLine.getX1() * lenOther) / totalLen);
					s = s + String.format(" %-g", (R0 * lenSelf + otherLine.getR0() * lenOther) / totalLen);
					s = s + String.format(" %-g", (X0 * lenSelf + otherLine.getX0() * lenOther) / totalLen);
					s = s + String.format(" %-g", (C1 * lenSelf + otherLine.getC1() * lenOther) / totalLen * 1.0e9);
					s = s + String.format(" %-g", (C0 * lenSelf + otherLine.getC0() * lenOther) / totalLen * 1.0e9);
				} else {
					/* Parallel */
					if (isSwitch) {
						s = "";   /* Leave as is if switch; just dummy z anyway */
					} else if (otherLine.isSwitch()) {
						s = " switch=yes";  /* This will take care of setting Z's */
					} else {
						/* ********* Will This work with length multiplier? did it ever work? ************************* */
						newZ = MathUtil.parallelZ(new Complex(R1 * len, X1 * len),
								new Complex(otherLine.getR1() * otherLine.getLen(), otherLine.getX1() * otherLine.getLen()));
						s = " R1=" + String.format("%-g %-g ", newZ.getReal(), newZ.getImaginary());
						newZ = MathUtil.parallelZ(new Complex(R0 * len, X0 * len),
								new Complex(otherLine.getR0() * otherLine.getLen(), otherLine.getX0() * otherLine.getLen()));
						s = " R0=" + String.format("%-g %-g ", newZ.getReal(), newZ.getImaginary());
						s = s + String.format(" %-g", (C1 * len + otherLine.getC1() * otherLine.getLen()) / totalLen * 1.0e9);
						s = s + String.format(" %-g", (C0 * len + otherLine.getC0() * otherLine.getLen()) / totalLen * 1.0e9);
					}
				}
				Parser.getInstance().setCmdString(s);   // this reset the length units
				edit();
			} else {
				/*------------------- Matrix Model ---------------------------*/
				if (!series) {
					totalLen = len / 2.0;  /* we'll assume lines are equal for now */
				} else {
					/* Matrices were defined */

					// merge Z matrices
					values1 = Z.asArray(order1);
					values2 = otherLine.getZ().asArray(order2);

					if (order1[0] != order2[0])
						return result;  // lines not same size for some reason

					// Z <= (Z1 + Z2) / TotalLen to get equiv ohms per unit length
					for (i = 0; i < order1[0] * order1[0]; i++)
						values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add(values2[i].multiply(lenOther)), totalLen);

					// merge Yc matrices
					values1 = Yc.asArray(order1);
					values2 = otherLine.getYc().asArray(order2);

					if (order1[0] != order2[0])
						return result;  // lines not same size for some reason

					for (i = 0; i < order1[0] * order1[0]; i++)
						values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add( values2[i].multiply(lenOther) ), totalLen);

					/* R matrix */
					s = "Rmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %-g", Z.get(i, j).getReal());
						s = s + " | ";
					}
					s = s + "] Xmatrix=[";
					/* X matrix */
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %-g", Z.get(i, j).getImaginary());
						s = s + " | ";
					}
					s = s + "]";
					Parser.getInstance().setCmdString(s);
					edit();

					/* C matrix */
					wnano = DSS.TWO_PI * baseFrequency / 1.0e9;
					s = "Cmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %-g", (Yc.get(i, j).getImaginary() / wnano));  // convert from mhos to nanofs
						s = s + " | ";
					}
					s = s + "] ";
					Parser.getInstance().setCmdString(s);
					edit();
				}
			}  // matrix definition

			Parser.getInstance().setCmdString(String.format(" length=%-g units=%s", totalLen, LineUnits.lineUnitsStr(lenUnitsSaved)));
			edit();

			otherLine.setEnabled(false);  // disable the other line
			result = true;
		} else {
			DSS.doSimpleMsg("Error in line merge: Attempt to merge with invalid (nil) line object found.", 184);
		}

		return result;
	}

	public void updateControlElements(String newName, String oldName) {
		Circuit ckt = DSS.activeCircuit;
		for (ControlElem pControlElem : ckt.getDSSControls())
			if (oldName.equalsIgnoreCase( pControlElem.getElementName() )) {
				Parser.getInstance().setCmdString(" Element=" + newName);  // change name of the property
				pControlElem.edit();
			}
	}

	public void fetchLineSpacing(String code) {

		if (DSS.lineSpacingClass.setActive(code)) {
			setLineSpacingObj( (LineSpacingObj) DSS.lineSpacingClass.getActiveObj() );
			lineCodeSpecified = false;
			killGeometrySpecified();
			spacingCode = code.toLowerCase();

			// need to establish Yorder before makeZFromSpacing
			setNumPhases( lineSpacingObj.getNPhases() );
			setNumConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);  // force rebuild of Y matrix
		} else {
			DSS.doSimpleMsg("Line spacing object " + code + " not found.", 181);
		}
	}

	public void fetchWireList(String code) {
		int i, istart;

		if (getLineSpacingObj() == null)
			DSS.doSimpleMsg("Must assign the LineSpacing before wires.", 181);

		if (phaseChoice == ConductorChoice.UNKNOWN) {  // it's an overhead line
			lineCodeSpecified = false;
			killGeometrySpecified();
			wireData = new ConductorDataObj[lineSpacingObj.getNWires()];
			istart = 0;
			phaseChoice = ConductorChoice.OVERHEAD;
		} else {  // adding bare neutrals to an underground line - TODO what about repeat invocation?
			istart = lineSpacingObj.getNPhases();
		}

		DSS.auxParser.setCmdString(code);
		for (i = istart; i < lineSpacingObj.getNWires(); i++) {
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.wireDataClass.setCode(DSS.auxParser.makeString());
			if (ConductorDataImpl.activeConductorDataObj != null) {
				wireData[i] = ConductorDataImpl.activeConductorDataObj;
			} else {
				DSS.doSimpleMsg("Wire " + DSS.auxParser.makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchCNCableList(String code) {
		int i;

		lineCodeSpecified = false;
		killGeometrySpecified();
		if (lineSpacingObj == null)
			DSS.doSimpleMsg("Must assign the LineSpacing before CN cables.", 181);

		phaseChoice = ConductorChoice.CONCENTRIC_NEUTRAL;
		wireData = new ConductorDataObj[lineSpacingObj.getNWires()];
		DSS.auxParser.setCmdString(code);
		for (i = 0; i < lineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.CNDataClass.setCode(DSS.auxParser.makeString());
			if (ConductorDataImpl.activeConductorDataObj != null) {
				wireData[i] = ConductorDataImpl.activeConductorDataObj;
			} else {
				DSS.doSimpleMsg("CN cable " + DSS.auxParser.makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchTSCableList(String code) {
		int i;

		lineCodeSpecified = false;
		killGeometrySpecified();
		if (lineSpacingObj == null)
			DSS.doSimpleMsg("Must assign the LineSpacing before TS cables.", 181);

		phaseChoice = ConductorChoice.TAPE_SHIELD;
		wireData = new ConductorDataObj[lineSpacingObj.getNWires()];
		DSS.auxParser.setCmdString(code);
		for (i = 0; i < lineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.TSDataClass.setCode(DSS.auxParser.makeString());
			if (ConductorDataImpl.activeConductorDataObj != null) {
				wireData[i] = ConductorDataImpl.activeConductorDataObj;
			} else {
				DSS.doSimpleMsg("TS cable " + DSS.auxParser.makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchGeometryCode(String code) {

		if (LineImpl.lineGeometryClass == null)
			LineImpl.lineGeometryClass = (LineGeometry) DSS.DSSClassList.get(DSS.classNames.find("LineGeometry"));

		if (LineImpl.lineGeometryClass.setActive(code)) {
			lineCodeSpecified = false;  // cancel this flag
			spacingSpecified  = false;

			setLineGeometryObj((LineGeometryObj) LineImpl.lineGeometryClass.getActiveObj());
			ZFrequency = -1.0;  // init to signify not computed

			geometryCode = code.toLowerCase();

			if (rhoSpecified)
				lineGeometryObj.setRhoEarth(rho);

			setNormAmps(lineGeometryObj.getNormAmps());
			setEmergAmps(lineGeometryObj.getEmergAmps());
			updatePDProperties();

			setNumPhases( lineGeometryObj.getNConds() );
			setNumConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			setYPrimInvalid(true);  // force rebuild of Y matrix
		} else {
			DSS.doSimpleMsg("Line geometry object:" + code + " not found.", 181);
		}
	}

	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromGeometry(double f) {

		if (f == ZFrequency)
			return;  // already done for this frequency, no need to do anything

		if (lineGeometryObj != null) {
			/* This will make a new Z; throw away present allocations */

			DSS.activeEarthModel = getEarthModel();

			Z  = getLineGeometryObj().getZMatrix(f, len, lengthUnits);
			Yc = getLineGeometryObj().getYcMatrix(f, len, lengthUnits);
			/* init Zinv */
			if (Z != null) {
				ZInv = new CMatrixImpl(Z.order());  // either no. phases or no. conductors
				ZInv.copyFrom(Z);
			}

			// Z and Yc are actual total impedance for the line

			ZFrequency = f;
		}
	}

	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromSpacing(double f) {

		if (f == ZFrequency)
			return;  // already done for this frequency, no need to do anything

		// make a temporary LineGeometry to calculate line constants
		if (LineImpl.lineGeometryClass == null)
			LineImpl.lineGeometryClass = (LineGeometry) DSS.DSSClassList.get(DSS.classNames.find("LineGeometry"));
		LineGeometryObj pGeo = new LineGeometryObjImpl(LineImpl.lineGeometryClass, "==");
		pGeo.loadSpacingAndWires(getLineSpacingObj(), getWireData());  // this sets OH, CN, or TS

		if (rhoSpecified)
			pGeo.setRhoEarth(rho);
		setNormAmps(pGeo.getNormAmps());
		setEmergAmps(pGeo.getEmergAmps());
		updatePDProperties();

		DSS.activeEarthModel = earthModel;

		Z  = pGeo.getZMatrix(f, len, lengthUnits);
		Yc = pGeo.getYcMatrix(f, len, lengthUnits);
		if (Z != null) {
			ZInv = new CMatrixImpl(Z.order());  // either no. phases or no. conductors
			ZInv.copyFrom(Z);
		}
		pGeo = null;

		ZFrequency = f;
	}

	/**
	 * Indicate no line geometry specification if this is called.
	 */
	// FIXME Private method in OpenDSS
	public void killGeometrySpecified() {
		if (geometrySpecified) {
			lineGeometryObj = null;
			ZFrequency = -1.0;
			geometrySpecified = false;
		}
	}

	// FIXME Private method in OpenDSS
	public void killSpacingSpecified() {
		if (spacingSpecified) {
			lineSpacingObj = null;
			wireData = new WireDataObj[0];
			phaseChoice = ConductorChoice.UNKNOWN;
			ZFrequency = -1.0;
			spacingSpecified = false;
		}
	}

	private void clearYPrim() {
		// line object needs both series and shunt YPrims built
		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			YPrimSeries = new CMatrixImpl(YOrder);
			YPrimShunt  = new CMatrixImpl(YOrder);
			YPrim       = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();   // zero out YPrim_Series
			YPrimShunt.clear();    // zero out YPrim_Shunt
			YPrim.clear();         // zero out YPrim
		}
	}

	/**
	 * If specify the impedances always assume the length units match.
	 */
	// FIXME Private method in OpenDSS
	public void resetLengthUnits() {
		unitsConvert = 1.0;
		lengthUnits = LineUnits.UNITS_NONE;
	}

	private int numConductorData() {
		if (wireData != null)
			return lineSpacingObj.getNWires();
		if (lineGeometryObj != null)
			return lineGeometryObj.getNWires();
		return 0;
	}

	private ConductorDataObj fetchConductorData(int i) {
		if (wireData != null) {
			if (i < lineSpacingObj.getNWires())
				return wireData[i];
		} else if (lineGeometryObj != null) {
			if (i < lineGeometryObj.getNWires())
				return lineGeometryObj.getConductorData(i);
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

	@Override
	public void getInjCurrents(Complex[] curr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
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
		return len;
	}

	public void setLen(double value) {
		len = value;
	}

	public int getLengthUnits() {
		return lengthUnits;
	}

	public void setLengthUnits(int units) {
		lengthUnits = units;
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

	public void setKXg(double kxg) {
		KXg = kxg;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public double getGeneralPlotQuantity() {
		return generalPlotQuantity;
	}

	public void setGeneralPlotQuantity(double quantity) {
		generalPlotQuantity = quantity;
	}

	public String getCondCode() {
		return condCode;
	}

	public void setCondCode(String code) {
		condCode = code;
	}

	public String getGeometryCode() {
		return geometryCode;
	}

	public void setGeometryCode(String code) {
		geometryCode = code;
	}

	public String getSpacingCode() {
		return spacingCode;
	}

	public void setSpacingCode(String code) {
		spacingCode = code;
	}

	public boolean isGeometrySpecified() {
		return geometrySpecified;
	}

	public void setGeometrySpecified(boolean specified) {
		geometrySpecified = specified;
	}

	public boolean isSpacingSpecified() {
		return spacingSpecified;
	}

	public void setSpacingSpecified(boolean specified) {
		spacingSpecified = specified;
	}

	public boolean isSymComponentsChanged() {
		return symComponentsChanged;
	}

	public void setSymComponentsChanged(boolean changed) {
		symComponentsChanged = changed;
	}

	public boolean isSymComponentsModel() {
		return symComponentsModel;
	}

	public void setSymComponentsModel(boolean model) {
		symComponentsModel = model;
	}

	public boolean isSwitch() {
		return isSwitch;
	}

	public void setSwitch(boolean value) {
		isSwitch = value;
	}

	public boolean isLineCodeSpecified() {
		return lineCodeSpecified;
	}

	public ConductorChoice getPhaseChoice() {
		return phaseChoice;
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

	public void setZFrequency(double frequency) {
		ZFrequency = frequency;
	}

	public int getLineCodeUnits() {
		return lineCodeUnits;
	}

	public void setLineCodeUnits(int units) {
		lineCodeUnits = units;
	}

	public double getUnitsConvert() {
		return unitsConvert;
	}

	public void setUnitsConvert(double units) {
		unitsConvert = units;
	}

	public LineGeometryObj getLineGeometryObj() {
		return lineGeometryObj;
	}

	public void setLineGeometryObj(LineGeometryObj obj) {
		lineGeometryObj = obj;
	}

	public LineSpacingObj getLineSpacingObj() {
		return lineSpacingObj;
	}

	public void setLineSpacingObj(LineSpacingObj obj) {
		lineSpacingObj = obj;
	}

	public ConductorDataObj[] getWireData() {
		return wireData;
	}

	public void setWireData(ConductorDataObj[] data) {
		wireData = data;
	}

	public boolean getRhoSpecified() {
		return rhoSpecified;
	}

	public void setRhoSpecified(boolean value) {
		this.rhoSpecified = value;
	}

	public int getEarthModel() {
		return earthModel;
	}

	public void setEarthModel(int model) {
		earthModel = model;
	}

	public void setLineCodeSpecified(boolean value) {
		lineCodeSpecified = value;
	}

	public CMatrix getZInv() {
		return ZInv;
	}

	public void setZInv(CMatrix zinv) {
		ZInv = zinv;
	}

	public boolean isCapSpecified() {
		return capSpecified;
	}

	public void setCapSpecified(boolean value) {
		capSpecified = value;
	}

}

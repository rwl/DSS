/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.EarthModel;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.general.ConductorChoice;
import com.ncond.dss.general.ConductorData;
import com.ncond.dss.general.ConductorDataObj;
import com.ncond.dss.general.LineCode;
import com.ncond.dss.general.LineCodeObj;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.general.LineGeometryObj;
import com.ncond.dss.general.LineSpacingObj;
import com.ncond.dss.general.WireDataObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.LineUnits;
import com.ncond.dss.shared.MathUtil;

public class LineObj extends PDElement {

	/* keep track of last frequency computed for geometry */
	private double Zfrequency;
	private LineUnits lineCodeUnits;
	private double unitsConvert; // conversion factor
	private LineGeometryObj lineGeometryObj;
	private LineSpacingObj lineSpacingObj;
	private ConductorDataObj[] wireData;
	private ConductorChoice phaseChoice;
	private boolean rhoSpecified;
	private boolean lineCodeSpecified;
	private EarthModel earthModel;
	private boolean capSpecified;  // to make sure user specifies C in some form

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
	protected double len;
	protected LineUnits lengthUnits;
	protected double Rg, Xg, kXg, rho;
	protected double generalPlotQuantity;  // for general circuit plotting
	protected String condCode;
	protected String geometryCode;
	protected String spacingCode;
	protected boolean geometrySpecified;
	protected boolean spacingSpecified;
	protected boolean symComponentsChanged;
	protected boolean symComponentsModel;
	protected boolean isSwitch;

	public LineObj(DSSClass parClass, String lineName) {
		super(parClass);

		setName(lineName.toLowerCase());
		objType = parClass.getClassType();  // DSSObjType + LINESECTION; // in both PD element list and line section lists

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
		Z = null;
		Zinv = null;
		Yc = null;
		condCode = "";

		Rg = 0.01805;  // ohms per 1000 ft
		Xg = 0.155081;
		rho = 100.0;
		kXg = Xg / Math.log(658.5 * Math.sqrt(rho / baseFrequency));
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
		geometryCode = "";
		lengthUnits = LineUnits.NONE;  // assume everything matches
		unitsConvert = 1.0;
		lineCodeUnits = LineUnits.NONE;
		lineCodeSpecified = false;
		earthModel = DSS.defaultEarthModel;

		spacingSpecified = false;
		lineSpacingObj = null;
		wireData = null;
		phaseChoice = ConductorChoice.UNKNOWN;
		spacingCode = "";

		Zfrequency = -1.0;  // indicate Z not computed.
		lineGeometryObj = null;

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	public void fetchLineCode(String code) {

		if (Line.lineCodeClass == null)
			Line.lineCodeClass = (LineCode) DSS.DSSClassList.get(DSS.classNames.find("linecode"));

		if (Line.lineCodeClass.setActive(code)) {

			LineCodeObj elem = (LineCodeObj) Line.lineCodeClass.getActiveObj();

			condCode = code.toLowerCase();

			// frequency compensation takes place in calcYPrim
			baseFrequency = elem.getBaseFrequency();

			/* Copy impedances from line code, but do not recalc because symmetrical
			 * component z's may not match what's in matrix */
			if (elem.isSymComponentsModel()) {
				R1 = elem.getR1();
				X1 = elem.getX1();
				R0 = elem.getR0();
				X0 = elem.getX0();
				C1 = elem.getC1();
				C0 = elem.getC0();
				symComponentsModel = true;
			} else {
				symComponentsModel = false;
			}

			// earth return impedances used to compensate for frequency
			Rg = elem.getRg();
			Xg = elem.getXg();
			rho = elem.getRho();
			kXg = Xg / Math.log(658.5 * Math.sqrt(rho / baseFrequency));

			lineCodeUnits = elem.getUnits();
			lineCodeSpecified = true;

			unitsConvert = LineUnits.convertLineUnits(lineCodeUnits, lengthUnits);

			setNormAmps(elem.getNormAmps());
			setEmergAmps(elem.getEmergAmps());
			setFaultRate(elem.getFaultRate());
			setPctPerm(elem.getPctPerm());
			setHrsToRepair(elem.getHrsToRepair());
			updatePDProperties();

			if (getNumPhases() != elem.getNPhases()) {
				setNumPhases(elem.getNPhases());
				// for a line, nPhases = nCond, for now
				Z = new CMatrix(nPhases);
				Zinv = new CMatrix(nPhases);
				Yc = new CMatrix(nPhases);
			}

			if (!symComponentsModel) {
				// copy matrices
				Z.copyFrom(elem.getZ());
				/*Zinv.copyFrom(lc.getZinv());*/  // no need to copy Zinv
				Yc.copyFrom(elem.getYc());
			} else {
				recalcElementData();  // compute matrices
			}

			setNumConds(nPhases);  // force reallocation of terminal info
			YOrder = nConds * nTerms;
			//setYprimInvalid(true);  // set in edit; this is redundant
		} else {
			DSS.doSimpleMsg("Line code:" + code + " not found", 180);
		}
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm, Ys, Ym, Ztemp;
		double Yc1, Yc0, oneThird = 1.0 / 3.0;  // do this to get more precision in next few statements
		Complex gammaL, expP, expM, exp2P, exp2M, sinhGL, tanh2GL;

		Circuit ckt = DSS.activeCircuit;

		// for a line, nPhases = nCond, for now
		Z    = new CMatrix(getNumPhases());
		Zinv = new CMatrix(getNumPhases());
		Yc   = new CMatrix(getNumPhases());

		/* Only time this is called is if symmetrical components are specified */

		Ztemp = new Complex(R1, X1).multiply(2.0);

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
				expP = new Complex(
					Math.cos(gammaL.getImaginary()),
					Math.sin(gammaL.getImaginary())
				).multiply(Math.exp(gammaL.getReal()));
				exp2P = new Complex(
					Math.cos(0.5 * gammaL.getImaginary()),
					Math.sin(0.5 * gammaL.getImaginary())
				).multiply(Math.exp(0.5 * gammaL.getReal()));
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
		Zs = Ztemp.add(new Complex(R0, X0)).multiply(oneThird);
		Zm = new Complex(R0, X0).subtract(new Complex(R1, X1)).multiply(oneThird);

		Yc1 = DSS.TWO_PI * baseFrequency * C1;
		Yc0 = DSS.TWO_PI * baseFrequency * C0;

		Ys = new Complex(0.0, Yc1).multiply(2.0).add(new Complex(0.0, Yc0)).multiply(oneThird);
		Ym = new Complex(0.0, Yc0).subtract(new Complex(0.0, Yc1)).multiply(oneThird);

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
		Complex[] ZinvValues;
		Complex[] Zvalues;
		Complex[] Yvalues;

		Circuit ckt = DSS.activeCircuit;

		double XgMod;
		int k;
		int[] norder = new int[1];

		double freqMult = 1.0;
		double lengthMult = 1.0;

		if (symComponentsChanged) {
			/* Try to catch inadvertent user error when they forget to specify C1 and C0 */
			/* Check to see if user has spec'd C1 and C0. If not, adjust default values for new length units */
			if (!capSpecified) {
				C1 = C1 / LineUnits.convertLineUnits(LineUnits.KFT, lengthUnits);  // were defined in kft
				C0 = C0 / LineUnits.convertLineUnits(LineUnits.KFT, lengthUnits);
				capSpecified = true;   // so we don't do it again
			}
			recalcElementData();
		}

		clearYPrim();

		// build series YPrim

		/* Build Z matrix */
		if (geometrySpecified) {
			makeZFromGeometry(ckt.getSolution().getFrequency());  // includes length in proper units
			if (DSS.solutionAbort) return;

		} else if (spacingSpecified) {
			makeZFromSpacing(ckt.getSolution().getFrequency());
			if (DSS.solutionAbort) return;

		} else {  // Z is from line code or specified in line data
			lengthMult = len / unitsConvert;  // convert to per unit length
			YPrimFreq = ckt.getSolution().getFrequency();
			freqMult = YPrimFreq / baseFrequency;

			/* Put in series RL */
			Zvalues = Z.asArray(norder);
			ZinvValues = Zinv.asArray(norder);
			// Correct the impedances for length and frequency
			// Rg increases with frequency
			// Xg modified by ln of sqrt(1/f)
			XgMod = (Xg != 0.0) ? 0.5 * kXg * Math.log(freqMult) : 0.0;

			for (int i = 0; i < norder[0] * norder[0]; i++) {
				ZinvValues[i] = new Complex(
					(Zvalues[i].getReal() + Rg * (freqMult - 1.0) ) * lengthMult,
					(Zvalues[i].getImaginary() - XgMod) * lengthMult * freqMult
				);
			}

			Zinv.invert();  /* Invert in place */
			if (Zinv.getErrorCode() > 0) {
				/* If error, put in tiny series conductance */
				DSS.doErrorMsg("LineObj.calcYPrim", "Matrix inversion error for line \"" + getName() + "\"",
						"Invalid impedance specified. Replaced with tiny conductance.", 183);
				Zinv.clear();
				for (int i = 0; i < getNumPhases(); i++)
					Zinv.set(i, i, new Complex(DSS.EPSILON, 0.0));
			} else {
				/* Now, put in YPrimSeries matrix */
				for (int i = 0; i < getNumPhases(); i++) {
					for (int j = 0; j < getNumPhases(); j++) {
						value = Zinv.get(i, j);
						YPrimSeries.set(i, j, value);
						YPrimSeries.set(i + getNumPhases(), j + getNumPhases(), value);
						value = value.negate();
						YPrimSeries.setSym(i, j + getNumPhases(), value);
					}
				}
			}

			YPrim.copyFrom(YPrimSeries);  // initialize YPrim for series impedances

			// moved this to after the copy to Yprim so it doesn't affect normal line model capacitance
			// include both sides of line
			// increase diagonal elements of both sides of line so that we will avoid isolated bus problem
			// add equivalent of 10 kvar capacitive at 345 kV
			for (int i = 0; i < YOrder; i++)
				YPrimSeries.add(i, i, Line.CAP_EPSILON);
		}

		// now build the shunt admittances and add into YPrim

		/* Put half the shunt capacitive admittance at each end */
		Yvalues = Yc.asArray(norder);

		if (geometrySpecified || spacingSpecified) {
			/* Values are already compensated for length and frequency */
			k = 0;
			for (int j = 0; j < getNumPhases(); j++) {
				for (int i = 0; i < getNumPhases(); i++) {
					value = ComplexUtil.divide(Yvalues[k], 2.0);  // half at each end ...
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
					value = new Complex(0.0, Yvalues[k].getImaginary() * lengthMult * freqMult / 2.0);
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
		String val;

		if (index >= 11 && index <= 13) {
			val = "[";
		} else {
			val = "";
		}

		/* Report impedance values in ohms per unit length of present length units */
		switch (index) {
		case 0:
			val = getBus(0);
			break;
		case 1:
			val = getBus(1);
			break;
		case 3:
			val = String.format("%-.7g", len);
			break;
		case 4:
			val = String.format("%d", nPhases);
			break;
		case 5:
			if (symComponentsModel) {
				val = String.format("%-.7g", R1 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 6:
			if (symComponentsModel) {
				val = String.format("%-.7g", X1 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 7:
			if (symComponentsModel) {
				val = String.format("%-.7g", R0 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 8:
			if (symComponentsModel) {
				val = String.format("%-.7g", X0 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 9:
			if (symComponentsModel) {
				val = String.format("%-.7g", C1 * 1.0e9 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 10:
			if (symComponentsModel) {
				val = String.format("%-.7g", C0 * 1.0e9 / unitsConvert);
			} else {
				val = "----";
			}
			break;
		case 11:
			for (i = 0; i < getNumConds(); i++) {
				for (j = 0; j < i; j++) {
					// report in per unit length in length units
					if (geometrySpecified || spacingSpecified) {
						val = val + String.format("%-.7g", Z.get(i, j).getReal() / len) + " ";
					} else {
						val = val + String.format("%-.7g", Z.get(i, j).getReal() / unitsConvert) + " ";
					}
					if (i < nConds - 1)
						val = val + "|";
				}
			}
			break;
		case 12:
			for (i = 0; i < nConds; i++) {
				for (j = 0; j < i; j++) {
					// X matrix
					if (geometrySpecified || spacingSpecified) {
						val = val + String.format("%-.7g", Z.get(i, j).getImaginary() / len) + " ";
					} else {
						val = val + String.format("%-.7g", Z.get(i, j).getImaginary() / unitsConvert) + " ";
					}
				}
				if (i < nConds - 1)
					val = val + "|";
			}
			break;
		case 13:  // CMatrix nf
			factor = DSS.TWO_PI * baseFrequency * 1.0e-9;
			for (i = 0; i < nConds; i++) {
				for (j = 0; j < i; j++) {
					if (geometrySpecified || spacingSpecified) {
						val = val + String.format("%-.7g", Yc.get(i, j).getImaginary() / factor / len) + " ";
					} else {
						val = val + String.format("%-.7g", Yc.get(i, j).getImaginary() / factor / unitsConvert) + " ";
					}
				}
				if (i < nConds - 1)
					val = val + "|";
			}
			break;
		case 14:
			if (isSwitch) {
				val = "true";
			} else {
				val = "false";
			}
			break;
		case 15:
			val = String.format("%g", Rg);
			break;
		case 16:
			val = String.format("%g", Xg);
			break;
		case 17:
			val = String.format("%g", rho);
			break;
		case 22:
			val = Util.getEarthModel(earthModel);
			break;
		default:
			val = super.getPropertyValue(index);
			break;
		}

		if (index >= 11 && index <= 13)
			val = val + "[";

		return val;
	}

	/**
	 * Only consider 3-phase branches with pos seq >> neg seq
	 * Otherwise, we don't know whether it is a 3-phase line or just a line with 3 phases
	 */
	public void getSeqLosses(Complex posSeqLosses, Complex negSeqLosses, Complex zeroSeqLosses) {
		int k;

		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] I012 = new Complex[3];

		posSeqLosses = Complex.ZERO;
		negSeqLosses = Complex.ZERO;
		zeroSeqLosses = Complex.ZERO;

		/* Method: sum seq powers going into each terminal */

		if (nPhases == 3) {
			/* 3-phase lines only */
			computeITerminal();
			for (int i = 0; i < 2; i++) {
				k = i * nPhases + 1;
				for (int j = 0; j < 3; j++)
					Vph[j] = DSS.activeCircuit.getSolution().getNodeV(nodeRef[k + j]);
				MathUtil.phase2SymComp(Vph, V012);
				MathUtil.phase2SymComp(ITerminal[k], I012);
				posSeqLosses = posSeqLosses.add(V012[1].multiply(I012[1].conjugate()));
				negSeqLosses = negSeqLosses.add(V012[2].multiply(I012[2].conjugate()));  // accumulate both line modes
				zeroSeqLosses = zeroSeqLosses.add(V012[0].multiply(I012[0].conjugate()));
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
		setPropertyValue(3, "1.0");  // was "5.28"
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
		setPropertyValue(22, Util.getEarthModel(EarthModel.SIMPLECARSON));

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
			for (i = 5; i < 14; i++) prpSequence[i] = 0;

			if (isSwitch) {
				s = " R1=1 X1=1 C1=1.1 phases=1 len=0.001";
			} else {
				if (symComponentsModel) {  // keep the same Z1 and C1
					Z1 = new Complex(R1, X1);
					C1New = C1 * 1.0e9;  // convert to nF
				} else {  // matrix was input directly, or built from physical data
					// average the diagonal and off-dialgonal elements
					ZS = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						ZS = ZS.add(Z.get(i, i));
					ZS = ComplexUtil.divide(ZS, nPhases);
					Zm = Complex.ZERO;
					for (i = 0; i < nPhases - 1; i++)
						for (j = i + 1; j < nPhases; j++)
							Zm = Zm.add(Z.get(i, j));
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
				s = String.format(" R1=%-.5g  %-.5g  C1=%-.5g phases=1", Z1.getReal(), Z1.getImaginary(), C1New);
			}
			// conductor current ratings
			s = s + String.format(" normAmps=%-.5g  %-.5g", getNormAmps(), getEmergAmps());
			Parser.getInstance().setCmdBuffer(s);
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
		LineUnits lenUnitsSaved;
		Complex newZ;
		double lenSelf, lenOther;
		boolean success = false;

		if (otherLine != null) {
			if (nPhases != otherLine.getNumPhases())
				return success;  // can't merge

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
					testBusNum = DSS.activeCircuit.getMapNodeToBus(nodeRef[1 + i * nConds]).busRef;
					for (j = 0; j < 2; j++) {
						if (DSS.activeCircuit.getMapNodeToBus(otherLine.getNodeRef(1 + j * otherLine.getNumConds())).busRef == testBusNum) {
							common1 = i;
							common2 = j;
							break;
						}
					}
					i += 1;
				}

				if (common1 == -1) return success;  // there's been an error; didn't find anything in common

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

				Parser.getInstance().setCmdBuffer(s);
				edit();
			}

			/* Rename the line */
			if (series) {
				newName = Util.stripExtension(getBus(0)) + "~" + Util.stripExtension(getBus(1));
			} else {
				newName = Util.stripExtension(getBus(0)) + "||" + Util.stripExtension(getBus(1));
			}

			/* Update control element connections to this line */
			updateControlElements("line." + newName, "line." + getName());
			updateControlElements("line." + newName, "line." + otherLine.getName());
			setName(newName);

			if (series) isSwitch = false;  // not allowed on series merge.

			/* Now do the impedances */

			lenSelf = len / unitsConvert;  // in units of r x Data
			lenOther = otherLine.getLen() / otherLine.getUnitsConvert();

			if (symComponentsModel) {
				/*------------------ Sym Component Model ---------------------*/
				if (series) {
					// ohms per unit length of this line length units
					s = " R1=" + String.format("%g", (R1 * lenSelf + otherLine.getR1() * lenOther) / totalLen);
					s = s + String.format(" %g", (X1 * lenSelf + otherLine.getX1() * lenOther) / totalLen);
					s = s + String.format(" %g", (R0 * lenSelf + otherLine.getR0() * lenOther) / totalLen);
					s = s + String.format(" %g", (X0 * lenSelf + otherLine.getX0() * lenOther) / totalLen);
					s = s + String.format(" %g", (C1 * lenSelf + otherLine.getC1() * lenOther) / totalLen * 1.0e9);
					s = s + String.format(" %g", (C0 * lenSelf + otherLine.getC0() * lenOther) / totalLen * 1.0e9);
				} else {
					/* Parallel */
					if (isSwitch) {
						s = "";   /* Leave as is if switch; just dummy z anyway */
					} else if (otherLine.isSwitch()) {
						s = " switch=yes";  /* This will take care of setting Z's */
					} else {
						/* ********* Will This work with length multiplier? did it ever work? ************************* */
						newZ = MathUtil.parallelZ(
							new Complex(R1 * len, X1 * len),
							new Complex(otherLine.getR1() * otherLine.getLen(), otherLine.getX1() * otherLine.getLen()));
						s = " R1=" + String.format("%g %g ", newZ.getReal(), newZ.getImaginary());
						newZ = MathUtil.parallelZ(new Complex(R0 * len, X0 * len),
								new Complex(otherLine.getR0() * otherLine.getLen(), otherLine.getX0() * otherLine.getLen()));
						s = " R0=" + String.format("%g %g ", newZ.getReal(), newZ.getImaginary());
						s = s + String.format(" %g", (C1 * len + otherLine.getC1() * otherLine.getLen()) / totalLen * 1.0e9);
						s = s + String.format(" %g", (C0 * len + otherLine.getC0() * otherLine.getLen()) / totalLen * 1.0e9);
					}
				}
				Parser.getInstance().setCmdBuffer(s);   // this reset the length units
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
						return success;  // lines not same size for some reason

					// Z <= (Z1 + Z2) / TotalLen to get equiv ohms per unit length
					for (i = 0; i < order1[0] * order1[0]; i++)
						values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add(values2[i].multiply(lenOther)), totalLen);

					// merge Yc matrices
					values1 = Yc.asArray(order1);
					values2 = otherLine.getYc().asArray(order2);

					if (order1[0] != order2[0])
						return success;  // lines not same size for some reason

					for (i = 0; i < order1[0] * order1[0]; i++)
						values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add( values2[i].multiply(lenOther) ), totalLen);

					/* R matrix */
					s = "Rmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %g", Z.get(i, j).getReal());
						s = s + " | ";
					}
					s = s + "] Xmatrix=[";
					/* X matrix */
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %g", Z.get(i, j).getImaginary());
						s = s + " | ";
					}
					s = s + "]";
					Parser.getInstance().setCmdBuffer(s);
					edit();

					/* C matrix */
					wnano = DSS.TWO_PI * baseFrequency / 1.0e9;
					s = "Cmatrix=[";
					for (i = 0; i < 3; i++) {
						for (j = 0; j < i; j++)
							s = s + String.format(" %g", (Yc.get(i, j).getImaginary() / wnano));  // convert from mhos to nanofs
						s = s + " | ";
					}
					s = s + "] ";
					Parser.getInstance().setCmdBuffer(s);
					edit();
				}
			}  // matrix definition

			Parser.getInstance().setCmdBuffer(String.format(" length=%g units=%s", totalLen, LineUnits.lineUnitsStr(lenUnitsSaved)));
			edit();

			otherLine.setEnabled(false);  // disable the other line
			success = true;
		} else {
			DSS.doSimpleMsg("Error in line merge: Attempt to merge with invalid (null) line object found.", 184);
		}

		return success;
	}

	public void updateControlElements(String newName, String oldName) {
		Circuit ckt = DSS.activeCircuit;
		for (ControlElem elem : ckt.getControls()) {
			if (oldName.equalsIgnoreCase(elem.getElementName())) {
				Parser.getInstance().setCmdBuffer(" element=" + newName);  // change name of the property
				elem.edit();
			}
		}
	}

	public void fetchLineSpacing(String code) {
		if (DSS.lineSpacingClass.setActive(code)) {
			setLineSpacingObj((LineSpacingObj) DSS.lineSpacingClass.getActiveObj());
			lineCodeSpecified = false;
			killGeometrySpecified();
			spacingCode = code.toLowerCase();

			// need to establish Yorder before makeZFromSpacing
			setNumPhases(lineSpacingObj.getNPhases());
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

		DSS.auxParser.setCmdBuffer(code);
		for (i = istart; i < lineSpacingObj.getNWires(); i++) {
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.wireDataClass.setCode(DSS.auxParser.makeString());

			if (ConductorData.activeConductorDataObj != null) {
				wireData[i] = ConductorData.activeConductorDataObj;
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

		DSS.auxParser.setCmdBuffer(code);
		for (i = 0; i < lineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.CNDataClass.setCode(DSS.auxParser.makeString());

			if (ConductorData.activeConductorDataObj != null) {
				wireData[i] = ConductorData.activeConductorDataObj;
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
		DSS.auxParser.setCmdBuffer(code);
		for (i = 0; i < lineSpacingObj.getNPhases(); i++) {  // fill extra neutrals later
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			DSS.TSDataClass.setCode(DSS.auxParser.makeString());

			if (ConductorData.activeConductorDataObj != null) {
				wireData[i] = ConductorData.activeConductorDataObj;
			} else {
				DSS.doSimpleMsg("TS cable " + DSS.auxParser.makeString() + " was not defined first.", 181);
			}
		}
	}

	public void fetchGeometryCode(String code) {
		if (Line.lineGeometryClass == null)
			Line.lineGeometryClass = (LineGeometry) DSS.DSSClassList.get(DSS.classNames.find("LineGeometry"));

		if (Line.lineGeometryClass.setActive(code)) {
			lineCodeSpecified = false;  // cancel this flag
			spacingSpecified  = false;

			setLineGeometryObj((LineGeometryObj) Line.lineGeometryClass.getActiveObj());
			Zfrequency = -1.0;  // init to signify not computed

			geometryCode = code.toLowerCase();

			if (rhoSpecified)
				lineGeometryObj.setRhoEarth(rho);

			setNormAmps(lineGeometryObj.getNormAmps());
			setEmergAmps(lineGeometryObj.getEmergAmps());
			updatePDProperties();

			setNumPhases(lineGeometryObj.getNConds());
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
		if (f == Zfrequency)
			return;  // already done for this frequency, no need to do anything

		if (lineGeometryObj != null) {
			/* This will make a new Z; throw away present allocations */

			DSS.activeEarthModel = getEarthModel();

			Z  = getLineGeometryObj().getZMatrix(f, len, lengthUnits);
			Yc = getLineGeometryObj().getYcMatrix(f, len, lengthUnits);

			/* init Zinv */
			if (Z != null) {
				Zinv = new CMatrix(Z.order());  // either no. phases or no. conductors
				Zinv.copyFrom(Z);
			}

			// Z and Yc are actual total impedance for the line

			Zfrequency = f;
		}
	}

	/**
	 * Make new Z, Zinv, Yc, etc
	 */
	private void makeZFromSpacing(double f) {
		if (f == Zfrequency)
			return;  // already done for this frequency, no need to do anything

		// make a temporary LineGeometry to calculate line constants
		if (Line.lineGeometryClass == null)
			Line.lineGeometryClass = (LineGeometry) DSS.DSSClassList.get(DSS.classNames.find("LineGeometry"));

		LineGeometryObj elem = new LineGeometryObj(Line.lineGeometryClass, "==");
		elem.loadSpacingAndWires(getLineSpacingObj(), getWireData());  // this sets OH, CN, or TS

		if (rhoSpecified)
			elem.setRhoEarth(rho);
		setNormAmps(elem.getNormAmps());
		setEmergAmps(elem.getEmergAmps());
		updatePDProperties();

		DSS.activeEarthModel = earthModel;

		Z  = elem.getZMatrix(f, len, lengthUnits);
		Yc = elem.getYcMatrix(f, len, lengthUnits);
		if (Z != null) {
			Zinv = new CMatrix(Z.order());  // either no. phases or no. conductors
			Zinv.copyFrom(Z);
		}
		elem = null;

		Zfrequency = f;
	}

	/**
	 * Indicate no line geometry specification if this is called.
	 */
	protected void killGeometrySpecified() {
		if (geometrySpecified) {
			lineGeometryObj = null;
			Zfrequency = -1.0;
			geometrySpecified = false;
		}
	}

	protected void killSpacingSpecified() {
		if (spacingSpecified) {
			lineSpacingObj = null;
			wireData = new WireDataObj[0];
			phaseChoice = ConductorChoice.UNKNOWN;
			Zfrequency = -1.0;
			spacingSpecified = false;
		}
	}

	private void clearYPrim() {
		// line object needs both series and shunt YPrims built
		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			YPrimSeries = new CMatrix(YOrder);
			YPrimShunt = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimSeries.clear();   // zero out YPrim_Series
			YPrimShunt.clear();    // zero out YPrim_Shunt
			YPrim.clear();         // zero out YPrim
		}
	}

	/**
	 * If specify the impedances always assume the length units match.
	 */
	protected void resetLengthUnits() {
		unitsConvert = 1.0;
		lengthUnits = LineUnits.NONE;
	}

	private void updatePDProperties() {
		setPropertyValue(Line.NumPropsThisClass + 0, String.format("%g", getNormAmps()));
		setPropertyValue(Line.NumPropsThisClass + 1, String.format("%g", getEmergAmps()));
		setPropertyValue(Line.NumPropsThisClass + 2, String.format("%g", getFaultRate()));
		setPropertyValue(Line.NumPropsThisClass + 3, String.format("%g", getPctPerm()));
		setPropertyValue(Line.NumPropsThisClass + 4, String.format("%g", getHrsToRepair()));
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public LineUnits getLineCodeUnits() {
		return lineCodeUnits;
	}

	public LineGeometryObj getLineGeometryObj() {
		return lineGeometryObj;
	}

	public LineSpacingObj getLineSpacingObj() {
		return lineSpacingObj;
	}

	public EarthModel getEarthModel() {
		return earthModel;
	}

	public double getR1() {
		return R1;
	}

	public double getR0() {
		return R0;
	}

	public double getC1() {
		return C1;
	}

	public double getC0() {
		return C0;
	}

	public double getLen() {
		return len;
	}

	public LineUnits getLengthUnits() {
		return lengthUnits;
	}

	public String getCondCode() {
		return condCode;
	}

	public double getUnitsConvert() {
		return unitsConvert;
	}

	public ConductorDataObj[] getWireData() {
		return wireData;
	}

	public boolean isLineCodeSpecified() {
		return lineCodeSpecified;
	}

	public boolean isCapSpecified() {
		return capSpecified;
	}

	public CMatrix getZ() {
		return Z;
	}

	public CMatrix getYc() {
		return Yc;
	}

	public double getX1() {
		return X1;
	}

	public double getX0() {
		return X0;
	}

	public double getXg() {
		return Xg;
	}

	public double getRho() {
		return rho;
	}

	public boolean isGeometrySpecified() {
		return geometrySpecified;
	}

	public boolean isSymComponentsModel() {
		return symComponentsModel;
	}

	public boolean isSwitch() {
		return isSwitch;
	}

	public void setLineGeometryObj(LineGeometryObj lineGeometryObj) {
		this.lineGeometryObj = lineGeometryObj;
	}

	public void setLineSpacingObj(LineSpacingObj lineSpacingObj) {
		this.lineSpacingObj = lineSpacingObj;
	}

	public void setRhoSpecified(boolean rhoSpecified) {
		this.rhoSpecified = rhoSpecified;
	}

	public void setLineCodeSpecified(boolean lineCodeSpecified) {
		this.lineCodeSpecified = lineCodeSpecified;
	}

	public void setEarthModel(EarthModel earthModel) {
		this.earthModel = earthModel;
	}

	public void setCapSpecified(boolean capSpecified) {
		this.capSpecified = capSpecified;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public void setR0(double r0) {
		R0 = r0;
	}

	public void setC1(double c1) {
		C1 = c1;
	}

	public void setC0(double c0) {
		C0 = c0;
	}

	public void setLen(double len) {
		this.len = len;
	}

	public void setLengthUnits(LineUnits lengthUnits) {
		this.lengthUnits = lengthUnits;
	}

	public void setRg(double rg) {
		Rg = rg;
	}

	public void setKXg(double kXg) {
		this.kXg = kXg;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public void setGeometrySpecified(boolean geometrySpecified) {
		this.geometrySpecified = geometrySpecified;
	}

	public void setSpacingSpecified(boolean spacingSpecified) {
		this.spacingSpecified = spacingSpecified;
	}

	public void setSymComponentsChanged(boolean symComponentsChanged) {
		this.symComponentsChanged = symComponentsChanged;
	}

	public void setSymComponentsModel(boolean symComponentsModel) {
		this.symComponentsModel = symComponentsModel;
	}

	public void setSwitch(boolean isSwitch) {
		this.isSwitch = isSwitch;
	}

	public void setUnitsConvert(double unitsConvert) {
		this.unitsConvert = unitsConvert;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public void setYc(CMatrix yc) {
		Yc = yc;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public void setX0(double x0) {
		X0 = x0;
	}

	public void setXg(double xg) {
		Xg = xg;
	}

}

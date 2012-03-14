package com.ncond.dss.conversion;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;

/**
 * Ideal current source.
 *
 * ISource maintains a positive sequence for harmonic scans.  If you want zero
 * sequence, use three single-phase ISource.
 *
 */
@Getter @Setter
public class ISourceObj extends PCElement {

	private double amps;

	private double angle;

	private double phaseShift;

	private SequenceType scanType;

	private SequenceType sequenceType;

	protected double srcFrequency;

	public ISourceObj(DSSClass parClass, String sourceName) {
		super(parClass);

		setName(sourceName.toLowerCase());
		objType = parClass.getClassType(); // SOURCE + NON_PCPD_ELEM;  // don't want this in PC Element List

		setNumPhases(3);
		nConds = 3;
		setNumTerms(1);

		amps = 0.0;
		angle = 0.0;
		srcFrequency = baseFrequency;
		phaseShift = 120.0;
		scanType = SequenceType.POS;
		sequenceType = SequenceType.POS;

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		setSpectrumObj((SpectrumObj) DSS.spectrumClass.find(getSpectrum()));

		if (getSpectrumObj() == null) {
			DSS.doSimpleMsg("Spectrum object \"" + getSpectrum() +
				"\" for device ISource." + getName() + " not found.", 333);
		}

		setInjCurrent( Util.resizeArray(getInjCurrent(), YOrder) );
	}

	@Override
	public void calcYPrim() {
		// build only YPrim_Series
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}


		/* Yprim = 0 for ideal current source; just leave it zeroed */

		/* Now account for open conductors.
		 * For any conductor that is open, zero out row and column.
		 */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	private Complex getBaseCurr() {
		double srcHarmonic;
		Complex curr = null;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			/* Get first phase current */
			if (sol.isHarmonicModel()) {
				srcHarmonic = sol.getFrequency() / srcFrequency;
				curr = getSpectrumObj().getMult(srcHarmonic).multiply(amps);  // base current for this harmonic
				curr = Util.rotatePhasorDeg(curr, srcHarmonic, angle);
			} else {
				if (Math.abs(sol.getFrequency() - srcFrequency) < DSS.EPSILON2) {
					curr = ComplexUtil.polarDeg2Complex(amps, angle);
				} else {
					curr = Complex.ZERO;
				}
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error computing current for ISource." + getName() +
					". Check specification. Aborting.", 334);
			if (DSS.inRedirect) DSS.redirectAbort = true;
		}

		return curr;
	}

	/**
	 * Sum currents directly into solution array.
	 */
	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());
		return super.injCurrents();  // adds into system array
	}

	/**
	 * Total currents into a device.
	 */
	@Override
	public void getCurrents(Complex[] curr) {
		try {
			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with YPrim currents
			for (int i = 0; i < YOrder; i++)
				curr[i] = complexBuffer[i].negate();
		} catch (Exception e) {
			DSS.doErrorMsg(("GetCurrents for ISource element: " + getName() + "."),
					e.getMessage(),
					"Inadequate storage allotted for circuit element.", 335);
		}
	}

	/**
	 * Fill up an array of injection currents.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		Complex baseCurr = getBaseCurr();  // this func applies spectrum if needed

		for (int i = 0; i < getNumPhases(); i++) {
			curr[i] = baseCurr;
			if (i < getNumPhases()) {
				if (sol.isHarmonicModel()) {
					switch (scanType) {
					case POS:
						baseCurr = Util.rotatePhasorDeg(baseCurr, 1.0, -getPhaseShift());  // maintain positive sequence for ISource
						break;
					case ZERO:
						// do not rotate for zero sequence
						break;
					default:
						baseCurr = Util.rotatePhasorDeg(baseCurr, sol.getHarmonic(), -getPhaseShift());  // rotate by frequency
						/* Harmonic 1 will be pos; 2 is neg; 3 is zero, and so on. */
						break;
					}
				} else {
					switch (sequenceType) {
					case NONE:
						baseCurr = Util.rotatePhasorDeg(baseCurr, 1.0, phaseShift);  // neg seq
						break;
					case ZERO:
						// do not rotate for zero sequence
						break;
					default:
						baseCurr = Util.rotatePhasorDeg(baseCurr, 1.0, -phaseShift);  // maintain pos seq
						break;
					}
				}
			}
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println();
			pw.println();
		}

		pw.close();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, getBus(0));
		setPropertyValue(1, "0");
		setPropertyValue(2, "0");
		setPropertyValue(3, String.format("%-.6g", srcFrequency));
		setPropertyValue(4, "3");
		setPropertyValue(5, "pos");
		setPropertyValue(6, "pos");

		super.initPropertyValues(ISource.NumPropsThisClass - 1);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getNumPhases() > 1) {
			Parser.getInstance().setCmdString("phases=1");
			edit();
		}
		super.makePosSequence();
	}

}

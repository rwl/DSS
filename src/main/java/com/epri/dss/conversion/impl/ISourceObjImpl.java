package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;

import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.ISource;
import com.epri.dss.conversion.ISourceObj;

public class ISourceObjImpl extends PCElementImpl implements ISourceObj {

	private double Amps;

	private double Angle;

	private double PhaseShift;

	private int ScanType;

	private int SequenceType;

	protected double SrcFrequency;

	public ISourceObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);

		setName(SourceName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); // SOURCE + NON_PCPD_ELEM;  // don't want this in PC Element List

		setNPhases(3);
		this.nConds = 3;
		setNTerms(1);

		this.Amps     = 0.0;
		this.Angle    = 0.0;
		this.SrcFrequency = baseFrequency;
		this.PhaseShift = 120.0;
		this.ScanType = 1;  // pos sequence
		this.SequenceType = 1;

		initPropertyValues(0);

		this.YOrder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );

		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device ISource."+getName()+" not found.", 333);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );
	}

	@Override
	public void calcYPrim() {

		// build only YPrim_Series
		if (isYprimInvalid()) {
			if (YPrimSeries != null)
				YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null)
				YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
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
		double SrcHarmonic;
		Complex Result = null;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			/* Get first phase current */
			if (sol.isIsHarmonicModel()) {
				SrcHarmonic = sol.getFrequency() / SrcFrequency;
				Result = getSpectrumObj().getMult(SrcHarmonic).multiply(Amps);  // base current for this harmonic
				Result = Utilities.rotatePhasorDeg(Result, SrcHarmonic, Angle);
			} else {
				if (Math.abs(sol.getFrequency() - SrcFrequency) < DSSGlobals.EPSILON2) {
					Result = ComplexUtil.polarDeg2Complex(Amps, Angle);
				} else {
					Result = Complex.ZERO;
				}
			}
		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing current for ISource."+getName()+". Check specification. Aborting.", 334);
			if (Globals.isInRedirect())
				Globals.setRedirectAbort(true);
		}

		return Result;
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
	public void getCurrents(Complex[] Curr) {
		try {
			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with YPrim currents
			for (int i = 0; i < YOrder; i++)
				Curr[i] = complexBuffer[i].negate();
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg(("GetCurrents for ISource element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element.", 335);
		}
	}

	/**
	 * Fill up an array of injection currents.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		Complex BaseCurr = getBaseCurr();  // this func applies spectrum if needed

		for (int i = 0; i < getNPhases(); i++) {
			Curr[i] = BaseCurr;
			if (i < getNPhases())
				if (sol.isIsHarmonicModel()) {
					switch (ScanType) {
					case 1:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, -getPhaseShift());  // maintain positive sequence for ISource
						break;
					case 0:
						// do not rotate for zero sequence
						break;
					default:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, sol.getHarmonic(), -getPhaseShift());  // rotate by frequency
						/* Harmonic 1 will be pos; 2 is neg; 3 is zero, and so on. */
						break;
					}
				} else {
					switch (SequenceType) {
					case -1:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, PhaseShift);  // neg seq
						break;
					case 0:
						// do not rotate for zero sequence
						break;
					default:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, -PhaseShift);  // maintain pos seq
						break;
					}
				}
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete) {
			F.println();
			F.println();
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, getBus(1));
		setPropertyValue(1, "0");
		setPropertyValue(2, "0");
		setPropertyValue(3, String.format("%-.6g", SrcFrequency));
		setPropertyValue(4, "3");
		setPropertyValue(5, "pos");
		setPropertyValue(6, "pos");

		super.initPropertyValues(ISource.NumPropsThisClass);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getNPhases() > 1) {
			Parser.getInstance().setCmdString("phases=1");
			edit();
		}
		super.makePosSequence();
	}

	public double getSrcFrequency() {
		return SrcFrequency;
	}

	public void setSrcFrequency(double srcFrequency) {
		SrcFrequency = srcFrequency;
	}

	// FIXME Private members in OpenDSS

	public double getAmps() {
		return Amps;
	}

	public void setAmps(double amps) {
		Amps = amps;
	}

	public double getAngle() {
		return Angle;
	}

	public void setAngle(double angle) {
		Angle = angle;
	}

	public double getPhaseShift() {
		return PhaseShift;
	}

	public void setPhaseShift(double phaseShift) {
		PhaseShift = phaseShift;
	}

	public int getScanType() {
		return ScanType;
	}

	public void setScanType(int scanType) {
		ScanType = scanType;
	}

	public int getSequenceType() {
		return SequenceType;
	}

	public void setSequenceType(int sequenceType) {
		SequenceType = sequenceType;
	}

}

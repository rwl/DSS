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
		this.DSSObjType = ParClass.getDSSClassType(); // SOURCE + NON_PCPD_ELEM;  // Don't want this in PC Element List

		setNPhases(3);
		this.nConds = 3;
		setNTerms(1);

		this.Amps     = 0.0;
		this.Angle    = 0.0;
		this.SrcFrequency = BaseFrequency;
		this.PhaseShift = 120.0;
		this.ScanType = 1;  // pos sequence
		this.SequenceType = 1;

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );

		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("Spectrum Object \"" + getSpectrum() + "\" for Device Isource."+getName()+" Not Found.", 333);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );
	}

	@Override
	public void calcYPrim() {

		// Build only YPrim Series
		if (isYprimInvalid()) {
			if (YPrim_Series != null)
				YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
			if (YPrim != null)
				YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Series.clear();
			YPrim.clear();
		}


		/* Yprim = 0  for Ideal Current Source;  just leave it zeroed */

		/* Now account for open conductors.
		 * For any conductor that is open, zero out row and column.
		 */
		super.calcYPrim();

		setYprimInvalid(false);
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
				Result = getSpectrumObj().getMult(SrcHarmonic).multiply(Amps);  // Base current for this harmonic
				Result = Utilities.rotatePhasorDeg(Result, SrcHarmonic, Angle);
			} else {
				if (Math.abs(sol.getFrequency() - SrcFrequency) < DSSGlobals.EPSILON2) {
					Result = ComplexUtil.polarDeg2Complex(Amps, Angle);
				} else {
					Result = Complex.ZERO;
				}
			}
		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing current for Isource."+getName()+". Check specification. Aborting.", 334);
			if (Globals.isIn_Redirect())
				Globals.setRedirect_Abort(true);
		}

		return Result;
	}

	/**
	 * Sum currents directly into solution array.
	 */
	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());

		return super.injCurrents();  // Adds into system array
	}

	/**
	 * Total currents into a device.
	 */
	@Override
	public void getCurrents(Complex[] Curr) {
		try {
			getInjCurrents(ComplexBuffer);  // Get present value of inj currents
			// Add together  with YPrim currents
			for (int i = 0; i < Yorder; i++)
				Curr[i] = ComplexBuffer[i].negate();
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg(("GetCurrents for Isource Element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element?", 335);
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
			if (i < getNPhases()) {
				if (sol.isIsHarmonicModel()) {
					switch (ScanType) {
					case 1:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, -getPhaseShift()); // maintain positive sequence for isource
						break;
					case 0:
						// Do not rotate for zero sequence
						break;
					default:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, sol.getHarmonic(), -getPhaseShift());  // rotate by frequency
						/* Harmonic 1 will be pos; 2 is neg; 3 is zero, and so on. */
						break;
					}
				} else {
					switch (SequenceType) {
					case -1:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, PhaseShift);  // Neg seq
						break;
					case 0:
						// Do not rotate for zero sequence
						break;
					default:
						BaseCurr = Utilities.rotatePhasorDeg(BaseCurr, 1.0, -PhaseShift);  // Maintain pos seq
						break;
					}
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

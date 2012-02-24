package com.ncond.dss.delivery.impl;

import java.io.PrintStream;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.delivery.Capacitor;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.impl.CMatrixImpl;
import com.ncond.dss.shared.impl.ComplexUtil;

import org.apache.commons.math.complex.Complex;

public class CapacitorObjImpl extends PDElementImpl implements CapacitorObj {

	private double[] C,
		XL,
		kVArRating,
		R,
		harm;  // single C per phase (line rating) if Cmatrix not specified
	private int[] states;

	private double totalKVAr,
		kVRating;
	private int numSteps,
		lastStepInService;
	private double[] CMatrix;  // if not nil then overrides C

	private boolean doHarmonicRecalc;

	private int specType;

	/* 0 or 1 for wye (default) or delta, respectively */
	protected int connection;

	public CapacitorObjImpl(DSSClass parClass, String capacitorName) {
		super(parClass);

		setName(capacitorName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(2);  // force allocation of terminals and conductors

		setBus(1, (getBus(0) + ".0.0.0"));  // default to grounded wye

		isShunt = true;  // defaults to shunt capacitor

		CMatrix = null;

		/* Initialize these pointers to nil so reallocmem will work reliably. */
		C = null;
		XL = null;
		kVArRating = null;
		R = null;
		harm = null;
		states = null;

		setNumSteps(1);  // initial allocation for the arrays, too
		lastStepInService = numSteps - 1;

		Utilities.initDblArray(numSteps, R, 0.0);
		Utilities.initDblArray(numSteps, XL, 0.0);
		Utilities.initDblArray(numSteps, harm, 0.0);
		Utilities.initDblArray(numSteps, kVArRating, 1200.0);

		states[0] = 1;

		kVRating = 12.47;
		Utilities.initDblArray(numSteps, C,
				1.0 / (DSSGlobals.TWO_PI * baseFrequency * Math.pow(kVRating, 2) * 1000.0 / kVArRating[0]));

		connection = 0;  // 0 or 1 for wye (default) or delta, respectively
		specType = 1;  // 1=kvar, 2=Cuf, 3=Cmatrix

		normAmps = kVArRating[0] * DSSGlobals.SQRT3 / kVRating * 1.35;  // 135%
		emergAmps = getNormAmps() * 1.8 / 1.35;  // 180%
		faultRate = 0.0005;
		pctPerm = 100.0;
		hrsToRepair = 3.0;
		YOrder = nTerms * nConds;

		doHarmonicRecalc = false;

		recalcElementData();

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		double kVArPerPhase, phaseKV, w;
		int i;

		totalKVAr = 0.0;
		phaseKV = 1.0;
		w = DSSGlobals.TWO_PI * baseFrequency;
		switch (specType) {
		case 1:// kvar
			switch (connection) {
			case 1:  // line-to-line
				phaseKV = kVRating;
				break;
			default:  // line-to-neutral
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
			for (i = 0; i < numSteps; i++)
				C[i] = 1.0 / (w * Math.pow(phaseKV, 2) * 1000.0 / (kVArRating[0] / nPhases));
			for (i = 0; i < numSteps; i++)
				totalKVAr = totalKVAr + kVArRating[i];
			break;
		case 2:  // Cuf
			switch (connection) {
			case 1:  // line-to-line
				phaseKV = kVRating;
				break;
			default:  // line-to-neutral
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
			for (i = 0; i < numSteps; i++)
				totalKVAr = totalKVAr + w * C[i] * Math.pow(phaseKV, 2) / 1000.0;
			break;
		case 3:  // Cmatrix
			// nothing to do
			break;
		}

		if (doHarmonicRecalc)  // if harmonic specified, compute filter reactance
			for (i = 0; i < numSteps; i++) {
				if (harm[i] != 0.0) {
					XL[i] = (1.0 / (w * C[i])) / Math.pow(harm[i], 2);
				} else {
					XL[i] = 0.0;  // assume 0 harmonic means no filter
				}
				if (R[i] == 0.0)
					R[i] = XL[i] / 1000.0;
			}

		kVArPerPhase = totalKVAr / nPhases;
		setNormAmps(kVArPerPhase / phaseKV * 1.35);
		setEmergAmps(getNormAmps() * 1.8 / 1.35);
	}

	@Override
	public void calcYPrim() {
		int i;
		CMatrix YPrimTemp, YPrimWork;

		// normally build only Yprim_Shunt, but if there are 2 terminals and bus1 != bus2

		if (isYprimInvalid()) {
			// reallocate YPrim if something has invalidated old allocation
			YPrimShunt = new CMatrixImpl(YOrder);
			YPrimSeries = new CMatrixImpl(YOrder);
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

		YPrimWork = new CMatrixImpl(YOrder);

		for (i = 0; i < numSteps; i++)
			if (states[i] == 1) {
				makeYprimWork(YPrimWork, i);
				YPrimTemp.addFrom(YPrimWork);
			}

		YPrimWork = null;

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		if (isShunt())
			for (i = 0; i < YOrder; i++)
				YPrimSeries.set(i, i, YPrimShunt.get(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		f.println("~ " + parentClass.getPropertyName()[0] + "=" + getFirstBus());
		f.println("~ " + parentClass.getPropertyName()[1] + "=" + getNextBus());

		f.println("~ " + parentClass.getPropertyName()[2] + "=" + getNPhases());
		f.println("~ " + parentClass.getPropertyName()[3] + "=" + getPropertyValue(3));

		f.println("~ " + parentClass.getPropertyName()[4] + "=" + getKVRating());
		switch (getConnection()) {
		case 0:
			f.println("~ " + parentClass.getPropertyName()[5] + "=wye");
			break;
		case 1:
			f.println("~ " + parentClass.getPropertyName()[6] + "=delta");
			break;
		}
		if (getCMatrix() != null) {
			f.print(parentClass.getPropertyName()[6] + "= (");
			for (int i = 0; i < getNPhases(); i++) {
				for (int j = 0; j < i; j++)
					f.print((getCMatrix()[i * getNPhases() + j] * 1.0e6) + " ");
				if (i != getNPhases())
					f.print("|");
			}
			f.println(")");
		}

		f.println("~ " + parentClass.getPropertyName()[7] + "=" + getPropertyValue(7));
		f.println("~ " + parentClass.getPropertyName()[8] + "=" + getPropertyValue(8));
		f.println("~ " + parentClass.getPropertyName()[9] + "=" + getPropertyValue(9));
		f.println("~ " + parentClass.getPropertyName()[10] + "=" + getPropertyValue(10));
		f.println("~ " + parentClass.getPropertyName()[11] + "=" + getNumSteps());
		f.println("~ " + parentClass.getPropertyName()[12] + "=" + getPropertyValue(12));

		for (int i = Capacitor.NumPropsThisClass; i < parentClass.getNumProperties(); i++) {
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
		}

		if (complete) {
			f.println("SpecType=" + getSpecType());
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, getBus(0));
		setPropertyValue(1, getBus(1));
		setPropertyValue(2, "3");
		setPropertyValue(3, "1200");
		setPropertyValue(4, "12.47");
		setPropertyValue(5, "wye");
		setPropertyValue(6, "");
		setPropertyValue(7, "");
		setPropertyValue(8, "0");
		setPropertyValue(9, "0");
		setPropertyValue(10, "0");
		setPropertyValue(11, "1");
		setPropertyValue(12, "1");  // states

		super.initPropertyValues(Capacitor.NumPropsThisClass - 1);

		// override inherited properties
		setPropertyValue(Capacitor.NumPropsThisClass + 0, Utilities.strReal(getNormAmps(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 1, Utilities.strReal(getEmergAmps(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 2, Utilities.strReal(getFaultRate(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 3, Utilities.strReal(getPctPerm(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 4, Utilities.strReal(getHrsToRepair(), 0));
		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String s = null;
		double kVArPerPhase, phaseKV, Cs, Cm;
		int i, j;

		if (getNPhases() > 1) {
			switch (getSpecType()) {
			case 1:  // kvar

				if (getNPhases() > 1 || getConnection() != 0) {
					phaseKV = getKVRating() / DSSGlobals.SQRT3;
				} else {
					phaseKV = getKVRating();
				}

				s = "phases=1 " + String.format(" kV=%-.5g kvar=(", phaseKV);

				for (i = 0; i < getNumSteps(); i++) {
					kVArPerPhase = getKVArRating()[i] / getNPhases();
					s = s + String.format(" %-.5g", kVArPerPhase);
				}

				s = s + ")";

				break;
				/* Leave R as specified */
			case 2:
				s = "phases=1 ";
				break;
			case 3:  // C matrix
				s = "phases=1 ";
				// r1
				Cs = 0.0;  // avg self
				for (i = 0; i < getNPhases(); i++)
					Cs = Cs + getCMatrix()[i * getNPhases() + i];
				Cs = Cs / getNPhases();

				Cm = 0.0;  // avg mutual
				for (i = 1; i < getNPhases(); i++)
					for (j = i; j < getNPhases(); j++)
						Cm = Cm + getCMatrix()[i * getNPhases() + j];
				Cm = Cm / (getNPhases() * (getNPhases() - 1.0) / 2.0);

				s = s + String.format(" Cuf=%-.5g", (Cs - Cm));
				break;
			}

			Parser.getInstance().setCmdString(s);
			edit();
		}

		super.makePosSequence();
	}

	public int getStates(int idx) {
		return getStates()[idx];
	}

	public void setStates(int idx, int value) {
		if (getStates()[idx] != value) {
			getStates()[idx] = value;
			setYPrimInvalid(true);
		}
	}

	/**
	 * Special case for changing from 1 to more. Automatically make a new bank.
	 *
	 * 1=kvar, 2=Cuf, 3=Cmatrix
	 */
	public void setNumSteps(int value) {
		double stepSize, RStep, XLStep;
		int i;

		/* Reallocate all arrays associated with steps */

		if (getNumSteps() != value && value > 0) {
			RStep = 0.0;
			XLStep = 0.0;
			if (getNumSteps() == 1) {
				/* Save total values to be divided up */
				setTotalKVAr( getKVArRating()[0] );
				RStep = getR()[0] * value;
				XLStep = getXL()[0] * value;
			}

			// reallocate arrays (must be initialized to nil for first call)
			setC( Utilities.resizeArray(getC(), value) );
			setXL( Utilities.resizeArray(getXL(), value) );
			setKVArRating( Utilities.resizeArray(getKVArRating(), value) );
			setR( Utilities.resizeArray(getR(), value) );
			setHarm( Utilities.resizeArray(getHarm(), value) );
			setStates( Utilities.resizeArray(getStates(), value) );

			// special case for numSteps=1

			if (getNumSteps() == 1) {
				switch (getSpecType()) {
				case 1:  // kvar   /* We'll make a multi-step bank of same net size as at present */
					stepSize = getTotalKVAr() / value;
					for (i = 0; i < value; i++)
						getKVArRating()[i] = stepSize;
					break;

				case 2:  // Cuf   /* We'll make a multi-step bank with all the same as first */
					for (i = 1; i < value; i++)
						getC()[i] = getC()[0];  // make same as first step
					break;

				case 3:  // Cmatrix   /* We'll make a multi-step bank with all the same as first */
					// nothing to do since all will be the same
					break;
				}

				switch (getSpecType()) {
				case 1:
					for (i = 0; i < value; i++)
						getR()[i] = RStep;
					for (i = 0; i < value; i++)
						getXL()[i] = XLStep;
					break;

				case 2:  // make R and XL same as first step
					for (i = 1; i < value; i++)
						getR()[i] = getR()[0];
					for (i = 1; i < value; i++)
						getXL()[i] = getXL()[0];
					break;
				case 3:  // make R and XL same as first step
					for (i = 1; i < value; i++)
						getR()[i] = getR()[0];
					for (i = 1; i < value; i++)
						getXL()[i] = getXL()[0];
					break;
				}

				for (i = 0; i < value; i++)
					getStates()[i] = 1;  // turn them all on
				setLastStepInService(value);
				for (i = 1; i < value; i++)
					getHarm()[i] = getHarm()[0];  // tune them all the same as first
			}
		}

		setNumSteps(value);
	}

	// FIXME Private member in OpenDSS
	public void processHarmonicSpec(String param) {
		Utilities.interpretDblArray(param, getNumSteps(), getHarm());
		setDoHarmonicRecalc(true);
	}

	// FIXME Private member in OpenDSS
	public void processStatesSpec(String param) {
		Utilities.interpretIntArray(param, getNumSteps(), getStates());

		lastStepInService = -1;

		for (int i = getNumSteps() - 1; i < 0; i--)
			if (getStates()[i] == 1) {
				lastStepInService = i;
				break;
			}
	}

	/**
	 * Call this routine only if step is energized.
	 */
	private void makeYprimWork(CMatrix YPrimWork, int iStep) {
		Complex value, value2, Zl = null;
		int i, j, ioffset;
		double w, freqMultiple;
		boolean hasZl;

		setYPrimFreq( DSSGlobals.activeCircuit.getSolution().getFrequency() );
		freqMultiple = getYPrimFreq() / getBaseFrequency();
		w = DSSGlobals.TWO_PI * getYPrimFreq();

		if ((getR()[iStep] + Math.abs(getXL()[iStep])) > 0.0) {
			hasZl = true;
		} else {
			hasZl = false;
		}

		if (hasZl)
			Zl = new Complex(getR()[iStep], getXL()[iStep] * freqMultiple);

		/* Now, put C into in Yprim matrix */

		switch (getSpecType()) {
		case 1:
			value = new Complex(0.0, getC()[iStep] * w);
			switch (getConnection()) {
			case 1:  // line-line
				value2 = value.multiply(2.0);
				value = value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YPrimWork.set(i, i, value2);
					for (j = 0; j < i; j++)
						YPrimWork.setSym(i, j, value);
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (hasZl)
					value = ComplexUtil.invert( Zl.add(ComplexUtil.invert( value )) ); // add in ZL
				value2 = value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YPrimWork.set(i, i, value);  // elements are only on the diagonals
					YPrimWork.set(i + getNPhases(), i + getNPhases(), value);
					YPrimWork.setSym(i, i + getNPhases(), value2);
				}
				break;
			}
			break;
		case 2:  // identical to case 1

			value = new Complex(0.0, getC()[iStep] * w);
			switch (getConnection()) {
			case 1:  // line-line
				value2 = value.multiply(2.0);
				value = value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YPrimWork.set(i, i, value2);
					for (j = 0; j < i; j++)
						YPrimWork.setSym(i, j, value);
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (hasZl)
					value = ComplexUtil.invert( Zl.add(ComplexUtil.invert( value )) );  // add in ZL
				value2 = value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YPrimWork.set(i, i, value);  // elements are only on the diagonals
					YPrimWork.set(i + getNPhases(), i + getNPhases(), value);
					YPrimWork.setSym(i, i + getNPhases(), value2);
				}
				break;
			}
			break;
		case 3:  // C matrix specified
			for (i = 0; i < getNPhases(); i++) {
				ioffset = i * getNPhases();
				for (j = 0; j < getNPhases(); j++) {
					value = new Complex(0.0, getCMatrix()[ioffset + j] * w);
					YPrimWork.set(i, j, value);
					YPrimWork.set(i + getNPhases(), j + getNPhases(), value);
					value = value.negate();
					YPrimWork.setSym(i, j + getNPhases(), value);
				}
			}
			break;
		}

		/* Add line reactance for filter reactor, if any */
		if (hasZl) {
			switch (getSpecType()) {
			case 1:

				switch (getConnection()) {
				case 1:  // line-line
					/* Add a little bit to each phase so it will invert */
					for (i = 0; i < getNPhases(); i++)
						YPrimWork.set(i, i, YPrimWork.get(i, i).multiply(1.000001));
					YPrimWork.invert();
					for (i = 0; i < getNPhases(); i++) {
						value = Zl.add(YPrimWork.get(i, i));
						YPrimWork.set(i, i, value);
					}
					YPrimWork.invert();
					break;
				default:  /* Wye - just put ZL in series */
					/* Do nothing; already in - see above */
					break;
				}
				break;
			case 2:  // identical to case 1

				switch (getConnection()) {
				case 1:  // line-line
					/* Add a little bit to each phase so it will invert */
					for (i = 0; i < getNPhases(); i++)
						YPrimWork.set(i, i, YPrimWork.get(i, i).multiply(1.000001));
					YPrimWork.invert();
					for (i = 0; i < getNPhases(); i++) {
						value = Zl.add(YPrimWork.get(i, i));
						YPrimWork.set(i, i, value);
					}
					YPrimWork.invert();
					break;
				default:  /* Wye - just put ZL in series */
					/* Do nothing; already in - see above */
					break;
				}
				break;
			case 3:
				YPrimWork.invert();
				for (i = 0; i < getNPhases(); i++) {
					value = Zl.add(YPrimWork.get(i, i));
					YPrimWork.set(i, i, value);
				}
				YPrimWork.invert();
				break;
			}
		}
	}

	@Override
	public String getPropertyValue(int index) {
		double[] temp;

		String result = "";
		switch (index) {  // special cases
		case 0:
			result = getBus(0);
			break;
		case 1:
			result = getBus(1);
			break;
		case 3:
			result = Utilities.getDSSArray(getNumSteps(), getKVArRating());
			break;
		case 7:
			temp = new double[getNumSteps()];
			for (int i = 0; i < getNumSteps(); i++)
				temp[i] = getC()[i] * 1.0e6;  // to microfarads
			result = Utilities.getDSSArray(getNumSteps(), temp);
			temp = null;  // throw away temp storage
			break;
		case 8:
			result = Utilities.getDSSArray(getNumSteps(), getR());
			break;
		case 10:
			result = Utilities.getDSSArray(getNumSteps(), getXL());
			break;
		case 11:
			result = Utilities.getDSSArray(getNumSteps(), getHarm());
			break;
		case 12:
			result = Utilities.getDSSArray(getNumSteps(), getStates());
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}
		return result;
	}

	public boolean addStep() {
		// start with last step in service and see if we can add more; if not return false.

		if (lastStepInService == getNumCustomers() - 1) {
			return false;
		} else {
			lastStepInService += 1;
			getStates()[lastStepInService] = 1;
			return true;
		}
	}

	public boolean subtractStep() {
		if (lastStepInService == -1) {
			return false;
		} else {
			getStates()[lastStepInService] = 0;
			lastStepInService -= 1;
			if (lastStepInService == -1) {
				return false;
			} else {
				return true;  // signify bank open
			}
		}
	}

	public int availableSteps() {
		return getNumSteps() - lastStepInService;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection(int conn) {
		connection = conn;
	}

	public double getTotalKVAr() {
		return totalKVAr;
	}

	public double getKVRating() {
		return kVRating;
	}

	// FIXME Private members in OpenDSS

	public double[] getC() {
		return C;
	}

	public void setC(double[] value) {
		C = value;
	}

	public double[] getXL() {
		return XL;
	}

	public void setXL(double[] xl) {
		XL = xl;
	}

	public double[] getKVArRating() {
		return kVArRating;
	}

	public void setKVArRating(double[] rating) {
		this.kVArRating = rating;
	}

	public double[] getR() {
		return R;
	}

	public void setR(double[] r) {
		R = r;
	}

	public double[] getHarm() {
		return harm;
	}

	public void setHarm(double[] values) {
		harm = values;
	}

	public int[] getStates() {
		return states;
	}

	public void setStates(int[] values) {
		states = values;
	}

	public int getLastStepInService() {
		return lastStepInService;
	}

	public void setLastStepInService(int lastStep) {
		lastStepInService = lastStep;
	}

	public double[] getCMatrix() {
		return CMatrix;
	}

	public void setCMatrix(double[] cmatrix) {
		CMatrix = cmatrix;
	}

	public boolean isDoHarmonicRecalc() {
		return doHarmonicRecalc;
	}

	public void setDoHarmonicRecalc(boolean value) {
		this.doHarmonicRecalc = value;
	}

	public int getSpecType() {
		return specType;
	}

	public void setSpecType(int type) {
		specType = type;
	}

	public void setTotalKVAr(double total) {
		this.totalKVAr = total;
	}

	public void setKVARating(double rating) {
		this.kVRating = rating;
	}

}

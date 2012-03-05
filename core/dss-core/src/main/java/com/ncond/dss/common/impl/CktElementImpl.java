package com.ncond.dss.common.impl;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Terminal;
import com.ncond.dss.general.impl.DSSObjectImpl;
import com.ncond.dss.shared.CMatrix;

abstract public class CktElementImpl extends DSSObjectImpl implements CktElement {

	private String[] busNames;
	private boolean enabled;
	private int enabledProperty;
	private int activeTerminalIdx;
	private boolean YPrimInvalid;
	private int handle;

	protected int nterm;
	/* No. conductors per terminal */
	protected int ncond;
	protected int nphase;

	protected Complex[] complexBuffer;

	protected int ITerminalSolutionCount;

	protected int busIdx;

	protected CMatrix YPrimSeries;
	protected CMatrix YPrimShunt;
	protected CMatrix YPrim;  // order will be nTerms * nCond

	/* Frequency at which YPrim has been computed */
	protected double YPrimFreq;

	/* Total nodeRef array for element */
	protected int[] nodeRef;
	protected int YOrder;
	/* Flag used in tree searches */
	protected int lastTerminalChecked;

	protected boolean checked, hasEnergyMeter, hasSensorObj, isIsolated,
		hasControl, isPartOfFeeder;

	protected CktElement controlElement;

	protected Complex[] ITerminal;
	protected Complex[] VTerminal;

	protected double baseFrequency;

	protected Terminal[] terminals;

	protected Terminal activeTerminal;

	public CktElementImpl(DSSClass parClass) {
		super(parClass);

		nodeRef     = null;
		YPrimSeries = null;
		YPrimShunt  = null;
		YPrim       = null;
		terminals   = null;
		busNames    = null;
		VTerminal   = null;
		ITerminal   = null;  // present value of terminal current
		complexBuffer = null;

		handle      = -1;
		busIdx    = -1;
		nterm      = 0;
		ncond      = 0;
		nphase     = 0;
		objType     = 0;
		YOrder      = 0;

		YPrimInvalid   = true;
		enabled        = true;
		hasEnergyMeter = false;
		hasSensorObj   = false;
		isPartOfFeeder = false;
		isIsolated     = false;

		controlElement = null;  // init to no control on this element
		hasControl     = false;

		activeTerminalIdx   = 0;
		lastTerminalChecked = -1;

		/* Indicates which solution ITemp is computed for */
		ITerminalSolutionCount = -1;

		baseFrequency = DSS.activeCircuit.getFundamental();
	}

	public void setYPrimInvalid(boolean value) {
		YPrimInvalid = value;
		if (value) {
			// if this device is in the circuit, then we have to rebuild Y on a change in Yprim
			if (enabled) DSS.activeCircuit.getSolution().setSystemYChanged(true);
		}
	}

	public boolean isYprimInvalid() {
		return YPrimInvalid;
	}

	public void setActiveTerminalIdx(int value) {
		if (value >= 0 && value < nterm) {
			activeTerminalIdx = value;
			activeTerminal = terminals[value] ;
		}
	}

	public int getActiveTerminalIdx() {
		return activeTerminalIdx;
	}

	public void setHandle(int value) {
		handle = value;
	}

	public int getHandle() {
		return handle;
	}

	public boolean isConductorClosed(int index) {
		boolean isClosed;
		if (index == -1) {  // all phases
			isClosed = true;
			for (int i = 0; i < nphase; i++) {
				if (!terminals[activeTerminalIdx].getConductor(i).isClosed()) {
					isClosed = false;
					break;
				}
			}
		} else {
			if (index >= 0 && index < ncond) {
				isClosed = terminals[activeTerminalIdx].getConductor(index).isClosed();
			} else {
				isClosed = false;
			}
		}
		return isClosed;
	}

	public void setConductorClosed(int index, boolean value) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (index == -1) {  // do all conductors
			for (int i = 0; i < nphase; i++)
				terminals[activeTerminalIdx].getConductor(i).setClosed(value);
			sol.setSystemYChanged(true);  // force Y matrix rebuild
			setYPrimInvalid(true);
		} else {
			if (index >= 0 && index < ncond) {
				terminals[activeTerminalIdx].getConductor(index).setClosed(value);
				sol.setSystemYChanged(true);
				setYPrimInvalid(true);
			}
		}
	}

	public void setNumConds(int value) {
		// check for an almost certain programming error
		if (value <= 0) {
			DSS.doSimpleMsg(String.format("Invalid number of terminals (%d) for \"%s.%s\"",
					value, parentClass.getName(), getName()), 749);
			return;
		}

		if (value != ncond)
			DSS.activeCircuit.setBusNameRedefined(true);
		ncond = value;
		setNumTerms(nterm);  // realloc terminals; need more efficient way to do this
	}

	public int getNumConds() {
		return ncond;
	}

	public void setNumPhases(int value) {
		if (value > 0)
			nphase = value;
	}

	public int getNumPhases() {
		return nphase;
	}

	public void setNumTerms(int value) {
		int i;
		String[] newBusNames;

		// check for an almost certain programming error
		if (value <= 0) {
			DSS.doSimpleMsg(String.format("Invalid number of terminals (%d) for \"%s.%s\"",
					value, parentClass.getName(), getName()), 749);
			return;
		}

		// if value is same as present value, no reallocation necessary;
		// if either nTerms or nConds has changed then reallocate
		if ((value != nterm) || (value * ncond != YOrder)) {

			/* Sanity check */
			if (ncond > 101) {
				DSS.doSimpleMsg(String.format("Warning: Number of conductors is very large (%d) for circuit element: \"%s.%s." +
						"Possible error in specifying the number of phases for element.",
						ncond, parentClass.getName(), getName()), 750);
			}

			/* Reallocate bus names */

			if (value < nterm) {
				busNames = Util.resizeArray(busNames, value);  // keeps old values; truncates storage
			} else {
				if (busNames == null) {
					// first allocation
					busNames = new String[value];
					for (i = 0; i < value; i++)
						busNames[i] = getName() + "_" + i;  // make up a bus name to stick in
					// this is so devices like transformers which may be defined on multiple commands
					// will have something in the busNames array
				} else {
					newBusNames = new String[value];  // make some new space
					for (i = 0; i < nterm; i++)
						newBusNames[i] = busNames[i];  // copy old into new
					for (i = 0; i < nterm; i++)
						busNames[i] = "";  // decrement usage counts by setting to empty string
					for (i = 0; i < nterm + 1; i++)
						newBusNames[i] = getName() + "_" + i;  // make up a bus name to stick in
					busNames = newBusNames;
				}
			}

			/* Reallocate terminals if ncond or nterm changed */
			if (terminals != null)
				for (i = 0; i < nterm; i++)
					terminals[i] = null;  // clean up old storage

			terminals = Util.resizeArray(terminals, value);

			nterm = value;  // set new number of terminals
			YOrder = nterm * ncond;
			VTerminal = Util.resizeArray(VTerminal, YOrder);
			ITerminal = Util.resizeArray(ITerminal, YOrder);
			complexBuffer = Util.resizeArray(complexBuffer, YOrder);  // used by both PD and PC elements

			for (i = 0; i < value; i++)
				terminals[i] = new PowerTerminal(ncond);
		}
	}

	public int getNumTerms() {
		return nterm;
	}

	public void setEnabled(boolean value) {
		if (value != enabled) {  // don't change unless this represents a change
			enabled = value;
			// force rebuilding of Y matrix and bus lists
			DSS.activeCircuit.setBusNameRedefined(true);
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public CMatrix getYPrim(int opt) {
		CMatrix Y = null;

		switch (opt) {
		case DSS.ALL_YPRIM:
			Y = YPrim;
			break;
		case DSS.SERIES:
			Y = YPrimSeries;
			break;
		case DSS.SHUNT:
			Y = YPrimShunt;
			break;
		}

		return Y;
	}

	public Complex[] getYPrimValues(int opt) {
		Complex[] result = null;

		switch (opt) {
		case DSS.ALL_YPRIM:
			if (YPrim != null)
				result = YPrim.asArray();
			break;
		case DSS.SERIES:
			if (YPrimSeries != null)
				result = YPrimSeries.asArray();
			break;
		case DSS.SHUNT:
			if (YPrimShunt != null)
				result = YPrimShunt.asArray();
			break;
		}

		return result;
	}

	abstract public void getCurrents(Complex[] curr);

	abstract public void getInjCurrents(Complex[] curr);

	public void getLosses(Complex[] totalLosses, Complex[] loadLosses, Complex[] noLoadLosses) {
		Complex totLosses = getLosses();  // watts, vars

		totalLosses[0] = totLosses;
		loadLosses[0] = totLosses;
		noLoadLosses[0] = Complex.ZERO;
	}

	abstract public int injCurrents();

	public void setNodeRef(int iTerm, int[] nodeRefArray) {
		int size, size2;

		// allocate nodeRef and move new values into it
		size = YOrder;
		size2 = ncond;  // size for one terminal
		nodeRef = Util.resizeArray(nodeRef, size);  // doesn't do anything if already properly allocated
		System.arraycopy(nodeRefArray[0], 0, nodeRef[iTerm * ncond], 0, size2);
		System.arraycopy(nodeRefArray[0], 0, terminals[iTerm].getTermNodeRef(0), 0, size2);  // copy in terminals as well

		// allocate temp array used to hold voltages and currents for calcs
		VTerminal = Util.resizeArray(VTerminal, YOrder);
		ITerminal = Util.resizeArray(ITerminal, YOrder);
		complexBuffer = Util.resizeArray(complexBuffer, YOrder);
	}

	public void setNodeRef(int iTerm, int nodeRefArray) {
		setNodeRef(iTerm, new int[] {nodeRefArray});
	}

	public String getFirstBus() {
		if (nterm > 0) {
			busIdx = 0;
			return busNames[busIdx];
		} else {
			return "";
		}
	}

	public String getNextBus() {
		String name = "";
		if (nterm > 0) {
			busIdx += 1;
			if (busIdx < nterm) {
				name = busNames[busIdx];
			} else {
				busIdx = nterm;
			}
		}
		return name;
	}

	public String getBus(int i) {
		if (i < nterm) {
			return busNames[i];
		} else {
			return "";
		}
	}

	public void setBus(int i, String name) {
		if (i < nterm) {
			busNames[i] = name.toLowerCase();
			// set global flag to signal circuit to rebuild bus defs
			DSS.activeCircuit.setBusNameRedefined(true);
		} else {
			DSS.doSimpleMsg(String.format("Attempt to set bus name for non-existent circuit element terminal(%d): \"%s\"", i, name), 7541);
		}
	}

	public void setFreq(double value) {
		if (value > 0.0) YPrimFreq = value;
	}

	public void setYPrimFreq(double value) {
		setFreq(value);
	}

	public double getYPrimFreq() {
		return this.YPrimFreq;
	}

	public void recalcElementData() {
		DSS.doSimpleMsg("recalcElementData in base CktElement class called for device = \"" + getName() + "\"", 754);
	}

	public void calcYPrim() {
		if (YPrimSeries != null)
			doYPrimCalcs(YPrimSeries);
		if (YPrimShunt != null)
			doYPrimCalcs(YPrimShunt);
		if (YPrim != null)
			doYPrimCalcs(YPrim);
	}

	public void computeITerminal() {
		// to save time, only recompute if a different solution than last time it was computed
		if (ITerminalSolutionCount != DSS.activeCircuit.getSolution().getSolutionCount()) {
			getCurrents(ITerminal);
			ITerminalSolutionCount = DSS.activeCircuit.getSolution().getSolutionCount();
		}
	}

	public double maxTerminalOneIMag() {
		double max = 0.0;
		if (enabled) {
			for (int i = 0; i < nphase; i++)
				max = Math.max(max, Math.pow(ITerminal[i].getReal(), 2) + Math.pow(ITerminal[i].getImaginary(), 2));
		}
		return Math.sqrt(max);  // just do the sqrt once and save a little time
	}

	public Complex getPower(int idxTerm) {
		Complex cPower = Complex.ZERO;
		int i, k, n;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		activeTerminalIdx = idxTerm;

		if (enabled) computeITerminal();

		// sum complex power going into phase conductors of active terminal
		k = idxTerm * ncond;
		for (i = 0; i < ncond; i++) {  // 11-7-08 changed from nPhases - was not accounting for all conductors
			n = activeTerminal.getTermNodeRef(i);  // don't bother for grounded node
			if (n >= 0) {
				cPower = cPower.add( sol.getNodeV(n).multiply( ITerminal[k + i].conjugate() ) );
			}
		}

		/* If this is a positive sequence circuit, then we need to multiply by 3 to get the 3-phase power */
		if (DSS.activeCircuit.isPositiveSequence()) {
			cPower = cPower.multiply(3.0);
		}

		return cPower;
	}

	public Complex getLosses() {
		Complex loss, totalLoss = Complex.ZERO;
		int k, n;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (enabled) {
			computeITerminal();

			// sum complex power going into all conductors of all terminals
			for (k = 0; k < YOrder; k++) {
				n = nodeRef[k];
				if (n >= 0) {
					if (DSS.activeCircuit.isPositiveSequence()) {
						loss = sol.getNodeV(n).multiply( ITerminal[k].conjugate() ).multiply(3.0);
					} else {
						loss = sol.getNodeV(n).multiply( ITerminal[k].conjugate() );
					}
					totalLoss = totalLoss.add(loss);
				}
			}
		}

		return totalLoss;
	}

	public void getPhasePower(Complex[] powerBuffer) {
		int i, n;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (enabled) {
			computeITerminal();

			for (i = 0; i < YOrder; i++) {
				n = nodeRef[i];  // increment through terminals
				if (n >= 0) {
					if (DSS.activeCircuit.isPositiveSequence()) {
						powerBuffer[i] = sol.getNodeV(n).multiply( ITerminal[i].conjugate() ).multiply(3.0);
					} else {
						powerBuffer[i] = sol.getNodeV(n).multiply( ITerminal[i].conjugate() );
					}
				}
			}
		} else {
			for (i = 0; i < YOrder; i++) powerBuffer[i] = Complex.ZERO;
		}
	}

	public void getPhaseLosses(int[] numPhases, Complex[] lossBuffer) {
		int i, j, k, n;
		Complex cLoss;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		numPhases[0] = nphase;
		if (enabled) {
			computeITerminal();

			for (i = 0; i < numPhases[0]; i++) {
				cLoss = Complex.ZERO;
				for (j = 0; j < nterm; j++) {
					k = j * ncond + i;
					n = nodeRef[k];  // increment through terminals
					if (n >= 0) {
						if (DSS.activeCircuit.isPositiveSequence()) {
							cLoss = cLoss.add( sol.getNodeV(n).multiply( ITerminal[k].conjugate() ).multiply(3.0) );
						} else {
							cLoss = cLoss.add( sol.getNodeV(n).multiply( ITerminal[k].conjugate() ) );
						}
					}
				}
				lossBuffer[i] = cLoss;
			}
		} else {
			for (i = 0; i < numPhases[0]; i++)
				lossBuffer[i] = Complex.ZERO;
		}
	}

	public void dumpProperties(OutputStream out, boolean complete) {
		PrintWriter pw = new PrintWriter(out);
	}

	private void doYPrimCalcs(CMatrix YMatrix) {
		int i, j, k = 0, ii, jj, elimRow;
		Complex Ynn, Yij, Yin, Ynj;
		int[] rowEliminated = null;
		boolean elementOpen = false;

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		k = 0;
		for (i = 0; i < nterm; i++) {
			for (j = 0; j < ncond; j++) {
				if (!terminals[i].getConductor(j).isClosed()) {
					if (!elementOpen) {
						rowEliminated = new int[YOrder];
						elementOpen = true;
					}
					// first do Kron reduction
					elimRow = j + k;
					Ynn = YMatrix.get(elimRow, elimRow);
					if (Ynn.abs() == 0.0)
						Ynn = new Complex(DSS.EPSILON, Ynn.getImaginary());
					rowEliminated[elimRow] = 1;
					for (ii = 0; ii < YOrder; ii++) {
						if (rowEliminated[ii] == 0) {
							Yin = YMatrix.get(ii, elimRow);
							for (jj = 0; jj < YOrder; jj++)
								if (rowEliminated[jj] == 0) {
									Yij = YMatrix.get(ii, jj);
									Ynj = YMatrix.get(elimRow, jj);
									YMatrix.setSym(ii, jj, Yij.subtract( Yin.multiply(Ynj).divide(Ynn) ));
								}
						}
					}
					// now zero out row and column
					YMatrix.zeroRow(elimRow);
					YMatrix.zeroCol(elimRow);
					YMatrix.set(elimRow, elimRow, new Complex(DSS.EPSILON, 0.0));  // in case node gets isolated
				}
			}
			k = k + ncond;
		}
		if (elementOpen)
			rowEliminated = new int[0];
	}

	/**
	 * Sum terminal currents into system currents array.
	 *
	 * Primarily for Newton iteration.
	 */
	public void sumCurrents() {
		if (enabled) {
			computeITerminal();

			SolutionObj sol = DSS.activeCircuit.getSolution();

			for (int i = 0; i < YOrder; i++)
				sol.setCurrent(nodeRef[i], sol.getCurrent(nodeRef[i]).add(ITerminal[i]));
		}
	}

	/**
	 * Bus voltages at indicated terminal.
	 *
	 * Fills VBuffer array which must be adequately allocated by calling routine.
	 */
	public void getTermVoltages(int iTerm, Complex[] VBuffer) {
		int i;
		SolutionObj sol;

		try {
			/* Return zero if terminal number improperly specified */
			if (iTerm <= 0 || iTerm >= nterm) {
				for (i = 0; i < ncond; i++)
					VBuffer[i] = Complex.ZERO;
				return;
			}

			sol = DSS.activeCircuit.getSolution();

			for (i = 0; i < ncond; i++)
				VBuffer[i] = sol.getNodeV( terminals[iTerm].getTermNodeRef(i) );
		} catch (Exception e) {
			DSS.doSimpleMsg("Error filling voltage buffer in getTermVoltages for circuit element:"+getDSSClassName()+"."+getName()+DSS.CRLF+
					"Probable cause: Invalid definition of element."+DSS.CRLF+
					"System error message: "+e.getMessage(), 755);
		}
	}

	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 1, String.format("%g", baseFrequency));  // base freq
		setPropertyValue(arrayOffset + 2, "true");  // enabled

		enabledProperty = arrayOffset + 2;  // keep track of this

		super.initPropertyValues(arrayOffset + 2);
	}

	public String getPropertyValue(int index) {
		String result;
		if (index == enabledProperty - 1) {
			if (enabled) {
				result = "true";
			} else {
				result = "false";
			}
		} else {
			result = super.getPropertyValue(index);
		}
		return result;
	}

	/**
	 * For the base class, just return complex zero.
	 *
	 * Derived classes have to supply appropriate function.
	 */
	public void getSeqLosses(Complex[] posSeqLosses, Complex[] negSeqLosses,
			Complex[] zeroModeLosses) {
		posSeqLosses[0] = Complex.ZERO;
		negSeqLosses[0] = Complex.ZERO;
		zeroModeLosses[0] = Complex.ZERO;
	}

	private boolean isGroundBus(String s) {
		boolean result = true;
		int i = s.indexOf(".1");
		if (i >= 0)
			result = false;
		i = s.indexOf(".2");
		if (i >= 0)
			result = false;
		i = s.indexOf(".3");
		if (i >= 0)
			result = false;
		i = s.indexOf('.');
		if (i == -1)
			result = false;
		return result;
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		boolean grnd;
		for (int i = 0; i < nterm; i++) {
			grnd = isGroundBus(busNames[i]);
			busNames[i] = Util.stripExtension(busNames[i]);
			if (grnd)
				busNames[i] = busNames[i] + ".0";
		}
	}

	/**
	 * Put terminal voltages in an array.
	 */
	public void computeVTerminal() {
		SolutionObj sol = DSS.activeCircuit.getSolution();
		for (int i = 0; i < YOrder; i++)
			VTerminal[i] = sol.getNodeV(nodeRef[i]);
	}

	public void zeroITerminal() {
		for (int i = 0; i < YOrder; i++)
			ITerminal[i] = Complex.ZERO;
	}

	public Terminal getTerminal(int idx) {
		return terminals[idx];
	}

	public int[] getNodeRef() {
		return nodeRef;
	}

	public void setNodeRef(int[] ref) {
		nodeRef = ref;
	}

	public int getYorder() {
		return YOrder;
	}

	public void setYOrder(int order) {
		YOrder = order;
	}

	public int getLastTerminalChecked() {
		return lastTerminalChecked;
	}

	public void setLastTerminalChecked(int checked) {
		lastTerminalChecked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean value) {
		checked = value;
	}

	public boolean hasEnergyMeter() {
		return hasEnergyMeter;
	}

	public void setHasEnergyMeter(boolean hasMeter) {
		hasEnergyMeter = hasMeter;
	}

	public boolean hasSensorObj() {
		return hasSensorObj;
	}

	public void setHasSensorObj(boolean value) {
		hasSensorObj = value;
	}

	public boolean isIsolated() {
		return isIsolated;
	}

	public void setIsolated(boolean value) {
		isIsolated = value;
	}

	public boolean hasControl() {
		return hasControl;
	}

	public void setHasControl(boolean value) {
		hasControl = value;
	}

	public boolean isPartofFeeder() {
		return isPartOfFeeder;
	}

	public void setPartofFeeder(boolean isPart) {
		isPartOfFeeder = isPart;
	}

	public CktElement getControlElement() {
		return controlElement;
	}

	public void setControlElement(CktElement element) {
		controlElement = element;
	}

	public Complex[] getITerminal() {
		return ITerminal;
	}

	public void setITerminal(Complex[] terminal) {
		ITerminal = terminal;
	}

	public Complex[] getVTerminal() {
		return VTerminal;
	}

	public void setVTerminal(Complex[] terminal) {
		VTerminal = terminal;
	}

	public double getBaseFrequency() {
		return baseFrequency;
	}

	public void setBaseFrequency(double frequency) {
		baseFrequency = frequency;
	}

	public Terminal[] getTerminals() {
		return terminals;
	}

	public void setTerminals(Terminal[] value) {
		terminals = value;
	}

	public void setActiveTerminal(Terminal terminal) {
		activeTerminal = terminal;
	}

	public Terminal getActiveTerminal() {
		return activeTerminal;
	}

}

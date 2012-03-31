/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.types.YPrimType;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.CMatrix;

abstract public class CktElement extends DSSObject {

	private String[] busNames;
	private boolean enabled;
	private int enabledProperty;
	private int activeTerminalIdx;
	private boolean YPrimInvalid;
	private int handle;

	protected int nTerms;
	protected int nConds;  // num conductors per terminal
	protected int nPhases;

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

	public CktElement(DSSClass parClass) {
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
		busIdx      = -1;
		nTerms      = 0;
		nConds      = 0;
		nPhases     = 0;
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

	public void setYPrimInvalid(boolean invalid) {
		YPrimInvalid = invalid;
		if (invalid) {
			// if this device is in the circuit, then we have to rebuild Y on a change in Yprim
			if (enabled) DSS.activeCircuit.getSolution().setSystemYChanged(true);
		}
	}

	public boolean isYprimInvalid() {
		return YPrimInvalid;
	}

	public void setActiveTerminalIdx(int value) {
		if (value >= 0 && value < nTerms) {
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

	/**
	 * @param index if index=-1 return true if all phases closed, else false
	 * @return the state of the selected conductor
	 */
	public boolean isConductorClosed(int index) {
		boolean isClosed;
		if (index == -1) {  // all phases
			isClosed = true;
			for (int i = 0; i < nPhases; i++) {
				if (!terminals[activeTerminalIdx].getConductor(i).isClosed()) {
					isClosed = false;
					break;
				}
			}
		} else {
			if (index >= 0 && index < nConds) {
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
			for (int i = 0; i < nPhases; i++)
				terminals[activeTerminalIdx].getConductor(i).setClosed(value);
			sol.setSystemYChanged(true);  // force Y matrix rebuild
			setYPrimInvalid(true);
		} else {
			if (index >= 0 && index < nConds) {
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
					value, parentClass.getClassName(), getName()), 749);
			return;
		}

		if (value != nConds)
			DSS.activeCircuit.setBusNameRedefined(true);
		nConds = value;
		setNumTerms(nTerms);  // realloc terminals; need more efficient way to do this
	}

	public int getNumConds() {
		return nConds;
	}

	public void setNumPhases(int value) {
		if (value > 0)
			nPhases = value;
	}

	public int getNumPhases() {
		return nPhases;
	}

	public void setNumTerms(int value) {
		int i;
		String[] newBusNames;

		// check for an almost certain programming error
		if (value <= 0) {
			DSS.doSimpleMsg(String.format("Invalid number of terminals (%d) for \"%s.%s\"",
					value, parentClass.getClassName(), getName()), 749);
			return;
		}

		// if value is same as present value, no reallocation necessary;
		// if either nTerms or nConds has changed then reallocate
		if ((value != nTerms) || (value * nConds != YOrder)) {

			/* Sanity check */
			if (nConds > 101) {
				DSS.doSimpleMsg(String.format("Warning: Number of conductors is very large (%d) for circuit element: \"%s.%s." +
						"Possible error in specifying the number of phases for element.",
						nConds, parentClass.getClassName(), getName()), 750);
			}

			/* Reallocate bus names */
			if (value < nTerms) {
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
					for (i = 0; i < nTerms; i++)
						newBusNames[i] = busNames[i];  // copy old into new
					for (i = 0; i < nTerms; i++)
						busNames[i] = "";  // decrement usage counts by setting to empty string
					for (i = nTerms; i < value; i++)
						newBusNames[i] = getName() + "_" + i;  // make up a bus name to stick in
					busNames = newBusNames;
				}
			}

			/* Reallocate terminals if ncond or nterm changed */
			if (terminals != null) {
				for (i = 0; i < nTerms; i++)
					terminals[i] = null;  // clean up old storage
			}

			terminals = Util.resizeArray(terminals, value);

			nTerms = value;  // set new number of terminals
			YOrder = nTerms * nConds;
			VTerminal = Util.resizeArray(VTerminal, YOrder);
			ITerminal = Util.resizeArray(ITerminal, YOrder);
			complexBuffer = Util.resizeArray(complexBuffer, YOrder);  // used by both PD and PC elements

			for (i = 0; i < value; i++) {
				terminals[i] = new Terminal(nConds);
			}
		}
	}

	public int getNumTerms() {
		return nTerms;
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

	public CMatrix getYPrimMatrix(YPrimType opt) {
		CMatrix Y = null;

		switch (opt) {
		case ALL_YPRIM:
			Y = YPrim;
			break;
		case SERIES:
			Y = YPrimSeries;
			break;
		case SHUNT:
			Y = YPrimShunt;
			break;
		}

		return Y;
	}

	/**
	 * Returns the storage arrays for fast access.
	 */
	public Complex[] getYPrimValues(YPrimType opt) {
		Complex[] result = null;

		switch (opt) {
		case ALL_YPRIM:
			if (YPrim != null)
				result = YPrim.asArray();
			break;
		case SERIES:
			if (YPrimSeries != null)
				result = YPrimSeries.asArray();
			break;
		case SHUNT:
			if (YPrimShunt != null)
				result = YPrimShunt.asArray();
			break;
		}

		return result;
	}

	/**
	 * Get present value of terminal current for reports
	 */
	abstract public void getCurrents(Complex[] curr);

	/**
	 * Returns injection currents
	 */
	abstract public void getInjCurrents(Complex[] curr);

	/**
	 * Applies to PC elements puts straight into solution array
	 */
	abstract public int injCurrents();

	public void getLosses(Complex[] totalLosses, Complex[] loadLosses, Complex[] noLoadLosses) {
		Complex totLosses = getLosses();  // watts, vars

		totalLosses[0] = totLosses;
		loadLosses[0] = totLosses;
		noLoadLosses[0] = Complex.ZERO;
	}

	/**
	 * Set nodeRef array for fast solution with intrinsics.
	 * Also allocates Vtemp & Itemp.
	 */
	public void setNodeRef(int iTerm, int[] nodeRefArray) {
		int size, size2;

		// allocate nodeRef and move new values into it
		size = YOrder;
		size2 = nConds;  // size for one terminal
		nodeRef = Util.resizeArray(nodeRef, size);  // doesn't do anything if already properly allocated
		System.arraycopy(nodeRefArray, 0, nodeRef, iTerm * nConds, size2);
		System.arraycopy(nodeRefArray, 0, terminals[iTerm].termNodeRef, 0, size2);  // copy in terminals as well

		// allocate temp array used to hold voltages and currents for calcs
		VTerminal = Util.resizeArray(VTerminal, YOrder);
		ITerminal = Util.resizeArray(ITerminal, YOrder);
		complexBuffer = Util.resizeArray(complexBuffer, YOrder);
	}

	public void setNodeRef(int iTerm, int nodeRefArray) {
		setNodeRef(iTerm, new int[] {nodeRefArray});
	}

	public String getFirstBus() {
		if (nTerms > 0) {
			busIdx = 0;
			return busNames[busIdx];
		} else {
			return "";
		}
	}

	public String getNextBus() {
		String name = "";
		if (nTerms > 0) {
			busIdx += 1;
			if (busIdx < nTerms) {
				name = busNames[busIdx];
			} else {
				busIdx = nTerms - 1;
			}
		}
		return name;
	}

	/**
	 * Get bus name by index
	 */
	public String getBus(int i) {
		return i < nTerms ? busNames[i] : "";
	}

	/**
	 * Set bus name by index
	 */
	public void setBus(int i, String name) {
		if (i < nTerms) {
			busNames[i] = name.toLowerCase();
			// set global flag to signal circuit to rebuild bus defs
			DSS.activeCircuit.setBusNameRedefined(true);
		} else {
			DSS.doSimpleMsg(String.format("Attempt to set bus name for non-existent" +
				" circuit element terminal(%d): \"%s\"", i, name), 7541);
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
		DSS.doSimpleMsg("recalcElementData in base CktElement class called for device = \"" +
				getName() + "\"", 754);
	}

	public void calcYPrim() {
		if (YPrimSeries != null) doYPrimCalcs(YPrimSeries);
		if (YPrimShunt != null) doYPrimCalcs(YPrimShunt);
		if (YPrim != null) doYPrimCalcs(YPrim);
	}

	/**
	 * Computes ITerminal for this device
	 */
	public void computeITerminal() {
		SolutionObj sol = DSS.activeCircuit.getSolution();
		// to save time, only recompute if a different solution than last time it was computed
		if (ITerminalSolutionCount != sol.getSolutionCount()) {
			getCurrents(ITerminal);
			ITerminalSolutionCount = sol.getSolutionCount();
		}
	}

	/** Max of ITerminal 1 phase currents */
	public double maxTerminalOneIMag() {
		double max = 0.0;
		if (enabled) {
			for (int i = 0; i < nPhases; i++)
				max = Math.max(max, Math.pow(ITerminal[i].real(), 2) + Math.pow(ITerminal[i].imag(), 2));
		}
		return Math.sqrt(max);  // just do the sqrt once and save a little time
	}

	/**
	 * Get total complex power in active terminal
	 */
	public Complex getPower(int idxTerm) {
		Complex cPower = Complex.ZERO;
		int i, k, n;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		activeTerminalIdx = idxTerm;

		if (enabled) computeITerminal();

		// sum complex power going into phase conductors of active terminal
		k = idxTerm * nConds;
		for (i = 0; i < nConds; i++) {
			n = activeTerminal.getTermNodeRef(i);  // don't bother with grounded node
			if (n > 0) {
				cPower = cPower.add( sol.getNodeV(n).mult(ITerminal[k + i].conj()) );
			}
		}

		/* If this is a positive sequence circuit, then we need to multiply by 3 to get the 3-phase power */
		if (DSS.activeCircuit.isPositiveSequence()) {
			cPower = cPower.mult(3.0);
		}

		return cPower;
	}

	/**
	 * Get total losses in circuit element, all phases, all terminals.
	 * Returns complex losses (watts, vars).
	 */
	public Complex getLosses() {
		Complex loss, totalLoss = Complex.ZERO;
		int k, n;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (enabled) {
			computeITerminal();

			// sum complex power going into all conductors of all terminals
			for (k = 0; k < YOrder; k++) {
				n = nodeRef[k];
				if (n > 0) {
					loss = sol.getNodeV(n).mult( ITerminal[k].conj() );
					if (DSS.activeCircuit.isPositiveSequence())
						loss = loss.mult(3.0);
					totalLoss = totalLoss.add(loss);
				}
			}
		}
		return totalLoss;
	}

	/**
	 * Get the power in each phase (complex losses) of active terminal
	 * neutral conductors are ignored by this routine.
	 */
	public void getPhasePower(Complex[] powerBuffer) {
		int i, n;
		Complex S;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (enabled) {
			computeITerminal();
			for (i = 0; i < YOrder; i++) {
				n = nodeRef[i];  // increment through terminals
				if (n > 0) {
					S = sol.getNodeV(n).mult( ITerminal[i].conj() );
					if (DSS.activeCircuit.isPositiveSequence())
						S = S.mult(3.0);
					powerBuffer[i] = S;
				}
			}
		} else {
			for (i = 0; i < YOrder; i++) powerBuffer[i] = Complex.ZERO;
		}
	}

	/**
	 * Get the losses in each phase (complex losses);  Power difference coming out
	 * each phase. Note: This can be misleading if the nodeV voltage is greatly unbalanced.
	 *
	 * Neutral conductors are ignored by this routine.
	 */
	public void getPhaseLosses(int[] numPhases, Complex[] lossBuffer) {
		int i, j, k, n;
		Complex losses, loss;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		numPhases[0] = nPhases;
		if (enabled) {
			computeITerminal();

			for (i = 0; i < numPhases[0]; i++) {
				losses = Complex.ZERO;
				for (j = 0; j < nTerms; j++) {
					k = j * nConds + i;
					n = nodeRef[k];  // increment through terminals
					if (n > 0) {
						loss = sol.getNodeV(n).mult( ITerminal[k].conj() );
						if (DSS.activeCircuit.isPositiveSequence())
							loss = loss.mult(3.0);
						loss = losses.add(loss);
					}
				}
				lossBuffer[i] = losses;
			}
		} else {
			for (i = 0; i < numPhases[0]; i++)
				lossBuffer[i] = Complex.ZERO;
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		pw.println(enabled ? "! ENABLED" : "! DISABLED");

		if (complete) {
			pw.println("! NPhases = " + nPhases);
		        pw.println("! NConds = " + nConds);
		        pw.println("! NTerms = " + nTerms);
		        pw.println("! Yorder = " + YOrder);
		        pw.print("! NodeRef = \"");
		        if (nodeRef == null) {
		        	pw.print("nil");
		        } else {
		        	for (i = 0; i < YOrder; i++) pw.print(nodeRef[i] + " ");
		        }
		        pw.println("\"");
		        pw.print("! Terminal Status: [");
		        for (i = 0; i < nTerms; i++) {
				for (j = 0; j < nConds; j++) {
					pw.write(terminals[i].getConductor(j).isClosed() ? "C " : "O ");
				}
			}
		        pw.println("]");
		        pw.print("! Terminal Bus Ref: [");
		        for (i = 0; i < nTerms; i++) {
				for (j = 0; j < nConds; j++) {
					pw.print(terminals[i].getBusRef() + " ");
				}
		        }
		        pw.println("]");
		        pw.println();

		        if (YPrim != null) {
		        	pw.println("! YPrim (G matrix)");
		        	for (i = 0; i < YOrder; i++) {
					pw.print("! ");
					for (j = 0; j < i; j++) {
						pw.printf(" %13.10g |", YPrim.get(i, j).real());
					}
					pw.println();
		        	}
		        	pw.println("! YPrim (B Matrix) = ");
		        	for (i = 0; i < YOrder; i++) {
					pw.print("! ");
					for (j = 0; j < i; j++) {
						pw.printf(" %13.10g |", YPrim.get(i, j).imag());
					}
					pw.println();
		        	}
		        }
		}
		pw.close();
	}

	private void doYPrimCalcs(CMatrix YMatrix) {
		int i, j, k, ii, jj, elimRow;
		Complex Ynn, Yij, Yin, Ynj;
		boolean[] rowEliminated = null;
		boolean elementOpen = false;

		/*
		 * Now account for open conductors
		 * For any conductor that is open, zero out row and column
		 */
		k = 0;
		for (i = 0; i < nTerms; i++) {
			for (j = 0; j < nConds; j++) {
				if (!terminals[i].getConductor(j).isClosed()) {
					if (!elementOpen) {
						rowEliminated = new boolean[YOrder];
						elementOpen = true;
					}
					// first do Kron reduction
					elimRow = j + k;
					Ynn = YMatrix.get(elimRow, elimRow);
					if (Ynn.abs() == 0.0)
						Ynn = new Complex(DSS.EPSILON, Ynn.imag());
					rowEliminated[elimRow] = true;
					for (ii = 0; ii < YOrder; ii++) {
						if (!rowEliminated[ii]) {
							Yin = YMatrix.get(ii, elimRow);
							for (jj = 0; jj < YOrder; jj++) {
								if (!rowEliminated[jj]) {
									Yij = YMatrix.get(ii, jj);
									Ynj = YMatrix.get(elimRow, jj);
									YMatrix.setSym(ii, jj, Yij.sub( Yin.mult(Ynj).div(Ynn) ));
								}
							}
						}
					}
					// now zero out row and column
					YMatrix.zeroRow(elimRow);
					YMatrix.zeroCol(elimRow);
					YMatrix.set(elimRow, elimRow, new Complex(DSS.EPSILON, 0.0));  // in case node gets isolated
				}
			}
			k = k + nConds;
		}
		rowEliminated = null;
	}

	/**
	 * Sum terminal currents into system currents array.
	 *
	 * Primarily for Newton iteration.
	 */
	public void sumCurrents() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (enabled) {
			computeITerminal();

			for (int i = 0; i < YOrder; i++)
				sol.setCurrent(nodeRef[i], sol.getCurrent(nodeRef[i]).add(ITerminal[i]));
		}
	}

	/**
	 * Bus voltages at indicated terminal.
	 *
	 * Fills VBuffer array which must be adequately allocated by calling routine.
	 */
	public void getTermVoltages(int iterm, Complex[] VBuffer) {
		int i;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			/* Return zero if terminal number improperly specified */
			if (iterm <= 0 || iterm >= nTerms) {
				for (i = 0; i < nConds; i++) VBuffer[i] = Complex.ZERO;
				return;
			}

			for (i = 0; i < nConds; i++) {
				VBuffer[i] = sol.getNodeV(terminals[iterm].getTermNodeRef(i));
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error filling voltage buffer in getTermVoltages for circuit element:" +
					getDSSClassName() + "." + getName() + DSS.CRLF +
					"Probable cause: Invalid definition of element." + DSS.CRLF +
					"System error message: " + e.getMessage(), 755);
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 1, String.format("%g", baseFrequency));  // base freq
		setPropertyValue(arrayOffset + 2, "true");  // enabled

		enabledProperty = arrayOffset + 2;  // keep track of this

		super.initPropertyValues(arrayOffset + 2);
	}

	@Override
	public String getPropertyValue(int index) {
		String val;
		if (index == enabledProperty) {
			val = enabled ? "true" : "false";
		} else {
			val = super.getPropertyValue(index);
		}
		return val;
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
		int i;
		boolean grnd = true;

		i = s.indexOf(".1");
		if (i >= 0) grnd = false;

		i = s.indexOf(".2");
		if (i >= 0) grnd = false;

		i = s.indexOf(".3");
		if (i >= 0) grnd = false;

		i = s.indexOf('.');
		if (i == -1) grnd = false;

		return grnd;
	}

	/**
	 * Make a positive sequence model
	 */
	public void makePosSequence() {
		boolean grnd;

		for (int i = 0; i < nTerms; i++) {
			grnd = isGroundBus(busNames[i]);
			busNames[i] = Util.stripExtension(busNames[i]);
			if (grnd) busNames[i] = busNames[i] + ".0";
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
		for (int i = 0; i < YOrder; i++) ITerminal[i] = Complex.ZERO;
	}

	public Terminal getTerminal(int idx) {
		return terminals[idx];
	}

	public Complex getVTerminal(int idx) {
		return VTerminal[idx];
	}

	public Complex[] getVTerminal() {
		return VTerminal;
	}

	public Complex getITerminal(int idx) {
		return ITerminal[idx];
	}

	public Complex[] getITerminal() {
		return ITerminal;
	}

	public int getNodeRef(int idx) {
		return nodeRef[idx];
	}

	public int[] getNodeRef() {
		return nodeRef;
	}

	public boolean hasEnergyMeter() {
		return hasEnergyMeter;
	}

	public boolean hasSensorObj() {
		return hasSensorObj;
	}

	public boolean hasControl() {
		return hasControl;
	}

	public int getNPhases() {
		return nPhases;
	}

	public int getYOrder() {
		return YOrder;
	}

	public CktElement getControlElement() {
		return controlElement;
	}

	public double getBaseFrequency() {
		return baseFrequency;
	}

	public Terminal getActiveTerminal() {
		return activeTerminal;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isIsolated() {
		return isIsolated;
	}

	public void setHasEnergyMeter(boolean hasEnergyMeter) {
		this.hasEnergyMeter = hasEnergyMeter;
	}

	public void setHasControl(boolean hasControl) {
		this.hasControl = hasControl;
	}

	public void setControlElement(CktElement controlElement) {
		this.controlElement = controlElement;
	}

	public void setBaseFrequency(double baseFrequency) {
		this.baseFrequency = baseFrequency;
	}

	public void setYOrder(int yOrder) {
		YOrder = yOrder;
	}

	public void setLastTerminalChecked(int lastTerminalChecked) {
		this.lastTerminalChecked = lastTerminalChecked;
	}

	public void setHasSensorObj(boolean hasSensorObj) {
		this.hasSensorObj = hasSensorObj;
	}

	public void setIsolated(boolean isIsolated) {
		this.isIsolated = isIsolated;
	}

	public void setPartOfFeeder(boolean isPartOfFeeder) {
		this.isPartOfFeeder = isPartOfFeeder;
	}

}

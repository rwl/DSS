package com.epri.dss.common.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CMatrix;

public class DSSCktElement extends DSSObjectImpl implements CktElement {

	private String[] BusNames;
	private boolean Enabled;
	private int EnabledProperty;
	private int ActiveTerminalIdx;
	private boolean YPrimInvalid;
	private int Handle;

	protected int nTerms;
	/* no. conductors per terminal */
	protected int nConds;
	protected int nPhases;

	protected Complex[] ComplexBuffer;

	protected int IterminalSolutionCount;

	protected int BusIndex;

	protected CMatrix YPrim_Series;
	protected CMatrix YPrim_Shunt;
	protected CMatrix YPrim;  // Order will be NTerms * Ncond

	/* Frequency at which YPrim has been computed */
	protected double YprimFreq;

	/* Total Noderef array for element */
	protected int[] NodeRef;
	protected int Yorder;
	/* Flag used in tree searches */
	protected int LastTerminalChecked;

	protected boolean Checked, HasEnergyMeter, HasSensorObj, IsIsolated,
		HasControl, IsPartofFeeder;

	protected DSSCktElement ControlElement;

	protected Complex[] Iterminal;
	protected Complex[] Vterminal;

	protected double BaseFrequency;

	protected PowerTerminal[] Terminals;

	protected PowerTerminal ActiveTerminal;

	public DSSCktElement(DSSClass ParClass) {
		super(ParClass);

		this.NodeRef = null;
		this.YPrim_Series = null;
		this.YPrim_Shunt  = null;
		this.YPrim        = null;
		this.Terminals    = null;
		this.BusNames    = null;
		this.Vterminal    = null;
		this.Iterminal    = null;  // present value of terminal current
		this.ComplexBuffer = null;

		this.Handle      = -1;
		this.BusIndex    = 0;
		this.nTerms      = 0;
		this.nConds      = 0;
		this.nPhases     = 0;
		this.DSSObjType  = 0;
		this.Yorder      = 0;

		this.YPrimInvalid   = true;
		this.Enabled        = true;
		this.HasEnergyMeter = false;
		this.HasSensorObj   = false;
		this.IsPartofFeeder = false;
		this.IsIsolated     = false;

		this.ControlElement = null;  // Init to no control on this element.
		this.HasControl     = false;

		this.ActiveTerminalIdx   = 1;
		this.LastTerminalChecked = 0;

		/* Indicates which solution Itemp is computed for */
		this.IterminalSolutionCount = -1;

		this.BaseFrequency = DSSGlobals.getInstance().getActiveCircuit().getFundamental();
	}

	public void setYprimInvalid(boolean Value) {
		YPrimInvalid = Value;
		if (Value) {
			// If this device is in the circuit, then we have to rebuild Y on a change in Yprim
			if (Enabled)
				DSSGlobals.getInstance().getActiveCircuit().getSolution().setSystemYChanged(true);
		}
	}

	public boolean isYprimInvalid() {
		return YPrimInvalid;
	}

	public void setActiveTerminalIdx(int Value) {
		if ((Value > 0) && (Value <= nTerms)) {
			ActiveTerminalIdx = Value;
			ActiveTerminal = Terminals[Value] ;
		}
	}

	public int getActiveTerminalIdx() {
		return ActiveTerminalIdx;
	}

	public void setHandle(int Value) {
		Handle = Value;
	}

	/**
	 * Returns the state of selected conductor.
	 * if index=-1 return true if all phases closed, else false
	 */
	public boolean getConductorClosed(int Index) {
		boolean Result;
		if (Index == 0) {
			Result = true;
			for (int i = 0; i < nPhases; i++) {
				if (!Terminals[ActiveTerminalIdx].getConductors()[i].isClosed()) {
					Result = false;
					break;
				}
			}
		} else {
			if ((Index > 0) && (Index <= nConds)) {
				Result = Terminals[ActiveTerminalIdx].getConductors()[Index].isClosed();
			} else {
				Result = false;
			}
		}
		return Result;
	}

	public void setConductorClosed(int Index, boolean Value) {
		if (Index == 0) {  // Do all conductors
			for (int i = 0; i < nPhases; i++)
				Terminals[ActiveTerminalIdx].getConductors()[i].setClosed(Value);
			DSSGlobals.getInstance().getActiveCircuit().getSolution().setSystemYChanged(true);  // force Y matrix rebuild
			YPrimInvalid = true;
		} else {
			if ((Index > 0) && (Index <= nConds)) {
				Terminals[ActiveTerminalIdx].getConductors()[Index].setClosed(Value);
				DSSGlobals.getInstance().getActiveCircuit().getSolution().setSystemYChanged(true);
				YPrimInvalid = true;
			}
		}
	}

	public void setNConds(int Value) {
		// Check for an almost certain programming error
		if (Value <= 0) {
			DSSGlobals.getInstance().doSimpleMsg(String.format("Invalid number of terminals (%d) for \"%s.%s\"",
								Value, ParentClass.getName(), getName()), 749);
			return;
		}

		if (Value != nConds)
			DSSGlobals.getInstance().getActiveCircuit().setBusNameRedefined(true);
		nConds = Value;
		setNTerms(this.nTerms);  // ReallocTerminals    // FIXME Need more efficient way to do this
	}

	public int getNConds() {
		return nConds;
	}

	public void setNPhases(int Value) {
		if (Value > 0)
			nPhases = Value;
	}

	public int getNPhases() {
		return nPhases;
	}

	public void setNTerms(int Value) {
		String[] NewBusNames;
		DSSGlobals Globals = DSSGlobals.getInstance();

		// Check for an almost certain programming error
		if (Value <= 0) {
			Globals.doSimpleMsg(String.format("Invalid number of terminals (%d) for \"%s.%s\"",
								Value, ParentClass.getName(), getName()), 749);
			return;
		}

		// If value is same as present value, no reallocation necessary;
		// If either Nterms or Nconds has changed then reallocate
		if ((Value != nTerms) || (Value * nConds != Yorder)) {

			/* Sanity Check */
			if (nConds > 101) {
				Globals.doSimpleMsg(String.format("Warning: Number of conductors is very large (%d) for Circuit Element: \"%s.%s." +
						"Possible error in specifying the Number of Phases for element.",
						nConds, ParentClass.getName(), getName()), 750);
			}

			/* ReAllocate BusNames */
			// because they are Strings, we have to do it differently

			if (Value < nTerms) {
				BusNames = (String[]) Utilities.resizeArray(BusNames, Value);  // Keeps old values; truncates storage
			} else {
				if (BusNames == null) {
					// First allocation
					BusNames = new String[Value];  // fill with zeros or strings will crash
					for (int i = 0; i < Value; i++)
						BusNames[i] = getName()+'_'+String.valueOf(i);  // Make up a bus name to stick in.;
					// This is so devices like transformers which may be defined on multiple commands
					// will have something in the BusNames array.
				} else {
					NewBusNames = new String[Value];  // make some new space
					for (int i = 0; i < nTerms; i++)
						NewBusNames[i] = BusNames[i];  // copy old into new
					for (int i = 0; i < nTerms; i++)
						BusNames[i] = "";  // decrement usage counts by setting to nil string
					for (int i = 0; i < nTerms + 1; i++)
						NewBusNames[i] = getName()+'_'+String.valueOf(i);  // Make up a bus name to stick in.
					BusNames = NewBusNames;
				}
			}

			/* Reallocate Terminals if nConds or nTerms changed */
			if (Terminals != null)
				for (int i = 0; i < nTerms; i++)
					Terminals[i] = null;  // clean up old storage

			Terminals = (PowerTerminal[]) Utilities.resizeArray(Terminals, Value);

			nTerms = Value;    // Set new number of terminals
			Yorder = nTerms * nConds;
			Vterminal = (Complex[]) Utilities.resizeArray(Vterminal, Yorder);
			Iterminal = (Complex[]) Utilities.resizeArray(Iterminal, Yorder);
			ComplexBuffer = (Complex[]) Utilities.resizeArray(ComplexBuffer, Yorder);  // used by both PD and PC elements

			for (int i = 0; i < Value; i++)
				Terminals[i] = new PowerTerminal(nConds);
		}
	}

	public int getNTerms() {
		return nTerms;
	}

	public void setEnabled(boolean Value) {
		if (Value != Enabled) {  // don't change unless this represents a change
			Enabled = Value;
			// force rebuilding of Y matrix and bus lists
			DSSGlobals.getInstance().getActiveCircuit().setBusNameRedefined(true);
		}
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public int getYPrim(CMatrix Ymatrix, int Opt) {
		switch (Opt) {
		case DSSGlobals.ALL_YPRIM:
			Ymatrix = YPrim;
		case DSSGlobals.SERIES:
			Ymatrix = YPrim_Series;
		case DSSGlobals.SHUNT:
			Ymatrix = YPrim_Shunt;
		}
		return 0;
	}

	/**
	 * Returns the storage arrays for fast access.
	 */
	public Complex[] getYPrimValues(int Opt) {
		int nOrder = 0;
		Complex[] Result = null;

		switch (Opt) {
		case DSSGlobals.ALL_YPRIM:
			if (YPrim != null)
				Result = YPrim.asArray(nOrder);
		case DSSGlobals.SERIES:
			if (YPrim_Series != null)
				Result = YPrim_Series.asArray(nOrder);
		case DSSGlobals.SHUNT:
			if (YPrim_Shunt != null)
				Result = YPrim_Shunt.asArray(nOrder);
		}

		return Result;
	}

	/**
	 * Get present value of terminal current for reports.
	 */
	public void getCurrents(Complex[] Curr) {
		DSSGlobals.getInstance().doErrorMsg("Something is wrong. Got to base CktElement getCurrents for Object:"+DSSGlobals.CRLF+getDSSClassName()+"."+getName(),
				"N/A",
				"Should not be able to get here. Probable Programming Error.", 751);
	}

	/**
	 * Returns injection currents
	 */
	public void getInjCurrents(Complex[] Curr) {
		DSSGlobals.getInstance().doErrorMsg("Something is Wrong. Got to base CktElement GetInjCurrents for Object:"+DSSGlobals.CRLF+getDSSClassName()+"."+getName(), "****",
				"Should not be able to get here. Probable Programming Error.", 752);
	}

	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {
		TotalLosses = getLosses();  // Watts, vars
		LoadLosses = TotalLosses;
		NoLoadLosses = Complex.ZERO;
	}

	/**
	 * Applies to PC Elements Puts straight into Solution Array
	 */
	public int injCurrents() {
		DSSGlobals.getInstance().doErrorMsg(("Improper call to InjCurrents for Element: " + getName() + "."), "****",
				"Called CktElement class base function instead of actual.", 753);
		return 0;
	}

	/**
	 * Set NodeRef Array for fast solution with intrinsics.
	 *
	 * Also allocates VTemp & Itemp.
	 */
	public void setNodeRef(int iTerm, int[] NodeRefArray) {
		int Size, Size2;

		// Allocate NodeRef and move new values into it.
		Size = Yorder;
		Size2 = nConds;  // Size for one terminal
		NodeRef = (int[]) Utilities.resizeArray(NodeRef, Size);  // doesn't do anything if already properly allocated
		System.arraycopy(NodeRefArray[0], 0, NodeRef[(iTerm - 1) * nConds + 1], 0, Size2);
		System.arraycopy(NodeRefArray[0], 0, Terminals[iTerm].TermNodeRef[0], 0, Size2);  // copy in terminal as well

		// Allocate temp array used to hold voltages and currents for calcs
		Vterminal = (Complex[]) Utilities.resizeArray(Vterminal, Yorder);
		Iterminal = (Complex[]) Utilities.resizeArray(Iterminal, Yorder);
		ComplexBuffer = (Complex[]) Utilities.resizeArray(ComplexBuffer, Yorder);
	}
	public void setNodeRef(int iTerm, int NodeRefArray) {
		setNodeRef(iTerm, new int[] {NodeRefArray});
	}

	public String getFirstBus() {
		if (nTerms > 0) {
			BusIndex = 0;  // TODO Check zero based indexing
			return BusNames[BusIndex];
		} else {
			return "";
		}
	}

	public String getNextBus() {
		String Result = "";
		if (nTerms > 0) {
			BusIndex += 1;
			if (BusIndex <= nTerms) {
				Result = BusNames[BusIndex];
			} else {
				BusIndex = nTerms;
			}
		}
		return Result;
	}

	/**
	 * Get bus name by index.
	 */
	public String getBus(int i) {
		if (i <= nTerms) {
			return BusNames[i];
		} else {
			return "";
		}
	}

	/**
	 * Set bus name by index.
	 */
	public void setBus(int i, String s) {
		if (i <= nTerms) {
			BusNames[i] = s.toLowerCase();
			// Set Global Flag to signal circuit to rebuild busdefs
			DSSGlobals.getInstance().getActiveCircuit().setBusNameRedefined(true);
		} else {
			DSSGlobals.getInstance().doSimpleMsg(String.format("Attempt to set bus name for non-existent circuit element terminal(%d): \"%s\"", i, s), 7541);
		}
	}

	/**
	 * Set freq and recompute YPrim.
	 */
	private void setFreq(double value) {
		if (value > 0.0)
			YprimFreq = value;
	}

	public void recalcElementData() {
		DSSGlobals.getInstance().doSimpleMsg("Virtual proc RecalcElementData in Base CktElement Class Called for Device = \"" + getName() +"\"", 754);
	}

	public void calcYPrim() {
		if (YPrim_Series != null)
			doYprimCalcs(YPrim_Series);
		if (YPrim_Shunt != null)
			doYprimCalcs(YPrim_Shunt);
		if (YPrim != null)
			doYprimCalcs(YPrim);
	}

	/**
	 * Computes Iterminal for this device
	 */
	public void computeIterminal() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		// to save time, only recompute if a different solution than last time it was computed.
		if (IterminalSolutionCount != Globals.getActiveCircuit().getSolution().getSolutionCount()) {
			getCurrents(Iterminal);
			IterminalSolutionCount = Globals.getActiveCircuit().getSolution().getSolutionCount();
		}
	}

	/**
	 * Max of Iterminal 1 phase currents.
	 */
	public double maxTerminalOneIMag() {
		double Result = 0.0;
		if (Enabled)
			for (int i = 0; i < nPhases; i++) {
				Result = Math.max(Result, Math.pow(Iterminal[i].getReal(), 2) + Math.pow(Iterminal[i].getImaginary(), 2));
			}
		return Math.sqrt(Result);  // just do the sqrt once and save a little time
	}

	/**
	 * Get total complex power in active terminal.
	 */
	public Complex getPower(int idxTerm) {
		Complex cPower = Complex.ZERO;
		int i, k, n;

		ActiveTerminalIdx = idxTerm;

		if (Enabled)
		computeIterminal();

		// Method: Sum complex power going into phase conductors of active terminal
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		k = (idxTerm - 1) * nConds;
		for (i = 0; i < nConds; i++) {  // 11-7-08 Changed from Fnphases - was not accounting for all conductors
			n = ActiveTerminal.getTermNodeRef()[i];  // don't bother for grounded node
			if (n > 0)
				// FIXME Implement complex accumulate function
				cPower = cPower.add( sol.getNodeV()[n].multiply(Iterminal[k + i].conjugate()) );
		}

		/* If this is a positive sequence circuit, then we need to multiply by 3 to get the 3-phase power */
		if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence())
			cPower = cPower.multiply(3.0);

		return cPower;
	}

	/**
	 * Get total losses in circuit element, all phases, all terminals.
	 * Returns complex losses (watts, vars).
	 */
	public Complex getLosses() {
		Complex cLoss = Complex.ZERO;
		int k, n;

		if (Enabled) {
		computeIterminal();

		// Method: Sum complex power going into all conductors of all terminals.
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		for (k = 0; k < Yorder; k++) {
			n = NodeRef[k];
			if (n > 0) {
				if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence()) {
					cLoss = cLoss.add( sol.getNodeV()[n].multiply(Iterminal[k].conjugate()).multiply(3.0) );
				} else {
					cLoss = cLoss.add( sol.getNodeV()[n].multiply(Iterminal[k].conjugate()) );
				}
			}
		}
		}

		return cLoss;
	}

	/**
	 * Get the power in each phase (complex losses) of active terminal
	 * neutral conductors are ignored by this routine.
	 */
	public void getPhasePower(Complex[] PowerBuffer) {
		int i, n;

		if (Enabled) {
			computeIterminal();

			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

			for (i = 0; i < Yorder; i++) {
				n = NodeRef[i];  // increment through terminals
				if (n > 0) {
					if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence()) {
						PowerBuffer[i] = sol.getNodeV()[n].multiply(Iterminal[i].conjugate()).multiply(3.0);
					} else {
						PowerBuffer[i] = sol.getNodeV()[n].multiply(Iterminal[i].conjugate());
					}
				}
			}
		} else {
			for (i = 0; i < Yorder; i++) {
				PowerBuffer[i] = Complex.ZERO;
			}
		}
	}

	/**
	 * Get the losses in each phase (complex losses);  Power difference coming out
	 * each phase. Note: This can be misleading if the nodev voltage is greatly unbalanced.
	 *
	 * Neutral conductors are ignored by this routine.
	 */
	public void getPhaseLosses(int Num_Phases, Complex[] LossBuffer) {
		int i, j, k, n;
		Complex cLoss;

		Num_Phases = nPhases;
		if (Enabled) {
			computeIterminal();

			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

			for (i = 0; i < Num_Phases; i++) {
				cLoss = Complex.ZERO;
				for (j = 0; j < nTerms; j++) {
					k = (j - 1) * nConds + i;
					n = NodeRef[k];  // increment through terminals
					if (n > 0) {
						if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence()) {
							cLoss = cLoss.add( sol.getNodeV()[n].multiply(Iterminal[k].conjugate()).multiply(3.0) );
						} else {
							cLoss = cLoss.add( sol.getNodeV()[n].multiply(Iterminal[k].conjugate()) );
						}
					}
				}
				LossBuffer[i] = cLoss;
			}
		} else {
			for (i = 0; i < Num_Phases; i++)
				LossBuffer[i] = Complex.ZERO;
		}
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	private void doYprimCalcs(CMatrix Ymatrix) {
		int i, j, k = 0, ii, jj, ElimRow;
		Complex Ynn, Yij, Yin, Ynj;
		int[] RowEliminated = null;
		boolean ElementOpen = false;

		/* Now Account for Open Conductors */
		/* For any conductor that is open, zero out row and column */
		k = 0;
		for (i = 0; i < nTerms; i++) {
			for (j = 0; j < nConds; j++) {
				if (!Terminals[i].getConductors()[j].isClosed()) {
					if (!ElementOpen) {
						RowEliminated = new int[Yorder];
						ElementOpen = true;
					}
					// First do Kron Reduction
					ElimRow = j + k;
					Ynn = Ymatrix.getElement(ElimRow, ElimRow);
					if (Ynn.abs() == 0.0)
						Ynn = new Complex(DSSGlobals.EPSILON, Ynn.getImaginary());
					RowEliminated[ElimRow] = 1;  // TODO Check zero based indexing.
					for (ii = 0; ii < Yorder; ii++) {
						if (RowEliminated[ii] == 0) {
							Yin = Ymatrix.getElement(ii, ElimRow);
							for (jj = 0; jj < Yorder; jj++)
								if (RowEliminated[jj] == 0) {
									Yij = Ymatrix.getElement(ii, jj);
									Ynj = Ymatrix.getElement(ElimRow, jj);
									Ymatrix.setElemSym(ii, jj, Yij.subtract(Yin.multiply(Ynj).divide(Ynn)));
								}
						}
					}
					// Now zero out row and column
					Ymatrix.zeroRow(ElimRow);
					Ymatrix.zeroCol(ElimRow);
					Ymatrix.setElement(ElimRow, ElimRow, new Complex(DSSGlobals.EPSILON, 0.0));  // In case node gets isolated
				}
			}
			k = k + nConds;
		}
		if (ElementOpen)
			RowEliminated = new int[0];
	}

	/**
	 * Sum Terminal Currents into System Currents Array.
	 *
	 * Primarily for Newton iteration.
	 */
	public void sumCurrents() {
		if (Enabled) {
			computeIterminal();

			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

			for (int i = 0; i < Yorder; i++)
				sol.getCurrents()[NodeRef[i]] = sol.getCurrents()[NodeRef[i]].add(Iterminal[i]);  // NodeRef=0 is OK
		}
	}

	/**
	 * Bus Voltages at indicated terminal.
	 *
	 * Fills Vbuffer array which must be adequately allocated by calling routine.
	 */
	public void getTermVoltages(int iTerm, Complex[] VBuffer) {
		try {
			int ncond = nConds;

			/* return Zero if terminal number improperly specified */
			if ((iTerm < 0) || (iTerm > nTerms)) {  // TODO Check zero based indexing
				for (int i = 0; i < ncond; i++)
					VBuffer[i] = Complex.ZERO;
				return;
			}

			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

			for (int i = 0; i < ncond; i++)
				VBuffer[i] = sol.getNodeV()[Terminals[iTerm].getTermNodeRef()[i]];
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error filling voltage buffer in getTermVoltages for Circuit Element:"+getDSSClassName()+"."+getName()+DSSGlobals.CRLF+
					"Probable Cause: Invalid definition of element."+DSSGlobals.CRLF+
					"System Error Message: "+e.getMessage(), 755);
		}
	}

	public void initPropertyValues(int ArrayOffset) {  // TODO Check zero based indexing
		PropertyValue[ArrayOffset + 1] = String.format("%-g", BaseFrequency);  // Base freq
		PropertyValue[ArrayOffset + 2] = "true";  // Enabled
		EnabledProperty = ArrayOffset + 2;     // keep track of this

		super.initPropertyValues(ArrayOffset + 2);
	}

	public String getPropertyValue(int Index) {
		String Result;
		if (Index == EnabledProperty) {
			if (Enabled) {
				Result = "true";
			} else {
				Result = "false";
			}
		} else {
			Result = super.getPropertyValue(Index);
		}
		return Result;
	}

	/**
	 * For the base class, just return complex zero.
	 *
	 * Derived classes have to supply appropriate function.
	 */
	public void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroModeLosses) {
		PosSeqLosses = Complex.ZERO;
		NegSeqLosses = Complex.ZERO;
		ZeroModeLosses = Complex.ZERO;
	}

	private boolean isGroundBus(String S) {
		boolean Result = true;
		int i = S.indexOf(".1");
		if (i >= 0) Result = false;
		i = S.indexOf(".2");
		if (i >= 0) Result = false;
		i = S.indexOf(".3");
		if (i >= 0) Result = false;
		i = S.indexOf('.');
		if (i == -1) Result = false;
		return Result;
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		boolean grnd;
		for (int i = 0; i < nTerms; i++) {
			grnd = isGroundBus(BusNames[i]);
			BusNames[i] = Utilities.stripExtension(BusNames[i]);
			if (grnd)
				BusNames[i] = BusNames[i] + ".0";
		}
	}

	/**
	 * Put terminal voltages in an array.
	 */
	public void computeVterminal() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		for (int i = 0; i < Yorder; i++)
			Vterminal[i] = sol.getNodeV()[NodeRef[i]];
	}

	public void zeroITerminal() {
		for (int i = 0; i < Yorder; i++)
			Iterminal[i] = Complex.ZERO;
	}

	public int[] getNodeRef() {
		return NodeRef;
	}

	public void setNodeRef(int[] nodeRef) {
		NodeRef = nodeRef;
	}

	public int getYorder() {
		return Yorder;
	}

	public void setYorder(int yorder) {
		Yorder = yorder;
	}

	public int getLastTerminalChecked() {
		return LastTerminalChecked;
	}

	public void setLastTerminalChecked(int lastTerminalChecked) {
		LastTerminalChecked = lastTerminalChecked;
	}

	public boolean isChecked() {
		return Checked;
	}

	public void setChecked(boolean checked) {
		Checked = checked;
	}

	public boolean hasEnergyMeter() {
		return HasEnergyMeter;
	}

	public void setHasEnergyMeter(boolean hasEnergyMeter) {
		HasEnergyMeter = hasEnergyMeter;
	}

	public boolean hasSensorObj() {
		return HasSensorObj;
	}

	public void setHasSensorObj(boolean hasSensorObj) {
		HasSensorObj = hasSensorObj;
	}

	public boolean isIsolated() {
		return IsIsolated;
	}

	public void setIsIsolated(boolean isIsolated) {
		IsIsolated = isIsolated;
	}

	public boolean hasControl() {
		return HasControl;
	}

	public void setHasControl(boolean hasControl) {
		HasControl = hasControl;
	}

	public boolean isPartofFeeder() {
		return IsPartofFeeder;
	}

	public void setIsPartofFeeder(boolean isPartofFeeder) {
		IsPartofFeeder = isPartofFeeder;
	}

	public DSSCktElement getControlElement() {
		return ControlElement;
	}

	public void setControlElement(DSSCktElement controlElement) {
		ControlElement = controlElement;
	}

	public Complex[] getIterminal() {
		return Iterminal;
	}

	public void setIterminal(Complex[] iterminal) {
		Iterminal = iterminal;
	}

	public Complex[] getVterminal() {
		return Vterminal;
	}

	public void setVterminal(Complex[] vterminal) {
		Vterminal = vterminal;
	}

	public double getBaseFrequency() {
		return BaseFrequency;
	}

	public void setBaseFrequency(double baseFrequency) {
		BaseFrequency = baseFrequency;
	}

	public PowerTerminal[] getTerminals() {
		return Terminals;
	}

	public void setTerminals(PowerTerminal[] terminals) {
		Terminals = terminals;
	}

	public void setActiveTerminal(PowerTerminal activeTerminal) {
		ActiveTerminal = activeTerminal;
	}

	public PowerTerminal getActiveTerminal() {
		return ActiveTerminal;
	}

	public void setYprimFreq(double Value) {
		setFreq(Value);
	}

	public double getYprimFreq() {
		return this.YprimFreq;
	}

	public int getHandle() {
		return 0;
	}

}

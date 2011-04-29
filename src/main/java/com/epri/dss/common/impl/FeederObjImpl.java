package com.epri.dss.common.impl;

import java.io.PrintStream;
import java.util.ArrayList;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.Feeder;
import com.epri.dss.common.FeederObj;
import com.epri.dss.conversion.impl.PCElementImpl;
import com.epri.dss.shared.CktTree;

public class FeederObjImpl extends PCElementImpl implements FeederObj {

	private ArrayList<CktElement> SequenceList;
	private ArrayList<CktElement> ShuntList;

	private CktElement RootElement;
	private int FromTerminalOffset;

	protected boolean IsSynched;

	public FeederObjImpl(DSSClassImpl ParClass, String MeterName) {
		super(ParClass);

		setName(MeterName.toLowerCase());
		DSSObjType = ParClass.getDSSClassType();  // This will be a current source (PCElement)

		SequenceList = new ArrayList<CktElement>(50);
		ShuntList = new ArrayList<CktElement>(50);

		IsSynched = false;

		// Bus names and nPhases, etc are set up from EnergyMeter

		recalcElementData();
		initPropertyValues(0);
	}

	public void initializeFeeder(CktTree BranchList) {
		int bref;
		CktElement pElement, pShunt;

		SequenceList.clear();  // Get rid of any previous definitions
		ShuntList.clear();

		IsSynched = false;
		// Now set up Feeder terminals and BusRef to match the from node of the first branch
		if (BranchList != null) {
			RootElement = (CktElement) BranchList.getFirst();

			nPhases = RootElement.getNPhases(); // Take care of allocating Terminal stuff
			nConds = RootElement.getNConds();
			nTerms = 1;
			Yorder = nTerms * nConds;

			Terminals[0].setBusRef(BranchList.getPresentBranch().getFromBusReference());  // TODO Check zero based indexing
			setBus(0, RootElement.getBus(BranchList.getPresentBranch().getFromTerminal()));  // set bus name same as first element
			FromTerminalOffset = (BranchList.getPresentBranch().getFromTerminal() - 1) * nConds;
			setNodeRef(0, RootElement.getNodeRef()[1 + FromTerminalOffset]);  // TODO Check zero based indexing

			// Build The Sequence List  and ShuntList
			pElement = RootElement;
			while (pElement != null) {
				SequenceList.add(pElement);

				// Mark all the To buses for this branch as radial buses
				BranchList.getPresentBranch().resetToBusList();  // reset pointer to first to bus
				for (int i = 0; i < pElement.getNTerms() - 1; i++) {
					bref = BranchList.getPresentBranch().getToBusReference();  // each call pops off a new one
					if (bref > 0)
						DSSGlobals.getInstance().getActiveCircuit().getBuses()[bref].setIsRadialBus(true);
				}

				pShunt = (CktElement) BranchList.getPresentBranch().getFirstObject();
				while (pShunt != null) {
					ShuntList.add(pShunt);
					pShunt = (CktElement) BranchList.getPresentBranch().getNextObject();
				}
				pElement = (CktElement) BranchList.GoForward();
			}

			IsSynched = true;

			setCktElementFeederFlags(true);
		}
	}

	@Override
	public void recalcElementData() {

	}

	@Override
	public void calcYPrim() {
		// For now, YPrim is null

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

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYprimInvalid(false);
	}

	/**
	 * Total currents into a feeder which are equal to the currents into the first element.
	 * Return the currents in the from terminal of the first element in the sequence list.
	 */
	@Override
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < Yorder; i++)
			Curr[i] = Complex.ZERO;  // no contribution if not radial solution
	}

	/**
	 * Fill Up an array of injection currents.
	 *
	 * Only thing this is used for is for getCurrents(). Ignore for Feeder.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);
		// Do not dump any properties for a Feeder unless debug.

		if (Complete) {
			/* Dump sequence lists, etc here... */
			F.println();
			F.println();
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		super.initPropertyValues(FeederImpl.getNumPropsThisClass());
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		// do nothing
	}

	public void setCktElementFeederFlags(boolean Value) {
		for (int i = 0; i < ShuntList.size(); i++)
			ShuntList.get(i).setIsPartofFeeder(Value);

		for (int i = 0; i < SequenceList.size(); i++)
			SequenceList.get(i).setIsPartofFeeder(Value);
	}

	public boolean isSynched() {
		return IsSynched;
	}

	public void setIsSynched(boolean isSynched) {
		IsSynched = isSynched;
	}
}

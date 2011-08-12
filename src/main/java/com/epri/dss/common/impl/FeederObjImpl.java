package com.epri.dss.common.impl;

import java.io.PrintStream;
import java.util.ArrayList;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.FeederObj;
import com.epri.dss.conversion.impl.PCElementImpl;
import com.epri.dss.shared.CktTree;

public class FeederObjImpl extends PCElementImpl implements FeederObj {

	private ArrayList<CktElement> sequenceList;
	private ArrayList<CktElement> shuntList;

	private CktElement rootElement;
	private int fromTerminalOffset;

	protected boolean isSynched;

	public FeederObjImpl(DSSClassImpl ParClass, String MeterName) {
		super(ParClass);

		setName(MeterName.toLowerCase());
		DSSObjType = ParClass.getDSSClassType();  // this will be a current source (PCElement)

		sequenceList = new ArrayList<CktElement>(50);
		shuntList = new ArrayList<CktElement>(50);

		isSynched = false;

		// bus names and nPhases, etc are set up from EnergyMeter

		recalcElementData();
		initPropertyValues(0);
	}

	public void initializeFeeder(CktTree BranchList) {
		int bref;
		CktElement pElement, pShunt;

		sequenceList.clear();  // get rid of any previous definitions
		shuntList.clear();

		isSynched = false;
		// set up feeder terminals and busRef to match the from node of the first branch
		if (BranchList != null) {
			rootElement = (CktElement) BranchList.getFirst();

			setNPhases( rootElement.getNPhases() );  // take care of allocating terminal stuff
			setNConds( rootElement.getNConds() );
			setNTerms(1);
			YOrder = nTerms * nConds;

			terminals[0].setBusRef(BranchList.getPresentBranch().getFromBusReference());  // TODO Check zero based indexing
			setBus(0, rootElement.getBus(BranchList.getPresentBranch().getFromTerminal()));  // set bus name same as first element
			fromTerminalOffset = (BranchList.getPresentBranch().getFromTerminal() - 1) * nConds;
			setNodeRef(0, rootElement.getNodeRef()[1 + fromTerminalOffset]);  // TODO Check zero based indexing

			// build the sequence list and shunt list
			pElement = rootElement;
			while (pElement != null) {
				sequenceList.add(pElement);

				// mark all the to buses for this branch as radial buses
				BranchList.getPresentBranch().resetToBusList();  // reset pointer to first to bus
				for (int i = 0; i < pElement.getNTerms() - 1; i++) {
					bref = BranchList.getPresentBranch().getToBusReference();  // each call pops off a new one
					if (bref > 0)
						DSSGlobals.getInstance().getActiveCircuit().getBuses()[bref].setRadialBus(true);
				}

				pShunt = (CktElement) BranchList.getPresentBranch().getFirstObject();
				while (pShunt != null) {
					shuntList.add(pShunt);
					pShunt = (CktElement) BranchList.getPresentBranch().getNextObject();
				}
				pElement = (CktElement) BranchList.GoForward();
			}

			isSynched = true;

			setCktElementFeederFlags(true);
		}
	}

	@Override
	public void recalcElementData() {

	}

	@Override
	public void calcYPrim() {
		// for now, YPrim is null

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

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	/**
	 * Total currents into a feeder which are equal to the currents into the first element.
	 * Return the currents in the from terminal of the first element in the sequence list.
	 */
	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < YOrder; i++)
			curr[i] = Complex.ZERO;  // no contribution if not radial solution
	}

	/**
	 * Fill Up an array of injection currents.
	 *
	 * Only thing this is used for is for getCurrents(). Ignore for Feeder.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);
		// Do not dump any properties for a Feeder unless debug.

		if (complete) {
			/* Dump sequence lists, etc here... */
			f.println();
			f.println();
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		super.initPropertyValues(FeederImpl.getNumPropsThisClass());
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		// do nothing
	}

	public void setCktElementFeederFlags(boolean value) {
		for (int i = 0; i < shuntList.size(); i++)
			shuntList.get(i).setPartofFeeder(value);

		for (int i = 0; i < sequenceList.size(); i++)
			sequenceList.get(i).setPartofFeeder(value);
	}

	public boolean isSynched() {
		return isSynched;
	}

	public void setSynched(boolean synched) {
		isSynched = synched;
	}
}

package com.ncond.dss.common.impl;

import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.FeederObj;
import com.ncond.dss.conversion.impl.PCElementImpl;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.impl.CMatrixImpl;

public class FeederObjImpl extends PCElementImpl implements FeederObj {

	private ArrayList<CktElement> sequenceList;
	private ArrayList<CktElement> shuntList;

	private CktElement rootElement;
	private int fromTerminalOffset;

	protected boolean isSynched;

	public FeederObjImpl(DSSClassImpl ParClass, String MeterName) {
		super(ParClass);

		setName(MeterName.toLowerCase());
		objType = ParClass.getDSSClassType();  // this will be a current source (PCElement)

		sequenceList = new ArrayList<CktElement>(50);
		shuntList = new ArrayList<CktElement>(50);

		isSynched = false;

		// bus names and nPhases, etc are set up from EnergyMeter

		recalcElementData();
		initPropertyValues(0);
	}

	public void initializeFeeder(CktTree branchList) {
		int bref;
		CktElement pElement, pShunt;

		sequenceList.clear();  // get rid of any previous definitions
		shuntList.clear();

		isSynched = false;
		// set up feeder terminals and busRef to match the from node of the first branch
		if (branchList != null) {
			rootElement = (CktElement) branchList.getFirst();

			setNPhases( rootElement.getNPhases() );  // take care of allocating terminal stuff
			setNConds( rootElement.getNConds() );
			setNTerms(1);
			YOrder = nTerms * nConds;

			terminals[0].setBusRef( branchList.getPresentBranch().getFromBusReference() );
			setBus(0, rootElement.getBus(branchList.getPresentBranch().getFromTerminal()));  // set bus name same as first element
			fromTerminalOffset = branchList.getPresentBranch().getFromTerminal() * nConds;
			setNodeRef(0, rootElement.getNodeRef()[ fromTerminalOffset ]);

			// build the sequence list and shunt list
			pElement = rootElement;
			while (pElement != null) {
				sequenceList.add(pElement);

				// mark all the to buses for this branch as radial buses
				branchList.getPresentBranch().resetToBusList();  // reset pointer to first to bus
				for (int i = 0; i < pElement.getNTerms() - 1; i++) {
					bref = branchList.getPresentBranch().getToBusReference();  // each call pops off a new one
					if (bref > 0)
						DSSGlobals.activeCircuit.getBus(bref).setRadialBus(true);
				}

				pShunt = (CktElement) branchList.getPresentBranch().getFirstObject();
				while (pShunt != null) {
					shuntList.add(pShunt);
					pShunt = (CktElement) branchList.getPresentBranch().getNextObject();
				}
				pElement = (CktElement) branchList.goForward();
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
	 * Fill up an array of injection currents.
	 *
	 * Only thing this is used for is for getCurrents(). Ignore for Feeder.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {

	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);
		// do not dump any properties for a Feeder unless debug

		if (complete) {
			/* Dump sequence lists, etc here... */
			f.println();
			f.println();
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		super.initPropertyValues(FeederImpl.NumPropsThisClass);
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

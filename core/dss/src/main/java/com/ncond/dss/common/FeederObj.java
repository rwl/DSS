package com.ncond.dss.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CktTree;

/**
 * Feeders get created from energy meters if radial is set to yes and meter
 * zones are already computed.  If radial=yes and the MeterZones are reset,
 * then the feeders are redefined.  If radial is subsequently set to no or a
 * solution mode is used that doesn't utilize feeders, the get currents
 * routines will not do anything.
 *
 * Feeders cannot be re-enabled unless the EnergyMeter object allows them
 * to be.
 *
 * Feeders are not saved. This is implicit with the EnergyMeter saving.
 */
@Getter @Setter
public class FeederObj extends PCElement {

	private List<CktElement> sequenceList;
	private List<CktElement> shuntList;

	private CktElement rootElement;
	private int fromTerminalOffset;

	protected boolean isSynched;

	public FeederObj(DSSClass parClass, String meterName) {
		super(parClass);

		setName(meterName.toLowerCase());
		objType = parClass.getClassType();  // this will be a current source (PCElement)

		sequenceList = new ArrayList<CktElement>(50);
		shuntList = new ArrayList<CktElement>(50);

		isSynched = false;

		// bus names and nPhases, etc are set up from EnergyMeter

		recalcElementData();
		initPropertyValues(0);
	}

	public void initializeFeeder(CktTree branchList) {
		int bref;
		CktElement elem, shunt;

		sequenceList.clear();  // get rid of any previous definitions
		shuntList.clear();

		isSynched = false;
		// set up feeder terminals and busRef to match the from node of the first branch
		if (branchList != null) {
			rootElement = (CktElement) branchList.getFirst();

			setNumPhases( rootElement.getNumPhases() );  // take care of allocating terminal stuff
			setNumConds( rootElement.getNumConds() );
			setNumTerms(1);
			YOrder = nTerms * nConds;

			terminals[0].setBusRef( branchList.getPresentBranch().getFromBusReference() );
			setBus(0, rootElement.getBus(branchList.getPresentBranch().getFromTerminalIdx()));  // set bus name same as first element
			fromTerminalOffset = branchList.getPresentBranch().getFromTerminalIdx() + 1 * nConds;
			setNodeRef(0, rootElement.getNodeRef(fromTerminalOffset));

			// build the sequence list and shunt list
			elem = rootElement;
			while (elem != null) {
				sequenceList.add(elem);

				// mark all the to buses for this branch as radial buses
				branchList.getPresentBranch().resetToBusList();  // reset pointer to first to bus
				for (int i = 0; i < elem.getNumTerms() - 1; i++) {
					bref = branchList.getPresentBranch().getToBusReference();  // each call pops off a new one
					if (bref > 0)
						DSS.activeCircuit.getBus(bref - 1).setRadialBus(true);
				}

				shunt = (CktElement) branchList.getPresentBranch().getFirstObject();
				while (shunt != null) {
					shuntList.add(shunt);
					shunt = (CktElement) branchList.getPresentBranch().getNextObject();
				}
				elem = (CktElement) branchList.goForward();
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
		// build only YPrimSeries
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
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
	 *
	 * @return currents in the from terminal of the first element in the sequence list.
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
		super.initPropertyValues(Feeder.NumPropsThisClass - 1);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		// do nothing
	}

	public void setCktElementFeederFlags(boolean value) {
		for (CktElement shunt : shuntList) shunt.setPartOfFeeder(value);
		for (CktElement seq : sequenceList) seq.setPartOfFeeder(value);
	}

}

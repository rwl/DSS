/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.conversion.GeneratorObj;

import static com.ncond.dss.common.Util.getCktElementIndex;
import static com.ncond.dss.common.Util.resizeArray;

import static java.lang.Math.max;
import static java.lang.Math.abs;


/**
 * A control element that is connected to a terminal of
 * another circuit element and sends dispatch kW signals to a set of generators
 * it controls.
 *
 *   new genDispatcher.name=myName element=devClass.name terminal=[ 1|2|...] capacitorList=(gen1 gen2 ...)
 */
public class GenDispatcherObj extends ControlElem {

	private double kWLimit, kWBand, halfKWBand, kVArLimit, totalWeight;
	private int listSize;
	private List<String> generatorNames;
	private List<GeneratorObj> generators;
	private double[] weights;

	private CktElement monitoredElement;

	public GenDispatcherObj(DSSClass parClass, String genDispatcherName) {
		super(parClass);

		setName(genDispatcherName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);  // not used in this control
		elementTerminalIdx = 0;
		monitoredElement = null;

		generatorNames = new ArrayList<String>();
		weights = null;
		generators = new ArrayList<GeneratorObj>(20);  // default size and increment
		listSize = 0;
		kWLimit = 8000.0;
		kWBand = 100.0;
		totalWeight = 1.0;
		halfKWBand = kWBand / 2.0;
		initPropertyValues(0);
		kVArLimit = kWLimit / 2.0;

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		/* Check for existence of monitored element */
		int devIndex = getCktElementIndex(elementName);

		if (devIndex >= 0) {
			monitoredElement = DSS.activeCircuit.getCktElements().get(devIndex);
			if (elementTerminalIdx >= monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("GenDispatcher: \"" + getName() + "\"",
						"Terminal no. \"" + (elementTerminalIdx+1) +
						"\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in GenDispatcher's buslist
				setBus(0, monitoredElement.getBus(elementTerminalIdx));
			}
		} else {
			DSS.doSimpleMsg("Monitored element in GenDispatcher." + getName() +
					" does not exist: \"" + elementName + "\"", 372);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (monitoredElement != null) {
			setNumPhases(getControlledElement().getNumPhases());
			setNumConds(nPhases);
			setBus(0, monitoredElement.getBus(elementTerminalIdx));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) pw.println();

		pw.close();
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int code, int proxyHdl) {
		// do nothing
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		int i;
		double Pdiff, Qdiff;
		Complex S;
		GeneratorObj gen;
		boolean genKWChanged, genKVArChanged;
		double genKW, genKVAr;

		// if list is not define, go make one from all generators in circuit
		if (generators.size() == 0) makeGenList();

		if (listSize > 0) {
			//monitoredElement.activeTerminalIdx = elementTerminalIdx;
			S = monitoredElement.getPower(elementTerminalIdx);  // power in active terminal

			Pdiff = S.getReal() * 0.001 - kWLimit;
			Qdiff = S.getImaginary() * 0.001 - kVArLimit;

			// redispatch the vars

			genKWChanged = false;
			genKVArChanged = false;

			if (abs(Pdiff) > halfKWBand) {  // redispatch generators
				// Pdiff is kW needed to get back into band
				for (i = 0; i < listSize; i++) {
					gen = generators.get(i);
					// compute new dispatch value for this generator ...
					genKW = max(1.0, (gen.getKWBase() + Pdiff * (weights[i] / totalWeight)));
					if (genKW != gen.getKWBase()) {
						gen.setKWBase(genKW);
						genKWChanged = true;
					}
				}
			}

			if (abs(Qdiff) > halfKWBand) {  // redispatch generators
				// QDiff is kVAr needed to get back into band
				for (i = 0; i < listSize; i++) {
					gen = generators.get(i);
					// compute new dispatch value for this generator ...
					genKVAr = max(0.0, (gen.getKVArBase() + Qdiff * (weights[i] / totalWeight)));
					if (genKVAr != gen.getKVArBase()) {
						gen.setKVArBase(genKVAr);
						genKVArChanged = true;
					}
				}
			}

			if (genKWChanged || genKVArChanged) {  // only push onto control queue if there has been a change
				Circuit ckt = DSS.activeCircuit;
				SolutionObj sol = ckt.getSolution();

				sol.setLoadsNeedUpdating(true);  // force recalc of power parms

				// push present time onto control queue to force re solve at new dispatch value
				ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, this);
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");  // "element";
		setPropertyValue(1, "1");  // "terminal";
		setPropertyValue(2, "8000");
		setPropertyValue(3, "100");
		setPropertyValue(4, "0");
		setPropertyValue(5, "");
		setPropertyValue(6, "");

		super.initPropertyValues(GenDispatcher.NumPropsThisClass - 1);
	}

	public boolean makeGenList() {
		GeneratorObj gen;
		int i;

		boolean made = false;
		DSSClass genClass = DSSClassDefs.getDSSClass("generator");

		if (listSize > 0) {  // name list is defined - use it
			for (i = 0; i < listSize; i++) {
				gen = (GeneratorObj) genClass.find(generatorNames.get(i));
				if ((gen != null) && gen.isEnabled()) generators.add(gen);
			}
		} else {
			/* Search through the entire circuit for enabled generators
			 * and add them to the list */

			for (i = 0; i < genClass.getElementCount(); i++) {
				gen = (GeneratorObj) genClass.getElementList().get(i);
				if (gen.isEnabled()) generators.add(gen);
			}

			/* Allocate uniform weights */
			listSize = generators.size();
			weights = resizeArray(weights, listSize);
			for (i = 0; i < listSize; i++) weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < listSize; i++)
			totalWeight = totalWeight + weights[i];

		if (generators.size() > 0) made = true;

		return made;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		//super.reset();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public double getKWBand() {
		return kWBand;
	}

	public void setKWBand(double kWBand) {
		this.kWBand = kWBand;
	}

	public int getListSize() {
		return listSize;
	}

	public List<String> getGeneratorNames() {
		return generatorNames;
	}

	public List<GeneratorObj> getGenerators() {
		return generators;
	}

	public double[] getWeights() {
		return weights;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setKWLimit(double kWLimit) {
		this.kWLimit = kWLimit;
	}

	public void setKVArLimit(double kVArLimit) {
		this.kVArLimit = kVArLimit;
	}

	public void setHalfKWBand(double halfKWBand) {
		this.halfKWBand = halfKWBand;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		this.monitoredElement = monitoredElement;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

}

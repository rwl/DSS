package com.ncond.dss.control.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSSClassImpl;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.control.GenDispatcher;
import com.ncond.dss.control.GenDispatcherObj;
import com.ncond.dss.conversion.GeneratorObj;

public class GenDispatcherObjImpl extends ControlElemImpl implements GenDispatcherObj {

	private double kWLimit,
		kWBand,
		halfKWBand,
		kVArLimit,
		totalWeight;
	private int listSize;
	private List<String> generatorNameList;
	private List<GeneratorObj> genPointerList;
	private double[] weights;

	private CktElement monitoredElement;

	public GenDispatcherObjImpl(DSSClassImpl parClass, String genDispatcherName) {
		super(parClass);

		setName(genDispatcherName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class


		elementName   = "";
		setControlledElement(null);  // not used in this control
		elementTerminal  = 1;
		monitoredElement = null;

		generatorNameList = new ArrayList<String>();
		weights   = null;
		genPointerList = new ArrayList<GeneratorObj>(20);  // default size and increment
		listSize    = 0;
		kWLimit     = 8000.0;
		kWBand      = 100.0;
		totalWeight = 1.0;
		halfKWBand  = kWBand / 2.0;
		initPropertyValues(0);
		kVArLimit   = kWLimit / 2.0;

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {

		/* Check for existence of monitored element */

		int devIndex = Util.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = DSS.activeCircuit.getCktElements().get(devIndex);
			if (elementTerminal > monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("GenDispatcher: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in GenDispatcher's buslist
				setBus(0, monitoredElement.getBus(elementTerminal));
			}
		} else {
			DSS.doSimpleMsg("Monitored Element in GenDispatcher."+getName()+" does not exist:\""+elementName+"\"", 372);
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
			setBus(0, monitoredElement.getBus(elementTerminal));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
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
		/* Do nothing */
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		int i;
		double PDiff, QDiff;
		Complex S;
		GeneratorObj gen;
		boolean genKWChanged, genKVArChanged;
		double genKW, genKVAr;

		// if list is not define, go make one from all generators in circuit
		if (genPointerList.size() == 0)
			makeGenList();

		if (listSize > 0) {

			//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
			S = monitoredElement.getPower(elementTerminal);  // power in active terminal

			PDiff = S.getReal() * 0.001 - kWLimit;

			QDiff = S.getImaginary() * 0.001 - kVArLimit;

			// redispatch the vars

			genKWChanged = false;
			genKVArChanged = false;

			if (Math.abs(PDiff) > halfKWBand) {  // redispatch generators
				// PDiff is kW needed to get back into band
				for (i = 0; i < listSize; i++) {
					gen = genPointerList.get(i);
					// compute new dispatch value for this generator ...
					genKW = Math.max(1.0, (gen.getKWBase() + PDiff * (weights[i] / totalWeight)));
					if (genKW != gen.getKWBase()) {
						gen.setKWBase(genKW);
						genKWChanged = true;
					}
				}
			}

			if (Math.abs(QDiff) > halfKWBand) {  // redispatch generators
				// QDiff is kVAr needed to get back into band
				for (i = 0; i < listSize; i++) {
					gen = genPointerList.get(i);
					// compute new dispatch value for this generator ...
					genKVAr = Math.max(0.0, (gen.getKVArBase() + QDiff * (weights[i] / totalWeight)));
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

	@Override
	public boolean makeGenList() {
		GeneratorObj gen;
		int i;

		boolean result = false;
		DSSClass genClass = DSSClassDefs.getDSSClass("generator");

		if (listSize > 0) {  // name list is defined - use it

			for (i = 0; i < listSize; i++) {
				gen = (GeneratorObj) genClass.find(generatorNameList.get(i - 1));
				if ((gen != null) && gen.isEnabled())
					genPointerList.add(gen);
			}

		} else {
			/* Search through the entire circuit for enabled generators and add them to the list */

			for (i = 0; i < genClass.getElementCount(); i++) {
				gen = (GeneratorObj) genClass.getElementList().get(i);
				if (gen.isEnabled())
					genPointerList.add(gen);
			}

			/* Allocate uniform weights */
			listSize = genPointerList.size();
			weights = Util.resizeArray(weights, listSize);
			for (i = 0; i < listSize; i++)
				weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < listSize; i++)
			totalWeight = totalWeight + weights[i];

		if (genPointerList.size() > 0)
			result = true;

		return result;
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

	// FIXME Private members in OpenDSS

	@Override
	public double getKWLimit() {
		return kWLimit;
	}

	@Override
	public void setKWLimit(double limit) {
		this.kWLimit = limit;
	}

	@Override
	public double getKWBand() {
		return kWBand;
	}

	@Override
	public void setKWBand(double band) {
		this.kWBand = band;
	}

	@Override
	public double getHalfKWBand() {
		return halfKWBand;
	}

	@Override
	public void setHalfKWBand(double band) {
		halfKWBand = band;
	}

	@Override
	public double getKVArLimit() {
		return kVArLimit;
	}

	@Override
	public void setKVArLimit(double limit) {
		kVArLimit = limit;
	}

	@Override
	public double getTotalWeight() {
		return totalWeight;
	}

	@Override
	public void setTotalWeight(double total) {
		totalWeight = total;
	}

	@Override
	public int getListSize() {
		return listSize;
	}

	@Override
	public void setListSize(int size) {
		listSize = size;
	}

	@Override
	public List<String> getGeneratorNameList() {
		return generatorNameList;
	}

	@Override
	public void setGeneratorNameList(List<String> list) {
		generatorNameList = list;
	}

	@Override
	public List<GeneratorObj> getGenPointerList() {
		return genPointerList;
	}

	@Override
	public void setGenPointerList(List<GeneratorObj> list) {
		genPointerList = list;
	}

	@Override
	public double[] getWeights() {
		return weights;
	}

	@Override
	public void setWeights(double[] values) {
		weights = values;
	}

	@Override
	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	@Override
	public void setMonitoredElement(CktElement element) {
		monitoredElement = element;
	}

}

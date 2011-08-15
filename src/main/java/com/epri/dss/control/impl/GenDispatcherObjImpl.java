package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.GenDispatcher;
import com.epri.dss.control.GenDispatcherObj;
import com.epri.dss.conversion.GeneratorObj;

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

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors in base class


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
		DSSGlobals globals = DSSGlobals.getInstance();

		/* Check for existence of monitored element */

		int devIndex = Utilities.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = globals.getActiveCircuit().getCktElements().get(devIndex);
			if (elementTerminal > monitoredElement.getNTerms()) {
				globals.doErrorMsg("GenDispatcher: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in GenDispatcher's buslist
				setBus(1, monitoredElement.getBus(elementTerminal));
			}
		} else {
			globals.doSimpleMsg("Monitored Element in GenDispatcher."+getName()+" does not exist:\""+elementName+"\"", 372);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (monitoredElement != null) {
			setNPhases(getControlledElement().getNPhases());
			setNConds(nPhases);
			setBus(1, monitoredElement.getBus(elementTerminal));
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
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete)
			f.println();
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
				// QDiff is kvar needed to get back into band
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
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				SolutionObj sol = ckt.getSolution();

				sol.setLoadsNeedUpdating(true);  // force recalc of power parms
				// push present time onto control queue to force re solve at new dispatch value
				ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, this);
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[0] = "";  // "element";
		propertyValue[1] = "1";  // "terminal";
		propertyValue[2] = "8000";
		propertyValue[3] = "100";
		propertyValue[4] = "0";
		propertyValue[5] = "";
		propertyValue[6] = "";

		super.initPropertyValues(GenDispatcher.NumPropsThisClass);	// TODO Check zero based indexing
	}

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
			weights = (double[]) Utilities.resizeArray(weights, listSize);
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

	// FIXME Private members in OpenDSS

	public double getKWLimit() {
		return kWLimit;
	}

	public void setKWLimit(double limit) {
		this.kWLimit = limit;
	}

	public double getKWBand() {
		return kWBand;
	}

	public void setKWBand(double band) {
		this.kWBand = band;
	}

	public double getHalfKWBand() {
		return halfKWBand;
	}

	public void setHalfKWBand(double band) {
		halfKWBand = band;
	}

	public double getKVArLimit() {
		return kVArLimit;
	}

	public void setKVArLimit(double limit) {
		kVArLimit = limit;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double total) {
		totalWeight = total;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int size) {
		listSize = size;
	}

	public List<String> getGeneratorNameList() {
		return generatorNameList;
	}

	public void setGeneratorNameList(List<String> list) {
		generatorNameList = list;
	}

	public List<GeneratorObj> getGenPointerList() {
		return genPointerList;
	}

	public void setGenPointerList(List<GeneratorObj> list) {
		genPointerList = list;
	}

	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] values) {
		weights = values;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setMonitoredElement(CktElement element) {
		monitoredElement = element;
	}

}

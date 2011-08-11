package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.epri.dss.shared.impl.Complex;

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
		HalfkWBand,
		kvarLimit,
		TotalWeight;
	private int ListSize;
	private List<String> GeneratorNameList;
	private List<GeneratorObj> GenPointerList;
	private double[] Weights;

	private CktElement MonitoredElement;

	public GenDispatcherObjImpl(DSSClassImpl ParClass, String GenDispatcherName) {
		super(ParClass);

		setName(GenDispatcherName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors in base class


		this.ElementName   = "";
		setControlledElement(null);  // not used in this control
		this.ElementTerminal  = 1;
		this.MonitoredElement = null;

		this.GeneratorNameList = new ArrayList<String>();
		this.Weights   = null;
		this.GenPointerList = new ArrayList<GeneratorObj>(20);  // default size and increment
		this.ListSize    = 0;
		this.kWLimit     = 8000.0;
		this.kWBand      = 100.0;
		this.TotalWeight = 1.0;
		this.HalfkWBand  = this.kWBand / 2.0;
		initPropertyValues(0);
		this.kvarLimit   = this.kWLimit / 2.0;

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Check for existence of monitored element */

		int DevIndex = Utilities.getCktElementIndex(ElementName);
		if (DevIndex >= 0) {
			MonitoredElement = Globals.getActiveCircuit().getCktElements().get(DevIndex);
			if (ElementTerminal > MonitoredElement.getNTerms()) {
				Globals.doErrorMsg("GenDispatcher: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in GenDispatcher's buslist
				setBus(1, MonitoredElement.getBus(ElementTerminal));
			}
		} else {
			Globals.doSimpleMsg("Monitored Element in GenDispatcher."+getName()+" does not exist:\""+ElementName+"\"", 372);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (MonitoredElement != null) {
			setNPhases(getControlledElement().getNPhases());
			setNConds(nPhases);
			setBus(1, MonitoredElement.getBus(ElementTerminal));
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		// leave YPrims as null and they will be ignored
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete)
			F.println();
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {
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
		GeneratorObj Gen;
		boolean GenkWChanged, Genkvarchanged;
		double GenkW, Genkvar;

		// if list is not define, go make one from all generators in circuit
		if (GenPointerList.size() == 0)
			makeGenList();

		if (ListSize > 0) {

			//MonitoredElement.ActiveTerminalIdx = ElementTerminal;
			S = MonitoredElement.getPower(ElementTerminal);  // power in active terminal

			PDiff = S.getReal() * 0.001 - kWLimit;

			QDiff = S.getImaginary() * 0.001 - kvarLimit;

			// redispatch the vars

			GenkWChanged = false;
			Genkvarchanged = false;

			if (Math.abs(PDiff) > HalfkWBand) {  // redispatch generators
				// PDiff is kW needed to get back into band
				for (i = 0; i < ListSize; i++) {
					Gen = GenPointerList.get(i);
					// compute new dispatch value for this generator ...
					GenkW = Math.max(1.0, (Gen.getkWBase() + PDiff * (Weights[i] / TotalWeight)));
					if (GenkW != Gen.getkWBase()) {
						Gen.setkWBase(GenkW);
						GenkWChanged = true;
					}
				}
			}

			if (Math.abs(QDiff) > HalfkWBand) {  // redispatch generators
				// QDiff is kvar needed to get back into band
				for (i = 0; i < ListSize; i++) {
					Gen = GenPointerList.get(i);
					// compute new dispatch value for this generator ...
					Genkvar = Math.max(0.0, (Gen.getKvarBase() + QDiff * (Weights[i] / TotalWeight)));
					if (Genkvar != Gen.getKvarBase()) {
						Gen.setKvarBase(Genkvar);
						Genkvarchanged = true;
					}
				}
			}

			if (GenkWChanged || Genkvarchanged) {  // only push onto control queue if there has been a change
				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
				SolutionObj sol = ckt.getSolution();

				sol.setLoadsNeedUpdating(true);  // force recalc of power parms
				// push present time onto control queue to force re solve at new dispatch value
				ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t, 0, 0, this);
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "";  // "element";
		PropertyValue[1] = "1";  // "terminal";
		PropertyValue[2] = "8000";
		PropertyValue[3] = "100";
		PropertyValue[4] = "0";
		PropertyValue[5] = "";
		PropertyValue[6] = "";

		super.initPropertyValues(GenDispatcher.NumPropsThisClass);	// TODO Check zero based indexing
	}

	public boolean makeGenList() {
		GeneratorObj Gen;
		int i;

		boolean Result = false;
		DSSClass GenClass = DSSClassDefs.getDSSClass("generator");

		if (ListSize > 0) {  // name list is defined - use it

			for (i = 0; i < ListSize; i++) {
				Gen = (GeneratorObj) GenClass.find(GeneratorNameList.get(i - 1));
				if ((Gen != null) && Gen.isEnabled())
					GenPointerList.add(Gen);
			}

		} else {
			/* Search through the entire circuit for enabled generators and add them to the list */

			for (i = 0; i < GenClass.getElementCount(); i++) {
				Gen = (GeneratorObj) GenClass.getElementList().get(i);
				if (Gen.isEnabled())
					GenPointerList.add(Gen);
			}

			/* Allocate uniform weights */
			ListSize = GenPointerList.size();
			Weights = (double[]) Utilities.resizeArray(Weights, ListSize);
			for (i = 0; i < ListSize; i++)
				Weights[i] = 1.0;
		}

		// add up total weights
		TotalWeight = 0.0;
		for (i = 0; i < ListSize; i++)
			TotalWeight = TotalWeight + Weights[i];

		if (GenPointerList.size() > 0)
			Result = true;

		return Result;
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		//super.reset();
	}

	// FIXME Private members in OpenDSS

	public double getkWLimit() {
		return kWLimit;
	}

	public void setkWLimit(double kWLimit) {
		this.kWLimit = kWLimit;
	}

	public double getkWBand() {
		return kWBand;
	}

	public void setkWBand(double kWBand) {
		this.kWBand = kWBand;
	}

	public double getHalfkWBand() {
		return HalfkWBand;
	}

	public void setHalfkWBand(double halfkWBand) {
		HalfkWBand = halfkWBand;
	}

	public double getKvarLimit() {
		return kvarLimit;
	}

	public void setKvarLimit(double fkvarLimit) {
		kvarLimit = fkvarLimit;
	}

	public double getTotalWeight() {
		return TotalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		TotalWeight = totalWeight;
	}

	public int getListSize() {
		return ListSize;
	}

	public void setListSize(int listSize) {
		ListSize = listSize;
	}

	public List<String> getGeneratorNameList() {
		return GeneratorNameList;
	}

	public void setGeneratorNameList(List<String> generatorNameList) {
		GeneratorNameList = generatorNameList;
	}

	public List<GeneratorObj> getGenPointerList() {
		return GenPointerList;
	}

	public void setGenPointerList(List<GeneratorObj> genPointerList) {
		GenPointerList = genPointerList;
	}

	public double[] getWeights() {
		return Weights;
	}

	public void setWeights(double[] weights) {
		Weights = weights;
	}

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

}

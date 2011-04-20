package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.GenDispatcherObj;

public class GenDispatcherObjImpl extends ControlElemImpl implements GenDispatcherObj {
	
	private double kWLimit,
		kWBand,
		HalfkWBand,
		kvarLimit,
		TotalWeight;
	private int ListSize;
	private String[] GeneratorNameList;
	private Object[] GenPointerList;
	private double[] Weights;

	private CktElement MonitoredElement;

	public GenDispatcherObjImpl(DSSClassImpl ParClass, String GenDispatcherName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}

	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {
		
	}
	
	/* Do the action that is pending from last sample */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {
		
	}
	
	/* Reset to initial defined state */
	@Override
	public void reset() {
		
	}
	
	@Override
	public void getInjCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public void getCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	public boolean makeGenList() {
		return false;
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

	public String[] getGeneratorNameList() {
		return GeneratorNameList;
	}

	public void setGeneratorNameList(String[] generatorNameList) {
		GeneratorNameList = generatorNameList;
	}

	public Object[] getGenPointerList() {
		return GenPointerList;
	}

	public void setGenPointerList(Object[] genPointerList) {
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

package com.epri.dss.common.impl;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Bus;
import com.epri.dss.general.impl.NamedObjectImpl;
import com.epri.dss.shared.CMatrix;

public class DSSBus extends NamedObjectImpl implements Bus {

	public class NodeBus {
		/* Ref to Bus in circuit's BusList */
		public int BusRef;
		public int NodeNum;
	}

	private int NumNodesThisBus;
	private int[] Nodes;
	private int Allocation;
	private int[] RefNo;

	protected Complex[] VBus, BusCurrent;
	protected CMatrix Zsc, Ysc;

	/* Coordinates */
	protected double x, y;
	protected double kVBase;
	/* Base kV for each node to ground (0) */
	protected double distFromMeter;

	protected boolean CoordDefined, BusChecked, Keep, IsRadialBus;

	public DSSBus() {
		super("Bus");
		// TODO Auto-generated constructor stub
	}

	private void addANode() {

	}

	public Complex getZsc0() {
		return null;
	}

	public Complex getZsc1() {
		return null;
	}

	public Complex[] getVBus() {
		return VBus;
	}

	public void setVBus(Complex[] vBus) {
		VBus = vBus;
	}

	public Complex[] getBusCurrent() {
		return BusCurrent;
	}

	public void setBusCurrent(Complex[] busCurrent) {
		BusCurrent = busCurrent;
	}

	public CMatrix getZsc() {
		return Zsc;
	}

	public void setZsc(CMatrix zsc) {
		Zsc = zsc;
	}

	public CMatrix getYsc() {
		return Ysc;
	}

	public void setYsc(CMatrix ysc) {
		Ysc = ysc;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getDistFromMeter() {
		return distFromMeter;
	}

	public void setDistFromMeter(double distFromMeter) {
		this.distFromMeter = distFromMeter;
	}

	public boolean isCoordDefined() {
		return CoordDefined;
	}

	public void setCoordDefined(boolean coordDefined) {
		CoordDefined = coordDefined;
	}

	public boolean isBusChecked() {
		return BusChecked;
	}

	public void setBusChecked(boolean busChecked) {
		BusChecked = busChecked;
	}

	public boolean isKeep() {
		return Keep;
	}

	public void setKeep(boolean keep) {
		Keep = keep;
	}

	public boolean isIsRadialBus() {
		return IsRadialBus;
	}

	public void setIsRadialBus(boolean isRadialBus) {
		IsRadialBus = isRadialBus;
	}

	public int getNumNodesThisBus() {
		return NumNodesThisBus;
	}
	
	public void allocateBusQuantities() {
		
	}
	
	public void allocateBusVoltages() {
		
	}
	
	public void allocateBusCurrents() {
		
	}

	public int add(int NodeNum) {
		return 0;
	}
	
	/** Returns reference num for node by node number */
	public int find(int NodeNum) {
		return 0;
	}
	
	/** Returns index of node by node number */
	public int findIdx(int NodeNum) {
		return 0;
	}
	
	/** Returns reference Num for node by node index */
	public int getRef(int NodeIndex) {
		return 0;
	}
	
	/** Returns ith node number designation */
	public int getNum(int NodeIndex) {
		return 0;
	}

}

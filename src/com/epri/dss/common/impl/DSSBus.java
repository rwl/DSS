package com.epri.dss.common.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.Bus;
import com.epri.dss.general.impl.NamedObjectImpl;

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

	protected DComplexMatrix1D VBus, BusCurrent;
	protected DComplexMatrix2D Zsc, Ysc;

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

	public double[] getZsc0() {
		return null;
	}

	public double[] getZsc1() {
		return null;
	}

	public DComplexMatrix1D getVBus() {
		return VBus;
	}

	public void setVBus(DComplexMatrix1D vBus) {
		VBus = vBus;
	}

	public DComplexMatrix1D getBusCurrent() {
		return BusCurrent;
	}

	public void setBusCurrent(DComplexMatrix1D busCurrent) {
		BusCurrent = busCurrent;
	}

	public DComplexMatrix2D getZsc() {
		return Zsc;
	}

	public void setZsc(DComplexMatrix2D zsc) {
		Zsc = zsc;
	}

	public DComplexMatrix2D getYsc() {
		return Ysc;
	}

	public void setYsc(DComplexMatrix2D ysc) {
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

}

package com.ncond.dss.common.impl;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.general.impl.NamedObjectImpl;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.impl.CMatrixImpl;

public class BusImpl extends NamedObjectImpl implements Bus {

	public static class NodeBus {
		public int busRef;  // ref to bus in circuit's bus list
		public int nodeNum;  // one-based
	}

	private int numNodesThisBus;
	private int[] nodes;
	private int allocation;
	private int[] refNo;

	protected Complex[] VBus, busCurrent;
	protected CMatrix Zsc, Ysc;

	protected double x, y;  // coordinates
	protected double kVBase;  // base kV for each node to ground (0)
	protected double distFromMeter;

	protected boolean coordDefined, busChecked, keep, isRadialBus;

	public BusImpl() {
		super("Bus");
		allocation = 3;
		nodes = new int[allocation];
		refNo = new int[allocation];
		numNodesThisBus = 0;
		Ysc             = null;
		Zsc             = null;
		VBus            = null;
		busCurrent      = null;
		kVBase          = 0.0;  // signify that it has not been set
		x               = 0.0;
		y               = 0.0;
		distFromMeter   = 0.0;
		coordDefined    = false;
		keep            = false;
		isRadialBus     = false;
	}

	@Override
	public int add(int nodeNum) {
		int result;
		Circuit ckt = DSS.activeCircuit;

		if (nodeNum == 0) {
			result = 0;
		} else {
			result = find(nodeNum);
			if (result == 0) {
				addANode();  // add a node to the bus
				nodes[numNodesThisBus - 1] = nodeNum;

				ckt.setNumNodes(ckt.getNumNodes() + 1);  // global node number for circuit
				refNo[numNodesThisBus - 1] = ckt.getNumNodes();
				result = ckt.getNumNodes();  // return global node number
			}
		}
		return result;
	}

	private void addANode() {
		numNodesThisBus += 1;
		if (numNodesThisBus > allocation) {
			allocation = allocation + 1;
			nodes = Util.resizeArray(nodes, allocation);
			refNo = Util.resizeArray(refNo, allocation);
		}
	}

	@Override
	public int find(int nodeNum) {
		for (int i = 0; i < numNodesThisBus; i++)
			if (nodes[i] == nodeNum) return refNo[i];
		return 0;
	}

	@Override
	public int getRef(int nodeIndex) {
		if (nodeIndex >= 0 && nodeIndex < numNodesThisBus) {
			return refNo[nodeIndex];
		} else {
			return 0;
		}
	}

	@Override
	public int getNum(int nodeIndex) {
		if (nodeIndex >= 0 && nodeIndex < numNodesThisBus) {
			return nodes[nodeIndex];
		} else {
			return 0;
		}
	}

	@Override
	public void allocateBusQuantities() {
		// have to perform a short circuit study to get this allocated
		Ysc = new CMatrixImpl(numNodesThisBus);
		Zsc = new CMatrixImpl(numNodesThisBus);
		allocateBusVoltages();
		allocateBusCurrents();
	}

	@Override
	public Complex getZsc0() {
		if (Zsc != null) {
			return Zsc.avgDiag().add( Zsc.avgOffDiag().multiply(2.0) );
		} else {
			return Complex.ZERO;
		}
	}

	@Override
	public Complex getZsc1() {
		if (Zsc != null) {
			return Zsc.avgDiag().subtract( Zsc.avgOffDiag() );
		} else {
			return Complex.ZERO;
		}
	}

	@Override
	public int findIdx(int nodeNum) {
		for (int i = 0; i < numNodesThisBus; i++)
			if (nodes[i] == nodeNum) return i;
		return -1;
	}

	@Override
	public void allocateBusVoltages() {
		VBus = new Complex[numNodesThisBus];

		for (int i = 0; i < numNodesThisBus; i++)
			VBus[i] = Complex.ZERO;
	}

	@Override
	public void allocateBusCurrents() {
		busCurrent = new Complex[numNodesThisBus];

		for (int i = 0; i < numNodesThisBus; i++)
			busCurrent[i] = Complex.ZERO;
	}

	@Override
	public Complex[] getVBus() {
		return VBus;
	}

	@Override
	public void setVBus(Complex[] vBus) {
		VBus = vBus;
	}

	@Override
	public Complex[] getBusCurrent() {
		return busCurrent;
	}

	@Override
	public void setBusCurrent(Complex[] buscurrent) {
		busCurrent = buscurrent;
	}

	@Override
	public CMatrix getZsc() {
		return Zsc;
	}

	@Override
	public void setZsc(CMatrix zsc) {
		Zsc = zsc;
	}

	@Override
	public CMatrix getYsc() {
		return Ysc;
	}

	@Override
	public void setYsc(CMatrix ysc) {
		Ysc = ysc;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getKVBase() {
		return kVBase;
	}

	@Override
	public void setKVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	@Override
	public double getDistFromMeter() {
		return distFromMeter;
	}

	@Override
	public void setDistFromMeter(double distFromMeter) {
		this.distFromMeter = distFromMeter;
	}

	@Override
	public boolean isCoordDefined() {
		return coordDefined;
	}

	@Override
	public void setCoordDefined(boolean defined) {
		coordDefined = defined;
	}

	@Override
	public boolean isBusChecked() {
		return busChecked;
	}

	@Override
	public void setBusChecked(boolean checked) {
		busChecked = checked;
	}

	@Override
	public boolean isKeep() {
		return keep;
	}

	@Override
	public void setKeep(boolean keep) {
		this.keep = keep;
	}

	@Override
	public boolean isRadialBus() {
		return isRadialBus;
	}

	@Override
	public void setRadialBus(boolean isRadial) {
		isRadialBus = isRadial;
	}

	@Override
	public int getNumNodesThisBus() {
		return numNodesThisBus;
	}

}

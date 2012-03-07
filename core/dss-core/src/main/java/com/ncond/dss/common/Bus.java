package com.ncond.dss.common;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.general.NamedObject;
import com.ncond.dss.shared.CMatrix;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Bus extends NamedObject {

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

	public Bus() {
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

	/**
	 * @param nodeNum
	 * @return global node number
	 */
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

	/**
	 * Returns reference num for node by node number
	 *
	 * @param nodeNum one based node number
	 * @return one based reference number
	 */
	public int find(int nodeNum) {
		for (int i = 0; i < numNodesThisBus; i++)
			if (nodes[i] == nodeNum) return refNo[i];
		return 0;
	}

	/**
	 * Returns reference num for node by node index
	 *
	 * @param nodeIndex zero based node index
	 * @return one based reference number
	 */
	public int getRef(int nodeIndex) {
		if (nodeIndex >= 0 && nodeIndex < numNodesThisBus) {
			return refNo[nodeIndex];
		} else {
			return 0;
		}
	}

	/**
	 * Returns i-th node number designation
	 *
	 * @param nodeIndex zero based node index
	 * @return one based node number
	 */
	public int getNum(int nodeIndex) {
		if (nodeIndex >= 0 && nodeIndex < numNodesThisBus) {
			return nodes[nodeIndex];
		} else {
			return 0;
		}
	}

	/**
	 * Returns index of node by node number
	 *
	 * @param nodeNum one based node number
	 * @return zero based node index
	 */
	public int findIdx(int nodeNum) {
		for (int i = 0; i < numNodesThisBus; i++)
			if (nodes[i] == nodeNum) return i;
		return -1;
	}

	public void allocateBusQuantities() {
		// have to perform a short circuit study to get this allocated
		Ysc = new CMatrix(numNodesThisBus);
		Zsc = new CMatrix(numNodesThisBus);
		allocateBusVoltages();
		allocateBusCurrents();
	}

	/**
	 * @return Zs + 2 Zm
	 */
	public Complex getZsc0() {
		if (Zsc != null) {
			return Zsc.avgDiag().add( Zsc.avgOffDiag().multiply(2.0) );
		} else {
			return Complex.ZERO;
		}
	}

	/**
	 * @return Zs - Zm
	 */
	public Complex getZsc1() {
		if (Zsc != null) {
			return Zsc.avgDiag().subtract( Zsc.avgOffDiag() );
		} else {
			return Complex.ZERO;
		}
	}

	public void allocateBusVoltages() {
		VBus = new Complex[numNodesThisBus];

		for (int i = 0; i < numNodesThisBus; i++)
			VBus[i] = Complex.ZERO;
	}

	public void allocateBusCurrents() {
		busCurrent = new Complex[numNodesThisBus];

		for (int i = 0; i < numNodesThisBus; i++)
			busCurrent[i] = Complex.ZERO;
	}

//	public Complex[] getVBus() {
//		return VBus;
//	}
//
//	public void setVBus(Complex[] vBus) {
//		VBus = vBus;
//	}

	public Complex getBusCurrent(int idx) {
		return busCurrent[idx];
	}

	public Complex[] getBusCurrent() {
		return busCurrent;
	}

//	public void setBusCurrent(Complex[] buscurrent) {
//		busCurrent = buscurrent;
//	}
//
//	public CMatrix getZsc() {
//		return Zsc;
//	}
//
//	public void setZsc(CMatrix zsc) {
//		Zsc = zsc;
//	}
//
//	public CMatrix getYsc() {
//		return Ysc;
//	}
//
//	public void setYsc(CMatrix ysc) {
//		Ysc = ysc;
//	}
//
//	public double getX() {
//		return x;
//	}
//
//	public void setX(double x) {
//		this.x = x;
//	}
//
//	public double getY() {
//		return y;
//	}
//
//	public void setY(double y) {
//		this.y = y;
//	}
//
//	public double getKVBase() {
//		return kVBase;
//	}
//
//	public void setKVBase(double kVBase) {
//		this.kVBase = kVBase;
//	}
//
//	public double getDistFromMeter() {
//		return distFromMeter;
//	}
//
//	public void setDistFromMeter(double distFromMeter) {
//		this.distFromMeter = distFromMeter;
//	}
//
//	public boolean isCoordDefined() {
//		return coordDefined;
//	}
//
//	public void setCoordDefined(boolean defined) {
//		coordDefined = defined;
//	}
//
//	public boolean isBusChecked() {
//		return busChecked;
//	}
//
//	public void setBusChecked(boolean checked) {
//		busChecked = checked;
//	}
//
//	public boolean isKeep() {
//		return keep;
//	}
//
//	public void setKeep(boolean keep) {
//		this.keep = keep;
//	}
//
//	public boolean isRadialBus() {
//		return isRadialBus;
//	}
//
//	public void setRadialBus(boolean isRadial) {
//		isRadialBus = isRadial;
//	}
//
//	public int getNumNodesThisBus() {
//		return numNodesThisBus;
//	}

}

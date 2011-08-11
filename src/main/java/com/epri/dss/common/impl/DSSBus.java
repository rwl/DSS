package com.epri.dss.common.impl;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.general.impl.NamedObjectImpl;
import com.epri.dss.shared.CMatrix;

public class DSSBus extends NamedObjectImpl implements Bus {

	public class NodeBus {
		/* Ref to bus in circuit's bus list */
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
		Allocation = 3;
		Nodes = new int[Allocation];
		RefNo = new int[Allocation];
		NumNodesThisBus = 0;
		Ysc              = null;
		Zsc              = null;
		VBus             = null;
		BusCurrent       = null;
		kVBase           = 0.0;  // signify that it has not been set
		x                = 0.0;
		y                = 0.0;
		distFromMeter    = 0.0;
		CoordDefined     = false;
		Keep             = false;
		IsRadialBus      = false;
	}

	private void addANode() {
		NumNodesThisBus += 1;
		if (NumNodesThisBus > Allocation) {
			Allocation = Allocation + 1;
			Nodes = (int[]) Utilities.resizeArray(Nodes, Allocation);
			RefNo = (int[]) Utilities.resizeArray(RefNo, Allocation);
		}
	}

	public int add(int NodeNum) {
		int Result;

		if (NodeNum == 0) {
			Result = 0;
		} else {
			Result = find(NodeNum);
			if (Result == 0) {
				// Add a node to the bus
				addANode();
				Nodes[NumNodesThisBus] = NodeNum;

				Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

				ckt.setNumNodes(ckt.getNumNodes() + 1);  // global node number for circuit
				RefNo[NumNodesThisBus] = ckt.getNumNodes();
				Result = ckt.getNumNodes();  // return global node number
			}
		}

		return Result;
	}

	/**
	 * Returns reference num for node by node number.
	 */
	public int find(int NodeNum) {
		for (int i = 0; i < NumNodesThisBus; i++) {
			if (Nodes[i] == NodeNum)
				return RefNo[i];
		}
		return 0;
	}

	/**
	 * Returns reference num for node by node index.
	 */
	public int getRef(int NodeIndex) {  // FIXME Check zero based indexing
		if ((NodeIndex > 0) && (NodeIndex <= NumNodesThisBus)) {
			return RefNo[NodeIndex];
		} else {
			return 0;
		}
	}

	/**
	 * Returns ith node number designation.
	 */
	public int getNum(int NodeIndex) {
		if ((NodeIndex > 0) && (NodeIndex <= NumNodesThisBus)) {
			return Nodes[NodeIndex];
		} else {
			return 0;
		}
	}

	public void allocateBusQuantities() {
		// have to perform a short circuit study to get this allocated
		Ysc = new CMatrixImpl(NumNodesThisBus);
		Zsc = new CMatrixImpl(NumNodesThisBus);
		allocateBusVoltages();
		allocateBusCurrents();
	}

	/**
	 * = Zs + 2 Zm
	 */
	public Complex getZsc0() {
		if (Zsc != null) {
			return Zsc.avgDiagonal().add( Zsc.avgOffDiagonal().multiply(2.0) );
		} else {
			return Complex.ZERO;
		}
	}

	/**
	 * = Zs - Zm
	 */
	public Complex getZsc1() {
		if (Zsc != null) {
			return Zsc.avgDiagonal().subtract( Zsc.avgOffDiagonal() );
		} else {
			return Complex.ZERO;
		}
	}

	/**
	 * Returns index of node by node number.
	 */
	public int findIdx(int NodeNum) {
		for (int i = 0; i < NumNodesThisBus; i++) {
			if (Nodes[i] == NodeNum)
				return i;
		}
		return 0;  // TODO Check zero based indexing
	}

	public void allocateBusVoltages() {
		VBus = (Complex[]) Utilities.resizeArray(VBus, NumNodesThisBus);
		for (int i = 0; i < NumNodesThisBus; i++)
			VBus[i] = Complex.ZERO;
	}

	public void allocateBusCurrents() {
		BusCurrent = (Complex[]) Utilities.resizeArray(BusCurrent, NumNodesThisBus);
		for (int i = 0; i < NumNodesThisBus; i++)
			BusCurrent[i] = Complex.ZERO;
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

}

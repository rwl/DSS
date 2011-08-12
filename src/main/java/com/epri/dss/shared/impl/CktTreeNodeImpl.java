package com.epri.dss.shared.impl;

import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.PointerList;

public class CktTreeNodeImpl implements CktTreeNode {

	private PointerList ChildBranches;  // TODO Replace with List and Iterator
	private int NumToBuses, ToBusPtr;
	private int[] ToBusList;

	protected boolean ChildAdded;
	protected int LexicalLevel;
	protected CktTreeNode ParentBranch;
	protected PointerList ShuntObjects;  // generic objects attached to the tree at this node

	protected Object CktObject;  // pointer to the circuit object referenced
	protected int FromBusReference;
	protected int VoltBaseIndex;
	protected int FromTerminal;
	protected boolean IsLoopedHere, IsParallel, IsDangling;
	protected Object LoopLineObj;


	public CktTreeNodeImpl(CktTreeNode pParent, Object pSelfObj) {
		super();
		this.CktObject = pSelfObj;
		this.ParentBranch = pParent;
		if (this.ParentBranch != null) {
			LexicalLevel = this.ParentBranch.getLexicalLevel() + 1;
		} else {
			LexicalLevel = 0;
		}
		this.ChildBranches   = new PointerListImpl(2);
		this.ShuntObjects    = new PointerListImpl(1);
		this.FromBusReference = 0;
		this.VoltBaseIndex    = 0;  // index to voltage base list used by EnergyMeter and maybe others
		this.NumToBuses = 0;
		this.ToBusList = null;
		this.ToBusPtr = 0;
		this.ChildAdded = false;
		// TEMc - initialize some topology variables, 10/2009
		this.IsDangling = true;
		this.IsLoopedHere = false;
		this.IsParallel = false;
		this.LoopLineObj = null;
	}

	public void addChild(CktTreeNode Value) {
		ChildBranches.add(Value);
		ChildAdded = true;
	}

	public void addObject(DSSObject Value) {
		ShuntObjects.add(Value);
	}

	public CktTreeNode getFirstChild() {
		return (CktTreeNode) ChildBranches.getFirst();
	}

	public CktTreeNode getNextChild() {
		return (CktTreeNode) ChildBranches.getNext();
	}

	public CktTreeNode getParent() {
		return ParentBranch;
	}

	/**
	 * Number of children at present node.
	 */
	public int getNumChildren() {
		return ChildBranches.size();
	}

	/**
	 * Number of objects at present node.
	 */
	public int getNumObjects() {
		return ShuntObjects.size();
	}

	/**
	 * Sequentially access the toBus list if more than one with each invocation of the property.
	 */
	public int getToBusReference() {
		if (NumToBuses == 1)  {
			return ToBusList[0];  // always return the first
		} else {
			ToBusPtr += 1;
			if (ToBusPtr >= NumToBuses) {  // TODO Check zero based indexing
				ToBusPtr = 0;  // ready for next sequence of access
				return -1;
			} else {
				return ToBusList[ToBusPtr];
			}
		}
	}

	public void setToBusReference(int Value) {
		NumToBuses += 1;
		ToBusList = (int[]) Utilities.resizeArray(ToBusList, NumToBuses);
		ToBusList[NumToBuses] = Value;
	}

	public void resetToBusList() {
		ToBusPtr = 0;  // TODO Check zero based indexing
	}

	public DSSObject getFirstObject() {
		return (DSSObject) ShuntObjects.getFirst();  // TODO Make generic
	}

	public DSSObject getNextObject() {
		return (DSSObject) ShuntObjects.getNext();
	}

	public DSSObject getCktObject() {
		return (DSSObject) CktObject;
	}

	public void setCktObject(DSSObject cktObject) {
		CktObject = cktObject;
	}

	public int getFromBusReference() {
		return FromBusReference;
	}

	public void setFromBusReference(int fromBusReference) {
		FromBusReference = fromBusReference;
	}

	public int getVoltBaseIndex() {
		return VoltBaseIndex;
	}

	public void setVoltBaseIndex(int voltBaseIndex) {
		VoltBaseIndex = voltBaseIndex;
	}

	public int getFromTerminal() {
		return FromTerminal;
	}

	public void setFromTerminal(int fromTerminal) {
		FromTerminal = fromTerminal;
	}

	public boolean isIsLoopedHere() {
		return IsLoopedHere;
	}

	public void setIsLoopedHere(boolean isLoopedHere) {
		IsLoopedHere = isLoopedHere;
	}

	public boolean isIsParallel() {
		return IsParallel;
	}

	public void setIsParallel(boolean isParallel) {
		IsParallel = isParallel;
	}

	public boolean isIsDangling() {
		return IsDangling;
	}

	public void setIsDangling(boolean isDangling) {
		IsDangling = isDangling;
	}

	public Object getLoopLineObj() {
		return LoopLineObj;
	}

	public void setLoopLineObj(Object loopLineObj) {
		LoopLineObj = loopLineObj;
	}

	// FIXME Protected members in OpenDSS

	public boolean isChildAdded() {
		return ChildAdded;
	}

	public void setChildAdded(boolean childAdded) {
		ChildAdded = childAdded;
	}

	public int getLexicalLevel() {
		return LexicalLevel;
	}

	public void setLexicalLevel(int lexicalLevel) {
		LexicalLevel = lexicalLevel;
	}

}

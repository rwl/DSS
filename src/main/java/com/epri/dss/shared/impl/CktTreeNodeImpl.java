package com.epri.dss.shared.impl;

import java.util.ArrayList;
import java.util.List;

import com.epri.dss.shared.CktTreeNode;

public class CktTreeNodeImpl implements CktTreeNode {

	private List<Object> ChildBranches;
	private int NumToBuses, ToBusPtr;
	private int[] ToBusList;

	protected boolean ChildAdded;
	protected int LexicalLevel;
	protected CktTreeNode ParentBranch;
	protected List<Object> ShuntObjects;  // Generic objects attached to the tree at this node


	protected Object CktObject;    // Pointer to the circuit object referenced
	protected int FromBusReference;
	protected int VoltBaseIndex;
	protected int FromTerminal;
	protected boolean IsLoopedHere, IsParallel, IsDangling;
	protected Object LoopLineObj;


	public CktTreeNodeImpl(CktTreeNode pParent, Object pSelfObj) {
		// TODO Auto-generated constructor stub
	}

	public CktTreeNode getFirstChild() {
		return null;
	}

	public CktTreeNode getNextChild() {
		return null;
	}

	public CktTreeNode getParent() {
		return null;
	}

	public void setAddChild(CktTreeNode Value) {

	}

	public void setAddObject(Object Value) {

	}

	/**
	 * Number of children at present node.
	 */
	public int getNumChildren() {
		return 0;
	}

	/**
	 * Number of objects at present node.
	 */
	public int getNumObjects() {
		return 0;
	}

	public int getToBusReference() {
		return 0;
	}

	public void setToBusReference(int Value) {

	}

	public Object getFirstObject() {
		return null;
	}

	public Object getNextObject() {
		return null;
	}

	public void resetToBusList() {

	}


	public Object getCktObject() {
		return CktObject;
	}

	public void setCktObject(Object cktObject) {
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

}

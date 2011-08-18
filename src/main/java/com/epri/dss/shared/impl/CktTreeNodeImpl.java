package com.epri.dss.shared.impl;

import com.epri.dss.common.CktElement;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.ObjectList;

public class CktTreeNodeImpl<T extends CktElement> implements CktTreeNode<T> {

	private ObjectList<CktTreeNode<T>> childBranches;
	private int numToBuses, toBusPtr;
	private int[] toBusList;

	protected boolean childAdded;
	protected int lexicalLevel;
	protected CktTreeNode<T> parentBranch;
	protected ObjectList<T> shuntObjects;  // generic objects attached to the tree at this node

	protected T cktObject;  // pointer to the circuit object referenced
	protected int fromBusReference;
	protected int voltBaseIndex;
	protected int fromTerminal;
	protected boolean isLoopedHere, isParallel, isDangling;
	protected T loopLineObj;


	public CktTreeNodeImpl(CktTreeNode<T> parent, T selfObj) {
		super();
		cktObject = selfObj;
		parentBranch = parent;
		if (parentBranch != null) {
			lexicalLevel = parentBranch.getLexicalLevel() + 1;
		} else {
			lexicalLevel = 0;
		}
		childBranches   = new ObjectListImpl<CktTreeNode<T>>(2);
		shuntObjects    = new ObjectListImpl<T>(1);
		fromBusReference = 0;
		voltBaseIndex    = 0;  // index to voltage base list used by EnergyMeter and maybe others
		numToBuses = 0;
		toBusList = null;
		toBusPtr = 0;
		childAdded = false;
		// TEMc - initialize some topology variables, 10/2009
		isDangling = true;
		isLoopedHere = false;
		isParallel = false;
		loopLineObj = null;
	}

	public void addChild(CktTreeNode<T> value) {
		childBranches.add(value);
		childAdded = true;
	}

	public void addObject(T value) {
		shuntObjects.add(value);
	}

	public CktTreeNode<T> getFirstChild() {
		return childBranches.getFirst();
	}

	public CktTreeNode<T> getNextChild() {
		return childBranches.getNext();
	}

	public CktTreeNode<T> getParent() {
		return parentBranch;
	}

	/**
	 * Number of children at present node.
	 */
	public int getNumChildren() {
		return childBranches.size();
	}

	/**
	 * Number of objects at present node.
	 */
	public int getNumObjects() {
		return shuntObjects.size();
	}

	/**
	 * Sequentially access the toBus list if more than one with each invocation of the property.
	 */
	public int getToBusReference() {
		if (numToBuses == 1)  {
			return toBusList[0];  // always return the first
		} else {
			toBusPtr += 1;
			if (toBusPtr >= numToBuses) {  // TODO Check zero based indexing
				toBusPtr = 0;  // ready for next sequence of access
				return -1;
			} else {
				return toBusList[toBusPtr];
			}
		}
	}

	public void setToBusReference(int value) {
		numToBuses += 1;

		// resize array
		int[] newList = new int[numToBuses];
		int size = toBusList.length;
		int l = Math.min(size, numToBuses);
		if (l > 0)
			System.arraycopy(toBusList, 0, newList, 0, l);
		toBusList = newList;

		toBusList[numToBuses] = value;
	}

	public void resetToBusList() {
		toBusPtr = 0;  // TODO Check zero based indexing
	}

	public T getFirstObject() {
		return shuntObjects.getFirst();
	}

	public T getNextObject() {
		return shuntObjects.getNext();
	}

	public T getCktObject() {
		return cktObject;
	}

	public void setCktObject(T ckt) {
		cktObject = ckt;
	}

	public int getFromBusReference() {
		return fromBusReference;
	}

	public void setFromBusReference(int reference) {
		fromBusReference = reference;
	}

	public int getVoltBaseIndex() {
		return voltBaseIndex;
	}

	public void setVoltBaseIndex(int index) {
		voltBaseIndex = index;
	}

	public int getFromTerminal() {
		return fromTerminal;
	}

	public void setFromTerminal(int terminal) {
		fromTerminal = terminal;
	}

	public boolean isLoopedHere() {
		return isLoopedHere;
	}

	public void setLoopedHere(boolean value) {
		isLoopedHere = value;
	}

	public boolean isParallel() {
		return isParallel;
	}

	public void setParallel(boolean value) {
		isParallel = value;
	}

	public boolean isDangling() {
		return isDangling;
	}

	public void setDangling(boolean value) {
		isDangling = value;
	}

	public T getLoopLineObj() {
		return loopLineObj;
	}

	public void setLoopLineObj(T lineObj) {
		loopLineObj = lineObj;
	}

	// FIXME Protected members in OpenDSS

	public boolean isChildAdded() {
		return childAdded;
	}

	public void setChildAdded(boolean added) {
		childAdded = added;
	}

	public int getLexicalLevel() {
		return lexicalLevel;
	}

	public void setLexicalLevel(int level) {
		lexicalLevel = level;
	}

}

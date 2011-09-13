package com.epri.dss.shared.impl;

import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.PointerList;

public class CktTreeNodeImpl implements CktTreeNode {

	private PointerList childBranches;  // TODO Replace with List and Iterator
	private int numToBuses, toBusPtr;
	private int[] toBusList;

	protected boolean childAdded;
	protected int lexicalLevel;
	protected CktTreeNode parentBranch;
	protected PointerList shuntObjects;  // generic objects attached to the tree at this node

	protected Object cktObject;  // pointer to the circuit object referenced
	protected int fromBusReference;
	protected int voltBaseIndex;
	protected int fromTerminal;
	protected boolean isLoopedHere, isParallel, isDangling;
	protected Object loopLineObj;


	public CktTreeNodeImpl(CktTreeNode parent, Object selfObj) {
		super();
		cktObject = selfObj;
		parentBranch = parent;
		if (parentBranch != null) {
			lexicalLevel = parentBranch.getLexicalLevel() + 1;
		} else {
			lexicalLevel = 0;
		}
		childBranches   = new PointerListImpl(2);
		shuntObjects    = new PointerListImpl(1);
		fromBusReference = 0;
		voltBaseIndex    = 0;  // index to voltage base list used by EnergyMeter and maybe others
		numToBuses = 0;
		toBusList = null;
		toBusPtr = 0;
		childAdded = false;

		isDangling = true;
		isLoopedHere = false;
		isParallel = false;
		loopLineObj = null;
	}

	public void addChild(CktTreeNode value) {
		childBranches.add(value);
		childAdded = true;
	}

	public void addObject(DSSObject value) {
		shuntObjects.add(value);
	}

	public CktTreeNode getFirstChild() {
		return (CktTreeNode) childBranches.getFirst();
	}

	public CktTreeNode getNextChild() {
		return (CktTreeNode) childBranches.getNext();
	}

	public CktTreeNode getParent() {
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
		toBusList = Utilities.resizeArray(toBusList, numToBuses);
		toBusList[numToBuses] = value;
	}

	public void resetToBusList() {
		toBusPtr = 0;  // TODO Check zero based indexing
	}

	public DSSObject getFirstObject() {
		return (DSSObject) shuntObjects.getFirst();  // TODO Make generic
	}

	public DSSObject getNextObject() {
		return (DSSObject) shuntObjects.getNext();
	}

	public DSSObject getCktObject() {
		return (DSSObject) cktObject;
	}

	public void setCktObject(DSSObject ckt) {
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

	public Object getLoopLineObj() {
		return loopLineObj;
	}

	public void setLoopLineObj(Object lineObj) {
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

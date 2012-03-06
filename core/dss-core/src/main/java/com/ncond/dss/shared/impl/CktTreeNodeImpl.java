package com.ncond.dss.shared.impl;

import com.ncond.dss.common.impl.Util;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.CktTreeNode;
import com.ncond.dss.shared.PointerList;

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
		fromBusReference = -1;
		voltBaseIndex    = -1;  // index to voltage base list used by EnergyMeter and maybe others
		numToBuses = 0;
		toBusList = null;
		toBusPtr = -1;
		childAdded = false;

		isDangling = true;
		isLoopedHere = false;
		isParallel = false;
		loopLineObj = null;
	}

	@Override
	public void addChild(CktTreeNode value) {
		childBranches.add(value);
		childAdded = true;
	}

	@Override
	public void addObject(DSSObject value) {
		shuntObjects.add(value);
	}

	@Override
	public CktTreeNode getFirstChild() {
		return (CktTreeNode) childBranches.getFirst();
	}

	@Override
	public CktTreeNode getNextChild() {
		return (CktTreeNode) childBranches.getNext();
	}

	@Override
	public CktTreeNode getParent() {
		return parentBranch;
	}

	/**
	 * Number of children at present node.
	 */
	@Override
	public int getNumChildren() {
		return childBranches.size();
	}

	/**
	 * Number of objects at present node.
	 */
	@Override
	public int getNumObjects() {
		return shuntObjects.size();
	}

	/**
	 * Sequentially access the toBus list if more than one with each invocation of the property.
	 */
	@Override
	public int getToBusReference() {
		if (numToBuses == 1)  {
			return toBusList[0];  // always return the first
		} else {
			toBusPtr += 1;
			if (toBusPtr >= numToBuses) {
				toBusPtr = -1;  // ready for next sequence of access
				return -1;
			} else {
				return toBusList[toBusPtr];
			}
		}
	}

	@Override
	public void setToBusReference(int value) {
		numToBuses += 1;
		toBusList = Util.resizeArray(toBusList, numToBuses);
		toBusList[numToBuses] = value;
	}

	@Override
	public void resetToBusList() {
		toBusPtr = -1;
	}

	@Override
	public DSSObject getFirstObject() {
		return (DSSObject) shuntObjects.getFirst();
	}

	@Override
	public DSSObject getNextObject() {
		return (DSSObject) shuntObjects.getNext();
	}

	@Override
	public DSSObject getCktObject() {
		return (DSSObject) cktObject;
	}

	@Override
	public void setCktObject(DSSObject ckt) {
		cktObject = ckt;
	}

	@Override
	public int getFromBusReference() {
		return fromBusReference;
	}

	@Override
	public void setFromBusReference(int reference) {
		fromBusReference = reference;
	}

	@Override
	public int getVoltBaseIndex() {
		return voltBaseIndex;
	}

	@Override
	public void setVoltBaseIndex(int index) {
		voltBaseIndex = index;
	}

	@Override
	public int getFromTerminal() {
		return fromTerminal;
	}

	@Override
	public void setFromTerminal(int terminal) {
		fromTerminal = terminal;
	}

	@Override
	public boolean isLoopedHere() {
		return isLoopedHere;
	}

	@Override
	public void setLoopedHere(boolean value) {
		isLoopedHere = value;
	}

	@Override
	public boolean isParallel() {
		return isParallel;
	}

	@Override
	public void setParallel(boolean value) {
		isParallel = value;
	}

	@Override
	public boolean isDangling() {
		return isDangling;
	}

	@Override
	public void setDangling(boolean value) {
		isDangling = value;
	}

	@Override
	public Object getLoopLineObj() {
		return loopLineObj;
	}

	@Override
	public void setLoopLineObj(Object lineObj) {
		loopLineObj = lineObj;
	}

	// FIXME Protected members in OpenDSS

	@Override
	public boolean isChildAdded() {
		return childAdded;
	}

	@Override
	public void setChildAdded(boolean added) {
		childAdded = added;
	}

	@Override
	public int getLexicalLevel() {
		return lexicalLevel;
	}

	@Override
	public void setLexicalLevel(int level) {
		lexicalLevel = level;
	}

}

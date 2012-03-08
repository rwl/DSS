package com.ncond.dss.shared;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.Util;
import com.ncond.dss.general.DSSObject;

@Getter @Setter
public class CktTreeNode {

	private PointerList childBranches;  // TODO Replace with List and Iterator
	private int numToBuses, toBusPtr;
	private int[] toBusList;

	protected boolean childAdded;
	protected int lexicalLevel;
	protected CktTreeNode parentBranch;
	protected PointerList shuntObjects;  // generic objects attached to the tree at this node

	protected DSSObject cktObject;  // pointer to the circuit object referenced
	protected int fromBusReference;  // one based bus reference
	protected int voltBaseIndex;
	protected int fromTerminal;
	protected boolean isLoopedHere, isParallel, isDangling;
	protected Object loopLineObj;


	public CktTreeNode(CktTreeNode parent, DSSObject selfObj) {
		super();
		cktObject = selfObj;
		parentBranch = parent;
		if (parentBranch != null) {
			lexicalLevel = parentBranch.getLexicalLevel() + 1;
		} else {
			lexicalLevel = 0;
		}
		childBranches   = new PointerList(2);
		shuntObjects    = new PointerList(1);
		fromBusReference = 0;
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
			if (toBusPtr >= numToBuses) {
				toBusPtr = -1;  // ready for next sequence of access
				return 0;
			} else {
				return toBusList[toBusPtr];
			}
		}
	}

	public void setToBusReference(int value) {
		numToBuses += 1;
		toBusList = Util.resizeArray(toBusList, numToBuses);
		toBusList[numToBuses] = value;
	}

	public void resetToBusList() {
		toBusPtr = -1;
	}

	public DSSObject getFirstObject() {
		return (DSSObject) shuntObjects.getFirst();
	}

	public DSSObject getNextObject() {
		return (DSSObject) shuntObjects.getNext();
	}

}

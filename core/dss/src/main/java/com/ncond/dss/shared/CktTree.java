package com.ncond.dss.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.general.DSSObject;

@Getter @Setter
public class CktTree {

	private CktTreeNode firstNode;
	private Stack<Object> forwardStack;

	protected CktTreeNode presentBranch;
	protected ZoneEndsList zoneEndsList;

	public CktTree() {
		super();
		firstNode = null;
		presentBranch = null;
		zoneEndsList = new ZoneEndsList();
		forwardStack = new Stack<Object>();
	}

	/**
	 * Adds child and makes it present.
	 */
	public void setNew(DSSObject value) {
		presentBranch = new CktTreeNode(presentBranch, value);
		if (firstNode == null) firstNode = presentBranch;
	}

	public void addNewChild(DSSObject value, int busRef, int terminalIdx) {
		CktTreeNode tmpNode;

		if (presentBranch == null) {
			setNew(value);
		} else {
			tmpNode = new CktTreeNode(presentBranch, value);

			tmpNode.setFromBusReference(busRef);
			tmpNode.setFromTerminalIdx(terminalIdx);

			presentBranch.addChild(tmpNode);
		}
	}

	/**
	 * Adds a pointer to an object to be associated with the current node.
	 */
	public void setNewObject(DSSObject value) {
		if (presentBranch != null) {
			presentBranch.addObject(value);
		}
	}

	private void pushAllChildren() {
		CktTreeNode child;

		if (presentBranch != null) {
			// push all children of present node onto stack
			child = presentBranch.getFirstChild();
			while (child != null) {
				forwardStack.push(child);
				child = presentBranch.getNextChild();
			}
			presentBranch.setChildAdded(false);
		}
	}

	/**
	 * Move forward from present node.
	 */
	public DSSObject goForward() {
		// if we have added children to the present node since we opened it push them on
		if (presentBranch != null) {
			if (presentBranch.isChildAdded()) pushAllChildren();
		}

		// if the forward stack is empty push stuff on it to get started
		if (forwardStack.size() == 0) pushAllChildren();

		presentBranch = (CktTreeNode) forwardStack.pop();
		pushAllChildren();  // push all children of latest
		if (presentBranch != null) {
			return presentBranch.getCktObject();
		} else {
			return null;
		}
	}

	/**
	 * Move backward from present node and reset forward stack.
	 */
	public DSSObject goBackward() {
		presentBranch = presentBranch.getParent();
		forwardStack.clear();
		if (presentBranch != null) {
			return presentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public Object getParent() {
		if (presentBranch.getParent() != null) {
			return presentBranch.getParent().getCktObject();
		} else {
			return null;
		}
	}

	/**
	 * Returns the first circuit object.
	 *
	 * Go to beginning and reset forward stack.
	 */
	public DSSObject getFirst() {
		presentBranch = firstNode;
		forwardStack.clear();
		pushAllChildren();
		if (presentBranch != null) {
			return (DSSObject) presentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public Object getFirstObject() {
		if (presentBranch != null) {
			return presentBranch.getFirstObject();
		} else {
			return null;
		}
	}

	public Object getNextObject() {
		if (presentBranch != null) {
			return presentBranch.getNextObject();
		} else {
			return null;
		}
	}

	public Object getActive() {
		if (presentBranch != null) {
			return presentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public void setActive(DSSObject p) {
		DSSObject temp = getFirst();
		while (temp != null) {
			if (presentBranch.getCktObject() == p) break;
			temp = goForward();
		}
		forwardStack.clear();
	}

	/**
	 * Start forward search at the present location (can also use active).
	 */
	public void startHere() {
		forwardStack.clear();
		if (presentBranch != null) {
			forwardStack.push(presentBranch);
		}
	}

	/**
	 * Get lexical level of present node.
	 */
	public int getLevel() {
		if (presentBranch != null) {
			return presentBranch.getLexicalLevel();
		} else {
			return 0;
		}
	}

	/* Utility code for building a connected tree starting from a circuit element. */

	/**
	 * Sources are excluded from the PC element list, so this is a brute-force search.
	 */
	private static void getSourcesConnectedToBus(int busRef, CktTree branchList, boolean analyze) {
		Circuit ckt = DSS.activeCircuit;

		for (PCElement elem : ckt.getSources()) {  // sources are special PC elements
			if (elem.isEnabled()) {
				if (analyze || !elem.isChecked()) {
					if (elem.getTerminal(0).getBusRef() == busRef) {  // connected to this bus?
						if (analyze) {
							elem.setIsolated(false);
							branchList.getPresentBranch().setDangling(false);
						}
						if (!elem.isChecked()) {
							branchList.setNewObject(elem);
							elem.setChecked(true);
						}
					}
				}
			}
		}
	}

	private static void getPCElementsConnectedToBus(List<PCElement> adjLst, CktTree branchList, boolean analyze) {
		for (int i = 0; i < adjLst.size(); i++) {
			CktElement elem = adjLst.get(i);
			if (elem.isEnabled()) {
				if (analyze) {
					elem.setIsolated(false);
					branchList.getPresentBranch().setDangling(false);
				}
				if (!elem.isChecked()) {
					branchList.setNewObject(elem);
					elem.setChecked(true);
				}
			}
		}
	}


	private static void findAllChildBranches(List<PDElement> adjLst, int busRef, CktTree branchList,
			boolean analyze, CktElement activeBranch) {
		int i, j;
		CktElement elem;

		for (i = 0; i < adjLst.size(); i++) {
			elem = adjLst.get(i);
			if (elem.isEnabled() && elem != activeBranch) {
				if (analyze || !elem.isChecked()) {
					if (!Util.isShuntElement(elem) && Util.allTerminalsClosed(elem)) {
						for (j = 0; j < elem.getNumTerms(); j++) {
							if (busRef == elem.getTerminal(j).getBusRef()) {
								if (analyze) {
									elem.setIsolated(false);
									branchList.getPresentBranch().setDangling(false);
									if (elem.isChecked() && branchList.getLevel() > 0) {
										branchList.getPresentBranch().setLoopedHere(true);
										branchList.getPresentBranch().setLoopLineObj(elem);
										if (Util.isLineElement(elem) && Util.isLineElement(activeBranch))
											if (Util.checkParallel(activeBranch, elem))
												branchList.getPresentBranch().setParallel(true);
									}
								}
								if (!elem.isChecked()) {
									branchList.addNewChild(elem, busRef, j);
									elem.getTerminal(j).setChecked(true);
									elem.setChecked(true);
									break;  /* for */
								}
							}
						}
					}
				}
			}
		}
	}

	private static void getShuntPDElementsConnectedToBus(List<PDElement> adjLst, CktTree branchList, boolean analyze) {
		for (int i = 0; i < adjLst.size(); i++) {
			CktElement p = adjLst.get(i);
			if (p.isEnabled() && Util.isShuntElement(p)) {
				if (analyze) {
					p.setIsolated(false);
					branchList.getPresentBranch().setDangling(false);
				}
				if (!p.isChecked()) {
					branchList.setNewObject(p);
					p.setChecked(true);
				}
			}
		}
	}

	public static CktTree getIsolatedSubArea(CktElement startElement) {
		return getIsolatedSubArea(startElement, false);
	}

	/**
	 * Build a tree of connected elements beginning at startElement.
	 * Analyze = true; will check for loops, isolated components, and parallel lines (takes longer)
	 */
	public static CktTree getIsolatedSubArea(CktElement startElement, boolean analyze) {
		int testBusRef;
		CktTree branchList;
		int iTerm;
		CktElement testBranch, testElement;
		List<PDElement>[] lstPD;
		List<PCElement>[] lstPC;

		Circuit ckt = DSS.activeCircuit;

		lstPD = ckt.getBusAdjacentPDLists();
		lstPC = ckt.getBusAdjacentPCLists();

		branchList = new CktTree();
		testElement = startElement;

		branchList.setNew(testElement);
		if (analyze)
			testElement.setIsolated(false);
		testElement.setLastTerminalChecked(0);  // we'll check things connected to both sides

		// check off this element so we don't use it again
		testElement.setChecked(true);

		// now start looking for other branches
		// finds any branch connected to the TestBranch and adds it to the list
		// goes until end of circuit, another energy meter, an open terminal, or disabled device
		testBranch = testElement;
		while (testBranch != null) {
			for (iTerm = 0; iTerm < testBranch.getNumTerms(); iTerm++) {
				if (!testBranch.getTerminal(iTerm).isChecked()) {
					// now find all PC elements connected to the bus on this end of branch
					// attach them as generic objects to cktTree node.
					testBusRef = testBranch.getTerminal(iTerm).getBusRef();
					branchList.getPresentBranch().setToBusReference(testBusRef);  // add this as a "to" bus reference
					if (testBusRef > 0) {
						ckt.getBus(testBusRef - 1).setBusChecked(true);
						getSourcesConnectedToBus(testBusRef, branchList, analyze);
						getPCElementsConnectedToBus(lstPC[testBusRef - 1], branchList, analyze);
						getShuntPDElementsConnectedToBus(lstPD[testBusRef - 1], branchList, analyze);
						findAllChildBranches(lstPD[testBusRef - 1], testBusRef, branchList, analyze, testBranch);
					}
				}
			}
			testBranch = (CktElement) branchList.goForward();
		}
		return branchList;
	}

	@SuppressWarnings("unchecked")
	public static void buildActiveBusAdjacencyLists(List<PDElement>[] lstPD, List<PCElement>[] lstPC) {
		int i, j, nBus;
		Circuit ckt = DSS.activeCircuit;

		nBus = ckt.getNumBuses();
		// ckt.getBuses() is effectively 1-based; bus 0 is ground
		lstPD = new List[nBus + 1];  // FIXME: unchecked type conversion
		lstPC = new List[nBus + 1];
		for (i = 0; i < nBus; i++) {
			lstPD[i] = new ArrayList<PDElement>();  // default capacity should be enough
			lstPC[i] = new ArrayList<PCElement>();
		}

		for (PCElement elem : ckt.getPCElements()) {
			if (elem.isEnabled()) {
				i = elem.getTerminal(0).getBusRef();
				lstPC[i].add(elem);
			}
		}

		for (CktElement elem : ckt.getPDElements()) {
			/* Put only eligible PD elements in the list */
			if (elem.isEnabled()) {
				if (Util.isShuntElement(elem)) {
					i = elem.getTerminal(0).getBusRef() - 1;
					lstPC[i].add((PCElement) elem);
				} else if (Util.allTerminalsClosed(elem))
					for (j = 0; j < elem.getNumTerms(); j++) {
						i = elem.getTerminal(j).getBusRef() - 1;
						lstPD[i].add((PDElement) elem);
					}
			}
		}
	}

	public static void freeBusAdjacencyLists(List<PDElement>[] lstPD, List<PCElement>[] lstPC) {
		for (int i = 0; i < lstPD.length; i++) {
			lstPD[i] = null;
			lstPC[i] = null;
		}
	}

}

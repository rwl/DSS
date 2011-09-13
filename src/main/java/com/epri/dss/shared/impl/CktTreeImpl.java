package com.epri.dss.shared.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.PointerList;

public class CktTreeImpl implements CktTree {

	public class ZoneEndsList {

		private PointerList endNodeList;
		private int[] endBuses;

		protected int numEnds;

		public ZoneEndsList() {
			endNodeList = new PointerListImpl(10);
			numEnds = 0;
			endBuses = null;
		}

		public void add(CktTreeNode node, int endBusRef) {
			numEnds += 1;
			endNodeList.add(node);
			endBuses = Utilities.resizeArray(endBuses, numEnds);
			endBuses[numEnds - 1] = endBusRef;  // TODO Check zero based indexing
		}

		public int get(int i, CktTreeNode node) {
			node = (CktTreeNode) endNodeList.get(i);  // FIXME Make generic
			return endBuses[i];
		}

		public int getNumEnds() {
			return numEnds;
		}

		public void setNumEnds(int num) {
			numEnds = num;
		}

	}

	private CktTreeNode firstNode;
	private Stack<Object> forwardStack;

	protected CktTreeNode presentBranch;
	protected ZoneEndsList zoneEndsList;

	public CktTreeImpl() {
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
		presentBranch = new CktTreeNodeImpl(presentBranch, value);
		if (firstNode == null)
			firstNode = presentBranch;
	}

	public void addNewChild(DSSObject value, int busRef, int terminalNo) {
		CktTreeNode tempNode;

		if (presentBranch == null) {
			setNew(value);
		} else {
			tempNode = new CktTreeNodeImpl(presentBranch, value);

			tempNode.setFromBusReference(busRef);
			tempNode.setFromTerminal(terminalNo);

			presentBranch.addChild(tempNode);
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
		CktTreeNode pChild;

		if (presentBranch != null) {
			// push all children of present node onto stack
			pChild = presentBranch.getFirstChild();
			while (pChild != null) {
				forwardStack.push((DSSObject) pChild);  // FIXME Implement generics
				pChild = presentBranch.getNextChild();
			}
			presentBranch.setChildAdded(false);
		}
	}

	/**
	 * Move forward from present node.
	 */
	public DSSObject goForward() {
		// if we have added children to the present node since we opened it push them on
		if (presentBranch != null)
			if (presentBranch.isChildAdded())
				pushAllChildren();

		// if the forward stack is empty push stuff on it to get started
		if (forwardStack.size() == 0)
			pushAllChildren();

		presentBranch = (CktTreeNode) forwardStack.pop();  // FIXME Implement generics
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
	 * Returns pointer to first cktobject.
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
			if (presentBranch.getCktObject() == p)
				break;
			temp = goForward();
		}
		forwardStack.clear();
	}

	/**
	 * Start forward search at the present location (can also use active).
	 */
	public void startHere() {
		forwardStack.clear();
		if (presentBranch != null)
			forwardStack.push((DSSObject) presentBranch);  // FIXME Implement generics
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

	/*
	 * Utility code for building a connected tree starting from a circuit element.
	 */

	/**
	 * Sources are excluded from the PC element list, so this is a brute-force search.
	 */
	private static void getSourcesConnectedToBus(int busNum, CktTree branchList, boolean analyze) {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (PCElement psrc : ckt.getSources()) {  // sources are special PC elements
			if (psrc.isEnabled()) {
				if (analyze || !psrc.isChecked()) {
					if (psrc.getTerminals()[0].getBusRef() == busNum) {  // ? connected to this bus ?
						if (analyze) {
							psrc.setIsolated(false);
							branchList.getPresentBranch().setDangling(false);
						}

						if (!psrc.isChecked()) {
							branchList.setNewObject(psrc);
							psrc.setChecked(true);
						}
					}
				}
			}
		}
	}

	private static void getPCElementsConnectedToBus(List<CktElement> adjLst, CktTree branchList, boolean analyze) {
		CktElement p;

		for (int i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled()) {
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


	private static void findAllChildBranches(List<CktElement> adjLst, int busNum, CktTree branchList,
			boolean analyze, CktElement activeBranch) {
		int i, j;
		CktElement p;

		for (i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled() && p != activeBranch) {
				if (analyze || !p.isChecked()) {
					if (!Utilities.isShuntElement(p) && Utilities.allTerminalsClosed(p)) {
						for (j = 0; j < p.getNTerms(); j++) {
							if (busNum == p.getTerminals()[j].getBusRef()) {
								if (analyze) {
									p.setIsolated(false);
									branchList.getPresentBranch().setDangling(false);
									if (p.isChecked() && branchList.getLevel() > 0) {
										branchList.getPresentBranch().setLoopedHere(true);
										branchList.getPresentBranch().setLoopLineObj(p);
										if (Utilities.isLineElement(p) && Utilities.isLineElement(activeBranch))
											if (Utilities.checkParallel(activeBranch, p))
												branchList.getPresentBranch().setParallel(true);
									}
								}
								if (!p.isChecked()) {
									branchList.addNewChild(p, busNum, j);
									p.getTerminals()[j].setChecked(true);
									p.setChecked(true);
									break;  /* for */
								}
							}
						}
					}
				}
			}
		}
	}

	private static void getShuntPDElementsConnectedToBus(List<CktElement> adjLst, CktTree branchList, boolean analyze) {
		CktElement p;
		for (int i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled() && Utilities.isShuntElement(p)) {
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
		int testBusNum;
		CktTree branchList;
		int iTerm;
		CktElement testBranch, testElement;
		List[] lstPD, lstPC;

		Circuit ckt = DSSGlobals.activeCircuit;

		lstPD = ckt.getBusAdjacentPDLists();
		lstPC = ckt.getBusAdjacentPCLists();

		branchList = new CktTreeImpl();
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
			for (iTerm = 0; iTerm < testBranch.getNTerms(); iTerm++) {
				if (!testBranch.getTerminals()[iTerm].isChecked()) {
					// now find all PC elements connected to the bus on this end of branch
					// attach them as generic objects to cktTree node.
					testBusNum = testBranch.getTerminals()[iTerm].getBusRef();
					branchList.getPresentBranch().setToBusReference(testBusNum);  // add this as a "to" bus reference
					if (testBusNum > 0) {
						ckt.getBuses()[testBusNum].setBusChecked(true);
						getSourcesConnectedToBus(testBusNum, branchList, analyze);
						getPCElementsConnectedToBus(lstPC[testBusNum], branchList, analyze);
						getShuntPDElementsConnectedToBus(lstPD[testBusNum], branchList, analyze);
						findAllChildBranches(lstPD[testBusNum], testBusNum, branchList, analyze, testBranch);
					}
				}
			}
			testBranch = (CktElement) branchList.goForward();
		}
		return branchList;
	}

	public static void buildActiveBusAdjacencyLists(List<PDElement>[] lstPD, List<PCElement>[] lstPC) {
		int i, j, nBus;
//		CktElement pCktElement;

		Circuit ckt = DSSGlobals.activeCircuit;

		nBus = ckt.getNumBuses();
		// Circuit.buses is effectively 1-based; bus 0 is ground   TODO Check zero based indexing
		lstPD = new List[nBus + 1];
		lstPC = new List[nBus + 1];
		for (i = 0; i < nBus; i++) {
			lstPD[i] = new ArrayList<PDElement>();  // default capacity should be enough
			lstPC[i] = new ArrayList<PCElement>();
		}

		for (CktElement pCktElement : ckt.getPCElements()) {
			if (pCktElement.isEnabled()) {
				i = pCktElement.getTerminals()[0].getBusRef();
				lstPC[i].add((PCElement) pCktElement);
			}
		}

		for (CktElement pCktElement : ckt.getPDElements()) {
			/* Put only eligible PD elements in the list */
			if (pCktElement.isEnabled()) {
				if (Utilities.isShuntElement(pCktElement)) {
					i = pCktElement.getTerminals()[0].getBusRef();
					lstPC[i].add((PCElement) pCktElement);
				} else if (Utilities.allTerminalsClosed(pCktElement))
					for (j = 0; j < pCktElement.getNTerms(); j++) {
						i = pCktElement.getTerminals()[j].getBusRef();
						lstPD[i].add((PDElement) pCktElement);
					}
			}
		}
	}

	public static void freeAndNilBusAdjacencyLists(List[] lstPD, List[] lstPC) {
		for (int i = 0; i < lstPD.length; i++) {
			lstPD[i] = null;
			lstPC[i] = null;
		}
		lstPD = null;
		lstPC = null;
	}

	public CktTreeNode getPresentBranch() {
		return presentBranch;
	}

	public void setPresentBranch(CktTreeNode branch) {
		presentBranch = branch;
	}

	public ZoneEndsList getZoneEndsList() {
		return zoneEndsList;
	}

	public void setZoneEndsList(ZoneEndsList list) {
		zoneEndsList = list;
	}

}

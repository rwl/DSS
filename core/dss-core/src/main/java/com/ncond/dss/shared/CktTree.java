package com.ncond.dss.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.XYCurveObj;

@Data
public class CktTree {

	@Data
	public class ZoneEndsList {

		private PointerList endNodeList;
		private int[] endBuses;

		protected int numEnds;

		public ZoneEndsList() {
			endNodeList = new PointerList(10);
			numEnds = 0;
			endBuses = null;
		}

		public void add(CktTreeNode node, int endBusRef) {
			numEnds += 1;
			endNodeList.add(node);
			endBuses = Util.resizeArray(endBuses, numEnds);
			endBuses[numEnds - 1] = endBusRef;
		}

		public int get(int i, CktTreeNode node) {
			node = (CktTreeNode) endNodeList.get(i);
			return endBuses[i];
		}

	}

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
		if (firstNode == null)
			firstNode = presentBranch;
	}

	public void addNewChild(DSSObject value, int busRef, int terminalNo) {
		CktTreeNode tempNode;

		if (presentBranch == null) {
			setNew(value);
		} else {
			tempNode = new CktTreeNode(presentBranch, value);

			tempNode.setFromBusReference(busRef);
			tempNode.setFromTerminal(terminalNo);

			presentBranch.addChild(tempNode);
		}
	}

	/**
	 * Adds a pointer to an object to be associated with the current node.
	 */
	public void setNewObject(DSSObject value) {
		if (presentBranch != null)
			presentBranch.addObject(value);
	}

	private void pushAllChildren() {
		CktTreeNode pChild;

		if (presentBranch != null) {
			// push all children of present node onto stack
			pChild = presentBranch.getFirstChild();
			while (pChild != null) {
				forwardStack.push(pChild);
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
			forwardStack.push(presentBranch);
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
		Circuit ckt = DSS.activeCircuit;

		for (PCElement psrc : ckt.getSources()) {  // sources are special PC elements
			if (psrc.isEnabled()) {
				if (analyze || !psrc.isChecked()) {
					if (psrc.getTerminal(0).getBusRef() == busNum) {  // ? connected to this bus ?
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

		for (int i = 0; i < adjLst.size(); i++) {
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

		for (i = 0; i < adjLst.size(); i++) {
			p = adjLst.get(i);
			if (p.isEnabled() && p != activeBranch) {
				if (analyze || !p.isChecked()) {
					if (!Util.isShuntElement(p) && Util.allTerminalsClosed(p)) {
						for (j = 0; j < p.getNumTerms(); j++) {
							if (busNum == p.getTerminal(j).getBusRef()) {
								if (analyze) {
									p.setIsolated(false);
									branchList.getPresentBranch().setDangling(false);
									if (p.isChecked() && branchList.getLevel() > 0) {
										branchList.getPresentBranch().setLoopedHere(true);
										branchList.getPresentBranch().setLoopLineObj(p);
										if (Util.isLineElement(p) && Util.isLineElement(activeBranch))
											if (Util.checkParallel(activeBranch, p))
												branchList.getPresentBranch().setParallel(true);
									}
								}
								if (!p.isChecked()) {
									branchList.addNewChild(p, busNum, j);
									p.getTerminal(j).setChecked(true);
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
		for (int i = 0; i < adjLst.size(); i++) {
			p = adjLst.get(i);
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
		int testBusNum;
		CktTree branchList;
		int iTerm;
		CktElement testBranch, testElement;
		List[] lstPD, lstPC;

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
					testBusNum = testBranch.getTerminal(iTerm).getBusRef();
					branchList.getPresentBranch().setToBusReference(testBusNum);  // add this as a "to" bus reference
					if (testBusNum > 0) {
						ckt.getBus(testBusNum).setBusChecked(true);
						getSourcesConnectedToBus(testBusNum, branchList, analyze);
						getPCElementsConnectedToBus(lstPC[ testBusNum ], branchList, analyze);
						getShuntPDElementsConnectedToBus(lstPD[ testBusNum ], branchList, analyze);
						findAllChildBranches(lstPD[ testBusNum ], testBusNum, branchList, analyze, testBranch);
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

		Circuit ckt = DSS.activeCircuit;

		nBus = ckt.getNumBuses();
		// Circuit.buses is effectively 1-based; bus 0 is ground
		lstPD = new List[nBus + 1];
		lstPC = new List[nBus + 1];
		for (i = 0; i < nBus; i++) {
			lstPD[i] = new ArrayList<PDElement>();  // default capacity should be enough
			lstPC[i] = new ArrayList<PCElement>();
		}

		for (CktElement pCktElement : ckt.getPCElements()) {
			if (pCktElement.isEnabled()) {
				i = pCktElement.getTerminal(0).getBusRef();
				lstPC[i].add((PCElement) pCktElement);
			}
		}

		for (CktElement pCktElement : ckt.getPDElements()) {
			/* Put only eligible PD elements in the list */
			if (pCktElement.isEnabled()) {
				if (Util.isShuntElement(pCktElement)) {
					i = pCktElement.getTerminal(0).getBusRef();
					lstPC[i].add((PCElement) pCktElement);
				} else if (Util.allTerminalsClosed(pCktElement))
					for (j = 0; j < pCktElement.getNumTerms(); j++) {
						i = pCktElement.getTerminal(j).getBusRef();
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

//	public CktTreeNode getPresentBranch() {
//		return presentBranch;
//	}
//
//	public void setPresentBranch(CktTreeNode branch) {
//		presentBranch = branch;
//	}
//
//	public ZoneEndsList getZoneEndsList() {
//		return zoneEndsList;
//	}
//
//	public void setZoneEndsList(ZoneEndsList list) {
//		zoneEndsList = list;
//	}

}

package com.epri.dss.shared.impl;

import java.util.ArrayList;
import java.util.List;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.PStack;
import com.epri.dss.shared.PointerList;

public class CktTreeImpl implements CktTree {

	public class ZoneEndsList {

		private PointerList EndNodeList;
		private int[] EndBuses;

		protected int NumEnds;

		public ZoneEndsList() {
			EndNodeList = new PointerListImpl(10);
			NumEnds = 0;
			EndBuses = null;
		}

		public void add(CktTreeNode Node, int EndBusRef) {
			NumEnds += 1;
			EndNodeList.add(Node);
			EndBuses = (int[]) Utilities.resizeArray(EndBuses, NumEnds);
			EndBuses[NumEnds - 1] = EndBusRef;  // TODO Check zero based indexing
		}

		public int get(int i, CktTreeNode Node) {
			Node = (CktTreeNode) EndNodeList.get(i);  // FIXME Make generic
			return EndBuses[i];
		}

		public int getNumEnds() {
			return NumEnds;
		}

		public void setNumEnds(int numEnds) {
			NumEnds = numEnds;
		}

	}

	private CktTreeNode FirstNode;
	private PStack ForwardStack;

	protected CktTreeNode PresentBranch;
	protected ZoneEndsList ZoneEndsList;

	public CktTreeImpl() {
		super();
		FirstNode = null;
		PresentBranch = null;
		ZoneEndsList = new ZoneEndsList();
		ForwardStack = new PStackImpl(20);
	}

	/**
	 * Adds child and makes it present.
	 */
	public void setNew(DSSObject Value) {
		PresentBranch = new CktTreeNodeImpl(PresentBranch, Value);
		if (FirstNode == null)
			FirstNode = PresentBranch;
	}

	public void addNewChild(DSSObject Value, int BusRef, int TerminalNo) {
		if (PresentBranch == null) {
			setNew(Value);
		} else {
			CktTreeNode TempNode = new CktTreeNodeImpl(PresentBranch, Value);

			TempNode.setFromBusReference(BusRef);
			TempNode.setFromTerminal(TerminalNo);

			PresentBranch.addChild(TempNode);
		}
	}

	/**
	 * Adds a pointer to an object to be associated with the current node.
	 */
	public void setNewObject(DSSObject Value) {
		if (PresentBranch != null) {
			PresentBranch.addObject(Value);
		}
	}

	private void pushAllChildren() {
		if (PresentBranch != null) {
			// Push all children of present node onto stack
			CktTreeNode pChild = PresentBranch.getFirstChild();
			while (pChild != null) {
				ForwardStack.push((DSSObject) pChild);  // FIXME Implement generics
				pChild = PresentBranch.getNextChild();
			}
			PresentBranch.setChildAdded(false);
		}
	}

	/**
	 * Move forward from present node.
	 */
	public DSSObject GoForward() {
		// If we have added children to the present node since we opened it push em on
		if (PresentBranch != null)
			if (PresentBranch.isChildAdded())
				pushAllChildren();

		// If the forward stack is empty push stuff on it to get started
		if (ForwardStack.size() == 0)
			pushAllChildren();

		PresentBranch = (CktTreeNode) ForwardStack.pop();  // FIXME Implement generics
		pushAllChildren();  // push all children of latest
		if (PresentBranch != null) {
			return PresentBranch.getCktObject();
		} else {
			return null;
		}
	}

	/**
	 * Move backward from present node and reset forward stack.
	 */
	public DSSObject GoBackward() {
		PresentBranch = PresentBranch.getParent();
		ForwardStack.clear();
		if (PresentBranch != null) {
			return PresentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public Object getParent() {
		if (PresentBranch.getParent() != null) {
			return PresentBranch.getParent().getCktObject();
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
		PresentBranch = FirstNode;
		ForwardStack.clear();
		pushAllChildren();
		if (PresentBranch != null) {
			return (DSSObject) PresentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public Object getFirstObject() {
		if (PresentBranch != null) {
			return PresentBranch.getFirstObject();
		} else {
			return null;
		}
	}

	public Object getNextObject() {
		if (PresentBranch != null) {
			return PresentBranch.getNextObject();
		} else {
			return null;
		}
	}

	public Object getActive() {
		if (PresentBranch != null) {
			return PresentBranch.getCktObject();
		} else {
			return null;
		}
	}

	public void setActive(DSSObject p) {
		DSSObject Temp = getFirst();
		while (Temp != null) {
			if (PresentBranch.getCktObject() == p) break;
			Temp = GoForward();
		}
		ForwardStack.clear();
	}

	/**
	 * Start forward search at the present location (can also use active).
	 */
	public void startHere() {
		ForwardStack.clear();
		if (PresentBranch != null)
			ForwardStack.push((DSSObject) PresentBranch);  // FIXME Implement generics
	}

	/**
	 * Get lexical level of present node.
	 */
	public int getLevel() {
		if (PresentBranch != null) {
			return PresentBranch.getLexicalLevel();
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
	private static void getSourcesConnectedToBus(int BusNum, CktTree BranchList, boolean Analyze) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (PCElement psrc : ckt.getSources()) {  // Sources are special PC elements
			if (psrc.isEnabled()) {
				if (Analyze || (!psrc.isChecked())) {
					if (psrc.getTerminals()[0].getBusRef() == BusNum) {  // ? Connected to this bus ?
						if (Analyze) {
							psrc.setIsIsolated(false);
							BranchList.getPresentBranch().setIsDangling(false);
						}

						if (!psrc.isChecked()) {
							BranchList.setNewObject(psrc);
							psrc.setChecked(true);
						}
					}
				}
			}
		}
	}

	private static void getPCElementsConnectedToBus(List<CktElement> adjLst, CktTree BranchList, boolean Analyze) {
		CktElement p;

		for (int i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled()) {
				if (Analyze) {
					p.setIsIsolated(false);
					BranchList.getPresentBranch().setIsDangling(false);
				}
				if (!p.isChecked()) {
					BranchList.setNewObject(p);
					p.setChecked(true);
				}
			}
		}
	}


	private static void findAllChildBranches(List<CktElement>adjLst, int BusNum, CktTree BranchList,
			boolean Analyze, CktElement ActiveBranch) {
		int i, j;
		CktElement p;

		for (i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled() && (p != ActiveBranch)) {
				if (Analyze || (!p.isChecked())) {
					if (!Utilities.isShuntElement(p) && Utilities.allTerminalsClosed(p)) {
						for (j = 0; j < p.getNTerms(); j++) {
							if (BusNum == p.getTerminals()[j].getBusRef()) {
								if (Analyze) {
									p.setIsIsolated(false);
									BranchList.getPresentBranch().setIsDangling(false);
									if (p.isChecked() && (BranchList.getLevel() > 0)) {
										BranchList.getPresentBranch().setIsLoopedHere(true);
										BranchList.getPresentBranch().setLoopLineObj(p);
										if (Utilities.isLineElement(p) && Utilities.isLineElement(ActiveBranch))
											if (Utilities.checkParallel(ActiveBranch, p))
												BranchList.getPresentBranch().setIsParallel(true);
									}
								}
								if (!p.isChecked()) {
									BranchList.addNewChild(p, BusNum, j);
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

	private static void getShuntPDElementsConnectedToBus(List<CktElement> adjLst, CktTree BranchList, boolean Analyze) {
		CktElement p;
		for (int i = 0; i < adjLst.size() - 1; i++) {
			p = adjLst.get(i);
			if (p.isEnabled() && Utilities.isShuntElement(p)) {
				if (Analyze) {
					p.setIsIsolated(false);
					BranchList.getPresentBranch().setIsDangling(false);
				}
				if (!p.isChecked()) {
					BranchList.setNewObject(p);
					p.setChecked(true);
				}
			}
		}
	}

	/**
	 * Build a tree of connected elements beginning at StartElement.
	 * Analyze = true; will check for loops, isolated components, and parallel lines (takes longer)
	 */
	public static CktTree getIsolatedSubArea(CktElement StartElement, boolean Analyze) {
		int TestBusNum;
		CktTree BranchList;
		int iTerm;
		CktElement TestBranch, TestElement;
		List[] lstPD, lstPC;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		lstPD = ckt.getBusAdjacentPDLists();
		lstPC = ckt.getBusAdjacentPCLists();

		BranchList = new CktTreeImpl();
		TestElement = StartElement;

		BranchList.setNew(TestElement);
		if (Analyze)
			TestElement.setIsIsolated(false);
		TestElement.setLastTerminalChecked(0);  // We'll check things connected to both sides

		// Check off this element so we don't use it again
		TestElement.setChecked(true);

		// Now start looking for other branches
		// Finds any branch connected to the TestBranch and adds it to the list
		// Goes until end of circuit, another energy meter, an open terminal, or disabled device.
		TestBranch = TestElement;
		while (TestBranch != null) {
			for (iTerm = 0; iTerm < TestBranch.getNTerms(); iTerm++) {
				if (!TestBranch.getTerminals()[iTerm].isChecked()) {
					// Now find all PC elements connected to the bus on this end of branch
					// attach them as generic objects to cktTree node.
					TestBusNum = TestBranch.getTerminals()[iTerm].getBusRef();
					BranchList.getPresentBranch().setToBusReference(TestBusNum);  // Add this as a "to" bus reference
					if (TestBusNum > 0) {
						ckt.getBuses()[TestBusNum].setBusChecked(true);
						getSourcesConnectedToBus(TestBusNum, BranchList, Analyze);
						getPCElementsConnectedToBus(lstPC[TestBusNum], BranchList, Analyze);
						getShuntPDElementsConnectedToBus(lstPD[TestBusNum], BranchList, Analyze);
						findAllChildBranches(lstPD[TestBusNum], TestBusNum, BranchList, Analyze, TestBranch);
					}
				}
			}
			TestBranch = (CktElement) BranchList.GoForward();
		}
		return BranchList;
	}

	public static void buildActiveBusAdjacencyLists(List<PDElement>[] lstPD, List<PCElement>[] lstPC) {
		int i, j, nBus;
//		CktElement pCktElement;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		nBus = ckt.getNumBuses();
		// Circuit.Buses is effectively 1-based; bus 0 is ground   TODO Check zero based indexing
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
			/* Put only eligible PDElements in the list */
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
		return PresentBranch;
	}

	public void setPresentBranch(CktTreeNode presentBranch) {
		PresentBranch = presentBranch;
	}

	public ZoneEndsList getZoneEndsList() {
		return ZoneEndsList;
	}

	public void setZoneEndsList(ZoneEndsList zoneEndsList) {
		ZoneEndsList = zoneEndsList;
	}

}

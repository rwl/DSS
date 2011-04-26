package com.epri.dss.shared.impl;

import java.util.ArrayList;
import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.CktTreeNode;

public class CktTreeImpl implements CktTree {

	public class ZoneEndsList {

		private PointerListImpl EndNodeList;
		private int[] EndBuses;

		protected int NumEnds;

		public ZoneEndsList() {
			// TODO Auto-generated constructor stub
		}

		public int getNumEnds() {
			return NumEnds;
		}

		public void setNumEnds(int numEnds) {
			NumEnds = numEnds;
		}

		public void Add(CktTreeNode Node, int EndBusRef) {

		}

		public int Get(int i, CktTreeNode Node) {
			return 0;
		}

	}

	private CktTreeNode FirstNode;
//	private Pstack ForwardStack;

	protected CktTreeNode PresentBranch;
	protected ZoneEndsList ZoneEndsList;

	public CktTreeImpl() {
		// TODO Auto-generated constructor stub
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

	/* Adds a pointer to an object to be associated with the current node */
	public void setNewObject(Object Value) {

	}

	private void pushAllChildren() {

	}

	/* Start Forward Search at the present location (can also use active) */
	public void startHere() {

	}

	public void addNewChild(Object Value, int BusRef, int TerminalNo) {

	}

	/* Adds Child and makes it present */
	public void setNew(Object Value) {

	}

	/* Returns pointer to first cktobject */
	public Object getFirst() {
		return null;
	}

	public Object getParent() {
		return null;
	}

	public Object getFirstObject() {
		return null;
	}

	public Object getNextObject() {
		return null;
	}

	public Object GoForward() {
		return null;
	}

	public Object GoBackward() {
		return null;
	}

	public Object getActive() {
		return null;
	}

	public void setActive(Object Value) {

	}

	/* Get lexical level of present node */
	public int getLevel() {
		return 0;
	}

	// build a tree of connected elements beginning at StartElement
	// Analyze = TRUE will check for loops, isolated components, and parallel lines (takes longer)
	public static CktTree getIsolatedSubArea(CktElement StartElement, boolean Analyze) {
		return null;
	}

	public static void buildActiveBusAdjacencyLists(List[] lstPD, List[] lstPC) {

	}

	public static void freeAndNilBusAdjacencyLists(List[] lstPD, List[] lstPC) {

	}

}

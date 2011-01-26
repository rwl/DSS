package com.epri.dss.shared.impl;

import com.epri.dss.shared.CktTree;

public class CktTreeImpl implements CktTree {

	public class CktTreeNode {

		private PointerListImpl ChildBranches;
		private int NumToBuses, ToBusPtr;
		private int[] ToBusList;

		public CktTreeNode() {
			// TODO Auto-generated constructor stub
		}

		public CktTreeNode Get_FirstChildBranch() {
			return null;
		}

		public CktTreeNode Get_NextChildBranch() {
			return null;
		}

		public CktTreeNode Get_Parent() {
			return null;
		}

		public void AddChildBranch(CktTreeNode Value) {

		}

		public void AddShuntObject(Object Value) {

		}

		public int Get_NumChildBranches() {
			return 0;
		}

		public int Get_NumShuntObjects() {
			return 0;
		}

		public int Get_ToBusReference() {
			return 0;
		}

		public void Set_ToBusReference(int Value) {

		}

		public Object Get_FirstShuntObject() {
			return null;
		}

		public Object Get_NextObject() {
			return null;
		}

	}

	public class ZoneEndsList {

		private PointerListImpl EndNodeList;
		private int[] EndBuses;

		public int NumEnds;

		public ZoneEndsList() {
			// TODO Auto-generated constructor stub
		}

		public void Add(CktTreeNode Node, int EndBusRef) {

		}

		public int Get(int i, CktTreeNode Node) {
			return 0;
		}

	}

	private CktTreeNode FirstNode;
//	private Pstack ForwardStack;

	public CktTreeNode PresentBranch;
	public ZoneEndsList ZoneEndsList;

	public CktTreeImpl() {
		// TODO Auto-generated constructor stub
	}

	/* Adds a pointer to an object to be associated with the current node */
	public void Set_NewObject(Object Value) {

	}

	private void PushAllChildren() {

	}

	/* Start Forward Search at the present location (can also use active) */
	public void StartHere() {

	}

	public void AddNewChild(Object Value, int BusRef, int TerminalNo) {

	}

	/* Adds Child and makes it present */
	public void Set_New(Object Value) {

	}

	/* Returns pointer to first cktobject */
	public Object Get_First() {
		return null;
	}

	public Object Get_Parent() {
		return null;
	}

	public Object Get_FirstObject() {
		return null;
	}

	public Object Get_NextObject() {
		return null;
	}

	public Object GoForward() {
		return null;
	}

	public Object GoBackward() {
		return null;
	}

	public Object Get_Active() {
		return null;
	}

	public void Set_Active(Object Value) {

	}

	/* Get lexical level of present node */
	public int Get_Level() {
		return 0;
	}

}

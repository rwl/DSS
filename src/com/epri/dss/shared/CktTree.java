package com.epri.dss.shared;

public class CktTree {

	private CktTreeNode FirstNode;
//	private Pstack ForwardStack;

	public CktTreeNode PresentBranch;
	public ZoneEndsList ZoneEndsList;

	public CktTree() {
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

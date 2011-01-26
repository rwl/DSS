package com.epri.dss.shared;

public class CktTreeNode {

	private PointerList ChildBranches;
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

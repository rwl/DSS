package com.epri.dss.shared;

public interface CktTree {

	/* Adds a pointer to an object to be associated with the current node */
	public void Set_NewObject(Object Value);

	/* Start Forward Search at the present location (can also use active) */
	public void StartHere();

	public void AddNewChild(Object Value, int BusRef, int TerminalNo);

	/* Adds Child and makes it present */
	public void Set_New(Object Value);

	/* Returns pointer to first cktobject */
	public Object Get_First();

	public Object Get_Parent();

	public Object Get_FirstObject();

	public Object Get_NextObject();

	public Object GoForward();

	public Object GoBackward();

	public Object Get_Active();

	public void Set_Active(Object Value);

	/* Get lexical level of present node */
	public int Get_Level();
}

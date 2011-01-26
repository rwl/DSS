package com.epri.dss.shared;

public interface PointerList {

	public Object Get_First();

	public Object Get_Next();

	public Object Get_Active();

	public void Set_New(Object Value);

	public void Clear();

	/* Returns index of item */
	public int Add(Object p);

	public Object Get(int i);

	public int Get_ListSize();

	public int Get_ActiveIndex();
}

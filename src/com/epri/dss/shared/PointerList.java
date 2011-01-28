package com.epri.dss.shared;

public interface PointerList {

	Object Get_First();

	Object Get_Next();

	Object Get_Active();

	void Set_New(Object Value);

	void Clear();

	/* Returns index of item */
	int Add(Object p);

	Object Get(int i);

	int Get_ListSize();

	int Get_ActiveIndex();

}

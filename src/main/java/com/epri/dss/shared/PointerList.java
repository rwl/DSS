package com.epri.dss.shared;

public interface PointerList {

	Object getFirst();

	Object getNext();

	Object getActive();

	void setNew(Object Value);

	void clear();

	/* Returns index of item */
	int add(Object p);

	Object get(int i);

	int size();

	int getActiveIndex();

}

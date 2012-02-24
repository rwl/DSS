package com.ncond.dss.shared;

public interface PointerList {

	Object getFirst();

	Object getNext();

	Object getActive();

	void clear();

	/** Returns index of item */
	int add(Object p);

	Object get(int i);

	int size();

	int getActiveIndex();

}

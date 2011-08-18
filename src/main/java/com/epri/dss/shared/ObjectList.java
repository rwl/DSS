package com.epri.dss.shared;

public interface ObjectList<T> {

	T getFirst();

	T getNext();

	T getActive();

	/** Synonymous with add() */
	void setNew(T value);

	void clear();

	/** Returns index of item */
	int add(T p);

	T get(int i);

	int size();

	int getActiveIndex();

}

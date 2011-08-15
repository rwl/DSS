package com.epri.dss.shared.impl;

import com.epri.dss.shared.PointerList;

public class PointerListImpl implements PointerList {

	private int numInList;

	private int maxAllocated;

	private int activeItem;

	private Object[] list;

	private int incrementSize;

	public PointerListImpl(int size) {
		super();
		maxAllocated = size;
		// default size & increment
		if (maxAllocated <= 0) maxAllocated = 10;
		list = new Object[maxAllocated];
		numInList = 0;
		activeItem = 0;
		// increment is equal to original allocation
		incrementSize = maxAllocated;
	}

	public Object getFirst() {
		if (numInList > 0) {
		activeItem = 0;
		return list[activeItem];
		} else {
			activeItem = -1;
			return null;
		}
	}

	public Object getNext() {
		if (numInList > 0) {
		activeItem += 1;
		if (activeItem > numInList) {
			activeItem = numInList;
			return null;
		} else {
			return list[activeItem];
		}
		} else {
			activeItem = -1;
			return null;
		}
	}

	public Object getActive() {
		if ((activeItem > 0) && (activeItem <= numInList)) {
			return get(activeItem);
		} else {
			return null;
		}
	}

	public void setNew(Object value) {
		add(value);
	}

	public void clear() {
		activeItem = -1;
		numInList = 0;
	}

	/** Returns index of item */
	public int add(Object p) {
		numInList += 1;
		if (numInList > maxAllocated) {
			maxAllocated = maxAllocated + incrementSize;
			// FIXME: Resize array
			list = new Object[maxAllocated];
		}
		list[numInList] = p;
		activeItem = numInList;
		return numInList;
	}

	public Object get(int i) {
		if ((i < 1) || (i > numInList)) {
			return null;
		} else {
			activeItem = i;
			return list[i];
		}
	}

	public int size() {
		return numInList;
	}

	public int getActiveIndex() {
		return activeItem;
	}

}

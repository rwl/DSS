package com.epri.dss.shared.impl;

import com.epri.dss.shared.PointerList;

public class PointerListImpl implements PointerList {

	private int numInList;

	private int maxAllocated;

	private int activeItem;

	private Object[] list;

	private int incrementSize;

	public PointerListImpl() {
		this(0);
	}

	public PointerListImpl(int size) {
		super();
		maxAllocated = size;
		// default size & increment
		if (maxAllocated <= 0)
			maxAllocated = 10;
		list = new Object[maxAllocated];
		numInList = 0;
		activeItem = -1;
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
			if (activeItem >= numInList) {
				activeItem = numInList - 1;
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
		if (activeItem >= 0 && activeItem < numInList) {
			return get(activeItem);
		} else {
			return null;
		}
	}

	public void clear() {
		activeItem = -1;
		numInList = 0;
	}

	/** Returns index of item */
	public int add(Object p) {
		Object[] newList;

		numInList += 1;
		if (numInList > maxAllocated) {
			maxAllocated = maxAllocated + incrementSize;
			newList = new Object[maxAllocated];
			System.arraycopy(list, 0, newList, 0, list.length);
			list = newList;
		}
		list[numInList - 1] = p;
		activeItem = numInList - 1;
		return numInList;
	}

	public Object get(int i) {
		if (i < 0 || i >= numInList) {
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
package com.epri.dss.shared.impl;

import com.epri.dss.shared.StackBase;

public class StackBaseImpl implements StackBase {

	protected int numItems, increment, maxItems;

	public StackBaseImpl(int initSize) {
		super();
		maxItems = initSize;
		increment = initSize;
		numItems = 0;
	}

	public void clear() {
		numItems = 0;
	}

	public int size() {
		return numItems;
	}

}

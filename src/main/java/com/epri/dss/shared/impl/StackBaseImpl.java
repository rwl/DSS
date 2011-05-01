package com.epri.dss.shared.impl;

import com.epri.dss.shared.StackBase;

public class StackBaseImpl implements StackBase {

	protected int NumItems, Increment, MaxItems;

	public StackBaseImpl(int initSize) {
		super();
		this.MaxItems = initSize;
		this.Increment = initSize;
		this.NumItems = 0;
	}

	public void clear() {
		NumItems = 0;
	}

	public int size() {
		return NumItems;
	}

}

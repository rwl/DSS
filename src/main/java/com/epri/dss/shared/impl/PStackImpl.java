package com.epri.dss.shared.impl;

import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.PStack;

public class PStackImpl extends StackBaseImpl implements PStack {

	private DSSObject[] items;

	public PStackImpl(int initSize) {
		super(initSize);
		this.items = new DSSObject[initSize];
	}

	public void push(DSSObject p) {
		numItems += 1;
		if (numItems > maxItems) {
			maxItems += increment;
			Utilities.resizeArray(items, maxItems);
		}
		items[numItems] = p;
	}

	public DSSObject pop() {
		DSSObject Result;
		if (numItems > 0) {
			Result = items[numItems - 1];
			numItems -= 1;
		} else {
			Result = null;
		}
		return Result;
	}

}

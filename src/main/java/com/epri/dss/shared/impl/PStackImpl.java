package com.epri.dss.shared.impl;

import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.PStack;

public class PStackImpl extends StackBaseImpl implements PStack {

	private DSSObject[] Items;

	public PStackImpl(int initSize) {
		super(initSize);
		this.Items = new DSSObject[initSize];
	}

	public void push(DSSObject p) {
		NumItems += 1;
		if (NumItems > MaxItems) {
			MaxItems += Increment;
			Utilities.resizeArray(Items, MaxItems);
		}
		Items[NumItems] = p;
	}

	public DSSObject pop() {
		DSSObject Result;
		if (NumItems > 0) {
			Result = Items[NumItems - 1];
			NumItems -= 1;
		} else {
			Result = null;
		}
		return Result;
	}

}

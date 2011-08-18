package com.epri.dss.shared.impl;

import com.epri.dss.shared.PStack;

public class PStackImpl<T> extends StackBaseImpl implements PStack<T> {

	private T[] items;

	public PStackImpl(int initSize) {
		super(initSize);
		items = (T[]) new Object[initSize];
	}

	public void push(T p) {
		T[] newItems;
		int size, l;

		numItems += 1;
		if (numItems > maxItems) {
			// resize array
			maxItems += increment;
			newItems = (T[]) new Object[maxItems];
			size = items.length;
			l = Math.min(size, maxItems);
			if (l > 0)
				System.arraycopy(items, 0, newItems, 0, l);
			items = newItems;
		}
		items[numItems] = p;
	}

	public T pop() {
		T result;
		if (numItems > 0) {
			result = items[numItems - 1];
			numItems -= 1;
		} else {
			result = null;
		}
		return result;
	}

}

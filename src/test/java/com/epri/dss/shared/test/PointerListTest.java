package com.epri.dss.shared.test;

import com.epri.dss.shared.PointerList;
import com.epri.dss.shared.impl.PointerListImpl;

import junit.framework.TestCase;

public class PointerListTest extends TestCase {

	public void testAdd() {
		PointerList pl = new PointerListImpl();

		try {
			pl.add( new Object() );
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSize() {
		int i, n = 3;

		PointerList pl = new PointerListImpl();

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		assertEquals(n, pl.size());
	}

	public void testGetFirst() {
		int i, n = 3;
		Object first;

		PointerList pl = new PointerListImpl();

		first = new Object();
		pl.add(first);

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		assertEquals(first, pl.getFirst());
	}

	public void testGetNext() {
		int i, n = 3;
		Object next;

		PointerList pl = new PointerListImpl();

		pl.add(new Object());
		next = new Object();
		pl.add(next);

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		pl.getFirst();
		assertEquals(next, pl.getNext());
	}

	public void testGetActive() {
		int i, n = 3;
		Object first, last;

		PointerList pl = new PointerListImpl();

		first = new Object();
		pl.add(first);

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		last = new Object();
		pl.add(last);

		assertEquals(last, pl.getActive());

		pl.getFirst();
		assertEquals(first, pl.getActive());
	}

	public void testGetActiveIndex() {
		int i, n = 3;
		Object first, last;

		PointerList pl = new PointerListImpl();

		first = new Object();
		pl.add(first);

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		last = new Object();
		pl.add(last);

		assertEquals(n + 1, pl.getActiveIndex());

		pl.getFirst();
		assertEquals(0, pl.getActiveIndex());
	}

	public void testGet() {
		int i, n = 3;
		Object first;

		PointerList pl = new PointerListImpl();

		first = new Object();
		pl.add(first);

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		assertEquals(first, pl.get(0));
	}

	public void testClear() {
		int i, n = 3;

		PointerList pl = new PointerListImpl();

		for (i = 0; i < n; i++)
			pl.add( new Object() );

		pl.clear();
		assertEquals(0, pl.size());
	}

}

package com.ncond.dss.shared.test;

import com.ncond.dss.shared.HashList;
import com.ncond.dss.shared.impl.HashListImpl;

import junit.framework.TestCase;

public class HashListTest extends TestCase {

	String[] months = new String[] {"January", "Febuary", "March",
			"April", "May", "June", "July", "August",
			"September", "October", "November", "December"};

	HashList hl;

	protected void setUp() throws Exception {
		hl = new HashListImpl(28);

		for (String month : months)
			hl.add(month);
		for (String month : months)
			hl.add(month);

		super.setUp();
	}

	public void testListSize() {
		assertEquals(24, hl.listSize());
	}

	public void testFind() {
		int idx;

		idx = hl.find("May");

		assertEquals(4, idx);

		idx = hl.find("June");

		assertEquals(5, idx);
	}

	public void testFindNext() {
		int idx;

		idx = hl.find("May");

		assertEquals(4, idx);

		idx = hl.findNext();

		assertEquals(16, idx);
	}

	public void testFindAbbrev() {
		int idx;

		idx = hl.findAbbrev("Dec");

		assertEquals(11, idx);
	}

	public void testGet() {
		int idx = 10;
		String month;

		month = hl.get(idx);

		assertEquals(months[idx].toLowerCase(), month.toLowerCase());
	}

	public void testExpand() {
		int idx;

		hl.expand(36);

		idx = hl.find("June");

		assertEquals(5, idx);
	}

	public void testClear() {
		hl.clear();

		assertEquals(0, hl.listSize());
	}

}

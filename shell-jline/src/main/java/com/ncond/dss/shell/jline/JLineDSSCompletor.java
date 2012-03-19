package com.ncond.dss.shell.jline;

import java.util.List;

import jline.Completor;

import com.ncond.dss.shell.DSSCompletor;

public class JLineDSSCompletor extends DSSCompletor implements Completor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int complete(final String buffer, final int cursor, final List candidates) {
		return super.complete(buffer, cursor, candidates);
	}

}

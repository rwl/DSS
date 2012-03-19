package com.ncond.dss.shell.jline;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.parser.impl.Parser;

import jline.Completor;

public class DSSCompletor implements Completor {

	@SuppressWarnings({"rawtypes", "unchecked"})
	public int complete(String buf, int cursor, List clist) {
		String buffer = (buf == null) ? "" : buf;

		// trim leading spaces
		while (buffer.startsWith(" ")) {
			buffer = buffer.replaceFirst("^ ", "");
			cursor -= 1;
		}

		// the list of candidates that will be completed
		Set<String> candidates = locateCandidates(buffer);


		if (candidates.size() == 0) {
			return cursor;
		} else if (candidates.size() == 1) {
			clist.add(candidates.iterator().next());

//			clist.set(0, ((String) clist.get(0)) + " ");
		} else {
			clist.addAll(candidates);
		}


		// the index of the completion is always from the beginning of the buffer
		return (clist.size() == 0) ? (-1) : 0;
	}

	private Set<String> locateCandidates(String buffer) {
		Parser parser = DSSGlobals.auxParser;

		Set<String> candidates = new TreeSet<String>();

		parser.setCmdString(buffer);

		String paramName = parser.getNextParam();
		String param = parser.makeString();

		System.out.println();
		System.out.println("Buffer: " + buffer);
		System.out.println("Param Name: " + paramName);
		System.out.println("Param: " + param);

		paramName = parser.getNextParam();
		param = parser.makeString();

		System.out.println("Param Name: " + paramName);
		System.out.println("Param: " + param);

		candidates.add(buffer);

		return candidates;

	}

}

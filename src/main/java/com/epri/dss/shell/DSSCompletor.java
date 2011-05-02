package com.epri.dss.shell;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jline.Completor;

public class DSSCompletor implements Completor {

	@SuppressWarnings({"rawtypes", "unchecked"})
	public int complete(String buf, int cursor, List clist) {
		String buffer = (buf == null) ? "" : buf;

		// Trim leading spaces.
		while (buffer.startsWith(" ")) {
			buffer = buffer.replaceFirst("^ ", "");
			cursor -= 1;
		}

		// The list of candidates that will be completed.
		Set<String> candidates = locateCandidates(buffer);


		if (candidates.size() == 0) {
			return cursor;
		} else if (candidates.size() == 1) {
			clist.add(candidates.iterator().next());

			clist.set(0, ((String) clist.get(0)) + " ");
		} else {
			clist.addAll(candidates);
		}


		// The index of the completion is always from the beginning of the buffer.
		return (clist.size() == 0) ? (-1) : 0;
	}

	private Set<String> locateCandidates(String buffer) {

		Set<String> candidates = new TreeSet<String>();

		System.out.println();
		System.out.println("Buffer: " + buffer);

		candidates.add(buffer);

		return candidates;

	}

}

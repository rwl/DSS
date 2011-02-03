package com.epri.dss.shared;

/**
 * This Hash list module is designed to make searches on arrays of strings more
 * efficient.  The list actually consists of several short linear lists.  When
 * a string is added, it is hashed and placed at the end of one of the lists.
 *
 * The list may by searched by string or by index.  When by string, the string
 * is hashed and the search is restricted to the resulting linear list.  When
 * by index, it simply goes to that index in the array of pointers that points
 * to the individual strings.
 *
 * All strings are saved in lower case and tested with case sensitivity.  This
 * actually makes the search insensitive to case because everything is lower
 * case.
 *
 * Modified 4/18/98 to allocate on demand.  That way, you can create it for a
 * certain number of hash lists, without caring about how many actual there
 * will be.
 *
 */
public interface HashList {

	int add(String S);

	/* repeat find for duplicate string in same hash list */
	int find(String S);

	int findAbbrev(String S);

	String get(int i);

	/* Expands number of elements */
	void expand(int NewSize);

	void dumpToFile(String fname);

	void clear();

	int listSize();

}

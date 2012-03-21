/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.Util;

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
public class HashList {

	private class SubList {
		public int nElem;
		public int nAllocated;
		public String[] str;
		public int[] idx;
	}

	private int numElementsAllocated;

	private int numLists;

	private int numElements;

	private String[] stringArray;

	private SubList[] listArray;

	private int allocationInc;

	private int lastFind;

	private int lastHash;

	private String lastSearchString;

	protected int initialAllocation;

	public HashList(int nelements) {
		super();
		numElements = 0;
		initialAllocation = nelements;
		stringArray = new String[numElements];

		numLists = (int) Math.round(Math.sqrt(nelements));
		int elementsPerList = nelements / numLists + 1;
		allocationInc = elementsPerList;
		if (numLists < 1) numLists = 1;  // make sure at least one list
		listArray = new SubList[numLists];
		for (int i = 0; i < numLists; i++) {
			listArray[i] = new SubList();
			/* Allocate initial sublists to zero; allocated on demand */
			listArray[i].str = new String[0];
			listArray[i].idx = new int[0];
			listArray[i].nAllocated = 0;
			listArray[i].nElem = 0;
		}
		numElementsAllocated = 0;
		lastFind = 0;
		lastHash = 0;
		lastSearchString = "";
	}

	public int getInitialAllocation() {
		return initialAllocation;
	}

	public void setInitialAllocation(int allocation) {
		initialAllocation = allocation;
	}

	public int listSize() {
		return numElements;
	}

	private void resizeSubList(SubList subList) {
		// resize by reasonable amount
		int oldAllocation = subList.nAllocated;
		subList.nAllocated = oldAllocation + allocationInc;
		subList.str = Util.resizeArray(subList.str, subList.nAllocated);
		subList.idx = Util.resizeArray(subList.idx, subList.nAllocated);
	}

	private int hash(String s) {
		int hashValue = s.hashCode();
		return Math.abs(hashValue % numLists);  // FIXME: negative modulus
	}

	/** Makes the linear string list larger. */
	private void resizeStrArray() {
		numElementsAllocated += allocationInc * numLists;
		stringArray = Util.resizeArray(stringArray, numElementsAllocated);
	}

	public int add(String s) {
		int hashValue;
		String ss;

		ss = s.toLowerCase();
		hashValue = hash(ss);

		numElements += 1;
		if (numElements > numElementsAllocated) resizeStrArray();

		listArray[hashValue].nElem += 1;
		if (listArray[hashValue].nElem > listArray[hashValue].nAllocated)
			resizeSubList(listArray[hashValue]);

		// make copy of whole string, lower case
		listArray[hashValue].str[listArray[hashValue].nElem - 1] = ss;

		// increments count to string
		stringArray[numElements - 1] = ss;

		listArray[hashValue].idx[listArray[hashValue].nElem - 1] = numElements - 1;

		return numElements;
	}

	/**
	 * Repeat find for duplicate string in same hash list.
	 */
	public int find(String s) {
		lastSearchString = s.toLowerCase();
		lastHash = hash(lastSearchString);
		int idx = -1;
		lastFind = -1;

		for (int i = 0; i < listArray[lastHash].nElem; i++) {
			if (lastSearchString.equalsIgnoreCase(listArray[lastHash].str[i])) {
				idx = listArray[lastHash].idx[i];
				lastFind = i;
				break;
			}
		}

		return idx;
	}

	/**
	 * Begins search in same list as last.
	 */
	public int findNext() {
		int idx = -1;  // default return
		lastFind += 1;    // start with next item in hash list

		if ((lastHash > 0) && (lastHash <= numLists)) {
			for (int i = lastFind; i < listArray[lastHash].nElem; i++)
				if (lastSearchString.equalsIgnoreCase(listArray[lastHash].str[i])) {
					idx = listArray[lastHash].idx[i];
					lastFind = i;
					break;
				}
		}

		return idx;
	}

	/**
	 * Makes a linear search and tests each string until a string is found
	 * that matches all the characters entered in s.
	 */
	public int findAbbrev(String s) {
		String test1, test2;

		int idx = -1;
		if (s.length() > 0) {
			test1 = s.toLowerCase();
			for (int i = 0; i < numElements; i++) {
				test2 = stringArray[i].substring(0, test1.length());
				if (test1.equalsIgnoreCase(test2)) {
					idx = i;
					break;
				}
			}
		}

		return idx;
	}

	public String get(int i) {
		return ((i >= 0) && (i < numElements)) ? stringArray[i] : "";
	}

	/**
	 * Expands number of elements.
	 *
	 * Creates a new set of string lists and copies the old strings
	 * into the new, hashing for the new number of lists.
	 */
	public void expand(int newSize) {
		String[] newStringArray;
		int newNumLists;
		int elementsPerList;
		SubList[] newListArray;
		int hashNum;
		String s;

		if (newSize > numElementsAllocated) {
			newStringArray = new String[newSize];
			newNumLists = (int) Math.round( Math.sqrt(newSize) );
			elementsPerList = newSize / newNumLists + 1;
			if (newNumLists < 1) newNumLists = 1;  // make sure at least one list
			newListArray = new SubList[newNumLists];
			for (int i = 0; i < newNumLists; i++) {
				newListArray[i] = new SubList();
				/* Allocate initial sublists */
				newListArray[i].str = new String[elementsPerList];
				newListArray[i].idx = new int[elementsPerList];
				newListArray[i].nAllocated = elementsPerList;
				newListArray[i].nElem = 0;
			}

			numLists = newNumLists;  // has to be set so hash function will work

			/* Add elements from old hash list to new hash list */

			for (int i = 0; i < numElements; i++) {
				s = stringArray[i];
				hashNum = hash(s);
				newListArray[hashNum].nElem += 1;
				if (newListArray[hashNum].nElem > newListArray[hashNum].nAllocated)
					resizeSubList(newListArray[hashNum]);

				newListArray[hashNum].str[newListArray[hashNum].nElem - 1] = s;
				newStringArray[numElements - 1] = newListArray[hashNum].str[newListArray[hashNum].nElem - 1];
				newListArray[hashNum].idx[newListArray[hashNum].nElem - 1] = i;
			}

			/* Assign new string and list pointers */

			stringArray = newStringArray;
			listArray = newListArray;
			numElementsAllocated = newSize;
		}
	}

	public void dumpToFile(String fname) {
		File f = new File(fname);
		try {
			PrintWriter pw = new PrintWriter(f);

			pw.printf("Number of hash lists = %d, Number of elements = %d", numLists, numElements);
			pw.println();

			pw.println();
			pw.println("Hash List Distribution");
			for (int i = 0; i < numLists; i++)
				pw.println(String.format("List = %d, Number of elements = %d", i, listArray[i].nElem));
			pw.println();

			for (int i = 0; i < numLists; i++) {
				pw.printf("List = %d, Number of elements = %d", i, listArray[i].nElem);
				pw.println();
				for (int j = 0; j < listArray[i].nElem; j++)
					pw.println("\"" + listArray[i].str[j] + "\"  idx= " + listArray[i].idx[j]);
				pw.println();
			}

			pw.println("Linear listing...");
			for (int i = 0; i < numElements; i++)
				pw.println(i + " = \"" + stringArray[i] + "\"");

			pw.close();
		} catch (FileNotFoundException e) {
			DSS.doSimpleMsg("Error dumping matrix to file.", -1);
		}
	}

	public void clear() {
		for (int i = 0; i < numLists; i++) {
			listArray[i].nElem = 0;
			for (int j = 0; j < listArray[i].nAllocated; j++)
				listArray[i].str[j] = "";
		}

		for (int i = 0; i < numElementsAllocated; i++) {
			stringArray[i] = "";
		}

		numElements = 0;
	}

	/**
	 * @return string array (includes nulls)
	 */
	public String[] toArray() {
		return stringArray;
	}

}

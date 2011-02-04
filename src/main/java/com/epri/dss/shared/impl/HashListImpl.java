package com.epri.dss.shared.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.epri.dss.common.impl.Utilities;
import com.epri.dss.shared.HashList;

public class HashListImpl implements HashList {

	private class SubList {
		public int nElem;
		public int nAllocated;
		public String[] str;
		public int[] idx;
	}

	private int NumElementsAllocated;

	private int NumLists;

	private int NumElements;

	private String[] StringArray;

	private SubList[] ListArray;

	private int AllocationInc;

	private int LastFind;

	private int LastHash;

	private String LastSearchString;

	protected int InitialAllocation;

	public HashListImpl(int Nelements) {
		super();
		this.NumElements = 0;
		this.InitialAllocation = Nelements;
		this.StringArray = null;  // new String[Nelements];

		this.NumLists = (int) Math.round(Math.sqrt(Nelements));
		int ElementsPerList = Nelements / NumLists + 1;
		this.AllocationInc = ElementsPerList;
		if (this.NumLists < 1) this.NumLists = 1;  // make sure at least one list
		this.ListArray = new SubList[NumLists];
		for (int i = 0; i < this.NumLists; i++) {
			/* Allocate initial Sublists to zero; allocated on demand */
			ListArray[i].str = null;
			ListArray[i].idx = null;
			ListArray[i].nAllocated = 0;
			ListArray[i].nElem = 0;
		}
		this.NumElementsAllocated = 0;
		this.LastFind = 0;
		this.LastHash = 0;
		this.LastSearchString = "";
	}

	public int getInitialAllocation() {
		return InitialAllocation;
	}

	public void setInitialAllocation(int initialAllocation) {
		InitialAllocation = initialAllocation;
	}

	public int listSize() {
		return NumElements;
	}

	private void resizeSubList(SubList subList) {
		// resize by reasonable amount
		int OldAllocation = subList.nAllocated;
		subList.nAllocated = OldAllocation + AllocationInc;
		subList.str = (String[]) Utilities.resizeArray(subList.str, subList.nAllocated);		
		subList.idx = (int[]) Utilities.resizeArray(subList.idx, subList.nAllocated);
	}

	private int hash(String S) {
		return S.hashCode();
	}

	/** Makes the linear string list larger. */
	private void resizeStrArray() {
		NumElementsAllocated += AllocationInc * NumLists;
		StringArray = (String[]) Utilities.resizeArray(StringArray, NumElementsAllocated);
	}

	public int add(String S) {
		int HashNum;
		String SS;
		
		SS = S.toLowerCase();
		HashNum = hash(SS);

		NumElements += 1;
		if (NumElements > NumElementsAllocated)
			resizeStrArray();

		ListArray[HashNum].nElem += 1;
		if (ListArray[HashNum].nElem > ListArray[HashNum].nAllocated)
			resizeSubList(ListArray[HashNum]);

		// make copy of whole string, lower case
		ListArray[HashNum].str[ListArray[HashNum].nElem] = SS;
		// increments count to string
		StringArray[NumElements] = SS;
		
		ListArray[HashNum].idx[ListArray[HashNum].nElem] = NumElements;
	
		return NumElements;
	}

	/**
	 * Repeat find for duplicate string in same hash list
	 */
	public int find(String S) {
		
		LastSearchString = S.toLowerCase();
		LastHash = hash(LastSearchString);
		int Result = 0;  // TODO: Check zero indexing.
		LastFind = 0;

		for (int i = 0; i < ListArray[LastHash].nElem; i++) 
			if (LastSearchString.equals(ListArray[LastHash].str[i])) {
				Result = ListArray[LastHash].idx[i];
				LastFind = i;
				break;
			}
		
		return Result;
	}
	
	/**
	 * Begins search in same list as last
	 */
	public int findNext() {
		// TODO: Check zero indexing.
		int Result = 0;  // Default return
		LastFind += 1;   // Start with next item in hash list

		if ((LastHash > 0) && (LastHash <= NumLists)) {
			for (int i = LastFind; i < ListArray[LastHash].nElem; i++) 
				if (LastSearchString.equals(ListArray[LastHash].str[i])) {
					Result = ListArray[LastHash].idx[i];
					LastFind = i;
					break;
				}
		}
		
		return Result;
	}

	/**
	 * Makes a linear search and tests each string until a string is found
	 * that matches all the characters entered in S.
	 */
	public int findAbbrev(String S) {
		String Test1, Test2;

		int Result = 0;
		if (S.length() > 0) {
			Test1 = S.toLowerCase();
			for (int i = 0; i < NumElements; i++) {
				Test2 = StringArray[i].substring(0, Test1.length());
				if (Test1.equals(Test2)) {
					Result = i;
					break;
				}
			}
		}
			
		return Result;
	}

	public String get(int i) {
		// TODO: Check zero indexing
		return ((i > 0) && (i <= NumElements)) ? StringArray[i] : "";
	}

	/**
	 * Expands number of elements.
	 * 
	 * Creates a new set of string lists and copies the old strings
	 * into the new, hashing for the new number of lists.
	 */
	public void expand(int NewSize) {

		String[] NewStringArray;
		int NewNumLists;
		int ElementsPerList;
		SubList[] NewListArray;
		int HashNum;
		String S;
//		int OldNumLists;

		if (NewSize > NumElementsAllocated) {

//			OldNumLists = NumLists;

			NewStringArray = new String[NewSize];
			NewNumLists = (int) Math.sqrt(NewSize);
			ElementsPerList = NewSize / NewNumLists + 1;
			if (NewNumLists < 1) NewNumLists = 1;  // make sure at least one list
			NewListArray = new SubList[NewNumLists];
			for (int i = 0; i < NumLists; i++) {  // TODO: Check zero indexing.
				/* Allocate initial Sublists */
				NewListArray[i].str = new String[ElementsPerList];
				NewListArray[i].idx = new int[ElementsPerList];
				NewListArray[i].nAllocated = ElementsPerList;
				NewListArray[i].nElem = 0;
			}

			NumLists = NewNumLists;  // Has to be set so Hash function will work

			/* Add elements from old Hash List to New Hash List */

			for (int i = 0; i < NumElements; i++) {  // TODO: Check zero indexing
				S = StringArray[i];
				HashNum = hash(S);
				NewListArray[HashNum].nElem += 1;
				if (NewListArray[HashNum].nElem > NewListArray[HashNum].nAllocated) {
					resizeSubList(NewListArray[HashNum]);
				}
				
				NewListArray[HashNum].str[NewListArray[HashNum].nElem] = S;
				NewStringArray[NumElements] = NewListArray[HashNum].str[NewListArray[HashNum].nElem];
				NewListArray[HashNum].idx[NewListArray[HashNum].nElem] = i;
			}

			/* Assign new String and List Pointers */

			StringArray = NewStringArray;
			ListArray = NewListArray;
			NumElementsAllocated = NewSize;

		}
	}

	public void dumpToFile(String fname) {
		File FD = new File(fname);
		try {
			PrintWriter F = new PrintWriter(FD);

			F.println(String.format("Number of Hash Lists = %d, Number of Elements = %d", NumLists, NumElements));

			F.println();
			F.println("Hash List Distribution");
			for (int i = 0; i < NumLists; i++) 
				F.println(String.format("List = %d, Number of elements = %d", i, ListArray[i].nElem));
			F.println();

			for (int i = 0; i < NumLists; i++) {
				F.println(String.format("List = %d, Number of elements = %d", i, ListArray[i].nElem));
				for (int j = 0; j < ListArray[i].nElem; j++) 
					F.println("\"" + ListArray[i].str[j] + "\"  Idx= " + ListArray[i].idx[j]);
				F.println();
			}
		
			F.println("LINEAR LISTING...");
			for (int i = 0; i < NumElements; i++) 
				F.println(i + " = \"" + StringArray[i] + "\"");
			
			F.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}

	public void clear() {
		for (int i = 0; i < NumLists; i++) {
			ListArray[i].nElem = 0;
			for (int j = 0; j < ListArray[i].nAllocated; j++) 
				ListArray[i].str[j] = "";
		}
		
		for (int i = 0; i < NumElementsAllocated; i++) 
			StringArray[i] = "";

		NumElements = 0;
	}

}

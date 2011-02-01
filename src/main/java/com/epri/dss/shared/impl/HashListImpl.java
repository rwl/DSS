package com.epri.dss.shared.impl;

import com.epri.dss.shared.HashList;

public class HashListImpl implements HashList {

	private class SubList {
		public int Nelem;
		public int Nallocated;
		public String[] Str;
		public long[] Idx;
	}

	private int NumElementsAllocated;

	private int NumLists;

	private int NumElements;

	private String[] StringPtr;

	private SubList[] ListPtr;

	private int AllocationInc;

	private int LastFind;

	private int LastHash;

	private String LastSearchString;

	public int InitialAllocation;

	public HashListImpl(int Nelements) {
		super();
		this.NumElements = 0;
	    this.InitialAllocation = Nelements;
	    this.StringPtr = null;  // new String[Nelements];

	    this.NumLists = (int) Math.round(Math.sqrt(Nelements));
	    int ElementsPerList = Nelements / NumLists + 1;
	    this.AllocationInc = ElementsPerList;
	    if (this.NumLists < 1) this.NumLists = 1;  // make sure at least one list
	    this.ListPtr = new SubList[NumLists];
	    for (int i = 0; i < this.NumLists; i++) {
			/* Allocate initial Sublists to zero; allocated on demand */
	    	ListPtr[i].Str = null;
	    	ListPtr[i].Idx = null;
	    	ListPtr[i].Nallocated = 0;
	    	ListPtr[i].Nelem = 0;
	    }
	    this.NumElementsAllocated = 0;
	    this.LastFind = 0;
	    this.LastHash = 0;
	    this.LastSearchString = "";
	}

	private void ResizeSubList(SubList SubList) {
		// resize by reasonable amount
	    int OldAllocation = SubList.Nallocated;
	    SubList.Nallocated = OldAllocation + AllocationInc;
//	    ReallocStr(Str, Sizeof(Str^[1]) * OldAllocation, Sizeof(Str^[1]) * Nallocated);
//	    Reallocmem(Idx, Sizeof(Idx^[1]) * Nallocated);
	}

	private int Hash(String S) {
		return 0;
	}

	private void ResizeStrPtr() {

	}

	public int Add(String S) {
		return 0;
	}

	/* repeat find for duplicate string in same hash list */
	public int Find(String S) {
		return 0;
	}

	public int FindAbbrev(String S) {
		return 0;
	}

	public String Get(int i) {
		return null;
	}

	/* Expands number of elements */
	public void Expand(int NewSize) {

	}

	public void DumpToFile(String fname) {

	}

	public void Clear() {

	}

	public int Get_ListSize() {
		return this.NumElements;
	}

}

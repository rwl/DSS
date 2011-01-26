package com.epri.dss.shared.impl;

import com.epri.dss.shared.PointerList;

public class PointerListImpl implements PointerList {

	private int NumInList;

	private int MaxAllocated;

	private int ActiveItem;

	private Object[] List;

	private int IncrementSize;

	public PointerListImpl(int Size) {
		super();
		this.MaxAllocated = Size;
		// Default Size & Increment
		if (this.MaxAllocated <= 0) this.MaxAllocated = 10;
		this.List = new Object[MaxAllocated];
		this.NumInList = 0;
		this.ActiveItem = 0;
		// Increment is equal to original allocation
		this.IncrementSize = MaxAllocated;
	}

	public Object Get_First() {
	    if (NumInList > 0) {
	       ActiveItem = 0;
	       return List[ActiveItem];
	    } else {
	        ActiveItem = -1;
	        return null;
	    }
	}

	public Object Get_Next() {
		if (NumInList > 0) {
	       ActiveItem += 1;
	       if (ActiveItem > NumInList) {
	          ActiveItem = NumInList;
	          return null;
	       } else {
	    	   return List[ActiveItem];
	       }
		} else {
			ActiveItem = -1;
			return null;
		}
	}

	public Object Get_Active() {
		if ((ActiveItem > 0) && (ActiveItem <= NumInList)) {
			return Get(ActiveItem);
		} else {
			return null;
		}
	}

	public void Set_New(Object Value) {
		Add(Value);
	}

	public void Clear() {
	    ActiveItem = -1;
	    NumInList = 0;
	}

	/* Returns index of item */
	public int Add(Object p) {
		NumInList += 1;
	    if (NumInList > MaxAllocated) {
	        MaxAllocated = MaxAllocated + IncrementSize;
	        // FIXME: Resize array
	        List = new Object[MaxAllocated];
	    }
	    List[NumInList] = p;
	    ActiveItem = NumInList;
		return NumInList;
	}

	public Object Get(int i) {
		if ((i < 1) || (i > NumInList)) {
			return null;
		} else {
			ActiveItem = i;
			return List[i];
		}
	}

	public int Get_ListSize() {
		return this.NumInList;
	}

	public int Get_ActiveIndex() {
		return this.ActiveItem;
	}

}

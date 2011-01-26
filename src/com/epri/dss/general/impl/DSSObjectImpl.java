/**
 * Copyright (C) 2009 Electric Power Research Institute, Inc.
 * Copyright (C) 2011 Richard Lincoln
 *
 * All rights reserved.
 */

package com.epri.dss.general.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.DSSObject;

public class DSSObjectImpl extends NamedObjectImpl implements DSSObject {

	protected int PropSeqCount;

	protected String[] PropertyValue;

	protected int[] PrpSequence;

	/* PD, PC, Monitor, CondCode, etc. */
	public int DSSObjType;

	public DSSClassImpl ParentClass;

	/* Index into the class collection list */
	public int ClassIndex;

	public boolean HasBeenSaved;

	/* General purpose Flag for each object  don't assume inited */
	public boolean Flag;


	public DSSObjectImpl(DSSClassImpl ParClass) {
		super(ParClass.Get_Name());

		this.DSSObjType = 0;
		this.PropSeqCount = 0;
		this.ParentClass = ParClass;
		this.PropertyValue = new String[this.ParentClass.NumProperties];

		// init'd to zero when allocated
		PrpSequence = new int[this.ParentClass.NumProperties];

		HasBeenSaved = false;
	}

	protected void finalize() throws Throwable {
		for (int i = 0; i < this.ParentClass.NumProperties; i++) {
			this.PropertyValue[i] = "";
		}
		this.PropertyValue = new String[0];
		this.PrpSequence = new int[0];

		super.finalize();
	}

	/* Use DSSClass.PropertyIndex to get index by name */
	public String Get_PropertyValue(int Index) {
		// Default Behavior for all DSS Objects
		return this.PropertyValue[Index];
	}

	public void Set_PropertyValue(int Index, String Value) {
		this.PropertyValue[Index] = Value;

	    // Keep track of the order in which this property was
		// accessed for Save Command
	    this.PropSeqCount += 1;
	    this.PrpSequence[Index] = this.PropSeqCount;
	}

	public String Get_Name() {
		return Get_LocalName();
	}

	public void Set_Name(String Value) {
		// If renamed, then let someone know so hash list can be updated
		if (Get_LocalName().length() > 0)
			this.ParentClass.ElementNamesOutOfSynch = true;

		Set_LocalName(Value);
	}

	/*
	 * Find next larger property sequence number
	 * return 0 if none found
	 */
	protected int GetNextPropertySet(int idx) {
		int Smallest = 9999999; // some big number
		int Result = 0;

		if (idx > 0)
			idx = this.PrpSequence[idx];

		for (int i = 0; i < this.ParentClass.NumProperties; i++) {
			if (this.PrpSequence[i] > idx) {
				if (this.PrpSequence[i] < Smallest) {
	                Smallest = PrpSequence[i];
	               	Result = i;
				}
			}
		}

		return Result;
	}

	/* Allow Calls to edit from object itself */
	public int Edit() {
		this.ParentClass.Set_Active(this.ClassIndex);
		return super.Edit();
	}

	public void InitPropertyValues(int ArrayOffset) {
		this.PropertyValue[ArrayOffset] = "";

		// Clear propertySequence Array after initialization
		ClearPropSeqArray();
	}

	public void DumpProperties(PrintStream F, boolean Complete) {
		F.println();
		F.println("New " + Get_DSSClassName() + '.' + this.Get_Name());
	}

	public void SaveWrite(PrintStream F) {
		/* Write only properties that were explicitly set in the
   		final order they were actually set */
		int iProp = GetNextPropertySet(0); // Works on ActiveDSSObject
		while (iProp > 0) {
			DSSClassImpl pc = this.ParentClass;
			F.print(' ' + pc.Get_PropertyName(pc.RevPropertyIdxMap[iProp]));
			F.print('=' + Utilities.CheckForBlanks(PropertyValue[iProp]));
			iProp = GetNextPropertySet(iProp);
		}
	}

	public void ClearPropSeqArray() {
		this.PropSeqCount = 0;
		for (int i = 0; i < this.ParentClass.NumProperties; i++) {
			this.PrpSequence[i] = 0;
		}
	}

}

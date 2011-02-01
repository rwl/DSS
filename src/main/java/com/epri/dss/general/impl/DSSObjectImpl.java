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

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.DSSObject;

public class DSSObjectImpl extends NamedObjectImpl implements DSSObject {

	protected int PropSeqCount;

	protected String[] PropertyValue;

	protected int[] PrpSequence;

	/* PD, PC, Monitor, CondCode, etc. */
	protected int DSSObjType;

	protected DSSClass ParentClass;

	/* Index into the class collection list */
	protected int ClassIndex;

	protected boolean HasBeenSaved;

	/* General purpose Flag for each object  don't assume inited */
	protected boolean Flag;


	public DSSObjectImpl(DSSClass ParClass) {
		super(ParClass.getName());

		DSSObjType = 0;
		PropSeqCount = 0;
		ParentClass = ParClass;
		PropertyValue = new String[ParentClass.getNumProperties()];

		// init'd to zero when allocated
		PrpSequence = new int[ParentClass.getNumProperties()];

		HasBeenSaved = false;
	}

	protected void finalize() throws Throwable {
		for (int i = 0; i < ParentClass.getNumProperties(); i++) {
			PropertyValue[i] = "";
		}
		PropertyValue = new String[0];
		PrpSequence = new int[0];

		super.finalize();
	}

	public int getDSSObjType() {
		return DSSObjType;
	}

	public void setDSSObjType(int dSSObjType) {
		DSSObjType = dSSObjType;
	}

	public DSSClass getParentClass() {
		return ParentClass;
	}

	public void setParentClass(DSSClass parentClass) {
		ParentClass = parentClass;
	}

	public int getClassIndex() {
		return ClassIndex;
	}

	public void setClassIndex(int classIndex) {
		ClassIndex = classIndex;
	}

	public boolean isHasBeenSaved() {
		return HasBeenSaved;
	}

	public void setHasBeenSaved(boolean hasBeenSaved) {
		HasBeenSaved = hasBeenSaved;
	}

	public boolean isFlag() {
		return Flag;
	}

	public void setFlag(boolean flag) {
		Flag = flag;
	}

	/* Use DSSClass.PropertyIndex to get index by name */
	public String getPropertyValue(int Index) {
		// Default Behavior for all DSS Objects
		return this.PropertyValue[Index];
	}

	public void setPropertyValue(int Index, String Value) {
		PropertyValue[Index] = Value;

		// Keep track of the order in which this property was
		// accessed for Save Command
		PropSeqCount += 1;
		PrpSequence[Index] = PropSeqCount;
	}

	public String getName() {
		return getLocalName();
	}

	public void setName(String Value) {
		// If renamed, then let someone know so hash list can be updated
		if (getLocalName().length() > 0)
			ParentClass.setElementNamesOutOfSynch(true);

		setLocalName(Value);
	}

	/*
	 * Find next larger property sequence number
	 * return 0 if none found
	 */
	protected int GetNextPropertySet(int idx) {
		int Smallest = 9999999; // some big number
		int Result = 0;

		if (idx > 0)
			idx = PrpSequence[idx];

		for (int i = 0; i < ParentClass.getNumProperties(); i++) {
			if (PrpSequence[i] > idx) {
				if (PrpSequence[i] < Smallest) {
					Smallest = PrpSequence[i];
					   Result = i;
				}
			}
		}

		return Result;
	}

	/* Allow Calls to edit from object itself */
	public int edit() {
		ParentClass.setActive(ClassIndex);
		return 0;//super.edit();
	}

	public void initPropertyValues(int ArrayOffset) {
		this.PropertyValue[ArrayOffset] = "";

		// Clear propertySequence Array after initialization
		clearPropSeqArray();
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		F.println();
		F.println("New " + getDSSClassName() + '.' + getName());
	}

	public void saveWrite(PrintStream F) {
		/* Write only properties that were explicitly set in the
		final order they were actually set */
		int iProp = GetNextPropertySet(0); // Works on ActiveDSSObject
		while (iProp > 0) {
			DSSClass pc = ParentClass;
//			F.print(' ' + pc.getPropertyName(pc.getRevPropertyIdxMap()[iProp]));
//			F.print('=' + Utilities.CheckForBlanks(PropertyValue[iProp]));
			iProp = GetNextPropertySet(iProp);
		}
	}

	public void clearPropSeqArray() {
		PropSeqCount = 0;
		for (int i = 0; i < ParentClass.getNumProperties(); i++) {
			PrpSequence[i] = 0;
		}
	}

}

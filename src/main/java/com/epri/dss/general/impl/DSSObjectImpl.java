/**
 * Copyright (C) 2009 Electric Power Research Institute, Inc.
 * Copyright (C) 2011 Richard Lincoln
 *
 * All rights reserved.
 */

package com.epri.dss.general.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.DSSObject;

public class DSSObjectImpl extends NamedObjectImpl implements DSSObject {

	protected int propSeqCount;

	protected String[] propertyValue;

	protected int[] prpSequence;

	/** PD, PC, Monitor, CondCode, etc. */
	protected int objType;

	protected DSSClass parentClass;

	/** Index into the class collection list */
	protected int classIndex;

	protected boolean hasBeenSaved;

	/** General purpose flag for each object  don't assume inited */
	protected boolean flag;

	public void clearPropSeqArray() {
		propSeqCount = 0;
		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			prpSequence[i] = 0;
		}
	}

	public DSSObjectImpl(DSSClass parClass) {
		super(parClass.getName());

		objType = 0;
		propSeqCount = 0;
		parentClass = parClass;
		propertyValue = new String[parentClass.getNumProperties()];

		// init'd to zero when allocated
		prpSequence = new int[parentClass.getNumProperties()];

		hasBeenSaved = false;
	}

	protected void finalize() throws Throwable {
		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			propertyValue[i] = "";
		}
		propertyValue = new String[0];
		prpSequence = new int[0];

		super.finalize();
	}

	public void dumpProperties(PrintStream f, boolean complete) {
		f.println();
		f.println("new " + getDSSClassName() + '.' + getName());
	}

	/**
	 * Allow calls to edit from object itself.
	 */
	public int edit() {
		parentClass.setActiveElement(classIndex);
		return parentClass.edit();
	}

	/**
	 * Use DSSClass.propertyIndex() to get index by name.
	 */
	public String getPropertyValue(int index) {
		// default behavior for all DSS objects
		return propertyValue[index];
	}

	public void initPropertyValues(int arrayOffset) {
		this.propertyValue[arrayOffset] = "";

		// clear propertySequence array after initialization
		clearPropSeqArray();
	}

	public void saveWrite(PrintWriter f) {
		/* Write only properties that were explicitly set in the
		 * final order they were actually set.
		 */
		int iProp = getNextPropertySet(0); // works on activeDSSObject
		while (iProp > 0) {
			DSSClass pc = parentClass;
			f.print(" " + pc.getPropertyName()[ pc.getRevPropertyIdxMap()[iProp] ]);
			f.print("=" + Utilities.checkForBlanks(propertyValue[iProp]));
			iProp = getNextPropertySet(iProp);
		}
	}

	/**
	 * Find next larger property sequence number
	 * return 0 if none found
	 */
	protected int getNextPropertySet(int idx) {
		int smallest = 9999999; // some big number
		int result = 0;

		if (idx > 0)
			idx = prpSequence[idx];

		for (int i = 0; i < parentClass.getNumProperties(); i++)
			if (prpSequence[i] > idx)
				if (prpSequence[i] < smallest) {
					smallest = prpSequence[i];
					result = i;
				}

		return result;
	}

	public void setName(String value) {
		// if renamed, then let someone know so hash list can be updated
		if (getLocalName().length() > 0)
			parentClass.setElementNamesOutOfSynch(true);

		setLocalName(value);
	}

	public void setPropertyValue(int index, String value) {
		propertyValue[index] = value;

		// keep track of the order in which this property was
		// accessed for save command
		propSeqCount += 1;
		prpSequence[index] = propSeqCount;
	}

	public String getName() {
		return getLocalName();
	}

	public int getDSSObjType() {
		return objType;
	}

	public void setDSSObjType(int type) {
		objType = type;
	}

	public DSSClass getParentClass() {
		return parentClass;
	}

	public void setParentClass(DSSClass parent) {
		parentClass = parent;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int index) {
		classIndex = index;
	}

	public boolean isHasBeenSaved() {
		return hasBeenSaved;
	}

	public void setHasBeenSaved(boolean saved) {
		hasBeenSaved = saved;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean value) {
		flag = value;
	}

	// FIXME Protected member in OpenDSS
	public int[] getPrpSequence() {
		return prpSequence;
	}

}

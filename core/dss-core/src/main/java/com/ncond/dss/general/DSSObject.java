package com.ncond.dss.general;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;

public interface DSSObject extends NamedObject {

	/**
	 * PD, PC, Monitor, CondCode, etc.
	 */
	int getDSSObjType();

	void setDSSObjType(int DSSObjType);

	DSSClass getParentClass();

	void setParentClass(DSSClass parentClass);

	/**
	 * Index into the class collection list.
	 */
	int getClassIndex();

	void setClassIndex(int classIndex);

	boolean isHasBeenSaved();

	void setHasBeenSaved(boolean hasBeenSaved);

	/**
	 * General purpose flag for each object don't assume inited.
	 */
	boolean isFlag();

	void setFlag(boolean flag);

	/**
	 * Use DSSClass.PropertyIndex to get index by name.
	 */
	String getPropertyValue(int index);

	void setPropertyValue(int index, String value);

	String getName();

	void setName(String value);

	/**
	 * Allow Calls to edit from object itself.
	 */
	int edit();

	void initPropertyValues(int arrayOffset);

	void dumpProperties(PrintStream f, boolean complete);

	void saveWrite(PrintWriter f);

	void clearPropSeqArray();


	// FIXME Protected member in OpenDSS
	int[] getPrpSequence();

}

package com.epri.dss.general;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.common.DSSClass;

public interface DSSObject extends NamedObject {

	/* PD, PC, Monitor, CondCode, etc. */
	int getDSSObjType();

	void setDSSObjType(int dSSObjType);

	DSSClass getParentClass();

	void setParentClass(DSSClass parentClass);

	/* Index into the class collection list */
	int getClassIndex();

	void setClassIndex(int classIndex);

	boolean isHasBeenSaved();

	void setHasBeenSaved(boolean hasBeenSaved);

	/* General purpose Flag for each object  don't assume inited */
	boolean isFlag();

	void setFlag(boolean flag);

	/* Use DSSClass.PropertyIndex to get index by name */
	String getPropertyValue(int Index);

	void setPropertyValue(int Index, String Value);

	String getName();

	void setName(String Value);

	/* Allow Calls to edit from object itself */
	int edit();

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void saveWrite(PrintWriter f);

	void clearPropSeqArray();

}

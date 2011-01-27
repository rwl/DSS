package com.epri.dss.general;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;

public interface DSSObject extends NamedObject {

	/* PD, PC, Monitor, CondCode, etc. */
	public int getDSSObjType();

	public void setDSSObjType(int dSSObjType);

	public DSSClass getParentClass();

	public void setParentClass(DSSClass parentClass);

	/* Index into the class collection list */
	public int getClassIndex();

	public void setClassIndex(int classIndex);

	public boolean isHasBeenSaved();

	public void setHasBeenSaved(boolean hasBeenSaved);

	/* General purpose Flag for each object  don't assume inited */
	public boolean isFlag();

	public void setFlag(boolean flag);

	/* Use DSSClass.PropertyIndex to get index by name */
	public String getPropertyValue(int Index);

	public void setPropertyValue(int Index, String Value);

	public String getName();

	public void setName(String Value);

	/* Allow Calls to edit from object itself */
	public int edit();

	public void initPropertyValues(int ArrayOffset);

	public void dumpProperties(PrintStream F, boolean Complete);

	public void saveWrite(PrintStream F);

	public void clearPropSeqArray();

}

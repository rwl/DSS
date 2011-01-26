package com.epri.dss.general;

import java.io.PrintStream;

import com.epri.dss.common.impl.DSSClassImpl;

public interface DSSObject extends NamedObject {

	/* PD, PC, Monitor, CondCode, etc. */
	public int DSSObjType = 0;

	public DSSClassImpl ParentClass = null;

	/* Index into the class collection list */
	public int ClassIndex = 0;

	public boolean HasBeenSaved = false;

	/* General purpose Flag for each object  don't assume inited */
	public boolean Flag = false;

	/* Use DSSClass.PropertyIndex to get index by name */
	public String Get_PropertyValue(int Index);

	public void Set_PropertyValue(int Index, String Value);

	public String Get_Name();

	public void Set_Name(String Value);

	/* Allow Calls to edit from object itself */
	public int Edit();

	public void InitPropertyValues(int ArrayOffset);

	public void DumpProperties(PrintStream F, boolean Complete);

	public void SaveWrite(PrintStream F);

	public void ClearPropSeqArray();
}

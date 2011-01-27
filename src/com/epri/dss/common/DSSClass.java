package com.epri.dss.common;

import com.epri.dss.shared.PointerList;

public interface DSSClass {

	public int getNumProperties();

	public void setNumProperties(int numProperties);

	public String[] getPropertyName();

	public void setPropertyName(String[] propertyName);

	public String[] getPropertyHelp();

	public void setPropertyHelp(String[] propertyHelp);

	public int[] getPropertyIdxMap();

	public void setPropertyIdxMap(int[] propertyIdxMap);

	public int[] getRevPropertyIdxMap();

	public void setRevPropertyIdxMap(int[] revPropertyIdxMap);

	public int getDSSClassType();

	public void setDSSClassType(int dSSClassType);

	public PointerList getElementList();

	public void setElementList(PointerList elementList);

	public boolean isElementNamesOutOfSynch();

	public void setElementNamesOutOfSynch(boolean elementNamesOutOfSynch);

	public boolean isSaved();

	public void setSaved(boolean saved);

	public int getActive();

	public void setActive(int Value);

	public int getElementCount();

	public int getFirst();

	public int getNext();

	public String getName();

	public String getFirstPropertyName();

	public String getNextPropertyName();

	/* Helper routine for building Property strings */
	public void AddProperty(String PropName, int CmdMapIndex,
			String HelpString);

	public void ReallocateElementNameList();

	/* uses global parser */
	public int Edit();

	public int Init(int Handle);

	public int NewObject(String ObjName);

	public boolean SetActive(String Value);

	/* Get address of active obj of this class */
	public Object GetActiveObj();

	/* Find an obj of this class by name */
	public Object Find(String ObjName);

	/* Find property value by string */
	public int PropertyIndex(String Prop);
}

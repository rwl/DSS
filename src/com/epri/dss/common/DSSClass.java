package com.epri.dss.common;

import com.epri.dss.shared.PointerList;

public interface DSSClass {

	int getNumProperties();

	void setNumProperties(int numProperties);

	String[] getPropertyName();

	void setPropertyName(String[] propertyName);

	String[] getPropertyHelp();

	void setPropertyHelp(String[] propertyHelp);

	int[] getPropertyIdxMap();

	void setPropertyIdxMap(int[] propertyIdxMap);

	int[] getRevPropertyIdxMap();

	void setRevPropertyIdxMap(int[] revPropertyIdxMap);

	int getDSSClassType();

	void setDSSClassType(int dSSClassType);

	PointerList getElementList();

	void setElementList(PointerList elementList);

	boolean isElementNamesOutOfSynch();

	void setElementNamesOutOfSynch(boolean elementNamesOutOfSynch);

	boolean isSaved();

	void setSaved(boolean saved);

	int getActive();

	void setActive(int Value);

	int getElementCount();

	int getFirst();

	int getNext();

	String getName();

	String getFirstPropertyName();

	String getNextPropertyName();

	/* Helper routine for building Property strings */
	void AddProperty(String PropName, int CmdMapIndex,
			String HelpString);

	void ReallocateElementNameList();

	/* uses global parser */
	int Edit();

	int Init(int Handle);

	int NewObject(String ObjName);

	boolean SetActive(String Value);

	/* Get address of active obj of this class */
	Object GetActiveObj();

	/* Find an obj of this class by name */
	Object Find(String ObjName);

	/* Find property value by string */
	int PropertyIndex(String Prop);

}

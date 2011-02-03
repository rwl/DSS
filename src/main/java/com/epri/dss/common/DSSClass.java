package com.epri.dss.common;

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
	void addProperty(String PropName, int CmdMapIndex,
			String HelpString);

	void reallocateElementNameList();

	/* uses global parser */
	int edit();

	int init(int Handle);

	int newObject(String ObjName);

	boolean setActive(String Value);

	/* Get address of active obj of this class */
	Object getActiveObj();

	/* Find an obj of this class by name */
	Object find(String ObjName);

	/* Find property value by string */
	int propertyIndex(String Prop);

}

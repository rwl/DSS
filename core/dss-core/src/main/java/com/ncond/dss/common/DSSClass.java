package com.ncond.dss.common;

import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.PointerList;

public interface DSSClass {

	int getNumProperties();

	void setNumProperties(int numProperties);

	String getPropertyName(int idx);

	String[] getPropertyName();

	void setPropertyName(String[] propertyName);

	String[] getPropertyHelp();

	void setPropertyHelp(String[] propertyHelp);

	int getPropertyIdxMap(int idx);

	int[] getPropertyIdxMap();

	void setPropertyIdxMap(int[] propertyIdxMap);

	int getRevPropertyIdxMap(int idx);

	/**
	 * Maps property to internal command number
	 */
	int[] getRevPropertyIdxMap();

	void setRevPropertyIdxMap(int[] revPropertyIdxMap);

	int getDSSClassType();

	void setDSSClassType(int DSSClassType);

	PointerList getElementList();

	void setElementList(PointerList elementList);

	boolean isElementNamesOutOfSynch();

	/**
	 * When device gets renamed
	 */
	void setElementNamesOutOfSynch(boolean outOfSynch);

	boolean isSaved();

	void setSaved(boolean saved);

	/**
	 * @return index of present active element
	 */
	int getActiveElement();

	void setActiveElement(int value);

	int getElementCount();

	int getFirst();

	int getNext();

	String getName();

	String getFirstPropertyName();

	String getNextPropertyName();

	/**
	 * Helper routine for building property strings.
	 *
	 * Using the addProperty function, you can list the properties here in the order you want
	 * them to appear when properties are accessed sequentially without tags. Syntax:
	 *
	 * addProperty(<name of property>, <index in the edit case statement>, <help text>);
	 */
	void addProperty(String propName, int cmdMapIndex, String helpString);

	void reallocateElementNameList();

	/**
	 * Uses global parser.
	 * @return 1 on success, 0 on error
	 */
	int edit();

	/**
	 * @return 1 on success, 0 on error
	 */
	int init(int Handle);

	int newObject(String objName);

	boolean setActive(String value);

	/**
	 * Get active object of this class
	 */
	DSSObject getActiveObj();

	/**
	 * Find an obj of this class by name
	 */
	DSSObject find(String objName);

	/**
	 * Find property index by string
	 */
	int propertyIndex(String prop);

}

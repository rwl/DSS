package com.ncond.dss.common.impl;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.impl.DSSObjectImpl;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.HashList;
import com.ncond.dss.shared.PointerList;
import com.ncond.dss.shared.impl.HashListImpl;
import com.ncond.dss.shared.impl.PointerListImpl;

/**
 * Base class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc
 *
 */
public class DSSClassImpl implements DSSClass {

	private static com.ncond.dss.common.DSSClasses DSSClasses;

	protected String className;

	/** Index of present active element */
	protected int activeElement;

	protected CommandList commandList;

	protected int activeProperty;

	protected HashList elementNameList;

	protected int numProperties;

	protected String[] propertyName, propertyHelp;

	protected int[] propertyIdxMap;

	/** Maps property to internal command number */
	protected int[] revPropertyIdxMap;

	protected int classType;

	protected PointerList elementList;

	/** When device gets renamed */
	protected boolean elementNamesOutOfSynch;

	protected boolean saved;

	public DSSClassImpl() {
		super();
		// init size and increment
		elementList = new PointerListImpl(20);
		propertyName = null;
		propertyHelp = null;
		propertyIdxMap = null;
		revPropertyIdxMap = null;

		activeElement = -1;
		activeProperty = -1;

		elementNameList = new HashListImpl(100);
		elementNamesOutOfSynch = false;
	}

	public int newObject(String ObjName) {
		DSS.doErrorMsg(
				"Reached base class of DSSClass for device \"" +ObjName+ "\"",
				"N/A",
				"Should be overridden.", 780);
		return -1;
	}

	public void setActiveElement(int value) {
		if (value >= 0 && value < elementList.size()) {
			activeElement = value;
			DSS.activeDSSObject = (DSSObjectImpl) elementList.get(activeElement);

			if (DSS.activeDSSObject instanceof CktElementImpl)
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
		}
	}

	public int getActiveElement() {
		return activeElement;
	}

	/**
	 * @return 1 on success, 0 on error
	 */
	public int edit() {
		DSS.doSimpleMsg("DSSClass.edit() called. Should be overriden.", 781);
		return 0;
	}

	public int init(int handle) {
		DSS.doSimpleMsg("DSSClass.init() called. Should be overriden.", 782);
		return 0;
	}

	/**
	 * Used by newObject().
	 */
	protected int addObjectToList(DSSObject Obj) {
		// put it in this collection's element list.
		elementList.add(Obj);
		elementNameList.add( Obj.getName() );

		if (elementList.size() > 2 * elementNameList.getInitialAllocation())
			reallocateElementNameList();

		setActiveElement(elementList.size() - 1);
		return activeElement;  // return index of object in list
	}

	public boolean setActive(String ObjName) {
		boolean result = false;

		if (elementNamesOutOfSynch)
			resynchElementNameList();
		int idx = elementNameList.find(ObjName);
		if (idx >= 0) {
			setActiveElement(idx);
			DSS.activeDSSObject = (DSSObject) elementList.get(idx);
			result = true;
		}
		return result;
	}

	/**
	 * Find an obj of this class by name.
	 */
	public Object find(String ObjName) {
		Object result = null;
		if (elementNamesOutOfSynch)
			resynchElementNameList();

		int idx = elementNameList.find(ObjName);
		if (idx >= 0) {
			setActiveElement(idx);
			result = elementList.get(idx);
		}
		return result;
	}

	/**
	 * Get address of active obj of this class.
	 */
	public Object getActiveObj() {
		if (activeElement >= 0) {
			return elementList.get(activeElement);
		} else {
			return null;
		}
	}

	public String getFirstPropertyName() {
		activeProperty = -1;
		return getNextPropertyName();
	}

	public String getNextPropertyName() {
		String result;

		activeProperty += 1;

		if (activeProperty < numProperties) {
			result = propertyName[activeProperty];
		} else {
			result = "";
		}

		return result;
	}

	/**
	 * Find property value by string.
	 */
	public int propertyIndex(String prop) {
		int result = -1;  // default result if not found
		for (int i = 0; i < numProperties; i++) {
			if (prop.equalsIgnoreCase(propertyName[i])) {
				result = propertyIdxMap[i];
				break;
			}
		}
		return result;
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	protected void countProperties() {
		numProperties = numProperties + 1;
	}

	/**
	 * Add properties of this class to propName.
	 */
	protected void defineProperties() {
		activeProperty += 1;

		propertyName[activeProperty] = "like";
		propertyHelp[activeProperty] = "Make like another object, e.g.:" +
				DSS.CRLF + DSS.CRLF +
				"new capacitor.C2 like=c1  ...";
	}

	protected int classEdit(final Object activeObj, int paramPointer) {
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			switch (paramPointer) {
			case 0:
				makeLike(Parser.getInstance().makeString());  // like command
				break;
			}
		}
		return 0;
	}

	/**
	 * @return 1 on success, 0 on error
	 */
	protected int makeLike(String objName) {
		DSS.doSimpleMsg("DSSClass.makeLike() called. Should be overriden.", 784);
		return 0;
	}

	public int getFirst() {
		int result = -1;
		if (elementList.size() == 0) {
			result = -1;
		} else {

			setActiveElement(0);
			DSS.activeDSSObject = (DSSObjectImpl) elementList.getFirst();
			if (DSS.activeDSSObject instanceof CktElementImpl) {
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
				result = activeElement;
			}
		}
		return result;
	}

	public int getNext() {
		int result = -1;

		activeElement += 1;

		if (activeElement >= elementList.size()) {
			result = -1;
		} else {
			DSS.activeDSSObject = (DSSObject) elementList.getNext();

			if (DSS.activeDSSObject instanceof CktElementImpl) {
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
				result = activeElement;
			}
		}

		return result;
	}

	/**
	 * Helper routine for building property strings.
	 *
	 * Using the addProperty function, you can list the properties here in the order you want
	 * them to appear when properties are accessed sequentially without tags. Syntax:
	 *
	 * addProperty(<name of property>, <index in the edit case statement>, <help text>);
	 */
	public void addProperty(String propName, int cmdMapIndex, String helpString) {
		activeProperty += 1;

		propertyName[activeProperty] = propName;
		propertyHelp[activeProperty] = helpString;
		// map to internal object property index
		propertyIdxMap[activeProperty] = cmdMapIndex;
		revPropertyIdxMap[cmdMapIndex] = activeProperty;
	}

	protected void allocatePropertyArrays() {
		propertyName = new String[numProperties];
		propertyHelp = new String[numProperties];
		propertyIdxMap = new int[numProperties];
		revPropertyIdxMap = new int[numProperties];

		activeProperty = -1;  // initialize for addProperty

		/* initialize propertyIdxMap to take care of legacy items */
		for (int i = 0; i < numProperties; i++)
			propertyIdxMap[i] = i;
		for (int i = 0; i < numProperties; i++)
			revPropertyIdxMap[i] = i;
	}

	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		elementNameList = null;  // throw away the old one
		elementNameList = new HashListImpl(2 * elementList.size());  // make a new one

		// do this using the names of the elements rather than the old list because it might be
		// messed up if an element gets renamed

		for (int i = 0; i < this.elementList.size(); i++)
			elementNameList.add( ((DSSObject) elementList.get(i)).getName() );
	}

	private void resynchElementNameList() {
		reallocateElementNameList();
		elementNamesOutOfSynch = false;
	}

	public int getElementCount() {
		return elementList.size();
	}

	public String getPropertyName(int idx) {
		return propertyName[idx];
	}

	public int getPropertyIdxMap(int idx) {
		return propertyIdxMap[idx];
	}

	public int getRevPropertyIdxMap(int idx) {
		return revPropertyIdxMap[idx];
	}

	public int getNumProperties() {
		return numProperties;
	}

	public void setNumProperties(int num) {
		numProperties = num;
	}

	public String[] getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String[] name) {
		propertyName = name;
	}

	public String[] getPropertyHelp() {
		return propertyHelp;
	}

	public void setPropertyHelp(String[] help) {
		propertyHelp = help;
	}

	public int[] getPropertyIdxMap() {
		return propertyIdxMap;
	}

	public void setPropertyIdxMap(int[] map) {
		propertyIdxMap = map;
	}

	public int[] getRevPropertyIdxMap() {
		return revPropertyIdxMap;
	}

	public void setRevPropertyIdxMap(int[] map) {
		revPropertyIdxMap = map;
	}

	public int getDSSClassType() {
		return classType;
	}

	public void setDSSClassType(int type) {
		classType = type;
	}

	public PointerList getElementList() {
		return elementList;
	}

	public void setElementList(PointerList list) {
		elementList = list;
	}

	public boolean isElementNamesOutOfSynch() {
		return elementNamesOutOfSynch;
	}

	public void setElementNamesOutOfSynch(boolean value) {
		elementNamesOutOfSynch = value;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean value) {
		saved = value;
	}

	public String getName() {
		return className;
	}

	public static void setDSSClasses(com.ncond.dss.common.DSSClasses classes) {
		DSSClasses = classes;
	}

	public static com.ncond.dss.common.DSSClasses getDSSClasses() {
		return DSSClasses;
	}

}

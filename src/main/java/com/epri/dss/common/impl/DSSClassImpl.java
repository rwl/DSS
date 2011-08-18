package com.epri.dss.common.impl;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.ObjectList;
import com.epri.dss.shared.impl.HashListImpl;
import com.epri.dss.shared.impl.ObjectListImpl;

/**
 * Base class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc
 *
 */
public class DSSClassImpl<T extends DSSObject> implements DSSClass<T> {

	private static com.epri.dss.common.DSSClasses DSSClasses;

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

	protected ObjectList<T> elementList;

	/** When device gets renamed */
	protected boolean elementNamesOutOfSynch;

	protected boolean saved;

	public DSSClassImpl() {
		super();
		// init size and increment
		elementList = new ObjectListImpl<T>(20);
		propertyName = null;
		propertyHelp = null;
		propertyIdxMap = null;
		revPropertyIdxMap = null;

		activeElement = -1;
		activeProperty = -1;

		elementNameList = new HashListImpl(100);
		elementNamesOutOfSynch = false;
	}

	protected void finalize() throws Throwable {
		// get rid of space occupied by strings
		for (int i = 0; i < numProperties; i++) propertyName[i] = "";
		for (int i = 0; i < numProperties; i++) propertyHelp[i] = "";

		propertyName = new String[0];
		propertyHelp = new String[0];
		propertyIdxMap = new int[0];
		revPropertyIdxMap = new int[0];
		elementList = null;
		elementNameList = null;
		commandList = null;

		super.finalize();
	}

	public int newObject(String objName) {
		DSSGlobals.doErrorMsg(
				"Reached base class of DSSClass for device \"" +objName+ "\"",
				"N/A",
				"Should be overridden.", 780);
		return 0;
	}

	public void setActiveElement(int value) {
		if ((value > 0) && (value <= elementList.size())) {
			activeElement = value;
			DSSGlobals.activeDSSObject = elementList.get(activeElement);

			if (DSSGlobals.activeDSSObject instanceof DSSCktElement)
				DSSGlobals.activeCircuit.setActiveCktElement( (CktElement) DSSGlobals.activeDSSObject );
		}
	}

	public int getActiveElement() {
		return activeElement;
	}

	/**
	 * Uses global parser.
	 */
	public int edit() {
		DSSGlobals.doSimpleMsg("DSSClass.edit() called. Should be overriden.", 781);
		return 0;
	}

	public int init(int Handle) {
		DSSGlobals.doSimpleMsg("DSSClass.init() called. Should be overriden.", 782);
		return 0;
	}

	/**
	 * Used by newObject().
	 */
	protected int addObjectToList(T Obj) {
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
		if (idx > 0) {
			setActiveElement(idx);
			DSSGlobals.activeDSSObject = elementList.get(idx);
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
		if (idx > 0) {
			setActiveElement(idx);
			result = elementList.get(idx);
		}
		return result;
	}

	/**
	 * Get address of active obj of this class.
	 */
	public Object getActiveObj() {
		if (activeElement > 0) {
			return elementList.get(activeElement);
		} else {
			return null;
		}
	}

	public String getFirstPropertyName() {
		activeProperty = 0;
		return getNextPropertyName();
	}

	public String getNextPropertyName() {
		String result;

		if (activeProperty <= numProperties) {
			result = propertyName[activeProperty];
		} else {
			result = "";
		}

		activeProperty += 1;

		return result;
	}

	/**
	 * Find property value by string.
	 */
	public int propertyIndex(String prop) {
		int result = 0;  // default result if not found
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
				DSSGlobals.CRLF + DSSGlobals.CRLF +
				"new capacitor.C2 like=c1  ...";
	}

	protected int classEdit(final Object activeObj, int paramPointer) {
		// continue parsing with contents of parser
		if (paramPointer > 0) {
//			DSSObject obj = (DSSObject) ActiveObj;
			switch (paramPointer) {
			case 1:
				makeLike(Parser.getInstance().makeString());  // like command
				break;
			}
		}
		return 0;
	}

	protected int makeLike(String objName) {
		DSSGlobals.doSimpleMsg("DSSClass.makeLike() called. Should be overriden.", 784);
		return 0;
	}

	public int getFirst() {
		int result = 0;
		if (elementList.size() == 0) {
			result = 0;
		} else {

			setActiveElement(1);
			DSSGlobals.activeDSSObject = elementList.get(0);
			if (DSSGlobals.activeDSSObject instanceof DSSCktElement) {
				DSSGlobals.activeCircuit.setActiveCktElement( (CktElement) DSSGlobals.activeDSSObject );
				result = activeElement;
			}
		}
		return result;
	}

	public int getNext() {
		int result = -1;
		if (activeElement > elementList.size()) {
			result = 0;
		} else {

			DSSGlobals.activeDSSObject = elementList.getNext();

			if (DSSGlobals.activeDSSObject instanceof DSSCktElement) {
				DSSGlobals.activeCircuit.setActiveCktElement( (CktElement) DSSGlobals.activeDSSObject );
				result = activeElement;
			}
		}
		activeElement += 1;

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
		for (int i = 0; i < numProperties; i++) propertyIdxMap[i] = i;
		for (int i = 0; i < numProperties; i++) revPropertyIdxMap[i] = i;
	}

	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		elementNameList = null;  // throw away the old one
		elementNameList = new HashListImpl(2 * elementList.size());  // make a new one

		// do this using the names of the elements rather than the old list because it might be
		// messed up if an element gets renamed

		for (int i = 0; i < this.elementList.size(); i++)
			elementNameList.add( elementList.get(i).getName() );
	}

	private void resynchElementNameList() {
		reallocateElementNameList();
		elementNamesOutOfSynch = false;
	}

	public int getElementCount() {
		return elementList.size();
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

	public ObjectList<T> getElementList() {
		return elementList;
	}

	public void setElementList(ObjectList<T> list) {
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

	public static void setDSSClasses(com.epri.dss.common.DSSClasses classes) {
		DSSClasses = classes;
	}

	public static com.epri.dss.common.DSSClasses getDSSClasses() {
		return DSSClasses;
	}

}

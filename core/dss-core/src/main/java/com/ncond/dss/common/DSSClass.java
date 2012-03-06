package com.ncond.dss.common;

import com.ncond.dss.general.DSSObject;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.HashList;
import com.ncond.dss.shared.PointerList;

/**
 * Base class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc.
 */
abstract public class DSSClass {

	private static com.ncond.dss.common.DSSClasses DSSClasses;

	protected String className;

	protected int activeElement;

	protected CommandList commandList;

	protected int activeProperty;

	protected HashList elementNameList;

	protected int numProperties;

	protected String[] propertyName, propertyHelp;

	protected int[] propertyIdxMap;

	/**
	 * Maps property to internal command number
	 */
	protected int[] revPropertyIdxMap;

	protected int classType;

	protected PointerList elementList;  // TODO: replace with List and Iterator

	/**
	 * When device gets renamed
	 */
	protected boolean elementNamesOutOfSynch;

	protected boolean saved;

	public DSSClass() {
		super();
		// init size and increment
		elementList = new PointerList(20);
		propertyName = null;
		propertyHelp = null;
		propertyIdxMap = null;
		revPropertyIdxMap = null;

		activeElement = -1;
		activeProperty = -1;

		elementNameList = new HashList(100);
		elementNamesOutOfSynch = false;
	}

	abstract public int newObject(String objName);

	/**
	 * Uses global parser.
	 *
	 * @return 1 on success, 0 on error
	 */
	abstract public int edit();

	/**
	 * @return 1 on success, 0 on error
	 */
	abstract public int init(int handle);

	/**
	 * @return 1 on success, 0 on error
	 */
	abstract protected int makeLike(String objName);

	public void setActiveElement(int value) {
		if (value >= 0 && value < elementList.size()) {
			activeElement = value;
			DSS.activeDSSObject = (DSSObject) elementList.get(activeElement);

			if (DSS.activeDSSObject instanceof CktElement)
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
		}
	}

	/**
	 * @return index of present active element
	 */
	public int getActiveElement() {
		return activeElement;
	}

	/** Used by newObject(). */
	protected int addObjectToList(DSSObject obj) {
		// put it in this collection's element list.
		elementList.add(obj);
		elementNameList.add( obj.getName() );

		if (elementList.size() > (2 * elementNameList.getInitialAllocation())) {
			reallocateElementNameList();
		}

		setActiveElement(elementList.size() - 1);
		return activeElement;  // return index of object in list
	}

	public boolean setActive(String objName) {
		boolean exists = false;

		if (elementNamesOutOfSynch) resynchElementNameList();

		int idx = elementNameList.find(objName);
		if (idx >= 0) {
			setActiveElement(idx);
			DSS.activeDSSObject = (DSSObject) elementList.get(idx);
			exists = true;
		}
		return exists;
	}

	/**
	 * Find an obj of this class by name
	 */
	public DSSObject find(String objName) {
		DSSObject result = null;
		if (elementNamesOutOfSynch) resynchElementNameList();

		int idx = elementNameList.find(objName);
		if (idx >= 0) {
			setActiveElement(idx);
			result = (DSSObject) elementList.get(idx);
		}
		return result;
	}

	/**
	 * Get active object of this class
	 */
	public DSSObject getActiveObj() {
		if (activeElement >= 0) {
			return (DSSObject) elementList.get(activeElement);
		} else {
			return null;
		}
	}

	public String getFirstPropertyName() {
		activeProperty = -1;
		return getNextPropertyName();
	}

	public String getNextPropertyName() {
		activeProperty += 1;
		return (activeProperty < numProperties) ? propertyName[activeProperty] : "";
	}

	/**
	 * Find property index by string
	 */
	public int propertyIndex(String prop) {
		int propIdx = -1;  // default result if not found
		for (int i = 0; i < numProperties; i++) {
			if (prop.equalsIgnoreCase(propertyName[i])) {
				propIdx = propertyIdxMap[i];
				break;
			}
		}
		return propIdx;
	}

	/** Add no. of intrinsic properties. */
	protected void countProperties() {
		numProperties = numProperties + 1;
	}

	/** Add properties of this class to propName. */
	protected void defineProperties() {
		activeProperty += 1;

		propertyName[activeProperty] = "like";
		propertyHelp[activeProperty] = "Make like another object, e.g.:" +
				DSS.CRLF + "new capacitor.C2 like=c1  ...";
	}

	protected int classEdit(final DSSObject activeObj, int paramPointer) {
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

	public int getFirst() {
		int idx = -1;
		if (elementList.size() == 0) {
			idx = -1;
		} else {
			setActiveElement(0);
			DSS.activeDSSObject = (DSSObject) elementList.getFirst();
			if (DSS.activeDSSObject instanceof CktElement) {
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
				idx = activeElement;
			}
		}
		return idx;
	}

	public int getNext() {
		int idx = -1;

		activeElement += 1;

		if (activeElement >= elementList.size()) {
			idx = -1;
		} else {
			DSS.activeDSSObject = (DSSObject) elementList.getNext();

			if (DSS.activeDSSObject instanceof CktElement) {
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
				idx = activeElement;
			}
		}
		return idx;
	}

	/**
	 * Helper routine for building property strings.
	 *
	 * Using the addProperty function, you can list the properties here in the order you want
	 * them to appear when properties are accessed sequentially without tags. Syntax:
	 *
	 * addProperty(<name of property>, <index in the edit case statement>, <help text>);
	 */
	public void addProperty(String name, int cmdMapIdx, String help) {
		activeProperty += 1;

		propertyName[activeProperty] = name;
		propertyHelp[activeProperty] = help;
		// map to internal object property index
		propertyIdxMap[activeProperty] = cmdMapIdx;
		revPropertyIdxMap[cmdMapIdx] = activeProperty;
	}

	protected void allocatePropertyArrays() {
		propertyName = new String[numProperties];
		propertyHelp = new String[numProperties];
		propertyIdxMap = new int[numProperties];
		revPropertyIdxMap = new int[numProperties];

		activeProperty = -1;  // initialize for addProperty

		/* Initialize propertyIdxMap to take care of legacy items */
		for (int i = 0; i < numProperties; i++)
			propertyIdxMap[i] = i;
		for (int i = 0; i < numProperties; i++)
			revPropertyIdxMap[i] = i;
	}

	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		elementNameList = new HashList(2 * elementList.size());  // make a new one

		// do this using the names of the elements rather than the old list because
		// it might be messed up if an element gets renamed
		for (int i = 0; i < elementList.size(); i++)
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

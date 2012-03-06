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
 * Keeps track of objects of each class, dispatches edits, etc.
 */
abstract public class DSSClassImpl implements DSSClass {

	private static com.ncond.dss.common.DSSClasses DSSClasses;

	protected String className;

	protected int activeElement;

	protected CommandList commandList;

	protected int activeProperty;

	protected HashList elementNameList;

	protected int numProperties;

	protected String[] propertyName, propertyHelp;

	protected int[] propertyIdxMap;

	protected int[] revPropertyIdxMap;

	protected int classType;

	protected PointerList elementList;  // TODO: replace with List and Iterator

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

	@Override
	abstract public int newObject(String objName);

	@Override
	abstract public int edit();

	@Override
	abstract public int init(int handle);

	/**
	 * @return 1 on success, 0 on error
	 */
	abstract protected int makeLike(String objName);

	@Override
	public void setActiveElement(int value) {
		if (value >= 0 && value < elementList.size()) {
			activeElement = value;
			DSS.activeDSSObject = (DSSObjectImpl) elementList.get(activeElement);

			if (DSS.activeDSSObject instanceof CktElement)
				DSS.activeCircuit.setActiveCktElement( (CktElement) DSS.activeDSSObject );
		}
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
	public DSSObject getActiveObj() {
		if (activeElement >= 0) {
			return (DSSObject) elementList.get(activeElement);
		} else {
			return null;
		}
	}

	@Override
	public String getFirstPropertyName() {
		activeProperty = -1;
		return getNextPropertyName();
	}

	@Override
	public String getNextPropertyName() {
		activeProperty += 1;
		return (activeProperty < numProperties) ? propertyName[activeProperty] : "";
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		elementNameList = new HashListImpl(2 * elementList.size());  // make a new one

		// do this using the names of the elements rather than the old list because
		// it might be messed up if an element gets renamed
		for (int i = 0; i < elementList.size(); i++)
			elementNameList.add( ((DSSObject) elementList.get(i)).getName() );
	}

	private void resynchElementNameList() {
		reallocateElementNameList();
		elementNamesOutOfSynch = false;
	}

	@Override
	public int getElementCount() {
		return elementList.size();
	}

	@Override
	public String getPropertyName(int idx) {
		return propertyName[idx];
	}

	@Override
	public int getPropertyIdxMap(int idx) {
		return propertyIdxMap[idx];
	}

	@Override
	public int getRevPropertyIdxMap(int idx) {
		return revPropertyIdxMap[idx];
	}

	@Override
	public int getNumProperties() {
		return numProperties;
	}

	@Override
	public void setNumProperties(int num) {
		numProperties = num;
	}

	@Override
	public String[] getPropertyName() {
		return propertyName;
	}

	@Override
	public void setPropertyName(String[] name) {
		propertyName = name;
	}

	@Override
	public String[] getPropertyHelp() {
		return propertyHelp;
	}

	@Override
	public void setPropertyHelp(String[] help) {
		propertyHelp = help;
	}

	@Override
	public int[] getPropertyIdxMap() {
		return propertyIdxMap;
	}

	@Override
	public void setPropertyIdxMap(int[] map) {
		propertyIdxMap = map;
	}

	@Override
	public int[] getRevPropertyIdxMap() {
		return revPropertyIdxMap;
	}

	@Override
	public void setRevPropertyIdxMap(int[] map) {
		revPropertyIdxMap = map;
	}

	@Override
	public int getDSSClassType() {
		return classType;
	}

	@Override
	public void setDSSClassType(int type) {
		classType = type;
	}

	@Override
	public PointerList getElementList() {
		return elementList;
	}

	@Override
	public void setElementList(PointerList list) {
		elementList = list;
	}

	@Override
	public boolean isElementNamesOutOfSynch() {
		return elementNamesOutOfSynch;
	}

	@Override
	public void setElementNamesOutOfSynch(boolean value) {
		elementNamesOutOfSynch = value;
	}

	@Override
	public boolean isSaved() {
		return saved;
	}

	@Override
	public void setSaved(boolean value) {
		saved = value;
	}

	@Override
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

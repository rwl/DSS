package com.epri.dss.common.impl;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.PointerList;
import com.epri.dss.shared.impl.HashListImpl;
import com.epri.dss.shared.impl.PointerListImpl;

/**
 * Base Class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc
 *
 * @author Richard Lincoln
 */
public class DSSClassImpl implements DSSClass {

	private static com.epri.dss.common.DSSClasses DSSClasses;

	protected String Class_Name;

	/* index of present ActiveElement */
	private int ActiveElement;

	protected CommandList CommandList;

	protected int ActiveProperty;

	protected HashList ElementNameList;


	protected int NumProperties;

	protected String[] PropertyName, PropertyHelp;

	protected int[] PropertyIdxMap;

	/* maps property to internal command number */
	protected int[] RevPropertyIdxMap;

	protected int DSSClassType;

	protected PointerList ElementList;

	/* When device gets renamed */
	protected boolean ElementNamesOutOfSynch;

	protected boolean Saved;

	public DSSClassImpl() {
		super();
		// Init size and increment
		this.ElementList = new PointerListImpl(20);
		this.PropertyName = null;
		this.PropertyHelp = null;
		this.PropertyIdxMap = null;
		this.RevPropertyIdxMap = null;

		setActiveElement(-1);
		this.ActiveProperty = -1;

		this.ElementNameList = new HashListImpl(100);
		this.ElementNamesOutOfSynch = false;
	}

	protected void finalize() throws Throwable {
		// Get rid of space occupied by strings
		for (int i = 0; i < this.NumProperties; i++) this.PropertyName[i] = "";
		for (int i = 0; i < this.NumProperties; i++) this.PropertyHelp[i] = "";

		this.PropertyName = new String[0];
		this.PropertyHelp = new String[0];
		this.PropertyIdxMap = new int[0];
		this.RevPropertyIdxMap = new int[0];
		this.ElementList = null;
		this.ElementNameList = null;
		this.CommandList = null;

		super.finalize();
	}

	public int newObject(String ObjName) {
		DSSGlobals.getInstance().doErrorMsg(
				"Reached base class of DSSClass for device \"" +ObjName+ "\"",
				"N/A",
				"Should be overridden.", 780);
		return 0;
	}

	public void setActiveElement(int Value) {
		if ((Value > 0) && (Value <= ElementList.size())) {
			DSSGlobals Globals = DSSGlobals.getInstance();
			this.ActiveElement = Value;
			Globals.setActiveDSSObject((DSSObjectImpl) ElementList.get(ActiveElement));
			// Make sure Active Ckt Element agrees if is a ckt element
			// So COM interface will work
			if (Globals.getActiveDSSObject() instanceof DSSCktElement)
				Globals.getActiveCircuit().setActiveCktElement( (CktElement) Globals.getActiveDSSObject() );
		}
	}

	public int getActiveElement() {
		return ActiveElement;
	}

	/**
	 * Uses global parser.
	 */
	public int edit() {
		DSSGlobals.getInstance().doSimpleMsg("DSSClass.edit() called. Should be overriden.", 781);
		return 0;
	}

	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("DSSClass.init() called. Should be overriden.", 782);
		return 0;
	}

	/**
	 * Used by newObject().
	 */
	protected int addObjectToList(DSSObject Obj) {
		// put it in this collection's element list.
		ElementList.add(Obj);
		ElementNameList.add( Obj.getName() );

		if (ElementList.size() > 2 * ElementNameList.getInitialAllocation())
			reallocateElementNameList();

		setActiveElement(ElementList.size() - 1);
		return ActiveElement;  // return index of object in list
	}

	public boolean setActive(String ObjName) {
		boolean Result = false;

		if (ElementNamesOutOfSynch)
			resynchElementNameList();
		int idx = ElementNameList.find(ObjName);
		if (idx > 0) {
			setActiveElement(idx);
			DSSGlobals.getInstance().setActiveDSSObject((DSSObject) ElementList.get(idx));
			Result = true;
		}
		return Result;
	}

	/**
	 * Find an obj of this class by name.
	 */
	public Object find(String ObjName) {
		Object Result = null;
		if (ElementNamesOutOfSynch)
			resynchElementNameList();

		int idx = ElementNameList.find(ObjName);
		if (idx > 0) {
			setActiveElement(idx);
			Result = ElementList.get(idx);
		}
		return Result;
	}

	/**
	 * Get address of active obj of this class.
	 */
	public Object getActiveObj() {
		if (ActiveElement > 0) {
			return ElementList.get(ActiveElement);
		} else {
			return null;
		}
	}

	public String getFirstPropertyName() {
		ActiveProperty = 0;
		return getNextPropertyName();
	}

	public String getNextPropertyName() {
		String Result;

		if (ActiveProperty <= NumProperties) {
			Result = PropertyName[ActiveProperty];
		} else {
			Result = "";
		}

		ActiveProperty += 1;

		return Result;
	}

	/**
	 * Find property value by string.
	 */
	public int propertyIndex(String Prop) {
		int Result = 0;  // Default result if not found
		for (int i = 0; i < NumProperties; i++) {
			if (Prop.equals(PropertyName[i])) {
				Result = PropertyIdxMap[i];
				break;
			}
		}
		return Result;
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	protected void countProperties() {
		NumProperties = NumProperties + 1;
	}

	/**
	 * Add Properties of this class to propName.
	 */
	protected void defineProperties() {
		ActiveProperty += 1;

		PropertyName[ActiveProperty] = "like";
		PropertyHelp[ActiveProperty] = "Make like another object, e.g.:" +
				DSSGlobals.CRLF + DSSGlobals.CRLF +
				"New Capacitor.C2 like=c1  ...";
	}

	protected int classEdit(final Object ActiveObj, int ParamPointer) {
		// continue parsing with contents of Parser
		if (ParamPointer > 0) {
//			DSSObject obj = (DSSObject) ActiveObj;
			switch (ParamPointer) {
			case 1:
				makeLike(Parser.getInstance().makeString());  // like command
				break;
			}
		}
		return 0;
	}

	protected int makeLike(String ObjName) {
		DSSGlobals.getInstance().doSimpleMsg("DSSClass.makeLike() called. Should be overriden.", 784);
		return 0;
	}

	public int getFirst() {
		int Result = 0;
		if (ElementList.size() == 0) {
			Result = 0;
		} else {
			DSSGlobals Globals = DSSGlobals.getInstance();

			setActiveElement(1);
			Globals.setActiveDSSObject((DSSObjectImpl) ElementList.get(0));
			// Make sure Active Ckt Element agrees if is a ckt element
			if (Globals.getActiveDSSObject() instanceof DSSCktElement) {
				Globals.getActiveCircuit().setActiveCktElement( (CktElement) Globals.getActiveDSSObject() );
				Result = ActiveElement;
			}
		}
		return Result;
	}

	public int getNext() {
		int Result = -1;
		if (ActiveElement > ElementList.size()) {
			Result = 0;
		} else {
			DSSGlobals Globals = DSSGlobals.getInstance();

			Globals.setActiveDSSObject((DSSObject) ElementList.getNext());
			// Make sure Active Ckt Element agrees if is a ckt element
			if (Globals.getActiveDSSObject() instanceof DSSCktElement) {
				Globals.getActiveCircuit().setActiveCktElement( (CktElement) Globals.getActiveDSSObject() );
				Result = ActiveElement;
			}
		}
		ActiveElement += 1;

		return Result;
	}

	/**
	 * Helper routine for building Property strings.
	 *
	 * Using the addProperty FUNCTION, you can list the properties here in the order you want
	 * them to appear when properties are accessed sequentially without tags.   Syntax:
	 *
	 * addProperty( <name of property>, <index in the EDIT Case statement>, <help text>);
	 */
	public void addProperty(String PropName, int CmdMapIndex, String HelpString) {
		ActiveProperty += 1;

		PropertyName[ActiveProperty] = PropName;
		PropertyHelp[ActiveProperty] = HelpString;
		// Map to internal object property index
		PropertyIdxMap[ActiveProperty] = CmdMapIndex;
		RevPropertyIdxMap[CmdMapIndex] = ActiveProperty;
	}

	protected void allocatePropertyArrays() {
		PropertyName = new String[NumProperties];
		PropertyHelp = new String[NumProperties];
		PropertyIdxMap = new int[NumProperties];
		RevPropertyIdxMap = new int[NumProperties];

		ActiveProperty = -1;    // initialize for addProperty

		/* initialize PropertyIdxMap to take care of legacy items */
		for (int i = 0; i < NumProperties; i++) PropertyIdxMap[i] = i;
		for (int i = 0; i < NumProperties; i++) RevPropertyIdxMap[i] = i;
	}

	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		ElementNameList = null;  // Throw away the old one.
		ElementNameList = new HashListImpl(2 * ElementList.size());  // make a new one

		// Do this using the Names of the Elements rather than the old list because it might be
		// messed up if an element gets renamed

		for (int i = 0; i < this.ElementList.size(); i++)
			ElementNameList.add( ((DSSObject) ElementList.get(i)).getName() );
	}

	private void resynchElementNameList() {
		reallocateElementNameList();
		ElementNamesOutOfSynch = false;
	}

	public int getElementCount() {
		return ElementList.size();
	}

	public int getNumProperties() {
		return NumProperties;
	}

	public void setNumProperties(int numProperties) {
		NumProperties = numProperties;
	}

	public String[] getPropertyName() {
		return PropertyName;
	}

	public void setPropertyName(String[] propertyName) {
		PropertyName = propertyName;
	}

	public String[] getPropertyHelp() {
		return PropertyHelp;
	}

	public void setPropertyHelp(String[] propertyHelp) {
		PropertyHelp = propertyHelp;
	}

	public int[] getPropertyIdxMap() {
		return PropertyIdxMap;
	}

	public void setPropertyIdxMap(int[] propertyIdxMap) {
		PropertyIdxMap = propertyIdxMap;
	}

	public int[] getRevPropertyIdxMap() {
		return RevPropertyIdxMap;
	}

	public void setRevPropertyIdxMap(int[] revPropertyIdxMap) {
		RevPropertyIdxMap = revPropertyIdxMap;
	}

	public int getDSSClassType() {
		return DSSClassType;
	}

	public void setDSSClassType(int dSSClassType) {
		DSSClassType = dSSClassType;
	}

	public PointerList getElementList() {
		return ElementList;
	}

	public void setElementList(PointerList elementList) {
		ElementList = elementList;
	}

	public boolean isElementNamesOutOfSynch() {
		return ElementNamesOutOfSynch;
	}

	public void setElementNamesOutOfSynch(boolean elementNamesOutOfSynch) {
		ElementNamesOutOfSynch = elementNamesOutOfSynch;
	}

	public boolean isSaved() {
		return Saved;
	}

	public void setSaved(boolean saved) {
		Saved = saved;
	}

	public String getName() {
		return Class_Name;
	}

	public static void setDSSClasses(com.epri.dss.common.DSSClasses dSSClasses) {
		DSSClasses = dSSClasses;
	}

	public static com.epri.dss.common.DSSClasses getDSSClasses() {
		return DSSClasses;
	}

}

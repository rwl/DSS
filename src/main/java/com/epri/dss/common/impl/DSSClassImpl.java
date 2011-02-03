package com.epri.dss.common.impl;

import com.epri.dss.common.DSSGlobals;
import com.epri.dss.common.DSSClass;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.HashList;

/**
 * Base Class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc
 *
 * @author Richard Lincoln
 */
public class DSSClassImpl implements DSSClass {

	protected String Class_Name;

	/* index of present ActiveElement */
	protected int ActiveElement;

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

		this.ActiveElement = 0;
		this.ActiveProperty = 0;

		this.ElementNameList = new HashListImpl(100);
		this.ElementNamesOutOfSynch = false;
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

	protected void finalize() throws Throwable {
		// Get rid of space occupied by strings
		for (int i = 0; i < this.NumProperties; i++) this.PropertyName[i] = "";
		for (int i = 0; i < this.NumProperties; i++) this.PropertyHelp[i] = "";

		this.PropertyName = new String[0];
		this.PropertyHelp = new String[0];
		this.PropertyIdxMap = new int[0];
		this.RevPropertyIdxMap = new int[0];
		this.ElementList.Free();
		this.ElementNameList.Free();
		this.CommandList.Free();

		super.finalize();
	}

	public int getActive() {
		return this.ActiveElement;
	}

	public void setActive(int Value) {
		if ((Value > 0) && (Value <= ElementList.ListSize)) {
			DSSGlobals Globals = DSSGlobals.getInstance();
			this.ActiveElement = Value;
			Globals.ActiveDSSObject = this.ElementList.Get(this.ActiveElement);
			// Make sure Active Ckt Element agrees if is a ckt element
			// So COM interface will work
			if (Globals.ActiveDSSObject instanceof DSSCktElement) {
				Globals.ActiveCircuit.ActiveCktElement = DSSCktElement(Globals.ActiveDSSObject);
			}
		}
	}

	public int getElementCount() {
		return this.ElementList.ListSize();
	}

	public int getFirst() {
		int Result;
		if (this.ElementList.ListSize == 0) {
			Result = 0;
		} else {
			DSSGlobals Globals = DSSGlobals.getInstance();

			this.ActiveElement = 1;
			Globals.ActiveDSSObject = ElementList.First();
			// Make sure Active Ckt Element agrees if is a ckt element
			if (Globals.ActiveDSSObject instanceof DSSCktElement) {
				Globals.ActiveCircuit.ActiveCktElement = new DSSCktElement(Globals.ActiveDSSObject);
				Result = this.ActiveElement;
			}
		}
		return Result;
	}

	public int getNext() {
		int Result;
		if (this.ActiveElement > this.ElementList.ListSize) {
			Result = 0;
		} else {
			DSSGlobals Globals = DSSGlobals.getInstance();

			Globals.ActiveDSSObject = this.ElementList.Next();
			// Make sure Active Ckt Element agrees if is a ckt element
			if (Globals.ActiveDSSObject instanceof DSSCktElement) {
				Globals.ActiveCircuit.ActiveCktElement =  new DSSCktElement(Globals.ActiveDSSObject);
				Result = this.ActiveElement;
			}
		}
		this.ActiveElement += 1;

		return Result;
	}

	public String getName() {
		return this.Class_Name;
	}

	private void resynchElementNameList() {
		reallocateElementNameList();
		this.ElementNamesOutOfSynch = false;
	}

	/* Used by NewObject */
	protected int addObjectToList(Object Obj) {
		// Stuff it in this collection's element list
		this.ElementList.New(Obj);
		this.ElementNameList.Add(new DSSObjectImpl(Obj).getName());

		if (this.ElementList.ListSize() > 2 * this.ElementNameList.InitialAllocation)
			reallocateElementNameList();

		this.ActiveElement = this.ElementList.ListSize();
		return this.ActiveElement; // Return index of object in list
	}

	public String getFirstPropertyName() {
		this.ActiveProperty = 0;
		return getNextPropertyName();
	}

	public String getNextPropertyName() {
		String Result;

		if (this.ActiveProperty <= this.NumProperties) {
			Result = this.PropertyName[ActiveProperty];
		} else {
			Result = "";
		}

		this.ActiveProperty += 1;

		return Result;
	}

	protected int makeLike(String ObjName) {
		DSSGlobals.getInstance().doSimpleMsg("virtual function TDSSClass.MakeLike called.  Should be overriden.", 784);
		return 0;
	}

	/* Add no. of intrinsic properties */
	protected void countProperties() {
		this.NumProperties = this.NumProperties + 1;
	}

	protected void allocatePropertyArrays() {
		this.PropertyName = new String[this.NumProperties];
		this.PropertyHelp = new String[this.NumProperties];
		this.PropertyIdxMap = new int[this.NumProperties];
		this.RevPropertyIdxMap = new int[this.NumProperties];
		this.ActiveProperty = 0;    // initialize for AddProperty
		/* initialize PropertyIdxMap to take care of legacy items */
		for (int i = 0; i < this.NumProperties; i++) this.PropertyIdxMap[i] = i;
		for (int i = 0; i < this.NumProperties; i++) this.RevPropertyIdxMap[i] = i;
	}

	/* Add Properties of this class to propName */
	protected void defineProperties() {
		String CRLF = DSSGlobals.getInstance().CRLF;

		this.PropertyName[ActiveProperty] = "like";
		this.PropertyHelp[ActiveProperty] = "Make like another object, e.g.:" + CRLF + CRLF +
						"New Capacitor.C2 like=c1  ...";

		this.ActiveProperty = this.ActiveProperty + 1;
	}

	protected int classEdit(Object ActiveObj, int ParamPointer) {
		// continue parsing with contents of Parser
		if (ParamPointer > 0) {
			DSSObjectImpl obj = new DSSObjectImpl(ActiveObj);
			switch (ParamPointer) {
			case 1:
				MakeLike(Parser.StrValue);    // Like command (virtual)
			}
		}
		return 0;
	}

	/* Helper routine for building Property strings */
	public void addProperty(String PropName, int CmdMapIndex,
			String HelpString) {
		this.ActiveProperty += 1;

		this.PropertyName[ActiveProperty] = PropName;
		this.PropertyHelp[ActiveProperty] = HelpString;
		// Map to internal object property index
		this.PropertyIdxMap[ActiveProperty] = CmdMapIndex;
		this.RevPropertyIdxMap[CmdMapIndex] = ActiveProperty;
	}

	public void reallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
		this.ElementNameList.Free(); // Throw away the old one.
		this.ElementNameList = new HashListImpl(2 * this.ElementList.ListSize());  // make a new one

		// Do this using the Names of the Elements rather than the old list because it might be
		// messed up if an element gets renamed

		for (int i = 0; i < this.ElementList.ListSize(); i++) {
			this.ElementNameList.add(new DSSObjectImpl(ElementList.Get(i)).getName());
		}
	}

	/* uses global parser */
	public int edit() {
		DSSGlobals.getInstance().DoSimpleMsg("virtual function DSSClass.Edit called.  Should be overriden.", 781);
		return 0;
	}

	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("virtual function DSSClass.Init called.  Should be overriden.", 782);
		return 0;
	}

	public int newObject(String ObjName) {
		DSSGlobals.getInstance().doErrorMsg(
				"Reached base class of TDSSClass for device \"" +ObjName+ "\"",
				"N/A",
				"Should be overridden.", 780);
		return 0;
	}

	public boolean setActive(String Value) {
		boolean Result = false;
		// Faster to look in hash list 7/7/03
		if (this.ElementNamesOutOfSynch) resynchElementNameList();
		int idx = this.ElementNameList.Find(ObjName);
		if (idx > 0) {
			this.ActiveElement = idx;
			DSSGlobals.getInstance().ActiveDSSObject = ElementList.Get(idx);
			Result = true;
		}
		return Result;
	}

	/* Get address of active obj of this class */
	public Object getActiveObj() {
		if (this.ActiveElement > 0) {
			return this.ElementList.Get(ActiveElement);
		} else {
			return null;
		}
	}

	/* Find an obj of this class by name */
	public Object find(String ObjName) {
		Object Result = null;
		if (this.ElementNamesOutOfSynch) resynchElementNameList();
		// Faster to look in hash list 7/7/03
		int idx = this.ElementNameList.Find(ObjName);
		if (idx > 0) {
			this.ActiveElement = idx;
			Result = ElementList.Get(idx);
		}
		return Result;
	}

	/* Find property value by string */
	public int propertyIndex(String Prop) {
		int Result = 0;  // Default result if not found
		for (int i = 0; i < this.NumProperties; i++) {
			if (Prop.equals(this.PropertyName[i])) {
				Result = this.PropertyIdxMap[i];
				break;
			}
		}
		return Result;
	}

}

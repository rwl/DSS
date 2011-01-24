package com.epri.dss.common;

import com.epri.dss.general.DSSObject;

/**
 * Base Class for all DSS collection classes.
 * Keeps track of objects of each class, dispatches edits, etc
 *
 * @author Richard Lincoln
 */
public class DSSClass {

	protected String Class_Name;

	/* index of present ActiveElement */
	protected int ActiveElement;

	protected CommandList CommandList;

	protected int ActiveProperty;

	// TODO: Substitute HashList with LinkedHashMap
	protected HashList ElementNameList;

	public int NumProperties;

	public String[] PropertyName, PropertyHelp;

	public int[] PropertyIdxMap;

	/* maps property to internal command number */
	public int[] RevPropertyIdxMap;

	public int DSSClassType;

	public PointerList ElementList;

	/* When device gets renamed */
	public boolean ElementNamesOutOfSynch;

	public boolean Saved;

	public DSSClass() {
		super();
		// Init size and increment
		this.ElementList = new PointerList(20);
		this.PropertyName = null;
		this.PropertyHelp = null;
		this.PropertyIdxMap = null;
		this.RevPropertyIdxMap = null;

		this.ActiveElement = 0;
		this.ActiveProperty = 0;

		this.ElementNameList = new HashList(100);
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
		this.ElementList.Free();
		this.ElementNameList.Free();
		this.CommandList.Free();

		super.finalize();
	}

	public int Get_Active() {
		return this.ActiveElement;
	}

	public void Set_Active(int Value) {
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

	public int Get_ElementCount() {
		return this.ElementList.ListSize();
	}

	public int Get_First() {
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

	public int Get_Next() {
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

	public String Get_Name() {
		return this.Class_Name;
	}

	private void ResynchElementNameList() {
		ReallocateElementNameList();
	    this.ElementNamesOutOfSynch = false;
	}

	/* Used by NewObject */
	protected int AddObjectToList(Object Obj) {
		// Stuff it in this collection's element list
		this.ElementList.New(Obj);
		this.ElementNameList.Add(new DSSObject(Obj).Get_Name());

		if (this.ElementList.ListSize() > 2 * this.ElementNameList.InitialAllocation)
			ReallocateElementNameList();

	    this.ActiveElement = this.ElementList.ListSize();
	    return this.ActiveElement; // Return index of object in list
	}

	public String Get_FirstPropertyName() {
		this.ActiveProperty = 0;
    	return Get_NextPropertyName();
	}

	public String Get_NextPropertyName() {
		String Result;

	    if (this.ActiveProperty <= this.NumProperties) {
	    	Result = this.PropertyName[ActiveProperty];
	    } else {
	    	Result = "";
	    }

	    this.ActiveProperty += 1;

		return Result;
	}

	protected int MakeLike(String ObjName) {
		DSSGlobals.getInstance().DoSimpleMsg("virtual function TDSSClass.MakeLike called.  Should be overriden.", 784);
		return 0;
	}

	/* Add no. of intrinsic properties */
	protected void CountProperties() {
		this.NumProperties = this.NumProperties + 1;
	}

	protected void AllocatePropertyArrays() {
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
	protected void DefineProperties() {
		String CRLF = DSSGlobals.getInstance().CRLF;

		this.PropertyName[ActiveProperty] = "like";
		this.PropertyHelp[ActiveProperty] = "Make like another object, e.g.:" + CRLF + CRLF +
	                      "New Capacitor.C2 like=c1  ...";

		this.ActiveProperty = this.ActiveProperty + 1;
	}

	protected int ClassEdit(Object ActiveObj, int ParamPointer) {
		// continue parsing with contents of Parser
		if (ParamPointer > 0) {
			DSSObject obj = new DSSObject(ActiveObj);
			switch (ParamPointer) {
			case 1:
				MakeLike(Parser.StrValue);    // Like command (virtual)
			}
		}
		return 0;
	}

	/* Helper routine for building Property strings */
	public void AddProperty(String PropName, int CmdMapIndex,
			String HelpString) {
		this.ActiveProperty += 1;

	    this.PropertyName[ActiveProperty] = PropName;
	    this.PropertyHelp[ActiveProperty] = HelpString;
	    // Map to internal object property index
	    this.PropertyIdxMap[ActiveProperty] = CmdMapIndex;
	    this.RevPropertyIdxMap[CmdMapIndex] = ActiveProperty;
	}

	public void ReallocateElementNameList() {
		/* Reallocate the device name list to improve the performance of searches */
	    this.ElementNameList.Free(); // Throw away the old one.
	    this.ElementNameList = new HashList(2 * this.ElementList.ListSize());  // make a new one

	    // Do this using the Names of the Elements rather than the old list because it might be
	    // messed up if an element gets renamed

	    for (int i = 0; i < this.ElementList.ListSize(); i++) {
	    	this.ElementNameList.Add(new DSSObject(ElementList.Get(i)).Get_Name());
	    }
	}

	/* uses global parser */
	public int Edit() {
		DSSGlobals.getInstance().DoSimpleMsg("virtual function DSSClass.Edit called.  Should be overriden.", 781);
		return 0;
	}

	public int Init(int Handle) {
		DSSGlobals.getInstance().DoSimpleMsg("virtual function DSSClass.Init called.  Should be overriden.", 782);
		return 0;
	}

	public int NewObject(String ObjName) {
		DSSGlobals.getInstance().DoErrorMsg(
				"Reached base class of TDSSClass for device \"" +ObjName+ "\"",
                "N/A",
                "Should be overridden.", 780);
		return 0;
	}

	public boolean SetActive(String Value) {
		boolean Result = false;
		// Faster to look in hash list 7/7/03
		if (this.ElementNamesOutOfSynch) ResynchElementNameList();
		int idx = this.ElementNameList.Find(ObjName);
		if (idx > 0) {
			this.ActiveElement = idx;
			DSSGlobals.getInstance().ActiveDSSObject = ElementList.Get(idx);
			Result = true;
		}
		return Result;
	}

	/* Get address of active obj of this class */
	public Object GetActiveObj() {
		if (this.ActiveElement > 0) {
	        return this.ElementList.Get(ActiveElement);
		} else {
	        return null;
		}
	}

	/* Find an obj of this class by name */
	public Object Find(String ObjName) {
		Object Result = null;
		if (this.ElementNamesOutOfSynch) ResynchElementNameList();
		// Faster to look in hash list 7/7/03
		int idx = this.ElementNameList.Find(ObjName);
		if (idx > 0) {
			this.ActiveElement = idx;
			Result = ElementList.Get(idx);
		}
		return Result;
	}

	/* Find property value by string */
	public int PropertyIndex(String Prop) {
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

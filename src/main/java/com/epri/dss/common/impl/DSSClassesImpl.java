package com.epri.dss.common.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.DSSClasses;

public class DSSClassesImpl implements DSSClasses {
	
	public void setNew(DSSClass Value) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		Globals.getDSSClassList().add(Value); // Add to class list
		Globals.setActiveDSSClass(Value);     // Declare to be active
		Globals.getClassNames().add(Globals.getActiveDSSClass().getName()); // Add to classname list
	}

}

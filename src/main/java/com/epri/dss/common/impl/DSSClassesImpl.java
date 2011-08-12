package com.epri.dss.common.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.DSSClasses;

public class DSSClassesImpl implements DSSClasses {

	public void setNew(DSSClass value) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.getDSSClassList().add(value);  // add to class list
		globals.setActiveDSSClass(value);      // declare to be active
		globals.getClassNames().add(globals.getActiveDSSClass().getName());  // add to classname list
	}

}

package com.epri.dss.common.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.DSSClasses;

public class DSSClassesImpl implements DSSClasses {

	public void setNew(DSSClass value) {

		DSSGlobals.DSSClassList.add(value);  // add to class list
		DSSGlobals.activeDSSClass = value;      // declare to be active
		DSSGlobals.classNames.add(DSSGlobals.activeDSSClass.getName());  // add to classname list
	}

}

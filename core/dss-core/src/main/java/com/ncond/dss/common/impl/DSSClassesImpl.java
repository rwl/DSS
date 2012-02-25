package com.ncond.dss.common.impl;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClasses;

public class DSSClassesImpl implements DSSClasses {

	public void setNew(DSSClass value) {

		DSSGlobals.DSSClassList.add(value);  // add to class list
		DSSGlobals.activeDSSClass = value;      // declare to be active
		DSSGlobals.classNames.add(DSSGlobals.activeDSSClass.getName());  // add to classname list
	}

}

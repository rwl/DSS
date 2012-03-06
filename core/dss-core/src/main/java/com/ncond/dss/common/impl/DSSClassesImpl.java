package com.ncond.dss.common.impl;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClasses;

public class DSSClassesImpl implements DSSClasses {

	@Override
	public void setNew(DSSClass value) {

		DSS.DSSClassList.add(value);  // add to class list
		DSS.activeDSSClass = value;      // declare to be active
		DSS.classNames.add(DSS.activeDSSClass.getName());  // add to classname list
	}

}

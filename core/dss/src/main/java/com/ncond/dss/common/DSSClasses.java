package com.ncond.dss.common;


/**
 * Collection of all DSS Classes.
 */
public class DSSClasses {

	public void setNew(DSSClass value) {
		DSS.DSSClassList.add(value);  // add to class list
		DSS.activeDSSClass = value;      // declare to be active
		DSS.classNames.add(DSS.activeDSSClass.getClassName());  // add to classname list
	}

}

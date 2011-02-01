package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.LoadShape;

/* Superstructure for all LoadShape objects */
public class LoadShapeImpl extends DSSClassImpl implements LoadShape {

	public LoadShapeImpl() {
		// TODO Auto-generated constructor stub
	}

	/* Returns active LoadShape string */
	public String getCode() {
		return null;
	}

	/* Sets the active LoadShape */
	public void setCode(String Value) {

	}

	private void doCSVFile(String FileName) {

	}

	private void doSngFile(String FileName) {

	}

	private void doDblFile(String FileName) {

	}

	protected void defineProperties() {

	}

	protected int makeLike(String ShapeName) {
		return 0;
	}

	public int edit() {
		return 0;
	}

	public int init(int Handle) {
		return 0;
	}

	public int newObject(String ObjName) {
		return 0;
	}

	/* Find an obj of this class by name */
	public Object find(String ObjName) {
		return null;
	}

	public void tOPExport(String ObjName) {

	}

}

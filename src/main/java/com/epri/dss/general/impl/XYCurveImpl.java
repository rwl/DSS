package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.XYCurve;
import com.epri.dss.general.XYCurveObj;

public class XYCurveImpl extends DSSClassImpl implements XYCurve {

	private static XYCurveObj ActiveXYCurveObj;

	public XYCurveImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return null;
	}

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

	@Override
	protected int makeLike(String CNName) {
		return 0;
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		return 0;
	}

	@Override
	public int init(int Handle) {
		return 0;
	}

	@Override
	public int newObject(String ObjName) {
		return 0;
	}

	/**
	 * Find an obj of this class by name.
	 */
	@Override
	public Object find(String ObjName) {
		return null;
	}

	public static void setActiveXYCurveObj(XYCurveObj activeXYCurveObj) {
		ActiveXYCurveObj = activeXYCurveObj;
	}

	public static XYCurveObj getActiveXYCurveObj() {
		return ActiveXYCurveObj;
	}

}

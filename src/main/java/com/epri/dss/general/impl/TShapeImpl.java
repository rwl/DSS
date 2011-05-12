package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.TShape;
import com.epri.dss.general.TShapeObj;

public class TShapeImpl extends DSSClassImpl implements TShape {

	private static TShapeObj ActiveTShapeObj;

	public TShapeImpl() {
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

	public void TOPExport(String ObjName) {
		// can export this to top for plotting
	}

	public static void setActiveTShapeObj(TShapeObj activeTShapeObj) {
		ActiveTShapeObj = activeTShapeObj;
	}

	public static TShapeObj getActiveTShapeObj() {
		return ActiveTShapeObj;
	}

}

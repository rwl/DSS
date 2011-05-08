package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.PriceShape;
import com.epri.dss.general.PriceShapeObj;

public class PriceShapeImpl extends DSSClassImpl implements PriceShape {

	private static PriceShapeObj ActivePriceShapeObj;

	public PriceShapeImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return null;
	}

	public void setCode(String Value) {

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

	public static void setActivePriceShapeObj(PriceShapeObj activePriceShapeObj) {
		ActivePriceShapeObj = activePriceShapeObj;
	}

	public static PriceShapeObj getActivePriceShapeObj() {
		return ActivePriceShapeObj;
	}

}

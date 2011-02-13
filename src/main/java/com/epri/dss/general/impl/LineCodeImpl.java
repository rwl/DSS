package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineCodeObj;

public class LineCodeImpl extends DSSClassImpl implements LineCode {
	
	public static LineCodeObj ActiveLineCodeObj;
	
	public static final int NumPropsThisClass = 22;
	
	private boolean SymComponentsChanged;

	private boolean MatrixChanged;

	public LineCodeImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return null;
	}
	
	/**
	 * Sets the active linecode.
	 */
	public void setCode(String Value) {
		
	}

	private void setZ1Z0(int i, double Value) {
		
	}
	
	/**
	 * Decode units specification.
	 */
	private void setUnits(String S) {
		
	}

	/**
	 * Set impedances as matrices.
	 */
	private void doMatrix(int i) {
		
	}
	
	protected void defineProperties() {
		
	}
	
	@Override
	protected int makeLike(String LineName) {
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


}

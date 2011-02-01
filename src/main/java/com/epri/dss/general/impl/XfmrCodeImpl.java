package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.XfmrCode;

public class XfmrCodeImpl extends DSSClassImpl implements XfmrCode {
	
	private enum WdgParmChoice {Conn, kV, kVA, R, Tap};

	public XfmrCodeImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private void setActiveWinding(int w) {
		
	}
	
	private void interpretWindings(String S, WdgParmChoice which) {
		
	}
	
	public String getCode() {
		return null;
	}
	
	public void setCode(String Value) {
		
	}
	
	@Override
	protected int makeLike(String Name) {
		return 0;
	}
	
	protected void defineProperties() {
		
	}
	
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

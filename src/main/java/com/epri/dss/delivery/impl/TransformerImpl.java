package com.epri.dss.delivery.impl;

import com.epri.dss.delivery.Transformer;
import com.epri.dss.delivery.TransformerObj;

public class TransformerImpl extends PDClassImpl implements Transformer {
	
	private static TransformerObj ActiveTransfObj;

	public TransformerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private void setActiveWinding(int w) {
		
	}
	
	private void interpretConnection(String S) {
		
	}
	
	private void interpretAllConns(String S) {
		
	}
	
	private void interpretAllBuses(String S) {
		
	}
	
	private void interpretAllTaps(String S) {
		
	}
	
	private void interpretAllkVRatings(String S) {
		
	}
	
	private void interpretAllkVARatings(String S) {
		
	}
	
	private void interpretAllRs(String S) {
		
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

	public static TransformerObj getActiveTransfObj() {
		return ActiveTransfObj;
	}

	public static void setActiveTransfObj(TransformerObj activeTransfObj) {
		ActiveTransfObj = activeTransfObj;
	}

}

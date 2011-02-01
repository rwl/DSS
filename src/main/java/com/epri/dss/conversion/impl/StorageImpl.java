package com.epri.dss.conversion.impl;

import com.epri.dss.conversion.Storage;

public class StorageImpl extends PCClassImpl implements Storage {

	private static final int NumStorageRegisters = 6;    
	private static final int NumStorageVariables = 4;

	private String[] RegisterNames = new String[NumStorageRegisters];

	public StorageImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String[] getRegisterNames() {
		return RegisterNames;
	}

	public void setRegisterNames(String[] registerNames) {
		RegisterNames = registerNames;
	}

	private void interpretConnection(String S) {
		
	}
	
	private void setNcondsForConnection() {
		
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
	
	public void resetRegistersAll() {
		
	}
	
	public void sampleAll() {
		
	}
	
	public void updateAll() {
		
	}

}

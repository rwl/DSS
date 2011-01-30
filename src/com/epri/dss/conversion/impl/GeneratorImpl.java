package com.epri.dss.conversion.impl;

import com.epri.dss.conversion.Generator;

public class GeneratorImpl extends PCClassImpl implements Generator {
	
	/* Number of energy meter registers */
	private static final int NumGenRegisters = 6;    
	private static final int NumGenVariables = 6;
	
	private String[] RegisterNames = new String[NumGenRegisters];

	public GeneratorImpl() {
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
	
	public void resetRegistersAll() {
		
	}
	
	public void sampleAll() {
		
	}

}

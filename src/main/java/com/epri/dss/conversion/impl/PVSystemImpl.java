package com.epri.dss.conversion.impl;

import com.epri.dss.conversion.PVSystem;
import com.epri.dss.conversion.PVSystemObj;

public class PVSystemImpl extends PCClassImpl implements PVSystem {

	private static PVSystemObj ActivePVsystemObj;

	private String[] RegisterNames;

	public PVSystemImpl() {
		// TODO Auto-generated constructor stub
	}

	private void interpretConnection(String S) {

	}

	private void setNcondsForConnection() {

	}

	protected void defineProperties() {

	}

	@Override
	protected int makeLike(String OtherGeneratorName) {
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

	public void setRegisterNames(String[] registerNames) {
		RegisterNames = registerNames;
	}

	public String[] getRegisterNames() {
		return RegisterNames;
	}

	public void resetRegistersAll() {

	}

	public void sampleAll() {

	}

	public void updateAll() {

	}

	public static void setActivePVsystemObj(PVSystemObj activePVsystemObj) {
		ActivePVsystemObj = activePVsystemObj;
	}

	public static PVSystemObj getActivePVsystemObj() {
		return ActivePVsystemObj;
	}

}

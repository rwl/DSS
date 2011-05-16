package com.epri.dss.shared.impl;

public class LineUnits {

	public static final int UNITS_MAXNUM = 9;
	public static final int UNITS_NONE  = 0;
	public static final int UNITS_MILES = 1;
	public static final int UNITS_KFT   = 2;
	public static final int UNITS_KM    = 3;
	public static final int UNITS_M     = 4;
	public static final int UNITS_FT    = 5;
	public static final int UNITS_IN    = 6;
	public static final int UNITS_CM    = 7;
	public static final int UNITS_MM    = 8;

	private LineUnits() {

	}

	public static int getUnitsCode(String S) {
		return 0;
	}

	public static String lineUnitsStr(int Units) {
		return null;
	}

	// Conversion to and from meters and per meter

	public static double toMeters(int Units) {
		return 0.0;
	}

	public static double toPerMeter(int Units) {
		return 0.0;
	}

	public static double fromPerMeter(int Units) {
		return 0.0;
	}

	public static double fromMeters(int Units) {
		return 0.0;
	}

	public static double convertLineUnits(int FromUnits, int ToUnits) {
		return 0.0;
	}

}

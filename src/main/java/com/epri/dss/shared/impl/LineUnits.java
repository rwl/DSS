package com.epri.dss.shared.impl;

public abstract class LineUnits {

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

	public static int getUnitsCode(String s) {

		String sTest = s.substring(0, 2);  // copy first 2 chars for most of the test
		if (sTest.equalsIgnoreCase("no")) {
			return UNITS_NONE;  // no units specified
		} else if (sTest.equalsIgnoreCase("mi")) {
			return UNITS_MILES;  // per mile
		} else if (sTest.equalsIgnoreCase("kf")) {
			return UNITS_KFT;  // per 1000 ft (kft)
		} else if (sTest.equalsIgnoreCase("km")) {
			return UNITS_KM;  // per km
		} else if (sTest.equalsIgnoreCase("m")) {
			return UNITS_M;  // per meter
		} else if (sTest.equalsIgnoreCase("me")) {
			return UNITS_M;  // per meter
		} else if (sTest.equalsIgnoreCase("ft")) {
			return UNITS_FT;
		} else if (sTest.equalsIgnoreCase("in")) {
			return UNITS_IN;
		} else if (sTest.equalsIgnoreCase("cm")) {
			return UNITS_CM;
		} else if (sTest.equalsIgnoreCase("mm")) {
			return UNITS_MM;
		}

		return 0;
	}

	public static String lineUnitsStr(int units) {

		switch (units) {
		case 0:
			return "none";
		case UNITS_MILES:
			return "mi";
		case UNITS_KFT:
			return "kft";
		case UNITS_KM:
			return "km";
		case UNITS_M :
			return "m";
		case UNITS_FT:
			return "ft";
		case UNITS_IN:
			return "in";
		case UNITS_CM:
			return "cm";
		case UNITS_MM:
			return "mm";
		default:
			return "none";
		}
	}

	// Conversion to and from meters and per meter

	public static double toMeters(int units) {
		switch (units) {
		case UNITS_MILES:
			return 1609.3;
		case UNITS_KFT:
			return 304.8;
		case UNITS_KM:
			return 1000.0;
		case UNITS_M:
			return 1.0;
		case UNITS_FT:
			return 0.3048;
		case UNITS_IN:
			return 0.0254;
		case UNITS_CM:
			return 0.01;
		case UNITS_MM:
			return 0.001;
		default:
			return 1.0;
		}
	}

	public static double toPerMeter(int units) {
		return 1.0 / toMeters(units);
	}

	public static double fromPerMeter(int units) {
		return toMeters(units);
	}

	public static double fromMeters(int units) {
		return 1.0 / toMeters(units);
	}

	public static double convertLineUnits(int fromUnits, int toUnits) {
		if (fromUnits == UNITS_NONE || toUnits == UNITS_NONE) {
			return 1.0;  // don't know what to convert
		} else {
			return fromMeters(toUnits) * toMeters(fromUnits);
		}
	}

}

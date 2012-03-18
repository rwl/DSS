/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.shared;

public enum LineUnits {

	NONE   (0),
	MILES  (1),
	KFT    (2),
	KM     (3),
	M      (4),
	FT     (5),
	IN     (6),
	CM     (7),
	MM     (8);

	private int code;

	private LineUnits(int code) {
		this.code = code;
	}

	public int code() {
		return this.code;
	}

	public static LineUnits interpretUnitsCode(String s) {
		String ss = s.substring(0, 2);  // copy first 2 chars for most of the test

		if (ss.equalsIgnoreCase("no")) {
			return NONE;  // no units specified
		} else if (ss.equalsIgnoreCase("mi")) {
			return MILES;  // per mile
		} else if (ss.equalsIgnoreCase("kf")) {
			return KFT;  // per 1000 ft (kft)
		} else if (ss.equalsIgnoreCase("km")) {
			return KM;  // per km
		} else if (ss.equalsIgnoreCase("m")) {
			return M;  // per meter
		} else if (ss.equalsIgnoreCase("me")) {
			return M;  // per meter
		} else if (ss.equalsIgnoreCase("ft")) {
			return FT;
		} else if (ss.equalsIgnoreCase("in")) {
			return IN;
		} else if (ss.equalsIgnoreCase("cm")) {
			return CM;
		} else if (ss.equalsIgnoreCase("mm")) {
			return MM;
		}

		return NONE;
	}

	public static String lineUnitsStr(LineUnits units) {

		switch (units) {
		case NONE:
			return "none";
		case MILES:
			return "mi";
		case KFT:
			return "kft";
		case KM:
			return "km";
		case M :
			return "m";
		case FT:
			return "ft";
		case IN:
			return "in";
		case CM:
			return "cm";
		case MM:
			return "mm";
		default:
			return "none";
		}
	}

	// Conversion to and from meters and per meter

	public static double toMeters(LineUnits units) {
		switch (units) {
		case MILES:
			return 1609.3;
		case KFT:
			return 304.8;
		case KM:
			return 1000.0;
		case M:
			return 1.0;
		case FT:
			return 0.3048;
		case IN:
			return 0.0254;
		case CM:
			return 0.01;
		case MM:
			return 0.001;
		default:
			return 1.0;
		}
	}

	public static double toPerMeter(LineUnits units) {
		return 1.0 / toMeters(units);
	}

	public static double fromPerMeter(LineUnits units) {
		return toMeters(units);
	}

	public static double fromMeters(LineUnits units) {
		return 1.0 / toMeters(units);
	}

	public static double convertLineUnits(LineUnits fromUnits, LineUnits toUnits) {
		if (fromUnits == NONE || toUnits == NONE) {
			return 1.0;  // don't know what to convert
		} else {
			return fromMeters(toUnits) * toMeters(fromUnits);
		}
	}

}

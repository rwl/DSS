package com.epri.dss.meter;

public interface Monitor extends MeterClass {

	static final int SEQUENCEMASK = 16;
	static final int MAGNITUDEMASK = 32;
	static final int POSSEQONLYMASK = 64;
	static final int MODEMASK = 15;

	static final int NumPropsThisClass = 7;

	void TOPExport(String ObjName);

}

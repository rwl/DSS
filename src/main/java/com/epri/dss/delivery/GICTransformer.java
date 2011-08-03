package com.epri.dss.delivery;

/**
 * Special resistance-only model of transformers for geomagnetically-induced
 * current (GIC) studies.
 */
public interface GICTransformer extends PDClass {

	static final int NumPropsThisClass = 8;

	static final int SPEC_GSU  = 1;
	static final int SPEC_AUTO = 2;
	static final int SPEC_YY   = 3;

}

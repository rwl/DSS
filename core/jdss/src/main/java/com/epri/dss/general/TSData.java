package com.epri.dss.general;

public interface TSData extends CableData {

	static final int NumPropsThisClass = 3;

	String getCode();

	void setCode(String value);

}

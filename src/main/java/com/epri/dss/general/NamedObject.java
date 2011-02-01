package com.epri.dss.general;

import java.util.UUID;

public interface NamedObject {

	String getDisplayName();

	void setDisplayName(String Value);

	void setGUID(UUID Value);

	String getID();

	String getCIM_ID();

	String getDSSClassName();

	void setDSSClassName(String Value);

	String getLocalName();

	void setLocalName(String Value);

	String uUIDToCIMString(UUID uUID);

}

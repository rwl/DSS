package com.epri.dss.general;

import java.util.UUID;

public interface NamedObject {

	String getQualifiedName();

	String getDisplayName();

	void setDisplayName(String value);

	void setUUID(UUID value);

	String getID();

	String getCIM_ID();

	String getDSSClassName();

	void setDSSClassName(String value);

	String getLocalName();

	void setLocalName(String value);

	String UUIDToCIMString(UUID uUID);

}

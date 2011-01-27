package com.epri.dss.general;

import java.util.UUID;

public interface NamedObject {

	public String getDisplayName();

	public void setDisplayName(String Value);

	public void setGUID(UUID Value);

	public String getID();

	public String getCIM_ID();

	public String getDSSClassName();

	public void setDSSClassName(String Value);

	public String getLocalName();

	public void setLocalName(String Value);

	public String uUIDToCIMString(UUID uUID);

}

package com.epri.dss.general;

import java.util.UUID;

public interface NamedObject {

	public String Get_DisplayName();

	public void Set_DisplayName(String Value);

	public void Set_GUID(UUID Value);

	public String Get_ID();

	public String Get_CIM_ID();

	public String Get_DSSClassName();

	public void Set_DSSClassName(String Value);

	public String Get_LocalName();

	public void Set_LocalName(String Value);

	public String GUIDToCIMString(UUID GUID);

}

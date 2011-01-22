/**
 * Copyright (C) 2009 Electric Power Research Institute, Inc.
 * Copyright (C) 2011 Richard Lincoln
 *
 * All rights reserved.
 */

package com.epri.dss.general;

import java.util.UUID;

public class NamedObject {

	/* path name, or class name for DSS */
	private String PName;

	/* localName is unique within a class, like the old FName */
	private String LName;

	/* for optional display, does not have to be unique */
	private String DName;

	private UUID pGuid;


	public NamedObject(String ClassName) {
		super();
		this.PName = ClassName;
		this.LName = "";
		this.DName = "";
		this.pGuid = null;
	}

	protected void finalize() throws Throwable {
		if (this.pGuid != null)
			this.pGuid = null;
	}


	public String Get_DisplayName() {
		if (this.DName == "") {
			return this.PName + "_" + this.LName;
		} else {
			return this.DName;
		}
	}

	public void Set_DisplayName(String Value) {
		this.DName = Value;
	}

	private UUID Get_GUID() {
		if (this.pGuid == null) {
			this.pGuid = UUID.randomUUID();
		}
		return this.pGuid;
	}

	public void Set_GUID(UUID Value) {
		//if (this.pGuid == null) {}
		this.pGuid = Value;
	}

	public String Get_ID() {
		return Get_GUID().toString();
	}

	public String Get_CIM_ID() {
		return GUIDToCIMString(Get_GUID());
	}


	public String Get_DSSClassName() {
		return this.PName;
	}

	public void Set_DSSClassName(String Value) {
		this.PName = Value;
	}

	public String Get_LocalName() {
		return this.LName;
	}

	public void Set_LocalName(String Value) {
		this.LName = Value;
	}


	public static String GUIDToCIMString(UUID GUID) {
		String s;
		s = GUID.toString();
		return s.substring(1, s.length() - 2);
	}

}

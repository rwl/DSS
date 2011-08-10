/**
 * Copyright (C) 2009 Electric Power Research Institute, Inc.
 * Copyright (C) 2011 Richard Lincoln
 *
 * All rights reserved.
 */

package com.epri.dss.general.impl;

import java.util.UUID;

import com.epri.dss.general.NamedObject;

public class NamedObjectImpl implements NamedObject {

	/**
	 * Path name, or class name for DSS.
	 */
	private String PName;

	/**
	 * LocalName is unique within a class.
	 */
	private String LName;

	/**
	 * For optional display, does not have to be unique.
	 */
	private String DName;

	private UUID pGuid;


	public NamedObjectImpl(String ClassName) {
		super();
		this.PName = ClassName;
		this.LName = "";
		this.DName = "";
		this.pGuid = null;
	}

	protected void finalize() throws Throwable {
		if (pGuid != null)
			pGuid = null;
	}

	public String getDisplayName() {
		if (this.DName == "") {
			return PName + "_" + LName;
		} else {
			return DName;
		}
	}

	public void setDisplayName(String Value) {
		DName = Value;
	}

	public String getQualifiedName() {
		return PName + "." + LName;
	}

	private UUID getUUID() {
		if (pGuid == null) {
			pGuid = UUID.randomUUID();
		}
		return pGuid;
	}

	public void setUUID(UUID Value) {
		//if (pGuid == null) {}
		pGuid = Value;
	}

	public String getID() {
		return getUUID().toString();
	}

	public String getCIM_ID() {
		return UUIDToCIMString(getUUID());
	}


	public String getDSSClassName() {
		return PName;
	}

	public void setDSSClassName(String Value) {
		PName = Value;
	}

	public String getLocalName() {
		return LName;
	}

	public void setLocalName(String Value) {
		LName = Value;
	}


	public String UUIDToCIMString(UUID uUID) {
		String s;
		s = uUID.toString();
		return s.substring(1, s.length() - 2);
	}

}

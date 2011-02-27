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
		if (this.pGuid != null)
			this.pGuid = null;
	}

	public String getDisplayName() {
		if (this.DName == "") {
			return this.PName + "_" + this.LName;
		} else {
			return this.DName;
		}
	}

	public void setDisplayName(String Value) {
		this.DName = Value;
	}
	
	public String getQualifiedName() {
		return PName + "." + LName;
	}

	private UUID getUUID() {
		if (this.pGuid == null) {
			this.pGuid = UUID.randomUUID();
		}
		return this.pGuid;
	}

	public void setUUID(UUID Value) {
		//if (this.pGuid == null) {}
		this.pGuid = Value;
	}

	public String getID() {
		return getUUID().toString();
	}

	public String getCIM_ID() {
		return UUIDToCIMString(getUUID());
	}


	public String getDSSClassName() {
		return this.PName;
	}

	public void setDSSClassName(String Value) {
		this.PName = Value;
	}

	public String getLocalName() {
		return this.LName;
	}

	public void setLocalName(String Value) {
		this.LName = Value;
	}


	public String UUIDToCIMString(UUID uUID) {
		String s;
		s = uUID.toString();
		return s.substring(1, s.length() - 2);
	}

}

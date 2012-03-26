/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.util.UUID;

public class NamedObject {

	/**
	 * Path name, or class name for DSS.
	 */
	private String DSSClassName;

	/**
	 * LocalName is unique within a class.
	 */
	private String localName;

	/**
	 * For optional display, does not have to be unique.
	 */
	private String displayName;

	private UUID uuid;


	public NamedObject(String className) {
		super();
		DSSClassName = className;
		localName = "";
		displayName = "";
		uuid = null;
	}

	public String getDisplayName() {
		if (displayName == "") {
			return DSSClassName + "_" + localName;
		} else {
			return displayName;
		}
	}

	public String getQualifiedName() {
		return DSSClassName + "." + localName;
	}

	private UUID getUUID() {
		if (uuid == null)
			uuid = UUID.randomUUID();
		return uuid;
	}

	public void setUUID(UUID value) {
		uuid = value;
	}

	public String getID() {
		return getUUID().toString();
	}

	public String getCIM_ID() {
		return UUIDToCIMString(getUUID());
	}

	public String UUIDToCIMString(UUID uUID) {
		return uUID.toString();
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getDSSClassName() {
		return DSSClassName;
	}

}

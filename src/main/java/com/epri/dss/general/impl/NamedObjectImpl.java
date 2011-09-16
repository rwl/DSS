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
	private String pathName;

	/**
	 * LocalName is unique within a class.
	 */
	private String localName;

	/**
	 * For optional display, does not have to be unique.
	 */
	private String displayName;

	private UUID uuid;


	public NamedObjectImpl(String className) {
		super();
		pathName = className;
		localName = "";
		displayName = "";
		uuid = null;
	}

	public String getDisplayName() {
		if (this.displayName == "") {
			return pathName + "_" + localName;
		} else {
			return displayName;
		}
	}

	public void setDisplayName(String value) {
		displayName = value;
	}

	public String getQualifiedName() {
		return pathName + "." + localName;
	}

	private UUID getUUID() {
		if (uuid == null)
			uuid = UUID.randomUUID();
		return uuid;
	}

	public void setUUID(UUID value) {
		//if (pGuid == null) {}
		uuid = value;
	}

	public String getID() {
		return getUUID().toString();
	}

	public String getCIM_ID() {
		return UUIDToCIMString(getUUID());
	}


	public String getDSSClassName() {
		return pathName;
	}

	public void setDSSClassName(String value) {
		pathName = value;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String value) {
		localName = value;
	}


	public String UUIDToCIMString(UUID uUID) {
		String s;
		s = uUID.toString();
		return s.substring(1, s.length() - 2);
	}

}

/**
 * Copyright (C) 2009 Electric Power Research Institute, Inc.
 * Copyright (C) 2011 Richard Lincoln
 * 
 * All rights reserved.
 */

package com.epri.dss.general;

public class NamedObject {
	
	/* path name, or class name for DSS */
	private String PName;
	
	/* localName is unique within a class, like the old FName */
	private String LName;
	
	/* for optional display, does not have to be unique */
	private String DName;
	
	private String pGuid;

	public NamedObject(String ClassName) {
		// TODO Auto-generated constructor stub
	}
	
	private String Get_QualifiedName() {
		return null;
	}
	
	private String Get_DisplayName() {
		return null;
	}
	
	private void Set_DisplayName(String Value) {
		
	}
	
	private String Get_GUID() {
		return null;
	}
	
	private String Get_ID() {
		return null;
	}
	
	private String Get_CIM_ID() {
		return null;
	}
	
	private void Set_GUID(String Value) {
		
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
	
	public String getQualifiedName() {
		return Get_QualifiedName();
	}

}

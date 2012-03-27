/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;

public class CableData extends ConductorData {

	private int numCableClassProps;

	public CableData() {
		numCableClassProps = 4;
		classType = DSSClassDefs.DSS_OBJECT;
	}

	@Override
	protected void countProperties() {
		numProperties = numProperties + numCableClassProps;
		super.countProperties();
	}

	@Override
	protected void defineProperties() {
		propertyName[activeProperty + 1] = "EpsR";
		propertyName[activeProperty + 2] = "InsLayer";
		propertyName[activeProperty + 3] = "DiaIns";
		propertyName[activeProperty + 4] = "DiaCable";

		propertyHelp[activeProperty + 1] = "Insulation layer relative permittivity; default is 2.3.";
		propertyHelp[activeProperty + 2] = "Insulation layer thickness; same units as radius; no default. " +
				"With DiaIns, establishes inner radius for capacitance calculation.";
		propertyHelp[activeProperty + 3] = "Diameter over insulation layer; same units as radius; no default." +
				"Establishes outer radius for capacitance calculation.";
		propertyHelp[activeProperty + 4] = "Diameter over cable; same units as radius; no default.";

		activeProperty = activeProperty + numCableClassProps;
		super.defineProperties();
	}

	@Override
	protected int classEdit(DSSObject activeObj, int paramPointer) {
		CableDataObj elem;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			elem = (CableDataObj) activeObj;
			switch (paramPointer) {
			case 0:
				elem.setEpsR(parser.doubleValue());
				break;
			case 1:
				elem.setInsLayer(parser.doubleValue());
				break;
			case 2:
				elem.setDiaIns(parser.doubleValue());
				break;
			case 3:
				elem.setDiaCable(parser.doubleValue());
				break;
			default:
				super.classEdit(activeObj, paramPointer - numCableClassProps);
				break;
			}
			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (elem.getEpsR() < 1.0)
					DSS.doSimpleMsg("Insulation permittivity must be greater than one for CableData " + elem.getName(), 999);
				break;
			case 1:
				if (elem.getInsLayer() <= 0.0)
					DSS.doSimpleMsg("Insulation layer thickness must be positive for CableData " + elem.getName(), 999);
				break;
			case 2:
				if (elem.getDiaIns() <= 0.0)
					DSS.doSimpleMsg("Diameter over insulation layer must be positive for CableData " + elem.getName(), 999);
				break;
			case 3:
				if (elem.getDiaCable() <= 0.0)
					DSS.doSimpleMsg("Diameter over cable must be positive for CableData " + elem.getName(), 999);
				break;
			}
		}

		return 0;
	}

	@Override
	protected void classMakeLike(DSSObject otherObj) {
		CableDataObj other = (CableDataObj) otherObj;
		CableDataObj elem = (CableDataObj) DSS.activeDSSObject;

		elem.setEpsR(other.getEpsR());
		elem.setInsLayer(other.getInsLayer());
		elem.setDiaIns(other.getDiaIns());
		elem.setDiaCable(other.getDiaCable());

		super.classMakeLike(otherObj);
	}

	public void setNumCableClassProps(int num) {
		numCableClassProps = num;
	}

	public int getNumCableClassProps() {
		return numCableClassProps;
	}

	@Override
	public int newObject(String objName) {
		DSS.doSimpleMsg("Need to implement CableData.newObject().", -1);
		return 0;
	}

	@Override
	public int edit() {
		DSS.doSimpleMsg("Need to implement CableData.edit", -1);
		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement CableData.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String objName) {
		DSS.doSimpleMsg("Need to implement CableData.makeLike", -1);
		return 0;
	}

}

package com.ncond.dss.general.impl;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.general.CableData;
import com.ncond.dss.general.CableDataObj;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.parser.impl.Parser;

public class CableDataImpl extends ConductorDataImpl implements CableData {

	private int numCableClassProps;

	public CableDataImpl() {
		numCableClassProps = 4;
		classType = DSSClassDefs.DSS_OBJECT;
	}

	protected void countProperties() {
		numProperties = numProperties + numCableClassProps;
		super.countProperties();
	}

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

	protected int classEdit(DSSObject activeObj, int paramPointer) {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			CableDataObj cd = (CableDataObj) activeObj;
			switch (paramPointer) {
			case 0:
				cd.setEpsR(parser.makeDouble());
				break;
			case 1:
				cd.setInsLayer(parser.makeDouble());
				break;
			case 2:
				cd.setDiaIns(parser.makeDouble());
				break;
			case 3:
				cd.setDiaCable(parser.makeDouble());
				break;
			default:
				super.classEdit(activeObj, paramPointer - numCableClassProps);
				break;
			}
			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (cd.getEpsR() < 1.0)
					DSS.doSimpleMsg("Error: Insulation permittivity must be greater than one for CableData " + cd.getName(), 999);
				break;
			case 1:
				if (cd.getInsLayer() <= 0.0)
					DSS.doSimpleMsg("Error: Insulation layer thickness must be positive for CableData " + cd.getName(), 999);
				break;
			case 2:
				if (cd.getDiaIns() <= 0.0)
					DSS.doSimpleMsg("Error: Diameter over insulation layer must be positive for CableData " + cd.getName(), 999);
				break;
			case 3:
				if (cd.getDiaCable() <= 0.0)
					DSS.doSimpleMsg("Error: Diameter over cable must be positive for CableData " + cd.getName(), 999);
				break;
			}
		}

		return result;
	}

	protected void classMakeLike(DSSObject otherObj) {

		CableDataObj otherCableData = (CableDataObj) otherObj;
		CableDataObj cd = (CableDataObj) DSS.activeDSSObject;

		cd.setEpsR(otherCableData.getEpsR());
		cd.setInsLayer(otherCableData.getInsLayer());
		cd.setDiaIns(otherCableData.getDiaIns());
		cd.setDiaCable(otherCableData.getDiaCable());

		super.classMakeLike(otherObj);
	}

	public void setNumCableClassProps(int num) {
		numCableClassProps = num;
	}

	public int getNumCableClassProps() {
		return numCableClassProps;
	}

}

package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.CableData;
import com.epri.dss.general.CableDataObj;
import com.epri.dss.general.DSSObject;
import com.epri.dss.parser.impl.Parser;

public class CableDataImpl extends ConductorDataImpl implements CableData {

	private int NumCableClassProps;

	public CableDataImpl() {
		this.NumCableClassProps = 4;
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
	}

	protected void countProperties() {
		NumProperties = NumProperties + NumCableClassProps;
		super.countProperties();
	}

	protected void defineProperties() {
		PropertyName[ActiveProperty + 1] = "EpsR";
		PropertyName[ActiveProperty + 2] = "InsLayer";
		PropertyName[ActiveProperty + 3] = "DiaIns";
		PropertyName[ActiveProperty + 4] = "DiaCable";

		PropertyHelp[ActiveProperty + 1] = "Insulation layer relative permittivity; default is 2.3.";
		PropertyHelp[ActiveProperty + 2] = "Insulation layer thickness; same units as radius; no default. " +
				"With DiaIns, establishes inner radius for capacitance calculation.";
		PropertyHelp[ActiveProperty + 3] = "Diameter over insulation layer; same units as radius; no default." +
                "Establishes outer radius for capacitance calculation.";
		PropertyHelp[ActiveProperty + 4] = "Diameter over cable; same units as radius; no default.";

		ActiveProperty = ActiveProperty + NumCableClassProps;
		super.defineProperties();
	}

	protected int classEdit(DSSObject ActiveObj, int ParamPointer) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of parser
		if (ParamPointer >= 0) {
			CableDataObj cd = (CableDataObj) ActiveObj;
			switch (ParamPointer) {
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
				super.classEdit(ActiveObj, ParamPointer - NumCableClassProps);
				break;
			}
			/* Check for critical errors */
			switch (ParamPointer) {
			case 0:
				if (cd.getEpsR() < 1.0)
					Globals.doSimpleMsg("Error: Insulation permittivity must be greater than one for CableData " + cd.getName(), 999);
				break;
			case 1:
				if (cd.getInsLayer() <= 0.0)
					Globals.doSimpleMsg("Error: Insulation layer thickness must be positive for CableData " + cd.getName(), 999);
				break;
			case 2:
				if (cd.getDiaIns() <= 0.0)
					Globals.doSimpleMsg("Error: Diameter over insulation layer must be positive for CableData " + cd.getName(), 999);
				break;
			case 3:
				if (cd.getDiaCable() <= 0.0)
					Globals.doSimpleMsg("Error: Diameter over cable must be positive for CableData " + cd.getName(), 999);
				break;
			}
		}

		return Result;
	}

	protected void classMakeLike(DSSObject OtherObj) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		CableDataObj OtherCableData = (CableDataObj) OtherObj;
		CableDataObj cd = (CableDataObj) Globals.getActiveDSSObject();

		cd.setEpsR(OtherCableData.getEpsR());
		cd.setInsLayer(OtherCableData.getInsLayer());
		cd.setDiaIns(OtherCableData.getDiaIns());
		cd.setDiaCable(OtherCableData.getDiaCable());

		super.classMakeLike(OtherObj);
	}

	public void setNumCableClassProps(int numCableClassProps) {
		NumCableClassProps = numCableClassProps;
	}

	public int getNumCableClassProps() {
		return NumCableClassProps;
	}

}

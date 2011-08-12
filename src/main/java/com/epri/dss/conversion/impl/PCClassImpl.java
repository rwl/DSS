package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.PCClass;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.parser.impl.Parser;

public class PCClassImpl extends CktElementClassImpl implements PCClass {

	int NumPCClassProps;

	public PCClassImpl() {
		super();
		this.NumPCClassProps = 1;
		this.DSSClassType = DSSClassDefs.PC_ELEMENT;
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	protected void countProperties() {
		numProperties = numProperties + NumPCClassProps;
		super.countProperties();
	}

	/**
	 * Add properties of this class to propName.
	 *
	 * Define the properties for the base power delivery element class.
	 */
	protected void defineProperties() {
		propertyName[activeProperty + 1] = "spectrum";

		propertyHelp[activeProperty + 1] = "Name of harmonic spectrum for this device.";

		activeProperty = activeProperty + NumPCClassProps;

		super.defineProperties();
	}

	protected int classEdit(Object ActivePCObj, int ParamPointer) {
		int Result = 0;
		// continue parsing with contents of parser
		if (ParamPointer >= 0) {
			PCElement pElem = (PCElement) ActivePCObj;

			switch (ParamPointer) {
			case 1:
				pElem.setSpectrum(Parser.getInstance().makeString());
				break;
			default:
				super.classEdit(ActivePCObj, ParamPointer - NumPCClassProps);
				break;
			}
		}
		return Result;
	}

	protected void classMakeLike(Object OtherObj) {
		PCElement OtherPCObj = (PCElement) OtherObj;

		PCElement pElem = (PCElement) DSSGlobals.getInstance().getActiveDSSObject();

		pElem.setSpectrum(OtherPCObj.getSpectrum());
		pElem.setSpectrumObj(OtherPCObj.getSpectrumObj());

		super.classMakeLike(OtherObj);
	}

}

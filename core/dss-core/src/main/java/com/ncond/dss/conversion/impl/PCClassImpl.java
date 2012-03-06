package com.ncond.dss.conversion.impl;

import com.ncond.dss.common.impl.CktElementClassImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.conversion.PCClass;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.parser.impl.Parser;

abstract public class PCClassImpl extends CktElementClassImpl implements PCClass {

	int numPCClassProps;

	public PCClassImpl() {
		super();
		numPCClassProps = 1;
		classType = DSSClassDefs.PC_ELEMENT;
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	@Override
	protected void countProperties() {
		numProperties = numProperties + numPCClassProps;
		super.countProperties();
	}

	/**
	 * Add properties of this class to propName.
	 *
	 * Define the properties for the base power delivery element class.
	 */
	@Override
	protected void defineProperties() {
		propertyName[activeProperty + 1] = "spectrum";

		propertyHelp[activeProperty + 1] = "Name of harmonic spectrum for this device.";

		activeProperty = activeProperty + numPCClassProps;

		super.defineProperties();
	}

	@Override
	protected int classEdit(DSSObject activePCObj, int paramPointer) {
		int result = 0;
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			PCElement pElem = (PCElement) activePCObj;

			switch (paramPointer) {
			case 0:
				pElem.setSpectrum(Parser.getInstance().makeString());
				break;
			default:
				super.classEdit(activePCObj, paramPointer - numPCClassProps);
				break;
			}
		}
		return result;
	}

	@Override
	protected void classMakeLike(DSSObject otherObj) {
		PCElement otherPCObj = (PCElement) otherObj;

		PCElement pElem = (PCElement) DSS.activeDSSObject;

		pElem.setSpectrum(otherPCObj.getSpectrum());
		pElem.setSpectrumObj(otherPCObj.getSpectrumObj());

		super.classMakeLike(otherObj);
	}

}

package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.PCClass;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.parser.impl.Parser;

public class PCClassImpl extends CktElementClassImpl implements PCClass {

	int numPCClassProps;

	public PCClassImpl() {
		super();
		this.numPCClassProps = 1;
		this.classType = DSSClassDefs.PC_ELEMENT;
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	protected void countProperties() {
		numProperties = numProperties + numPCClassProps;
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

		activeProperty = activeProperty + numPCClassProps;

		super.defineProperties();
	}

	protected int classEdit(Object activePCObj, int paramPointer) {
		int result = 0;
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			PCElement pElem = (PCElement) activePCObj;

			switch (paramPointer) {
			case 1:
				pElem.setSpectrum(Parser.getInstance().makeString());
				break;
			default:
				super.classEdit(activePCObj, paramPointer - numPCClassProps);
				break;
			}
		}
		return result;
	}

	protected void classMakeLike(Object otherObj) {
		PCElement otherPCObj = (PCElement) otherObj;

		PCElement pElem = (PCElement) DSSGlobals.activeDSSObject;

		pElem.setSpectrum(otherPCObj.getSpectrum());
		pElem.setSpectrumObj(otherPCObj.getSpectrumObj());

		super.classMakeLike(otherObj);
	}

}

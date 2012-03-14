package com.ncond.dss.conversion;

import com.ncond.dss.common.CktElementClass;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.parser.Parser;

abstract public class PCClass extends CktElementClass {

	int numPCClassProps;

	public PCClass() {
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
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			PCElement elem = (PCElement) activePCObj;

			switch (paramPointer) {
			case 0:
				elem.setSpectrum(Parser.getInstance().makeString());
				break;
			default:
				super.classEdit(activePCObj, paramPointer - numPCClassProps);
				break;
			}
		}
		return 0;
	}

	@Override
	protected void classMakeLike(DSSObject otherObj) {
		PCElement other = (PCElement) otherObj;

		PCElement elem = (PCElement) DSS.activeDSSObject;

		elem.setSpectrum(other.getSpectrum());
		elem.setSpectrumObj(other.getSpectrumObj());

		super.classMakeLike(otherObj);
	}

}

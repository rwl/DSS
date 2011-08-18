package com.epri.dss.common.impl;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.CktElementClass;
import com.epri.dss.parser.impl.Parser;

public class CktElementClassImpl extends DSSClassImpl<CktElement> implements
		CktElementClass {

	private int numCktElemClassProps;

	public CktElementClassImpl() {
		super();
		numCktElemClassProps = 2;
	}

	protected int classEdit(CktElement activeCktElemObj, int paramPointer) {
		// continue parsing with contents of parser
		Parser parser = Parser.getInstance();

		if (paramPointer >= 0) {
			CktElement cktElem = activeCktElemObj;
			switch (paramPointer) {
			case 0:
				cktElem.setBaseFrequency(parser.makeDouble());
				break;
			case 2:
				cktElem.setEnabled( Utilities.interpretYesNo(parser.makeString()) );
				break;
			default:
				super.classEdit(activeCktElemObj, paramPointer - numCktElemClassProps);
				break;
			}
		}
		return 0;
	}

	protected void classMakeLike(CktElement otherObj) {
		CktElement otherCktObj = otherObj;

		CktElement cktElem = (CktElement) DSSGlobals.activeDSSObject;
		cktElem.setBaseFrequency(otherCktObj.getBaseFrequency());
		cktElem.setEnabled(true);
	}

	/**
	 * Add no. of intrinsic properties.
	 */
	protected void countProperties() {
		numProperties = numProperties + numCktElemClassProps;
		super.countProperties();
	}

	/**
	 * Define the properties for the base power delivery element class.
	 */
	protected void defineProperties() {
		propertyName[activeProperty + 1] = "basefreq";  // TODO Check zero based indexing
		propertyName[activeProperty + 2] = "enabled";

		propertyHelp[activeProperty + 1] = "Base Frequency for ratings.";
		propertyHelp[activeProperty + 2] = "{Yes|No or True|False} Indicates whether this element is enabled.";

		activeProperty = activeProperty + numCktElemClassProps;

		super.defineProperties();
	}

	public int getNumCktElemClassProps() {
		return numCktElemClassProps;
	}

}

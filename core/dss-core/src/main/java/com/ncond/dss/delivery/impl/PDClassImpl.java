package com.ncond.dss.delivery.impl;

import com.ncond.dss.common.impl.CktElementClassImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.delivery.PDClass;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.parser.impl.Parser;

abstract public class PDClassImpl extends CktElementClassImpl implements PDClass {

	private int numPDClassProps;

	public PDClassImpl() {
		super();
		numPDClassProps = 5;
		classType = DSSClassDefs.PD_ELEMENT;
	}

	@Override
	protected void countProperties() {
		numProperties = numProperties + numPDClassProps;
		super.countProperties();
	}

	/**
	 * Define the properties for the base power delivery element class.
	 */
	@Override
	protected void defineProperties() {
		propertyName[activeProperty + 1] = "normamps";
		propertyName[activeProperty + 2] = "emergamps";
		propertyName[activeProperty + 3] = "faultrate";
		propertyName[activeProperty + 4] = "pctperm";
		propertyName[activeProperty + 5] = "repair";

		propertyHelp[activeProperty + 1] = "Normal rated current.";
		propertyHelp[activeProperty + 2] = "Maximum current.";
		propertyHelp[activeProperty + 3] = "No. of failures per year.";
		propertyHelp[activeProperty + 4] = "Percent of failures that become permanent.";
		propertyHelp[activeProperty + 5] = "Hours to repair.";

		activeProperty = activeProperty + numPDClassProps;

		super.defineProperties();
	}

	@Override
	protected int classEdit(DSSObject activePDObj, int paramPointer) {
		// continue parsing with contents of parser
		Parser parser = Parser.getInstance();

		if (paramPointer >= 0) {
			PDElement PDElem = (PDElement) activePDObj;

			switch (paramPointer) {
			case 0:
				PDElem.setNormAmps(parser.makeDouble());
				break;
			case 1:
				PDElem.setEmergAmps(parser.makeDouble());
				break;
			case 2:
				PDElem.setFaultRate(parser.makeDouble());
				break;
			case 3:
				PDElem.setPctPerm(parser.makeDouble());
				break;
			case 4:
				PDElem.setHrsToRepair(parser.makeDouble());
				break;
			default:
				super.classEdit(activePDObj, paramPointer - numPDClassProps);
				break;
			}
		}
		return 0;
	}

	@Override
	protected void classMakeLike(DSSObject otherObj) {
		PDElement otherPDObj = (PDElement) otherObj;
		PDElement PDElem = (PDElement) DSS.activeDSSObject;

		PDElem.setNormAmps(otherPDObj.getNormAmps());
		PDElem.setEmergAmps(otherPDObj.getEmergAmps());
		PDElem.setFaultRate(otherPDObj.getFaultRate());
		PDElem.setPctPerm(otherPDObj.getPctPerm());
		PDElem.setHrsToRepair(otherPDObj.getHrsToRepair());

		super.classMakeLike(otherObj);
	}

	@Override
	public int getNumPDClassProps() {
		return numPDClassProps;
	}

	@Override
	public void setNumPDClassProps(int num) {
		numPDClassProps = num;
	}

}

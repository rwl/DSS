package com.epri.dss.delivery;

import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.impl.ConductorChoice;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.shared.CMatrix;

public interface LineObj extends PDElement {

	void fetchTSCableList(String Code);

	void fetchCNCableList(String Code);

	CMatrix getZ();

	void setZ(CMatrix z);

	CMatrix getYc();

	void setYc(CMatrix yc);

	double getR1();

	void setR1(double r1);

	double getX1();

	void setX1(double x1);

	double getR0();

	void setR0(double r0);

	double getX0();

	void setX0(double x0);

	double getC1();

	void setC1(double c1);

	double getC0();

	void setC0(double c0);

	double getLen();

	void setLen(double len);

	int getLengthUnits();

	void setLengthUnits(int lengthUnits);

	double getRg();

	void setRg(double rg);

	double getXg();

	void setXg(double xg);

	double getKXg();

	void setKXg(double kXg);

	double getRho();

	void setRho(double rho);

	double getGeneralPlotQuantity();

	void setGeneralPlotQuantity(double generalPlotQuantity);

	String getCondCode();

	void setCondCode(String condCode);

	String getGeometryCode();

	void setGeometryCode(String geometryCode);

	String getSpacingCode();

	void setSpacingCode(String spacingCode);

	boolean isGeometrySpecified();

	void setGeometrySpecified(boolean geometrySpecified);

	boolean isSpacingSpecified();

	void setSpacingSpecified(boolean spacingSpecified);

	boolean isSymComponentsChanged();

	void setSymComponentsChanged(boolean symComponentsChanged);

	boolean isSymComponentsModel();

	void setSymComponentsModel(boolean symComponentsModel);

	boolean isIsSwitch();

	void setIsSwitch(boolean isSwitch);

	void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroSeqLosses);

	boolean mergeWith(LineObj OtherLine, boolean Series);

	void updateControlElements(String NewName, String OldName);

	void fetchLineCode(String Code);

	void fetchGeometryCode(String Code);

	void fetchLineSpacing(String Code);

	void fetchWireList(String Code);

	boolean isLineCodeSpecified();

	ConductorChoice getPhaseChoice();

	int numConductorsAvailable();

	ConductorDataObj getConductorData(int i);

	// FIXME Private method in OpenDSS
	void killGeometrySpecified();

	// FIXME Private method in OpenDSS
	void killSpacingSpecified();

	// FIXME Private method in OpenDSS
	void resetLengthUnits();


	// FIXME Private members in OpenDSS

	double getZFrequency();

	void setZFrequency(double zFrequency);

	int getLineCodeUnits();

	void setLineCodeUnits(int lineCodeUnits);

	double getUnitsConvert();

	void setUnitsConvert(double unitsConvert);

	LineGeometryObj getLineGeometryObj();

	void setLineGeometryObj(LineGeometryObj lineGeometryObj);

	LineSpacingObj getLineSpacingObj();

	void setLineSpacingObj(LineSpacingObj lineSpacingObj);

	ConductorDataObj[] getWireData();

	void setWireData(ConductorDataObj[] wireData);

	boolean getRhoSpecified();

	void setRhoSpecified(boolean rhoSpecified);

	int getEarthModel();

	void setEarthModel(int earthModel);

	void setLineCodeSpecified(boolean lineCodeSpecified);

	CMatrix getZinv();

	void setZinv(CMatrix zinv);

	boolean isCapSpecified();

	void setCapSpecified(boolean capSpecified);

}

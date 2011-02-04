package com.epri.dss.delivery;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.shared.CMatrix;

public interface LineObj extends PDElement {

	public CMatrix getZ();

	public void setZ(CMatrix z);

	public CMatrix getYc();

	public void setYc(CMatrix yc);

	public double getR1();

	public void setR1(double r1);

	public double getX1();

	public void setX1(double x1);

	public double getR0();

	public void setR0(double r0);

	public double getX0();

	public void setX0(double x0);

	public double getC1();

	public void setC1(double c1);

	public double getC0();

	public void setC0(double c0);

	public double getLen();

	public void setLen(double len);

	public int getLengthUnits();

	public void setLengthUnits(int lengthUnits);

	public double getRg();

	public void setRg(double rg);

	public double getXg();

	public void setXg(double xg);

	public double getKXg();

	public void setKXg(double kXg);

	public double getRho();

	public void setRho(double rho);

	public double getGeneralPlotQuantity();

	public void setGeneralPlotQuantity(double generalPlotQuantity);

	public String getCondCode();

	public void setCondCode(String condCode);

	public String getGeometryCode();

	public void setGeometryCode(String geometryCode);

	public String getSpacingCode();

	public void setSpacingCode(String spacingCode);

	public boolean isGeometrySpecified();

	public void setGeometrySpecified(boolean geometrySpecified);

	public boolean isSpacingSpecified();

	public void setSpacingSpecified(boolean spacingSpecified);

	public boolean isSymComponentsChanged();

	public void setSymComponentsChanged(boolean symComponentsChanged);

	public boolean isSymComponentsModel();

	public void setSymComponentsModel(boolean symComponentsModel);

	public boolean isIsSwitch();

	public void setIsSwitch(boolean isSwitch);
	
	public void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroSeqLosses);
	
	public boolean mergeWith(LineObj OtherLine, boolean Series);
	
	public void updateControlElements(String NewName, String OldName);
	
	public void fetchLineCode(String Code);
	
	public void fetchGeometryCode(String Code);
	
	public void fetchLineSpacing(String Code);
	
	public void fetchWireList(String Code);

	public boolean isLineCodeSpecified();

}

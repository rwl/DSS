package com.epri.dss.plot;

import java.awt.Color;
import java.util.ArrayList;

import com.epri.dss.common.CktElement;
import com.epri.dss.plot.impl.DSSPlotImpl.PlotQuantity;
import com.epri.dss.plot.impl.DSSPlotImpl.PlotType;

public interface DSSPlot {
	
	public static DSSPlot DSSPlotObj = null;

	void setMaxLineThickness(int Value);
	
	int getMaxLineThickness();

	PlotType getPlotType();

	void setPlotType(PlotType plotType);

	double getMaxScale();

	void setMaxScale(double maxScale);

	double getMinScale();

	void setMinScale(double minScale);

	boolean isDots();

	void setDots(boolean dots);

	boolean isLabels();

	void setLabels(boolean labels);

	boolean isShowLoops();

	void setShowLoops(boolean showLoops);

	boolean isShowSubs();

	void setShowSubs(boolean showSubs);

	PlotQuantity getQuantity();

	void setQuantity(PlotQuantity quantity);

	String getObjectName();

	void setObjectName(String objectName);

	String getFeederName();

	void setFeederName(String feederName);

	int getValueIndex();

	void setValueIndex(int valueIndex);

	int getMarkerIdx();

	void setMarkerIdx(int markerIdx);

	int getPhasesToPlot();

	void setPhasesToPlot(int phasesToPlot);

	int[] getChannels();

	void setChannels(int[] channels);

	double[] getBases();

	void setBases(double[] bases);

	Color getColor1();

	void setColor1(Color color1);

	Color getColor2();

	void setColor2(Color color2);

	Color getColor3();

	void setColor3(Color color3);

	double getTriColorMax();

	void setTriColorMax(double triColorMax);

	double getTriColorMid();

	void setTriColorMid(double triColorMid);

	boolean isMaxScaleIsSpecified();

	void setMaxScaleIsSpecified(boolean maxScaleIsSpecified);

	boolean isMinScaleIsSpecified();

	void setMinScaleIsSpecified(boolean minScaleIsSpecified);

	String[] getDaisyBusList();

	void setDaisyBusList(String[] daisyBusList);

	void execute();
	
	void setDefaults();

	void doLoadShapePlot(String LoadShapeName);
	
	void doDI_Plot(String CaseName, int CaseYear, int[] iRegisters, boolean PeakDay, String MeterName);
	
	void doCompareCases(String CaseName1, String CaseName2, String WhichFile, int Reg);
	
	void doYearlyCurvePlot(ArrayList<String> caseNames, String WhichFile, int[] iRegisters);
	
	void doVisualizationPlot(CktElement Element, int Quantity);

}

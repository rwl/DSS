package com.ncond.dss.plot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.plot.impl.DSSPlotImpl.PlotQuantity;
import com.ncond.dss.plot.impl.DSSPlotImpl.PlotType;

public interface DSSPlot {

	static final int vizCURRENT = 1;
	static final int vizVOLTAGE = 2;
	static final int vizPOWER   = 3;

	void setMaxLineThickness(int value);

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

	List<String> getDaisyBusList();

	void setDaisyBusList(List<String> daisyBusList);

	void execute();

	void setDefaults();

	void doLoadShapePlot(String loadShapeName);

	void doTempShapePlot(String tempShapeName);

	void doPriceShapePlot(String priceShapeName);

	void doDI_Plot(String caseName, int caseYear, int[] iRegisters, boolean peakDay, String meterName);

	void doCompareCases(String caseName1, String caseName2, String whichFile, int reg);

	void doYearlyCurvePlot(ArrayList<String> caseNames, String whichFile, int[] iRegisters);

	void doVisualizationPlot(CktElement element, int quantity);

}

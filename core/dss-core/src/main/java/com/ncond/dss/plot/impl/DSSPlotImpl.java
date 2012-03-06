package com.ncond.dss.plot.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.TransformerObj;
import com.ncond.dss.plot.DSSPlot;

public class DSSPlotImpl implements DSSPlot {

	private static DSSPlot DSSPlotObj;

	private static int addMarkerColor;
	private static int addMarkerCode, addMarkerSize;
	private static int singlePhLineStyle;
	private static int threePhLineStyle;

	public enum PlotType {
		AUTO_ADD_LOG_PLOT,
		CIRCUIT_PLOT,
		GENERAL_DATA_PLOT,
		GENERAL_CIRCUIT_PLOT,
		MONITOR_PLOT,
		DAISY_PLOT,
		METER_ZONES,
		LOAD_SHAPE,
		TSHAPE,
		PRICE_SHAPE,
		PROFILE
	}

	public enum PlotQuantity {
		VOLTAGE, CURRENT, POWER, LOSSES, CAPACITY, NONE
	}

	private int activeColorIdx;
	private int[] colorArray = new int[17];
	private LineObj line;
	private TransformerObj transf;
	private int bus1Idx;
	private int bus2Idx;
	private String generalCircuitPlotQuantity;
	private int maxLineThickness;

	protected PlotType plotType;
	protected double maxScale;
	protected double minScale;
	protected boolean dots,
		labels,
		showLoops,  // applies to meterZone plots only
		showSubs;
	protected PlotQuantity quantity;
	protected String objectName,
		feederName;
	protected int valueIndex,
		markerIdx;  // for general & autoAdd

	protected int phasesToPlot;  // profile plot

	protected int[] channels;  // for monitor plot
	protected double[] bases;  // for monitor plot

	protected Color color1, color2, color3;

	/* Tri-color plots */
	protected double triColorMax, triColorMid;

	protected boolean maxScaleIsSpecified;
	protected boolean minScaleIsSpecified;

	protected List<String> daisyBusList;

	public DSSPlotImpl() {
		// TODO Auto-generated constructor stub
	}

	private void doGeneralPlot() {

	}

	private void doAutoAddPlot() {

	}

	private void doTheDaisies() {

	}

	private void doCircuitPlot() {

	}

	private void doGeneralCircuitPlot() {

	}

	private void doMeterZonePlot() {

	}

	private void doMonitorPlot() {

	}

	private void doProfilePlot() {

	}

	/** Misc support procedures */
	private void markSubTransformers() {

	}

	private void markTheTransformers() {

	}

	private void doBusLabels(int idx1, int idx2) {

	}

	private void doBusLabel(int idx, String busLabel) {

	}

	private void labelBuses() {

	}

	private void loadGeneralLineData() {

	}

	private void setColorArray() {

	}

	private void setMaxScale() {

	}

	private int getColor() {
		return 0;
	}

	private int thickness() {
		return 0;
	}

	private double maxCurrent() {
		return 0.0;
	}

	private Color nextColor() {
		return null;
	}

	private String quantityString() {
		return null;
	}

//	private PenStyle style(int code) {
//		return null;
//	}

	private Color getAutoColor(double scale) {
		return null;
	}

	private Byte getMarker(int idx) {
		return null;
	}

	private boolean coordinateSame(int i1, int i2) {
		return false;
	}

	private Color interpolateGradientColor(Color color1, Color color2, double ratio) {
		return null;
	}

	@Override
	public void setMaxLineThickness(int value) {

	}

	@Override
	public int getMaxLineThickness() {
		return maxLineThickness;
	}

	@Override
	public PlotType getPlotType() {
		return plotType;
	}

	@Override
	public void setPlotType(PlotType type) {
		plotType = type;
	}

	@Override
	public double getMaxScale() {
		return maxScale;
	}

	@Override
	public void setMaxScale(double max) {
		maxScale = max;
	}

	@Override
	public double getMinScale() {
		return minScale;
	}

	@Override
	public void setMinScale(double min) {
		minScale = min;
	}

	@Override
	public boolean isDots() {
		return dots;
	}

	@Override
	public void setDots(boolean value) {
		dots = value;
	}

	@Override
	public boolean isLabels() {
		return labels;
	}

	@Override
	public void setLabels(boolean lbl) {
		labels = lbl;
	}

	@Override
	public boolean isShowLoops() {
		return showLoops;
	}

	@Override
	public void setShowLoops(boolean show) {
		showLoops = show;
	}

	@Override
	public boolean isShowSubs() {
		return showSubs;
	}

	@Override
	public void setShowSubs(boolean show) {
		showSubs = show;
	}

	@Override
	public PlotQuantity getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(PlotQuantity qty) {
		quantity = qty;
	}

	@Override
	public String getObjectName() {
		return objectName;
	}

	@Override
	public void setObjectName(String name) {
		objectName = name;
	}

	@Override
	public String getFeederName() {
		return feederName;
	}

	@Override
	public void setFeederName(String name) {
		feederName = name;
	}

	@Override
	public int getValueIndex() {
		return valueIndex;
	}

	@Override
	public void setValueIndex(int index) {
		valueIndex = index;
	}

	@Override
	public int getMarkerIdx() {
		return markerIdx;
	}

	@Override
	public void setMarkerIdx(int idx) {
		markerIdx = idx;
	}

	@Override
	public int getPhasesToPlot() {
		return phasesToPlot;
	}

	@Override
	public void setPhasesToPlot(int phases) {
		phasesToPlot = phases;
	}

	@Override
	public int[] getChannels() {
		return channels;
	}

	@Override
	public void setChannels(int[] values) {
		channels = values;
	}

	@Override
	public double[] getBases() {
		return bases;
	}

	@Override
	public void setBases(double[] values) {
		bases = values;
	}

	@Override
	public Color getColor1() {
		return color1;
	}

	@Override
	public void setColor1(Color value) {
		color1 = value;
	}

	@Override
	public Color getColor2() {
		return color2;
	}

	@Override
	public void setColor2(Color value) {
		color2 = value;
	}

	@Override
	public Color getColor3() {
		return color3;
	}

	@Override
	public void setColor3(Color value) {
		color3 = value;
	}

	@Override
	public double getTriColorMax() {
		return triColorMax;
	}

	@Override
	public void setTriColorMax(double max) {
		triColorMax = max;
	}

	@Override
	public double getTriColorMid() {
		return triColorMid;
	}

	@Override
	public void setTriColorMid(double mid) {
		triColorMid = mid;
	}

	@Override
	public boolean isMaxScaleIsSpecified() {
		return maxScaleIsSpecified;
	}

	@Override
	public void setMaxScaleIsSpecified(boolean isSpecified) {
		maxScaleIsSpecified = isSpecified;
	}

	@Override
	public boolean isMinScaleIsSpecified() {
		return minScaleIsSpecified;
	}

	@Override
	public void setMinScaleIsSpecified(boolean isSpecified) {
		minScaleIsSpecified = isSpecified;
	}

	@Override
	public List<String> getDaisyBusList() {
		return daisyBusList;
	}

	@Override
	public void setDaisyBusList(List<String> list) {
		daisyBusList = list;
	}

	@Override
	public void execute() {

	}

	@Override
	public void setDefaults() {

	}

	@Override
	public void doLoadShapePlot(String loadShapeName) {

	}

	@Override
	public void doTempShapePlot(String tempShapeName) {

	}

	@Override
	public void doPriceShapePlot(String priceShapeName) {

	}

	@Override
	public void doDI_Plot(String caseName, int caseYear, int[] iRegisters, boolean peakDay, String meterName) {

	}

	@Override
	public void doCompareCases(String caseName1, String caseName2, String whichFile, int reg) {

	}

	@Override
	public void doYearlyCurvePlot(ArrayList<String> caseNames, String whichFile, int[] iRegisters) {

	}

	@Override
	public void doVisualizationPlot(CktElement element, int quantity) {

	}

	public static void setDSSPlotObj(DSSPlot plotObj) {
		DSSPlotObj = plotObj;
	}

	public static DSSPlot getDSSPlotObj() {
		return DSSPlotObj;
	}

	public static int getAddMarkerColor() {
		return addMarkerColor;
	}

	public static void setAddMarkerColor(int color) {
		addMarkerColor = color;
	}

	public static int getAddMarkerCode() {
		return addMarkerCode;
	}

	public static void setAddMarkerCode(int code) {
		addMarkerCode = code;
	}

	public static int getAddMarkerSize() {
		return addMarkerSize;
	}

	public static void setAddMarkerSize(int size) {
		addMarkerSize = size;
	}

	public static int getSinglePhLineStyle() {
		return singlePhLineStyle;
	}

	public static void setSinglePhLineStyle(int style) {
		singlePhLineStyle = style;
	}

	public static int getThreePhLineStyle() {
		return threePhLineStyle;
	}

	public static void setThreePhLineStyle(int style) {
		threePhLineStyle = style;
	}

}

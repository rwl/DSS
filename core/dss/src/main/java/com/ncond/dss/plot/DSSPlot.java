package com.ncond.dss.plot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.TransformerObj;

@SuppressWarnings("unused")
public class DSSPlot {

	public static final int vizCURRENT = 1;
	public static final int vizVOLTAGE = 2;
	public static final int vizPOWER   = 3;

	private static DSSPlot DSSPlotObj;

	private static int addMarkerColor;
	private static int addMarkerCode, addMarkerSize;
	private static int singlePhLineStyle;
	private static int threePhLineStyle;

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

	public DSSPlot() {
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

	public void setMaxLineThickness(int value) {

	}

	public int getMaxLineThickness() {
		return maxLineThickness;
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public void setPlotType(PlotType type) {
		plotType = type;
	}

	public double getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(double max) {
		maxScale = max;
	}

	public double getMinScale() {
		return minScale;
	}

	public void setMinScale(double min) {
		minScale = min;
	}

	public boolean isDots() {
		return dots;
	}

	public void setDots(boolean value) {
		dots = value;
	}

	public boolean isLabels() {
		return labels;
	}

	public void setLabels(boolean lbl) {
		labels = lbl;
	}

	public boolean isShowLoops() {
		return showLoops;
	}

	public void setShowLoops(boolean show) {
		showLoops = show;
	}

	public boolean isShowSubs() {
		return showSubs;
	}

	public void setShowSubs(boolean show) {
		showSubs = show;
	}

	public PlotQuantity getQuantity() {
		return quantity;
	}

	public void setQuantity(PlotQuantity qty) {
		quantity = qty;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String name) {
		objectName = name;
	}

	public String getFeederName() {
		return feederName;
	}

	public void setFeederName(String name) {
		feederName = name;
	}

	public int getValueIndex() {
		return valueIndex;
	}

	public void setValueIndex(int index) {
		valueIndex = index;
	}

	public int getMarkerIdx() {
		return markerIdx;
	}

	public void setMarkerIdx(int idx) {
		markerIdx = idx;
	}

	public int getPhasesToPlot() {
		return phasesToPlot;
	}

	public void setPhasesToPlot(int phases) {
		phasesToPlot = phases;
	}

	public int[] getChannels() {
		return channels;
	}

	public void setChannels(int[] values) {
		channels = values;
	}

	public double[] getBases() {
		return bases;
	}

	public void setBases(double[] values) {
		bases = values;
	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color value) {
		color1 = value;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color value) {
		color2 = value;
	}

	public Color getColor3() {
		return color3;
	}

	public void setColor3(Color value) {
		color3 = value;
	}

	public double getTriColorMax() {
		return triColorMax;
	}

	public void setTriColorMax(double max) {
		triColorMax = max;
	}

	public double getTriColorMid() {
		return triColorMid;
	}

	public void setTriColorMid(double mid) {
		triColorMid = mid;
	}

	public boolean isMaxScaleIsSpecified() {
		return maxScaleIsSpecified;
	}

	public void setMaxScaleIsSpecified(boolean isSpecified) {
		maxScaleIsSpecified = isSpecified;
	}

	public boolean isMinScaleIsSpecified() {
		return minScaleIsSpecified;
	}

	public void setMinScaleIsSpecified(boolean isSpecified) {
		minScaleIsSpecified = isSpecified;
	}

	public List<String> getDaisyBusList() {
		return daisyBusList;
	}

	public void setDaisyBusList(List<String> list) {
		daisyBusList = list;
	}

	public void execute() {

	}

	public void setDefaults() {

	}

	public void doLoadShapePlot(String loadShapeName) {

	}

	public void doTempShapePlot(String tempShapeName) {

	}

	public void doPriceShapePlot(String priceShapeName) {

	}

	public void doDI_Plot(String caseName, int caseYear, int[] iRegisters, boolean peakDay, String meterName) {

	}

	public void doCompareCases(String caseName1, String caseName2, String whichFile, int reg) {

	}

	public void doYearlyCurvePlot(ArrayList<String> caseNames, String whichFile, int[] iRegisters) {

	}

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

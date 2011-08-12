package com.epri.dss.plot.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.plot.DSSPlot;

public class DSSPlotImpl implements DSSPlot {

	private static DSSPlot DSSPlotObj;

	private static int AddMarkerColor;
	private static int AddMarkerCode, AddMarkerSize;
	private static int SinglePhLineStyle;
	private static int ThreePhLineStyle;

	public enum PlotType {
		AutoAddLogPlot,
		CircuitPlot,
		GeneralDataPlot,
		GeneralCircuitPlot,
		MonitorPlot,
		DaisyPlot,
		MeterZones,
		LoadShape,
		TShape,
		PriceShape,
		Profile
	}

	public enum PlotQuantity {
		Voltage, Current, Power, Losses, Capacity, None
	}

	private int ActiveColorIdx;
	private int[] ColorArray = new int[17];
	private LineObj Line;
	private TransformerObj Transf;
	private int Bus1Idx;
	private int Bus2Idx;
	private String GeneralCircuitPlotQuantity;
	private int MaxLineThickness;

	protected PlotType PlotType;
	protected double MaxScale;
	protected double MinScale;
	protected boolean Dots,
		Labels,
		ShowLoops,  // applies to meterZone plots only
		ShowSubs;
	protected PlotQuantity Quantity;
	protected String ObjectName,
		FeederName;
	protected int ValueIndex,
		MarkerIdx;  // for general & autoAdd

	protected int PhasesToPlot;  // profile plot

	protected int[] Channels;  // for monitor plot
	protected double[] Bases;  // for monitor plot

	protected Color Color1, Color2, Color3;

	/* Tri-color plots */
	protected double TriColorMax, TriColorMid;

	protected boolean MaxScaleIsSpecified;
	protected boolean MinScaleIsSpecified;

	protected List<String> DaisyBusList;

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

	private void doBusLabels(int Idx1, int Idx2) {

	}

	private void doBusLabel(int Idx, String BusLabel) {

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

//	private PenStyle style(int Code) {
//		return null;
//	}

	private Color getAutoColor(double Scale) {
		return null;
	}

	private Byte getMarker(int idx) {
		return null;
	}

	private boolean coordinateSame(int i1, int i2) {
		return false;
	}

	private Color interpolateGradientColor(Color Color1, Color Color2, double Ratio) {
		return null;
	}

	public void setMaxLineThickness(int Value) {

	}

	public int getMaxLineThickness() {
		return MaxLineThickness;
	}

	public PlotType getPlotType() {
		return PlotType;
	}

	public void setPlotType(PlotType plotType) {
		PlotType = plotType;
	}

	public double getMaxScale() {
		return MaxScale;
	}

	public void setMaxScale(double maxScale) {
		MaxScale = maxScale;
	}

	public double getMinScale() {
		return MinScale;
	}

	public void setMinScale(double minScale) {
		MinScale = minScale;
	}

	public boolean isDots() {
		return Dots;
	}

	public void setDots(boolean dots) {
		Dots = dots;
	}

	public boolean isLabels() {
		return Labels;
	}

	public void setLabels(boolean labels) {
		Labels = labels;
	}

	public boolean isShowLoops() {
		return ShowLoops;
	}

	public void setShowLoops(boolean showLoops) {
		ShowLoops = showLoops;
	}

	public boolean isShowSubs() {
		return ShowSubs;
	}

	public void setShowSubs(boolean showSubs) {
		ShowSubs = showSubs;
	}

	public PlotQuantity getQuantity() {
		return Quantity;
	}

	public void setQuantity(PlotQuantity quantity) {
		Quantity = quantity;
	}

	public String getObjectName() {
		return ObjectName;
	}

	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}

	public String getFeederName() {
		return FeederName;
	}

	public void setFeederName(String feederName) {
		FeederName = feederName;
	}

	public int getValueIndex() {
		return ValueIndex;
	}

	public void setValueIndex(int valueIndex) {
		ValueIndex = valueIndex;
	}

	public int getMarkerIdx() {
		return MarkerIdx;
	}

	public void setMarkerIdx(int markerIdx) {
		MarkerIdx = markerIdx;
	}

	public int getPhasesToPlot() {
		return PhasesToPlot;
	}

	public void setPhasesToPlot(int phasesToPlot) {
		PhasesToPlot = phasesToPlot;
	}

	public int[] getChannels() {
		return Channels;
	}

	public void setChannels(int[] channels) {
		Channels = channels;
	}

	public double[] getBases() {
		return Bases;
	}

	public void setBases(double[] bases) {
		Bases = bases;
	}

	public Color getColor1() {
		return Color1;
	}

	public void setColor1(Color color1) {
		Color1 = color1;
	}

	public Color getColor2() {
		return Color2;
	}

	public void setColor2(Color color2) {
		Color2 = color2;
	}

	public Color getColor3() {
		return Color3;
	}

	public void setColor3(Color color3) {
		Color3 = color3;
	}

	public double getTriColorMax() {
		return TriColorMax;
	}

	public void setTriColorMax(double triColorMax) {
		TriColorMax = triColorMax;
	}

	public double getTriColorMid() {
		return TriColorMid;
	}

	public void setTriColorMid(double triColorMid) {
		TriColorMid = triColorMid;
	}

	public boolean isMaxScaleIsSpecified() {
		return MaxScaleIsSpecified;
	}

	public void setMaxScaleIsSpecified(boolean maxScaleIsSpecified) {
		MaxScaleIsSpecified = maxScaleIsSpecified;
	}

	public boolean isMinScaleIsSpecified() {
		return MinScaleIsSpecified;
	}

	public void setMinScaleIsSpecified(boolean minScaleIsSpecified) {
		MinScaleIsSpecified = minScaleIsSpecified;
	}

	public List<String> getDaisyBusList() {
		return DaisyBusList;
	}

	public void setDaisyBusList(List<String> daisyBusList) {
		DaisyBusList = daisyBusList;
	}

	public void execute() {

	}

	public void setDefaults() {

	}

	public void doLoadShapePlot(String LoadShapeName) {

	}

	public void doTempShapePlot(String TempShapeName) {

	}

	public void doPriceShapePlot(String PriceShapeName) {

	}

	public void doDI_Plot(String CaseName, int CaseYear, int[] iRegisters, boolean PeakDay, String MeterName) {

	}

	public void doCompareCases(String CaseName1, String CaseName2, String WhichFile, int Reg) {

	}

	public void doYearlyCurvePlot(ArrayList<String> CaseNames, String WhichFile, int[] iRegisters) {

	}

	public void doVisualizationPlot(CktElement Element, int Quantity) {

	}

	public static void setDSSPlotObj(DSSPlot dSSPlotObj) {
		DSSPlotObj = dSSPlotObj;
	}

	public static DSSPlot getDSSPlotObj() {
		return DSSPlotObj;
	}

	public static int getAddMarkerColor() {
		return AddMarkerColor;
	}

	public static void setAddMarkerColor(int addMarkerColor) {
		AddMarkerColor = addMarkerColor;
	}

	public static int getAddMarkerCode() {
		return AddMarkerCode;
	}

	public static void setAddMarkerCode(int addMarkerCode) {
		AddMarkerCode = addMarkerCode;
	}

	public static int getAddMarkerSize() {
		return AddMarkerSize;
	}

	public static void setAddMarkerSize(int addMarkerSize) {
		AddMarkerSize = addMarkerSize;
	}

	public static int getSinglePhLineStyle() {
		return SinglePhLineStyle;
	}

	public static void setSinglePhLineStyle(int singlePhLineStyle) {
		SinglePhLineStyle = singlePhLineStyle;
	}

	public static int getThreePhLineStyle() {
		return ThreePhLineStyle;
	}

	public static void setThreePhLineStyle(int threePhLineStyle) {
		ThreePhLineStyle = threePhLineStyle;
	}

}

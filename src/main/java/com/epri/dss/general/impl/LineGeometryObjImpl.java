package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.CMatrix;

public class LineGeometryObjImpl extends DSSObjectImpl implements LineGeometryObj {

	private int NConds;
	private int NPhases;
	private String[] condType;
	private WireDataObj[] WireData;
	private double[] X;
	private double[] Y;
	private int[] Units;
	private int LastUnit;
	private boolean DataChanged;
	private boolean Reduce;
	private int ActiveCond;
	private String SpacingType;

	private OHLineConstants LineData;
	
	protected double NormAmps;
	protected double EmergAmps;

	public LineGeometryObjImpl(DSSClass ParClass, String LineGeometryName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public void setNconds(int Value) {

	}

	public void setNphases(int Value) {

	}
	
	public int getNphases() {
		return NConds;
	}

	public void setActiveCond(int Value) {

	}
	
	public int getActiveCond() {
		return ActiveCond;
	}

	public CMatrix getYCmatrix(double f, double Length, int Units) {
		return null;
	}

	public CMatrix getZmatrix(double f, double Length, int Units) {
		return null;
	}

	public double getRhoEarth() {
		return 0.0;
	}

	public void setRhoEarth(double Value) {

	}

	public int getNconds() {
		return 0;
	}
	
	/* call this before using the line data */
	private void updateLineGeometryData(double f) {
		
	}
	
	public double getXcoord(int i) {
		return 0.0;
	}
	
	public double getYcoord(int i) {
		return 0.0;
	}
	
	public String getWireName(int i) {
		return null;
	}
	
	public WireDataObj getWireData (int i) {
		return null;
	}
	
	public int getNWires() {
		return NConds;
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public void saveWrite(PrintStream F) {
		
	}
	
	/**
	 * called from a Line object that has its own Spacing and Wires input
	 * automatically sets reduce=y if the spacing has more wires than phases
	 */
	public void LoadSpacingAndWires(LineSpacingObj Spc, WireDataObj[] Wires) {
		
	}

	public double getNormAmps() {
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	public String[] getCondType() {
		return condType;
	}

	public void setCondType(String[] condType) {
		this.condType = condType;
	}

	public WireDataObj[] getWireData() {
		return WireData;
	}

	public void setWireData(WireDataObj[] wireData) {
		WireData = wireData;
	}

	public double[] getX() {
		return X;
	}

	public void setX(double[] x) {
		X = x;
	}

	public double[] getY() {
		return Y;
	}

	public void setY(double[] y) {
		Y = y;
	}

	public int[] getUnits() {
		return Units;
	}

	public void setUnits(int[] units) {
		Units = units;
	}

	public int getLastUnit() {
		return LastUnit;
	}

	public void setLastUnit(int lastUnit) {
		LastUnit = lastUnit;
	}

	public boolean isDataChanged() {
		return DataChanged;
	}

	public void setDataChanged(boolean dataChanged) {
		DataChanged = dataChanged;
	}

	public boolean isReduce() {
		return Reduce;
	}

	public void setReduce(boolean reduce) {
		Reduce = reduce;
	}

	public String getSpacingType() {
		return SpacingType;
	}

	public void setSpacingType(String spacingType) {
		SpacingType = spacingType;
	}

	public OHLineConstants getLineData() {
		return LineData;
	}

	public void setLineData(OHLineConstants lineData) {
		LineData = lineData;
	}

}
